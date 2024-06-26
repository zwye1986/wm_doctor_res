<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<title>${sysCfgMap['sys_title_name']}</title>
	<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="font" value="true"/>
		<jsp:param name="jquery_validation" value="true"/>
		<jsp:param name="jquery_datePicker" value="true"/>
		<jsp:param name="jquery_cxselect" value="true"/>
		<jsp:param name="jquery_form" value="true"/>
	</jsp:include>
	<script type="text/javascript">
		/**
		 * 保存
		 */
		function saveSpeId(){
			if(false==$("#editForm").validationEngine("validate")){
				return false;
			}
			var $catSpeId = $("#editForm input[name='catSpeId']:checked");
			var catSpeId = $catSpeId.val();
			if(catSpeId == '' || catSpeId == undefined){
			    jboxTip("请选择培训类别！");
			    return false;
			}
            var catSpeName;
            if (catSpeId == 'DoctorTrainingSpe') {
                catSpeName = "住院医师";
            } else {
                catSpeName = "助理全科";
            }
            var recruitFlow = '${doctorRecruit.recruitFlow}';
			$("#catSpeName").val(catSpeName);
			var url = "<s:url value='/jsres/doctor/saveDoctorCatSpe'/>?catSpeId="+catSpeId+"&catSpeName="+catSpeName+"&recruitFlow="+recruitFlow;
            var iframe = "<iframe name='mainIframe' id='mainIframe' width='100%' height='800px' scrolling='no' src='" + url + "'></iframe>";
            jboxMessager(iframe, null, 800, 480);
		}

	</script>
</head>

<body>
<div class="infoAudit">
	<div class="div_table">
		<h4>培训信息(<font color="red">培训类别慎重选择</font>)</h4>
		<input type="hidden" id="proveFileUrlFlag"/>
		<input type="hidden" id="completeFileUrlFlag"/>
		<input type="hidden" id="upFileId"/>
		<form id="editForm" style="position: relative;" method="post">
			<input type="hidden" name="recruitFlow" value="${doctorRecruit.recruitFlow}"/>
			<input type="hidden" name="doctorFlow"  id="doctorFlow"value="${sessionScope.currUser.userFlow}"/>

			<input type="hidden" id="sessionNumber" name="sessionNumber" value="${doctorRecruit.sessionNumber}"/>

			<input type="hidden" id="orgName" name="orgName" value="${doctorRecruit.orgName}"/>
			<input type="hidden" id="catSpeName" name="catSpeName" value="${doctorRecruit.catSpeName}"/>
			<input type="hidden" id="speName" name="speName" value="${doctorRecruit.speName}"/>
			<%-- 所在地区:市、地区 --%>
			<input type="hidden" id="placeId" name="placeId" value="${doctorRecruit.placeId}"/>
			<input type="hidden" id="placeName" name="placeName" value="${doctorRecruit.placeName}"/>

			<!-- 非二阶段修改为二阶段 ：生成一个创建时间在此之前的一阶段  -->
			<input type="hidden" name="createTime" value="${doctorRecruit.createTime}"/>

			<table border="0" cellpadding="0" cellspacing="0" class="base_info">
				<colgroup>
					<col width="40%"/>
					<col width="60%"/>
				</colgroup>
				<tbody>
				<tr>
					<th><span id="red" class="red">*</span>培训类别：</th>
					<td id="catSpeTd">
						<span><input type="radio" name="catSpeId" value="DoctorTrainingSpe"
									 <c:if test="${typeId eq 'DoctorTrainingSpe'}">checked="checked"</c:if>/>住院医师</span>&#12288;&#12288;
						<span><input type="radio" name="catSpeId" value="AssiGeneral"
									 <c:if test="${typeId eq 'AssiGeneral'}">checked="checked"</c:if>/>助理全科</span>
					</td>
				</tr>
				</tbody>
			</table>
			</form>

		<div align="center" style="margin-top: 20px; margin-bottom:20px;">
			<input type="button" id="saveBtn" class="btn_green" onclick="saveSpeId();" value="确定"/>&nbsp;
			<input type="button" class="btn_green" onclick="jboxClose();" value="关闭"/>&nbsp;
		</div>
	</div>
</div>
<div style="display: none;">
	<select id="WMFirst_select">
		<c:forEach items="${dictTypeEnumWMFirstList}" var="dict">
			<option
					<c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if>
					value="${dict.dictId}">${dict.dictName}</option>
		</c:forEach>
	</select>
	<select id="WMSecond_select">
		<c:forEach items="${dictTypeEnumWMSecondList}" var="dict">
			<option
					<c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if>
					value="${dict.dictId}">${dict.dictName}</option>
		</c:forEach>
	</select>
	<select id="AssiGeneral_select">
		<c:forEach items="${dictTypeEnumAssiGeneralList}" var="dict">
			<option
					<c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if>
					value="${dict.dictId}">${dict.dictName}</option>
		</c:forEach>
	</select>
	<select id="DoctorTrainingSpe_select">
		<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
			<option
					<c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if>
					value="${dict.dictId}">${dict.dictName}</option>
		</c:forEach>
	</select>

</div>
</body>
</html>