package com.buaa.sample.model;

import java.io.Serializable;

public class StudentInfo implements Serializable {

    private String id; // android.provider.BaseColumns._ID
    private String name;
    private String className;
    private int age;

    public StudentInfo() {
    }

    public StudentInfo(String name, String className, int age) {
        this.name = name;
        this.className = className;
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "StudentInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", className='" + className + '\'' +
                ", age=" + age +
                '}';
    }
}
