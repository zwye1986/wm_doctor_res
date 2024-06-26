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
$(function(){
	$(".num :checkbox").change(function(){
		checkSum();
	});
	var dopsId="${afterRecTypeEnumDOPS.id}";
	var miniCexId="${afterRecTypeEnumMini_CEX.id}";
	var data={
			processFlow:"${currRegProcess.processFlow}"
	};
	<c:if test="${param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER || param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD}">
	jboxPost("<s:url value='/res/rec/getRecDatas'/>?recTypeId="+dopsId+"&recTypeId="+miniCexId,data,function(resp){
		if(resp){
			var dopsIdMaps=resp[dopsId];
			var miniCexIdMaps=resp[miniCexId];
			if(dopsIdMaps){
				var dopsIdMap=dopsIdMaps.formDataMap;
				if(dopsIdMap){
					var dopsIdScore=dopsIdMap.ZongHe;	
					$("#basicSkills").val(dopsIdScore);
				}
			}
			if(miniCexIdMaps){
				var miniCexIdMap=miniCexIdMaps.formDataMap;
				if(miniCexIdMap){
					var miniCexIdScore=miniCexIdMap.ZongHe;
					$("#treatAbility").val(miniCexIdScore);
				}
			}
			check();
		}
	},null,false);
	</c:if>
});

function checkSum(){
	var zong=0;
	$(".num :checkbox:checked").each(function(i,n){
		var num = parseInt($(n).val());
		zong+=num;
	});
	$("#ZongHe").val(zong);
	$('#dailyEvalua').val(zong);
	check();
}
function save(){
	if($("#evaluationForm").validationEngine("validate")){
		jboxConfirm("确认保存？保存之后不可修改！",function(){
		jboxPost("<s:url value='/res/rec/saveRegistryFormNew'/>",$("#evaluationForm").serialize(),function(resp){
			if(resp=="${GlobalConstant.SAVE_SUCCESSED}"){	
				window.parent.document.mainIframe.location.reload();
			   jboxClose();
			}				
		},null,true);
		});
	}
}
function isInt(num){
	return !isNaN(num) && num!="";
}
var countCfg=[
             {"id":"theoryscore",
            	 "per":0.1},
           	 {"id":"treatAbility",
               	 "per":0.35},
             {"id":"basicSkills",
               	 "per":0.25}
             ];
var countCfg2=[
		{"id":"dailyEvalua",
			"per":0.3},
		{"id":"score",
			"per":0.3}
             ];
function check(){
	var sum = 0;
	for(var index in countCfg){
		var ele = countCfg[index];
		var val = $("#"+ele.id).val();
		if(isInt(val)){
			var a = ele.per;
			sum+=(val*a);
		}else{
			val=0;
		}
	}
	var sum2=0;
	for(var index in countCfg2){
		var ele = countCfg2[index];
		var val = $("#"+ele.id).val();
		if(isInt(val)){
			var a = ele.per;
			sum2+=(val*a);
		}else{
			val=0;
		}
	}
	sum2=sum2/countCfg2.length;
	sum+=sum2;
	sum=sum.toFixed(2);
	$("#total input").val(sum);
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
				 <c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD}">
					 <input type="hidden" name="headAuditStatusId" value="${recStatusEnumHeadAuditY.id}"/>
				 </c:if>
				  <c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER}">
						 <input type="hidden" name="auditStatusId" value="${recStatusEnumTeacherAuditY.id}"/>
					 </c:if>
				<table class="basic" width="100%" style="margin-top: 10px;">
					<tr>
						<th style="text-align: center; font-size: 15px;" colspan="6">日常评价表</th>
					</tr>
					<tr>
						<td class="num">
							<div>1、组织纪律，有无无旷工、迟到、早退情况，有无擅自脱岗、窜岗</div>
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
							<div>有待加强：
								<label><input type="checkbox" onchange="single(this)"  name="discipEvalua" value="1" <c:if test="${formDataMap['discipEvalua']=='1'}">checked</c:if>/>&nbsp;1&nbsp;</label>
								<label><input type="checkbox" onchange="single(this)"  name="discipEvalua" value="2" <c:if test="${formDataMap['discipEvalua']=='2'}">checked</c:if>/>&nbsp;2&nbsp;</label>
								<label><input type="checkbox" onchange="single(this)"  name="discipEvalua" value="3" <c:if test="${formDataMap['discipEvalua']=='3'}">checked</c:if>/>&nbsp;3&nbsp;</label>&#12288;&#12288;
								合格：<label><input type="checkbox" onchange="single(this)"  name="discipEvalua" value="4" <c:if test="${formDataMap['discipEvalua']=='4'}">checked</c:if>/>&nbsp;4&nbsp;</label>
									 <label><input type="checkbox" onchange="single(this)"  name="discipEvalua" value="5" <c:if test="${formDataMap['discipEvalua']=='5'}">checked</c:if>/>&nbsp;5&nbsp;</label>
									 <label><input type="checkbox" onchange="single(this)"  name="discipEvalua" value="6" <c:if test="${formDataMap['discipEvalua']=='6'}">checked</c:if>/>&nbsp;6&nbsp;</label>&#12288;&#12288;
								优良：<label><input type="checkbox" onchange="single(this)"  name="discipEvalua" value="7" <c:if test="${formDataMap['discipEvalua']=='7'}">checked</c:if>/>&nbsp;7&nbsp;</label>
									 <label><input type="checkbox" onchange="single(this)"  name="discipEvalua" value="8" <c:if test="${formDataMap['discipEvalua']=='8'}">checked</c:if>/>&nbsp;8&nbsp;</label>
									<label><input type="checkbox" onchange="single(this)"  name="discipEvalua" value="9" <c:if test="${formDataMap['discipEvalua']=='9'}">checked</c:if>/>&nbsp;9&nbsp;</label>
							</div>
								</c:if>
								<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
											<c:if test="${!empty formDataMap['discipEvalua'] && (fn:indexOf('123',formDataMap['discipEvalua'])>-1)}">
												有待加强：${formDataMap['discipEvalua']}
											</c:if>
											<c:if test="${!empty  formDataMap['discipEvalua'] && (fn:indexOf('456', formDataMap['discipEvalua'])>-1)}">
												合格：${ formDataMap['discipEvalua']}
											</c:if>
											<c:if test="${!empty  formDataMap['discipEvalua'] && (fn:indexOf('789', formDataMap['discipEvalua'])>-1)}">
											优良：${ formDataMap['discipEvalua']}
											</c:if>
											<input type="hidden" name="discipEvalua" value="${formDataMap['discipEvalua']}"/>
								</c:if>
								
						</td>
					</tr>
					<tr>
						<td class="num">
							<div>2、服务意识强、对待病人一视同仁；尊重患者权利、保守医疗秘密；恪守职业操守、工作责任心强；遵纪守法、廉洁行医；团结协作、严格规范医疗行为</div>
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<div>有待加强：<label><input type="checkbox" onchange="single(this)"  name="serviceEvalua" value="1" <c:if test="${formDataMap['serviceEvalua']=='1'}">checked</c:if>/>&nbsp;1&nbsp;</label>
											 <label><input type="checkbox" onchange="single(this)"  name="serviceEvalua" value="2" <c:if test="${formDataMap['serviceEvalua']=='2'}">checked</c:if>/>&nbsp;2&nbsp;</label>
											 <label><input type="checkbox" onchange="single(this)"  name="serviceEvalua" value="3" <c:if test="${formDataMap['serviceEvalua']=='3'}">checked</c:if>/></label>&nbsp;3&nbsp;</label>&#12288;&#12288;
									合格：<label><input type="checkbox" onchange="single(this)"  name="serviceEvalua" value="4" <c:if test="${formDataMap['serviceEvalua']=='4'}">checked</c:if>/>&nbsp;4&nbsp;</label>
										 <label><input type="checkbox" onchange="single(this)"  name="serviceEvalua" value="5" <c:if test="${formDataMap['serviceEvalua']=='5'}">checked</c:if>/>&nbsp;5&nbsp;</label>
										 <label><input type="checkbox" onchange="single(this)"  name="serviceEvalua" value="6" <c:if test="${formDataMap['serviceEvalua']=='6'}">checked</c:if>/>&nbsp;6&nbsp;</label>&#12288;&#12288;
									优良：<label><input type="checkbox" onchange="single(this)"  name="serviceEvalua" value="7" <c:if test="${formDataMap['serviceEvalua']=='7'}">checked</c:if>/>&nbsp;7&nbsp;</label>
											 <label><input type="checkbox" onchange="single(this)"  name="serviceEvalua" value="8" <c:if test="${formDataMap['serviceEvalua']=='8'}">checked</c:if>/>&nbsp;8&nbsp;</label>
											 <label><input type="checkbox" onchange="single(this)"  name="serviceEvalua" value="9" <c:if test="${formDataMap['serviceEvalua']=='9'}">checked</c:if>/>&nbsp;9&nbsp;</label>
								</div>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
									<c:if test="${!empty formDataMap['serviceEvalua'] && (fn:indexOf('123',formDataMap['serviceEvalua'])>-1)}">
										有待加强：${formDataMap['serviceEvalua']}
									</c:if>
									<c:if test="${!empty  formDataMap['serviceEvalua'] && (fn:indexOf('456', formDataMap['serviceEvalua'])>-1)}">
										合格：${ formDataMap['serviceEvalua']}
									</c:if>
									<c:if test="${!empty  formDataMap['serviceEvalua'] && (fn:indexOf('789', formDataMap['serviceEvalua'])>-1)}">
									优良：${ formDataMap['serviceEvalua']}
									</c:if>
									<input type="hidden" name="serviceEvalua" value="${formDataMap['serviceEvalua']}"/>
								</c:if>
						</td>
					</tr>
					<tr>
						<td class="num">
							<div>3、病种、例数、手术治疗数量，管理病人数量</div>
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<div>有待加强：<label><input type="checkbox" onchange="single(this)"  name="diseaseEvalua" value="1" <c:if test="${formDataMap['diseaseEvalua']=='1'}">checked</c:if>/>&nbsp;1&nbsp;</label>
											 <label><input type="checkbox" onchange="single(this)"  name="diseaseEvalua" value="2" <c:if test="${formDataMap['diseaseEvalua']=='2'}">checked</c:if>/>&nbsp;2&nbsp;</label>
											 <label><input type="checkbox" onchange="single(this)"  name="diseaseEvalua" value="3" <c:if test="${formDataMap['diseaseEvalua']=='3'}">checked</c:if>/>&nbsp;3&nbsp;</label>&#12288;&#12288;
									合格：<label><input type="checkbox" onchange="single(this)"  name="diseaseEvalua" value="4" <c:if test="${formDataMap['diseaseEvalua']=='4'}">checked</c:if>/>&nbsp;4&nbsp;</label>
										 <label><input type="checkbox" onchange="single(this)"  name="diseaseEvalua" value="5" <c:if test="${formDataMap['diseaseEvalua']=='5'}">checked</c:if>/>&nbsp;5&nbsp;</label>
										 <label><input type="checkbox" onchange="single(this)"  name="diseaseEvalua" value="6" <c:if test="${formDataMap['diseaseEvalua']=='6'}">checked</c:if>/>&nbsp;6&nbsp;</label>&#12288;&#12288;
									优良：<label><input type="checkbox" onchange="single(this)"  name="diseaseEvalua" value="7" <c:if test="${formDataMap['diseaseEvalua']=='7'}">checked</c:if>/>&nbsp;7&nbsp;</label>
											 <label><input type="checkbox" onchange="single(this)"  name="diseaseEvalua" value="8" <c:if test="${formDataMap['diseaseEvalua']=='8'}">checked</c:if>/>&nbsp;8&nbsp;</label>
											 <label><input type="checkbox" onchange="single(this)"  name="diseaseEvalua" value="9" <c:if test="${formDataMap['diseaseEvalua']=='9'}">checked</c:if>/>&nbsp;9&nbsp;</label>
								</div>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
									<c:if test="${!empty formDataMap['diseaseEvalua'] && (fn:indexOf('123',formDataMap['diseaseEvalua'])>-1)}">
										有待加强：${formDataMap['diseaseEvalua']}
									</c:if>
									<c:if test="${!empty  formDataMap['diseaseEvalua'] && (fn:indexOf('456', formDataMap['diseaseEvalua'])>-1)}">
										合格：${ formDataMap['diseaseEvalua']}
									</c:if>
									<c:if test="${!empty  formDataMap['diseaseEvalua'] && (fn:indexOf('789', formDataMap['diseaseEvalua'])>-1)}">
									优良：${ formDataMap['diseaseEvalua']}
									</c:if>
									<input type="hidden" name="diseaseEvalua" value="${formDataMap['diseaseEvalua']}"/>
								</c:if>
						</td>
					</tr>
					<tr>
						<td class="num">
							<div>4、基本技能成绩（临床科室用DOPS评量成绩)</div>
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<div>有待加强：<label><input type="checkbox" onchange="single(this)"  name="skillEvalua" value="1" <c:if test="${formDataMap['skillEvalua']=='1'}">checked</c:if>/>&nbsp;1&nbsp;</label>
											 <label><input type="checkbox" onchange="single(this)"  name="skillEvalua" value="2" <c:if test="${formDataMap['skillEvalua']=='2'}">checked</c:if>/>&nbsp;2&nbsp;</label>
											 <label><input type="checkbox" onchange="single(this)"  name="skillEvalua" value="3" <c:if test="${formDataMap['skillEvalua']=='3'}">checked</c:if>/>&nbsp;3&nbsp;</label>&#12288;&#12288;
									合格：<label><input type="checkbox" onchange="single(this)"  name="skillEvalua" value="4" <c:if test="${formDataMap['skillEvalua']=='4'}">checked</c:if>/>&nbsp;4&nbsp;</label>
										 <label><input type="checkbox" onchange="single(this)"  name="skillEvalua" value="5" <c:if test="${formDataMap['skillEvalua']=='5'}">checked</c:if>/>&nbsp;5&nbsp;</label>
										 <label><input type="checkbox" onchange="single(this)"  name="skillEvalua" value="6" <c:if test="${formDataMap['skillEvalua']=='6'}">checked</c:if>/>&nbsp;6&nbsp;</label>&#12288;&#12288;
									优良：<label><input type="checkbox" onchange="single(this)"  name="skillEvalua" value="7" <c:if test="${formDataMap['skillEvalua']=='7'}">checked</c:if>/>&nbsp;7&nbsp;</label>
											 <label><input type="checkbox" onchange="single(this)"  name="skillEvalua" value="8" <c:if test="${formDataMap['skillEvalua']=='8'}">checked</c:if>/>&nbsp;8&nbsp;</label>
											 <label><input type="checkbox" onchange="single(this)"  name="skillEvalua" value="9" <c:if test="${formDataMap['skillEvalua']=='9'}">checked</c:if>/>&nbsp;9&nbsp;</label>
								</div>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
									<c:if test="${!empty formDataMap['skillEvalua'] && (fn:indexOf('123',formDataMap['skillEvalua'])>-1)}">
										有待加强：${formDataMap['skillEvalua']}
									</c:if>
									<c:if test="${!empty  formDataMap['skillEvalua'] && (fn:indexOf('456', formDataMap['skillEvalua'])>-1)}">
										合格：${ formDataMap['skillEvalua']}
									</c:if>
									<c:if test="${!empty  formDataMap['skillEvalua'] && (fn:indexOf('789', formDataMap['skillEvalua'])>-1)}">
									优良：${ formDataMap['skillEvalua']}
									</c:if>
									<input type="hidden" name="skillEvalua" value="${formDataMap['skillEvalua']}"/>
								</c:if>
						</td>
					</tr>
					<tr>
						<td class="num">
							<div>5、诊疗能力评量成绩（临床科室用MINI-CEX评量成绩）</div>
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<div>有待加强：<label><input type="checkbox" onchange="single(this)"  name="treatEvalua" value="1" <c:if test="${formDataMap['treatEvalua']=='1'}">checked</c:if>/>&nbsp;1&nbsp;</label>
											 <label><input type="checkbox" onchange="single(this)"  name="treatEvalua" value="2" <c:if test="${formDataMap['treatEvalua']=='2'}">checked</c:if>/>&nbsp;2&nbsp;</label>
											 <label><input type="checkbox" onchange="single(this)"  name="treatEvalua" value="3" <c:if test="${formDataMap['treatEvalua']=='3'}">checked</c:if>/>&nbsp;3&nbsp;</label>&#12288;&#12288;
									合格：<label><input type="checkbox" onchange="single(this)"  name="treatEvalua" value="4" <c:if test="${formDataMap['treatEvalua']=='4'}">checked</c:if>/>&nbsp;4&nbsp;</label>
										 <label><input type="checkbox" onchange="single(this)"  name="treatEvalua" value="5" <c:if test="${formDataMap['treatEvalua']=='5'}">checked</c:if>/>&nbsp;5&nbsp;</label>
										 <label><input type="checkbox" onchange="single(this)"  name="treatEvalua" value="6" <c:if test="${formDataMap['treatEvalua']=='6'}">checked</c:if>/>&nbsp;6&nbsp;</label>&#12288;&#12288;
									优良：<label><input type="checkbox" onchange="single(this)"  name="treatEvalua" value="7" <c:if test="${formDataMap['treatEvalua']=='7'}">checked</c:if>/>&nbsp;7&nbsp;</label>
											 <label><input type="checkbox" onchange="single(this)"  name="treatEvalua" value="8" <c:if test="${formDataMap['treatEvalua']=='8'}">checked</c:if>/>&nbsp;8&nbsp;</label>
											 <label><input type="checkbox" onchange="single(this)"  name="treatEvalua" value="9" <c:if test="${formDataMap['treatEvalua']=='9'}">checked</c:if>/>&nbsp;9&nbsp;</label>
								</div>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
									<c:if test="${!empty formDataMap['treatEvalua'] && (fn:indexOf('123',formDataMap['treatEvalua'])>-1)}">
										有待加强：${formDataMap['treatEvalua']}
									</c:if>
									<c:if test="${!empty  formDataMap['treatEvalua'] && (fn:indexOf('456', formDataMap['treatEvalua'])>-1)}">
										合格：${ formDataMap['treatEvalua']}
									</c:if>
									<c:if test="${!empty  formDataMap['treatEvalua'] && (fn:indexOf('789', formDataMap['treatEvalua'])>-1)}">
									优良：${ formDataMap['treatEvalua']}
									</c:if>
									<input type="hidden" name="treatEvalua" value="${formDataMap['treatEvalua']}"/>
								</c:if>
						</td>
					</tr>
					<tr>
						<td class="num">
							<div>6、带教能力	</div>
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<div>有待加强：<label><input type="checkbox" onchange="single(this)"  name="abilityEvalua" value="1" <c:if test="${formDataMap['abilityEvalua']=='1'}">checked</c:if>/>&nbsp;1&nbsp;</label>
											 <label><input type="checkbox" onchange="single(this)"  name="abilityEvalua" value="2" <c:if test="${formDataMap['abilityEvalua']=='2'}">checked</c:if>/>&nbsp;2&nbsp;</label>
											 <label><input type="checkbox" onchange="single(this)"  name="abilityEvalua" value="3" <c:if test="${formDataMap['abilityEvalua']=='3'}">checked</c:if>/>&nbsp;3&nbsp;</label>&#12288;&#12288;
									合格：<label><input type="checkbox" onchange="single(this)"  name="abilityEvalua" value="4" <c:if test="${formDataMap['abilityEvalua']=='4'}">checked</c:if>/>&nbsp;4&nbsp;</label>
										 <label><input type="checkbox" onchange="single(this)"  name="abilityEvalua" value="5" <c:if test="${formDataMap['abilityEvalua']=='5'}">checked</c:if>/>&nbsp;5&nbsp;</label>
										 <label><input type="checkbox" onchange="single(this)"  name="abilityEvalua" value="6"  <c:if test="${formDataMap['abilityEvalua']=='6'}">checked</c:if>/>&nbsp;6&nbsp;</label>&#12288;&#12288;
									优良：<label><input type="checkbox" onchange="single(this)"  name="abilityEvalua" value="7" <c:if test="${formDataMap['abilityEvalua']=='7'}">checked</c:if>/>&nbsp;7&nbsp;</label>
											 <label><input type="checkbox" onchange="single(this)"  name="abilityEvalua" value="8" <c:if test="${formDataMap['abilityEvalua']=='8'}">checked</c:if>/>&nbsp;8&nbsp;</label>
											 <label><input type="checkbox" onchange="single(this)"  name="abilityEvalua" value="9" <c:if test="${formDataMap['abilityEvalua']=='9'}">checked</c:if>/>&nbsp;9&nbsp;</label>
								</div> 
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
									<c:if test="${!empty formDataMap['abilityEvalua'] && (fn:indexOf('123',formDataMap['abilityEvalua'])>-1)}">
										有待加强：${formDataMap['abilityEvalua']}
									</c:if>
									<c:if test="${!empty  formDataMap['abilityEvalua'] && (fn:indexOf('456', formDataMap['abilityEvalua'])>-1)}">
										合格：${ formDataMap['abilityEvalua']}
									</c:if>
									<c:if test="${!empty  formDataMap['abilityEvalua'] && (fn:indexOf('789', formDataMap['abilityEvalua'])>-1)}">
									优良：${ formDataMap['abilityEvalua']}
									</c:if>
									<input type="hidden" name="abilityEvalua" value="${formDataMap['abilityEvalua']}"/>
								</c:if>
						</td>
					</tr>
					<tr>
						<td class="num">
							<div>7、主动阅读相关书籍及文献</div>
								<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
										<div>有待加强：
										<label><input type="checkbox" onchange="single(this)"  name="readEvalua" value="1" <c:if test="${formDataMap['readEvalua']=='1'}">checked</c:if>/>&nbsp;1&nbsp;</label>
										 <label><input type="checkbox" onchange="single(this)"  name="readEvalua" value="2" <c:if test="${formDataMap['readEvalua']=='2'}">checked</c:if>/>&nbsp;2&nbsp;</label>
										 <label><input type="checkbox" onchange="single(this)"  name="readEvalua" value="3" <c:if test="${formDataMap['readEvalua']=='3'}">checked</c:if>/>&nbsp;3&nbsp;</label>&#12288;&#12288;
								合格：<label><input type="checkbox" onchange="single(this)"  name="readEvalua" value="4" <c:if test="${formDataMap['readEvalua']=='4'}">checked</c:if>/>&nbsp;4&nbsp;</label>
									 <label><input type="checkbox" onchange="single(this)"  name="readEvalua" value="5" <c:if test="${formDataMap['readEvalua']=='5'}">checked</c:if>/>&nbsp;5&nbsp;</label>
									 <label><input type="checkbox" onchange="single(this)"  name="readEvalua" value="6" <c:if test="${formDataMap['readEvalua']=='6'}">checked</c:if>/>&nbsp;6&nbsp;</label>&#12288;&#12288;
								优良：<label><input type="checkbox" onchange="single(this)"  name="readEvalua" value="7" <c:if test="${formDataMap['readEvalua']=='7'}">checked</c:if>/>&nbsp;7&nbsp;</label>
										 <label><input type="checkbox" onchange="single(this)"  name="readEvalua" value="8" <c:if test="${formDataMap['readEvalua']=='8'}">checked</c:if>/>&nbsp;8&nbsp;</label>
										 <label><input type="checkbox" onchange="single(this)"  name="readEvalua" value="9" <c:if test="${formDataMap['readEvalua']=='9'}">checked</c:if>/>&nbsp;9&nbsp;</label>
							</div>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
									<c:if test="${!empty formDataMap['readEvalua'] && (fn:indexOf('123',formDataMap['readEvalua'])>-1)}">
										有待加强：${formDataMap['readEvalua']}
									</c:if>
									<c:if test="${!empty  formDataMap['readEvalua'] && (fn:indexOf('456', formDataMap['readEvalua'])>-1)}">
										合格：${ formDataMap['readEvalua']}
									</c:if>
									<c:if test="${!empty  formDataMap['readEvalua'] && (fn:indexOf('789', formDataMap['readEvalua'])>-1)}">
									优良：${ formDataMap['readEvalua']}
									</c:if>
									<input type="hidden" name="readEvalua" value="${formDataMap['readEvalua']}"/>
								</c:if>
						</td>
					</tr>
					<tr>
						<td class="num">
							<div>8、对该生的整体评价	</div>
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
							<div>有待加强：<label><input type="checkbox" onchange="single(this)"  name="wholeEvalua" value="1" <c:if test="${formDataMap['wholeEvalua']=='1'}">checked</c:if>/>&nbsp;1&nbsp;</label>
										 <label><input type="checkbox" onchange="single(this)"  name="wholeEvalua" value="2" <c:if test="${formDataMap['wholeEvalua']=='2'}">checked</c:if>/>&nbsp;2&nbsp;</label>
										 <label><input type="checkbox" onchange="single(this)"  name="wholeEvalua" value="3" <c:if test="${formDataMap['wholeEvalua']=='3'}">checked</c:if>/>&nbsp;3&nbsp;</label>&#12288;&#12288;
								合格：<label><input type="checkbox" onchange="single(this)"  name="wholeEvalua" value="4" <c:if test="${formDataMap['wholeEvalua']=='4'}">checked</c:if>/>&nbsp;4&nbsp;</label>
									 <label><input type="checkbox" onchange="single(this)"  name="wholeEvalua" value="5" <c:if test="${formDataMap['wholeEvalua']=='5'}">checked</c:if>/>&nbsp;5&nbsp;</label>
									 <label><input type="checkbox" onchange="single(this)"  name="wholeEvalua" value="6" <c:if test="${formDataMap['wholeEvalua']=='6'}">checked</c:if>/>&nbsp;6&nbsp;</label>&#12288;&#12288;
								优良：<label><input type="checkbox" onchange="single(this)"  name="wholeEvalua" value="7" <c:if test="${formDataMap['wholeEvalua']=='7'}">checked</c:if>/>&nbsp;7&nbsp;</label>
										 <label><input type="checkbox" onchange="single(this)"  name="wholeEvalua" value="8" <c:if test="${formDataMap['wholeEvalua']=='8'}">checked</c:if>/>&nbsp;8&nbsp;</label>
										 <label><input type="checkbox" onchange="single(this)"  name="wholeEvalua" value="9" <c:if test="${formDataMap['wholeEvalua']=='9'}">checked</c:if>/>&nbsp;9&nbsp;</label>
							</div>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
									<c:if test="${!empty formDataMap['wholeEvalua'] && (fn:indexOf('123',formDataMap['wholeEvalua'])>-1)}">
										有待加强：${formDataMap['wholeEvalua']}
									</c:if>
									<c:if test="${!empty  formDataMap['wholeEvalua'] && (fn:indexOf('456', formDataMap['wholeEvalua'])>-1)}">
										合格：${ formDataMap['wholeEvalua']}
									</c:if>
									<c:if test="${!empty  formDataMap['wholeEvalua'] && (fn:indexOf('789', formDataMap['wholeEvalua'])>-1)}">
									优良：${ formDataMap['wholeEvalua']}
									</c:if>
									<input type="hidden" name="wholeEvalua" value="${formDataMap['wholeEvalua']}"/>
								</c:if>
						</td>
					</tr>
					<tr>
						<td>
							总分：
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText" id="ZongHe" name="evaluaScore" value="${formDataMap['evaluaScore']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<input type="hidden" class="inputText" id="ZongHe" name="evaluaScore" value="${formDataMap['evaluaScore']}"/>
								<label id="ZongHe">${formDataMap['evaluaScore']}</label>
							</c:if>
						</td>
					</tr>
				</table>
				<table class="basic" width="100%" style="margin-top: 10px;">
					<tr>
						<th style="text-align: center; font-size:15px;" colspan="6">出科成绩表</th>
					</tr>
					<tr>
						<td style="width:17%;">理论考核得分</td>
						<td style="width:20%;">
						<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
							<input id="theoryscore" type="text" class="score inputText" onchange="check();" name="theoryscore" value="${formDataMap['theoryscore']}"/>
						</c:if>
						<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
							${formDataMap['theoryscore']}
							<input type="hidden" class="inputText"  name="theoryscore" value="${formDataMap['theoryscore']}"/>
						</c:if>
						</td>
						<td style="width:10.5%;" >诊疗能力</td>
						<td style="width:20%;" >
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input id="treatAbility" type="text" class="score inputText" onchange="check();" readonly="readonly" name="treatAbility" value="${formDataMap['treatAbility']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<input type="hidden" class="inputText" name="treatAbility" value="${formDataMap['treatAbility']}"/>
								${formDataMap['treatAbility']}
							</c:if>
						</td>
						<td style="width:12.5%;">基本技能</td>
						<td style="width:20%;">
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input id="basicSkills" type="text" class="inputText" onchange="check();" readonly="readonly" name="basicSkills" value="${formDataMap['basicSkills']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<input type="hidden" class="inputText" name="basicSkills" value="${formDataMap['basicSkills']}"/>
								${formDataMap['basicSkills']}
							</c:if>
						</td>
					</tr>
					<tr>
						<td>日常评价</td>
						<td>
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input id="dailyEvalua" type="text" class="inputText" readonly="readonly" onchange="check();" name="dailyEvalua" value="${formDataMap['dailyEvalua']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<input type="hidden" id="dailyEvalua" class="inputText" name="dailyEvalua" value="${formDataMap['dailyEvalua']}"/>
								<label id="dailyEvalua">${formDataMap['dailyEvalua']}</label>
							</c:if>
						</td>
						<td>评价教师(填写姓名)</td>
						<td>
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input id="evaluaTeacher" type="text" class="inputText" name="evaluaTeacher" value="${formDataMap['evaluaTeacher']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<input type="hidden" class="inputText" name="evaluaTeacher" value="${formDataMap['evaluaTeacher']}"/>
								${formDataMap['evaluaTeacher']}
							</c:if>
						</td>
						<td>得分</td>
						<td>
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input id="score" type="text" class="inputText" onchange="check();" name="score" value="${formDataMap['score']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<input type="hidden" class="inputText" name="score" value="${formDataMap['score']}"/>
								${formDataMap['score']}
							</c:if>
						</td>
					</tr>
					<tr>
						<td colspan="4">总分
						（理论考核*10%+ 诊疗能力*35%+ 基本技能*25%+综合评价*30%）
						</td>
						<td colspan="2" id="total">
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input  type="text" readonly="readonly" class="inputText" name="allScore" value="${formDataMap['allScore']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<input type="hidden" class="inputText" name="allScore" value="${formDataMap['allScore']}"/>
								${formDataMap['allScore']}
							</c:if>
						</td>
					</tr>
					<tr>
						<td>考勤得分</td>
						<td>
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)}">
								<input type="text" class="inputText" name="attendScore" value="${formDataMap['attendScore']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD))}">
								<input type="hidden" class="inputText" name="attendScore" value="${formDataMap['attendScore']}"/>
								${formDataMap['attendScore']}
							</c:if>
						</td>
						<td>请假</td>
						<td>
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText" name="leave" value="${formDataMap['leave']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<input type="hidden" class="inputText" name="leave" value="${formDataMap['leave']}"/>
								${formDataMap['leave']}
							</c:if>
						</td>
						<td>脱岗</td>
						<td>
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText" name="offDuty" value="${formDataMap['offDuty']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<input type="hidden" class="inputText" name="offDuty" value="${formDataMap['offDuty']}"/>
								${formDataMap['offDuty']}
							</c:if>	
						</td>
					</tr>
					<c:if test="${(param.roleFlag!=GlobalConstant.RES_ROLE_SCOPE_TEACHER && !empty rec.auditStatusId) || param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER}">
					<tr>
						<td>带教老师鉴定意见</td>
						<td colspan="5">
								<c:if test="${param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId }">
									<textarea placeholder="请填写" style="width: 100%; height:100px; border: none;" name="teacherOpinion">${formDataMap['teacherOpinion']}</textarea>
								</c:if>
								<c:if test="${!(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId)}">
									<textarea  placeholder="请填写" style="display:none; width: 100%; height:100px; border: none;" name="teacherOpinion">${formDataMap['teacherOpinion']}</textarea>
									<div style="height: 100px;">${formDataMap['teacherOpinion']}</div>
								</c:if>
								<div style="margin-bottom: 2px;">
									<div style="width: 200px;float: right;">
										日期：
										<c:if test="${param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId }">
											<input type="text" style="width: 120px;" class="inputText validate[required]" name="teacherDate" value="${empty formDataMap['teacherDate']?pdfn:getCurrDate():formDataMap['teacherDate']}" style="width: 100px;" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
										</c:if>
										<c:if test="${!(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId)}">
											<input type="hidden" class="inputText validate[required]" name="teacherDate" value="${empty formDataMap['teacherDate']?pdfn:getCurrDate():formDataMap['teacherDate']}" style="width: 100px;" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
											<label>${formDataMap['teacherDate']}</label>
										</c:if>
									</div>
									<div style="width: 250px;float: right;">
										带教老师签名：
										<c:if test="${param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId }">
											<input type="text" class="validate[required] inputText" style="width: 120px;" name="teacherSignature" value="${empty formDataMap['teacherSignature']?sessionScope.currUser.userName:formDataMap['teacherSignature']}"/>
										</c:if>
										<c:if test="${!(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId)}">
											<input type="hidden" class="validate[required] inputText" style="width: 120px;" name="teacherSignature" value="${empty formDataMap['teacherSignature']?sessionScope.currUser.userName:formDataMap['teacherSignature']}"/>
											<label>${formDataMap['teacherSignature']}</label>
										</c:if>
									</div>
								</div>
						</td>
					</tr>
					</c:if>
<!-- 					<tr> -->
<!-- 						<td>教学秘书鉴定意见</td> -->
<!-- 						<td colspan="5"> -->
<%-- 							<textarea class="xltxtarea" name="secretary">${formDataMap['secretary']}</textarea>	 --%>
							
<!-- 							<div style="margin-bottom: 2px;"> -->
<!-- 									<div style="width: 200px;float: right;"> -->
<!-- 										日期： -->
<%-- 											<input type="text" class="inputText validate[required]" name="secretaryDate" value="${formDataMap['secretaryDate']}" style="width: 120px;" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/> --%>
<!-- 									</div> -->
<!-- 									<div style="width: 250px;float: right;"> -->
<!-- 										教学秘书签名： -->
<%-- 											<input type="text" style="width: 120px;" class="inputText validate[required]" name="signature" value="${formDataMap['signature']}"/> --%>
<!-- 									</div> -->
<!-- 								</div> -->
							
							
<!-- 						</td> -->
<!-- 					</tr> -->
					<c:if test="${(param.roleFlag!=GlobalConstant.RES_ROLE_SCOPE_HEAD && !empty rec.headAuditStatusId) || param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD}">
					<tr>
						<td>科主任鉴定意见</td>
						<td colspan="5">
							<c:if test="${param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD}">
								<textarea placeholder="请填写" style="width: 100%; height:100px; border: none;" name="sectionDirector">${formDataMap['sectionDirector']}</textarea>
							</c:if>
							<c:if test="${!(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)}">
								<textarea  placeholder="请填写" style="display:none; width: 100%; height:100px; border: none;" name="sectionDirector">${formDataMap['sectionDirector']}</textarea>
								<div style="height: 100px;">${formDataMap['sectionDirector']}</div>
							</c:if>
							<div style="margin-bottom: 2px;">
									<div style="width: 200px;float: right;">
										日期：
										<c:if test="${param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD}">
											<input type="text" class="inputText validate[required]" name="directorDate" value="${empty formDataMap['directorDate']?pdfn:getCurrDate():formDataMap['directorDate']}" style="width: 120px;" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
										</c:if>
										<c:if test="${!(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)}">
											<input type="hidden" class="inputText validate[required]" name="directorDate" value="${empty formDataMap['directorDate']?pdfn:getCurrDate():formDataMap['directorDate']}" style="width: 120px;" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
											<label>${formDataMap['directorDate']}</label>
										</c:if>
									</div>
									<div style="width: 250px;float: right;">
										科主任签名：
										<c:if test="${param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD}">
											<input type="text" style="width: 120px;" class="inputText validate[required]" name="directorSignature" value="${empty formDataMap['directorSignature']?sessionScope.currUser.userName:formDataMap['directorSignature']}"/>
										</c:if>
										<c:if test="${!(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
											<input type="hidden" style="width: 120px;" class="inputText validate[required]" name="directorSignature" value="${empty formDataMap['directorSignature']?sessionScope.currUser.userName:formDataMap['directorSignature']}"/>
											<label>${formDataMap['directorSignature']}</label>
										</c:if>
									</div>
								</div>
						</td>
					</tr>
					</c:if>
				</table>
				<p align="center" style="margin-top: 10px;">
					<c:if test="${param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId}">
						<input class="search" type="button" value="保&#12288;存"  onclick="save();"/>
					</c:if>
					<c:if test="${param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD }">
						<input class="search" type="button" value="保&#12288;存"  onclick="save();"/>
					</c:if>
					<input class="search" type="button" value="关&#12288;闭"  onclick="jboxClose();"/>
				</p>
			</form>
		</div>
	</div>
</body>
</html>