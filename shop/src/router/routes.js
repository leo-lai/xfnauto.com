import Home from '../pages/home'
import Loan from '../pages/loan'
import Me from '../pages/me'
import Login from '../pages/login'
import Register from '../pages/register'
const MeStoreInfo = () => import('../pages/store-info').then(m => m.default)
const MeStoreAuth = () => import('../pages/store-auth').then(m => m.default)
const MeInfo = () => import('../pages/me-info').then(m => m.default)
const Password = () => import('../pages/password').then(m => m.default)
const Msg = () => import('../pages/msg').then(m => m.default)
const Pay = () => import('../pages/pay').then(m => m.default)
const Loan1 = () => import('../pages/loan-1').then(m => m.default)
const Loan2 = () => import('../pages/loan-2').then(m => m.default)
const LoanList = () => import('../pages/loan-list').then(m => m.default)
const LoanList2 = () => import('../pages/loan-list2').then(m => m.default)
const LoanInfo = () => import('../pages/loan-info').then(m => m.default)
const LoanInfo2 = () => import('../pages/loan-info2').then(m => m.default)
const LoanDeferRecord = () => import('../pages/loan-defer-record').then(m => m.default)
const LoanAuth = () => import('../pages/loan-auth').then(m => m.default)
const Loan2Addcar = () => import('../pages/loan-2-addcar').then(m => m.default)
const CarSelector = () => import('../pages/car-selector').then(m => m.default)
const MapSelector = () => import('../pages/map-selector').then(m => m.default)
const CarSeek = () => import('../pages/car-seek').then(m => m.default)
const CarSeekList = () => import('../pages/car-seek-list').then(m => m.default)
const CarSeekInfo = () => import('../pages/car-seek-info').then(m => m.default)
const WuliuList = () => import('../pages/wuliu-list').then(m => m.default)
const WuliuFreight = () => import('../pages/wuliu-freight').then(m => m.default)
const WuliuFreightAddcar = () => import('../pages/wuliu-freight-addcar').then(m => m.default)
const GoodsList = () => import('../pages/goods-list').then(m => m.default)
const GoodsInfo = () => import('../pages/goods-info').then(m => m.default)
const GoodsOrder = () => import('../pages/goods-order').then(m => m.default)
const OrderList1 = () => import('../pages/order-list1').then(m => m.default)
const OrderInfo1 = () => import('../pages/order-info1').then(m => m.default)
const OrderList2 = () => import('../pages/order-list2').then(m => m.default)
const OrderInfo2 = () => import('../pages/order-info2').then(m => m.default)
const OrderList3 = () => import('../pages/order-list3').then(m => m.default)
const OrderInfo3 = () => import('../pages/order-info3').then(m => m.default)
const OrderPayRecord = () => import('../pages/order-pay-record').then(m => m.default)

const routes = [
  {
    path: '/',
    name: 'home',
    component: Home,
    meta: {
      title: '首页',
      tabbar: true
    }
  },
  {
    path: '/loan',
    name: 'loan',
    component: Loan,
    meta: {
      title: '加载中',
      tabbar: true
    }
  },
  {
    path: '/loan/1',
    name: 'loan1',
    component: Loan1,
    meta: {
      title: '申请贷款',
      auth: true
    }
  },
  {
    path: '/loan/2',
    name: 'loan2',
    component: Loan2,
    meta: {
      title: '申请垫资',
      auth: true
    }
  },
  {
    path: '/loan/auth',
    name: 'loan-auth',
    component: LoanAuth,
    meta: {
      title: '垫资资格认证'
    }
  },
  {
    path: '/loan/list',
    name: 'loan-list',
    component: LoanList,
    meta: {
      title: '贷款申请',
      auth: true
    }
  },
  {
    path: '/loan/list2',
    name: 'loan-list2',
    component: LoanList2,
    meta: {
      title: '垫资申请记录',
      auth: true
    }
  },
  {
    path: '/loan/info',
    name: 'loan-info',
    component: LoanInfo,
    meta: {
      title: '加载中'
    }
  },
  {
    path: '/loan/info2',
    name: 'loan-info2',
    component: LoanInfo2,
    meta: {
      title: '垫资详情'
    }
  },
  {
    path: '/loan/defer/record',
    name: 'loan-defer-record',
    component: LoanDeferRecord,
    meta: {
      title: '垫资还款记录'
    }
  },
  {
    path: '/loan/2/addcar',
    name: 'loan-2-addcar',
    component: Loan2Addcar,
    meta: {
      title: '添加垫资车辆'
    }
  },
  {
    path: '/me',
    name: 'me',
    component: Me,
    meta: {
      title: '我的',
      tabbar: true
    }
  },
  {
    path: '/me/info',
    name: 'me-info',
    component: MeInfo,
    meta: {
      title: '账户资料',
      auth: true
    }
  },
  {
    path: '/store/info',
    name: 'store-info',
    component: MeStoreInfo,
    meta: {
      title: '完善商家信息'
    }
  },
  {
    path: '/store/auth',
    name: 'store-auth',
    component: MeStoreAuth,
    meta: {
      title: '商铺认证'
    }
  },
  {
    path: '/login',
    name: 'login',
    component: Login,
    meta: {
      title: '登录'
    }
  },
  {
    path: '/register',
    name: 'register',
    component: Register,
    meta: {
      title: '注册',
      scope: 'snsapi_userinfo'
    }
  },
  {
    path: '/password',
    name: 'password',
    component: Password,
    meta: {
      title: '修改密码'
    }
  },
  {
    path: '/msg',
    name: 'msg',
    component: Msg,
    meta: {
      title: '提示'
    }
  },
  {
    path: '/pay/',
    name: 'pay',
    component: Pay,
    meta: {
      title: '微信支付'
    }
  },
  {
    path: '/car/selector',
    name: 'car-selector',
    component: CarSelector,
    meta: {
      title: '选择车型'
    }
  },
  {
    path: '/map/selector',
    name: 'map-selector',
    component: MapSelector,
    meta: {
      title: '选择位置'
    }
  },
  {
    path: '/car/seek',
    name: 'car-seek',
    component: CarSeek,
    meta: {
      title: '要寻车',
      auth: true
    }
  },
  {
    path: '/car/seek/list',
    name: 'car-seek-list',
    component: CarSeekList,
    meta: {
      title: '寻车记录',
      auth: true
    }
  },
  {
    path: '/car/seek/info',
    name: 'car-seek-info',
    component: CarSeekInfo,
    meta: {
      title: '寻车详情',
      auth: true
    }
  },
  {
    path: '/wuliu/list',
    name: 'wuliu-list',
    component: WuliuList,
    meta: {
      title: '车物流'
    }
  },
  {
    path: '/wuliu/freight',
    name: 'wuliu-freight',
    component: WuliuFreight,
    meta: {
      title: '我要托运'
    }
  },
  {
    path: '/wuliu/freight/addcar',
    name: 'wuliu-freight-addcar',
    component: WuliuFreightAddcar,
    meta: {
      title: '添加车辆'
    }
  },
  {
    path: '/goods/list',
    name: 'goods-list',
    component: GoodsList,
    meta: {
      title: '车辆列表'
    }
  },
  {
    path: '/goods/info',
    name: 'goods-info',
    component: GoodsInfo,
    meta: {
      title: '车辆详情'
    }
  },
  {
    path: '/goods/order',
    name: 'goods-order',
    component: GoodsOrder,
    meta: {
      title: '预约下单',
      auth: true
    }
  },
  {
    path: '/order/list1',
    name: 'order-list1',
    component: OrderList1,
    meta: {
      title: '预约订单',
      auth: true
    }
  },
  {
    path: '/order/info1',
    name: 'order-info1',
    component: OrderInfo1,
    meta: {
      title: '预约单详情',
      auth: true
    }
  },
  {
    path: '/order/list2',
    name: 'order-list2',
    component: OrderList2,
    meta: {
      title: '订购单',
      auth: true
    }
  },
  {
    path: '/order/info2',
    name: 'order-info2',
    component: OrderInfo2,
    meta: {
      title: '订购单详情',
      auth: true
    }
  },
  {
    path: '/order/list3',
    name: 'order-list3',
    component: OrderList3,
    meta: {
      title: '订购单',
      auth: true
    }
  },
  {
    path: '/order/info3',
    name: 'order-info3',
    component: OrderInfo3,
    meta: {
      title: '订购单详情',
      auth: true
    }
  },
  {
    path: '/order/pay-record',
    name: 'order-pay-record',
    component: OrderPayRecord,
    meta: {
      title: '支付记录',
      auth: true
    }
  },
]

export default routes