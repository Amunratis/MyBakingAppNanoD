package com.example.sirth.mybakingappnanod.baseClasses;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import com.example.sirth.mybakingappnanod.App;

import javax.inject.Inject;

import retrofit2.Retrofit;

public abstract class BaseActivity extends AppCompatActivity{

    @Inject public Retrofit retrofit;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable
            PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        ((App) getApplication()).getNetComponent().inject(this);
    }

    public abstract void onPositionDiscontinuity();
}
