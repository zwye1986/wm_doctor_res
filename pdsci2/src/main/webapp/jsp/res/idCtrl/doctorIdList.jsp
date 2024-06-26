<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true" />
	<jsp:param name="jbox" value="true" />
	<jsp:param name="jquery_form" value="false" />
	<jsp:param name="jquery_ui_tooltip" value="true" />
	<jsp:param name="jquery_ui_combobox" value="false" />
	<jsp:param name="jquery_ui_sortable" value="false" />
	<jsp:param name="jquery_cxselect" value="true" />
	<jsp:param name="jquery_scrollTo" value="false" />
	<jsp:param name="jquery_jcallout" value="false" />
	<jsp:param name="jquery_validation" value="true" />
	<jsp:param name="jquery_datePicker" value="true" />
	<jsp:param name="jquery_fullcalendar" value="false" />
	<jsp:param name="jquery_fngantt" value="false" />
	<jsp:param name="jquery_fixedtableheader" value="true" />
	<jsp:param name="jquery_placeholder" value="true" />
	<jsp:param name="jquery_iealert" value="false" />
</jsp:include>
<style>
</style>
<script type="text/javascript">
	function search(){
		jboxStartLoading();
		$("#searchForm").submit();
	}
	
	function toPage(page) {
		if(page){
			$("#currentPage").val(page);			
		}
		search();
	}

	function addId(){
		jboxOpen("<s:url value='/res/idCtrl/addId'/>","详情", 480, 300);
	}

	function details(recordFlow){
		jboxOpen("<s:url value='/res/idCtrl/details'/>?recordFlow="+recordFlow+"&currentPage=${param.currentPage}","详情", 480, 480,false);
	}

	function myIds(){
		jboxOpen("<s:url value='/res/idCtrl/myIdList'/>","详情", 480, 480,true);
	}

	function bindingId(doctorFlow,endDate){
		jboxOpen("<s:url value='/res/idCtrl/bindId'/>?doctorFlow="+doctorFlow+"&endDate="+endDate+"&currentPage=${param.currentPage}","详情", 480, 480,true);
	}

	function checkAll(){
		if($("#checkAll").attr("checked")){
			$(".check").attr("checked",true);
		}else{
			$(".check").attr("checked",false);
		}
	}

	function checkSingel(obj){
		if(!$(obj).attr("checked")){
			$("#checkAll").attr("checked",false);
		}else{
			var checkAllLen = $("input[type='checkbox'][class='check']").length;
			var checkLen = $("input[type='checkbox'][class='check']:checked").length;
			if(checkAllLen == checkLen){
				$("#checkAll").attr("checked",true);
			}
		}
	}

	function bindingIds(){
		var num = $(".check:checked").length;
		if(num==0){
			jboxTip("请至少选择一个学员");
			return;
		}
		jboxOpen("<s:url value='/res/idCtrl/bindingIds'/>?num="+num+"&currentPage=${param.currentPage}","详情", 480, 480,true);
	}
</script>
</head>
<body>
	<div class="mainright">
		<div class="content">
			<div class="title1 clearfix">
				<form id="searchForm" method="post" action="<s:url value='/res/idCtrl/doctorIdList'/>">
					<input id="currentPage" type="hidden" name="currentPage" value=""/>
					<div class="queryDiv">
					<div class="inputDiv">
						姓&#12288;&#12288;名：
						<input type="text" name="doctorName" class="qtext" value="${param.doctorName}"/>
					</div>
					<div class="inputDiv">
						年&#12288;&#12288;级：
						<select name="sessionNumber" class="qselect">
							<option value="">全部</option>
							<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict">
								<option value="${dict.dictName}" ${param.sessionNumber eq dict.dictName?'selected':''}>${dict.dictName}</option>
							</c:forEach>
						</select>&nbsp;
					</div>
					<div class="inputDiv">
						培训专业：
						<select name="trainingSpeId" class="qselect">
							<option value="">全部</option>
							<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="spe">
								<option value="${spe.dictId}"
										<c:if test="${param.trainingSpeId eq spe.dictId}">selected</c:if>
								>${spe.dictName}</option>
							</c:forEach>
						</select>
					</div>
					<div class="inputDiv">
						培训年限：
						<select name="trainingYears" class="qselect">
							<option value="">全部</option>
							<c:forEach items="${dictTypeEnumTrainingYearsList}" var="dict">
								<option value="${dict.dictName}" ${param.trainingYears eq dict.dictName?'selected':''}>${dict.dictName}</option>
							</c:forEach>
						</select>
					</div>
					<div class="inputDiv">
						结业年份：
						<input class="qtext" name="graduationYear" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy'});" value="${param.graduationYear}">
					</div>
					<div class="inputDiv" style="text-align: left;padding-left: 23px;min-width: 222px;max-width: 222px;">
						<label><input type="checkbox" value="Y" name="nearOut" ${param.nearOut eq 'Y'?'checked':''}> 即将过期</label>
					</div>
					<div class="inputDiv" style="text-align: left;min-width: 300px;max-width: 300px;">&#12288;&#12288;
						<input type="button" value="查&#12288;询" class="search" onclick="search();" style=""/>
						<input type="button" value="批量绑定" class="search" onclick="bindingIds();"/>
						<input type="button" value="我的ID库" class="search" onclick="myIds()"/>
					</div>
					</div>
				</form>
				<table class="basic" width="100%">
					<tr style="font-weight: bold;">
						<td style="text-align: left;padding-left: 1%;width: 50px;">
							<input type="checkbox" id="checkAll" onclick="checkAll()">&nbsp;序号
						</td>
						<td style="text-align: center;padding: 0px;">姓名</td>
						<td style="text-align: center;padding: 0px;">年级</td>
						<td style="text-align: center;padding: 0px;">专业</td>
						<td style="text-align: center;padding: 0px;">培训年限</td>
						<td style="text-align: center;padding: 0px;">结业年份</td>
						<td style="text-align: center;padding: 0px;">使用期限</td>
						<td style="text-align: center;padding: 0px;">绑定ID</td>
					</tr>
					<c:forEach items="${resultMapList}" var="item" varStatus="s">
						<tr>
							<td style="text-align: left;padding-left: 1%;width: 50px;">
								<input type="checkbox" class="check" value="${item.USER_FLOW}" endDate="${item.END_DATE}"
									   onclick="checkSingel(this)">&nbsp;${s.index+1}
							</td>
							<td style="text-align: center;padding: 0px;">${item.USER_NAME}</td>
							<td style="text-align: center;padding: 0px;">${item.SESSION_NUMBER}</td>
							<td style="text-align: center;padding: 0px;">${item.TRAINING_SPE_NAME}</td>
							<td style="text-align: center;padding: 0px;">${item.TRAINING_YEARS}</td>
							<td style="text-align: center;padding: 0px;">${item.GRADUATION_YEAR}</td>
							<td style="text-align: center;padding: 0px;">${item.START_DATE} ~ ${item.END_DATE}</td>
							<td style="text-align: center;padding: 0px;">
								<a onclick="bindingId('${item.USER_FLOW}','${item.END_DATE}')" style="cursor: pointer;color: blue;">绑定</a>
							</td>
						</tr>
					</c:forEach>
					<c:if test="${empty resultMapList}">
						<tr><td style="text-align: center;" colspan="10">无记录</td></tr>
					</c:if>
				</table>
				<div>
				   	<c:set var="pageView" value="${pdfn:getPageView(resultMapList)}" scope="request"/>
					<pd:pagination toPage="toPage"/>
				</div>
			</div>
		</div>
	</div>
</body>
</html>