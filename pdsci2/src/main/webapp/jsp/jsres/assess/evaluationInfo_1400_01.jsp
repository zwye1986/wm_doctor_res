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
			}s
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
            if (parseInt(self1Score) >= 220) {
                self1Score = 2;
            } else if (parseInt(self1Score) >= 220*0.9) {
                self1Score = 1;
            } else if (parseInt(self1Score) >= 220*0.85) {
                self1Score = 0.5;
            } else {
                self1Score = 0;
            }
            if (parseInt(self2Score) >= 305) {
                self2Score = 4;
            } else if (parseInt(self2Score) >= 305*0.9) {
                self2Score = 2;
            } else if (parseInt(self2Score) >= 305*0.85) {
                self2Score = 1;
            } else {
                self2Score = 0;
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
<div class="div_table" style="overflow: auto;max-height: 570px;">
	<table cellpadding="4" style="width: 1000px">
		<tbody>
			<tr height="34" class="firstRow">
				<th colspan="5">
					<h2 style="font-size:150%">骨科疾病种类/临床技能操作</h2>
				</th>
			</tr>
			<tr style="height:32px;">
				<th colspan="2">疾病种类/临床技能操作</th>
				<th >标准</th>
				<th >实际数</th>
				<th >低于标准数（划√）</th>
			</tr>
			<tr style="height:32px">
				<th rowspan="9">疾病种类</th>
				<th >常见部位骨折</th>
				<th >≥50</th>
				<td>
					<input onchange="saveScore(this,50);" itemId="1400-01-1.1" itemName="k常见部位骨折"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="1400-01-1.1" itemName="d常见部位骨折"  name="less1"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >常见部位关节脱位</th>
				<th >≥50</th>
				<td>
					<input onchange="saveScore(this,50);" itemId="1400-01-1.2" itemName="k常见部位关节脱位"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="1400-01-1.2" itemName="d常见部位关节脱位"  name="less1"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >运动系统慢性损伤</th>
				<th >≥25</th>
				<td>
					<input onchange="saveScore(this,25);" itemId="1400-01-1.3" itemName="k运动系统慢性损伤"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="1400-01-1.3" itemName="d运动系统慢性损伤"  name="less1"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >腰椎间盘突出症</th>
				<th >≥10</th>
				<td>
					<input onchange="saveScore(this,10);" itemId="1400-01-1.4" itemName="k腰椎间盘突出症"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="1400-01-1.4" itemName="d腰椎间盘突出症"  name="less1"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >颈椎病</th>
				<th >≥10</th>
				<td>
					<input onchange="saveScore(this,10);" itemId="1400-01-1.5" itemName="k颈椎病"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="1400-01-1.5" itemName="d颈椎病"  name="less1"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >脊柱畸形</th>
				<th >≥5</th>
				<td>
					<input onchange="saveScore(this,5);" itemId="1400-01-1.6" itemName="k脊柱畸形"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="1400-01-1.6" itemName="d脊柱畸形"  name="less1"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >骨与关节感染</th>
				<th >≥10</th>
				<td>
					<input onchange="saveScore(this,10);" itemId="1400-01-1.7" itemName="k骨与关节感染"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="1400-01-1.7" itemName="d骨与关节感染"  name="less1"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >关节非感染性关节炎</th>
				<th >≥50</th>
				<td>
					<input onchange="saveScore(this,50);" itemId="1400-01-1.8" itemName="k关节非感染性关节炎"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="1400-01-1.8" itemName="d关节非感染性关节炎"  name="less1"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >骨关节先天性或发育性畸形</th>
				<th >≥10</th>
				<td>
					<input onchange="saveScore(this,10);" itemId="1400-01-1.9" itemName="k骨关节先天性或发育性畸形"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="1400-01-1.9" itemName="d骨关节先天性或发育性畸形"  name="less1"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th rowspan="15">临床技能操作（或手术）</th>
				<th >急性运动损伤</th>
				<th >≥10</th>
				<td>
					<input onchange="saveScore(this,10);" itemId="1400-01-2.1" itemName="k急性运动损伤"  name="self2"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="1400-01-2.1" itemName="d急性运动损伤"  name="less2"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >骨软组织肿瘤</th>
				<th >≥10</th>
				<td>
					<input onchange="saveScore(this,10);" itemId="1400-01-2.2" itemName="k骨软组织肿瘤"  name="self2"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="1400-01-2.2" itemName="d骨软组织肿瘤"  name="less2"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >常见部位骨折的手法复位,夹板、石膏外固定</th>
				<th >≥50</th>
				<td>
					<input onchange="saveScore(this,50);" itemId="1400-01-2.3" itemName="k常见部位骨折的手法复位,夹板、石膏外固定"  name="self2"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="1400-01-2.3" itemName="d常见部位骨折的手法复位,夹板、石膏外固定"  name="less2"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >常见部位关节脱位的手法复位</th>
				<th >≥25</th>
				<td>
					<input onchange="saveScore(this,25);" itemId="1400-01-2.4" itemName="k常见部位关节脱位的手法复位"  name="self2"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="1400-01-2.4" itemName="d常见部位关节脱位的手法复位"  name="less2"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >常见部位的骨牵引</th>
				<th >≥25</th>
				<td>
					<input onchange="saveScore(this,25);" itemId="1400-01-2.5" itemName="k常见部位的骨牵引"  name="self2"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="1400-01-2.5" itemName="d常见部位的骨牵引"  name="less2"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >手外伤的清创、缝合、皮肤缺损的修复及肌腱吻合</th>
				<th >≥25</th>
				<td>
					<input onchange="saveScore(this,25);" itemId="1400-01-2.6" itemName="k手外伤的清创、缝合、皮肤缺损的修复及肌腱吻合"  name="self2"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="1400-01-2.6" itemName="d手外伤的清创、缝合、皮肤缺损的修复及肌腱吻合"  name="less2"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >开放骨折的清创、切开复位内固定</th>
				<th >≥25</th>
				<td>
					<input onchange="saveScore(this,25);" itemId="1400-01-2.7" itemName="k开放骨折的清创、切开复位内固定"  name="self2"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="1400-01-2.7" itemName="d开放骨折的清创、切开复位内固定"  name="less2"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >颈腰椎退行性疾病手术</th>
				<th >≥25</th>
				<td>
					<input onchange="saveScore(this,25);" itemId="1400-01-2.8" itemName="k颈腰椎退行性疾病手术"  name="self2"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="1400-01-2.8" itemName="d颈腰椎退行性疾病手术"  name="less2"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >脊柱畸形矫形手术</th>
				<th >≥5</th>
				<td>
					<input onchange="saveScore(this,5);" itemId="1400-01-2.9" itemName="k脊柱畸形矫形手术"  name="self2"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="1400-01-2.9" itemName="d脊柱畸形矫形手术"  name="less2"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >脊柱骨折脱位手术</th>
				<th >≥5</th>
				<td>
					<input onchange="saveScore(this,5);" itemId="1400-01-2.10" itemName="k脊柱骨折脱位手术"  name="self2"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="1400-01-2.10" itemName="d脊柱骨折脱位手术"  name="less2"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >人工关节置换手术</th>
				<th >≥25</th>
				<td>
					<input onchange="saveScore(this,25);" itemId="1400-01-2.11" itemName="k人工关节置换手术"  name="self2"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="1400-01-2.11" itemName="d人工关节置换手术"  name="less2"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >运动损伤功能重建手术</th>
				<th >≥10</th>
				<td>
					<input onchange="saveScore(this,10);" itemId="1400-01-2.12" itemName="k运动损伤功能重建手术"  name="self2"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="1400-01-2.12" itemName="d运动损伤功能重建手术"  name="less2"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >运动系统慢性损伤的局部注射</th>
				<th >≥50</th>
				<td>
					<input onchange="saveScore(this,50);" itemId="1400-01-2.13" itemName="k运动系统慢性损伤的局部注射"  name="self2"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="1400-01-2.13" itemName="d运动系统慢性损伤的局部注射"  name="less2"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >骨、软组织感染手术</th>
				<th >≥5</th>
				<td>
					<input onchange="saveScore(this,5);" itemId="1400-01-2.14" itemName="k骨、软组织感染手术"  name="self2"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="1400-01-2.14" itemName="d骨、软组织感染手术"  name="less2"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th >四肢常见的骨及软组织瘤手术</th>
				<th >≥10</th>
				<td>
					<input onchange="saveScore(this,10);" itemId="1400-01-2.15" itemName="k四肢常见的骨及软组织瘤手术"  name="self2"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="1400-01-2.15" itemName="d四肢常见的骨及软组织瘤手术"  name="less2"  class="input" type="text" style="height:20px; text-align: center"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th colspan="4" style="text-align: right">合计：</th>
				<th><span id="expertTotalled"></span></th>
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