<template>
  <div id="home">
    <van-swipe :autoplay="2000" indicator-color="white">
      <van-swipe-item v-for="item in banners" :key="'banner-'+item.id"><img v-bind:src="item.cover"></van-swipe-item>
    </van-swipe>
    <van-grid>
      <van-grid-item v-bind:icon="item.cover" v-bind:text="item.name" v-for="(item,index) in catalogs" 
        @click="goContent(item.url)"
        :key="'catalog-'+index"/>
    </van-grid>
    <span class="head">更多推荐</span>
    <van-list
      v-model="loading"
      :finished="finished"
      finished-text="没有更多了"
      @load="onLoad"
    >
      <van-cell 
        v-for="item in recommends2"
        :key="item.id"
        :title="item.title"
        :to="'/album/'+item.zid"
      >
      <template slot="title">
        <img v-bind:src="item.cover" /> 
        <span v-html="item.title" ></span>
      </template>
      </van-cell>
    </van-list>

  </div>
</template>
<script>
import Vue from 'vue'
import { getBanner, getCatalog, getRecommends } from '@/api/main'
import { Swipe, SwipeItem, Lazyload } from 'vant'
import { Grid, GridItem } from 'vant';
import { List, Cell } from 'vant';

Vue.use(List).use(Cell);
Vue.use(Grid).use(GridItem);
Vue.use(Swipe).use(SwipeItem).use(Lazyload)

/* eslint-disable */
export default {
  data () {
    return {
      loading: false,
      finished: false,
      images: [
        'https://img.yzcdn.cn/vant/apple-1.jpg',
        'https://img.yzcdn.cn/vant/apple-2.jpg'
      ],
      banners: [],
      catalogs:[
        {"id":1,"cover":"https://cdn.qian.fm/upload/img/20180815/174515981.png","name":"古诗朗诵","url":"/voice/list/2667635973481609793"},
        {"id":2,"cover":"https://cdn.qian.fm/upload/img/20180815/174526747.png","name":"学英语","url":"/voice/list/2692636203113692225"},
        {"id":3,"cover":"https://cdn.qian.fm/upload/img/20180815/174535168.png","name":"卡通绘本","url":"/voice/list/2617728042945074241"},
        {"id":4,"cover":"https://cdn.qian.fm/upload/img/20180815/174543481.png","name":"英语伴读","url":"/voice/list/ieng-1"},
        {"id":5,"cover":"https://cdn.qian.fm/upload/img/20180815/174543481.png","name":"师仙李白","url":"/iframe?src=http://www.neumedias.com/store/index.php/online/series_new?id=855"},
        {"id":6,"cover":"https://cdn.qian.fm/upload/img/20180815/174543481.png","name":"八仙过海","url":"/iframe?src=http://www.neumedias.com/store/index.php/online/series_new?id=244"},
        {"id":7,"cover":"https://cdn.qian.fm/upload/img/20180815/174543481.png","name":"十万个为什么","url":"/iframe?src=http://www.neumedias.com/store/index.php/online/series_new?id=279&org_id=35&user_id="},
        {"id":8,"cover":"https://cdn.qian.fm/upload/img/20180815/174543481.png","name":"黑脸包公","url":"/iframe?src=http://baoan.3eol.com.cn/Cartoon/mobile/Index?raw_id=631"}
      ],
      recommends:[],
      recommends2:[]
    };
  },
  created: function () {
    getBanner().then(res=>{
      if( res.data.code == 1){
        this.banners = res.data.data;
      }  
    });
    
    getCatalog().then(res=>{
      //this.catalogs = []
      console.log(res.data)
      if( res.data.code == 1){
        //console.log(res.data.data[0]["cover"])
        this.catalogs = this.catalogs.concat( res.data.data );
      }  
      //console.log(this.catalogs);
    });
    

  },
  mounted: function () {

  },
  methods: {
    goContent(url){
      console.log(url);
      if( url.startsWith("http")){
        this.$router.replace({ name: 'iframe', params: { src: url }})
      }else{
        this.$router.push(url)
      }
    },
    onLoad() {
      getRecommends().then(res=>{
        console.log(res.data.code)
        if( res.data.code == 1){
          this.recommends2 = res.data.data;
          this.loading = false;
          this.finished = true;
        }  
      });
    }
  }
  
}
</script>

<style scoped>
#home {
  text-align: center;
}

#banner {
  height: 120px;
}
.carousel-item {
  height: 120px;
}
.catalog img{
  width: 80%;
}
.catalog span{
  display: box;
  text-align: center;
  font-size: 10px;
}
span.head {
  margin: 10px 1px;
  float: left;
  font-size:  16px;
  font-weight: 600;
}
.article {
  padding: 5px 10px;
}
.recommand2 {
  text-align: left;
}
.recommand2 span{
  display:inline-block;
  margin-top: 10px;
  margin-bottom: 20px;
}
.van-cell__title  {
  text-align: left;
}
.van-cell__title img{
 height: 80%;
 width: 100%;
 margin-bottom: 5px;
}

</style>
