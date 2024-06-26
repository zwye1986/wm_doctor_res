<%@include file="/jsp/common/doctype.jsp" %>
<html>
<title>${sysCfgMap['sys_title_name']}</title>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<script type="text/javascript">
	$(function(){
		$('#operStartDate,#operEndDate').datepicker();
		$('#sessionNumber').datepicker({
			startView: 2, 
			maxViewMode: 2,
			minViewMode:2,
			format:'yyyy'
		});
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
	
	function operDetail(name,keyCode,date){
		var startDate = $("#operStartDate").val() || "";
		var endDate = $("#operEndDate").val() || "";
		date =date||"";
		var url = "<s:url value='/jsres/institute/gradeSearchDoc'/>?gradeRole=${param.gradeRole}&keyCode="+keyCode+"&operStartDate="+startDate+"&operEndDate="+endDate+"&date="+date;
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
			基地：
			<select name="recOrgFlow" onchange="gradeSearch();" class="select" style="width: 100px;">
				<option/>
				<c:forEach items="${sysOrgList}" var="org">
					<option value="${org.orgFlow}" <c:if test="${param.recOrgFlow eq org.orgFlow}">selected</c:if>>${org.orgName}</option>
				</c:forEach>
			</select>
			&#12288;
			<c:if test="${param.gradeRole eq 'head'}">
				科室：
				<select name="deptFlow" onchange="gradeSearch();" class="select" style="width: 100px;">
					<option/>
					<c:forEach items="${depts}" var="dept">
						<option value="${dept.deptFlow}" <c:if test="${param.deptFlow eq dept.deptFlow}">selected</c:if>>${dept.deptName}</option>
					</c:forEach>
				</select>
				&#12288;
			</c:if>
			
			<c:if test="${param.gradeRole eq 'teacher'}">
				姓名：
				<input type="text" name="userName" value="${param.userName}" class="input" onchange="gradeSearch();" style="width: 100px;"/>
				&#12288;
			</c:if>
			评分时间：
			<input type="text" id="operStartDate" name="operStartDate" value="${param.operStartDate}" class="input" onchange="gradeSearch();" readonly="readonly" style="width: 100px;"/>
			~
			<input type="text" id="operEndDate" name="operEndDate" value="${param.operEndDate}" class="input" onchange="gradeSearch();" readonly="readonly" style="width: 100px;"/>
				&#12288;届别：
				<input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" class="input" onchange="gradeSearch();" readonly="readonly" style="width: 100px;"/>
		</div>
	</form>
</div>

<div class="search_table">

	<table border="0" cellpadding="0" cellspacing="0" class="grid">
		<colgroup>
			<c:if test="${param.gradeRole eq 'teacher'}">
				<c:if test="${empty param.sessionNumber}">
					<c:set value="7" var="col"/>
					<col width="20%"/>
					<col width="15%"/>
					<col width="15%"/>
					<col width="15%"/>
					<col width="15%"/>
					<col width="10%"/>
					<col width="10%"/>
				</c:if>
				
				<c:if test="${not empty param.sessionNumber}">
					<c:set value="5" var="col"/>
					<col width="20%"/>
					<col width="15%"/>
					<col width="35%"/>
					<col width="15%"/>
					<col width="15%"/>
				</c:if>
			</c:if>
			<c:if test="${param.gradeRole eq 'head'}">
				<c:if test="${empty param.sessionNumber}">
					<c:set value="6" var="col"/>
					<col width="25%"/>
					<col width="15%"/>
					<col width="15%"/>
					<col width="15%"/>
					<col width="15%"/>
					<col width="15%"/>
				</c:if>
				<c:if test="${not empty param.sessionNumber}">
					<c:set value="4" var="col"/>
					<col width="40%"/>
					<col width="20%"/>
					<col width="20%"/>
					<col width="20%"/>
				</c:if>
			</c:if>
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
			<c:if test="${param.gradeRole eq 'teacher'}">
				<th >科室信息</th>
			</c:if>
			<c:if test="${empty param.sessionNumber}">
				<th >${FirstTwoYearsDate}届总均分</th>
				<th >${PreviouYearDate}届总均分</th>
				<th >${currDate}届总均分</th>
			</c:if>
			<c:if test="${not empty param.sessionNumber}">
				<th>${param.sessionNumber}届总均分</th>
			</c:if>
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
		
			<tr>
				<th style="text-align: left;padding-left: 10px;">
					<span style="cursor: pointer;color: blue;font-size: 0.5em;line-height: 5px;" onclick="detailShow(this,'${key}');">
						[<font class="open">展开</font><font class="close" style="display: none;">收起</font>]
					</span>
					&nbsp;
					${name}
				</th>
				<c:if test="${param.gradeRole eq 'teacher'}">
					<th>${data.deptName}</th>
				</c:if>
				<c:if test="${empty param.sessionNumber}">
					<th>
						<a style="color: blue;font-weight: normal;" onclick="operDetail('${name}','${key}','${FirstTwoYearsDate}');">${recFirstTwoYearsAvgMap[avgScoreKey]}</a>
					</th>
					<th>
						<a style="color: blue;font-weight: normal;" onclick="operDetail('${name}','${key}','${PreviouYearDate}');">${recpreviouYearAvgMap[avgScoreKey]}</a>
					</th>
					<th>
						<a style="color: blue;font-weight: normal;" onclick="operDetail('${name}','${key}','${currDate}');">${recCurrDateAvgMap[avgScoreKey]}</a>
					</th>
				</c:if>
				<c:if test="${not empty param.sessionNumber}">
					<th>
						<a style="color: blue;font-weight: normal;" onclick="operDetail('${name}','${key}','${param.sessionNumber}');">${recDateAvgMap[avgScoreKey]}</a>
					</th>
				</c:if>
				<th style="font-weight:normal;color: #C5C5C5;">${scoreMap[key]}</th>
				<th>
					<a style="color: blue;font-weight: normal;" onclick="operDetail('${name}','${key}',null);">${avgMap[avgScoreKey]}</a>
				</th>
			</tr>
			<c:forEach items="${assessCfgList}" var="title">
				<tr>
					<c:if test="${param.gradeRole eq 'head'}">
					<c:if test="${empty param.sessionNumber}">
						<td class="${key}Show" colspan="5" style="text-align: left;padding-left: 10px;font-weight: bold;display: none;">
							${title.name}
						</td>
					</c:if>
					<c:if test="${not empty param.sessionNumber}">
						<td class="${key}Show" colspan="3" style="text-align: left;padding-left: 10px;font-weight: bold;display: none;">
							${title.name}
						</td>
					</c:if>
					</c:if>
					<c:if test="${param.gradeRole eq 'teacher'}">
					<c:if test="${empty param.sessionNumber}">
						<td class="${key}Show" colspan="6" style="text-align: left;padding-left: 10px;font-weight: bold;display: none;">
							${title.name}
						</td>
					</c:if>
					<c:if test="${not empty param.sessionNumber}">
						<td class="${key}Show" colspan="4" style="text-align: left;padding-left: 10px;font-weight: bold;display: none;">
							${title.name}
						</td>
					</c:if>
					</c:if>
					<td class="${key}Show"  style="font-weight: bold;display: none;padding-right: 20px;">
					</td>
				</tr>
				<c:forEach items="${title.itemList}" var="item">
					<c:set var="scoreKey" value="${key}_${item.id}"/>
					<tr>
						<c:if test="${param.gradeRole eq 'head'}">
							<td class="${key}Show"  style="text-align: left;padding-left: 10px;display: none;">${item.name}</td>
						</c:if>
						<c:if test="${param.gradeRole eq 'teacher'}">
							<td class="${key}Show" colspan="2" style="text-align: left;padding-left: 10px;display: none;">${item.name}</td>
						</c:if>
						<c:if test="${empty param.sessionNumber}">
							<td class="${key}Show" style="display: none;padding-right: 20px;">${recFirstTwoYearsAvgMap[scoreKey]}</td>
							<td class="${key}Show" style="display: none;padding-right: 20px;">${recpreviouYearAvgMap[scoreKey]}</td>
							<td class="${key}Show" style="display: none;padding-right: 20px;">${recCurrDateAvgMap[scoreKey]}</td>
						</c:if>
						<c:if test="${not empty param.sessionNumber}">
							<td class="${key}Show" style="display: none;padding-right: 20px;">${recDateAvgMap[scoreKey]}</td>
						</c:if>
						<td class="${key}Show" style="display: none;padding-right: 20px;color: #C5C5C5;">${item.score}</td>
						<td class="${key}Show" style="display: none;">${avgMap[scoreKey]}</td>
					</tr>
				</c:forEach>
			</c:forEach>
		</c:forEach>
			<c:if test="${empty datas}">
			<tr>
				<td colspan="${col}">
					<c:if test="${empty param.recOrgFlow}">
						请选择基地
					</c:if>
					<c:if test="${not empty param.recOrgFlow}">
						无记录
					</c:if>
				</td>
			</tr>
			</c:if>
	</table>
</div>
</html>