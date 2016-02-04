package com.deepcoder.movieapp.utils;

import android.net.Uri;

import com.deepcoder.movieapp.model.MovieTrailers;

/**
 * Created by jdeepak on 1/31/2016.
 */
public class URIBuilder {

    public String buildURI(String queryType,String queryPageNumber){
        Uri.Builder builder=null;
        builder=Uri.parse(Constants.MOVIE_DB_SORT_BASE_URL).buildUpon()
                .appendQueryParameter("api_key", Constants.MOVIE_DB_API_KEY)
                .appendQueryParameter("page", queryPageNumber)
                .appendQueryParameter("sort_by",queryType);
        Uri uri=builder.build();
        return uri.toString();
    }

    public String buildURI(String queryType){
        Uri.Builder builder=null;
        builder=Uri.parse(Constants.MOVIE_DB_SORT_BASE_URL).buildUpon()
                .appendQueryParameter("api_key", Constants.MOVIE_DB_API_KEY)
                .appendQueryParameter("sort_by",queryType);
        Uri uri=builder.build();
        return uri.toString();
    }

    public String buildTrailerURL(MovieTrailers trailersItem){
        String key = trailersItem.getKey();
        String site = trailersItem.getSite();
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http")
                .authority("www.youtube.com")
                .appendPath("watch")
                .appendQueryParameter("v", key);
        String videoUrl = builder.build().toString();
        return videoUrl;
    }

}
