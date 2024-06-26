<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<script type="text/javascript">
function checkFile(file){
	var filePath = file.value;
	var types = "${sysCfgMap['inx_image_support_suffix']}".split(",");
	var regStr = "/";
	for(var i = 0 ;i<types.length;i++){
		if(types[i]){
			if(i==(types.length-1)){
				regStr = regStr+"\\"+types[i]+'$';
			}else{
				regStr = regStr+"\\"+types[i]+'$|';
			}
		}
	}
	regStr = regStr+'/i';
	regStr = eval(regStr);
	if($.trim(filePath)!="" && !regStr.test(filePath)){
		file.value = "";
		jboxTip("请上传&nbsp;${sysCfgMap['inx_image_support_suffix']}格式的图片");
	}
}

function checkUploadFile(){
	jboxTip("上传成功!");
	doClose();
}

function reupload(){
	$("#existsFile").hide();
	$("#file").show();
	$("#canBtn").show();
	$("#upBtn").hide();
}
function cancleReupload(){
	$("#existsFile").show();
	$("#file").hide();
	$("#canBtn").hide();
	$("#upBtn").show();
}

function reUpload(){
	$('#uploadErrorDiv').hide();
	$('#uploadFileDiv').show();
}

function doClose() {
	var openDialog = top.dialog.get('openDialog');
	if(openDialog !=null && openDialog.open){
		openDialog.close().remove();
	}
}

$(document).ready(function(){
	if ("${GlobalConstant.FLAG_Y}"=="${result}") {
		returnUrl();
	}
});	
</script>
</head>
<body>
<div class="infoAudit">
			<div style="margin-bottom: 30px;">
			<table  style="width: 100%;border: 1px solid #e3e3e3;height: 100px;">
				<tr>
					<td style="border: 1px solid #e3e3e3;width:100px;">出科考核表：</td>
					<td style="margin-top: 20px;">
					<c:choose>
						<c:when test="${!empty afterEvaluation}">
							<a href="<s:url value='/jsp/jsres/doctor/ckkhb.xlsx'/>" >出科考核表</a>
							<a class="btn" id="upBtn" onclick="reupload();" style="float: right">重新上传</a>
						</c:when>
						<c:otherwise>
							<a class="btn" id="canBtn" onclick="cancleReupload();" style="float: right;display: none">取消</a>
							<input id="file" type="file" name="file" onchange="checkFile(this);" class="validate[required]"/>
						</c:otherwise>
					</c:choose>
					</td>
				</tr>
				<!-- 
				<tr>
					<th  style="border: 1px solid #e3e3e3;width:100px;">模版：</th>
					<td><a href="<s:url value='/jsp/jsres/doctor/ckkhb_empty.xlsx'/>">出科考核表模版.xlsx</a></td>
				</tr>
				 -->
				
				<tr>
					<th colspan="2" style="text-align: left;color:red">*请拍照或扫描上传(需涵有红章)，考核表原件由科室保存</th>
				</tr>
			</table>
			</div>
			<div class="button">
				<input class="btn_blue" type="button" value="上&#12288;传" onclick="checkUploadFile();" />
				<input class="btn_blue" type="button" value="取&#12288;消" onclick="jboxClose();" />
			</div>
</div>
</body>
</html>