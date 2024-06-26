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
function  applyAudit(doctorFlow,roleFlag,applyType)
{
	var msg="";
	if(applyType=="ManagerPassed")
	{
		msg="上报";
	}else if(applyType=="GrantCertf"){
		msg="同意发证";
	}
	jboxConfirm("确定"+msg+"？" , function(){
		jboxPost("<s:url value='/jsres/doctorScoreApply/doctorScoreApplyAudit'/>?doctorFlow="+doctorFlow+"&roleFlag="+roleFlag+"&applyType="+applyType+"&reason=",null,function(resp){
			if(resp=="${GlobalConstant.FLAG_Y}"){
				jboxTip(msg+"成功！！");
				toPage(1);
			}else{
				jboxTip(msg+"失败！");
			}
		},null,false);
	});
}

function  applyBack(doctorFlow,roleFlag,applyType)
{
	var msg="";
	if(applyType=="")
	{
		msg="证书退回";
	}else if(applyType=="UnGrantCertf"){
		msg="证书申请审核";
	}
	var url = "<s:url value='/jsres/doctorScoreApply/applyBack?doctorFlow='/>"+doctorFlow+"&roleFlag="+roleFlag+"&applyType="+applyType+"&reason=";
	jboxOpen(url, msg,600,300);
}
</script>
<div class="main_bd clearfix">
	<!--表格  -->
	<div id="tableContext" style="width:900px; margin-top: 10px;margin-bottom: 10px;overflow: auto;margin-left: 30px;" onscroll="tableFixed(this);">
		<!--第一次 -->
		<div id="dateFixed" style="height: 0px;overflow: visible;position: relative;">
			<table class="grid"style="width: auto;" >
				<tr>
					<c:if test="${roleFlag eq GlobalConstant.USER_LIST_LOCAL ||roleFlag eq GlobalConstant.USER_LIST_GLOBAL}">
						<th rowspan="2" style="min-width: 60px;max-width: 60px;" class="toFiexdDept">
							<input type="checkbox" >全选/<input type="checkbox" >反选
						</th>
						<th rowspan="2" style="min-width: 80px;max-width: 80px;" class="toFiexdDept">
							操作
						</th>
					</c:if>
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
						省厅意见
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
					<c:if test="${roleFlag eq GlobalConstant.USER_LIST_LOCAL ||roleFlag eq GlobalConstant.USER_LIST_GLOBAL}">
						<th rowspan="2" style="min-width: 60px;max-width: 60px;" class="toFiexdDept">
							<input type="checkbox" >全选/<input type="checkbox" >反选
						</th>
						<th rowspan="2" style="min-width: 80px;max-width: 80px;" class="toFiexdDept">
							操作
						</th>
					</c:if>
					<th rowspan="2" style="min-width: 60px;max-width: 60px;"  class="toFiexdDept">姓名</th>
				</tr>
				<tr>
				</tr>
				<c:forEach items="${doctorList}" var="doctor">
					<tr class="fixTrTh">
						<c:set var="doctorFlow" value="${doctor.USER_FLOW}"></c:set>
						<c:set var="userResumeExt" value="${userResumeExtMap[doctorFlow]}"></c:set>
						<c:set var="f" value="${isCountryOrgMap[doctorFlow]}"></c:set>
						<c:if test="${roleFlag eq GlobalConstant.USER_LIST_LOCAL ||roleFlag eq GlobalConstant.USER_LIST_GLOBAL}">
							<td style="height: 40px;background: white;" class="fix">
								<%--<c:if test="${f eq '1'}">--%>
									<c:if test="${doctor.GRADUATION_STATUS_ID eq 'Submit' and roleFlag eq GlobalConstant.USER_LIST_LOCAL and !(isJointOrg eq '1')}">
										<input type="checkbox" name="check" value="${doctor.DOCTOR_FLOW}">
									</c:if>
									<c:if test="${doctor.GRADUATION_STATUS_ID eq 'ManagerPassed' and roleFlag eq GlobalConstant.USER_LIST_GLOBAL }">
										<input type="checkbox" name="check" value="${doctor.DOCTOR_FLOW}">
									</c:if>
								<%--</c:if>--%>
								<%--<c:if test="${f eq '0'}">--%>
								<%--</c:if>--%>

							</td>
							<td  style="height: 40px;background: white;" class="fix">
								<%--<c:if test="${f eq '1'}">--%>
									<c:if test="${doctor.GRADUATION_STATUS_ID eq 'Submit' and roleFlag eq GlobalConstant.USER_LIST_LOCAL and !(isJointOrg eq '1') }">
										[<a   onclick="applyAudit('${doctorFlow}','${roleFlag}','ManagerPassed');" style="width: 50px">上报</a>]
										<br>
										[<a   onclick="applyBack('${doctorFlow}','${roleFlag}','');" style="width: 50px">退回</a>]
									</c:if>
									<c:if test="${roleFlag eq GlobalConstant.USER_LIST_GLOBAL }">
										<c:if test="${doctor.GRADUATION_STATUS_ID eq 'ManagerPassed'}" >
											[<a   onclick="applyAudit('${doctorFlow}','${roleFlag}','GrantCertf');" style="width: 50px">同意发证</a>]
											<br>
											[<a   onclick="applyBack('${doctorFlow}','${roleFlag}','UnGrantCertf');" style="width: 50px">暂不发证</a>]
										</c:if>
										<c:if test="${doctor.GRADUATION_STATUS_ID eq 'GrantCertf'}" >
											[<a   onclick="applyBack('${doctorFlow}','${roleFlag}','UnGrantCertf');" style="width: 50px">暂不发证</a>]
										</c:if>
										<c:if test="${doctor.GRADUATION_STATUS_ID eq 'UnGrantCertf'}" >
											[<a   onclick="applyAudit('${doctorFlow}','${roleFlag}','GrantCertf');" style="width: 50px">同意发证</a>]
										</c:if>
									</c:if>
								<%--</c:if>--%>
								<%--<c:if test="${f eq '0'}">--%>
								<%--</c:if>--%>
							</td>
						</c:if>
						<td style="height: 40px;background: white;" style="min-width: 60px;max-width: 60px;" class="fix">${doctor.USER_NAME}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<!--第三次  -->
		<div id="topTitle" style="width: 0px;overflow: visible;position: relative;height: 0px;">
			<table class="grid" style="width: auto;border-right: 0px" >
				<tr>
					<c:if test="${roleFlag eq GlobalConstant.USER_LIST_LOCAL ||roleFlag eq GlobalConstant.USER_LIST_GLOBAL}">
						<th rowspan="2" style="min-width: 60px;max-width: 60px;" class="toFiexdDept">
							<a id="allChecked" style="color:#459ae9;">全</a>/<a id="allBackChecked" style="color:#459ae9;">反</a>
						</th>
						<th rowspan="2" style="min-width: 80px;max-width: 80px;" class="toFiexdDept">
							操作
						</th>
					</c:if>
					<th rowspan="2" style="min-width: 60px;max-width: 60px;" class="toFiexdDept">姓名</th>
				</tr>
				<tr>
				</tr>
			</table>
		</div>
		<table class="grid" style="width: auto;">
			<tr>
				<c:if test="${roleFlag eq GlobalConstant.USER_LIST_LOCAL ||roleFlag eq GlobalConstant.USER_LIST_GLOBAL}">
					<th rowspan="2" style="min-width: 60px;max-width: 60px;" class="toFiexdDept">
						<input type="checkbox" >全选/<input type="checkbox">反选
					</th>
					<th rowspan="2" style="min-width: 80px;max-width:80px;"class="toFiexdDept">
						操作
					</th>
				</c:if>
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
					省厅意见
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
					<c:if test="${roleFlag eq GlobalConstant.USER_LIST_LOCAL ||roleFlag eq GlobalConstant.USER_LIST_GLOBAL}">
						<td >
							<input type="checkbox" />
						</td>
						<td >
							操作
						</td>
					</c:if>
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
						<c:if test="${not empty doctor.SKILL_SCORE}">
						<a title="详情" onclick="skillScoreDetail('${doctorFlow}','${doctor.SKILL_SCORE_FLOW}','skillScore','${doctor.SKILL_SCORE_YEAR}');" >
							<c:if test="${doctor.SKILL_SCORE eq GlobalConstant.PASS}">合格</c:if>
							<c:if test="${doctor.SKILL_SCORE eq GlobalConstant.UNPASS}">不合格</c:if>
						</a>
						</c:if>
					</td>
					<td style="min-width: 60px;max-width: 60px;"class="by">
						<c:if test="${not empty doctor.PUBLIC_NOTALL_PASS}">
							<a title="详情" onclick="publicScoreDetail('${doctor.PUBLIC_SCORE_FLOW}');" >
								<c:if test="${doctor.PUBLIC_NOTALL_PASS eq GlobalConstant.PASS}">合格</c:if>
								<c:if test="${doctor.PUBLIC_NOTALL_PASS eq GlobalConstant.UNPASS}">不合格</c:if>
							</a>
						</c:if>
					</td>
					<td style="min-width: 60px;max-width: 60px;"class="by">${doctor.PUBLIC_ALL}</td>
					<td style="min-width: 60px;max-width: 60px;"class="by">${doctor.DISAGREE_REASON}</td>
				</tr>
			</c:forEach>
			<c:if test="${empty doctorList}">
				<tr>
					<td style="text-align: left;" colspan="99">无记录</td>
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
//	$("input[name='check']").click(function(){
//		var n=0;
//		var m=0;
//		$("input[name='check']").each(function(){
//			n++;
//		});
//		$("input[name='check']:checked").each(function(){
//			m++;
//		});
//		if(n>m){
//			$("#allChecked").attr("checked",false);
//		}else{
//			$("#allChecked").attr("checked",true);
//		}
//	});
	heightFiexed();
</script>
<div class="page" style="padding-right: 40px;">
	<c:set var="pageView" value="${pdfn:getPageView(doctorList)}" scope="request"></c:set>
	<pd:pagination-jsres toPage="toPage"/>
</div>