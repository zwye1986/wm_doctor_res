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
				if ((itemIdList[i].getAttribute("name") == "self1") && itemIdList[i].value == "") {
					$(itemIdList[i]).focus();
					top.jboxTip("有输入框未输入数据，请输入数据！");
					return;
				}
			}
			var expertTotal = Number($('#selfTotalled').text())/529 * 100;
            var url = "<s:url value='/jsres/supervisio/saveScheduleTotalled'/>";
            var data = {
                "itemId": 'cScoreInfo2',
                "itemName": 'evaluationInfo_1800',
                "score": expertTotal,
                "orgFlow": '${orgFlow}',
                "orgName": '${orgName}',
                "speId": '${speId}',
                "subjectFlow": '${subjectFlow}',
                "roleFlag": '${roleFlag}'
            };
            top.jboxPost(url, data, function (resp) {
                if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
					 data = {
						"itemId": 'AScoreInfo2',
						"itemName": 'evaluationInfo_1800',
						"score": expertTotal,
						"orgFlow": '${orgFlow}',
						"orgName": '${orgName}',
						"speId": '${speId}',
						"subjectFlow": '${subjectFlow}',
						"roleFlag": '${roleFlag}'
					};
					top.jboxPost(url, data, function (resp) {
						if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
							window.parent.frames["jbox-message-iframe"].$("#cScoreInfo2")[0].value = expertTotal;
							window.parent.frames["jbox-message-iframe"].$("#AScoreInfo2")[0].value = expertTotal;
							var scoreAll = 0;
							var scoreAll1 = 0;
							var num = 0;
							var num1 = 0;
							var scoreInfoList = window.parent.frames["jbox-message-iframe"].$("input[name='cScoreInfo']");
							var scoreInfoList1 = window.parent.frames["jbox-message-iframe"].$("input[name='AScoreInfo']");
							for (var i = 1; i <= scoreInfoList.length; i++) {
								if (!isNaN(parseInt(window.parent.frames["jbox-message-iframe"].$("#cScoreInfo" + i)[0].value))) {
									scoreAll = parseInt(window.parent.frames["jbox-message-iframe"].$("#cScoreInfo" + i)[0].value) + parseInt(scoreAll);
									num++;
								}
							}
							for (var i = 1; i <= scoreInfoList1.length; i++) {
								if (!isNaN(parseInt(window.parent.frames["jbox-message-iframe"].$("#AScoreInfo" + i)[0].value))) {
									scoreAll1 = parseInt(window.parent.frames["jbox-message-iframe"].$("#AScoreInfo" + i)[0].value) + parseInt(scoreAll1);
									num1++;
								}
							}
							scoreAll = scoreAll / num;
							scoreAll1 = scoreAll1 / num1;
							if (parseInt(scoreAll) >= 100) {
								scoreAll = 4;
							}else if (parseInt(scoreAll) >= 90) {
								scoreAll = 2;
							} else if (parseInt(scoreAll) >= 85) {
								scoreAll = 1;
							} else {
								scoreAll = 0;
							}
							if (parseInt(scoreAll1) >= 90) {
								scoreAll1 = 8;
							}else if (parseInt(scoreAll1) >= 80) {
								scoreAll1 = 4;
							} else {
								scoreAll1 = 0;
							}
							var input;
							if (${ roleFlag==("baseExpert")}) {
								input = window.parent.frames["jbox-message-iframe"].$("#fubiao2");
								input1 = window.parent.frames["jbox-message-iframe"].$("#fubiao112");
							} else if (${roleFlag==("expertLeader")}) {
								input = window.parent.frames["jbox-message-iframe"].$("#fubiao2Expert");
								input1 = window.parent.frames["jbox-message-iframe"].$("#fubiao112Expert");
							}
							input[0].value = scoreAll;
							input1[0].value = scoreAll1;
							if (${ roleFlag==("baseExpert")}) {
								window.parent.frames["jbox-message-iframe"].saveScore(input[0], scoreAll);
								window.parent.frames["jbox-message-iframe"].saveScore(input1[0], scoreAll1);
							} else if (${roleFlag==("expertLeader")}) {
								window.parent.frames["jbox-message-iframe"].saveScore4Expert(input[0], scoreAll);
								window.parent.frames["jbox-message-iframe"].saveScore4Expert(input1[0], scoreAll1);
							}
						}else {
							top.jboxTip(resp);
						}
					}, null, false);
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
	<table cellpadding="4" style="width: 1000px;font-size: inherit">
		<tbody>
			<tr height="34" class="firstRow">
				<th colspan="5">
					<h2 style="font-size:150%">耳鼻咽喉科住院患者手术种类/年</h2>
				</th>
			</tr>
			<tr style="height:32px;">
				<th >手术名称</th>
				<th >标准</th>
				<th >实际数</th>
				<th >低于标准数（划√）</th>
			</tr>
			<tr style="height:32px">
				<th style="text-align: left;">耳外伤手术</th>
				<th >2</th>
				<td>
					<input onchange="saveScore(this,2);" itemId="1800-04-1.1" itemName="k耳外伤手术"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="1800-04-1.1" itemName="d耳外伤手术"  name="less1"  class="input" type="text" style="height:20px; text-align: center" readonly="readonly"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th style="text-align: left;">鼓室成形术</th>
				<th >25</th>
				<td>
					<input onchange="saveScore(this,25);" itemId="1800-04-1.2" itemName="k鼓室成形术"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="1800-04-1.2" itemName="d鼓室成形术"  name="less1"  class="input" type="text" style="height:20px; text-align: center" readonly="readonly"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th style="text-align: left;">鼓膜置管术</th>
				<th >25</th>
				<td>
					<input onchange="saveScore(this,25);" itemId="1800-04-1.3" itemName="k鼓膜置管术"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="1800-04-1.3" itemName="d鼓膜置管术"  name="less1"  class="input" type="text" style="height:20px; text-align: center" readonly="readonly"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th style="text-align: left;">外耳道胆脂瘤去除术</th>
				<th >4</th>
				<td>
					<input onchange="saveScore(this,4);" itemId="1800-04-1.4" itemName="k外耳道胆脂瘤去除术"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="1800-04-1.4" itemName="d外耳道胆脂瘤去除术"  name="less1"  class="input" type="text" style="height:20px; text-align: center" readonly="readonly"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th style="text-align: left;">乳突根治术</th>
				<th >8</th>
				<td>
					<input onchange="saveScore(this,8);" itemId="1800-04-1.5" itemName="k乳突根治术"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="1800-04-1.5" itemName="d乳突根治术"  name="less1"  class="input" type="text" style="height:20px; text-align: center" readonly="readonly"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th style="text-align: left;">面神经减压术</th>
				<th >3</th>
				<td>
					<input onchange="saveScore(this,3);" itemId="1800-04-1.6" itemName="k面神经减压术"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="1800-04-1.6" itemName="d面神经减压术"  name="less1"  class="input" type="text" style="height:20px; text-align: center" readonly="readonly"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th style="text-align: left;">经中耳颅内脓肿引流术</th>
				<th >1</th>
				<td>
					<input onchange="saveScore(this,1);" itemId="1800-04-1.7" itemName="k经中耳颅内脓肿引流术"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="1800-04-1.7" itemName="d经中耳颅内脓肿引流术"  name="less1"  class="input" type="text" style="height:20px; text-align: center" readonly="readonly"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th style="text-align: left;">颞骨次全切除术</th>
				<th >2</th>
				<td>
					<input onchange="saveScore(this,2);" itemId="1800-04-1.8" itemName="k颞骨次全切除术"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="1800-04-1.8" itemName="d颞骨次全切除术"  name="less1"  class="input" type="text" style="height:20px; text-align: center" readonly="readonly"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th style="text-align: left;">小儿畸形成形术</th>
				<th >1</th>
				<td>
					<input onchange="saveScore(this,1);" itemId="1800-04-1.9" itemName="k小儿畸形成形术"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="1800-04-1.9" itemName="d小儿畸形成形术"  name="less1"  class="input" type="text" style="height:20px; text-align: center" readonly="readonly"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th style="text-align: left;">耳硬化症手术</th>
				<th >1</th>
				<td>
					<input onchange="saveScore(this,1);" itemId="1800-04-1.10" itemName="k耳硬化症手术"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="1800-04-1.10" itemName="d耳硬化症手术"  name="less1"  class="input" type="text" style="height:20px; text-align: center" readonly="readonly"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th style="text-align: left;">电子耳蜗植入术</th>
				<th >2</th>
				<td>
					<input onchange="saveScore(this,2);" itemId="1800-04-1.11" itemName="k电子耳蜗植入术"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="1800-04-1.11" itemName="d电子耳蜗植入术"  name="less1"  class="input" type="text" style="height:20px; text-align: center" readonly="readonly"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th style="text-align: left;">内淋巴囊减压术</th>
				<th >1</th>
				<td>
					<input onchange="saveScore(this,1);" itemId="1800-04-1.12" itemName="k内淋巴囊减压术"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="1800-04-1.12" itemName="d内淋巴囊减压术"  name="less1"  class="input" type="text" style="height:20px; text-align: center" readonly="readonly"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th style="text-align: left;">鼻内镜下下鼻甲部分切除术</th>
				<th >50</th>
				<td>
					<input onchange="saveScore(this,50);" itemId="1800-04-1.13" itemName="k鼻内镜下下鼻甲部分切除术"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="1800-04-1.13" itemName="d鼻内镜下下鼻甲部分切除术"  name="less1"  class="input" type="text" style="height:20px; text-align: center" readonly="readonly"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th style="text-align: left;">鼻内镜下额窦内病变去除术</th>
				<th >30</th>
				<td>
					<input onchange="saveScore(this,30);" itemId="1800-04-1.14" itemName="k鼻内镜下额窦内病变去除术"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="1800-04-1.14" itemName="d鼻内镜下额窦内病变去除术"  name="less1"  class="input" type="text" style="height:20px; text-align: center" readonly="readonly"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th style="text-align: left;">鼻内镜下眶内脓肿引流术</th>
				<th >2</th>
				<td>
					<input onchange="saveScore(this,2);" itemId="1800-04-1.15" itemName="k鼻内镜下眶内脓肿引流术"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="1800-04-1.15" itemName="d鼻内镜下眶内脓肿引流术"  name="less1"  class="input" type="text" style="height:20px; text-align: center" readonly="readonly"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th style="text-align: left;">鼻内镜下鼻骨骨折复位术、鼻中隔矫正术</th>
				<th >3</th>
				<td>
					<input onchange="saveScore(this,3);" itemId="1800-04-1.16" itemName="k鼻内镜下鼻骨骨折复位术、鼻中隔矫正术"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="1800-04-1.16" itemName="d鼻内镜下鼻骨骨折复位术、鼻中隔矫正术"  name="less1"  class="input"
						   type="text" style="height:20px; text-align: center" readonly="readonly"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th style="text-align: left;">鼻内镜下筛窦内病变去除术</th>
				<th >40</th>
				<td>
					<input onchange="saveScore(this,40);" itemId="1800-04-1.17" itemName="k鼻内镜下筛窦内病变去除术"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="1800-04-1.17" itemName="d鼻内镜下筛窦内病变去除术"  name="less1"  class="input" type="text" style="height:20px; text-align: center" readonly="readonly"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th style="text-align: left;">鼻内镜下上颌窦内病变去除术</th>
				<th >50</th>
				<td>
					<input onchange="saveScore(this,50);" itemId="1800-04-1.18" itemName="k鼻内镜下上颌窦内病变去除术"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="1800-04-1.18" itemName="d鼻内镜下上颌窦内病变去除术"  name="less1"  class="input" type="text" style="height:20px; text-align: center" readonly="readonly"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th style="text-align: left;">鼻内镜下蝶窦内病变去除术</th>
				<th >30</th>
				<td>
					<input onchange="saveScore(this,30);" itemId="1800-04-1.19" itemName="k鼻内镜下蝶窦内病变去除术"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="1800-04-1.19" itemName="d鼻内镜下蝶窦内病变去除术"  name="less1"  class="input" type="text" style="height:20px; text-align: center" readonly="readonly"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th style="text-align: left;">鼻内镜下脑脊液鼻漏修补术</th>
				<th >1</th>
				<td>
					<input onchange="saveScore(this,1);" itemId="1800-04-1.20" itemName="k鼻内镜下脑脊液鼻漏修补术"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="1800-04-1.20" itemName="d鼻内镜下脑脊液鼻漏修补术"  name="less1"  class="input" type="text" style="height:20px; text-align: center" readonly="readonly"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th style="text-align: left;">鼻内镜下鼻中隔矫正术</th>
				<th >50</th>
				<td>
					<input onchange="saveScore(this,50);" itemId="1800-04-1.21" itemName="k鼻内镜下鼻中隔矫正术"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="1800-04-1.21" itemName="d鼻内镜下鼻中隔矫正术"  name="less1"  class="input" type="text" style="height:20px; text-align: center" readonly="readonly"/>
				</td>
			</tr>
			<tr style="height:32px">
				<th style="text-align: left;">鼻内镜下鼻腔止血术</th>
				<th >5</th>
				<td>
					<input onchange="saveScore(this,5);" itemId="1800-04-1.22" itemName="k鼻内镜下鼻腔止血术"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
				</td>
				<td>
					<input itemId="1800-04-1.22" itemName="d鼻内镜下鼻腔止血术"  name="less1"  class="input" type="text" style="height:20px; text-align: center" readonly="readonly"/>
				</td>
			</tr>
            <tr style="height:32px">
                <th style="text-align: left;">鼻内镜下鼻腔、鼻窦肿瘤切除术</th>
                <th >10</th>
                <td>
                    <input onchange="saveScore(this,10);" itemId="1800-04-1.23" itemName="k鼻内镜下鼻腔、鼻窦肿瘤切除术"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
                </td>
                <td>
                    <input itemId="1800-04-1.23" itemName="d鼻内镜下鼻腔、鼻窦肿瘤切除术"  name="less1"  class="input" type="text" style="height:20px; text-align: center" readonly="readonly"/>
                </td>
            </tr>
            <tr style="height:32px">
                <th style="text-align: left;">悬雍垂腭咽成形术</th>
                <th >20</th>
                <td>
                    <input onchange="saveScore(this,20);" itemId="1800-04-1.24" itemName="k悬雍垂腭咽成形术"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
                </td>
                <td>
                    <input itemId="1800-04-1.24" itemName="d悬雍垂腭咽成形术"  name="less1"  class="input" type="text" style="height:20px; text-align: center" readonly="readonly"/>
                </td>
            </tr>
            <tr style="height:32px">
                <th style="text-align: left;">扁桃体切除术</th>
                <th >50</th>
                <td>
                    <input onchange="saveScore(this,50);" itemId="1800-04-1.25" itemName="k扁桃体切除术"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
                </td>
                <td>
                    <input itemId="1800-04-1.25" itemName="d扁桃体切除术"  name="less1"  class="input" type="text" style="height:20px; text-align: center" readonly="readonly"/>
                </td>
            </tr>
            <tr style="height:32px">
                <th style="text-align: left;">喉良性病变显微外科手术</th>
                <th >20</th>
                <td>
                    <input onchange="saveScore(this,20);" itemId="1800-04-1.26" itemName="k喉良性病变显微外科手术"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
                </td>
                <td>
                    <input itemId="1800-04-1.26" itemName="d喉良性病变显微外科手术"  name="less1"  class="input" type="text" style="height:20px; text-align: center" readonly="readonly"/>
                </td>
            </tr>
            <tr style="height:32px">
                <th style="text-align: left;">鼻内镜下腺样体切除术</th>
                <th >25</th>
                <td>
                    <input onchange="saveScore(this,25);" itemId="1800-04-1.27" itemName="k鼻内镜下腺样体切除术"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
                </td>
                <td>
                    <input itemId="1800-04-1.27" itemName="d鼻内镜下腺样体切除术"  name="less1"  class="input" type="text" style="height:20px; text-align: center" readonly="readonly"/>
                </td>
            </tr>
            <tr style="height:32px">
                <th style="text-align: left;">喉清创成形术</th>
                <th >1</th>
                <td>
                    <input onchange="saveScore(this,1);" itemId="1800-04-1.28" itemName="k喉清创成形术"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
                </td>
                <td>
                    <input itemId="1800-04-1.28" itemName="d喉清创成形术"  name="less1"  class="input" type="text" style="height:20px; text-align: center" readonly="readonly"/>
                </td>
            </tr>
            <tr style="height:32px">
                <th style="text-align: left;">气管切开术</th>
                <th >30</th>
                <td>
                    <input onchange="saveScore(this,30);" itemId="1800-04-1.29" itemName="k气管切开术"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
                </td>
                <td>
                    <input itemId="1800-04-1.29" itemName="d气管切开术"  name="less1"  class="input" type="text" style="height:20px; text-align: center" readonly="readonly"/>
                </td>
            </tr>
            <tr style="height:32px">
                <th style="text-align: left;">气管、食管异物取出术</th>
                <th >20</th>
                <td>
                    <input onchange="saveScore(this,20);" itemId="1800-04-1.30" itemName="k气管、食管异物取出术"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
                </td>
                <td>
                    <input itemId="1800-04-1.30" itemName="d气管、食管异物取出术"  name="less1"  class="input" type="text" style="height:20px; text-align: center" readonly="readonly"/>
                </td>
            </tr>
            <tr style="height:32px">
                <th style="text-align: left;">喉癌各种术式喉切除术</th>
                <th >5</th>
                <td>
                    <input onchange="saveScore(this,5);" itemId="1800-04-1.31" itemName="k喉癌各种术式喉切除术"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
                </td>
                <td>
                    <input itemId="1800-04-1.31" itemName="d喉癌各种术式喉切除术"  name="less1"  class="input" type="text" style="height:20px; text-align: center" readonly="readonly"/>
                </td>
            </tr>
            <tr style="height:32px">
                <th style="text-align: left;">下咽癌切除术</th>
                <th >2</th>
                <td>
                    <input onchange="saveScore(this,2);" itemId="1800-04-1.32" itemName="k下咽癌切除术"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
                </td>
                <td>
                    <input itemId="1800-04-1.32" itemName="d下咽癌切除术"  name="less1"  class="input" type="text" style="height:20px; text-align: center" readonly="readonly"/>
                </td>
            </tr>
            <tr style="height:32px">
                <th style="text-align: left;">颈淋巴结清扫术</th>
                <th >5</th>
                <td>
                    <input onchange="saveScore(this,5);" itemId="1800-04-1.33" itemName="k颈淋巴结清扫术"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
                </td>
                <td>
                    <input itemId="1800-04-1.33" itemName="d颈淋巴结清扫术"  name="less1"  class="input" type="text" style="height:20px; text-align: center" readonly="readonly"/>
                </td>
            </tr>
            <tr style="height:32px">
                <th style="text-align: left;">甲舌囊肿切除术</th>
                <th >5</th>
                <td>
                    <input onchange="saveScore(this,5);" itemId="1800-04-1.34" itemName="k甲舌囊肿切除术"  name="self1"  class="input" type="text"  style="height:20px; text-align: center"  />
                </td>
                <td>
                    <input itemId="1800-04-1.34" itemName="d甲舌囊肿切除术"  name="less1"  class="input" type="text" style="height:20px; text-align: center" readonly="readonly"/>
                </td>
				<td style="display: none"><span id="selfTotalled"></span></td>
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