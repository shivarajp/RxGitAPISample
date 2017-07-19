package com.shivaraj.githubapi.views.screen_contracts;

import com.shivaraj.githubapi.models.AuthorItemModel;

import java.util.Map;

/**
 * Created by H237872 on 4/25/2017.
 */

public interface MainScreenContract {
    void progressUpdater(boolean show);
    void onError(String message);
    void loadList(Map<Integer,AuthorItemModel> modelsForList);
}
