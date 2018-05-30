<template>
  <view-box class="l-bg-white">
    <swiper auto :list="bannerSwiper.list" dots-position="center" :show-desc-mask="false"></swiper>
    <br>
    <div class="l-img-title">
      <img src="../assets/images/20180529002.jpg" alt="">
    </div>
    <div class="l-margin l-txt-justify">
      <p>广州喜蜂鸟网络科技服务有限公司成立于2017年，隶属于传创投资控股集团旗下品牌。是一个专注于汽车产业互联网技术开发、新零售连锁品牌运营管理、人工智能产业技术研发与投资、汽车供应链服务平台开发与运营的创新型科技服务公司。</p>
      <p class="l-margin-t">公司打造以线上商城、资源、物流、SaaS系统、整店输出、售后服务六位一体的一站式标准化...</p>
      <br>
      <p class="l-margin-t l-txt-center">
        <router-link to="/about" class="l-btn-1" round mini>了解更多 >></router-link>
      </p>
    </div>
    <br>
    <div class="l-img-title" style="background-color:#f8f8f8; padding: 20px;">
      <img src="../assets/images/20180529003.jpg" alt="">
    </div>
    <div class="l-flex-hc">
      <div class="l-rest l-diyun">
        <div class="l-txt-center">
          <img src="../assets/images/20180529010.png" alt="">
          <span>底蕴+创新</span>
        </div>
        <p class="l-txt-justify">打破传统汽车行业销售模式，实行O2O立体营销模式，线上系统广布营销宣传矩阵，融合集团多年行业经验，让喜蜂鸟淘车网声名通达，让财富与自己主动对话。</p>
      </div>
      <div class="l-rest l-zhuda">
        <div class="l-txt-center">
          <img src="../assets/images/20180529011.png" alt="">
          <span>主打+综合</span>
        </div>
        <p class="l-txt-justify">线下线上带动真实可判的消费。还免费赠送PC商城及移动端销售平台，零投入生产线上增值，复合化综合渠道盈利增收。</p>
      </div>
    </div>
    <br><br>
    <div class="l-img-title">
      <img src="../assets/images/20180529004.jpg" alt="">
    </div>
    <div class="l-operate-list">
      <div class="_item l-flex-vhc _even">
        <img src="../assets/images/20180527009.png" alt="">
        <p class="_t">01 / 市场招展中心</p>
        <p class="_c">立足全国市场，把控行业走向，制定品牌战略</p>
      </div>
      <div class="_item l-flex-vhc">
        <img src="../assets/images/20180527010.png" alt="">
        <p class="_t">02 / 媒体运营中心</p>
        <p class="_c">品牌广告升级，宣传推广，提高知名度</p>
      </div>
      <div class="_item l-flex-vhc _even">
        <img src="../assets/images/20180527011.png" alt="">
        <p class="_t">03 / 资源管理中心</p>
        <p class="_c">精选优势车源，国际物流衔接全国市场</p>
      </div>
      <div class="_item l-flex-vhc">
        <img src="../assets/images/20180527012.png" alt="">
        <p class="_t">04 / 品牌运营中心</p>
        <p class="_c">店铺运营指导，驻店解决店铺运营问题</p>
      </div>
      <div class="_item l-flex-vhc">
        <img src="../assets/images/20180527013.png" alt="">
        <p class="_t">05 / 售后服务中心</p>
        <p class="_c">全时全面专业解决客户汽车售后问题</p>
      </div>
      <div class="_item l-flex-vhc _even">
        <img src="../assets/images/20180527014.png" alt="">
        <p class="_t">06 / 系统培训中心</p>
        <p class="_c">统一培训，运营管理服务标准</p>
      </div>
      <div class="_item l-flex-vhc">
        <img src="../assets/images/20180527015.png" alt="">
        <p class="_t">07 / 金融服务中心</p>
        <p class="_c">为全国客户提供垫资金融服务，给店铺做最大资金以后盾</p>
      </div>
      <div class="_item l-flex-vhc _even">
        <img src="../assets/images/20180527016.png" alt="">
        <p class="_t">08 / SaaS系统服务中心</p>
        <p class="_c">行业首创，为店铺运营保驾护航</p>
      </div>
    </div>
    <br>
    <div class="l-img-title" style="background-color:#f8f8f8; padding: 20px;">
      <img src="../assets/images/20180529005.jpg" alt="">
    </div>
    <div class="l-shop-img">
      <div class="l-flex-hc l-scroll-x">
        <img src="../assets/images/20180527019.jpg" alt="">
        <div style="margin-left: 20px;"></div>
        <img src="../assets/images/20180527021.jpg" alt="">
        <div style="margin-left: 20px;"></div>
        <img src="../assets/images/20180527022.jpg" alt="">
        <div style="margin-left: 20px;"></div>
        <img src="../assets/images/20180527023.jpg" alt="">
      </div>
    </div>

  </view-box>
</template>

<script>
import { Swiper } from 'vux'
export default {
  name: 'home',
  components: {
    Swiper,
  },
  data () {
    return {
      userInfo: null,
      bannerSwiper: {
        list: [{ 
          url: 'javascript:', 
          img: require('../assets/images/20180529001.jpg'), 
          title: '' 
        }]
      }
    }
  }
}
</script>

<style lang="less" scoped>
.l-ft-ask{
  background-color: #fff; padding: 10px; text-align: center; position: fixed; left: 0; right: 0; bottom: 0;
  img{width: 20px; height: 20px; vertical-align: -4px; margin-right: 5px;}
}
.l-ft-ask ~ ._placeholder{height: 45px;}
.l-btn-1{display: inline-block; font-size: 14px; border:none; outline: none; background-color: #ec6e3a; color: #fff;  padding: 10px 30px;}
.l-btn-1[mini]{padding: 5px 30px;}
.l-btn-1[round]{border-radius: 50px;}

.l-img-title{
  text-align: center;
  img{ vertical-align: top; height: 50px;}
}

.l-diyun, .l-zhuda{
  width: 50%; height: 270px; box-sizing: border-box;  padding: 20px; font-size: 14px;
  img{width: 40px; height: 40px; vertical-align: -13px; margin-right: 10px;}
  p{margin-top: 10px;}
}
.l-diyun{
  background: url('../assets/images/20180529008.jpg') no-repeat; background-size: cover; color: #fff;
}
.l-zhuda{
  background: url('../assets/images/20180529009.jpg') no-repeat; background-size: cover;
}

.l-operate-list{
  overflow: hidden;
  ._item{float: left; text-align: center; width: 50%; height: 180px; padding: 10px 20px; box-sizing: border-box; }
  img{width: 40px; height: 40px;}
  ._t{color: #5c5c5c; font-weight: 500; font-size: 13px; margin-top: 10px;}
  ._c{text-align: left; color: #898989; font-size: 13px; margin-top: 10px; min-height: 4em;}
}

.l-shop-img{
  background: url('../assets/images/20180527020.jpg') 50% 50% no-repeat; background-size: cover; padding: 20px;
  img{width: 250px; background-color: #fff;}
}
</style>

