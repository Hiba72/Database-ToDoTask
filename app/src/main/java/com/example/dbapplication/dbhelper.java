package com.example.dbapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dbhelper extends SQLiteOpenHelper {
    public static final String STUDENT_NAME = "STUDENTName";
    public static final String STUDENT_AGE = "STUDENTAge";
    public static final String ACTIVE_STUDENT = "ActiveSTUDENT";
    public static final String STUDENT_ID = "STUDENTID";
    public static final String STUDENT_TABLE = "StudentTable";


    public dbhelper(@Nullable Context context) {
        super(context, "studentDB.db", null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + STUDENT_TABLE + "(" + STUDENT_ID + " Integer PRIMARY KEY AUTOINCREMENT, " + STUDENT_NAME + " Text, " + STUDENT_AGE + " Int, " + ACTIVE_STUDENT + " BOOL) ";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + STUDENT_TABLE);
        onCreate(db);
    }
    public void  addStudent(StudentModel STUDENTModel){
        SQLiteDatabase db = this.getWritableDatabase();
        //Hash map, as we did in bundles
        ContentValues cv = new ContentValues();

        cv.put(STUDENT_NAME, STUDENTModel.getName());
        cv.put(STUDENT_AGE, STUDENTModel.getAge());
        cv.put(ACTIVE_STUDENT, STUDENTModel.isActive());
        db.insert(STUDENT_TABLE, null, cv);
        db.close();
    }

}
