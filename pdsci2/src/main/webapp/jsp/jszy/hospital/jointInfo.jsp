<jsp:include page="/jsp/jszy/htmlhead-jszy.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<style type="text/css">
	input[type='text']{
		text-align: center;
		border-bottom: 1px solid lightgrey;
	}
</style>
<div style="width:100%;height:100%;overflow-y:auto;">
<form id="myForm">
	<table class="grid" style="margin-top:10px;">
		<input type="hidden" name="recordFlow" value="${param.recordFlow}">
		<input type="hidden" name="unitTypeId" value="${param.unitTypeId}">
		<input type="hidden" name="jsonStr" id="deptList">
		<tr>
			<th style="text-align:right;width:20%;">单位名称：</th>
			<td style="width:30%;">
				<c:if test="${param.viewFlag eq 'view'}">${empty unitInfo.unitName?org.orgName:unitInfo.unitName}</c:if>
				<c:if test="${param.viewFlag ne 'view'}">
					<input type="text" name="unitName" class="validate[required]" value="${empty unitInfo.unitName?org.orgName:unitInfo.unitName}">
				</c:if>
			</td>
			<th style="text-align:right;width:20%;">单位地址：</th>
			<td style="width:30%;">
				<c:if test="${param.viewFlag eq 'view'}">${unitInfo.unitAddress}</c:if>
				<c:if test="${param.viewFlag ne 'view'}">
					<input type="text" name="unitAddress" class="validate[required]" value="${unitInfo.unitAddress}">
				</c:if>
			</td>
		</tr>
		<tr>
			<th style="text-align:right;">单位负责人：</th>
			<td>
				<c:if test="${param.viewFlag eq 'view'}">${unitInfo.unitLeader}</c:if>
				<c:if test="${param.viewFlag ne 'view'}">
					<input type="text" name="unitLeader" class="validate[required]" value="${unitInfo.unitLeader}">
				</c:if>
			</td>
			<th style="text-align:right;">联系电话：</th>
			<td>
				<c:if test="${param.viewFlag eq 'view'}">${unitInfo.linkPhone}</c:if>
				<c:if test="${param.viewFlag ne 'view'}">
					<input type="text" name="linkPhone" class="validate[required]" value="${unitInfo.linkPhone}">
				</c:if>
			</td>
		</tr>
		<%--协同基地--%>
		<c:if test="${param.unitTypeId eq '1'}">
			<tr>
				<th>协同科室名称</th>
				<th>床位数（张）</th>
				<th colspan="2">带教师资数（人）<c:if test="${param.viewFlag ne 'view'}"><img style='cursor:pointer;float:right;' src='<s:url value='/jsp/inx/lcjn/images/add.png'/>' <c:if test="${param.viewFlag ne 'view'}">onclick='bindDept()'</c:if>></c:if></th>
			</tr>
			<tbody class="deptTB">
			<c:if test="${param.viewFlag eq 'view'}">
				<c:forEach items="${deptList}" var="dept">
					<tr>
						<td>${dept.deptName}</td>
						<td>${dept.bedNum}</td>
						<td colspan="2">${dept.teaNum}</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${param.viewFlag ne 'view'}">
				<c:if test="${empty deptList}">
					<tr>
						<td><input type="text" class="validate[required]"></td>
						<td><input type="text" class="validate[required,custom[integer]]"></td>
						<td colspan="2"><input type="text" class="validate[required,custom[integer]]"><img style='cursor:pointer;float:right;' src='<s:url value='/jsp/inx/lcjn/images/reduce.png'/>' onclick='delTr(this)'></td>
					</tr>
				</c:if>
				<c:forEach items="${deptList}" var="dept">
					<tr>
						<td><input type="text" class="validate[required]" value="${dept.deptName}"></td>
						<td><input type="text" class="validate[required,custom[integer]]" value="${dept.bedNum}"></td>
						<td colspan="2"><input type="text" class="validate[required,custom[integer]]" value="${dept.teaNum}"><img style='cursor:pointer;float:right;' src='<s:url value='/jsp/inx/lcjn/images/reduce.png'/>' onclick='delTr(this)'></td>
					</tr>
				</c:forEach>
			</c:if>
			</tbody>
		</c:if>
	</table>
</form>
<div style="text-align:center;margin-top:20px;">
	<c:if test="${param.viewFlag ne 'view'}">
		<input class="btn_brown" type="button" onclick="save()" value="保&#12288;存"/>
	</c:if>
	<c:if test="${param.viewFlag eq 'view'}">
		<input class="btn_brown" type="button" onclick="jboxClose()" value="关&#12288;闭"/>
	</c:if>
</div>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		var forms = $("form");
		$.each(forms , function(i , form){
			$(form).validationEngine("attach",{
						promptPosition : "bottomLeft",
						scroll:true,
						autoPositionUpdate: true,
						//addPromptClass:"formError-noArrow formError-text"
						autoHidePrompt:true,
						maxErrorsPerField:1,
						showOneMessage : true
					}
			);
		});
	});
	function save(){
		if(!$("#myForm").validationEngine("validate")){
			return false;
		}
		<c:if test="${param.unitTypeId eq '1'}">
			var deptList = [];
			$(".deptTB tr").each(function(i){
				var obj = {
					"deptName" : $(this).find("input:eq(0)").val(),
					"bedNum" : $(this).find("input:eq(1)").val(),
					"teaNum" : $(this).find("input:eq(2)").val()
				};
				deptList.push(obj);
			});
			$("#deptList").val(JSON.stringify(deptList));
		</c:if>
		var form = $("#myForm").serialize();
		jboxPost("<s:url value='/jszy/base/saveJointInfo'/>", form, function () {
//			parent.baseInfoManage();
			jboxClose();
		});
	}
	function bindDept(){
		var i = $(".deptTB tr").length;
		var html = "<tr><td><input type='text' class='validate[required]'></td><td><input type='text' class='validate[required,custom[integer]]'></td><td colspan='2'><input type='text' class='validate[required,custom[integer]]'><img style='cursor:pointer;float:right;' src='<s:url value='/jsp/inx/lcjn/images/reduce.png'/>' onclick='delTr(this)'></td></tr>";
		$(".deptTB").append(html);
	}
	function delTr(obj){
		$(obj).parents("tr").remove();
	}
</script>