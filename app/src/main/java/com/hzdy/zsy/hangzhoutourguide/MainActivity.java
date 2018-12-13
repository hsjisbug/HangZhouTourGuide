package com.hzdy.zsy.hangzhoutourguide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hzdy.zsy.hangzhoutourguide.model.ManageDB;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainActivity extends Activity {
    private Button yu;
    private Button zhao;
    private Button lu;
    private Button ye;
    private Button jin;
    private Button huang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        yu=findViewById(R.id.yuxin);
        zhao=findViewById(R.id.zhaoxuanwei);
        lu=findViewById(R.id.luqiaofeng);
        ye=findViewById(R.id.yejiwei);
        jin=findViewById(R.id.jinbojun);
        huang=findViewById(R.id.huangshengjin);



        huang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });
    }
}
