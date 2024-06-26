<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="login" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<style type="text/css">
	#boxHome .item:HOVER{background-color: #eee;}
</style>
<script type="text/javascript">
	function save(){
		if(false==$("#excelForm").validationEngine("validate")){
			return false;
		}
		var url = "<s:url value='/jsres/statistic/saveTeacher'/>";
		jboxStartLoading();
		jboxPost(url , $('#excelForm').serialize() , function(){
			window.parent.search();
			jboxClose();
		} , null , true);
	}
	function getOrgName() {
		$("#orgName").val($("#orgFlow option:selected").text());
	}
</script>
</head>
<body>
<input type="hidden" id="checkFileFlag" name="checkFileFlag"/>
<form id="excelForm" method="post" enctype="multipart/form-data">
	<table class="grid" style="width: 100%;margin-top: 15px">
		<input  type="text" name="recordFlow" id="recordFlow"  value="${teacher.recordFlow}" style="display: none;"/>
		<tr>
			<th width="150px">证书编号</th>
			<td  style="text-align: left;">
				<input  type="text" name="certificateNo" class="select" value="${teacher.certificateNo}" class="validate[required,custom[integer]]" style="text-align: left;width: 150px;"/>
			</td>
		</tr>
		<tr>
			<th width="150px">基地</th>
			<td  style="text-align: left;">
				<select name="orgFlow" id="orgFlow" class="select" style="width: 150px;" onchange="getOrgName()" >
					<option value="">全部</option>
					<c:forEach items="${orgs}" var="org">
						<option value="${org.orgFlow}"<c:if test="${(teacher.orgFlow==org.orgFlow) or (teacher.orgName==org.orgName) }">selected="selected"</c:if>>${org.orgName}</option>
					</c:forEach>
				</select>
				<input  type="text" name="orgName" id="orgName" class="validate[required]" value="${teacher.orgName}" style="text-align: left;display: none;width: 150px;"/>
			</td>
		</tr>
		<tr>
			<th width="150px">姓名</th>
			<td  style="text-align: left;">
				<input  type="text" name="doctorName" class="select"  class="validate[required]" value="${teacher.doctorName}" style="text-align: left;width: 150px;"/>
			</td>
		</tr>
		<tr>
			<th width="150px">性别</th>
			<td  style="text-align: left;">
				<select name="sexName" id="sexName" class="select" style="width: 150px;" >
					<option value=""></option>
						<option value="男"<c:if test="${teacher.sexName=='男' }">selected="selected"</c:if>>男</option>
						<option value="女"<c:if test="${teacher.sexName=='女' }">selected="selected"</c:if>>女</option>
				</select>
			</td>
		</tr>
		<tr>
			<th width="150px">技术职称</th>
			<td  style="text-align: left;">
				<input  type="text" name="technicalTitle" class="select"  class="validate[required]" value="${teacher.technicalTitle}" style="text-align: left;"/>
			</td>
		</tr>
	</table>
	<div style="text-align: center; margin-top: 10px;">
		<input type="button" onclick="save();" class="btn_green" value="保&#12288;存"/>
		<input type="button" class="btn_green" value="关&#12288;闭" onclick="jboxClose();"/>
	</div>
</form>
