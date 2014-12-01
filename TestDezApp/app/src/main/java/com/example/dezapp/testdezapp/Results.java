package com.example.dezapp.testdezapp;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Results extends Activity{
    ArrayList<ListItems> items;
    String jsonText;
    JSONObject jsonData;
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_layout);

        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            jsonText =  extras.getString("response");
            //t.setText(jsonText);
        }

        try {
            //t.setText(jsonText);
            jsonData = new JSONObject(jsonText);
            //t.setText(jsonData.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        CustomAdapter ca = null;
        try {
            ca = new CustomAdapter(this, generateData());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListView listview = (ListView) findViewById(R.id.listview);

        listview.setAdapter(ca);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Results.this, itemInfo.class);
                ListItems lt = items.get(position);
                Bundle d = new Bundle();
                d.putString("title",lt.getTitle());
                d.putString("author",lt.getAuthor());
                d.putString("date",lt.getTime());
                d.putString("comments",String.valueOf(lt.getComments()));
                d.putString("score",String.valueOf(lt.getScore()));
                d.putString("image",String.valueOf(lt.getImg()));
                intent.putExtras(d);
                startActivity(intent);
            }
        });

    }

    public ArrayList<ListItems> generateData() throws JSONException {
        items = new ArrayList<ListItems>();
        JSONObject jObj = null;
        String thumbnail, title, author, time, score, comments;
        JSONObject temp = jsonData.getJSONObject("data");
        JSONArray jsonArray = temp.getJSONArray("children");
        for (int i = 0; i < jsonArray.length(); i++) {
            jObj = jsonArray.getJSONObject(i).getJSONObject("data");
            thumbnail = jObj.getString("thumbnail");
            title = jObj.getString("title");
            author = jObj.getString("author");
            time = jObj.getString("created");

            Date d = new Date((long) Double.parseDouble(time));
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date = dateFormat.format(d);

            score = jObj.getString("score");
            comments = jObj.getString("num_comments");
            if(comments.equals(null)){comments = "0";}
            items.add(new ListItems(thumbnail, title, author, date, Integer.parseInt(score), Integer.parseInt(comments)));
         }
        //items.add(new ListItems("some image","Title1","Author1","time",70,4));
        //items.add(new ListItems("some image2","Title2","Author2","Time2",50,6));
        return items;
    }
}