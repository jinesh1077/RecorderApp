package com.jin10.shopping;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Spinner sp1;
    private Button btn1;
    private MyDbHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHandler = new MyDbHandler(this,null,null,1);
        if(false) {
            dbHandler.addData("Banana", "10", "Banana is Really Healthy Food and Good for to keep Energy", "Fruit");
            dbHandler.addData("Apple", "50", "Apple is Really Healthy Food.Take One Everyday", "Fruit");
            dbHandler.addData("Potato", "20", "Potato is use to gain fat.", "Vegetable");
            dbHandler.addData("Tomato", "30", "Tomato is use to gain Blood.", "Vegetable");
            dbHandler.addData("Wafer", "10", "Wafer,Crunchy and Sweet ", "FastFood");
            dbHandler.addData("Biscuit", "15", "Biscuit,Chocolaty and Creamy ", "FastFood");
        }
        dbHandler.del();
        //String[] str =dbHandler.getVal("Banana","Fruit");;

        //Toast.makeText(this,str[0]+""+str[1],Toast.LENGTH_SHORT).show();;

        sp1=(Spinner)findViewById(R.id.sp1);
        btn1=(Button) findViewById(R.id.btn1);
        String[] items ={"Fruit","Vegetable","FastFood"};


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        sp1.setAdapter(adapter);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String val= sp1.getSelectedItem().toString();
                Intent i=new Intent(MainActivity.this,ListActivity.class);
                i.putExtra("sp1",val+"");
                startActivity(i);
            }
        });





    }
}
