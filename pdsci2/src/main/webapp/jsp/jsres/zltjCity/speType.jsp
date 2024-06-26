
<script type="text/javascript" src="<s:url value='/js/Scoll/Scorll2.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script>
	$(document).ready(function(){
		var style={"margin-left":"0px","width":"940px","height":"640px"};
		var options ={
			"colums":1//根据固定列的数量
		};
		$("#dataTable").Scorll(options,style,true,null);

	});
</script>
<div class="search_table">
		   <table border="0" cellpadding="0" cellspacing="0" id="dataTable" style="width:auto;" class="grid">
			   <thead>
			   <tr>
				   <th style="min-width: 60px; max-width: 60px; "   class="toFiexdDept">专业</th>
				   <c:forEach items="${citys}" var="city">
					   <c:if test="${empty param.orgCityId or city.cityId eq param.orgCityId}">
						   <c:if test="${city.cityName eq '连云港市'}">
							   <th style="min-width: 60px; max-width: 60px; "   class="toFiexdDept">${city.cityName}</th>
						   </c:if>
						   <c:if test="${city.cityName ne '连云港市'}">
							   <th style="min-width: 50px; max-width: 50px; "   class="toFiexdDept">${city.cityName}</th>
						   </c:if>
					   </c:if>
				   </c:forEach>
				   <th style="min-width: 50px; max-width: 50px; "   class="toFiexdDept">合计</th>
			   </tr>
			   </thead>
			   <tbody>
			   <c:set var="hjsum" value="0"></c:set>
			   <c:forEach items="${spes}" var="spe">
				   <c:if test="${empty param.trainingSpeId or spe.dictId eq param.trainingSpeId}">
						<tr>
							<td style="min-width: 60px; max-width: 60px; " class="by">
								${spe.dictName}
							</td>
							<c:set var="sum" value="0"></c:set>
							<c:forEach items="${citys}" var="city">
								<c:if test="${empty param.orgCityId or city.cityId eq param.orgCityId}">
									<c:set var="key" value="${city.cityId}${spe.dictId}"></c:set>
									<c:set var="sum" value="${sum+cityTypeNumMap[key]}"></c:set>

									<c:if test="${city.cityName eq '连云港市'}">
										<td style="min-width: 60px; max-width: 60px; " class="by">${empty cityTypeNumMap[key]?0:cityTypeNumMap[key]}</td>
									</c:if>
									<c:if test="${city.cityName ne '连云港市'}">
										<td style="min-width: 50px; max-width: 50px; " class="by">${empty cityTypeNumMap[key]?0:cityTypeNumMap[key]}</td>
									</c:if>
								</c:if>
							</c:forEach>
							<td style="min-width: 50px; max-width: 50px; " class="by">
									${typeNumMap[spe.dictId]+0}
								<c:set var="hjsum" value="${hjsum+typeNumMap[spe.dictId]}"></c:set>
							</td>
						</tr>
				   </c:if>
			   </c:forEach>
			   <tr>
				   <td style="min-width: 60px; max-width: 60px; " class="by">合计</td>
                   <c:set var="hjsum2" value="0"></c:set>
				   <c:forEach items="${citys}" var="city">
					   <c:if test="${empty param.orgCityId or city.cityId eq param.orgCityId}">

						   <c:if test="${city.cityName eq '连云港市'}">
							   <td style="min-width: 60px; max-width: 60px; " class="by">${empty cityNumMap[city.cityId]?0:cityNumMap[city.cityId]}</td>
						   </c:if>
						   <c:if test="${city.cityName ne '连云港市'}">
							   <td style="min-width: 50px; max-width: 50px; " class="by">${empty cityNumMap[city.cityId]?0:cityNumMap[city.cityId]}</td>
						   </c:if>
					   </c:if>
                       <c:set var="hjsum2" value="${hjsum2+cityNumMap[city.cityId]}"></c:set>
				   </c:forEach>
				   <td style="min-width: 50px; max-width: 50px; " class="by">${hjsum2}</td>
			   </tr>
			   </tbody>
		   </table>
	   </div>