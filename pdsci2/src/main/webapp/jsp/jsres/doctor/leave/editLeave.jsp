<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="login" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<style type="text/css">
	#boxHome .item:HOVER{background-color: #eee;}
</style>
<script type="text/javascript">
    $(document).ready(function () {
        $('#startDate').datepicker({
            format: 'yyyy-mm-dd'
        });
		$('#endDate').datepicker({
			format: 'yyyy-mm-dd'
		});

        <%--var deptId = '${teacher.deptFlow}';--%>
        <%--$("#deptFlow").empty();--%>
        <%--$("#deptFlow").append("<option value=''>请选择</option>");--%>
        <%--<c:forEach var="dept" items="${deptList}">--%>
		<%--	var deptFlow = '${dept.deptFlow}';--%>
		<%--	var deptName = '${dept.deptName}';--%>
		<%--	if(deptFlow == deptId) {--%>
		<%--		$("#deptFlow").append("<option selected value='" + deptFlow + "'>" + deptName + "</option>");--%>
		<%--	}else{--%>
		<%--		$("#deptFlow").append("<option value='" + deptFlow + "'>" + deptName + "</option>");--%>
		<%--	}--%>
		<%--</c:forEach>--%>
    });

    // function checkUploadFile(){
    //     if ($("#excelForm").validationEngine("validate")) {
    //         $("#schDeptName").val($("#schDeptFlow option:selected").text());
    //         jboxStartLoading();
    //         $(":file.auto:hidden").attr("disabled",true);
    //         $("#excelForm").submit();
    //     }
    // }

	function save(){
		if(!$("#excelForm").validationEngine("validate")){
			return false;
		}
		$("#schDeptName").val($("#schDeptFlow option:selected").text());
		jboxConfirm("提交后无法修改，确认提交？",function(){
			var url = "<s:url value='/jsres/attendanceNew/saveLeave'/>";
			jboxSubmit($('#excelForm'),url,function (resp) {
				if ("1" == resp) {
					jboxTip("保存成功！")
					setTimeout(function () {
						window.parent.toPage();
						jboxClose();
					}, 1000);
				}else{
					jboxTip(resp);
				}
			},null,false);

		})
	}

	function checkDateCompareNowDate() {
		var endDate = $("#schDeptFlow").find("option:selected").attr("endDate");
		var nowDate = "${nowDate}";
		if(endDate < nowDate){
			jboxTip("轮转结束时间小于当前时间");
			$('#schDeptFlow').attr('value','');
			return false;
		}
		var processFlow = $("#schDeptFlow").find("option:selected").attr("processFlow");
		$("#processFlow").val(processFlow);
	}

	function checkDate(flag) {
		var schStartDate = $("#schDeptFlow").find("option:selected").attr("startDate");
		var schEndDate = $("#schDeptFlow").find("option:selected").attr("endDate");
		if(schStartDate == "" || schStartDate == undefined){
			jboxTip("请先选择轮转科室");
			$("#startDate").val("");
			$("#endDate").val("");
			return false;
		}
		var nowDate = "${nowDate}";
		var startDate = $("#startDate").val();
		var endDate = $("#endDate").val();
    	if(flag == "startDate"){
			if(startDate == ""){
				return;
			}
			if(startDate < nowDate){
				jboxTip("请假开始时间不能小于当前时间");
				$("#startDate").val("");
				return false;
			}
			if(endDate != "") {
				if (endDate < startDate) {
					jboxTip("请假结束时间不能小于请假开始时间");
					$("#startDate").val("");
					return false;
				}
			}
			if(startDate < schStartDate || startDate > schEndDate){
				jboxTip("请假开始时间需在轮转时间内");
				$("#startDate").val("");
				return false;
			}
		}
		if(flag == "endDate"){
			var startDate = $("#startDate").val();
			var endDate = $("#endDate").val();
			if(endDate == ""){
				return;
			}
			if(endDate < nowDate){
				jboxTip("请假结束时间不能小于当前时间");
				$("#endDate").val("");
				return false;
			}
			if(startDate != "") {
				if (endDate < startDate) {
					jboxTip("请假结束时间不能小于请假开始时间");
					$("#endDate").val("");
					return false;
				}
			}
			if(endDate < schStartDate || endDate > schEndDate){
				jboxTip("请假结束时间需在轮转时间内");
				$("#endDate").val("");
				return false;
			}
		}
		if(startDate != "" && endDate != "") {
			jboxPost("<s:url value='/jsres/attendanceNew/intervalDays'/>?startDate=" + startDate + "&endDate=" + endDate, null, function (resp) {
				$("#intervalDays").val("");
				if (null != resp && resp.length > 0) {
					if(resp == "0"){
						jboxTip("请假时间为周末，不需提交申请！");
						$("#startDate").val("");
						$("#endDate").val("");
						return false
					}else {
						$("#intervalDays").val(resp);
					}
				}
			}, null, false);
		}
	}

	function addFile(){
		$('#filesTable').append($("#fileTableFormat tr:eq(0)").clone());
	}

	function moveTr(obj){
		jboxConfirm("确认删除？" , function(){
			var tr=obj.parentNode.parentNode;
			tr.remove();
		});
	}
</script>
</head>
<body>
<div class="div_table" style="overflow: auto;max-height: 520px;width: 100%;margin-top: 15px;padding: 0px 0px 0;">
<input type="hidden" id="checkFileFlag" name="checkFileFlag"/>
	<form id="excelForm" method="post"  enctype="multipart/form-data">
		<table class="grid" style="">
			<input  type="text" name="recordFlow" id="recordFlow"  value="${doctorKq.recordFlow}" style="display: none;"/>
			<input  type="text" name="doctorFlow" id="doctorFlow"  value="${user.userFlow}" style="display: none;"/>
			<input  type="text" name="doctorName" id="doctorName"  value="${user.userName}" style="display: none;"/>
			<input  type="text" name="processFlow" id="processFlow"  value="" style="display: none;"/>
			<tr>
				<th width="150px"><font color="red" >*</font>轮转科室</th>
				<td  style="text-align: left;">
					<select class="select" name="schDeptFlow" id="schDeptFlow" style="width: 150px" onchange="checkDateCompareNowDate()">
						<option value="">请选择</option>
						<c:forEach items="${deptList}" var="dept">
							<option <c:if test="${doctorKq.schDeptFlow eq dept.schDeptFlow}">selected="selected"</c:if>
									value="${dept.schDeptFlow}" startDate = "${dept.schStartDate}" endDate = "${dept.schEndDate}"
									processFlow = "${dept.processFlow}"
							>${dept.schDeptName}(${dept.schStartDate}~${dept.schEndDate})</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th width="150px"><font color="red" >*</font>请假类型</th>
				<td  style="text-align: left;">
					<select class="select" name="typeId" style="width: 150px">
						<option value="">请选择</option>
						<c:forEach items="${dictTypeEnumLeaveTypeList}" var="dict">
							<option <c:if test="${doctorKq.typeId eq dict.dictId}">selected="selected"</c:if>
									value="${dict.dictId}">${dict.dictName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th width="150px"><font color="red" >*</font>请假开始时间</th>
				<td  style="text-align: left;">
					<input type="text" readonly="readonly" value="${doctorKq.startDate}" class="select validate[required]"
						   name="startDate" id="startDate" style="width: 150px;" onchange="checkDate('startDate')"/>
				</td>
			</tr>
			<tr>
				<th width="150px"><font color="red" >*</font>请假结束时间</th>
				<td  style="text-align: left;">
					<input type="text" readonly="readonly" style="width:150px;" class="select validate[required]" name="endDate" id="endDate"
						   value="${doctorKq.endDate}" onchange="checkDate('endDate')" />
				</td>
			</tr>
			<tr>
				<th width="150px"><font color="red" >*</font>请假天数</th>
				<td  style="text-align: left;">
					<input  type="text" id="intervalDays" name="intervalDays" readonly class="select validate[required]" value="${doctorKq.intervalDays}" style="text-align: left;width: 150px;"/>
					<span style="color: red;font-size: 4px">注：8小时为一天，不满一天按一天计算。</span>
				</td>
			</tr>
			<tr>
				<th width="150px"><font color="red" >*</font>请假事由</th>
				<td  style="text-align: left;">
					<textarea name="doctorRemarks" placeholder="请假事由（限250字）。" style="height: 100px;width: 80%"
							  class="validate[required] validate[maxSize[250]] xltxtarea">${doctorKq.doctorRemarks }</textarea>
				</td>
			</tr>
			<tr>
				<th width="150px">附件</th>
				<td colspan="3" style="padding-left:0px;">
					<table style="width: 100%;" class="filesTable" id="filesTable">
						<tr>
							<td style="text-align: left;padding-left: 10px;">
								文件名
								<span><font color="#999" title=".jpg,.png,.jpeg">&#12288;&#12288;仅支持图片格式 </font></span>
							</td>
							<td width="75px">操作 &#12288;<img class="opBtn" title="新增"
															 src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
															 style="cursor: pointer;" onclick="addFile();"/></td>
						</tr>
						<c:forEach items="${files}" var="f">
							<tr>
								<td style="text-align: left;padding-left: 10px;">
									<input hidden name="fileFlows" value="${f.fileFlow}">
									<a href="${sysCfgMap['upload_base_url']}/${f.filePath}" target="_blank">${f.fileName}</a>
								</td>
								<td width="75px">
									<img class='opBtn' title='删除' style='cursor: pointer;'
										 src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick='moveTr(this);'/>
								</td>
							</tr>
						</c:forEach>
					</table>
				</td>
			</tr>
		</table>
		<div style="text-align: center; margin-top: 10px;">
			<input type="button" onclick="save();" class="btn_green" value="保&#12288;存"/>
			<input type="button" class="btn_green" value="关&#12288;闭" onclick="jboxClose();"/>
		</div>
		<%--附件模板--%>
		<table class="filesTable" id="fileTableFormat" style="display: none" style="width: 100%">
		<tr>
			<td style="text-align: left;padding-left: 10px;">
				<input type='file' name='files' class='validate[required]' multiple='multiple' style="max-width: 200px;"
					   accept=".jpg,.png,.jpeg"/>
			</td>
			<td>
				<img class='opBtn' title='删除' style='cursor: pointer;'
					 src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick='moveTr(this);'/>
			</td>
		</tr>
	</table>
</form>
</div>
</body>