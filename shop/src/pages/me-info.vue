<template>
  <view-box>
    <group gutter="0">
      <cell title="头像">
        <div class="l-user-avatar l-bg-co" :style="{'background-image':'url('+ userInfo.headPortrait +')'}"></div>
      </cell>
      <cell title="姓名" :value="userInfo.realName"></cell>
      <cell title="手机号码" :value="userInfo.phoneNumber"></cell>
    </group>

    <group>
      <cell title="商铺认证" is-link @click.native="storeAuth">
        <img class="weui-cell__icon" slot="icon" src="../assets/images/icon-008.png">
        <span v-if="shopState[userInfo.shopState]" :class="shopState[userInfo.shopState].css">{{shopState[userInfo.shopState].name}}</span>
      </cell>
      <cell title="垫资资格认证" is-link @click.native="loanAuth">
        <img class="weui-cell__icon" slot="icon" src="../assets/images/20180402004.png">
        <span v-if="loanState[userInfo.loanState]" :class="loanState[userInfo.loanState].css">{{loanState[userInfo.loanState].name}}</span>
      </cell>
    </group>

    <div class="l-fixed-bottom">
      <div class="_placeholder"></div>
      <div class="_inner">
        <div class="l-btn-w50 l-padding-tb">
          <x-button @click.native="logout" class="l-btn-radius" type="primary">退出登录</x-button>
        </div>
      </div>
    </div>
  </view-box>
</template>

<script>
export default {
  name: 'me-info',
  data() {
    return {
      userInfo: {},
      shopState: {
        '-1': {
          css: 'l-txt-gray',
          name: '去认证'
        },
        '0': {
          css: 'l-txt-warn',
          name: '审核中'
        },
        '1': {
          css: 'l-txt-theme',
          name: '已认证'
        },
        '2': {
          css: 'l-txt-error',
          name: '认证不通过'
        },
      },
      loanState: {
        '-1': {
          css: 'l-txt-gray',
          name: '去认证'
        },
        '0': {
          css: 'l-txt-warn',
          name: '审核中'
        },
        '1': {
          css: 'l-txt-theme',
          name: '已认证'
        },
        '2': {
          css: 'l-txt-error',
          name: '认证不通过'
        },
      }
    }
  },
  methods: {
    logout() {
      this.$vux.confirm.show({
        title: '系统提示',
        content: '是否确定退出登录',
        onConfirm: _ => {
          this.$api.user.logout()
        }
      })
    },
    storeAuth() {
      switch(this.userInfo.shopState) {
        case -1: // 未认证
          this.$router.push('/store/auth')
          break 
        default: // 审核中 已认证 认证不通过
          this.$router.push('/store/info')
          break
      }
    },
    loanAuth() {
      if(this.userInfo.shopState === 1){
        this.$router.push('/loan/auth')
      }else{
        this.$vux.alert.show({
          content: '请先认证商铺',
          onHide: _ => {
            this.storeAuth()
          }
        })
      }
    }
  },
  mounted() {
    this.$api.user.getAllInfo().then(({ data }) => {
      this.userInfo = data
    })
  }
}
</script>

<style lang="less">
.l-user-avatar {
  width: 50px;
  height: 50px;
  background-color: #ddd;
  border-radius: 50%;
}
</style>

