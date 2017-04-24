package com.yandex_task_2017_kravtsov.presentation.translator;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.yandex_task_2017_kravtsov.domain.Language;

import java.util.List;

final class LanguageSpinnerAdapter extends ArrayAdapter<Language> {

    private List<Language> languages;

    ////

    LanguageSpinnerAdapter(Context context, int resourceId, List<Language> languages) {
        super(context, resourceId, languages);
        this.languages = languages;
    }

    //// ARRAY ADAPTER

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater.from(getContext()));
            view = inflater.inflate(android.R.layout.simple_spinner_item, null);
        }

        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        Language language = languages.get(position);
        textView.setText(language.getTitle());

        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater.from(getContext()));
            view = inflater.inflate(android.R.layout.simple_spinner_dropdown_item, null);
        }

        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        Language language = languages.get(position);
        textView.setText(language.getTitle());

        return view;
    }
}
