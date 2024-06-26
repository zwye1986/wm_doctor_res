
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
			var theoreResult = $('[name="theoreResult"]').val();
			$('#evaluationForm').append('<input type="hidden" name="totalScore" value="'+theoreResult+'"/>');
			jboxPost("<s:url value='/res/rec/saveRegistryFormNew'/>",$("#evaluationForm").serialize(),function(resp){
				if(resp=="${GlobalConstant.SAVE_SUCCESSED}"){
					top.document.mainIframe.location.reload();
				   jboxClose();
				}
			},null,true);
			});
		}
	}

	<%--<c:if test="${empty rec.headAuditStatusId}">--%>
	function getDoctorDatas(){
		jboxCustomSqlData([
			{
				id:'res_evaluation_1.0_getDoctorDatas',
				values:['${currRegProcess.processFlow}']
			},
			{
				id:'res_evaluation_1.0_getDoctorDatas_Activtiy_cap',
				values:['${registryTypeEnumCampaignNoItemRegistry.id}','${currRegProcess.processFlow}']
			}
		],function(resp){
			if(resp){
				var progressData = resp['res_evaluation_1.0_getDoctorDatas'][0];
				$('[name="sessional"]').val(progressData.sessionNumber);
				$('[name="trainMajor"]').val(progressData.trainingSpeName);
				$('[name="deptName"]').val(progressData.schDeptName);
				$('[name="cycleTimeQ"]').val(progressData.schStartDate);
				$('[name="cycleTimeH"]').val(progressData.schEndDate);
				<c:if test="${testTypeFlag}">
					//$('[name="theoreResult"]').val(progressData.theoryScore);
				</c:if>

				var capData = resp['res_evaluation_1.0_getDoctorDatas_Activtiy_cap'];
				var contents = '';
				for (var index in capData) {
					contents += (capData[index].recContent);
				}
				$("#content").html(contents);
				var trm = $("#content").find('activity_way[id="1"]').length;
				var dg = $("#content").find('activity_way[id="2"]').length+$("#content").find('activity_way[id="3"]').length;
				var al = $("#content").find('activity_way[id="4"]').length;
				var dd = $("#content").find('activity_way[id="5"]').length;

				<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
					$('[name="nwzbltl"]').val(dg);
					$('[name="jxcb"]').val(trm);
					$('[name="xsjz"]').val(al);
					$('[name="swbltl"]').val(dd);
				</c:if>
				<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
					$('[name="nwzbltl"]').html(dg);
					$('[name="jxcb"]').html(trm);
					$('[name="xsjz"]').html(al);
					$('[name="swbltl"]').html(dd);
				</c:if>
			}
		});
	}

	function getProgressData(){
		jboxPostNoLoad('<s:url value="/res/rec/getProgressByProcess"/>',{
			doctorFlow:'${doctor.doctorFlow}',
			processFlow:'${currRegProcess.processFlow}'
		},function(resp){
			if(resp){
				var processFlow = '${currRegProcess.schResultFlow}';
				var ck = '${registryTypeEnumCaseRegistry.id}';
				var dk = '${registryTypeEnumDiseaseRegistry.id}';
				var sk = '${registryTypeEnumSkillRegistry.id}';
				var ok = '${registryTypeEnumOperationRegistry.id}';
				var req = 'req';
				var finish = 'finish';

				<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">

					$('[name="blsywc"]').val(resp[(processFlow+ck+req)]);
					$('[name="blsyjwc"]').val(resp[(processFlow+ck+finish)]);
					$('[name="blswcbl"]').val(resp[(processFlow+ck)]);

					$('[name="bzsywc"]').val(resp[(processFlow+dk+req)]);
					$('[name="bzsyjwc"]').val(resp[(processFlow+dk+finish)]);
					$('[name="bzswcbl"]').val(resp[(processFlow+dk)]);

					$('[name="czsywc"]').val(resp[(processFlow+sk+req)]);
					$('[name="czsyjwc"]').val(resp[(processFlow+sk+finish)]);
					$('[name="czswcbl"]').val(resp[(processFlow+sk)]);

					$('[name="sssywc"]').val(resp[(processFlow+ok+req)]);
					$('[name="sssyjwc"]').val(resp[(processFlow+ok+finish)]);
					$('[name="ssswcbl"]').val(resp[(processFlow+ok)]);
				</c:if>
				<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">

					$('[name="blsywc"]').html(resp[(processFlow+ck+req)]);
					$('[name="blsyjwc"]').html(resp[(processFlow+ck+finish)]);
					$('[name="blswcbl"]').html(resp[(processFlow+ck)]);

					$('[name="bzsywc"]').html(resp[(processFlow+dk+req)]);
					$('[name="bzsyjwc"]').html(resp[(processFlow+dk+finish)]);
					$('[name="bzswcbl"]').html(resp[(processFlow+dk)]);

					$('[name="czsywc"]').html(resp[(processFlow+sk+req)]);
					$('[name="czsyjwc"]').html(resp[(processFlow+sk+finish)]);
					$('[name="czswcbl"]').html(resp[(processFlow+sk)]);

					$('[name="sssywc"]').html(resp[(processFlow+ok+req)]);
					$('[name="sssyjwc"]').html(resp[(processFlow+ok+finish)]);
					$('[name="ssswcbl"]').html(resp[(processFlow+ok)]);
				</c:if>
			}
		},null,false);
	}

	$(function(){
		getDoctorDatas();
		getProgressData();
	});
	<%--</c:if>--%>
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
					<input type="hidden" name="headAuditStatusId" value="${recStatusEnumHeadAuditY.id}"/>
				</c:if>
				<label style="margin-bottom: 10px;">
					姓名：
					<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
						<input type="text" class="inputText validate[required]"  name="name" value="${empty formDataMap['name']?doctor.doctorName:formDataMap['name']}"/>
					</c:if>	
					<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
						${formDataMap['name']}
						<input type="hidden" class="inputText"  name="name" value="${formDataMap['name']}"/>
					</c:if>
				</label>
				<p style="float: right; margin-bottom: 10px;" >届别：
				<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
					<input type="text" class="inputText validate[required]"  name="sessional" value="${formDataMap['sessional']}"/>&#12288;
				</c:if>
				<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
					${formDataMap['sessional']}
				</c:if>					
				&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;
					培训专业：
					<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
						<input type="text" class="inputText validate[required]"  name="trainMajor" value="${formDataMap['trainMajor']}"/>
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
								<input type="text" class="inputText validate[required]"  name="deptName" value="${formDataMap['deptName']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								${formDataMap['deptName']}
								<input type="hidden" class="inputText"  name="deptName" value="${formDataMap['deptName']}"/>
							</c:if>
						</td>
						<td>轮转时间：</td>
						<td>
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
								<input type="hidden" class="inputText"  name="trainMajor" value="${formDataMap['trainMajor']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td>考勤</td>
						<td colspan="3">
						
							全勤：
								<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
									<input type="text" class="inputText validate[costum[number],required]" style="width: 70px;" name="attendance" value="${formDataMap['attendance']}"/>天；
								</c:if>
								<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
									${formDataMap['attendance']}
									<input type="hidden" class="inputText" style="width: 70px;" name="attendance" value="${formDataMap['attendance']}"/>天；
								</c:if>
								&#12288;
							事假：
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText validate[costum[number],required]" style="width: 70px;" name="leave" value="${formDataMap['leave']}"/>天；&#12288;
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								${formDataMap['leave']}
								<input type="hidden" class="inputText" style="width: 70px;" name="leave" value="${formDataMap['leave']}"/>天；&#12288;
							</c:if>
							病假：
								<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
									<input type="text" class="inputText validate[costum[number],required]" style="width: 70px;" name="sickLeave" value="${formDataMap['sickLeave']}"/>天；
								</c:if>
								<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
									${formDataMap['sickLeave']}
									<input type="hidden" class="inputText" style="width: 70px;" name="sickLeave" value="${formDataMap['sickLeave']}"/>天；
								</c:if>
								&#12288;
							产假：
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText validate[costum[number],required]" style="width: 70px;" name="materLeave" value="${formDataMap['materLeave']}"/>天；&#12288;
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								${formDataMap['materLeave']}
								<input type="hidden" class="inputText" style="width: 70px;" name="materLeave" value="${formDataMap['materLeave']}"/>天；&#12288;
							</c:if>
							旷工：
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText validate[costum[number],required]" style="width: 70px;" name="absenteeism" value="${formDataMap['absenteeism']}"/>天；
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
								<label><input type="checkbox"  onchange="single(this)" name="zsgjflfg" value="1" <c:if test="${formDataMap['zsgjflfg_id']=='1'}">checked</c:if> class="autoValue validate[required]"/>&#12288;优&#12288;&#12288;&#12288;</label>
								<label><input type="checkbox"  onchange="single(this)" name="zsgjflfg" value="2" <c:if test="${formDataMap['zsgjflfg_id']=='2'}">checked</c:if> class="autoValue validate[required]"/>&#12288;良&#12288;&#12288;&#12288;&#12288;</label>
								<label><input type="checkbox"  onchange="single(this)" name="zsgjflfg" value="3" <c:if test="${formDataMap['zsgjflfg_id']=='3'}">checked</c:if> class="autoValue validate[required]"/>&#12288;中&#12288;&#12288;&#12288;&#12288;</label>
								<label><input type="checkbox"  onchange="single(this)" name="zsgjflfg" value="4" <c:if test="${formDataMap['zsgjflfg_id']=='4'}">checked</c:if> class="autoValue validate[required]"/>&#12288;差&#12288;&#12288;&#12288;</label>
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
								<label><input type="checkbox" onchange="single(this)" name="lxgwzz" value="1" <c:if test="${formDataMap['lxgwzz_id']=='1'}">checked</c:if> class="autoValue validate[required]"/>&#12288;优&#12288;&#12288;&#12288;</label>
								<label><input type="checkbox" onchange="single(this)" name="lxgwzz" value="2" <c:if test="${formDataMap['lxgwzz_id']=='2'}">checked</c:if> class="autoValue validate[required]"/>&#12288;良&#12288;&#12288;&#12288;&#12288;</label>
								<label><input type="checkbox" onchange="single(this)" name="lxgwzz" value="3" <c:if test="${formDataMap['lxgwzz_id']=='3'}">checked</c:if> class="autoValue validate[required]"/>&#12288;中&#12288;&#12288;&#12288;&#12288;</label>
								<label><input type="checkbox" onchange="single(this)" name="lxgwzz" value="4" <c:if test="${formDataMap['lxgwzz_id']=='4'}">checked</c:if> class="autoValue validate[required]"/>&#12288;差&#12288;&#12288;&#12288;</label>
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
								<label><input type="checkbox" onchange="single(this)" name="ybrwzx" value="1" <c:if test="${formDataMap['ybrwzx_id']=='1'}">checked</c:if> class="autoValue validate[required]"/>&#12288;优&#12288;&#12288;&#12288;</label>
								<label><input type="checkbox" onchange="single(this)" name="ybrwzx" value="2" <c:if test="${formDataMap['ybrwzx_id']=='2'}">checked</c:if> class="autoValue validate[required]"/>&#12288;良&#12288;&#12288;&#12288;&#12288;</label>
								<label><input type="checkbox" onchange="single(this)" name="ybrwzx" value="3" <c:if test="${formDataMap['ybrwzx_id']=='3'}">checked</c:if> class="autoValue validate[required]"/>&#12288;中&#12288;&#12288;&#12288;&#12288;</label>
								<label><input type="checkbox" onchange="single(this)" name="ybrwzx" value="4" <c:if test="${formDataMap['ybrwzx_id']=='4'}">checked</c:if> class="autoValue validate[required]"/>&#12288;差&#12288;&#12288;&#12288;</label>
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
								<label><input  type="checkbox" onchange="single(this)" name="rjgtbdnl" value="1" <c:if test="${formDataMap['rjgtbdnl_id']=='1'}">checked</c:if> class="autoValue validate[required]"/>&#12288;强&#12288;&#12288;&#12288;</label>
								<label><input  type="checkbox" onchange="single(this)" name="rjgtbdnl" value="2" <c:if test="${formDataMap['rjgtbdnl_id']=='2'}">checked</c:if> class="autoValue validate[required]"/>&#12288;较强&#12288;&#12288;&#12288;</label>
								<label><input  type="checkbox" onchange="single(this)" name="rjgtbdnl" value="3" <c:if test="${formDataMap['rjgtbdnl_id']=='3'}">checked</c:if> class="autoValue validate[required]"/>&#12288;一般&#12288;&#12288;&#12288;</label>
								<label><input  type="checkbox" onchange="single(this)" name="rjgtbdnl" value="4" <c:if test="${formDataMap['rjgtbdnl_id']=='4'}">checked</c:if> class="autoValue validate[required]"/>&#12288;差&#12288;&#12288;&#12288;</label>
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
									<label><input  type="checkbox" onchange="single(this)" name="tjxzjsxm" value="1" <c:if test="${formDataMap['tjxzjsxm_id']=='1'}">checked</c:if> class="autoValue validate[required]"/>&#12288;优&#12288;&#12288;&#12288;</label>
									<label><input  type="checkbox" onchange="single(this)" name="tjxzjsxm" value="2" <c:if test="${formDataMap['tjxzjsxm_id']=='2'}">checked</c:if> class="autoValue validate[required]"/>&#12288;良&#12288;&#12288;&#12288;&#12288;</label>
									<label><input  type="checkbox" onchange="single(this)" name="tjxzjsxm" value="3" <c:if test="${formDataMap['tjxzjsxm_id']=='3'}">checked</c:if> class="autoValue validate[required]"/>&#12288;中&#12288;&#12288;&#12288;&#12288;</label>
									<label><input  type="checkbox" onchange="single(this)" name="tjxzjsxm" value="4" <c:if test="${formDataMap['tjxzjsxm_id']=='4'}">checked</c:if> class="autoValue validate[required]"/>&#12288;差&#12288;&#12288;&#12288;</label>
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
						<td rowspan="5">临床综合能力作</td>
						<td>临床基本知识、基本理论掌握程度</td>
						<td colspan="2">
								<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<label><input  type="checkbox" onchange="single(this)" name="jbllzwcd" value="1" <c:if test="${formDataMap['jbllzwcd_id']=='1'}">checked</c:if> class="autoValue validate[required]"/>&#12288;强&#12288;&#12288;&#12288;</label>
								<label><input  type="checkbox" onchange="single(this)" name="jbllzwcd" value="2" <c:if test="${formDataMap['jbllzwcd_id']=='2'}">checked</c:if> class="autoValue validate[required]"/>&#12288;较强&#12288;&#12288;&#12288;</label>
								<label><input  type="checkbox" onchange="single(this)" name="jbllzwcd" value="3" <c:if test="${formDataMap['jbllzwcd_id']=='3'}">checked</c:if> class="autoValue validate[required]"/>&#12288;一般&#12288;&#12288;&#12288;</label>
								<label><input  type="checkbox" onchange="single(this)" name="jbllzwcd" value="4" <c:if test="${formDataMap['jbllzwcd_id']=='4'}">checked</c:if> class="autoValue validate[required]"/>&#12288;差&#12288;&#12288;&#12288;</label>
								</c:if>
								<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
									<c:if test="${!empty formDataMap['jbllzwcd']}">
										${formDataMap['jbllzwcd']}
									</c:if>
								</c:if>
								<input type="hidden" name="jbllzwcd" value="${formDataMap['jbllzwcd']}"/>
						</td>
					</tr>
					<tr>
						<td>临床基本技能掌握程度</td>
						<td colspan="2">
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<label><input  type="checkbox" onchange="single(this)" name="njbjnzwcd" value="1" <c:if test="${formDataMap['njbjnzwcd_id']=='1'}">checked</c:if> class="autoValue validate[required]"/>&#12288;强&#12288;&#12288;&#12288;</label>
								<label><input  type="checkbox" onchange="single(this)" name="njbjnzwcd" value="2" <c:if test="${formDataMap['njbjnzwcd_id']=='2'}">checked</c:if> class="autoValue validate[required]"/>&#12288;较强&#12288;&#12288;&#12288;</label>
								<label><input  type="checkbox" onchange="single(this)" name="njbjnzwcd" value="3" <c:if test="${formDataMap['njbjnzwcd_id']=='3'}">checked</c:if> class="autoValue validate[required]"/>&#12288;一般&#12288;&#12288;&#12288;</label>
								<label><input  type="checkbox" onchange="single(this)" name="njbjnzwcd" value="4" <c:if test="${formDataMap['njbjnzwcd_id']=='4'}">checked</c:if> class="autoValue validate[required]"/>&#12288;差&#12288;&#12288;&#12288;</label>
								</c:if>
								<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<c:if test="${!empty formDataMap['njbjnzwcd']}">
									${formDataMap['njbjnzwcd']}
								</c:if>
								<input type="hidden" name="njbjnzwcd" value="${formDataMap['njbjnzwcd']}"/>
								</c:if>
						</td>
					</tr>
					<tr>
						<td>临床思维能力</td>
						<td colspan="2">
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<label><input  type="checkbox" onchange="single(this)" name="lcswnl" value="1" <c:if test="${formDataMap['lcswnl_id']=='1'}">checked</c:if> class="autoValue validate[required]"/>&#12288;强&#12288;&#12288;&#12288;</label>
								<label><input  type="checkbox" onchange="single(this)" name="lcswnl" value="2" <c:if test="${formDataMap['lcswnl_id']=='2'}">checked</c:if> class="autoValue validate[required]"/>&#12288;较强&#12288;&#12288;&#12288;</label>
								<label><input  type="checkbox" onchange="single(this)" name="lcswnl" value="3" <c:if test="${formDataMap['lcswnl_id']=='3'}">checked</c:if> class="autoValue validate[required]"/>&#12288;一般&#12288;&#12288;&#12288;</label>
								<label><input  type="checkbox" onchange="single(this)" name="lcswnl" value="4" <c:if test="${formDataMap['lcswnl_id']=='4'}">checked</c:if> class="autoValue validate[required]"/>&#12288;差&#12288;&#12288;&#12288;</label>
								</c:if>
								<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
									<c:if test="${!empty formDataMap['lcswnl']}">
										${formDataMap['lcswnl']}
									</c:if>
								</c:if>
								<input type="hidden" name="lcswnl" value="${formDataMap['lcswnl']}"/>
						</td>
					</tr>
					<tr>
						<td>临床诊疗能力</td>
						<td colspan="2">
								<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
									<label><input  type="checkbox" onchange="single(this)" name="lczlnl" value="1" <c:if test="${formDataMap['lczlnl_id']=='1'}">checked</c:if> class="autoValue validate[required]"/>&#12288;强&#12288;&#12288;&#12288;</label>
									<label><input  type="checkbox" onchange="single(this)" name="lczlnl" value="2" <c:if test="${formDataMap['lczlnl_id']=='2'}">checked</c:if> class="autoValue validate[required]"/>&#12288;较强&#12288;&#12288;&#12288;</label>
									<label><input  type="checkbox" onchange="single(this)" name="lczlnl" value="3" <c:if test="${formDataMap['lczlnl_id']=='3'}">checked</c:if> class="autoValue validate[required]"/>&#12288;一般&#12288;&#12288;&#12288;</label>
									<label><input  type="checkbox" onchange="single(this)" name="lczlnl" value="4" <c:if test="${formDataMap['lczlnl_id']=='4'}">checked</c:if> class="autoValue validate[required]"/>&#12288;差&#12288;&#12288;&#12288;</label>
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
						<td colspan="2">
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
							<label><input  type="checkbox" onchange="single(this)" name="jjclnl" value="1" <c:if test="${formDataMap['jjclnl_id']=='1'}">checked</c:if> class="autoValue validate[required]"/>&#12288;优&#12288;&#12288;&#12288;</label>
							<label><input  type="checkbox" onchange="single(this)" name="jjclnl" value="2" <c:if test="${formDataMap['jjclnl_id']=='2'}">checked</c:if> class="autoValue validate[required]"/>&#12288;良&#12288;&#12288;&#12288;&#12288;</label>
							<label><input  type="checkbox" onchange="single(this)" name="jjclnl" value="3" <c:if test="${formDataMap['jjclnl_id']=='3'}">checked</c:if> class="autoValue validate[required]"/>&#12288;中&#12288;&#12288;&#12288;&#12288;</label>
							<label><input  type="checkbox" onchange="single(this)" name="jjclnl" value="4" <c:if test="${formDataMap['jjclnl_id']=='4'}">checked</c:if> class="autoValue validate[required]"/>&#12288;差&#12288;&#12288;&#12288;</label>
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
						<td colspan="3">
							<div style="text-align: center;">
							完成病历数：应完成
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText validate[costum[number],required]" style="width: 70px;" readonly name="blsywc" value="${formDataMap['blsywc']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<label name="blsywc"></label>
							</c:if>
							例，
							已完成
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText validate[costum[number],required]" style="width: 70px;" readonly name="blsyjwc" value="${formDataMap['blsyjwc']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<label name="blsyjwc"></label>
							</c:if>
							例，
							完成比例
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText validate[costum[number],required]" style="width: 70px;" readonly name="blswcbl" value="${formDataMap['blswcbl']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<label name="blswcbl"></label>
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
								<input type="text" class="inputText validate[costum[number],required]" style="width: 70px;" readonly name="bzsywc" value="${formDataMap['bzsywc']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<label name="bzsywc"></label>
							</c:if>
							例，
							已完成
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText validate[costum[number],required]" style="width: 70px;" readonly name="bzsyjwc" value="${formDataMap['bzsyjwc']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<label name="bzsyjwc"></label>
							</c:if>
							例，
							完成比例
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText validate[costum[number],required]" style="width: 70px;" readonly name="bzswcbl" value="${formDataMap['bzswcbl']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<label name="bzswcbl"></label>
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
								<input type="text" class="inputText validate[costum[number],required]" style="width: 70px;" readonly name="czsywc" value="${formDataMap['czsywc']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<label name="czsywc"></label>
							</c:if>
							例，
							已完成
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText validate[costum[number],required]" style="width: 70px;" readonly name="czsyjwc" value="${formDataMap['czsyjwc']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<label name="czsyjwc"></label>
							</c:if>
							例，
							完成比例
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText validate[costum[number],required]" style="width: 70px;" readonly name="czswcbl" value="${formDataMap['czswcbl']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<label name="czswcbl"></label>
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
								<input type="text" class="inputText validate[costum[number],required]" style="width: 70px;" readonly name="sssywc" value="${formDataMap['sssywc']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<label name="sssywc"></label>
							</c:if>
							例，
							已完成
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText validate[costum[number],required]" style="width: 70px;" readonly name="sssyjwc" value="${formDataMap['sssyjwc']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<label name="sssyjwc"></label>
							</c:if>
							例，
							完成比例
								<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
									<input type="text" class="inputText validate[costum[number],required]" style="width: 70px;" readonly name="ssswcbl" value="${formDataMap['ssswcbl']}"/>
								</c:if>
								<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
									<label name="ssswcbl"></label>
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
								<input type="text" class="inputText validate[costum[number],required]" style="width: 70px;" readonly name="jxcb" value="${formDataMap['jxcb']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<label name="jxcb"></label>
							</c:if>
							次                 
							&#12288;
							<label style="float: right; margin-right: 20px;">
							 疑难、危重病例讨论：
							 <c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								 <input type="text" class="inputText validate[costum[number],required]" style="width: 70px;" readonly name="nwzbltl" value="${formDataMap['nwzbltl']}"/>
							 </c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<label name="nwzbltl"></label>
							 </c:if>
							 次 
							 </label>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							学术讲座：
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText validate[costum[number],required]" style="width: 70px;" readonly name="xsjz" value="${formDataMap['xsjz']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<label name="xsjz"></label>
							</c:if>
							次                  
							&#12288;
								<label style="float: right; margin-right: 20px;">
							  死亡病例讨论：
							  <c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
							  	<input type="text" class="inputText validate[costum[number],required]" style="width: 70px;" readonly name="swbltl" value="${formDataMap['swbltl']}"/>
							  </c:if>
							  <c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								  <label name="swbltl"></label>
							  </c:if>
							 次 
							 </label>
						</td>
					</tr>
					<%--if 未配置 原来的--%>
					<c:if test="${cfg13 ne 'Y' and sysCfgMap['res_isGlobalSch_flag'] ne 'Y' or sysCfgMap['res_isGlobalSch_flag'] eq 'Y'}">
					<tr>
						<td rowspan="2">出科考核</td>
						<td colspan="3">
							理论成绩：
							<c:set var="testSwitch" value="${sysCfgMap['res_after_test_switch'] eq GlobalConstant.FLAG_Y}"/>
							<c:set var="urlEmpt" value="${sysCfgMap['res_after_url_cfg'] != null and sysCfgMap['res_after_url_cfg'] != '' }"/>
							<%--<c:set var="orgTestSwitch" value="jswjw_${currRegProcess.orgFlow}_P004"/>
							<c:set var="orgTestSwitch" value="${sysCfgMap[orgTestSwitch] eq GlobalConstant.FLAG_Y}"/>
							<c:set var="docTestSwitch" value="doc_test_switch_${currRegProcess.orgFlow}_${currRegProcess.userFlow}"/>
							<c:set var="docTestSwitch" value="${sysCfgMap[docTestSwitch] eq GlobalConstant.FLAG_Y}"/>
							<c:set var="testTypeFlag" value="${testSwitch && orgTestSwitch && docTestSwitch&&urlEmpt}"/>--%>
							<c:set var="ckks" value="${open eq GlobalConstant.FLAG_Y}"/>
							<c:set var="testTypeFlag" value="${testSwitch and urlEmpt and ckks}"/>
							<c:choose>
								<c:when test="${!testTypeFlag}">
									<input class="inputText validate[required]" style="width: 70px;" name="theoreResult" type="text" value="${formDataMap['theoreResult']}" />
								</c:when>
								<c:when test="${testTypeFlag}">
									<input class="inputText validate[required]" style="width: 70px;" name="theoreResult" type="text" value="${empty formDataMap['theoreResult'] ? (empty outScore.theoryScore ? '':outScore.theoryScore):formDataMap['theoreResult']}" readonly="readonly"/>
								</c:when>
							</c:choose>
							<%--<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId) }">--%>
								<%--<input type="text" class="inputText validate[required]" style="width: 70px;" name="theoreResult" value="${formDataMap['theoreResult']}" <c:if test="${GlobalConstant.FLAG_Y eq testSwith}">readonly="readonly"</c:if>/>--%>
							<%--</c:if>--%>
							<%--<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId)) }">--%>
								<%--${formDataMap['theoreResult']}--%>
								<%--<input type="hidden" class="inputText validate[required]" style="width: 70px;" name="theoreResult" value="${formDataMap['theoreResult']}"/>--%>
							<%--</c:if>--%>
							分
							<c:if test="${testTypeFlag}">
								<c:if test="${(empty outScore || empty outScore.theoryScore)&&(empty formDataMap['theoreResult'])}"><font color="red">该学员暂未参加出科考核</font></c:if>
							</c:if>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							技能考核名称：
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText validate[required]"  name="skillName" value="${formDataMap['skillName']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								${formDataMap['skillName']}
								<input type="hidden" class="inputText" name="skillName" value="${formDataMap['skillName']}"/>
							</c:if>
							&#12288;
							得分：
								<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
									<input type="text" class="inputText validate[costum[number],min[1],max[100],required]" style="width: 70px;" name="score" value="${formDataMap['score']}"/>
								</c:if>
								<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
									${formDataMap['score']}
									<input type="hidden" class="inputText" style="width: 70px;" name="score" value="${formDataMap['score']}"/>
								</c:if>
							 分； 
							 &#12288;
							 考官1：
							 <c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
							 	<input type="text" class="inputText validate[required]" style="width: 70px;" name="examiner1" value="${formDataMap['examiner1']}"/>
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
					</c:if>
					<%--已配置 如果已经保存就取保存的值，没保存时，如果已经导入就取导入值，--%>
					<c:if test="${cfg13 eq 'Y' and sysCfgMap['res_isGlobalSch_flag'] ne 'Y'}">
						<tr>
							<td rowspan="2">出科考核</td>
							<td colspan="3">
								理论成绩：
								<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
									<input type="text" class="inputText validate[required]"  name="theoreResult" value="${empty formDataMap['theoreResult']?score.theoryScore:formDataMap['theoreResult']}"/>
								</c:if>
								<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
									${empty formDataMap['theoreResult']?score.theoryScore:formDataMap['theoreResult']}
									<input type="hidden" class="inputText" name="theoreResult" value="${empty formDataMap['theoreResult']?score.theoryScore:formDataMap['theoreResult']}"/>
								</c:if>
								分
							</td>
						</tr>
						<tr>
							<td colspan="3">
								技能考核名称：
								<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
									<input type="text" class="inputText validate[required]"  name="skillName" value="${empty formDataMap['skillName']?score.scoreResultName:formDataMap['skillName']}"/>
								</c:if>
								<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
									${empty formDataMap['skillName']?score.scoreResultName:formDataMap['skillName']}
									<input type="hidden" class="inputText" name="skillName" value="${empty formDataMap['skillName']?score.scoreResultName:formDataMap['skillName']}"/>
								</c:if>
								&#12288;
								得分：
								<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
									<input type="text" class="inputText validate[costum[number],min[1],max[100],required]" style="width: 70px;" name="score" value="${empty formDataMap['score']?score.skillScore:formDataMap['score']}"/>
								</c:if>
								<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
									${empty formDataMap['score']?score.skillScore:formDataMap['score']}
									<input type="hidden" class="inputText" style="width: 70px;" name="score" value="${empty formDataMap['score']?score.skillScore:formDataMap['score']}"/>
								</c:if>
								分；
								&#12288;
								考官1：
								<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
									<input type="text" class="inputText validate[required]" style="width: 70px;" name="examiner1" value="${empty formDataMap['examiner1']?score.auditStatusId:formDataMap['examiner1']}"/>
								</c:if>
								<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
									${empty formDataMap['examiner1']?score.auditStatusId:formDataMap['examiner1']}
									<input type="hidden" class="inputText" style="width: 70px;" name="examiner1" value="${empty formDataMap['examiner1']?score.auditStatusId:formDataMap['examiner1']}"/>
								</c:if>
								&#12288;
								考官2：
								<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
									<input type="text" class="inputText" style="width: 70px;" name="examiner2" value="${empty formDataMap['examiner2']?score.auditStatusName:formDataMap['examiner2']}"/>
								</c:if>
								<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
									${empty formDataMap['examiner2']?score.auditStatusName:formDataMap['examiner2']}
									<input type="hidden" class="inputText" style="width: 70px;" name="examiner2" value="${empty formDataMap['examiner2']?score.auditStatusName:formDataMap['examiner2']}"/>
								</c:if>
								&#12288;
								考官3：
								<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
									<input type="text" class="inputText" style="width: 70px;" name="examiner3" value="${empty formDataMap['examiner3']?score.scoreResultId:formDataMap['examiner3']}"/>
								</c:if>
								<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
									${empty formDataMap['examiner3']?score.scoreResultId:formDataMap['examiner3']}
									<input type="hidden" class="inputText" style="width: 70px;" name="examiner3" value="${empty formDataMap['examiner3']?score.scoreResultId:formDataMap['examiner3']}"/>
								</c:if>
							</td>
						</tr>
					</c:if>

					<tr>
						<td>所在科室考核小组总体评价</td>
						<td colspan="3">
						<c:if test="${param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId }">
							<label><input  type="checkbox"  name="szkskhxzztpj" onchange="single(this)" value="1" <c:if test="${formDataMap['szkskhxzztpj_id']=='1'}">checked</c:if> class="autoValue validate[required]"/>&#12288;通过 &#12288;&#12288;&#12288;</label>
							 &#12288;
							<label><input  type="checkbox"  name="szkskhxzztpj" onchange="single(this)" value="0" <c:if test="${formDataMap['szkskhxzztpj_id']=='0'}">checked</c:if> class="autoValue validate[required]"/>&#12288;不通过 &#12288;&#12288;&#12288;</label>
						</c:if>
						<c:if test="${!(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) }">
							<c:if test="${!empty formDataMap['szkskhxzztpj']}">
									${formDataMap['szkskhxzztpj']}
							</c:if>
							<input type="hidden" name="szkskhxzztpj" value="${formDataMap['szkskhxzztpj']}"/>
						</c:if>
						</td>
					</tr>
					<tr>
						<td>带教老师签名

						</td>
						<td colspan="3">
							<c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER}">
								<label  style="float: left;">${empty formDataMap['teacherName']?sessionScope.currUser.userName:formDataMap['teacherName']}</label>
							</c:if>
							<c:if test="${!(param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER)}">
								<label  style="float: left;">${formDataMap['teacherName']}</label>
							</c:if>
							<input type="hidden" name="teacherName" value="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER?sessionScope.currUser.userName:formDataMap['teacherName']}">
							<label style="float: right; margin-right: 20px;">
							日期：
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
									<input type="text" class="inputText validate[required]" name="teacherDate" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${empty formDataMap['teacherDate']?pdfn:getCurrDate():formDataMap['teacherDate']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								${formDataMap['teacherDate']}
								<input type="hidden" class="inputText" name="teacherDate" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${empty formDataMap['teacherDate']?pdfn:getCurrDate():formDataMap['teacherDate']}"/>
							</c:if>
								</label>
						</td>
					</tr>
				</table>
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

					<c:if test="${teaSub}">
						<input class="search" type="button" value="保&#12288;存"  onclick="save();"/>
					</c:if>
					<c:if test="${(GlobalConstant.RES_ROLE_SCOPE_DOCTOR eq param.roleFlag or 'manage'eq param.roleFlag) && not empty rec.auditStatusId}">
						<input class="search" type="button" value="打&#12288;印"  onclick="jboxPrint('printDiv');"/>
					</c:if>
					<input class="search" type="button" value="关&#12288;闭"  onclick="jboxClose();"/>
				</p>
			</form>
		</div>
	</div>
</body>
</html>