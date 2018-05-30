<template>
  <div id="app-content">
    <div id="app-body">
      <div class="l-app-hd">
        <div class="l-flex-hc" @click="showMenu = !showMenu">
          <div class="_logo">喜蜂鸟网络科技服务有限公司</div>
          <div class="l-rest"></div>
          <div class="_mbtn"></div>
        </div>
        <div class="l-app-menu" v-show="showMenu" @click="showMenu = false">
          <router-link to="/" class="_item" :class="{_active: $route.path == '/'}">首页</router-link>
          <router-link to="/youshi" class="_item" :class="{_active: $route.path == '/youshi'}">合作优势</router-link>
          <router-link to="/joinus" class="_item" :class="{_active: $route.path == '/joinus'}">招商加盟</router-link>
          <router-link to="/news" class="_item" :class="{_active: $route.path == '/news'}">新闻资讯</router-link>
          <router-link to="/about" class="_item" :class="{_active: $route.path == '/about'}">关于我们</router-link>
          <router-link to="/contact" class="_item" :class="{_active: $route.path == '/contact'}">联系我们</router-link>
        </div>
      </div>
      <transition @after-enter="$vux.bus && $vux.bus.$emit('vux:after-view-enter')" :name="viewTransition" :css="!!direction">
        <router-view class="page-view"></router-view>
      </transition>
      <div class="l-ft-ask l-flex-hc">
        <a :href="'tel:' + $config.tel" class="l-rest">
          <img src="./assets/images/20180529006.jpg" alt="">
          <span>立即咨询</span>
        </a>
        <router-link to="/joinus" class="l-rest">
          <img src="./assets/images/20180529007.jpg" alt="">
          <span>我要加盟</span>
        </router-link>
      </div>
    </div>

    <loading v-model="loading"></loading>
  </div>
</template>

<script>
import { Loading, Tabbar, TabbarItem, throttle } from 'vux'
import { mapGetters } from 'vuex'
export default {
  name: 'app',
  components: {
    Tabbar,
    TabbarItem,
    Loading
  },
  computed: {
    ...mapGetters(['loading', 'scrollTop', 'direction']),
    viewTransition() {
      if (!this.direction) return ''
      return 'vux-pop-' + this.direction
    }
  },
  data() {
    return {
      showMenu: false,
      userInfo: null,
      scrollElem: {
        scrollTop: 0,
        scrollHeight: 0,
        clientHeight: window.innerHeight
      }
    }
  },
  watch: {
    '$route.path': {
      immediate: true,
      deep: true,
      handler() {
        this.onScroll()
      }
    }
  },
  methods: {
    scrollHandler(event) {
      this.$router.savedScroll[this.$route.fullPath] = event.target.scrollTop
    },
    onScroll() {
      this.noPreventBounce = !!document.querySelector('[no-prevent-bounce]')
      this.$nextTick(_ => {
        let viewBoxs = document.querySelectorAll('#vux_view_box_body')
        let scrollElem = viewBoxs[1] || viewBoxs[0]
        if (scrollElem) {
          setTimeout(_ => scrollElem.scrollTop = this.scrollTop)
          scrollElem.addEventListener('scroll', throttle(this.scrollHandler, 500), false)
          this.scrollElem = scrollElem
        }
      })
    },
    preventBounce() { // 禁止ios 无滚动时页面反弹效果
      let scrollData = { posY: 0, maxScroll: 0 }
      let appBody = document.querySelector('#app-body')
      appBody.addEventListener('touchstart', event => {
        let e = event.touches[0] || event
        // 垂直位置标记
        scrollData.posY = e.pageY
        // 是否可以滚动
        if(this.scrollElem) {
          scrollData.maxScroll = this.scrollElem.scrollHeight - this.scrollElem.clientHeight
        }
      }, false)

      appBody.addEventListener('touchmove', event => {
        if(this.noPreventBounce) return

        let e = event.touches[0] || event
        // 如果不足于滚动，则禁止触发整个窗体元素的滚动
        if (scrollData.maxScroll <= 0) {
          // 禁止滚动
          return event.preventDefault()
        }

        // 当前移动的垂直位置，用来判断是往上移动还是往下
        let distanceY = e.pageY - scrollData.posY
        // 上下边缘检测
        if (distanceY > 0 && this.scrollElem.scrollTop == 0) {
          // 往上滑，并且到头
          // 禁止滚动的默认行为
          return event.preventDefault()
        }

        // 下边缘检测
        if (distanceY < 0 && this.scrollElem.scrollTop + 1 >= scrollData.maxScroll) {
          // 往下滑，并且到头
          // 禁止滚动的默认行为
          return event.preventDefault()
        }
      }, false)
      
      appBody.addEventListener('touchend', event => {
        scrollData.maxScroll = 0
      }, false)
    }
  },
  mounted() {
    this.preventBounce()
  }
}
</script>

<style lang="less">
@import "~vux/src/styles/reset.less";
@import "~vux/src/styles/1px.less";
@import "./assets/base.less";
#vux_view_box_body{padding: 0;}
.l-app-hd{
  position: absolute; top:0; left:0; width: 100%; background-color: #fff; height: 46px; z-index: 1000;
  ._logo{
    width: 250px; height: 46px; line-height: 46px; padding-left: 12%; font-weight: 500;
    background: url('./assets/images/20180527001.jpg') 10px 50% no-repeat;
    background-size: 10%;
  }
  ._mbtn{
    width: 20px; height: 15px; background: url('./assets/images/20180529012.jpg') 0 50% no-repeat;
    background-size: contain; margin-right: 10px;
  }
}

.l-app-menu{
  background-color: #f8f8f8; overflow: hidden; 
  ._item{
    display: block; padding: 10px 0; text-align: center; color: #999;
  }
  ._item._active, ._item:active{background-color: #ecedf1; color: rgb(235, 97, 0);}
}

.l-ft-ask{
  height: 50px; background-color: #fff; padding: 10px; text-align: center; box-sizing: border-box;
  position: fixed; left: 0; right: 0; bottom: 0; z-index: 1000;
  img{width: 20px; height: 20px; vertical-align: -4px; margin-right: 5px;}
  a{color: inherit;}
}
</style>
