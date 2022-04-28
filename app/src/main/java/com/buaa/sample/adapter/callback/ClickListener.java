package com.buaa.sample.adapter.callback;

import com.buaa.sample.model.StudentInfo;

interface ClickListener {

    void onContentItemClick(StudentInfo studentInfo);

    void onHeaderItemClick(int buttonId);
}
