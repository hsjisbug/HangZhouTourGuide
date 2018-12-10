package com.hzdy.zsy.hangzhoutourguide.model;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManageDB{
    public static Connection connection = null;
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://172.20.17.88:3306/tour";
    private static String user = "root";
    private static String password = "000000";

    public static void initDatabase(){
        connection = getConn();
        createTable();
    }

    public static Connection getConn(){
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url,user,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private static void createTable(){
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
            connection.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insert(String Sql){
        PreparedStatement ps=null;
        connection=getConn();
        try {
            ps = connection.prepareStatement(Sql);
            ps.execute();
            connection.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete(String Sql){
        connection=getConn();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(Sql);
            ps.execute();
            connection.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void update(String Sql){
        PreparedStatement ps = null;
        connection=getConn();
        try {
            ps=connection.prepareStatement(Sql);
            ps.execute();
            connection.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List select(String Sql){
        PreparedStatement ps = null;
        ResultSet rs=null;
        List list=null;
        connection=getConn();
        try {
            ps = connection.prepareStatement(Sql);
            if(ps!=null){
                rs=ps.executeQuery();
                list=convertList(rs);
            }
            connection.close();
            ps.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    private static List convertList(ResultSet rs){
        List list = new ArrayList();
        ResultSetMetaData md = null;//获取键名
        try {
            md = rs.getMetaData();
            int columnCount = md.getColumnCount();//获取行的数量
            while (rs.next()) {
                Map rowData = new HashMap();//声明Map
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i), rs.getObject(i));//获取键名及值
                }
                list.add(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
