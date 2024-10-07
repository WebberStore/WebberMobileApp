package com.example.webberapp.ui.browse;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BrowseViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public BrowseViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Can not browse right now");
    }

    public LiveData<String> getText() {
        return mText;
    }
}