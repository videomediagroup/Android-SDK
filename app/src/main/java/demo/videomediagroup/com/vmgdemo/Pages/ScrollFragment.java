
package demo.videomediagroup.com.vmgdemo.Pages;
/**
 * Copyright © 2017 Video Media Group, Seyfullah Semen All rights reserved
 * <p>
 * Created by Seyfullah Semen
 * </p>
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.vmg.BaseUtils.VMGBase;
import com.yalantis.phoenix.PullToRefreshView;

import demo.videomediagroup.com.vmgdemo.R;

public class ScrollFragment extends Fragment {
    private WebView mWebView;
    private VMGBase mBase;
    private NestedScrollView mScrollView;
    private PullToRefreshView mRefreshScrollview;

    public ScrollFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_scroll, container, false);
        mScrollView = v.findViewById(R.id.scroll);

        mWebView = v.findViewById(R.id.webView);
        mRefreshScrollview = v.findViewById(R.id.refresScrollview);
        mRefreshScrollview.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRefreshScrollview.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        mRefreshScrollview.setRefreshing(false);
                        mBase = new VMGBase(getActivity(), mWebView, 6370);

                    }
                }, 2000);

            }
        });

        mBase = new VMGBase(getActivity(), mWebView, 6370);
        mScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                mBase.VMGScrollEvent(mScrollView, mWebView);
            }
        });


        return v; // return the view
    }
}
