package com.example.mithilesh.wikisearchapidemo.mvp.screen_main;

import com.example.mithilesh.wikisearchapidemo.data.DataSource;
import com.example.mithilesh.wikisearchapidemo.data.Repository;
import com.example.mithilesh.wikisearchapidemo.data.remote.ApiClient;
import com.example.mithilesh.wikisearchapidemo.data.remote.model.ResponseGetSearchResult;
import com.example.mithilesh.wikisearchapidemo.mvp.model.BeanSearchItem;

import java.util.ArrayList;

public class MainPresenter implements MainContract.Presenter {

    private Repository mRepository = null;
    private MainContract.View mView = null;

    private MainPresenter() {
    }

    public MainPresenter(Repository repository, MainContract.View view) {

        mRepository = repository;
        mView = view;

        mView.setPresenter(this);
    }

    @Override
    public void getSearchResult(String query, final MainContract.View.GetSerchResultCallBack callBack) {
        mRepository.getSearchResult(query, new DataSource.GetSerchResultCallBack() {
            @Override
            public void success(ResponseGetSearchResult data) {

                ArrayList<BeanSearchItem> list = new ArrayList<>();

                if (data.getQuery() != null && data.getQuery().getPages() != null && data.getQuery().getPages().size() > 0) {

                    for (ResponseGetSearchResult.Page page : data.getQuery().getPages()) {

                        BeanSearchItem beanSearchItem = new BeanSearchItem();

                        beanSearchItem.setPageId(String.valueOf(page.getPageid()));
                        beanSearchItem.setTitle(String.valueOf(page.getTitle()));

                        if (page.getThumbnail() != null && page.getThumbnail().getSource() != null) {
                            beanSearchItem.setThumbnail(page.getThumbnail().getSource());
                        }

                        if (page.getTerms() != null && page.getTerms().getDescription() != null && page.getTerms().getDescription().size() > 0) {
                            beanSearchItem.setDescription(page.getTerms().getDescription().get(0));
                        }

                        list.add(beanSearchItem);
                    }

                    callBack.success(list);
                    
                } else {
                    callBack.failed(ApiClient.HttpErrorCode.NO_CODE, "No Data");
                }
            }

            @Override
            public void failed(int errorCode, String errorMessage) {
                callBack.failed(errorCode,errorMessage);
            }
        });
    }
}
