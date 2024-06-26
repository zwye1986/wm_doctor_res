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
            //实得分合计
            var score = 0;
            // 疾病种类
            var jScore = 0;
            // 技能操作
            var cScore = 0;
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "self1") {
                    jScore = Number(jScore) + Number(itemIdList[i].value);
                }
                if (itemIdList[i].getAttribute("name") == "self2"){
                    cScore = Number(cScore) + Number(itemIdList[i].value);
                }
            }
            score = jScore + cScore;
            $("#jTotalled").text(check(jScore.toFixed(1)));
            $("#cTotalled").text(check(cScore.toFixed(1)));
            $("#expertTotalled").text(check(score.toFixed(1)));
        }

        function subInfo() {
			var itemIdList = $("input");
			for (var i = 0; i < itemIdList.length; i++) {
				if ((itemIdList[i].getAttribute("name") == "self1" || itemIdList[i].getAttribute("name") == "self2"|| itemIdList[i].getAttribute("name") == "self")
						&& itemIdList[i].value == "") {
					$(itemIdList[i]).focus();
					top.jboxTip("有输入框未输入数据，请输入数据！");
					return;
				}
			}
            // 疾病和临床的分数
            var selfOneScore = $("#jTotalled").text();//疾病分数
            var selfTwoScore = $("#cTotalled").text();//临床分数
            selfOneScore = parseInt(selfOneScore / 155 * 100);
            selfTwoScore = parseInt(selfTwoScore / 150 * 100);
            var url = "<s:url value='/jsres/supervisio/saveManyScheduleTotalled'/>";
            var data = {
                "itemIdOne": 'jScoreInfo3',
                "itemIdTwo": 'cScoreInfo3',
                "itemName": 'evaluationInfo_1500',
                "selfOneScore": selfOneScore,
                "selfTwoScore": selfTwoScore,
                "orgFlow": '${orgFlow}',
                "orgName": '${orgName}',
                "speId": '${speId}',
                "subjectFlow": '${subjectFlow}',
                "roleFlag": '${roleFlag}'
            };
            top.jboxPost(url, data, function (resp) {
                if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                    window.parent.frames["jbox-message-iframe"].$("#jScoreInfo3")[0].value = selfOneScore;
                    window.parent.frames["jbox-message-iframe"].$("#cScoreInfo3")[0].value = selfTwoScore;
                    var scoreOneAll = 0;
                    var scoreTwoAll = 0;
                    var oneNum = 0;
                    var twoNum = 0;
                    var scoreOneInfoList = window.parent.frames["jbox-message-iframe"].$("input[name='jScoreInfo']");
                    var scoreTwoInfoList = window.parent.frames["jbox-message-iframe"].$("input[name='cScoreInfo']");
                    for (var i = 1; i <= scoreOneInfoList.length; i++) {
                        if (!isNaN(parseInt(window.parent.frames["jbox-message-iframe"].$("#jScoreInfo" + i)[0].value))) {
                            scoreOneAll = parseInt(window.parent.frames["jbox-message-iframe"].$("#jScoreInfo" + i)[0].value) + parseInt(scoreOneAll);
                            oneNum++;
                        }
                    }
                    for (var i = 1; i <= scoreTwoInfoList.length; i++) {
                        if (!isNaN(parseInt(window.parent.frames["jbox-message-iframe"].$("#cScoreInfo" + i)[0].value))) {
                            scoreTwoAll = parseInt(window.parent.frames["jbox-message-iframe"].$("#cScoreInfo" + i)[0].value) + parseInt(scoreTwoAll);
                            twoNum++;
                        }
                    }
                    selfOneScore = parseInt(scoreOneAll / oneNum);
                    selfTwoScore = parseInt(scoreTwoAll / twoNum);
                    if (selfOneScore >= 100) {
                        selfOneScore = 2;
                    } else if (selfOneScore >= 90) {
                        selfOneScore = 1;
                    } else if (selfOneScore >= 85) {
                        selfOneScore = 0.5;
                    } else {
                        selfOneScore = 0;
                    }
                    if (selfTwoScore >= 100) {
                        selfTwoScore = 4;
                    } else if (selfTwoScore >= 90) {
                        selfTwoScore = 2;
                    } else if (selfTwoScore >= 85) {
                        selfTwoScore = 1;
                    } else {
                        selfTwoScore = 0;
                    }
                    var inputSelf1;
                    var inputSelf2;
					if (${ roleFlag==("baseExpert")}) {
                        inputSelf1 = window.parent.frames["jbox-message-iframe"].$("#fubiao11");
                        inputSelf2 = window.parent.frames["jbox-message-iframe"].$("#fubiao12");
                    } else if (${roleFlag==("expertLeader")}) {
                        inputSelf1 = window.parent.frames["jbox-message-iframe"].$("#fubiao11Expert");
                        inputSelf2 = window.parent.frames["jbox-message-iframe"].$("#fubiao12Expert");
                    }

                    inputSelf1[0].value = selfOneScore;
                    inputSelf2[0].value = selfTwoScore;
					if (${ roleFlag==("baseExpert")}) {
                        window.parent.frames["jbox-message-iframe"].saveScore(inputSelf1[0], selfOneScore);
                        window.parent.frames["jbox-message-iframe"].saveScore(inputSelf2[0], selfTwoScore);
                    } else if (${roleFlag==("expertLeader")}) {
                        window.parent.frames["jbox-message-iframe"].saveScore4Expert(inputSelf1[0], selfOneScore);
                        window.parent.frames["jbox-message-iframe"].saveScore4Expert(inputSelf2[0], selfTwoScore);
                    }
                    // top.jboxClose();
                } else {
                    top.jboxTip(resp);
                }
            }, null, false);
        };

        //保存自评分
        function saveScore(expl, num) {
            var score = expl.value;
            var itemId = expl.getAttribute("itemId");
            var itemName = expl.getAttribute("itemName");
			var reg = /(^[0-9]\d*$)/;
            var reg1 = /^\d+(\.0)$/;
            if (itemName.startsWith("d")) {
                if (!(score >= 0 && score <= num && reg.test(score))) {
                    expl.value = expl.getAttribute("preValue");
                    top.jboxTip("评分只能是正整数");
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
                top.jboxTip("评分只能是正整数");
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
				<th colspan="8">
					<h2 style="font-size:150%">儿外科(骨科)疾病种类/临床技能操作</h2>
				</th>
			</tr>
			<tr style="height:32px;">
				<th colspan="2">年收治病人数(人次)</th>
				<th rowspan="3" style="width: 11%">标准</th>
				<th style="width: 11%">≥176</th>
				<th rowspan="3" style="width: 11%">实际数</th>
				<td style="width: 11%">
					<input onchange="saveScore(this,176);" itemId="1500-03-1.1" itemName="k3年收治病人数"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<th rowspan="3" style="width: 11%">低于标准数<br>（划√）</th>
				<td style="width: 11%">
					<input itemId="1500-03-1.1" itemName="d3年收治病人数"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px;">
				<th colspan="2">年门诊量(人次)</th>
				<th >≥10000</th>
				<td>
					<input onchange="saveScore(this,10000);" itemId="1500-03-1.2" itemName="k3年门诊量"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="1500-03-1.2" itemName="d3年门诊量"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px;">
				<th colspan="2">年急诊量(人次)</th>
				<th >≥2000</th>
				<td>
					<input onchange="saveScore(this,2000);" itemId="1500-03-1.3" itemName="k3年急诊量"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="1500-03-1.3" itemName="d3年急诊量"  name="less"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px;">
				<th colspan="2">疾病种类/临床技能操作</th>
				<th colspan="2">标准</th>
				<th colspan="2">实际数</th>
				<th colspan="2">低于标准数（划√）</th>
			</tr>
			<tr style="height:32px">
				<th rowspan="8">疾病种类</th>
				<th >常见部位骨折</th>
				<th colspan="2">≥50</th>
				<td colspan="2">
					<input onchange="saveScore(this,50);" itemId="1500-03-2.1" itemName="k常见部位骨折"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td colspan="2">
					<input itemId="1500-03-2.1" itemName="d常见部位骨折"  name="less1"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >常见部位关节脱位与半脱位</th>
				<th colspan="2">≥50</th>
				<td colspan="2">
					<input onchange="saveScore(this,50);" itemId="1500-03-2.2" itemName="k常见部位关节脱位与半脱位"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td colspan="2">
					<input itemId="1500-03-2.2" itemName="d常见部位关节脱位与半脱位"  name="less1"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >骨关节感染</th>
				<th colspan="2">≥5</th>
				<td colspan="2">
					<input onchange="saveScore(this,5);" itemId="1500-03-2.3" itemName="k骨关节感染"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td colspan="2">
					<input itemId="1500-03-2.3" itemName="d骨关节感染"  name="less1"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >先天性马蹄内翻足</th>
				<th colspan="2">≥10</th>
				<td colspan="2">
					<input onchange="saveScore(this,10);" itemId="1500-03-2.4" itemName="k先天性马蹄内翻足"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td colspan="2">
					<input itemId="1500-03-2.4" itemName="d先天性马蹄内翻足"  name="less1"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >发育性髋脱位</th>
				<th colspan="2">≥10</th>
				<td colspan="2">
					<input onchange="saveScore(this,10);" itemId="1500-03-2.5" itemName="k发育性髋脱位"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td colspan="2">
					<input itemId="1500-03-2.5" itemName="d发育性髋脱位"  name="less1"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >赘生指并指畸形</th>
				<th colspan="2">≥10</th>
				<td colspan="2">
					<input onchange="saveScore(this,10);" itemId="1500-03-2.6" itemName="k赘生指并指畸形"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td colspan="2">
					<input itemId="1500-03-2.6" itemName="d赘生指并指畸形"  name="less1"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >骨肿瘤</th>
				<th colspan="2">≥10</th>
				<td colspan="2">
					<input onchange="saveScore(this,10);" itemId="1500-03-2.7" itemName="k骨肿瘤"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td colspan="2">
					<input itemId="1500-03-2.7" itemName="d骨肿瘤"  name="less1"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >脊柱侧弯与后凸畸形</th>
				<th colspan="2">≥10</th>
				<td colspan="2">
					<input onchange="saveScore(this,10);" itemId="1500-03-2.8" itemName="k脊柱侧弯与后凸畸形"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td colspan="2">
					<input itemId="1500-03-2.8" itemName="d脊柱侧弯与后凸畸形"  name="less1"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
				<td style="display: none"><span id="jTotalled"></span></td>
			</tr>
			<tr style="height:32px">
				<th rowspan="7">临床技能操作</th>
				<th >常见部位关节脱位的手法复位</th>
				<th colspan="2">≥25</th>
				<td colspan="2">
					<input onchange="saveScore(this,25);" itemId="1500-03-3.1" itemName="k常见部位关节脱位的手法复位"  name="self2"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td colspan="2">
					<input itemId="1500-03-3.1" itemName="d常见部位关节脱位的手法复位"  name="less2"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >常见部位的骨牵引</th>
				<th colspan="2">≥25</th>
				<td colspan="2">
					<input onchange="saveScore(this,25);" itemId="1500-03-3.2" itemName="k常见部位的骨牵引"  name="self2"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td colspan="2">
					<input itemId="1500-03-3.2" itemName="d常见部位的骨牵引"  name="less2"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >手外伤的清创、 缝合、 皮肤缺损的修复及肌腱吻合</th>
				<th colspan="2">≥25</th>
				<td colspan="2">
					<input onchange="saveScore(this,25);" itemId="1500-03-3.3" itemName="k手外伤的清创、 缝合、 皮肤缺损的修复及肌腱吻合"  name="self2"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td colspan="2">
					<input itemId="1500-03-3.3" itemName="d手外伤的清创、 缝合、 皮肤缺损的修复及肌腱吻合"  name="less2"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >开放骨折的清创、 切开复位内固定</th>
				<th colspan="2">≥25</th>
				<td colspan="2">
					<input onchange="saveScore(this,25);" itemId="1500-03-3.4" itemName="k开放骨折的清创、 切开复位内固定"  name="self2"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td colspan="2">
					<input itemId="1500-03-3.4" itemName="d开放骨折的清创、 切开复位内固定"  name="less2"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >发育性髋脱位复位固定及手术</th>
				<th colspan="2">≥20</th>
				<td colspan="2">
					<input onchange="saveScore(this,20);" itemId="1500-03-3.5" itemName="k发育性髋脱位复位固定及手术"  name="self2"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td colspan="2">
					<input itemId="1500-03-3.5" itemName="d发育性髋脱位复位固定及手术"  name="less2"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >脊柱侧弯矫形手术</th>
				<th colspan="2">≥20</th>
				<td colspan="2">
					<input onchange="saveScore(this,20);" itemId="1500-03-3.6" itemName="k脊柱侧弯矫形手术"  name="self2"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td colspan="2">
					<input itemId="1500-03-3.6" itemName="d脊柱侧弯矫形手术"  name="less2"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >四肢常见的骨及软组织瘤手术</th>
				<th colspan="2">≥10</th>
				<td colspan="2">
					<input onchange="saveScore(this,10);" itemId="1500-03-3.7" itemName="k四肢常见的骨及软组织瘤手术"  name="self2"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td colspan="2">
					<input itemId="1500-03-3.7" itemName="d四肢常见的骨及软组织瘤手术"  name="less2"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
				<td style="display: none"><span id="cTotalled"></span></td>
			</tr>
			<tr style="height:32px">
				<th colspan="6" style="text-align: right">合计：</th>
				<th colspan="2"><span id="expertTotalled"></span></th>
			</tr>
		</tbody>

	</table>
</div>

<c:if test="${(GlobalConstant.USER_LIST_GLOBAL ne roleFlag) && (isRead ne GlobalConstant.RECORD_STATUS_Y) && (editFlag ne 'N')}">
	<div class="button" >
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