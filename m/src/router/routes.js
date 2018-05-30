import home from '../pages/home'
const youshi = () => import('../pages/youshi').then(m => m.default)
const joinus = () => import('../pages/joinus').then(m => m.default)
const about = () => import('../pages/about').then(m => m.default)
const contact = () => import('../pages/contact').then(m => m.default)
const news = () => import('../pages/news').then(m => m.default)
const infos = () => import('../pages/infos').then(m => m.default)

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
  },
  {
    path: '/contact',
    name: 'contact',
    component: contact,
    meta: {
      title: '联系我们'
    }
  },
  {
    path: '/news',
    name: 'news',
    component: news,
    meta: {
      title: '新闻资讯'
    }
  }, ,
  {
    path: '/infos',
    name: 'infos',
    component: infos,
    meta: {
      title: '新闻详情'
    }
  },
]
export default routes