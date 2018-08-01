package com.mygit.recyclerview_demo.refresh_recyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Window;

import com.mygit.recyclerview_demo.R;
import com.mygit.recyclerview_demo.refresh_recyclerview.pulltorefresh.baseview.PullToRefreshLayout;
import com.mygit.recyclerview_demo.refresh_recyclerview.pulltorefresh.pulltorefreshview.PullToRefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by admin on 2018/7/31.
 */

public class RefreshRecyclerActivity extends Activity implements PullToRefreshLayout.OnRefreshListener {
    private static final int DEFALUT_PAGE = 1;
    @Bind(R.id.refresh_recycler)
    PullToRefreshRecyclerView refresh_recycler;
    private ArrayList<String> list;
    private AdapterRefreshRecycler adapterRefreshRecycler;
    private int currentPage = DEFALUT_PAGE;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_refresh_recycler);
        ButterKnife.bind(this);
        initAdapter();
    }

    private void initAdapter() {
        refresh_recycler.setOnRefreshListener(this);
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("这是第" + i + "行");
        }
        //垂直布局
        adapterRefreshRecycler = new AdapterRefreshRecycler(list);
        refresh_recycler.setAdapter(adapterRefreshRecycler);
        refresh_recycler.setCanRefresh(true);
        refresh_recycler.setCanRefresh(true);
    }

    @Override
    public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
        list.clear();
        currentPage = DEFALUT_PAGE;
        for (int i = 0; i < 10; i++) {
            list.add("这是第" + i + "行");
        }
        adapterRefreshRecycler.setData(list);
        refresh_recycler.Finish();
    }

    @Override
    public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
        currentPage = currentPage + 10;
        List<String> mList=new ArrayList<>();
        for (int i = currentPage; i < currentPage+10; i++) {
            mList.add("这是第" + i + "行");
        }
        list.addAll(mList);
        adapterRefreshRecycler.setData(list);
        refresh_recycler.Finish();
    }
}

