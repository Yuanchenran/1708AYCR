package com.bawei.day02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bawei.day02.adapter.MyListAdapter;
import com.bawei.day02.base.BaseActivity;
import com.bawei.day02.bean.MyBean;
import com.bawei.day02.bean.MyResult;
import com.bawei.day02.mvp.presenter.BannerPresenter;
import com.bawei.day02.mvp.presenter.ListPresenter;
import com.bawei.day02.mvp.view.BannerView;
import com.google.gson.Gson;
import com.stx.xhb.xbanner.XBanner;

import java.util.List;

public class MainActivity extends BaseActivity implements BannerView , com.bawei.day02.mvp.view.ListView {

    private static final String TAG = "MainActivity";
    private com.stx.xhb.xbanner.XBanner xbanner;
    private android.widget.ListView listView;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        xbanner = (XBanner) findViewById(R.id.xbanner);
        listView = (ListView) findViewById(R.id.list_view);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        String path="http://172.17.8.100/small/commodity/v1/bannerShow";
        BannerPresenter bannerPresenter = new BannerPresenter((BannerView) this);
        bannerPresenter.getBannData(path);
        path="http://172.17.8.100/small/commodity/v1/findCommodityByCategory?categoryId=1001003004&page=1&count=5";
        ListPresenter listPresenter = new ListPresenter((com.bawei.day02.mvp.view.ListView) this);
        listPresenter.getListData(path);
    }

    @Override
    public void jsonOk(String json) {
        Gson gson = new Gson();
        MyBean myBean = gson.fromJson(json, MyBean.class);
        final List<MyResult> result = myBean.getResult();
        xbanner.setData(result,null);
        xbanner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, View view, int position) {
                NetUlit netUlit = NetUlit.getNetUlit();
                MyResult myResult = result.get(position);
                String imageUrl = myResult.getImageUrl();
                ImageView imageView = (ImageView) view;
                netUlit.doImg(imageUrl,imageView);
            }
        });

    }

    @Override
    public void jsonNo(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void jsonListOk(String json) {
        Gson gson = new Gson();
        MyBean myBean = gson.fromJson(json, MyBean.class);
        List<MyResult> result = myBean.getResult();
        MyListAdapter myListAdapter = new MyListAdapter(result, MainActivity.this);
        listView.setAdapter(myListAdapter);
    }

    @Override
    public void jsonListNo(String msg) {

    }
}
