<template>
  <div>
<van-pull-refresh v-model="isLoading" @refresh="onRefresh">
</van-pull-refresh>

<van-cell-group title="">
  <van-cell :title="bandinfo.name" value="内容" :label="bandinfo.intro">
    <template slot="default">
      <van-image :src="bandinfo.cover"  radius="20" width="8rem" 
  height="8rem"
  fit="contain" />
    </template>
  </van-cell>
</van-cell-group>

<van-divider :style="{ color: '#1989fa', borderColor: '#1989fa', padding: '0 16px' }">
  列表（{{bandinfo.voiceNum}}）
</van-divider>

<van-list
  v-model="loading"
  :finished="finished"
  finished-text="没有更多了"
  @load="onLoad"
>
      <van-cell is-link center
        v-for="(item,index) in list"  
        :key="index" 
        
        @click="goContent(item.id,item.url) ">
        <template slot="title">
          <div>
          <img style="width:60px;vertical-align: middle;" :src="item.cover" />
          <span class="truncate" >{{item.name | truncate(16,'...')}} </span>
          </div>
        </template>
      </van-cell>
</van-list>
  </div>
</template>
<script>
import { getVoiceList } from '@/api/main'
import Vue from 'vue';
import { Image } from 'vant';
import { Icon } from 'vant';
import { List } from 'vant';
import { Divider } from 'vant';
import { PullRefresh } from 'vant';

Vue.use(PullRefresh);

Vue.use(Divider);
Vue.use(List);
Vue.use(Icon);
Vue.use(Image);

export default {
  data() {
    return {
      sid: "",
      curpage: 1,
      size: 5,
      total: 0,
      list: [],
      loading: false,
      isLoading: false,
      finished: false,
      bandinfo: {},
      count: 0,
      isLoading: false
    }
  },
  created: function(){
    
  },
  mounted: function(){
    this.sid = this.$route.params.sid;
    
  },
  filters: {
    truncate(text, length, clamp){
      return text.slice(0, length) + (length < text.length ? clamp || '...' : '')
    }
  },
  methods: {
    //跳转到内容
   goContent(id,src) {
      if ( this.sid.startsWith("ieng")  ){
        this.$router.replace({ name: 'iframe', params: { src: src }})
      }else{
        this.$router.replace({name:'VoiceInfo',params:{id: id}})
      }
    },
    
    // 上拉刷新
    onRefresh() {
      this.isLoading = true
      this.count++
      console.log('刷新成功')
      getVoiceList(this.sid,1).then(response => {
        let res = response.data;
        this.$toast('刷新成功');
        this.isLoading = false;
        this.list = res.audios
        this.total = res.total
        this.curpage = res.p
        this.size = res.size
        this.bandinfo = res.bandinfo;
      })
    },
    onLoad(){
      getVoiceList(this.sid,this.curpage).then(response => {
        if( response.status !== 200 ){
          this.$toast('网络异常，获取列表失败！');
          return;
        }
        if( response.data.code !== 1 ){
          this.$toast('服务异常，获取列表失败！');
          return;
        }
        this.loading = false;
        let res=response.data.data;
        console.log("onload - getVoiceList");

        this.list = this.list.concat(res.audios)
        if(this.curpage == 1){
          this.total = res.total
          this.curpage = res.p
          this.size = res.size
          this.bandinfo = res.bandinfo;
          console.log(res);
        }
        if( this.total < 1){
          this.finished = true;
        }
        else if( this.size * this.curpage <  this.total){
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
