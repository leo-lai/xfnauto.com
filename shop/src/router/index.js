import { storage, utils, getGrantUrl, toptip } from '../assets/utils'
import Vue from 'vue'
import Router from 'vue-router'
import store from '../store'
import routes from './routes'

Vue.use(Router)
const router = new Router({ 
  base: '/',
  mode: 'history', 
  routes 
})

router.hostURL = location.origin + (router.options.base || '')
router.showLoading = autoClose => {
  router.loading = true
  Vue.$vux.loading.show()
  if (autoClose) {
    router.loadingId = setTimeout(() => {
      router.loading = false
      Vue.$vux.loading.hide()
    }, autoClose)
  }
}
router.hideLoading = _ => {
  router.loading = false
  Vue.$vux.loading.hide()
  clearTimeout(router.loadingId)
}

// 记录滚动位置
router.savedScroll = {}
// 记录历史路由
router.pageHistory = storage.session.get('page_history') || []
router.backHistory = (index = -2) => {
  if(router.pageHistory.length > 1) {
    router.push(router.pageHistory[index > -1 ? index : router.pageHistory.length + index])
  }else {
    router.back()
  }
}

// 判断是否是router触发的路由事件，否则就是系统事件(左滑返回)
let touchEndTime = Date.now()
document.addEventListener('touchend', _ => touchEndTime = Date.now())

let routerEventName = ['push', 'replace', 'go', 'forward', 'back']
routerEventName.forEach(key => {
  let method = router[key].bind(router)
  router[key] = function (...args) {
    router.eventName = key
    method.apply(null, args)
  }
})

// 记录微信的Landing Page，用于IOS微信JSSDK授权路径
router.landingUrl = location.href
// ios 微信返回事件
window.addEventListener('pageshow', _ => {
  router.loading && router.hideLoading()
})

router.beforeEach((to, from, next) => {
  // 微信页面授权
  if(to.meta.scope && !to.query.code) {
    location.assign(getGrantUrl(to.fullPath, null, to.meta.scope))
    router.eventName = ''

    // 如果网络过慢，跳转授权链接，显示loading
    router.showLoading()
    return next(false)
  }

  // http直接跳转
  if (/^\/?http/.test(to.fullPath)) {
    let url = to.fullPath.replace(/^\/?(http)/, '$1')
    router.eventName === 'replace' ? location.replace(url) : location.assign(url)
    router.eventName = ''

    // 如果网络过慢，跳转第三方链接，显示loading
    router.showLoading()
    return next(false)
  }

  // 验证登录
  if (to.meta.auth && !storage.local.get('token')) {
    storage.local.remove('token')
    storage.local.remove('userinfo')
    router.eventName = 'push'
    store.commit('updateDirection', { direction: 'in' })
    return next(`/login?to=${to.fullPath || ''}`)
  }

  let direction = ''
  // 页面返回
  if (router.pageHistory[router.pageHistory.length - 2] === to.path) {
    direction = 'out'
    router.pageHistory.pop()
  // tabbar页面切换
  }else if (from.meta.tabbar && to.meta.tabbar) {
    router.pageHistory[router.pageHistory.length - 1] = to.path
    direction = 'fade'
  // 第一个页面进入
  } else if (from.path === '/' && !from.name) {
    if (router.pageHistory[router.pageHistory.length - 1] !== to.path) {
      router.pageHistory.push(to.path)
    }
    direction = 'fade'
  // 正常页面进入
  }else {
    direction = 'in'
    if (router.eventName === 'replace') {
      router.pageHistory[router.pageHistory.length - 1] = to.path
    }else{
      router.pageHistory.push(to.path)
    }
  }

  // 判断是否是ios左滑返回
  if (!router.eventName && (Date.now() - touchEndTime) < 377) {
    direction = ''
  }

  store.commit('updateDirection', { direction: to.query.direction || direction})
  store.commit('updateLoading', { loading: true })
  store.commit('updateScrollTop', { top: router.savedScroll[to.path] || 0 })
  next()
})

router.afterEach((to, from) => {
  router.eventName = ''
  router.loading && router.hideLoading()

  store.commit('updateLoading', { loading: false })
  storage.session.set('page_history', router.pageHistory)
  utils.setTitle(to.meta.title)
  router.from = from
})

export default router