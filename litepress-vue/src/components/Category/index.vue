<template>
  <div class="cat">
    <div v-for="(item,index) in list" :key="index">
    <div class="title"><span  v-text="item.cname"></span></div>
    <van-grid :column-num="3">
      <van-grid-item v-for="(img,index) in item.list" :key="'img'+index">
        <router-link :to="'/album/'+img.zid">
          <van-image v-bind:src="img.cover" />
        </router-link>
      </van-grid-item>
    </van-grid>
    </div>
  </div>
</template>
<script>
import Vue from 'vue';
import { Row, Col } from 'vant';
import { Image } from 'vant';
import { getCategory } from '@/api/main'

Vue.use(Image);

Vue.use(Row).use(Col);

export default {
  data () {
    return {
      list: []
    }
  },
  created: function() {
    getCategory().then(res=>{
      console.log(res);
      if( res.data.code == 1){
        this.list = res.data.data;
        
      }  
    });
  }
  
}
</script>
<style scoped>
.cat {
  margin: 20px 10px;
}
div.title {
  font-weight: 700;
}
</style>