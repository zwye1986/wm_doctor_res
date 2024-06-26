<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_validation" value="true"/>
	</jsp:include>
	<script type="text/javascript" src="https://webapi.amap.com/maps?v=1.4.8&key=fd9e6d7408418bbb6cf33fe6eac77d30"></script>
	<script type="text/javascript" src="https://cache.amap.com/lbs/static/addToolbar.js"></script>
	<script type="text/javascript" src="https://webapi.amap.com/demos/js/liteToolbar.js"></script>
	<link rel="stylesheet" href="https://cache.amap.com/lbs/static/main1119.css"/>
	<script type="text/javascript">
	function save(){
		if(false==$("#orgAddressForm").validationEngine("validate")){
			return false;
		}
		var url = "<s:url value='/res/address/saveSigninSet'/>";
		 var data = $('#orgAddressForm').serialize();
		 jboxPost(url, data, function(resp) {
			 if(resp=="保存成功！") {
			 	window.location.reload(true);
			 }
		 }, null, true);
	}


	var map,addMarker;
	var geocoder;
	var placeSearch;
	var infoWindow ;
	var markers = [];
	function mapInit(){
		// 加入高的地图
		map = new AMap.Map('mapContainer', {
			resizeEnable: true,
			zoom:11
			<c:if test="${not empty orgAddress}">
			,
			center: [${orgAddress.longitude},${orgAddress.latitude}]
			</c:if>
		});
		AMap.plugin(['AMap.ToolBar','AMap.Scale'],
				function(){
					map.addControl(new AMap.ToolBar());
					map.addControl(new AMap.Scale());
				});
		AMap.service('AMap.Geocoder',function(){//回调函数
			//实例化Geocoder
			geocoder = new AMap.Geocoder({
				city: "全国"//城市，默认：“全国”
			});
		});
		// 初始化加载
		myMapViewLocation();
		AMap.service(["AMap.PlaceSearch"], function() {
			placeSearch = new AMap.PlaceSearch();
		});
		infoWindow = new AMap.InfoWindow({offset: new AMap.Pixel(0, -30)});//信息窗口
		//为地图注册click事件获取鼠标点击出的经纬度坐标
		var clickEventListener = map.on('click', function(e) {
			$("input[name=longitude]").val(e.lnglat.lng);
			$("input[name=latitude]").val(e.lnglat.lat);
			// 填写地址
			writeAddress([e.lnglat.lng,e.lnglat.lat]);
			if(map.getAllOverlays()!=''){
				map.remove(marker);
				map.remove(markers);
			}
		});
		//键盘点击搜索
		//placeSearch.search("北京");
		$("#input_id").on("keydown",function(event){
			if(event = event || window.event){
				if(event.keyCode==13){
					mapsearch();
				}
			}
		});
		$("#input_id").on("blur",function(event){
			mapsearch();
		});
	}
	//地图搜索
	function mapsearch(){
		//查询前先移除所有标注
		if(map.getAllOverlays()!=''){
			map.remove(marker);
			map.remove(markers);
		}
		var searchVal = $("#input_id").val();
		placeSearch.search(searchVal,function(status, result){
			if (status === 'complete' && result.info === 'OK') {
				var poiArr = result.poiList.pois;
				var str = "<ul>";
				for(var i=0;i<poiArr.length;i++){
					//在地图上创建标注点
					marker = new AMap.Marker();
					marker.setPosition(new AMap.LngLat(poiArr[i].location.lng,poiArr[i].location.lat));
					marker.setMap(map);
					marker.setLabel({//label默认蓝框白底左上角显示，样式className为：amap-marker-label
						offset: new AMap.Pixel(3, 0),//修改label相对于maker的位置
						content: String.fromCharCode(65+i)
					});
					marker.content = poiArr[i].name+"<br/>"+poiArr[i].address;
					marker.on('click', markerClick);
					markers.push(marker);
					map.setFitView();
				}
			}else{
				console.log("未查询到相关地址");
			}
		});

	}
	// 初始化回显
	function myMapViewLocation(){
		//console.log("回显坐标");
		var mlon = $("input[name=longitude]").val();
		var mlat = $("input[name=latitude]").val();
		var lnglatXY = [mlon,mlat];
		if(mlon&&mlat){
			addMarker(lnglatXY);
		}
	}
	// 实例化点标记
	function addMarker(lnglatXY) {
		if(map.getAllOverlays()!=''){
			map.remove(marker);
			map.remove(markers);
		}
		marker = new AMap.Marker({
			icon: "http://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",
			position: lnglatXY
		});
		marker.setMap(map);
		map.setFitView();// 执行定位
	}
	// 填写地址
	function writeAddress(lnglatXY){
		geocoder.getAddress(lnglatXY, function(status, result) {
			if (status === 'complete' && result.info === 'OK') {
				myMapViewLocation()
				geocoder_CallBack(result);
			}
		});
	}
	//点击标注  显示信息窗口及内容
	function markerClick(e) {
		infoWindow.setContent(e.target.content);
		infoWindow.open(map, e.target.getPosition());
	}
	// 地址回调
	function geocoder_CallBack(data) {
		var address = data.regeocode.formattedAddress; //返回地址描述
		$("input[name=orgAddress]").val(address);
	}
	$(function(){
		//===============================
		mapInit();	//地图初始化
		<c:if test="${not empty orgAddress}">
			myMapViewLocation();
		</c:if>

	});
</script>
	<style type="text/css">

		#mapContainer input {
			position: absolute;
			z-index: 99999;
			top: 20px;
			right: 30px;
			height: 30px;
			width: 200px;
		}
		#panel {
			position: absolute;
			background-color: white;
			max-height: 90%;
			overflow-y: auto;
			top: 10px;
			right: 10px;
			width: 280px;
		}
	</style>
</head>
<body>
<div class="content">
	<div class="title1 clearfix">
		<div id="tagContent">
			<fieldset>
				<legend>考勤定位地点设置</legend>
				<div id="mapContainer" style="width: 100%;height: 400px;">
					<input name="input_id" id="input_id" placeholder="请输入搜索地址"  maxlength="20"/>
				</div>
				<div id="panel">
				</div>
				<div style="height: 300px;width: 100%">
					<form id="orgAddressForm">
						<input name="orgFlow" hidden value="${sessionScope.currUser.orgFlow }">
						<input name="longitude" hidden value="${orgAddress.longitude}">
						<input name="latitude" hidden  value="${orgAddress.latitude}">
						<table class="basic" width="100%">
							<tr>
								<td width="150px;">签到地址：</td>
								<td><input name="orgAddress" type="text" class="input" style="width: 80%" value="${orgAddress.orgAddress}"></td>
							</tr>
							<tr>
								<td>签到半径：</td>
								<td><input name="scopeLength" type="text" class="input validate[required,custom[integer]]"  value="${orgAddress.scopeLength}">米</td>
							</tr>
						</table>


					</form>
					<div class="button">
						<input type="button" class="search" onclick="save();" value="保&#12288;存"/>
					</div>
				</div>
			</fieldset>
		</div>
	</div>
</div>
</body>
</html>