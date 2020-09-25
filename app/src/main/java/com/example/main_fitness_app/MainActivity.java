package com.example.main_fitness_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String user;
    String pass;
    DataBaseForReg DBR;
    EditText textUser;
    EditText textpass;
    Bundle bu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textUser=(EditText)findViewById(R.id.username);
        textpass   =(EditText)findViewById(R.id.password);
        Button btnLogin=(Button)findViewById(R.id.btu_login);
        DBR=new DataBaseForReg(this);
        bu=new Bundle();


        Button btnReg=(Button)findViewById(R.id.btu_register);
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Mainintent=new Intent(MainActivity.this,Register.class);
                startActivity(Mainintent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user=textUser.getText().toString().trim();
                pass=textpass.getText().toString().trim();
                Boolean check=DBR.CheckUser(user,pass);
                if(check==true){
                    bu.putString("UserName",user);
                    Toast.makeText(getApplicationContext(),"Successfully Logged in",Toast.LENGTH_LONG).show();
                    Intent LoginActivity= new Intent(MainActivity.this,BodyCheck.class);
                    LoginActivity.putExtras(bu);
                    startActivity(LoginActivity);

                }
                else{

                    Toast.makeText(getApplicationContext(),"Error in Logged in",Toast.LENGTH_LONG).show();
                }




            }
        });
    }
}
