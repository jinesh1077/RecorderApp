package com.example.android.new_starting;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by shubhamgupta on 14/01/18.
 */
class StarbuzzDatabaseHelper extends SQLiteOpenHelper {
        public static final String DATABASE_NAME = "Student.db";
        public static final String TABLE_NAME = "subject1_table";
        public static final String TABLE_NAME2 = "subject2_table";
        public static final String TABLE_NAME1 = "start_table";
        public static final String COL_1 = "ID";
        public static final String COL_2 = "DAY";
        public static final String COL_3 = "SUBJECT";
        public static final String COL1 = "STARTD";
        public static final String COL2 = "ENDD";
        public static final String COL3 = "DAY1";
        public static final String COL4 = "NUMBEROF";
        public static final String TABLE_NAME3 = "student3_table";

        public static final String COL_11 = "ID";
        public static final String COL_12 = "DATE";
        public static final String COL_13 = "SUBJECT";
        public static final String COL_14 = "ATTENDANCE";

        public static final String COL111 = "SUBJECT";

    public static final String CO = "GENDER";
        public static final String COL112 = "TOPIC";
        public static final String COL113 = "SUBTOPIC";
    public static final String C = "EDI";
        public static final String TABLE_NAME4 = "TOPIC_INFO";
        public static final String TABLE_NAME5 = "TOPIC_RANDOM";
    public static final String TABLE_NAME6 = "SUBJECTS_NAME";
    public static final String TABLE_NAME7 = "GENDER";







        public StarbuzzDatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table " + TABLE_NAME +" (ID TEXT,DAY TEXT,SUBJECT TEXT)");
            db.execSQL("create table " + TABLE_NAME1 +" (DAY1 TEXT,NUMBEROF TEXT)");
            db.execSQL("create table " + TABLE_NAME2 +" (STARTD TEXT,ENDD TEXT)");
            db.execSQL("create table " + TABLE_NAME6 +" (SUBJECT TEXT)");
            db.execSQL("create table " + TABLE_NAME3 +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,SUBJECT TEXT,ATTENDANCE TEXT,EDI TEXT)");
            db.execSQL("create table " + TABLE_NAME4 +" (SUBJECT TEXT,TOPIC TEXT,SUBTOPIC TEXT)");
            db.execSQL("create table " + TABLE_NAME5 +" (SUBJECT TEXT,SUBTOPIC TEXT)");
            db.execSQL("create table " + TABLE_NAME7 +" (GENDER TEXT)");


        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME2);
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME3);
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME4);
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME5);
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME6);
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME7);

            onCreate(db);
        }
        public boolean check1(SQLiteDatabase db){
            Cursor c=db.rawQuery("SELECT * FROM "+TABLE_NAME6,null);
            if(c.getCount()>0)
            {
                return true;
            }
            else
                return false;
        }
    public boolean insertsubin(SQLiteDatabase db,String subject) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_13,subject);


        long result = db.insert(TABLE_NAME6,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }
    public boolean insertgender(SQLiteDatabase db,String gender) {
deletegender(db);
        ContentValues contentValues = new ContentValues();
        contentValues.put(CO,gender);


        long result = db.insert(TABLE_NAME7,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }
    public String getgender(SQLiteDatabase db)
    {
        Cursor cursor = db.query(TABLE_NAME7,new String[]{"GENDER"},null,null,null,null,null  );

        String value=cursor.getString(cursor.getColumnIndex("GENDER"));
        return value;
    }
    public Integer deletegender (SQLiteDatabase db) {

        return db.delete(TABLE_NAME7, null,null);
    }
        public boolean insertData(SQLiteDatabase db,String id,String day,String subject) {

            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_1,id);
            contentValues.put(COL_2,day);
            contentValues.put(COL_3,subject);


            // String Query = "Select * from " + TABLE_NAME + " where " + date + " =? " ;
            //Cursor cursor = db.query(SELECT * FROM TABLE_NAME WHERE DATE == date AND SUBJECT ==subject);

            // Toast.makeText(MainActivity.this,"EXIST",Toast.LENGTH_LONG).show();

            Cursor cursor = db.query(TABLE_NAME,new String[]{"ID","DAY" },"ID=? AND DAY=?",new String[]{id,day},null,null,null  );
            if(cursor.getCount() > 0){
                cursor.close();

                return updateData(db,id,day,subject);
            }
            long result = db.insert(TABLE_NAME,null ,contentValues);
            if(result == -1)
                return false;
            else
                return true;
        }
        public boolean insertsub(SQLiteDatabase db,String day,String numberofsubject) {

            ContentValues contentValues = new ContentValues();
            //contentValues.put(COL1,startd);
            //contentValues.put(COL2,endd);
            contentValues.put(COL3,day);
            contentValues.put(COL4,numberofsubject);
            Cursor cursor = db.query(TABLE_NAME1,new String[]{"DAY1","NUMBEROF"}," DAY1=?",new String[]{day},null,null,null  );
            if(cursor.getCount() > 0){
                cursor.close();

                return updateData1(db,day,numberofsubject);
            }
            long result = db.insert(TABLE_NAME1,null ,contentValues);
            if(result == -1)
                return false;
            else
                return true;

        }
    public boolean checkday1(SQLiteDatabase db,String day)
    {
        int c=db.delete(TABLE_NAME, "DAY = ?",new String[] {day});
        if(c==-1)
        {
            return false;

        }
        else{
            return true;
        }

    }
        public boolean insertrandom(SQLiteDatabase db,String subject,String subtopic) {

            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_13,subject);
            //contentValues.put(COL112,topic);
            contentValues.put(COL113,subtopic);

            long result = db.insert(TABLE_NAME5,null ,contentValues);
            if(result == -1)
                return false;
            else
                return true;

        }

        public boolean inserttopic(SQLiteDatabase db,String subject,String topic,String subtopic) {

            ContentValues contentValues = new ContentValues();
            contentValues.put(COL111,subject);
            contentValues.put(COL112,topic);
            contentValues.put(COL113,subtopic);

            long result = db.insert(TABLE_NAME4,null ,contentValues);
            if(result == -1)
                return false;
            else
                return true;

        }

        private boolean updateData(SQLiteDatabase db,String id, String day, String subject) {


            ContentValues contentValues = new ContentValues();

            contentValues.put(COL_1,id);
            contentValues.put(COL_2,day);
            contentValues.put(COL_3,subject);

            long re=db.update(TABLE_NAME,
                    contentValues,
                    "DAY" + " = ? AND " + "ID"  + " = ?",
                    new String[]{day,id});
            if(re == -1)
                return false;
            else
                return true;
        }
        private boolean updateData1(SQLiteDatabase db,String day, String numberofsubject) {


            ContentValues contentValues = new ContentValues();

            contentValues.put(COL3,day);
            contentValues.put(COL4,numberofsubject);

            long re=db.update(TABLE_NAME1,
                    contentValues,
                    "DAY1" + " = ?",
                    new String[]{day});
            if(re == -1)
                return false;
            else
                return true;
        }
        public Cursor getAllData(SQLiteDatabase db) {

            Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
            return res;
        }
        public Cursor view(SQLiteDatabase db) {

            Cursor res = db.rawQuery("select * from "+TABLE_NAME4,null);
            return res;
        }
        public Cursor getAllData1(SQLiteDatabase db) {

            Cursor res = db.rawQuery("select * from "+TABLE_NAME2,null);
            return res;
        }
        public Cursor getAllData2(SQLiteDatabase db) {

            Cursor res = db.rawQuery("select * from "+TABLE_NAME1,null);
            return res;
        }
        public boolean insertData11(SQLiteDatabase db,String date,String subject,String att,String edi) {

            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_12,date);
            contentValues.put(COL_13,subject);
            contentValues.put(COL_14,att);
            contentValues.put(C,edi);

            // String Query = "Select * from " + TABLE_NAME + " where " + date + " =? " ;
            //Cursor cursor = db.query(SELECT * FROM TABLE_NAME WHERE DATE == date AND SUBJECT ==subject);
            Cursor cursor = db.query(TABLE_NAME3,new String[]{"DATE","SUBJECT","EDI" },"DATE=? AND SUBJECT=? AND EDI=?",new String[]{date,subject,edi},null,null,null  );
            if(cursor.getCount() > 0){
                cursor.close();

                return false;
            }
            // Toast.makeText(MainActivity.this,"EXIST",Toast.LENGTH_LONG).show();
            cursor.close();


            long result = db.insert(TABLE_NAME3,null ,contentValues);
            if(result == -1)
                return false;
            else
                return true;
        }
        public Cursor getAllData11(SQLiteDatabase db) {

            Cursor res = db.rawQuery("select * from "+TABLE_NAME3,null);
            return res;
        }

        public boolean updateData11(SQLiteDatabase db,String date,String subject,String att) {

            ContentValues contentValues = new ContentValues();

            contentValues.put(COL_12,date);
            contentValues.put(COL_13,subject);
            contentValues.put(COL_14,att);

            db.update(TABLE_NAME3,
                    contentValues,
                    "DATE" + " = ? AND " + "SUBJECT"  + " = ?",
                    new String[]{date,subject});

            return true;

        }

        public Integer deleteData11 (SQLiteDatabase db,String id) {

            return db.delete(TABLE_NAME3, "ID = ?",new String[] {id});
        }
        public String findsubject(SQLiteDatabase db,String id,String day)
        {

            Cursor cursor = db.query(TABLE_NAME,new String[]{"ID","DAY","SUBJECT"}," ID=? AND DAY=? ",new String[]{id,day},null,null,null  );
            // Log.w("day",cursor.getString(2));
            //Toast.makeText(context,cursor.getString(2),Toast.LENGTH_LONG).show();
            if(cursor.getCount() <= 0){
                cursor.close();
                db.close();
                return "hello";
            }
            else{
                if(cursor.moveToFirst())
                {
                    String value=cursor.getString(cursor.getColumnIndex("SUBJECT"));
                    db.close();
                    return value;


                }
                else
                {
                    return "error1";
                }}
        }

        public int find11(SQLiteDatabase db,String s)
        {
            // SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.query(TABLE_NAME1,new String[]{"DAY1","NUMBEROF"}," DAY1=?",new String[]{s},null,null,null  );
            if(cursor.getCount() <= 0){
                cursor.close();
                db.close();
                return -1;
            }


            else
            {
                if(cursor.moveToFirst())
                {String value=cursor.getString(cursor.getColumnIndex("NUMBEROF"));
                    int finalValue=Integer.parseInt(value);
                    db.close();
                    return finalValue;
                }
                else
                {
                    db.close();

                    return -1;
                }



            }
        }
        public boolean  checkdatafilled(SQLiteDatabase db){

            Cursor cursor = db.rawQuery("select * from "+TABLE_NAME2,null);
            if(cursor.getCount() <= 0){
                cursor.close();
                db.close();
                return false;}
            else
                return true;
        }
        public boolean checkday(SQLiteDatabase db,String day)
        {
            Cursor cursor = db.query(TABLE_NAME1,new String[]{"DAY1","NUMBEROF"}," DAY1=?",new String[]{day},null,null,null  );
            if(cursor.getCount() <= 0){
                cursor.close();
                db.close();
                return false;}
            else
                return true;
        }
        public long check(SQLiteDatabase db)
        {
            Cursor res = db.rawQuery("select DISTINCT DATE from "+TABLE_NAME3,null);
            return res.getCount();

        }
        public long subjecttotal(SQLiteDatabase db,String sub){
            Cursor cursor = db.query(TABLE_NAME3,new String[]{"SUBJECT","ATTENDANCE"}," SUBJECT=?",new String[]{sub},null,null,null  );
            return cursor.getCount();
        }
        public long subjectattended(SQLiteDatabase db,String sub){
            Cursor cursor = db.query(TABLE_NAME3,new String[]{"SUBJECT","ATTENDANCE"}," SUBJECT=? AND ATTENDANCE=?",new String[]{sub,"3"},null,null,null  );
            return cursor.getCount();
        }
        public Cursor findallsubjectsin(SQLiteDatabase db)
        {
            Cursor res = db.rawQuery("select DISTINCT SUBJECT from "+TABLE_NAME,null);
            return res;
        }
    public long countsub(SQLiteDatabase db){
        Cursor cursor = db.query(TABLE_NAME6,new String[]{"SUBJECT"},null,null,null,null,null  );
        return cursor.getCount();
    }
    public Cursor countsub1(SQLiteDatabase db){
        Cursor cursor = db.query(TABLE_NAME6,new String[]{"SUBJECT"},null,null,null,null,null  );
        return cursor;
    }
    public Cursor checkdataof(SQLiteDatabase db,String day)
    {
        Cursor cursor = db.query(TABLE_NAME,new String[]{"DAY","SUBJECT"}," DAY=?",new String[]{day},null,null,null  );
        return cursor;
    }
    public long subattnottaken(SQLiteDatabase db,String sub){
        Cursor cursor = db.query(TABLE_NAME3,new String[]{"SUBJECT","ATTENDANCE"}," SUBJECT=? AND ATTENDANCE=?",new String[]{sub,"4"},null,null,null  );
        return cursor.getCount();
    }
    public long subholiday(SQLiteDatabase db,String sub){
        Cursor cursor = db.query(TABLE_NAME3,new String[]{"SUBJECT","ATTENDANCE"}," SUBJECT=? AND ATTENDANCE=?",new String[]{sub,"4"},null,null,null  );
        return cursor.getCount();
    }


    }







