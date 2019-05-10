package com.example.android.new_starting;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class Main2Activity extends AppCompatActivity {
String c="FEMALE";
    StarbuzzDatabaseHelper db1=new StarbuzzDatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setText("What is your name ?");
        Intent i = getIntent();
        String gender= i.getStringExtra("puzzle");
        ImageView img= (ImageView) findViewById(R.id.imageView);
        try {


        if(gender.equals("male")){
            img.setImageResource(R.drawable.man2);
            c="MALE";
        }
            img.setBackgroundColor(Color.TRANSPARENT);

    }
    catch (Exception e)
    {
        Toast.makeText(this,""+e,Toast.LENGTH_LONG).show();
    }}
    public void next1(View view)
    {//Toast.makeText(this,"ON NEXT BUTTON",Toast.LENGTH_LONG).show();

        try{
            EditText t=(EditText) findViewById(R.id.name1);
            String s=t.getText().toString();
            if(s.equals(""))
            {
                Toast.makeText(this,"ENTER NAME",Toast.LENGTH_SHORT).show();
            }else{
            SQLiteDatabase db = db1.getReadableDatabase();
           boolean x= db1.insertgender(db,c);
           if(!x)
           {
               Toast.makeText(this,"not inserted",Toast.LENGTH_SHORT).show();
           }
           else
           {
               Toast.makeText(this,"inserted",Toast.LENGTH_SHORT).show();
           }

        Intent newact1=new Intent(Main2Activity.this,Main3Activity.class);
        //    Toast.makeText(this,"ON NEXT BUTTON",Toast.LENGTH_LONG).show();

        startActivity(newact1);
        }}catch (Exception e)
        {
            Toast.makeText(this,""+e,Toast.LENGTH_LONG).show();
        }

    }
    public void setText(final String s)
    {
        final TextView tv= (TextView)findViewById(R.id.name);
        tv.setTextColor(Color.parseColor("#c16328"));

        tv.setTextSize(25);
        final int[] i = new int[1];
        i[0] = 0;
        final int length = s.length();
        final Handler handler = new Handler()
        {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                char c= s.charAt(i[0]);
                Log.d("Strange",""+c);
                tv.append(String.valueOf(c));
                i[0]++;
            }
        };

        final Timer timer = new Timer();
        TimerTask taskEverySplitSecond = new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
                if (i[0] == length - 1) {
                    timer.cancel();
                }
            }
        };
        timer.schedule(taskEverySplitSecond, 1, 100);
    }
}
