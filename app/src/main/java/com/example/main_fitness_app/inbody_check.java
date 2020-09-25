package com.example.main_fitness_app;

public class inbody_check {

    public inbody_check() {
    }
    public double Calc_Calories(String gender , float weight, float height, int age)
    {     double DailyCalories;
        if(gender.equals("male"))
        {
            DailyCalories=(13.75*weight)+(5*height)-(6.76*age)+66;
        }
        else
        {
            DailyCalories=(9.56*weight)+(1.85*height)-(4.68*age)+655;
        }
        return DailyCalories;
    }
    public double BMI_Calc(float weight, float height)
    {
        double BMI=(weight/Math.sqrt(height));
        return BMI;
    }

}