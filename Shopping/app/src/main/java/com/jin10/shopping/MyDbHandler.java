package com.jin10.shopping;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLDataException;
import java.util.ArrayList;

public class MyDbHandler extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "ShopDatabase.db";
    private static final String TABLE_NAME = "Fruit";
    private static final String TABLE_NAME2 = "Vegetable";
    private static final String TABLE_NAME3 = "FastFood";
    private static final String TABLE_NAME4 = "Cart";
    private static final int DATABASE_VERSION = 1;
    private static final String COL_ID = "_id" ;
    private static final String COL_NAME = "_name" ;
    private static final String COL_Price = "_price" ;
    private static final String COL_Detail = "_detail" ;
    private static final String COL_Quantity = "_qaunt" ;
    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
            " ("+COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ COL_NAME+ " TEXT, "+COL_Price+ " Text, " + COL_Detail + " TEXT);";
    private static final String CREATE_TABLE2 = "CREATE TABLE "+TABLE_NAME2+
            " ("+COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ COL_NAME+ " TEXT, "+COL_Price+ " Text, " + COL_Detail + " TEXT);";
    private static final String CREATE_TABLE3 = "CREATE TABLE "+TABLE_NAME3+
            " ("+COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ COL_NAME+ " TEXT, "+COL_Price+ " Text, " + COL_Detail + " TEXT);";

    private static final String CREATE_TABLE4 = "CREATE TABLE "+TABLE_NAME4+
            " ("+COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ COL_NAME+ " TEXT, " +COL_Price+ " Text, " + COL_Quantity + " TEXT);";



    private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME4;
    private Context c;

    public MyDbHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        c = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE2);
        db.execSQL(CREATE_TABLE3);
        db.execSQL(CREATE_TABLE4);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }
    public void del() {

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(DROP_TABLE);
        db.close();

    }
    public void addData(String n,String s,String a,String tab){

        delData(n,tab);

        ContentValues values = new ContentValues();
        values.put(COL_NAME,n);
        values.put(COL_Price,s);
        values.put(COL_Detail,a);
        SQLiteDatabase db = getWritableDatabase();
        db.insert(tab,null,values);
        db.close();
    }

    public void addDataQ(String n,String s,String a){

        delData(n,TABLE_NAME4);

        ContentValues values = new ContentValues();
        values.put(COL_NAME,n);
        values.put(COL_Price,s);
        values.put(COL_Quantity,a);
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME4,null,values);
        db.close();
    }


    public void delData(String n,String tab){
        String[] values = {n};
        SQLiteDatabase db = getWritableDatabase();
        db.delete(tab,COL_NAME + " = ?",values);
        db.close();
    }

    public String[] getVal(String n,String tab){

        SQLiteDatabase db = getWritableDatabase();
        String[] str = new String[2];
        str[0]="";
        str[1]="";
        String s="";
        String query = "SELECT * FROM " + tab ;

        Cursor cursor =db.rawQuery(query,null);


        cursor.moveToFirst();
        try{
            while (!cursor.isAfterLast())
            {
                String check = cursor.getString(cursor.getColumnIndex("_name"));
                if(check!=null&&check.equals(n)){
                    //Toast.makeText(c, "ok", Toast.LENGTH_LONG).show();
                    str[0] =  cursor.getString(cursor.getColumnIndex("_price"));
                    str[1] =  cursor.getString(cursor.getColumnIndex("_detail"));
                    //s=s+"\n";

                }
                cursor.moveToNext();
            }}
        catch (Exception e){

        }
        db.close();
        return str;
    }



    public  ArrayList<String> getValY(String stn){
        SQLiteDatabase db = getWritableDatabase();
        String[] projection = {stn};
        Cursor cursor = db.query(TABLE_NAME, projection, null, null, null, null, null);

        ArrayList<String> values = new ArrayList<String>();
        if (cursor != null && cursor.getCount() != 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {

                values.add(cursor.getString(cursor.getColumnIndex(stn)));

                cursor.moveToNext();
            }
        }
        db.close();
        return values;
    }

    public String[] getValQ(String n){

        SQLiteDatabase db = getWritableDatabase();
        String[] str = new String[2];
        str[0]="";
        str[1]="";
        String s="";
        String query = "SELECT * FROM " + TABLE_NAME4 ;

        Cursor cursor =db.rawQuery(query,null);


        cursor.moveToFirst();
        try{
            while (!cursor.isAfterLast())
            {
                String check = cursor.getString(cursor.getColumnIndex("_name"));
                if(check!=null&&check.equals(n)){
                    //Toast.makeText(c, "ok", Toast.LENGTH_LONG).show();
                    String st=  cursor.getString(cursor.getColumnIndex("_price"));
                    str[0] =  cursor.getString(cursor.getColumnIndex("_quant"));
                    int k= Integer.parseInt(st) *Integer.parseInt(str[0]);


                    str[1] =  String.valueOf(k);
                    //s=s+"\n";

                }
                cursor.moveToNext();
            }}
        catch (Exception e){

        }
        db.close();
        return str;
    }





}
