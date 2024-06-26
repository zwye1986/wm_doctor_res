
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
	<script src="<s:url value='/js/jquery.jqprint-0.3.js'/>" type="text/javascript"></script>
	<style type="text/css">
		.inputText{
			width: 100px;
			margin-right:10px ;
		}
	</style>
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
	$(function(){
		$(".score").keyup(function(){
			var sum = 0;
			$(".score").each(function(){
				var val = this.value;
				if(val && !isNaN(val)){
					sum+=parseFloat(val);
				}
				$("#xj").val(sum);
				$("input[name='xj']").val(sum);
			});
		});
	});
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
						<td rowspan="2" colspan="2">考核内容</td>
						<td colspan="2">考核成绩</td>
						<td rowspan="2">评分说明</td>
					</tr>
					<tr>
						<td>分值</td>
						<td>评分</td>
					</tr>
					<tr>
						<td>考勤</td>
						<td>组织纪律、有无旷工、迟到、早退、脱岗位</td>
						<td>5</td>
						<td>
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText validate[required,custom[integer,max[5],min[0]]] score"  name="kq" value="${formDataMap['kq']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<c:if test="${!empty formDataMap['kq']}">
									${formDataMap['kq']}
								</c:if>
								<input type="hidden" name="kq" value="${formDataMap['kq']}"/>
							</c:if>
						</td>
						<td>
							1.旷工，记0分；<br/>
							2.满勤，记5分；<br/>
							3.迟到或早退或脱岗一次，扣1分，直至扣完
						</td>
					</tr>
					<tr>
						<td rowspan="4">医德医风</td>
						<td>服务态度、爱护伤病员观念</td>
						<td rowspan="4">5</td>
						<td rowspan="4">
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText validate[required,custom[integer,max[5],min[0]]] score"  name="ydyf" value="${formDataMap['ydyf']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<c:if test="${!empty formDataMap['ydyf']}">
									${formDataMap['ydyf']}
								</c:if>
								<input type="hidden" name="ydyf" value="${formDataMap['ydyf']}"/>
							</c:if>
						</td>
						<td rowspan="4">
							带教老师根据住院医师平时的工作表现进行综合评价
						</td>
					</tr>
					<tr>
						<td>工作责任心、无差错</td>
					</tr>
					<tr>
						<td>医疗作风、廉洁行医</td>
					</tr>
					<tr>
						<td>团结协作、遵守制度</td>
					</tr>
					<tr>
						<td rowspan="6">指标完成情况</td>
						<td>病种、例数、手术治疗数量（门诊）</td>
						<td rowspan="6">20</td>
						<td rowspan="6">
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText validate[required,custom[integer,max[20],min[0]]] score"  name="zbwcqk" value="${formDataMap['zbwcqk']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<c:if test="${!empty formDataMap['zbwcqk']}">
									${formDataMap['zbwcqk']}
								</c:if>
								<input type="hidden" name="zbwcqk" value="${formDataMap['zbwcqk']}"/>
							</c:if>
						</td>
						<td rowspan="6">
							带教老师根据住院医师轮转时实际完成情况进行综合评价
						</td>
					</tr>
					<tr>
						<td>管理病人数（病房）</td>
					</tr>
					<tr>
						<td>参加轮科科室组织的业务学习、病例讨论及各项教学活动</td>
					</tr>
					<tr>
						<td>必轮科室完成手写2份大病历</td>
					</tr>
					<tr>
						<td>阅读指定的书籍</td>
					</tr>
					<tr>
						<td>值班</td>
					</tr>
					<tr>
						<td rowspan="3">基本技能</td>
						<td>医疗文件书写质量（门诊处方、各类检查申请单、病历书写）</td>
						<td rowspan="3">10</td>
						<td rowspan="3">
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText validate[required,custom[integer,max[10],min[0]]] score"  name="jbjn" value="${formDataMap['jbjn']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<c:if test="${!empty formDataMap['jbjn']}">
									${formDataMap['jbjn']}
								</c:if>
								<input type="hidden" name="jbjn" value="${formDataMap['jbjn']}"/>
							</c:if>
						</td>
						<td rowspan="9">
							带教老师根据住院医师轮转时实际完成情况进行综合评价
						</td>
					</tr>
					<tr>
						<td>体格检查</td>
					</tr>
					<tr>
						<td>手术或技能操作</td>
					</tr>
					<tr>
						<td rowspan="4">诊治能力</td>
						<td>常见病诊断和鉴别</td>
						<td rowspan="4">10</td>
						<td rowspan="4">
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText validate[required,custom[integer,max[10],min[0]]] score"  name="zznl" value="${formDataMap['zznl']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<c:if test="${!empty formDataMap['zznl']}">
									${formDataMap['zznl']}
								</c:if>
								<input type="hidden" name="zznl" value="${formDataMap['zznl']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td>急、危重病人的处置或抢救</td>
					</tr>
					<tr>
						<td>结合病情分析、检查、报告</td>
					</tr>
					<tr>
						<td>综合处置</td>
					</tr>
					<tr>
						<td rowspan="2">临床思维能力</td>
						<td>归纳能力(掌握病例特点、分析深入、语言表达精练、推理有逻辑性、思维正确)</td>
						<td rowspan="2">10</td>
						<td rowspan="2">
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText validate[required,custom[integer,max[10],min[0]]] score"  name="lcswnl" value="${formDataMap['lcswnl']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								<c:if test="${!empty formDataMap['lcswnl']}">
									${formDataMap['lcswnl']}
								</c:if>
								<input type="hidden" name="lcswnl" value="${formDataMap['lcswnl']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td>分析能力(理论和实践的结合)</td>
					</tr>
					<tr>
						<td colspan="2">医疗差错事故</td>
						<td colspan="3">
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
						<td>小计</td>
						<td>（日常考核合格线：42分）</td>
						<td colspan="3">
							<input type="text" class="inputText" readonly id="xj" value="${formDataMap['xj']}"/>
							<input type="hidden" name="xj" value="${formDataMap['xj']}"/>
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
						<td colspan="2">
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
						<td colspan="2">
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
						<td colspan="3">
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
								<td class="orgTd" colspan="5" style="padding-top:10px;padding-left:0;">
									所轮科室（年度）考核评语
								</td>
							</tr>
							<tr class="orgTr">
								<td class="orgTd" colspan="5" style="padding-top:10px;padding-left:0;">
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
									<td class="orgTd" colspan="3" style="height:30px;text-align: right;width: 80%">
										考核组长签名：
									</td>
									<td class="orgTd" colspan="2" style="height:30px;text-align: left">
										<input type="text" class="inputText" name="headExpertName" value="${formDataMap['headExpertName']}"/>
									</td>
								</tr>
								<tr>
									<td class="orgTd" colspan="3" style="height:30px;text-align: right;width: 80%">
										考核成员签名：
									</td>
									<td class="orgTd" colspan="2" style="height:30px;text-align: left">
										<input type="text" class="inputText" name="headMemberName" value="${formDataMap['headMemberName']}"/>
									</td>
								</tr>
							</c:if>
							<c:if test="${!(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<tr>
									<td class="orgTd" colspan="3" style="height:30px;text-align: right;width: 80%">
										考核组长签名：
									</td>
									<td class="orgTd" colspan="2" style="height:30px;text-align: left">
											${formDataMap['headExpertName']}
										<input type="hidden" name="headExpertName" value="${formDataMap['headExpertName']}"/>
									</td>
								</tr>
								<tr>
									<td class="orgTd" colspan="3" style="height:30px;text-align: right;width: 80%">
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