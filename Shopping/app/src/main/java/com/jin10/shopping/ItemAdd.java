package com.jin10.shopping;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ItemAdd extends AppCompatActivity {

    private Button add,addCart,minus,checkout;
    private TextView cart,name,price,detail;
    private MyDbHandler dbHandler;
    private String st1,st2;
    private ImageView img,qr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_add);

        dbHandler = new MyDbHandler(this,null,null,1);
        Intent i=getIntent();
        st1=getIntent().getStringExtra("sp1");
        st2=getIntent().getStringExtra("sp2");
        //Toast.makeText(this, ""+st, Toast.LENGTH_SHORT).show();
        String[] str = dbHandler.getVal(st2,st1);


        add=(Button) findViewById(R.id._plus);
        minus=(Button) findViewById(R.id._minus);
        addCart=(Button) findViewById(R.id._add);
        checkout=(Button) findViewById(R.id._checkout);
        cart=(TextView) findViewById(R.id._cart);
        name=(TextView) findViewById(R.id._name);
        price=(TextView) findViewById(R.id._price);
        detail=(TextView) findViewById(R.id._detail);
        img=(ImageView) findViewById(R.id.img);
        qr=(ImageView) findViewById(R.id.qr);




        name.setText(st2);
        price.setText(str[0]);
        detail.setText(str[1]);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String q1=cart.getText().toString();
                int k= Integer.parseInt(q1);
                k++;
                //Toast.makeText(getApplicationContext(), "hello", Toast.LENGTH_SHORT).show();
                cart.setText(String.valueOf(k));
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String q1=cart.getText().toString();
                int k= Integer.parseInt(q1);
                k--;
                if(k<=0){
                    k=0;
                }
                cart.setText(String.valueOf(k));
            }
        });


        addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // dbHandler.delData(st2,"Cart");



                String q1= price.getText().toString();
                String q2= cart.getText().toString();
                int k=(Integer.parseInt(q1))*(Integer.parseInt(q2));
                dbHandler.addDataQ(st2,String.valueOf(k),cart.getText().toString());
            }
        });

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(ItemAdd.this,Checkout.class);
                //i.putExtra("","3")
                startActivity(i);

            }
        });






    }
}
