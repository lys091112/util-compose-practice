#!/bin/bash

BASE_DIR=''

function generate_basedir() {
    if [ "x${BASE_DIR}" = "x" ]; then
        BASE_DIR=$(cd `dirname $0`/..; pwd)
    fi
}

function check_app_status() {
    if [ ! -f ${BASE_DIR}/bin/mock.pid ]; then
        echo "NO Application is Running!"
        exit 1
    fi
}

function shutdown() {
    # get pid info
    PID=`cat ${BASE_DIR}/bin/mock.pid`
    if ps -p ${PID} > /dev/null; then
        echo "Stopping Application ${PID}! "
        kill -9 ${PID}
        rm ${BASE_DIR}/bin/mock.pid
    else
        echo "Maybe Application Killed By System Or Delete Derictly"
        exit 1
    fi
}

generate_basedir
check_app_status
shutdown
echo "Stop Application Success!"

