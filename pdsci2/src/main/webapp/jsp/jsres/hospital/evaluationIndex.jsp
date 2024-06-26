<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="font" value="true"/>
		<jsp:param name="jquery_validation" value="true"/>
		<jsp:param name="jquery_placeholder" value="true"/>
		<jsp:param name="jquery_ui_combobox" value="true"/>
	</jsp:include>
	<script type="text/javascript">
		$(document).ready(function(){
			<c:forEach items="${titleFormList}" var="title">
				scoreCount("${title.id}");
			</c:forEach>
		});
		function addFromTitle(){
			$("#titleParam").show();
			$("#addTitleButton").hide();
		}
		function cancelSaveFromTitle(){
			$("#titleParam").hide();
			$("#name").val("");
			$("#addTitleButton").show();
		}
		//编辑评分项目名称
		function editTitle(titleId){
			$("#assessType_"+titleId).prev().hide();
			$("#assessType_"+titleId).show();
		}
		//保存评分项目
		function saveFromTitle(titleId,index){
			var data = "";
			if(titleId != ""){//修改名称
				if(!$("#cfgForm").validationEngine("validate")){
					return false;
				}
				data = {
					configFlow : '${evalConfig.configFlow}',
					id : titleId,
					name : $("#assessType_"+titleId).val(),
					orderNum : $(".bigOrder_"+index).val()
				};
			}else {
				if(!$("#fromTitleForm").validationEngine("validate")){
					return false;
				}
				$("#saveTitleButton").attr("disabled",true);
				//给评分项目加一个排序码
				$("#orderNum").val('${fn:length(titleFormList)+1}');
				data = $("#fromTitleForm").serialize();
			}
			var url = "<s:url value='/jsres/manage/saveEvalConfig'/>";
			jboxPost(url, data, function(resp){
				if("${GlobalConstant.SAVE_SUCCESSED}" == resp){
					evaluationIndexMaintenance();
				}
			}, null, true);
		}
		//新增评分指标行
		function addItem(titleId){
			$("#"+titleId+"Td").append($("#clone tr:eq(0)").clone());
			$addLastTr = $("#"+titleId+"Td").children("tr:last");
			$addLastTr.find("input[name='titleId']").val(titleId);
			$addLastTr.find("input[name='score']").attr("onblur","scoreCount('"+titleId+"')");
			$addLastTr.children("td:last").children().attr("onclick","removeItemTr(this,'"+titleId+"')");//删除
			$("#saveButton").show();
			$("#"+titleId+"noRecoredTd").hide();
		}
		//删除新增的评分指标行
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
		//编辑评分指标分数，计算总分
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
		//删除考评项目
		function deleteTitle(id){
			jboxConfirm("确认删除此评分项目？", function() {
				var configFlow = $("input[name='configFlow']").val();
				var url = "<s:url value='/jsres/manage/deleteTitle'/>?configFlow="+configFlow +"&id=" +id;
				jboxPost(url, null,function(resp){
					if("${GlobalConstant.DELETE_SUCCESSED}" == resp){
						evaluationIndexMaintenance();
					}
				},null,true);
			});
		}
		//删除考评指标
		function deleteItem(id){
			jboxConfirm("确认删除此评分指标？", function() {
				var configFlow = $("input[name='configFlow']").val();
				var url = "<s:url value='/jsres/manage/deleteItem'/>?configFlow="+configFlow +"&id=" +id;
				jboxPost(url, null,function(resp){
					if("${GlobalConstant.DELETE_SUCCESSED}" == resp){
						evaluationIndexMaintenance();
					}
				},null,true);
			});
		}
		//编辑评分指标
		function changeStyle(obj){
			$inputList = $(obj).closest("tr").find("input");
			$name = $inputList.eq(1);
			$name.removeAttr("readonly");
			$name.css({"text-align":"center","border":"1px solid grey","width":"50%"});
			$score = $inputList.eq(2);
			$score.removeAttr("readonly");
			$score.css({"text-align":"center","border":"1px solid grey","width":"80px"});
			$(obj).parent().hide();
			$(obj).parent().next().show();
		}
		//保存评分指标
		function modifyItem(obj, itemId){
			$inputList = $(obj).closest("tr").find("input");
			$name = $inputList.eq(1);
			$score = $inputList.eq(2);
			if($.trim($name.val()) == "" || $.trim($score.val()) == ""){
				return false;
			}
			if($name.val() == $name.attr("oldvalue") && $score.val() == $score.attr("oldvalue")){
				jboxTip("未修改评分指标或分数！");
				return;
			}
			//var re = (/^[1-9][0-9][0-9]*$/).test($score.val()) ;
			var re = (/^[1-9]\d*$/).test($score.val()) ;
			if(!re)
			{
				jboxTip("请输入正整数！");
				return;
			}
			var data = {
				configFlow:'${evalConfig.configFlow}',
				id:itemId,
				name:$name.val(),
				score:$score.val(),
				order:$inputList.eq(0).val()
			};
			jboxPost("<s:url value='/jsres/manage/modifyItem'/>",data,function(resp){
				if("${GlobalConstant.SAVE_SUCCESSED}" == resp){
					evaluationIndexMaintenance();
				}
			},null,true);
		}
		//保存所有项目评分指标
		function save(){
			if(!$("#cfgForm").validationEngine("validate")){
				return false;
			}
			var trs = $(".itemTd .addTr");
			if(trs.length<=0){
				jboxTip("请添加评分指标！");
				return false;
			}
			//给所有新增的评分指标加一个排序码
			for(var i = 0;i < $(".littleOrder").length-1; i++) {
				var val = $(".littleOrder").eq(i).val();
				if(val=="" || val==null){
					if(i==0){
						$(".littleOrder").eq(i).val(1);
					}else{
						$(".littleOrder").eq(i).val(parseInt($(".littleOrder").eq(i-1).val())+1);
					}
				}
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
			var requestData = {"itemFormList":datas, "configFlow":'${evalConfig.configFlow}'};
			var url = "<s:url value='/jsres/manage/saveEvalItemList'/>";
			$("#saveButton").attr("disabled",true);
			jboxPostJson(url,JSON.stringify(requestData),function(resp){
				if("${GlobalConstant.SAVE_SUCCESSED}" == resp){
					evaluationIndexMaintenance();
				}
			}, null, true);
		}
	</script>
</head>
<body>
	<%--<div class="main_hd">--%>
		<%--<h2 class="underline">考评指标维护</h2>--%>
	<%--</div>--%>
	<div class="main_bd">
		<div class="div_search">
			<form id="fromTitleForm">
				<input type="hidden" name="configFlow" value="${evalConfig.configFlow}"/>
				<input type="hidden" name="id"/>
				<input type="hidden" name="orderNum" id="orderNum"/>
				<input class="btn_green" type="button" style="margin:5px 10px 0px 40px;float:left;" id="addTitleButton" value="添加考评项目" onclick="addFromTitle();"/>
				<div id="titleParam" style="display:none;float:left;margin:5px 10px 0px 40px;">
					评分项目：<input type="text" name="name" id="name" class="input validate[required]" />
					&#12288;<input class="btn_green" type="button" id="saveTitleButton" value="保&#12288;存" onclick="saveFromTitle('','');"/>
					&#12288;<input class="btn_green" type="button" id="cancelSaveTitleButton" value="取&#12288;消" onclick="cancelSaveFromTitle();"/>
				</div>
			</form>
		</div>
		<div class="search_table" style="padding-top: 40px;">
			<form id="cfgForm" >
				<input type="hidden" name="configFlow" value="${evalConfig.configFlow}"/>
				<!-- 标题 -->
				<c:forEach items="${titleFormList}" var="title" varStatus="s">
					<table class="grid">
						<input id="assessOrder_${title.id}" class="bigOrder_${s.index+1}" type="hidden" value="${title.orderNum}"/>
						<tr>
							<th>评分项目：
								<span class="name" style="width: 150px;">
									<font>${title.name}</font>
									<input id="assessType_${title.id}" type="text" class="validate[required]" style="display:none;border:1px solid grey;" onblur="saveFromTitle('${title.id}','${s.index+1}');" value="${title.name}"/>
								</span>
							</th>
							<th>总分：<font id="scoreCount_${title.id}"></font></th>
							<th colspan="2">
								<a style="cursor: pointer;" onclick="addItem('${title.id}')"><img alt="新增" title="新增" src="<s:url value="/css/skin/Blue/images/add.png"/>"/></a>&nbsp;&nbsp;
								<a style="cursor: pointer;" onclick="editTitle('${title.id}');"><img alt="修改" title="修改" src="<s:url value="/css/skin/Blue/images/modify.png"/>"/></a>&nbsp;
								<a style="cursor: pointer;" onclick="deleteTitle('${title.id}')"><img alt="删除" title="删除" src="<s:url value="/css/skin/Blue/images/del2.png"/>"></a>
							</th>
						</tr>
						<tr>
							<th colspan="2" style="width:75%;">评分指标</th>
							<th style="width:10%;">分数</th>
							<th style="width:15%;">操作</th>
						</tr>
						<tbody id="${title.id}Td" class="itemTd">
							<c:forEach items="${title.itemList}" var="item" varStatus="status">
								<tr>
									<td colspan="2">
										<input type="hidden" class="littleOrder" name="order" value="${item.order}"/>
										<input type="text" name="name" class="validate[required]" title="${item.name}" oldvalue="${item.name}" value="${pdfn:cutString(item.name,45,true,6)}" style="text-align:center;height:20px;line-height:20px;width:100%;" readonly="readonly"/>
									</td>
									<td>
										<input type="text" name="score" class="validate[required,custom[number]]" oldvalue="${item.score}" value="${item.score}" style="text-align:center;height:20px;line-height:20px;" readonly="readonly"/>
									</td>
									<td>
										<span>[<a onclick="changeStyle(this)">编辑</a>]</span>
										<span style="display: none;">[<a onclick="modifyItem(this,'${item.id}')">保存</a>]</span>
										[<a onclick="deleteItem('${item.id}')">删除</a>]
									</td>
								</tr>
							</c:forEach>
						</tbody>
						<c:if test="${empty title.itemList}">
							<tr  id="${title.id}noRecoredTd">
								<td colspan="4">无记录！</td>
							</tr>
						</c:if>
					</table>
				</c:forEach>
			</form>
			<div style="margin-top:10px;text-align:center;">
				<input class="btn_green" type="button" id="saveButton" style="display:none;" value="保&#12288;存" onclick="save();" />
			</div>
			<c:if test="${empty titleFormList}">
				<table width="100%" class="grid">
					<tr>
						<th>无记录！</th>
					</tr>
				</table>
			</c:if>
		</div>
	</div>
	<table style="display:none;" id="clone">
		<tr class="addTr">
			<td colspan="2">
				<input type="hidden" name="titleId"/>
				<input type="hidden" class="littleOrder" name="order" />
				<input type="text" name="name" class="validate[required]" style="border:1px solid grey;width:50%;text-align:center;"/>
			</td>
			<td><input type="text" name="score" class="validate[required,custom[number]]" style="border:1px solid grey;width:80px;text-align:center;"/></td>
			<td>[<a>删除</a>]</td>
		</tr>
	</table>
</body>
</html>