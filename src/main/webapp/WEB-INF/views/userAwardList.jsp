<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/base.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>服务站信息</title>
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

<script type="text/javascript" src="<%=basePath %>js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
function query(pageNo) {
	   $.ajax({   
	       type: "POST",
	       url:"/user/showListByUserId",
	       dataType: "json",
	       cache: false,
	       data:{
	    	   "userId":$("#userId").val(),
		       "startNumber":(pageNo-1)*$("#pageSize").val(),
		       "pageSize":$("#pageSize").val()
		   },   
	       beforeSend: function() {
				$("#message").text("正在发送请求，请稍后...");
				return true;
		   },
		   error:function(a,b){
 			  /*  alert("超时，请重新登陆！")
			   window.open('../login.jsp','_top'); */ 
		   },
	       success:function(jsonObject) {
 	   		if (!jsonObject.success) {
					$("#message").text("分页查询失败");
					return;	
		    	}else{
 	   		$("#message").text("结果如下");
 	   		$("#totalRecords").text(jsonObject.total);
 	   		var page;
 	   		var totalPages = parseInt(page = ((jsonObject.total)%$("#pageSize").val()==0)?(jsonObject.total) / $("#pageSize").val():(jsonObject.total) / $("#pageSize").val()+1);
 	   		$("#totalPages").text(totalPages);
				$("#stationTable tr:gt(0)").remove();
				var htmlString = "";
				$.each(jsonObject.list, function(i, n) { 
					
					var type="";
					if(n.awardType === 0){
						type = "最新大奖";
					}else if(n.awardType === 1){
						type = "超级大奖";
					}
					var image="<%=basePath%>"+n.awardImage;
					   htmlString+="<tr>";
					   htmlString+="<td height='20' bgcolor='#FFFFFF' class='STYLE19'><div align='center'><img class='item-img width='20px' height='20px' src='" + image + "'/></div></td>";
					   htmlString+="<td height='20' bgcolor='#FFFFFF' class='STYLE19'><div align='center'>" + n.awardInfo + "</div></td>";
					   htmlString+="<td height='20' bgcolor='#FFFFFF' class='STYLE19'><div align='center'>" + n.awardNumber + "</div></td>";
					   htmlString+="<td height='20' bgcolor='#FFFFFF' class='STYLE19'><div align='center'>" + type + "</div></td>";
					   htmlString+="<td height='20' bgcolor='#FFFFFF' class='STYLE19'><div align='center'>" + n.awardAddress + "</div></td>";
					   htmlString+="<td height='20' bgcolor='#FFFFFF' class='STYLE19'><div align='center'>" + n.awardMap + "</div></td>";
					   htmlString+="<td height='20' bgcolor='#FFFFFF' class='STYLE19'><div align='center'>" + n.awardPhone + "</div></td>";
					   htmlString+="<td height='20' bgcolor='#FFFFFF' class='STYLE19'><div align='center'>" + n.awardStart + "</div></td>";
					   htmlString+="<td height='20' bgcolor='#FFFFFF' class='STYLE19'><div align='center'>" + n.awardEnd + "</div></td>";
					   htmlString+="<td height='20' bgcolor='#FFFFFF' class='STYLE19'><div align='center'>" + n.awardSecret + "</div></td>";
					   htmlString+="<td height='20' bgcolor='#FFFFFF' class='STYLE19'><div align='center'>" + n.awardProvide + "</div></td>";
					   htmlString+="<td height='20' bgcolor='#FFFFFF' class='STYLE19'><div align='center'>" + n.awardRate + "</div></td>";
					   htmlString+="<td height='20' bgcolor='#FFFFFF' class='STYLE19'><div align='center'>" + n.createTime + "</div></td>";
					   htmlString+="<td height='20' bgcolor='#FFFFFF' class='STYLE19'><div align='center'>" + n.updateTime + "</div></td>";
					   htmlString+="<td height='20' bgcolor='#FFFFFF' class='STYLE19'><div align='center'><a href='/user/deleteAwards?userId="+n.userId+"&awardId="+n.id+"' >兑奖</a></td>";
					   htmlString+="</tr>";
				});
				$("#stationTable").append(htmlString);
				$("#pageNo").val(pageNo);
            }
 	   	   check1(pageNo);
		   check2(pageNo);
	       }
	    });
	}
/**
 * 修改服务站信息
 */
function setAward(id){
	 window.open('/station/goSetStation?stationId='+id+'', 'orgcodeSelWindow', 'width=420, height=250, scrollbars=no')
}	
/**
 * 修改服务站信息
 */
function deleteAward(id){
	 window.open('/station/setpwd?stationId='+id+'', 'orgcodeSelWindow', 'width=420, height=250, scrollbars=no')
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
		 if ( reg.test($("#pageNo").val()) ){
		 	alert( "输入的页码不是数字，请重新输入" );
		 	$("#pageNo").val("");
		 	window.setTimeout( function(){ $("#pageNo").focus();}, 0);
		 	return;
		 }
		 // 范围验证
		 var no = parseInt( $("#pageNo").val() );
		 if ( no < 1 || no > $('#totalPages').text() ) {
		 	alert( "输入的页码超出范围请重新输入" );
		 	$("#pageNo").val("");
		 	window.setTimeout( function(){ $("#pageNo").focus(); }, 0);
		 	$("#pageNo").focus().select();
		 	return;
		 }
		 });
	 //分页条数改变
		$("#pageSize").change(function(){
				 $("#pageNo").val("1");
			});
  });
 function check1(pageNo){
	
		if(pageNo==1){
			 $("#page1").removeAttr("href");
			 $("#page1").removeAttr("onclick");

		}else{
			$("#page1").attr("href","javascript:void(0)"); 
			$("#page1").attr("onclick","query($('#pageNo').val()-1)"); 
			
		}
 }
 function check2(pageNo){
	  if(pageNo==$("#totalPages").text()){
		  $("#page2").removeAttr("href");
	      $("#page2").removeAttr("onclick");
	}else{
		$("#page2").attr("href","javascript:void(0)"); 
		$("#page2").attr("onclick","query(parseInt($('#pageNo').val())+1)"); 
	} 
 }
 function get(type){
	if(type==1)
		return "通过核审";
	else
		return "未通过审核";
 }
</script>
</head>
<body onload="query(1)" >
<form id="stationForm" name="stationForm" action="station/getStation" method="get">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="6%" height="19" valign="bottom"><div align="center"><img src="images/tb.gif" width="14" height="14" /></div></td>
                <td width="94%" valign="bottom"><span class="STYLE1"> 服务站基本信息列表</span></td>
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
       <%--  <td width="8%" height="40" ><div align="center"><span class="STYLE10">奖品编码：</span></div></td>
        <td width="164"><input type="button" onclick="query(1)" name="button" id="button" value="查询" /></td> --%>
        <td width="8%" height="20"><input type="hidden" name="userId" id="userId" value="${userId}" /></td>
        <td><div <c:if test="${empty errorMSG}"> style="display:none;color: red;"</c:if>>温馨提示: ${errorMSG}</div>
			<div <c:if test="${empty sucessMSG}"> style="display:none;color: green;"</c:if>>温馨提示: ${sucessMSG}</div></td>
        <td width="8%" height="40" ><div align="center"><span id="message" style="color: red;"></span></div></td>
         
      </tr>
    </table>
  </tr>
  <tr>
    <td><div style="overflow: scroll;"><table id ="stationTable" width="150%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
      <tr>
        <td width="100" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">奖品图片</span></div></td>
        <td width="200" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">奖励说明</span></div></td>
        <td width="100" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">奖品编号</span></div></td>
        <td width="100" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">奖品类型</span></div></td>
        <td width="100" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">地址</span></div></td>
        <td width="100" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">商家经纬度</span></div></td>
        <td width="100" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">联系电话</span></div></td>
        <td width="100" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">开始期限</span></div></td>
        <td width="100" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">结束期限</span></div></td>
        <td width="100" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">兑奖暗号</span></div></td>
        <td width="100" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">奖品提供者</span></div></td>
        <td width="100" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">中奖率</span></div></td>
        <td width="100" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">创建时间</span></div></td>
        <td width="100" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">更新时间</span></div></td>
        <td width="100" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">操作</span></div></td>
      </tr>
    </table></div></td>
  </tr>
  <tr>
  <td>
     <table width="100%" height="30" border="0" cellpadding="0" cellspacing="0" class="page_table">
  <tr>
    <td width="20" bgcolor='#FFFFFF' class="STYLE19" align="right"><div align="right">数据:<span class="STYLE10" id="totalRecords"></span>条</div></td>
    <td width="20" bgcolor='#FFFFFF' class="STYLE19" align="center"><div align="center"><a href="javascript:void(0)" onclick="query(1)">第一页</a></div></td>
     <td width="20" bgcolor='#FFFFFF' class="STYLE19" align="center"><div align="center"><a  id="page1" href="javascript:void(0)"  onclick="query($('#pageNo').val()-1)">上一页</a></div></td>
      <td width="20" bgcolor='#FFFFFF' class="STYLE19" align="center"><div align="center"><a id="page2" href="javascript:void(0)" onclick="query(parseInt($('#pageNo').val())+1)">下一页</a></div></td>
       <td width="20" bgcolor='#FFFFFF' class="STYLE19" align="center"><div align="center"><a href="javascript:void(0)" onclick="query($('#totalPages').text())">尾页</a></div></td>
    <td width="12" bgcolor='#FFFFFF' class="STYLE19" align="center" > <div align="center">第</div></td>
    <td width="375" bgcolor='#FFFFFF' class="STYLE19" class="font_left">
      <input id="pageNo" type="text" value="1" size="2" maxlength="4" />
      /<span id="totalPages"></span>页
      	<input type="button"  value="分页查询" border="0" onclick="query($('#pageNo').val())" />
      	   
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
</form>
</body>
</html>
