package com.example.coffeepedia.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Many coffee lovers have an idea about how coffees from different parts of the world taste. But even for your average coffee connoisseur, it may still be difficult at times to tell the difference between two coffees from different regions of the world. For instance, trying to decipher the taste of a Guatemalan coffee from a Brazilian. We are here to break down a few of these basic taste differences for you.");
    }

    public LiveData<String> getText() {
        return mText;
    }
}