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
            var dScore = 100 - score;
            $("#selfTotalled").text(check(dScore.toFixed(1)));
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
			if (${isRead eq GlobalConstant.RECORD_STATUS_Y} || ${GlobalConstant.USER_LIST_GLOBAL eq roleFlag} || ${editFlag eq GlobalConstant.FLAG_N}) {
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
                if (itemIdList[i].getAttribute("itemName") == "${item.itemName}" && itemIdList[i].getAttribute("name") == "self") {
                    itemIdList[i].value = "${item.score}";
                    $(itemIdList[i]).attr("preValue", "${item.score}");
                }
            }
            //获取扣分原因
            for (var i = 0; i < itemIdList2.length; i++) {
                if (itemIdList2[i].getAttribute("itemId") == "${item.itemId}" && itemIdList2[i].getAttribute("name") == "reason") {
                    itemIdList2[i].value = "${item.itemDetailed}";
                }
            }
            </c:forEach>
            loadDate();
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
			var expertTotal = Number($('#selfTotalled').text());
            var input;
			if (${ roleFlag==("baseExpert")}) {
                input = window.parent.frames["jbox-message-iframe"].$("#fubiao4");
            } else if (${roleFlag==("expertLeader")}) {
                input = window.parent.frames["jbox-message-iframe"].$("#fubiao4Expert");
            }
            if (parseInt(expertTotal) >= 90) {
                expertTotal = 8;
            } else if (parseInt(expertTotal) >= 80) {
                expertTotal = 5;
            } else if (parseInt(expertTotal) >= 70) {
                expertTotal = 3;
            } else if (parseInt(expertTotal) >= 60) {
                expertTotal = 1;
            } else {
                expertTotal = 0;
            }
            input[0].value = expertTotal;
			if (${ roleFlag==("baseExpert")}) {
                window.parent.frames["jbox-message-iframe"].saveScore(input[0], expertTotal);
            } else if (${roleFlag==("expertLeader")}) {
                window.parent.frames["jbox-message-iframe"].saveScore4Expert(input[0], expertTotal);
            }
            top.jboxClose();
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
				<h2 style="font-size:150%">住院医师临床能力评分表（下肢骨折固定）评分表</h2>
			</th>
		</tr>
		<tr height="28" >
			<th style="text-align:left;padding-left: 4px;" >培训对象姓名：${userName}</th>
			<th></th>
			<th style="text-align:left;padding-left: 4px;" >所在科室：${speName}</th>
			<th></th>
			<th style="text-align:left;padding-left: 4px;" >培训基地（医院）：${orgName}</th>
		</tr>
		<tr style="height:32px;">
			<th style="width: 20%">项目</th>
			<th style="width: 20%">考核内容</th>
			<th style="width: 20%">评分标准</th>
			<th style="width: 20%">标准分</th>
			<th style="width: 20%">扣分</th>
		</tr>
		<tr style="height:32px">
			<th rowspan="2">医患沟通（10分）</th>
			<td rowspan="2">
				与患者沟通能力
			</td>
			<td style="text-align: center;">
				向患者交代治疗项目5分
			</td>
			<td style="text-align: center;" rowspan="2">10</td>
			<td>
				<input onchange="saveScore(this,5);" itemId="1400-04-1.1" itemName="1与患者沟通能力"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
		</tr>
		<tr style="height:32px">
			<td>
				告知风险、注意事项，询问病史，除外禁忌症5分
			</td>
			<td>
				<input onchange="saveScore(this,5);" itemId="1400-04-1.2" itemName="2与患者沟通能力"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
		</tr>
		<tr style="height:32px">
			<th rowspan="3">操作前准备（15分）</th>
			<td rowspan="3">
				耗材、体位、石膏的准备工作
			</td>
			<td style="text-align: center;">
				石膏绷带，普通绷带，棉衬，袜套
			</td>
			<td style="text-align: center;" >5</td>
			<td>
				<input onchange="saveScore(this,5);" itemId="1400-04-2.1" itemName="1操作前准备"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
		</tr>
		<tr style="height:32px">
			<td style="text-align: center;">
				患者体位，充分显露，局部清洁
			</td>
			<td style="text-align: center;" >5</td>
			<td>
				<input onchange="saveScore(this,5);" itemId="1400-04-2.2" itemName="2操作前准备"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
		</tr>
		<tr style="height:32px">
			<td style="text-align: center;">
				确定石膏位置，测量长度，准备石膏
			</td>
			<td style="text-align: center;" >5</td>
			<td>
				<input onchange="saveScore(this,5);" itemId="1400-04-2.3" itemName="3操作前准备"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
		</tr>
		<tr style="height:32px">
			<th rowspan="6">操作（65分）</th>
			<td rowspan="6">
				固定的具体方法及相关要求
			</td>
			<td style="text-align: center;">
				剪裁合适袜套，骨凸处加衬垫
			</td>
			<td style="text-align: center;" >10</td>
			<td>
				<input onchange="saveScore(this,10);" itemId="1400-04-3.1" itemName="1操作"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
		</tr>
		<tr style="height:32px">
			<td style="text-align: center;">
				石膏浸水，两端堵住，对掌位挤压多余水分
			</td>
			<td style="text-align: center;" >10</td>
			<td>
				<input onchange="saveScore(this,10);" itemId="1400-04-3.2" itemName="2操作"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
		</tr>
		<tr style="height:32px">
			<td style="text-align: center;">
				自远端向近端缠绕石膏绷带，不要牵拉
			</td>
			<td style="text-align: center;" >10</td>
			<td>
				<input onchange="saveScore(this,10);" itemId="1400-04-3.3" itemName="3操作"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
		</tr>
		<tr style="height:32px">
			<td style="text-align: center;">
				相邻重叠1/2-1/3，不能出现皱褶
			</td>
			<td style="text-align: center;" >10</td>
			<td>
				<input onchange="saveScore(this,10);" itemId="1400-04-3.4" itemName="4操作"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
		</tr>
		<tr style="height:32px">
			<td style="text-align: center;">
				缠绕时注意抹平，相邻面贴附牢固，12-14层
			</td>
			<td style="text-align: center;" >10</td>
			<td>
				<input onchange="saveScore(this,10);" itemId="1400-04-3.5" itemName="5操作"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
		</tr>
		<tr style="height:32px">
			<td style="text-align: center;">
				塑形，石膏表面平整，修整两端，露出肢体远端，抹平时用力均匀，避免凹陷
			</td>
			<td style="text-align: center;" >15</td>
			<td>
				<input onchange="saveScore(this,15);" itemId="1400-04-3.6" itemName="6操作"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
		</tr>
		<tr style="height:32px">
			<th rowspan="2">术后处理（10分）</th>
			<td rowspan="2">
				固定后的注意事项
			</td>
			<td style="text-align: center;">
				标记石膏固定时间5分
			</td>
			<td style="text-align: center;" rowspan="2">10</td>
			<td>
				<input onchange="saveScore(this,5);" itemId="1400-04-4.1" itemName="1术后处理"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
		</tr>
		<tr style="height:32px">
			<td style="text-align: center;">
				向患者交代注意事项，下一步治疗方案5分
			</td>
			<td>
				<input onchange="saveScore(this,5);" itemId="1400-04-4.2" itemName="2术后处理"  name="self"  class="input" type="text"  style="height:20px; text-align: center"  />
			</td>
		</tr>

		<tr style="height:32px;">
			<td colspan="3" style="text-align: right;">合计：</td>
			<td style="text-align: center;">100</td>
			<td><span id="selfTotalled"></span></td>
		</tr>
		<tr style="height: 50px">
			<th style="text-align:left">
				&#12288;&#12288;考核专家：
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