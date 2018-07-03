<template>
  <view-box no-prevent-bounce>
    <div class="l-tab">
      <tab :scroll-threshold="5" :line-width="1">
        <tab-item v-for="item in tab.data" :key="item.value" :selected="tab.active === item.value" @click.native="tabClick(item.value)">{{item.label}}</tab-item>
      </tab>
    </div>
    <div class="_tab-placeholder"></div>

    <div class="l-search-placeholder">
      <search @on-submit="onSearch" @on-cancel="onSearch" v-model="list.filter.keywords" :auto-fixed="false" placeholder="输入垫资单号或车型查找"></search>
    </div>

    <router-link tag="div" :to="'/loan/info2?id=' + item.id" class="l-loan-item  l-fs-m" v-for="item in list.data" :key="item.id">
      <div class="_hd l-flex-hc l-is-link">
        <div class="l-rest">{{item.orderId}}</div>
        <span v-if="item.state == 4 || item.state == 5" class="l-fr l-txt-error l-fs-m">
          <icon class="l-fs-m _icon" type="warn"></icon>{{item.stateName}}
        </span>
        <span v-else class="l-fr l-txt-theme l-fs-m">{{item.stateName}}</span>
      </div>
      <div class="_bd l-flex-hc" v-for="carItem in item.info" :key="carItem.id">
        <img class="_thumb" :src="carItem.thumb" alt="">
        <div class="l-rest">
          <p>{{carItem.carName}}</p>
          <p class="l-txt-gray l-fs-m">
            <span class="l-margin-r-s">垫资数量：{{carItem.number}}</span>
            <span>垫资金额：<i class="l-rmb">{{carItem.amount}}</i></span>
          </p>
        </div>
      </div>
      <div class="l-margin-t-m l-flex-hc">
        <div class="l-rest">
          <span class="l-fs-m l-txt-gray">申请日期 {{item.createTime}}</span>
        </div>
        <div v-if="[0, 1, 2].includes(item.state)">垫资本金总额：<span class="l-txt-theme l-rmb">{{item.amount}}</span></div>
        <div v-else-if="[3, 4, 5, 6].includes(item.state)">待还总额：<span class="l-txt-theme l-rmb">{{item.unpayAmountTotal}}</span></div>
        <div v-else-if="item.state == 7">还款总额：<span class="l-txt-theme l-rmb">{{item.totalAmount}}</span></div>
      </div>
      <div v-if="item.state == 0" class="_ft vux-1px-t l-txt-right" @click.stop>
        <span class="l-margin-l-s"></span>
        <x-button round mini plain @click.native="cancel(item.id)">取消</x-button>
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
import { Search, Tab, TabItem, Sticky } from 'vux'
import infiniteLoading from '../components/vue-infinite-loading'

export default {
  name: 'loan-list',
  components: {
    infiniteLoading, Search, Tab, TabItem, Sticky
  },
  data () {
    return {
      userInfo: null,
      state: ['初审通过', '已通过', '已拒绝'],
      tab: {
        data: [
          { label: '全部', value: '' },
          { label: '审核中', value: 0 },
          { label: '待放款', value: 2 },
          { label: '已放款', value: 3 },
          { label: '请还款', value: 4 },
          { label: '已逾期', value: 5 },
          { label: '已拒绝', value: 1 },
          { label: '已移交处理', value: 6 },
          { label: '已还清', value: 7 },
        ],
        active: ''
      },
      list: {
        filter: {
          keywords: '',
          state: ''
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
    tabClick(value = '') {
      this.tab.active = value
      this.list.filter.state = value
      this.resetInfinite()
    },
    onSearch() {
      this.resetInfinite()
    },
    resetInfinite() {
      this.$refs.infinite.$emit('$InfiniteLoading:reset', false)
      this.onInfinite(1)
      
    },
    onInfinite(page) {
      this.$api.loan.getList2(this.list.filter, page || this.list.page).then(({data}) => {
        let returnList = data.list.map(item => {
          item.info.forEach(carItem => {
            carItem.thumb = this.$utils.imgThumb(carItem.carImage, 100, 100) || this.$config.thumb1  
          })
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
    },
    cancel(id = '') {
      this.$vux.confirm.show({
        title: '系统提示',
        content: '是否确定删除该垫资申请？',
        onConfirm: _ => {
          this.$vux.loading.show()
          this.$api.loan.cancel(id).then(_ => {
            this.resetInfinite()
          }).finally(_ => {
            this.$vux.loading.hide()
          })
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
.l-tab{position: fixed; left: 0; right: 0; top: 0; z-index: 1;}
._tab-placeholder{height: 44px;}
.l-loan-item{
  background-color: #fff; padding: 10px; margin: 10px 0;
  ._hd ._icon{vertical-align: -2px;}
  ._thumb{width: 50px; height: 50px; border-radius: 5px; margin-right: 10px; background-color: #fff;}
  ._bd{ background-color:#f4fafa; padding: 10px; margin-top: 10px; }
  ._ft{ padding: 10px 0 0 0;}
}
.l-fixed-rbbtn{
  position: fixed; bottom: 30px; right: 30px;
  color: #36cdc6; text-align: center; font-size: 12px;
  img{width: 40px; height: 40px; display: block; margin: auto; border-radius: 50%; box-shadow: 0 0 10px 0px #36cdc6;}
}
</style>
