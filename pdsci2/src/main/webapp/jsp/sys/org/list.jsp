<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="false"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_scrollTo" value="false"/>
	<jsp:param name="jquery_jcallout" value="false"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fullcalendar" value="false"/>
	<jsp:param name="jquery_fngantt" value="false"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
</jsp:include>
<style type="text/css">
	#boxHome .item:HOVER{background-color: #eee;}
</style>
<script type="text/javascript">
	function add() {
		jboxOpen("<s:url value='/sys/org/edit?orgProvId=${param.orgProvId}'/>","新增机构信息", 900, 400);
	}
	function edit(orgFlow) {
		jboxOpen("<s:url value='/sys/org/edit?orgFlow='/>"+orgFlow,"编辑机构信息", 900, 400);
	}

	function delOrg(orgFlow, recordStatus) {
		var msg = "";
		if (recordStatus == '${GlobalConstant.RECORD_STATUS_N}') {
			msg = "停用";
		} else if (recordStatus == '${GlobalConstant.RECORD_STATUS_Y}') {
			msg = "启用";
		}
		jboxConfirm("确认" + msg + "该记录吗？",function () {
			var url = "<s:url value='/sys/org/delete?orgFlow='/>" + orgFlow+ "&recordStatus=" + recordStatus;
			jboxGet(url,null,function(){
				search();
			});
		});
	}
	function search() {
		jboxStartLoading();
		$("#searchForm").submit();
	}
	function toPage(page) {
		if(page!=undefined){
			$("#currentPage").val(page);
		}
		search();
	}
	function editOrgApplyLimit(orgFlow) {
		var url = "<s:url value='/srm/proj/apply/editOrgApplyLimit?orgFlow='/>"+orgFlow;
		jboxOpen(url, "机构限报", 800, 300);
	}
	function viewApplyLimitInfo(){
		var url = "<s:url value='/srm/proj/apply/viewallorgapplylimitinfo'/>";
		jboxOpen(url, "机构限报数量", 800, 400);
	}
	function changeDivView(vari,type){
		if(type == 'provId'){
			if(!$(vari).val()){
				$("#orgCityId").parent("div").hide();
				$("#orgAreaId").parent("div").hide();
			}else {
				$("#orgCityId").parent("div").attr("style","display:block;");
			}
		}else if(type == 'cityId'){
			if(!$(vari).val()){
				console.log(3);
				$("#orgAreaId").parent("div").hide();
			}else {
				console.log(4);
				$("#orgAreaId").parent("div").attr("style","display:block;");
			}
		}
	}


	/**
	*模糊查询加载
	*/
	(function($){
		$.fn.likeSearchInit = function(option){
			option.clickActive = option.clickActive || null;

			var searchInput = this;
			searchInput.on("keyup focus",function(){
				$("#boxHome").show();
				if($.trim(this.value)){
					$("#boxHome .item").hide();
					var items = $("#boxHome .item[value*='"+this.value+"']").show();
					if(!items){
						$("#boxHome").hide();
					}
				}else{
					$("#boxHome .item").show();
				}
			});
			searchInput.on("blur",function(){
				if(!$("#boxHome.on").length){
					$("#boxHome").hide();
				}
			});
			$("#boxHome").on("mouseenter mouseleave",function(){
				$(this).toggleClass("on");
			});
			$("#boxHome .item").click(function(){
				$("#boxHome").hide();
				var value = $(this).attr("value");
				$("#itemName").val(value);
				searchInput.val(value);
				if(option.clickActive){
					option['clickActive']($(this).attr("flow"));
				}
			});
		};
	})(jQuery);
	//======================================
	//页面加载完成时调用
	$(function(){
		$("#orgSel").likeSearchInit({
			/* clickActive:function(flow){
				$("#studyUserFlow").val(flow).change();
			} */
		});
	});

</script>
</head>
<body>
	<div class="mainright">
		<div class="content">
			<div class="title1 clearfix">
				<form id="searchForm" action="<s:url value="/sys/org/list" />"	method="post">
					<input id="currentPage" type="hidden" name="currentPage" value=""/>
					<div class="queryDiv">
						<div class="inputDiv">
							<label class="qlable">机构名称：</label>
							<input id="orgSel" class="toggleView qtext" type="text" name="orgName" value="${param.orgName}" autocomplete="off" title="${param.orgName}" onmouseover="this.title = this.value"/>
							<div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:35px;left:100px;">
								<div id="boxHome" style="max-height: 250px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 220px;border-top: none;position: relative;display: none;">
									<c:forEach items="${applicationScope.sysOrgList}" var="org">
										<p class="item" flow="${org.orgFlow}" value="${org.orgName}" style="height: 30px;padding-left: 10px;text-align: left;">${org.orgName}</p>
									</c:forEach>
								</div>
							</div>
						</div>
						<c:if test="${empty param.orgProvId}">
								<span id="provCityAreaId">
									<div class="inputDiv">
										<label class="province qlable">所属省份：</label>
										<select id="orgProvId" onchange="changeDivView(this,'provId');" name="orgProvId" class="province qselect" data-value="" data-first-title="选择省"></select>
									</div>
									<div class="inputDiv" style="display: none">
										<label class="city qlable">所属城市：</label>
										<select id="orgCityId" onchange="changeDivView(this,'cityId');" name="orgCityId" class="city qselect" data-value="" data-first-title="选择市"></select>
									</div>
									<div class="inputDiv" style="display: none">
										<label class="area qlable">所属区域：</label>
										<select id="orgAreaId" onchange="changeDivView(this,'areaId');" name="orgAreaId" class="area qselect" data-value="" data-first-title="选择地区"></select>
									</div>
								</span>
						</c:if>

						<c:if test="${not empty param.orgProvId}">
								<span id="provCityAreaId">
									<div class="inputDiv">
										<label class="province qlable">所属省份：</label>
										<select id="orgProvId" onchange="changeDivView(this,'provId');" name="orgProvId" class="province qselect" data-value="${param.orgProvId}" data-first-title="选择省"></select>
									</div>
									<div class="inputDiv" style="display: none">
										<label class="city qlable">所属城市：</label>
										<select id="orgCityId" onchange="changeDivView(this,'cityId');" name="orgCityId" class="city qselect" data-value="${param.orgCityId}" data-first-title="选择市"></select>
									</div>
									<div class="inputDiv" style="display: none">
										<label class="area qlable">所属区域：</label>
										<select id="orgAreaId" onchange="changeDivView(this,'areaId');" name="orgAreaId" class="area qselect" data-value="${param.orgAreaId}" data-first-title="选择地区"></select>
									</div>
								</span>
						</c:if>
						<script type="text/javascript">
							// 提示：如果服务器不支持 .json 类型文件，请将文件改为 .js 文件
							$.cxSelect.defaults.url = "<s:url value='/js/provCityAreaJson.min.json'/>";
							$.cxSelect.defaults.nodata = "none";

							$("#provCityAreaId").cxSelect({
								selects : ["province", "city", "area"],
								//selects : ["province"],
								nodata : "none",
								firstValue : ""
							});
						</script>
						<div class="inputDiv">
							<label class="qlable">机构代码：</label>
							<input type="text" name="orgCode" value="${param.orgCode}" class="qtext"/>
						</div>
						<div class="lastDiv"  style="min-width: 245px;max-width: 245px;text-align: left;padding-left: 29px;">
							<input type="button" class="search" onclick="search();" value="查&#12288;询">
							<input type="button" class="search" onclick="add();" value="新&#12288;增">
							<c:if test="${sessionScope.currWsId == 'srm'}">
								<input type="button" class="search" onclick="viewApplyLimitInfo();" value="查看限报">
							</c:if>
						</div>
					</div>
				</form>
			</div>
			<table id="orgTable" class="xllist">
				<thead>
				<tr>
					<th>机构代码</th>
					<th>机构名称</th>
					<th>所属地区</th>
					<c:if test="${sessionScope.currWsId=='edc'}">
					<th>机构类型</th>
					</c:if>
					<c:if test="${sessionScope.currWsId=='srm' and applicationScope.sysCfgMap['srm_for_use']=='global'}">
					<th>主管部门</th>
					</c:if>
					<th>操作</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${sysList}" var="org">
					<tr>
						<td>${org.orgCode }</td>
						<td>${org.orgName }</td>
						<td>${org.orgProvName}-${org.orgCityName}-${org.orgAreaName}</td>
						<c:if test="${sessionScope.currWsId=='edc'}">
						<td>${org.orgTypeName}</td>
						</c:if>
						<c:if test="${sessionScope.currWsId=='srm' and applicationScope.sysCfgMap['srm_for_use']=='global'}">
						<td>${org.chargeOrgName}</td>
						</c:if>
						<td>
							<c:if test="${org.recordStatus == GlobalConstant.RECORD_STATUS_Y }">
								[<a href="javascript:edit('${org.orgFlow}');">编辑</a>] |
								[<a href="javascript:delOrg('${org.orgFlow}','${GlobalConstant.RECORD_STATUS_N}');">停用</a>]
							</c:if>
							<c:if test="${org.recordStatus == GlobalConstant.RECORD_STATUS_N }">
								[<a href="javascript:delOrg('${org.orgFlow}','${GlobalConstant.RECORD_STATUS_Y}');">启用</a>]
							</c:if>
							<c:if test="${sessionScope.currWsId == 'srm'}">
								[<a href="javascript:void(0)" onclick="editOrgApplyLimit('${org.orgFlow}')">机构限报</a>]
							</c:if>

						</td>
					</tr>
				</c:forEach>
				</tbody>
				<c:if test="${sysList == null || sysList.size()==0 }">
					<tr>
						<td align="center" colspan="5">无记录</td>
					</tr>
				</c:if>
			</table>
			<c:set var="pageView" value="${pdfn:getPageView(sysList)}" scope="request"></c:set>
			<pd:pagination toPage="toPage"/>
	</div>
</div>
</body>
</html>