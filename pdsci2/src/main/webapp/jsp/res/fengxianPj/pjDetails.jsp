<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_form" value="true"/>
	</jsp:include>

	<script type="text/javascript">
		function addPj(){
			var typeId=$("select[name='recTypeId']").val();
			if(typeId==''||typeId==null){
				jboxTip("请选择表格类型！");
				return;
			}
			var url = "<s:url value='/res/fengxianPj/show360EvlForm'/>"+
					"?recTypeId="+typeId+
					"&roleFlag=${roleFlag}&openType=open"+
					"&processFlow=${processFlow}"+
					"&schDeptFlow=${schDeptFlow}"+
					"&userFlow=${operUserFlow}"+
					"&operUserFlow=${operUserFlow}";
			jboxOpen(url, "评价表单", 1000, 500);
		}
		function info(recFlow){
			var url = "<s:url value='/res/fengxianPj/show360EvlForm'/>"+
					"?recFlow="+recFlow+
					"&roleFlag=${roleFlag}&openType=open"+
					"&schDeptFlow=${schDeptFlow}"+
					"&processFlow=${processFlow}"+
					"&operUserFlow=${operUserFlow}";
			jboxOpen(url, "评价表单", 1000, 500);
		}
	</script>
</head>
<body>
<div class="mainright">
  <div class="content" style="margin-top: 10px;overflow: auto;">
	  <div style="width: 100%;text-align: left;margin-bottom: 6px;">
		  <label class="qlable">选取表格：</label>
		  <select name="recTypeId" class="qselect">
			  <option value="">请选择</option>
			  <c:forEach items="${recSkillTypeEnumList}" var="type">
				  <c:if test="${recForm eq type.type}">
					  <option value="${type.id}" >${type.name}</option>
				  </c:if>
			  </c:forEach>
		  </select>&emsp;
		  <input type="button" class="searchInput" onclick="addPj();" value="新增评价"/>
	  </div>
  	<table class="basic" width="100%">
		<tr>
			<th style="text-align: center;padding-right: 0px">姓名</th>
			<th style="text-align: center;padding-right: 0px">轮转科室</th>
			<th style="text-align: center;padding-right: 0px">表单名称</th>
			<th style="text-align: center;padding-right: 0px">评价详情</th>
		</tr>
		<c:forEach items="${dataList}" var="data">
			<tr>
				<td style="text-align: center;padding-left: 0px">${data.operUserName}</td>
				<td style="text-align: center;padding-left: 0px">${data.schDeptName}</td>
				<td style="text-align: center;padding-left: 0px">${data.recTypeName}</td>
				<td style="text-align: center;padding-left: 0px"><a style="color: blue;cursor: pointer" onclick="info('${data.recFlow}')">查看</a></td>
			</tr>
		</c:forEach>
		<c:if test="${empty dataList}">
			<tr>
				<td colspan="99" style="text-align: center;">无记录</td>
			</tr>
		</c:if>
  	</table>
	  <div style="text-align: center;margin-top: 10px;">
		  <input type="button"  value="关&#12288;闭" class="search" style="width: 70px"  onclick="jboxCloseMessager();" />
		  <%--<a style="color: blue;cursor: pointer;" onclick="jboxClose();">关闭</a>--%>
	  </div>
  </div>
</div>
</body>
</html>