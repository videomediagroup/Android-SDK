package demo.videomediagroup.com.vmgdemo.Pages;
/**
 * Copyright © 2017 Video Media Group, Seyfullah Semen All rights reserved
 * <p>
 * Created by Seyfullah Semen
 * </p>
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;


import com.vmg.BaseUtils.VMGBase;
import com.yalantis.phoenix.PullToRefreshView;

import java.util.ArrayList;

import demo.videomediagroup.com.vmgdemo.R;
import demo.videomediagroup.com.vmgdemo.adapters.RecycleAdapter;

public class RecyclerFragment extends Fragment {
    protected RecyclerView mRecyclerView;
    private WebView mWebView;
    private VMGBase mBase;
    private PullToRefreshView mRefreshRecyclerview;

    public RecyclerFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_recycler, container, false);
        mRecyclerView = v.findViewById(R.id.recycler);
        mWebView = v.findViewById(R.id.webbs); // get the id of the webview
        mRefreshRecyclerview = v.findViewById(R.id.refreshRecyclerview);
        mRefreshRecyclerview.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRefreshRecyclerview.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRefreshRecyclerview.setRefreshing(false);
                        mBase = new VMGBase(getActivity(), mWebView, 6370);


                    }
                }, 2000);
            }
        });

        mBase = new VMGBase(getActivity(), mWebView, 6370);


        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        setRecyclerViewAdapter(mRecyclerView);
    }

    private void setRecyclerViewAdapter(RecyclerView recyclerView) {
        ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            data.add("VMG " + i);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new RecycleAdapter(data, mRecyclerView));
    }
}
