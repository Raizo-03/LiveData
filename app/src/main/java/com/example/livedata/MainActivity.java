package com.example.livedata;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.livedata.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    MyViewModel viewModel;
    ActivityMainBinding mainBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //Helps access the viewModel class whenever needed in the activity or fragment in different events of
        // the activity/fragment lifecycle.
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);


        //linking binding to viewmodel
        mainBinding.setMyviewmodel(viewModel);

        //Observing the LiveData
        viewModel.getCounter().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
            mainBinding.textView2.setText(String.valueOf(viewModel.getCounter()));
            }
        });
    }

/*
LiveData is an observable data holder class.
Unlike regular observable, LiveData is lifecycle-aware, meaning it respects the lifecycle of the
other app components, such as activities, fragments, or services

If the lifecycle status is STARTED or RESUMED, LiveData considers an observer to be in an
active state. Only active observers are notified of lifecycle updates by LiveData

PROS
-eliminates memory leaks caused by multiple callbacks that send results to the UI thread, ensuring that
the UI is always up to date

-It de-couples tight integration between data, mediator, and the UI to avoid crashed activities




 */

}