package com.example.indalamar.scp_1488;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class FavouriteOnesList extends AppCompatActivity {
    private ArrayList<RecyclerViewItem> mItems = new ArrayList<>();
    private RecyclerView recyclerView ;
    private FavouriteOnesAdapter adapter;

    protected void onCreate(Bundle saveOnInstanceInstate) {
        super.onCreate(saveOnInstanceInstate);
        setContentView(R.layout.favourite_ones_list);

        mItems.clear();

        recyclerView = (RecyclerView) findViewById(R.id.favourite_ones);
        adapter = new FavouriteOnesAdapter(this , mItems);
        recyclerView.setAdapter(adapter);

        new loadAllFavouriteOnes().execute(this);

    }
    private class loadAllFavouriteOnes extends AsyncTask <FavouriteOnesList , Void , Void> {

        @Override
        protected Void doInBackground(FavouriteOnesList... favouriteOnesLists) {
            DBHelper dbHelper = new DBHelper(favouriteOnesLists[0]);;
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.query("DB" , null , null , null  , null   , null
                    ,null   );
            if (cursor.moveToFirst()) {
                if (cursor!= null && cursor.moveToFirst())
                do {
                    int idColIndex = cursor.getColumnIndex("id");
                    int textColIndex = cursor.getColumnIndex("description");
                    int urlColIndex = cursor.getColumnIndex("url") ;
                    String url = cursor.getString(urlColIndex);
                    String text = cursor.getString(textColIndex);
                    int id = cursor.getInt(idColIndex);
                    mItems.add(new RecyclerViewItem(text, url, id));
                }while (cursor.moveToNext());
            }
            if (mItems.size()!=0) adapter.updateData();
            return null;
        }
    }
}
