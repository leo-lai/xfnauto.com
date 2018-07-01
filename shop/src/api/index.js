import config from '../config'
import { toptip, device, storage, utils } from '../assets/utils'
import Vue from 'vue'
import axios from 'axios'
import router from '../router'

const apiURL = config.apiURL
const apiURL2 = config.apiURL2
// 创建axios实例
const service = axios.create({
  timeout: 60000
})
// request拦截器
service.interceptors.request.use(config => {
  // Do something before request is sent
  return config
}, error => {
  return Promise.reject(error)
})
// respone拦截器
service.interceptors.response.use(response => {
  const data = response.data
  if(data.resultCode == 200) {
    data.code = data.resultCode
    return data
  }else {
    return Promise.reject({
      code: data.resultCode,
      message: data.message || '服务器接口出错'
    })
  }
}, error => {
  error = error || {
    code: 0,
    message: '未知错误'
  }

  if (error.response) {
    error.code = error.response.status
    error.message = ''

    switch (error.code) {
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
        error.message = '请求超时，请检查网络'
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
  }
  return Promise.reject(error)
})

const showToast = (text = '', type = 'warn') => {
  return new Promise((resolve, reject) => {
    if(type) {
      Vue.$vux.toast.show({
        type,
        text,
        time: 3000,
        isShowMask: true,
        onHide: resolve
      })
    }else{
      Vue.$vux.toast.text(text, 'top')
    }
  })
}
const showMessage = content => {
  Vue.$vux.alert.show({
    title: '系统提示',
    content
  })
}
const showLoading = text => Vue.$vux.loading.show({ text })
const hideLoading = _ => Vue.$vux.loading.hide()

const fetch = {
  source: {},
  ajax(url = '', data = {}, method = 'GET', contentType = 'form') {
    let source = axios.CancelToken.source()
    fetch.source[url] = source
    data.sessionId = storage.local.get('token')
    data.orgCode = storage.session.get('org_code') || null
    return new Promise((resolve, reject) => {
      let ajaxParams = {
        url, method, headers: {}
      }
      switch (method) {
        case 'POST':
          ajaxParams.headers['Content-Type'] = 'application/x-www-form-urlencoded'
          ajaxParams.data = data
          ajaxParams.transformRequest = [function (data) {
            let ret = []
            for (let key in data) {
              if(data[key] != null && data[key] != undefined) {
                ret.push(encodeURIComponent(key) + '=' + encodeURIComponent(data[key]))
              }
            }
            return ret.join('&')
          }]
          break
        case 'JSON':
          ajaxParams.method = 'POST'
          ajaxParams.headers['Content-Type'] = 'application/json'
          ajaxParams.data = data
          break
        default:
          ajaxParams.params = data
          break
      }

      service(ajaxParams).then(data => {
        fetch.source[url] = null
        resolve(data)
      }).catch(error => {
        if (axios.isCancel(error)) {
          error.code = -1
          error.abort = true
        } else {
          error.abort = false
          if (error.code == 4002) { // 登录失效
            showToast('登录失效').then(_ => {
              api.user.logout()
            })
          } else { // 接口出错
            showMessage(error.message)
          }
        }
        fetch.source[url] = null
        reject(error)
      })
    })
  },
  post(url, data) {
    return this.ajax(url, data, 'POST')
  },
  json(url, data) {
    return this.ajax(url, data, 'JSON')
  }
}

const api = {
  pageSizes: [10, 20, 50, 100],
  abort(url = '') { // 取消接口请求
    if (url) {
      fetch.source[url] && fetch.source[url].cancel('取消请求')
    }else {
      let urls = Object.keys(fetch.source)
      urls.forEach(url => {
        fetch.source[url] && fetch.source[url].cancel('取消请求')
      })
    }
  },
  // 发送手机验证码
  sendMobiCode(phone, btn) {
    if (!/^1\d{10}$/.test(phone)) {
      showMessage('请输入正确手机号码')
      return Promise.reject('请输入正确手机号码')
    }

    let time = 60
    let oldtext = ''
    let timeid = 0
    if (btn) {
      btn.setAttribute('disabled', true)
      btn.classList.add('weui-btn_disabled')
      oldtext = btn.textContent
      btn.textContent = '60s'
      timeid = setInterval(() => {
        if (--time >= 0) {
          btn.textContent = `${time}s`
        } else {
          clearInterval(timeid)
          btn.removeAttribute('disabled')
          btn.textContent = oldtext
        }
      }, 1000)
    }

    let promise = fetch.post(apiURL + '/common/phoneVerifyCode', { phoneNumber: phone })
    promise.then((response) => {
      showToast('手机验证码已发送', null)
      return response
    }).catch(() => {
      clearInterval(timeid)
      btn.removeAttribute('disabled')
      btn.classList.remove('weui-btn_disabled')
      btn.textContent = oldtext
    })

    return promise
  },
  getWxConfig(url) {
    if(!window.wx) {
      return Promise.reject('页面没有引入微信JS-SDK')
    }

    if (!device.isWechat) {
      return Promise.reject('请使用微信浏览器打开')
    }

    url = url || (device.isIos ? router.landingUrl : window.location.href)
    url = url.split('#')[0]
    // 如果查询参数后面带有 / 会导致签名失败 所以要encodeURIComponent
    url = utils.url.setArgs(url, utils.url.getArgs(url))

    const that = this
    let promise = new Promise((resolve, reject) => {
      let config = {
        debug: false,
        appId: '',
        timestamp: '',
        nonceStr: '',
        signature: '',
        jsApiList: ['onMenuShareTimeline', 'onMenuShareAppMessage', 'onMenuShareQQ', 'onMenuShareWeibo', 'onMenuShareQZone', 'chooseImage', 'previewImage', 'uploadImage', 'downloadImage', 'openLocation', 'getLocation', 'hideOptionMenu', 'showOptionMenu', 'scanQRCode']
      }

      if (device.isIos && window.wx._configSuccess) {
        return resolve(window.wx)
      }

      fetch.post(apiURL + '/common/getConfig', { url }).then(({ data }) => {
        config.appId = data.appId
        config.timestamp = data.timestamp
        config.nonceStr = data.nonceStr
        config.signature = data.signature

        // wx.config begin
        window.wx.config(config)
        
        let configError = false
        window.wx.error(res => {
          if (res.errMsg.indexOf('config:fail') !== -1) {
            configError = true
            if (window.wx._tryConfig) {
              window.wx._configSuccess = false
              console.log('current page：微信JS-SDK权限验证失败', res)
              reject('微信JS-SDK权限验证失败')
            }else {
              // ios环境下(landing page)第一次权限验证失败，再利用当前地址(current page)尝试一下
              console.log('landing page：微信JS-SDK权限验证失败', res)
              window.wx._tryConfig = true
              resolve(that.getWxConfig(window.location.href))
            }
          }
        })
        window.wx.ready(res => {
          // config:fail 权限验证失败也会执行ready 函数（此处略坑）
          // 使用延迟函数，等待wx.config end（内部函数）执行完毕
          clearTimeout(window.wx.timeid)
          window.wx.timeid = setTimeout(_ => {
            if (!configError) {
              console.log('微信JS-SDK权限验证成功', res)
              window.wx._configSuccess = true
              resolve(window.wx)
            }
          }, 500)
        })
      }).catch(res => {
        console.log('服务器返回微信JS-SDK配置失败', res)
        reject('服务器返回微信JS-SDK配置失败')
      })
    })
    return promise
  },
  getWxPayConfig(formData = {}) { // 微信支付配置
    if (!formData.orderId) {
      showMessage('订单id不存在')
      return Promise.reject('订单id不存在')
    }

    let promise = new Promise((resolve, reject) => {
      fetch.post(apiURL + '/interfaceShop/advanceOrder/orgAdvanceOrderInpay', formData).then(({ data }) => {
        resolve(data.payinfo || data.payInfo)
      }).catch(reject)
    })

    return promise
  },
  chooseWXPay(formData) { // 微信jssdk支付
    let promise = new Promise((resolve, reject) => {
      if (!device.isWechat) {
        showToast('请使用微信浏览器支付', null)
        return reject('请使用微信浏览器支付')
      }

      showLoading('正在支付...')
      this.getWxConfig().then(wx => {
        this.getWxPayConfig(formData).then(data => {
          wx.chooseWXPay({
            timestamp: data.timeStamp,
            nonceStr: data.nonceStr,
            package: data.package,
            signType: data.signType,
            paySign: data.paySign,
            success: res => {
              if (res.err_msg === 'get_brand_wcpay_request:ok') {
                resolve('ok')
              } else if (res.err_msg === 'get_brand_wcpay_request:cancel') {
                reject('cancel')
              } else if (res.err_msg === 'get_brand_wcpay_request:fail') {
                reject('fail')
              } else {
                resolve('支付回调成功')
              }
            },
            fail: err => reject(err.errMsg)
          })
        }).finally(_ => {
          hideLoading()
        })
      }).catch(errMsg => {
        reject(errMsg)
        hideLoading()
      })
    })
    return promise
  },
  chooseWXPay2(formData) { // 微信浏览器支付
    let promise = new Promise((resolve, reject) => {
      if (!device.isWechat) {
        showToast('请使用微信浏览器支付', null)
        reject('请使用微信浏览器支付')
        return
      }
      showLoading('正在支付...')
      this.getWxPayConfig(formData).then(data => {
        let onBridgeReady = function () {
          WeixinJSBridge.invoke('getBrandWCPayRequest', data, res => {
            if (res.err_msg === 'get_brand_wcpay_request:ok') {
              resolve('ok')
            } else if (res.err_msg === 'get_brand_wcpay_request:cancel') {
              reject('cancel')
            } else if (res.err_msg === 'get_brand_wcpay_request:fail') {
              reject('fail')
            } else {
              resolve('支付回调成功')
            }
          })
        }
        if (typeof WeixinJSBridge == 'undefined') {
          document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false)
        } else {
          onBridgeReady()
        }
      }).catch(reject).finally(_ => {
        hideLoading()
      })
    })
    return promise
  },
  previewImage(imgs = [], index = 0) {
    return new Promise((resolve, reject) => {
      showLoading()
      this.getWxConfig().then(wx => {
        wx.previewImage({
          current: utils.isNumber(index) ? imgs[index] : index,   // 当前显示图片的http链接
          urls: imgs,                                             // 需要预览的图片http链接列表
          success: res => resolve(res),
          fail: res => reject(err.errMsg)
        })
      }).catch(reject).finally(_ => {
        hideLoading()
      })
    })
  },
  chooseImage(count = 1) {
    return new Promise((resolve, reject) => {
      showLoading()
      this.getWxConfig().then(wx => {
        wx.chooseImage({
          count,
          sizeType: ['original', 'compressed'],     // 可以指定是原图还是压缩图，默认二者都有
          sourceType: ['album', 'camera'],          // 可以指定来源是相册还是相机，默认二者都有
          success: res => resolve(res.localIds),    // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
          fail: err => reject(err.errMsg)
        })
      }).catch(reject).finally(_ => {
        hideLoading()
      })
    })
  },
  uploadImage(localIds = [], remote = true) {
    return new Promise((resolve, reject) => {
      let _serverIds = []
      let _localIds = []
      let allLength = localIds.length
      
      
      showLoading(allLength > 1 ? `上传中(1/${allLength})` : '上传中')
      let _ = function syncUpload(localIds) {
        let localId = localIds.pop()
        wx.uploadImage({
          localId,
          isShowProgressTips: 0,
          success: res => {
            _serverIds.push(res.serverId)
            _localIds.push(localId)

            if (localIds.length > 0) {
              showLoading(`上传中(${_localIds.length}/${allLength})`)
              syncUpload(localIds)
            } else {
              if (remote) {
                fetch.post(apiURL + '/common/uploadImage', { 
                  media_ids: _serverIds.join(',') 
                }).then(({ data }) => {
                  resolve({
                    serverIds: _serverIds,
                    localIds: _localIds,
                    images: data
                  })
                }).finally(_ => {
                  hideLoading()
                })
              } else {
                resolve({
                  serverIds: _serverIds,
                  localIds: _localIds,
                  images: []
                })
                hideLoading()
              }
            }
          },
          fail: err => {
            if (localIds.length === 0) {
              hideLoading()
              reject(err.errMsg)
            }
          }
        })
      }(localIds)
    })
  },
  wxShare(shareInfo) {
    let _default = {
      title: '喜蜂鸟·淘车网',
      desc: '想批发价买新车，就上淘车网——您身边的汽车超市！',
      imgUrl: config.thumb0,
      link: window.location.href
    }
    return new Promise((resolve, reject) => {
      this.getWxConfig().then(wx => {
        let _info = Object.assign({}, _default, shareInfo)
        wx.onMenuShareTimeline(_info)
        wx.onMenuShareAppMessage(_info)
        wx.onMenuShareQQ(_info)
        wx.onMenuShareQZone(_info)
        
        resolve(wx)
      }).catch(reject)
    })
  },
  user: {
    register(formData = {}) {
      return fetch.post(apiURL + '/interfaceShop/shopUsers/register', formData)
    },
    login(formData = {}) {
      return fetch.post(apiURL + '/interfaceShop/shopUsers/loginPassword', formData)
    },
    logout() {
      storage.local.remove('token')
      storage.local.remove('userinfo')
      router.replace('/login')
    },
    getInfo() {
      return Promise.resolve(storage.local.get('userinfo'))
    },
    getAllInfo() { // 个人信息和认证状态信息
      return fetch.ajax(apiURL2 + '/shop_v3/user/profile')
    },
    saveStoreInfo(formData = {}) { // 提交商铺信息
      return fetch.post(apiURL2 + '/shop_v3/Index/verify', formData)
    },
    getStoreInfo() { // 商铺认证详情
      return fetch.ajax(apiURL2 + '/shop_v3/user/shopInfo')
    },
    getLoanInfo() { // 垫资认证详情
      return fetch.ajax(apiURL2 + '/shop_v3/user/loanInfo')
    },
    forgotPwd(formData = {}) {
      return fetch.post(apiURL + '/interfaceShop/shopUsers/forgetPassword', formData)
    }
  },
  car: {
    getBrandList() {
      return fetch.post(apiURL + '/common/carsBrandList')
    },
    getFamilyList(brandId = '') {
      return fetch.post(apiURL + '/common/carsFamilyList', { brandId })
    },
    getCarList(familyId = '') {
      return fetch.post(apiURL + '/common/carsListList', { familyId })
    },
    getCheShenList(familyId = '') {
      return fetch.post(apiURL + '/common/carColourList', { familyId })
    },
    getNeishiList(familyId = '') {
      return fetch.post(apiURL + '/common/carInteriorList', { familyId })
    }
  },
  goods: {
    getList(formData = {}, page = 1, rows = 50) {
      formData.page = page
      formData.rows = rows
      return fetch.post(apiURL + '/interfaceShop/shopGoodsCars/shopGoodsCarsList', formData)
    },
    getInfo(goodsCarsId = '') {
      return fetch.post(apiURL + '/interfaceShop/shopGoodsCars/shopGoodsCarsInfo', { goodsCarsId })
    },
    getBrandList() {
      return fetch.post(apiURL + '/interfaceShop/shopGoodsCars/brandListList')
    },
    getCityList() {
      return fetch.post(apiURL + '/interfaceShop/shopGoodsCars/cityListList')
    },
    getActiveList(formData = {}, page = 1, rows = 50) {
      formData.page = page
      formData.rows = rows
      return fetch.post(apiURL + '/interfaceShop/goodsCarsActivity/activityList', formData)
    },
    getActiveInfo(goodsCarsActivityId = '') {
      return fetch.post(apiURL + '/interfaceShop/goodsCarsActivity/activityInfo', { goodsCarsActivityId })
    },
    getFullPayment(formData = {}) {
      return fetch.post(apiURL + '/interfaceShop/shopGoodsCars/fullPayment', formData)
    },
    getLoanPayment(formData = {}) {
      return fetch.post(apiURL + '/interfaceShop/shopGoodsCars/loanPayment', formData)
    }
  },
  order: {
    add(formData = {}) { // 预约下单
      return fetch.post(apiURL + '/interfaceShop/advanceOrder/advanceOrderEdit', formData)
    },
    getList1(formData = {}, page = 1, rows = 50) { // 预约单
      formData.page = page
      formData.rows = rows
      return fetch.post(apiURL + '/interfaceShop/advanceOrder/myOrgAdvanceOrderList', formData)
    },
    getInfo1(advanceOrderId = '') { // 预约单详情
      return fetch.post(apiURL + '/interfaceShop/advanceOrder/orgAdvanceOrderInfo', { advanceOrderId })
    },
    getList2(formData = {}, page = 1, rows = 50) { // 订购单
      formData.page = page
      formData.rows = rows
      return fetch.post(apiURL + '/interfaceShop/advanceOrder/myOrderList', formData)
    },
    getInfo2(orderId = '') { // 订购单详情
      return fetch.post(apiURL + '/interfaceShop/advanceOrder/myOrderInfo', { orderId })
    },
    getPayRecord(formData = {}, page = 1, rows = 50) {
      formData.page = page
      formData.rows = rows
      return fetch.post(apiURL + '/interfaceShop/advanceOrder/myOrderPaymentList', formData).then(response => {
        if (!response.data.list) {
          response.data = {
            page, 
            rows: 1000,
            total: 1,
            list: response.data
          }
        }
        return response
      })
    }
  },
  loan: { // 贷款
    apply1(formData = {}) { // 个人贷款
      return fetch.post(apiURL + '/interfaceShop/applyLoan/applyLoanEdit', formData)
    },
    // apply2(formData = {}) { // 商家垫资
    //   return fetch.post(apiURL + '/interfaceShop/applyLoan/applyLoanMerchant', formData)
    // },
    apply2(formData = {}) { // 商家垫资
      return fetch.post(apiURL2 + '/shop_v3/loan/create', formData)
    },
    getList(formData = {}, page = 1, rows = 50) { // 贷款申请记录
      formData.page = page
      formData.rows = rows
      return fetch.post(apiURL + '/interfaceShop/applyLoan/myApplyLoanList', formData)
    },
    getInfo(applyLoanId = '') { // 贷款详情
      return fetch.post(apiURL + '/interfaceShop/applyLoan/applyLoanInfo', { applyLoanId })
    },
    getList2(formData = {}, page = 1, rows = 50) { // 垫资申请记录
      formData.page = page
      formData.rows = rows
      return fetch.ajax(apiURL2 + '/shop_v3/loan/index', formData)
    },
    getInfo2(id = '') { // 垫资详情
      return fetch.ajax(apiURL2 + '/shop_v3/loan/detail', { id })
    },
    sendTpl(formData = {}) { // 发送认证模板
      return fetch.post(apiURL2 + '/shop_v3/loan/send', formData)
    },
    cancel(orderId = '') { // 取消垫资单
      return fetch.ajax(apiURL2 + '/shop_v3/loan/cancel', { orderId })
    },
    deferRecord(orderId = '') { // 延期记录
      return fetch.ajax(apiURL2 + '/shop_v3/loan/overduerecords', { orderId })
    },
    getPayRecord(id = '') { // 还款记录
      return fetch.ajax(apiURL2 + '/shop_v3/loan/payrecord', { id })
    },
  },
  seek: { // 寻车
    add(formData = {}) {
      return fetch.post(apiURL + '/interfaceShop/shopFindCar/shopFindCarEdit', formData)
    },
    getList(formData = {}, page = 1, rows = 50) { // 寻车记录
      formData.page = page
      formData.rows = rows
      return fetch.post(apiURL + '/interfaceShop/shopFindCar/myShopFindCarList', formData)
    },
    getInfo(findTheCarId = '') { // 寻车详情
      return fetch.post(apiURL + '/interfaceShop/shopFindCar/shopFindCarInfo', { findTheCarId })
    },
    cancel(findTheCarId = '') { // 取消寻车
      return fetch.post(apiURL + '/interfaceShop/shopFindCar/shopFindCarDel', { findTheCarId })
    }
  },
  wuliu: { // 物流
    getList() {
      return fetch.post(apiURL + '/interfaceShop/consignment/logisticsLineList')
    },
    getFreight(formData = {}) { // 查询运费
      return fetch.post(apiURL + '/interfaceShop/consignment/expensesCount', formData)
    }
  }
}

Vue.prototype.$api = api
export default api
