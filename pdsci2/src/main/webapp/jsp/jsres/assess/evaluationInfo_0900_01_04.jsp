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


		//保存自评分
		function saveScore(expl, num) {
			var score = expl.value;
			var itemId = expl.getAttribute("itemId");
			var itemName = expl.getAttribute("itemName");
			var reg = /^\d+(\.\d)?$/;
			var reg1 = /^\d+(\.0)$/;
			if (reg.test(score)) {
				if (reg1.test(score)) {
					var score1 = parseInt(score);
					expl.value = score1;
				} else {
					var score1 = score;
				}
				$(expl).attr("preValue", score1);
				var url = "<s:url value='/jsres/supervisio/savScheduleScore'/>";
				var data = {
					"itemId": itemId,
					"itemName": itemName,
					"score": score1,
					"orgFlow": '${orgFlow}',
					"orgName": '${orgName}',
					"speId": '${speId}',
					"subjectFlow": '${subjectFlow}',
					"num": num,
					"roleFlag": '${roleFlag}',
					"fileRoute": '${fileRoute}'
				};
				top.jboxPost(url, data, function (resp) {
					if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
						var itemName2;
						if (itemName.startsWith("d")) {
							itemName2 = "k" + itemName.substring(1);
						} else {
							itemName2 = "d" + itemName.substring(1);
						}
						var itenNameScore = 0;
						if (score >= num) {
							itenNameScore = '';
						} else {
							itenNameScore = '√';
						}
						var inputItem = $("input[itemName=\"" + itemName2 + "\"]").val(itenNameScore);
						loadDate();
						top.jboxTip(resp);
					} else {
						top.jboxTip(resp);
					}
				}, null, false);
			} else {
				expl.value = expl.getAttribute("preValue");
				top.jboxTip("评分只能是正整数或含有一位小数");
			}
			loadDate();
		}

		//计算合计
		function loadDate() {
			var itemIdList = $("input");
			var kScore = 0;
			for (var i = 0; i < itemIdList.length; i++) {
				if (itemIdList[i].getAttribute("name") == "self1" || itemIdList[i].getAttribute("name") == "self2") {
					kScore = Number(kScore) + Number(itemIdList[i].value);
				}
			}
			$("#expertTotalled").text(check(kScore.toFixed(1)));
		}

		function subInfo() {
			// 遍历疾病和临床的分数
			var itemIdList = $("input");
			for (var i = 0; i < itemIdList.length; i++) {
				if ((itemIdList[i].getAttribute("name") == "self1" || itemIdList[i].getAttribute("name") == "self2")
						&& itemIdList[i].value == "") {
					$(itemIdList[i]).focus();
					top.jboxTip("有输入框未输入数据，请输入数据！");
					return;
				}
			}
			var selfOneScore = 0;//疾病分数
			var selfTwoScore = 0;//临床分数
			for (var i = 0; i < itemIdList.length; i++) {
				if (itemIdList[i].getAttribute("name") == "self1") {
					selfOneScore = Number(selfOneScore) + Number(itemIdList[i].value);
				}
				if (itemIdList[i].getAttribute("name") == "self2") {
					selfTwoScore = Number(selfTwoScore) + Number(itemIdList[i].value);
				}
			}
			selfOneScore = parseInt(selfOneScore / 160 * 100);
			selfTwoScore = parseInt(selfTwoScore / 85 * 100);
			var url = "<s:url value='/jsres/supervisio/saveManyScheduleTotalled'/>";
			var data = {
				"itemIdOne": 'scoreInfo14',
				"itemIdTwo": 'scoreInfo24',
				"itemName": 'evaluationInfo_0900',
				"selfOneScore": selfOneScore,
				"selfTwoScore": selfTwoScore,
				"orgFlow": '${orgFlow}',
				"orgName": '${orgName}',
				"speId": '${speId}',
				"subjectFlow": '${subjectFlow}',
				"roleFlag": '${roleFlag}'
			};
			top.jboxPost(url, data, function (resp) {
				if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
					window.parent.frames["jbox-message-iframe"].$("#scoreInfo14")[0].value = selfOneScore;
					window.parent.frames["jbox-message-iframe"].$("#scoreInfo24")[0].value = selfTwoScore;
					var scoreOneAll = 0;
					var scoreTwoAll = 0;
					var oneNum = 0;
					var twoNum = 0;
					var scoreOneInfoList = window.parent.frames["jbox-message-iframe"].$("input[name='scoreOneInfo']");
					var scoreTwoInfoList = window.parent.frames["jbox-message-iframe"].$("input[name='scoreTwoInfo']");
					for (var i = 1; i <= scoreOneInfoList.length; i++) {
						if (!isNaN(parseInt(window.parent.frames["jbox-message-iframe"].$("#scoreInfo1" + i)[0].value))) {
							scoreOneAll = parseInt(window.parent.frames["jbox-message-iframe"].$("#scoreInfo1" + i)[0].value) + parseInt(scoreOneAll);
							oneNum++;
						}
					}
					for (var i = 1; i <= scoreTwoInfoList.length; i++) {
						if (!isNaN(parseInt(window.parent.frames["jbox-message-iframe"].$("#scoreInfo2" + i)[0].value))) {
							scoreTwoAll = parseInt(window.parent.frames["jbox-message-iframe"].$("#scoreInfo2" + i)[0].value) + parseInt(scoreTwoAll);
							twoNum++;
						}
					}
					selfOneScore = parseInt(scoreOneAll / oneNum);
					selfTwoScore = parseInt(scoreTwoAll / twoNum);
					if (selfOneScore >= 100) {
						selfOneScore = 2;
					} else if (selfOneScore >= 90) {
						selfOneScore = 1;
					} else if (selfOneScore >= 85) {
						selfOneScore = 0.5;
					} else {
						selfOneScore = 0;
					}
					if (selfTwoScore >= 100) {
						selfTwoScore = 4;
					} else if (selfTwoScore >= 90) {
						selfTwoScore = 2;
					} else if (selfTwoScore >= 85) {
						selfTwoScore = 1;
					} else {
						selfTwoScore = 0;
					}
					var inputSelf1;
					var inputSelf2;
					if (${ roleFlag==("baseExpert")}) {
						inputSelf1 = window.parent.frames["jbox-message-iframe"].$("#fubiao11");
						inputSelf2 = window.parent.frames["jbox-message-iframe"].$("#fubiao12");
					} else if (${roleFlag==("expertLeader")}) {
						inputSelf1 = window.parent.frames["jbox-message-iframe"].$("#fubiao11Expert");
						inputSelf2 = window.parent.frames["jbox-message-iframe"].$("#fubiao12Expert");
					}

					inputSelf1[0].value = selfOneScore;
					inputSelf2[0].value = selfTwoScore;
					if (${ roleFlag==("baseExpert")}) {
						window.parent.frames["jbox-message-iframe"].saveScore(inputSelf1[0], selfOneScore);
						window.parent.frames["jbox-message-iframe"].saveScore(inputSelf2[0], selfTwoScore);
					} else if (${roleFlag==("expertLeader")}) {
						window.parent.frames["jbox-message-iframe"].saveScore4Expert(inputSelf1[0], selfOneScore);
						window.parent.frames["jbox-message-iframe"].saveScore4Expert(inputSelf2[0], selfTwoScore);
					}
				} else {
					top.jboxTip(resp);
				}
			}, null, false);
		};

		//窗口加载后
		$(function () {
			//获取所有input标签
			var itemIdList = $("input");
			for (var i = 0; i < itemIdList.length; i++) {
				if (itemIdList[i].getAttribute("type") == "text") {
					$(itemIdList[i]).css("display", "block").css("margin", "0 auto");
				}
			}
			if (${isRead eq GlobalConstant.RECORD_STATUS_Y} || ${GlobalConstant.USER_LIST_GLOBAL eq roleFlag} || ${editFlag eq GlobalConstant.FLAG_N}) {
				for (var i = 0; i < itemIdList.length; i++) {
					itemIdList[i].readOnly = "true";
				}
			}
			//为每个input赋值
			<c:forEach items="${scoreList}" var="item" varStatus="status" >
			//获得值
			for (var i = 0; i < itemIdList.length; i++) {
				if(null != itemIdList[i].getAttribute("itemName")){
					if (itemIdList[i].getAttribute("itemName").substring(0,1) == "${item.itemName}".substring(0,1) && itemIdList[i].getAttribute("itemId") == "${item.itemId}" && itemIdList[i].getAttribute("name").startsWith("s")) {
						itemIdList[i].value = "${item.score}";
						$(itemIdList[i]).attr("preValue", "${item.score}");
					}
					if (itemIdList[i].getAttribute("itemName").substring(0,1) == "${item.itemName}".substring(0,1) && itemIdList[i].getAttribute("itemId") == "${item.itemId}" && itemIdList[i].getAttribute("name").startsWith("l")) {
						itemIdList[i].value = "${item.score}";
						if (itemIdList[i].value <= 0) {
							itemIdList[i].value = "";
						} else {
							itemIdList[i].value = "√";
						}
						$(itemIdList[i]).attr("preValue", "${item.score}");
					}
				}
			}
			</c:forEach>
			loadDate();
		});

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
	</script>
</head>
<body>
<div class="div_table"style="overflow: auto;max-height: 570px;">
	<table cellpadding="4" style="width: 1000px;font-size: inherit">
		<tbody>
			<tr height="34" class="firstRow">
				<th colspan="6">
					<h2 style="font-size:150%">外科(泌尿外科)疾病种类/临床技能操作</h2>
				</th>
			</tr>
			<tr style="height:32px;">
				<th colspan="2">年收治病人数(人次)</th>
				<th rowspan="2">标准</th>
				<th >≥300</th>
				<th rowspan="3">实际数</th>
				<th rowspan="3">低于标准数（划√）</th>
			</tr>
			<tr style="height:32px;">
				<th colspan="2">年门诊量(人次)</th>
				<th>≥9600</th>
			</tr>
			<tr style="height:32px;">
				<th colspan="2">疾病种类/临床技能操作</th>
				<th colspan="2">标准</th>
			</tr>
			<tr style="height:32px">
				<th rowspan="9">疾病种类</th>
				<th >泌尿生殖系炎症</th>
				<th colspan="2">≥50</th>
				<td>
					<input onchange="saveScore(this,5);" itemId="0900-04-1.1" itemName="k泌尿生殖系炎症"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="0900-04-1.1" itemName="d泌尿生殖系炎症"  name="less" readonly="readonly"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >睾丸鞘膜积液</th>
				<th colspan="2">≥5</th>
				<td>
					<input onchange="saveScore(this,5);" itemId="0900-04-1.2" itemName="k睾丸鞘膜积液"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="0900-04-1.2" itemName="d睾丸鞘膜积液"  name="less" readonly="readonly"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >前列腺增生症</th>
				<th colspan="2">≥25</th>
				<td>
					<input onchange="saveScore(this,5);" itemId="0900-04-1.3" itemName="k前列腺增生症"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="0900-04-1.3" itemName="d前列腺增生症"  name="less" readonly="readonly"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >隐睾</th>
				<th colspan="2">≥5</th>
				<td>
					<input onchange="saveScore(this,5);" itemId="0900-04-1.4" itemName="k隐睾"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="0900-04-1.4" itemName="d隐睾"  name="less" readonly="readonly"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >精索静脉曲张</th>
				<th colspan="2">≥10</th>
				<td>
					<input onchange="saveScore(this,5);" itemId="0900-04-1.5" itemName="k精索静脉曲张"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="0900-04-1.5" itemName="d精索静脉曲张"  name="less" readonly="readonly"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >泌尿系结石</th>
				<th colspan="2">≥30</th>
				<td>
					<input onchange="saveScore(this,5);" itemId="0900-04-1.6" itemName="k泌尿系结石"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="0900-04-1.6" itemName="d泌尿系结石"  name="less" readonly="readonly"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >膀胱癌</th>
				<th colspan="2">≥20</th>
				<td>
					<input onchange="saveScore(this,5);" itemId="0900-04-1.7" itemName="k膀胱癌"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="0900-04-1.7" itemName="d膀胱癌"  name="less" readonly="readonly"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >肾肿瘤</th>
				<th colspan="2">≥10</th>
				<td>
					<input onchange="saveScore(this,5);" itemId="0900-04-1.8" itemName="k肾肿瘤"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="0900-04-1.8" itemName="d肾肿瘤"  name="less" readonly="readonly"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >前列腺癌</th>
				<th colspan="2">≥5</th>
				<td>
					<input onchange="saveScore(this,5);" itemId="0900-04-1.9" itemName="k前列腺癌"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="0900-04-1.9" itemName="d前列腺癌"  name="less" readonly="readonly"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th rowspan="11">临床技能操作</th>
				<th >膀胱造瘘术</th>
				<th colspan="2">≥5</th>
				<td>
					<input onchange="saveScore(this,5);" itemId="0900-04-2.1" itemName="k膀胱造瘘术"  name="self2"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="0900-04-2.1" itemName="d膀胱造瘘术"  name="less" readonly="readonly"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >精索静脉高位结扎术</th>
				<th colspan="2">≥5</th>
				<td>
					<input onchange="saveScore(this,5);" itemId="0900-04-2.2" itemName="k精索静脉高位结扎术"  name="self2"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="0900-04-2.2" itemName="d精索静脉高位结扎术"  name="less" readonly="readonly"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >睾丸鞘膜翻转术</th>
				<th colspan="2">≥5</th>
				<td>
					<input onchange="saveScore(this,5);" itemId="0900-04-2.3" itemName="k睾丸鞘膜翻转术"  name="self2"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="0900-04-2.3" itemName="d睾丸鞘膜翻转术"  name="less" readonly="readonly"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >睾丸切除术</th>
				<th colspan="2">≥5</th>
				<td>
					<input onchange="saveScore(this,5);" itemId="0900-04-2.4" itemName="k睾丸切除术"  name="self2"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="0900-04-2.4" itemName="d睾丸切除术"  name="less" readonly="readonly"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >膀胱肿瘤手术</th>
				<th colspan="2">≥5</th>
				<td>
					<input onchange="saveScore(this,5);" itemId="0900-04-2.5" itemName="k膀胱肿瘤手术"  name="self2"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="0900-04-2.5" itemName="d膀胱肿瘤手术"  name="less" readonly="readonly"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >肾切除术</th>
				<th colspan="2">≥15</th>
				<td>
					<input onchange="saveScore(this,5);" itemId="0900-04-2.6" itemName="k肾切除术"  name="self2"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="0900-04-2.6" itemName="d肾切除术"  name="less" readonly="readonly"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >泌尿系结石手术</th>
				<th colspan="2">≥10</th>
				<td>
					<input onchange="saveScore(this,5);" itemId="0900-04-2.7" itemName="k泌尿系结石手术"  name="self2"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="0900-04-2.7" itemName="d泌尿系结石手术"  name="less" readonly="readonly"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >前列腺增生手术</th>
				<th colspan="2">≥10</th>
				<td>
					<input onchange="saveScore(this,5);" itemId="0900-04-2.8" itemName="k前列腺增生手术"  name="self2"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="0900-04-2.8" itemName="d前列腺增生手术"  name="less" readonly="readonly"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >尿道狭窄手术</th>
				<th colspan="2">≥5</th>
				<td>
					<input onchange="saveScore(this,5);" itemId="0900-04-2.9" itemName="k尿道狭窄手术"  name="self2"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="0900-04-2.9" itemName="d尿道狭窄手术"  name="less" readonly="readonly"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >泌尿生殖系成形术</th>
				<th colspan="2">≥5</th>
				<td>
					<input onchange="saveScore(this,5);" itemId="0900-04-2.10" itemName="k泌尿生殖系成形术"  name="self2"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="0900-04-2.10" itemName="d泌尿生殖系成形术"  name="less" readonly="readonly"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >腔内泌尿外科手术</th>
				<th colspan="2">≥15</th>
				<td>
					<input onchange="saveScore(this,5);" itemId="0900-04-2.11" itemName="k腔内泌尿外科手术"  name="self2"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="0900-04-2.11" itemName="d腔内泌尿外科手术"  name="less" readonly="readonly"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th colspan="5" style="text-align: right">合计：</th>
				<td style="text-align: center;"><span id="expertTotalled"></span></td>
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