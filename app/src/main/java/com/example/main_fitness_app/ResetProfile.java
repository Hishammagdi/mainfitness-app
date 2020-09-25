package com.example.main_fitness_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class ResetProfile extends AppCompatActivity {
    Bundle bundle;
    DataBaseForReg db;
    Button update;
    RadioButton gender;
    RadioButton Genderf;
    TextView Age;
    TextView Height;
    TextView Weight;
    String use;
    String tFamele;
    String tmale;
    String tage;
    String height;
    String weight;

    NumberPicker PickerAge;
    NumberPicker Picker_H;
    NumberPicker Picker_W;
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
    Boolean updatedataFemale;
    Boolean updatedata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db=new DataBaseForReg(this);
        setContentView(R.layout.activity_reset_profile);

        picage=(TextView)findViewById(R.id.textage1) ;
        textyears=(TextView)findViewById(R.id.yearsText);
        PickerAge=(NumberPicker)findViewById(R.id.picker_age);
        Picker_H =(NumberPicker)findViewById(R.id.Picker_Height);
        Picker_W =(NumberPicker)findViewById(R.id.Picker_Weight);
        picHeight=(TextView)findViewById(R.id.H_P1);
        picWeight=(TextView)findViewById(R.id.W_P1);
        bundle=getIntent().getExtras();
        use=bundle.getString("UserName");
        gender=(RadioButton)findViewById(R.id.radio_male);
        Genderf=(RadioButton)findViewById(R.id.radio_female);
        Age=(TextView)findViewById(R.id.textage1);
        Height=(TextView)findViewById(R.id.H_P1);
        Weight=(TextView)findViewById(R.id.W_P1);



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
        picage.setText(getIntent().getExtras().getString("Age"));

        picWeight.setText(getIntent().getExtras().getString("Weight"));




        picHeight.setText(getIntent().getExtras().getString("Height"));

        update=(Button)findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String     hheight=picHeight.getText().toString().trim();
                String    wweight=picWeight.getText().toString().trim();
                String    ttage=picage.getText().toString().trim();
                String    ttmale=gender.getText().toString().trim();
                String    ttFamele=Genderf.getText().toString().trim();
                System.out.println(use);
                System.out.println("Ageis"+ttage);
                System.out.println("hheight"+hheight);
                System.out.println("wwheight"+wweight);
                System.out.println("gender"+ttmale);
                System.out.println("female"+ttFamele);




                if(gender.isChecked()){

                    updatedata=db.UpdateData(use,picWeight.getText().toString(),picHeight.getText().toString(),picage.getText().toString(),ttmale);
                    if(updatedata==true){
                        Toast.makeText(getApplicationContext(),"Update Successfully",Toast.LENGTH_LONG).show();
                        Intent Showpro=new Intent(ResetProfile.this,ShowProfile.class);
                        Showpro.putExtras(bundle);
                        startActivity(Showpro);
                    }
                }
                else  if(Genderf.isChecked()){

                    updatedataFemale=db.UpdateData(use,wweight,hheight,ttage,ttFamele);
                    if(updatedataFemale==true) {
                        Toast.makeText(getApplicationContext(), "Update Successfully", Toast.LENGTH_LONG).show();
                        Intent Showpro=new Intent(ResetProfile.this,ShowProfile.class);
                        Showpro.putExtras(bundle);
                        startActivity(Showpro);
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Update Error",Toast.LENGTH_LONG).show();
                }

            }
        });



    }
}
