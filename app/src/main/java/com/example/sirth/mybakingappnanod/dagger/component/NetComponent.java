package com.example.sirth.mybakingappnanod.dagger.component;

import com.example.sirth.mybakingappnanod.baseClasses.BaseActivity;
import com.example.sirth.mybakingappnanod.dagger.module.AppModule;
import com.example.sirth.mybakingappnanod.dagger.module.NetModule;
import com.example.sirth.mybakingappnanod.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    void inject(BaseActivity baseActivity);
}