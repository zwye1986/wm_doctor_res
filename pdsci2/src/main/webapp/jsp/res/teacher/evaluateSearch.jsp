<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>${sysCfgMap['sys_title_name']}</title>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="slideRight" value="true"/>
</jsp:include>
	<style type="text/css">
		.module-tabs{ height:49px; line-height:49px; font-size:15px; color:6e6e6e; border-bottom:1px solid #dadada}
		.module-tabs li{ float:left;  cursor:pointer;display:block;}
		.module-tabs .type li{padding:0 15px;}
		.module-tabs li.on{ height:48px; border-bottom:2px solid #009fff; color:#009fff;}
		.module-tabs li a{padding:8px 15px;}
		.module-tabs li a.hove1{ height:48px; color:#fff; padding:8px 15px;  background:#009fff;}
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

		#tags li.on a{background-position: right top;color: #000;line-height: 28px;height:28px;}

		#tags li a{
			padding: 0 20px;
			background:url(images/tab.png) repeat-x;
			float: left;
			color:#1e7db3;
			font-weight:bold;
			line-height: 28px;
			-moz-border-radius: 3px 3px 0 0;
			-webkit-border-radius: 3px 3px 0 0;
			border-radius: 3px 3px 0 0;
		}

	</style>
<script type="text/javascript">
	$(function(){
		if(${param.roleType eq 'head'}){
			$(".on").removeClass("selectTag");
			$('#head').addClass('selectTag');
		}else if(${param.roleType eq 'teacher'}){
			$(".on").removeClass("selectTag");
			$('#teacher').addClass('selectTag');
		}
	});
	function setType(flag,obj){
		$(".selectTag").removeClass("selectTag");
		$(obj).addClass('selectTag');
		$("#roleType").val(flag);
		var startDate = $("#startDate").val();
		var endDate = $("#endDate").val();
		if(startDate && endDate && startDate>endDate){
			return jboxTip("开始时间必须小于等于结束时间！");
		}
		evaluateSearch();
	}
	function evaluateSearch(){
		var startDate = $("#startDate").val();
		var endDate = $("#endDate").val();
		if(startDate && endDate && startDate>endDate){
			return jboxTip("开始时间必须小于等于结束时间！");
		}
		$('#searchForm').submit();
	}
	var height=(window.screen.height)*0.7;
	function checkDetails(roleType,userFlow){
		var startDate=$("#startDate").val();
		var endDate=$("#endDate").val();
		var url="<s:url value='/res/head/checkDetails'/>?roleType="+roleType+"&userFlow="+userFlow+"&startDate="+startDate+"&endDate="+endDate;
		jboxOpen(url,"查看",900,height,true);
	}
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div class="top-tab">
			<%--<div class="module-tabs" style="display: block;width: 100%;">--%>
			<div class="clearfix" style="padding-top: 10px;">
				<ul id="tags">
					<li id="teacher" onclick="setType('teacher',this);">
						<a>带教老师</a>
					</li>
					<li id="head" onclick="setType('head',this);">
						<a>科室</a>
					</li>
				</ul>
				<div id="tagContent">
					<div class="content">
						<div class="module-content" style="width: 100%;">
							<form id="searchForm" action="<s:url value='/res/head/evaluateSearch'/>" method="post">
								<input type="hidden" id="roleType" name="roleType" value="${param.roleType}">
								<div class="queryDiv">
									<div class="qcheckboxDiv">
										<label class="qlable">评分时间：</label>
										<input type="text" id="startDate" name="startDate"  class="qtime" value="${param.startDate}"  readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" <%--onchange="setType('${param.roleType}');"--%>/>
										~
										<input type="text" id="endDate" name="endDate" class="qtime" value="${param.endDate}" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" <%--onchange="setType('${param.roleType}');"--%>/>
									</div>

									<c:if test="${param.roleType eq GlobalConstant.RES_ROLE_SCOPE_TEACHER}">
										<div class="inputDiv">
											<label class="qlable">姓&#12288;&#12288;名：</label>
											<input type="text" name="doctorName" class="qtext" value="${param.doctorName}" <%--onchange="setType('${param.roleType}');"--%> />
										</div>
									</c:if>
									&#12288;
									<input type="button" class="searchInput" value="查&#12288;询" onclick="evaluateSearch()"/>
									<c:if test="${param.roleType eq GlobalConstant.RES_ROLE_SCOPE_HEAD}">
										<input type="button" class="searchInput" value="查看详情" onclick="checkDetails('${param.roleType}');"/>
									</c:if>

								</div>
							</form>
							<c:if test="${param.roleType eq GlobalConstant.RES_ROLE_SCOPE_TEACHER}">
								<table class="basic list" width="100%" style="margin-top: 10px;">
									<tr>
										<th>老师姓名</th>
										<th>当前带教人数</th>
										<th>总人数</th>
										<th>平均得分</th>
									</tr>
									<c:forEach items="${sysUserList}" var="user">
										<c:set value="curr${user.userFlow}" var="currKey"/>
										<c:set value="student${user.userFlow}" var="studentKey"/>
										<tr>
											<td>${user.userName}</td>
											<td>${studentNumMap[currKey]}</td>
											<td>${studentNumMap[studentKey]}</td>
											<td><a style="cursor: pointer;color:blue;" onclick="checkDetails('${param.roleType}','${user.userFlow}');">${averageMap[user.userFlow]}</a></td>
										</tr>
									</c:forEach>
								</table>
							</c:if>
							<c:if test="${param.roleType eq GlobalConstant.RES_ROLE_SCOPE_HEAD}">
								<table class="basic list" width="100%" style="margin-top: 10px;">
									<colgroup>
										<col width="70%"/>
										<col width="15%"/>
										<col width="15%"/>
									</colgroup>
									<tr>
										<th>评分项</th>
										<th>标准分</th>
										<th>平均得分</th>
									</tr>
									<c:forEach items="${titleFormList}" var="title">
										<tr>
											<td colspan="3" style="text-align: left;padding-left: 10px;font-weight:bold;font-size: 16px;">${title.name}</td>
										</tr>
										<c:forEach items="${title.itemList}" var="item">
											<tr>
												<td style="text-align: left;padding-left: 10px;">${item.name}</td>
												<td class="biaoz" style="font-weight:normal;"><c:out value="${item.score}" default="-"/></td>
												<td><c:out value="${heJiMap[item.id]}" default="-"/></td>
											</tr>
										</c:forEach>
									</c:forEach>
									<tr>
										<th style="text-align: left;padding-left: 10px;">总标准分/总平均分</th>
										<th><span id="biaoZ" style="font-weight:bold;"></span></th>
										<th>${average}</th>
									</tr>
								</table>
							</c:if>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
</div>
</body>
</html>