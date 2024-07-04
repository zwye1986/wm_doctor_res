
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

<script type="text/javascript">
function single(box){
    var curr=box.checked;
 	if(curr){
		var name=box.name;
		$(":checkbox[name='"+name+"']").attr("checked",false);
	}
	  box.checked = curr;
  }
function save(){
	if($("#evaluationForm").validationEngine("validate")){
		jboxConfirm("确认保存？保存之后不可修改！",function(){
		autoValue($("#evaluationForm"),"autoValue");
		jboxPost("<s:url value='/res/rec/saveRegistryFormNew'/>",$("#evaluationForm").serialize(),function(resp){
			if(resp=="${GlobalConstant.SAVE_SUCCESSED}"){	
				window.parent.document.mainIframe.location.reload();
			   jboxClose();
			}				
		},null,true);
		});
	}
}
</script>
</head>
<body>	
   <div class="mainright">
      <div class="content">
			<form id="evaluationForm">
				<input type="hidden" name="formFileName" value="${formFileName}"/>
				<input type="hidden" name="schDeptFlow" value="${param.schDeptFlow}"/>
				<input type="hidden" name="operUserFlow" value="${param.operUserFlow}"/>
				<input type="hidden" name="roleFlag" value="${roleFlag}"/>
				<input type="hidden" name="recFlow" value="${rec.recFlow}"/>
				<input type="hidden" name="processFlow" value="${currRegProcess.processFlow}"/>
				<label style="margin-bottom: 10px;">
					姓名：
					<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
						<input type="text" class="inputText"  name="name" value="${empty formDataMap['name']?doctor.doctorName:formDataMap['name']}"/>
					</c:if>	
					<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
						${formDataMap['name']}
						<input type="hidden" class="inputText"  name="name" value="${formDataMap['name']}"/>
					</c:if>
				</label>
				<p style="float: right; margin-bottom: 10px;" >届别：
				<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
					<input type="text" class="inputText"  name="sessional" value="${formDataMap['sessional']}"/>&#12288;
				</c:if>
				<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
					${formDataMap['sessional']}
					<input type="hidden" class="inputText"  name="sessional" value="${formDataMap['sessional']}"/>&#12288;
				</c:if>					
				&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;
					培训专业：
					<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
						<input type="text" class="inputText"  name="trainMajor" value="${formDataMap['trainMajor']}"/>
					</c:if>
					<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
						${formDataMap['trainMajor']}
						<input type="hidden" class="inputText"  name="trainMajor" value="${formDataMap['trainMajor']}"/>
					</c:if>
					
				</p>
				<table class="basic" width="100%" style="margin-top: 10px;">
					<tr>
						<td>轮转科室名称：</td>
						<td>
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText"  name="deptName" value="${formDataMap['deptName']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								${formDataMap['deptName']}
								<input type="hidden" class="inputText"  name="deptName" value="${formDataMap['deptName']}"/>
							</c:if>
						</td>
						<td>轮转时间：</td>
						<td>
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" name="cycleTimeQ" value="${formDataMap['cycleTimeQ']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								${formDataMap['cycleTimeQ']}
								<input type="hidden" class="inputText" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" name="cycleTimeQ" value="${formDataMap['cycleTimeQ']}"/>
							</c:if>
							至
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" name="cycleTimeH" value="${formDataMap['cycleTimeH']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								${formDataMap['cycleTimeH']}
								<input type="hidden" class="inputText"  name="trainMajor" value="${formDataMap['trainMajor']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td>考勤</td>
						<td colspan="3">
						
							全勤：
								<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
									<input type="text" class="inputText" style="width: 70px;" name="attendance" value="${formDataMap['attendance']}"/>天；
								</c:if>
								<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
									${formDataMap['attendance']}
									<input type="hidden" class="inputText" style="width: 70px;" name="attendance" value="${formDataMap['attendance']}"/>天；
								</c:if>
								&#12288;
							事假：
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText" style="width: 70px;" name="leave" value="${formDataMap['leave']}"/>天；&#12288;
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								${formDataMap['leave']}
								<input type="hidden" class="inputText" style="width: 70px;" name="leave" value="${formDataMap['leave']}"/>天；&#12288;
							</c:if>
							病假：
								<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
									<input type="text" class="inputText" style="width: 70px;" name="sickLeave" value="${formDataMap['sickLeave']}"/>天；
								</c:if>
								<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
									${formDataMap['sickLeave']}
									<input type="hidden" class="inputText" style="width: 70px;" name="sickLeave" value="${formDataMap['sickLeave']}"/>天；
								</c:if>
								&#12288;
							产假：
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText" style="width: 70px;" name="materLeave" value="${formDataMap['materLeave']}"/>天；&#12288;
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								${formDataMap['materLeave']}
								<input type="hidden" class="inputText" style="width: 70px;" name="materLeave" value="${formDataMap['materLeave']}"/>天；&#12288;
							</c:if>
							旷工：
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText" style="width: 70px;" name="absenteeism" value="${formDataMap['absenteeism']}"/>天；
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								${formDataMap['absenteeism']}
								<input type="hidden" class="inputText" style="width: 70px;" name="absenteeism" value="${formDataMap['absenteeism']}"/>天；
							</c:if>
						</td>
					</tr>
					<tr>
						<td colspan="2">评价内容</td>
						<td colspan="2">评价结果</td>
					</tr>
					<tr>
						<td rowspan="5">医德医风人际沟通团队合作</td>
						<td>遵守国家法律法规、医院规章制度</td>
						<td colspan="2">
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<label><input type="checkbox"  onchange="single(this)" name="zsgjflfg" value="1" <c:if test="${formDataMap['zsgjflfg_id']=='1'}">checked</c:if> class="autoValue"/>&#12288;优&#12288;&#12288;&#12288;</label>
								<label><input type="checkbox"  onchange="single(this)" name="zsgjflfg" value="2" <c:if test="${formDataMap['zsgjflfg_id']=='2'}">checked</c:if> class="autoValue"/>&#12288;良&#12288;&#12288;&#12288;&#12288;</label>
								<label><input type="checkbox"  onchange="single(this)" name="zsgjflfg" value="3" <c:if test="${formDataMap['zsgjflfg_id']=='3'}">checked</c:if> class="autoValue"/>&#12288;中&#12288;&#12288;&#12288;&#12288;</label>
								<label><input type="checkbox"  onchange="single(this)" name="zsgjflfg" value="4" <c:if test="${formDataMap['zsgjflfg_id']=='4'}">checked</c:if> class="autoValue"/>&#12288;差&#12288;&#12288;&#12288;</label>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
							<c:if test="${!empty formDataMap['zsgjflfg']}">
									${formDataMap['zsgjflfg']}
							</c:if>
								<input type="hidden" name="zsgjflfg" value="${formDataMap['zsgjflfg']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td>履行岗位职责</td>
						<td colspan="2">
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<label><input type="checkbox" onchange="single(this)" name="lxgwzz" value="1" <c:if test="${formDataMap['lxgwzz_id']=='1'}">checked</c:if> class="autoValue"/>&#12288;优&#12288;&#12288;&#12288;</label>
								<label><input type="checkbox" onchange="single(this)" name="lxgwzz" value="2" <c:if test="${formDataMap['lxgwzz_id']=='2'}">checked</c:if> class="autoValue"/>&#12288;良&#12288;&#12288;&#12288;&#12288;</label>
								<label><input type="checkbox" onchange="single(this)" name="lxgwzz" value="3" <c:if test="${formDataMap['lxgwzz_id']=='3'}">checked</c:if> class="autoValue"/>&#12288;中&#12288;&#12288;&#12288;&#12288;</label>
								<label><input type="checkbox" onchange="single(this)" name="lxgwzz" value="4" <c:if test="${formDataMap['lxgwzz_id']=='4'}">checked</c:if> class="autoValue"/>&#12288;差&#12288;&#12288;&#12288;</label>
							</c:if>	
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
							<c:if test="${!empty formDataMap['lxgwzz']}">
									${formDataMap['lxgwzz']}
								</c:if>
								<input type="hidden" name="lxgwzz" value="${formDataMap['lxgwzz']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td>以病人为中心，体现人文关怀</td>
						<td colspan="2">
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<label><input type="checkbox" onchange="single(this)" name="ybrwzx" value="1" <c:if test="${formDataMap['ybrwzx_id']=='1'}">checked</c:if> class="autoValue"/>&#12288;优&#12288;&#12288;&#12288;</label>
								<label><input type="checkbox" onchange="single(this)" name="ybrwzx" value="2" <c:if test="${formDataMap['ybrwzx_id']=='2'}">checked</c:if> class="autoValue"/>&#12288;良&#12288;&#12288;&#12288;&#12288;</label>
								<label><input type="checkbox" onchange="single(this)" name="ybrwzx" value="3" <c:if test="${formDataMap['ybrwzx_id']=='3'}">checked</c:if> class="autoValue"/>&#12288;中&#12288;&#12288;&#12288;&#12288;</label>
								<label><input type="checkbox" onchange="single(this)" name="ybrwzx" value="4" <c:if test="${formDataMap['ybrwzx_id']=='4'}">checked</c:if> class="autoValue"/>&#12288;差&#12288;&#12288;&#12288;</label>
							</c:if>
								<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<c:if test="${!empty formDataMap['ybrwzx']}">
									${formDataMap['ybrwzx']}
								</c:if>
								<input type="hidden" name="lxgwzz" value="${formDataMap['ybrwzx']}"/>
								</c:if>
						</td>
					</tr>
					<tr>
						<td>人际（医患）沟通和表达能力</td>
						<td colspan="2">
								<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<label><input type="checkbox" onchange="single(this)" name="rjgtbdnl" value="1" <c:if test="${formDataMap['rjgtbdnl_id']=='1'}">checked</c:if> class="autoValue"/>&#12288;强&#12288;&#12288;&#12288;</label>
								<label><input type="checkbox" onchange="single(this)" name="rjgtbdnl" value="2" <c:if test="${formDataMap['rjgtbdnl_id']=='2'}">checked</c:if> class="autoValue"/>&#12288;较强&#12288;&#12288;&#12288;</label>
								<label><input type="checkbox" onchange="single(this)" name="rjgtbdnl" value="3" <c:if test="${formDataMap['rjgtbdnl_id']=='3'}">checked</c:if> class="autoValue"/>&#12288;一般&#12288;&#12288;&#12288;</label>
								<label><input type="checkbox" onchange="single(this)" name="rjgtbdnl" value="4" <c:if test="${formDataMap['rjgtbdnl_id']=='4'}">checked</c:if> class="autoValue"/>&#12288;差&#12288;&#12288;&#12288;</label>
								</c:if>
								<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<c:if test="${!empty formDataMap['rjgtbdnl']}">
									${formDataMap['rjgtbdnl']}
								</c:if>
								<input type="hidden" name="lxgwzz" value="${formDataMap['rjgtbdnl']}"/>
								</c:if>
						</td>
					</tr>
					<tr>
						<td>团结协作精神</td>
						<td colspan="2">
								<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
									<label><input type="checkbox" onchange="single(this)" name="tjxzjsxm" value="1" <c:if test="${formDataMap['tjxzjsxm_id']=='1'}">checked</c:if> class="autoValue"/>&#12288;优&#12288;&#12288;&#12288;</label>
									<label><input type="checkbox" onchange="single(this)" name="tjxzjsxm" value="2" <c:if test="${formDataMap['tjxzjsxm_id']=='2'}">checked</c:if> class="autoValue"/>&#12288;良&#12288;&#12288;&#12288;&#12288;</label>
									<label><input type="checkbox" onchange="single(this)" name="tjxzjsxm" value="3" <c:if test="${formDataMap['tjxzjsxm_id']=='3'}">checked</c:if> class="autoValue"/>&#12288;中&#12288;&#12288;&#12288;&#12288;</label>
									<label><input type="checkbox" onchange="single(this)" name="tjxzjsxm" value="4" <c:if test="${formDataMap['tjxzjsxm_id']=='4'}">checked</c:if> class="autoValue"/>&#12288;差&#12288;&#12288;&#12288;</label>
								</c:if>
								<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
									<c:if test="${!empty formDataMap['tjxzjsxm']}">
										${formDataMap['tjxzjsxm']}
									</c:if>
									<input type="hidden" name="lxgwzz" value="${formDataMap['tjxzjsxm']}"/>
								</c:if>
						</td>
					</tr>
						<tr>
						<td rowspan="5">临床综合能力作</td>
						<td>临床基本知识、基本理论掌握程度</td>
						<td colspan="2">
								<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<label><input type="checkbox" onchange="single(this)" name="jbllzwcd" value="1" <c:if test="${formDataMap['jbllzwcd_id']=='1'}">checked</c:if> class="autoValue"/>&#12288;强&#12288;&#12288;&#12288;</label>
								<label><input type="checkbox" onchange="single(this)" name="jbllzwcd" value="2" <c:if test="${formDataMap['jbllzwcd_id']=='2'}">checked</c:if> class="autoValue"/>&#12288;较强&#12288;&#12288;&#12288;</label>
								<label><input type="checkbox" onchange="single(this)" name="jbllzwcd" value="3" <c:if test="${formDataMap['jbllzwcd_id']=='3'}">checked</c:if> class="autoValue"/>&#12288;一般&#12288;&#12288;&#12288;</label>
								<label><input type="checkbox" onchange="single(this)" name="jbllzwcd" value="4" <c:if test="${formDataMap['jbllzwcd_id']=='4'}">checked</c:if> class="autoValue"/>&#12288;差&#12288;&#12288;&#12288;</label>
								</c:if>
								<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
									<c:if test="${!empty formDataMap['jbllzwcd']}">
										${formDataMap['jbllzwcd']}
									</c:if>
								</c:if>
								<input type="hidden" name="lxgwzz" value="${formDataMap['jbllzwcd']}"/>
						</td>
					</tr>
					<tr>
						<td>临床基本技能掌握程度</td>
						<td colspan="2">
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<label><input type="checkbox" onchange="single(this)" name="njbjnzwcd" value="1" <c:if test="${formDataMap['njbjnzwcd_id']=='1'}">checked</c:if> class="autoValue"/>&#12288;强&#12288;&#12288;&#12288;</label>
								<label><input type="checkbox" onchange="single(this)" name="njbjnzwcd" value="2" <c:if test="${formDataMap['njbjnzwcd_id']=='2'}">checked</c:if> class="autoValue"/>&#12288;较强&#12288;&#12288;&#12288;</label>
								<label><input type="checkbox" onchange="single(this)" name="njbjnzwcd" value="3" <c:if test="${formDataMap['njbjnzwcd_id']=='3'}">checked</c:if> class="autoValue"/>&#12288;一般&#12288;&#12288;&#12288;</label>
								<label><input type="checkbox" onchange="single(this)" name="njbjnzwcd" value="4" <c:if test="${formDataMap['njbjnzwcd_id']=='4'}">checked</c:if> class="autoValue"/>&#12288;差&#12288;&#12288;&#12288;</label>
								</c:if>
								<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<c:if test="${!empty formDataMap['njbjnzwcd']}">
									${formDataMap['njbjnzwcd']}
								</c:if>
								<input type="hidden" name="lxgwzz" value="${formDataMap['njbjnzwcd']}"/>
								</c:if>
						</td>
					</tr>
					<tr>
						<td>临床思维能力</td>
						<td colspan="2">
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<label><input type="checkbox" onchange="single(this)" name="lcswnl" value="1" <c:if test="${formDataMap['lcswnl_id']=='1'}">checked</c:if> class="autoValue"/>&#12288;强&#12288;&#12288;&#12288;</label>
								<label><input type="checkbox" onchange="single(this)" name="lcswnl" value="2" <c:if test="${formDataMap['lcswnl_id']=='2'}">checked</c:if> class="autoValue"/>&#12288;较强&#12288;&#12288;&#12288;</label>
								<label><input type="checkbox" onchange="single(this)" name="lcswnl" value="3" <c:if test="${formDataMap['lcswnl_id']=='3'}">checked</c:if> class="autoValue"/>&#12288;一般&#12288;&#12288;&#12288;</label>
								<label><input type="checkbox" onchange="single(this)" name="lcswnl" value="4" <c:if test="${formDataMap['lcswnl_id']=='4'}">checked</c:if> class="autoValue"/>&#12288;差&#12288;&#12288;&#12288;</label>
								</c:if>
								<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
									<c:if test="${!empty formDataMap['lcswnl']}">
										${formDataMap['lcswnl']}
									</c:if>
								</c:if>
								<input type="hidden" name="lxgwzz" value="${formDataMap['lcswnl']}"/>
						</td>
					</tr>
					<tr>
						<td>临床诊疗能力</td>
						<td colspan="2">
								<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
									<label><input type="checkbox" onchange="single(this)" name="lczlnl" value="1" <c:if test="${formDataMap['lczlnl_id']=='1'}">checked</c:if> class="autoValue"/>&#12288;强&#12288;&#12288;&#12288;</label>
									<label><input type="checkbox" onchange="single(this)" name="lczlnl" value="2" <c:if test="${formDataMap['lczlnl_id']=='2'}">checked</c:if> class="autoValue"/>&#12288;较强&#12288;&#12288;&#12288;</label>
									<label><input type="checkbox" onchange="single(this)" name="lczlnl" value="3" <c:if test="${formDataMap['lczlnl_id']=='3'}">checked</c:if> class="autoValue"/>&#12288;一般&#12288;&#12288;&#12288;</label>
									<label><input type="checkbox" onchange="single(this)" name="lczlnl" value="4" <c:if test="${formDataMap['lczlnl_id']=='4'}">checked</c:if> class="autoValue"/>&#12288;差&#12288;&#12288;&#12288;</label>
								</c:if>
								<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
									<c:if test="${!empty formDataMap['lczlnl']}">
										${formDataMap['lczlnl']}
									</c:if>
									<input type="hidden" name="lxgwzz" value="${formDataMap['lczlnl']}"/>
								</c:if>
						</td>
					</tr>
					<tr>
						<td>危重病人的识别及紧急处理能力</td>
						<td colspan="2">
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
							<label><input type="checkbox" onchange="single(this)" name="jjclnl" value="1" <c:if test="${formDataMap['jjclnl_id']=='1'}">checked</c:if> class="autoValue"/>&#12288;优&#12288;&#12288;&#12288;</label>
							<label><input type="checkbox" onchange="single(this)" name="jjclnl" value="2" <c:if test="${formDataMap['jjclnl_id']=='2'}">checked</c:if> class="autoValue"/>&#12288;良&#12288;&#12288;&#12288;&#12288;</label>
							<label><input type="checkbox" onchange="single(this)" name="jjclnl" value="3" <c:if test="${formDataMap['jjclnl_id']=='3'}">checked</c:if> class="autoValue"/>&#12288;中&#12288;&#12288;&#12288;&#12288;</label>
							<label><input type="checkbox" onchange="single(this)" name="jjclnl" value="4" <c:if test="${formDataMap['jjclnl_id']=='4'}">checked</c:if> class="autoValue"/>&#12288;差&#12288;&#12288;&#12288;</label>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<c:if test="${!empty formDataMap['jjclnl']}">
										${formDataMap['jjclnl']}
									</c:if>
								<input type="hidden" name="lxgwzz" value="${formDataMap['jjclnl']}"/>	
							</c:if>
						</td>
					</tr>
					<tr>
						<td rowspan="4">临床实践指标完成情况</td>
						<td colspan="3">
							<div style="text-align: center;">
							完成病历数：应完成
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText" style="width: 70px;" name="blsywc" value="${formDataMap['blsywc']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								${formDataMap['blsywc']}
								<input type="hidden" class="inputText" style="width: 70px;" name="blsywc" value="${formDataMap['blsywc']}"/>
							</c:if>
							例，
							已完成
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText" style="width: 70px;" name="blsyjwc" value="${formDataMap['blsyjwc']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								${formDataMap['blsyjwc']}
								<input type="hidden" class="inputText" style="width: 70px;" name="blsyjwc" value="${formDataMap['blsyjwc']}"/>
							</c:if>
							例，
							完成比例
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText" style="width: 70px;" name="blswcbl" value="${formDataMap['blswcbl']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								${formDataMap['blswcbl']}
								<input type="hidden" class="inputText" style="width: 70px;" name="blswcbl" value="${formDataMap['blswcbl']}"/>
							</c:if>
							%
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="3">
						<div style="text-align: center;">
							管理病种数：应完成
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText" style="width: 70px;" name="bzsywc" value="${formDataMap['bzsywc']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								${formDataMap['bzsywc']}
								<input type="hidden" class="inputText" style="width: 70px;" name="bzsywc" value="${formDataMap['bzsywc']}"/>
							</c:if>
							例，
							已完成
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText" style="width: 70px;" name="bzsyjwc" value="${formDataMap['bzsyjwc']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								${formDataMap['bzsyjwc']}
								<input type="hidden" class="inputText" style="width: 70px;" name="bzsyjwc" value="${formDataMap['bzsyjwc']}"/>
							</c:if>
							例，
							完成比例
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText" style="width: 70px;" name="bzswcbl" value="${formDataMap['bzswcbl']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								${formDataMap['bzswcbl']}
								<input type="hidden" class="inputText" style="width: 70px;" name="bzswcbl" value="${formDataMap['bzswcbl']}"/>
							</c:if>
							%
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="3">
						<div style="text-align: center;">
							完成操作数：应完成
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText" style="width: 70px;" name="czsywc" value="${formDataMap['czsywc']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								${formDataMap['czsywc']}
								<input type="hidden" class="inputText" style="width: 70px;" name="czsywc" value="${formDataMap['czsywc']}"/>
							</c:if>
							例，
							已完成
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText" style="width: 70px;" name="czsyjwc" value="${formDataMap['czsyjwc']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								${formDataMap['czsyjwc']}
								<input type="hidden" class="inputText" style="width: 70px;" name="czsyjwc" value="${formDataMap['czsyjwc']}"/>
							</c:if>
							例，
							完成比例
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText" style="width: 70px;" name="czswcbl" value="${formDataMap['czswcbl']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								${formDataMap['czswcbl']}
								<input type="hidden" class="inputText" style="width: 70px;" name="czswcbl" value="${formDataMap['czswcbl']}"/>
							</c:if>
							%
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="3">
						<div style="text-align: center;">
							参加手术数：应完成
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText" style="width: 70px;" name="sssywc" value="${formDataMap['sssywc']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								${formDataMap['sssywc']}
								<input type="hidden" class="inputText" style="width: 70px;" name="sssywc" value="${formDataMap['sssywc']}"/>
							</c:if>
							例，
							已完成
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText" style="width: 70px;" name="sssyjwc" value="${formDataMap['sssyjwc']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								${formDataMap['sssyjwc']}
								<input type="hidden" class="inputText" style="width: 70px;" name="sssyjwc" value="${formDataMap['sssyjwc']}"/>
							</c:if>
							例，
							完成比例
								<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
									<input type="text" class="inputText" style="width: 70px;" name="ssswcbl" value="${formDataMap['ssswcbl']}"/>
								</c:if>
								<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
									${formDataMap['ssswcbl']}
									<input type="hidden" class="inputText" style="width: 70px;" name="ssswcbl" value="${formDataMap['ssswcbl']}"/>
								</c:if>
							%
							</div>
						</td>
					</tr>
					<tr>
						<td rowspan="2">参加各种形式活动</td>
						<td colspan="3">
							教学查房：
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText" style="width: 70px;" name="jxcb" value="${formDataMap['jxcb']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								${formDataMap['jxcb']}
								<input type="hidden" class="inputText" style="width: 70px;" name="jxcb" value="${formDataMap['jxcb']}"/>
							</c:if>
							次                 
							&#12288;
							<label style="float: right; margin-right: 20px;">
							 疑难、危重病例讨论：
							 <c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								 <input type="text" class="inputText" style="width: 70px;" name="nwzbltl" value="${formDataMap['nwzbltl']}"/>
							 </c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
							 	${formDataMap['nwzbltl']}
							 	<input type="hidden" class="inputText" style="width: 70px;" name="nwzbltl" value="${formDataMap['nwzbltl']}"/>
							 </c:if>
							 次 
							 </label>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							学术讲座：
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText" style="width: 70px;" name="xsjz" value="${formDataMap['xsjz']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								${formDataMap['xsjz']}
								<input type="hidden" class="inputText" style="width: 70px;" name="xsjz" value="${formDataMap['xsjz']}"/>
							</c:if>
							次                  
							&#12288;
								<label style="float: right; margin-right: 20px;">
							  死亡病例讨论：
							  <c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
							  	<input type="text" class="inputText" style="width: 70px;" name="swbltl" value="${formDataMap['swbltl']}"/>
							  </c:if>
							  <c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
							  	${formDataMap['swbltl']}
							  	<input type="hidden" class="inputText" style="width: 70px;" name="swbltl" value="${formDataMap['swbltl']}"/>
							  </c:if>
							 次 
							 </label>
						</td>
					</tr>
					<tr>
						<td rowspan="2">出科考核</td>
						<td colspan="3">
							理论成绩：
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText validate[required]" style="width: 70px;" name="theoreResult" value="${formDataMap['theoreResult']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								${formDataMap['theoreResult']}
								<input type="hidden" class="inputText validate[required]" style="width: 70px;" name="theoreResult" value="${formDataMap['theoreResult']}"/>
							</c:if>
							分                
						</td>
					</tr>
					<tr>
						<td colspan="3">
							技能考核名称：
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText"  name="skillName" value="${formDataMap['skillName']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								${formDataMap['skillName']}
								<input type="hidden" class="inputText" name="skillName" value="${formDataMap['skillName']}"/>
							</c:if>
							&#12288;
							得分：
								<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
									<input type="text" class="inputText" style="width: 70px;" name="score" value="${formDataMap['score']}"/>
								</c:if>
								<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
									${formDataMap['score']}
									<input type="hidden" class="inputText" style="width: 70px;" name="score" value="${formDataMap['score']}"/>
								</c:if>
							 分； 
							 &#12288;
							 考官1：
							 <c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
							 	<input type="text" class="inputText" style="width: 70px;" name="examiner1" value="${formDataMap['examiner1']}"/>
							 </c:if>
							 <c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
							 	${formDataMap['examiner1']}
							 	<input type="hidden" class="inputText" style="width: 70px;" name="examiner1" value="${formDataMap['examiner1']}"/>
							 </c:if>
							  &#12288;
							 考官2：
							 <c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
							 	<input type="text" class="inputText" style="width: 70px;" name="examiner2" value="${formDataMap['examiner2']}"/>
							 </c:if>
							  <c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
							  	${formDataMap['examiner2']}
							  	<input type="hidden" class="inputText" style="width: 70px;" name="examiner2" value="${formDataMap['examiner2']}"/>
							  </c:if>
						</td>
					</tr>
					<tr>
						<td>所在科室考核小组总体评价</td>
						<td colspan="3">
						<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
							<label><input type="checkbox"  name="szkskhxzztpj" onchange="single(this)" value="1" <c:if test="${formDataMap['szkskhxzztpj']=='1'}">checked</c:if> class="autoValue"/>&#12288;通过 &#12288;&#12288;&#12288;</label>
							 &#12288;
							<label><input type="checkbox"  name="szkskhxzztpj" onchange="single(this)" value="0" <c:if test="${formDataMap['szkskhxzztpj']=='0'}">checked</c:if> class="autoValue"/>&#12288;不通过 &#12288;&#12288;&#12288;</label>
						</c:if>
						<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
							<c:if test="${!empty formDataMap['szkskhxzztpj']}">
									${formDataMap['szkskhxzztpj']}
							</c:if>
							<input type="hidden" name="szkskhxzztpj" value="${formDataMap['szkskhxzztpj']}"/>	
						</c:if>
						</td>
					</tr>
					<tr>
						<td>带教老师签名</td>
						<td colspan="3">
								<label style="float: right; margin-right: 20px;">
							日期：
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
									<input type="text" class="inputText" name="teacherDate" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${empty formDataMap['teacherDate']?pdfn:getCurrDate():formDataMap['teacherDate']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								${formDataMap['teacherDate']}
								<input type="hidden" class="inputText" name="teacherDate" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${empty formDataMap['teacherDate']?pdfn:getCurrDate():formDataMap['teacherDate']}"/>
							</c:if>
								</label>
						</td>
					</tr>
					<tr>
						<td>科主任签名</td>
						<td colspan="3">
						<label style="float: right; margin-right: 20px;">
							日期：
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText" name="directorDate" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${empty formDataMap['directorDate']?pdfn:getCurrDate():formDataMap['directorDate']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								${formDataMap['directorDate']}
								<input type="hidden" class="inputText" name="directorDate" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${empty formDataMap['directorDate']?pdfn:getCurrDate():formDataMap['directorDate']}"/>
							</c:if>
						</label>
						</td>
					</tr>
				</table>
				<table class="basic" width="100%" style="margin-top: 10px;">
				</table>
				<p align="center" style="margin-top: 10px;">
					<input class="search" type="button" value="保&#12288;存"  onclick="save();"/>
					<input class="search" type="button" value="关&#12288;闭"  onclick="jboxClose();"/>
				</p>
			</form>
		</div>
	</div>
</body>
</html>