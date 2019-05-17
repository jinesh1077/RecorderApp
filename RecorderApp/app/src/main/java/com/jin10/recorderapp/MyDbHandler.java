package com.jin10.recorderapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "SongDatabase.db";
    private static final String TABLE_NAME = "TagTable";
    private static final int DATABASE_VERSION = 1;
    private static final String COL_ID = "_id" ;
    private static final String COL_Song = "_song" ;
    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
            " ("+COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ COL_Song+ " TEXT);";
    private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
    private Context c;
    private int i;

    public MyDbHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        c = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public void addData(String s){


        ContentValues values = new ContentValues();
        values.put(COL_Song,s);
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME,null,values);
        db.close();
    }


    public String[] getVal(){

        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME ;

        Cursor cursor =db.rawQuery(query,null);
        String[] str = new String[cursor.getCount()];
        i=0;
        cursor.moveToFirst();
        try{

            while (!cursor.isAfterLast())
            {

                    str[i] =  cursor.getString(cursor.getColumnIndex("_song"));

                    i++;


                cursor.moveToNext();
            }
        }
        catch (Exception e){

        }
        db.close();
        return str;
    }


}
