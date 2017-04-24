package com.yandex_task_2017_kravtsov.presentation.history;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.yandex_task_2017_kravtsov.DependencyInjection;
import com.yandex_task_2017_kravtsov.R;
import com.yandex_task_2017_kravtsov.domain.Language;
import com.yandex_task_2017_kravtsov.domain.Translation;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;
import io.reactivex.disposables.CompositeDisposable;

public final class HistoryFragment extends Fragment {

    private static final String TAG = HistoryFragment.class.getSimpleName();

    //// WIDGETS

    @BindView(R.id.historyFragment_searchEditText)
    EditText searchEditText;
    @BindView(R.id.historyFragment_historyRecyclerView)
    RecyclerView historyRecyclerView;

    private HistoryViewModel viewModel;
    private CompositeDisposable viewModelSubscription;
    private HistoryRecyclerAdapter historyRecyclerAdapter;

    //// FRAGMENT

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = DependencyInjection.provideHistoryViewModel();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        ButterKnife.bind(this, view);
        setupHistoryRecyclerView();
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
        if (isVisibleToUser) viewModel.searchHistory(searchEditText.getText().toString());
    }

    //// CONTROL CALLBACKS

    @OnTextChanged(R.id.historyFragment_searchEditText)
    public void onTextChangedInSearchEditText() {
        viewModel.searchHistory(searchEditText.getText().toString());
    }

    //// PRIVATE

    private void bindViewModel() {
        viewModelSubscription = new CompositeDisposable();
        viewModelSubscription.add(viewModel.getHistory().subscribe(this::updateHistory));
    }

    private void unbindViewModel() {
        viewModelSubscription.dispose();
    }

    private void setupHistoryRecyclerView() {
        historyRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        historyRecyclerView.setLayoutManager(layoutManager);
        historyRecyclerAdapter = new HistoryRecyclerAdapter();
        historyRecyclerView.setAdapter(historyRecyclerAdapter);
    }

    private void updateHistory(List<Translation> translations) {
        historyRecyclerAdapter.updateHistory(translations);
    }
}
