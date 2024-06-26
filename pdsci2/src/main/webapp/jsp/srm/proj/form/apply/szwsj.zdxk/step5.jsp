
<c:if test="${param.view != GlobalConstant.FLAG_Y}">
	<script type="text/javascript">
function nextOpt(step){
	if(false==$("#projForm").validationEngine("validate")){
		return false;
	}
	var form = $('#projForm');
	form.append('<input type="hidden" name="nextPageName" value="'+step+'"/>');
	$('#nxt').attr({"disabled":"disabled"});
	$('#prev').attr({"disabled":"disabled"});
	jboxStartLoading();
	form.submit();
}

$(document).ready(function(){
	if($("#completeTb tr").length<=0){
		add('complete');
	}
});

function add(tb){
	var length = $("#"+tb+"Tb").children().length;
	if(length > 9){
		jboxTip("限填10项！");
		return false; 
	}
	//计算行数
	$("#"+tb+"Num").val(length+1);
	
 	$("#"+tb+"Tb").append($("#"+tb+"Template tr:eq(0)").clone());
 	var length = $("#"+tb+"Tb").children().length;
 	//序号
	$("#"+tb+"Tb").children("tr").last().children("td").eq(1).text(length);
}

function delTr(tb){
	//alert("input[name="+tb+"Ids]:checked");
	var checkboxs = $("input[name='"+tb+"Ids']:checked");
	if(checkboxs.length==0){
		jboxTip("请勾选要删除的！");
		return false;
	}
	jboxConfirm("确认删除?",function () {
		var trs = $('#'+tb+'Tb').find(':checkbox:checked');
		$.each(trs , function(i , n){
			$(n).parent('td').parent("tr").remove();
		});
		//删除后序号
		var serial = 0;
		$("."+tb+"Serial").each(function(){
			serial += 1;
			$(this).text(serial);
		});
		//计算行数
		var length = $("#"+tb+"Tb").children().length;
		$("#"+tb+"Num").val(length);
	});
}

</script>
</c:if>


<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post"
	id="projForm" style="position: relative;">
	<input type="hidden" name="pageName" value="step5" /> <input
		type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}" />
	<input type="hidden" id="projFlow" name="projFlow"
		value="${proj.projFlow}" /> <input type="hidden" name="recTypeId"
		value="${param.recTypeId}" />
	<table class="bs_tb" style="width: 100%; margin-top: 10px;">
		<tr>
			<th colspan="8" class="theader">
				表6：近五年为第一负责人已完成的市级及以上科研项目共 <input type="text" name="completeNum" id="completeNum" value="${resultMap.completeNum}" class="validate[custom[integer]] inputText" style="width: 30px;" />项。（限填10项代表作）
				<c:if test="${param.view!=GlobalConstant.FLAG_Y }">
				<span style="float: right;padding-right: 10px">
					<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="add('complete')"></img>&#12288;
					<img title="删除"  style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delTr('complete');"></img>
				</span>
				</c:if>
			</th>
		</tr>
		<tr>
			<td style="font-weight:bold;" width="4%"></td>
			<td style="font-weight:bold;" width="4%">序号</td>
			<td style="font-weight:bold;" width="20%">课题编号</td>
			<td style="font-weight:bold;" width="20%">项目名称</td>
			<td style="font-weight:bold;" width="15%">项目来源</td>
			<td style="font-weight:bold;" width="20%">项目起迄时间</td>
			<td style="font-weight:bold;" width="10%">项目经费(万元)</td>
			<td style="font-weight:bold;" width="10%">第一完成人</td>
		</tr>
		<tbody id="completeTb" class="tb">
		<c:forEach var="complete" items="${resultMap.complete}" varStatus="status">
			<tr>
				<td style="text-align: center;"><input name="completeIds" type="checkbox"/></td>
				<td style="text-align: center;" class="completeSerial">${status.count}</td>
				<td><input type="text" class="inputText" name="complete_lessonNo" value="${complete.objMap.complete_lessonNo}" style="width: 90%;" /></td>
				<td><input type="text" class="inputText" name="complete_projName" value="${complete.objMap.complete_projName}" style="width: 90%;" /></td>
				<td>
					<select name="complete_projSource" class="inputText" style="width: 90%;">
						<option value="">请选择</option>
						<option value="国家自然科学基金"
							<c:if test="${complete.objMap.complete_projSource eq '国家自然科学基金'}">selected="selected"</c:if>>国家自然科学基金</option>
						<option value="科技部"
							<c:if test="${complete.objMap.complete_projSource eq '科技部'}">selected="selected"</c:if>>科技部</option>
						<option value="国家卫计委"
							<c:if test="${complete.objMap.complete_projSource eq '国家卫计委'}">selected="selected"</c:if>>国家卫计委</option>
						<option value="科技厅"
							<c:if test="${complete.objMap.complete_projSource eq '科技厅'}">selected="selected"</c:if>>科技厅</option>
						<option value="省卫计委"
							<c:if test="${complete.objMap.complete_projSource eq '省卫计委'}">selected="selected"</c:if>>省卫计委</option>
						<option value="科技局"
							<c:if test="${complete.objMap.complete_projSource eq '科技局'}">selected="selected"</c:if>>科技局</option>
						<option value="市卫计委"
							<c:if test="${complete.objMap.complete_projSource eq '卫生局' or complete.objMap.complete_projSource eq '市卫计委'}">selected="selected"</c:if>>市卫计委</option>
					</select>
				</td>
				<td>
					<input type="text" class="inputText" name="complete_projStartTime" value="${complete.objMap.complete_projStartTime}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 40%; margin-right: 0px;" readonly="readonly" />
					~ <input type="text" class="inputText" name="complete_projEndTime" value="${complete.objMap.complete_projEndTime}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 40%;" readonly="readonly" />
				</td>
				<td><input type="text" class="inputText validate[custom[number]]" name="complete_projFund" value="${complete.objMap.complete_projFund}" style="width: 90%;" /></td>
				<td><input type="text" class="inputText" name="complete_firstCompletePerson" value="${complete.objMap.complete_firstCompletePerson}" style="width: 90%;" /></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</form>


<c:if test="${param.view!=GlobalConstant.FLAG_Y}">
	<div align="center" style="margin-top: 10px">
		<input id="prev" type="button" onclick="nextOpt('step4')" class="search" value="上一步"/>
		<input id="nxt" type="button" onclick="nextOpt('step6')" class="search" value="下一步"/>
	</div>
</c:if>	

<div style="display: none;">
	<!-- 模板 -->
	<table id="completeTemplate" width="100%" class="bs_tb">
		<tr>
			<td style="text-align: center;"><input name="completeIds" type="checkbox"/></td>
			<td style="text-align: center;" class="completeSerial"></td>
			<td><input type="text" class="inputText" name="complete_lessonNo" style="width: 90%;" /></td>
			<td><input type="text" class="inputText" name="complete_projName" style="width: 90%;" /></td>
			<td>
				<select name="complete_projSource" class="inputText" style="width: 90%;">
					<option value="">请选择</option>
					<option value="国家自然科学基金">国家自然科学基金</option>
					<option value="科技部">科技部</option>
					<option value="国家卫计委">国家卫计委</option>
					<option value="科技厅">科技厅</option>
					<option value="省卫计委">省卫计委</option>
					<option value="科技局">科技局</option>
					<option value="市卫计委">市卫计委</option>
				</select>
			</td>
			<td>
				<input type="text" class="inputText" name="complete_projStartTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 40%; margin-right: 0px;" readonly="readonly" />
				~ <input type="text" class="inputText" name="complete_projEndTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 40%;" readonly="readonly" />
			</td>
			<td><input type="text" class="inputText validate[custom[number]]" name="complete_projFund" style="width: 90%;" /></td>
			<td><input type="text" class="inputText" name="complete_firstCompletePerson" style="width: 90%;" /></td>
		</tr>
	</table>
</div>
