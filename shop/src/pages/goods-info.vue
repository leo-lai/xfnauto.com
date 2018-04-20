<template>
  <view-box>
    <div class="l-goods-info" v-if="info">
      <swiper auto :list="bannerSwiper.list" dots-position="center" :show-desc-mask="false"></swiper>
      <div class="l-padding-btn l-bg-white">
        <h4>
          <!-- <span class="_tag0">新品</span> -->
          {{info.carsName}}
        </h4>
        <p>
          <span class="_tag1">车身颜色：{{info.colourName}}</span>
          <span class="_tag1">内饰颜色：{{info.interiorName}}</span>
        </p>
        <p class="l-margin-t-s">
          <span class="_tag2 l-fr" v-if="info.overInsurance">含强交险</span>
          <span class="l-txt-theme">活动价：<i class="l-rmb">18.5</i>万</span>
          <span class="l-txt-gray l-fs-s l-margin-l">指导价：<i class="l-rmb">18.5</i>万</span>
        </p>
        <p class="l-margin-t l-fs-s">
          <span class="l-txt-icon l-fr" border style="margin-top:-4px;">
            <img src="../assets/images/icon-027.png" alt="">买车计算器
          </span>
          <span class="l-txt-gray">仓库：{{info.warehouseName || '--'}}</span>
          <span class="l-txt-gray l-margin-l">库存：{{info.saleingNumber || '--'}} 辆</span>
        </p>
      </div>

      <div class="l-padding-btn l-bg-white l-flex-h l-margin-tb">
        <div class="l-thumb1 l-margin-r" radius :style="{'background-image': 'url('+ info.organization.imageUrl +')'}"></div>
        <div class="l-rest">
          <p>客户经理：{{info.organization.serviceName || '--'}}</p>
          <p>{{info.organization.servicePhone}}</p>
        </div>
        <div>
          <a :href="'tel:'+ info.organization.servicePhone" class="l-txt-icon" border>
            <img src="../assets/images/icon-017.png" alt="">联系他
          </a>
        </div>
      </div>

      <div class="l-bg-white l-margin-tb">
        <div class="l-flex-hc l-padding-btn vux-1px-b">
          <img class="l-img-icon l-margin-r-m" src="../assets/images/icon-013.png" alt="">
          <h4 class="l-rest">车辆信息</h4>
        </div>
        <div class="l-table-info l-fs-m">
          <table>
            <tr>
              <td class="_tit">所属区域：</td>
              <td class="l-txt-right">{{info.organization.cityName || '--'}}</td>
            </tr>
            <tr>
              <td class="_tit">生产日期：</td>
              <td class="l-txt-right">{{info.dateOfManufacture || '--'}}</td>
            </tr>
            <tr>
              <td class="_tit">开票价格：</td>
              <td class="l-txt-right"><span class="l-rmb">{{info.invoicePrice || '--'}}</span></td>
            </tr>
            <tr>
              <td class="_tit">开票周期：</td>
              <td class="l-txt-right">{{info.invoiceCycle || '--'}}</td>
            </tr>
            <tr>
              <td class="_tit">备注信息：</td>
              <td class="l-txt-right">{{info.remarks || '--'}}</td>
            </tr>
            <!-- <tr>
              <td class="_tit">活动描述：</td>
              <td class="l-txt-right"></td>
            </tr> -->
          </table>
        </div>
        
        <img class="l-img-block" :src="item" alt="" v-for="item in info.carsImageArr" :key="item">
      </div>

      <div class="l-goods-placeholder"></div>
      <div class="l-goods-fixed">
        <div class="l-flex-hc l-bg-white">
          <a :href="'tel:'+ info.organization.servicePhone" class="l-txt-icon l-margin-l-s">
            <img src="../assets/images/icon-017.png" alt="">电话咨询
          </a>
          <div class="l-rest"></div>
          <router-link class="_btn2" to="/car/seek">
            <img src="../assets/images/icon-025.png" alt="">我要寻车
          </router-link>
          <div class="_btn1" @click="gotoOrder">
            <img src="../assets/images/icon-026.png" alt="">预约下单
          </div>
        </div>

        <!-- 买车计算器 -->
        
      </div>
    </div>
  </view-box>
</template>

<script>
import { Swiper } from 'vux'
export default {
  name: 'goods-info',
  components: { Swiper },
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
      info: null
    }
  },
  methods: {
    getInfo() {
      this.$vux.loading.show()
      this.$api.goods.getInfo(this.$route.query.id).then(({data}) => {
        data.thumb = this.$utils.imgThumb(data.image, 100, 100) || this.$config.thumb1
        data.carsImageArr = data.carsImage ? data.carsImage.split(',') : []
        this.info = data
      }).finally(_ => {
        this.$vux.loading.hide()
      })
    },
    gotoOrder() {
      this.$storage.session.set('goods-info', this.info)
      this.$router.push('/goods/order')
    }
  },
  mounted() {
    this.getInfo()
  }
}
</script>

<style lang="less" scoped>
.l-goods-placeholder{height: 46px;}
.l-goods-fixed{
  position: fixed; left: 0; right: 0; bottom: 0; z-index: 10; overflow: hidden;
  ._btn1{
    font-size: 14px; padding: 12px 15px; background-image: linear-gradient(135deg, #fa8734, #eb6100); color: #fff;
    img{width: 20px; height: 20px; vertical-align: -4px; margin-right: 5px;}
  }
  ._btn2{
    font-size: 14px; padding: 12px 15px; background-color: #eef3f6; color: inherit;
    img{width: 20px; height: 20px; vertical-align: -4px; margin-right: 5px;}
  }

}
.l-txt-icon{
  font-size: 12px; padding: 2px 5px; color: inherit; text-decoration: underline;
  img{width: 14px; height: 14px; vertical-align: -2px; margin-right: 4px;}
  &[border]{border: 1px solid #eee; border-radius: 5px;}
}
.l-goods-info{
  ._tag0 {
    display: inline-block;
    background-color: #fd8842;
    color: #fff;
    border-radius: 2px;
    padding: 0 5px;
    font-size: 10px;
    vertical-align: middle;
  }
  ._tag1 {
    display: inline-block;
    border-radius: 10px;
    background-color: #e2f3ff;
    padding: 1px 10px;
    margin-right: 5px;
    font-size: 11px;
    color: #999;
  }
  ._tag2 {
    border-radius: 5px;
    border: 1px solid rgb(235, 97, 0);
    color: rgb(235, 97, 0);
    padding: 1px 5px;
    font-size: 10px;
    margin-top: 3px;
  }
}
</style>

