// const apiConfig = require('./app.config')

module.exports = {
  // https://github.com/isaacs/node-lru-cache#options
  cache: {
    max: 20,
    maxAge: 600000
  },
  /*
  ** Customize the progress-bar color
  */
  loading: { color: '#fc6441' },
  /*
  ** Headers of the page
   * https://github.com/declandewet/vue-meta#recognized-metainfo-properties
  */
  head: {
    htmlAttrs: {
      xmlns: 'http://www.w3.org/1999/xhtml',
      lang: 'zh'
    },
    title: '喜蜂鸟网络科技服务有限公司',
    titleTemplate: '%s|喜蜂鸟网络科技服务有限公司',
    meta: [
      { charset: 'utf-8' },
      // { name: 'viewport', content: 'width=device-width, initial-scale=1' },
      { name: 'fragment', content: '!' },
      { 'http-equiv': 'X-UA-Compatible', content: 'IE=edge,chrome=1' },
      { name: 'renderer', content: 'webkit|ie-comp|ie-stand' },
      { hid: 'keywords', name: 'keywords', content: '喜蜂鸟网络科技服务有限公司' },
      { hid: 'description', name: 'description', content: '喜蜂鸟网络科技服务有限公司' }
    ],
    link: [
      { rel: 'icon', type: 'image/x-icon', href: '/favicon.png' }
    ],
    script: [
      // { type: 'text/javascript', src: 'https://static.ushiyihao.com/feature.min.js'},
    ],
    noscript: [
      { innerHTML: 'This website requires JavaScript.' }
    ]
  },
  /*
  ** Customize css
  */
  css: [
    'element-ui/lib/theme-chalk/index.css'
  ],
  /*
  ** plugins
  */
  plugins: [
    {src: '~/plugins/element-ui.js', ssr: true},
    {src: '~/plugins/utils.js', ssr: false}
    // {src: '~/plugins/baidu-seo.js', ssr: false}
  ],
  /*
  ** https://github.com/nuxt-community/axios-module
  modules: [
    '@nuxtjs/axios'
  ],
  axios: {
    baseURL,
    credentials: false,
    proxyHeaders: false
  },
  */
  /*
  ** Build configuration
  */
  build: {
    // analyze: true,
    vendor: ['babel-polyfill', 'axios', 'element-ui', 'vue-scrollto'],
    /*
    ** Run ESLINT on save
    */
    extend (config, ctx) {
      if (ctx.dev && ctx.isClient) {
        // config.module.rules.push({
        //   enforce: 'pre',
        //   test: /\.(js|vue)$/,
        //   loader: 'eslint-loader',
        //   exclude: /(node_modules)/
        // })
      }
    }
  }
}
