	<script>
		function toPage(page){
			$("#currentPage").val(page);
			jboxPostLoad("content","<s:url value='/hbres/singup/hospital/registAudit'/>",$("#searchForm").serialize(),true);
		}
		function auditRegist(userFlow,recordFlow){
			jboxOpen("<s:url value='/hbres/singup/manage/userInfo?flag=auditRegist'/>&userFlow="+userFlow+"&recordFlow="+recordFlow,"用户信息",1000,550);
		}
		function view(userFlow,recordFlow){
			jboxOpen("<s:url value='/hbres/singup/manage/userInfo?flag=view'/>&userFlow="+userFlow+"&recordFlow="+recordFlow,"用户信息",1000,550);
		}
		function checkAll(obj) {
			var f = false;
			if ($(obj).attr("checked") == "checked") {
				f = true;
			}
			$(".docType").each(function () {
				if (f) {
					$(this).attr("checked", "checked");
				} else {
					$(this).removeAttr("checked");
				}

			});
		}
		function changeAllBox() {
			if ($(".docType:checked").length == $(".docType").length) {
				$("#all").attr("checked", "checked");
			} else {
				$("#all").removeAttr("checked");
			}
		}
		$(function(){
			<c:forEach items="${hBRecDocTypeEnumList}" var="type">
			<c:forEach items="${datas}" var="data">
			if ("${data}" == "${type.id}") {
				$("#" + "${data}").attr("checked", "checked");
			}
			</c:forEach>
			<c:if test="${empty datas}">
			$("#" + "${type.id}").attr("checked", "checked");
			</c:if>
			</c:forEach>
		})
		function checkAll2(obj) {
			if (obj.checked) {
				$(":checkbox[name='registFlow_check']").attr("checked",true);
			}else {
				$(":checkbox[name='registFlow_check']").attr("checked",false);
			}
		}
		function auditForBatch(){
			var numBanch = $("input[name='registFlow_check']:checked").length;
			if (numBanch<1) {
				jboxTip("请至少选择一条记录！");
				return;
			}
			jboxConfirm("确认批量录取？",function(){
				var recordFlows = [];
				$("input[name='registFlow_check']:checked").each(function(){
					var recordFlow=$(this).val();
					recordFlows.push(recordFlow);
				})
				jboxPost("<s:url value='/hbres/singup/hospital/auditForBatch'/>","&recordFlows="+recordFlows,function(resp){
					if(resp==1){
						jboxTip("操作成功！");
						toPage();
					}
				},null,false)
			})
		}
	</script>
	<div class="main_hd">
		<h2 class="underline">
			学员信息审核
		</h2>
	</div>
	<div class="main_bd" id="div_table_0" >
	<div class="div_search" style="padding: 10px 40px;line-height: 35px;">
		<form id="searchForm" method="post" action="<s:url value='/hbres/singup/hospital/registAudit'/>">
			<input id="currentPage" type="hidden" name="currentPage"/>
			<table class="searchTable" style="width: 100%;border-collapse: separate;border-spacing: 0px 10px;font-size: 14px;line-height: normal;">
				<tr>
					<td class="td_left">学员来源：</td>
					<td>
						<select name="stuType" class="select" style="width: 126px;">
							<option value="">全部</option>
							<option value="N" ${param.stuType eq 'N'?'selected':''}>统一招录</option>
							<option value="Y" ${param.stuType eq 'Y'?'selected':''}>线下招录</option>
							<option value="Graduate" ${param.stuType eq 'Graduate'?'selected':''}>四证合一</option>
						</select>
					</td>
					<td class="td_left">年&#12288;&#12288;级：</td>
					<td>
						<select name="sessionNumber" class="select" style="width: 126px;">
							<option value="">请选择</option>
							<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict">
								<option value="${dict.dictName}" ${recruitYear eq dict.dictName?'selected':''}>${dict.dictName}</option>
							</c:forEach>
						</select>
					</td>
					<td class="td_left">姓&#12288;&#12288;名：</td>
					<td><input type="text" name="userName" value="${param.userName}" class="input" style="width: 120px;"/></td>
					<td class="td_left">证&ensp;件&ensp;号：</td>
					<td><input type="text" name="idNo" value="${param.idNo}" class="input" style="width: 120px;"/></td>
				</tr>
				<tr>
					<td class="td_left">培训专业：</td>
					<td>
						<select name="speId" class="select" style="width: 126px;">
							<option  value="">全部</option>
							<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
								<option value="${dict.dictId}" ${param.speId eq dict.dictId?'selected':''}>${dict.dictName}</option>
							</c:forEach>
						</select>
					</td>
					<td class="td_left">审核状态：</td>
					<td>
						<select name="auditStatusId" class="select" style="width: 126px;">
							<option  value="">全部</option>
							<c:forEach items="${resBaseStatusEnumList}" var="dict">
								<c:if test="${dict.id eq 'Auditing' || dict.id eq 'Passed' || dict.id eq 'NotPassed'}">
									<option value="${dict.id}" ${param.auditStatusId eq dict.id?'selected':''}>${dict.name}</option>
								</c:if>
							</c:forEach>
						</select>
					</td>
					<td class="td_left">学员类型：</td>
					<td colspan="3"  class="td_left">
						<label><input type="checkbox" id="all" value="all" name="all" checked
									  onclick="checkAll(this);"/>全部</label>
						<c:forEach items="${hBRecDocTypeEnumList}" var="type">
							<label><input type="checkbox" id="${type.id}" value="${type.id}" class="docType"
							name="datas" onclick="changeAllBox();"/>${type.name}</label>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<font color="red">(当前只能审核${regYear}届医师培训信息！)
						<input class="btn_green btn_green1" type="button" value="查&#12288;询" onclick="toPage();"/>&#12288;
						<input class="btn_green" type="button" value="批量通过" onclick="auditForBatch();"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
	</div>
	<div class="search_table">
       <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
				<th><input type="checkbox" onclick="checkAll2(this);"></th>
				<th width="5%">序号</th>
				<th width="10%">姓名</th>
				<th width="20%">证件号</th>
				<th width="5%">性别</th>
				<th width="10%">年级</th>
				<th width="10%">培训类别</th>
				<th width="20%">培训专业</th>
				<th width="10%">状态</th>
				<th width="10%">操作</th>
            </tr>
            <c:forEach items="${registList}" var="regist" varStatus="s">
            	<tr>
					<td style="text-align: center;">
						<c:if test="${regist['AUDIT_STATUS_ID'] eq 'Auditing'}">
						<input type="checkbox" name="registFlow_check"  value="${regist['RECORD_FLOW']}">
						</c:if>
					</td>
	                <td>${s.index+1}</td>
	                <td>${regist["USER_NAME"]}</td>
	                <td>${regist["ID_NO"]}</td>
	                <td>${regist["SEX_NAME"]}</td>
					<td>${regist["SESSION_NUMBER"]}</td>
	                <td>${regist["DOCTOR_CATEGORY_NAME"]}</td>
	                <td>${regist["SPE_NAME"]}</td>
					<td <c:if test="${regist['AUDIT_STATUS_ID'] eq 'NotPassed'}">
							title="退回原因：${regist['AUDIT_INFO']}"
						</c:if>
					>${regist["AUDIT_STATUS_NAME"]}</td>
					<td>
						<c:if test="${regist['AUDIT_STATUS_ID'] eq 'Auditing'}">
							<a class="btn" onclick="auditRegist('${regist["DOCTOR_FLOW"]}','${regist["RECORD_FLOW"]}');">审核</a>
						</c:if>
						<c:if test="${regist['AUDIT_STATUS_ID'] ne 'Auditing'}">
							<a class="btn" onclick="view('${regist["DOCTOR_FLOW"]}','${regist["RECORD_FLOW"]}');">详情</a>
						</c:if>
					</td>
	            </tr>
            </c:forEach>
            <c:if test="${empty registList}">
				<tr>
					<td colspan="20" >无记录！</td>
				</tr>
      	  	</c:if>
        </table>
	</div>
	<div class="page" style="text-align: center">
		<c:set var="pageView" value="${pdfn:getPageView(registList)}" scope="request"></c:set>
		<pd:pagination-jsres toPage="toPage"/>
	</div>
      
