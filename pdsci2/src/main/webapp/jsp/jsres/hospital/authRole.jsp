<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true" />
	<jsp:param name="jbox" value="true" />
	<jsp:param name="font" value="true" />
	<jsp:param name="jquery_validation" value="true" />
</jsp:include>
<script type="text/javascript">
    function selRole(roleFlow, obj) {
        var checked = $(obj).attr("checked");
        if("${applicationScope.sysCfgMap['res_teacher_role_flow'] }" == roleFlow) {
            if(!checked){
                checked = 'N';
            }else {
                checked = 'Y';
            }
        }else {
            checked = 'Y';
        }
        var url = "<s:url value='/jsres/manage/selRole'/>?userFlow=${userFlow}&wsId=${GlobalConstant.RES_WS_ID}&roleFlow=" + roleFlow + "&checked=" + checked ;
        jboxGet(url, null, null, null, true);
    }

    // 关闭
	function doClose(){
        // 刷新父页面
        window.parent.toPage();
        setTimeout(function(){
            jboxClose();
        }, 500);
	}

</script>
</head>
<body>
	<table border="0" cellpadding="0" cellspacing="0" class="base_info" style="margin-top: 20px;">
		<tbody>
			<tr>
				<td>
					<c:if test="${!empty applicationScope.sysCfgMap['res_teacher_role_flow']}">
						<label style="display: inline-block;">
							<input type="checkbox" onclick="selRole(this.value,this)" value="${applicationScope.sysCfgMap['res_teacher_role_flow'] }"
							<c:if test="${pdfn:contain(applicationScope.sysCfgMap['res_teacher_role_flow'],roleFlowList) }">checked='checked'</c:if> />
							${!empty sysRoleMap[sysCfgMap['res_teacher_role_flow']]?sysRoleMap[sysCfgMap['res_teacher_role_flow']].roleName:'带教老师'}
						</label><br>
					</c:if>
					<c:if test="${!empty applicationScope.sysCfgMap['res_head_role_flow']}">
						<label style="display: inline-block;">
							<input type="checkbox" onclick="selRole(this.value);" value="${applicationScope.sysCfgMap['res_head_role_flow']}"
							<c:if test="${pdfn:contain(applicationScope.sysCfgMap['res_head_role_flow'],roleFlowList) }">checked='checked'</c:if> />
							${!empty sysRoleMap[sysCfgMap['res_head_role_flow']]?sysRoleMap[sysCfgMap['res_head_role_flow']].roleName:'科主任'}
						</label><br>
					</c:if>
					<c:if test="${!empty applicationScope.sysCfgMap['res_secretary_role_flow']}">
						<label style="display: inline-block;">
							<input type="checkbox" onclick="selRole(this.value);" value="${applicationScope.sysCfgMap['res_secretary_role_flow']}"
							<c:if test="${pdfn:contain(applicationScope.sysCfgMap['res_secretary_role_flow'],roleFlowList) }">checked='checked'</c:if> />
							${!empty sysRoleMap[sysCfgMap['res_secretary_role_flow']]?sysRoleMap[sysCfgMap['res_secretary_role_flow']].roleName:'科秘'}
						</label><br>
					</c:if>
					<c:if test="${!empty applicationScope.sysCfgMap['res_teaching_head_role_flow']}">
						<label style="display: inline-block;">
							<input type="checkbox" onclick="selRole(this.value);" value="${applicationScope.sysCfgMap['res_teaching_head_role_flow']}"
							<c:if test="${pdfn:contain(applicationScope.sysCfgMap['res_teaching_head_role_flow'],roleFlowList) }">checked='checked'</c:if> />
							${!empty sysRoleMap[sysCfgMap['res_teaching_head_role_flow']]?sysRoleMap[sysCfgMap['res_teaching_head_role_flow']].roleName:'教学主任'}
						</label><br>
					</c:if>
					<c:if test="${!empty applicationScope.sysCfgMap['res_teaching_secretary_role_flow']}">
						<label style="display: inline-block;">
							<input type="checkbox" onclick="selRole(this.value);" value="${applicationScope.sysCfgMap['res_teaching_secretary_role_flow']}"
							<c:if test="${pdfn:contain(applicationScope.sysCfgMap['res_teaching_secretary_role_flow'],roleFlowList) }">checked='checked'</c:if> />
							${!empty sysRoleMap[sysCfgMap['res_teaching_secretary_role_flow']]?sysRoleMap[sysCfgMap['res_teaching_secretary_role_flow']].roleName:'教学秘书'}
						</label><br>
					</c:if>
					<c:if test="${!empty applicationScope.sysCfgMap['res_hospitalLeader_role_flow']}">
						<label style="display: inline-block;">
							<input type="checkbox" onclick="selRole(this.value);" value="${applicationScope.sysCfgMap['res_hospitalLeader_role_flow']}"
							<c:if test="${pdfn:contain(applicationScope.sysCfgMap['res_hospitalLeader_role_flow'],roleFlowList) }">checked='checked'</c:if> />
							${!empty sysRoleMap[sysCfgMap['res_hospitalLeader_role_flow']]?sysRoleMap[sysCfgMap['res_hospitalLeader_role_flow']].roleName:'评分专家'}
						</label>
					</c:if>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="button">
		<input type="button" class="btn_green" onclick="doClose();" value="关&#12288;闭"/>
	</div>
</body>
</html>