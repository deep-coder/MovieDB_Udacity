package com.deepcoder.movieapp.fragment;

import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Spinner;

import com.deepcoder.movieapp.activity.MovieDetailsActivity;
import com.deepcoder.movieapp.adapter.movieAdapter;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.deepcoder.movieapp.model.MovieDetails;
import com.deepcoder.movieapp.utils.AssortedUtils;
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
public class MainFragment extends Fragment {


    String TAG = "MainActivity";
    @Bind(R.id.movie_grid_list)
    GridView movieGridList;
    @Bind(R.id.spinnerSortBy)
    Spinner SortBy;
    List<MovieDetails> movieDetailsList = new ArrayList<>();
    public final static String PARCELABLE_KEY = "com.myapp.parcelable";
    private boolean isTablet;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);
        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        if (AssortedUtils.getPreferences(Constants.PREFERENCES_SORTBY, getActivity()) != null) {
            if (AssortedUtils.getPreferences(Constants.PREFERENCES_SORTBY, getActivity()).equals(Constants.PREFERENCES_SORTBY_POPULAR)) {
                jsonRequest(Constants.MOVIE_DB_SORT_POPULAR_URL);
                SortBy.setSelection(0);
            } else {
                jsonRequest(Constants.MOVIE_DB_SORT_VOTE_AVERAGE_URL);
                SortBy.setSelection(1);
            }
        } else {
            jsonRequest(Constants.MOVIE_DB_SORT_POPULAR_URL);
            SortBy.setSelection(0);
        }

        // movieGridList.setAdapter(new movieAdapter(MainActivity.this, movieDetailsList));
        movieGridList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                boolean tabletSize = getResources().getBoolean(R.bool.isTablet);
                if (!tabletSize) {
                    Intent intent = new Intent(rootView.getContext(), MovieDetailsActivity.class);
                    Bundle mBundle = new Bundle();
                    mBundle.putParcelable(PARCELABLE_KEY, movieDetailsList.get(position));
                    intent.putExtras(mBundle);
                    startActivity(intent);
                } else {
                    Bundle arguments = new Bundle();
                    arguments.putParcelable(MainFragment.PARCELABLE_KEY, movieDetailsList.get(position));
                    MovieDetailsFragment fragment = new MovieDetailsFragment();
                    fragment.setArguments(arguments);
                    getFragmentManager().beginTransaction()
                            .replace(R.id.movie_detail_container, fragment)
                            .commit();
                }
            }
        });

        SortBy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        jsonRequest(Constants.MOVIE_DB_SORT_POPULAR_URL);
                        AssortedUtils.SavePreferences(Constants.PREFERENCES_SORTBY, Constants.PREFERENCES_SORTBY_POPULAR, getActivity());
                        break;
                    case 1:
                        jsonRequest(Constants.MOVIE_DB_SORT_VOTE_AVERAGE_URL);
                        AssortedUtils.SavePreferences(Constants.PREFERENCES_SORTBY, Constants.PREFERENCES_SORTBY_VOTE, getActivity());
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                URL, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (movieDetailsList.size() > 0) {
                                movieDetailsList.clear();
                            }
                            JSONArray array = response.getJSONArray("results");
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject movieDetailsObject = array.getJSONObject(i);
                                MovieDetails movieDetails = new MovieDetails();
                                movieDetails.setMovieID(movieDetailsObject.getInt(Constants.KEY_ID));
                                movieDetails.setImageThumbnail(movieDetailsObject.getString(Constants.KEY_POSTER_PATH));
                                movieDetails.setMovieDate(movieDetailsObject.getString(Constants.KEY_RELEASE_DATE));
                                movieDetails.setMoviePlot(movieDetailsObject.getString(Constants.KEY_OVERVIEW));
                                movieDetails.setMovieRating(movieDetailsObject.getDouble(Constants.KEY_VOTE_AVEARGE));
                                movieDetails.setMovieTitle(movieDetailsObject.getString(Constants.KEY_ORIGINAL_TITLE));
                                movieDetails.setImageBackDrop(movieDetailsObject.getString(Constants.KEY_BACKDROP_PATH));
                                movieDetailsList.add(movieDetails);
                                Log.i("Object", movieDetailsObject.getString("original_title"));
                            }
                            Log.d(TAG, response.toString());

                        } catch (Exception e) {

                        }
                        movieGridList.setAdapter(new movieAdapter(getActivity(), movieDetailsList));
                        progressDialog.hide();

                        Log.d(TAG, response.toString());

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                progressDialog.hide();
                // hide the progress dialog

            }
        });
        JsonNetworkManager.getInstance(getActivity()).addToRequestQueue(jsonObjReq);

    }

}
