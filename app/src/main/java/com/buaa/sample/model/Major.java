package com.buaa.sample.model;

public enum Major {

    CS("计算机科学"),
    CE("计算机工程"),
    EE("电子工程");

    public String name;

    Major(String name) {
        this.name = name;
    }
}
