let config = {
  regexp: {
    int: /^\d{1,10}$/,
    tel: /^1\d{10}$/,
    money: /^\d{1,9}(\.\d{1,2})?$/
  },
  router: {
    base: Object.is(process.env.NODE_ENV, 'production') ? '/' : '/admin/'
  },
  api: {
    baseURL: window.location.host === 'admin.xfnauto.com' ? 
      'https://tomcat.xfnauto.com/tauto/management/admin' : 'http://tomcat.mifengqiche.com/tauto/management/admin'
  },
  pay: {
    url: window.location.host === 'admin.xfnauto.com' ? 
      'https://service.allinpay.com/gateway/index.do' : 'http://ceshi.allinpay.com/gateway/index.do'
  },
  baseData: {
    carSource: ['资源采购', '4S店出货', '订车入库'],
    carTime: ['随车', '3个工作日内', '7个工作日内', '10个工作日内', '15个工作日内'],
    buyTime: ['3天内', '7天内'],
    buyWay: ['全款', '分期'],
    carSpec: ['合格证', '一致证书', '环保清单', '主钥匙', '备用钥匙', '用户手册', '保养手册', '首保', '三包', '车架号拓印纸', '天线', '点烟器'],
    orgLevel: ['公司', '分公司', '门店'],
    edu: ['博士', '硕士', '本科', '大专', '高中', '其他'],
    carJP: ['防爆膜', '底盘漆', '地毯', '灭火器', '车头锁', '头枕', '抱枕', '香水', '导航', '全车座椅拉皮', '行车记录仪（单向）', '行车记录仪（双向）', '行车记录仪（隐藏式）', '行车记录仪（高清）', '晴雨挡', '挡泥板', '全车隔音', '倒车雷达（4探）', '倒车雷达（6探）'],
    orderState: {
      '1': '待交定金',
      '3': '等待银行审核',
      '4': '银行审核不通过',
      '5': '等待车辆出库',
      '7': '等待加装精品',
      '9': '等待上牌',
      '11': '等待贴膜',
      '13': '等待交付车辆',
      '15': '等待支付尾款',
      '17': '订单完成'
    }
  },
  editorOption: {
    placeholder: '请在这里输入内容...',
    modules: {
      toolbar: [
        [{ 'size': ['small', false, 'large', 'huge'] }],
        [{ 'font': [] }],
        [{ 'header': 1 }, { 'header': 2 }],
        [{ 'header': [1, 2, 3, 4, 5, 6, false] }],
        ['bold', 'italic', 'underline', 'strike'],
        ['blockquote', 'code-block'],
        [{ 'list': 'ordered' }, { 'list': 'bullet' }],
        // [{ 'script': 'sub' }, { 'script': 'super' }],
        [{ 'indent': '-1' }, { 'indent': '+1' }],
        [{ 'direction': 'rtl' }],
        [{ 'color': [] }, { 'background': [] }],
        [{ 'align': [] }],
        ['clean'],
        ['link', 'image']
      ],
      history: {
        delay: 1000,
        maxStack: 50,
        userOnly: false
      },
      imageDrop: true,
      imageResize: {
        displayStyles: {
          backgroundColor: 'black',
          border: 'none',
          color: 'white'
        },
        modules: [ 'Resize', 'DisplaySize', 'Toolbar' ]
      }
    }
  },
  qrcodeOption: {
    data: '',
    cellSize: 5,
    correctLevel: 'H',
    typeNumber: 5,
    logo: {
      fontFamily: 'Arial',
      size: 0.15,
      color: '#000',
      text: '',
      clearEdges: 2,
      margin: 10
    },
    effect: {
      key: 'round',
      value: 0.2  
    }
  }
}

export default config