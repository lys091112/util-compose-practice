# spark

FROM ubuntu

RUN \

   echo "nameserver 8.8.8.8" >> /etc/resolv.conf \

    # 更新软件源
    && apt-get -y update \
    && apt-get install -y telnet \
    && apt-get install -y curl \
    && apt-get install -y openssh-server \
    && apt-get install -y net-tools \
    && apt-get install -y inetutils-ping \
    && apt-get install -y vim

CMD ["bash"]
