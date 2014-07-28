<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理工作平台</title>
<link rel="stylesheet" type="text/css" href="/css/style.css"/>
<script type="text/javascript" src="<%=basePath%>js/js.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery-1.7.2.min.js"></script>
<script >
function login(){

	var Message = "${message}";
	if(Message!="")
	 alert(Message);
	 window.open('../login.jsp','_top');
}
</script>
</head>
<body onload="login()">
<div id="top"> </div>
<form id="login" name="login" action="admin/login" method="post">
  <div id="center">
    <div id="center_left"></div>
    <div id="center_middle">
      <div class="user">
        <label>用户名：
        <input type="text" name="adminId" id="adminId" />
        </label>
      </div>
      <div class="user">
        <label>密　码：
        <input type="password" name="password" id="password" />
        </label>
      </div>
    </div>
    <div id="center_middle_right"></div>
    <div id="center_submit">
      <div class="button"> <img src="<%=basePath%>images/dl.gif" width="57" height="20" onclick="form_submit()" > </div>
      <div class="button"> <img src="<%=basePath%>images/cz.gif" width="57" height="20" onclick="form_reset()"> </div>
    </div>
    <div id="center_right"></div>
  </div>
</form>
<div id="footer"></div>
</body>
</html>
