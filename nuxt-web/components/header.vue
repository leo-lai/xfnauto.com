<template>
	<div class="l-page-hd l-sticky">
		<el-row type="flex" class="_inner" :gutter="0">
		  <el-col :span="12">
		  	<a class="l-page-hd-logo" href="/">
			  	<img style="height: 52px; vertical-align: -18px; margin-right: 7px;" src="~/static/logo.png" alt="喜蜂鸟网络科技服务有限公司">
			  	喜蜂鸟网络科技服务有限公司
		  	</a>
		  </el-col>
		  <el-col :span="12">
		  	<el-menu class="l-page-hd-menu" mode="horizontal" router :default-active="$route.path">
				  <el-menu-item index="/">首页</el-menu-item>
				  <el-menu-item index="/shop">门店分布</el-menu-item>
				  <!-- <el-menu-item index="/news">新闻中心</el-menu-item> -->
				  <el-menu-item index="/story">品牌故事</el-menu-item>
				  <el-menu-item index="/join">招贤纳士</el-menu-item>
				  <el-menu-item index="/about">关于我们</el-menu-item>
				</el-menu>
		  </el-col>
		</el-row>
	</div>
</template>

<script>
export default {
	
}
</script>
<style lang="scss">
.l-page-hd-menu > .el-menu-item.is-active{border-bottom: 5px solid #fc6441;}
.el-submenu .el-menu-item{min-width: 100%; }
.l-page-hd{
	background: rgba(255,255,255,1); padding: 0; border-bottom: 1px solid #efefef;
	>._inner{width: 1200px; margin: auto;}
}
.l-page-hd-logo{
	display: block; padding: 7px 0; text-decoration: none; color: #333;font-size: 1rem;
}
.l-page-hd-menu{
	background-color: transparent; float: right;
	.el-menu-item, .el-submenu .el-submenu__title{
		height: 74px; line-height: 74px; font-size: 0.8rem;
	}
	.el-submenu>.el-menu{
		top: 75px; border:none;
	}
}
.l-page-hd-menu .el-menu-item:hover, .l-page-hd-menu .el-submenu__title:hover{
	background-color: transparent;
}

</style>