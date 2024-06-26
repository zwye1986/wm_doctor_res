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
	<style type="text/css">
		table.xllist a{color: blue;}
	</style>
	<script type="text/javascript">
		function editKq(recordFlow){
			var title = "新增";
			if(recordFlow){
				title = "修改";
			}
			var url = "<s:url value='/res/qingpuKq/editLeave'/>?roleFlag=doctor&recordFlow=" + recordFlow;
			jboxOpen(url, title+"请假", 900, 400);
		}

		function delKq(recordFlow){
			jboxConfirm("确认删除请假? " ,  function(){
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

		function search(){
			var form = $("#searchForm");
			form.submit();
		}

		function toPage(page){
			if (page) {
				$("#currentPage").val(page);
			}
			search();
		}
		function calculateDay(){
			var startDate = $("input[name='startDate']").val();
			var endDate = $("input[name='endDate']").val();
			if("" == startDate || "" == endDate){
				return;
			}
			if(endDate < startDate){
				jboxTip("结束日期不能早于开始日期！");
				return;
			}
		}
		function exportDoc(recordFlow){
			var url = '<s:url value="/res/qingpuKq/exportDoc"/>?recordFlow='+recordFlow;
			jboxTip("打印中,请稍等...");
			window.location.href = url;
		}
	</script>

</head>
<body>
<div class="mainright">
	<div class="content">
		<div class="title1 clearfix">
			<form id="searchForm" action="<s:url value="/res/qingpuKq/leaveList"/>" method="post">
				<input type="hidden" id="currentPage" name="currentPage" value=""/>
				<div class="queryDiv">
					<div class="inputDiv">
						<label class="qlable">请假时间：</label>
						<input type="text" class="qtime" name="startDate" value="${param.startDate}" onblur="calculateDay();" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
						~ <input type="text" class="qtime" name="endDate" value="${param.endDate}" onblur="calculateDay();" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  readonly="readonly"/>
					</div>
					<div class="inputDiv">
						<label class="qlable">请假类型：</label>
						<select name="qingpuKqTypeDetailId" class="qselect">
							<option value="">请选择</option>
							<c:forEach var="type" items="${qingpuKqLeaveTypeEnumList}">
								<option value="${type.id}" <c:if test="${param.qingpuKqTypeDetailId eq type.id}">selected="selected"</c:if> >${type.name}</option>
							</c:forEach>
						</select>
					</div>
					<%--<div class="inputDiv">--%>
						<%--<label class="qlable">姓&#12288;&#12288;名：</label>--%>
						<%--<input type="text" class="qselect" name="doctorName" value="${param.doctorName}">--%>
					<%--</div>--%>
					<div class="inputDiv" style="text-align: left">&#12288;
						<input type="button" value="查&#12288;询" class="search" onclick="search();"/>
						<input type="button" value="新&#12288;增" class="search" onclick="editKq('');"/>
					</div>

				</div>
				<div style="float: right;margin: 0px auto 10px auto">
					<span>
					<img src="<s:url value='/css/skin/${skinPath}/images/query.gif'/>"/>&nbsp;待审核&#12288;
					<img src="<s:url value='/css/skin/${skinPath}/images/gou.png'/>"/>&nbsp;审核通过&#12288;
					<img src="<s:url value='/css/skin/${skinPath}/images/del3.png'/>"/>&nbsp;审核不通过&#12288;
					</span>
				</div>
				<table class="xllist">
					<tr>
						<th width="7%">姓名</th>
						<th width="7%">请假类型</th>
						<th width="17%">请假事由</th>
						<th width="17%">请假时间</th>
						<th width="10%">轮转科室</th>
						<th width="10%">带教老师</th>
						<th width="10%">科秘</th>
						<th width="10%">管理员</th>
						<th width="10%">操作</th>
					</tr>
					<c:forEach items="${kqList}" var="kq">
						<c:set var="disagreeFlag" value="false"/>
						<c:if test="${GlobalConstant.FLAG_N eq kq.teacherAgreeFlag or
						GlobalConstant.FLAG_N eq kq.headAgreeFlag or 
						GlobalConstant.FLAG_N eq kq.managerAgreeFlag
						}">
							<c:set var="disagreeFlag" value="true"/>
						</c:if>
						<tr>
							<td>${kq.doctorName}</td>
							<td>${kq.qingpuKqTypeDetailName }</td>
							<td>${kq.doctorRemarks}</td>
							<td>
									${kq.startDate}~${kq.endDate}<br/>
								<font style="font-weight: bold;">
									(${kq.intervalDays}天)
								</font>
							</td>
							<td style="text-align: center; ">${kq.schDeptName}</td>
							<td>
								<c:if test="${not empty kq.teacherAgreeFlag}">
									<c:if test="${GlobalConstant.FLAG_N eq kq.teacherAgreeFlag}">
										<img src="<s:url value='/css/skin/${skinPath}/images/del3.png'/>" alt="审核不通过"/>
									</c:if>
									<c:if test="${GlobalConstant.FLAG_Y eq kq.teacherAgreeFlag}">
										<img src="<s:url value='/css/skin/${skinPath}/images/gou.png'/>" alt="审核通过"/>
									</c:if>
									<a title="审核信息：${kq.teacherAuditInfo}">${kq.teacherName}</a>
								</c:if>
								<c:if test="${empty kq.teacherAgreeFlag and !disagreeFlag}">
									<img src="<s:url value='/css/skin/${skinPath}/images/query.gif'/>" alt="待审核"/>&nbsp;${kq.teacherName}
								</c:if>
							</td>
							<td>
								<c:if test="${not empty kq.headAgreeFlag}">
									<c:if test="${GlobalConstant.FLAG_N eq kq.headAgreeFlag}">
										<img src="<s:url value='/css/skin/${skinPath}/images/del3.png'/>" />
									</c:if>
									<c:if test="${GlobalConstant.FLAG_Y eq kq.headAgreeFlag}">
										<img src="<s:url value='/css/skin/${skinPath}/images/gou.png'/>" />
									</c:if>
									<a title="审核信息：${kq.headAuditInfo}">${kq.headName}</a>
								</c:if>
								<c:if test="${empty kq.headAgreeFlag and !disagreeFlag}">
									<img src="<s:url value='/css/skin/${skinPath}/images/query.gif'/>"/>&nbsp;${kq.headName}
								</c:if>
							</td>
							<td>
								<c:if test="${not empty kq.managerAgreeFlag}">
									<c:if test="${GlobalConstant.FLAG_N eq kq.managerAgreeFlag}">
										<img src="<s:url value='/css/skin/${skinPath}/images/del3.png'/>" />
									</c:if>
									<c:if test="${GlobalConstant.FLAG_Y eq kq.managerAgreeFlag}">
										<img src="<s:url value='/css/skin/${skinPath}/images/gou.png'/>" />
									</c:if>
									<a title="审核信息：${kq.managerAuditInfo}">${kq.managerName}</a>
								</c:if>
								<c:if test="${empty kq.managerAgreeFlag and !disagreeFlag}">
									<img src="<s:url value='/css/skin/${skinPath}/images/query.gif'/>"/>&nbsp;${kq.managerName}
								</c:if>
							</td>
							<td>
								<c:if test="${(empty kq.teacherAgreeFlag) and (empty kq.headAgreeFlag) and (empty kq.managerAgreeFlag)}">
									<a href="javascript:editKq('${kq.recordFlow}');">编辑</a> |
									<a href="javascript:delKq('${kq.recordFlow}');">删除</a> |
								</c:if>
								<a href="javascript:exportDoc('${kq.recordFlow}');">导出WORD</a>
							</td>
						</tr>
					</c:forEach>
					<c:if test="${empty kqList}">
						<tr>
							<td colspan="20">无记录</td>
						</tr>
					</c:if>
				</table>
			</form>
			<p>
				<c:set var="pageView" value="${pdfn:getPageView2(kqList, 10)}" scope="request"/>
				<pd:pagination toPage="toPage"/>
			</p>
		</div>
	</div>
</div>
</body>
</html>