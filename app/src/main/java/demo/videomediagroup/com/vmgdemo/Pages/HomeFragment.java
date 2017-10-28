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
import android.widget.ImageView;
import android.widget.TextView;

import demo.videomediagroup.com.vmgdemo.R;

public class HomeFragment extends Fragment {
    private TextView mTxtHome;
    private ImageView mImageHome;

    public HomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_home, container, false);
        mTxtHome = v.findViewById(R.id.txt_home);
        mImageHome = v.findViewById(R.id.img_home);

        return v;
    }
}
