package com.example.sirth.mybakingappnanod;

import android.app.Application;

import com.example.sirth.mybakingappnanod.dagger.component.DaggerNetComponent;
import com.example.sirth.mybakingappnanod.dagger.component.NetComponent;
import com.example.sirth.mybakingappnanod.dagger.module.AppModule;
import com.example.sirth.mybakingappnanod.dagger.module.NetModule;

public class App extends Application {
    private NetComponent mNetComponent;

    private static App mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;


        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule("https://d17h27t6h515a5.cloudfront.net/topher/2017/May/"))
                .build();
    }

    public static synchronized App getmInstance () {return mInstance;}

    public NetComponent getNetComponent() {
        return mNetComponent;
    }
}