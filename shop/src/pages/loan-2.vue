<template>
  <view-box>
    <div class="l-flex-hc l-padding-btn">
      <img class="l-img-icon l-margin-r-m" src="../assets/images/icon-010.png" alt="">
      <h4 class="l-rest">车辆信息</h4>
    </div>
    <group gutter="0" label-width="6em">
      <cell title="车型" link="/car/selector">
        <div style="padding: 2px 0;" v-if="formData.carsId" class="l-fs-m">{{formData.carsName}}</div>
        <span v-else class="l-txt-gray">请选择车型</span>
      </cell>
      <cell title="指导价">
        <span>{{formData.guidancePrice}} 元</span>
      </cell>
      <x-input title="车辆数量" placeholder="请输入申请贷款车辆数量" type="tel" :max="10" placeholder-align="right" v-model="formData.carNumber"></x-input>
      <x-input title="贷款总额" placeholder="请输入贷款总额" type="number" :max="10" placeholder-align="right" v-model="formData.loanAmount">
        <span slot="right" class="l-txt-gray l-margin-l-s">元</span>
      </x-input>
    </group>

    <div class="l-flex-hc l-padding-btn l-margin-t">
      <img class="l-img-icon l-margin-r-m" src="../assets/images/icon-008.png" alt="">
      <h4 class="l-rest">商家信息</h4>
      <router-link v-if="!storeInfo" class="l-fr l-link-main" to="/me/store/info">去完善信息</router-link>
      <span v-else class="l-txt-theme">{{storeStatus[storeInfo.status - 1]}}</span>
    </div>

    <group gutter="0" v-if="storeInfo" label-width="6em">
      <cell title="商家类型" :value="getStoreType(storeInfo.natureType)"></cell>
      <cell title="店铺名称" :value="storeInfo.shortName"></cell>
      <cell title="店铺地址" align-items="flex-start" :value="getAddress()"></cell>
      <cell title="店铺描述" align-items="flex-start" :value="storeInfo.introduce"></cell>
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

    <div class="l-fixed-bottom">
      <div class="_placeholder"></div>
      <div class="_inner">
        <div class="l-btn-w50 l-padding-tb"><x-button @click.native="submit" class="l-btn-radius" type="primary">提交申请</x-button></div>
      </div>
    </div>
  </view-box>
</template>

<script>
export default {
  name: 'loan-2',
  data () {
    return {
      storeStatus: ['审核通过', '已禁用', '审核中'],
      storeInfo: null,
      businessLicense: [],
      storeImages: [],
      formData: {
        carsId: '',
        carsName: '',
        guidancePrice: '',
        carNumber: '',
        loanAmount: ''
      }
    }
  },
  methods: {
    getStoreType(key = 1) {
      return this.$config.storeType.filter(item => item.key === key)[0].value
    },
    getAddress() {
      let {provinceName, cityName, areaName, address} = this.storeInfo
      return (provinceName === cityName ? provinceName : provinceName + cityName) + areaName + address
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
    },
    submit() {
      if(!this.formData.carsId) {
        this.$toptip('请选择车型')
        return
      }
      if(!(Number(this.formData.carNumber) > 0)) {
        this.$toptip('请输入申请贷款车辆数量')
        return
      }
      if(!(Number(this.formData.loanAmount) > 0)) {
        this.$toptip('请输入贷款总额')
        return
      }

      this.$vux.loading.show()
      this.$api.loan.apply2(this.formData).then(({data}) => {
        this.$vux.toast.show({
          text: '提交申请成功',
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
    this.getStoreInfo()
    let sltedCar = this.$storage.session.get('car_slted')
    if(sltedCar) {
      this.formData.carsId = sltedCar.id
      this.formData.carsName = sltedCar.name
      this.formData.guidancePrice = sltedCar.price
    }
  }
}
</script>

<style lang="less">
</style>

