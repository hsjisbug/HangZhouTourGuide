package com.hzdy.zsy.hangzhoutourguide.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.ActivityChooserView;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.hzdy.zsy.hangzhoutourguide.R;
import com.hzdy.zsy.hangzhoutourguide.ScienceSpotActivity;
import com.hzdy.zsy.hangzhoutourguide.bean.HomeNewsContentList;
import com.hzdy.zsy.hangzhoutourguide.bean.HomeNewsLocation;

import java.util.List;

public class ScienceAdapter extends RecyclerView.Adapter<ScienceAdapter.ViewHolder> {

    private List<HomeNewsContentList> scienceList;
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private LocationClient mLocationClient;
    private PopupWindow popupWindow;
    private Activity activity;
    private MyLocationListener myLocationListener;
    private TextView scienceMapAddress;

    static class ViewHolder extends RecyclerView.ViewHolder{
//      把science_item.xml中的控件都先声明
        TextView scienceStar;
        TextView scienceName;
        TextView scienceAddresss;
        TextView scienceContent;

        public ViewHolder(View itemView) {
            super(itemView);
//          初始化所有控件，赋予id
            scienceStar = (TextView)itemView.findViewById(R.id.science_star);
            scienceName = (TextView)itemView.findViewById(R.id.science_name);
            scienceAddresss = (TextView)itemView.findViewById(R.id.science_address);
            scienceContent = (TextView)itemView.findViewById(R.id.science_content);
        }
    }

    public ScienceAdapter(List<HomeNewsContentList> list,Activity activity){
        scienceList = list;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.science_item, viewGroup, false);
        View contentView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.baidu_map, viewGroup, false);

        scienceMapAddress = (TextView)contentView.findViewById(R.id.science_map_address);
        mMapView = (MapView) contentView.findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(19.0f);
        mBaiduMap.setMapStatus(msu);
        MapStatus mMapStatus;//地图当前状态
        MapStatusUpdate mMapStatusUpdate;//地图将要变化成的状态
        mMapStatus = new MapStatus.Builder().overlook(-45).build();
        mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        mBaiduMap.setMapStatus(mMapStatusUpdate);

        //定位初始化
        mLocationClient = new LocationClient(viewGroup.getContext());

        //通过LocationClientOption设置LocationClient相关参数
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);

        //设置locationClientOption
        mLocationClient.setLocOption(option);

        //注册LocationListener监听器
        myLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(myLocationListener);
        mBaiduMap.setMyLocationEnabled(true);
        //开启地图定位图层
        mLocationClient.start();
        //引入弹窗布局
        int height = activity.getWindowManager().getDefaultDisplay().getHeight();
        int width = activity.getWindowManager().getDefaultDisplay().getWidth();
        popupWindow = new PopupWindow(contentView, width*3/4, height*3/4, true);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                updateAlpha(1.0f);
                mMapView.onResume();
            }
        });

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final HomeNewsContentList science = scienceList.get(i);//获取json中contentlist第i条数据。它会从第一条轮到最后一条，每次都会调用这个方法，传入不同的i。
        //给所有控件赋值。当然，不用的控件可以不用管
        viewHolder.scienceStar.setText("星级:"+science.getStar());
        viewHolder.scienceName.setText("景区名:"+science.getName());
        viewHolder.scienceAddresss.setText("地址:"+science.getAddress());
        viewHolder.scienceContent.setText("介绍:"+science.getSummary());

        viewHolder.scienceName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(v, science.getLocation(),science.getAddress());
            }
        });
    }

    @Override
    public int getItemCount() {
        return scienceList.size();
    }


    private void showDialog(View parentView, HomeNewsLocation location,String address) {
        updateAlpha(0.5f);
        getLocation(Double.valueOf(location.getLat()),Double.valueOf(location.getLon()));
        scienceMapAddress.setText(address);
        //相对于父控件的位置（例如正中央Gravity.CENTER，下方Gravity.BOTTOM等），可以设置偏移或无偏移
        popupWindow.showAtLocation(parentView, Gravity.CENTER, 0, 0);
    }
    private void getLocation(double lat,double lon){
        LatLng latLng = new LatLng(lat, lon);
        MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
        mBaiduMap.setMapStatus(msu);
    }

    private void updateAlpha(float a){
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = a; //0.0-1.0
        activity.getWindow().setAttributes(lp);
    }

    public class MyLocationListener extends BDAbstractLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            //mapView 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(location.getDirection()).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
        }
    }
}
