# 操作步骤

BASE

在master机器执行 
ssh root@master
ssh root@slave1
ssh root@slave2
建立机器之间的ssh链接认证

HADOOP

1. hdfs namenode -format // 格式化namenode

2. start-dfs.sh start-yarn.sh 启动服务 或者执行 start-all.sh


SPARK

1. start-master.sh start-slaves.sh 来启动master worker进程


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

4. org.apache.hadoop.mapred.InvalidInputException: Input path does not exist: hdfs://sandbox:9000/user/root/README.md
```
val textFile = sc.textFile("README.md")
解决方法为： 
    1.  将README.md的路径修改为本地路径 file:///ownpath/README.md

    2. 如果没有hdfs，则先创建hdfs，然后将文件上传到hdfs
        hdfs dfs -mkdir /user
        hdfs dfs -mkdir /user/root
        hadoop fs -put /ownpath/README.md README.md // 上传文件

上传后的文件最终会保存到各自的datanode下的.../dfs/data 目录下，但因为是经过转码的，所以不是明文但仍然可以通过查看文件观测到
存储的份数和设置的备份数量有关

```

4.1. 退出scala shell命令 ==> :q

5. spark任务提交
```
 local执行模式
     spark-shell --master yarn-client --driver-memory 1g --executor-memory 1g --executor-cores 1
     ./spark-submit --class com.xianyue.spark.Main --master local[2] --executor-memory 1g --total-executor-cores 2 hdfs:///user/root/kong-1.0-SNAPSHOT.jar

 cluster 模式
    ./spark-submit --class com.xianyue.spark.Main --master spark://master:7077 --deploy-mode cluster --supervise --executor-memory 1g --total-executor-cores 2 file:///root/app/kong-1.0-SNAPSHOT.jar

    ./spark-submit --class com.xianyue.spark.Main --master spark://master:7077 --deploy-mode cluster --executor-memory 1g --total-executor-cores 2 hdfs:///user/root/kong-1.0-SNAPSHOT.jar
```



6. hadoopo端口统计：
```
HADOOP3.0.3端口统计：
    9870 hadoop namenode web ui端口
    8088 yarm web端口

SPARK2.3 端口统计
    spark集群的web端口：8080
    spark-job监控端口：4040
```
