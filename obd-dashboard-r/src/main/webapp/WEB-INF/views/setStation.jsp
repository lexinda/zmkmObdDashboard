<%@ page language="java" contentType="text/html; charset=utf-8" 
pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
<script>
   //保存信息
   function doSet(){
	   var flg = checkFormData();
		if(!flg)
			return false;
		   $.ajax({   
		       type: "post",
		       url:"/station/reviseStation",
		       dataType: "json",
		       cache: false,
		       data:{
		    	   "stationId":$("#stationId").val(),
		    	   "stationName":$("#stationName").val(),
		    	   "stationPhone":$("#stationPhone").val(),
		    	   "directorName":$("#directorName").val(),
		    	   "status":$("#status").val(),
		    	   "directorPhone":$("#directorPhone").val()
			   },   
		       beforeSend: function() {
					$("#message").text("正在发送请求，请稍后...");
					return true;
			   },
			   error:function(a,b){
	 			   alert("超时，请重新登陆！")
				   window.open('../login.jsp','_top'); 
			   },
		       success:function(jsonObject) {   
	 	   		if (!jsonObject.success) {
						$("#message").text("分页查询失败");
						return;	
			    	}
	 	   		alert("保存成功！")
	 	   	      window.close();
		       }
		    }); 
   }
   //取消保存信息
   function doCancel(){
	   window.close();
   }
   //信息校验
   function checkFormData() {
	    // 总监姓名非空验证
	    var stationName = document.getElementById("stationName");
	    if ( stationName.value == "" ) {
	        alert("服务站名称不能为空，请输入");
	        stationName.focus();
	        return false;
	    }
	    // 新密码非空验证
	    var stationPhone = document.getElementById("stationPhone");
	    if ( stationPhone.value == "" ) {
	        alert("新密码不能为空，请输入");
	        stationPhone.focus();
	        return false;
	    }
	    // 确认密码
	    var directorName = document.getElementById("directorName");
	    if ( directorName.value == "" ) {
	        alert("确认密码不能为空，请输入");
	        directorName.focus();
	        return false;
	    }
	 // 确认密码
	    var directorPhone = document.getElementById("directorPhone");
	    if ( directorPhone.value == "" ) {
	        alert("确认密码不能为空，请输入");
	        directorPhone.focus();
	        return false;
	    }
		return true;
	} 
</script>
</head>

<body bgcolor="d3eaef">
<form id="setForm" name="setForm" action="station/reviseStation" method="post">
<input type="hidden" name="stationId" id="stationId" value="${stationId}"/>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="6%" height="19" valign="bottom"><div align="center"><img src="images/tb.gif" width="14" height="14" /></div></td>
                <td width="94%" valign="bottom"><span class="STYLE1"> 当前位置：修改服务站信息</span></td>
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
        <td width="5%" height="20"  class="STYLE6"><div align="right"><span class="STYLE10">总监ID：</span></div></td>
        <td width="15%" height="20" class="STYLE6"> <label>${consultantAccount}</label></td>
      </tr>
      <tr>
        <td width="5%" height="20"  class="STYLE6"><div align="right"><span class="STYLE10">4S店名称：</span></div></td>
        <td width="15%" height="20" class="STYLE6"><input type="text" name="stationName" id="stationName" value="${name}"/></td>
      </tr>
       <tr>
        <td width="10%" height="20" class="STYLE6"><div align="right"><span class="STYLE10">4S店电话：</span></div></td>
        <td width="15%" height="20" class="STYLE6"><input type="text" name="stationPhone" id="stationPhone" value="${phone}" /></td>
      </tr>
      <tr>
        <td width="5%" height="20" bgcolor="" class="STYLE6"><div align="right"><span class="STYLE10">是否通过审核：</span></div></td>
        <td width="15%" height="20" bgcolor="" class="STYLE6">
        	<select id="status" name="status">
        	<c:if test="${status == 1}">
			  <option value ="1">通过审核</option>
			  <option value ="0">未审核</option>
			</c:if>
			<c:if test="${status == 0}">
			  <option value ="0">未审核</option>
			  <option value ="1">通过审核</option>
			</c:if>
			</select>
        </td>
      </tr>
       <tr>
        <td width="5%" height="20"  class="STYLE6"><div align="right"><span class="STYLE10">总监名称：</span></div></td>
        <td width="15%" height="20" class="STYLE6"><input type="text" name="directorName" id="directorName" value="${cname}"/></td>
      </tr>
       <tr>
        <td width="10%" height="20" class="STYLE6"><div align="right"><span class="STYLE10">联系人电话：</span></div></td>
        <td width="15%" height="20" class="STYLE6"><input type="text" name="directorPhone" id="directorPhone" value="${cphone}"/></td>
      </tr>
      <tr>
        <td width="10%" height="20" class="STYLE6"><div align="right"><span class="STYLE10"></span></div></td>
        <td width="15%" height="20" class="STYLE6" align="left"><input type="button" name="button" id="button" onclick="doSet()" value="保存" />
        <input type="button" name="button" id="button" onclick="doCancel()" value="取消" /></td>
      </tr>
    </table></td>
  </tr>
</table>
</form>
</body>
</html>
