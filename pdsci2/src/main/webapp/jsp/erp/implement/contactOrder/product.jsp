 <table cellpadding="0" cellspacing="0" class="basic" style="width:100%; border-style: none;">
 <c:if test="${not empty productNameList }">
    <c:forEach items="${productNameList }" var="productName" >
      	<tr>
      		<td style="border: none;text-align: right;">名称：</td>
      		<td style="border: none;text-align: left;">${productName }</td>
	      	<c:if test="${not empty productInsMap[productName] }">
	      		<td style="border: none;text-align: right;">安装地点：</td>
	      		<td style="border: none; text-align: left;">${empty productInsMap[productName].installPlace?'无':productInsMap[productName].installPlace }</td>
	      		<td style="border: none;text-align: right;">版本号：</td>
	      		<td style="border: none; text-align: left;">${empty productInsMap[productName].versions?'无':productInsMap[productName].versions }</td>
		 	</c:if>
	     	<c:if test="${empty productInsMap[productName] }">
	     		<td style="border: none;text-align: right;">安装地点：</td>
	     		<td style="border: none; text-align: left;">无</td>
	     		<td style="border: none;text-align: right;">版本号：</td>
	     		<td style="border: none; text-align: left;">无</td>
		 	</c:if>
     	</tr>
</c:forEach>
</c:if>
</table>