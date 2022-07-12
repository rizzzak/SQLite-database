package com.example.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "myDB", null, 1); //myDB – имя базы данных
    }
    public void onCreate(SQLiteDatabase db) {
        Log.d("Ivan", "--- onCreate database ---"); //создадим новую таблицу в базе
        db.execSQL("create table mytable ("
                + "id integer primary key autoincrement,"
                + "name text,"
                + "email text" + ");");
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS mytable"); //перезапишем старую таблицу
        db.execSQL("create table mytable ("
                + "id integer primary key autoincrement,"
                + "name text,"
                + "email text" + ");");
    }
}
