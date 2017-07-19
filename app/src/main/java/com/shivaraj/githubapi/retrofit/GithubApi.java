package com.shivaraj.githubapi.retrofit;

import com.shivaraj.githubapi.models.Commit;
import com.shivaraj.githubapi.models.Example;

import java.util.List;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by H237872 on 4/25/2017.
 */

public class GithubApi {

    private static final String BASE_URL = "https://api.github.com/repos/rails/rails/";
    private ApiService apiService = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService.class);

    public interface ApiService {
        @GET("commits")
        Observable<List<Example>> getCommits();
    }

    public Observable<List<Example>> getCommitsObservable(){
        return apiService.getCommits();
    }

}
