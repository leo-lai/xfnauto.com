<template>
	<div class="l-page-ft">
		<div class="_inner">
			<div class="_nav">
				<dl>
					<dt>联系我们</dt>
					<dd>广东省广州市海珠区东晓南路548号</dd>
					<dd>400-1639-989</dd>
					<dd>customer@xfnauto.com</dd>
				</dl>
				<dl>
					<dt>门店分布</dt>
					<dd><a href="/shop">合作门店</a></dd>
				</dl>
				<dl style="width: 120px;">
					<dt>喜蜂鸟</dt>
					<dd><a href="/about">关于我们</a></dd>
					<dd><a href="/story">品牌故事</a></dd>
					<dd><a href="/join">招贤纳士</a></dd>
				</dl>
			</div>

			<div class="l-text-center">
				<p class="l-margin-s" style="color:#eee; font-size:0.8rem;">集团旗下公司</p>
				<p class="l-margin-s">
					广州淘车网汽车贸易有限公司&nbsp;&nbsp;|&nbsp;&nbsp;广州传创投资有限公司&nbsp;&nbsp;|&nbsp;&nbsp;鹤山永一骏亨汽车贸易有限公司</p>
				<p class="l-margin-s">粤ICP备17143479号</p>	
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
	background: #333; color: #999; 
	>._inner{width: 1200px; margin: auto; padding: 1rem; }
	a{color: #fff; text-decoration: none;}
	._nav{
		display: flex;
		justify-content: space-around;
		dl{width: 300px;}
		dt, dd{ margin: 0; padding: 0;}
		dt{color: #eee; margin-bottom: 5px; font-size: 0.8rem;}
		a{color: #999;}
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