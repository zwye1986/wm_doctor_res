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
			.attr("style","background-color:#54B2E5;")
			.find("a").attr("style","color: white;");
	$("#toptab .tabli").click(function(){
		$("#toptab .tab_select").addClass("tab").attr("style","")
				.find("a").attr("style","");
		$("#toptab .tab_select").removeClass("tab_select");
		$(this).removeClass("tab");
		$(this).addClass("tab_select").attr("style","background-color:#54B2E5;")
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
<%--$(function () {--%>
	<%--getAsseApplication('${recruitList[0].recruitFlow}','${sessionScope.currUser.userFlow}','"${recruitList[0].auditStatusId}"');--%>
<%--});--%>

function getAsseApplication(recruitFlow, doctorFlow,auditStatusId){
	if("NotPassed" == auditStatusId){
		$("#docTitle").hide();
		$("#noPass").text("培训记录审核不通过！");
		$("#noPass").show();
		$("#doctorContent").hide();
		return;
	}
	if("Auditing" == auditStatusId){
		$("#docTitle").hide();
		$("#noPass").text("培训记录待基地审核！");
		$("#noPass").show();
		$("#doctorContent").hide();
		return;
	}
	if("NotSubmit" == auditStatusId){
		$("#docTitle").hide();
		$("#noPass").text("培训记录未提交！");
		$("#noPass").show();
		$("#doctorContent").hide();
		return;
	}
	if("Passed" == auditStatusId){
		$("#docTitle").show();
	}
	$("#noPass").text("");
	$("#noPass").hide();
	$("#doctorContent").html("");
	$("#doctorContent").show();
	$("#docInfo").removeClass("tab").addClass("tab_select");
	$("#materials").removeClass("tab_select").addClass("tab");
    // jboxStartLoading();
	var url = "<s:url value='/jsres/doctor/getAsseApplication?applyYear=${pdfn:getCurrYear()}&recruitFlow='/>"+recruitFlow + "&doctorFlow=" + doctorFlow;
    // jboxPost(url, "", function (resp) {
    //     $("#doctorContent").html(resp);
    //     // jboxEndLoading();
    // }, null, false);
    jboxPostLoad("doctorContent",url,null,true);
}

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
})

function showImages(deptFlow)
{
    var url = "<s:url value='/jsres/asse/showImages?deptFlow='/>"+deptFlow+"&doctorFlow=${doctorRecruit.doctorFlow}&applyYear=${param.applyYear}";
    jboxOpen(url, "出科考核表",800,500);
}
//提交
function apply2(doctorFlow,applyYear) {
    var recruitFlow = "${recruitFlow}";
    // 备注信息
    var remark = "";
    jboxConfirm("数据更新，请勿多次重新更新！" , function() {
        $("#submitBtn2").hide();
        jboxPost("<s:url value='/jsres/temp/reAsseApply?applyYear='/>"+applyYear+"&doctorFlow=" + doctorFlow +"&recruitFlow=" + recruitFlow + "&remark=" + remark, null, function (resp) {
            if ("1" == resp) {
                jboxTip("更新成功!");
                setTimeout(function(){
                    <%--jboxLoad("doctorContent", "<s:url value='/jsres/doctor/getAsseApplication?applyYear='/>"+applyYear+"&doctorFlow=" + doctorFlow +"&recruitFlow=" + recruitFlow, true);--%>
                    jboxLoad("doctorContent", "<s:url value='/jsres/doctor/asseApplicationMain'/>" , true);
                },1500);
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
        jboxTip("请将培训信息补充完整！");
        return;
    };
    if(!$("#doctorForm").validationEngine("validate")){
    	return false;
	}
    if(${empty userResumeExt.certificateUri}){
        jboxTip("请上传毕业证书附件！");
        return;
    }
    <%--if(${empty userResumeExt.qualificationMaterialUri}){--%>
    <%--jboxTip("请上传医师执业证书附件！");--%>
    <%--return;--%>
    <%--}--%>

    var skillScore = $("[name='skillScore']").val();
    if (skillScore == 1) {
        if (${sysCfgMap['is_public_qualified'] eq 'Y' || empty sysCfgMap['is_public_qualified']}) {
            if (${empty publicScore.skillScore}) {
                if (flag == 'N') {
                    jboxTip("公共科目合格时必须上传公共科目成绩单");
                    return;
                }
            }
        }
    }

    if(${sysCfgMap['is_check_uploaded'] eq 'Y' || empty sysCfgMap['is_check_uploaded']}){
        if(${empty afterImgMap}){
            jboxTip("请上传出科考核表！");
            return;
        }
    }
    var changeSpeId="";
    var changeSpeName="";
    <c:if test="${needChange eq 'Y'}">
    changeSpeId=$("#changeSpeId").val();
    changeSpeName=$("#changeSpeId").find('option:selected').text();
    if(!changeSpeId)
    {
        jboxTip("请选择报考专业！");
        return;
    }
    </c:if>
    var msg="1.请认真核对基本信息；";
    if(!changeSpeName){
        changeSpeName="${doctorRecruit.speName}";
        msg+="<br/>2.当前报名专业为“"+changeSpeName+"”；";
    }else{
        msg+="<br/>2.当前报名专业为“"+changeSpeName+"”，培训专业为“${doctorRecruit.speName}”；"
    }
    msg+="<br/>3.提交后培训数据完成比例将不再统计更新；"+
        "<br/>4.提交后将无法修改，是否确认提交！";
    var recruitFlow = "${recruitFlow}";
    // 备注信息
    var remark = $("#remark").val();
    jboxConfirm(msg , function() {
        $("#submitBtn").hide();
        jboxPost("<s:url value='/jsres/doctor/asseApply?applyYear=${pdfn:getCurrYear()}&doctorFlow='/>" + doctorFlow +"&recruitFlow=" + recruitFlow+"&changeSpeId=" + changeSpeId + "&remark=" + remark, null, function (resp) {
            if ("1" == resp) {
                jboxTip("申请成功!");
                setTimeout(function(){
                    <%--jboxLoad("doctorContent", "<s:url value='/jsres/doctor/getAsseApplication?applyYear=${pdfn:getCurrYear()}&doctorFlow='/>" + doctorFlow +"&recruitFlow=" + recruitFlow, true);--%>
                    jboxLoad("doctorContent", "<s:url value='/jsres/doctor/asseApplicationMain'/>" , true);
                },1500);
            }else	if ("0" == resp) {
                jboxTip("申请失败!");
                $("#submitBtn").show();
            }else	if ("-1" == resp) {
                jboxTip("已提交申请，请勿重复提交!");
                setTimeout(function(){
                    <%--jboxLoad("doctorContent", "<s:url value='/jsres/doctor/getAsseApplication?applyYear=${pdfn:getCurrYear()}&doctorFlow='/>" + doctorFlow +"&recruitFlow=" + recruitFlow, true);--%>
                    jboxLoad("doctorContent", "<s:url value='/jsres/doctor/asseApplicationMain'/>" , true);
                },1500);
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
    var url = "<s:url value='/jsres/doctor/printDoc?applyYear=${pdfn:getCurrYear()}&doctorFlow='/>" + doctorFlow +"&recruitFlow=${recruitFlow}";
    jboxTip("正在准备打印…");
    document.getElementById('printDivIframe').src=url;
}

//培训小结
function guiPeiDetail(recruitFlow)
{
    var url = "<s:url value='/jsres/asse/recruitDetail?applyYear=${pdfn:getCurrYear()}&recruitFlow='/>"+recruitFlow;
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
function saveSkillScore(){
    var recruitFlow = "${recruitFlow}";
    var skillScore = $("select[name='skillScore']").find("option:selected").val();
    if('' == skillScore){
        jboxTip("成绩必选是否合格！")
        return;
    }
    var url = "<s:url value='/jsres/doctor/saveAsseScore'/>";
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

//查看减免证明的图片
function showReductionImg(recordFlow) {
	var url = "<s:url value ='/jsres/doctor/showReductionImg'/>?recordFlow="+recordFlow;
	jboxOpen(url, "减免证明", 700, 500);
}
</script>
<div class="main_hd" >
    <%--<h2 class="underline" style="font-weight: bold;letter-spacing: 2px;">结业申请</h2>--%>
    <%--<div style="background-color: #f4f5f9;" class="title_tab" id="toptab">
        <ul>
            <c:forEach items="${recruitList}" var="recruit">
            	<input type="hidden" class="${recruit.catSpeId}" value="${recruit.speId}"/>
	            <li class="tab tabli" id="li_${recruit.recruitFlow}" onclick="getAsseApplication('${recruit.recruitFlow}','${sessionScope.currUser.userFlow}','${recruit.auditStatusId}');"><a>${recruit.catSpeName}（${empty recruit.speName?'--':recruit.speName}）</a></li>
            </c:forEach>
        </ul>
    </div>
	<c:if test="${not empty recruitList}">
		<div id="docTitle" class="title_tab" style="padding-top: 20px;">
			<ul>
				<script>
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
&lt;%&ndash;				<li id="materials" style="font-size: 12px;height: 35px;" class="tab" onclick="showMaterials(this);"><a style="padding: 10px;">结业报考材料</a></li>&ndash;%&gt;
			</ul>
		</div>
	</c:if>--%>
</div>
<div class="main_bd" id="div_table" id="doctorContent">
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
								&nbsp;${resDoctor.doctorTypeName}
								<c:if test="${empty resDoctor.doctorTypeName}">
									<span style="color: red">未填写！</span>
									<c:set var="canApply" value="N"></c:set>
								</c:if>
							</td>
							<th>培训基地：</th>
							<td>
								&nbsp;${doctorRecruit.orgName}
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
				<div class="main_bd" id="div_table_1" style="padding-top: 15px;" >
					<h4 >报考信息</h4>
					<table border="0" cellpadding="0" cellspacing="0" class="base_info basc_bk" >
						<colgroup>
							<%--						<col width="21%"/>--%>
							<%--						<col width="21%"/>--%>
							<%--						<col width="21%"/>--%>
							<col width="190" />
							<col width="189" />
							<col width="189" />
							<col />
							<%--						<col width="37%"/>--%>
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
							<th>毕业证书编号：</th>
							<td>
								&nbsp;${resDoctor.certificateNo}
								<c:if test="${empty resDoctor.certificateNo}">
									<span style="color: red" >未填写！</span>
									<c:set var="canApply" value="N"></c:set>
								</c:if>
							</td>
						</tr>
						<tr>
							<th>培训开始时间：</th>
							<td>
								&nbsp;${startDate}
								<c:if test="${empty startDate}">
									<span style="color: red" >未填写！</span>
									<c:set var="canApply" value="N"></c:set>
								</c:if>
							</td>
							<th>培训结束时间 ：</th>
							<td>
								&nbsp;${endDate}
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
							<th>报考资格材料编码：</th>
							<td>
								&nbsp;${practicingMap.graduationMaterialCode}
								<c:if test="${empty practicingMap.graduationMaterialCode}">
									<span style="color: red" >&nbsp;未填写！</span>
									<c:set var="canApply" value="N"></c:set>
								</c:if>
							</td>
						</tr>
						<tr>
							<th>取得资格时间：</th>
							<td >
								&nbsp;${practicingMap.graduationMaterialTime}
								<c:if test="${empty practicingMap.graduationMaterialTime}">
									<span>暂无</span>
								</c:if>
							</td>
							<th>执业类型：</th>
							<td>
								<c:forEach items="${dictTypeEnumPracticeTypeList}" var="dictTypeEnumPracticeType">
									<c:if test='${practicingMap.graduationCategoryId eq dictTypeEnumPracticeType.dictId}'>
										&nbsp;${dictTypeEnumPracticeType.dictName}
									</c:if>
								</c:forEach>
								<c:if test="${practicingMap.graduationCategoryId eq '暂无'}">暂无</c:if>
								<c:if test="${empty practicingMap.graduationCategoryId}">
									<span style="color: red" >&nbsp;未填写！</span>
									<c:set var="canApply" value="N"></c:set>
								</c:if>
							</td>
						</tr>
						<tr>
							<th>执业范围：</th>
							<td>
								<c:forEach items="${dictTypeEnumPracticeTypeList}" var="dict">
									<c:set var="dictKey" value="dictTypeEnumPracticeType.${dict.dictId}List"/>
									<c:forEach items="${applicationScope[dictKey]}" var="scope">
										<c:if test='${ practicingMap.graduationScopeId eq scope.dictId and dict.dictId eq practicingMap.graduationCategoryId}'>
											&nbsp;${scope.dictName}
										</c:if>
									</c:forEach>
								</c:forEach>
								<c:if test="${practicingMap.graduationScopeId eq '暂无'}">暂无</c:if>
								<c:if test="${empty practicingMap.graduationScopeId}">
									<span style="color: red" >&nbsp;未填写！</span>
									<c:set var="canApply" value="N"></c:set>
								</c:if>
							</td>
							<th>培训年限：</th>
							<td>
								<c:if test="${empty doctorRecruit.trainYear}">
									<span style="color: red">&nbsp;未填写！</span>
									<c:set var="canApply" value="N"></c:set>
								</c:if>
								<c:if test="${'OneYear' eq doctorRecruit.trainYear}">&nbsp;一年</c:if>
								<c:if test="${'TwoYear' eq doctorRecruit.trainYear}">&nbsp;两年</c:if>
								<c:if test="${'ThreeYear' eq doctorRecruit.trainYear}">&nbsp;三年</c:if>
								<span style="padding-left: 30px">
							<c:if test="${empty doctorRecruit.trainYear ||  'ThreeYear' eq doctorRecruit.trainYear}">
								<%--无减免证明--%>
							</c:if>
							<c:if test="${'TwoYear' eq doctorRecruit.trainYear ||  'OneYear' eq doctorRecruit.trainYear}">
								<c:if test="${'DoctorTrainingSpe' eq doctorRecruit.catSpeId}">
									<c:if test="${empty reduction}">
										&nbsp;无减免证明
									</c:if>
									<c:if test="${not empty reduction}">
										&nbsp;[<a onclick="showReductionImg('${reduction.recordFlow}')" target="_blank">查看减免证明</a>]&nbsp;

									</c:if>
								</c:if>
								<c:if test="${'DoctorTrainingSpe' ne doctorRecruit.catSpeId}">
									&nbsp;无减免证明
								</c:if>
							</c:if>
							</span>
							</td>
						</tr>
						<tr>
							<th>培训登记手册完成情况：</th>
							<td>
								<%--<select <c:if test="${(empty jsresGraduationApply.auditStatusId) or (jsresGraduationApply.auditStatusId eq 'Auditing') or (jsresGraduationApply.auditStatusId eq 'WaitChargePass') or (jsresGraduationApply.auditStatusId eq 'WaitGlobalPass')--%>
							<%--or (jsresGraduationApply.auditStatusId eq 'GlobalPassed')}">disabled="disabled"</c:if>--%>
								<select <c:if test="${(jsresGraduationApply.auditStatusId eq 'Auditing') or (jsresGraduationApply.auditStatusId eq 'WaitChargePass') or (jsresGraduationApply.auditStatusId eq 'WaitGlobalPass')
								or (jsresGraduationApply.auditStatusId eq 'GlobalPassed')}">disabled="disabled"</c:if>
										class="validate[required]" name="registeManua" onchange="saveRegisteManua();">
									<option value="">--请选择--</option>
									<option value="2" <c:if test="${not empty userResumeExt.registeManua and userResumeExt.registeManua eq '2'}">selected="selected"</c:if>>正常</option>
									<option value="1" <c:if test="${not empty userResumeExt.registeManua and userResumeExt.registeManua eq GlobalConstant.PASS}">selected="selected"</c:if>>已完成</option>
									<option value="0" <c:if test="${not empty userResumeExt.registeManua and userResumeExt.registeManua eq GlobalConstant.UNPASS}">selected="selected"</c:if>>未完成</option>
								</select>
							</td>
							<th>实际轮转时间（月）：</th>
							<td>

								&nbsp;<fmt:formatNumber type="number" value="${allMonth}" maxFractionDigits="2"/>
								<c:if test="${empty allMonth}">
									<span style="color: red" >&nbsp;未填写！</span>
									<c:set var="canApply" value="N"></c:set>
								</c:if>
							</td>
						</tr>
						<tr>
							<th>数据填写比例：</th>
							<td>
								&nbsp;${avgBiMap.avgComplete}
								<c:if test="${empty avgBiMap.avgComplete}">
									0
								</c:if>%
							</td>
							<th>数据审核比例：</th>
							<td>
								&nbsp;${avgBiMap.avgAudit}
								<c:if test="${empty avgBiMap.avgAudit}">
									0
								</c:if>%
							</td>
						</tr>
						<tr>
							<th>是否军队人员：</th>
							<td  colspan="3">
								<c:if test="${empty userResumeExt.armyType}">
									<span style="color: red;padding-left: 4px;" >&nbsp;未填写,请到培训信息完善！</span>
									<c:set var="canApply" value="N"></c:set>
								</c:if>
								<c:if test="${not empty userResumeExt.armyType}">
									<span >&nbsp;${pdfn:getArmyTypeEnumName(userResumeExt.armyType)}</span>
								</c:if>
							</td>
						</tr>
<%--						<tr>--%>
<%--							<th>受疫情影响未完成的培训：</th>--%>
<%--							<td  colspan="3">--%>
<%--								<c:choose>--%>
<%--									<c:when test="${'Y'eq apply || ('Y'eq inApplyTime  and  ((jsresGraduationApply.auditStatusId eq 'LocalNotPassed')--%>
<%--									or (jsresGraduationApply.auditStatusId eq 'ChargeNotPassed')--%>
<%--									or (jsresGraduationApply.auditStatusId eq 'JointLocalNotPassed')--%>
<%--														) and not empty jsresGraduationApply.auditStatusId)}">--%>
<%--										&nbsp;<textarea name="remark" id="remark" class="input validate[required,maxSize[150]]" style="width: 85%;height: 100px;margin: 5px;text-indent: 5px;">${jsresGraduationApply.remark}</textarea>--%>
<%--									</c:when>--%>
<%--									<c:otherwise>--%>
<%--										&nbsp;${jsresGraduationApply.remark}--%>
<%--										<c:if test="${empty jsresGraduationApply.remark}">--%>
<%--											<span style="color: red" >未填写！</span>--%>
<%--											<c:set var="canApply" value="N"></c:set>--%>
<%--										</c:if>--%>
<%--									</c:otherwise>--%>
<%--								</c:choose>--%>
<%--							</td>--%>
<%--						</tr>--%>
						</tbody>
					</table>
					<table cellpadding="0" cellspacing="0" class="base_info" >
						<colgroup>
							<col width="21%"/>
							<col width="13.2%"/>
							<col width="13.2%"/>
							<col width="13.2%"/>
							<col width="13.2%"/>
							<col width="13.2%"/>
							<col width="13.2%"/>
						</colgroup>
						<tbody>
						<c:if test="${isAssiGeneral eq 'N'}">
							<tr>
								<th style="text-align: center;" rowspan="2">公共科目成绩</th>
								<th style="text-align: center;">卫生法律和法规</th>
								<th style="text-align: center;">循证医学</th>
								<th style="text-align: center;">临床思维与<br>人际沟通</th>
								<th style="text-align: center;">重点传染病<br>防治知识</th>
								<th style="text-align: center;">是否合格</th>
									<%--							<th style="text-align: center;">附件材料</th>--%>
							</tr>
							<tr>
								<td style=" text-align: center;">
									<input <c:if test="${(jsresGraduationApply.auditStatusId eq 'WaitChargePass') or (jsresGraduationApply.auditStatusId eq 'WaitGlobalPass')
														or (jsresGraduationApply.auditStatusId eq 'GlobalPassed') or publicScore.paperFlow eq 'Y'}">readonly="readonly"</c:if> style="width:100%;height: 100%;text-align: center" name="lawScore"
										   onchange="saveScore('lawScore',this);" value="${extScore.lawScore}"/>
								</td>
								<td style=" text-align: center;">
									<input <c:if test="${(jsresGraduationApply.auditStatusId eq 'WaitChargePass') or (jsresGraduationApply.auditStatusId eq 'WaitGlobalPass')

															or (jsresGraduationApply.auditStatusId eq 'GlobalPassed') or publicScore.paperFlow eq 'Y'}">readonly="readonly"</c:if> style="width:100%;height: 100%;text-align: center" name="medicineScore"
										   onchange="saveScore('medicineScore',this);" value="${extScore.medicineScore}"/>
								</td>
								<td style=" text-align: center;">
									<input <c:if test="${(jsresGraduationApply.auditStatusId eq 'WaitChargePass') or (jsresGraduationApply.auditStatusId eq 'WaitGlobalPass')
													or (jsresGraduationApply.auditStatusId eq 'GlobalPassed') or publicScore.paperFlow eq 'Y'}">readonly="readonly"</c:if> style="width:100%;height: 100%;text-align: center" name="clinicalScore"
										   onchange="saveScore('clinicalScore',this);" value="${extScore.clinicalScore}"/>
								</td>
								<td style=" text-align: center;">
									<input <c:if test="${(jsresGraduationApply.auditStatusId eq 'WaitChargePass') or (jsresGraduationApply.auditStatusId eq 'WaitGlobalPass')
											or (jsresGraduationApply.auditStatusId eq 'GlobalPassed') or publicScore.paperFlow eq 'Y'}">readonly="readonly"</c:if> style="width:100%;height: 100%;text-align: center" name="ckScore"
										   onchange="saveScore('ckScore',this);" value="${extScore.ckScore}"/>
								</td>
								<td style=" text-align: center;">
									<%--<select <c:if test="${(empty jsresGraduationApply.auditStatusId) or (jsresGraduationApply.auditStatusId eq 'Auditing') or (jsresGraduationApply.auditStatusId eq 'WaitChargePass') or (jsresGraduationApply.auditStatusId eq 'WaitGlobalPass')--%>
							<%--or (jsresGraduationApply.auditStatusId eq 'GlobalPassed')}">disabled="disabled"</c:if>--%>
											<%--class="validate[required]" name="skillScore" onchange="saveSkillScore();">--%>
										<%--<option value="">--请选择--</option>--%>
										<%--<option value="1" <c:if test="${publicScore.skillScore eq GlobalConstant.PASS}">selected="selected"</c:if>>合格</option>--%>
										<%--<option value="0" <c:if test="${publicScore.skillScore eq GlobalConstant.UNPASS}">selected="selected"</c:if>>不合格</option>--%>
									<%--</select>--%>
									<input type="text" placeholder="以上传附件为准" readonly="readonly"/>
								</td>
									<%--<td style=" text-align: center;">

                                        <input id="filePath" type="hidden" value="${file.filePath}">
                                            &lt;%&ndash;<c:if test="${empty jsresGraduationApply.auditStatusId}">&ndash;%&gt;
                                            &lt;%&ndash;<c:if test="${ (!((jsresGraduationApply.auditStatusId eq 'WaitChargePass') or (jsresGraduationApply.auditStatusId eq 'WaitGlobalPass')&ndash;%&gt;
                                            &lt;%&ndash;or (jsresGraduationApply.auditStatusId eq 'GlobalPassed')))}">&ndash;%&gt;
                                        <c:if test="${empty file}">
                                                <span id="fileSpan" style="display:none">
                                                    [<a href="${sysCfgMap['upload_base_url']}/${file.filePath}" target="_blank">查看</a>]&nbsp;
                                                </span>
                                            <a name="chooseFile" href="javascript:chooseFile();" >上传</a>&nbsp;
                                            &lt;%&ndash;<span id="fileDelSpan" style="display: none;">&ndash;%&gt;
                                            &lt;%&ndash;<a href="javascript:delFile();">删除</a>&ndash;%&gt;
                                            &lt;%&ndash;</span>&ndash;%&gt;
                                        </c:if>
                                        <c:if test="${not empty file}">
                                                <span id="fileSpan">
                                                    [<a href="${sysCfgMap['upload_base_url']}/${file.filePath}" target="_blank">查看</a>]&nbsp;
                                                </span>
                                            <a name="chooseFile" href="javascript:chooseFile();" >重新上传</a>
                                            &lt;%&ndash;<span id="fileDelSpan">&ndash;%&gt;
                                            &lt;%&ndash;<a href="javascript:delFile();">删除</a>&ndash;%&gt;
                                            &lt;%&ndash;</span>&ndash;%&gt;
                                        </c:if>
                                            &lt;%&ndash;</c:if>&ndash;%&gt;
                                            &lt;%&ndash;<c:if test="${ ((jsresGraduationApply.auditStatusId eq 'WaitChargePass') or (jsresGraduationApply.auditStatusId eq 'WaitGlobalPass')&ndash;%&gt;
                                            &lt;%&ndash;or (jsresGraduationApply.auditStatusId eq 'GlobalPassed'))}">&ndash;%&gt;
                                            &lt;%&ndash;<c:if test="${empty file.filePath}">&ndash;%&gt;
                                            &lt;%&ndash;未上传&ndash;%&gt;
                                            &lt;%&ndash;</c:if>&ndash;%&gt;
                                            &lt;%&ndash;<c:if test="${not empty file.filePath}">&ndash;%&gt;
                                            &lt;%&ndash;<span id="fileSpan">&ndash;%&gt;
                                            &lt;%&ndash;[<a href="${sysCfgMap['upload_base_url']}/${file.filePath}" target="_blank">查看</a>]&nbsp;&ndash;%&gt;
                                            &lt;%&ndash;</span>&ndash;%&gt;
                                            &lt;%&ndash;</c:if>&ndash;%&gt;
                                            &lt;%&ndash;</c:if>&ndash;%&gt;
                                    </td>--%>
							</tr>
						</c:if>
						<c:if test="${isAssiGeneral eq 'Y'}">
							<tr>
								<th style="text-align: center;" rowspan="2" >全科医学及相关理论知识考核</th>
								<th style="text-align: center;" colspan="4">成绩</th>
								<th style="text-align: center;">是否合格</th>
									<%--							<th style="text-align: center;">附件材料</th>--%>
							</tr>
							<tr>
								<td style="text-align: center;" colspan="4">
									<input <c:if test="${(jsresGraduationApply.auditStatusId eq 'WaitChargePass') or (jsresGraduationApply.auditStatusId eq 'WaitGlobalPass')
							or (jsresGraduationApply.auditStatusId eq 'GlobalPassed') or publicScore.paperFlow eq 'Y'}">readonly="readonly"</c:if> style="width:100%;height: 100%;text-align: center" name="theoryScore"
										   onchange="saveScore('theoryScore',this);" value="${publicScore.theoryScore}"/>
								</td>
								<td style=" text-align: center;">
									<%--<select <c:if test="${(jsresGraduationApply.auditStatusId eq 'Auditing') or (jsresGraduationApply.auditStatusId eq 'WaitChargePass') or (jsresGraduationApply.auditStatusId eq 'WaitGlobalPass')--%>
							<%--or (jsresGraduationApply.auditStatusId eq 'GlobalPassed')}">disabled="disabled"</c:if>--%>
											<%--class="validate[required]"  name="skillScore" onchange="saveSkillScore();">--%>
										<%--<option value="">--请选择--</option>--%>
										<%--<option value="1" <c:if test="${publicScore.skillScore eq GlobalConstant.PASS}">selected="selected"</c:if>>合格</option>--%>
										<%--<option value="0" <c:if test="${publicScore.skillScore eq GlobalConstant.UNPASS}">selected="selected"</c:if>>不合格</option>--%>
									<%--</select>--%>
									<input type="text" placeholder="以上传附件为准" readonly="readonly"/>
								</td>
									<%--<td style=" text-align: center;">
                                        <input id="filePath" type="hidden" value="${file.filePath}">
                                            &lt;%&ndash;<c:if test="${empty jsresGraduationApply.auditStatusId or jsresGraduationApply.auditStatusId eq 'Auditing'}">&ndash;%&gt;
                                            &lt;%&ndash;<c:if test="${ ( !((jsresGraduationApply.auditStatusId eq 'WaitChargePass') or (jsresGraduationApply.auditStatusId eq 'WaitGlobalPass')&ndash;%&gt;
                                            &lt;%&ndash;or (jsresGraduationApply.auditStatusId eq 'GlobalPassed')))}">&ndash;%&gt;
                                        <c:if test="${empty file}">
                                                <span id="fileSpan" style="display:none">
                                                    [<a href="${sysCfgMap['upload_base_url']}/${file.filePath}" target="_blank">查看</a>]&nbsp;
                                                </span>
                                            <a name="chooseFile" href="javascript:chooseFile();" >上传</a>&nbsp;
                                            &lt;%&ndash;<span id="fileDelSpan" style="display: none;">&ndash;%&gt;
                                            &lt;%&ndash;<a href="javascript:delFile();">删除</a>&ndash;%&gt;
                                            &lt;%&ndash;</span>&ndash;%&gt;
                                        </c:if>
                                        <c:if test="${not empty file}">
                                                <span id="fileSpan">
                                                    [<a href="${sysCfgMap['upload_base_url']}/${file.filePath}" target="_blank">查看</a>]&nbsp;
                                                </span>
                                            <a name="chooseFile" href="javascript:chooseFile();" >重新上传</a>
                                            &lt;%&ndash;<span id="fileDelSpan">&ndash;%&gt;
                                            &lt;%&ndash;<a href="javascript:delFile();">删除</a>&ndash;%&gt;
                                            &lt;%&ndash;</span>&ndash;%&gt;
                                        </c:if>
                                            &lt;%&ndash;</c:if>&ndash;%&gt;
                                            &lt;%&ndash;<c:if test="${ ((jsresGraduationApply.auditStatusId eq 'WaitChargePass') or (jsresGraduationApply.auditStatusId eq 'WaitGlobalPass')&ndash;%&gt;
                                            &lt;%&ndash;or (jsresGraduationApply.auditStatusId eq 'GlobalPassed'))}">&ndash;%&gt;
                                            &lt;%&ndash;<c:if test="${empty file.filePath}">&ndash;%&gt;
                                            &lt;%&ndash;未上传&ndash;%&gt;
                                            &lt;%&ndash;</c:if>&ndash;%&gt;
                                            &lt;%&ndash;<c:if test="${not empty file.filePath}">&ndash;%&gt;
                                            &lt;%&ndash;<span id="fileSpan">&ndash;%&gt;
                                            &lt;%&ndash;[<a href="${sysCfgMap['upload_base_url']}/${file.filePath}" target="_blank">查看</a>]&nbsp;&ndash;%&gt;
                                            &lt;%&ndash;</span>&ndash;%&gt;
                                            &lt;%&ndash;</c:if>&ndash;%&gt;
                                            &lt;%&ndash;</c:if>&ndash;%&gt;
                                            &lt;%&ndash;</c:if>&ndash;%&gt;
                                            &lt;%&ndash;<c:if test="${not empty jsresGraduationApply.auditStatusId}">&ndash;%&gt;
                                            &lt;%&ndash;<c:if test="${not empty file}">&ndash;%&gt;
                                            &lt;%&ndash;[<a href="${sysCfgMap['upload_base_url']}/${file.filePath}" target="_blank">查看</a>]&ndash;%&gt;
                                            &lt;%&ndash;</c:if>&ndash;%&gt;
                                            &lt;%&ndash;</c:if>&ndash;%&gt;
                                    </td>--%>
							</tr>
						</c:if>
						</tbody>
					</table>
					<%--				<table cellpadding="0" cellspacing="0" class="base_info" >--%>
					<%--					<colgroup>--%>
					<%--						<col width="21%"/>--%>
					<%--						<col width="15.8%"/>--%>
					<%--						<col width="15.8%"/>--%>
					<%--						<col width="15.8%"/>--%>
					<%--						<col width="15.8%"/>--%>
					<%--						<col width="15.8%"/>--%>
					<%--					</colgroup>--%>
					<%--					<tbody>--%>
					<%--&lt;%&ndash;					<c:if test="${isAssiGeneral eq 'N'}">&ndash;%&gt;--%>
					<%--						<tr>--%>
					<%--							<th style="text-align: center;" rowspan="2">公共科目成绩</th>--%>
					<%--							<th style="text-align: center;">卫生法律和法规</th>--%>
					<%--							<th style="text-align: center;">循证医学</th>--%>
					<%--							<th style="text-align: center;">临床思维与<br>人际沟通</th>--%>
					<%--							<th style="text-align: center;">重点传染病<br>防治知识</th>--%>
					<%--							<th style="text-align: center;">是否合格</th>--%>
					<%--						</tr>--%>
					<%--						<tr>--%>
					<%--							<td style=" text-align: center;">--%>
					<%--								<input readonly="readonly" style="width:100%;height: 100%;text-align: center" name="lawScore"--%>
					<%--									   value="${extScore.lawScore}"/>--%>
					<%--							</td>--%>
					<%--							<td style=" text-align: center;">--%>
					<%--								<input readonly="readonly"style="width:100%;height: 100%;text-align: center" name="medicineScore"--%>
					<%--									   value="${extScore.medicineScore}"/>--%>
					<%--							</td>--%>
					<%--							<td style=" text-align: center;">--%>
					<%--								<input readonly="readonly" style="width:100%;height: 100%;text-align: center" name="clinicalScore"--%>
					<%--									    value="${extScore.clinicalScore}"/>--%>
					<%--							</td>--%>
					<%--							<td style=" text-align: center;">--%>
					<%--								<input readonly="readonly" style="width:100%;height: 100%;text-align: center" name="ckScore"--%>
					<%--									    value="${extScore.ckScore}"/>--%>
					<%--							</td>--%>
					<%--							<td style=" text-align: center;">--%>
					<%--								<c:if test="${publicScore.skillScore eq GlobalConstant.PASS}">--%>
					<%--									<input type="hidden" readonly="readonly" style="width:100%;height: 100%;text-align: center"--%>
					<%--										   name="skillScore"--%>
					<%--										   value="1"/>合格--%>
					<%--								</c:if>--%>
					<%--								<c:if test="${publicScore.skillScore eq GlobalConstant.UNPASS}">--%>
					<%--									<input type="hidden" readonly="readonly" style="width:100%;height: 100%;text-align: center"--%>
					<%--										   name="skillScore"--%>
					<%--										   value="0"/>不合格--%>
					<%--								</c:if>--%>
					<%--								<c:if test="${empty publicScore.skillScore }">--%>
					<%--									<input type="hidden" readonly="readonly" style="width:100%;height: 100%;text-align: center"--%>
					<%--										   name="skillScore"--%>
					<%--										   value=""/>--%>
					<%--								</c:if>--%>
					<%--							</td>--%>
					<%--						</tr>--%>

					<%--					</tbody>--%>
					<%--				</table>--%>

					<%--<table cellpadding="0" cellspacing="0" class="base_info" >--%>
					<%--<colgroup>--%>
					<%--<col width="21%"/>--%>
					<%--<col width="79%"/>--%>
					<%--</colgroup>--%>
					<%--<tr>--%>
					<%--<th style="text-align: center;" >结业考核资格审查意见</th>--%>
					<%--<td>${jsresGraduationApply.auditStatusName}</td>--%>
					<%--</tr>--%>
			</form>

			<div class="main_bd"  style="padding-top: 15px;" >
				<h4  style="float:none;">报考材料</h4>
				<div style="display: flex;justify-content: space-between">
					<div class="div_table"  style="padding: 20px 0px 0px">
						<div style="width: 280px;height: 220px">
							<c:if test="${not empty userResumeExt.certificateUri}">
								<%--<a href="${sysCfgMap['upload_base_url']}/${userResumeExt.certificateUri}" target="_blank">--%>
									<%--<img src="${sysCfgMap['upload_base_url']}/${userResumeExt.certificateUri}" style="width: 100%;height: 90%;">--%>
								<%--</a>--%>
								<ul>
									<li class="ratateImg" style="height: 200px;">
										<img src="${sysCfgMap['upload_base_url']}/${userResumeExt.certificateUri}"
											 style="width: 100%;height: 90%;">
									</li>
								</ul>
							</c:if>
							<c:if test="${empty userResumeExt.certificateUri}">
								<span style="margin-top: 100px;margin-left: 110px;">暂未上传</span>
							</c:if>
						</div>
						<h5 style="text-align: center;">毕业证书</h5>
					</div>
					<div class="div_table" style="padding: 20px 0px 0px">
						<div style="width: 280px;height: 220px">
							<c:if test="${userResumeExt.isPassQualifyingExamination eq 	GlobalConstant.FLAG_N }">
								<c:if test="${'暂无' ne practicingMap.graduationMaterialUri}">
									<c:if test="${not empty practicingMap.graduationMaterialUri}">
										<%--<a href="${sysCfgMap['upload_base_url']}/${practicingMap.graduationMaterialUri}"--%>
										   <%--target="_blank">--%>
											<%--<img src="${sysCfgMap['upload_base_url']}/${practicingMap.graduationMaterialUri}"--%>
												 <%--style="width: 100%;height: 90%;">--%>
										<%--</a>--%>
										<ul>
											<li class="ratateImg" style="height: 200px;">
												<img src="${sysCfgMap['upload_base_url']}/${practicingMap.graduationMaterialUri}"
													 style="width: 100%;height: 90%;">
											</li>
										</ul>
									</c:if>
									<c:if test="${empty practicingMap.graduationMaterialUri}">
										<span style="margin-top: 100px;margin-left: 110px;">暂未上传</span>
									</c:if>
								</c:if>
								<c:if test="${'暂无' eq practicingMap.graduationMaterialUri}">
									<span style="margin-top: 100px;margin-left: 110px;">暂未上传</span>
								</c:if>
							</c:if>
							<c:if test="${userResumeExt.isPassQualifyingExamination eq 	GlobalConstant.FLAG_Y }">
								<c:if test="${userResumeExt.isHaveQualificationCertificate eq 	GlobalConstant.FLAG_N }">
									<c:if test="${not  empty userResumeExt.qualificationMaterialId2Url}">
										<%--<a href="${sysCfgMap['upload_base_url']}/${userResumeExt.qualificationMaterialId2Url}"--%>
										   <%--target="_blank">--%>
											<%--<img src="${sysCfgMap['upload_base_url']}/${userResumeExt.qualificationMaterialId2Url}"--%>
												 <%--style="width: 100%;height: 90%;">--%>
										<%--</a>--%>
										<ul>
											<li class="ratateImg" style="height: 200px;">
												<img src="${sysCfgMap['upload_base_url']}/${userResumeExt.qualificationMaterialId2Url}"
													 style="width: 100%;height: 90%;">
											</li>
										</ul>
									</c:if>
									<c:if test="${empty userResumeExt.qualificationMaterialId2Url}">
										<span style="margin-top: 100px;margin-left: 110px;">暂未上传</span>
									</c:if>
								</c:if>
							</c:if>
							<c:if test="${userResumeExt.isPassQualifyingExamination eq 	GlobalConstant.FLAG_Y }">
								<c:if test="${userResumeExt.isHaveQualificationCertificate eq 	GlobalConstant.FLAG_Y }">
									<c:if test="${userResumeExt.isHavePracticingCategory eq 	GlobalConstant.FLAG_N }">
										<c:if test="${not  empty userResumeExt.doctorQualificationCertificateUrl}">
											<%--<a href="${sysCfgMap['upload_base_url']}/${userResumeExt.doctorQualificationCertificateUrl}"--%>
											   <%--target="_blank">--%>
												<%--<img src="${sysCfgMap['upload_base_url']}/${userResumeExt.doctorQualificationCertificateUrl}"--%>
													 <%--style="width: 100%;height: 90%;">--%>
											<%--</a>--%>
											<ul>
												<li class="ratateImg" style="height: 200px;">
													<img src="${sysCfgMap['upload_base_url']}/${userResumeExt.doctorQualificationCertificateUrl}"
														 style="width: 100%;height: 90%;">
												</li>
											</ul>
										</c:if>
										<c:if test="${empty userResumeExt.doctorQualificationCertificateUrl}">
											<span style="margin-top: 100px;margin-left: 110px;">暂未上传</span>
										</c:if>
									</c:if>
								</c:if>
							</c:if>
							<c:if test="${userResumeExt.isPassQualifyingExamination eq 	GlobalConstant.FLAG_Y }">
								<c:if test="${userResumeExt.isHaveQualificationCertificate eq 	GlobalConstant.FLAG_Y }">
									<c:if test="${userResumeExt.isHavePracticingCategory eq 	GlobalConstant.FLAG_Y }">
										<c:if test="${not  empty userResumeExt.doctorPracticingCategoryUrl}">
											<%--<a href="${sysCfgMap['upload_base_url']}/${userResumeExt.doctorPracticingCategoryUrl}"--%>
											   <%--target="_blank">--%>
												<%--<img src="${sysCfgMap['upload_base_url']}/${userResumeExt.doctorPracticingCategoryUrl}"--%>
													 <%--style="width: 100%;height: 90%;">--%>
											<%--</a>--%>
											<ul>
												<li class="ratateImg" style="height: 200px;">
													<img src="${sysCfgMap['upload_base_url']}/${userResumeExt.doctorPracticingCategoryUrl}"
														 style="width: 100%;height: 90%;">
												</li>
											</ul>
										</c:if>
										<c:if test="${empty userResumeExt.doctorPracticingCategoryUrl}">
											<span style="margin-top: 100px;margin-left: 110px;">暂未上传</span>
										</c:if>
									</c:if>
								</c:if>
							</c:if>
						</div>
						<h5 style="text-align: center;">
							<c:if test="${userResumeExt.isPassQualifyingExamination eq 	GlobalConstant.FLAG_N }">
								医师执业证书
							</c:if>
							<c:if test="${userResumeExt.isPassQualifyingExamination eq 	GlobalConstant.FLAG_Y }">
								<c:if test="${userResumeExt.isHaveQualificationCertificate eq 	GlobalConstant.FLAG_N }">
									成绩单
								</c:if>
							</c:if>
							<c:if test="${userResumeExt.isPassQualifyingExamination eq 	GlobalConstant.FLAG_Y }">
								<c:if test="${userResumeExt.isHaveQualificationCertificate eq 	GlobalConstant.FLAG_Y }">
									<c:if test="${userResumeExt.isHavePracticingCategory eq 	GlobalConstant.FLAG_N }">
										医师资格证书
									</c:if>
								</c:if>
							</c:if>
							<c:if test="${userResumeExt.isPassQualifyingExamination eq 	GlobalConstant.FLAG_Y }">
								<c:if test="${userResumeExt.isHaveQualificationCertificate eq 	GlobalConstant.FLAG_Y }">
									<c:if test="${userResumeExt.isHavePracticingCategory eq 	GlobalConstant.FLAG_Y }">
										医师执业证书
									</c:if>
								</c:if>
							</c:if>
						</h5>
					</div>
					<div class="div_table" style="padding: 20px 0px 0px">
						<div style="width: 280px;height: 220px" id="publicImg">
							<c:if test="${not empty file.filePath}">
								<%--<a href="${sysCfgMap['upload_base_url']}/${file.filePath}" target="_blank">--%>
									<%--<img src="${sysCfgMap['upload_base_url']}/${file.filePath}" style="width: 100%;height: 90%;">--%>
								<%--</a>--%>
								<ul>
									<li class="ratateImg" style="height: 200px;">
										<img src="${sysCfgMap['upload_base_url']}/${file.filePath}"
											 style="width: 100%;height: 90%;">
									</li>
								</ul>
								<c:if test="${empty jsresGraduationApply}">
									<a style="margin-left: 110px;" name="chooseFile"
									   href="javascript:chooseFile();">重新上传</a>
								</c:if>
								<c:if test="${not empty jsresGraduationApply}">
									<c:if test="${jsresGraduationApply.auditStatusId eq 'LocalNotPassed' or
								jsresGraduationApply.auditStatusId eq 'ChargeNotPassed' or
							jsresGraduationApply.auditStatusId eq 'GlobalNotPassed'	or
							jsresGraduationApply.auditStatusId eq 'JointLocalNotPassed' or
							jsresGraduationApply.auditStatusId eq 'Black'}">
										<a style="margin-left: 110px;" name="chooseFile"
										   href="javascript:chooseFile();">重新上传</a>
									</c:if>
								</c:if>
							</c:if>
							<c:if test="${ empty file.filePath}">
								<input id="filePath" type="hidden" value="${file.filePath}">
								<c:if test="${empty file}">
									<a  href="javascript:chooseFile();" >
										<img width="280px" height="180px"  src="<s:url value='/jsp/inx/jsres/images/add.png'/>">
									</a>&nbsp;
								</c:if>
							</c:if>
						</div>
						<h5 style="text-align: center;">公共科目成绩</h5>
					</div>
				</div>
			</div>

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
																<%--<a href="${image.imageUrl}" target="_blank"><img width="80px" height="80px"--%>
																												 <%--src="${image.thumbUrl}"></a>&nbsp;--%>
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
																<%--<a href="${image.imageUrl}" target="_blank"><img width="80px" height="80px"--%>
																												 <%--src="${image.thumbUrl}"></a>&nbsp;--%>
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
													<%--<a href="${image.imageUrl}" target="_blank"><img width="80px" height="80px"--%>
																									 <%--src="${image.thumbUrl}"></a>&nbsp;--%>
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
							<c:if test="${doctorRecruit.speId eq '2500' and doctorRecruit.rotationFlow eq '0215d10b29454bdf9b018a7b80b89031'}">
								<fmt:formatNumber type="number" value="33" maxFractionDigits="2"/>
							</c:if>
							<c:if test="${doctorRecruit.speId ne '2500' and doctorRecruit.rotationFlow ne '0215d10b29454bdf9b018a7b80b89031'}">
								<fmt:formatNumber type="number" value="${allSchMonth}" maxFractionDigits="2"/>
							</c:if>
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

			<div class="main_bd" style="padding-top: 15px;">
				<c:if test="${not empty jsresGraduationApply.jointLocalAuditStatusId || not empty jsresGraduationApply.localAuditStatusId
				|| not empty jsresGraduationApply.cityAuditStatusId || not empty jsresGraduationApply.globalAuditStatusId}">
					<h4 style="float:none;">审核意见</h4>
				</c:if>
				<table border="0" cellpadding="0" cellspacing="0"style="border-top:0px; " class="base_info" >
					<colgroup>
						<col width="21%"/>
						<col width="79%"/>
					</colgroup>
					<tbody>
					<c:if test="${not empty jsresGraduationApply.jointLocalAuditStatusId}">
						<tr>
							<th style="text-align: center;">
								协同基地意见
							</th>
							<td colspan="6">${jsresGraduationApply.jointLocalAuditStatusName}
								<c:if test="${not empty jsresGraduationApply.jointLocalReason}">
									(${jsresGraduationApply.jointLocalReason})
								</c:if>
							</td>
						</tr>
					</c:if>
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
							<c:set var="k" value="${jsresGraduationApply.testId}_asse_application"/>
							<c:choose>
								<%-- root 下配置 省厅审核意见可见 需求人：徐开宏 时间：2020年6月24日 禅道需求编码：319--%>
								<c:when test="${ sysCfgMap[k] eq 'Y'}">
									<td colspan="6">${jsresGraduationApply.globalAuditStatusName}
										<c:if test="${not empty jsresGraduationApply.globalReason}">
											(${jsresGraduationApply.globalReason})
										</c:if>
									</td>
								</c:when>
								<c:otherwise >
									<td colspan="6"> 省厅审核中</td>
								</c:otherwise>
							</c:choose>

						</tr>
					</c:if>
					</tbody>
				</table>
			</div>

			<div style="text-align: center;padding-top: 20px;padding-bottom: 50px;">
				<c:if test="${'Y'eq apply}">
					<%--<c:if test="${('ThreeYear' eq doctorRecruit.trainYear and allMonth > 29)--%>
						<%--or ('TwoYear' eq doctorRecruit.trainYear and allMonth > 19)--%>
						<%--or ('OneYear' eq doctorRecruit.trainYear and allMonth > 11)--%>
						<%--and avgBiMap.avgComplete >= 80 and doctorRecruit.catSpeId eq 'DoctorTrainingSpe'}">--%>
					<c:if test="${avgBiMap.avgComplete >= 80 and doctorRecruit.catSpeId eq 'DoctorTrainingSpe'}">
						<input type="button" class="btn_green" id="submitBtn" onclick="apply('${resDoctor.doctorFlow}','${canApply}');"
							   value="提交认证"/>&#12288;&#12288;
					</c:if>

					<c:if test="${doctorRecruit.catSpeId ne 'DoctorTrainingSpe'}">
						<input type="button" class="btn_green" id="submitBtn" onclick="apply('${resDoctor.doctorFlow}','${canApply}');"
							   value="提交认证"/>&#12288;&#12288;
					</c:if>
				</c:if>
				<%-- 禅道需求449
                    1、在某一年度的结业里，基地，市局，审核不通过，可以反复提交 ，审核。省厅 审核不通过 不能重新提交 。但是省厅可以修改审核结果 。
                    2、上一年度参加结业 ，省厅审核不通过 ，可以重新提交数据 。重新走基地、市局 、省厅的审核流程 ，如果有反复提交 按第一层判定走 。
                --%>
				<c:set var="applyTime" value="${'Y'eq inApplyTime }"></c:set>
				<%-- 结业申请审核状态 --%>
				<c:set var="auditStatusId" value="${not empty jsresGraduationApply.auditStatusId
				    and (jsresGraduationApply.auditStatusId eq 'LocalNotPassed') or (jsresGraduationApply.auditStatusId eq 'JointLocalNotPassed')
					or (jsresGraduationApply.auditStatusId eq 'ChargeNotPassed') or (jsresGraduationApply.auditStatusId eq 'Black')}"></c:set>

				<c:if test="${applyTime and  auditStatusId}">
					<input type="button" class="btn_green" id="submitBtn2" onclick="apply2('${resDoctor.doctorFlow}','${doctorRecruit.graduationYear}');" value="重新提交"/>
				</c:if>
				<%--<c:if test="${not empty jsresGraduationApply.globalReason}">--%>
				<%--<input type="button" class="btn_green" id="submitBtn2" onclick="apply2('${resDoctor.doctorFlow}','${jsresGraduationApply.applyYear}');" value="更新提交数据信息"/>--%>
				<%--</c:if>--%>
				<%--<c:if test="${jsresGraduationApply.auditStatusId eq jsResAsseAuditListEnumGlobalPassed.id}">--%>
				<c:if test="${not empty jsresGraduationApply}">
					<input type="button" class="btn_green" onclick="printDoc('${resDoctor.doctorFlow}');" value="打&#12288;印"/>
				</c:if>
				<%--</c:if>--%>
			</div>
		</div>
	</div>
	</div>
</div>
