import Vue from 'vue'
import axios from 'axios'
import appConfig from '../app.config'

const service = axios.create({
  baseURL: appConfig.serverURL
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

service.interceptors.response.use(response => {
  return response
}, error => {
  return Promise.reject(error)
})

Vue.prototype.$http = service
export default service
