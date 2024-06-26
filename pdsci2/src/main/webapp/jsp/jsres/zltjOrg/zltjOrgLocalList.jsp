
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
		   <table border="0" cellpadding="0" cellspacing="0" id="dataTable" style="width:100%;" class="grid">
			   <thead>
			   <tr>
				   <th style="min-width: 150px; max-width: 150px; "   class="toFiexdDept">专业</th>
				   <c:forEach items="${jsResDocTypeEnumList}" var="type">
					   <th style="min-width: 120px; max-width: 120px; " class="toFiexdDept">${type.name}</th>
				   </c:forEach>
				   <th style="min-width: 60px; max-width: 60px; "   class="toFiexdDept">合计</th>
			   </tr>
			   </thead>
			   <tbody>
			   <c:set var="hjsum" value="0"></c:set>
			   <c:forEach items="${spes}" var="spe">
				   <c:if test="${empty param.trainingSpeId or spe.dictId eq param.trainingSpeId}">
					   <c:if test="${spe.dictId ne '50'}">
						   <tr>
							   <td style="min-width: 150px; max-width: 150px; " class="by">
									   ${spe.dictName}
							   </td>
							   <c:forEach items="${jsResDocTypeEnumList}" var="type">
								   <c:set var="key" value="${type.id}${spe.dictId}"></c:set>
								   <c:set var="sum" value="${sum+typeSpeNumMap[key]}"></c:set>
								   <td style="min-width: 120px; max-width: 120px; " class="by">${empty typeSpeNumMap[key]?0:typeSpeNumMap[key]}</td>
							   </c:forEach>
							   <td style="min-width: 60px; max-width: 60px; " class="by">
									   ${speNumMap[spe.dictId]+0}
								   <c:set var="hjsum" value="${hjsum+speNumMap[spe.dictId]}"></c:set>
							   </td>
						   </tr>
					   </c:if>
				   </c:if>
			   </c:forEach>
			   <tr>
				   <td style="min-width: 150px; max-width: 150px; " class="by">合计</td>
				   <c:forEach items="${jsResDocTypeEnumList}" var="type">
					   <td style="min-width: 120px; max-width: 120px; " class="by">${empty typeNumMap[type.id]?0:typeNumMap[type.id]}</td>
				   </c:forEach>
				   <td style="min-width: 60px; max-width: 60px; " class="by">${hjsum}</td>
			   </tr>
			   </tbody>
		   </table>
	   </div>