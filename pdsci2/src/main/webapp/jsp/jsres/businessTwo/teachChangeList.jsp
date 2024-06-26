<script type="text/javascript">
	$(document).ready(function(){
		jboxEndLoading();
		initCheck();
	});

	function initCheck()
	{
		var cl=$("input[name='appLogins']:checked").length;
		var al=$("input[name='appLogins']").length;
		if(cl!= 0&&cl==al)
		{
			$("input[name='appLogin']").attr("checked",true);
		}else{
			$("input[name='appLogin']").attr("checked",false);
		}
	}


	function oper(obj, userFlow,cfg){
		var cfgValue = "${GlobalConstant.FLAG_N}";
		if($(obj).attr("checked")=="checked") {
			cfgValue = "${GlobalConstant.FLAG_Y}";
		}
		$("#cfgCode").val(cfg+userFlow);
		$("#cfgValue").val(cfgValue);
		$("#desc").val("是否开放带教app登录权限");
		save(userFlow,cfg,cfgValue);
	}

	function save(userFlow,cfg,cfgValue){
		var url = "<s:url value='/jsres/teacherCfg/saveOne'/>?userFlow="+userFlow;
		var title = "确认开放权限？";
		if(cfgValue == "N"){
			title = "确认取消权限？";
		}
		jboxButtonConfirm(title,"确认","取消", function () {
			jboxPost(url, $($('#sysCfgForm').html().htmlFormart(cfg+userFlow)).serialize(), function(resp){
				if(resp == "${GlobalConstant.SAVE_SUCCESSED}"){
					jboxTip("操作成功！");
					searchTea();
				}else{
					jboxTip("操作失败！");
				}initCheck();
			}, null, false);
		},function(){
			search();
		},300);
	}

	String.prototype.htmlFormart = function(){
		var html = this;
		for(var index in arguments){
			var reg = new RegExp('\\{'+index+'\\}','g');
			html = html.replace(reg,arguments[index]);
		}
		return html;
	};

	function updateTeaSubmitOne(userFlow) {
		var userFlows = userFlow;
		var url = "<s:url value='/jsres/powerCfg/updateTeaSubmit'/>?userFlows="+userFlows;
		jboxConfirm("确认提交？", function () {
			jboxPost(url, null, function (resp) {
				if ("${GlobalConstant.OPRE_SUCCESSED}" == resp) {
					searchTea();
					jboxTip("操作成功！");
				}else{
					jboxTip("操作失败！");
				}
			}, null, true);
		});
	}
</script>
<div class="search_table">
	<table border="0" cellpadding="0" cellspacing="0" class="grid">
		<tr>
			<th>培训基地</th>
			<th>所在科室</th>
			<th>姓名</th>
			<th>用户名</th>
			<th>APP权限</th>
			<th>是否提交</th>
			<th>审核状态</th>
		</tr>
		<c:forEach items="${list }" var="userExt">
			<tr>
				<td>${userExt.orgName }</td>
				<td>${userExt.deptName }</td>
				<td>${userExt.userName }</td>
				<td>${userExt.userCode }</td>
				<td>
					<c:set var="key" value="jsres_teacher_app_login_${userExt.userFlow}"/>
					<input type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if>
						   id="jsres_teacher_app_login_${userExt.userFlow}" name="appLogins"
						   value="${userExt.userFlow}" ${pdfn:jsresPowerCfgMap(key) == GlobalConstant.FLAG_Y?'checked':''}
						   onchange="oper(this,'${userExt.userFlow }','jsres_teacher_app_login_');"/>
				</td>
				<td>
					<c:if test="${userExt.isSubmitId eq 'NotSubmit' or empty userExt.isSubmitId }">
						<c:set var="key" value="jsres_teacher_app_login_${userExt.userFlow}"/>

						<c:if test="${pdfn:jsresPowerCfgMap(key) == GlobalConstant.FLAG_Y}">
							<input type="button" value="提交" class="" style="background-color: #e9e9ed;height: 25px;width: 45px;border-radius: 5px" onclick="updateTeaSubmitOne('${userExt.userFlow}')"/>
							<input type="checkbox" id="isSubmitId" name="isSubmitId" value="${userExt.userFlow}" style="display: none"/>
						</c:if>

					</c:if>
					<c:if test="${userExt.isSubmitId eq 'Submit' and userExt.checkStatusId ne 'UnPassed'}">
						已提交
					</c:if>
					<c:if test="${userExt.isSubmitId eq 'Submit' and userExt.checkStatusId eq 'UnPassed'}">
						<input type="button" value="重新提交" class="" style="background-color: #e9e9ed;height: 25px;width: 65px;border-radius: 5px" onclick="updateTeaSubmitOne('${userExt.userFlow}')"/>
						<input type="checkbox" id="isSubmitId" name="isSubmitId" value="${userExt.userFlow}" style="display: none"/>
					</c:if>
				</td>
				<td>${userExt.checkStatusName }</td>
			</tr>
		</c:forEach>
		<c:if test="${empty list}">
			<tr>
				<td colspan="10" style="border:none;">暂无记录！</td>
			</tr>
		</c:if>
	</table>
</div>
<div class="page" style="padding-right: 40px;">
	<c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"></c:set>
	<pd:pagination-jsres toPage="toPage"/>
</div>