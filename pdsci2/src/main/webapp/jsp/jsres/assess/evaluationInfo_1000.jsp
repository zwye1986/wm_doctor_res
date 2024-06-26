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
				var evaDate = $('#evaluationDate').val();
				var selfTotalled = $('#selfTotalled').text();
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
				<th colspan="11">
					<h2 style="font-size:150%">住院医师规范化培训评估指标——外科（神经外科方向）专业基地</h2>
				</th>
			</tr>
			<tr height="28" >
				<th style="text-align:left;padding-left: 4px;"  height="28"  colspan="6">培训基地（医院）名称：${orgName}</th>
				<th style="text-align:left;padding-left: 4px;"  height="28"  colspan="5">省（区、市）：${orgCityName}</th>
			</tr>
			<tr style="height:16px;">
				<th style="min-width: 320px;max-width: 320px;height: 16px;" colspan="3">评估项目</th>
				<th style="min-width: 230px;max-width: 230px" rowspan="2">评估内容</th>
				<th style="min-width: 140px;max-width: 140px" rowspan="2">现场评估<br>方式</th>
				<th style="min-width: 230px;max-width: 230px" rowspan="2">评分标准</th>
				<th style="min-width: 50px;max-width: 50px" rowspan="2">分值</th>
				<th style="min-width: 130px;max-width: 130px" rowspan="2">附件信息</th>
				<c:if test="${indicators eq 'Y'}">
					<th style="min-width: 54px;max-width: 50px" rowspan="2">自评得分</th>
					<th style="min-width: 54px;max-width: 50px" rowspan="2">专家评分</th>
				</c:if>
				<c:if test="${indicators ne 'Y'}">
					<th style="min-width: 50px;max-width: 50px" rowspan="2">自评得分</th>
					<th style="min-width: 50px;max-width: 50px" rowspan="2">专家评分</th>
				</c:if>
				<th style="min-width: 150px;max-width: 150px" rowspan="2">扣分原因</th>
			</tr>
			<tr style="height:32px">
				<th style="width: 100px;height: 32px;">一级指标</th>
				<th style="width: 100px">二级指标</th>
				<th style="width: 120px">三级指标<br> ★为核心指标</th>
			</tr>
		</table>
	</div>
	<table border="0" cellpadding="0" cellspacing="0" class="grid" style="width: 100%" >
		<tr height="34" class="firstRow">
			<th colspan="11">
				<h2 style="font-size:150%">住院医师规范化培训评估指标——外科（神经外科方向）专业基地</h2>
			</th>
		</tr>
		<tr height="28" >
			<th style="text-align:left;padding-left: 4px;"  height="28"  colspan="6">培训基地（医院）名称：${orgName}</th>
			<th style="text-align:left;padding-left: 4px;"  height="28"  colspan="5">省（区、市）：${orgCityName}</th>
		</tr>
		<tr style="height:16px;">
			<th style="min-width: 320px;max-width: 320px;height: 16px;" colspan="3">评估项目</th>
			<th style="min-width: 230px;max-width: 230px" rowspan="2">评估内容</th>
			<th style="min-width: 140px;max-width: 140px" rowspan="2">现场评估<br>方式</th>
			<th style="min-width: 230px;max-width: 230px" rowspan="2">评分标准</th>
			<th style="min-width: 50px;max-width: 50px" rowspan="2">分值</th>
			<th style="min-width: 130px;max-width: 130px" rowspan="2">附件信息</th>
			<th style="min-width: 50px;max-width: 50px" rowspan="2">自评得分</th>
			<th style="min-width: 50px;max-width: 50px" rowspan="2">专家评分</th>
			<th style="min-width: 150px;max-width: 150px" rowspan="2">扣分原因</th>
		</tr>
		<tr style="height:32px">
			<th style="width: 100px;height: 32px;">一级指标</th>
			<th style="width: 100px">二级指标</th>
			<th style="width: 120px">三级指标<br> ★为核心指标</th>
		</tr>
		<tr style="height:50px">
			<td style="height: 438px;" rowspan="13">1.基本条件<br>（18分）</td>
			<td rowspan="10">1.1专业基地所在医院条件</td>
			<td>1.1.1总床位数</td>
			<td width="313px">
				三级甲等综合医院神经外科床位总数≥60张
			</td>
			<td width="193px">检查相关文件复印件，需加盖医院公章 ，实地考查</td>
			<td width="313px">
				符合标准，得满分<br>
				不达标准，不得分
			</td>
			<td style="text-align: center;width:74px;">1</td>
			<td name="file" itemId="2022-1.1.1" itemName="总床位数" width="180px"></td>
			<td width="74px"><input onchange="saveScoreOnlyIntenger(this,1);" itemId="2022-1.1.1" value="${ownerScoreMap['2022-1.1.1']}" itemName="总床位数"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td width="74px"><input onchange="saveScore4ExpertOnlyIntenger(this,1);" itemId="2022-1.1.1" itemName="总床位数"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input type="radio" name="2022-1.1.1" value="1" onchange="selectRadio('2022-1.1.1','1','self','1','')"/>
					&nbsp;<label>符合标准，得满分</label><br>
					<input type="radio" name="2022-1.1.1" value="0" onchange="selectRadio('2022-1.1.1','0','self','1','不达标准，不得分')"/>
					&nbsp;<label>不达标准，不得分</label>
				</c:if>
				<c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input  type="radio" name="2022-1.1.1" value="1" onchange="selectRadio('2022-1.1.1','1','expert','1','')"/>
					&nbsp;<label>符合标准，得满分</label><br>
					<input  type="radio" name="2022-1.1.1" value="0" onchange="selectRadio('2022-1.1.1','0','expert','1','不达标准，不得分')"/>
					&nbsp;<label>不达标准，不得分</label>
				</c:if>
			 <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea  onchange="saveSpeReason(this);" itemId="2022-1.1.1" itemName="总床位数" name="reason"
							   placeholder="请填写扣分原因" style="width: 98%"
							   class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-1.1.1']}</textarea>
				</c:if>
			</td>
		</tr>
		<tr style="height:53px;">
			<td>1.1.2年收治病人数</td>
			<td>
				≥1300人次
			</td>
			<td rowspan="3">检查相关统计报表复印件，需加盖医院公章</td>
			<td>
				符合标准，得1分<br>
				不达标准，不得分
			</td>
			<td style="text-align: center;">1</td>
			<td name="file" itemId="2022-1.1.2" itemName="年收治病人数" ></td>
			<td><input onchange="saveScoreOnlyIntenger(this,1);" itemId="2022-1.1.2" itemName="年收治病人数"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,1);" itemId="2022-1.1.2" itemName="年收治病人数"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input type="radio" name="2022-1.1.2" value="1" onchange="selectRadio('2022-1.1.2','1','self','1','')"/>
					&nbsp;<label>符合标准，得1分</label><br>
					<input type="radio" name="2022-1.1.2" value="0" onchange="selectRadio('2022-1.1.2','0','self','1','不达标准，不得分')"/>
					&nbsp;<label>不达标准，不得分</label>
				</c:if>
				<c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input  type="radio" name="2022-1.1.2" value="1" onchange="selectRadio('2022-1.1.2','1','expert','1','')"/>
					&nbsp;<label>符合标准，得1分</label><br>
					<input  type="radio" name="2022-1.1.2" value="0" onchange="selectRadio('2022-1.1.2','0','expert','1','不达标准，不得分')"/>
					&nbsp;<label>不达标准，不得分</label>
				</c:if>
			 <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea  onchange="saveSpeReason(this);" itemId="2022-1.1.2" itemName="年收治病人数" name="reason"
							   placeholder="请填写扣分原因" style="width: 98%"
							   class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-1.1.2']}</textarea>
				</c:if>
			</td>
		</tr>
		<tr style="height:53px;">
			<td>1.1.3年门诊量</td>
			<td>
				≥5000人次
			</td>
			<td>
				符合标准，得1分<br>
				不达标准，不得分
			</td>
			<td style="text-align: center;">1</td>
			<td name="file" itemId="2022-1.1.3" itemName="年门诊量" ></td>
			<td><input onchange="saveScoreOnlyIntenger(this,1);" itemId="2022-1.1.3" itemName="年门诊量"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,1);" itemId="2022-1.1.3" itemName="年门诊量"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input type="radio" name="2022-1.1.3" value="1" onchange="selectRadio('2022-1.1.3','1','self','1','')"/>
					&nbsp;<label>符合标准，得1分</label><br>
					<input type="radio" name="2022-1.1.3" value="0" onchange="selectRadio('2022-1.1.3','0','self','1','不达标准，不得分')"/>
					&nbsp;<label>不达标准，不得分</label>
				</c:if>
				<c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input  type="radio" name="2022-1.1.3" value="1" onchange="selectRadio('2022-1.1.3','1','expert','1','')"/>
					&nbsp;<label>符合标准，得1分</label><br>
					<input  type="radio" name="2022-1.1.3" value="0" onchange="selectRadio('2022-1.1.3','0','expert','1','不达标准，不得分')"/>
					&nbsp;<label>不达标准，不得分</label>
				</c:if>
			 <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea  onchange="saveSpeReason(this);" itemId="2022-1.1.3" itemName="年门诊量" name="reason"
							   placeholder="请填写扣分原因" style="width: 98%"
							   class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-1.1.3']}</textarea>
				</c:if>
			</td>
		</tr>
		<tr style="height:39px;">
			<td>1.1.4年急诊量</td>
			<td>
				≥800人次
			</td>
			<td>
				符合标准，得1分<br>
				不达标准，不得分
			</td>
			<td style="text-align: center;">1</td>
			<td name="file" itemId="2022-1.1.4" itemName="年急诊量" ></td>
			<td><input onchange="saveScoreOnlyIntenger(this,1);" itemId="2022-1.1.4" itemName="年急诊量"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,1);" itemId="2022-1.1.4" itemName="年急诊量"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input type="radio" name="2022-1.1.4" value="1" onchange="selectRadio('2022-1.1.4','1','self','1','')"/>
					&nbsp;<label>符合标准，得1分</label><br>
					<input type="radio" name="2022-1.1.4" value="0" onchange="selectRadio('2022-1.1.4','0','self','1','不达标准，不得分')"/>
					&nbsp;<label>不达标准，不得分</label>
				</c:if>
				<c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input  type="radio" name="2022-1.1.4" value="1" onchange="selectRadio('2022-1.1.4','1','expert','1','')"/>
					&nbsp;<label>符合标准，得1分</label><br>
					<input  type="radio" name="2022-1.1.4" value="0" onchange="selectRadio('2022-1.1.4','0','expert','1','不达标准，不得分')"/>
					&nbsp;<label>不达标准，不得分</label>
				</c:if>
			 <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea  onchange="saveSpeReason(this);" itemId="2022-1.1.4" itemName="年急诊量" name="reason"
							   placeholder="请填写扣分原因" style="width: 98%"
							   class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-1.1.4']}</textarea>
				</c:if>
			</td>
		</tr>
		<tr style="height:32px;">
			<td style="height: 32px;">1.1.5科室和实验室</td>
			<td>
				必备科室:普通外科、骨科、泌尿外科、胸心外科、麻醉科、外科重症监护治疗室(SICU)、神经外科、医院门诊、急诊科、内科、中心手术室<br>
				辅助科室：放射科、超声科、核医学科、病理科、检验医学科、输血科，外科实验室
			</td>
			<td>查看相关文件，实地考查</td>
			<td>
				必备科室缺1个科室，不得分<br>
				辅助科室缺2个科室，不得分
			</td>
			<td style="text-align: center;">1</td>
			<td name="file" itemId="2022-1.1.5" itemName="科室和实验室"></td>
			<td><input onchange="saveScoreOnlyIntenger(this,1);" itemId="2022-1.1.5" itemName="科室和实验室"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,1);" itemId="2022-1.1.5" itemName="科室和实验室"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input type="radio" name="2022-1.1.5" value="1" onchange="selectRadio('2022-1.1.5','1','self','1','')"/>
					&nbsp;<label>符合标准，得1分</label><br>
					<input type="radio" name="2022-1.1.5" value="0" onchange="selectRadio('2022-1.1.5','0','self','1','必备科室缺1个科室或辅助科室缺2个科室，不得分')"/>
					&nbsp;<label>必备科室缺1个科室或辅助科室缺2个科室，不得分</label>
				</c:if>
				<c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input type="radio" name="2022-1.1.5" value="1" onchange="selectRadio('2022-1.1.5','1','expert','1','')"/>
					&nbsp;<label>符合标准，得1分</label><br>
					<input type="radio" name="2022-1.1.5" value="0" onchange="selectRadio('2022-1.1.5','0','expert','1','必备科室缺1个科室或辅助科室缺2个科室，不得分')"/>
					&nbsp;<label>必备科室缺1个科室或辅助科室缺2个科室，不得分</label>
				</c:if>
			 <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea  onchange="saveSpeReason(this);" itemId="2022-1.1.5" itemName="科室和实验室" name="reason"
							   placeholder="请填写扣分原因" style="width: 98%"
							   class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-1.1.5']}</textarea>
				</c:if>
			</td>
		</tr>
		<tr style="height:32px;">
			<td style="height: 32px;">1.1.6轮转科室</td>
			<td>
				普通外科(包括门、急诊)、骨科(包括门、急诊)、泌尿外科、胸心外科、 神经外科、 麻醉科、外科重症监护室
			</td>
			<td>
				1.查看各亚专业（专科）设置名称<br>
				2.查看培训对象轮转计划和轮转登记手册<br>
				3.实地考查，访谈培训对象
			</td>
			<td>
				科室齐全，得满分<br>
				缺1个科室，不得分
			</td>
			<td style="text-align: center;">2</td>
			<td name="file" itemId="2022-1.1.6" itemName="轮转科室"></td>
			<td><input onchange="saveScoreOnlyIntenger(this,2);" itemId="2022-1.1.6" itemName="轮转科室"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,2);" itemId="2022-1.1.6" itemName="轮转科室"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input type="radio" name="2022-1.1.6" value="2" onchange="selectRadio('2022-1.1.6','2','self','2','')"/>
					&nbsp;<label>科室齐全，得满分</label><br>
					<input type="radio" name="2022-1.1.6" value="0" onchange="selectRadio('2022-1.1.6','0','self','2','缺1个科室，不得分')"/>
					&nbsp;<label>缺1个科室，不得分</label>
				</c:if>
				<c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input type="radio" name="2022-1.1.6" value="2" onchange="selectRadio('2022-1.1.6','2','expert','2','')"/>
					&nbsp;<label>科室齐全，得满分</label><br>
					<input type="radio" name="2022-1.1.6" value="0" onchange="selectRadio('2022-1.1.6','0','expert','2','缺1个科室，不得分')"/>
					&nbsp;<label>缺1个科室，不得分</label>
				</c:if>
			 <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea  onchange="saveSpeReason(this);" itemId="2022-1.1.6" itemName="轮转科室" name="reason"
							   placeholder="请填写扣分原因" style="width: 98%"
							   class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-1.1.6']}</textarea>
				</c:if>
			</td>
		</tr>
		<tr style="height:64px">
			<td style="height: 32px;">1.1.7疾病种类及数量</td>
			<td rowspan="2">
				符合《住院医师规范化培训基地认定标准（试行）》和《住院医师规范化培训内容与标准（试行）》外科（神经外科方向）专业细则要求，详见
					<a href="javascript:void(0)" onclick="Schedule('evaluationInfo_1000_01','附表1',450);">附表1</a>

			</td>
			<td>
				核对上一年度各亚专业（专科）收治疾病种类及数量统计报表
			</td>
			<td>
				符合要求（含协同单位），得满分<br>
				疾病种类及数量≥规定数的90%，得1分<br>
				疾病种类及数量≥规定数的85%，得0.5分<br>
				疾病种类及数量＜规定数的85%，不得分
			</td>
			<td style="text-align: center;">2</td>
			<td name="file" itemId="2022-1.1.7" itemName="疾病种类及数量"></td>
			<input type="hidden" id="scoreInfo11" value="${scoreInfo11}" name="scoreOneInfo">
			<td><input id="fubiao11" onchange="saveScore(this,2);" itemId="2022-1.1.7" itemName="疾病种类及数量"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input id="fubiao11Expert" onchange="saveScore4Expert(this,2);" itemId="2022-1.1.7" itemName="疾病种类及数量"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<textarea  onchange="saveSpeReason(this);" itemId="2022-1.1.7" itemName="疾病种类及数量" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-1.1.7']}</textarea>
			</td>
		</tr>
		<tr style="height:64px;">
			<td style="height: 32px;">1.1.8技能操作和手术种类及数量★</td>
			<td>
				核对上一年度各亚专业（专科）技能操作、手术种类及数量的统计报表
			</td>
			<td>
				符合要求（含协同单位），得满分<br>
				技能操作和手术种类及数量≥规定数的90%，得2分<br>
				技能操作和手术种类及数量≥规定数的85%，得1分<br>
				技能操作和手术种类及数量＜规定数的85%，不得分
			</td>
			<td style="text-align: center;">4</td>
			<td name="file" itemId="2022-1.1.8" itemName="技能操作和手术种类及数量"></td>
			<input type="hidden" id="scoreInfo21" value="${scoreInfo21}" name="scoreTwoInfo">
			<td><input id="fubiao12" onchange="saveScore(this,4);" itemId="2022-1.1.8" itemName="技能操作和手术种类及数量"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input id="fubiao12Expert" onchange="saveScore4Expert(this,4);" itemId="2022-1.1.8" itemName="技能操作和手术种类及数量"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<textarea  onchange="saveSpeReason(this);" itemId="2022-1.1.8" itemName="技能操作和手术种类及数量" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-1.1.8']}</textarea>
			</td>
		</tr>
		<tr style="height:50px;">
			<td style="height: 32px;">1.1.9医院设备</td>
			<td>
				X射线机、数字减影血管造影机、CT、MRI、放射治疗机、彩色B超(带 Doppler等探头)
			</td>
			<td>
				检查设备清单复印件，需加盖医院公章，实地考查
			</td>
			<td>
				缺1项，不得分
			</td>
			<td style="text-align: center;">1</td>
			<td name="file" itemId="2022-1.1.9" itemName="医院设备"></td>
			<td><input onchange="saveScoreOnlyIntenger(this,1);" itemId="2022-1.1.9" itemName="医院设备"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,1);" itemId="2022-1.1.9" itemName="医院设备"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input type="radio" name="2022-1.1.9" value="1" onchange="selectRadio('2022-1.1.9','1','self','1','')"/>
					&nbsp;<label>设备齐全，得满分</label><br>
					<input type="radio" name="2022-1.1.9" value="0" onchange="selectRadio('2022-1.1.9','0','self','1','缺1项，不得分')"/>
					&nbsp;<label>缺1项，不得分</label>
				</c:if>
				<c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input type="radio" name="2022-1.1.9" value="1" onchange="selectRadio('2022-1.1.9','1','expert','1','')"/>
					&nbsp;<label>设备齐全，得满分</label><br>
					<input type="radio" name="2022-1.1.9" value="0" onchange="selectRadio('2022-1.1.9','0','expert','1','缺1项，不得分')"/>
					&nbsp;<label>缺1项，不得分</label>
				</c:if>
			 <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea  onchange="saveSpeReason(this);" itemId="2022-1.1.9" itemName="医院设备" name="reason"
							   placeholder="请填写扣分原因" style="width: 98%"
							   class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-1.1.9']}</textarea>
				</c:if>
			</td>
		</tr>
		<tr style="height:50px;">
			<td style="height: 32px;">1.1.10专业基地设备</td>
			<td>
				1.神经外科手术显微镜<br>
				2.呼吸机<br>
				3.床旁多功能监护仪<br>
				4.术中电生理监护仪（诱发电位、脑电图）<br>
				5.颅内压监护仪<br>
				6.头架、升降手术床、神经外科显微器械<br>
				7.术中超声仪<br>
				8.神经外科导航设备<br>
				9.CUSA<br>
				10.神经内镜系统<br>
				11.功能和立体定性系统
			</td>
			<td>
				检查设备清单复印件，需加盖医院公章，实地考查
			</td>
			<td>
				缺1项，不得分
			</td>
			<td style="text-align: center;">1</td>
			<td name="file" itemId="2022-1.1.10" itemName="专业基地设备"></td>
			<td><input onchange="saveScoreOnlyIntenger(this,1);" itemId="2022-1.1.10" itemName="专业基地设备"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,1);" itemId="2022-1.1.10" itemName="专业基地设备"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input type="radio" name="2022-1.1.10" value="1" onchange="selectRadio('2022-1.1.10','1','self','1','')"/>
					&nbsp;<label>设备齐全，得满分</label><br>
					<input type="radio" name="2022-1.1.10" value="0" onchange="selectRadio('2022-1.1.10','0','self','1','缺1项，不得分')"/>
					&nbsp;<label>缺1项，不得分</label>
				</c:if>
				<c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input type="radio" name="2022-1.1.10" value="1" onchange="selectRadio('2022-1.1.10','1','expert','1','')"/>
					&nbsp;<label>设备齐全，得满分</label><br>
					<input type="radio" name="2022-1.1.10" value="0" onchange="selectRadio('2022-1.1.10','0','expert','1','缺1项，不得分')"/>
					&nbsp;<label>缺1项，不得分</label>
				</c:if>
			 <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea  onchange="saveSpeReason(this);" itemId="2022-1.1.10" itemName="专业基地设备" name="reason"
							   placeholder="请填写扣分原因" style="width: 98%"
							   class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-1.1.10']}</textarea>
				</c:if>
			</td>
		</tr>
		<tr style="height:50px;">
			<td rowspan="3">1.2协同单位</td>
			<td>1.2.1协同数</td>
			<td>
				协同数量不应超过3个
			</td>
			<td rowspan="3">
				查看原始资料，核实相关信息
			</td>
			<td>
				满足要求，得1分（无协同单位的专业基地，此处不失分）
			</td>
			<td style="text-align: center;">1</td>
			<td name="file" itemId="2022-1.2.1" itemName="协同数"></td>
			<td><input onchange="saveScoreOnlyIntenger(this,1);" itemId="2022-1.2.1" itemName="协同数"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,1);" itemId="2022-1.2.1" itemName="协同数"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input type="radio" name="2022-1.2.1" value="1" onchange="selectRadio('2022-1.2.1','1','self','1','')"/>
					&nbsp;<label>满足要求，得1分（无协同单位的专业基地，此处不失分）</label><br>
					<input type="radio" name="2022-1.2.1" value="0" onchange="selectRadio('2022-1.2.1','0','self','1','不满足要求，不得分')"/>
					&nbsp;<label>不满足要求，不得分</label>
				</c:if>
				<c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input type="radio" name="2022-1.2.1" value="1" onchange="selectRadio('2022-1.2.1','1','expert','1','')"/>
					&nbsp;<label>满足要求，得1分（无协同单位的专业基地，此处不失分）</label><br>
					<input type="radio" name="2022-1.2.1" value="0" onchange="selectRadio('2022-1.2.1','0','expert','1','不满足要求，不得分')"/>
					&nbsp;<label>不满足要求，不得分</label>
				</c:if>
			 <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea  onchange="saveSpeReason(this);" itemId="2022-1.2.1" itemName="协同数" name="reason"
							   placeholder="请填写扣分原因" style="width: 98%"
							   class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-1.2.1']}</textarea>
				</c:if>
			</td>
		</tr>
		<tr style="height:50px;">
			<td>1.2.2协同床位数</td>
			<td>
				各亚专业（专科）床位数（参照《住院医师规范化培训基地认定标准（试行）》本专业细则要求）
			</td>
			<td>
				满足要求，得1分（无协同单位的专业基地，此处不失分）
			</td>
			<td style="text-align: center;">1</td>
			<td name="file" itemId="2022-1.2.2" itemName="协同床位数"></td>
			<td><input onchange="saveScoreOnlyIntenger(this,1);" itemId="2022-1.2.2" itemName="协同床位数"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,1);" itemId="2022-1.2.2" itemName="协同床位数"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input type="radio" name="2022-1.2.2" value="1" onchange="selectRadio('2022-1.2.2','1','self','1','')"/>
					&nbsp;<label>满足要求，得1分（无协同单位的专业基地，此处不失分）</label><br>
					<input type="radio" name="2022-1.2.2" value="0" onchange="selectRadio('2022-1.2.2','0','self','1','不满足要求，不得分')"/>
					&nbsp;<label>不满足要求，不得分</label>
				</c:if>
				<c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input type="radio" name="2022-1.2.2" value="1" onchange="selectRadio('2022-1.2.2','1','expert','1','')"/>
					&nbsp;<label>满足要求，得1分（无协同单位的专业基地，此处不失分）</label><br>
					<input type="radio" name="2022-1.2.2" value="0" onchange="selectRadio('2022-1.2.2','0','expert','1','不满足要求，不得分')"/>
					&nbsp;<label>不满足要求，不得分</label>
				</c:if>
			 <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea  onchange="saveSpeReason(this);" itemId="2022-1.2.2" itemName="协同床位数" name="reason"
							   placeholder="请填写扣分原因" style="width: 98%"
							   class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-1.2.2']}</textarea>
				</c:if>
			</td>
		</tr>
		<tr style="height:50px;">
			<td>1.2.3轮转时间</td>
			<td>
				在协同亚专业（专科）轮转时间不超过3个月
			</td>
			<td>
				满足要求，得1分（无协同单位的专业基地，此处不失分）
			</td>
			<td style="text-align: center;">1</td>
			<td name="file" itemId="2022-1.2.3" itemName="轮转时间"></td>
			<td><input onchange="saveScoreOnlyIntenger(this,1);" itemId="2022-1.2.3" itemName="轮转时间"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,1);" itemId="2022-1.2.3" itemName="轮转时间"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input type="radio" name="2022-1.2.3" value="1" onchange="selectRadio('2022-1.2.3','1','self','1','')"/>
					&nbsp;<label>满足要求，得1分（无协同单位的专业基地，此处不失分）</label><br>
					<input type="radio" name="2022-1.2.3" value="0" onchange="selectRadio('2022-1.2.3','0','self','1','不满足要求，不得分')"/>
					&nbsp;<label>不满足要求，不得分</label>
				</c:if>
				<c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input type="radio" name="2022-1.2.3" value="1" onchange="selectRadio('2022-1.2.3','1','expert','1','')"/>
					&nbsp;<label>满足要求，得1分（无协同单位的专业基地，此处不失分）</label><br>
					<input type="radio" name="2022-1.2.3" value="0" onchange="selectRadio('2022-1.2.3','0','expert','1','不满足要求，不得分')"/>
					&nbsp;<label>不满足要求，不得分</label>
				</c:if>
			 <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea  onchange="saveSpeReason(this);" itemId="2022-1.2.3" itemName="轮转时间" name="reason"
							   placeholder="请填写扣分原因" style="width: 98%"
							   class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-1.2.3']}</textarea>
				</c:if>
			</td>
		</tr>

		<tr style="height:60px;">
			<td style="height: 438px;" rowspan="7">2.师资条件<br>（17分）</td>
			<td rowspan="4">2.1师资情况</td>
			<td>2.1.1带教医师与培训对象比例★</td>
			<td>
				每名带教医师同时带教本专业培训对象不超过3名
			</td>
			<td>
				查看原始资料，访谈培训对象
			</td>
			<td>
				不达标准，不得分
			</td>
			<td style="text-align: center;">3</td>
			<td name="file" itemId="2022-2.1.1" itemName="带教医师与培训对象比例"></td>
			<td><input onchange="saveScore(this,3);" itemId="2022-2.1.1" itemName="带教医师与培训对象比例"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,3);" itemId="2022-2.1.1" itemName="带教医师与培训对象比例"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<textarea  onchange="saveSpeReason(this);" itemId="2022-2.1.1" itemName="带教医师与培训对象比例" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-2.1.1']}</textarea>
			</td>
		</tr>
		<tr style="height:38px;">
			<td style="height: 38px;">2.1.2带教医师条件</td>
			<td>
				医学本科及以上学历,主治医师专业技术职务3年以上，从事外科专业工作5年以上
			</td>
			<td rowspan="3">
				查看人事部门提供的师资状况统计表，包括姓名、毕业时间、毕业学校、学历学位、专业技术职务、
				专业技术职务任职时间、工作时间，需加盖人事部门公章
			</td>
			<td>
				其中1名带教医师不符合要求，不得分
			</td>
			<td style="text-align: center;">1</td>
			<td name="file" itemId="2022-2.1.2" itemName="带教医师条件"></td>
			<td><input onchange="saveScoreOnlyIntenger(this,1);" itemId="2022-2.1.2" itemName="带教医师条件"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,1);" itemId="2022-2.1.2" itemName="带教医师条件"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input type="radio" name="2022-2.1.2" value="1" onchange="selectRadio('2022-2.1.2','1','self','1','')"/>
					&nbsp;<label>符合要求，得满分</label><br>
					<input type="radio" name="2022-2.1.2" value="0" onchange="selectRadio('2022-2.1.2','0','self','1','其中1名带教医师不符合要求，不得分')"/>
					&nbsp;<label>其中1名带教医师不符合要求，不得分</label>
				</c:if>
				<c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input type="radio" name="2022-2.1.2" value="1" onchange="selectRadio('2022-2.1.2','1','expert','1','')"/>
					&nbsp;<label>符合要求，得满分</label><br>
					<input type="radio" name="2022-2.1.2" value="0" onchange="selectRadio('2022-2.1.2','0','expert','1','其中1名带教医师不符合要求，不得分')"/>
					&nbsp;<label>其中1名带教医师不符合要求，不得分</label>
				</c:if>
			 <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea  onchange="saveSpeReason(this);" itemId="2022-2.1.2" itemName="带教医师条件" name="reason"
							   placeholder="请填写扣分原因" style="width: 98%"
							   class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-2.1.2']}</textarea>
				</c:if>
			</td>
		</tr>
		<tr style="height:38px;">
			<td style="height: 38px;">2.1.3带教医师组成</td>
			<td>
				各亚专业（专科）有主任医师≥1人，副主任医师≥1人，主治医师≥2人
			</td>
			<td>
				1个亚专业（专科）不达标，不得分
			</td>
			<td style="text-align: center;">1</td>
			<td name="file" itemId="2022-2.1.3" itemName="带教医师组成"></td>
			<td><input onchange="saveScoreOnlyIntenger(this,1);" itemId="2022-2.1.3" itemName="带教医师组成"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,1);" itemId="2022-2.1.3" itemName="带教医师组成"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input type="radio" name="2022-2.1.3" value="1" onchange="selectRadio('2022-2.1.3','1','self','1','')"/>
					&nbsp;<label>符合要求，得满分</label><br>
					<input type="radio" name="2022-2.1.3" value="0" onchange="selectRadio('2022-2.1.3','0','self','1','1个亚专业（专科）不达标，不得分')"/>
					&nbsp;<label>1个亚专业（专科）不达标，不得分</label>
				</c:if>
				<c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input type="radio" name="2022-2.1.3" value="1" onchange="selectRadio('2022-2.1.3','1','expert','1','')"/>
					&nbsp;<label>符合要求，得满分</label><br>
					<input type="radio" name="2022-2.1.3" value="0" onchange="selectRadio('2022-2.1.3','0','expert','1','1个亚专业（专科）不达标，不得分')"/>
					&nbsp;<label>1个亚专业（专科）不达标，不得分</label>
				</c:if>
			 <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea onchange="saveSpeReason(this);"  itemId="2022-2.1.3" itemName="带教医师组成" name="reason"
							  placeholder="请填写扣分原因" style="width: 98%"
							  class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-2.1.3']}</textarea>
				</c:if>
			</td>
		</tr>
		<tr style="height:38px;">
			<td style="height: 38px;">2.1.4专业基地负责人条件</td>
			<td>
				医学本科及以上学历，主任医师专业技术职务，从事神经外科专业的医疗、科研和教学工作超过15年
			</td>
			<td>
				1项不符合条件，不得分
			</td>
			<td style="text-align: center;">1</td>
			<td name="file" itemId="2022-2.1.4" itemName="专业基地负责人条件"></td>
			<td><input onchange="saveScoreOnlyIntenger(this,1);" itemId="2022-2.1.4" itemName="专业基地负责人条件"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,1);" itemId="2022-2.1.4" itemName="专业基地负责人条件"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input type="radio" name="2022-2.1.4" value="1" onchange="selectRadio('2022-2.1.4','1','self','1','')"/>
					&nbsp;<label>符合条件，得满分</label><br>
					<input type="radio" name="2022-2.1.4" value="0" onchange="selectRadio('2022-2.1.4','0','self','1','1项不符合条件，不得分')"/>
					&nbsp;<label>1项不符合条件，不得分</label>
				</c:if>
				<c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input type="radio" name="2022-2.1.4" value="1" onchange="selectRadio('2022-2.1.4','1','expert','1','')"/>
					&nbsp;<label>符合条件，得满分</label><br>
					<input type="radio" name="2022-2.1.4" value="0" onchange="selectRadio('2022-2.1.4','0','expert','1','1项不符合条件，不得分')"/>
					&nbsp;<label>1项不符合条件，不得分</label>
				</c:if>
			 <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea  onchange="saveSpeReason(this);" itemId="2022-2.1.4" itemName="专业基地负责人条件" name="reason"
							   placeholder="请填写扣分原因" style="width: 98%"
							   class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-2.1.4']}</textarea>
				</c:if>
			</td>
		</tr>
		<tr style="height:35px;">
			<td rowspan="3">2.2师资建设</td>
			<td>2.2.1师资培训★</td>
			<td>
				带教医师均参加过院级师资培训<br>
				各亚专业（专科）至少1名带教医师参加过省级及以上师资培训
			</td>
			<td>
				查看培训资料、名单和培训证书
			</td>
			<td>
				2项培训均满足，得满分<br>
				1项满足，得1分
			</td>
			<td style="text-align: center;">3</td>
			<td name="file" itemId="2022-2.2.1" itemName="师资培训"></td>
			<td><input onchange="saveScoreOnlyIntenger(this,3);" itemId="2022-2.2.1" itemName="师资培训"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,3);" itemId="2022-2.2.1" itemName="师资培训"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input type="radio" name="2022-2.2.1" value="3" onchange="selectRadio('2022-2.2.1','3','self','3','')"/>
					&nbsp;<label>2项培训均满足，得满分</label><br>
					<input type="radio" name="2022-2.2.1" value="1" onchange="selectRadio('2022-2.2.1','1','self','3','1项满足，得1分')"/>
					&nbsp;<label>1项满足，得1分</label>
				</c:if>
				<c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input type="radio" name="2022-2.2.1" value="3" onchange="selectRadio('2022-2.2.1','3','expert','3','')"/>
					&nbsp;<label>2项培训均满足，得满分</label><br>
					<input type="radio" name="2022-2.2.1" value="1" onchange="selectRadio('2022-2.2.1','1','expert','3','1项满足，得1分')"/>
					&nbsp;<label>1项满足，得1分</label>
				</c:if>
			 <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea  onchange="saveSpeReason(this);" itemId="2022-2.2.1" itemName="师资培训" name="reason"
							   placeholder="请填写扣分原因" style="width: 98%"
							   class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-2.2.1']}</textarea>
				</c:if>
			</td>
		</tr>
		<tr style="height:35px;">
			<td>2.2.2师资评价★</td>
			<td>
				每年度至少组织1次对带教医师教学工作进行评价
			</td>
			<td>
				查看原始资料，访谈带教医师和培训对象
			</td>
			<td>
				有评价方案，原始记录详实，得满分<br>
				有评价记录无方案，得2分<br>
				有方案无记录，得1分
			</td>
			<td style="text-align: center;">4</td>
			<td name="file" itemId="2022-2.2.2" itemName="师资评价"></td>
			<td><input onchange="saveScoreOnlyIntenger(this,4);" itemId="2022-2.2.2" itemName="师资评价"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,4);" itemId="2022-2.2.2" itemName="师资评价"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input type="radio" name="2022-2.2.2" value="4" onchange="selectRadio('2022-2.2.2','4','self','4','')"/>
					&nbsp;<label>有评价方案，原始记录详实，得满分</label><br>
					<input type="radio" name="2022-2.2.2" value="2" onchange="selectRadio('2022-2.2.2','2','self','4','有评价记录无方案，得2分')"/>
					&nbsp;<label>有评价记录无方案，得2分</label><br>
					<input type="radio" name="2022-2.2.2" value="1" onchange="selectRadio('2022-2.2.2','1','self','4','有方案无记录，得1分')"/>
					&nbsp;<label>有方案无记录，得1分</label>
				</c:if>
				<c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input type="radio" name="2022-2.2.2" value="4" onchange="selectRadio('2022-2.2.2','4','expert','4','')"/>
					&nbsp;<label>有评价方案，原始记录详实，得满分</label><br>
					<input type="radio" name="2022-2.2.2" value="2" onchange="selectRadio('2022-2.2.2','2','expert','4','有评价记录无方案，得2分')"/>
					&nbsp;<label>有评价记录无方案，得2分</label><br>
					<input type="radio" name="2022-2.2.2" value="1" onchange="selectRadio('2022-2.2.2','1','expert','4','有方案无记录，得1分')"/>
					&nbsp;<label>有方案无记录，得1分</label>
				</c:if>
			 <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea  onchange="saveSpeReason(this);" itemId="2022-2.2.2" itemName="师资评价" name="reason"
							   placeholder="请填写扣分原因" style="width: 98%"
							   class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-2.2.2']}</textarea>
				</c:if>
			</td>
		</tr>
		<tr style="height:35px;">
			<td>2.2.3激励制度★</td>
			<td>
				建立带教医师激励机制，将教学工作与绩效考评、奖金、评优等挂钩
			</td>
			<td>
				查看相关材料，访谈带教医师
			</td>
			<td>
				有激励机制，并与奖金、评优挂钩，得满分<br>
				有激励机制，未与奖金、评优挂钩，得2分<br>
				无，不得分
			</td>
			<td style="text-align: center;">4</td>
			<td name="file" itemId="2022-2.2.3" itemName="激励制度"></td>
			<td><input onchange="saveScoreOnlyIntenger(this,4);" itemId="2022-2.2.3" itemName="激励制度"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,4);" itemId="2022-2.2.3" itemName="激励制度"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input type="radio" name="2022-2.2.3" value="4" onchange="selectRadio('2022-2.2.3','4','self','4','')"/>
					&nbsp;<label>有激励机制，并与奖金、评优挂钩，得满分</label><br>
					<input type="radio" name="2022-2.2.3" value="2" onchange="selectRadio('2022-2.2.3','2','self','4','有激励机制，未与奖金、评优挂钩，得2分')"/>
					&nbsp;<label>有激励机制，未与奖金、评优挂钩，得2分</label><br>
					<input type="radio" name="2022-2.2.3" value="0" onchange="selectRadio('2022-2.2.3','0','self','4','无，不得分')"/>
					&nbsp;<label>无，不得分</label>
				</c:if>
				<c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input type="radio" name="2022-2.2.3" value="4" onchange="selectRadio('2022-2.2.3','4','expert','4','')"/>
					&nbsp;<label>有激励机制，并与奖金、评优挂钩，得满分</label><br>
					<input type="radio" name="2022-2.2.3" value="2" onchange="selectRadio('2022-2.2.3','2','expert','4','有激励机制，未与奖金、评优挂钩，得2分')"/>
					&nbsp;<label>有激励机制，未与奖金、评优挂钩，得2分</label><br>
					<input type="radio" name="2022-2.2.3" value="0" onchange="selectRadio('2022-2.2.3','0','expert','4','无，不得分')"/>
					&nbsp;<label>无，不得分</label>
				</c:if>
			 <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea  onchange="saveSpeReason(this);" itemId="2022-2.2.3" itemName="激励制度" name="reason"
							   placeholder="请填写扣分原因" style="width: 98%"
							   class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-2.2.3']}</textarea>
				</c:if>
			</td>
		</tr>
		<tr style="height:32px;">
			<td style="height: 438px;" rowspan="13">3.过程管理<br>（30分）</td>
			<td style="height:136px;" rowspan="6">3.1培训制度与落实</td>
			<td>3.1.1主任职责</td>
			<td>实行专业基地负责人负责制，并切实落实</td>
			<td rowspan="3">查看相关文件，访谈各类人员</td>
			<td>
				职责明确，履职认真，得1分<br>
				无岗位职责，或履职不认真，不得分
			</td>
			<td style="text-align: center;">1</td>
			<td name="file" itemId="2022-3.1.1" itemName="主任职责"></td>
			<td><input onchange="saveScoreOnlyIntenger(this,1);" itemId="2022-3.1.1" itemName="主任职责"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,1);" itemId="2022-3.1.1" itemName="主任职责"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input type="radio" name="2022-3.1.1" value="1" onchange="selectRadio('2022-3.1.1','1','self','1','')"/>
					&nbsp;<label>职责明确，履职认真，得1分</label><br>
					<input type="radio" name="2022-3.1.1" value="0" onchange="selectRadio('2022-3.1.1','0','self','1','无岗位职责，或履职不认真，不得分')"/>
					&nbsp;<label>无岗位职责，或履职不认真，不得分</label>
				</c:if>
				<c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input type="radio" name="2022-3.1.1" value="1" onchange="selectRadio('2022-3.1.1','1','expert','1','')"/>
					&nbsp;<label>职责明确，履职认真，得1分</label><br>
					<input type="radio" name="2022-3.1.1" value="0" onchange="selectRadio('2022-3.1.1','0','expert','1','无岗位职责，或履职不认真，不得分')"/>
					&nbsp;<label>无岗位职责，或履职不认真，不得分</label>
				</c:if>
			 <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea  onchange="saveSpeReason(this);" itemId="2022-3.1.1" itemName="主任职责" name="reason"
							   placeholder="请填写扣分原因" style="width: 98%"
							   class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.1.1']}</textarea>
				</c:if>
			</td>
		</tr>
		<tr style="height:32px;">
			<td>3.1.2教学主任★</td>
			<td>设置专职教学主任岗位，专门负责本专业基地教学工作的组织实施</td>
			<td>
				职责明确，履职认真，得4分<br>
				无岗位职责，或履职不认真，不得分
			</td>
			<td style="text-align: center;">4</td>
			<td name="file" itemId="2022-3.1.2" itemName="教学主任"></td>
			<td><input onchange="saveScoreOnlyIntenger(this,4);" itemId="2022-3.1.2" itemName="教学主任"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,4);" itemId="2022-3.1.2" itemName="教学主任"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input type="radio" name="2022-3.1.2" value="4" onchange="selectRadio('2022-3.1.2','4','self','4','')"/>
					&nbsp;<label>职责明确，履职认真，得4分</label><br>
					<input type="radio" name="2022-3.1.2" value="0" onchange="selectRadio('2022-3.1.2','0','self','4','无岗位职责，或履职不认真，不得分')"/>
					&nbsp;<label>无岗位职责，或履职不认真，不得分</label>
				</c:if>
				<c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input type="radio" name="2022-3.1.2" value="4" onchange="selectRadio('2022-3.1.2','4','expert','4','')"/>
					&nbsp;<label>职责明确，履职认真，得4分</label><br>
					<input type="radio" name="2022-3.1.2" value="0" onchange="selectRadio('2022-3.1.2','0','expert','4','无岗位职责，或履职不认真，不得分')"/>
					&nbsp;<label>无岗位职责，或履职不认真，不得分</label>
				</c:if>
			 <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea  onchange="saveSpeReason(this);" itemId="2022-3.1.2" itemName="教学主任" name="reason"
							   placeholder="请填写扣分原因" style="width: 98%"
							   class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.1.2']}</textarea>
				</c:if>
			</td>
		</tr>
		<tr style="height:32px;">
			<td>3.1.3教学秘书</td>
			<td>设置专职教学秘书岗位，落实本专业基地教学工作</td>
			<td>
				有教学秘书，履职认真，得1分<br>
				无，或履职不认真，不得分
			</td>
			<td style="text-align: center;">1</td>
			<td name="file" itemId="2022-3.1.3" itemName="教学秘书"></td>
			<td><input onchange="saveScoreOnlyIntenger(this,1);" itemId="2022-3.1.3" itemName="教学秘书"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,1);" itemId="2022-3.1.3" itemName="教学秘书"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input type="radio" name="2022-3.1.3" value="1" onchange="selectRadio('2022-3.1.3','1','self','1','')"/>
					&nbsp;<label>有教学秘书，履职认真，得1分</label><br>
					<input type="radio" name="2022-3.1.3" value="0" onchange="selectRadio('2022-3.1.3','0','self','1','无，或履职不认真，不得分')"/>
					&nbsp;<label>无，或履职不认真，不得分</label>
				</c:if>
				<c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input type="radio" name="2022-3.1.3" value="1" onchange="selectRadio('2022-3.1.3','1','expert','1','')"/>
					&nbsp;<label>有教学秘书，履职认真，得1分</label><br>
					<input type="radio" name="2022-3.1.3" value="0" onchange="selectRadio('2022-3.1.3','0','expert','1','无，或履职不认真，不得分')"/>
					&nbsp;<label>无，或履职不认真，不得分</label>
				</c:if>
			 <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea  onchange="saveSpeReason(this);" itemId="2022-3.1.3" itemName="教学秘书" name="reason"
							   placeholder="请填写扣分原因" style="width: 98%"
							   class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.1.3']}</textarea>
				</c:if>
			</td>
		</tr>
		<tr style="height:32px;">
			<td>3.1.4教学小组</td>
			<td>
				成立教学小组，明确小组职责，定期组织研究教学工作
			</td>
			<td>查看教学小组名单、职责和研究教学工作记录</td>
			<td>
				有教学小组，履职认真，得1分<br>
				无，或履职不认真，不得分
			</td>
			<td style="text-align: center;">1</td>
			<td name="file" itemId="2022-3.1.4" itemName="教学小组"></td>
			<td><input onchange="saveScoreOnlyIntenger(this,1);" itemId="2022-3.1.4" itemName="教学小组"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,1);" itemId="2022-3.1.4" itemName="教学小组"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input type="radio" name="2022-3.1.4" value="1" onchange="selectRadio('2022-3.1.4','1','self','1','')"/>
					&nbsp;<label>有教学小组，履职认真，得1分</label><br>
					<input type="radio" name="2022-3.1.4" value="0" onchange="selectRadio('2022-3.1.4','0','self','1','无，或履职不认真，不得分')"/>
					&nbsp;<label>无，或履职不认真，不得分</label>
				</c:if>
				<c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input type="radio" name="2022-3.1.4" value="1" onchange="selectRadio('2022-3.1.4','1','expert','1','')"/>
					&nbsp;<label>有教学小组，履职认真，得1分</label><br>
					<input type="radio" name="2022-3.1.4" value="0" onchange="selectRadio('2022-3.1.4','0','expert','1','无，或履职不认真，不得分')"/>
					&nbsp;<label>无，或履职不认真，不得分</label>
				</c:if>
			 <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea  onchange="saveSpeReason(this);" itemId="2022-3.1.4" itemName="教学小组" name="reason"
							   placeholder="请填写扣分原因" style="width: 98%"
							   class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.1.4']}</textarea>
				</c:if>
			</td>
		</tr>
		<tr style="height:32px;">
			<td>3.1.5轮转计划★</td>
			<td>按规定落实轮转计划和要求</td>
			<td>查看2～3培训对象轮转手册等原始资料，访谈培训对象</td>
			<td>
				有，且严格落实，得满分<br>
				未严格落实，不得分
			</td>
			<td style="text-align: center;">4</td>
			<td name="file" itemId="2022-3.1.5" itemName="轮转计划"></td>
			<td><input onchange="saveScoreOnlyIntenger(this,4);" itemId="2022-3.1.5" itemName="轮转计划"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,4);" itemId="2022-3.1.5" itemName="轮转计划"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input type="radio" name="2022-3.1.5" value="4" onchange="selectRadio('2022-3.1.5','4','self','4','')"/>
					&nbsp;<label>有，且严格落实，得满分</label><br>
					<input type="radio" name="2022-3.1.5" value="0" onchange="selectRadio('2022-3.1.5','0','self','4','未严格落实，不得分')"/>
					&nbsp;<label>未严格落实，不得分</label>
				</c:if>
				<c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input type="radio" name="2022-3.1.5" value="4" onchange="selectRadio('2022-3.1.5','4','expert','4','')"/>
					&nbsp;<label>有，且严格落实，得满分</label><br>
					<input type="radio" name="2022-3.1.5" value="0" onchange="selectRadio('2022-3.1.5','0','expert','4','未严格落实，不得分')"/>
					&nbsp;<label>未严格落实，不得分</label>
				</c:if>
			 <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea  onchange="saveSpeReason(this);" itemId="2022-3.1.5" itemName="轮转计划" name="reason"
							   placeholder="请填写扣分原因" style="width: 98%"
							   class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.1.5']}</textarea>
				</c:if>
			</td>
		</tr>
		<tr style="height:32px;">
			<td>3.1.6考勤制度</td>
			<td>有考勤规章制度规定，有专人负责，并严格执行</td>
			<td>查看考勤规章制度，并抽查2～3名培训对象考勤记录原始资料</td>
			<td>
				有，且严格落实，得满分<br>
				未严格落实，不得分
			</td>
			<td style="text-align: center;">2</td>
			<td name="file" itemId="2022-3.1.6" itemName="考勤制度"></td>
			<td><input onchange="saveScoreOnlyIntenger(this,2);" itemId="2022-3.1.6" itemName="考勤制度"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,2);" itemId="2022-3.1.6" itemName="考勤制度"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input type="radio" name="2022-3.1.6" value="2" onchange="selectRadio('2022-3.1.6','2','self','2','')"/>
					&nbsp;<label>有，且严格落实，得满分</label><br>
					<input type="radio" name="2022-3.1.6" value="0" onchange="selectRadio('2022-3.1.6','0','self','2','未严格落实，不得分')"/>
					&nbsp;<label>未严格落实，不得分</label>
				</c:if>
				<c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input type="radio" name="2022-3.1.6" value="2" onchange="selectRadio('2022-3.1.6','2','expert','2','')"/>
					&nbsp;<label>有，且严格落实，得满分</label><br>
					<input type="radio" name="2022-3.1.6" value="0" onchange="selectRadio('2022-3.1.6','0','expert','2','未严格落实，不得分')"/>
					&nbsp;<label>未严格落实，不得分</label>
				</c:if>
			 <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea  onchange="saveSpeReason(this);" itemId="2022-3.1.6" itemName="考勤制度" name="reason"
							   placeholder="请填写扣分原因" style="width: 98%"
							   class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.1.6']}</textarea>
				</c:if>
			</td>
		</tr>
		<tr style="height:40px;">
			<td style="height:136px;" rowspan="4">3.2培训活动</td>
			<td style="height: 40px;">3.2.1入科教育</td>
			<td>
				规范实施，包括科室情况、科室纪律、培养计划与要求、医德医风、医患沟通等内容的入科教育，
				并有专人组织实施
			</td>
			<td>提供本年度入科教育原始资料</td>
			<td>
				有，且严格落实，得1分<br>
				未严格落实，不得分
			</td>
			<td style="text-align: center;">1</td>
			<td name="file" itemId="2022-3.2.1" itemName="入科教育"></td>
			<td><input onchange="saveScoreOnlyIntenger(this,1);" itemId="2022-3.2.1" itemName="入科教育"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,1);" itemId="2022-3.2.1" itemName="入科教育"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input type="radio" name="2022-3.2.1" value="1" onchange="selectRadio('2022-3.2.1','1','self','1','')"/>
					&nbsp;<label>有，且严格落实，得1分</label><br>
					<input type="radio" name="2022-3.2.1" value="0" onchange="selectRadio('2022-3.2.1','0','self','1','未严格落实，不得分')"/>
					&nbsp;<label>未严格落实，不得分</label>
				</c:if>
				<c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input type="radio" name="2022-3.2.1" value="1" onchange="selectRadio('2022-3.2.1','1','expert','1','')"/>
					&nbsp;<label>有，且严格落实，得1分</label><br>
					<input type="radio" name="2022-3.2.1" value="0" onchange="selectRadio('2022-3.2.1','0','expert','1','未严格落实，不得分')"/>
					&nbsp;<label>未严格落实，不得分</label>
				</c:if>
			 <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea  onchange="saveSpeReason(this);" itemId="2022-3.2.1" itemName="入科教育" name="reason"
							   placeholder="请填写扣分原因" style="width: 98%"
							   class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.2.1']}</textarea>
				</c:if>
			</td>
		</tr>
		<tr style="height:40px;">
			<td style="height: 40px;">3.2.2教学查房</td>
			<td>
				开展规范的教学查房，至少2周1次
			</td>
			<td rowspan="3">
				提供本年度原始资料，访谈培训对象，核实落实情况
			</td>
			<td>
				开展次数达标，且认真规范，得满分<br>
				未开展或不达标，不得分
			</td>
			<td style="text-align: center;">2</td>
			<td name="file" itemId="2022-3.2.2" itemName="教学查房"></td>
			<td><input onchange="saveScoreOnlyIntenger(this,2);" itemId="2022-3.2.2" itemName="教学查房"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,2);" itemId="2022-3.2.2" itemName="教学查房"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input type="radio" name="2022-3.2.2" value="2" onchange="selectRadio('2022-3.2.2','2','self','2','')"/>
					&nbsp;<label>开展次数达标，且认真规范，得满分</label><br>
					<input type="radio" name="2022-3.2.2" value="0" onchange="selectRadio('2022-3.2.2','0','self','2','未开展或不达标，不得分')"/>
					&nbsp;<label>未开展或不达标，不得分</label>
				</c:if>
				<c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input type="radio" name="2022-3.2.2" value="2" onchange="selectRadio('2022-3.2.2','2','expert','2','')"/>
					&nbsp;<label>开展次数达标，且认真规范，得满分</label><br>
					<input type="radio" name="2022-3.2.2" value="0" onchange="selectRadio('2022-3.2.2','0','expert','2','未开展或不达标，不得分')"/>
					&nbsp;<label>未开展或不达标，不得分</label>
				</c:if>
			 <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea  onchange="saveSpeReason(this);" itemId="2022-3.2.2" itemName="教学查房" name="reason"
							   placeholder="请填写扣分原因" style="width: 98%"
							   class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.2.2']}</textarea>
				</c:if>
			</td>
		</tr>
		<tr style="height:40px;">
			<td style="height: 40px;">3.2.3小讲课</td>
			<td>
				开展规范的小讲课活动，至少1周1次
			</td>
			<td>
				开展次数达标，且认真规范，得满分<br>
				未开展或不达标，不得分
			</td>
			<td style="text-align: center;">2</td>
			<td name="file" itemId="2022-3.2.3" itemName="小讲课"></td>
			<td><input onchange="saveScoreOnlyIntenger(this,2);" itemId="2022-3.2.3" itemName="小讲课"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,2);" itemId="2022-3.2.3" itemName="小讲课"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input type="radio" name="2022-3.2.3" value="2" onchange="selectRadio('2022-3.2.3','2','self','2','')"/>
					&nbsp;<label>开展次数达标，且认真规范，得满分</label><br>
					<input type="radio" name="2022-3.2.3" value="0" onchange="selectRadio('2022-3.2.3','0','self','2','未开展或不达标，不得分')"/>
					&nbsp;<label>未开展或不达标，不得分</label>
				</c:if>
				<c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input type="radio" name="2022-3.2.3" value="2" onchange="selectRadio('2022-3.2.3','2','expert','2','')"/>
					&nbsp;<label>开展次数达标，且认真规范，得满分</label><br>
					<input type="radio" name="2022-3.2.3" value="0" onchange="selectRadio('2022-3.2.3','0','expert','2','未开展或不达标，不得分')"/>
					&nbsp;<label>未开展或不达标，不得分</label>
				</c:if>
			 <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea  onchange="saveSpeReason(this);" itemId="2022-3.2.3" itemName="小讲课" name="reason"
							   placeholder="请填写扣分原因" style="width: 98%"
							   class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.2.3']}</textarea>
				</c:if>
			</td>
		</tr>
		<tr style="height:40px;">
			<td style="height: 40px;">3.2.4疑难病例讨论</td>
			<td>
				开展规范的疑难病例讨论，至少2周1次
			</td>
			<td>
				开展次数达标，且认真规范，得满分<br>
				未开展或不达标，不得分
			</td>
			<td style="text-align: center;">2</td>
			<td name="file" itemId="2022-3.2.4" itemName="疑难病例讨论"></td>
			<td><input onchange="saveScoreOnlyIntenger(this,2);" itemId="2022-3.2.4" itemName="疑难病例讨论"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,2);" itemId="2022-3.2.4" itemName="疑难病例讨论"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input type="radio" name="2022-3.2.4" value="2" onchange="selectRadio('2022-3.2.4','2','self','2','')"/>
					&nbsp;<label>开展次数达标，且认真规范，得满分</label><br>
					<input type="radio" name="2022-3.2.4" value="0" onchange="selectRadio('2022-3.2.4','0','self','2','未开展或不达标，不得分')"/>
					&nbsp;<label>未开展或不达标，不得分</label>
				</c:if>
				<c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input type="radio" name="2022-3.2.4" value="2" onchange="selectRadio('2022-3.2.4','2','expert','2','')"/>
					&nbsp;<label>开展次数达标，且认真规范，得满分</label><br>
					<input type="radio" name="2022-3.2.4" value="0" onchange="selectRadio('2022-3.2.4','0','expert','2','未开展或不达标，不得分')"/>
					&nbsp;<label>未开展或不达标，不得分</label>
				</c:if>
			 <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea  onchange="saveSpeReason(this);" itemId="2022-3.2.4" itemName="疑难病例讨论" name="reason"
							   placeholder="请填写扣分原因" style="width: 98%"
							   class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.2.4']}</textarea>
				</c:if>
			</td>
		</tr>
		<tr style="height:48px;">
			<td style="height:136px;">3.3过程考核</td>
			<td>3.3.1出科考核</td>
			<td>
				理论考核(如临床病例分析)试题、技能操作考核评分标准、培训对象测评结果、考勤记录等原始资料齐全，
				真实规范
			</td>
			<td>
				随机抽查访谈本院、委培、社会招收培训对象各1～2名，检查近1年原始资料
			</td>
			<td>
				考核项目全面，且认真规范，得满分<br>
				仅有技能操作考核，得2分<br>
				仅有理论考试，得1分<br>
				仅有测评结果和考勤记录，得1分
			</td>
			<td style="text-align: center;">3</td>
			<td name="file" itemId="2022-3.3.1" itemName="出科考核"></td>
			<td><input onchange="saveScoreOnlyIntenger(this,3);" itemId="2022-3.3.1" itemName="出科考核"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,3);" itemId="2022-3.3.1" itemName="出科考核"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input type="radio" name="2022-3.3.1" value="3" onchange="selectRadio('2022-3.3.1','3','self','3','')"/>
					&nbsp;<label>考核项目全面，且认真规范，得满分</label><br>
					<input type="radio" name="2022-3.3.1" value="2" onchange="selectRadio('2022-3.3.1','2','self','3','仅有技能操作考核，得2分')"/>
					&nbsp;<label>仅有技能操作考核，得2分</label><br>
					<input type="radio" name="2022-3.3.1" value="1" onchange="selectRadio('2022-3.3.1','1','self','3','仅有理论考试或仅有测评结果和考勤记录，得1分')"/>
					&nbsp;<label>仅有理论考试或仅有测评结果和考勤记录，得1分</label>
				</c:if>
				<c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input type="radio" name="2022-3.3.1" value="3" onchange="selectRadio('2022-3.3.1','3','expert','3','')"/>
					&nbsp;<label>考核项目全面，且认真规范，得满分</label><br>
					<input type="radio" name="2022-3.3.1" value="2" onchange="selectRadio('2022-3.3.1','2','expert','3','仅有技能操作考核，得2分')"/>
					&nbsp;<label>仅有技能操作考核，得2分</label><br>
					<input type="radio" name="2022-3.3.1" value="1" onchange="selectRadio('2022-3.3.1','1','expert','3','仅有理论考试或仅有测评结果和考勤记录，得1分')"/>
					&nbsp;<label>仅有理论考试或仅有测评结果和考勤记录，得1分</label>
				</c:if>
			 <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea  onchange="saveSpeReason(this);" itemId="2022-3.3.1" itemName="出科考核" name="reason"
							   placeholder="请填写扣分原因" style="width: 98%"
							   class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.3.1']}</textarea>
				</c:if>
			</td>
		</tr>
		<tr style="height:48px;">
			<td style="height:136px;" rowspan="2">3.4培训强度</td>
			<td>3.4.1管理床位数★</td>
			<td>
				病房：日管床数≥5张(在带教医师指导下独立于其他培训对象管理床位)
			</td>
			<td rowspan="2">
				访谈本院、委培、社会招收培训对象各1～2名，查看轮转手册等相关材料，随机抽查
			</td>
			<td>
				管床数达到要求，得满分<br>
				管床数≥3张，得2分<br>
				管床数≥2，得1分<br>
				管床数＜2张，或未安排，不得分
			</td>
			<td style="text-align: center;">4</td>
			<td name="file" itemId="2022-3.4.1" itemName="管理病床数"></td>
			<td><input onchange="saveScoreOnlyIntenger(this,4);" itemId="2022-3.4.1" itemName="管理病床数"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,4);" itemId="2022-3.4.1" itemName="管理病床数"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input type="radio" name="2022-3.4.1" value="4" onchange="selectRadio('2022-3.4.1','4','self','4','')"/>
					&nbsp;<label>管床数达到要求，得满分</label><br>
					<input type="radio" name="2022-3.4.1" value="2" onchange="selectRadio('2022-3.4.1','2','self','4','管床数≥3张，得2分')"/>
					&nbsp;<label>管床数≥3张，得2分</label><br>
					<input type="radio" name="2022-3.4.1" value="1" onchange="selectRadio('2022-3.4.1','1','self','4','管床数≥2，得1分')"/>
					&nbsp;<label>管床数≥2，得1分</label><br>
					<input type="radio" name="2022-3.4.1" value="0" onchange="selectRadio('2022-3.4.1','0','self','4','管床数＜2张，或未安排，不得分')"/>
					&nbsp;<label>管床数＜2张，或未安排，不得分</label><br>
				</c:if>
				<c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input type="radio" name="2022-3.4.1" value="4" onchange="selectRadio('2022-3.4.1','4','expert','4','')"/>
					&nbsp;<label>管床数达到要求，得满分</label><br>
					<input type="radio" name="2022-3.4.1" value="2" onchange="selectRadio('2022-3.4.1','2','expert','4','管床数≥3张，得2分')"/>
					&nbsp;<label>管床数≥3张，得2分</label><br>
					<input type="radio" name="2022-3.4.1" value="1" onchange="selectRadio('2022-3.4.1','1','expert','4','管床数≥2，得1分')"/>
					&nbsp;<label>管床数≥2，得1分</label><br>
					<input type="radio" name="2022-3.4.1" value="0" onchange="selectRadio('2022-3.4.1','0','expert','4','管床数＜2张，或未安排，不得分')"/>
					&nbsp;<label>管床数＜2张，或未安排，不得分</label><br>
				</c:if>
			 <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea  onchange="saveSpeReason(this);" itemId="2022-3.4.1" itemName="管理病床数" name="reason"
							   placeholder="请填写扣分原因" style="width: 98%"
							   class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.4.1']}</textarea>
				</c:if>
			</td>
		</tr>
		<tr style="height:48px;">
			<td>3.4.2门急诊工作量</td>
			<td>
				门诊：日诊治数≥20人次<br>
				急诊：日急诊数≥10人次
			</td>
			<td>
				门急诊工作量达到要求，得满分<br>
				门急诊量≥规定数的80%，得2分<br>
				门急诊量≥规定数的60%，得1分<br>
				门急诊量＜规定数60%，或未安排，不得分
			</td>
			<td style="text-align: center;">3</td>
			<td name="file" itemId="2022-3.4.2" itemName="门急诊工作量"></td>
			<td><input onchange="saveScoreOnlyIntenger(this,3);" itemId="2022-3.4.2" itemName="门急诊工作量"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,3);" itemId="2022-3.4.2" itemName="门急诊工作量"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input type="radio" name="2022-3.4.2" value="3" onchange="selectRadio('2022-3.4.2','3','self','3','')"/>
					&nbsp;<label>门急诊工作量达到要求，得满分</label><br>
					<input type="radio" name="2022-3.4.2" value="2" onchange="selectRadio('2022-3.4.2','2','self','3','门急诊量≥规定数的80%，得2分')"/>
					&nbsp;<label>门急诊量≥规定数的80%，得2分</label><br>
					<input type="radio" name="2022-3.4.2" value="1" onchange="selectRadio('2022-3.4.2','1','self','3','门急诊量≥规定数的60%，得1分')"/>
					&nbsp;<label>门急诊量≥规定数的60%，得1分</label><br>
					<input type="radio" name="2022-3.4.2" value="0" onchange="selectRadio('2022-3.4.2','0','self','3','门急诊量＜规定数60%或未安排，不得分')"/>
					&nbsp;<label>门急诊量＜规定数60%或未安排，不得分</label><br>
				</c:if>
				<c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input type="radio" name="2022-3.4.2" value="3" onchange="selectRadio('2022-3.4.2','3','expert','3','')"/>
					&nbsp;<label>门急诊工作量达到要求，得满分</label><br>
					<input type="radio" name="2022-3.4.2" value="2" onchange="selectRadio('2022-3.4.2','2','expert','3','门急诊量≥规定数的80%，得2分')"/>
					&nbsp;<label>门急诊量≥规定数的80%，得2分</label><br>
					<input type="radio" name="2022-3.4.2" value="1" onchange="selectRadio('2022-3.4.2','1','expert','3','门急诊量≥规定数的60%，得1分')"/>
					&nbsp;<label>门急诊量≥规定数的60%，得1分</label><br>
					<input type="radio" name="2022-3.4.2" value="0" onchange="selectRadio('2022-3.4.2','0','expert','3','门急诊量＜规定数60%或未安排，不得分')"/>
					&nbsp;<label>门急诊量＜规定数60%或未安排，不得分</label><br>
				</c:if>
			 <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea  onchange="saveSpeReason(this);" itemId="2022-3.4.2" itemName="门急诊工作量" name="reason"
							   placeholder="请填写扣分原因" style="width: 98%"
							   class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.4.2']}</textarea>
				</c:if>
			</td>
		</tr>
		<tr style="height:48px;">
			<td style="height:96px;" rowspan="6">4.质量控制<br>（35分）</td>
			<td rowspan="3">4.1带教医师教学质量</td>
			<td>4.1.1查房质量★</td>
			<td>主任或带教医师组织规范的教学查房，悉心指导培训对象</td>
			<td>随机抽查1～2名带教医师教学查房</td>
			<td>
				教学查房评分表见
					<a href="javascript:void(0)" onclick="Schedule('evaluationInfo_1000_02','附表2',650);">附表2</a>
				<br>
				≥90分得满分，≥80分得3分，≥70分得2分<br>
				≥60分得1分，＜60分不得分
			</td>
			<td style="text-align: center;">4</td>
			<td name="file" itemId="2022-4.1.1" itemName="查房质量"></td>
			<td><input id="fubiao2" onchange="saveScore(this,4);" itemId="2022-4.1.1" itemName="查房质量"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input id="fubiao2Expert" onchange="saveScore4Expert(this,4);" itemId="2022-4.1.1" itemName="查房质量"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<textarea  onchange="saveSpeReason(this);" itemId="2022-4.1.1" itemName="查房质量" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-4.1.1']}</textarea>
			</td>
		</tr>
		<tr style="height:48px;">
			<td>4.1.2技能操作和手术安排情况★</td>
			<td>
				每个轮转科室均按照《住院医师规范化培训内容与标准（试行）》本专业培训细则要求执行，
				为每名培训对象安排并完成规定的技能操作和手术
			</td>
			<td>
				随机抽查5～10名培训对象技能操作和手术记录，了解实际情况
			</td>
			<td>
				完成率≥90%，得满分<br>
				完成率≥80%，得4分<br>
				完成率＜80%，不得分
			</td>
			<td style="text-align: center;">8</td>
			<td name="file" itemId="2022-4.1.2" itemName="技能操作和手术安排情况"></td>
			<td><input onchange="saveScoreOnlyIntenger(this,8);" itemId="2022-4.1.2" itemName="技能操作和手术安排情况"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,8);" itemId="2022-4.1.2" itemName="技能操作和手术安排情况"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input type="radio" name="2022-4.1.2" value="8" onchange="selectRadio('2022-4.1.2','8','self','8','')"/>
					&nbsp;<label>完成率≥90%，得满分</label><br>
					<input type="radio" name="2022-4.1.2" value="4" onchange="selectRadio('2022-4.1.2','4','self','8','完成率≥80%，得4分')"/>
					&nbsp;<label>完成率≥80%，得4分</label><br>
					<input type="radio" name="2022-4.1.2" value="0" onchange="selectRadio('2022-4.1.2','0','self','8','完成率＜80%，不得分')"/>
					&nbsp;<label>完成率＜80%，不得分</label><br>
				</c:if>
				<c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input type="radio" name="2022-4.1.2" value="8" onchange="selectRadio('2022-4.1.2','8','expert','8','')"/>
					&nbsp;<label>完成率≥90%，得满分</label><br>
					<input type="radio" name="2022-4.1.2" value="4" onchange="selectRadio('2022-4.1.2','4','expert','8','完成率≥80%，得4分')"/>
					&nbsp;<label>完成率≥80%，得4分</label><br>
					<input type="radio" name="2022-4.1.2" value="0" onchange="selectRadio('2022-4.1.2','0','expert','8','完成率＜80%，不得分')"/>
					&nbsp;<label>完成率＜80%，不得分</label><br>
				</c:if>
			 <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea  onchange="saveSpeReason(this);" itemId="2022-4.1.2" itemName="技能操作和手术安排情况" name="reason"
							   placeholder="请填写扣分原因" style="width: 98%"
							   class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-4.1.2']}</textarea>
				</c:if>
			</td>
		</tr>
		<tr style="height:48px;">
			<td>4.1.3技能操作和手术带教情况★</td>
			<td>
				带教医师协助并指导培训对象完成技能操作或手术，带教严格规范
			</td>
			<td>
				随机抽查1～2名带教医师指导培训对象（二年级以上）进行技能操作或手术
			</td>
			<td>
				1.培训对象操作前是否与患者交流、沟通情况 1分<br>
				2.培训对象操作中存在问题及时进行指导  1分<br>
				3.培训对象操作结束后是否进行提问   1分<br>
				4.对培训对象的操作的总体评价（优、缺点点评） 2分
			</td>
			<td style="text-align: center;">5</td>
			<td name="file" itemId="2022-4.1.3" itemName="技能操作和手术带教情况"></td>
			<td><input onchange="saveScore(this,5);" itemId="2022-4.1.3" itemName="技能操作和手术带教情况"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,5);" itemId="2022-4.1.3" itemName="技能操作和手术带教情况"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<textarea  onchange="saveSpeReason(this);" itemId="2022-4.1.3" itemName="技能操作和手术带教情况" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-4.1.3']}</textarea>
			</td>
		</tr>
		<tr style="height:48px;">
			<td rowspan="3">4.2培训对象学习效果</td>
			<td style="height: 48px;">4.2.1病历书写★</td>
			<td>培训对象病历书写规范</td>
			<td>随机抽查1～2名培训对象运行病历，结合病历提问题</td>
			<td>
				病历书写评分表见
					<a href="javascript:void(0)" onclick="Schedule('evaluationInfo_1000_03','附表3',650);">附表3</a>
				<br>
				≥90分得满分，≥80分得3分，≥70分得2分<br>
				≥60分得1分，＜60分不得分
			</td>
			<td style="text-align: center;">4</td>
			<td name="file" itemId="2022-4.2.1" itemName="病历书写"></td>
			<td><input id="fubiao3" onchange="saveScore(this,4);" itemId="2022-4.2.1" itemName="病历书写"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input id="fubiao3Expert" onchange="saveScore4Expert(this,4);" itemId="2022-4.2.1" itemName="病历书写"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<textarea  onchange="saveSpeReason(this);" itemId="2022-4.2.1" itemName="病历书写" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-4.2.1']}</textarea>
			</td>
		</tr>
		<tr style="height:48px;">
			<td style="height: 48px;">4.2.2技能操作和手术★</td>
			<td>培训对象技能操作或手术情况</td>
			<td>
				随机抽查1～2名二年级以上培训对象进行技能操作或常见手术操作（术者、助手），查看其掌握情况
			</td>
			<td>
				技能操作评分表见
					<a href="javascript:void(0)" onclick="Schedule('evaluationInfo_1000_04','附表4',650);">附表4</a>
				<br>
				≥90分得满分，≥80分得5分，≥70分得3分<br>
				≥60分得1分，＜60分不得分
			</td>
			<td style="text-align: center;">8</td>
			<td name="file" itemId="2022-4.2.2" itemName="技能操作和手术"></td>
			<td><input id="fubiao4" onchange="saveScore(this,8);" itemId="2022-4.2.2" itemName="技能操作和手术"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input id="fubiao4Expert" onchange="saveScore4Expert(this,8);" itemId="2022-4.2.2" itemName="技能操作和手术"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<textarea  onchange="saveSpeReason(this);" itemId="2022-4.2.2" itemName="技能操作和手术" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-4.2.2']}</textarea>
			</td>
		</tr>
		<tr style="height:48px;">
			<td style="height: 48px;">4.2.3完成培训内容与要求★</td>
			<td>
				按照本专业《住院医师规范化培训内容与标准（试行）》细则，核实培训内容的完成情况
			</td>
			<td>
				随机抽查访谈本院、委培、社会招收培训对象各2～3名，查看轮转登记手册、出科考核等原始资料
			</td>
			<td>
				完成率≥90%，得满分<br>
				完成率≥85%，得4分<br>
				完成率≥80%，得2分<br>
				完成率＜80%，不得分
			</td>
			<td style="text-align: center;">6</td>
			<td name="file" itemId="2022-4.2.3" itemName="完成培训内容与要求"></td>
			<td><input onchange="saveScoreOnlyIntenger(this,6);" itemId="2022-4.2.3" itemName="完成培训内容与要求"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,6);" itemId="2022-4.2.3" itemName="完成培训内容与要求"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input type="radio" name="2022-4.2.3" value="6" onchange="selectRadio('2022-4.2.3','6','self','6','')"/>
					&nbsp;<label>完成率≥90%，得满分</label><br>
					<input type="radio" name="2022-4.2.3" value="4" onchange="selectRadio('2022-4.2.3','4','self','6','完成率≥85%，得4分')"/>
					&nbsp;<label>完成率≥85%，得4分</label><br>
					<input type="radio" name="2022-4.2.3" value="2" onchange="selectRadio('2022-4.2.3','2','self','6','完成率≥80%，得2分')"/>
					&nbsp;<label>完成率≥80%，得2分</label><br>
					<input type="radio" name="2022-4.2.3" value="0" onchange="selectRadio('2022-4.2.3','0','self','6','完成率＜80%，不得分')"/>
					&nbsp;<label>完成率＜80%，不得分</label><br>
				</c:if>
				<c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input type="radio" name="2022-4.2.3" value="6" onchange="selectRadio('2022-4.2.3','6','expert','6','')"/>
					&nbsp;<label>完成率≥90%，得满分</label><br>
					<input type="radio" name="2022-4.2.3" value="4" onchange="selectRadio('2022-4.2.3','4','expert','6','完成率≥85%，得4分')"/>
					&nbsp;<label>完成率≥85%，得4分</label><br>
					<input type="radio" name="2022-4.2.3" value="2" onchange="selectRadio('2022-4.2.3','2','expert','6','完成率≥80%，得2分')"/>
					&nbsp;<label>完成率≥80%，得2分</label><br>
					<input type="radio" name="2022-4.2.3" value="0" onchange="selectRadio('2022-4.2.3','0','expert','6','完成率＜80%，不得分')"/>
					&nbsp;<label>完成率＜80%，不得分</label><br>
				</c:if>
			 <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
					<textarea  onchange="saveSpeReason(this);" itemId="2022-4.2.3" itemName="完成培训内容与要求" name="reason"
							   placeholder="请填写扣分原因" style="width: 98%"
							   class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-4.2.3']}</textarea>
				</c:if>
			</td>
		</tr>
		<tr style="height:40px;">
			<td colspan="6" style="text-align: right;">合计</td>
			<td style="text-align: center;">100</td>
			<td>&mdash;&mdash;&mdash;</td>
			<td style="text-align: center;"><span id="selfTotalled"></span></td>
			<td style="text-align: center;"><span id="expertTotalled"></span></td>
			<td></td>
		</tr>
		<tr style="height:150px;">
			<td rowspan="2" colspan="3">存在问题请详细填写：</td>
			<td rowspan="2" colspan="8">
				<textarea class="input" type="text" id="speContentInput" name="speContentInput" style="width: 96%;height: 150px;text-indent: 3px;" onchange="saveSpeContent(this,'${orgFlow}')">${subjectUser.speContent}</textarea>
			</td>
		</tr>
		<tr style="height:16px;">
		</tr>
		<tr>
			<td colspan="11" style="text-align: left">
				备注：<br>
				&#12288;&#12288;1.一级指标4项；二级指标10项；三级指标37项，核心指标14项，计65分，一般指标22项，共32分，共100分。<br>
				&#12288;&#12288;2.指标中所有规章制度，专指住院医师规范化培训相关制度。<br>
				&#12288;&#12288;3.随机抽查对象优先选择委托培训对象和面向社会招收的培训对象，如果没有，可考虑本基地培训对象。<br>
				&#12288;&#12288;4.现场评估时详细填写存在的问题和扣分原因。<br>
				<font color="red">*表格数据可能和自评表中存在差距,以实际为准</font>
			</td>
		</tr>
		<c:if test="${GlobalConstant.USER_LIST_LOCAL ne roleFlag}">
			<tr style="height: 30px">
				<td colspan="2" style="text-align:left">
					&#12288;&#12288;专家签字：
				</td>
				<td colspan="4">
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
		</tbody>

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