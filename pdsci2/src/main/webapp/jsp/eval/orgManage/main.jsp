<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		<jsp:param name="jquery_mask" value="true"/>
	</jsp:include>

	<style type="text/css">
		.selSysDept span{color: red;}
		.searchTable{
			border: 0px;
		}
		.searchTable tbody td{
			border: 0px;
			background-color: white;
			color: #575656;
		}
		.searchTable tbody th{
			border: 0px;
			background: white;
			color: #575656;
		}
	</style>

	<style type="text/css">
		#boxHome .item:HOVER{background-color: #eee;}
	</style>
	<script type="text/javascript" src="<s:url value='/js/j.suggest.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	<script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	<script type="text/javascript">
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
			});
		});
		$(document).ready(function(){
			toPage(1);
		});
		function search(){
			jboxPostLoad("userList" ,"<s:url value='/eval/orgManage/list'/>" ,$("#searchForm").serialize() , true);
		}
		function toPage(page) {
			if(page){
				$("#currentPage").val(page);
			}
			search();
		}
		function addOrg() {
			jboxOpen("<s:url value='/eval/orgManage/edit'/>","新增基地信息", 900, 300);
		}
		function editOrg(orgFlow) {
			jboxOpen("<s:url value='/eval/orgManage/edit'/>?orgFlow="+orgFlow+"&currentPage=${param.currentPage}","编辑基地信息", 900, 300);
		}
		function xieTong(flow){
			jboxOpen("<s:url value='/eval/orgManage/jointAll'/>?flow="+flow+"&currentPage=${param.currentPage}","协同基地维护",850,500);
		}
		function delOrg(orgFlow, recordStatus) {
			var msg = "";
			if (recordStatus == '${GlobalConstant.RECORD_STATUS_N}') {
				msg = "停用";
			} else if (recordStatus == '${GlobalConstant.RECORD_STATUS_Y}') {
				msg = "启用";
			}
			jboxConfirm("确认" + msg + "该基地吗？",function () {
				var url = "<s:url value='/eval/orgManage/delete?orgFlow='/>" + orgFlow+ "&recordStatus=" + recordStatus;
				jboxGet(url,null,function(){
					search();
				});
			});
		}
	</script>
</head>
<body>
<div class="mainright">
	<div class="content" id="provCityAreaId">
		<div class="queryDiv">
			<form  id="searchForm" method="post">
			<input type="hidden" id="currentPage" name="currentPage"/>
				<div class="inputDiv">
					<label class="qlable">基地名称：</label>
					<input id="orgSel" class="qtext" type="text" name="orgName" value="" autocomplete="off"/>
					<div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:32px;left:100px;">
						<div id="boxHome" style="max-height: 250px;overflow: auto;border: 1px #ccc solid;background-color: white;
						min-width: 148px;border-top: none;position: relative;display: none;text-align: left;">
							<c:forEach items="${applicationScope.sysOrgList}" var="org">
								<p class="item" flow="${org.orgFlow}" value="${org.orgName}" style="padding-left: 10px;">${org.orgName}</p>
							</c:forEach>
						</div>
					</div>
				</div>
				<div class="inputDiv">
					<label class="qlable">基地类型：</label>
					<select name="orgTypeId" class="qselect" >
						<option value="">全部</option>
						<c:forEach var="orgType" items="${orgTypeEnumList}">
							<c:if test="${(orgType.id eq orgTypeEnumHospital.id)}">
								<option value="${orgType.id}" <c:if test="${sysOrg.orgTypeId==orgType.id}">selected="selected"</c:if>>${orgType.name}</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
				<div class="inputDiv">
					<label class="qlable">基地等级：</label>
					<select name="orgLevelId" class="qselect">
						<option value="">全部</option>
						<c:forEach items="${orgLevelEnumList}" var="level">
							<option value="${level.id}">${level.name}</option>
						</c:forEach>
					</select>
				</div>
				<div class="qcheckboxDiv">
					<input type="button" value="查&#12288;询" class="searchInput" onclick="toPage(1);"/>
					<input type="button" class="searchInput" onclick="addOrg();" value="新&#12288;增"/>
				</div>
			</form>
		</div>
		<div class="resultDiv" id="userList">

		</div>
	</div>
</div>

</body>
</html>