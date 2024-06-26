
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
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
<script type="text/javascript">
	function save(){
		if($("#lectureGradeForm").validationEngine("validate")){
			jboxPost("<s:url value='/res/lecture4qingpu/saveGrade'/>",$("#lectureGradeForm").serialize(),function(resp){
				top.jboxTip("${GlobalConstant.OPRE_SUCCESSED}");
				window.parent.frames['mainIframe'].window.$("#history").click();
				top.jboxCloseMessager();
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
		<c:if test="${flag eq 'N'}">
			$("input:text").attr("readonly",true);
			$("textarea").attr("readonly",true);
		</c:if>
	});
</script>
</head>
<body>	
	<div class="mainright">
		<div class="content">
			<div class="title1 clearfix">
				<div style="padding-top: 10px;padding-left: 5px;padding-right: 5px;padding-bottom: 5px;">
					<form id="lectureGradeForm" style="position: relative;">
						<input type="hidden" name="recordFlow" value="${resLectureEvaDetail.recordFlow}"/>
						<input type="hidden" name="lectureFlow" value="${lectureFlow}"/>
					 	<c:forEach items="${titleFormList}" var="title">
					 		<table class="xllist nofix"  style="margin-top: 10px;">
					 		<tr>
								<th style="text-align: left;" colspan="3">&#12288;${title.name}</th>
							</tr>
					 		<c:forEach items="${title.itemList}" var="item">
					 			<tr>
									<td style="text-align: left;"  colspan="3">&#12288;&#12288;${item.order}.${item.name}</td>
								</tr>
								<tr>
									<td style="text-align: left;width: 100px;">&#12288;&#12288;分值：
                                        ${item.score}
										<input type="hidden" name="assessId" value="${item.id}"/>
                                    </td>
									<td style="text-align: left;width: 100px;">
									&#12288;我的打分：
										<input style="width: 200px;" name="score" type="text" class="inputText validate[required,custom[number],max[${item.score}],min[0]] scoreCount" value="${gradeMap[item.id]['score']}" onkeyup="countScore();"/>
									</td>
								</tr>
					 		</c:forEach>
					 		</table>
					 	</c:forEach>
						<table class="xllist nofix"  style="margin-top: 10px;">
							<tr>
								<th style="text-align: left">
									&#12288;意见或建议：
								</th>
							</tr>
							<tr>
								<td>
									<textarea class="validate[required,maxSize[500]] xltxtarea" name="evaContent">${resLectureEvaDetail.evaContent}</textarea>
								</td>
							</tr>
						</table>
						<div style="padding-left: 140px;padding-top: 5px;">总分：<input style="width:200px;" type="text" name="totalScore" id="totalScore" class="inputText" value="${empty gradeMap['totalScore']?0:gradeMap['totalScore']}" readonly="readonly"/></div>
					</form>
					
					<div style="text-align: center;margin-top: 5px;">
						<c:if test="${flag eq 'Y'}">
						<input type="button" value="保&#12288;存" class="search" onclick="save();"/>
						</c:if>
						<input type="button" value="关&#12288;闭" class="search" onclick="jboxCloseMessager();"/>
					</div>
				</div> 	
			</div>
		</div>
	</div>
</body>
</html>