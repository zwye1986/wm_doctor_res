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

		function subInfo() {
			var itemIdList = $("input");
			for (var i = 0; i < itemIdList.length; i++) {
				if ((itemIdList[i].getAttribute("name") == "self1" || itemIdList[i].getAttribute("name") == "self2")
						&& itemIdList[i].value == "") {
					$(itemIdList[i]).focus();
					top.jboxTip("有输入框未输入数据，请输入数据！");
					return;
				}
			}
			var self1Score = 0;
			var self2Score = 0;
			for (var i = 0; i < itemIdList.length; i++) {
				if (itemIdList[i].getAttribute("name") == "self1") {
					self1Score = Number(self1Score) + Number(itemIdList[i].value);
				}
				if (itemIdList[i].getAttribute("name") == "self2") {
					self2Score = Number(self2Score) + Number(itemIdList[i].value);
				}
			}
			self1Score = self1Score / 491 * 100;
			self2Score = self2Score / 176 * 100;
			if (self1Score >= 100) {
				self1Score = 3;
			} else if (self1Score >= 90) {
				self1Score = 2;
			} else if (self1Score >= 85) {
				self1Score = 1;
			} else {
				self1Score = 0;
			}
			if (self2Score >= 100) {
				self2Score = 4;
			} else if (self2Score >= 90) {
				self2Score = 2;
			} else if (self2Score >= 85) {
				self2Score = 1;
			} else {
				self2Score = 0;
			}

			var inputSelf1;
			var inputSelf2;
			if (${ roleFlag==("baseExpert")}) {
				inputSelf1 = window.parent.frames["jbox-message-iframe"].$("#fubiao1s1");
				inputSelf2 = window.parent.frames["jbox-message-iframe"].$("#fubiao1s2");
			} else if (${roleFlag==("expertLeader")}) {
				inputSelf1 = window.parent.frames["jbox-message-iframe"].$("#fubiao1e1");
				inputSelf2 = window.parent.frames["jbox-message-iframe"].$("#fubiao1e2");
			}

			inputSelf1[0].value = self1Score;
			inputSelf2[0].value = self2Score;
			if (${ roleFlag==("baseExpert")}) {
				window.parent.frames["jbox-message-iframe"].saveScore(inputSelf1[0], self1Score);
				window.parent.frames["jbox-message-iframe"].saveScore(inputSelf2[0], self2Score);
			} else if (${roleFlag==("expertLeader")}) {
				window.parent.frames["jbox-message-iframe"].saveScore4Expert(inputSelf1[0], self1Score);
				window.parent.frames["jbox-message-iframe"].saveScore4Expert(inputSelf2[0], self2Score);
			}
			top.jboxClose();
		}

		//保存自评分
		function saveScore(expl, num) {
			var score = expl.value;
			var itemId = expl.getAttribute("itemId");
			var itemName = expl.getAttribute("itemName");
			var reg = /^\d+(\.\d)?$/;
			var reg1 = /^\d+(\.0)$/;
			if (itemName.startsWith("d")) {
				if (!(score >= 0 && score <= num && reg.test(score))) {
					expl.value = expl.getAttribute("preValue");
					top.jboxTip("评分不能大于" + num + "且只能是正整数或含有一位小数");
					loadDate();
					return;
				}
			}
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
							itenNameScore = '√';
						} else {
							itenNameScore = '×';
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
							itemIdList[i].value = "√";
						} else {
							itemIdList[i].value = "×";
						}
						$(itemIdList[i]).attr("preValue", "${item.score}");
					}
				}
			}
			</c:forEach>
			loadDate();
		});


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
<div class="div_table"   style="overflow: auto;max-height: 570px;">
	<table cellpadding="4" style="width: 1000px">
		<tbody>
		<tr height="34" class="firstRow">
			<th colspan="5">
				<h2 style="font-size:150%">放射科疾病种类/临床技能操作</h2>
			</th>
		</tr>
		<tr style="height:32px;">
			<th colspan="2">放射科疾病种类/临床技能操作</th>
			<th>标准例数</th>
			<th>实际例数</th>
			<th>是否达到标准数（达到划√，未达到划×）</th>
		</tr>
		<tr style="height:32px">
			<th rowspan="8">放射诊断疾病种类</th>
			<th >中枢神经系统影像</th>
			<th>68</th>
			<td>
				<input onchange="saveScore(this,68);" itemId="2200-01-1.1" itemName="k中枢神经系统影像"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
			<td>
				<input itemId="2200-01-1.1" itemName="d中枢神经系统影像"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
			</td>
		</tr>
		<tr style="height:32px">
			<th >头颈及五官影像</th>
			<th>26</th>
			<td>
				<input onchange="saveScore(this,26);" itemId="2200-01-1.2" itemName="k头颈及五官影像"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
			<td>
				<input itemId="2200-01-1.2" itemName="d头颈及五官影像"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
			</td>
		</tr>
		<tr style="height:32px">
			<th >呼吸系统影像</th>
			<th>88</th>
			<td>
				<input onchange="saveScore(this,88);" itemId="2200-01-1.3" itemName="k呼吸系统影像"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
			<td>
				<input itemId="2200-01-1.3" itemName="d呼吸系统影像"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
			</td>
		</tr>
		<tr style="height:32px">
			<th >循环系统影像（心脏、大血管）</th>
			<th>41</th>
			<td>
				<input onchange="saveScore(this,41);" itemId="2200-01-1.4" itemName="k循环系统影像（心脏、大血管）"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
			<td>
				<input itemId="2200-01-1.4" itemName="d循环系统影像（心脏、大血管）"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
			</td>
		</tr>
		<tr style="height:32px">
			<th >乳腺影像</th>
			<th>20</th>
			<td>
				<input onchange="saveScore(this,20);" itemId="2200-01-1.5" itemName="k乳腺影像"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
			<td>
				<input itemId="2200-01-1.5" itemName="d乳腺影像"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
			</td>
		</tr>
		<tr style="height:32px">
			<th >消化系统影像</th>
			<th>117</th>
			<td>
				<input onchange="saveScore(this,117);" itemId="2200-01-1.6" itemName="k消化系统影像"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
			<td>
				<input itemId="2200-01-1.6" itemName="d消化系统影像"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
			</td>
		</tr>
		<tr style="height:32px">
			<th >泌尿生殖系统影像</th>
			<th>56</th>
			<td>
				<input onchange="saveScore(this,56);" itemId="2200-01-1.7" itemName="k泌尿生殖系统影像"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
			<td>
				<input itemId="2200-01-1.7" itemName="d泌尿生殖系统影像"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
			</td>
		</tr>
		<tr style="height:32px">
			<th >骨与关节影像</th>
			<th>78</th>
			<td>
				<input onchange="saveScore(this,78);" itemId="2200-01-1.8" itemName="k骨与关节影像"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
			<td>
				<input itemId="2200-01-1.8" itemName="d骨与关节影像"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
			</td>
		</tr>
		<tr style="height:32px">
			<th rowspan="3">临床技能操作</th>
			<th >消化道造影操作</th>
			<th>120</th>
			<td>
				<input onchange="saveScore(this,120);" itemId="2200-01-2.1" itemName="k消化道造影操作"  name="self2"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
			<td>
				<input itemId="2200-01-2.1" itemName="d消化道造影操作"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
			</td>
		</tr>
		<tr style="height:32px">
			<th >非血管介入操作</th>
			<th>29</th>
			<td>
				<input onchange="saveScore(this,29);" itemId="2200-01-2.2" itemName="k非血管介入操作"  name="self2"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
			<td>
				<input itemId="2200-01-2.2" itemName="d非血管介入操作"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
			</td>
		</tr>
		<tr style="height:32px">
			<th >血管介入操作</th>
			<th>27</th>
			<td>
				<input onchange="saveScore(this,27);" itemId="2200-01-2.3" itemName="k血管介入操作"  name="self2"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
			<td>
				<input itemId="2200-01-2.3" itemName="d血管介入操作"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
			</td>
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