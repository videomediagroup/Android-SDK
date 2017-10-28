package demo.videomediagroup.com.vmgdemo;
/**
 * Copyright © 2017 Video Media Group, Seyfullah Semen All rights reserved
 * <p>
 * Created by Seyfullah Semen
 * </p>
 */

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.vmg.ConfigVMG.VMGConfig;
import com.vmg.LoggerPack.VMGLogs;

import demo.videomediagroup.com.vmgdemo.Pages.AboutVMGFragment;
import demo.videomediagroup.com.vmgdemo.Pages.HomeFragment;
import demo.videomediagroup.com.vmgdemo.Pages.InPageScrollWithLayout;
import demo.videomediagroup.com.vmgdemo.Pages.ListViewFragment;
import demo.videomediagroup.com.vmgdemo.Pages.RecyclerFragment;
import demo.videomediagroup.com.vmgdemo.Pages.ScrollFragment;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private Toolbar mToolbar;
    private FrameLayout mFragmentContainer;
    private HomeFragment fragment = new HomeFragment();
    private DrawerLayout.DrawerListener mDrawerListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mFragmentContainer = (FrameLayout) findViewById(R.id.frags_container);
        setSupportActionBar(mToolbar);
        if (savedInstanceState == null) {
            FragmentManager mFragmentManager = getSupportFragmentManager();
            FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();

            mFragmentTransaction.add(R.id.frags_container, fragment);
            mFragmentTransaction.commit();
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                mToolbar,
                R.string.drawer_open,
                R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                if (mDrawerListener != null) {
                    mDrawerListener.onDrawerClosed(mDrawerLayout);
                }
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                if (mDrawerListener != null) {
                    mDrawerListener.onDrawerOpened(mDrawerLayout);
                }
                invalidateOptionsMenu();
            }
        };

        mDrawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        changeView();
        VMGConfig.loadConfig(getApplicationContext(), 4426);
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        mActionBarDrawerToggle.syncState();
        super.onPostCreate(savedInstanceState, persistentState);
    }


    private void changeView() {
        Button mAboutFragment = (Button) findViewById(R.id.about_vmg);
        Button mHomeFragment = (Button) findViewById(R.id.home);
        Button mScrollFragment = (Button) findViewById(R.id.scrollview_vmg);
        Button mInReadTopListView = (Button) findViewById(R.id.in_read_top_listview);
        Button mInReadToprecyclerview = (Button) findViewById(R.id.in_read_top_recyclerview);
        Button mInreadScrollViewWithLayout = (Button) findViewById(R.id.scrollviewWithLayout_vmg);

        mHomeFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewFragment(new HomeFragment());
            }
        });

        mAboutFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewFragment(new AboutVMGFragment());
            }
        });

        mScrollFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewFragment(new ScrollFragment());
            }
        });

        mInReadTopListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewFragment(new ListViewFragment());
            }
        });
        mInReadToprecyclerview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewFragment(new RecyclerFragment());
            }
        });

        mInreadScrollViewWithLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewFragment(new InPageScrollWithLayout());
            }
        });

    }

    private void openNewFragment(Fragment fragment) {
        String stateName = ((Object) fragment).getClass().getName();
        try {
            FragmentManager mFragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = mFragmentManager.beginTransaction();

            transaction.replace(R.id.frags_container, fragment, ((Object) fragment).getClass().getName());
            transaction.addToBackStack(stateName);
            mDrawerLayout.closeDrawer(GravityCompat.START);

            transaction.commit();
        } catch (IllegalStateException ex) {
            System.err.println("An error occurred with the Fragment");
        }

    }
}
