<style>
	.selRole:HOVER {color: #0d6aa2;}
</style>

<script>
	$(function(){
		loadTheRoles();
		$("#changeRole").on("mouseenter mouseleave",function(){
			$("#roleContent").toggle();
		});
	});
	
	function loadTheRoles(){
		jboxPost("<s:url value='/jsres/base/getRoles'/>",null,function(resp){
			if(resp){
				if(resp.length > 1){
					for(var index in resp){
						var role = resp[index];
						if(role){
							var name = role.roleName;
							var url = "<s:url value='/inx/jsres/changeRole?roleFlow='/>" +  role.roleFlow;
							var roleItem = $('<div/>').addClass("selRole").attr("url",url).text(name).click(function(){
								location.href = $(this).attr("url");
							});
							$("#roleHome").append(roleItem);
						}
					}
					$("#roleSpan").show();
				}else{
					$("#changeRole").remove();
                    $("#roleSpan").hide();
				}
			}
		},null,false);
	}
</script>
<span id="roleSpan" style="display: none;">${sessionScope.currRoleName}&#12288;</span>
<span id="changeRole">
	<div id="roleContent" style="width: 0px;height: 0px;overflow: visible;float: right;display: none;">
		<div id="roleHome" 
		style="
		background: #6eb0dd;
		width: 120px;
		text-align: left;
		padding: 0px 5px;
		top:21px;
		border-radius:3px;
		position: relative;
		right: 165px;
		border: 1px solid #459fd0;
		cursor: pointer;"></div>
	</div>
	<a><c:out value="${param.actionName}" default="切换角色"/></a>
	&#12288;
</span>