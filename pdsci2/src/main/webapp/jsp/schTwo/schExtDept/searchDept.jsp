
<style type="text/css">
	.selSysDept span{color: red;}
</style>
<script type="text/javascript">
	function viewDept(recordFlow){
		if(recordFlow){
			$(".selSysDept").removeClass("selSysDept");
			$(".TR_"+recordFlow).addClass("selSysDept");
		}else{
			$(".selSysDept").removeClass("selSysDept");
		}
		$("#schExternalDeptTimes").html("");
		jboxPostLoad("schExternalDepts" ,"<s:url value='/sch/schExtDept/schRotationDepts'/>?recordFlow="+recordFlow ,null , true);
	}
</script>
<table class="basic" style="width: 100%">
	<c:set var="isWeek" value="${sysCfgMap['res_rotation_unit'] eq schUnitEnumWeek.id}"/>
	<c:forEach items="${depts}" var="dept">
		<tr class="viewTd TR_${dept.recordFlow}"  recordFlow="${dept.recordFlow}" >
			<td style="width: 100%;text-align: left;border-left: 0px;border-right: 0px;border-top: 0px;" >
				<span onclick="viewDept('${dept.recordFlow}');" style="cursor: pointer;">${dept.standardDeptName}（${dept.schMonth}
					<c:if test="${!isWeek}">
						${schUnitEnumMonth.name}
					</c:if>
					<c:if test="${isWeek}">
						${schUnitEnumWeek.name}
					</c:if>
					）</span>
			</td>
		</tr>
	</c:forEach>
</table>