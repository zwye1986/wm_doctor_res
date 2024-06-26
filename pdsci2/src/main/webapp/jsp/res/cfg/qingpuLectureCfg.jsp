
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_scrollTo" value="false"/>
	<jsp:param name="jquery_jcallout" value="false"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fullcalendar" value="false"/>
	<jsp:param name="jquery_fngantt" value="false"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
</jsp:include>
<style type="text/css">
	.edit3{text-align: center;border:none;}
	.cont_list{
		background:none;
		background-color: #f8f8f8;
		border:1px solid #e3e3e3;
		border-bottom-style: none;
	}
	.cont_list .left .name{
		color: #333;
	}
	[type='text']{width:150px;height: 22px;}
	select{width: 153px;height: 27px;}
</style>
<script type="text/javascript">
function addLectureCfgTitle(){
	var typeId = $("select[name='typeId']").val();
	if(typeId ==""){
		jboxTip("请选择类型！");
		return false;
	}
	$("#addTitleButton").prev().show();
	$("#addTitleButton").hide();
	$("#saveTitleButton").show();
}

function modifyAssessTitle(titleId){
	$("#assessTitle_"+titleId).prev().hide();
	$("#assessTitle_"+titleId).show();
	$(".assessType_"+titleId).toggle();
	$(".assessEvalType_"+titleId).toggle();
}

function saveLectureCfgTitle(titleId){
	var data = "";
	var saveFlag = true;
	if(titleId!=""){//修改名称
		if($("#assessTitle_"+titleId).val().trim()==""){
			saveFlag = false;
			jboxTip("请填写名称！");
			return false;
		}
		data = {
			id : titleId,
			name : $("#assessTitle_"+titleId).val()
		};
	}else{//新增名称
		if(false == $("#lectureTitleForm").validationEngine("validate")){
			saveFlag = false;
			return false;
		}
		data = $("#lectureTitleForm").serialize();
		$("#saveTitleButton").attr("disabled",true);
	}
	if(!saveFlag){
		return false;
	}
	var typeId = $("select[name='typeId'] option:selected").val();
	var url = "<s:url value='/res/qingpuLectureCfg/saveLectureCfgTitle'/>?recordFlow=${lectureEvalCfg.recordFlow}&typeId="+typeId;
	jboxPost(url, data,
			function(resp){
				if(resp == "${GlobalConstant.SAVE_SUCCESSED}"){
					search();
				}
			}, null, true);
}

function search(){
	var typeId = $("select[name='typeId'] option:selected").val();
	var url = "<s:url value='/res/qingpuLectureCfg/lectureCfg'/>?typeId=" + typeId;
	window.location.href = url;
}

function deleteTitle(id){
	jboxConfirm("确认删除？", function() {
		var recordFlow = $("input[name='recordFlow']").val();
		var url = "<s:url value='/res/qingpuLectureCfg/deleteTitle'/>?recordFlow="+recordFlow +"&id=" +id;
		jboxPost(url, null,
				function(resp){
					if("${GlobalConstant.DELETE_SUCCESSED}" == resp){
						search();
					}
				}, 
				null,true);
	});
}

function addItem(titleId){
	$("#"+titleId+"Td").append($("#clone tr:eq(0)").clone());
	$addLastTr = $("#"+titleId+"Td").children("tr:last");
	$addLastTr.find("input[name='titleId']").val(titleId);
	$addLastTr.find("input[name='score']").attr("onblur","scoreCount('"+titleId+"')");
	$("#saveButton").show();
	$("#"+titleId+"noRecoredTd").hide();
	$addLastTr.children("td:last").children().attr("onclick","removeItemTr(this,'"+titleId+"')");
}

function removeItemTr(obj, titleId){
	$(obj).parent().parent().remove();
	var trs = $(".itemTd .addTr");
	if(trs.length<=0){
		$("#saveButton").hide();
	}
	scoreCount(titleId);
	var addTrs = $(".itemTd .addTr");
	if(trs.length<=0){
		$("#"+titleId+"noRecoredTd").show();
	}
}

function save(){
	if(false == $("#assessCfgForm").validationEngine("validate")){
		return false;
	}
	var trs = $(".itemTd .addTr");
	if(trs.length<=0){
		jboxTip("请添加考核指标！");
		return false;
	}
	var datas = [];
	$.each(trs, function(i,n){
		var titleId = $(n).find("input[name='titleId']").val();
		var name = $(n).find("input[name='name']").val();
		var score = $(n).find("input[name='score']").val();
		var order = $(n).find("input[name='order']").val();
		var data={
			"titleId":titleId, 
			"name":name, 
			"score":score,
			"order":order
		};
		datas.push(data);
	});
	var recordFlow = $("input[name='recordFlow']").val();
	var requestData = {"itemFormList":datas, "recordFlow":recordFlow};
	var url = "<s:url value='/res/qingpuLectureCfg/saveLectureCfgItemList'/>";
	$("#saveButton").attr("disabled",true);
	jboxPostJson(
			url,
			JSON.stringify(requestData),
			function(resp){
				if("${GlobalConstant.SAVE_SUCCESSED}" == resp){
					search();
				}
			}, null, true);
}

function changeStyle(obj,stylename){
	$scoreTd = $(obj).parent().parent().prev();
	$score = $scoreTd.children();
	$name = $scoreTd.prev().children();
	$order = $scoreTd.prev().prev().children();
	$score.removeClass(stylename);
	$name.removeClass(stylename);
	$order.removeClass(stylename);
	$score.removeAttr("readonly");
	$name.removeAttr("readonly");
	$order.removeAttr("readonly");
	$(obj).parent().hide();
	$(obj).parent().next().show();
}

function modifyItem(obj, itemId, titleId){
	if(false == $("#assessCfgForm").validationEngine("validate")){
		return false;
	}
	$scoreTd = $(obj).parent().parent().prev();
	$score =$scoreTd.children();
	$name = $scoreTd.prev().children();
	$order = $scoreTd.prev().prev().children();
	$score.addClass("edit3");
	$name.addClass("edit3");
	$order.addClass("edit3")
	$score.attr("readonly","readonly");
	$name.attr("readonly","readonly");
	$order.attr("readonly","readonly");
	$(obj).parent().hide();
	$(obj).parent().prev().show();
	if($name.val()==$name.attr("oldvalue") && $score.val()==$score.attr("oldvalue")&& $order.val()==$$order.attr("oldvalue")){
		jboxTip("没有修改！");
		return;
	}
	var recordFlow = $("input[name='recordFlow']").val();
	var data = {
			recordFlow:recordFlow,
			id:itemId,
			name:$name.val(),
			score:$score.val(),
			order:$order.val()
	};
	jboxPost("<s:url value='/res/qingpuLectureCfg/modifyItem'/>",data,
			function(resp){
				if("${GlobalConstant.SAVE_SUCCESSED}" == resp){
					scoreCount(titleId);
					$name.attr("oldvalue",$name.val());
					$score.attr("oldvalue",$score.val());
					$order.attr("oldvalue",$order.val());
				}
			}
			,null,true);
}

function deleteItem(id){
	jboxConfirm("确认删除？", function() {
		var recordFlow = $("input[name='recordFlow']").val();
		var url = "<s:url value='/res/qingpuLectureCfg/deleteItem'/>?recordFlow="+recordFlow +"&id=" +id;
		jboxPost(url, null,
				function(resp){
					if("${GlobalConstant.DELETE_SUCCESSED}" == resp){
						search();
					}
				}, 
				null,true);
	});
}

function scoreCount(titleId){
	var trs = $("#"+titleId+"Td").children();
	var scoreCount = 0;
	$.each(trs, function(i,n){
		var score = $(n).find("input[name='score']").val();
		if (score == null || score == undefined || score == '' || isNaN(score)){
			score = 0;
		}
		scoreCount += parseFloat(score);
	});
	$("#scoreCount_"+titleId).text(scoreCount);
}

$(document).ready(function(){
	<c:forEach items="${titleFormList}" var="title">
		scoreCount("${title.id}");
	</c:forEach>
});
</script>
</head>
<body>
<div class="mainright">
<div class="content">
	<div class="title1 clearfix">
		<div style="width: auto;height: 30px;">
		<div style="float: left">
		类型：
		<select name="typeId" onchange="search();" class="validate[required]">
			<option value="">请选择</option>
			<c:forEach var="type" items="${deptActivityItemTypeEnumList}">
				<c:if test="${sysCfgMap['dept_activity_type'] eq 'Y'||type.isCfg eq 'Y'}">
				<option value="${type.id}" ${param.typeId eq type.id?'selected':''}>${type.name}</option>
				</c:if>
			</c:forEach>
		</select>
		</div>
		<div style="float: left;">
		<form id="lectureTitleForm">
			<input type="hidden" name="id" id="titleId"/>
			<span style="display:none;">
				&#12288;名&#12288;称：
				<input type="text" name="name" id="titleName" class="validate[required,maxSize[50]]" />
			</span>
			<input class="search" type="button" id="addTitleButton" value="新&#12288;增" onclick="addLectureCfgTitle();"/>
			<input class="search" type="button" id="saveTitleButton" value="保&#12288;存" onclick="saveLectureCfgTitle('');" style="display: none;"/>
		</form>
		</div>
		</div>
		
		<form id="assessCfgForm" method="post" style="position: relative;margin-top: 10px;">
			<input type="hidden" name="recordFlow" value="${lectureEvalCfg.recordFlow}"/>
			<!-- 标题 -->
			<c:forEach items="${titleFormList}" var="title">
				<div class="cont_list" style="margin-top: 15px;">
					<div class="left" style="width: 910px;">
						名&#12288;称：
						<span class="name" style="width: 300px;">
						<font>${title.name}</font>
						<input id="assessTitle_${title.id}" type="text" class="validate[required,maxSize[50]]" style="display: none;" onblur="saveLectureCfgTitle('${title.id}');" value="${title.name}"/>
						</span>

						<span class="name" style="width: 100px;">
						总&#12288;分：<font id="scoreCount_${title.id}"></font>
						</span>

					</div>
					<div class="right" style="width: 100px;float:right;padding-right: 15px;">
						<a style="cursor: pointer;" onclick="addItem('${title.id}')"><img alt="新增" title="新增" src="<s:url value="/css/skin/Blue/images/add.png"/>"/></a>&nbsp;&nbsp;
						<a style="cursor: pointer;" onclick="modifyAssessTitle('${title.id}')"><img alt="修改" title="修改" src="<s:url value="/css/skin/Blue/images/modify.png"/>"/></a>&nbsp;
						<a style="cursor: pointer;" onclick="deleteTitle('${title.id}')"><img alt="删除" title="删除" src="<s:url value="/css/skin/Blue/images/del2.png"/>"></a>
					</div>
				</div>
				<div style="padding-top: 0px;">
					<table class="xllist"  cellpadding="0" cellspacing="0" style="width: 100%">
						<colgroup>
                            <col width="10%"/>
							<col width="70%"/>
                            <col width="10%"/>
                            <col width="20%"/>
						</colgroup>
						<tr>
							<th style="text-align: center;">项目</th>
							<th style="text-align: center;">测评内容</th>
							<th style="text-align: center;">分值</th>
							<th style="text-align: center;">操作</th>
						</tr>
						<!-- item -->
						<tbody id="${title.id}Td" class="itemTd">
						<c:forEach items="${title.itemList}" var="item" varStatus="status">
						  <tr>
						    <td>
							    <input type="text" class="validate[required,custom[number]] edit3" style="text-align: left;width: 90%;text-align: center;" oldvalue="${item.order}" value="${item.order}" readonly="readonly"/>
						    </td>

						    <td>
					   		 	<input type="text" class="validate[required,maxSize[50]] edit3" style="text-align: left;width: 90%;text-align: center;" oldvalue="${item.name}" value="${item.name}" readonly="readonly"/>
						    </td>
						    
						    <td>
						    	<input type="text" name="score" class="validate[required,custom[number]] edit3" style="text-align: left;width: 90%;text-align: center;" oldvalue="${item.score}" value="${item.score}" readonly="readonly"/>
						    </td>
						    
						    <td>
								<span>[<a href="javascript:void(0)" onclick="changeStyle(this,'edit3');">编辑</a>]</span>
							    <span style="display: none;">[<a href="javascript:void(0)" onclick="modifyItem(this,'${item.id}','${title.id}')">保存</a>]</span>
							    | [<a href="javascript:void(0)" onclick="deleteItem('${item.id}')">删除</a>]
							</td>
						  </tr>
						</c:forEach>
						</tbody>
						<c:if test="${empty title.itemList}">
						<tr  id="${title.id}noRecoredTd">
                            <td colspan="10">无记录！</td>
						</tr>
						</c:if>
					</table>
				</div>
			</c:forEach>
		</form>
		<c:if test="${empty titleFormList}">
		  	<table width="100%" class="basic">
				<tr>
					<th style="text-align: center;">无记录！</th>
				</tr>
			</table>
		</c:if>
		<div class="button" style="width: 100%;">
			<input class="search" type="button" id="saveButton" style="display: none;" value="保&#12288;存" onclick="save();" />
		</div>
	</div>
</div>
</div>

<table style="display: none;" id="clone">
	<tr class="addTr">
		<td>
			<input type="text" name="order" class="validate[required,custom[number]]" style="width:90%;"/>
		</td>
		<td>
			<input type="hidden" name="titleId"/>
			<input type="text" name="name" class="validate[required,maxSize[50]]" style="width:90%;"/>
		</td>
		<td>
			<input type="text" name="score" class="validate[required,custom[number]]" style="width:90%;"/>
		</td>
		<td>
			[<a href="javascript:void(0)" >删除</a>]
		</td>
	</tr>
</table>
</body>
</html>