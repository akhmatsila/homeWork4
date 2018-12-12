package com.example.indalamar.scp_1488;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.Button;



public class ItemDetailActivity extends AppCompatActivity {

    private DBHelper dbHelper ;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        addButton = (Button) findViewById(R.id.favouriteAdd);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own detail action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        dbHelper = new DBHelper(this);
        new checkAdd().execute(getIntent().getStringExtra("url"));

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putString("id",
                    getIntent().getStringExtra("id"));
            arguments.putString("url", getIntent().getStringExtra("url"));
            ItemDetailFragment fragment = new ItemDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.item_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpTo(this, new Intent(this, ItemListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void makeFavourite(View view) {
        Button button = (Button) view;
        if (button.getText().equals("ADDED"))
            return ;
        button.setText("ADDED");
        new UploadTask().execute(getIntent().getStringExtra("text"),getIntent().getStringExtra("url"));
    }


    private class checkAdd extends  AsyncTask <String , Void , Void > {

        @Override
        protected Void doInBackground(String... strings) {
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM DB WHERE url = '"+ strings[0]+"'" , null);
            if (cursor.moveToFirst()) {
                db.close();
                addButton.setText("ADDED");
            }
            return null;
        }
    }

    private class UploadTask extends AsyncTask<String , Void , Void> {

        @Override
        protected Void doInBackground(String... strings) {
            SQLiteDatabase db  = dbHelper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("description",strings[0]);
            cv.put("url",strings[1]);
            db.insert("DB",null , cv);
            return null;
        }
    }
}