<template>
  <view-box>
    <div class="l-register-tab">
      <a :class="{'_active': formData.userType === 1}" @click="formData.userType = 1">我是个人用户</a>
      <a :class="{'_active': formData.userType === 2}" @click="formData.userType = 2">我是商家</a>
    </div>
    <group gutter="0">
      <x-input class="l-ipt" type="tel" is-type="china-mobile" placeholder="请输入手机号码" :max="11" v-model="formData.phoneNumber">
        <i slot="label" class="l-icon">&#xe613;</i>
      </x-input>
      <x-input class="l-ipt" type="number" placeholder="请输入手机验证码" :max="6" v-model="formData.phoneCode">
        <i slot="label" class="l-icon">&#xe60f;</i>
        <x-button ref="sendBtn" type="primary" slot="right" mini @click.native="sendMobiCode">获取验证码</x-button>
      </x-input>
    </group>
    <group>
      <x-input class="l-ipt" type="text" placeholder="请输入您的真实姓名" :max="50" v-model="formData.realName">
        <i slot="label" class="l-icon">&#xe60e;</i>
      </x-input>
      <x-input class="l-ipt" type="password" placeholder="请输入登录密码" :max="50" v-model="formData.password">
        <i slot="label" class="l-icon">&#xe616;</i>
      </x-input>
      <x-input class="l-ipt" type="password" placeholder="请再次输入密码" :max="50" v-model="formData.password2">
        <i slot="label" class="l-icon">&#xe616;</i>
      </x-input>
    </group>

    <div class="l-btn-area">
      <x-button @click.native="register" type="primary">{{formData.userType === 1 ? '注册' : '注册并完善商家信息'}}</x-button>
      <x-button @click.native="back">返回</x-button>
    </div>
  </view-box>
</template>

<script>
export default {
  name: 'register',
  data () {
    return {
      formData: {
        userType: 1,
        phoneNumber: '',
        phoneCode: '',
        realName: '',
        password: '',
        password2: '',
        code: ''
      }
    }
  },
  methods: {
    back() {
      this.$router.go(-2)
    },
    sendMobiCode() {
      this.$api.sendMobiCode(this.formData.phoneNumber, this.$refs.sendBtn.$el)
    },
    register() {
      if(!this.formData.phoneNumber) {
        this.$toptip('请输入手机号码')
        return
      }
      if(!this.formData.phoneCode) {
        this.$toptip('请输入手机验证码')
        return
      }
      if(!this.formData.password) {
        this.$toptip('请输入登录密码')
        return
      }
      if(!this.formData.password2) {
        this.$toptip('请再次输入密码')
        return
      }
      if(this.formData.password2 !== this.formData.password) {
        this.$toptip('两次输入的密码不一致')
        return
      }

      this.$vux.loading.show({text: '注册中...'})
      this.$api.user.register(this.formData).then(({data}) => {
        this.$vux.toast.show({
          text: '注册成功',
          onHide: _ => {
            this.$storage.local.set('token', data.sessionId)
            this.$storage.local.set('userinfo', data)
            if(this.formData.userType === 1) {
              this.$router.replace('/me')
            }else {
              this.$router.replace('/me/store/info?register=1')
            }
          }
        })
      }).finally(_ => {
        this.$vux.loading.hide()
      })
    }
  },
  mounted() {
    this.formData.code = this.$route.query.code || ''
  }
}
</script>

<style lang="less">
.l-register-tab{
  padding: 30px 20px 15px; position: relative; z-index: 1;
  a{margin-right: 15px;}
  ._active{color: rgb(235, 97, 0); text-decoration: underline;position: relative;}
  ._active::after, ._active::before{
    content: ''; position: absolute; left: 50%; bottom: -19px;
    border-style: solid; border-width: 10px; border-color: transparent;
  }
  ._active::before{ border-bottom-color: #D9D9D9; transform: translate3d(-50%, -1px, 0)}
  ._active::after { border-bottom-color: #fff; transform: translate3d(-50%, 0, 0);}
}
</style>

