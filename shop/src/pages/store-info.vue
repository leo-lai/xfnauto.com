<template>
  <view-box>
    <div v-if="shopState[storeInfo.state]" class="l-shop-state">
      <h4><b>{{shopState[storeInfo.state].name}}</b></h4>
      <p v-if="storeInfo.state == 2" class="l-margin-t-m">{{storeInfo.reason}}</p>
    </div>
    <group label-width="6em">
      <cell title="商铺名称" :value="storeInfo.shopName"></cell>
      <cell title="商家类型" :value="getStoreType(storeInfo.type)"></cell>
      <cell title="商铺地址" align-items="flex-start" :value="getAddress()"></cell>
      <cell title="商铺描述" align-items="flex-start" :value="storeInfo.describes"></cell>
    </group>
    <group label-width="6em">
      <cell title="法人姓名" :value="storeInfo.corporation"></cell>
      <cell title="联系电话" :value="storeInfo.phone"></cell>
      <cell title="法人身份证" align-items="flex-start">
        <div slot="title" style="width: 10em;">法人身份证</div>
        <div class="l-idcard-upload l-flex-hc" slot="inline-desc">
          <div class="_item">
            <img :src="storeInfo.idCardPicOn" alt="">
          </div>
          <div class="l-margin-l"></div>
          <div class="_item">
            <img :src="storeInfo.idCardPicOff" alt="">
          </div>
        </div>
      </cell>
      <cell title="营业执照" align-items="flex-start">
        <div class="l-margin-t-m" slot="inline-desc">
          <div class="l-preview-imgs">
            <img class="_item" :src="item" v-for="item in storeInfo.license" :key="item">
          </div>
        </div>
      </cell>
      <cell title="商铺照片" align-items="flex-start">
        <div class="l-margin-t-m" slot="inline-desc">
          <div class="l-preview-imgs">
            <img class="_item" :src="item" v-for="item in storeInfo.image" :key="item">
          </div>
        </div>
      </cell>
    </group>

    <div class="l-fixed-bottom" v-if="storeInfo.state == 2">
      <div class="_placeholder"></div>
      <div class="_inner l-padding">
        <x-button style="width: 50%;" class="l-btn-radius" type="primary" link="/store/auth">重新提交审核</x-button>
      </div>
    </div>
  </view-box>
</template>

<script>

export default {
  name: 'store-info',
  components: {},
  data() {
    return {
      storeInfo: {},
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
      }
    }
  },
  methods: {
    getStoreType(key = 1) {
      return (this.$config.storeType.filter(item => item.key === key)[0] || {}).value || ''
    },
    getAddress() {
      let { provinceName, cityName, areaName, address } = this.storeInfo
      return (provinceName === cityName ? provinceName : provinceName + cityName) + areaName + address
    },
    getStoreInfo() {
      this.$api.user.getStoreInfo().then(({ data }) => {
        this.storeInfo = data
      })
    }
  },
  mounted() {
    this.getStoreInfo()
  }
}
</script>
<style lang="less" scoped>
.l-shop-state{
  background-color: @theme-color; padding: 15px; text-align: center;
  color: #ffffff;
}
</style>


