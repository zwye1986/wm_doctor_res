<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true" />
	<jsp:param name="jbox" value="true" />
	<jsp:param name="jquery_form" value="false" />
	<jsp:param name="jquery_ui_tooltip" value="true" />
	<jsp:param name="jquery_ui_combobox" value="false" />
	<jsp:param name="jquery_ui_sortable" value="false" />
	<jsp:param name="jquery_cxselect" value="true" />
	<jsp:param name="jquery_scrollTo" value="false" />
	<jsp:param name="jquery_jcallout" value="false" />
	<jsp:param name="jquery_validation" value="true" />
	<jsp:param name="jquery_datePicker" value="true" />
	<jsp:param name="jquery_fullcalendar" value="false" />
	<jsp:param name="jquery_fngantt" value="false" />
	<jsp:param name="jquery_fixedtableheader" value="true" />
	<jsp:param name="jquery_placeholder" value="true" />
	<jsp:param name="jquery_iealert" value="false" />
</jsp:include>
<style>
	#searchForm td{border: hidden;width: 10%}
	.doctorTypeDiv {
		border: 0px;
		float: left;
		width: auto;
		line-height: 35px;
		height: 35px;
		text-align: right;
	}
	.doctorTypeLabel {
		border: 0px;
		float: left;
		width: 96px;
		line-height: 35px;
		height: 35px;
		text-align: right;
	}
	.doctorTypeContent {
		border: 0px;
		float: left;
		width: auto;
		line-height: 35px;
		height: 35px;
		text-align: right;
	}
</style>
<script type="text/javascript">

	$(function () {
		$("#doctorCategory").change(function(){
			changeSpe()
			changRotation();
		});
		$("#spe").change(function(){
			changRotation();
			getRotationInfo();
		});
		$("#rotationFlow").change(function(){
			getRotationInfo();
		});
		$("#sessionNumber").change(function(){
			getRotationInfo();
		});
		$("#selectYear").change(function(){
			changeYear();
			getDoctorInfo();
		});
		changeSpe();
		changRotation();
		changeYear();
	});
	function changeYear(){
		var selectYear = $("#selectYear").val()||"";
		if(selectYear=="One")
		{
			$(".One").show();
			$(".Two").hide();
			$(".Three").hide();
		}
		if(selectYear=="Two")
		{
			$(".One").show();
			$(".Two").show();
			$(".Three").hide();
		}
		if(selectYear=="Three")
		{
			$(".One").show();
			$(".Two").show();
			$(".Three").show();
		}
	}

	function getDoctorInfo() {
		var doctorCategory=$("#doctorCategory").val()||"";
		if(doctorCategory=="")
		{
			jboxTip("请选择培训类别！");
			return false;
		}
		var sessionNumber=$("#sessionNumber").val()||"";
		if(sessionNumber=="")
		{
			jboxTip("请选择年级！");
			return false;
		}
		var trainingSpeId=$("#spe").val()||"";
		if(trainingSpeId=="")
		{
			jboxTip("请选择专业！");
			return false;
		}
		var rotationFlow=$("#rotationFlow").val()||"";
		if(rotationFlow=="")
		{
			jboxTip("请选择轮转方案！");
			return false;
		}
		var selectYear=$("#selectYear").val()||"";
		if(selectYear=="")
		{
			jboxTip("请选择培养年限！");
			return false;
		}
		getStudents(doctorCategory,trainingSpeId,selectYear,sessionNumber,rotationFlow);
	}
	//获取相关配置信息
	function getRotationInfo() {
		var doctorCategory=$("#doctorCategory").val()||"";
		if(doctorCategory=="")
		{
			jboxTip("请选择培训类别！");
			return false;
		}
		var sessionNumber=$("#sessionNumber").val()||"";
		if(sessionNumber=="")
		{
			jboxTip("请选择年级！");
			return false;
		}
		var trainingSpeId=$("#spe").val()||"";
		if(trainingSpeId=="")
		{
			jboxTip("请选择专业！");
			return false;
		}
		var rotationFlow=$("#rotationFlow").val()||"";
		if(rotationFlow=="")
		{
			jboxTip("请选择轮转方案！");
			return false;
		}
		var selectYear=$("#selectYear").val()||"";
		if(selectYear=="")
		{
			jboxTip("请选择培养年限！");
			return false;
		}
		getRotationType(sessionNumber,rotationFlow);
		getRotationStartDate(sessionNumber);
		getStudents(doctorCategory,trainingSpeId,selectYear,sessionNumber,rotationFlow);
	}
	function getStudents(doctorCategoryId,trainingSpeId,selectYear,sessionNumber,rotationFlow)
	{
		$("#students").html("");
		$("#arrangeResults").html("");
		var data={
			doctorCategoryId:doctorCategoryId,
			trainingSpeId:trainingSpeId,
			selectYear:selectYear,
			rotationFlow:rotationFlow,
			sessionNumber:sessionNumber
		}
		jboxPostLoad("students" ,"<s:url value='/sch/arrangeResult/students'/>" ,data , true);
	}
	function getRotationStartDate(sessionNumber) {

		var url = "<s:url value='/sch/arrangeResult/getRotationStartDate'/>?sessionNumber="+sessionNumber;
		jboxPost(url,null,function(resp){
			if(resp == "请选择年级！")
			{
				jboxTip("请选择年级！");
				$("#errorInfo2").hide();
				$("#startDate").hide();
			}else if(resp == ""){
				$("#errorInfo2").show();
				$("#startDate").hide();
			}else{
				$("#startDate span").html(resp);
				$("#errorInfo2").hide();
				$("#startDate").show();
			}
		},function(resp){},false);
	}
	function getRotationType(sessionNumber, rotationFlow) {
		$("#cycleTypeId").val("");
		var url = "<s:url value='/sch/arrangeResult/getRotationType'/>?sessionNumber="+sessionNumber+"&rotationFlow="+rotationFlow;
		jboxPost(url,null,function(resp){
			if(resp == ""){
				$("#errorInfo1").show();
				$("#NotAll").hide();
				$("#AllDiv").hide();
				$("#cycleTypeId").val(resp);
			}else if(resp == "OneYear"){
				$("#errorInfo1").hide();
				$("#NotAll").show();
				$("#AllDiv").hide();
				$("#cycleTypeId").val(resp);
			}else if(resp == "AllYear"){
				$("#errorInfo1").hide();
				$("#NotAll").hide();
				$("#AllDiv").show();
				$("#cycleTypeId").val(resp);
			}else{
				jboxTip(resp);
				$("#errorInfo1").hide();
				$("#NotAll").hide();
				$("#AllDiv").hide();
			}
		},function(resp){},false);
	}
	function changeSpe() {
		var doctorCategory=$("#doctorCategory").val()||"";
		$("#spe").empty();
		$("#spe").append($("."+doctorCategory).clone());
	}
	function changRotation()
	{
		var speId=$("#spe").val()||"";
		var url = "<s:url value='/sch/arrangeResult/changRotation'/>?speId="+speId;
		jboxGet(url, null, function(resp){
			$("select[name=rotationFlow] option[value != '']").remove();
			if(resp!=""){
				var dataObj = eval("("+resp+")");
				for(var i = 0; i<dataObj.length;i++){
					var speId = dataObj[i].rotationFlow;
					var speName = dataObj[i].rotationName;
					$option =$("<option></option>");
					$option.attr("value",speId);
					$option.text(speName);
					$("select[name=rotationFlow]").append($option);
				}
				getRotationInfo();
			}
		}, null , false);
	}
	function syncArrange(){
		if(!$("#errorInfo1").is(":hidden")){
			jboxTip("请先配置排班方式！");
			return ;
		}
		if(!$("#errorInfo2").is(":hidden")){
			jboxTip("请先设置排班开始时间！");
			return ;
		}
		var cycleYear=$("input[name='cycleYear']:checked").val()||"";
		if(""==cycleYear)
		{
			jboxTip("请选择导出的排班范围！");
			return ;
		}
		var orgFlow=$("#orgFlow").val()||"";
		var startDate=$("#startDate").find("span").html()||"";
		var rotationFlow=$("#rotationFlow").val()||"";
		var sessionNumber=$("#sessionNumber").val()||"";
		var selectYear=$("#selectYear").val()||"";
		if(selectYear=="")
		{
			jboxTip("请选择培养年限！");
			return false;
		}
		if(""==rotationFlow)
		{
			jboxTip("请选择轮转方案！");
			return ;
		}
		if($(".selUser").length<=0)
		{
			jboxTip("请选择需要导出的学员！");
			return ;
		}
		var doctors=[];
		var b=true;
		var name="";
		$(".selUser").each(function(){
			var doctor={};
			doctor.userName=$(this).attr("userName")||"";
			doctor.userFlow=$(this).attr("userFlow")||"";
			doctor.selDeptFlag=$(this).attr("selDeptFlag")||"";
			doctor.schFlag=$(this).attr("schFlag")||"";
			doctor.selAllFlag=$(this).attr("selAllFlag")||"";
			doctor.schAllFlag=$(this).attr("schAllFlag")||"";
			doctor.selOneFlag=$(this).attr("selOneFlag")||"";
			doctor.schOneFlag=$(this).attr("schOneFlag")||"";
			doctor.selTwoFlag=$(this).attr("selTwoFlag")||"";
			doctor.schTwoFlag=$(this).attr("schTwoFlag")||"";
			doctor.selThreeFlag=$(this).attr("selThreeFlag")||"";
			doctor.schThreeFlag=$(this).attr("schThreeFlag")||"";
			if(cycleYear=="All"&&doctor.schAllFlag=="N"
					||cycleYear=="One"&&doctor.schOneFlag=="N"
					||cycleYear=="Two"&&doctor.schTwoFlag=="N"
					||cycleYear=="Three"&&doctor.schThreeFlag=="N"
			)
			{
				b=false;
				name+=doctor.userName+",";
			}
			doctors.push(doctor);
		});
		if(name!="")
		{
			name=name.substr(0,name.length-1);
		}
		if(!b)
		{
			jboxTip(name+"等学员未生成排班，请不要选中！");
			return;
		}
		var cycleTypeId=$("#cycleTypeId").val();
		var data={};
		data.rotationFlow=rotationFlow;
		data.sessionNumber=sessionNumber;
		data.startDate=startDate;
		data.cycleTypeId=cycleTypeId;
		data.orgFlow=orgFlow;
		data.cycleYear=cycleYear;
		data.selectYear=selectYear;
		data.doctors=doctors;
		jboxConfirm("确认导入排班结果至过程系统？",function(){
			jboxPostJson("<s:url value='/sch/arrangeResult/syncArrange'/>",JSON.stringify(data),function(resp){

			},null,true);
		},null);
	}
	function saveArrange()
	{
		if(!$("#errorInfo1").is(":hidden")){
			jboxTip("请先配置排班方式！");
			return ;
		}
		if(!$("#errorInfo2").is(":hidden")){
			jboxTip("请先设置排班开始时间！");
			return ;
		}
		var cycleYear=$("input[name='cycleYear']:checked").val()||"";
		if(""==cycleYear)
		{
			jboxTip("请选择排班范围！");
			return ;
		}
		var orgFlow=$("#orgFlow").val()||"";
		var startDate=$("#startDate").find("span").html()||"";
		var rotationFlow=$("#rotationFlow").val()||"";
		var sessionNumber=$("#sessionNumber").val()||"";
		var selectYear=$("#selectYear").val()||"";
		if(selectYear=="")
		{
			jboxTip("请选择培养年限！");
			return false;
		}
		if(""==rotationFlow)
		{
			jboxTip("请选择轮转方案！");
			return ;
		}
		if(""==startDate)
		{
			jboxTip("请先设置排班开始时间！");
			return ;
		}
		if($(".selUser").length<=0)
		{
			jboxTip("请选择需要排班的学员！");
			return ;
		}
		var doctors=[];
		var b=true;
		var name="";
		$(".selUser").each(function(){
			var doctor={};
			doctor.userName=$(this).attr("userName")||"";
			doctor.userFlow=$(this).attr("userFlow")||"";
			doctor.selDeptFlag=$(this).attr("selDeptFlag")||"";
			doctor.schFlag=$(this).attr("schFlag")||"";
			doctor.selAllFlag=$(this).attr("selAllFlag")||"";
			doctor.schAllFlag=$(this).attr("schAllFlag")||"";
			doctor.selOneFlag=$(this).attr("selOneFlag")||"";
			doctor.schOneFlag=$(this).attr("schOneFlag")||"";
			doctor.selTwoFlag=$(this).attr("selTwoFlag")||"";
			doctor.schTwoFlag=$(this).attr("schTwoFlag")||"";
			doctor.selThreeFlag=$(this).attr("selThreeFlag")||"";
			doctor.schThreeFlag=$(this).attr("schThreeFlag")||"";
			if(cycleYear=="All"&&doctor.schAllFlag=="Y"
			||cycleYear=="One"&&doctor.schOneFlag=="Y"
			||cycleYear=="Two"&&doctor.schTwoFlag=="Y"
			||cycleYear=="Three"&&doctor.schThreeFlag=="Y"
			)
			{
				b=false;
				name+=doctor.userName+",";
			}
			doctors.push(doctor);
		});
		if(name!="")
		{
			name=name.substr(0,name.length-1);
		}
		if(!b)
		{
			jboxTip(name+"等学员已经有排班记录，请先删除！");
			return;
		}
		var cycleTypeId=$("#cycleTypeId").val();
		var data={};
		data.rotationFlow=rotationFlow;
		data.sessionNumber=sessionNumber;
		data.startDate=startDate;
		data.cycleTypeId=cycleTypeId;
		data.orgFlow=orgFlow;
		data.cycleYear=cycleYear;
		data.selectYear=selectYear;
		data.doctors=doctors;
		console.log(JSON.stringify(data));
		jboxConfirm("排班时间可能较长，请耐心等候?", function () {
			jboxPostJsonWait("<s:url value='/sch/arrangeResult/saveArrange'/>", JSON.stringify(data), function (resp) {
				if (resp == '${GlobalConstant.OPRE_SUCCESSED}') {
//					location.reload(true);

					jboxTip("排班成功！请点击【查看】查询排班结果！");
					$(".selUser").each(function(){
						if(cycleYear=="All")
						{
							$(this).attr("schAllFlag","Y");
							$(this).attr("schOneFlag","Y");
							$(this).attr("schTwoFlag","Y");
							$(this).attr("schThreeFlag","Y");
						}
						if(cycleYear=="One")
						{
							$(this).attr("schOneFlag","Y");
						}
						if(cycleYear=="Two")
						{
							$(this).attr("schTwoFlag","Y");
						}
						if(cycleYear=="Three")
						{
							$(this).attr("schThreeFlag","Y");
						}
					});
					<%--jboxPostJsonLoad("arrangeResults" ,"<s:url value='/sch/arrangeResult/doctorResults'/>" ,JSON.stringify(data) , true);--%>
				}else {
					jboxTip(resp);
				}
			}, null, false);
		}, null);
	}
	function delArrangeResult()
	{
		var cycleYear=$("input[name='cycleYear']:checked").val()||"";
		var selectYear=$("#selectYear").val()||"";
		if(selectYear=="")
		{
			jboxTip("请选择培养年限！");
			return false;
		}
		if(""==cycleYear)
		{
			jboxTip("请选择排班范围！");
			return ;
		}
		if($(".selUser").length<=0)
		{
			jboxTip("请选择需要删除排班的学员！");
			return ;
		}
		var doctors=[];
		var b=true;
		var name="";
		$(".selUser").each(function(){
			var doctor={};
			doctor.userName=$(this).attr("userName")||"";
			doctor.userFlow=$(this).attr("userFlow")||"";
			doctor.selDeptFlag=$(this).attr("selDeptFlag")||"";
			doctor.schFlag=$(this).attr("schFlag")||"";
			doctor.selAllFlag=$(this).attr("selAllFlag")||"";
			doctor.schAllFlag=$(this).attr("schAllFlag")||"";
			doctor.selOneFlag=$(this).attr("selOneFlag")||"";
			doctor.schOneFlag=$(this).attr("schOneFlag")||"";
			doctor.selTwoFlag=$(this).attr("selTwoFlag")||"";
			doctor.schTwoFlag=$(this).attr("schTwoFlag")||"";
			doctor.selThreeFlag=$(this).attr("selThreeFlag")||"";
			doctor.schThreeFlag=$(this).attr("schThreeFlag")||"";
			if(cycleYear=="All"&&doctor.schAllFlag=="N"
			||cycleYear=="One"&&doctor.schOneFlag=="N"
			||cycleYear=="Two"&&doctor.schTwoFlag=="N"
			||cycleYear=="Three"&&doctor.schThreeFlag=="N"
			)
			{
				b=false;
				name+=doctor.userName+",";
			}
			doctors.push(doctor);
		});
		if(name!="")
		{
			name=name.substr(0,name.length-1);
		}
		if(!b)
		{
			jboxTip(name+"等学员无排班记录，请不要选中！");
			return;
		}
		var cycleTypeId=$("#cycleTypeId").val();
		var orgFlow=$("#orgFlow").val()||"";
		var data={};
		data.cycleTypeId=cycleTypeId;
		data.orgFlow=orgFlow;
		data.cycleYear=cycleYear;
		data.selectYear=selectYear;
		data.doctors=doctors;
		console.log(JSON.stringify(data));

		jboxConfirm("确认删除学员排班记录？", function () {
			jboxPostJsonWait("<s:url value='/sch/arrangeResult/delArrangeResult'/>", JSON.stringify(data), function (resp) {
				if (resp == '${GlobalConstant.OPRE_SUCCESSED}') {
//					location.reload(true);
					$("#arrangeResults").html("");
					$(".selUser").each(function(){
						if(cycleYear=="All")
						{
							$(this).attr("schAllFlag","N");
							$(this).attr("schOneFlag","N");
							$(this).attr("schTwoFlag","N");
							$(this).attr("schThreeFlag","N");
						}
						if(cycleYear=="One")
						{
							$(this).attr("schOneFlag","N");
						}
						if(cycleYear=="Two")
						{
							$(this).attr("schTwoFlag","N");
						}
						if(cycleYear=="Three")
						{
							$(this).attr("schThreeFlag","N");
						}
					});
					<%--jboxPostJsonLoad("arrangeResults" ,"<s:url value='/sch/arrangeResult/doctorResults'/>" ,JSON.stringify(data) , true);--%>
				}
					jboxTip(resp);
			}, null, false);
		}, null);
	}

	function selectAll() {
		if($("#checkAll").is(':checked')){
			$(".canClick").addClass("selUser");
		}else{
			$(".canClick").removeClass("selUser");
		}
	}
</script>
</head>
<body>
	<div class="mainright">
		<div class="content">
			<div class="title1 clearfix">
				<form id="searchForm" method="post" action="<s:url value='/sch/sel/doctorList'/>">
					<input id="orgFlow" type="hidden" name="orgFlow" value="${orgFlow}"/>
					<input id="cycleTypeId" type="hidden" name="cycleTypeId" />
					<div class="queryDiv">
						<div class="inputDiv">
							<label class="qlable">培训类别：</label>
							<select id="doctorCategory" name="doctorCategoryId" class="qselect">
								<c:forEach items="${recDocCategoryEnumList}" var="category">
									<c:set var="res_doctor_category_key" value="res_doctor_category_${category.id }"/>
									<c:if test="${sysCfgMap[res_doctor_category_key]==GlobalConstant.FLAG_Y }">
										<option value="${category.id}" ${param.doctorCategoryId eq category.id?'selected':''}>${category.name}</option>
									</c:if>
								</c:forEach>
							</select>
						</div>
						<div class="inputDiv">
							<label class="qlable">培训专业：</label>
							<select id="spe" name="trainingSpeId" class="qselect">
							</select>
							<div style="display: none;">
								<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
									<option
											class="
								${recDocCategoryEnumDoctor.id} |
								${recDocCategoryEnumGraduate.id} |
								${recDocCategoryEnumIntern.id} |
								${recDocCategoryEnumScholar.id} |
								${recDocCategoryEnumSpecialist.id} |
								${recDocCategoryEnumGeneralDoctor.id} |
								" value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
								</c:forEach>
								<c:forEach items="${dictTypeEnumWMFirstList}" var="dict">
									<option class="${recDocCategoryEnumWMFirst.id}" value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
								</c:forEach>
								<c:forEach items="${dictTypeEnumWMSecondList}" var="dict">
									<option class="${recDocCategoryEnumWMSecond.id}" value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
								</c:forEach>
								<c:forEach items="${dictTypeEnumAssiGeneralList}" var="dict">
									<option class="${recDocCategoryEnumAssiGeneral.id}" value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
								</c:forEach>
								<c:forEach items="${dictTypeEnumChineseMedicineList}" var="dict">
									<option class="${recDocCategoryEnumChineseMedicine.id}" value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
								</c:forEach>
								<c:forEach items="${dictTypeEnumTCMGeneralList}" var="dict">
									<option class="${recDocCategoryEnumTCMGeneral.id}" value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
								</c:forEach>
								<c:forEach items="${dictTypeEnumTCMAssiGeneralList}" var="dict">
									<option class="${recDocCategoryEnumTCMAssiGeneral.id}" value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
								</c:forEach>
							</div>
						</div>
						<div class="inputDiv">
							<label class="qlable">轮转方案：</label>
							<select id="rotationFlow" name="rotationFlow" class="qselect" >

							</select>
						</div>
						<div class="inputDiv">
							<label class="qlable">年&#12288;&#12288;级：</label>
							<select id="sessionNumber" name="sessionNumber" class="qselect" >
								<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict" varStatus="num">
									<option value="${dict.dictName}" ${param.sessionNumber eq dict.dictName?'selected':''}>${dict.dictName}</option>
								</c:forEach>
							</select>
						</div>
						<div class="inputDiv">
							<label class="qlable">培养年限：</label>
							<select class="qselect" id="selectYear" name="selectYear">
								<c:forEach items="${schSelYearEnumList}" var="dict" varStatus="num">
									<option value="${dict.id}" ${selectYear eq dict.id?'selected':''}>${dict.name}</option>
								</c:forEach>
							</select>
						</div>
						<div id="startDate" class="lastDiv" style="min-width: 200px;text-align: left;display: none;">
							&#12288;&#12288;排班开始时间：<span></span>
						</div>
						<div id="AllDiv" class="lastDiv" style="min-width: 250px;text-align: left;display: none;">
							&#12288;&#12288;排班范围：<input type="radio" name="cycleYear" value="All" id="All"> <label for="All">全年</label>
						</div>
						<div id="NotAll" class="lastDiv" style="min-width: 350px;text-align: left;display: none;">
							&#12288;&#12288;排班范围：<input type="radio" name="cycleYear" value="One" class="One" id="One"> <label  class="One" for="One">第一年</label>
							<input type="radio" name="cycleYear" value="Two" class="Two" id="Two"> <label class="Two" for="Two">第二年</label>
							<input type="radio" name="cycleYear" value="Three" class="Three" id="Three"> <label class="Three" for="Three">第三年</label>
						</div>
						<c:if test="${sysCfgMap['sch_inRes_flag'] eq GlobalConstant.FLAG_Y}">
							<div class="lastDiv" style="min-width:182px;max-width:182px;">
								<input type="button" value="导出排班结果至过程系统" class="searchInput" onclick="syncArrange();"/>
							</div>
						</c:if>
					</div>
					<div id="errorInfo1" class="queryDiv" style="display: none;">
						&#12288;&#12288;<font style="color:red;">未配置排班方式</font>
					</div>
					<div id="errorInfo2" class="queryDiv" style="display: none;">
						&#12288;&#12288;<font style="color:red;">未配置排班开始时间</font>
					</div>
				</form>
			</div>
			<div class="content" style="width: 95%;margin-left:10px;">
				<table class="basic" style="width:100%;">
					<tr style="width: 100%;">
						<td style="width: 24%">
							<p>
							<h2>学员
							<input type="checkbox" name="checkAll" class="checkAll" value="Y" id="checkAll" onclick="selectAll()"/>
							<label for="checkAll">全选</label></h2>
							</p>
						</td>
						<td style="width: 74%">
							<p>
							<h2>排班信息</h2>
							</p>
						</td>
					</tr>
					<tr>
						<td style="padding-left: 0px;">
							<div id="students" style="width: 100%;min-height: 350px;max-height: 350px;overflow: auto;">

							</div>
						</td>
						<td style="padding-left: 0px;">
							<div id="arrangeResults" style="width: 100%;min-height: 350px;max-height: 350px;overflow: auto;">

							</div>
						</td>
					</tr>
					<tr>
						<td style="text-align: left;" colspan="2">
							<input type="button" value="自动排班" class="search" onclick="saveArrange();"/>
							<input type="button" value="删除排班" class="search" onclick="delArrangeResult();"/>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>