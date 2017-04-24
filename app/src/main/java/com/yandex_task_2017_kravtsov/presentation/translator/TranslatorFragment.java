package com.yandex_task_2017_kravtsov.presentation.translator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.yandex_task_2017_kravtsov.DependencyInjection;
import com.yandex_task_2017_kravtsov.R;
import com.yandex_task_2017_kravtsov.domain.Language;
import com.yandex_task_2017_kravtsov.domain.Translation;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import butterknife.OnTextChanged;
import io.reactivex.disposables.CompositeDisposable;

public final class TranslatorFragment extends Fragment {

    //// WIDGETS

    @BindView(R.id.translatorFragment_languagesFromSpinner)
    Spinner languagesFromSpinner;
    @BindView(R.id.translatorFragment_languagesToSpinner)
    Spinner languagesToSpinner;
    @BindView(R.id.translatorFragment_translateFromEditText)
    EditText translateFromEditText;
    @BindView(R.id.translatorFragment_translatedToTextView)
    TextView translatedToTextView;

    private static final String TAG = TranslatorFragment.class.getSimpleName();

    private TranslatorViewModel viewModel;
    private CompositeDisposable viewModelSubscription;
    private LanguageSpinnerAdapter languageFromAdapter;
    private LanguageSpinnerAdapter languageToAdapter;

    //// FRAGMENT

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = DependencyInjection.provideTranslatorViewModel();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_translator, container, false);
        ButterKnife.bind(this, view);
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

    //// CONTROL CALLBACKS

    @OnTextChanged(R.id.translatorFragment_translateFromEditText)
    public void onTextChangedInTranslateFromEditText() {
        String translateFrom = translateFromEditText.getText().toString();
        viewModel.translateFrom(
                translateFrom,
                (Language) languagesFromSpinner.getSelectedItem(),
                (Language) languagesToSpinner.getSelectedItem());
    }

    @OnItemSelected(R.id.translatorFragment_languagesFromSpinner)
    public void onItemSelectedInLanguagesFromSpinner(int position) {
        viewModel.languageFromSelected(languageFromAdapter.getItem(position));
    }

    @OnItemSelected(R.id.translatorFragment_languagesToSpinner)
    public void onItemSelectedInLanguagesToSpinner(int position) {
        viewModel.languageToSelected(languageToAdapter.getItem(position));
        viewModel.translateFrom(
                translateFromEditText.getText().toString(),
                (Language) languagesFromSpinner.getSelectedItem(),
                (Language) languagesToSpinner.getSelectedItem());
    }

    @OnClick(R.id.translatorFragment_swapLanguagesImageButton)
    public void onClickSwapLanguagesImageButton() {
        viewModel.languagesSwapped();
        viewModel.translateFrom(
                translateFromEditText.getText().toString(),
                (Language) languagesFromSpinner.getSelectedItem(),
                (Language) languagesToSpinner.getSelectedItem());
    }

    @OnClick(R.id.translatorFragment_addToFavouritesButton)
    public void onClickAddToFavouritesButton() {
        viewModel.addedToFavourites(new Translation(
                Calendar.getInstance().getTimeInMillis(),
                translateFromEditText.getText().toString(),
                translatedToTextView.getText().toString().split("\n"),
                (Language) languagesFromSpinner.getSelectedItem(),
                (Language) languagesToSpinner.getSelectedItem()
        ));
    }

    //// PRIVATE

    private void bindViewModel() {
        viewModelSubscription = new CompositeDisposable();
        viewModelSubscription.add(viewModel.getAlert().subscribe(this::setAlert));
        viewModelSubscription.add(viewModel.getLanguagesFrom().subscribe(this::setLanguagesFrom));
        viewModelSubscription.add(viewModel.getSelectedLanguageFrom().subscribe(this::setSelectedLanguageFrom));
        viewModelSubscription.add(viewModel.getLanguagesTo().subscribe(this::setLanguagesTo));
        viewModelSubscription.add(viewModel.getSelectedLanguageTo().subscribe(this::setSelectedLanguageTo));
        viewModelSubscription.add(viewModel.getTranslatedTo().subscribe(this::setTranslatedTo));
    }

    private void unbindViewModel() {
        viewModelSubscription.dispose();
    }

    private void setAlert(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    private void setTranslatedTo(String[] translatedTo) {
        StringBuilder result = new StringBuilder();

        for (String string : translatedTo) {
            result.append(string);
            result.append("\n");
        }

        translatedToTextView.setText(result);
    }

    private void setLanguagesFrom(List<Language> languages) {
        languageFromAdapter = new LanguageSpinnerAdapter(
                getActivity(),
                android.R.layout.simple_spinner_item,
                languages);
        languageFromAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languagesFromSpinner.setAdapter(languageFromAdapter);
    }

    private void setSelectedLanguageFrom(Language language) {
        int position = languageFromAdapter.getPosition(language);
        languagesFromSpinner.setSelection(position);
    }

    private void setLanguagesTo(List<Language> languages) {
        languageToAdapter = new LanguageSpinnerAdapter(
                getActivity(),
                android.R.layout.simple_spinner_item,
                languages);
        languageToAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languagesToSpinner.setAdapter(languageToAdapter);
    }

    private void setSelectedLanguageTo(Language language) {
        int position = languageToAdapter.getPosition(language);
        languagesToSpinner.setSelection(position);
    }
}









































