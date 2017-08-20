<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>上传视频文件</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="js/jquery1.10.2.js"></script>
  </head>
  <body>
  <form action="${pageContext.request.contextPath}/UploadVideo" enctype="multipart/form-data" method="post">
      选择视频文件：<input type="file" name="file"><br/>
  		  <input type="submit" value="上传">
  </form>
  ${message}</br>
        <!-- 遍历Map集合 -->
        视频列表列表<br/>
		<c:forEach var="me" items="${fileNameMap}">
		<c:url value="/RemoveVideo" var="removeurl">
		<c:param name="filename" value="${me.key}"></c:param>
		</c:url>
		${me.value}<a href="${removeurl}">删除</a><br/>
</c:forEach>
	${deletemessage}<br/>
	<select id="selectvideo" name="video">
	<option value="null">--请选择要播放的视频--</option>
	<c:forEach var="me" items="${fileNameMap}">
		<option value="${me.key}">${me.value}</option>
	</c:forEach>
	</select>
	<input type="button" value="播放" id="play" /><input type="button" value="停止" id="pause"/>	
	<div id="mes"></div>
  </body>
 <script type="text/javascript" language="javascript">
 	$("#play").click(function(){
 	var filename={"filename":$("#selectvideo").val(),"task":"play"};
 	alert($("#selectvideo").val());
 	$.post("PlayAndPauseVideo",filename,function(data,textStatus,xmlHttpReq){
 		alert(data);
 		$("#mes").text(data);
 	});
 	});
 	$("#pause").click(function(){
 	var task={"filename":$("#selectvideo").val(),"task":"stop"};
 	$.post("PlayAndPauseVideo",task,function(data,textStatus,xmlHttpReq){
 		alert(data);
 		$("#mes").text(data);
 	});
 	});
 </script>
</html>
