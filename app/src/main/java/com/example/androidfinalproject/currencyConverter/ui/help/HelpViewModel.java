package com.example.androidfinalproject.currencyConverter.ui.help;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * This class contain value of help view model
 */
public class HelpViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HelpViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Author: Wajahat Ali");
    }

    public LiveData<String> getText() {
        return mText;
    }
}