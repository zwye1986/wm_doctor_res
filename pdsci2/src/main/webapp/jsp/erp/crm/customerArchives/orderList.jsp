
					<table class="xllist">
						<colgroup>
							<col width="5%">
							<col width="10%">
							<col width="10%">
							<col width="10%">
							<col width="10%">
							<col width="10%">
							<col width="10%">
							<col width="10%">
							<col width="15%">
							<col width="10%">
						</colgroup>
				<thead>
				<tr>
					<th>序号</th>
					<th>联系单编号</th>
					<th>客户名称</th>
					<th>产品/项目名称</th>
					<th>需求事项</th>
					<th>接收人</th>
					<th>接收部门</th>
					<th>申请人</th>
					<th>申请时间</th>
					<th>审核状态</th>
				</tr>
				</thead>						
				<tbody>
				   <c:if test="${empty contactOrderList  }">
				      <tr><td colspan="10">无记录</td></tr>
				   </c:if>
				   <c:forEach items="${contactOrderList }" var="contactOrder" varStatus="num">
				      <tr class="contactTr" id="contactTr_${contactOrder.contactFlow }">
				        <td>${num.count }</td>
				        <td><a href="javascript:info('${contactOrder.contactFlow }');" >${contactOrder.contactNo }</a></td>
				        <td>${contactOrder.customerName }</td>
				        <td>
							${contactOrder.productTypeName }
							<c:if test="${(not empty contactOrder.productTypeName) and (not empty contactOrder.orderProductName) }">、</c:if>
							${contactOrder.orderProductName }
				        </td>
				        <td>${contactOrder.demandMatterName }</td>
				        <td>${contactOrder.receiveUserName }</td>
				        <td>${contactOrder.receiveDeptName}</td>
				        <td>${contactOrder.receiveUserName }</td>
				        <td>${contactOrder.applyDate }</td>
				        <td>${contactOrder.contactStatusName }</td>
				      </tr>
				   </c:forEach>
				</tbody>
			</table>