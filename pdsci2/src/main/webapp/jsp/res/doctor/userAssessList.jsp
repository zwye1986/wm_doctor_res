
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>

<style type="text/css">
	#tags li{cursor: pointer;}
</style>

<script type="text/javascript">
	function toPage(page) {
		if(page){
			$("#currentPage").val(page);
		}
		search();
	}

	function search(){
		jboxPost("<s:url value='/res/doc/userAssessList'/>?roleFlag=${param.roleFlag}",$("#searchUserAccess").serialize(),function(resp){
			$("#tagContent").html(resp);
		},null,false);
	}
</script>
</head>
<body>
<div class="mainright">
	<div class="content" style="height: 100%;">
		<div class="title1 clearfix">
			<form id="searchUserAccess">
				<input id="currentPage" type="hidden" name="currentPage" value=""/>

				<c:if test="${param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR}">
					<a style="float: right;margin-right: 10px;color: blue;" href="http://www.gfhpx.com" target="_blank">执业医师考试</a>
				</c:if>
				<div class="queryDiv">
					<div class="inputDiv">
						<label class="qlable">考试类型：</label>
						<select name="testTypeId" class="qselect">
							<option value="">全部</option>
							<c:forEach items="${dictTypeEnumTestTypeList}" var="dict">
								<option value="${dict.dictId}" ${param.testTypeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
							</c:forEach>
						</select>
					</div>
					<div class="inputDiv">
						<label class="qlable">考卷名称：</label>
						<input type="text"  class="qtext" name="paperName" value="${param.paperName}" />
					</div>
					<div class="inputDiv">
						<label class="qlable">是否合格：</label>
						<select name="passFlag" class="qselect">
							<option value="">全部</option>
							<option value="${GlobalConstant.FLAG_Y}" ${param.passFlag eq GlobalConstant.FLAG_Y?'selected':''}>是</option>
							<option value="${GlobalConstant.FLAG_N}" ${param.passFlag eq GlobalConstant.FLAG_N?'selected':''}>否</option>
						</select>
					</div>

					<c:if test="${!(param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR)}">
					<div class="inputDiv">
						<label class="qlable">姓&#12288;&#12288;名：</label>
						<select name="userFlow" class="qselect">
							<option value="">全部</option>
							<c:forEach items="${userList}" var="user">
								<option value="${user.userFlow}" <c:if test="${param.userFlow eq user.userFlow}">selected</c:if>>${user.userName}</option>
							</c:forEach>
						</select>
					</div>
					</c:if>
					<div class="qcheckboxDiv">
						&#12288;&#12288;<input type="button" value="查&#12288;询" class="searchInput" onclick="search();"/>
					</div>
				</div>
			</form>
		</div>
		
		<div class="resultDiv">
			<table class="xllist" style="width: 100%;">
				<tr>
					<th style="width: 15%;">考试类型</th>
					<th style="width: 25%;">考卷名称</th>
					<th style="width: 10%;">准考证号</th>
					<th style="width: 15%;">考试时间</th>
					<th style="width: 10%;">考试成绩</th>
					<th style="width: 10%;">是否合格</th>
					<th style="width: 15%;">备注</th>
				</tr>
				<c:forEach items="${resultList}" var="result">
					<tr>
						<td>${result.testTypeName}</td>
						<td>${result.paperName}</td>
						<td>${result.ticketNumber}</td>
						<td>${pdfn:transDate(result.testTime)}</td>
						<td>${result.totleScore}</td>
						<td>
							<c:if test="${result.passFlag eq GlobalConstant.FLAG_Y}">是</c:if>
							<c:if test="${result.passFlag eq GlobalConstant.FLAG_N}">否</c:if>
						</td>
						<td>${result.remark}</td>
					</tr>
				</c:forEach>
				<c:if test="${!(param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR)}">
					<c:if test="${empty param.userFlow}">
						<tr><td colspan="7">请选择学员！</td></tr>
					</c:if>
					<c:if test="${!empty param.userFlow && empty resultList}">
						<tr><td colspan="7">无记录</td></tr>
					</c:if>
				</c:if>
				<c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR}">
					<c:if test="${empty resultList}">
						<tr><td colspan="7">无记录</td></tr>
					</c:if>
				</c:if>
			</table>

		</div>
		<div>
			<c:set var="pageView" value="${pdfn:getPageView(resultList)}" scope="request"></c:set>
			<pd:pagination toPage="toPage"/>
		</div>
	</div>
</div>
</body>
</html>