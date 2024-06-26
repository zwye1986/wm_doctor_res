<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true" />
	<jsp:param name="jbox" value="true" />
	<jsp:param name="jquery_validation" value="true" />
	<jsp:param name="jquery_datePicker" value="true" />
</jsp:include>
<style type="text/css">
	.xllist td{
		text-align: left;height: 35px;
	}
</style>
<script type="text/javascript">
	$(document).ready(function(){
		var exampaperType = "${ment.exampaperType}";
		if(exampaperType == 2){
			$("#assessmentStartTime").val("").attr("disabled","disabled");
			$("#assessmentEndTime").val("").attr("disabled","disabled");
		}
		if(exampaperType == 1 | $("input[type='checkbox']:checked").length < 1){
			$("#editExamScope").val("未设置");
		}
		var deptNames = '';
		$("input[name='standardDeptId']:checked").each(
				function(index, domEle){
					deptNames += $(domEle).next().text() + '，';
				}
		);
		deptNames = deptNames.substring(0,deptNames.length-1);
		$("#editExamScope").attr("title",deptNames)
	});
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
		var url = "<s:url value='/res/examCfg/updateArrangement'/>";
		var data = $('#editForm').serialize();
		jboxStartLoading();
		jboxPost(url, data, function(resp) {
			if(resp == 'cannotInsert'){
				jboxTip("考试方案已存在，请按红色字体要求填写！");
				jboxEndLoading();
				return false;
			}
			jboxTip("操作成功！");
			window.parent.frames['mainIframe'].toPage(1);
			jboxClose();
		}, null, false);
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
			var deptNames = '';
			$("input[name='standardDeptId']:checked").each(
					function(index, domEle){
						deptNames += $(domEle).next().text() + '，';
					}
			);
			deptNames = deptNames.substring(0,deptNames.length-1);
			$("#editExamScope").attr("title",deptNames)
		}
		$("#infoDiv").toggle();
		$("#standardDeptScope").toggle();
	}
	function changePaperType(resp){
		if(resp == 1){
			$("#assessmentStartTime").attr("disabled",false).addClass("validate[required]");
			$("#assessmentEndTime").attr("disabled",false).addClass("validate[required]");
		}
		if(resp == 2){
			$("#assessmentStartTime").val("").attr("disabled","disabled");
			$("#assessmentEndTime").val("").attr("disabled","disabled");
		}
	}
</script>
</head>
<body>
<div class="infoAudit">
	<form id="editForm" style="position: relative;padding-top: 20px;" method="post">
		<div id="infoDiv" class="div_table" style="padding-top: 5px;">
			<input type="hidden" name="arrangeFlow" value="${ment.arrangeFlow }"/>
			<input type="hidden" id="trainingSpeName" name="trainingSpeName" value="${ment.trainingSpeName }"/>
			<input type="hidden" id="isOpen" name="isOpen" value="${ment.isOpen }"/>
			<input type="hidden" id="orgFlow" name="orgFlow" value="${currentOrg.orgFlow }"/>
			<input type="hidden" id="orgName" name="orgName" value="${currentOrg.orgName }"/>
			<table class="xllist">
				<tbody>
					<tr>
						<th>考核年度：</th>
						<td>&nbsp;
							<input class="validate[required] input qtext" onclick="WdatePicker({dateFmt:'yyyy'})" id="assessmentYear" name="assessmentYear" type="text" value="${ment.assessmentYear }" readonly="readonly" />
						</td>
						<th>培训专业：</th>
						<td>&nbsp;
							<select name="trainingSpeId" id="trainingSpeId" class="validate[required] qselect" onchange="changeSpeName(this)">
								<option value="">请选择</option>
								<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
									<option value="${dict.dictId}" ${ment.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th>年&#12288;&#12288;级：</th>
						<td>&nbsp;
							<input type="text" onclick="WdatePicker({dateFmt:'yyyy'})" id="sessionNumber" name="sessionNumber" value="${ment.sessionNumber }" class="validate[required] qtext" readonly="readonly"/>
						</td>
						<th>
							考核次数：
						</th>
						<td>&nbsp;
							<input type="text" name="examNumber" value="${ment.examNumber }" class="validate[required ,custom[integer]] qtext"/>
						</td>
					</tr>
					<tr>
						<th rowspan="4" style="background-color: #f1f1f1;">考试模式：</th>
						<td>&nbsp;
							<input class="validate[required] input"  name="exampaperType" <c:if test="${ment.exampaperType eq 1 }">checked</c:if> onchange="changePaperType(1);" type="radio" value="1" />随机试卷
						</td>
						<th>年度区间：</th>
						<td>&nbsp;
							<input type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="assessmentStartTime" name="assessmentStartTime" value="${ment.assessmentStartTime }" class="qtext" readonly="readonly" />
							~
							<input type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="assessmentEndTime" name="assessmentEndTime" value="${ment.assessmentEndTime }" class="qtext" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<span>
								&nbsp;说明：系统根据每个学员年度区间内轮转科室情况组卷，学员试卷不同
							</span>
						</td>
					</tr>
					<tr>
						<td>&nbsp;
							<input class="validate[required] input" name="exampaperType" <c:if test="${ment.exampaperType eq 2 }">checked</c:if> onchange="changePaperType(2);" type="radio" value="2" />固定试卷
						</td>
						<th>考试范围：</th>
						<td>&nbsp;
							<input type="button" id="editExamScope" class="search" onclick="editScope();" value="已设置">
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<span>
								&nbsp;说明：通过配置考核范围组卷考试，学员试卷相同
							</span>
						</td>
					</tr>
				</tbody>
			</table>
			<div class="red" style="text-align: center;width: 100%">Tip：同一考核年度、同一培训专业、同一年级只能录入一条考核配置记录！</div>
			<div class="button">
				<input type="button" class="search" onclick="save();" value="保&#12288;存"/>&#12288;
				<input type="button" class="search" onclick="jboxClose();" value="关&#12288;闭"/>
			</div>
		</div>
		<div class="main_bd" id="standardDeptScope" style="display: none;">
			<div class="div_search" style="padding: 10px;">
				<input type="text" name="deptName" placeholder="可通过关键字检索" onkeyup="likeSearch(this.value);" class="input" />
			</div>
			<div class="div_search" style="width: 96%;padding: 10px;margin: 10px;float:left;overflow:auto;max-height: 248px;">
				<c:forEach items="${schDepts}" var="schDept">
					<div style="width: 24%;float: left;margin-top: 15px;" deptName="${schDept.schDeptName}">
						<label>
							<input type="checkbox" class="validate[required]" name="standardDeptId"  <c:if test="${pdfn:contain(schDept.schDeptFlow, standardList) }">checked</c:if>  value="${schDept.schDeptFlow}"/>
							<span>${schDept.schDeptName}</span>
						</label>
					</div>
				</c:forEach>
			</div>
			<div class="button">
				<input type="button" class="search" onclick="editScope();" value="保&#12288;存"/>
				<input type="button" class="search" onclick="editScope('back');" value="返&#12288;回"/>
			</div>
		</div>
	</form>
</div>

</body>
</html>