package com.example.android.new_starting;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.Locale;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

public class Main5Activity extends AppCompatActivity {
    StarbuzzDatabaseHelper db1=new StarbuzzDatabaseHelper(this);
    EditText editText;
    Calendar myCalendar;
    int numberofsub;
    String finalday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        Calendar c = Calendar.getInstance();


        Toast.makeText(Main5Activity.this,"hello",Toast.LENGTH_LONG).show();



        int weekDay=c.get(Calendar.DAY_OF_WEEK);
        String s;

        if(weekDay==2)
            s="MONDAY";
        else if(weekDay==3)
            s="TUESDAY";
        else if(weekDay==4)
            s="WEDNESDAY";



        else if(weekDay==5)
            s="THURSDAY";

        else if(weekDay==6)
            s="FRIDAY";
        else
            s="Holiday";
        finalday=s;
        try{
            fn1(s);}
        catch (Exception e)
        {
            Toast.makeText(this,""+e,Toast.LENGTH_LONG).show();
        }

        try {
            Toast.makeText(Main5Activity.this,s,LENGTH_LONG).show();
            //setdate(s);

        }
        catch (Exception e)
        {
            Toast.makeText(Main5Activity.this,""+e,LENGTH_LONG).show();
        }


        try {
            myCalendar = Calendar.getInstance();
            // private int dayOfMonth,monthOfYear,year;



            editText= (EditText) findViewById(R.id.Birthday);
editText.setTextColor(Color.parseColor("#ffffba"));
            updateLabel();


            final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                    // TODO Auto-generated method stub
                    myCalendar.set(Calendar.YEAR, year);
                    myCalendar.set(Calendar.MONTH, monthOfYear);
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    SimpleDateFormat simpledateformat = new SimpleDateFormat("EEEE");
                    Date date = new Date(year,monthOfYear, dayOfMonth-1);
                    String dayOfWeek = simpledateformat.format(date);
                    String weekDay = dayOfWeek;
                    try {
                        if(weekDay.equals("Monday"))
                            weekDay="MONDAY";
                        else if(weekDay.equals("Tuesday"))
                            weekDay="TUESDAY";
                        else if(weekDay.equals("Wednesday"))
                            weekDay="WEDNESDAY";
                        else if(weekDay.equals("Thursday"))
                            weekDay="THURSDAY";
                        else if(weekDay.equals("Friday"))
                            weekDay="FRIDAY";
                        else
                            weekDay="Holiday";



                        //Toast.makeText(Main5Activity.this,weekDay,LENGTH_LONG).show();
                        finalday=weekDay;
                        fn1(weekDay);
                    }
                    catch (Exception e)
                    {
                        //Toast.makeText(Main2Activity.this,""+e,LENGTH_LONG).show();

                    }

                    editText= (EditText) findViewById(R.id.Birthday);
                    updateLabel();

                }

            };


            editText.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    new DatePickerDialog(Main5Activity.this, date, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();

                }
            });

        } catch (Exception e) {
            Toast tb = makeText(this, "issue in oncreate function ", LENGTH_SHORT);
            tb.show();
        }
    }

    private void fn1(String day) {
        SQLiteDatabase db = db1.getReadableDatabase();
        int finalv= db1.find11(db,day);

        if(finalv==-1)
            Toast.makeText(this,"error1",Toast.LENGTH_LONG).show();
        f2(finalv,day);
        numberofsub=finalv;
        Toast.makeText(this,""+finalv,LENGTH_LONG).show();
        if(finalv==0||finalv==-1)
        {
            LinearLayout linearLayout= (LinearLayout)findViewById(R.id.linear);
            linearLayout.removeAllViews();
            ImageView img= new ImageView(this);
            img.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,600));
            //String z=db1.getgender(db);
            //Toast.makeText(this,""+z,LENGTH_LONG).show();
            img.getLayoutParams().height = 600;


RadioButton r=(RadioButton)findViewById(R.id.attended);
r.setActivated(false);
                img.setImageResource(R.drawable.female_relax);

            linearLayout.addView(img);


        }
        ((ViewGroup) findViewById(R.id.radiogroup)).removeAllViews();
        for(int i=1;i<=finalv;i++)
            addRadioButtons(i);

    }
    public void f2(int finalv,String day){
        LinearLayout linearLayout= (LinearLayout)findViewById(R.id.linear);
        linearLayout.removeAllViews();
        for(int i=0; i<finalv;i++){
            TextView textView= new TextView(this);
            textView.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,250));



            int c=i+1;
            finalday=day;
            String j=""+c;
            textView.setText(""+setdate(day,j));
            textView.setTextColor(Color.parseColor("#ffffba"));
            textView.setTypeface(Typeface.MONOSPACE,Typeface.BOLD_ITALIC);
            textView.setTextSize(28);//adding text
            linearLayout.addView(textView);
        }
    }


    public void addRadioButtons(int id) {


        RadioGroup ll = new RadioGroup(this);
        ll.setOrientation(LinearLayout.VERTICAL);

        ll.setId((id));
        for (int i = 1; i <= 3; i++) {
            RadioButton rdbtn = new RadioButton(this);
            rdbtn.setId(i+id*10);
            rdbtn.setTextSize(18);
            if(i==1)
                rdbtn.setText("Yes " );
            else if (i==2)
                rdbtn.setText("No " );
            else
                rdbtn.setText("Attendance Not Taken " );
            ll.addView(rdbtn);
        }
        ((ViewGroup) findViewById(R.id.radiogroup)).addView(ll);


    }
    private void updateLabel() {
        try {
            //Toast.makeText(a1.this,"hello1",Toast.LENGTH_LONG).show();

            String myFormat = "dd/MM/yy"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

            editText.setText(sdf.format(myCalendar.getTime()));

        } catch (Exception e) {
            Toast tb = makeText(this, "issue is in update label function and the issue is\n\n"+e, LENGTH_LONG);
            tb.show();
        }
    }

    public String setdate(String day,String id) {




        SQLiteDatabase db = db1.getReadableDatabase();
        String c = db1.findsubject(db,id,day);

        return c;



    }
    public  void AddData(View view) {

        try {
            RadioGroup radioGroup = (RadioGroup) findViewById(R.id.checkatt);
            int  radioButtonID = radioGroup.getCheckedRadioButtonId();

            RadioButton radioButton = radioGroup.findViewById(radioButtonID);
            String c = (String) radioButton.getText();
            editText= (EditText) findViewById(R.id.Birthday);
            String s=editText.getText().toString();
            String c1;
            if(c.equals("holiday"))
            {c1="-1";
                SQLiteDatabase db = db1.getWritableDatabase();
                boolean isInserted = db1.insertData11(db,s,c,c1,null);}
            else if(c.equals("bunk"))
            {
                c1="-2";
                SQLiteDatabase db = db1.getWritableDatabase();
                boolean isInserted = db1.insertData11(db,s,c,c1,null);
            }
            else{
                for(int i=1;i<=numberofsub;i++){

                    radioGroup = (RadioGroup) findViewById(i);
                    radioButtonID = radioGroup.getCheckedRadioButtonId();

                    radioButton =  radioGroup.findViewById(radioButtonID);
                    c = (String) radioButton.getText();
                    Toast.makeText(Main5Activity.this,c,LENGTH_LONG).show();
                    if(c.equals("no "))
                        c1="2";
                    else if(c.equals("yes "))
                        c1="3";
                    else if(c.equals("attendance not taken "))
                        c1="4";
                    else
                        c1="5";
String ed_id=""+i;
                    String sub=setdate(finalday,""+i);
                    SQLiteDatabase db = db1.getWritableDatabase();
                    boolean isInserted = db1.insertData11(db,s,sub,c1,ed_id);
                    if (isInserted == true)
                        Toast.makeText(Main5Activity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(Main5Activity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                }}
                next3();
        }catch (Exception e) {
            Toast.makeText(this, "there is an exception" + e, Toast.LENGTH_LONG).show();
        }
    }
    public void viewall(View view) {

        try{
            SQLiteDatabase db = db1.getReadableDatabase();
            Cursor res = db1.getAllData11(db);
            if(res.getCount() == 0) {
                // show message
                showMessage("Error","Nothing found");
                return;
            }

            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()) {
                buffer.append("Id :"+ res.getString(0)+"\n");
                buffer.append("date :"+ res.getString(1)+"\n");
                buffer.append("subject :"+ res.getString(2)+"\n");
                buffer.append("what happened :"+ res.getString(3)+"\n");
                buffer.append("edittextid :"+ res.getString(4)+"\n");
            }

            // Show all data
            showMessage("Data",buffer.toString());
        }
        catch(Exception e)
        {
            Toast.makeText(this,"error in view"+e,LENGTH_LONG).show();
        }
    }
    // );
    //}
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
    public void next2(View view)
    {
        //Toast.makeText(this,"error in button"+finalday,LENGTH_LONG).show();
next3();

    }
    public void next3()
    {
        try{
            Intent newact=new Intent(this,Main6Activity.class);
            newact.putExtra("puzzle",finalday);
            if(!finalday.equals("Hoilday"))
                startActivity(newact);
            else {
                newact = new Intent(this, Main6Activity.class);
                startActivity(newact);

            }
        }
        catch (Exception e)
        {
            Toast.makeText(this,"error in button"+e,LENGTH_LONG).show();
        }
    }
    public void returnhome(View view)
    {
        Intent newact=new Intent(this,Main6Activity.class);
        startActivity(newact);
        Toast.makeText(this,"error in button",LENGTH_LONG).show();

    }
}
