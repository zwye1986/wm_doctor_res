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
		<jsp:param name="jquery_cxselect" value="false"/>
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
		/*#boxHome .item{line-height: normal;  vertical-align:middle; }*/
	</style>
	<script type="text/javascript">
		$(document).ready(function(){
			toPage(1);
		});
		function search(){
			jboxPostLoad("changeList" ,"<s:url value='/erp/crm/chargeUserChange/changeList'/>?role=${role}" ,$("#searchForm").serialize() , true);
		}
		function toPage(page) {
			if(page){
				$("#currentPage").val(page);
			}
			search();
		}

		function searchDeptUser(obj,type){
			var deptFlow=$(obj).val();
			var url="<s:url value='/erp/crm/searchDeptUserJsonNew'/>?deptFlow="+deptFlow;
			if(type=="")
			{
				url="<s:url value='/erp/crm/searchDeptUserJson'/>?deptFlow="+deptFlow;
			}
			$("#"+type+"ChargeUserFlow").empty();
			$("#"+type+"ChargeUser2Flow").empty();

			var option="<option value=''>请选择</option>";
			$("#"+type+"ChargeUserFlow").append(option);
			$("#"+type+"ChargeUser2Flow").append(option);
			if(deptFlow!=""){
				jboxPost(url,null,function(data){
					if(data!=null){
						for ( var i = 0; i < data.length; i++) {
							$("#"+type+"ChargeUserFlow").append('<option value="'+data[i].userFlow+'">'+data[i].userName+'</option>');
							$("#"+type+"ChargeUser2Flow").append('<option value="'+data[i].userFlow+'">'+data[i].userName+'</option>');
						}
					}
				},null,false);
			}
		}
		function audit(changeFlow,statusId){
			if(changeFlow=="")
			{
				jboxTip("请选择需要审核的变更申请！");
				return false;
			}
			if(statusId=="")
			{
				jboxTip("请选择审核通过或驳回！");
				return false;
			}
			var msg="确认审核";
			if(statusId=="${userChangeStatusEnumApplyBack.id}")
			{
				msg+="驳回";
			}else{
				msg+="通过";
			}
			msg+="此变更申请？";
			jboxConfirm(msg, function () {
				jboxPost("<s:url value='/erp/crm/chargeUserChange/audit'/>?changeFlow="+changeFlow+"&statusId="+statusId,null,function(data){
					var code=data.code;
					if(code==1)
					{
						jboxTip("审核成功");
						toPage($("#currentPage").val());
					}else{
						var msg="审核失败！"+data.msg;
						jboxTip(msg);
					}
				},null,false);
			}, null);
		}
		function add(changeFlow){
			jboxOpen("<s:url value='/erp/crm/chargeUserChange/add'/>?changeFlow="+changeFlow, "负责人变更申请",800,300);
		}
	</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<form  id="searchForm" method="post">
			<input id="currentPage" type="hidden" name="currentPage" value=""/>
			<table class="basic searchTable" style="width:100%;margin-top: 10px;">
				<tr>
					<td>
						<label>原合同负责部门：</label>
						<select name="oldSignDeptFlow" id="oldSignDeptFlow" class="xlselect" onchange="searchDeptUser(this,'old');" style="width:158px;">
							<option value="">请选择</option>
							<option value="f48a5060931147daa467eadbb5885629">总经办</option>
							<option value="a8a0aeef43c846c4a976589184b4ed8b">销售一部</option>
							<option value="a02a8190fc58440f8635b7c5cfcd1949">销售二部</option>
							<option value="d7f1afc58416446ba414d3c69bb204e2">销售三部</option>
							<option value="5b42e66a13e742d3a796f17d8104e02d">销售四部</option>
							<option value="1f56f216c3fd4dc4a1b23e7dda645c0b">销售五部</option>
						</select>
						<label>原合同负责人：</label>
						<select name="oldChargeUserFlow" id="oldChargeUserFlow" class="xlselect" style="width:158px;">
							<option value="">请选择</option>
						</select>
						<label>原合同负责人2：</label>
						<select name="oldChargeUser2Flow" id="oldChargeUser2Flow" class="xlselect" style="width:158px;">
							<option value="">请选择</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<label>变更为负责部门：</label>
						<select name="signDeptFlow" id="signDeptFlow" class="xlselect" onchange="searchDeptUser(this,'');" style="width:158px;">
							<option value="">请选择</option>
							<option value="f48a5060931147daa467eadbb5885629">总经办</option>
							<option value="a8a0aeef43c846c4a976589184b4ed8b">销售一部</option>
							<option value="a02a8190fc58440f8635b7c5cfcd1949">销售二部</option>
							<option value="d7f1afc58416446ba414d3c69bb204e2">销售三部</option>
							<option value="5b42e66a13e742d3a796f17d8104e02d">销售四部</option>
							<option value="1f56f216c3fd4dc4a1b23e7dda645c0b">销售五部</option>
						</select>
						<label>变更为负责人：</label>
						<select name="chargeUserFlow" id="ChargeUserFlow" class="xlselect" style="width:158px;">
							<option value="">请选择</option>
						</select>
						<label>变更为负责人2：</label>
						<select name="chargeUser2Flow" id="ChargeUser2Flow" class="xlselect" style="width:158px;">
							<option value="">请选择</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<label>&#12288;&#12288;&#12288;申请状态：</label>
						<select name="statusID" id="statusId" class="xlselect" style="width:158px;">
							<option value="">请选择</option>
							<c:forEach items="${userChangeStatusEnumList}" var="e">
								<option value="${e.id}">${e.name}</option>
							</c:forEach>
						</select>
						<c:if test="${role eq 'global'}">
							<label>&#12288;&#12288;&#12288;申请人：</label>
							<input type="text" name="applyUserName" class="xltext"  style="width: 150px;">
						</c:if>
						<c:if test="${role eq 'global'}">
							<label>&#12288;&#12288;&#12288;&nbsp; 审核人：</label>
							<input type="text" name="auditUserName" class="xltext"  style="width: 150px;">
						</c:if>
						&#12288;
						<input type="button" value="查&#12288;询" class="search" onclick="toPage(1);"/>
						<c:if test="${role ne 'global'}">
							&#12288;
							<input type="button" value="新增申请" class="search" onclick="add('')"/>
						</c:if>
					</td>
				</tr>
			</table>
		</form>
		<div id="changeList">

		</div>
	</div>
</div>
</body>
</html>