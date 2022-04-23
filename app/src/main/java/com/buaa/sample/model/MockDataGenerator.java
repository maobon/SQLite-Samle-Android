package com.buaa.sample.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MockDataGenerator {

    public static List<StudentInfo> generate() {
        List<StudentInfo> list = new ArrayList<>();
        int index = 0;
        while (index < 50) {
            list.add(new StudentInfo(String.format("name=%s", index), "CS-2020", new Random().nextInt(100)));
            index++;
        }
        return list;
    }
}
