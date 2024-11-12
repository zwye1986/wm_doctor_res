<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>湖北省住院医师规范化培训招录系统</title>
<jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<script type="text/javascript">
function saveGrade(){
	if(false==$("#gradeForm").validationEngine("validate")){
		return ;
	}
	jboxConfirm("确认保存分数？" , function(){
		var url = "<s:url value='/hbres/singup/hospital/savegrade'/>";
		var reqdata = $('#gradeForm').serialize();
		jboxPost(url, reqdata, function(resp) {
			if(resp=="1"){
				jboxTip("操作成功");
				window.parent.searchDoctor();
				jboxClose();		
			}else{
				jboxTip("操作失败");
			}
		},null,false);
	});
}

</script>
<style>
</style>
</head>
<body>
	<div class="div_search">
	<form id="gradeForm">
	    <table border="0" width="100%">
	    <tr><td width="35%" align="right" height="80px">
	    <input type="hidden" name="recruitFlow" value="${recruit.recruitFlow}"/>
	    <label>面试成绩：</label>
	    </td>
	    <td>
	    <input type="text" class="input validate[custom[number]]" name="auditionResult"  value="${recruit.auditionResult}"/>
	    </td>
	    </tr>
	    <tr>
	    <td align="right"><label>操作成绩：</label></td>
	    <td><input type="text" class="input validate[custom[number]]" name="operResult" value="${recruit.operResult}"/></td>
	    </tr>
	    </table>
	    <div align="center" class="div_table">
	    <input type="button" class="btn_green" style="width: 100px;" id="operBtn" onclick="saveGrade();" value="确认"/>
	    <input type="button" class="btn" style="width: 100px;" id="closeBtn" onclick="jboxClose();" value="取消"/>
	    </div>
	</form>
	
	</div>
</body>
</html>