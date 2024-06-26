
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
		if($("#resRecGradeForm").validationEngine("validate")){
			var teacherNameOne = $("#sel1 option:selected").text();
			$("[name='teacherNameOne']").val(teacherNameOne);

			var teacherNameTwo = $("#sel2 option:selected").text();
			$("[name='teacherNameTwo']").val(teacherNameTwo);

			var teacherNameThree = $("#sel3 option:selected").text();
			$("[name='teacherNameThree']").val(teacherNameThree);

			jboxPost("<s:url value='/res/rec/saveDoctorEval'/>",$("#resRecGradeForm").serialize(),function(resp){
				$("[name='recFlow']").val(resp);
				$("#submitButton").show();
				window.parent.frames['mainIframe'].window.$(".box.selected").click();
				top.jboxTip("${GlobalConstant.OPRE_SUCCESSED}");
			},null,false);
		}
	}
	
	function back(){
		window.location.href="<s:url value='/res/doc/rotationDetail'/>?resultFlow=${param.resultFlow}&schDeptFlow=${param.schDeptFlow}&rotationFlow=${param.rotationFlow}";
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
	
	function submit(){
		jboxConfirm("确认提交?",function(){
			var recFlow = $("[name='recFlow']").val();
			jboxPost("<s:url value='/res/rec/opreResRecForGrade'/>",{"recFlow":recFlow,"statusId":"${recStatusEnumSubmit.id}"},function(resp){
				if(resp=="${GlobalConstant.OPRE_SUCCESSED}"){
					<c:if test="${isOpen}">
						window.parent.frames['mainIframe'].window.location.reload(true);
						jboxClose();
					</c:if>
					<c:if test="${!isOpen}">
						jboxClose();
						//location.reload(true);
					</c:if>
				}
			},null,true);
		},null);
	}
	
	$(function(){
		<c:if test="${isRead || rec.statusId eq recStatusEnumSubmit.id}">
			$("input:text").attr("readonly",true);
		</c:if>
	});

	function change(obj) {
		switch(obj) {
			case 1:
				if($("#sel1").val()!=null && $("#sel1").val()!="") {
					if ($("#sel1").val() == $("#sel2").val() || $("#sel1").val() == $("#sel3").val()) {
						jboxTip("列表框的值不能一样");
						$("#sel1").val("");
						return false;
					}
				}
				break;

			case 2:
				if($("#sel2").val()!=null && $("#sel2").val()!="") {
					if ($("#sel2").val() == $("#sel1").val() || $("#sel2").val() == $("#sel3").val()) {
						jboxTip("列表框的值不能一样");
						$("#sel2").val("");
						return false;
					}
				}
				break;
			case 3:
				if($("#sel3").val()!=null && $("#sel3").val()!="") {
					if ($("#sel3").val() == $("#sel1").val() || $("#sel3").val() == $("#sel2").val()) {
						jboxTip("列表框的值不能一样");
						$("#sel3").val("");
						return false;
					}
				}
				break;
		}
	}

</script>
</head>
<%--<body>
	<div class="mainright">
		<div class="content">
			<div class="title1 clearfix">
				<div style="padding-top: 10px;padding-left: 5px;padding-right: 5px;padding-bottom: 5px;">
					<form id="resRecGradeForm" style="position: relative;">
						<input type="hidden" name="schDeptFlow" value="${empty rec?param.schDeptFlow:rec.schDeptFlow}"/>
						<input type="hidden" name="recFlow" value="${rec.recFlow}"/>
						<input type="hidden" name="recTypeId" value="${empty rec?param.recTypeId:rec.recTypeId}"/>
						<input type="hidden" name="processFlow" value="${param.processFlow}"/>
						<input type="hidden" name="cfgFlow" value="${param.cfgFlow}"/>
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
						<c:if test="${empty rec.statusId}">
							<c:if test="${param.roleFlag == GlobalConstant.RES_ROLE_SCOPE_DOCTOR }">
								<input id="submitButton" type="button" value="提&#12288;交" class="search" onclick="submit();" style="${!empty rec?'':'display: none;'}"/>
								<input type="button" value="保&#12288;存" class="search" onclick="saveRec();"/>
							</c:if>
						</c:if>
						<input type="button" value="关&#12288;闭" class="search" onclick="jboxClose();"/>
					</div>
				</div> 	
			</div>
		</div>
	</div>
</body>--%>
<body>
<div class="mainright">
	<div class="content">
		<div class="title1 clearfix">
			<div style="padding-top: 10px;padding-left: 5px;padding-right: 5px;padding-bottom: 5px;">
				<form id="resRecGradeForm" style="position: relative;">
					<input type="hidden" name="schDeptFlow" value="${empty rec?param.schDeptFlow:rec.schDeptFlow}" onchange="loadTeacherAndHead(this.value);"/>
					<input type="hidden" name="recFlow" value="${rec.recFlow}"/>
					<input type="hidden" name="recTypeId" value="${empty rec?param.recTypeId:rec.recTypeId}"/>
					<input type="hidden" name="processFlow" value="${param.processFlow}"/>
					<input type="hidden" name="cfgFlow" value="${param.cfgFlow}"/>
					<input type="hidden" name="teacherNameOne" value="${rec.teacherNameOne}"/>
					<input type="hidden" name="teacherNameTwo" value="${rec.teacherNameTwo}"/>
					<input type="hidden" name="teacherNameThree" value="${rec.teacherNameThree}"/>
					<table class=""  style="margin-top: 10px;">
						<tr>
							<td style="font-size: 16px;height: 35px;">轮转科室：${doctorSchProcess.schDeptName}</td>
							<td style="font-size: 16px;height: 35px;">轮转时间：${doctorSchProcess.schStartDate}&nbsp;&nbsp;至&nbsp;&nbsp;${doctorSchProcess.schEndDate}</td>
						</tr>
						<tr>
							<td style="font-size: 16px;height: 35px;">
								推选本科室优秀带教老师(≤3 名):

								<select class="teacherSeller validate[required]" id="sel1" name="teacherFlowOne" style="width: 100px;" onchange="change(1)">
									<option value=""></option>
									<c:forEach items="${teacherList}" var="teacherOne">
										<option value="${teacherOne.userFlow}" ${teacherOne.userFlow eq rec.teacherFlowOne ?'selected':''}>${teacherOne.userName}</option>
									</c:forEach>
								</select>
								<select class="" id="sel2" name="teacherFlowTwo" style="width: 100px;" onchange="change(2)">
									<option value=""></option>
									<c:forEach items="${teacherList}" var="teacherTwo">
										<option value="${teacherTwo.userFlow}" ${teacherTwo.userFlow eq rec.teacherFlowTwo ?'selected':''}>${teacherTwo.userName}</option>
									</c:forEach>
								</select>
								<select class="" id="sel3" name="teacherFlowThree" style="width: 100px;" onchange="change(3)">
									<option value=""></option>
									<c:forEach items="${teacherList}" var="teacherThree">
										<option value="${teacherThree.userFlow}" ${teacherThree.userFlow eq rec.teacherFlowThree ?'selected':''}>${teacherThree.userName}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
					</table>
					<table class="xllist nofix"  style="margin-top: 10px;">
						<tr>
							<td style="text-align: left; width: 20%;" colspan="2">
								考评项
							</td>
							<td style="text-align: center;" colspan="2">
								优
							</td>
							<td style="text-align: center;" colspan="2">
								良
							</td>
							<td style="text-align: center;" colspan="2">
								中
							</td>
							<td style="text-align: center;" colspan="2">
								差
							</td>
							<td style="text-align: center;width: 20%;" colspan="2">
								备注(若哪项没有，可填无)
							</td>
						</tr>
						<c:forEach items="${titleFormList}" var="title">
							<c:forEach items="${title.itemList}" var="item">
								<tr>
									<td style="text-align: left;"  colspan="2">&#12288;&#12288;${item.name}
										<input type="hidden" name="assessId" value="${item.id}"/>
									</td>
									<td style="text-align: center;" colspan="2"><input type="radio" class=" validate[required]" name="${item.id}" ${gradeMap[item.id]['score'] eq '1' ?'checked':''} value="1"></td>
									<td style="text-align: center;" colspan="2"><input type="radio" class=" validate[required]" name="${item.id}" ${gradeMap[item.id]['score'] eq '2' ?'checked':''} value="2"></td>
									<td style="text-align: center;" colspan="2"><input type="radio" class=" validate[required]" name="${item.id}" ${gradeMap[item.id]['score'] eq '3' ?'checked':''} value="3"></td>
									<td style="text-align: center;" colspan="2"><input type="radio" class=" validate[required]" name="${item.id}" ${gradeMap[item.id]['score'] eq '4' ?'checked':''} value="4"></td>
									<td style="text-align: center;" colspan="2"><input type="text" name="lostReason" value="${gradeMap[item.id]['lostReason']}" style="width: 95%;text-align: center"></td>
								</tr>
							</c:forEach>
						</c:forEach>

					</table>
					<table class="xllist nofix"  style="margin-top: 10px;">
						<tr>
							<td style="text-align: left;" colspan="2">&#12288;&#12288;有无专人带教</td>
							<td style="text-align: center;" colspan="4">有<input type="radio" class=" validate[required]" name="teach" ${gradeMap['teach'] eq 'yes' ?'checked':''} value="yes"></td>
							<td style="text-align: center;" colspan="4">无<input type="radio" class=" validate[required]" name="teach" ${gradeMap['teach'] eq 'no' ?'checked':''} value="no"></td>
							<td style="text-align: center;" colspan="2"></td>
						</tr>
						<tr>
							<td style="text-align: left;" colspan="2">&#12288;&#12288;特色教学活动或亮点</td>
							<td style="text-align: center;" colspan="10"><input name="activty" class=" validate[required]" type="text" style="width: 98%" value="${gradeMap['activty']}"></td>
						</tr>
						<tr>
							<td style="text-align: left;" colspan="2">&#12288;&#12288;您对本科室整体带教的建议和意见</td>
							<td style="text-align: center;" colspan="10"><input name="jianyi" class=" validate[required]" type="text" style="width: 98%" value="${gradeMap['jianyi']}"></td>
						</tr>
					</table>
				</form>

				<div style="text-align: center;margin-top: 5px;">
					<c:if test="${empty rec.statusId}">
						<c:if test="${param.roleFlag == GlobalConstant.RES_ROLE_SCOPE_DOCTOR }">
							<input id="submitButton" type="button" value="提&#12288;交" class="search" onclick="submit();" style="${!empty rec?'':'display: none;'}"/>
							<input type="button" value="保&#12288;存" class="search" onclick="saveRec();"/>
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