<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/base.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增app地址</title>
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
<script>

	$(function(){
		$('#ImgForm').ajaxForm({ 
	        dataType:  'json', 
	        success:   function(rsp){
	        	if(rsp.status == 1){
	        		var imgUrl = rsp.imgUrl;
	        		$("#awardImageInfo").val(imgUrl);
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
		document.forms["awardAdd"].submit();
	}
}
//对表单元素进行非空判断
function checkFormData() {

	return true;
} 

</script>

<script type="text/javascript">
    
</script>

</head>

<body bgcolor="d3eaef" onload="check()">
<form id="awardAdd" name="awardAdd" action="award/doAddAddress" method="post">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="6%" height="19" valign="bottom"><div align="center"><img src="<%=basePath%>images/tb.gif" width="14" height="14" /></div></td>
                <td width="94%" valign="bottom"><span class="STYLE1"> 当前位置： 奖品管理 > 修改app地址</span></td>
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
        <td width="30%" height="20" bgcolor="" class="STYLE6"><div align="right"><span class="STYLE10">app地址：</span></div></td>
        <td width="70%" height="20" bgcolor="" class="STYLE6"> <textarea id="appAddress" name="appAddress" rows="3" cols="20">${appAddress}</textarea></td>
      </tr>
      
       <tr>
        <td width="20%" height="20" bgcolor="" class="STYLE6"><div align="right"><span class="STYLE10"></span></div></td>
        <td width="5%" height="20"  bgcolor="" class="STYLE6" ><input type="button" name="button" id="button" onclick="doAdd()" value="保存" /></td>
      </tr>
    </table></td>
  </tr>
</table>
</form>
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
