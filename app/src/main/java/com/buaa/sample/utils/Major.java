package com.buaa.sample.utils;

public enum Major {

    CS("计算机科学"),
    CE("计算机工程"),
    EE("电子工程");

    public String name;

    Major(String name) {
        this.name = name;
    }

    public static String[] getMajorArray() {
        String[] array = new String[values().length];
        int index = 0;
        for (Major major : Major.values()) {
            array[index] = major.name;
            index++;
        }
        return array;
    }
}
