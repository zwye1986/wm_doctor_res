<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<script type="text/javascript">
	function toPage(page){
		$("#currentPage").val(page);
		$("#searchForm").submit();
	}
	function addInfo(rargetFlow){
		var title = rargetFlow == ""?"新增":"编辑";
		var url = "<s:url value='/gzykdx/school/addRecruitPlan?rargetFlow='/>"+rargetFlow;
		jboxOpen(url, title,600,360,true);
	}
	function releasedInfo(){
		jboxConfirm("确认发布今年下所有二级机构学校审核通过的招生指标？", function(){
			var url = "<s:url value='/gzykdx/school/releasedTarget?recruitYear='/>"+$("#recruitYear").val();
			jboxPost(url, null, function(resp){
				if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
					location.reload();
				}
			});
		});
	}
	function exportInfo(){
		var url = "<s:url value='/gzykdx/schoolAudit/exportRecruitCatalog'/>";
		jboxTip("导出中……");
		jboxSubmit($("#searchForm"), url, null, null, false);
		jboxEndLoading();
	}
	function editDataInfo(year,applyFlow,orgFlow,type,val,obj){
		if (false == $("#dataForm").validationEngine("validate")) {
			return false;
		}
		var page = '${currentPage}';
		var url = "<s:url value='/gzykdx/school/modifyRecruitPlan?year='/>"+year+"&applyFlow="+applyFlow+"&orgFlow="+orgFlow+"&type="+type+"&val="+val;
		jboxPost(url, null, function(resp){
			if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
				jboxTip(resp);
				setTimeout(function(){
					window.parent.frames['mainIframe'].window.toPage(page);
				},1000);
			}
			if (resp == "${GlobalConstant.NULL}") {
				jboxTip("保存失败！编辑人数大于指标剩余人数！");
				setTimeout(function(){
					window.parent.frames['mainIframe'].window.toPage(page);
				},1000);
			}
		},null,false);
	}

</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<form id="searchForm" action="<s:url value="/gzykdx/school/recruitCatalogRelease"/>" method="post">
			<input type="hidden" id="currentPage" name="currentPage">
			<div class="choseDivNewStyle">
				<span></span>年&#12288;&#12288;份：
				<input type="text" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy'})" id="recruitYear" name="recruitYear" style="width:137px;" value="${empty param.recruitYear?currentYear:param.recruitYear}"/>
				<span style="padding-left:20px;"></span>机构名称：
				<select style="width:140px;" name="orgFlow" >
					<option value=""></option>
					<c:forEach var="org" items="${applicationScope.sysOrgList}">
						<c:if test="${org.isSecondFlag eq 'Y'}">
							<option value="${org.orgFlow}" <c:if test="${param.orgFlow eq org.orgFlow}">selected="selected"</c:if>>${org.orgName}</option>
						</c:if>
					</c:forEach>
				</select>
				<span style="padding-left:20px;"></span>姓名：
				<input type="text" name="userName" style="width:140px;" value="${param.userName}">
				<div class="newStyleSubDiv">
					<span style="padding-left:8px;"></span>专业名称：
					<select style="width:140px;" name="speId" >
						<option value=""></option>
						<c:forEach var="dict" items="${dictTypeEnumGzykdxSpeList}">
							<option value="${dict.dictId}" <c:if test="${param.speId eq dict.dictId}"> selected="selected"</c:if>>${dict.dictName}[${dict.dictId}]</option>
						</c:forEach>
					</select>
					<span style="padding-left:20px;"></span>研究方向：
					<input type="text" name="researchDirection" value="${param.researchDirection}" style="width:137px;"/>
					<%--<select style="width:140px;" name="researchDirectionId" >--%>
						<%--<option value=""></option>--%>
						<%--<c:forEach var="dict" items="${dictTypeEnumResearchAreaList}">--%>
							<%--<option value="${dict.dictId}" <c:if test="${param.researchDirectionId eq dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>--%>
						<%--</c:forEach>--%>
					<%--</select>--%>
					<span style="padding-left:20px;"></span>
					<input type="button" class="search" value="查&#12288;询" onclick="toPage(1)"/>
					<input type="button" class="search" value="发&#12288;布" onclick="releasedInfo()"/>
					<input type="button" class="search" value="导&#12288;出" onclick="exportInfo()"/>
					<font style="color:red;">*修改内容后需重新发布招生目录</font>
				</div>
			</div>
		</form>
		<form id="dataForm">
			<table class="xllist" style="width:100%;">
				<tr>
					<th style="min-width:80px;">年份</th>
					<th style="min-width:80px;">专业代码</th>
					<%--<th style="min-width:80px;">研究方向码</th>--%>
					<th style="min-width:100px;">专业名称</th>
					<th style="min-width:100px;">研究方向</th>
					<th style="min-width:100px;">导师</th>
					<th style="min-width:100px;">考试科目</th>
					<th style="min-width:100px;">导师单位</th>
					<th style="min-width:80px;">学术学位拟招生人数</th>
					<th style="min-width:80px;">专业学位拟招生人数</th>
					<th style="min-width:100px;">学位类型</th>
				</tr>
				<c:forEach items="${dataList}" var="info">
					<tr>
						<td>${info.RECRUIT_YEAR}</td>
						<td>${info.SPE_ID}</td>
						<%--<td>${info.RESEARCH_DIRECTION_ID}</td>--%>
						<td>${info.SPE_NAME}</td>
						<td>${info.RESEARCH_DIRECTION}</td>
						<td>${info.USER_NAME}</td>
						<td>${info.DICT_DESC}</td>
						<td>${info.ORG_NAME}</td>
						<td>
							<input value="${info.ACADEMIC_NUM}" onchange="editDataInfo('${info.RECRUIT_YEAR}','${info.APPLY_FLOW}','${info.ORG_FLOW}','academicNum',this.value,this)"
								   name="academicNum" style="width: 60px;" class="validate[custom[positiveNum],custom[integer]]]" <c:if test="${info.IS_ACADEMIC ne 'Y' && info.IS_SPECIALIZED eq 'Y'}">disabled="disabled"</c:if>/>
						</td>
						<td>
							<input value="${info.SPECIALIZED_NUM}" onchange="editDataInfo('${info.RECRUIT_YEAR}','${info.APPLY_FLOW}','${info.ORG_FLOW}','specializedNum',this.value,this)"
								   name="specializedNum" style="width: 60px;" class="validate[custom[positiveNum],custom[integer]]]" <c:if test="${info.IS_ACADEMIC eq 'Y' && info.IS_SPECIALIZED ne 'Y'}">disabled="disabled"</c:if>/>
						</td>
						<td>
							<c:if test="${info.IS_ACADEMIC eq 'Y' && info.IS_SPECIALIZED eq 'Y'}">学术/专业学位</c:if>
							<c:if test="${info.IS_ACADEMIC eq 'Y' && info.IS_SPECIALIZED ne 'Y'}">学术学位</c:if>
							<c:if test="${info.IS_ACADEMIC ne 'Y' && info.IS_SPECIALIZED eq 'Y'}">专业学位</c:if>
						</td>

					</tr>
				</c:forEach>
			</table>
			<div style="margin-top:65px;">
				<c:set var="pageView" value="${pdfn:getPageView(dataList)}" scope="request"/>
				<pd:pagination toPage="toPage"/>
			</div>
		</form>
	</div>
</div>
</body>	
</html>