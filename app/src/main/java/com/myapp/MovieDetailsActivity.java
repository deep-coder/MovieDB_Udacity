package com.myapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.model.MovieDetails;
import com.squareup.picasso.Picasso;
import com.utils.Constants;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jdeepak on 12/13/2015.
 */
public class MovieDetailsActivity extends AppCompatActivity {
    @Bind(R.id.collapsing_toolbar)CollapsingToolbarLayout collapsingToolbar;
    int mutedColor = R.attr.colorPrimary;
    @Bind(R.id.plot_synopsis) TextView plot;
    @Bind(R.id.movieBackdropTitle)TextView movieTitle;
    @Bind(R.id.image_backdrop)ImageView header;
    @Bind(R.id.ratingGiven)TextView ratingGiven;
    @Bind(R.id.releaseDate)TextView releaseDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details_layout);
        ButterKnife.bind(this);
        MovieDetails movieDetails=(MovieDetails)getIntent().getParcelableExtra(MainActivity.PARCELABLE_KEY);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.anim_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbar.setTitle(movieDetails.getMovieTitle());
        ratingGiven.setText(movieDetails.getMovieRating()+"");
        releaseDate.setText(movieDetails.getMovieDate());
        movieTitle.setText(movieDetails.getMovieTitle());
        String URL= Constants.MOVIE_DB_IMAGE_BASE_URL_w500+movieDetails.getImageBackDrop();
        Picasso.with(getApplicationContext()).load(URL).into(header);
        plot.setText(movieDetails.getMoviePlot());
       /* Bitmap bitmap = BitmapFactory.decodeResource(getResources(),getResources().getDrawable(R.id.image_backdrop));

        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @SuppressWarnings("ResourceType")
            @Override
            public void onGenerated(Palette palette) {

                mutedColor = palette.getMutedColor(R.color.primary_500);
                collapsingToolbar.setContentScrimColor(mutedColor);
                collapsingToolbar.setStatusBarScrimColor(R.color.black_trans80);
            }
        });*/
        Log.i("Parcelable",movieDetails.getMovieTitle());
    }
}
