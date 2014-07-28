<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/base.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>信息设置</title>
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
</style>
<script>
   function doSet(){
	   var flg = checkFormData();
	   if(flg){
			document.forms["setForm"].submit();
		}
   }
   function checkFormData() {
	    // 总监姓名非空验证
	    var oldPassword = document.getElementById("oldPassword");
	    if ( oldPassword.value == "" ) {
	        alert("原密码不能为空，输入");
	        oldPassword.focus();
	        return false;
	    }
	    // 新密码非空验证
	    var newPassword = document.getElementById("newPassword");
	    if ( newPassword.value == "" ) {
	        alert("新密码不能为空，请输入");
	        newPassword.focus();
	        return false;
	    }
	    // 确认密码
	    var confirmPassword = document.getElementById("confirmPassword");
	    if ( confirmPassword.value == "" ) {
	        alert("确认密码不能为空，请输入");
	        confirmPassword.focus();
	        return false;
	    }

	    // 密码的一致性验证
	    if ( confirmPassword.value != newPassword.value ) {
	        alert( "新密码和确认密码不一致，请重新输入" );
	        confirmPassword.value = "";
	        confirmPassword.focus();
	        return false;
	    }
		return true;
	} 
</script>
</head>

<body bgcolor="d3eaef">
<form id="setForm" name="setForm" action="admin/changePassword" method="post">
<input type="hidden" name="adminId" id="adminId" value="${admin.getId()}"/>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="6%" height="19" valign="bottom"><div align="center"><img src="images/tb.gif" width="14" height="14" /></div></td>
                <td width="94%" valign="bottom"><span class="STYLE1"> 当前位置：修改密码</span></td>
              </tr>
            </table></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellpadding="0" cellspacing="0" >
      <tr>
        <td width="30%" height="20" bgcolor="" class="STYLE6"></td>
        <td width="70%" height="20" bgcolor="" class="STYLE6"></td>
      </tr>
      <tr>
        <td width="30%" height="20" bgcolor="" class="STYLE6"></td>
        <td width="70%" height="20" bgcolor="" class="STYLE6"></td>
      </tr>
      <tr>
        <td width="30%" height="20" bgcolor="" class="STYLE6"></td>
        <td width="70%" height="20" bgcolor="" class="STYLE6"></td>
      </tr>
     <tr>
        <td width="5%" height="20"  class="STYLE6"><div align="right"><span class="STYLE10">原密码：</span></div></td>
        <td width="15%" height="20" class="STYLE6"> <input type="password" name="oldPassword" id="oldPassword"/></td>
      </tr>
      <tr>
        <td width="5%" height="20"  class="STYLE6"><div align="right"><span class="STYLE10">新密码：</span></div></td>
        <td width="15%" height="20" class="STYLE6"><input type="password" name="newPassword" id="newPassword" /></td>
      </tr>
       <tr>
        <td width="10%" height="20" class="STYLE6"><div align="right"><span class="STYLE10">确认密码：</span></div></td>
        <td width="15%" height="20" class="STYLE6"><input type="password" name="confirmPassword" id="confirmPassword" /></td>
      </tr>
      <tr>
        <td width="10%" height="20" class="STYLE6"><div align="right"><span class="STYLE10"></span></div></td>
        <td width="15%" height="20" class="STYLE6" align="left"><input type="button" name="button" id="button" onclick="doSet()" value="保存" /></td>
      </tr>
    </table></td>
  </tr>
</table>
</form>
</body>
</html>
