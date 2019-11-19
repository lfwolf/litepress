#!/bin/sh

path=$(dirname `pwd`)
remote=/root/litepress/litepress-api

cd $path
mvn clean package -Pdev
scp $path/target/litepress-api-0.0.1-SNAPSHOT.jar wxapp02:$remote/litepress-api.jar
scp $path/doc/run.sh wxapp02:$remote/
ssh root@wxapp02 "cd $remote; sh run.sh"
