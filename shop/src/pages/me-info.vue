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
        <span v-else class="l-txt-theme">{{storeStatus[storeInfo.status - 1]}}</span>
      </div>
      
      <group v-if="storeInfo" gutter="0" label-width="6em">
        <cell title="店长姓名" :value="storeInfo.linkMan"></cell>
        <cell title="店长电话" :value="storeInfo.telePhone"></cell>
        <cell title="客服姓名" :value="storeInfo.serviceName"></cell>
        <cell title="客服电话" :value="storeInfo.servicePhone"></cell>
      </group>
      <group v-if="storeInfo" label-width="6em">
        <cell title="商家类型" :value="getStoreType(storeInfo.natureType)"></cell>
        <cell title="店铺名称" :value="storeInfo.shortName"></cell>
        <cell title="店铺地址" align-items="flex-start" :value="getAddress()"></cell>
        <cell title="店铺描述" align-items="flex-start" :value="storeInfo.introduce"></cell>
      </group>
      <group v-if="storeInfo" label-width="6em">
        <cell title="企业法人身份证" align-items="flex-start">
          <div slot="title" style="width: 10em;">企业法人身份证</div>
          <div class="l-idcard-upload l-flex-hc" slot="inline-desc">
            <div class="_item">
              <img :src="storeInfo.idCardPicOn" alt="">
              <p class="l-txt-gray l-fs-s">身份证正面</p>
            </div>
            <div class="l-margin-l"></div>
            <div class="_item">
              <img :src="storeInfo.idCardPicOff" alt="">
              <p class="l-txt-gray l-fs-s">身份证反面</p>
            </div>
          </div>
        </cell>
        <cell title="营业执照" align-items="flex-start">
          <div class="l-margin-t-m" slot="inline-desc">
            <div class="l-preview-imgs">
              <img class="_item" :src="item" v-for="item in businessLicense" :key="item">
            </div>
          </div>
        </cell>
        <cell title="店铺照片" align-items="flex-start">
          <div class="l-margin-t-m" slot="inline-desc">
            <div class="l-preview-imgs">
              <img class="_item" :src="item" v-for="item in storeImages" :key="item">
            </div>
          </div>
        </cell>
      </group>
      <router-link v-else tag="div" to="/me/store/info" class="l-bg-white l-padding l-txt-center" style="padding-top: 10%;padding-bottom: 10%;">
        <img style="width: 50%; margin-right: -1.2rem;" src="../assets/images/20180402014.png" alt="">
        <p class="l-txt-gray l-margin-t l-fs-s">请完善商家信息</p>
      </router-link>
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
      storeStatus: ['店铺资料审核通过', '店铺资料审核不通过', '审核中'],
      userInfo: null,
      storeInfo: null,
      businessLicense: [],
      storeImages: []
    }
  },
  methods: {
    getStoreType(key = 1) {
      return (this.$config.storeType.filter(item => item.key === key)[0] || {}).value || ''
    },
    getAddress() {
      let {provinceName, cityName, areaName, address} = this.storeInfo
      return (provinceName === cityName ? provinceName : provinceName + cityName) + areaName + address
    },
    logout() {
      this.$vux.confirm.show({
        title: '系统提示',
        content: '是否确定退出登录',
        onConfirm: _ => {
          this.$api.user.logout()
        }
      })
    },
    getStoreInfo() {
      this.$vux.loading.show()
      this.$api.user.getStoreInfo().then(({data}) => {
        if(data) {
          this.storeInfo = data
          this.businessLicense = data.businessLicense ? data.businessLicense.split(',') : []
          this.storeImages = data.imageUrl ? data.imageUrl.split(',') : []
        }
      }).finally(_ => {
        this.$vux.loading.hide()
      })
    }
  },
  mounted() {
    this.$api.user.getInfo().then(data => {
      this.userInfo = data
      if(data.userType === 2) {
        this.getStoreInfo()
      }
    })
  }
}
</script>

<style lang="less">
.l-user-avatar{ width: 50px; height: 50px; background-color: #fff; border-radius: 50%; }
</style>

