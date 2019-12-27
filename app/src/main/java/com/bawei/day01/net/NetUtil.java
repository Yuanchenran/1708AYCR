package com.bawei.day01.net;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bawei.day01.app.App;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.security.auth.callback.Callback;

public class NetUtil {
    private static final String TAG = "NetUtil";
    private static NetUtil netUtil;
    private final RequestQueue requestQueue;
    //public static Callback callback;

    private NetUtil() {
        requestQueue = Volley.newRequestQueue(App.context);
    }

    public static NetUtil getNetUtil() {
        if (netUtil == null) {
            synchronized (NetUtil.class){
                if (netUtil == null) {
                    netUtil=new NetUtil();
                }
            }
        }
        return netUtil;
    }
    public void doGet(String path, final CallBack callBack){
        StringRequest stringRequest = new StringRequest(path, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: "+response);
               callBack.jsonOk(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String message = error.getMessage();
                callBack.jsonNo(message);
            }
        });
        requestQueue.add(stringRequest);
    }
    public void imgGet(String path, final ImageView imageView){
        new AsyncTask<String, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(String... strings) {
                String path = strings[0];
                try {
                    URL url = new URL(path);
                    HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.setReadTimeout(8000);
                    httpURLConnection.connect();
                    int responseCode = httpURLConnection.getResponseCode();
                    if (responseCode==200) {
                        InputStream inputStream = httpURLConnection.getInputStream();
                        BitmapFactory.Options options=new BitmapFactory.Options();
                        options.inPreferredConfig= Bitmap.Config.RGB_565;
                        options.inSampleSize=2;
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, options);
                        return bitmap;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
                if (bitmap!=null){
                    imageView.setImageBitmap(bitmap);
                }

            }
        }.execute(path);
    }


   public interface CallBack{
        void jsonOk(String json);
        void jsonNo(String msg);
    }
}
