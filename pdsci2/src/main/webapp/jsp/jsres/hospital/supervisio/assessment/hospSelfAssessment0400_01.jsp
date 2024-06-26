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
            height: 32px;
            font-size: 13px;
        }

        .div_table table th {
            border-top: 0;
            border-right: 1px solid #D3D3D3;
            border-bottom: 1px solid #D3D3D3;
            border-left: 0;
            height: 32px;
            font-size: 13px;
        }

        .div_table table tr.lastrow td {
            border-bottom: 0;
        }

        .div_table table tr td.lastCol {
            border-right: 0;
        }
    </style>
    <script src="<s:url value='/js/dsbridge.js'/>"></script>
    <script type="text/javascript">

        //保存单项评分
        function saveScore(expl, num) {
            var score = expl.value;
            var itemId = expl.getAttribute("itemId");
            var reg = /^\d+(\.\d)?$/;
            var reg1 = /^\d+(\.0)$/;
            if (score >= 0 && reg.test(score)) {
                if (reg1.test(score)) {
                    var score1 = parseInt(score);
                    expl.value = score1;
                } else {
                    var score1 = score;
                }
                $(expl).attr("preValue", score1);
                var substandard=" ";
                if (score < num) {
                    substandard = '√';
                }
                var url = "<s:url value='/jsres/supervisio/saveAssessmengtScoreOne'/>";
                var data = {
                    "itemId": itemId,
                    "score": score1,
                    "orgFlow": '${assessment.orgFlow}',
                    "orgName": '${assessment.orgName}',
                    "speId": '${assessment.speId}',
                    "speName": '${assessment.speName}',
                    "subjectFlow": '${assessment.recordFlow}',
                    "subjectName": '${assessment.subjectName}',
                    "evaluationYear": '${assessment.sessionNumber}',
                    "scoreType": 'spe',
                    "substandard": substandard,
                    "fileRoute": '${fileRoute}'
                };
                top.jboxPost(url, data, function (resp) {
                    if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
                        $(expl).next().val(substandard);
                        top.jboxTip(resp);
                    } else {
                        top.jboxTip(resp);
                    }
                }, null, false);
            }else {
                expl.value = expl.getAttribute("preValue");
                var call = dsBridge.call("testSyn", "评分不能大于" + num + "且只能是正整数或含有一位小数");
                top.jboxTip("评分不能大于" + num + "且只能是正整数或含有一位小数");
            }
        }

        //校验数值
        function check(exp) {
            var reg1 = /^\d+(\.0)$/;
            if (reg1.test(exp)) {
                return parseInt(exp);
            } else {
                return exp;
            }
        }

        //提交总分
        function subInfo() {
            var itemIdList = $("input");
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "first" && itemIdList[i].value == "") {
                    $(itemIdList[i]).focus();
                    top.jboxTip("有输入框未输入，请输入！");
                    return;
                }
            }
            var firstSubstandard=0;
            var firstNum=0;
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "substandard") {
                    firstNum++;
                    if ($(itemIdList[i]).val().indexOf('√')!= -1){
                        firstSubstandard=firstSubstandard+1;
                    }
                }
            }
            var firstScore =100 -  parseInt(firstSubstandard / firstNum * 100);
            if (firstScore >= 75) {
                firstScore =  3;
            } else {
                firstScore = 0;
            }
            var firstInput= window.parent.frames["jbox-message-iframe"].$("#fubiao11");
            firstInput[0].value = firstScore;
            window.parent.frames["jbox-message-iframe"].saveScore4Expert(firstInput[0], firstScore,0);
        }
    </script>
</head>
<body>
<div class="div_table" style="overflow: auto;max-height: 600px;">
    <table cellpadding="4" style="width: 1000px">
        <tbody>
        <tr height="34" class="firstRow">
            <th colspan="3">
                <h2 style="font-size:150%">皮肤科疾病种类及年诊治例数</h2>
            </th>
        </tr>
        <tr style="height:32px;">
            <th>病 种</th>
            <th>年诊治例次数（≥)</th>
            <th>实际数</th>
        </tr>
        <tr>
            <td style="width: 75%;">病毒性皮肤病* (寻常疣、跖疣、扁平疣、传染性软疣、单纯疱疹、带状疱疹、水痘、手足口病等)</td>
            <td style="text-align: center;width: 15%;" >1000</td>
            <td style="text-align: center;width: 10%;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1000);" itemId="1" name="first" class="input"
                           type="text" value="${scoreMap['1']}"  style="height:20px;width: 35px; text-align: center"/>
                    <input type="hidden" name="substandard" value=" ${substandardMap['1']}">
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['1']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>细菌性皮肤病(脓疱病、毛囊炎* 、疖和疖病* 、丹毒* 、麻风、皮肤结核*等)</td>
            <td style="text-align: center;" >500*</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,500);" itemId="2" name="first" class="input"
                           type="text" value="${scoreMap['2']}"  style="height:20px;width: 35px; text-align: center"/>
                    <input type="hidden" name="substandard" value=" ${substandardMap['2']}">
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['2']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>真菌病(头癣、体癣* 、股癣* 、手癣* 、足癣* 、甲癣* 、花斑糠疹* 、孢子丝菌病、念珠菌病* 等)</td>
            <td style="text-align: center;" >2000*</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,2000);" itemId="3" name="first" class="input"
                           type="text" value="${scoreMap['3']}"  style="height:20px;width: 35px; text-align: center"/>
                    <input type="hidden" name="substandard" value=" ${substandardMap['3']}">
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['3']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>寄生虫、昆虫与动物引起的皮肤病* (疥疮、丘疹性荨麻疹、虫咬皮炎等)</td>
            <td style="text-align: center;" >500*</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,500);" itemId="4" name="first" class="input"
                           type="text" value="${scoreMap['4']}"  style="height:20px;width: 35px; text-align: center"/>
                    <input type="hidden" name="substandard" value=" ${substandardMap['4']}">
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['4']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>皮炎湿疹类皮肤病(特应性皮炎、接触性皮炎、湿疹等)</td>
            <td style="text-align: center;" >2000</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,2000);" itemId="5" name="first" class="input"
                           type="text" value="${scoreMap['5']}"  style="height:20px;width: 35px; text-align: center"/>
                    <input type="hidden" name="substandard" value=" ${substandardMap['5']}">
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['5']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>瘙痒性皮肤病* (慢性单纯性苔藓、痒疹、瘙痒症、人工皮炎等)</td>
            <td style="text-align: center;" >500*</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,500);" itemId="6" name="first" class="input"
                           type="text" value="${scoreMap['6']}"  style="height:20px;width: 35px; text-align: center"/>
                    <input type="hidden" name="substandard" value=" ${substandardMap['6']}">
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['6']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>过敏性皮肤病* (荨麻疹、药疹等)</td>
            <td style="text-align: center;" >1000</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1000);" itemId="7" name="first" class="input"
                           type="text" value="${scoreMap['7']}"  style="height:20px;width: 35px; text-align: center"/>
                    <input type="hidden" name="substandard" value=" ${substandardMap['7']}">
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['7']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>红斑鳞屑类皮肤病(银屑病* 、副银屑病* 、多形红斑* 、白色糠疹* 、玫瑰糠疹* 、扁平苔藓* 、线状苔藓、红皮病等)</td>
            <td style="text-align: center;" >500*</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,500);" itemId="8" name="first" class="input"
                           type="text" value="${scoreMap['8']}"  style="height:20px;width: 35px; text-align: center"/>
                    <input type="hidden" name="substandard" value=" ${substandardMap['8']}">
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['8']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>物理性皮肤病* (日光性皮炎、痱、冻疮、鸡眼、胼胝、手足皲裂等)</td>
            <td style="text-align: center;" >500*</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="9" name="first" class="input"
                           type="text" value="${scoreMap['9']}"  style="height:20px;width: 35px; text-align: center"/>
                    <input type="hidden" name="substandard" value=" ${substandardMap['9']}">
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['9']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>角化与萎缩性皮肤病(鱼鳞病* 、掌跖角化症* 、毛发红糠疹* 、毛发苔藓* 、小棘苔藓、黑棘皮病* 、斑状萎缩、萎缩纹* 等) </td>
            <td style="text-align: center;" >500*</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,500);" itemId="10" name="first" class="input"
                           type="text" value="${scoreMap['10']}"  style="height:20px;width: 35px; text-align: center"/>
                    <input type="hidden" name="substandard" value=" ${substandardMap['10']}">
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['10']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>皮肤血管疾病* (变应性血管炎、过敏性紫癜、结节性红斑等)</td>
            <td style="text-align: center;" > 100</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,100);" itemId="11" name="first" class="input"
                           type="text" value="${scoreMap['11']}"  style="height:20px;width: 35px; text-align: center"/>
                    <input type="hidden" name="substandard" value=" ${substandardMap['11']}">
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['11']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>代谢性皮肤病(环状肉芽肿* 、与糖尿病有关的皮肤病* 、皮肤淀粉样变* 、黄色瘤* 、卟啉症、痛风* 等)</td>
            <td style="text-align: center;" >50*</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,50);" itemId="12" name="first" class="input"
                           type="text" value="${scoreMap['12']}"  style="height:20px;width: 35px; text-align: center"/>
                    <input type="hidden" name="substandard" value=" ${substandardMap['12']}">
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['12']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>结缔组织病* (红斑狼疮、皮肌炎、局限性硬皮病、系统性硬皮病等)</td>
            <td style="text-align: center;" >50*</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,50);" itemId="13" name="first" class="input"
                           type="text" value="${scoreMap['13']}"  style="height:20px;width: 35px; text-align: center"/>
                    <input type="hidden" name="substandard" value=" ${substandardMap['13']}">
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['13']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>大疱性皮肤病* (天疱疮、大疱性类天疱疮、线状IgA 大疱性皮病等)</td>
            <td style="text-align: center;" >30*</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="14" name="first" class="input"
                           type="text" value="${scoreMap['14']}"  style="height:20px;width: 35px; text-align: center"/>
                    <input type="hidden" name="substandard" value=" ${substandardMap['14']}">
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['14']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>皮肤附属器疾病* (痤疮、玫瑰痤疮、多汗症、汗疱疹、斑秃、雄激素型脱发、多毛症等)</td>
            <td style="text-align: center;" >1000*</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1000);" itemId="15" name="first" class="input"
                           type="text" value="${scoreMap['15']}"  style="height:20px;width: 35px; text-align: center"/>
                    <input type="hidden" name="substandard" value=" ${substandardMap['15']}">
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['15']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>色素障碍性皮肤病* (白癜风、黄褐斑、黑变病、炎症后色素沉着、雀斑等)</td>
            <td style="text-align: center;" >500*</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,500);" itemId="16" name="first" class="input"
                           type="text" value="${scoreMap['16']}"  style="height:20px;width: 35px; text-align: center"/>
                    <input type="hidden" name="substandard" value=" ${substandardMap['16']}">
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['16']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>皮肤良性肿瘤(色素痣* 、血管瘤* 、瘢痕疙瘩* 、脂溢性角化症* 、栗丘疹* 、表皮样囊肿* 、皮脂腺痣* 、表皮痣* 、汗管瘤* 、毛发上皮瘤、皮肤纤维瘤* 、神经纤维瘤* 等)</td>
            <td style="text-align: center;" >500*</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,500);" itemId="17" name="first" class="input"
                           type="text" value="${scoreMap['17']}"  style="height:20px;width: 35px; text-align: center"/>
                    <input type="hidden" name="substandard" value=" ${substandardMap['17']}">
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['17']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>皮肤癌前病变和皮肤恶性肿瘤(日光性角化症* 、鲍恩病* 、基底细胞癌* 、鳞状细胞癌* 、黑色素瘤* 、蕈样肉芽肿* 、淋巴瘤等)</td>
            <td style="text-align: center;" >50*</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,50);" itemId="18" name="first" class="input"
                           type="text" value="${scoreMap['18']}"  style="height:20px;width: 35px; text-align: center"/>
                    <input type="hidden" name="substandard" value=" ${substandardMap['18']}">
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['18']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>性传播疾病(梅毒* 、淋病* 、衣原体性尿道炎* 、尖锐湿疣* 、生殖器疱疹* 、艾滋病等)</td>
            <td style="text-align: center;" >100*</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,100);" itemId="19" name="first" class="input"
                           type="text" value="${scoreMap['19']}"  style="height:20px;width: 35px; text-align: center"/>
                    <input type="hidden" name="substandard" value=" ${substandardMap['19']}">
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['19']}
                </c:if>
            </td>
        </tr>
        </tbody>
    </table>
    <div>
        注:*皮肤科专业基地必须具备病种及数量
    </div>
</div>
<div class="button" style="margin-top: 25px">
    <c:if test="${type eq 'Y'}">
        <input class="btn_green" type="button" value="提&#12288;交" onclick="subInfo();"/>&#12288;
        <input class="btn_green" type="button" id="zancun" value="暂&#12288存" onclick="zancun();"/>&#12288;
        <script type="text/javascript">
            function zancun() {
                top.jboxTip("暂存成功！");
                $('#zancun').hide();
            }
        </script>
    </c:if>
    <input class="btn_green" type="button" value="关&#12288;闭" onclick="top.jboxClose();"/>
</div>
</body>
</html>