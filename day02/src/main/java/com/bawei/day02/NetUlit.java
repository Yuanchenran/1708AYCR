package com.bawei.day02;

import android.util.Log;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bawei.day02.app.App;
import com.bumptech.glide.Glide;

import javax.security.auth.callback.Callback;

public class NetUlit {
    private static final String TAG = "NetUlit";
    private static NetUlit netUlit;
    private final RequestQueue requestQueue;

    private NetUlit() {
        requestQueue = Volley.newRequestQueue(App.context);
    }

    public static NetUlit getNetUlit() {
        if (netUlit == null) {
            synchronized (NetUlit.class){
                if (netUlit == null) {
                    netUlit=new NetUlit();
                }
            }
        }
        return netUlit;
    }
    public void doGet(String path, final CallBack callback){
        StringRequest stringRequest = new StringRequest(path, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.jsonOk(response);
                Log.d(TAG, "onResponse: "+response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String message = error.getMessage();
                callback.jsonNo(message);
            }
        });
        requestQueue.add(stringRequest);
    }
    public void doImg(String path, ImageView imageView){
        Glide.with(App.context)
                .load(path)
                .into(imageView);
    }


    public interface CallBack{
        void jsonOk(String json);
        void jsonNo(String msg);
    }
}
