package com.example.mithilesh.wikisearchapidemo.di;

import android.content.Context;

import com.example.mithilesh.wikisearchapidemo.data.Repository;
import com.example.mithilesh.wikisearchapidemo.data.local.LocalDataSource;
import com.example.mithilesh.wikisearchapidemo.data.remote.RemoteDataSource;

public class RepositoryInjector {

    public static Repository provideRepository(Context context) {
        return Repository.getInstance(LocalDataSource.getInstance(context), RemoteDataSource.getInstance(context));
    }
}
