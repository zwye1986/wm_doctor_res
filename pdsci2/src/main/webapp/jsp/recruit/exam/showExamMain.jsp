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
						<select id="cfgFlow" name="cfgFlow" disabled style="width:150px;" class="validate[required] xlselect" >
							<option/>
							<c:forEach items="${cfgs}" var="cfg">
								<option value="${cfg.cfgFlow}" ${main.cfgFlow eq cfg.cfgFlow?'selected':''}>${cfg.recruitYear}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td style="text-align:right;line-height:18px;">开始日期：</td>
					<td>
						${main.startTime}
					</td>
				</tr>
				<tr>
					<td style="text-align:right;line-height:18px;">结束日期：</td>
					<td>
						 ${main.endTime}
					</td>
				</tr>
			</table>
			<div style="text-align: center;margin-top:20px;">
				<input type="button" class="search" onclick="jboxClose();" value="取&#12288;消"/>
			</div>
		</form>
	</div>
</div>
</body>
</html>