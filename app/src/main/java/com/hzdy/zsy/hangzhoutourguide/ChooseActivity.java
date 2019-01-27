package com.hzdy.zsy.hangzhoutourguide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.hzdy.zsy.hangzhoutourguide.model.Const;

public class ChooseActivity extends Activity {
    private TextView jd, wd;
    private Button zhuan;
    private Button weather_button;
    private Button science_spot_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose);

        jd = (TextView) findViewById(R.id.jd);
        wd = (TextView) findViewById(R.id.wd);
        zhuan = (Button) findViewById(R.id.zhuan);
        weather_button = (Button) findViewById(R.id.weather_button);
        science_spot_button = (Button)findViewById(R.id.science_spot_button);

        zhuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseActivity.this, BaiduMapActivity.class);
                startActivity(intent);
            }
        });

        weather_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseActivity.this, WeatherActivity.class);
                startActivity(intent);
            }
        });

        science_spot_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseActivity.this, ScienceSpotActivity.class);
                startActivity(intent);
            }
        });

        openMap();
    }

    public void openMap(){
        LocationClient mLocationClient = new LocationClient(this);

        //通过LocationClientOption设置LocationClient相关参数
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);

        //设置locationClientOption
        mLocationClient.setLocOption(option);

        //注册LocationListener监听器
        MyLocationListener myLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(myLocationListener);
        //开启地图定位图层
        mLocationClient.start();

        jd.setText(String.valueOf(Const.LATITUDE));
        wd.setText(String.valueOf(Const.LONGITUDE));
    }

    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            Const.LATITUDE = bdLocation.getLatitude();
            Const.LONGITUDE = bdLocation.getLongitude();
        }
    }
}
