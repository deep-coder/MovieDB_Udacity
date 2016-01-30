package com.deepcoder.movieapp.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.deepcoder.movieapp.activity.MovieDetailsActivity;
import com.deepcoder.movieapp.adapter.movieAdapter;
import com.deepcoder.movieapp.data.MovieDBContract;
import com.deepcoder.movieapp.model.MovieDetails;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jdeepak on 1/24/2016.
 */
public abstract class BaseFragment extends Fragment implements AdapterView.OnItemClickListener{

    @Bind(R.id.movie_grid_list)
    GridView movieGridList;
    List<MovieDetails> movieDetailsList = null;
    public final static String PARCELABLE_KEY = "com.myapp.parcelable";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);
        movieGridList.setDrawSelectorOnTop(true);
        movieGridList.setOnItemClickListener(this);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        movieDetailsList = new ArrayList<>();
    }



    protected void showProgressBar(){

    }

    protected void jsonRequest(String URL) {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.show();
        FragmentController fragmentController = new FragmentController();
        fragmentController.jsonRequest(URL, getContext(), new onTaskCompleted() {
            @Override
            public void onSuccess(Object object) {
                if (object != null) {
                    movieDetailsList.addAll((List<MovieDetails>) object);
                    movieGridList.setAdapter(new movieAdapter(getContext(), movieDetailsList));
                    progressDialog.hide();
                }
            }
        });
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        boolean tabletSize = getResources().getBoolean(R.bool.isTablet);
        movieGridList.setItemChecked(position, true);
        if (!tabletSize) {
            Intent intent = new Intent(view.getContext(), MovieDetailsActivity.class);
            Bundle mBundle = new Bundle();
            mBundle.putParcelable(PARCELABLE_KEY, movieDetailsList.get(position));
            intent.putExtras(mBundle);
            startActivity(intent);
        } else {
            Bundle arguments = new Bundle();
            arguments.putParcelable(PopularMovieFragment.PARCELABLE_KEY, movieDetailsList.get(position));
            MovieDetailsFragment fragment = new MovieDetailsFragment();
            fragment.setArguments(arguments);
            getFragmentManager().beginTransaction()
                    .replace(R.id.movie_detail_container, fragment)
                    .commit();
        }
    }
}
