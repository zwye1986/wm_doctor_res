<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="font" value="true"/>
		<jsp:param name="jquery_validation" value="true"/>
	</jsp:include>
	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
					<h2 style="font-size:150%">住院医师规范化培训评估指标——超声医学科专业基地</h2>
				</th>
			</tr>
			<tr height="28" >
				<th style="text-align:left;padding-left: 4px;"  height="28"  colspan="6">培训基地（医院）名称：${orgName}</th>
				<th style="text-align:left;padding-left: 4px;"  height="28"  colspan="5">所属省（区、市）：${orgCityName}</th>
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
				<h2 style="font-size:150%">住院医师规范化培训评估指标——超声医学科专业基地</h2>
			</th>
		</tr>
		<tr height="28" >
			<th style="text-align:left;padding-left: 4px;"  height="28"  colspan="6">培训基地（医院）名称：${orgName}</th>
			<th style="text-align:left;padding-left: 4px;"  height="28"  colspan="5">所属省（区、市）：${orgCityName}</th>
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
			<td style="height: 438px;" rowspan="11">1.基本条件<br>（20分）</td>
			<td rowspan="7">1.1医院和超<br>声科条件</td>
			<td>1.1.1医院日门诊量</td>
			<td width="313px">
				≥2000人次
			</td>
			<td width="193px" rowspan="3">检查相关统计报表复印件，需加盖医院公章</td>
			<td width="313px">
				符合标准，得1分
				不达标准，不得分
			</td>
			<td style="text-align: center;width:74px;">1</td>
			<td name="file" itemId="2022-1.1.1" itemName="医院日门诊量" width="180px"></td>
			<td width="74px"><input onchange="saveScoreOnlyIntenger(this,1);" itemId="2022-1.1.1" value="${ownerScoreMap['2022-1.1.1']}" itemName="医院日门诊量"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td width="74px"><input onchange="saveScore4ExpertOnlyIntenger(this,1);" itemId="2022-1.1.1" itemName="医院日门诊量"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input type="radio"  name="2022-1.1.1" value="1" onchange="selectRadio('2022-1.1.1','1','self','1','')"/>
					&nbsp;<label >符合标准，得满分</label><br>
					<input   type="radio"  name="2022-1.1.1" value="0" onchange="selectRadio('2022-1.1.1','0','self','1','不达标准，不得分')"/>
					&nbsp;<label >不达标准，不得分</label>
				</c:if>
				  <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input type="radio"  name="2022-1.1.1" value="1" onchange="selectRadio('2022-1.1.1','1','expert','1','')"/>
					&nbsp;<label >符合标准，得满分</label><br>
					<input type="radio"  name="2022-1.1.1" value="0" onchange="selectRadio('2022-1.1.1','0','expert','1','不达标准，不得分')"/>
					&nbsp;<label >不达标准，不得分</label>
				</c:if>
				<c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                    ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
				<textarea onchange="saveSpeReason(this);"  itemId="2022-1.1.1" itemName="医院日门诊量" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-1.1.1']}</textarea>
				</c:if>
			</td>
		</tr>
		<tr style="height:53px;">
			<td style="height:53px;">1.1.2医院日急诊量</td>
			<td>≥100人次</td>
			<td>符合标准，得1分
				不达标准，不得分
			</td>
			<td style="text-align: center;">1</td>
			<td name="file" itemId="2022-1.1.2" itemName="医院日急诊量" ></td>
			<td><input onchange="saveScoreOnlyIntenger(this,1);" itemId="2022-1.1.2" itemName="医院日急诊量"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,1);" itemId="2022-1.1.2" itemName="医院日急诊量"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input   type="radio"   name="2022-1.1.2" value="1" onchange="selectRadio('2022-1.1.2','1','self','1','')"/>
					&nbsp;<label >符合标准，得1分</label><br>
					<input  type="radio"  name="2022-1.1.2" value="0" onchange="selectRadio('2022-1.1.2','0','self','1','不达标准，不得分')"/>
					&nbsp;<label >不达标准，不得分</label>
				</c:if>
				  <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input   type="radio"  name="2022-1.1.2" value="1" onchange="selectRadio('2022-1.1.2','1','expert','1','')"/>
					&nbsp;<label >符合标准，得1分</label><br>
					<input   type="radio"   name="2022-1.1.2" value="0" onchange="selectRadio('2022-1.1.2','0','expert','1','不达标准，不得分')"/>
					&nbsp;<label >不达标准，不得分</label>
				</c:if>
				<c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                    ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
				<textarea  onchange="saveSpeReason(this);" itemId="2022-1.1.2" itemName="医院日急诊量" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-1.1.2']}</textarea>
				</c:if>
			</td>
		</tr>
		<tr style="height:53px;">
			<td style="height:53px;">1.1.3科室日检查例数</td>
			<td>腹部≥70人次，心脏≥50人次，妇产≥60人次，血管≥30人次，浅表器官≥40人次，介入≥2人次（日均），其他（床旁、急症、胸部、术中、院内）≥50人次  </td>
			<td>符合标准，得2分
				不达标准，不得分</td>
			<td style="text-align: center;">2</td>
			<td name="file" itemId="2022-1.1.3" itemName="科室日检查例数" ></td>
			<td><input onchange="saveScoreOnlyIntenger(this,2);" itemId="2022-1.1.3" itemName="科室日检查例数"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,2);" itemId="2022-1.1.3" itemName="科室日检查例数"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input   type="radio" name="2022-1.1.3" value="2" onchange="selectRadio('2022-1.1.3','2','self','2','')"/>
					&nbsp;<label >符合标准，得2分</label><br>
					<input   type="radio"   name="2022-1.1.3" value="0" onchange="selectRadio('2022-1.1.3','0','self','2','不达标准，不得分')"/>
					&nbsp;<label >不达标准，不得分</label>
				</c:if>
				  <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input   type="radio"  name="2022-1.1.3" value="2" onchange="selectRadio('2022-1.1.3','2','expert','2','')"/>
					&nbsp;<label>符合标准，得2分</label><br>
					<input  type="radio"  name="2022-1.1.3" value="0" onchange="selectRadio('2022-1.1.3','0','expert','2','不达标准，不得分')"/>
					&nbsp;<label >不达标准，不得分</label>
				</c:if>
				<c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                    ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
				<textarea onchange="saveSpeReason(this);" itemId="2022-1.1.3" itemName="科室日检查例数" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-1.1.3']}</textarea>
				</c:if>
			</td>
		</tr>
		<tr style="height:53px;">
			<td style="height:53px;">1.1.4超声科亚专业设置★</td>
			<td>腹部、浅表器官、血管、妇产、心脏、介入等6个超声亚专业
			</td>
			<td>1.查看PACS和/或His系统或其他登记系统
				2.实地检查
				3.访谈学员（回避制）</td>
			<td>6个亚专业，得满分 <br>
				5个亚专业，得2分 <br>
				4个亚专业，得1分 <br>
				＜4个亚专业，不得分</td>
			<td style="text-align: center;">5</td>
			<td name="file" itemId="2022-1.1.4" itemName="超声科亚专业设置" ></td>
			<td><input onchange="saveScoreOnlyIntenger(this,5);" itemId="2022-1.1.4" itemName="超声科亚专业设置"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,5);" itemId="2022-1.1.4" itemName="超声科亚专业设置"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
                <c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
                    <input type="radio"  name="2022-1.1.4" value="5" onchange="selectRadio('2022-1.1.4','5','self','5','')"/>
                    &nbsp;<label >6个亚专业，得满分</label><br>
                    <input  type="radio"  name="2022-1.1.4" value="2" onchange="selectRadio('2022-1.1.4','2','self','5','5个亚专业，得2分')"/>
                    &nbsp;<label >5个亚专业，得2分</label><br>
					<input  type="radio"  name="2022-1.1.4" value="1" onchange="selectRadio('2022-1.1.4','1','self','5','4个亚专业，得1分')"/>
					&nbsp;<label >4个亚专业，得1分</label><br>
					<input  type="radio"  name="2022-1.1.4" value="0" onchange="selectRadio('2022-1.1.4','0','self','5','＜4个亚专业，不得分')"/>
					&nbsp;<label >＜4个亚专业，不得分</label>
                </c:if>
                  <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
                    <input   type="radio"  name="2022-1.1.4" value="5" onchange="selectRadio('2022-1.1.4','5','expert','5','')"/>
                    &nbsp;<label>6个亚专业，得满分</label><br>
                    <input type="radio"   name="2022-1.1.4" value="2" onchange="selectRadio('2022-1.1.4','2','expert','5','5个亚专业，得2分')"/>
                    &nbsp;<label>5个亚专业，得2分</label><br>
					<input type="radio"   name="2022-1.1.4" value="1" onchange="selectRadio('2022-1.1.4','1','expert','5','4个亚专业，得1分')"/>
					&nbsp;<label>4个亚专业，得1分</label><br>
					<input type="radio"   name="2022-1.1.4" value="0" onchange="selectRadio('2022-1.1.4','0','expert','5','＜4个亚专业，不得分')"/>
					&nbsp;<label>＜4个亚专业，不得分</label>
                </c:if>
                <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                    ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
				<textarea onchange="saveSpeReason(this);"  itemId="2022-1.1.4" itemName="超声科亚专业设置" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-1.1.4']}</textarea>
				</c:if>
			</td>
		</tr>
		<tr style="height:53px;">
			<td>1.1.5疾病种类及数量★</td>
			<td rowspan="2">
				符合《住院医师规范化培训基地认定标准（试行）》和《住院医师规范化培训内容与标准（试行）》中有关超声专业细则的要求（详见
					<a href="javascript:void(0)" onclick="Schedule('evaluationInfo_2300_01','附表1');">附表1</a>
				或2014年版两细则）
			</td>
			<td>核对上1年度超声科各亚专业收治疾病种类和数量统计报表（以PACS和His为准） </td>
			<td>
				符合要求（含协同单位），得满分
				疾病种类及数量≥规定数的90%，得3分
				疾病种类及数量≥规定数的85%，得1分
				疾病种类及数量＜规定数的85%，不得分
			</td>
			<td style="text-align: center;">3</td>
			<td name="file" itemId="2022-1.1.5" itemName="疾病种类及数量"></td>
			<td><input id="fubiao1s1" onchange="saveScoreOnlyIntenger(this,3);" itemId="2022-1.1.5" itemName="疾病种类及数量"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input id="fubiao1e1" onchange="saveScore4ExpertOnlyIntenger(this,3);" itemId="2022-1.1.5" itemName="疾病种类及数量"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<textarea onchange="saveSpeReason(this);"  itemId="2022-1.1.5" itemName="疾病种类及数量" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-1.1.5']}</textarea>
			</td>
		</tr>
		<tr style="height:53px;">
			<td>1.1.6技能（上机）操作种类及数量★</td>
			<td>核对上1年度超声科各亚专业技能（上机）操作种类和数量的统计报表（以PACS和His为准） </td>
			<td>
				符合要求（含协同单位），得满分
				技能（上机）操作种类及数量≥规定数的90%，得3分
				技能（上机）操作种类及数量≥规定数的85%，得1分
				技能（上机）操作种类及数量＜规定数85%，不得分
			</td>
			<td style="text-align: center;">3</td>
			<td name="file" itemId="2022-1.1.6" itemName="技能（上机）操作种类及数量"></td>
			<td><input id="fubiao1s2" onchange="saveScoreOnlyIntenger(this,3);" itemId="2022-1.1.6" itemName="技能（上机）操作种类及数量"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input id="fubiao1e2" onchange="saveScore4ExpertOnlyIntenger(this,3);" itemId="2022-1.1.6" itemName="技能（上机）操作种类及数量"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<textarea  onchange="saveSpeReason(this);" itemId="2022-1.1.6" itemName="技能（上机）操作种类及数量" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-1.1.6']}</textarea>
			</td>
		</tr>
		<tr style="height:53px;">
			<td>1.1.7专业基地设备</td>
			<td>彩色多普勒超声诊断仪≥8台</td>
			<td>检查设备清单复印件，需加盖医院公章，实地考查</td>
			<td>
				符合标准，得1分；不达标准，不得分
			</td>
			<td style="text-align: center;">1</td>
			<td name="file" itemId="2022-1.1.7" itemName="专业基地设备"></td>
			<td><input onchange="saveScoreOnlyIntenger(this,1);" itemId="2022-1.1.7" itemName="专业基地设备"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,1);" itemId="2022-1.1.7" itemName="专业基地设备"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input  type="radio"  name="2022-1.1.7" value="1" onchange="selectRadio('2022-1.1.7','1','self','1','')"/>
					&nbsp;<label >符合标准，得1分</label><br>
					<input type="radio"  name="2022-1.1.7" value="0" onchange="selectRadio('2022-1.1.7','0','self','1','不达标准，不得分')"/>
					&nbsp;<label >不达标准，不得分</label>
				</c:if>
				  <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input   type="radio"  name="2022-1.1.7" value="1" onchange="selectRadio('2022-1.1.7','1','expert','1','')"/>
					&nbsp;<label>符合标准，得1分</label><br>
					<input type="radio"   name="2022-1.1.7" value="0" onchange="selectRadio('2022-1.1.7','0','expert','1','不达标准，不得分')"/>
					&nbsp;<label>不达标准，不得分</label>
				</c:if>
				<c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                    ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
				<textarea  onchange="saveSpeReason(this);" itemId="2022-1.1.7" itemName="专业基地设备" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-1.1.7']}</textarea>
				</c:if>
			</td>
		</tr>
		<tr style="height:53px;">
			<td rowspan="2">1.2协同医院</td>
			<td>1.2.1协同数</td>
			<td>≤3个（暂定）</td>
			<td rowspan="2">查看原始资料，核实相关信息（以PACS和His为准） </td>
			<td>
				满足要求，得1分（无协同单位的专业基地，此处不失分）
			</td>
			<td style="text-align: center;">1</td>
			<td name="file" itemId="2022-1.2.1" itemName="协同数"></td>
			<td><input onchange="saveScoreOnlyIntenger(this,1);" itemId="2022-1.2.1" itemName="协同数"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,1);" itemId="2022-1.2.1" itemName="协同数"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<textarea  onchange="saveSpeReason(this);" itemId="2022-1.2.1" itemName="协同数" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-1.2.1']}</textarea>
			</td>
		</tr>
		<tr style="height:53px;">
			<td>1.2.2轮转时间</td>
			<td>在协同亚专业（专科）轮转时间不超过3个月</td>
			<td>
				满足要求，得1分（无协同单位的专业基地，此处不失分）
			</td>
			<td style="text-align: center;">1</td>
			<td name="file" itemId="2022-1.2.2" itemName="轮转时间"></td>
			<td><input onchange="saveScoreOnlyIntenger(this,1);" itemId="2022-1.2.2" itemName="轮转时间"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,1);" itemId="2022-1.2.2" itemName="轮转时间"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<textarea  onchange="saveSpeReason(this);" itemId="2022-1.2.2" itemName="轮转时间" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-1.2.2']}</textarea>
			</td>
		</tr>
		<tr style="height:53px;">
			<td rowspan="2">1.3政策支持</td>
			<td>1.3.1执业地点变更</td>
			<td>医院管理部门协助办理医师执业地点变更手续</td>
			<td>查看原始材料，核实相关信息</td>
			<td>
				满足要求，得1分；不达标准，不得分
			</td>
			<td style="text-align: center;">1</td>
			<td name="file" itemId="2022-1.3.1" itemName="执业地点变更"></td>
			<td><input onchange="saveScoreOnlyIntenger(this,1);" itemId="2022-1.3.1" itemName="执业地点变更"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,1);" itemId="2022-1.3.1" itemName="执业地点变更"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input  type="radio"  name="2022-1.3.1" value="1" onchange="selectRadio('2022-1.3.1','1','self','1','')"/>
					&nbsp;<label >满足要求，得1分</label><br>
					<input type="radio"  name="2022-1.3.1" value="0" onchange="selectRadio('2022-1.3.1','0','self','1','不达标准，不得分')"/>
					&nbsp;<label >不达标准，不得分</label>
				</c:if>
				  <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input   type="radio"  name="2022-1.3.1" value="1" onchange="selectRadio('2022-1.3.1','1','expert','1','')"/>
					&nbsp;<label>满足要求，得1分</label><br>
					<input type="radio"   name="2022-1.3.1" value="0" onchange="selectRadio('2022-1.3.1','0','expert','1','不达标准，不得分')"/>
					&nbsp;<label>不达标准，不得分</label>
				</c:if>
				<c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                    ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
				<textarea onchange="saveSpeReason(this);"  itemId="2022-1.3.1" itemName="执业地点变更" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-1.3.1']}</textarea>
				</c:if>
			</td>
		</tr>
		<tr style="height:53px;">
			<td>1.3.2“同工同酬”★</td>
			<td>符合《住院医师规范化培训管理办法（试行）》等相关文件规定，制定规章制度，落实住培薪酬待遇</td>
			<td>查看原始材料，核实相关信息，访谈（回避制）</td>
			<td>
				满足要求，得1分；不达标准，不得分
			</td>
			<td style="text-align: center;">1</td>
			<td name="file" itemId="2022-1.3.2" itemName="同工同酬"></td>
			<td><input onchange="saveScoreOnlyIntenger(this,1);" itemId="2022-1.3.2" itemName="同工同酬"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,1);" itemId="2022-1.3.2" itemName="同工同酬"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input  type="radio"  name="2022-1.3.2" value="1" onchange="selectRadio('2022-1.3.2','1','self','1','')"/>
					&nbsp;<label >满足要求，得1分</label><br>
					<input type="radio"  name="2022-1.3.2" value="0" onchange="selectRadio('2022-1.3.2','0','self','1','不达标准，不得分')"/>
					&nbsp;<label >不达标准，不得分</label>
				</c:if>
				  <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input   type="radio"  name="2022-1.3.2" value="1" onchange="selectRadio('2022-1.3.2','1','expert','1','')"/>
					&nbsp;<label>满足要求，得1分</label><br>
					<input type="radio"   name="2022-1.3.2" value="0" onchange="selectRadio('2022-1.3.2','0','expert','1','不达标准，不得分')"/>
					&nbsp;<label>不达标准，不得分</label>
				</c:if>
				<c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                    ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
				<textarea onchange="saveSpeReason(this);"  itemId="2022-1.3.2" itemName="同工同酬" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-1.3.2']}</textarea>
				</c:if>
			</td>
		</tr>
		<tr style="height:53px;">
			<td style="height: 438px;" rowspan="7">2.师资条件<br>（15分）</td>
			<td rowspan="4">2.1师资情况</td>
			<td>2.1.1带教医师与学员比例★</td>
			<td>
				带教老师与学员（包括并轨研究生）比例为1:1～2
			</td>
			<td>查看原始资料，访谈学员</td>
			<td>
				达标准，得2分；不达标准，不得分
			</td>
			<td style="text-align: center;">2</td>
			<td name="file" itemId="2022-2.1.1" itemName="带教医师与学员比例"></td>
			<td><input onchange="saveScoreOnlyIntenger(this,2);" itemId="2022-2.1.1" itemName="带教医师与学员比例"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,2);" itemId="2022-2.1.1" itemName="带教医师与学员比例"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input  type="radio"  name="2022-2.1.1" value="2" onchange="selectRadio('2022-2.1.1','2','self','2','')"/>
					&nbsp;<label >达到标准，得满分</label><br>
					<input type="radio"  name="2022-2.1.1" value="0" onchange="selectRadio('2022-2.1.1','0','self','2','不达标准，不得分')"/>
					&nbsp;<label >不达标准，不得分</label>
				</c:if>
				  <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input   type="radio"  name="2022-2.1.1" value="2" onchange="selectRadio('2022-2.1.1','2','expert','2','')"/>
					&nbsp;<label>达到标准，得满分</label><br>
					<input type="radio"   name="2022-2.1.1" value="0" onchange="selectRadio('2022-2.1.1','0','expert','2','不达标准，不得分')"/>
					&nbsp;<label>不达标准，不得分</label>
				</c:if>
				<c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                    ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
				<textarea onchange="saveSpeReason(this);" itemId="2022-2.1.1" itemName="带教医师与学员比例" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-2.1.1']}</textarea>
				</c:if>
			</td>
		</tr>
		<tr style="height:53px;">
			<td>2.1.2带教医师条件★</td>
			<td>
				均须有“医学影像与放射治疗学”的执业资格，包括主任和教学副主任等；医学本科及以上学历,超声专业主治医师专业技术职称3年（含）以上，从事本专业临床诊疗工作5年（含）以上；介入超声带教医师应经过正规培训，并从事介入超声工作3年（含）以上
			</td>
			<td rowspan="3">查看人事部门提供的师资状，统计表，包括姓名、年龄、毕业时间、毕业学校、学历、学位、专业技术职称及任职时间、工作年限，需加盖人事部门公章</td>
			<td>
				其中1名带教医师不符合要求，不得分
			</td>
			<td style="text-align: center;">1</td>
			<td name="file" itemId="2022-2.1.2" itemName="带教医师条件"></td>
			<td><input onchange="saveScoreOnlyIntenger(this,1);" itemId="2022-2.1.2" itemName="带教医师条件"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,1);" itemId="2022-2.1.2" itemName="带教医师条件"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input  type="radio"  name="2022-2.1.2" value="1" onchange="selectRadio('2022-2.1.2','1','self','1','')"/>
					&nbsp;<label >符合标准，得满分</label><br>
					<input type="radio"  name="2022-2.1.2" value="0" onchange="selectRadio('2022-2.1.2','0','self','1','其中1名带教医师不符合要求，不得分')"/>
					&nbsp;<label >其中1名带教医师不符合要求，不得分</label>
				</c:if>
				  <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input   type="radio"  name="2022-2.1.2" value="1" onchange="selectRadio('2022-2.1.2','1','expert','1','')"/>
					&nbsp;<label>符合标准，得满分</label><br>
					<input type="radio"   name="2022-2.1.2" value="0" onchange="selectRadio('2022-2.1.2','0','expert','1','其中1名带教医师不符合要求，不得分')"/>
					&nbsp;<label>其中1名带教医师不符合要求，不得分</label>
				</c:if>
				<c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                    ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
				<textarea  onchange="saveSpeReason(this);" itemId="2022-2.1.2" itemName="带教医师条件" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-2.1.2']}</textarea>
				</c:if>
			</td>
		</tr>
		<tr style="height:53px;">
			<td>2.1.3带教医师组成</td>
			<td>
				各超声亚专业（组）有主任医师或副主任医师≥1人，主治医师≥2人
			</td>
			<td>1个亚专业不达标，不得分</td>
			<td style="text-align: center;">1</td>
			<td name="file" itemId="2022-2.1.3" itemName="带教医师组成"></td>
			<td><input onchange="saveScoreOnlyIntenger(this,1);" itemId="2022-2.1.3" itemName="带教医师组成"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,1);" itemId="2022-2.1.3" itemName="带教医师组成"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input  type="radio"  name="2022-2.1.3" value="1" onchange="selectRadio('2022-2.1.3','1','self','1','')"/>
					&nbsp;<label >符合标准，得满分</label><br>
					<input type="radio"  name="2022-2.1.3" value="0" onchange="selectRadio('2022-2.1.3','0','self','1','1个亚专业不达标，不得分')"/>
					&nbsp;<label >1个亚专业不达标，不得分</label>
				</c:if>
				  <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input   type="radio"  name="2022-2.1.3" value="1" onchange="selectRadio('2022-2.1.3','1','expert','1','')"/>
					&nbsp;<label>符合标准，得满分</label><br>
					<input type="radio"   name="2022-2.1.3" value="0" onchange="selectRadio('2022-2.1.3','0','expert','1','1个亚专业不达标，不得分')"/>
					&nbsp;<label>1个亚专业不达标，不得分</label>
				</c:if>
				<c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                    ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
				<textarea onchange="saveSpeReason(this);"  itemId="2022-2.1.3" itemName="带教医师组成" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-2.1.3']}</textarea>
				</c:if>
			</td>
		</tr>
		<tr style="height:53px;">
			<td>2.1.4专业基地主任/负责人条件★</td>
			<td>
				1.附属医院者，具有医学硕士研究生学历，主任医师专业技术职称；教学医院者，医学本科及以上学历，副主任医师以上专业技术职称；2.从事本专业医教研工作≥15年
			</td>
			<td>1项不符合条件，不得分</td>
			<td style="text-align: center;">2</td>
			<td name="file" itemId="2022-2.1.4" itemName="专业基地主任/负责人条件"></td>
			<td><input onchange="saveScoreOnlyIntenger(this,2);" itemId="2022-2.1.4" itemName="专业基地主任/负责人条件"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,2);" itemId="2022-2.1.4" itemName="专业基地主任/负责人条件"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input  type="radio"  name="2022-2.1.4" value="2" onchange="selectRadio('2022-2.1.4','2','self','2','')"/>
					&nbsp;<label >符合标准，得满分</label><br>
					<input type="radio"  name="2022-2.1.4" value="0" onchange="selectRadio('2022-2.1.4','0','self','2','1项不符合条件，不得分')"/>
					&nbsp;<label >1项不符合条件，不得分</label>
				</c:if>
				  <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input   type="radio"  name="2022-2.1.4" value="2" onchange="selectRadio('2022-2.1.4','2','expert','2','')"/>
					&nbsp;<label>符合标准，得满分</label><br>
					<input type="radio"   name="2022-2.1.4" value="0" onchange="selectRadio('2022-2.1.4','0','expert','2','1项不符合条件，不得分')"/>
					&nbsp;<label>1项不符合条件，不得分</label>
				</c:if>
				<c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                    ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
				<textarea  onchange="saveSpeReason(this);" itemId="2022-2.1.4" itemName="专业基地主任/负责人条件" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-2.1.4']}</textarea>
				</c:if>
			</td>
		</tr>
		<tr style="height:53px;">
			<td rowspan="3">2.2师资建设</td>
			<td>2.2.1师资培训★</td>
			<td>
				近一年，带教医师60%参加过省/直辖市级师资培训活动，20%参加国家级师资培训活动
			</td>
			<td>查看培训证书</td>
			<td>
				两级别培训均满足，得满分；仅有一项，得2分；都不满足，不得分
			</td>
			<td style="text-align: center;">4</td>
			<td name="file" itemId="2022-2.2.1" itemName="师资培训"></td>
			<td><input onchange="saveScoreOnlyIntenger(this,4);" itemId="2022-2.2.1" itemName="师资培训"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,4);" itemId="2022-2.2.1" itemName="师资培训"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input type="radio"  name="2022-2.2.1" value="4" onchange="selectRadio('2022-2.2.1','4','self','4','')"/>
					&nbsp;<label >两级别培训均满足，得满分</label><br>
					<input   type="radio"  name="2022-2.2.1" value="2" onchange="selectRadio('2022-2.2.1','2','self','4','仅有一项，得2分')"/>
					&nbsp;<label >仅有一项，得2分</label><br>
					<input   type="radio"  name="2022-2.2.1" value="0" onchange="selectRadio('2022-2.2.1','0','self','4','都不满足，不得分')"/>
					&nbsp;<label >都不满足，不得分</label>
				</c:if>
				  <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input type="radio"  name="2022-2.2.1" value="4" onchange="selectRadio('2022-2.2.1','4','expert','4','')"/>
					&nbsp;<label >两级别培训均满足，得满分</label><br>
					<input type="radio"  name="2022-2.2.1" value="2" onchange="selectRadio('2022-2.2.1','2','expert','4','仅有一项，得2分')"/>
					&nbsp;<label >仅有一项，得2分</label><br>
					<input type="radio"  name="2022-2.2.1" value="0" onchange="selectRadio('2022-2.2.1','0','expert','4','都不满足，不得分')"/>
					&nbsp;<label >都不满足，不得分</label>
				</c:if>
				<c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                    ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
				<textarea onchange="saveSpeReason(this);"  itemId="2022-2.2.1" itemName="师资培训" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-2.2.1']}</textarea>
				</c:if>
			</td>
		</tr>
		<tr style="height:53px;">
			<td>2.2.2师资评价</td>
			<td>
				每年度至少组织1次对带教医师教学工作进行评价
			</td>
			<td>查看原始资料（评价或鉴定表等），访谈带教医师和学员（回避制）</td>
			<td>
				原始记录详实，得满分；无记录，不得分
			</td>
			<td style="text-align: center;">1</td>
			<td name="file" itemId="2022-2.2.2" itemName="师资评价"></td>
			<td><input onchange="saveScoreOnlyIntenger(this,1);" itemId="2022-2.2.2" itemName="师资评价"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,1);" itemId="2022-2.2.2" itemName="师资评价"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input  type="radio"  name="2022-2.2.2" value="1" onchange="selectRadio('2022-2.2.2','1','self','1','')"/>
					&nbsp;<label >原始记录详实，得满分</label><br>
					<input  type="radio"  name="2022-2.2.2" value="0" onchange="selectRadio('2022-2.2.2','0','self','1','无记录，不得分')"/>
					<label >无记录，不得分</label><br>
				</c:if>
				  <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input   type="radio"  name="2022-2.2.2" value="1" onchange="selectRadio('2022-2.2.2','1','expert','1','')"/>
					&nbsp;<label>原始记录详实，得满分</label><br>
					<input   type="radio"  name="2022-2.2.2" value="0" onchange="selectRadio('2022-2.2.2','0','expert','1','无记录，不得分')"/>
					&nbsp;<label>无记录，不得分</label><br>
				</c:if>
				<c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                    ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
				<textarea  onchange="saveSpeReason(this);" itemId="2022-2.2.2" itemName="师资评价" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-2.2.2']}</textarea>
				</c:if>
			</td>
		</tr>
		<tr style="height:53px;">
			<td>2.2.3激励制度★</td>
			<td>
				建立带教医师激励机制，将教学工作与绩效考评、奖金、评优等挂钩
			</td>
			<td>查看相关材料，访谈带教医师（回避制）</td>
			<td>
				有激励机制，与奖金、评优挂钩，得满分；无激励机制，不得分
			</td>
			<td style="text-align: center;">4</td>
			<td name="file" itemId="2022-2.2.3" itemName="激励制度"></td>
			<td><input onchange="saveScoreOnlyIntenger(this,4);" itemId="2022-2.2.3" itemName="激励制度"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,4);" itemId="2022-2.2.3" itemName="激励制度"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input  type="radio"  name="2022-2.2.3" value="4" onchange="selectRadio('2022-2.2.3','4','self','4','')"/>
					&nbsp;<label >有激励机制，与奖金、评优挂钩，得满分</label><br>
					<input type="radio"  name="2022-2.2.3" value="0" onchange="selectRadio('2022-2.2.3','0','self','4','无激励机制，不得分')"/>
					&nbsp;<label >无激励机制，不得分</label>
				</c:if>
				  <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input   type="radio"  name="2022-2.2.3" value="4" onchange="selectRadio('2022-2.2.3','4','expert','4','')"/>
					&nbsp;<label>有激励机制，与奖金、评优挂钩，得满分</label><br>
					<input type="radio"   name="2022-2.2.3" value="0" onchange="selectRadio('2022-2.2.3','0','expert','4','无激励机制，不得分')"/>
					&nbsp;<label>无激励机制，不得分</label>
				</c:if>
				<c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                    ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
				<textarea onchange="saveSpeReason(this);"  itemId="2022-2.2.3" itemName="激励制度" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-2.2.3']}</textarea>
				</c:if>
			</td>
		</tr>

		<tr style="height:50px">
			<td style="height: 438px;" rowspan="11">3.过程管理<br>（30分）</td>
			<td rowspan="7">3.1培训制度与落实</td>
			<td>3.1.1主任职责</td>
			<td>
				实行专业基地负责人负责制，并切实落实
			</td>
			<td rowspan="2">查看相关文件与证明，访谈各类人员（回避制）</td>
			<td>
				职责明确，履职认真，得1分；无岗位职责或履职不认真，不得分
			</td>
			<td style="text-align: center;">1</td>
			<td name="file" itemId="2022-3.1.1" itemName="主任职责"></td>
			<td><input onchange="saveScoreOnlyIntenger(this,1);" itemId="2022-3.1.1" itemName="主任职责"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,1);" itemId="2022-3.1.1" itemName="主任职责"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input  type="radio"  name="2022-3.1.1" value="1" onchange="selectRadio('2022-3.1.1','1','self','1','')"/>
					&nbsp;<label >职责明确，履职认真，得1分</label><br>
					<input type="radio"  name="2022-3.1.1" value="0" onchange="selectRadio('2022-3.1.1','0','self','1','无岗位职责或履职不认真，不得分')"/>
					&nbsp;<label >无岗位职责或履职不认真，不得分</label>
				</c:if>
				  <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input   type="radio"  name="2022-3.1.1" value="1" onchange="selectRadio('2022-3.1.1','1','expert','1','')"/>
					&nbsp;<label>职责明确，履职认真，得1分</label><br>
					<input type="radio"   name="2022-3.1.1" value="0" onchange="selectRadio('2022-3.1.1','0','expert','1','无岗位职责或履职不认真，不得分')"/>
					&nbsp;<label>无岗位职责或履职不认真，不得分</label>
				</c:if>
				<c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                    ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
				<textarea onchange="saveSpeReason(this);"  itemId="2022-3.1.1" itemName="主任职责" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.1.1']}</textarea>
				</c:if>
			</td>
		</tr>
		<tr style="height:50px">
			<td>3.1.2教学主任或相应负责人★</td>
			<td>
				与科主任或基地主任非同一人，副高职称及以上，专门具体负责本专业基地教学培训工作的组织与实施
			</td>
			<td>
				职责明确，履职认真，得3分；无岗位职责，或履职不认真，不得分
			</td>
			<td style="text-align: center;">3</td>
			<td name="file" itemId="2022-3.1.2" itemName="教学主任或相应负责人"></td>
			<td><input onchange="saveScoreOnlyIntenger(this,3);" itemId="2022-3.1.2" itemName="教学主任或相应负责人"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,3);" itemId="2022-3.1.2" itemName="教学主任或相应负责人"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input  type="radio"  name="2022-3.1.2" value="3" onchange="selectRadio('2022-3.1.2','3','self','3','')"/>
					&nbsp;<label >职责明确，履职认真，得3分</label><br>
					<input type="radio"  name="2022-3.1.2" value="0" onchange="selectRadio('2022-3.1.2','0','self','3','无岗位职责，或履职不认真，不得分')"/>
					&nbsp;<label >无岗位职责，或履职不认真，不得分</label>
				</c:if>
				  <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input   type="radio"  name="2022-3.1.2" value="3" onchange="selectRadio('2022-3.1.2','3','expert','3','')"/>
					&nbsp;<label>职责明确，履职认真，得3分</label><br>
					<input type="radio"   name="2022-3.1.2" value="0" onchange="selectRadio('2022-3.1.2','0','expert','3','无岗位职责，或履职不认真，不得分')"/>
					&nbsp;<label>无岗位职责，或履职不认真，不得分</label>
				</c:if>
				<c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                    ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
				<textarea onchange="saveSpeReason(this);"  itemId="2022-3.1.2" itemName="教学主任或相应负责人" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.1.2']}</textarea>
				</c:if>
			</td>
		</tr>
		<tr style="height:50px">
			<td>3.1.3教学秘书</td>
			<td>
				主治医师及以上职称，落实本专业基地教学工作
			</td>
			<td>查看相关文件与证明，访谈各类人员（回避制）</td>
			<td>
				有教学秘书，履职认真，得1分；无或履职不认真，不得分
			</td>
			<td style="text-align: center;">1</td>
			<td name="file" itemId="2022-3.1.3" itemName="教学秘书"></td>
			<td><input onchange="saveScoreOnlyIntenger(this,1);" itemId="2022-3.1.3" itemName="教学秘书"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,1);" itemId="2022-3.1.3" itemName="教学秘书"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input  type="radio"  name="2022-3.1.3" value="1" onchange="selectRadio('2022-3.1.3','1','self','1','')"/>
					&nbsp;<label >有教学秘书，履职认真，得1分</label><br>
					<input type="radio"  name="2022-3.1.3" value="0" onchange="selectRadio('2022-3.1.3','0','self','1','无，或履职不认真，不得分')"/>
					&nbsp;<label >无，或履职不认真，不得分</label>
				</c:if>
				  <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input   type="radio"  name="2022-3.1.3" value="1" onchange="selectRadio('2022-3.1.3','1','expert','1','')"/>
					&nbsp;<label>有教学秘书，履职认真，得1分</label><br>
					<input type="radio"   name="2022-3.1.3" value="0" onchange="selectRadio('2022-3.1.3','0','expert','1','无，或履职不认真，不得分')"/>
					&nbsp;<label>无，或履职不认真，不得分</label>
				</c:if>
				<c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                    ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
				<textarea onchange="saveSpeReason(this);"  itemId="2022-3.1.3" itemName="教学秘书" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.1.3']}</textarea>
				</c:if>
			</td>
		</tr>
		<tr style="height:50px">
			<td>3.1.4教学小组</td>
			<td>
				成立教学小组，明确小组职责，定期组织教学工作
			</td>
			<td>查看教学小组名单、职责和教学工作记录</td>
			<td>
				有教学小组，履职认真，得2分；无或履职不认真，不得分
			</td>
			<td style="text-align: center;">2</td>
			<td name="file" itemId="2022-3.1.4" itemName="教学小组"></td>
			<td><input onchange="saveScoreOnlyIntenger(this,2);" itemId="2022-3.1.4" itemName="教学小组"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,2);" itemId="2022-3.1.4" itemName="教学小组"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input  type="radio"  name="2022-3.1.4" value="2" onchange="selectRadio('2022-3.1.4','2','self','2','')"/>
					&nbsp;<label >有教学小组，履职认真，得2分</label><br>
					<input type="radio"  name="2022-3.1.4" value="0" onchange="selectRadio('2022-3.1.4','0','self','2','无或履职不认真，不得分')"/>
					&nbsp;<label >无或履职不认真，不得分</label>
				</c:if>
				  <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input   type="radio"  name="2022-3.1.4" value="2" onchange="selectRadio('2022-3.1.4','2','expert','2','')"/>
					&nbsp;<label>有教学小组，履职认真，得2分</label><br>
					<input type="radio"   name="2022-3.1.4" value="0" onchange="selectRadio('2022-3.1.4','0','expert','2','无或履职不认真，不得分')"/>
					&nbsp;<label>无或履职不认真，不得分</label>
				</c:if>
				<c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                    ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
				<textarea  onchange="saveSpeReason(this);" itemId="2022-3.1.4" itemName="教学小组" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.1.4']}</textarea>
				</c:if>
			</td>
		</tr>
		<tr style="height:50px">
			<td>3.1.5教学计划</td>
			<td>
				按规定制定并落实教学计划
			</td>
			<td>查看原始材料，核实相关信息，演示，访谈（回避制）</td>
			<td>
				有，且严格落实，得2分；未严格落实，不得分
			</td>
			<td style="text-align: center;">2</td>
			<td name="file" itemId="2022-3.1.5" itemName="教学计划"></td>
			<td><input onchange="saveScoreOnlyIntenger(this,2);" itemId="2022-3.1.5" itemName="教学计划"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,2);" itemId="2022-3.1.5" itemName="教学计划"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input  type="radio"  name="2022-3.1.5" value="2" onchange="selectRadio('2022-3.1.5','2','self','2','')"/>
					&nbsp;<label >有，且严格落实，得2分</label><br>
					<input type="radio"  name="2022-3.1.5" value="0" onchange="selectRadio('2022-3.1.5','0','self','2','未严格落实，不得分')"/>
					&nbsp;<label >未严格落实，不得分</label>
				</c:if>
				  <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input   type="radio"  name="2022-3.1.5" value="2" onchange="selectRadio('2022-3.1.5','2','expert','2','')"/>
					&nbsp;<label>有，且严格落实，得2分</label><br>
					<input type="radio"   name="2022-3.1.5" value="0" onchange="selectRadio('2022-3.1.5','0','expert','2','未严格落实，不得分')"/>
					&nbsp;<label>未严格落实，不得分</label>
				</c:if>
				<c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                    ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
				<textarea  onchange="saveSpeReason(this);" itemId="2022-3.1.5" itemName="教学计划" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.1.5']}</textarea>
				</c:if>
			</td>
		</tr>
		<tr style="height:50px">
			<td>3.1.6轮转管理</td>
			<td>
				按细则落实轮转计划和要求
			</td>
			<td>查看2～3名学员轮转手册等原始资料，访谈学员（回避制）</td>
			<td>
				有，且严格落实，得4分；未严格落实，不得分
			</td>
			<td style="text-align: center;">4</td>
			<td name="file" itemId="2022-3.1.6" itemName="轮转管理"></td>
			<td><input onchange="saveScoreOnlyIntenger(this,4);" itemId="2022-3.1.6" itemName="轮转管理"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,4);" itemId="2022-3.1.6" itemName="轮转管理"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input  type="radio"  name="2022-3.1.6" value="4" onchange="selectRadio('2022-3.1.6','4','self','4','')"/>
					&nbsp;<label >有，且严格落实，得4分</label><br>
					<input type="radio"  name="2022-3.1.6" value="0" onchange="selectRadio('2022-3.1.6','0','self','4','未严格落实，不得分')"/>
					&nbsp;<label >未严格落实，不得分</label>
				</c:if>
				  <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input   type="radio"  name="2022-3.1.6" value="4" onchange="selectRadio('2022-3.1.6','4','expert','4','')"/>
					&nbsp;<label>有，且严格落实，得4分</label><br>
					<input type="radio"   name="2022-3.1.6" value="0" onchange="selectRadio('2022-3.1.6','0','expert','4','未严格落实，不得分')"/>
					&nbsp;<label>未严格落实，不得分</label>
				</c:if>
				<c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                    ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
				<textarea  onchange="saveSpeReason(this);" itemId="2022-3.1.6" itemName="轮转管理" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.1.6']}</textarea>
				</c:if>
			</td>
		</tr>
		<tr style="height:50px">
			<td>3.1.7考勤制度</td>
			<td>
				有考勤规章制度，有专人负责，并严格执行
			</td>
			<td>查看考勤规章制度，并抽查2～3名学员考勤记录原始资料</td>
			<td>
				有，且严格落实，得2分；未严格落实，不得分
			</td>
			<td style="text-align: center;">2</td>
			<td name="file" itemId="2022-3.1.7" itemName="考勤制度"></td>
			<td><input onchange="saveScoreOnlyIntenger(this,2);" itemId="2022-3.1.7" itemName="考勤制度"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,2);" itemId="2022-3.1.7" itemName="考勤制度"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input  type="radio"  name="2022-3.1.7" value="2" onchange="selectRadio('2022-3.1.7','2','self','2','')"/>
					&nbsp;<label >有，且严格落实，得2分</label><br>
					<input type="radio"  name="2022-3.1.7" value="0" onchange="selectRadio('2022-3.1.7','0','self','2','未严格落实，不得分')"/>
					&nbsp;<label >未严格落实，不得分</label>
				</c:if>
				  <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input   type="radio"  name="2022-3.1.7" value="2" onchange="selectRadio('2022-3.1.7','2','expert','2','')"/>
					&nbsp;<label>有，且严格落实，得4分</label><br>
					<input type="radio"   name="2022-3.1.7" value="0" onchange="selectRadio('2022-3.1.7','0','expert','2','未严格落实，不得分')"/>
					&nbsp;<label>未严格落实，不得分</label>
				</c:if>
				<c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                    ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
				<textarea onchange="saveSpeReason(this);"  itemId="2022-3.1.7" itemName="考勤制度" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.1.7']}</textarea>
				</c:if>
			</td>
		</tr>
		<tr style="height:50px">
			<td rowspan="3">3.2培训活动</td>
			<td>3.2.1入科教育</td>
			<td>
				规范实施包括科室情况、科室纪律、培养计划与要求、医德医风、医患沟通等入科教育，并有专人组织实施
			</td>
			<td>提供本年度入科教育原始资料</td>
			<td>
				有，且严格落实，得2分；未严格落实，不得分
			</td>
			<td style="text-align: center;">2</td>
			<td name="file" itemId="2022-3.2.1" itemName="入科教育"></td>
			<td><input onchange="saveScoreOnlyIntenger(this,2);" itemId="2022-3.2.1" itemName="入科教育"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,2);" itemId="2022-3.2.1" itemName="入科教育"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input  type="radio"  name="2022-3.2.1" value="2" onchange="selectRadio('2022-3.2.1','2','self','2','')"/>
					&nbsp;<label >有，且严格落实，得2分</label><br>
					<input type="radio"  name="2022-3.2.1" value="0" onchange="selectRadio('2022-3.2.1','0','self','2','未严格落实，不得分')"/>
					&nbsp;<label >未严格落实，不得分</label>
				</c:if>
				  <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input   type="radio"  name="2022-3.2.1" value="2" onchange="selectRadio('2022-3.2.1','2','expert','2','')"/>
					&nbsp;<label>有，且严格落实，得2分</label><br>
					<input type="radio"   name="2022-3.2.1" value="0" onchange="selectRadio('2022-3.2.1','0','expert','2','未严格落实，不得分')"/>
					&nbsp;<label>未严格落实，不得分</label>
				</c:if>
				<c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                    ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
				<textarea  onchange="saveSpeReason(this);" itemId="2022-3.2.1" itemName="入科教育" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.2.1']}</textarea>
				</c:if>
			</td>
		</tr>
		<tr style="height:50px">
			<td>3.2.2小讲课</td>
			<td>
				开展规范的小讲课活动，至少2周1次
			</td>
			<td>提供本年度原始资料，访谈学员（回避制），核实落实情况</td>
			<td>
				开展次数达标，且认真规范，得4分；未达标或不规范，不得分
			</td>
			<td style="text-align: center;">4</td>
			<td name="file" itemId="2022-3.2.2" itemName="小讲课"></td>
			<td><input onchange="saveScoreOnlyIntenger(this,4);" itemId="2022-3.2.2" itemName="小讲课"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,4);" itemId="2022-3.2.2" itemName="小讲课"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input  type="radio"  name="2022-3.2.2" value="4" onchange="selectRadio('2022-3.2.2','4','self','4','')"/>
					&nbsp;<label >开展次数达标，且认真规范，得4分</label><br>
					<input type="radio"  name="2022-3.2.2" value="0" onchange="selectRadio('2022-3.2.2','0','self','4',' 未达标或不规范，不得分')"/>
					&nbsp;<label > 未达标或不规范，不得分</label>
				</c:if>
				  <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input   type="radio"  name="2022-3.2.2" value="4" onchange="selectRadio('2022-3.2.2','4','expert','4','')"/>
					&nbsp;<label>开展次数达标，且认真规范，得4分</label><br>
					<input type="radio"   name="2022-3.2.2" value="0" onchange="selectRadio('2022-3.2.2','0','expert','4',' 未达标或不规范，不得分')"/>
					&nbsp;<label> 未达标或不规范，不得分</label>
				</c:if>
				<c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                    ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
				<textarea onchange="saveSpeReason(this);"  itemId="2022-3.2.2" itemName="小讲课" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.2.2']}</textarea>
				</c:if>
			</td>
		</tr>
		<tr style="height:50px">
			<td>3.2.3疑难病例讨论</td>
			<td>
				开展规范的疑难病例讨论，至少2周1次
			</td>
			<td>提供本年度原始资料，访谈学员（回避制），核实落实情况</td>
			<td>
				开展次数达标，且认真规范，得3分；未达标或不规范，不得分
			</td>
			<td style="text-align: center;">3</td>
			<td name="file" itemId="2022-3.2.3" itemName="疑难病例讨论"></td>
			<td><input onchange="saveScoreOnlyIntenger(this,3);" itemId="2022-3.2.3" itemName="疑难病例讨论"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,3);" itemId="2022-3.2.3" itemName="疑难病例讨论"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input  type="radio"  name="2022-3.2.3" value="3" onchange="selectRadio('2022-3.2.3','3','self','3','')"/>
					&nbsp;<label >开展次数达标，且认真规范，得3分</label><br>
					<input type="radio"  name="2022-3.2.3" value="0" onchange="selectRadio('2022-3.2.3','0','self','3',' 未达标或不规范，不得分')"/>
					&nbsp;<label > 未达标或不规范，不得分</label>
				</c:if>
				  <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input   type="radio"  name="2022-3.2.3" value="3" onchange="selectRadio('2022-3.2.3','3','expert','3','')"/>
					&nbsp;<label>开展次数达标，且认真规范，得3分</label><br>
					<input type="radio"   name="2022-3.2.3" value="0" onchange="selectRadio('2022-3.2.3','0','expert','3',' 未达标或不规范，不得分')"/>
					&nbsp;<label> 未达标或不规范，不得分</label>
				</c:if>
				<c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                    ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
				<textarea  onchange="saveSpeReason(this);" itemId="2022-3.2.3" itemName="疑难病例讨论" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.2.3']}</textarea>
				</c:if>
			</td>
		</tr>
		<tr style="height:50px">
			<td>3.3过程考核</td>
			<td>3.3.1出科考核★</td>
			<td>
				理论考核、技能操作考核评分标准、学员测评结果等原始资料齐全，真实规范
			</td>
			<td>随机抽查、访谈委培、社会人、本院学员2～3名（回避制），检查近1年原始资料</td>
			<td>
				考核项目全面，且认真规范，得6分；仅有技能操作考核，得5分；理论考试，得3分；无考核，不得分
			</td>
			<td style="text-align: center;">6</td>
			<td name="file" itemId="2022-3.3.1" itemName="出科考核"></td>
			<td><input onchange="saveScoreOnlyIntenger(this,6);" itemId="2022-3.3.1" itemName="出科考核"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,6);" itemId="2022-3.3.1" itemName="出科考核"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input  type="radio"  name="2022-3.3.1" value="6" onchange="selectRadio('2022-3.3.1','6','self','6','')"/>
					&nbsp;<label >考核项目全面，且认真规范，得6分</label><br>
					<input type="radio"  name="2022-3.3.1" value="5" onchange="selectRadio('2022-3.3.1','5','self','6','仅有技能操作考核，得5分')"/>
					&nbsp;<label >仅有技能操作考核，得5分</label><br>
					<input type="radio"  name="2022-3.3.1" value="3" onchange="selectRadio('2022-3.3.1','3','self','6','理论考试，得3分')"/>
					&nbsp;<label >理论考试，得3分</label><br>
					<input type="radio"  name="2022-3.3.1" value="0" onchange="selectRadio('2022-3.3.1','0','self','6','无考核，不得分')"/>
					&nbsp;<label >无考核，不得分</label>
				</c:if>
				  <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input   type="radio"  name="2022-3.3.1" value="6" onchange="selectRadio('2022-3.3.1','6','expert','6','')"/>
					&nbsp;<label>考核项目全面，且认真规范，得6分</label><br>
					<input type="radio"  name="2022-3.3.1" value="5" onchange="selectRadio('2022-3.3.1','5','expert','6','仅有技能操作考核，得5分')"/>
					&nbsp;<label >仅有技能操作考核，得5分</label><br>
					<input type="radio"  name="2022-3.3.1" value="3" onchange="selectRadio('2022-3.3.1','3','expert','6','理论考试，得3分')"/>
					&nbsp;<label >理论考试，得3分</label><br>
					<input type="radio"  name="2022-3.3.1" value="0" onchange="selectRadio('2022-3.3.1','0','expert','6','无考核，不得分')"/>
					&nbsp;<label >无考核，不得分</label>
				</c:if>
				<c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                    ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
				<textarea onchange="saveSpeReason(this);"  itemId="2022-3.3.1" itemName="出科考核" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.3.1']}</textarea>
				</c:if>
			</td>
		</tr>
		<tr style="height:50px">
			<td style="height: 50px;" rowspan="6">4.质量控制<br>（35分）</td>
			<td rowspan="2">4.1带教医师教学质量</td>
			<td>4.1.1带教老师教学活动★</td>
			<td>
				带教医师针对学员开展规范的临床教学指导（演示、阅片会）
			</td>
			<td>随机抽查2～3名带教医师的临床教学情况</td>
			<td rowspan="2">
				临床教学评分表见
					<a href="javascript:void(0)" onclick="Schedule('evaluationInfo_2300_02','附表2');">附表2</a>
				≥90分得10分，＜90～80分得8分， ＜80～70分得6分，＜70～60分得4分，＜60分不得分
			</td>
			<td style="text-align: center;" rowspan="2">10</td>
			<td name="file" itemId="2022-4.1.1" itemName="带教医师教学质量" rowspan="2"></td>
			<td rowspan="2"><input id="fubiao2s" onchange="saveScoreOnlyIntenger(this,10);" itemId="2022-4.1.1" itemName="带教医师教学质量"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td rowspan="2"><input id="fubiao2e" onchange="saveScore4ExpertOnlyIntenger(this,10);" itemId="2022-4.1.1" itemName="带教医师教学质量"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td rowspan="2">
				<textarea onchange="saveSpeReason(this);"  itemId="2022-4.1.1" itemName="带教医师教学质量" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-4.1.1']}</textarea>
			</td>
		</tr>
		<tr style="height:50px">
			<td>4.1.2技能操作带教情况★</td>
			<td>
				带教医师指导学员完成技能操作，操作前、操作中及时的指导，结束后提问、讲解、指导书写报告等
			</td>
			<td>随机抽查2～3名带教医师指导学员（规二及以上）进行技能操作等</td>
		</tr>
		<tr style="height:50px">
			<td rowspan="4">4.2学员学习效果</td>
			<td>4.2.1报告书写★</td>
			<td>
				学员诊断报告书写规范、准确
			</td>
			<td>随机抽查2～3名学员书写的诊断报告，结合报告提问（回避制）</td>
			<td>
				诊断报告书写评分表见
					<a href="javascript:void(0)" onclick="Schedule('evaluationInfo_2300_03','附表3');">附表3</a>
				≥90分得5分，＜90～80分得4分，＜80～70分得2分，＜70～60分得1分，＜60分不得分
			</td>
			<td style="text-align: center;">5</td>
			<td name="file" itemId="2022-4.2.1" itemName="报告书写"></td>
			<td><input id="fubiao3s" onchange="saveScoreOnlyIntenger(this,5);" itemId="2022-4.2.1" itemName="报告书写"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input id="fubiao3e" onchange="saveScore4ExpertOnlyIntenger(this,5);" itemId="2022-4.2.1" itemName="报告书写"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<textarea onchange="saveSpeReason(this);"  itemId="2022-4.2.1" itemName="报告书写" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-4.2.1']}</textarea>
			</td>
		</tr>
		<tr style="height:50px">
			<td>4.2.2上机操作★</td>
			<td>
				学员技能操作情况
			</td>
			<td>随机抽查2～3名二年级及以上学员进行技能操作，查看其掌握超声检查的情况</td>
			<td>
				技能操作评分表见
					<a href="javascript:void(0)" onclick="Schedule('evaluationInfo_2300_04','附表4');">附表4</a>
				≥90分得8分，＜90～80分得6分，＜80～70分得4分，＜70～60分得2分，＜60分不得分
			</td>
			<td style="text-align: center;">8</td>
			<td name="file" itemId="2022-4.2.2" itemName="上机操作"></td>
			<td><input id="fubiao4s" onchange="saveScoreOnlyIntenger(this,8);" itemId="2022-4.2.2" itemName="上机操作"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input id="fubiao4e" onchange="saveScore4ExpertOnlyIntenger(this,8);" itemId="2022-4.2.2" itemName="上机操作"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<textarea onchange="saveSpeReason(this);"  itemId="2022-4.2.2" itemName="上机操作" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-4.2.2']}</textarea>
			</td>
		</tr>
		<tr style="height:50px">
			<td>4.2.3完成培训内容与要求</td>
			<td>按照本专业《住院医师规范化培训内容与标准（试行）》细则，核实培训内容的完成情况</td>
			<td>
				随机抽查、访谈学员2～3名（回避制），查看轮转登记手册、出科考核等原始资料
			</td>
			<td>
				资料完整得4分
				资料部分完整得2分
				无完整资料不得分
			</td>
			<td style="text-align: center;">4</td>
			<td name="file" itemId="2022-4.2.3" itemName="完成培训内容与要求"></td>
			<td><input onchange="saveScoreOnlyIntenger(this,4);" itemId="2022-4.2.3" itemName="完成培训内容与要求"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,4);" itemId="2022-4.2.3" itemName="完成培训内容与要求"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input  type="radio"  name="2022-4.2.3" value="4" onchange="selectRadio('2022-4.2.3','4','self','4','')"/>
					&nbsp;<label > 资料完整得4分</label><br>
					<input type="radio"  name="2022-4.2.3" value="2" onchange="selectRadio('2022-4.2.3','2','self','4','资料部分完整得2分')"/>
					&nbsp;<label >资料部分完整得2分</label><br>
					<input type="radio"  name="2022-4.2.3" value="0" onchange="selectRadio('2022-4.2.3','0','self','4','无完整资料不得分')"/>
					&nbsp;<label >无完整资料不得分</label>
				</c:if>
				  <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input   type="radio"  name="2022-4.2.3" value="4" onchange="selectRadio('2022-4.2.3','4','expert','4','')"/>
					&nbsp;<label>资料完整得4分</label><br>
					<input type="radio"  name="2022-4.2.3" value="2" onchange="selectRadio('2022-4.2.3','2','expert','4','资料部分完整得2分')"/>
					&nbsp;<label >资料部分完整得2分</label><br>
					<input type="radio"  name="2022-4.2.3" value="0" onchange="selectRadio('2022-4.2.3','0','expert','4','无完整资料不得分')"/>
					&nbsp;<label >无完整资料不得分</label>
				</c:if>
				<c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                    ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
				<textarea onchange="saveSpeReason(this);"  itemId="2022-4.2.3" itemName="完成培训内容与要求" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-4.2.3']}</textarea>
				</c:if>
			</td>
		</tr>
		<tr style="height:50px">
			<td>4.2.4近三年理论、技能考核成绩★</td>
			<td>学员理论与技能考核情况</td>
			<td>
				查看原始材料，核实相关信息
			</td>
			<td>
				学员理论及技能考核全部合格，得8分；1名学员考核理论或技能不合格，得4分；2名学员考核理论或技能不合格，不得分
			</td>
			<td style="text-align: center;">8</td>
			<td name="file" itemId="2022-4.2.4" itemName="近三年理论、技能考核成绩"></td>
			<td><input onchange="saveScoreOnlyIntenger(this,8);" itemId="2022-4.2.4" itemName="近三年理论、技能考核成绩"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4ExpertOnlyIntenger(this,8);" itemId="2022-4.2.4" itemName="近三年理论、技能考核成绩"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td>
				<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
					<input  type="radio"  name="2022-4.2.4" value="8" onchange="selectRadio('2022-4.2.4','8','self','8','')"/>
					&nbsp;<label >学员理论及技能考核全部合格，得8分</label><br>
					<input type="radio"  name="2022-4.2.4" value="4" onchange="selectRadio('2022-4.2.4','4','self','8','1名学员考核理论或技能不合格，得4分')"/>
					&nbsp;<label >1名学员考核理论或技能不合格，得4分</label><br>
					<input type="radio"  name="2022-4.2.4" value="0" onchange="selectRadio('2022-4.2.4','0','self','8','2名学员考核理论或技能不合格，不得分')"/>
					&nbsp;<label >2名学员考核理论或技能不合格，不得分</label>
				</c:if>
				  <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
					<input   type="radio"  name="2022-4.2.4" value="8" onchange="selectRadio('2022-4.2.4','8','expert','8','')"/>
					&nbsp;<label>学员理论及技能考核全部合格，得8分</label><br>
					<input type="radio"  name="2022-4.2.4" value="4" onchange="selectRadio('2022-4.2.4','4','expert','8','1名学员考核理论或技能不合格，得4分')"/>
					&nbsp;<label >1名学员考核理论或技能不合格，得4分</label><br>
					<input type="radio"  name="2022-4.2.4" value="0" onchange="selectRadio('2022-4.2.4','0','expert','8','2名学员考核理论或技能不合格，不得分')"/>
					&nbsp;<label >2名学员考核理论或技能不合格，不得分</label>
				</c:if>
				<c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                    ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
				<textarea  onchange="saveSpeReason(this);" itemId="2022-4.2.4" itemName="近三年理论、技能考核成绩" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-4.2.4']}</textarea>
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
				<textarea class="input" type="text" id="speContentInput" name="speContentInput" style="width: 96%;height: 150px;text-indent: 3px;" onchange="saveSpeContent(this,'${userFlow}')">${subjectUser.speContent}</textarea>
			</td>
		</tr>
		<tr style="height:16px;">
		</tr>
		<tr>
			<td colspan="11" style="text-align: left">
				使用说明：<br>
				&#12288;&#12288;1.以上指标条件应用时，均应参照《住院医师规范化培训基地认定标准（试行）——超声医学科专业基地认定细则》和《住院医师规范化培训内容与标准（试行）——超声医学科培训细则》等相关的国家卫计委、中国医师协会颁布的文件。<br>
				&#12288;&#12288;2.随机抽查对象优先选择委托学员和社会人学员，如果没有可考虑本基地（本科室）学员。<br>
				&#12288;&#12288;3.以上各项所涉及的文件如证书、证明、记录、成绩单等，均必须由被检查单位的教育、医务、人事等部门提供原件及其盖红章的复印件。<br>
				&#12288;&#12288;4.现场理论考核，本次暂不计分，为预调查项。<br>
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