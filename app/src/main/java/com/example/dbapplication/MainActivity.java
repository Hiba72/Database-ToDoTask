package com.example.dbapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public boolean isShow=false;
    Button buttonAdd, buttonViewAll,buttondel;
    EditText editName, editAge;
    Switch switchIsActive;
   // ListView listViewStudent;
    Intent intent;
    List<StudentModel> list;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttondel=findViewById(R.id.buttond);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonViewAll = findViewById(R.id.buttonViewAll);
        editName = findViewById(R.id.editTextName);
        editAge = findViewById(R.id.editTextAge);
        switchIsActive = findViewById(R.id.switchStudent);
       // listViewStudent = findViewById(R.id.listViewStudent);
buttonAdd.setOnClickListener(new View.OnClickListener() {
    StudentModel studentModel;

    @Override
    public void onClick(View v) {
        try {
            studentModel = new StudentModel(editName.getText().toString(), Integer.parseInt(editAge.getText().toString()), switchIsActive.isChecked());
            Toast.makeText(MainActivity.this, studentModel.toString(), Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
        }
        dbhelper dbHelper = new dbhelper(MainActivity.this);
        dbHelper.addStudent(studentModel);
    }
});
buttonViewAll.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        dbhelper dbHelper = new dbhelper(MainActivity.this);
        list = dbHelper.getAllStudents();
        recyclerView = findViewById(R.id.myRecyclerView);

        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new myRecyclerViewAdatpter(MainActivity.this,list);
        recyclerView.setAdapter(adapter);
        isShow=true;

    }
});

//if(isShow) {
//    buttondel.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {

//            String n;
//             dbhelper dbHelper = new dbhelper(MainActivity.this);
//             n=dbHelper.deleteRecord();
//txt.setText(n);
//            list = dbHelper.getAllStudents();
//            recyclerView = findViewById(R.id.myRecyclerView);
//
//            recyclerView.setHasFixedSize(true);
//            layoutManager=new LinearLayoutManager(MainActivity.this);
//            recyclerView.setLayoutManager(layoutManager);
//            adapter = new myRecyclerViewAdatpter(list);
//            recyclerView.setAdapter(adapter);
    //    }
  //  });
//}
//buttondel.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View v) {
//   //     dbhelper dbHelper = new dbhelper(MainActivity.this);
//      //  dbHelper.deleteRecord();
//    }
//});
    }
//    public void delRow(){
//        dbhelper dbHelper = new dbhelper(MainActivity.this);
//        dbHelper.deleteRecord();
////        list = dbHelper.getAllStudents();
////            recyclerView = findViewById(R.id.myRecyclerView);
////
////            recyclerView.setHasFixedSize(true);
////            layoutManager=new LinearLayoutManager(MainActivity.this);
////            recyclerView.setLayoutManager(layoutManager);
////            adapter = new myRecyclerViewAdatpter(list);
////            recyclerView.setAdapter(adapter);
//    }
}