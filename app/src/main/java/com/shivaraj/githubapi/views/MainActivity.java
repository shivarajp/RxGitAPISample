package com.shivaraj.githubapi.views;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.shivaraj.githubapi.R;
import com.shivaraj.githubapi.dagger.DaggerInjector;
import com.shivaraj.githubapi.models.AuthorItemModel;
import com.shivaraj.githubapi.presenters.MainActivityPresenter;
import com.shivaraj.githubapi.views.screen_contracts.MainScreenContract;

import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainScreenContract {

    @Inject
    MainActivityPresenter mainActivityPresenter;
    CommitsAdapter mAdapter;

    @BindView(R.id.commits_rv)
    RecyclerView commitsRecyclerView;
    @BindView(R.id.progressbar)
    ProgressBar progressbar;
    @BindView(R.id.coordinator)
    CoordinatorLayout coordinator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        DaggerInjector.get().inject(this);
        init();
        mainActivityPresenter.loadCommits(this);
    }

    private void init() {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        commitsRecyclerView.setNestedScrollingEnabled(true);
        commitsRecyclerView.setLayoutManager(mLayoutManager);
        commitsRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void progressUpdater(boolean show) {
        if(show)
            progressbar.setVisibility(View.VISIBLE);
        else
            progressbar.setVisibility(View.GONE);
    }

    @Override
    public void onError(String message) {
        Snackbar snackbar = Snackbar
                .make(coordinator, "Error:"+ message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public void loadList(Map<Integer, AuthorItemModel> modelsForList) {
        mAdapter = new CommitsAdapter(modelsForList);
        commitsRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onDestroy() {
        mainActivityPresenter.unSubscribe();
        super.onDestroy();
    }
}
