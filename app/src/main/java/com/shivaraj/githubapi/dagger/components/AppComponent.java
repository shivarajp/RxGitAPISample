package com.shivaraj.githubapi.dagger.components;

import com.shivaraj.githubapi.views.MainActivity;
import com.shivaraj.githubapi.dagger.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by H237872 on 4/25/2017.
 */

@Component(modules = AppModule.class)
@Singleton
public interface AppComponent {
    void inject(MainActivity mainActivity);
}