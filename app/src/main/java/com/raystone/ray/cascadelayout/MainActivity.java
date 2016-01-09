package com.raystone.ray.cascadelayout;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);
        FragmentManager manager = getFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fragment_container);
        if(fragment == null)
        {
            fragment = MainFragment.newInstance();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(R.id.fragment_container,fragment,"MAINFRAGMENT");
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
