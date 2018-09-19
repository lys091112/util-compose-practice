# 操作步骤

BASE

执行 
ssh root@master
ssh root@slave1
ssh root@slave2
建立机器之间的ssh链接认证

HADOOP

1. hdfs namenode -format // 格式化namenode

2. start-dfs.sh start-yarn.sh 启动服务 或者执行 start-all.sh



SPARK

1. 修改spark-env.sh 将其中的域名改为ip地址

2. start-master.sh start-slaves.sh 来启动master worker进程


tmp:

ssh-copy-id -i ~/.ssh/id_rsa.pub root@slave1



1. 配置无密码登录之后，ssh localhost仍然需要输入密码，可能的问题是文件权限问题：
```

我这次的问题是root目录的归属组为10000:10000，而不是root，执行命令
chown root:root -R /root 解决


其他的尝试情形有：

1. 更改authorized_keys的权限为600: chmod 600 authorized_keys
2. 首选.ssh目录权限是700， 两个dsa 和 rsa的 私钥权限是600，其余文件权限是644.
3. .ssh文件夹绝对路径是/root/.ssh，所以/root目录的权限应该是755

```

2. Hadoop3要用workers代替之前版本的slaves

3. 在hadoop3.0之后，使用9870端口代替原有的50070端口
