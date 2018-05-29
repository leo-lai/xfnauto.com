import home from '../pages/home'
const youshi = () => import('../pages/youshi').then(m => m.default)
const joinus = () => import('../pages/joinus').then(m => m.default)
const about = () => import('../pages/about').then(m => m.default)

const routes = [
  {
    path: '/',
    name: 'home',
    component: home,
    meta: {
      title: '首页'
    }
  },
  {
    path: '/youshi',
    name: 'youshi',
    component: youshi,
    meta: {
      title: '合作优势'
    }
  },
  {
    path: '/joinus',
    name: 'joinus',
    component: joinus,
    meta: {
      title: '招商加盟'
    }
  },
  {
    path: '/about',
    name: 'about',
    component: about,
    meta: {
      title: '关于我们'
    }
  }
]

export default routes