
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
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
.col-tb {
display: table;
width: 100%;
}
.ps-r {
position: relative;
}
.col-tb>[class^="col-"], .col-tb>[class*=" col-"] {
display: table-cell;
vertical-align: top;
padding-right: 10px;
float: none;
}
.panel {
margin-bottom: 20px;
background-color: #fff;
border: 1px solid #ddd;
-webkit-box-shadow: 0 1px 1px rgba(0,0,0,.05);
box-shadow: 0 1px 1px rgba(0,0,0,.05);
}
.panel-head {
padding: 0 10px;
min-height: 38px;
background: #f9f9f9;
border-bottom: 1px solid #ddd;
}
.panel-head > h3 {
display: inline-block;
line-height: 37px;
font-size: 13px;	
margin: 0;
color: inherit;
}
.panel>.list-group {
margin-bottom: 0;
}
.list-group {
padding-left: 0;
margin-bottom: 20px;
}

.panel-head+.list-group .list-group-item:first-child {
border-top-width: 0;
}
.panel>.list-group .list-group-item {
border-width: 1px 0;
border-radius: 0;
}
.list-group-item:first-child {
border-top-left-radius: 4px;
border-top-right-radius: 4px;
}
a.list-group-item {
color: #555;
}
.list-group-item {
position: relative;
display: block;
padding: 10px 15px;
margin-bottom: -1px;
border: 1px solid #ddd;
}
element.style {
width: 238px;
}
.panel-head+.list-group .list-group-item:first-child {
border-top-width: 0;
}
.panel>.list-group .list-group-item {
border-width: 1px 0;
border-radius: 0;
}
.list-group-item:first-child {
border-top-left-radius: 4px;
border-top-right-radius: 4px;
}
a.list-group-item {
color: #555;
}
.list-group-item {
position: relative;
display: block;
padding: 10px 15px;
margin-bottom: -1px;
border: 1px solid #ddd;
}
.ellipsis {
white-space: nowrap;
text-overflow: ellipsis;
overflow: hidden;
-o-text-overflow: ellipsis;
}
table.dataTable.no-footer {
border-color: #bebebe;
}
table.dataTable.no-footer {
border-bottom: 1px solid #111;
}
table.dataTable {
width: 100%;
margin: 0 auto;
clear: both;
border-collapse: separate;
border-spacing: 0;
}
table {
background-color: transparent;
}
table.dataTable thead th, table.dataTable thead td, table.dataTable.no-footer {
border-color: #bebebe;
}
table.dataTable thead th {
padding: 12px 10px;
}
table.dataTable thead th, table.dataTable thead td {
padding: 10px 18px;
border-bottom: 1px solid #111;
}
table.dataTable thead th, table.dataTable tfoot th {
font-weight: bold;
}
th {
text-align: left;
}
a {
cursor: pointer;
background: transparent;
color: #428bca;
text-decoration: none;
}
</style>

<script>

	$(document).ready(function() {
		$(".parentA").click(function(event){
			event.stopPropagation();
		});
		if ("up"=="${param.showType}") {
			//toggleItem('processView');
		}
//	  loadProcessStudyInfo();
	});

	function selActive2(tag,recTypeId){
		$(".recTypeTag.active").removeClass("active");
		$(tag).addClass("active");
		var url ="<s:url value='/res/doc/rotationFinishDetail'/>?roleFlag=${param.roleFlag}&doctorFlow=${param.doctorFlow}&recordFlow=${param.recordFlow}&recTypeId="+recTypeId+"&typeId="+"${typeId}";
        location.href=url;
	}
</script>
</head>
<body>

<div id="processView_div">
	<div id="dataView" class="ebox1" style="height:auto;border:0;margin-bottom: 20px;">
		<div class="toolkit bg-f6 toolkit-lg toolkit-bar fl" style="border-top: 0;width: 100%;">
			<ul class="j_e-nav-tab toolkit-list fl">
				<c:if test="${typeId eq jszyTCMPracticEnumN.id}">
					<c:set var="enumList" value="${registryTypeEnumList}"></c:set>
				</c:if>
				<c:if test="${typeId eq jszyTCMPracticEnumBasicPractice.id}">
					<c:set var="enumList" value="${practicRegistryTypeEnumList}"></c:set>
				</c:if>
				<c:if test="${typeId eq jszyTCMPracticEnumTheoreticalStudy.id}">
					<c:set var="enumList" value="${theoreticalRegistryTypeEnumList}"></c:set>
				</c:if>
				<c:forEach items="${enumList}" var="registryType" varStatus="status">
					<c:if test="${typeId eq jszyTCMPracticEnumN.id}">
						<c:set value="res_registry_type_${registryType.id}" var="viewCfgKey"/>
					</c:if>
					<c:if test="${typeId eq jszyTCMPracticEnumBasicPractice.id}">
						<c:set value="practic_registry_type_${registryType.id}" var="viewCfgKey"/>
					</c:if>
					<c:if test="${typeId eq jszyTCMPracticEnumTheoreticalStudy.id}">
						<c:set value="theoretical_registry_type_${registryType.id}" var="viewCfgKey"/>
					</c:if>
					
					<c:if test="${sysCfgMap[viewCfgKey] eq GlobalConstant.FLAG_Y}">
						<li id="${registryType.id}" class="j_mainline_link toolkit-item toolkit-item-tab j_mainline_link_task router recTypeTag <c:if test="${recTypeId eq registryType.id}">active</c:if>"
						onclick="selActive2(this,'${registryType.id}');">
							<a>${registryType.name}</a>
						</li>
					</c:if>
				</c:forEach>
				</ul>
			</div>
		<div id="processDiv" style="min-height: 300px;max-height: 300px;overflow: auto; clear: both;">
			<div class="goal-category">
				<c:if test="${typeId eq jszyTCMPracticEnumN.id}">
					<c:set var="enumKey" value="registryTypeEnum${recTypeId}"></c:set>
				</c:if>
				<c:if test="${typeId eq jszyTCMPracticEnumBasicPractice.id}">
					<c:set var="enumKey" value="practicRegistryTypeEnum${recTypeId}"></c:set>
				</c:if>
				<c:if test="${typeId eq jszyTCMPracticEnumTheoreticalStudy.id}">
					<c:set var="enumKey" value="theoreticalRegistryTypeEnum${recTypeId}"></c:set>
				</c:if>
				<c:set value="${dept.groupFlow}${dept.standardDeptId}" var="standardKey"/>
				<c:if test="${applicationScope[enumKey].haveItem eq GlobalConstant.FLAG_Y}">
					<c:forEach items="${deptReqList}" var="deptReq">
						<c:set value="${standardKey}${recTypeId}${deptReq.itemId}finish" var="finishKey"/>

						<div class="goal-category-head classButton">
							<span class="j_stage_name">
									<c:set value="${standardKey}${recTypeId}${deptReq.itemId}req" var="reqNum"/>
								<span style="display: inline-block;width: 20%;">${deptReq.itemName}&nbsp;></span>
								<span style="display: inline-block;width: 12%;">要求数：<fmt:formatNumber pattern="0" value="${finishPerMap[reqNum]+0}" var="s"/><c:out value="${s}" default="0"/></span>
								<span style="display: inline-block;width: 12%;">完成数：
									<fmt:formatNumber type="Number" value="${finishPerMap[finishKey]+0}" maxFractionDigits="0"/>
								</span>
								<span style="display: inline-block;width: 14%;color:red;">未完成数：
									<fmt:formatNumber type="Number" value="${(finishPerMap[reqNum]-finishPerMap[finishKey]+0)<0?0:(finishPerMap[reqNum]-finishPerMap[finishKey]+0)}" maxFractionDigits="0"/>
								</span>

									<c:if test="${ (empty finishPerMap[reqNum]) or (finishPerMap[reqNum]+0 eq 0) or (finishPerMap[reqNum] eq '')}">
										<c:set var="per" value="100%"></c:set>
									</c:if>
									<c:if test="${ !((empty finishPerMap[reqNum]) or (finishPerMap[reqNum]+0 eq 0) or (finishPerMap[reqNum] eq ''))}">
										<c:set var="per" value="${pdfn:transPercent(finishPerMap[finishKey],finishPerMap[reqNum]+0,2)}"></c:set>
									</c:if>
									<span style="display: inline-block;width: 20%;">完成比例：${per}</span>
							</span>
						</div>
					</c:forEach>
				</c:if>

				<c:if test="${applicationScope[enumKey].haveItem eq GlobalConstant.FLAG_N}">
					<div class="goal-category-head classButton">
						<span class="j_stage_name">
						<c:if test="${applicationScope[enumKey].haveReq eq GlobalConstant.FLAG_Y}">
							<c:set value="${applicationScope[enumKey].id}reqNum" var="reqKey"/>
							<c:set value="${standardKey}${recTypeId}finish" var="finishKey"/>
							<c:set value="${standardKey}${recTypeId}req" var="reqNum"/>
							<span style="display: inline-block;width: 12%;">要求数：<fmt:formatNumber pattern="0" value="${finishPerMap[reqNum]+0}" var="s"/><c:out value="${s}" default="0"/></span>
							<span style="display: inline-block;width: 12%;">完成数：${finishPerMap[finishKey]+0}</span>
							<span style="display: inline-block;width: 14%;color:red;">未完成数：${(finishPerMap[reqNum]-finishPerMap[finishKey]+0)<0?0:(finishPerMap[reqNum]-finishPerMap[finishKey]+0)}</span>

								<c:set value="${standardKey}${applicationScope[enumKey].id}" var="preKey"/>
								<c:if test="${ (empty finishPerMap[reqNum]) or (finishPerMap[reqNum]+0 eq 0) or (finishPerMap[reqNum] eq '')}">
									<c:set var="per" value="100%"></c:set>
								</c:if>
								<c:if test="${ !((empty finishPerMap[reqNum]) or (finishPerMap[reqNum]+0 eq 0) or (finishPerMap[reqNum] eq ''))}">
									<c:set var="per" value="${pdfn:transPercent(finishPerMap[finishKey],finishPerMap[reqNum]+0,2)}"></c:set>
								</c:if>
								<span style="display: inline-block;width: 20%;">完成比例：${per}</span>
						</c:if>
						<c:if test="${applicationScope[enumKey].haveReq eq GlobalConstant.FLAG_N}">
							<span style="display: inline-block;">${applicationScope[enumKey].name}数：${recList.size()+0}</span>
						</c:if>
						</span>
					</div>
				</c:if>
			</div>
		</div>
	</div>
</div>
</body>
</html>
