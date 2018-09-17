package com.example.mithilesh.wikisearchapidemo.mvp.screen_detail;

import com.example.mithilesh.wikisearchapidemo.data.DataSource;
import com.example.mithilesh.wikisearchapidemo.data.Repository;
import com.example.mithilesh.wikisearchapidemo.data.remote.ApiClient;
import com.example.mithilesh.wikisearchapidemo.data.remote.model.PageInfo;
import com.example.mithilesh.wikisearchapidemo.data.remote.model.ResponseGetWikiPage;
import com.example.mithilesh.wikisearchapidemo.mvp.model.BeanWikiPage;


public class DetailPresenter implements DetailContract.Presenter {

    private Repository mRepository = null;
    private DetailContract.View mView = null;

    private DetailPresenter() {
    }

    public DetailPresenter(Repository repository, DetailContract.View view) {

        mRepository = repository;
        mView = view;

        mView.setPresenter(this);
    }

    @Override
    public void getWikiDetail(final String pageId, final DetailContract.View.GetWikiDetailCallBack callBack) {
        mRepository.getWikiDetail(pageId, new DataSource.GetWikiDetailCallBack() {
            @Override
            public void success(ResponseGetWikiPage page) {
                if (page != null && page.getQuery() != null && page.getQuery().getPages() != null) {
                    BeanWikiPage beanWikiPage = new BeanWikiPage();
                    PageInfo pageInfo = page.getQuery().getPages().get(pageId);

                    beanWikiPage.setFullurl(pageInfo.getFullurl());
                    callBack.success(beanWikiPage);

                } else {
                    callBack.failed(ApiClient.HttpErrorCode.NO_CODE, "No Data");
                }
            }

            @Override
            public void failed(int errorCode, String errorMessage) {
                callBack.failed(errorCode, errorMessage);
            }
        });
    }
}
