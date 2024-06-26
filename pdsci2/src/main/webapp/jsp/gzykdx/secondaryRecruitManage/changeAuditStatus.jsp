<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>

	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="font" value="true"/>
		<jsp:param name="jquery_form" value="true"/>
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
	<script type="text/javascript">
		function secondaryChangeStatus(id,name){
			jboxConfirm("确认操作？",function(){
				var url = "<s:url value='/gzykdx/secondaryRecruit/secondaryEditDocTeaRec'/>";
				var orgAuditMemo = $("#orgAuditMemo").val();
				var data = {"recordFlow":'${param.recordFlow}',
							"auditStatusId":id,
							"auditStatusName":name,
							"schoolAuditStatusId":id,
							"schoolAuditStatusName":name,
							"orgAuditMemo":orgAuditMemo,
				}
				jboxPost(url,data,function(resp){
					if(resp==1) {
						jboxTip("操作成功！");
						window.parent.frames['mainIframe'].location.reload(true);
						jboxClose();
					}
					if(resp==-1) {
						jboxTip("操作失败！导师剩余名额为0！");
					}
				},null,false)
			})
		}
	</script>
</head>
<body>

<div class="mainright">
		<input type="hidden" name="recordFlow" value="${param.recordFlow}">
	<div>
		审核意见：
		<textarea style="width: 98%;" class="xltxtarea" name="orgAuditMemo" id="orgAuditMemo">  拟录取
		</textarea>
	</div>
	<div style="text-align: center;margin-top: 20px;">
		<input type="button" class="search" value="录&#12288;取"
			   onclick="secondaryChangeStatus('${gzykdxAdmissionStatusEnumSchoolPassing.id}','${gzykdxAdmissionStatusEnumSchoolPassing.name}')">
		<input type="button" class="search" value="不录取"
			   onclick="secondaryChangeStatus('${gzykdxAdmissionStatusEnumUnPassed.id}','${gzykdxAdmissionStatusEnumUnPassed.name}')">
		<input type="button" value="取&#12288;消" onclick="top.jboxClose();" class="search">
	</div>
</div>
</body>
</html>