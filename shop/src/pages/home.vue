<template>
  <view-box>
    <swiper auto :list="bannerSwiper.list" dots-position="center" :show-desc-mask="false"></swiper>
    <div class="l-bg-white l-padding">
      <flexbox>
        <flexbox-item>
          <router-link class="l-link-1" to="/wuliu/list" tag="div">
            <img class="_icon" src="../assets/images/20180402003.png">
            <div class="_txt">
              <p>车物流</p>
              <p class="l-fs-xs l-txt-gray">轻松托运车辆</p>
            </div>
          </router-link>
        </flexbox-item>
        <flexbox-item>
          <router-link tag="div" class="l-link-1" to="/loan">
            <img class="_icon" src="../assets/images/20180402004.png">
            <div class="_txt">
              <p>金融购</p>
              <p class="l-fs-xs l-txt-gray">垫资车贷点这里</p>
            </div>
          </router-link>
        </flexbox-item>
        <flexbox-item>
          <router-link tag="div" class="l-link-1" to="/car/seek">
            <img class="_icon" src="../assets/images/20180402005.png">
            <div class="_txt">
              <p>要寻车</p>
              <p class="l-fs-xs l-txt-gray">找到心仪车辆</p>
            </div>
          </router-link>
        </flexbox-item>
      </flexbox>
    </div>

    <!-- 品牌 -->
    <div class="l-bg-white l-padding l-margin-tb">
      <flexbox>
        <flexbox-item>
          <router-link to="/goods/list?bid=1"><img class="l-link-2" src="../assets/images/20180402006.png"></router-link>
        </flexbox-item>
        <flexbox-item>
          <router-link to="/goods/list?bid=2"><img class="l-link-2" src="../assets/images/20180402008.png"></router-link>
        </flexbox-item>
        <flexbox-item>
          <router-link to="/goods/list?bid=3"><img class="l-link-2" src="../assets/images/20180402007.png"></router-link>
        </flexbox-item>
      </flexbox>
      <div class="l-margin-t">
        <x-button :plain="true" type="primary" link="/goods/list">查看全部品牌 »</x-button>
      </div>
    </div>

    <!-- 活动热销专区 -->
    <div class="l-bg-white l-zoom l-margin-tb">
      <img class="l-img-block" src="../assets/images/20180402009.jpg" alt="活动热销专区">
      <div class="l-padding-lr">
        <router-link tag="div" class="l-flex-h l-list-1 vux-1px-b" :to="'/goods/info?id=' + item.goodsCarsActivityId" v-for="item in goodsActive" :key="item.goodsCarsActivityId">
          <div class="_thumb l-bg-co l-margin-r-m" :style="{'background-image':'url('+item.thumb+')'}"></div>
          <div class="l-rest">
            <h5 class="l-txt-wrap1">
              {{item.carsName}}
            </h5>
            <div class="l-fs-s l-txt-gray">
              <p>
                <span class="_tag1">车身：{{item.colourName}}</span>
                <span class="_tag1">内饰：{{item.interiorName}}</span>
              </p>
              <p>
                <span class="_tag2 l-fr" v-if="item.overInsurance">含强交险</span>
                <span class="l-txt-theme l-fs-l l-margin-r-m l-rmb">{{item.saleingPriceStr}}万</span>
                <span>指导价：<i class="l-rmb">{{item.guidingPriceStr}}</i>万</span>
              </p>
              <p>
                <span class="l-fr">库存：{{item.saleingNumber}}</span>
                <span>{{item.orgName}}</span>
              </p>
              <p v-if="item.discountPriceStr" :class="item.discountPriceOnLine ? '_jia' : '_jian'">{{item.discountPriceStr}}</p>
            </div>
          </div>
        </router-link>
      </div>
      <div class="l-margin">
        <x-button :plain="true" type="primary" link="/goods/list?isActive=1">查看全部活动 »</x-button>
      </div>
    </div>

    <!-- 新品专区 -->
    <div class="l-bg-white l-zoom l-margin-tb">
      <img class="l-img-block" src="../assets/images/20180402010.jpg" alt="新品专区">
      <div class="l-padding-lr">
        <router-link tag="div" class="l-flex-h l-list-1 vux-1px-b" :to="'/goods/info?id=' + item.goodsCarsId" v-for="item in goodsNew" :key="item.goodsCarsId">
          <div class="_thumb l-bg-co l-margin-r-m" :style="{'background-image':'url('+item.thumb+')'}"></div>
          <div class="l-rest">
            <h5 class="l-txt-wrap1">
              <span class="_tag0">新品</span>
              {{item.carsName}}
            </h5>
            <div class="l-fs-s l-txt-gray">
              <p>
                <span class="_tag1">车身：{{item.colourName}}</span>
                <span class="_tag1">内饰：{{item.interiorName}}</span>
              </p>
              <p>
                <span class="_tag2 l-fr" v-if="item.overInsurance">含强交险</span>
                <span class="l-txt-theme l-fs-l l-margin-r-m l-rmb">{{item.saleingPriceStr}}万</span>
                <span>指导价：<i class="l-rmb">{{item.guidingPriceStr}}</i>万</span>
              </p>
              <p>
                <span class="l-fr">库存：{{item.saleingNumber}}</span>
                <span>{{item.orgName}}</span>
              </p>
              <p v-if="item.discountPriceStr" :class="item.discountPriceOnLine ? '_jia' : '_jian'">{{item.discountPriceStr}}</p>
            </div>
          </div>
        </router-link>
      </div>
      <div class="l-margin">
        <x-button :plain="true" type="primary" link="/goods/list?isNew=1">查看全部新品 »</x-button>
      </div>
    </div>

  </view-box>
</template>

<script>
import { Swiper, Flexbox, FlexboxItem } from 'vux'
export default {
  name: 'home',
  components: {
    Swiper, Flexbox, FlexboxItem, 
  },
  data () {
    return {
      bannerSwiper: {
        list: [{ 
          url: 'javascript:', 
          img: require('../assets/images/20180402001.jpg'), 
          title: '' 
        }, { 
          url: 'javascript:', 
          img: require('../assets/images/20180402002.jpg'), 
          title: ''
        }]
      },
      goodsActive: [],
      goodsNew: [],
    }
  },
  methods: {
    getGoodsActive() {
      this.$api.goods.getActiveList({}, 1, 5).then(({data}) => {
        this.goodsActive = data.list.map(item => {
          item.thumb = this.$utils.imgThumb(item.image, 100, 100) || this.$config.thumb1
          item.guidingPriceStr = (item.guidingPrice / 10000).toFixed(2)
          item.discountPriceOnLine = item.discountPriceOnLine || 0
          item.saleingPriceStr = (item.activityPrice / 10000).toFixed(2)
          if(item.discountPriceOnLine === 0) {
            item.discountPriceStr = ''
          }else {
            item.discountPriceStr = (item.discountPriceOnLine > 0 ? '加价 ' : '降价 ')
            if(Math.abs(item.discountPriceOnLine) >= 10000) {
              item.discountPriceStr += (item.discountPriceOnLine / 10000).toFixed(2) + '万元'
            }else {
              item.discountPriceStr += item.discountPriceOnLine+ '元'
            }
          }
          return item
        })
      })
    },
    getGoodsNew() {
      this.$api.goods.getList({isNew: 1}, 1, 5).then(({data}) => {
        this.goodsNew = data.list.map(item => {
          item.thumb = this.$utils.imgThumb(item.image, 100, 100) || this.$config.thumb1
          item.guidingPriceStr = (item.guidingPrice / 10000).toFixed(2)
          item.discountPriceOnLine = item.discountPriceOnLine || 0
          item.saleingPriceStr = (item.bareCarPriceOnLine / 10000).toFixed(2)
          if(item.discountPriceOnLine === 0) {
            item.discountPriceStr = ''
          }else {
            item.discountPriceStr = (item.discountPriceOnLine > 0 ? '加价 ' : '降价 ')
            if(Math.abs(item.discountPriceOnLine) >= 10000) {
              item.discountPriceStr += (item.discountPriceOnLine / 10000).toFixed(2) + '万元'
            }else {
              item.discountPriceStr += item.discountPriceOnLine+ '元'
            }
          }
          return item
        })
      })
    }
  },
  mounted() {
    setTimeout(() => {
      this.getGoodsActive()
      this.getGoodsNew()
    }, 500)
  },
  beforeDestroy() {
    this.$api.abort()
  }
}
</script>

<style lang="less">

</style>

