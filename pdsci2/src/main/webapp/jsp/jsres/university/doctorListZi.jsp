<script type="text/javascript">
$(document).ready(function(){
	$(".show").on("mouseenter mouseleave",
		function(){
			$(".info",this).toggle(100);
		}
	);
	heightFiexed();
});
function tableFixed(div){
	$("#dateFixed,#topTitle").css("top",$(div).scrollTop()+"px");
	$("#deptFixed,#topTitle").css("left",$(div).scrollLeft()+"px");
}
function heightFiexed(){
	//修正高度
	var toFixedTd = $(".toFiexdDept");
	$(".fixedBy").each(function(i){
		$(toFixedTd[i]).height($(this).height());
	});
	var fixTrTd = $(".fixTrTd");
	var fixTrTh = $(".fixTrTh");
	var fixTd = $(".fix");
	$(fixTrTd).each(function(i)
	{
		var maxheight=-1;
		$(fixTrTd[i]).find(".by").each(function(){
			if($(this).height()>maxheight) maxheight=$(this).height();
		});
		if(maxheight!=-1) {
			$(fixTrTh[i]).find(".fix").each(function(){
				$(this).height(maxheight);
			});
		}
	});
}

onresize = function(){
	heightFiexed();
};
//培训小结
function guiPeiDetail(doctorFlow)
{
	var url = "<s:url value='/jsres/doctorTheoryScore/guiPeiDetail?doctorFlow='/>"+doctorFlow;
	jboxOpen(url, "各轮转科室出科考核表",1200,500);
}
//非全科详情
function publicScoreDetail(scoreFlow)
{
	var url = "<s:url value='/jsres/doctorTheoryScore/publicScoreDetail?scoreFlow='/>"+scoreFlow;
	jboxOpen(url, "非全科专业住院医师成绩详情",800,200);
}
//技能成绩详情
function skillScoreDetail(doctorFlow,scoreFlow,scoreType,scoreYear)
{
	var url = "<s:url value='/jsres/doctorTheoryScore/scoreDetail?scoreFlow='/>"+scoreFlow+"&doctorFlow="+doctorFlow+"&scoreType="+scoreType+"&scoreYear="+scoreYear;
	if(scoreType=="theoryScore")
	{
		jboxOpen(url, "理论成绩详情",800,400);
	}else {
		jboxOpen(url, "技能成绩详情", 1200, 400);
	}
}

</script>
<div class="main_bd clearfix">
	<!--表格  -->
	<div id="tableContext" style="width:900px; margin-top: 10px;margin-bottom: 10px;overflow: auto;margin-left: 30px;" onscroll="tableFixed(this);">
		<!--第一次 -->
		<div id="dateFixed" style="height: 0px;overflow: visible;position: relative;">
			<table class="grid"style="width: auto;" >
				<tr>
					<th rowspan="2" style="min-width: 60px;max-width: 60px;" class="toFiexdDept">
						姓名
					</th>
					<th rowspan="2" style="min-width: 130px;max-width: 130px;" class="fixedBy">
						身份证号
					</th>
					<th rowspan="2" style="min-width: 60px;max-width: 60px;" class="fixedBy">
						性别
					</th>
					<th rowspan="2" style="min-width: 130px;max-width: 130px;" class="fixedBy">
						培训基地
					</th>
					<th rowspan="2" style="min-width: 130px;max-width: 130px;" class="fixedBy">
						培训专业
					</th>
					<th rowspan="2" style="min-width: 180px;max-width: 180px;" class="fixedBy">
						培训起止日期
					</th>
					<th rowspan="2" style="min-width: 60px;max-width: 60px;" class="fixedBy">
						学位
					</th>
					<th rowspan="2" style="min-width: 130px;max-width: 130px;" class="fixedBy">
						毕业证书编号
					</th>
					<th rowspan="2" style="min-width: 130px;max-width: 130px;" class="fixedBy">
						医师执业证书编号
					</th>
					<th rowspan="2" style="min-width: 130px;max-width: 130px;" class="fixedBy">
						执业范围
					</th>
					<th colspan="2" style="min-width: 60px;max-width: 60px;" class="fixedBy">
						理论成绩
					</th>
					<th colspan="2" style="min-width: 60px;max-width: 60px;" class="fixedBy">
						技能成绩
					</th>
					<th colspan="2" style="min-width: 60px;max-width: 60px;" class="fixedBy">
						公共科目考核成绩
					</th>
					<th rowspan="2" style="min-width: 130px;max-width: 130px;" class="fixedBy">
						审核意见
					</th>
					<th rowspan="2" style="min-width: 130px;max-width: 130px;" class="fixedBy">
						证书发放状态
					</th>
				</tr>
				<tr>
					<th  style="min-width: 60px;max-width: 60px;" class="fixedBy">
						年份
					</th>
					<th  style="min-width: 60px;max-width: 60px;" class="fixedBy">
						成绩
					</th>
					<th  style="min-width: 60px;max-width: 60px;" class="fixedBy">
						年份
					</th>
					<th style="min-width: 60px;max-width: 60px;" class="fixedBy">
						成绩
					</th>
					<th  style="min-width: 60px;max-width: 60px;" class="fixedBy">
						非全科专业成绩
					</th>
					<th  style="min-width: 60px;max-width: 60px;" class="fixedBy">
						全科专业成绩
					</th>
				</tr>
			</table>
		</div>
		<!--第二次 -->
		<div id="deptFixed" style="height: 0px;overflow: visible;position: relative;">
			<table class="grid" style="width: auto;border-right: 0px">
				<tr>
					<th rowspan="2" style="min-width: 60px;max-width: 60px;"  class="toFiexdDept">姓名</th>
				</tr>
				<tr>
				</tr>
				<c:forEach items="${doctorList}" var="doctor">
					<tr class="fixTrTh">
						<c:set var="doctorFlow" value="${doctor.USER_FLOW}"></c:set>
						<c:set var="userResumeExt" value="${userResumeExtMap[doctorFlow]}"></c:set>
						<c:set var="f" value="${isCountryOrgMap[doctorFlow]}"></c:set>
						<td style="height: 40px;background: white;" style="min-width: 60px;max-width: 60px;" class="fix">${doctor.USER_NAME}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<!--第三次  -->
		<div id="topTitle" style="width: 0px;overflow: visible;position: relative;height: 0px;">
			<table class="grid" style="width: auto;border-right: 0px" >
				<tr>
					<th rowspan="2" style="min-width: 60px;max-width: 60px;" class="toFiexdDept">姓名</th>
				</tr>
				<tr>
				</tr>
			</table>
		</div>
		<table class="grid" style="width: auto;">
			<tr>
				<th rowspan="2" style="min-width: 60px;max-width: 60px;" class="toFiexdDept">
					姓名
				</th>
				<th rowspan="2" style="min-width: 130px;max-width: 130px;"class="fixedBy">
					身份证号
				</th>
				<th rowspan="2" style="min-width: 60px;max-width: 60px;"class="fixedBy">
					性别
				</th>
				<th rowspan="2" style="min-width: 130px;max-width: 130px;"class="fixedBy">
					培训基地
				</th>
				<th rowspan="2" style="min-width: 130px;max-width: 130px;"class="fixedBy">
					培训专业
				</th>
				<th rowspan="2" style="min-width:180px;max-width: 180px;"class="fixedBy">
					培训起止日期
				</th>
				<th rowspan="2" style="min-width: 60px;max-width: 60px;"class="fixedBy">
					学位
				</th>
				<th rowspan="2" style="min-width: 130px;max-width: 130px;"class="fixedBy">
					毕业证书编号
				</th>
				<th rowspan="2" style="min-width: 130px;max-width: 130px;"class="fixedBy">
					医师执业证书编号
				</th>
				<th rowspan="2" style="min-width: 130px;max-width: 130px;"class="fixedBy">
					执业范围
				</th>
				<th colspan="2" style="min-width: 60px;max-width: 60px;"class="fixedBy">
					理论成绩
				</th>
				<th colspan="2" style="min-width: 60px;max-width: 60px;"class="fixedBy">
					技能成绩
				</th>
				<th colspan="2" style="min-width: 60px;max-width: 60px;"class="fixedBy">
					公共科目考核成绩
				</th>
				<th rowspan="2" style="min-width: 130px;max-width: 130px;"class="fixedBy">
					审核意见
				</th>
				<th rowspan="2" style="min-width: 130px;max-width: 130px;" class="fixedBy">
					证书发放状态
				</th>
			</tr>
			<tr>
				<th  style="min-width: 60px;max-width: 60px;"class="fixedBy">
					年份
				</th>
				<th  style="min-width: 60px;max-width: 60px;"class="fixedBy">
					成绩
				</th>
				<th style="min-width: 60px;max-width: 60px;"class="fixedBy">
					年份
				</th>
				<th  style="min-width: 60px;max-width: 60px;"class="fixedBy">
					成绩
				</th>
				<th  style="min-width: 60px;max-width: 60px;"class="fixedBy">
					非全科专业成绩
				</th>
				<th  style="min-width: 60px;max-width: 60px;"class="fixedBy">
					全科专业成绩
				</th>
			</tr>
			<c:forEach items="${doctorList}" var="doctor">
				<tr class="fixTrTd">
					<c:set var="doctorFlow" value="${doctor.DOCTOR_FLOW}"></c:set>
					<c:set var="userResumeExt" value="${userResumeExtMap[doctorFlow]}"></c:set>
					<td >${doctor.USER_NAME}</td>
					<td style="min-width: 60px;max-width: 60px;" class="by">${doctor.ID_NO}</td>
					<td style="min-width: 60px;max-width: 60px;" class="by">${doctor.SEX_NAME}</td>
					<td style="min-width: 60px;max-width: 60px;"class="by">${doctor.ORG_NAME}</td>
					<td style="min-width: 60px;max-width: 60px;" class="by">${doctor.TRAINING_SPE_NAME}</td>
					<td style="min-width: 180px;max-width: 180px;"class="by">${doctor.COMPLETE_START_DATE}~${doctor.COMPLETE_END_DATE}&#12288;<br/>[<a onclick="guiPeiDetail('${doctorFlow}')" >详情</a>]</td>
					<td style="min-width: 60px;max-width: 60px;"class="by">
						<c:choose>
							<c:when test="${ not empty userResumeExt.doctorDegreeName}">
								${userResumeExt.doctorDegreeName}
							</c:when>
							<c:when test="${ not empty userResumeExt.masterDegreeName}">
								${userResumeExt.masterDegreeName}
							</c:when>
							<c:otherwise>
								${userResumeExt.degreeName}
							</c:otherwise>
						</c:choose>
					</td>
					<td style="min-width: 60px;max-width: 60px;"class="by">
							${doctor.CERTIFICATE_NO}&#12288;
					<span id="" style="display:${!empty userResumeExt.certificateUri?'':'none'} ">
						<br/>[<a href="${sysCfgMap['upload_base_url']}/${userResumeExt.certificateUri}" target="_blank">详情</a>]&nbsp;
					</span>
					</td>
					<td style="min-width: 60px;max-width: 60px;"class="by">
						<c:if test="${userResumeExt.qualificationMaterialId eq '176'}">${userResumeExt.qualificationMaterialCode}</c:if>
					<span id="" style="display:${!empty userResumeExt.qualificationMaterialUri?'':'none'} ">
						<br/>[<a href="${sysCfgMap['upload_base_url']}/${userResumeExt.qualificationMaterialUri}" target="_blank">详情</a>]&nbsp;
					</span>
					</td>
					<td style="min-width: 60px;max-width: 60px;"class="by">
						<c:forEach items="${dictTypeEnumPracticeTypeList}" var="dict">
							<c:set var="dictKey" value="dictTypeEnumPracticeType.${dict.dictId}List"/>
							<c:forEach items="${applicationScope[dictKey]}" var="scope">
								<c:if test='${userResumeExt.practicingScopeId==scope.dictId}'>${scope.dictName}</c:if>
							</c:forEach>
						</c:forEach>
					</td>
					<td style="min-width: 60px;max-width: 60px;"class="by">${doctor.THEORY_SCORE_YEAR}</td>
					<td style="min-width: 60px;max-width: 60px;"class="by">${doctor.THEORY_SCORE}</td>
					<td style="min-width: 60px;max-width: 60px;"class="by">${doctor.SKILL_SCORE_YEAR}</td>
					<td style="min-width: 60px;max-width: 60px;"class="by">
						<c:if test="${doctor.SKILL_SCORE eq GlobalConstant.PASS}">合格</c:if>
						<c:if test="${doctor.SKILL_SCORE eq GlobalConstant.UNPASS}">不合格</c:if>
						<c:if test="${not empty doctor.SKILL_SCORE}">
							&#12288;[<a onclick="skillScoreDetail('${doctorFlow}','${doctor.SKILL_SCORE_FLOW}','skillScore','${doctor.SKILL_SCORE_YEAR}');" >详情</a>]&nbsp;
						</c:if>
					</td>
					<td style="min-width: 60px;max-width: 60px;"class="by"><c:if test="${doctor.PUBLIC_NOTALL_PASS eq GlobalConstant.PASS}">合格</c:if>
						<c:if test="${doctor.PUBLIC_NOTALL_PASS eq GlobalConstant.UNPASS}">不合格</c:if>
						<c:if test="${not empty doctor.PUBLIC_NOTALL_PASS}">
						&#12288;[<a onclick="publicScoreDetail('${doctor.PUBLIC_SCORE_FLOW}');" >详情</a>]&nbsp;
						</c:if>
					</td>
					<td style="min-width: 60px;max-width: 60px;"class="by">${doctor.PUBLIC_ALL}</td>
					<td style="min-width: 60px;max-width: 60px;"class="by">${doctor.DISAGREE_REASON}</td>
					<td style="min-width: 60px;max-width: 60px;"class="by">${doctor.GRADUATION_STATUS_NAME}</td>
				</tr>
			</c:forEach>
			<c:if test="${empty doctorList}">
				<tr>
					<td colspan="10" >无记录！</td>
				</tr>
			</c:if>
		</table>
	</div>

</div>

<script>
	$("#allChecked").click(function(){

			$("input[name='check']").attr("checked","checked");

	});
	$("#allBackChecked").click(function(){
		var elements=$("input[name='check']");
		for(var i=0;i<elements.length;i++)
		{
			var b=elements[i];
			if($(b).attr("checked")=="checked"){
				$(b).attr("checked",false);
			}else{
				$(b).attr("checked","checked");
			}
		}
	});
	heightFiexed();
</script>
<div class="page" style="padding-right: 40px;">
	<c:set var="pageView" value="${pdfn:getPageView(doctorList)}" scope="request"></c:set>
	<pd:pagination-jsres toPage="toPage"/>
</div>