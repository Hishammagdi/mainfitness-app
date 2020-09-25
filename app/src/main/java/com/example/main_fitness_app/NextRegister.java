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
import android.widget.TextView;
import android.widget.Toast;

public class NextRegister extends AppCompatActivity {

    NumberPicker PickerAge;
    NumberPicker Picker_H;
    NumberPicker Picker_W;
    TextView Height;
    TextView Weight;
    TextView textAge;
    RadioButton male;
    RadioButton female;
    SQLiteDatabase DB;
    DataBaseForReg DR;
    SQLiteOpenHelper OpenHelper;
    TextView picWeight;
    TextView picage;
    TextView textyears;
    TextView picHeight;
    Register r;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_register);
        bundle=getIntent().getExtras();
        r=new Register();
        final String USENAME=bundle.getString("UserName");
        final String EMAIL=bundle.getString("Email");
        final String CONFIRMPASSWORD=bundle.getString("Password");
        final String PASSWORD=bundle.getString("ConfirmPassword");
        OpenHelper = new DataBaseForReg(this);
        DR = new DataBaseForReg(this);
        picage=(TextView)findViewById(R.id.textage) ;
        textyears=(TextView)findViewById(R.id.yearsText);

        PickerAge=(NumberPicker)findViewById(R.id.Picker_age);
        Picker_H =(NumberPicker)findViewById(R.id.Picker_Height);
        Picker_W =(NumberPicker)findViewById(R.id.Picker_Weight);
        picHeight=(TextView)findViewById(R.id.H_P);
        picWeight=(TextView)findViewById(R.id.W_P);
        male=(RadioButton)findViewById(R.id.radio_male);
        female=(RadioButton)findViewById(R.id.radio_female);
        Button btnreg=(Button)findViewById(R.id.btu_register);
        // textAge=(TextView)findViewById(R.id.yearsText)

        PickerAge.setMinValue(10);
        PickerAge.setMaxValue(60);
        PickerAge.setWrapSelectorWheel(true);
        PickerAge.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //   textAge.setText("Age is "+newVal);
                picage.setText(""+newVal);
            }
        });
        // PickerAge.getValue();

        Picker_H.setMinValue(130);
        Picker_H.setMaxValue(200);
        Picker_H.setWrapSelectorWheel(true);
        Picker_H.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //   textAge.setText("Age is "+newVal);
                // Picker_H.setValue(valHeight);
                picHeight.setText(""+newVal);
            }
        });

        Picker_W.setMinValue(35);
        Picker_W.setMaxValue(220);
        Picker_W.setWrapSelectorWheel(true);
        Picker_W.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //   textAge.setText("Age is "+newVal);
                picWeight.setText(""+newVal);
            }
        });
        //String cm=String.valueOf("cm");
        System.out.println("UserName is");
        System.out.println(USENAME);
        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DB=OpenHelper.getWritableDatabase();

                //height,weight
                String USER=r.Unamme;
                String Email=r.tEmail;
                String Pass=r.tpass;
                String Copass=r.tCpass;
                String height=picHeight.getText().toString().trim();
                String weight=picWeight.getText().toString().trim();

                String tage=picage.getText().toString().trim();
                String tmale=male.getText().toString().trim();
                String tFamele=female.getText().toString().trim();
                System.out.println("Name is+");
                System.out.println(USER);


                if(male.isChecked()) {


                    if (PASSWORD.equals(CONFIRMPASSWORD)) {
                        long val = DR.insertData(USENAME, PASSWORD, CONFIRMPASSWORD, EMAIL, tage, weight, height, tmale);
                        if (val > 0) {
                            Toast.makeText(getApplicationContext(), "Register is Done", Toast.LENGTH_LONG).show();
                            Intent LoginActivity = new Intent(NextRegister.this, MainActivity.class);
                            startActivity(LoginActivity);
                            //   Clear();
                        } else {
                            Toast.makeText(getApplicationContext(), "Regtration Error", Toast.LENGTH_LONG).show();
                            Clear();
                        }
                    } else {


                        Toast.makeText(getApplicationContext(), "Password is not correct", Toast.LENGTH_SHORT).show();
                    }
                }


                else if(female.isChecked()){

                    if(PASSWORD.equals(CONFIRMPASSWORD)){
                        long val=  DR.insertData(USENAME,PASSWORD,CONFIRMPASSWORD,EMAIL,tage,weight,height,tFamele);
                        if(val>0){
                            Toast.makeText(getApplicationContext(),"Register is Done",Toast.LENGTH_LONG).show();
                            Intent LoginActivity= new Intent(NextRegister.this,MainActivity.class);
                            startActivity(LoginActivity);
//                     Clear();
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Regtration Error",Toast.LENGTH_LONG).show();
                            Clear();
                        }
                    } else {


                        Toast.makeText(getApplicationContext(), "Password is not correct", Toast.LENGTH_SHORT).show();
                    }



                }




            }
        });
    }

    void Clear()
    {
        r.textUsername.getText().clear();
        //textAge.getText().clear();
        r.textEmail.getText().clear();
        r.textPass.getText().clear();
        //textFirst.getText().clear();
        //textLast.getText().clear();
        r.textCnpass.getText().clear();


    }

}








