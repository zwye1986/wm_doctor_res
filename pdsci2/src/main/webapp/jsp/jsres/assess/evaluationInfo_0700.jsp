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
		#tableContext table {
			border-collapse: collapse;
			border: 1px solid #D3D3D3;
		}

		#tableContext table td {
			border-top: 0;
			border-right: 1px solid #D3D3D3;
			border-bottom: 1px solid #D3D3D3;
			border-left: 0;
			padding-left: 4px;
			padding-right: 2px;
			text-align: left;
		}

		#tableContext table th {
			border-top: 0;
			border-right: 1px solid #D3D3D3;
			border-bottom: 1px solid #D3D3D3;
			border-left: 0;
		}
	</style>
	<script src="<s:url value='/js/dsbridge.js'/>"></script>
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

			if ('${widthSize}'=='Y'){
				$('#tableContext').css("width","1000px");
			}

		});

		//保存总分  日期
		function submitScore() {
			if ('${roleFlag}' == 'local' || '${roleFlag}' == 'baseExpert') {
				var itemIdList = $("input");
				for (var i = 0; i < itemIdList.length; i++) {
					if ((itemIdList[i].getAttribute("name") == "self") && itemIdList[i].value == "") {
						$(itemIdList[i]).focus();
						top.jboxTip("有输入框未输入数据，请输入数据！");
						return;
					}
				}
				var expertTotal = $('#expertTotalled').text();
				var selfTotalled = $('#selfTotalled').text();
				var evaDate = $('#evaluationDate').val();
				var data = {
					"userFlow": '${userFlow}',
					"subjectFlow": "${subjectFlow}",
					"speScoreTotal": expertTotal,
					"evaluationDate": evaDate,
					"roleFlag": '${roleFlag}',
					"selfTotalled":selfTotalled
				};
				var url = "<s:url value='/jsres/supervisio/saveSpeScoreTotal'/>";
				top.jboxPost(url, data, function (resp) {
					if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
						top.jboxTip("提交成功!");
						top.jboxCloseMessager();
					} else {
						top.jboxTip(resp);
					}
				}, null, false);
			}

			if ('${roleFlag}' == 'expertLeader') {
				var itemIdList = $("input");
				for (var i = 0; i < itemIdList.length; i++) {
					if ((itemIdList[i].getAttribute("name") == "expert") && itemIdList[i].value == "") {
						$(itemIdList[i]).focus();
						top.jboxTip("有输入框未输入数据，请输入数据！");
						return;
					}
				}
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
					"subjectFlow": '${subjectFlow}'
				};
				top.jboxPost(url, data, function (resp) {
					if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
						//保存完分数回显按钮
						var itemIdList = $("input");
						for (var i = 0; i < itemIdList.length; i++) {
							if (itemIdList[i].getAttribute("name") == itemId && itemIdList[i].value==score1) {
								$(itemIdList[i]).attr("checked",true);
							}
						}
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

		//保存自评分----整数
		function saveScoreOnlyIntenger(expl, num) {
			var score = expl.value;
			var itemId = expl.getAttribute("itemId");
			var itemName = expl.getAttribute("itemName");
			var selfTotalled = $("#selfTotalled").text();
			var reg = /(^[0-9]\d*$)/;
			if (score >= 0 && score <= num && reg.test(score)) {
				var score1 = parseInt(score);
				$(expl).attr("preValue", score1);
				var url = "<s:url value='/jsres/supervisio/saveOwnerScore'/>";
				var data = {
					"itemId": itemId,
					"itemName": itemName,
					"score": score1,
					"orgFlow": '${orgFlow}',
					"speId": '${speId}',
					"subjectFlow": '${subjectFlow}'
				};
				top.jboxPost(url, data, function (resp) {
					if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
						//保存完分数回显按钮
						var itemIdList = $("input");
						for (var i = 0; i < itemIdList.length; i++) {
							if (itemIdList[i].getAttribute("name") == itemId && itemIdList[i].value==score1) {
								$(itemIdList[i]).attr("checked",true);
							}
						}
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

		function saveScore4ExpertOnlyIntenger(expl, num) {
			var score = expl.value;
			var itemId = expl.getAttribute("itemId");
			var itemName = expl.getAttribute("itemName");
			var reg = /(^[0-9]\d*$)/;
			if (score >= 0 && score <= num && reg.test(score)) {
				var score1 = parseInt(score);
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
						var itemIdList = $("input");
						for (var i = 0; i < itemIdList.length; i++) {
							if (itemIdList[i].getAttribute("name") == itemId && itemIdList[i].value==score1) {
								$(itemIdList[i]).attr("checked",true);
								itemIdList[i].onchange();
							}
						}
						top.jboxTip(resp);
					} else {
						top.jboxTip(resp);
					}
				}, null, false);
				loadDate();
			} else {
				expl.value = expl.getAttribute("preValue");
				top.jboxTip("评分不能大于" + num + "且只能是正整数");
			}
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
						var itemIdList = $("input");
						for (var i = 0; i < itemIdList.length; i++) {
							if (itemIdList[i].getAttribute("name") == itemId && itemIdList[i].value==score1) {
								$(itemIdList[i]).attr("checked",true);
								itemIdList[i].onchange();
							}
						}
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

		function scheduleMany(fileRoute, fileName) {
			var url = "<s:url value ='/jsres/supervisio/scheduleMany'/>?orgName=${orgName}&userFlow=${userFlow}&orgFlow=${orgFlow}&roleFlag=${roleFlag}&isRead=${isRead}&speId=${speId}&editFlag=${editFlag}&subjectFlow=${subjectFlow}&fileRoute=" + fileRoute;
			top.jboxOpen(url, fileName, 1100, 690);
		}

		function Schedule(fileRoute, fileName) {
			var url = "<s:url value ='/jsres/supervisio/Schedule'/>?orgName=${orgName}&userFlow=${userFlow}&orgFlow=${orgFlow}&isRead=${isRead}&speId=${speId}&roleFlag=${roleFlag}&editFlag=${editFlag}&subjectFlow=${subjectFlow}&fileRoute=" + fileRoute;
			top.jboxOpen(url, fileName, 1100, 670);
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
			var url = "<s:url value='/jsres/supervisio/uploadFile'/>?itemId=" + itemId + "&itemName=" + encodeURIComponent(encodeURIComponent(itemName)) + "&subjectFlow=${subjectFlow}" + "&speId=${speId}";
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
			//为了显示存在问题详情
			$("#speContentInput").val("${subjectUser.speContent}");
			//获取所有input标签
			var itemIdList = $("input");
			var itemIdList2 = $("textarea");
			for (var i = 0; i < itemIdList.length; i++) {
				if (itemIdList[i].getAttribute("name") == "expert" || itemIdList[i].getAttribute("name") == "self") {
					$(itemIdList[i]).css("margin-left","22px");
				}
			}
			if (${isRead eq GlobalConstant.RECORD_STATUS_Y}) {
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
			for (var i = 0; i < itemIdList.length; i++) {
				if (itemIdList[i].getAttribute("itemId") == "${item.itemId}" && itemIdList[i].getAttribute("name") == "self") {
					itemIdList[i].value = "${item.ownerScore}";
					$(itemIdList[i]).attr("preValue", "${item.ownerScore}");
				}
				if (${roleFlag=="local" || roleFlag == 'baseExpert'}){
					if (itemIdList[i].getAttribute("name") == "${item.itemId}"&& itemIdList[i].value=="${item.ownerScore}") {
						$(itemIdList[i]).attr("checked",true);
					}
				}
			}
			</c:forEach>
			if (${roleFlag!="local" || roelFlag != 'baseExpert' || suAoth=='Y'}){
				<c:forEach items="${speScoreList}" var="item" varStatus="status" >
				for (var i = 0; i < itemIdList.length; i++) {
					if (itemIdList[i].getAttribute("itemId") == "${item.itemId}" && itemIdList[i].getAttribute("name") == "expert") {
						itemIdList[i].value = "${item.speScore}";
						$(itemIdList[i]).attr("preValue", "${item.speScore}");
					}
					if (itemIdList[i].getAttribute("name") == "${item.itemId}"&& itemIdList[i].value=="${item.speScore}") {
						$(itemIdList[i]).attr("checked",true);
					}
				}
				if(itemIdList2.length > 0){
					//获取扣分原因
					for (var i = 0; i < itemIdList2.length; i++) {
						var aa = itemIdList2[i].getAttribute("itemId");
						if (itemIdList2[i].getAttribute("itemId") == "${item.itemId}" && itemIdList2[i].getAttribute("name") == "reason") {
							itemIdList2[i].value = "${item.speReason}";
						}
					}
				}
				</c:forEach>
			}


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
						itemIdList[i].onchange = function () {};
					}
					if ('${editFlag}' == 'N') {
						if (itemIdList[i].getAttribute("name") == "expert") {
							itemIdList[i].readOnly = "true";
							itemIdList[i].onchange = function () {};
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
				} else if (${"expertLeader" eq roleFlag || "expert" eq roleFlag || 'baseExpert' eq roleFlag}) {
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
		function saveSpeContent(expe, userFlow) {
			var speContent = expe.value;
			var url = "<s:url value='/jsres/supervisio/saveSpeContent'/>?userFlow=" + userFlow + "&subjectFlow=${subjectFlow}" + "&speContent=" + encodeURIComponent(encodeURIComponent(speContent));
			var data = {
				"url": "${ip}/jsres/supervisio/saveSpeContentNew",
				"userFlow": userFlow,
				"subjectFlow": '${subjectFlow}',
				"speContent": encodeURIComponent(encodeURIComponent(speContent))
			}
			top.jboxPost(url, null, function (resp) {
				if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
					top.jboxTip(resp);
				} else {
					top.jboxTip(resp);
				}
			}, null, false);
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
			if(${(roleFlag eq 'local' || roleFlag eq 'baseExpert') and isRead eq 'Y'}){
				$("button[name='removeFileBtn']").attr({style: "display:none"});
				$("a[name='upLoadBtn']").attr({style: "display:none"});
			}
		});



		function returnToBaseList() {
//		$(".tab_select a").click();
			if (${not empty page}) {
				toPage(${page});
			} else {
				toPage(1);
			}
		}
		function tableFixed(div){
			$("#dateFixed").css("top",$(div).scrollTop()+"px");
		}

		function selectRadio(itemId,score,roleFlag,itemScore,reason) {
			if (${editFlag=='N'}){
				return;
			}
			var input = $("input[itemId=\"" + itemId + "\"][name=\"" + roleFlag + "\"]").val(score);
			if(roleFlag == 'self'){
				saveScore(input[0],itemScore);
			}else{
				var score = input[0].value;
				var itemId = input[0].getAttribute("itemId");
				var itemName = input[0].getAttribute("itemName");
				var score1 = parseInt(score);
				$(input[0]).attr("preValue", score1);
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
						reason = encodeURIComponent(reason);
						var itemId = input[0].getAttribute("itemId");
						var itemName = input[0].getAttribute("itemName");
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

					} else {
						top.jboxTip(resp);
					}
				}, null, false);

				loadDate();
			}
		}
	</script>
</head>
<body>
<div id="tableContext" style="overflow-y: auto;margin-left: 10px;margin-right: 10px;height: 750px;" onscroll="tableFixed(this);">
	<div id="dateFixed" style="height: 0px;overflow: visible;position: relative;">
		<table class="grid" style="width: 100%;" >
			<tr height="34" class="firstRow">
				<th colspan="10">
					<h2 style="font-size:150%">2021年住院医师规范化培训评估指标——全科专业基地</h2>
				</th>
			</tr>
			<tr height="28" >
				<th style="text-align:left;padding-left: 4px;"  height="28"  colspan="5">所属省（区、市）：${orgCityName}</th>
				<th style="text-align:left;padding-left: 4px;"  height="28"  colspan="5">培训基地（医院）名称：${orgName}</th>
			</tr>
			<tr height="28" >
				<th style="text-align:left;padding-left: 4px;"  height="28"  colspan="5">临床轮转基地主管科室：</th>
				<th style="text-align:left;padding-left: 4px;"  height="28"  colspan="5">基层实践基地名称：</th>
			</tr>
			<tr style="height:16px;">
				<th style="min-width: 320px;max-width: 320px;height: 16px;" colspan="3">评估项目</th>
				<th style="min-width: 270px;max-width: 270px;" rowspan="2">评估内容</th>
				<th style="min-width: 270px;max-width: 270px;" rowspan="2">评分标准</th>
				<th style="min-width: 50px;max-width: 50px" rowspan="2">分值</th>
				<th style="min-width: 150px;max-width: 150px" rowspan="2">附件信息</th>
				<th style="min-width: 54px;max-width: 50px" rowspan="2">自评得分</th>
				<th style="min-width: 54px;max-width: 50px" rowspan="2">专家评分</th>
				<th style="min-width: 200px;max-width: 200px" rowspan="2">扣分原因</th>
			</tr>
			<tr style="height:32px">
				<th style="width: 100px;height: 32px;">一级指标</th>
				<th style="width: 100px;">二级指标</th>
				<th style="width: 120px;">三级指标<br> ★为核心指标</th>
			</tr>
		</table>
	</div>
	<table border="0" cellpadding="0" cellspacing="0" class="grid" style="width: 100%" >
		<tr height="34" >
			<th colspan="10">
				<h2 style="font-size:150%">2021年住院医师规范化培训评估指标——全科专业基地</h2>
			</th>
		</tr>
		<tr height="28" >
			<th style="text-align:left;padding-left: 4px;"  height="28"  colspan="5">所属省（区、市）：${orgCityName}</th>
			<th style="text-align:left;padding-left: 4px;"  height="28"  colspan="5">培训基地（医院）名称：${orgName}</th>
		</tr>
		<tr height="28" >
			<th style="text-align:left;padding-left: 4px;"  height="28"  colspan="5">临床轮转基地主管科室：</th>
			<th style="text-align:left;padding-left: 4px;"  height="28"  colspan="5">基层实践基地名称：</th>
		</tr>
		<tr style="height:16px;">
			<th style="min-width: 320px;max-width: 320px;height: 16px;" colspan="3">评估项目</th>
			<th style="min-width: 270px;max-width: 270px;" rowspan="2">评估内容</th>
			<th style="min-width: 270px;max-width: 270px;" rowspan="2">评分标准</th>
			<th style="min-width: 50px;max-width: 50px" rowspan="2">分值</th>
			<th style="min-width: 150px;max-width: 150px" rowspan="2">附件信息</th>
			<th style="min-width: 54px;max-width: 50px" rowspan="2">自评得分</th>
			<th style="min-width: 54px;max-width: 50px" rowspan="2">专家评分</th>
			<th style="min-width: 200px;max-width: 200px" rowspan="2">扣分原因</th>
		</tr>
		<tr style="height:32px">
			<th style="width: 100px;height: 32px;">一级指标</th>
			<th style="width: 100px;">二级指标</th>
			<th style="width: 120px;">三级指标<br> ★为核心指标</th>
		</tr>
		<tr style="height:50px">
			<td style="height: 438px;" rowspan="7">1.基本条件<br>（10分）</td>
			<td rowspan="5">1.1基地条件</td>
			<td>1.1.1临床培训基地条件</td>
			<td width="366px">
				1.年出院病人数≥1万人次，年门诊量≥40万人次，年急诊量≥2万人次<br>
				2.必备科室:全科医学科、内科、神经内科、儿科、外科（普外科、骨科、泌尿外科）、妇产科、急诊科、
				皮肤科、五官科（眼科、耳鼻喉科）、传染科（感染疾病科）、 精神科、中医科、康复医学科、医学影像科、检验医学科
			</td>
			<td width="367px">
				每项0.5分（项目内容均符合得0.5分）。<br>
				（第2项说明1.应有包括全科医学科在内不少于10个轮转科室在本法人机构内，其他轮转科室不全的，可与协同
				单位联合培训,协同培训的科室≤3个。2.感染疾病科接诊范围应包含感染性腹泻、病毒性肝炎、结核等《标准》
				要求的传染病， 否则需与其他机构联合培训。）
			</td>
			<td style="text-align: center;">1</td>
			<td name="file" itemId="2022-1.1.1" itemName="临床培训基地条件"></td>
			<td><input onchange="saveScore(this,1);" itemId="2022-1.1.1" value="${ownerScoreMap['2022-1.1.1']}" itemName="临床培训基地条件"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td width="74px"><input onchange="saveScore4Expert(this,1);" itemId="2022-1.1.1" itemName="临床培训基地条件"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<textarea onchange="saveSpeReason(this);" itemId="2022-1.1.1" itemName="临床培训基地条件" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-1.1.1']}</textarea>
			</td>
		</tr>
		<tr style="height:53px;">
			<td>1.1.2基层实践基地条件</td>
			<td>
				1.辖区卫生行政部门设置、具有示范作用的基层实践基地（社区卫生服务中心或乡镇卫生院，下同）
				辖区人口数≥5万人（中西部地区可适当放宽）<br>
				2.必备科室:全科、预防保健科、中医科、康复科、精神疾病管理科（或精防科）、检验科、医学影像科
			</td>
			<td>
				每项0.5分（项目内容均符合得0.5分）。<br>
				（第2项说明：前2个轮转科室应在本法人机构内，其他轮转科室不全的，可与临床培训基地联合培训）
			</td>
			<td style="text-align: center;">1</td>
			<td name="file" itemId="2022-1.1.2" itemName="基层实践基地条件" ></td>
			<td><input onchange="saveScore(this,1);" itemId="2022-1.1.2" itemName="基层实践基地条件"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,1);" itemId="2022-1.1.2" itemName="基层实践基地条件"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<textarea onchange="saveSpeReason(this);"  itemId="2022-1.1.2" itemName="基层实践基地条件" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-1.1.2']}</textarea>
			</td>
		</tr>
		<tr style="height:53px;">
			<td>1.1.3临床培训基地全科医学科设置要求★</td>
			<td>
				1.独立设置全科医学科，有符合全科教学要求的全科门诊、全科病房、有独立的示教室，全科医学科年门
				诊量≥ 2 万人次；年收治病人数≥ 500 人次<br>
				2.全科医学科在基地职能部门的帮助下，牵头承担培训任务，包括住院医师管理、轮转计划、考勤考核和教学质控
			</td>
			<td>
				第1项2分，第2项1分。<br>
				（第1项说明：1.独立设置全科，总门诊诊间≥ 2 间，其中一间为全科教学门诊； 病床数20-40张为宜，得2分。<br>
				2.独立设置全科，只有全科病房或符合教学要求的全科门诊，得1分。<br>
				3.全科医学科未独立设置，取消其培训基地资格。）
			</td>
			<td style="text-align: center;">3</td>
			<td name="file" itemId="2022-1.1.3" itemName="临床培训基地全科医学科设置要求" ></td>
			<td><input onchange="saveScore(this,3);" itemId="2022-1.1.3" itemName="临床培训基地全科医学科设置要求"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,3);" itemId="2022-1.1.3" itemName="临床培训基地全科医学科设置要求"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<textarea onchange="saveSpeReason(this);"  itemId="2022-1.1.3" itemName="临床培训基地全科医学科设置要求" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-1.1.3']}</textarea>
			</td>
		</tr>
		<tr style="height:39px;">
			<td>1.1.4疾病种类及数量</td>
			<td rowspan="2">
				符合《住院医师规范化培训基地标准（2019 年修订版）》及《住院医师规范化培训内容与标准（2019 年修订版）》全科专业相关要求
			</td>
			<td>
				抽查1个临床科室及基层实践基地，各0.5分。<br>
				查看上年度医院统计室提供的病种病例数（基层提供慢性病管理数），符合要求，得0.5分<br>
				未达标，不得分
			</td>
			<td style="text-align: center;">1</td>
			<td name="file" itemId="2022-1.1.4" itemName="疾病种类及数量" ></td>
			<td><input onchange="saveScore(this,1);" itemId="2022-1.1.4" itemName="疾病种类及数量"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,1);" itemId="2022-1.1.4" itemName="疾病种类及数量"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<textarea onchange="saveSpeReason(this);"  itemId="2022-1.1.4" itemName="疾病种类及数量" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-1.1.4']}</textarea>
			</td>
		</tr>
		<tr style="height:32px;">
			<td style="height: 32px;">1.1.5临床技能操作种类及数量</td>
			<td>
				抽查1个临床科室及基层实践基地，各0.5分。<br>
				查看上年度医院统计室提供的技能操作种类和数量（基层提供相关服务数），符合要求，得0.5分<br>
				未达标，不得分
			</td>
			<td style="text-align: center;">1</td>
			<td name="file" itemId="2022-1.1.5" itemName="临床技能操作种类及数量"></td>
			<td><input onchange="saveScore(this,1);" itemId="2022-1.1.5" itemName="临床技能操作种类及数量"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,1);" itemId="2022-1.1.5" itemName="临床技能操作种类及数量"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<textarea onchange="saveSpeReason(this);"  itemId="2022-1.1.5" itemName="临床技能操作种类及数量" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-1.1.5']}</textarea>
			</td>
		</tr>
		<tr style="height:50px;">
			<td rowspan="2">1.2协同培训</td>
			<td>1.2.1临床培训基地与基层实践基地联系紧密★</td>
			<td>
				1.与基层实践基地正式签订规范的联合培训协议<br>
				2.临床基地对基层实践基地指导医师开展培训<br>
				3.临床基地每年到基层实践基地指导、督查教学工作，开展联合教学活动和召开教学相关会议
			</td>
			<td>
				满足所有要求，且有良好效果和可靠证据，得2分<br>
				每缺1项扣1分，扣完为止
			</td>
			<td style="text-align: center;">2</td>
			<td name="file" itemId="2022-1.2.1" itemName="临床培训基地与基层实践基地联系紧密"></td>
			<td><input onchange="saveScore(this,2);" itemId="2022-1.2.1" itemName="临床培训基地与基层实践基地联系紧密"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,2);" itemId="2022-1.2.1" itemName="临床培训基地与基层实践基地联系紧密"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<textarea onchange="saveSpeReason(this);"  itemId="2022-1.2.1" itemName="临床培训基地与基层实践基地联系紧密" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-1.2.1']}</textarea>
			</td>
		</tr>
		<tr style="height:50px;">
			<td>1.2.2协同单位</td>
			<td>
				轮转科室不全的，须有协同的其他医疗机构共同完成培训内容，轮转培训时间和质量符合大纲要求
			</td>
			<td>
				满足所有要求，得1分。（设置非必要协同单位的不合格限期整改；协同科室齐备，无需协同单位的，此处不扣分）
			</td>
			<td style="text-align: center;">1</td>
			<td name="file" itemId="2022-1.2.2" itemName="协同单位"></td>
			<td><input onchange="saveScore(this,1);" itemId="2022-1.2.2" itemName="协同单位"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,1);" itemId="2022-1.2.2" itemName="协同单位"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<textarea onchange="saveSpeReason(this);"  itemId="2022-1.2.2" itemName="协同单位" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-1.2.2']}</textarea>
			</td>
		</tr>
		<tr style="height:60px;">
			<td style="height: 438px;" rowspan="9">2.师资建设<br>（15分）</td>
			<td rowspan="6">2.1师资要求</td>
			<td>2.1.1师资与培训对象比例</td>
			<td>
				每名指导医师同时带教全科学员不超过2名
			</td>
			<td>
				临床基地及基层实践基地，各0.5分<br>
				符合要求，得0.5分<br>
				未达标，不得分
			</td>
			<td style="text-align: center;">1</td>
			<td name="file" itemId="2022-2.1.1" itemName="师资与培训对象比例"></td>
			<td><input onchange="saveScore(this,1);" itemId="2022-2.1.1" itemName="师资与培训对象比例"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,1);" itemId="2022-2.1.1" itemName="师资与培训对象比例"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<textarea onchange="saveSpeReason(this);"  itemId="2022-2.1.1" itemName="师资与培训对象比例" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-2.1.1']}</textarea>
			</td>
		</tr>
		<tr style="height:38px;">
			<td style="height: 38px;">2.1.2临床培训基地师资条件</td>
			<td>
				1.指导医师具有医学本科及以上学历、主治医师及以上专业技术职务<br>
				2.全科医学科指导医师熟悉基层全科医生工作情况，在基层实践基地承担以教学为主的专家门诊、
				会诊及示范教学等工作，全科医学科指导医师至少每月1次，其他科室指导医师至少每年1次<br>
				3.全科医学科指导医师全部执业注册范围含“全科医学专业”
			</td>
			<td>
				检查基地提供的师资名单均达到标准，得1分<br>
				两项达到标准，得0.5分<br>
				其他，不得分
			</td>
			<td style="text-align: center;">1</td>
			<td name="file" itemId="2022-2.1.2" itemName="临床培训基地师资条件"></td>
			<td><input onchange="saveScore(this,1);" itemId="2022-2.1.2" itemName="临床培训基地师资条件"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,1);" itemId="2022-2.1.2" itemName="临床培训基地师资条件"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<textarea onchange="saveSpeReason(this);"  itemId="2022-2.1.2" itemName="临床培训基地师资条件" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-2.1.2']}</textarea>
			</td>
		</tr>
		<tr style="height:38px;">
			<td style="height: 38px;">2.1.3基层实践基地师资条件</td>
			<td>
				1.医学专科及以上学历<br>
				2.主治医师及以上专业技术职称<br>
				3.有３年及以上基层卫生工作经历<br>
				4.全科专业指导医师的执业注册范围含“全科医学专业”
			</td>
			<td>
				均达到标准，得1分<br>
				两项达到标准，得0.5分<br>
				其他，不得分
			</td>
			<td style="text-align: center;">1</td>
			<td name="file" itemId="2022-2.1.3" itemName="基层实践基地师资条件"></td>
			<td><input onchange="saveScore(this,1);" itemId="2022-2.1.3" itemName="基层实践基地师资条件"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,1);" itemId="2022-2.1.3" itemName="基层实践基地师资条件"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<textarea  onchange="saveSpeReason(this);" itemId="2022-2.1.3" itemName="基层实践基地师资条件" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-2.1.3']}</textarea>
			</td>
		</tr>
		<tr style="height:38px;">
			<td style="height: 38px;">2.1.4师资队伍组成</td>
			<td>
				1.临床培训基地全科专业指导医师总人数至少 15 人，其中内科、全科医学科至少各 3 人，
				神经内科、外科、儿科、急诊科至少各 1 人；基层实践基地全科医学指导医师总人数至少 5 人，
				其中全科医学科至少 3 人，预防保健科至少 1 人<br>
				2.副主任医师及以上专业技术职务比例，临床培训基地不少于１/３，基层实践基地不少于1人
			</td>
			<td>
				均达到标准，得1分<br>
				不达标准，不得分
			</td>
			<td style="text-align: center;">1</td>
			<td name="file" itemId="2022-2.1.4" itemName="师资队伍组成"></td>
			<td><input onchange="saveScore(this,1);" itemId="2022-2.1.4" itemName="师资队伍组成"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,1);" itemId="2022-2.1.4" itemName="师资队伍组成"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<textarea onchange="saveSpeReason(this);"  itemId="2022-2.1.4" itemName="师资队伍组成" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-2.1.4']}</textarea>
			</td>
		</tr>
		<tr style="height:38px;">
			<td style="height: 38px;">2.1.5专业基地负责人条件</td>
			<td>
				1.临床培训基地：医学本科及以上学历、高级专业技术职务任职资格，含全科专业执业范围，从事全科医疗、
				科研和教学工作至少 5 年； 参加过省级及以上全科医学师资培训或全科基地管理人员培训，并获得培训证书<br>
				2.基层实践基地：医学专科及以上学历、中级及以上专业技术职务、至少5年基层工作经历，参加过省级及以上
				全科医学师资培训或全科基地管理人员培训，并获得培训证书
			</td>
			<td>
				每项0.5分<br>
				均达到标准，该项得0.5分<br>
				不达标准，不得分
			</td>
			<td style="text-align: center;">1</td>
			<td name="file" itemId="2022-2.1.5" itemName="专业基地负责人条件"></td>
			<td><input onchange="saveScore(this,1);" itemId="2022-2.1.5" itemName="专业基地负责人条件"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,1);" itemId="2022-2.1.5" itemName="专业基地负责人条件"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<textarea  onchange="saveSpeReason(this);" itemId="2022-2.1.5" itemName="专业基地负责人条件" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-2.1.5']}</textarea>
			</td>
		</tr>
		<tr style="height:38px;">
			<td style="height: 38px;">2.1.6设立全科教研室与教学小组</td>
			<td>
				1.培训基地有全科教研室（含基层实践基地成员），并有效开展相关教学活动<br>
				2.临床培训基地主要科室（全科医学科、内科、神经内科、外科、急诊科、儿科、基层实践基地等）分别设立
				全科教学小组，明确相应成员的职责，定期组织研究全科教学工作
			</td>
			<td>
				每项1分<br>
				均达到标准，该项得1分<br>
				不达标准，不得分
			</td>
			<td style="text-align: center;">2</td>
			<td name="file" itemId="2022-2.1.6" itemName="设立全科教研室与教学小组"></td>
			<td><input onchange="saveScore(this,2);" itemId="2022-2.1.6" itemName="设立全科教研室与教学小组"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,2);" itemId="2022-2.1.6" itemName="设立全科教研室与教学小组"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<textarea onchange="saveSpeReason(this);"  itemId="2022-2.1.6" itemName="设立全科教研室与教学小组" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-2.1.6']}</textarea>
			</td>
		</tr>

		<tr style="height:35px;">
			<td rowspan="3">2.2师资管理</td>
			<td>2.2.1师资培训★</td>
			<td>
				1.主管教学院领导参加省级及以上全科医学师资培训或基地管理人员培训班，并取得培训证书<br>
				2.临床培训基地全科医学科和内科从事全科带教的指导医师均应参加过省级及以上全科医学师资培训，
				其他轮转科室至少各 1 人参加过省级及以上全科医学师资培训，并获得全科师资培训证书；其中经过骨干
				师资培训或国家级师资培训的人数不低于1/5；基层实践基地至少有 5 人参加过省级及以上全科医学师资培训，
				并获得师资培训证书<br>
				3.所有指导医师每三年均参加过院级（基层参加过临床轮转基地院级及以上）全科师资类别培训
			</td>
			<td>
				每项1分（证书5年内有效）<br>
				【说明：国家级和省级师资培训时间需执行原卫生部《全科医学师资培训实施意见（试行）》
				（卫办科教发〔2012〕151号）要求，集中培训时间不少于56学时或4整天，内容符合全科医学师资培训要求】
			</td>
			<td style="text-align: center;">3</td>
			<td name="file" itemId="2022-2.2.1" itemName="师资培训"></td>
			<td><input onchange="saveScore(this,3);" itemId="2022-2.2.1" itemName="师资培训"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,3);" itemId="2022-2.2.1" itemName="师资培训"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<textarea onchange="saveSpeReason(this);"  itemId="2022-2.2.1" itemName="师资培训" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-2.2.1']}</textarea>
			</td>
		</tr>
		<tr style="height:35px;">
			<td>2.2.2师资评价</td>
			<td>
				每年度至少组织1次对指导医师的教学工作评价
			</td>
			<td>
				临床培训基地及基层实践基地，各0.5分<br>
				有方案，具体实施并有反馈和运用，得0.5分<br>
				未达标，不得分
			</td>
			<td style="text-align: center;">1</td>
			<td name="file" itemId="2022-2.2.2" itemName="师资评价"></td>
			<td><input onchange="saveScore(this,1);" itemId="2022-2.2.2" itemName="师资评价"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,1);" itemId="2022-2.2.2" itemName="师资评价"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<textarea  onchange="saveSpeReason(this);" itemId="2022-2.2.2" itemName="师资评价" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-2.2.2']}</textarea>
			</td>
		</tr>
		<tr style="height:35px;">
			<td>2.2.3激励制度★</td>
			<td>
				建立指导医师激励机制，将教学工作与绩效考评、奖金、评优等挂钩并切实执行，并对指导医师实行动态管理
			</td>
			<td>
				临床培训基地及基层实践基地，各2分<br>
				有激励机制或方案，且已与绩效考评、教学补助、评优及职称晋升等挂钩，得2分<br>
				有激励制度但不落实或无激励制度，不得分
			</td>
			<td style="text-align: center;">4</td>
			<td name="file" itemId="2022-2.2.3" itemName="激励制度"></td>
			<td><input onchange="saveScore(this,4);" itemId="2022-2.2.3" itemName="激励制度"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,4);" itemId="2022-2.2.3" itemName="激励制度"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<textarea onchange="saveSpeReason(this);"  itemId="2022-2.2.3" itemName="激励制度" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-2.2.3']}</textarea>
			</td>
		</tr>
		<tr style="height:32px;">
			<td style="height: 438px;" rowspan="15">3.过程管理<br>（30分）</td>
			<td style="height:136px;" rowspan="7">3.1培训制度与落实</td>
			<td>3.1.1“主要领导”责任制★</td>
			<td>
				院领导重视全科培训工作，并切实落实。 医院主管教学的院级领导对全科医学有较清晰认识、
				熟知全科医学人才培养的基本规律；基层实践基地有相应“主要领导”负责
			</td>
			<td>
				临床基地及基层实践基地，各1.5分<br>
				1.院（中心）领导班子每年至少组织1次研究并解决全科住培工作相关问题，全科住培工作纳入每年年度工作计划
				及总结。均达到标准，得1.5分<br>
				2.不达标准，不得分
			</td>
			<td style="text-align: center;">3</td>
			<td name="file" itemId="2022-3.1.1" itemName="主要领导责任制"></td>
			<td><input onchange="saveScore(this,3);" itemId="2022-3.1.1" itemName="主要领导责任制"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,3);" itemId="2022-3.1.1" itemName="主要领导责任制"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<textarea  onchange="saveSpeReason(this);" itemId="2022-3.1.1" itemName="主要领导责任制" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.1.1']}</textarea>
			</td>
		</tr>
		<tr style="height:32px;">
			<td>3.1.2基地负责人</td>
			<td>
				实行专业基地主任负责制，并切实落实；基层实践基地有相应基地负责人
			</td>
			<td>
				有，且职责明确、履职认真，临床和基层实践基地各得1分<br>
				无或岗责不清或履职不认真，不得分
			</td>
			<td style="text-align: center;">2</td>
			<td name="file" itemId="2022-3.1.2" itemName="基地负责人"></td>
			<td><input onchange="saveScore(this,2);" itemId="2022-3.1.2" itemName="基地负责人"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,2);" itemId="2022-3.1.2" itemName="基地负责人"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<textarea onchange="saveSpeReason(this);"  itemId="2022-3.1.2" itemName="基地负责人" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.1.2']}</textarea>
			</td>
		</tr>
		<tr style="height:32px;">
			<td>3.1.3教学主任</td>
			<td>
				设专职教学主任岗位，负责全科专业基地教学工作的组织实施；基层实践基地设置专职或兼职教学主任岗位
			</td>
			<td>
				有，且职责明确、履职认真，临床和基层实践基地各得1分<br>
				无或岗责不清或履职不认真，不得分
			</td>
			<td style="text-align: center;">2</td>
			<td name="file" itemId="2022-3.1.3" itemName="教学主任"></td>
			<td><input onchange="saveScore(this,2);" itemId="2022-3.1.3" itemName="教学主任"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,2);" itemId="2022-3.1.3" itemName="教学主任"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<textarea onchange="saveSpeReason(this);"  itemId="2022-3.1.3" itemName="教学主任" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.1.3']}</textarea>
			</td>
		</tr>
		<tr style="height:32px;">
			<td>3.1.4教学秘书</td>
			<td>
				设置专职教学秘书岗位，落实全科专业基地教学工作；基层实践基地设置专职或兼职教学秘书岗位
			</td>
			<td>
				有，且职责明确、履职认真，临床和基层实践基地各得0.5分<br>
				无或岗责不清或履职不认真，不得分
			</td>
			<td style="text-align: center;">1</td>
			<td name="file" itemId="2022-3.1.4" itemName="教学秘书"></td>
			<td><input onchange="saveScore(this,1);" itemId="2022-3.1.4" itemName="教学秘书"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,1);" itemId="2022-3.1.4" itemName="教学秘书"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<textarea  onchange="saveSpeReason(this);" itemId="2022-3.1.4" itemName="教学秘书" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.1.4']}</textarea>
			</td>
		</tr>
		<tr style="height:32px;">
			<td>3.1.5招收计划完成情况</td>
			<td>
				连续两年完成本省分配的招生任务情况
			</td>
			<td>
				1.按完成全科专业招收任务比例折算（保留小数点后1位），完成率≥90%，1年得1分<br>
				2.90%＞完成率≥85%，1年得0.5分<br>
				3.超过容量，不得分
			</td>
			<td style="text-align: center;">2</td>
			<td name="file" itemId="2022-3.1.5" itemName="招收计划完成情况"></td>
			<td><input onchange="saveScore(this,2);" itemId="2022-3.1.5" itemName="招收计划完成情况"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,2);" itemId="2022-3.1.5" itemName="招收计划完成情况"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<textarea onchange="saveSpeReason(this);"  itemId="2022-3.1.5" itemName="招收计划完成情况" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.1.5']}</textarea>
			</td>
		</tr>
		<tr style="height:32px;">
			<td>3.1.6轮转计划★</td>
			<td>
				按规定落实轮转计划和要求。全科医学科和基层实践基地轮转计划合理
			</td>
			<td>
				临床基地及基层实践基地，各1分<br>
				符合要求且严格落实，得1分<br>
				其他，不得分
			</td>
			<td style="text-align: center;">2</td>
			<td name="file" itemId="2022-3.1.6" itemName="轮转计划"></td>
			<td><input onchange="saveScore(this,2);" itemId="2022-3.1.6" itemName="轮转计划"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,2);" itemId="2022-3.1.6" itemName="轮转计划"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<textarea onchange="saveSpeReason(this);"  itemId="2022-3.1.6" itemName="轮转计划" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.1.6']}</textarea>
			</td>
		</tr>
		<tr style="height:32px;">
			<td>3.1.7考勤制度</td>
			<td>
				有考勤规章制度，有专人负责，并严格执行
			</td>
			<td>
				临床基地及基层实践基地，各0.5分<br>
				有，且严格落实，得0.5分<br>
				未严格落实，不得分
			</td>
			<td style="text-align: center;">1</td>
			<td name="file" itemId="2022-3.1.7" itemName="轮转计划"></td>
			<td><input onchange="saveScore(this,1);" itemId="2022-3.1.7" itemName="轮转计划"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,1);" itemId="2022-3.1.7" itemName="轮转计划"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<textarea onchange="saveSpeReason(this);"  itemId="2022-3.1.7" itemName="轮转计划" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.1.7']}</textarea>
			</td>
		</tr>
		<tr style="height:40px;">
			<td style="height:136px;" rowspan="5">3.2培训活动</td>
			<td style="height: 40px;">3.2.1入院及入科教育</td>
			<td>
				有入院教育，各科室均有入科教育，内容包括医院（科室）情况、医院（科室）培养计划与要求、规章纪律、医德医风、
				医患沟通以及临床基本知识和基本技能模拟训练等内容，并有专人组织实施
			</td>
			<td>
				核查入院教育、3个临床科室及基层实践基地的均有入科教育，且严格落实，得2分<br>
				其中1个科室或基层实践基地无或不规范，得1分<br>
				其他，不得分
			</td>
			<td style="text-align: center;">2</td>
			<td name="file" itemId="2022-3.2.1" itemName="入院及入科教育"></td>
			<td><input onchange="saveScore(this,2);" itemId="2022-3.2.1" itemName="入院及入科教育"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,2);" itemId="2022-3.2.1" itemName="入院及入科教育"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<textarea  onchange="saveSpeReason(this);" itemId="2022-3.2.1" itemName="入院及入科教育" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.2.1']}</textarea>
			</td>
		</tr>
		<tr style="height:40px;">
			<td style="height: 40px;">3.2.2教学查房</td>
			<td>
				临床培训基地开展规范的教学查房，至少2周1次
			</td>
			<td>
				核查2个临床科室，次数达标且内容形式规范，各得1分
			</td>
			<td style="text-align: center;">2</td>
			<td name="file" itemId="2022-3.2.2" itemName="教学查房"></td>
			<td><input onchange="saveScore(this,2);" itemId="2022-3.2.2" itemName="教学查房"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,2);" itemId="2022-3.2.2" itemName="教学查房"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<textarea  onchange="saveSpeReason(this);" itemId="2022-3.2.2" itemName="教学查房" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.2.2']}</textarea>
			</td>
		</tr>
		<tr style="height:40px;">
			<td style="height: 40px;">3.2.3小讲课</td>
			<td>
				开展针对全科住院医师的小讲课活动，至少2周1次
			</td>
			<td>
				临床基地（涉及所有科室）及基层实践基地，次数达标且内容形式规范，各得1分
			</td>
			<td style="text-align: center;">2</td>
			<td name="file" itemId="2022-3.2.3" itemName="小讲课"></td>
			<td><input onchange="saveScore(this,2);" itemId="2022-3.2.3" itemName="小讲课"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,2);" itemId="2022-3.2.3" itemName="小讲课"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<textarea  onchange="saveSpeReason(this);" itemId="2022-3.2.3" itemName="小讲课" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.2.3']}</textarea>
			</td>
		</tr>
		<tr style="height:40px;">
			<td style="height: 40px;">3.2.4病例讨论</td>
			<td>
				开展规范的病例讨论，至少2周1次
			</td>
			<td>
				临床基地（涉及所有科室）及基层实践基地，次数达标且内容形式规范，各得1分
			</td>
			<td style="text-align: center;">2</td>
			<td name="file" itemId="2022-3.2.4" itemName="病例讨论"></td>
			<td><input onchange="saveScore(this,2);" itemId="2022-3.2.4" itemName="病例讨论"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,2);" itemId="2022-3.2.4" itemName="病例讨论"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<textarea  onchange="saveSpeReason(this);" itemId="2022-3.2.4" itemName="病例讨论" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.2.4']}</textarea>
			</td>
		</tr>
		<tr style="height:40px;">
			<td style="height: 40px;">3.2.5其他培训活动</td>
			<td>
				组织或学员参与全科相关教学(日常教学以外）和学术活动情况
			</td>
			<td>
				有且与全科教学相关，得1分
			</td>
			<td style="text-align: center;">1</td>
			<td name="file" itemId="2022-3.2.5" itemName="病例讨论"></td>
			<td><input onchange="saveScore(this,1);" itemId="2022-3.2.5" itemName="病例讨论"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,1);" itemId="2022-3.2.5" itemName="病例讨论"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<textarea  onchange="saveSpeReason(this);" itemId="2022-3.2.5" itemName="病例讨论" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.2.5']}</textarea>
			</td>
		</tr>
		<tr style="height:48px;">
			<td style="height:136px;">3.3过程考核</td>
			<td>3.3.1出科考核★</td>
			<td>
				有出科考核方案，有理论考核题库、试题充足、每年重复率不超过30%，有技能操作考核评分表，有考核评分结果反馈及运用
			</td>
			<td>
				核查2个临床科室及基层实践基地，各1分<br>
				考核全面、原始资料齐全，真实规范，有考核评分结果反馈及运用,得1分<br>
				无考核评分结果反馈及运用，不得分
			</td>
			<td style="text-align: center;">3</td>
			<td name="file" itemId="2022-3.3.1" itemName="出科考核"></td>
			<td><input onchange="saveScore(this,3);" itemId="2022-3.3.1" itemName="出科考核"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,3);" itemId="2022-3.3.1" itemName="出科考核"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<textarea  onchange="saveSpeReason(this);" itemId="2022-3.3.1" itemName="出科考核" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.3.1']}</textarea>
			</td>
		</tr>
		<tr style="height:48px;">
			<td style="height:136px;" rowspan="2">3.4培训强度</td>
			<td>3.4.1管理床位数★</td>
			<td>
				独立管床2～5张，其中全科医疗科管床数3～5张
			</td>
			<td>
				核查2个临床科室，各1分<br>
				书写入院记录、首次病程、病程记录、出院记录等管床数符合要求，得1分<br>
				不独立管床或管床过多（＞8张）、过少（＜2张），不得分
			</td>
			<td style="text-align: center;">2</td>
			<td name="file" itemId="2022-3.4.1" itemName="管理床位数"></td>
			<td><input onchange="saveScore(this,2);" itemId="2022-3.4.1" itemName="管理床位数"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,2);" itemId="2022-3.4.1" itemName="管理床位数"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<textarea  onchange="saveSpeReason(this);" itemId="2022-3.4.1" itemName="管理床位数" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.4.1']}</textarea>
			</td>
		</tr>
		<tr style="height:48px;">
			<td>3.4.2门急诊工作量★</td>
			<td>
				1.全科医学科、内科、神经内科、儿科和外科、妇科、急诊科等主要培训科室的指导医师应保证一定的工作量，
				门诊或病房安排合理，且在门诊工作的平均每日接诊 20 名以上患者<br>
				2.急诊科有上级医师指导的门诊实践与教学，平均日接诊量≥15人次<br>
				3.符合条件的培训对象，有上级医师指导，在基层实践基地独立接诊，平均每日≥5人次
			</td>
			<td>
				每项1分<br>
				不达标，不得分
			</td>
			<td style="text-align: center;">3</td>
			<td name="file" itemId="2022-3.4.2" itemName="门急诊工作量"></td>
			<td><input onchange="saveScore(this,3);" itemId="2022-3.4.2" itemName="门急诊工作量"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,3);" itemId="2022-3.4.2" itemName="门急诊工作量"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<textarea  onchange="saveSpeReason(this);" itemId="2022-3.4.2" itemName="门急诊工作量" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.4.2']}</textarea>
			</td>
		</tr>
		<tr style="height:48px;">
			<td style="height:96px;" rowspan="8">4.质量控制<br>（45分）</td>
			<td rowspan="3">4.1培训质量</td>
			<td>
				4.1.1执业医师资格考试通过率
			</td>
			<td>
				近2年住院医师首次参加执业医师资格考试的通过情况
			</td>
			<td>
				每年度分别计算，各1.5分。<br>
				1.通过率≥85%，且≥本省平均通过率，得1.5分<br>
				2.通过率＜85%，但≥本省平均通过率，得1分<br>
				3.通过率＜本省（区、市）平均通过率，不得分<br>
				（通过率=当年首次参加考试通过的人数/当年度应首次参加考试总人数）
			</td>
			<td style="text-align: center;">3</td>
			<td name="file" itemId="2022-4.1.1" itemName="执业医师资格考试通过率"></td>
			<td><input onchange="saveScore(this,3);" itemId="2022-4.1.1" itemName="执业医师资格考试通过率"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,3);" itemId="2022-4.1.1" itemName="执业医师资格考试通过率"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<textarea  onchange="saveSpeReason(this);" itemId="2022-4.1.1" itemName="执业医师资格考试通过率" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-4.1.1']}</textarea>
			</td>
		</tr>
		<tr style="height:48px;">
			<td>4.1.2住院医师规范化培训结业考核通过率★</td>
			<td>
				近2年住院医师首次参加结业考核的通过情况（理论考核、技能考核同时通过）
			</td>
			<td>
				每年度分别计算，各3分。<br>
				1.通过率≥85%，且≥本省平均通过率，得3分<br>
				2.通过率＜85%，但≥本省平均通过率，得2分<br>
				3.通过率＜本省（区、市）平均通过率，不得分<br>
				（通过率=当年首次参加结业考核通过的人数/当年应首次参加结业考核总人数）
			</td>
			<td style="text-align: center;">6</td>
			<td name="file" itemId="2022-4.1.2" itemName="住院医师规范化培训结业考核通过率"></td>
			<td><input onchange="saveScore(this,6);" itemId="2022-4.1.2" itemName="住院医师规范化培训结业考核通过率"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,6);" itemId="2022-4.1.2" itemName="住院医师规范化培训结业考核通过率"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<textarea  onchange="saveSpeReason(this);" itemId="2022-4.1.2" itemName="住院医师规范化培训结业考核通过率" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-4.1.2']}</textarea>
			</td>
		</tr>
		<tr style="height:48px;">
			<td>4.1.3年度理论测试★</td>
			<td>
				上一年度，本专业住院医师参加年度业务水平测试成绩位于全国前列。
			</td>
			<td>
				位于前15%，得3分；<br>
				位于前20%，得2分；<br>
				位于前25%，得1分。
			</td>
			<td style="text-align: center;">3</td>
			<td name="file" itemId="2022-4.1.3" itemName="年度理论测试"></td>
			<td><input onchange="saveScore(this,3);" itemId="2022-4.1.3" itemName="年度理论测试"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,3);" itemId="2022-4.1.3" itemName="年度理论测试"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<textarea  onchange="saveSpeReason(this);" itemId="2022-4.1.3" itemName="年度理论测试" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-4.1.3']}</textarea>
			</td>
		</tr>
		<tr style="height:48px;">
			<td rowspan="2">4.2考核师资</td>
			<td style="height: 48px;">4.2.1教学查房质量★</td>
			<td>
				指导医师组织规范的教学查房，悉心指导培训对象
			</td>
			<td>
				抽选全科与1个临床轮转科室，每个科室得4分，按实际得分折算（保留小数点后1位）
			</td>
			<td style="text-align: center;">8</td>
			<td name="file" itemId="2022-4.2.1" itemName="教学查房质量"></td>
			<td><input onchange="saveScore(this,8);" itemId="2022-4.2.1" itemName="教学查房质量"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,8);" itemId="2022-4.2.1" itemName="教学查房质量"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<textarea onchange="saveSpeReason(this);"  itemId="2022-4.2.1" itemName="教学查房质量" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-4.2.1']}</textarea>
			</td>
		</tr>
		<tr style="height:48px;">
			<td style="height: 48px;">4.2.2技能操作带教情况★</td>
			<td>
				指导医师协助并指导培训对象完成技能操作，带教严格规范
			</td>
			<td>
				核查1个临床轮转科室及基层实践基地，每个科室4分，按实际得分折算（保留小数点后1位）
			</td>
			<td style="text-align: center;">8</td>
			<td name="file" itemId="2022-4.2.2" itemName="技能操作带教情况"></td>
			<td><input onchange="saveScore(this,8);" itemId="2022-4.2.2" itemName="技能操作带教情况"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,8);" itemId="2022-4.2.2" itemName="技能操作带教情况"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<textarea  onchange="saveSpeReason(this);" itemId="2022-4.2.2" itemName="技能操作带教情况" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-4.2.2']}</textarea>
			</td>
		</tr>
		<tr style="height:48px;">
			<td style="height: 48px;" rowspan="3">4.3考核培训对象</td>
			<td>
				4.3.1医疗文书书写★
			</td>
			<td>
				培训对象临床科室的病历书写、基层实践基地的健康档案书写规范
			</td>
			<td>
				核查1个临床轮转科室及基层实践基地，每个科室4分，按实际得分折算（保留小数点后1位）（无住院医师，不得分）
			</td>
			<td style="text-align: center;">8</td>
			<td name="file" itemId="2022-4.3.1" itemName="医疗文书书写"></td>
			<td><input onchange="saveScore(this,8);" itemId="2022-4.3.1" itemName="医疗文书书写"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,8);" itemId="2022-4.3.1" itemName="医疗文书书写"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<textarea onchange="saveSpeReason(this);"  itemId="2022-4.3.1" itemName="医疗文书书写" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-4.3.1']}</textarea>
			</td>
		</tr>
		<tr style="height:48px;">
			<td>
				4.3.2技能操作★
			</td>
			<td>
				培训对象技能操作情况
			</td>
			<td>
				核查1个临床轮转科室及基层实践基地，每个科室4分，按实际得分折算（保留小数点后1位）（无住院医师，不得分）
			</td>
			<td style="text-align: center;">8</td>
			<td name="file" itemId="2022-4.3.2" itemName="技能操作"></td>
			<td><input onchange="saveScore(this,8);" itemId="2022-4.3.2" itemName="技能操作"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,8);" itemId="2022-4.3.2" itemName="技能操作"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<textarea  onchange="saveSpeReason(this);" itemId="2022-4.3.2" itemName="技能操作" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-4.3.2']}</textarea>
			</td>
		</tr>
		<tr style="height:48px;">
			<td>
				4.3.3完成培训内容与要求
			</td>
			<td>
				按照全科培训标准，核实学员培训手册的填写情况
			</td>
			<td>
				核查1个临床科室及基层实践基地，每个科室0.5分<br>
				符合全科培训标准要求，完成率≥90%，得0.5分<br>
				其他或无住院医师，不得分
			</td>
			<td style="text-align: center;">1</td>
			<td name="file" itemId="2022-4.3.3" itemName="技能操作"></td>
			<td><input onchange="saveScore(this,1);" itemId="2022-4.3.3" itemName="技能操作"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,1);" itemId="2022-4.3.3" itemName="技能操作"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<textarea  onchange="saveSpeReason(this);" itemId="2022-4.3.3" itemName="技能操作" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-4.3.3']}</textarea>
			</td>
		</tr>
		<tr style="height:40px;">
			<td colspan="5" style="text-align: right;">合计</td>
			<td style="text-align: center;">100</td>
			<td>&mdash;&mdash;&mdash;</td>
			<td style="text-align: center;"><span id="selfTotalled"></span></td>
			<td style="text-align: center;"><span id="expertTotalled"></span></td>
			<td></td>
		</tr>
		<tr>
			<td colspan="10" style="text-align: left">
				备注：<br>
				&#12288;&#12288;1.指标中所有规章制度，专指住院医师规范化培训相关制度，且有医院正式文件；<br>
				&#12288;&#12288;2.指导医师指具有带教住院医师资格的临床医师。<br>
				&#12288;&#12288;3.原则上考核住院医师必须为在培第二年及以上住院医师。没有住院医师的，该项目得0分。3年无住院医师的，该培训基地不合格；<br>
				&#12288;&#12288;4.全科专业基地（含基层实践基地）聘用服务期内或违约农村订单定向免费医学毕业生的，每聘用1名服务期内或违约定向生扣10分；<br>
				&#12288;&#12288;5.一级指标4项，二级指标11项，三级指标39项。三级指标中，核心指标15项、计66分，一般指标24项、计34分，其中培训质量为45分，共100分。<br>
				&#12288;&#12288;&#12288;&#12288;合格：基本条件合格，且具备下列条件：总分数≥85分；培训质量得分在≥38.25分<br>
				&#12288;&#12288;&#12288;&#12288;基本合格：基本条件合格，且具备下列条件： 70分≤总分数＜85分；31.5分≤培训质量得分＜38.25分<br>
				&#12288;&#12288;&#12288;&#12288;不合格（限期整改）：60分≤总分数＜70分；27分≤培训质量得分＜31.5分<br>
				&#12288;&#12288;&#12288;&#12288;不合格（撤销）：基本条件不合格；总分数＜60分；培训质量得分＜27分 <br>
				<font color="red">*表格数据可能和自评表中存在差距,以实际为准</font>
			</td>
		</tr>
		<c:if test="${GlobalConstant.USER_LIST_LOCAL ne roleFlag}">
			<tr style="height: 30px">
				<td colspan="2" style="text-align:left">
					&#12288;&#12288;专家签字：
				</td>
				<td colspan="3">
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
				</td>
				<td colspan="5" style="text-align:right">
					<fmt:formatDate value="${evaluationDate}" pattern="yyyy 年 MM 月 dd 日"/>&#12288;&#12288;
					<input id="evaluationDate"
						   value="<fmt:formatDate value="${evaluationDate}" pattern="yyyy-MM-dd" />"
						   hidden>
				</td>
			</tr>
		</c:if>
	</table>
</div>
<div class="button" style="margin-top: 25px">
	<c:if test="${('expertLeader' eq roleFlag || 'baseExpert' eq roleFlag || GlobalConstant.USER_LIST_LOCAL eq roleFlag) and editFlag ne 'N'and isRead ne GlobalConstant.RECORD_STATUS_Y}">
		<input class="btn_green" type="button" value="提&#12288;交" onclick="submitScore();" />&#12288;
		<input class="btn_green" type="button" id="zancun" value="暂&#12288存" onclick="zancun();"/>&#12288;
		<input class="btn_green" type="button" id="close" value="取&#12288;消" onclick="top.jboxCloseMessager();" />

        <script type="text/javascript">
            function zancun() {
                top.jboxTip("暂存成功！");
                $('#zancun').hide();
            }
        </script>
	</c:if>
	<c:if test="${editFlag eq 'N'}">
		<input type="button" class="btn_green" id="print" onclick="printEvaScore('${userFlow}','${subjectFlow}');" value="打&#12288;印"/>
	</c:if>
</div>
</body>
</html>