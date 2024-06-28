
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_cxselect" value="true"/>
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
	$(function () {
		//计算总分
		var tds = $(".cSum")
		var sum = 0;
		var sum2 = 0;
		for (var i = 0; i < tds.length; i++) {
			var m = tds.eq(i).text();
			if (m!=null&&m!="") {
				sum += parseFloat(m);
			}
		}
		if(sum!=0) {
			$("#cSum").text(sum);
		}
		var tds = $(".sSum")
		for(var i=0;i<tds.length;i++){
			var m = tds.eq(i).text();
			sum2 += parseFloat(m);
		}
		if(sum2!=0) {
			$("#sSum").text(sum2);
		}

	})
function single(box){
    var curr=box.checked;
 	if(curr){
		var name=box.name;
		$(":checkbox[name='"+name+"']").attr("checked",false);
	}
	  box.checked = curr;
	var weight = $(box).parent().parent().prev().text();
	var score = $(box).parent().text();
	$(box).parent().parent().next().text(score*weight);
	total(box);
  }
function count(text){
	var weight = parseFloat($(text).parent().next().text())/100;
	var score = $(text).val()*weight;
	$(text).parent().next().next().text(score);
	var table = $("table").eq(1).children().last();
	var trs = table.children("tr");
	var sum = 0;
	for(var i=1;i<trs.length-1;i++){
		var td = trs.eq(i).children().last();
		var m = td.text();
		if(m!=""&&m!=null) {
			sum += parseFloat(m);
		}
	}
	$(text).parent().parent().parent().children("tr").last().children().last().text(sum);
}
function total(box){
	var trs = $(box).parent().parent().parent().parent().children("tr");
//	var trs2 = $(':last',$('tr',$(box).closest('table')));
//	trs2.each(function(){
//		alert($(this).text());
//	});
	var sum = 0;
	for(var i=1;i<trs.length-1;i++){
		var td = trs.eq(i).children().last();
//		var td2 = $(':last',trs[i]);
//		var m = td2.text();
//		if(m!=""&&m!=null) {
//			sum += parseFloat(m);
//		}
		var m = td.text();
		if(m!=""&&m!=null) {
			sum += parseFloat(m);
		}
	}
	$(box).parent().parent().parent().parent().children("tr").last().children().last().text(sum);
//	$('td:last',$(box).closest('table')).text(sum);
}
function save(){
	if($("#evaluationForm").validationEngine("validate")){
		jboxConfirm("确认保存？保存之后不可修改！",function(){
		autoValue($("#evaluationForm"),"autoValue");
		jboxPost("<s:url value='/res/rec/saveRegistryFormNew'/>",$("#evaluationForm").serialize(),function(resp){
			if(resp=="${GlobalConstant.SAVE_SUCCESSED}"){
				window.parent.document.mainIframe.location.reload();
			   jboxClose();
			}				
		},null,true);
		});
	}
}

</script>
</head>
<body>	
   <div class="mainright">
      <div class="content">
			<form id="evaluationForm">
				<input type="hidden" name="formFileName" value="${formFileName}"/>
				<input type="hidden" name="schDeptFlow" value="${param.schDeptFlow}"/>
				<input type="hidden" name="operUserFlow" value="${param.operUserFlow}"/>
				<input type="hidden" name="roleFlag" value="${roleFlag}"/>
				<input type="hidden" name="recFlow" value="${rec.recFlow}"/>
				<input type="hidden" name="processFlow" value="${currRegProcess.processFlow}"/>
				<c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER}">
					<input type="hidden" name="auditStatusId" value="${recStatusEnumTeacherAuditY.id}"/>
				</c:if>
				<c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD}">
					<input type="hidden" name="headAuditStatusId" value="${recStatusEnumHeadAuditY.id}"/>
				</c:if>
				<label style="margin-bottom: 10px;">
					姓名：
					<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId) }">
						<input type="text" class="inputText"  name="name" value="${empty formDataMap['name']?doctor.doctorName:formDataMap['name']}"/>
					</c:if>	
					<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId)) }">
						${formDataMap['name']}
						<input type="hidden" class="inputText"  name="name" value="${formDataMap['name']}"/>
					</c:if>
				</label>
				<p style="float: right; margin-bottom: 10px;" >届别：
				<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId) }">
					<input type="text" class="inputText"  name="sessional" value="${formDataMap['sessional']}"/>&#12288;
				</c:if>
				<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId)) }">
					${formDataMap['sessional']}
					<input type="hidden" class="inputText"  name="sessional" value="${formDataMap['sessional']}"/>&#12288;
				</c:if>					
				&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;
					培训专业：
					<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId) }">
						<input type="text" class="inputText"  name="trainMajor" value="${formDataMap['trainMajor']}"/>
					</c:if>
					<c:if test="${!((param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId)) }">
						${formDataMap['trainMajor']}
						<input type="hidden" class="inputText"  name="trainMajor" value="${formDataMap['trainMajor']}"/>
					</c:if>
				</p>

                <!-- 引入科室表单 -->
                <jsp:include page="/jsp/res/form/ChineseMedicine/deptFormHandel.jsp" flush="true"></jsp:include>
				<table class="basic" width="100%" style="margin-top: 10px;">
				</table>
				<p align="center" style="margin-top: 10px;">
					<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId) }">
						<input class="search" type="button" value="保&#12288;存"  onclick="save();"/>
					</c:if>
					<input class="search" type="button" value="关&#12288;闭"  onclick="jboxClose();"/>
				</p>
			</form>
		</div>
	</div>
</body>
</html>