#crescent/pytorch

FROM pytorch/pytorch:latest

# Add build payloads
ADD ./bin /crescent/bin/


#更改至国内163镜像源
RUN mv /etc/apt/sources.list /etc/apt/sources.list.bak

ADD resources/source.list /etc/apt/sources.list


RUN \
    /bin/chmod 755 /crescent/bin/*.sh \
    && pip install --upgrade pip \
    && apt update \
    && echo "nameserver 8.8.8.8" >>/etc/resolv.conf

RUN mkdir -p /crescent/workspace \
    && y | conda install jupyter notebook \
    && y | conda install ipykernel

# 安装基本使用库
RUN \
    y | conda install scikit-learn \
    y | conda install matplotlib


EXPOSE 8888

ENTRYPOINT ["/bin/bash","/crescent/bin/startup.sh"]
