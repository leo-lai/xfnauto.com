import Home from '../pages/home'
import Loan from '../pages/loan'
import Me from '../pages/me'
// import Login from '../pages/login'
const Login = () => import('../pages/login').then(m => m.default)

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
    path: '/me',
    name: 'me',
    component: Me,
    meta: {
      title: '我的',
      tabbar: true
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
]

export default routes