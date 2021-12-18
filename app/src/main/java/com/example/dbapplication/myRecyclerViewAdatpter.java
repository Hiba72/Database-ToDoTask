package com.example.dbapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class myRecyclerViewAdatpter extends RecyclerView.Adapter<myRecyclerViewAdatpter.MyViewHolder> {
    List<StudentModel> list;
   public static String name;

    public myRecyclerViewAdatpter(List<StudentModel> list) {
        this.list=list;
    }

    @NonNull
    @Override
    public myRecyclerViewAdatpter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.singlerecorddesign, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull myRecyclerViewAdatpter.MyViewHolder holder, int position) {
        holder.data=list.get(position);
        holder.stdName.setText(holder.data.getName());
        name=holder.data.getName();
        holder.stdAge.setText(String.valueOf(holder.data.getAge()));
        if(holder.data.isActive()) {
            holder.stdStatus.setText("true");
        }
        else{
            holder.stdStatus.setText("false");

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView stdName;
        TextView stdAge;
        TextView stdStatus;
StudentModel data;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            stdName = itemView.findViewById(R.id.studentName);
            stdAge = itemView.findViewById(R.id.studentAge);
            stdStatus = itemView.findViewById(R.id.studentStatus);
        }
    }
}
