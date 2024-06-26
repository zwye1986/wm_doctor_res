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


		function subInfo() {
			var itemIdList = $("input");
			for (var i = 0; i < itemIdList.length; i++) {
				if ((itemIdList[i].getAttribute("name") == "self") && itemIdList[i].value == "") {
					$(itemIdList[i]).focus();
					top.jboxTip("有输入框未输入数据，请输入数据！");
					return;
				}
			}
			var expertTotal = Number($('#selfTotalled').text());
			var input;
			if (${ roleFlag==("baseExpert")}) {
				input = window.parent.frames["jbox-message-iframe"].$("#fubiao4");
			} else if (${roleFlag==("expertLeader")}) {
				input = window.parent.frames["jbox-message-iframe"].$("#fubiao4Expert");
			}
			if (expertTotal >= 90) {
				expertTotal = 8;
			} else if (expertTotal >= 80) {
				expertTotal = 5;
			} else if (expertTotal >= 70) {
				expertTotal = 3;
			} else if (expertTotal >= 60) {
				expertTotal = 1;
			} else {
				expertTotal = 0;
			}
			input[0].value = expertTotal;
			if (${ roleFlag==("baseExpert")}) {
				window.parent.frames["jbox-message-iframe"].saveScore(input[0], expertTotal);
			} else if (${roleFlag==("expertLeader")}) {
				window.parent.frames["jbox-message-iframe"].saveScore4Expert(input[0], expertTotal);
			}
			top.jboxClose();
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
			<th colspan="5">
				<h2 style="font-size:150%">住院医师临床技能操作考核（换药）评分表</h2>
			</th>
		</tr>
		<tr height="28" >
			<th style="text-align:left;padding-left: 4px;" >培训对象姓名：${userName}</th>
			<th></th>
			<th style="text-align:left;padding-left: 4px;" >所在科室：${speName}</th>
			<th></th>
			<th style="text-align:left;padding-left: 4px;" >培训基地（医院）：${orgName}</th>
		</tr>
		<tr style="height:32px;">
			<th style="width: 20%">考核项目</th>
			<th style="width: 20%">考核内容</th>
			<th style="width: 20%">评分标准</th>
			<th style="width: 20%">标准分</th>
			<th style="width: 20%">得分</th>
		</tr>
		<tr style="height:32px">
			<th rowspan="3">准备（25分）</th>
			<td>
				戴帽子口罩
			</td>
			<td style="text-align: center;">
				戴帽子口罩3分<br>
				戴法正确2分
			</td>
			<td style="text-align: center;">5</td>
			<td>
				<input onchange="saveScore(this,5);" itemId="1000-04-1.1" itemName="戴帽子口罩"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
		</tr>
		<tr style="height:32px">
			<td>
				洗手、查看伤口
			</td>
			<td style="text-align: center;">
				洗手2分<br>
				揭胶布2分<br>
				揭敷料2分
			</td>
			<td style="text-align: center;">6</td>
			<td>
				<input onchange="saveScore(this,6);" itemId="1000-04-1.2" itemName="洗手、查看伤口"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
		</tr>
		<tr style="height:32px">
			<td>
				洗手、物品准备
			</td>
			<td style="text-align: center;">
				洗手2分<br>
				检查物品消毒有效期3分<br>
				取物数量适当3分<br>
				取物顺序3分<br>
				持物钳手法3分
			</td>
			<td style="text-align: center;">14</td>
			<td>
				<input onchange="saveScore(this,14);" itemId="1000-04-1.3" itemName="洗手、物品准备"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
		</tr>
		<tr style="height:32px">
			<th rowspan="8">换药（50分）</th>
			<td>
				换药碗（盘分）
			</td>
			<td style="text-align: center;">
				拿至床边正确3分<br>
				位置合适2分
			</td>
			<td style="text-align: center;">5</td>
			<td>
				<input onchange="saveScore(this,5);" itemId="1000-04-2.1" itemName="换药碗（盘分）"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
		</tr>
		<tr style="height:32px">
			<td>
				镊子的用法
			</td>
			<td style="text-align: center;">
				持镊方法3分<br>
				镊子尖向下3分<br>
				区分两把镊子4分
			</td>
			<td style="text-align: center;">10</td>
			<td>
				<input onchange="saveScore(this,10);" itemId="1000-04-2.2" itemName="镊子的用法"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
		</tr>
		<tr style="height:32px">
			<td>
				消毒
			</td>
			<td style="text-align: center;">
				范围4分<br>
				顺序4分<br>
				无空白区2分
			</td>
			<td style="text-align: center;">10</td>
			<td>
				<input onchange="saveScore(this,10);" itemId="1000-04-2.3" itemName="消毒"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
		</tr>
		<tr style="height:32px">
			<td>
				清洁伤口
			</td>
			<td style="text-align: center;">
				消毒后蘸洗2分<br>
				盐水蘸洗正确3分
			</td>
			<td style="text-align: center;">5</td>
			<td>
				<input onchange="saveScore(this,5);" itemId="1000-04-2.4" itemName="清洁伤口"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
		</tr>
		<tr style="height:32px">
			<td rowspan="3">
				盖纱布固定
			</td>
			<td style="text-align: center;">
				纱布毛光面方向正确5分
			</td>
			<td style="text-align: center;">5</td>
			<td>
				<input onchange="saveScore(this,5);" itemId="1000-04-2.5" itemName="盖纱布固定"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
		</tr>
		<tr style="height:32px">
			<td style="text-align: center;">
				纱布覆盖范围、层数正确5分
			</td>
			<td style="text-align: center;">5</td>
			<td>
				<input onchange="saveScore(this,5);" itemId="1000-04-2.6" itemName="盖纱布固定"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
		</tr>
		<tr style="height:32px">
			<td style="text-align: center;">
				胶布长短适宜2分<br>
				方向、位置适当3分
			</td>
			<td style="text-align: center;">5</td>
			<td>
				<input onchange="saveScore(this,5);" itemId="1000-04-2.7" itemName="盖纱布固定"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
		</tr>
		<tr style="height:32px">
			<td>
				敷料用品的处理
			</td>
			<td style="text-align: center;">
				一次性物品丢至黄色垃圾袋2分<br>
				金属用品浸泡1分<br>
				洗手2分
			</td>
			<td style="text-align: center;">5</td>
			<td>
				<input onchange="saveScore(this,5);" itemId="1000-04-2.8" itemName="敷料用品的处理"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
		</tr>
		<tr style="height:32px">
			<th rowspan="2">其他（15分）</th>
			<td>
				整个操作熟练程度
			</td>
			<td style="text-align: center;">
				过程熟练5分<br>
				过程不熟练3分
			</td>
			<td style="text-align: center;">5</td>
			<td>
				<input onchange="saveScore(this,5);" itemId="1000-04-3.1" itemName="整个操作熟练程度"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
		</tr>
		<tr style="height:32px">
			<td>
				人文、沟通及回答提问
			</td>
			<td style="text-align: center;">
				有人文关怀、沟通5分<br>
				答题5分
			</td>
			<td style="text-align: center;">10</td>
			<td>
				<input onchange="saveScore(this,10);" itemId="1000-04-3.2" itemName="人文、沟通及回答提问"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
		</tr>
		<tr style="height:32px">
			<th rowspan="3">无菌观念（10分）</th>
			<td rowspan="3">
				有、无污染
			</td>
			<td style="text-align: center;">
				无污染10分<br>
				有污染但补救8分
			</td>
			<td style="text-align: center;" rowspan="3">10</td>
			<td rowspan="3">
				<input onchange="saveScore(this,10);" itemId="1000-04-4.1" itemName="无菌观念"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
		</tr>
		<tr style="height:32px">
			<td style="text-align: center;">
				有污染无补救0分
			</td>
		</tr>
		<tr style="height:32px">
			<td style="text-align: center;">
				严重违反无菌原则扣50分
			</td>
		</tr>
		<tr style="height:32px;">
			<td colspan="3" style="text-align: right;">合计：</td>
			<td style="text-align: center;">100</td>
			<td style="text-align: center;"><span id="selfTotalled"></span></td>
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
				<fmt:formatDate value="${scheduleDate}" pattern="yyyy 年 MM 月 dd 日"/>&#12288;&#12288;
				<input id="evaluationDate"
					   value="<fmt:formatDate value="${evaluationDate}" pattern="yyyy-MM-dd" />"
					   hidden>
			</th>
		</tr>
		</tbody>
	</table>
</div>

<c:if test="${(GlobalConstant.USER_LIST_GLOBAL ne roleFlag) && (isRead ne GlobalConstant.RECORD_STATUS_Y) && (editFlag ne 'N')}">
	<div class="button">
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