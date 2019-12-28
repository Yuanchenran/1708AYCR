package com.bawei.day02.bean;

import java.io.Serializable;
import java.util.List;

public class MyBean implements Serializable {
    private List<MyResult> result;

    public List<MyResult> getResult() {
        return result;
    }
}
