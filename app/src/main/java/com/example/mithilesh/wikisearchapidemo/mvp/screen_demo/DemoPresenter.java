package com.example.mithilesh.wikisearchapidemo.mvp.screen_demo;

import com.example.mithilesh.wikisearchapidemo.data.Repository;


public class DemoPresenter implements DemoContract.Presenter {

    private Repository mRepository = null;
    private DemoContract.View mView = null;

    private DemoPresenter() {
    }

    public DemoPresenter(Repository repository, DemoContract.View view) {

        mRepository = repository;
        mView = view;

        mView.setPresenter(this);
    }

}
