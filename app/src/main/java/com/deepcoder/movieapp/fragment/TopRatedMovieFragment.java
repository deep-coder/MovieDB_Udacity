package com.deepcoder.movieapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.deepcoder.movieapp.activity.MovieDetailsActivity;
import com.deepcoder.movieapp.adapter.movieAdapter;
import com.deepcoder.movieapp.model.MovieDetails;
import com.deepcoder.movieapp.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jdeepak on 1/13/2016.
 */
public class TopRatedMovieFragment extends Fragment implements AdapterView.OnItemClickListener {


    String TAG = "MainActivity";
    @Bind(R.id.movie_grid_list)
    GridView movieGridList;
    List<MovieDetails> movieDetailsList = new ArrayList<>();
    public final static String PARCELABLE_KEY = "com.myapp.parcelable";
    private boolean isTablet;

    public static TopRatedMovieFragment newInstance(){
        TopRatedMovieFragment topRatedMovieFragment=new TopRatedMovieFragment();
        return topRatedMovieFragment;
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
        jsonRequest(Constants.MOVIE_DB_SORT_VOTE_AVERAGE_URL);
        movieGridList.setOnItemClickListener(this);
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

