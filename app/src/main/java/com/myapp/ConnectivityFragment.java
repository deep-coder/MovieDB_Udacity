package com.myapp;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.utils.CheckConnectivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jdeepak on 12/19/2015.
 */
public class ConnectivityFragment extends Fragment {


    @Bind(R.id.ConnectivityText)TextView connectivityText;
    @Bind(R.id.refresh_main_page)Button refresh_page;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.activity_display_no_connectivity, container, false);
        final ConnectivityFragment connectivityFragment=this;
        ButterKnife.bind(this,rootView);
        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        refresh_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                if (new CheckConnectivity(rootView.getContext()).isConnectedToInternet()) {

                    ft.replace(R.id.fragment, new MainFragmentActivity());

                }
                else{
                    ft.replace(R.id.fragment,connectivityFragment );
                }
                ft.commit();
            }
        });
        return  rootView;
    }
}
