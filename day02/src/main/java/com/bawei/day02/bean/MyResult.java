package com.bawei.day02.bean;

import java.io.Serializable;

public class MyResult implements Serializable {
    private String imageUrl;
    private String commodityName;
    private String masterPic;
    private int price;

    public String getCommodityName() {
        return commodityName;
    }

    public String getMasterPic() {
        return masterPic;
    }

    public int getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
