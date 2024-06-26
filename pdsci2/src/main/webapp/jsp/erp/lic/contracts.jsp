 		        <c:forEach items="${contractList }" var="contract">
			        <tr>
			           <td><input type="checkbox" class="validate[required]" onchange="selectSingle(this);changeContract();" value="${contract.contractFlow }" name="contractFlow"></td>
			           <td>${customer.customerName }</td>
			           <td><a title="查看合同信息" href="javascript:contractInfo('${contract.contractFlow }');">${contract.contractNo }</a></td>
			           <td>
			              <c:forEach items="${productMap[contract.contractFlow] }" var="product" varStatus="num">
			                 <span id="${product.productTypeId }">${product.productTypeName }<c:if test="${num.count != fn:length(productMap[contract.contractFlow]) }">，</c:if></span>
			                 <c:if test="${product.productTypeId eq 'netexam' }">
			                   <input type="hidden" id="${contract.contractFlow }_regFileClientName" value="${product.regFileClientName }"/>
			                 </c:if>
			              </c:forEach>
			           </td>
			        </tr>
			        </c:forEach>
			        <c:if test="${empty contractList }">
			           <tr>
			             <td colspan="4">无记录</td>
			           </tr>
			        </c:if>

