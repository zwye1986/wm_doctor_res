<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
		<jsp:param name="basic" value="true" />
		<jsp:param name="font" value="true" />
		<jsp:param name="jquery_validation" value="true" />
	</jsp:include>
	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<style type="text/css">
		.div_table table{
			border-collapse: collapse;
			border: 1px solid #D3D3D3;
		}
		.div_table table td {
			border-top: 0;
			border-right: 1px solid #D3D3D3;
			border-bottom: 1px solid #D3D3D3;
			border-left: 0;
			padding-left: 4px;
			padding-right: 2px;
		}
		.div_table table th {
			border-top: 0;
			border-right: 1px solid #D3D3D3;
			border-bottom: 1px solid #D3D3D3;
			border-left: 0;
		}
		.div_table table tr.lastrow td {
			border-bottom: 0;
		}
		.div_table table tr td.lastCol {
			border-right: 0;
		}
	</style>
	<script type="text/javascript">
		$(function () {
			<c:forEach items="${signList}" var="sign" varStatus="status">
			var id = "ratateImg${status.index+1}"
			var viewer = new Viewer(document.getElementById(id), {
				url: 'data-original'
			});
			</c:forEach>
		});
		$(document).ready(function () {
			$(".showInfo").on("mouseenter mouseleave",
					function () {
						$(".theInfo", this).toggle(100);
					}
			);
			$(".show").on("mouseenter mouseleave",
					function () {
						$(".info", this).toggle(100);
					}
			);

		});

		//保存总分  日期
		function submitScore() {
			top.jboxConfirm("提交之后无法修改，确认提交？", function () {
				if ('${roleFlag}' == 'local') {
					var expertTotal = $('#expertTotalled').text();
					var evaDate = $('#evaluationDate').val();
					var data = {
						"userFlow": '${userFlow}',
						"subjectFlow": "${subjectFlow}",
						"speScoreTotal": expertTotal,
						"evaluationDate": evaDate,
						"roleFlag": '${roleFlag}'
					};
					var url = "<s:url value='/jsres/supervisio/saveSpeScoreTotal'/>";
					top.jboxPost(url, data, function (resp) {
						if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
							top.jboxTip(resp);
							top.jboxCloseMessager();
						} else {
							top.jboxTip(resp);
						}
					}, null, false);
				}

				if ('${roleFlag}' == 'expert' || '${roleFlag}' == 'expertLeader') {
					var signUrl = "${speSignUrl}";
					if (signUrl == "") {
						top.jboxTip("请上传签名图片");
						return false;
					}
					var expertTotal = $('#expertTotalled').text();
					var evaDate = $('#evaluationDate').val();
					var data = {
						"userFlow": '${userFlow}',
						"subjectFlow": "${subjectFlow}",
						"speScoreTotal": expertTotal,
						"evaluationDate": evaDate
					};
					var url = "<s:url value='/jsres/supervisio/saveSpeScoreTotal'/>";
					top.jboxPost(url, data, function (resp) {
						if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
							top.jboxTip(resp);
							top.jboxCloseMessager();
						} else {
							top.jboxTip(resp);
						}
					}, null, false);
				}
			});
		}

		//打印
		function printEvaScore() {
			top.jboxExp(null, "<s:url value='/jsres/supervisio/scoreDownload?userFlow=${userFlow}&subjectFlow=${subjectFlow}&roleFlag=${roleFlag}'/>");
		}

		//保存自评分
		function saveScore(expl, num) {
			var score = expl.value;
			var itemId = expl.getAttribute("itemId");
			var itemName = expl.getAttribute("itemName");
			var selfTotalled = $("#selfTotalled").text();
			var reg = /^\d+(\.\d)?$/;
			var reg1 = /^\d+(\.0)$/;
			if (score >= 0 && score <= num && reg.test(score)) {
				if (reg1.test(score)) {
					var score1 = parseInt(score);
					expl.value = score1;
				} else {
					var score1 = score;
				}
				$(expl).attr("preValue", score1);
				var url = "<s:url value='/jsres/supervisio/saveOwnerScore'/>";
				var data = {
					"itemId": itemId,
					"itemName": itemName,
					"score": score1,
					"orgFlow": '${orgFlow}',
					"speId": '${speId}',
					"subjectFlow": '${subjectFlow}',
					"fileRoute": '${fileRoute}'
				};
				top.jboxPost(url, data, function (resp) {
					if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
						top.jboxTip(resp);
					} else {
						top.jboxTip(resp);
					}
				}, null, false);
			} else {
				expl.value = expl.getAttribute("preValue");
				top.jboxTip("评分不能大于" + num + "且只能是正整数或含有一位小数");
			}
			loadDate();
		}

		function saveScore4Expert(expl, num) {
			var score = expl.value;
			var itemId = expl.getAttribute("itemId");
			var itemName = expl.getAttribute("itemName");
			var reg = /^\d+(\.\d)?$/;
			var reg1 = /^\d+(\.0)$/;
			if (score >= 0 && score <= num && reg.test(score)) {
				if (reg1.test(score)) {
					var score1 = parseInt(score);
					expl.value = score1;
				} else {
					var score1 = score;
				}
				$(expl).attr("preValue", score1);
				var url = "<s:url value='/jsres/supervisio/saveSpeScore'/>";
				var data = {
					"itemId": itemId,
					"itemName": itemName,
					"score": score1,
					"orgFlow": '${orgFlow}',
					"speId": '${speId}',
					"subjectFlow": '${subjectFlow}',
					"userFlow": '${userFlow}'
				};
				top.jboxPost(url, data, function (resp) {
					if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
						top.jboxTip(resp);
					} else {
						top.jboxTip(resp);
					}
				}, null, false);
				loadDate();
			} else {
				expl.value = expl.getAttribute("preValue");
				top.jboxTip("评分不能大于" + num + "且只能是正整数或含有一位小数");
			}
		}

		function saveSpeReason(expl) {
			var reason = expl.value;
			reason = encodeURIComponent(reason);
			var itemId = expl.getAttribute("itemId");
			var itemName = expl.getAttribute("itemName");
			var url = "<s:url value='/jsres/supervisio/saveSpeReason'/>";
			var data = {
				"itemId": itemId, "itemName": itemName, "reason": reason, "orgFlow": '${orgFlow}', "speId": '${speId}',
				"subjectFlow": '${subjectFlow}', "userFlow": '${userFlow}'
			};
			top.jboxPost(url, data, function (resp) {
				if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
					top.jboxTip(resp);
				} else {
					top.jboxTip(resp);
				}
			}, null, false);
			// loadReason();
		}

		function uploadFile(itemId, itemName) {
			var url = "<s:url value='/jsres/supervisio/uploadFile'/>?itemId=" + itemId + "&itemName=" + encodeURIComponent(encodeURIComponent(itemName))+"&subjectFlow=${subjectFlow}"+"&speId=${speId}";
			typeName = "医院评估附件";
			top.jboxOpen(url, "上传" + typeName, 500, 185);
		}

		function delFile(recordFlow, exp) {
			top.jboxConfirm("确认移除该附件吗？", function () {
				var url = "<s:url value='/jsres/supervisio/removeFile?recordFlow='/>" + recordFlow;
				top.jboxGet(url, null, function () {
					$("#" + exp).attr({style: "display:none"});
					$("#" + exp).attr({style: "display:inline"});
					$("#" + exp).next().remove();
					$("#" + exp).remove();
				});
			});
		}

		//窗口加载后
		$(function () {
			//获取所有input标签
			var itemIdList = $("input");
			var itemIdList2 = $("textarea");
			if (${isRead eq GlobalConstant.RECORD_STATUS_Y} || ${GlobalConstant.USER_LIST_GLOBAL eq roleFlag} || ${editFlag eq GlobalConstant.FLAG_N}) {
				for (var i = 0; i < itemIdList.length; i++) {
					itemIdList[i].readOnly = "true";
				}
				for (var i = 0; i < itemIdList2.length; i++) {
					itemIdList2[i].readOnly = "true";
				}
			}
			//为每个input赋值
			<c:forEach items="${ownerScoreList}" var="item" varStatus="status" >
			//获得值
			for(var i=0;i<itemIdList.length;i++){
				if(itemIdList[i].getAttribute("itemId")=="${item.itemId}"&&itemIdList[i].getAttribute("name")=="self"){
					itemIdList[i].value="${item.ownerScore}";
					$(itemIdList[i]).attr("preValue","${item.ownerScore}");
				}
			}
			</c:forEach>
			<c:forEach items="${speScoreList}" var="item" varStatus="status" >
			//获得值
			for(var i=0;i<itemIdList.length;i++){
				if(itemIdList[i].getAttribute("itemId")=="${item.itemId}"&&itemIdList[i].getAttribute("name")=="expert"){
					itemIdList[i].value="${item.speScore}";
					$(itemIdList[i]).attr("preValue","${item.speScore}");
				}
			}
			//获取扣分原因
			for(var i=0;i<itemIdList2.length;i++){
				if(itemIdList2[i].getAttribute("itemId")=="${item.itemId}"&&itemIdList2[i].getAttribute("name")=="reason"){
					itemIdList2[i].value="${item.speReason}";
				}
			}
			</c:forEach>

			loadDate("${jsresBaseEvaluation.speAllScore}");
			//获取所有name为file的td
			var fileList = $("td[name='file']");
			for (var i = 0; i < fileList.length; i++) {
				var itemId = fileList[i].getAttribute("itemId");
				var itemName = fileList[i].getAttribute("itemName");
				var id = itemId;
				id = id.replace(".", "-");
				id = id.replace(".", "-");
				<c:forEach items="${supervisioFileList}" var="file" varStatus="status" >
				if (itemId == "${file.itemId}") {
					var delId = "delFile_${file.recordFlow}";
					fileList[i].innerHTML += "<span style=\"margin: 10px;\" class='titleName' title='${file.fileName}' id='" + delId + "'><a style=\"color: #459ae9;\" href='javascript:void(0);' onclick=\"downloadFile('${file.recordFlow}');\">${pdfn:cutString(file.fileName,4,true,3) }</a><button name='removeFileBtn' title='移除' style='background: white'  onclick=\"delFile('${file.recordFlow}','" + delId + "')\"><img alt= \"上传附件\" src=\"<s:url value='/css/skin/LightBlue/images/del3.png'/>\"></button></span><br>";

				}
				</c:forEach>
				var fileId = "divFile_" + id;
				var html = "<div><a style=\"color: #459ae9;\" id='" + fileId + "' name='upLoadBtn' href=\"javascript:uploadFile('" + itemId + "','" + itemName + "');\"><img style=\"margin: 10px;\" alt= \"上传附件\" src=\"<s:url value='/css/skin/LightBlue/images/add6.png'/>\"></a></div>";
				fileList[i].innerHTML += "<span id='" + fileId + "'></span>"
				fileList[i].innerHTML += html;
			}
			//console.log(fileList);
			for (var i = 0; i < itemIdList.length; i++) {
				//权限判断（各级角色以及是否付费）
				if (${GlobalConstant.USER_LIST_GLOBAL eq roleFlag}) {
					if (itemIdList[i].getAttribute("name") == "self") {
						itemIdList[i].readOnly = "true";
						itemIdList[i].onchange = function () {
						};
					}
					if (itemIdList[i].getAttribute("name") == "expert") {
						itemIdList[i].readOnly = "true";
						itemIdList[i].onchange = function () {
						};
					}
					$("#speContentInput").attr({readOnly: "true"});
					$("button[name='removeFileBtn']").attr({style: "display:none"});
					$("a[name='upLoadBtn']").attr({style: "display:none"});
				} else if (${"expertLeader" eq roleFlag || "expert" eq roleFlag}) {
					if (itemIdList[i].getAttribute("name") == "self") {
						itemIdList[i].readOnly = "true";
						itemIdList[i].onchange = function () {
						};
					}
					if ('${editFlag}' == 'N') {
						if (itemIdList[i].getAttribute("name") == "expert") {
							itemIdList[i].readOnly = "true";
							itemIdList[i].onchange = function () {
							};
						}
						$("#speContentInput").attr({readOnly: "true"});
					}
					$("button[name='removeFileBtn']").attr({style: "display:none"});
					$("a[name='upLoadBtn']").attr({style: "display:none"});
				} else {
					if (itemIdList[i].getAttribute("name") == "expert") {
						itemIdList[i].readOnly = "true";
						itemIdList[i].onchange = function () {
						};
					}
					$("#speContentInput").attr({readOnly: "true"});
				}
			}
			for (var i = 0; i < itemIdList2.length; i++) {
				//权限判断（各级角色以及是否付费）
				if (${GlobalConstant.USER_LIST_GLOBAL eq roleFlag}) {
					if (itemIdList2[i].getAttribute("name") == "reason") {
						itemIdList2[i].readOnly = "true";
						itemIdList2[i].onchange = function () {
						};
					}
				} else if (${"expertLeader" eq roleFlag || "expert" eq roleFlag}) {
					if ('${editFlag}' == 'N') {
						if (itemIdList2[i].getAttribute("name") == "reason") {
							itemIdList2[i].readOnly = "true";
							itemIdList2[i].onchange = function () {
							};
						}
					}
				} else {
					if (itemIdList2[i].getAttribute("name") == "reason") {
						itemIdList2[i].readOnly = "true";
						itemIdList2[i].onchange = function () {
						};
					}
				}
			}
		});

		function downloadFile(recordFlow) {
			top.jboxTip("下载中…………");
			var url = "<s:url value='/jsres/supervisio/downloadFile?recordFlow='/>" + recordFlow;
			window.location.href = url;
		}

		//计算合计
		function loadDate(speAllExpScore) {
			var itemIdList = $("input");
			//自评合计
			var selfToal = 0;
			//专家合计
			var expertToal = 0;
			//自评附加分合计
			var selfAdditionalToal = 0;
			//专家附加分合计
			var expertAdditionalToal = 0;
			//自评总计
			var selfTotalled = 0;
			//专家总计
			var expertTotalled = 0;
			for (var i = 0; i < itemIdList.length; i++) {
				if (itemIdList[i].getAttribute("name") == "self") {
					selfTotalled = Number(selfTotalled) + Number(itemIdList[i].value);
				}
				if (itemIdList[i].getAttribute("name") == "expert") {
					expertTotalled = Number(expertTotalled) + Number(itemIdList[i].value);
				}
				if (itemIdList[i].getAttribute("selfAdd") == "selfAdd") {
					selfAdditionalToal = Number(selfAdditionalToal) + Number(itemIdList[i].value);
				}
				if (itemIdList[i].getAttribute("expertAdd") == "expertAdd") {
					expertAdditionalToal = Number(expertAdditionalToal) + Number(itemIdList[i].value);
				}
			}
			selfToal = Number(selfTotalled) - Number(selfAdditionalToal);
			expertToal = Number(expertTotalled) - Number(expertAdditionalToal);
			if (speAllExpScore != null && speAllExpScore != "") {
				expertTotalled = Number(speAllExpScore);
			}
			var reg1 = /^\d+(\.0)$/;
			$("#selfToal").text(check(selfToal.toFixed(1)));
			$("#selfAdditionalToal").text(check(selfAdditionalToal.toFixed(1)));
			$("#selfTotalled").text(check(selfTotalled.toFixed(1)));
			$("#expertToal").text(check(expertToal.toFixed(1)));
			$("#expertAdditionalToal").text(check(expertAdditionalToal.toFixed(1)));
			$("#expertTotalled").text(check(expertTotalled.toFixed(1)));
		}

		function check(exp) {
			var reg1 = /^\d+(\.0)$/;
			if (reg1.test(exp)) {
				return parseInt(exp);
			} else {
				return exp;
			}
		}

		$(function () {
			if (${GlobalConstant.USER_LIST_GLOBAL eq roleFlag} || ${"expertLeader" eq roleFlag} || ${"expert" eq roleFlag}) {
				$("button[name='removeFileBtn']").attr({style: "display:none"});
				$("a[name='upLoadBtn']").attr({style: "display:none"});
			}
		});

		function saveSpeContent(expe, userFlow) {
			var speContent = expe.value;
			var url = "<s:url value='/jsres/supervisio/saveSpeContent'/>?userFlow=" + userFlow + "&subjectFlow=${subjectFlow}" + "&speContent=" + encodeURIComponent(encodeURIComponent(speContent));
			top.jboxPost(url, null, function (resp) {
				if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
					top.jboxTip(resp);
				} else {
					top.jboxTip(resp);
				}
			}, null, false);
		}

		function returnToBaseList() {
//		$(".tab_select a").click();
			if (${not empty page}) {
				toPage(${page});
			} else {
				toPage(1);
			}
		}
	</script>
</head>
<body>
<div class="div_table" style="overflow: auto;max-height: 570px;">
	<table cellpadding="4" style="width: 1000px">
		<tbody>
		<tr height="34" class="firstRow">
			<th colspan="5">
				<h2 style="font-size:150%">住院医师病历书写评分表</h2>
			</th>
		</tr>
		<tr height="28" >
			<th style="text-align:left;padding-left: 4px;width: 35%" colspan="2">培训对象姓名：${userName}</th>
			<th style="text-align:left;padding-left: 4px;width: 30%" >所在科室：${deptName}</th>
			<th style="text-align:left;padding-left: 4px;width: 35%" colspan="2">培训基地（医院）：${orgName}</th>
		</tr>
		<tr style="height:32px;">
			<th colspan="2">检查项目</th>
			<th>病历内容要求</th>
			<th>标准分</th>
			<th>得分</th>
		</tr>
		<tr style="height:32px">
			<th rowspan="9" style="width: 15%">住院病历</th>
			<td>病历首页</td>
			<td>完整、规范</td>
			<td style="text-align: center">5</td>
			<td><input onchange="saveScore(this,5);" itemId="0300-04-1.1" itemName="病历首页"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  /></td>
		</tr>
		<tr style="height:32px">
			<td>一般项目</td>
			<td>姓名、性别、年龄、职业等</td>
			<td style="text-align: center">1</td>
			<td><input onchange="saveScore(this,1);" itemId="0300-04-1.2" itemName="一般项目"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  /></td>
		</tr>
		<tr style="height:32px">
			<td>主诉</td>
			<td>简明、扼要、完整</td>
			<td style="text-align: center">2</td>
			<td><input onchange="saveScore(this,2);" itemId="0300-04-1.3" itemName="主诉"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  /></td>
		</tr>
		<tr style="height:32px">
			<td>现病史</td>
			<td>起病时间、诱因、症状、缓解因素、治疗经过、具有鉴别诊断意义的阴性病史、发病后一般情况</td>
			<td style="text-align: center">8</td>
			<td><input onchange="saveScore(this,8);" itemId="0300-04-1.4" itemName="现病史"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  /></td>
		</tr>
		<tr style="height:32px">
			<td>既往史等</td>
			<td>既往史、个人史、家族史等（大病历应有系统回顾）</td>
			<td style="text-align: center">2</td>
			<td><input onchange="saveScore(this,2);" itemId="0300-04-1.5" itemName="既往史等"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  /></td>
		</tr>
		<tr style="height:32px">
			<td>体格检查</td>
			<td>各大系统无遗漏、阳性体征准确；有鉴别诊断意义的阴性体征无遗漏；</td>
			<td style="text-align: center">10</td>
			<td><input onchange="saveScore(this,10);" itemId="0300-04-1.6" itemName="体格检查"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  /></td>
		</tr>
		<tr style="height:32px">
			<td>辅助检查</td>
			<td>血尿便常规、重要化验、X射线、心电图、B超等相关检查遗漏或描述不正确</td>
			<td style="text-align: center">2</td>
			<td><input onchange="saveScore(this,2);" itemId="0300-04-1.7" itemName="辅助检查"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  /></td>
		</tr>
		<tr style="height:32px">
			<td>诊断</td>
			<td>急诊诊断及其他疾病诊断规范</td>
			<td style="text-align: center">4</td>
			<td><input onchange="saveScore(this,4);" itemId="0300-04-1.8" itemName="诊断"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  /></td>
		</tr>
		<tr style="height:32px">
			<td>签名</td>
			<td>字迹清楚</td>
			<td style="text-align: center">1</td>
			<td><input onchange="saveScore(this,1);" itemId="0300-04-1.9" itemName="签名"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  /></td>
		</tr>
		<tr style="height:32px">
			<th rowspan="4">首次病程记录</th>
			<td>病历特点</td>
			<td>字迹清楚</td>
			<td style="text-align: center">4</td>
			<td>
				<input onchange="saveScore(this,4);" itemId="0300-04-2.1"
					   itemName="病历特点"
					   name="self"  class="input" type="text"  style="height:20px; text-align: center"  /></td>
		</tr>
		<tr style="height:32px">
			<td>诊断依据</td>
			<td>
				各项诊断均有病史、体检、辅助检查的支持
			</td>
			<td style="text-align: center">10</td>
			<td>
				<input onchange="saveScore(this,10);" itemId="0300-04-2.2"
					   itemName="诊断依据"
					   name="self"  class="input" type="text"  style="height:20px; text-align: center"  /></td>
		</tr>
		<tr style="height:32px">
			<td>鉴别诊断</td>
			<td>
				结合病人、分析有条理，思路清晰
			</td>
			<td style="text-align: center">8</td>
			<td>
				<input onchange="saveScore(this,8);" itemId="0300-04-2.3"
					   itemName="鉴别诊断"
					   name="self"  class="input" type="text"  style="height:20px; text-align: center"  /></td>
		</tr>
		<tr style="height:32px">
			<td>诊疗计划</td>
			<td>
				简明合理，具体
			</td>
			<td style="text-align: center">5</td>
			<td>
				<input onchange="saveScore(this,5);" itemId="0300-04-2.4"
					   itemName="诊疗计划"
					   name="self"  class="input" type="text"  style="height:20px; text-align: center"  /></td>
		</tr>
		<tr style="height:32px">
			<th rowspan="8">病程记录</th>
			<td>时间</td>
			<td>
				病危＞1次/天，病重＞1次/2天，病情稳定1次/3天。
			</td>
			<td style="text-align: center">3</td>
			<td>
				<input onchange="saveScore(this,3);" itemId="0300-04-3.1"
					   itemName="时间"
					   name="self"  class="input" type="text"  style="height:20px; text-align: center"  /></td>
		</tr>
		<tr style="height:32px">
			<td rowspan="6">内容</td>
			<td>
				准确反映病情变化及诊治过程、有病情分析；
			</td>
			<td style="text-align: center" rowspan="6">12</td>
			<td rowspan="6">
				<input onchange="saveScore(this,12);" itemId="0300-04-3.2"
					   itemName="内容"
					   name="self"  class="input" type="text"  style="height:20px; text-align: center"  /></td>
		</tr>
		<tr style="height:32px">
			<td>
				辅助检查结果有记录及分析；
			</td>
		</tr>
		<tr style="height:32px">
			<td>
				重要医嘱更改（抗生素及专科用药）记录及时、理由充分；
			</td>
		</tr>
		<tr style="height:32px">
			<td>
				康复评价记录、交接班记录、转科记录、阶段小结按时完成，格式符合要求；
			</td>
		</tr>
		<tr style="height:32px">
			<td>
				重要操作、抢救记录及时；
			</td>
		</tr>
		<tr style="height:32px">
			<td>
				病历讨论记录详实、层次清楚、重点突出；
			</td>
		</tr>
		<tr style="height:32px">
			<td>上级医师查房记录</td>
			<td>
				在规定时间内完成；记录真实、层次清楚、重点突出；
			</td>
			<td style="text-align: center">5</td>
			<td>
				<input onchange="saveScore(this,5);" itemId="0300-04-3.3"
					   itemName="上级医师查房记录"
					   name="self"  class="input" type="text"  style="height:20px; text-align: center"  /></td>
		</tr>
		<tr style="height:32px">
			<th rowspan="6">出院记录</th>
			<td>一般情况</td>
			<td>
				姓名、性别、年龄、入院日期、出院日期，住院天数
			</td>
			<td style="text-align: center">2</td>
			<td>
				<input onchange="saveScore(this,2);" itemId="0300-04-4.1"
					   itemName="一般情况"
					   name="self"  class="input" type="text"  style="height:20px; text-align: center"  /></td>
		</tr>
		<tr style="height:32px">
			<td>入院情况</td>
			<td>
				简洁明了、重点突出；入院诊断合理
			</td>
			<td style="text-align: center">2</td>
			<td>
				<input onchange="saveScore(this,2);" itemId="0300-04-4.2"
					   itemName="入院情况"
					   name="self"  class="input" type="text"  style="height:20px; text-align: center"  /></td>
		</tr>
		<tr style="height:32px">
			<td>诊疗经过</td>
			<td>
				住院期间的病情变化、检查结果、治疗经过及效果表述清楚
			</td>
			<td style="text-align: center">4</td>
			<td>
				<input onchange="saveScore(this,4);" itemId="0300-04-4.3"
					   itemName="诊疗经过"
					   name="self"  class="input" type="text"  style="height:20px; text-align: center"  /></td>
		</tr>
		<tr style="height:32px">
			<td>出院情况</td>
			<td>
				主要症状、体征、辅助检查结果记录清楚、完整
			</td>
			<td style="text-align: center">2</td>
			<td>
				<input onchange="saveScore(this,2);" itemId="0300-04-4.4"
					   itemName="出院情况"
					   name="self"  class="input" type="text"  style="height:20px; text-align: center"  /></td>
		</tr>
		<tr style="height:32px">
			<td>出院诊断</td>
			<td>
				完整、规范
			</td>
			<td style="text-align: center">2</td>
			<td>
				<input onchange="saveScore(this,2);" itemId="0300-04-4.5"
					   itemName="出院诊断"
					   name="self"  class="input" type="text"  style="height:20px; text-align: center"  /></td>
		</tr>
		<tr style="height:32px">
			<td>出院医嘱</td>
			<td>
				全面、具体（药物及非药物治疗、生活指导、复诊时间）
			</td>
			<td style="text-align: center">2</td>
			<td>
				<input onchange="saveScore(this,2);" itemId="0300-04-4.6"
					   itemName="出院医嘱"
					   name="self"  class="input" type="text"  style="height:20px; text-align: center"  /></td>
		</tr>
		<tr style="height:32px">
			<th colspan="2">病历规格</th>
			<td>
				书写规范、字迹工整、无错别字，无涂改、无摹仿他人签字
			</td>
			<td style="text-align: center">4</td>
			<td>
				<input onchange="saveScore(this,4);" itemId="0300-04-5.1"
					   itemName="病历规格"
					   name="self"  class="input" type="text"  style="height:20px; text-align: center"  /></td>
		</tr>
		<tr style="height:32px;">
			<td colspan="3" style="text-align: right;">总分：</td>
			<td style="text-align: center;">100</td>
			<td></td>
		</tr>
		<tr style="height: 50px">
			<th style="text-align:left">
				&#12288;&#12288;考核专家：
			</th>
			<th colspan="2">
				<c:if test="${not empty speSignUrl}">
					<div>
						<ul>
							<li id="ratateImg${status.index+1}">
								<img src="${sysCfgMap['upload_base_url']}/${speSignUrl}"
									 style="width: 250px;height: 80px;">
							</li>
						</ul>
					</div>
				</c:if>
				<c:if test="${empty speSignUrl}">请上传签名图片</c:if>
			</th>
			<th colspan="2" style="text-align:right">
				<fmt:formatDate value="${evaluationDate}" pattern="yyyy 年 MM 月 dd 日"/>&#12288;&#12288;
				<input id="evaluationDate"
					   value="<fmt:formatDate value="${evaluationDate}" pattern="yyyy-MM-dd" />"
					   hidden>
			</th>
		</tr>
		</tbody>
	</table>
</div>

<c:if test="${(GlobalConstant.USER_LIST_GLOBAL ne roleFlag) && (isRead ne GlobalConstant.RECORD_STATUS_Y) && (editFlag ne 'N')}">
	<div class="button" style="margin-top: 25px">
		<input class="btn_green" type="button" value="提&#12288;交" onclick="subInfo();"/>&#12288;
		<input class="btn_green" type="button" id="zancun" value="暂&#12288存" onclick="zancun();"/>&#12288;
		<input class="btn_green" type="button" value="取&#12288;消" onclick="top.jboxClose();"/>
        <script type="text/javascript">
            function zancun() {
                top.jboxTip("暂存成功！");
                $('#zancun').hide();
            }
        </script>
	</div>
</c:if>
</body>
</html>