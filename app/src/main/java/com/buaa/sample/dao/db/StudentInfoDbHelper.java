package com.buaa.sample.dao.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class StudentInfoDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "StudentInfo.db";

    public StudentInfoDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL.CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(SQL.DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }

    private static class SQL {

        // create table
        private static final String CREATE_ENTRIES =
                "CREATE TABLE " + StudentInfoContract.StudentInfoEntry.TABLE_NAME + "(" +
                        StudentInfoContract.StudentInfoEntry._ID + " INTEGER PRIMARY KEY," +
                        StudentInfoContract.StudentInfoEntry.COLUMN_NAME_STUDENT_NAME + " TEXT," +
                        StudentInfoContract.StudentInfoEntry.COLUMN_NAME_MAJOR_INDEX + " INTEGER," +
                        StudentInfoContract.StudentInfoEntry.COLUMN_NAME_STUDENT_AGE + " INTEGER)";

        // delete table
        private static final String DELETE_ENTRIES = "DROP TABLE IF EXISTS " + StudentInfoContract.StudentInfoEntry.TABLE_NAME;
    }
}
