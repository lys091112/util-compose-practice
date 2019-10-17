#!/bin/bash

# BIN_DIR="/crescent/local/bin"

# python ./loop.py
/opt/conda/envs/jupyter_env/bin/jupyter notebook --ip=* --no-browser --allow-root --notebook-dir=/tmp

#while true; do echo hello world; sleep 1; done
tail -f /dev/null


