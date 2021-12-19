package com.example.dbapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

public class MainActivity2 extends AppCompatActivity {
    EditText editName1, editAge1;
    Switch switchIsActive1;
    String tr;
    Intent intent,intent1;
    Button btnup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        editName1 = findViewById(R.id.editTextName2);
        editAge1 = findViewById(R.id.editTextAge2);
        switchIsActive1 = findViewById(R.id.switchStudent2);
        btnup=findViewById(R.id.Up);
        intent=getIntent();
        if(switchIsActive1.isChecked()){
            tr="true";
        }
        else{
            tr="false";
        }
        Context context=getApplicationContext();
        dbhelper dbhelper=new dbhelper(context);
//        int agg=Integer.parseInt(editAge.getText().toString());
//        String nme=editName.getText().toString();
        btnup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbhelper.updateRecord(editName1.getText().toString(),"24","true",intent.getStringExtra("value"));
                intent1=new Intent(MainActivity2.this,MainActivity.class);
                startActivity(intent1);
            }
        });
    }
}