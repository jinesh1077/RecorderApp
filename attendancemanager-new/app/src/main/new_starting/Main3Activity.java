package com.example.android.new_starting;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {

    LinearLayout l1;
    int number_of_subjects =0;
    StarbuzzDatabaseHelper db1=new StarbuzzDatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        try{
       l1=(LinearLayout)findViewById(R.id.linear);


    } catch (Exception e)
        {
            Toast.makeText(this,""+e,Toast.LENGTH_LONG).show();
        }}
    public void addvi(View view)
    {

        number_of_subjects++;
try{
        EditText editText = new EditText(Main3Activity.this);
        editText.setId(number_of_subjects);
        editText.setBackgroundResource(R.drawable.subjects);

        editText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        editText.setHint("SUBJECT NAME "+(number_of_subjects));
            l1.addView(editText);
        }catch (Exception e)
        {
            Toast.makeText(this,""+e,Toast.LENGTH_LONG).show();
        }
    }
    public void remove (View view)
    {
        try{
        ViewGroup layout = (ViewGroup) findViewById(R.id.linear);
        View command = layout.findViewById(number_of_subjects);
        layout.removeView(command);
            if(number_of_subjects>0)
        number_of_subjects--;
    }catch (Exception e)
        {
            Toast.makeText(this,""+e,Toast.LENGTH_LONG).show();
        }
}
    public void next(View view)
    {
        int check1=1,check2=0;
        for(int i=1;i<=number_of_subjects;i++)
        for (int j=i+1;j<=number_of_subjects;j++)
        {
            EditText e=(EditText)findViewById(i);
            String s=e.getText().toString();
            e=(EditText)findViewById(j);
            String s2=e.getText().toString();
            if(s.equals(s2))
              check2=1;



        }
        //Toast.makeText(this,""+check2,Toast.LENGTH_LONG).show();
        if(check2==1) {
            Toast toast=Toast.makeText(getApplicationContext(),"REMOVE SAME SUBJECTS",Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER,0,0);
            toast.show();
        }
        else
        {
            for(int i=1;i<=number_of_subjects;i++)
            {
                EditText e=(EditText)findViewById(i);
                String s=e.getText().toString();
                SQLiteDatabase db = db1.getWritableDatabase();
                if(s.equals(""))
                    check1=0;
                else
                {boolean c=db1.insertsubin(db,s);
                if(!c)
                    check1=0;}
            }
            if(check1==0)
            {
                Toast toast=Toast.makeText(getApplicationContext(),"NOT INSERTED PROPERLY",Toast.LENGTH_LONG);
                toast.setGravity(Gravity.BOTTOM | Gravity.CENTER,0,0);
                toast.show();
            }
            else
            {
                try{
                Toast.makeText(this,"INSERTED PROPERLY",Toast.LENGTH_LONG).show();
                try {


                    Intent newact1 = new Intent(Main3Activity.this, Main4Activity.class);

                    startActivity(newact1);
                }
                catch (Exception e)
                {
                    Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
                }
            }catch (Exception e)
                {
                    Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
                }
            }

        }


    }
}
