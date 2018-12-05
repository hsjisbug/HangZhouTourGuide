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