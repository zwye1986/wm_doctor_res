
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
<style type="text/css">
	table.xllist a{color: blue;cursor: pointer;}
</style>
<script type="text/javascript">

	function save(recordFlow, agreeFlag){
		var url = "<s:url value='/res/qingpuKq/saveKqInfo'/>?recordFlow="+recordFlow+"&agreeFlag="+agreeFlag+"&roleFlag="+'${roleFlag}';
		jboxOpen(url,"审核意见",500,300,true);
	}
	
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

	function editKq(recordFlow){
		var title = "新增";
		if(recordFlow){
			title = "修改";
		}
		var url = "<s:url value='/res/qingpuKq/editSubmit'/>?roleFlag=${roleFlag}&recordFlow=" + recordFlow;
		jboxOpen(url, title+"违纪登记", 900, 400);
	}

	function delKq(recordFlow){
		jboxConfirm("确认删除违纪记录? " ,  function(){
			jboxStartLoading();
			var url = "<s:url value='/res/qingpuKq/delKq'/>?recordFlow=" + recordFlow;
			jboxPost(url, null,
					function(resp){
						if("1" == resp){
							jboxTip("操作成功");
							search();
						}
					}, null, false);
		});
	}
</script>

</head>
<body>
	<div class="mainright">
		<div class="content">
			<div class="title1 clearfix">
				<form id="searchForm" action="<s:url value='/res/qingpuKq/submitList/${roleFlag}'/>" method="post">
					<input id="currentPage" type="hidden" name="currentPage" value="${param.currentPage}"/>
					<div class="queryDiv">
						<div class="inputDiv">
							<label class="qlable">姓&#12288;&#12288;名：</label>
							<input type="text" class="qtext" name="doctorName" value="${param.doctorName}"/>
						</div>
						<div class="inputDiv">
							<label class="qlable">违纪类型：</label>
							<select name="qingpuKqTypeDetailId" class="qselect">
								<option value="">请选择</option>
								<c:forEach var="type" items="${qingpuKqSubmitTypeEnumList}">
									<option value="${type.id}" <c:if test="${param.qingpuKqTypeDetailId eq type.id}">selected="selected"</c:if> >${type.name}</option>
								</c:forEach>
							</select>
						</div>
						<div class="inputDiv" style="min-width: 280px;max-width: 280px;">
							<label class="qlable">违纪时间：</label>
							<input type="text" class="qtime" name="startDate" value="${param.startDate}"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  readonly="readonly"/>
							~
							<input type="text" class="qtime" name="endDate" value="${param.endDate}"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  readonly="readonly"/>
						</div>
						<c:if test="${GlobalConstant.RES_ROLE_SCOPE_MANAGER==resRoleScope}">
						<div class="inputDiv">
							<label class="qlable">轮转科室：</label>
							<select name="schDeptFlow" class="qselect">
								<option value="">全部</option>
								<c:forEach items="${schDeptList}" var="schDetp">
									<option value="${schDetp.schDeptFlow}" <c:if test="${param.schDeptFlow eq schDetp.schDeptFlow}">selected="selected"</c:if>>${schDetp.schDeptName}</option>
								</c:forEach>
							</select>
						</div>
						</c:if>
						<div class="inputDiv" style="text-align: left">&#12288;
							<input type="button" value="查&#12288;询" class="search" onclick="search();"/>
							<c:if test="${roleFlag ne 'manager'}">
							<input type="button" value="新&#12288;增" class="search" onclick="editKq('');"/>
							</c:if>
						</div>
					</div>

				</form>
				<div style="float: right;margin: 0px auto 10px auto">
					<span>
					<img src="<s:url value='/css/skin/${skinPath}/images/query.gif'/>"/>&nbsp;待审核&#12288;
					<img src="<s:url value='/css/skin/${skinPath}/images/gou.png'/>"/>&nbsp;审核通过&#12288;
					<img src="<s:url value='/css/skin/${skinPath}/images/del3.png'/>"/>&nbsp;审核不通过&#12288;
					</span>
				</div>
				<div class="resultDiv">
				<table class="xllist">
					<tr>
						<th width="4%">姓名</th>
						<th width="5%">违纪类型</th>
						<th width="15%">违纪事由</th>
						<th width="12%">违纪时间</th>
						<th width="10%">轮转科室</th>
						<%--<th width="9%">违纪材料</th>--%>
						<th width="8%">报送人</th>
						<th width="8%">管理员</th>
						<c:if test="${'manager' ne roleFlag}">
						<th width="6%">操作</th>
						</c:if>
					</tr>
					<c:set var="emptyRecord" value="true"/>
					<c:forEach items="${kqList}" var="kq">
						<c:set var="emptyRecord" value="false"/>
						<tr>
							<td>${kq.doctorName}</td>
							<td>${kq.qingpuKqTypeDetailName}</td>
							<td>${kq.doctorRemarks}</td>
							<td>
								${kq.startDate}~${kq.endDate}<br/>
								<font style="font-weight: bold;">
									(${kq.intervalDays}天)
								</font>
							</td>
							<td>${kq.schDeptName}</td>
							<%--<td><a id="down" href='<s:url value="/gzzyjxres/doctor/fileDown?fileFlow=${kq.makingFile}"/>'>${fileMap[kq.makingFile].fileName}</a></td>--%>
							<td>
								<c:if test="${'teacher' eq roleFlag}">
									${kq.teacherName}
								</c:if>
								<c:if test="${'head' eq roleFlag}">
									${kq.headName}
								</c:if>
								<c:if test="${'manager' eq roleFlag}">
									${kq.teacherName}${kq.headName}
								</c:if>
							</td>
							<td>
								<c:if test="${not empty kq.managerAgreeFlag}">
									<c:if test="${GlobalConstant.FLAG_N eq kq.managerAgreeFlag}">
										<img src="<s:url value='/css/skin/${skinPath}/images/del3.png'/>" title="审核不通过"/>
									</c:if>
									<c:if test="${GlobalConstant.FLAG_Y eq kq.managerAgreeFlag}">
										<img src="<s:url value='/css/skin/${skinPath}/images/gou.png'/>" title="审核通过"/>
									</c:if>
									<a title="审核信息：${kq.managerAuditInfo}">${kq.managerName}</a>
								</c:if>
								<c:if test="${empty kq.managerAgreeFlag and 'manager' ne roleFlag}">
									<img src="<s:url value='/css/skin/${skinPath}/images/query.gif'/>" alt="待审核"/>&nbsp;${kq.managerName}
								</c:if>
								<c:if test="${empty kq.managerAgreeFlag && 'manager' eq roleFlag}">
									<a href="javascript:save('${kq.recordFlow}','${GlobalConstant.FLAG_Y}');">同意</a> |
									<a href="javascript:save('${kq.recordFlow}','${GlobalConstant.FLAG_N}');">不同意</a>
								</c:if>
							</td>
							<c:if test="${'manager' ne roleFlag}">
							<td>
								<c:if test="${empty kq.managerAgreeFlag}">
									<a href="javascript:editKq('${kq.recordFlow}');">编辑</a> |
									<a href="javascript:delKq('${kq.recordFlow}');">删除</a>
								</c:if>
							</td>
							</c:if>
						</tr>
					</c:forEach>

					<c:if test="${emptyRecord}">
						<tr>
							<td style="text-align: center" colspan="20">无记录</td>
						</tr>
					</c:if>
				</table>
				</div>
				<div class="resultDiv">
		             <c:set var="pageView" value="${pdfn:getPageView(kqList)}" scope="request"/>
					<pd:pagination toPage="toPage"/>
		        </div>
			</div>
		</div>
	</div>
</body>
</html>