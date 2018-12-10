package com.hzdy.zsy.hangzhoutourguide;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import com.hzdy.zsy.hangzhoutourguide.model.ManageDB;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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

        String Sql="select * from user;";//查找user表的所有数据
        List list = null;
        try {
            list = ManageDB.select(Sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int i=0;i<list.size();i++){
            Map map= (Map) list.get(i);
            System.out.println(map);
        }

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
