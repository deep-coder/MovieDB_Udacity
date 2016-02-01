package com.deepcoder.movieapp.utils;

import android.net.Uri;

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

}
