<jsp:include page="/jsp/czyyjxres/htmlhead-czyyjxres.jsp">
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
<script type="text/javascript">


	//发放结业证书
	function issueCertificate(resumeFlow){
		jboxTip("该功能正在建设中...");
	}
	//查看管理员上传的附件
	function showFile(fileFlow){
		jboxPost("<s:url value='/czyyjxres/doctor/checkFile'/>?fileFlow="+fileFlow ,null ,
				function(resp){
					console.log("${sysCfgMap['upload_base_url']}/"+resp);
					//直接打开
					if(resp!="1")
					{
						var href="${sysCfgMap['upload_base_url']}/"+resp;
						$("#fileSrc").attr("href",href);
						$("#fileSrc").attr("target","_blank");
						$("#fileSrc").find("span").trigger("click");
					}else{
						//下载
						var href="<s:url value='/czyyjxres/doctor/fileDown'/>?fileFlow="+fileFlow;
						$("#fileSrc").attr("href",href);
						$("#fileSrc").removeAttr("target");
						$("#fileSrc").find("span").trigger("click");
					}
		} , null , false);
	}

	//选择文件
	function chooseFile(resumeFlow){
		$("#productFlow").val(resumeFlow);
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
		var url = "<s:url value='/czyyjxres/doctor/uploadFile'/>";
		jboxSubmit($("#fileForm"),url,function(resp){
			jboxEndLoading();
			if(resp=='上传成功！'){
				resp='Upload Successfully!';
			}
			jboxTip(resp);
			if("${GlobalConstant.UPLOAD_FAIL}" != resp){
				graduationAppraisal();
			}
		}, null, false);
	}
</script>
<form id="fileForm" method="post" enctype="multipart/form-data">
	<input type="text" id="productFlow" name="productFlow" value="" style="display: none;"/>
	<input type="file" id="file" name="file" class="validate[required]" style="display: none;"/>
</form>
<a id="fileSrc" target="_blank" style="display: none;"><span></span></a>
	<div class="main_hd">
		<h2 class="underline">Training Identification</h2></div>
		<div class="main_bd">
			<div class="div_table" style="width: 90%;">
		<table border="0" cellpadding="0" cellspacing="0" class="grid">
			<colgroup>
				<col width="12%"/>
				<col width="28%"/>
				<col width="20%"/>
				<col width="20%"/>
				<col width="20%"/>
			</colgroup>
			<tr>
				<th>Study Batches</th>
				<th>Department Of Study Purpose(The Length)</th>
				<th>Identify The Result</th>
				<c:if test="${not empty adminFileMap[doctor.resumeFlow]}">
				<th>file</th>
				</c:if>
				<th>Operation</th>
			</tr>
			<c:forEach items="${list}" var="doctor">
			<tr>
				<td>${doctor.stuBatName}</td>
				<td>
					<c:set value="days" var="unit"></c:set>
					<c:forEach items="${speFormMap[doctor.resumeFlow]}" var="speForm">
						${speForm.speId}
						(
						${not empty speForm.stuTimeId?speForm.stuTimeId:(pdfn:signDaysBetweenTowDate(speForm.endDate,speForm.beginDate)+1)}
						${unit}
						)
					</c:forEach>
				</td>
				<td title="${graduationMarkMap[doctor.resumeFlow]}" >
					<c:choose>
						<c:when test="${(not empty stuFileMap[doctor.resumeFlow]) and doctor.stuStatusName eq stuStatusEnumAdmited.name}">Passing</c:when>
						<c:otherwise>${doctor.stuStatusId}</c:otherwise>
					</c:choose>
				</td>
				<c:if test="${not empty adminFileMap[doctor.resumeFlow]}">
				<td>
						<input class="btn_green" onclick="showFile('${adminFileMap[doctor.resumeFlow].fileFlow}');" value="View File" type="button">
				</td>
				</c:if>
				<td>
					<c:if test="${doctor.stuStatusId eq stuStatusEnumAdmited.id or (doctor.stuStatusId eq stuStatusEnumGraduation.id)or (doctor.stuStatusId eq stuStatusEnumDelayGraduation.id)}">
						<c:if test="${not empty stuFileMap[doctor.resumeFlow]}">
							<input class="btn_green" onclick="showFile('${stuFileMap[doctor.resumeFlow].fileFlow}');" value="View<%--Training Identification--%>" type="button">
							<c:if test="${doctor.stuStatusId ne stuStatusEnumGraduation.id }">
								<input class="btn_green" onclick="chooseFile('${doctor.resumeFlow}');" value="Reupload" type="button">
							</c:if>
						</c:if>
						<c:if test="${ empty stuFileMap[doctor.resumeFlow]}">
							<input class="btn_green" onclick="chooseFile('${doctor.resumeFlow}');" value="Upload Training Identification" type="button">
						</c:if>
					</c:if>
					<c:if test="${doctor.stuStatusId eq stuStatusEnumGraduation.id and doctor.issueCertificate eq GlobalConstant.FLAG_Y}">
						<a class="btn_green" style="color: white;" href="<s:url value='/czyyjxres/doctor/showCertificate?resumeFlow=${doctor.resumeFlow}'/>" target="_blank">Print Certificate of Completion</a>
						<%--<input class="btn_green" onclick="issueCertificate('${doctor.resumeFlow}');" value="Print Certificate of Completion" type="button">--%>
					</c:if>
				</td>
			</tr>
			</c:forEach>
			<c:if test="${empty list}">
				<tr>
					<td colspan="5" >No record!</td>
				</tr>
			</c:if>
		</table>
	</div>
		</div>
	</div>
<div class="page" style="padding-right: 40px;">
	<c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"></c:set>
	<pd:pagination-jsres toPage="toPage"/>
</div>
      
