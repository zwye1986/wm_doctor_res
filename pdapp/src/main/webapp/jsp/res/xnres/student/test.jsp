<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>西南住院医师APP学生端测试程序<%-- --http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath} --%></title>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true" />
	<jsp:param name="jbox" value="true" />
</jsp:include>
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/highlight.js/8.4/styles/default.min.css">
<style type="text/css">
body{ 
	height:100%;
	overflow:scroll;
}
</style>
<script type="text/javascript">
	function test() {
		var action = $("#reqCode option:selected").val();
		if(action==""){
			jboxTip("请选择测试的交易");
			return;
		}
		var method = $("#reqCode option:selected").attr("method");
		if(method=="put"){
			method = "post"
		}
		if(method=="delete"){
			method = "post"
		}
		var isForm = $("#reqCode option:selected").attr("isForm");
		if(isForm=="Y")
		{
			var url = "<s:url value='/res/xnres/student'/>/" + action ;
			var form=$('<form action="'+url+'" method="'+(method||'post')+'"></form>');
			var formData = $("#reqParam").val();
			var arr=formData.split("&"); //各个参数放到数组里
			for(var i=0;i < arr.length;i++){
				var num=arr[i].indexOf("=");
				if(num>0){
					$(form).append($('<input name="'+arr[i].substring(0,num)+'" value="'+arr[i].substr(num+1)+'" type="hidden"/>'));
				}
			}
			$(form).appendTo('body').submit().remove();
		}else {
			var url = "<s:url value='/res/xnres/student'/>/" + action + "?_method=" + method;
			var formData = $("#reqParam").val();
			var needDataType = $("#reqCode option:selected").attr("needDataType");
			var dataType = $("#dataType option:selected").val();
			if (needDataType == 'Y') {
				formData = formData + "&dataType=" + dataType;
				formData = formData + "&" + $("#reqParamDataType").val();
			} else {

			}
			$.ajax({
				type: method,
				url: url,
				data: formData,
				cache: false,
				beforeSend: function () {
					jboxStartLoading();
				},
				success: function (resp) {
					jboxEndLoading();
					jboxTip("测试成功");
					$("#rsp").text(JSON.stringify(resp, null, 4));
				},
				error: function () {
					jboxEndLoading();
					jboxTip("操作失败,请刷新页面后重试");
				},
				complete: function () {
					jboxEndLoading();
				},
				statusCode: {
					405: function () {
						jboxTip("交易方法不正确");
					}
				}
			});
		}
	}
	function change(divid) {
		$("#reqParam").val($("#" + divid + "  textarea").first().val());
	}
	function change2(dataType) {
		var divid = $("#reqCode option:selected").val();
		$("#reqParamDataType").text($("#" + divid + " textarea[dataType='"+dataType+"']").first().val());
	}
	$(document).ready(function(){
		<c:if test="${not empty param.reqCode}">
		change('${param.reqCode}');
		</c:if>
	});
</script>
</head>
<body>
	<form name="appForm" action="${ctxPath}/res/xnres/student/remember" method="get">
	<div style="width: 60%; float: left">
		<div style="margin-top: 20px; margin-bottom: 20px; margin-left: 100px">
			学生端动作：	<select id="reqCode" name="reqCode" onchange="change(this.value);">
						<option value="" method="">请选择</option>
						<option value="deptList" method="get" needDataType="N" <c:if test="${param.reqCode=='deptList'}">selected</c:if> >3.1.1获取轮转计划列表(deptList)get</option>
						<option value="funcList" method="get" needDataType="N" <c:if test="${param.reqCode=='funcList'}">selected</c:if> >3.1.2获取功能列表(funcList)get</option>
						<option value="standardDeptList" method="get" needDataType="N" <c:if test="${param.reqCode=='standardDeptList'}">selected</c:if> >3.1.3获取医师的标准科室列表(standardDeptList)get</option>
						<option value="addRotationDept" method="post" needDataType="N" <c:if test="${param.reqCode=='addRotationDept'}">selected</c:if> >3.1.4新增轮转计划(addRotationDept)post</option>
						<option value="modRotationDept" method="post" needDataType="N" <c:if test="${param.reqCode=='modRotationDept'}">selected</c:if> >3.1.5修改轮转计划(modRotationDept)post</option>
						<option value="deleteRotationDept" method="post" needDataType="N" <c:if test="${param.reqCode=='deleteRotationDept'}">selected</c:if> >3.1.6删除轮转计划(deleteRotationDept)post</option>

						<option value="getNewLectures" method="post" needDataType="N" <c:if test="${param.reqCode=='getNewLectures'}">selected</c:if> >3.2.1最新讲座(getNewLectures)post</option>
						<option value="getHistoryLectures" method="post" needDataType="N" <c:if test="${param.reqCode=='getHistoryLectures'}">selected</c:if> >3.2.2历史讲座(getHistoryLectures)post</option>
						<option value="lectureRegist" method="post" needDataType="N" <c:if test="${param.reqCode=='lectureRegist'}">selected</c:if> >3.2.3报名(lectureRegist)post</option>
						<option value="evaluate" method="post" needDataType="N" <c:if test="${param.reqCode=='evaluate'}">selected</c:if> >3.2.4查看评价(evaluate)post</option>
						<option value="saveEvaluate" method="post" needDataType="N" <c:if test="${param.reqCode=='saveEvaluate'}">selected</c:if> >3.2.5保存评价(saveEvaluate)post</option>
						<option value="suggestions" method="post" needDataType="N" <c:if test="${param.reqCode=='suggestions'}">selected</c:if> >3.2.6查询意见反馈(suggestions)post</option>
						<option value="delOpinions" method="post" needDataType="N" <c:if test="${param.reqCode=='delOpinions'}">selected</c:if> >3.2.7删除意见反馈(delOpinions)post</option>
						<option value="opinionsDetail" method="post" needDataType="N" <c:if test="${param.reqCode=='opinionsDetail'}">selected</c:if> >3.2.8意见反馈详情(opinionsDetail)post</option>
						<option value="saveOpinions" method="post" needDataType="N" <c:if test="${param.reqCode=='saveOpinions'}">selected</c:if> >3.2.9保存意见反馈(saveOpinions)post</option>
						<option value="getZhupeiNotices" method="post" needDataType="N" <c:if test="${param.reqCode=='getZhupeiNotices'}">selected</c:if> >3.3.0住培指南(getZhupeiNotices)post</option>
						<option value="zpNoticeDetail" method="post" needDataType="N" <c:if test="${param.reqCode=='zpNoticeDetail'}">selected</c:if> >3.3.1住培指南详情(zpNoticeDetail)post</option>
						<option value="studentSignIn" method="post" needDataType="N" <c:if test="${param.reqCode=='studentSignIn'}">selected</c:if> >3.3.2学员签到查询次数(studentSignIn)post</option>
						<option value="saveSignIn" method="post" needDataType="N" <c:if test="${param.reqCode=='saveSignIn'}">selected</c:if> >3.3.3保存签到信息(saveSignIn)post</option>
						<option value="joinExam" method="post" needDataType="N" <c:if test="${param.reqCode=='joinExam'}">selected</c:if> >3.3.4 参加出科考试(joinExam)post</option>
						<option value="modfiyPass" method="post" needDataType="N" <c:if test="${param.reqCode=='modfiyPass'}">selected</c:if> >3.3.4 修改密码(modfiyPass)post</option>
			<option value="allAfterDept" method="post" needDataType="N" <c:if test="${param.reqCode=='allAfterDept'}">selected</c:if> >4.6出科考试科室列表(allAfterDept)post</option>
			<option value="gradeDeptList" method="post" needDataType="N" <c:if test="${param.reqCode=='gradeDeptList'}">selected</c:if> >4.7 双向评价(gradeDeptList)post</option>
			<option value="userCenter" method="post" needDataType="N" <c:if test="${param.reqCode=='userCenter'}">selected</c:if> >4.7 学员首页(userCenter)post</option>
			<option value="discipleIndex" method="post" needDataType="N" <c:if test="${param.reqCode=='discipleIndex'}">selected</c:if> >4.7 跟师学习管理学习手册封面(discipleIndex)post</option>
			<option value="saveDiscipleInfo" method="post" needDataType="N" <c:if test="${param.reqCode=='saveDiscipleInfo'}">selected</c:if> >4.7 保存跟师学习管理学习手册封面(saveDiscipleInfo)post</option>
			<option value="discipleTeacherIndex" method="post" needDataType="N" <c:if test="${param.reqCode=='discipleTeacherIndex'}">selected</c:if> >4.7 师承指导老师简况表(discipleTeacherIndex)post</option>
			<option value="saveDiscipleTeacherInfo" method="post" needDataType="N" <c:if test="${param.reqCode=='saveDiscipleTeacherInfo'}">selected</c:if> >4.7 保存师承指导老师简况表(saveDiscipleTeacherInfo)post</option>
			<option value="showFollowTeacherRecord" method="post" needDataType="N" <c:if test="${param.reqCode=='showFollowTeacherRecord'}">selected</c:if> >4.7 跟师记录列表(showFollowTeacherRecord)post</option>
			<option value="addFollowTeacherRecord" method="post" needDataType="N" <c:if test="${param.reqCode=='addFollowTeacherRecord'}">selected</c:if> >4.7 新增或编辑跟师记录(addFollowTeacherRecord)post</option>
			<option value="delFollowTeacherRecord" method="post" needDataType="N" <c:if test="${param.reqCode=='delFollowTeacherRecord'}">selected</c:if> >4.7 删除跟师记录(delFollowTeacherRecord)post</option>
			<option value="saveFollowTeacherRecord" method="post" needDataType="N" <c:if test="${param.reqCode=='saveFollowTeacherRecord'}">selected</c:if> >4.7 保存跟师记录(saveFollowTeacherRecord)post</option>
			<option value="showDiscipleNoteInfo" method="post" needDataType="N" <c:if test="${param.reqCode=='showDiscipleNoteInfo'}">selected</c:if> >4.7 学习笔记或跟师心得或医籍学习体会列表(showDiscipleNoteInfo)post</option>
			<option value="addDiscipleNoteInfo" method="post" needDataType="N" <c:if test="${param.reqCode=='addDiscipleNoteInfo'}">selected</c:if> >4.7 新增或编辑【学习笔记或跟师心得或医籍学习体会】(addDiscipleNoteInfo)post</option>
			<option value="saveDiscipleNoteInfo" method="post" needDataType="N" <c:if test="${param.reqCode=='saveDiscipleNoteInfo'}">selected</c:if> >4.7 保存【学习笔记或跟师心得或医籍学习体会】(saveDiscipleNoteInfo)post</option>
			<option value="delDiscipleNoteInfo" method="post" needDataType="N" <c:if test="${param.reqCode=='delDiscipleNoteInfo'}">selected</c:if> >4.7 删除【学习笔记或跟师心得或医籍学习体会】(delDiscipleNoteInfo)post</option>
			<option value="imageList" method="post" needDataType="N" <c:if test="${param.reqCode=='imageList'}">selected</c:if> >4.7 图片附件列表【学习笔记或跟师心得或医籍学习体会或典型案例或年度考核表或结业考核】(imageList)post</option>
			<option value="addImage" method="post" needDataType="N" <c:if test="${param.reqCode=='addImage'}">selected</c:if> >4.7 上传拍照图片【学习笔记或跟师心得或医籍学习体会或典型案例或年度考核表或结业考核】(addImage)post</option>
			<option value="delImage" method="post" needDataType="N" <c:if test="${param.reqCode=='delImage'}">selected</c:if> >4.7 删除附件【学习笔记或跟师心得或医籍学习体会或典型案例或年度考核表或结业考核】(delImage)post</option>
			<option value="bookRecordList" method="post" needDataType="N" <c:if test="${param.reqCode=='bookRecordList'}">selected</c:if> >4.7 中医经典书籍学习记录列表(bookRecordList)post</option>
			<option value="editBookRecord" method="post" needDataType="N" <c:if test="${param.reqCode=='editBookRecord'}">selected</c:if> >4.7 新增或编辑中医经典书籍学习记录列表(editBookRecord)post</option>
			<option value="saveBookRecord" method="post" needDataType="N" <c:if test="${param.reqCode=='saveBookRecord'}">selected</c:if> >4.7 保存中医经典书籍学习记录列表(saveBookRecord)post</option>
			<option value="showTypicalCases" method="post" needDataType="N" <c:if test="${param.reqCode=='showTypicalCases'}">selected</c:if> >4.7 典型案例列表(showTypicalCases)post</option>
			<option value="addTypicalCases" method="post" needDataType="N" <c:if test="${param.reqCode=='addTypicalCases'}">selected</c:if> >4.7 新增或编辑【典型案例】(addTypicalCases)post</option>
			<option value="saveTypicalCases" method="post" needDataType="N" <c:if test="${param.reqCode=='saveTypicalCases'}">selected</c:if> >4.7 保存【典型案例】(saveTypicalCases)post</option>
			<option value="delTypicalCases" method="post" needDataType="N" <c:if test="${param.reqCode=='delTypicalCases'}">selected</c:if> >4.7 删除【典型案例】(delTypicalCases)post</option>
			<option value="annualAssessmentList" method="post" needDataType="N" <c:if test="${param.reqCode=='annualAssessmentList'}">selected</c:if> >4.7 年度考核情况记录表列表(annualAssessmentList)post</option>
			<option value="selectAnnualTime" method="post" needDataType="N" <c:if test="${param.reqCode=='selectAnnualTime'}">selected</c:if> >4.7 新增【年度考核情况记录表】选择时间(selectAnnualTime)post</option>
			<option value="saveAnnualTime" method="post" needDataType="N" <c:if test="${param.reqCode=='saveAnnualTime'}">selected</c:if> >4.7 保存新增【年度考核情况记录表】选择时间(saveAnnualTime)post</option>
			<option value="addAnnualAssessment" method="post" needDataType="N" <c:if test="${param.reqCode=='addAnnualAssessment'}">selected</c:if> >4.7 编辑【年度考核情况记录表】(addAnnualAssessment)post</option>
			<option value="saveAnnualAssessment" method="post" needDataType="N" <c:if test="${param.reqCode=='saveAnnualAssessment'}">selected</c:if> >4.7 保存【年度考核情况记录表】(saveAnnualAssessment)post</option>
			<option value="delAnnualAssessment" method="post" needDataType="N" <c:if test="${param.reqCode=='delAnnualAssessment'}">selected</c:if> >4.7 删除 【年度考核情况记录表】(delAnnualAssessment)post</option>
			<option value="addGraduationAssessment" method="post" needDataType="N" <c:if test="${param.reqCode=='addGraduationAssessment'}">selected</c:if> >4.7 编辑【结业考核情况记录表】(addGraduationAssessment)post</option>
			<option value="saveGraduationAssessment" method="post" needDataType="N" <c:if test="${param.reqCode=='saveGraduationAssessment'}">selected</c:if> >4.7 保存【结业考核情况记录表】(saveGraduationAssessment)post</option>
			<option value="reFreshComMap" method="post" needDataType="N" <c:if test="${param.reqCode=='reFreshComMap'}">selected</c:if> >4.7 刷新列表表头数据(reFreshComMap)post</option>
			<option value="checkGraduationFile" method="post" needDataType="N" <c:if test="${param.reqCode=='checkGraduationFile'}">selected</c:if> >4.7 下载结业论文前校验文件是否存在(checkGraduationFile)post</option>
			<option value="downFile" method="post" isForm="Y" needDataType="N" <c:if test="${param.reqCode=='downFile'}">selected</c:if> >1.0.6 下载结业论文(downFile)post</option>
		</select>
					<input type="button" value="测试" class="search" onclick="test();">
					<a href="<s:url value='/res/xnres/test'/>">测试工具</a>
		</div>
		<div style="margin-left: 100px ;width: 800px;">
			记住参数：<br>
			userFlow:<input name="userFlow" type="text" value="${userFlow}">
			deptFlow:<input name="deptFlow" type="text" value="${deptFlow}">
			<br>
			cataFlow:<input name="cataFlow" type="text" value="${cataFlow}">
			dataFlow:<input name="dataFlow" type="text" value="${dataFlow}">
			<br>
			funcTypeId:<input name="funcTypeId" type="text" value="${funcTypeId}">
			funcFlow:<input name="funcFlow" type="text" value="${funcFlow}">
			<input type="submit" value="记住" class="search">
		</div>
	</div>
	</form>
	<div style="width: 60%; float: left">
	<div style="margin-top: 20px; margin-bottom: 20px; margin-left: 100px">
		参数&nbsp;：	<textarea id="reqParam" style="width: 600px; height: 200px;" name="reqParam" ></textarea>
	</div>
	<div id="reqParamDataTypeDiv"  style="margin-top: 20px; margin-bottom: 20px; margin-left: 100px;display: none;">
		参数2：<textarea id="reqParamDataType" style="width: 600px; height: 200px;" name="reqParamDataType" wrap="soft"></textarea>
	</div>
	<div style="margin-left: 100px">
		响应：	<pre><code id="rsp" class="JSON"></code></pre>
	</div>
	</div>
	<div id="deptList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&pageIndex=1&pageSize=100</textarea>
	</div>
	<div id="funcList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&deptFlow=${deptFlow}</textarea>
	</div>
	<div id="standardDeptList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&searchData=&pageIndex=1&pageSize=100</textarea>
	</div>
	<div id="addRotationDept" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&standardDeptFlow=&schDeptFlow=${schDeptFlow}&schStartDate=&schEndDate=&teacherUserFlow=&headUserFlow=</textarea>
	</div>
	<div id="modRotationDept" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&standardDeptFlow=&schDeptFlow=${schDeptFlow}&schStartDate=&schEndDate=&teacherUserFlow=&headUserFlow=&deptFlow=${deptFlow}</textarea>
	</div>
	<div id="deleteRotationDept" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&deptFlow=${deptFlow}</textarea>
	</div>
	<div id="getNewLectures" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="getHistoryLectures" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="lectureRegist" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&lectureFlow=${lectureFlow}</textarea>
	</div>
	<div id="evaluate" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&lectureFlow=${lectureFlow}</textarea>
	</div>
	<div id="saveEvaluate" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&lectureFlow=${lectureFlow}&evaContent=${evaContent}&evaScore=${evaScore}</textarea>
	</div>
	<div id="suggestions" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&pageIndex=1&pageSize=10
		</textarea>
	</div>
	<div id="delOpinions" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&trainingOpinionFlow=${trainingOpinionFlow}</textarea>
	</div>
	<div id="opinionsDetail" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&trainingOpinionFlow=${trainingOpinionFlow}</textarea>
	</div>
	<div id="saveOpinions" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&trainingOpinionFlow=${trainingOpinionFlow}&opinionUserContent=${opinionUserContent}</textarea>
	</div>
	<div id="getZhupeiNotices" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">noticeTitle=${noticeTitle}&userFlow=${userFlow}&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="zpNoticeDetail" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">recordFlow=${recordFlow}&userFlow=${userFlow}</textarea>
	</div>
	<div id="studentSignIn" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}</textarea>
	</div>
	<div id="saveSignIn" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&date=${date}&time=${time}&local=${local}&remark=${remark}</textarea>
	</div>
	<div id="modfiyPass" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&oldPass=&newPass=&reNewPass=</textarea>
	</div>
	<div id="allAfterDept" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&pageIndex=1&pageSize=100</textarea>
	</div>
	<div id="gradeDeptList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&pageIndex=1&pageSize=100</textarea>
	</div>
	<div id="userCenter" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}</textarea>
	</div>
	<div id="discipleIndex" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}</textarea>
	</div>
	<div id="discipleTeacherIndex" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}</textarea>
	</div>
	<div id="showFollowTeacherRecord" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&typeId=&pageIndex=1&pageSize=100</textarea>
	</div>
	<div id="addFollowTeacherRecord" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&recordFlow=</textarea>
	</div>
	<div id="delFollowTeacherRecord" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&recordFlow=</textarea>
	</div>
	<div id="saveFollowTeacherRecord" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&recordFlow=&auditStatusId=&discipleDate=&startTime=&endTime=&address=&jsonData=</textarea>
	</div>
	<div id="saveDiscipleInfo" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&discipleFlow=&workOrgName=&discipleStartDate=&discipleEndDate=</textarea>
	</div>
	<div id="saveDiscipleTeacherInfo" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&recordFlow=&teacherName=&sexId=&birthdate=&workSpeName=&teacherTitleName=&educationId=&address=&phone=&content=</textarea>
	</div>
	<div id="showDiscipleNoteInfo" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">noteTypeId=&userFlow=${userFlow}&typeId=&pageIndex=1&pageSize=100</textarea>
	</div>
	<div id="addDiscipleNoteInfo" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">noteTypeId=&userFlow=${userFlow}&recordFlow=</textarea>
	</div>
	<div id="delDiscipleNoteInfo" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&recordFlow=</textarea>
	</div>
	<div id="saveDiscipleNoteInfo" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">noteTypeId=&userFlow=${userFlow}&recordFlow=&jsonData=&flag=&其他参数请将【addDiscipleNoteInfo】接口返回的带有required=true的inputId依次拼接后提交，如果是提交操作请将flag的值设为Y</textarea>
	</div>
	<div id="imageList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">noteTypeId=&userFlow=${userFlow}&recordFlow=&pageIndex=1&pageSize=100</textarea>
	</div>
	<div id="addImage" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">noteTypeId=&userFlow=${userFlow}&recordFlow=&imageContent=&fileName=</textarea>
	</div>
	<div id="delImage" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&fileFlow=</textarea>
	</div>
	<div id="bookRecordList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&pageIndex=1&pageSize=100</textarea>
	</div>
	<div id="editBookRecord" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&recordFlow=</textarea>
	</div>
	<div id="saveBookRecord" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&recordFlow=&studyStartTime=&studyEndTime=&studyActionId=&studyContent=&remark=</textarea>
	</div>
	<div id="showTypicalCases" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">noteTypeId=TypicalCases&userFlow=${userFlow}&typeId=&pageIndex=1&pageSize=100</textarea>
	</div>
	<div id="addTypicalCases" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">noteTypeId=TypicalCases&userFlow=${userFlow}&recordFlow=</textarea>
	</div>
	<div id="saveTypicalCases" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">noteTypeId=TypicalCases&userFlow=${userFlow}&recordFlow=&jsonData=&flag=&其他参数请将【addTypicalCases】接口返回的所有inputId依次拼接后提交，如果是提交操作请将flag的值设为Y</textarea>
	</div>
	<div id="delTypicalCases" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&recordFlow=</textarea>
	</div>
	<div id="annualAssessmentList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">noteTypeId=AnnualAssessment&userFlow=${userFlow}&pageIndex=1&pageSize=100</textarea>
	</div>
	<div id="selectAnnualTime" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">noteTypeId=AnnualAssessment&userFlow=${userFlow}</textarea>
	</div>
	<div id="saveAnnualTime" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">noteTypeId=AnnualAssessment&userFlow=${userFlow}&annualYear=&startTime=&endTime=</textarea>
	</div>
	<div id="addAnnualAssessment" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">noteTypeId=AnnualAssessment&userFlow=${userFlow}&recordFlow=</textarea>
	</div>
	<div id="saveAnnualAssessment" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">noteTypeId=AnnualAssessment&userFlow=${userFlow}&jsonData=&flag=&其他参数请将【addAnnualAssessment】接口返回的所有inputId依次拼接后提交，如果是提交操作请将flag的值设为Y</textarea>
	</div>
	<div id="delAnnualAssessment" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&recordFlow=</textarea>
	</div>
	<div id="addGraduationAssessment" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">noteTypeId=GraduationAssessment&userFlow=${userFlow}</textarea>
	</div>
	<div id="saveGraduationAssessment" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">noteTypeId=AnnualAssessment&userFlow=${userFlow}&jsonData=&flag=&其他参数请将【addGraduationAssessment】接口返回的所有inputId依次拼接后提交，如果是提交操作请将flag的值设为Y</textarea>
	</div>
	<div id="reFreshComMap" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">noteTypeId=&userFlow=${userFlow}</textarea>
	</div>
	<div id="checkGraduationFile" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}</textarea>
	</div>
	<div id="downFile" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}</textarea>
	</div>
</body>
</html>
<script src="//cdnjs.cloudflare.com/ajax/libs/highlight.js/8.4/highlight.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	hljs.initHighlightingOnLoad();
});
</script>