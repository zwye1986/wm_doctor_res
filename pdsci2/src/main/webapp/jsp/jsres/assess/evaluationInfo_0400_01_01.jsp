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
                if ((itemIdList[i].getAttribute("name") == "self")
                    && itemIdList[i].value == "") {
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
            self1Score = self1Score / 15080 * 100;
            if (self1Score >= 100) {
                self1Score = 3;
            } else if (self1Score >= 90) {
                self1Score = 2;
            } else if (self1Score >= 85) {
                self1Score = 1;
            } else {
                self1Score = 0;
            }

            var inputSelf1;
            if (${ roleFlag==("baseExpert")}) {
                inputSelf1 = window.parent.frames["jbox-message-iframe"].$("#fubiao11");
            } else if (${roleFlag==("expertLeader")}) {
                inputSelf1 = window.parent.frames["jbox-message-iframe"].$("#fubiao11Expert");
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
            var selfTotalled = $("#selfTotalled").text();
            var reg = /^\d+(\.\d)?$/;
            var reg1 = /^\d+(\.0)$/;
            if (reg.test(score)) {
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
                        top.jboxTip(resp);
                    } else {
                        top.jboxTip(resp);
                    }
                }, null, false);
            } else {
                expl.value = expl.getAttribute("preValue");
                top.jboxTip("评分不能大于" + num + "且只能是正整数或含有一位小数");
            }
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
<div class="div_table" style="overflow: auto;max-height: 570px;">
    <table cellpadding="4" style="width: 1000px">
        <tbody>
        <tr height="34" class="firstRow">
            <th colspan="3">
                <h2 style="font-size:150%">皮肤科疾病种类及年诊治例数</h2>
            </th>
        </tr>
        <tr style="height:32px;">
            <th style="width: 60%">病种</th>
            <th style="width: 20%">年诊治例次数（≥)</th>
            <th style="width: 20%">实际数</th>
        </tr>
        <tr style="height:32px">
            <td>
                病毒性皮肤病（寻常疣，跖疣，扁平疣，传染性软疣，单纯疱疹，带状疱疹等）
            </td>
            <td style="text-align: center">2000</td>
            <td>
                <input onchange="saveScore(this,500);" itemId="0400-01-1.1" itemName="病毒性皮肤病" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                细菌性皮肤病（脓疱病，毛囊炎，疖和疖病，丹毒，麻风，皮肤结核等）
            </td>
            <td style="text-align: center">1500</td>
            <td>
                <input onchange="saveScore(this,500);" itemId="0400-01-1.2" itemName="细菌性皮肤病" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                真菌病（头癣，体癣，股癣，手癣，足癣，甲癣，花斑糠疹，孢子丝菌病念珠菌病等）
            </td>
            <td style="text-align: center">2000</td>
            <td>
                <input onchange="saveScore(this,500);" itemId="0400-01-1.3" itemName="真菌病" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                寄生虫、昆虫与动物引起的皮肤病（疥疮，丘疹性荨麻疹，虫咬皮炎等）
            </td>
            <td style="text-align: center">500</td>
            <td>
                <input onchange="saveScore(this,500);" itemId="0400-01-1.4" itemName="寄生虫、昆虫与动物引起的皮肤病" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                与变态反应有关的皮肤病（湿疹与皮炎，特应性皮炎，脂溢性皮炎，接触性皮炎，荨麻疹，药疹等）
            </td>
            <td style="text-align: center">4000</td>
            <td>
                <input onchange="saveScore(this,500);" itemId="0400-01-1.5" itemName="与变态反应有关的皮肤病" name="self"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                瘙痒性皮肤病（慢性单纯性苔藓，痒疹，瘙痒症，人工皮炎等）
            </td>
            <td style="text-align: center">1000</td>
            <td>
                <input onchange="saveScore(this,500);" itemId="0400-01-1.6" itemName="瘙痒性皮肤病" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                红斑鳞屑类皮肤病（银屑病，副银屑病，多形红斑，白色糠疹，玫瑰糠疹，扁平苔藓，线状苔藓等）
            </td>
            <td style="text-align: center">500</td>
            <td>
                <input onchange="saveScore(this,500);" itemId="0400-01-1.7" itemName="红斑鳞屑类皮肤病" name="self"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                物理性皮肤病（日光性皮炎，痱，冻疮，鸡眼，胼胝，手足皲裂等）
            </td>
            <td style="text-align: center">500</td>
            <td>
                <input onchange="saveScore(this,500);" itemId="0400-01-1.8" itemName="物理性皮肤病" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                角化与萎缩性皮肤病（鱼鳞病，掌跖角化症，毛发红糠疹，毛发苔藓，小棘苔藓，黑棘皮病，斑状萎缩，萎缩纹等）
            </td>
            <td style="text-align: center">500</td>
            <td>
                <input onchange="saveScore(this,500);" itemId="0400-01-1.9" itemName="角化与萎缩性皮肤病" name="self"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                皮肤血管疾病（变应性血管炎，过敏性紫癜，结节性红斑等）
            </td>
            <td style="text-align: center">200</td>
            <td>
                <input onchange="saveScore(this,500);" itemId="0400-01-1.10" itemName="皮肤血管疾病" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                代谢性皮肤病（环状肉芽肿，与糖尿病有关的皮肤病，皮肤淀粉样变，黄色瘤，卟啉症、痛风等）
            </td>
            <td style="text-align: center">50--100</td>
            <td>
                <input onchange="saveScore(this,500);" itemId="0400-01-1.11" itemName="代谢性皮肤病" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                结缔组织病（红斑狼疮，皮肌炎，局限性硬皮病，系统性硬皮病等）
            </td>
            <td style="text-align: center">50--100</td>
            <td>
                <input onchange="saveScore(this,500);" itemId="0400-01-1.12" itemName="结缔组织病" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                大疱性皮肤病（天疱疮，大疱性类天疱疮，线状IgA大疱性皮病等）
            </td>
            <td style="text-align: center">30--50</td>
            <td>
                <input onchange="saveScore(this,500);" itemId="0400-01-1.13" itemName="大疱性皮肤病" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                皮肤附属器疾病（痤疮，玫瑰痤疮，多汗症，汗疱疹，斑秃，雄激素型脱发，多毛症等）
            </td>
            <td style="text-align: center">1000</td>
            <td>
                <input onchange="saveScore(this,500);" itemId="0400-01-1.14" itemName="皮肤附属器疾病" name="self"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                色素障碍性皮肤病（白癜风，黄褐斑，黑变病，炎症后色素沉着，雀斑等）
            </td>
            <td style="text-align: center">500</td>
            <td>
                <input onchange="saveScore(this,500);" itemId="0400-01-1.15" itemName="色素障碍性皮肤病" name="self"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                皮肤良性肿瘤（色素痣，血管瘤，瘢痕疙瘩，脂溢性角化症，栗丘疹，皮样囊肿，皮脂腺痣，表皮痣，汗管瘤，毛发上皮瘤，皮肤纤维瘤，神经纤维瘤等）
            </td>
            <td style="text-align: center">500</td>
            <td>
                <input onchange="saveScore(this,500);" itemId="0400-01-1.16" itemName="皮肤良性肿瘤" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                皮肤癌前病变和皮肤恶性肿瘤（日光性角化症，鲍恩病，基底细胞癌，鳞状细胞癌，黑色素瘤，蕈样肉芽肿，淋巴瘤等）
            </td>
            <td style="text-align: center">50</td>
            <td>
                <input onchange="saveScore(this,500);" itemId="0400-01-1.17" itemName="皮肤癌前病变和皮肤恶性肿瘤" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                性传播疾病（梅毒，淋病，衣原体性尿道炎，尖锐湿疣，生殖器疱疹，艾滋病等）
            </td>
            <td style="text-align: center">200-300</td>
            <td>
                <input onchange="saveScore(this,200);" itemId="0400-01-1.18" itemName="性传播疾病" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
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