<table id="productTable" cellpadding="0" cellspacing="0" class="basic" style="width:100%; border-style: none;">
<c:forEach items="${productNameList }" var="productName">
	<tr>
		<td style="border: none;">名称：${productName }</td>
		<c:if test="${not empty productInsMap[productName] }">
			<td style="border: none;">
				<c:set var="product" value="${productInsMap[productName] }"/>
				<input type="hidden" name="productFlow" value="${product.productFlow}"/>
				<input type="hidden" name="productTypeId" value="${productTypeIdMap[productName] }"/>
				<input type="hidden" name="productTypeName" value="${productName}"/>
				安装地点：<input type="text" name="installPlace"  placeholder="原：${empty product.installPlace?'无':product.installPlace}" class="inputText validate[required]" style="width: 150px;text-align: left;"/>
			</td>
			<td style="border: none;">
				版本号：<input type="text" name="versions" placeholder="原：${empty product.versions?'无':product.versions}" class="inputText validate[required]" style="width: 100px;text-align: left;"/>
			</td>
		</c:if>
		<c:if test="${empty productInsMap[productName] }">
			<td style="border: none;">安装地点：无</td>
			<td style="border: none;">版本号：无</td>
		</c:if>
	</tr>
</c:forEach>
</table>

