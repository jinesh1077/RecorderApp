package com.jin10.shopping;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.HashMap;

public class ListActivity extends AppCompatActivity {

    private HashMap<String,String[]> hash;
    private MyDbHandler dbHandler;
    private ListView listView;
    private String st1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent i=getIntent();
        String st=getIntent().getStringExtra("sp1");
        st1 =st;
        //Toast.makeText(this, ""+st, Toast.LENGTH_SHORT).show();
        dbHandler = new MyDbHandler(this,null,null,1);


        hash = new HashMap<>();
        hash.put("Fruit", new String[]{"Banana", "Apple"});
        hash.put("Vegetable",new String[]{"Potato","Tomato"});
        hash.put("FastFood",new String[]{"Wafer", "Biscuit"});

        String[] result = hash.get(st);

        //dbHandler = new MyDbHandler(this,null,null,1);
        listView =(ListView)findViewById(R.id.listView);


        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(ListActivity.this,R.layout.list_design,R.id.itemName,result);
        listView.setAdapter(adapter1);




    }

    public void onSelected(View view){

        //String val= sp1.getSelectedItem().toString();
        Button btn=(Button)view.findViewById(R.id.itemName);
        String st2=btn.getText().toString();
        Intent i=new Intent(ListActivity.this,ItemAdd.class);
        i.putExtra("sp1",st1+"");
        i.putExtra("sp2",st2+"");
        startActivity(i);

    }


}
