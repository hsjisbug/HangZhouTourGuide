package com.hzdy.zsy.hangzhoutourguide.controller;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.google.gson.Gson;
import com.hzdy.zsy.hangzhoutourguide.adapter.ScienceAdapter;
import com.hzdy.zsy.hangzhoutourguide.bean.HomeNewsBean;
import com.hzdy.zsy.hangzhoutourguide.bean.HomeNewsContentList;
import com.show.api.ShowApiRequest;

import java.util.List;

public class ScienceSpotAsyncTask extends AsyncTask<Void ,String ,List<HomeNewsContentList>> {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private Activity activity;

    public ScienceSpotAsyncTask(RecyclerView recyclerView,LinearLayoutManager layoutManager,Activity activity){
        this.recyclerView = recyclerView;
        this.layoutManager = layoutManager;
        this.activity = activity;
    }

    @Override
    protected List<HomeNewsContentList> doInBackground(Void... voids) {
        String appid="82738";
        String secret="782a93185919420082799431c555c168";
        final String res=new ShowApiRequest( "https://route.showapi.com/268-1", appid, secret)
                .addTextPara("keyword", "杭州")
                .post();
        Gson gson = new Gson();
        HomeNewsBean bean = gson.fromJson(res, HomeNewsBean.class);
        return bean.getShowapi_res_body().getPagebean().getContentlist();
    }

    @Override
    protected void onPostExecute(List<HomeNewsContentList> contentLists) {
        recyclerView.setLayoutManager(layoutManager);
        ScienceAdapter scienceAdapter = new ScienceAdapter(contentLists, activity);
        recyclerView.setAdapter(scienceAdapter);//设置列表的适配器
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
