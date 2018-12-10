连接数据库：
	1.打开cmd，输入mysql -uroot -p(你的root用户密码)//没有括号，-u和root中间没有空格，-p也一样
	2.成功进入后输入create database tour;
	旧版mysql{
        3.接着输入GRANT ALL PRIVILEGES ON *.* TO root@'%' IDENTIFIED BY "(你的root用户密码)";//没有括号
        4.接着输入FLUSH PRIVILEGES;
	}
	新版mysql{
	    3.接着输入GRANT ALL PRIVILEGES ON *.* TO root @'%';
	    4.use mysql
	    5.update user set user.host='%' where user.user='root'
        6.接着输入FLUSH PRIVILEGES;
	}
	5.输入exit退出mysql
	6.继续在黑框输入ipconfig查看你的本机IP
	7.把项目的ManageDB.java文件的url变量中的IP地址改成你自己刚刚查的
	8.把项目的ManageDB.java文件的password变量改成你自己mysql的密码

分支指令：
    在本地新建一个分支： git branch newBranch
    切换到你的新分支: git checkout newBranch
    将新分支发布在github上： git push origin newBranch
    在本地删除一个分支： git branch -d newBranch
    在github远程端删除一个分支： git push origin :newBranch (分支名前的冒号代表删除)
    /git push origin –delete newBranch

数据库使用范例：
    1.添加一条数据到user表：
    public class Test {
        public static void main(String args[]){
            String key="";String values="";

            ArrayList<SqlMap> list = new ArrayList<SqlMap>();//存放要插入的数据

            //以键值对的形式把数据，放入list集合，key代表属性名，values代表对应要存入的值
            key="username";values="jbj";
            list.add(new SqlMap(key,values));
            key="password";values="000000";
            list.add(new SqlMap(key,values));
            key="sex";values="男";
            list.add(new SqlMap(key,values));
            key="age";values="21";
            list.add(new SqlMap(key,values));
            key="phone";values="110";
            list.add(new SqlMap(key,values));
            key="area";values="这里";
            list.add(new SqlMap(key,values));

            ManageDB.insert("user",list);//调用ManageDB类的insert方法插入数据，insert("表名",Arraylist集合名)
        }
    }

    2.删除数据
    public class Test {
        public static void main(String args[]){
            String Sql="delete from user where id=1;";//删除user表id为1的数据
            ManageDB.delete(Sql);//调用ManageDB类的delete方法删除数据，delete(SQL语句)
        }
    }
    3.查找数据
    public class Test {
        public static void main(String args[]){
            String Sql="select * from user";//查找user表的所有数据
            ResultSet rs = ManageDB.select(Sql);//调用ManageDB类的select方法查找数据，select(SQL语句)
            ResultSetMetaData md = rs.getMetaData();//获取键名
            int columnCount = md.getColumnCount();//获取行的数量
            while (rs.next()) {
            for (int i = 1; i <= columnCount; i++) {
                Log.i("TAG",md.getColumnName(i));
                Log.i(rs.getObject(i));
            }
        }
    }
