<%@include file="/jsp/common/doctype.jsp" %>
<html>
<title>江苏省住院医师规范化培训管理平台</title>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<script type="text/javascript">
	$(function(){
		$('#operStartDate,#operEndDate').datepicker();
	});
	
	function selTag(gradeRole){
		$("#gradeRole").val(gradeRole);
		var startDate = $("#operStartDate").val() || "";
		var endDate = $("#operEndDate").val() || "";
		if(startDate && endDate && startDate>endDate){
			return jboxTip("开始时间必须小于等于结束时间！");
		}
		gradeSearch();
	}
	
	function detailShow(span,clazz){
		$("font",span).toggle();
		$("."+clazz+"Show").fadeToggle(100);
	}
	function operDetail(name,keyCode){
		var startDate = $("#operStartDate").val() || "";
		var endDate = $("#operEndDate").val() || "";
		var url = "<s:url value='/jsres/manage/gradeSearchDoc'/>?gradeRole=${param.gradeRole}&keyCode="+keyCode+"&operStartDate="+startDate+"&operEndDate="+endDate;
		jboxOpen(url,name+"评分详情",800,500);
	}
</script>

<div class="main_hd">
	<h2>出科评价查看</h2>
    <div class="title_tab">
        <ul id="reducationTab">
            <li class="${param.gradeRole eq 'teacher'?'tab_select':'tab'}" onclick="selTag('teacher');"><a>带教老师</a></li>
            <li class="${param.gradeRole eq 'head'?'tab_select':'tab'}" onclick="selTag('head');"><a>科室</a></li>
        </ul>
    </div>
</div>

<div class="div_search">
	<form id="gradeSearchForm">
		<input type="hidden" id="currentPage" name="currentPage" value="${currentPage}">
		<input type="hidden" id="gradeRole" name="gradeRole" value="${param.gradeRole}">
		<div>
			<c:if test="${param.gradeRole eq 'teacher'}">
				姓名：
				<input type="text" name="userName" value="${param.userName}" class="input" onchange="gradeSearch();" style="width: 100px;"/>
				&#12288;
			</c:if>
			<c:if test="${param.gradeRole eq 'head'}">
				科室名称：
				<input type="text" name="deptName" value="${param.deptName}" class="input" onchange="gradeSearch();" style="width: 100px;"/>
				&#12288;
			</c:if>
			评分时间：
			<input type="text" id="operStartDate" name="operStartDate" value="${param.operStartDate}" class="input" onchange="gradeSearch();" readonly="readonly" style="width: 100px;"/>
			~
			<input type="text" id="operEndDate" name="operEndDate" value="${param.operEndDate}" class="input" onchange="gradeSearch();" readonly="readonly" style="width: 100px;"/>
		</div>
	</form>
</div>

<div class="search_table">
	<table border="0" cellpadding="0" cellspacing="0" class="grid">
		<colgroup>
			<col width="70%"/>
			<col width="15%"/>
			<col width="15%"/>
		</colgroup>
		
		<tr>
			<th style="text-align: left;padding-left: 10px;">
				<c:if test="${param.gradeRole eq 'teacher'}">
					老师姓名
				</c:if>
				<c:if test="${param.gradeRole eq 'head'}">
					科室名称
				</c:if>
			</th>
			<th >标准分</th>
			<th >总均分</th>
		</tr>
		
		<c:forEach items="${datas}" var="data">
			<c:if test="${param.gradeRole eq 'teacher'}">
				<c:set var="key" value="${data.userFlow}"/>
				<c:set var="name" value="${data.userName}"/>
			</c:if>
			
			<c:if test="${param.gradeRole eq 'head'}">
				<c:set var="key" value="${data.deptFlow}"/>
				<c:set var="name" value="${data.deptName}"/>
			</c:if>
			
			<c:set var="avgScoreKey" value="${key}_Total"/>
			<c:if test="${not empty avgMap[avgScoreKey]}">
			<tr>
				<th style="text-align: left;padding-left: 10px;">
					<span style="cursor: pointer;color: blue;font-size: 0.5em;line-height: 5px;" onclick="detailShow(this,'${key}');">
						[<font class="open">展开</font><font class="close" style="display: none;">收起</font>]
					</span>
					&nbsp;
					${name}
				</th>
				<th>${scoreMap[key]}</th>
				<th>
					<a style="color: blue;font-weight: normal;" onclick="operDetail('${name}','${key}');">${avgMap[avgScoreKey]}</a>
				</th>
			</tr>
			<c:forEach items="${assessCfgList}" var="title">
				<tr>
					<td class="${key}Show" colspan="2" style="text-align: left;padding-left: 10px;font-weight: bold;display: none;">
						${title.name}
					</td>
					<td class="${key}Show"  style="font-weight: bold;display: none;padding-right: 20px;">
					</td>
				</tr>
				<c:forEach items="${title.itemList}" var="item">
					<c:set var="scoreKey" value="${key}_${item.id}"/>
					<tr>
						<td class="${key}Show" style="text-align: left;padding-left: 10px;display: none;">${item.name}</td>
						<td class="${key}Show" style="display: none;padding-right: 20px;">${item.score}</td>
						<td class="${key}Show" style="display: none;">${avgMap[scoreKey]}</td>
					</tr>
				</c:forEach>
			</c:forEach>
			</c:if>
		</c:forEach>
		<c:if test="${empty datas}">
			<tr>
				<td colspan="2">
					无记录
				</td>
			</tr>
		</c:if>
	</table>
</div>
</html>