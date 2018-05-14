<template>
  <view-box>
    <group gutter="0">
      <x-input title="店长姓名" placeholder="请输入店长姓名" :show-clear="false" :max="20" placeholder-align="right" v-model="formData.linkMan"></x-input>
      <x-input title="店长电话" placeholder="请输入店长联系电话" type="tel" :max="11" :show-clear="false" placeholder-align="right" v-model="formData.telePhone"></x-input>
    </group>
    <group>
      <x-input title="客服姓名" placeholder="请输入客服姓名" :show-clear="false" :max="20" placeholder-align="right" v-model="formData.serviceName"></x-input>
      <x-input title="客服电话" placeholder="请输入客服联系电话" type="tel" :max="11" :show-clear="false" placeholder-align="right" v-model="formData.servicePhone"></x-input>
    </group>
    <group>
      <popup-radio title="商家类型" :options="storeType" v-model="formData.natureType" placeholder="请选择商家类型">
        <div slot="popup-header" class="vux-1px-b l-padding-btn l-txt-center">请选择商家类型</div>
      </popup-radio>
      <x-input title="店铺名称" placeholder="请输入店铺名称" :max="100" :show-clear="false" placeholder-align="right" v-model="formData.shortName"></x-input>
      <x-address title="店铺区域" placeholder="请选择省市区" raw-value :list="addressData" v-model="regionValue" @on-hide="sltAddress"></x-address>
      <x-textarea class="vux-x-input-placeholder-right" title="详细地址" placeholder="请输入详细地址 (街道、门牌等)" :show-counter="false" :rows="1" autosize v-model="formData.address"></x-textarea>
      <x-textarea class="vux-x-input-placeholder-right" title="店铺描述" placeholder="请输入店铺描述" :rows="3" v-model="formData.introduce"></x-textarea>
    </group>

    <group label-width="6em"> 
      <cell title="上传企业法人身份证" align-items="flex-start">
        <div slot="title" style="width: 10em;">上传企业法人身份证</div>
        <div class="l-idcard-upload l-flex-hc" slot="inline-desc">
          <div class="_item" @click="chooseImage(1, 1)">
            <img :src="formData.idCardPicOn" alt="">
            <p class="l-txt-gray l-fs-s">身份证正面</p>
          </div>
          <div class="l-margin-l"></div>
          <div class="_item" @click="chooseImage(2, 1)">
            <img :src="formData.idCardPicOff" alt="">
            <p class="l-txt-gray l-fs-s">身份证反面</p>
          </div>
        </div>
      </cell>
      <cell title="上传营业执照" align-items="flex-start">
        <div slot="title" style="width: 7em;">上传营业执照</div>
        <div class="l-preview-imgs">
          <img class="_item" :src="item" v-for="(item,index) in businessLicense" :key="item" @click="$api.previewImage(businessLicense, index)">
          <i class="_add" src="../assets/images/icon-009.png" @click="chooseImage(3, 9)"></i>
        </div>
      </cell>
      <cell title="上传店铺照片" align-items="flex-start">
        <div slot="title" style="width: 7em;">上传店铺照片</div>
        <div class="l-preview-imgs">
          <img class="_item" :src="item" v-for="(item, index) in storeImages" :key="item" @click="$api.previewImage(storeImages, index)">
          <i class="_add" src="../assets/images/icon-009.png" @click="chooseImage(4, 9)"></i>
        </div>
      </cell>
    </group>

    <div class="l-fixed-bottom">
      <div class="_placeholder"></div>
      <div class="_inner">
        <div class="l-flex-hc l-padding">
          <template v-if="isRegister">
            <x-button style="width: 50%;" class="l-btn-radius" @click.native="gohome">先不完善，去淘车</x-button>
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
  name: 'me-store-info',
  components: { PopupRadio, XAddress, XTextarea },
  data () {
    return {
      isRegister: false,
      addressData: ChinaAddressV4Data,
      storeType: [],
      regionValue: [],
      businessLicense: [],
      storeImages:[],
      formData: {
        linkMan: '',
        telePhone: '',
        serviceName: '',
        servicePhone: '',
        natureType: '',
        shortName: '',
        provinceId: '',
        provinceName: '',
        cityId: '',
        cityName: '',
        areaId: '',
        areaName: '',
        address: '',
        introduce: '',
        idCardPicOn: '',
        idCardPicOff: '',
        businessLicense: '',
        imageUrl: ''
      }
    }
  },
  methods: {
    gohome() {
      this.$router.replace('/')
    },
    sltAddress() {
      if(this.regionValue.length === 3) {
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
      this.$api.chooseImage(number).then(localIds => {
        this.$api.uploadImage(localIds).then(({ serverIds, images, localIds })=>{
          console.log(serverIds, images, localIds)
          switch (type) {
            case 1: // 身份证正面
              this.formData.idCardPicOn = images[0]
              break
            case 2: // 身份证反面
              this.formData.idCardPicOff = images[0]
              break
            case 3: // 营业执照
              this.businessLicense = this.businessLicense.concat(images)
              this.formData.businessLicense = this.businessLicense.join(',')
              break
            case 4: // 店铺照片
              this.storeImages = this.storeImages.concat(images)
              this.formData.imageUrl = this.storeImages.join(',')
              break
          }
        })
      }).catch(errMsg => {
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
    submit() {
      console.log(this.formData)
      if(!this.formData.linkMan) {
        this.$toptip('请填写店长姓名')
        return
      }
      if(!this.formData.telePhone) {
        this.$toptip('请填写店长联系电话')
        return
      }
      // if(!this.formData.serviceName) {
      //   this.$toptip('请填写客服姓名')
      //   return
      // }
      // if(!this.formData.servicePhone) {
      //   this.$toptip('请填写客服联系电话')
      //   return
      // }
      if(!this.formData.natureType) {
        this.$toptip('请选择商家类型')
        return
      }
      if(!this.formData.shortName) {
        this.$toptip('请填写店铺名称')
        return
      }
      if(this.regionValue.length === 0) {
        this.$toptip('请选择店铺区域')
        return
      }
      if(!this.formData.address) {
        this.$toptip('请填写店铺详细地址')
        return
      }
      if(!this.formData.idCardPicOn) {
        this.$toptip('请上传企业法人身份证正面')
        return
      }
      if(!this.formData.idCardPicOff) {
        this.$toptip('请上传企业法人身份证反面')
        return
      }
      if(!this.formData.businessLicense) {
        this.$toptip('请上传营业执照')
        return
      }
      // if(!this.formData.imageUrl) {
      //   this.$toptip('请上传店铺照片')
      //   return
      // }

      this.$vux.loading.show()
      this.$api.user.saveStoreInfo(this.formData).then(({data}) => {
        this.$router.replace('/msg?type=1&register=' + (this.$route.query.register || ''))
      }).finally(_ => {
        this.$vux.loading.hide()
      })
    }
  },
  mounted() {
    this.storeType = this.$config.storeType
    this.isRegister = !!this.$route.query.register

    this.$api.user.getInfo().then(data => {
      console.log(data)
      this.formData.linkMan = data.realName
      this.formData.telePhone = data.phoneNumber
    })
  }
}
</script>

<style lang="less">

</style>

