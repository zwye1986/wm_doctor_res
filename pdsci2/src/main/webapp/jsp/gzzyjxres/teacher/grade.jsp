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
	<c:set value="${!empty param.isRead}" var="isOpen"></c:set>
	<c:set value="${isOpen && param.isRead}" var="isRead"></c:set>
	<script type="text/javascript">
		function saveRec(){
			var roleFlag = '${roleFlag}';

			if($("#resRecGradeForm").validationEngine("validate")){
				jboxPost("<s:url value='/res/rec/saveGrade?='/>",$("#resRecGradeForm").serialize(),function(resp){
					$("[name='recFlow']").val(resp);
//				$("#submitButton").show();
					window.parent.frames['mainIframe'].window.$(".box.selected").click();
					top.jboxTip("${GlobalConstant.OPRE_SUCCESSED}");
					jboxClose();
				},null,false);
			}
		}


		function countScore(){
			var count = 0;
			$(".scoreCount<c:if test="${!(assessCfg.assessTypeId eq resAssessScoreTypeEnumPercentile.id)}">:checked</c:if>").each(function(){
				var score = $(this).val();
				if($.trim(score)!="" && !isNaN(score)){
					count+=parseFloat(score);
				}
			});
			$("#totalScore").val(count.toFixed(1));
		}

		<%--function submit(){--%>
		<%--jboxConfirm("确认提交?",function(){--%>
		<%--var recFlow = $("[name='recFlow']").val();--%>
		<%--jboxPost("<s:url value='/res/rec/opreResRecForGrade'/>",{"recFlow":recFlow,"statusId":"${recStatusEnumSubmit.id}"},function(resp){--%>
		<%--if(resp=="${GlobalConstant.OPRE_SUCCESSED}"){--%>
		<%--<c:if test="${isOpen}">--%>
		<%--window.parent.frames['mainIframe'].window.location.reload(true);--%>
		<%--jboxClose();--%>
		<%--</c:if>--%>
		<%--<c:if test="${!isOpen}">--%>
		<%--jboxClose();--%>
		<%--//location.reload(true);--%>
		<%--</c:if>--%>
		<%--}--%>
		<%--},null,true);--%>
		<%--},null);--%>
		<%--}--%>

		$(function(){
			<c:if test="${isRead || rec.statusId eq recStatusEnumSubmit.id}">
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
				<form id="resRecGradeForm" style="position: relative;">
					<input type="hidden" name="schDeptFlow" value="${empty rec?param.schDeptFlow:rec.schDeptFlow}"/>
					<input type="hidden" name="roleFlag" value="${param.roleFlag}"/>
					<input type="hidden" name="recFlow" value="${rec.recFlow}"/>
					<c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER}">
						<input type="hidden" name="recTypeId" value="${resRecTypeEnumTeacherDoctorGrade.id}"/>
					</c:if>

					<c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD}">
						<input type="hidden" name="recTypeId" value="${resRecTypeEnumHeadDoctorGrade.id}"/>
					</c:if>

					<input type="hidden" name="processFlow" value="${param.processFlow}"/>


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
									<td style="text-align: left;width: 100px;">&#12288;&#12288;分值：
										<c:choose>
											<c:when test="${assessCfg.assessTypeId eq resAssessScoreTypeEnumPercentile.id}">
												${item.score}
											</c:when>
											<c:when test="${assessCfg.assessTypeId eq resAssessScoreTypeEnumNine.id}">
												9
											</c:when>
											<c:otherwise>
												5
											</c:otherwise>
										</c:choose>
										<input type="hidden" name="assessId" value="${item.id}"/>
									</td>
									<td style="text-align: left;width: ${(assessCfg.assessTypeId eq resAssessScoreTypeEnumPercentile.id)?300:600}px;">
										&#12288;我的打分：
										<c:if test="${assessCfg.assessTypeId eq resAssessScoreTypeEnumPercentile.id}">
											<input style="width: 200px;" name="score" type="text" class="inputText  validate[required,custom[number],max[${item.score}],min[0]] scoreCount" value="${gradeMap[item.id]['score']}" onkeyup="countScore();"/>
										</c:if>
										<c:if test="${assessCfg.assessTypeId ne resAssessScoreTypeEnumPercentile.id}">
											<%--<c:if test="${empty gradeMap[item.id]['score']}">--%>

											<c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER or roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD}">
												<label>
													<input name="${item.id}score" type="radio"  class="validate[required] scoreCount" value="1" <c:if test="${gradeMap[item.id]['score'] == '1'}">checked</c:if> onchange="$('#${item.id}scoreSel').val(this.value);countScore();"/>
													差
												</label>
												<label>
													<input name="${item.id}score" type="radio" class="validate[required] scoreCount" value="2" <c:if test="${gradeMap[item.id]['score'] == '2'}">checked</c:if> onchange="$('#${item.id}scoreSel').val(this.value);countScore();"/>
													较差
												</label>
												<label>
													<input name="${item.id}score" type="radio" class="validate[required] scoreCount" value="3" <c:if test="${gradeMap[item.id]['score'] == '3'}">checked</c:if> onchange="$('#${item.id}scoreSel').val(this.value);countScore();"/>
													一般
												</label>
												<label>
													<input name="${item.id}score" type="radio" class="validate[required] scoreCount" value="4" <c:if test="${gradeMap[item.id]['score'] == '4'}">checked</c:if> onchange="$('#${item.id}scoreSel').val(this.value);countScore();"/>
													较好
												</label>
												<label>
													<input name="${item.id}score" type="radio" class="validate[required] scoreCount" value="5" <c:if test="${gradeMap[item.id]['score'] == '5'}">checked</c:if> onchange="$('#${item.id}scoreSel').val(this.value);countScore();"/>
													好
												</label>
											</c:if>
											<%--</c:if>--%>
											<c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR or roleFlag eq GlobalConstant.RES_ROLE_SCOPE_ADMIN}">
												<c:if test="${not empty gradeMap[item.id]['score'] && (fn:indexOf('1',gradeMap[item.id]['score'])>-1)}">
													${gradeMap[item.id]['score']}&nbsp;(差)
												</c:if>
												<c:if test="${not empty gradeMap[item.id]['score'] && (fn:indexOf('2',gradeMap[item.id]['score'])>-1)}">
													${gradeMap[item.id]['score']}&nbsp;(较差)
												</c:if>
												<c:if test="${not empty gradeMap[item.id]['score'] && (fn:indexOf('3',gradeMap[item.id]['score'])>-1)}">
													${gradeMap[item.id]['score']}&nbsp;(一般)
												</c:if>
												<c:if test="${not empty gradeMap[item.id]['score'] && (fn:indexOf('4',gradeMap[item.id]['score'])>-1)}">
													${gradeMap[item.id]['score']}&nbsp;(较好)
												</c:if>
												<c:if test="${not empty gradeMap[item.id]['score'] && (fn:indexOf('5',gradeMap[item.id]['score'])>-1)}">
													${gradeMap[item.id]['score']}&nbsp;(好)
												</c:if>
											</c:if>

                                            <input id="${item.id}scoreSel" name="score" type="hidden" value="${gradeMap[item.id]['score']}"/>
										</c:if>
									</td>
									<td style="text-align: left;">&#12288;扣分原因：<input type="text" class="inputText" name="lostReason" style="text-align: left;width: 50%;" value="${gradeMap[item.id]['lostReason']}"/></td>
								</tr>
							</c:forEach>
						</table>
					</c:forEach>

					<div style="padding-left: 140px;padding-top: 5px;">总分：<input style="width:200px;" type="text" name="totalScore" id="totalScore" class="inputText" value="${empty gradeMap['totalScore']?0:gradeMap['totalScore']}" readonly="readonly"/></div>
				</form>

				<div style="text-align: center;margin-top: 5px;">
					<c:if test="${empty rec.statusId}">
						<c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER or param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD}">
							<c:if test="${isShow ne GlobalConstant.FLAG_N }">
								<%--<input id="submitButton" type="button" value="提&#12288;交" class="search" onclick="submit();" style="${!empty rec?'':'display: none;'}"/>--%>
								<input type="button" value="保&#12288;存" class="search" onclick="saveRec();"/>
							</c:if>
						</c:if>
					</c:if>
					<input type="button" value="关&#12288;闭" class="search" onclick="jboxClose();"/>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>