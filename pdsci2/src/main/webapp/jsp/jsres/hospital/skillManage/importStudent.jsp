<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="font" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_form" value="true"/>
		<jsp:param name="jquery_validation" value="true"/>
		<jsp:param name="jquery_datePicker" value="true"/>
	</jsp:include>
	<script type="text/javascript">
		function daoRu(){
			if(false==$("#excelForm").validationEngine("validate")){
				return false;
			}
			var checkFileFlag = $("#checkFileFlag").val();
			if('${GlobalConstant.FLAG_Y}'!=checkFileFlag){
				jboxTip("请上传Excel文件！");
				return false;
			}
			var timeFlow = $("select option:selected").attr("flow");
			var url = "<s:url value='/jsres/skillTimeConfig/importStudents?clinicalFlow=${param.clinicalFlow}'/>&timeFlow="+timeFlow;
			jboxSubmit(
					$('#excelForm'),
					url,
					function(resp){
						top.jboxInfo(resp);
						if(resp.substring(0,5)=="${GlobalConstant.UPLOAD_SUCCESSED}"){
							window.parent.toPage1(1);
							jboxClose();
						}
					},
					function(resp){
						top.jboxEndLoading();
						top.jboxTip('${GlobalConstant.UPLOAD_FAIL}');
					},false);
		}

		function checkFile(file){
			var filePath = file.value;
			var suffix = filePath.substring(filePath.lastIndexOf(".")+1);
			if("xlsx" == suffix || "xls" == suffix){
				$("#checkFileFlag").val("${GlobalConstant.FLAG_Y}");
			}else{
				$("#checkFileFlag").val("${GlobalConstant.FLAG_N}");
				$(file).val(null);
				jboxTip("请上传Excel文件");
			}
		}

		function showTimeDiv(param){
			if(param=="add"){
				$(".timeDiv").show();
			}else{
				$(".timeDiv").hide();
			}
		}

		function checkYear(obj){
			var dates = $(':text',$(obj).closest("td"));
			if(dates[0].value && dates[1].value && dates[0].value > dates[1].value){
				jboxTip("开始时间不能大于结束时间！");
				obj.value = "";
			}
		}
	</script>
</head>
<body>
<input type="hidden" id="checkFileFlag" name="checkFileFlag"/>
<form id="excelForm" method="post" enctype="multipart/form-data">
	<table class="grid" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<th>请选择导入文件</th>
			<td>
				<input id="file" type="file" name="file" onchange="checkFile(this);" class="validate[required]"/>
			</td>
		</tr>
		<tr>
			<th>考核时间</th>
			<td>
				<c:if test="${!(empty skillsAssessmentTimes)}">
					<select class="select validate[required]" name="time" onchange="showTimeDiv(this.value)" style="width: 70%">
						<option value="">请选择</option>
						<c:forEach items="${skillsAssessmentTimes}" var="time">
							<option flow="${time.recrodFlow}">${time.examStartTime}-${time.examEndTime}</option>
						</c:forEach>
						<option value="add">新增时间</option>
					</select><br/>
				</c:if>
				<div class="timeDiv"
				<c:if test="${!(empty skillsAssessmentTimes)}">
					 style="display:none"
				</c:if>
				>
				<input type="text" class="validate[required] xltext" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" name="startTime" onchange="checkYear(this)"/>
				— <input type="text" class="validate[required] xltext" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" name="endTime" onchange="checkYear(this)"/>
				</div>
			</td>
		</tr>
		<tr>
			<th>模板文件</th>
			<td><a href="<s:url value="/jsp/osca/base/studentTemp.xlsx"/>">学生导入模板.xls</a></td>
		</tr>
	</table>
</form>
<div style="text-align: center; margin-top: 10px;">
	<input type="button" onclick="daoRu();" value="导&#12288;入" class="btn_green"/>
	<input type="button" class="btn_green" value="关&#12288;闭" onclick="jboxClose();"/>
</div>
</body>
</html>