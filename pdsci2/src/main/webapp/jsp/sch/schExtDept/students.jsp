
<style type="text/css">
	.selUser {background-color: #0a6999}
</style>
<script type="text/javascript">
	function viewUser(userFlow){
		if(userFlow){
			if ($(".TR_"+userFlow).hasClass('selUser')) {
				$(".TR_"+userFlow).removeClass("selUser");
			}else {
				var f=true;
				var doctors=$(".selUser").length+1;
				$("#schExternalDeptTimes input[name='recordFlows']:checked").each(function(){
					var lastnum=$(this).attr("lastnum")||"0";
					var deptName=$(this).attr("deptName")||"";
					var date=$(this).attr("date")||"";
					if(doctors>lastnum)
					{
						jboxTip("轮转科室【"+deptName+"】在开放时间"+date+"内，开放人数不足，请重新选择！");
						f=false;
					}

				});
				if(f)
					$(".TR_" + userFlow).addClass("selUser");
			}
		}
	}

</script>
<table class="basic" style="width: 100%">
	<c:forEach items="${users}" var="user">
		<tr class="viewTd TR_${user.sysUser.userFlow}"  userFlow="${user.sysUser.userFlow}" style="cursor: pointer;" onclick="viewUser('${user.sysUser.userFlow}');" >
			<td style="width: 100%;text-align: left;border-left: 0px;border-right: 0px;border-top: 0px;" >
				<span >${user.sysUser.userName}</span>
			</td>
		</tr>
	</c:forEach>
</table>