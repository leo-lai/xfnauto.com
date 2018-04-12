import { storage, utils, getGrantUrl, toptip } from '../assets/utils'
import Vue from 'vue'
import Router from 'vue-router'
import store from '../store'
import routes from './routes'


Vue.use(Router)
const router = new Router({ mode: 'history', routes })

// 记录滚动位置
router.savedScroll = {}

// 记录历史路由
const pageHistory = storage.session.get('page_history') || []
router.pageHistory = pageHistory

router.backHistory = (index = -2) => {
  if(pageHistory.length > 1) {
    router.push(pageHistory[index > -1 ? index : pageHistory.length + index])
  }else {
    router.back()
  }
}

// 判断是否是router触发的路由事件，否则就是系统事件(左滑返回)
let touchEndTime = Date.now()
document.addEventListener('touchend', _ => touchEndTime = Date.now())

let routerEventName = ''
let routerEvent = ['push', 'replace', 'go', 'forward', 'back']
routerEvent.forEach(key => {
  let method = router[key].bind(router)
  router[key] = function (...args) {
    routerEventName = key
    method.apply(null, args)
  }
})

// 记录微信的Landing Page，用于IOS微信JSSDK授权路径
router.landingUrl = location.href

router.beforeEach((to, from, next) => {
  // 微信页面授权
  if(to.meta.scope && !to.query.code) {
    window.location.href = getGrantUrl(to.fullPath, null, to.meta.scope)
    return next(false)
  }

  // http直接跳转
  if (/\/?http/.test(to.fullPath)) {
    let url = to.fullPath.replace(/\/?(http)/, '$1')
    window.location.href = url
    return next(false)
  }

  // 验证登录
  if (to.meta.auth && !storage.local.get('token')) {
    storage.local.remove('token')
    storage.local.remove('userinfo')
    routerEventName = 'push'
    store.commit('updateDirection', { direction: 'in' })
    return next(`/login?to=${to.fullPath || ''}`)
  }

  let direction = ''
  // tabbar页面切换
  if(from.meta.tabbar && to.meta.tabbar) {
    pageHistory[pageHistory.length - 1] = to.path
    direction = 'fade'
  // 页面返回
  } else if (pageHistory[pageHistory.length - 2] === to.path) {
    // 判断是否是ios左滑返回
    if (!routerEventName && (Date.now() - touchEndTime) < 377) {
      direction = ''
    } else {
      direction = 'out'
    }
    pageHistory.pop()

  // 第一个页面进入
  } else if (from.path === '/' && !from.name) {
    if (pageHistory[pageHistory.length - 1] !== to.path) {
      pageHistory.push(to.path)
    }
    direction = 'fade'
  // 正常页面进入
  }else {
    direction = 'in'
    if (routerEventName === 'replace') {
      pageHistory[pageHistory.length - 1] = to.path
    }else{
      pageHistory.push(to.path)
    }
  }

  store.commit('updateDirection', { direction: to.query.direction || direction})
  store.commit('updateLoading', { loading: true })
  store.commit('updateScrollTop', { top: router.savedScroll[to.path] || 0 })
  next()
})

router.afterEach((to, from) => {
  routerEventName = ''
  store.commit('updateLoading', { loading: false })
  storage.session.set('page_history', pageHistory)
  utils.setTitle(to.meta.title)
  router.from = from
})

export default router