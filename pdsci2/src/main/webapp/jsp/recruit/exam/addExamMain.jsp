<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_form" value="true"/>
		<jsp:param name="jquery_validation" value="true"/>
		<jsp:param name="jquery_datePicker" value="true"/>
	</jsp:include>
	<script type="text/javascript">
		function save(){
			if (!$("#myForm").validationEngine("validate")) {
				return;
			}
			var recruitEndDate=$("#cfgFlow").find("option:selected").attr("recruitEndDate")||"";

			var startDate = $("#startTime").val();
			var endDate = $("#endTime").val();
			if(startDate<recruitEndDate)
			{
				jboxTip("开始时间不能小于招录结束时间【"+recruitEndDate+"】");
				return;
			}
			if(startDate>endDate){
				jboxTip("开始时间不能小于结束时间");
				return;
			}
			var url = "<s:url value='/recruit/examMain/saveExamMain'/>";
			jboxPost(url, $("#myForm").serialize(), function (resp) {//form提交，jsonData2已存在，切记重复传值

				if(resp=="${ GlobalConstant.SAVE_SUCCESSED}")
				{
					setTimeout(function(){
						window.parent.frames['mainIframe'].toPage(1);
						jboxClose();
					},500);
				}
			}, null,true);
		}
		$(function(){
			changeMinDate();
		});
		function setMinDate(recruitEndDate)
		{
			var recruitEndDate=$("#cfgFlow").find("option:selected").attr("recruitEndDate")||"";
			recruitEndDate=recruitEndDate||"";
			console.log(recruitEndDate);
			$("#startTime").unbind("click");
			$("#startTime").click(function (){

			});

		}
		function changeMinDate()
		{
			var recruitEndDate=$("#cfgFlow").find("option:selected").attr("recruitEndDate")||"";
			console.log(recruitEndDate);

			var startDate = $("#startTime").val();
			var endDate = $("#endTime").val();
			if(startDate<recruitEndDate)
			{
				$("#startTime").val("");
			}
			if(endDate<recruitEndDate)
			{
				$("#endTime").val("");
			}
			$("#recruitEndDate").val(recruitEndDate);
		}
	</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<form id="myForm">
			<input type="hidden" name="mainFlow" value="${main.mainFlow}">
			<table class="basic" style="width: 100%;margin: 10px 0px;">
				<tr>
					<td style="text-align:right;width:30%;">招录年份：</td>
					<td>
						<select id="cfgFlow" name="cfgFlow" style="width:150px;" onchange="changeMinDate()" class="validate[required] xlselect" >
							<option/>
							<c:forEach items="${cfgs}" var="cfg">
								<option value="${cfg.cfgFlow}" recruitEndDate="${cfg.recruitEndDate}" ${main.cfgFlow eq cfg.cfgFlow?'selected':''}>${cfg.recruitYear}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td style="text-align:right;line-height:18px;">开始日期：</td>
					<td>
						<input hidden id="recruitEndDate">
						<input class="validate[required] qtext" id="startTime" name="startTime" type="text" class="qtext" value="${main.startTime}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'recruitEndDate\')}',maxDate: '#F{$dp.$D(\'endTime\')}'})"   readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td style="text-align:right;line-height:18px;">结束日期：</td>
					<td>
						<input class="validate[required] qtext" id="endTime" name="endTime" type="text" class="qtext" value="${main.endTime}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate: '#F{$dp.$D(\'startTime\')}'})" readonly="readonly"/>
					</td>
				</tr>
			</table>
			<div style="text-align: center;margin-top:20px;">
				<input type="button" class="search" onclick="save();" value="保&#12288;存"/>
				<input type="button" class="search" onclick="jboxClose();" value="取&#12288;消"/>
			</div>
		</form>
	</div>
</div>
</body>
</html>