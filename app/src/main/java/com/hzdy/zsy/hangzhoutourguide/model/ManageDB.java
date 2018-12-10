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

public class ManageDB extends Thread{
    public static Connection connection = null;
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://172.20.17.88:3306/tour";
    private static String user = "root";
    private static String password = "000000";
    private static boolean tag=false;

    @Override
    public void run() {
        try {
            connection = getConn();
            createTable();
            tag=true;
            Log.i("TAG","run");
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConn() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        connection = DriverManager.getConnection(url,user,password);
        return connection;
    }

    private void createTable() throws SQLException {
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


        Statement st = connection.createStatement();
        st.execute(SQL_CREATE_USER);
        st.execute(SQL_CREATE_COMMENT);
        st.execute(SQL_CREATE_SCIENCE_SPOT);
        st.execute(SQL_CREATE_ATTRACTIONS);
        //connection.close();
        st.close();
    }

    public static void insert(String tableName, ArrayList<SqlMap> insertMap) throws SQLException, ClassNotFoundException {
        int size=insertMap.size();
        String column="(";
        String values="(";
        String SQL_INSERT="insert into ";
        PreparedStatement ps=null;
        //connection=getConn();
        for(int i=0;i<size;i++){
            SqlMap key=insertMap.get(i);column+=key.getKey();
            if(!(key.getKey().equals("age")||key.getKey().equals("user_id")||key.getKey().equals("belong_ss")||
                    key.getKey().equals("release_time")||key.getKey().equals("position")||key.getKey().equals("id"))){
                values+="'"+key.getValues()+"'";
            }else {
                values += key.getValues();
            }
            if(i<size-1){
                column+=",";values+=",";
            }else{
                column+=") values";values+=");";
            }
        }
        SQL_INSERT+=tableName+column+values;
        ps = connection.prepareStatement(SQL_INSERT);
        ps.execute();
    }

    public static void delete(String Sql) throws SQLException, ClassNotFoundException {
        connection=getConn();
        PreparedStatement ps = null;
        ps = connection.prepareStatement(Sql);
    }

    public static void update(String tableName,ArrayList<SqlMap> updateMap) throws SQLException, ClassNotFoundException {
        PreparedStatement ps = null;
        int size=updateMap.size();
        String SQL_UPDATE="update "+tableName+" set ";
        connection=getConn();
        for(int i=0;i<size;i++){
            SqlMap key=updateMap.get(i);
            if(!(key.getKey().equals("age")||key.getKey().equals("user_id")||key.getKey().equals("belong_ss")||
                    key.getKey().equals("release_time")||key.getKey().equals("position")||key.getKey().equals("id"))){
                if (i < size - 1) {
                    SQL_UPDATE += key.getKey() + "=";
                    SQL_UPDATE += "'" + key.getValues() + "'";
                }
                else
                    SQL_UPDATE+=" where " + key.getKey() + "='" + key.getValues() + "'";
            } else {
                if (i < size - 1) {
                    SQL_UPDATE += key.getKey() + "=";
                    SQL_UPDATE += key.getValues() ;
                }
                else
                    SQL_UPDATE+=" where " + key.getKey() + "=" + key.getValues();
            }
            if (i < size - 2)
                SQL_UPDATE += ",";
        }
        ps=connection.prepareStatement(SQL_UPDATE);
        ps.execute();
    }

    public static List select(String Sql) throws SQLException, ClassNotFoundException, InterruptedException {
        while(!tag){

        }
        Log.i("SEL","查找");
        PreparedStatement ps = null;
        ResultSet rs=null;
        List list=null;
        //connection=getConn();
        ps = connection.prepareStatement(Sql);
        Log.i("TAG", String.valueOf(ps));
        if(ps!=null){
            rs=ps.executeQuery();
            list=convertList(rs);
        }
        //connection.close();
        ps.close();
        return list;
    }

    private static List convertList(ResultSet rs) throws SQLException{
        List list = new ArrayList();
        ResultSetMetaData md = rs.getMetaData();//获取键名
        int columnCount = md.getColumnCount();//获取行的数量
        while (rs.next()) {
            Map rowData = new HashMap();//声明Map
            for (int i = 1; i <= columnCount; i++) {
                rowData.put(md.getColumnName(i), rs.getObject(i));//获取键名及值
            }
            list.add(rowData);
        }
        return list;
    }
}
