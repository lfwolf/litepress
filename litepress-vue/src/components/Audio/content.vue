<template>
  <div style="margin-top:10px;">
    <van-popup v-model="show">内容</van-popup>
    <van-row>
      <van-col span="4" click="showPopup"><van-image :src="info.cover" round  /></van-col>
      <van-col span="19" offset="1">
        <router-link to="{name:'home'}" >
          <span>{{info.name}}</span>
        </router-link>
        <div style="margin-top:10px;">
            <audio controls ref='audio' class="audio">
              <source v-bind:src="info.url" type="audio/mpeg">
            </audio>
        </div>
        <span style="font-size:14px;">{{info.createTime | formatDate}}</span>
      </van-col>
    </van-row>
<van-nav-bar
  :title="info.name"
  left-text="上一篇"
  right-text="下一篇"
  @click-left="onClickLeft"
  @click-right="onClickRight"
/>
    <van-image :src="imgurl" />    

  </div>
</template>
<script>
import { getVoiceInfo } from '@/api/main'
import { formatDate } from '@/utils/date.js'

import Vue from 'vue';
import { Image } from 'vant';
import { Row, Col } from 'vant';
import { Popup } from 'vant';
import { NavBar } from 'vant';

Vue.use(NavBar);

Vue.use(Popup);

Vue.use(Row).use(Col);

Vue.use(Image);
export default {
  data() {
    return {
      info : { createTime : new Date()},
      imgurl: "",
      show:false,
    }
  },
  created: function(){
    getVoiceInfo(this.$route.params.id).then(response=>{
      console.log(response)
      if(response.status !== 200){ return }
      let res = response.data;
      if( res.data.code > 0) return;
      let userVoice = res.data.data.userVoice
      let voiceIds = res.data.data.voiceIds
      
      this.info  = {
        id: userVoice.voiceInfo.voiceId,
        cover: userVoice.voiceInfo.imageUrl ,
        name: userVoice.voiceInfo.name,
        url: userVoice.voicePlayProperty.trackUrl,
        band: userVoice.userInfo.band,
        createTime: new Date(userVoice.voiceInfo.createTime*1000),
        nextId : voiceIds[1],
        prevId : voiceIds[0]
      }
      if( this.info.band == "1367391" ){
        //古诗类目图片按id名保存在本地。
        this.imgurl = "/static/img/lizhi-cover/"+this.info.id+".jpg";
      }else{
        this.imgurl = this.info.cover;
      }
      this.$refs.audio.src = this.info.url;

    })
  },
  mounted: function(){
    
  },
  filters: {
    formatDate(time) {
        var date = new Date(time);
        return formatDate(date, 'yyyy-MM-dd hh:mm:ss');
      }
  },
  methods: {
    showPopup() {
      this.show = true;
    },
    onClickLeft() {
      this.$router.replace({ name: 'VoiceInfo', params: { id: this.info.prevId }})
      
    },
    onClickRight() {
      this.$router.replace({ name: 'VoiceInfo', params: { id: this.info.nextId }})
      
    }
  }
}
</script>
<style scoped>
.audio audio {
    filter: sepia(20%) saturate(70%) grayscale(1) contrast(99%) invert(12%);
    width: 250px;
    height: 25px;
}
</style>