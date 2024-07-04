
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
	<c:set var="testSwitch" value="${sysCfgMap['res_after_test_switch'] eq GlobalConstant.FLAG_Y}"/>
	<c:set var="urlEmpt" value="${sysCfgMap['res_after_url_cfg'] != null and sysCfgMap['res_after_url_cfg'] != '' }"/>
	<%--<c:set var="orgTestSwitch" value="jswjw_${currRegProcess.orgFlow}_P004"/>--%>
	<%--<c:set var="orgTestSwitch" value="${sysCfgMap[orgTestSwitch] eq GlobalConstant.FLAG_Y}"/>--%>
	<%--<c:set var="docTestSwitch" value="doc_test_switch_${currRegProcess.orgFlow}_${currRegProcess.userFlow}"/>--%>
	<%--<c:set var="docTestSwitch" value="${sysCfgMap[docTestSwitch] eq GlobalConstant.FLAG_Y}"/>--%>
	<%--<c:set var="testTypeFlag" value="${testSwitch && orgTestSwitch && docTestSwitch&&urlEmpt}"/>--%>
	<c:set var="ckks" value="${open eq GlobalConstant.FLAG_Y}"/>
	<c:set var="testTypeFlag" value="${testSwitch and urlEmpt and ckks}"/>
	<script src="<s:url value='/js/jquery.jqprint-0.3.js'/>" type="text/javascript"></script>
<script type="text/javascript">
	function single(box){
		var curr=box.checked;
		if(curr){
			var name=box.name;
			$(":checkbox[name='"+name+"']").attr("checked",false);
		}
		  box.checked = curr;
	}
	function jboxPrint(id) {
		jboxTip("正在准备打印…")
		setTimeout(function(){
			$("#title").show();
			var newstr = $("#"+id).html();
			var oldstr = document.body.innerHTML;
			document.body.innerHTML = newstr;
			window.print();
			document.body.innerHTML = oldstr;
			$("#title").hide();
			jboxEndLoading();
			return false;
		},2000);
	}
	function save(){
		if($("#evaluationForm").validationEngine("validate")){
			var info = "确认保存？<c:if test="${!(param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD)}">保存之后不可修改！</c:if>";
			jboxConfirm(info,function(){
			autoValue($("#evaluationForm"),"autoValue");
			jboxPost("<s:url value='/res/rec/saveRegistryFormNew'/>",$("#evaluationForm").serialize(),function(resp){
				if(resp=="${GlobalConstant.SAVE_SUCCESSED}"){
					top.document.mainIframe.location.reload();
				   jboxClose();
				}
			},null,true);
			});
		}
	}

	<c:if test="${empty rec.headAuditStatusId}">
	function getDoctorDatas(){
		jboxCustomSqlData([
			{
				id:'res_evaluation_1.0_getDoctorDatas',
				values:['${currRegProcess.processFlow}']
			}
		],function(resp){
			if(resp){
				var progressData = resp[0];
				$('[name="sessional"]').val(progressData.sessionNumber);
				$('[name="trainMajor"]').val(progressData.trainingSpeName);
				$('[name="deptName"]').val(progressData.schDeptName);
				$('[name="cycleTimeQ"]').val(progressData.schStartDate);
				$('[name="cycleTimeH"]').val(progressData.schEndDate);

			}
		});
	}


	$(function(){
		getDoctorDatas();
	});
	</c:if>
</script>
</head>
<body>
<div id="content" style="display: none;"></div>
   <div class="mainright">
      <div class="content">
			<form id="evaluationForm">
				<div id="printDiv">
				<div id="title" style="width:100%;text-align: center;font-size: 12px;display: none;">
					出科考核表
				</div>
				<input type="hidden" name="formFileName" value="${formFileName}"/>
				<input type="hidden" name="schDeptFlow" value="${param.schDeptFlow}"/>
				<input type="hidden" name="operUserFlow" value="${param.operUserFlow}"/>
				<input type="hidden" name="roleFlag" value="${roleFlag}"/>
				<input type="hidden" name="recFlow" value="${rec.recFlow}"/>
				<input type="hidden" name="processFlow" value="${currRegProcess.processFlow}"/>
				<c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER}">
					<input type="hidden" name="auditStatusId" value="${recStatusEnumTeacherAuditY.id}"/>
				</c:if>
				<c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD}">
					<input type="hidden" name="headAuditStatusId" value="${recStatusEnumHeadAuditY.id}"/>
				</c:if>
				<label style="margin-bottom: 10px;">


				</label>
				<table class="basic" width="100%" style="margin-top: 10px;">
					<tr>
						<td>姓&#12288;&#12288;名：</td>
						<td style="text-align: left;">
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText validate[required]"  name="name" value="${empty formDataMap['name']?doctor.doctorName:formDataMap['name']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								${formDataMap['name']}
								<input type="hidden" class="inputText"  name="name" value="${formDataMap['name']}"/>
							</c:if>
						</td>
						<td>培训专业：</td>
						<td style="text-align: left;">
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText validate[required]"  name="trainMajor" value="${formDataMap['trainMajor']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								${formDataMap['trainMajor']}
								<input type="hidden" class="inputText"  name="trainMajor" value="${formDataMap['trainMajor']}"/>
							</c:if>
						</td>
						<td>轮转科室名称：</td>
						<td style="text-align: left;">
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText validate[required]"  name="deptName" value="${formDataMap['deptName']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								${formDataMap['deptName']}
								<input type="hidden" class="inputText"  name="deptName" value="${formDataMap['deptName']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td>轮转时间：</td>
						<td colspan="5" style="text-align: left;">
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText validate[required]" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" name="cycleTimeQ" value="${formDataMap['cycleTimeQ']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								${formDataMap['cycleTimeQ']}
								<input type="hidden" class="inputText" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" name="cycleTimeQ" value="${formDataMap['cycleTimeQ']}"/>
							</c:if>
							至
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText validate[required]" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" name="cycleTimeH" value="${formDataMap['cycleTimeH']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								${formDataMap['cycleTimeH']}
								<input type="hidden" class="inputText"  name="cycleTimeH" value="${formDataMap['cycleTimeH']}"/>
							</c:if>，共
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText validate[required]" readonly="readonly" name="schMonth" value="${empty formDataMap['schMonth'] ? result.schMonth: formDataMap['schMonth'] }"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								${formDataMap['schMonth']}
								<input type="hidden" class="inputText"  name="schMonth" value="${formDataMap['schMonth']}"/>
							</c:if>月
						</td>
					</tr>
					<tr>
						<td>考核时间</td>
						<td style="text-align: left;">
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText validate[costum[number],required]" style="width: 120px;" name="auditTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${empty formDataMap['auditTime']?pdfn:getCurrDate():formDataMap['auditTime']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								${formDataMap['auditTime']}
								<input type="hidden" class="inputText" style="width: 120px;" name="auditTime" value="${formDataMap['auditTime']}"/>
							</c:if>
						</td>
						<td>带教老师</td>
						<td style="text-align: left;" colspan="3">
							${currRegProcess.teacherUserName}
							<input type="hidden" class="inputText" style="width: 120px;" name="teacherName" value="${currRegProcess.teacherUserName}"/>
						</td>
					</tr>
				</table>
				<table class="basic" width="100%" style="margin-top: 10px;">
					<tr>
						<td colspan="2">考核内容</td>
						<td>评分等级</td>
						<td>成绩</td>
					</tr>
					<tr>
						<td>考勤</td>
						<td>组织纪律、有无旷工、迟到、早退、脱岗位</td>
						<td>
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<label><input type="checkbox"  onchange="single(this)" name="kqLevel" value="1" <c:if test="${formDataMap['kqLevel_id']=='1'}">checked</c:if> class="autoValue validate[required]"/>优&#12288;&#12288;</label>
								<label><input type="checkbox"  onchange="single(this)" name="kqLevel" value="2" <c:if test="${formDataMap['kqLevel_id']=='2'}">checked</c:if> class="autoValue validate[required]"/>良&#12288;&#12288;</label>
								<label><input type="checkbox"  onchange="single(this)" name="kqLevel" value="3" <c:if test="${formDataMap['kqLevel_id']=='3'}">checked</c:if> class="autoValue validate[required]"/>差*&#12288;&#12288;</label>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<c:if test="${!empty formDataMap['kqLevel']}">
									${formDataMap['kqLevel']}
								</c:if>
								<input type="hidden" name="kqLevel" value="${formDataMap['kqLevel']}"/>
							</c:if>
						</td>
						<td>
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<label><input type="checkbox"  onchange="single(this)" name="kqCj" value="1" <c:if test="${formDataMap['kqCj_id']=='1'}">checked</c:if> class="autoValue validate[required]"/>合格&#12288;&#12288;</label>
								<label><input type="checkbox"  onchange="single(this)" name="kqCj" value="2" <c:if test="${formDataMap['kqCj_id']=='2'}">checked</c:if> class="autoValue validate[required]"/>不合格&#12288;&#12288;</label>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<c:if test="${!empty formDataMap['kqCj']}">
									${formDataMap['kqCj']}
								</c:if>
								<input type="hidden" name="kqCj" value="${formDataMap['kqCj']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td rowspan="4">医德医风</td>
						<td>服务态度、爱护伤病员观念</td>
						<td>
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<label><input type="checkbox"  onchange="single(this)" name="fwtdLevel" value="1" <c:if test="${formDataMap['fwtdLevel_id']=='1'}">checked</c:if> class="autoValue validate[required]"/>优&#12288;&#12288;</label>
								<label><input type="checkbox"  onchange="single(this)" name="fwtdLevel" value="2" <c:if test="${formDataMap['fwtdLevel_id']=='2'}">checked</c:if> class="autoValue validate[required]"/>良&#12288;&#12288;</label>
								<label><input type="checkbox"  onchange="single(this)" name="fwtdLevel" value="3" <c:if test="${formDataMap['fwtdLevel_id']=='3'}">checked</c:if> class="autoValue validate[required]"/>差*&#12288;&#12288;</label>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<c:if test="${!empty formDataMap['fwtdLevel']}">
									${formDataMap['fwtdLevel']}
								</c:if>
								<input type="hidden" name="fwtdLevel" value="${formDataMap['fwtdLevel']}"/>
							</c:if>
						</td>
						<td rowspan="4">
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<label><input type="checkbox"  onchange="single(this)" name="fwtdCj" value="1" <c:if test="${formDataMap['fwtdCj_id']=='1'}">checked</c:if> class="autoValue validate[required]"/>合格&#12288;&#12288;</label>
								<label><input type="checkbox"  onchange="single(this)" name="fwtdCj" value="2" <c:if test="${formDataMap['fwtdCj_id']=='2'}">checked</c:if> class="autoValue validate[required]"/>不合格&#12288;&#12288;</label>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<c:if test="${!empty formDataMap['fwtdCj']}">
									${formDataMap['fwtdCj']}
								</c:if>
								<input type="hidden" name="fwtdCj" value="${formDataMap['fwtdCj']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td>工作责任心、无差错</td>
						<td>
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<label><input type="checkbox"  onchange="single(this)" name="gzzrxLevel" value="1" <c:if test="${formDataMap['gzzrxLevel_id']=='1'}">checked</c:if> class="autoValue validate[required]"/>优&#12288;&#12288;</label>
								<label><input type="checkbox"  onchange="single(this)" name="gzzrxLevel" value="2" <c:if test="${formDataMap['gzzrxLevel_id']=='2'}">checked</c:if> class="autoValue validate[required]"/>良&#12288;&#12288;</label>
								<label><input type="checkbox"  onchange="single(this)" name="gzzrxLevel" value="3" <c:if test="${formDataMap['gzzrxLevel_id']=='3'}">checked</c:if> class="autoValue validate[required]"/>差*&#12288;&#12288;</label>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<c:if test="${!empty formDataMap['gzzrxLevel']}">
									${formDataMap['gzzrxLevel']}
								</c:if>
								<input type="hidden" name="gzzrxLevel" value="${formDataMap['gzzrxLevel']}"/>
							</c:if>
						</td>
					</tr>
					<tr><td>医疗作风、廉洁行医</td>
						<td>
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<label><input type="checkbox"  onchange="single(this)" name="ylzfLevel" value="1" <c:if test="${formDataMap['ylzfLevel_id']=='1'}">checked</c:if> class="autoValue validate[required]"/>优&#12288;&#12288;</label>
								<label><input type="checkbox"  onchange="single(this)" name="ylzfLevel" value="2" <c:if test="${formDataMap['ylzfLevel_id']=='2'}">checked</c:if> class="autoValue validate[required]"/>良&#12288;&#12288;</label>
								<label><input type="checkbox"  onchange="single(this)" name="ylzfLevel" value="3" <c:if test="${formDataMap['ylzfLevel_id']=='3'}">checked</c:if> class="autoValue validate[required]"/>差*&#12288;&#12288;</label>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<c:if test="${!empty formDataMap['ylzfLevel']}">
									${formDataMap['ylzfLevel']}
								</c:if>
								<input type="hidden" name="ylzfLevel" value="${formDataMap['ylzfLevel']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td>团结协作、遵守制度</td>
						<td>
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<label><input type="checkbox"  onchange="single(this)" name="tjxzLevel" value="1" <c:if test="${formDataMap['tjxzLevel_id']=='1'}">checked</c:if> class="autoValue validate[required]"/>优&#12288;&#12288;</label>
								<label><input type="checkbox"  onchange="single(this)" name="tjxzLevel" value="2" <c:if test="${formDataMap['tjxzLevel_id']=='2'}">checked</c:if> class="autoValue validate[required]"/>良&#12288;&#12288;</label>
								<label><input type="checkbox"  onchange="single(this)" name="tjxzLevel" value="3" <c:if test="${formDataMap['tjxzLevel_id']=='3'}">checked</c:if> class="autoValue validate[required]"/>差*&#12288;&#12288;</label>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<c:if test="${!empty formDataMap['tjxzLevel']}">
									${formDataMap['tjxzLevel']}
								</c:if>
								<input type="hidden" name="tjxzLevel" value="${formDataMap['tjxzLevel']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td rowspan="2">指标完成情况</td>
						<td>病种、例数、手术治疗数量（门诊）</td>
						<td>
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<label><input type="checkbox"  onchange="single(this)" name="bzblslLevel" value="1" <c:if test="${formDataMap['bzblslLevel_id']=='1'}">checked</c:if> class="autoValue validate[required]"/>优&#12288;&#12288;</label>
								<label><input type="checkbox"  onchange="single(this)" name="bzblslLevel" value="2" <c:if test="${formDataMap['bzblslLevel_id']=='2'}">checked</c:if> class="autoValue validate[required]"/>良&#12288;&#12288;</label>
								<label><input type="checkbox"  onchange="single(this)" name="bzblslLevel" value="3" <c:if test="${formDataMap['bzblslLevel_id']=='3'}">checked</c:if> class="autoValue validate[required]"/>差*&#12288;&#12288;</label>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<c:if test="${!empty formDataMap['bzblslLevel']}">
									${formDataMap['bzblslLevel']}
								</c:if>
								<input type="hidden" name="bzblslLevel" value="${formDataMap['bzblslLevel']}"/>
							</c:if>
						</td>
						<td rowspan="2">
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<label><input type="checkbox"  onchange="single(this)" name="bzblslCj" value="1" <c:if test="${formDataMap['bzblslCj_id']=='1'}">checked</c:if> class="autoValue validate[required]"/>合格&#12288;&#12288;</label>
								<label><input type="checkbox"  onchange="single(this)" name="bzblslCj" value="2" <c:if test="${formDataMap['bzblslCj_id']=='2'}">checked</c:if> class="autoValue validate[required]"/>不合格&#12288;&#12288;</label>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<c:if test="${!empty formDataMap['bzblslCj']}">
									${formDataMap['bzblslCj']}
								</c:if>
								<input type="hidden" name="bzblslCj" value="${formDataMap['bzblslCj']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td>管理病人数（病房）</td>
						<td>
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<label><input type="checkbox"  onchange="single(this)" name="glbrsLevel" value="1" <c:if test="${formDataMap['glbrsLevel_id']=='1'}">checked</c:if> class="autoValue validate[required]"/>优&#12288;&#12288;</label>
								<label><input type="checkbox"  onchange="single(this)" name="glbrsLevel" value="2" <c:if test="${formDataMap['glbrsLevel_id']=='2'}">checked</c:if> class="autoValue validate[required]"/>良&#12288;&#12288;</label>
								<label><input type="checkbox"  onchange="single(this)" name="glbrsLevel" value="3" <c:if test="${formDataMap['glbrsLevel_id']=='3'}">checked</c:if> class="autoValue validate[required]"/>差*&#12288;&#12288;</label>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<c:if test="${!empty formDataMap['glbrsLevel']}">
									${formDataMap['glbrsLevel']}
								</c:if>
								<input type="hidden" name="glbrsLevel" value="${formDataMap['glbrsLevel']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td rowspan="3">基本技能</td>
						<td>医疗文件书写质量（门诊处方、各类检查申请单、病历书写）</td>
						<td>
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<label><input type="checkbox"  onchange="single(this)" name="ylwjsxLevel" value="1" <c:if test="${formDataMap['ylwjsxLevel_id']=='1'}">checked</c:if> class="autoValue validate[required]"/>优&#12288;&#12288;</label>
								<label><input type="checkbox"  onchange="single(this)" name="ylwjsxLevel" value="2" <c:if test="${formDataMap['ylwjsxLevel_id']=='2'}">checked</c:if> class="autoValue validate[required]"/>良&#12288;&#12288;</label>
								<label><input type="checkbox"  onchange="single(this)" name="ylwjsxLevel" value="3" <c:if test="${formDataMap['ylwjsxLevel_id']=='3'}">checked</c:if> class="autoValue validate[required]"/>差*&#12288;&#12288;</label>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<c:if test="${!empty formDataMap['ylwjsxLevel']}">
									${formDataMap['ylwjsxLevel']}
								</c:if>
								<input type="hidden" name="ylwjsxLevel" value="${formDataMap['ylwjsxLevel']}"/>
							</c:if>
						</td>
						<td rowspan="3">
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<label><input type="checkbox"  onchange="single(this)" name="ylwjsxCj" value="1" <c:if test="${formDataMap['ylwjsxCj_id']=='1'}">checked</c:if> class="autoValue validate[required]"/>合格&#12288;&#12288;</label>
								<label><input type="checkbox"  onchange="single(this)" name="ylwjsxCj" value="2" <c:if test="${formDataMap['ylwjsxCj_id']=='2'}">checked</c:if> class="autoValue validate[required]"/>不合格&#12288;&#12288;</label>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<c:if test="${!empty formDataMap['ylwjsxCj']}">
									${formDataMap['ylwjsxCj']}
								</c:if>
								<input type="hidden" name="ylwjsxCj" value="${formDataMap['ylwjsxCj']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td>体格检查</td>
						<td>
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<label><input type="checkbox"  onchange="single(this)" name="tgjcLevel" value="1" <c:if test="${formDataMap['tgjcLevel_id']=='1'}">checked</c:if> class="autoValue validate[required]"/>优&#12288;&#12288;</label>
								<label><input type="checkbox"  onchange="single(this)" name="tgjcLevel" value="2" <c:if test="${formDataMap['tgjcLevel_id']=='2'}">checked</c:if> class="autoValue validate[required]"/>良&#12288;&#12288;</label>
								<label><input type="checkbox"  onchange="single(this)" name="tgjcLevel" value="3" <c:if test="${formDataMap['tgjcLevel_id']=='3'}">checked</c:if> class="autoValue validate[required]"/>差*&#12288;&#12288;</label>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<c:if test="${!empty formDataMap['tgjcLevel']}">
									${formDataMap['tgjcLevel']}
								</c:if>
								<input type="hidden" name="tgjcLevel" value="${formDataMap['tgjcLevel']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td>手术或技能操作</td>
						<td>
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<label><input type="checkbox"  onchange="single(this)" name="sshjnczLevel" value="1" <c:if test="${formDataMap['sshjnczLevel_id']=='1'}">checked</c:if> class="autoValue validate[required]"/>优&#12288;&#12288;</label>
								<label><input type="checkbox"  onchange="single(this)" name="sshjnczLevel" value="2" <c:if test="${formDataMap['sshjnczLevel_id']=='2'}">checked</c:if> class="autoValue validate[required]"/>良&#12288;&#12288;</label>
								<label><input type="checkbox"  onchange="single(this)" name="sshjnczLevel" value="3" <c:if test="${formDataMap['sshjnczLevel_id']=='3'}">checked</c:if> class="autoValue validate[required]"/>差*&#12288;&#12288;</label>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<c:if test="${!empty formDataMap['sshjnczLevel']}">
									${formDataMap['sshjnczLevel']}
								</c:if>
								<input type="hidden" name="sshjnczLevel" value="${formDataMap['sshjnczLevel']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td rowspan="4">诊治能力</td>
						<td>常见病诊断和鉴别</td>
						<td>
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<label><input type="checkbox"  onchange="single(this)" name="cjbzdLevel" value="1" <c:if test="${formDataMap['cjbzdLevel_id']=='1'}">checked</c:if> class="autoValue validate[required]"/>优&#12288;&#12288;</label>
								<label><input type="checkbox"  onchange="single(this)" name="cjbzdLevel" value="2" <c:if test="${formDataMap['cjbzdLevel_id']=='2'}">checked</c:if> class="autoValue validate[required]"/>良&#12288;&#12288;</label>
								<label><input type="checkbox"  onchange="single(this)" name="cjbzdLevel" value="3" <c:if test="${formDataMap['cjbzdLevel_id']=='3'}">checked</c:if> class="autoValue validate[required]"/>差*&#12288;&#12288;</label>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<c:if test="${!empty formDataMap['cjbzdLevel']}">
									${formDataMap['cjbzdLevel']}
								</c:if>
								<input type="hidden" name="cjbzdLevel" value="${formDataMap['cjbzdLevel']}"/>
							</c:if>
						</td>
						<td rowspan="4">
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<label><input type="checkbox"  onchange="single(this)" name="cjbzdCj" value="1" <c:if test="${formDataMap['cjbzdCj_id']=='1'}">checked</c:if> class="autoValue validate[required]"/>合格&#12288;&#12288;</label>
								<label><input type="checkbox"  onchange="single(this)" name="cjbzdCj" value="2" <c:if test="${formDataMap['cjbzdCj_id']=='2'}">checked</c:if> class="autoValue validate[required]"/>不合格&#12288;&#12288;</label>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<c:if test="${!empty formDataMap['cjbzdCj']}">
									${formDataMap['cjbzdCj']}
								</c:if>
								<input type="hidden" name="cjbzdCj" value="${formDataMap['cjbzdCj']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td>急、危重病人的处置或抢救</td>
						<td>
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<label><input type="checkbox"  onchange="single(this)" name="jwzbrczLevel" value="1" <c:if test="${formDataMap['jwzbrczLevel_id']=='1'}">checked</c:if> class="autoValue validate[required]"/>优&#12288;&#12288;</label>
								<label><input type="checkbox"  onchange="single(this)" name="jwzbrczLevel" value="2" <c:if test="${formDataMap['jwzbrczLevel_id']=='2'}">checked</c:if> class="autoValue validate[required]"/>良&#12288;&#12288;</label>
								<label><input type="checkbox"  onchange="single(this)" name="jwzbrczLevel" value="3" <c:if test="${formDataMap['jwzbrczLevel_id']=='3'}">checked</c:if> class="autoValue validate[required]"/>差*&#12288;&#12288;</label>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<c:if test="${!empty formDataMap['jwzbrczLevel']}">
									${formDataMap['jwzbrczLevel']}
								</c:if>
								<input type="hidden" name="jwzbrczLevel" value="${formDataMap['jwzbrczLevel']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td>结合病情分析、检查、报告</td>
						<td>
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<label><input type="checkbox"  onchange="single(this)" name="jhbqfxjcbgLevel" value="1" <c:if test="${formDataMap['jhbqfxjcbgLevel_id']=='1'}">checked</c:if> class="autoValue validate[required]"/>优&#12288;&#12288;</label>
								<label><input type="checkbox"  onchange="single(this)" name="jhbqfxjcbgLevel" value="2" <c:if test="${formDataMap['jhbqfxjcbgLevel_id']=='2'}">checked</c:if> class="autoValue validate[required]"/>良&#12288;&#12288;</label>
								<label><input type="checkbox"  onchange="single(this)" name="jhbqfxjcbgLevel" value="3" <c:if test="${formDataMap['jhbqfxjcbgLevel_id']=='3'}">checked</c:if> class="autoValue validate[required]"/>差*&#12288;&#12288;</label>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<c:if test="${!empty formDataMap['jhbqfxjcbgLevel']}">
									${formDataMap['jhbqfxjcbgLevel']}
								</c:if>
								<input type="hidden" name="jhbqfxjcbgLevel" value="${formDataMap['jhbqfxjcbgLevel']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td>综合处置</td>
						<td>
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<label><input type="checkbox"  onchange="single(this)" name="zhczLevel" value="1" <c:if test="${formDataMap['zhczLevel_id']=='1'}">checked</c:if> class="autoValue validate[required]"/>优&#12288;&#12288;</label>
								<label><input type="checkbox"  onchange="single(this)" name="zhczLevel" value="2" <c:if test="${formDataMap['zhczLevel_id']=='2'}">checked</c:if> class="autoValue validate[required]"/>良&#12288;&#12288;</label>
								<label><input type="checkbox"  onchange="single(this)" name="zhczLevel" value="3" <c:if test="${formDataMap['zhczLevel_id']=='3'}">checked</c:if> class="autoValue validate[required]"/>差*&#12288;&#12288;</label>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<c:if test="${!empty formDataMap['zhczLevel']}">
									${formDataMap['zhczLevel']}
								</c:if>
								<input type="hidden" name="zhczLevel" value="${formDataMap['zhczLevel']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td rowspan="2">临床思维能力</td>
						<td>归纳能力(掌握病例特点、分析深入、语言表达精练、推理有逻辑性、思维正确)</td>
						<td>
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<label><input type="checkbox"  onchange="single(this)" name="gnnlLevel" value="1" <c:if test="${formDataMap['gnnlLevel_id']=='1'}">checked</c:if> class="autoValue validate[required]"/>优&#12288;&#12288;</label>
								<label><input type="checkbox"  onchange="single(this)" name="gnnlLevel" value="2" <c:if test="${formDataMap['gnnlLevel_id']=='2'}">checked</c:if> class="autoValue validate[required]"/>良&#12288;&#12288;</label>
								<label><input type="checkbox"  onchange="single(this)" name="gnnlLevel" value="3" <c:if test="${formDataMap['gnnlLevel_id']=='3'}">checked</c:if> class="autoValue validate[required]"/>差*&#12288;&#12288;</label>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<c:if test="${!empty formDataMap['gnnlLevel']}">
									${formDataMap['gnnlLevel']}
								</c:if>
								<input type="hidden" name="gnnlLevel" value="${formDataMap['gnnlLevel']}"/>
							</c:if>
						</td>
						<td rowspan="2">
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<label><input type="checkbox"  onchange="single(this)" name="gnnlCj" value="1" <c:if test="${formDataMap['gnnlCj_id']=='1'}">checked</c:if> class="autoValue validate[required]"/>合格&#12288;&#12288;</label>
								<label><input type="checkbox"  onchange="single(this)" name="gnnlCj" value="2" <c:if test="${formDataMap['gnnlCj_id']=='2'}">checked</c:if> class="autoValue validate[required]"/>不合格&#12288;&#12288;</label>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<c:if test="${!empty formDataMap['gnnlCj']}">
									${formDataMap['gnnlCj']}
								</c:if>
								<input type="hidden" name="gnnlCj" value="${formDataMap['gnnlCj']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td>分析能力(理论和实践的结合)</td>
						<td>
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<label><input type="checkbox"  onchange="single(this)" name="fxnlLevel" value="1" <c:if test="${formDataMap['fxnlLevel_id']=='1'}">checked</c:if> class="autoValue validate[required]"/>优&#12288;&#12288;</label>
								<label><input type="checkbox"  onchange="single(this)" name="fxnlLevel" value="2" <c:if test="${formDataMap['fxnlLevel_id']=='2'}">checked</c:if> class="autoValue validate[required]"/>良&#12288;&#12288;</label>
								<label><input type="checkbox"  onchange="single(this)" name="fxnlLevel" value="3" <c:if test="${formDataMap['fxnlLevel_id']=='3'}">checked</c:if> class="autoValue validate[required]"/>差*&#12288;&#12288;</label>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<c:if test="${!empty formDataMap['fxnlLevel']}">
									${formDataMap['fxnlLevel']}
								</c:if>
								<input type="hidden" name="fxnlLevel" value="${formDataMap['fxnlLevel']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td>教学</td>
						<td>带教</td>
						<td>
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<label><input type="checkbox"  onchange="single(this)" name="djLevel" value="1" <c:if test="${formDataMap['djLevel_id']=='1'}">checked</c:if> class="autoValue validate[required]"/>优&#12288;&#12288;</label>
								<label><input type="checkbox"  onchange="single(this)" name="djLevel" value="2" <c:if test="${formDataMap['djLevel_id']=='2'}">checked</c:if> class="autoValue validate[required]"/>良&#12288;&#12288;</label>
								<label><input type="checkbox"  onchange="single(this)" name="djLevel" value="3" <c:if test="${formDataMap['djLevel_id']=='3'}">checked</c:if> class="autoValue validate[required]"/>差*&#12288;&#12288;</label>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<c:if test="${!empty formDataMap['djLevel']}">
									${formDataMap['djLevel']}
								</c:if>
								<input type="hidden" name="djLevel" value="${formDataMap['djLevel']}"/>
							</c:if>
						</td>
						<td>
						</td>
					</tr>
					<tr>
						<td colspan="2">医疗差错事故</td>
						<td colspan="2">
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<label><input type="checkbox"  onchange="single(this)" name="ylccsgLevel" value="1" <c:if test="${formDataMap['ylccsgLevel_id']=='1'}">checked</c:if> class="autoValue validate[required]"/>有&#12288;&#12288;</label>
								<label><input type="checkbox"  onchange="single(this)" name="ylccsgLevel" value="2" <c:if test="${formDataMap['ylccsgLevel_id']=='2'}">checked</c:if> class="autoValue validate[required]"/>无&#12288;&#12288;&#12288;</label>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<c:if test="${!empty formDataMap['ylccsgLevel']}">
									${formDataMap['ylccsgLevel']}
								</c:if>
								<input type="hidden" name="ylccsgLevel" value="${formDataMap['ylccsgLevel']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td colspan="2">指定阅读的书籍</td>
						<td colspan="2">
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<label><input type="checkbox"  onchange="single(this)" name="zdydsjLevel" value="1" <c:if test="${formDataMap['zdydsjLevel_id']=='1'}">checked</c:if> class="autoValue validate[required]"/>完成&#12288;</label>
								<label><input type="checkbox"  onchange="single(this)" name="zdydsjLevel" value="2" <c:if test="${formDataMap['zdydsjLevel_id']=='2'}">checked</c:if> class="autoValue validate[required]"/>未完成&#12288;&#12288;&#12288;&#12288;</label>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<c:if test="${!empty formDataMap['zdydsjLevel']}">
									${formDataMap['zdydsjLevel']}
								</c:if>
								<input type="hidden" name="zdydsjLevel" value="${formDataMap['ylccsgLevel']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td>理论考试成绩</td>
						<td>

							<c:set var="testSwitch" value="${sysCfgMap['res_after_test_switch'] eq GlobalConstant.FLAG_Y}"/>
							<c:set var="urlEmpt" value="${sysCfgMap['res_after_url_cfg'] != null and sysCfgMap['res_after_url_cfg'] != '' }"/>
							<c:set var="testTypeFlag" value="${testSwitch and urlEmpt}"/>
							<c:choose>
								<c:when test="${!testTypeFlag}">
									<input class="inputText validate[required]"  name="totalScore" type="text" value="${formDataMap['totalScore']}" />
								</c:when>
								<c:when test="${testTypeFlag}">
									<input class="inputText validate[required]" name="totalScore" type="text" value="${empty formDataMap['totalScore'] ? (empty outScore.theoryScore ? '':outScore.theoryScore):formDataMap['totalScore']}" readonly="readonly"/>
								</c:when>
							</c:choose>
							分
							<c:if test="${testTypeFlag}">
								<c:if test="${empty outScore || empty outScore.theoryScore}"><font color="red">该学员暂未参加出科考核</font></c:if>
							</c:if>
						</td>
						<td>专业英语成绩</td>
						<td>
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText"  name="zyyycj" value="${formDataMap['zyyycj']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								${formDataMap['zyyycj']}
								<input type="hidden" class="inputText"  name="zyyycj" value="${formDataMap['zyyycj']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td>临床技能考试成绩</td>
						<td>
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText validate[required,custom[integer,min[0]]]"  name="lcjncj" value="${formDataMap['lcjncj']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								${formDataMap['lcjncj']}
								<input type="hidden" class="inputText"  name="lcjncj" value="${formDataMap['lcjncj']}"/>
							</c:if>
						</td>
						<td>病历书写成绩</td>
						<td>
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText validate[required,custom[integer,min[0]]]"  name="blsxcj" value="${formDataMap['blsxcj']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								${formDataMap['blsxcj']}
								<input type="hidden" class="inputText"  name="blsxcj" value="${formDataMap['blsxcj']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td colspan="2">考核总成绩（以上考核项目中有一项不合格视为本次轮科不合格）
						</td>
						<td colspan="2">
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<label><input type="checkbox"  onchange="single(this)" name="zCj" value="1" <c:if test="${formDataMap['zCj_id']=='1'}">checked</c:if> class="autoValue validate[required]"/>合格&#12288;</label>
								<label><input type="checkbox"  onchange="single(this)" name="zCj" value="2" <c:if test="${formDataMap['zCj_id']=='2'}">checked</c:if> class="autoValue validate[required]"/>不合格&#12288;&#12288;</label>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<c:if test="${!empty formDataMap['zCj']}">
									${formDataMap['zCj']}
								</c:if>
								<input type="hidden" name="zCj" value="${formDataMap['zCj']}"/>
							</c:if>
						</td>
					</tr>
				</table>
					<c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD || !empty rec.headAuditStatusId}">
						<table class="basic"  width="100%" cellspacing="0" cellpadding="0" style="margin-left: auto;margin-right: auto;">
							<tr class="orgTr">
								<td class="orgTd" colspan="4" style="padding-top:10px;padding-left:0;">
									所轮科室（年度）考核评语
								</td>
							</tr>
							<tr class="orgTr">
								<td class="orgTd" colspan="4" style="padding-top:10px;padding-left:0;">
									<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
										<textarea name="deptHeadAutograth" style="width: 100%;height: 200px;" class="validate[required]">${formDataMap['deptHeadAutograth']}</textarea>
									</c:if>
									<c:if test="${!(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
										&#12288;${formDataMap['deptHeadAutograth']}
										<input type="hidden" name="deptHeadAutograth" value="${formDataMap['deptHeadAutograth']}"/>
									</c:if>
								</td>
							</tr>
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<tr>
									<td class="orgTd" colspan="2" style="height:30px;text-align: right;width: 80%">
										考核组长签名：
									</td>
									<td class="orgTd" colspan="2" style="height:30px;text-align: left">
										<input type="text" class="inputText" name="headExpertName" value="${formDataMap['headExpertName']}"/>
									</td>
								</tr>
								<tr>
									<td class="orgTd" colspan="2" style="height:30px;text-align: right;width: 80%">
										考核成员签名：
									</td>
									<td class="orgTd" colspan="2" style="height:30px;text-align: left">
										<input type="text" class="inputText" name="headMemberName" value="${formDataMap['headMemberName']}"/>
									</td>
								</tr>
							</c:if>
							<c:if test="${!(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<tr>
									<td class="orgTd" colspan="2" style="height:30px;text-align: right;width: 80%">
										考核组长签名：
									</td>
									<td class="orgTd" colspan="2" style="height:30px;text-align: left">
											${formDataMap['headExpertName']}
										<input type="hidden" name="headExpertName" value="${formDataMap['headExpertName']}"/>
									</td>
								</tr>
								<tr>
									<td class="orgTd" colspan="2" style="height:30px;text-align: right;width: 80%">
										考核成员签名：
									</td>
									<td class="orgTd" colspan="2" style="height:30px;text-align: left">
											${formDataMap['headMemberName']}
										<input type="hidden" name="headMemberName" value="${formDataMap['headMemberName']}"/>
									</td>
								</tr>
							</c:if>
						</table>
					</c:if>
				<table class="basic" width="100%" style="margin-top: 10px;">
				</table>
				</div>

				<%--<c:set var="docSub" value="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR && empty rec.auditStatusId && empty rec.headAuditStatusId}"/>--%>
				<c:set var="teaSub" value="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId}"/>
				<c:set var="headSub" value="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD && (not empty rec.auditStatusId)}"/>
				<c:set var="showMsg" value="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId &&  empty rec.auditStatusId}"/>
				<p align="center" style="margin-top: 10px;">
					<%--<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId)}">--%>
						<%--<input class="search" type="button" value="保&#12288;存"  onclick="save();"/>--%>
					<%--</c:if>--%>
					<c:if test="${showMsg}">
							[<font color="red">带教老师还未审核，请等待！</font>]
					</c:if>

					<c:if test="${teaSub or headSub}">
						<input class="search" type="button" value="保&#12288;存"  onclick="save();"/>
					</c:if>
					<c:if test="${GlobalConstant.RES_ROLE_SCOPE_DOCTOR eq param.roleFlag && not empty rec.auditStatusId}">
						<%--<input class="search" type="button" value="打&#12288;印"  onclick="jboxPrint('printDiv');"/>--%>
					</c:if>
					<input class="search" type="button" value="关&#12288;闭"  onclick="jboxClose();"/>
				</p>
			</form>
		</div>
	</div>
</body>
</html>