<template>
  <div id="app-content">
    <div id="app-body" class="no-header" :class="{'no-tabbar': !$route.meta.tabbar}">
      <transition @after-enter="$vux.bus && $vux.bus.$emit('vux:after-view-enter')" :name="viewTransition" :css="!!direction">
        <router-view class="page-view"></router-view>
      </transition>

      <transition :name="viewTransition" :css="!!direction || direction === 'fade'">
        <tabbar v-show="$route.meta.tabbar">
          <tabbar-item :link="{path: '/', replace: true}" :selected="$route.path === '/'">
            <img slot="icon" src="./assets/images/tabbar-01.png">
            <img slot="icon-active" src="./assets/images/tabbar-01-active.png">
            <span slot="label">首页</span>
          </tabbar-item>
          <tabbar-item v-if="!orgCode" :link="{path: '/loan', replace: true}" :selected="$route.path === '/loan'">
            <img slot="icon" src="./assets/images/tabbar-02.png">
            <img slot="icon-active" src="./assets/images/tabbar-02-active.png">
            <span slot="label">{{userInfo && userInfo.userType == 2 ? '找垫资' : '0-1成首付'}}</span>
          </tabbar-item>
          <tabbar-item :link="{path: '/me', replace: true}" :selected="$route.path === '/me'">
            <img slot="icon" src="./assets/images/tabbar-03.png">
            <img slot="icon-active" src="./assets/images/tabbar-03-active.png">
            <span slot="label">我的</span>
          </tabbar-item>
        </tabbar>
      </transition>
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
    ...mapGetters(['orgCode', 'loading', 'scrollTop', 'direction']),
    viewTransition() {
      if (!this.direction) return ''
      return 'vux-pop-' + this.direction
    }
  },
  data() {
    return {
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
      // deep: true,
      handler() {
        this.onScroll()
        this.$api.user.getInfo().then(data => this.userInfo = data)
        let orgCode = this.$route.query.sc
        if(orgCode) {
          this.$store.commit('updateOrgCode', { orgCode })
          this.$storage.session.set('org_code', orgCode)
        }
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
</style>
