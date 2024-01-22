#!/bin/bash

# BIN_DIR="/crescent/local/bin"
cd /crescent/bin

### #python ./loop.py
jupyter notebook --ip=* --no-browser --allow-root

#while true; do echo hello world; sleep 1; done
tail -f /dev/null
