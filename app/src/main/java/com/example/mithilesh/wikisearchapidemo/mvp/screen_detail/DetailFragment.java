package com.example.mithilesh.wikisearchapidemo.mvp.screen_detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.mithilesh.wikisearchapidemo.R;
import com.example.mithilesh.wikisearchapidemo.di.RepositoryInjector;
import com.example.mithilesh.wikisearchapidemo.mvp.BaseFragment;
import com.example.mithilesh.wikisearchapidemo.mvp.model.BeanWikiPage;
import com.example.mithilesh.wikisearchapidemo.utils.AppConstants;


public class DetailFragment extends BaseFragment implements DetailContract.View {

    public static final String TAG = DetailFragment.class.getSimpleName();

    private DetailContract.Presenter mPresenter;

    private String mPageId = "";

    private WebView mWebView;

    public DetailFragment() {
    }

    public static DetailFragment newInstance() {
        return new DetailFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragmet_detail, container, false);
        setHasOptionsMenu(true);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setScreenTitle(R.string.screen_name_detail);
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
        mWebView = (WebView) mView.findViewById(R.id.webview);
    }

    @Override
    protected void initMembers() {
        mPresenter = new DetailPresenter(RepositoryInjector.provideRepository(getContext()), this);

        Bundle bundle = getArguments();
        mPageId = bundle.getString(AppConstants.IntentKey.DATA_STRING);
    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initData() {
        mPresenter.getWikiDetail(mPageId, new GetWikiDetailCallBack() {
            @Override
            public void success(BeanWikiPage page) {
                WebSettings webSettings = mWebView.getSettings();
                webSettings.setJavaScriptEnabled(true);

                WebViewClientImpl webViewClient = new WebViewClientImpl(mActivity);
                mWebView.setWebViewClient(webViewClient);

                mWebView.loadUrl(page.getFullurl());
            }

            @Override
            public void failed(int errorCode, String errorMessage) {

            }
        });
    }

    @Override
    public void setPresenter(DetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

}
