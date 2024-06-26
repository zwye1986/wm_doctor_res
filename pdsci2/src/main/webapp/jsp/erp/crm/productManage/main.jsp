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
	<script type="text/javascript">
		$(document).ready(function(){
			toPage(1);
		});
		function search(){
			jboxPostLoad("list" ,"<s:url value='/erp/crm/productManage/list'/>" ,$("#searchForm").serialize() , true);
		}
		function toPage(page) {
			if(page){
				$("#currentPage").val(page);
			}
			search();
		}
		function add(manageFlow)
		{
			if(manageFlow) {
				var url = "<s:url value='/erp/crm/productManage/checkStatus'/>?manageFlow="+manageFlow;
				jboxPost(url,null,function(resp){
					if(resp=="1")
					{
						jboxOpen("<s:url value='/erp/crm/productManage/add'/>?manageFlow=" + manageFlow, "编辑项目信息", 600, 300);
					}else{
						jboxTip(resp);
					}
				},null,false);
			}else{
				jboxOpen("<s:url value='/erp/crm/productManage/add'/>?manageFlow=" + manageFlow, "新增项目信息", 600, 300);
			}
		}
		function completeProduct(manageFlow,type)
		{
			var msg="项目跟进";
			if(type=='Complete')
			{
				msg="项目完成";
			}
			var url = "<s:url value='/erp/crm/productManage/checkStatus2'/>?manageFlow="+manageFlow;
			jboxPost(url,null,function(resp){
				if(resp=="1")
				{
					jboxOpen("<s:url value='/erp/crm/productManage/completeProduct'/>?type="+type+"&manageFlow=" + manageFlow, msg, 600, 300);
				}else{
					jboxTip(resp);
				}
			},null,false);
		}
		function manageStart(manageFlow)
		{
			var url = "<s:url value='/erp/crm/productManage/checkStatus'/>?manageFlow="+manageFlow;
			jboxPost(url,null,function(resp){
				if(resp=="1")
				{
					jboxConfirm("确认启动此项目，启动后无法修改？",function(){
						var url = "<s:url value='/erp/crm/productManage/manageStart'/>?manageFlow="+manageFlow;
						jboxPost(url,null,function(resp){
							if(resp=="1")
							{
								jboxTip("项目启动成功！");
								toPage(1);
							}else {
								jboxTip(resp);
							}
						},null,false);
					},null);
				}else{
					jboxTip(resp);
				}
			},null,false);
		}
		function del(manageFlow)
		{
			var url = "<s:url value='/erp/crm/productManage/checkStatus'/>?manageFlow="+manageFlow;
			jboxPost(url,null,function(resp){
				if(resp=="1")
				{
					jboxConfirm("确认删除此项目？",function(){
						var url = "<s:url value='/erp/crm/productManage/delManage'/>?manageFlow="+manageFlow;
						jboxPost(url,null,function(resp){
							if(resp=="1")
							{
								jboxTip("项目删除成功！");
								toPage(1);
							}else {
								jboxTip(resp);
							}
						},null,false);
					},null);
				}else{
					jboxTip(resp);
				}
			},null,false);
		}
		function productProcessList(manageFlow)
		{
			jboxOpen("<s:url value='/erp/crm/productManage/productProcessList'/>?manageFlow="+manageFlow,"项目过程信息", 600, 500);
		}
	</script>
</head>
<body>
<div class="mainright">
	<div class="content" id="provCityAreaId">
		<form  id="searchForm" method="post">
			<input type="hidden" id="currentPage" name="currentPage"/>
			<input id="" type="hidden" name="type" value="${type}"/>
			<table class="basic searchTable" style="width:100%;margin-top: 10px;">
				<tr>
					<td>
						<label>&#12288;项目名称：</label>
						<input type="text"  name="productName" class="xltext" autocomplete="off" style="width:120px;"/>
						<label>&#12288;客户名称：</label>
						<input type="text"  name="customerName" autocomplete="off" class="xltext" style="width:120px;"/>
						<label>&#12288;立项时间：</label>
						<input type="text" name="approvalTime" class="ctime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width:120px;"/>
						<c:if test="${type eq 'add' or type eq 'query'}">
							<label>&#12288;&#12288;参与人：</label>
							<input type="text"  name="userName" autocomplete="off" class="xltext" style="width:120px;"/>
						</c:if>
						<c:if test="${type eq 'process'}">
							<label>&#12288;&#12288;立项人：</label>
							<input type="text"  name="approvalUserName" autocomplete="off" class="xltext" style="width:120px;"/>
						</c:if>
					</td>
				</tr>
				<tr>
					<td>
						<label>预计完成日：</label>
						<input type="text" name="etcTime" class="ctime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width:120px;"/>
						<label>实际完成日：</label>
						<input type="text" name="completeTime" class="ctime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width:120px;"/>
						<label>&#12288;项目状态：</label>
						<select name="statusId" class="xlselect" style="width:128px;">
							<option value=""></option>
							<option value="NotStart">未开始</option>
							<option value="Processing">进行中</option>
							<option value="Complete">完成</option>
						</select>&#12288;
						<c:if test="${ type eq 'query'}">
							<label>&#12288;立项人：</label>
							<input type="text"  name="approvalUserName" autocomplete="off" class="xltext" style="width:120px;"/>
						</c:if>
						<input type="button" class="search"  onclick="search();" value="查&#12288;询">
						<c:if test="${type eq 'add'}">
							<input type="button" class="search"  onclick="add('');" value="新&#12288;增">
						</c:if>
					</td>
				</tr>
			</table>
		</form>
		<div id="list">

		</div>
	</div>
</div>

</body>
</html>