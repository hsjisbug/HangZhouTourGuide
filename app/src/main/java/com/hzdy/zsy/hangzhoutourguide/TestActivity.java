package com.hzdy.zsy.hangzhoutourguide;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import com.hzdy.zsy.hangzhoutourguide.controller.Weather;
import com.show.api.ShowApiRequest;
import org.json.JSONException;
import org.json.JSONObject;

public class TestActivity extends Activity {
    private TextView textView1;
    private TextView textView2;
    private Weather weather;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    weather = (Weather) msg.obj;

                    textView1.setText(weather.getWeather());
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        textView1=(TextView) findViewById(R.id.textView1);
        textView2=(TextView) findViewById(R.id.textView2);
        new Test().getWeather();
    }

    public class Test{
        private void getWeather(){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String appid="82738";
                    String secret="782a93185919420082799431c555c168";
                    final String res=new ShowApiRequest( "https://route.showapi.com/9-5", appid, secret)
                            .addTextPara("from", "5")
                            .addTextPara("lng", "116.2278")
                            .addTextPara("lat", "40.242266")
                            .addTextPara("needMoreDay", "0")
                            .addTextPara("needIndex", "0")
                            .addTextPara("needHourData", "0")
                            .addTextPara("need3HourForcast", "0")
                            .addTextPara("needAlarm", "0")
                            .post();
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(res);
                        jsonObject=jsonObject.getJSONObject("showapi_res_body");
                        String weather=jsonObject.getJSONObject("now").optString("weather");
                        String temperature = jsonObject.getJSONObject("now").optString("temperature");
                        String position = jsonObject.getJSONObject("cityInfo").optString("c5");
                        Message message=new Message();
                        message.what=0;
                        message.obj=new Weather(weather,temperature,position);
                        handler.sendMessage(message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

}
