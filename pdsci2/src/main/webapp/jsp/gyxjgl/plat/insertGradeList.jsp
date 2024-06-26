<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_form" value="true"/>
		<jsp:param name="jquery_ui_tooltip" value="true"/>
		<jsp:param name="jquery_cxselect" value="true"/>
		<jsp:param name="jquery_validation" value="true"/>
		<jsp:param name="jquery_datePicker" value="true"/>
		<jsp:param name="jquery_fixedtableheader" value="true"/>
		<jsp:param name="jquery_placeholder" value="true"/>
	</jsp:include>
	<script type="text/javascript">
		//查询
		function search(){
			jboxStartLoading();
			$("#searchForm").submit();
		}
		//分页
		function toPage(page) {
			if(page){
				$("#currentPage").val(page);
			}
			search();
		}
		function addInfo(stuNo){
			var url =  "<s:url value='/gyxjgl/user/editInsertGrade'/>?stuNo="+stuNo;
			jboxOpen(url ,stuNo==""?"新增":"编辑",760,660);
		}
		function printFlag(stuNo){
			var url = '<s:url value="/gyxjgl/user/printInsertGrade"/>?stuNo='+stuNo;
			jboxStartLoading();
			jboxPost(url, null, function(data) {
				$("#printDiv").html(data);
				setTimeout(function(){
					var newstr = $("#printDiv").html();
					var oldstr = document.body.innerHTML;
					document.body.innerHTML =newstr;
					window.print();
					document.body.innerHTML = oldstr;
					jboxEndLoading();
					return false;
				},3000);
			},null,false);
		}
	</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<form id="searchForm" action="<s:url value="/gyxjgl/user/insertStuGrade"/>" method="post">
			<table class="basic" style="width: 100%;margin: 10px 0px 5px -20px;border: none;">
				<tr>
					<td style="border: none;">
						<input id="currentPage" type="hidden" name="currentPage" value=""/>
						<div>&#12288;
							学&#12288;&#12288;号：<input type="text" style="width: 137px;" name="stuNo" value="${param.stuNo}" >
							&#12288;姓&#12288;&#12288;名：<input type="text" style="width: 137px;" name="userName" value="${param.userName }">
							<input type="button" class="search" onclick="search();" value="查&#12288;询" />
							<input type="button" name="" class="search" onclick="addInfo('');" value="新&#12288;增"/>
						</div>
					</td>
				</tr>
			</table>
		</form>
		<table class="xllist" style="width:100%; margin-top: 5px;">
			<tr>
				<th>学号</th>
				<th>姓名</th>
				<th>培养类型</th>
				<th>学位类别</th>
				<th>专业</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${gradeList}" var="record">
			<tr>
				<td>${record.stuNo}</td>
				<td>${record.userName}</td>
				<td>${record.trainType}</td>
				<td>${record.degreeType}</td>
				<td>${record.major}</td>
				<td>
					<a onclick="addInfo('${record.stuNo}');" style="cursor: pointer;color: blue;">编辑</a>
					<a onclick="printFlag('${record.stuNo}');" style="cursor: pointer;color: blue;">打印</a>
				</td>
			</tr>
			</c:forEach>
			<c:if test="${empty gradeList}">
				<tr><td colspan="99">无记录</td></tr>
			</c:if>
		</table>
		<div>
			<c:set var="pageView" value="${pdfn:getPageView(gradeList)}" scope="request"/>
			<pd:pagination toPage="toPage"/>
		</div>
	</div>
</div>
<div id="printDiv" style="display: none;"></div>
</body>
</html>