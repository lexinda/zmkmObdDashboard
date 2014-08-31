<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath %>">
<meta http-equiv="Content-Type" content="text/html; charset=GB18030" />