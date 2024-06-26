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
	<jsp:param name="jquery_ui_tooltip" value="true"/>
</jsp:include>
<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/jsres/css/exam.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
<link rel="stylesheet" type="text/css" href="<s:url value='/js/viewer/viewer.min.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
<script type="text/javascript" src="<s:url value='/js/viewer/viewer-jquery.min.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#toptab li:first").removeClass("tab").addClass("tab_select")
			.attr("style","background-color:#44b549;")
			.find("a").attr("style","color: white;");
	$("#toptab .tabli").click(function(){
		$("#toptab .tab_select").addClass("tab").attr("style","")
				.find("a").attr("style","");
		$("#toptab .tab_select").removeClass("tab_select");
		$(this).removeClass("tab");
		$(this).addClass("tab_select").attr("style","background-color:#44b549;")
										.find("a").attr("style","color: white;");
	});
	
	//加载基本信息或培训信息
	var recruitFlow = "${param.recruitFlow}";
	if(recruitFlow != ""){
		$("#li_"+ recruitFlow).click();
	}else{
		$("#toptab .tab_select a").click();
	}


	<c:if test="${empty recruitList or recruitList.get(recruitList.size()-1).doctorStatusId eq '21'}">
		$("#addRecruitBtn").show();
	</c:if>
});

var flag = 'N';
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
    if ('${file.filePath}') {
        flag = 'Y';
    }
});

$(function () {
    $('.ratateImg').viewer();

    var score = '${publicScore.skillScore}';
    if(score != '') {
        var td = document.getElementById("mouseOver");
        var div = document.getElementById("showMouseOver");
        td.onmouseover = function () {
            div.style.display = 'block';
        }
        td.onmouseout = function () {
            div.style.display = 'none';
        }
        var td2 = document.getElementById("mouseOver2");
        var div2 = document.getElementById("showMouseOver2");
        td2.onmouseover = function () {
            div2.style.display = 'block';
        }
        td2.onmouseout = function () {
            div2.style.display = 'none';
        }
    }
})

//证书申请
function doctoCertificateApply(applyFlow) {
    jboxConfirm("证书申请确认后无法继续填写数据！",function () {
        var url = "<s:url value='/jsres/doctor/saveCertificateApply?applyFlow='/>" + applyFlow;
        jboxPost(url,null,function(resp){
            if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                jboxTip("申请成功!");
                setTimeout(function(){
                    jboxLoad("doctorContent", "<s:url value='/jsres/doctor/certificateApply'/>" , true);
                },1500);
            }
        });
    });
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
    var url = "<s:url value='/jsres/doctor/saveAsseScore'/>";
    var data = {"score":score,"scoreType":scoreType,"recruitFlow":recruitFlow};
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
    var recruitFlow = "${recruitFlow}";
    var registeManua = $("select[name='registeManua']").find("option:selected").val();
    if('' == registeManua){
        jboxTip("培训登记手册完成情况必选！")
        return;
    }
    var url = "<s:url value='/jsres/doctor/saveRegisteManua'/>";
    var data = {"registeManua":registeManua,"recruitFlow":recruitFlow};
    jboxPost(url, data, function(resp) {
        if(resp == "${GlobalConstant.OPERATE_SUCCESSED}"){
            top.jboxTip(resp);
        }else{
            top.jboxTip(resp);
        }
    }, null, true);
    var url = "<s:url value='/jsres/temp/saveRegisteManua'/>";
    var data = {"registeManua":registeManua,"recruitFlow":recruitFlow};
    jboxPost(url, data, function(resp) {
    }, null, true);
}
//选择文件
function chooseFile(){
    return $("#file").click();
}
$(function(){
    $("#file").live("change",function(){
        uploadFile();
    });
});
function uploadFile(){
    if(false == $("#fileForm").validationEngine("validate")){
        return false;
    }
    jboxStartLoading();
    var checkResult = checkFile($("#file")[0]);
    if(!checkResult){
        jboxEndLoading();
        return false;
    }
    var url = "<s:url value='/jsres/doctor/uploadApplicationFile'/>";
    jboxSubmit($("#fileForm"),url,function(resp){
        if("${GlobalConstant.UPLOAD_FAIL}" != resp){
            jboxEndLoading();
            var index = resp.indexOf("/");
            if(index != -1){
                returnUrl(resp);
            }else{//验证文件信息
                jboxInfo(resp);
            }
        }
    }, null, false);
}
/**
 * 检查文件格式
 */
function checkFile(file){
    var filePath = file.value;
    var types = "${sysCfgMap['inx_image_support_suffix']}".split(",");
    var regStr = "/";
    for(var i = 0 ;i<types.length;i++){
        if(types[i]){
            if(i==(types.length-1)){
                regStr = regStr+"\\"+types[i]+'$';
            }else{
                regStr = regStr+"\\"+types[i]+'$|';
            }
        }
    }
    regStr = regStr+'/i';
    regStr = eval(regStr);
    if($.trim(filePath)!="" && !regStr.test(filePath)){
        file.value = "";
        jboxTip("请上传&nbsp;${sysCfgMap['inx_image_support_suffix']}格式的图片");
        return false;
    }else{
        return true;
    }
}
function returnUrl(filePath){
    flag = "Y";
    $("a[name='chooseFile']").text("重新上传");
//	$("#asseValue").val(filePath);
    var filePath = "${sysCfgMap['upload_base_url']}" +"/" + filePath;
    $("#filePath").val(filePath);
    var html="<a href='"+filePath+"' target='_blank'>" +
        "<img src='"+filePath+"' style='width: 100%;height: 90%;'>	</a>"+
        '<a style="margin-left: 110px;"  href="javascript:chooseFile();" >重新上传</a>';
    $("#publicImg").html(html);
    $("#fileSpan").show();
    $("#fileDelSpan").show();
    $("#fileSpan").find("a").attr('href',filePath);
}
function delFile(type) {
    var recruitFlow = "${recruitFlow}";
    jboxConfirm("确认删除？" , function(){
        var url = "<s:url value='/jsres/doctor/delScoreFile'/>";
        var data = {"recruitFlow":recruitFlow};
        jboxPost(url, data, function(resp) {
            if(resp == "${GlobalConstant.OPERATE_SUCCESSED}"){
                top.jboxTip(resp);
                $("#filePath").val("");
                $("#publicImg").html("暂未上传");
                $("#fileDelSpan").hide();
                $("#fileSpan").hide();
                $("a[name='chooseFile']").text("上传");
            }else{
                top.jboxTip(resp);
            }
        }, null, true);
    });
}

</script>
<div id="doctorContent">
<div class="main_bd" id="div_table" >
	<span id="noPass" style="display:none;color: red;padding: 20px;"></span>
	<div >
		<div class="search_table doctorInfo" style="margin-top:15px;">
			<form id="fileForm" method="post" enctype="multipart/form-data">
				<input type="text" name="productFlow" value="${recruitFlow}" style="display: none;"/>
				<input type="file" id="file" name="file" class="validate[required]" style="display: none;"/>
			</form>
			<c:set var="canApply" value="Y"></c:set>
			<form id="doctorForm" style="position:relative;">
				<div class="main_bd" id="div_table_0" >
					<h4 >基本信息</h4>
					<table border="0" cellpadding="0" cellspacing="0" class="base_info basc_jb" >
						<colgroup>
							<%--						<col width="21%"/>--%>
							<%--						<col width="21%"/>--%>
							<%--						<col width="21%"/>--%>
							<%--						<col width="21%-130"/>--%>
							<col width="190" />
							<col width="189" />
							<col width="189" />
							<col />
							<col width="130"/>
						</colgroup>
						<tbody>
						<tr>
							<th>姓名：</th>
							<td>
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
							<td rowspan="${needChange eq 'Y'?7:6}" style="text-align: center;padding: 0">
								<img src="${sysCfgMap['upload_base_url']}/${user.userHeadImg}" id="showImg"
									 width="130px" height="196px"
									 onerror="this.src='<s:url value="/css/skin/up-pic.jpg"/>'"/>
								<p style="line-height: 48px" title="<div id='changeInfo' class='pxxx' >
							<div class='changeinfo' id='changeInfoContent' style='height: 65px;width:300px;'>
								<i class='icon_up'></i>
								<table border='0' cellpadding='0' cellspacing='0' class='grid' style='width: 100%'>
									1、2寸白底彩色免冠照片，JPG格式，分辨率不小于413*626，大小不超过300K，人像正立。
									<br>2、该照片用于结业证书，请慎重认真上传！
								</table>
							</div></div>">照片要求</p>
							</td>
						</tr>
						<tr>
							<th>证件类型：</th>
							<td>
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
							<th>证&nbsp;件&nbsp;号：</th>
							<td>
								&nbsp;${user.idNo}
								<c:if test="${empty user.idNo}">
									<span style="color: red" >未填写！</span>
									<c:set var="canApply" value="N"></c:set>
								</c:if>
							</td>
						</tr>
						<tr>
							<th>年级：</th>
							<td>
								&nbsp;${doctorRecruit.sessionNumber}
								<c:if test="${empty doctorRecruit.sessionNumber}">
									<span style="color: red">未填写！</span>
									<c:set var="canApply" value="N"></c:set>
								</c:if>
							</td>
							<th>结业年份：</th>
							<td>
								&nbsp;${doctorRecruit.graduationYear}
								<c:if test="${empty doctorRecruit.graduationYear}">
									<span style="color: red">未填写！</span>
									<c:set var="canApply" value="N"></c:set>
								</c:if>
							</td>
						</tr>
						<tr>
							<th>人员类型：</th>
							<td>
								${resDoctor.doctorTypeName}
								<c:if test="${empty resDoctor.doctorTypeName}">
									<span style="color: red">未填写！</span>
									<c:set var="canApply" value="N"></c:set>
								</c:if>
							</td>
							<th>培训基地：</th>
							<td>
								${doctorRecruit.orgName}
								<c:if test="${empty doctorRecruit.orgName}">
									<span style="color: red">未填写！</span>
									<c:set var="canApply" value="N"></c:set>
								</c:if>
							</td>
						</tr>
						<tr>
							<th>培训类别：</th>
							<td>
								&nbsp;${resDoctor.trainingTypeName}
								<c:if test="${empty resDoctor.trainingTypeName}">
									<span style="color: red" >未填写！</span>
									<c:set var="canApply" value="N"></c:set>
								</c:if>
							</td>
							<th>培训专业：</th>
							<td>
								&nbsp;${doctorRecruit.speName}
								<c:if test="${empty doctorRecruit.speName}">
									<span style="color: red" >未填写！</span>
									<c:set var="canApply" value="N"></c:set>
								</c:if>
							</td>
						</tr>
						<tr>
							<th>学位：</th>
							<td>
								<c:if test="${user.educationId eq '191'}">&nbsp;无学位</c:if>
								<c:if test="${user.educationId ne '191'}">
									<c:choose>
										<c:when test="${ not empty userResumeExt.doctorDegreeName}">
											&nbsp;${userResumeExt.doctorDegreeName}
											<c:if test="${empty userResumeExt.doctorDegreeName}">
												<span style="color: red" >未填写！</span>
												<c:set var="canApply" value="N"></c:set>
											</c:if>
										</c:when>
										<c:when test="${ not empty userResumeExt.masterDegreeName}">
											&nbsp;${userResumeExt.masterDegreeName}
											<c:if test="${empty userResumeExt.masterDegreeName}">
												<span style="color: red" >未填写！</span>
												<c:set var="canApply" value="N"></c:set>
											</c:if>
										</c:when>
										<c:otherwise>
											&nbsp;${userResumeExt.degreeName}
											<c:if test="${empty userResumeExt.degreeName}">
												<span style="color: red" >未填写！</span>
												<c:set var="canApply" value="N"></c:set>
											</c:if>
										</c:otherwise>
									</c:choose>
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
				<%--<div class="main_bd" id="div_table_1" style="padding-top: 15px;" >--%>
					<%--<h4 >报考信息</h4>--%>
					<%--<table border="0" cellpadding="0" cellspacing="0" class="base_info basc_bk" >--%>
						<%--<colgroup>--%>
							<%--&lt;%&ndash;						<col width="21%"/>&ndash;%&gt;--%>
							<%--&lt;%&ndash;						<col width="21%"/>&ndash;%&gt;--%>
							<%--&lt;%&ndash;						<col width="21%"/>&ndash;%&gt;--%>
							<%--<col width="190" />--%>
							<%--<col width="189" />--%>
							<%--<col width="189" />--%>
							<%--<col />--%>
							<%--&lt;%&ndash;						<col width="37%"/>&ndash;%&gt;--%>
						<%--</colgroup>--%>
						<%--<tbody>--%>
						<%--<tr>--%>
							<%--<th>学历：</th>--%>
							<%--<td>--%>
								<%--&nbsp;${user.educationName}--%>
								<%--<c:if test="${empty user.educationName}">--%>
									<%--<span style="color: red" >未填写！</span>--%>
									<%--<c:set var="canApply" value="N"></c:set>--%>
								<%--</c:if>--%>
							<%--</td>--%>
							<%--<th>毕业证书编号：</th>--%>
							<%--<td>--%>
								<%--&nbsp;${resDoctor.certificateNo}--%>
								<%--<c:if test="${empty resDoctor.certificateNo}">--%>
									<%--<span style="color: red" >未填写！</span>--%>
									<%--<c:set var="canApply" value="N"></c:set>--%>
								<%--</c:if>--%>
							<%--</td>--%>
						<%--</tr>--%>
						<%--<tr>--%>
							<%--<th>培训开始时间：</th>--%>
							<%--<td>--%>
								<%--&nbsp;${startDate}--%>
								<%--<c:if test="${empty startDate}">--%>
									<%--<span style="color: red" >未填写！</span>--%>
									<%--<c:set var="canApply" value="N"></c:set>--%>
								<%--</c:if>--%>
							<%--</td>--%>
							<%--<th>培训结束时间 ：</th>--%>
							<%--<td>--%>
								<%--${endDate}--%>
								<%--<c:if test="${empty endDate}">--%>
									<%--<span style="color: red" >未填写！</span>--%>
									<%--<c:set var="canApply" value="N"></c:set>--%>
								<%--</c:if>--%>
							<%--</td>--%>
						<%--</tr>--%>
						<%--<tr>--%>
							<%--<th>报考资格材料：</th>--%>
							<%--<td>--%>
								<%--&nbsp;${practicingMap.graduationMaterialName}--%>
								<%--<c:if test="${empty practicingMap.graduationMaterialName}">--%>
									<%--<span style="color: red" >未填写！</span>--%>
									<%--<c:set var="canApply" value="N"></c:set>--%>
								<%--</c:if>--%>
							<%--</td>--%>
							<%--<th>报考资格材料编码：</th>--%>
							<%--<td>--%>
								<%--&nbsp;${practicingMap.graduationMaterialCode}--%>
								<%--<c:if test="${empty practicingMap.graduationMaterialCode}">--%>
									<%--<span style="color: red" >&nbsp;未填写！</span>--%>
									<%--<c:set var="canApply" value="N"></c:set>--%>
								<%--</c:if>--%>
							<%--</td>--%>
						<%--</tr>--%>
						<%--<tr>--%>
							<%--<th>取得资格时间：</th>--%>
							<%--<td >--%>
								<%--&nbsp;${practicingMap.graduationMaterialTime}--%>
								<%--<c:if test="${empty practicingMap.graduationMaterialTime}">--%>
									<%--<span>暂无</span>--%>
								<%--</c:if>--%>
							<%--</td>--%>
							<%--<th>执业类型：</th>--%>
							<%--<td>--%>
								<%--<c:forEach items="${dictTypeEnumPracticeTypeList}" var="dictTypeEnumPracticeType">--%>
									<%--<c:if test='${practicingMap.graduationCategoryId eq dictTypeEnumPracticeType.dictId}'>--%>
										<%--${dictTypeEnumPracticeType.dictName}--%>
									<%--</c:if>--%>
								<%--</c:forEach>--%>
								<%--<c:if test="${practicingMap.graduationCategoryId eq '暂无'}">暂无</c:if>--%>
								<%--<c:if test="${empty practicingMap.graduationCategoryId}">--%>
									<%--<span style="color: red" >未填写！</span>--%>
									<%--<c:set var="canApply" value="N"></c:set>--%>
								<%--</c:if>--%>
							<%--</td>--%>
						<%--</tr>--%>
						<%--<tr>--%>
							<%--<th>执业范围：</th>--%>
							<%--<td>--%>
								<%--<c:forEach items="${dictTypeEnumPracticeTypeList}" var="dict">--%>
									<%--<c:set var="dictKey" value="dictTypeEnumPracticeType.${dict.dictId}List"/>--%>
									<%--<c:forEach items="${applicationScope[dictKey]}" var="scope">--%>
										<%--<c:if test='${ practicingMap.graduationScopeId eq scope.dictId and dict.dictId eq practicingMap.graduationCategoryId}'>--%>
											<%--${scope.dictName}--%>
										<%--</c:if>--%>
									<%--</c:forEach>--%>
								<%--</c:forEach>--%>
								<%--<c:if test="${practicingMap.graduationScopeId eq '暂无'}">暂无</c:if>--%>
								<%--<c:if test="${empty practicingMap.graduationScopeId}">--%>
									<%--<span style="color: red" >未填写！</span>--%>
									<%--<c:set var="canApply" value="N"></c:set>--%>
								<%--</c:if>--%>
							<%--</td>--%>
							<%--<th>培训年限：</th>--%>
							<%--<td>--%>
								<%--<c:if test="${empty doctorRecruit.trainYear}">--%>
									<%--<span style="color: red">未填写！</span>--%>
									<%--<c:set var="canApply" value="N"></c:set>--%>
								<%--</c:if>--%>
								<%--<c:if test="${'OneYear' eq doctorRecruit.trainYear}">&nbsp;一年</c:if>--%>
								<%--<c:if test="${'TwoYear' eq doctorRecruit.trainYear}">&nbsp;两年</c:if>--%>
								<%--<c:if test="${'ThreeYear' eq doctorRecruit.trainYear}">&nbsp;三年</c:if>--%>
								<%--<span style="padding-left: 30px">--%>
							<%--<c:if test="${empty doctorRecruit.trainYear ||  'ThreeYear' eq doctorRecruit.trainYear}">无减免证明</c:if>--%>
							<%--<c:if test="${'TwoYear' eq doctorRecruit.trainYear ||  'OneYear' eq doctorRecruit.trainYear}">--%>
								<%--<c:if test="${'DoctorTrainingSpe' eq doctorRecruit.catSpeId}">--%>
									<%--<c:if test="${empty doctorRecruit.proveFileUrl}">--%>
										<%--无减免证明--%>
									<%--</c:if>--%>
									<%--<c:if test="${not empty doctorRecruit.proveFileUrl}">--%>
										<%--[<a href="${sysCfgMap['upload_base_url']}/${doctorRecruit.proveFileUrl}" target="_blank">查看减免证明</a>]&nbsp;--%>
									<%--</c:if>--%>
								<%--</c:if>--%>
								<%--<c:if test="${'DoctorTrainingSpe' ne doctorRecruit.catSpeId}">--%>
									<%--无减免证明--%>
								<%--</c:if>--%>
							<%--</c:if>--%>
							<%--</span>--%>
							<%--</td>--%>
						<%--</tr>--%>
						<%--<tr>--%>
							<%--<th>培训登记手册完成情况：</th>--%>
							<%--<td>--%>
								<%--&lt;%&ndash;<select <c:if test="${(empty jsresGraduationApply.auditStatusId) or (jsresGraduationApply.auditStatusId eq 'Auditing') or (jsresGraduationApply.auditStatusId eq 'WaitChargePass') or (jsresGraduationApply.auditStatusId eq 'WaitGlobalPass')&ndash;%&gt;--%>
							<%--&lt;%&ndash;or (jsresGraduationApply.auditStatusId eq 'GlobalPassed')}">disabled="disabled"</c:if>&ndash;%&gt;--%>
								<%--<select <c:if test="${(jsresGraduationApply.auditStatusId eq 'Auditing') or (jsresGraduationApply.auditStatusId eq 'WaitChargePass') or (jsresGraduationApply.auditStatusId eq 'WaitGlobalPass')--%>
								<%--or (jsresGraduationApply.auditStatusId eq 'GlobalPassed')}">disabled="disabled"</c:if>--%>
										<%--class="validate[required]" name="registeManua" onchange="saveRegisteManua();">--%>
									<%--<option value="">--请选择--</option>--%>
									<%--<option value="2" <c:if test="${not empty userResumeExt.registeManua and userResumeExt.registeManua eq '2'}">selected="selected"</c:if>>正常</option>--%>
									<%--<option value="1" <c:if test="${not empty userResumeExt.registeManua and userResumeExt.registeManua eq GlobalConstant.PASS}">selected="selected"</c:if>>已完成</option>--%>
									<%--<option value="0" <c:if test="${not empty userResumeExt.registeManua and userResumeExt.registeManua eq GlobalConstant.UNPASS}">selected="selected"</c:if>>未完成</option>--%>
								<%--</select>--%>
							<%--</td>--%>
							<%--<th>实际轮转时间（月）：</th>--%>
							<%--<td>--%>

								<%--&nbsp;<fmt:formatNumber type="number" value="${allMonth}" maxFractionDigits="2"/>--%>
								<%--<c:if test="${empty allMonth}">--%>
									<%--<span style="color: red" >未填写！</span>--%>
									<%--<c:set var="canApply" value="N"></c:set>--%>
								<%--</c:if>--%>
							<%--</td>--%>
						<%--</tr>--%>
						<%--<tr>--%>
							<%--<th>数据填写比例：</th>--%>
							<%--<td>--%>
								<%--&nbsp;${avgBiMap.avgComplete}--%>
								<%--<c:if test="${empty avgBiMap.avgComplete}">--%>
									<%--0--%>
								<%--</c:if>%--%>
							<%--</td>--%>
							<%--<th>数据审核比例：</th>--%>
							<%--<td>--%>
								<%--&nbsp;${avgBiMap.avgAudit}--%>
								<%--<c:if test="${empty avgBiMap.avgAudit}">--%>
									<%--0--%>
								<%--</c:if>%--%>
							<%--</td>--%>
						<%--</tr>--%>
						<%--<tr>--%>
							<%--<th>是否军队人员：</th>--%>
							<%--<td  colspan="3">--%>
								<%--<c:if test="${empty userResumeExt.armyType}">--%>
									<%--<span style="color: red;padding-left: 4px;" >未填写,请到培训信息完善！</span>--%>
									<%--<c:set var="canApply" value="N"></c:set>--%>
								<%--</c:if>--%>
								<%--<c:if test="${not empty userResumeExt.armyType}">--%>
									<%--<span >${pdfn:getArmyTypeEnumName(userResumeExt.armyType)}</span>--%>
								<%--</c:if>--%>
							<%--</td>--%>
						<%--</tr>--%>
						<%--<tr>--%>
							<%--<th>受疫情影响未完成的培训：</th>--%>
							<%--<td  colspan="3">--%>
								<%--<c:choose>--%>
									<%--<c:when test="${'Y'eq apply || ('Y'eq inApplyTime and pdfn:getCurrYear() eq doctorRecruit.graduationYear and  ((jsresGraduationApply.auditStatusId eq 'LocalNotPassed') or (jsresGraduationApply.auditStatusId eq 'ChargeNotPassed')--%>
														<%--) and not empty jsresGraduationApply.auditStatusId)}">--%>
										<%--&nbsp;<textarea name="remark" id="remark" class="input validate[required,maxSize[150]]" style="width: 85%;height: 100px;margin: 5px;text-indent: 5px;">${jsresGraduationApply.remark}</textarea>--%>
									<%--</c:when>--%>
									<%--<c:otherwise>--%>
										<%--&nbsp;${jsresGraduationApply.remark}--%>
										<%--<c:if test="${empty jsresGraduationApply.remark}">--%>
											<%--<span style="color: red" >未填写！</span>--%>
											<%--<c:set var="canApply" value="N"></c:set>--%>
										<%--</c:if>--%>
									<%--</c:otherwise>--%>
								<%--</c:choose>--%>
							<%--</td>--%>
						<%--</tr>--%>
						<%--</tbody>--%>
					<%--</table>--%>
					<%--<table cellpadding="0" cellspacing="0" class="base_info" >--%>
						<%--<colgroup>--%>
							<%--<col width="21%"/>--%>
							<%--<col width="13.2%"/>--%>
							<%--<col width="13.2%"/>--%>
							<%--<col width="13.2%"/>--%>
							<%--<col width="13.2%"/>--%>
							<%--<col width="13.2%"/>--%>
							<%--<col width="13.2%"/>--%>
						<%--</colgroup>--%>
						<%--<tbody>--%>
						<%--<c:if test="${isAssiGeneral eq 'N'}">--%>
							<%--<tr>--%>
								<%--<th style="text-align: center;" rowspan="2">公共科目成绩</th>--%>
								<%--<th style="text-align: center;">卫生法律和法规</th>--%>
								<%--<th style="text-align: center;">循证医学</th>--%>
								<%--<th style="text-align: center;">临床思维与<br>人际沟通</th>--%>
								<%--<th style="text-align: center;">重点传染病<br>防治知识</th>--%>
								<%--<th style="text-align: center;">是否合格</th>--%>
									<%--&lt;%&ndash;							<th style="text-align: center;">附件材料</th>&ndash;%&gt;--%>
							<%--</tr>--%>
							<%--<tr>--%>
								<%--<td style=" text-align: center;">--%>
									<%--<input <c:if test="${(jsresGraduationApply.auditStatusId eq 'WaitChargePass') or (jsresGraduationApply.auditStatusId eq 'WaitGlobalPass')--%>
														<%--or (jsresGraduationApply.auditStatusId eq 'GlobalPassed') or publicScore.paperFlow eq 'Y'}">readonly="readonly"</c:if> style="width:100%;height: 100%;text-align: center" name="lawScore"--%>
										   <%--onchange="saveScore('lawScore',this);" value="${extScore.lawScore}"/>--%>
								<%--</td>--%>
								<%--<td style=" text-align: center;">--%>
									<%--<input <c:if test="${(jsresGraduationApply.auditStatusId eq 'WaitChargePass') or (jsresGraduationApply.auditStatusId eq 'WaitGlobalPass')--%>

															<%--or (jsresGraduationApply.auditStatusId eq 'GlobalPassed') or publicScore.paperFlow eq 'Y'}">readonly="readonly"</c:if> style="width:100%;height: 100%;text-align: center" name="medicineScore"--%>
										   <%--onchange="saveScore('medicineScore',this);" value="${extScore.medicineScore}"/>--%>
								<%--</td>--%>
								<%--<td style=" text-align: center;">--%>
									<%--<input <c:if test="${(jsresGraduationApply.auditStatusId eq 'WaitChargePass') or (jsresGraduationApply.auditStatusId eq 'WaitGlobalPass')--%>
													<%--or (jsresGraduationApply.auditStatusId eq 'GlobalPassed') or publicScore.paperFlow eq 'Y'}">readonly="readonly"</c:if> style="width:100%;height: 100%;text-align: center" name="clinicalScore"--%>
										   <%--onchange="saveScore('clinicalScore',this);" value="${extScore.clinicalScore}"/>--%>
								<%--</td>--%>
								<%--<td style=" text-align: center;">--%>
									<%--<input <c:if test="${(jsresGraduationApply.auditStatusId eq 'WaitChargePass') or (jsresGraduationApply.auditStatusId eq 'WaitGlobalPass')--%>
											<%--or (jsresGraduationApply.auditStatusId eq 'GlobalPassed') or publicScore.paperFlow eq 'Y'}">readonly="readonly"</c:if> style="width:100%;height: 100%;text-align: center" name="ckScore"--%>
										   <%--onchange="saveScore('ckScore',this);" value="${extScore.ckScore}"/>--%>
								<%--</td>--%>
								<%--<td style=" text-align: center;">--%>
									<%--&lt;%&ndash;<select <c:if test="${(empty jsresGraduationApply.auditStatusId) or (jsresGraduationApply.auditStatusId eq 'Auditing') or (jsresGraduationApply.auditStatusId eq 'WaitChargePass') or (jsresGraduationApply.auditStatusId eq 'WaitGlobalPass')&ndash;%&gt;--%>
							<%--&lt;%&ndash;or (jsresGraduationApply.auditStatusId eq 'GlobalPassed')}">disabled="disabled"</c:if>&ndash;%&gt;--%>
											<%--&lt;%&ndash;class="validate[required]" name="skillScore" onchange="saveSkillScore();">&ndash;%&gt;--%>
										<%--&lt;%&ndash;<option value="">--请选择--</option>&ndash;%&gt;--%>
										<%--&lt;%&ndash;<option value="1" <c:if test="${publicScore.skillScore eq GlobalConstant.PASS}">selected="selected"</c:if>>合格</option>&ndash;%&gt;--%>
										<%--&lt;%&ndash;<option value="0" <c:if test="${publicScore.skillScore eq GlobalConstant.UNPASS}">selected="selected"</c:if>>不合格</option>&ndash;%&gt;--%>
									<%--&lt;%&ndash;</select>&ndash;%&gt;--%>
									<%--<input type="text" placeholder="以上传附件为准" readonly="readonly"/>--%>
								<%--</td>--%>
									<%--&lt;%&ndash;<td style=" text-align: center;">--%>

                                        <%--<input id="filePath" type="hidden" value="${file.filePath}">--%>
                                            <%--&lt;%&ndash;<c:if test="${empty jsresGraduationApply.auditStatusId}">&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;<c:if test="${ (!((jsresGraduationApply.auditStatusId eq 'WaitChargePass') or (jsresGraduationApply.auditStatusId eq 'WaitGlobalPass')&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;or (jsresGraduationApply.auditStatusId eq 'GlobalPassed')))}">&ndash;%&gt;--%>
                                        <%--<c:if test="${empty file}">--%>
                                                <%--<span id="fileSpan" style="display:none">--%>
                                                    <%--[<a href="${sysCfgMap['upload_base_url']}/${file.filePath}" target="_blank">查看</a>]&nbsp;--%>
                                                <%--</span>--%>
                                            <%--<a name="chooseFile" href="javascript:chooseFile();" >上传</a>&nbsp;--%>
                                            <%--&lt;%&ndash;<span id="fileDelSpan" style="display: none;">&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;<a href="javascript:delFile();">删除</a>&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;</span>&ndash;%&gt;--%>
                                        <%--</c:if>--%>
                                        <%--<c:if test="${not empty file}">--%>
                                                <%--<span id="fileSpan">--%>
                                                    <%--[<a href="${sysCfgMap['upload_base_url']}/${file.filePath}" target="_blank">查看</a>]&nbsp;--%>
                                                <%--</span>--%>
                                            <%--<a name="chooseFile" href="javascript:chooseFile();" >重新上传</a>--%>
                                            <%--&lt;%&ndash;<span id="fileDelSpan">&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;<a href="javascript:delFile();">删除</a>&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;</span>&ndash;%&gt;--%>
                                        <%--</c:if>--%>
                                            <%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;<c:if test="${ ((jsresGraduationApply.auditStatusId eq 'WaitChargePass') or (jsresGraduationApply.auditStatusId eq 'WaitGlobalPass')&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;or (jsresGraduationApply.auditStatusId eq 'GlobalPassed'))}">&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;<c:if test="${empty file.filePath}">&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;未上传&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;<c:if test="${not empty file.filePath}">&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;<span id="fileSpan">&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;[<a href="${sysCfgMap['upload_base_url']}/${file.filePath}" target="_blank">查看</a>]&nbsp;&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;</span>&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
                                    <%--</td>&ndash;%&gt;--%>
							<%--</tr>--%>
						<%--</c:if>--%>
						<%--<c:if test="${isAssiGeneral eq 'Y'}">--%>
							<%--<tr>--%>
								<%--<th style="text-align: center;" rowspan="2" >全科医学及相关理论知识考核</th>--%>
								<%--<th style="text-align: center;" colspan="4">成绩</th>--%>
								<%--<th style="text-align: center;">是否合格</th>--%>
									<%--&lt;%&ndash;							<th style="text-align: center;">附件材料</th>&ndash;%&gt;--%>
							<%--</tr>--%>
							<%--<tr>--%>
								<%--<td style="text-align: center;" colspan="4">--%>
									<%--<input <c:if test="${(jsresGraduationApply.auditStatusId eq 'WaitChargePass') or (jsresGraduationApply.auditStatusId eq 'WaitGlobalPass')--%>
							<%--or (jsresGraduationApply.auditStatusId eq 'GlobalPassed') or publicScore.paperFlow eq 'Y'}">readonly="readonly"</c:if> style="width:100%;height: 100%;text-align: center" name="theoryScore"--%>
										   <%--onchange="saveScore('theoryScore',this);" value="${publicScore.theoryScore}"/>--%>
								<%--</td>--%>
								<%--<td style=" text-align: center;">--%>
									<%--&lt;%&ndash;<select <c:if test="${(jsresGraduationApply.auditStatusId eq 'Auditing') or (jsresGraduationApply.auditStatusId eq 'WaitChargePass') or (jsresGraduationApply.auditStatusId eq 'WaitGlobalPass')&ndash;%&gt;--%>
							<%--&lt;%&ndash;or (jsresGraduationApply.auditStatusId eq 'GlobalPassed')}">disabled="disabled"</c:if>&ndash;%&gt;--%>
											<%--&lt;%&ndash;class="validate[required]"  name="skillScore" onchange="saveSkillScore();">&ndash;%&gt;--%>
										<%--&lt;%&ndash;<option value="">--请选择--</option>&ndash;%&gt;--%>
										<%--&lt;%&ndash;<option value="1" <c:if test="${publicScore.skillScore eq GlobalConstant.PASS}">selected="selected"</c:if>>合格</option>&ndash;%&gt;--%>
										<%--&lt;%&ndash;<option value="0" <c:if test="${publicScore.skillScore eq GlobalConstant.UNPASS}">selected="selected"</c:if>>不合格</option>&ndash;%&gt;--%>
									<%--&lt;%&ndash;</select>&ndash;%&gt;--%>
										<%--<input type="text" placeholder="以上传附件为准" readonly="readonly"/>--%>
								<%--</td>--%>
									<%--&lt;%&ndash;<td style=" text-align: center;">--%>
                                        <%--<input id="filePath" type="hidden" value="${file.filePath}">--%>
                                            <%--&lt;%&ndash;<c:if test="${empty jsresGraduationApply.auditStatusId or jsresGraduationApply.auditStatusId eq 'Auditing'}">&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;<c:if test="${ ( !((jsresGraduationApply.auditStatusId eq 'WaitChargePass') or (jsresGraduationApply.auditStatusId eq 'WaitGlobalPass')&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;or (jsresGraduationApply.auditStatusId eq 'GlobalPassed')))}">&ndash;%&gt;--%>
                                        <%--<c:if test="${empty file}">--%>
                                                <%--<span id="fileSpan" style="display:none">--%>
                                                    <%--[<a href="${sysCfgMap['upload_base_url']}/${file.filePath}" target="_blank">查看</a>]&nbsp;--%>
                                                <%--</span>--%>
                                            <%--<a name="chooseFile" href="javascript:chooseFile();" >上传</a>&nbsp;--%>
                                            <%--&lt;%&ndash;<span id="fileDelSpan" style="display: none;">&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;<a href="javascript:delFile();">删除</a>&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;</span>&ndash;%&gt;--%>
                                        <%--</c:if>--%>
                                        <%--<c:if test="${not empty file}">--%>
                                                <%--<span id="fileSpan">--%>
                                                    <%--[<a href="${sysCfgMap['upload_base_url']}/${file.filePath}" target="_blank">查看</a>]&nbsp;--%>
                                                <%--</span>--%>
                                            <%--<a name="chooseFile" href="javascript:chooseFile();" >重新上传</a>--%>
                                            <%--&lt;%&ndash;<span id="fileDelSpan">&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;<a href="javascript:delFile();">删除</a>&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;</span>&ndash;%&gt;--%>
                                        <%--</c:if>--%>
                                            <%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;<c:if test="${ ((jsresGraduationApply.auditStatusId eq 'WaitChargePass') or (jsresGraduationApply.auditStatusId eq 'WaitGlobalPass')&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;or (jsresGraduationApply.auditStatusId eq 'GlobalPassed'))}">&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;<c:if test="${empty file.filePath}">&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;未上传&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;<c:if test="${not empty file.filePath}">&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;<span id="fileSpan">&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;[<a href="${sysCfgMap['upload_base_url']}/${file.filePath}" target="_blank">查看</a>]&nbsp;&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;</span>&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;<c:if test="${not empty jsresGraduationApply.auditStatusId}">&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;<c:if test="${not empty file}">&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;[<a href="${sysCfgMap['upload_base_url']}/${file.filePath}" target="_blank">查看</a>]&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
                                    <%--</td>&ndash;%&gt;--%>
							<%--</tr>--%>
						<%--</c:if>--%>
						<%--</tbody>--%>
					<%--</table>--%>
					<%--&lt;%&ndash;				<table cellpadding="0" cellspacing="0" class="base_info" >&ndash;%&gt;--%>
					<%--&lt;%&ndash;					<colgroup>&ndash;%&gt;--%>
					<%--&lt;%&ndash;						<col width="21%"/>&ndash;%&gt;--%>
					<%--&lt;%&ndash;						<col width="15.8%"/>&ndash;%&gt;--%>
					<%--&lt;%&ndash;						<col width="15.8%"/>&ndash;%&gt;--%>
					<%--&lt;%&ndash;						<col width="15.8%"/>&ndash;%&gt;--%>
					<%--&lt;%&ndash;						<col width="15.8%"/>&ndash;%&gt;--%>
					<%--&lt;%&ndash;						<col width="15.8%"/>&ndash;%&gt;--%>
					<%--&lt;%&ndash;					</colgroup>&ndash;%&gt;--%>
					<%--&lt;%&ndash;					<tbody>&ndash;%&gt;--%>
					<%--&lt;%&ndash;&lt;%&ndash;					<c:if test="${isAssiGeneral eq 'N'}">&ndash;%&gt;&ndash;%&gt;--%>
					<%--&lt;%&ndash;						<tr>&ndash;%&gt;--%>
					<%--&lt;%&ndash;							<th style="text-align: center;" rowspan="2">公共科目成绩</th>&ndash;%&gt;--%>
					<%--&lt;%&ndash;							<th style="text-align: center;">卫生法律和法规</th>&ndash;%&gt;--%>
					<%--&lt;%&ndash;							<th style="text-align: center;">循证医学</th>&ndash;%&gt;--%>
					<%--&lt;%&ndash;							<th style="text-align: center;">临床思维与<br>人际沟通</th>&ndash;%&gt;--%>
					<%--&lt;%&ndash;							<th style="text-align: center;">重点传染病<br>防治知识</th>&ndash;%&gt;--%>
					<%--&lt;%&ndash;							<th style="text-align: center;">是否合格</th>&ndash;%&gt;--%>
					<%--&lt;%&ndash;						</tr>&ndash;%&gt;--%>
					<%--&lt;%&ndash;						<tr>&ndash;%&gt;--%>
					<%--&lt;%&ndash;							<td style=" text-align: center;">&ndash;%&gt;--%>
					<%--&lt;%&ndash;								<input readonly="readonly" style="width:100%;height: 100%;text-align: center" name="lawScore"&ndash;%&gt;--%>
					<%--&lt;%&ndash;									   value="${extScore.lawScore}"/>&ndash;%&gt;--%>
					<%--&lt;%&ndash;							</td>&ndash;%&gt;--%>
					<%--&lt;%&ndash;							<td style=" text-align: center;">&ndash;%&gt;--%>
					<%--&lt;%&ndash;								<input readonly="readonly"style="width:100%;height: 100%;text-align: center" name="medicineScore"&ndash;%&gt;--%>
					<%--&lt;%&ndash;									   value="${extScore.medicineScore}"/>&ndash;%&gt;--%>
					<%--&lt;%&ndash;							</td>&ndash;%&gt;--%>
					<%--&lt;%&ndash;							<td style=" text-align: center;">&ndash;%&gt;--%>
					<%--&lt;%&ndash;								<input readonly="readonly" style="width:100%;height: 100%;text-align: center" name="clinicalScore"&ndash;%&gt;--%>
					<%--&lt;%&ndash;									    value="${extScore.clinicalScore}"/>&ndash;%&gt;--%>
					<%--&lt;%&ndash;							</td>&ndash;%&gt;--%>
					<%--&lt;%&ndash;							<td style=" text-align: center;">&ndash;%&gt;--%>
					<%--&lt;%&ndash;								<input readonly="readonly" style="width:100%;height: 100%;text-align: center" name="ckScore"&ndash;%&gt;--%>
					<%--&lt;%&ndash;									    value="${extScore.ckScore}"/>&ndash;%&gt;--%>
					<%--&lt;%&ndash;							</td>&ndash;%&gt;--%>
					<%--&lt;%&ndash;							<td style=" text-align: center;">&ndash;%&gt;--%>
					<%--&lt;%&ndash;								<c:if test="${publicScore.skillScore eq GlobalConstant.PASS}">&ndash;%&gt;--%>
					<%--&lt;%&ndash;									<input type="hidden" readonly="readonly" style="width:100%;height: 100%;text-align: center"&ndash;%&gt;--%>
					<%--&lt;%&ndash;										   name="skillScore"&ndash;%&gt;--%>
					<%--&lt;%&ndash;										   value="1"/>合格&ndash;%&gt;--%>
					<%--&lt;%&ndash;								</c:if>&ndash;%&gt;--%>
					<%--&lt;%&ndash;								<c:if test="${publicScore.skillScore eq GlobalConstant.UNPASS}">&ndash;%&gt;--%>
					<%--&lt;%&ndash;									<input type="hidden" readonly="readonly" style="width:100%;height: 100%;text-align: center"&ndash;%&gt;--%>
					<%--&lt;%&ndash;										   name="skillScore"&ndash;%&gt;--%>
					<%--&lt;%&ndash;										   value="0"/>不合格&ndash;%&gt;--%>
					<%--&lt;%&ndash;								</c:if>&ndash;%&gt;--%>
					<%--&lt;%&ndash;								<c:if test="${empty publicScore.skillScore }">&ndash;%&gt;--%>
					<%--&lt;%&ndash;									<input type="hidden" readonly="readonly" style="width:100%;height: 100%;text-align: center"&ndash;%&gt;--%>
					<%--&lt;%&ndash;										   name="skillScore"&ndash;%&gt;--%>
					<%--&lt;%&ndash;										   value=""/>&ndash;%&gt;--%>
					<%--&lt;%&ndash;								</c:if>&ndash;%&gt;--%>
					<%--&lt;%&ndash;							</td>&ndash;%&gt;--%>
					<%--&lt;%&ndash;						</tr>&ndash;%&gt;--%>

					<%--&lt;%&ndash;					</tbody>&ndash;%&gt;--%>
					<%--&lt;%&ndash;				</table>&ndash;%&gt;--%>

					<%--&lt;%&ndash;<table cellpadding="0" cellspacing="0" class="base_info" >&ndash;%&gt;--%>
					<%--&lt;%&ndash;<colgroup>&ndash;%&gt;--%>
					<%--&lt;%&ndash;<col width="21%"/>&ndash;%&gt;--%>
					<%--&lt;%&ndash;<col width="79%"/>&ndash;%&gt;--%>
					<%--&lt;%&ndash;</colgroup>&ndash;%&gt;--%>
					<%--&lt;%&ndash;<tr>&ndash;%&gt;--%>
					<%--&lt;%&ndash;<th style="text-align: center;" >结业考核资格审查意见</th>&ndash;%&gt;--%>
					<%--&lt;%&ndash;<td>${jsresGraduationApply.auditStatusName}</td>&ndash;%&gt;--%>
					<%--&lt;%&ndash;</tr>&ndash;%&gt;--%>
			<%--</form>--%>

			<%--<div class="main_bd"  style="padding-top: 15px;" >--%>
				<%--<h4  style="float:none;">报考材料</h4>--%>
				<%--<div style="display: flex;justify-content: space-between">--%>
					<%--<div class="div_table"  style="padding: 20px 0px 0px">--%>
						<%--<div style="width: 280px;height: 200px">--%>
							<%--<c:if test="${not empty userResumeExt.certificateUri}">--%>
								<%--&lt;%&ndash;<a href="${sysCfgMap['upload_base_url']}/${userResumeExt.certificateUri}" target="_blank">&ndash;%&gt;--%>
									<%--&lt;%&ndash;<img src="${sysCfgMap['upload_base_url']}/${userResumeExt.certificateUri}" style="width: 100%;height: 90%;">&ndash;%&gt;--%>
								<%--&lt;%&ndash;</a>&ndash;%&gt;--%>
								<%--<ul>--%>
									<%--<li class="ratateImg" style="height: 200px;">--%>
										<%--<img src="${sysCfgMap['upload_base_url']}/${userResumeExt.certificateUri}"--%>
											 <%--style="width: 100%;height: 90%;">--%>
									<%--</li>--%>
								<%--</ul>--%>
							<%--</c:if>--%>
							<%--<c:if test="${empty userResumeExt.certificateUri}">--%>
								<%--<span style="margin-top: 100px;margin-left: 110px;">暂未上传</span>--%>
							<%--</c:if>--%>
						<%--</div>--%>
						<%--<h5 style="text-align: center;">毕业证书</h5>--%>
					<%--</div>--%>
					<%--<div class="div_table" style="padding: 20px 0px 0px">--%>
						<%--<div style="width: 280px;height: 200px">--%>
							<%--<c:if test="${userResumeExt.isPassQualifyingExamination eq 	GlobalConstant.FLAG_N }">--%>
								<%--<c:if test="${'暂无' ne practicingMap.graduationMaterialUri}">--%>
									<%--<c:if test="${not empty practicingMap.graduationMaterialUri}">--%>
										<%--&lt;%&ndash;<a href="${sysCfgMap['upload_base_url']}/${practicingMap.graduationMaterialUri}"&ndash;%&gt;--%>
										   <%--&lt;%&ndash;target="_blank">&ndash;%&gt;--%>
											<%--&lt;%&ndash;<img src="${sysCfgMap['upload_base_url']}/${practicingMap.graduationMaterialUri}"&ndash;%&gt;--%>
												 <%--&lt;%&ndash;style="width: 100%;height: 90%;">&ndash;%&gt;--%>
										<%--&lt;%&ndash;</a>&ndash;%&gt;--%>
										<%--<ul>--%>
											<%--<li class="ratateImg" style="height: 200px;">--%>
												<%--<img src="${sysCfgMap['upload_base_url']}/${practicingMap.graduationMaterialUri}"--%>
													 <%--style="width: 100%;height: 90%;">--%>
											<%--</li>--%>
										<%--</ul>--%>
									<%--</c:if>--%>
									<%--<c:if test="${empty practicingMap.graduationMaterialUri}">--%>
										<%--<span style="margin-top: 100px;margin-left: 110px;">暂未上传</span>--%>
									<%--</c:if>--%>
								<%--</c:if>--%>
								<%--<c:if test="${'暂无' eq practicingMap.graduationMaterialUri}">--%>
									<%--<span style="margin-top: 100px;margin-left: 110px;">暂未上传</span>--%>
								<%--</c:if>--%>
							<%--</c:if>--%>
							<%--<c:if test="${userResumeExt.isPassQualifyingExamination eq 	GlobalConstant.FLAG_Y }">--%>
								<%--<c:if test="${userResumeExt.isHaveQualificationCertificate eq 	GlobalConstant.FLAG_N }">--%>
									<%--<c:if test="${not  empty userResumeExt.qualificationMaterialId2Url}">--%>
										<%--&lt;%&ndash;<a href="${sysCfgMap['upload_base_url']}/${userResumeExt.qualificationMaterialId2Url}"&ndash;%&gt;--%>
										   <%--&lt;%&ndash;target="_blank">&ndash;%&gt;--%>
											<%--&lt;%&ndash;<img src="${sysCfgMap['upload_base_url']}/${userResumeExt.qualificationMaterialId2Url}"&ndash;%&gt;--%>
												 <%--&lt;%&ndash;style="width: 100%;height: 90%;">&ndash;%&gt;--%>
										<%--&lt;%&ndash;</a>&ndash;%&gt;--%>
										<%--<ul>--%>
											<%--<li class="ratateImg" style="height: 200px;">--%>
												<%--<img src="${sysCfgMap['upload_base_url']}/${userResumeExt.qualificationMaterialId2Url}"--%>
													 <%--style="width: 100%;height: 90%;">--%>
											<%--</li>--%>
										<%--</ul>--%>
									<%--</c:if>--%>
									<%--<c:if test="${empty userResumeExt.qualificationMaterialId2Url}">--%>
										<%--<span style="margin-top: 100px;margin-left: 110px;">暂未上传</span>--%>
									<%--</c:if>--%>
								<%--</c:if>--%>
							<%--</c:if>--%>
							<%--<c:if test="${userResumeExt.isPassQualifyingExamination eq 	GlobalConstant.FLAG_Y }">--%>
								<%--<c:if test="${userResumeExt.isHaveQualificationCertificate eq 	GlobalConstant.FLAG_Y }">--%>
									<%--<c:if test="${userResumeExt.isHavePracticingCategory eq 	GlobalConstant.FLAG_N }">--%>
										<%--<c:if test="${not  empty userResumeExt.doctorQualificationCertificateUrl}">--%>
											<%--&lt;%&ndash;<a href="${sysCfgMap['upload_base_url']}/${userResumeExt.doctorQualificationCertificateUrl}"&ndash;%&gt;--%>
											   <%--&lt;%&ndash;target="_blank">&ndash;%&gt;--%>
												<%--&lt;%&ndash;<img src="${sysCfgMap['upload_base_url']}/${userResumeExt.doctorQualificationCertificateUrl}"&ndash;%&gt;--%>
													 <%--&lt;%&ndash;style="width: 100%;height: 90%;">&ndash;%&gt;--%>
											<%--&lt;%&ndash;</a>&ndash;%&gt;--%>
											<%--<ul>--%>
												<%--<li class="ratateImg" style="height: 200px;">--%>
													<%--<img src="${sysCfgMap['upload_base_url']}/${userResumeExt.doctorQualificationCertificateUrl}"--%>
														 <%--style="width: 100%;height: 90%;">--%>
												<%--</li>--%>
											<%--</ul>--%>
										<%--</c:if>--%>
										<%--<c:if test="${empty userResumeExt.doctorQualificationCertificateUrl}">--%>
											<%--<span style="margin-top: 100px;margin-left: 110px;">暂未上传</span>--%>
										<%--</c:if>--%>
									<%--</c:if>--%>
								<%--</c:if>--%>
							<%--</c:if>--%>
							<%--<c:if test="${userResumeExt.isPassQualifyingExamination eq 	GlobalConstant.FLAG_Y }">--%>
								<%--<c:if test="${userResumeExt.isHaveQualificationCertificate eq 	GlobalConstant.FLAG_Y }">--%>
									<%--<c:if test="${userResumeExt.isHavePracticingCategory eq 	GlobalConstant.FLAG_Y }">--%>
										<%--<c:if test="${not  empty userResumeExt.doctorPracticingCategoryUrl}">--%>
											<%--&lt;%&ndash;<a href="${sysCfgMap['upload_base_url']}/${userResumeExt.doctorPracticingCategoryUrl}"&ndash;%&gt;--%>
											   <%--&lt;%&ndash;target="_blank">&ndash;%&gt;--%>
												<%--&lt;%&ndash;<img src="${sysCfgMap['upload_base_url']}/${userResumeExt.doctorPracticingCategoryUrl}"&ndash;%&gt;--%>
													 <%--&lt;%&ndash;style="width: 100%;height: 90%;">&ndash;%&gt;--%>
											<%--&lt;%&ndash;</a>&ndash;%&gt;--%>
											<%--<ul>--%>
												<%--<li class="ratateImg" style="height: 200px;">--%>
													<%--<img src="${sysCfgMap['upload_base_url']}/${userResumeExt.doctorPracticingCategoryUrl}"--%>
														 <%--style="width: 100%;height: 90%;">--%>
												<%--</li>--%>
											<%--</ul>--%>
										<%--</c:if>--%>
										<%--<c:if test="${empty userResumeExt.doctorPracticingCategoryUrl}">--%>
											<%--<span style="margin-top: 100px;margin-left: 110px;">暂未上传</span>--%>
										<%--</c:if>--%>
									<%--</c:if>--%>
								<%--</c:if>--%>
							<%--</c:if>--%>
						<%--</div>--%>
						<%--<h5 style="text-align: center;">--%>
							<%--<c:if test="${userResumeExt.isPassQualifyingExamination eq 	GlobalConstant.FLAG_N }">--%>
								<%--医师执业证书--%>
							<%--</c:if>--%>
							<%--<c:if test="${userResumeExt.isPassQualifyingExamination eq 	GlobalConstant.FLAG_Y }">--%>
								<%--<c:if test="${userResumeExt.isHaveQualificationCertificate eq 	GlobalConstant.FLAG_N }">--%>
									<%--成绩单--%>
								<%--</c:if>--%>
							<%--</c:if>--%>
							<%--<c:if test="${userResumeExt.isPassQualifyingExamination eq 	GlobalConstant.FLAG_Y }">--%>
								<%--<c:if test="${userResumeExt.isHaveQualificationCertificate eq 	GlobalConstant.FLAG_Y }">--%>
									<%--<c:if test="${userResumeExt.isHavePracticingCategory eq 	GlobalConstant.FLAG_N }">--%>
										<%--医师资格证书--%>
									<%--</c:if>--%>
								<%--</c:if>--%>
							<%--</c:if>--%>
							<%--<c:if test="${userResumeExt.isPassQualifyingExamination eq 	GlobalConstant.FLAG_Y }">--%>
								<%--<c:if test="${userResumeExt.isHaveQualificationCertificate eq 	GlobalConstant.FLAG_Y }">--%>
									<%--<c:if test="${userResumeExt.isHavePracticingCategory eq 	GlobalConstant.FLAG_Y }">--%>
										<%--医师执业证书--%>
									<%--</c:if>--%>
								<%--</c:if>--%>
							<%--</c:if>--%>
						<%--</h5>--%>
					<%--</div>--%>
					<%--<div class="div_table" style="padding: 20px 0px 0px">--%>
						<%--<div style="width: 280px;height: 200px" id="publicImg">--%>
							<%--<c:if test="${not empty file.filePath}">--%>
								<%--&lt;%&ndash;<a href="${sysCfgMap['upload_base_url']}/${file.filePath}" target="_blank">&ndash;%&gt;--%>
									<%--&lt;%&ndash;<img src="${sysCfgMap['upload_base_url']}/${file.filePath}" style="width: 100%;height: 90%;">&ndash;%&gt;--%>
								<%--&lt;%&ndash;</a>&ndash;%&gt;--%>
								<%--<ul>--%>
									<%--<li class="ratateImg" style="height: 200px;">--%>
										<%--<img src="${sysCfgMap['upload_base_url']}/${file.filePath}"--%>
											 <%--style="width: 100%;height: 90%;">--%>
									<%--</li>--%>
								<%--</ul>--%>
								<%--<c:if test="${empty jsresGraduationApply}">--%>
									<%--<a style="margin-left: 110px;" name="chooseFile"--%>
									   <%--href="javascript:chooseFile();">重新上传</a>--%>
								<%--</c:if>--%>
								<%--<c:if test="${not empty jsresGraduationApply}">--%>
									<%--<c:if test="${jsresGraduationApply.auditStatusId eq 'LocalNotPassed' or--%>
								<%--jsresGraduationApply.auditStatusId eq 'ChargeNotPassed' or--%>
							<%--jsresGraduationApply.auditStatusId eq 'GlobalNotPassed'	}">--%>
										<%--<a style="margin-left: 110px;" name="chooseFile"--%>
										   <%--href="javascript:chooseFile();">重新上传</a>--%>
									<%--</c:if>--%>
								<%--</c:if>--%>
							<%--</c:if>--%>
							<%--<c:if test="${ empty file.filePath}">--%>
								<%--<input id="filePath" type="hidden" value="${file.filePath}">--%>
								<%--<c:if test="${empty file}">--%>
									<%--<a  href="javascript:chooseFile();" >--%>
										<%--<img width="280px" height="180px"  src="<s:url value='/jsp/inx/jsres/images/add.png'/>">--%>
									<%--</a>&nbsp;--%>
								<%--</c:if>--%>
							<%--</c:if>--%>
						<%--</div>--%>
						<%--<h5 style="text-align: center;">公共科目成绩</h5>--%>
					<%--</div>--%>
				<%--</div>--%>
			<%--</div>--%>

			<div class="main_bd doctorInfo" style="padding-top: 15px;" >
				<h4 style="float:none;">轮转详情</h4>
				<div style="overflow:auto;max-width: 930px;max-height: 550px;border: medium;">
					<table cellpadding="0" cellspacing="0" class="grid" style="width: 100%;padding:0px;margin: 0px;">
						<tr>
							<th style="width: 100px;min-width: 100px;max-width: 100px;">轮转类型</th>
							<th style="width: 100px;min-width: 100px;max-width: 100px;">标准科室</th>
							<th style="width: 100px;min-width: 100px;max-width: 100px;">要求时间(月)/实际时间(月)</th>
							<th style="width: 100px;min-width: 100px;max-width: 100px;">轮转科室</th>
							<th style="width: 300px;min-width: 200px;max-width: 300px;">轮转时间</th>
							<th style="width: 400px;min-width: 400px;max-width: 400px;">出科考核表</th>
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
								<c:set var="bi" value="${biMap[dept.recordFlow]}"></c:set>
								<c:if test="${not empty resultMap[resultKey]}">
									<c:forEach items="${resultMap[resultKey]}" var="result" varStatus="resultStatus">
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
													<c:set var="imagelist" value="${afterImgMap[dept.recordFlow]}"></c:set>
													<td rowspan="${deptRowSpan[resultKey]}" <c:if test="${not empty imagelist}"></c:if>>
														<div style="margin-top:10px; ">
															<c:forEach var="image" items="${imagelist}" varStatus="status">
																<%--<a href="${image.imageUrl}" target="_blank"><img width="80px" height="80px"  src="${image.thumbUrl}"></a>&nbsp;--%>
																<ul>
																	<li class="ratateImg">
																		<img width="80px" height="80px" src="${image.imageUrl}">&nbsp;
																	</li>
																</ul>
															</c:forEach>
														</div>
													</td>
													<td rowspan="${deptRowSpan[resultKey]}">${empty bi.completeBi ? 0: bi.completeBi}${bi.completeBi=='-' ? '':'%'}</td>
													<td rowspan="${deptRowSpan[resultKey]}">${empty bi.outCompleteBi ? 0: bi.outCompleteBi}${bi.outCompleteBi=='-' ? '':'%'}</td>
													<td rowspan="${deptRowSpan[resultKey]}">${empty bi.auditBi ? 0:bi.auditBi}${bi.auditBi=='-' ? '':'%'}</td>
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
													<c:set var="imagelist" value="${afterImgMap[dept.recordFlow]}"></c:set>
													<td rowspan="${deptRowSpan[resultKey]}" <c:if test="${not empty imagelist}"></c:if>>
														<div style="margin-top:10px; ">
															<c:forEach var="image" items="${imagelist}" varStatus="status">
																<%--<a href="${image.imageUrl}" target="_blank"><img width="80px" height="80px"  src="${image.thumbUrl}"></a>&nbsp;--%>
																<ul>
																	<li class="ratateImg">
																		<img width="80px" height="80px" src="${image.imageUrl}">&nbsp;
																	</li>
																</ul>
															</c:forEach>
														</div>
													</td>
													<td rowspan="${deptRowSpan[resultKey]}">${empty bi.completeBi ? 0: bi.completeBi}${bi.completeBi=='-' ? '':'%'}</td>
													<td rowspan="${deptRowSpan[resultKey]}">${empty bi.outCompleteBi ? 0: bi.outCompleteBi}${bi.outCompleteBi=='-' ? '':'%'}</td>
													<td rowspan="${deptRowSpan[resultKey]}">${empty bi.auditBi ? 0:bi.auditBi}${bi.auditBi=='-' ? '':'%'}</td>
												</tr>
											</c:when>
											<c:when test="${(lastGroupFlow eq group.groupFlow) and
								(lastDeptFlow eq dept.recordFlow) and !(resultStatus.first)}">
												<tr>
													<td>${result.schDeptName}</td>
													<td>${result.schStartDate}~${result.schEndDate}</td>
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
										<c:set var="imagelist" value="${afterImgMap[dept.recordFlow]}"></c:set>
										<td <c:if test="${not empty imagelist}"></c:if>>
											<div style="margin-top:10px; ">
												<c:forEach var="image" items="${imagelist}" varStatus="status">
													<%--<a href="${image.imageUrl}" target="_blank"><img width="80px" height="80px"  src="${image.thumbUrl}"></a>&nbsp;--%>
													<ul>
														<li class="ratateImg">
															<img width="80px" height="80px" src="${image.imageUrl}">&nbsp;
														</li>
													</ul>
												</c:forEach>
											</div>
										</td>
										<td>${empty bi.completeBi ? 0: bi.completeBi}${bi.completeBi=='-' ? '':'%'}</td>
										<td>${empty bi.outCompleteBi ? 0: bi.outCompleteBi}${bi.outCompleteBi=='-' ? '':'%'}</td>
										<td>${empty bi.auditBi ? 0:bi.auditBi}${bi.auditBi=='-' ? '':'%'}</td>
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
							<td colspan="3">平均比例</td>
							<td >${empty avgBiMap.avgComplete?0:avgBiMap.avgComplete}%</td>
							<td >${empty avgBiMap.avgOutComplete ? 0:avgBiMap.avgOutComplete}%</td>
							<td >${empty avgBiMap.avgAudit?0:avgBiMap.avgAudit}%</td>
						</tr>
					</table>
				</div>
			</div>
			<iframe style="display:none" id="printDivIframe" name="printDivIframe">
				<div id="printDoc">

				</div>
			</iframe>

			<div class="main_bd" id="div_table_2" style="margin-top: 20px">
				<h4 style="margin-bottom: 15px;">考试记录 </h4>
				<table border="0" cellpadding="0" cellspacing="0" class="base_info">
					<colgroup>
						<col width="20%"/>
						<col width="20%"/>
						<col width="20%"/>
						<col width="20%"/>
						<col width="20%"/>
					</colgroup>
					<tr>
						<th style="text-align: center">考试年份</th>
						<th style="text-align: center">考试编号</th>
						<th style="text-align: center">考试类型</th>
						<th style="text-align: center">是否为补考</th>
						<th style="text-align: center">考核结果</th>
					</tr>
					<c:if test="${empty skillList && empty theoryList}">
						<tr>
							<td style="text-align: center" colspan="5">无考试记录！</td>
						</tr>
					</c:if>
					<c:forEach items="${skillList}" var="skill">
						<c:set value="${skill.scoreFlow}" var="Flow"></c:set>
						<tr>
							<td style="text-align: center">${skill.scorePhaseId}</td>
							<td style="text-align: center">${skill.testId}</td>
							<td style="text-align: center">${skill.scoreTypeName}</td>
							<td style="text-align: center">
								<c:if test="${isExamSign[Flow] eq 'Y'}">
									是
								</c:if>
								<c:if test="${isExamSign[Flow] ne 'Y'}">
									否
								</c:if>
							</td>
							<td style="text-align: center">
								<c:if test="${skill.skillScore eq '1'}">合格</c:if>
								<c:if test="${skill.skillScore eq '0'}">不合格</c:if>
							</td>
						</tr>
					</c:forEach>
					<c:forEach items="${theoryList}" var="theory">
						<c:set value="${theory.scoreFlow}" var="Flow"></c:set>
						<tr>
							<td style="text-align: center">${theory.scorePhaseId}</td>
							<td style="text-align: center">${theory.testId}</td>
							<td style="text-align: center">${theory.scoreTypeName}</td>
							<td style="text-align: center">
								<c:if test="${isExamSign[Flow] eq 'Y'}">
									是
								</c:if>
								<c:if test="${isExamSign[Flow] ne 'Y'}">
									否
								</c:if>
							</td>
							<td style="text-align: center">
								<c:if test="${theory.theoryScore eq '1'}">合格</c:if>
								<c:if test="${theory.theoryScore eq '0'}">不合格</c:if>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>

			<div class="main_bd" id="div_table_4" style="margin-top: 20px">
				<h4 style="margin-bottom: 15px;">审核意见 </h4>
				<table border="0" cellpadding="0" cellspacing="0" class="base_info">
					<colgroup>
						<col width="30%"/>
						<col width="30%"/>
						<col width="40%"/>
					</colgroup>
					<tr>
						<th>基地审核意见</th>
						<td>${jsresGraduationApply.localAuditStatusName}</td>
						<td>${jsresGraduationApply.localReason}</td>
					</tr>
					<tr>
						<th>市局审核意见</th>
						<td>${jsresGraduationApply.cityAuditStatusName}</td>
						<td>${jsresGraduationApply.cityReason}</td>
					</tr>
					<tr>
						<th>省厅审核意见</th>
						<td>${jsresGraduationApply.globalAuditStatusName}</td>
						<td>${jsresGraduationApply.globalReason}</td>
					</tr>
				</table>
			</div>

			<div style="text-align: center;padding-top: 20px;padding-bottom: 50px;">
				<c:if test="${not empty doctorRecruit.certificateFlow && jsresGraduationApply.doctorIsApply ne 'Y'}">
					<input type="button" class="btn_green" onclick="doctoCertificateApply('${jsresGraduationApply.applyFlow}');" value="证书申请"/>
				</c:if>
			</div>
		</div>
	</div>
	</div>
</div>
