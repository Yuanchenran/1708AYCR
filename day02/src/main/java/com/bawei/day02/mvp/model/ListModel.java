package com.bawei.day02.mvp.model;

import com.bawei.day02.NetUlit;

public class ListModel {
    public void getListData(String path, NetUlit.CallBack callBack){
        NetUlit netUlit = NetUlit.getNetUlit();
        netUlit.doGet(path,callBack);
    }

}
