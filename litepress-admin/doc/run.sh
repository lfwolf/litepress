#!/bin/sh
file=admin.pid
jarname=litepress-admin.jar

if [  -f "$file" ]; then
        pid=$(cat $file)
        kill -9 $pid
        if [ $? -ne 0 ]; then
                echo "kill job[$pid] failed!"
                echo "please remove $file file and try again!"
                exit -1
        fi
fi
nohup java -jar $jarname 2>1 > out.log &
pid=$!
echo "PID of this script: $pid"
echo $pid > $file
