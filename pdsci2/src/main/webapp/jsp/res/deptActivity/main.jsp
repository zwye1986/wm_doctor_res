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
		jboxPostLoad("doctorListZi","<s:url value='/res/deptActivity/list/${role}'/>",$("#searchForm").serialize(),false);
	}
	function edit(arrangeFlow) {
		var url = "<s:url value='/res/examCfg/edit'/>?arrangeFlow="+arrangeFlow;
		jboxOpen(url, "考核设置",800,400);
	}
	function addPlan(planFlow) {
		var url = "<s:url value='/res/deptActivity/addPlan'/>?planFlow="+planFlow;
		if(planFlow) {
			var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
			top.jboxMessager(iframe,"科室活动维护", 900, 500, null, false);
		}else{
			jboxOpen(url, "科室活动新增", 400, 300);
		}
	}
	function auditPlan(planFlow) {
		var url = "<s:url value='/res/deptActivity/showPlan'/>?isAudit=Y&planFlow="+planFlow;
		jboxOpen(url, "科室活动审核",900,500);
	}
	function show(planFlow) {
		var url = "<s:url value='/res/deptActivity/showPlan'/>?isAudit=N&planFlow="+planFlow;
		jboxOpen(url, "科室活动查看",900,500);
	}
	function delPlan(planFlow)
	{
		jboxConfirm("确认删除此科室活动计划？",function(){
			var url = "<s:url value='/res/deptActivity/delPlan'/>?planFlow="+planFlow;
			jboxPost(url,null,function(resp){
				if('${GlobalConstant.OPRE_SUCCESSED_FLAG}'==resp){
					jboxTip("${GlobalConstant.OPRE_SUCCESSED}");
					toPage(1);
				}
				if('${GlobalConstant.OPRE_FAIL_FLAG}'==resp){
					jboxTip("${GlobalConstant.OPRE_FAIL}");
				}
				if('${GlobalConstant.OPRE_FAIL_FLAG}'!=resp&&'${GlobalConstant.OPRE_SUCCESSED_FLAG}'!=resp)
				{
					jboxTip(resp);
				}
			},null,false);
		},null);
	}
	function reportPlan(planFlow)
	{
		jboxConfirm("确认发布此科室活动计划？",function(){
			var url = "<s:url value='/res/deptActivity/reportPlan'/>?planFlow="+planFlow;
			jboxPost(url,null,function(resp){
				if('${GlobalConstant.OPRE_SUCCESSED_FLAG}'==resp){
					jboxTip("${GlobalConstant.OPRE_SUCCESSED}");
					toPage(1);
				}
				if('${GlobalConstant.OPRE_FAIL_FLAG}'==resp){
					jboxTip("${GlobalConstant.OPRE_FAIL}");
				}
				if('${GlobalConstant.OPRE_FAIL_FLAG}'!=resp&&'${GlobalConstant.OPRE_SUCCESSED_FLAG}'!=resp)
				{
					jboxTip(resp);
				}
			},null,false);
		},null);
	}
	function subPlan(planFlow)
	{
		jboxConfirm("提交后，无法修改！确认提交？",function(){
			var url = "<s:url value='/res/deptActivity/subPlan'/>?planFlow="+planFlow;
			jboxPost(url,null,function(resp){
				if('${GlobalConstant.OPRE_SUCCESSED_FLAG}'==resp){
					jboxTip("${GlobalConstant.OPRE_SUCCESSED}");
					toPage(1);
				}
				if('${GlobalConstant.OPRE_FAIL_FLAG}'==resp){
					jboxTip("${GlobalConstant.OPRE_FAIL}");
				}
				if('${GlobalConstant.OPRE_FAIL_FLAG}'!=resp&&'${GlobalConstant.OPRE_SUCCESSED_FLAG}'!=resp)
				{
					jboxTip(resp);
				}
			},null,false);
		},null);
	}
</script>

<div class="mainright" style="min-height: 300px;">
	<div class="content">
		<div class="title1 clearfix">
			<form id="searchForm">
				<input type="hidden" id="currentPage" name="currentPage"/>
				<div class="queryDiv">
					<div class="inputDiv">
						<label class="qlable">科&#12288;&#12288;室：</label>
						<select id="deptFlow" name="deptFlow" class="qselect">
							<option></option>
							<c:forEach items="${deptList}" var="dept">
								<option value="${dept.deptFlow}" >${dept.deptName}</option>
							</c:forEach>
						</select>
					</div>
					<div class="inputDiv">
						<label class="qlable">科室类型：</label>
						<select id="planTypeId" name="planTypeId" class="qselect">
							<option></option>
							<c:forEach var="dict" items="${dictTypeEnumDeptActivityTypeList}">
								<option value="${dict.dictId}">${dict.dictName}</option>
							</c:forEach>
						</select>
					</div>
					<div class="inputDiv">
						<label class="qlable">月&#12288;&#12288;度：</label>
						<input type="text"  onclick="WdatePicker({dateFmt:'yyyy-MM'})" name="planDate" class="qtext" readonly="readonly"/>
					</div>
					<div class="lastDiv" style="max-width: 200px;min-width: 200px;">
						<input class="searchInput" type="button" value="查&#12288;询" onclick="toPage(1);"/>
						<c:if test="${role eq 'head'}">
							<input class="searchInput" type="button" value="新&#12288;增" onclick="addPlan('');"/>
						</c:if>
					</div>
				</div>
			</form>
		</div>
		<div id="doctorListZi">
		</div>
	</div>
</div>
</html>