<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
</jsp:include>
<script type="text/javascript" src="<s:url value='/js/Scoll/Scorll2.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
	$(document).ready(function(){

	});

	function delProcess(processFlow)
	{
		if(!processFlow){
			jboxTip("请选择轮需要删除轮转记录！");
			return false;
		}
		var url = "<s:url value='/jsres/afterDataManager/delProcess'/>?processFlow="+processFlow;

		jboxConfirm("确认删除轮转记录？",function(){
			jboxPost(url, null, function (resp) {
				toPage();
			}, null, true);
		});
	}
	function delProcessType(processFlow)
	{
		if(!processFlow){
			jboxTip("请选择轮转记录！");
			return false;
		}
		var recTypeId=$('#'+processFlow+'recTypeId').val();
		if(!recTypeId){
			jboxTip("请选择退回表单类型！");
			return false;
		}
		var recFlow=$('#'+processFlow+recTypeId).attr("recFlow");

		var url = "<s:url value='/jsres/afterDataManager/delProcessType'/>?processFlow="+processFlow+"&recTypeId="+recTypeId+"&recFlow="+recFlow;
		jboxConfirm("确认退回出科表单？",function() {
			jboxPost(url, null, function (resp) {
				if(resp=='${GlobalConstant.OPERATE_SUCCESSED}')
				{
					$('#'+processFlow+recTypeId).remove();
				}
			}, null, true);
		});
	}
</script>
	<div class="search_table" style="width: 100%;">
		<table border="0" cellpadding="0" cellspacing="0" class="grid">
			<tr>
				<th>轮转科室</th>
				<th>开始时间</th>
				<th>结束时间</th>
				<th>考核表类型</th>
				<th>操作</th>
			</tr>

			<c:forEach items="${list}" var="bean">
				<tr>
					<td>${bean.schDeptName}</td>
					<td>${bean.schStartDate}</td>
					<td>${bean.schEndDate}</td>
					<td>
						<select type="text" id="${bean.processFlow}recTypeId" name="recTypeId" class="input"  style="width: 150px;margin-left: 0px;height: 35px;">
							<option value=""></option>
							<c:set var="key" value="${bean.processFlow}AfterEvaluation"></c:set>
							<c:set value="0" var="count"></c:set>
							<c:if test="${not empty expressMap[key]}">
								<c:set value="${count+1}" var="count"></c:set>
								<option value="AfterEvaluation" id="${bean.processFlow}AfterEvaluation" recFlow="${expressMap[key].recFlow}">出科考核表</option>
							</c:if>
							<c:set var="key" value="${bean.processFlow}DOPS"></c:set>
							<c:if test="${not empty expressMap[key]}">
								<c:set value="${count+1}" var="count"></c:set>
								<option value="DOPS" id="${bean.processFlow}DOPS" recFlow="${expressMap[key].recFlow}">临床操作技能评估（DOPS）量化表</option>
							</c:if>
							<c:set var="key" value="${bean.processFlow}Mini_CEX"></c:set>
							<c:if test="${not empty expressMap[key]}">
								<c:set value="${count+1}" var="count"></c:set>
								<option value="Mini_CEX" id="${bean.processFlow}Mini_CEX" recFlow="${expressMap[key].recFlow}">迷你临床演练评估（Mini-CEX）量化表</option>
							</c:if>
						</select>
					</td>
					<td>
						<c:if test="${count>0}">
							<a class="btn"  onclick="delProcessType('${bean.processFlow}');" style="margin-top: 5px;margin-bottom: 5px;">退回出科表</a>
						</c:if>
						<a class="btn"  onclick="delProcess('${bean.processFlow}');" style="margin-top: 5px;margin-bottom: 5px;">删除科室</a>
					</td>
				</tr>
			</c:forEach>
			<c:if test="${empty list}">
				<tr>
					<td colspan="10" >${empty msg ? '无轮转记录！':msg}</td>
				</tr>
			</c:if>
		</table>
	</div>
