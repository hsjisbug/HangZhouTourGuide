package com.hzdy.zsy.hangzhoutourguide.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ManageDB extends Thread{
    public static Connection connection = null;
    private String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://172.20.17.88:3306/tour";
    private String user = "root";
    private String password = "000000";

    @Override
    public void run() {
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url,user,password);
            createTable();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTable(){
        String SQL_CREATE_USER = "create table if not exists user(" +
                "id int(11) not null auto_increment," +
                "username varchar(100) character set utf8 not null," +
                "password varchar(100) character set utf8 not null," +
                "sex varchar(8) character set utf8," +
                "age int(8)," +
                "phone varchar(13) character set utf8 not null," +
                "area varchar(100) character set utf8," +
                "primary key(id));";

        String SQL_CREATE_COMMENT = "create table if not exists comment(" +
                "id int(11) not null auto_increment," +
                "user_id int(11) not null," +
                "content text character set utf8," +
                "release_time date not null," +
                "content_image varchar(100) character set utf8," +
                "primary key(id)," +
                "foreign key(user_id) references user(id));";

        String SQL_CREATE_SCIENCE_SPOT="create table if not exists sciencespot(" +
                "id int(11) not null auto_increment," +
                "ss_name varchar(100) character set utf8 not null," +
                "address varchar(100) character set utf8 not null," +
                "position point," +
                "ss_message text character set utf8," +
                "ss_voice_introduce varchar(100) character set utf8," +
                "wc text character set utf8," +
                "food text character set utf8," +
                "in_and_out text character set utf8," +
                "hotel text character set utf8," +
                "parking_lot text character set utf8," +
                "bus_stop text character set utf8," +
                "visitor_center text character set utf8," +
                "primary key(id));";

        String SQL_CREATE_ATTRACTIONS="create table if not exists attractions(" +
                "id int(11) not null auto_increment," +
                "at_name varchar(100) character set utf8 not null," +
                "belong_ss int(11) not null," +
                "at_message text," +
                "position point," +
                "at_voice_introduce varchar(100) character set utf8," +
                "primary key(id)," +
                "foreign key(belong_ss) references sciencespot(id));";


        try {
            Statement st = connection.createStatement();
            st.execute(SQL_CREATE_USER);
            st.execute(SQL_CREATE_COMMENT);
            st.execute(SQL_CREATE_SCIENCE_SPOT);
            st.execute(SQL_CREATE_ATTRACTIONS);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public static void insert(){
        try {
            Statement st = connection.createStatement();
            String SQL_INSERT="insert into";
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
