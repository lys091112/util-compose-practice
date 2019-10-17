#crescent/jupyter

FROM python:3.7

# Add build payloads
ADD ./bin /crescent/bin/
ADD ./resource/anaconda.sh /crescent/resource/

RUN \
    /bin/chmod 755 /crescent/bin/*.sh \
    && pip3 install --upgrade pip \
    && apt update
#    && echo "nameserver 8.8.8.8" >>/etc/resolv.conf \
#

# Install anaconda for python 3.7
RUN /bin/bash /crescent/resource/anaconda.sh -b -p /opt/conda \
    && rm /crescent/resource/anaconda.sh\
    && echo "export PATH=/opt/conda/bin:$PATH" >> ~/.bashrc

ENV PATH $PATH:/opt/conda/bin
#Install ANACONDA Environment
RUN conda create -y -n jupyter_env python=3.7 anaconda \
	&& pip install tensorflow keras jupyter-tensorboard jupyterlab


EXPOSE 8888

ENTRYPOINT ["/bin/bash","/crescent/bin/startup.sh"]
