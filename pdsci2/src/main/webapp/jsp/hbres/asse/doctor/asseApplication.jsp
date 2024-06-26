<jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
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
	<jsp:param name="jquery_ui_tooltip" value="true"/>
</jsp:include>
<script type="text/javascript"
		src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<style type="text/css">
.boxHome .item:HOVER{background-color: #eee;}
.cur{color:red;}
.grid th{
	border:1px solid #e7e7eb;
}
.grid td{
	border:1px solid #e7e7eb;
}
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

	var tabCourse = $('#showImg');
	tabCourse.on('mouseover',function(){
		$("#changeInfo").show();
	});
	tabCourse.on('mouseout',function(){
		$(".pxxx").hide();
	});
});
function showImages(deptFlow)
{
	var url = "<s:url value='/hbres/asse/showImages?deptFlow='/>"+deptFlow+"&doctorFlow=${doctor.doctorFlow}&applyYear=${param.applyYear}";
	jboxOpen(url, "出科考核表",800,500);
}
	//提交
	function apply2(doctorFlow,applyYear) {
		var recruitFlow = doctorFlow;
		jboxConfirm("数据更新，请勿多次重新更新！" , function() {
			$("#submitBtn2").hide();
			jboxPost("<s:url value='/hbres/temp/reAsseApply?applyYear='/>"+applyYear+"&doctorFlow=" + doctorFlow +"&recruitFlow=" + recruitFlow, null, function (resp) {
				if ("1" == resp) {
					jboxTip("更新成功!");
					jboxLoad("doctorContent", "<s:url value='/hbres/doctor/getAsseApplication?applyYear='/>"+applyYear+"&doctorFlow=" + doctorFlow +"&recruitFlow=" + recruitFlow, true);
				}else	if ("0" == resp) {
					jboxTip("更新失败!");
					$("#submitBtn2").show();
				}else{
					jboxTip(resp);
					$("#submitBtn2").show();
				}
			}, null, false);
		},null);
	}
	//提交
	function apply(doctorFlow,canApply)
	{
		if(canApply == "N"){
			jboxTip("请前往过程管理个人中心将信息补充完整！");
			return;
		}
;		if(!$("#doctorForm").validationEngine("validate")){
			return false;
		}
		if(${empty userResumeExt.certificateUri}){
			jboxTip("请上传毕业证书附件！");
			return;
		}
//		var imgFiles = $('.imgFile');
//		var canApply = 'Y';
//        $(imgFiles).each(function () {
//            var attr = $(this).css('display');
//            if(attr=='inline'){
//                canApply='N';
//                return false;
//            }
//        })
//        if(canApply=='N'){
//            jboxTip("请上传所有出科考核表/出科小结！");
//            return;
//        }
        // alert(canApply);
		var msg="1.请认真核对基本信息；";
		var changeSpeName="${doctor.trainingSpeName}";
		msg+="<br/>2.当前报名专业为“"+changeSpeName+"”；";
		msg+="<br/>3.提交后培训数据完成比例将不再统计更新；"+
		"<br/>4.提交后将无法修改，是否确认提交！";
		var recruitFlow = "${doctor.doctorFlow}";
		jboxConfirm(msg , function() {
			$("#submitBtn").hide();
			jboxPost("<s:url value='/hbres/doctor/asseApply?applyYear=${pdfn:getCurrYear()}&doctorFlow='/>" + doctorFlow +"&recruitFlow=" + recruitFlow, null, function (resp) {
				if ("1" == resp) {
					jboxTip("申请成功!");
					jboxLoad("doctorContent", "<s:url value='/hbres/doctor/getAsseApplication?applyYear=${pdfn:getCurrYear()}&doctorFlow='/>" + doctorFlow +"&recruitFlow=" + recruitFlow, true);
				}else	if ("0" == resp) {
					jboxTip("申请失败!");
					$("#submitBtn").show();
				}else	if ("-1" == resp) {
					jboxTip("已提交申请，请勿重复提交!");
					jboxLoad("doctorContent", "<s:url value='/hbres/doctor/getAsseApplication?applyYear=${pdfn:getCurrYear()}&doctorFlow='/>" + doctorFlow +"&recruitFlow=" + recruitFlow, true);
				}else{
					jboxTip(resp);
					$("#submitBtn").show();
				}
			}, null, false);
		},null);
	}
	//打印
	function printDoc(doctorFlow)
	{
		var url = "<s:url value='/hbres/doctor/printDoc?applyYear=${pdfn:getCurrYear()}&doctorFlow='/>" + doctorFlow +"&recruitFlow=${doctor.doctorFlow}";
		jboxTip("正在准备打印…");
		document.getElementById('printDivIframe').src=url;
	}

	//培训小结
	function guiPeiDetail(recruitFlow)
	{
		var url = "<s:url value='/hbres/asse/recruitDetail?applyYear=${pdfn:getCurrYear()}&recruitFlow='/>"+recruitFlow;
		jboxOpen(url, "各轮转科室出科考核表",1200,600);
	}
	//
	function saveScore(scoreType,exp){
		var score = exp.value;
		var recruitFlow = "${recruitFlow}";
		if(score!="")
		{
			if(isNaN(score)){
				jboxTip("请填写数字!");
				return false;
			}
			if(parseFloat(score)<0||parseFloat(score)>100)
			{
				jboxTip("成绩在0~100之间!");
				return false;
			}
		}
		var url = "<s:url value='/hbres/doctor/saveAsseScore'/>";
		var data = {"score":score,"scoreType":scoreType,"recruitFlow":recruitFlow};
		jboxPost(url, data, function(resp) {
			if(resp == "${GlobalConstant.OPERATE_SUCCESSED}"){
				top.jboxTip(resp);
			}else{
				top.jboxTip(resp);
			}
		}, null, false);
	}
function saveSkillScore(){
	var recruitFlow = "${recruitFlow}";
	var skillScore = $("select[name='skillScore']").find("option:selected").val();
	if('' == skillScore){
		jboxTip("成绩必选是否合格！")
		return;
	}
	var url = "<s:url value='/hbres/doctor/saveAsseScore'/>";
	var data = {"score":skillScore,"scoreType":"skillScore","recruitFlow":recruitFlow};
	jboxPost(url, data, function(resp) {
		if(resp == "${GlobalConstant.OPERATE_SUCCESSED}"){
			top.jboxTip(resp);
		}else{
			top.jboxTip(resp);
		}
	}, null, false);
}
//培训登记手册完成情况
function saveRegisteManua(){
	var recruitFlow="${doctor.doctorFlow}";
	var registeManua = $("select[name='registeManua']").find("option:selected").val();
	if('' == registeManua){
		jboxTip("培训登记手册完成情况必选！")
		return;
	}
	var url = "<s:url value='/hbres/doctor/saveRegisteManua'/>";
	var data = {"registeManua":registeManua};
	jboxPost(url, data, function(resp) {
		if(resp == "${GlobalConstant.OPERATE_SUCCESSED}"){
			top.jboxTip(resp);
		}else{
			top.jboxTip(resp);
		}
	}, null, true);
	var url = "<s:url value='/hbres/temp/saveRegisteManua'/>";
	var data = {"registeManua":registeManua,"recruitFlow":recruitFlow};
	jboxPost(url, data, function(resp) {
	}, null, true);
}
	function uploadFile(resultFlow,fileType,type)
	{
		var fileId=type+resultFlow;
		var uploadId=type+resultFlow+"Upload";
		var ImgId=type+resultFlow+"Img";
		console.log($("#"+fileId));
		$("#"+fileId).off("change").on("change",function(){
			console.log(resultFlow);
			console.log(fileType);
			$.ajaxFileUpload({
				url:"<s:url value='/hbres/doctor/imgUpload'/>?resultFlow="+resultFlow+"&fileType="+fileType,
				secureuri:false,
				fileElementId:fileId,
				dataType: 'json',
				success: function (data){
					data=eval("("+data+")");
					if(data){
						var status=data.status;
						if(status=="${GlobalConstant.OPRE_SUCCESSED_FLAG}"){
							$("#"+uploadId).hide();
							$("#"+ImgId).attr("src",data.url).show();
							jboxTip("上传成功！");
						}else{
							$("#"+fileId).val("");
							jboxTip(data.error);
						};
					};
				},
				error: function (data, status, e){
					jboxTip('${GlobalConstant.UPLOAD_FAIL}');
				},
				complete:function(){
				}
			});
		});
		$("#"+fileId).click();
	}
	function reUploadFile(resultFlow,fileType,type)
	{
		var fileId=type+resultFlow;
		var ImgId=type+resultFlow+"Img";
		$("#"+fileId).off("change").on("change",function(){
			$.ajaxFileUpload({
				url:"<s:url value='/hbres/doctor/imgUpload'/>?resultFlow="+resultFlow+"&fileType="+fileType,
				secureuri:false,
				fileElementId:fileId,
				dataType: 'json',
				success: function (data){
					data=eval("("+data+")");
					if(data){
						var status=data.status;
						if(status=="${GlobalConstant.OPRE_SUCCESSED_FLAG}"){
							$("#"+ImgId).attr("src",data.url).show();
							jboxTip("上传成功！");
						}else{
							$("#"+fileId).val("");
							jboxTip(data.error);
						};
					};
				},
				error: function (data, status, e){
					jboxTip('${GlobalConstant.UPLOAD_FAIL}');
				},
				complete:function(){
				}
			});
		});
		$("#"+fileId).click();
	}
</script>
<div class="main_hd">
	<h2 class="underline">考核申请</h2>
</div>
<div id="docTitle" class="title_tab" style="padding-top: 20px;">
	<ul>
		<script>

			$(document).ready(function(){

				$("#docInfo").removeClass("tab").addClass("tab_select");
				$("#materials").removeClass("tab_select").addClass("tab");
			});
			function showDoctorInfo(exp){
				if($(exp).attr("class") == "tab_select"){
					return;
				}
				$(".doctorInfo").toggle();
				$(exp).removeClass("tab");
				$(exp).addClass("tab_select");
				$(exp).next().removeClass("tab_select");
				$(exp).next().addClass("tab");
			}
			function showMaterials(exp){
				if($(exp).attr("class") == "tab_select"){
					return;
				}
				$(".doctorInfo").toggle();
				$(exp).removeClass("tab");
				$(exp).addClass("tab_select");
				$(exp).prev().removeClass("tab_select");
				$(exp).prev().addClass("tab");
			}
		</script>
		<li id="docInfo" style="font-size: 12px;height: 35px;" class="tab_select" onclick="showDoctorInfo(this);"><a style="padding: 10px;">个人基本信息</a></li>
		<li id="materials" style="font-size: 12px;height: 35px;" class="tab" onclick="showMaterials(this);"><a style="padding: 10px;">结业报考材料</a></li>
	</ul>
</div>
	<div class="search_table doctorInfo" style="margin-top:20px;">
		<c:set var="canApply" value="Y"></c:set>
		<form id="doctorForm">
			<div class="main_bd" id="div_table_0" >
				<h4 >基本信息</h4>
				<table border="0" cellpadding="0" cellspacing="0" class="grid" >
					<colgroup>
						<col width="21%"/>
						<col width="21%"/>
						<col width="21%"/>
						<col width="21%"/>
						<col width="16%"/>
					</colgroup>
					<tbody>
					<tr>
						<th style="color: red" >姓名：</th>
						<td style="color: red" >
							&nbsp;${user.userName}
							<c:if test="${empty user.userName}">
								<span style="color: red" >未填写！</span>
								<c:set var="canApply" value="N"></c:set>
							</c:if>
						</td>
						<th>性别：</th>
						<td>
							<c:if test="${user.sexId eq userSexEnumMan.id}">
								&nbsp;${userSexEnumMan.name}
							</c:if>
							<c:if test="${user.sexId eq userSexEnumWoman.id}">
								&nbsp;${userSexEnumWoman.name}
							</c:if>
							<c:if test="${empty user.sexId}">
								<span style="color: red" >未填写！</span>
								<c:set var="canApply" value="N"></c:set>
							</c:if>
						</td>
						<td rowspan="${needChange eq 'Y'?6:5}" style="text-align: center;">
							<img src="${sysCfgMap['upload_base_url']}/${user.userHeadImg}" id="showImg"
								 style="margin-top: 3px;" width="130px" height="150px"
								 onerror="this.src='<s:url value="/css/skin/up-pic.jpg"/>'"/>
							<br>
							<%--<p title="<div id='changeInfo' class='pxxx' >--%>
							<%--<div class='changeinfo' id='changeInfoContent' style='height: 85px;width:300px;'>--%>
								<%--<i class='icon_up'></i>--%>
								<%--<table border='0' cellpadding='0' cellspacing='0' class='grid' style='width: 100%'>--%>
									<%--<tr><td>1、2寸白底彩色免冠照片，JPG格式，分辨率不小于413*626，大小不超过300K，人像正立。</td></tr>--%>
									<%--<tr><td>2、该照片用于结业证书，请慎重认真上传！</td></tr>--%>
								<%--</table>--%>
							<%--</div></div>">照片要求</p>--%>
						</td>
					</tr>
					<tr>
						<th style="color: red" >证件类型：</th>
						<td style="color: red" >
							<c:forEach items="${certificateTypeEnumList}" var="certificateType">
								<c:if test="${user.cretTypeId eq certificateType.id}">
									&nbsp;${certificateType.name}
								</c:if>
							</c:forEach>
							<c:if test="${empty user.cretTypeId}">
								<span style="color: red" >未填写！</span>
								<c:set var="canApply" value="N"></c:set>
							</c:if>
						</td>
						<th style="color: red" >证&nbsp;件&nbsp;号：</th>
						<td>
							&nbsp;${user.idNo}
							<c:if test="${empty user.idNo}">
								<span style="color: red" >未填写！</span>
								<c:set var="canApply" value="N"></c:set>
							</c:if>
						</td>
					</tr>
					<tr>
						<th>培训基地：</th>
						<td>
							&nbsp;${doctor.orgName}
							<c:if test="${empty doctor.orgName}">
								<span style="color: red" >未填写！</span>
								<c:set var="canApply" value="N"></c:set>
							</c:if>
						</td>
						<th style="color: red" >培训专业：</th>
						<td>
							&nbsp;${doctor.trainingSpeName}
							<c:if test="${empty doctor.trainingSpeName}">
								<span style="color: red" >未填写！</span>
								<c:set var="canApply" value="N"></c:set>
							</c:if>
						</td>
					</tr>
					<tr>
						<th style="color: red" >入培年级：</th>
						<td style="color: red" >
							&nbsp;${doctor.sessionNumber}
							<c:if test="${empty doctor.sessionNumber}">
								<span style="color: red" >未填写！</span>
								<c:set var="canApply" value="N"></c:set>
							</c:if>
						</td>
						<th>结业考核年份：</th>
						<td>
							&nbsp;${doctor.graduationYear}
							<c:if test="${empty doctor.graduationYear}">
								<span style="color: red" >未填写！</span>
								<c:set var="canApply" value="N"></c:set>
							</c:if>
						</td>
					</tr>
					<tr>
						<th style="color: red" >学位：</th>
						<td style="color: red" >
							&nbsp;${user.degreeName}
							<c:if test="${empty user.degreeName}">
								<span style="color: red" >未填写！</span>
								<c:set var="canApply" value="N"></c:set>
							</c:if>
						</td>
						<th>联系方式：</th>
						<td>
							&nbsp;${user.userPhone}
							<c:if test="${empty user.userPhone}">
								<span style="color: red" >未填写！</span>
								<c:set var="canApply" value="N"></c:set>
							</c:if>
						</td>
					</tr>
					</tbody>
				</table>
			</div>
			<div class="main_bd" id="div_table_1" style="padding-top: 20px;" >
				<h4 >报考信息（请仔细核对报考信息，一经核实发现信息作假，将取消考核资格）</h4>
				<table border="0" cellpadding="0" cellspacing="0" class="grid" >
					<colgroup>
						<col width="21%"/>
						<col width="21%"/>
						<col width="21%"/>
						<col width="37%"/>
					</colgroup>
					<tbody>
					<tr>
						<th>学历：</th>
						<td>
							&nbsp;${user.educationName}
							<c:if test="${empty user.educationName}">
								<span style="color: red" >未填写！</span>
								<c:set var="canApply" value="N"></c:set>
							</c:if>
						</td>
						<th style="color: red" >毕业证书编号：</th>
						<td>
							&nbsp;${userResumeExt.certificateCode}
							<c:if test="${empty userResumeExt.certificateCode}">
								<span style="color: red" >未填写！</span>
								<c:set var="canApply" value="N"></c:set>
							</c:if>
						</td>
					</tr>
					<tr>
						<th>培训年限：</th>
						<td>
							<c:if test="${empty doctor.trainingYears}">
								<span style="color: red" >未填写！</span>
								<c:set var="canApply" value="N"></c:set>
							</c:if>
							<c:if test="${'1' eq doctor.trainingYears}">&nbsp;一年</c:if>
							<c:if test="${'2' eq doctor.trainingYears}">&nbsp;两年</c:if>
							<c:if test="${'3' eq doctor.trainingYears}">&nbsp;三年</c:if>
						</td>
						<th style="color: red" >培训起止日期：</th>
						<td>
							&nbsp;${startDate}
							<c:if test="${empty startDate}">
								<span style="color: red" >未填写！</span>
								<c:set var="canApply" value="N"></c:set>
							</c:if>
							~
								${endDate}
							<c:if test="${empty endDate}">
								<span style="color: red" >未填写！</span>
								<c:set var="canApply" value="N"></c:set>
							</c:if>
						</td>
					</tr>
					<tr>
						<th>报考资格材料：</th>
						<td>
							&nbsp;${practicingMap.graduationMaterialName}
							<c:if test="${empty practicingMap.graduationMaterialName}">
								<span style="color: red" >未填写！</span>
								<c:set var="canApply" value="N"></c:set>
							</c:if>
						</td>
						<th style="color: red" >报考资格材料编码：</th>
						<td>
							&nbsp;${practicingMap.graduationMaterialCode}
							<c:if test="${empty practicingMap.graduationMaterialCode}">
								<span style="color: red" >&nbsp;未填写！</span>
								<c:set var="canApply" value="N"></c:set>
							</c:if>
						</td>
					</tr>
					<tr>
						<th>取得资格日期：</th>
						<td colspan="3">
							&nbsp;${practicingMap.graduationMaterialTime}
							<c:if test="${empty practicingMap.graduationMaterialTime}">
								<span style="color: red" >暂无</span>
							</c:if>
						</td>
					</tr>
					<tr>
						<th>执业类型：</th>
						<td>
							&nbsp;${practicingMap.graduationCategoryName}
							<c:if test="${empty practicingMap.graduationCategoryName}">
								<span style="color: red" >未填写！</span>
								<c:set var="canApply" value="N"></c:set>
							</c:if>
						</td>
						<th style="color: red" >执业范围：</th>
						<td>
							&nbsp;${practicingMap.graduationScopeName}
							<c:if test="${empty practicingMap.graduationScopeName}">
								<span style="color: red" >未填写！</span>
								<c:set var="canApply" value="N"></c:set>
							</c:if>
						</td>
					</tr>
					<tr>
						<th>培训登记手册完成情况：</th>
						<td>
							<select <c:if test="${(jsresGraduationApply.auditStatusId eq 'WaitChargePass') or (jsresGraduationApply.auditStatusId eq 'WaitGlobalPass')
							or (jsresGraduationApply.auditStatusId eq 'GlobalPassed')}">disabled="disabled"</c:if>
									class="validate[required]" name="registeManua" onchange="saveRegisteManua();">
								<option value="">--请选择--</option>
								<option value="1" <c:if test="${not empty userResumeExt.registeManua and userResumeExt.registeManua eq GlobalConstant.PASS}">selected="selected"</c:if>>已完成</option>
								<option value="0" <c:if test="${not empty userResumeExt.registeManua and userResumeExt.registeManua eq GlobalConstant.UNPASS}">selected="selected"</c:if>>未完成</option>
							</select>
						</td>
						<th style="color: red" >实际轮转时间（月）：</th>
						<td>

							&nbsp;<fmt:formatNumber type="number" value="${allMonth}" maxFractionDigits="2"/>
							<c:if test="${empty allMonth}">
								<span style="color: red" >未填写！</span>
								<c:set var="canApply" value="N"></c:set>
							</c:if>
						</td>
					</tr>
					<%--<tr>--%>
						<%--<th style="color: red" >数据填写比例：</th>--%>
						<%--<td>--%>
							<%--&nbsp;${avgBiMap.avgComplete}--%>
							<%--<c:if test="${empty avgBiMap.avgComplete}">--%>
								<%--0--%>
							<%--</c:if>%--%>
						<%--</td>--%>
						<%--<th style="color: red" >数据审核比例：</th>--%>
						<%--<td>--%>
							<%--&nbsp;${avgBiMap.avgAudit}--%>
							<%--<c:if test="${empty avgBiMap.avgAudit}">--%>
								<%--0--%>
							<%--</c:if>%--%>
						<%--</td>--%>
					<%--</tr>--%>
					</tbody>
				</table>
				<%--<table cellpadding="0" cellspacing="0" class="grid" >--%>
					<%--<colgroup>--%>
						<%--<col width="21%"/>--%>
						<%--<col width="79%"/>--%>
					<%--</colgroup>--%>
					<%--<tr>--%>
						<%--<th style="text-align: center;" >结业考核资格审查意见</th>--%>
						<%--<td>${jsresGraduationApply.auditStatusName}</td>--%>
					<%--</tr>--%>
				<%--</table>--%>
				<table border="0" cellpadding="0" cellspacing="0"style="border-top:0px; " class="grid" >
					<colgroup>
						<col width="21%"/>
						<col width="79%"/>
					</colgroup>
					<tbody>
					<c:if test="${not empty jsresGraduationApply.localAuditStatusId}">
						<tr>
							<th style="text-align: center;">
								基地意见
							</th>
							<td colspan="6">${jsresGraduationApply.localAuditStatusName}
								<c:if test="${not empty jsresGraduationApply.localReason}">
									(${jsresGraduationApply.localReason})
								</c:if>
							</td>
						</tr>
					</c:if>
					<c:if test="${not empty jsresGraduationApply.cityAuditStatusId}">
						<tr>
							<th style="text-align: center;">
								市局意见
							</th>
							<td colspan="6">${jsresGraduationApply.cityAuditStatusName}
								<c:if test="${not empty jsresGraduationApply.cityReason}">
									(${jsresGraduationApply.cityReason})
								</c:if>
							</td>
						</tr>
					</c:if>
					<c:if test="${not empty jsresGraduationApply.globalAuditStatusId}">
						<tr>
							<th style="text-align: center;">
								省厅意见
							</th>
							<td colspan="6">${jsresGraduationApply.globalAuditStatusName}
								<c:if test="${not empty jsresGraduationApply.globalReason}">
									(${jsresGraduationApply.globalReason})
								</c:if>
							</td>
						</tr>
					</c:if>
					</tbody>
				</table>
			</div>
		</form>
		<div style="text-align: center;padding-top: 20px;padding-bottom: 50px;">
			<c:set var="canUpload" value="N"></c:set>
			<c:if test="${'Y'eq apply}">
				<c:set var="canUpload" value="Y"></c:set>
				<input type="button" class="btn_green" id="submitBtn" onclick="apply('${doctor.doctorFlow}','${canApply}');" value="提交申请"/>&#12288;&#12288;
			</c:if>
			<c:if test="${pdfn:getCurrYear() eq doctor.graduationYear and  !((jsresGraduationApply.auditStatusId eq 'WaitChargePass') or (jsresGraduationApply.auditStatusId eq 'WaitGlobalPass')
							or (jsresGraduationApply.auditStatusId eq 'GlobalPassed')) and not empty jsresGraduationApply.auditStatusId}">
				<c:set var="canUpload" value="Y"></c:set>
				<input type="button" class="btn_green" id="submitBtn2" onclick="apply2('${doctor.doctorFlow}','${jsresGraduationApply.applyYear}');" value="更新提交数据信息"/>
			</c:if>
			<%--<c:if test="${jsresGraduationApply.auditStatusId eq jsResAsseAuditListEnumGlobalPassed.id}">--%>
				<input type="button" class="btn_green" onclick="printDoc('${doctor.doctorFlow}');" value="打&#12288;印"/>
			<%--</c:if>--%>
		</div>
	</div>
	<div class="main_bd doctorInfo" style="display: none;">
		<div class="div_table">
			<h4>各科室轮转情况(<font color="red">请完成【出科考核表】与【出科小结】附件上传</font>)</h4>
			<div style="overflow:auto;max-width: 910px;max-height: 550px;border: medium;margin: 0 0 0 0;">
				<table cellpadding="0" cellspacing="0" class="grid" style="width: 100%;padding:0px;margin: 0px;">
						<tr>
							<th style="width: 100px;min-width: 100px;max-width: 100px;">轮转类型</th>
							<th style="width: 100px;min-width: 100px;max-width: 100px;">标准科室</th>
							<th style="width: 100px;min-width: 100px;max-width: 100px;">要求时间(月)/实际时间(月)</th>
							<th style="width: 100px;min-width: 100px;max-width: 100px;">轮转科室</th>
							<th style="width: 300px;min-width: 200px;max-width: 300px;">轮转时间</th>
							<th style="width: 200px;min-width: 200px;max-width: 200px;">出科考核表/出科小结</th>
							<th style="width: 60px;min-width: 60px;max-width: 60px;">总比例</th>
							<th style="width: 60px;min-width: 60px;max-width: 60px;">补填比例</th>
							<th style="width: 60px;min-width: 60px;max-width: 60px;">审核比例</th>
						</tr>

						<c:set value="" var="lastGroupFlow"></c:set>
						<c:set value="" var="lastDeptFlow"></c:set>
						<c:set value="0" var="allSchMonth"></c:set>
						<c:set value="0" var="allRealMonth"></c:set>
						<c:forEach items="${groupList}" var="group" varStatus="groupStatus">
							<c:forEach items="${rotationDeptMap[group.groupFlow]}" var="dept" varStatus="deptStatus">
								<c:set value="${allSchMonth+0+dept.schMonth}" var="allSchMonth"></c:set>

								<c:set var="resultKey" value="${group.groupFlow}${dept.standardDeptId}"/>
								<c:set value="${allRealMonth+0+(empty realMonthMap[resultKey] ? 0 : realMonthMap[resultKey])}" var="allRealMonth"></c:set>

								<c:if test="${not empty resultMap[resultKey]}">
									<c:forEach items="${resultMap[resultKey]}" var="result" varStatus="resultStatus">
										<c:set var="bi" value="${biMap[result.resultFlow]}"></c:set>
										<c:choose>
											<c:when test="${!(lastGroupFlow eq group.groupFlow) and
								!(lastDeptFlow eq dept.recordFlow) and resultStatus.first
								}">
												<tr>
													<td rowspan="${(groupRowSpan[group.groupFlow]-fn:length(rotationDeptMap[group.groupFlow]))<=0?0:(groupRowSpan[group.groupFlow]-fn:length(rotationDeptMap[group.groupFlow]))}">
														<c:if test="${rotation.isStage eq GlobalConstant.FLAG_Y}">
															${group.schStageName}：
														</c:if>
															${group.groupName}【${dept.isRequired == GlobalConstant.FLAG_N?"选科":"必轮"}】
														<br>(轮转时间：${group.schMonth}月
														<c:if test="${group.isRequired eq GlobalConstant.FLAG_N}">
															&#12288;${group.selTypeName}：${group.deptNum}<c:if test="${group.selTypeId eq schSelTypeEnumFree.id}">~${group.maxDeptNum}</c:if>
														</c:if>)
													</td>
													<td rowspan="${deptRowSpan[resultKey]}">
															${dept.standardDeptName}
													</td>
													<td rowspan="${deptRowSpan[resultKey]}">${dept.schMonth}/${empty realMonthMap[resultKey] ? 0 : realMonthMap[resultKey]}</td>
													<td>${result.schDeptName}</td>
													<td>${result.schStartDate}~${result.schEndDate}</td>
													<td>
														<c:set var="evalFile" value="${evaluationFileMap[result.resultFlow]}"></c:set>
														<c:set var="summarFile" value="${summaryFileMap[result.resultFlow]}"></c:set>

														<div style="margin-top:10px; ">
															<c:if test="${canUpload eq 'Y'}">
																<input id="evalFile${result.resultFlow}" style="display: none;" type="file" name="checkFile" class="validate[required]"/>
																<c:if test="${empty evalFile.filePath}">
																	<img class="imgFile" width="40px" id="evalFile${result.resultFlow}Upload" height="40px" title="上传出科考核表" onclick="uploadFile('${result.resultFlow}','AfterEvaluationFile','evalFile');"  src="<s:url value='/jsp/hbres/images/upload.png'/>">&nbsp;
																	<img id="evalFile${result.resultFlow}Img"  title="重新上传" onclick="reUploadFile('${result.resultFlow}','AfterEvaluationFile','evalFile');" style="display: none;" width="80px" height="80px" >&nbsp;
																</c:if>
																<c:if test="${not empty evalFile.filePath}">
																	<img id="evalFile${result.resultFlow}Img" title="重新上传" onclick="reUploadFile('${result.resultFlow}','AfterEvaluationFile','evalFile');" width="80px" height="80px"  src="${sysCfgMap['upload_base_url']}/${evalFile.filePath}">&nbsp;
																</c:if>

																<input id="summarFile${result.resultFlow}" style="display: none;" type="file" name="checkFile" class="validate[required]"/>
																<c:if test="${empty summarFile.filePath}">
																	<img class="imgFile" width="40px" id="summarFile${result.resultFlow}Upload" height="40px" title="上传出科小结表" onclick="uploadFile('${result.resultFlow}','AfterSummaryFile','summarFile');"  src="<s:url value='/jsp/hbres/images/upload.png'/>">&nbsp;
																	<img id="summarFile${result.resultFlow}Img"  title="重新上传" onclick="reUploadFile('${result.resultFlow}','AfterSummaryFile','summarFile');" style="display: none;" width="80px" height="80px" >&nbsp;
																</c:if>
																<c:if test="${not empty summarFile.filePath}">
																	<img id="summarFile${result.resultFlow}Img" title="重新上传" onclick="reUploadFile('${result.resultFlow}','AfterSummaryFile','summarFile');" width="80px" height="80px"  src="${sysCfgMap['upload_base_url']}/${summarFile.filePath}">&nbsp;
																</c:if>
															</c:if>
															<c:if test="${canUpload ne 'Y'}">
																<c:if test="${not empty evalFile.filePath}">
																	<a href="${sysCfgMap['upload_base_url']}/${evalFile.filePath}" target="_blank"><img width="80px" height="80px"  src="${sysCfgMap['upload_base_url']}/${evalFile.filePath}"></a>&nbsp;
																</c:if>
																<c:if test="${not empty summarFile.filePath}">
																	<a href="${sysCfgMap['upload_base_url']}/${summarFile.filePath}" target="_blank"><img width="80px" height="80px"  src="${sysCfgMap['upload_base_url']}/${summarFile.filePath}"></a>&nbsp;
																</c:if>
															</c:if>
														</div>
													</td>
													<td>${empty bi.completeBi ? 0: bi.completeBi}${bi.completeBi=='-' ? '':'%'}</td>
													<td>${empty bi.outCompleteBi ? 0: bi.outCompleteBi}${bi.outCompleteBi=='-' ? '':'%'}</td>
													<td>${empty bi.auditBi ? 0:bi.auditBi}${bi.auditBi=='-' ? '':'%'}</td>
												</tr>
											</c:when>
											<c:when test="${(lastGroupFlow eq group.groupFlow) and
								!(lastDeptFlow eq dept.recordFlow) and resultStatus.first
								}">
												<tr>
													<td rowspan="${deptRowSpan[resultKey]}">
															${dept.standardDeptName}
													</td>
													<td rowspan="${deptRowSpan[resultKey]}">${dept.schMonth}/${empty realMonthMap[resultKey] ? 0 : realMonthMap[resultKey]}</td>
													<td>${result.schDeptName}</td>
													<td>${result.schStartDate}~${result.schEndDate}</td>
													<td>

														<c:set var="evalFile" value="${evaluationFileMap[result.resultFlow]}"></c:set>
														<c:set var="summarFile" value="${summaryFileMap[result.resultFlow]}"></c:set>

														<div style="margin-top:10px; ">
															<c:if test="${canUpload eq 'Y'}">
																<input id="evalFile${result.resultFlow}" style="display: none;" type="file" name="checkFile" class="validate[required]"/>
																<c:if test="${empty evalFile.filePath}">
																	<img class="imgFile" width="40px" id="evalFile${result.resultFlow}Upload" height="40px" title="上传出科考核表" onclick="uploadFile('${result.resultFlow}','AfterEvaluationFile','evalFile');"  src="<s:url value='/jsp/hbres/images/upload.png'/>">&nbsp;
																	<img id="evalFile${result.resultFlow}Img"  title="重新上传" onclick="reUploadFile('${result.resultFlow}','AfterEvaluationFile','evalFile');" style="display: none;" width="80px" height="80px" >&nbsp;
																</c:if>
																<c:if test="${not empty evalFile.filePath}">
																	<img id="evalFile${result.resultFlow}Img" title="重新上传" onclick="reUploadFile('${result.resultFlow}','AfterEvaluationFile','evalFile');" width="80px" height="80px"  src="${sysCfgMap['upload_base_url']}/${evalFile.filePath}">&nbsp;
																</c:if>

																<input id="summarFile${result.resultFlow}" style="display: none;" type="file" name="checkFile" class="validate[required]"/>
																<c:if test="${empty summarFile.filePath}">
																	<img class="imgFile" width="40px" id="summarFile${result.resultFlow}Upload" height="40px" title="上传出科小结表" onclick="uploadFile('${result.resultFlow}','AfterSummaryFile','summarFile');"  src="<s:url value='/jsp/hbres/images/upload.png'/>">&nbsp;
																	<img id="summarFile${result.resultFlow}Img"  title="重新上传" onclick="reUploadFile('${result.resultFlow}','AfterSummaryFile','summarFile');" style="display: none;" width="80px" height="80px" >&nbsp;
																</c:if>
																<c:if test="${not empty summarFile.filePath}">
																	<img id="summarFile${result.resultFlow}Img" title="重新上传" onclick="reUploadFile('${result.resultFlow}','AfterSummaryFile','summarFile');" width="80px" height="80px"  src="${sysCfgMap['upload_base_url']}/${summarFile.filePath}">&nbsp;
																</c:if>
															</c:if>
															<c:if test="${canUpload ne 'Y'}">
																<c:if test="${not empty evalFile.filePath}">
																	<a href="${sysCfgMap['upload_base_url']}/${evalFile.filePath}" target="_blank"><img width="80px" height="80px"  src="${sysCfgMap['upload_base_url']}/${evalFile.filePath}"></a>&nbsp;
																</c:if>
																<c:if test="${not empty summarFile.filePath}">
																	<a href="${sysCfgMap['upload_base_url']}/${summarFile.filePath}" target="_blank"><img width="80px" height="80px"  src="${sysCfgMap['upload_base_url']}/${summarFile.filePath}"></a>&nbsp;
																</c:if>
															</c:if>
														</div>
													</td>
													<td>${empty bi.completeBi ? 0: bi.completeBi}${bi.completeBi=='-' ? '':'%'}</td>
													<td>${empty bi.outCompleteBi ? 0: bi.outCompleteBi}${bi.outCompleteBi=='-' ? '':'%'}</td>
													<td>${empty bi.auditBi ? 0:bi.auditBi}${bi.auditBi=='-' ? '':'%'}</td>
												</tr>
											</c:when>
											<c:when test="${(lastGroupFlow eq group.groupFlow) and
								(lastDeptFlow eq dept.recordFlow) and !(resultStatus.first)}">
												<tr>
													<td>${result.schDeptName}</td>
													<td>${result.schStartDate}~${result.schEndDate}</td>
													<td>
														<c:set var="evalFile" value="${evaluationFileMap[result.resultFlow]}"></c:set>
														<c:set var="summarFile" value="${summaryFileMap[result.resultFlow]}"></c:set>


														<div style="margin-top:10px; ">
															<c:if test="${canUpload eq 'Y'}">
																<input id="evalFile${result.resultFlow}" style="display: none;" type="file" name="checkFile" class="validate[required]"/>
																<c:if test="${empty evalFile.filePath}">
																	<img class="imgFile" width="40px" id="evalFile${result.resultFlow}Upload" height="40px" title="上传出科考核表" onclick="uploadFile('${result.resultFlow}','AfterEvaluationFile','evalFile');"  src="<s:url value='/jsp/hbres/images/upload.png'/>">&nbsp;
																	<img id="evalFile${result.resultFlow}Img"  title="重新上传" onclick="reUploadFile('${result.resultFlow}','AfterEvaluationFile','evalFile');" style="display: none;" width="80px" height="80px" >&nbsp;
																</c:if>
																<c:if test="${not empty evalFile.filePath}">
																	<img id="evalFile${result.resultFlow}Img" title="重新上传" onclick="reUploadFile('${result.resultFlow}','AfterEvaluationFile','evalFile');" width="80px" height="80px"  src="${sysCfgMap['upload_base_url']}/${evalFile.filePath}">&nbsp;
																</c:if>

																<input id="summarFile${result.resultFlow}" style="display: none;" type="file" name="checkFile" class="validate[required]"/>
																<c:if test="${empty summarFile.filePath}">
																	<img class="imgFile" width="40px" id="summarFile${result.resultFlow}Upload" height="40px" title="上传出科小结表" onclick="uploadFile('${result.resultFlow}','AfterSummaryFile','summarFile');"  src="<s:url value='/jsp/hbres/images/upload.png'/>">&nbsp;
																	<img id="summarFile${result.resultFlow}Img"  title="重新上传" onclick="reUploadFile('${result.resultFlow}','AfterSummaryFile','summarFile');" style="display: none;" width="80px" height="80px" >&nbsp;
																</c:if>
																<c:if test="${not empty summarFile.filePath}">
																	<img id="summarFile${result.resultFlow}Img" title="重新上传" onclick="reUploadFile('${result.resultFlow}','AfterSummaryFile','summarFile');" width="80px" height="80px"  src="${sysCfgMap['upload_base_url']}/${summarFile.filePath}">&nbsp;
																</c:if>
															</c:if>
															<c:if test="${canUpload ne 'Y'}">
																<c:if test="${not empty evalFile.filePath}">
																	<a href="${sysCfgMap['upload_base_url']}/${evalFile.filePath}" target="_blank"><img width="80px" height="80px"  src="${sysCfgMap['upload_base_url']}/${evalFile.filePath}"></a>&nbsp;
																</c:if>
																<c:if test="${not empty summarFile.filePath}">
																	<a href="${sysCfgMap['upload_base_url']}/${summarFile.filePath}" target="_blank"><img width="80px" height="80px"  src="${sysCfgMap['upload_base_url']}/${summarFile.filePath}"></a>&nbsp;
																</c:if>
															</c:if>
														</div>
													</td>
													<td>${empty bi.completeBi ? 0: bi.completeBi}${bi.completeBi=='-' ? '':'%'}</td>
													<td>${empty bi.outCompleteBi ? 0: bi.outCompleteBi}${bi.outCompleteBi=='-' ? '':'%'}</td>
													<td>${empty bi.auditBi ? 0:bi.auditBi}${bi.auditBi=='-' ? '':'%'}</td>

												</tr>
											</c:when>
										</c:choose>
										<c:set value="${group.groupFlow}" var="lastGroupFlow"></c:set>
										<c:set value="${dept.recordFlow}" var="lastDeptFlow"></c:set>
									</c:forEach>
								</c:if>
								<c:if test="${empty resultMap[resultKey]}">
									<tr>
										<c:if test="${deptStatus.first}">
											<td rowspan="${(groupRowSpan[group.groupFlow]-fn:length(rotationDeptMap[group.groupFlow]))<=0?0:(groupRowSpan[group.groupFlow]-fn:length(rotationDeptMap[group.groupFlow]))}">
											<c:if test="${rotation.isStage eq GlobalConstant.FLAG_Y}">
													${group.schStageName}：
												</c:if>
													${group.groupName}【${dept.isRequired == GlobalConstant.FLAG_N?"选科":"必轮"}】
												<br>(轮转时间：${group.schMonth}月
												<c:if test="${group.isRequired eq GlobalConstant.FLAG_N}">
													&#12288;${group.selTypeName}：${group.deptNum}<c:if test="${group.selTypeId eq schSelTypeEnumFree.id}">~${group.maxDeptNum}</c:if>
												</c:if>)
											</td>
										</c:if>
										<td>
												${dept.standardDeptName}
										</td>
										<td>${dept.schMonth}/0</td>
										<td></td>
										<td></td>
										<td>

										</td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
								</c:if>
								<c:set value="${group.groupFlow}" var="lastGroupFlow"></c:set>
								<c:set value="${dept.recordFlow}" var="lastDeptFlow"></c:set>
							</c:forEach>
						</c:forEach>
						<tr>
							<td colspan="2">合计时间</td>
							<td colspan="">
								<fmt:formatNumber type="number" value="${allSchMonth}" maxFractionDigits="2"/>
								/<fmt:formatNumber type="number" value="${allRealMonth}" maxFractionDigits="2"/>
							</td>
							<td colspan="3"><%--平均比例--%></td>
							<td ><%--${empty avgBiMap.avgComplete?0:avgBiMap.avgComplete}%--%></td>
							<td ><%--${empty avgBiMap.avgOutComplete ? 0:avgBiMap.avgOutComplete}%--%></td>
							<td ><%--${empty avgBiMap.avgAudit?0:avgBiMap.avgAudit}%--%></td>
						</tr>
					</table>
			</div>
		</div>
		<div class="div_table" style="padding-top: 20px;">
				<h4>毕业证书</h4>
				<div style="width: 100%;height: 400px">
					<c:if test="${not empty userResumeExt.certificateUri}">
						<a href="${sysCfgMap['upload_base_url']}/${userResumeExt.certificateUri}" target="_blank">
							<img src="${sysCfgMap['upload_base_url']}/${userResumeExt.certificateUri}" style="width: 100%;height: 100%;">
						</a>
					</c:if>
					<c:if test="${empty userResumeExt.certificateUri}">
						暂未上传
					</c:if>
				</div>
			</div>
		<div class="div_table"  style="padding-top: 20px;">
				<h4>执业医师资格证书</h4>
				<div style="width: 100%;height: 400px">
					<c:if test="${'暂无' ne practicingMap.graduationMaterialUri}">
						<c:if test="${not empty practicingMap.graduationMaterialUri}">
							<a href="${sysCfgMap['upload_base_url']}/${practicingMap.graduationMaterialUri}" target="_blank">
								<img src="${sysCfgMap['upload_base_url']}/${practicingMap.graduationMaterialUri}" style="width: 100%;height: 100%;">
							</a>
						</c:if>
						<c:if test="${empty practicingMap.graduationMaterialUri}">
							暂未上传
						</c:if>
					</c:if>
					<c:if test="${'暂无' eq practicingMap.graduationMaterialUri}">
						暂未上传
					</c:if>
				</div>
			</div>
	</div>
<iframe style="display:none" id="printDivIframe" name="printDivIframe">
	<div id="printDoc">

	</div>
</iframe>