package com.deepcoder.movieapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.deepcoder.movieapp.model.MovieDetails;
import com.deepcoder.movieapp.fragment.R;
import com.squareup.picasso.Picasso;
import com.deepcoder.movieapp.utils.Constants;

import java.util.List;

/**
 * Created by jdeepak on 11/22/2015.
 */
public class movieAdapter extends BaseAdapter {
    private Context mContext;
    private List<MovieDetails> movieDetailsList;

    public movieAdapter(Context c, List<MovieDetails> movieDetailsList) {
        mContext = c;
        this.movieDetailsList=movieDetailsList;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        MovieDetails currentMovieDetails=(MovieDetails)getItem(position);
        View row = convertView;
        ViewHolder holder = null;
        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(R.layout.grid_item_layout, parent, false);
            holder = new ViewHolder();
            holder.image = (ImageView) row.findViewById(R.id.poster);
            holder.ratingText=(TextView)row.findViewById(R.id.rating_text_MainView);

            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }
        String URL= Constants.MOVIE_DB_IMAGE_BASE_URL+currentMovieDetails.getImageThumbnail();
        Picasso.with(mContext).load(URL).into(holder.image);
        holder.ratingText.setText(currentMovieDetails.getMovieRating()+"");
        return row;
    }

    static class ViewHolder {
        ImageView image;
        TextView ratingText;
    }
}
