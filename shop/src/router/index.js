import Vue from 'vue'
import Router from 'vue-router'
import { device, storage } from '../assets/utils'
import store from '../store'
import routes from './routes'

Vue.use(Router)
const router = new Router({ 
  routes
})

// 记录滚动位置
router.savedScroll = {}

// 记录历史路由
const pageHistory = storage.session.get('page_history') || []

// 微信导航标题
const setTitle = title => {
  document.title = title || '微信浏览器'
  // 判断是否为ios设备的微信浏览器，加载iframe来刷新title
  if (device.isWechat && device.isIphone) {
    let iframe = document.createElement('iframe')

    iframe.setAttribute('style','position:absolute;visibility:hidden;height:0;width:0;');
    iframe.addEventListener('load', function load() {
      iframe.removeEventListener('load', load)
      document.body.removeChild(iframe)
    })

    setTimeout(()=>{
      iframe.setAttribute('src', '//m.baidu.com/favicon.ico')
      document.body.appendChild(iframe)
    }, 650)
  }
}

let touchEndTime = Date.now()
document.addEventListener('touchend', _ => touchEndTime = Date.now())

// 判断是否是router触发的路由事件，否则就是系统事件(左滑返回)
let isRouterEvent = false
let routerEvent = ['push', 'go', 'replace', 'forward', 'back']
routerEvent.forEach(key => {
  let method = router[key].bind(router)
  router[key] = function (...args) {
    isRouterEvent = true
    method.apply(null, args)
  }
})

router.beforeEach((to, from, next) => {
  // http直接跳转
  if (/\/http/.test(to.path)) {
    let url = to.path.split('http')[1]
    window.location.href = `http${url}`
    return next(false)
  }

  store.commit('updateLoading', { loading: true })
  store.commit('updateScrollTop', { top: router.savedScroll[to.fullPath] || 0 })

  // tabbar页面切换
  if(from.meta.tabbar && to.meta.tabbar) {
    store.commit('updateDirection', {direction: 'fade'})
    pageHistory[pageHistory.length - 1] = to.fullPath

  // 第一个页面进入
  }else if(from.fullPath === '/' && !from.name) {
    store.commit('updateDirection', {direction: 'fade'})
    if(pageHistory[pageHistory.length - 1] !== to.fullPath) {
      pageHistory.push(to.fullPath)
    }
  // 页面返回
  }else if(pageHistory[pageHistory.length - 2] === to.fullPath && 
    pageHistory[pageHistory.length - 1] === from.fullPath) {

    // 判断是否是ios左滑返回
    if (!isRouterEvent && (Date.now() - touchEndTime) < 377) {
      store.commit('updateDirection', {direction: ''})
    } else {
      store.commit('updateDirection', { direction: 'out' })
    }
    pageHistory.pop()
  // 正常页面进入
  }else {
    store.commit('updateDirection', {direction: 'in'})
    pageHistory.push(to.fullPath)
  }

  next()
  
})

router.afterEach(to => {
  isRouterEvent = false
  store.commit('updateLoading', { loading: false })
  storage.session.set('page_history', pageHistory)
  setTitle(to.meta.title)
})

export default router