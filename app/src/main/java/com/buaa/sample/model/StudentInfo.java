package com.buaa.sample.model;

import java.io.Serializable;

public class StudentInfo implements Serializable {

    private String id; // android.provider.BaseColumns._ID
    private String name;
    private int index; // major index
    private int age;

    public StudentInfo() {
    }

    public StudentInfo(String name, int index, int age) {
        this.name = name;
        this.index = index;
        this.age = age;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
