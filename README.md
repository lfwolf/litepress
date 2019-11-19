# litepress 项目

## 项目介绍

该项目用于陪伴Evan成长。Evan非常喜欢听钱儿爸的故事和古诗，每天学习很多知识。
1、学古诗（钱儿爸）记录在荔枝上。
2、学英语的记录在ienglish上。
3、学编程在code.org上。
我希望将这些都整合起来，并将我们的成长故事分享给更多父母。

### lietpress-vlue

仿照钱儿爸网站（https://www.qian.fm/）

### litepress-api

vue对应的后台服务接口（VueJS）

### litepress-admin

后台管理系统（SpringBoot）

## 如何部署

介绍如何一键部署到云服务器中。（后续添加docker部署模式）

### 云服务器

建议配置如下 CentOS7.0 + MySQL5.7 + JDK1.8 + nginx。
在本机配置[免密登录](!https://lfwolf.github.io/2019/11/11/configure-login-ssh-with-key-on-synology/)云服务器。

### 申请https

[手把手教你在Nginx上使用CertBot](!https://segmentfault.com/a/1190000005797776)

### nginx配置

在/etc/nginx/conf.d/目录添加litepress.conf，内容如下

```
upstream litepressApi{
  server 127.0.0.1:8280;
}
upstream litepressAdmin{
	server 127.0.0.1:8281;
}
server {
  listen 443;
  server_name www.wycode.xyz;
  access_log /var/log/nginx/wycodexyz.log;
  error_log  /var/log/nginx/wycodexyz-error.log;
  ssl on;
  ssl_certificate /etc/letsencrypt/live/www.wycode.xyz/fullchain.pem;
  ssl_certificate_key /etc/letsencrypt/live/www.wycode.xyz/privkey.pem;
  ssl_trusted_certificate /etc/letsencrypt/live/www.wycode.xyz/chain.pem;
  root /usr/share/nginx/html/press;
  location / {
          index index.html;
  }
  location /api {
          proxy_set_header host $host;
          proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
          proxy_pass http://litepressApi;
  }
  location /admin {
          proxy_set_header host $host;
          proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
          proxy_pass http://litepressAdmin;
  }

}
```

### 修改应用配置

1. 修改litepress-vue下的prod.env.js中的API_ROOT
2. 执行./doc目录下的deploy.sh即可发布。
