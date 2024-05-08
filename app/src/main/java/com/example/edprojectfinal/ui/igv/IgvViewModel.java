package com.example.edprojectfinal.ui.igv;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
public class IgvViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public IgvViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is igv fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
