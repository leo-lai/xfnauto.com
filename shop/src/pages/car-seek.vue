<template>
  <view-box>
    <group gutter="0" label-width="6em">
      <cell title="车型" link="/car/selector">
        <div style="padding: 2px 0; color:#333;" v-if="formData.carsId" class="l-fs-m">{{formData.carsName}}</div>
        <span v-else class="l-txt-gray">请选择车型</span>
      </cell>
      <cell title="指导价">
        <span style="color:#333;">{{formData.guidancePrice}}</span><span class="l-margin-l-s">万元</span>
      </cell>
      <popup-picker title="车身颜色" :data="cheshen.list" v-model="cheshen.slted"  @on-show="getCheShenList" show-name placeholder="请选择"></popup-picker>
      <popup-picker title="内饰颜色" :data="neishi.list" v-model="neishi.slted"  @on-show="getNeishiList" show-name placeholder="请选择"></popup-picker>
    </group>

    <group>
      <x-input title="期望价" placeholder="请填写" type="number" :show-clear="false" :max="10" placeholder-align="right" v-model="formData.expectationAmount">
        <span slot="right" class="l-txt-gray l-margin-l-s">万元</span>
      </x-input>
      <x-input title="上牌城市" placeholder="请填写" :show-clear="false" :max="50" placeholder-align="right" v-model="formData.signCity"></x-input>
      <calendar title="期望提车时间" v-model="formData.expectationHaveingCarTime" placeholder="请选择" disable-past></calendar>
      <x-input title="联系人姓名" placeholder="请填写" :show-clear="false" :max="20" placeholder-align="right" v-model="formData.linkmanName"></x-input>
      <cell title="联系人电话" :value="formData.linkmanPhone"></cell>
      <!-- <x-input title="联系人电话" placeholder="请填写" type="tel" :max="11" :show-clear="false" placeholder-align="right" v-model="formData.linkmanPhone"></x-input> -->
      <x-textarea class="vux-x-input-placeholder-right" title="特殊要求" placeholder="请填写" :rows="3" v-model="formData.remarks"></x-textarea>
    </group>

    <div class="l-fixed-bottom">
      <div class="_placeholder"></div>
      <div class="_inner">
        <div class="l-btn-w50 l-padding-tb"><x-button @click.native="submit" class="l-btn-radius" type="primary">确定寻车</x-button></div>
      </div>
    </div>
  </view-box>
</template>

<script>
import { PopupPicker, Calendar, XTextarea } from 'vux'

export default {
  name: 'car-seek',
  components: {
    PopupPicker, Calendar, XTextarea
  },
  data () {
    return {
      cheshen: {
        list: [],
        slted: []
      },
      neishi: {
        list: [],
        slted: []
      },
      formData: {
        carsId: '',
        carsName: '',
        guidancePrice: '',
        familyId: '',
        colourId: '',
        interiorId: '',
        expectationAmount: '',
        expectationHaveingCarTime: '',
        signCity: '',
        linkmanName: '',
        linkmanPhone: '',
        remarks: '',
      }
    }
  },
  methods: {
    getCheShenList() {
      if(this.formData.familyId) {
        if(this.cheshen.familyId != this.formData.familyId) {
          this.cheshen.familyId = this.formData.familyId
          this.$api.car.getChenShenList(this.formData.familyId).then(({data}) => {
            data = data.map(item => {
              return {
                name: item.carColourName,
                value: item.carColourId + ''
              }
            })
            this.cheshen.list = [data]
          })
        }
      }else {
        this.cheshen.list = [[]]
        this.$toptip('请选择车型')
      }
    },
    getNeishiList() {
      if(this.formData.familyId) {
        if(this.neishi.familyId != this.formData.familyId) {
          this.neishi.familyId = this.formData.familyId
          this.$api.car.getNeishiList(this.formData.familyId).then(({data}) => {
            data = data.map(item => {
              return {
                name: item.interiorName,
                value: item.interiorId + ''
              }
            })
            this.neishi.list = [data]
          })
        }
      }else {
        this.neishi.list = [[]]
        this.$toptip('请选择车型')
      }
    }, 
    submit() {
      if(!this.formData.carsId) {
        this.$toptip('请选择车型')
        return
      }
      // if(!this.cheshen.slted[0]) {
      //   this.$toptip('请选择车身颜色')
      //   return
      // }
      // if(!this.neishi.slted[0]) {
      //   this.$toptip('请选择内饰颜色')
      //   return
      // }
      if(!(Number(this.formData.expectationAmount) > 0)) {
        this.$toptip('请填写期望价')
        return
      }
      if(!this.formData.expectationHaveingCarTime) {
        this.$toptip('请选择期望提车时间')
        return
      }
      if(!this.formData.linkmanName) {
        this.$toptip('请填写联系人姓名')
        return
      }
      if(!this.formData.linkmanPhone) {
        this.$toptip('请填写联系人电话')
        return
      }

      this.formData.colourId = this.cheshen.slted[0] || ''
      this.formData.interiorId = this.neishi.slted[0] || ''

      this.$vux.loading.show()
      this.$api.seek.add(this.formData).then(({data}) => {
        this.$vux.toast.show({
          text: '提交成功',
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
    let sltedCar = this.$storage.session.get('car_slted')
    let sltedFamily = this.$storage.session.get('family_slted')
    if(sltedCar) {
      this.formData.carsId = sltedCar.id
      this.formData.carsName = sltedCar.name
      this.formData.guidancePrice = (sltedCar.price / 10000).toFixed(2)
      this.formData.familyId = sltedFamily.id
    }

    this.$api.user.getInfo().then(data => {
      console.log(data)
      this.userInfo = data
      this.formData.linkmanPhone = data.phoneNumber
    })
  }
}
</script>

<style lang="less">
</style>

