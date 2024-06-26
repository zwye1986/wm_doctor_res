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
		function changeStatus(id,name){
			jboxConfirm("确认操作？",function(){
				var url = "<s:url value='/gzykdx/secondaryRecruit/editDocTeaRec'/>";
				var schoolAuditMemo = $("#schoolAuditMemo").val();
				var data = {"recordFlow":'${param.recordFlow}',
							"schoolAuditStatusId":id,
							"schoolAuditStatusName":name,
							"schoolAuditMemo":schoolAuditMemo,
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
		<textarea style="width: 98%;" name="schoolAuditMemo" id="schoolAuditMemo" class="xltxtarea">  拟录取
		</textarea>
		<div style="text-align: center;margin-top: 20px;">
		(通过后确认考生被录取，不通过考生进入下批调剂。)
		</div>
	</div>
	<div style="text-align: center;margin-top: 20px;">
		<input type="button" class="search" value="通&#12288;过"
			   onclick="changeStatus('${gzykdxAdmissionStatusEnumPassed.id}','${gzykdxAdmissionStatusEnumPassed.name}')">
		<input type="button" class="search" value="不通过"
			   onclick="changeStatus('${gzykdxAdmissionStatusEnumUnPassed.id}','${gzykdxAdmissionStatusEnumUnPassed.name}')">
		<input type="button" value="取&#12288;消" onclick="top.jboxClose();" class="search">
	</div>
</div>
</body>
</html>