package com.utils;

/**
 * Created by jdeepak on 12/8/2015.
 */
public class Constants {
    public static final String MOVIE_DB_API_KEY = "YOUR API KEY";
    public static final String MOVIE_DB_IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w185";
    public static final String MOVIE_DB_IMAGE_BASE_URL_w500 = "http://image.tmdb.org/t/p/w500";
    public static final String MOVIE_DB_SORT_POPULAR_URL = "http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key="+MOVIE_DB_API_KEY;
    public static final String MOVIE_DB_SORT_VOTE_AVERAGE_URL = "http://api.themoviedb.org/3/discover/movie?sort_by=vote_average.desc&api_key="+MOVIE_DB_API_KEY;

    public static  final String PREFERENCES_SORTBY="SortBy";
    public static final String PREFERENCES_SORTBY_POPULAR="Popular";
    public static final String PREFERENCES_SORTBY_VOTE="Vote";
    /*API Constants*/
    public static final String
            KEY_ADULT = "adult",
            KEY_BACKDROP_PATH = "backdrop_path",
            KEY_GENRE_ID = "genre_ids",
            KEY_ID = "id",
            KEY_ORIGINAL_LANGUAGE = "original_langauge",
            KEY_ORIGINAL_TITLE = "original_title",
            KEY_OVERVIEW = "overview",
            KEY_RELEASE_DATE = "release_date",
            KEY_POSTER_PATH = "poster_path",
            KEY_POPULARITY = "popularity",
            KEY_TITLE = "title",
            KEY_VIDEO = "video",
            KEY_VOTE_AVEARGE = "vote_average",
            KEY_VOTE_COUNT = "vote_count";


}
