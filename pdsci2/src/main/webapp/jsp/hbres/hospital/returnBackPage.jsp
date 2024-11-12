<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>湖北省住院医师规范化培训招录系统</title>
<jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="ueditor" value="true"/>
</jsp:include>
<script type="text/javascript">
	//不发复试通知直接退回
	function returnBack(recruitFlow){
		if(!$("#noticeForm").validationEngine("validate")){
			return;
		}
		jboxConfirm("确认退回？", function(){
			jboxPost("<s:url value='/hbres/singup/hospital/returnBack'/>" , $("#noticeForm").serialize() , function(resp){
				if(resp == "${GlobalConstant.OPRE_SUCCESSED_FLAG}"){
					jboxTip("${GlobalConstant.OPERATE_SUCCESSED}");
					window.parent.searchDoctor();
					jboxClose();
				}
			} , null , false);
		});
	}
</script>
</head>
<body style="width:100%; min-width: inherit; height:100%;background-image:none; background:#fff;">
	<div class="infoAudit" >
	<form id="noticeForm">
		<input type="hidden" name="recruitFlow" value="${param.recruitFlow}">
		<input type="hidden" name="returnBackFlag" value="N">
		<table border="0" width="925" cellspacing="0" cellpadding="0">
				<caption>退回原因</caption>
				<tr>
					<td colspan="4" style="padding-top:10px;padding-left:0;">
					<textarea class="validate[maxSize[250]] txtarea" name="returnBackRemark"></textarea>
					</td>
				</tr>
			</table>
		</form>
		<div align="center" style="margin-top: 20px; margin-bottom:20px;">
			<a class="btn_green" id="operBtn" href="javascript:returnBack();">确定</a>
			<a class="btn_grey" id="closeBtn" href="javascript:jboxClose();">关闭</a>
		</div>
	</div>
</body>
</html>