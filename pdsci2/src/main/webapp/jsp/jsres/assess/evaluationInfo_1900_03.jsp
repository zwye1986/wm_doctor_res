<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="font" value="true"/>
		<jsp:param name="jquery_validation" value="true"/>
	</jsp:include>
	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<style type="text/css">
		.div_table table {
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
            var url = "<s:url value='/jsres/supervisio/saveScheduleTotalled'/>";
            var data = {
                "itemId": 'scoreInfo2',
                "itemName": 'evaluationInfo_1900',
                "score": expertTotal,
                "orgFlow": '${orgFlow}',
                "orgName": '${orgName}',
                "speId": '${speId}',
                "subjectFlow": '${subjectFlow}',
                "roleFlag": '${roleFlag}'
            };
            top.jboxPost(url, data, function (resp) {
                if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                    window.parent.frames["jbox-message-iframe"].$("#scoreInfo2")[0].value = $('#selfTotalled').text();
                    var scoreAll = 0;
                    var num = 0;
                    var scoreInfoList = window.parent.frames["jbox-message-iframe"].$("input[name='scoreInfo']");
                    for (var i = 1; i <= scoreInfoList.length; i++) {
                        if (!isNaN(parseInt(window.parent.frames["jbox-message-iframe"].$("#scoreInfo" + i)[0].value))) {
                            scoreAll = parseInt(window.parent.frames["jbox-message-iframe"].$("#scoreInfo" + i)[0].value) + parseInt(scoreAll);
                            num++;
                        }
                    }
                    scoreAll = scoreAll / num;
                    if (parseInt(scoreAll) >= 90) {
                        scoreAll = 7;
                    }else if (parseInt(scoreAll) >= 80) {
                        scoreAll = 5;
                    } else if (parseInt(scoreAll) >= 70) {
                        scoreAll = 3;
                    } else if (parseInt(scoreAll) >= 60) {
                        scoreAll = 2;
                    } else {
                        scoreAll = 0;
                    }
                    var input;
					if (${ roleFlag==("baseExpert")}) {
                        input = window.parent.frames["jbox-message-iframe"].$("#fubiao2");
                    } else if (${roleFlag==("expertLeader")}) {
                        input = window.parent.frames["jbox-message-iframe"].$("#fubiao2Expert");
                    }
                    input[0].value = scoreAll;
					if (${ roleFlag==("baseExpert")}) {
                        window.parent.frames["jbox-message-iframe"].saveScore(input[0], scoreAll);
                    } else if (${roleFlag==("expertLeader")}) {
                        window.parent.frames["jbox-message-iframe"].saveScore4Expert(input[0], scoreAll);
                    }
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
	<table cellpadding="4" style="width: 1000px;font-size: inherit">
		<tbody>
		<tr height="34" class="firstRow">
			<th colspan="4">
				<h2 style="font-size:150%">住院医师规范化培训技能考核——硬膜外麻醉操作</h2>
			</th>
		</tr>
		<tr style="height:32px;">
			<th>考核项目</th>
			<th>考核内容</th>
			<th>标准分</th>
			<th>得分</th>
		</tr>
		<tr style="height:32px">
			<th rowspan="7">麻醉前准备 <br>(25)</th>
			<th style="text-align: left;">1、对病人周身状态及实验室检查材料了解情况</th>
			<th rowspan="4">10</th>
			<td rowspan="4">
				<input onchange="saveScore(this,10);" itemId="1900-03-1.1" itemName="2麻醉前准备" name="self"
					   class="input"
					   type="text" style="height:28px; text-align: center"/>
			</td>
		</tr>
		<tr style="height:32px">
			<th style="text-align: left;">A、除手术疾病外是否发现有其它疾病3分</th>
		</tr>
		<tr style="height:32px">
			<th style="text-align: left;">B、实验室检查是否有缺项和异常3分</th>
		</tr>
		<tr style="height:32px">
			<th style="text-align: left;">C、体格分级是否正确4分</th>
		</tr>
		<tr style="height:32px">
			<th style="text-align: left;">2、确定麻醉方法</th>
			<th>5</th>
			<td>
				<input onchange="saveScore(this,5);" itemId="1900-03-1.2" itemName="2确定麻醉方法" name="self"
					   class="input"
					   type="text" style="height:28px; text-align: center"/>
			</td>
		</tr>
		<tr style="height:32px">
			<th style="text-align: left;">3、麻醉前用药A、用药是否正确B、剂量是否正确</th>
			<th >5</th>
			<td >
				<input onchange="saveScore(this,5);" itemId="1900-03-1.3" itemName="3、麻醉前用药A、用药是否正确B、剂量是否正确" name="self"
					   class="input"
					   type="text" style="height:28px; text-align: center"/>
			</td>
		</tr>
		<tr style="height:32px">
			<th style="text-align: left;">4、麻醉记录单填写情况</th>
			<th>5</th>
			<td>
				<input onchange="saveScore(this,5);" itemId="1900-03-1.4" itemName="2麻醉记录单填写情况" name="self"
					   class="input"
					   type="text" style="height:28px; text-align: center"/>
			</td>
		</tr>
		<tr style="height:32px">
			<th rowspan="29">麻醉操作<br>(70)</th>
			<th style="text-align: left;">1、麻醉前准备急救药品是否完善（气管导管、喉镜、麻醉机）</th>
			<th>5</th>
			<td >
				<input onchange="saveScore(this,5);" itemId="1900-03-2.1" itemName="1、麻醉前准备急救药品是否完善（气管导管、喉镜、麻醉机）" name="self"
					   class="input"
					   type="text" style="height:28px; text-align: center"/>
			</td>
		</tr>
		<tr style="height:32px">
			<th style="text-align: left;">2、麻醉药品准备情况（急救药品）</th>
			<th>3</th>
			<td >
				<input onchange="saveScore(this,3);" itemId="1900-03-2.2" itemName="2、麻醉药品准备情况（急救药品）" name="self"
					   class="input"
					   type="text" style="height:28px; text-align: center"/>
			</td>
		</tr>
		<tr style="height:32px">
			<th style="text-align: left;">3、复查病人的血压脉搏并记录</th>
			<th>3</th>
			<td >
				<input onchange="saveScore(this,3);" itemId="1900-03-2.3" itemName="3、复查病人的血压脉搏并记录" name="self"
					   class="input"
					   type="text" style="height:28px; text-align: center"/>
			</td>
		</tr>
		<tr style="height:32px">
			<th style="text-align: left;">4、患者穿刺体位是否正确</th>
			<th>3</th>
			<td >
				<input onchange="saveScore(this,3);" itemId="1900-03-2.4" itemName="4、患者穿刺体位是否正确" name="self"
					   class="input"
					   type="text" style="height:28px; text-align: center"/>
			</td>
		</tr>
		<tr style="height:32px">
			<th style="text-align: left;">5、戴无菌手套是否正确</th>
			<th>3</th>
			<td >
				<input onchange="saveScore(this,3);" itemId="1900-03-2.5" itemName="5、戴无菌手套是否正确" name="self"
					   class="input"
					   type="text" style="height:28px; text-align: center"/>
			</td>
		</tr>
		<tr style="height:32px">
			<th style="text-align: left;">6、穿刺用具检查</th>
			<th rowspan="4">9</th>
			<td rowspan="4">
				<input onchange="saveScore(this,9);" itemId="1900-03-2.6" itemName="6、穿刺用具检查" name="self"
					   class="input"
					   type="text" style="height:28px; text-align: center"/>
			</td>
		</tr>
		<tr style="height:32px">
			<th style="text-align: left;">A、检查消毒标记</th>
		</tr>
		<tr style="height:32px">
			<th style="text-align: left;">B、穿刺针是否通畅</th>
		</tr>
		<tr style="height:32px">
			<th style="text-align: left;">C、导管是否通畅</th>
		</tr>
		<tr style="height:32px">
			<th style="text-align: left;">7、药液配制</th>
			<th rowspan="3">6</th>
			<td rowspan="3">
				<input onchange="saveScore(this,6);" itemId="1900-03-2.7" itemName="7、药液配制" name="self"
					   class="input"
					   type="text" style="height:28px; text-align: center"/>
			</td>
		</tr>
		<tr style="height:32px">
			<th style="text-align: left;">A、吸药是否用过滤器和更换针头</th>
		</tr>
		<tr style="height:32px">
			<th style="text-align: left;">B、配制是否正确、是否核对药名</th>
		</tr>
		<tr style="height:32px">
			<th style="text-align: left;">8、穿刺部位消毒</th>
			<th rowspan="3">6</th>
			<td rowspan="3">
				<input onchange="saveScore(this,6);" itemId="1900-03-2.8" itemName="8、穿刺部位消毒" name="self"
					   class="input"
					   type="text" style="height:28px; text-align: center"/>
			</td>
		</tr>
		<tr style="height:32px">
			<th style="text-align: left;">A、消毒顺序是否正确</th>
		</tr>
		<tr style="height:32px">
			<th style="text-align: left;">B、消毒范围是否正确</th>
		</tr>
		<tr style="height:32px">
			<th style="text-align: left;">9、铺无菌单是否正确</th>
			<th >3</th>
			<td >
				<input onchange="saveScore(this,3);" itemId="1900-03-2.9" itemName="9、铺无菌单是否正确" name="self"
					   class="input"
					   type="text" style="height:28px; text-align: center"/>
			</td>
		</tr>
		<tr style="height:32px">
			<th style="text-align: left;">10、确定穿刺点是否正确</th>
			<th >3</th>
			<td >
				<input onchange="saveScore(this,3);" itemId="1900-03-2.10" itemName="10、确定穿刺点是否正确" name="self"
					   class="input"
					   type="text" style="height:28px; text-align: center"/>
			</td>
		</tr>

		<tr style="height:32px">
			<th style="text-align: left;">11、穿刺点局部浸润麻醉是否正确</th>
			<th >3</th>
			<td >
				<input onchange="saveScore(this,3);" itemId="1900-03-2.11" itemName="11、穿刺点局部浸润麻醉是否正确" name="self"
					   class="input"
					   type="text" style="height:28px; text-align: center"/>
			</td>
		</tr>
		<tr style="height:32px">
			<th style="text-align: left;">12、持针头方向是否正确</th>
			<th >3</th>
			<td >
				<input onchange="saveScore(this,3);" itemId="1900-03-2.12" itemName="12、持针头方向是否正确" name="self"
					   class="input"
					   type="text" style="height:28px; text-align: center"/>
			</td>
		</tr>

		<tr style="height:32px">
			<th style="text-align: left;">13、判定硬膜外腔的试验方法是否正确</th>
			<th rowspan="3">2</th>
			<td rowspan="3">
				<input onchange="saveScore(this,2);" itemId="1900-03-2.13" itemName="13、判定硬膜外腔的试验方法是否正确" name="self"
					   class="input"
					   type="text" style="height:28px; text-align: center"/>
			</td>
		</tr>
		<tr style="height:32px">
			<th style="text-align: left;">A、注气试验</th>
		</tr>
		<tr style="height:32px">
			<th style="text-align: left;">B、气泡压缩试验</th>
		</tr>
		<tr style="height:32px">
			<th style="text-align: left;">14、放置导管是否正确</th>
			<th >1</th>
			<td >
				<input onchange="saveScore(this,1);" itemId="1900-03-2.14" itemName="14、放置导管是否正确" name="self"
					   class="input"
					   type="text" style="height:28px; text-align: center"/>
			</td>
		</tr>
		<tr style="height:32px">
			<th style="text-align: left;">15、退针固定导管</th>
			<th >3</th>
			<td >
				<input onchange="saveScore(this,3);" itemId="1900-03-2.15" itemName="15、退针固定导管" name="self"
					   class="input"
					   type="text" style="height:28px; text-align: center"/>
			</td>
		</tr>
		<tr style="height:32px">
			<th style="text-align: left;">16、注药前是否静脉开放与回吸</th>
			<th >3</th>
			<td >
				<input onchange="saveScore(this,3);" itemId="1900-03-2.16" itemName="16、注药前是否静脉开放与回吸" name="self"
					   class="input"
					   type="text" style="height:28px; text-align: center"/>
			</td>
		</tr>
		<tr style="height:32px">
			<th style="text-align: left;">17、是否给与试验量</th>
			<th >3</th>
			<td >
				<input onchange="saveScore(this,3);" itemId="1900-03-2.17" itemName="17、是否给与试验量" name="self"
					   class="input"
					   type="text" style="height:28px; text-align: center"/>
			</td>
		</tr>
		<tr style="height:32px">
			<th style="text-align: left;">18、注入全量前是否测量生命体征</th>
			<th >3</th>
			<td >
				<input onchange="saveScore(this,3);" itemId="1900-03-2.18" itemName="18、注入全量前是否测量生命体征" name="self"
					   class="input"
					   type="text" style="height:28px; text-align: center"/>
			</td>
		</tr>
		<tr style="height:32px">
			<th style="text-align: left;">19、查麻醉平面方法是否正确（用酒精棉球测试皮肤的温度感觉减退平面）</th>
			<th >3</th>
			<td >
				<input onchange="saveScore(this,3);" itemId="1900-03-2.19" itemName="19、查麻醉平面方法是否正确（用酒精棉球测试皮肤的温度感觉减退平面）" name="self"
					   class="input"
					   type="text" style="height:28px; text-align: center"/>
			</td>
		</tr>
		<tr style="height:32px">
			<th style="text-align: left;">20、注入全量后是否密切观察病人循环呼吸并记录</th>
			<th >2</th>
			<td >
				<input onchange="saveScore(this,2);" itemId="1900-03-2.20" itemName="20、注入全量后是否密切观察病人循环呼吸并记录" name="self"
					   class="input"
					   type="text" style="height:28px; text-align: center"/>
			</td>
		</tr>
		<tr style="height:32px">
			<th rowspan="2">人文与沟通 <br>（6）</th>
			<th style="text-align: left;">1、与医护沟通交流良好</th>
			<th>2.5</th>
			<td>
				<input onchange="saveScore(this,2.5);" itemId="1900-03-3.1" itemName="2与医护沟通交流良好" name="self"
					   class="input"
					   type="text" style="height:28px; text-align: center"/>
			</td>
		</tr>
		<tr style="height:32px">
			<th style="text-align: left;">2、与患者沟通交流良好，充分体现人文关怀，保护患者隐私</th>
			<th>2.5</th>
			<td>
				<input onchange="saveScore(this,2.5);" itemId="1900-03-3.2" itemName="2与患者沟通交流良好，充分体现人文关怀，保护患者隐私"
					   name="self"
					   class="input"
					   type="text" style="height:28px; text-align: center"/>
			</td>
		</tr>

		<tr style="height:32px">
			<th colspan="2" style="text-align: right">合计：</th>
			<th>100</th>
			<th><span id="selfTotalled"></span></th>
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