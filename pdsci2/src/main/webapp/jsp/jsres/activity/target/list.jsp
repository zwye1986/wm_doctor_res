<script type="text/javascript">
$(document).ready(function(){

});
function delTarget(targetFlow)
{
	var url = '<s:url value="/jsres/activityTarget/delTarget"/>?targetFlow='+targetFlow;
	jboxConfirm("确认删除？" , function(){
		jboxPost(url , null , function(resp){
			toPage(1);
		} , null , true);
	});
}
</script>
    <div class="search_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
        	<%--<colgroup>--%>
				<%--<col width="10%" />--%>
				<%--<col width="80%" />--%>
				<%--<col width="10%" />--%>
			<%--</colgroup>--%>
            <tr>
                <th>序号</th>
				<th>活动形式</th>
                <th>评价指标</th>
                <th>操作</th>
            </tr>
             <c:forEach items="${targets}" var="target" varStatus="s">
	             <tr>
	                 <td>${(currentPage-1)*currentPageSize+s.count}</td>
					 <td>${target.activityTypeName}</td>
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
    </div>
    <div class="page" style="padding-right: 40px;">
		<c:set var="pageView" value="${pdfn:getPageView(targets)}" scope="request"></c:set>
		<pd:pagination-jsres toPage="toPage"/>	 
	</div>
      
