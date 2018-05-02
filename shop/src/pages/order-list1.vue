<template>
  <view-box>
    <div class="l-search-placeholder">
      <search @on-submit="onSearch" @on-cancel="onSearch" v-model="list.filter.keywords" :auto-fixed="false" placeholder="输入单号或车型查询"></search>
    </div>
    <router-link tag="div" :to="'/order/info1?id=' + item.advanceOrderId" class="l-seek-item l-fs-m" v-for="item in list.data" :key="item.advanceOrderId">
      <div class="_hd l-flex-hc l-is-link">
        <div class="l-rest">预约单号：{{item.orderCode}}</div>
        <!-- <div class="l-txt-theme">{{item.overPay ? '已付款' : '未付款'}}</div> -->
      </div>
      <div class="_bd l-flex-hc" v-if="item.carInfo">
        <img class="_thumb" :src="item.carInfo.thumb" alt="">
        <div class="l-rest">
          <p class="l-txt-wrap1">{{item.carInfo.carsName}}</p>
          <p class="l-txt-gray l-margin-t-s">
            <span>车身：{{item.carInfo.colourName}}</span>
            <span class="l-margin-l">内饰：{{item.carInfo.interiorName}}</span>
          </p>
        </div>
      </div>
    </router-link>
    <infinite-loading :on-infinite="onInfinite" ref="infinite"></infinite-loading>
  </view-box>
</template>
<script>
import { Search } from 'vux'
import infiniteLoading from '../components/vue-infinite-loading'

export default {
  name: 'order-list1',
  components: {
    infiniteLoading, Search
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
      this.$api.order.getList1(this.list.filter, page || this.list.page).then(({data}) => {
        let returnList = data.list.map(item => {
          if(item.orderInfoVos && item.orderInfoVos.length > 0) {
            let carInfo = item.orderInfoVos[0]
            carInfo.thumb = this.$utils.imgThumb(carInfo.image, 100, 100) || this.$config.thumb1
            carInfo.guidancePriceStr = (carInfo.guidancePrice / 10000).toFixed(2)
            item.carInfo = carInfo
          }
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
.l-seek-item{
  background-color: #fff; padding: 15px; margin-bottom: 15px;
  ._thumb{width: 50px; height: 50px; border-radius: 5px; margin-right: 10px; background-color: #fff;}
  ._bd{ background-color:#f4fafa; padding: 10px; margin-top: 10px; }
}
</style>


