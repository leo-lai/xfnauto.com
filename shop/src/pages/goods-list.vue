<template>
  <view-box no-prevent-bounce>
    <div class="l-search-placeholder">
      <search @on-submit="onSearch" @on-cancel="onSearch" @on-focus="hideSearchFilter" v-model="list.filter.keywords" :auto-fixed="false" placeholder="查找车辆"></search>
    </div>
    <div class="l-search-filter">
      <div class="_fixed" :class="{'_active': tabIndex > -1}" @click="hideSearchFilter">
        <div class="l-flex-hvc _tab-box" @click.stop>
          <div class="_tab" :class="{'_active': tabIndex == 1}" @click="tabClick(1)">
            <span>{{brandName}}</span>
          </div>
          <div class="_l vux-1px-l"></div>
          <div class="_tab" :class="{'_active': tabIndex == 2}" @click="tabClick(2)">
            <span>{{cityName}}</span>
          </div>
        </div>
        <div class="_cont l-scroll" @click.stop>
          <checker @on-change="searchFilter" v-show="tabIndex == 1" class="l-search-brand" v-model="list.filter.brandId" default-item-class="l-checker-item" selected-item-class="l-checker-item-slted">
            <checker-item v-for="item in brandList" :key="item.brandId" :value="item.brandId">
              <i :style="{'background-image': 'url('+ item.brandImage +')'}"></i>
              {{item.brandName}}
            </checker-item>
          </checker>
          <checker @on-change="searchFilter" v-show="tabIndex == 2" class="l-search-brand" v-model="list.filter.cityId" default-item-class="l-checker-item" selected-item-class="l-checker-item-slted">
            <checker-item v-for="item in cityList" :key="item.regionId" :value="item.regionId">
              {{item.regionName}}
            </checker-item>
          </checker>
        </div>
      </div>
    </div>
    <!-- 产品列表 -->
    <div class="l-bg-white l-padding-lr">
      <router-link tag="div" :to="'/goods/info?id=' + item.goodsCarsId + '&isActive=' + isActive" class="l-flex-h l-list-1" :class="{'vux-1px-t': index > 0}" v-for="(item,index) in list.data" :key="item.goodsCarsId">
        <div class="_thumb l-bg-co l-margin-r-m" :style="{'background-image': 'url(' + item.thumb + ')'}"></div>
        <div class="l-rest">
          <h5 class="l-txt-wrap1">{{item.carsName}}</h5>
          <div class="l-fs-s l-txt-gray">
            <p>
              <span class="_tag1">车身：{{item.colourName}}</span>
              <span class="_tag1">内饰：{{item.interiorName}}</span>
            </p>
            <p>
              <span class="_tag2 l-fr" v-if="item.overInsurance">带交强险</span>
              <span class="l-txt-theme l-fs-l l-margin-r-m l-rmb">{{item.saleingPriceStr}}万</span>
              <span>指导价：<i class="l-rmb">{{item.guidingPriceStr}}</i>万</span>
            </p>
            <p>
              <span class="l-fr">{{item.goodsCarsActivityId ? '活动数量：' : '库存：'}}{{item.saleingNumber}}</span>
              <span>{{item.orgName}}</span>
            </p>
            <p v-if="item.discountPriceStr" :class="item.discountPriceOnLine > 0 ? '_jia' : '_jian'">{{item.discountPriceStr}}</p>
          </div>
        </div>
      </router-link>
    </div>
    <infinite-loading ref="infinite" :on-infinite="onInfinite"></infinite-loading>
  </view-box>
</template>

<script>
import { Search, Checker, CheckerItem } from 'vux'
import infiniteLoading from '../components/vue-infinite-loading'

export default {
  name: 'goods-list',
  components: {
    infiniteLoading, Search, Checker, CheckerItem
  },
  data () {
    return {
      isActive: 0,
      tabIndex: -1,
      brandList: [],
      cityList: [],
      list: {
        filter: {
          isNew: 0,
          brandId: '',
          cityId: '',
          keywords: ''
        },
        page: 1,
        data: []
      }
    }
  },
  computed: {
    brandName() {
      return (this.brandList.filter(item => item.brandId == this.list.filter.brandId)[0] || {}).brandName || '品牌'
    },
    cityName() {
      return (this.cityList.filter(item => item.regionId == this.list.filter.cityId)[0] || {}).regionName || '区域'
    }
  },
  methods: {
    onSearch() {
      this.list.filter.brandId = ''
      this.list.filter.cityId = ''
      this.resetInfinite()
    },
    hideSearchFilter() {
      this.tabIndex = -1
    },
    searchFilter(value) {
      this.tabIndex = -1
      this.resetInfinite()
    },
    resetInfinite() {
      this.$refs.infinite.$emit('$InfiniteLoading:reset', false)
      this.onInfinite(1)
    },
    onInfinite(page) {
      let promise = null
      if(this.isActive) {
        promise = this.$api.goods.getActiveList(this.list.filter, page || this.list.page)
      }else{
        promise = this.$api.goods.getList(this.list.filter, page || this.list.page)
      }
      
      promise.then(({data}) => {
        let returnList = data.list.map(item => {
          item.thumb = this.$utils.imgThumb(item.image, 100, 100) || this.$config.thumb1
          item.guidingPriceStr = (item.guidingPrice / 10000).toFixed(2)
          
          if(item.goodsCarsActivityId) {
            item.goodsCarsId = item.goodsCarsActivityId
            item.saleingPriceStr = (item.activityPrice / 10000).toFixed(2)
          }else {
            item.saleingPriceStr = (item.bareCarPriceOnLine / 10000).toFixed(2)
          }

          if(!item.discountPriceOnLine) {
            item.discountPriceStr = ''
          }else {
            let discountPriceOnLine = Math.abs(item.discountPriceOnLine)
            item.discountPriceStr = (item.discountPriceOnLine > 0 ? '加价 ' : '降价 ')
            if(discountPriceOnLine >= 10000) {
              item.discountPriceStr += (discountPriceOnLine / 10000).toFixed(2) + '万元'
            }else {
              item.discountPriceStr += discountPriceOnLine+ '元'
            }
          }
          return item
        })
        this.list.data = data.page > 1 ? this.list.data.concat(returnList) : returnList

        if(returnList.length > 0){
          this.$refs.infinite.$emit('$InfiniteLoading:loaded')
          
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
    tabClick(index = 1) {
      if(this.tabIndex != index) {
        this.tabIndex = index
      }else {
        this.tabIndex = -1
      }
    },
    getBrandList() {
      this.$vux.loading.show()
      return this.$api.goods.getBrandList().then(({data}) => {
        this.brandList = data
        
      }).finally(_ => {
        this.$vux.loading.hide()
      })
    },
    getCityList() {
      this.$api.goods.getCityList().then(({data}) => {
        this.cityList = data
      })
    }
  },
  created() {
    this.isActive = this.$route.query.isActive == 1 ? 1 : 0
    this.list.filter.isNew = this.$route.query.isNew == 1 ? 1 : 0
    this.list.filter.brandId = this.$route.query.bid || ''
  },
  mounted() {
    setTimeout(() => {
      this.getBrandList()
      this.getCityList()  
    }, 500)
  },
  beforeDestroy() {
    this.$api.abort()
  }
}
</script>

<style lang="less">
.l-search-brand{
  padding: 15px; display: flex; flex-wrap: wrap; justify-content: space-between;
  .l-checker-item{
    width: 80px; text-align: center;
    i{display: block; width: 50px; height: 50px;  margin: auto; background: no-repeat 50% 50%; background-size: contain;}
  }
}
</style>
<style lang="less" scoped>
.l-search-filter{
  &::before{content: ''; display: block; height: 52px;}
  ._fixed{
    position: fixed; top: 43px; left: 0; width: 100%; z-index: 10; padding-top: 53px;
    &::before{
      content: ''; position: absolute; top:0; left: 0; right: 0; bottom: 0; 
      background-color: rgba(0, 0, 0, 0.6); z-index: -1; visibility: hidden; opacity: 0; pointer-events: none; transition: 0.5s all;
    }
    &._active{bottom: 0;}
    &._active::before{visibility: visible; opacity: 1; pointer-events: auto;}
  }
  ._tab-box{
    position: absolute; top:0; left: 0; width: 100%; box-sizing: border-box; z-index: 1;
    padding: 10px 10px 0 10px; border-bottom: 1px solid #eee; background-color: #fff;
  }
  ._tab{ 
    width: 50%;  box-sizing: border-box; height: 42px; transform: translateY(1px); border: 1px solid transparent;
    span{ display: block; background-color: #f1f5f8; padding: 5px; text-align: center; margin-bottom: 10px; font-size: 14px;}
    span::after{
      content: ''; border: 5px solid transparent; border-top-color: inherit; height: 0; font-size: 0; 
      margin-left: 5px; display: inline-block; vertical-align: -3px;
    }
  }
  ._active._tab{
    border-color: #eee; border-bottom-color: #fff; 
    span{background-color: transparent; color: rgb(235, 97, 0);}
    span::after{transform: rotate(180deg); vertical-align: 3px;}
  }
  ._l{margin: 0 10px 10px 10px; height: 20px;}
  ._cont{background-color: #fff; max-height: 100%;}
}
</style>

