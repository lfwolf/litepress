#!/bin/sh
file=api.pid
if [  -f "$file" ]; then
        pid=$(cat $file)
        kill -9 $pid
        if [ $? -ne 0 ]; then
                echo "kill job[$pid] failed!"
                echo "please remove $file file and try again!"
                exit -1
        fi
fi
nohup java -jar litepress-api.jar 2>1 > out.log &
pid=$!
echo "PID of this script: $pid"
echo $pid > $file
