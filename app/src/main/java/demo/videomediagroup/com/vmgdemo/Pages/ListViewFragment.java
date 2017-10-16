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
    private ListView listView;
    private WebView webber;
    private VMGBase frag;
    private PullToRefreshView refreshListview;

    public ListViewFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.fragment_list_view, container, false);
        refreshListview = v.findViewById(R.id.refreshListview);
        refreshListview.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshListview.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshListview.setRefreshing(false);
                        frag = new VMGBase(getActivity(), webber, 6370);

                    }
                }, 2000);
            }
        });
        listView = v.findViewById(R.id.listView);
        webber = v.findViewById(R.id.webber); // get the id of the custom webview
        frag = new VMGBase(getActivity(), webber, 6370);

        return v;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        setValues(listView);

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
