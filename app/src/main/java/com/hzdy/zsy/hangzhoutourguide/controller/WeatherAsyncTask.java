package com.hzdy.zsy.hangzhoutourguide.controller;

import android.os.AsyncTask;
import android.widget.TextView;

import com.hzdy.zsy.hangzhoutourguide.model.Const;
import com.hzdy.zsy.hangzhoutourguide.model.Weather;
import com.show.api.ShowApiRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class WeatherAsyncTask extends AsyncTask<Void, String, Weather> {
    private TextView showWeather;

    public WeatherAsyncTask(TextView t){
        this.showWeather = t;
    }

    @Override
    protected Weather doInBackground(Void... voids) {
        String appid="82738";
        String secret="782a93185919420082799431c555c168";
        final String res=new ShowApiRequest( "https://route.showapi.com/9-5", appid, secret)
                .addTextPara("from", "5")
                .addTextPara("lng", String.valueOf(Const.LONGITUDE))
                .addTextPara("lat", String.valueOf(Const.LATITUDE))
                .post();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(res);
            jsonObject =jsonObject.getJSONObject("showapi_res_body");
            String weather =jsonObject.getJSONObject("now").optString("weather");
            String temperature = jsonObject.getJSONObject("now").optString("temperature");
            String position = jsonObject.getJSONObject("cityInfo").optString("c5");
            return new Weather(weather,temperature,position);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Weather weather) {
        showWeather.setText("天气:"+weather.getWeather()+"\n温度:"+weather.getTemperature() +
                "\n位置:"+weather.getPosition());
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }
}
