<template>
  <view-box>
    <group gutter="0">
      <x-input title="商铺名称" placeholder="必填" :max="100" placeholder-align="right" v-model="formData.shopName"></x-input>
      <popup-radio title="商家类型" :options="storeType" v-model="formData.type" placeholder="请选择">
        <div slot="popup-header" class="vux-1px-b l-padding-btn l-txt-center">请选择商家类型</div>
      </popup-radio>
      <x-address title="商铺区域" placeholder="请选择" raw-value :list="addressData" v-model="regionValue" @on-hide="sltAddress"></x-address>
      <x-textarea class="vux-x-input-placeholder-right" title="详细地址" placeholder="请输入详细地址 (街道、门牌等)" :show-counter="false" :rows="1" autosize v-model="formData.address"></x-textarea>
      <x-textarea class="vux-x-input-placeholder-right" title="商铺描述" placeholder="选填" :rows="3" v-model="formData.describes"></x-textarea>
    </group>

    <group label-width="6em">
      <x-input title="法人姓名" placeholder="必填" :max="20" placeholder-align="right" v-model="formData.corporation"></x-input>
      <x-input title="联系电话" placeholder="必填" keyboard="number" is-type="china-mobile" :max="11" placeholder-align="right" v-model="formData.phone"></x-input>
      <cell title="法人身份证" align-items="flex-start">
        <div slot="title" style="width: 10em;">法人身份证</div>
        <div class="l-idcard-upload l-flex-hc" slot="inline-desc">
          <div class="_item" @click="chooseImage(1, 1)">
            <img :src="formData.idCardPicOn" alt=''>
            <p class="l-txt-gray l-fs-s">身份证正面</p>
          </div>
          <div class="l-margin-l"></div>
          <div class="_item" @click="chooseImage(2, 1)">
            <img :src="formData.idCardPicOff" alt=''>
            <p class="l-txt-gray l-fs-s">身份证反面</p>
          </div>
        </div>
      </cell>
      <cell title="营业执照" align-items="flex-start">
        <span class="l-icon l-fs-x2" @click="chooseImage(3, 9)">&#xe610;</span>
        <div slot="inline-desc" class="l-preview-imgs">
          <img class="_item" :src="item" v-for="(item, index) in businessLicense" :key="index" @click="$api.previewImage(businessLicense, index)">
        </div>
      </cell>
      <cell title="商铺照片" align-items="flex-start">
        <span class="l-icon l-fs-x2" @click="chooseImage(4, 9)">&#xe610;</span>
        <div slot="inline-desc" class="l-preview-imgs">
          <img class="_item" :src="item" v-for="(item, index) in storeImages" :key="index" @click="$api.previewImage(storeImages, index)">
        </div>
      </cell>
    </group>

    <div class="l-fixed-bottom">
      <div class="_placeholder"></div>
      <div class="_inner">
        <div class="l-flex-hc l-padding">
          <template v-if="isRegister">
            <x-button style="width: 50%;" class="l-btn-radius" @click.native="gohome">稍后认证，去淘车</x-button>
            <span class="l-margin-l"></span>
          </template>
          <x-button style="width: 50%;" class="l-btn-radius" type="primary" @click.native="submit">提交审核</x-button>
        </div>
      </div>
    </div>
  </view-box>
</template>

<script>
import { PopupRadio, XTextarea, XAddress, ChinaAddressV4Data, Value2nameFilter as value2name } from 'vux'
export default {
  name: 'store-auth',
  components: { PopupRadio, XAddress, XTextarea },
  data() {
    return {
      isRegister: false,
      addressData: ChinaAddressV4Data,
      storeType: [],
      regionValue: [],
      businessLicense: [],
      storeImages: [],
      formData: {
        shopName: '',
        type: '',
        provinceId: '',
        provinceName: '',
        cityId: '',
        cityName: '',
        areaId: '',
        areaName: '',
        address: '',
        describes: '',                    // 商铺描述
        corporation: '',                  // 法人姓名
        phone: '',                        // 联系电话
        idCardPicOn: '',
        idCardPicOff: '',
      }
    }
  },
  methods: {
    gohome() {
      this.$router.replace('/')
    },
    sltAddress() {
      if (this.regionValue.length === 3) {
        let regionName = value2name(this.regionValue, ChinaAddressV4Data).split(' ')
        this.formData.provinceId = this.regionValue[0]
        this.formData.cityId = this.regionValue[1]
        this.formData.areaId = this.regionValue[2]
        this.formData.provinceName = regionName[0]
        this.formData.cityName = regionName[1]
        this.formData.areaName = regionName[2]
      }
    },
    chooseImage(type = 1, number = 1) {
      this.$api
        .chooseImage(number)
        .then(localIds => {
          this.$api.uploadImage(localIds).then(({ serverIds, images, localIds }) => {
            switch (type) {
              case 1: // 身份证正面
                this.formData.idCardPicOn = images[0]
                break
              case 2: // 身份证反面
                this.formData.idCardPicOff = images[0]
                break
              case 3: // 营业执照
                this.businessLicense = this.businessLicense.concat(images)
                break
              case 4: // 商铺照片
                this.storeImages = this.storeImages.concat(images)
                break
            }
          })
        })
        .catch(errMsg => {
          this.$vux.confirm.show({
            title: '授权提示',
            content: '需要获取您的相册权限，是否允许？',
            cancelText: '不允许',
            confirmText: '允许',
            onConfirm: res => {
              this.$utils.url.reload()
            }
          })
        })
    },
    getStoreInfo() {
      this.$api.user.getStoreInfo().then(({ data }) => {
        if(data){
          this.$utils.copyObj(this.formData, data)
          this.regionValue = [data.provinceName, data.cityName, data.areaName]
          this.businessLicense = data.license
          this.storeImages = data.image
        }
      })
    },
    submit() {
      if (!this.formData.shopName) {
        this.$toptip('请填写商铺名称')
        return
      }
      if (!this.formData.type) {
        this.$toptip('请选择商家类型')
        return
      }
      if (this.regionValue.length === 0) {
        this.$toptip('请选择商铺区域')
        return
      }
      if (!this.formData.address) {
        this.$toptip('请填写商铺详细地址')
        return
      }
      if (!this.formData.corporation) {
        this.$toptip('请填写法人姓名')
        return
      }
      if (!this.formData.phone) {
        this.$toptip('请填写联系电话')
        return
      }
      if (!this.formData.idCardPicOn) {
        this.$toptip('请上传企业法人身份证正面')
        return
      }
      if (!this.formData.idCardPicOff) {
        this.$toptip('请上传企业法人身份证反面')
        return
      }
      if (this.businessLicense.length === 0) {
        this.$toptip('请上传营业执照')
        return
      }
      // if(!this.formData.image) {
      //   this.$toptip('请上传商铺照片')
      //   return
      // }
      
      this.formData.idCard = this.formData.idCardPicOn + ',' + this.formData.idCardPicOff
      this.formData.license = this.businessLicense.join(',')
      this.formData.image = this.storeImages.join(',')

      this.$vux.loading.show()
      this.$api.user.saveStoreInfo(this.formData).then(({ data }) => {
          this.$router.replace('/msg?type=1&register=' + (this.$route.query.register || ''))
        }).finally(_ => {
          this.$vux.loading.hide()
        })
    }
  },
  mounted() {
    this.storeType = this.$config.storeType
    this.isRegister = !!this.$route.query.register

    this.getStoreInfo()
  }
}
</script>
<style lang="less" scoped>
.l-preview-imgs{margin-top: 0; margin-right: -20px;}
</style>

