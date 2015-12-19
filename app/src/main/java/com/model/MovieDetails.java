package com.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by jdeepak on 12/7/2015.
 */
public class MovieDetails implements Parcelable{
    private String movieTitle;
    private int movieRating;
    private String moviePlot;
    private String movieDate;
    private String imageThumbnail;
    private String imageBackDrop;

    public String getImageBackDrop() {
        return imageBackDrop;
    }

    public void setImageBackDrop(String imageBackDrop) {
        this.imageBackDrop = imageBackDrop;
    }



    public MovieDetails(String movieTitle,int movieRating,String moviePlot,String movieDate,String imageThumbnail,String imageBackDrop){
        this.movieTitle=movieTitle;
        this.movieRating=movieRating;
        this.moviePlot=moviePlot;
        this.movieDate=movieDate;
        this.imageThumbnail=imageThumbnail;
        this.imageBackDrop=imageBackDrop;

    }

    public MovieDetails(){

    }
    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public int getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(int movieRating) {
        this.movieRating = movieRating;
    }

    public String getMoviePlot() {
        return moviePlot;
    }

    public void setMoviePlot(String moviePlot) {
        this.moviePlot = moviePlot;
    }

    public String getMovieDate() {
        return movieDate;
    }

    public void setMovieDate(String movieDate) {
        this.movieDate = movieDate;
    }

    public String getImageThumbnail() {
        return imageThumbnail;
    }

    public void setImageThumbnail(String imageThumbnail) {
        this.imageThumbnail = imageThumbnail;
    }

    public static final Parcelable.Creator<MovieDetails> CREATOR = new Creator<MovieDetails>() {
        public MovieDetails createFromParcel(Parcel source) {
            MovieDetails movieDetails=new MovieDetails();
            movieDetails.moviePlot=source.readString();
            movieDetails.imageThumbnail=source.readString();
            movieDetails.movieTitle=source.readString();
            movieDetails.movieDate=source.readString();
            movieDetails.movieRating=source.readInt();
            movieDetails.imageBackDrop=source.readString();
            return movieDetails;
        }
        public MovieDetails[] newArray(int size) {
            return new MovieDetails[size];

        }

    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(moviePlot);
        dest.writeString(imageThumbnail);
        dest.writeString(movieTitle);
        dest.writeString(movieDate);
        dest.writeInt(movieRating);
        dest.writeString(imageBackDrop);

    }
}
