package com.example.android.new_starting;

        import android.app.DatePickerDialog;
        import android.content.Intent;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.icu.text.SimpleDateFormat;

        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.view.ViewGroup;

        import android.widget.DatePicker;
        import android.widget.EditText;
        import android.widget.LinearLayout;
        import android.widget.RadioButton;
        import android.widget.RadioGroup;
        import android.widget.TextView;
        import android.widget.Toast;


        import java.util.Calendar;
        import java.util.Date;

        import static android.widget.Toast.LENGTH_LONG;
        import static android.widget.Toast.LENGTH_SHORT;
        import static android.widget.Toast.makeText;

public class Main8Activity extends AppCompatActivity {
    StarbuzzDatabaseHelper db1=new StarbuzzDatabaseHelper(this);
    EditText editText;
    Calendar myCalendar;
    int numberofsub;
    String finalday;
    EditText selectDate;
    private int mYear, mMonth, mDay;
    int d,y,m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        Calendar c = Calendar.getInstance();
        selectDate=(EditText)findViewById(R.id.Birthday);

        //Toast.makeText(Main5Activity.this,"hello",Toast.LENGTH_LONG).show();



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

        fn1(s);






    }
    public void click(View view){click1();}
    public void click1(){

        {
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            selectDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            d=dayOfMonth;
                            m=monthOfYear;
                            y= year;

                        }
                    }, mYear, mMonth, mDay);
            Date date = new Date(y,m, d-1);
            Calendar cal  = Calendar.getInstance();
            cal.set(y, m, d);
            int weekDay=cal.get(Calendar.DAY_OF_WEEK);
            String s="";
            try {

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
                else if(weekDay==7)
                    s="SATURDAY";
                else
                    s="SUNDAY";
            }
            catch (Exception e)
            {
                Toast.makeText(this,""+e,Toast.LENGTH_LONG).show();
            }
            datePickerDialog.show();
            //Toast.makeText(this,""+s,Toast.LENGTH_LONG).show();
            finalday=s;
            fn1(finalday);


        }}

    private void fn1(String day) {
        SQLiteDatabase db = db1.getReadableDatabase();
        int finalv= db1.find11(db,day);

        if(finalv==-1)
            Toast.makeText(this,"error1",Toast.LENGTH_LONG).show();
        f2(finalv,day);
        numberofsub=finalv;
        ((ViewGroup) findViewById(R.id.radiogroup)).removeAllViews();
        for(int i=1;i<=finalv;i++)
            addRadioButtons(i);

    }
    public void f2(int finalv,String day){
        LinearLayout linearLayout= (LinearLayout)findViewById(R.id.linear);      //find the linear layout
        linearLayout.removeAllViews();                              //add this too
        for(int i=0; i<finalv;i++){          //looping to create 5 textviews

            TextView textView= new TextView(this);              //dynamically create textview
            textView.setLayoutParams(new LinearLayout.LayoutParams(             //select linearlayoutparam- set the width & height
                    ViewGroup.LayoutParams.MATCH_PARENT,200));


            //textView.setGravity(Gravity.CENTER_VERTICAL);
            int c=i+1;
            finalday=day;
            String j=""+c;//set the gravity too
            textView.setText(""+setdate(day,j));
            textView.setTextSize(20);//adding text
            linearLayout.addView(textView);                                     //inflating :)
        }
    }


    public void addRadioButtons(int id) {


        RadioGroup ll = new RadioGroup(this);
        ll.setOrientation(LinearLayout.VERTICAL);

        ll.setId((id));
        for (int i = 1; i <= 3; i++) {
            RadioButton rdbtn = new RadioButton(this);
            rdbtn.setId(i+id*10);
            if(i==1)
                rdbtn.setText("yes " );
            else if (i==2)
                rdbtn.setText("no " );
            else
                rdbtn.setText("attendance not taken " );
            ll.addView(rdbtn);
        }
        ((ViewGroup) findViewById(R.id.radiogroup)).addView(ll);


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
                    // Toast.makeText(Main5Activity.this,c,LENGTH_LONG).show();
                    if(c.equals("no "))
                        c1="2";
                    else if(c.equals("yes "))
                        c1="3";
                    else if(c.equals("attendance not taken "))
                        c1="4";
                    else
                        c1="5";

                    String sub=setdate(finalday,""+i);
                    SQLiteDatabase db = db1.getWritableDatabase();
                    boolean isInserted = db1.insertData11(db,s,sub,c1,""+i);
                    if (isInserted == true)
                        Toast.makeText(Main8Activity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(Main8Activity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
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
    }
}
