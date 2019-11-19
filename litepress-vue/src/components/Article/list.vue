<template>
  <van-cell-group>
  <van-cell v-for="(item,index) in list" :key="index" :title="item.title" 
  :to="'/articlecontent/'+item.id"
  :label="item.pubdate">
      <!-- 使用 right-icon 插槽来自定义右侧图标 -->
    <van-image round
      width="80"
      height="80"
      :src="item.cover"
    />
  </van-cell>
</van-cell-group>
</template>
<script>
import { getArticleList } from '@/api/main'
import Vue from 'vue';
import { Cell, CellGroup } from 'vant';
import { Image } from 'vant';

Vue.use(Image);

Vue.use(Cell).use(CellGroup);
export default {
  data() {
    return {
      list: []
    }
  },
  created: function() {
  getArticleList().then(res=>{
    if( res.data.code == 1){
      this.list = res.data.data;
      
    }  
  });
}
}
</script>