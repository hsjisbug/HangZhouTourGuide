连接数据库：
	1.打开cmd，输入mysql -uroot -p(你的root用户密码)
	2.成功进入后输入create database tour;
	3.接着输入GRANT ALL PRIVILEGES ON *.* TO root @'%' IDENTIFIED BY "(你的root用户密码)";
	4.接着输入FLUSH PRIVILEGES;
	5.输入exit退出mysql
	6.继续在黑框输入ipconfig查看你的本机IP
	7.把项目的ManageDB.java文件的url变量中的IP地址改成你自己刚刚查的