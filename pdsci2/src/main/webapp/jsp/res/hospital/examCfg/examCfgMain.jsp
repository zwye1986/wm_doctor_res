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
</style>
<script type="text/javascript">
	$(document).ready(function(){
		toPage(1);
	});
	function toPage(page) {
		if(page==undefined||page=="undefined")
				page=1;
		$("#currentPage").val(page);
		jboxStartLoading();
		jboxPostLoad("doctorListZi","<s:url value='/res/examCfg/list'/>",$("#searchForm").serialize(),false);
	}
	function edit(arrangeFlow) {
		var url = "<s:url value='/res/examCfg/edit'/>?arrangeFlow="+arrangeFlow;
		jboxOpen(url, "考核设置",800,400);
	}
	function openInfo(arrangeFlow,isOpen) {
		var msg = "";
		if (isOpen == '${GlobalConstant.RECORD_STATUS_N}') {
			msg = "关闭";
		} else if (isOpen == '${GlobalConstant.RECORD_STATUS_Y}') {
			msg = "开放";
		}
		jboxConfirm("确认" + msg + "该年度考核吗？",function () {
			var url = "<s:url value='/res/examCfg/updateCfg?arrangeFlow='/>" + arrangeFlow+ "&isOpen=" + isOpen;
			jboxGet(url,null,function(resp){
				if(resp=="操作成功！")
					toPage(1);
			},null,true);
		});
	}
	function del(arrangeFlow) {
		jboxConfirm("确认删除该年度考核吗？",function () {
			var url = "<s:url value='/res/examCfg/updateCfg?arrangeFlow='/>" + arrangeFlow+ "&recordStatus=N";
			jboxGet(url,null,function(resp){
				if(resp=="操作成功！")
					toPage(1);
			},null,true);
		});
	}
</script>

<div class="mainright" style="min-height: 300px;">
	<div class="content">
		<div class="title1 clearfix">
			<form id="searchForm">
				<input type="hidden" id="currentPage" name="currentPage"/>
				<div class="queryDiv">
					<div class="inputDiv">
						<label class="qlable">培训专业：</label>
						<select name="trainingSpeId" id="trainingSpeId" class="qselect">
							<option value="">全部</option>
							<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
								<option value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
							</c:forEach>
						</select>
					</div>
					<div class="inputDiv">
						<label class="qlable">年&#12288;&#12288;级：</label>
						<select id="sessionNumber" name="sessionNumber" class="qselect">
							<option></option>
							<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict">
								<option value="${dict.dictId}" ${param.sessionNumber eq dict.dictId?'selected':''}>${dict.dictName}</option>
							</c:forEach>
						</select>
					</div>
					<div class="inputDiv">
						<label class="qlable">年&#12288;&#12288;度：</label>
						<input type="text" id="assessmentYear" onclick="WdatePicker({dateFmt:'yyyy'})" name="assessmentYear" class="qtext" readonly="readonly"/>
					</div>
					<div class="lastDiv" style="max-width: 200px;min-width: 200px;">
						<input class="searchInput" type="button" value="查&#12288;询" onclick="toPage();"/>
						<input class="searchInput" type="button" value="考核设置" onclick="edit('');"/>
					</div>
				</div>
			</form>
		</div>
		<div id="doctorListZi">
		</div>
	</div>
</div>
</html>