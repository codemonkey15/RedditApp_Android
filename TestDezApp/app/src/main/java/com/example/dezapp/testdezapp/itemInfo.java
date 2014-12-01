package com.example.dezapp.testdezapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class itemInfo extends Activity{
    String title, author, img, date, comments, score;
    TextView titleTV, authorTV, dateTV, commentsTV, scoreTV;
    ImageView imgIV;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_info);

        titleTV = (TextView) findViewById(R.id.titleInfo);
        authorTV = (TextView) findViewById(R.id.authorInfo);
        imgIV = (ImageView) findViewById(R.id.icon1);
        dateTV = (TextView) findViewById(R.id.dateInfo);
        commentsTV = (TextView) findViewById(R.id.commentInfo);
        Bundle d = this.getIntent().getExtras();
        if(d!=null){
            title =  d.getString("title");
            author = d.getString("author");
            //img = d.getString("image");
            date = d.getString("date");
            comments = d.getString("comments");
            //score = d.getString("score");
            //t.setText(jsonText);
        }

        titleTV.setText(title);
        authorTV.setText(author);
        //imgIV.setImageURI(img);
        dateTV.setText(date);
        commentsTV.setText(comments);
    }
}