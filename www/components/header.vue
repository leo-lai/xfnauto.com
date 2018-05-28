<template>
	<div class="l-page-hd l-sticky">
		<el-row type="flex" class="_inner" :gutter="0">
		  <el-col :span="8">
		  	<a class="l-page-hd-logo" href="/">
			  	<img style="margin-top: 7px;" src="~/static/images/20180527001.jpg" alt="喜蜂鸟网络科技服务有限公司">
		  	</a>
		  </el-col>
		  <el-col :span="16">
		  	<el-menu class="l-page-hd-menu" mode="horizontal" router :default-active="$route.path">
				  <el-menu-item index="/">
						<p>首页</p>
						<span>home</span>
					</el-menu-item>
				  <el-menu-item index="/youshi">
						<p>合作优势</p>
						<span>the advantage</span>
					</el-menu-item>
				  <el-menu-item class="_hot" index="/joinus">
						<p>
							招商加盟
							<img src="~/static/images/hot.png" alt="">
						</p>
						<span>join us</span>
					</el-menu-item>
				  <el-menu-item index="/news">
						<p>新闻资讯</p>
						<span>the news</span>
					</el-menu-item>
				  <el-menu-item index="/about">
						<p>关于我们</p>
						<span>about us</span>
					</el-menu-item>
					<el-menu-item index="/contact">
						<p>联系我们</p>
						<span>contact us</span>
					</el-menu-item>
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
.l-page-hd-menu > .el-menu-item.is-active{border-bottom: 5px solid #fc6441; color: #fc6441;}
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
		font-size: 0.8rem; text-align: center;
		height: auto; line-height: 1.5; padding: 15px 30px 5px; 
		p{font-weight: 700;}
		span{color: #999; font-size: 0.5rem; text-transform: uppercase;}
	}
	._hot{
		p{position: relative; color: #fc6441;}
		img{position: absolute; top: -14px;}
	}
	.el-submenu>.el-menu{
		top: 75px; border:none;
	}
}
.l-page-hd-menu .el-menu-item:hover, .l-page-hd-menu .el-submenu__title:hover{
	background-color: transparent;
}

</style>