package com.example.mithilesh.wikisearchapidemo.data.local;

import android.content.Context;

import com.example.mithilesh.wikisearchapidemo.data.DataSource;

public class LocalDataSource implements DataSource {


    private static LocalDataSource INSTANCE = null;

    private Context mContext;

    private LocalDataSource() {

    }

    private LocalDataSource(Context context) {
        mContext = context;
    }

    public static LocalDataSource getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (LocalDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LocalDataSource(context);
                }
            }
        }

        return INSTANCE;
    }

    @Override
    public void getSearchResult(String query, GetSerchResultCallBack callBack) {

    }

    @Override
    public void getWikiDetail(String pageId, GetWikiDetailCallBack callBack) {

    }
}
