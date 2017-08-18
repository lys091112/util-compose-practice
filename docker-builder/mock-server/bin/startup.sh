#!/bin/bash

BIN_DIR="/crescent/local/bin"

# start mockserver 
echo "Start MockServer"
bash ${BIN_DIR}/start-mock.sh

tail -f /dev/null


