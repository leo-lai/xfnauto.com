<template>
  <div class="l-page-bd">
  	<el-carousel class="l-page-banner" trigger="click" height="600px">
      <el-carousel-item style="background: url('/images/20171113001.jpg') 50% 50% no-repeat;">
      	<img src="~static/images/20171113001.jpg">
      </el-carousel-item>
    </el-carousel>
    <div class="l-text-center _inner">
      <p style="font-size:1.5rem; color:#fc6441;">喜蜂鸟网络科技服务有限公司</p>
      <p style="color:#999;">由传创投资有限公司投资成立于2017年</p>
      <p style="margin-top:1rem; font-size:1rem;">
        专注于汽车产业互联技术开发、新零售连锁品牌运营管理、人工<br>智能产业技术研发与投资、电子商务物流运营系统研发</p>
      <p style="margin-top: 1.2rem;"><img src="~static/images/20171113002.png"></p>
    </div>
    <div class="_inner l-box-2">
      <img width="35%" src="~static/images/20171113003.png">
      <div class="l-box-1">旗下品牌“喜蜂鸟淘车网”连锁市场规模年销售业绩已经突破10亿，喜蜂鸟淘车网连锁管理供应链管理服务平台致力于采用SAAS平台链接（ERP\CRM\CSM）对传统汽车销售与服务供应链进行重构、链接生产商和物流终端、优化供应链资源与服务商共享；有效对传统企业的成本控制、效率提升、营销能力等进行深度协同赋能，同时更有效提高顾客价值。
      </div>
    </div>
    <div class="_inner l-box-3">
      <h3>我们的宗旨</h3>
      <p>通过对技术与科技的结合、以多元的表现形式来促进企业与顾客、顾客与顾客的互动交流、创造非凡舒心的消费体验</p>
    </div>
    <div class="_inner l-box-4">
      <h3>我们的使命</h3>
      <p>是运用新的思维、新的技术、新的形式、将人与场景、信息、商品、事物链接在一起、为人们带来多感官的交互体验</p>
    </div>
    <div class="_inner l-box-3">
      <h3>我们的理念</h3>
      <p>在零售交付终端，一切设计都旨在为顾客提供个性化的服务 ——以用户希望的方式，提供切实想要的东西，并让他们倍感舒适。</p>
    </div>
    <div class="_inner l-text-center">
      <img width="90%" src="~static/images/20171113008.png">
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