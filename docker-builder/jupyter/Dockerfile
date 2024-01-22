#crescent/jupyter

FROM python:3.6

ENV JULIA_VERSION 1.2.0
ENV JULIA_URL https://mirrors.ustc.edu.cn/julia/releases/v1.2/julia-1.2.0-linux-x86_64.tar.gz  
ENV CONDA_URL https://repo.anaconda.com/archive/Anaconda2-5.2.0-Linux-x86_64.sh

# Add build payloads
ADD ./bin /crescent/bin/

RUN \
    /bin/chmod 755 /crescent/bin/*.sh \
    && pip3 install --upgrade pip \
    && gpg --keyserver keyserver.ubuntu.com --recv 3B4FE6ACC0B21F32 \
    && gpg --export --armor 3B4FE6ACC0B21F32 | apt-key add - \
    && apt update \
    && echo "nameserver 8.8.8.8" >>/etc/resolv.conf

RUN curl -fSL $JULIA_URL -o /crescent/julia-${JULIA_VERSION}.tar.gz \
    && mkdir -p /crescent/local \
    && tar xfz /crescent/julia-${JULIA_VERSION}.tar.gz -C /crescent/local \
    && ls /crescent/local \
    && rm -rf /crescent/julia-${JULIA_VERSION}.tar.gz 

ENV PATH $PATH:/crescent/local/julia-${JULIA_VERSION}/bin
#

# Install anaconda for python 3.6
RUN mkdir -p /crescent/resource \
    && curl -fSL $CONDA_URL -o /crescent/resource/anaconda.sh \
    && /bin/bash /crescent/resource/anaconda.sh -b -p /opt/conda \
    && rm /crescent/resource/anaconda.sh \
    && echo "export PATH=/opt/conda/bin:$PATH" >> ~/.bashrc \
    && echo ". /opt/conda/etc/profile.d/conda.sh" >> ~/.bashrc \
    && echo "conda activate jupyter_env" >> ~/.bashrc

ENV PATH $PATH:/opt/conda/bin
#Install ANACONDA Environment
RUN conda create -y -n jupyter_env python=3.6 anaconda \
    && conda activate jupyter_env \
	&& y | conda install tensorflow==2.0.0 \
    && y | conda install keras \
    && y | conda install tornado \
    && y | conda install jupyter-tensorboard \
    && y | conda install jupyterlab

EXPOSE 8888

ENTRYPOINT ["/bin/bash","/crescent/bin/startup.sh"]
