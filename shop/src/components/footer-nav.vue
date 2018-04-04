<template>
  <div>
    <!-- tabbar -->
    <div class="_place-holder"></div>
    <tabbar>
      <tabbar-item :link="{path: '/', replace: true}" :selected="$route.path === '/'">
        <img slot="icon" src="../assets/images/tabbar-01.png">
        <img slot="icon-active" src="../assets/images/tabbar-01-active.png">
        <span slot="label">首页</span>
      </tabbar-item>
      <tabbar-item :link="{path: '/loan', replace: true}" :selected="$route.path === '/loan'">
        <img slot="icon" src="../assets/images/tabbar-02.png">
        <img slot="icon-active" src="../assets/images/tabbar-02-active.png">
        <span slot="label">0-1成首付</span>
      </tabbar-item>
      <tabbar-item :link="{path: '/me', replace: true}" :selected="$route.path === '/me'">
        <img slot="icon" src="../assets/images/tabbar-03.png">
        <img slot="icon-active" src="../assets/images/tabbar-03-active.png">
        <span slot="label">我的</span>
      </tabbar-item>
    </tabbar>
  </div>
</template>
<script>
import { Tabbar, TabbarItem } from 'vux'
export default {
  name: 'footer-nav',
  components: {
    Tabbar, TabbarItem
  }
}
</script>

<style scoped>
._place-holder{height: 50px;}
</style>
