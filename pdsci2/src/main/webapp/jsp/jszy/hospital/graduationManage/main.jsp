<%@include file="/jsp/common/doctype.jsp" %>
<html>
<title>${sysCfgMap['sys_title_name']}</title>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true" />
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
</jsp:include>
<style type="text/css">
	.doctorTypeDiv {
		border: 0px;
		float: left;
		width: auto;
		line-height: 35px;
		height: 35px;
		text-align: right;
	}
	.doctorTypeLabel {
		border: 0px;
		float: left;
		width: 96px;
		line-height: 35px;
		height: 35px;
		text-align: right;
	}
	.doctorTypeContent {
		border: 0px;
		float: left;
		width: auto;
		line-height: 35px;
		height: 35px;
		text-align: right;
	}
</style>
<script type="text/javascript">
	$(document).ready(function(){
		toPage(1);
	});
	function toPage(page) {
		if(page==undefined||page=="undefined")
			page=1;
		$("#currentPage").val(page);
		var typeNum=$("input[name='doctorTypeIdList']:checked").length;
		if(typeNum<1){
			jboxTip("请选择人员类型！");
			return;
		}
		jboxStartLoading();
		jboxPostLoad("doctorListZi","<s:url value='/jszy/graduation/scorelist'/>",$("#searchForm").serialize(),false);
	}
	function exportInfo()
	{
		var typeNum=$("input[name='doctorTypeIdList']:checked").length;
		if(typeNum<1){
			jboxTip("请选择人员类型！");
			return;
		}
		jboxExp($("#searchForm"),"<s:url value='/jszy/graduation/exportInfo'/>");
	}
	<%--function downloadExamPaper(recordFlow) {--%>
		<%--var url = "<s:url value='/res/examCfg/downloadExamPaper?recordFlow='/>" + recordFlow;--%>
		<%--jboxGet(url,null,function(resp){--%>
			<%--var data=eval("("+resp+")");--%>
			<%--if(data.result=="1")--%>
			<%--{--%>
				<%--$("#url").val(data.url);--%>
				<%--jboxExp($("#exportFrom"),"<s:url value='/res/examCfg/downloadFile'/>");--%>
			<%--}else{--%>
				<%--jboxTip(data.msg);--%>
			<%--}--%>
		<%--},null,false);--%>
	<%--}--%>
</script>

<form id="exportFrom">
	<input type="hidden" id="url" name="url"/>
	</form>
<div class="mainright" style="min-height: 300px;">
	<div class="content">
		<div class="title1 clearfix">
			<form id="searchForm">
				<input type="hidden" id="currentPage" name="currentPage"/>
				<div class="queryDiv">
					<div class="inputDiv">
						<label class="qlable">姓&#12288;&#12288;名：</label>
						<input type="text" name="userName" class="qtext"/>
					</div>
					<div class="inputDiv">
						<label class="qlable">培训专业：</label>
						<select name="trainingSpeId" id="trainingSpeId" class="qselect">
							<option value="">全部</option>
							<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
								<option value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
							</c:forEach>
						</select>
					</div>
					<div class="doctorTypeDiv">
						<div class="doctorTypeLabel">学员类型：</div>
						<div class="doctorTypeContent">
							<c:forEach items="${dictTypeEnumDoctorTypeList}" var="type">
								<label><input type="checkbox" name="doctorTypeIdList" <c:if test="${empty param.doctorTypeIdList}" >checked</c:if> value="${type.dictId}"
									${doctorTypeSelectMap[type.dictId]}>${type.dictName}</label>
							</c:forEach>
						</div>
					</div>
					<div class="inputDiv">
						<label class="qlable">年&#12288;&#12288;级：</label>
						<label class="qlable"><input type="text" id="sessionNumber" name="sessionNumber" onclick="WdatePicker({dateFmt:'yyyy'})"  value="${pdfn:getCurrYear()-3}" class="qtext" readonly="readonly" /></label>
						<%--<select id="sessionNumber" name="sessionNumber" class="qselect">
							<option></option>
							<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict">
								<option value="${dict.dictId}" ${param.sessionNumber eq dict.dictId?'selected':''}>${dict.dictName}</option>
							</c:forEach>
						</select>--%>
					</div>
					<div class="lastDiv" style="min-width: 185px;max-width: 185px;">
						<input class="searchInput" type="button" value="查&#12288;询" onclick="toPage();"/>
						<input class="searchInput" type="button" value="导&#12288;出" onclick="exportInfo();"/>
					</div>
				</div>
			</form>
		</div>
		<div id="doctorListZi">
		</div>
	</div>
</div>
</html>