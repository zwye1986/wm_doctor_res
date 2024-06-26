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
			jboxPostLoad("userList" ,"<s:url value='/eval/expertEvalOrg/list'/>" ,$("#searchForm").serialize() , true);
		}
		function toPage(page) {
			if(page){
				$("#currentPage").val(page);
			}
			search();
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
				<label class="qlable">评估年份：</label>
				<input class="qtext" id="evalYear" name="evalYear" value="${pdfn:getCurrYear()-1}" type="text"  readonly onClick="WdatePicker({dateFmt:'yyyy'})">
			</div>
			<div class="inputDiv">
				<label class="qlable">培训基地：</label>
				<input id="orgSel" class="qtext" type="text" name="orgName" value="${param.orgName}"  autocomplete="off"/>
				<div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:32px;left:100px;">
					<div id="boxHome" style="max-height: 250px;overflow: auto;border: 1px #ccc solid;background-color: white;
						min-width: 148px;border-top: none;position: relative;display: none;text-align: left;">
						<c:forEach items="${applicationScope.sysOrgList}" var="org">
							<p class="item" flow="${org.orgFlow}" value="${org.orgName}"
							   style="padding-left: 10px;">${org.orgName}</p>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="lastDiv" >
				<input type="button" class="searchInput"  onclick="toPage(1);" value="查&#12288;询">
			</div>
		</form>
		</div>
		<div id="userList" class="resultDiv">

		</div>
	</div>
</div>

</body>
</html>