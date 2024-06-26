
<style type="text/css">
	.selUser {background-color: #0a6999}
</style>
<script type="text/javascript">
	function viewUser(userFlow){
		$("#arrangeResults").html("");
		userFlow=userFlow||$(".selUser").attr("userFlow");
		if(userFlow){
			$(".viewTd").removeClass("selUser");
			$(".TR_" + userFlow).addClass("selUser");
			jboxPostLoad("arrangeResults" ,"<s:url value='/sch/arrangeImport/results'/>?doctorFlow="+userFlow,null , true);
		}
	}

</script>
<table class="basic" style="width: 100%">
	<c:forEach items="${users}" var="user">
		<tr class="viewTd TR_${user.sysUser.userFlow} "
			userName="${user.sysUser.userName}"
			userFlow="${user.sysUser.userFlow}"
			selDeptFlag="${user.selDeptFlag}"
			schFlag="${user.schFlag}"
			selOneFlag="${user.selOneFlag}"
			schOneFlag="${user.schOneFlag}"
			selTwoFlag="${user.selTwoFlag}"
			schTwoFlag="${user.schTwoFlag}"
			selThreeFlag="${user.selThreeFlag}"
			schThreeFlag="${user.schThreeFlag}"
			onclick="viewUser('${user.sysUser.userFlow}');" >
			<td style="width: 100%;text-align: left;border-left: 0px;border-right: 0px;border-top: 0px;" >
				<span >${user.sysUser.userName}</span>
			</td>
		</tr>
	</c:forEach>
</table>