<%@include file="/jsp/common/doctype.jsp" %>
<head>
	<title>${sysCfgMap['sys_title_name']}</title>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_form" value="true"/>
		<jsp:param name="jquery_ui_tooltip" value="true"/>
		<jsp:param name="jquery_datePicker" value="true"/>
		<jsp:param name="jquery_validation" value="true"/>
		<jsp:param name="slideRight" value="true"/>
	</jsp:include>
	<style type="text/css">
		.module-tabs{ height:49px; line-height:49px; font-size:15px; color:6e6e6e; border-bottom:1px solid #dadada}
		.module-tabs li{ float:left;  cursor:pointer;display:block;}
		.module-tabs .type li{padding:0 15px;}
		.module-tabs li.on{ height:48px; border-bottom:2px solid blue; color:blue;}
		.module-tabs li a{padding:8px 15px;}
		.module-tabs li a.hove1{ height:48px; color:#fff; padding:8px 15px;  background:blue;}
		.module-tabs li a:hover{color:#000;text-decoration:none;}
		.fl{ float:left;}
		.module-content{ margin-top:10px;}
		.module-content ul li{ display:none;}
		.module-content ul li dl{ margin-left:20px; width:368px;}
		.module-content ul li{background:#fff; overflow:hidden; border:1px solid #dfdfdf; position:relative; margin-bottom:10px;}
		.module-content ul li dt{ line-height:40px; color:#000; font-size:15px; margin-bottom:12px;}
		.module-content li dd p{ color:#9e9e9e; line-height:25px;}
		.module-content dd div{ line-height:30px;}
		.module-content dd div img{ vertical-align:middle;}
		.module-content .btn{ position:absolute; right:10px; bottom:10px; height:30px; padding:0 24px;}
		.module-content dt .fr{margin-right:10px;}
		.module-content .lessonimg{border:4px solid #fff;cursor:default;}
		.module-content .graph{width:142px;background:#dadada;vertical-align:middle;height:11px;border-radius:5px;display:inline-block;}
		table.basic th,table.basic td{text-align: center;padding: 0;}
	</style>
	<script type="text/javascript">

		function gradeSearch(page) {
			var startDate = $("#operStartDate").val();
			var endDate = $("#operEndDate").val();
			if(startDate && endDate && startDate>endDate){
				return jboxTip("开始时间必须小于等于结束时间！");
			}
			if (page != undefined) {
				$("#currentPage").val(page);
			}
			$('#gradeSearchForm').submit();
		}

		function operDetail(name,deptFlow){
			var startDate = $("#operStartDate").val() || "";
			var endDate = $("#operEndDate").val() || "";
			var url = "<s:url value='/thres/head/thGradeSearchAllDetail'/>?gradeRole=${param.gradeRole}&deptFlow="+deptFlow+"&operStartDate="+startDate+"&operEndDate="+endDate;
			jboxOpen(url,name+"分析汇总",800,500);
		}
	</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div class="top-tab">
			<div class="clearfix" style="padding-top: 10px;">
				<div id="tagContent">
					<div class="content">
						<div class="module-content" style="width: 100%;">
							<form id="gradeSearchForm" action="<s:url value="/thres/head/thGradeSearchAll"/>" method="post">
								<input type="hidden" id="currentPage" name="currentPage">
								<input type="hidden" id="gradeRole" name="gradeRole" value="${param.gradeRole}">
								<input type="hidden" id="role" name="role" value="${role}">
								<div class="queryDiv" style="min-width: 960px;max-width: 960px;">
									<div class="inputDiv" style="min-width: 275px;max-width: 275px;">
										出科时间：
										<input type="text" id="operStartDate" name="operStartDate" value="${operStartDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"readonly="readonly" class="qtext" style="width: 75px;"/>~<input type="text" id="operEndDate" name="operEndDate" value="${operEndDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" class="qtext" style="width: 75px;"/>
									</div>
									<div class="lastDiv" style="min-width: 182px;max-width: 182px;">
										<input type="button" class="searchInput" value="查&#12288;询" onclick="gradeSearch(1)">
									</div>
								</div>
							</form>
							<table class="basic list" width="100%" style="margin-top: 10px;">
								<tr>
									<th style="text-align: left;padding-left: 10px;">
										科室名称
									</th>
									<th>评价份数</th>
									<th>操作</th>
								</tr>
								<c:forEach items="${datas}" var="data">
									<tr>
										<td style="text-align: left;padding-left: 10px;">
											${data.deptName}
										</td>
										<td>
											${data.num}
										</td>
										<td>
											<span style="cursor: pointer;color: blue;font-size: 1em;line-height: 5px;" onclick="operDetail('${data.deptName}','${data.deptFlow}');">查看汇总</span>
										</td>
									</tr>
								</c:forEach>


								<c:if test="${empty datas}">
									<tr>
										<td colspan="3">
											无记录
										</td>
									</tr>
								</c:if>
							</table>

							<div class="page" style="padding-right: 40px;">
								<c:set var="pageView" value="${pdfn:getPageView(datas)}" scope="request"></c:set>
								<pd:pagination toPage="gradeSearch"/>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>