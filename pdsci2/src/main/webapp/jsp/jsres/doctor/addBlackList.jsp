<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
		<jsp:param name="basic" value="true" />
		<jsp:param name="jbox" value="true" />
		<jsp:param name="font" value="true" />
		<jsp:param name="jquery_validation" value="true" />
	</jsp:include>
	<script type="text/javascript">
		function save() {
			if(false==$("#editForm").validationEngine("validate")){
				return ;
			}
			cretTypeId
			var cretTypeId = $("#cretTypeId").val();
			if (cretTypeId==''){
				top.jboxTip("请选择证件类型！");
			}
			var attachmentPath = $("#fileInput").val();
			var fileName = $("#fileInputName").val();
			var url = "<s:url value='/jsres/blackList/saveBlackList'/>?roleFlag=${param.roleFlag}&attachmentPath="+attachmentPath+"&fileName="+fileName;
			var data = $('#editForm').serialize();
			jboxPost(url, data, function(resp) {
				if(resp == "${GlobalConstant.OPERATE_SUCCESSED}"){
					top.jboxTip(resp);
					top.search();
					top.jboxCloseMessager();
				}else{
					top.jboxTip(resp);
				}
			}, null, false);
		}

		function uploadFile() {
			debugger;
			jboxOpen("<s:url value='/jsres/blackList/uploadFile'/>?roleFlag=local", "附件上传",500, 170);
		}
		function delFile() {
			$("#fileName").remove();
		}
	</script>
</head>

<body>
<div class="infoAudit">
	<div class="div_table" style="overflow: hidden;">
		<!-- <h4>编辑黑名单信息</h4> -->
		<form id="editForm" style="position: relative;" method="post">
			<input type="hidden" name="orgFlow" value="${sessionScope.currUser.orgFlow }"/>
			<table border="0" cellpadding="0" cellspacing="0" class="base_info">
				<colgroup>
					<col width="30%" />
					<col width="70%" />
				</colgroup>
				<tbody>
				<br/>
				<tr>
					<th><font color="red">*</font>姓名：</th>
					<td><input class="validate[required,minSize[1],maxSize[25]] input" name="userName" type="text"  /></td>
				</tr>
			<tr>
				<th style="text-align: right;width: 100px;"><font color="red">*</font>证件类型：</th>
				<td style="text-align: left;width: 500px;">
					<select class="select" id="cretTypeId" name="cretTypeId" class="txt validate[required]" style="width:160px;margin-left: 5px">
						<option value="">请选择</option>
						<c:forEach items="${certificateTypeEnumList}" var="cret">
							<option value="${cret.id}" ${param.cretTypeId eq cret.id?'selected':''}>${cret.name}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
				<tr>
					<th><font color="red">*</font>学员证件号：</th>
					<td><input class="validate[required] input " name="userIdNo" type="text"  /></td>
				</tr>
				<tr>
					<th>原因：</th>
					<td><textarea class="validate[required,minSize[1],maxSize[100]]" name="reason" type="text" rows="2" ></textarea></td>
				</tr>
				<tr>
					<th>附件：</th>
					<td>
						<a class="btn" style="width: 60px;" href="javascript:void(0);"   onclick="uploadFile()">上传附件</a>
						<a class="btn" style="width: 60px;" href="javascript:void(0);"   onclick="delFile()">删除附件</a>
						<p id="fileInfo"></p>
					</td>
				</tr>
				</tbody>
			</table>
		</form>

		<div class="button">
			<input type="button" class="btn_green" onclick="save();" value="保&#12288;存"/>
			<input type="button" class="btn_green" onclick="top.jboxCloseMessager();" value="关&#12288;闭"/>
		</div>
	</div>
</div>
</body>
</html>