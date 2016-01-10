package com.deepcoder.movieapp.model;

/**
 * Created by jdeepak on 12/29/2015.
 */
public class MovieReviews {

    public MovieReviews(String id, String author, String content) {
        this.id = id;
        this.author = author;
        this.content = content;

    }
    public MovieReviews(){

    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String id;
    private String author;
    private String content;
}
