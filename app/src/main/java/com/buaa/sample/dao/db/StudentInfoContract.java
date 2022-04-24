package com.buaa.sample.dao.db;

import android.provider.BaseColumns;

public final class StudentInfoContract {

    private StudentInfoContract() {
    }

    // StudentInfo entry
    public static class StudentInfoEntry implements BaseColumns {

        public static final String TABLE_NAME = "student_info";

        public static final String COLUMN_NAME_STUDENT_NAME = "name";
        public static final String COLUMN_NAME_CLASS_NAME = "class_name";
        public static final String COLUMN_NAME_STUDENT_AGE = "age";
    }
}
