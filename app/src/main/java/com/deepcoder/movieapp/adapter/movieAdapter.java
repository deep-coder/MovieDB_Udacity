package com.deepcoder.movieapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.deepcoder.movieapp.model.MovieDetails;
import com.deepcoder.movieapp.fragment.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.deepcoder.movieapp.utils.Constants;

import java.util.List;

/**
 * Created by jdeepak on 11/22/2015.
 */
public class MovieAdapter extends BaseAdapter  {
    private Context mContext;
    private List<MovieDetails> movieDetailsList;


    public MovieAdapter(Context c, List<MovieDetails> movieDetailsList) {
        mContext = c;
        this.movieDetailsList = movieDetailsList;
    }

    @Override
    public int getCount() {

        return movieDetailsList.size();
    }

    @Override
    public Object getItem(int position) {
        return movieDetailsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final MovieDetails currentMovieDetails = (MovieDetails) getItem(position);
        View row = convertView;
        final ViewHolder holder ;
        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(R.layout.grid_item_layout, parent, false);
            holder = new ViewHolder();
            holder.image = (ImageView) row.findViewById(R.id.poster);
            holder.ratingText = (TextView) row.findViewById(R.id.rating_text_MainView);
            holder.movieName=(TextView) row.findViewById(R.id.movie_name);
            holder.linearLayout=(LinearLayout)row.findViewById(R.id.layout_content);
            //holder.favouriteIcon = (ImageView) row.findViewById(R.id.favourite_MainView);
            /*holder.favouriteIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos=(Integer)v.getTag();
                    ((MovieDetails) getItem(pos)).setIsFavourite(true);
                    //notifyDataSetChanged();
                    Log.i("Position",pos+"");
                    //holder.favouriteIcon.setImageResource(R.drawable.ic_favourite);
                }
            });*/
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        String URL = Constants.MOVIE_DB_IMAGE_BASE_URL + currentMovieDetails.getImageThumbnail();
        Picasso.with(mContext).load(URL).into(holder.image);
        Picasso.with(mContext).load(URL).into(holder.image, new Callback() {
            @Override
            public void onSuccess() {

                   /* Bitmap bitmap = ((BitmapDrawable) holder.image.getDrawable()).getBitmap();
                    Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                        public void onGenerated(Palette palette) {
                            //collapsingToolbar.setStatusBarScrimColor(palette.getDarkMutedColor(getResources().getColor(R.color.colorPrimaryDark)));
                            Palette.Swatch vibrant = palette.getLightVibrantSwatch();
                            if (vibrant != null) {
                                // Set the background color of a layout based on the vibrant color
                                //holder.linearLayout.setBackgroundColor(vibrant.getRgb());

                            }

                        }
                    });*/

            }

            @Override
            public void onError() {

            }
        });
        holder.movieName.setText(currentMovieDetails.getMovieOriginalTitle());
        holder.ratingText.setText(currentMovieDetails.getMovieRating() + "");
        //holder.favouriteIcon.setTag(position);
        /*if(currentMovieDetails.isFavourite()){
            Log.i("Position",position+"");
            holder.favouriteIcon.setImageResource(R.drawable.ic_favourite);
           //notifyDataSetChanged();
        }*/

        return row;
    }



    static class ViewHolder {
        LinearLayout linearLayout;
        ImageView image;
        TextView ratingText;
        TextView movieName;
        //ImageView favouriteIcon;

    }
}
