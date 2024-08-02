<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
</jsp:include>
<script type="text/javascript">
function delTarget(targetFlow)
{
	var url = '<s:url value="/res/activityTarget/delTarget"/>?targetFlow='+targetFlow;
	jboxConfirm("确认删除？" , function(){
		jboxPost(url , null , function(resp){
			toPage(1);
		} , null , true);
	});
}
</script>
		<table class="xllist">
        	<colgroup>
				<col width="10%" />
				<col width="80%" />
				<col width="10%" />
			</colgroup>
            <tr>
                <th>序号</th>
                <th>评价指标</th>
                <th>操作</th>
            </tr>
             <c:forEach items="${targets}" var="target" varStatus="s">
	             <tr>
	                <td>${(currentPage-1)*currentPageSize+s.count}</td>
	                <td>${target.targetName}</td>
	          		<td>
						<a style="cursor: pointer;" href="javascript:void(0);" onclick="add('${target.targetFlow}')">编辑</a>
						<a style="cursor: pointer;" href="javascript:void(0);" onclick="delTarget('${target.targetFlow}')">删除</a>
	          		</td>
	            </tr>
            </c:forEach>
            <c:if test="${empty targets}">
				<tr>
					<td colspan="10" >无记录！</td>
				</tr>
      	  	</c:if>
        </table>
		<c:set var="pageView" value="${pdfn:getPageView(targets)}" scope="request"></c:set>
		<pd:pagination toPage="toPage"/>
