package com.example.main_fitness_app;

import android.content.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyAdapterHolder>  {

    Context context;
    String NameTrining[];
    String time[];
    int images[];
    int position;
    View view;
    MainActivity mainActivity ;
    private  RecylerViewClickListener listener;
    public interface  OnItemClickListener{
        void OnItemClickListener(int position);
        //sasaassasaassa
    }

    public MyAdapter(Context context, String NameTrining[], String time[], int images[],RecylerViewClickListener listener){
        this.context=context;
        this.NameTrining=NameTrining;
        this.time=time;
        this.images=images;
        this.listener=listener;
        position= 0;
        view=null;
    }
    @NonNull
    @Override
    public MyAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.my_row,parent,false);
        MyAdapterHolder myAdapterHolder = new MyAdapterHolder(view,getItemCount());

        mainActivity = new MainActivity();
        myAdapterHolder.animi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("saddasdsa");
            }
        });
        return new MyAdapterHolder(view,getItemCount());
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterHolder holder, final int position) {
        //   MyAdapterHolder myAdapterHolder = new MyAdapterHolder(view ,position);
        WebView ani = null;

        holder.tx1.setText(NameTrining[position]);
        holder.tx2.setText(time[position]);
        holder.setitem(position);



        //holder.image.setImageResource(images[position]);
        //       ViewAnimation = Card[i].findViewById(R.id.web_view);

        WebSettings websettings = holder.animi.getSettings();
        String PathGif = "file:android_asset/" + "t" + position + ".gif";
        holder.animi.loadUrl(PathGif);

    }

    @Override
    public int getItemCount() {
        return NameTrining.length;
    }
    public interface RecylerViewClickListener{
        void OnClick(View view,int position);
    }
    public class MyAdapterHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tx1,tx2;
        ImageView image;
        WebView animi;
        int position11;

        public MyAdapterHolder(@NonNull View itemView,int posintion11) {
            super(itemView);
            tx1 = itemView.findViewById(R.id.textNameTrining);
            tx2 = itemView.findViewById(R.id.texttime);
            animi = itemView.findViewById(R.id.web_view);
            this.position11=posintion11;
            itemView.setOnClickListener(this);
        }

        public int getitem(){
            return position ;
        }
        public int setitem(int position1){
            return position1;
        }
        @Override
        public void onClick(View v) {
            listener.OnClick(itemView,getAdapterPosition());
        }

    }
}
