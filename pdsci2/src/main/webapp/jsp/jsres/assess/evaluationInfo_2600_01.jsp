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

        function subInfo() {
            var itemIdList = $("input");
            for (var i = 0; i < itemIdList.length; i++) {
                if ((itemIdList[i].getAttribute("name") == "self1" || itemIdList[i].getAttribute("name") == "self2")
                    && itemIdList[i].value == "") {
                    $(itemIdList[i]).focus();
                    top.jboxTip("有输入框未输入数据，请输入数据！");
                    return;
                }
            }
            var self1Score = 0;
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "self1") {
                    self1Score = Number(self1Score) + Number(itemIdList[i].value);
                }
            }
            if (parseInt(self1Score) >= 507) {
                self1Score = 2;
            } else if (parseInt(self1Score) >= 507*0.9) {
                self1Score = 1;
            } else if (parseInt(self1Score) >= 507*0.85) {
                self1Score = 0.5;
            } else {
                self1Score = 0;
            }

            var inputSelf1;
            if (${ roleFlag==("baseExpert")}) {
                inputSelf1 = window.parent.frames["jbox-message-iframe"].$("#fubiao1");
            } else if (${roleFlag==("expertLeader")}) {
                inputSelf1 = window.parent.frames["jbox-message-iframe"].$("#fubiao1Expert");
            }
            inputSelf1[0].value = self1Score;
            if (${ roleFlag==("baseExpert")}) {
                window.parent.frames["jbox-message-iframe"].saveScore(inputSelf1[0], self1Score);
            } else if (${roleFlag==("expertLeader")}) {
                window.parent.frames["jbox-message-iframe"].saveScore4Expert(inputSelf1[0], self1Score);
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
                <h2 style="font-size:150%">神经内科疾病种类/临床技能操作</h2>
            </th>
        </tr>
        <tr style="height:32px;">
            <th colspan="2">疾病种类/临床技能操作</th>
            <th width="55px">标准</th>
            <th>实际数</th>
            <th>低于标准数（划√）</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="18" width="100px">疾病种类</th>
            <th style="text-align: left;">数目异常性染色体病(21三体综合征、13三体综合征、18三体综合征、Turner综合征、Klinefelter综合征、三倍体综合征等)</th>
            <th>≥80</th>
            <td>
                <input onchange="saveScore(this,80);" itemId="2600-01-1.1" itemName="k数目异常性染色体病" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2600-01-1.1" itemName="d数目异常性染色体病" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">
                结构异常染色体病(染色体亚端粒重组异常相关性智力低下、22q11微缺失综合征、22q11微重复综合征、Prader-Willi综合征、Angelman综合征、Beckwith-Wiedermann综合征等)
            </th>
            <th>≥50</th>
            <td>
                <input onchange="saveScore(this,50);" itemId="2600-01-1.2" itemName="结构异常染色体病" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2600-01-1.2" itemName="结构异常染色体病" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">出生缺陷疾病(妊娠期风疹病毒感染、巨细胞病毒宫内感染、先天性梅毒、先天性神经管缺陷、唇/腭裂畸形等)</th>
            <th>≥10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="2600-01-1.3" itemName="k出生缺陷疾病" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2600-01-1.3" itemName="d出生缺陷疾病" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">母胎医学（唐氏筛查异常、B超软指标或结构异常、胎儿染色体异常、拷贝数异常、孕期药物、毒物、射线接触等）</th>
            <th>≥40</th>
            <td>
                <input onchange="saveScore(this,40);" itemId="2600-01-1.4" itemName="k母胎医学" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2600-01-1.4" itemName="d母胎医学" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">血液系统遗传病(α地中海贫血、β地中海贫血、G6PD缺乏症、血友病等)</th>
            <th>≥10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="2600-01-1.5" itemName="k血液系统遗传病" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2600-01-1.5" itemName="d血液系统遗传病" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">
                神经肌肉系统遗传病(腓骨肌萎缩症、Friedreich共济失调、遗传性痉挛截瘫、脊髓小脑性共济失调、肝豆状核变性、Huntington病、家族性肌萎缩侧索硬化症、脊肌萎缩症、假肥大性肌营养不良、面肩肱型肌营养不良、强直性肌营养不良、癫痫及癫痫综合征\家族性帕金森病（综合征）等)
            </th>
            <th>≥120</th>
            <td>
                <input onchange="saveScore(this,120);" itemId="2600-01-1.6" itemName="k神经肌肉系统遗传病" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2600-01-1.6" itemName="d神经肌肉系统遗传病" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">心血管系统遗传病(家族性高胆固醇血症、家族性肥厚性心肌病、家族性扩张型心肌病等)</th>
            <th>≥20</th>
            <td>
                <input onchange="saveScore(this,20);" itemId="2600-01-1.7" itemName="k心血管系统遗传病" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2600-01-1.7" itemName="d心血管系统遗传病" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">遗传代谢病(苯丙酮尿症、酪氨酸血症、异戊酸血症、戊二酸血症I型、半乳糖血症、糖原贮积症、黏多糖贮积症等)</th>
            <th>≥20</th>
            <td>
                <input onchange="saveScore(this,20);" itemId="2600-01-1.8" itemName="k遗传代谢病" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2600-01-1.8" itemName="d遗传代谢病" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">骨骼系统疾病(马方综合征、软骨发育不全、成骨不全病、抗维生素D 佝偻病、颅缝早闭综合征等)</th>
            <th>≥20</th>
            <td>
                <input onchange="saveScore(this,20);" itemId="2600-01-1.9" itemName="k骨骼系统疾病" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2600-01-1.9" itemName="d骨骼系统疾病" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">眼科遗传病(视网膜色素变性、视网膜黄斑变性、白内障等)</th>
            <th>≥5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2600-01-1.10" itemName="k眼科遗传病" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2600-01-1.10" itemName="d眼科遗传病" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">耳鼻喉科遗传病(遗传性非综合征性聋和综合征性聋、Waardenburg综合征、耳硬化症等)</th>
            <th>≥20</th>
            <td>
                <input onchange="saveScore(this,20);" itemId="2600-01-1.11" itemName="k耳鼻喉科遗传病" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2600-01-1.11" itemName="d耳鼻喉科遗传病" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">肾脏遗传病(Alport综合征、多囊肾病等)</th>
            <th>≥10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="2600-01-1.12" itemName="k肾脏遗传病" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2600-01-1.12" itemName="d肾脏遗传病" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">内分泌系统遗传病(糖尿病、雄激素不敏感综合征、先天性肾上腺皮质增生症等)</th>
            <th>≥20</th>
            <td>
                <input onchange="saveScore(this,20);" itemId="2600-01-1.13" itemName="k内分泌系统遗传病" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2600-01-1.13" itemName="d内分泌系统遗传病" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">皮肤系统遗传病(银屑病、鱼鳞病、大疱性表皮松解症、白化病、白癜风、结节性硬化症等)</th>
            <th>≥20</th>
            <td>
                <input onchange="saveScore(this,20);" itemId="2600-01-1.14" itemName="k皮肤系统遗传病" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2600-01-1.14" itemName="d皮肤系统遗传病" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">肿瘤、癌症综合征(视网膜母细胞瘤、家族性腺瘤性息肉病、神经纤维瘤病、急性早幼粒细胞白血病、慢性髓细胞白血病、毛细血管扩张性共济失调症等)</th>
            <th>≥10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="2600-01-1.15" itemName="k肿瘤、癌症综合征" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2600-01-1.15" itemName="d肿瘤、癌症综合征" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">常见智力低下疾病(脆性X 综合征及相关疾病、孤独症、Rett综合征、胎儿酒精综合征等)</th>
            <th>≥20</th>
            <td>
                <input onchange="saveScore(this,20);" itemId="2600-01-1.16" itemName="k常见智力低下疾病" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2600-01-1.16" itemName="d常见智力低下疾病" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">生殖系统遗传病(性腺功能减退、隐睾、不孕症、McCune-Albright综合征、性发育异常等)</th>
            <th>≥30</th>
            <td>
                <input onchange="saveScore(this,30);" itemId="2600-01-1.17" itemName="k生殖系统遗传病" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2600-01-1.17" itemName="d生殖系统遗传病" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">线粒体遗传病(Leigh综合征、线粒体DNA 缺失综合征、Leber遗传性视神经病线粒体脑肌病等)</th>
            <th>≥2</th>
            <td>
                <input onchange="saveScore(this,2);" itemId="2600-01-1.18" itemName="k线粒体遗传病" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2600-01-1.18" itemName="d线粒体遗传病" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
		<tr style="height:32px">
			<th rowspan="6" width="100px">检查项目</th>
			<th style="text-align: left;">外周血核型分析</th>
			<th>≥100</th>
			<td>
				<input onchange="saveScore(this,100);" itemId="2600-01-2.1" itemName="k外周血核型分析" name="self2" class="input"
					   type="text" style="height:20px; text-align: center"/>
			</td>
			<td>
				<input itemId="2600-01-2.1" itemName="d外周血核型分析" name="less2" class="input" type="text"
					   style="height:20px; text-align: center"/>
			</td>
		</tr>
		<tr style="height:32px">
			<th style="text-align: left;">羊水及脐带血核型分析</th>
			<th>≥30</th>
			<td>
				<input onchange="saveScore(this,30);" itemId="2600-01-2.2" itemName="k羊水及脐带血核型分析" name="self2" class="input"
					   type="text" style="height:20px; text-align: center"/>
			</td>
			<td>
				<input itemId="2600-01-2.2" itemName="d羊水及脐带血核型分析" name="less2" class="input" type="text"
					   style="height:20px; text-align: center"/>
			</td>
		</tr>
		<tr style="height:32px">
			<th style="text-align: left;">染色体微芯片</th>
			<th>≥30</th>
			<td>
				<input onchange="saveScore(this,30);" itemId="2600-01-2.3" itemName="k染色体微芯片" name="self2" class="input"
					   type="text" style="height:20px; text-align: center"/>
			</td>
			<td>
				<input itemId="2600-01-2.3" itemName="d染色体微芯片" name="less2" class="input" type="text"
					   style="height:20px; text-align: center"/>
			</td>
		</tr>
		<tr style="height:32px">
			<th style="text-align: left;">FISH</th>
			<th>≥10</th>
			<td>
				<input onchange="saveScore(this,10);" itemId="2600-01-2.4" itemName="kFISH" name="self2" class="input"
					   type="text" style="height:20px; text-align: center"/>
			</td>
			<td>
				<input itemId="2600-01-2.4" itemName="dFISH" name="less2" class="input" type="text"
					   style="height:20px; text-align: center"/>
			</td>
		</tr>
		<tr style="height:32px">
			<th style="text-align: left;">基因突变检测</th>
			<th>≥30</th>
			<td>
				<input onchange="saveScore(this,30);" itemId="2600-01-2.5" itemName="k基因突变检测" name="self2" class="input"
					   type="text" style="height:20px; text-align: center"/>
			</td>
			<td>
				<input itemId="2600-01-2.5" itemName="d基因突变检测" name="less2" class="input" type="text"
					   style="height:20px; text-align: center"/>
			</td>
		</tr>
		<tr style="height:32px">
			<th style="text-align: left;">遗传代谢病筛查及高通量基因测序孕妇外周血检测</th>
			<th>各≥30</th>
			<td>
				<input onchange="saveScore(this,30);" itemId="2600-01-2.6" itemName="k遗传代谢病筛查及高通量基因测序孕妇外周血检测" name="self2" class="input"
					   type="text" style="height:20px; text-align: center"/>
			</td>
			<td>
				<input itemId="2600-01-2.6" itemName="d遗传代谢病筛查及高通量基因测序孕妇外周血检测" name="less2" class="input" type="text"
					   style="height:20px; text-align: center"/>
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