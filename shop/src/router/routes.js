import Home from '../pages/home'
import Loan from '../pages/loan'
import Me from '../pages/me'
import Login from '../pages/login'
import Register from '../pages/register'
const MeStoreInfo = () => import('../pages/me-store-info').then(m => m.default)
const MeInfo = () => import('../pages/me-info').then(m => m.default)
const Password = () => import('../pages/password').then(m => m.default)
const Msg = () => import('../pages/msg').then(m => m.default)

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
      tabbar: true,
      auth: true,  // 是否需要登录
    }
  },
  {
    path: '/me',
    name: 'me',
    component: Me,
    meta: {
      title: '我的',
      tabbar: true  // 是否是tabbar页面
    }
  },
  {
    path: '/me/info',
    name: 'me-info',
    component: MeInfo,
    meta: {
      title: '账户资料',
      auth: true,  // 是否需要登录
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
      scope: 'snsapi_userinfo'     // 是否微信页面授权
      
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
  
]

export default routes