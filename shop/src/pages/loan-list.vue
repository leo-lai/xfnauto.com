<template>
  <view-box>
    <div class="l-search-placeholder">
      <search @on-submit="onSearch" @on-cancel="onSearch" v-model="list.filter.keywords" :auto-fixed="false" placeholder="输入车型查找"></search>
    </div>
    <router-link tag="div" :to="'/loan/info?id=' + item.applyLoanId" class="l-loan-item  l-fs-m" v-for="item in list.data" :key="item.applyLoanId">
      <div class="_hd l-flex-hc l-is-link">
        <div class="l-rest">{{userInfo && userInfo.userType == 2 ? '垫资' : '贷款'}}金额：<i class="l-rmb">{{item.loanAmount}}</i></div>
        <span class="l-txt-theme">{{state[item.loneState]}}</span>
      </div>
      <div class="_bd l-flex-hc">
        <img class="_thumb" :src="item.thumb" alt="">
        <div class="l-rest">
          <p>{{item.carsName}}</p>
          <p class="l-txt-gray l-fs-m">
            <span>指导价：<i class="l-rmb">{{item.guidancePriceStr}}</i>万</span>
          </p>
        </div>
      </div>
      <div class="l-margin-t l-flex-hc">
        <div class="l-rest">
          <span class="l-fs-m l-txt-gray">申请时间：{{item.createTime}}</span>
        </div>
      </div>
    </router-link>
    <infinite-loading :on-infinite="onInfinite" ref="infinite"></infinite-loading>
    <div class="l-fixed-rbbtn" @click="loan">
      <img src="../assets/images/20180402004.png" alt="找资金">
      <p>找资金</p>
    </div>
  </view-box>
</template>
<script>
import { Search } from 'vux'
import infiniteLoading from '../components/vue-infinite-loading'

export default {
  name: 'loan-list',
  components: {
    infiniteLoading, Search
  },
  data () {
    return {
      userInfo: null,
      state: ['初审通过', '已通过', '已拒绝'],
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
    loan() {
      if(!this.userInfo) {
        this.$router.push('/login')
      }else {
        this.$router.push(this.userInfo.userType === 2 ? '/loan/2' : '/loan/1')
      }
    },
    onSearch() {
      this.resetInfinite()
    },
    resetInfinite() {
      this.$refs.infinite.$emit('$InfiniteLoading:reset', false)
      this.onInfinite(1)
    },
    onInfinite(page) {
      this.$api.loan.getList(this.list.filter, page || this.list.page).then(({data}) => {
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
      }).catch(err => {
        if(!err.abort) {
          this.$refs.infinite.$emit('$InfiniteLoading:complete')
        }
      })
    }
  },
  mounted() {
    this.$api.user.getInfo().then(data => {
      this.userInfo = data
    })
  }
}
</script>
<style lang="less" scoped>
.l-loan-item{
  background-color: #fff; padding: 15px; margin-bottom: 15px;
  ._thumb{width: 50px; height: 50px; border-radius: 5px; margin-right: 10px; background-color: #fff;}
  ._bd{ background-color:#f4fafa; padding: 10px; margin-top: 10px; }
}
.l-fixed-rbbtn{
  position: fixed; bottom: 30px; right: 30px;
  color: #36cdc6; text-align: center; font-size: 12px;
  img{width: 40px; height: 40px; display: block; margin: auto; border-radius: 50%; box-shadow: 0 0 10px 0px #36cdc6;}
}
</style>
