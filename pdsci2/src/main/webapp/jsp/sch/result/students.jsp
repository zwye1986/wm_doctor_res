
<style type="text/css">
	.selUser {background-color: #0a6999}
</style>
<script type="text/javascript">
	$(function(){
		$("a").click(function(e){
			e.stopPropagation();
		});
	});
	function viewUser(userFlow){
		$("#arrangeResults").html("");
		if(userFlow){
//				if($("#"+userFlow).length>0)
//				{
//					$("#"+userFlow).html("");
//				}else{
//					var d=$("<div>").attr("id",userFlow);
//					$("#arrangeResults").append(d);
//				}
				var cycleTypeId=$("#cycleTypeId").val();
				var selectYear=$("#selectYear").val();
				jboxPostLoad("arrangeResults" ,"<s:url value='/sch/arrangeResult/arrange/results'/>?doctorFlow="+userFlow+"&cycleTypeId="+cycleTypeId+"&selectYear="+selectYear ,null , true);
		}
	}
	function addClass(userFlow){
		if(userFlow){
			if ($(".TR_"+userFlow).hasClass('selUser')) {
				$(".TR_"+userFlow).removeClass("selUser");
				$("#"+userFlow).html("");
			}else if ($(".TR_"+userFlow).hasClass('canClick')){
				$(".TR_" + userFlow).addClass("selUser");
			}
		}
	}

</script>
<table class="basic" style="width: 100%">
	<c:forEach items="${users}" var="user">
		<tr class="${user.selAllFlag eq 'Y' ?'canClick':''} viewTd TR_${user.sysUser.userFlow} "
			userName="${user.sysUser.userName}"
			userFlow="${user.sysUser.userFlow}"
			selDeptFlag="${user.selDeptFlag}"
			schFlag="${user.schFlag}"
			selAllFlag="${user.selAllFlag}"
			schAllFlag="${user.schAllFlag}"
			selOneFlag="${user.selOneFlag}"
			schOneFlag="${user.schOneFlag}"
			selTwoFlag="${user.selTwoFlag}"
			schTwoFlag="${user.schTwoFlag}"
			selThreeFlag="${user.selThreeFlag}"
			schThreeFlag="${user.schThreeFlag}"
			style="${user.selAllFlag eq 'Y' ?'cursor: pointer;':''}" onclick="addClass('${user.sysUser.userFlow}');" >
			<td style="width: 70%;text-align: left;border-left: 0px;border-right: 0px;border-top: 0px;" >
				<span >${user.sysUser.userName}</span>
				<span style="color:red;">${user.selAllFlag eq 'Y' ?'':'未选科'}</span>
			</td>
			<td>
				<span><a href="javascript:void(0);" onclick="viewUser('${user.sysUser.userFlow}');" >查看</a> </span>
			</td>
		</tr>
	</c:forEach>
</table>