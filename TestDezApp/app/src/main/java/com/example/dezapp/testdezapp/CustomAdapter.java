package com.example.dezapp.testdezapp;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dezapp.testdezapp.ListItems;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<ListItems>{

    private final Context context;
    private final ArrayList<ListItems> itemsArrayList;

    public CustomAdapter(Context context, ArrayList<ListItems> itemsArrayList) {

        super(context, R.layout.result_object, itemsArrayList);

        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.result_object, parent, false);

        // 3. Get the two text view from the rowView
        TextView title = (TextView) rowView.findViewById(R.id.titleTextView);
        TextView author = (TextView) rowView.findViewById(R.id.authorTextView);
        TextView time = (TextView) rowView.findViewById(R.id.timeTextView);
        TextView score = (TextView) rowView.findViewById(R.id.scoreTextView);
        ImageView img = (ImageView) rowView.findViewById(R.id.icon);

        title.setText(itemsArrayList.get(position).getTitle());
        author.setText(itemsArrayList.get(position).getAuthor());
        time.setText(itemsArrayList.get(position).getTime());
        score.setText(String.valueOf(itemsArrayList.get(position).getScore()));
        //img.setImageURI(new ImageDownloader(img).execute(itemsArrayList.get(position).getImg()));
        String uri = itemsArrayList.get(position).getImg().toString();
        //if(!uri.equalsIgnoreCase("self")&&!uri.equalsIgnoreCase("default")&&) {
        if(uri.length() > 4 && uri.substring(0,3).equals("http")){
            new ImageDownloader(img).execute(uri);
        }
        // 5. retrn rowView
        return rowView;
    }

    public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public ImageDownloader(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {

            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
