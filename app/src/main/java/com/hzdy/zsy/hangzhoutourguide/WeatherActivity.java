package com.hzdy.zsy.hangzhoutourguide;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.hzdy.zsy.hangzhoutourguide.controller.WeatherAsyncTask;

public class WeatherActivity extends Activity {
    private TextView textView1;
    private TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather);

        textView1=(TextView) findViewById(R.id.textView1);
        textView2=(TextView) findViewById(R.id.textView2);

        new WeatherAsyncTask(textView1).execute();
    }

}
