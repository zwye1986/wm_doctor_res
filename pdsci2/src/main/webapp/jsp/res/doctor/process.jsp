
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
<script type="text/javascript" src="<s:url value='/js/echarts/echarts-all.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/res/doctor/Style.css'/>"></link>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true" />
	<jsp:param name="jbox" value="true" />
	<jsp:param name="jquery_ui_tooltip" value="true" />
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>

<style type="text/css">
#main{
   width: 100%;
   height: 98%;
   padding-top: 1%;
 }
.side1{margin:0;}
.selected {
	border-color: #79bf87;
	background-color: #D0FFDE;
}

.box {
	margin-bottom: 10px;
	cursor: pointer;
	border: 1px solid #ddd;
	width: 250px;
	height: 62px;
	background-color: #e0f7e1;
}

.box table {
	width: 100%;
	height: 100%
}

.toolkit-lg {
	min-height: 49px;
}

.toolkit-bar {
	border-bottom: 1px solid #ddd;
	min-height: 48px;
	border-top: 1px solid #ddd;
}

.toolkit {
	/* min-height: 37px; */
	position: relative;
}

.bg-f6 {
	background-color: #f6f6f6;
}

.toolkit-bar>ul {
	margin-right: 0;
}

.toolkit>ul {
	margin-right: -5px;
}

.toolkit-bar>ul {
	margin-right: 0;
}

.fl {
	float: left !important;
}

.fr {
	float: right !important;
}

ul,ol {
	list-style: none;
	margin: 0;
	padding: 0;
}

.toolkit-bar>.toolkit-list:first-child>.toolkit-item:first-child {
	margin-left: 12px;
}

.toolkit-lg.toolkit-bar .toolkit-item-tab {
	margin-top: 0;
	font-size: 14px;
}

.toolkit-bar .toolkit-item-tab {
	margin-bottom: -1px;
	margin-top: 0;
}

.toolkit>ul>li {
	margin-right: 10px;
	float: left;
}

.toolkit-lg.toolkit-bar .toolkit-item-tab>a {
	line-height: 46px;
}

.toolkit-bar .toolkit-item-tab.active>a {
	border-bottom: 2px solid #69b1df;
	color: #0088cc;
}

.toolkit-bar .toolkit-item-tab>a:hover {
	color: #0088cc;
}

.toolkit-bar .toolkit-item-tab>a {
	padding: 1px 5px 0;
	border-bottom: 2px solid transparent;
	color: #888;
	display: block;
	text-decoration: none;
	line-height: 34px;
}

.btn-sm,.btn-group-sm>.btn {
	padding: 5px 10px;
	font-size: 12px;
	line-height: 1.5;
	border-radius: 5px;
}

.btn-success {
	color: #fff;
	background-color: #5cb85c;
	border-color: #4cae4c;
}

.btn {
	display: inline-block;
	margin-bottom: 0;
	font-weight: normal;
	text-align: center;
	white-space: nowrap;
	vertical-align: middle;
	cursor: pointer;
	-webkit-user-select: none;
	background-image: none;
	border: 1px solid #ccc;
}

.ebox1 {
	background: #fff;
	height: 100%;
}


.goal-category ul.e-list {
min-height: 0px;
}
.goal-category-head {
font-weight: bold;
font-size: 14px;
color: #00a753;
line-height: 25px;
padding: 7px 8px;
position: relative;
cursor: pointer;
border-bottom: 1px solid #efefef;
}
.task-list {
min-height: 5px;
}
ul, ol {
list-style: none;
margin: 0;
padding: 0;
}

.e-list li {
margin: 0px;
padding: 0px;
list-style: none;
height: 36px;
line-height: 35px;
border-bottom: solid 1px #efefef;
cursor: pointer;
position: relative;
}
.e-list li.active, .e-list li.active:hover, .e-list li.active:hover input, .e-list li.active input {
background-color: #f5f5f5;
}
.e-list li .title {
text-align: left;
margin: 0px;
padding: 8px 0 7px 2px;
line-height: 20px;
height: 35px;
border: none;
background-color: transparent;
width: 52%;
-webkit-box-shadow: none;
-moz-box-shadow: none;
box-shadow: none;
color: #000;
vertical-align: top;
}
.e-list li > span {
margin: 0px;
padding: 0px;
text-align: center;
float: left;
}
.ellipsis {
white-space: nowrap;
text-overflow: ellipsis;
overflow: hidden;
-o-text-overflow: ellipsis;
}
.e-list li .mark {
height:35px;
padding: 0 0 0 8px;
cursor: move;
background-color: transparent;
}
.e-list li .mark > i {
background: url('<s:url value='/css/skin/${skinPath}/images/label.png'/>') no-repeat;
display: inline-block;
width: 5px;
height: 11px;
vertical-align: middle;
margin-top: 12px;
}
.goal-createGroup-bar {
padding: 5px;
line-height: 25px;
border: dashed 1px #e2e2e2;
border-width: 1px 0;
}
.goal-createGroup-bar a {
color: #333;
cursor: pointer;
font-size: 14px;
font-weight: bold;
}
.e-list.task-list li:HOVER {
	background-color: #f5f5f5;
}
.btn.btn-sm.btn-success.change:HOVER{
	color: lightgreen;
}
.menuHover:HOVER{
	color: lightgreen;
}
.selected{border:1px solid green;background-color: palegreen; box-shadow: 0px 1px 2px #f8fffa;}
.task-tab-li a:hover,.task-tab-li a:focus{color: #fff;outline: 0;background-color: #449d44;}
.toolkit-bar .toolkit-item-tab .tool_title {
color:black;
font-weight:bold;
line-height: 46px;
}
a {
cursor: pointer;
background: transparent;
color: #428bca;
text-decoration: none;
}
</style>
<script>
	var title = {
			<c:forEach items="${registryTypeEnumList}" var="registryType">
			
				<c:set value="res_registry_type_${registryType.id}" var="viewCfgKey"/>
				
				<c:if test="${sysCfgMap[viewCfgKey] eq GlobalConstant.FLAG_Y}">
				"${registryType.id}":{
					   registry:"${registryType.name}登记",
					   <c:if test="${GlobalConstant.FLAG_Y eq registryType.haveAppeal}">
					   appeal:"申述${registryType.name}",
					   </c:if>
					   width:700,
					   height:500,
				   },
				</c:if>
			</c:forEach>
			<c:forEach items="${practicRegistryTypeEnumList}" var="registryType">

				<c:set value="practic_registry_type_${registryType.id}" var="viewCfgKey"/>

				<c:if test="${sysCfgMap[viewCfgKey] eq GlobalConstant.FLAG_Y}">
				"${registryType.id}":{
					registry:"${registryType.name}登记",
					<c:if test="${GlobalConstant.FLAG_Y eq registryType.haveAppeal}">
					appeal:"申述${registryType.name}",
					</c:if>
					width:700,
					height:500,
				},
				</c:if>
			</c:forEach>
			<c:forEach items="${theoreticalRegistryTypeEnumList}" var="registryType">

				<c:set value="theoretical_registry_type_${registryType.id}" var="viewCfgKey"/>

				<c:if test="${sysCfgMap[viewCfgKey] eq GlobalConstant.FLAG_Y}">
				"${registryType.id}":{
					registry:"${registryType.name}登记",
					<c:if test="${GlobalConstant.FLAG_Y eq registryType.haveAppeal}">
					appeal:"申述${registryType.name}",
					</c:if>
					width:700,
					height:500,
				},
				</c:if>
			</c:forEach>
		};
	
	var openRec = {};
	
	var currentView = "view";
	
	var indexObj = {recTypeId:"${resRecTypeEnumCaseRegistry.id}"};
	var tagUrl = {
			"${resRecTypeEnumAfterSummary.id}":"<s:url value='/res/rec/showRegistryForm'/>",
	};
	
	function selBox(box,schDeptFlow,rotationFlow){
		$(".selected").removeClass("selected");
		$(box).addClass("selected");
		indexObj['schDeptFlow'] = schDeptFlow;
		indexObj['rotationFlow'] = rotationFlow;
		indexObj.resultFlow = $(box).attr("resultFlow") || "";
		
	}
	
	function selActive(tag,recTypeId){
		$(".recTypeTag.active").removeClass("active");
		$(tag).addClass("active");
		indexObj['recTypeId'] = recTypeId;
	}
	
	function loadRegistryView(processFlow){
		var url = tagUrl[indexObj.recTypeId] || "<s:url value='/res/rec/requireDataList'/>";
		jboxLoad("processDiv",url+"?roleFlag=${param.roleFlag}&schDeptFlow="+indexObj.schDeptFlow+"&processFlow="+processFlow+"&rotationFlow="+indexObj.rotationFlow+"&recTypeId="+indexObj.recTypeId+"&resultFlow="+indexObj.resultFlow,true);
	}
	
	//登记
	function loadForm(recFlow,reqFlow,recTypeId){
		recTypeId = recTypeId || indexObj.recTypeId;
		console.log(recTypeId);
		console.log(title);
		jboxOpen("<s:url value='/res/rec/showRegistryForm'/>?roleFlag=${param.roleFlag}&itemId="+reqFlow+"&recFlow="+recFlow+"&schDeptFlow="+indexObj.schDeptFlow+"&rotationFlow="+indexObj.rotationFlow+"&recTypeId="+recTypeId+"&resultFlow="+indexObj.resultFlow,title[recTypeId].registry,title[recTypeId].width,title[recTypeId].height);
	}
	
	//申述
	function editAppeal(processFlow,recTypeId,userFlow,itemId){
		recTypeId = recTypeId || indexObj.recTypeId;
		jboxOpen("<s:url value='/res/rec/editAppeal'/>?roleFlag=${param.roleFlag}&processFlow="+processFlow+"&recTypeId="+recTypeId+"&userFlow="+userFlow+"&resultFlow="+indexObj.resultFlow+"&schDeptFlow="+indexObj.schDeptFlow+"&itemId="+itemId,title[recTypeId].appeal,700,400);
	}
	
	function view(type,showType){
		$("#processView").show();
		currentView = type;
		if ("up"!=showType && $("#processView_div").is(":hidden")) {
			toggleItem('processView');
		}
		if(type=="view"){
			$("#dataViewB").hide();
			$("#dataViewA").show();
			$("#dataView").hide();
			var resultFlow = $(".selected").attr("resultFlow");
			var processFlow = $(".selected").attr("processFlow");
			jboxLoad("processView","<s:url value='/res/rec/processView'/>?roleFlag=${param.roleFlag}&resultFlow="+resultFlow+"&processFlow="+processFlow+"&showType="+showType,true);
			$("#proceView").show();
		}else{
			$("#dataViewA").hide();
			$("#dataViewB").show();
			$("#dataView").show();
			if($(".recTypeTag.active").length){
				$(".recTypeTag.active").click();
			}else{
				$(".recTypeTag:eq(0)").click();
			}
			$("#proceView").hide();
		}
	}
	
	function reloadContent(typeId){
		inDeptView();
		loadProcess("up");
		outDeptView("up");
		//中医全科-理论学习不展示出科模块
		ifViewOutDept(typeId);
	}

	function ifViewOutDept(typeId){
		if("${jszyTCMPracticEnumN.id}" == typeId){
			<%--中医全科实践理论学习没有出科--%>
			$("#outDeptView").show();
		}else if("${jszyTCMPracticEnumBasicPractice.id}" == typeId){
			<%--中医全科实践理论学习没有出科--%>
			$("#outDeptView").show();
		}else if("${jszyTCMPracticEnumTheoreticalStudy.id}" == typeId){
			<%--中医全科实践理论学习没有出科--%>
			$("#outDeptView").hide();
		}
	}
	
	function loadProcess(showType){
		if(currentView == "view"){
			view(currentView,showType);
		}else{
			if(!showType){
				loadRegistryView("${processMap[result.resultFlow].processFlow}");
			}else {
				view("view",showType);
			}

		}
	}
	
   $(document).ready(function() {
		$(".selected").click();
		<c:if test="${GlobalConstant.FLAG_Y eq sysCfgMap['res_reg_pre_flag']}">
			var myChart;
			<c:forEach items="${arrResultList}" var="result">
				<c:set value="${result.resultFlow}finish" var="finishKey"/>
				 <c:set value="${result.resultFlow}req" var="reqKey"/>
			   <c:set value="${finishPerMap[result.resultFlow]+0}" var="viewPer"/>
			   <c:set value="${pdfn:percentRoundHalfUp(viewPer,2)}" var="viewPer"/>
	   myChart = echarts.init(document.getElementById('chart_${result.resultFlow}'));
			 myChart.setOption({
					series : [
							{
								type : 'pie',
								radius : [25, 28],
								x: '0%', // for funnel
								itemStyle : {
									normal : {
										label : {
											formatter : function (a,b,c){return "<fmt:formatNumber type="Number" value="${viewPer}" maxFractionDigits="0"/>%"},
											textStyle: {
												baseline : 'center'
											}
										}
									},
								},
								data : [
									{name:'other', value:${100-viewPer},itemStyle : {
										normal : {
											color: '#ccc',
											label : {
												show : true,
												position : 'center'
											},
											labelLine : {
												show : false
											}
										},
										emphasis: {
											color: 'rgba(0,0,0,0)'
										}
									}},
									{name:'', value:${viewPer}, itemStyle :  {
										normal : {
											label : {
												show : true,
												position : 'center',
												formatter : '{b}',
												textStyle: {
													baseline : 'bottom'
												}
											},
											labelLine : {
												show : false
											}
										}
									}},
								]
							}
						]
					});
			</c:forEach>
		</c:if>
	});
   
   function inDeptView() {
	   var processFlow = $(".selected").attr("processFlow");
       jboxLoad("inDeptView","<s:url value='/res/rec/inDeptView'/>?roleFlag=${param.roleFlag}&resultFlow="+indexObj.resultFlow+"&rotationFlow=${param.rotationFlow}&schDeptFlow="+indexObj.schDeptFlow+"&processFlow="+processFlow,true);
   }
   
   function outDeptView() {
	   var processFlow = $(".selected").attr("processFlow");
	   jboxLoad("outDeptView","<s:url value='/res/rec/outDeptView'/>?roleFlag=${param.roleFlag}&resultFlow=${param.resultFlow}&rotationFlow=${param.rotationFlow}&schDeptFlow=${param.schDeptFlow}&processFlow="+processFlow,true);
  	}
   
   function toggleItem(id,obj) {
	   $("#"+id+"_div").toggle();
	   $(obj).find("img").toggle();
   }
   
   function scrollHome(homeId){
	   var homeHeight = $("#"+homeId).css("height");
	   if(homeHeight){
		   homeHeight = parseFloat(homeHeight.replace("px",""))+$(".side1").scrollTop();
		   $(".side1").animate({scrollTop:homeHeight+"px"},500);
// 		   $(".side1").scrollTop(homeHeight);
	   }
   }
</script>
</head>
<body>
	<div class="main_fix" style="overflow: hidden; top: 0">
		<div id="main">
			
<!-- 			<div style="width: 40px;height: 40px;border-radius:20px;background-color: #436996;position: absolute;top: 5px;right: 10px;z-index: 1;border: 1px solid #ccc;cursor: pointer;"> -->
<!-- 				<div style="color: white;margin-top: 12px;margin-left: 7px;">周记</div> -->
<!-- 			</div> -->
			
			<div class="side" style="height: 100%;width: 270px;">
				<c:forEach items="${arrResultList}" var="result">
					<div 
						resultFlow="${result.resultFlow}" 
						processFlow="${processMap[result.resultFlow].processFlow}" 
						class="box ${param.resultFlow eq result.resultFlow?'selected':''}" 
						<c:if test="${!empty processMap[result.resultFlow]}">
							onclick="selBox(this,'${result.schDeptFlow}','${result.rotationFlow}');reloadContent('${resultIfTheoreticalStudyMap[result.resultFlow]}');"
						</c:if>
						<c:if test="${empty processMap[result.resultFlow]}">
							style="background-color: #fff;cursor: default;opacity:0.5;"
						</c:if>
					>
						<table>
							<tr>
								<td style="padding-top: 2px;width: 0px;text-align: right;">
								</td>
								<td style="text-align: left; padding-left: 5px;">
									<img 
										style="vertical-align:middle;" 
										src="<s:url value='/jsp/res/images/icon_ks.png'/>"
									>
									
									<span style="color:#017bce;font-size:14px;">
										${result.schDeptName}
											<c:if test="${GlobalConstant.FLAG_Y eq processMap[result.resultFlow].isExternal}">
												<br><font size="2">(${processMap[result.resultFlow].orgName})</font>
											</c:if>
									</span>
									<div style="font-size: 8px;padding-left: 4px;color: #a8a8a8;">
										${result.schStartDate} ~ ${result.schEndDate}
									</div>
								</td>
								<td>
									<div id="chart_${result.resultFlow}" style="height: 100%;width:60px;float: right;"></div>
								</td>
<%-- 								<td style="text-align: right; padding-right: 5px;">${finishPerMap[result.resultFlow]+0}%</td> --%>
							</tr>
						</table>
					</div>
				</c:forEach>
			</div>
			<div class="side1">
				<div id="inDeptView" class="side1" style="height:auto;border:1px solid #ddd;margin-bottom: 20px;">
				</div>
<%-- 				<c:if test="${empty sysCfgMap['res_process_flag'] || sysCfgMap['res_process_flag']==GlobalConstant.FLAG_Y }"> --%>
					<c:if test="${ sysCfgMap['res_jipei'] != GlobalConstant.FLAG_N}">
						<div id="processView" class="side1" style="height:auto;border:1px solid #ddd;margin-bottom: 20px;"></div>
					</c:if>
<%-- 				</c:if> --%>
				<div id="outDeptView" class="side1" style="height:auto;border:1px solid #ddd;margin-bottom: 20px;">
				</div>
			</div>
		</div>
	</div>
</body>
</html>