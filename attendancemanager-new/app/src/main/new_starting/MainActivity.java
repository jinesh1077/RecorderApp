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
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    StarbuzzDatabaseHelper db1=new StarbuzzDatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         check();

        setText("SELECT YOUR GENDER");
    }
    public boolean check(){
        SQLiteDatabase db = db1.getReadableDatabase();
        boolean c1=db1.check1(db);
        if(c1)
        {
            goto5();
            return true;
        }
        else
            return false;

    }
    public void male(View view)
    {
        if(check())
            goto5();
        else
        {
        Intent newact=new Intent(this,Main2Activity.class);
        newact.putExtra("puzzle","male");
        startActivity(newact);

    }}
    public void female(View view)
    {
        if(check())
            goto5();
        else
        {
        Toast.makeText(this,"female",Toast.LENGTH_LONG).show();
        Intent newact=new Intent(this,Main2Activity.class);
        newact.putExtra("puzzle","female");
        startActivity(newact);
    }}

    public void setText(final String s)
    {
        final TextView tv= (TextView)findViewById(R.id.hello);
        tv.setTextColor(Color.parseColor("#ffffba"));
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
        timer.schedule(taskEverySplitSecond, 1, 200);
    }
    public void goto5()
    {
        int f = Integer.valueOf(android.os.Build.VERSION.SDK);
        if (f >= 24) {
            Intent newact1 = new Intent(MainActivity.this, Main5Activity.class);
            startActivity(newact1);
        }
        else
        {
            Intent newact1 = new Intent(MainActivity.this, Main8Activity.class);
            startActivity(newact1);
        }
    }
}
