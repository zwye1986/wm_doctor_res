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
			var url = "<s:url value='/res/doctorSignin/editAppeal'/>?roleFlag=doctor&recordFlow=" + recordFlow;
			jboxOpen(url, title+"申诉", 900, 400);
		}

		function delKq(recordFlow){
			jboxConfirm("确认删除申诉? " ,  function(){
				jboxStartLoading();
				var url = "<s:url value='/res/doctorSignin/delKq'/>?recordFlow=" + recordFlow;
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
		function revokeKq(recordFlow){
			jboxConfirm("确认撤销申诉? " ,  function(){
				jboxStartLoading();
				var url = "<s:url value='/res/doctorSignin/revokeKq'/>?recordFlow=" + recordFlow;
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
			<form id="searchForm" action="<s:url value="/res/doctorSignin/appealList"/>" method="post">
				<input type="hidden" id="currentPage" name="currentPage" value=""/>
				<div class="queryDiv">
					<div class="inputDiv">
						<label class="qlable">申诉时间：</label>
						<input type="text" class="qtime" name="startDate" value="${param.startDate}" onblur="calculateDay();" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
						~ <input type="text" class="qtime" name="endDate" value="${param.endDate}" onblur="calculateDay();" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  readonly="readonly"/>
					</div>
					<%--<div class="inputDiv">--%>
						<%--<label class="qlable">申诉类型：</label>--%>
						<%--<select name="typeId" class="qselect">--%>
							<%--<option value="">请选择</option>--%>
							<%--<c:forEach var="type" items="${dictTypeEnumAppealTypeList}">--%>
								<%--<option value="${type.dictId}" <c:if test="${param.typeId eq type.dictId}">selected="selected"</c:if> >${type.dictName}</option>--%>
							<%--</c:forEach>--%>
						<%--</select>--%>
					<%--</div>--%>
					<div class="inputDiv" style="text-align: left">&#12288;
						<input type="button" value="查&#12288;询" class="search" onclick="search();"/>
						<input type="button" value="新&#12288;增" class="search" onclick="editKq('');"/>
					</div>

				</div>
				<table class="xllist">
					<tr>
						<th width="13%">姓名</th>
						<%--<th width="20%">申诉类型</th>--%>
						<th width="27%">申诉时间</th>
						<th width="20%">申诉事由</th>
						<th width="15%">审核状态</th>
						<th width="10%">操作</th>
					</tr>
					<c:forEach items="${kqList}" var="kq">
						<tr>
							<td>${kq.doctorName}</td>
							<%--<td>${kq.typeName}</td>--%>
							<td>
									${kq.startDate}~${kq.endDate}
							</td>
							<td>${kq.doctorRemarks}</td>
							<td >${kq.auditStatusName}</td>
							<td>
								<c:if test="${kq.auditStatusId eq 'Auditing'}">
									<a href="javascript:revokeKq('${kq.recordFlow}');">撤销</a> |
									<a href="javascript:delKq('${kq.recordFlow}');">删除</a>
								</c:if>
								<c:if test="${kq.auditStatusId eq 'Revoke'}">
									<a href="javascript:delKq('${kq.recordFlow}');">删除</a>
								</c:if>
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