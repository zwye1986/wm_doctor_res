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

		textarea{
			text-indent: 0px;
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
			if (${edit eq 'N'}){
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
				if (itemIdList[i].getAttribute("itemId") == "${item.itemId}" && itemIdList[i].getAttribute("name") == "self") {
					itemIdList[i].value = "${item.score}";
					$(itemIdList[i]).attr("preValue", "${item.score}");
				}
			}
			for (var i = 0; i < itemIdList2.length; i++) {
				if (itemIdList2[i].getAttribute("itemId") == "${item.itemId}" && itemIdList2[i].getAttribute("name") == "reason") {
					var reason= "${item.itemDetailed}";
					var reg = new RegExp("<br/>","g");//g,表示全部替换。
					reason=reason.replace(reg,"\n");
					itemIdList2[i].innerHTML= reason;                }
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

		function printHospitalScore(path) {
			top.jboxExp(null, "<s:url value='/jsres/supervisio/printHospitalScore?userFlow=${userFlow}&subjectFlow=${subjectFlow}&path='/>"+path);
		}

		function subInfo() {
			var itemIdList = $("input");
			// 输入框是否为空
			for (var i = 0; i < itemIdList.length; i++) {
				if (itemIdList[i].getAttribute("name") == "self" && itemIdList[i].value == "") {
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
			var expertTotal = Number($('#selfTotalled').text());
			var url = "<s:url value='/jsres/supervisio/saveHospitalScore'/>";
			var data = {
				"expertTotal": expertTotal,
				"subjectFlow": '${subjectFlow}',
			};
			top.jboxPost(url, data, function (resp) {
				if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
					top.jboxTip(resp);
					top.jboxClose();
				} else {
					top.jboxTip(resp);
				}
			}, null, false);
		}

		function saveSpeReason(expl) {
			var reason = expl.value;
			var reg = new RegExp("\\n","g");//g,表示全部替换。
			reason=reason.replace(reg,"<br/>");
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
			<th colspan="6">
				<h2 style="font-size:150%">临床操作技能床旁教学（对比剂副反应的识别与处理）考核评分表</h2>
			</th>
		</tr>
		<tr height="28" >
			<th style="text-align:left;padding-left: 4px;" colspan="3">
				<div style="margin-top: 8px"><span>培训对象姓名：</span></div>
				<textarea type="text"style="border: 1px solid #bababa;width: 50%;height: 25px;margin-top: -22px;margin-left: 90px"
						  itemId="2200-04-0.3" name="reason" onchange="saveSpeReason(this);"></textarea>
			</th>
			<th style="text-align:left;padding-left: 4px;" colspan="3">所在科室：${deptName}</th>
		</tr>
		<tr height="28" >
			<th style="text-align:left;padding-left: 4px;" colspan="3">培训基地（医院）：${orgName}</th>
			<th style="text-align: left;" colspan="3">省市：${cityName}</th>
		</tr>
		<tr style="height:32px;">
			<th style="width: 15%">考核项目</th>
			<th style="width: 20%" colspan="2">考核内容</th>
			<th style="width: 25%">评分标准</th>
			<th style="width: 20%">标准分</th>
			<th style="width: 20%">得分</th>
		</tr>
		<tr style="height:32px">
			<th>操作前判断（10分）</th>
			<td colspan="2">
				住院医师根据考官设置的场景，快速判断属于何种程度的对比剂副反应
			</td>
			<td>
				判断正确得10分。判断错误不得分，由考官告知其正确的判断
			</td>
			<td style="text-align: center;">10</td>
			<td>
				<input onchange="saveScore(this,10);" itemId="2200-04-1.1" itemName="操作前判断"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
		</tr>
		<tr style="height:32px">
			<th rowspan="2" style="width: 15%">一般反应（14分）</th>
			<td style="width: 17%">临床表现</td>
			<td style="width: 18%">潮红、头痛、恶心、呕吐、荨麻疹</td>
			<td style="width: 18%">至少说出3种常见表现，少答1种扣3分</td>
			<td style="text-align: center;width: 16%">9</td>
			<td style="width: 16%">
				<input onchange="saveScore(this,9);" itemId="2200-04-2.1" itemName="临床表现"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
		</tr>
		<tr style="height:32px">
			<td>处理原则</td>
			<td>一般不需处理，可自行恢复</td>
			<td>答错扣5分</td>
			<td style="text-align: center;">5</td>
			<td>
				<input onchange="saveScore(this,5);" itemId="2200-04-2.2" itemName="处理原则"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
		</tr>
		<tr style="height:32px">
			<th rowspan="2">轻度反应（18分）</th>
			<td>临床表现</td>
			<td>喷嚏、流泪、结膜充血、面部水肿</td>
			<td>至少说出3种常见表现，少1种扣3分。</td>
			<td style="text-align: center;">9</td>
			<td>
				<input onchange="saveScore(this,9);" itemId="2200-04-3.1" itemName="临床表现"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
		</tr>
		<tr style="height:32px">
			<td>处理原则</td>
			<td>卧床休息、吸氧、观察生命体征、肌注或静注地塞米松或非那根</td>
			<td>至少说出3条处理原则，少1条扣3分。</td>
			<td style="text-align: center;">9</td>
			<td>
				<input onchange="saveScore(this,9);" itemId="2200-04-3.2" itemName="处理原则"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
		</tr>
		<tr style="height:32px">
			<th rowspan="2">中度反应（24分）</th>
			<td>临床表现</td>
			<td>反复重度呕吐、眩晕、轻度喉头水肿、轻度支气管痉挛、轻度和暂时性血压下降</td>
			<td>至少说出4种常见表现，少1种扣3分。</td>
			<td style="text-align: center;">12</td>
			<td>
				<input onchange="saveScore(this,12);" itemId="2200-04-4.1" itemName="临床表现"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
		</tr>
		<tr style="height:32px">
			<td>处理原则</td>
			<td>卧床休息、吸氧、密切观察生命体征，及时对症治疗</td>
			<td>至少说出3条处理原则，少1条扣4分。</td>
			<td style="text-align: center;">12</td>
			<td>
				<input onchange="saveScore(this,12);" itemId="2200-04-4.2" itemName="处理原则"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
		</tr>
		<tr style="height:32px">
			<th rowspan="2">重度反应（24分）</th>
			<td>临床表现</td>
			<td>呼吸困难、意识不清、休克、惊厥、心律失常、心跳骤停</td>
			<td>至少说出4种常见表现，少1种扣4分。</td>
			<td style="text-align: center;">16</td>
			<td>
				<input onchange="saveScore(this,16);" itemId="2200-04-5.1" itemName="临床表现"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
		</tr>
		<tr style="height:32px">
			<td>处理原则</td>
			<td>立即采取气管切开、心肺复苏等急救措施</td>
			<td>少1条扣4分。</td>
			<td style="text-align: center;">8</td>
			<td>
				<input onchange="saveScore(this,8);" itemId="2200-04-5.2" itemName="处理原则"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
		</tr>
		<tr style="height:32px">
			<th>人文关怀及总体评价（10分）</th>
			<td colspan="2">操作规范、熟练，整个操作过程体现人文关怀</td>
			<td>操作规范、熟练5分；整个操作过程体现人文关怀5分</td>
			<td style="text-align: center;">10</td>
			<td>
				<input onchange="saveScore(this,10);" itemId="2200-04-6.1" itemName="人文关怀及总体评价"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
		</tr>
		<tr style="height:32px;">
			<td colspan="4" style="text-align: right;">总分：</td>
			<td style="text-align: center;">100</td>
			<td style="text-align: center;"><span id="selfTotalled"></span></td>
		</tr>
		<tr style="height: 50px">
			<th style="text-align:left">
				&#12288;&#12288;评价人：${specialistName}
			</th>
			<th colspan="3">
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

<div class="button">
	<input class="btn_green" type="button" value="打&#12288;印" onclick="printHospitalScore('evaluationInfo_2200_lc');"/>&#12288;
	<c:if test="${edit ne 'N'}">
		<input class="btn_green" type="button" value="提&#12288;交" onclick="subInfo();"/>&#12288;
	</c:if>
</div>
</body>
</html>