package com.example.main_fitness_app;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class fileAdapter extends RecyclerView.Adapter<fileAdapter.ViewHolder> {
    private List<String> foodname,serving;
    private HashMap<String, Integer> calories;
    private LayoutInflater inflater;
    private ArrayList<Integer> checkeditems = new ArrayList<>();
    private Context context ;
    private int bfcaloriesnumber=0 ,dicaloriesnumber=0 ,sncaloriesnumber=0 ,lucaloriesnumber=0 , category  ;
    public static double totalcal ;
    private int brtotal , ditotal ,sntotal , lutotal ;
TextView caltv ;
    public fileAdapter(Context context, List<String> foodname, HashMap<String, Integer> calories, List<String> serving ,int category){
        this.foodname = foodname;
        this.calories = calories;
        this.serving = serving;
        this.context = context ;
        this.category = category;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = inflater.inflate(R.layout.customfoodlayout,parent,false);
        generatecalories();
        caltv = view.findViewById(R.id.c);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        String food_name = foodname.get(position);
        String cal = String.valueOf(calories.get(food_name));
        String serv = serving.get(position);

        foodmain.totalcal.setText("Your total calories are "+ totalcal);
        holder.food_nametv.setText(food_name);
        holder.caltv.setText(cal + " calories");
        holder.servtv.setText(serv);
        System.out.println("checked items is --->" +checkeditems);

        for(int i=0 ; i< checkeditems.size(); i++) {
            if (position != checkeditems.get(i)) {
                holder.cardView.setBackgroundColor(Color.parseColor("#2bA58C"));
            } else {
                holder.cardView.setBackgroundColor(Color.parseColor("#084539"));
                break;
            }
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkeditems.contains(position)){
                    System.out.println("yu deleted" + holder.food_nametv.getText());
                    remove(position);
                    holder.cardView.setBackgroundColor(Color.parseColor("#2bA58C"));
                }
               else if (!holder.cardView.getCardBackgroundColor().equals(Color.parseColor("#2bA58C"))) {
                    switch (category) {
                        case 0 :
                            sncaloriesnumber = action(holder, sncaloriesnumber, sntotal);
                            foodmain.c.setText("you selected " +sncaloriesnumber +" out from " + sntotal );
                            break ;
                        case 1 :
                            bfcaloriesnumber=  action(holder, bfcaloriesnumber,brtotal );
                            foodmain.c.setText("you selected " +bfcaloriesnumber +" out from " + brtotal );

                            break;
                        case 2 :
                            dicaloriesnumber= action(holder, dicaloriesnumber ,ditotal);
                            foodmain.c.setText("you selected " +dicaloriesnumber +" out from " + ditotal );

                            break;
                        case 3:
                            lucaloriesnumber= action(holder, lucaloriesnumber,lutotal );
                            foodmain.c.setText("you selected " +lucaloriesnumber +" out from " + lutotal );

                            break;
                    }


                }
            }

        });

    }
    private void remove(int position){
        for(int i=0 ; i< checkeditems.size();i++){
            if(position == checkeditems.get(i)){
                checkeditems.remove(i);
                break;
            }

        }

    }
private int  action( ViewHolder holder , int calorie , int total ){

    if (calorie + calories.get(holder.food_nametv.getText()) <= total) {
            checkeditems.add(holder.getAdapterPosition());
            holder.cardView.setBackgroundColor(Color.parseColor("#084539"));
            calorie += calories.get(holder.food_nametv.getText());

    } else {
        Toast.makeText(context.getApplicationContext(), "Sorry you achieved your calories limit", Toast.LENGTH_LONG).show();
    }
    return calorie ;


}
    @Override
    public int getItemCount() {
        return foodname.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView food_nametv,caltv,servtv;
        CardView cardView ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            food_nametv = itemView.findViewById(R.id.checkedTextView);
            caltv = itemView.findViewById(R.id.calories);
            servtv = itemView.findViewById(R.id.serving);
            cardView = itemView.findViewById(R.id.cardview);
        }
    }
private void generatecalories(){
        ditotal = (int)(totalcal/8);
        sntotal = (int)(totalcal/8);
        lutotal= (int)(totalcal/2);
        brtotal=(int)(totalcal/4);

}

}