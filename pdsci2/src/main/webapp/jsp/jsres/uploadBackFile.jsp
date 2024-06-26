<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<script type="text/javascript">
function add(exp){
	$(exp).parents('tr').after("<tr class='back' name='backFile'>" +
			"<th></th>" +
			"<td colspan='3'>" +
			"<input type='file' name='files' class='validate[required]' multiple='multiple'/><span class='red'>*</span>" +
			"<span style='float: right;padding-right: 34px'>" +
			"<img class='opBtn' title='新增' src=\"<s:url value='/css/skin/${skinPath}/images/add3.png' />\"style='cursor: pointer;' onclick='add(this)'></img>&#12288;" +
			"<img class='opBtn' title='删除'  style='cursor: pointer;' src=\"<s:url value='/css/skin/${skinPath}/images/del1.png'/>\" onclick='delTr(this);'></img>" +
			"</span></td></tr>" +
			"");
}
function delTr(exp){
	$(exp).parents('tr').remove();
}
function save(){
	if(false==$("#uploadFileForm").validationEngine("validate")){
		return false;
	}
	var url='<s:url value="/jsres/doctor/saveBackFile"/>';
	jboxSubmit($("#uploadFileForm"),url,function(){
		window.parent.toPage("${param.currentPage}");
		setTimeout(function(){
			jboxClose();
		}, 1000);
	},null,true)
}
function doClose(){
	jboxClose();
}
</script>
</head>
<body>
<div class="infoAudit">
	<form id="uploadFileForm" method="post" style="position:relative;" enctype="multipart/form-data">
		<input type="hidden" name="recordFlow" value="${recordFlow}"/>
		<div style="overflow: auto;height: 200px;">
			<div style="text-align: center;vertical-align: middle;">
				<table style="width: 100%;border: 1px solid #e3e3e3;margin-top: 25px;">
					<tr class="back" name="backFile">
						<th>上传附件：</th>
						<td colspan="3">
							<input type="file" name="files" class="validate[required]" multiple="multiple"/><span class="red">*</span>
							<span style="float: right;padding-right: 50px">
									<img class="opBtn" title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="add(this)"></img>&#12288;
							</span>
						</td>
					</tr>
				</table>
			</div>
			<div class="button">
				<input class="btn_green" type="button" value="确&#12288;定" onclick="save();" />
				<input class="btn_green" type="button" value="取&#12288;消" onclick="doClose();" />
			</div>
		</div>
	</form>
</div>
</body>
</html>