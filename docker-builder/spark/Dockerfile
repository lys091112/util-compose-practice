# spark

FROM ubuntu

ENV JAVA_ADDRESS http://10.252.25.90:8125/files/jdk-8u181-linux-x64.tar.gz

# Add build payloads
ADD bin /bin

RUN \

   chmod 755 /bin/*.sh \

   && echo "nameserver 8.8.8.8" >> /etc/resolv.conf \

    # 更新软件源
    && apt-get -y update \
  
    # Install Java
    && mkdir -p /usr/crescent \
    && apt-get install -y telnet \
    && apt-get install -y curl \
    && curl -fSL $JAVA_ADDRESS -o /tmp/jdk-8u181-linux-x64.tar.gz \
    && tar -zxvf /tmp/jdk-8u181-linux-x64.tar.gz -C /usr/crescent/ \
    && echo "export JAVA_HOME=/usr/crescent/jdk1.8.0_181" >> /etc/profile \
    && echo "export JRE_HOME=\${JAVA_HOME}/jre" >> /etc/profile \
    && echo "export CLASSPATH=.:\${JAVA_HOME}/lib:\${JRE_HOME}/lib" >> /etc/profile \
    && echo "export PATH=\${JAVA_HOME}/bin:\$PATH" >> /etc/profile \
    && source /etc/profile/
    && rm -rf /tmp/jdk-8u181-linux-x64.tar.gz

    # Generate the need directory structure
    #&& mkdir -p /crescent/local \

    # Install MockServer
    # && curl -fSl $MOCK_SERVER -o /tmp/mock-service.tar.gz \
    # && tar xfz /tmp/mock-service.tar.gz -C /crescent/local \
    # && mv /crescent/local/mock-service/* /crescent/local \
    # && rm -rf /crescent/local/mock-service 

# Add JAVA_HOME into system environment
ENV JAVA_HOME /usr/crescent/jdk-8u181-linux-x64

EXPOSE 18001

ENTRYPOINT ["/bin/bash","/bin/startup.sh"]
