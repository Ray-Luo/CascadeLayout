package com.raystone.ray.cascadelayout;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Ray on 1/7/2016.
 */
public class MainFragment extends Fragment {

    private View mView;

    public static MainFragment newInstance()
    {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        mView = null;
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(layoutInflater,container,savedInstanceState);
        mView = layoutInflater.inflate(R.layout.activity_main,container,false);
        return mView;
    }
}
