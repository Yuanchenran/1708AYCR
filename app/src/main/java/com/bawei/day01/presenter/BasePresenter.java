package com.bawei.day01.presenter;

import com.bawei.day01.model.BaseModel;
import com.bawei.day01.net.NetUtil;
import com.bawei.day01.view.BaseView;

public class BasePresenter {
  private BaseView baseView;

    public BasePresenter(BaseView baseView) {
        this.baseView = baseView;
    }

    public void BaseGetData(String path){
        BaseModel baseModel = new BaseModel();
        baseModel.BaseGetData(path, new NetUtil.CallBack() {
            @Override
            public void jsonOk(String json) {
                  baseView.jsonOk(json);
            }

            @Override
            public void jsonNo(String msg) {
                    baseView.jsonNo(msg);
            }
        });

    }
}
