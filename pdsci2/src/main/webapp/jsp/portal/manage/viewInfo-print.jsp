
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<script src="<s:url value='/js/jquery-1.9.1.min.js'/>" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function () {
	window.print();
})
</script>
	<style type="text/css">

		table.gridtable {
			font-family: verdana,arial,sans-serif;
			font-size:11px;
			color:#333333;
			border-width: 1px;
			border-color: #666666;
			border-collapse: collapse;
		}
		table.gridtable th {
			border-width: 1px;
			padding: 5px;
			border-style: solid;
			border-color: #666666;
			background-color: #dedede;
			text-align: right;
		}
		table.gridtable td {
			border-width: 1px;
			padding: 5px;
			border-style: solid;
			border-color: #666666;
			background-color: #ffffff;
			text-align: left;
		}
	</style>
</head>
<body>
<div id="printDiv" style="overflow-y: auto;overflow-x: visible;">
   		<table class="gridtable" style="width:97%; margin-top: 3px; margin-bottom: 10px;">
   			<tr>
                <td class="bs_name" colspan="4">资讯信息：</td>
            </tr>
            <tr>
                <th class="td_blue" width="100px">标&#12288;&#12288;题：</th>
                <td colspan="3">
                	${info.infoTitle }
                </td>
            </tr>
			<tr>
				<th class="td_blue">所属系统：</th>
				<td colspan="3">
					<c:forEach items="${sysRoleList}" var="role">
						<c:if test="${fn:contains(roleFlows.roleName, role.roleFlow) or sessionScope.currUser.userCode==GlobalConstant.ROOT_USER_CODE}">
							<c:if test="${info.roleFlow eq  role.roleFlow}">${role.roleName}</c:if>
						</c:if>
					</c:forEach>
				</td>
			</tr>
            <tr>
                <th class="td_blue">所属栏目：</th>
                <td colspan="3" id="column">
                	<c:if test="${info.column != null }"> ${info.column.columnName } </c:if>
                </td>
            </tr>
      		<tr>
             	<th width="13%">标题图片：</th>
                <td width="37%"><c:if test="${info.titleImg!=null&&info.titleImg!=''}" ><a href="${imageBaseUrl}${info.titleImg}" target="_blank" title="点击查看大图"><img src="${imageBaseUrl}${info.titleImg}" width="150px"/></a></c:if></td>          
                <th width="13%">资讯时间：</th>
                <td width="37%">
                     ${info.infoTime}
                </td>                                    
             </tr>
             <tr>
             	<th>关键字：</th>
                <td>
                	${info.infoKeyword}
				</td>
                          
                <th>是否置顶：</th>
                <td>
                     <c:if test="${info.isTop==GlobalConstant.RECORD_STATUS_Y }" >是</c:if><c:if test="${info.isTop==GlobalConstant.RECORD_STATUS_N }" >否</c:if>
                </td>                                    
             </tr>
             <tr>
                <th class="td_blue">资讯内容：</th>
                <td colspan="3">
                	${info.infoContent}
                </td>
            </tr>
		</table>
	</div>
</body>
</html>