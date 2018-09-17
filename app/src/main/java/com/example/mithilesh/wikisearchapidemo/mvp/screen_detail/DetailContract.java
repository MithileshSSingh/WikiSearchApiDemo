package com.example.mithilesh.wikisearchapidemo.mvp.screen_detail;

import com.example.mithilesh.wikisearchapidemo.mvp.BasePresenter;
import com.example.mithilesh.wikisearchapidemo.mvp.BaseView;
import com.example.mithilesh.wikisearchapidemo.mvp.model.BeanWikiPage;


public class DetailContract {

    interface View extends BaseView<Presenter> {

        interface GetWikiDetailCallBack {
            void success(BeanWikiPage page);

            void failed(int errorCode, String errorMessage);
        }
    }

    interface Presenter extends BasePresenter {
        void getWikiDetail(String pageId, View.GetWikiDetailCallBack callBack);
    }
}
