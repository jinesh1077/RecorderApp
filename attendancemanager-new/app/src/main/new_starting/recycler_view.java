package com.example.android.new_starting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class recycler_view extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        RecyclerView m1=( RecyclerView) findViewById(R.id.list1);
        m1.setLayoutManager(new LinearLayoutManager(this));
        String[] cha={"jkw","ddf","fdfdfd","ddvfg","fgggg","Fvvv"};
        m1.setAdapter(new recycler1(cha));
    }
}
