<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
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
<style type="text/css">
.boxHome .item:HOVER{background-color: #eee;}
.cur{color:red;}
</style>
<script type="text/javascript">
$(document).ready(function(){
	$('#sessionNumber').datepicker({
		startView: 2, 
		maxViewMode: 2,
		minViewMode:2,
		format:'yyyy'
	});
	$('#graduationYear').datepicker({
		startView: 2, 
		maxViewMode: 2,
		minViewMode:2,
		format:'yyyy'
	});
	$('.datepicker').datepicker();
});
	//非全科详情
	function publicScoreDetail(scoreFlow)
	{
		var url = "<s:url value='/hbzy/doctorTheoryScore/publicScoreDetail?scoreFlow='/>"+scoreFlow;
		jboxOpen(url, "非全科专业住院医师成绩详情",800,200);
	}
	//技能成绩详情
	function skillScoreDetail(doctorFlow,scoreFlow,scoreType)
	{
		var url = "<s:url value='/hbzy/doctorTheoryScore/scoreDetail?scoreFlow='/>"+scoreFlow+"&doctorFlow="+doctorFlow+"&scoreType="+scoreType;
		if(scoreType=="theoryScore")
		{
			jboxOpen(url, "理论成绩详情",800,400);
		}else {
			jboxOpen(url, "技能成绩详情", 1200, 400);
		}
	}
	//提交
	function submit(doctorFlow)
	{
		var msg="";
//		var completeStartDate=$("#completeStartDate").val();
//		var completeEndDate=$("#completeEndDate").val();
//		if(!completeStartDate&&!completeEndDate)
//		{
//			msg+="培训起止日期为空，";
//		}else{
//			msg="";
//		}
		jboxConfirm(msg+"确认申请？" , function() {
			jboxPost("<s:url value='/hbzy/doctorTheoryScore/doctorSubmit?doctorFlow='/>" + doctorFlow, null, function (resp) {
				if ("1" == resp) {
					jboxTip("申请成功!");
					jboxLoad("content", "<s:url value='/hbzy/doctor/owenScore'/>", true);
				}
				if ("0" == resp) {
					jboxTip("申请失败!");
				}
			}, null, false);
		});
	}
	//保存培训日期
	function saveCompleteDate(doctorFlow,key,obj)
	{
		var time =$(obj).val();
		if(!time){
			jboxTip("请填写培训时间！");
			return false;
		}
		jboxPost("<s:url value='/hbzy/doctorTheoryScore/saveCompleteDate?doctorFlow='/>"+doctorFlow+"&key="+key+"&time="+time,null,function(resp){
			if("1"==resp){
				jboxTip("保存成功!");
			}
			if("0"==resp){
				jboxTip("保存失败,请稍后再试!");
			}
			if("2"==resp){
				jboxTip("培训开始日期不得大于培训结束日期");
			}
			if("3"==resp){
				jboxTip("培训结束日期不得小于培训开始日期");
			}
		},null,false);
	}
	//培训小结
	function guiPeiDetail(doctorFlow)
	{
		var url = "<s:url value='/hbzy/doctorTheoryScore/guiPeiDetail?doctorFlow='/>"+doctorFlow;
		jboxOpen(url, "各轮转科室出科考核表",1200,500);
	}
</script>
<div class="main_hd">
		<h2 class="underline">成绩查询</h2>
</div>
	<div class="search_table" style="margin-top:20px;">
<div class="main_bd" id="div_table_0" style="display: none;" >
	<h4 >基本信息</h4>
	<table border="0" cellpadding="0" cellspacing="0" class="base_info" >
		<colgroup>
			<col width="25%"/>
			<col width="25%"/>
			<col width="25%"/>
			<col width="25%"/>
		</colgroup>
		<tbody>
		<tr>
			<th>姓名：</th>
			<td>
				${user.userName}
			</td>
			<th>性别：</th>
			<td>
				<c:if test="${user.sexId eq userSexEnumMan.id}">&nbsp;${userSexEnumMan.name}</c:if><c:if test="${user.sexId eq userSexEnumWoman.id}">&nbsp;${userSexEnumWoman.name}</c:if>
			</td>
			<td rowspan="5" style="text-align: center;">
				<img src="${sysCfgMap['upload_base_url']}/${user.userHeadImg}" id="showImg"
					 style="margin-top: 3px;" width="130px" height="150px"
					 onerror="this.src='<s:url value="/css/skin/up-pic.jpg"/>'"/>
				<br><font>个人电子照片因用于合格证书打印，故请仔细核对</font>
			</td>
		</tr>
		<tr>
			<th>证件类型：</th>
			<td>

				<c:forEach items="${certificateTypeEnumList}" var="certificateType">
					<c:if test="${user.cretTypeId eq certificateType.id}">&nbsp;${certificateType.name}</c:if>
				</c:forEach>

			</td>
			<th>证&nbsp;件&nbsp;号：</th>
			<td>
				${user.idNo}
			</td>
		</tr>
		<tr>
			<th>培训基地：</th>
			<td>
				${resDoctor.orgName}
			</td>
			<th>培训专业：</th>
			<td>
				${resDoctor.trainingSpeName}
			</td>
		</tr>
		<tr>
			<th>届别/培训年限：</th>
			<td>
				${resDoctor.sessionNumber}/<c:if test="${'OneYear' eq resDoctor.trainingYears}">一年</c:if>
					<c:if test="${'TwoYear' eq resDoctor.trainingYears}">两年</c:if>
					<c:if test="${'ThreeYear' eq resDoctor.trainingYears}">三年</c:if>
			</td>
			<th>结业考核年份：</th>
			<td>
				${doctorRecruit.graduationYear}
			</td>
		</tr>
		<tr>
			<th>培训起止日期：</th>
			<td colspan="3">
				<%--<input id="completeStartDate" name="resDoctor.completeStartDate" value="${resDoctor.completeStartDate}" onchange="saveCompleteDate('${resDoctor.doctorFlow}','completeStartDate',this);" class="input datepicker" style="width: 149px;" readonly="readonly"/>~--%>
				<%--<input id="completeEndDate" name="resDoctor.completeEndDate" value="${resDoctor.completeEndDate}"	onchange="saveCompleteDate('${resDoctor.doctorFlow}','completeEndDate',this);" class="input datepicker" style="width: 149px;" readonly="readonly"/>--%>

				<%--<c:if test="${! (resDoctor.graduationStatusId eq 'GrantCertf') }">--%>
					<%--<input name="resDoctor.completeStartDate" value="${resDoctor.completeStartDate}" onchange="saveCompleteDate('${resDoctor.doctorFlow}','completeStartDate',this);" class="input datepicker" style="width: 149px;" readonly="readonly"/>~--%>
					<%--<input name="resDoctor.completeEndDate" value="${resDoctor.completeEndDate}"	onchange="saveCompleteDate('${resDoctor.doctorFlow}','completeEndDate',this);" class="input datepicker" style="width: 149px;" readonly="readonly"/>--%>
				<%--</c:if>--%>
				<c:if test="${not empty doctorRecruit}">
					${resDoctor.completeStartDate}~	${resDoctor.completeEndDate}
				</c:if>
				&#12288;[<a onclick="guiPeiDetail('${resDoctor.doctorFlow}')" >详情</a>]&nbsp;
			</td>
		</tr>
		<tr>
			<th>学位：</th>
			<td>
				<%--<c:if test="${userResumeExt.isMaster eq GlobalConstant.FLAG_Y }">硕士</c:if>--%>
				<%--<c:if test="${userResumeExt.isDoctor eq GlobalConstant.FLAG_Y }">博士</c:if>--%>
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
			<th>毕业证书编号：</th>
			<td colspan="2">${resDoctor.certificateNo}&#12288;
				<span id="" style="display:${!empty userResumeExt.certificateUri?'':'none'} ">
					[<a href="${sysCfgMap['upload_base_url']}/${userResumeExt.certificateUri}" target="_blank">证书详情</a>]&nbsp;
				</span>
			</td>
		</tr>
		<tr>
			<th>医师执业证书编号：</th>
			<td><c:if test="${userResumeExt.qualificationMaterialId eq '176'}">${userResumeExt.qualificationMaterialCode}</c:if>
			<span id="" style="display:${!empty userResumeExt.qualificationMaterialUri?'':'none'} ">
				[<a href="${sysCfgMap['upload_base_url']}/${userResumeExt.qualificationMaterialUri}" target="_blank">证书详情</a>]&nbsp;
			</span>
			</td>
			<th>执业范围：</th>
			<td colspan="2"><c:forEach items="${dictTypeEnumPracticeTypeList}" var="dict">
				<c:set var="dictKey" value="dictTypeEnumPracticeType.${dict.dictId}List"/>
				<c:forEach items="${applicationScope[dictKey]}" var="scope">
					<c:if test='${userResumeExt.practicingScopeId==scope.dictId  and dict.dictId == userResumeExt.practicingCategoryId}'>${scope.dictName}</c:if>
				</c:forEach>
			</c:forEach></td>
		</tr>
		</tbody>
	</table>
</div>
		<div class="main_bd" id="div_table_1" >
		<h4 >成绩信息</h4>
		<table border="0" cellpadding="0" cellspacing="0" class="base_info" >
			<colgroup>
				<col width="16.6%"/>
				<col width="16.6%"/>
				<col width="16.6%"/>
				<col width="16.6%"/>
				<col width="16.6%"/>
				<col width="16.6%"/>
			</colgroup>
			<tbody>
			<%--<tr>
				<th style="text-align: center;" rowspan="2">公共科目成绩</th>
				<td style="text-align: center;">卫生法律和法规</td>
				<td style="text-align: center;">循证医学</td>
				<td style="text-align: center;">临床思维与人际沟通</td>
				<td style="text-align: center;">重点传染病防治知识</td>
				<td style="text-align: center;">是否合格</td>
			</tr>
			<tr>
				<td style=" text-align: center;">
					${extScore.lawScore}
				</td>
				<td style=" text-align: center;">
					${extScore.medicineScore}
				</td>
				<td style=" text-align: center;">
					${extScore.clinicalScore}
				</td>
				<td style=" text-align: center;">
					${extScore.ckScore}
				</td>
				<td style=" text-align: center;">
					<c:if test="${publicScore.skillScore eq GlobalConstant.PASS}">合格</c:if>
					<c:if test="${publicScore.skillScore eq GlobalConstant.UNPASS}">不合格</c:if>
				</td>
			</tr>
			<tr>
				<th style="text-align: center;">全科医学及相关理论知识考核</th>
				<td style="text-align: center;" colspan="5">${publicScore.theoryScore}
					<c:if test="${publicScore.theoryScore>=60}">【合格】</c:if>
					<c:if test="${publicScore.theoryScore<60}">【不合格】</c:if>
				</td>
			</tr>--%>
			<tr>
				<th style="text-align: center;" <c:if test="${not empty theoryList}"> rowspan="${fn:length(theoryList)+1}"</c:if><c:if test="${ empty theoryList}">rowspan="2"</c:if>>省统考理论成绩</th>
				<th style="text-align: center;" colspan="2">考核年份</th>
				<th style="text-align: center;" colspan="3">成绩</th>
			</tr>
			<c:forEach items="${theoryList}" var="resScore">
				<tr>
					<td  style="text-align: center;"colspan="2">${resScore.scorePhaseId}</td>
					<td  style="text-align: center;"colspan="3">${resScore.theoryScore eq '1'?'合格':(resScore.theoryScore eq '0'?'不合格':'缺考')}</td>
				</tr>
			</c:forEach>
			<c:if test="${empty theoryList}">
				<tr>
					<td colspan="5" align="center">无记录！</td>
				</tr>
			</c:if>
			</tbody>
		</table>
		<table border="0" cellpadding="0" cellspacing="0"style="border-top:0px; " class="base_info" >
			<colgroup>
				<col width="12%"/>
				<col width="6%"/>
				<col width="6%"/>
				<col width="4%"/>
				<col width="4%"/>
				<col width="4%"/>
				<col width="4%"/>
				<col width="4%"/>
				<col width="4%"/>
				<col width="4%"/>
				<col width="4%"/>
				<col width="8%"/>
				<col width="8%"/>
			</colgroup>
			<tbody>
			<tr style=" width:100%;text-align: center;">
				<th style="text-align: center;" <c:if test="${not empty skillList}"> rowspan="${fn:length(skillList)+1}"</c:if><c:if test="${ empty skillList}">rowspan="2"</c:if>>技能成绩</th>
				<th style="text-align: center;">考核年份</th>
				<th style="text-align: center;">第一站</th>
				<th style="text-align: center;">第二站</th>
				<th style="text-align: center;">第三站</th>
				<th style="text-align: center;">第四站</th>
				<th style="text-align: center;">第五站</th>
				<th style="text-align: center;">第六站</th>
				<th style="text-align: center;">第七站</th>
				<th style="text-align: center;">第八站</th>
				<th style="text-align: center;">第九站</th>
				<th style="text-align: center;">总分</th>
				<th style="text-align: center;">是否合格</th>
			</tr>
			<c:forEach items="${skillList}" var="resScore">
				<c:set var="extScore" value="${skillExtScoreMap[resScore.scoreFlow]}"></c:set>
				<c:set var="firstStationScore" value="${extScore.firstStationScore}"></c:set>
				<c:set var="secondStationScore" value="${extScore.secondStationScore}"></c:set>
				<c:set var="thirdStationScore" value="${extScore.thirdStationScore}"></c:set>
				<c:set var="fourthStationScore" value="${extScore.fourthStationScore}"></c:set>
				<c:set var="fifthStationScore" value="${extScore.fifthStationScore}"></c:set>
				<c:set var="sixthStationScore" value="${extScore.sixthStationScore}"></c:set>
				<c:set var="seventhStationScore" value="${extScore.seventhStationScore}"></c:set>
				<c:set var="eighthStationScore" value="${extScore.eighthStationScore}"></c:set>
				<c:set var="ninthStationScore" value="${extScore.ninthStationScore}"></c:set>
				<c:set var="xiji1" value="${firstStationScore+secondStationScore+thirdStationScore}"></c:set>
				<c:set var="xiji2" value="${fourthStationScore+fifthStationScore+sixthStationScore	 +seventhStationScore+eighthStationScore}"></c:set>
				<c:set var="all" value="${firstStationScore+secondStationScore+thirdStationScore+fourthStationScore+fifthStationScore+sixthStationScore
					 +seventhStationScore+eighthStationScore+ninthStationScore }"></c:set>
				<tr>
					<td  style="text-align: center;">${resScore.scorePhaseId}</td>
					<td  style="text-align: center;">${firstStationScore}</td>
					<td  style="text-align: center;">${secondStationScore}</td>
					<td  style="text-align: center;">${thirdStationScore}</td>
					<td  style="text-align: center;">${fourthStationScore}</td>
					<td  style="text-align: center;">${fifthStationScore}</td>
					<td  style="text-align: center;">${sixthStationScore}</td>
					<td  style="text-align: center;">${seventhStationScore}</td>
					<td  style="text-align: center;">${eighthStationScore}</td>
					<td  style="text-align: center;">${ninthStationScore}</td>
					<td  style="text-align: center;">${all}</td>
					<td  style="text-align: center;">${resScore.skillScore eq '1'?'合格':(resScore.skillScore eq '0'?'不合格':'缺考')}</td>
				</tr>

			</c:forEach>
			<c:if test="${empty skillList}">
				<tr>
					<td colspan="14" align="center">无记录！</td>
				</tr>
			</c:if>
			<tr style="display:none;">
				<th style="text-align: center;">
					<c:if test="${ (resDoctor.graduationStatusId eq 'ManagerPassed') or (empty resDoctor.graduationStatusId) }">
						基地意见
					</c:if>
					<c:if test="${ (resDoctor.graduationStatusId eq 'GrantCertf') or (resDoctor.graduationStatusId eq 'UnGrantCertf') }">
						省厅意见
					</c:if>
				</th>
				<td colspan="14">${resDoctor.disagreeReason}</td>
			</tr>
			</tbody>
		</table>
			<br>
		<%--<table border="0" cellpadding="0" cellspacing="0" class="base_info" >--%>
			<%--<colgroup>--%>
				<%--<col width="25%"/>--%>
				<%--<col width="25%"/>--%>
				<%--<col width="25%"/>--%>
				<%--<col width="25%"/>--%>
			<%--</colgroup>--%>
			<%--<tbody>--%>
		<%--<tr>--%>
			<%--<th>理论成绩</th>--%>
			<%--<td>${theoryScore.theoryScore}--%>
				<%--<c:if test="${!empty theoryScore.theoryScore}">(${theoryScore.scorePhaseId})</c:if>&#12288;--%>
				<%--&lt;%&ndash;<input type="button" class="btn_blue" onclick="skillScoreDetail('${resDoctor.doctorFlow}','${publicScore.scoreFlow}');" value="详情"/>&nbsp;&ndash;%&gt;--%>
				<%--&#12288;[<a onclick="skillScoreDetail('${resDoctor.doctorFlow}','${publicScore.scoreFlow}','theoryScore');" >详情</a>]&nbsp;--%>
			<%--</td>--%>
			<%--<th>技能成绩</th>--%>
			<%--<td><c:if test="${skillScore.skillScore eq GlobalConstant.PASS}">合格</c:if><c:if test="${skillScore.skillScore eq GlobalConstant.UNPASS}">不合格</c:if>--%>
				<%--<c:if test="${!empty skillScore.skillScore}">(${skillScore.scorePhaseId})</c:if>&#12288;--%>
				<%--&lt;%&ndash;<input type="button" class="btn_blue" onclick="skillScoreDetail('${resDoctor.doctorFlow}','${publicScore.scoreFlow}');" value="详情"/>&nbsp;&ndash;%&gt;--%>
				<%--&#12288;[<a onclick="skillScoreDetail('${resDoctor.doctorFlow}','${publicScore.scoreFlow}','skillScore');" >详情</a>]&nbsp;--%>
			<%--</td>--%>
		<%--</tr>--%>
		<%--<tr>--%>
			<%--<th rowspan="2">--%>
				<%--<table border="0" cellpadding="0" cellspacing="0" width="100%">--%>
					<%--<tr><th rowspan="2">公共科目考核成绩</th><th>非全科专业成绩</th></tr>--%>
					<%--<tr><th>全科专业成绩</th></tr>--%>
				<%--</table>--%>
			<%--</th>--%>

			<%--<td colspan="3"><c:if test="${publicScore.skillScore eq GlobalConstant.PASS}">合格</c:if>--%>
				<%--<c:if test="${publicScore.skillScore eq GlobalConstant.UNPASS}">不合格</c:if>--%>
				<%--&lt;%&ndash;<input type="button" class="btn_blue" onclick="publicScoreDetail('${publicScore.scoreFlow}');" value="详情"/>&nbsp;&ndash;%&gt;--%>
				<%--&#12288;[<a onclick="publicScoreDetail('${publicScore.scoreFlow}');" >详情</a>]&nbsp;--%>
			<%--</td>--%>
		<%--</tr>--%>
		<%--<tr>--%>
			<%--<td colspan="3">${publicScore.theoryScore}</td>--%>
		<%--</tr>--%>
		<%--<tr>--%>
			<%--<th>--%>
				<%--<c:if test="${ (resDoctor.graduationStatusId eq 'ManagerPassed') or (empty resDoctor.graduationStatusId) }">--%>
					<%--基地意见--%>
				<%--</c:if>--%>
				<%--<c:if test="${ (resDoctor.graduationStatusId eq 'GrantCertf') or (resDoctor.graduationStatusId eq 'UnGrantCertf') }">--%>
					<%--省厅意见--%>
				<%--</c:if>--%>
			<%--</th>--%>
			<%--<td colspan="3">${resDoctor.disagreeReason}</td>--%>
		<%--</tr>--%>
		<%--</tbody>--%>
	<%--</table>--%>
</div>
		<c:choose>
			<c:when test="${ f eq '1'}">
				<c:if test="${ (thisYear eq skillScore.scorePhaseId or thisYear eq theoryScore.scorePhaseId) and (empty resDoctor.graduationStatusId) }">
					<div class="main_bd" id="div_table_1" >

						<div align="center" style="margin-top: 20px; margin-bottom:20px;">
							<%--<input type="button" id="submitBtn" class="btn_red" onclick="submit('${resDoctor.doctorFlow}');" value="提交"/>&nbsp;--%>
						</div>
					</div>
				</c:if>
			</c:when>
			<c:when test="${ f eq '0'}">

			</c:when>
			<c:otherwise>

			</c:otherwise>
		</c:choose>

</div>
