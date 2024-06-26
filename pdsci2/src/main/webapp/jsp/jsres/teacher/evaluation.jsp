<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>

<c:set var="formDataMap" value="${empty formDataMap?resRecMap[param.recFlow]:formDataMap}"/>
<c:set var="dataMap" value="${empty dataMap?dataAllMap[param.recFlow]:dataMap}"/>

<html>
<head>
<c:if test="${!param.noHead}">
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
</c:if>
<style type="text/css">
	table th,table td { border-right:1px solid #E7E7EB; border-bottom:1px solid #E7E7EB; height:35px;}
	 th { color:#333; height:35px; text-align:right; padding-right:10px; background:#ECF2FA;}
	 td { text-align:left; padding-left:10px; line-height:35px;border: 1px solid #E7E7EB;}
	.btn_green{display: inline-block;overflow: visible;padding: 0 20px;height: 30px;line-height: 28px;vertical-align: middle;text-align: center;text-decoration: none;border-radius: 3px;-moz-border-radius: 3px;-webkit-border-radius: 3px;font-size: 14px;cursor: pointer;font-family: "Microsoft YaHei";}
	.btn_green{background-color: #44b549;color: #fff;border:none;}
	.btn_green:hover{background-color: #2f9833;}
</style>
<script type="text/javascript">
	function diffDays(){

		var begin  =$("#beginDate").val();
		console.log(begin);
		var  end   =$("#endDate").val();
		console.log(end);
		var startTime = new Date(Date.parse(begin.replace(/-/g,   "/"))).getTime();
		var endTime = new Date(Date.parse(end.replace(/-/g,   "/"))).getTime();
		var totalDays = Number(Math.abs((endTime - startTime))/(1000*60*60*24))+1;

		console.log(totalDays);
		var leaveDays = $("#leave").val()||0;//事假
		var sickLeaveDays = $("#sickLeave").val()||0;//病假
		var materLeaveDays = $("#materLeave").val()||0;//产假
		var absenteeismDays = $("#absenteeism").val()||0;//旷工
		$("#attendance").val(Number(totalDays-leaveDays-sickLeaveDays-materLeaveDays-absenteeismDays));
        $("#teacherComment").html('${rec.teacherComment}');

	}
	$(document).ready(function(){
		diffDays();
	})


	function single(box){
    var curr=box.checked;
 	if(curr){
		var name=box.name;
		$(":checkbox[name='"+name+"']").attr("checked",false);
	}
	  box.checked = curr;
  }
function save(){
	var ssswcbl = $("#ssswcbl").val();
	var bzswcbl = $("#bzswcbl").val();
	var czswcbl = $("#czswcbl").val();
	var blswcbl = $("#blswcbl").val();
	var checkProcess = $("#checkProcess").val();
	var number = (Number(ssswcbl)+Number(bzswcbl)+Number(czswcbl)+Number(blswcbl))/4;
	if(number<checkProcess){
		jboxInfo("数据填写比例不达"+checkProcess+"%,无法审核出科！");
		return false;
	}


	if(!$("#evaluationForm").validationEngine("validate")){
		return false;
	}
	var theoreResult = parseFloat($("input[name='theoreResult']").val());
	if(!theoreResult){
		theoreResult=0;
	}
	// 审核结果
    var status = $('input[name="szkskhxzztpj"]:checked').val();
	// 如果审核通过则判断是否需要校验出科从理论成绩
    <%--if(status == 1){--%>
        <%--if("${theoreticalCfg}" && "${theoreticalCfg.cfgValue}" == "Y"){--%>
            <%--// 判断试卷是否存在并且有及格分--%>
            <%--if("${testPaper}" && "${testPaper.passScore}"){--%>
                <%--// 理论成绩小于试卷及格分个提示并且不能保存--%>
                <%--if(theoreResult < "${testPaper.passScore}"){--%>
                    <%--jboxInfo("学员理论成绩不合格，无法出科，请通知学员重新参加出科考试！");--%>
                    <%--return ;--%>
                <%--}--%>
            <%--}--%>
        <%--}--%>
    <%--}--%>
	//禅道201 修改
    if(status == 1){
		// 判断配置及格分数
		if("${theoryScorePass}"){
			// 理论成绩小于试卷及格分个提示并且不能保存
			if(theoreResult < "${theoryScorePass}"){
				jboxInfo("学员理论成绩不合格，无法出科，请通知学员重新参加出科考试！");
				return ;
			}
		}
    }
	if ('${departureStatusId}' =='Y'){
		if ($("#teacherComment").val().trim()==''){
			jboxInfo("请填写带教老师评价！");
			return ;
		}
	}

	//禅道 4254 （苏大附二）学员出科考核表技能名称限制文字，得分限制数字 http://192.168.2.3/zentao/story-view-4254.html
	var skillScore = parseInt($('input[name="score"]').val(), 10);
	var skillNameStr = $('input[name="skillName"]').val();
	if ($('input[name="score"]').val() != null && $('input[name="score"]').val() !== '') {
		// 获取输入值并转换为整数
		var isValid = !isNaN(skillScore) && skillScore >= 0 && skillScore <= 100; // 验证是否为0到100的整数

		if (!isValid) {
			jboxInfo("技能考核得分只能在0到100之间！");
			return;
		}
	}

	if (skillNameStr != null && skillNameStr !== '') {
		// 正则表达式，用于匹配只包含中文字符的字符串
		// var regex = /^[\u4e00-\u9fa5]+$/;
		var regex = /^\d+$/;// 正则表达式，用于匹配只包含数字字符的字符串

		// 使用test方法进行校验
		if (regex.test(skillNameStr)) {
			jboxInfo('技能考核名称不能是数字');
			return ;
		}
	}

	var tip = "确认保存？";
	jboxConfirm(tip , function(){
		autoValue($("#evaluationForm"),"autoValue");
		jboxPost("<s:url value='/jsres/teacher/saveRegistryForm'/>",$("#evaluationForm").serialize(),function(resp){
			if(resp=="${GlobalConstant.SAVE_SUCCESSED}"){
			top.jboxTip("${GlobalConstant.SAVE_SUCCESSED}");
			   top.toPage();
			   top.jboxClose();
			}
		},null,false);
	});
}
</script>
</head>
<body>	
   <div class="mainright">
   	 <c:if test="${param.roleFlag == GlobalConstant.RES_ROLE_SCOPE_TEACHER or param.roleFlag == GlobalConstant.RES_ROLE_SCOPE_HEAD or param.roleFlag == GlobalConstant.RES_ROLE_SCOPE_SECRETARY }">
      <div class="content">
      </c:if>
      <c:if test="${!(param.roleFlag == GlobalConstant.RES_ROLE_SCOPE_TEACHER or param.roleFlag == GlobalConstant.RES_ROLE_SCOPE_HEAD or param.roleFlag == GlobalConstant.RES_ROLE_SCOPE_SECRETARY)}">
      <div >
      </c:if>
		  <c:set value="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId or (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER and (cksh ne 'Y' or empty rec.managerAuditUserFlow)))
		  || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD or param.roleFlag == GlobalConstant.RES_ROLE_SCOPE_SECRETARY) and (cksh eq 'Y' or not empty rec.managerAuditUserFlow) }" var="showEdit"></c:set>
			<form id="evaluationForm">
				<input type="hidden" name="formFileName" value="${formFileName}"/>
				<input type="hidden" name="deptFlow" value="${param.schDeptFlow}"/>
				<input type="hidden" name="operUserFlow" value="${param.operUserFlow}"/>
				<input type="hidden" name="roleFlag" value="${roleFlag}"/>
				<input type="hidden" name="recFlow" value="${rec.recFlow}"/>
				<input type="hidden" name="processFlow" value="${processFlow}"/>
				<input type="hidden" name="cksh" value="${cksh}"/>
				<input type="hidden" name="recordFlow" value="${param.recordFlow}"/>
				<input type="hidden" name="checkProcess" id="checkProcess" value="${checkProcess}"/>
				<c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER}">
					<input type="hidden" name="auditStatusId" value="${recStatusEnumTeacherAuditY.id}"/>
				</c:if>
				<c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD or roleFlag eq GlobalConstant.RES_ROLE_SCOPE_SECRETARY  }">
					<input type="hidden" name="headAuditStatusId" value="${recStatusEnumHeadAuditY.id}"/>
				</c:if>
				<label style="margin-bottom: 10px;">
					姓名：
					<c:if test="${empty formDataMap['name']}">
						<lable>${doctor.doctorName}</lable>
						<input type="hidden" class="inputText  validate[required]"  name="name" value="${doctor.doctorName}"/>
					</c:if>	
					<c:if test="${not empty formDataMap['name']}">
						<lable>${formDataMap['name']}</lable>
						<input type="hidden" class="inputText  validate[required]"  name="name" value="${formDataMap['name']}"/>
					</c:if>
				</label>
				<p style="float: right; margin-bottom: 10px;" >届别：
				<c:if test="${empty formDataMap['sessional']}">
					<lable>${doctor.sessionNumber}</lable>
					<input type="hidden" class="inputText  validate[required]"  name="sessional" value="${doctor.sessionNumber}"/>&#12288;
				</c:if>
				<c:if test="${not empty formDataMap['sessional']}">
					${formDataMap['sessional']}
					<input type="hidden" class="inputText  validate[required]"  name="sessional" value="${formDataMap['sessional']}"/>&#12288;
				</c:if>					
				&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;
					培训专业：
					<c:if test="${empty formDataMap['trainMajor']}">
						<lable>${doctor.trainingSpeName}</lable>
						<input type="hidden" class="inputText  validate[required]"  name="trainMajor" value="${doctor.trainingSpeName}"/>
					</c:if>
					<c:if test="${not empty formDataMap['trainMajor']}">
						${formDataMap['trainMajor']}
						<input type="hidden" class="inputText  validate[required]"  name="trainMajor" value="${formDataMap['trainMajor']}"/>
					</c:if>
					
				</p>
				<table  width="100%" style="margin-top: 10px;">
					<tr>
						<td>轮转科室名称：</td>
						<td>
							<c:if test="${empty formDataMap['deptName']}">
								<lable>${resDoctorSchProcess.schDeptName}</lable>
								<input type="hidden" class="inputText  validate[required]"  name="deptName" value="${resDoctorSchProcess.schDeptName}"/>
							</c:if>
							<c:if test="${not empty formDataMap['deptName']}">
								<lable>${formDataMap['deptName']}</lable>
								<input type="hidden" class="inputText  validate[required]"  name="deptName" value="${formDataMap['deptName']}"/>
							</c:if>
						</td>
						<td>轮转时间：</td>
						<td>
							<c:if test="${empty formDataMap['cycleTimeQ']}">
								<lable>${resDoctorSchProcess.schStartDate}</lable>
								<input type="hidden" class="inputText  validate[required]"  id="beginDate" name="cycleTimeQ" value="${resDoctorSchProcess.schStartDate}"/>
							</c:if>
							<c:if test="${not empty formDataMap['cycleTimeQ']}">
								${formDataMap['cycleTimeQ']}
								<input type="hidden" class="inputText  validate[required]"  id="beginDate" name="cycleTimeQ" value="${formDataMap['cycleTimeQ']}"/>
							</c:if>
							至
							<c:if test="${empty formDataMap['cycleTimeH']}">
								<lable>${resDoctorSchProcess.schEndDate}</lable>
								<input type="hidden" class="inputText  validate[required]"  id="endDate" name="cycleTimeH" value="${resDoctorSchProcess.schEndDate}"/>
							</c:if>
							<c:if test="${not empty formDataMap['cycleTimeH']}">
								${formDataMap['cycleTimeH']}
								<input type="hidden" class="inputText  validate[required]"  id="endDate" name="cycleTimeH" value="${formDataMap['cycleTimeH']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td>考勤</td>
						<td colspan="3">
						
							全勤：
								<c:if test="${showEdit}">
									<input type="text" class="inputText  validate[required,custom[number,min[0]]" style="width: 50px;" readonly="readonly" id="attendance" name="attendance" value="${formDataMap['attendance']}"/>天；
								</c:if>
								<c:if test="${!showEdit }">
									${formDataMap['attendance']}
									<input type="hidden" class="inputText  validate[required,custom[number,min[0]]" style="width: 50px;" name="attendance" value="${formDataMap['attendance']}"/>天；
								</c:if>
								&#12288;
							事假：
							<c:if test="${showEdit}">
								<input type="text" class="inputText  validate[required,custom[number,min[0]]" style="width: 50px;" id="leave" name="leave" value="${formDataMap['leave']}" onchange="diffDays()"/>天；&#12288;
							</c:if>
							<c:if test="${!showEdit }">
								${formDataMap['leave']}
								<input type="hidden" class="inputText  validate[required,custom[number,min[0]]" style="width: 50px;" name="leave" value="${formDataMap['leave']}"/>天；&#12288;
							</c:if>
							病假：
								<c:if test="${showEdit}">
									<input type="text" class="inputText  validate[required,custom[number,min[0]]" style="width: 50px;" id="sickLeave" name="sickLeave" value="${formDataMap['sickLeave']}" onchange="diffDays()"/>天；
								</c:if>
								<c:if test="${!showEdit }">
									${formDataMap['sickLeave']}
									<input type="hidden" class="inputText  validate[required,custom[number,min[0]]" style="width: 50px;" name="sickLeave" value="${formDataMap['sickLeave']}"/>天；
								</c:if>
								&#12288;
							产假：
							<c:if test="${showEdit}">
								<input type="text" class="inputText  validate[required,custom[number,min[0]]" style="width: 50px;" id="materLeave" name="materLeave" value="${formDataMap['materLeave']}" onchange="diffDays()"/>天；&#12288;
							</c:if>
							<c:if test="${!showEdit }">
								${formDataMap['materLeave']}
								<input type="hidden" class="inputText  validate[required,custom[number,min[0]]" style="width: 50px;" name="materLeave" value="${formDataMap['materLeave']}"/>天；&#12288;
							</c:if>
							旷工：
							<c:if test="${showEdit}">
								<input type="text" class="inputText  validate[required,custom[number,min[0]]" style="width: 50px;" id="absenteeism" name="absenteeism" value="${formDataMap['absenteeism']}" onchange="diffDays()"/>天；
							</c:if>
							<c:if test="${!showEdit }">
								${formDataMap['absenteeism']}
								<input type="hidden" class="inputText  validate[required,custom[number,min[0]]" style="width: 50px;" name="absenteeism" value="${formDataMap['absenteeism']}"/>天；
							</c:if>
						</td>
					</tr>
					<tr>
						<td rowspan="4">临床实践指标完成情况</td>
						<td colspan="3">
							<div style="text-align: center;">
							完成病历数：应完成
							<lable>${dataMap['caseRegistryReqNum']}</lable>
							<input type="hidden" class="inputText  validate[required]"  name="blsywc" value="${dataMap['caseRegistryReqNum']}"/>
							<%--<c:if test="${param.roleFlag != GlobalConstant.RES_ROLE_SCOPE_TEACHER}">--%>
 								<%--<lable>${formDataMap['blsywc']}</lable>--%>
 								<%--<input type="hidden" class="inputText  validate[required]" name="blsywc" value="${formDataMap['blsywc']}"/>--%>
 							<%--</c:if>--%>
							例，
							已完成
								<lable>${dataMap['caseRegistryFinished']}</lable>
								<input type="hidden" class="inputText  validate[required]"  name="blsyjwc" value="${dataMap['caseRegistryFinished']}"/>
							<%--<c:if test="${param.roleFlag != GlobalConstant.RES_ROLE_SCOPE_TEACHER}"> --%>
 								<%--<lable>${formDataMap['blsyjwc']}</lable> --%>
 								<%--<input type="hidden" class="inputText  validate[required]" name="blsyjwc" value="${formDataMap['blsyjwc']}"/>--%>
							<%--</c:if> --%>
							例，
							完成比例
								<lable>${dataMap['caseRegistry']}</lable>
								<input type="hidden" class="inputText  validate[required]"  name="blswcbl" id="blswcbl" value="${dataMap['caseRegistry']}"/>
							<%--<c:if test="${param.roleFlag != GlobalConstant.RES_ROLE_SCOPE_TEACHER}"> --%>
								<%--<lable>${formDataMap['blswcbl']}</lable>--%>
								<%--<input type="hidden" class="inputText  validate[required]" name="blswcbl" value="${formDataMap['blswcbl']}"/>--%>
							<%--</c:if>--%>
							%
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="3">
						<div style="text-align: center;">
							管理病种数：应完成
								<lable>${dataMap['diseaseRegistryReqNum']}</lable>
								<input type="hidden" class="inputText  validate[required]"  name="bzsywc" value="${dataMap['diseaseRegistryReqNum']}"/>
							<%--<c:if test="${param.roleFlag != GlobalConstant.RES_ROLE_SCOPE_TEACHER}"> --%>
								<%--<lable>${formDataMap['bzsywc']}</lable>--%>
								<%--<input type="hidden" class="inputText  validate[required]" name="bzsywc" value="${formDataMap['bzsywc']}"/>--%>
							<%--</c:if>--%>
							例，
							已完成
								<lable>${dataMap['diseaseRegistryFinished']}</lable>
								<input type="hidden" class="inputText  validate[required]"  name="bzsyjwc" value="${dataMap['diseaseRegistryFinished']}"/>
							<%--<c:if test="${param.roleFlag != GlobalConstant.RES_ROLE_SCOPE_TEACHER}"> --%>
								<%--<lable>${formDataMap['bzsyjwc']}</lable>--%>
								<%--<input type="hidden" class="inputText  validate[required]"  name="bzsyjwc" value="${formDataMap['bzsyjwc']}"/>--%>
							<%--</c:if>--%>
							例，
							完成比例
								<lable>${dataMap['diseaseRegistry']}</lable>
								<input type="hidden" class="inputText  validate[required]"  name="bzswcbl" id="bzswcbl" value="${dataMap['diseaseRegistry']}"/>
							<%--<c:if test="${param.roleFlag != GlobalConstant.RES_ROLE_SCOPE_TEACHER}"> --%>
								<%--<lable>${formDataMap['bzswcbl']}</lable>--%>
								<%--<input type="hidden" class="inputText  validate[required]"  name="bzswcbl" value="${formDataMap['bzswcbl']}"/>--%>
							<%--</c:if>--%>
							%
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="3">
						<div style="text-align: center;">
							完成操作数：应完成
								<lable>${dataMap['skillRegistryReqNum']}</lable>
								<input type="hidden" class="inputText  validate[required]"  name="czsywc" value="${dataMap['skillRegistryReqNum']}"/>
							<%--<c:if test="${param.roleFlag != GlobalConstant.RES_ROLE_SCOPE_TEACHER}"> --%>
								<%--<lable>${formDataMap['czsywc']}</lable>--%>
								<%--<input type="hidden" class="inputText  validate[required]"  name="czsywc" value="${formDataMap['czsywc']}"/>--%>
							<%--</c:if>--%>
							例，
							已完成
								<lable>${dataMap['skillRegistryFinished']}</lable>
								<input type="hidden" class="inputText  validate[required]"  name="czsyjwc" value="${dataMap['skillRegistryFinished']}"/>
							<%--<c:if test="${param.roleFlag != GlobalConstant.RES_ROLE_SCOPE_TEACHER}"> --%>
								<%--<lable>${formDataMap['czsyjwc']}</lable>--%>
								<%--<input type="hidden" class="inputText  validate[required]"  name="czsyjwc" value="${formDataMap['czsyjwc']}"/>--%>
							<%--</c:if>--%>
							例，
							完成比例
								<lable>${dataMap['skillRegistry']}</lable>
								<input type="hidden" class="inputText  validate[required]"  name="czswcbl" id="czswcbl" value="${dataMap['skillRegistry']}"/>
							<%--<c:if test="${param.roleFlag != GlobalConstant.RES_ROLE_SCOPE_TEACHER}"> --%>
								<%--<lable>${formDataMap['czswcbl']}</lable>--%>
								<%--<input type="hidden" class="inputText  validate[required]"  name="czswcbl" value="${formDataMap['czswcbl']}"/>--%>
							<%--</c:if>--%>
							%
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="3">
						<div style="text-align: center;">
							参加手术数：应完成
								<lable>${dataMap['operationRegistryReqNum']}</lable>
								<input type="hidden" class="inputText  validate[required]"  name="sssywc" value="${dataMap['operationRegistryReqNum']}"/>
							<%--<c:if test="${param.roleFlag != GlobalConstant.RES_ROLE_SCOPE_TEACHER}"> --%>
								<%--<lable>${formDataMap['sssywc']}</lable>--%>
								<%--<input type="hidden" class="inputText  validate[required]"  name="sssywc" value="${formDataMap['sssywc']}"/>--%>
							<%--</c:if>--%>
							例，
							已完成
								<lable>${dataMap['operationRegistryFinished']}</lable>
								<input type="hidden" class="inputText  validate[required]" name="sssyjwc" value="${dataMap['operationRegistryFinished']}"/>
							<%--<c:if test="${param.roleFlag != GlobalConstant.RES_ROLE_SCOPE_TEACHER}"> --%>
								<%--<lable>${formDataMap['sssyjwc']}</lable>--%>
								<%--<input type="hidden" class="inputText  validate[required]" name="sssyjwc" value="${formDataMap['sssyjwc']}"/>--%>
							<%--</c:if>--%>
							例，
							完成比例
								<lable>${dataMap['operationRegistry']}</lable>
								<input type="hidden" class="inputText  validate[required]"  name="ssswcbl" id="ssswcbl" value="${dataMap['operationRegistry']}"/>
							<%--<c:if test="${param.roleFlag != GlobalConstant.RES_ROLE_SCOPE_TEACHER}"> --%>
								<%--<lable>${formDataMap['ssswcbl']}</lable>--%>
								<%--<input type="hidden" class="inputText  validate[required]"  name="ssswcbl" value="${formDataMap['ssswcbl']}"/>--%>
							<%--</c:if>--%>
							%
							</div>
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
							<c:if test="${showEdit}">
								<label><input type="checkbox" onchange="single(this)" name="zsgjflfg" value="1" <c:if test="${formDataMap['zsgjflfg_id']=='1'}">checked</c:if> class="autoValue validate[required]"/>&#12288;优&#12288;&#12288;&#12288;</label>
								<label><input type="checkbox" onchange="single(this)" name="zsgjflfg" value="2" <c:if test="${formDataMap['zsgjflfg_id']=='2'}">checked</c:if> class="autoValue validate[required]"/>&#12288;良&#12288;&#12288;&#12288;&#12288;</label>
								<label><input type="checkbox" onchange="single(this)" name="zsgjflfg" value="3" <c:if test="${formDataMap['zsgjflfg_id']=='3'}">checked</c:if> class="autoValue validate[required]"/>&#12288;中&#12288;&#12288;&#12288;&#12288;</label>
								<label><input type="checkbox" onchange="single(this)" name="zsgjflfg" value="4" <c:if test="${formDataMap['zsgjflfg_id']=='4'}">checked</c:if> class="autoValue validate[required]"/>&#12288;差&#12288;&#12288;&#12288;</label>
							</c:if>
<%--							<c:if test="${!showEdit }">	--%>
<%--							<c:if test="${!empty formDataMap['zsgjflfg']}">--%>
<%--									${formDataMap['zsgjflfg']}--%>
<%--							</c:if>--%>
<%--								<input type="hidden" name="zsgjflfg" value="${formDataMap['zsgjflfg']}"/>--%>
<%--							</c:if>--%>
							<c:if test="${!showEdit }">
								<c:if test="${!empty formDataMap['zsgjflfg_id']}">
									<c:if test="${formDataMap['zsgjflfg_id']=='1'}">优</c:if>
									<c:if test="${formDataMap['zsgjflfg_id']=='2'}">良</c:if>
									<c:if test="${formDataMap['zsgjflfg_id']=='3'}">中</c:if>
									<c:if test="${formDataMap['zsgjflfg_id']=='4'}">差</c:if>
								</c:if>
								<input type="hidden" name="zsgjflfg" value="${formDataMap['zsgjflfg_id']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td>履行岗位职责</td>
						<td colspan="2">
							<c:if test="${showEdit}">
								<label><input type="checkbox" onchange="single(this)" name="lxgwzz" value="1" <c:if test="${formDataMap['lxgwzz_id']=='1'}">checked</c:if> class="autoValue validate[required]"/>&#12288;优&#12288;&#12288;&#12288;</label>
								<label><input type="checkbox" onchange="single(this)" name="lxgwzz" value="2" <c:if test="${formDataMap['lxgwzz_id']=='2'}">checked</c:if> class="autoValue validate[required]"/>&#12288;良&#12288;&#12288;&#12288;&#12288;</label>
								<label><input type="checkbox" onchange="single(this)" name="lxgwzz" value="3" <c:if test="${formDataMap['lxgwzz_id']=='3'}">checked</c:if> class="autoValue validate[required]"/>&#12288;中&#12288;&#12288;&#12288;&#12288;</label>
								<label><input type="checkbox" onchange="single(this)" name="lxgwzz" value="4" <c:if test="${formDataMap['lxgwzz_id']=='4'}">checked</c:if> class="autoValue validate[required]"/>&#12288;差&#12288;&#12288;&#12288;</label>
							</c:if>	
<%--							<c:if test="${!showEdit }">--%>
<%--							<c:if test="${!empty formDataMap['lxgwzz']}">--%>
<%--									${formDataMap['lxgwzz']}--%>
<%--								</c:if>--%>
<%--								<input type="hidden" name="lxgwzz" value="${formDataMap['lxgwzz']}"/>--%>
<%--							</c:if>--%>
							<c:if test="${!showEdit }">
								<c:if test="${!empty formDataMap['lxgwzz_id']}">
									<c:if test="${formDataMap['lxgwzz_id']=='1'}">优</c:if>
									<c:if test="${formDataMap['lxgwzz_id']=='2'}">良</c:if>
									<c:if test="${formDataMap['lxgwzz_id']=='3'}">中</c:if>
									<c:if test="${formDataMap['lxgwzz_id']=='4'}">差</c:if>
								</c:if>
								<input type="hidden" name="lxgwzz" value="${formDataMap['lxgwzz_id']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td>以病人为中心，体现人文关怀</td>
						<td colspan="2">
							<c:if test="${showEdit}">
								<label><input type="checkbox" onchange="single(this)" name="ybrwzx" value="1" <c:if test="${formDataMap['ybrwzx_id']=='1'}">checked</c:if> class="autoValue validate[required]"/>&#12288;优&#12288;&#12288;&#12288;</label>
								<label><input type="checkbox" onchange="single(this)" name="ybrwzx" value="2" <c:if test="${formDataMap['ybrwzx_id']=='2'}">checked</c:if> class="autoValue validate[required]"/>&#12288;良&#12288;&#12288;&#12288;&#12288;</label>
								<label><input type="checkbox" onchange="single(this)" name="ybrwzx" value="3" <c:if test="${formDataMap['ybrwzx_id']=='3'}">checked</c:if> class="autoValue validate[required]"/>&#12288;中&#12288;&#12288;&#12288;&#12288;</label>
								<label><input type="checkbox" onchange="single(this)" name="ybrwzx" value="4" <c:if test="${formDataMap['ybrwzx_id']=='4'}">checked</c:if> class="autoValue validate[required]"/>&#12288;差&#12288;&#12288;&#12288;</label>
							</c:if>
<%--								<c:if test="${!showEdit }">--%>
<%--								<c:if test="${!empty formDataMap['ybrwzx']}">--%>
<%--									${formDataMap['ybrwzx']}--%>
<%--								</c:if>--%>
<%--								<input type="hidden" name="ybrwzx" value="${formDataMap['ybrwzx']}"/>--%>
<%--								</c:if>--%>
							<c:if test="${!showEdit }">
								<c:if test="${!empty formDataMap['ybrwzx_id']}">
									<c:if test="${formDataMap['ybrwzx_id']=='1'}">优</c:if>
									<c:if test="${formDataMap['ybrwzx_id']=='2'}">良</c:if>
									<c:if test="${formDataMap['ybrwzx_id']=='3'}">中</c:if>
									<c:if test="${formDataMap['ybrwzx_id']=='4'}">差</c:if>
								</c:if>
								<input type="hidden" name="ybrwzx" value="${formDataMap['ybrwzx_id']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td>人际（医患）沟通和表达能力</td>
						<td colspan="2">
								<c:if test="${showEdit}">
								<label><input type="checkbox" onchange="single(this)" name="rjgtbdnl" value="1" <c:if test="${formDataMap['rjgtbdnl_id']=='1'}">checked</c:if> class="autoValue validate[required]"/>&#12288;强&#12288;&#12288;&#12288;</label>
								<label><input type="checkbox" onchange="single(this)" name="rjgtbdnl" value="2" <c:if test="${formDataMap['rjgtbdnl_id']=='2'}">checked</c:if> class="autoValue validate[required]"/>&#12288;较强&#12288;&#12288;&#12288;</label>
								<label><input type="checkbox" onchange="single(this)" name="rjgtbdnl" value="3" <c:if test="${formDataMap['rjgtbdnl_id']=='3'}">checked</c:if> class="autoValue validate[required]"/>&#12288;一般&#12288;&#12288;&#12288;</label>
								<label><input type="checkbox" onchange="single(this)" name="rjgtbdnl" value="4" <c:if test="${formDataMap['rjgtbdnl_id']=='4'}">checked</c:if> class="autoValue validate[required]"/>&#12288;差&#12288;&#12288;&#12288;</label>
								</c:if>
<%--								<c:if test="${!showEdit }">--%>
<%--								<c:if test="${!empty formDataMap['rjgtbdnl']}">--%>
<%--									${formDataMap['rjgtbdnl']}--%>
<%--								</c:if>--%>
<%--								<input type="hidden" name="rjgtbdnl" value="${formDataMap['rjgtbdnl']}"/>--%>
<%--								</c:if>--%>
							<c:if test="${!showEdit }">
								<c:if test="${!empty formDataMap['rjgtbdnl_id']}">
									<c:if test="${formDataMap['rjgtbdnl_id']=='1'}">强</c:if>
									<c:if test="${formDataMap['rjgtbdnl_id']=='2'}">较强</c:if>
									<c:if test="${formDataMap['rjgtbdnl_id']=='3'}">一般</c:if>
									<c:if test="${formDataMap['rjgtbdnl_id']=='4'}">差</c:if>
								</c:if>
								<input type="hidden" name="rjgtbdnl" value="${formDataMap['rjgtbdnl_id']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td>团结协作精神</td>
						<td colspan="2">
								<c:if test="${showEdit}">
									<label><input type="checkbox" onchange="single(this)" name="tjxzjsxm" value="1" <c:if test="${formDataMap['tjxzjsxm_id']=='1'}">checked</c:if> class="autoValue validate[required]"/>&#12288;优&#12288;&#12288;&#12288;</label>
									<label><input type="checkbox" onchange="single(this)" name="tjxzjsxm" value="2" <c:if test="${formDataMap['tjxzjsxm_id']=='2'}">checked</c:if> class="autoValue validate[required]"/>&#12288;良&#12288;&#12288;&#12288;&#12288;</label>
									<label><input type="checkbox" onchange="single(this)" name="tjxzjsxm" value="3" <c:if test="${formDataMap['tjxzjsxm_id']=='3'}">checked</c:if> class="autoValue validate[required]"/>&#12288;中&#12288;&#12288;&#12288;&#12288;</label>
									<label><input type="checkbox" onchange="single(this)" name="tjxzjsxm" value="4" <c:if test="${formDataMap['tjxzjsxm_id']=='4'}">checked</c:if> class="autoValue validate[required]"/>&#12288;差&#12288;&#12288;&#12288;</label>
								</c:if>
<%--								<c:if test="${!showEdit }">--%>
<%--									<c:if test="${!empty formDataMap['tjxzjsxm']}">--%>
<%--										${formDataMap['tjxzjsxm']}--%>
<%--									</c:if>--%>
<%--									<input type="hidden" name="tjxzjsxm" value="${formDataMap['tjxzjsxm']}"/>--%>
<%--								</c:if>--%>
							<c:if test="${!showEdit }">
								<c:if test="${!empty formDataMap['tjxzjsxm_id']}">
									<c:if test="${formDataMap['tjxzjsxm_id']=='1'}">优</c:if>
									<c:if test="${formDataMap['tjxzjsxm_id']=='2'}">良</c:if>
									<c:if test="${formDataMap['tjxzjsxm_id']=='3'}">中</c:if>
									<c:if test="${formDataMap['tjxzjsxm_id']=='4'}">差</c:if>
								</c:if>
								<input type="hidden" name="tjxzjsxm" value="${formDataMap['tjxzjsxm_id']}"/>
							</c:if>
						</td>
					</tr>
						<tr>
						<td rowspan="5">临床综合能力作</td>
						<td>临床基本知识、基本理论掌握程度</td>
						<td colspan="2">
								<c:if test="${showEdit}">
								<label><input type="checkbox" onchange="single(this)" name="jbllzwcd" value="1" <c:if test="${formDataMap['jbllzwcd_id']=='1'}">checked</c:if> class="autoValue validate[required]"/>&#12288;强&#12288;&#12288;&#12288;</label>
								<label><input type="checkbox" onchange="single(this)" name="jbllzwcd" value="2" <c:if test="${formDataMap['jbllzwcd_id']=='2'}">checked</c:if> class="autoValue validate[required]"/>&#12288;较强&#12288;&#12288;&#12288;</label>
								<label><input type="checkbox" onchange="single(this)" name="jbllzwcd" value="3" <c:if test="${formDataMap['jbllzwcd_id']=='3'}">checked</c:if> class="autoValue validate[required]"/>&#12288;一般&#12288;&#12288;&#12288;</label>
								<label><input type="checkbox" onchange="single(this)" name="jbllzwcd" value="4" <c:if test="${formDataMap['jbllzwcd_id']=='4'}">checked</c:if> class="autoValue validate[required]"/>&#12288;差&#12288;&#12288;&#12288;</label>
								</c:if>
<%--								<c:if test="${!showEdit }">--%>
<%--									<c:if test="${!empty formDataMap['jbllzwcd']}">--%>
<%--										${formDataMap['jbllzwcd']}--%>
<%--									</c:if>--%>
<%--									<input type="hidden" name="jbllzwcd" value="${formDataMap['jbllzwcd']}"/>--%>
<%--								</c:if>--%>
							<c:if test="${!showEdit }">
								<c:if test="${!empty formDataMap['jbllzwcd_id']}">
									<c:if test="${formDataMap['jbllzwcd_id']=='1'}">强</c:if>
									<c:if test="${formDataMap['jbllzwcd_id']=='2'}">较强</c:if>
									<c:if test="${formDataMap['jbllzwcd_id']=='3'}">一般</c:if>
									<c:if test="${formDataMap['jbllzwcd_id']=='4'}">差</c:if>
								</c:if>
								<input type="hidden" name="jbllzwcd" value="${formDataMap['jbllzwcd_id']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td>临床基本技能掌握程度</td>
						<td colspan="2">
							<c:if test="${showEdit}">
								<label><input type="checkbox" onchange="single(this)" name="njbjnzwcd" value="1" <c:if test="${formDataMap['njbjnzwcd_id']=='1'}">checked</c:if> class="autoValue validate[required]"/>&#12288;强&#12288;&#12288;&#12288;</label>
								<label><input type="checkbox" onchange="single(this)" name="njbjnzwcd" value="2" <c:if test="${formDataMap['njbjnzwcd_id']=='2'}">checked</c:if> class="autoValue validate[required]"/>&#12288;较强&#12288;&#12288;&#12288;</label>
								<label><input type="checkbox" onchange="single(this)" name="njbjnzwcd" value="3" <c:if test="${formDataMap['njbjnzwcd_id']=='3'}">checked</c:if> class="autoValue validate[required]"/>&#12288;一般&#12288;&#12288;&#12288;</label>
								<label><input type="checkbox" onchange="single(this)" name="njbjnzwcd" value="4" <c:if test="${formDataMap['njbjnzwcd_id']=='4'}">checked</c:if> class="autoValue validate[required]"/>&#12288;差&#12288;&#12288;&#12288;</label>
								</c:if>
<%--								<c:if test="${!showEdit }">--%>
<%--								<c:if test="${!empty formDataMap['njbjnzwcd']}">--%>
<%--									${formDataMap['njbjnzwcd']}--%>
<%--								</c:if>--%>
<%--								<input type="hidden" name="njbjnzwcd" value="${formDataMap['njbjnzwcd']}"/>--%>
<%--								</c:if>--%>
							<c:if test="${!showEdit }">
								<c:if test="${!empty formDataMap['njbjnzwcd_id']}">
									<c:if test="${formDataMap['njbjnzwcd_id']=='1'}">强</c:if>
									<c:if test="${formDataMap['njbjnzwcd_id']=='2'}">较强</c:if>
									<c:if test="${formDataMap['njbjnzwcd_id']=='3'}">一般</c:if>
									<c:if test="${formDataMap['njbjnzwcd_id']=='4'}">差</c:if>
								</c:if>
								<input type="hidden" name="njbjnzwcd" value="${formDataMap['njbjnzwcd_id']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td>临床思维能力</td>
						<td colspan="2">
							<c:if test="${showEdit}">
								<label><input type="checkbox" onchange="single(this)" name="lcswnl" value="1" <c:if test="${formDataMap['lcswnl_id']=='1'}">checked</c:if> class="autoValue validate[required]"/>&#12288;强&#12288;&#12288;&#12288;</label>
								<label><input type="checkbox" onchange="single(this)" name="lcswnl" value="2" <c:if test="${formDataMap['lcswnl_id']=='2'}">checked</c:if> class="autoValue validate[required]"/>&#12288;较强&#12288;&#12288;&#12288;</label>
								<label><input type="checkbox" onchange="single(this)" name="lcswnl" value="3" <c:if test="${formDataMap['lcswnl_id']=='3'}">checked</c:if> class="autoValue validate[required]"/>&#12288;一般&#12288;&#12288;&#12288;</label>
								<label><input type="checkbox" onchange="single(this)" name="lcswnl" value="4" <c:if test="${formDataMap['lcswnl_id']=='4'}">checked</c:if> class="autoValue validate[required]"/>&#12288;差&#12288;&#12288;&#12288;</label>
								</c:if>
<%--								<c:if test="${!showEdit }">--%>
<%--									<c:if test="${!empty formDataMap['lcswnl']}">--%>
<%--										${formDataMap['lcswnl']}--%>
<%--									</c:if>--%>
<%--								</c:if>--%>
<%--								<input type="hidden" name="lcswnl" value="${formDataMap['lcswnl']}"/>--%>
							<c:if test="${!showEdit }">
								<c:if test="${!empty formDataMap['lcswnl_id']}">
									<c:if test="${formDataMap['lcswnl_id']=='1'}">强</c:if>
									<c:if test="${formDataMap['lcswnl_id']=='2'}">较强</c:if>
									<c:if test="${formDataMap['lcswnl_id']=='3'}">一般</c:if>
									<c:if test="${formDataMap['lcswnl_id']=='4'}">差</c:if>
								</c:if>
								<input type="hidden" name="lcswnl" value="${formDataMap['lcswnl_id']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td>临床诊疗能力</td>
						<td colspan="2">
								<c:if test="${showEdit}">
									<label><input type="checkbox" onchange="single(this)" name="lczlnl" value="1" <c:if test="${formDataMap['lczlnl_id']=='1'}">checked</c:if> class="autoValue validate[required]"/>&#12288;强&#12288;&#12288;&#12288;</label>
									<label><input type="checkbox" onchange="single(this)" name="lczlnl" value="2" <c:if test="${formDataMap['lczlnl_id']=='2'}">checked</c:if> class="autoValue validate[required]"/>&#12288;较强&#12288;&#12288;&#12288;</label>
									<label><input type="checkbox" onchange="single(this)" name="lczlnl" value="3" <c:if test="${formDataMap['lczlnl_id']=='3'}">checked</c:if> class="autoValue validate[required]"/>&#12288;一般&#12288;&#12288;&#12288;</label>
									<label><input type="checkbox" onchange="single(this)" name="lczlnl" value="4" <c:if test="${formDataMap['lczlnl_id']=='4'}">checked</c:if> class="autoValue validate[required]"/>&#12288;差&#12288;&#12288;&#12288;</label>
								</c:if>
<%--								<c:if test="${!showEdit }">--%>
<%--									<c:if test="${!empty formDataMap['lczlnl']}">--%>
<%--										${formDataMap['lczlnl']}--%>
<%--									</c:if>--%>
<%--									<input type="hidden" name="lczlnl" value="${formDataMap['lczlnl']}"/>--%>
<%--								</c:if>--%>
							<c:if test="${!showEdit }">
								<c:if test="${!empty formDataMap['lczlnl_id']}">
									<c:if test="${formDataMap['lczlnl_id']=='1'}">强</c:if>
									<c:if test="${formDataMap['lczlnl_id']=='2'}">较强</c:if>
									<c:if test="${formDataMap['lczlnl_id']=='3'}">一般</c:if>
									<c:if test="${formDataMap['lczlnl_id']=='4'}">差</c:if>
								</c:if>
								<input type="hidden" name="lczlnl" value="${formDataMap['lczlnl_id']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td>危重病人的识别及紧急处理能力</td>
						<td colspan="2">
							<c:if test="${showEdit}">
							<label><input type="checkbox" onchange="single(this)" name="jjclnl" value="1" <c:if test="${formDataMap['jjclnl_id']=='1'}">checked</c:if> class="autoValue validate[required]"/>&#12288;强&#12288;&#12288;&#12288;</label>
							<label><input type="checkbox" onchange="single(this)" name="jjclnl" value="2" <c:if test="${formDataMap['jjclnl_id']=='2'}">checked</c:if> class="autoValue validate[required]"/>&#12288;较强&#12288;&#12288;&#12288;</label>
							<label><input type="checkbox" onchange="single(this)" name="jjclnl" value="3" <c:if test="${formDataMap['jjclnl_id']=='3'}">checked</c:if> class="autoValue validate[required]"/>&#12288;一般&#12288;&#12288;&#12288;</label>
							<label><input type="checkbox" onchange="single(this)" name="jjclnl" value="4" <c:if test="${formDataMap['jjclnl_id']=='4'}">checked</c:if> class="autoValue validate[required]"/>&#12288;差&#12288;&#12288;&#12288;</label>
							</c:if>
<%--							<c:if test="${!showEdit }">--%>
<%--								<c:if test="${!empty formDataMap['jjclnl']}">--%>
<%--										${formDataMap['jjclnl']}--%>
<%--									</c:if>--%>
<%--								<input type="hidden" name="jjclnl" value="${formDataMap['jjclnl']}"/>--%>
<%--							</c:if>--%>
							<c:if test="${!showEdit }">
								<c:if test="${!empty formDataMap['jjclnl_id']}">
									<c:if test="${formDataMap['jjclnl_id']=='1'}">优</c:if>
									<c:if test="${formDataMap['jjclnl_id']=='2'}">良</c:if>
									<c:if test="${formDataMap['jjclnl_id']=='3'}">中</c:if>
									<c:if test="${formDataMap['jjclnl_id']=='4'}">差</c:if>
								</c:if>
								<input type="hidden" name="jjclnl" value="${formDataMap['jjclnl_id']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td rowspan="8">参加各种形式活动</td>
						<td colspan="3">
							教学查房：
							<%--<c:if test="${empty formDataMap['jxcb']or (formDataMap['jxcb'] eq '0')}">--%>
								<lable>${dataMap['jxcf']}</lable>
								<input type="hidden" class="inputText  validate[required]"  name="jxcf" value="${dataMap['jxcf']}"/>
							<%--</c:if>--%>
							<%--<c:if test="${not empty formDataMap['jxcb']  and !(formDataMap['jxcb'] eq '0')}">--%>
								<%--<lable>${formDataMap['jxcb']}</lable>--%>
								<%--<input type="hidden" class="inputText  validate[required]"  name="jxcb" value="${formDataMap['jxcb']}"/>--%>
							<%--</c:if>--%>
							次                 
							&#12288;
							<label style="margin-left: 70px;">
								临床小讲课：
								<lable>${dataMap['xjk']}</lable>
								<input type="hidden" class="inputText  validate[required]"  name="xjk" value="${dataMap['xjk']}"/>
								次
							</label>&#12288;
							<label style="margin-left: 70px;">
								入轮转科室教育：
								<lable>${dataMap['rkjy']}</lable>
								<input type="hidden" class="inputText  validate[required]"  name="rkjy" value="${dataMap['rkjy']}"/>
								次
							</label>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							教学阅片：
								<lable>${dataMap['yph']}</lable>
								<input type="hidden" class="inputText  validate[required]" name="yph" value="${dataMap['yph']}"/>
							次                  
							&#12288;
							<label style="margin-left: 70px;">
								门诊教学：
								<lable>${dataMap['jxhz']}</lable>
								<input type="hidden" class="inputText  validate[required]"  name="jxhz" value="${dataMap['jxhz']}"/>
								次
							</label>&#12288;
							&#12288;
							<label style="margin-left: 70px;">
								教学病例讨论：
								<lable>${dataMap['jxbltl']}</lable>
								<input type="hidden" class="inputText  validate[required]"  name="jxbltl" value="${dataMap['jxbltl']}"/>
								次
							</label>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							疑难病例讨论：
							<lable>${dataMap['ynbltl']}</lable>
							<input type="hidden" class="inputText  validate[required]" name="ynbltl" value="${dataMap['ynbltl']}"/>
							次
							&#12288;
							<label style="margin-left: 70px;">
								危重病例讨论：
								<lable>${dataMap['wzbltl']}</lable>
								<input type="hidden" class="inputText  validate[required]"  name="wzbltl" value="${dataMap['wzbltl']}"/>
								次
							</label>&#12288;
							&#12288;
							<label style="margin-left: 70px;">
								死亡病例讨论：
								<lable>${dataMap['swbltl']}</lable>
								<input type="hidden" class="inputText  validate[required]"  name="swbltl" value="${dataMap['swbltl']}"/>
								次
							</label>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							临床操作技能床旁教学：
							<lable>${dataMap['lcczjnzd']}</lable>
							<input type="hidden" class="inputText  validate[required]" name="lcczjnzd" value="${dataMap['lcczjnzd']}"/>
							次
							&#12288;&#12288;
							<label style="margin-left: 70px;">
								住院病历书写指导教学：
								<lable>${dataMap['lcblsxzd']}</lable>
								<input type="hidden" class="inputText  validate[required]"  name="lcblsxzd" value="${dataMap['lcblsxzd']}"/>
								次
							</label>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							手术操作指导教学：
							<lable>${dataMap['ssczzd']}</lable>
							<input type="hidden" class="inputText  validate[required]" name="ssczzd" value="${dataMap['ssczzd']}"/>
							次
							&#12288;&#12288;
							<label style="margin-left: 70px;">
								影像诊断报告书写指导教学：
								<lable>${dataMap['yxzdbgsxzd']}</lable>
								<input type="hidden" class="inputText  validate[required]"  name="yxzdbgsxzd" value="${dataMap['yxzdbgsxzd']}"/>
								次
							</label>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							临床文献研读会：
							<lable>${dataMap['lcwxyd']}</lable>
							<input type="hidden" class="inputText  validate[required]" name="lcwxyd" value="${dataMap['lcwxyd']}"/>
							次
							&#12288;&#12288;
							<label style="margin-left: 70px;">
								入院教育：
								<lable>${dataMap['ryjy']}</lable>
								<input type="hidden" class="inputText  validate[required]"  name="ryjy" value="${dataMap['ryjy']}"/>
								次
							</label>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							入专业基地教育：
							<lable>${dataMap['rzyjdjy']}</lable>
							<input type="hidden" class="inputText  validate[required]" name="rzyjdjy" value="${dataMap['rzyjdjy']}"/>
							次
							&#12288;&#12288;
							<label style="margin-left: 70px;">
								晨间报告：
								<lable>${dataMap['cjbg']}</lable>
								<input type="hidden" class="inputText  validate[required]"  name="cjbg" value="${dataMap['cjbg']}"/>
								次
							</label>

							&#12288;&#12288;
							<label style="margin-left: 70px;">
								报告单分析：
								<lable>${dataMap['bgdfx']}</lable>
								<input type="hidden" class="inputText  validate[required]"  name="bgdfx" value="${dataMap['bgdfx']}"/>
								次
							</label>
						</td>
					</tr>
					<tr>
						<td colspan="3">

							教学上机：
							<lable>${dataMap['jxsj']}</lable>
							<input type="hidden" class="inputText  validate[required]"  name="jxsj" value="${dataMap['jxsj']}"/>
							次

							&#12288;&#12288;
							<label style="margin-left: 70px;">
								上机演示：
								<lable>${dataMap['sjys']}</lable>
								<input type="hidden" class="inputText  validate[required]"  name="sjys" value="${dataMap['sjys']}"/>
								次
							</label>
						</td>
					</tr>

					<tr>
						<td rowspan="2">出科考核</td>
						<td colspan="3">
							理论成绩：
							<c:if test="${ckk}">
								<%--<input  class="inputText validate[required,custom[number],max[100]]" style="width: 70px;" name="theoreResult" readonly="readonly" value="${empty formDataMap['theoreResult'] ? (empty outScore.theoryScore ? '':outScore.theoryScore):formDataMap['theoreResult']}"/>--%>
								<%--分--%>
								<%--<c:if test="${empty outScore || empty outScore.theoryScore}"><font color="red">该学员暂未参加出科考核</font></c:if>--%>
								<%--禅道 201 修改--%>
								<c:if test="${teacherWrite}">
									<input type="text" class="inputText validate[required,custom[number],max[100]]" style="width: 70px;" name="theoreResult"
										   value="${formDataMap['theoreResult']}"/>
								</c:if>
								<c:if test="${!teacherWrite}">
									<input  class="inputText validate[required,custom[number],max[100]]" style="width: 70px;" name="theoreResult" readonly="readonly" value="${empty formDataMap['theoreResult'] ? (empty outScore.theoryScore ? '':outScore.theoryScore):formDataMap['theoreResult']}"/>
									分
									<c:if test="${empty outScore || empty outScore.theoryScore}"><font color="red">该学员暂未参加出科考核</font></c:if>
								</c:if>
							</c:if>
							<c:if test="${!ckk}">
								<c:if test="${showEdit and teacherWrite}">
									<input type="text" class="inputText validate[required,custom[number],max[100]]" style="width: 70px;" name="theoreResult"
											 value="${formDataMap['theoreResult']}"/>
								</c:if>
								<c:if test="${!showEdit }">
									${formDataMap['theoreResult']}
									<input type="hidden" class="inputText validate[required,custom[number],max[100]]" style="width: 70px;" name="theoreResult"
											 value="${formDataMap['theoreResult']}"/>
								</c:if>
								分
							</c:if>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<c:set var="classVal" value="validate[required]"></c:set>
							<c:set var="readonly" value=""></c:set>
							<c:if test="${empty rec}">
								<c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER and cksh eq 'Y'}">
									<c:set var="classVal" value=""></c:set>
									<c:set var="readonly" value="readonly"></c:set>
								</c:if>
							</c:if>
							<c:if test="${not empty rec}">
								<c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER and not empty rec.managerAuditUserFlow }">
									<c:set var="classVal" value=""></c:set>
									<c:set var="readonly" value="readonly"></c:set>
								</c:if>
							</c:if>
							技能考核名称：
							<c:if test="${showEdit}">
								<input type="text" class="inputText ${classVal}" ${readonly}  name="skillName" value="${formDataMap['skillName']}" style="width: 90px;"/>
							</c:if>
							<c:if test="${!showEdit }">
								${formDataMap['skillName']}
								<input type="hidden" class="inputText ${classVal}" ${readonly} name="skillName" value="${formDataMap['skillName']}" style="width: 90px;"/>
							</c:if>
							&#12288;
							得分：
								<c:if test="${showEdit}">
									<input type="text" class="inputText  ${classVal}" ${readonly} style="width: 50px;" name="score" value="${formDataMap['score']}"/>
								</c:if>
								<c:if test="${!showEdit }">
									${formDataMap['score']}
									<input type="hidden" class="inputText ${classVal}" ${readonly} style="width: 50px;" name="score" value="${formDataMap['score']}"/>
								</c:if>
							 分； 
							 &#12288;
							 考官1：
							 <c:if test="${showEdit}">
							 	<input type="text" class="inputText  ${classVal}" ${readonly} style="width: 90px;" name="examiner1" value="${formDataMap['examiner1']}"/>
							 </c:if>
							 <c:if test="${!showEdit }">
							 	${formDataMap['examiner1']}
							 	<input type="hidden" class="inputText  ${classVal}" ${readonly} style="width: 90px;" name="examiner1" value="${formDataMap['examiner1']}"/>
							 </c:if>
							  &#12288;
							 考官2：
							 <c:if test="${showEdit}">
							 	<input type="text" class="inputText  ${classVal}" ${readonly} style="width: 90px;" name="examiner2" value="${formDataMap['examiner2']}"/>
							 </c:if>
							  <c:if test="${!showEdit }">
							  	${formDataMap['examiner2']}
							  	<input type="hidden" class="inputText  ${classVal}" ${readonly} style="width: 90px;" name="examiner2" value="${formDataMap['examiner2']}"/>
							  </c:if>
						</td>
					</tr>
					<tr>
						<td>所在科室考核小组总体评价</td>
						<td colspan="3">
						<c:if test="${showEdit}">
							<label><input type="checkbox" class="autoValue" name="szkskhxzztpj" onchange="single(this)" value="1" <c:if test="${formDataMap['szkskhxzztpj_id']=='1'}">checked</c:if>/>&#12288;通过 &#12288;&#12288;&#12288;</label>
							 &#12288;
							<label><input type="checkbox"  class="autoValue" name="szkskhxzztpj" onchange="single(this)" value="0" <c:if test="${formDataMap['szkskhxzztpj_id']=='0'}">checked</c:if>/>&#12288;不通过 &#12288;&#12288;&#12288;</label>
						</c:if>
<%--						<c:if test="${!showEdit }">--%>
<%--							<c:if test="${!empty formDataMap['szkskhxzztpj']}">--%>
<%--									${formDataMap['szkskhxzztpj']}--%>
<%--							</c:if>--%>
<%--							<input type="hidden" name="szkskhxzztpj" value="${formDataMap['szkskhxzztpj']}"/>	--%>
<%--						</c:if>--%>
							<c:if test="${!showEdit }">
								<c:if test="${!empty formDataMap['szkskhxzztpj_id']}">
									<c:if test="${formDataMap['szkskhxzztpj_id']=='1'}">通过</c:if>
									<c:if test="${formDataMap['szkskhxzztpj_id']=='0'}">不通过</c:if>
								</c:if>
								<input type="hidden" name="szkskhxzztpj" value="${formDataMap['szkskhxzztpj_id']}"/>
							</c:if>
						</td>
					</tr>

					<tr>
						<td>带教老师评价</td>
						<td colspan="3">
							<c:if test="${showEdit}">
							<textarea id="teacherComment" name="teacherComment" placeholder="请填写评语" style="width: 98%"
									  class="text-input"></textarea>
							</c:if>
							<c:if test="${!showEdit }">
								<textarea id="teacherComment" name="teacherComment" style="width: 98%" readonly class="text-input"></textarea>
							</c:if>
						</td>
					</tr>

					<tr>
						<td>带教老师签名</td>
						<td colspan="3">
							<div style="float: left;">
								<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId)}">
									<label>${sessionScope.currUser.userName}</label>
									<input type="hidden" name="teacherName" value="${sessionScope.currUser.userName}"/>
								</c:if>
								<c:if test="${!(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId)}">
									<label>${formDataMap['teacherName']}</label>
									<input type="hidden" name="teacherName" value="${formDataMap['teacherName']}"/>
								</c:if>
							</div>
							<div style="float: right;padding-right: 10px;">日期：
							<span style="width: 150px;display: inline-block;">
								<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId)}">
										<input type="text" class="inputText  validate[required]" name="teacherDate" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${empty formDataMap['teacherDate']?pdfn:getCurrDate():formDataMap['teacherDate']}"/>
								</c:if>
								<c:if test="${!(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) }">
									${formDataMap['teacherDate']}
									<input type="hidden"  name="teacherDate" value="${formDataMap['teacherDate']}"/>
								</c:if>
							</span>
							</div>
						</td>
					</tr>
					<tr>
						<td>科主任签名</td>
						<td colspan="3">
							<div style="float: left;">
								<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
									<label>${sessionScope.currUser.userName}</label>
									<input type="hidden" name="directorName" value="${sessionScope.currUser.userName}"/>
								</c:if>
								<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_SECRETARY) }">
									<label>${sessionScope.currUser.userName}/${resDoctorSchProcess.headUserName}</label>
									<input type="hidden" name="directorName" value="${sessionScope.currUser.userName}/${resDoctorSchProcess.headUserName}"/>
								</c:if>
								<c:if test="${!(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD or param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_SECRETARY) }">
									<label>${formDataMap['directorName']}</label>
									<input type="hidden" name="directorName" value="${formDataMap['directorName']}"/>
								</c:if>
							</div>
							<div style="float: right;padding-right: 10px;">日期：
							<span style="width:150px;display: inline-block;">
								<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
									<input type="text" class="inputText  validate[required]" name="directorDate" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${empty formDataMap['directorDate']?pdfn:getCurrDate():formDataMap['directorDate']}"/>
								</c:if>
								<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_SECRETARY) }">
									<input type="text" class="inputText  validate[required]" name="directorDate" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${empty formDataMap['directorDate']?pdfn:getCurrDate():formDataMap['directorDate']}"/>
								</c:if>
								<c:if test="${!(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD or param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_SECRETARY) }">
									${formDataMap['directorDate']}
									<input type="hidden"  name="directorDate" value="${formDataMap['directorDate']}"/>
								</c:if>
							</span>
							</div>
						</td>
					</tr>
				</table>
				<p align="center" style="margin-top: 10px;">
                    <c:if test="${statusId ne 'Passed'}">
                        <input class="btn_green" type="button" value="无操作权限，请联系基地管理员"  />
                    </c:if>
                    <c:if test="${statusId eq 'Passed'}">
                        <c:if test="${empty rec}">
                            <c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER}">
                                <input class="btn_green" type="button" value="保&#12288;存"  onclick="save();"/>
                            </c:if>
                        </c:if>
                        <c:if test="${not empty rec}">
                            <c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER and empty rec.managerAuditUserFlow  }">
                                <input class="btn_green" type="button" value="保&#12288;存"  onclick="save();"/>
                            </c:if>
                            <c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD and not empty rec.managerAuditUserFlow }">
                                <input class="btn_green" type="button" value="保&#12288;存"  onclick="save();"/>
                            </c:if>
                            <c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_SECRETARY and not empty rec.managerAuditUserFlow }">
                                <input class="btn_green" type="button" value="保&#12288;存"  onclick="save();"/>
                            </c:if>
                        </c:if>
                    </c:if>
						<%--&#12288;<input class="btn_green" type="button" value="关&#12288;闭"  onclick="top.jboxClose();"/>--%>
				</p>
			</form>
		</div>
	</div>
</body>
</html>