package com.hzdy.zsy.hangzhoutourguide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
        new ManageDB().start();

        yu=findViewById(R.id.yuxin);
        zhao=findViewById(R.id.zhaoxuanwei);
        lu=findViewById(R.id.luqiaofeng);
        ye=findViewById(R.id.yejiwei);
        jin=findViewById(R.id.jinbojun);
        huang=findViewById(R.id.huangshengjin);

//        yu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Activity.this, );
//                startActivity(intent);
//            }
//        });
//
//        zhao.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Activity.this, );
//                startActivity(intent);
//            }
//        });
//
//        lu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Activity.this, );
//                startActivity(intent);
//            }
//        });
//
//        ye.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Activity.this, );
//                startActivity(intent);
//            }
//        });
//
//        jin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Activity.this, );
//                startActivity(intent);
//            }
//        });
//
//        huang.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Activity.this, );
//                startActivity(intent);
//            }
//        });
    }
}
