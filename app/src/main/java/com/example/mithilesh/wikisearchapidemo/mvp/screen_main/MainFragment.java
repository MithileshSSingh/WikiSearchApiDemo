package com.example.mithilesh.wikisearchapidemo.mvp.screen_main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.mithilesh.wikisearchapidemo.R;
import com.example.mithilesh.wikisearchapidemo.di.RepositoryInjector;
import com.example.mithilesh.wikisearchapidemo.mvp.BaseFragment;
import com.example.mithilesh.wikisearchapidemo.mvp.listeners.OnItemClicked;
import com.example.mithilesh.wikisearchapidemo.mvp.model.BeanSearchItem;
import com.example.mithilesh.wikisearchapidemo.mvp.screen_detail.DetailActivity;
import com.example.mithilesh.wikisearchapidemo.utils.AppConstants;
import com.example.mithilesh.wikisearchapidemo.utils.AppPref;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class MainFragment extends BaseFragment implements MainContract.View, OnItemClicked {

    public static final String TAG = MainFragment.class.getSimpleName();

    private MainContract.Presenter mPresenter;

    private LinearLayoutManager mLayoutManagerRV;
    private SearchResultListAdapter mAdapter;
    private ArrayList<BeanSearchItem> mListData = new ArrayList<>();
    private RecyclerView mRecyclerViewSearchResult;

    private LinearLayout llNoDataLayout;
    private LinearLayout llProgressLayout;
    private LinearLayout llDataLayout;

    public MainFragment() {
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_main, container, false);
        setHasOptionsMenu(true);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search_menu, menu);

        SearchView search = (SearchView) menu.findItem(R.id.search).getActionView();

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                searchInListAndUpdate(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {

                return false;

            }

        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setScreenTitle(R.string.screen_name_search);
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
        llNoDataLayout = (LinearLayout) mView.findViewById(R.id.llNoDataLayout);
        llProgressLayout = (LinearLayout) mView.findViewById(R.id.llProgressLayout);
        llDataLayout = (LinearLayout) mView.findViewById(R.id.llDataLayout);

        mRecyclerViewSearchResult = (RecyclerView) mView.findViewById(R.id.recyclerViewSearchResult);

    }

    @Override
    protected void initMembers() {
        mPresenter = new MainPresenter(RepositoryInjector.provideRepository(getContext().getApplicationContext()), this);

        initRecyclerView();

        Type type = new TypeToken<ArrayList<BeanSearchItem>>() {
        }.getType();
        String json = AppPref.getString(mActivity.getApplicationContext(), AppPref.Keys.LAST_SEARCH_DATA);

        if (!TextUtils.isEmpty(json)) {
            List<BeanSearchItem> list;

            list = (new Gson()).fromJson(json, type);

            if (list != null && list.size() > 0) {
                mListData.clear();
                mListData.addAll(list);
                mAdapter.notifyDataSetChanged();

                llDataLayout.setVisibility(View.VISIBLE);
                llNoDataLayout.setVisibility(View.GONE);
                llProgressLayout.setVisibility(View.GONE);
            }
        }

    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {

    }

    @Override
    public void onItemClicked(View v, int position) {

        BeanSearchItem beanSearchItem = mListData.get(position);

        Intent intent = new Intent(mActivity, DetailActivity.class);

        Bundle bundle = new Bundle();
        bundle.putString(AppConstants.IntentKey.DATA_STRING, beanSearchItem.getPageId());
        intent.putExtra(AppConstants.IntentKey.DATA_STREAM, bundle);

        startActivity(intent);
    }

    private void initRecyclerView() {

        mAdapter = new SearchResultListAdapter(mActivity, mListData, this);
        mLayoutManagerRV = new LinearLayoutManager(mActivity.getApplicationContext());
        RecyclerView.ItemAnimator itemAnimatorVertical = new DefaultItemAnimator();

        mRecyclerViewSearchResult.setHasFixedSize(true);
        mRecyclerViewSearchResult.setLayoutManager(mLayoutManagerRV);
        mRecyclerViewSearchResult.setItemAnimator(itemAnimatorVertical);
        mRecyclerViewSearchResult.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();

    }

    private void searchInListAndUpdate(String query) {

        llDataLayout.setVisibility(View.GONE);
        llNoDataLayout.setVisibility(View.GONE);
        llProgressLayout.setVisibility(View.VISIBLE);
        mPresenter.getSearchResult(query, new GetSerchResultCallBack() {
            @Override
            public void success(List<BeanSearchItem> dataList) {

                llDataLayout.setVisibility(View.VISIBLE);
                llNoDataLayout.setVisibility(View.GONE);
                llProgressLayout.setVisibility(View.GONE);


                // Saving data
                Type type = new TypeToken<ArrayList<BeanSearchItem>>() {
                }.getType();

                String json = (new Gson()).toJson(dataList, type);
                AppPref.putString(mActivity.getApplicationContext(), AppPref.Keys.LAST_SEARCH_DATA, json);

                mListData.clear();
                mListData.addAll(dataList);
                mAdapter.setListData(mListData);
            }

            @Override
            public void failed(int errorCode, String errorMessage) {

                llDataLayout.setVisibility(View.GONE);
                llNoDataLayout.setVisibility(View.VISIBLE);
                llProgressLayout.setVisibility(View.GONE);

                Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
