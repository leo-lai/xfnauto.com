<template>
  <div class="l-login-box l-flex-vhc">
    <img class="_logo" src="../assets/images/20180402013.png" alt="欢迎登录淘车网">
    <div class="l-login-form">
      <div class="_ipt l-flex-hc">
        <i class="l-icon">&#xe613;</i>
        <input autocomplete="off" autocapitalize="off" autocorrect="off" spellcheck="false" type="tel" placeholder="请输入手机号码" maxlength="11" v-model="formData.phoneNumber">
      </div>
      <div class="_ipt l-flex-hc">
        <i class="l-icon">&#xe60f;</i>
        <input autocomplete="off" autocapitalize="off" autocorrect="off" spellcheck="false" type="password" placeholder="请输入登录密码" maxlength="50" v-model="formData.password" @keyup.enter="login">
      </div>
      <div class="l-margin-t-m l-txt-right">
        <router-link class="l-link-gray l-fs-s" to="/password">忘记密码？</router-link>
      </div>
      <div class="l-margin-t-l">
        <x-button type="primary" @click.native="login">登录</x-button>
      </div>
    </div>
    
    <div class="_footer">
      <p class="l-txt-gray">您还没有账号？</p>
      <p><a class="l-link-main" @click="register">免费注册</a></p>
    </div>
  </div>
</template>

<script>
export default {
  name: 'login',
  data () {
    return {
      formData: {
        phoneNumber: '',
        password: ''
      }
    }
  },
  methods: {
    register() {
      // this.$api.getGrantUrl('/register')
      this.$router.push('/register?to=' + this.$route.query.to || '')
    },
    login() {
      if(!this.formData.phoneNumber) {
        this.$toptip('请输入手机号码')
        return
      }
      if(!this.formData.password) {
        this.$toptip('请输入登录密码')
        return
      }

      this.$vux.loading.show()
      this.$api.user.login(this.formData).then(({data}) => {
        this.$storage.local.set('token', data.sessionId)
        this.$storage.local.set('userinfo', data)

        this.$vux.toast.show({
          text: '登录成功',
          onHide: res => {
            let toUrl = this.$route.query.to
            if(toUrl) {
              this.$router.replace({
                path: toUrl, 
                query: {
                  direction: 'out'
                }
              })
            }else {
              this.$router.back()
            }
          }
        })
      }).finally(_ => {
        this.$vux.loading.hide()
      })
    }
  }
}
</script>

<style lang="less">
.l-login-box{
  position: absolute; left: 0; top: 0; width: 100%; height: 100%;
  background:#fff url(../assets/images/20180402012.jpg) no-repeat 50% 0;background-size: cover;
  ._logo{width: 150px; height: 150px; margin: -30% 0 30px;}
  ._footer{position: absolute; left: 0; right: 0; bottom: 30px; text-align: center; }
}
.l-login-form{
  width: 280px;
  ._ipt{
    background-color: #fdfdff; border: 1px solid #d5d8dd; border-radius: 5px; padding: 7px; margin-top: 15px;
    .l-icon{ display: inline-block; width: 30px; text-align: center; margin-right: 5px; color: #999;}
    input{border:none; background: none; padding: 5px 0; width: 100%; box-sizing: border-box; font-size: inherit;outline: none;}
  }
}
</style>

