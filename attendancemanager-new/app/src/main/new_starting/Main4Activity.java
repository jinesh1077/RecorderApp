package com.example.android.new_starting;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.Image;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class Main4Activity extends AppCompatActivity {
    int day=1,in_id=0;

    StarbuzzDatabaseHelper db1=new StarbuzzDatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        checksub();
        setday();
    }

    private void checksub() {
        SQLiteDatabase db = db1.getReadableDatabase();
        String s="";
        if(day==1)
            s="MONDAY";
        else if(day==2)
            s="TUESDAY";
        else if(day==3)
            s="WEDNESDAY";
        else if(day==4)
            s="THURSDAY";

        else if(day==5)
            s="FRIDAY";
        else if(day==6)
            s="SATURDAY";
        else if(day==0)
            s="SUNDAY";
        Cursor c=db1.checkdataof(db,s);
        while (c.moveToNext()) {


            {
                LinearLayout l1=(LinearLayout)findViewById(R.id.l1) ;
                //Toast.makeText(Main4Activity.this,sub.getString(0),Toast.LENGTH_LONG).show();
                TextView textView= new TextView(Main4Activity.this);              //dynamically create textview
                textView.setLayoutParams(new LinearLayout.LayoutParams(             //select linearlayoutparam- set the width & height
                        ViewGroup.LayoutParams.MATCH_PARENT,100));
                textView.setTextSize(20);
                in_id++;
                textView.setId(in_id);
                textView.setText(""+c.getString(1));  textView.setTextColor(Color.parseColor("#c16328"));                                  //adding text
                l1.addView(textView);
            }



        }



    }

    public void addsubjects(View view) {

        SQLiteDatabase db = db1.getReadableDatabase();
        int c=(int)db1.countsub(db);
        //Toast.makeText(this,""+c,Toast.LENGTH_LONG).show();
        AlertDialog.Builder builder = new AlertDialog.Builder(Main4Activity.this);
        builder.setTitle("Choose subject");

// add a list

        String[] animals= new String[c];
        Cursor sub=db1.countsub1(db);
        int i=0;
        while (sub.moveToNext()) {
            animals[i]=sub.getString(0);
            i++;

        }
        sub.close();



        builder.setItems(animals, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(Main4Activity.this,"hell"+which,Toast.LENGTH_LONG).show();
                SQLiteDatabase db = db1.getReadableDatabase();

                Cursor sub=db1.countsub1(db);
                int i=0;
                while (sub.moveToNext()) {

                    if(i==which)
                    {
                        LinearLayout l1=(LinearLayout)findViewById(R.id.l1) ;
                        //Toast.makeText(Main4Activity.this,sub.getString(0),Toast.LENGTH_LONG).show();
                        TextView textView= new TextView(Main4Activity.this);              //dynamically create textview
                        textView.setLayoutParams(new LinearLayout.LayoutParams(             //select linearlayoutparam- set the width & height
                                ViewGroup.LayoutParams.MATCH_PARENT,100));
                        textView.setTextSize(20);
                        in_id++;
                        textView.setId(in_id);
                        textView.setText(sub.getString(0));
                        //adding text
                        textView.setTextColor(Color.parseColor("#ffffba"));
                        l1.addView(textView);
                    }
                    i++;


                }
                sub.close();
            }
        });

// create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();


//Toast.makeText(this,"hell",Toast.LENGTH_LONG).show();
    }
    public void adddatato(View view)
    {
        add();

        }
        public void add()
        {
            String s="";
            if(day==1)
                s="MONDAY";
            else if(day==2)
                s="TUESDAY";
            else if(day==3)
                s="WEDNESDAY";
            else if(day==4)
                s="THURSDAY";

            else if(day==5)
                s="FRIDAY";
            else if(day==6)
                s="SATURDAY";
            else if(day==0)
                s="SUNDAY";
            SQLiteDatabase db = db1.getReadableDatabase();
            db1.checkday1(db,s);
            for(int j=1;j<=in_id;j++)
            {

                TextView t=(TextView)findViewById(j);

                String c1=t.getText().toString();

                String k=""+j;

                boolean result=db1.insertData(db,k,s,c1);

                if(result)
                    Toast.makeText(this,"added",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(this,"not added",Toast.LENGTH_LONG).show();
            }
            //SQLiteDatabase db = db1.getWritableDatabase();
            boolean r = db1.insertsub(db,s,""+in_id);
        }


    public void next12(View view) {
        add();
        LinearLayout l1=(LinearLayout)findViewById(R.id.l1) ;
l1.removeAllViews();

        in_id=0;
        day++;
        day=day%7;
        if(day<0)
            day=day+7;
        checksub();
        //Toast.makeText(this,"hell1"+day,Toast.LENGTH_LONG).show();
        setday();
    }
    public void next11(View view) {
        add();
        LinearLayout l1=(LinearLayout)findViewById(R.id.l1) ;
        l1.removeAllViews();
        in_id=0;
        day--;
        day=day%7;
        if(day<0)
            day=day+7;
        checksub();
        //Toast.makeText(this,"hell"+day,Toast.LENGTH_LONG).show();
        setday();}
    public void remove (View view)
    {
        try{
            ViewGroup layout = (ViewGroup) findViewById(R.id.l1);
            View command = layout.findViewById(in_id);
            layout.removeView(command);
            if(in_id>0)
                in_id--;
        }catch (Exception e)
        {
            Toast.makeText(this,""+e,Toast.LENGTH_LONG).show();
        }
    }


    public void setday()
    {TextView t=(TextView)findViewById(R.id.daydisp);
        day=day%7;
        if(day<0)
            day=day+7;
        //Toast.makeText(this,""+day,Toast.LENGTH_LONG).show();
        if(day==1)
            t.setText("Add subjects for Monday");
        else if(day==2)
            t.setText("Add subjects for Tuesday");
        else if(day==3)
            t.setText("Add subjects for Wednesday");
        else if(day==4)
            t.setText("Add subjects for Thursday");
        else if(day==5)
            t.setText("Add subjects for Friday");
        else if(day==6)
            t.setText("Add subjects for Saturday");
        else if(day==0)
            t.setText("Add subjects for Sunday");
        t.setTextColor(Color.parseColor("#ffffba"));
        t.setGravity(Gravity.CENTER);



    }
    public void next(View view) {
        add();
        int f = Integer.valueOf(android.os.Build.VERSION.SDK);
        if (f >= 24) {
            Intent newact1 = new Intent(Main4Activity.this, Main5Activity.class);
            startActivity(newact1);
        }
        else
        {
            Intent newact1 = new Intent(Main4Activity.this, Main8Activity.class);
            startActivity(newact1);
        }
    }
}
