package com.example.webberapp.ui.cart;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CartViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public CartViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Cart is Empty");
    }

    public LiveData<String> getText() {
        return mText;
    }
}