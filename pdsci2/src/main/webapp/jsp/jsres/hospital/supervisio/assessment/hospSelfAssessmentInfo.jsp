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
            if ('${widthSize}'=='Y'){
                $('#tableContext').css("width","1000px");
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


        function tableFixed(div){
            $("#dateFixed").css("top",$(div).scrollTop()+"px");
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
            var localAll = 0;
            if (${type eq 'Y'}){
                var itemIdList = $("input");
                for (var i = 0; i < itemIdList.length; i++) {
                    if (itemIdList[i].getAttribute("name") == "local") {
                        localAll = Number(localAll) + Number(itemIdList[i].value);
                    }
                }
            }else {
                var itemIdList = $("span");
                for (var i = 0; i < itemIdList.length; i++) {
                    if (itemIdList[i].getAttribute("name") == "local") {
                        localAll = Number(localAll) + Number(itemIdList[i].innerText);
                    }

                }
            }
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
            var reg = new RegExp("\\n","g");//g,表示全部替换。
            reason=reason.replace(reg,"<br/>");
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
                data: {"cfgFlow": '${assessment.recordFlow}',"orgFlow": '${assessment.orgFlow}',"speId": '${assessment.speId}',"subjectType": 'org'},
                dataType: "json",
                success: function (res) {
                    var allScore = $('#localAll').text();
                    var assessmentResult = "";
                    if (Number(allScore) >= 80 && Number(res) >= 13) {
                        assessmentResult = "合格";
                    } else if ((70 <= Number(allScore) && Number(allScore) < 80) && 10 <= Number(res)) {
                        assessmentResult = "基本合格";
                    } else if ((60 <= Number(allScore) && Number(allScore) < 70) || (6 <= Number(res) && Number(res)<= 9)) {
                        assessmentResult = "限时整改";
                    } else if (60 < Number(allScore) || 5 <= Number(res)) {
                        assessmentResult = "撤销";
                    }
                    var data = {
                        "allScore": allScore,
                        "recordFlow": '${assessment.recordFlow}',
                        "assessmentResult": assessmentResult,
                        "subjectType": "org",
                        "orgFlow": '${assessment.orgFlow}',
                        "orgName": '${assessment.orgName}'
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
    </script>
</head>
<body>
<div id="tableContext" style="overflow-y: auto;margin-left: 10px;margin-right: 10px;height: 740px;" onscroll="tableFixed(this);">
    <div id="dateFixed" style="height: 0px;overflow: visible;position: relative;width: 100%;">
        <table border="0" cellpadding="0" cellspacing="0" class="grid" style="width: 99%" >
            <tr height="34" class="firstRow">
                <th colspan="10">
                    <h2 style="font-size:150%">住院医师规范化培训基地评估指标—管理（2023 年版）</h2>
                </th>
            </tr>
            <tr height="28">
                <th style="text-align:left;padding-left: 4px;" height="28" colspan="10">培训基地（医院）名称：${orgName}</th>
            </tr>
            <tr style="height:16px;">
                <th style="min-width: 360px;max-width: 360px;height: 16px;" colspan="3">评估项目</th>
                <th style="min-width: 260px;max-width: 420px;" rowspan="2">评估内容</th>
                <th style="min-width: 410px;max-width: 600px;" rowspan="2">评分标准</th>
                <th style="min-width: 244px;max-width: 367px;" rowspan="2">现场评估方式</th>
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
    <table border="0" cellpadding="0" cellspacing="0" class="grid" style="width: 99%" >
        <tr height="34" class="firstRow">
            <th colspan="10">
                <h2 style="font-size:150%">住院医师规范化培训基地评估指标—管理（2023 年版）</h2>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" height="28" colspan="10  ">培训基地（医院）名称：${orgName}</th>
        </tr>
        <tr style="height:16px;">
            <th style="min-width: 360px;max-width: 360px;height: 16px;" colspan="3">评估项目</th>
            <th style="min-width: 260px;max-width: 420px;" rowspan="2">评估内容</th>
            <th style="min-width: 410px;max-width: 600px;" rowspan="2">评分标准</th>
            <th style="min-width: 244px;max-width: 367px;" rowspan="2">现场评估方式</th>
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
        <tr style="height:50px">
            <td style="height: 438px;" rowspan="10">1.基本条件<br>（15分）</td>
            <td rowspan="2">1.1 医院情况 （5 分）</td>
            <td>
                1.1.1 基地基本条件★
            </td>
            <td>培训基地和专业基地基本条件与建设</td>
            <td >
                培训基地及所有专业基地基本条件均符合《住院医师规范化培训基地标准（2022 年版）》（以下简称标准），得 3 分；培训基地医院层面基本条件或有 1 个专业基地基本条件不符合标准要求，不得分，不符合要求的专业基地撤销资格 <br><br>
                培训基地有 2 个及以上专业基地基本条件不符合标准要求，培训基地限期整改且不符合要求的专业基地撤销其专业基地资格 <br><br>
                注：本条所指的专业基地基本条件不符合标准要求的认定原则是：1. 科室规模不达标；2.专业基地收治的疾病种类和数量未满足本专业基地细则要求的 75%；3.专业基地收治的疾病种类和数量满足本专业基地细则要求的 75%但未达到 100%，且未按需设置协同单位的；4.专业基地收治的疾病种类和数量满足本专业基地细则要求的 75%但未达到100%，按需设置协同单位，但总病例病种数仍未达标的；5.按需设置的基层医疗卫生机构和专业公共卫生机构为全科专业住院医师规范化培训基地的重要组成部分，不计入协同单位数量。 <br><br>
                如果专业基地在收治的疾病种类和数量满足本专业基地细则要求的75%但未达到 100%基础上，按需设置了协同单位，总的病例病种数符合标准要求，则不扣分
            </td>
            <td >
                现场查看
            </td>
            <td style="text-align: center;">3</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,3,0);" itemId="1"  name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['1']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['1']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                   <textarea onchange="saveSpeReason(this);" itemId="1"  name="reason"
                             placeholder="请填写详细情况" style="width: 98%;height: 98%;" class="text-input validate[required,maxSize[1000]]">${contentMap['1']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['1']}
                </c:if>
            </td>
            <td name="file" itemId="1"></td>
        </tr>
        <tr style="height:50px">
            <td>
                1.1.2 协同单位建设★
            </td>
            <td>培训基地对协同单位的管理与指导</td>
            <td >
                1. 按需设置协同单位且签订协同培训协议，明确培训基地与协同单位职责任务，培训基地承担主体责任，实行分级管理，协同单位按规定的培训专业、培训内容和培训时间开展培训活动，得 1 分 <br>
                2. 培训基地对协同单位每年开展不少于 1 次的定期督导，得 0.5 分；未开展，不得分 <br>
                3. 专业基地对协同科室每季度开展不少于 1 次的定期指导且认真规范，得 0.5 分；每年组织 2 次及以下，不得分 <br>
                4. 培训基地非必需设置了协同单位的，或未经省级卫生健康管理部门同意，跨地域（指培训基地所在地市）设置协同单位，或协同单位超出约定内容与时间开展培训的，不得分  <br>
                协同单位存在独立招收、独立培训住院医师的，撤销培训基地资格。培训基地基本条件满足培训需要，未设置协同单位的，此项不扣分
            </td>
            <td>
                1.现场查看 <br>
                2.访谈培训基地管理人员、指导医师和住院医师
            </td>
            <td style="text-align: center;">2</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,2,0);" itemId="2"  name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['2']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['2']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="2"  name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['2']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['2']}
                </c:if>
            </td>
            <td name="file" itemId="2"></td>
        </tr>
        <tr style="height:50px">
            <td rowspan="2">1.2培训设施及信息系统（2 分）</td>
            <td>
                1.2.1 培训设施
            </td>
            <td>示教室、图书馆（含电子图书）及文献检索系统（含手机端）</td>
            <td >
                培训基地有满足培训需求的示教室、图书馆（或阅览室）、文献检索系统（含手机端），得 1 分；没有或不能满足培训需求，不得分
            </td>
            <td rowspan="2">
                1.现场查看 <br>
                2.查看信息管理平台使用情况
            </td>
            <td style="text-align: center;">1</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,1);" itemId="3" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['3']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['3']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="3" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['3']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['3']}
                </c:if>
            </td>
            <td name="file" itemId="3"></td>
        </tr>
        <tr style="height:50px">
            <td>
                1.2.2 信息系统
            </td>
            <td>网络信息管理平台</td>
            <td >
                有用于住培管理的网络信息管理平台，且平台能满足培训需求，并能溯源病例信息的真实性，得 1 分；有平台且能满足培训需求，得 0.5分；无平台，或平台不能满足培训所需，不得分
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
                              placeholder="请填写详细情况" style="width: 98%"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['4']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['4']}
                </c:if>
            </td>
            <td name="file" itemId="4"></td>
        </tr>
        <tr style="height:50px">
            <td rowspan="3">
                1.3 临床技能培训中心（3 分）
            </td>
            <td>
                1.3.1 设施设备
            </td>
            <td>建筑面积与训练设备配备</td>
            <td >
                面积不小于 600 平方米（专科医院符合培训基地标准的相关要求），且设施设备满足培训需求，得 1 分；不符合标准或不满足培训需求，不得分
            </td>
            <td rowspan="3">
                1.现场查看 <br>
                2.访谈3名以上住院医师
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
                    <textarea onchange="saveSpeReason(this);" itemId="5"  name="reason"
                              placeholder="请填写详细情况" style="width: 98%"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['5']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['5']}
                </c:if>
            </td>
            <td name="file" itemId="5"></td>
        </tr>

        <tr style="height:50px">
            <td>
                1.3.2 人员配备
            </td>
            <td>专职管理人员与带教老师</td>
            <td>
                有专职管理人员、有对应专业的带教老师，且通过相关的专业培训，满足培训需求，得 1 分；仅有专人管理或仅有对应专业的带教老师，通过相关的专业培训，满足部分培训需求，得 0.5 分；如无专职管理人员、带教老师未经培训等不得分
            </td>
            <td style="text-align: center;">1</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,1);" itemId="6"  name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['6']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['6']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="6"  name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['6']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['6']}
                </c:if>
            </td>
            <td name="file" itemId="6"></td>
        </tr>
        <tr style="height:50px">
            <td>
                1.3.3 管理情况
            </td>
            <td>管理规章制度、培训计划与工作实施</td>
            <td>
                有培训管理规章制度，培训计划体现分层分级、符合专业特点，且有效落实，得 1 分；有培训管理规章制度、培训计划，但计划制定不科学或未按计划实施，得 0.5 分；培训管理规章制度不健全，无培训计划或未落实，不得分
            </td>
            <td style="text-align: center;">1</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,1);" itemId="7"  name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['7']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['7']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="7"  name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['7']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['7']}
                </c:if>
            </td>
            <td name="file" itemId="7"></td>
        </tr>
        <tr style="height:50px">
            <td rowspan="2">
                1.4 全科医学科（4 分）
            </td>
            <td>
                1.4.1 学科设置和运行★
            </td>
            <td>综合医院全科医学科设置与工作开展</td>
            <td>
                1.全科医学科独立设置，且有符合教学要求的全科门诊、全科病房，并有效运行，得 2 分；缺 1 项，不得分或近 3 年接受过国家级评估其结论为合格或基本合格者，得 2 分；限期整改者，不得分 <br>
                2.综合医院未按要求独立设置全科医学科，撤销培训基地资格
            </td>
            <td>
                1.现场查看 <br>
                2.非综合医院设置全科专业基地的，参考本指标评估；未设置全科专业基地的，不评估此项指标，此项不扣分
            </td>
            <td style="text-align: center;">2</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,2,0);" itemId="8"  name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['8']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['8']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="8"  name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['8']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['8']}
                </c:if>
            </td>
            <td name="file" itemId="8"></td>
        </tr>
        <tr style="height:50px">
            <td>
                1.4.2 基层实践基地
            </td>
            <td>基本条件与管理</td>
            <td>
                基层实践基地基本条件符合住院医师规范化培训基地标准，并与临床基地签订培训协议，得 2 分；有 1 项不符合，不得分
            </td>
            <td>
                现场查看
            </td>
            <td style="text-align: center;">2</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,2);" itemId="9"  name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['9']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['9']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="9"  name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['9']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['9']}
                </c:if>
            </td>
            <td name="file" itemId="9"></td>
        </tr>
        <tr style="height:50px">
            <td>
                1.5 教学资源建设（1 分）
            </td>
            <td>
                1.5.1 教学门诊建设
            </td>
            <td>教学门诊设置</td>
            <td>
                综合医院设置除全科外的其他专业教学门诊的，每个专业得 0.5 分，最多得 1 分；全科未设置不得分。 <br>
                专科医院设置本专科领域的教学门诊，得 1 分，未设置不得分
            </td>
            <td>
                现场查看
            </td>
            <td style="text-align: center;">1</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,1);" itemId="10"  name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['10']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['10']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="10" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['10']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['10']}
                </c:if>
            </td>
            <td name="file" itemId="10"></td>
        </tr>
        <tr style="height:50px">
            <td rowspan="9">2.培训管理（25 分）</td>
            <td rowspan="3">
                2.1培训体系（7 分）
            </td>
            <td>
                2.1.1 培训基地★
            </td>
            <td>组织管理</td>
            <td>
                1.落实主要领导负责制，培训基地主要负责人每年至少主持召开 2 次专题会议，了解本基地住培制度运行基本情况，及时研究并有效解决住培工作相关问题，得 2 分；主要负责人对本基地住培工作不熟悉、不了解，未能及时研究解决基地住培实施工作中的相关问题，不得分 <br>
                2.建立住培工作组织协调机制且有效开展工作，得 1 分；未建立相关工作机制或落实不到位，不得分
            </td>
            <td>
                1.现场查看 <br>
                2.访谈医院主要负责同志、职能管理部门人员、师资和住院医师
            </td>
            <td style="text-align: center;">3</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,3,0);" itemId="11"  name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['11']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['11']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="11"  name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['11']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['11']}
                </c:if>
            </td>
            <td name="file" itemId="11"></td>
        </tr>
        <tr style="height:50px">
            <td>
                2.1.2 职能管理部门
            </td>
            <td>住培职能管理部门设置与协调工作落实</td>
            <td>
                1.有住培职能管理部门，且职责明确，与其他相关职能部门密切协作，共同落实好所有培训对象的住培管理责任，得 1 分；没有住培职能管理部门，或住培职责不明确，或未有效发挥作用，不得分 <br>
                2.培训基地有至少 3 名胜任岗位的住培专职管理人员；300 人≤在培人数（含全日制临床医学、口腔医学硕士专业学位研究生，下同）≤500人的，应当按照在培人数与专职管理人员 100：1 的比例，配齐配强住培专职管理人员；在培人数>500 人的视情增配专职人员，满足培训所需。达到上述要求，得 1 分；达不到上述要求，不得分
            </td>
            <td>
                1.现场查看 <br>
                2.访谈职能部门和党政办公室、医务、财务、人事等部门管理人员
            </td>
            <td style="text-align: center;">2</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,2);" itemId="12"  name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['12']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['12']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="12"  name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['12']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['12']}
                </c:if>
            </td>
            <td name="file" itemId="12"></td>
        </tr>

        <tr style="height:50px">
            <td>
                2.1.3 专业基地
            </td>
            <td>人员设置与组织管理</td>
            <td>
                专业基地设置本专业基地主任、教学主任、教学秘书和教学小组，轮转科室设置教学主任、教学秘书，组织健全，职责明确，并有效发挥作用，得 2 分；组织不健全或职责不明确或职责明确但未有效发挥作用，不得分
            </td>
            <td>
                1.现场查看 <br>
                2.访谈基地主任、教学主任、指导医师和住院医师
            </td>
            <td style="text-align: center;">2</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,2);" itemId="13"  name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['13']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['13']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="13"  name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['13']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['13']}
                </c:if>
            </td>
            <td name="file" itemId="13"></td>
        </tr>
        <tr style="height:50px">
            <td rowspan="6">2.2 过程管理（18 分）</td>
            <td>
                2.2.1 招收管理★
            </td>
            <td>招收实施及完成情况</td>
            <td>
                1.各专业基地容量测算符合要求，得 1 分；1 个专业基地不符合要求，得 0.5 分；2 个及以上专业基地不符合要求，不得分 <br>
                2.各专业基地近 3 年招收培训对象数量之和均不低于最小培训容量、不超过培训容量，且近 3 年均无零招收现象，得 2 分有一个专业基地出现近 3 年招收培训对象数量之和低于最小培训容量，扣 0.5 分/专业基地；如发现一个专业基地超容量招收，此项不得分；有一个专业基地近 3 年出现零招收情况，扣 1 分/专业基地，扣完为止；近 3 年均为零招收的专业基地（含全科基地）撤销基地资格 <br>
                培训基地为综合医院的或非综合医院设置了全科专业基地的，其全科专业基地近 3 年招收培训对象之和低于最小培训容量，此项不得分 <br>
                3.近 3 年完成紧缺专业招收任务，得 1 分；缺一个扣 0.5 分，扣完为止 <br>
                4.招收外单位委托培养住院医师和面向社会招收的住院医师占比≥40%，得 1 分；20%≤占比＜40%，得 0.5 分；占比＜20%，不得分
            </td>
            <td>
                1.现场查看 <br>
                2.访谈住院医师
            </td>
            <td style="text-align: center;">5</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,5,0);" itemId="14"  name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['14']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['14']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="14"  name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['14']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['14']}
                </c:if>
            </td>
            <td name="file" itemId="14"></td>
        </tr>
        <tr style="height:50px">
            <td>
                2.2.2 入院、入专业基地和入轮转科室教育和临床实践教学活动★
            </td>
            <td>内容与落实</td>
            <td>
                1.入院教育、入专业基地教育、入轮转科室教育规范实施，符合指南要求，且有专人严格组织实施，得 1 分；不规范实施或未实施，不得分 <br>
                2.各专业基地教学查房、临床小讲课、教学病例讨论、门诊教学等教学活动规范开展，符合指南要求，且有专人严格组织实施，得 1 分。不规范实施或未实施，不得分 <br>
                3.将思政教育融入到住院医师的临床实践、教学活动中，有明确要求并有效落实，得 1 分；无要求或未有效落实，不得分
            </td>
            <td>
                1.现场查看及观摩教学活动 <br>
                2.访谈基地管理人员、指导医师和住院医师
            </td>
            <td style="text-align: center;">3</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,3,0);" itemId="15" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['15']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['15']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="15" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['15']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['15']}
                </c:if>
            </td>
            <td name="file" itemId="15"></td>
        </tr>
        <tr style="height:50px">
            <td>
                2.2.3 轮转管理★
            </td>
            <td>轮转方案和计划制定与执行</td>
            <td>
                1.根据标准要求，职能管理部门会同专业基地制定科学合理的轮转方案，体现岗位胜任、分层递进的培训理念，得 1 分；无方案或方案未体现岗位胜任、分层递进的培训理念，不得分 <br>
                2.专业基地根据轮转方案要求制定轮转计划，且严格落实，得 1 分；轮转计划严格落实，但不符合轮转方案要求，得 0.5 分；抽查或访谈中发现没有严格执行轮转计划的，不得分，该住院医师所在的专业基地限期整改
            </td>
            <td>
                1.现场核对在培人员轮转情况 <br>
                2.访谈管理人员、指导医师和住院医师
            </td>
            <td style="text-align: center;">2</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,2,0);" itemId="16" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['16']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['16']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="16"  name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['16']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['16']}
                </c:if>
            </td>
            <td name="file" itemId="16"></td>
        </tr>
        <tr style="height:50px">
            <td>
                2.2.4 考核管理★
            </td>
            <td>过程考核制度与落实</td>
            <td>
                1.院级有考核管理规定，内容包括医德医风、临床职业素养、出勤情况、临床实践能力、培训内容与要求完成情况和参加业务学习情况等内容并规范落实，同时开展了形成性评价，得 1.5 分；有管理规定且规范落实，但未开展形成性评价得 1 分；无管理规定或未规范落实，不得分 <br>
                2.专业基地有出科考核实施细则，且规范落实。出科考核体现专业特点和岗位胜任、分层递进的培训理念，并落实出科考核结果由专业基地教学小组审核和组长签字，得 1.5 分；未落实或不规范或不体现专业特点或不体现岗位胜任、分层递进的培训理念，不得分 <br>
                3.年度考核组织规范，体现岗位胜任、分层递进的培训理念，得 1 分；未落实或不规范或不体现岗位胜任、分层递进的培训理念，不得分 <br>
                4.规范组织符合条件的住院医师参加全国年度业务水平测试，参考率达到 100%，且把测试结果有效用于质量改进的，得 1 分；否则不得分
            </td>
            <td>
                现场查看
            </td>
            <td style="text-align: center;">5</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,5,0);" itemId="17"  name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['17']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['17']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="17" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['17']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['17']}
                </c:if>
            </td>
            <td name="file" itemId="17"></td>
        </tr>
        <tr style="height:50px">
            <td>
                2.2.5 院级督导★
            </td>
            <td>制度与实施</td>
            <td>
                有院级督导制度，每年至少有效开展 1 次院级督导，每次督导有目标、有组织、有内容，有总结，有反馈及整改措施，督导后及时整改，年度内实现所有专业基地全覆盖（含协同单位），得 2 分；督导未实现全覆盖，或反馈的问题未及时整改，不得分
            </td>
            <td>
                1.现场查看
                2.访谈专业基地主任、教学主任、指导医师和住院医师
            </td>
            <td style="text-align: center;">2</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,2,0);" itemId="18" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['18']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['18']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="18"  name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['18']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['18']}
                </c:if>
            </td>
            <td name="file" itemId="18"></td>
        </tr>
        <tr style="height:50px">
            <td>
                2.2.6 沟通反馈
            </td>
            <td>顺畅性与实用性</td>
            <td>
                建立住院医师表达诉求的有效渠道，有沟通反馈机制及应急处理预案，能及时掌握住院医师和指导医师的意见建议及相关情况，相关记录完整，且能有效反馈和解决具体问题，得 1 分；无沟通反馈机制或沟通不畅，不得分
            </td>
            <td>
                访谈职能部门人员、指导医师和住院医师
            </td>
            <td style="text-align: center;">1</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,1);" itemId="19" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['19']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['19']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="19"  name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['19']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['19']}
                </c:if>
            </td>
            <td name="file" itemId="19"></td>
        </tr>

        <tr style="height:50px">
            <td rowspan="6">3.师资管理 （10 分）</td>
            <td rowspan="2">
                3.1 管理规定（3 分）
            </td>
            <td>3.1.1 带教师资管理</td>
            <td>
                管理机制运行
            </td>
            <td>
                1.科学制定带教师资管理制度，有遴选、培训、聘任、考核、激励和退出机制，得 1 分 <br>
                2.在轮转科室按本专业细则规定为每位住院医师配备师资，得 1 分未按要求配备师资，不得分
            </td>
            <td>
                1.现场查看 <br>
                2.访谈指导医师和住院医师
            </td>
            <td style="text-align: center;">2</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,2);" itemId="20" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['20']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['20']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="20" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['20']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['20']}
                </c:if>
            </td>
            <td name="file" itemId="20"></td>
        </tr>

        <tr style="height:50px">
            <td>3.1.2 导师管理</td>
            <td>
                管理机制运行
            </td>
            <td>
                科学制定住培导师管理规定，有遴选、培训、聘任、考核、激励和退出机制，为每位住院医师配置 1 名固定的导师，负责对住院医师培训期间的全程指导和管理，并联系紧密，得 1 分；无管理规定或未有效落实，不得分
            </td>
            <td>
                1.现场查看 <br>
                2.访谈住培导师和住院医师
            </td>
            <td style="text-align: center;">1</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,1);" itemId="21" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['21']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['21']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="21"  name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['21']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['21']}
                </c:if>
            </td>
            <td name="file" itemId="21"></td>
        </tr>
        <tr style="height:50px">
            <td rowspan="2">3.2 师资培训（2 分）</td>
            <td>3.2.1 院级培训</td>
            <td>
                师资参加院级培训
            </td>
            <td>
                1.科学合理制定年度师资培训计划，并严格落实，得 0.5 分 <br>
                2.指导医师上岗前需参加院级师资上岗培训，培训率 100%，持有效期内师资证上岗，并不断接受教学能力提升的继续教育，得 0.5 分不符合上述要求，不得分
            </td>
            <td>
                1.现场查看 <br>
                2.访谈专业基地管理人员和指导医师
            </td>
            <td style="text-align: center;">1</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,1);" itemId="22"  name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['22']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['22']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="22"  name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['22']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['22']}
                </c:if>
            </td>
            <td name="file" itemId="22"></td>
        </tr>
        <tr style="height:50px">

            <td>3.2.2 省级及以上培训</td>
            <td>
                师资参加省级及以上培训
            </td>
            <td>
                近 5 年内，每个专业基地负责人、教学主任、教学秘书和每个轮转科室 1 名以上骨干指导医师经过省级及以上的师资培训，得 1 分；有 1个轮转科室少于 1 名，得 0.5 分；有 2 个及以上轮转科室少于 1 名，不得分
            </td>
            <td>
                1.现场查看 <br>
                2.访谈专业基地管理人员和指导医师
            </td>
            <td style="text-align: center;">1</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,1);" itemId="23"  name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['23']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['23']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="23"  name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['23']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['23']}
                </c:if>
            </td>
            <td name="file" itemId="23"></td>
        </tr>
        <tr style="height:50px">
            <td>3.3 师资评价（1 分）</td>
            <td>3.3.1 带教评价</td>
            <td>
                对指导医师的评价机制
            </td>
            <td>
                建立住院医师对指导医师评价机制，指标设置科学，能反映指导医师的带教意识、能力、作风和效果，评价结果真实客观，有反馈和整改措施，且将测评结果纳入指导医师总体评价，得 1 分；有评价，有整改，但未将测评结果纳入指导医师总体评价，得 0.5 分；仅有评价或未开展评价，不得分
            </td>
            <td>
                1.现场查看 <br>
                2.访谈指导医师和住院医师
            </td>
            <td style="text-align: center;">1</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,1);" itemId="24"  name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['24']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['24']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="24"  name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['24']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['24']}
                </c:if>
            </td>
            <td name="file" itemId="24"></td>
        </tr>
        <tr style="height:50px">
            <td>3.4 激励机制（4 分）</td>
            <td>3.4.1 教学实践活动考核与激励★</td>
            <td>
                教学实践活动与绩效考核、职称晋升
            </td>
            <td>
                1.建立教学实践活动绩效管理制度，培训基地将教学实践活动与各专业基地或轮转科室绩效考核挂钩，且绩效考核不低于考核总分的 8%，且各专业基地或轮转科室二次分配中将专业基地负责人、教学主任、教学秘书的教学管理活动和指导医师的带教活动纳入个人绩效考核范围，得 3 分；绩效考核占考核总分的 5%～8%，得 1 分 <br>
                2.考核结果与专业技术职务晋升挂钩（包含评审或聘任），得 1 分 <br>
                3.绩效考核占考核总分低于 5%或不纳入，或与晋升不挂钩或与晋升挂钩但激励力度过弱，不得分
            </td>
            <td>
                1.现场查看 <br>
                2.访谈专业基地管理人员、指导医师
            </td>
            <td style="text-align: center;">4</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,4,0);" itemId="25"  name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['25']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['25']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="25"  name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['25']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['25']}
                </c:if>
            </td>
            <td name="file" itemId="25"></td>
        </tr>
        <tr style="height:50px">
            <td rowspan="6">4.培训质量 （30 分）</td>
            <td rowspan="2">4.1 培训通过率（8 分）</td>
            <td>4.1.1 结业考核★</td>
            <td>
                近三年本培训基地住院医师首次参加结业理论考核的通过率
            </td>
            <td>
                1.近三年本培训基地住院医师首次参加结业理论考核的通过率≥近三年全国住院医师首次参加结业理论考核平均通过率，得 4分；低于近三年全国住院医师首次参加结业理论考核平均通过率，每降低 1 个百分点，扣 0.5 分；低于近三年全国住院医师首次参加结业理论考核平均通过率 5 个百分点以上，不得分 <br>
                2.近三年本培训基地住院医师首次参加住培结业理论考核通过率排序位于本地区（指东、中、西部地区）前 20%（含），得 2分；位于前 40%（含），得 1.5 分；位于前 60%（含），得 1 分；位于前 80%（含），得 0.5 分；位于 80%以下不得分 <br>
                （近三年本培训基地住院医师首次参加结业理论考核的通过率=近三年首次参加结业理论考核通过的人数/近三年首次参加结业理论考核总人数）
            </td>
            <td>
                来源于国家卫生健康委人才交流服务中心
            </td>
            <td style="text-align: center;">6</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,6,0);" itemId="26"  name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['26']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['26']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="26"  name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['26']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['26']}
                </c:if>
            </td>
            <td name="file" itemId="26"></td>
        </tr>
        <tr style="height:50px">
            <td>4.1.2 执业医师资格考试</td>
            <td>
                近三年本培训基地住院医师首次参加执业医师资格考试的通过率
            </td>
            <td>
                近三年本培训基地住院医师首次参加执业医师资格考试的通过率≥近三年全国住院医师首次参加执业医师资格考试的平均通过率，得 2 分；低于近三年全国住院医师首次参加执业医师资格考试的平均通过率，每降低 1 个百分点，扣 0.5 分，扣完为止 <br>
                （近三年本培训基地住院医师首次参加执业医师资格考试的通过率=近三年首次参加考试通过的人数/近三年首次参加考试总人数）
            </td>
            <td>
                来源于国家医学考试中心
            </td>
            <td style="text-align: center;">2</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,2);" itemId="27"  name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['27']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['27']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="27"  name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['27']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['27']}
                </c:if>
            </td>
            <td name="file" itemId="27"></td>
        </tr>
        <tr style="height:50px">
            <td>4.2 专业基地培训质量（18 分）</td>
            <td>4.2.1 专业基地现场评估★</td>
            <td>
                专业基地培训质量
            </td>
            <td>
                受评专业基地“培训质量（总分 35 分）”得分的平均得分，平均得分≥32 分，得 18 分；28 分≤平均得分＜32 分，得 14分；25 分≤平均得分＜28 分，得 10 分；21 分≤平均得分＜25分，得 6 分；平均得分＜21 分，不得分
            </td>
            <td>
                计算受评专业基地“培训质量”得分的平均得分，此项得 10分以下，即平均得分＜25 分，为该核心指标不合格
            </td>
            <td style="text-align: center;">18</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,18,0);" itemId="28"  name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['28']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['28']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="28"  name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['28']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['28']}
                </c:if>
            </td>
            <td name="file" itemId="28"></td>
        </tr>

        <tr style="height:50px">
            <td rowspan="3">4.3 综合评价（4 分）</td>
            <td>4.3.1 住院医师多源评价</td>
            <td>
                实施与运用
            </td>
            <td>
                指导医师、科室护士、其他有关专业人员、管理人员等对住院医师实施综合评价，指标设置科学，能反映住院医师的实际表现，且进行有效分析和结果的正确运用，得 1 分；有综合评价，指标设置欠科学，部分指标未能反映住院医师的实际表现，分析和运用不充分，得 0.5 分；未实施综合评价，不得分
            </td>
            <td>
                现场查看
            </td>
            <td style="text-align: center;">1</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,1);" itemId="29"  name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['29']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['29']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="29"  name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['29']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['29']}
                </c:if>
            </td>
            <td name="file" itemId="29"></td>
        </tr>

        <tr style="height:50px">
            <td>4.3.2 住院医师培训质量满意度</td>
            <td>
                住院医师对培训基地的培训质量满意度
            </td>
            <td>
                住院医师对在培期间培训基地的培训质量满意度达到 90%及以上的，得 1 分；80%≤满意度＜90%，得 0.5 分；低于 80%，不得分
            </td>
            <td>
                现场对在培住院医师进行问卷调研
            </td>
            <td style="text-align: center;">1</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,1);" itemId="30"  name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['30']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['30']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="30"  name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['30']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['30']}
                </c:if>
            </td>
            <td name="file" itemId="30"></td>
        </tr>
        <tr style="height:50px">
            <td>4.3.3 培训基地省级年度综合评价</td>
            <td>
                培训基地省级年度综合评价
            </td>
            <td>
                本培训基地上一年度位于省级年度综合评价第一梯队得 2 分，第二梯队得 1.5 分，第三梯队得 1 分，第四梯队不得分
            </td>
            <td>
                查看相关文件
            </td>
            <td style="text-align: center;">2</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,2);" itemId="31"  name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['31']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['31']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="31"  name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['31']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['31']}
                </c:if>
            </td>
            <td name="file" itemId="31"></td>
        </tr>
        <tr style="height:50px">
            <td rowspan="9">5.保障措施（20 分）</td>
            <td>5.1 专项经费（2 分）</td>
            <td>5.1.1 专项管理★</td>
            <td>
                住培经费使用的规范性
            </td>
            <td>
                建立住培经费管理制度，实行专项管理，统筹规范使用中央财政和地方财政补助经费以及培训基地自筹资金，得 2 分；有 1项不符合要求或被省级及以上卫生健康行政部门通报经费使用不规范问题的，不得分
            </td>
            <td>
                查看管理制度、年度预算及使用计划、财务报表经费使用范围的规范性等相关资料
            </td>
            <td style="text-align: center;">2</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,2,0);" itemId="32"  name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['32']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['32']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="32"  name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['32']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['32']}
                </c:if>
            </td>
            <td name="file" itemId="32"></td>
        </tr>
        <tr style="height:50px">
            <td rowspan="5">5.2 住院医师待遇及保障（14 分）</td>
            <td>5.2.1 住院医师补助★</td>
            <td>
                制定住院医师培训期间薪酬待遇或生活补助发放标准，并纳入培训招收简章
            </td>
            <td>
                1.培训基地制定相关办法，保障住院医师合理待遇，综合考虑经济发展、物价变动、所在地城镇职工平均工资等因素，结合实际制定培训对象薪酬待遇发放标准，明确不同资质、不同年资住院医师培训期间的薪酬待遇或生活补助发放标准和考核相关要求，同时对全科、儿科等紧缺专业培训对象的薪酬待遇予以倾斜，得 2 分，否则不得分 <br>
                2.培训基地在招收简章中明确住院医师培训期间待遇，并严格落实执行，得 2 分，否则不得分 <br>
                3.以上要求有一项未落实的，此项不得分，培训基地限期整改（全部为面向社会招收或外单位委托培养住院医师的培训基地，参照对应要求，符合标准，得 4 分）
            </td>
            <td>
                1.查看培训基地住院医师薪酬待遇或生活补助发放标准 <br>
                2.查看对外发布的招收简章 <br>
                3.查看财务、人事部门提供的待遇发放流水单 <br>
                4.抽查3～5名住院医师收入情况
            </td>
            <td style="text-align: center;">4</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,4,0);" itemId="33"  name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['33']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['33']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="33"  name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['33']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['33']}
                </c:if>
            </td>
            <td name="file" itemId="33"></td>
        </tr>

        <tr style="height:50px">
            <td>5.2.2 生活保障</td>
            <td>
                为住院医师提供住宿或住宿补贴
            </td>
            <td>
                为住院医师提供住宿或住宿补贴
            </td>
            <td>
                1.现场查看 <br>
                2.访谈住院医师
            </td>
            <td style="text-align: center;">1</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,1);" itemId="34" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['34']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['34']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="34"  name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['34']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['34']}
                </c:if>
            </td>
            <td name="file" itemId="34"></td>
        </tr>
        <tr style="height:50px">
            <td>5.2.3 签订劳动合同/培训协议，落实社会保障制度</td>
            <td>
                培训基地与面向社会招收住院医师签订劳动合同和缴纳社保；与其他住院医师签订培训协议
            </td>
            <td>
                培训基地与面向社会招收的住院医师签订劳动合同和协助缴纳社会保险（按本单位同类在职人员标准），与其他类型住院医师按规定签订培训协议，保障合理待遇，约定有关事项，切实落实到位，得 1 分；未签订合同或协议，或不协助缴纳社会保险的或不落实到位的，不得分
            </td>
            <td>
                1.查看相关合同、协议原件等 <br>
                2.访谈住院医师
            </td>
            <td style="text-align: center;">1</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,1);" itemId="35" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['35']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['35']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="35"  name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['35']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['35']}
                </c:if>
            </td>
            <td name="file" itemId="35"></td>
        </tr>
        <tr style="height:50px">
            <td>5.2.4 住院医师临床实践保障★</td>
            <td>
                培训基地有明确制度保障住院医师临床实践，包括执业注册、医疗权限、管理床位和值班等，对所有类型住院医师一视同仁，使其享受同等的教学资源和实践机会
            </td>
            <td>
                1.协助住院医师报考执业医师资格考试和办理执业注册或变更注册，落实得 0.5 分；未落实，不得分 <br>
                2.为住院医师开通电子病历系统账号，并赋予其与培训阶段相适应的参与临床实践工作的权限，落实得 0.5 分；未落实，不得分 <br>
                3.住院医师在轮转培训中，管理床位数和门诊接诊数达到规定标准，得 1 分，有 1 名住院医师未达标准扣 0.5 分，扣完为止 <br>
                4.轮转科室应安排住院医师承担与其培训阶段相适应的临床医
                疗责任及值班工作，落实得 1 分；未落实，不得分 <br>
                5.对所有类型住院医师一视同仁，享受同等教学资源和实践机会，落实得 1 分；未落实，不得分 <br>
                6.以上有 2 项及以上工作落实不到位，此项不得分，培训基地限期整改
            </td>
            <td>
                1.访谈住院医师 <br>
                2.查看电子病历系统 <br>
                3.查看轮转计划，查看轮转排班值班
            </td>
            <td style="text-align: center;">4</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,4,0);" itemId="36" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['36']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['36']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="36"  name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['36']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['36']}
                </c:if>
            </td>
            <td name="file" itemId="36"></td>
        </tr>
        <tr style="height:50px">
            <td>5.2.5 落实“两个同等对待”政策★</td>
            <td>
                1. 面向社会招聘的本科学历的住院医师享受“两个同等对待”落实情况
            </td>
            <td>
                1.培训基地网站宣传住培“两个同等对待”政策，得 1 分；否则不得分 <br>
                2.对面向社会招收的普通高校应届毕业生住院医师培训合格当年在医疗卫生机构就业的，在招聘、派遣、落户等方面，按当年应届毕业生同等对待。均已落实得 1.5 分。否则不得分 <br>
                3.对经住培合格的本科学历临床医师，在人员招聘、职称晋升、岗位聘用、薪酬待遇等方面，与临床医学专业学位硕士研究生同等对待。均已落实得 1.5 分，否则不得分 <br>
                4.第一项工作要求未落实的，此项不得分
            </td>
            <td>
                1.查看医院官方网站 <br>
                2.查看医院本年度招聘简章、劳动合同等相关资料 <br>
                3.访谈住院医师
            </td>
            <td style="text-align: center;">4</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,4,0);" itemId="37" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['37']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['37']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="37"  name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['37']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['37']}
                </c:if>
            </td>
            <td name="file" itemId="37"></td>
        </tr>
        <tr style="height:50px">
            <td rowspan="2">5.3 激励机制（3 分）</td>
            <td>5.3.1 专业基地绩效考核</td>
            <td>
                与招收完成、过程考核和结业考核挂钩及落实
            </td>
            <td>
                将住培招收完成率、教学带教工作量、首次执业医师资格考试通过率和结业考核通过率等在内的培训质量和效果，与专业基地年度综合目标绩效考核紧密挂钩，挂钩比例高于年度综合目标绩效考核总分的 10%，且严格有效落实，得 2 分；有挂钩且落实，但挂钩比例少于年度综合目标绩效考核总分的10%，得 1 分；无挂钩，或未落实，不得分
            </td>
            <td>
                1.现场查看 <br>
                2.访谈专业基地管理人员、指导医师
            </td>
            <td style="text-align: center;">2</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,2);" itemId="38" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['38']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['38']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="38"  name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['38']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['38']}
                </c:if>
            </td>
            <td name="file" itemId="38"></td>
        </tr>
        <tr style="height:50px">
            <td>5.3.2 评优树先</td>
            <td>
                对指导医师和住院医师开展评优树先活动
            </td>
            <td>
                1.积极开展评优树先活动，对优秀的指导医师予以奖励，提高指导医师教学工作积极性，得 0.5 分；未开展，不得分 <br>
                2.积极开展评优树先活动，对优秀的住院医师予以奖励，提高住院医师培训学习积极性，得 0.5 分；未开展，不得分
            </td>
            <td>
                1.查看相关制度、实施等原始资料 <br>
                2.访谈 2～3 名指导医师和住院医师
            </td>
            <td style="text-align: center;">1</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,1);" itemId="39" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['39']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['39']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="39"  name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['39']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['39']}
                </c:if>
            </td>
            <td name="file" itemId="39"></td>
        </tr>

        <tr style="height:50px">
            <td>5.4 其他（1 分）</td>
            <td>5.4.1 住培宣传</td>
            <td>
                工作开展
            </td>
            <td>
                1.有宣传工作制度、通讯员，每年在省级及以上主流媒体至少发表 2 篇宣传稿件，得 0.5 分；没有，不得分 <br>
                2.培训基地网站有住培专栏，宣传介绍国家、地方及基地医院住培有关政策制度和工作进展与成效，并及时更新，得 0.5分；没有，不得分；
            </td>
            <td>
                查看相关制度、稿件发表记录等资料
            </td>
            <td style="text-align: center;">1</td>
            <td  style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,1);" itemId="40" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['40']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['40']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="40"  name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['40']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['40']}
                </c:if>
            </td>
            <td name="file" itemId="40"></td>
        </tr>
        <tr style="height:50px">
            <td colspan="6" style="text-align: right">合计</td>
            <td style="text-align: center">100</td>
            <td style="text-align: center" id="localAll"></td>
            <td></td>
            <td></td>
        </tr>
    </table>
    <div style="margin-top: 20px;">
        备注：<br>
        1.一级指标 5 项，二级指标 18 项，三级指标 40 个，共 100 分，其中核心指标 16 个，共 69 分。 <br>
        2.单个核心指标达标判定标准：单个核心指标得分率≥70%为达标，＜70%为不达标。其中，单项指标满分为 3 分的，若评估得 2 分的则判定为达标。<br>
        3.培训基地评估结论判定标准：（1）合格：评估分值≥80 分，且核心指标达标数≥13 个。（2）基本合格：70 分≤评估分值＜80 分，且核心指标达标数≥10 个。（3）限期整改（黄牌）：60 分≤评估分值＜70 分，或 6 个≤核心指标达标数≤9 个。（4）撤销资格（红牌）：评估分值＜60 分，或核心指标达标数≤5 个，或出现下列情况之一者：①拒不接受评估或评估整改的；②培训基地评估结果为限期整改的，整改期满复评仍为不合格的；③在同一年度内接受综合评估、专业评估、专项评估和飞行检查中，累计有 3 个及以上专业基地评估结果为限期整改时，整改期满复评仍不合格的；④综合医院未按要求独立设置全科医学科，撤销培训基地资格；⑤协同单位存在独立招收、独立培训住院医师的培训基地撤销资格。<br>
        4.紧缺专业是指全科、儿科、精神科、妇产科、麻醉科、急诊科、临床病理科、重症医学科。<br>
        5.分层递进的培训理念，是指根据培养对象的不同培训年限和能力素质要求，设置并实施递进上升、顶岗负责、符合岗位胜任的阶段性培训目标，以达到培养独立、规范地承担相关专业常见病多发病诊疗工作的合格临床医师的总目标。<br>
        6.培训基地聘用委培单位服务期内或违约农村订单定向免费培养医学毕业生的，以及招收违约农村订单定向免费培养医学毕业生参加全科专业以外住培的，每聘用或招收 1 名扣 10 分。<br>
        7.在培住院医师均含在读临床医学、口腔医学专业学位硕士研究生数。<br>
        8.培训基地应确保所提供的材料真实可靠，对于弄虚作假者，一经查实，将提请当地省级卫生健康行政部门暂停其住培招收资格，情节严重的，撤销培训基地资格。
    </div>
    <div style="margin-top: 30px;">
        <div style="margin-right: 2%;">专家签字：

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