<jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
	<jsp:param name="font" value="true"/>
</jsp:include>
<script type="text/javascript">

	$(function(){
		document.onkeydown = function(e){
			var ev = document.all ? window.event : e;
			var key = $.trim($('#key').val());
			if(ev.keyCode == 13 &&  key != "") {
				searchregStuLst();
			}
		}
	});

	function searchregStuLst(){
		var data = "key=" + $('#key').val();
		jboxPostLoad("content","<s:url value='/hbres/singup/manage/stuReg'/>",data,true);
	}
	
	function withdrawUser(){
		jboxConfirm("确认此学员重报吗?",function(){
			var data = {
					doctorFlow : $('#userFlow').val()
			};
			jboxPost("<s:url value='/hbres/singup/manage/subOption'/>",data,
				function(resp){
					if(resp == "${GlobalConstant.OPERATE_SUCCESSED}"){
						jboxLoad("content","<s:url value='/hbres/singup/manage/stuReg'/>",true);
					}
				}
			,null,true);
		});
	}
</script>
      <div class="main_hd">
        <h2>学员重报</h2>
      </div>
      <div class="main_bd" id="div_table_0" > 
      	<div class="div_search fr">
      	    <input type="text" class="fr input" id="key" name="key" value="${key}" placeholder="身份证" onblur="searchregStuLst();" style="width:200px; height:20px;"/>
      	</div>
        <div class="div_table">
          <table class="grid" style="text-align: center;">
			  <colgroup>
				  <col width="12%"/>
				  <col width="22%"/>
				  <col width="12%"/>
				  <col width="22%"/>
				  <col width="12%"/>
				  <col width="20%"/>
			  </colgroup>
            <tr>
				<th>登录名</th>
				<td>${user.userCode}</td>
                <th>用户名</th>
				<td>${user.userName}</td>
				<th>性别</th>
				<td>${user.sexName}</td>
			</tr>
			<tr>
				<th>状态描述</th>
				<td>${user.statusDesc}</td>
				<th>用户手机</th>
				<td>${user.userPhone}</td>
				<th>邮箱</th>
				<td>${user.userEmail}</td>
			</tr>
			  <tr>
				  <th>身份证</th>
				  <td>${user.idNo}</td>
				  <th>学员类型</th>
				  <td>${user.doctorTypeName}</td>
				  <th>学员状态</th>
				  <td>${user.doctorStatusName}</td>
			  </tr>
			<tr>
				<th>毕业届</th>
				<td>${user.sessionNumber}</td>
				<th>学位</th>
				<td>${user.degreeName}</td>
				<th>学历</th>
				<td>${user.educationName}<input type="hidden" id ="userFlow" value="${user.userFlow}"/></td>
			</tr>
			<tr>
				<th>培训基地</th>
				<td>${user.orgName}</td>
				<th>培训专业</th>
				<td>${user.trainingSpeName}</td>
				<th>轮转信息</th>
				<td>${user.rotationName}</td>
			</tr>
          </table>
			<table class="grid" style="text-align: center;margin-top: 10px;">
				<tr>
					<th>注册年份</th>
					<th>注册状态</th>
				</tr>
				<c:forEach items="${ regLst}" var="user">
					<tr>
						<td>${user.regYear}</td>
						<td>${user.statusName}</td>
					</tr>
				</c:forEach>
				<c:if test="${empty regLst}">
					<tr>
						<td colspan="2">无记录</td>
					</tr>
				</c:if>
			</table>
			<table class="grid" style="text-align: center;margin-top: 10px;">
				<tr>
					<th>考点编号</th>
					<th>考点名称</th>
					<th>准考证号</th>
					<th>是否打印准考证</th>
					<th>考试成绩</th>
				</tr>
				<c:forEach items="${ examLst}" var="user">
					<tr>
						<td>${user.siteCode}</td>
						<td>${user.siteName}</td>
						<td>${user.ticketNum}</td>
						<td><c:choose>
							<c:when test="${user.ticketPrintFlag eq 'Y'}">是</c:when>
							<c:when test="${user.ticketPrintFlag eq 'N'}">否</c:when>
						</c:choose></td>
						<td>${user.examResult}</td>
					</tr>
				</c:forEach>
				<c:if test="${empty examLst}">
					<tr>
						<td colspan="5">无记录</td>
					</tr>
				</c:if>
			</table>
			<table class="grid" style="text-align: center;margin-top: 10px;">
				<tr>
					<th>志愿机构</th>
					<th>志愿专业</th>
					<th>填报年份</th>
					<th>笔试成绩</th>
					<th>面试成绩</th>
					<th>操作成绩</th>
					<th>总成绩</th>
					<th>是否录取</th>
					<th>是否确认</th>
				</tr>
				<c:forEach items="${ recruitLst}" var="user">
					<tr>
						<td>${user.orgName}</td>
						<td>${user.speName}</td>
						<td>${user.recruitYear}</td>
						<td>${user.examResult}</td>
						<td>${user.auditionResult}</td>
						<td>${user.operResult}</td>
						<td>${user.totleResult}</td>
						<td><c:choose>
								<c:when test="${user.recruitFlag eq 'Y'}">是</c:when>
								<c:when test="${user.recruitFlag eq 'N'}">否</c:when>
						</c:choose></td>
						<td><c:choose>
							<c:when test="${user.confirmFlag eq 'Y'}">是</c:when>
							<c:when test="${user.confirmFlag eq 'N'}">否</c:when>
						</c:choose></td>
					</tr>
				</c:forEach>
				<c:if test="${empty recruitLst}">
					<tr>
						<td colspan="9">无记录</td>
					</tr>
				</c:if>
			</table>
            <c:if test="${ not empty user && user.doctorStatusId != regStatusEnumUnSubmit.id && user.doctorStatusId ne 'Training'}">
                <div style="margin-top: 10px;text-align: center;">
					<a class="btn" onclick="withdrawUser();">重&#12288;报</a>
				</div>
            </c:if>
        </div>
      </div>
      
