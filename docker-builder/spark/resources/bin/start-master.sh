#!/bin/bash


echo "\n"
# 格式化NameNode
hdfs namenode -format


echo "\n"
# 启动HDFS
start-all.sh

