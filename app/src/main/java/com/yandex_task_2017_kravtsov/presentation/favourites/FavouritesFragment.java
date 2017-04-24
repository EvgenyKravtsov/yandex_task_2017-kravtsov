package com.yandex_task_2017_kravtsov.presentation.favourites;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.yandex_task_2017_kravtsov.DependencyInjection;
import com.yandex_task_2017_kravtsov.R;
import com.yandex_task_2017_kravtsov.domain.Translation;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;
import io.reactivex.disposables.CompositeDisposable;

public final class FavouritesFragment extends Fragment {

    private static final String TAG = FavouritesFragment.class.getSimpleName();

    //// WIDGETS

    @BindView(R.id.favouritesFragment_searchEditText)
    EditText searchEditText;
    @BindView(R.id.favouritesFragment_favouritesRecyclerView)
    RecyclerView favouritesRecyclerView;

    private FavouritesViewModel viewModel;
    private CompositeDisposable viewModelSubscription;
    private FavouritesRecyclerAdapter favouritesRecyclerAdapter;

    //// FRAGMENT

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = DependencyInjection.provideFavouritesViewModel();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourites, container, false);
        ButterKnife.bind(this, view);
        setupFavouritesRecyclerView();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        bindViewModel();
    }

    @Override
    public void onPause() {
        super.onPause();
        unbindViewModel();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.getUserVisibleHint();
        if (isVisibleToUser) viewModel.searchFavourites(searchEditText.getText().toString());
    }

    //// CONTROL CALLBACKS

    @OnTextChanged(R.id.favouritesFragment_searchEditText)
    public void onTextChangedInSearchEditText() {
        viewModel.searchFavourites(searchEditText.getText().toString());
    }

    //// PRIVATE

    private void bindViewModel() {
        viewModelSubscription = new CompositeDisposable();
        viewModelSubscription.add(viewModel.getFavourites().subscribe(this::updateHistory));
    }

    private void unbindViewModel() {
        viewModelSubscription.dispose();
    }

    private void setupFavouritesRecyclerView() {
        favouritesRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        favouritesRecyclerView.setLayoutManager(layoutManager);
        favouritesRecyclerAdapter = new FavouritesRecyclerAdapter();
        favouritesRecyclerView.setAdapter(favouritesRecyclerAdapter);
    }

    private void updateHistory(List<Translation> translations) {
        favouritesRecyclerAdapter.updateFavourites(translations);
    }
}
