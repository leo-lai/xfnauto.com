<template>
  <view-box>
    <group gutter="0" v-if="userInfo">
      <cell title="头像">
        <div class="l-user-avatar l-bg-co" :style="{'background-image':'url('+ userInfo.headPortrait +')'}"></div>
      </cell>
      <cell title="昵称" :value="userInfo.userName"></cell>
      <cell title="手机号码" :value="userInfo.phoneNumber"></cell>
    </group>

    <template v-if="userInfo && userInfo.userType === 2">
      <div class="l-flex-hc l-padding-btn l-bg-white l-margin-t">
        <img class="l-img-icon l-margin-r-m" src="../assets/images/icon-008.png" alt="">
        <h4 class="l-rest">商家信息</h4>
        <router-link v-if="!storeInfo" class="l-fr l-link-main" to="/me/store/info">去完善信息</router-link>
      </div>
      
      <group v-if="storeInfo" gutter="0" label-width="6em">
        <cell title="店长姓名" value=""></cell>
        <cell title="店长电话" value=""></cell>
        <cell title="客服姓名" value=""></cell>
        <cell title="客服电话" value=""></cell>
        <cell title="商家类型" value=""></cell>
        <cell title="店铺名称" value=""></cell>
        <cell title="店铺地址" align-items="flex-start" value=""></cell>
        <cell title="店铺描述" align-items="flex-start" value=""></cell>
        <cell title="营业执照" align-items="flex-start">
          <div class="l-preview-imgs">
            <!-- <img class="_item" src="http://thirdwx.qlogo.cn/mmopen/vi_32/zJ7D4do3QnFDsnavMiaHtNib6xwmp3fQ9gPPWdVHtpopvs1xCctMx0j1SJl7MXuqgDw6HHnTcX7dMBN0rdb7DIeg/132"> -->
          </div>
        </cell>
        <cell title="店铺照片" align-items="flex-start">
        </cell>
      </group>
      <div v-else class="l-bg-white l-padding l-txt-center" style="padding-top: 10%;padding-bottom: 10%;">
        <img style="width: 50%; margin-right: -1.2rem;" src="../assets/images/20180402014.png" alt="">
        <p class="l-txt-gray l-margin-t l-fs-s">请完善商家信息</p>
      </div>
    </template>
    <div class="l-fixed-bottom">
      <div class="_placeholder"></div>
      <div class="_inner">
        <div class="l-btn-w50 l-padding-tb"><x-button @click.native="logout" class="l-btn-radius" type="primary">退出登录</x-button></div>
      </div>
    </div>
  </view-box>
</template>

<script>
export default {
  name: 'me-info',
  data () {
    return {
      userInfo: null,
      storeInfo: null
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
    }
  },
  mounted() {
    this.$api.user.getInfo().then(data => {
      this.userInfo = data
    })

    this.$vux.loading.show()
    this.$api.user.getStoreInfo().then(({data}) => {
      this.storeInfo = data
    }).finally(_ => {
      this.$vux.loading.hide()
    })
  }
}
</script>

<style lang="less">
.l-user-avatar{ width: 50px; height: 50px; background-color: #fff; border-radius: 50%; }
</style>

