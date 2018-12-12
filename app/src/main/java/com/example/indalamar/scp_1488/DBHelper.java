package com.example.indalamar.scp_1488;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper (Context context) {
        super(context , "DB" , null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table DB ("
                + "id integer primary key autoincrement,"
                + "description text,"
                + "url text" + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
