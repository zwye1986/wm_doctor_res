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
		<c:if  test="${testTypeFlag}">
			var theoryAssessment=$('[name="llkh"]').val();
			if(!theoryAssessment){
				jboxTip("该学员还未参加出科考核！不可审核出科考核表！");
				return false;
			}
		</c:if>
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
	function census(){
		var count = 0;
		$(".score").each(function(){
			var s = $(this).val();
			if(s != '' && !isNaN(s)){
				count=accAdd(count,parseFloat(s));
			}
		});
		$("input[name='gradeScore']").val(count);
	}
	function accAdd(arg1, arg2) {
		var r1, r2, m;
		try {
			r1 = arg1.toString().split(".")[1].length;
		}
		catch (e) {
			r1 = 0;
		}
		try {
			r2 = arg2.toString().split(".")[1].length;
		}
		catch (e) {
			r2 = 0;
		}
		m = Math.pow(10, Math.max(r1, r2));
		return (arg1 * m + arg2 * m) / m;
	}
	$(function(){
		census();
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
					<input type="hidden" name="headAuditStatusId" value="${recStatusEnumHeadAuditY.id}"/>
				</c:if>
				<table class="basic" width="100%" style="margin-top: 10px;">
					<tr>
						<td>姓名：</td>
						<td>
							${doctor.doctorName}
							<input type="hidden" class="inputText"  name="name" value="${doctor.doctorName}"/>
						</td>
						<td>培训阶段：</td>
						<td>
							${doctor.trainingTypeName}
							<input type="hidden" name="trainStage" value="${doctor.trainingTypeName}"/>
						</td>
						<td>入培年份：</td>
						<td>
							${doctor.sessionNumber}
							<input type="hidden" class="inputText"  name="trainYear" value="${doctor.sessionNumber}"/>
						</td>
					</tr>
					<tr>
						<td>培训专业：</td>
						<td>
							${doctor.trainingSpeName}
							<input type="hidden" name="trainSpe" value="${doctor.trainingSpeName}"/>
						</td>
						<td>轮转科室名称：</td>
						<td>
							${result.schDeptName}
							<input type="hidden" name="head" value="${result.schDeptName}"/>
						</td>
						<td>带教老师：</td>
						<td>
							${currRegProcess.teacherUserName}
							<input type="hidden" name="teacher" value="${currRegProcess.teacherUserName}"/>
						</td>
					</tr>
					<tr>
						<td>轮转时间：</td>
						<td colspan="5">
							${currRegProcess.schStartDate}至${currRegProcess.schEndDate}，共${result.schMonth}个月
								<input type="hidden" class="inputText" name="beginTime" value="${currRegProcess.schStartDate}"/>
								<input type="hidden" class="inputText" name="endTime" value="${currRegProcess.schEndDate}"/>
								<input type="hidden" class="inputText" name="totalTime" value="${result.schMonth}"/>
						</td>
					</tr>
					<tr>
						<td>考核时间：</td>
						<td colspan="5">
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText validate[required]" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" name="testDate" value="${formDataMap['testDate']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								${formDataMap['testDate']}
								<input type="hidden" class="inputText" name="testDate" value="${formDataMap['testDate']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td rowspan="2" colspan="4" style="text-align:center;">考核内容</td>
						<td colspan="2" style="text-align:center;">考核成绩</td>
					</tr>
					<tr>
						<td style="text-align:center;">分值</td>
						<td style="text-align:center;">实得分或折算分</td>
					</tr>
					<tr>
						<td>组织纪律</td>
						<td colspan="3">有无旷工、迟到、早退、脱岗</td>
						<td style="text-align:center;">5</td>
						<td style="text-align:center;">
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText validate[required,custom[number,min[0],max[5]]] score" onchange="census()" name="zzjl" value="${formDataMap['zzjl']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								${formDataMap['zzjl']}
								<input type="hidden" class="inputText" name="zzjl" value="${formDataMap['zzjl']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td>医德医风</td>
						<td colspan="3">医疗作风、遵守制度、服务态度、工作责任</td>
						<td style="text-align:center;">5</td>
						<td style="text-align:center;">
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText validate[required,custom[number,min[0],max[5]]] score" onchange="census()" name="ydyf" value="${formDataMap['ydyf']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								${formDataMap['ydyf']}
								<input type="hidden" class="inputText" name="ydyf" value="${formDataMap['ydyf']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td>学习态度</td>
						<td colspan="3">参加科室学术活动、住院医师培训课程情况</td>
						<td style="text-align:center;">5</td>
						<td style="text-align:center;">
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText validate[required,custom[number,min[0],max[5]]] score" onchange="census()" name="xxtd" value="${formDataMap['xxtd']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								${formDataMap['xxtd']}
								<input type="hidden" class="inputText" name="xxtd" value="${formDataMap['xxtd']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td rowspan="2">病种与操作</td>
						<td colspan="3">完成管理全程病人数和规定的病种数量情况</td>
						<td style="text-align:center;">5</td>
						<td style="text-align:center;">
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText validate[required,custom[number,min[0],max[5]]] score" onchange="census()" name="bzqk" value="${formDataMap['bzqk']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								${formDataMap['bzqk']}
								<input type="hidden" class="inputText" name="bzqk" value="${formDataMap['bzqk']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td colspan="3">完成技能操作种类和数量情况</td>
						<td style="text-align:center;">5</td>
						<td style="text-align:center;">
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText validate[required,custom[number,min[0],max[5]]] score" onchange="census()" name="czqk" value="${formDataMap['czqk']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								${formDataMap['czqk']}
								<input type="hidden" class="inputText" name="czqk" value="${formDataMap['czqk']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td rowspan="6">临床诊疗能力</td>
						<td colspan="3">病史采集与体格检查</td>
						<td rowspan="6" style="text-align:center;">20</td>
						<td rowspan="6" style="text-align:center;">
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText validate[required,custom[number,min[0],max[20]]] score" onchange="census()" name="lczlnl" value="${formDataMap['lczlnl']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								${formDataMap['lczlnl']}
								<input type="hidden" class="inputText" name="lczlnl" value="${formDataMap['lczlnl']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td colspan="3">实验室结果判读与分析</td>
					</tr>
					<tr>
						<td colspan="3">诊断与鉴别诊断</td>
					</tr>
					<tr>
						<td colspan="3">对疾病的综合处理能力</td>
					</tr>
					<tr>
						<td colspan="3">归纳、分析、总结以及表达能力</td>
					</tr>
					<tr>
						<td colspan="3">人文关怀与人际沟通能力</td>
					</tr>
					<tr>
						<td rowspan="2">动手能力</td>
						<td colspan="3">临床技能操作能力</td>
						<td rowspan="2" style="text-align:center;">10</td>
						<td rowspan="2" style="text-align:center;">
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText validate[required,custom[number,min[0],max[10]]] score" onchange="census()" name="dsnl" value="${formDataMap['dsnl']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								${formDataMap['dsnl']}
								<input type="hidden" class="inputText" name="dsnl" value="${formDataMap['dsnl']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td colspan="3">人文关怀与人际沟通能力</td>
					</tr>
					<tr>
						<td>病历书写</td>
						<td colspan="3">病历书写考核（原始分,附修改过的手写大病历和病历书写评分表）</td>
						<td style="text-align:center;">10</td>
						<td style="text-align:center;">
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText validate[required,custom[number,min[0],max[10]]] score" onchange="census()" name="blsx" value="${formDataMap['blsx']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								${formDataMap['blsx']}
								<input type="hidden" class="inputText" name="blsx" value="${formDataMap['blsx']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td>英语考核</td>
						<td colspan="3">专业英语考核（原始分,附原始试卷和有修改评分的答案）</td>
						<td style="text-align:center;">10</td>
						<td style="text-align:center;">
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText validate[required,custom[number,min[0],max[10]]] score" onchange="census()" name="yykh" value="${formDataMap['yykh']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								${formDataMap['yykh']}
								<input type="hidden" class="inputText" name="yykh" value="${formDataMap['yykh']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td>理论考核</td>
						<td colspan="3">专业理论考核（原始分，考核时间）</td>
						<td style="text-align:center;">25</td>
						<td style="text-align:center;">
							<c:set var="testSwitch" value="${sysCfgMap['res_after_test_switch'] eq GlobalConstant.FLAG_Y}"/>
							<c:set var="urlEmpt" value="${sysCfgMap['res_after_url_cfg'] != null and sysCfgMap['res_after_url_cfg'] != '' }"/>
							<c:set var="ckks" value="${open eq GlobalConstant.FLAG_Y}"/>
							<c:set var="testTypeFlag" value="${testSwitch and urlEmpt and ckks}"/>
							<%--<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">--%>
							<c:choose>
								<c:when test="${!testTypeFlag}">
									<input type="text" class="inputText validate[required,custom[number],max[25],min[0]] score" onchange="census()" name="llkh" value="${formDataMap['llkh']}" style="width: 150px;"/><br>
								</c:when>
								<c:when test="${testTypeFlag}">
									<input type="text" class="inputText validate[required,custom[number],max[25],min[0]] score" name="llkh" type="text" value="${empty formDataMap['llkh'] ? (empty outScore.theoryScore ? '':(outScore.theoryScore.toString()+0)*0.25*10/10):formDataMap['llkh']}" readonly="readonly"/><br>
								</c:when>
							</c:choose>
							<c:if test="${testTypeFlag}">
								<c:if test="${(empty outScore || empty outScore.theoryScore)&&(empty formDataMap['llkh'])}"><font color="red">该学员暂未参加出科考核</font></c:if>
							</c:if>
							<%--</c:if>--%>
							<%--<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">--%>
								<%--${formDataMap['llkh']}--%>
								<%--<input type="hidden" class="inputText" name="llkh" value="${formDataMap['llkh']}"/>--%>
							<%--</c:if>--%>
						</td>
					</tr>
					<tr>
						<td colspan="4" style="text-align:center;">考核总成绩</td>
						<td style="text-align:center;">100</td>
						<td style="text-align:center;">
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<input type="text" class="inputText validate[required,custom[number,min[0],max[100]]]" readonly="readonly" name="gradeScore" value="${formDataMap['gradeScore']}"/>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								${formDataMap['gradeScore']}
								<input type="hidden" class="inputText" name="gradeScore" value="${formDataMap['gradeScore']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td colspan="4" style="text-align:center;">医疗差错、事故</td>
						<td style="text-align:center;">有无</td>
						<td style="text-align:center;">
							<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
								<select name="ylsg" style="width:143px" class="select validate[required]">
									<option value="" >请选择</option>
									<option value="有" <c:if test="${formDataMap['ylsg']=='有'}">selected="selected"</c:if> >有</option>
									<option value="无" <c:if test="${formDataMap['ylsg']=='无'}">selected="selected"</c:if>>无</option>
								</select>
							</c:if>
							<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
								${formDataMap['ylsg']}
								<input type="hidden" class="inputText" name="ylsg" value="${formDataMap['ylsg']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td colspan="6" style="height:250px;">
							<div style="width:100%;height:150px;">
								考核评语：
								<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
									<textarea class="xltxtarea" name="memo" style="height:100px;">${formDataMap['memo']}</textarea>
								</c:if>
								<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
									${formDataMap['memo']}
									<input type="hidden" name="memo"  value="${formDataMap['memo']}"/>
								</c:if>
							</div>
							<div style="width:100%;height:100px;">

								<div style="text-align:right;padding-right:300px;">考核老师签名（二名）：</div>
								<div style="text-align:right;padding-right:300px;">管理小组组长签名：</div>
								<div  style="text-align:right;padding-right:157px;">
									日&#12288;&#12288;期：
									<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD) }">
										<input type="text" class="inputText validate[required]" name="signTime" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${empty formDataMap['signTime']?pdfn:getCurrDate():formDataMap['signTime']}"/>
									</c:if>
									<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD)) }">
										${formDataMap['signTime']}
										<input type="hidden" name="signTime"  value="${empty formDataMap['signTime']?pdfn:getCurrDate():formDataMap['signTime']}"/>
									</c:if>
								</div>
							</div>
						</td>
					</tr>
				</table>
				</div>
				<c:set var="teaSub" value="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId}"/>
				<c:set var="headSub" value="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD && (not empty rec.auditStatusId)}"/>
				<c:set var="showMsg" value="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId &&  empty rec.auditStatusId}"/>
				<p align="center" style="margin-top: 10px;">
					<c:if test="${teaSub||headSub}">
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