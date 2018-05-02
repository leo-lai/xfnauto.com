<template>
  <view-box style="background-color:#e1e8ee;">
    <div class="l-pay-record" v-for="item in list.data" :key="item.orderInPayId">
      <div class="_hd l-flex-hc">
        <h4 class="l-rest">电子支付凭证</h4>
        <p class="l-fs-s l-txt-gray">{{item.payDate}}</p>
      </div>
      <div class="_bd">
        <p class="l-fs-m">支付金额：</p>
        <p class="l-txt-theme l-fs-l"><i class="l-rmb">{{item.amount}}</i></p>

        <p class="l-fs-m l-margin-t">支付方式：</p>
        <p class="l-txt-theme l-fs-l">{{item.payMethodName}}</p>
      </div>
      <div class="_line1"></div>
      <div class="_ft l-fs-m">
        <p>收款人员：{{item.systemUserName}}</p>
        <p>收款门店：{{item.orgName}}</p>
      </div>
      <div class="_line2"></div>
    </div>
    <infinite-loading :on-infinite="onInfinite" ref="infinite"></infinite-loading>
  </view-box>
</template>
<script>
import infiniteLoading from '../components/vue-infinite-loading'

export default {
  name: 'order-pay-record',
  components: {
    infiniteLoading
  },
  data () {
    return {
      list: {
        filter: {
          keywords: ''
        },
        page: 1,
        data: []
      }
    }
  },
  methods: {
    onSearch() {
      this.resetInfinite()
    },
    resetInfinite() {
      this.$refs.infinite.$emit('$InfiniteLoading:reset', false)
      this.onInfinite(1)
    },
    onInfinite(page) {
      this.$api.order.getPayRecord({
        customerOrderId: this.$route.query.id
      }, page || this.list.page).then(({data}) => {
        let returnList = data.list.map(item => {
          item.thumb = this.$utils.imgThumb(item.carsIndexImage, 100, 100) || this.$config.thumb1
          return item
        })
        
        this.list.data = data.page > 1 ? this.list.data.concat(returnList) : returnList

        if(returnList.length > 0){
          this.$nextTick(()=>{
            this.$refs.infinite.$emit('$InfiniteLoading:loaded')
          })
          
          if(returnList.length >= data.rows){
            this.list.page++
          }else{
            this.$refs.infinite.$emit('$InfiniteLoading:complete')
          }
        }else{
          this.$refs.infinite.$emit('$InfiniteLoading:complete')
        }
      }).catch(err => {
        if(!err.abort) {
          this.$refs.infinite.$emit('$InfiniteLoading:complete')
        }
      })
    }
  }
}
</script>
<style lang="less" scoped>
.l-pay-record{
  margin: 15px; padding: 0 20px; overflow: hidden;
  background-color: #fff; border-radius: 5px 5px 15px 15px;
  ._hd{padding: 15px 10px; border-bottom: 1px dashed #ccc;}
  ._bd{padding: 15px 10px;}
  ._ft{padding: 15px 10px;}
  ._line1{
    position: relative; border-top: 1px dashed #ccc;
    &::before, &::after{content: ''; position: absolute; width: 15px; height: 15px; border-radius: 50%; top: -8px; background-color: #e1e8ee;}
    &::before{ left: -28px;}
    &::after{ right: -28px;}
  }
  ._line2{
    height: 15px; margin:0 -20px; position: relative; top: 8px;
    background: #D24161;background: radial-gradient(#e1e8ee 8px, transparent 5px) repeat-x; background-size: 16px 15px;
  }
}
</style>


