package com.deepcoder.movieapp.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.deepcoder.movieapp.activity.MovieDetailsActivity;
import com.deepcoder.movieapp.adapter.movieAdapter;
import com.deepcoder.movieapp.model.MovieDetails;
import com.deepcoder.movieapp.utils.Constants;
import com.deepcoder.movieapp.utils.JsonNetworkManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jdeepak on 12/16/2015.
 */
public class PopularMovieFragment extends BaseFragment implements AdapterView.OnItemClickListener {


    String TAG = "MainActivity";
    @Bind(R.id.movie_grid_list)
    GridView movieGridList;
    List<MovieDetails> movieDetailsList = new ArrayList<>();
    public final static String PARCELABLE_KEY = "com.myapp.parcelable";
    private boolean isTablet;

    public static PopularMovieFragment newInstance() {
        PopularMovieFragment popularMovieFragment = new PopularMovieFragment();
        return popularMovieFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);
        movieGridList.setDrawSelectorOnTop(true);
        movieGridList.setOnItemClickListener(this);
        jsonRequest(Constants.MOVIE_DB_SORT_POPULAR_URL);
        return rootView;
    }




    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    private void jsonRequest(String URL) {
        FragmentController fragmentController = new FragmentController();
        fragmentController.jsonRequest(URL, getContext(), new onTaskCompleted() {
            @Override
            public void onSuccess(Object object) {
                if (object != null) {
                    movieDetailsList.addAll((List<MovieDetails>) object);
                    movieGridList.setAdapter(new movieAdapter(getContext(), movieDetailsList));
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
