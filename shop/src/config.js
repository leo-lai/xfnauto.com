const resURL = 'https://res.xfnauto.com'
const apiURL = window.location.host.indexOf('xfnauto.com') !== -1 ? 'https://tomcat.xfnauto.com/tauto' : 'http://tomcat.mifengqiche.com/tauto'
const apiURL2 = window.location.host.indexOf('xfnauto.com') !== -1 ? 'https://api.xfnauto.com' : 'http://api.mifengqiche.com'
let config = {
  tel: '400-1639-989',
  resURL, apiURL, apiURL2,
  thumb0: `${resURL}/shop/logo-100x100.jpg`,
  thumb1: 'http://opii7iyzy.bkt.clouddn.com/momo.jpg?imageMogr2/format/jpg/interlace/1/quality/60/gravity/Center/thumbnail/100x/crop/x100',
  thumb2: `${resURL}/shop/20180402013.png`,
  thumb3: `${resURL}/shop/20180402020.png`,
  storeType: [
    { key: 1, value: '4S店' }, 
    { key: 2, value: '资源公司' }, 
    { key: 3, value: '汽贸公司' }
  ],
  bankList: [
    { key: 1, value: '奇瑞金融' },
    { key: 2, value: '瑞福德金融' },
    { key: 3, value: '建设银行' },
    { key: 4, value: '农业银行' },
    { key: 5, value: '工商银行' },
    { key: 6, value: '广州银行' },
    { key: 7, value: '鹤山珠江村镇银行' },
    { key: 8, value: '鹤山农村信用合作社' }
  ]
}

export default config