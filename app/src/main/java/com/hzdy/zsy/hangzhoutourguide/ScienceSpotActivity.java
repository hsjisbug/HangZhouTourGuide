package com.hzdy.zsy.hangzhoutourguide;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import com.hzdy.zsy.hangzhoutourguide.controller.ScienceSpotAsyncTask;

public class ScienceSpotActivity extends Activity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.science_recycler_view);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        new ScienceSpotAsyncTask(recyclerView, layoutManager, this).execute();//打开异步，解析json和给列表加适配器
    }


}
