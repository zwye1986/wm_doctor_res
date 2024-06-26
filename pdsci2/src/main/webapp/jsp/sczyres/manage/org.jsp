<jsp:include page="/jsp/sczyres/htmlhead-sczyres.jsp">
	<jsp:param name="font" value="true"/>
</jsp:include>
<script type="text/javascript">
	function search(){
		jboxPostLoad("content", "<s:url value='/sczyres/manage/hospitalList'/>",$("#searchForm").serialize(), true);
	}
	function addOrg() {
		jboxOpen("<s:url value='/sys/org/edit4sczy'/>","新增机构信息", 900, 300);
	}
	function editOrg(orgFlow) {
		jboxOpen("<s:url value='/sys/org/edit4sczy'/>?orgFlow="+orgFlow,"编辑机构信息", 900, 300);
	}
	function addUser(orgFlow,userFlow) {
		var url = "<s:url value='/sys/user/edit4sczy/${GlobalConstant.USER_LIST_GLOBAL}'/>?roleFlow=${sysCfgMap['res_admin_role_flow']}&orgFlow="+orgFlow+"&userFlow="+userFlow;
		jboxOpen(url,"管理员账号信息", 900, 400);
	}
	function resetPasswd(userFlow){
		jboxConfirm("确认将该用户的密码重置为:${pdfn:getInitPass()} 吗？",function () {
			var url = "<s:url value='/sys/user/resetPasswd?userFlow='/>"+userFlow;
			jboxGet(url,null,function(){
				//searchUser();
			});
		});
	}
	function xieTong(flow){
		jboxOpen("<s:url value='/res/platform/jointAll4sczy'/>?flow="+flow+"&currentPage=${param.currentPage}","机构",850,500);
	}
</script>
      <div class="main_hd">
        <h2 class="underline">基地维护</h2>
      </div>
      <div class="main_bd" id="div_table_0"  >
		  <form id="searchForm">
			  <div class="div_search">
				  医院名称：
				  <input type="text" name="orgName" class="input" value="${param.orgName}" />
				  &#12288;
				  <input type="button" class="btn_blue" value="查&#12288;询" onclick="search();"/>
				  &#12288;
				  <input type="button" class="btn_blue" value="新&#12288;增" onclick="addOrg();"/>
			  </div>
		  </form>
		  <div class="div_table">
          <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
				<th width="10%" style="text-align: center;padding: 0px;">医院名称</th>
				<th width="15%" style="text-align: center;padding: 0px;">协同医院</th>
				<th width="10%" style="text-align: center;padding: 0px;">基地类型</th>
				<th width="10%" style="text-align: center;padding: 0px;">管理员账号</th>
				<th width="30%" style="text-align: center;padding: 0px;">操作</th>
            </tr>
			  <c:forEach items="${orgList}" var="org">
				  <c:set var="user" value="${userMap[org.orgFlow]}"/>
				  <tr>
					  <td style="text-align: center;padding: 0px;">${org.orgName}</td>
					  <td style="text-align: center;padding: 0px;">
						  <c:forEach items="${jointOrgMap[org.orgFlow]}" var="joint" varStatus="relStatus">
							  <c:if test="${!relStatus.first}">
								  ,
							  </c:if>
							  ${joint.jointOrgName }
						  </c:forEach>
					  </td>
					  <td style="text-align: center;padding: 0px;">
							  ${org.orgLevelName}
					  </td>
					  <td style="text-align: center;padding: 0px;">${user.userCode}</td>
					  <td style="text-align: left;padding-left: 9%;">
						  <a style="color: blue;cursor: pointer;" onclick="editOrg('${org.orgFlow}');">编辑</a>
						  |
						  <a style="color: blue;cursor: pointer;" onclick="addUser('${org.orgFlow}','${user.userFlow}');">账户维护</a>
						  <c:if test="${!empty user}">
							  |
							  <a style="color: blue;cursor: pointer;" onclick="resetPasswd('${user.userFlow}');">重置密码</a>
						  </c:if>
						<c:if test="${org.orgLevelId eq 'Main'}">
						  |
						  <a style="color: blue;cursor: pointer;" onclick="xieTong('${org.orgFlow}');">协同机构维护</a>
						</c:if>
					  </td>
				  </tr>
			  </c:forEach>
			  <c:if test="${empty orgList}">
				  <tr><td colspan="99" style="text-align: center;">无记录</td></tr>
			  </c:if>
          </table>
        </div>
      </div>
      
