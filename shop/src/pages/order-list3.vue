<template>
  <view-box>
    <div class="l-search-placeholder">
      <search @on-submit="onSearch" @on-cancel="onSearch" v-model="list.filter.keywords" :auto-fixed="false" placeholder="输入单号或车型查询"></search>
    </div>
    <div class="l-seek-item l-fs-m" v-for="item in list.data" :key="item.customerOrderId">
      <router-link tag="div" :to="'/order/info3?id=' + item.customerOrderId">
        <div class="_hd l-flex-hc l-is-link">
          <div class="l-rest">订购单号：{{item.customerOrderCode}}</div>
          <div class="l-txt-theme">{{item.customerOrderStateName}}</div>
        </div>
        <div class="_bd l-flex-hc">
          <img class="_thumb" :src="item.thumb" alt="">
          <div class="l-rest">
            <p class="l-txt-wrap1">{{item.carsName}}</p>
            <p class="l-txt-gray l-margin-t-s">
              <span>车身：{{item.colorName}}</span>
              <span class="l-margin-l">内饰：{{item.interiorName}}</span>
            </p>
          </div>
        </div>
      </router-link>
      <div class="_ft l-txt-right l-margin-t">
        <x-button :link="'/order/pay-record?id=' + item.customerOrderId" mini plain type="primary">支付记录</x-button>
      </div>
    </div>
    <infinite-loading :on-infinite="onInfinite" ref="infinite"></infinite-loading>
  </view-box>
</template>
<script>
import { Search } from 'vux'
import infiniteLoading from '../components/vue-infinite-loading'

export default {
  name: 'order-list3',
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
      this.$api.order.getList2(this.list.filter, page || this.list.page).then(({data}) => {
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
.l-seek-item{
  background-color: #fff; padding: 15px; margin-bottom: 15px;
  ._thumb{width: 50px; height: 50px; border-radius: 5px; margin-right: 10px; background-color: #fff;}
  ._bd{ background-color:#f4fafa; padding: 10px; margin-top: 10px; }
}
</style>


