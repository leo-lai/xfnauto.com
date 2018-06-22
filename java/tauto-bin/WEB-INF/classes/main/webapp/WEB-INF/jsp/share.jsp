<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv = "X-UA-Compatible" content = "IE=edge,
    chrome=1" />
<meta name="viewport" content="width=device-width,
    minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
<meta name="Description" content=""/>
<meta name="keywords" content=""/>
<title>喜蜂鸟淘车网</title>
<style>
html,body{margin:0;padding:0;}
html,body{width: 640px; margin:0 auto;}
#images img{display:block;width: 100%;}
</style>
<script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>

</head>

<body>
 	<div id="images">
 	<%
 		String baseImages = request.getAttribute("baseImage").toString();
 		String[] baseImage = baseImages.split(",");
 		for(String i : baseImage){			
 	%>
 		<img src="<%=i%>">
 	<%} %>
 	<%
 		String image = request.getAttribute("image").toString();
 		String[] images = image.split(",");
 		for(String y : images){			
 	%>
 		<img src="<%=y%>">
 	<%} %>
 	</div>
 	<div style="margin: 15px;">
 		<img style="width: 100px; height: 100px; float:right;" src="${headPortrait}">
 		<p>客户经理：${userName}</p>
 		<p>联系电话：${phone}</p>
 		<p>门店名称：${orgName}</p>
 		<p>备注信息：${remarks}</p>
 	</div>
 	<div style="margin: 15px; text-align:center;">
 		<img width="200" height="200" src="${ticketImage}" />
 	</div>
 	
    <div id="content">
		
    </div>
</body>
<script>
// var baseImageStr = '${baseImage}';
// var baseImageArr = baseImageStr ? baseImageStr.split(',') : []
// var shareImageStr = '${image}';
// var shareImageArr = shareImageStr ? shareImageStr.split(',') : []

// var allImages = baseImageArr.concat(shareImageArr)
// var imagesHtmlArr = []
// for(var i=0; i<allImages.length; i++){
// 	imagesHtmlArr.push('<img src="'+allImages[i]+'">')
// }
// $('#images').html(imagesHtmlArr.join(''))
  
</script>
</html>