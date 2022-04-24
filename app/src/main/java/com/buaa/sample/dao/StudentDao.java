package com.buaa.sample.dao;

import static android.provider.BaseColumns._ID;
import static com.buaa.sample.dao.db.StudentInfoContract.StudentInfoEntry.COLUMN_NAME_CLASS_NAME;
import static com.buaa.sample.dao.db.StudentInfoContract.StudentInfoEntry.COLUMN_NAME_STUDENT_AGE;
import static com.buaa.sample.dao.db.StudentInfoContract.StudentInfoEntry.COLUMN_NAME_STUDENT_NAME;
import static com.buaa.sample.dao.db.StudentInfoContract.StudentInfoEntry.TABLE_NAME;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.buaa.sample.dao.db.StudentInfoDbHelper;
import com.buaa.sample.model.StudentInfo;

import java.util.ArrayList;
import java.util.List;

public class StudentDao {

    private static StudentDao sOurInstance;
    private final StudentInfoDbHelper dbHelper;

    public static StudentDao getInstance(Context context) {
        if (sOurInstance == null) {
            synchronized (StudentDao.class) {
                if (sOurInstance == null) {
                    sOurInstance = new StudentDao(context);
                }
            }
        }
        return sOurInstance;
    }

    private StudentDao(Context context) {
        dbHelper = new StudentInfoDbHelper(context);
    }

    /**
     * 新增
     *
     * @param info StudentInfo
     */
    public void insert(StudentInfo info) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_STUDENT_NAME, info.getName());
        values.put(COLUMN_NAME_CLASS_NAME, info.getClassName());
        values.put(COLUMN_NAME_STUDENT_AGE, info.getAge());
        db.insert(TABLE_NAME, null, values);
    }

    /**
     * 查找
     *
     * @return List<StudentInfo>
     */
    public List<StudentInfo> query() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {_ID, COLUMN_NAME_STUDENT_NAME, COLUMN_NAME_CLASS_NAME, COLUMN_NAME_STUDENT_AGE};
        Cursor cursor = db.query(
                TABLE_NAME,
                projection,
                null, null, null, null, null);

        List<StudentInfo> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            StudentInfo info = new StudentInfo();
            info.setId(cursor.getString(cursor.getColumnIndexOrThrow(_ID)));
            info.setName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_STUDENT_NAME))); // student name
            info.setClassName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_CLASS_NAME))); // student class name
            info.setAge(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_STUDENT_AGE))); // age
            list.add(info);
        }
        cursor.close();
        return list;
    }

    /**
     * 修改
     *
     * @param info StudentInfo
     */
    public void update(StudentInfo info) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_STUDENT_NAME, info.getName());
        values.put(COLUMN_NAME_CLASS_NAME, info.getClassName());
        values.put(COLUMN_NAME_STUDENT_AGE, info.getAge());

        String selection = _ID + " LIKE ?";
        String[] selectionArgs = {info.getId()};
        db.update(TABLE_NAME, values, selection, selectionArgs);
    }

    /**
     * 删除
     *
     * @param id PRIMARY KEY
     */
    public void delete(String id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selection = _ID + " LIKE ?";
        String[] selectionArgs = {id};
        db.delete(TABLE_NAME, selection, selectionArgs);
    }

    public void disconnect() {
        dbHelper.close();
    }
}
