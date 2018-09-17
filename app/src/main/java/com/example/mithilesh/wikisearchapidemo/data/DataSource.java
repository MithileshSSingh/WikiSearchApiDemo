package com.example.mithilesh.wikisearchapidemo.data;


import com.example.mithilesh.wikisearchapidemo.data.remote.model.ResponseGetSearchResult;
import com.example.mithilesh.wikisearchapidemo.data.remote.model.ResponseGetWikiPage;

public interface DataSource {

    void getSearchResult(String query, GetSerchResultCallBack callBack);

    void getWikiDetail(String pageId, GetWikiDetailCallBack callBack);


    interface GetWikiDetailCallBack {
        void success(ResponseGetWikiPage page);

        void failed(int errorCode, String errorMessage);
    }

    interface GetSerchResultCallBack {
        void success(ResponseGetSearchResult data);

        void failed(int errorCode, String errorMessage);
    }

}
