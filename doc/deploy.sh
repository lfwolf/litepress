#!/bin/sh

path=$(dirname `pwd`)
remote=/root/litepress/litepress-api

# deploy api
cd $path/litepress-api/doc/
sh deploy.sh
# deploy vue
cd $path/litepress-vue/
sh deploy.sh

