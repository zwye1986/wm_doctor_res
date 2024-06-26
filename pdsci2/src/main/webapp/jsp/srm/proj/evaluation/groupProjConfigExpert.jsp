<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
               <c:forEach items="${expertInfos}" var="expertInfo">
               <c:if test="${empty expertProjMap[expertInfo.user.userFlow ]}">
               <tr>
                	<td>
                		<input type="checkbox" name="userFlow" value="${expertInfo.user.userFlow }" <c:if test="${applicationScope.sysCfgMap['srm_expert_proj_by_group'] eq 'Y'}"> checked="checked"</c:if> />
                	</td>
                	<td>${expertInfo.user.userName}</td>
                	<td>
                		<c:if test='${expertInfo.user.sexId=="1"}'>男</c:if>
                		<c:if test='${expertInfo.user.sexId=="2"}'>女</c:if>
                	</td>
                	<td>${expertInfo.expert.majorName}</td>
                	<td>${expertInfo.expert.education}</td>
                	<td>${expertInfo.user.orgName}</td>
                	<td>${expertInfo.user.userPhone}</td>
                	<td>${expertInfo.user.userEmail}</td>
            	</tr>
            	</c:if>
            </c:forEach>

<c:if test="${applicationScope.sysCfgMap['srm_expert_proj_by_group'] eq 'Y'}">
    <script type="text/javascript">
        $(function () {
                    $("input[type='checkbox']").click(
                            function () {
                                this.checked = !this.checked;
                            }
                    );
                }
        );
    </script>
</c:if>