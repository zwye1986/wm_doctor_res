
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<c:if test="${!noHead}">
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="false"/>
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
</c:if>
<script type="text/javascript">
	<c:set var="teaSub" value="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId}"/>
	<c:set var="headSub" value="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD && (not empty rec.auditStatusId)}"/>
	<c:set var="showMsg" value="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId &&  empty rec.auditStatusId}"/>

	function saveRec(){
		if($("#resRecGradeForm").validationEngine("validate")){
			jboxPost("<s:url value='/res/rec/saveDeptEval'/>",$("#resRecGradeForm").serialize(),function(resp){
				$("[name='recFlow']").val(resp);
				window.parent.frames['mainIframe'].window.toPage(1);
				top.jboxTip("${GlobalConstant.OPRE_SUCCESSED}");
				setTimeout(function(){
					jboxClose();
				},1000);

			},null,false);
		}
	}

	function countScore(){
		var count = 0;
		$(".scoreCount").each(function(){
			var score = $(this).val();
			if($.trim(score)!="" && !isNaN(score)){
				count+=parseFloat(score);
			}
		});
		$("#totalScore").val(count.toFixed(1));
	}

	
	$(function(){
		<c:if test="${showMsg or !(teaSub or headSub)}">
			$("input:text").attr("readonly",true);
		</c:if>
	});
</script>
</head>
<body>	
	<div class="mainright">
		<div class="content">
			<div class="title1 clearfix">
				<div style="padding-top: 10px;padding-left: 5px;padding-right: 5px;padding-bottom: 5px;">
					<c:if test="${not empty cfg}">
					<form id="resRecGradeForm" style="position: relative;">
						<input type="hidden" name="schDeptFlow" value="${empty rec?param.schDeptFlow:rec.schDeptFlow}"/>
						<input type="hidden" name="recFlow" value="${rec.recFlow}"/>
						<input type="hidden" name="recTypeId" value="${empty rec?param.recTypeId:rec.recTypeId}"/>
						<input type="hidden" name="processFlow" value="${param.processFlow}"/>
						<input type="hidden" name="operUserFlow" value="${param.operUserFlow}"/>
						<input type="hidden" name="cfgFlow" value="${cfg.cfgFlow}"/>
						<input type="hidden" name="roleFlag" value="${param.roleFlag}"/>
						<c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER}">
							<input type="hidden" name="auditStatusId" value="${recStatusEnumTeacherAuditY.id}"/>
						</c:if>
						<c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD}">
							<input type="hidden" name="headAuditStatusId" value="${recStatusEnumHeadAuditY.id}"/>
						</c:if>
					 	<c:forEach items="${titleFormList}" var="title">
					 		<table class="xllist nofix"  style="margin-top: 10px;">
					 		<tr>
								<th style="text-align: left;" colspan="3">&#12288;${title.name}</th>
							</tr>
					 		<c:forEach items="${title.itemList}" var="item">
					 			<tr>
									<td style="text-align: left;"  colspan="3">&#12288;&#12288;${item.name}</td>
								</tr>
								<tr>
									<td style="text-align: left;width: 100px;">&#12288;&#12288;分值：${item.score}
										<input type="hidden" name="assessId" value="${item.id}"/>
                                    </td>
									<td style="text-align: left;width:300px;">
									&#12288;我的打分：<input style="width: 200px;" name="score" type="text" class="inputText  validate[required,custom[number],max[${item.score}],min[0]] scoreCount" value="${gradeMap[item.id]['score']}" onkeyup="countScore();"/>
									</td>
									<td style="text-align: left;">&#12288;扣分原因：<input type="text" class="inputText" name="lostReason" style="text-align: left;width: 50%;" value="${gradeMap[item.id]['lostReason']}"/></td>
								</tr>
					 		</c:forEach>
					 		</table>
					 	</c:forEach>
					 
						<div style="padding-left: 140px;padding-top: 5px;">总分：<input style="width:200px;" type="text" name="totalScore" id="totalScore" class="inputText" value="${empty gradeMap['totalScore']?0:gradeMap['totalScore']}" readonly="readonly"/></div>
					</form>
					
					<div style="text-align: center;margin-top: 5px;">
							<c:if test="${showMsg}">
								[<font color="red">带教老师还未审核，请等待！</font>]
							</c:if>

							<c:if test="${teaSub or headSub}">
								<input class="search" type="button" value="保&#12288;存"  onclick="saveRec();"/>
							</c:if>
							<input class="search" type="button" value="关&#12288;闭"  onclick="jboxClose();"/>
					</div>
					</c:if>
					<c:if test="${empty cfg}">
						管理员未配置评价表单，请联系医院管理员！
					</c:if>
				</div>
			</div>
		</div>
	</div>
</body>
</html>