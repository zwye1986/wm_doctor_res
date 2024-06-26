<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true" />
	<jsp:param name="jbox" value="true" />
	<jsp:param name="jquery_validation" value="true" />
	<jsp:param name="jquery_datePicker" value="true" />
</jsp:include>
<style type="text/css">
	.xllist td{
		text-align: left;height: 35px;
	}
</style>
<script type="text/javascript">
	function save() {
		if(false==$("#editForm").validationEngine("validate")){
			return false;
		}

		var url = "<s:url value='/res/deptActivity/checkPlan'/>";
		jboxStartLoading();
		jboxPost(url, $("#editForm").serialize(), function(resp) {
			if('${GlobalConstant.OPRE_SUCCESSED_FLAG}'==resp){
				var url = "<s:url value='/res/deptActivity/newPlan'/>?"+$("#editForm").serialize();
				var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
				top.jboxMessager(iframe,"科室活动维护", 900, 500, null, false);
				jboxClose();
			}
			if('${GlobalConstant.OPRE_FAIL_FLAG}'==resp){
				jboxTip("${GlobalConstant.OPRE_FAIL}");
			}
			if('${GlobalConstant.OPRE_FAIL_FLAG}'!=resp&&'${GlobalConstant.OPRE_SUCCESSED_FLAG}'!=resp)
			{
				jboxTip(resp);
			}
		}, null, false);
	}
</script>
</head>
<body>
<div class="infoAudit">
	<form id="editForm" style="position: relative;padding-top: 20px;" method="post">
		<div id="infoDiv" class="div_table" style="padding-top: 5px;">
			<table class="xllist">
				<tr>
					<td style="text-align: right">科&#12288;&#12288;室：</td>
					<td>
						&#12288;<select id="deptFlow" name="deptFlow" class="qselect validate[required]">
							<option value="">请选择</option>
							<c:forEach items="${deptList}" var="dept">
								<option value="${dept.deptFlow}" >${dept.deptName}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td style="text-align: right">科室类型：</td>
					<td>
						&#12288;<select id="planTypeId" name="planTypeId" class="qselect validate[required]">
							<option value="">请选择</option>
							<c:forEach var="dict" items="${dictTypeEnumDeptActivityTypeList}">
								<option value="${dict.dictId}">${dict.dictName}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td style="text-align: right">月&#12288;&#12288;度：</td>
					<td>
						&#12288;<input type="text"  onclick="WdatePicker({dateFmt:'yyyy-MM'})" name="planDate" class="qtext" readonly="readonly"/>
					</td>
				</tr>
			</table>
			<div class="button">
				<input type="button" class="search" onclick="save();" value="确&#12288;认"/>&#12288;
				<input type="button" class="search" onclick="jboxClose();" value="关&#12288;闭"/>
			</div>
		</div>
	</form>
</div>
</body>
</html>