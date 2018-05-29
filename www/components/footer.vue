<template>
	<div class="l-page-ft">
		<div class="_inner">
			<div class="_nav">
				<dl>
					<dt><a href="/youshi">合作优势</a></dt>
					<dd><a href="/youshi">商业模式</a></dd>
					<dd><a href="/youshi">盈利优势</a></dd>
					<dd><a href="/youshi">运营优势</a></dd>
					<dd><a href="/youshi">供应链优势</a></dd>
					<dd><a href="/youshi">数据优势</a></dd>
					<dd><a href="/youshi">资金优势</a></dd>
					<dd><a href="/youshi">资源优势</a></dd>
				</dl>
				<dl>
					<dt><a href="/news">新闻资讯</a></dt>
					<dd><a href="/news?type=1">公司新闻</a></dd>
					<dd><a href="/news?type=2">行业动态</a></dd>
					<dd><a href="/news?type=3">汽车商学院</a></dd>
				</dl>
				<dl>
					<dt><a href="/about">关于我们</a></dt>
					<dd><a href="/about">企业介绍</a></dd>
					<dd><a href="/about">品牌概念</a></dd>
					<dd><a href="/about">项目优势</a></dd>
					<dd><a href="/about">店铺形象</a></dd>
				</dl>
				<dl style="border-left: 1px solid rgb(77, 85, 93); width: 140px;"></dl>
				<dl style="width: 390px;">
					<dd>
						<span style="display: inline-block; vertical-align: top; text-align: right; margin-right: 88px;">
							周一至周五 <br>
							<b style="font-size: 1rem; color:#fff;">400-1936-989</b>
						</span>
						<img style="width: 120px; height: 120px;" src="~/static/images/20180527006.jpg" alt="喜蜂鸟网络科技服务有限公司">
					</dd>
					<dd style="margin-top: 10px;">
						<p>公司地址：广州市海珠区琶洲保利世贸中心E座3205号</p>
						<p>电子邮箱：customer@xfnauto.com</p>
					</dd>
				</dl>
			</div>

			<div class="l-text-center">
				<!-- <p class="l-margin-s" style="color:#eee; font-size:0.8rem;">集团旗下公司</p> -->
				<p class="l-margin-s">
					<!-- 广州淘车网汽车贸易有限公司&nbsp;&nbsp;|&nbsp;&nbsp;广州传创投资有限公司&nbsp;&nbsp;|&nbsp;&nbsp;鹤山永一骏亨汽车贸易有限公司</p> -->
				<p class="l-margin-s l-fs-s">©2017-2018 喜蜂鸟网络科技服务有限公司 粤ICP备17143479号</p>	
			</div>
		</div>

		<div class="l-fixed-side">
			<!-- <el-tooltip placement="left">
				<div slot="content">
					<img width="200" height="200" src="https://static.ushiyihao.com/web/usee-online.jpg" alt="">
				</div>
				<a class="_item">
					<i class="l-icon">&#xe66f;</i>
					<span>微信<br>商城</span>
				</a>
			</el-tooltip> -->
			<a class="_item" @click="$scrollTo('#page-top')">
				<i class="l-icon">&#xe705;</i>
				<span>返回<br>顶部</span>
			</a>
			<a class="_item" @click="$router.back()">
				<i class="l-icon">&#xe62f;</i>
				<span style="padding:1rem 0;">上一页</span>
			</a>
		</div>
	</div>
</template>

<style lang="scss">
.l-page-ft{
	background: #373e45; color: #aeb9c3; 
	>._inner{width: 1200px; margin: auto; padding: 2rem 1rem 1px; }
	a{color: #fff; text-decoration: none;}
	._nav{
		display: flex;
		// justify-content: space-around;
		dl{width: 200px;}
		dt, dd{ margin: 0; padding: 0;}
		dt{color: #eee; margin-bottom: 5px; font-size: 0.8rem;}
		dd{font-size: 14px;}
		a{color: #aeb9c3;}
	}
}

.l-fixed-side{
	position: fixed; bottom: 10%; right: 0;  z-index: 1000; transform: translate3d(0,0,0);
	width: 60px;
	._item{
		background-color: #fc6441;
		display: block; width: 100%; text-align: center; height: 60px; line-height: 60px; color: #fff; margin-top: 1px;
		cursor: pointer; position: relative; overflow: hidden;
		span{
			background-color: #fc6441; padding: 11px; box-sizing:border-box; transition: all 0.3s;
			position: absolute; height: 100%; width: 100%; right:0; bottom: -60px; line-height: 1.4;
		}
	}
	.l-icon{font-size: 1.4rem;}
	._item:hover{
		span{bottom: 0;}
	}
}
</style>