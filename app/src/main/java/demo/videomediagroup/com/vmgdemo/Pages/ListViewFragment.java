package demo.videomediagroup.com.vmgdemo.Pages;
/**
 * Copyright © 2017 Video Media Group, Seyfullah Semen All rights reserved
 * <p>
 * Created by Seyfullah Semen
 * </p>
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.vmg.BaseUtils.VMGBase;
import com.yalantis.phoenix.PullToRefreshView;

import demo.videomediagroup.com.vmgdemo.R;

public class ListViewFragment extends Fragment {
    private ListView mListView;
    private WebView mWebView;
    private VMGBase mBase;
    private PullToRefreshView mRefreshListview;

    public ListViewFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.fragment_list_view, container, false);
        mRefreshListview = v.findViewById(R.id.refreshListview);
        mRefreshListview.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRefreshListview.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRefreshListview.setRefreshing(false);
                        mBase = new VMGBase(getActivity(), mWebView, 6370);

                    }
                }, 2000);
            }
        });
        mListView = v.findViewById(R.id.listView);
        mWebView = v.findViewById(R.id.webber); // get the id of the custom webview
        mBase = new VMGBase(getActivity(), mWebView, 6370);

        return v;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        setValues(mListView);

    }

    private void setValues(ListView listView) {
        int size = 50;
        String[] vals = new String[size];

        for (int i = 0; i < vals.length; i++) {
            vals[i] = "VMG Demo " + i;
        }

        ArrayAdapter adapter = new ArrayAdapter<>(getActivity(),
                R.layout.list_row, R.id.listViewText, vals);
        listView.setAdapter(adapter);
    }
}
