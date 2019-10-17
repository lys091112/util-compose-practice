# 手动添加镜像


1.  执行docker build 构建镜像
```
    docker build -t lys091112/ubuntu:0.1 .
```

2. 执行docker run，进入镜像，保持镜像运行状态

```
    docker run -it lys091112/ubuntu:0.1 /bin/bash
```

3. 提交本地镜像

```
    docker commit -m="add net-tools vim" -a="lys091112" 08909ecfe1ec lys091112/ubuntu:0.1
```

4. 推送到远程仓库

```
sudo docker login // 登录docker仓库


docker push sudo docker push lys091112/ubuntu:0.1


```


### 基础镜像

```
    镜像 scratch(空镜像), 大小 0B
    镜像 busybox(空镜像 + busybox), 大小 1.4MB
    镜像 alpine (空镜像 + busybox + apk), 大小 3.98MB
```
