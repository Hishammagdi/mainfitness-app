package com.example.main_fitness_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class Register extends AppCompatActivity {

    EditText textUsername;
    EditText textPass;
    EditText textCnpass;
    EditText textEmail;
    String Unamme;
    String tpass;
    String tEmail;
    String tCpass;
    Bundle bu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        textEmail=(EditText)findViewById(R.id.Email);
        textUsername=(EditText)findViewById(R.id.user);
        textPass=(EditText)findViewById(R.id._password);
        textCnpass=(EditText)findViewById(R.id.ConfirmPassword);



        bu=new Bundle();
        Button buttonnext=(Button)findViewById(R.id.btu_next);
        buttonnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tpass=textPass.getText().toString().trim();
                tCpass=textCnpass.getText().toString().trim();
                tEmail=textEmail.getText().toString().trim();
                Unamme =textUsername.getText().toString().trim();

                bu.putString("UserName",Unamme);
                bu.putString("Email",tEmail);
                bu.putString("Password",tpass);
                bu.putString("ConfirmPassword",tCpass);
                Intent RegisterActivity= new Intent(Register.this,NextRegister.class);
                RegisterActivity.putExtras(bu);
                startActivity(RegisterActivity);
            }
        });
    }
}
