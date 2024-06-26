<script type="text/javascript">
$(document).ready(function(){
	$(".show").on("mouseenter mouseleave",
		function(){
			$(".info",this).toggle(100);
		}
	);

});

</script>
<div class="search_table clearfix" style="margin-top: 20px;">
	<table border="0" style=" width:100%;text-align: left;" class="base_info" >
		<colgroup>
			<col width="25%"/>
			<col width="25%"/>
			<col width="25%"/>
			<col width="25%"/>
		</colgroup>
		<tbody>
		<tr>
			<c:forEach items="${list}" var="bean" varStatus="status">
				<c:set var="lastCount" value="${(status.count % 4) }"></c:set>
				<td style="text-align: left;">${bean.doctorName}<a href="<s:url value='/jsres/doctorScoreApply/showCertificate?doctorFlow=${bean.doctorFlow}'/>" onclick="" target="_blank">${bean.completeNo}</a></td>
				<c:if test="${ lastCount eq '0'}"></tr><tr></c:if>
			</c:forEach>
			<c:if test="${not empty list}">
				<c:if test="${ !(lastCount eq '0')}">
					<c:forEach  var="i" begin="1" end="${4-lastCount}" >
						<td style="text-align: center;">-</td>
					</c:forEach>
				</c:if>
			</c:if>
		</tr>
		<c:if test="${empty list}">
			<tr>
				<td colspan="5" >无记录！</td>
			</tr>
		</c:if>

		</tbody>
	</table>
</div>

