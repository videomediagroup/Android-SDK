package demo.videomediagroup.com.vmgdemo.Pages;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vmg.BaseUtils.VMGBase;

import demo.videomediagroup.com.vmgdemo.R;

public class InPageScrollWithLayout extends Fragment {
    private ConstraintLayout mConstraint;
    private NestedScrollView mNested;
    private VMGBase mBase;

    public InPageScrollWithLayout() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.in_page_scroll_with_layout, container, false);
        mConstraint = view.findViewById(R.id.constraint);
        mNested = view.findViewById(R.id.nested);

        mBase = new VMGBase(getActivity(), mConstraint, 6194);
        mNested.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                mBase.VMGScrollEvent(mNested, mConstraint);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}
