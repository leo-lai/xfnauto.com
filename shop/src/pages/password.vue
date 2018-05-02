<template>
  <view-box>
    <group gutter="0">
      <x-input class="l-ipt" type="tel" is-type="china-mobile" placeholder="请输入手机号码" :max="11" v-model="formData.phoneNumber">
        <i slot="label" class="l-icon">&#xe613;</i>
      </x-input>
      <x-input class="l-ipt" type="number" placeholder="请输入手机验证码" :max="6" v-model="formData.phoneCode">
        <i slot="label" class="l-icon">&#xe60f;</i>
        <x-button ref="sendBtn" type="primary" slot="right" mini @click.native="sendMobiCode">获取验证码</x-button>
      </x-input>
      <x-input class="l-ipt" type="text" placeholder="请输入新密码" :max="50" v-model="formData.password" @on-enter="changePwd">
        <i slot="label" class="l-icon">&#xe616;</i>
      </x-input>
    </group>

    <div class="l-btn-area">
      <x-button @click.native="changePwd" type="primary">修改密码</x-button>
    </div>
  </view-box>
</template>

<script>
export default {
  name: 'password',
  data () {
    return {
      formData: {
        phoneNumber: '',
        phoneCode: '',
        password: '',
        password2: ''
      }
    }
  },
  methods: {
    back() {
      window.history.go(-2)
    },
    sendMobiCode() {
      this.$api.sendMobiCode(this.formData.phoneNumber, this.$refs.sendBtn.$el)
    },
    changePwd() {
      if(!this.formData.phoneNumber) {
        this.$toptip('请输入手机号码')
        return
      }
      if(!this.formData.phoneCode) {
        this.$toptip('请输入手机验证码')
        return
      }
      if(!this.formData.password) {
        this.$toptip('请输入新密码')
        return
      }

      this.$vux.loading.show()
      this.$api.user.forgotPwd(this.formData).then(({data}) => {
        this.$vux.toast.show({
          text: '修改成功',
          onHide: _ => {
            this.$router.back()
          }
        })
      }).finally(_ => {
        this.$vux.loading.hide()
      })
    }
  },
  mounted() {
    this.$api.user.getInfo().then(data => {
      if(data) {
        this.formData.phoneNumber = data.phoneNumber
      }
    })
  }
}
</script>

<style lang="less">

</style>

