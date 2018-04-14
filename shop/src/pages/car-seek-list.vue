<template>
  <view-box>
    <div class="l-search-placeholder">
      <search @on-submit="onSearch" @on-cancel="onSearch" v-model="list.filter.keywords" :auto-fixed="false" placeholder="查找寻车记录"></search>
    </div>
    <router-link tag="div" :to="'/car/seek/info?id=' + item.findTheCarId" class="l-seek-item" v-for="item in list.data" :key="item.findTheCarId">
      <div class="_hd l-flex-hc l-is-link">
        <div class="l-rest">期望价：{{item.guidancePriceStr}}万元</div>
        <span class="l-txt-theme">{{item.findCarOffers.length > 0 ? '有报价' : '寻车中'}}</span>
      </div>
      <div class="_bd l-flex-hc">
        <img class="_thumb" :src="item.thumb" alt="">
        <div class="l-rest">
          <p>{{item.carsName}}</p>
          <p class="l-txt-gray l-fs-m">
            <span>车身颜色：{{item.colourName}}</span>
            <span class="l-margin-l">内饰颜色：{{item.interiorName}}</span>
          </p>
        </div>
      </div>
    </router-link>
    <infinite-loading :on-infinite="onInfinite" ref="infinite"></infinite-loading>
    <router-link class="l-fixed-rbbtn" tag="div" to="/car/seek">
      <img src="../assets/images/20180402005.png" alt="要寻车">
      <p>要寻车</p>
    </router-link>
  </view-box>
</template>
<script>
import { Search } from 'vux'
import infiniteLoading from '../components/vue-infinite-loading'

export default {
  name: 'car-seek-list',
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
      this.$api.seek.getList(this.list.filter, page || this.list.page).then(({data}) => {
        let returnList = data.list.map(item => {
          item.thumb = this.$utils.imgThumb(item.image, 100, 100) || this.$config.thumb1
          item.guidancePriceStr = (item.guidancePrice / 10000).toFixed(2)
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
      }).catch(_ => {
        this.$refs.infinite.$emit('$InfiniteLoading:complete')
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
.l-fixed-rbbtn{
  position: fixed; bottom: 30px; right: 30px;
  color: #fda253; text-align: center; font-size: 12px;
  img{width: 40px; height: 40px; display: block; margin: auto; border-radius: 50%; box-shadow: 0 0 10px 0px #fda253;}
}
</style>


