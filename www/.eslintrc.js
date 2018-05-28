module.exports = {
  root: true,
  parser: 'babel-eslint',
  env: {
    browser: true,
    node: true
  },
  extends: 'standard',
  // required to lint *.vue files
  plugins: [
    'html'
  ],
  // add your custom rules here
  // http://www.jianshu.com/p/29ca5a6a34fd
  // http://eslint.cn/docs/rules/
  rules: {
    'no-extend-native': 0,
    'no-tabs': 0,
    'no-mixed-spaces-and-tabs': 0,
    'indent': 0
  },
  globals: {}
}
