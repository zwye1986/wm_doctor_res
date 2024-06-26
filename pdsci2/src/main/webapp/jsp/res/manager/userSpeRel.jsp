<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/res/teacher/Style.css'/>"></link>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
</jsp:include>

<script type="text/javascript">
	function save(){
		var form=$("#userSpeForm").serialize();
		jboxPost("<s:url value='/res/manager/saveMajor'/>?",form,function(obj){
			if(obj=="${GlobalConstant.SAVE_SUCCESSED}"){
				window.parent.document.mainIframe.searchUser();			
				jboxClose();
			}
		});
	}
</script>
</head>
<body>
<div class="mainright">
	<div class="content" >
		<form id="userSpeForm">
		<input type="hidden" name="userFlow" value="${param.userFlow}">
			<table class="basic" style="width:100%;">
			
				<tr>
					<th style="text-align: left; padding-left: 10px;">关联专业</th>
				</tr>
				<tr>
					<td>
						<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="spe">
							<div style="float: left;width: 24%;">
								<label>
									<input type="checkbox" name="speIds" value="${spe.dictId}" <c:if test="${!empty resUserSpeMap[spe.dictId]}">checked="checked"</c:if> >
									${spe.dictName}
								</label>
							</div>
						</c:forEach>
					</td>
				</tr>
			</table>
			<div align="center" style="width: 100%; margin-top: 10px;" >
				<input type="button" value="保&#12288;存" class="search" onclick="save();">
				<input type="button" value="关&#12288;闭" class="search" onclick="jboxClose();">
			</div>
		</form>
	</div>
</div>
</body>
</html>