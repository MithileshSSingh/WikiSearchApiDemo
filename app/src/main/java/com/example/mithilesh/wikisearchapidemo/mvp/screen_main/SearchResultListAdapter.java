package com.example.mithilesh.wikisearchapidemo.mvp.screen_main;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mithilesh.wikisearchapidemo.R;
import com.example.mithilesh.wikisearchapidemo.mvp.BaseViewHolder;
import com.example.mithilesh.wikisearchapidemo.mvp.listeners.OnItemClicked;
import com.example.mithilesh.wikisearchapidemo.mvp.model.BeanSearchItem;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;

import java.util.ArrayList;
import java.util.List;

public class SearchResultListAdapter extends RecyclerView.Adapter<SearchResultListViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<BeanSearchItem> mListData;

    private OnItemClicked mListener;

    public SearchResultListAdapter(Context context, ArrayList<BeanSearchItem> listBeanSKUs, OnItemClicked listener) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mListData = listBeanSKUs;
        mListener = listener;

    }

    @Override
    public SearchResultListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = mInflater.inflate(R.layout.item_search_item, parent, false);
        return new SearchResultListViewHolder(mContext, convertView, mListener);
    }

    @Override
    public void onBindViewHolder(SearchResultListViewHolder holder, int position) {
        BeanSearchItem data = mListData.get(position);
        holder.apply(data, position);
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    public void setListData(ArrayList<BeanSearchItem> data) {
        mListData = data;
        notifyDataSetChanged();
    }
}


class SearchResultListViewHolder extends RecyclerView.ViewHolder implements BaseViewHolder<BeanSearchItem>, View.OnClickListener {

    private View mView;
    private int mPosition;
    private Context mContext;
    private BeanSearchItem mData;

    private OnItemClicked mListener;

    private TextView tvTitle;
    private TextView tvDescription;

    private ImageView ivThumbnail;
    private ProgressBar progressBar;

    private RelativeLayout rootLayout;

    public SearchResultListViewHolder(Context context, View itemView, OnItemClicked listener) {
        super(itemView);
        mView = itemView;
        mContext = context;
        mListener = listener;

        init();
    }

    public SearchResultListViewHolder(View itemView) {
        super(itemView);
    }

    private void init() {
        initView();
        initListener();
    }

    private void initView() {
        tvTitle = (TextView) mView.findViewById(R.id.tvTitle);
        tvDescription = (TextView) mView.findViewById(R.id.tvDescription);

        ivThumbnail = (ImageView) mView.findViewById(R.id.ivThumbnail);
        progressBar = (ProgressBar) mView.findViewById(R.id.progressBar);

        rootLayout = (RelativeLayout) mView.findViewById(R.id.rootLayout);
    }

    private void initListener() {
        rootLayout.setOnClickListener(this);
    }

    @Override
    public void apply(BeanSearchItem data, int position) {
        mData = data;
        mPosition = position;

        tvTitle.setText(String.valueOf(mData.getTitle()));
        tvDescription.setText(String.valueOf(mData.getDescription()));

        initImageView();
    }

    private void initImageView() {

        ImageLoader imageLoader = ImageLoader.getInstance();
        DisplayImageOptions options = new DisplayImageOptions.Builder().showImageForEmptyUri(android.R.drawable.stat_notify_error) // resource or drawable
                .showImageOnFail(android.R.drawable.stat_notify_error) // resource or drawable
                .resetViewBeforeLoading(false)  // default
                .delayBeforeLoading(1000).cacheInMemory(true) // default
                .cacheOnDisk(false).build();

        imageLoader.displayImage(mData.getThumbnail(), ivThumbnail, options, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {

                ivThumbnail.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

                ivThumbnail.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

                ivThumbnail.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {

                ivThumbnail.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        }, new ImageLoadingProgressListener() {
            @Override
            public void onProgressUpdate(String imageUri, View view, int current, int total) {
            }
        });
    }

    @Override
    public void onClick(View view) {
        mListener.onItemClicked(null, mPosition);
    }
}
