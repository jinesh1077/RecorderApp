package com.jin10.shopping;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Checkout extends AppCompatActivity {


    private ListView listView1,listView2,listView3;
    private MyDbHandler dbHandler;
    private Button clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);


        listView1 =(ListView)findViewById(R.id.listView1);
        dbHandler = new MyDbHandler(this,null,null,1);
        clear=(Button) findViewById(R.id._clear);
        String[] result1 ={"apple"};
        String[] result2 ={"3"};
        String[] result3 ={"150"};
       // ArrayList<String> result1=dbHandler.getValY("_name");
        //ArrayList<String> result2=dbHandler.getValY("_quant");
        //ArrayList<String> result3=dbHandler.getValY("_price");





        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(Checkout.this,R.layout.list_design,R.id.itemName,result1);
        listView1.setAdapter(adapter1);


        listView2 =(ListView)findViewById(R.id.listView2);


        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(Checkout.this,R.layout.list_design,R.id.itemName,result2);
        listView2.setAdapter(adapter2);



        listView3 =(ListView)findViewById(R.id.listView3);


        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(Checkout.this,R.layout.list_design,R.id.itemName,result3);
        listView3.setAdapter(adapter3);


        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHandler.del();
            }
        });



    }
}
