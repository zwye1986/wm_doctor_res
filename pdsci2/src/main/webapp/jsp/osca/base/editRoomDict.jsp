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
		var url = "<s:url value='/osca/base/save'/>";
		var data = $('#sysDictForm').serialize();
		jboxPost(url, data, function(resp) {
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
<form id="sysDictForm" style="height: 100px;" >
<input name="dictFlow" value='${param.dictFlow}' type="hidden"/>
<div class="content">
	<div class="title1 clearfix">
		<div id="tagContent">
			<div class="tagContent selectTag" id="tagContent0">
				<table style="width: 400px" cellpadding="0" cellspacing="0" class="basic">
					<tr>
						<th width="100px">字典类型：</th>
						<td width="200px">
							<select class="validate[required] xlselect" name="dictTypeId">
								<option value="">请选择</option>
								<option value="ExamRoom" <c:if test="${dict.dictTypeId eq 'ExamRoom' or 'ExamRoom' eq param.dictTypeId}">selected="selected"</c:if>>临床技能考核.考场</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>字典代码：</th>
						<td>
							<input class="validate[required] xltext" name="dictId" type="text"
							value="${dict.dictId}" /></td>
					</tr>
					<tr>
						<th>字典名称：</th>
						<td
							><input class="validate[required,maxSize[50]] xltext" name="dictName" type="text"
							value="${dict.dictName }" /></td>
					</tr>
					<tr>
						<th>代码描述：</th>
						<td
							><input class="xltext validate[maxSize[50]]" name="dictDesc" type="text"
							value="${dict.dictDesc }" /></td>
					</tr>
					<tr>
						<th >排序码：</th>
						<td style="text-align: left; " width="350px"
							><input class="validate[required,custom[integer]] xltext" name="ordinal" type="text"
							value="${dict.ordinal }" /></td>
					</tr>
				</table>
				<div class="button" style="width: 100%">
					<input class="search" type="button" value="保&#12288;存" onclick="doSave();" />
					<input type="button" class="search" onclick="jboxClose();" value="关&#12288;闭">
				</div>
			</div>
		</div>
	</div>
</div>
</form>
</body>
</html>