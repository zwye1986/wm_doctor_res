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
    function save(){
        jboxConfirm("确认预约？", function () {
            jboxPost("<s:url value='/njmuedu/user/saveSubjectDetail'/>",$("#submitForm").serialize(),function(resp){
                if(resp=="${GlobalConstant.SAVE_SUCCESSED}"){
                    // window.parent.frames['mainIframe'].location.reload();//刷新页面
					window.parent.location.reload();
                    jboxClose();
                }
            },null,true);

        })
    }

</script>
<head>
<body style="background:#fff; margin:0 10px;">      	
<div class="mainright"></div>
<div>
	<p class="courseP">课程情况</p>
	<form id="submitForm" method="post" style="position: relative;">
	<input type="hidden" name="subjectFlow" value="${studySubject.subjectFlow}">
	<div class="part editTeacher">
	<table border="0" cellpadding="0" cellspacing="0" class="course-table course-table1" style="border:1px solid #d4e7f0;">
		<col width="15%">
		<col width="35%">
		<col width="15%">
		<col width="35%">
		<tr>
			<th><span style="color: red"></span>&nbsp;课程名称：</th>
            <td>${studySubject.subjectName}</td>
		</tr>
		<tr>
			<th><span style="color: red"></span>&nbsp;课程年份：</th>
			<td>${studySubject.subjectYear}</td>
		</tr>
		<tr>
			<th><span style="color: red"></span>&nbsp;课程类型：</th>
			<td>
			   <c:forEach items="${dictTypeEnumCourseTypeList}" var="dict">
					<c:if test="${dict.dictId eq studySubject.subjectType}"> ${dict.dictName} </c:if>
			   </c:forEach>
			</td>
		</tr>
		<tr>
			<th><span style="color: red"></span>&nbsp;预约开放时间：</th>
			<td>
				${studySubject.subjectStartTime}	— ${studySubject.subjectStartTime}
			</td>
		</tr>
		<tr>
			<th><span style="color: red"></span>&nbsp;预约学员容量：</th>
			<td>${studySubject.reservationsNum}</td>
		</tr>
		<tr>
			<th><span style="color: red"></span>&nbsp;课程说明 ：</th>
			<td>${studySubject.subjectExplain}</td>
		</tr>
	</table>
	</div>
	</form>
	<div style="width: 600px;" class="editBtn">
		<c:if test="${param.flag ne 'view'}"><input type="button" class="search" onclick="save();" value="预&#12288;约" id="saveBtn"/></c:if>
	 	<input type="button" class="search2" value="关闭" onclick='doClose();'/>
	</div>
 </div>

</body>
</html>