package com.example.main_fitness_app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.security.AccessController.getContext;

public class finishmeal extends AppCompatActivity {
    private RecyclerView recyclerView ;
    static HashMap<String, Integer> calories = new HashMap<String, Integer>();
    static List<String> foodname = new ArrayList<>(),
            serving= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foodfinish);
        recyclerView = findViewById(R.id.finishrc);
        fileAdapter adapter = new fileAdapter(finishmeal.this,foodname,calories,serving,1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }
}
