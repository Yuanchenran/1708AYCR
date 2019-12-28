package com.bawei.day02.mvp.presenter;

import com.bawei.day02.NetUlit;
import com.bawei.day02.mvp.model.BannModel;
import com.bawei.day02.mvp.view.BannerView;

import java.nio.file.Path;

public class BannerPresenter {
    private final BannModel bannModel;
    private BannerView bannerView;

    public BannerPresenter(BannerView bannerView) {
        this.bannerView = bannerView;
        bannModel = new BannModel();
    }

    public void getBannData(String Path){
   bannModel.getBannData(Path, new NetUlit.CallBack() {
       @Override
       public void jsonOk(String json) {
           bannerView.jsonOk(json);
       }

       @Override
       public void jsonNo(String msg) {
           bannerView.jsonNo(msg);
       }
   });

    }
}
