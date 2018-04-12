import Home from '../pages/home'
import Loan from '../pages/loan'
import Me from '../pages/me'
import Login from '../pages/login'
import Register from '../pages/register'
const MeStoreInfo = () => import('../pages/me-store-info').then(m => m.default)
const MeInfo = () => import('../pages/me-info').then(m => m.default)
const Password = () => import('../pages/password').then(m => m.default)
const Msg = () => import('../pages/msg').then(m => m.default)
const Loan1 = () => import('../pages/loan-1').then(m => m.default)
const Loan2 = () => import('../pages/loan-2').then(m => m.default)
const LoanList = () => import('../pages/loan-list').then(m => m.default)
const CarSelector = () => import('../pages/car-selector').then(m => m.default)
const MapSelector = () => import('../pages/map-selector').then(m => m.default)
const CarSeek = () => import('../pages/car-seek').then(m => m.default)
const CarSeekList = () => import('../pages/car-seek-list').then(m => m.default)
const CarSeekInfo = () => import('../pages/car-seek-info').then(m => m.default)
const WuliuList = () => import('../pages/wuliu-list').then(m => m.default)
const WuliuFreight = () => import('../pages/wuliu-freight').then(m => m.default)

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
      title: '金融购',
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
      title: '申请贷款',
      auth: true
    }
  },
  {
    path: '/loan/list',
    name: 'loan-list',
    component: LoanList,
    meta: {
      title: '贷款申请'
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
    path: '/me/store/info',
    name: 'me-store-info',
    component: MeStoreInfo,
    meta: {
      title: '完善商家信息'
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
]

export default routes