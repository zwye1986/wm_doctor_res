<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<style type="text/css">
	.label td{
		width:120px;height:35px;text-align:center;border:1px solid #E3E3E3;cursor:pointer;
	}
	.label td.on{background-color:#4195C5;color:#fff;}
</style>
<script type="text/javascript">
	$(function(){
		$(".label td").bind('click',function(){
			$(this).attr("class","on");
			$(this).siblings("td").removeAttr("class");
//			var index = $(this).index();
//			$(".labelDiv").each(function(i){
//				if(i == index){
//					$(this).show();
//					$(this).siblings(".labelDiv").hide();
//				}
//			});
			$(".labelDiv").hide();
			//标签查询动作
			if($(this).attr("value") == "p1"){
				//预约学员信息
				toPage1(1);
				$("#appointDiv").show();
			}else if($(this).attr("value") == "p2") {
				//签到管理
				toPage2(1);
				$("#signDiv").show();
			}else if($(this).attr("value") == "p3") {
				//成绩管理
				toPage3(1);
				$("#gradeDiv").show();
			}else if($(this).attr("value") == "p4"){
				//课程评价
				jboxLoad("courseEvaluationDiv",'<s:url value="/lcjn/evaluate/coursEvaluation"/>?courseFlow=${param.courseFlow}',true);
				$("#courseEvaluationDiv").show();
			}else if($(this).attr("value") == "p5"){
				//教师评价
				jboxLoad("teacherEvaluationDiv",'<s:url value="/lcjn/evaluate/teacherEvalutionList"/>?courseFlow=${param.courseFlow}',true);
				$("#teacherEvaluationDiv").show();
			}else if($(this).attr("value") == "p6"){
				//成本核算
				jboxLoad("costDiv",'<s:url value="/lcjn/cost/list"/>?courseFlow=${param.courseFlow}',true);
				$("#costDiv").show();
			}
		});
	});
	/*******************************预约学员信息***********************************/
	function toPage1(page){
		$("#currentPage1").val(page);
		jboxPostLoad("appointDiv","<s:url value="/lcjn/lcjnDoctorTrainInfo/orderInfoList"/>",$("#appointForm").serialize(),true);
	}
	//复选框事件
	//全选、取消全选、反选的事件
	function selectAll(){
		var chsub = $("input[type='checkbox'][id='subcheck']").length; //获取subcheck的个数
		var checkedsub = $("input[type='checkbox'][id='subcheck']:checked").length; //获取选中的subcheck的个数
		if (checkedsub == chsub || checkedsub == 0) {
			if ($("#SelectAll").attr("checked")) {
				$(":checkbox").attr("checked", true);
			} else {
				$(":checkbox").attr("checked", false);
			}
		}else {
			$("input[type='checkbox'][id='subcheck']").each(function(){
				if($(this).attr("checked"))
				{
					$(this).removeAttr("checked");
				}
				else
				{
					$(this).attr("checked","true");
				}
			});
		}
	}
	//子复选框的事件
	function setSelectAll(){
		//当没有选中某个子复选框时，SelectAll取消选中
		if (!$("#subcheck").checked) {
			$("#SelectAll").attr("checked", false);
		}
		var chsub = $("input[type='checkbox'][id='subcheck']").length; //获取subcheck的个数
		var checkedsub = $("input[type='checkbox'][id='subcheck']:checked").length; //获取选中的subcheck的个数
		if (checkedsub == chsub) {
			$("#SelectAll").attr("checked", true);
		}
	}
	function auditOpt(){
		var checkLen = $(":checkbox[class='check']:checked").length;
		if(checkLen == 0){
			jboxTip("请勾选预约学员信息！");
			return;
		}else{
			var len = 0;
			$(":checkbox[class='check']:checked").each(function(){
				if($(this).attr("statusId") != "Passing"){
					len ++;
				}
			});
			if(len > 0){
				jboxTip("只能审核待审核状态的记录！");
				return;
			}
		}
		var recordLst = [];
		$(":checkbox[class='check']:checked").each(function(){
			recordLst.push(this.value);
		})
		jboxButtonConfirm("学员能否成功预约该培训课程？","通过","不通过", function(){//通过
			var json = {"recordLst":recordLst,"auditStatusId":"Passed"};
			var url = "<s:url value='/lcjn/lcjnDoctorTrainInfo/auditAppoint'/>";
			jboxPost(url, "jsonData="+JSON.stringify(json), function(resp){
				setTimeout(function(){
					jboxPostLoad("appointDiv","<s:url value="/lcjn/lcjnDoctorTrainInfo/orderInfoList"/>",$("#appointForm").serialize(),true);
				},1000);
			}, null, true);
		},function(){//不通过
			var json = {"recordLst":recordLst,"auditStatusId":"UnPassed"};
			jboxOpen("<s:url value='/jsp/lcjn/doctorInfoManage/reason.jsp'/>?jsonData="+encodeURI(JSON.stringify(json)),"信息审核",250,150,true);
		},300);
	}
	function editOneInfo(recordFlow,type){
		var recordLst = [];
		recordLst.push(recordFlow);
		if(type == "audit"){
			jboxButtonConfirm("学员能否成功预约该培训课程？","通过","不通过", function(){//通过
				var json = {"recordLst":recordLst,"auditStatusId":"Passed"};
				var url = "<s:url value='/lcjn/lcjnDoctorTrainInfo/auditAppoint'/>";
				jboxPost(url, "jsonData="+JSON.stringify(json), function(resp){
					setTimeout(function(){
						jboxPostLoad("appointDiv","<s:url value="/lcjn/lcjnDoctorTrainInfo/orderInfoList"/>",$("#appointForm").serialize(),true);
					},300);
				}, null, true);
			},function(){//不通过
				var json = {"recordLst":recordLst,"auditStatusId":"UnPassed"};
				jboxOpen("<s:url value='/jsp/lcjn/doctorInfoManage/reason.jsp'/>?jsonData="+encodeURI(JSON.stringify(json)),"信息审核",250,150,true);
			},300);
		}
		if(type == "edit"){
			jboxConfirm("确认撤销成待审核状态？",function() {//通过
				var json = {"recordLst": recordLst, "auditStatusId": "Passing"};
				var url = "<s:url value='/lcjn/lcjnDoctorTrainInfo/auditAppoint'/>";
				jboxPost(url, "jsonData="+JSON.stringify(json), function(resp){
					if("信息审核成功！"== resp){
						jboxTip("状态撤销成功！");
					}else{
						jboxTip("状态撤销失败！");
					}
					setTimeout(function(){
						jboxPostLoad("appointDiv","<s:url value="/lcjn/lcjnDoctorTrainInfo/orderInfoList"/>",$("#appointForm").serialize(),true);
					},1000);
				}, null, false);
			});
		}
	}
	function revokeAudit(recordFlow,auditStatus){
		var courseFlow=$("input[name='courseFlow']").val();
		if(auditStatus == 'UnPassed'){
			var url = "<s:url value='/lcjn/lcjnDoctorTrainInfo/selectOrderNum?courseFlow='/>" + courseFlow;
			jboxPost(url, null, function (resp) {
				if("N"== resp){
					jboxTip("该课程预约人数已满，无法撤销！")
					return;
				}
				if("Y"== resp){
					editOneInfo(recordFlow,'edit');
				}
			}, null, false);
		}else{
			editOneInfo(recordFlow,'edit');
		}
	}
	function addDoctors(){
		var courseFlow=$("input[name='courseFlow']").val();
		var courseName=$("input[name='courseName']").val();
		jboxStartLoading();
		jboxOpen("<s:url value='/lcjn/lcjnDoctorTrainInfo/showLocalDoctors'/>?courseFlow="+courseFlow+"&courseName="+encodeURI(encodeURI(courseName)), "添加学员", 400, 380,false);
	}
	function exportDoctorInfo(){
		var auditStatusId=$("select[name='auditStatusId']").val();
		var courseFlow='${courseFlow}';
		var url = "<s:url value='/lcjn/lcjnDoctorTrainInfo/exportDoctorInfo'/>?courseFlow="+courseFlow+"&auditStatusId="+auditStatusId;
		jboxTip("导出中....");
		$("#exportA1").attr("href",url);
		$("#outToExcelSpan1").click();
	}
	/*******************************签到管理***********************************/
	//jq代码
	function toPage2(page){
		$("#currentPage2").val(page);
		jboxPostLoad("signDiv","<s:url value="/lcjn/lcjnDoctorTrainInfo/signList"/>",$("#signForm").serialize(),true);
	}
	function exportSign(){
		var sign=$("select[name='sign']").val();
		var userName=$("input[name='userName']").val();
		var courseFlow=$("input[name='courseFlow']").val();
		var url = "<s:url value='/lcjn/lcjnDoctorTrainInfo/exportSign'/>?courseFlow="+courseFlow+"&userName="+userName+"&sign="+sign;
		jboxTip("导出中....");
		$("#exportA2").attr("href",url);
		$("#outToExcelSpan2").click();

	}
	/*******************************成绩管理***********************************/
	//jq代码
	function toPage3(page){
		$("#currentPage3").val(page);
		jboxPostLoad("gradeDiv","<s:url value="/lcjn/lcjnDoctorTrainInfo/doctorScoreList"/>",$("#gradeForm").serialize(),true);
	}

	function editOneInfo1(courseFlow,doctorFlow){
		jboxStartLoading();
		jboxOpen("<s:url value='/lcjn/lcjnDoctorTrainInfo/showDoctorScore'/>?courseFlow="+courseFlow+"&doctorFlow="+doctorFlow, "编辑学员成绩", 400, 240);
	}
	function releaseScore(){
		var courseFlow=$("input[name='courseFlow']").val();
		jboxConfirm("确认发布该课程成绩吗？",function () {
			var url = "<s:url value='/lcjn/lcjnDoctorTrainInfo/releaseScore?courseFlow='/>" + courseFlow;
			jboxPost(url,null,function(resp){
				jboxTip(resp);
			});
		});
	}
	function importScore(){
		var courseFlow='${courseFlow}';
		var url = "<s:url value='/lcjn/lcjnDoctorTrainInfo/showImportScore'/>?courseFlow="+courseFlow;
		typeName="导入学员成绩";
		jboxOpen(url, typeName, 380, 180);
	}
	function exportScore(){
		var enteringStatusId=$("select[name='enteringStatusId']").val();
		var appointDoctorName=$("input[name='appointDoctorName']").val();
		var courseFlow='${courseFlow}';
		var url = "<s:url value='/lcjn/lcjnDoctorTrainInfo/exportScore'/>?courseFlow="+courseFlow+"&doctorName="+appointDoctorName+"&enteringStatusId="+enteringStatusId;
		jboxTip("导出中....");
		$("#exportA3").attr("href",url);
		$("#outToExcelSpan3").click();
	}
	/*******************************课程评价***********************************/
	//jq代码

	/*******************************教师评价***********************************/
	//jq代码

	/*******************************成本核算***********************************/
	//jq代码

</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div style="margin:20px 0px 10px;">
			<table class="label">
				<tr>
					<td value="p1" class="on">学员信息</td>
					<td value="p2">签到管理</td>
					<td value="p3">成绩管理</td>
					<td value="p4">课程评价</td>
					<td value="p5">教师评价</td>
					<td id="costButton" value="p6">成本核算</td>
				</tr>
			</table>
		</div>
		<div style="font:16px bold;" id="speDiv">课程名称：${courseName}</div>
		<div id="appointDiv" class="labelDiv">
			<form id="appointForm" action="<s:url value="/lcjn/lcjnDoctorTrainInfo/orderInfoList"/>" method="post">
				<div class="choseDivNewStyle">
				<a class="btn" id="exportA1" type="hidden"><span id="outToExcelSpan1"> </span></a>
				<input type="hidden" name="courseFlow" id="courseFlow" value="${courseFlow}"/>
				<input type="hidden" name="courseName" value="${courseName}"/>
				<input id="currentPage1" type="hidden" name="currentPage1" value="1"/>
				<table class="basic" style="width:100%;border:0px;margin:10px 0px;">
					<tr>
						<td style="border:0px;">
							<span style="margin-left: -10px;"></span>审核状态：
							<select name="auditStatusId" style="width:137px;" class="select">
								<option value="">全部</option>
								<c:forEach items="${lcjnAuditStatusEnumList}" var="status">
									<option value="${status.id}" ${param.auditStatusId eq status.id ?'selected':''}>${status.name}</option>
								</c:forEach>
							</select>
							<span style="padding-left:20px;"></span>
							<input type="button" class="search" value="查&#12288;询" onclick="toPage1(1)"/>
							<input type="button" class="search" value="添&#12288;加" onclick="addDoctors()"/>
							<input type="button" class="search" value="导&#12288;出" onclick="exportDoctorInfo()"/><br/>
							<input type="checkbox" id="SelectAll" onclick="selectAll()" style="margin-left: -10px;">&nbsp;全选/反选&nbsp;
							<input type="button" class="search" value="审&#12288;核" onclick="auditOpt()"/>
						</td>
					</tr>
				</table>
				</div>
			</form>
			<table class="xllist" style="width:100%;">
				<colgroup>
					<col width="5%"/>
					<col width="10%"/>
					<col width="10%"/>
					<col width="5%"/>
					<col width="10%"/>
					<col width="10%"/>
					<col width="10%"/>
					<col width="10%"/>
					<col width="10%"/>
					<col width="10%"/>
					<col width="10%"/>
				</colgroup>
				<tr>
					<th>序号</th>
					<th>用户名</th>
					<th>姓名</th>
					<th>性别</th>
					<th>培训专业</th>
					<th>身份证号</th>
					<th>工作单位</th>
					<th>所在科室</th>
					<th>联系方式</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${doctorOrderInfoList}" var="info" varStatus="i">
					<tr>
						<td><input type="checkbox" id="subcheck" class="check" value="${info.RECORD_FLOW}" statusId="${info.AUDIT_STATUS_ID}" onclick="setSelectAll()">&nbsp;${i.index + 1}</td>
						<td>${info.USER_CODE}</td>
						<td>${info.USER_NAME}</td>
						<td>${info.SEX_NAME}</td>
						<td>${info.LCJN_SPE_NAME}</td>
						<td>${info.ID_NO}</td>
						<td>${info.ORG_NAME}</td>
						<td>${info.DEPT_NAME}</td>
						<td>${info.USER_PHONE}</td>
						<td>${info.AUDIT_STATUS_NAME}</td>
						<td>
							<c:if test="${info.AUDIT_STATUS_ID eq lcjnAuditStatusEnumPassing}">
								<a onclick="editOneInfo('${info.RECORD_FLOW}','audit')" style="cursor:pointer;color:#4195c5;">审核</a>&#12288;
								<a style="color:#b9c0c6;">撤销</a>
							</c:if>
							<c:if test="${info.AUDIT_STATUS_ID eq lcjnAuditStatusEnumPassed or info.AUDIT_STATUS_ID eq lcjnAuditStatusEnumUnPassed}">
								<a style="color:#b9c0c6;">审核</a>&#12288;
								<a onclick="revokeAudit('${info.RECORD_FLOW}','${info.AUDIT_STATUS_ID}')" style="cursor:pointer;color:#4195c5;">撤销</a>
							</c:if>
							<c:if test="${info.AUDIT_STATUS_ID eq lcjnAuditStatusEnumInvalid}">
								<a style="color:#b9c0c6;">审核</a>&#12288;
								<a style="color:#b9c0c6;">撤销</a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
				<c:if test="${empty doctorOrderInfoList}">
					<tr><td colspan="99">暂无记录</td></tr>
				</c:if>
			</table>
			<div id="detail"></div>
			<div style="margin-top:100px;">
				<c:set var="pageView" value="${pdfn:getPageView(doctorOrderInfoList)}" scope="request"/>
				<pd:pagination toPage="toPage1"/>
			</div>
		</div>
		<div id="signDiv" class="labelDiv" style="display:none;">
			<form id="signForm" action="<s:url value="/lcjn/lcjnDoctorTrainInfo/signList"/>" method="post">
				<div class="choseDivNewStyle">
				<input type="hidden" name="courseFlow" value="${courseFlow}"/>
				<a class="btn" id="exportA2" type="hidden"><span id="outToExcelSpan2"> </span></a>
				<input id="currentPage2" type="hidden" name="currentPage2" value="1"/>
				<table class="basic" style="width:100%;border:0px;margin:10px 0px;">
					<tr>
						<td style="border:0px;">
							<span style="margin-left: -10px;"></span>是否签到：
							<select name="sign" style="width:137px;" class="select">
								<option value="">全部</option>
								<c:forEach items="${lcjnSignStatusEnumList}" var="status">
									<option value="${status.id}" ${param.sign eq status.id ?'selected':''}>${status.name}</option>
								</c:forEach>
							</select>
							<span style="padding-left:10px;"></span>姓名：
							<input type="text" name="userName" value="${param.userName}">
							<span style="padding-left:20px;"></span>
							<input type="button" class="search" value="查&#12288;询" onclick="toPage2(1)"/>
							<input type="button" class="search" value="导&#12288;出" onclick="exportSign()"/>
						</td>
					</tr>
				</table>
				</div>
			</form>
		</div>
		<div id="gradeDiv" class="labelDiv" style="display:none;">
			<form id="gradeForm" action="<s:url value="/lcjn/lcjnDoctorTrainInfo/doctorScoreList"/>" method="post">
				<div class="choseDivNewStyle">
				<a class="btn" id="exportA3" type="hidden"><span id="outToExcelSpan3"> </span></a>
				<input type="hidden" name="courseFlow" value="${courseFlow}"/>
				<input id="currentPage3" type="hidden" name="currentPage3" value="1"/>
				<table class="basic" style="width:100%;border:0px;margin:10px 0px;">
					<tr>
						<td style="border:0px;">
							<span style="margin-left: -10px;"></span>姓名：
							<input type="text" name="appointDoctorName" value="${param.appointDoctorName}">
							<span style="padding-left:10px;"></span>录入状态：
							<select name="enteringStatusId" style="width:137px;" class="select">
								<option value="">全部</option>
								<c:forEach items="${lcjnDoctorScoreEnumList}" var="status">
									<option value="${status.id}" ${param.enteringStatusId eq status.id ?'selected':''}>${status.name}</option>
								</c:forEach>
							</select>
							<span style="padding-left:20px;"></span>
							<input type="button" class="search" value="查&#12288;询" onclick="toPage3(1)"/>
							<input type="button" class="search" value="导&#12288;出" onclick="exportScore()"/>
							<input type="button" class="search" value="成绩导入" onclick="importScore()"/>
							<input type="button" class="search" value="成绩发布" onclick="releaseScore()"/>
						</td>
					</tr>
				</table>
				</div>
			</form>
		</div>
		<div id="courseEvaluationDiv" class="labelDiv" style="display:none;">
			<form id="courseEvaluationForm" method="post">
				<input type="hidden" name="currentPage3" value="1"/>
				<table class="basic" style="width:100%;border:0px;margin: 10px 0px;">

				</table>
			</form>
		</div>
		<div id="teacherEvaluationDiv" class="labelDiv" style="display:none;">
			<form id="teacherEvaluationForm" method="post">
				<table class="basic" style="width:100%;border:0px;margin: 10px 0px;">

				</table>
			</form>
		</div>
		<div id="costDiv" class="labelDiv" style="display:none;">
			<form id="costForm" method="post">
				<table class="basic" style="width:100%;border:0px;margin: 10px 0px;">

				</table>
			</form>
		</div>
	</div>
</div>
</body>	
</html>