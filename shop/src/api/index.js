import config from '../config'
import axios from 'axios'
import { storage, utils } from 'assets/js/utils'
import { Message } from 'element-ui'
import router from '../router'

// 创建axios实例
const service = axios.create({
  baseURL: config.api.baseURL,
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
  switch(data.resultCode) {
  	case 200:
  		return data
  	case 4002:
  		Message({
        type: 'error',
        message: data.message || '登录失效，请重新登录',
        onClose() {
          storage.local.remove('sessionId')
          api.auth.logout()
        }
      })
      break
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
  ajax(url = '', data = {}, method = 'GET', contentType = 'form') {
    data.sessionId = storage.local.get('sessionId')
    return new Promise((resolve, reject) => {
      service({
        url, method, data,
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        },
        transformRequest: [function(data) {
          let ret = []
          for (let key in data) {
            ret.push(encodeURIComponent(key) + '=' + encodeURIComponent(data[key]))
          }
          return ret.join('&')
        }]
      }).then(resolve).catch(error => {
        error && error.message && Message({
          type: 'error',
          message: error.message
        })
        reject(error)
      })
    })
  },
  post(url, data) {
    return this.ajax(url, data, 'POST')
  },
  form(url, formData) { // 自定义表单数据
    formData && formData.append('sessionId', storage.local.get('sessionId'))
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

const api = {
  baseURL: config.api.baseURL,
  pageSizes: [100, 200, 300, 400],
  uploadBase64(base64Data = '') {
    return fetch.post('/uploadImageBase64', {
      img_file: base64Data
    })
  },
  uploadByBase64(base64Data = '') {
    var formData = new FormData()
    //convertBase64UrlToBlob函数是将base64编码转换为Blob  
    formData.append('img_file', utils.convert.base64ToBlob(base64Data), 'image_' + Date.now() + '.png')
    return fetch.form('/uploadImage', formData)
  },
  auth: {
    check() {
      return !!storage.local.get('sessionId')
    },
    login(formData = {}) {
      formData.userName = (formData.userName || '').trim()
      return fetch.post('/login', formData)
    },
    logout(toLogin = true) {
      // return new Promise((resolve, reject) => {
      //   if (storage.local.get('sessionId')) {
      //     fetch.post('/loginOut').then(resolve, reject)
      //   } else {
      //     resolve()
      //   }
      // }).finally(_ => {
        storage.local.remove('sessionId')
        storage.local.remove('usermenus')
        storage.local.remove('userinfo')
        toLogin && location.replace(window.location.origin + `${config.router.base}login?to=` + location.href)
        // toLogin && router.replace(`/login?to=` + location.href)
      // })
    },
    changePwd(formData = {}) {
      return fetch.post('/changePassword', formData)
    },
    getZuzhiList() {
      return fetch.post('/organizationLevelList')
    },
    getRoleList() {
      return fetch.post('/roleListList')
    }
  },
  index: {
    getCount() {
      return fetch.post('/index')
    }
  },
  zuzhi: { // 组织架构管理
    getList(formData = {}, page = 1, rows = 50) {
      formData.page = page
      formData.rows = rows
      return fetch.post('/organizationList', formData)
    },
    getParent(orgLevel = 0) {
      return fetch.post('/organizationLevelListByLevel', { orgLevel })
    },
    getInfo(orgId = '') {
      return fetch.post('/organizationInfo', { orgId })
    },
    enable(orgId = '', isOn = '') {
      return fetch.post('/organizationOnOff', {orgId, isOn})
    },
    add(formData = {}) {
      return fetch.post('/organizationEdit', formData)
    },
    getCangList() { // 仓库列表
      return fetch.post('/organizationWarehouseList')
    }
  },
  user: { // 系统用户管理
    getList(formData = {}, page = 1, rows = 50) {
      formData.page = page
      formData.rows = rows
      return fetch.post('/userList', formData)
    },
    enable(userId = '', isEnable = '') {
      return fetch.post('/userIsEnable', {userId, isEnable})
    },
    add(formData = {}) {
      return fetch.post('/addUser', formData)
    },
    getSalesList(formData = {}) {
      return fetch.post('/salesList', formData)
    }
  },
  role: { // 角色管理
    getList(formData = {}, page = 1, rows = 50) {
      formData.page = page
      formData.rows = rows
      return fetch.post('/roleList', formData)
    },
    add(formData = {}) {
      return fetch.post('/roleEdit', formData)
    },
    getMenuList(roleId = '') {
      return fetch.post('/menuListTree', { roleId })
    },
    setRoleMenu(formData = {}) {
      return fetch.post('/setRoleMenu', formData)
    }
  },
  group: { // 分组管理
    getList(formData = {}, page = 1, rows = 50) {
      formData.page = page
      formData.rows = rows
      return fetch.post('/systemGroupingList', formData)
    },
    add(formData = {}) {
      return fetch.post('/systemGroupingEdit', formData)
    },
    del(groupingId = '') { // 删除分组
      return fetch.post('/systemGroupingDalete', { groupingId })
    },
    getUserList(groupingId = '') { // 已分配人员列表
      return fetch.post('/systemUserGroupingList', { groupingId })
    },
    getSltUserList() { // 待分配人员列表
      return fetch.post('/orgOneSelfList')
    },
    addUser(formData = {}) { // 添加人员
      return fetch.post('/systemUserGroupingEdit', formData)
    },
    delUser(userGroupingId = '') { // 删除人员
      return fetch.post('/systemUserGroupingDalete', { userGroupingId })
    }
  },
  menu: {
    add(formData = {}) {
      return fetch.post('/editMenu', formData)
    },
    del(menuId = '') {
      return fetch.post('/deleteMenu', { menuId })
    }
  },
  supplier: { // 供应商管理
    getList(formData = {}, page = 1, rows = 50) {
      formData.page = page
      formData.rows = rows
      return fetch.post('/supplierList', formData)
    },
    add(formData = {}) {
      return fetch.post('/supplierEdit', formData)
    },
    del(supplierId = '') {
      return fetch.post('/supplierDelete', { supplierId })
    },
    getListDown() {
      return fetch.post('/supplierListList')
    }
  },
  car: { // 车型管理
    getList(formData = {}, page = 1, rows = 50) {
      formData.page = page
      formData.rows = rows
      return fetch.post('/carsList', formData)
    },
    add(formData = {}) {
      return fetch.post('/carsEdit', formData)
    },
    del(carId = '') {
      return fetch.post('/carsDelete', { carId })
    },
    getBrandList() { // 品牌列表
      return fetch.post('/carsBrandList')
    },
    getFamilyList(brandId = '') { // 车系列表
      return fetch.post('/carsFamilyList', { brandId })
    },
    getStyleList(familyId = '') { // 车等级列表
      return fetch.post('/carsStyleList', { familyId })
    },
    getCarsList(familyId = '') { // 车大类列表
      return fetch.post('/carsListList', { familyId })
    },
    getCarsInfo(carId = '') { // 车大类相关信息
      return fetch.post('/carsInfo', { carId })
    },
    getDepositPrice(formData = {}) { // 获取车辆定金
      return fetch.post('/carDepositPrice', formData)
    }
  },
  color: { // 车身颜色内饰管理
    getList(formData = {}, page = 1, rows = 50) { // 车系列表(分页)
      formData.page = page
      formData.rows = rows
      return fetch.post('/carsFamilyListPage', formData)
    },
    getCheshenList(familyId = '') { // 获取车身颜色列表
      return fetch.post('/carColourGetByBrand', { familyId })
    },
    addCheshen(formData = {}) {
      return fetch.post('/carColourEdit', formData)
    },
    delCheshen(carColourId = '') {
      return fetch.post('/carColourDelete', { carColourId })
    },
    getNeishiList(familyId = '') { // 获取内饰颜色列表
      return fetch.post('/carInteriorGetByBrand', { familyId })
    },
    addNeishi(formData = {}) {
      return fetch.post('/carInteriorEdit', formData)
    },
    delNeishi(interiorId = '') {
      return fetch.post('/carInteriorDelete', { interiorId })
    },
    addImages(formData = {}) {
      return fetch.post('/carColourImageAdd', formData)
    },
    getImages(carsId = '',  carColourId = '') { // 获取车身照片
      return fetch.post('/carColourImageGetByCarColour', { carsId, carColourId })
    }
  },
  customer: { // 客户管理
    getList(formData = {}, page = 1, rows = 50) {
      formData.page = page
      formData.rows = rows
      return fetch.post('/customerOrgList', formData)
    },
    getInfo(formData = {}) {
      return fetch.post('/customerUsersrInfo', formData)
    },
    saveUserInfo(formData = {}) {
      return fetch.post('/changeUserInfo', formData)
    },
    saveCarInfo(formData = {}) {
      return fetch.post('/changeUserCarInfo', formData)
    },
    addOrder(formData = {}) { // 新增/编辑
      return fetch.post('/editCustomerOrder', formData)
    },
    getOrderInfo(customerOrderId = '') { // 获取订单详情
      return fetch.post('/customerOrderInfo', { customerOrderId })
    },
    payOrder(formData = {}) { // 支付定金
      return fetch.post('/payInOrder', formData)
    },
    orderPrice(customerOrderId = '') { // 订单费用
      return fetch.post('/orderPriceList', { customerOrderId })
    },
    payHistory(customerOrderId = '') { // 收款历史
      return fetch.post('/orderPayList', { customerOrderId })
    },
    bankPass(customerOrderId = '') { // 银行审核通过
      return fetch.post('/bankApprovalPass', { customerOrderId })
    },
    fullPay(customerOrderId = '') { // 银行审核不通过，改成全款支付尾款
      return fetch.post('/changeFullPayment', { customerOrderId })
    },
    giveCar(formData = {}) {
      return fetch.post('/turnOverVehicle', formData)
    },
    saveRemark(formData = {}) {
      return fetch.post('/addCustomerRemarks', formData)
    },
    add(formData = {}) { // 新增客户
      return fetch.post('/addCustomerUsersr', formData)
    }, // 跟踪列表
    getTrackList(formData = {}, page = 1, rows = 50) {
      formData.page = page
      formData.rows = rows
      return fetch.post('/trackCustomerOrgList', formData)
    }, // 预约列表
    getBespeakList(formData = {}, page = 1, rows = 50) {
      formData.page = page
      formData.rows = rows
      return fetch.post('/bespeakCustomerOrgList', formData)
    },
    notBuy(customerUsersOrgId = '') { // 标记为不购买
      return fetch.post('/notBuyCustomerOrg', { customerUsersOrgId })
    },
    track(formData = {}) { // 标记为已到店
      return fetch.post('/systenUserChangeCustomerOrg', formData)
    },
    getContractInfo(customerOrderId = '') { // 合同信息
      return fetch.post('/customerOrderPrint', { customerOrderId })
    }
  },
  stock: { // 库存管理
    getList(formData = {}, page = 1, rows = 50) { // 车辆库存列表
      formData.page = page
      formData.rows = rows
      return fetch.post('/stockCarList', formData)
    },
    getInfo(formData = {}){ // 车辆库存详情
      return fetch.post('/stockCarInfo', formData)
    },
    editInfo(formData = {}) { // 编辑车辆库存详情
      return fetch.post('/stockCarEdit', formData)
    },
    getInList(formData = {}, page = 1, rows = 50) { // 入库单列表
      formData.page = page
      formData.rows = rows
      return fetch.post('/storageList', formData)
    },
    addIn(formData = {}) { // 新增入库单
      return fetch.post('/storageEdit', formData)
    },
    delIn(stockCarId = '') {
      return fetch.post('/storageCarDelete', { stockCarId })
    },
    getInInfo(storageId = '') { // 入库单详情
      return fetch.post('/storageInfo', { storageId })
    },
    getInCarList(formData = {}, page = 1, rows = 50) {
      formData.page = page
      formData.rows = rows
      return fetch.post('/storageCarList', formData)
    },
    addInCar(formData = {}) { // 新增入库车辆
      return fetch.post('/storageCarEdit', formData)
    },
    addOrder(formData = {}) { // 新增订车单
      return fetch.post('/stockOrderCreate', formData)
    },
    cancelOrder(stockOrderId = '') { // 取消订车单
      return fetch.post('/stockOrderCancel', { stockOrderId })
    },
    getOrderInfo(stockOrderId = '', isSellList = 0) { // 获取订车单详情
      return fetch.post('/stockOrderInfo', { stockOrderId, isSellList })
    },
    signOrder(stockOrderId = '') {
      return fetch.post('/stockOrderSign', { stockOrderId })
    },
    noticeBefore(stockOrderId = '') { // 通知有车前信息
      return fetch.post('/stockOrderNoticeBefor', { stockOrderId })
    },
    notice(formData = {}) { // 通知有车
      return fetch.post('/stockOrderNotice', formData)
    },
    getOrderList(formData = {}, page = 1, rows = 50) { // 订车列表
      formData.page = page
      formData.rows = rows
      return fetch.post('/stockOrderList', formData)
    },
    outStockBefor(stockOrderId = '') { // 二级出库车辆前信息
      return fetch.post('/stockOrderStorageOutBefor', { stockOrderId })
    },
    outStock(formData = {}) { // 二级车辆出库
      return fetch.post('/stockOrderStorageOut', formData)
    },
    getOrderList2(formData = {}, page = 1, rows = 50) { // 三级车辆出库列表
      formData.page = page
      formData.rows = rows
      return fetch.post('/customerOrderList', formData)
    },
    outStockBefor2(customerOrderId = '') { // 三级出库车辆前信息
      return fetch.post('/customerOrderStorageOutBefor', { customerOrderId })
    },
    outStock2(formData = {}) { // 三级车辆出库
      return fetch.post('/customerOrderStorageOut', formData)
    }
  },
  order: { // 代购管理
    getList(formData = {}, page = 1, rows = 50) { // 代购单列表
      formData.page = page
      formData.rows = rows
      return fetch.post('/ConsumerOrder/listOrders', formData)
    },
    getInfo(orderId = '') {
      return fetch.post('/ConsumerOrder/getOrderDetail', { orderId })
    },
    getPayInfo(orderId = '') { // 获取支付信息
      return fetch.post('/ConsumerOrder/getPaymentInfo', { orderId })
    },
    pay(formData = {}) { // 上传支付凭证
      return fetch.post('/ConsumerOrderPayment/create', formData)
    },
    tickPic(formData = {}) { // 上传票证图片
      return fetch.post('/ConsumerOrderCar/uploadTickPic', formData)
    },
    tickDone(orderId = '') { // 所有上传票证图片
      return fetch.post('/ConsumerOrder/finishOrder', { orderId })
    },
    getContractInfo(orderId = '') { // 合同信息
      return fetch.post('/ConsumerOrder/getContractInfo', { orderId })
    },
    refund(formData = {}) { // 退款
      return fetch.post('/ConsumerOrder/countermandExamine', formData)
    }
  },
  pay: { // 通联支付
    orderPay(formData = {}) {
      return fetch.post('/stockOrderPay', formData)
    },
    finish(customerOrderId = '') { // 完款交车
      return fetch.post('/endOrder', { customerOrderId })
    }
  },
  bank: {
    getList(formData = {}, page = 1, rows = 50) { // 审核列表
      formData.page = page
      formData.rows = rows
      return fetch.post('/bankToExamineOrderList', formData)
    },
    examine(formData = {}) { // 审核
      return fetch.post('/bankToExamineOrder', formData)
    }
  }
}

export default api
