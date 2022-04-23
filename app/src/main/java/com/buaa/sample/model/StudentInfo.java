package com.buaa.sample.model;

public class StudentInfo {

    private String name;
    private String className;
    private int age;

    public StudentInfo(String name, String className, int age) {
        this.name = name;
        this.className = className;
        this.age = age;
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
}
