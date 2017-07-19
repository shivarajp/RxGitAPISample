package com.shivaraj.githubapi.presenters;

import com.shivaraj.githubapi.models.AuthorItemModel;
import com.shivaraj.githubapi.models.Example;
import com.shivaraj.githubapi.retrofit.GithubApi;
import com.shivaraj.githubapi.views.screen_contracts.MainScreenContract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by H237872 on 4/25/2017.
 */

public class MainActivityPresenter {

    GithubApi githubApi;
    Subscription subscription;
    MainScreenContract mainScreenContractContract;
    HashMap<Integer,AuthorItemModel> modelsForList2 = new HashMap<Integer, AuthorItemModel>();

    public MainActivityPresenter(){}

    @Inject
    public MainActivityPresenter (GithubApi api){
        githubApi = api;
    }

    public void loadCommits(MainScreenContract mainScreenContractContract){
        this.mainScreenContractContract = mainScreenContractContract;
        mainScreenContractContract.progressUpdater(true);
        subscription = githubApi.getCommitsObservable()
                .subscribeOn(Schedulers.io())
                .flatMap(allCommits -> Observable.from(allCommits))
                .limit(30)
                .flatMap(oneCommit -> saveCommit(oneCommit))
                .filter(eachCommit -> eachCommit.getAuthor() != null)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(e -> mainScreenContractContract.onError(e.getMessage()))
                .subscribe(m->loadList(modelsForList2));
    }

    public Observable<Example> saveCommit(Example oneCommit) {
        modelsForList2 = groupAndAddToModelsList(oneCommit, modelsForList2);
                return Observable.just(oneCommit);
    }

    public HashMap<Integer, AuthorItemModel> groupAndAddToModelsList(Example oneCommit, HashMap<Integer, AuthorItemModel> modelsForList2) {
        HashMap<Integer,AuthorItemModel> modelsForList = modelsForList2;
        if(modelsForList.keySet().contains(oneCommit.getAuthor().getId())){
            AuthorItemModel authorItemModel2 = modelsForList.get(oneCommit.getAuthor().getId());
            authorItemModel2.getMessages().add(oneCommit.getCommit().getMessage());
            authorItemModel2.setMessages(authorItemModel2.getMessages());

            authorItemModel2.getDate().add(oneCommit.getCommit().getAuthor().getDate().substring(0,11));
            authorItemModel2.setDate(authorItemModel2.getDate());
            modelsForList.put(oneCommit.getAuthor().getId(),authorItemModel2);
        }else {
            AuthorItemModel authorItemModel = new AuthorItemModel();
            List<String> messages = new ArrayList<>();
            messages.add(oneCommit.getCommit().getMessage());
            authorItemModel.setMessages(messages);

            List<String> dates = new ArrayList<>();
            dates.add(oneCommit.getCommit().getAuthor().getDate().substring(0,11));
            authorItemModel.setDate(dates);
            modelsForList.put(oneCommit.getAuthor().getId(), authorItemModel);
        }
        return modelsForList;
    }

    public void loadList(HashMap<Integer, AuthorItemModel> modelsForList){
        mainScreenContractContract.progressUpdater(false);
        mainScreenContractContract.loadList(modelsForList);
    }

    public void unSubscribe(){
        subscription.unsubscribe();
    }
}
