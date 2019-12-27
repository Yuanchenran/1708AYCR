package com.bawei.day01.model;

import com.bawei.day01.net.NetUtil;



public class BaseModel {
    public void BaseGetData(String path, NetUtil.CallBack callBack){
        NetUtil netUtil = NetUtil.getNetUtil();
        netUtil.doGet(path,callBack);
    }
}
