package com.shivaraj.githubapi;

import com.shivaraj.githubapi.models.AuthorItemModel;
import com.shivaraj.githubapi.models.Author_;
import com.shivaraj.githubapi.models.Example;
import com.shivaraj.githubapi.presenters.MainActivityPresenter;
import com.shivaraj.githubapi.views.screen_contracts.MainScreenContract;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashMap;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by H237872 on 5/4/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTestManager {

    MainActivityPresenter presenter;

    @Before
    public void setUp(){
        presenter = new MainActivityPresenter();
    }

    private HashMap<Integer, AuthorItemModel> loadDummyModelsToTest() {
        HashMap<Integer,AuthorItemModel> modelsForList = mock(HashMap.class);
        for(int i=0; i <=20; i++){
            modelsForList.put(i, getDummyAuthorModel(i));
        }
        return modelsForList;
    }

    public AuthorItemModel getDummyAuthorModel(int key){
        AuthorItemModel itemModel = new AuthorItemModel();
        ArrayList<String> dates = new ArrayList<>();
        ArrayList<String> msgs = new ArrayList<>();
        dates.add(key+"-2-17");
        msgs.add(key+" dummy message");
        itemModel.setDate(dates);
        itemModel.setMessages(msgs);
        return itemModel;
    }

    @Test
    public void testGroupAndAddToModelsList(){
        MainActivityPresenter presenter = mock(MainActivityPresenter.class);
        Example dummyObj = mock(Example.class);
        Author_ author_ = mock(Author_.class);
        dummyObj.setAuthor(author_);
//        dummyObj.getAuthor().setId(12331);
        int size = loadDummyModelsToTest().size();
        verify(presenter).groupAndAddToModelsList(dummyObj, mock(HashMap.class));
        //int size2 = presenter.groupAndAddToModelsList(dummyObj, loadDummyModelsToTest()).size();
        //assert size < size2;
    }

    @Test
    public void verifyLoadListInvoked(){
        MainScreenContract mainScreenContract = mock(MainScreenContract.class);
        MainActivityPresenter presenter = mock(MainActivityPresenter.class);
        presenter.loadList(loadDummyModelsToTest());
        verify(mainScreenContract).loadList(mock(HashMap.class));
    }
}
