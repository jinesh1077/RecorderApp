package com.jin10.justalk;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {

    private EditText email,password;
    private Button btn_register;

    //private FirebaseAuth auth;
   // private DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        btn_register=(Button)findViewById(R.id.btn_register);

/*        auth= FirebaseAuth.getInstance();

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _email = email.getText().toString();
                String _password = password.getText().toString();

                if(TextUtils.isEmpty(_email)||TextUtils.isEmpty(_password)){
                    Toast.makeText(LoginActivity.this,"ALL FIELDS ARE REQUIERD",Toast.LENGTH_SHORT).show();
                }else{
                    auth.signInWithEmailAndPassword(_email,_password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if(task.isSuccessful()){

                                        Intent i = new Intent(LoginActivity.this,MainActivity.class);
                                        i.addFlags(i.FLAG_ACTIVITY_CLEAR_TASK|i.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(i);
                                        finish();

                                    }else{
                                        Toast.makeText(LoginActivity.this,"Auth Error",Toast.LENGTH_SHORT).show();

                                    }

                                }
                            });
                }



            }
        });
*/
    }
}
