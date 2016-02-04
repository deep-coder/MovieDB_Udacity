package com.deepcoder.movieapp.utils;

/**
 * Created by jdeepak on 12/8/2015.
 */
public class Constants {
    public final static String PARCELABLE_KEY = "com.myapp.parcelable";
    public static final String MOVIE_DB_API_KEY = "API KEY";
    public static final String MOVIE_DB_IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w185";
    public static final String MOVIE_DB_IMAGE_BASE_URL_w500 = "http://image.tmdb.org/t/p/w500";
    public static final String MOVIE_DB_SORT_BASE_URL="http://api.themoviedb.org/3/discover/movie";
    public static final String MOVIE_DB_SORT_POPULAR_URL = "popularity.desc";
    public static final String MOVIE_DB_SORT_VOTE_AVERAGE_URL = "vote_average.desc";
    public static final String MOVIE_DB_REVIEWS_URL = "http://api.themoviedb.org/3/movie/$/reviews?api_key=" + MOVIE_DB_API_KEY;
    public static final String MOVIE_DB_TRAILERS_URL = "http://api.themoviedb.org/3/movie/$/videos?api_key=" + MOVIE_DB_API_KEY;
    public static final String PREFERENCES_SORTBY = "SortBy";
    public static final String PREFERENCES_SORTBY_POPULAR = "Popular";
    public static final String PREFERENCES_SORTBY_VOTE = "Vote";
    public static final String YOUTUBE_TRAILER_THUMBNAIL="http://img.youtube.com/vi/$/0.jpg";
    /*API Constants*/
    public static final String
            KEY_ADULT = "adult",
            KEY_BACKDROP_PATH = "backdrop_path",
            KEY_GENRE_ID = "genre_ids",
            KEY_ID = "id",
            KEY_ORIGINAL_LANGUAGE = "original_language",
            KEY_ORIGINAL_TITLE = "original_title",
            KEY_OVERVIEW = "overview",
            KEY_RELEASE_DATE = "release_date",
            KEY_POSTER_PATH = "poster_path",
            KEY_POPULARITY = "popularity",
            KEY_TITLE = "title",
            KEY_VIDEO = "video",
            KEY_VOTE_AVEARGE = "vote_average",
            KEY_VOTE_COUNT = "vote_count";
    /*For Movie Reviews*/
    public static final String
            KEY_MOVIE_REVIEW_ID = "id",
            KEY_MOVIE_REVIEW_AUTHOR = "author",
            KEY_MOVIE_REVIEW_CONTENT = "content";

    /*For Movie Trailer"*/

    public static final String
            KEY_MOVIE_TRAILER_ID = "id",
            KEY_MOVIE_TRAILER_ISO = "iso_639_1",
            KEY_MOVIE_TRAILER_KEY = "key",
            KEY_MOVIE_TRAILER_name = "name",
            KEY_MOVIE_TRAILER_SITE = "site",
            KEY_MOVIE_TRAILER_SIZE = "size",
            KEY_MOVIE_TRAILER_TYPE = "type";
    public static final String
            MONTH_JANUARY = "January",
            MONTH_FEBRUARY = "February",
            MONTH_MARCH = "March",
            MONTH_APRIL = "April",
            MONTH_MAY = "May",
            MONTH_JUNE = "June",
            MONTH_JULY = "July",
            MONTH_AUGUST = "August",
            MONTH_SEPTEMBER = "September",
            MONTH_OCTOBER = "October",
            MONTH_NOVEMBER = "November",
            MONTH_DECEMBER = "December";


}
