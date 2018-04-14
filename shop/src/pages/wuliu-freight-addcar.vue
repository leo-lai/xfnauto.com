<template>
  <view-box>
    <group gutter="0" label-width="6em">
      <cell title="车型" link="/car/selector">
        <div style="padding: 2px 0;" v-if="formData.carsId" class="l-fs-m">{{formData.carsName}}</div>
        <span v-else class="l-txt-gray">请选择车型</span>
      </cell>
      <cell title="指导价">
        <span class="l-rmb">{{formData.guidancePrice}}万</span>
      </cell>
      <x-input title="车辆数量" placeholder="请输入车辆数量" type="tel" :max="10" placeholder-align="right" v-model="formData.carNumber"></x-input>
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
export default {
  name: 'me-info',
  data () {
    return {
      formData: {
        carsId: '',
        carsName: '',
        guidancePrice: '',
        carNumber: ''
      }
    }
  },
  methods: {
    submit() {
      if(!this.formData.carsId) {
        this.$toptip('请选择车型')
        return
      }
      if(!(Number(this.formData.carNumber) > 0)) {
        this.$toptip('请输入车辆数量')
        return
      }
      
      let addcar = this.$storage.session.get('wuliu-freight-addcar') || []
      this.formData.guid = this.$utils.guid()
      addcar.push(this.formData)
      this.$storage.session.set('wuliu-freight-addcar', addcar)
      this.$router.back()
    }
  },
  mounted() {
    let sltedCar = this.$storage.session.get('car_slted')
    if(sltedCar) {
      this.formData.carsId = sltedCar.id
      this.formData.carsName = sltedCar.name
      this.formData.guidancePrice = (sltedCar.price / 10000).toFixed(2)
    }
  },
  beforeDestroy() {
    this.$storage.session.remove('car_slted')
  }
}
</script>
