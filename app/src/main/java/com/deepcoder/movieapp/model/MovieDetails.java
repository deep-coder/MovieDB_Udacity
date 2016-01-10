package com.deepcoder.movieapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jdeepak on 12/7/2015.
 */
public class MovieDetails implements Parcelable{


    private int movieID;
    private String movieTitle;
    private double movieRating;
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
    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }
    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public double getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(double movieRating) {
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
            movieDetails.movieID=source.readInt();
            movieDetails.moviePlot=source.readString();
            movieDetails.imageThumbnail=source.readString();
            movieDetails.movieTitle=source.readString();
            movieDetails.movieDate=source.readString();
            movieDetails.movieRating=source.readDouble();
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
        dest.writeInt(movieID);
        dest.writeString(moviePlot);
        dest.writeString(imageThumbnail);
        dest.writeString(movieTitle);
        dest.writeString(movieDate);
        dest.writeDouble(movieRating);
        dest.writeString(imageBackDrop);

    }
}
