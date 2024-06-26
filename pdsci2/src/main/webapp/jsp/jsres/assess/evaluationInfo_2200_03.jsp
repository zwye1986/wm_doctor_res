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
				if ((itemIdList[i].getAttribute("name") == "self") && itemIdList[i].value == "") {
					$(itemIdList[i]).focus();
					top.jboxTip("有输入框未输入数据，请输入数据！");
					return;
				}
			}
			var expertTotal = Number($('#selfTotalled').text());
			var input;
			if (${ roleFlag==("baseExpert")}) {
				input = window.parent.frames["jbox-message-iframe"].$("#fubiao3s");
			} else if (${roleFlag==("expertLeader")}) {
				input = window.parent.frames["jbox-message-iframe"].$("#fubiao3e");
			}
			if (expertTotal >= "90") {
				expertTotal = 4;
			} else if (expertTotal >= "80") {
				expertTotal = 3;
			} else if (expertTotal >= "70") {
				expertTotal = 2;
			} else if (expertTotal >= "60") {
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
	</script>
</head>
<body>
<div class="div_table" style="overflow: auto;max-height: 570px;">
	<table cellpadding="4" style="width: 1000px">
		<tbody>
		<tr height="34" class="firstRow">
			<th colspan="5">
				<h2 style="font-size:150%">住院医师临床阅片评分表</h2>
			</th>
		</tr>
		<tr height="28" >
			<th style="text-align:left;padding-left: 4px;" colspan="2">培训对象姓名：${userName}</th>
			<th style="text-align:left;padding-left: 4px;" >所在科室：${speName}</th>
			<th style="text-align:left;padding-left: 4px;" colspan="2">培训基地（医院）：${orgName}</th>
		</tr>
		<tr style="height:32px;">
			<th style="width: 15%">考核项目</th>
			<th style="width: 20%" >考核内容</th>
			<th style="width: 25%">评分标准</th>
			<th style="width: 20%">标准分</th>
			<th style="width: 20%">得分</th>
		</tr>
		<tr style="height:32px">
			<th rowspan="3">一、核对一般项目</th>
			<td>
				1．核对患者姓名、性别、年龄、影像号
			</td>
			<td>
				1．遗漏1项扣1分
			</td>
			<td style="text-align: center;" rowspan="3">10</td>
			<td rowspan="3">
				<input onchange="saveScore(this,10);" itemId="2200-03-1.1" itemName="核对一般项目"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
		</tr>
		<tr style="height:32px">
			<td>2．核对检查日期、检查方法</td>
			<td style="text-align: center">2．遗漏1项扣1分</td>
		</tr>
		<tr style="height:32px">
			<td>3．核对检查部位和左、右</td>
			<td style="text-align: center">3．遗漏1项扣2分</td>
		</tr>
		<tr style="height:32px">
			<th rowspan="4">二、征象描述及分析</th>
			<td>1．影像观察全面，注意到重要的阳性征象和阴性征象</td>
			<td style="text-align: center; ">1．观察不全面扣1-5分；遗漏重要阳性征象扣1-5分；遗漏重要阴性征象扣1-5分</td>
			<td style="text-align: center;" rowspan="4">35</td>
			<td rowspan="4">
				<input onchange="saveScore(this,35);" itemId="2200-03-2.1" itemName="征象描述及分析"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
		</tr>
		<tr style="height:32px">
			<td>2．征象描述客观，专业术语运用恰当</td>
			<td style="text-align: center; ">2．征象描述不客观扣1-5分；未能使用专业术语扣1-5分</td>
		</tr>
		<tr style="height:32px">
			<td>
				3．分析征象条理性强，语言精炼
			</td>
			<td style="text-align: center; ">
				3．分析征象缺乏条理性，扣1-2分；语言过繁或过简或用诊断用语扣1-3分
			</td>
		</tr>
		<tr style="height:32px">
			<td>
				4．分析过程能结合临床资料和病理改变
			</td>
			<td style="text-align: center; ">
				4．分析过程未能结合临床资料扣1-2.5分；未能结合病理改变扣1-2.5分
			</td>
		</tr>
		<tr style="height:32px">
			<th>三、报告书写质量</th>
			<td>报告书写格式规范，字迹工整，无错别字，征象描述细致，条理清楚，确认签名</td>
			<td style="text-align: center; ">
				报告书写格式不规范扣1-4分；字迹不工整扣1-4分，出现1个错别字扣1分（最多扣2分）；
				征象描述不确切扣1-4分；语句不通顺扣1-3分；未签名扣1-3分
			</td>
			<td style="text-align: center;">20</td>
			<td>
				<input onchange="saveScore(this,20);" itemId="2200-03-3.1" itemName="报告书写质量"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
		</tr>
		<tr style="height:32px">
			<th>四、诊断</th>
			<td>诊断依据充分，结论准确，主次分明</td>
			<td style="text-align: center; ">
				主要诊断错误扣1-3分，欠准确1-2分；主要并发症错误或遗漏扣1-3分；
				次要诊断有错误或遗漏扣0.5-1分；诊断主次顺序有错误扣0.5-1分
			</td>
			<td style="text-align: center;">10</td>
			<td>
				<input onchange="saveScore(this,10);" itemId="2200-03-4.1" itemName="诊断"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
		</tr>
		<tr style="height:32px">
			<th>五、鉴别诊断</th>
			<td>鉴别诊断合理，至少提出2个需鉴别的疾病</td>
			<td style="text-align: center; ">鉴别诊断病名不恰当扣1-2.5分/个；鉴别要点不确切扣1-2.5分/个</td>
			<td style="text-align: center;">10</td>
			<td>
				<input onchange="saveScore(this,10);" itemId="2200-03-5.1" itemName="鉴别诊断"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
		</tr>
		<tr style="height:32px">
			<th>六、回答问题</th>
			<td>考官至少提出4个相关问题（主要涉及考生在阅片过程中出现的问题）</td>
			<td style="text-align: center; ">回答错误1个问题扣1-2.5分</td>
			<td style="text-align: center;">10</td>
			<td>
				<input onchange="saveScore(this,10);" itemId="2200-03-6.1" itemName="回答问题"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
		</tr>
		<tr style="height:32px">
			<th>七、考核用时</th>
			<td>30分钟</td>
			<td style="text-align: center; ">考核用时超过30分钟扣1-5分</td>
			<td style="text-align: center;">5</td>
			<td>
				<input onchange="saveScore(this,5);" itemId="2200-03-7.1" itemName="考核用时"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
		</tr>
		<tr style="height:32px;">
			<td colspan="3" style="text-align: right;">总分：</td>
			<td style="text-align: center;">100</td>
			<td style="text-align: center;"><span id="selfTotalled"></span></td>
		</tr>
		<tr style="height:32px;">
			<th>备注</th>
			<th colspan="4">X线平片/CT/MRI（胸部、腹部、骨关节、头颅等）任选1-3片</th>
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
    <input class="btn_green" type="button" value="提&#12288;交" onclick="subInfo();" />&#12288;
    <input class="btn_green" type="button" id="zancun" value="暂&#12288存" onclick="zancun();"/>&#12288;
	<input class="btn_green" type="button" value="取&#12288;消" onclick="top.jboxClose();" />

        <script type="text/javascript">
            function zancun() {
                top.jboxTip("暂存成功！");
                $('#zancun').hide();
            }
        </script>
</div>
</body>
</html>