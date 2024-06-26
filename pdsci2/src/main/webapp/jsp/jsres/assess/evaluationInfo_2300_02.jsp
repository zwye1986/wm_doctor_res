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
			<c:forEach items="${scoreList}" var="item" varStatus="status" >
			//获得值
			for (var i = 0; i < itemIdList.length; i++) {
				if (itemIdList[i].getAttribute("itemName") == "${item.itemName}" && itemIdList[i].getAttribute("name") == "self") {
					itemIdList[i].value = "${item.score}";
					$(itemIdList[i]).attr("preValue", "${item.score}");
				}
			}
			//获取扣分原因
			for (var i = 0; i < itemIdList2.length; i++) {
				if (itemIdList2[i].getAttribute("itemId") == "${item.itemId}" && itemIdList2[i].getAttribute("name") == "reason") {
					itemIdList2[i].value = "${item.itemDetailed}";
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
				if ((itemIdList[i].getAttribute("name") == "self") && itemIdList[i].value == "") {
					$(itemIdList[i]).focus();
					top.jboxTip("有输入框未输入数据，请输入数据！");
					return;
				}
			}
			var expertTotal = Number($('#selfTotalled').text());
			var input;
			if (${ roleFlag==("baseExpert")}) {
				input = window.parent.frames["jbox-message-iframe"].$("#fubiao2s");
			} else if (${roleFlag==("expertLeader")}) {
				input = window.parent.frames["jbox-message-iframe"].$("#fubiao2e");
			}
			if (expertTotal >= 90) {
				expertTotal = 10;
			} else if (expertTotal >= 80) {
				expertTotal = 3;
			} else if (expertTotal >= 70) {
				expertTotal = 2;
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

		function saveSpeReason(expl) {
			var reason = expl.value;
			reason = encodeURIComponent(reason);
			var itemId = expl.getAttribute("itemId");
			var itemName = expl.getAttribute("itemName");
			var url = "<s:url value='/jsres/supervisio/saveScheduleDetailed'/>";
			var data = {
				"itemId": itemId,
				"itemName": itemName,
				"itemDetailed": reason,
				"orgFlow": '${orgFlow}',
				"orgName": '${orgName}',
				"speId": '${speId}',
				"subjectFlow": '${subjectFlow}',
				"userFlow": '${userFlow}',
				"roleFlag": '${roleFlag}',
				"fileRoute": '${fileRoute}'
			};
			top.jboxPost(url, data, function (resp) {
				if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
					top.jboxTip(resp);
				} else {
					top.jboxTip(resp);
				}
			}, null, false);
		}
	</script>
</head>
<body>
<div class="div_table" style="overflow: auto;max-height: 570px;">
	<table cellpadding="4" style="width: 1000px">
		<tbody>
		<tr height="34" class="firstRow">
			<th colspan="6">
				<h2 style="font-size:150%">指导医师教学查房评分表</h2>
			</th>
		</tr>
		<tr height="28" >
			<th style="text-align:left;padding-left: 4px;" colspan="3">专业基地：超声</th>
			<th style="text-align:left;padding-left: 4px;" colspan="3">培训基地（医院）：${orgName}</th>
		</tr>
		<tr height="28" >
			<th style="text-align:left;padding-left: 4px;" colspan="3">指导医师姓名：${teacherName}</th>
			<th style="text-align:left;padding-left: 4px;" colspan="3">职称：${speSkillName}</th>
		</tr>
		<tr height="28" >
			<th style="text-align:left;padding-left: 4px;" colspan="3">受培训情况：省/直辖市级</th>
			<th style="text-align:left;padding-left: 4px;" colspan="3">证书等：</th>
		</tr>
		<tr height="28" >
			<th style="text-align:left;padding-left: 4px;" colspan="3">患者病历号：${userCaseId}</th>
			<th style="text-align:left;padding-left: 4px;" colspan="3">疾病名称：${diseaseName}</th>
		</tr>
		<tr height="28" >
			<th style="text-align:left;padding-left: 4px;" colspan="3">学员姓名：</th>
			<th style="text-align:left;padding-left: 4px;" colspan="3">规2/3: </th>
		</tr>
		<tr height="28" >
			<th style="text-align:left;padding-left: 4px;" colspan="3">本科学员：</th>
			<th style="text-align:left;padding-left: 4px;" colspan="3">社会人：</th>
		</tr>
		<tr height="28" >
			<th style="text-align:left;padding-left: 4px;" colspan="6">外单位者的单位名称：</th>
		</tr>
		<tr style="height:32px;">
			<th style="width: 20%">考核项目</th>
			<th style="width: 20%" colspan="2">考核内容</th>
			<th style="width: 20%">标准分</th>
			<th style="width: 20%">得分</th>
			<th style="width: 20%">扣分原因</th>
		</tr>
		<tr style="height:32px">
			<th rowspan="5">阅片（40分）</th>
			<td colspan="2">1.准备工作充分、认真</td>
			<td style="text-align: center">8</td>
			<td><input onchange="saveScore(this,8);" itemId="2300-02-1.1" itemName="1.准备工作充分、认真"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  /></td>
			<td>
				<textarea onchange="saveSpeReason(this);" itemId="2300-02-1.1" itemName="1.准备工作充分、认真" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]"></textarea>
			</td>
		</tr>
		<tr style="height:32px">
			<td colspan="2">2.指导学员全面、规范地阅片，包括动态图像</td>
			<td style="text-align: center">8</td>
			<td><input onchange="saveScore(this,8);" itemId="2300-02-1.2" itemName="2.指导学员全面、规范地阅片，包括动态图像"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  /></td>
			<td>
				<textarea onchange="saveSpeReason(this);" itemId="2300-02-1.2" itemName="2.指导学员全面、规范地阅片，包括动态图像" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]"></textarea>
			</td>
		</tr>
		<tr style="height:32px">
			<td colspan="2">3.指导学员如何结合其它影像学资料、实验室检查及相关病史</td>
			<td style="text-align: center">8</td>
			<td><input onchange="saveScore(this,8);" itemId="2300-02-1.3" itemName="3.指导学员如何结合其它影像学资料、实验室检查及相关病史"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  /></td>
			<td>
				<textarea onchange="saveSpeReason(this);" itemId="2300-02-1.3" itemName="3.指导学员如何结合其它影像学资料、实验室检查及相关病史" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]"></textarea>
			</td>
		</tr>
		<tr style="height:32px">
			<td colspan="2">4.培养学员临床思维、分析问题的方法与思路、诊断及鉴别诊断的能力</td>
			<td style="text-align: center">8</td>
			<td><input onchange="saveScore(this,8);" itemId="2300-02-1.4" itemName="4.培养学员临床思维、分析问题的方法与思路、诊断及鉴别诊断的能力"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  /></td>
			<td>
				<textarea onchange="saveSpeReason(this);" itemId="2300-02-1.4" itemName="4.培养学员临床思维、分析问题的方法与思路、诊断及鉴别诊断的能力" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]"></textarea>
			</td>
		</tr>
		<tr style="height:32px">
			<td colspan="2">5.结合病例，联系理论基础讲解,并介绍相关医学新进展新技术</td>
			<td style="text-align: center">8</td>
			<td><input onchange="saveScore(this,8);" itemId="2300-02-1.5" itemName="5.结合病例，联系理论基础讲解,并介绍相关医学新进展新技术"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  /></td>
			<td>
				<textarea onchange="saveSpeReason(this);" itemId="2300-02-1.5" itemName="5.结合病例，联系理论基础讲解,并介绍相关医学新进展新技术" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]"></textarea>
			</td>
		</tr>
		<tr style="height:32px">
			<th rowspan="4">技能操作（40分）</th>
			<td colspan="2">1.操作前指导学员如何询问病史、与患者及家属沟通</td>
			<td style="text-align: center">10</td>
			<td><input onchange="saveScore(this,10);" itemId="2300-02-1.6" itemName="1.操作前指导学员如何询问病史、与患者及家属沟通"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  /></td>
			<td>
				<textarea onchange="saveSpeReason(this);" itemId="2300-02-1.6" itemName="1.操作前指导学员如何询问病史、与患者及家属沟通" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]"></textarea>
			</td>
		</tr>
		<tr style="height:32px">
			<td colspan="2">2.指导学员调节仪器</td>
			<td style="text-align: center">10</td>
			<td><input onchange="saveScore(this,10);" itemId="2300-02-1.7" itemName="2.指导学员调节仪器"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  /></td>
			<td>
				<textarea onchange="saveSpeReason(this);" itemId="2300-02-1.7" itemName="2.指导学员调节仪器" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]"></textarea>
			</td>
		</tr>
		<tr style="height:32px">
			<td colspan="2">3.及时指出学员操作中出现的问题，并予指导与纠正</td>
			<td style="text-align: center">10</td>
			<td><input onchange="saveScore(this,10);" itemId="2300-02-1.8" itemName="3.及时指出学员操作中出现的问题，并予指导与纠正"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  /></td>
			<td>
				<textarea onchange="saveSpeReason(this);" itemId="2300-02-1.8" itemName="3.及时指出学员操作中出现的问题，并予指导与纠正" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]"></textarea>
			</td>
		</tr>
		<tr style="height:32px">
			<td colspan="2">4.学员操作结束后，提问并讲解</td>
			<td style="text-align: center">10</td>
			<td><input onchange="saveScore(this,10);" itemId="2300-02-1.9" itemName="4.学员操作结束后，提问并讲解"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  /></td>
			<td>
				<textarea onchange="saveSpeReason(this);" itemId="2300-02-1.9" itemName="4.学员操作结束后，提问并讲解" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]"></textarea>
			</td>
		</tr>
		<tr style="height:32px">
			<th rowspan="2">诊断报告（15分）</th>
			<td colspan="2">1.指导学员做出准确的诊断及鉴别诊断</td>
			<td style="text-align: center">7</td>
			<td><input onchange="saveScore(this,7);" itemId="2300-02-1.10" itemName="1.指导学员做出准确的诊断及鉴别诊断"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  /></td>
			<td>
				<textarea onchange="saveSpeReason(this);" itemId="2300-02-1.10" itemName="1.指导学员做出准确的诊断及鉴别诊断" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]"></textarea>
			</td>
		</tr>
		<tr style="height:32px">
			<td colspan="2">2.指导书写超声诊断报告</td>
			<td style="text-align: center">8</td>
			<td><input onchange="saveScore(this,8);" itemId="2300-02-1.11" itemName="2.指导书写超声诊断报告"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  /></td>
			<td>
				<textarea onchange="saveSpeReason(this);" itemId="2300-02-1.11" itemName="2.指导书写超声诊断报告" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]"></textarea>
			</td>
		</tr>
		<tr style="height:32px">
			<th>指导医师总体印象（5分）</th>
			<td colspan="2">衣着、谈吐、耐心、同情心等</td>
			<td style="text-align: center">5</td>
			<td><input onchange="saveScore(this,5);" itemId="2300-02-1.12" itemName="衣着、谈吐、耐心、同情心等"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  /></td>
			<td>
				<textarea onchange="saveSpeReason(this);" itemId="2300-02-1.12" itemName="衣着、谈吐、耐心、同情心等" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]"></textarea>
			</td>
		</tr>
		<tr style="height:32px;">
			<td colspan="3" style="text-align: right;">总分：</td>
			<td style="text-align: center;">100</td>
			<td style="text-align: center;"><span id="selfTotalled"></span></td>
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