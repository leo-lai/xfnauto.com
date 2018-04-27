<template>
  <view-box>
    <div class="l-flex-hc l-padding-btn">
      <img class="l-img-icon l-margin-r-m" src="../assets/images/icon-012.png" alt="">
      <h4 class="l-rest">普通物流</h4>
    </div>
    <group v-if="line1" gutter="0" label-width="15em">
      <cell :title="line1.lineName" :inline-desc="'起步价：￥' + line1.startPrice">
        <x-button class="l-btn-radius" mini type="primary" @click.native="searchFreight(line1)">查询运费</x-button>
      </cell>
    </group>

    <div class="l-flex-hc l-padding-btn l-margin-t">
      <img class="l-img-icon l-margin-r-m" src="../assets/images/icon-011.png" alt="">
      <h4 class="l-rest">专线物流</h4>
    </div>
    <group v-if="line2" gutter="0" label-width="15em">
      <cell v-for="item in line2" :key="item.lineId" :title="item.lineName" :inline-desc="'运费：￥' + item.amount + ' / 辆'">
        <x-button class="l-btn-radius" mini type="primary" @click.native="searchFreight(item)">查询运费</x-button>
      </cell>
    </group>
  </view-box>
</template>
<script>
export default {
  name: 'wuliu-list',
  data () {
    return {
      line1: null,
      line2: null
    }
  },
  methods: {
    getList() {
      this.$vux.loading.show()
      this.$api.wuliu.getList().then(({data}) => {
        this.line1 = data.list[0]
        this.line2 = data.list.slice(1, data.list.length)
      }).finally(_ => {})
      this.$vux.loading.hide()
    },
    searchFreight(item) {
      let formData = this.$storage.session.get('wuliu-freight-formdata')
      this.$storage.session.set(
        'wuliu-freight-formdata', 
        Object.assign({}, formData, {
          consignmentType: item.consignmentType,
          consignmentTypeLineId: item.lineId,
          consignmentTypeLineName: item.lineName,
          startingPointAddress: item.startingPointAddress,
          startingPointLatitude: item.startingPointLatitude,
          startingPointLongitude: item.startingPointLongitude,
          destinationAddress: item.destinationAddress,
          destinationLatitude: item.destinationLatitude,
          destinationLongitude: item.destinationLongitude,
        })
      )

      if(formData.consignmentTypeLineId != item.lineId){
        this.$storage.session.remove('wuliu-freight-addcar')
      }
      this.$router.push('/wuliu/freight')
    }
  },
  mounted() {
    this.getList()
  }
}
</script>
<style lang="less">

</style>
