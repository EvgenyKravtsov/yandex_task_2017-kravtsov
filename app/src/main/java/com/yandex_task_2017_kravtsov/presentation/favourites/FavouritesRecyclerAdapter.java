package com.yandex_task_2017_kravtsov.presentation.favourites;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yandex_task_2017_kravtsov.R;
import com.yandex_task_2017_kravtsov.domain.Translation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

final class FavouritesRecyclerAdapter extends RecyclerView.Adapter<FavouritesRecyclerAdapter.ViewHolder> {

    private static final String TAG = FavouritesRecyclerAdapter.class.getSimpleName();

    private List<Translation> translations = new ArrayList<>();

    ////

    void updateFavourites(List<Translation> translations) {
        this.translations = translations;
        notifyDataSetChanged();
    }

    //// RECYCLER ADAPTER

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_favourites, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Translation translation = translations.get(position);
        holder.directionTextView.setText(String.format(
                "%s - %s",
                translation.getLanguageFrom().getCode(),
                translation.getLanguageTo().getCode()));
        holder.fromTextView.setText(translation.getFrom());
        holder.toTextView.setText(stringArrayToSeparatedString(translation.getTo(), ", "));
    }

    @Override
    public int getItemCount() {
        return translations.size();
    }


    ////

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.favouritesListItem_directionTextView)
        TextView directionTextView;
        @BindView(R.id.favouritesListItem_fromTextView)
        TextView fromTextView;
        @BindView(R.id.favouritesListItem_toTextView)
        TextView toTextView;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    //// PRIVATE

    private String stringArrayToSeparatedString(String[] array, String separator) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < array.length; i++) {
            builder.append(array[i]);
            if (i != array.length - 1) builder.append(separator);
        }

        return builder.toString();
    }
}
