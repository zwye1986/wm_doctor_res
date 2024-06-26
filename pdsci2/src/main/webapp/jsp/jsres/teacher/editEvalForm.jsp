<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="font" value="true"/>
		<jsp:param name="login" value="true"/>
		<jsp:param name="jquery_validation" value="true"/>
		<jsp:param name="jquery_datePicker" value="true"/>
		<jsp:param name="jquery_fixedtableheader" value="true"/>
		<jsp:param name="jquery_placeholder" value="true"/>
	</jsp:include>
<style type="text/css">
	#searchForm table td{border:1px solid #F4F5F9;text-align:left;padding-left:20px;}
	table input{width:80px;line-height:30px;text-align:center;}
</style>
<script type="text/javascript">
	$(function(){
		var forms = $("form");
		$.each(forms , function(i , form){
			$(form).validationEngine("attach",{
						promptPosition : "bottomLeft",
						scroll:true,
						autoPositionUpdate: true,
						autoHidePrompt:true,
						maxErrorsPerField:1,
						showOneMessage : true
					}
			);
		});
		<c:forEach items="${titleFormList}" var="title">
			var score = 0;
			$(".item_${title.id}").each(function(){
				score = accAdd(score,$(this).text())
			});
			$("#title_${title.id}").text(score);
		</c:forEach>
	});
	function accAdd(arg1,arg2){
		var r1,r2,m;
		try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
		try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
		m=Math.pow(10,Math.max(r1,r2))
		return (arg1*m+arg2*m)/m
	}
	<c:if test="${empty flag}">
		function changeScore(obj){
			var scoreCount=0;
			$("input[name='score']").each(function(){
				if ($(this).val() != null && $(this).val() != undefined && $(this).val() != '' && !isNaN($(this).val())){
					scoreCount=accAdd(scoreCount,$(this).val());
				}
			});
			$("input[name='evalScore']").val(scoreCount);
			$("#evalScore").text(scoreCount);
		}
		function validateNum(val){//验证小数，保留一位小数点
			var patten = /^-?\d+\.?\d{0,1}$/;
			return patten.test(val);
		}
    var isCommitted = false;
	function saveForm(){
			if (!$("#searchForm").validationEngine("validate")) {
				return;
			}
			if(!validateNum($("input[name='attendance']").val()))
			{
				jboxTip("出勤天数最多保留一位小数");
				return;
			};
			if(!validateNum($("input[name='leave']").val()))
			{
				jboxTip("请假天数最多保留一位小数");
				return;
			};
			if(!validateNum($("input[name='absenteeism']").val()))
			{
				jboxTip("缺勤天数最多保留一位小数");
				return;
			};

					<c:forEach items="${titleFormList}" var="title">
						<c:forEach items="${title.itemList}" var="item" varStatus="i">
							if(!validateNum($("input[id='${item.id}']").val()))
							{
								jboxTip("${item.name}最多保留一位小数");
								return;
							};
						</c:forEach>
					</c:forEach>
			var url = "<c:url value='/jsres/teacher/saveForm'/>";
			if(isCommitted==false){
				jboxPost(url,$("#searchForm").serialize(), function(data){
					isCommitted = true;//防止重复提交
					setTimeout(function(){
						// parent.stuEvaluation();
						parent.toPage("${param.page == 'undefined'? 1: param.page}");
						jboxClose();
					},1000);
				},null,true);
			}else{
                jboxTip("已保存。");
			}

		};
	</c:if>
</script>
</head>
<body>
	<div class="main_bd">
	<div style="margin:0px 20px 0px 20px;max-height: 550px;overflow: auto;">
		<c:if test="${empty flag}">
			<form id="searchForm">
				<input type="hidden" name="processFlow" value="${param.processFlow}" />
				<input type="hidden" name="startTime" value="${fn:split(param.timeArea,'~')[0]}" />
				<input type="hidden" name="endTime" value="${fn:split(param.timeArea,'~')[1]}" />
				<table class="grid">
					<tr>
						<th>考勤情况</th>
						<td colspan="3">
							出勤天数：<input type="text" class="validate[required,custom[number]] input" name="attendance"/>天<span style="margin-left:40px;"></span>
							请假天数：<input type="text" class="validate[required,custom[number]] input" name="leave"/>天<span style="margin-left:40px;"></span>
							缺勤天数：<input type="text" class="validate[required,custom[number]] input" name="absenteeism"/>天
						</td>
					</tr>
					<tr><td colspan="4" style="height:30px;"></td></tr>
					<c:forEach items="${titleFormList}" var="title">
						<c:forEach items="${title.itemList}" var="item" varStatus="i">
							<c:if test="${i.first}">
								<tr>
									<th rowspan="${fn:length(title.itemList)}">${title.name}（<span id="title_${title.id}"></span>分）</th>
									<td colspan="2">${item.name}（<span class="item_${title.id}">${item.score}</span>分）</td>
									<td style="width:24%;">得分：<input type="text" class="validate[required,custom[number],max[${item.score}]] input" id="${item.id}" name="score" onblur="changeScore()"/></td>
									<input type="hidden" name="scoreId" value="${item.id}" />
								</tr>
							</c:if>
							<c:if test="${!i.first}">
								<tr>
									<td colspan="2">${item.name}（<span class="item_${title.id}">${item.score}</span>分）</td>
									<td>得分：<input type="text" class="validate[required,custom[number],max[${item.score}]] input" id="${item.id}" name="score" onblur="changeScore()"/></td>
									<input type="hidden" name="scoreId" value="${item.id}" />
								</tr>
							</c:if>
						</c:forEach>
					</c:forEach>
					<c:if test="${fn:length(titleFormList) gt 0}">
						<input type="hidden" name="isForm" value="Y" />
						<input type="hidden" name="evalScore" />
						<tr>
							<th colspan="3">总得分</th>
							<th id="evalScore"></th>
						</tr>
						<tr><td colspan="4" style="height:30px;"></td></tr>
					</c:if>
					<tr>
						<th>考评意见</th>
						<td colspan="3" style="height:100px;"><textarea style="width:90%;height:80%;" class="validate[required]" name="evalContent"></textarea></td>
					</tr>
				</table>
			</form>
			<div style="text-align:center;margin-top:20px;">
				<input class="btn_green" id="saveFormButton" type="button" value="保存" onclick="saveForm()"/>&#12288;
				<input class="btn_green" type="button" value="关闭" onclick="jboxClose()"/>
			</div>
		</c:if>
		<c:if test="${flag eq 'view'}">
			<form id="searchForm">
				<table class="grid">
					<tr>
						<th>考勤情况</th>
						<td colspan="3">
							出勤天数：<input type="text" class="input" value="${eval.attendance}" readonly/>天<span style="margin-left:40px;"></span>
							请假天数：<input type="text" class="input" value="${eval.leave}" readonly/>天<span style="margin-left:40px;"></span>
							缺勤天数：<input type="text" class="input" value="${eval.absenteeism}" readonly/>天
						</td>
					</tr>
					<tr><td colspan="4" style="height:30px;"></td></tr>
					<c:forEach items="${titleFormList}" var="title">
						<c:forEach items="${title.itemList}" var="item" varStatus="i">
							<c:if test="${i.first}">
								<tr>
									<th rowspan="${fn:length(title.itemList)}">${title.name}（<span id="title_${title.id}"></span>分）</th>
									<td colspan="2">${item.name}（<span class="item_${title.id}">${item.score}</span>分）</td>
									<td style="width:24%;">得分：<input type="text" class="input" value="${scoreMap[item.id]}" readonly/></td>
								</tr>
							</c:if>
							<c:if test="${!i.first}">
								<tr>
									<td colspan="2">${item.name}（<span class="item_${title.id}">${item.score}</span>分）</td>
									<td>得分：<input type="text" class="input" value="${scoreMap[item.id]}" readonly/></td>
								</tr>
							</c:if>
						</c:forEach>
					</c:forEach>
					<c:if test="${fn:length(titleFormList) gt 0}">
						<tr>
							<th colspan="3">总得分</th>
							<th>${eval.evalScore}</th>
						</tr>
						<tr><td colspan="4" style="height:30px;"></td></tr>
					</c:if>
					<tr>
						<th>考评意见</th>
						<td colspan="3" style="height:100px;"><textarea style="width:90%;height:80%;" readonly>${eval.evalContent}</textarea></td>
					</tr>
				</table>
				<div style="text-align:center;margin-top:20px;">
					<input class="btn_green" type="button" value="关&#12288;闭" onclick="jboxClose()"/>
				</div>
			</form>
		</c:if>
	</div>
</div>
</body>
</html>