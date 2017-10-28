package demo.videomediagroup.com.vmgdemo.Pages;

/**
 * Copyright © 2017 Video Media Group, Seyfullah Semen All rights reserved
 * <p>
 * Created by Seyfullah Semen
 * </p>
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.vmg.BaseUtils.VMGBase;

import demo.videomediagroup.com.vmgdemo.R;


public class AboutVMGFragment extends Fragment {
    private ImageView mImageLogo;
    private TextView mTextAbout;
    private WebView mWebView;
    private NestedScrollView mScrollView;

    private VMGBase mBase;

    public AboutVMGFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_about_vmg, container, false);
        mImageLogo = v.findViewById(R.id.img_logo_about);
        mTextAbout = v.findViewById(R.id.text_about);
        mWebView = v.findViewById(R.id.web_about);
        mScrollView = v.findViewById(R.id.scroll__about);

        mBase = new VMGBase(getActivity(), mWebView, 6370);
        mScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                mBase.VMGScrollEvent(mScrollView, mWebView);
            }
        });

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

}
