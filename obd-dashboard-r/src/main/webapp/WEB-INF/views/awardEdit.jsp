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
		var map = new BMap.Map("container");
	    map.centerAndZoom("宁波", 12);
	    map.enableScrollWheelZoom();    //启用滚轮放大缩小，默认禁用
	    map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用

	    map.addControl(new BMap.NavigationControl());  //添加默认缩放平移控件
	    map.addControl(new BMap.OverviewMapControl()); //添加默认缩略地图控件
	    map.addControl(new BMap.OverviewMapControl({ isOpen: true, anchor: BMAP_ANCHOR_BOTTOM_RIGHT }));   //右下角，打开

	    var localSearch = new BMap.LocalSearch(map);
	    localSearch.enableAutoViewport(); //允许自动调节窗体大小
	    
	    $("#awardStart").focus(function() {
			WdatePicker({awardStart:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:00',alwaysUseStartDate:true});
		});
	    
	    $("#awardEnd").focus(function() {
			WdatePicker({awardEnd:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:00',alwaysUseStartDate:true});
		});
		
	});

	$(function(){
		$('#ImgForm').ajaxForm({ 
	        dataType:  'json', 
	        success:   function(rsp){
	        	if(rsp.status == 1){
	        		var imgUrl = rsp.imgUrl;
	        		$("#images").val(imgUrl);
	        		$("#oldImg").val(imgUrl);
	        		$("#uploadImg").val("");
	        		$("#uploadImg").remove();
	        		$("#awardImage").parent().html('<img class="item-img" src="'+imgUrl+'">'+
	        								'<input type="hidden" id="uploadImg" name="uploadImg" value="'+imgUrl+'"/>'+
	        								'<input class="span2" type="button" id="awardImage" name="awardImage" value="选择图片"/>');
	        	}
	        	$('#ImgForm').resetForm();
	        } 
	    });
	}).on("change", "#selectImg", function(){
		$('#ImgForm').submit();
	}).on('click', '#awardImage', function() {
		$("#selectImg").click();
	}).on("blur","#awardAddress",function(){
		searchByStationName();
	});
	
	function searchByStationName() {
		var map = new BMap.Map("container");
	    map.centerAndZoom("宁波", 12);
	    map.enableScrollWheelZoom();    //启用滚轮放大缩小，默认禁用
	    map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用

	    map.addControl(new BMap.NavigationControl());  //添加默认缩放平移控件
	    map.addControl(new BMap.OverviewMapControl()); //添加默认缩略地图控件
	    map.addControl(new BMap.OverviewMapControl({ isOpen: true, anchor: BMAP_ANCHOR_BOTTOM_RIGHT }));   //右下角，打开

	    var localSearch = new BMap.LocalSearch(map);
	    localSearch.enableAutoViewport(); //允许自动调节窗体大小
	    //map.clearOverlays();//清空原来的标注
	    var keyword = document.getElementById("awardAddress").value;
	    localSearch.setSearchCompleteCallback(function (searchResult) {
	        var poi = searchResult.getPoi(0);
	        document.getElementById("awardMap").value = poi.point.lng + "," + poi.point.lat;
	        //map.centerAndZoom(poi.point, 13);
	        //var marker = new BMap.Marker(new BMap.Point(poi.point.lng, poi.point.lat));  // 创建标注，为要查询的地方对应的经纬度
	        //map.addOverlay(marker);
	        var content = document.getElementById("text_").value + "<br/><br/>经度：" + poi.point.lng + "<br/>纬度：" + poi.point.lat;
	       // var infoWindow = new BMap.InfoWindow("<p style='font-size:14px;'>" + content + "</p>");
	        //marker.addEventListener("click", function () { this.openInfoWindow(infoWindow); });
	        // marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
	    });
	    localSearch.search(keyword);
	} 
function check(){
	var message = "${message}";
	if(message !="")
	alert(message);
}
function doAdd(){
	var flg = checkFormData();
	if(flg){
		document.forms["awardUpdate"].submit();
	}
}
//对表单元素进行非空判断
function checkFormData() {

	// 4s店名称
    var uploadImg = document.getElementById("uploadImg");
    if ( uploadImg.value == "" ) {
        alert( "奖品图片不能为空，请输入！" );
        return false;
    }

    //4s店电话
    var awardInfo = document.getElementById("awardInfo");
    if ( awardInfo.value == "" ) {
        alert("奖励说明不能为空，请输入！");
        awardInfo.focus();
        return false;
    }
    
  	//4S店所属地区
    var awardNumber = document.getElementById("awardNumber");
    if ( awardNumber.value == "" ) {
        alert("奖品编码不能为空，请输入!");
        awardNumber.focus();
        return false;
    }

    // 初始密码非空验证
    var awardType = document.getElementById("awardType");
    if ( awardType.value == -1 ) {
        alert("请选择奖品类型!");
        awardType.focus();
        return false;
    }
    
    // 总监电话非空验证
    var awardAddress = document.getElementById("awardAddress");
    if ( awardAddress.value == "" ) {
        alert("奖品地址不能为空，请输入！");
        awardAddress.focus();
        return false;
    }
    // 确认密码
    var awardMap = document.getElementById("awardMap");
    if ( awardMap.value == "" ) {
        alert("商家经纬度为空，检查奖品地址是否书写准确！");
        awardAddress.focus();
        return false;
    }

    // 密码的一致性验证
    var awardPhone = document.getElementById("awardPhone");
    //var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    var regPartton=/1[3-8]+\d{9}/;
    if ( awardPhone.value == "" ) {
        alert( "电话号码不能为空，请输入！" );
        awardPhone.focus();
        return false;
    }else if(!telCheck(awardPhone.value)){
    	alert("电话号码格式不正确！");
    	awardPhone.focus();
		return false;
	}
    
    function telCheck(phone)
    {
       with(document.forms[0]) 
       {
        var patten =/^(((\(0\d{2,3}\)){1}|(0\d{2,3}[- ]?){1})?([1-9]{1}[0-9]{2,7}(\-\d{3,4})?))$/;
        var pat = /^(\b13[0-9]{9}\b)|(\b14[7-7]\d{8}\b)|(\b15[0-9]\d{8}\b)|(\b18[0-9]\d{8}\b)|\b1[1-9]{2,4}\b$/ ;
        var checkphone=phone.toString().split('-');
        if( checkphone.length>2)
          return false;
         if (phone !="" || phone.length!=0) 
      {         
       if (phone.substring(0,3) == "+86") 
       {
                phone = phone.substring(3,phone.length);
         }
         if (phone.substring(0, 2) == "13"||phone.substring(0, 2) == "14" || phone.substring(0, 2) == "15" || phone.substring(0, 2) == "18") {
                if(pat.test(phone))
                {            
                  return true;
             }
             else 
             {
                 return false;
             } 
         } 
         else
         {
           if(patten.test(phone)) 
           {
               return true;
           } 
           else 
           {
                  return false;
              }
         }
        } 
        else
        {
            return false;
     } 
      }
    }

    // 总监姓名非空验证
    var awardStart = document.getElementById("awardStart");
    if ( awardStart.value == "" ) {
        alert("开始日期不能为空，请选择！");
        awardStart.focus();
        return false;
    } 
    
    var awardEnd = document.getElementById("awardEnd");
    if ( awardEnd.value == "" ) {
        alert("结束日期不能为空，请选择！");
        awardEnd.focus();
        return false;
    }else if(awardEnd.value<awardStart.value){
    	 alert("结束日期不能大于开始日期，请选择！");
         awardEnd.focus();
         return false;
    }
    
    var awardSecret = document.getElementById("awardSecret");
    if ( awardSecret.value == "" ) {
        alert("兑奖暗号不能为空，请选择！");
        awardSecret.focus();
        return false;
    }
    
    var awardProvide = document.getElementById("awardProvide");
    if ( awardProvide.value == "" ) {
        alert("奖品提供者不能为空，请选择！");
        awardProvide.focus();
        return false;
    }
    
    var awardRate = document.getElementById("awardRate");
    var reg = /^\d+$/;
    if ( awardRate.value == "" ) {
        alert("奖品出现的摇奖次数，请选择！");
        awardRate.focus();
        return false;
    }else if(!reg.test(awardRate.value)){
    	alert("奖品出现的摇奖次数格式不正确,应为纯数字格式！");
    	awardRate.focus();
		return false;
	}
	
	return true;
} 

</script>

<script type="text/javascript">
    
</script>

</head>

<body bgcolor="d3eaef" onload="check()">
<form id="awardUpdate" name="awardUpdate" action="award/doUpdate" method="post">
		<input type="hidden" id="awardId" name="awardId" value="${id}"/>
      <input type="hidden" id="uploadImg" name="uploadImg" value="${awardImage}"/>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="6%" height="19" valign="bottom"><div align="center"><img src="images/tb.gif" width="14" height="14" /></div></td>
                <td width="94%" valign="bottom"><span class="STYLE1"> 当前位置： 奖品管理 > 修改奖品</span></td>
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
        <td width="30%" height="20" bgcolor="" class="STYLE6"><div align="right"><span class="STYLE10">奖品图片：</span></div></td>
        <td width="70%" height="20" bgcolor="" class="STYLE6"> <input class="span2" type="button" id="awardImage" name="awardImage" value="选择图片"/> </td>
      </tr>
      <tr>
        <td width="5%" height="20" bgcolor="" class="STYLE6"><div align="right"><span class="STYLE10">奖励说明：</span></div></td>
        <td width="15%" height="20" bgcolor="" class="STYLE6"><input type="text" name="awardInfo" id="awardInfo" value="${awardInfo}" /></td>
      </tr>
       <tr>
        <td width="10%" height="20" bgcolor="" class="STYLE6"><div align="right"><span class="STYLE10">奖品编号：</span></div></td>
        <td width="15%" height="20" bgcolor="" class="STYLE6"><input type="text" name="awardNumber" id="awardNumber" value="${awardNumber}"/></td>
      </tr>
      <tr>
        <td width="5%" height="20" bgcolor="" class="STYLE6"><div align="right"><span class="STYLE10">奖品类型：</span></div></td>
        <td width="15%" height="20" bgcolor="" class="STYLE6">
        	<select id="awardType" name="awardType">
        	<c:if test="${awardType == 0}">
			  	<option value ="0">最新大奖</option>
			  	<option value ="1">超级大奖</option>
			</c:if>
			<c:if test="${awardType == 1}">
			  	<option value ="1">超级大奖</option>
			    <option value ="0">最新大奖</option>
			</c:if>  	
			</select>
        </td>
      </tr>
       <tr>
        <td width="10%" height="20" bgcolor="" class="STYLE6"><div align="right"><span class="STYLE10">地址：</span></div></td>
        <td width="15%" height="20" bgcolor="" class="STYLE6"><input type="text" name="awardAddress" id="awardAddress" value="${awardAddress}"/></td>
      </tr>
       <tr>
        <td width="10%" height="20" bgcolor="" class="STYLE6"><div align="right"><span class="STYLE10">商家经纬度：</span></div></td>
        <td width="15%" height="20" bgcolor="" class="STYLE6"><input type="text" name="awardMap" id="awardMap" value="${awardMap}"/></td>
      </tr>
       <tr>
        <td width="10%" height="20" bgcolor="" class="STYLE6"><div align="right"><span class="STYLE10">联系电话：</span></div></td>
        <td width="15%" height="20" bgcolor="" class="STYLE6"><input type="text" name="awardPhone" id="awardPhone" value="${awardPhone}"/></td>
      </tr>
       <tr>
        <td width="10%" height="20" bgcolor="" class="STYLE6"><div align="right"><span class="STYLE10">开始期限：</span></div></td>
        <td width="15%" height="20" bgcolor="" class="STYLE6"><input type="text"  id="awardStart" name="awardStart" value="${awardStart}"/></td>
      </tr>
      <tr>
        <td width="10%" height="20" bgcolor="" class="STYLE6"><div align="right"><span class="STYLE10">结束期限：</span></div></td>
        <td width="15%" height="20" bgcolor="" class="STYLE6"><input type="text"  id="awardEnd"  name="awardEnd" value="${awardEnd}"/></td>
      </tr>
      <tr>
        <td width="10%" height="20" bgcolor="" class="STYLE6"><div align="right"><span class="STYLE10">兑奖暗号：</span></div></td>
        <td width="15%" height="20" bgcolor="" class="STYLE6"><input type="text"  id="awardSecret"  name="awardSecret" value="${awardSecret}"/></td>
      </tr>
      <tr>
        <td width="10%" height="20" bgcolor="" class="STYLE6"><div align="right"><span class="STYLE10">奖品提供者：</span></div></td>
        <td width="15%" height="20" bgcolor="" class="STYLE6"><input type="text"  id="awardProvide"  name="awardProvide" value="${awardProvide}"/></td>
      </tr>
      <tr>
        <td width="10%" height="20" bgcolor="" class="STYLE6"><div align="right"><span class="STYLE10">中奖率：</span></div></td>
        <td width="15%" height="20" bgcolor="" class="STYLE6"><input type="text"  id="awardRate"  name="awardRate" value="${awardRate}"/></td>
      </tr>
       <tr>
        <td width="20%" height="20" bgcolor="" class="STYLE6"><div align="right"><span class="STYLE10"></span></div></td>
        <td width="5%" height="20"  bgcolor="" class="STYLE6" ><input type="button" name="button" id="button" onclick="doAdd()" value="保存" /></td>
      </tr>
    </table></td>
  </tr>
</table>
</form>
<div style="display:none">
<form id="ImgForm" action="award/imgUpload" method="post" enctype="multipart/form-data">
<input id="selectImg" type="file" name="selectImg" />
<input type="hidden" id="oldImg" name="oldImg" value="${awardImage}"/>
</form>
</div>
<div style="width:730px;margin:auto; display: none;">   
        <div id="container" 
            style="position: absolute;
                margin-top:30px; 
                width: 730px; 
                height: 590px; 
                top: 50; 
                border: 1px solid gray;
                overflow:hidden;
                display: none;
                ">
        </div>
    </div>
</body>
</html>
