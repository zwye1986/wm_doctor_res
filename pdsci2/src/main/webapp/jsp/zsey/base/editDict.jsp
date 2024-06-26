<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
</jsp:include>
<script type="text/javascript">
	function doSave() {
		if(false==$("#sysDictForm").validationEngine("validate")){
			return ;
		}
		var dictTypeText = $("select[name='dictTypeId'] option:selected").text();
		$("input[name='dictTypeName']").val(dictTypeText);
		var url = "<s:url value='/zsey/base/save'/>";
		jboxPost(url, $('#sysDictForm').serialize(), function(resp) {
			if(resp=='${GlobalConstant.OPRE_FAIL_FLAG}'){
				jboxTip("字典代码重复！");
			}else{
				jboxTip('${GlobalConstant.SAVE_SUCCESSED}');
				window.parent.frames['mainIframe'].window.search();
				jboxClose();
			}
		},null,false);
	}
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<form id="sysDictForm">
			<input name="dictFlow" value='${param.dictFlow}' type="hidden"/>
			<table class="basic" style="width: 100%;margin: 10px 0px;">
				<tr>
					<td style="text-align:right;">字典类型：</td>
					<td>
						<select class="validate[required] select" style="width:156px;" name="dictTypeId">
							<option value="">请选择</option>
							<c:forEach var="dictTypeEnum" items="${dictTypeEnumList}">
								<c:if test="${dictTypeEnum.wsid eq sessionScope.currWsId}">
									<option value="${dictTypeEnum.id}" ${dict.dictTypeId eq dictTypeEnum.id?'selected':''}>${dictTypeEnum.name}</option>
								</c:if>
							</c:forEach>
						</select>
						<input type="hidden" name="dictTypeName" value="${dict.dictTypeName}">
					</td>
				</tr>
				<tr>
					<td style="text-align:right;">字典代码：</td>
					<td><input class="validate[required]" name="dictId" type="text" value="${dict.dictId}" /></td>
				</tr>
				<tr>
					<td style="text-align:right;">字典名称：</td>
					<td><input class="validate[required]" name="dictName" type="text" value="${dict.dictName }" /></td>
				</tr>
				<tr>
					<td style="text-align:right;">代码描述：</td>
					<td><input class="validate[maxSize[50]]" name="dictDesc" type="text" value="${dict.dictDesc }" /></td>
				</tr>
				<tr>
					<td style="text-align:right;">排序码：</td>
					<td style="text-align:left;" width="350px"><input class="validate[required,custom[integer]]" name="ordinal" type="text" value="${dict.ordinal }" /></td>
				</tr>
			</table>
			<div class="button" style="width:100%;margin-top:20px;">
			<input class="search" type="button" value="保&#12288;存" onclick="doSave();" />
			<input type="button" class="search" onclick="jboxClose();" value="关&#12288;闭">
		</div>
		</form>
	</div>
</div>
</body>
</html>