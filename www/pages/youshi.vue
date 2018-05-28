<template>
  <div class="l-page-bd">
  	<div class="l-img-text" style="background-image: url('/images/20180527024.jpg');">
      <img style="height: 500px;" src="~static/images/20180527024.jpg" alt="">
    </div>
    <div class="l-img-text" style="background-image: url('/images/20180527025.jpg');">
      <img style="height: 535px;" src="~static/images/20180527025.jpg" alt="">
    </div>

    <div class="l-box-6">
      <img style="float:right; margin-top: -85px;" src="~static/images/20180527003.png">
      <div class="_inner">
        <p class="l-text-center"><img src="~static/images/20180527030.png" alt=""></p>
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
      <img style="position: relative; bottom: -90px;" src="~static/images/20180527004.png">
    </div>


    <div class="l-img-text" style="background-image: url('/images/20180527026.jpg');">
      <img style="height: 591px;" src="~static/images/20180527026.jpg" alt="">
    </div>

    <div class="l-img-text" style="background-image: url('/images/20180527027.jpg');">
      <img style="height: 645px;" src="~static/images/20180527027.jpg" alt="">
    </div>

    <div class="l-img-text" style="background-image: url('/images/20180527028.jpg');">
      <img style="height: 861px;" src="~static/images/20180527028.jpg" alt="">
    </div>

  </div>
</template>
<script>
export default {
  name: 'youshi',
  head: {
    title: '合作优势'
  }
}
</script>
<style scoped lang="scss">
.l-page-bd{background: #fff; }

.l-box-6{
  background: url('~static/images/20180527029.jpg') 50% 50% no-repeat;
  padding: 50px 0;
}
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

</style>