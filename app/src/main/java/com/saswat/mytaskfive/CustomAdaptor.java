package com.saswat.mytaskfive;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdaptor extends RecyclerView.Adapter<CustomAdaptor.MyViewHolder> {

    private Context context;
    private ArrayList user_id;
    private ArrayList user_name;
    private ArrayList user_email_address;
    private ArrayList user_phone_no;
    Activity activity;


    public CustomAdaptor(Activity activity, Context context, ArrayList<String> user_id, ArrayList<String> user_name, ArrayList<String> user_email_address, ArrayList<String> user_phone_no) {
        this.activity = activity;
        this.context = context;
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_email_address = user_email_address;
        this.user_phone_no = user_phone_no;
    }
    @NonNull
    @Override
    public CustomAdaptor.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_cell, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdaptor.MyViewHolder holder, int position) {
        holder.user_id.setText(String.valueOf(user_id.get(position)));
        holder.user_name.setText(String.valueOf(user_name.get(position)));
        holder.user_email.setText(String.valueOf(user_email_address.get(position)));
        holder.user_phone.setText(String.valueOf(user_phone_no.get(position)));

    }

    @Override
    public int getItemCount() {
        return user_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView user_id, user_name, user_email, user_phone;
        ConstraintLayout constraintLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            user_id = itemView.findViewById(R.id.user_id);
            user_name = itemView.findViewById(R.id.tv_name_value);
            user_email = itemView.findViewById(R.id.tv_email_value);
            user_phone = itemView.findViewById(R.id.tv_phone_no_value);
            constraintLayout = itemView.findViewById(R.id.constraint_layout);
        }
    }
}
