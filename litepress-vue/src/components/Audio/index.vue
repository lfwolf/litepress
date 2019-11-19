<template>
  <div>
<van-list
  v-model="loading"
  :finished="finished"
  finished-text="没有更多了"
  @load="onLoad"
>
      <van-cell is-link center
        v-for="(item,index) in list"  
        :title="item.name" 
        :key="index" 
        :to="{name:'audioContent',params:item}" >
        <template slot="title">
          <div>
          <img style="width:60px;vertical-align: middle;" :src="item.cover" />
          <span class="truncate" v-html="item.name" />
          </div>
        </template>
      </van-cell>
</van-list>
  </div>
</template>
<script>
import { getAudioList } from '@/api/main'
import Vue from 'vue';
import { Image } from 'vant';
import { Icon } from 'vant';
import { List } from 'vant';

Vue.use(List);
Vue.use(Icon);
Vue.use(Image);
const name='audio.vue'
// eg: http://cdnimg103.lizhi.fm/audio_cover/2019/10/22/2766973206213969927.jpg

export default {
  data() {
    return {
      curpage: 1,
      size: 5,
      total: 0,
      list: [],
      loading: false,
      isLoading: false,
      finished: false
    }
  },
  created: function(){
    console.log(name +'created.')
  },
  mounted: function(){
    console.log(name +'mounted.')
    

    
  },
methods: {
  onLoad(){
    console.log("vant-cell onLoad")
    getAudioList(this.curpage).then(res => {
      this.loading = false;
      res=res.data
      //res.audios.forEach(function(element) {
      //  element.url = 'http://cdnimg103.lizhi.fm/audio_cover/' + element.url
      //})
      this.list = this.list.concat(res.audios)
      this.total = res.total
      this.curpage = res.p
      this.size = res.size
      if( this.size * this.curpage <  this.total){
        this.curpage++
      }else{
        
        this.finished = true;
      }
    })
  }
}

  
}
</script>
<style scoped>
img.van-image__img {
  width:  80px;

}
.name {
  text-overflow:ellipsis;
  white-space:nowrap; 
  overflow:hidden;
  vertical-align: middle;
}
.truncate {
  width: 100px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
</style>
