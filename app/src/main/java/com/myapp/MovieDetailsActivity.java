package com.myapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.model.MovieDetails;
import com.model.MovieReviews;
import com.model.MovieTrailers;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.utils.Constants;
import com.utils.DateFormatter;
import com.utils.JsonNetworkManager;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jdeepak on 12/13/2015.
 */
public class MovieDetailsActivity extends AppCompatActivity {
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    int mutedColor = R.attr.colorPrimary;
    @Bind(R.id.plot_synopsis)
    TextView plot;
    @Bind(R.id.movieTitle)
    TextView movieTitle;
    @Bind(R.id.image_backdrop)
    ImageView header;
    @Bind(R.id.ratingGiven)
    TextView ratingGiven;
    @Bind(R.id.releaseDate)
    TextView releaseDate;
    @Bind(R.id.poster_details_activity)
    ImageView posterView;
    @Bind(R.id.btn_all_review)
    Button btnSeeReviews;
    @Bind(R.id.RecyclerTrailerList)
    RecyclerView recyclerViewTrailerList;
    List<MovieTrailers> movieTrailersList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details_layout);
        ButterKnife.bind(this);
        final MovieDetails movieDetails = (MovieDetails) getIntent().getParcelableExtra(MainActivity.PARCELABLE_KEY);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.anim_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbar.setTitle(movieDetails.getMovieTitle());
        ratingGiven.setText(movieDetails.getMovieRating() + "");
        releaseDate.setText(DateFormatter.formatDate(movieDetails.getMovieDate()));
        movieTitle.setText(movieDetails.getMovieTitle());
        String posterURL = Constants.MOVIE_DB_IMAGE_BASE_URL + movieDetails.getImageThumbnail();
        String URL = Constants.MOVIE_DB_IMAGE_BASE_URL_w500 + movieDetails.getImageBackDrop();
        Picasso.with(getApplicationContext()).load(posterURL).into(posterView);
        Picasso.with(getApplicationContext()).load(URL).into(header, new Callback() {
            @Override
            public void onSuccess() {
                Bitmap bitmap = ((BitmapDrawable) header.getDrawable()).getBitmap();
                Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                    public void onGenerated(Palette palette) {
                        applyPalette(palette);
                    }
                });
            }

            @Override
            public void onError() {

            }
        });
        plot.setText(movieDetails.getMoviePlot());

        jsonRequestTrailer(Constants.MOVIE_DB_TRAILERS_URL.replace("$",movieDetails.getMovieID()+""));
        btnSeeReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MovieReviewsActivity.class);
                intent.putExtra("MovieID", movieDetails.getMovieID());
                startActivity(intent);
            }
        });
    }

    private void jsonRequestTrailer(String URL) {
        {
            JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                    URL, null,
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if (movieTrailersList.size() > 0) {
                                    movieTrailersList.clear();
                                }
                                JSONArray array = response.getJSONArray("results");
                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject movieTrailersObject = array.getJSONObject(i);
                                    MovieTrailers movieTrailers = new MovieTrailers();
                                    movieTrailers.setKey(movieTrailersObject.getString(Constants.KEY_MOVIE_TRAILER_KEY));
                                    movieTrailers.setSite(movieTrailersObject.getString(Constants.KEY_MOVIE_TRAILER_SITE));
                                    movieTrailersList.add(movieTrailers);
                                }
                            } catch (Exception e) {

                            }
                            recyclerViewTrailerList.setNestedScrollingEnabled(false);
                            recyclerViewTrailerList.setHasFixedSize(false);
                            recyclerViewTrailerList.setAdapter(new MovieTrailerRecyclerAdapter(movieTrailersList, R.layout.movie_trailer_design));
                            final WrappingLinearLayoutManager layoutManager = new WrappingLinearLayoutManager(getApplicationContext());
                            layoutManager.setOrientation(WrappingLinearLayoutManager.HORIZONTAL);

                            recyclerViewTrailerList.setLayoutManager(layoutManager);


                        }
                    }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {

                    // hide the progress dialog

                }
            });
            JsonNetworkManager.getInstance(getApplicationContext()).addToRequestQueue(jsonObjReq);

        }

    }

    private void applyPalette(Palette palette) {
        collapsingToolbar.setContentScrimColor(palette.getMutedColor(getResources().getColor(R.color.colorPrimary)));
        collapsingToolbar.setStatusBarScrimColor(palette.getDarkMutedColor(getResources().getColor(R.color.colorPrimaryDark)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
