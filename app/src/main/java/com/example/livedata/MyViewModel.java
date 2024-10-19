package com.example.livedata;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {

    //Observes data changes from the UI
    //Live data automatically updates the UI
    private MutableLiveData<Integer> counter = new MutableLiveData<>();
    public void incrementCounter(View view) {
        int current = counter.getValue() !=null ? counter.getValue() : 0;;

        //increase counter by one
        counter.setValue(current + 1);



    }
    public LiveData<Integer> getCounter() {
    return counter;
    }
}
