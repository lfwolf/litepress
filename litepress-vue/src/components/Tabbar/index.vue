<template>
    <van-tabbar v-model="active" route>
      <van-tabbar-item name="home" icon="home-o" replace to="/">首页</van-tabbar-item>
      <van-tabbar-item name="search" icon="apps-o" replace to="/category">分类</van-tabbar-item>
      <van-tabbar-item name="friends" icon="friends-o" replace to="/articlelist">文章</van-tabbar-item>
     <!-- <van-tabbar-item name="setting" icon="setting-o" replace to="/user">我的</van-tabbar-item> -->
    </van-tabbar>
</template>

<script>
import { Tabbar, TabbarItem } from 'vant';

export default {
  data: function () {
    return {
      active: 'home',
      msg: 'ok'
    }
  }
}
</script>
