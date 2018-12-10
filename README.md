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

数据库使用范例：     注意！注意！注意！只要用到数据库，代码必须要写在多线程中
                    原因：android不允许在主线程运行耗时较大的程序，而数据库一般都耗时不少
    1.添加一条数据到user表：
    public class Test {
        public static void main(String args[]){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //添加user表一条数据，id这个属性不用写，因为数据库设置好了，他会自动增加，每次加一，id不会重复
                    String Sql="insert into user (username,password,sex,age,phone,area) values('hsj','000000','男',21,13819158175,'510');";
                    ManageDB.insert(Sql);//调用ManageDB类的insert方法插入数据，insert(要执行的SQL语句)
                }
            }).start();
        }
    }

    2.删除数据
    public class Test {
        public static void main(String args[]){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String Sql="delete from user where id=1;";//删除user表id为1的数据
                    ManageDB.delete(Sql);//调用ManageDB类的delete方法删除数据，delete(SQL语句)
                }
            }).start();
        }
    }
    3.查找数据
    public class Test {
        public static void main(String args[]){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String Sql="select * from user;";//查找user表的所有数据的SQL语句
                    List list = null;
                    list = ManageDB.select(Sql);//调用ManageDB的select(要执行的SQL语句)获取所有查找到的数据
                    for(int i=0;i<list.size();i++){     //遍历结果的list
                        Map map = (Map) list.get(i);    //取出结果集的第i条数据,每条数据都是以map的形式储存
                        Set<String> s = map.keySet();   //遍历map,取出所有结果
                        for(String str:s){
                            System.out.println(str+":"+map.get(str));
                        }
                    }
                }
            }).start();
        }
    }
    4.修改数据
    public class Test {
        public static void main(String args[]){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //修改user表id为2的数据的area属性为‘3幢510’
                    String Sql = "update user set area='3幢510' where id=2;";
                    ManageDB.update(Sql);
                }
            }).start();
        }
    }
