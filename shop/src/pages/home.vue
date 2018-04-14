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
        <div class="l-flex-hc l-list-1 vux-1px-b" v-for="item in goodsActive" :key="item">
          <div class="_thumb l-bg-co l-margin-r-m" style="background-image:url('https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1387625928,2358473142&fm=27&gp=0.jpg')"></div>
          <div class="l-rest">
            <h5 class="l-txt-wrap1">本田啊打发的骄傲的法拉利大撒反对发本田啊打发的骄傲的法拉利大撒反对发</h5>
            <div class="l-fs-s l-txt-gray">
              <p>
                <span class="_tag1">车身：白色</span>
                <span class="_tag1">内饰：白色</span>
              </p>
              <p>
                <span class="_tag2 l-fr">含强交险</span>
                <i class="l-txt-theme l-fs-xs">￥</i><span class="l-txt-theme l-fs-l l-margin-r-m">18.5万</span>
                <span class="l-txt-del">指导价：￥50万</span>
              </p>
              <p>
                <span class="l-fr">库存：100</span>
                <span>江门鹤山店</span>
              </p>
            </div>
          </div>
        </div>
      </div>
      <div class="l-margin">
        <x-button :plain="true" type="primary" link="/goods/list?isActive=1">查看全部活动 »</x-button>
      </div>
    </div>

    <!-- 新品专区 -->
    <div class="l-bg-white l-zoom l-margin-tb">
      <img class="l-img-block" src="../assets/images/20180402010.jpg" alt="新品专区">
      <div class="l-padding-lr">
        <div class="l-flex-hc l-list-1 vux-1px-b" v-for="item in goodsNew" :key="item">
          <div class="_thumb l-bg-co l-margin-r-m" style="background-image:url('https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1387625928,2358473142&fm=27&gp=0.jpg')"></div>
          <div class="l-rest">
            <h5 class="l-txt-wrap1">
              <span class="_tag0">新品</span>
              本田啊打发的骄傲的法拉利大撒反对发本田啊打发的骄傲的法拉利大撒反对发
            </h5>
            <div class="l-fs-s l-txt-gray">
              <p>
                <span class="_tag1">车身：白色</span>
                <span class="_tag1">内饰：白色</span>
              </p>
              <p>
                <span class="_tag2 l-fr">含强交险</span>
                <i class="l-txt-theme l-fs-xs">￥</i><span class="l-txt-theme l-fs-l l-margin-r-m">18.5万</span>
                <span class="l-txt-del">指导价：￥50万</span>
              </p>
              <p>
                <span class="l-fr">库存：100</span>
                <span>江门鹤山店</span>
              </p>
            </div>
          </div>
        </div>
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
        this.goodsActive = data.list
      })
    },
    getGoodsNew() {
      this.$api.goods.getList({isNew: 1}, 1, 5).then(({data}) => {
        this.goodsNew = data.list
      })
    }
  },
  mounted() {
    setTimeout(() => {
      this.getGoodsActive()
      // this.getGoodsNew()
    }, 500)
  }
}
</script>

<style lang="less">

</style>

