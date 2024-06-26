<div class="search_table">
		   <table border="0" cellpadding="0" cellspacing="0" class="grid">
			   <tr>
				   <th>地市</th>
				   <c:forEach items="${jsResDocTypeEnumList}" var="type">
					   <th>${type.name}</th>
				   </c:forEach>
				   <th>合计</th>
			   </tr>
			   <c:set var="hjsum" value="0"></c:set>
			   <c:forEach items="${citys}" var="city">
				   <c:if test="${empty param.orgCityId or city.cityId eq param.orgCityId}">
						<tr>
							<td>
								${city.cityName}
							</td>
							<c:set var="sum" value="0"></c:set>
							<c:forEach items="${jsResDocTypeEnumList}" var="type">
								<c:set var="key" value="${city.cityId}${type.id}"></c:set>
								<c:set var="sum" value="${sum+cityTypeNumMap[key]}"></c:set>
								<td>${empty cityTypeNumMap[key]?0:cityTypeNumMap[key]}</td>
							</c:forEach>
							<td>
									${cityNumMap[city.cityId]+0}
								<c:set var="hjsum" value="${hjsum+cityNumMap[city.cityId]}"></c:set>
							</td>
						</tr>
				   </c:if>
			   </c:forEach>
			   <tr>
				   <td>合计</td>
				   <c:forEach items="${jsResDocTypeEnumList}" var="type">
					   <td>${empty typeNumMap[type.id]?0:typeNumMap[type.id]}</td>
				   </c:forEach>
				   <td>${hjsum}</td>
			   </tr>
		   </table>
	   </div>