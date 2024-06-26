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
				<h2 style="font-size:150%">住院病历书写指导教学考核评分表</h2>
			</th>
		</tr>
		<tr height="28" >
			<th colspan="3" style="text-align:left;padding-left: 4px;" >
				<div style="margin-top: 8px"><span>培训对象姓名：</span></div>
				<textarea type="text"style="border: 1px solid #bababa;width: 50%;height: 25px;margin-top: -22px;margin-left: 90px"
						  itemId="1500-08-0.3" name="reason" onchange="saveSpeReason(this);"></textarea>
			</th>
			<th style="text-align:left;padding-left: 4px;" colspan="2">所在科室：${deptName}</th>
		</tr>
		<tr height="28" >
			<th colspan="3" style="text-align:left;padding-left: 4px;" >培训基地（医院）：${orgName}</th>
			<th colspan="2" style="text-align:left;padding-left: 4px;" >省市：${cityName}</th>
		</tr>
		<tr style="height:32px;">
			<th >考核项目</th>
			<th style="width: 40%" colspan="2">考核内容及评分标准</th>
			<th style="width: 20%">标准分</th>
			<th style="width: 20%">得分</th>
		</tr>
		<tr style="height:32px">
			<th rowspan="3" style="width: 20%">一、主诉（5分）</th>
			<td style="width: 20%">1、主要症状有遗漏或错误</td>
			<td style="text-align: center; width: 20%">扣2分</td>
			<td style="text-align: center;width: 20%" rowspan="3">5</td>
			<td rowspan="3" style="width: 20%">
				<input onchange="saveScore(this,5);" itemId="1500-08-1.1" itemName="主诉"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
		</tr>
		<tr style="height:32px">
			<td>2、发病时间有遗漏或错误</td>
			<td style="text-align: center">扣1分</td>
		</tr>
		<tr style="height:32px">
			<td>3、主诉叙述不符合要求（如主诉用诊断用语，主诉过于繁琐）</td>
			<td style="text-align: center">扣2分</td>
		</tr>
		<tr style="height:32px">
			<th rowspan="8">二、现病史（15分）</th>
			<td>1、起病情况及患病时间叙述不清，未说明有无诱因与可能的病因及细节</td>
			<td style="text-align: center; ">扣1-2分</td>
			<td style="text-align: center;" rowspan="8">15</td>
			<td rowspan="8">
				<input onchange="saveScore(this,15);" itemId="1500-08-2.1" itemName="现病史"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
		</tr>
		<tr style="height:32px">
			<td>2、发病经过顺序不清，条理性差或有遗漏</td>
			<td style="text-align: center; ">扣0.5-1分</td>
		</tr>
		<tr style="height:32px">
			<td>
				3、主要症状特点未加描述或描述不清
			</td>
			<td style="text-align: center; ">
				扣2-3分
			</td>
		</tr>
		<tr style="height:32px">
			<td>
				4、伴随症状描述不清
			</td>
			<td style="text-align: center; ">
				扣1-2分
			</td>
		</tr>
		<tr style="height:32px">
			<td>
				5、有关鉴别的症状或重要的阴性症状描述不清或遗漏
			</td>
			<td style="text-align: center; ">
				扣1-2分
			</td>
		</tr>
		<tr style="height:32px">
			<td>
				6、诊疗经过叙述不全面
			</td>
			<td style="text-align: center; ">
				扣1-2分
			</td>
		</tr>
		<tr style="height:32px">
			<td>
				7、一般状况未叙述
			</td>
			<td style="text-align: center; ">
				扣0.5-1分
			</td>
		</tr>
		<tr style="height:32px">
			<td>
				8、现病史与主诉内容不一致
			</td>
			<td style="text-align: center; ">
				扣1-2分
			</td>
		</tr>
		<tr style="height:32px">
			<th rowspan="3">三、其他病史（5分）</th>
			<td>1、项目有遗漏</td>
			<td style="text-align: center; ">扣1-3分</td>
			<td style="text-align: center;" rowspan="3">5</td>
			<td rowspan="3">
				<input onchange="saveScore(this,5);" itemId="1500-08-3.1" itemName="其他病史"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
		</tr>
		<tr style="height:32px">
			<td>
				2、有关阴性病史未提及
			</td>
			<td style="text-align: center; ">
				扣1分
			</td>
		</tr>
		<tr style="height:32px">
			<td>
				3、顺序错误
			</td>
			<td style="text-align: center; ">
				扣1分
			</td>
		</tr>
		<tr style="height:32px">
			<th rowspan="5">四、体格检查（10分）</th>
			<td>1、项目有遗漏</td>
			<td style="text-align: center; ">扣1-2分</td>
			<td style="text-align: center;" rowspan="5">10</td>
			<td rowspan="5">
				<input onchange="saveScore(this,10);" itemId="1500-08-4.1" itemName="体格检查"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
		</tr>
		<tr style="height:32px">
			<td>
				2、顺序错误
			</td>
			<td style="text-align: center; ">
				扣1分
			</td>
		</tr>
		<tr style="height:32px">
			<td>
				3、结果错误
			</td>
			<td style="text-align: center; ">
				扣1-2分
			</td>
		</tr>
		<tr style="height:32px">
			<td>
				4、重要体征特点描述不全或不确切
			</td>
			<td style="text-align: center; ">
				扣1-2分
			</td>
		</tr>
		<tr style="height:32px">
			<td>
				5、专科情况描述不全或不确切
			</td>
			<td style="text-align: center; ">
				扣2-3分
			</td>
		</tr>
		<tr style="height:32px">
			<th>五、辅助检查（5分）</th>
			<td>血尿便常规、重要化验、X射线、心电图、B超等相关检查遗漏或描述不正确</td>
			<td style="text-align: center; ">每项扣1分</td>
			<td style="text-align: center;">5</td>
			<td>
				<input onchange="saveScore(this,5);" itemId="1500-08-5.1" itemName="辅助检查"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
		</tr>
		<tr style="height:32px">
			<th rowspan="3">六、诊断（10分）</th>
			<td>1、主要诊断及主要并发症有错误或有遗漏，诊断不规范（如甲亢、风心病等）</td>
			<td style="text-align: center; ">扣2-5分</td>
			<td style="text-align: center;" rowspan="3">10</td>
			<td rowspan="3">
				<input onchange="saveScore(this,10);" itemId="1500-08-6.1" itemName="诊断"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
		</tr>
		<tr style="height:32px">
			<td>
				2、次要诊断遗漏或有错误、不规范
			</td>
			<td style="text-align: center; ">
				扣1-3分
			</td>
		</tr>
		<tr style="height:32px">
			<td>
				3、诊断主次顺序错误
			</td>
			<td style="text-align: center; ">
				扣1-2分
			</td>
		</tr>
		<tr style="height:32px">
			<th rowspan="3">七、首次病程日志病例特点（5分）</th>
			<td>1、内容有遗漏</td>
			<td style="text-align: center; ">遗漏1项扣0.5分</td>
			<td style="text-align: center;" rowspan="3">5</td>
			<td rowspan="3">
				<input onchange="saveScore(this,5);" itemId="1500-08-7.1" itemName="首次病程日志病例特点"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
		</tr>
		<tr style="height:32px">
			<td>
				2、条理性差（未逐条写出，叙述过繁）
			</td>
			<td style="text-align: center; ">
				扣1-2分
			</td>
		</tr>
		<tr style="height:32px">
			<td>
				3、顺序错误（一般项目、症状、体征、辅助检查）
			</td>
			<td style="text-align: center; ">
				扣1-2分
			</td>
		</tr>
		<tr style="height:32px">
			<th rowspan="3">八、诊断分析（10分）</th>
			<td>1、诊断依据不足 </td>
			<td style="text-align: center; ">扣1-2分</td>
			<td style="text-align: center;" rowspan="3">10</td>
			<td rowspan="3">
				<input onchange="saveScore(this,10);" itemId="1500-08-8.1" itemName="诊断分析"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
		</tr>
		<tr style="height:32px">
			<td>
				2、未作必要的鉴别诊断，缺少鉴别的依据或方法
			</td>
			<td style="text-align: center; ">
				扣2-4分
			</td>
		</tr>
		<tr style="height:32px">
			<td>
				3、仅罗列书本内容缺少对本病例实际情况的具体分析与联系
			</td>
			<td style="text-align: center; ">
				扣2-4分
			</td>
		</tr>
		<tr style="height:32px">
			<th rowspan="2">九、诊疗计划（5分）</th>
			<td>1、有错误、有遗漏</td>
			<td style="text-align: center; ">扣2-3分</td>
			<td style="text-align: center;" rowspan="2">5</td>
			<td rowspan="2">
				<input onchange="saveScore(this,5);" itemId="1500-08-9.1" itemName="诊疗计划"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
		</tr>
		<tr style="height:32px">
			<td>
				2、针对性差
			</td>
			<td style="text-align: center; ">
				扣1-2分
			</td>
		</tr>
		<tr style="height:32px">
			<th rowspan="6">十、病程记录（15分）</th>
			<td>1、病程记录不及时、入院后3天无病程记录，长期住院病人超过一周无病程记录</td>
			<td style="text-align: center; ">扣1-2分</td>
			<td style="text-align: center;" rowspan="6">15</td>
			<td rowspan="6">
				<input onchange="saveScore(this,15);" itemId="1500-08-10.1" itemName="病程记录"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
		</tr>
		<tr style="height:32px">
			<td>
				2、病程记录不能反映三级查房的意见
			</td>
			<td style="text-align: center; ">
				扣1-2分
			</td>
		</tr>
		<tr style="height:32px">
			<td>
				3、病程不能反映病情变化、无病情分析、对重要化验及其他辅助检查结果无分析评价、未记录病情变化后治疗措施变更的理由
			</td>
			<td style="text-align: center; ">
				扣1-2分
			</td>
		</tr>
		<tr style="height:32px">
			<td>
				4、危重症病例无抢救记录或记录不及时、不准确
			</td>
			<td style="text-align: center; ">
				扣1-2分
			</td>
		</tr>
		<tr style="height:32px">
			<td>
				5、长期住院病人无阶段小结，无交接班记录
			</td>
			<td style="text-align: center; ">
				扣1-2分
			</td>
		</tr>
		<tr style="height:32px">
			<td>
				6、会诊记录单及各种记录检查单填写有缺项（姓名、病历号、日期、诊断、签名等）
			</td>
			<td style="text-align: center; ">
				扣0.5-2分
			</td>
		</tr>
		<tr style="height:32px">
			<th rowspan="4">十一、提问（15分）</th>
			<td>结合本病例提3个问题</td>
			<td></td>
			<td style="text-align: center;" rowspan="4">15</td>
			<td rowspan="4">
				<input onchange="saveScore(this,15);" itemId="1500-08-11.1" itemName="提问"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
		</tr>
		<tr style="height:32px">
			<td>
				1、问题1
			</td>
			<td style="text-align: center; ">
				扣2-5分
			</td>
		</tr>
		<tr style="height:32px">
			<td>
				2、问题2
			</td>
			<td style="text-align: center; ">
				扣2-5分
			</td>
		</tr>
		<tr style="height:32px">
			<td>
				3、问题3
			</td>
			<td style="text-align: center; ">
				扣2-5分
			</td>
		</tr>
		<tr style="height:32px;">
			<td colspan="3" style="text-align: right;">合计：</td>
			<td style="text-align: center;">100</td>
			<td><span id="selfTotalled"></span></td>
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
				<fmt:formatDate value="${evaluationDate}" pattern="yyyy 年 MM 月 dd 日"/>&#12288;&#12288;
				<input id="evaluationDate"
					   value="<fmt:formatDate value="${evaluationDate}" pattern="yyyy-MM-dd" />"
					   hidden>
			</th>
		</tr>
		</tbody>
	</table>
</div>

<div class="button">
	<input class="btn_green" type="button" value="打&#12288;印" onclick="printHospitalScore('evaluationInfo_1500_blsx');"/>&#12288;
	<c:if test="${edit ne 'N'}">
		<input class="btn_green" type="button" value="提&#12288;交" onclick="subInfo();"/>&#12288;
	</c:if>
</div>
</body>
</html>