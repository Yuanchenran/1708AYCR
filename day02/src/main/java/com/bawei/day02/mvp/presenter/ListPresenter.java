package com.bawei.day02.mvp.presenter;

import com.bawei.day02.NetUlit;
import com.bawei.day02.mvp.model.BannModel;
import com.bawei.day02.mvp.model.ListModel;
import com.bawei.day02.mvp.view.BannerView;
import com.bawei.day02.mvp.view.ListView;

public class ListPresenter {
    private final ListModel listModel;
    private ListView listView;

    public ListPresenter(ListView listView) {
        this.listView = listView;
        listModel = new ListModel();

    }


    public void getListData(String Path){
        listModel.getListData(Path, new NetUlit.CallBack() {
       @Override
       public void jsonOk(String json) {
           listView.jsonListOk(json);
       }

       @Override
       public void jsonNo(String msg) {
           listView.jsonListNo(msg);
       }
   });

    }
}
