<template>
  <div id="album">
    <div class="cover">
      <img v-bind:src="info.cover" />
    </div>
    <van-cell-group>
      <van-cell >
        <template slot="title">
          <span v-html="info.brief" />
        </template>
      </van-cell>
      <van-grid clickable>
        <van-grid-item icon="star-o" text="收藏" />
        <van-grid-item icon="down" text="下载" />
        <van-grid-item icon="share" text="分享" />
        <van-grid-item icon="exchange" text="排序" v-on:click="resort" />
      </van-grid>
      <van-cell v-for="(item,index) in list" :key="'list-'+item.id" :to="'/article/'+item.id">
        <template slot="title">
            <span class="seq">{{index+1}}</span>
            <img class="thumb" v-bind:src="item.cover_small" />
            <span>{{item.title}}</span>
        </template>
      </van-cell>
    </van-cell-group>
  </div>
</template>
<script>
/* eslint-disable */
import Vue from 'vue'
import { Row, Col } from 'vant'
import { getAlbum } from '@/api/main'
import { Grid, GridItem, Cell, CellGroup } from 'vant';

Vue.use(Grid).use(GridItem).use(Cell).use(CellGroup);

Vue.use(Row).use(Col)

export default {
  
  data () {
    return {
      info: {},
      list: [],
      msg: 'message'
    }
  },
  created: function(){
    let albumid = this.$route.params.id;
    getAlbum(albumid).then(res=>{
      if( res.data.code == 1){
        this.info = res.data.info;
        this.list = res.data.list;
      }  
    });
  },
  methods:{
    resort: function() {
      this.list = this.list.reverse();
    }
  }
}
</script>
<style scoped>
.cover {
  height: 120px;
}
.brief {
  margin-top: 10px;
  font-weight: 400;
  font-size: 14px;
}
.tools {
  margin-top: 20px;
  margin-bottom: 20px;
  text-align: center;
}
.tools i {
  font-size: 35px; 
  color: black;
  display: block;
}
.listItem {
  margin-bottom: 10px;

}
.thumb {
  width: 50px;
  height:50px;
  border-radius:100px;
}
</style>
