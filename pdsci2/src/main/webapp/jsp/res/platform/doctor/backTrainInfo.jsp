<head>
<style type="text/css">
	#boxHome .item:HOVER{background-color: #eee;}
</style>
<script type="text/javascript">
/**
 *模糊查询加载
 */
(function($){
	$.fn.likeSearchInit = function(option){
		option.clickActive = option.clickActive || null;

		var searchInput = this;
		searchInput.on("keyup focus",function(){
			$("#boxHome").show();
			if($.trim(this.value)){
				$("#boxHome .item").hide();
				var items = $("#boxHome .item[value*='"+this.value+"']").show();
				if(!items){
					$("#boxHome").hide();
				}
			}else{
				$("#boxHome .item").show();
			}
		});
		searchInput.on("blur",function(){
			if(!$("#boxHome.on").length){
				$("#boxHome").hide();
			}
		});
		$("#boxHome").on("mouseenter mouseleave",function(){
			$(this).toggleClass("on");
		});
		$("#boxHome .item").click(function(){
			$("#boxHome").hide();
			var value = $(this).attr("value");
			$("#itemName").val(value);
			searchInput.val(value);
			if(option.clickActive){
				option['clickActive']($(this).attr("flow"));
			}
		});
	};
})(jQuery);
//======================================
//页面加载完成时调用
$(function(){
	$("#orgSel").likeSearchInit({
		clickActive:function(flow){
			$("."+flow).show();
		}
	});
});
function toPage(page) {
	var currentPage="";
	if(!page||page!=undefined){
		currentPage=page;
	}
	if(page==undefined||page==""){
		currentPage=1;
	}
	$("#currentPage").val(currentPage);
	searchRecInfo();
}
function searchRecInfo(){
	jboxStartLoading();
	var url ="<s:url value='/res/doc/backTrainInfo'/>"
	jboxPostLoad("tagContent",url,$("#searchForm").serialize(),false);
}
function exportExcel(){
	var url = "<s:url value='/res/doc/exportForBack?flag=Y'/>";
	jboxExp($("#searchForm"),url);
}
</script>
</head>
<body>
<%--<c:if test="${GlobalConstant.USER_LIST_PERSONAL != sessionScope.userListScope and param.seeFlag !=GlobalConstant.FLAG_Y}">--%>
	<%--<div class="main_hd">--%>
	    <%--<h2 class="underline">医师退培管理</h2> --%>
	<%--</div>--%>
<%--</c:if>--%>
<div class="mainright" style="min-height: 300px;">
	<div class="content">
		<div class="title1 clearfix">
			<form id="searchForm" action="<s:url value="/res/doc/backTrainCheck" />" method="post">
				<input id="currentPage" type="hidden" name="currentPage" value=""/>
				<input type="hidden" name="roleFlag" value="${roleFlag}"/>
				<div class="queryDiv">
					<div class="inputDiv">
						<label class="qlable">培训基地：</label>
						<input id="orgSel" class="toggleView qtext" type="text" name="orgName" value="${param.orgName}" autocomplete="off" placeholder="全部"/>
						<div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative;top:34px;left:100px;">
							<div id="boxHome" style="max-height: 250px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 220px;border-top: none;position: relative;display: none;">
								<c:forEach items="${orgs}" var="org">
									<p class="item" flow="${org.orgFlow}" value="${org.orgName}" style="text-align: left;height: 30px;padding-left: 10px;width: 100%;">${org.orgName}</p>
								</c:forEach>
							</div>
						</div>
					</div>
					<c:if test="${GlobalConstant.USER_LIST_UNIVERSITY ne roleFlag}">
					<div class="inputDiv">
						<label class="qlable">学员类型：</label>
						<select name="doctorTypeId" class="qselect">
							<option value="">全部</option>
							<c:forEach items="${resDocTypeEnumList}" var="type">
								<option value="${type.id}" ${param.doctorTypeId eq type.id?'selected':''}>${type.name}</option>
							</c:forEach>
						</select>
					</div>
					</c:if>
					<div class="inputDiv">
						<label class="qlable">年&#12288;&#12288;级：</label>
						<input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}"
							   readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy'})" class="qtext" />
						<%--<select name="sessionNumber" class="qselect">--%>
							<%--<option value="">全部</option>--%>
							<%--<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict" varStatus="num">--%>
								<%--<option value="${dict.dictName}" ${param.sessionNumber eq dict.dictName?'selected':''}>${dict.dictName}</option>--%>
							<%--</c:forEach>--%>
						<%--</select>--%>
					</div>
					<div class="inputDiv" style="min-width: 275px;max-width: 275px;">
						<label class="qlable">退培主要原因：</label>
						<select class="qselect" id="reasonId" name="reasonId">
							<option value="">请选择</option>
							<option value="1"<c:if test="${param.reasonId eq 1}">selected="selected"</c:if>>辞职</option>
							<option value="2"<c:if test="${param.reasonId eq 2}">selected="selected"</c:if>>考研</option>
							<option value="3"<c:if test="${param.reasonId eq 3}">selected="selected"</c:if>>其他</option>
						</select>
					</div>
					<div class="inputDiv">
						<label class="qlable">退培类型：</label>
						<select class="qselect" id="policyId" name="policyId">
							<option value="">请选择</option>
							<option value="1"<c:if test="${param.policyId eq 1}">selected="selected"</c:if>>协议退培</option>
							<option value="2"<c:if test="${param.policyId eq 2}">selected="selected"</c:if>>违约退培</option>
						</select>
					</div>
					<div class="inputDiv">
						<label class="qlable">专&#12288;&#12288;业：</label>
						<input type="text"  value="${param.trainingSpeName}" name="trainingSpeName" class="qtext "/>
					</div>
					<div class="inputDiv">
						<label class="qlable">审核结果：</label>
						<select name="auditStatusId"  class="qselect">
							<option value="">全部</option>
							<option value="${resBaseStatusEnumGlobalPassed.id}" <c:if test="${param.auditStatusId eq resBaseStatusEnumGlobalPassed.id}">selected="selected"</c:if>>同意</option>
							<option value="${resBaseStatusEnumGlobalNotPassed.id}" <c:if test="${param.auditStatusId eq resBaseStatusEnumGlobalNotPassed.id}">selected="selected"</c:if>>不同意</option>
						</select>
					</div>
					<div class="lastDiv" style="min-width: 175px;max-width: 175px;">
						<input class="search" type="button" onclick="searchRecInfo()" value="查&#12288;询"/>
						<input class="search" type="button" value="导&#12288;出" onclick="exportExcel();"/>
					</div>
				</div>
		 </form>
	 	</div>
        <table class="xllist">
			<colgroup>
				<col width="12%"/>
				<col width="5%"/>
				<col width="6%"/>
				<col width="12%"/>
				<col width="8%"/>
				<col width="10%"/>
				<col width="8%"/>
				<col width="12%"/>
				<col width="7%"/>
				<c:if test="${'N' ne sysCfgMap['res_return_audit']}">
				<col width="10%"/>
				<col width="10%"/>
				</c:if>
			</colgroup>
        	<tr>
        		<th>培训基地</th>
        		<th>年级</th>
        		<th>学员姓名</th>
        		<th>专业</th>
        		<th>退培主要原因</th>
        		<th>退培类型</th>
        		<th>学员去向</th>
        		<th>备注(培训基地意见)</th>
				<th>附件</th>
				<c:if test="${'N' ne sysCfgMap['res_return_audit']}">
				<th>省厅审核结果</th>
				<th>省厅审核意见</th>
				</c:if>
        	</tr>
			<c:forEach items="${resRecList}" var="rec">
				<tr>
					<td title="${rec.orgName}">
							${pdfn:cutString(rec.orgName,8,true,3)}
					</td>
					<td>${rec.sessionNumber}</td>
					<td>${rec.doctorName}</td>
					<td title="${rec.trainingSpeName}">
							${pdfn:cutString(rec.trainingSpeName,10,true,3) }
					</td>
					<td>${rec.reasonName}
						<label title="${rec.reason}">
							<c:if test="${not empty rec.reason }">
								(${pdfn:cutString(rec.reason,2,true,3) })
							</c:if>
						</label>
					</td>
					<td>${rec.policyName}
						<label title="${rec.policy }">
							<c:if test="${not empty rec.policy }">
								(${pdfn:cutString(rec.policy,2,true,3) })
							</c:if>
						</label>
					</td>
					<td><label title="${rec.dispositon}">${pdfn:cutString(rec.dispositon,5,true,3) }</label></td>
					<td><label title="${rec.remark}">${pdfn:cutString(rec.remark,8,true,3) }</label></td>
					<td>
						<c:forEach items="${fileMaps[rec.recordFlow]}" var="file">
							<a href="<s:url value='/res/doc/fileDown'/>?fileFlow=${file.fileFlow}">[下载附件]</a>
						</c:forEach>
					</td>
					<c:if test="${'N' ne sysCfgMap['res_return_audit']}">
					<td>${rec.auditStatusName}</td>
					<td title="${rec.auditOpinion}">${pdfn:cutString(rec.auditOpinion,8,true,3)}</td>
					</c:if>
				</tr>
			</c:forEach>
        	<c:if test="${empty resRecList}">
        		<tr>
        			<td colspan="11">无记录</td>
        		</tr>
        	</c:if>
        </table>
		<c:set var="pageView" value="${pdfn:getPageView(resRecList)}" scope="request"></c:set>
		<pd:pagination toPage="toPage"/>
	</div>
	</div>
</body>