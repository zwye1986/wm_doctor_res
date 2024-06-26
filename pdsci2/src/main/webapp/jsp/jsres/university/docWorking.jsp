<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<script type="text/javascript">
		function search(){
			jboxPost("<s:url value='/jsres/base/uni/docWorking'/>",$("#searchForm").serialize(),function(resp){
				$('#content').html(resp);
			},null,false);
		}
		function toPage(page){
			var page = page||"${param.currentPage}";
			$("#currentPage").val(page);
			search();
		}
		$(function(){
			$('#sessionNumber,#graduationYear').datepicker({
				startView: 2,
				maxViewMode: 2,
				minViewMode:2,
				format:'yyyy'
			});
			changeTrainSpes();
		});
		function changeTrainSpes(){
			//清空原来专业！！！
			var trainCategoryid=$("#trainingTypeId").val();
			if(trainCategoryid==""){
				$("select[name=trainingSpeId] option[value != '']").remove();
				return false;
			}
			$("select[name=trainingSpeId] option[value != '']").remove();
			$("#"+trainCategoryid+"_select").find("option").each(function(){
				$(this).clone().appendTo($("#trainingSpeId"));
			});
			return false;
		}
		function detailSearch(userFlow){
			var url = "<s:url value='/jsres/base/docWorkDetailSearch'/>?userFlow="+userFlow;
			jboxOpen(url, "详情", 960, 600, true);
		}
	</script>
</head>
<body>
<div class="main_hd">
	<h2 class="underline">
		医师工作量查询
	</h2>
</div>
<div class="div_table">
	<div class="div_search">
		<form id="searchForm">
			<input id="currentPage" type="hidden" name="currentPage"  value=""/>
			<table style="width: 100%">
				<tr>
					<td>
						姓&#12288;&#12288;名：<input type="text" style="width: 100px;" class="input" value="${param.docName}" name="docName">
					</td>
					<td>
						年&#12288;&#12288;级：<input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" class="input" onchange="changeSpes()" readonly="readonly" style="width: 100px;"/>
					</td>
					<td>培&nbsp;&nbsp;训&nbsp;&nbsp;类&nbsp;&nbsp;别：&nbsp;
						<select name="trainingTypeId" id="trainingTypeId" class="select" onchange="changeTrainSpes()" style="width:106px;">
							<option value="">请选择</option>
							<c:forEach items="${trainCategoryEnumList}" var="trainCategory">
								<option value="${trainCategory.id}" <c:if test="${trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
							</c:forEach>
						</select>
					</td>
					<td>
						培训专业：
						<select name="trainingSpeId" id="trainingSpeId"class="select" style="width: 106px;">
							<option value="">全部</option>
						</select>
					</td>
				</tr>
					<td>
						证件号码：<input type="text" style="width: 100px;" class="input" value="${param.cardNo}" name="cardNo">
					</td>
					<td>
						培训基地：
						<select class="select" style="width: 106px" name="orgFlow" id="orgFlow" onchange="changeTrainSpes()">
							<option></option>
							<c:forEach items="${orgs}" var="org" varStatus="status">
								<option value="${org.orgFlow}" <c:if test="${param.orgFlow eq org.orgFlow}">selected</c:if>>${org.orgName}</option>
							</c:forEach>
						</select>
					</td>
					<td>
						结业考核年份：<input type="text" id="graduationYear" name="graduationYear" value="${param.graduationYear}" class="input" readonly="readonly" style="width: 100px;"/>
					</td>
					<td>
						<input type="button" class="btn_green" value="查&#12288;询" onclick="toPage(1);"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div>
		<table class="grid" width="100%">
			<tr>
				<th>姓名</th>
				<th>专业</th>
				<th>年级</th>
				<th>培训数据<br>要求数/完成数/审核数</th>
				<th>轮转科室<br>要求数/已轮转/已出科</th>
				<th>详细信息</th>
			</tr>
			<c:forEach items="${rltLst}" var="obj">
				<tr>
					<td>${obj.doctorName}</td>
					<td>${obj.trainingSpeName}</td>
					<td>${obj.sessionNumber}</td>
					<td>${obj.reqNum}/${obj.completeNum}/${obj.auditNum}</td>
					<td>${obj.rotationNum}/${obj.ylz}/${obj.schNum}</td>
					<td><a onclick="detailSearch('${obj.doctorFlow}');" class="btn">查看</a></td>
				</tr>
			</c:forEach>
			<c:if test="${empty rltLst}">
				<tr>
					<td colspan="6">无数据记录！</td>
				</tr>
			</c:if>
		</table>
	</div>
</div>
<div class="page" style="padding-right: 40px;">
	<c:set var="pageView" value="${pdfn:getPageView(rltLst)}" scope="request"></c:set>
	<pd:pagination-jsres toPage="toPage"/>
</div>
<div style="display: none;">
	<select id="WMFirst_select">
		<c:forEach items="${dictTypeEnumWMFirstList}" var="dict">
			<option value="${dict.dictId}"<c:if test="${param.trainingSpeId==dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
		</c:forEach>
	</select>
	<select id="WMSecond_select">
		<c:forEach items="${dictTypeEnumWMSecondList}" var="dict">
			<option value="${dict.dictId}"<c:if test="${param.trainingSpeId==dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
		</c:forEach>
	</select>
	<select id="AssiGeneral_select">
		<c:forEach items="${dictTypeEnumAssiGeneralList}" var="dict">
			<option value="${dict.dictId}"<c:if test="${param.trainingSpeId==dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
		</c:forEach>
	</select>
	<select id="DoctorTrainingSpe_select">
		<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
			<option value="${dict.dictId}"<c:if test="${param.trainingSpeId==dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
		</c:forEach>
	</select>

</div>
</body>
</html>