<template>
  <div>
    <router-view :key="$route.fullPath"></router-view>
    <router-view name="tabbar"></router-view>
  </div>
</template>

<script>
export default {
  name: 'App',
  data() {
    return {
      active: 'home'
    }
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
  font-size: 12px;
}
img{
  width: 100%;
  height: 100%;
  object-fit: cover;
}
</style>
