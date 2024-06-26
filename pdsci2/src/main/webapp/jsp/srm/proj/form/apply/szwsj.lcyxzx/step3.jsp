<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	if($("#fruitTb tr").length<=0){
		add('fruit');
	}
	if($("#topicTb tr").length<=0){
		add('topic');
	}
	if($("#thesisTb tr").length<=0){
		add('thesis');
	}
	if($("#magazineTb tr").length<=0){
		add('magazine');
	}
	if($("#groupTb tr").length<=0){
		add('group');
	}
});

function add(tb){
	var length = $("#"+tb+"Tb").children().length;
	if(length > 4){
		jboxTip("限填5项！");
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

<font style="font-size: 14px; font-weight: bold; color: #333;">表4：临床医学中心带头人主要业绩</font>
<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" style="position: relative;">
	<input type="hidden" name="pageName" value="step3" /> 
	<input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}" />
	<input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}" /> 
	<input type="hidden" name="recTypeId" value="${param.recTypeId}" />
	
	<table class="bs_tb" style="width: 100%">
		<tr>
			<th colspan="6" class="theader">
				1、近五年为第一完成人的市级及以上科研成果（含专利、新药证书）共<input type="text" name="fruitNum" id="fruitNum" value="${resultMap.fruitNum}" style="width: 30px;" class="inputText" /> 项，限填5项代表作。 
				<c:if test="${param.view!=GlobalConstant.FLAG_Y }">
				<span style="float: right;padding-right: 10px">
					<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="add('fruit')"/>&#12288;
					<img title="删除"  style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delTr('fruit');"/>
				</span>
				</c:if>
			</th>
		</tr>
		<tr>
			<td style="font-weight:bold;" width="30px"></td>
			<td style="font-weight:bold;" width="30px">序号</td>
			<td style="font-weight:bold;" width="15%">授予时间</td>
			<td style="font-weight:bold;" width="30%">成果名称</td>
			<td style="font-weight:bold;" width="30%">授予部门</td>
			<td style="font-weight:bold;" width="15%">等级</td>
		</tr>
		<tbody id="fruitTb">
		<c:forEach var="fruit" items="${resultMap.fruit}" varStatus="status">
			<tr>
				<td><input name="fruitIds" type="checkbox" /></td>
				<td style="text-align: center;" class="fruitSerial">${status.count}</td>
				<td>
					<input type="text" class="inputText" name="fruit_fruitAwardTime" value="${fruit.objMap.fruit_fruitAwardTime}" style="width: 125px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
				</td>
				<td><input type="text" class="inputText" name="fruit_fruitName" value="${fruit.objMap.fruit_fruitName}" style="width: 90%;" /></td>
				<td><input type="text" class="inputText" name="fruit_fruitAwardOrg" value="${fruit.objMap.fruit_fruitAwardOrg}" style="width: 90%;" /></td>
				<td><input type="text" class="inputText" name="fruit_fruitGrade" value="${fruit.objMap.fruit_fruitGrade}" style="width: 90%;" /></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
	<table class="bs_tb" style="width: 100%;margin-top: 10px;">
		<tr>
			<th colspan="7" class="theader">
				2、近五年为第一负责人的市级及以上科研课题共<input type="text" name="topicNum" id="topicNum" value="${resultMap.topicNum}" style="width: 30px;" class="inputText" />项，限填5项代表作。 
				<c:if test="${param.view!=GlobalConstant.FLAG_Y }">
				<span style="float: right;padding-right: 10px">
					<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="add('topic')"/>&#12288;
					<img title="删除"  style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delTr('topic');"/>
				</span>
				</c:if>
			</th>
		</tr>
		<tr>
			<td style="font-weight:bold;" width="30px"></td>
			<td style="font-weight:bold;" width="30px">序号</td>
			<td style="font-weight:bold;" width="15%">课题编号</td>
			<td style="font-weight:bold;" width="25%">课题名称</td>
			<td style="font-weight:bold;" width="15%">课题来源</td>
			<td style="font-weight:bold;" width="10%">经费<br />(万元)</td>
			<td style="font-weight:bold;" width="27%">起止时间</td>
		</tr>
		<tbody id="topicTb">
		<c:forEach var="topic" items="${resultMap.topic}" varStatus="status">
			<tr>
				<td style="text-align: center;"><input name="topicIds" type="checkbox" /></td>
				<td style="text-align: center;" class="topicSerial">${status.count}</td>
				<td><input type="text" class="inputText" name="topic_topicNo" value="${topic.objMap.topic_topicNo}" style="width: 90%;" /></td>
				<td><input type="text" class="inputText" name="topic_topicName" value="${topic.objMap.topic_topicName}" style="width: 90%;" /></td>
				<td><input type="text" class="inputText" name="topic_topicSource" value="${topic.objMap.topic_topicSource}" style="width: 90%;" /></td>
				<td><input type="text" class="inputText validate[custom[number]]" name="topic_topicOutlay" value="${topic.objMap.topic_topicOutlay}" style="width: 90%;" /></td>
				<td>
					<input type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="inputText" name="topic_topicStartTime" value="${topic.objMap.topic_topicStartTime}" style="width: 125px;margin: 0px;" readonly="readonly" />
					~ <input type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="inputText" name="topic_topicEndTime" value="${topic.objMap.topic_topicEndTime}" style="width: 125px;margin: 0px;" readonly="readonly" />
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
	<table class="bs_tb" style="width: 100%;margin-top: 10px;">
		<tr>
			<th colspan="6" class="theader">
				3、近五年为第一作者发表的SCI和中华级学术论文共<input type="text" name="thesisNum" id="thesisNum" value="${resultMap.thesisNum}" style="width: 30px;" class="inputText" />篇，限填5项代表作。 
				<c:if test="${param.view!=GlobalConstant.FLAG_Y }">
				<span style="float: right;padding-right: 10px">
					<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="add('thesis')"/>&#12288;
					<img title="删除"  style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delTr('thesis');"/>
				</span>
				</c:if>
			</th>
		</tr>
		<tr>
			<td style="font-weight:bold;" width="30px"></td>
			<td style="font-weight:bold;" width="30px">序号</td>
			<td style="font-weight:bold;" width="16%">发表时间</td>
			<td style="font-weight:bold;" width="37%">论文名称</td>
			<td style="font-weight:bold;" width="37%">刊物名称</td>
		</tr>
		<tbody id="thesisTb">
		<c:forEach var="thesis" items="${resultMap.thesis}" varStatus="status">
			<tr>
				<td><input name="thesisIds" type="checkbox" /></td>
				<td style="text-align: center;" class="thesisSerial">${status.count}</td>
				<td><input type="text" class="inputText" name="thesis_thesisPublishTime" value="${thesis.objMap.thesis_thesisPublishTime}" style="width: 125px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" /></td>
				<td><input type="text" class="inputText" name="thesis_thesisName" value="${thesis.objMap.thesis_thesisName}" style="width: 90%;" /></td>
				<td><input type="text" class="inputText" name="thesis_thesisPublicationName" value="${thesis.objMap.thesis_thesisPublicationName}" style="width: 90%;" /></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
	<table class="bs_tb" style="width: 100%;margin-top: 10px;">
		<tr>
			<th colspan="6" class="theader">
				4、近五年学术杂志任职情况，共<input type="text" name="magazineNum" id="magazineNum" value="${resultMap.magazineNum}" style="width: 30px;" class="inputText" />份杂志，限填5份代表杂志 
				<c:if test="${param.view!=GlobalConstant.FLAG_Y }">
				<span style="float: right;padding-right: 10px">
					<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="add('magazine')"/>&#12288;
					<img title="删除"  style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delTr('magazine');"/>
				</span>
				</c:if>
			</th>
		</tr>
		<tr>
			<td style="font-weight:bold;" width="30px"></td>
			<td style="font-weight:bold;" width="30px">序号</td>
			<td style="font-weight:bold;" width="16%">任期年限</td>
			<td style="font-weight:bold;" width="37%">学术杂志名称</td>
			<td style="font-weight:bold;" width="37%">主编/副主编/编委</td>
		</tr>
		<tbody id="magazineTb">
		<c:forEach var="magazine" items="${resultMap.magazine}" varStatus="status">
			<tr>
				<td><input name="magazineIds" type="checkbox" /></td>
				<td style="text-align: center;" class="magazineSerial">${status.count}</td>
				<td><input type="text" class="inputText" name="magazine_magazineAppointYear" value="${magazine.objMap.magazine_magazineAppointYear}" style="width: 90%;" /></td>
				<td><input type="text" class="inputText" name="magazine_magazineName" value="${magazine.objMap.magazine_magazineName}" style="width: 90%;" /></td>
				<td><input type="text" class="inputText" name="magazine_magazineTitle" value="${magazine.objMap.magazine_magazineTitle}" style="width: 90%;" /></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
	<table class="bs_tb" style="width: 100%;margin-top: 10px;">
		<tr>
			<th colspan="6" class="theader" style="text-align: left;padding-left: 10px;">
				5、近五年学会任职情况，共<input type="text" name="groupNum" id="groupNum" value="${resultMap.groupNum}" style="width: 30px;" class="inputText" />个职务，限填5项最高职务。 
				<c:if test="${param.view!=GlobalConstant.FLAG_Y }">
				<span style="float: right;padding-right: 10px">
					<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="add('group')"/>&#12288;
					<img title="删除"  style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delTr('group');"/>
				</span>
				</c:if>
			</th>
		</tr>
		<tr>
			<td style="font-weight:bold;" width="30px"></td>
			<td style="font-weight:bold;" width="30px">序号</td>
			<td style="font-weight:bold;" width="16%">任期年限</td>
			<td style="font-weight:bold;" width="37%">学会（分会、学组）名称</td>
			<td style="font-weight:bold;" width="37%">主委/副主委/组长/常委/委员</td>
		</tr>
		<tbody id="groupTb">
		<c:forEach var="group" items="${resultMap.group}" varStatus="status">
			<tr>
				<td><input name="groupIds" type="checkbox" /></td>
				<td style="text-align: center;" class="groupSerial">${status.count}</td>
				<td><input type="text" class="inputText" name="group_groupAppointYear" value="${group.objMap.group_groupAppointYear}" style="width: 90%;" /></td>
				<td><input type="text" class="inputText" name="group_groupName" value="${group.objMap.group_groupName}" style="width: 90%;" /></td>
				<td><input type="text" class="inputText" name="group_groupTitle" value="${group.objMap.group_groupTitle}" style="width: 90%;" /></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</form>

<c:if test="${param.view!=GlobalConstant.FLAG_Y}">
	<div align="center" style="margin-top: 10px">
		<input id="prev" type="button" onclick="nextOpt('step2')" class="search" value="上一步"/>
		<input id="nxt" type="button" onclick="nextOpt('step4')" class="search" value="下一步"/>
	</div>
</c:if>	



<div style="display: none;">
	<!-- 模板 1-->
	<table id="fruitTemplate" width="100%" class="bs_tb">
		<tr>
			<td style="text-align: center;"><input name="fruitIds" type="checkbox"/></td>
			<td style="text-align: center;" class="fruitSerial"></td>
			<td>
				<input type="text" class="inputText" name="fruit_fruitAwardTime" value="${fruit.objMap.fruit_fruitAwardTime}" style="width: 125px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
			</td>
			<td><input type="text" class="inputText" name="fruit_fruitName" value="${fruit.objMap.fruit_fruitName}" style="width: 90%;" /></td>
			<td><input type="text" class="inputText" name="fruit_fruitAwardOrg" value="${fruit.objMap.fruit_fruitAwardOrg}" style="width: 90%;" /></td>
			<td><input type="text" class="inputText" name="fruit_fruitGrade" value="${fruit.objMap.fruit_fruitGrade}" style="width: 90%;" /></td>
		</tr>
	</table>
	
	<!-- 模板  2-->
	<table id="topicTemplate" width="100%" class="bs_tb">
		<tr>
			<td style="text-align: center;"><input name="topicIds" type="checkbox" /></td>
			<td style="text-align: center;" class="topicSerial"></td>
			<td><input type="text" class="inputText" name="topic_topicNo" style="width: 90%;" /></td>
			<td><input type="text" class="inputText" name="topic_topicName" style="width: 90%;" /></td>
			<td><input type="text" class="inputText" name="topic_topicSource" style="width: 90%;" /></td>
			<td><input type="text" class="inputText validate[custom[number]]" name="topic_topicOutlay" style="width: 90%;" /></td>
			<td>
				<input type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="inputText" name="topic_topicStartTime" style="width: 125px;margin: 0px;" readonly="readonly" />
				~ <input type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="inputText" name="topic_topicEndTime" style="width: 125px;margin: 0px;" readonly="readonly" />
			</td>
		</tr>
	</table>
	
	<!-- 模板 3-->
	<table id="thesisTemplate" width="100%" class="bs_tb" >
		<tr>
			<td><input name="thesisIds" type="checkbox" /></td>
			<td style="text-align: center;" class="thesisSerial"></td>
			<td><input type="text" class="inputText" name="thesis_thesisPublishTime" style="width: 125px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" /></td>
			<td><input type="text" class="inputText" name="thesis_thesisName" style="width: 90%;" /></td>
			<td><input type="text" class="inputText" name="thesis_thesisPublicationName" style="width: 90%;" /></td>
		</tr>
	</table>
	
	<!-- 模板  4-->
	<table id="magazineTemplate" width="100%" class="bs_tb" >
		<tr>
			<td><input name="magazineIds" type="checkbox" /></td>
			<td style="text-align: center;" class="magazineSerial"></td>
			<td><input type="text" class="inputText" name="magazine_magazineAppointYear" style="width: 90%;" /></td>
			<td><input type="text" class="inputText" name="magazine_magazineName" style="width: 90%;" /></td>
			<td><input type="text" class="inputText" name="magazine_magazineTitle" style="width: 90%;" /></td>
		</tr>
	</table>
	
	<!-- 模板 5-->
	<table id="groupTemplate" width="100%" class="bs_tb" >
		<tr>
			<td><input name="groupIds" type="checkbox" /></td>
			<td style="text-align: center;" class="groupSerial"></td>
			<td><input type="text" class="inputText" name="group_groupAppointYear" style="width: 90%;" /></td>
			<td><input type="text" class="inputText" name="group_groupName" style="width: 90%;" /></td>
			<td><input type="text" class="inputText" name="group_groupTitle" style="width: 90%;" /></td>
		</tr>
	</table>

</div>