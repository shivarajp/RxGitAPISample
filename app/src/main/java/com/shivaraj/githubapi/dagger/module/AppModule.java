package com.shivaraj.githubapi.dagger.module;

import com.shivaraj.githubapi.retrofit.GithubApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by H237872 on 4/25/2017.
 */

@Module
public class AppModule {

    @Provides
    @Singleton
    GithubApi providePiStatsAPI(){
        return new GithubApi();
    }
}

