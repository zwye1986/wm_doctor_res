<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="false"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_cxselect" value="false"/>
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
<style>
	.cjTab{width:100%;margin-top:10px;border-collapse:collapse;}
	.cjTab td{border:1px solid grey;}
</style>
<script type="text/javascript" src="<s:url value='/js/jquery.jqprint-0.3.js'/>"></script>
<script type="text/javascript">
var courseLists = [];
function saveStudentCourse(){
	jboxConfirm('确认保存?',function(){
	var trs = $('#appendTbody').children();
	var datas =[];
	$.each(trs , function(i , n){
		var recordFlow= $(n).find('[name="recordFlow"]').val();
		var courseFlow = $(n).find('[name="courseFlow"]').val();
		var userFlow = $(n).find('[name="userFlow"]').val();
		var pacificGrade=$(n).find('[name="pacificGrade"]').val();
		var teamEndGrade=$(n).find('[name="teamEndGrade"]').val();
		var grade= $(n).find('[name="courseGrade"]');
		var courseGrade="";
		if(grade.is(":hidden")){
			 pacificGrade="";
			 teamEndGrade="";
			 courseGrade=$(n).find('[name="courseGradeSelect"]').val();
		}else{
			courseGrade=grade.val();
		}
		var gradeTermId=$(n).find('[name="gradeTermId"]').val();
		var studyWayId=$(n).find('[name="studyWayId"]').val();
		var assessTypeId=$(n).find('[name="assessTypeId"]').val();
		var degreeCourseFlag=$(n).find('[name="degreeCourseFlag"]').val();
		var courseTypeId=$(n).find('[name="courseTypeId"]').val();
		var coursePeriod=$(n).find('[name="coursePeriod"]').val();
		var courseCredit=$(n).find('[name="courseCredit"]').val();
		var data = {
				"recordFlow":recordFlow,
				"courseFlow":courseFlow,
				"userFlow":userFlow,
				"studyWayId":studyWayId,
				"assessTypeId":assessTypeId,
				"pacificGrade":pacificGrade,
				"teamEndGrade":teamEndGrade,
				"courseGrade":courseGrade,
				"gradeTermId":gradeTermId,
				"degreeCourseFlag":degreeCourseFlag,
				"courseTypeId":courseTypeId,
				"coursePeriod":coursePeriod,
				"submitFlag":"Y",
				"auditStatusName":"通过",
				"auditStatusId":"Passed",
				"courseCredit":courseCredit
		};
		datas.push(data);
	});
	var url = "<s:url value='/gyxjgl/user/saveStudentCourse'/>";
	jboxStartLoading();
	$("#save").attr("disabled",true);
	jboxPostJson(url,JSON.stringify(datas),function(){
//		window.parent.frames['mainIframe'].window.toPage();
//		jboxClose();
	},null,true);
	});
}
function check(){
	if(!$("#form").validationEngine("validate")){
		return false;
	}
	var r="";
	$("#appendTbody").find("select[name='courseName']").each(function(){
		var result=$(this).val();
		if(result==""){
			r="${GlobalConstant.FLAG_Y}";
		}
	});
	if(r==""){
		saveStudentCourse();
	}else{
		jboxTip("课程名称没有填写完整！确认后保存");
		return false;
	}
}
function currFlow(obj){
	$('[name="currFlowSun"]').val($(":selected",obj).val());
}
function xiaLa(obj){
	var courseFlow=$(":selected",obj).val();
	var num=0;
	var zhiDing=$('[name="currFlowSun"]').val();
	if(courseFlow!=""){
	 <c:forEach items="${eduUserExt.courseExtsList}" var="studentCourseExt">
		if("${studentCourseExt.courseFlow}"==$(":selected",obj).val()){
			num++;
		}
	</c:forEach>
	if(num>=1){
		jboxTip("该课程已经选择！");
		$("[value='"+zhiDing+"']",obj).attr("selected",true);
		return false;
	}
	 if(num<1){
		 var tr=$(obj).closest(".each");
		 $(".courseTypeName",tr).text(courseLists[courseFlow].courseTypeName);
		 $(".coursePeriod",tr).text(courseLists[courseFlow].course.coursePeriod);
		 $(".courseCredit",tr).text(courseLists[courseFlow].course.courseCredit);
		 $(".courseGrade",tr).val(courseLists[courseFlow].studentCourse.courseGrade);
		 $(".pacificGrade",tr).text(courseLists[courseFlow].studentCourse.pacificGrade);
		 $(".teamEndGrade",tr).text(courseLists[courseFlow].studentCourse.teamEndGrade);
		 $(".courseFlow",tr).val(courseFlow);
	 }
	}else{
		 var tr=$(obj).closest(".each");
		 $(".courseTypeName",tr).text("");
		 $(".coursePeriod",tr).text("");
		 $(".courseCredit",tr).text("");
		 $(".courseGrade",tr).val("");
		 $(".pacificGrade",tr).text("");
		 $(".teamEndGrade",tr).text("");
		 $(".courseFlow",tr).val("");
	}
	var zongXue = 0;
    var zongShi = 0; 
	$(".coursePeriod").each(function(i,n){
		if($(n).text()!="" && !isNaN($(n).text())){	
			var numItem = parseInt($(n).text());	
			zongXue+=numItem;		
		}
	});
	$("#s").text(zongXue);
	
	$(".courseCredit").each(function(i,n){
		if($(n).text()!="" && !isNaN($(n).text())){	
			var  courseCredit= parseInt($(n).text());	
			zongShi+=courseCredit;	
		}
	});
	$("#f").text(zongShi);

}
function down(){
	jboxTip("下载中,请稍等...");
	var url = '<s:url value="/gyxjgl/user/print"/>?userFlow=${param.userFlow}';
	window.location.href = url;	
}
//function printFlag(){
//	jboxTip("正在准备打印…");
//	setTimeout(function(){
//		$("#printDiv2").removeAttr("hidden");
//		$("#printDiv2").jqprint({
//			debug: false,
//			importCSS: true,
//			printContainer: true,
//			operaSupport: false
//		});
//		$("#printDiv2").attr("hidden","hidden");
//		jboxEndLoading();
//		return false;
//	},2000);
//}
function printFlag(){
	jboxTip("正在准备打印…");
	var url = '<s:url value="/gyxjgl/user/printYuLian"/>?userFlow=${param.userFlow}';
	jboxStartLoading();
	jboxPost(url, null, function(data) {
		$("#printDiv2").html(data);
		setTimeout(function(){
			var newstr = $("#printDiv2").html();
			var oldstr = document.body.innerHTML;
				document.body.innerHTML =newstr;
				window.print();
				document.body.innerHTML = oldstr;
				jboxEndLoading();
			return false;
		},3000);
	},null,false);
}
$(document).ready(function(){
	$("#s").text($("#zongShi").val());
	$("#f").text($("#zongFen").val());
	<c:forEach items="${eduUserExt.courseExtsList}" var="edu">
		<c:forEach items="${dictTypeEnumGyXjIsPassedList}" var="dict">
			if("${dict.dictId}"=="${edu.courseGrade }"){
				changeGrade("${fn:replace(edu.recordFlow,'.','')}");
				$("#"+"${edu.recordFlow}"+'label').text("${dict.dictName}");
			}
		</c:forEach>
	</c:forEach>
});
function delGrade(isHaveScore,recordFlow){
	jboxConfirm("<font color='red'>删除会影响该学生选课使用，确认删除?</font>", function() {
			var url="<s:url value='/gyxjgl/user/emptyCourse'/>?recordFlow="+recordFlow;
			jboxPost(url,null,function(resp){
				if(resp=="${GlobalConstant.FLAG_Y}"){
					jboxTip("删除成功！！");
					window.location.reload();
					window.parent.frames['mainIframe'].topage();
				}else{
					jboxTip("删除失败！");
				}
			},null,false);
	});
}
function changeAll(obj){
	var result=obj.value;
	if(result){
		$("#appendTbody").find("select[name='gradeTermId']").each(function(){
			if(!this.value){
				$("[value='"+result+"']",this).attr("selected","selected");
			}
		});
	}
}
function changeGrade(recordFlow){
	if($("#"+recordFlow+'Y').is(":hidden")){
		if($("#"+recordFlow+'Y').val()=='NaN'||$("#"+recordFlow+'Y').val()=='Y'||$("#"+recordFlow+'Y').val()=='N' ||$("#"+recordFlow+'Y').val()=='T'){
			$("#"+recordFlow+'Y').val("");
		}
		$("#"+recordFlow+'Y').show();
		$("#"+recordFlow+'N').hide();
	}else{
		$("#"+recordFlow+'Y').hide();
		$("#"+recordFlow+'N').show();
	}
	if($("#"+recordFlow+'N').is(":visible")){
		$("#"+recordFlow+'pacificGrade').show();
		$("."+recordFlow+'pacificGrade').hide();
		$("#"+recordFlow+'teamEndGrade').show();
		$("."+recordFlow+'teamEndGrade').hide();
	}else{
		$("#"+recordFlow+'pacificGrade').hide();
		$("."+recordFlow+'pacificGrade').show();
		$("#"+recordFlow+'teamEndGrade').hide();
		$("."+recordFlow+'teamEndGrade').show();
	}
}
function switchStautus(obj){
	var pacificGrade=$(obj).parent().prev().prev().children(".pacificGrade");
	var pacificGradeLabel=$(obj).parent().prev().prev().children(".pacificGradeLabel");
	var teamEndGrade=$(obj).parent().prev().children(".teamEndGrade");
	var teamEndGradeLabel=$(obj).parent().prev().children(".teamEndGradeLabel");
	var courseGrade=$(obj).parent().children(".courseGrade");
	var courseGradeSelect=$(obj).parent().children(".courseGradeSelect");
	if(pacificGrade.is(":hidden")){
		pacificGrade.show();
		teamEndGrade.show();
		courseGrade.show();
		pacificGradeLabel.hide();
		teamEndGradeLabel.hide();
		courseGradeSelect.hide();
	}else{
		pacificGrade.hide();
		teamEndGrade.hide();
		courseGrade.hide();
		pacificGradeLabel.show();
		teamEndGradeLabel.show();
		courseGradeSelect.show();
	}
}
function delBatchGrade(){
	var checkLen = $(":checkbox[class='check']:checked").length;
	if(checkLen == 0){
		jboxTip("请勾选成绩记录！");
		return;
	}
	var recordLst = [];
	$(":checkbox[class='check']:checked").each(function(){
		recordLst.push(this.value);
	})
	jboxConfirm("确认批量删除勾选成绩记录？", function(){
		var url = "<s:url value='/gyxjgl/user/delBatchGrade?recordLst='/>"+recordLst;
		jboxPost(url, null, function(resp){
			if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
				location.reload();
			}
		});
	});
}
function allCheck(){
	if($("#checkAll").attr("checked")){
		$(".check").attr("checked",true);
	}else{
		$(".check").attr("checked",false);
	}
}
function checkSingel(obj){
	if(!$(obj).attr("checked")){
		$("#checkAll").attr("checked",false);
	}else{
		var checkAllLen = $("input[type='checkbox'][class='check']").length;
		var checkLen = $("input[type='checkbox'][class='check']:checked").length;
		if(checkAllLen == checkLen){
			$("#checkAll").attr("checked",true);
		}
	}
}
</script>
<style>
.xllist_1 { border:1px solid #D0E3F2; width:100%;}
.xllist_1 tr:nth-child(2n) {background-color: #fffdf5;transition: all .125s ease-in-out;}
.xllist_1 tr:hover { background:#fbf8e9;}
.xllist_1 th,.xllist_1 td { border-right:1px solid #D0E3F2; border-bottom:1px solid #D0E3F2;text-align:center;}
.xllist_1 th { background:#ECF2FA; color:#333; height:30px;}
.xllist_1 td { text-align:center; line-height:25px; word-break:break-all;}
</style>
</head>
<body>
<div class="mainright">
	<div class="content">
	<table class="basic" style="width: 100%;margin-bottom: 5px;margin-top: 0px;">
			<tr>
				<td>
					&#12288;
					姓名：<label>${sysuser.userName}</label>
					&#12288;
					学号：<label>${eduuser.sid}</label>
					&#12288;
					总学时：<label id="s">${zongShi}</label>
					&#12288;
					总学分：<label id="f">${zongFen}</label>
					<span style="float: right;">
						<%--<c:if test="${GlobalConstant.FLAG_Y != param.openType}">--%>
						<%--<input class="search"  type="button" value="下&#12288;载" onclick="down();"/>--%>
						<%--</c:if>--%>
						<input class="search"  type="button" value="打&#12288;印" onclick="printFlag();"/>
						<c:if test="${GlobalConstant.FLAG_Y != param.openType}">
							<input class="search"  type="button" value="批量删除" onclick="delBatchGrade();"/>
						</c:if>
					</span>
				</td>
			</tr>
		</table>
		<form id="form">
			<table  class="xllist" id="tempTable" style="width: 100%;">
			<tr hidden>
				<td></td>
			</tr>
				<tr>
					<c:if test="${GlobalConstant.FLAG_Y != param.openType}">
						<th style="width:45px;"><input type="checkbox" id="checkAll" onclick="allCheck()"/></th>
						<th style="width:120px;">课程名称</th>
						<th style="width:80px;">学位课程</th>
						<th style="width:80px;">课程类型</th>
						<th style="width:60px;">学时</th>
						<th style="width:60px;">学分</th>
						<th style="width:80px;">修读方式</th>
						<th style="width:80px;">考核方式</th>
						<th style="width:80px;">平时成绩</th>
						<th style="width:80px;">期末成绩</th>
						<th style="width:80px;">成绩</th>
						<th style="width:80px;">获得学期</th>
						<th style="width:60px;">操作</th>
					</c:if>
					<c:if test="${GlobalConstant.FLAG_Y eq param.openType}">
						<th style="width:150px;">课程名称</th>
						<th style="width:80px;">学位课程</th>
						<th style="width:80px;">课程类型</th>
						<th style="width:60px;">学时</th>
						<th style="width:60px;">学分</th>
						<th style="width:80px;">修读方式</th>
						<th style="width:80px;">考核方式</th>
						<th style="width:80px;">平时成绩</th>
						<th style="width:80px;">期末成绩</th>
						<th style="width:80px;">成绩</th>
						<th style="width:80px;">获得学期</th>
					</c:if>
				</tr>
			<tbody id="appendTbody">
				<c:forEach items="${eduUserExt.courseExtsList}" var="studentCourseExt">
				<c:set var="recordIdClassVar" value="${fn:replace(studentCourseExt.recordFlow, '.', '')}"/>
				<c:set var="isHaveScore" value="1"></c:set>
					<tr class="each">
							<input  type="hidden" name="currFlowSun" value="">
							<c:if test="${GlobalConstant.FLAG_Y != param.openType}">
								<td><input type="checkbox" class="check" value="${studentCourseExt.recordFlow}" onclick="checkSingel(this)"/></td>
							</c:if>
							<td>
							<c:if test="${GlobalConstant.FLAG_Y != param.openType}">
								<label>
									${studentCourseExt.courseName}
								</label>
							</c:if>
							<c:if test="${GlobalConstant.FLAG_Y eq param.openType}">
								<label>
									${studentCourseExt.courseName}
								</label>
							</c:if>
							</td>

						<td>
							<c:if test="${GlobalConstant.FLAG_Y != param.openType}">
								<select style="width:  80%;text-align: center;" name="degreeCourseFlag">
									<option value="Y" ${studentCourseExt.degreeCourseFlag eq 'Y'?'selected':''}>是</option>
									<option value="N" ${studentCourseExt.degreeCourseFlag ne 'Y'?'selected':''}>否</option>
								</select>
							</c:if>
							<c:if test="${GlobalConstant.FLAG_Y eq param.openType}">
								<label>${studentCourseExt.degreeCourseFlag eq 'Y'?'是':'否'}</label>
							</c:if>
						</td>
						<c:if test="${GlobalConstant.FLAG_Y ne param.openType}">
							<td>
								<select style="width:  80%;text-align: center;" name="courseTypeId">
									<option value="">请选择</option>
									<c:forEach items="${xjglCourseTypeEnumList}" var="xcte">
										<c:if test="${xcte.id == 'OptionalNeed'||xcte.id == 'Public'}">
											<option value="${xcte.id}" ${studentCourseExt.courseTypeId eq xcte.id?'selected':''}>${xcte.name=='公共选修课'?'选修课':'必修课'}</option>
										</c:if>
									</c:forEach>
								</select>
							</td>
							<td><input name="coursePeriod" type="text" style="width: 70%;text-align: center;" value="${studentCourseExt.coursePeriod}"/></td>
							<td><input name="courseCredit" type="text" style="width: 70%;text-align: center;" value="${studentCourseExt.courseCredit}"/></td>
						</c:if>
						<c:if test="${GlobalConstant.FLAG_Y eq param.openType}">
							<td><label class="courseTypeName">${studentCourseExt.courseTypeName}</label></td>
							<td><label class="coursePeriod">${studentCourseExt.coursePeriod}</label></td>
							<td><label class="courseCredit">${studentCourseExt.courseCredit}</label></td>
						</c:if>
							<td>
								<c:if test="${GlobalConstant.FLAG_Y != param.openType}">
									<select style="width:  80%;text-align: center;" class="studyWayId" name="studyWayId">
										<option value="">请选择</option>
										<c:forEach items="${dictTypeEnumGyXjStudyWayList}" var="dict">
											<option value="${dict.dictId}"<c:if test="${dict.dictId eq studentCourseExt.studyWayId}">selected="selected"</c:if>>${dict.dictName }</option>
										</c:forEach>
									</select>
								</c:if>
								<c:if test="${GlobalConstant.FLAG_Y eq param.openType}">
									<label>
										${studentCourseExt.studyWayName}
									</label>
								</c:if>
								<c:if test="${ not empty studentCourseExt.studyWayId }">
									<c:set var="isHaveScore" value="0"></c:set>
								</c:if>
							</td>
							<td>
								<c:if test="${GlobalConstant.FLAG_Y != param.openType}">
									<select style="width:  80%;text-align: center;" class="assessTypeId" name="assessTypeId">
										<option value="">请选择</option>
										<c:forEach items="${dictTypeEnumGyXjEvaluationModeList}" var="dict">
											<option value="${dict.dictId}"<c:if test="${dict.dictId eq studentCourseExt.assessTypeId}">selected="selected"</c:if>>${dict.dictName }</option>
										</c:forEach>
									</select>
								</c:if>
								<c:if test="${GlobalConstant.FLAG_Y eq param.openType}">
									<label>
										${studentCourseExt.assessTypeName}
									</label>
								</c:if>
								<c:if test="${ not empty studentCourseExt.assessTypeId }">
									<c:set var="isHaveScore" value="0"></c:set>
								</c:if>
							</td>
							<td>
								<c:set value="${recordIdClassVar}pacificGrade" var="pac"/>
								<c:if test="${GlobalConstant.FLAG_Y != param.openType}">
									<input name="pacificGrade" class="pacificGrade validate[custom[number]] ${pac}" type="text" style="width: 70%;text-align: center;" value="${studentCourseExt.pacificGrade}"/>
								</c:if>
								<c:if test="${GlobalConstant.FLAG_Y eq param.openType}">
									<label class="pacificGrade ${pac}">${studentCourseExt.pacificGrade}</label>
								</c:if>
								<label id="${pac}" style="display: none;">--</label>
								<c:if test="${ not empty studentCourseExt.pacificGrade }">
									<c:set var="isHaveScore" value="0"></c:set>
								</c:if>
							</td>
							<td>
								<c:set value="${recordIdClassVar}teamEndGrade" var="team"/>
								<c:if test="${GlobalConstant.FLAG_Y != param.openType}">
									<input name="teamEndGrade" class="teamEndGrade validate[custom[number]] ${team }" type="text" style="width: 70%;text-align: center;" value="${studentCourseExt.teamEndGrade}"/>
								</c:if>
								<c:if test="${GlobalConstant.FLAG_Y eq param.openType}">
									<label class="teamEndGrade ${team }">${studentCourseExt.teamEndGrade}</label>
								</c:if>
								<label id="${team }" style="display: none;">--</label>
								<c:if test="${ not empty studentCourseExt.teamEndGrade }">
									<c:set var="isHaveScore" value="0"></c:set>
								</c:if>
							</td>
							<td class="courseGrade${studentCourseExt.courseCode}">
								<c:if test="${GlobalConstant.FLAG_Y != param.openType}">
									<input id="${recordIdClassVar}Y" name="courseGrade" class="courseGrade validate[custom[number]]" type="text" style="width: 70%;text-align: center;" value="${studentCourseExt.courseGrade}"/>
									<select id="${recordIdClassVar}N" style="width:  73%;text-align: center;display: none;" class="courseGrade validate[required]" name="courseGradeSelect" >
										<option value="">请选择</option>
										<c:forEach items="${dictTypeEnumGyXjIsPassedList }" var="dict">
											<option value="${dict.dictId}"<c:if test="${dict.dictId eq studentCourseExt.courseGrade}">selected="selected"</c:if>>${dict.dictName}</option>
										</c:forEach>
									</select>
									<a onclick="changeGrade('${recordIdClassVar}');" style="cursor: pointer;" title="切换">></a>
									<script>
										var val0 = "${studentCourseExt.courseGrade}"==""?"":Number('${studentCourseExt.courseGrade}').toFixed(1);
										var len = $(".courseGrade${studentCourseExt.courseCode}").length;
										if(len > 1){
											$($(".courseGrade${studentCourseExt.courseCode}").eq(len-1).find("input")).val(val0);
										}else{
											$(".courseGrade${studentCourseExt.courseCode} input").val(val0);
										}
									</script>
								</c:if>
								<c:if test="${GlobalConstant.FLAG_Y eq param.openType}">
									<label id="${studentCourseExt.recordFlow}label">${studentCourseExt.courseGrade}</label>
									<script>
										var val1 = "${studentCourseExt.courseGrade}";
										<c:choose>
											<c:when test="${record.courseGrade eq 'Y' || record.courseGrade eq 'N' || record.courseGrade eq 'Excellent' || record.courseGrade eq 'Good'
													|| record.courseGrade eq 'Secondary' || record.courseGrade eq 'Pass' || record.courseGrade eq 'UnPass'}">
												<c:set var="gradeId" value="GyXjIsPassed.${record.courseGrade}" />
												val1 = '${applicationScope.sysDictIdMap[gradeId]}';
											</c:when>
											<c:otherwise>
												val1 = ""!=val1 && !isNaN(val1)?Number('${studentCourseExt.courseGrade}').toFixed(1):"";
											</c:otherwise>
										</c:choose>
										var len = $(".courseGrade${studentCourseExt.courseCode}").length;
										if(len > 1){
											$($(".courseGrade${studentCourseExt.courseCode}").eq(len-1).find("label")).text(val1);
										}else{
											$(".courseGrade${studentCourseExt.courseCode} label").text(val1);
										}
									</script>
								</c:if>
								<c:if test="${ not empty studentCourseExt.courseGrade }">
									<c:set var="isHaveScore" value="0"></c:set>
								</c:if>
							</td>
							<td>
								<c:if test="${GlobalConstant.FLAG_Y != param.openType}">
									<select style="width:  80%;text-align: center;" class="gradeTermId" name="gradeTermId"  onchange="changeAll(this);">
										<option value="">请选择</option>
										<c:forEach items="${dictTypeEnumGyRecruitSeasonList}" var="recruitSeason">
										  <option value="${recruitSeason.dictId}" <c:if test="${studentCourseExt.gradeTermId==recruitSeason.dictId}">selected="selected"</c:if>>${recruitSeason.dictName}</option>
										</c:forEach>
									</select>
								</c:if>
								<c:if test="${GlobalConstant.FLAG_Y eq param.openType}">
									<label>${studentCourseExt.gradeTermName }</label>
								</c:if>
								<c:if test="${ not empty studentCourseExt.gradeTermName }">
									<c:set var="isHaveScore" value="0"></c:set>
								</c:if>
							</td>
							<c:if test="${GlobalConstant.FLAG_Y != param.openType}">
								<td><a onclick="delGrade('${isHaveScore}','${studentCourseExt.recordFlow}');" style="cursor: pointer;" title="删除课程">删除课程</a></td>
							</c:if>
							<input type="hidden" name="recordFlow" value="${studentCourseExt.recordFlow}"/>
							<input type="hidden" name="courseFlow" class="courseFlow" value="${studentCourseExt.courseFlow}"/>
							<input type="hidden" name="userFlow" value="${param.userFlow}"/>
					</tr>
					<c:set var="zongShi" value="${zongShi+studentCourseExt.coursePeriod}"/>
					<c:set var="zongFenTemp" value="${(studentCourseExt.courseGrade eq GlobalConstant.FLAG_Y || (studentCourseExt.courseGrade !='N' and studentCourseExt.courseGrade !='T' and studentCourseExt.courseGrade>'0'))?studentCourseExt.courseCredit:0}"/>
					<c:set var="zongFen" value="${zongFen+zongFenTemp}"/>
				</c:forEach>
				<c:if test="${empty eduUserExt.courseExtsList}">
					<tr>
						<td colspan="99">无记录</td>
					</tr>
				</c:if>
				</tbody>
				<input type="hidden" id="zongShi" value="<fmt:formatNumber type="number" value="${zongShi}" maxFractionDigits="2"/>"/>
				<input type="hidden" id="zongFen" value="<fmt:formatNumber type="number" value="${zongFen}" maxFractionDigits="2"/>"/>
				<tr class="each" style="display: none;" id="temp">
							<td><input type="checkbox"/></td>
							<td>
								<select style="width: 80%;" class="courseName" name="courseName" onchange="xiaLa(this);" onclick="currFlow(this)" class="currSelect xlselect">
									<option value="">请选择</option>
									<c:forEach items="${currCourseMajorlist}" var="currCourseMajor">
										<option value="${currCourseMajor.courseFlow}"   <c:if test="${studentCourseExt.courseFlow==currCourseMajor.courseFlow}">selected="selected"</c:if>>${currCourseMajor.courseName}</option>
									</c:forEach>
								</select>
							</td>
							<td><label class="courseTypeName"></label></td>
							<td><label class="coursePeriod"></label></td>
							<td><label class="courseCredit"></label></td>
							<td>
								<select style="width:  80%;text-align: center;" class="studyWayId" name="studyWayId">
									<option value="">请选择</option>
									<c:forEach items="${dictTypeEnumGyXjStudyWayList}" var="dict">
										<option value="${dict.dictId}">${dict.dictName }</option>
									</c:forEach>
								</select>
							</td>
							<td>
								<select style="width:  80%;text-align: center;" class="assessTypeId" name="assessTypeId">
									<option value="">请选择</option>
									<c:forEach items="${dictTypeEnumGyXjEvaluationModeList}" var="dict">
										<option value="${dict.dictId}">${dict.dictName }</option>
									</c:forEach>
								</select>
							</td>
							<td>
								<input name="pacificGrade" class="pacificGrade" type="text" style="width: 70%;text-align: center;" value=""/>
								<label class="pacificGradeLabel" style="display: none;">--</label>
							</td>
							<td>
								<input name="teamEndGrade" class="teamEndGrade" type="text" style="width: 70%;text-align: center;" value=""/>
								<label class="teamEndGradeLabel" style="display: none;">--</label>
							</td>
							<td>
								<input name="courseGrade" class="courseGrade" type="text" style="width: 70%;text-align: center;" value=""/>
								<select  style="width:  73%;text-align: center;display: none;" class="courseGradeSelect validate[required]" name="courseGradeSelect" >
									<option value="">请选择</option>
									<c:forEach items="${dictTypeEnumGyXjIsPassedList }" var="dict">
										<option value="${dict.dictId}"<c:if test="${dict.dictId eq studentCourseExt.courseGrade}">selected="selected"</c:if>>${dict.dictName}</option>
									</c:forEach>
								</select>
								<a onclick="switchStautus(this);" style="cursor: pointer;" title="切换">></a>
							</td>
							<td>
								<select style="width: 80%;text-align: center;" class="gradeTermId" name="gradeTermId" onchange="changeAll(this);">
									<option value="">请选择</option>
									<c:forEach items="${dictTypeEnumGyRecruitSeasonList}" var="recruitSeason">
									  <option value="${recruitSeason.dictId}" <c:if test="${studentCourseExt.gradeTermId==recruitSeason.dictId}">selected="selected"</c:if>>${recruitSeason.dictName}</option>
									</c:forEach>
								</select>
							</td>
							<input type="hidden" name="courseFlow" class="courseFlow" value=""/>
							<input type="hidden" name="userFlow" value="${param.userFlow}"/>
				</tr>
		</table>
		</form>
		<div class="button">
			<c:if test="${GlobalConstant.FLAG_Y != param.openType}">
				<input class="search" id="save" type="button" value="保&#12288;存" onclick="check();"/>
			</c:if>
			<input class="search" type="button" value="关&#12288;闭" onclick="jboxClose();"/>
		</div>
	</div>
	<div id="printDiv2" hidden="hidden">
		<%@include file="/jsp/common/doctype.jsp" %>
		<head>
			<style>
				.cjTab{width:100%;margin-top:10px;border-collapse:collapse;}
				.cjTab td{border:1px solid grey;}
			</style>
		</head>
		<div class="printDiv">
			<div style="text-align: center;">
				<%--studyFormId=5 同等学力形式--%>
				<img src="<s:url value='/jsp/gyxjgl/images/xxlogo${baseInfo.trainTypeId eq "5"?"_same":""}.png'/>"/>
			</div>
			<table style="margin-top:10px;">
				<tr>
					<td style="min-width:110px;width:1%;border-left:2px solid grey;">&nbsp;学位类型：<br/>&nbsp;Degree Type:</td>
					<td style="min-width:${baseInfo.trainCategoryId eq '5'?'127px':'113px'};width:1%;">${baseInfo.trainCategoryId eq '4'?'学术学位':''}${baseInfo.trainCategoryId eq '5'?'专业学位':''}<br/>
						${baseInfo.trainCategoryId eq '4'?'Academic Degree':''}${baseInfo.trainCategoryId eq '5'?'Professional Degree':''}
					</td>
					<td style="min-width:75px;width:1%;border-left:2px solid grey;">&nbsp;学号：<br/>&nbsp;Student ID:</td>
					<td style="min-width:80px;width:1%;">${baseInfo.sid}<br/>${baseInfo.sid}</td>
					<td style="min-width:50px;width:1%;border-left:2px solid grey;">&nbsp;姓名：<br/>&nbsp;Name:</td>
					<td style="width:95%;">${baseInfo.userName}<br/>${baseInfo.userEnName}</td>
				</tr>
				<tr>
					<td style="border-left:2px solid grey;">&nbsp;学位级别：<br/>&nbsp;Degree Level:</td>
					<td>${baseInfo.degreeType eq 'Master'?'硕士学位':''}${baseInfo.degreeType eq 'Doctor'?'博士学位':''}<br/>
						${baseInfo.degreeType}
					</td>
					<td style="border-left:2px solid grey;">&nbsp;性别：<br/>&nbsp;Gender:</td>
					<td>${baseInfo.sexName}<br/>${baseInfo.sexId eq 'Woman'?'Female':''}${baseInfo.sexId eq 'Man'?'Male':''}</td>
					<td style="border-left:2px solid grey;">&nbsp;专业：<br/>&nbsp;Major:</td>
					<td>${baseInfo.majorName}<br/>${baseInfo.majorEnName}</td>
				</tr>
			</table>
			<table class="cjTab">
				<tr>
					<td style="min-width:200px;width:96%;">&ensp;课程名称<br/>&ensp;Course Name</td>
					<td style="text-align:center;min-width:74px;width:1%;">学时<br/>Hours</td>
					<td style="text-align:center;min-width:74px;width:1%;">学分<br/>Credits</td>
					<td style="text-align:center;min-width:74px;width:1%;">成绩<br/>Scores</td>
					<td style="text-align:center;min-width:84px;width:1%;">绩点<br/>Grade Points</td>
				</tr>
				<c:set var="creditCount" value="0" />
				<c:set var="pointCount" value="0" />
				<c:forEach items="${gradeList}" var="gra">
					<tr>
						<c:set var="creditCount" value="${creditCount+gra.courseCredit}"/>
						<td ><div style="border-bottom:1px dashed grey;padding-left:7px;">${gra.courseName}</div><div style="padding-left:7px;">${gra.courseNameEn}</div></td>
						<td style="text-align:center;">${gra.coursePeriod}</td>
						<td style="text-align:center;">${gra.courseCredit}</td>
						<c:choose>
							<c:when test="${gra.courseGrade eq 'Y'}">
								<td style="text-align:center;"><div style="border-bottom:1px dashed grey;">合格</div>Pass</td><td style="text-align:center;">3.0</td>
								<c:set var="pointCount" value="${pointCount+3}"/>
							</c:when>
							<c:when test="${gra.courseGrade eq 'N'}">
								<td style="text-align:center;"><div style="border-bottom:1px dashed grey;">不合格</div>UnPass</td><td style="text-align:center;">0.0</td>
							</c:when>
							<c:when test="${gra.courseGrade eq 'Excellent'}">
								<td style="text-align:center;"><div style="border-bottom:1px dashed grey;">优秀</div>Excellent</td><td style="text-align:center;">4.0</td>
								<c:set var="pointCount" value="${pointCount+4}"/>
							</c:when>
							<c:when test="${gra.courseGrade eq 'Good'}">
								<td style="text-align:center;"><div style="border-bottom:1px dashed grey;">良好</div>Good</td><td style="text-align:center;">3.0</td>
								<c:set var="pointCount" value="${pointCount+3}"/>
							</c:when>
							<c:when test="${gra.courseGrade eq 'Secondary'}">
								<td style="text-align:center;"><div style="border-bottom:1px dashed grey;">中等</div>Secondary</td><td style="text-align:center;">2.0</td>
								<c:set var="pointCount" value="${pointCount+2}"/>
							</c:when>
							<c:when test="${gra.courseGrade eq 'Pass'}">
								<td style="text-align:center;"><div style="border-bottom:1px dashed grey;">及格</div>Pass</td><td style="text-align:center;">1.0</td>
								<c:set var="pointCount" value="${pointCount+1}"/>
							</c:when>
							<c:when test="${gra.courseGrade eq 'UnPass'}">
								<td style="text-align:center;"><div style="border-bottom:1px dashed grey;">不及格</div>UnPass</td><td style="text-align:center;">0.0</td>
							</c:when>
							<c:otherwise>
								<fmt:parseNumber var="courseGrade" type='number' value='${gra.courseGrade}'/>
								<td style="text-align:center;">${gra.courseGrade}</td><td style="text-align:center;">
								<fmt:formatNumber type="number" value="${courseGrade lt 60?0.0:((courseGrade-50)/10 gt 5?5:(courseGrade-50)/10)}" pattern="0.0" maxFractionDigits="1"/></td>
								<c:set var="pointCount" value="${pointCount+(courseGrade lt 60?0:((courseGrade-50)/10 gt 5?5:(courseGrade-50)/10))}"/>
							</c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>
			</table>
			<table class="cjTab">
				<tr>
					<td style="text-align:center;width:49%;"><div style="border-bottom:1px dashed grey;">总学分</div>Total Credits</td>
					<td style="text-align:center;min-width:110px;width:1%;">${creditCount}</td>
					<td style="text-align:center;width:49%;"><div style="border-bottom:1px dashed grey;">平均绩点</div>Grade Points Average</td>
					<td style="text-align:center;min-width:110px;width:1%;"><fmt:formatNumber type="number" value="${fn:length(gradeList) eq 0?0:pointCount/fn:length(gradeList)+0.0001}" pattern="0.0" maxFractionDigits="1"/></td>
				</tr>
			</table>
			<div style="text-align: right;margin-top: 15px;">
				<div style="height:150px;float:right; position:relative;">
					<font style="position:absolute;right:18px;top:25px;width:200px;">广州医科大学研究生院</font>
					<c:set var="dateStr" value="${fn:split(pdfn:getCurrDate(),'-')}"/>
					<font style="position:absolute;right:18px;top:45px;width:400px;">Graduate School of Guangzhou Medical University<br/>${dateStr[0]}年 ${dateStr[1]}月 ${dateStr[2]}日<br/>
						${dateStr[1] eq '01'?'Jan':''}${dateStr[1] eq '02'?'Feb':''}${dateStr[1] eq '03'?'Mar':''}${dateStr[1] eq '04'?'Apr':''}
						${dateStr[1] eq '05'?'May':''}${dateStr[1] eq '06'?'Jun':''}${dateStr[1] eq '07'?'Jul':''}${dateStr[1] eq '08'?'Aug':''}
						${dateStr[1] eq '09'?'Sep':''}${dateStr[1] eq '10'?'Oct':''}${dateStr[1] eq '11'?'Nov':''}${dateStr[1] eq '12'?'Dec':''}.<c:choose><c:when test="${dateStr[2] eq '01' || dateStr[2] eq '21' || dateStr[2] eq '31'}">${dateStr[2]}st</c:when>
							<c:when test="${dateStr[2] eq '02' || dateStr[2] eq '22'}">${dateStr[2]}nd</c:when>
							<c:when test="${dateStr[2] eq '03' || dateStr[2] eq '23'}">${dateStr[2]}rd</c:when><c:otherwise>${dateStr[2]}th</c:otherwise>
						</c:choose>, ${dateStr[0]}</font>
				</div>
			</div>
		</div>
	</div>
</div>
</div>
</body>
</html>