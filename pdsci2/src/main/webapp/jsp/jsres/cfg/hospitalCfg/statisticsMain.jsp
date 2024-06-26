<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_ui_tooltip" value="true"/>
		<jsp:param name="jquery_validation" value="true"/>
		<jsp:param name="jquery_datePicker" value="true"/>
		<jsp:param name="jquery_fixedtableheader" value="true"/>
		<jsp:param name="jquery_placeholder" value="true"/>
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
				 clickActive:function(flow){
                     $("#orgFlow").val(flow);
				 }
			});
		});

		$(document).ready(function(){
			toPage(1);
		});

		function search(){
			jboxPostLoad("hospitalList" ,"<s:url value='/jsres/hospitalCfg/statisticsList'/>" ,$("#doctorSearchForm").serialize() , true);
		}
		function toPage(page) {
			if(page){
				$("#currentPage").val(page);
			}
			search();
		}
		
		function exportStatistics() {
            jboxExp($("#doctorSearchForm"),"<s:url value='/jsres/hospitalCfg/exportStatistics'/>");
        }

	</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<form  id="doctorSearchForm" method="post">
			<input id="currentPage" type="hidden" name="currentPage" value=""/>
			<table class="basic searchTable" style="width:100%;margin-top: 10px;">
				<tr>
					<td>
						培训基地：
						<input id="orgSel" class="toggleView xltext" type="text" name="orgName" value="${param.orgName}" autocomplete="off"/>
						<div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:30px;left: 69px;">
							<div id="boxHome" style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">
								<p class="item trainOrg allOrg orgs" flow="" value="全部" type="AllOrgP" style="line-height: 20px; padding:10px 0;cursor: default; ">全部</p>
								<c:forEach items="${allSysOrgList}" var="org">
									<p class="item" flow="${org.orgFlow}" value="${org.orgName}"style="line-height: 20px; padding:10px 0;cursor: default; ">${org.orgName}</p>
								</c:forEach>
							</div>
							<input type="text" name="orgFlow" id="orgFlow" value="" style="display: none;"/>
						</div>
					</td>
					<td>
						<div class="inputDiv">
							年&#12288;&#12288;级：
							<input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" class="input"
								   style="height: 23px"
								   onClick="WdatePicker({dateFmt:'yyyy'})"  readonly="readonly" />
						</div>
					</td>
					<td>
						<div class="doctorTypeDiv" style="max-width: 420px;min-width: 235px;">
							培训类别：
							<select name="trainingTypeId" id="trainingTypeId" class="qselect">
								<option value="">请选择</option>
								<c:forEach items="${trainCategoryEnumList}" var="trainCategory">
									<option value="${trainCategory.id}"
											<c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>
										<%--<c:if test="${trainCategoryEnumWMFirst.id ==trainCategory.id}">style="display: none;"</c:if>--%>
										<%--<c:if test="${trainCategoryEnumWMSecond.id ==trainCategory.id}">style="display: none;"</c:if>--%>
									>${trainCategory.name}</option>
								</c:forEach>
							</select>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="doctorTypeDiv">
							<div class="doctorTypeContent">
								人员类型：
								<c:forEach items="${dictTypeEnumDoctorTypeList}" var="type">
									<label><input name="datas" class="docType" type="checkbox" checked  value="${type.dictId}"/>${type.dictName}&nbsp;</label>
								</c:forEach>
							</div>
						</div>
					</td>
					<td>
						<input type="button" value="查&#12288;询" class="search" onclick="toPage(1);"/>&#12288;
						<input type="button" value="导&#12288;出" class="search" onclick="exportStatistics();"/>&#12288;
					</td>
				</tr>
			</table>
		</form>
		<div id="hospitalList">

		</div>
	</div>
</div>
</body>
</html>