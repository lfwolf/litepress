#!/bin/sh

path=$(dirname `pwd`)
remote=/root/litepress/litepress-admin
jarname=litepress-admin.jar

cd $path
mvn clean package -Dmaven.test.skip=true -Pdev
scp $path/target/$jarname wxapp02:$remote/$jarname
scp $path/doc/run.sh wxapp02:$remote/
ssh root@wxapp02 "cd $remote; sh run.sh"