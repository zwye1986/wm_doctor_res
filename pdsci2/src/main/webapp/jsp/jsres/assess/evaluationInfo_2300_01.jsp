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
				if ((itemIdList[i].getAttribute("name") == "self") && itemIdList[i].value == "") {
					$(itemIdList[i]).focus();
					top.jboxTip("有输入框未输入数据，请输入数据！");
					return;
				}
			}
			var self1Score = 0;
			for (var i = 0; i < itemIdList.length; i++) {
				if (itemIdList[i].getAttribute("name") == "self") {
					self1Score = Number(self1Score) + Number(itemIdList[i].value);
				}
			}
			self1Score = self1Score / 1525 * 100;
			if (self1Score >= 90) {
				self1Score = 3;
			} else if (self1Score >= 85) {
				self1Score = 1;
			} else {
				self1Score = 0;
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
			inputSelf2[0].value = self1Score;
			if (${ roleFlag==("baseExpert")}) {
				window.parent.frames["jbox-message-iframe"].saveScore(inputSelf1[0], self1Score);
				window.parent.frames["jbox-message-iframe"].saveScore(inputSelf2[0], self1Score);
			} else if (${roleFlag==("expertLeader")}) {
				window.parent.frames["jbox-message-iframe"].saveScore4Expert(inputSelf1[0], self1Score);
				window.parent.frames["jbox-message-iframe"].saveScore4Expert(inputSelf2[0], self1Score);
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

		//窗口加载后
		$(function () {
			//获取所有input标签
			var itemIdList = $("input");
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


		//计算合计
		function loadDate() {
			var itemIdList = $("input");
			var kScore = 0;
			for (var i = 0; i < itemIdList.length; i++) {
				if (itemIdList[i].getAttribute("name") == "self") {
					kScore = Number(kScore) + Number(itemIdList[i].value);
				}
			}
			$("#selfTotalled").text(check(kScore.toFixed(1)));
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
					<h2 style="font-size:150%">超声科要求的疾病种类</h2>
				</th>
			</tr>
			<tr style="height:32px;">
				<th colspan="2">疾病种类</th>
				<th >标准</th>
				<th >实际数</th>
				<th >低于标准数（划√）</th>
			</tr>
			<tr style="height:32px">
				<th>超声基础</th>
				<th >超声基本原理、伪像、超声仪器及探头、超声诊断原则</th>
				<th >/</th>
				<td>
					<input onchange="saveScore(this);" itemId="2300-01-1.1" itemName="k超声基本原理、伪像、超声仪器及探头、超声诊断原则"  name="self"  class="input" type="text"  style="height:20px; text-align: center;"  readonly/>
				</td>
				<td>
					<input itemId="2300-01-1.1" itemName="d超声基本原理、伪像、超声仪器及探头、超声诊断原则"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th rowspan="14">腹部（含胸部）</th>
				<th >肝弥漫性病变(肝炎、肝硬化、脂肪肝、肝血吸虫病等)</th>
				<th >100</th>
				<td>
					<input onchange="saveScore(this,100);" itemId="2300-01-1.2" itemName="k肝弥漫性病变(肝炎、肝硬化、脂肪肝、肝血吸虫病等)"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="2300-01-1.2" itemName="d肝弥漫性病变(肝炎、肝硬化、脂肪肝、肝血吸虫病等)"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >肝局灶性病变(肝囊肿、肝脓肿、肝血管瘤、肝癌、肝内血肿、肝包虫病等)</th>
				<th >100</th>
				<td>
					<input onchange="saveScore(this,100);" itemId="2300-01-1.3" itemName="k肝局灶性病变(肝囊肿、肝脓肿、肝血管瘤、肝癌、肝内血肿、肝包虫病等)"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="2300-01-1.3" itemName="d肝局灶性病变(肝囊肿、肝脓肿、肝血管瘤、肝癌、肝内血肿、肝包虫病等)"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >胆囊疾病(炎症、结石、息肉、胆囊癌、胆囊腺肌症等)</th>
				<th >100</th>
				<td>
					<input onchange="saveScore(this,100);" itemId="2300-01-1.4" itemName="k胆囊疾病(炎症、结石、息肉、胆囊癌、胆囊腺肌症等)"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="2300-01-1.4" itemName="d胆囊疾病(炎症、结石、息肉、胆囊癌、胆囊腺肌症等)"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >胆管疾病(肝外胆管癌、胆管扩张等)</th>
				<th >50</th>
				<td>
					<input onchange="saveScore(this,50);" itemId="2300-01-1.5" itemName="k胆管疾病(肝外胆管癌、胆管扩张等)"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="2300-01-1.5" itemName="d胆管疾病(肝外胆管癌、胆管扩张等)"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >胰腺(急慢性炎症、良恶性肿瘤等)</th>
				<th >20</th>
				<td>
					<input onchange="saveScore(this);" itemId="2300-01-1.6" itemName="k胰腺(急慢性炎症、良恶性肿瘤等)"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="2300-01-1.6" itemName="d胰腺(急慢性炎症、良恶性肿瘤等)"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >脾脏(脾大、副脾、脾囊肿、脾血管瘤、脾转移瘤、脾淋巴瘤等)</th>
				<th >50</th>
				<td>
					<input onchange="saveScore(this,50);" itemId="2300-01-1.7" itemName="k脾脏(脾大、副脾、脾囊肿、脾血管瘤、脾转移瘤、脾淋巴瘤等)"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="2300-01-1.7" itemName="d脾脏(脾大、副脾、脾囊肿、脾血管瘤、脾转移瘤、脾淋巴瘤等)"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >泌尿系畸形(重复肾、异位肾、融合肾、肾缺如等)</th>
				<th >10</th>
				<td>
					<input onchange="saveScore(this,10);" itemId="2300-01-1.8" itemName="k泌尿系畸形(重复肾、异位肾、融合肾、肾缺如等)"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="2300-01-1.8" itemName="d泌尿系畸形(重复肾、异位肾、融合肾、肾缺如等)"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >泌尿系结石及梗阻</th>
				<th >50</th>
				<td>
					<input onchange="saveScore(this,50);" itemId="2300-01-1.9" itemName="k泌尿系结石及梗阻"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="2300-01-1.9" itemName="d泌尿系结石及梗阻"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >肾脏弥漫性病变及移植肾</th>
				<th >30</th>
				<td>
					<input onchange="saveScore(this,30);" itemId="2300-01-1.10" itemName="k肾脏弥漫性病变及移植肾"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="2300-01-1.10" itemName="d肾脏弥漫性病变及移植肾"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >泌尿系肿瘤(包括肾、输尿管、膀胱等)</th>
				<th >30</th>
				<td>
					<input onchange="saveScore(this,30);" itemId="2300-01-1.11" itemName="k泌尿系肿瘤(包括肾、输尿管、膀胱等)"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="2300-01-1.11" itemName="d泌尿系肿瘤(包括肾、输尿管、膀胱等)"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >肾上腺肿瘤</th>
				<th >5</th>
				<td>
					<input onchange="saveScore(this,5);" itemId="2300-01-1.12" itemName="k肾上腺肿瘤"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="2300-01-1.12" itemName="d肾上腺肿瘤"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >前列腺病变,残余尿测定</th>
				<th >30</th>
				<td>
					<input onchange="saveScore(this,30);" itemId="2300-01-1.13" itemName="k前列腺病变,残余尿测定"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="2300-01-1.13" itemName="d前列腺病变,残余尿测定"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >腹腔积液</th>
				<th >100</th>
				<td>
					<input onchange="saveScore(this,100);" itemId="2300-01-1.14" itemName="k腹腔积液"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="2300-01-1.14" itemName="d腹腔积液"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >胸腔积液、胸壁-胸膜病变、周围型肺病变</th>
				<th >50</th>
				<td>
					<input onchange="saveScore(this,50);" itemId="2300-01-1.15" itemName="k胸腔积液、胸壁-胸膜病变、周围型肺病变"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="2300-01-1.15" itemName="d胸腔积液、胸壁-胸膜病变、周围型肺病变"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<th rowspan="9">妇产科</th>
			<tr style="height:32px">
				<th >子宫疾病(子宫畸形、肌层病变、内膜病变等)</th>
				<th >80</th>
				<td>
					<input onchange="saveScore(this,80);" itemId="2300-01-1.16" itemName="k子宫疾病(子宫畸形、肌层病变、内膜病变等)"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="2300-01-1.16" itemName="d子宫疾病(子宫畸形、肌层病变、内膜病变等)"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >卵巢囊肿和肿瘤(常见类型)</th>
				<th >50</th>
				<td>
					<input onchange="saveScore(this,50);" itemId="2300-01-1.17" itemName="k卵巢囊肿和肿瘤(常见类型)"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="2300-01-1.17" itemName="d卵巢囊肿和肿瘤(常见类型)"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >盆腔炎性疾病</th>
				<th >10</th>
				<td>
					<input onchange="saveScore(this,10);" itemId="2300-01-1.18" itemName="k盆腔炎性疾病"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="2300-01-1.18" itemName="d盆腔炎性疾病"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >正常早孕及第11～14周超声检查</th>
				<th >50</th>
				<td>
					<input onchange="saveScore(this,50);" itemId="2300-01-1.19" itemName="k正常早孕及第11～14周超声检查"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="2300-01-1.19" itemName="d正常早孕及第11～14周超声检查"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >正常中晚孕(含中孕胎儿结构畸形筛查等)</th>
				<th >30</th>
				<td>
					<input onchange="saveScore(this,30);" itemId="2300-01-1.20" itemName="k正常中晚孕(含中孕胎儿结构畸形筛查等)"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="2300-01-1.20" itemName="d正常中晚孕(含中孕胎儿结构畸形筛查等)"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >异常妊娠及妊娠合并症(流产、异位妊娠、多胎妊娠、羊水及胎盘异常等)</th>
				<th >30</th>
				<td>
					<input onchange="saveScore(this,30);" itemId="2300-01-1.21" itemName="k异常妊娠及妊娠合并症(流产、异位妊娠、多胎妊娠、羊水及胎盘异常等)"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="2300-01-1.21" itemName="d异常妊娠及妊娠合并症(流产、异位妊娠、多胎妊娠、羊水及胎盘异常等)"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >常见胎儿结构畸形</th>
				<th >5</th>
				<td>
					<input onchange="saveScore(this,5);" itemId="2300-01-1.22" itemName="k常见胎儿结构畸形"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="2300-01-1.22" itemName="d常见胎儿结构畸形"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >妊娠滋养细胞疾病</th>
				<th >5</th>
				<td>
					<input onchange="saveScore(this,5);" itemId="2300-01-1.23" itemName="k妊娠滋养细胞疾病"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="2300-01-1.23" itemName="d妊娠滋养细胞疾病"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>

			<tr style="height:32px">
				<th rowspan="2">心脏</th>
				<th >先心性心脏病（常见类型等）</th>
				<th >30</th>
				<td>
					<input onchange="saveScore(this,30);" itemId="2300-01-1.24" itemName="k先心性心脏病（常见类型等）"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="2300-01-1.24" itemName="d先心性心脏病（常见类型等）"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >后天获得性心脏病（瓣膜病、冠心病、心肌病、心包疾病、心脏肿瘤等）</th>
				<th >50</th>
				<td>
					<input onchange="saveScore(this,50);" itemId="2300-01-1.25" itemName="k后天获得性心脏病（瓣膜病、冠心病、心肌病、心包疾病、心脏肿瘤等）"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="2300-01-1.25" itemName="d后天获得性心脏病（瓣膜病、冠心病、心肌病、心包疾病、心脏肿瘤等）"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th rowspan="6">浅表器官</th>
				<th >甲状腺疾病（炎症性疾病、甲状腺肿、结甲、甲状腺癌等）</th>
				<th >100</th>
				<td>
					<input onchange="saveScore(this,100);" itemId="2300-01-1.26" itemName="k甲状腺疾病（炎症性疾病、甲状腺肿、结甲、甲状腺癌等）"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="2300-01-1.26" itemName="d甲状腺疾病（炎症性疾病、甲状腺肿、结甲、甲状腺癌等）"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >甲状旁腺疾病</th>
				<th >5</th>
				<td>
					<input onchange="saveScore(this,5);" itemId="2300-01-1.27" itemName="k甲状旁腺疾病"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="2300-01-1.27" itemName="d甲状旁腺疾病"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >乳腺（增生、炎症、良恶性占位等）</th>
				<th >100</th>
				<td>
					<input onchange="saveScore(this,100);" itemId="2300-01-2.1" itemName="k乳腺（增生、炎症、良恶性占位等）"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="2300-01-2.1" itemName="d乳腺（增生、炎症、良恶性占位等）"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >涎腺（炎症、肿瘤等）</th>
				<th >5</th>
				<td>
					<input onchange="saveScore(this,5);" itemId="2300-01-2.2" itemName="k涎腺（炎症、肿瘤等）"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="2300-01-2.2" itemName="d涎腺（炎症、肿瘤等）"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >淋巴结（良、恶性疾病）</th>
				<th >30</th>
				<td>
					<input onchange="saveScore(this,30);" itemId="2300-01-2.3" itemName="k淋巴结（良、恶性疾病）"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="2300-01-2.3" itemName="d淋巴结（良、恶性疾病）"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >阴囊（阴囊急症、睾丸肿瘤、鞘膜积液、斜疝等）</th>
				<th >10</th>
				<td>
					<input onchange="saveScore(this,10);" itemId="2300-01-2.4" itemName="k阴囊（阴囊急症、睾丸肿瘤、鞘膜积液、斜疝等）"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="2300-01-2.4" itemName="d阴囊（阴囊急症、睾丸肿瘤、鞘膜积液、斜疝等）"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th rowspan="4">周围血管</th>
				<th >颈动脉、椎动脉（动脉粥样硬化、支架等）</th>
				<th >80</th>
				<td>
					<input onchange="saveScore(this);" itemId="2300-01-2.5" itemName="k颈动脉、椎动脉（动脉粥样硬化、支架等）"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="2300-01-2.5" itemName="d颈动脉、椎动脉（动脉粥样硬化、支架等）"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >四肢动脉（动脉粥样硬化、动脉瘤等）</th>
				<th >50</th>
				<td>
					<input onchange="saveScore(this,50);" itemId="2300-01-2.6" itemName="k四肢动脉（动脉粥样硬化、动脉瘤等）"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="2300-01-2.6" itemName="d四肢动脉（动脉粥样硬化、动脉瘤等）"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >四肢静脉（血栓、静脉瓣功能不全、动静脉瘘等）</th>
				<th >50</th>
				<td>
					<input onchange="saveScore(this,50);" itemId="2300-01-2.7" itemName="k四肢静脉（血栓、静脉瓣功能不全、动静脉瘘等）"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="2300-01-2.7" itemName="d抗链球菌溶血素“O”(ASO)、C反应蛋白(CRP)"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >腹部血管（腹主动脉瘤、门脉病变、布-加综合征、肾静脉疾病等）</th>
				<th >30</th>
				<td>
					<input onchange="saveScore(this,30);" itemId="2300-01-2.8" itemName="k腹部血管（腹主动脉瘤、门脉病变、布-加综合征、肾静脉疾病等）"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="2300-01-2.8" itemName="d腹部血管（腹主动脉瘤、门脉病变、布-加综合征、肾静脉疾病等）"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th>介入超声（上级医师指导）</th>
				<th >腹部脓肿穿刺抽吸置管引流，肝、肾脏穿刺活检，肾囊肿穿刺硬化疗法，前列腺穿刺活检</th>
				<th >各1</th>
				<td>
					<input onchange="saveScore(this);" itemId="2300-01-2.9" itemName="k腹部脓肿穿刺抽吸置管引流，肝、肾脏穿刺活检，肾囊肿穿刺硬化疗法，前列腺穿刺活检"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  readonly/>
				</td>
				<td>
					<input itemId="2300-01-2.9" itemName="d腹部脓肿穿刺抽吸置管引流，肝、肾脏穿刺活检，肾囊肿穿刺硬化疗法，前列腺穿刺活检"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th colspan="3" style="text-align: right">合计：</th>
				<td style="text-align: center;"><span id="selfTotalled"></span></td>
				<td style="text-align: center;"></td>
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