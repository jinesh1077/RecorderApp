package com.example.android.new_starting;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;

public class Main6Activity extends AppCompatActivity {
    StarbuzzDatabaseHelper db1=new StarbuzzDatabaseHelper(this);
    int finalv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        LinearLayout l1=(LinearLayout)findViewById(R.id.ll1);
        Intent i = getIntent();
        String finalday = i.getStringExtra("puzzle");
        //TextView t=(TextView)findViewById(R.id.textView2);
        //t.setText(easyPuzzle);
        Toast.makeText(Main6Activity.this,finalday,Toast.LENGTH_LONG).show();
        SQLiteDatabase db = db1.getReadableDatabase();
        finalv= db1.find11(db,finalday);

        l1.removeAllViews();



        for(int j=0;j<finalv;j++)
        {


            //buttonLayoutParams.setMargins(50, 10, 0, 0);
            //b.setLayoutParams(buttonLayoutParams);



            int c=j+1;
            // l1.removeAllViews();
            TextView b=new TextView(Main6Activity.this);
            EditText e1=new EditText(Main6Activity.this);
            EditText e2=new EditText(Main6Activity.this);
            b.setId(j);
            b.setTextColor(Color.parseColor("#ffffba"));
            e1.setId(j+100);
            e2.setId(j+200);
            b.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            LinearLayout.LayoutParams b1 = new LinearLayout.LayoutParams(800, LinearLayout.LayoutParams.WRAP_CONTENT);
            LinearLayout.LayoutParams b2 = new LinearLayout.LayoutParams(800, LinearLayout.LayoutParams.WRAP_CONTENT);
            String k=""+c;
            b.setText(""+setdate(finalday,k));
            b.setTextSize(40);
            l1.addView(b);
            e1.setHint("Chapter");
            e2.setHint("Subtopic");
            b1.setMargins(300,0,0,0);
            b2.setMargins(300,0,0,0);
            e1.setLayoutParams(b1);
            e2.setLayoutParams(b2);
            l1.addView(e1);
            l1.addView(e2);

        }
        Button b1=new Button(Main6Activity.this);int dd=33;
        b1.setId(dd+1);
        b1.setLayoutParams(new LinearLayout.LayoutParams(300,150));
        b1.setText("Submit");
        l1.addView(b1);



        Button b3=new Button(Main6Activity.this);
        b3.setId(dd+3);
        b3.setLayoutParams(new LinearLayout.LayoutParams(300,150));
        b3.setText("NEXT");
        l1.addView(b3);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SQLiteDatabase db = db1.getReadableDatabase();
                for(int j=0;j<finalv;j++)
                {
                    EditText e1,e2;
                    e1=(EditText)findViewById(j+100);
                    e2=(EditText)findViewById(j+200);
                    String s=e1.getText().toString();
                    String c=e2.getText().toString();
                    if(s.equals(""))
                    {
                        if(c.equals(""))
                            continue;
                        else {
                            TextView t=(TextView)findViewById(j);
                            String c2=t.getText().toString();
                            db1.insertrandom(db, c2, c);
                        }
                    }
                    else{
                        if(c.equals(""))
                        {
                            c="subtopic not known for now";
                        }
                        TextView t=(TextView)findViewById(j);
                        String c2=t.getText().toString();
                        db1.inserttopic(db,c2,s,c);
                    }
                }
                Toast.makeText(Main6Activity.this,"button",Toast.LENGTH_LONG).show();
                //Log.i("TAG", "The index is" + index);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent newact=new Intent(Main6Activity.this,Main7Activity.class);
                startActivity(newact);
            }});
    }



    public String setdate(String day,String id) {




        SQLiteDatabase db = db1.getReadableDatabase();
        String c = db1.findsubject(db,id,day);

        return c;



    }

}
