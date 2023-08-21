package com.unoveo.calcUtils;

import java.util.ArrayList;

public  class Expression {
    private String type;
    private String value;
    public Expression(){

    }
    public Expression(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Expression{" +
                "type='" + type + '\'' +
                ", value='" + value + '\'' +
                '}';
    }


}
