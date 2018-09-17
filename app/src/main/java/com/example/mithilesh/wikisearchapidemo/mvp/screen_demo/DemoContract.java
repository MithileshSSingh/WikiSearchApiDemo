package com.example.mithilesh.wikisearchapidemo.mvp.screen_demo;

import com.example.mithilesh.wikisearchapidemo.mvp.BasePresenter;
import com.example.mithilesh.wikisearchapidemo.mvp.BaseView;


public class DemoContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {

    }
}
