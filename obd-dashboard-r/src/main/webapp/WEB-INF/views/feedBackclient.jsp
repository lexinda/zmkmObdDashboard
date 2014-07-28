<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/base.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户反馈</title>
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

<script type="text/javascript" src="<%=basePath%>js/jquery-1.7.2.min.js"></script>
<script >
function doQuery(pageNo){
	  check1(pageNo);
	  check2(pageNo);
	  var director;
	  var page;
	 $.ajax({   
	       type: "GET",
	       url:"feedback/getfeedback",
	       dataType: "json",
	       cache: false,
	       data:{
		       "startNumber":(pageNo-1)*$("#pageSize").val(),
		       "pageSize":$("#pageSize").val(),
		       "type":${type}
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
	   		$("#message").text("结果如下");
	   		$("#totalRecords").text(jsonObject.total);
	   		
	   		var totalPages = parseInt( page=((jsonObject.total)%$("#pageSize").val()==0)?(jsonObject.total) / $("#pageSize").val():(jsonObject.total) / $("#pageSize").val()+1);
	   		$("#totalPages").text(totalPages);
				$("#pageTable tr:gt(0)").remove();
				var htmlString = "";
				$.each(jsonObject.list, function(i, n) { 
					   htmlString+="<tr>";
					   htmlString+="<td height='20' bgcolor='#FFFFFF' class='STYLE19'><div align='center'>" + n.userAccount + "</div></td>";
					   htmlString+="<td height='20' bgcolor='#FFFFFF' class='STYLE19'><div align='center'>" + n.userName + "</div></td>";
					   htmlString+="<td height='20' bgcolor='#FFFFFF' class='STYLE19'><div align='center'>" + n.content + "</div></td>";
					   htmlString+="<td height='20' bgcolor='#FFFFFF' class='STYLE19'><div align='center'>" + n.userPhone + "</div></td>";
					   htmlString+="<td height='20' bgcolor='#FFFFFF' class='STYLE19'><div align='center'>" + n.fbTime + "</div></td>";
					   htmlString+="<td height='20' bgcolor='#FFFFFF' class='STYLE19'><div align='center'>" + get(n.type) + "</div></td>";
					   htmlString+="</tr>";
				});
				$("#pageTable").append(htmlString);
				$("#pageNo").val(pageNo);
				 check1(pageNo);
		 		   check2(pageNo);
            }
	    });   
	} 
	
$(document).ready(function(){
	 $("#pageNo").blur(function(){
		 //非空验证
		 if( $("#pageNo").val()==null || $("#pageNo").val()==""){
			 alert( "页数不能为空请输入！" );
			 	window.setTimeout( function(){ $("#pageNo").focus();}, 0);
			 	return;
		 }
		//数字验证
		 var reg = /\D/g;
		 if ( reg.test($("#pageNo").val()) ) {
		 	alert( "输入的页码不是数字，请重新输入" );
		 	$("#pageNo").val("");
		 	window.setTimeout( function(){ $("#pageNo").focus(); }, 0);
		 	return;
		 }

		 // 范围验证
		 var no = parseInt( $("#pageNo").val() );
		 if ( no < 1 || no > $('#totalPages').text() ) {
		 	alert( "输入的页码超出范围请重新输入" );
		 	$("#pageNo").val("");
		 	window.setTimeout( function(){ $("#pageNo").focus(); }, 0);
		 	return;
		 }
		 });
	 //分页条数改变
		$("#pageSize").change(function(){
				 $("#pageNo").val("1");
			});
});

/**
 * 上一页是否失效
 */
 function check1(pageNo){
		
		if(pageNo==1){
			// $("#page1").text("");
			 $("#page1").removeAttr("href");
			 $("#page1").removeAttr("onclick");

		}else{
			$("#page1").attr("href","javascript:void(0)"); 
			$("#page1").attr("onclick","doQuery($('#pageNo').val()-1)"); 
			
		}
}
/**
 * 下一页失效判断
 */
function check2(pageNo){
	
	  if(pageNo==$("#totalPages").text()){
		  $("#page2").removeAttr("href");
	      $("#page2").removeAttr("onclick");
	}else{
		$("#page2").attr("href","javascript:void(0)"); 
		$("#page2").attr("onclick","doQuery(parseInt($('#pageNo').val())+1)"); 
	} 
}

function get(type){
	if(type==0)
		return "普通用户";
	else
		return "VIP";
 }
</script>

</head>
<body onload="doQuery(1)">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="6%" height="19" valign="bottom"><div align="center"><img src="images/tb.gif" width="14" height="14" /></div></td>
                <td width="94%" valign="bottom"><span class="STYLE1">当前位置： 用户反馈</span></td>
              </tr>
            </table></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
   <tr>
    <table width="100%" border="0" cellspacing="0" cellpadding="1" bgcolor="#a8c7ce">
      <tr>
        <td width="8%" height="40" ><div align="right"><span id="message" style="color: red;"></span></div></td>
      </tr>
    </table>
  </tr>
  <tr>
    <td><table id="pageTable"  width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
      <tr>
        <td width="5%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">用户ID</span></div></td>
        <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">用户姓名</span></div></td>
        <td width="45%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">反馈内容</span></div></td>
        <td width="15%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">联系人电话</span></div></td>
        <td width="15%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">反馈时间</span></div></td>
        <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">用户等级</span></div></td>
      </tr>
      
    </table></td>
  </tr>
  <tr>
    <td height="30">
     <table width="100%" height="30" border="0" cellpadding="0" cellspacing="0" class="page_table">
  <tr>
    <td width="20" bgcolor='#FFFFFF' class="STYLE19" align="right"><div align="right">数据:<span class="STYLE10" id="totalRecords"></span>条</div></td>
    <td width="20" bgcolor='#FFFFFF' class="STYLE19" align="center"><div align="center"><a href="javascript:void(0)" onclick="doQuery(1)">第一页</a></div></td>
     <td width="20" bgcolor='#FFFFFF' class="STYLE19" align="center"><div align="center"><a id="page1" href="javascript:void(0)" onclick="doQuery($('#pageNo').val()-1)">上一页</a></div></td>
      <td width="20" bgcolor='#FFFFFF' class="STYLE19" align="center"><div align="center"><a id="page2" href="javascript:void(0)" onclick="doQuery(parseInt($('#pageNo').val())+1)">下一页</a></div></td>
       <td width="20" bgcolor='#FFFFFF' class="STYLE19" align="center"><div align="center"><a href="javascript:void(0)" onclick="doQuery($('#totalPages').text())">尾页</a></div></td>
    <td width="12" bgcolor='#FFFFFF' class="STYLE19" align="center" > <div align="center">第</div></td>
    <td width="375" bgcolor='#FFFFFF' class="STYLE19" class="font_left">
      <input id="pageNo" type="text" value="1" size="2" maxlength="4" />
      /<span id="totalPages"></span>页
      	<input type="button"  value="分页查询" border="0" onclick="doQuery($('#pageNo').val())" />
      	   
      	   <select name="pageSize" id="pageSize">
		      <option value="20">20</option>
		      <option value="50">50</option>
		      <option value="100">100</option>
		    </select>
      </td>
  </tr>
</table>
   </td>
  </tr>
</table>
</body>
</html>
