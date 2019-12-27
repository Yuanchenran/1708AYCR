package com.bawei.day01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bawei.day01.base.BaseActivity;
import com.bawei.day01.bean.MyBean;
import com.bawei.day01.bean.MyResult;
import com.bawei.day01.net.NetUtil;
import com.bawei.day01.presenter.BasePresenter;
import com.bawei.day01.view.BaseView;
import com.google.gson.Gson;
import com.stx.xhb.xbanner.XBanner;

import java.nio.file.Path;
import java.util.List;

public class MainActivity extends BaseActivity implements BaseView {
    private static final String TAG = "MainActivity";


    private com.stx.xhb.xbanner.XBanner xbanner;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        xbanner = (XBanner) findViewById(R.id.xbanner);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        String path="http://172.17.8.100/small/commodity/v1/bannerShow";
        BasePresenter basePresenter = new BasePresenter(MainActivity.this);
        basePresenter.BaseGetData(path);
    }

    @Override
    public void jsonOk(String json) {
        Log.d(TAG, "jsonOk: "+json);
        Gson gson = new Gson();
        MyBean myBean = gson.fromJson(json, MyBean.class);
        final List<MyResult> result = myBean.getResult();
        xbanner.setData(result,null);
        xbanner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                ImageView imageView=(ImageView) view;
                MyResult myResult = result.get(position);
                String imageUrl = myResult.getImageUrl();
                NetUtil netUtil = NetUtil.getNetUtil();
                netUtil.imgGet(imageUrl,imageView);
            }
        });
    }

    @Override
    public void jsonNo(String msg) {
        Log.d(TAG, "jsonNo: "+msg);

    }
}
