<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
</jsp:include>
<div style="text-align: center">
<c:if test="${!empty titleMap }">
   	<table class="basic" width="97%" style="margin: 10px;" >
    		<tr>
	    		<c:forEach items="${titleMap}" var="rec" varStatus="a">
	    			<th style="text-align: center;padding: 0">${rec.title}</th>
    			</c:forEach>
    		</tr>
    		<c:forEach items="${resRecList}" var="res">
    			<tr>
    				<c:forEach items="${titleMap}" var="t" varStatus="a">
						<td style="text-align: center;padding: 0">${resRecMap[res.recFlow][t.name]}</td>
					</c:forEach>
    			</tr>
    		</c:forEach>
   	</table>
</c:if>
		    	<c:if test="${empty titleMap }">
		    		<table class="basic" width="97%" style="margin: 10px">
		    		<tr>
		    			<td colspan="10">暂无记录</td>
		    		</tr>
		    		</table>
				</c:if>
</div>