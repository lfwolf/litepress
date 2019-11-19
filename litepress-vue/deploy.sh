#!/bin/sh
remote=/usr/share/nginx/html/press/

npm run build
scp -r `pwd`/dist/. wxapp02:$remote
