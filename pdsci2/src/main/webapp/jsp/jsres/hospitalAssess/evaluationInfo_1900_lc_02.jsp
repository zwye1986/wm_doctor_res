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
			if (${edit eq 'N'}){
				for (var i = 0; i < itemIdList.length; i++) {
					itemIdList[i].readOnly = "true";
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
	</script>
</head>
<body>
<div class="div_table" style="overflow: auto;max-height: 570px;">
	<table cellpadding="4" style="width: 1000px;font-size: inherit">
		<tbody>
		<tr height="34" class="firstRow">
			<th colspan="4">
				<h2 style="font-size:150%">临床操作技能床旁教学（中心静脉穿刺查）考核评分表</h2>
			</th>
		</tr>
		<tr style="height:32px;">
			<th style="text-align: left;" colspan="2">
				<div style="margin-top: 8px"><span>培训对象姓名：</span></div>
				<textarea type="text"style="border: 1px solid #bababa;width: 50%;height: 25px;margin-top: -22px;margin-left: 90px"
						  itemId="1900-04-0.3" name="reason" onchange="saveSpeReason(this);"></textarea>
			</th>
			<th style="text-align: left;" colspan="2">所在科室：${deptName}</th>
		</tr>
		<tr style="height:32px;">
			<th style="text-align: left;" colspan="2">培训基地（医院）：${orgName}</th>
			<th style="text-align: left;" colspan="2">省市：${cityName}</th>
		</tr>
		<tr style="height:32px;">
			<th>考核项目</th>
			<th>考核内容</th>
			<th>标准分</th>
			<th>得分</th>
		</tr>
		<tr style="height:32px">
			<th rowspan="3">麻醉前准备 <br>（20分）</th>
			<th style="text-align: left;">1、掌握中心静脉穿刺的适应症与禁忌症</th>
			<th >5</th>
			<td >
				<input onchange="saveScore(this,5);" itemId="1900-04-1.1" itemName="3对病人周身状态及实验室检查材料了解情况" name="self"
					   class="input"
					   type="text" style="height:28px; text-align: center"/>
			</td>
		</tr>
		<tr style="height:32px">
			<th style="text-align: left;">2、掌握各种中心静脉穿刺点定位</th>
			<th >5</th>
			<td >
				<input onchange="saveScore(this,5);" itemId="1900-04-1.2" itemName="2、掌握各种中心静脉穿刺点定位" name="self"
					   class="input"
					   type="text" style="height:28px; text-align: center"/>
			</td>
		</tr>
		<tr style="height:32px">
			<th style="text-align: left;">3、掌握穿刺的方法</th>
			<th >10</th>
			<td >
				<input onchange="saveScore(this,10);" itemId="1900-04-1.3" itemName="3、掌握穿刺的方法" name="self"
					   class="input"
					   type="text" style="height:28px; text-align: center"/>
			</td>
		</tr>
		<tr style="height:32px">
			<th rowspan="11">中心静脉穿刺 <br>（75分）</th>
			<th style="text-align: left;">1、穿刺点定位，A：定位准确得5分，B：利用超声定位和评估得5分</th>
			<th >10</th>
			<td >
				<input onchange="saveScore(this,10);" itemId="1900-04-2.1" itemName="1、穿刺点定位，A：定位准确得5分，B：利用超声定位和评估得5分" name="self"
					   class="input"
					   type="text" style="height:28px; text-align: center"/>
			</td>
		</tr>
		<tr style="height:32px">
			<th style="text-align: left;">2、严格无菌操作</th>
			<th >5</th>
			<td >
				<input onchange="saveScore(this,5);" itemId="1900-04-2.2" itemName="2、严格无菌操作" name="self"
					   class="input"
					   type="text" style="height:28px; text-align: center"/>
			</td>
		</tr>
		<tr style="height:32px">
			<th style="text-align: left;">3、铺无菌单正确</th>
			<th >5</th>
			<td >
				<input onchange="saveScore(this,5);" itemId="1900-04-2.3" itemName="3、铺无菌单正确" name="self"
					   class="input"
					   type="text" style="height:28px; text-align: center"/>
			</td>
		</tr>
		<tr style="height:32px">
			<th style="text-align: left;">4、穿刺点局部侵润麻醉正确</th>
			<th >5</th>
			<td >
				<input onchange="saveScore(this,5);" itemId="1900-04-2.4" itemName="4、穿刺点局部侵润麻醉正确" name="self"
					   class="input"
					   type="text" style="height:28px; text-align: center"/>
			</td>
		</tr>
		<tr style="height:32px">
			<th style="text-align: left;">5、进针角度是否正确</th>
			<th >5</th>
			<td >
				<input onchange="saveScore(this,5);" itemId="1900-04-2.5" itemName="5、进针角度是否正确" name="self"
					   class="input"
					   type="text" style="height:28px; text-align: center"/>
			</td>
		</tr>
		<tr style="height:32px">
			<th style="text-align: left;">6、穿刺</th>
			<th rowspan="4">35</th>
			<td rowspan="4">
				<input onchange="saveScore(this,35);" itemId="1900-04-2.6" itemName="6、穿刺" name="self"
					   class="input"
					   type="text" style="height:28px; text-align: center"/>
			</td>
		</tr>
		<tr style="height:32px">
			<th style="text-align: left;">A、穿刺过程顺利，穿刺次数≤3次</th>
		</tr>
		<tr style="height:32px">
			<th style="text-align: left;">B、穿刺次数>3次或出现血肿扣15分，且每增加一次穿刺扣5分</th>
		</tr>
		<tr style="height:32px">
			<th style="text-align: left;">C、穿刺失败该项目为0分</th>
		</tr>
		<tr style="height:32px">
			<th style="text-align: left;">7、接换能器、接肝素帽</th>
			<th >5</th>
			<td >
				<input onchange="saveScore(this,5);" itemId="1900-04-2.7" itemName="7、接换能器、接肝素帽" name="self"
					   class="input"
					   type="text" style="height:28px; text-align: center"/>
			</td>
		</tr>
		<tr style="height:32px">
			<th style="text-align: left;">8、回抽血不通畅扣10分</th>
			<th >5</th>
			<td >
				<input onchange="saveScore(this,5);" itemId="1900-04-2.8" itemName="8、回抽血不通畅扣10分" name="self"
					   class="input"
					   type="text" style="height:28px; text-align: center"/>
			</td>
		</tr>
		<tr style="height:32px">
			<th rowspan="2">人文与沟通 <br>（5）</th>
			<th style="text-align: left;">1、与医护沟通交流良好</th>
			<th >2.5</th>
			<td >
				<input onchange="saveScore(this,2.5);" itemId="1900-04-3.1" itemName="3与医护沟通交流良好" name="self"
					   class="input"
					   type="text" style="height:28px; text-align: center"/>
			</td>
		</tr>
		<tr style="height:32px">
			<th style="text-align: left;">2、与患者沟通交流良好，充分体现人文关怀，保护患者隐私</th>
			<th >2.5</th>
			<td >
				<input onchange="saveScore(this,2.5);" itemId="1900-04-3.2" itemName="3与患者沟通交流良好，充分体现人文关怀，保护患者隐私" name="self"
					   class="input"
					   type="text" style="height:28px; text-align: center"/>
			</td>
		</tr>
		<tr style="height:32px">
			<th colspan="2" style="text-align: right">总分：</th>
			<th>100</th>
			<th><span id="selfTotalled"></span></th>
		</tr>
		<tr style="height: 50px">
			<th style="text-align:left">
				&#12288;&#12288;评价人：${specialistName}
			</th>
			<th >
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
	<input class="btn_green" type="button" value="打&#12288;印" onclick="printHospitalScore('evaluationInfo_1900_lc_02');"/>&#12288;
	<c:if test="${edit ne 'N'}">
		<input class="btn_green" type="button" value="提&#12288;交" onclick="subInfo();"/>&#12288;
	</c:if>
</div>
</body>
</html>