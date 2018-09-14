# spark

FROM ubuntu

ENV JAVA_ADDRESS http://10.252.25.90:8125/files/jdk-8u181-linux-x64.tar.gz
ENV SCALA_ADDRESS http://10.252.25.90:8125/files/scala-2.11.12.tgz
ENV HADOOP_ADDRESS http://10.252.25.90:8125/files/hadoop-3.0.3.tar.gz
ENV SPARK_ADDRESS http://10.252.25.90:8125/files/spark-2.3.1-bin-hadoop2.7.tgz


ENV JAVA_HOME /usr/crescent/jdk1.8.0_181
ENV SCALA_HOME /usr/crescent/scala-2.11.12
ENV HADOOP_HOME /usr/crescent/hadoop-3.0.3
ENV SPARK_HOME /usr/crescent/spark-2.3.1-bin-hadoop2.7

# Add build payloads
ADD resources/bin /bin
ADD resources/ssh /root/.ssh
ADD resources/tmp /tmp/conf

RUN \

   chmod 755 /bin/*.sh \

   && echo "nameserver 8.8.8.8" >> /etc/resolv.conf \

    # 更新软件源
    && apt-get -y update \
    && apt-get install -y telnet \
    && apt-get install -y curl \
    && apt-get install -y openssh-server \
    && apt-get install -y net-tools \
    && apt-get install -y inetutils-ping \
    && apt-get install -y vim \
    # && dpkg-reconfigure dash -y \
    # && rm -f /bin/sh \
    # && ln -s /bin/bash /bin/sh \

    && mkdir -p /usr/crescent \

    # Install Java
    && curl -fSL $JAVA_ADDRESS -o /tmp/jdk-8u181-linux-x64.tar.gz \
    && tar -zxf /tmp/jdk-8u181-linux-x64.tar.gz -C /usr/crescent/ \
    && echo "export JAVA_HOME=/usr/crescent/jdk1.8.0_181" >> /etc/profile \
    && echo "export JRE_HOME=\${JAVA_HOME}/jre" >> /etc/profile \
    && echo "export CLASSPATH=.:\${JAVA_HOME}/lib:\${JRE_HOME}/lib" >> /etc/profile \
    && echo "export PATH=\${JAVA_HOME}/bin:\$PATH" >> /etc/profile \
    && rm -rf /tmp/jdk-8u181-linux-x64.tar.gz \

    # Install Scala
    && curl -fSL $SCALA_ADDRESS -o /tmp/scala-2.11.12.tgz \
    && tar -zxf /tmp/scala-2.11.12.tgz -C /usr/crescent/ \
    && echo "export SCALA_HOME=/usr/crescent/scala-2.11.12" >> /etc/profile \
    && echo "export PATH=\${SCALA_HOME}/bin:\$PATH" >> /etc/profile \
    && rm -rf /tmp/scala-2.11.12.tgz \


    ## Install Hadoop
    && curl -fSL $HADOOP_ADDRESS -o /tmp/hadoop-3.0.3.tar.gz \
    && tar -zxf /tmp/hadoop-3.0.3.tar.gz -C /usr/crescent/ \
    && echo "export HADOOP_HOME=/usr/crescent/hadoop-3.0.3" >> /etc/profile \
    && echo "export PATH=\${HADOOP_HOME}/bin:\${HADOOP_HOME}/sbin:\$PATH" >> /etc/profile \
    && echo "export HADOOP_PREFIX=/usr/crescent/hadoop-3.0.3" >> /usr/crescent/hadoop-3.0.3/etc/hadoop/hadoop-env.sh \
    && echo "export JAVA_HOME=/usr/crescent/jdk1.8.0_181" >> /usr/crescent/hadoop-3.0.3/etc/hadoop/hadoop-env.sh \
    && echo "export JAVA_HOME=/usr/crescent/jdk1.8.0_181" >> /usr/crescent/hadoop-3.0.3/etc/hadoop/yarm-env.sh \
    && yes | cp -r /tmp/conf/* /usr/crescent/hadoop-3.0.3/etc/hadoop/ \
    && rm -rf /tmp/hadoop-3.0.3.tar.gz \

    ## Install Spark
    && curl -fSL $SPARK_ADDRESS -o /tmp/spark-2.3.1-bin-hadoop2.7.tar.gz \
    && tar -zxf /tmp/spark-2.3.1-bin-hadoop2.7.tar.gz -C /usr/crescent/ \
    && echo "export SPARK_HOME="$SPARK_HOME >> /etc/profile \
    && echo "export PATH=\${SPARK_HOME}/bin:\${SPARK_HOME}/sbin:\$PATH" >> /etc/profile \
    && mv /usr/crescent/spark-2.3.1-bin-hadoop2.7/conf/spark-env.sh.template /usr/crescent/spark-2.3.1-bin-hadoop2.7/conf/spark-env.sh \
    && echo "export JAVA_HOME="$JAVA_HOME >> /usr/crescent/spark-2.3.1-bin-hadoop2.7/conf/spark-env.sh \
    && echo "export SCALA_HOME="$SCALA_HOME >> /usr/crescent/spark-2.3.1-bin-hadoop2.7/conf/spark-env.sh \
    && echo "export SPARK_WORKER_MEMORY=1g" >> /usr/crescent/spark-2.3.1-bin-hadoop2.7/conf/spark-env.sh \
    && echo "export HADOOP_CONF_DIR=/usr/crescent/hadoop-3.0.3/etc/hadoop" >> /usr/crescent/spark-2.3.1-bin-hadoop2.7/conf/spark-env.sh \
    && echo "export SPARK_MASTER_IP=DEV-SH-MAP-01" >> /usr/crescent/spark-2.3.1-bin-hadoop2.7/conf/spark-env.sh \
    && mv /usr/crescent/spark-2.3.1-bin-hadoop2.7/conf/slaves.template /usr/crescent/spark-2.3.1-bin-hadoop2.7/conf/slaves \
    && echo "" >> /usr/crescent/spark-2.3.1-bin-hadoop2.7/conf/slaves \
    && echo "DEV-SH-MAP-01" >> /usr/crescent/spark-2.3.1-bin-hadoop2.7/conf/slaves \
    && rm -rf /tmp/spark-2.3.1-bin-hadoop2.7.tar.gz
    
# Add JAVA_HOME into system environment
ENV CLASSPATH .:${JAVA_HOME}/lib:${JRE_HOME}/lib
ENV PATH $PATH:$JAVA_HOME/bin:$SCALA_HOME/lib:$HADOOP_HOME/bin:$HADOOP_HOME/sbin:$SPARK_HOME/bin:$SPARK_HOME/sbin

ENV HDFS_DATANODE_USER=root
ENV HADOOP_SECURE_DN_USER=hdfs
ENV HDFS_NAMENODE_USER=root
ENV HDFS_SECONDARYNAMENODE_USER=root

ENV YARN_RESOURCEMANAGER_USER=root
ENV HADOOP_SECURE_DN_USER=yarn
ENV YARN_NODEMANAGER_USER=root

CMD ["source", "/etc/profile"]
ENTRYPOINT ["/bin/bash","/bin/startup.sh"]
