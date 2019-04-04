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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] items = new String[]{"Fruit", "Electronics", "Sports"};

        sp1=(Spinner)findViewById(R.id.sp1);
        btn1=(Button) findViewById(R.id.btn1);


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
