
<style type="text/css">
	.selSchDept span{color: blue;}
</style>
<script type="text/javascript">
	function viewTime(schDeptFlow){
		if(schDeptFlow){
			$(".selSchDept").removeClass("selSchDept");
			$(".TR_"+schDeptFlow).addClass("selSchDept");
		}else{
			$(".selSchDept").removeClass("selSchDept");
		}

		var recordFlow =  $(".selSysDept").attr("recordFlow") || "";
		jboxPostLoad("schExternalDeptTimes" ,"<s:url value='/sch/schExtDept/schExternalDeptTimes'/>?schDeptFlow="+schDeptFlow+"&recordFlow="+recordFlow ,null , true);
	}
</script>
<table class="basic" style="width: 100%">
	<c:forEach items="${schDepts}" var="dept">
		<tr class="viewTd TR_${dept.schDeptFlow}"  schDeptFlow="${dept.schDeptFlow}" >
			<td style="width: 100%;text-align: left;border-left: 0px;border-right: 0px;border-top: 0px;" >
				<span onclick="viewTime('${dept.schDeptFlow}');" style="cursor: pointer;">${dept.schDeptName}（${dept.orgName}）</span>
			</td>
		</tr>
	</c:forEach>
</table>