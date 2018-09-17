package com.example.mithilesh.wikisearchapidemo.data;


import com.example.mithilesh.wikisearchapidemo.data.remote.model.ResponseGetSearchResult;
import com.example.mithilesh.wikisearchapidemo.data.remote.model.ResponseGetWikiPage;

public class Repository implements DataSource {


    private static Repository INSTANCE = null;

    private DataSource mLocalDataSource = null;
    private DataSource mRemoteDataSource = null;

    private Repository() {

    }

    private Repository(DataSource localDataSource, DataSource remoteDataSource) {
        mLocalDataSource = localDataSource;
        mRemoteDataSource = remoteDataSource;
    }

    public static Repository getInstance(DataSource localDataSource, DataSource remoteDataSource) {

        if (INSTANCE == null) {
            INSTANCE = new Repository(localDataSource, remoteDataSource);
        }

        return INSTANCE;
    }

    @Override
    public void getSearchResult(String query, final GetSerchResultCallBack callBack) {
        mRemoteDataSource.getSearchResult(query, new GetSerchResultCallBack() {
            @Override
            public void success(ResponseGetSearchResult data) {
                callBack.success(data);
            }

            @Override
            public void failed(int errorCode, String errorMessage) {
                callBack.failed(errorCode,errorMessage);
            }
        });
    }

    @Override
    public void getWikiDetail(String pageId, final GetWikiDetailCallBack callBack) {
        mRemoteDataSource.getWikiDetail(pageId, new GetWikiDetailCallBack() {
            @Override
            public void success(ResponseGetWikiPage page) {
                callBack.success(page);
            }

            @Override
            public void failed(int errorCode, String errorMessage) {
                callBack.failed(errorCode,errorMessage);
            }
        });
    }
}