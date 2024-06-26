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
				if (itemIdList[i].getAttribute("itemName") == "${item.itemName}" && itemIdList[i].getAttribute("name") == "self") {
					itemIdList[i].value = "${item.score}";
					$(itemIdList[i]).attr("preValue", "${item.score}");
				}
			}
			</c:forEach>
			loadDate();
		});


		//计算合计
		function loadDate() {
			var itemIdList = $("input");
			//实得分合计
			var score = 0;
			for (var i = 0; i < itemIdList.length; i++) {
				if (itemIdList[i].getAttribute("name") == "self") {
					score = Number(score) + Number(itemIdList[i].value);
				}
			}
			$("#selfTotalled").text(check(score.toFixed(1)));
		}

		function subInfo() {
			var itemIdList = $("input");
			for (var i = 0; i < itemIdList.length; i++) {
				if ((itemIdList[i].getAttribute("name") == "self")
						&& itemIdList[i].value == "") {
					$(itemIdList[i]).focus();
					top.jboxTip("有输入框未输入数据，请输入数据！");
					return;
				}
			}
			var expertTotal = Number($('#selfTotalled').text());
			var url = "<s:url value='/jsres/supervisio/saveScheduleTotalled'/>";
			var data = {
				"itemId": 'scoreInfo2',
				"itemName": 'evaluationInfo_0800',
				"score": expertTotal,
				"orgFlow": '${orgFlow}',
				"orgName": '${orgName}',
				"speId": '${speId}',
				"subjectFlow": '${subjectFlow}',
				"roleFlag": '${roleFlag}'
			};
			top.jboxPost(url, data, function (resp) {
				if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
					window.parent.frames["jbox-message-iframe"].$("#scoreInfo2")[0].value = $('#selfTotalled').text();
					var scoreAll = 0;
					var num = 0;
					var scoreInfoList = window.parent.frames["jbox-message-iframe"].$("input[name='scoreInfo']");
					for (var i = 1; i <= scoreInfoList.length; i++) {
						if (!isNaN(parseInt(window.parent.frames["jbox-message-iframe"].$("#scoreInfo" + i)[0].value))) {
							scoreAll = parseInt(window.parent.frames["jbox-message-iframe"].$("#scoreInfo" + i)[0].value) + parseInt(scoreAll);
							num++;
						}
					}
					scoreAll = scoreAll / num;
					if (scoreAll >= 90) {
						scoreAll = 8;
					}else if (scoreAll >= 80) {
						scoreAll = 5;
					} else if (scoreAll >= 70) {
						scoreAll = 3;
					} else if (scoreAll >= 60) {
						scoreAll = 1;
					} else {
						scoreAll = 0;
					}
					var input;
					if (${ roleFlag==("baseExpert")}) {
						input = window.parent.frames["jbox-message-iframe"].$("#fubiao4");
					} else if (${roleFlag==("expertLeader")}) {
						input = window.parent.frames["jbox-message-iframe"].$("#fubiao4Expert");
					}
					input[0].value = scoreAll;
					if (${ roleFlag==("baseExpert")}) {
						window.parent.frames["jbox-message-iframe"].saveScore(input[0], scoreAll);
					} else if (${roleFlag==("expertLeader")}) {
						window.parent.frames["jbox-message-iframe"].saveScore4Expert(input[0], scoreAll);
					}
					top.jboxClose();
				} else {
					top.jboxTip(resp);
				}
			}, null, false);
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
				var url = "<s:url value='/jsres/supervisio/saveScheduleMK'/>";
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
						loadDate();
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
<div class="div_table" style="overflow: auto;max-height: 570px;">
	<table cellpadding="4" style="width: 1000px;font-size: inherit">
		<tbody>
			<tr height="34" class="firstRow">
				<th colspan="5">
					<h2 style="font-size:150%">康复医学科住院医师规范化培训临床实践能力考核评分表</h2>
				</th>
			</tr>
			<tr style="height:32px;">
				<th colspan="5">关节活动范围/徒手肌力检查</th>
			</tr>
			<tr style="height:32px;">
				<th></th>
				<th >序号</th>
				<th >考核内容</th>
				<th >标准分</th>
				<th >得分</th>
			</tr>
			<tr style="height:32px">
				<th rowspan="6">关节活动范围检查</th>
				<th >1</th>
				<th >测试前是否向患者解释说明</th>
				<td style="text-align: center">5</td>
				<td>
					<input onchange="saveScore(this,5);" itemId="0800-05-1.1" itemName="测试前是否向患者解释说明"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
			</tr>
			<tr style="height:32px">
				<th >2</th>
				<th >检查时患者体位摆放</th>
				<td style="text-align: center">10</td>
				<td>
					<input onchange="saveScore(this,10);" itemId="0800-05-1.2" itemName="检查时患者体位摆放"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
			</tr>
			<tr style="height:32px">
				<th >3</th>
				<th >固定臂位置</th>
				<td style="text-align: center">10</td>
				<td>
					<input onchange="saveScore(this,10);" itemId="0800-05-1.3" itemName="固定臂位置"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
			</tr>
			<tr style="height:32px">
				<th >4</th>
				<th >移动臂位置</th>
				<td style="text-align: center">10</td>
				<td>
					<input onchange="saveScore(this,10);" itemId="0800-05-1.4" itemName="移动臂位置"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
			</tr>
			<tr style="height:32px">
				<th >5</th>
				<th >结果的记录</th>
				<td style="text-align: center">10</td>
				<td>
					<input onchange="saveScore(this,10);" itemId="0800-05-1.5" itemName="结果的记录"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
			</tr>
			<tr style="height:32px">
				<th >6</th>
				<th >测量中注意事项</th>
				<td style="text-align: center">5</td>
				<td>
					<input onchange="saveScore(this,5);" itemId="0800-05-1.6" itemName="测量中注意事项"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
			</tr>
			<tr style="height:32px">
				<th rowspan="5">徒手肌力检查</th>
				<th >1</th>
				<th >测试前是否向患者解释说明</th>
				<td style="text-align: center">5</td>
				<td>
					<input onchange="saveScore(this,5);" itemId="0800-05-2.1" itemName="测试前是否向患者解释说明"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
			</tr>
			<tr style="height:32px">
				<th >2</th>
				<th >检查时患者体位摆放</th>
				<td style="text-align: center">10</td>
				<td>
					<input onchange="saveScore(this,10);" itemId="0800-05-2.2" itemName="检查时患者体位摆放"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
			</tr>
			<tr style="height:32px">
				<th >3</th>
				<th >检查者手法</th>
				<td style="text-align: center">20</td>
				<td>
					<input onchange="saveScore(this,20);" itemId="0800-05-2.3" itemName="检查者手法"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
			</tr>
			<tr style="height:32px">
				<th >4</th>
				<th >结果判定标准</th>
				<td style="text-align: center">10</td>
				<td>
					<input onchange="saveScore(this,10);" itemId="0800-05-2.4" itemName="结果判定标准"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
			</tr>
			<tr style="height:32px">
				<th >5</th>
				<th >测量中注意事项</th>
				<td style="text-align: center">5</td>
				<td>
					<input onchange="saveScore(this,5);" itemId="0800-05-2.5" itemName="测量中注意事项"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
			</tr>
			<tr style="height:32px">
				<th colspan="3" style="text-align: right">合计：</th>
				<th>100</th>
				<td style="text-align: center;"><span id="selfTotalled"></span></td>
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