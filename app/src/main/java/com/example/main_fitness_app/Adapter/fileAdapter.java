package com.example.main_fitness_app.Adapter;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.main_fitness_app.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class fileAdapter extends RecyclerView.Adapter<fileAdapter.ViewHolder> {
    private List<String> foodname,serving;
    private HashMap<String, Integer> calories;
    private LayoutInflater inflater;
    private ArrayList<Integer> checkeditems = new ArrayList<>();
    private Context context ;
    private int bfcaloriesnumber=0 ,dicaloriesnumber=0 ,sncaloriesnumber=0 ,lucaloriesnumber=0 , category , totalamountofcal ;

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

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        String food_name = foodname.get(position);
        String cal = String.valueOf(calories.get(food_name));
        String serv = serving.get(position);

        holder.food_nametv.setText(food_name);
        holder.caltv.setText(cal + " calories");
        holder.servtv.setText(serv);

        for(int i=0 ; i< checkeditems.size() ; i++){
            System.out.println("selected items" + checkeditems.get(i));
        }

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
                if (!holder.cardView.getCardBackgroundColor().equals(Color.parseColor("#2bA58C"))) {
                    switch (category) {
                        case 1:
                            bfcaloriesnumber=  action(holder, bfcaloriesnumber );
                           // TextView total = view.findViewById(R.id.totalcal);
                             //total.setText(String.valueOf(bfcaloriesnumber));
                            break;
                        case 2:
                            dicaloriesnumber= action(holder, dicaloriesnumber );
                            break;
                        case 3:
                            lucaloriesnumber= action(holder, lucaloriesnumber );
                            break;
                        case 0:
                            sncaloriesnumber = action(holder, sncaloriesnumber );
                           break;
                    }


                }

            }

        });

    }
private int  action( ViewHolder holder , int calorie ){

    if (calorie + calories.get(holder.food_nametv.getText()) <= 200) {
        System.out.println("holder posithion --->" + holder.getAdapterPosition());
            checkeditems.add(holder.getAdapterPosition());
            holder.cardView.setBackgroundColor(Color.parseColor("#084539"));
            System.out.println("you choosed from bf---> " + holder.food_nametv.getText() + " with  ---> " + holder.caltv.getText() );
            calorie += calories.get(holder.food_nametv.getText());

    } else {
        System.out.println("sorry max numberof cal");
    }
    System.out.println("total become" + calorie);
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

    public int getBfcaloriesnumber() {
        return bfcaloriesnumber;
    }

    public int getDicaloriesnumber() {
        return dicaloriesnumber;
    }

    public int getSncaloriesnumber() {
        return sncaloriesnumber;
    }

    public int getLucaloriesnumber() {
        return lucaloriesnumber;
    }
}