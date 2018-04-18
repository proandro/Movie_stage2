package com.example.lucky.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class favourite_movie extends AppCompatActivity implements MyAdapter_for_favourite.OnItemClickListener {
    public static final String EXTRA_URL = "imageurl";
    public static final String EXTRA_URL_BACK = "imageurl2";
    public static final String EXTRA_Title = "movietitle";
    public static final String EXTRA_DESC = "description";
    public static final String EXTRA_RATING = "rating";
    public static final String EXTRA_DATE = "date";
    public static final String EXTRA_ID = "id_of_movies";
    public RecyclerView recyclerView2;
    public MyAdapter_for_favourite adapter2;
    public List<ListItem> listItems;
    public int total_item = 0;
    TextView textView;
    database mydb_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_movie);
        recyclerView2 = (RecyclerView) findViewById(R.id.rv_number_for_fav_movies);
        textView = (TextView) findViewById(R.id.favourite_movies);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new GridLayoutManager(this, 3));
        listItems = new ArrayList<>();
        mydb_result = new database(this);
        set_favourite_movie();
    }

    private void set_favourite_movie() {
        try {
            Cursor res = mydb_result.getAllData();
            if (res.getCount() == 0) {
                textView.setText("Nothing found");
                return;
            }
            while (res.moveToNext()) {
                String id_of_movie = res.getString(0);
                String title = res.getString(1);
                String rating_of_movie = res.getString(2);
                String release_date = res.getString(3);
                String overv = res.getString(4);
                String img_URL = res.getString(5);
                String backurl = res.getString(6);
                total_item++;
                ListItem item = new ListItem(title, release_date, img_URL, backurl, overv, rating_of_movie, id_of_movie);
                listItems.add(item);
            }
            Toast.makeText(getApplicationContext(), "Total Results: " + total_item, Toast.LENGTH_SHORT).show();
            adapter2 = new MyAdapter_for_favourite(listItems, getApplicationContext());
            recyclerView2.setAdapter(adapter2);
            adapter2.setOnItemClickListener(favourite_movie.this);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "error occur", Toast.LENGTH_SHORT).show();
        }
    }

    public void onItemClick(int position) {
        Intent intent = new Intent(favourite_movie.this, DetailActivity.class);
        ListItem itemclicked = listItems.get(position);
        intent.putExtra(EXTRA_URL, itemclicked.getImage());
        intent.putExtra(EXTRA_DESC, itemclicked.getDesc());
        intent.putExtra(EXTRA_Title, itemclicked.getHead());
        intent.putExtra(EXTRA_URL_BACK, itemclicked.getBackurl());
        intent.putExtra(EXTRA_RATING, itemclicked.getRating());
        intent.putExtra(EXTRA_DATE, itemclicked.getrelase_date());
        intent.putExtra(EXTRA_ID, itemclicked.getId());
        startActivity(intent);
    }
}
