package com.jin10.shopping;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.HashMap;

public class ListActivity extends AppCompatActivity {

    private HashMap<String,String[]> hash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent i=getIntent();
        String st=getIntent().getStringExtra("sp1");
        Toast.makeText(this, ""+st, Toast.LENGTH_SHORT).show();

        hash = new HashMap<>();
        hash.put("Fruit", new String[]{"Banana", "Apple","Mango"});
        hash.put("Electronics",new String[]{"Bulb"});
        hash.put("Sports",new String[]{"Football", "BasketBall"});

        String[] result = hash.get(st);




    }
}
