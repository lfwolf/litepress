<template>
  <div> 
    <div class="cover"><img v-bind:src="info.cover" /></div>
    <div class="player">
      <audio controls ref='audio'>
        <source v-bind:src="info.url" type="audio/mpeg">
      </audio>
    </div>
    <van-panel v-bind:title="info.title" >
      <div v-html="info.brief"></div>
    </van-panel>
  </div>
</template>
<script>
import { getArticle } from '@/api/main'
import Vue from 'vue';
import { Panel } from 'vant';

Vue.use(Panel);
export default {
  data () {
    return {
      info: {}
    }
  },
  created: function(){
    let id = this.$route.params.id
    getArticle(id).then(res=>{
      if( res.data.code == 1){
        this.info = res.data.data;
        this.$refs.audio.src = this.info.url;
      }  
    });

  }
}
</script>
<style scoped>
.cover {
  width: 100%;
}
#video {
  width: 100%;
}
.player {
  margin: 10px 0px;
  text-align: center;
}
.player i {
  font-size: 30px;
  color: red;
  margin: 10px 0;
}

.desc { 
  font-size: 12px;
  letter-spacing: 1px;
  line-height: 1.5;
}
.van-panel {
  margin:0px 20px;
}
.van-panel__header {
  font-size: 14px;
  font-weight: 600;
}
.van-panel__content {
  font-size: 12px;
  letter-spacing: 1px;
  line-height: 1.5;
  padding-left: 10px;
}

</style>