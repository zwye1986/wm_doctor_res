<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true" />
	<jsp:param name="jbox" value="true" />
	<jsp:param name="font" value="true" />
	<jsp:param name="jquery_validation" value="true" />
	<jsp:param name="jquery_datePicker3" value="true" />
</jsp:include>
	<script src="<s:url value='/js/yearSelect/checkYear.js'/>?t=33" type="text/javascript"></script>
	<script type="text/javascript" src="<s:url value='/js/itemSelect/itemSelect2.js'/>?t=33"></script>
    <style>
        #editForm input{
            margin-bottom:0 ;
        }
		input[type="radio"], input[type="checkbox"] {
			margin: auto;
			line-height: normal;
		}
    </style>
<script type="text/javascript">
	$(document).ready(function(){
		$.checkYear("sessionNumbers","",null);
		$('#assessmentYear').attr("readonly",false);
		$('#assessmentYear').datetimepicker({
			language:  'zh-CN',
			startView: 4,
			maxView: 4,
			minView:4,
			format: 'yyyy',
			autoclose:true
		});

        $('#assessmentStartTime').datetimepicker({
            language:  'zh-CN',
            startView: 2,
            maxView: 4,
            minView:2,
            format:'yyyy-mm-dd',
            autoclose:true
        });

        $('#assessmentEndTime').datetimepicker({
            language:  'zh-CN',
            startView: 2,
            maxView: 4,
            minView: 2,
            format: 'yyyy-mm-dd',
            autoclose: true
        });
        $('#examStartTime').datetimepicker({
            language:  'zh-CN',
            startView: 2,
            maxView: 4,
            minView:0,
            format:'yyyy-mm-dd hh:ii',
            autoclose:true
		});
        $('#examEndTime').datetimepicker({
            language:  'zh-CN',
            startView: 2,
            maxView: 4,
            minView:0,
            format:'yyyy-mm-dd hh:ii',
            autoclose:true
		});

		changeTrainSpes();
		var exampaperType = "${ment.exampaperType}";
		if(exampaperType == 2){
			$("#assessmentStartTime").val("").attr("disabled","disabled");
			$("#assessmentEndTime").val("").attr("disabled","disabled");
            $("#yearScope").hide();
            $("#examScope").show();
		}
		if(exampaperType == 1 || $("input[type='checkbox']:checked").length < 1){
			$("#editExamScope").val("未设置");
            $("#yearScope").show();
            $("#examScope").hide();
		}
	});
	function changeTrainSpes(t){
		var datas =[];
		var trainCategoryid=$("#trainingTypeId").val();
		$("#trainingSpeIds").val("");
		if(trainCategoryid==""){
			$.itemSelect("trainingSpeIds",datas,null,null,null,0,150);
			return false;
		}
		$("#"+trainCategoryid+"_select").find("option").each(function(){
			var arry = {"id":$(this).val(),"text":$(this).text()};
			datas.push(arry);
		});
		$.itemSelect("trainingSpeIds",datas,null,null,null,0,150);
		return false;
	}
	function changeSpeName(resp){
		var trainingSpeName = $(resp).find("option:selected").text();
		$("#trainingSpeName").val(trainingSpeName);
	}
	function save() {
		if(false==$("#editForm").validationEngine("validate")){
			return false;
		}
		var exampaperType = $("input[name='exampaperType']:checked").val();
		if(exampaperType == 2 & $("input[type='checkbox']:checked").length < 1){
			jboxTip("请配置考试范围！");
			return;
		}
		var startDate = $("#assessmentStartTime").val();
		var endDate = $("#assessmentEndTime").val();
		if(exampaperType == 1 & startDate > endDate){
			jboxTip("年度区间开始日期不能小于截止日期！");
			return;
		}
		var trainingSpeIds=$("#trainingSpeIds").val();
		if(!trainingSpeIds)
		{
			jboxTip("请选择培训专业！");
			return;
		}
		var sessionNumbers=$("#sessionNumbers").val();
		if(!sessionNumbers)
		{
			jboxTip("请选择年级！");
			return;
		}
		var isApp = $("input[id='isAppCheck']:checked").val()||"N";
		var isWeb = $("input[id='isWebCheck']:checked").val()||"N";
		$("input[id='isApp']").val(isApp);
		$("input[id='isWeb']").val(isWeb);
		var url = "<s:url value='/jsres/examCfg/updateArrangementAdd'/>";
		var data = $('#editForm').serialize();
		jboxStartLoading();
		jboxPost(url, data, function(resp) {
			if(resp == 'cannotInsert'){
				jboxTip("考试方案已存在，请按红色字体要求填写！");
				jboxEndLoading();
				return;
			}
			jboxTip("操作成功！");
			window.parent.toPage(1);
			jboxClose();
		}, null, true);
	}
	//模糊查询
	function likeSearch(name){
		if(name){
			$("[deptName]").hide();
			$("[deptName*='"+name+"']").show();
		}else{
			$("[deptName]").show();
		}
	}
	function editScope(back){
		if(back == "back"){
			$("#infoDiv").toggle();
			$("#standardDeptScope").toggle();
			return;
		}
		if($("#standardDeptScope").is(":visible") & $("input[type='checkbox']:checked").length < 1)
		{
			jboxTip("您还未勾选考试范围！");
			return;
		}
		if($("#infoDiv").is(":visible") & $("input[name='exampaperType']:checked").val() != 2)
		{
			jboxTip("您还未勾选固定试卷！");
			return;
		}
		if($("#standardDeptScope").is(":visible"))
		{
			$("#editExamScope").val("已设置");
		}
		$("#infoDiv").toggle();
		$("#standardDeptScope").toggle();
	}
	function changePaperType(resp){
		if(resp == 1){
			$("#assessmentStartTime").attr("disabled",false).attr("readonly",false).addClass("validate[required]");
			$("#assessmentEndTime").attr("disabled",false).attr("readonly",false).addClass("validate[required]");
			$("#yearScope").show();
			$("#examScope").hide();
		}
		if(resp == 2){
			$("#assessmentStartTime").val("").attr("disabled","disabled");
			$("#assessmentEndTime").val("").attr("disabled","disabled");
            $("#yearScope").hide();
            $("#examScope").show();
		}
	}
    function checkExamTime(obj){
        var examStartTime = $("#examStartTime").val();
        var examEndTime = $("#examEndTime").val();
        examStartTime = new Date( examStartTime.replace(/-/g,"/") );
        examEndTime = new Date(examEndTime.replace(/-/g,"/") );
        if(examEndTime !="" && examStartTime != ""){
            if(examEndTime <= examStartTime){
               jboxTip("考核开始时间不得晚于结束时间");
                $(obj).val("");
            }
        }
    }
</script>
</head>
<body>
<div class="infoAudit">
	<form id="editForm" style="position: relative;padding-top: 20px;" method="post">
		<span class="red">&#12288;&#12288;Tip：同一考核年度、同一培训类别、同一培训专业、同一年级只能录入一条考核配置记录！</span>
		<div id="infoDiv" class="div_table">
			<input type="hidden" name="arrangeFlow" value="${ment.arrangeFlow }"/>
			<input type="hidden" id="trainingTypeName" name="trainingTypeName" value="${ment.trainingTypeName }"/>
			<input type="hidden" id="trainingSpeName" name="trainingSpeName" value="${ment.trainingSpeName }"/>
			<input type="hidden" id="isOpen" name="isOpen" value="${ment.isOpen }"/>
			<input type="hidden" id="orgFlow" name="orgFlow" value="${currentOrg.orgFlow }"/>
			<input type="hidden" id="orgName" name="orgName" value="${currentOrg.orgName }"/>
			<table border="0" cellpadding="0" cellspacing="0" class="base_info">
				<tbody>
					<tr>
						<th style="width:10%;"><font color="red">*</font>考核年度：</th>
						<td style="width:15%;">
							<input class="validate[required] input" id="assessmentYear" name="assessmentYear" type="text" value="${ment.assessmentYear }" readonly="readonly" style="width: 100px;" />
						</td>
						<th style="width:10%;"><font color="red">*</font>培训类别：</th>
						<td style="width:15%;">
							<select style="width: 106px;margin-left: 4px;    margin-bottom: 0;" name="trainingTypeId" id="trainingTypeId" class="validate[required] select" onchange="changeTrainSpes(this)">
								<option value="">请选择</option>
								<c:forEach items="${trainCategoryEnumList}" var="trainCategory">
									<option value="${trainCategory.id}" <c:if test="${ment.trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
								</c:forEach>
							</select>
						</td>
						<th style="width:10%;"><font color="red">*</font>培训专业：</th>
						<td style="width:15%;">
							<input id="trainingSpeIds" name="trainingSpeIds" class="input" style="width: 100px;" type="text" readonly="readonly" />
						</td>
						<th style="width:10%;"><font color="red">*</font>年&#12288;&#12288;级：</th>
						<td style="width:15%;">
							<input style="width: 100px;" type="text" id="sessionNumbers" name="sessionNumbers"  class="input" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<th rowspan="4"><font color="red">*</font>考试模式：</th>
						<td>
							<input class="validate[required] input" name="exampaperType" <c:if test="${ment.exampaperType eq 1 }">checked</c:if> onchange="changePaperType(1);" type="radio" value="1" />随机试卷
						</td>
						<th><font id="yearScope" color="red">*</font>年度区间：</th>
						<td colspan="5">
							<input style="width: 100px;" type="text" id="assessmentStartTime" name="assessmentStartTime" value="${ment.assessmentStartTime }" class="input" readonly="readonly" />
							~
							<input style="width: 100px;" type="text" id="assessmentEndTime" name="assessmentEndTime" value="${ment.assessmentEndTime }" class="input" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<td colspan="7">
							<span>
								&nbsp;说明：系统根据每个学员年度区间内轮转科室情况组卷，学员试卷不同
							</span>
						</td>
					</tr>
					<tr>
						<td>
							<input class="validate[required] input" name="exampaperType" <c:if test="${ment.exampaperType eq 2 }">checked</c:if> onchange="changePaperType(2);" type="radio" value="2" />固定试卷
						</td>
						<th><font id="examScope" color="red">*</font>考试范围：</th>
						<td colspan="5">&nbsp;
							<input type="button" id="editExamScope" class="btn_green" onclick="editScope();" value="已设置">
						</td>
					</tr>
					<tr>
						<td colspan="7">
							<span>
								&nbsp;说明：通过配置考核范围组卷考试，学员试卷相同
							</span>
						</td>
					</tr>
					<tr>
						<th>
                            <font color="red">*</font>考核次数：
						</th>
						<td >
							<input style="width: 100px;" type="text" name="examNumber" value="${ment.examNumber }" class="validate[required ,custom[positiveNum]] input"/>
						</td>
                        <th>
                            考核时长：
                        </th>
                        <td >
                            <input style="width: 30px;" type="text" name="examDuration" value="${ment.examDuration}" class="validate[custom[positiveNum]] input"/>分钟
                        </td>
                        <th>
                            考核时间：
                        </th>
                        <td colspan="4">
                            <input style="width: 150px;" type="text" name="examStartTime" id="examStartTime" value="${ment.examStartTime}" class="input" onchange="checkExamTime(this)"/>
                        ~
                            <input style="width: 150px;" type="text" name="examEndTime"  id="examEndTime" value="${ment.examEndTime}" class="input" onchange="checkExamTime(this)"/>
                        </td>
					</tr>
					<tr>
						<th>
                            <font color="red">*</font>考核方式：
						</th>
						<td colspan="7">
							<input id="isApp" hidden name="isApp" value="${empty ment.isApp ?'N':ment.isApp}">
							<input id="isWeb" hidden name="isWeb" value="${empty ment.isWeb ?'N':ment.isWeb}">
							<input class="validate[required] input" name="examType" id="isAppCheck" <c:if test="${ment.isApp eq 'Y' }">checked</c:if> type="checkbox" value="Y" /><label style="width:50px;    display: initial" for="isAppCheck">APP端</label>
							<input class="validate[required] input" name="examType" id="isWebCheck" <c:if test="${ment.isWeb eq 'Y' }">checked</c:if> type="checkbox" value="Y" /><label style="width:50px;    display: initial" for="isWebCheck">WEB端</label>
						</td>
					</tr>
					<tr>
						<th>
							<font color="red">*</font>考核成绩：
						</th>
						<td colspan="7">
							<input class="validate[required] " name="isOpenResult" type="radio" value="${GlobalConstant.FLAG_Y }" id="isOpenResult_Y"
								   <c:if test="${ment.isOpenResult eq GlobalConstant.FLAG_Y }">checked="checked"</c:if>/>开放&#12288;
							<input class="validate[required] " name="isOpenResult" type="radio" value="${GlobalConstant.FLAG_N }" id="isOpenResult_N"
								   <c:if test="${ment.isOpenResult eq GlobalConstant.FLAG_N }">checked="checked"</c:if>/>不开放&#12288;
						</td>
					</tr>
				</tbody>
			</table>
			<div class="button">
				<input type="button" class="btn_green" onclick="save();" value="保&#12288;存"/>&#12288;
				<input type="button" class="btn_green" onclick="jboxClose();" value="关&#12288;闭"/>
			</div>
		</div>
		<div class="main_bd" id="standardDeptScope" style="display: none;">
			<div class="div_search" style="padding: 10px;">
				<input type="text" name="deptName" placeholder="可通过关键字检索" onkeyup="likeSearch(this.value);" class="input" />
			</div>
			<div class="div_search" style="width: 96%;padding: 10px;margin: 10px;float:left;overflow:auto;max-height: 238px;">
				<c:forEach items="${all}" var="dict">
					<div style="width: 24%;float: left;" deptName="${dict.deptName}">
						<label>
							<input type="checkbox" class="validate[required]" name="standardDeptId"  <c:if test="${pdfn:contain(dict.deptFlow, standardList) }">checked</c:if>  value="${dict.deptFlow}"/>
								${dict.deptName}
						</label>
					</div>
				</c:forEach>
			</div>
			<div class="button">
				<input type="button" class="btn_green" onclick="editScope();" value="保&#12288;存"/>
				<input type="button" class="btn_green" onclick="editScope('back');" value="返&#12288;回"/>
			</div>
		</div>
	</form>
</div>

<div style="display: none;">
	<select id="WMFirst_select">
		<c:forEach items="${dictTypeEnumWMFirstList}" var="dict">
			<c:if test="${empty speIds or (pdfn:contain(dict.dictId, speIds))}">
				<option <c:if test="${ment.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
			</c:if>
		</c:forEach>
	</select>
	<select id="WMSecond_select">
		<c:forEach items="${dictTypeEnumWMSecondList}" var="dict">
			<c:if test="${empty speIds or (pdfn:contain(dict.dictId, speIds))}">
				<option <c:if test="${ment.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
			</c:if>
		</c:forEach>
	</select>
	<select id="AssiGeneral_select">
		<c:forEach items="${dictTypeEnumAssiGeneralList}" var="dict">
			<c:if test="${empty speIds or (pdfn:contain(dict.dictId, speIds))}">
				<option <c:if test="${ment.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
			</c:if>
		</c:forEach>
	</select>
	<select id="DoctorTrainingSpe_select">
		<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
			<c:if test="${empty speIds or (pdfn:contain(dict.dictId, speIds))}">
				<option <c:if test="${ment.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
			</c:if>
		</c:forEach>
	</select>
</div>
</body>
</html>