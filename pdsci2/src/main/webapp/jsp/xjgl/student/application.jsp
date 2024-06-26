
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
</jsp:include>
<style type="text/css">
	.boxHome:HOVER{background-color: #eee;}
	.auditPass{width:129px; text-align: left;}
	.right{text-align: right;}
	.sug{width: 200px; height: 150px;}
	.choos{width: 120px;}
	.time{width:129px; text-align: left;}
	.sp{float:right;padding-right: 4px}
	.wid{width: 100px}
</style>
<script type="text/javascript">
$(document).ready(function(){
	$("#file").live("change",function(){
		uploadFile();
	});
	if("" !="${param.applyTypeId}"){
		type("${param.applyTypeId}");
	}
	if("${param.lookFlag}"!='Y'){
		$(".teachersuggestion").hide();
		$(".trainorgsuggestion").hide();
		$(".nzcdssuggestion").hide();
		$(".nzrdwsuggestion").hide();
		$(".teacherHideSpan").hide();
		$(".trainorgHideSpan").hide();
		$(".nzcdsHideSpan").hide();
		$(".nzrdwHideSpan").hide();
	}else{
		$(".teachersuggestion").removeClass();
		$(".trainorgsuggestion").removeClass();
		$(".nzcdssuggestion").removeClass();
		$(".nzrdwsuggestion").removeClass();
		$(".teacherHideSpan").hide();
		$(".trainorgHideSpan").hide();
		$(".nzcdsHideSpan").hide();
		$(".nzrdwHideSpan").hide();
		$(".teacherOpenSpan").removeClass();
		$(".trainorgOpenSpan").removeClass();
		$(".nzcdsOpenSpan").removeClass();
		$(".nzrdwOpenSpan").removeClass();

	}
});
	function type(obj){
		if(obj=="${userChangeApplyTypeEnumDelayGraduate.id}"){
			$(".condition").hide();
			$("."+"${userChangeApplyTypeEnumLeaveSchool.id}").show();
		}else if(obj=="${userChangeApplyTypeEnumStopStudy.id}"){
			$(".condition").hide();
			$("."+"${userChangeApplyTypeEnumLeaveSchool.id}").show();
			$("."+"${userChangeApplyTypeEnumStopStudy.id}").show();
			$("#stopStudyStart").text("拟休学开始时间：");
			$("#stopStudyEnd").text("拟休学结束时间：");
		}else if(obj=="${userChangeApplyTypeEnumBackStudy.id}"){
			$(".condition").hide();
			$("."+"${userChangeApplyTypeEnumLeaveSchool.id}").show();
			$("."+"${userChangeApplyTypeEnumStopStudy.id}").show();
			$("#stopStudyStart").text("拟复学开始时间：");
			$("#stopStudyEnd").text("拟复学结束时间：");
		}else if(obj=="${userChangeApplyTypeEnumTransferStudy.id}"){
			$(".condition").hide();
			$("."+"${userChangeApplyTypeEnumLeaveSchool.id}").show();
		}else if(obj=="${userChangeApplyTypeEnumAddStudy.id}"){
			$(".condition").hide();
			$("."+"${userChangeApplyTypeEnumDelayStudy.id}").show();
			$("#delayStudyStart").text("拟补修起始时间：");
			$("#delayStudyEnd").text("预计补修时间：");
			$("#delayStudyName").text("补修课程名称：");
		}else if(obj=="${userChangeApplyTypeEnumDelayStudy.id}"){
			$(".condition").hide();
			$("."+"${userChangeApplyTypeEnumDelayStudy.id}").show();
			$("#delayStudyStart").text("拟缓修起始时间：");
			$("#delayStudyEnd").text("预计缓修时间：");
			$("#delayStudyName").text("缓修课程名称：");
		}else{
			$(".condition").hide();
			$("."+obj).show();
		}
	}
	function changeType(obj){
		if(obj=="${userChangeApplyTypeEnumDelayGraduate.id}"){
			$(".condition").hide();
			$("."+"${userChangeApplyTypeEnumLeaveSchool.id}").show();
		}else
		if(obj=="${userChangeApplyTypeEnumStopStudy.id}"){
			$(".condition").hide();
			$("."+"${userChangeApplyTypeEnumLeaveSchool.id}").show();
			$("."+"${userChangeApplyTypeEnumStopStudy.id}").show();
			$("#stopStudyStart").text("拟休学开始时间：");
			$("#stopStudyEnd").text("拟休学结束时间：");
			$("#stopStudyStarTime").val("");
			$("#stopStudyEndTime").val("");
		}else
		if(obj=="${userChangeApplyTypeEnumBackStudy.id}"){
			$(".condition").hide();
			$("."+"${userChangeApplyTypeEnumLeaveSchool.id}").show();
			$("."+"${userChangeApplyTypeEnumStopStudy.id}").show();
			$("#stopStudyStart").text("拟复学开始时间：");
			$("#stopStudyEnd").text("拟复学结束时间：");
			$("#stopStudyStarTime").val("");
			$("#stopStudyEndTime").val("");
		}else
		if(obj=="${userChangeApplyTypeEnumTransferStudy.id}"){
			$(".condition").hide();
			$("."+"${userChangeApplyTypeEnumLeaveSchool.id}").show();
		}else 
		if(obj=="${userChangeApplyTypeEnumAddStudy.id}"){
			$(".condition").hide();
			$("."+"${userChangeApplyTypeEnumDelayStudy.id}").show();
			$("#delayStudyStart").text("拟补修起始时间：");
			$("#delayStudyEnd").text("预计补修时间：");
			$("#delayStudyName").text("补修课程名称：");
			$("#delayStudyTime").val("");
			$("#againStudyTime").val("");
			$("#d").find("option[value='']").attr("selected","selected"); 
		}else
		if(obj=="${userChangeApplyTypeEnumDelayStudy.id}"){
			$(".condition").hide();
			$("."+"${userChangeApplyTypeEnumDelayStudy.id}").show();
			$("#delayStudyStart").text("拟缓修起始时间：");
			$("#delayStudyEnd").text("预计缓修时间：");
			$("#delayStudyName").text("缓修课程名称：");
			$("#delayStudyTime").val("");
			$("#againStudyTime").val("");
			$("#d").find("option[value='']").attr("selected","selected"); 
		}
		else{
			$(".condition").hide();
			$("."+obj).show();
		}
		
	}
	function save(userFlow,recordFlow){
	  if($("#chooseSelect").val()==''){
			return jboxTip("申请类型不能为空");	
		} 
	  if($("#a").val()==''&&$("#chooseSelect").val()=="${userChangeApplyTypeEnumMakeup.id}"){
			return jboxTip("申请补考科目不能为空");	
		} 
	  if($("#b").val()==''&&$("#chooseSelect").val()=="${userChangeApplyTypeEnumChangeTrainType.id}"){
			return jboxTip("拟培养类型不能为空");	
		} 
	  if($("#delayExamTime").val()==''&&$("#chooseSelect").val()=="${userChangeApplyTypeEnumDelayExam.id}"){
			return jboxTip("缓考课程考试时间不能为空");	
		} 
	  if($("#makeUpTime").val()==''&&$("#chooseSelect").val()=="${userChangeApplyTypeEnumDelayExam.id}"){
			return jboxTip("预计补考时间不能为空");	
		} 
	  if($("#c").val()==''&&$("#chooseSelect").val()=="${userChangeApplyTypeEnumDelayExam.id}"){
			return jboxTip("缓考课程名称不能为空");	
		} 
	  if($("#delayStudyTime").val()==''&&$("#chooseSelect").val()=="${userChangeApplyTypeEnumDelayStudy.id}"){
			return jboxTip("缓修起始时间不能为空");	
		} 
	  if($("#againStudyTime").val()==''&&$("#chooseSelect").val()=="${userChangeApplyTypeEnumDelayStudy.id}"){
			return jboxTip("预计复修时间不能为空");	
		} 
	  if($("#d").val()==''&&$("#chooseSelect").val()=="${userChangeApplyTypeEnumDelayStudy.id}"){
			return jboxTip("缓修课程名称不能为空");	
		} 
	  if($("#e").val()==''&&$("#chooseSelect").val()=="${userChangeApplyTypeEnumOutStudy.id}"){
			return jboxTip("目的地不能为空");	
		} 
	  if($("#startTime").val()==''&&$("#chooseSelect").val()=="${userChangeApplyTypeEnumOutStudy.id}"){
			return jboxTip("起始时间不能为空");	
		} 
	  if($("#endTime").val()==''&&$("#chooseSelect").val()=="${userChangeApplyTypeEnumOutStudy.id}"){
			return jboxTip(" 预计结束时间不能为空");	
		} 
	  if($("#stopStudyStarTime").val()==''&&$("#chooseSelect").val()=="${userChangeApplyTypeEnumStopStudy.id}"){
			return jboxTip(" 拟休学开始时间不能为空");	
		} 
	  if($("#stopStudyEndTime").val()==''&&$("#chooseSelect").val()=="${userChangeApplyTypeEnumStopStudy.id}"){
			return jboxTip(" 拟休学结束时间不能为空");	
		} 
	  if($("#f").val()==''&&$("#chooseSelect").val()=="${userChangeApplyTypeEnumChangeMajor.id}"){
			return jboxTip(" 拟转入专业名称不能为空");	
		} 
		if($("#delayExamTime").val()>$("#makeUpTime").val()){
			$("#indexBody").scrollTop("0px");
			return jboxTip("补考时间不能小于考试时间");
		}
		if($("#delayStudyTime").val()>$("#againStudyTime").val()){
			$("#indexBody").scrollTop("0px");
			return jboxTip("缓修起始时间不能小于复修时间");
		}
		if($("#startTime").val()>$("#endTime").val()){
			$("#indexBody").scrollTop("0px");
			return jboxTip("结束时间不能小于起始时间");
		}
		if($("#stopStudyStarTime").val()>$("#stopStudyEndTime").val()){
			$("#indexBody").scrollTop("0px");
			return jboxTip("休学结束时间不能小于休学起始时间");
		}
		var flag = true;
		$("input[id^='changeApplyUrl'][id$='Value']").each(function(){
			if($(this).val() != ""){
				flag = false;//附件不为空
			}
		});
		if(flag && ${empty existApply.content}){
			jboxTip("请上传附件！");
			return false;
		}
		jboxConfirm('确认保存?',function(){
		jboxPost("<s:url value='/xjgl/change/apply/saveApply'/>?recordFlow="+recordFlow+"&userFlow="+userFlow , $("#submitForm").serialize() , function(resp){
			window.parent.frames['mainIframe'].location.reload(true);
		} , null , true);
	});
	}
	function audit(recordFlow,status){
		var v="";
		if(status=="${GlobalConstant.FLAG_Y }"){
			v="通过";
		}else{
			v="不通过";
		}
	jboxConfirm('确认审核'+v+'?',function(){
		jboxPost("<s:url value='/xjgl/change/apply/auditApply'/>?recordFlow="+recordFlow+"&status="+status ,$("#form").serialize()  , function(resp){
				top.document.mainIframe.location.reload();
				jboxClose();
			if("${GlobalConstant.OPRE_SUCCESSED}"==resp){
			}
		} , null , true);
		});
	}
	function print(recordFlow){
		jboxTip("打印中,请稍等...");
		var url = '<s:url value="/xjgl/change/apply/print"/>?recordFlow='+recordFlow;
		window.location.href = url;	
	}
	function add(obj){
		$("."+obj+"suggestion").toggle();
		if($("."+obj+"suggestion").is(":visible")){
			$("."+obj+"OpenSpan").hide();
			$("."+obj+"HideSpan").show();
		}else{
			$("."+obj+"OpenSpan").show();
			$("."+obj+"HideSpan").hide();
		}
	}
	function del(obj){
		$("."+obj+"suggestion").hide();
	}
/**
 * 上传文件
 */
function chooseFile(id){
	$("#upFileId").val(id);
	return $("#file").click();
}
function checkFile(file){
	var filePath = file.value;
	if(filePath==""){
		return false;
	}
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
/**
 * 删除文件
 */
function delFile(fileUrlId) {
	jboxConfirm("确认删除？" , function(){
		$("#"+fileUrlId+"Del").hide();
		$("#"+fileUrlId+"Span").hide();
		$("#"+fileUrlId).val("上传");
		$("#"+fileUrlId+"Value").val("");
		$("#file").val(null);
	});
}
/**
 * 返回文件URL
 */
function returnUrl(filePath){
	var fileUrlId = $("#upFileId").val();
	$('#'+fileUrlId).val("重新上传");
	$('#'+fileUrlId+'Value').val(filePath);
	var filePath = "${sysCfgMap['upload_base_url']}/" + filePath;
	$('#'+fileUrlId+'Del').show();
	$('#'+fileUrlId+'Span').show();
	$('#'+fileUrlId+'Span').find("a").attr('href',filePath);
	$("#file").val(null);//上传成功后清除此文件
}
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
	var url = "<s:url value='/xjgl/change/apply/uploadChangeApply'/>";
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
//添加上传控件
function addPicUrl(tb) {
	if($("#chooseSelect").val() == ""){
		jboxTip("请选择申请类型！");
		return false;
	}
	var cloneTr = $("#" + tb + "Template tr:eq(0)").clone();
	var index = $("#" + tb + "Tb tr").length;
	cloneTr.html(cloneTr.html().htmlFormartById({"index": index}));
	$("#" + tb + "Tb").append(cloneTr);
}
//删除上传控件
function delRegTr(obj) {
	jboxConfirm("确认删除?", function () {
		$(obj).parent('td').parent("tr").remove();
		var reg = new RegExp('\\[\\d+\\]', 'g');
		$("#credentialsTb tr").each(function (i) {
			$("[name]", this).each(function () {
				this.name = this.name.replace(reg, "[" + i + "]");
			});
		});
	});
}
String.prototype.htmlFormartById = function (data) {
	var html = this;
	for (var key in data) {
		var reg = new RegExp('\\{' + key + '\\}', 'g');
		html = html.replace(reg, data[key]);
	}
	return html;
}
//下载申请表格
function downloadTblFile(){
	if($("#chooseSelect").val() == ""){
		jboxTip("请选择申请类型！");
		return false;
	}
	jboxTip("下载中…………");
	var url ="<s:url value='/xjgl/change/apply/downloadTblFile'/>?applyTypeId="+$("#chooseSelect").val();
	window.location.href = url;
}
function instructionShow(){
	var url = "<s:url value='/xjgl/change/apply/uploadInstruction'/>?viewFlag=Y";
	jboxOpen(url ,"上传说明",660,360);
}
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<form id='submitForm' style="position:relative;">
		<input type="hidden" id="upFileId"/>
			<table class="basic" style="width: 100%;margin: 10px 0px;">
				<tr>
					<th colspan="4">
						<span style="float: left;margin-left: 10px">
							<c:if test="${param.viewFlag != GlobalConstant.FLAG_Y }">
								<span class="red"style="float: left;margin-top: 6px;">*</span>
							</c:if>申请类型：</span>
						<span style="float: left;">
							<select id="chooseSelect"class="xlselect choos validate[required]" name="apply.applyTypeId"onchange="changeType(this.value);" <c:if test="${param.viewFlag eq GlobalConstant.FLAG_Y }">style="display: none;"</c:if>>
									<option value="">请选择</option>
									<c:forEach items="${userChangeApplyTypeEnumList}" var="apply">
										<option value="${apply.id}"<c:if test="${apply.id eq param.applyTypeId}">selected="selected"</c:if>>${apply.name}</option>
									</c:forEach>
							</select>
						</span>
						<c:if test="${param.viewFlag eq GlobalConstant.FLAG_Y }">
							<span style="float: left;"> ${existApply.applyTypeName}</span>
						</c:if>
					</th>
				</tr>
				<tr>
					<td width="18%"class="right" style="text-align: right;padding-right: 20px">学&#12288;&#12288;号：</td>
					<td width="35%"><span id="sid">${user.sid}</span></td>
					<td width="18%" class="right" style="text-align: right;padding-right: 20px">姓&#12288;&#12288;名：</td>
					<td width="35%"><span id="userName">${sysUser.userName}</span></td>
				</tr>
				<tr>
					<td style="text-align: right;padding-right: 20px">培养类型：</td>
					<td>${user.trainCategoryName}</td>
					<td style="text-align: right;padding-right: 20px">专&#12288;&#12288;业：</td>
					<td>${user.majorName}
					</td>
				</tr>
				<tr> 
					<td style="text-align: right;padding-right: 20px">性&#12288;&#12288;别：</td>
					<td>${sysUser.sexName}</td>
					<td style="text-align: right;padding-right: 20px">培养单位：</td>
					<td>${doctor.orgName}</td>
				</tr>
				<tr>
					<td style="text-align: right;padding-right: 20px">学位分委员会：</td>
					<td colspan="3">${user.trainOrgName }</td>
				</tr>
				<tr style="display: none;" class="${userChangeApplyTypeEnumChangeTeacher.id} condition">
					<td style="text-align: right;padding-right: 20px">原导师姓名：</td>
					<td>
						<c:if test="${param.viewFlag ne GlobalConstant.FLAG_Y }">
							${user.firstTeacher}<c:if test="${not empty user.firstTeacher && not empty user.secondTeacher}">，</c:if>${user.secondTeacher}
							<input type="hidden" name="oldTutorName" value="${user.firstTeacher}<c:if test="${not empty user.firstTeacher && not empty user.secondTeacher}">，</c:if>${user.secondTeacher}" />
						</c:if>
						<c:if test="${param.viewFlag eq GlobalConstant.FLAG_Y }">${form.oldTutorName}</c:if>
					</td>
					<td style="text-align: right;padding-right: 20px">拟更换导师姓名：</td>
					<td>
						<c:if test="${param.viewFlag ne GlobalConstant.FLAG_Y }"><input type="text" name="changeTutorName" value="${form.changeTutorName}"></c:if>
						<c:if test="${param.viewFlag eq GlobalConstant.FLAG_Y }">${form.changeTutorName}</c:if>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;padding-right: 20px" class="${userChangeApplyTypeEnumChangeTrainType.id} condition">导&#12288;&#12288;师：</td>
					<td colspan="3" class="${userChangeApplyTypeEnumChangeTrainType.id} condition" >
						<span id="teacher">${user.firstTeacher}&nbsp;&nbsp;${user.secondTeacher}
						</span>
					</td>
				</tr>
				<tr style="display: none;" class="${userChangeApplyTypeEnumLeaveSchool.id} condition">
					<td style="text-align: right;padding-right: 20px">层&#12288;&#12288;次：</td>
					<td colspan="3">
						<span >${user.trainTypeName}</span>
					</td>
				</tr>
				<tr style="display: none;" class="${userChangeApplyTypeEnumMakeup.id} condition">
					<td style="text-align: right;padding-right: 20px">
							<c:if test="${param.viewFlag != GlobalConstant.FLAG_Y }">
								<span class="red">*</span>
							</c:if>申请补考科目：</td>
					<td colspan="3">
						<select id="a"  class="xlselect choos validate[required]" name="applyMakeUpCou"  <c:if test="${param.viewFlag eq GlobalConstant.FLAG_Y }">style="display: none;"</c:if>>
							<option value="">请选择</option>
								<c:forEach items="${educourseList}" var="edu">
									<option value="${edu.courseName}" <c:if test="${form.applyMakeUpCou eq edu.courseName}">selected="selected"</c:if>>${edu.courseName}</option>
								</c:forEach>
						</select>
						<c:if test="${param.viewFlag eq GlobalConstant.FLAG_Y }">
							<span>${form.applyMakeUpCou}</span>
						</c:if>
					</td>
				</tr>
				<tr style="display: none;" class="${userChangeApplyTypeEnumChangeTrainType.id} condition">
					<td style="text-align: right;padding-right: 20px">执业医师资格证：</td><td><input name="qualifiedNo" readonly="readonly" value="${doctor.qualifiedNo}" style="border-style: none;"></td>
					<td style="text-align: right;padding-right: 20px">医师资格证号：</td><td><input name="docQualifiedNo" readonly="readonly" value="${doctor.doctorLicenseNo}" style="border-style: none;"></td>
				</tr>
				<tr style="display: none;" class="${userChangeApplyTypeEnumChangeTrainType.id} condition">
					<td style="text-align: right;padding-right: 20px">
						<c:if test="${param.viewFlag != GlobalConstant.FLAG_Y }">
							<span class="red">*</span>
						</c:if>
					拟培养类型：</td>
					<td colspan="3">
						<select id="b" class="xlselect choos validate[required]" name="willTrainType"  <c:if test="${param.viewFlag eq GlobalConstant.FLAG_Y }">style="display: none;"</c:if>>
							<option value="">请选择</option>
							<c:forEach items="${dictTypeEnumTrainCategoryList}" var="tra">
								<option value="${tra.dictName}" <c:if test="${form.willTrainType eq tra.dictName }">selected="selected"</c:if>
								<c:if test="${user.trainCategoryName eq tra.dictName}">style="display: none;"</c:if>>${tra.dictName}</option>
							</c:forEach>
						</select>
						<c:if test="${param.viewFlag eq GlobalConstant.FLAG_Y }">
							<span>${form.willTrainType} </span>
						</c:if>
					</td>
				</tr>
				<tr style="display: none;" class="${userChangeApplyTypeEnumDelayExam.id} condition">
					<td style="text-align: right;padding-right: 20px">
						<c:if test="${param.viewFlag != GlobalConstant.FLAG_Y }">
							<span class="red">*</span>
						</c:if>缓考课程考试时间：</td>
					<td>
					<input id="delayExamTime"value="${form.delayExamTime}" name="delayExamTime"onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"  class="xltext ctime time validate[required]" type="text" <c:if test="${param.viewFlag eq GlobalConstant.FLAG_Y }">style="display: none;"</c:if>/>
						<c:if test="${param.viewFlag eq GlobalConstant.FLAG_Y }">
							<span>${form.delayExamTime}</span>
						</c:if>
					</td>
					<td style="text-align: right;padding-right: 20px">
						<c:if test="${param.viewFlag != GlobalConstant.FLAG_Y }">
							<span class="red">*</span>
						</c:if>预计补考时间：
					</td>
					<td><input id="makeUpTime" name="makeUpTime" value="${form.makeUpTime}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"  class="xltext ctime time validate[required]" type="text" <c:if test="${param.viewFlag eq GlobalConstant.FLAG_Y }">style="display: none;"</c:if>/>
						<c:if test="${param.viewFlag eq GlobalConstant.FLAG_Y }">
							<span>${form.makeUpTime}</span>
						</c:if>
					</td>	
				</tr>
				<tr style="display: none;" class="${userChangeApplyTypeEnumDelayExam.id} condition">
					<td style="text-align: right;padding-right: 20px">
						<c:if test="${param.viewFlag != GlobalConstant.FLAG_Y }">
							<span class="red">*</span>
						</c:if>缓考课程名称：
					</td>
					<td colspan="3">
						<c:if test="${param.viewFlag != GlobalConstant.FLAG_Y}">
							<select id="c"class="xlselect choos validate[required]"  name="delayCourName" style="width: 16%">
								<option value="">请选择</option>
								<c:forEach items="${educourseList}" var="edu">
									<option value="${edu.courseName}" <c:if test="${form.delayCourName eq edu.courseName}">selected="selected"</c:if>>${edu.courseName}</option>
								</c:forEach>
							</select>
						</c:if>
						<c:if test="${param.viewFlag eq GlobalConstant.FLAG_Y}">
							<span>${form.delayCourName}</span>
						</c:if>
					</td>
				</tr>
				<tr style="display: none;" class="${userChangeApplyTypeEnumDelayStudy.id} condition">
					<td style="text-align: right;padding-right: 20px">
							<c:if test="${param.viewFlag != GlobalConstant.FLAG_Y }">
								<span class="red">*</span>
							</c:if><label id="delayStudyStart">缓修起始时间：</label>
					</td>
					<td><input id="delayStudyTime" value="${form.delayStudyTime}" name="delayStudyTime"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"  class="xltext ctime time validate[required] " type="text" <c:if test="${param.viewFlag eq GlobalConstant.FLAG_Y }">style="display: none;"</c:if>/>
						 <c:if test="${param.viewFlag eq GlobalConstant.FLAG_Y }">
							<span>${form.delayStudyTime}</span>
						 </c:if>
					</td>
					<td style="text-align: right;padding-right: 20px">
							<c:if test="${param.viewFlag != GlobalConstant.FLAG_Y }">
								<span class="red">*</span>
							</c:if><label id="delayStudyEnd">预计复修时间：</label>
					</td>
				    <td><input id="againStudyTime" value="${form.againStudyTime}"name="againStudyTime"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" class="xltext ctime time validate[required]" type="text" <c:if test="${param.viewFlag eq GlobalConstant.FLAG_Y }">style="display: none;"</c:if>/>
						<c:if test="${param.viewFlag eq GlobalConstant.FLAG_Y }">
							<span>${form.againStudyTime}</span>	
						</c:if>
					</td>
				</tr>
				<tr style="display: none;" class="${userChangeApplyTypeEnumDelayStudy.id} condition">
					<td style="text-align: right;padding-right: 20px">
						<c:if test="${param.viewFlag != GlobalConstant.FLAG_Y }">
							<span class="red">*</span>
						</c:if><label id="delayStudyName">缓修课程名称：</label>
					</td>
					<td colspan="3">
						  <c:if test="${param.viewFlag != GlobalConstant.FLAG_Y }">
							<select id="d" class="xlselect choos validate[required] " name="delayStudycourName"style="width: 16%">
								<option value="">请选择</option>
								<c:forEach items="${educourseList}" var="edc">
									<option value="${edc.courseName}" <c:if test="${form.delayStudycourName eq edc.courseName}">selected="selected"</c:if>>${edc.courseName}</option>
								</c:forEach>
							</select>
						 </c:if>
						<c:if test="${param.viewFlag eq GlobalConstant.FLAG_Y }">
							<span>${form.delayStudycourName}</span>
						</c:if>
					</td>
				</tr>
				<tr style="display: none;" class="${userChangeApplyTypeEnumOutStudy.id} condition">
					<td style="text-align: right;padding-right: 20px">
							<c:if test="${param.viewFlag != GlobalConstant.FLAG_Y }">
								<span class="red">*</span>
							</c:if>目的地：
					</td>
					<td colspan="3">
						<input id="e" class="inputText validate[required]" name="destination" value="${form.destination}"  
						<c:if test="${param.viewFlag eq GlobalConstant.FLAG_Y }">style="display: none;"</c:if>/>
						<c:if test="${param.viewFlag eq GlobalConstant.FLAG_Y }">
						<span>${form.destination}</span>
						</c:if>
					</td>
				</tr>
				<tr style="display: none;" class="${userChangeApplyTypeEnumOutStudy.id} condition">
					<td style="text-align: right;padding-right: 20px">
						<c:if test="${param.viewFlag != GlobalConstant.FLAG_Y }">
							<span class="red">*</span>
						</c:if>起始时间：
					</td>
					<td><input id="startTime" name="startTime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" value="${form.startTime}"class="xltext ctime time validate[required]" type="text"  <c:if test="${param.viewFlag eq GlobalConstant.FLAG_Y }">style="display: none;"</c:if>/>
						<c:if test="${param.viewFlag eq GlobalConstant.FLAG_Y }">
							<span>${form.startTime}</span>
						</c:if>
					</td>
					<td style="text-align: right;padding-right: 20px">
							<c:if test="${param.viewFlag != GlobalConstant.FLAG_Y }">
								<span class="red">*</span>
							</c:if>预计结束时间：
					</td>
					<td><input id="endTime" value="${form.endTime}"name="endTime"onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"  class="xltext ctime time validate[required]" type="text" <c:if test="${param.viewFlag eq GlobalConstant.FLAG_Y }">style="display: none;"</c:if>/>
						<c:if test="${param.viewFlag eq GlobalConstant.FLAG_Y }">
							<span>${form.endTime}</span>
						</c:if>
					</td>
				</tr>
				<tr style="display: none;" class="${userChangeApplyTypeEnumStopStudy.id} condition">
					<td style="text-align: right;padding-right: 20px">
							<c:if test="${param.viewFlag != GlobalConstant.FLAG_Y }">
								<span class="red">*</span>
							</c:if><label id="stopStudyStart">拟休学开始时间：</label>
					</td>
					<td><input id="stopStudyStarTime"value="${form.stopStudyStarTime}" name="stopStudyStarTime"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"  class="xltext ctime time validate[required]" type="text"  <c:if test="${param.viewFlag eq GlobalConstant.FLAG_Y }">style="display: none;"</c:if>/>
						<c:if test="${param.viewFlag eq GlobalConstant.FLAG_Y }">
							<span>${form.stopStudyStarTime}</span>
						</c:if>
					</td>
					<td style="text-align: right;padding-right: 20px">
						<c:if test="${param.viewFlag != GlobalConstant.FLAG_Y }">
								<span class="red">*</span>
							</c:if><label id="stopStudyEnd">拟休学结束时间：</label>
					</td>
					<td><input id="stopStudyEndTime" name="stopStudyEndTime" value="${form.stopStudyEndTime}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"  class="xltext ctime time validate[required]" type="text" <c:if test="${param.viewFlag eq GlobalConstant.FLAG_Y }">style="display: none;"</c:if>/>
						<c:if test="${param.viewFlag eq GlobalConstant.FLAG_Y }">
							<span>${form.stopStudyEndTime}</span>
						</c:if>
					</td>
				</tr>
				<tr style="display: none;" class="${userChangeApplyTypeEnumChangeMajor.id} condition">
					<td style="text-align: right;padding-right: 20px">
							<c:if test="${param.viewFlag != GlobalConstant.FLAG_Y }">
								<span class="red">*</span>
							</c:if>拟转入专业名称：
					</td>
					<td colspan="3">
						<select id="f" class="xlselect choos validate[required]"name="swichmajorName" <c:if test="${param.viewFlag eq GlobalConstant.FLAG_Y }">style="display: none;"</c:if>>
							<option value="">请选择</option>
								<c:forEach items="${dictTypeEnumMajorList}" var="mr">
									<option value="${mr.dictName}" <c:if test="${form.swichmajorName eq mr.dictName}">selected="selected"</c:if>
									<c:if test="${user.majorName eq mr.dictName}">style="display: none;"</c:if>
									>${mr.dictName}
									</option>
								</c:forEach>
						</select>
						<c:if test="${param.viewFlag eq GlobalConstant.FLAG_Y }">
							<span>${form.swichmajorName}</span>
						</c:if>
					</td>
				</tr>
				<c:if test="${userChangeApplyStatusEnumSave.id eq existApply.statusId || empty existApply.statusId}">
					<tr>
						<td style="text-align: right;padding-right: 20px"><label class="red">*</label>&nbsp;上传附件：</td>
						<td colspan="3">
							添加上传附件&#12288;<img class="opBtn" title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="addPicUrl('picAddress')"/>
							<span style="padding-left:50px"><input type="button" class="search" value="上传说明" onclick="instructionShow();"/></span>
							<span style="padding-left:5px"><input type="button" class="search" value="下载申请表格" onclick="downloadTblFile();"/></span>
						</td>
					</tr>
					<tbody id="picAddressTb">
						<c:forEach var="urlObj" items="${form.changeApplyUrlList}" varStatus="status">
							<tr>
								<td></td>
								<td colspan="3">
									<input type="hidden" id="changeApplyUrl${status.index}Value" name="changeApplyUrlList" value="<c:if test="${not empty urlObj}">${urlObj}</c:if>" />
									<label id="changeApplyUrl${status.index}Span" style="display:${!empty urlObj?'':'none'}">
										<a  type="button" class="search" style="padding: 4px 4px 2px;" href="${sysCfgMap['upload_base_url']}/${urlObj}" target="_blank">查看图片</a>&nbsp;
									</label>
									<input id="changeApplyUrl${status.index}"  type="button" onclick="chooseFile('changeApplyUrl${status.index}');" class="search" value="${empty urlObj?'上&#12288;传':'重新上传'}" />&nbsp;
									<input id="changeApplyUrl${status.index}Del" type="button" onclick="delFile('changeApplyUrl${status.index}');" class="search" style="${empty urlObj?'display:none':''}" value="删&#12288;除"/>
									<img class="opBtn" title="删除" style="cursor: pointer;margin-left:50px;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delRegTr(this);"/>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</c:if>
				<c:if test="${userChangeApplyStatusEnumSave.id != existApply.statusId && not empty existApply.statusId}">
					<c:forEach var="urlObj" items="${form.changeApplyUrlList}" varStatus="status">
						<tr>
							<c:choose>
								<c:when test="${status.first}"><td style="text-align: right;padding-right: 20px">附&#12288;&#12288;件：</td></c:when>
								<c:otherwise><td></td></c:otherwise>
							</c:choose>
							<td colspan="3">
								<label id="changeApplyUrlSpan${status.index}" style="display:${!empty urlObj?'':'none'}">
									<a  type="button" class="search wid" style="padding: 4px 4px 2px;" href="${sysCfgMap['upload_base_url']}/${urlObj}" target="_blank">查看图片</a>&nbsp;
								</label>
							</td>
						</tr>
					</c:forEach>
				</c:if>
				<tr>
					<th style="text-align:left;padding-left: 10px"colspan="4">申请原因 :</th>
				</tr>
				<tr>
					<td colspan="4"style="padding-left: 0px">
						<c:if test="${param.viewFlag != GlobalConstant.FLAG_Y }">
							<textarea class="xltextarea" style="width: 99%; height: 150px;margin: 4px" name="applyReason" placeholder="请输入申请原因" >${form.applyReason}</textarea>
						</c:if>
						<c:if test="${param.viewFlag eq GlobalConstant.FLAG_Y }">
							<span style="margin-left: 1%">${form.applyReason} </span>
						</c:if>
					</td>
				</tr>
			</table>

			<div style="display: none">
				<!--上传附件模板-->
				<table id="picAddressTemplate">
					<tr>
						<td></td>
						<td colspan="3">
							<input type="hidden" id="changeApplyUrl${'{index}'}Value" name="changeApplyUrlList" value="<c:if test="${not empty urlObj}">${urlObj}</c:if>" />
							<label id="changeApplyUrl${'{index}'}Span" style="display:${!empty urlObj?'':'none'}">
								<a type="button" class="search" style="padding: 4px 4px 2px;" href="${sysCfgMap['upload_base_url']}/${urlObj}" target="_blank">查看图片</a>&nbsp;
							</label>
							<input id="changeApplyUrl${'{index}'}"  type="button" onclick="chooseFile('changeApplyUrl${'{index}'}');" class="search" value="${empty urlObj?'上&#12288;传':'重新上传'}"/>&nbsp;
							<input id="changeApplyUrl${'{index}'}Del" type="button" onclick="delFile('changeApplyUrl${'{index}'}');" class="search" style="${empty urlObj?'display:none':''}" value="删&#12288;除"/>
							<img class="opBtn" title="删除" style="cursor: pointer;margin-left:50px;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delRegTr(this);"/>
						</td>
					</tr>
				</table>
			</div>

			<div style="text-align: center;">
				<input type="button"id="saveBtn" class="search min" value="保&#12288;存" onclick="save('${user.userFlow}','${param.recordFlow}');" <c:if test="${param.viewFlag eq GlobalConstant.FLAG_Y }">style="display: none;"</c:if>/>
				<input type="button" class="search" value="关&#12288;闭" onclick="jboxCloseMessager();"<c:if test="${param.viewFlag eq GlobalConstant.FLAG_Y }">style="display: none;"</c:if>/>
			</div>
	  </form>
	  <form id="fileForm" method="post" enctype="multipart/form-data">
		<input type="file" id="file" name="file" accept="${sysCfgMap['inx_image_support_mime']}" style="display: none"/>
	  </form> 
	  <c:if test="${param.auditFlag eq GlobalConstant.FLAG_Y}">
	   <div>
		  <form id='form' style="position:relative;">
	  <div>
	  	<table class="basic" style="width: 100%;margin: 10px 0px;">
	  	     <c:if test="${userChangeApplyTypeEnumDelayExam.id != param.applyTypeId}">
			  	<tr>
					<th colspan="3" style="text-align: left;padding-left: 10px;cursor: pointer;"
						onclick="add('teacher')">
			  			导师意见：
			  			<span class="teacherOpenSpan"<c:if test="${param.lookFlag eq GlobalConstant.FLAG_Y }">style="display: none;"</c:if>>
							<img class="opBtn"  title="弹出" style="cursor: pointer;float: right;margin: 2px;" src="<s:url value="/css/skin/${skinPath}/images/zTreeStandard_03.png" />"onblur="add('teacher')"/>
						</span>
						<span class="teacherHideSpan">
							<img class="opBtn" title="收回" style="cursor: pointer;float: right;margin: 2px;" src="<s:url value='/css/skin/${skinPath}/images/zTreeStandard_05.png'/>" onblur="add('teacher')"/>
						</span>
			  		</th>
			  	</tr>
			  	<tr class="teachersuggestion">
				 	 <td style="padding-left: 0px"colspan="3">
				 		 <c:if test="${param.audit != GlobalConstant.FLAG_Y}">
				    		<textarea class="xltextarea" style="width: 99%; height: 150px;margin: 4px" name="teacherSugg" placeholder="请输入导师意见">${form.teacherSugg}</textarea>
				  		</c:if>
				 		<c:if test="${param.audit eq GlobalConstant.FLAG_Y}">
							 <span style="padding-left: 10px">${form.teacherSugg}</span>
						 </c:if>
				  </td>
			  	</tr>
	         </c:if>
			 <c:if test="${userChangeApplyTypeEnumMakeup.id != param.applyTypeId and
			  userChangeApplyTypeEnumDelayExam.id != param.applyTypeId and
			   userChangeApplyTypeEnumDelayStudy.id != param.applyTypeId and
			    userChangeApplyTypeEnumAddStudy.id != param.applyTypeId}">
			 	<tr>
					<th colspan="3" style="text-align: left;padding-left: 10px;cursor: pointer;"
						onclick="add('trainorg')">
			  			培养单位意见：
			  			<span class="trainorgOpenSpan"<c:if test="${param.lookFlag eq GlobalConstant.FLAG_Y }">style="display: none;"</c:if>>
							<img class="opBtn" title="弹出" style="cursor: pointer;float: right;margin: 2px;"
								 src="<s:url value="/css/skin/${skinPath}/images/zTreeStandard_03.png" />"
								 onblur="add('trainorg')"/>
						</span>
						<span class="trainorgHideSpan">
							<img class="opBtn" title="收回" style="cursor: pointer;float: right;margin: 2px;"
								 src="<s:url value='/css/skin/${skinPath}/images/zTreeStandard_05.png'/>"
								 onblur="add('trainorg')"/>
						</span>
			  		</th>
			  	</tr>
			  	<tr class="trainorgsuggestion">
				  	<td style="padding-left: 0px"colspan="3">
				  		 <c:if test="${param.audit != GlobalConstant.FLAG_Y}">
				   	  		 <textarea class="xltextarea" style="width: 99%; height: 150px;margin: 4px" id="trainOrgSugg" name="trainOrgSugg"placeholder="请输入培养单位意见">${form.trainOrgSugg}</textarea>
				 		 </c:if>
				  		 <c:if test="${param.audit eq GlobalConstant.FLAG_Y}">
				   		  <span style="padding-left: 10px">${form.trainOrgSugg}</span>  
						 </c:if>
				  </td>
			  	</tr>
			  </c:if>
			  <c:if test="${userChangeApplyTypeEnumChangeTeacher.id eq param.applyTypeId or userChangeApplyTypeEnumChangeMajor.id eq param.applyTypeId}">
			  	<tr>
					<th colspan="3" style="text-align: left;padding-left: 10px;cursor: pointer;" onclick="add('nzcds')">
			  			拟转入导师意见：
			  			<span class="nzcdsOpenSpan"<c:if test="${param.lookFlag eq GlobalConstant.FLAG_Y }">style="display: none;"</c:if>>
							<img class="opBtn" title="弹出" style="cursor: pointer;float: right;margin: 2px;"
								 src="<s:url value="/css/skin/${skinPath}/images/zTreeStandard_03.png" />"
								 onblur="add('nzcds')"/>
						</span>
						<span class="nzcdsHideSpan">
							<img class="opBtn" title="收回" style="cursor: pointer;float: right;margin: 2px;"
								 src="<s:url value='/css/skin/${skinPath}/images/zTreeStandard_05.png'/>"
								 onblur="add('nzcds')"/>
						</span>
			  		</th>
			  	</tr>
			  	<tr  class="nzcdssuggestion ">
				  	<td style="padding-left: 0px">
				  	<c:if test="${param.audit != GlobalConstant.FLAG_Y}">
				  	    <textarea class="xltextarea" style="width: 99%; height: 150px;margin: 4px" name="swichTeachSugg"placeholder="请输入拟转入导师意见">${form.swichTeachSugg}</textarea>
				   </c:if>
				   <c:if test="${param.audit eq GlobalConstant.FLAG_Y}">
				   		 <span style="padding-left: 10px">${form.swichTeachSugg}</span>
				   </c:if>
				  	</td>
			  	</tr>
			  	<tr>
					<th colspan="3" style="text-align: left;padding-left: 10px;cursor: pointer;" onclick="add('nzrdw')">
			  			拟转入单位意见：
			  			<span class="nzrdwOpenSpan"<c:if test="${param.lookFlag eq GlobalConstant.FLAG_Y }">style="display: none;"</c:if>>
							<img class="opBtn" title="弹出" style="cursor: pointer;float: right;margin: 2px;"
								 src="<s:url value="/css/skin/${skinPath}/images/zTreeStandard_03.png" />"
								 onblur="add('nzrdw')"/>
						</span>
						<span class="nzrdwHideSpan">
							<img class="opBtn" title="收回" style="cursor: pointer;float: right;margin: 2px;"
								 src="<s:url value='/css/skin/${skinPath}/images/zTreeStandard_05.png'/>"
								 onblur="add('nzrdw')"/>
						</span>
			  		</th>
			  	</tr>
			  	<tr class="nzrdwsuggestion ">
				  	<td style="padding-left: 0px">
				  	<c:if test="${param.audit != GlobalConstant.FLAG_Y}">
				  	    <textarea class="xltextarea" style="width: 99%; height: 150px;margin: 4px" name="switchOrgSugg" placeholder="请输入拟转入单位意见">${form.switchOrgSugg}</textarea>
				  	</c:if>
				  	<c:if test="${param.audit eq GlobalConstant.FLAG_Y}">
				  		 <span style="padding-left: 10px">${form.switchOrgSugg}</span>
				  	</c:if>
				  	</td>
			  	</tr>
			  </c:if>
			    <c:if test="${userChangeApplyTypeEnumMakeup.id != param.applyTypeId}">
			  	 <c:if test="${userChangeApplyTypeEnumOutStudy.id eq param.applyTypeId}">
				  	<tr>
				  		<th style="text-align: center;" rowspan="2"width="11%">研究生学院意见</th>
				  		<td>学生工作部意见：</td>
				  		<td>
					  		<c:if test="${param.audit != GlobalConstant.FLAG_Y}">
				  				<textarea class="xltextarea"  style="width: 99%; height: 100px;margin: 4px;margin-left: 0px" name="studySuggg" placeholder="请输入学工部意见">${form.studySuggg}</textarea>
				  			</c:if>
				  			<c:if test="${param.audit eq GlobalConstant.FLAG_Y}">
				  				 <span style="padding-left: 10px">${form.studySuggg}</span>
				  			</c:if>
			  			</td>
				  	</tr>
				  	<tr>
						<c:set var="curDate" value="${pdfn:getCurrDate()}" />
				  		<td width="7%">培养科意见：</td>
				  		<td colspan="2">
				  			<c:if test="${param.audit != GlobalConstant.FLAG_Y}">
			  					<textarea class="xltextarea"  style="width: 99%; height: 100px;margin: 4px;margin-left: 0px" name="trainSugg"placeholder="请输入培养科意见" >${form.trainSugg}</textarea>
			  				</c:if>
			  				<c:if test="${param.audit eq GlobalConstant.FLAG_Y}">
			  				 	<span style="padding-left: 10px">${form.trainSugg}</span>
			  				</c:if>
				  			<br>
				  			<span class="sp" >
							批准人：
							<input type="text" class="auditPass" name="auditPerson" value="${empty form.auditPerson?sessionScope.currUser.userName:form.auditPerson}"
							<c:if test="${param.lookFlag eq GlobalConstant.FLAG_Y}">style="display: none;"</c:if>/>
						 		 <c:if test="${param.lookFlag eq GlobalConstant.FLAG_Y}">
								 	<span style="width:129px; text-align: left;">${form.auditPerson }</span></c:if>
									&#12288;批准日期：<input name="auditDate" value="${empty form.auditDate?curDate:form.auditDate}" class="auditPass" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"  class="xltext ctime" type="text" <c:if test="${param.lookFlag eq GlobalConstant.FLAG_Y}">style="display: none;"</c:if>>
								<c:if test="${param.lookFlag eq GlobalConstant.FLAG_Y}"><span style="width:129px; text-align: left;">${form.auditDate}</span></c:if>
							</span>
				  		</td>
				  	</tr>
			  	 </c:if>
			  	<tr  <c:if test="${userChangeApplyTypeEnumOutStudy.id eq param.applyTypeId}">style="display: none;"</c:if>>
			  		<th style="text-align: left;padding-left: 10px;" colspan="3" >研究生学院意见：</th>
			  	</tr>
			  	<c:if test="${userChangeApplyTypeEnumOutStudy.id != param.applyTypeId}">
				  	<tr>
					  	<td style="padding-left: 0px" colspan="3">
							<c:if test="${param.audit != GlobalConstant.FLAG_Y}">
					  	    	<textarea class="xltextarea sug"  style="width: 99%; height: 150px;margin: 4px"name="postgraduSchSugg"placeholder="请输入审核意见" <c:if test="${userChangeApplyTypeEnumOutStudy.id eq param.applyTypeId}">style="display: none;"</c:if>>${form.postgraduSchSugg}</textarea>
					  		</c:if>
					  		<c:if test="${param.audit eq GlobalConstant.FLAG_Y}">
					  			 <span style="padding-left: 10px">${form.postgraduSchSugg}</span>
					  		</c:if>
					  		<br>
					  	<span style="float:right;padding-right: 4px">
							批准人：
							<input type="text" class="auditPass" name="auditPerson" value="${empty form.auditPerson?sessionScope.currUser.userName:form.auditPerson}"
							<c:if test="${param.lookFlag eq GlobalConstant.FLAG_Y}">style="display: none;"</c:if>/>
						 		 <c:if test="${param.lookFlag eq GlobalConstant.FLAG_Y}">
								 	<span style="width:129px; text-align: left;">${form.auditPerson }</span></c:if>
									&#12288;批准日期：<input name="auditDate" value="${empty form.auditDate?curDate:form.auditDate}" class="auditPass" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"  class="xltext ctime" type="text" <c:if test="${param.lookFlag eq GlobalConstant.FLAG_Y}">style="display: none;"</c:if>>
								<c:if test="${param.lookFlag eq GlobalConstant.FLAG_Y}"><span style="width:129px; text-align: left;">${form.auditDate}</span></c:if>
						</span>
					  	</td>
				  	</tr>
				  	</c:if>
			 </c:if>
		 </table>
	  </div>

	<table class="basic" style="width: 100%;">
	<c:if test="${userChangeApplyTypeEnumMakeup.id eq param.applyTypeId}">
		<tr>
		<th style="text-align: left;padding-left: 10px;">审核意见：</th></tr>
		<tr>
			<td style="padding-left: 0px">
				<c:if test="${param.lookFlag != GlobalConstant.FLAG_Y}">
					<textarea  class="xltextarea"style="width: 99%; height: 150px;margin: 3px;padding-left: 0px"placeholder="请输入终审意见"name="auditContent"></textarea>
				</c:if>
				<c:if test="${param.lookFlag eq GlobalConstant.FLAG_Y}">
				 	<span style="padding-left: 10px; text-align: left;">${form.auditContent}</span>
				</c:if>
				<br>
				<span style="float:right;padding-right: 15px">
					批准人：
					<input type="text" class="auditPass" name="auditPerson" value="${empty form.auditPerson?sessionScope.currUser.userName:form.auditPerson}"
					<c:if test="${param.lookFlag eq GlobalConstant.FLAG_Y}">style="display: none;"</c:if>/>
				 	 	<c:if test="${param.lookFlag eq GlobalConstant.FLAG_Y}">
							 <span style="width:129px; text-align: left;">${form.auditPerson }</span>
				   		 </c:if>
						&#12288;批准日期：<input name="auditDate" value="${empty form.auditDate?curDate:form.auditDate}" class="auditPass" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"  class="xltext ctime" type="text" <c:if test="${param.lookFlag eq GlobalConstant.FLAG_Y}">style="display: none;"</c:if>>
					<c:if test="${param.lookFlag eq GlobalConstant.FLAG_Y}"><span style="width:129px; text-align: left;">${form.auditDate}</span></c:if>
				</span>
			</td>
		</tr>
		</c:if>
		</table>
		</form>
		<div style="text-align: center;margin-top: 0.8%">
			<input type="button" class="search" value="审核通过" onclick="audit('${param.recordFlow}','${GlobalConstant.FLAG_Y }');"<c:if test="${param.lookFlag eq GlobalConstant.FLAG_Y}">style="display: none;"</c:if>/>
			<input type="button" class="search" value="审核不通过" onclick="audit('${param.recordFlow}','${GlobalConstant.FLAG_N }');"<c:if test="${param.lookFlag eq GlobalConstant.FLAG_Y}">style="display: none;"</c:if>/>
			<input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();"<c:if test="${param.lookFlag eq GlobalConstant.FLAG_Y}">style="display: none;"</c:if>>
		</div>
	  </div>
  </c:if>
</div>
	<c:if test="${param.audit eq GlobalConstant.FLAG_Y or param.lookFlag eq GlobalConstant.FLAG_Y}">
		<div style="text-align: center;">
			<input type="button"class="search" style="text-align: center;width: 7%" value="关&#12288;闭" onclick="jboxClose();">
		</div>
	</c:if>
</div>
</body>
</html>