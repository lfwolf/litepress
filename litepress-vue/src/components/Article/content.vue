<template>
  <van-panel :title="info.title" :desc="info.addtime">
    <div v-html="info.content"></div>
  </van-panel>
</template>
<script>
import { getArticleContent } from '@/api/main'
import Vue from 'vue';
import { Panel } from 'vant';

Vue.use(Panel);
export default {
  data() {
    return {
      info: {}
    }
  },
  created: function() {
    let id = this.$route.params.id
    getArticleContent(id).then(res=>{
      if( res.data.code == 1){
        this.info = res.data.data;
        
      }  
    });
  }
}
</script>
<style scoped>
.van-cell__title {
  margin-top:20px;
  font-weight: 500;
  font-size: 22px;
}
.van-panel__content{
  font-size: 12px;
  margin: 10px 20px;
}
</style>