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
          <tabbar-item :link="{path: '/loan', replace: true}" :selected="$route.path === '/loan'">
            <img slot="icon" src="./assets/images/tabbar-02.png">
            <img slot="icon-active" src="./assets/images/tabbar-02-active.png">
            <span slot="label">0-1成首付</span>
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
    ...mapGetters(['loading', 'scrollTop', 'direction']),
    viewTransition() {
      if (!this.direction) return ''
      return 'vux-pop-' + this.direction
    }
  },
  data() {
    return {
      // scrollElem: {
      //   scrollTop: 0,
      //   scrollHeight: 0,
      //   clientHeight: window.innerHeight
      // }
    }
  },
  methods: {
    scrollHandler(event) {
      this.$router.savedScroll[this.$route.fullPath] = event.target.scrollTop
    },
    onScroll() {
      this.noPreventBounce = !!document.querySelector('#no-prevent-bounce')
      this.$nextTick(_ => {
        let viewBoxs = document.querySelectorAll('#vux_view_box_body')
        let scrollElem = viewBoxs[1] || viewBoxs[0]
        if (scrollElem) {
          this.scrollElem = scrollElem
          scrollElem.scrollTop = this.scrollTop
          scrollElem.addEventListener('scroll', throttle(this.scrollHandler, 500), false)
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
    this.onScroll()
    this.preventBounce()
  },
  updated() {
    this.onScroll()
  }
}
</script>

<style lang="less">
@import "~vux/src/styles/reset.less";
@import "~vux/src/styles/1px.less";
@import "./assets/base.less";
.l-link-1 {
  text-align: center;
  ._icon {
    display: block;
    width: 50px;
    height: 50px;
    margin: auto;
  }
  ._txt {
    margin-top: 5px;
  }
}

.l-link-2 {
  display: block;
  width: 106px;
  margin: auto;
}
.l-list-1 {
  position: relative;
  padding: 15px 0;
  /* &::after{content: ' '; position: absolute; left: 0; right: 0; bottom: 0; height: 1px; color: #d9d9d9; 
  border-bottom: 1px solid #d9d9d9; transform-origin: 0 100%; transform: scaleY(.5);} */
  p {
    margin-top: 3px;
  }
  ._thumb {
    position: relative;
    border: 1px solid #d9d9d9;
    border-radius: 5px;
    width: 90px;
    height: 90px;
  }
  ._thumb img {
    display: block;
    width: 100%;
    height: 100%;
  }
  ._tag0 {
    display: inline-block;
    background-color: #fd8842;
    color: #fff;
    border-radius: 2px;
    padding: 0 5px;
    font-size: 10px;
    vertical-align: middle;
  }
  ._tag1 {
    display: inline-block;
    border-radius: 10px;
    background-color: #e2f3ff;
    padding: 1px 10px;
    margin-right: 5px;
    font-size: 11px;
  }
  ._tag2 {
    border-radius: 5px;
    border: 1px solid rgb(235, 97, 0);
    color: rgb(235, 97, 0);
    padding: 1px 5px;
    font-size: 10px;
    margin-top: 3px;
  }
  ._loc {
    position: absolute;
    left: 0;
    right: 0;
    bottom: 0;
    border-radius: 0 0 5px 5px;
    height: 20px;
    line-height: 20px;
    background: rgba(0, 0, 0, 0.6);
    color: #fff;
    text-align: center;
    font-size: 11px;
  }
}
</style>
