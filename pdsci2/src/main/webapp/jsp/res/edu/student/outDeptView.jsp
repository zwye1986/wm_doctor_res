
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
<script>
function openAfterRec(recFlow,recTypeName,recTypeId){
	var url = "<s:url value='/res/rec/showRegistryFormNew'/>"+
	"?recFlow="+recFlow+
	"&schDeptFlow="+indexObj.schDeptFlow+
	"&rotationFlow="+indexObj.rotationFlow+
	"&recTypeId="+recTypeId+
	"&processFlow=${process.processFlow}"+
	"&operUserFlow=${process.userFlow}"+
	"&roleFlag=${param.roleFlag}&openType=open&resultFlow=${process.schResultFlow}";
	var h = $('.main_fix').height();		
	jboxOpen(url, recTypeName,1000,h);
}
function  exportDeptInfo(userFlow,schDeptFlow,processFlow)
{
	jboxTip("导出中,请稍等...");
	var url = '<s:url value="/schDept/export/exportDeptInfo"/>?userFlow='+userFlow+'&schDeptFlow='+schDeptFlow+'&processFlow='+processFlow;
	window.location.href = url;
}

// function workingAttitude(){
// 	var url = "<s:url value='/res/rec/showRegistryForm'/>"+
// 			"?recFlow=${workingAttitude.recFlow}"+
// 			"&schDeptFlow="+indexObj.schDeptFlow+
// 			"&rotationFlow="+indexObj.rotationFlow+
// 			"&recTypeId=${afterRecTypeEnumWorkingAttitude.id}"+
// 			"&roleFlag=${param.roleFlag}&openType=open";
// 	var h = $('.main_fix').height();		
// 	jboxOpen(url, "${afterRecTypeEnumWorkingAttitude.name}",800,h);
// }

function discipleSummary(userFlow,schDeptFlow,recTypeId){
	jboxOpen("<s:url value='/res/rec/showRegistryFormNew'/>?roleFlag=${param.roleFlag}&resultFlow=${process.schResultFlow}&processFlow=${process.processFlow}&userFlow="+userFlow+"&schDeptFlow="+schDeptFlow+"&recTypeId="+recTypeId,"门诊跟师个人小结",800,500);
}

//评分
function grade(recTypeName,recTypeId,recFlow){
	var w = $('.main_fix').width();
	jboxOpen("<s:url value='/res/rec/grade'/>?roleFlag=${param.roleFlag}&processFlow=${process.processFlow}&resultFlow=${process.schResultFlow}&schDeptFlow=${process.schDeptFlow}&rotationFlow=${result.rotationFlow}&recTypeId="+recTypeId+"&recFlow="+recFlow,
			   recTypeName,1000,500);
}
function doctorEval(recTypeName,recTypeId,recFlow,cfgFlow){
	var w = $('.main_fix').width();
	jboxOpen("<s:url value='/res/rec/doctorEval'/>?roleFlag=${param.roleFlag}&processFlow=${process.processFlow}&resultFlow=${process.schResultFlow}&schDeptFlow=${process.schDeptFlow}&rotationFlow=${result.rotationFlow}&recTypeId="+recTypeId+"&recFlow="+recFlow+"&cfgFlow="+cfgFlow,
			   recTypeName,w,500);
}

function annualTrainForm(){
	var h = $('.main_fix').height();
	jboxOpen("<s:url value='/jsp/res/edu/student/annualTrainForm.jsp'/>", "年度培训记录表",700,h);
}

function showCkkPapers(processFlow){
	jboxOpen("<s:url value='/res/exam/paper/list'/>?processFlow="+processFlow, "${process.schDeptName}出科记录",700,500);
}

$(document).ready(function() {
	//toggleItem('outDeptView');
	$("[onclick]").click(function(e){
		e.stopPropagation();
	});
});

//操作出科
function operAfter(){
	jboxPost("<s:url value='/res/rec/docOperAfter'/>?userFlow=${process.userFlow}&schDeptFlow=${process.schDeptFlow}&processFlow=${process.processFlow}",null,function(resp){
		if(resp=="${GlobalConstant.OPRE_SUCCESSED_FLAG}"){
			jboxTip("${GlobalConstant.OPRE_SUCCESSED}");
			$(".box.selected").click();
		}else{
			jboxTip("请先完成岗前培训表和对带教老师评价！");
		}
	},null,false);
}
function dengJi(){
	var url = '<s:url value="/res/rec/dengJi"/>';
	window.location.href = url;
}
function afterTest(){
	<c:set value="out_test_limit_${sessionScope.currUser.orgFlow}" var="limitNum"/>
	if("${!empty sysCfgMap[limitNum] && fn:length(resultses) ge sysCfgMap[limitNum]}" == "true"){
		jboxInfoBasic("出科考试已达考试限制次数！");
		return;
	}
	toTest();
}

function toTest(){
	var url = '<s:url value="/res/test/toTest"/>?processFlow=${process.processFlow}';
	window.open(url);
	$(".box.selected").click();
}
</script>
<style>
a {
cursor: pointer;
background: transparent;
color: #428bca;
text-decoration: none;
}
</style>
</head>
<body>
	<div class="toolkit bg-f6 toolkit-lg toolkit-bar" style="border-top: 0;cursor: pointer;" onclick="toggleItem('outDeptView',this);scrollHome('outDeptView');">
		<ul class="j_e-nav-tab toolkit-list fl">
			<li class="j_mainline_link toolkit-item toolkit-item-tab j_mainline_link_task router">
				<span id="operImg" class="tool_title">
					<img title="收缩" src="<s:url value='/css/skin/up.png'/>"/>
					<img title="展开" src="<s:url value='/css/skin/down.png'/>" style="display: none;"/>
				</span>
				<span class="tool_title">出科</span>
			</li>
		</ul>
<!-- 		<ul class="toolkit-list fr"> -->
<!-- 			<li class="toolkit-item dropdown-create create-stage fr task-tab-li"> -->
<%-- 				<c:if test="${GlobalConstant.FLAG_Y eq sysCfgMap['res_doc_oper_after']}"> --%>
<%-- 					<c:if test="${!(GlobalConstant.FLAG_Y eq process.schFlag)}"> --%>
<!-- 						<input type="button" value="出&#12288;科" class="search" style="margin-top: 10px;" onclick="operAfter();"> -->
<%-- 					</c:if> --%>
<%-- 				</c:if> --%>
<!-- 			</li> -->
<!-- 		</ul> -->
	</div>
	<div class="col-tb ps-r scrollwrapper" style="width: 99%;" id="outDeptView_div"> 
		<div class="col-cell j_center" style="width:100%;">
			<ul class=" e-list task-list" style="margin-top: 5px;width: 100%;">
				<c:if test="${cfg13 ne 'Y' or  sysCfgMap['res_isGlobalSch_flag'] eq 'Y'}">
					<c:set var="assessk" value="res_${resAssessTypeEnumTeacherAssess.id}"/>
					<c:if test="${sysCfgMap[assessk]== GlobalConstant.FLAG_Y}">
						<li  style="position: relative;">
							<span class="mark"><i></i></span>
								<span class="j_title  ellipsis" style="padding-left: 10px;width: 250px;text-align:left">
									<c:set var="rec" value="${requestScope[resRecTypeEnumTeacherGrade.id]}"/>
									<c:set var="mapKey" value="${resRecTypeEnumTeacherGrade.id}Map"/>
									<c:set var="dataMap" value="${requestScope[mapKey]}"/>
									<a id="${resRecTypeEnumTeacherGrade.id}_tooper" href="javascript:grade('${resRecTypeEnumTeacherGrade.name}','${resRecTypeEnumTeacherGrade.id}','${rec.recFlow}');">评价带教老师：${process.teacherUserName}</a>
								</span>
							&#12288;评分：
							<a href="javascript:grade('${resRecTypeEnumTeacherGrade.name}','${resRecTypeEnumTeacherGrade.id}','${rec.recFlow}');">${empty rec?'未评':(dataMap.totalScore+0)}分</a>
							<c:if test="${empty rec}"><c:set var="exitFlag" value="Y"/></c:if>
						</li>
					</c:if>

					<c:set var="assessk" value="res_${resAssessTypeEnumDeptAssess.id}"/>
					<c:if test="${sysCfgMap[assessk]== GlobalConstant.FLAG_Y}">
						<li  style="position: relative;">
							<span class="mark"><i></i></span>
							<span class="j_title  ellipsis" style="padding-left: 10px;width: 250px;text-align:left">
								<c:set var="rec" value="${requestScope[resRecTypeEnumDeptGrade.id]}"/>
								<c:set var="mapKey" value="${resRecTypeEnumDeptGrade.id}Map"/>
								<c:set var="dataMap" value="${requestScope[mapKey]}"/>
								<a id="${resRecTypeEnumDeptGrade.id}_tooper" href="javascript:grade('${resRecTypeEnumDeptGrade.name}','${resRecTypeEnumDeptGrade.id}','${rec.recFlow}');">评价科室：${process.schDeptName}</a>
							</span>
							&#12288;评分：
							<a href="javascript:grade('${resRecTypeEnumDeptGrade.name}','${resRecTypeEnumDeptGrade.id}','${rec.recFlow}');">${empty rec?'未评':(dataMap.totalScore)}分</a>
							<c:if test="${empty rec}"><c:set var="exitFlag" value="Y"/></c:if>
						</li>
					</c:if>
				</c:if>
				<c:if test="${cfg13 eq 'Y'and sysCfgMap['res_isGlobalSch_flag'] ne 'Y'}">
					<c:if test="${not empty EvaluationCfg}">
						<li  style="position: relative;">
							<span class="mark"><i></i></span>
							<span class="j_title  ellipsis" style="padding-left: 10px;width: 250px;text-align:left">
								<c:set var="rec" value="${requestScope[resRecTypeEnumDoctorEval.id]}"/>
								<c:set var="mapKey" value="${resRecTypeEnumDoctorEval.id}Map"/>
								<c:set var="dataMap" value="${requestScope[mapKey]}"/>
								<a id="${resRecTypeEnumDoctorEval.id}_tooper" href="javascript:doctorEval('${resRecTypeEnumDoctorEval.name}','${resRecTypeEnumDoctorEval.id}','${rec.recFlow}','${EvaluationCfg.cfgFlow}');">对科室评分开放表：${process.schDeptName}</a>
							</span>
							&#12288;评分：
							<%--<a href="javascript:doctorEval('${resRecTypeEnumDoctorEval.name}','${resRecTypeEnumDoctorEval.id}','${rec.recFlow}','${EvaluationCfg.cfgFlow}');">${empty rec?'未评':(dataMap.totalScore)}分</a>--%>
							<a href="javascript:doctorEval('${resRecTypeEnumDoctorEval.name}','${resRecTypeEnumDoctorEval.id}','${rec.recFlow}','${EvaluationCfg.cfgFlow}');">${empty rec?'未评':'已评'}分</a>
						</li>
					</c:if>
					<c:if test="${empty EvaluationCfg}">
						<li  style="position: relative;">
							<span class="mark"><i></i></span>
							<span class="j_title  ellipsis" style="padding-left: 10px;width: 250px;text-align:left">
								<a id="${resRecTypeEnumDoctorEval.id}_tooper" href="javascript:void(0);">评价科室：${process.schDeptName}</a>
							</span>
							<span style="color: red;">未配置科室评分表，请联系管理员</span>
						</li>
					</c:if>
				</c:if>

                <!-- 广医进修过程-->
                   <c:if test="${sysCfgMap['is_show_jxres'] eq 'Y'}">
                    <c:set var="assessk" value="res_${resAssessTypeEnumManagerAssess.id}"/>
                    <c:if test="${sysCfgMap[assessk] eq GlobalConstant.FLAG_Y}">
                    <li  style="position: relative;">
                        <span class="mark"><i></i></span>
							<span class="j_title  ellipsis" style="padding-left: 10px;width: 250px;text-align:left">
								<c:set var="rec" value="${requestScope[resRecTypeEnumManagerGrade.id]}"/>
								<c:set var="mapKey" value="${resRecTypeEnumManagerGrade.id}Map"/>
								<c:set var="dataMap" value="${requestScope[mapKey]}"/>
								<a id="${resRecTypeEnumManagerGrade.id}_tooper" href="javascript:grade('${resRecTypeEnumManagerGrade.name}','${resRecTypeEnumManagerGrade.id}','${rec.recFlow}');">评价继续教育科：${process.orgName}</a>
							</span>
                        &#12288;评分：
                        <a href="javascript:grade('${resRecTypeEnumManagerGrade.name}','${resRecTypeEnumManagerGrade.id}','${rec.recFlow}');">${empty rec?'未评':(dataMap.totalScore)}分</a>

                    </li>
                    </c:if>
                   </c:if>

<!-- 				<li  style="position: relative;"> -->
<!-- 					<span class="mark"><i></i></span> -->
<!-- 					<span class="j_title  ellipsis" style="padding-left: 10px;"> -->
<!-- 					<a href="#">出科考试</a> -->
<!-- 					（成绩： -->
<%-- 					<c:if test="${empty process.schScore}">暂无</c:if> --%>
<%-- 					<c:if test="${!empty process.schScore}"> --%>
<%-- 						<a href="javascript:evaluation();">${process.schScore}</a> --%>
<%-- 					</c:if>） --%>
<!-- 					</span> -->
<!-- 				</li> -->
				<c:if test="${GlobalConstant.FLAG_Y eq sysCfgMap['res_doc_reg_score']}">
					<c:set var="testSwitch" value="${sysCfgMap['res_after_test_switch'] eq GlobalConstant.FLAG_Y}"/>
					<c:set var="urlEmpt" value="${sysCfgMap['res_after_url_cfg'] != null and sysCfgMap['res_after_url_cfg'] != '' }"/>
					<%--<c:set var="orgTestSwitch" value="jswjw_${process.orgFlow}_P004"/>--%>
					<%--<c:set var="orgTestSwitch" value="${sysCfgMap[orgTestSwitch] eq GlobalConstant.FLAG_Y}"/>--%>
					<%--<c:set var="docTestSwitch" value="doc_test_switch_${process.orgFlow}_${process.userFlow}"/>--%>
					<%--<c:set var="docTestSwitch" value="${sysCfgMap[docTestSwitch] eq GlobalConstant.FLAG_Y}"/>--%>
					<%--<c:set var="testTypeFlag" value="${testSwitch && orgTestSwitch && docTestSwitch&&urlEmpt}"/>--%>
					<c:set var="ckks" value="${open eq GlobalConstant.FLAG_Y}"/>
					<c:set var="testTypeFlag" value="${testSwitch and urlEmpt and ckks}"/>

					<c:if test="${testTypeFlag}">
					<li  style="position: relative;">
						<span class="mark"><i></i></span>
						<span class="j_title  ellipsis" style="width: 190px;text-align: left;padding-left: 10px;">
							<script type="text/javascript">
								function saveScore(k,score){
									if(!score || isNaN(score)){
										return jboxTip("请填写数字!");
									}
									var data = {};
									data.scoreFlow = "${outScore.scoreFlow}";
									data.doctorFlow = "${process.userFlow}";
									data.resultFlow = "${process.schResultFlow}";
									data.processFlow = "${process.processFlow}";
									data.schDeptFlow = "${process.schDeptFlow}";
									data.schDeptName = "${process.schDeptName}";
									data[k] = score;
									jboxPost("<s:url value='/res/score/saveScore'/>",data,function(resp){
										if("${GlobalConstant.OPRE_SUCCESSED_FLAG}"==resp){
											$("."+k+":first").after('<span>'+score+'</span>');
											$("."+k).remove();
											$("div.box.selected").click();
										}
										if("${GlobalConstant.OPRE_FAIL_FLAG}"==resp){
											jboxTip("成绩保存失败,请稍后再试!");
										}
									},null,false);
								}
							</script>
									理论成绩(
									<c:if test="${empty outScore || empty outScore.theoryScore}">
										<a onclick="afterTest();">参加出科考试</a>
									</c:if>
									
									<c:if test="${!(empty outScore || empty outScore.theoryScore)}">
										<a onclick="afterTest();">${outScore.theoryScore}</a>
									</c:if>
									)
									<c:if test="${!(empty outScore || empty outScore.theoryScore)}">
										<c:set var="key12" value="jswjw_${doctor.orgFlow}_downExamFile"/>
										<c:if test="${sysCfgMap[key12]==GlobalConstant.FLAG_Y and testTypeFlag }">
											<a style="cursor: pointer" onclick="showCkkPapers('${process.processFlow}');">[查看试卷记录]</a>
										</c:if>
									</c:if>
						</span>
					</li>
					</c:if>
					<%--<c:if test="${!testTypeFlag}">--%>
						<%--<li  style="position: relative;">--%>
							<%--<span class="mark"><i></i></span>--%>
						<%--<span class="j_title  ellipsis" style="width: 190px;text-align: left;padding-left: 10px;">--%>
							<%--<script type="text/javascript">--%>
								<%--function saveScore(k,score){--%>
									<%--if(!score || isNaN(score)){--%>
										<%--return jboxTip("请填写数字!");--%>
									<%--}--%>
									<%--var data = {};--%>
									<%--data.scoreFlow = "${outScore.scoreFlow}";--%>
									<%--data.doctorFlow = "${process.userFlow}";--%>
									<%--data.resultFlow = "${process.schResultFlow}";--%>
									<%--data.processFlow = "${process.processFlow}";--%>
									<%--data.schDeptFlow = "${process.schDeptFlow}";--%>
									<%--data.schDeptName = "${process.schDeptName}";--%>
									<%--data[k] = score;--%>
									<%--jboxPost("<s:url value='/res/score/saveScore'/>",data,function(resp){--%>
										<%--if("${GlobalConstant.OPRE_SUCCESSED_FLAG}"==resp){--%>
<%--//											$("."+k+":first").after('<span>'+score+'</span>');--%>
<%--//											$("."+k).remove();--%>
											<%--$("div.box.selected").click();--%>
										<%--}--%>
										<%--if("${GlobalConstant.OPRE_FAIL_FLAG}"==resp){--%>
											<%--jboxTip("成绩保存失败,请稍后再试!");--%>
										<%--}--%>
									<%--},null,false);--%>
								<%--}--%>
							<%--</script>--%>
									<%--理论成绩(--%>
										<%--<c:if test="${empty outScore.theoryScore}">--%>
											<%--<a class="theoryScore" onclick="$('.theoryScore').toggle();">--%>
												<%--输入成绩--%>
											<%--</a>--%>
											<%--<input class="inputText theoryScore"--%>
												   <%--type="text"--%>
												   <%--value=""--%>
												   <%--style="display: none;width: 60px;"--%>
												   <%--onchange="saveScore('theoryScore',this.value);"/>--%>
										<%--</c:if>--%>
										<%--<c:if test="${!empty outScore.theoryScore}">--%>
											<%--${outScore.theoryScore}--%>
										<%--</c:if>--%>
									<%--)--%>
						<%--</span>--%>
						<%--</li>--%>
					<%--</c:if>--%>
				</c:if>
<%-- 				<c:set var="workKey" value="res_${afterRecTypeEnumWorkingAttitude.id}_form_flag"/> --%>
<%-- 				<c:if test="${GlobalConstant.FLAG_Y eq sysCfgMap[workKey]}"> --%>
<!-- 					<li  style="position: relative;"> -->
<!-- 						<span class="mark"><i></i></span> -->
<%-- 						<span class="j_title  ellipsis" style="padding-left: 10px;"><a onclick="workingAttitude();">${afterRecTypeEnumWorkingAttitude.name}(${empty workingAttitude.recFlow?"未填写":"已填写"})</a></span> --%>
<!-- 					</li> -->
<%-- 				</c:if> --%>
				
				<c:set var="dopsKey" value="res_${afterRecTypeEnumDOPS.id}_form_flag"/>
				<c:if test="${GlobalConstant.FLAG_Y eq sysCfgMap[dopsKey]
							&& pdfn:findChineseOrWestern(docUser.medicineTypeId,afterRecTypeEnumDOPS.id)}">
					<c:set var="rec" value="${requestScope[afterRecTypeEnumDOPS.id]}"/>
					<c:if test="${!empty rec}">
						<c:set var="mapKey" value="${afterRecTypeEnumDOPS.id}Map"/>
						<c:set var="dataMap" value="${requestScope[mapKey]}"/>
						<li  style="position: relative;">
							<span class="mark"><i></i></span>
							<span class="j_title  ellipsis" style="padding-left: 10px;"><a onclick="openAfterRec('${rec.recFlow}','${afterRecTypeEnumDOPS.name}','${afterRecTypeEnumDOPS.id}');">${afterRecTypeEnumDOPS.name}</a></span>
						</li>
					</c:if>
				</c:if>
				
				<c:set var="minicexKey" value="res_${afterRecTypeEnumMini_CEX.id}_form_flag"/>
				<c:if test="${GlobalConstant.FLAG_Y eq sysCfgMap[minicexKey]
							&& pdfn:findChineseOrWestern(docUser.medicineTypeId,afterRecTypeEnumMini_CEX.id)}">
					<c:set var="rec" value="${requestScope[afterRecTypeEnumMini_CEX.id]}"/>
					<c:if test="${!empty rec}">
						<c:set var="mapKey" value="${afterRecTypeEnumMini_CEX.id}Map"/>
						<c:set var="dataMap" value="${requestScope[mapKey]}"/>
						<li  style="position: relative;">
							<span class="mark"><i></i></span>
							<span class="j_title  ellipsis" style="padding-left: 10px;"><a onclick="openAfterRec('${rec.recFlow}','${afterRecTypeEnumMini_CEX.name}','${afterRecTypeEnumMini_CEX.id}');">${afterRecTypeEnumMini_CEX.name}</a></span>
						</li>
					</c:if>
				</c:if>
				
				<c:set var="afterKey" value="res_${afterRecTypeEnumAfterEvaluation.id}_form_flag"/>
				<c:if test="${GlobalConstant.FLAG_Y eq sysCfgMap[afterKey]
							&& pdfn:findChineseOrWestern(docUser.medicineTypeId,afterRecTypeEnumAfterEvaluation.id)}">
					<c:set var="rec" value="${requestScope[afterRecTypeEnumAfterEvaluation.id]}"/>
					<c:if test="${!empty rec}">
						<c:set var="mapKey" value="${afterRecTypeEnumAfterEvaluation.id}Map"/>
						<c:set var="dataMap" value="${requestScope[mapKey]}"/>
						<li  style="position: relative;">
							<span class="mark"><i></i></span>
							<span class="j_title  ellipsis" style="padding-left: 10px;"><a onclick="openAfterRec('${rec.recFlow}','${afterRecTypeEnumAfterEvaluation.name}','${afterRecTypeEnumAfterEvaluation.id}');">${afterRecTypeEnumAfterEvaluation.name}</a></span>
						</li>
					</c:if>
				</c:if>
				
				<!-- 
				<li  style="position: relative;">
					<span class="mark"><i></i></span>
					<span class="j_title  ellipsis" style="padding-left: 10px;"><a href="javascript:void();" onclick="annualTrainForm();">年度考核表</a></span>
				</li>
				 -->
				 <c:set var="summaryKey" value="res_${afterRecTypeEnumAfterSummary.id}_form_flag"/>
				 <c:if test="${GlobalConstant.FLAG_Y eq sysCfgMap[summaryKey]
							&& pdfn:findChineseOrWestern(docUser.medicineTypeId,afterRecTypeEnumAfterSummary.id)}">
				 	<c:set var="rec" value="${requestScope[afterRecTypeEnumAfterSummary.id]}"/>
				 	<c:set var="mapKey" value="${afterRecTypeEnumAfterEvaluation.id}Map"/>
					<c:set var="dataMap" value="${requestScope[mapKey]}"/>
					<li  style="position: relative;">
						<span class="mark"><i></i></span>
						<span class="j_title  ellipsis" style="padding-left: 10px;"><a onclick="afterSummary('${process.userFlow }','${process.schDeptFlow }','${resRecTypeEnumAfterSummary.id}');">出科小结</a></span>
					</li>
				</c:if>
				 <c:set var="summaryKey" value="res_${afterRecTypeEnumDiscipleSummary.id}_form_flag"/>
				 <c:if test="${GlobalConstant.FLAG_Y eq sysCfgMap[summaryKey]
							&& pdfn:findChineseOrWestern(docUser.medicineTypeId,afterRecTypeEnumDiscipleSummary.id)}">
					<li  style="position: relative;">
						<span class="mark"><i></i></span>
						<span class="j_title  ellipsis" style="padding-left: 10px;"><a onclick="discipleSummary('${process.userFlow }','${process.schDeptFlow }','${resRecTypeEnumDiscipleSummary.id}');">门诊跟师个人小结</a></span>
					</li>
				</c:if>
				<!-- <li><span class="mark"><i></i></span>&nbsp;&nbsp;&nbsp;<a onclick="dengJi();">登记手册</a></li> -->
				<c:if test="${isMonthOpen eq 'Y'}">
					<c:set var="monthKey" value="res_${afterRecTypeEnumMonthlyAssessment_clinic.id}_form_flag"/>
					<c:if test="${GlobalConstant.FLAG_Y eq sysCfgMap[monthKey]
							&& pdfn:findChineseOrWestern(docUser.medicineTypeId,afterRecTypeEnumMonthlyAssessment_clinic.id)}">
						<c:set var="rec" value="${requestScope[afterRecTypeEnumMonthlyAssessment_clinic.id]}"/>
						<c:if test="${!empty rec}">
							<c:set var="mapKey" value="${afterRecTypeEnumMonthlyAssessment_clinic.id}Map"/>
							<c:set var="dataMap" value="${requestScope[mapKey]}"/>
							<li  style="position: relative;">
								<span class="mark"><i></i></span>
								<span class="j_title  ellipsis" style="padding-left: 10px;"><a onclick="openAfterRec('${rec.recFlow}','${afterRecTypeEnumMonthlyAssessment_clinic.name}','${afterRecTypeEnumMonthlyAssessment_clinic.id}');">${afterRecTypeEnumMonthlyAssessment_clinic.name}</a></span>
							</li>
						</c:if>
					</c:if>
					<c:set var="month2Key" value="res_${afterRecTypeEnumMonthlyAssessment_inpatientArea.id}_form_flag"/>
					<c:if test="${GlobalConstant.FLAG_Y eq sysCfgMap[month2Key]
							&& pdfn:findChineseOrWestern(docUser.medicineTypeId,afterRecTypeEnumMonthlyAssessment_inpatientArea.id)}">
						<c:set var="rec" value="${requestScope[afterRecTypeEnumMonthlyAssessment_inpatientArea.id]}"/>
						<c:if test="${!empty rec}">
							<c:set var="mapKey" value="${afterRecTypeEnumMonthlyAssessment_inpatientArea.id}Map"/>
							<c:set var="dataMap" value="${requestScope[mapKey]}"/>
							<li  style="position: relative;">
								<span class="mark"><i></i></span>
								<span class="j_title  ellipsis" style="padding-left: 10px;"><a onclick="openAfterRec('${rec.recFlow}','${afterRecTypeEnumMonthlyAssessment_inpatientArea.name}','${afterRecTypeEnumMonthlyAssessment_inpatientArea.id}');">${afterRecTypeEnumMonthlyAssessment_inpatientArea.name}</a></span>
							</li>
						</c:if>
					</c:if>
				</c:if>
				<c:if test="${not empty result.afterFileFlow}">

					<li  style="position: relative;">
						<span class="mark"><i></i></span>
						<span class="j_title  ellipsis" style="padding-left: 10px;"><a href="<s:url value='/res/teacher/downFile'/>?fileFlow=${result.afterFileFlow}">出科考核材料</a></span>
					</li>
				</c:if>
				<c:if test="${GlobalConstant.FLAG_Y eq sysCfgMap['sch_dept_info_detail_download']}">
					<li  style="position: relative;">
						<span class="mark"><i></i></span>
						<span class="j_title  ellipsis" style="padding-left: 10px;"><a onclick="exportDeptInfo('${process.userFlow }','${process.schDeptFlow }','${process.processFlow}');">科室轮转情况导出</a></span>
					</li>
				</c:if>
			</ul>
		</div>
	</div>
</body>
<script>
	function afterSummary(userFlow,schDeptFlow,recTypeId){
		if("${exitFlag}"=="Y"){
			jboxInfoBasic("请填写对带教老师和科室评价后再填写出科小结！");
			return;
		}
		jboxOpen("<s:url value='/res/rec/showRegistryFormNew'/>?roleFlag=${param.roleFlag}&resultFlow=${process.schResultFlow}&processFlow=${process.processFlow}&userFlow="+userFlow+"&schDeptFlow="+schDeptFlow+"&recTypeId="+recTypeId,"出科小结",800,500);
	}
</script>
</html>
	