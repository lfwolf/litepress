<template>
  <div class="act-form">
    <iframe :src="src" ref="iframe" id="bdIframe" frameborder="0" ></iframe>
  </div>
</template>

<script>

export default {
  data () {
    return {
      src: '你的src'
    }
  },
  created() {
    this.src = this.$route.params.src;
    if( this.src == null ){
      this.src = this.$route.query.src;
    }
    console.log(this.src)
  },
  mounted () {
    /**
     * iframe-宽高自适应显示   
     */
    const oIframe = document.getElementById('bdIframe');
    const deviceWidth = document.documentElement.clientWidth;
    const deviceHeight = document.documentElement.clientHeight;
    oIframe.style.width = (Number(deviceWidth)) + 'px'; //数字是页面布局宽度差值
    oIframe.style.height = (Number(deviceHeight)) + 'px'; //数字是页面布局高度差
  }
}
</script>
<style scoped>
.act-form{
  border: 0px solid red;
}
iframe {
    margin: 0;
    border-style: none;
    width: 100%;
    height: 100%;
}
</style>