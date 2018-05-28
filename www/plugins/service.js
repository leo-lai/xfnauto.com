import Vue from 'vue'
import axios from 'axios'
import appConfig from '../app.config'
import { Message } from 'element-ui'

const service = axios.create({
  // baseURL: appConfig.apiURL,
  baseURL: '/',
  timeout: 60000
})

// 拦截器
service.interceptors.request.use(config => {
	config.transformRequest = [function (data) {
    let ret = []
    for (let key in data) {
    	ret.push(encodeURIComponent(key) + '=' + encodeURIComponent(data[key]))
    }
    return ret.join('&')
  }]
  return config
}, error => {
  return Promise.reject(error)
})

// respone拦截器
service.interceptors.response.use(response => {
  const data = response.data

  if (typeof data === 'string') return data
  switch (data.resultCode) {
    case 200:
      return data
    default:
      data.message = data.message || '服务器接口出错'
  }
  return Promise.reject(data)
}, error => {
  if (error && error.response) {
    switch (error.response.status) {
      case 400:
        error.message = '请求错误'
        break
      case 401:
        error.message = '未授权，请登录'
        break
      case 403:
        error.message = '拒绝访问'
        break
      case 404:
        error.message = `请求地址出错: ${error.response.config.url}`
        break
      case 408:
        error.message = '请求超时'
        break
      case 500:
        error.message = '服务器内部错误'
        break
      case 501:
        error.message = '服务未实现'
        break
      case 502:
        error.message = '网关错误'
        break
      case 503:
        error.message = '服务不可用'
        break
      case 504:
        error.message = '网关超时'
        break
      case 505:
        error.message = 'HTTP版本不受支持'
        break
      default:
        error.message = '服务器连接失败'
    }
    error.message += ` - ${error.response.status}`
  }
  return Promise.reject(error)
})

const fetch = {
  ajax (url = '', data = {}, method = 'GET') {
    if (!/^http(s?)$/i.test(url)) {
      url = appConfig.apiURL + url
    }
    return new Promise((resolve, reject) => {
      let ajaxParams = {
        url, method
      }
      switch (method) {
        case 'POST':
          ajaxParams.data = data
          ajaxParams.headers = {
            'Content-Type': 'application/x-www-form-urlencoded'
          }
          ajaxParams.transformRequest = [function (data) {
            let ret = []
            for (let key in data) {
              ret.push(encodeURIComponent(key) + '=' + encodeURIComponent(data[key]))
            }
            return ret.join('&')
          }]
          break
        default:
          ajaxParams.params = data
          break
      }
      service(ajaxParams).then(resolve).catch(error => {
        error && error.message && Message({
          type: 'error',
          message: error.message
        })
        reject(error)
      })
    })
  },
  post (url, data) {
    return this.ajax(url, data, 'POST')
  },
  form (url, formData) { // 自定义表单数据
    return new Promise((resolve, reject) => {
      service({
        url,
        method: 'post',
        data: formData
      }).then(resolve).catch(error => {
        error && error.message && Message({
          type: 'error',
          message: error.message
        })
        reject(error)
      })
    })
  }
}

Vue.prototype.$config = appConfig
Vue.prototype.$http = fetch

export default service
