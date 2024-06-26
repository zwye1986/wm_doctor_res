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
        #tableContext table {
            border-collapse: collapse;
            border: 1px solid #D3D3D3;
        }

        #tableContext table td {
            border-top: 0;
            border-right: 1px solid #D3D3D3;
            border-bottom: 1px solid #D3D3D3;
            border-left: 0;
            padding-left: 4px;
            padding-right: 2px;
            text-align: left;
            height: 32px;
            font-size: 13px;
        }

        #tableContext table th {
            border-top: 0;
            border-right: 1px solid #D3D3D3;
            border-bottom: 1px solid #D3D3D3;
            border-left: 0;
            height: 32px;
            font-size: 13px;
        }
    </style>
    <script src="<s:url value='/js/dsbridge.js'/>"></script>
    <script type="text/javascript">
        $(function () {
            var call = dsBridge.call("testSyn", 'pad');
            if ('Y' == call) {
                $('#close').hide();
                $('#print').hide();
            }
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
            if ('${widthSize}' == 'Y') {
                $('#tableContext').css("width", "1000px");
            }

            loadDate("${scoreMap}");
            var type='${type}';
            //获取所有name为file的td
            var fileList = $("td[name='file']");
            for (var i = 0; i < fileList.length; i++) {
                var itemId = fileList[i].getAttribute("itemId");
                var id = itemId;
                id = id.replace(".", "-");
                id = id.replace(".", "-");
                if (type !='N'){
                    <c:forEach items="${supervisioFileList}" var="file" varStatus="status" >
                    if (itemId == "${file.itemId}") {
                        var delId = "delFile_${file.recordFlow}";
                        fileList[i].innerHTML += "<span style=\"margin: 10px;\" class='titleName' title='${file.fileName}' id='" + delId + "'><a style=\"color: #459ae9;\" href='javascript:void(0);' onclick=\"downloadFile('${file.recordFlow}');\">${pdfn:cutString(file.fileName,4,true,3) }</a><button name='removeFileBtn' title='移除' style='background: white'  onclick=\"delFile('${file.recordFlow}','" + delId + "')\"><img alt= \"上传附件\" src=\"<s:url value='/css/skin/LightBlue/images/del3.png'/>\"></button></span><br>";
                    }
                    </c:forEach>
                }else {
                    <c:forEach items="${supervisioFileList}" var="file" varStatus="status" >
                    if (itemId == "${file.itemId}") {
                        var delId = "delFile_${file.recordFlow}";
                        fileList[i].innerHTML += "<span style=\"margin: 10px;\" class='titleName' title='${file.fileName}' id='" + delId + "'><a style=\"color: #459ae9;\" href='javascript:void(0);' onclick=\"downloadFile('${file.recordFlow}');\">${pdfn:cutString(file.fileName,4,true,3) }</a></span><br>";
                    }
                    </c:forEach>
                }

                if (type !='N'){
                    var fileId = "divFile_" + id;
                    var html = "<div><a style=\"color: #459ae9;\" id='" + fileId + "' name='upLoadBtn' href=\"javascript:uploadFile('" + itemId + "');\"><img style=\"margin: 10px;\" alt= \"上传附件\" src=\"<s:url value='/css/skin/LightBlue/images/add6.png'/>\"></a></div>";
                    fileList[i].innerHTML += "<span id='" + fileId + "'></span>"
                    fileList[i].innerHTML += html;
                }
            }
        });

        function uploadFile(itemId) {
            var url = "<s:url value='/jsres/supervisio/uploadFile'/>?itemId=" + itemId + "&subjectFlow=${assessment.recordFlow}" + "&speId=${assessment.speId}"+ "&subjectYear=${assessment.sessionNumber}";
            top.jboxOpen(url, "上传附件", 500, 185);
        }

        function delFile(recordFlow, exp) {
            top.jboxConfirm("确认移除该附件吗？", function () {
                var url = "<s:url value='/jsres/supervisio/removeFile?recordFlow='/>" + recordFlow;
                top.jboxGet(url, null, function () {
                    $("#" + exp).attr({style: "display:none"});
                    $("#" + exp).attr({style: "display:inline"});
                    $("#" + exp).next().remove();
                    $("#" + exp).remove();
                });
            });
        }

        function downloadFile(recordFlow) {
            top.jboxTip("下载中…………");
            var url = "<s:url value='/jsres/supervisio/downloadFile?recordFlow='/>" + recordFlow;
            window.location.href = url;
        }

        function tableFixed(div) {
            $("#dateFixed").css("top", $(div).scrollTop() + "px");
        }

        function saveScore4Expert(expl,num,coreIndicators) {
            var score = expl.value;
            var itemId = expl.getAttribute("itemId");
            var itemName = expl.getAttribute("itemName");
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

                //coreIndicators   0达标的核心指标   1未达标的核心指标   2 不是核心指标
                if (null!=coreIndicators && coreIndicators!=undefined && coreIndicators=='0'){
                    //核心指标
                    if (score1 < num*0.7){
                        coreIndicators=1;
                    }
                    if (num==3 && score1 < 2){
                        coreIndicators=1;
                    }else {
                        coreIndicators=0;
                    }
                }else {
                    coreIndicators=2;
                }

                var url = "<s:url value='/jsres/supervisio/saveHospitalSelfAssessmentScore'/>";
                var data = {
                    "itemId": itemId,
                    "itemName": itemName,
                    "ownerScore": score1,
                    "coreIndicators": coreIndicators,
                    "orgFlow": '${assessment.orgFlow}',
                    "orgName": '${assessment.orgName}',
                    "speId": '${assessment.speId}',
                    "speName": '${assessment.speName}',
                    "subjectFlow": '${assessment.recordFlow}',
                    "subjectName": '${assessment.subjectName}'
                };
                top.jboxPost(url, data, function (resp) {
                    if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                        top.jboxTip(resp);
                    } else {
                        top.jboxTip(resp);
                    }
                }, null, false);
                loadDate();
            } else {
                expl.value = expl.getAttribute("preValue");
                var call = dsBridge.call("testSyn", "评分不能大于" + num + "且只能是正整数或含有一位小数");
                top.jboxTip("评分不能大于" + num + "且只能是正整数或含有一位小数");
            }
        }

        //计算合计
        function loadDate(speAllExpScore) {
            var localTotalled = 0;
            var localScore = 0;
            var localAll = 0;
            if (${type eq 'Y'}) {
                var itemIdList = $("input");
                for (var i = 0; i < itemIdList.length; i++) {
                    if (itemIdList[i].getAttribute("name") == "local") {
                        localTotalled = Number(localTotalled) + Number(itemIdList[i].value);
                    }
                    if (itemIdList[i].getAttribute("name") == "local1") {
                        localScore = Number(localScore) + Number(itemIdList[i].value);
                    }
                }
            } else {
                var itemIdList = $("span");
                for (var i = 0; i < itemIdList.length; i++) {
                    if (itemIdList[i].getAttribute("name") == "local") {
                        localTotalled = Number(localTotalled) + Number(itemIdList[i].innerText);
                    }
                    if (itemIdList[i].getAttribute("name") == "local1") {
                        localScore = Number(localScore) + Number(itemIdList[i].innerText);
                    }
                }
            }
            localAll = localTotalled + localScore;
            //基地自评分数
            $("#localTotalled").text(check(localTotalled.toFixed(2)));
            $("#localScore").text(check(localScore.toFixed(2)));
            $("#localAll").text(check(localAll.toFixed(2)));
        }

        function check(exp) {
            var reg1 = /^\d+(\.0)$/;
            if (reg1.test(exp)) {
                return parseInt(exp);
            } else {
                return exp;
            }
        }

        function saveSpeReason(expl) {
            var reason = expl.value;
            var reg = new RegExp("\\n", "g");//g,表示全部替换。
            reason = reason.replace(reg, "<br/>");
            reason = encodeURIComponent(reason);
            var itemId = expl.getAttribute("itemId");
            var itemName = expl.getAttribute("itemName");
            var url = "<s:url value='/jsres/supervisio/saveHospitalSelfAssessmentScore'/>";
            var data = {
                "itemId": itemId,
                "itemName": itemName,
                "speContent": reason,
                "orgFlow": '${assessment.orgFlow}',
                "orgName": '${assessment.orgName}',
                "speId": '${assessment.speId}',
                "speName": '${assessment.speName}',
                "subjectFlow": '${assessment.recordFlow}',
                "subjectName": '${assessment.subjectName}'
            };
            top.jboxPost(url, data, function (resp) {
                if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                    top.jboxTip(resp);
                } else {
                    top.jboxTip(resp);
                }
            }, null, false);
        }

        //保存总分  日期
        function submitScore() {
            var itemIdList = $("input");
            for (var i = 0; i < itemIdList.length; i++) {
                if (((itemIdList[i].getAttribute("name") == "local") || (itemIdList[i].getAttribute("name") == "local1"))
                    && itemIdList[i].value == "") {
                    $(itemIdList[i]).focus();
                    top.jboxTip("有输入框未输入分数，请输入分数！");
                    return;
                }
            }

            var signUrl = "${speSignUrl}";
            if (signUrl == "") {
                top.jboxTip("请上传签名图片");
                return false;
            }

            $.ajax({
                url: "<s:url value='/jsres/supervisio/findCoreIndicatorsNum'/>",
                type: "get",
                data: {"cfgFlow": '${assessment.recordFlow}',"orgFlow": '${assessment.orgFlow}',"speId": '${assessment.speId}',"subjectType": 'spe'},
                dataType: "json",
                success: function (res) {
                    var allScore = $('#localAll').text();
                    var assessmentResult = "";
                    if (Number(allScore) >= 80 && Number(res) >= 14) {
                        assessmentResult = "合格";
                    } else if ((70 <= Number(allScore) && Number(allScore) < 80) && (10 <= Number(res) && Number(res)<= 13)) {
                        assessmentResult = "基本合格";
                    } else if ((60 <= Number(allScore) && Number(allScore) < 70) || (7 <= Number(res) && Number(res)<= 9)) {
                        assessmentResult = "限时整改";
                    } else if (60 < Number(allScore) || 6 <= Number(res)) {
                        assessmentResult = "撤销";
                    }
                    var data = {
                        "allScore": allScore,
                        "recordFlow": '${assessment.recordFlow}',
                        "assessmentResult": assessmentResult,
                        "subjectType": "spe",
                        "orgFlow": '${assessment.orgFlow}',
                        "orgName": '${assessment.orgName}',
                        "speId": '${assessment.speId}',
                        "speName": '${assessment.speName}'
                    };
                    var url = "<s:url value='/jsres/supervisio/saveAssessmentAllScore'/>";
                    top.jboxPost(url, data, function (resp) {
                        if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
                            top.jboxTip(resp);
                            window.parent.toPage(1);
                            top.jboxCloseMessager();
                        } else {
                            top.jboxTip(resp);
                        }
                    }, null, false);
                }
            });
        }

        function scheduleOne(fileRoute, fileName) {
            var url = "<s:url value ='/jsres/supervisio/showAssessment'/>?type=${type}&cfgFlow=${assessment.recordFlow}&orgFlow=${assessment.orgFlow}&&speId=${assessment.speId}&fileRoute=" + fileRoute;

            if (fileRoute=='hospSelfAssessment0400_02'){
                top.jboxOpen(url, fileName, 1100, 335);
            }else {
                top.jboxOpen(url, fileName, 1100, 690);
            }
        }

        function scheduleMany(fileRoute, fileName) {
            var url = "<s:url value ='/jsres/supervisio/showAssessmentMany'/>?type=${type}&recordFlow=${assessment.recordFlow}&orgFlow=${assessment.orgFlow}&speId=${assessment.speId}&fileRoute=" + fileRoute;
            top.jboxOpen(url, fileName, 1100, 690);
        }
    </script>
</head>
<body>
<div id="tableContext" style="overflow-y: auto;margin-left: 10px;margin-right: 10px;height: 740px;" onscroll="tableFixed(this);">
    <div id="dateFixed" style="height: 0px;overflow: visible;position: relative;width: 100%;">
        <table border="0" cellpadding="0" cellspacing="0" class="grid" style="width: 99%">
            <tr height="34" class="firstRow">
                <th colspan="10">
                    <h2 style="font-size:150%">住院医师规范化培训专业基地评估指标——皮肤科（2023年版）</h2>
                </th>
            </tr>
            <tr height="28">
                <th style="text-align:left;padding-left: 4px;" height="28" colspan="5">培训基地（医院）名称：${orgName}</th>
                <th style="text-align:left;padding-left: 4px;" height="28" colspan="5">所属省（区、市）：${cityName}</th>
            </tr>
            <tr style="height:16px;">
                <th style="min-width: 360px;max-width: 360px;height: 16px;" colspan="3">评估项目</th>
                <th style="min-width: 310px;max-width: 470px;" rowspan="2">评估内容</th>
                <th style="min-width: 244px;max-width: 267px;" rowspan="2">现场评估方式</th>
                <th style="min-width: 360px;max-width: 520px;" rowspan="2">评分标准</th>
                <th style="min-width: 51px;max-width: 100px;" rowspan="2">分值</th>
                <th style="min-width: 82px;max-width: 100px;" rowspan="2">自评得分</th>
                <th style="min-width: 160px;max-width: 240px;" rowspan="2">详细情况</th>
                <th style="min-width: 130px;max-width: 130px;" rowspan="2">附件</th>
            </tr>
            <tr style="height:32px">
                <th style="width: 100px;height: 32px;">一级指标</th>
                <th style="width: 100px;">二级指标</th>
                <th style="width: 120px;">三级指标<br> ★为核心指标</th>
            </tr>
        </table>
    </div>
    <table border="0" cellpadding="0" cellspacing="0" class="grid" style="width: 99%">
        <tr height="34" class="firstRow">
            <th colspan="10">
                <h2 style="font-size:150%">住院医师规范化培训专业基地评估指标——皮肤科（2023年版）</h2>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" height="28" colspan="5">培训基地（医院）名称：${orgName}</th>
            <th style="text-align:left;padding-left: 4px;" height="28" colspan="5">所属省（区、市）：${cityName}</th>
        </tr>
        <tr style="height:16px;">
            <th style="min-width: 360px;max-width: 360px;height: 16px;" colspan="3">评估项目</th>
            <th style="min-width: 310px;max-width: 470px;" rowspan="2">评估内容</th>
            <th style="min-width: 244px;max-width: 267px;" rowspan="2">现场评估方式</th>
            <th style="min-width: 360px;max-width: 520px;" rowspan="2">评分标准</th>
            <th style="min-width: 51px;max-width: 100px;" rowspan="2">分值</th>
            <th style="min-width: 82px;max-width: 100px;" rowspan="2">自评得分</th>
            <th style="min-width: 160px;max-width: 240px;" rowspan="2">详细情况</th>
            <th style="min-width: 130px;max-width: 130px;" rowspan="2">附件</th>
        </tr>
        <tr style="height:32px">
            <th style="width: 100px;height: 32px;">一级指标</th>
            <th style="width: 100px;">二级指标</th>
            <th style="width: 120px;">三级指标<br> ★为核心指标</th>
        </tr>
        <tr >
            <td rowspan="10">1.基本条件<br>（15分）</td>
            <td rowspan="9">1.1专业基地相关<br>医疗和设备条件</td>
            <td>1.1.1总床位数</td>
            <td>≥10张</td>
            <td>
                现场查看
            </td>
            <td>
                符合标准，得1分<br>
                不达标准，不得分
            </td>

            <td style="text-align: center;">1</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,1);" itemId="1" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['1']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['1']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                   <textarea onchange="saveSpeReason(this);" itemId="1" name="reason"
                             placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                             class="text-input validate[required,maxSize[1000]]">${contentMap['1']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['1']}
                </c:if>
            </td>
            <td name="file" itemId="1"></td>
        </tr>
        <tr >
            <td style="height:53px;">1.1.2年收治病人数</td>
            <td>≥300人次</td>
            <td rowspan="2">
                检查相关统计报表复印件，需加盖医院公章
            </td>
            <td>
                符合标准，得1分<br>
                不达标准，不得分
            </td>

            <td style="text-align: center;">1</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,1);" itemId="2"  name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['2']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['2']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="2"  name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['2']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['2']}
                </c:if>
            </td>
            <td name="file" itemId="2"></td>
        </tr>
        <tr >
            <td>
                1.1.3年门诊量
            </td>
            <td>≥50000人次</td>
            <td>
                符合标准，得1分<br>
                不达标准，不得分
            </td>

            <td style="text-align: center;">1</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,1);" itemId="3"  name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['3']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['3']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="3"  name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['3']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['3']}
                </c:if>
            </td>
            <td name="file" itemId="3"></td>
        </tr>
        <tr >
            <td>
                1.1.4科室和实验室
            </td>
            <td>急诊科、内科、外科、妇产科、放射
                （影像）科、病理科、超声科及皮肤科
                实验室
            </td>
            <td>现场查看</td>
            <td>
                满足要求，得1分；缺1项，不得分
            </td>

            <td style="text-align: center;">1</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,1);" itemId="4"  name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['4']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['4']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="4"  name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['4']}</textarea>

                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['4']}
                </c:if>
            </td>
            <td name="file" itemId="4"></td>
        </tr>
        <tr >
            <td>
                1.1.5轮转科室
            </td>
            <td>必选科室：皮肤性病科、心血管内科、
                呼吸内科、风湿免疫科、急诊科、皮肤
                病理室、皮肤病治疗室（含光疗室）、
                临床检验室、皮肤外科手术室
                可选科室：肾内科、消化内科、血液内
                科、内分泌科、普通外科、整形外科、
                妇科门诊、泌尿外科门诊、感染科</td>
            <td>1.现场查看
                2.访谈住院医师</td>
            <td>
                科室齐全，得1分
                缺1个科室，扣0.5分，扣完为止
            </td>

            <td style="text-align: center;">1</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,1);" itemId="5" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['5']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['5']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="5" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['5']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['5']}
                </c:if>
            </td>
            <td name="file" itemId="5"></td>
        </tr>
        <tr >
            <td>
                1.1.6疾病种类及数量★
            </td>
            <td rowspan="2">符合《住院医师规范化培训基地标准
                （2022年版）》和《住院医师规范化培
                训内容与标准（2022年版）》皮肤性病
                专业细则要求（见
                <a href="javascript:void(0)" onclick="scheduleOne('hospSelfAssessment0400_01','附件1');">附件1</a>
                和
                <a href="javascript:void(0)" onclick="scheduleOne('hospSelfAssessment0400_02','附件2');">附件2</a>
                ）
            </td>
            <td>现场查看</td>
            <td>
                1.专业基地的疾病种类及数量符合标准要
                求，或疾病种类及数量≥规定数的75%，且
                有符合要求的协同单位，总疾病种类及数
                量符合标准要求，得满分 <br>
                2.其他情况均不得分，专业基地暂停招收
                住院医师
            </td>

            <td style="text-align: center;">3</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input id="fubiao11" onchange="saveScore4Expert(this,3,0);" itemId="6" name="local"
                           class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['6']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['6']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="6" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['6']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['6']}
                </c:if>
            </td>
            <td name="file" itemId="6"></td>
        </tr>
        <tr >
            <td>
                1.1.7技能操作种类及数量★
            </td>
            <td>
                现场查看
            </td>
            <td>
                专业基地的临床技能操作种类及数量符合
                标准要求，得满分，否则，不得分
            </td>

            <td style="text-align: center;">3</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input id="fubiao12" onchange="saveScore4Expert(this,3,0);" itemId="7" name="local"
                           class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['7']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['7']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="7" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['7']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['7']}
                </c:if>
            </td>
            <td name="file" itemId="7"></td>
        </tr>
        <tr >
            <td>
                1.1.8医院设备
            </td>
            <td>冰冻切片机1台，荧光显微镜1台</td>
            <td>
                现场查看
            </td>
            <td>满足要求，得1分；缺1项，不得分</td>

            <td style="text-align: center;">1</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,1);" itemId="8"  name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['8']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['8']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="8"  name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['8']}</textarea>

                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['8']}
                </c:if>
            </td>
            <td name="file" itemId="8"></td>
        </tr>
        <tr >
            <td>
                1.1.9专业基地设备
            </td>
            <td>符合《住院医师规范化培训基地标准
                （2022年版）》皮肤性病专业细则要求
                （见
                <a href="javascript:void(0)" onclick="scheduleOne('hospSelfAssessment0400_03','附件3');">附件3</a>）
            </td>
            <td>
                现场查看
            </td>
            <td>
                满足要求，得1分；缺1项，不得分
            </td>

            <td style="text-align: center;">1</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input id="fubiao3" onchange="saveScore4Expert(this,1);" itemId="9"  name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['9']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['9']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="9"  name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['9']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['9']}
                </c:if>
            </td>
            <td name="file" itemId="9"></td>
        </tr>
        <tr >
            <td>
                1.2协同情况
            </td>
            <td>
                1.2.1协同单位与科室数量
                、轮转时间
            </td>
            <td>协同单位数量不超过3家，协同培训的科
                室（含亚专业）不超过3个，在协同培训
                单位的轮转时间累计不超过6个月
            </td>
            <td>
                现场查看
            </td>
            <td>
                完全符合要求，得2分
                1项不符合要求，不得分
                无协同单位的专业基地，此处不失分
            </td>

            <td style="text-align: center;">2</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,2);" itemId="11"  name="local"
                           class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['11']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['11']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="11"  name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['11']}</textarea>

                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['11']}
                </c:if>
            </td>
            <td name="file" itemId="11"></td>
        </tr>
        <tr >
            <td rowspan="8">2.师资管理（15分）</td>
            <td rowspan="5">2.1师资情况</td>
            <td>
                2.1.1指导医师与住院
                医师比例★
            </td>
            <td>每名指导医师同时带教本专业住院医师
                不超过2名</td>
            <td>
                1.现场查看
                2.访谈指导医师和住院医师
            </td>
            <td>
                符合标准，得1分
                不达标准，不得分
            </td>

            <td style="text-align: center;">1</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,1,0);" itemId="12"  name="local"
                           class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['12']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['12']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="12"  name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['12']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['12']}
                </c:if>
            </td>
            <td name="file" itemId="12"></td>
        </tr>
        <tr >
            <td>
                2.1.2指导医师条件
            </td>
            <td>具有医学本科及以上学历，主治医师专
                业技术职务3年及以上，从事本专业医疗
                与教学工作3年及以上，能阅读病理片
            </td>
            <td rowspan="3">
                查看人事部门提供的师资状
                况统计表，包括姓名、毕业
                时间、毕业学校、学历学位
                、专业技术职务、专业技术
                职务任职时间、工作时间，
                需加盖人事部门公章
            </td>
            <td>
                符合标准，得1分
                有1名指导医师不符合要求，不得分
            </td>

            <td style="text-align: center;">1</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,1);" itemId="13" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['13']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['13']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="13" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['13']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['13']}
                </c:if>
            </td>
            <td name="file" itemId="13"></td>
        </tr>
        <tr >
            <td>
                2.1.3指导医师组成
            </td>
            <td>具有中级专业技术职务及以上人员≥
                7人，其中具有高级专业技术职务的≥4
                人；
                能开展皮肤外科手术，且具备皮肤科主
                治医师及以上专业技术职务的医师≥2人
            </td>
            <td>
                符合标准，得1分
                只要有1个亚专业不达标，不得分
            </td>

            <td style="text-align: center;">1</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,1);" itemId="14" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['14']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['14']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="14" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['14']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['14']}
                </c:if>
            </td>
            <td name="file" itemId="14"></td>
        </tr>
        <tr >
            <td>
                2.1.4专业基地负责人条件
            </td>
            <td>具有医学本科及以上学历，主任医师专
                业技术职务，从事皮肤科临床医疗和教
                学工作15年及以上，且正在从事本专业
                临床医疗与教学工作
            </td>
            <td>
                符合标准，得1分
                有1项不符合条件，不得分
            </td>

            <td style="text-align: center;">1</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,1);" itemId="15" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['15']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['15']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="15" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['15']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['15']}
                </c:if>
            </td>
            <td name="file" itemId="15"></td>
        </tr>
        <tr >
            <td>
                2.1.5责任导师制度
            </td>
            <td>为每名住院医师配置1名固定的责任指导
                医师作为导师，负责住院医师在培训期
                间的全程指导
            </td>
            <td>
                1.现场查看
                2.访谈责任导师和住院医师
            </td>
            <td>
                符合标准，得1分
                未配置责任导师或配置但未有效落实，不
                得分
            </td>

            <td style="text-align: center;">1</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,1);" itemId="16" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['16']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['16']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="16" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['16']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['16']}
                </c:if>
            </td>
            <td name="file" itemId="16"></td>
        </tr>
        <tr >
            <td rowspan="3">2.2师资建设</td>
            <td>
                2.2.1师资培训
            </td>
            <td>指导医师上岗前需参加院级师资上岗培
                训，培训率100%，持有效期内师资证上
                岗，并不断接受教学能力提升的继续教
                育。近5年内，每个专业基地负责人、教
                学主任、教学秘书和每个轮转科室1名以
                上骨干指导医师经过省级及以上的师资
                培训
            </td>
            <td>
                1.现场查看
                2.访谈指导医师
            </td>
            <td>
                2项均满足，得3分
                1项满足，得1.5分
                2项均不满足，不得分
            </td>

            <td style="text-align: center;">3</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,3);" itemId="17" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['17']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['17']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                     <textarea onchange="saveSpeReason(this);" itemId="17" name="reason"
                               placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                               class="text-input validate[required,maxSize[1000]]">${contentMap['17']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['17']}
                </c:if>
            </td>
            <td name="file" itemId="17"></td>
        </tr>
        <tr >
            <td>
                2.2.2师资评价
            </td>
            <td>每年度至少组织1次对每位指导医师的教学工作
                进行评价
            </td>
            <td>
                1.现场查看
                2.访谈指导医师和住院医师
            </td>
            <td>
                评价方案全面，原始记录详实，有落实、
                评价结果分析运用、改进，得3分
                评价方案简单，有落实，无结果分析运
                用，得2分；
                无方案，但有单一评价记录，得1分
                无方案或有方案无评价实施记录，不得分
            </td>

            <td style="text-align: center;">3</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,3);" itemId="18" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['18']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['18']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="18" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['18']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['18']}
                </c:if>
            </td>
            <td name="file" itemId="18"></td>
        </tr>
        <tr >
            <td>2.2.3激励制度★</td>
            <td>建立带教活动绩效管理制度，将带教活
                动与专业基地绩效考核挂钩，并在科室
                二次分配中将专业基地负责人、教学主
                任、教学秘书的教学管理活动和指导医
                师的带教活动，纳入个人绩效考核的重
                要指标范围
            </td>
            <td>
                1.现场查看
                2.访谈专业基地管理人员、指导医师
            </td>
            <td>
                教学绩效考核不低于考核总分的8%，考核
                结果与专业技术职务晋升挂钩，得4分
                教学绩效考核占考核总分的5%～8%之间，
                得2分
                教学绩效考核占考核总分低于5%或不纳
                入，不得分
            </td>

            <td style="text-align: center;">4</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,4,0);" itemId="19" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['19']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['19']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="19" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['19']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['19']}
                </c:if>
            </td>
            <td name="file" itemId="19"></td>
        </tr>
        <tr >
            <td style="height: 438px;" rowspan="15">3.培训管理（35分）</td>
            <td rowspan="5">3.1制度与落实</td>
            <td>
                3.1.1专业基地负责人
            </td>
            <td>实行专业基地负责人负责制，负责协调
                本专业和相关专业的教学资源,加强对教
                学与培训人员的组织管理,整体把控培训
                质量,对本专业基地(含协同单位)的培训
                质量承担主要责任，并切实落实
            </td>
            <td rowspan="4">
                1.现场查看
                2.访谈专业基地管理人员、指导医
                师
            </td>
            <td>
                职责明确，履职认真，得1分
                无岗位职责，或履职不认真，不得分
            </td>

            <td style="text-align: center;">1</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,1);" itemId="20" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['20']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['20']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="20" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['20']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['20']}
                </c:if>
            </td>
            <td name="file" itemId="20"></td>
        </tr>
        <tr >
            <td>
                3.1.2教学主任
            </td>
            <td>设置教学主任岗位，负责本专业住院医
                师的轮转计划制订;负责本专业培训的全
                过程管理;定期检查评价住院医师的培训
                质量 和指导医师的带教质量等
            </td>
            <td>
                职责明确，履职认真，得2分
                无岗位职责，或履职不认真，不得分
            </td>

            <td style="text-align: center;">2</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,2);" itemId="21" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['21']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['21']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="21" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['21']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['21']}
                </c:if>
            </td>
            <td name="file" itemId="21"></td>
        </tr>
        <tr >
            <td>
                3.1.3教学秘书
            </td>
            <td>设置教学秘书岗位，协助专业基地负责
                人、教学主任开展培训与教学工作。执
                行专业基地负责人、教学主任布置的各
                项培训工作任务,督促指导医师积极落实
                带教任务等。
            </td>
            <td>
                有教学秘书，履职认真，得2分
                无教学秘书，或履职不认真，不得分
            </td>

            <td style="text-align: center;">2</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,2);" itemId="22" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['22']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['22']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="22" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['22']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['22']}
                </c:if>
            </td>
            <td name="file" itemId="22"></td>
        </tr>
        <tr >
            <td>
                3.1.4教学小组★
            </td>
            <td>成立教学小组，组成合理、职责明确，
                切实履职。能定期进行带教指导、指导
                医师培训、教学培训实施和考核、质量
                监管以及教学研究等工作
            </td>
            <td>
                有教学小组，履职认真，得2分
                无教学小组，或履职不认真，不得分
            </td>

            <td style="text-align: center;">2</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,2,0);" itemId="23" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['23']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['23']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="23" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['23']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['23']}
                </c:if>
            </td>
            <td name="file" itemId="23"></td>
        </tr>
        <tr >
            <td>
                3.1.5轮转计划★
            </td>
            <td>按规定制定和落实轮转计划和要求，体现分层
                递进的培训理念
            </td>
            <td>
                1.现场核查在培住院医师轮
                转情况
                2.访谈专业基地管理人员、
                指导医师和住院医师
            </td>
            <td>
                轮转计划体现分层递进，并严格落实，得5
                分
                轮转计划未体现分层递进，但严格落实，
                得3分
                未制定轮转计划或未严格落实的，不得
                分，专业基地限期整改
            </td>

            <td style="text-align: center;">5</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,5,0);" itemId="24" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['24']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['24']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="24" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['24']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['24']}
                </c:if>
            </td>
            <td name="file" itemId="24"></td>
        </tr>
        <tr >
            <td>3.2培训招收
            </td>
            <td>3.2.1培训容量及招收
                ★
            </td>
            <td>容量测算科学合理，完成招收任务，且3
                年内在培住院医师≥10名，不能超容量
                招收
            </td>
            <td>
                1.现场查看
                2.访谈住院医师
            </td>
            <td>
                招收在容量测算数内，完成招收任务，且
                在培住院医师≥10名，得2分
                容量测算科学合理，近三年未完成招收任
                务，但在培住院医师≥10名，得1分
                在培住院医师＜10名，或超容量招收，不
                得分
            </td>

            <td style="text-align: center;">2</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,2,0);" itemId="25" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['25']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['25']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="25" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['25']}</textarea>

                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['25']}
                </c:if>
            </td>
            <td name="file" itemId="25"></td>
        </tr>
        <tr >
            <td rowspan="4">3.3培训活动</td>
            <td>
                3.3.1入专业基地及入
                轮转科室教育
            </td>
            <td>规范开展入专业基地教育，包括学科背
                景、规章制度及流程、专业基地培训目
                标、培训内容和轮转计划、轮转期间所
                需掌握的临床诊疗能力、技能操作等内
                容，并有专人组织实施
                规范开展入轮转科室教育，包括科室情
                况、科室纪律、培养计划与要求、医德
                医风、医患沟通等内容的入科教育，培
                训与考核要求体现科室岗位基本需求特
                点，并有专人组织实施
            </td>
            <td rowspan="4">
                1.现场查看教学活动落实情
                况
                2.访谈专业基地管理人员、
                指导医师和住院医师
            </td>
            <td>
                1.按指南要求规范组织实施入专业基地教
                育，得1.5分；未组织或组织实施不规范，
                不得分
                2.按指南要求规范组织实施入轮转科室教
                育，得1.5分；未组织或组织实施不规范，
                不得分
            </td>

            <td style="text-align: center;">2</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,2);" itemId="26" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['26']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['26']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="26" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['26']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['26']}
                </c:if>
            </td>
            <td name="file" itemId="26"></td>
        </tr>

        <tr >
            <td>
                3.3.2教学查房★
            </td>
            <td>开展规范的教学查房，至少2周1次</td>
            <td>
                开展次数达标，且认真规范，得3分
                开展次数达标，但不认真规范，得2分
                未开展或不达标，不得分
            </td>

            <td style="text-align: center;">3</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,3,0);" itemId="27" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['27']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['27']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="27" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['27']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['27']}
                </c:if>
            </td>
            <td name="file" itemId="27"></td>
        </tr>
        <tr >
            <td>
                3.3.3小讲课
            </td>
            <td>开展规范的小讲课活动，至少每周1次</td>
            <td>
                开展次数达标，且认真规范，得2分
                开展次数达标，但不认真规范，得1分
                未开展或不达标，不得分
            </td>

            <td style="text-align: center;">2</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,2);" itemId="28" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['28']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['28']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="28" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['28']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['28']}
                </c:if>
            </td>
            <td name="file" itemId="28"></td>
        </tr>
        <tr >
            <td>
                3.3.4教学病例讨论
            </td>
            <td>开展规范的教学病例讨论，至少2周1次</td>
            <td>
                开展次数达标，且认真规范，得2分
                开展次数达标，但不认真规范，得1分
                未开展或不达标，不得分
            </td>

            <td style="text-align: center;">2</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,2);" itemId="29" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['29']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['29']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="29" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['29']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['29']}
                </c:if>
            </td>
            <td name="file" itemId="29"></td>
        </tr>
        <tr >
            <td rowspan="3">3.4过程考核
            </td>
            <td>3.4.1日常考核</td>
            <td>包括医德医风、临床职业素养、考勤、
                临床实践能力、培训指标完成情况和参
                加业务学习情况，以及形成性评价开展
                情况等内容，相关记录等原始资料齐
                全，真实规范
            </td>
            <td rowspan="3">
                1.现场查看过程考核落实情
                况
                2.访谈专业基地管理人员、
                指导医师和住院医师
            </td>
            <td>
                评估项目全面，且认真规范，得2分
                仅有测评结果和考勤记录，得1分
                无，不得分
            </td>

            <td style="text-align: center;">2</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,2);" itemId="30" name="local"
                           class="input" type="text" style="height:20px;width: 25px; text-align: center"
                           value="${scoreMap['30']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['30']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="30" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['30']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['30']}
                </c:if>
            </td>
            <td name="file" itemId="30"></td>
        </tr>
        <tr >
            <td>3.4.2出科考核★</td>
            <td>
                有出科考核实施细则；考核内容包括理
                论与技能，体现专业特点和岗位胜任、
                分层递进的培训理念；出科考核形式规
                范，原始资料齐全，专业基地教学小组
                审核和组长签字
            </td>
            <td>
                1.有实施细则，得1分
                2.考核内容齐全，并体现专业特点和岗位
                胜任力、分层递进，得1.5分
                3.考核形式规范，资料齐全，有专业基地
                教学小组审核和组长签字，得0.5分
            </td>

            <td style="text-align: center;">3</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,3,0);" itemId="31" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['31']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['31']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                 <textarea onchange="saveSpeReason(this);" itemId="31" name="reason"
                           placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                           class="text-input validate[required,maxSize[1000]]">${contentMap['31']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['31']}
                </c:if>
            </td>
            <td name="file" itemId="31"></td>
        </tr>
        <tr >
            <td>3.4.3年度考核
            </td>
            <td>有符合本专业的年度考核实施细则，内
                容包括个人总结、理论知识、实践技能
                、综合评价等，能真实全面反映年度培
                训情况，体现专业特点和分层递进的培
                训要求
            </td>
            <td>考核内容全面，能体现岗位胜任力，得1分
                考核项目不全面，或不能体现岗位胜任
                力，得0.5分
                无年度考核，不得分
            </td>

            <td style="text-align: center;">1</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,1);" itemId="32" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['32']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['32']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="32" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['32']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['32']}
                </c:if>
            </td>
            <td name="file" itemId="32"></td>
        </tr>
        <tr >
            <td rowspan="2">3.5培训强度
            </td>
            <td>3.5.1病房完成诊疗病人
                数★
            </td>
            <td>病房工作期间每名住院医师日管理床位
                ≥3张，每半年收治患者≥48人
            </td>
            <td rowspan="2">
                查看轮转手册（或医院相关
                电子系统）等，随机抽查访
                谈各类住院医师各1～2名
            </td>
            <td>
                管床数（或管理患者数）达到要求，得3分
                管床2张，或完成病例数≥34人，得1分
                管床数≤1张，或完成病例数≤33，不得分
            </td>

            <td style="text-align: center;">3</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,3,0);" itemId="33" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['33']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['33']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                  <textarea onchange="saveSpeReason(this);" itemId="33" name="reason"
                            placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                            class="text-input validate[required,maxSize[1000]]">${contentMap['33']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['33']}
                </c:if>
            </td>
            <td name="file" itemId="33"></td>
        </tr>
        <tr >
            <td>3.5.2门急诊工作量★
            </td>
            <td>门诊每日接诊患者平均≥30人次
            </td>
            <td>
                门急诊工作量达到要求，得3分
                门急诊量≥规定数的80%，得2.5分
                门急诊量≥规定数的60%，得1分
                门急诊量＜规定数60%或未安排，不得分
            </td>

            <td style="text-align: center;">3</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,3,0);" itemId="331" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['331']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['331']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                  <textarea onchange="saveSpeReason(this);" itemId="331" name="reason"
                            placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                            class="text-input validate[required,maxSize[1000]]">${contentMap['331']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['331']}
                </c:if>
            </td>
            <td name="file" itemId="331"></td>
        </tr>
        <tr >
            <td rowspan="8">4.培训质量（35分）</td>
            <td rowspan="3">4.1指导医师教学质量</td>
            <td>4.1.1教学查房质量★</td>
            <td>
                指导医师规范组织教学查房，悉心指导
                住院医师
            </td>
            <td>
                随机抽查1～2名指导医师教学查房
            </td>
            <td>教学查房评分表见
                <a href="javascript:void(0)" onclick="scheduleOne('hospSelfAssessment0400_04','附件4');">附件4</a><br>
                评估分值≥90分，得4分； <br>
                80分≤评估分值＜90分，得3分；<br>
                60分≤评估分值＜80分，得2分；<br>
                评估分值＜60分，不得分
            </td>
            <td style="text-align: center;">4</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input id="fb4" onchange="saveScore4Expert(this,4,0);" itemId="35" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['35']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['34']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                <textarea onchange="saveSpeReason(this);" itemId="35" name="reason"
                          placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                          class="text-input validate[required,maxSize[1000]]">${contentMap['35']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['35']}
                </c:if>
            </td>
            <td name="file" itemId="35"></td>
        </tr>
        <tr >
            <td>
                4.1.2教学病例讨论质量
            </td>
            <td>主任或指导医师组织教学病例讨论，悉
                心指导住院医师
            </td>
            <td>
                随机抽查1名指导医师教学病
                例讨论
            </td>
            <td>
                教学病例讨论评分表见<a href="javascript:void(0)" onclick="scheduleOne('hospSelfAssessment0400_05','附件5');">附件5</a><br>
                评估分值≥90分，得4分；<br>
                80分≤评估分值＜90分，得3分；<br>
                60分≤评估分值＜80分，得2分；<br>
                评估分值＜60分，不得分
            </td>

            <td style="text-align: center;">4</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input id="fb5" onchange="saveScore4Expert(this,4,0);" itemId="36" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['36']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['36']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
               <textarea onchange="saveSpeReason(this);" itemId="36" name="reason"
                         placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                         class="text-input validate[required,maxSize[1000]]">${contentMap['36']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['36']}
                </c:if>
            </td>
            <td name="file" itemId="36"></td>
        </tr>
        <tr >
            <td>
                4.1.3技能操作和手术带
                教情况★
            </td>
            <td>指导医师协助并指导住院医师完成技能
                操作或手术，带教严格规范
            </td>
            <td>
                随机抽查1～2名指导医师指
                导住院医师（二年级以上）
                进行技能操作或手术情况
            </td>
            <td>
                1.住院医师操作前进行有效的交流，并与
                患者沟通核实，得1分
                2.住院医师操作中应如遇重大医疗安全隐
                患或将造成患者较大伤害的，应予以及时
                制止与纠正，得1分
                3.住院医师操作结束后提问，得1分
                4.对住院医师的操作采用合适的反馈方式
                进行总体评价，如有必要进行适当示范，
                得2分
            </td>

            <td style="text-align: center;">5</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,5,0);" itemId="37" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['37']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['37']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
               <textarea onchange="saveSpeReason(this);" itemId="37" name="reason"
                         placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                         class="text-input validate[required,maxSize[1000]]">${contentMap['37']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['37']}
                </c:if>
            </td>
            <td name="file" itemId="37"></td>
        </tr>
        <tr >
            <td rowspan="5">
                4.2住院医师培训效果
            </td>
            <td>
                4.2.1病历书写★
            </td>
            <td>住院医师病历书写情况
            </td>
            <td>
                随机抽查1～2名住院医师运行病历和
                归档病历各1份
            </td>
            <td>
                病历书写评分表见
                <a href="javascript:void(0)" onclick="scheduleOne('hospSelfAssessment0400_06','附件6');">附件6</a><br>
                评估分值≥90分，得6分；<br>
                80分≤评估分值＜90分，得5分；<br>
                70分≤评估分值＜80分，得3分；<br>
                60分≤评估分值＜70分，得2分； <br>
                评估分值＜60分，不得分
            </td>
            <td style="text-align: center;">6</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input id="fb6" onchange="saveScore4Expert(this,6,0);" itemId="38" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['38']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['38']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
               <textarea onchange="saveSpeReason(this);" itemId="38" name="reason"
                         placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                         class="text-input validate[required,maxSize[1000]]">${contentMap['38']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['38']}
                </c:if>
            </td>
            <td name="file" itemId="38"></td>
        </tr>
        <tr >
            <td>
                4.2.2技能操作和手术★
            </td>
            <td>住院医师技能操作或手术情况</td>
            <td>随机抽查1～2名二年级以上
                住院医师进行技能操作或常
                见手术操作（术者、助
                手），查看其掌握情况
            </td>
            <td>
                技能操作评分表见
                <a href="javascript:void(0)" onclick="scheduleOne('hospSelfAssessment0400_07','附件7');">附件7</a><br>
                评估分值≥90分，得7分；<br>
                80分≤评估分值＜90分，得5分；<br>
                70分≤评估分值＜80分，得4分；<br>
                60分≤评估分值＜70分，得2分；<br>
                评估分值＜60分，不得分
            </td>

            <td style="text-align: center;">7</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input id="fb7" onchange="saveScore4Expert(this,7,0);" itemId="39" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['39']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['39']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                 <textarea onchange="saveSpeReason(this);" itemId="39" name="reason"
                           placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                           class="text-input validate[required,maxSize[1000]]">${contentMap['39']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['39']}
                </c:if>
            </td>
            <td name="file" itemId="39"></td>
        </tr>
        <tr >
            <td>
                4.2.3技能操作和手术
                完成情况★
            </td>
            <td>每个轮转科室均按照《住院医师规范化
                培训内容与标准（2022年版）》皮肤性
                病专业培训细则要求执行</td>
            <td>
                随机抽查5～10名住院医师，
                由评估专家根据本专业实际
                需求确定手术或技能操作项
                目，查看技能操作或手术记
                录，掌握岗位胜任力的实际
                情况
            </td>
            <td>
                完成率≥95%，得5分
                90%≤完成率＜95%，得4分
                80%≤完成率＜90%，得2.5分
                完成率＜80%，不得分
            </td>

            <td style="text-align: center;">5</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,5,0);" itemId="40" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['40']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['40']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                <textarea onchange="saveSpeReason(this);" itemId="37" name="reason"
                          placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                          class="text-input validate[required,maxSize[1000]]">${contentMap['40']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['40']}
                </c:if>
            </td>
            <td name="file" itemId="40"></td>
        </tr>
        <tr >
            <td>
                4.2.4执业医师资格考
                试
            </td>
            <td>住院医师首次参加执业医师资格考试的通过率
            </td>
            <td>
                现场查看
            </td>
            <td>
                近三年本专业住院医师首次参加执业医师
                资格考试的通过率≥近三年全国本专业住
                院医师首次参加执业医师资格考试的平均
                通过率，得1分；低于近三年全国本专业住
                院医师首次参加执业医师资格考试的平均
                通过率不得分
                （近三年本专业住院医师首次参加执业医
                师资格考试的通过率=近三年本专业首次参
                加考试通过的人数/近三年本专业首次参加
                考试总人数）
            </td>

            <td style="text-align: center;">1</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,1);" itemId="41" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['41']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['41']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
              <textarea onchange="saveSpeReason(this);" itemId="41" name="reason"
                        placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                        class="text-input validate[required,maxSize[1000]]">${contentMap['41']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['41']}
                </c:if>
            </td>
            <td name="file" itemId="41"></td>
        </tr>
        <tr >
            <td>4.2.5结业考核★
            </td>
            <td>住院医师首次参加结业考核的通过率</td>
            <td>
                现场查看
            </td>
            <td>
                近三年本专业住院医师首次参加结业理论
                考核的通过率≥近三年全国本专业住院医
                师首次参加结业理论考核平均通过率，得3
                分；低于近三年全国本专业住院医师首次
                参加结业理论考核平均通过率，每降低1个
                百分点，扣1分，扣完为止
                （近三年本专业住院医师首次参加结业理
                论考核的通过率=近三年本专业首次参加结
                业理论考核通过的人数/近三年本专业首次
                参加结业理论考核总人数）
            </td>

            <td style="text-align: center;">3</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,3,0);" itemId="42" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['42']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['42']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
              <textarea onchange="saveSpeReason(this);" itemId="42" name="reason"
                        placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                        class="text-input validate[required,maxSize[1000]]">${contentMap['42']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['42']}
                </c:if>
            </td>
            <td name="file" itemId="42"></td>
        </tr>
        <tr >
            <td colspan="6" style="text-align: right">合计</td>
            <td style="text-align: center"> 100</td>
            <td style="text-align: center" id="localAll"></td>
            <td></td>
            <td></td>
        </tr>

        <tr style="height:16px;">
            <td colspan="10">
                <c:if test="${type eq 'Y'}">
              <textarea onchange="saveSpeReason(this);" itemId="43" name="reason"
                        placeholder="请详细填写存在问题" style="width: 100%;height: 100px"
                        class="text-input validate[required,maxSize[1000]]">${contentMap['43']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['40']}
                </c:if>
            </td>
        </tr>
    </table>

    <div style="margin-top: 20px;">
        备注：<br>
        1.一级指标4项，二级指标11项，三级指标41项。三级指标中，核心指标17项、计62分，一般指标24项、计38分，共100分。单个核心指标达标判定标准：单个核心指标得分率≥70%为达标，＜70%为不达标。其中，单项指标满分为3分的，若评估得2分的则判定为达标。<br>
        合格：评估分值≥80分，且核心指标达标数≥14个<br>
        基本合格：70分≤评估分值＜80分，且10个≤核心指标达标数≤13个<br>
        限期整改(黄牌)：60分≤评估分值＜70分；或基本条件合格，7个≤核心指标达标数≤9个；或在培住院医师总数近3年达不到专业基地最小培训容量要求的；未制定轮转计划或未严格落实的；结业理论考核通过率处于全省末位者。<br>
        撤销(红牌)：评估分值＜60分；或核心指标达标数≤6个；或连续3年“零”招收<br>
        2.指标中所有规章制度，专指住院医师规范化培训相关制度。<br>
        3.随机抽查对象优先选择委托住院医师和面向社会招收的住院医师，如果没有，可考虑本基地住院医师。<br>
        4.现场评估时详细填写存在的问题和详细情况。<br>
        5.专业基地应确保所提供的材料真实可靠，对于弄虚作假者，一经查实，将提请当地省级卫生健康行政部门暂停其住培招收资格，情节严重的，撤销其专业基地资格。
    </div>
    <div style="margin-top: 30px;">
        <div style="margin-right: 2%;">评估专家签字：
            <c:if test="${not empty speSignUrl}">
                <img src="${sysCfgMap['upload_base_url']}/${speSignUrl}" style="width: 250px;height: 80px;">
            </c:if>
            <c:if test="${empty speSignUrl}">请上传签名图片</c:if></div>
        <div style="float: right;margin-right: 20%;margin-top: -15px;">
            日期：${subTime}
        </div>
    </div>

</div>
<div class="button" style="margin-top: 25px">
    <c:if test="${type eq 'Y'}">
        <input class="btn_green" type="button" value="提&#12288;交" onclick="submitScore();"/>&#12288;
        <input class="btn_green" type="button" id="close" value="取&#12288;消" onclick="top.jboxCloseMessager();"/>
    </c:if>
    <c:if test="${type ne 'Y'}">
        <input class="btn_green" type="button" id="close" value="关&#12288;闭" onclick="top.jboxCloseMessager();"/>
    </c:if>
</div>
</body>
</html>