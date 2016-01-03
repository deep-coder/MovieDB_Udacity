package com.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.model.MovieDetails;
import com.model.MovieReviews;
import com.utils.Constants;
import com.utils.JsonNetworkManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jdeepak on 1/1/2016.
 */
public class MovieReviewsActivity extends AppCompatActivity {
    String TAG=MovieReviewsActivity.class.getSimpleName();
    @Bind(R.id.RecyclerReviewList)RecyclerView recyclerViewReview;
    List<MovieReviews> reviewList=new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_reviews_recycler_layout);
        Intent in=getIntent();
        Bundle bundle=in.getExtras();
        int movieID=(int)bundle.getInt("MovieID");
        final Toolbar toolbar = (Toolbar) findViewById(R.id.review_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);
        jsonRequestHandler(Constants.MOVIE_DB_REVIEWS_URL.replace("$",movieID+""));

    }

    public void jsonRequestHandler(String URL){
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                URL, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (reviewList.size() > 0) {
                                reviewList.clear();
                            }
                            JSONArray array = response.getJSONArray("results");
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject movieReviewsObject = array.getJSONObject(i);
                                MovieReviews movieReviews = new MovieReviews();
                                movieReviews.setId(movieReviewsObject.getString(Constants.KEY_MOVIE_REVIEW_ID));
                                movieReviews.setAuthor(movieReviewsObject.getString(Constants.KEY_MOVIE_REVIEW_AUTHOR));
                                movieReviews.setContent(movieReviewsObject.getString(Constants.KEY_MOVIE_REVIEW_CONTENT));
                                reviewList.add(movieReviews);
                            }
                            Log.d(TAG, response.toString());
                        } catch (Exception e) {

                        }
                        recyclerViewReview.setAdapter(new movieReviewsRecyclerAdapter(reviewList,R.layout.movie_reviews_design));
                        final LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        recyclerViewReview.setLayoutManager(layoutManager);
                        Log.d(TAG, response.toString());

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                // hide the progress dialog

            }
        });
        JsonNetworkManager.getInstance(getApplicationContext()).addToRequestQueue(jsonObjReq);

    }
}
