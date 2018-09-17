package com.example.mithilesh.wikisearchapidemo.mvp.screen_demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mithilesh.wikisearchapidemo.R;
import com.example.mithilesh.wikisearchapidemo.di.RepositoryInjector;
import com.example.mithilesh.wikisearchapidemo.mvp.BaseFragment;


public class DemoFragment extends BaseFragment implements DemoContract.View {

    public static final String TAG = DemoFragment.class.getSimpleName();

    private DemoContract.Presenter mPresenter;

    public DemoFragment() {
    }

    public static DemoFragment newInstance() {
        return new DemoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_base_layout, container, false);
        setHasOptionsMenu(true);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    @Override
    protected void init() {
        initView();
        initMembers();
        initListeners();
        initData();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initMembers() {
        mPresenter = new DemoPresenter(RepositoryInjector.provideRepository(getContext()), this);

    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void setPresenter(DemoContract.Presenter presenter) {
        mPresenter = presenter;
    }

}
