#!/bin/bash

BASE_DIR=''
CONFIG_DIR=''
CLASSPATH=''
JAVA_BIN=''
MAIN_CLASS=com.github.mock.Application

function genetate_config() {
	if [ "x$CONFIG_DIR" = "x" ]; then
		CONFIG_DIR=${BASE_DIR}/config/
	fi
}

function generate_basedir() {
    if [ "x${BASE_DIR}" = "x" ]; then
        BASE_DIR=$(cd `dirname $0`;cd ..; pwd)
    fi
}

#function generate_classpath() {
#  for file in ${BASE_DIR}/lib/*.jar;
#  do
#    if [ "x$CLASSPATH" = "x" ]; then
#      CLASSPATH=${file}
#    else
#      CLASSPATH=${CLASSPATH}:${file}
#    fi
#  done
#  # Add static resource
#  CLASSPATH=${CLASSPATH}:${BASE_DIR}
#
#  # Place custom config path at the top of the classpath
#  CLASSPATH=${CONFIG_DIR}:${CLASSPATH}
#}

function generate_classpath() {
  CLASSPATH=${BASE_DIR}/lib/*
  CLASSPATH=${CONFIG_DIR}:${CLASSPATH}
}

# which java to use
function select_java() {
  if [ -z "${JAVA_HOME}" ]; then
    echo -e "No \033[41;37m\$JAVA_HOME\033[0m defined, would try to find java in your \033[41;37m\$PATH\033[0m.\nIf this fails, try defining \033[41;37m\$JAVA_HOME\033[0m"
    command -v java >/dev/null 2>&1 || { echo >&2 "\033[41;37mRequire java but couldn't find it from \$PATH. Aborting.\033[0m"; exit 1; }
    JAVA_BIN="java"
  else
    JAVA_BIN="${JAVA_HOME}/bin/java"
  fi
}


function java_opts() {
  if [ "x$BASE_DIR" = "x" ]; then
    generate_basedir
  fi

  JAVA_OPTS="$JAVA_OPTS -server"
  JAVA_OPTS="$JAVA_OPTS -Xms216m -Xmx1g"
#  JAVA_OPTS="$JAVA_OPTS -XX:+UseG1GC -XX:MaxGCPauseMillis=200 -XX:InitiatingHeapOccupancyPercent=35 -XX:+DisableExplicitGC"
  JAVA_OPTS="$JAVA_OPTS -XX:+UseConcMarkSweepGC -XX:CMSInitiatingOccupancyFraction=60 -XX:+DisableExplicitGC"
  JAVA_OPTS="$JAVA_OPTS -XX:+UseTLAB -XX:+ResizeTLAB"

  # GC LOG
  JAVA_OPTS="$JAVA_OPTS \
  -verbose:gc \
  -Xloggc:${BASE_DIR}/logs/gc.$(date '+%Y%m%d_%H%M%S').log \
  -XX:+PrintGCDetails \
  -XX:+PrintGCTimeStamps \
  -XX:+PrintGCDateStamps \
  -XX:+PrintHeapAtGC \
  -XX:+PrintPromotionFailure \
  -XX:+PrintClassHistogram \
  -XX:+PrintTenuringDistribution \
  -XX:+PrintGCApplicationStoppedTime \
  -XX:+UseGCLogFileRotation \
  -XX:NumberOfGCLogFiles=10 \
  -XX:GCLogFileSize=10M"

  JAVA_OPTS="$JAVA_OPTS -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=${BASE_DIR}/logs"
  JAVA_OPTS="$JAVA_OPTS -XX:ErrorFile=${BASE_DIR}/logs/err.log"

  JAVA_OPTS="$JAVA_OPTS -Dfile.encoding=UTF-8"
  JAVA_OPTS="$JAVA_OPTS -Djava.awt.headless=true"
  JAVA_OPTS="$JAVA_OPTS -Dsun.net.inetaddr.ttl=0"
  JAVA_OPTS="$JAVA_OPTS -Djava.net.preferIPv4Stack=true"
  JAVA_OPTS="$JAVA_OPTS -Djava.security.egd=file:/dev/./urandom"
  JAVA_OPTS="$JAVA_OPTS -Dcom.sun.management.jmxremote=true -Dcom.sun.management.jmxremote.port=50008 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -Djava.rmi.server.hostname=10.128.6.188"
}

function bootstrap() {
 # check_application_running_status
  generate_classpath

  echo "${JAVA_BIN}"
  echo "${JAVA_OPTS}"
  echo "${CLASSPATH}"
  echo "${MAIN_CLASS}"

  exec ${JAVA_BIN} ${JAVA_OPTS} -cp ${CLASSPATH} ${MAIN_CLASS} <&- &

  retval=$?
  PID=$!
  [ ${retval} -eq 0 ] || exit ${retval}
  sleep 7
  if ! ps -p ${PID} > /dev/null ; then
    echo "Failed to start application."
    exit 1
  fi

  printf "%d" ${PID} > "${BASE_DIR}/bin/com.github.mock.pid"
  echo "Successfully launched Application, PID: ${PID}"
  exit 0
}

generate_basedir
genetate_config
select_java
java_opts
bootstrap

