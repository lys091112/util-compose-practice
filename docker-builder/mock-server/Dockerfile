# mock-server

FROM centos:7

ENV JAVA_ADDRESS http://10.128.6.188:8125/files/jdk-8u144-linux-x64.rpm
ENV MOCK_SERVER http://10.128.6.188:8125/files/mock-service.tar.gz

# Add build payloads
ADD bin /bin

RUN \

    chmod 755 /bin/*.sh \

    && echo "nameserver 8.8.8.8" >>/etc/resolv.conf \

    # 导入rpm的签名信息
    && rpm --import /etc/pki/rpm-gpg/RPM-GPG-KEY-CentOS-7 \

    # Install Java
    && yum install -y telnet \
    && curl -fSL $JAVA_ADDRESS -o /tmp/jdk-8-linux-x64.rpm \
    && yum -y install /tmp/jdk-8-linux-x64.rpm \
    && alternatives --install /usr/bin/java java /usr/java/latest/bin/java 200000 \
    && rm -rf /tmp/jdk-8-linux-x64.rpm \

    # Generate the need directory structure
    && mkdir -p /crescent/local \

    # Install MockServer
    && curl -fSl $MOCK_SERVER -o /tmp/mock-service.tar.gz \
    && tar xfz /tmp/mock-service.tar.gz -C /crescent/local \
    && mv /crescent/local/mock-service/* /crescent/local \
    && rm -rf /crescent/local/mock-service 

# Add JAVA_HOME into system environment
ENV JAVA_HOME /usr/java/latest

EXPOSE 18001
EXPOSE 18002

ENTRYPOINT ["/bin/bash","/bin/startup.sh"]
