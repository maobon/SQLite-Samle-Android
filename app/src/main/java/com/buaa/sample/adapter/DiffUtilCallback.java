package com.buaa.sample.adapter;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.buaa.sample.model.StudentInfo;

import java.util.List;

class DiffUtilCallback extends DiffUtil.Callback {

    private final List<StudentInfo> oldList;
    private final List<StudentInfo> newList;

    public DiffUtilCallback(List<StudentInfo> oldList, List<StudentInfo> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getId().equals(newList.get(newItemPosition).getId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        StudentInfo oldInfo = oldList.get(oldItemPosition);
        StudentInfo newInfo = newList.get(newItemPosition);
        return oldInfo.getAge() == newInfo.getAge() && oldInfo.getIndex() == newInfo.getIndex()
                && oldInfo.getName().equals(newInfo.getName());
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        StudentInfo oldInfo = oldList.get(oldItemPosition);
        StudentInfo newInfo = newList.get(newItemPosition);

        Bundle payload = new Bundle();
        if (oldInfo.getAge() != newInfo.getAge()) {
            payload.putInt("age", newInfo.getAge());
        }
        if (oldInfo.getIndex() != newInfo.getIndex()) {
            payload.putInt("index", newInfo.getIndex());
        }
        if (!oldInfo.getName().equals(newInfo.getName())) {
            payload.putString("name", newInfo.getName());
        }
        if (payload.size() == 0) return null;
        return payload;
    }
}
