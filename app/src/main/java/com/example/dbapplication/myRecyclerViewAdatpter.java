package com.example.dbapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class myRecyclerViewAdatpter extends RecyclerView.Adapter<myRecyclerViewAdatpter.MyViewHolder> {
    List<StudentModel> list;
   public static String name;
    private static Context sContext;

    public myRecyclerViewAdatpter(Context context,List<StudentModel> list) {
        sContext = context;
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
       holder.btndel.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               removeItem(holder.getAdapterPosition());
               dbhelper dbHelper = new dbhelper(sContext);
               dbHelper.deleteRecord(holder.getAdapterPosition());
             //  MainActivity mainActivity= new MainActivity();
              // mainActivity.delRow(position);
           }
       });
    }
    public void onClick(View view){
        MyViewHolder holder = (MyViewHolder) view.getTag();
        if (view.getId() == holder.stdName.getId()) {
            Toast.makeText(sContext, holder.stdName.getText(), Toast.LENGTH_SHORT).show();
        }
    }
    public boolean onLongClick(View view) {
        MyViewHolder holder = (MyViewHolder) view.getTag();
        if (view.getId() == holder.stdName.getId()) {
            list.remove(holder.getAdapterPosition());

            notifyDataSetChanged();

            Toast.makeText(sContext, "Item " + holder.stdName.getText() + " has been removed from list",
                    Toast.LENGTH_SHORT).show();
        }
        return false;
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView stdName;
        TextView stdAge;
        TextView stdStatus;
        Button btndel;
StudentModel data;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            stdName = itemView.findViewById(R.id.studentName);
            stdAge = itemView.findViewById(R.id.studentAge);
            stdStatus = itemView.findViewById(R.id.studentStatus);
            btndel=itemView.findViewById(R.id.buttond);
//            btndel.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//onLongClick(v);
////                    MainActivity mainActivity= new MainActivity();
////                    mainActivity.delRow();
//                  //  mainActivity.txt.setText("hiba");
//                }
//            });
        }
    }
    private void removeItem(int position) {
        list.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, list.size());
    }
}
