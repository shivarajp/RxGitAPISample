package com.shivaraj.githubapi.dagger;

import com.shivaraj.githubapi.dagger.components.AppComponent;
import com.shivaraj.githubapi.dagger.components.DaggerAppComponent;
import com.shivaraj.githubapi.dagger.module.AppModule;

/**
 * Created by H237872 on 4/25/2017.
 */

public class DaggerInjector {
    private static AppComponent appComponent = DaggerAppComponent.builder().appModule(new AppModule()).build();

    public static AppComponent get() {
        return appComponent;
    }
}
