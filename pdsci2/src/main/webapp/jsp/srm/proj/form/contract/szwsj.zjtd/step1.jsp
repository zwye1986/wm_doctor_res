<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<c:if test="${param.view != GlobalConstant.FLAG_Y}">
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="ueditor" value="true"/>
</jsp:include>
<script type="text/javascript">
function nextOpt(step){
	if(false==$("#projForm").validationEngine("validate")){
		return false;
	}
	var form = $("#projForm");
	form.append('<input type="hidden" name="nextPageName" value="'+step+'"/>');
	$('#nxt').attr({"disabled":"disabled"});
	jboxStartLoading();
	form.submit();
}
</script>
</c:if>
    <form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" style="position: relative;">
	    <input type="hidden" id="pageName" name="pageName" value="step1"/>
		<input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
	    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
		<input type="hidden" name="recTypeId" value="${param.recTypeId}"/>

        <table width="100%" cellpadding="0" cellspacing="0" class="basic">
            <tr>
			    <th  colspan="4" style="text-align: left;padding-left: 15px;font-size: 14px; font-weight:bold;"></th>
			</tr>
			<c:choose>
                <c:when test="${param.view == GlobalConstant.FLAG_Y}">
                    <tr>
				        <td width="20%" style="text-align: right;">团队名称：</td>
						<td colspan="3" width="80%">${resultMap.teamName}</td>
					</tr>
					<tr>
					    <td width="20%" style="text-align: right;">团队带头人：</td>
						<td colspan="3" width="80%">${resultMap.leaderName}</td>
					</tr>
					<tr>
					    <td width="20%" style="text-align: right;">引进单位：</td>
						<td colspan="3" width="80%">${resultMap.applyOrgName}</td>
					</tr>
					<tr>
						<td width="20%" style="text-align: right;">引进单位负责人：</td>
						<td colspan="3" width="80%">${resultMap.applyOrgMan}</td>
					</tr>
					<tr>
					    <td width="20%" style="text-align: right;">依托科室负责人：</td>
						<td colspan="3" width="80%">${resultMap.supportDeptUser}</td>
					</tr>
				    <tr>
					    <td width="20%" style="text-align: right;">引进单位联系人：</td>
						<td colspan="3" width="80%">${resultMap.applyOrgLinkMan}</td>
					</tr>
					<tr>
					    <td width="20%" style="text-align: right;">联系电话：</td>
						<td colspan="3" width="80%">${resultMap.linkTel}</td>
					</tr>
					<tr>
						<td width="20%" style="text-align: right;">合同起止年限：</td>
						<td colspan="3" width="80%">
							2O17年至2O21年
						</td>
					</tr>
                </c:when>
                <c:otherwise>
			    	<tr>
			    		<td width="20%" style="text-align: right;">团队名称：<span class="redspan" style="color: red;padding: 0px;">*</span></td>
			    		<td colspan="3" width="80%">
							<input name="teamName" type="text" value="<c:if test='${empty resultMap.teamName and param.view != GlobalConstant.FLAG_Y}'>${proj.projName}</c:if><c:if test='${!empty resultMap.teamName}'>${resultMap.teamName}</c:if>" class="validate[required] inputText"  style="width: 400px; "/>
	                    </td>
			    	</tr>
			    	<tr>
			    		<td width="20%" style="text-align: right;">团队带头人：<span class="redspan" style="color: red;padding: 0px;">*</span></td>
			    		<td colspan="3" width="80%">
							<input name="leaderName" type="text" value="${resultMap.leaderName}" class="validate[required] inputText"   style="width: 400px; "/>
	                    </td>
			    	</tr>
			    	<tr>
			    		<td width="20%" style="text-align: right;">引进单位：<span class="redspan" style="color: red;padding: 0px;">*</span></td>
			    		<td colspan="3" width="80%">
	                       	<input name="applyOrgName" type="text" value="<c:if test='${empty resultMap.applyOrgName and param.view != GlobalConstant.FLAG_Y}'>${proj.applyOrgName}</c:if><c:if test='${!empty resultMap.applyOrgName}'>${resultMap.applyOrgName}</c:if>" class="validate[required] inputText"   style="width: 400px; "/>
	                    </td>
			    	</tr>
					<tr>
						<td width="20%" style="text-align: right;">引进单位负责人：<span class="redspan" style="color: red;padding: 0px;">*</span></td>
						<td colspan="3" width="80%">
							<input name="applyOrgMan" type="text" value="${resultMap.applyOrgMan}" class="validate[required] inputText"   style="width: 400px; "/>
						</td>
					</tr>
			    	<tr>
			    		<td width="20%" style="text-align: right;">依托科室负责人：<span class="redspan" style="color: red;padding: 0px;">*</span></td>
			    		<td colspan="3" width="80%">
							<input name="supportDeptUser" type="text" value="<c:if test='${empty resultMap.supportDeptUser and param.view != GlobalConstant.FLAG_Y}'>${proj.applyUserName}</c:if><c:if test='${!empty resultMap.supportDeptUser }'>${resultMap.supportDeptUser}</c:if>" class="validate[required] inputText"  style="width: 400px; "/>
	                    </td>
			    	</tr>
			    	
			    	<tr>
			    		<td width="20%" style="text-align: right;">引进单位联系人：<span class="redspan" style="color: red;padding: 0px;">*</span></td>
			    		<td colspan="3" width="80%">
	                       	<input name="applyOrgLinkMan" type="text" value="<c:if test='${empty resultMap.applyOrgLinkMan and param.view != GlobalConstant.FLAG_Y}'>${proj.applyUserName}</c:if><c:if test='${!empty resultMap.applyOrgLinkMan }'>${resultMap.applyOrgLinkMan}</c:if>" class="validate[required] inputText"  style="width: 400px; "/>
	                    </td>
			    	</tr>
					<tr>
			    		<td width="20%" style="text-align: right;">联系电话：<span class="redspan" style="color: red;padding: 0px;">*</span></td>
			    		<td colspan="3" width="80%">
	                       	<input name="linkTel" type="text" value="${resultMap.linkTel}" class="validate[required] inputText"  style="width: 400px; "/>
	                    </td>
			    	</tr>
			    	<tr>
			    		<td width="20%" style="text-align: right;">起止年限：<span class="redspan" style="color: red;padding: 0px;">*</span></td>
			    		<td colspan="3" width="80%">
							<p style="text-align: center;width: 400px;">2017年至2021年</p>
	                    </td>
			    	</tr>
				</c:otherwise>
			</c:choose>
		</table>
       	<c:if test="${param.view != GlobalConstant.FLAG_Y}">
       	<div align="center" style="margin-top: 10px">
       		<input type="button" id="nxt" onclick="nextOpt('step2')" class="search" value="下一步"/>
       	</div>
       	</c:if>	          
    </form>
