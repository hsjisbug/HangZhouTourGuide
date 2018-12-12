package com.hzdy.zsy.hangzhoutourguide.controller;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class WeatherForecast {
    private TextView textView;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    String re = (String)msg.obj;
                    textView.setText(re);
                    break;
            }
        }
    };

    private void sendRequestWithHttpClient(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try{
                    URL url = new URL("https://v.juhe.cn/weather/index?cityname=%E6%9D%AD%E5%B7%9E&dtype=&format=&key=e0ff9897b8ab5c8ae73e62643eabd880");
                    connection = (HttpURLConnection)url.openConnection();
                    Log.i("TAG",connection.toString());
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while((line= reader.readLine())!=null){
                        response.append(line);
                    }
                    Log.i("TAG",response.toString());
                    parseJSONObjectOrJSONArray(response.toString());
                    if (reader != null){
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (connection != null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    private void parseJSONObjectOrJSONArray(String jsonData){
        try {
            String count="";
            JSONObject jsonObject=new JSONObject(jsonData);
            Log.i("AAA",jsonObject.toString());
            jsonObject=jsonObject.getJSONObject("result").getJSONObject("today");
            Log.i("AAA",jsonObject.toString());
            String dateDay=jsonObject.optString("date_y");
            String weather=jsonObject.optString("weather");
            String wind =jsonObject.optString("wind");
            String temperature = jsonObject.optString("temperature");
            count=count+"\n"+dateDay+" "+weather+" "+wind+" "+temperature;
            Log.i("AAA",count);
            Message message=new Message();
            message.what=0;
            message.obj=count;
            handler.sendMessage(message);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
