<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
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
	#boxHome .item:HOVER{background-color: #eee;}
</style>
<script type="text/javascript">
function daoRu(){
	if(false==$("#excelForm").validationEngine("validate")){
		return false;
	}
	var checkFileFlag = $("#checkFileFlag").val();
	if('${GlobalConstant.FLAG_Y}'!=checkFileFlag){
		jboxTip("请上传Excel文件！");
		return false;
	}
	jboxStartLoading();
	var url = "<s:url value='/res/manager/importPerManageFromExcel'/>";
	jboxSubmit(
		$('#excelForm'),
		url,
		function(resp){
			top.jboxInfo(resp);
			if(resp.substring(0,5)=="${GlobalConstant.UPLOAD_SUCCESSED}"){
				window.parent.frames['mainIframe'].window.location.reload();
				top.jboxClose();
			}
		},
		function(resp){
			top.jboxEndLoading();
			top.jboxTip(resp);		
		},false);
}

function checkFile(file){
	var filePath = file.value;
	var suffix = filePath.substring(filePath.lastIndexOf(".")+1);
	if("xlsx" == suffix || "xls" == suffix){
		$("#checkFileFlag").val("${GlobalConstant.FLAG_Y}");
	}else{
		$("#checkFileFlag").val("${GlobalConstant.FLAG_N}");
		$(file).val(null);
		jboxTip("请上传Excel文件");
	}
}
</script>
</head>
<body>
<input type="hidden" id="checkFileFlag" name="checkFileFlag"/>
<form id="excelForm" method="post" enctype="multipart/form-data">
	<table class="basic" style="width: 100%;">
		<tr>
			<th>请选择导入</th>
			<td>
				<input id="file" type="file" name="file" onchange="checkFile(this);" class="validate[required]"/>
			</td>
		</tr>
		<tr>
		<th>请选择导入角色</th>
			<td>
				<c:if test="${!empty applicationScope.sysCfgMap['res_teacher_role_flow']}">
					<label style="width: 22%;display: inline-block;">
						<input type="checkbox" name="roles"  value="${applicationScope.sysCfgMap['res_teacher_role_flow'] }" />${!empty sysRoleMap[sysCfgMap['res_teacher_role_flow']]?sysRoleMap[sysCfgMap['res_teacher_role_flow']].roleName:'带教老师'}
					</label>
				</c:if>
				<c:if test="${!empty applicationScope.sysCfgMap['res_secretary_role_flow']}">
					<label style="width: 22%;display: inline-block;">
						<input type="checkbox" name="roles" value="${applicationScope.sysCfgMap['res_secretary_role_flow'] }" />${!empty sysRoleMap[sysCfgMap['res_secretary_role_flow']]?sysRoleMap[sysCfgMap['res_secretary_role_flow']].roleName:'科秘'}
					</label>
				</c:if>
				<c:if test="${!empty applicationScope.sysCfgMap['res_head_role_flow']}">
					<label style="width: 22%;display: inline-block;">
						<input type="checkbox" name="roles" value="${applicationScope.sysCfgMap['res_head_role_flow']}" />${!empty sysRoleMap[sysCfgMap['res_head_role_flow']]?sysRoleMap[sysCfgMap['res_head_role_flow']].roleName:'科主任'}
					</label>
				</c:if>
				<c:if test="${!empty applicationScope.sysCfgMap['res_manager_role_flow']}">
					<label style="width: 22%;display: inline-block;">
						<input type="checkbox" name="roles" class="box" id="${sysUser.userFlow}box" value="${applicationScope.sysCfgMap['res_manager_role_flow'] }"/>${!empty sysRoleMap[sysCfgMap['res_manager_role_flow']]?sysRoleMap[sysCfgMap['res_manager_role_flow']].roleName:'基地主任'}
					</label>
				</c:if>
				<c:if test="${!empty applicationScope.sysCfgMap['res_tutor_role_flow']}">
					<label style="width: 22%;display: inline-block;">
						<input type="checkbox"  name="roles" value="${applicationScope.sysCfgMap['res_tutor_role_flow']}" />${!empty sysRoleMap[sysCfgMap['res_tutor_role_flow']]?sysRoleMap[sysCfgMap['res_tutor_role_flow']].roleName:'导师'}
					</label>
				</c:if>
				<c:if test="${!empty applicationScope.sysCfgMap['res_responsible_teacher_role_flow']}">
					<label style="width: 22%;display: inline-block;">
						<input type="checkbox"  name="roles" value="${applicationScope.sysCfgMap['res_responsible_teacher_role_flow']}" />${!empty sysRoleMap[sysCfgMap['res_responsible_teacher_role_flow']]?sysRoleMap[sysCfgMap['res_responsible_teacher_role_flow']].roleName:'责任导师'}
					</label>
				</c:if>
				<c:if test="${!empty applicationScope.sysCfgMap['res_disciple_role_flow']}">
					<label style="width: 22%;display: inline-block;">
						<input type="checkbox"  name="roles" value="${applicationScope.sysCfgMap['res_disciple_role_flow']}" />${!empty sysRoleMap[sysCfgMap['res_disciple_role_flow']]?sysRoleMap[sysCfgMap['res_disciple_role_flow']].roleName:'师承老师'}
					</label>
				</c:if>
			</td>
		</tr>
		<tr>
			<th>模板文件</th>
			<td><a href="<s:url value='/jsp/res/manager/temeplete/importPerManage.xlsx'/>">人员管理导入模板.xlsx</a></td>
		</tr>
	</table>
</form>
	<div style="text-align: center; margin-top: 10px;">
		<input type="button" onclick="daoRu();" value="导&#12288;入" class="search"/>
		<input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();"/>
	</div>
</body>
</html>