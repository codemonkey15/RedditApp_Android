package com.example.dezapp.testdezapp;

import android.net.Uri;

public class ListItems{
    private String thumbnail;
    private String title;
    private String author;
    private String time;
    private int comment_cnt;
    private int score;

    public ListItems(String thumbnail, String title, String author, String created, int score, int comments){
        super();
        this.thumbnail = thumbnail;
        this.title = title;
        this.author = author;
        this.time = created;
        this.score = score;
        this.comment_cnt = comments;
    }

    public String getTitle(){
        return title;
    }

    public String getAuthor(){
        return author;
    }

    public String getTime(){
        return time;
    }

    public int getScore(){
        return score;
    }

    public int getComments(){
        return comment_cnt;
    }

    public Uri getImg() {
        return Uri.parse(thumbnail);
    }
}