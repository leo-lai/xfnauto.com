// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
require('es6-promise').polyfill()
import Vue from 'vue'
Vue.config.productionTip = false

const FastClick = require('fastclick')
FastClick.attach(document.body)

import { sync } from 'vuex-router-sync'
import router from './router'
import store from './store'
import api from './api'
import config from './config'

// 把路由信息映射到状态
sync(store, router)

import { 
  TransferDom, ConfigPlugin, BusPlugin, 
  DatetimePlugin, LoadingPlugin, ToastPlugin, AlertPlugin, ConfirmPlugin,
  ViewBox, Group, GroupTitle, Cell, Sticky, XHeader, XInput, XButton
} from 'vux'

// plugins
Vue.directive('transfer-dom', TransferDom)
Vue.use(ConfigPlugin, { $layout: 'VIEW_BOX' }) // global config for VUX, since v2.5.12
Vue.use(BusPlugin)
Vue.use(DatetimePlugin)
Vue.use(LoadingPlugin)
Vue.use(ToastPlugin)
Vue.use(AlertPlugin)
Vue.use(ConfirmPlugin)

// component
Vue.component('view-box', ViewBox)
Vue.component('group', Group)
Vue.component('group-title', GroupTitle)
Vue.component('cell', Cell)
Vue.component('sticky', Sticky)
Vue.component('x-header', XHeader)
Vue.component('x-button', XButton)
Vue.component('x-input', XInput)

import { device, toptip, utils, storage } from './assets/utils'
// 全局函数
Vue.prototype.$config = config      // 设备检测
Vue.prototype.$device = device      // 设备检测
Vue.prototype.$storage = storage    // 本地存储
Vue.prototype.$utils = utils        // 工具函数
Vue.prototype.$toptip = toptip      // 顶部提示

/* eslint-disable no-new */
import App from './App'
new Vue({
  store,
  router,
  render: h => h(App)
}).$mount('#app')
