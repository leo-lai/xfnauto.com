<template>
  <div class="l-page-bd">
  	<el-carousel class="l-page-banner" trigger="click" height="600px">
      <el-carousel-item style="background: url('/images/20180527002.jpg') 50% 50% no-repeat;">
        <img src="~static/images/20180527002.jpg" >
      </el-carousel-item>
    </el-carousel>
    
    <div class="l-box-6">
      <br><br><br>
      <img style="float:right;" src="~static/images/20180527003.png">
      <div class="_inner">
        <p class="l-text-center"><img src="~static/images/20180527005.jpg" alt=""></p>
        <p class="l-margin-t">广州喜蜂鸟网络科技服务有限公司成立于2017年，隶属于传创投资控股集团旗下品牌。是一个专注于汽车产业互联网技术开发、新零售连锁品牌运营管理、人工智能产业技术研发与投
资、汽车供应链服务平台开发与运营的创新型科技服务公司。</p>
        <p class="l-margin-t">公司打造以线上商城、资源、物流、SaaS系统、整店输出、售后服务六位一体的一站式标准化服务。</p>
        <p class="l-margin-t">公司以用户体验为核心，在线上通过技术与科技的完美结合，实现用户之间实时信息交换，优化全国车源配置，为传统门店降低成本，提升效率。在线下，公司以发展“喜蜂鸟淘车网”
汽车品牌6S服务综合门店为主要业务，集汽车批发、零售、物流、金融、保险、维修保养、精品为一体，为客户提供管家式贴心服务。</p>
        <br>
        <p class="l-margin-t l-text-center">
          <button class="l-btn-1" mini round>了解更多 >></button>
        </p>
      </div>
      <img style="float:left;" src="~static/images/20180527004.png">
    </div>

    <div class="l-img-text" style="background-image: url('/images/20180527007.jpg');">
      <img style="height: 760px;" src="~static/images/20180527007.jpg" alt="">
    </div>

    <div class="l-box-7">
      <br><br>
      <div class="_inner">
        <p class="l-text-center"><img src="~static/images/20180527008.jpg" alt=""></p>
        <div class="l-operate-list l-margin-t">
          <div class="_item l-flex-vhc _even">
            <img src="~static/images/20180527009.png" alt="">
            <p class="_t">01 / 市场招展中心</p>
            <p class="_c">立足全国市场，把控行业走向，制定品牌战略</p>
          </div>
          <div class="_item l-flex-vhc">
            <img src="~static/images/20180527010.png" alt="">
            <p class="_t">02 / 媒体运营中心</p>
            <p class="_c">品牌广告升级，宣传推广，提高知名度</p>
          </div>
          <div class="_item l-flex-vhc _even">
            <img src="~static/images/20180527011.png" alt="">
            <p class="_t">03 / 资源管理中心</p>
            <p class="_c">精选优势车源，国际物流衔接全国市场</p>
          </div>
          <div class="_item l-flex-vhc">
            <img src="~static/images/20180527012.png" alt="">
            <p class="_t">04 / 品牌运营中心</p>
            <p class="_c">店铺运营指导，驻店解决店铺运营问题</p>
          </div>
        </div>
        <div class="l-operate-list">
          <div class="_item l-flex-vhc">
            <img src="~static/images/20180527013.png" alt="">
            <p class="_t">05 / 售后服务中心</p>
            <p class="_c">全时全面专业解决客户汽车售后问题</p>
          </div>
          <div class="_item l-flex-vhc _even">
            <img src="~static/images/20180527014.png" alt="">
            <p class="_t">06 / 系统培训中心</p>
            <p class="_c">统一培训，运营管理服务标准</p>
          </div>
          <div class="_item l-flex-vhc">
            <img src="~static/images/20180527015.png" alt="">
            <p class="_t">07 / 金融服务中心</p>
            <p class="_c">为全国客户提供垫资金融服务，给店铺做最大资金以后盾</p>
          </div>
          <div class="_item l-flex-vhc _even">
            <img src="~static/images/20180527016.png" alt="">
            <p class="_t">08 / SaaS系统服务中心</p>
            <p class="_c">行业首创，为店铺运营保驾护航</p>
          </div>
        </div>
      </div>
    </div>

    <div class="l-box-7">
      <br><br><br>
      <div class="_inner">
        <p class="l-text-center"><img src="~static/images/20180527018.jpg" alt=""></p>
      </div>
    </div>
    <div class="l-shop-img l-margin-t">
      <div class="_inner l-flex-hc">
        <img src="~static/images/20180527019.jpg" alt="">
        <div style="margin-left: 20px;"></div>
        <img src="~static/images/20180527021.jpg" alt="">
        <div style="margin-left: 20px;"></div>
        <img src="~static/images/20180527022.jpg" alt="">
        <div style="margin-left: 20px;"></div>
        <img src="~static/images/20180527023.jpg" alt="">
      </div>
    </div>

    <div class="l-box-5 _inner">
      <div class="_tit"><span>合作企业</span></div>
      <img src="~static/images/20171113009.jpg">
    </div>
  </div>
</template>
<script>
export default {
  name: 'index',
  head: {
    title: '首页'
  }
}
</script>
<style scoped lang="scss">
.l-page-bd{background: #fff; }
.l-box-6 ._inner{width: 1200px; margin: auto;}
.l-img-text{
  background: 50% 50% no-repeat;
  img{display: block; margin: auto; visibility: hidden;}
}
.l-box-7 ._inner{width: 1200px; margin: auto;}

.l-operate-list{
  display: flex;
  ._item{text-align: center; width: 300px; height: 350px; padding: 30px; box-sizing: border-box; background-color: #f5f5f5;}
  ._even{background-color: #eaeaea;}
  img{width: 80px; height: 80px;}
  ._t{color: #5c5c5c; font-weight: 700; font-size: 18px; margin-top: 15px;}
  ._c{text-align: left; color: #898989; font-size: 14px; margin-top: 15px; min-height: 3em;}
}

.l-shop-img{
  background: url('~static/images/20180527020.jpg') 50% 50% no-repeat;
  background-size: cover;
  ._inner{width:  1200px; margin: auto; padding: 30px 0;}
  img{width:  285px; background-color: #fff;}
}




.l-box-1{
  border:1px solid #c7c6c6;
  padding: 1rem;
  font-size: 1rem;
  position: relative;
  text-indent: 2em;
}
.l-box-2{
  margin: 50px auto 0;
  overflow: hidden;
  text-align: center;
}
.l-box-1{width: 500px; margin-left: 100px; display: inline-block; vertical-align: top; text-align: left;}
.l-box-1:before{
  content: '';
  position: absolute;
  left: -15px;
  top: -15px;
  border-left: 5px solid #fc6441;
  border-top: 5px solid #fc6441;
  height: 50px;
  width: 50px;
}
.l-box-1:after{
  content: '';
  position: absolute;
  right: -15px;
  bottom: -15px;
  border-right: 5px solid #fc6441;
  border-bottom: 5px solid #fc6441;
  height: 50px;
  width: 50px;
}
.l-box-3{
  background: url('~static/images/20171113006.png') no-repeat 70% bottom; background-size: 70%;
  text-align: center; padding: 90px 300px; box-sizing: border-box; margin: -120px auto -30px;
  h3{color: #fc6441; font-size: 1.5rem; text-align: center;}
  p{font-size: 1rem; text-align: left;}
}
.l-box-4{
  background: url('~static/images/20171113007.png') no-repeat 30% bottom; background-size: 70%;
  text-align: center; padding: 90px 300px; box-sizing: border-box; margin: -60px auto 60px;
  h3{color: #fc6441; font-size: 1.5rem; text-align: center;}
  p{font-size: 1rem; text-align: left;}
}
.l-box-5{
  text-align: center;
  ._tit{
    position: relative; z-index: 0;
    span{background:#fff; padding: 20px 50px; font-size: 1.5rem; display: inline-block;}
    &:before{content: ''; border-top: 1px solid #999; position:absolute; top: 50%; left: 5%; right: 5%;z-index: -1;}
  }
}
</style>