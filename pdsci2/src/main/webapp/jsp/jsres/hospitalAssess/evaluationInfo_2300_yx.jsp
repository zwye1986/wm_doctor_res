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
			//获取扣分原因
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
				<h2 style="font-size:150%">影像诊断报告书写指导教学考核评分表</h2>
			</th>
		</tr>
		<tr height="28" >
			<th style="text-align:left;padding-left: 4px;" colspan="4">
				<div style="margin-top: 8px"><span>培训对象姓名：</span></div>
				<textarea type="text"style="border: 1px solid #bababa;width: 50%;height: 25px;margin-top: -22px;margin-left: 90px"
						  itemId="2300-03-0.3" name="reason" onchange="saveSpeReason(this);"></textarea>
			</th>
			<th style="text-align:left;padding-left: 4px;" colspan="2">所在科室：${deptName}</th>
		</tr>
		<tr height="28" >
			<th style="text-align:left;padding-left: 4px;" colspan="4">培训基地（医院）：${orgName}</th>
			<th style="text-align:left;padding-left: 4px;" colspan="2">省市：${cityName}</th>
		</tr>
		<tr style="height:32px;">
			<th style="width: 20%">考核内容</th>
			<th style="width: 20%" colspan="2">评分标准</th>
			<th style="width: 20%">分值</th>
			<th style="width: 20%">得分</th>
			<th style="width: 20%">扣分原因</th>
		</tr>
		<tr style="height:32px">
			<th rowspan="3">一、一般信息（10分）</th>
			<td>1、医院名称、病人姓名、性别、年龄、病历号（或住院号）、影像号、仪器型号、联系方式等</td>
			<td rowspan="3">每缺少一项扣1分</td>
			<td style="text-align: center" rowspan="3">10</td>
			<td rowspan="3"><input onchange="saveScore(this,10);" itemId="2300-03-1.1" itemName="1、医院名称、病人姓名、性别、年龄、病历号（或住院号）、影像号、仪器型号、联系方式等"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  /></td>
			<td rowspan="3">
				<textarea onchange="saveSpeReason(this);" itemId="2300-03-1.1" itemName="1、医院名称、病人姓名、性别、年龄、病历号（或住院号）、影像号、仪器型号、联系方式等" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]"></textarea>
			</td>
		</tr>
		<tr style="height:32px">
			<td>2、临床诊断、检查部位、检查日期等</td>
		</tr>
		<tr style="height:32px">
			<td>3、操作、报告医师签名，审核医师签名</td>
		</tr>
		<tr style="height:32px">
			<th rowspan="4">二、超声所见（40分）</th>
			<td>1、书写规范、词句流畅、逻辑性强</td>
			<td>扣1～15分</td>
			<td style="text-align: center">15</td>
			<td><input onchange="saveScore(this,15);" itemId="2300-03-1.2" itemName="1、书写规范、词句流畅、逻辑性强"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  /></td>
			<td>
				<textarea onchange="saveSpeReason(this);" itemId="2300-03-1.2" itemName="1、书写规范、词句流畅、逻辑性强" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]"></textarea>
			</td>
		</tr>
		<tr style="height:32px">
			<td>2、定位、病变征象（包括彩超），全面、顺序等</td>
			<td>扣1～15分</td>
			<td style="text-align: center">15</td>
			<td><input onchange="saveScore(this,15);" itemId="2300-03-1.3" itemName="2、定位、病变征象（包括彩超），全面、顺序等"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  /></td>
			<td>
				<textarea onchange="saveSpeReason(this);" itemId="2300-03-1.3" itemName="2、定位、病变征象（包括彩超），全面、顺序等" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]"></textarea>
			</td>
		</tr>
		<tr style="height:32px">
			<td>3、重要的阴性征象</td>
			<td>扣1～5分（如无则不扣分）</td>
			<td style="text-align: center">5</td>
			<td><input onchange="saveScore(this,5);" itemId="2300-03-1.4" itemName="3、重要的阴性征象"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  /></td>
			<td>
				<textarea onchange="saveSpeReason(this);" itemId="2300-03-1.4" itemName="3、重要的阴性征象" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]"></textarea>
			</td>
		</tr>
		<tr style="height:32px">
			<td>4、特殊情况说明：如半坐位、站位、胸膝位等，经直肠检查等</td>
			<td>扣1～5分（如无则不扣分）</td>
			<td style="text-align: center">5</td>
			<td><input onchange="saveScore(this,5);" itemId="2300-03-1.5" itemName="4、特殊情况说明：如半坐位、站位、胸膝位等，经直肠检查等"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  /></td>
			<td>
				<textarea onchange="saveSpeReason(this);" itemId="2300-03-1.5" itemName="4、特殊情况说明：如半坐位、站位、胸膝位等，经直肠检查等" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]"></textarea>
			</td>
		</tr>
		<tr style="height:32px">
			<th rowspan="5">三、超声诊断（30分）</th>
			<td>1、诊断明确；如不肯定时，给出数个诊断并根据可能性予与排名</td>
			<td>扣1～10分</td>
			<td style="text-align: center">10</td>
			<td><input onchange="saveScore(this,10);" itemId="2300-03-1.6" itemName="1、诊断明确；如不肯定时，给出数个诊断并根据可能性予与排名"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  /></td>
			<td>
				<textarea onchange="saveSpeReason(this);" itemId="2300-03-1.6" itemName="1、诊断明确；如不肯定时，给出数个诊断并根据可能性予与排名" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]"></textarea>
			</td>
		</tr>
		<tr style="height:32px">
			<td>2、必要时，给出鉴别诊断</td>
			<td>扣1～5分（如无则不扣分）</td>
			<td style="text-align: center">5</td>
			<td><input onchange="saveScore(this,5);" itemId="2300-03-1.7" itemName="2、必要时，给出鉴别诊断"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  /></td>
			<td>
				<textarea onchange="saveSpeReason(this);" itemId="2300-03-1.7" itemName="2、必要时，给出鉴别诊断" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]"></textarea>
			</td>
		</tr>
		<tr style="height:32px">
			<td>3、必要时，结合临床、实验室等</td>
			<td>扣1～5分（如无则不扣分）</td>
			<td style="text-align: center">5</td>
			<td><input onchange="saveScore(this,5);" itemId="2300-03-1.8" itemName="3、必要时，结合临床、实验室等"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  /></td>
			<td>
				<textarea onchange="saveSpeReason(this);" itemId="2300-03-1.8" itemName="3、必要时，结合临床、实验室等" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]"></textarea>
			</td>
		</tr>
		<tr style="height:32px">
			<td>4、复查时，与以前的检查进行比较 </td>
			<td>扣1～5分（如无则不扣分）</td>
			<td style="text-align: center">5</td>
			<td><input onchange="saveScore(this,5);" itemId="2300-03-1.9" itemName="4、复查时，与以前的检查进行比较 "  name="self"  class="input" type="text"  style="height:20px; text-align: center"  /></td>
			<td>
				<textarea onchange="saveSpeReason(this);" itemId="2300-03-1.9" itemName="4、复查时，与以前的检查进行比较 " name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]"></textarea>
			</td>
		</tr>
		<tr style="height:32px">
			<td>5、必要时提出随访或其他检查的建议</td>
			<td>扣1～5分（如无则不扣分）</td>
			<td style="text-align: center">5</td>
			<td><input onchange="saveScore(this,5);" itemId="2300-03-1.10" itemName="5、必要时提出随访或其他检查的建议"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  /></td>
			<td>
				<textarea onchange="saveSpeReason(this);" itemId="2300-03-1.10" itemName="5、必要时提出随访或其他检查的建议" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]"></textarea>
			</td>
		</tr>
		<tr style="height:32px">
			<th rowspan="3">四、图像质量（20分）</th>
			<td>1、病变相关的代表性图像</td>
			<td>扣1～10分</td>
			<td style="text-align: center">10</td>
			<td><input onchange="saveScore(this,10);" itemId="2300-03-1.11" itemName="1、病变相关的代表性图像"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  /></td>
			<td>
				<textarea onchange="saveSpeReason(this);" itemId="2300-03-1.11" itemName="1、病变相关的代表性图像" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]"></textarea>
			</td>
		</tr>
		<tr style="height:32px">
			<td>2、图像质量好、清晰，大小、亮度及对比合适</td>
			<td>扣1～5分</td>
			<td style="text-align: center">5</td>
			<td><input onchange="saveScore(this,5);" itemId="2300-03-1.12" itemName="2、图像质量好、清晰，大小、亮度及对比合适"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  /></td>
			<td>
				<textarea onchange="saveSpeReason(this);" itemId="2300-03-1.12" itemName="2、图像质量好、清晰，大小、亮度及对比合适" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]"></textarea>
			</td>
		</tr>
		<tr style="height:32px">
			<td>3、体标或标注文字准确</td>
			<td>扣1～5分</td>
			<td style="text-align: center">5</td>
			<td><input onchange="saveScore(this,5);" itemId="2300-03-1.13" itemName="3、体标或标注文字准确"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  /></td>
			<td>
				<textarea onchange="saveSpeReason(this);" itemId="2300-03-1.13" itemName="3、体标或标注文字准确" name="reason" placeholder="请填写扣分原因" style="width: 98%" class="text-input validate[required,maxSize[1000]]"></textarea>
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
				&#12288;&#12288;评价人：${specialistName}
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

<div class="button">
	<input class="btn_green" type="button" value="打&#12288;印" onclick="printHospitalScore('evaluationInfo_2300_yx');"/>&#12288;
	<c:if test="${edit ne 'N'}">
		<input class="btn_green" type="button" value="提&#12288;交" onclick="subInfo();"/>&#12288;
	</c:if>
</div>
</body>
</html>