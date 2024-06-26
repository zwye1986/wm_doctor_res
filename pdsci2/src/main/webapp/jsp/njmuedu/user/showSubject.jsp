<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
<title></title>
<jsp:include page="/jsp/njmuedu/htmlhead-njmuedu.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jbox" value="true"/>
    <jsp:param name="userCenter" value="true"/>
	<jsp:param name="findCourse" value="true"/>
	<jsp:param name="jquery_scrollTo" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<script>
    function doClose(){
    	jboxClose();
    }

	function refresh(){
		top.window.loadContent('<s:url value='/njmuedu/user/courseOrder'/>');
	}

	function checkYear(obj){
		var dates = $(':text',$(obj).closest("td"));
		if(dates[0].value && dates[1].value && dates[0].value > dates[1].value){
			jboxTip("开始时间不能大于结束时间！");
			obj.value = "";
		}
	}
	function save() {
		if (!$("#editForm").validationEngine("validate")) {
			return;
		}
		var url = "<s:url value='/njmuedu/user/saveSubject'/>";
		top.jboxStartLoading();
		jboxPost(url,$("#editForm").serialize(),
				function(resp){
					top.jboxEndLoading();
					top.window.jboxTip(resp);
					if(resp == "${GlobalConstant.SAVE_SUCCESSED}"){
						//  window.parent.location.reload();
						refresh();
						jboxClose();

					}
				},
				function(resp){
					top.jboxEndLoading();},false);

	}
</script>
<head>
<body style="background:#fff; margin:0 10px;">      	
<div class="clear"></div> 
<div>
	<p class="courseP">课程情况</p>
	<form id="editForm" method="post" style="position: relative;">
	<input type="hidden" name="subjectFlow" value="${subject.subjectFlow}">
	<div class="part editTeacher">
	<table border="0" cellpadding="0" cellspacing="0" class="course-table course-table1" style="border:1px solid #d4e7f0;">
		<col width="15%">
		<col width="35%">
		<col width="15%">
		<col width="35%">
		<tr>
			<th><span style="color: red"></span>&nbsp;课程名称：</th>
            <td>${subject.subjectName}</td>
		</tr>
		<tr>
			<th><span style="color: red"></span>&nbsp;课程年份：</th>
			<td>${subject.subjectYear}</td>
		</tr>
		<tr>
			<th><span style="color: red"></span>&nbsp;课程类型：</th>
			<td>
			   <c:forEach items="${dictTypeEnumCourseTypeList}" var="dict">
					<c:if test="${dict.dictId eq subject.subjectType}"> ${dict.dictName} </c:if>
			   </c:forEach>
			</td>
		</tr>

		<c:if test="${param.flag eq 'D'}">
			<tr>
				<th><span style="color: red">*</span>&nbsp;预约开放时间：</th>
				<td>
					<input type="text" name="subjectStartTime" class="validate[required]" onchange="checkYear(this)" value="${subject.subjectStartTime}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"; />
					- <input type="text" name="subjectEndTime" class="validate[required]" onchange="checkYear(this)" value="${subject.subjectEndTime}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"); />
				</td>
			</tr>
		</c:if>
		<c:if test="${param.flag ne 'D'}">
			<tr>
				<th><span style="color: red"></span>&nbsp;预约开放时间：</th>
				<td>
						${subject.subjectStartTime}	— ${subject.subjectEndTime}
				</td>
			</tr>
		</c:if>
		<tr>
			<th><span style="color: red"></span>&nbsp;预约学员容量：</th>
			<td>${subject.reservationsNum}</td>
		</tr>
		<tr>
			<th><span style="color: red"></span>&nbsp;审核通过人数：</th>
			<td>${passNum}</td>
		</tr>
		<tr>
			<th><span style="color: red"></span>&nbsp;课程说明 ：</th>
			<td>${subject.subjectExplain}</td>
		</tr>
	</table>
	</div>
	</form>
	<div style="width: 600px;" class="editBtn">
		<c:if test="${param.flag eq 'D'}">
			<input type="button" class="search" id="saveBtn" value="保存" onclick='save();' />
		</c:if>
	 	<input type="button" class="search2" value="关闭" onclick='doClose();'/>
	</div>
 </div>

</body>
</html>