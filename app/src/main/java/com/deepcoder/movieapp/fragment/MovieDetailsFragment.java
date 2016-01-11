package com.deepcoder.movieapp.fragment;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.deepcoder.movieapp.activity.MainActivity;
import com.deepcoder.movieapp.activity.MovieReviewsActivity;
import com.deepcoder.movieapp.adapter.MovieTrailerRecyclerAdapter;
import com.deepcoder.movieapp.model.MovieDetails;
import com.deepcoder.movieapp.model.MovieTrailers;
import com.deepcoder.movieapp.utils.Constants;
import com.deepcoder.movieapp.utils.DateFormatter;
import com.deepcoder.movieapp.utils.JsonNetworkManager;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jdeepak on 1/10/2016.
 */
public class MovieDetailsFragment extends Fragment {
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
    MovieDetails movieDetails;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.movie_details_layout, container, false);
        ButterKnife.bind(this, rootView);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        boolean tabletSize = getResources().getBoolean(R.bool.isTablet);
        if(!tabletSize){
            movieDetails = (MovieDetails) activity.getIntent().getParcelableExtra(MainActivity.PARCELABLE_KEY);
        }
        else{
            Bundle bundle=getArguments();
            movieDetails = (MovieDetails) bundle.getParcelable(MainActivity.PARCELABLE_KEY);
        }

        final Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.anim_toolbar);
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbar.setTitle(movieDetails.getMovieTitle());
        ratingGiven.setText(movieDetails.getMovieRating() + "");
        releaseDate.setText(DateFormatter.formatDate(movieDetails.getMovieDate()));
        movieTitle.setText(movieDetails.getMovieTitle());
        String posterURL = Constants.MOVIE_DB_IMAGE_BASE_URL + movieDetails.getImageThumbnail();
        String URL = Constants.MOVIE_DB_IMAGE_BASE_URL_w500 + movieDetails.getImageBackDrop();
        Picasso.with(rootView.getContext()).load(posterURL).into(posterView);
        Picasso.with(rootView.getContext()).load(URL).into(header, new Callback() {
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
                Intent intent = new Intent(rootView.getContext(), MovieReviewsActivity.class);
                intent.putExtra("MovieID", movieDetails.getMovieID());
                startActivity(intent);
            }
        });
        return rootView;
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
                            final WrappingLinearLayoutManager layoutManager = new WrappingLinearLayoutManager(getActivity());
                            layoutManager.setOrientation(WrappingLinearLayoutManager.HORIZONTAL);

                            recyclerViewTrailerList.setLayoutManager(layoutManager);


                        }
                    }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {

                    // hide the progress dialog

                }
            });
            JsonNetworkManager.getInstance(getActivity()).addToRequestQueue(jsonObjReq);

        }

    }

    private void applyPalette(Palette palette) {
        collapsingToolbar.setContentScrimColor(palette.getMutedColor(getResources().getColor(R.color.colorPrimary)));
        collapsingToolbar.setStatusBarScrimColor(palette.getDarkMutedColor(getResources().getColor(R.color.colorPrimaryDark)));
    }
}
