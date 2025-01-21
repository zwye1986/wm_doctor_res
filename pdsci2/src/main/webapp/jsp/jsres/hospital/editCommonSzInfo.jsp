<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="login" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="basic_bootstrap" value="true" />
	<jsp:param name="bootstrapSelect" value="true"/>
</jsp:include>
<script type="text/javascript"
		src="<s:url value='/js/DatePicker/WdatePicker.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<style type="text/css">

	#boxHome .item:HOVER{background-color: #eee;}

	.text{
		margin-left: 0;
		width: auto;
		height: auto;
		line-height: inherit;
		color: black;
	}

	.selected a{
		padding: 0;
		background: white !important;
	}

	.base_info td a{
		color: black !important;
	}

	.btn-group.bootstrap-select {
		margin: 0 12px 0 4px !important;
		width: 147px !important;
		height: 30px;
	}

	.btn-default {
		background-color: white;
		border-color: black;
	}

	.pull-left {
		float: left !important;
		margin-left: -21px;
	}

	.div_table {
		padding: 5px 30px 0;
		margin-bottom: 0px;
	}

	.vertical-line {
		background-color: lightblue; /* 线的颜色 */
		margin: 0 auto; /* 居中显示 */
	}
</style>
<script type="text/javascript">
	function removeLetter(obj) {
		jboxConfirm("确认删除？" , function(){
			var tr=obj.parentNode.parentNode;
			tr.remove();
			var educationTrs = $(".educationTab").children().length;
			if(educationTrs == 0){
				addEducation("educationTab");
			}else{
				setEducationRadioName("educationTab");
			}
			var trainingTrs = $(".trainingTab").children().length;
			if(trainingTrs == 0){
				addTraining("trainingTab");
			}else{
				setTrainingRadioName("trainingTab");
			}
			var letterTrs = $(".letterTab").children().length;
			if(letterTrs == 0){
				addLetter("letterTab");
			}else{
				setLetterRadioName("letterTab");
			}
		});
	}

	function removeTraining(obj) {
		jboxConfirm("确认删除？" , function(){
			var tr=obj.parentNode.parentNode;
			tr.remove();
			var educationTrs = $(".educationTab").children().length;
			if(educationTrs == 0){
				addEducation("educationTab");
			}else{
				setEducationRadioName("educationTab");
			}
			var trainingTrs = $(".trainingTab").children().length;
			if(trainingTrs == 0){
				addTraining("trainingTab");
			}else{
				setTrainingRadioName("trainingTab");
			}
			var letterTrs = $(".letterTab").children().length;
			if(letterTrs == 0){
				addLetter("letterTab");
			}else{
				setLetterRadioName("letterTab");
			}
		});
	}

	function removeEducation(obj) {
		jboxConfirm("确认删除？" , function(){
			var tr=obj.parentNode.parentNode;
			tr.remove();
			var educationTrs = $(".educationTab").children().length;
			if(educationTrs == 0){
				addEducation("educationTab");
			}else{
				setEducationRadioName("educationTab");
			}
			var trainingTrs = $(".trainingTab").children().length;
			if(trainingTrs == 0){
				addTraining("trainingTab");
			}else{
				setTrainingRadioName("trainingTab");
			}
			var letterTrs = $(".letterTab").children().length;
			if(letterTrs == 0){
				addLetter("letterTab");
			}else{
				setLetterRadioName("letterTab");
			}
		});
	}

	function addLetter(templateId) {
		if (templateId) {
			$('.' + templateId).append($('#' + templateId).clone().attr('id', ''));
			setLetterRadioName(templateId);
		}
	}

	function setLetterRadioName(templateId){
		$('.' + templateId + ' tr').each(function (i, n) {
			$(n).find("span[id='letterFileSpan']").attr("id", "letterFile" + i + "Span");
			$(n).find("a[id='letterFile']").attr("id", "letterFile" + i).attr("href", "javascript:uploadFile('letterFile" + i + "','学历附件');");
			$(n).find("font[id='letterFileF']").attr("id", "letterFile" + i + "F");
			$(n).find("span[id='letterFileDel']").attr("id", "letterFile" + i + "Del");
			$(n).find("img[id='img4']").attr("onclick", "delFile('letterFile" + i + "');");
			$(n).find("input[id='letterFileValue']").attr("id", "letterFile" + i + "Value");
			$(n).find("td[name='index']").text(i + 1);
		});
	}

	function addTraining(templateId) {
		if (templateId) {
			$('.' + templateId).append($('#' + templateId).clone().attr('id', ''));
			setTrainingRadioName(templateId);
		}
	}

	function setTrainingRadioName(templateId){
		$('.' + templateId + ' tr').each(function (i, n) {
			$(n).find("span[id='certificateFileSpan']").attr("id", "certificateFile" + i + "Span");
			$(n).find("a[id='certificateFile']").attr("id", "certificateFile" + i).attr("href", "javascript:uploadFile('certificateFile" + i + "','学历附件');");
			$(n).find("font[id='certificateFileF']").attr("id", "certificateFile" + i + "F");
			$(n).find("span[id='certificateFileDel']").attr("id", "certificateFile" + i + "Del");
			$(n).find("img[id='img3']").attr("onclick", "delFile('certificateFile" + i + "');");
			$(n).find("input[id='certificateFileValue']").attr("id", "certificateFile" + i + "Value");
			$(n).find("td[name='index']").text(i + 1);
		});
	}

	function addEducation(templateId) {
		var trs = $(".educationTab").children().length;
		if (trs > 4) {
			jboxTip("最多允许添加五条教育信息，请确认！");
			return false;
		}
		if (templateId) {
			$('.' + templateId).append($('#' + templateId).clone().attr('id', ''));
			setEducationRadioName(templateId);
		}
	}

	function setEducationRadioName(templateId){
		$('.' + templateId + ' tr').each(function (i, n) {
			$(n).find("span[id='academicBackgroundFileSpan']").attr("id", "academicBackgroundFile" + i + "Span");
			$(n).find("a[id='academicBackgroundFile']").attr("id", "academicBackgroundFile" + i).attr("href", "javascript:uploadFile('academicBackgroundFile" + i + "','学历附件');");
			$(n).find("font[id='academicBackgroundFileF']").attr("id", "academicBackgroundFile" + i + "F");
			$(n).find("span[id='academicBackgroundFileDel']").attr("id", "academicBackgroundFile" + i + "Del");
			$(n).find("img[id='img1']").attr("onclick", "delFile('academicBackgroundFile" + i + "');");
			$(n).find("input[id='academicBackgroundFileValue']").attr("id", "academicBackgroundFile" + i + "Value");
			$(n).find("span[id='academicDegreeFileSpan']").attr("id", "academicDegreeFile" + i + "Span");
			$(n).find("a[id='academicDegreeFile']").attr("id", "academicDegreeFile" + i).attr("href", "javascript:uploadFile('academicDegreeFile" + i + "','学历附件');");
			$(n).find("font[id='academicDegreeFileF']").attr("id", "academicDegreeFile" + i + "F");
			$(n).find("span[id='academicDegreeFileDel']").attr("id", "academicDegreeFile" + i + "Del");
			$(n).find("img[id='img2']").attr("onclick", "delFile('academicDegreeFile" + i + "');");
			$(n).find("input[id='academicDegreeFileValue']").attr("id", "academicDegreeFile" + i + "Value");
			$(n).find("td[name='index']").text(i + 1);
		});
	}

	$(document).ready(function () {
		var educationTrs = $(".educationTab").children().length;
		if(educationTrs == 0){
			addEducation("educationTab");
		}else{
			setEducationRadioName("educationTab");
		}
		var trainingTrs = $(".trainingTab").children().length;
		if(trainingTrs == 0){
			addTraining("trainingTab");
		}else{
			setTrainingRadioName("trainingTab");
		}
		var letterTrs = $(".letterTab").children().length;
		if(letterTrs == 0){
			addLetter("letterTab");
		}else{
			setLetterRadioName("letterTab");
		}
    });

	function saveCommonSzInfo() {
		if ('${roleFlag}' != 'teacher') {
			if(false==$("#excelForm").validationEngine("validate")){
				return false ;
			}
		}

		var url = "<s:url value='/jsres/manage/saveCommonSzInfo'/>";

		// 处理多条教育信息
		var educationTrs = $('.educationTab').children();
		var educationList = [];
		$.each(educationTrs, function (i, n) {
			var educationFlow = $(n).find("input[name='educationFlow']").val();
			var graduationSchool = $(n).find("input[name='graduationSchool']").val();
			var graduationTime = $(n).find("input[name='graduationTime']").val();
			var graduationSpe =  $(n).find("input[name='graduationSpe']").val();
			var academicBackgroundId =  $(n).find("select[name='academicBackgroundId']").find("option:selected").val();
			var academicBackgroundName =  $(n).find("select[name='academicBackgroundId']").find("option:selected").text();
			var academicBackgroundFile = $(n).find("input[name='educationInfo.academicBackgroundFile']").val();
			var academicDegreeId = $(n).find("select[name='academicDegreeId']").find("option:selected").val();
			var academicDegreeName = $(n).find("select[name='academicDegreeId']").find("option:selected").text();
			var academicDegreeFile = $(n).find("input[name='educationInfo.academicDegreeFile']").val();

			var data = {
				"educationFlow" : educationFlow,
				"graduationSchool" : graduationSchool,
				"graduationTime" : graduationTime,
				"graduationSpe" : graduationSpe,
				"academicBackgroundId" : academicBackgroundId,
				"academicBackgroundName" : academicBackgroundName,
				"academicBackgroundFile" : academicBackgroundFile,
				"academicDegreeId" : academicDegreeId,
				"academicDegreeName" : academicDegreeName,
				"academicDegreeFile" : academicDegreeFile
			};
			educationList.push(data);
		});
		$("#educationData").val(JSON.stringify(educationList));

		// 处理多条培训信息
		var trainingTrs = $('.trainingTab').children();
		var trainingList = [];
		$.each(trainingTrs, function (i, n) {
			var trainingFlow = $(n).find("input[name='trainingFlow']").val();
			var trainingYear = $(n).find("input[name='trainingYear']").val();
			var trainingUnit = $(n).find("input[name='trainingUnit']").val();
			var trainingSpeId =  $(n).find("select[name='trainingSpeId']").find("option:selected").val();
			var trainingSpeName =  $(n).find("select[name='trainingSpeId']").find("option:selected").text();
			var certificateLevelId =  $(n).find("select[name='certificateLevelId']").find("option:selected").val();
			var certificateLevelName =  $(n).find("select[name='certificateLevelId']").find("option:selected").text();
			var certificateNo = $(n).find("input[name='certificateNo']").val();
			var certificateTime = $(n).find("input[name='certificateTime']").val();
			var certificateFile = $(n).find("input[name='trainingInfo.certificateFile']").val();

			var data = {
				"trainingFlow" : trainingFlow,
				"trainingYear" : trainingYear,
				"trainingUnit" : trainingUnit,
				"trainingSpeId" : trainingSpeId,
				"trainingSpeName" : trainingSpeName,
				"certificateLevelId" : certificateLevelId,
				"certificateLevelName" : certificateLevelName,
				"certificateNo" : certificateNo,
				"certificateTime" : certificateTime,
				"certificateFile" : certificateFile
			};
			trainingList.push(data);
		});
		$("#trainingData").val(JSON.stringify(trainingList));

		// 处理多条聘书信息
		var letterTrs = $('.letterTab').children();
		debugger;
		var letterList = [];
		$.each(letterTrs, function (i, n) {
			var letterFlow = $(n).find("input[name='letterFlow']").val();
			var letterPeriod = $(n).find("select[name='letterPeriod']").find("option:selected").val();
			var letterStartTime = $(n).find("input[name='letterStartTime']").val();
			var letterEndTime =  $(n).find("input[name='letterEndTime']").val();
			var letterTime =  $(n).find("input[name='letterTime']").val();
			var letterFile = $(n).find("input[name='letterInfo.letterFile']").val();

			var data = {
				"letterFlow" : letterFlow,
				"letterPeriod" : letterPeriod,
				"letterStartTime" : letterStartTime,
				"letterEndTime" : letterEndTime,
				"letterTime" : letterTime,
				"letterFile" : letterFile
			};
			letterList.push(data);
		});
		$("#letterData").val(JSON.stringify(letterList));

		$("#professionalTitleName").val($("select[name='professionalTitleId']").find("option:selected").text());
		$("#technicalPositionName").val($("select[name='technicalPositionId']").find("option:selected").text());

		var data = $('#excelForm').serialize();
		jboxPost(url, data, function(data) {
			if('${GlobalConstant.SAVE_SUCCESSED}'==data){
				jboxTip(data);
				if ('${roleFlag}' != 'teacher') {
					window.parent.search();
					setTimeout(function(){
						doClose();
					}, 1000);
				}
				if ('${roleFlag}' == 'teacher') {
					window.parent.editCommonSzInfo('${sessionScope.currUser.userFlow}');
				}
			} else {
				jboxTip(data);
			}
		},null,true);
	}

    function returnUrl(){
        window.parent.search();
        doClose();
    }

    $(document).ready(function(){
        if ("${GlobalConstant.FLAG_Y}"=="${result}") {
            window.parent.search();
            jboxClose();
        }else if ("${GlobalConstant.FLAG_N}"=="${result}"){
            jboxTip("保存失败");
        }else if("${GlobalConstant.FLAG_N}" == "${certificateNoFlag}"){
        	jboxTip("证书编号不能重复，请核对！");
		}
    });

	function getOrgName() {
		$("#orgName").val($("#orgFlow option:selected").text());
	}

    function searchDeptList(orgFlow){
        jboxPost("<s:url value='/jsres/teacher/searchDeptList'/>?orgFlow="+orgFlow, null, function (resp) {
            $("#deptFlow").empty();
            $("#deptFlow").append("<option value=''>请选择</option>");
            if (null != resp && resp.length > 0) {
                for(var i= 0;i<resp.length;i++){
					$("#deptFlow").append("<option value='" + resp[i].deptFlow + "'>" + resp[i].deptName + "</option>");
                }
            }
        },null,false);
    }

	function uploadFile(type,typeName,fileType) {
		var url = "<s:url value='/jsres/manage/uploadFile'/>?operType="+type+"&fileType="+fileType+"&roleFlag=${roleFlag}";
		<%--var url = "<s:url value='/jsres/doctor/uploadFile'/>?operType="+type+"&fileType="+fileType;--%>
		jboxOpen(url, "上传"+typeName, 500, 185);
	}

	function delFile(type) {
		jboxConfirm("确认删除？" , function(){
			$("#"+type+"Del").hide();
			$("#"+type+"Span").hide();
			$("#"+type).text("上传");
			$("#"+type+"Value").val("");
			$("#"+type+"Se").show();
		});
	}

	function doClose() {
		var openDialog = top.dialog.get('jbox-message-iframe');
		if(openDialog !=null && openDialog.open){
			openDialog.close().remove();
		}
	}

	function teacherApplication() {
		var recordFlow = $("#userFlow").val();
		if(false==$("#excelForm").validationEngine("validate")){
			return false ;
		}
		saveCommonSzInfo();
		var url = "<s:url value='/jsres/statistic/teacherApplication'/>?recordFlow=" + recordFlow;
		jboxOpen(url, "师资申请", 800, 350);
	}

</script>
</head>
<body>

<form id="excelForm" method="post" enctype="multipart/form-data" style="height: 760px;overflow-y: auto">
	<input type="hidden" id="userFlow" name="userFlow" value="${user.userFlow}"/>
	<input type="hidden" id="professionalFlow" name="professionalFlow" value="${professionalInfo.professionalFlow}"/>
	<input type="hidden" id="educationData" name="educationData" />
	<input type="hidden" id="trainingData" name="trainingData" />
	<input type="hidden" id="letterData" name="letterData" />
	<input type="hidden" id="professionalTitleName" name="professionalTitleName" />
	<input type="hidden" id="technicalPositionName" name="technicalPositionName" />
	<c:if test="${roleFlag eq 'teacher'}">
		<input type="hidden" id="teacherLevel" name="teacherLevel" value="${user.teacherLevel}"/>
	</c:if>

	<div class="div_table">
		<h5><svg width="3" height="12" class="vertical-line"></svg>&#12288;个人信息</h5>
		<table border="0" cellspacing="0" cellpadding="0" class="base_info " style="margin-top: 6px">
			<colgroup>
				<col width="23%"/>
				<col width="25%"/>
				<col width="23%"/>
				<col width="25%"/>
			</colgroup>
			<tbody>
			<tr>
				<th style="background-color: #f4f5f9; text-align: center">姓名</th>
				<td style="text-align: center">${user.userName}</td>
				<th style="background-color: #f4f5f9; text-align: center">性别</th>
				<td style="text-align: center">${user.sexName}</td>
			</tr>
			<tr>
				<th style="background-color: #f4f5f9; text-align: center">手机号码</th>
				<td style="text-align: center">${user.userPhone}</td>
				<th style="background-color: #f4f5f9; text-align: center">身份证号</th>
				<td style="text-align: center">${user.idNo}</td>
			</tr>
			<tr>
				<th style="background-color: #f4f5f9; text-align: center">年龄</th>
				<td style="text-align: center">${age}</td>
			</tr>
			</tbody>
		</table>
	</div>

	<div class="div_table" style="margin-top: 10px">
		<h5><svg width="3" height="12" class="vertical-line"></svg>&#12288;教育信息</h5>
		<table class="base_info"  style="margin-top: 6px">
			<tr>
				<th width="6%" style="text-align: center">序号</th>
				<th width="14%" style="text-align: center">毕业院校</th>
				<th width="10%" style="text-align: center">毕业时间</th>
				<th width="10%" style="text-align: center">毕业专业</th>
				<th width="10%" style="text-align: center">学历</th>
				<th width="12%" style="text-align: center">学历附件</th>
				<th width="10%" style="text-align: center">学位</th>
				<th width="12%" style="text-align: center">学位附件</th>
				<th width="16%" style="text-align: center">
					操作<font color="#54B2E5" style="cursor: pointer;" onclick="addEducation('educationTab')">【新增】</font>
				</th>
			</tr>
			<tbody class="educationTab">
			<c:forEach items="${educationInfoList}" var="educationInfo" varStatus="status">
				<tr style="height:20px ">
					<td style="display: none"><input type="hidden" value="${educationInfo.educationFlow}" name="educationFlow" /></td>
					<td name="index" style="text-align: center">
							${status.index + 1}
					</td>
					<td style="text-align: center">
						<input type="text" class="select validate[required]" style="text-align: left;width: 90%;" value="${educationInfo.graduationSchool}" placeholder="请输入" name="graduationSchool" />
					</td>
					<td style="text-align: center">
						<input type="text" readonly class="select validate[required]" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="text-align: left;width: 90%;" value="${educationInfo.graduationTime}" name="graduationTime" />
					</td>
					<td style="text-align: center">
						<input type="text" class="select validate[required]" style="text-align: left;width: 90%;" value="${educationInfo.graduationSpe}" placeholder="请输入" name="graduationSpe" />
					</td>
					<td style="text-align: center">
						<select name="academicBackgroundId" class="select validate[required]" style="width: 90%;">
							<option value="" >请选择学历</option>
							<option value="3" <c:if test="${educationInfo.academicBackgroundId eq '3'}">selected="selected"</c:if>>博士研究生</option>
							<option value="2" <c:if test="${educationInfo.academicBackgroundId eq '2'}">selected="selected"</c:if>>硕士研究生</option>
							<option value="1" <c:if test="${educationInfo.academicBackgroundId eq '1'}">selected="selected"</c:if>>本科</option>
						</select>
					</td>
					<td style="text-align: center">
						<span id="academicBackgroundFileSpan" style="display:${!empty educationInfo.academicBackgroundFile?'':'none'} ">
							&nbsp; <a href="${sysCfgMap['upload_base_url']}/${educationInfo.academicBackgroundFile}" target="_blank"><font color="#54B2E5">查看</font></a>&nbsp;
							</span>
						<a id="academicBackgroundFile" href="javascript:uploadFile('academicBackgroundFile','学历附件');" style="margin-left: 2px"><font id="academicBackgroundFileF" color="#54B2E5">${empty educationInfo.academicBackgroundFile?'':'重新'}上传</font></a>&nbsp;
						<span id="academicBackgroundFileDel" href="javascript:void(0)" style="${empty educationInfo.academicBackgroundFile?'display:none':''}">
									<img id="img1" class="opBtn" title="删除" src="<s:url value="/css/skin/${skinPath}/images/del1.png" />"
										 style="cursor: pointer;" onclick="delFile('academicBackgroundFile');" />
								</span>
						<input type="hidden" id="academicBackgroundFileValue"  name="educationInfo.academicBackgroundFile" value="${educationInfo.academicBackgroundFile}" />
					</td>
					<td style="text-align: center">
						<select name="academicDegreeId" class="select validate[required]" style="width: 90%;">
							<option value="" >请选择学位</option>
							<option value="3" <c:if test="${educationInfo.academicDegreeId eq '3'}">selected="selected"</c:if>>博士学位</option>
							<option value="2" <c:if test="${educationInfo.academicDegreeId eq '2'}">selected="selected"</c:if>>硕士学位</option>
							<option value="1" <c:if test="${educationInfo.academicDegreeId eq '1'}">selected="selected"</c:if>>学士学位</option>
						</select>
					</td>
					<td style="text-align: center">
						<span id="academicDegreeFileSpan" style="display:${!empty educationInfo.academicDegreeFile?'':'none'} ">
							&nbsp; <a href="${sysCfgMap['upload_base_url']}/${educationInfo.academicDegreeFile}" target="_blank"><font color="#54B2E5">查看</font></a>&nbsp;
							</span>
						<a id="academicDegreeFile" href="javascript:uploadFile('academicDegreeFile','学位附件');" style="margin-left: 2px"><font id="academicDegreeFileF" color="#54B2E5">${empty educationInfo.academicDegreeFile?'':'重新'}上传</font></a>&nbsp;
						<span id="academicDegreeFileDel" href="javascript:void(0)" style="${empty educationInfo.academicDegreeFile?'display:none':''}">
									<img id="img2" class="opBtn" title="删除" src="<s:url value="/css/skin/${skinPath}/images/del1.png" />"
										 style="cursor: pointer;" onclick="delFile('academicDegreeFile');" />
								</span>
						<input type="hidden" id="academicDegreeFileValue"  name="educationInfo.academicDegreeFile" value="${educationInfo.academicDegreeFile}" />
					</td>
					<td style="text-align: center">
						<a onclick="removeEducation(this)"><font color="red" >删除</font></a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>

	<div class="div_table" style="margin-top: 10px">
		<h5><svg width="3" height="12" class="vertical-line"></svg>&#12288;职称信息</h5>
		<table border="0" cellspacing="0" cellpadding="0" class="base_info "  style="margin-top: 6px">
			<colgroup>
				<col width="23%"/>
				<col width="25%"/>
				<col width="23%"/>
				<col width="25%"/>
			</colgroup>
			<tbody>
			<tr>
				<th style="background-color: #f4f5f9; text-align: center">师资级别</th>
				<td style="text-align: center">
					<select name="teacherLevel" <c:if test="${roleFlag eq 'teacher'}">disabled </c:if> class="select" style="width: 90%;">
						<c:if test="${roleFlag eq 'teacher'}">
							<option value="">无</option>
						</c:if>
						<option value="一般师资" <c:if test="${user.teacherLevel eq '一般师资'}">selected="selected"</c:if>>一般师资</option>
						<option value="骨干师资" <c:if test="${user.teacherLevel eq '骨干师资'}">selected="selected"</c:if>>骨干师资</option>
					</select>
				</td>
				<th style="background-color: #f4f5f9; text-align: center">职称</th>
				<td style="text-align: center">
					<select name="professionalTitleId" class="select validate[required]" style="width: 90%;">
						<option value="" >请选择</option>
						<option value="1" <c:if test="${professionalInfo.professionalTitleId eq '1'}">selected="selected"</c:if>>正高级职称</option>
						<option value="2" <c:if test="${professionalInfo.professionalTitleId eq '2'}">selected="selected"</c:if>>副高级职称</option>
						<option value="3" <c:if test="${professionalInfo.professionalTitleId eq '3'}">selected="selected"</c:if>>中级职称</option>
						<option value="4" <c:if test="${professionalInfo.professionalTitleId eq '4'}">selected="selected"</c:if>>初级职称</option>
					</select>
				</td>
			</tr>
			<tr>
				<th style="background-color: #f4f5f9; text-align: center">专业技术职务</th>
				<td style="text-align: center">
					<select name="technicalPositionId" class="select validate[required]" style="width: 90%;">
						<option value="" >请选择</option>
						<option value="chiefPhysician" <c:if test="${professionalInfo.technicalPositionId eq 'chiefPhysician'}">selected="selected"</c:if>>主任医师</option>
						<option value="associateChiefPhysician" <c:if test="${professionalInfo.technicalPositionId eq 'associateChiefPhysician'}">selected="selected"</c:if>>副主任医师</option>
						<option value="physicianInCharge" <c:if test="${professionalInfo.technicalPositionId eq 'physicianInCharge'}">selected="selected"</c:if>>主治医师</option>
						<option value="seniorTechnologist" <c:if test="${professionalInfo.technicalPositionId eq 'seniorTechnologist'}">selected="selected"</c:if>>主任技师</option>
						<option value="deputyChiefTechnician" <c:if test="${professionalInfo.technicalPositionId eq 'deputyChiefTechnician'}">selected="selected"</c:if>>副主任技师</option>
						<option value="attendingTechnician" <c:if test="${professionalInfo.technicalPositionId eq 'attendingTechnician'}">selected="selected"</c:if>>主管技师</option>
					</select>
				</td>
				<th style="background-color: #f4f5f9; text-align: center">专业技术职务任职时间</th>
				<td style="text-align: center">
					<input type="text" readonly class="select validate[required]" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="text-align: left;width: 90%;" value="${professionalInfo.technicalPositionTime}" placeholder="请选择" name="technicalPositionTime" />
				</td>
			</tr>
			<tr>
				<th style="background-color: #f4f5f9; text-align: center">从事临床教学工作时间</th>
				<td style="text-align: center">
					<input type="text" readonly class="select validate[required]" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="text-align: left;width: 90%;" value="${professionalInfo.clinicalTeachingTime}" placeholder="请选择" name="clinicalTeachingTime" />
				</td>
			</tr>
			</tbody>
		</table>
	</div>

	<div class="div_table" style="margin-top: 10px">
		<h5><svg width="3" height="12" class="vertical-line"></svg>&#12288;师资培训记录</h5>
		<table class="base_info"  style="margin-top: 6px">
			<tr>
				<th width="6%" style="text-align: center">序号</th>
				<th width="10%" style="text-align: center">培训年份</th>
				<th width="14%" style="text-align: center">培训单位</th>
				<th width="10%" style="text-align: center">培训专业</th>
				<th width="10%" style="text-align: center">证书等级</th>
				<th width="12%" style="text-align: center">证书编号</th>
				<th width="10%" style="text-align: center">证书取得时间</th>
				<th width="12%" style="text-align: center">证书附件</th>
				<th width="16%" style="text-align: center">
					操作<font color="#54B2E5" style="cursor: pointer;" onclick="addTraining('trainingTab')">【新增】</font>
				</th>
			</tr>
			<tbody class="trainingTab">
			<c:forEach items="${trainingInfoList}" var="trainingInfo" varStatus="status">
				<tr style="height:20px ">
					<td style="display: none"><input type="hidden" value="${trainingInfo.trainingFlow}" name="trainingFlow" /></td>
					<td name="index" style="text-align: center">
							${status.index + 1}
					</td>
					<td style="text-align: center">
						<input type="text" readonly class="select validate[required]" onclick="WdatePicker({dateFmt:'yyyy'})" style="text-align: left;width: 90%;" value="${trainingInfo.trainingYear}" name="trainingYear" />
					</td>
					<td style="text-align: center">
						<input type="text" class="select validate[required]" style="text-align: left;width: 90%;" value="${trainingInfo.trainingUnit}" placeholder="请输入" name="trainingUnit" />
					</td>
					<td style="text-align: center">
						<select name="trainingSpeId" class="select validate[required]" style="width: 90%;">
							<option value="" >选择专业</option>
							<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
								<option <c:if test="${trainingInfo.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
							</c:forEach>
						</select>
					</td>
					<td style="text-align: center">
						<select name="certificateLevelId" class="select validate[required]" style="width: 90%;">
							<option value="" >请选择</option>
							<option value="1" <c:if test="${trainingInfo.certificateLevelId eq '1'}">selected="selected"</c:if>>国家级</option>
							<option value="2" <c:if test="${trainingInfo.certificateLevelId eq '2'}">selected="selected"</c:if>>省级</option>
							<option value="3" <c:if test="${trainingInfo.certificateLevelId eq '3'}">selected="selected"</c:if>>市级</option>
							<option value="4" <c:if test="${trainingInfo.certificateLevelId eq '4'}">selected="selected"</c:if>>院级</option>
						</select>
					</td>
					<td style="text-align: center">
						<input type="text" class="select validate[required]" style="text-align: left;width: 90%;" value="${trainingInfo.certificateNo}" name="certificateNo" placeholder="请输入" />
					</td>
					<td style="text-align: center">
						<input type="text" readonly class="select validate[required]" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="text-align: left;width: 90%;" value="${trainingInfo.certificateTime}" name="certificateTime" />
					</td>
					<td style="text-align: center">
						<span id="certificateFileSpan" style="display:${!empty trainingInfo.certificateFile?'':'none'} ">
							&nbsp; <a href="${sysCfgMap['upload_base_url']}/${trainingInfo.certificateFile}" target="_blank"><font color="#54B2E5">查看</font></a>&nbsp;
							</span>
						<a id="certificateFile" href="javascript:uploadFile('certificateFile','证书附件');" style="margin-left: 2px"><font id="certificateFileF" color="#54B2E5">${empty trainingInfo.certificateFile?'':'重新'}上传</font></a>&nbsp;
						<span id="certificateFileDel" href="javascript:void(0)" style="${empty trainingInfo.certificateFile?'display:none':''}">
									<img id="img3" class="opBtn" title="删除" src="<s:url value="/css/skin/${skinPath}/images/del1.png" />"
										 style="cursor: pointer;" onclick="delFile('certificateFile');" />
								</span>
						<input type="hidden" id="certificateFileValue"  name="trainingInfo.certificateFile" value="${trainingInfo.certificateFile}" />
					</td>
					<td style="text-align: center">
						<a onclick="removeTraining(this)"><font color="red" >删除</font></a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>

	<div class="div_table" style="margin-top: 10px">
		<h5><svg width="3" height="12" class="vertical-line"></svg>&#12288;师资聘书记录</h5>
		<table class="base_info"  style="margin-top: 6px">
			<tr>
				<th width="6%" style="text-align: center">序号</th>
				<th width="14%" style="text-align: center">聘书有效期</th>
				<th width="16%" style="text-align: center">开始时间</th>
				<th width="16%" style="text-align: center">结束时间</th>
				<th width="16%" style="text-align: center">聘书取得时间</th>
				<th width="16%" style="text-align: center">证书附件</th>
				<th width="16%" style="text-align: center">
					操作<font color="#54B2E5" style="cursor: pointer;" onclick="addLetter('letterTab')">【新增】</font>
				</th>
			</tr>
			<tbody class="letterTab">
			<c:forEach items="${letterInfoList}" var="letterInfo" varStatus="status">
				<tr style="height:20px ">
					<td style="display: none"><input type="hidden" value="${letterInfo.letterFlow}" name="letterFlow" /></td>
					<td name="index" style="text-align: center">
							${status.index + 1}
					</td>
					<td style="text-align: center">
						<select name="letterPeriod" class="select validate[required]" style="width: 90%;">
							<option value="有限时长" <c:if test="${letterInfo.letterPeriod eq '有限时长'}">selected="selected"</c:if>>有限时长</option>
							<option value="长期" <c:if test="${letterInfo.letterPeriod eq '长期'}">selected="selected"</c:if>>长期</option>
						</select>
					</td>
					<td style="text-align: center">
						<input type="text" readonly class="select validate[required]" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="text-align: left;width: 90%;" value="${letterInfo.letterStartTime}" placeholder="请选择" name="letterStartTime" />
					</td>
					<td style="text-align: center">
						<input type="text" readonly class="select" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="text-align: left;width: 90%;" value="${letterInfo.letterEndTime}" placeholder="请选择" name="letterEndTime" />
					</td>
					<td style="text-align: center">
						<input type="text" readonly class="select validate[required]" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="text-align: left;width: 90%;" value="${letterInfo.letterTime}" placeholder="请选择" name="letterTime" />
					</td>
					<td style="text-align: center">
						<span id="letterFileSpan" style="display:${!empty letterInfo.letterFile?'':'none'} ">
							&nbsp; <a href="${sysCfgMap['upload_base_url']}/${letterInfo.letterFile}" target="_blank"><font color="#54B2E5">查看</font></a>&nbsp;
							</span>
						<a id="letterFile" href="javascript:uploadFile('letterFile','证书附件');" style="margin-left: 2px"><font id="letterFileF" color="#54B2E5">${empty letterInfo.letterFile?'':'重新'}上传</font></a>&nbsp;
						<span id="letterFileDel" href="javascript:void(0)" style="${empty letterInfo.letterFile?'display:none':''}">
									<img id="img4" class="opBtn" title="删除" src="<s:url value="/css/skin/${skinPath}/images/del1.png" />"
										 style="cursor: pointer;" onclick="delFile('letterFile');" />
								</span>
						<input type="hidden" id="letterFileValue"  name="letterInfo.letterFile" value="${letterInfo.letterFile}" />
					</td>
					<td style="text-align: center">
						<a onclick="removeLetter(this)"><font color="red" >删除</font></a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</form>
<div style="text-align: center; margin-top: 10px;">
	<input type="button" onclick="saveCommonSzInfo();" class="btn_green" value="保&#12288;存"/>
	<c:if test="${roleFlag eq 'teacher' and user.teacherLevel ne '骨干师资' and (empty teacherTraining or teacherTraining.applicationAuditStatus eq 'HeadNotPassed' or teacherTraining.applicationAuditStatus eq 'NotPassed' or teacherTraining.applicationAuditStatus eq 'Passed')}">
		<input type="button" onclick="teacherApplication();" class="btn_green" value="师资申请"/>
	</c:if>
	<c:if test="${not empty teacherTraining.applicationTeacherLevelId and teacherTraining.applicationAuditStatus ne 'HeadNotPassed' and teacherTraining.applicationAuditStatus ne 'NotPassed' and teacherTraining.applicationAuditStatus ne 'Passed'}">
		<input type="button" class="btn_grey" style="background-color: lightgrey" value="师资申请审核中"/>
	</c:if>
	<c:if test="${roleFlag ne 'teacher'}">
		<input type="button" class="btn_green" value="取&#12288;消" onclick="doClose();"/>
	</c:if>
</div>

<table style="display: none;">
	<tr id="educationTab">
		<td name="index" style="text-align: center"></td>
		<td style="text-align: center">
			<input type="text" class="select validate[required]" style="text-align: left;width: 90%;" value="" placeholder="请输入" name="graduationSchool" />
		</td>
		<td style="text-align: center">
			<input type="text" readonly class="select validate[required]" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="text-align: left;width: 90%;" value="" placeholder="请选择" name="graduationTime" />
		</td>
		<td style="text-align: center">
			<input type="text" class="select validate[required]" style="text-align: left;width: 90%;" placeholder="请输入" value="" name="graduationSpe" />
		</td>
		<td style="text-align: center">
			<select name="academicBackgroundId" class="select validate[required]" style="width: 90%;">
				<option value="" >请选择学历</option>
				<option value="3" <c:if test="${param.academicBackgroundId eq '3'}">selected="selected"</c:if>>博士研究生</option>
				<option value="2" <c:if test="${param.academicBackgroundId eq '2'}">selected="selected"</c:if>>硕士研究生</option>
				<option value="1" <c:if test="${param.academicBackgroundId eq '1'}">selected="selected"</c:if>>本科</option>
			</select>
		</td>
		<td style="text-align: center">
						<span id="academicBackgroundFileSpan" style="display:${!empty educationInfo.academicBackgroundFile?'':'none'} ">
							&nbsp; <a href="${sysCfgMap['upload_base_url']}/${educationInfo.academicBackgroundFile}" target="_blank"><font color="#54B2E5">查看</font></a>&nbsp;
							</span>
			<a id="academicBackgroundFile" href="javascript:uploadFile('academicBackgroundFile','学历附件');" style="margin-left: 2px"><font id="academicBackgroundFileF" color="#54B2E5">${empty educationInfo.academicBackgroundFile?'':'重新'}上传</font></a>&nbsp;
			<span id="academicBackgroundFileDel" href="javascript:void(0)" style="${empty educationInfo.academicBackgroundFile?'display:none':''}">
									<img id="img1" class="opBtn" title="删除" src="<s:url value="/css/skin/${skinPath}/images/del1.png" />"
										 style="cursor: pointer;" onclick="delFile('academicBackgroundFile');" />
								</span>
			<input type="hidden" id="academicBackgroundFileValue"  name="educationInfo.academicBackgroundFile" value="${educationInfo.academicBackgroundFile}" />
		</td>
		<td style="text-align: center">
			<select name="academicDegreeId" class="select validate[required]" style="width: 90%;">
				<option value="" >请选择学位</option>
				<option value="3" <c:if test="${param.academicDegreeId eq '3'}">selected="selected"</c:if>>博士学位</option>
				<option value="2" <c:if test="${param.academicDegreeId eq '2'}">selected="selected"</c:if>>硕士学位</option>
				<option value="1" <c:if test="${param.academicDegreeId eq '1'}">selected="selected"</c:if>>学士学位</option>
			</select>
		</td>
		<td style="text-align: center">
						<span id="academicDegreeFileSpan" style="display:${!empty educationInfo.academicDegreeFile?'':'none'} ">
							&nbsp; <a href="${sysCfgMap['upload_base_url']}/${educationInfo.academicDegreeFile}" target="_blank"><font color="#54B2E5">查看</font></a>&nbsp;
							</span>
			<a id="academicDegreeFile" href="javascript:uploadFile('academicDegreeFile','学位附件');" style="margin-left: 2px"><font id="academicDegreeFileF" color="#54B2E5">${empty educationInfo.academicDegreeFile?'':'重新'}上传</font></a>&nbsp;
			<span id="academicDegreeFileDel" href="javascript:void(0)" style="${empty educationInfo.academicDegreeFile?'display:none':''}">
									<img id="img2" class="opBtn" title="删除" src="<s:url value="/css/skin/${skinPath}/images/del1.png" />"
										 style="cursor: pointer;" onclick="delFile('academicDegreeFile');" />
								</span>
			<input type="hidden" id="academicDegreeFileValue"  name="educationInfo.academicDegreeFile" value="${educationInfo.academicDegreeFile}" />
		</td>
		<td style="text-align: center">
			<a onclick="removeEducation(this)"><font color="red" >删除</font></a>
		</td>
	</tr>
</table>

<table style="display: none;">
	<tr id="trainingTab">
		<td name="index" style="text-align: center"></td>
		<td style="text-align: center">
			<input type="text" readonly class="select validate[required]" onclick="WdatePicker({dateFmt:'yyyy'})" style="text-align: left;width: 90%;" value="" placeholder="请选择" name="trainingYear" />
		</td>
		<td style="text-align: center">
			<input type="text" class="select validate[required]" style="text-align: left;width: 90%;" value="" placeholder="请输入" name="trainingUnit" />
		</td>
		<td style="text-align: center">
			<select name="trainingSpeId" class="select validate[required]" style="width: 90%;">
				<option value="" >选择专业</option>
				<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
					<option <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
				</c:forEach>
			</select>
		</td>
		<td style="text-align: center">
			<select name="certificateLevelId" class="select validate[required]" style="width: 90%;">
				<option value="" >请选择</option>
				<option value="1" <c:if test="${param.certificateLevelId eq '1'}">selected="selected"</c:if>>国家级</option>
				<option value="2" <c:if test="${param.certificateLevelId eq '2'}">selected="selected"</c:if>>省级</option>
				<option value="3" <c:if test="${param.certificateLevelId eq '3'}">selected="selected"</c:if>>市级</option>
				<option value="4" <c:if test="${param.certificateLevelId eq '4'}">selected="selected"</c:if>>院级</option>
			</select>
		</td>
		<td style="text-align: center">
			<input type="text" class="select validate[required]" style="text-align: left;width: 90%;" value="" name="certificateNo" placeholder="请输入" />
		</td>
		<td style="text-align: center">
			<input type="text" readonly class="select validate[required]" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="text-align: left;width: 90%;" value="" placeholder="请选择" name="certificateTime" />
		</td>
		<td style="text-align: center">
						<span id="certificateFileSpan" style="display:${!empty trainingInfo.certificateFile?'':'none'} ">
							&nbsp; <a href="${sysCfgMap['upload_base_url']}/${trainingInfo.certificateFile}" target="_blank"><font color="#54B2E5">查看</font></a>&nbsp;
							</span>
			<a id="certificateFile" href="javascript:uploadFile('certificateFile','证书附件');" style="margin-left: 2px"><font id="certificateFileF" color="#54B2E5">${empty trainingInfo.certificateFile?'':'重新'}上传</font></a>&nbsp;
			<span id="certificateFileDel" href="javascript:void(0)" style="${empty trainingInfo.certificateFile?'display:none':''}">
									<img id="img3" class="opBtn" title="删除" src="<s:url value="/css/skin/${skinPath}/images/del1.png" />"
										 style="cursor: pointer;" onclick="delFile('certificateFile');" />
								</span>
			<input type="hidden" id="certificateFileValue"  name="trainingInfo.certificateFile" value="${trainingInfo.certificateFile}" />
		</td>
		<td style="text-align: center">
			<a onclick="removeTraining(this)"><font color="red" >删除</font></a>
		</td>
	</tr>
</table>

<table style="display: none;">
	<tr id="letterTab">
		<td name="index" style="text-align: center"></td>
		<td style="text-align: center">
			<select name="letterPeriod" class="select validate[required]" style="width: 90%;">
				<option value="有限时长" <c:if test="${param.letterPeriod eq '有限时长'}">selected="selected"</c:if>>有限时长</option>
				<option value="长期" <c:if test="${param.letterPeriod eq '长期'}">selected="selected"</c:if>>长期</option>
			</select>
		</td>
		<td style="text-align: center">
			<input type="text" readonly class="select validate[required]" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="text-align: left;width: 90%;" value="" placeholder="请选择" name="letterStartTime" />
		</td>
		<td style="text-align: center">
			<input type="text" readonly class="select" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="text-align: left;width: 90%;" value="" placeholder="请选择" name="letterEndTime" />
		</td>
		<td style="text-align: center">
			<input type="text" readonly class="select validate[required]" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="text-align: left;width: 90%;" value="" placeholder="请选择" name="letterTime" />
		</td>
		<td style="text-align: center">
						<span id="letterFileSpan" style="display:${!empty letterInfo.letterFile?'':'none'} ">
							&nbsp; <a href="${sysCfgMap['upload_base_url']}/${letterInfo.letterFile}" target="_blank"><font color="#54B2E5">查看</font></a>&nbsp;
							</span>
			<a id="letterFile" href="javascript:uploadFile('letterFile','证书附件');" style="margin-left: 2px"><font id="letterFileF" color="#54B2E5">${empty letterInfo.letterFile?'':'重新'}上传</font></a>&nbsp;
			<span id="letterFileDel" href="javascript:void(0)" style="${empty letterInfo.letterFile?'display:none':''}">
									<img id="img4" class="opBtn" title="删除" src="<s:url value="/css/skin/${skinPath}/images/del1.png" />"
										 style="cursor: pointer;" onclick="delFile('letterFile');" />
								</span>
			<input type="hidden" id="letterFileValue"  name="letterInfo.letterFile" value="${letterInfo.letterFile}" />
		</td>
		<td style="text-align: center">
			<a onclick="removeLetter(this)"><font color="red" >删除</font></a>
		</td>
	</tr>
</table>











<%--<table class="grid" style="width: 100%;margin-top: 15px">--%>
<%--	<input  type="text" name="recordFlow" id="recordFlow"  value="${teacher.recordFlow}" style="display: none;"/>--%>
<%--	<input  type="text" name="orgFlow" id="orgFlow"  value="${teacher.orgFlow}" style="display: none;"/>--%>
<%--	<input  type="text" name="roleFlag" id="roleFlag" value="${roleFlag}" style="text-align: left;display: none;width: 150px;"/>--%>
<%--	<tr>--%>
<%--		<th width="150px"><font color="red" >*</font>姓名</th>--%>
<%--		<td  style="text-align: left;">--%>
<%--			<input  type="text" name="doctorName" class="select validate[required]" value="${teacher.doctorName}" style="text-align: left;width: 150px;"/>--%>
<%--		</td>--%>
<%--	</tr>--%>
<%--	<tr>--%>
<%--		<th width="150px">手机号码</th>--%>
<%--		<td  style="text-align: left;">--%>
<%--			<input  type="text" id="userPhone" name="userPhone"  class="validate[custom[mobile]] input" value="${teacher.userPhone}" style="text-align: left;width: 150px;"/>--%>
<%--		</td>--%>
<%--	</tr>--%>
<%--	<tr>--%>
<%--		<th width="150px">身份证号</th>--%>
<%--		<td  style="text-align: left;">--%>
<%--			<input  type="text" id="idNo" name="idNo" class="validate[custom[chinaIdLoose]] input"  value="${teacher.idNo}" style="text-align: left;width: 150px;"/>--%>
<%--		</td>--%>
<%--	</tr>--%>
<%--	<tr>--%>
<%--		<th width="150px"><font color="red" >*</font>性别</th>--%>
<%--		<td  style="text-align: left;">--%>
<%--			<select name="sexName" id="sexName" class="select validate[required]" style="width: 150px;" >--%>
<%--				<option value=""></option>--%>
<%--				<option value="男"<c:if test="${teacher.sexName=='男' }">selected="selected"</c:if>>男</option>--%>
<%--				<option value="女"<c:if test="${teacher.sexName=='女' }">selected="selected"</c:if>>女</option>--%>
<%--			</select>--%>
<%--		</td>--%>
<%--	</tr>--%>
<%--	<tr>--%>
<%--		<th width="150px"><font color="red" >*</font>年龄</th>--%>
<%--		<td  style="text-align: left;">--%>
<%--			<input  type="text" name="doctorAge" class="select validate[required,custom[integer],min[0]]" value="${teacher.doctorAge}" style="text-align: left;width: 150px;"/>--%>
<%--		</td>--%>
<%--	</tr>--%>
<%--	<tr>--%>
<%--		<th width="150px"><font color="red" >*</font>专业</th>--%>
<%--		<td  style="text-align: left;">--%>
<%--			<select class="select validate[required]" id="speId" name="speId" style="width: 150px">--%>
<%--				<option value="">请选择</option>--%>
<%--				<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">--%>
<%--					<option <c:if test="${teacher.speId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>--%>
<%--				</c:forEach>--%>
<%--			</select>--%>
<%--		</td>--%>
<%--	</tr>--%>
<%--	<tr>--%>
<%--		<th width="150px"><font color="red" >*</font>科室</th>--%>
<%--		<td  style="text-align: left;">--%>
<%--			<select multiple class="selectpicker" name="userDepts" id="userDepts" title="请选择科室" data-actions-box="true">--%>
<%--				<c:forEach items="${sysDeptList}" var="sysDept">--%>
<%--					<option value="${sysDept.deptFlow}"<c:if test="${!empty sysUserDeptMap[sysDept.deptFlow]}">selected="selected"</c:if>>${sysDept.deptName}</option>--%>
<%--				</c:forEach>--%>
<%--			</select>--%>
<%--		</td>--%>
<%--	</tr>--%>
<%--	<tr>--%>
<%--		<th width="150px"><font color="red" >*</font>培训年份</th>--%>
<%--		<td  style="text-align: left;">--%>
<%--			<input type="text" value="${teacher.trainingYear}" class="select validate[required]" name="trainingYear" id="trainingYear" style="width: 150px;"/>--%>
<%--		</td>--%>
<%--	</tr>--%>
<%--	<tr>--%>
<%--		<th width="150px"><font color="red" >*</font>学历</th>--%>
<%--		<td  style="text-align: left;">--%>
<%--			<select class="select validate[required]" id="doctorEdu" name="doctorEdu" style="width: 150px">--%>
<%--				<option value="">请选择</option>--%>
<%--				<option value="专科"<c:if test="${teacher.doctorEdu eq '专科'}">selected="selected"</c:if>>专科</option>--%>
<%--				<option value="本科"<c:if test="${teacher.doctorEdu eq '本科'}">selected="selected"</c:if>>本科</option>--%>
<%--				<option value="硕士研究生"<c:if test="${teacher.doctorEdu eq '硕士研究生'}">selected="selected"</c:if>>硕士研究生</option>--%>
<%--				<option value="博士研究生"<c:if test="${teacher.doctorEdu eq '博士研究生'}">selected="selected"</c:if>>博士研究生</option>--%>
<%--				<option value="其他"<c:if test="${teacher.doctorEdu eq '其他'}">selected="selected"</c:if>>其他</option>--%>
<%--				<option value="无"<c:if test="${teacher.doctorEdu eq '无'}">selected="selected"</c:if>>无</option>--%>
<%--			</select>--%>
<%--		</td>--%>
<%--	</tr>--%>
<%--	<tr>--%>
<%--		<th width="150px"><font color="red" >*</font>毕业学校</th>--%>
<%--		<td  style="text-align: left;">--%>
<%--			<input  type="text" name="graduationSchool" class="select validate[required]" value="${teacher.graduationSchool}" style="text-align: left;width: 150px"/>--%>
<%--		</td>--%>
<%--	</tr>--%>
<%--	<tr>--%>
<%--		<th width="150px"><font color="red" >*</font>毕业时间</th>--%>
<%--		<td  style="text-align: left;">--%>
<%--			<input type="text" value="${teacher.graduationTime}" class="select validate[required]" name="graduationTime" id="graduationTime" style="width: 150px;"/>--%>
<%--		</td>--%>
<%--	</tr>--%>
<%--	<tr>--%>
<%--		<th width="150px"><font color="red" >*</font>工作时间</th>--%>
<%--		<td  style="text-align: left;">--%>
<%--			<input type="text" value="${teacher.workingTime}" class="select validate[required]" name="workingTime" id="workingTime" style="width: 150px;"/>--%>
<%--		</td>--%>
<%--	</tr>--%>
<%--	<tr>--%>
<%--		<th width="150px"><font color="red" >*</font>技术职称</th>--%>
<%--		<td  style="text-align: left;">--%>
<%--			<input  type="text" name="technicalTitle" class="select validate[required]" value="${teacher.technicalTitle}" style="text-align: left;width: 150px"/>--%>
<%--		</td>--%>
<%--	</tr>--%>
<%--	<tr>--%>
<%--		<th width="150px"><font color="red" >*</font>师资培训会议名称</th>--%>
<%--		<td  style="text-align: left;">--%>
<%--			<input  type="text" name="meetingName" class="select validate[required]" value="${teacher.meetingName}"  style="text-align: left;width: 150px;"/>--%>
<%--		</td>--%>
<%--	</tr>--%>
<%--	<tr>--%>
<%--		<th width="150px"><font color="red" >*</font>师资证书等级</th>--%>
<%--		<td  style="text-align: left;">--%>
<%--			<select class="select" name="teachingCertLevel" id="teachingCertLevel" style="width: 150px;">--%>
<%--				<option value="">请选择</option>--%>
<%--				<c:forEach items="${teachingCertLevelEnumList}" var="certLevel">--%>
<%--					<option value="${certLevel.code}" <c:if test="${teacher.teachingCertLevel eq certLevel.code}">selected</c:if> >${certLevel.name}</option>--%>
<%--				</c:forEach>--%>
<%--			</select>--%>
<%--		</td>--%>
<%--	</tr>--%>
<%--	<tr>--%>
<%--		<th width="150px"><font color="red" >*</font>证书取得时间</th>--%>
<%--		<td  style="text-align: left;">--%>
<%--			<input type="text" value="${teacher.certGrantedDate}" class="select validate[required]" name="certGrantedDate" id="certGrantedDate" style="width: 150px;"/>--%>
<%--		</td>--%>
<%--	</tr>--%>
<%--	<tr>--%>
<%--		<th width="150px"><font color="red" >*</font>证书编号</th>--%>
<%--		<td  style="text-align: left;">--%>
<%--			<input  type="text" id="certificateNo" name="certificateNo" class="select validate[required]" value="${teacher.certificateNo}"  style="text-align: left;width: 150px;"/>--%>
<%--			<a style="cursor: pointer;" onclick="javascript:viewTrainAttachment('${teacher.recordFlow}','szzsAttach','证书附件')">上传附件</a>--%>
<%--		</td>--%>
<%--	</tr>--%>
<%--	<tr>--%>
<%--		<th width="150px">任现职务年限</th>--%>
<%--		<td  style="text-align: left;">--%>
<%--			<input  type="text" name="officeYear" class="select validate[custom[integer],min[0]]" value="${teacher.officeYear}" style="text-align: left;width: 150px;"/>--%>
<%--		</td>--%>
<%--	</tr>--%>
<%--	<tr>--%>
<%--		<th width="150px">从事本专业临床工作年限</th>--%>
<%--		<td  style="text-align: left;">--%>
<%--			<input  type="text" name="workYear" class="select validate[custom[integer],min[0]]" value="${teacher.workYear}" style="text-align: left;width: 150px;"/>--%>
<%--		</td>--%>
<%--	</tr>--%>
<%--	<tr>--%>
<%--		<th width="150px">带住院医师年限</th>--%>
<%--		<td  style="text-align: left;">--%>
<%--			<input  type="text" name="hosYear" class="select validate[custom[integer],min[0]]" value="${teacher.hosYear}" style="text-align: left;width: 150px;"/>--%>
<%--		</td>--%>
<%--	</tr>--%>
<%--	<tr>--%>
<%--		<th width="150px">带住院医师近三年累计人数</th>--%>
<%--		<td  style="text-align: left;">--%>
<%--			<input  type="text" name="threeHosYear" class="select validate[custom[integer],min[0]]" value="${teacher.threeHosYear}" style="text-align: left;width: 150px;"/>--%>
<%--		</td>--%>
<%--	</tr>--%>
<%--	<tr>--%>
<%--		<th width="150px">参加省级及以上住院医师规范化培训师资培训</th>--%>
<%--		<td  style="text-align: left;">--%>
<%--			<label><input name="isTrain" type="radio" value="${GlobalConstant.FLAG_Y}"--%>
<%--						  <c:if test="${teacher.isTrain ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>--%>
<%--			<label><input name="isTrain" type="radio" value="${GlobalConstant.FLAG_N}"--%>
<%--						  <c:if test="${teacher.isTrain ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>--%>
<%--		</td>--%>
<%--	</tr>--%>

<%--	<tr>--%>
<%--		<th width="150px">成果附件</th>--%>
<%--		<td >--%>
<%--			<a style="cursor: pointer;" onclick="javascript:viewTrainAttachment('${teacher.recordFlow}','szcgAttach','成果附件')">上传附件</a>--%>
<%--		</td>--%>
<%--	</tr>--%>
<%--</table>--%>
