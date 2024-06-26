
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
			jboxPost("<s:url value='/res/deptActivityStatistics/saveEval'/>",$("#lectureGradeForm").serialize(),function(resp){
				jboxTip(resp);
				if(resp=="${GlobalConstant.SAVE_SUCCESSED}")
				{
					setTimeout(function(){
						window.parent.frames['mainIframe'].window.toPage(1);
						jboxClose();
					},1000);
				}
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
		<c:if test="${not empty eval}">
			$("input:text").attr("readonly",true);
			$("input:radio").attr("disabled","disabled");
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
						<input type="hidden" name="recordFlow" value="${eval.evalFlow}"/>
						<input type="hidden" name="itemFlow" value="${item.itemFlow}"/>
						<c:if test="${item.itemTypeId ne 'JXCFAP'}">
						<table class="xllist nofix"  style="margin-top: 10px;">
							<tr>
								<td  colspan="6" style="text-align: left;padding-left: 10px;font-weight: bold;">
									基本信息
								</td>
							</tr>
							<tr  >
								<th>时间</th>
								<td>${item.planTime}
									<input type="hidden" name="planTime" value="${item.planTime}"/>
								</td>
								<th>姓名</th>
								<td>${user.userName}
									<input type="hidden" name="userName" value="${user.userName}"/>
								</td>
								<th>基地名称</th>
								<td>
										${doctor.orgName}
									<input type="hidden" name="orgName" value="${doctor.orgName}"/>
								</td>
							</tr>
							<tr >
								<th>课程名称</th>
								<td colspan="5">
									<input type="text"style="width: 90%" class="inputText validate[required]" name="name" value="${gradeMap['name']}"/>
								</td>
							</tr>
							<tr>
								<th>授课教师</th>
								<td>${joinUser.userName}
									<input type="hidden" name="teaName" value="${joinUser.userName}"/>
								</td>
								<th>职称</th>
								<td>${joinUser.postName}
									<input type="hidden" name="teaTitle" value="${joinUser.postName}"/>
								</td>
								<th>科室</th>
								<td>
										${joinUser.deptName}
									<input type="hidden" name="deptName" value="${joinUser.deptName}"/>
								</td>
							</tr>
							</table>
						</c:if>
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

						<c:if test="${item.itemTypeId eq 'JXCFAP'}">
							<table class="xllist nofix"  style="margin-top: 10px;">
								<tr>
									<th style="text-align: right">
										参加本次查房后，认为收获：
									</th>
									<td style="text-align: left">
										<label>
											<input type="radio" name="shouHuo" class="autoValue validate[required]"
												   <c:if test="${gradeMap['shouHuo'] eq '很大'}">checked</c:if>
												   value="很大"
											/> 很大
										</label>
										<label>
											<input type="radio" name="shouHuo" class="autoValue validate[required]"
												   <c:if test="${gradeMap['shouHuo'] eq '有收获'}">checked</c:if>
												   value="有收获"
											/> 有收获
										</label>
										<label>
											<input type="radio" name="shouHuo" class="autoValue validate[required]"
												   <c:if test="${gradeMap['shouHuo'] eq '有点收获'}">checked</c:if>
												   value="有点收获"
											/> 有点收获
										</label>
										<label>
											<input type="radio" name="shouHuo" class="autoValue validate[required]"
												   <c:if test="${gradeMap['shouHuo'] eq '收获不大'}">checked</c:if>
												   value="收获不大"
											/> 收获不大
										</label>
									</td>
								</tr>
							</table>
						</c:if>
						<table class="xllist nofix"  style="margin-top: 10px;">
							<tr>
								<th style="text-align: left">
									&#12288;意见或建议：
								</th>
							</tr>
							<tr>
								<td>
									<textarea class="validate[required,maxSize[500]] xltxtarea" name="evalContent">${eval.evalContent}</textarea>
								</td>
							</tr>
						</table>
						<div style="padding-left: 140px;padding-top: 5px;">总分：<input style="width:200px;" type="text" name="totalScore" id="totalScore" class="inputText" value="${empty gradeMap['totalScore']?0:gradeMap['totalScore']}" readonly="readonly"/></div>
					</form>
					
					<div style="text-align: center;margin-top: 5px;">
						<c:if test="${empty eval}">
							<input type="button" value="保&#12288;存" class="search" onclick="save();"/>
						</c:if>
						<input type="button" value="关&#12288;闭" class="search" onclick="jboxClose();"/>
					</div>
				</div> 	
			</div>
		</div>
	</div>
</body>
</html>