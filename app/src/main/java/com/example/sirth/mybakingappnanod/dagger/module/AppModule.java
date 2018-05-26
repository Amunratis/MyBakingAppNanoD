package com.example.sirth.mybakingappnanod.dagger.module;


import android.app.Application;

import javax.inject.Singleton;

import dagger.Provides;
import dagger.Module;

@Module
    public class AppModule {
        Application mApplication;

        public AppModule(Application mApplication) {
            this.mApplication = mApplication;
        }

        @Provides
        @Singleton
        Application provideApplication() {
            return mApplication;
        }
    }
