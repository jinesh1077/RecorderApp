package com.example.android.new_starting;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class Main7Activity extends AppCompatActivity {
    StarbuzzDatabaseHelper db1=new StarbuzzDatabaseHelper(this);
    long  a[]={0,0};
    String attended[]={"attended","not attended"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        checkatt();
    }
    public void checkatt()
    {
        long c=0,c4=0;

        LinearLayout l1=(LinearLayout)findViewById(R.id.ll1);
        SQLiteDatabase db = db1.getReadableDatabase();
        try{c=db1.subjecttotal(db,"dc");


        }

        catch (Exception E)
        {
            Toast.makeText(this,""+E,Toast.LENGTH_LONG).show();
        }
        String c1=""+c;
        //SQLiteDatabase db = db1.getReadableDatabase();
        Cursor res = db1.findallsubjectsin(db);
        if(res.getCount()==0) {
            c = 0;
            c1=c1+" "+c;
        }


        else{
            int finalv=res.getCount();
            int j=0;
            while (res.moveToNext()) {
                j++;

                TextView e1=new TextView(Main7Activity.this);
                e1.setId(j+100);


                LinearLayout.LayoutParams b1 = new LinearLayout.LayoutParams(800, LinearLayout.LayoutParams.WRAP_CONTENT,250);
                e1.setLayoutParams(b1);
                e1.setTextSize(24);
                e1.setTextColor(Color.parseColor("#ffffba"));
                l1.addView(e1);
                b1.setMargins(0, 0, 0, 8);

                e1.setText(res.getString(0));


                c= db1.subjecttotal(db,res.getString(0));
                c4=db1.subattnottaken(db,res.getString(0));

                long c3=db1.subjectattended(db,res.getString(0));
                try{

                    double c9=(double)c-c4;
                    PieChart c12=new PieChart(Main7Activity.this);
                    a[0]=(long)(((double)(c3)/(c9))*100);
                    a[1]=(long)(((double)(c-c3-c4)/c9)*100);
                    LinearLayout.LayoutParams b12 = new LinearLayout.LayoutParams(800, LinearLayout.LayoutParams.WRAP_CONTENT);
                    c12.setLayoutParams(b12);
                    l1.addView(c12);
                    c12.setId(j+200);
                    if((a[0]+a[1])!=0)
                    {setuppie(j);
                        c12.setRotationAngle(90);
                        c12.setRotationEnabled(true);
                        c12.getLayoutParams().height = 700;
                        c12.getLayoutParams().width = 900;}
                    //LinearLayout.LayoutParams b2 = new LinearLayout.LayoutParams(800, LinearLayout.LayoutParams.WRAP_CONTENT,250);
                    double c6=0;
                    if(c!=0)
                    c6=(double)c3/((double)(c-c4));
                    else
                        c6=0;
                    Toast.makeText(this,""+c6,Toast.LENGTH_LONG).show();

                    if(c6<0.75) {
                        TextView e2 = new TextView(Main7Activity.this);
                        e2.setId(j + 300);
                        e2.setLayoutParams(b1);
                        e2.setTextSize(20);
                        e2.setTextColor(Color.parseColor("#ffffba"));
                        l1.addView(e2);
                        b1.setMargins(0, 0, 0, 8);
                        double c7=3*c-3*c4-4*c3;
                        int c8=(int)c7;
                        if(c7!=0)
                        if(c8==c7)
                        e2.setText("Attend next:"+c8+" lecture");
                        else
                        {
                            c8=c8+1;e2.setText("Attend next:"+c8+" lecture");

                        }
                        else
                        {
                            c8=c8+1;e2.setText("Attend next:"+c8+" lecture");
                        }
                    }
                    else
                    {
                        c6=(double)c3/((double)(c-c4+1));
                        if(c6>0.75)
                        {
                            TextView e2 = new TextView(Main7Activity.this);
                            e2.setId(j + 300);
                            e2.setLayoutParams(b1);
                            e2.setTextSize(20);
                            l1.addView(e2);
                            b1.setMargins(0, 0, 0, 8);
                            int q;
                            for(q=1;q<c-c4;q++)
                            {
                                double c7=(double)c3/((double)(c-c4+q));
                                if(c7<0.75)
                                    break;
                            }

                            q=q-1;
                            e2.setTextColor(Color.parseColor("#ffffba"));
                            e2.setText("You can bunk next "+q+" lecture");

                        }
                    }

                }
                catch (Exception E)
                {
                    Toast.makeText(this,""+E,Toast.LENGTH_LONG).show();
                }

                //e1.setText(""+c+" "+c3);
                c1=c1+" "+res.getString(0)+c;
                Toast.makeText(this,""+res.getString(0),Toast.LENGTH_LONG).show();

            }}


        c=db1.check(db);
        c1=c1+" "+c;

        try{c=db1.subjectattended(db,"dc");
        }
        catch (Exception E)
        {
            Toast.makeText(this,""+E,Toast.LENGTH_LONG).show();
        }
        c1=c1+" "+ c;


    }
    private void setuppie(int j){


        List<PieEntry> pieE=new ArrayList<>();
        for(int i=0;i<a.length;i++)
        {

            pieE.add(new PieEntry(a[i],attended[i]));
            //Toast.makeText(Main7Activity.this,""+a[i],Toast.LENGTH_LONG).show();
        }
        PieDataSet dataSet= new PieDataSet(pieE,"");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data=new PieData(dataSet);
        PieChart chart = (PieChart)findViewById(j+200);
        chart.setData(data);
        chart.invalidate();

    }
}
