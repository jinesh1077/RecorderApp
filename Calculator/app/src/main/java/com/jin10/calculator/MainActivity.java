package com.jin10.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView firstView,secondView,thirdView;
    private Button btn[];
    private Button plus,minus,mult,div,equal,back,ac,btnDot;
    private int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn=new Button[10];
        btnDot=(Button)findViewById(R.id.btnDot);
        plus=(Button)findViewById(R.id.plus);
        minus=(Button)findViewById(R.id.minus);
        mult=(Button)findViewById(R.id.mult);
        div=(Button)findViewById(R.id.div);
        equal=(Button)findViewById(R.id.equal);
        back=(Button)findViewById(R.id.btnBack);
        ac=(Button)findViewById(R.id.btnAC);


        btn[0]=(Button) findViewById(R.id.btn0);
        btn[1]=(Button) findViewById(R.id.btn1);
        btn[2]=(Button) findViewById(R.id.btn2);
        btn[3]=(Button) findViewById(R.id.btn3);
        btn[4]=(Button) findViewById(R.id.btn4);
        btn[5]=(Button) findViewById(R.id.btn5);
        btn[6]=(Button) findViewById(R.id.btn6);
        btn[7]=(Button) findViewById(R.id.btn7);
        btn[8]=(Button) findViewById(R.id.btn8);
        btn[9]=(Button) findViewById(R.id.btn9);

        firstView=(TextView)findViewById(R.id.firstView);
        secondView=(TextView)findViewById(R.id.secondView);
        thirdView=(TextView)findViewById(R.id.thirdView);

        for(i=0;i<10;i++){
            final int j=i;
            btn[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String tmp=firstView.getText().toString();
                    if(tmp.equals("0")){
                        tmp= String.valueOf(j);
                    }else{
                        tmp+=String.valueOf(j);
                    }
                    firstView.setText(tmp);
                }
            });

        }

        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tmp=firstView.getText().toString();
                if(tmp.equals("0")){
                    tmp= String.valueOf("0.");
                }else if(tmp.contains(".")){

                }else{
                    tmp+=String.valueOf(".");
                }
                firstView.setText(tmp);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tmp=firstView.getText().toString();
                if(tmp.equals("0")){

                }else{

                    if(tmp.length()==1){
                        tmp="0";
                    }else {
                        tmp = tmp.substring(0, tmp.length() - 1);
                    }
                }
                firstView.setText(tmp);
            }
        });


        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String tmp=firstView.getText().toString();
                String tmp2=secondView.getText().toString();
                String tmp3=thirdView.getText().toString();

                if(tmp2==""){

                }else{

                    if(tmp3.equals("+")){
                        tmp= String.valueOf(Double.parseDouble(tmp2)+Double.parseDouble(tmp));
                        firstView.setText(tmp);
                        secondView.setText("");
                        thirdView.setText("");
                    }else if(tmp3.equals("-")){
                        tmp= String.valueOf(Double.parseDouble(tmp2)-Double.parseDouble(tmp));
                        firstView.setText(tmp);
                        secondView.setText("");
                        thirdView.setText("");
                    }else if(tmp3.equals("*")){
                        tmp= String.valueOf(Double.parseDouble(tmp2)*Double.parseDouble(tmp));
                        firstView.setText(tmp);
                        secondView.setText("");
                        thirdView.setText("");
                    }else if(tmp3.equals("/")){
                        if(tmp.equals("0")){
                            firstView.setText("0");
                            secondView.setText("");
                            thirdView.setText("");
                            Toast.makeText(getBaseContext(), "Cannot divide by zero", Toast.LENGTH_SHORT).show();


                        }else {
                            tmp = String.valueOf(Double.parseDouble(tmp2) / Double.parseDouble(tmp));
                            firstView.setText(tmp);
                            secondView.setText("");
                            thirdView.setText("");
                        }
                    }

                }

            }
        });


        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String tmp=firstView.getText().toString();
                String tmp2=secondView.getText().toString();
                String tmp3=thirdView.getText().toString();

                if(tmp2==""){
                    secondView.setText(tmp);
                    thirdView.setText("+");
                    firstView.setText("0");
                }else{


                    if(tmp3.equals("+")){
                        tmp= String.valueOf(Double.parseDouble(tmp2)+Double.parseDouble(tmp));
                        firstView.setText("0");
                        secondView.setText(tmp);
                        thirdView.setText("+");
                    }else if(tmp3.equals("-")){
                        tmp= String.valueOf(Double.parseDouble(tmp2)-Double.parseDouble(tmp));
                        firstView.setText("0");
                        secondView.setText(tmp);
                        thirdView.setText("+");
                    }else if(tmp3.equals("*")){
                        tmp= String.valueOf(Double.parseDouble(tmp2)*Double.parseDouble(tmp));
                        firstView.setText("0");
                        secondView.setText(tmp);
                        thirdView.setText("+");
                    }else if(tmp3.equals("/")){
                        if(tmp.equals("0")){
                            firstView.setText("0");
                            secondView.setText("");
                            thirdView.setText("");
                            Toast.makeText(getBaseContext(), "Cannot divide by zero", Toast.LENGTH_SHORT).show();


                        }else {
                            tmp = String.valueOf(Double.parseDouble(tmp2) / Double.parseDouble(tmp));
                            firstView.setText("0");
                            secondView.setText(tmp);
                            thirdView.setText("+");
                        }
                    }


                }

            }
        });


        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String tmp=firstView.getText().toString();
                String tmp2=secondView.getText().toString();
                String tmp3=thirdView.getText().toString();

                if(tmp2==""){
                    secondView.setText(tmp);
                    thirdView.setText("-");
                    firstView.setText("0");
                }else{


                    if(tmp3.equals("+")){
                        tmp= String.valueOf(Double.parseDouble(tmp2)+Double.parseDouble(tmp));
                        firstView.setText("0");
                        secondView.setText(tmp);
                        thirdView.setText("-");
                    }else if(tmp3.equals("-")){
                        tmp= String.valueOf(Double.parseDouble(tmp2)-Double.parseDouble(tmp));
                        firstView.setText("0");
                        secondView.setText(tmp);
                        thirdView.setText("-");
                    }else if(tmp3.equals("*")){
                        tmp= String.valueOf(Double.parseDouble(tmp2)*Double.parseDouble(tmp));
                        firstView.setText("0");
                        secondView.setText(tmp);
                        thirdView.setText("-");
                    }else if(tmp3.equals("/")){
                        if(tmp.equals("0")){
                            firstView.setText("0");
                            secondView.setText("");
                            thirdView.setText("");
                            Toast.makeText(getBaseContext(), "Cannot divide by zero", Toast.LENGTH_SHORT).show();


                        }else {
                            tmp = String.valueOf(Double.parseDouble(tmp2) / Double.parseDouble(tmp));
                            firstView.setText("0");
                            secondView.setText(tmp);
                            thirdView.setText("-");
                        }
                    }



                }

            }
        });


        mult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String tmp=firstView.getText().toString();
                String tmp2=secondView.getText().toString();
                String tmp3=thirdView.getText().toString();

                if(tmp2==""){
                    secondView.setText(tmp);
                    thirdView.setText("*");
                    firstView.setText("0");
                }else{


                    if(tmp3.equals("+")){
                        tmp= String.valueOf(Double.parseDouble(tmp2)+Double.parseDouble(tmp));
                        firstView.setText("0");
                        secondView.setText(tmp);
                        thirdView.setText("*");
                    }else if(tmp3.equals("-")){
                        tmp= String.valueOf(Double.parseDouble(tmp2)-Double.parseDouble(tmp));
                        firstView.setText("0");
                        secondView.setText(tmp);
                        thirdView.setText("*");
                    }else if(tmp3.equals("*")){
                        tmp= String.valueOf(Double.parseDouble(tmp2)*Double.parseDouble(tmp));
                        firstView.setText("0");
                        secondView.setText(tmp);
                        thirdView.setText("*");
                    }else if(tmp3.equals("/")){
                        if(tmp.equals("0")){
                            firstView.setText("0");
                            secondView.setText("");
                            thirdView.setText("");
                            Toast.makeText(getBaseContext(), "Cannot divide by zero", Toast.LENGTH_SHORT).show();


                        }else {
                            tmp = String.valueOf(Double.parseDouble(tmp2) / Double.parseDouble(tmp));
                            firstView.setText("0");
                            secondView.setText(tmp);
                            thirdView.setText("*");
                        }
                    }


                }

            }
        });

        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String tmp=firstView.getText().toString();
                String tmp2=secondView.getText().toString();
                String tmp3=thirdView.getText().toString();

                if(tmp2==""){
                    secondView.setText(tmp);
                    thirdView.setText("/");
                    firstView.setText("0");
                }else{


                    if(tmp3.equals("+")){
                        tmp= String.valueOf(Double.parseDouble(tmp2)+Double.parseDouble(tmp));
                        firstView.setText("0");
                        secondView.setText(tmp);
                        thirdView.setText("/");
                    }else if(tmp3.equals("-")){
                        tmp= String.valueOf(Double.parseDouble(tmp2)-Double.parseDouble(tmp));
                        firstView.setText("0");
                        secondView.setText(tmp);
                        thirdView.setText("/");
                    }else if(tmp3.equals("*")){
                        tmp= String.valueOf(Double.parseDouble(tmp2)*Double.parseDouble(tmp));
                        firstView.setText("0");
                        secondView.setText(tmp);
                        thirdView.setText("/");
                    }else if(tmp3.equals("/")){
                        if(tmp.equals("0")){
                            firstView.setText("0");
                            secondView.setText("");
                            thirdView.setText("");
                            Toast.makeText(getBaseContext(), "Cannot divide by zero", Toast.LENGTH_SHORT).show();


                        }else {
                            tmp = String.valueOf(Double.parseDouble(tmp2) / Double.parseDouble(tmp));
                            firstView.setText("0");
                            secondView.setText(tmp);
                            thirdView.setText("/");
                        }
                    }


                }

            }
        });


        ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    secondView.setText("");
                    thirdView.setText("");
                    firstView.setText("0");


            }
        });







    }
}
