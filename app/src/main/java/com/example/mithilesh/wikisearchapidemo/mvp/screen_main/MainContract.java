package com.example.mithilesh.wikisearchapidemo.mvp.screen_main;

import com.example.mithilesh.wikisearchapidemo.mvp.BasePresenter;
import com.example.mithilesh.wikisearchapidemo.mvp.BaseView;
import com.example.mithilesh.wikisearchapidemo.mvp.model.BeanSearchItem;

import java.util.List;


public class MainContract {

    interface View extends BaseView<Presenter> {

        interface GetSerchResultCallBack {
            void success(List<BeanSearchItem> dataList);

            void failed(int errorCode, String errorMessage);
        }

    }

    interface Presenter extends BasePresenter {

        void getSearchResult(String query, View.GetSerchResultCallBack callBack);

    }
}
