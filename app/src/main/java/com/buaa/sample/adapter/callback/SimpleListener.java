package com.buaa.sample.adapter.callback;

import com.buaa.sample.model.StudentInfo;

abstract public class SimpleListener implements ClickListener {

    @Override
    public void onContentItemClick(StudentInfo studentInfo) {

    }

    @Override
    public void onHeaderItemClick(int buttonId) {

    }
}
