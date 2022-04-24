package com.buaa.sample.dao.db;

class SQLConstants {

    static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + StudentInfoContract.StudentInfoEntry.TABLE_NAME + "(" +
                    StudentInfoContract.StudentInfoEntry._ID + " INTEGER PRIMARY KEY," +
                    StudentInfoContract.StudentInfoEntry.COLUMN_NAME_STUDENT_NAME + " TEXT," +
                    StudentInfoContract.StudentInfoEntry.COLUMN_NAME_CLASS_NAME + " TEXT," +
                    StudentInfoContract.StudentInfoEntry.COLUMN_NAME_STUDENT_AGE + " INTEGER)";

    static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + StudentInfoContract.StudentInfoEntry.TABLE_NAME;
}
