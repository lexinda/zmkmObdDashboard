<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>车保镖后台管理工作平台</title>
</head>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<frameset rows="127,*,11" frameborder="no" border="0" framespacing="0">
	<frame src="<%=basePath%>top.jsp" name="topFrame" scrolling="no"
		noresize="noresize" id="topFrame" />
	<frame src="<%=basePath%>center.jsp" name="mainFrame" id="mainFrame" />
	<frame src="<%=basePath%>down.jsp" name="bottomFrame" scrolling="no"
		noresize="noresize" id="bottomFrame" />
</frameset>
<noframes>
<body>
</body>
</noframes>
</html>