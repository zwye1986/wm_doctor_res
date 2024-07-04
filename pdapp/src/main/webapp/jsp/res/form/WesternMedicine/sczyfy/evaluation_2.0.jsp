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
function sumScore()
{

	var $item = $(".score");
	var zhonghe = 0;
	$item.each(function(){
		var value = this.value;
		if(value && !isNaN(value)){
			var numItem = parseFloat(value);
			zhonghe+=numItem;
		}
	});
	zhonghe=zhonghe.toFixed(2);
	$("#hj").val(zhonghe);
}
$(function(){  
	var url = "<s:url value='/res/rec/evaluationSun'/>?operUserFlow=${param.operUserFlow}"+"&processFlow=${param.processFlow}";
	top.jboxPost(url,null,function(resp){
		$("#blsywc").val(resp.caseRegistryReqNum);
		$("#blsyjwc").val(resp.caseRegistryFinished);
		$("#blswcbl").val(resp.caseRegistry);
		
		$("#blsywcs").text(resp.caseRegistryReqNum);
		$("#blsyjwcs").text(resp.caseRegistryFinished);
		$("#blswcbls").text(resp.caseRegistry);
		
		$("#mzsywc").val(resp.caseRecordNum);
		$("#mzsyjwc").val(resp.caseRecordFinished);
		$("#mzswcbl").val(resp.caseRecord);
		
		$("#mzsywcs").text(resp.caseRecordNum);
		$("#mzsyjwcs").text(resp.caseRecordFinished);
		$("#mzswcbls").text(resp.caseRecord);
		
		$("#czsywc").val(resp.skillRegistryReqNum);
		$("#czsyjwc").val(resp.skillRegistryFinished);
		$("#czswcbl").val(resp.skillRegistry);
		
		$("#czsywcs").text(resp.skillRegistryReqNum);
		$("#czsyjwcs").text(resp.skillRegistryFinished);
		$("#czswcbls").text(resp.skillRegistry);
		
		$("#hdsywc").val(resp.campaignRegistryNum);
		$("#hdsyjwc").val(resp.campaignRegistryFinished);
		$("#hdswcbl").val(resp.campaignRegistry);
		
		$("#hdsywcs").text(resp.campaignRegistryNum);
		$("#hdsyjwcs").text(resp.campaignRegistryFinished);
		$("#hdswcbls").text(resp.campaignRegistry);
	},null,false);
	var url = "<s:url value='/res/rec/hdxsevaluationSun'/>?operUserFlow=${param.operUserFlow}"+"&processFlow=${param.processFlow}";
	top.jboxPost(url,null,function(resp){
		var huoDong="";
		if("${formDataMap['hdxs']}"!=""){
			$("#huo").show();
		}else{
			for(var i in resp){
				huoDong=huoDong+resp[i].itemName+" : "+
				resp[i].count+" 次   ";
				if(resp[i].itemName!=null&&resp[i].itemName!=""){
					var div=$("<div></div>");
					div.css({
						"float":"left",
						"margin-left":"15px",
					}).text(
							resp[i].itemName+" : "+
							resp[i].count+" 次");
					$("#div").append(div);
				}
			};
			$("#hdxs").val(huoDong);
		}
	},null,false);
	var urlr = "<s:url value='/res/rec/leaveRecordSun'/>?schDeptFlow=${doctorSchProcess.schDeptFlow}&userFlow=${param.operUserFlow}";
	top.jboxPost(urlr,null,function(resp){
		$("#leave").val(resp.leave);
		$("#sickLeave").val(resp.sickleave);
		$("#materLeave").val(resp.maternityleave);
		$("#absenteeism").val(resp.marriage);
		if(resp.schDoctorAbsenceList>0){
			$("#quan").text("非全勤   ");
		}else{
			$("#quan").text("全勤   ");
			$("#yin").hide();
		}
	},null,false);
	var $totalScore = $('[name="totalScore"]');
	if($totalScore.length && !$totalScore.val()){
		jboxPost("<s:url value='/res/score/getScoreByProcess'/>?processFlow=${param.processFlow}",null,function(resp){
			if(resp){
				$totalScore.val(resp).attr("readonly",true);
			}
		},null,false);
	}
});  
function save(){
	if($("#evaluationForm").validationEngine("validate")){
		/* var checkLength=$(".c:checked").length;
		if(checkLength<=0){
			jboxTip("请选择是否同意出科！");
			return false;
		} */
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
				<input type="hidden" name="hdxs" id="hdxs" value="${formDataMap['hdxs']}"/> 
				<input type="hidden" name="processFlow" value="${currRegProcess.processFlow}"/>
				<c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD}">
					 <input type="hidden" name="headAuditStatusId" value="${recStatusEnumHeadAuditY.id}"/>
				 </c:if>
				<c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER}">
					 <input type="hidden" name="auditStatusId" value="${recStatusEnumTeacherAuditY.id}"/>
				 </c:if>
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
					<input type="text" class="inputText"  name="sessional" value="${empty formDataMap['sessional']?doctor.sessionNumber:formDataMap['sessional']}"/>&#12288;
				</c:if>
				<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
					${formDataMap['sessional']}
					<input type="hidden" class="inputText"  name="sessional" value="${formDataMap['sessional']}"/>&#12288;
				</c:if>					
				&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;
					培训专业：
					<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
						<input type="text" class="inputText"  name="trainMajor" value="${empty formDataMap['trainMajor']?doctor.trainingSpeName:formDataMap['trainMajor']}"/>
					</c:if>
					<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
						${formDataMap['trainMajor']}
						<input type="hidden" class="inputText"  name="trainMajor" value="${formDataMap['trainMajor']}"/>
					</c:if>
					
				</p>
				<table class="basic" width="100%" style="margin-top: 10px;">
					<tr>
						<td width="20%">轮转科室名称：</td>
						<td width="30%">
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText"  name="deptName" value="${doctorSchProcess.schDeptName}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								${formDataMap['deptName']}
								<input type="hidden" class="inputText"  name="deptName" value="${formDataMap['deptName']}"/>
							</c:if>
						</td>
						<td width="10%">轮转时间：</td>
						<td  width="40%" colspan="2">
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" name="cycleTimeQ" value="${empty formDataMap['cycleTimeQ']?arrangeResult.schStartDate:formDataMap['cycleTimeQ']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								${formDataMap['cycleTimeQ']}
								<input type="hidden" class="inputText"  name="cycleTimeQ" value="${formDataMap['cycleTimeQ']}"/>
							</c:if>
							至
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" name="cycleTimeH" value="${empty formDataMap['cycleTimeH']?arrangeResult.schEndDate:formDataMap['cycleTimeH']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								${formDataMap['cycleTimeH']}
								<input type="hidden" class="inputText"  name="cycleTimeH" value="${formDataMap['cycleTimeH']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td colspan="2">评价内容</td>
						<td>要求</td>
						<td>满分</td>
						<td>得分</td>
					</tr>
					<tr>
						<td rowspan="5">医德医风人际沟通团队合作</td>
						<td>遵守国家法律法规、医院规章制度</td>
						<td rowspan="5">投诉一次扣1分</td>
						<td rowspan="5">5</td>
						<td>
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<label><input type="text"  name="zsgjflfg" value="${formDataMap['zsgjflfg']}"class="validate[required,custom[number],min[0],max[1]] score inputText" onchange="sumScore()" /></label>
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
								<label><input type="text"  name="lxgwzz" value="${formDataMap['lxgwzz']}"class="validate[required,custom[number],min[0],max[1]] score inputText" onchange="sumScore()" /></label>
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
								<label><input type="text"  name="ybrwzx" value="${formDataMap['ybrwzx']}"class="validate[required,custom[number],min[0],max[1]] score inputText" onchange="sumScore()" /></label>
							</c:if>
								<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<c:if test="${!empty formDataMap['ybrwzx']}">
									${formDataMap['ybrwzx']}
								</c:if>
								<input type="hidden" name="ybrwzx" value="${formDataMap['ybrwzx']}"/>
								</c:if>
						</td>
					</tr>
					<tr>
						<td>人际（医患）沟通和表达能力</td>
						<td colspan="2">
								<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
									<label><input type="text"  name="rjgtbdnl" value="${formDataMap['rjgtbdnl']}"class="validate[required,custom[number],min[0],max[1]] score inputText" onchange="sumScore()" /></label>
								</c:if>
								<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<c:if test="${!empty formDataMap['rjgtbdnl']}">
									${formDataMap['rjgtbdnl']}
								</c:if>
								<input type="hidden" name="rjgtbdnl" value="${formDataMap['rjgtbdnl']}"/>
								</c:if>
						</td>
					</tr>
					<tr>
						<td>团结协作精神</td>
						<td colspan="2">
								<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
									<label><input type="text"  name="tjxzjsxm" value="${formDataMap['tjxzjsxm']}"class="validate[required,custom[number],min[0],max[1]] score inputText" onchange="sumScore()" /></label>
								</c:if>
								<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
									<c:if test="${!empty formDataMap['tjxzjsxm']}">
										${formDataMap['tjxzjsxm']}
									</c:if>
									<input type="hidden" name="tjxzjsxm" value="${formDataMap['tjxzjsxm']}"/>
								</c:if>
						</td>
					</tr>
						<tr>
						<td rowspan="3">临床综合能力</td>
						<td>文书书写（每个月手写2份大病历：入院记录+首次病程记录，需带教老师修改签字，规培秘书审核完后病历原件交由学员留存备查）</td>
						<td>抽查病例，大病历份数（缺一份扣3分）、处方，医嘱等。</td>
						<td>15</td>
						<td>
								<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
									<label><input type="text"  name="jbllzwcd" value="${formDataMap['jbllzwcd']}"class="validate[required,custom[number],min[0],max[15]] score inputText" onchange="sumScore()" /></label>
								</c:if>
								<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
									<c:if test="${!empty formDataMap['jbllzwcd']}">
										${formDataMap['jbllzwcd']}
									</c:if>
									<input type="hidden" name="jbllzwcd" value="${formDataMap['jbllzwcd']}"/>
								</c:if>
						</td>
					</tr>
					<tr>
						<td>临床诊疗能力</td>
						<td>常见病诊断和鉴别，每出现一个不掌握、不熟悉、不了解，各扣1分</td>
						<td>5</td>
						<td>
								<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
									<label><input type="text"  name="lczlnl" value="${formDataMap['lczlnl']}"class="validate[required,custom[number],min[0],max[5]] score inputText" onchange="sumScore()" /></label>
								</c:if>
								<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
									<c:if test="${!empty formDataMap['lczlnl']}">
										${formDataMap['lczlnl']}
									</c:if>
									<input type="hidden" name="lczlnl" value="${formDataMap['lczlnl']}"/>
								</c:if>
						</td>
					</tr>
					<tr>
						<td>危重病人的识别及紧急处理能力</td>
						<td>急、危重病人的处置或抢救</td>
						<td>3</td>
						<td>
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<label><input type="text"  name="jjclnl" value="${formDataMap['jjclnl']}"class="validate[required,custom[number],min[0],max[3]] score inputText" onchange="sumScore()" /></label>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<c:if test="${!empty formDataMap['jjclnl']}">
										${formDataMap['jjclnl']}
									</c:if>
								<input type="hidden" name="jjclnl" value="${formDataMap['jjclnl']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td rowspan="4">临床实践指标完成情况</td>
						<td>
							<div style="text-align: center;">
							完成病历数：应完成
							<c:if test="${empty rec.headAuditStatusId && empty rec.auditStatusId}">
								<label id="blsywcs"></label>
								<input type="hidden"  id="blsywc" name="blsywc" value=""/>
							</c:if>
							<c:if test="${!(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || !(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								${formDataMap['blsywc']}
								<input type="hidden" class="inputText" style="width: 70px;" name="blsywc" value="${formDataMap['blsywc']}"/>
							</c:if>
							例，
							已完成
							<c:if test="${empty rec.headAuditStatusId && empty rec.auditStatusId}">
								<label id="blsyjwcs"></label>
								<input type="hidden"  id="blsyjwc" name="blsyjwc" value=""/>
							</c:if>
							<c:if test="${!(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || !(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								${formDataMap['blsyjwc']}
								<input type="hidden" class="inputText" style="width: 70px;" name="blsyjwc" value="${formDataMap['blsyjwc']}"/>
							</c:if>
							例，
							完成比例
							<c:if test="${empty rec.headAuditStatusId && empty rec.auditStatusId}">
								<label id="blswcbls"></label>
								<input type="hidden"  id="blswcbl" name="blswcbl" value=""/>
							</c:if>
							<c:if test="${!(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || !(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								${formDataMap['blswcbl']}
								<input type="hidden" class="inputText" style="width: 70px;" name="blswcbl" value="${formDataMap['blswcbl']}"/>
							</c:if>
							%
							</div>
						</td>
						<td rowspan="4">完成比例85%以上的全分，75%-84%得7.5分,60%-74%得3.5分。60%以下不得分</td>
						<td>15</td>
						<td>
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<label><input type="text"  name="blsscore" value="${formDataMap['blsscore']}"class="validate[required,custom[number],min[0],max[15]] score inputText" onchange="sumScore()" /></label>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<c:if test="${!empty formDataMap['blsscore']}">
										${formDataMap['blsscore']}
									</c:if>
								<input type="hidden" name="blsscore" value="${formDataMap['blsscore']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td>
						<div style="text-align: center;">
							门诊病例数：应完成
							<c:if test="${empty rec.headAuditStatusId && empty rec.auditStatusId}">
								<label id="mzsywcs"></label>
								<input type="hidden"  id="mzsywc" name="mzsywc" value=""/>
							</c:if>
							<c:if test="${!(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || !(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								${formDataMap['mzsywc']}
								<input type="hidden" class="inputText" style="width: 70px;" name="mzsywc" value="${formDataMap['mzsywc']}"/>
							</c:if>
							例，
							已完成
							<c:if test="${empty rec.headAuditStatusId && empty rec.auditStatusId}">	
								<label id="mzsyjwcs"></label>
								<input type="hidden"  id="mzsyjwc" name="mzsyjwc" value=""/>
							</c:if>
							<c:if test="${!(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || !(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								${formDataMap['mzsyjwc']}
								<input type="hidden" class="inputText" style="width: 70px;" name="mzsyjwc" value="${formDataMap['mzsyjwc']}"/>
							</c:if>
							例，
							完成比例
							<c:if test="${empty rec.headAuditStatusId && empty rec.auditStatusId}">
								<label id="mzswcbls"></label>
								<input type="hidden"  id="mzswcbl" name="mzswcbl" value=""/>
							</c:if>
							<c:if test="${!(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || !(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								${formDataMap['mzswcbl']}
								<input type="hidden" class="inputText" style="width: 70px;" name="mzswcbl" value="${formDataMap['mzswcbl']}"/>
							</c:if>
							%
							</div>
						</td>
						<td>15</td>
						<td>
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<label><input type="text"  name="mzsscore" value="${formDataMap['mzsscore']}"class="validate[required,custom[number],min[0],max[15]] score inputText" onchange="sumScore()" /></label>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<c:if test="${!empty formDataMap['mzsscore']}">
										${formDataMap['mzsscore']}
									</c:if>
								<input type="hidden" name="mzsscore" value="${formDataMap['mzsscore']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td>
						<div style="text-align: center;">
							完成操作数：应完成
							<c:if test="${empty rec.headAuditStatusId && empty rec.auditStatusId}">
								<label id="czsywcs"></label>
								<input type="hidden"  id="czsywc" name="czsywc" value=""/>
							</c:if>
							<c:if test="${!(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || !(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								${formDataMap['czsywc']}
								<input type="hidden" class="inputText" style="width: 70px;" name="czsywc" value="${formDataMap['czsywc']}"/>
							</c:if>
							例，
							已完成
							<c:if test="${empty rec.headAuditStatusId && empty rec.auditStatusId}">
								<label id="czsyjwcs"></label>
								<input type="hidden"  id="czsyjwc" name="czsyjwc" value=""/>
							</c:if>
							<c:if test="${!(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || !(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								${formDataMap['czsyjwc']}
								<input type="hidden" class="inputText" style="width: 70px;" name="czsyjwc" value="${formDataMap['czsyjwc']}"/>
							</c:if>
							例，
							完成比例
							<c:if test="${empty rec.headAuditStatusId && empty rec.auditStatusId}">
								<label id="czswcbls"></label>
								<input type="hidden"  id="czswcbl" name="czswcbl" value=""/>
							</c:if>
							<c:if test="${!(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || !(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								${formDataMap['czswcbl']}
								<input type="hidden" class="inputText" style="width: 70px;" name="czswcbl" value="${formDataMap['czswcbl']}"/>
							</c:if>
							%
							</div>
						</td>
						<td>10</td>
						<td>
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<label><input type="text"  name="czsscore" value="${formDataMap['czsscore']}"class="validate[required,custom[number],min[0],max[10]] score inputText" onchange="sumScore()" /></label>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<c:if test="${!empty formDataMap['czsscore']}">
										${formDataMap['czsscore']}
									</c:if>
								<input type="hidden" name="czsscore" value="${formDataMap['czsscore']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td>
						<div style="text-align: center;">
							参加活动数：应完成
							<c:if test="${empty rec.headAuditStatusId && empty rec.auditStatusId}">
								<label id="hdsywcs"></label>
								<input type="hidden"  id="hdsywc" name="hdsywc" value=""/>
							</c:if>
							<c:if test="${!(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || !(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								${formDataMap['hdsywc']}
								<input type="hidden" class="inputText" style="width: 70px;" name="hdsywc" value="${formDataMap['hdsywc']}"/>
							</c:if>
							例，
							已完成
							<c:if test="${empty rec.headAuditStatusId && empty rec.auditStatusId}">
								<label id="hdsyjwcs"></label>
								<input type="hidden"  id="hdsyjwc" name="hdsyjwc" value=""/>
							</c:if>
							<c:if test="${!(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || !(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								${formDataMap['hdsyjwc']}
								<input type="hidden" class="inputText" style="width: 70px;" name="hdsyjwc" value="${formDataMap['hdsyjwc']}"/>
							</c:if>
							例，
							完成比例
								<c:if test="${empty rec.headAuditStatusId && empty rec.auditStatusId}">
									<label id="hdswcbls"></label>
									<input type="hidden"  id="hdswcbl" name="hdswcbl" value=""/>	
								</c:if>
								<c:if test="${!(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || !(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
									${formDataMap['hdswcbl']}
									<input type="hidden" class="inputText" style="width: 70px;" name="hdswcbl" value="${formDataMap['hdswcbl']}"/>
								</c:if>
							%
							</div>
						</td>
						<td>10</td>
						<td>
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<label><input type="text"  name="hdsscore" value="${formDataMap['hdsscore']}"class="validate[required,custom[number],min[0],max[10]] score inputText" onchange="sumScore()" /></label>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<c:if test="${!empty formDataMap['hdsscore']}">
										${formDataMap['hdsscore']}
									</c:if>
								<input type="hidden" name="hdsscore" value="${formDataMap['hdsscore']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td  colspan="2">医疗事故</td>
						<td>发生医疗差错事故为0分</td>
						<td>5</td>
						<td>
								<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
									<label><input type="text"  name="ylsg" value="${formDataMap['ylsg']}"class="validate[required,custom[number],min[0],max[5]] score inputText" onchange="sumScore()" /></label>
								</c:if>
								<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
									<c:if test="${!empty formDataMap['ylsg']}">
										${formDataMap['ylsg']}
									</c:if>
									<input type="hidden" name="ylsg" value="${formDataMap['ylsg']}"/>
								</c:if>
						</td>
					</tr>
					<tr>
						<td  colspan="2">培训考核登记手册</td>
						<td>记录完整、整洁</td>
						<td>5</td>
						<td>
								<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
									<label><input type="text"  name="pxkhdjsc" value="${formDataMap['pxkhdjsc']}"class="validate[required,custom[number],min[0],max[5]] score inputText" onchange="sumScore()" /></label>
								</c:if>
								<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
									<c:if test="${!empty formDataMap['pxkhdjsc']}">
										${formDataMap['pxkhdjsc']}
									</c:if>
									<input type="hidden" name="pxkhdjsc" value="${formDataMap['pxkhdjsc']}"/>
								</c:if>
						</td>
					</tr>
					<tr>
						<td>考勤</td>
						<td>组织纪律、有无旷工、请假、迟到、早退、脱岗</td>
						<td>旷工一次扣2分，请假扣0.5分，迟到早退扣1分，脱岗扣全分</td>
						<td>12</td>
						<td>
								<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
									<label><input type="text"  name="kq" value="${formDataMap['kq']}"class="validate[required,custom[number],min[0],max[12]] score inputText" onchange="sumScore()" /></label>
								</c:if>
								<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
									<c:if test="${!empty formDataMap['kq']}">
										${formDataMap['kq']}
									</c:if>
									<input type="hidden" name="kq" value="${formDataMap['kq']}"/>
								</c:if>
						</td>
					</tr>
					<tr>
						<td colspan="3">合计</td>
						<td colspan="2">
								<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
									<label><input type="text"  name="hj" id="hj" readonly value="${formDataMap['hj']}"class="validate[required,custom[number],min[0],max[100]] inputText" /></label>
								</c:if>
								<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
									<c:if test="${!empty formDataMap['hj']}">
										${formDataMap['hj']}
									</c:if>
									<input type="hidden" name="hj" value="${formDataMap['hj']}"/>
								</c:if>
						</td>
					</tr>
					<tr>
						<td rowspan="2">出科考核</td>
						<td colspan="4">
							理论成绩：
							<c:set var="testSwitch" value="${sysCfgMap['res_after_test_switch'] eq GlobalConstant.FLAG_Y}"/>
							<c:set var="urlEmpt" value="${sysCfgMap['res_after_url_cfg'] != null and sysCfgMap['res_after_url_cfg'] != '' }"/>
							<c:set var="ckks" value="${open eq GlobalConstant.FLAG_Y}"/>
							<c:set var="testTypeFlag" value="${testSwitch and urlEmpt and ckks}"/>

							<c:choose>
								<c:when test="${!testTypeFlag}">
									<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
										<input type="text" class="inputText validate[required,custom[number],min[0],max[100]]" style="width: 70px;" name="totalScore" value="${formDataMap['totalScore']}"/>
									</c:if>
									<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
										${formDataMap['totalScore']}
										<input type="hidden" class="inputText validate[required,custom[number],min[0],max[100]]" style="width: 70px;" name="totalScore" value="${formDataMap['totalScore']}"/>
									</c:if>
								</c:when>
								<c:when test="${testTypeFlag}">
									<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
										<input type="text" class="inputText validate[required,custom[number],min[0],max[100]]" style="width: 70px;" name="totalScore" value="${empty formDataMap['totalScore'] ? (empty outScore.theoryScore ? '':outScore.theoryScore):formDataMap['totalScore']}" readonly="readonly"/>
									</c:if>
									<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
										${formDataMap['totalScore']}
										<input type="hidden" class="inputText validate[required,custom[number],min[0],max[100]]" style="width: 70px;" name="totalScore" value="${formDataMap['totalScore']}" />
									</c:if>
								</c:when>
							</c:choose>
							分；
							&#12288;
							考官1：
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText" style="width: 70px;" name="examiner11" value="${formDataMap['examiner11']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								${formDataMap['examiner11']}
								<input type="hidden" class="inputText" style="width: 70px;" name="examiner11" value="${formDataMap['examiner11']}"/>
							</c:if>
							&#12288;
							考官2：
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText" style="width: 70px;" name="examiner22" value="${formDataMap['examiner22']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								${formDataMap['examiner2']}
								<input type="hidden" class="inputText" style="width: 70px;" name="examiner22" value="${formDataMap['examiner22']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td colspan="4">
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
						<td rowspan="2">所在科室考核小组总体评价</td>
						<td colspan="4">
						<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
							<label><input type="checkbox"  name="szkskhxzztpj" onchange="single(this)" value="1" <c:if test="${formDataMap['szkskhxzztpj_id']=='1'}">checked</c:if> class="autoValue"/>&#12288;通过 &#12288;&#12288;&#12288;</label>
							 &#12288;
							<label><input type="checkbox"  name="szkskhxzztpj" onchange="single(this)" value="0" <c:if test="${formDataMap['szkskhxzztpj_id']=='0'}">checked</c:if> class="autoValue"/>&#12288;不通过 &#12288;&#12288;&#12288;</label>
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
						<td colspan="4">
						<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
							<textarea style="width:99%; border:1px solid #bdbebe;	height:150px;	margin:5px 5px 5px 0px" class="ctrlInput validate[required]"  name="opinion">${formDataMap['opinion']}</textarea>
						</c:if>
						<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
							<textarea style="width:99%; border:1px solid #bdbebe;	height:150px;	margin:5px 5px 5px 0px" readonly class="ctrlInput validate[required]"  name="opinion">${formDataMap['opinion']}</textarea>
						</c:if>
						</td>
					</tr>
					<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
						<tr>
							<td>是否同意出科</td>
							<td colspan="4">
								<label><input type="checkbox"   name="isAgree" onchange="single(this)" value="Y" <c:if test="${formDataMap['isAgree_id']=='Y'}">checked</c:if> class="autoValue validate[required]"/>&#12288;同意 &#12288;&#12288;&#12288;</label>
								 &#12288;
								<label><input type="checkbox" name="isAgree" onchange="single(this)" value="N" <c:if test="${formDataMap['isAgree_id']=='N'}">checked</c:if> class="autoValue validate[required]"/>&#12288;不同意 &#12288;&#12288;&#12288;</label>
							</td>
						</tr>
					</c:if>
					<c:if test="${!(empty rec.headAuditStatusId) }">
						<tr>
							<td>是否同意出科</td>
							<td colspan="4">
								<c:if test="${!empty formDataMap['isAgree']}">
									${formDataMap['isAgree']}
								</c:if>
								<input type="hidden" name="isAgree" value="${formDataMap['isAgree']}"/>	
							</td>
						</tr>
					</c:if>
					<tr>
						<td>带教老师签名</td>
						<td colspan="4">
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
										<input type="text" class="inputText" name="teacherDate" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${empty formDataMap['teacherDate']?pdfn:getCurrDate():formDataMap['teacherDate']}"/>
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
						<td colspan="4">
							<div style="float: left;">
								<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
									<label>${sessionScope.currUser.userName}</label>
									<input type="hidden" name="directorName" value="${sessionScope.currUser.userName}"/>
								</c:if>
								<c:if test="${!(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
									<label>${formDataMap['directorName']}</label>
									<input type="hidden" name="directorName" value="${formDataMap['directorName']}"/>
								</c:if>
							</div>
							<div style="float: right;padding-right: 10px;">日期：
							<span style="width:150px;display: inline-block;">
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
									<input type="text" class="inputText" name="directorDate" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${empty formDataMap['directorDate']?pdfn:getCurrDate():formDataMap['directorDate']}"/>
							</c:if>
							<c:if test="${!(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								${formDataMap['directorDate']}
								<input type="hidden"  name="directorDate" value="${formDataMap['directorDate']}"/>
							</c:if>
							</span>
							</div>
						</td>
					</tr>
				</table>
				<table class="basic" width="100%" style="margin-top: 10px;">
				</table>
				<p align="center" style="margin-top: 10px;">
					<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD&&  empty rec.auditStatusId)}">
						[<font color="red">带教老师还未审核，请等待！</font>]
					</c:if>
					<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD&& not empty rec.auditStatusId)
					 ||( param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER&& empty rec.auditStatusId)}">
						<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER and not empty summary and not empty summary.auditStatusId )
						||(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD and not empty summary and not empty summary.headAuditStatusId )}">
							<c:set var="f" value="true"></c:set>
							<c:if test="${testTypeFlag}">
								<c:if test="${(empty outScore || empty outScore.theoryScore)&&(empty formDataMap['totalScore'])}">
									<c:set var="f" value="false"></c:set>
									<font color="red">该学员暂未参加出科考核!</font>
								</c:if>
							</c:if>
							<c:if test="${f}">
								<input class="search" type="button" value="保&#12288;存"  onclick="save();"/>
							</c:if>
						</c:if>
						<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER and not empty summary and not empty summary.auditStatusId )
						||(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD and not empty summary and not empty summary.headAuditStatusId ))}">
							<font color="red">请先审核出科小结表！</font>
						</c:if>
					</c:if>
					<input class="search" type="button" value="关&#12288;闭"  onclick="jboxClose();"/>
				</p>
			</form>
		</div>
	</div>
</body>
</html>