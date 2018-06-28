<template>
  <view-box>
    <group gutter="0" label-width="7em">
      <cell title="垫资车型" link="/car/selector">
        <div style="padding: 2px 0;" v-if="formData.carId" class="l-fs-m">{{formData.carName}}</div>
        <span v-else class="l-txt-gray">请选择车型</span>
      </cell>
      <cell title="指导价">
        <span>{{formData.guidancePrice}}</span><i class="l-margin-l-s">元</i>
      </cell>
      <popup-picker title="车身颜色" :data="cheshen.list" v-model="cheshen.slted"  @on-show="getCheShenList" show-name placeholder="请选择"></popup-picker>
      <x-input title="实际购车价格" placeholder="" type="tel" :max="10" placeholder-align="right" :show-clear="false" v-model="formData.price">
        <span class="l-txt-gray l-margin-l-s" slot="right">元</span>
      </x-input>
      <x-input title="保证金比列" placeholder="20 ~ 100" type="tel" :max="10" placeholder-align="right" :show-clear="false" v-model="formData.downPayments">
        <span class="l-txt-gray l-margin-l-s" slot="right">%</span>
      </x-input>
      <x-input title="垫资车辆数量" placeholder="" type="tel" :max="10" placeholder-align="right" :show-clear="false" v-model="formData.number">
        <span class="l-txt-gray l-margin-l-s" slot="right">辆</span>
      </x-input>
      <cell title="垫资金额">
        <span>{{formData.amount}}</span><i class="l-margin-l-s">元</i>
      </cell>
    </group>

    <div class="l-fixed-bottom">
      <div class="_placeholder"></div>
      <div class="_inner">
        <div class="l-btn-w50 l-padding-tb"><x-button @click.native="submit" class="l-btn-radius" type="primary">确定添加</x-button></div>
      </div>
    </div>
  </view-box>
</template>

<script>
import { PopupPicker } from 'vux'
export default {
  name: 'loan-2-addcar',
  components: {
    PopupPicker
  },
  data () {
    return {
      cheshen: {
        list: [],
        slted: []
      },
      formData: {
        carId: '',
        carName: '',
        colorId: '',
        colorName: '',
        guidancePrice: '',
        price: '',
        downPayments: 20,
        number: 1,
        amount: 0,
      }
    }
  },
  watch: {
    'formData.price': function(value) {
      clearTimeout(this.amountTimeId)
      this.amountTimeId = setTimeout(this.getAmount, 500)
    },
    'formData.downPayments': function(value) {
      clearTimeout(this.amountTimeId)
      this.amountTimeId = setTimeout(this.getAmount, 500)
    },
    'formData.number': function(value) {
      clearTimeout(this.amountTimeId)
      this.amountTimeId = setTimeout(this.getAmount, 500)
    },
  },
  methods: {
    getAmount() {
      let price = Number(this.formData.price) || 0
      let downPayments = Math.max(Math.min(Number(this.formData.downPayments) || 20, 100), 20)
      let number = Math.max(Number(this.formData.number) || 1, 1)

      this.formData.price = price
      this.formData.downPayments = downPayments
      this.formData.number = number

      if(price && number) {
        this.formData.amount = Math.ceil(price * number * (1 - downPayments / 100) * 100) / 100
      }
    },
    getCheShenList() {
      if(this.formData.familyId) {
        if(this.cheshen.familyId != this.formData.familyId) {
          this.cheshen.familyId = this.formData.familyId
          this.$api.car.getCheShenList(this.formData.familyId).then(({data}) => {
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
    submit() {
      if(!this.formData.carId) {
        this.$toptip('请选择车型')
        return
      }

      this.formData.colorId = this.cheshen.slted[0] || ''
      // if(!this.formData.colorId) {
      //   this.$toptip('请选择车身颜色')
      //   return
      // }

      if(!(Number(this.formData.price) > 0)) {
        this.$toptip('请输入实际购车价格')
        return
      }
      if(!(Number(this.formData.downPayments) > 0)) {
        this.$toptip('请输入保证金比例')
        return
      }
      if(!(Number(this.formData.number) > 0)) {
        this.$toptip('请输入垫资车辆数量')
        return
      }
      
      let addcar = this.$storage.session.get('loan-2-addcar') || []
      this.formData.guid = this.$utils.guid()
      addcar.push(this.formData)
      this.$storage.session.set('loan-2-addcar', addcar)
      this.$router.back()
    }
  },
  mounted() {
    let sltedCar = this.$storage.session.get('car_slted')
    let sltedFamily = this.$storage.session.get('family_slted')
    if(sltedCar) {
      this.formData.carId = sltedCar.id
      this.formData.carName = sltedCar.name
      this.formData.guidancePrice = sltedCar.price
      this.formData.familyId = sltedFamily.id
    }
  },
  beforeDestroy() {
    this.$storage.session.remove('car_slted')
    this.$storage.session.remove('family_slted')
  }
}
</script>
