<template>
	<div class="l-page-bd">
		<div class="l-about">
			<div class="_inner">
				<br>
				<div class="l-text-center">
					<img src="~static/images/20180527038.png">
				</div>
				<p class="l-margin-t"><b class="l-fs-l">广州喜蜂鸟网络科技服务</b> 有限公司成立于2017年，隶属于传创投资控股集团旗下品牌。是一家专注于汽车产业互联网技术开发、新零售连锁品牌运营管理、人工智能产业技术研发与投资、汽车供应链服务平台开发与运营的创新型科技服务公司。</p>
				<p class="l-margin-t">司公司以用户体验为核心，在线上通过技术与科技的完美结合，实现用户之间实时信息交换，优化全国车源配置，为传统门店降低成本，提升效率。在线下，公司以发展“喜蜂鸟淘车网”汽车品牌6S服务综合门店为主要业务，集汽车批发、零售、物流、金融、保险、维修保养、精品为一体，为客户提供管家式贴心服务。</p>
				<p class="l-margin-t">2018年，喜蜂鸟淘车网品牌店铺启动全球连锁加盟招商，广州、东莞、清远、江门、云浮，均有网点覆盖，同年4月广西省成立了品牌分公司。公司现有终端业务团队与总部运营团队成员300余人，2017年销售额达2.8个亿，创下行业发展时间最短，业务规模最大的新记录。喜蜂鸟淘车网以用户至上为己任，以新思维，新技术，新形式为理念，以建立全国最大的汽车销售综合服务连锁品牌为目标，求真务实论发展，超越自我，全程为您。</p>
				<br><br>
				<div class="l-text-center l-margin-t">
					<img src="~static/images/20180527037.png">
				</div>
			</div>
		</div>

		<div>
			<img style="float:right; margin-top: -33px;" src="~static/images/20180527003.png">
			<div class="l-img-text" style="background-image: url('/images/20180527038.jpg');">
				<img style="height: 610px;" src="~static/images/20180527038.jpg" alt="">
			</div>
			<img style="float:left; position: relative; margin-top: -33px;" src="~static/images/20180527004.png">
		</div>
		
		<div class="l-img-text" style="background-image: url('/images/20180527039.jpg');">
      <img style="height: 896px;" src="~static/images/20180527039.jpg" alt="">
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
  </div>
</template>
<script>
export default {
	name: 'about',
  head: {
    title: '关于我们'
  }
}
</script>
<style scoped lang="scss">
.l-page-bd{background-color: #fff;}
.l-about{
	background: url('~static/images/20180527036.jpg') 50% top no-repeat; overflow: hidden; background-size: contain;
	._inner{ box-sizing: border-box; width:  1200px; padding: 30px 60px; margin: 60px auto; background-color: rgba(255, 255, 255, 0.8);}
}
.l-img-text{
  background: 50% 50% no-repeat;
  img{display: block; margin: auto; visibility: hidden;}
}
.l-shop-img{
  background: url('~static/images/20180527020.jpg') 50% 50% no-repeat;
  background-size: cover;
  ._inner{width:  1200px; margin: auto; padding: 30px 0;}
  img{width:  285px; background-color: #fff;}
}

</style>