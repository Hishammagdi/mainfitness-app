package com.example.main_fitness_app;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.main_fitness_app.Breakfast;
import com.example.main_fitness_app.Dinner;
import com.example.main_fitness_app.Lunch;
import com.example.main_fitness_app.Snackes;

public class MainAdapterPager extends FragmentStatePagerAdapter {
public static final int count = 4 ;

    public MainAdapterPager( FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
       switch (position)
       {
           case 0:
               System.out.println("inbrek");
               return Breakfast.getInstance();
           case 1:
               System.out.println("insnaks");
               return Snackes.getInstance();
           case 2:
               System.out.println("inlunch");
               return Lunch.getInstance();
           case 3:
               System.out.println("indinner");
               return Dinner.getInstance();
       }
 return null;
    }

    @Override
    public int getCount() {
        return count;
    }
}
