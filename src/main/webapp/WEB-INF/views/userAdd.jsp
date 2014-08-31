<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/base.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新建服务站</title>
<style type="text/css">
body {
	margin-left: 3px;
	margin-top: 0px;
	margin-right: 3px;
	margin-bottom: 0px;
}
.STYLE1 {
	color: #e1e2e3;
	font-size: 12px;
}
.STYLE6 {color: #000000; font-size: 12; }
.STYLE10 {color: #000000; font-size: 12px; }
.STYLE19 {
	color: #344b50;
	font-size: 12px;
}
.STYLE21 {
	font-size: 12px;
	color: #3b6375;
}
.STYLE22 {
	font-size: 12px;
	color: #295568;
}
.item-img {width: 311px;height: 185px;vertical-align: middle; padding-bottom: 5px;}
</style>
<script type="text/javascript" src="<%=basePath %>js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/jquery.form.min.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.3"></script>
<script type="text/javascript" src="<%=basePath %>js/DatePicker/WdatePicker.js"></script>
<script>


	$(function(){
	});
	
function check(){
	var message = "${message}";
	if(message !="")
	alert(message);
}
function doAdd(){
	var flg = checkFormData();
	if(flg){
		document.forms["userAdd"].submit();
	}
}
//对表单元素进行非空判断
function checkFormData() {

   // 4s店名称
    /* var stationName = document.getElementById("stationName");
    if ( stationName.value == "" ) {
        alert( "4s店名不能为空，请输入" );
        stationName.focus();
        return false;
    }

    //4s店电话
    var stationPhone = document.getElementById("stationPhone");
    if ( stationPhone.value == "" ) {
        alert("4s店电话不能为空，请输入");
        stationPhone.focus();
        return false;
    }
    
  	//4S店所属地区
    var regionCode = document.getElementById("regionList");
    if ( regionCode.value <= 0 ) {
        alert("请选择4s店所属的地区!");
        regionCode.focus();
        return false;
    }

    // 初始密码非空验证
    var initPassword = document.getElementById("initPassword");
    if ( initPassword.value == "" ) {
        alert("初始密码不能为空，请输入");
        initPassword.focus();
        return false;
    }
    
    // 总监电话非空验证
    var directorPhone = document.getElementById("directorPhone");
    if ( directorPhone.value == "" ) {
        alert("总监电话不能为空，请输入");
        directorPhone.focus();
        return false;
    }
    // 确认密码
    var regPassword = document.getElementById("regPassword");
    if ( regPassword.value == "" ) {
        alert("确认密码不能为空，请输入");
        regPassword.focus();
        return false;
    }

    // 密码的一致性验证
    if ( regPassword.value != initPassword.value ) {
        alert( "用户密码和确认密码不一致，请重新输入" );
        regPassword.value = "";
        regPassword.focus();
        return false;
    }

    // 总监姓名非空验证
    var directorName = document.getElementById("directorName");
    if ( directorName.value == "" ) {
        alert("总监姓名不能为空，请选择");
        directorName.focus();
        return false;
    } */
	
	return true;
} 

</script>

<script type="text/javascript">
    
</script>

</head>

<body bgcolor="d3eaef" onload="check()">
<form id="userAdd" name="userAdd" action="user/doAdd" method="post">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="6%" height="19" valign="bottom"><div align="center"><img src="images/tb.gif" width="14" height="14" /></div></td>
                <td width="94%" valign="bottom"><span class="STYLE1"> 当前位置： 用户管理 > 新增用户</span></td>
              </tr>
            </table></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="d3eaef">
      <tr>
      		<div <c:if test="${empty errorMSG}"> style="display:none;color: red;"</c:if>>温馨提示: ${errorMSG}</div>
			<div <c:if test="${empty sucessMSG}"> style="display:none;color: green;"</c:if>>温馨提示: ${sucessMSG}</div>
      </tr>
      <tr>
        <td width="30%" height="20" bgcolor="" class="STYLE6"><div align="right"><span class="STYLE10">账号：</span></div></td>
        <td width="70%" height="20" bgcolor="" class="STYLE6"> <input type="text" id="userAccount" name="userAccount" /> </td>
      </tr>
      <tr>
        <td width="5%" height="20" bgcolor="" class="STYLE6"><div align="right"><span class="STYLE10">密码：</span></div></td>
        <td width="15%" height="20" bgcolor="" class="STYLE6"><input type="text" name="userPassword" id="userPassword" /></td>
      </tr>
       <tr>
        <td width="10%" height="20" bgcolor="" class="STYLE6"><div align="right"><span class="STYLE10">确认密码：</span></div></td>
        <td width="15%" height="20" bgcolor="" class="STYLE6"><input type="text" name="userPassword2" id="userPassword2" /></td>
      </tr>
       <tr>
        <td width="10%" height="20" bgcolor="" class="STYLE6"><div align="right"><span class="STYLE10">姓名：</span></div></td>
        <td width="15%" height="20" bgcolor="" class="STYLE6"><input type="text" name="userName" id="userName" /></td>
      </tr>
      <tr>
        <td width="5%" height="20" bgcolor="" class="STYLE6"><div align="right"><span class="STYLE10">性别：</span></div></td>
        <td width="15%" height="20" bgcolor="" class="STYLE6">
        	<select id="userGender" name="userGender">
			  <option value ="-1">=选择性别=</option>
			  	<option value ="0">男</option>
			  	<option value ="1">女</option>
			</select>
        </td>
      </tr>
       <tr>
        <td width="10%" height="20" bgcolor="" class="STYLE6"><div align="right"><span class="STYLE10">手机号码：</span></div></td>
        <td width="15%" height="20" bgcolor="" class="STYLE6"><input type="text" name="userPhone" id="userPhone" /></td>
      </tr>
       <tr>
        <td width="10%" height="20" bgcolor="" class="STYLE6"><div align="right"><span class="STYLE10">邮箱：</span></div></td>
        <td width="15%" height="20" bgcolor="" class="STYLE6"><input type="text" name="userEmail" id="userEmail" /></td>
      </tr>
       <tr>
        <td width="20%" height="20" bgcolor="" class="STYLE6"><div align="right"><span class="STYLE10"></span></div></td>
        <td width="5%" height="20"  bgcolor="" class="STYLE6" ><input type="button" name="button" id="button" onclick="doAdd()" value="保存" /></td>
      </tr>
    </table></td>
  </tr>
</table>
</form>
</body>
</html>
