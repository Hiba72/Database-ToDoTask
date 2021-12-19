package com.example.dbapplication;

import android.content.Context;
import android.content.Intent;
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
   Intent intent;
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
               dbHelper.deleteRecord(holder.data.getName());
           }
       });
        holder.btnupd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context contextt=v.getContext();
                 intent=new Intent(contextt.getApplicationContext(),MainActivity2.class);
                 intent.putExtra("value",String.valueOf(holder.data.getName()));
                contextt.startActivity(intent);

            }
        });
//        holder.btnupd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//             MainActivity mainActivity=new MainActivity();
//             mainActivity.changeActivity();
//             //   holder.data=list.get(holder.getAdapterPosition());
//
//            //    MainActivity mainActivity=new MainActivity();
//              //  mainActivity.update(holder.data.getName(),String.valueOf(holder.data.getAge()),String.valueOf(holder.data.isActive()));
//
////                holder.stdName.setText(holder.data.getName());
////                holder.stdAge.setText(String.valueOf(holder.data.getAge()));
////                if(holder.data.isActive()) {
////                    holder.stdStatus.setText("true");
////                }
////                else{
////                    holder.stdStatus.setText("false");
////
////                }
//
////                dbhelper dbHelper = new dbhelper(sContext);
////                notifyItemChanged(holder.getAdapterPosition());
////                dbHelper.updateRecord(String.valueOf(holder.stdName),String.valueOf(holder.stdAge),String.valueOf(holder.stdStatus),holder.data.getName());
//
//            }
//        });
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
        Button btnupd;
        StudentModel data;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            stdName = itemView.findViewById(R.id.studentName);
            stdAge = itemView.findViewById(R.id.studentAge);
            stdStatus = itemView.findViewById(R.id.studentStatus);
            btndel=itemView.findViewById(R.id.buttond);
            btnupd=itemView.findViewById(R.id.btnUpdate);

        }

    }
    private void removeItem(int position) {
        list.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, list.size());
    }
}
