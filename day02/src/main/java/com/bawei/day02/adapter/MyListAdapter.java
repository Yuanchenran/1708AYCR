package com.bawei.day02.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.day02.NetUlit;
import com.bawei.day02.R;
import com.bawei.day02.bean.MyResult;

import java.util.ArrayList;
import java.util.List;

public class MyListAdapter extends BaseAdapter {
    private List<MyResult> results=new ArrayList<>();
    private Context context;

    public MyListAdapter(List<MyResult> results, Context context) {
        this.results.addAll(results);
        this.context = context;
    }

    @Override
    public int getCount() {
        return results.size();
    }

    @Override
    public Object getItem(int position) {
        return results.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if (convertView == null) {
            convertView=View.inflate(context, R.layout.item_list,null);
            holder=new ViewHolder();
            holder.img=convertView.findViewById(R.id.img);
            holder.name=convertView.findViewById(R.id.name);
            holder.price=convertView.findViewById(R.id.price);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        NetUlit netUlit = NetUlit.getNetUlit();
        netUlit.doImg(results.get(position).getMasterPic(),holder.img);
        holder.name.setText(results.get(position).getCommodityName());
        holder.price.setText(results.get(position).getPrice()+"");
        return convertView;
    }
    class ViewHolder{
        ImageView img;
        TextView name,price;
    }
}
