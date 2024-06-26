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
			var selectList = $("select");
			for (var i = 0; i < selectList.length; i++) {
				if (selectList[i].value == "") {
					$(selectList[i]).focus();
					top.jboxTip("有选择框未选择数据，请选择数据！");
					return;
				}
			}
			top.jboxTip("操作成功！");
			top.jboxClose();
		}

		function saveSelect(expl, itemId, itemName) {
			var url = "<s:url value='/jsres/supervisio/savScheduleHaveAndNo'/>";
			var data = {
				"itemId": itemId,
				"itemName": itemName,
				"score": expl.value,
				"orgFlow": '${orgFlow}',
				"orgName": '${orgName}',
				"speId": '${speId}',
				"subjectFlow": '${subjectFlow}',
				"roleFlag": '${roleFlag}',
				"fileRoute": '${fileRoute}'
			};
			top.jboxPost(url, data, function (resp) {
				if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
					var itemName2 = "d" + itemName.substring(1);
					var scoreInput = "";
					if (expl.value == '有') {
						scoreInput = "";
					} else {
						scoreInput = "√";
					}
					var inputItem = $("input[itemName=\"" + itemName2 + "\"]").val(scoreInput);
					top.jboxTip(resp);
				} else {
					top.jboxTip(resp);
				}
			}, null, false);
		};

		//窗口加载后
		$(function () {
			//获取所有input标签
			var itemIdList = $("input");
			var selectList = $("select");
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
			for (var i = 0; i < selectList.length; i++) {
				$(selectList[i]).css("display", "block").css("margin", "0 auto");
				if (selectList[i].getAttribute("itemName") == "${item.itemName}" && selectList[i].getAttribute("name").startsWith("s")) {
					selectList[i].value = "${item.score}";
					$(selectList[i]).attr("preValue", "${item.score}");
				}
			}
			</c:forEach>
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
<div class="div_table"  style="overflow: auto;max-height: 570px;">
	<table cellpadding="4"  style="width: 1000px">
		<tbody>
			<tr height="34" class="firstRow">
				<th colspan="5">
					<h2 style="font-size:150%">检验医学科检验项目</h2>
				</th>
			</tr>
			<tr style="height:32px;">
				<th colspan="2">检验项目</th>
				<th >标准</th>
				<th >实际数</th>
				<th >低于标准数（划√）</th>
			</tr>
			<tr style="height:32px">
				<th rowspan="20">临床体液、血液学专业</th>
				<th >全血细胞计数及分类计数</th>
				<th >有</th>
				<td>
					<select onchange="saveSelect(this,'2100-01-1.1','k全血细胞计数及分类计数');" itemId="2100-01-1.1" itemName="k全血细胞计数及分类计数"
							name="self1" style="width: 160px;margin-left: 6px;">
						<option value="" style="text-align: center">请选择</option>
						<option value="有" style="text-align: center">有</option>
						<option value="无" style="text-align: center">无</option>
					</select>
				</td>
				<td>
					<input itemId="2100-01-1.1" itemName="d全血细胞计数及分类计数"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >血涂片的形态学检查</th>
				<th >有</th>
				<td>
					<select onchange="saveSelect(this,'2100-01-1.2','k血涂片的形态学检查');" itemId="2100-01-1.2" itemName="k血涂片的形态学检查"
							name="self1" style="width: 160px;margin-left: 6px;">
						<option value="" style="text-align: center">请选择</option>
						<option value="有" style="text-align: center">有</option>
						<option value="无" style="text-align: center">无</option>
					</select>
				</td>
				<td>
					<input itemId="2100-01-1.2" itemName="d血涂片的形态学检查"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >红细胞沉降率,网织红细胞计数,嗜酸性粒细胞计数</th>
				<th >有</th>
				<td>
					<select onchange="saveSelect(this,'2100-01-1.3','k红细胞沉降率,网织红细胞计数,嗜酸性粒细胞计数');" itemId="2100-01-1.3" itemName="k红细胞沉降率,网织红细胞计数,嗜酸性粒细胞计数"
							name="self1" style="width: 160px;margin-left: 6px;">
						<option value="" style="text-align: center">请选择</option>
						<option value="有" style="text-align: center">有</option>
						<option value="无" style="text-align: center">无</option>
					</select>
				</td>
				<td>
					<input itemId="2100-01-1.3" itemName="d红细胞沉降率,网织红细胞计数,嗜酸性粒细胞计数"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >尿液的理学、化学检查及沉渣镜检</th>
				<th >有</th>
				<td>
					<select onchange="saveSelect(this,'2100-01-1.4','k尿液的理学、化学检查及沉渣镜检');" itemId="2100-01-1.4" itemName="k尿液的理学、化学检查及沉渣镜检"
							name="self1" style="width: 160px;margin-left: 6px;">
						<option value="" style="text-align: center">请选择</option>
						<option value="有" style="text-align: center">有</option>
						<option value="无" style="text-align: center">无</option>
					</select>
				</td>
				<td>
					<input itemId="2100-01-1.4" itemName="d尿液的理学、化学检查及沉渣镜检"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >乳糜尿检查,尿妊娠试验</th>
				<th >有</th>
				<td>
					<select onchange="saveSelect(this,'2100-01-1.5','k乳糜尿检查,尿妊娠试验');" itemId="2100-01-1.5" itemName="k乳糜尿检查,尿妊娠试验"
							name="self1" style="width: 160px;margin-left: 6px;">
						<option value="" style="text-align: center">请选择</option>
						<option value="有" style="text-align: center">有</option>
						<option value="无" style="text-align: center">无</option>
					</select>
				</td>
				<td>
					<input itemId="2100-01-1.5" itemName="d乳糜尿检查,尿妊娠试验"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >尿液干化学自动分析仪应用及结果分析</th>
				<th >有</th>
				<td>
					<select onchange="saveSelect(this,'2100-01-1.6','k尿液干化学自动分析仪应用及结果分析');" itemId="2100-01-1.6" itemName="k尿液干化学自动分析仪应用及结果分析"
							name="self1" style="width: 160px;margin-left: 6px;">
						<option value="" style="text-align: center">请选择</option>
						<option value="有" style="text-align: center">有</option>
						<option value="无" style="text-align: center">无</option>
					</select>
				</td>
				<td>
					<input itemId="2100-01-1.6" itemName="d尿液干化学自动分析仪应用及结果分析"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >尿液分析质控、尿沉渣细胞与管型的标准化定量检查</th>
				<th >有</th>
				<td>
					<select onchange="saveSelect(this,'2100-01-1.7','k尿液分析质控、尿沉渣细胞与管型的标准化定量检查');" itemId="2100-01-1.7" itemName="k尿液分析质控、尿沉渣细胞与管型的标准化定量检查"
							name="self1" style="width: 160px;margin-left: 6px;">
						<option value="" style="text-align: center">请选择</option>
						<option value="有" style="text-align: center">有</option>
						<option value="无" style="text-align: center">无</option>
					</select>
				</td>
				<td>
					<input itemId="2100-01-1.7" itemName="d尿液分析质控、尿沉渣细胞与管型的标准化定量检查"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >粪便常规检查及隐血试验</th>
				<th >有</th>
				<td>
					<select onchange="saveSelect(this,'2100-01-1.8','k粪便常规检查及隐血试验');" itemId="2100-01-1.8" itemName="k粪便常规检查及隐血试验"
							name="self1" style="width: 160px;margin-left: 6px;">
						<option value="" style="text-align: center">请选择</option>
						<option value="有" style="text-align: center">有</option>
						<option value="无" style="text-align: center">无</option>
					</select>
				</td>
				<td>
					<input itemId="2100-01-1.8" itemName="d粪便常规检查及隐血试验"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >粪便常见寄生虫及其虫卵识别</th>
				<th >有</th>
				<td>
					<select onchange="saveSelect(this,'2100-01-1.9','k粪便常见寄生虫及其虫卵识别');" itemId="2100-01-1.9" itemName="k粪便常见寄生虫及其虫卵识别"
							name="self1" style="width: 160px;margin-left: 6px;">
						<option value="" style="text-align: center">请选择</option>
						<option value="有" style="text-align: center">有</option>
						<option value="无" style="text-align: center">无</option>
					</select>
				</td>
				<td>
					<input itemId="2100-01-1.9" itemName="d粪便常见寄生虫及其虫卵识别"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >脑脊液检查(包括外观:颜色、透明度,细胞计数与分类)</th>
				<th >有</th>
				<td>
					<select onchange="saveSelect(this,'2100-01-1.10','k脑脊液检查(包括外观:颜色、透明度,细胞计数与分类)');" itemId="2100-01-1.10" itemName="k脑脊液检查(包括外观:颜色、透明度,细胞计数与分类)"
							name="self1" style="width: 160px;margin-left: 6px;">
						<option value="" style="text-align: center">请选择</option>
						<option value="有" style="text-align: center">有</option>
						<option value="无" style="text-align: center">无</option>
					</select>
				</td>
				<td>
					<input itemId="2100-01-1.10" itemName="d脑脊液检查(包括外观:颜色、透明度,细胞计数与分类)"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >浆膜腔积液检查(外观的颜色及比重、蛋白、细胞计数与形态,渗出液与漏出液的鉴别)</th>
				<th >有</th>
				<td>
					<select onchange="saveSelect(this,'2100-01-1.11','k浆膜腔积液检查(外观的颜色及比重、蛋白、细胞计数与形态,渗出液与漏出液的鉴别)');" itemId="2100-01-1.11" itemName="k浆膜腔积液检查(外观的颜色及比重、蛋白、细胞计数与形态,渗出液与漏出液的鉴别)"
							name="self1" style="width: 160px;margin-left: 6px;">
						<option value="" style="text-align: center">请选择</option>
						<option value="有" style="text-align: center">有</option>
						<option value="无" style="text-align: center">无</option>
					</select>
				</td>
				<td>
					<input itemId="2100-01-1.11" itemName="d浆膜腔积液检查(外观的颜色及比重、蛋白、细胞计数与形态,渗出液与漏出液的鉴别)"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >精液、前列腺液、阴道分泌物的常规检查</th>
				<th >有</th>
				<td>
					<select onchange="saveSelect(this,'2100-01-1.12','k进行性肌营养不良');" itemId="2100-01-1.12" itemName="k进行性肌营养不良"
							name="self1" style="width: 160px;margin-left: 6px;">
						<option value="" style="text-align: center">请选择</option>
						<option value="有" style="text-align: center">有</option>
						<option value="无" style="text-align: center">无</option>
					</select>
				</td>
				<td>
					<input itemId="2100-01-1.12" itemName="d进行性肌营养不良"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >正常骨髓形态学检查辨认及分类计数</th>
				<th >有</th>
				<td>
					<select onchange="saveSelect(this,'2100-01-1.13','k正常骨髓形态学检查辨认及分类计数');" itemId="2100-01-1.13" itemName="k正常骨髓形态学检查辨认及分类计数"
							name="self1" style="width: 160px;margin-left: 6px;">
						<option value="" style="text-align: center">请选择</option>
						<option value="有" style="text-align: center">有</option>
						<option value="无" style="text-align: center">无</option>
					</select>
				</td>
				<td>
					<input itemId="2100-01-1.13" itemName="d正常骨髓形态学检查辨认及分类计数"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >常用细胞化学染色方法</th>
				<th >有</th>
				<td>
					<select onchange="saveSelect(this,'2100-01-1.14','k常用细胞化学染色方法');" itemId="2100-01-1.14" itemName="k常用细胞化学染色方法"
							name="self1" style="width: 160px;margin-left: 6px;">
						<option value="" style="text-align: center">请选择</option>
						<option value="有" style="text-align: center">有</option>
						<option value="无" style="text-align: center">无</option>
					</select>
				</td>
				<td>
					<input itemId="2100-01-1.14" itemName="d常用细胞化学染色方法"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >常见血液病的骨髓检查</th>
				<th >有</th>
				<td>
					<select onchange="saveSelect(this,'2100-01-1.15','k常见血液病的骨髓检查');" itemId="2100-01-1.15" itemName="k常见血液病的骨髓检查"
							name="self1" style="width: 160px;margin-left: 6px;">
						<option value="" style="text-align: center">请选择</option>
						<option value="有" style="text-align: center">有</option>
						<option value="无" style="text-align: center">无</option>
					</select>
				</td>
				<td>
					<input itemId="2100-01-1.15" itemName="d常见血液病的骨髓检查"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >骨髓增生异常综合征、少见类型血液病的实验室检查</th>
				<th >有</th>
				<td>
					<select onchange="saveSelect(this,'2100-01-1.16','k骨髓增生异常综合征、少见类型血液病的实验室检查');" itemId="2100-01-1.16" itemName="k骨髓增生异常综合征、少见类型血液病的实验室检查"
							name="self1" style="width: 160px;margin-left: 6px;">
						<option value="" style="text-align: center">请选择</option>
						<option value="有" style="text-align: center">有</option>
						<option value="无" style="text-align: center">无</option>
					</select>
				</td>
				<td>
					<input itemId="2100-01-1.16" itemName="d骨髓增生异常综合征、少见类型血液病的实验室检查"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >溶血性贫血的诊断实验</th>
				<th >有</th>
				<td>
					<select onchange="saveSelect(this,'2100-01-1.17','k溶血性贫血的诊断实验');" itemId="2100-01-1.17" itemName="k溶血性贫血的诊断实验"
							name="self1" style="width: 160px;margin-left: 6px;">
						<option value="" style="text-align: center">请选择</option>
						<option value="有" style="text-align: center">有</option>
						<option value="无" style="text-align: center">无</option>
					</select>
				</td>
				<td>
					<input itemId="2100-01-1.17" itemName="d溶血性贫血的诊断实验"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >血液流变学检查</th>
				<th >有</th>
				<td>
					<select onchange="saveSelect(this,'2100-01-1.18','k血液流变学检查');" itemId="2100-01-1.18" itemName="k血液流变学检查"
							name="self1" style="width: 160px;margin-left: 6px;">
						<option value="" style="text-align: center">请选择</option>
						<option value="有" style="text-align: center">有</option>
						<option value="无" style="text-align: center">无</option>
					</select>
				</td>
				<td>
					<input itemId="2100-01-1.18" itemName="d血液流变学检查"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >止血与凝血障碍性疾病及弥漫性血管内凝血(DIC)的实验室检查</th>
				<th >有</th>
				<td>
					<select onchange="saveSelect(this,'2100-01-1.19','k止血与凝血障碍性疾病及弥漫性血管内凝血');" itemId="2100-01-1.19" itemName="k止血与凝血障碍性疾病及弥漫性血管内凝血"
							name="self1" style="width: 160px;margin-left: 6px;">
						<option value="" style="text-align: center">请选择</option>
						<option value="有" style="text-align: center">有</option>
						<option value="无" style="text-align: center">无</option>
					</select>
				</td>
				<td>
					<input itemId="2100-01-1.19" itemName="d止血与凝血障碍性疾病及弥漫性血管内凝血(DIC)的实验室检查"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >白血病、淋巴瘤等流式细胞免疫分型检查</th>
				<th >有</th>
				<td>
					<select onchange="saveSelect(this,'2100-01-1.20','k白血病、淋巴瘤等流式细胞免疫分型检查');" itemId="2100-01-1.20" itemName="k白血病、淋巴瘤等流式细胞免疫分型检查"
							name="self1" style="width: 160px;margin-left: 6px;">
						<option value="" style="text-align: center">请选择</option>
						<option value="有" style="text-align: center">有</option>
						<option value="无" style="text-align: center">无</option>
					</select>
				</td>
				<td>
					<input itemId="2100-01-1.20" itemName="d白血病、淋巴瘤等流式细胞免疫分型检查"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th rowspan="7">临床化学专业</th>
				<th >血清酶测定</th>
				<th >有</th>
				<td>
					<select onchange="saveSelect(this,'2100-01-1.21','k血清酶测定');" itemId="2100-01-1.21" itemName="k血清酶测定"
							name="self1" style="width: 160px;margin-left: 6px;">
						<option value="" style="text-align: center">请选择</option>
						<option value="有" style="text-align: center">有</option>
						<option value="无" style="text-align: center">无</option>
					</select>
				</td>
				<td>
					<input itemId="2100-01-1.21" itemName="d血清酶测定"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >肝功能检查</th>
				<th >有</th>
				<td>
					<select onchange="saveSelect(this,'2100-01-1.22','k肝功能检查');" itemId="2100-01-1.22" itemName="k肝功能检查"
							name="self1" style="width: 160px;margin-left: 6px;">
						<option value="" style="text-align: center">请选择</option>
						<option value="有" style="text-align: center">有</option>
						<option value="无" style="text-align: center">无</option>
					</select>
				</td>
				<td>
					<input itemId="2100-01-1.22" itemName="d肝功能检查"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >肾功能及肾早期损伤检查</th>
				<th >有</th>
				<td>
					<select onchange="saveSelect(this,'2100-01-1.23','k肾功能及肾早期损伤检查');" itemId="2100-01-1.23" itemName="k肾功能及肾早期损伤检查"
							name="self1" style="width: 160px;margin-left: 6px;">
						<option value="" style="text-align: center">请选择</option>
						<option value="有" style="text-align: center">有</option>
						<option value="无" style="text-align: center">无</option>
					</select>
				</td>
				<td>
					<input itemId="2100-01-1.23" itemName="d肾功能及肾早期损伤检查"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >脑脊液生化检查</th>
				<th >有</th>
				<td>
					<select onchange="saveSelect(this,'2100-01-1.24','k脑脊液生化检查');" itemId="2100-01-1.24" itemName="k脑脊液生化检查"
							name="self1" style="width: 160px;margin-left: 6px;">
						<option value="" style="text-align: center">请选择</option>
						<option value="有" style="text-align: center">有</option>
						<option value="无" style="text-align: center">无</option>
					</select>
				</td>
				<td>
					<input itemId="2100-01-1.24" itemName="d脑脊液生化检查"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >糖代谢检查</th>
				<th >有</th>
				<td>
					<select onchange="saveSelect(this,'2100-01-1.25','k糖代谢检查');" itemId="2100-01-1.25" itemName="k糖代谢检查"
							name="self1" style="width: 160px;margin-left: 6px;">
						<option value="" style="text-align: center">请选择</option>
						<option value="有" style="text-align: center">有</option>
						<option value="无" style="text-align: center">无</option>
					</select>
				</td>
				<td>
					<input itemId="2100-01-1.25" itemName="d糖代谢检查"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >脂代谢检查</th>
				<th >有</th>
				<td>
					<select onchange="saveSelect(this,'2100-01-1.26','k脂代谢检查');" itemId="2100-01-1.26" itemName="k脂代谢检查"
							name="self1" style="width: 160px;margin-left: 6px;">
						<option value="" style="text-align: center">请选择</option>
						<option value="有" style="text-align: center">有</option>
						<option value="无" style="text-align: center">无</option>
					</select>
				</td>
				<td>
					<input itemId="2100-01-1.26" itemName="d脂代谢检查"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >激素代谢检查</th>
				<th >有</th>
				<td>
					<select onchange="saveSelect(this,'2100-01-1.27','k激素代谢检查');" itemId="2100-01-1.27" itemName="k激素代谢检查"
							name="self1" style="width: 160px;margin-left: 6px;">
						<option value="" style="text-align: center">请选择</option>
						<option value="有" style="text-align: center">有</option>
						<option value="无" style="text-align: center">无</option>
					</select>
				</td>
				<td>
					<input itemId="2100-01-1.27" itemName="d激素代谢检查"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th rowspan="2">临床化学专业</th>
				<th >电解质平衡检查</th>
				<th >有</th>
				<td>
					<select onchange="saveSelect(this,'2100-01-2.1','k电解质平衡检查');" itemId="2100-01-2.1" itemName="k电解质平衡检查"
							name="self1" style="width: 160px;margin-left: 6px;">
						<option value="" style="text-align: center">请选择</option>
						<option value="有" style="text-align: center">有</option>
						<option value="无" style="text-align: center">无</option>
					</select>
				</td>
				<td>
					<input itemId="2100-01-2.1" itemName="d电解质平衡检查"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >心肌损伤检查</th>
				<th >有</th>
				<td>
					<select onchange="saveSelect(this,'2100-01-2.2','k心肌损伤检查');" itemId="2100-01-2.2" itemName="k心肌损伤检查"
							name="self1" style="width: 160px;margin-left: 6px;">
						<option value="" style="text-align: center">请选择</option>
						<option value="有" style="text-align: center">有</option>
						<option value="无" style="text-align: center">无</option>
					</select>
				</td>
				<td>
					<input itemId="2100-01-2.2" itemName="d心肌损伤检查"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th rowspan="13">临床免疫学专业</th>
				<th >乙肝血清标志物的测定</th>
				<th >有</th>
				<td>
					<select onchange="saveSelect(this,'2100-01-2.3','k乙肝血清标志物的测定');" itemId="2100-01-2.3" itemName="k乙肝血清标志物的测定"
							name="self1" style="width: 160px;margin-left: 6px;">
						<option value="" style="text-align: center">请选择</option>
						<option value="有" style="text-align: center">有</option>
						<option value="无" style="text-align: center">无</option>
					</select>
				</td>
				<td>
					<input itemId="2100-01-2.3" itemName="d乙肝血清标志物的测定"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >甲肝和丙肝病毒抗体测定</th>
				<th >有</th>
				<td>
					<select onchange="saveSelect(this,'2100-01-2.4','k甲肝和丙肝病毒抗体测定');" itemId="2100-01-2.4" itemName="k甲肝和丙肝病毒抗体测定"
							name="self1" style="width: 160px;margin-left: 6px;">
						<option value="" style="text-align: center">请选择</option>
						<option value="有" style="text-align: center">有</option>
						<option value="无" style="text-align: center">无</option>
					</select>
				</td>
				<td>
					<input itemId="2100-01-2.4" itemName="d甲肝和丙肝病毒抗体测定"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >免疫蛋白电泳</th>
				<th >有</th>
				<td>
					<select onchange="saveSelect(this,'2100-01-2.5','k免疫蛋白电泳');" itemId="2100-01-2.5" itemName="k免疫蛋白电泳"
							name="self1" style="width: 160px;margin-left: 6px;">
						<option value="" style="text-align: center">请选择</option>
						<option value="有" style="text-align: center">有</option>
						<option value="无" style="text-align: center">无</option>
					</select>
				</td>
				<td>
					<input itemId="2100-01-2.5" itemName="d免疫蛋白电泳"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >免疫球蛋白测定,补体测定,T淋巴细胞亚群</th>
				<th >有</th>
				<td>
					<select onchange="saveSelect(this,'2100-01-2.6','k免疫球蛋白测定,补体测定,T淋巴细胞亚群');" itemId="2100-01-2.6" itemName="k免疫球蛋白测定,补体测定,T淋巴细胞亚群"
							name="self1" style="width: 160px;margin-left: 6px;">
						<option value="" style="text-align: center">请选择</option>
						<option value="有" style="text-align: center">有</option>
						<option value="无" style="text-align: center">无</option>
					</select>
				</td>
				<td>
					<input itemId="2100-01-2.6" itemName="d免疫球蛋白测定,补体测定,T淋巴细胞亚群"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >抗链球菌溶血素“O”(ASO)、C反应蛋白(CRP)</th>
				<th >有</th>
				<td>
					<select onchange="saveSelect(this,'2100-01-2.7','k抗链球菌溶血素“O”(ASO)、C反应蛋白(CRP)');" itemId="2100-01-2.7" itemName="k抗链球菌溶血素“O”(ASO)、C反应蛋白(CRP)"
							name="self1" style="width: 160px;margin-left: 6px;">
						<option value="" style="text-align: center">请选择</option>
						<option value="有" style="text-align: center">有</option>
						<option value="无" style="text-align: center">无</option>
					</select>
				</td>
				<td>
					<input itemId="2100-01-2.7" itemName="d抗链球菌溶血素“O”(ASO)、C反应蛋白(CRP)"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >血清M蛋白分型、κ轻链及λ轻链测定</th>
				<th >有</th>
				<td>
					<select onchange="saveSelect(this,'2100-01-2.8','k血清M蛋白分型、κ轻链及λ轻链测定');" itemId="2100-01-2.8" itemName="k血清M蛋白分型、κ轻链及λ轻链测定"
							name="self1" style="width: 160px;margin-left: 6px;">
						<option value="" style="text-align: center">请选择</option>
						<option value="有" style="text-align: center">有</option>
						<option value="无" style="text-align: center">无</option>
					</select>
				</td>
				<td>
					<input itemId="2100-01-2.8" itemName="d血清M蛋白分型、κ轻链及λ轻链测定"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >甲胎蛋白(AFP)、癌胚抗原(CEA)、CA-A5-3、CA19-9、CA12-5、PSA等肿瘤标志物检查</th>
				<th >有</th>
				<td>
					<select onchange="saveSelect(this,'2100-01-2.9','k甲胎蛋白(AFP)、癌胚抗原(CEA)');" itemId="2100-01-2.9" itemName="k甲胎蛋白(AFP)、癌胚抗原(CEA)"
							name="self1" style="width: 160px;margin-left: 6px;">
						<option value="" style="text-align: center">请选择</option>
						<option value="有" style="text-align: center">有</option>
						<option value="无" style="text-align: center">无</option>
					</select>
				</td>
				<td>
					<input itemId="2100-01-2.9" itemName="d甲胎蛋白(AFP)、癌胚抗原(CEA)"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >转铁蛋白(TRF)、T3、T4、TSH、胰岛素及C肽测定</th>
				<th >有</th>
				<td>
					<select onchange="saveSelect(this,'2100-01-2.10','k转铁蛋白(TRF)、T3、T4、TSH、胰岛素及C肽测定');" itemId="2100-01-2.10" itemName="k转铁蛋白(TRF)、T3、T4、TSH、胰岛素及C肽测定"
							name="self1" style="width: 160px;margin-left: 6px;">
						<option value="" style="text-align: center">请选择</option>
						<option value="有" style="text-align: center">有</option>
						<option value="无" style="text-align: center">无</option>
					</select>
				</td>
				<td>
					<input itemId="2100-01-2.10" itemName="d转铁蛋白(TRF)、T3、T4、TSH、胰岛素及C肽测定"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >HIV抗体检测、梅毒螺旋体抗原血清试验(TPPA/TPHA/ELISA)、非梅毒螺旋体抗原血清试验(RPR/TRUST)</th>
				<th >有</th>
				<td>
					<select onchange="saveSelect(this,'2100-01-2.11','kHIV抗体检测、梅毒螺旋体抗原血清试验');" itemId="2100-01-2.11" itemName="kHIV抗体检测、梅毒螺旋体抗原血清试验"
							name="self1" style="width: 160px;margin-left: 6px;">
						<option value="" style="text-align: center">请选择</option>
						<option value="有" style="text-align: center">有</option>
						<option value="无" style="text-align: center">无</option>
					</select>
				</td>
				<td>
					<input itemId="2100-01-2.11" itemName="dHIV抗体检测、梅毒螺旋体抗原血清试验"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >血清肥达反应、外裴反应</th>
				<th >有</th>
				<td>
					<select onchange="saveSelect(this,'2100-01-2.12','k血清肥达反应、外裴反应');" itemId="2100-01-2.12" itemName="k血清肥达反应、外裴反应"
							name="self1" style="width: 160px;margin-left: 6px;">
						<option value="" style="text-align: center">请选择</option>
						<option value="有" style="text-align: center">有</option>
						<option value="无" style="text-align: center">无</option>
					</select>
				</td>
				<td>
					<input itemId="2100-01-2.12" itemName="d血清肥达反应、外裴反应"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >抗核抗体(ANA)、抗双链DNA 抗体(dsDNA)、抗线粒体抗体、类风湿因子(RF)</th>
				<th >有</th>
				<td>
					<select onchange="saveSelect(this,'2100-01-2.13','k抗核抗体(ANA)、抗双链DNA 抗体(dsDNA)、抗线粒体抗体、类风湿因子(RF)');" itemId="2100-01-2.13" itemName="k抗核抗体(ANA)、抗双链DNA 抗体(dsDNA)、抗线粒体抗体、类风湿因子(RF)"
							name="self1" style="width: 160px;margin-left: 6px;">
						<option value="" style="text-align: center">请选择</option>
						<option value="有" style="text-align: center">有</option>
						<option value="无" style="text-align: center">无</option>
					</select>
				</td>
				<td>
					<input itemId="2100-01-2.13" itemName="d抗核抗体(ANA)、抗双链DNA 抗体(dsDNA)、抗线粒体抗体、类风湿因子(RF)"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >Ⅳ型胶原(Ⅳ-C)</th>
				<th >有</th>
				<td>
					<select onchange="saveSelect(this,'2100-01-2.14','kⅣ型胶原(Ⅳ-C)')" itemId="2100-01-2.14" itemName="kⅣ型胶原(Ⅳ-C)"
							name="self1" style="width: 160px;margin-left: 6px;">
						<option value="" style="text-align: center">请选择</option>
						<option value="有" style="text-align: center">有</option>
						<option value="无" style="text-align: center">无</option>
					</select>
				</td>
				<td>
					<input itemId="2100-01-2.14" itemName="dⅣ型胶原(Ⅳ-C)"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >弓形虫、风疹病毒、巨细胞病毒、单纯疱疹病毒抗体检查(TORCH 试验)</th>
				<th >有</th>
				<td>
					<select onchange="saveSelect(this,'2100-01-2.15','k弓形虫、风疹病毒、巨细胞病毒、单纯疱疹病毒抗体检查(TORCH 试验)')" itemId="2100-01-2.15" itemName="k弓形虫、风疹病毒、巨细胞病毒、单纯疱疹病毒抗体检查(TORCH 试验)"
							name="self1" style="width: 160px;margin-left: 6px;">
						<option value="" style="text-align: center">请选择</option>
						<option value="有" style="text-align: center">有</option>
						<option value="无" style="text-align: center">无</option>
					</select>
				</td>
				<td>
					<input itemId="2100-01-2.15" itemName="d弓形虫、风疹病毒、巨细胞病毒、单纯疱疹病毒抗体检查(TORCH 试验)"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th rowspan="9">临床微生物学</th>
				<th >常用微生物染色法(革兰、抗酸、墨汁染色)</th>
				<th >有</th>
				<td>
					<select onchange="saveSelect(this,'2100-01-2.16','k常用微生物染色法(革兰、抗酸、墨汁染色)')" itemId="2100-01-2.16" itemName="k常用微生物染色法(革兰、抗酸、墨汁染色)"
							name="self1" style="width: 160px;margin-left: 6px;">
						<option value="" style="text-align: center">请选择</option>
						<option value="有" style="text-align: center">有</option>
						<option value="无" style="text-align: center">无</option>
					</select>
				</td>
				<td>
					<input itemId="2100-01-2.16" itemName="d常用微生物染色法(革兰、抗酸、墨汁染色)"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >悬滴法观察细菌动力</th>
				<th >有</th>
				<td>
					<select onchange="saveSelect(this,'2100-01-2.17','k悬滴法观察细菌动力')" itemId="2100-01-2.17" itemName="k悬滴法观察细菌动力"
							name="self1" style="width: 160px;margin-left: 6px;">
						<option value="" style="text-align: center">请选择</option>
						<option value="有" style="text-align: center">有</option>
						<option value="无" style="text-align: center">无</option>
					</select>
				</td>
				<td>
					<input itemId="2100-01-2.17" itemName="d悬滴法观察细菌动力"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >常见标本的核收、培养及鉴定(包括血、脑脊液、痰、尿、便、脓汁、胸腹腔积液、分泌物等)</th>
				<th >有</th>
				<td>
					<select onchange="saveSelect(this,'2100-01-2.18','k常见标本的核收、培养及鉴定(包括血、脑脊液、痰、尿')" itemId="2100-01-2.18" itemName="k常见标本的核收、培养及鉴定(包括血、脑脊液、痰、尿"
							name="self1" style="width: 160px;margin-left: 6px;">
						<option value="" style="text-align: center">请选择</option>
						<option value="有" style="text-align: center">有</option>
						<option value="无" style="text-align: center">无</option>
					</select>
				</td>
				<td>
					<input itemId="2100-01-2.18" itemName="d常见标本的核收、培养及鉴定(包括血、脑脊液、痰、尿"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >常见细菌及真菌的培养、分离鉴定</th>
				<th >有</th>
				<td>
					<select onchange="saveSelect(this,'2100-01-2.19','k常见细菌及真菌的培养、分离鉴定')" itemId="2100-01-2.19" itemName="k常见细菌及真菌的培养、分离鉴定"
							name="self1" style="width: 160px;margin-left: 6px;">
						<option value="" style="text-align: center">请选择</option>
						<option value="有" style="text-align: center">有</option>
						<option value="无" style="text-align: center">无</option>
					</select>
				</td>
				<td>
					<input itemId="2100-01-2.19" itemName="d常见细菌及真菌的培养、分离鉴定"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >常见寄生虫的检测</th>
				<th >有</th>
				<td>
					<select onchange="saveSelect(this,'2100-01-2.20','k常见寄生虫的检测')" itemId="2100-01-2.20" itemName="k常见寄生虫的检测"
							name="self1" style="width: 160px;margin-left: 6px;">
						<option value="" style="text-align: center">请选择</option>
						<option value="有" style="text-align: center">有</option>
						<option value="无" style="text-align: center">无</option>
					</select>
				</td>
				<td>
					<input itemId="2100-01-2.20" itemName="d常见寄生虫的检测"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >荚膜、芽胞、细胞壁、鞭毛、阿伯尔染色法、异染颗粒染色</th>
				<th >有</th>
				<td>
					<select onchange="saveSelect(this,'2100-01-2.21','k荚膜、芽胞、细胞壁、鞭毛、阿伯尔染色法、异染颗粒染色')" itemId="2100-01-2.21" itemName="k荚膜、芽胞、细胞壁、鞭毛、阿伯尔染色法、异染颗粒染色"
							name="self1" style="width: 160px;margin-left: 6px;">
						<option value="" style="text-align: center">请选择</option>
						<option value="有" style="text-align: center">有</option>
						<option value="无" style="text-align: center">无</option>
					</select>
				</td>
				<td>
					<input itemId="2100-01-2.21" itemName="d荚膜、芽胞、细胞壁、鞭毛、阿伯尔染色法、异染颗粒染色"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >产气荚膜杆菌、爱德华菌属等非常见菌的分离鉴定</th>
				<th >有</th>
				<td>
					<select onchange="saveSelect(this,'2100-01-2.22','k产气荚膜杆菌、爱德华菌属等非常见菌的分离鉴定')" itemId="2100-01-2.22" itemName="k产气荚膜杆菌、爱德华菌属等非常见菌的分离鉴定"
							name="self1" style="width: 160px;margin-left: 6px;">
						<option value="" style="text-align: center">请选择</option>
						<option value="有" style="text-align: center">有</option>
						<option value="无" style="text-align: center">无</option>
					</select>
				</td>
				<td>
					<input itemId="2100-01-2.22" itemName="d产气荚膜杆菌、爱德华菌属等非常见菌的分离鉴定"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >药物敏感试验(包括K-B法、MIC法)</th>
				<th >有</th>
				<td>
					<select onchange="saveSelect(this,'2100-01-2.23','k药物敏感试验(包括K-B法、MIC法)')" itemId="2100-01-2.23" itemName="k药物敏感试验(包括K-B法、MIC法)"
							name="self1" style="width: 160px;margin-left: 6px;">
						<option value="" style="text-align: center">请选择</option>
						<option value="有" style="text-align: center">有</option>
						<option value="无" style="text-align: center">无</option>
					</select>
				</td>
				<td>
					<input itemId="2100-01-2.23" itemName="d药物敏感试验(包括K-B法、MIC法)"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >编码系统鉴定各种细菌</th>
				<th >有</th>
				<td>
					<select onchange="saveSelect(this,'2100-01-2.24','k编码系统鉴定各种细菌')" itemId="2100-01-2.24" itemName="k编码系统鉴定各种细菌"
							name="self1" style="width: 160px;margin-left: 6px;">
						<option value="" style="text-align: center">请选择</option>
						<option value="有" style="text-align: center">有</option>
						<option value="无" style="text-align: center">无</option>
					</select>
				</td>
				<td>
					<input itemId="2100-01-2.24" itemName="d编码系统鉴定各种细菌"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th rowspan="4">临床细胞分子遗传学</th>
				<th >染色体检查</th>
				<th >有</th>
				<td>
					<select onchange="saveSelect(this,'2100-01-2.25','k染色体检查')" itemId="2100-01-2.25" itemName="k染色体检查"
							name="self1" style="width: 160px;margin-left: 6px;">
						<option value="" style="text-align: center">请选择</option>
						<option value="有" style="text-align: center">有</option>
						<option value="无" style="text-align: center">无</option>
					</select>
				</td>
				<td>
					<input itemId="2100-01-2.25" itemName="d染色体检查"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >核酸检查</th>
				<th >有</th>
				<td>
					<select onchange="saveSelect(this,'2100-01-2.26','k核酸检查')" itemId="2100-01-2.26" itemName="k核酸检查"
							name="self1" style="width: 160px;margin-left: 6px;">
						<option value="" style="text-align: center">请选择</option>
						<option value="有" style="text-align: center">有</option>
						<option value="无" style="text-align: center">无</option>
					</select>
				</td>
				<td>
					<input itemId="2100-01-2.26" itemName="d核酸检查"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >蛋白检查</th>
				<th >有</th>
				<td>
					<select onchange="saveSelect(this,'2100-01-2.27','k染色体检查')" itemId="2100-01-2.27" itemName="k染色体检查"
							name="self1" style="width: 160px;margin-left: 6px;">
						<option value="" style="text-align: center">请选择</option>
						<option value="有" style="text-align: center">有</option>
						<option value="无" style="text-align: center">无</option>
					</select>
				</td>
				<td>
					<input itemId="2100-01-2.27" itemName="d染色体检查"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >质谱分析、个体化基因诊断、荧光原位杂交和测序分析</th>
				<th >有</th>
				<td>
					<select onchange="saveSelect(this,'2100-01-2.28','k质谱分析、个体化基因诊断、荧光原位杂交和测序分析')" itemId="2100-01-2.28" itemName="k质谱分析、个体化基因诊断、荧光原位杂交和测序分析"
							name="self1" style="width: 160px;margin-left: 6px;">
						<option value="" style="text-align: center">请选择</option>
						<option value="有" style="text-align: center">有</option>
						<option value="无" style="text-align: center">无</option>
					</select>
				</td>
				<td>
					<input itemId="2100-01-2.28" itemName="d质谱分析、个体化基因诊断、荧光原位杂交和测序分析"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>

			<tr style="height:32px">
				<th colspan="4" style="text-align: right">合计：</th>
				<th></th>
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