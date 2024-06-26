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

        function saveScore4Expert(expl, num, coreIndicators) {
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
                if (null != coreIndicators && coreIndicators != undefined && coreIndicators == '0') {
                    //核心指标
                    if (score1 < num * 0.7) {
                        coreIndicators = 1;
                    }
                    if (num == 3 && score1 < 2) {
                        coreIndicators = 1;
                    } else {
                        coreIndicators = 0;
                    }
                } else {
                    coreIndicators = 2;
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
            if (${type eq 'Y'}){
                var itemIdList = $("input");
                for (var i = 0; i < itemIdList.length; i++) {
                    if (itemIdList[i].getAttribute("name") == "local") {
                        localTotalled = Number(localTotalled) + Number(itemIdList[i].value);
                    }
                    if (itemIdList[i].getAttribute("name") == "local1") {
                        localScore = Number(localScore) + Number(itemIdList[i].value);
                    }
                }
            }else {
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

        function scheduleMany(fileRoute, fileName) {
            var url = "<s:url value ='/jsres/supervisio/showAssessmentMany'/>?type=${type}&recordFlow=${assessment.recordFlow}&orgFlow=${assessment.orgFlow}&speId=${assessment.speId}&fileRoute=" + fileRoute;
            top.jboxOpen(url, fileName, 1100, 750);
        }

        function scheduleOne(fileRoute, fileName) {
            var url = "<s:url value ='/jsres/supervisio/showAssessment'/>?type=${type}&cfgFlow=${assessment.recordFlow}&orgFlow=${assessment.orgFlow}&&speId=${assessment.speId}&fileRoute=" + fileRoute;
            top.jboxOpen(url, fileName, 1100, 690);
        }
    </script>
</head>
<body>
<div id="tableContext" style="overflow-y: auto;margin-left: 10px;margin-right: 10px;height: 740px;" onscroll="tableFixed(this);">
    <div id="dateFixed" style="height: 0px;overflow: visible;position: relative;width: 100%;">
        <table border="0" cellpadding="0" cellspacing="0" class="grid" style="width: 99%" >
            <tr height="34" class="firstRow">
                <th colspan="10">
                    <h2 style="font-size:150%">住院医师规范化培训专业基地评估指标 ——口腔正畸科（2023年版）</h2>
                </th>
            </tr>
            <tr height="28">
                <th style="text-align:left;padding-left: 4px;" height="28" colspan="4">培训基地（医院）名称：${orgName}</th>
                <th style="text-align:left;padding-left: 4px;" height="28" colspan="6">所属省（区、市）：${cityName}</th>
            </tr>
            <tr style="height:16px;">
                <th style="min-width: 360px;max-width: 360px;height: 16px;" colspan="3">评估项目</th>
                <th style="min-width: 260px;max-width: 420px;" rowspan="2">评估内容</th>
                <th style="min-width: 344px;max-width: 367px;" rowspan="2">现场评估方式</th>
                <th style="min-width: 310px;max-width: 470px;" rowspan="2">评分标准</th>
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
                <h2 style="font-size:150%">住院医师规范化培训专业基地评估指标 ——口腔正畸科（2023年版）</h2>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" height="28" colspan="4">培训基地（医院）名称：${orgName}</th>
            <th style="text-align:left;padding-left: 4px;" height="28" colspan="6">所属省（区、市）：${cityName}</th>
        </tr>
        <tr style="height:16px;">
            <th style="min-width: 360px;max-width: 360px;height: 16px;" colspan="3">评估项目</th>
            <th style="min-width: 260px;max-width: 420px;" rowspan="2">评估内容</th>
            <th style="min-width: 344px;max-width: 367px;" rowspan="2">现场评估方式</th>
            <th style="min-width: 310px;max-width: 470px;" rowspan="2">评分标准</th>
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
            <td style="height: 438px;" rowspan="9">1.基本条件<br>（15分）</td>
            <td rowspan="8">
                1.1专业基地<br>
                相关医疗和<br>
                设备条件
            </td>
            <td>
                1.1.1医院性质
            </td>
            <td>
                设有口腔正畸科的口腔专科医院
            </td>
            <td >
                现场查看
            </td>
            <td >
                符合标准，得满分<br>
                不达标准，不得分
            </td>

            <td style="text-align: center;width: 55px">1</td>
            <td width="56px" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,1);" itemId="1" itemName="医院性质" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['1']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['1']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                   <textarea onchange="saveSpeReason(this);" itemId="1" itemName="医院性质" name="reason"
                             placeholder="请填写详细情况" style="width: 98%;height: 98%" class="text-input validate[required,maxSize[1000]]">${contentMap['1']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['1']}
                </c:if>
            </td>
            <td name="file" itemId="1"></td>
        </tr>
        <tr style="height:50px">
            <td>
                1.1.2牙科综合治疗台数<br>
                ★
            </td>
            <td>
                ≥20台
            </td>
            <td width="20%" rowspan="2">
                检查相关文件复印件，需加盖医院<br>
                公章 ，实地考查
            </td>
            <td width="20%">
                符合标准，得满分<br>
                不达标准，不得分
            </td>

            <td style="text-align: center;">2</td>
            <td width="56px" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,2,0);" itemId="2" itemName="牙科综合治疗台数" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['2']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['2']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="2" itemName="牙科综合治疗台数" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['2']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['2']}
                </c:if>
            </td>
            <td name="file" itemId="2"></td>
        </tr>
        <tr style="height:50px">
            <td>
                1.1.3年门诊量★
            </td>
            <td>
                ≥2500人次
            </td>
            <td width="20%">
                符合标准，得2分<br>
                不达标准，不得分
            </td>

            <td style="text-align: center;">2</td>
            <td width="56px" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,2,0);" itemId="3" itemName="年门诊量" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['3']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['3']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="3" itemName="年门诊量" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%"
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
                1.1.4科室和实验室
            </td>
            <td>
                放射或影像科(综合性医院的放射科具<br>
                备拍摄根尖片、曲面体层片、头颅定位<br>
                侧位片等 X线片能力)，检验科等
            </td>
            <td width="20%">
                现场查看
            </td>
            <td width="20%">
                满足要求，得1分<br>
                缺1项，不得分
            </td>

            <td style="text-align: center;">1</td>
            <td width="56px" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,1);" itemId="4" itemName="科室和实验室" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['4']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['4']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="4" itemName="科室和实验室" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['4']}</textarea>

                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['4']}
                </c:if>
            </td>
            <td name="file" itemId="4"></td>
        </tr>
        <tr style="height:50px">
            <td>
                1.1.5轮转科室（专业）
            </td>
            <td>
                牙体牙髓科、牙周科、口腔颌面外科、<br>
                口腔修复科、口腔预防科、口腔颌面影<br>
                像科、儿童口腔科、口腔正畸科、技工<br>
                室、口腔急诊、口腔颌面外科（选择学<br>
                习正颌、唇腭裂、颞下颌关节专业）、<br>
                机动
            </td>
            <td width="20%">
                1.现场查看<br>
                2.访谈住院医师
            </td>
            <td width="20%">
                科室齐全，得2分<br>
                缺1个科室，扣0.5分，扣完为止
            </td>

            <td style="text-align: center;">2</td>
            <td width="56px" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,2);" itemId="5" itemName="轮转科室（专业）" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['5']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['5']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="5" itemName="轮转科室（专业）" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%"
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
                1.1.6疾病种类及数量★
            </td>
            <td rowspan="2">
                符合《住院医师规范化培训基地标准<br>
                （2022年版）》口腔正畸科专业细则要<br>
                求，详见
                <a href="javascript:void(0)" onclick="scheduleOne('hospSelfAssessment3200_01',' 附件1');">附件1</a>
            </td>
            <td width="20%">
                现场查看
            </td>
            <td width="20%">
                1.专业基地的疾病种类及数量符合标准<br>
                要求，或疾病种类及数量≥规定数的<br>
                75%，且有符合要求的协同单位，总疾<br>
                病种类及数量符合标准要求 ，得满分<br>
                2.其他情况均不得分，专业基地暂停招<br>
                收住院医师
            </td>

            <td style="text-align: center;">2</td>
            <td width="56px" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input id="fubiao11" onchange="saveScore4Expert(this,2,0);" itemId="6" itemName="疾病种类及数量" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['6']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['6']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="6" itemName="疾病种类及数量" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%"
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
                1.1.7技能操作种类及数<br>
                量★
            </td>
            <td width="20%">
                现场查看
            </td>
            <td>
                专业基地的临床技能操作种类及数量符<br>
                合标准要求，得满分，否则，不得分
            </td>

            <td style="text-align: center;">2</td>
            <td width="56px" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,2,0);" itemId="7" itemName="技能操作种类及数量" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center"value="${scoreMap['7']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['7']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="7" itemName="技能操作种类及数量" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['7']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['7']}
                </c:if>
            </td>
            <td name="file" itemId="7"></td>
        </tr>
        <tr style="height:50px">
            <td>
                1.1.8专业基地设备
            </td>
            <td>
                拍摄面颌相片、制取牙模型的相应设<br>
                备;妥善保存上述资料及所有病历记录<br>
                的设备和空间；模型修整、活动矫治器<br>
                打磨，以及点焊机、银焊枪等专用设备<br>
                。每位正畸医师至少配备4套以上正畸<br>
                常用器械，包括针持、细丝弯制钳、细<br>
                丝刻断钳、末端刻断钳等;1套以上正畸<br>
                完整器械包括:转矩钳、刻断钳、尖钳<br>
                、弓丝成形器等。
            </td>
            <td width="20%">
                现场查看
            </td>
            <td>
                满足要求，得1分；缺1项，不得分
            </td>

            <td style="text-align: center;">1</td>
            <td width="56px" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,1);" itemId="8" itemName="专业基地设备" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['8']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['8']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="8" itemName="专业基地设备" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['8']}</textarea>

                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['8']}
                </c:if>
            </td>
            <td name="file" itemId="8"></td>
        </tr>
        <tr style="height:50px">
            <td>1.2协同情况</td>
            <td>
                1.2.1协同单位与科室数<br>
                量、轮转时间
            </td>
            <td>
                协同单位数量不超过3家，协同培训的<br>
                科室（含亚专业）不超过3个，在协同<br>
                培训单位的轮转时间累计不超过 6个月
            </td>
            <td width="20%">
                现场查看
            </td>
            <td>
                完全符合要求，得2分<br>
                1项不符合要求，不得分<br>
                协同单位存在独立招收、独立培训住院<br>
                医师的，此处不得分，撤销培训基地资<br>
                格<br>
                专业基地自身基本条件符合标准要求 ，<br>
                无协同单位，此处不失分
            </td>

            <td style="text-align: center;">2</td>
            <td width="56px" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,2);" itemId="9" itemName="协同单位与科室数量、轮转时间" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['9']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['9']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="9" itemName="协同单位与科室数量、轮转时间" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['9']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['9']}
                </c:if>
            </td>
            <td name="file" itemId="9"></td>
        </tr>
        <tr style="height:50px">
            <td rowspan="9">
                2.师资管<br>
                理<br>
                （15分）
            </td>
            <td rowspan="6">2.1师资情况</td>
            <td>
                2.1.1专职指导医师与住<br>
                院医师比例★
            </td>
            <td>
                基地须安排至少1名指导医师从事专职<br>
                带教(不同时从事临床或管理等工作 )，<br>
                每名专职指导医师同时带教住院医师不<br>
                超过5名
            </td>
            <td width="20%">
                1.现场查看<br>
                2.访谈指导医师和住院医师
            </td>
            <td>
                符合标准，得1分<br>
                不达标准，不得分
            </td>

            <td style="text-align: center;">1</td>
            <td width="56px" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,1,0);" itemId="10" itemName="专职指导医师与住院医师比例" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['10']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['10']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="10" itemName="专职指导医师与住院医师比例" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['10']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['10']}
                </c:if>
            </td>
            <td name="file" itemId="10"></td>
        </tr>
        <tr style="height:50px">
            <td>
                2.1.2基地师资管理配备
            </td>
            <td>
                专业基地应至少配备专业基地负责人 、<br>
                教学主任和教学秘书各1名
            </td>
            <td>
                现场查看
            </td>
            <td>
                达到标准，得满分<br>
                不达标准，不得分
            </td>

            <td style="text-align: center;">1</td>
            <td width="56px" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,1);" itemId="11" itemName="基地师资管理配备" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['11']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['11']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="11" itemName="基地师资管理配备" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%"
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
                2.1.3指导医师组成
            </td>
            <td width="20%">
                专业基地应有在职指导医师总数 ≥<br>
                15人，具有高级专业技术职务人员≥<br>
                30%，高级专业技术职务人员≥3名，正<br>
                高级专业技术职务人员≥1名
            </td>
            <td rowspan="3">
                查看人事部门提供的师资状况统计<br>
                表，包括姓名、毕业时间、毕业学<br>
                校、学历学位、专业技术职务、专<br>
                业技术职务任职时间、工作时间，<br>
                需加盖人事部门公章。
            </td>
            <td>
                达到标准，得满分<br>
                不达标准，不得分
            </td>

            <td style="text-align: center;">1</td>
            <td width="56px" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,1);" itemId="12" itemName="指导医师组成" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['12']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['12']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="12" itemName="指导医师组成" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%"
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
                2.1.4指导医师条件
            </td>
            <td>
                具有口腔医学本科及以上学历 ，高年资<br>
                主治医师及以上专业技术职务 。从事口<br>
                腔正畸专业临床教学工作 ≥8年，并符<br>
                合总则中规定的指导医师条件 。
            </td>
            <td>
                符合标准，得1分<br>
                有1名指导医师不符合要求，不得分
            </td>

            <td style="text-align: center;">1</td>
            <td width="56px" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,1);" itemId="13" itemName="指导医师条件" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['13']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['13']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="13" itemName="指导医师条件" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['13']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['13']}
                </c:if>
            </td>
            <td name="file" itemId="13"></td>
        </tr>
        <tr style="height:50px">
            <td>
                2.1.5专业基地负责人条<br>
                件
            </td>
            <td>
                具有口腔医学本科及以上学历 ，主任医<br>
                师专业技术职务，从事口腔正畸学专业<br>
                临床和教学工作超过15年
            </td>
            <td>
                符合标准，得1分<br>
                有1项不符合条件，不得分
            </td>

            <td style="text-align: center;">1</td>
            <td width="56px" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,1);" itemId="14" itemName="专业基地负责人条件" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center"value="${scoreMap['14']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['14']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="14" itemName="专业基地负责人条件" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%"
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
                2.1.6责任导师制度
            </td>
            <td>
                为每名住院医师配置1名固定的责任指<br>
                导医师作为导师，负责住院医师在培训<br>
                期间的全程指导
            </td>
            <td width="20%">
                1.现场查看<br>
                2.访谈责任导师和住院医师
            </td>
            <td>
                符合标准，得1分<br>
                未配置责任导师或配置但未有效落实 ，<br>
                不得分
            </td>

            <td style="text-align: center;">1</td>
            <td width="56px" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,1);" itemId="15" itemName="责任导师制度" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center"value="${scoreMap['15']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['15']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="15" itemName="责任导师制度" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['15']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['15']}
                </c:if>
            </td>
            <td name="file" itemId="15"></td>
        </tr>
        <tr style="height:50px">
            <td rowspan="3">
                2.2师资建设
            </td>
            <td>
                2.2.1师资培训★
            </td>
            <td>
                指导医师上岗前需参加院级师资上岗培<br>
                训，培训率100%，持有效期内师资证上<br>
                岗，并不断接受教学能力提升的继续教<br>
                育。近5年内，每个专业基地负责人、<br>
                教学主任、教学秘书和每个轮转科室 1<br>
                名以上骨干指导医师经过省级及以上的<br>
                师资培训
            </td>
            <td width="20%">
                1.现场查看<br>
                2.访谈指导医师
            </td>
            <td>
                2项均满足，得3分<br>
                1项满足，得1分<br>
                2项均不满足，不得分
            </td>

            <td style="text-align: center;">3</td>
            <td width="56px" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,3,0);" itemId="16" itemName="师资培训" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['16']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['16']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="16" itemName="师资培训" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%"
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
                2.2.2师资评价
            </td>
            <td>
                每年度至少组织1次对每位指导医师的<br>
                教学工作进行评价
            </td>
            <td width="20%">
                1.现场查看<br>
                2.访谈指导医师和住院医师
            </td>
            <td>
                评价方案全面，原始记录详实，有落实<br>
                、评价结果分析运用、改进，得3分<br>
                评价方案简单，有落实，无结果分析运<br>
                用，得2分<br>
                无方案，但有单一评价记录，得1分<br>
                无方案或有方案无评价实施记录 ，不得<br>
                分
            </td>

            <td style="text-align: center;">3</td>
            <td width="56px" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,3);" itemId="17" itemName="师资评价" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['17']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['17']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                     <textarea onchange="saveSpeReason(this);" itemId="17" itemName="师资评价" name="reason"
                               placeholder="请填写详细情况" style="width: 98%;height: 98%"
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
                2.2.3激励制度★
            </td>
            <td>
                建立带教活动绩效管理制度 ，将带教活<br>
                动与专业基地绩效考核挂钩 ，并在科室<br>
                二次分配中将专业基地负责人 、教学主<br>
                任、教学秘书的教学管理活动和指导医<br>
                师的带教活动，纳入个人绩效考核的重<br>
                要指标范围
            </td>
            <td width="20%">
                1.现场查看<br>
                2.访谈专业基地管理人员、指导医<br>
                师
            </td>
            <td>
                教学绩效考核不低于考核总分的 8%，考<br>
                核结果与专业技术职务晋升挂钩 ，得3<br>
                分<br>
                教学绩效考核占考核总分的 5%～8%之<br>
                间，得2分<br>
                教学绩效考核占考核总分低于 5%或不纳<br>
                入，不得分
            </td>

            <td style="text-align: center;">3</td>
            <td width="56px" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,3,0);" itemId="18" itemName="激励制度" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['18']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['18']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="18" itemName="激励制度" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['18']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['18']}
                </c:if>
            </td>
            <td name="file" itemId="18"></td>
        </tr>
        <tr style="height:50px">
            <td rowspan="15">
                3.培训管<br>
                理<br>
                （35分）
            </td>
            <td rowspan="5">
                3.1制度与落<br>
                实
            </td>
            <td>
                3.1.1专业基地负责人
            </td>
            <td>
                实行专业基地负责人负责制 ，负责协调<br>
                本专业和相关专业的教学资源 ,加强对<br>
                教学与培训人员的组织管理 ,整体把控<br>
                培训质量,对本专业基地(含协同单位)<br>
                的培训质量承担主要责任 ，并切实落实
            </td>
            <td rowspan="4">
                1.现场查看<br>
                2.访谈专业基地管理人员、指导医<br>
                师
            </td>
            <td width="20%">
                职责明确，履职认真，得2分<br>
                无岗位职责，或履职不认真，不得分
            </td>

            <td style="text-align: center;">2</td>
            <td width="56px" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,2);" itemId="19" itemName="专业基地负责人" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['19']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['19']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="19" itemName="专业基地负责人" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['19']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['19']}
                </c:if>
            </td>
            <td name="file" itemId="19"></td>
        </tr>
        <tr style="height:50px">
            <td>
                3.1.2教学主任
            </td>
            <td>
                设置教学主任岗位，负责本专业住院医<br>
                师的轮转计划制订;负责本专业培训的<br>
                全过程管理;定期检查评价住院医师的<br>
                培训质量 和指导医师的带教质量等
            </td>
            <td width="20%">
                职责明确，履职认真，得2分<br>
                无岗位职责，或履职不认真，不得分
            </td>

            <td style="text-align: center;">2</td>
            <td width="56px" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,2);" itemId="20" itemName="教学主任" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['20']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['20']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="20" itemName="教学主任" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['20']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['20']}
                </c:if>
            </td>
            <td name="file" itemId="20"></td>
        </tr>
        <tr style="height:50px">
            <td>
                3.1.3教学秘书
            </td>
            <td>
                设置教学秘书岗位，协助专业基地负责<br>
                人、教学主任开展培训与教学工作 。执<br>
                行专业基地负责人、教学主任布置的各<br>
                项培训工作任务,督促指导医师积极落<br>
                实带教任务等。
            </td>
            <td>
                有教学秘书，履职认真，得2分<br>
                无教学秘书，或履职不认真，不得分
            </td>

            <td style="text-align: center;">2</td>
            <td width="56px" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,2);" itemId="21" itemName="教学秘书" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['21']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['21']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="21" itemName="教学秘书" name="reason"
                              placeholder="请填写详细情况" style="width: 98%"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['21']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['21']}
                </c:if>
            </td>
            <td name="file" itemId="21"></td>
        </tr>
        <tr style="height:50px">
            <td>
                3.1.4教学小组★
            </td>
            <td>
                成立教学小组，组成合理、职责明确，<br>
                切实履职。能定期进行带教指导、指导<br>
                医师培训、教学培训实施和考核、质量<br>
                监管以及教学研究等工作 。
            </td>
            <td>
                有教学小组，履职认真，得3分<br>
                无，或履职不认真，不得分
            </td>

            <td style="text-align: center;">3</td>
            <td width="56px" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,3,0);" itemId="22" itemName="教学小组" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['22']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['22']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="22" itemName="教学小组" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['22']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['22']}
                </c:if>
            </td>
            <td name="file" itemId="22"></td>
        </tr>
        <tr style="height:50px">
            <td>
                3.1.5轮转计划★
            </td>
            <td>
                按规定制定和落实轮转计划和要求 ，体<br>
                现分层递进的培训理念。
            </td>
            <td width="20%">
                1.现场核查在培住院医师轮转情况<br>
                2.访谈专业基地管理人员、指导医<br>
                师和住院医师
            </td>
            <td>
                轮转计划体现分层递进，并严格落实，<br>
                得4分<br>
                轮转计划未体现分层递进，但严格落<br>
                实，得2分<br>
                未制定轮转计划或未严格落实的 ，不得<br>
                分，专业基地限期整改
            </td>

            <td style="text-align: center;">4</td>
            <td width="56px" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,4,0);" itemId="23" itemName="轮转计划" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['23']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['23']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="23" itemName="轮转计划" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['23']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['23']}
                </c:if>
            </td>
            <td name="file" itemId="23"></td>
        </tr>
        <tr style="height:50px">
            <td>3.2培训招收</td>
            <td>
                3.2.1培训容量及招收★
            </td>
            <td>
                容量测算科学合理，完成招收任务，且<br>
                在培住院医师≥10名，不能超容量招收
            </td>
            <td>
                1.现场查看<br>
                2.访谈住院医师
            </td>
            <td width="20%">
                招收在容量测算数内，完成招收任务，<br>
                且在培住院医师≥10名，得2分；<br>
                容量测算科学合理，近三年未完成招收<br>
                任务，但在培住院医师≥10名，得<br>
                1分；<br>
                在培住院医师＜10名，或超容量招收，<br>
                不得分
            </td>

            <td style="text-align: center;">2</td>
            <td width="56px" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,2,0);" itemId="24" itemName="培训容量及招收" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['24']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['24']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="24" itemName="培训容量及招收" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['24']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['24']}
                </c:if>
            </td>
            <td name="file" itemId="24"></td>
        </tr>
        <tr style="height:50px">
            <td rowspan="5">
                3.3培训活动
            </td>
            <td>
                3.3.1入专业基地及入<br>
                轮转科室教育
            </td>
            <td>
                规范开展入专业基地教育 ，包括学科背<br>
                景、规章制度及流程、专业基地培训目<br>
                标、培训内容和轮转计划、轮转期间所<br>
                需掌握的临床诊疗能力、技能操作等内<br>
                容，并有专人组织实施<br>
                规范开展入轮转科室教育 ，包括科室情<br>
                况、科室纪律、培养计划与要求、医德<br>
                医风、医患沟通等内容的入科教育 ，培<br>
                训与考核要求体现科室岗位基本需求特<br>
                点，并有专人组织实施
            </td>
            <td rowspan="5">
                1.现场查看教学活动落实情况<br>
                2.访谈专业基地管理人员、指导医<br>
                师和住院医师
            </td>
            <td width="20%">
                1.按指南要求规范组织实施入专业基地<br>
                教育，得1分；未组织或组织实施不规<br>
                范，不得分<br>
                2.按指南要求规范组织实施入轮转科室<br>
                教育，得1分；未组织或组织实施不规<br>
                范，不得分
            </td>

            <td style="text-align: center;">2</td>
            <td width="56px" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,2);" itemId="25" itemName="入专业基地及入轮转科室教育" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['25']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['25']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="25" itemName="入专业基地及入轮转科室教育" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['25']}</textarea>

                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['25']}
                </c:if>
            </td>
            <td name="file" itemId="25"></td>
        </tr>
        <tr style="height:50px">
            <td>
                3.3.2教学查房
            </td>
            <td>
                开展规范的教学查房，至少2周1次
            </td>
            <td width="20%">
                开展次数达标，且认真规范，得2分<br>
                开展次数达标，但不认真规范，得1分<br>
                未开展或不达标，不得分
            </td>

            <td style="text-align: center;">2</td>
            <td width="56px" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,2);" itemId="26" itemName="教学查房" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['26']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['26']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="26" itemName="教学查房" name="reason"
                              placeholder="请填写详细情况" style="width: 98%"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['26']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['26']}
                </c:if>
            </td>
            <td name="file" itemId="26"></td>
        </tr>
        <tr style="height:50px">
            <td>
                3.3.3小讲课
            </td>
            <td>
                开展规范的小讲课活动，至少每周1次
            </td>
            <td>
                开展次数达标，且认真规范，得2分<br>
                开展次数达标，但不认真规范，得1分<br>
                未开展或不达标，不得分
            </td>

            <td style="text-align: center;">2</td>
            <td width="56px" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,2);" itemId="27" itemName="小讲课" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['27']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['27']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="27" itemName="小讲课" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['27']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['27']}
                </c:if>
            </td>
            <td name="file" itemId="27"></td>
        </tr>
        <tr style="height:50px">
            <td>
                3.3.4教学病例讨论
            </td>
            <td>
                开展规范的疑难病例讨论 ，至少每月1<br>
                次
            </td>
            <td width="20%">
                开展次数达标，且认真规范，得2分<br>
                开展次数达标，但不认真规范，得1分<br>
                未开展或不达标，不得分
            </td>

            <td style="text-align: center;">2</td>
            <td width="56px" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,2);" itemId="28" itemName="教学病例讨论" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['28']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['28']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="28" itemName="教学病例讨论" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['28']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['28']}
                </c:if>
            </td>
            <td name="file" itemId="28"></td>
        </tr>
        <tr style="height:50px">
            <td>
                3.3.5特色教学活动
            </td>
            <td>
                根据基地条件，开展特色教学活动，有<br>
                助于加强《住院医师规范化培训内容与<br>
                标准（2022年版）》口腔正畸科专业细<br>
                则要求的落实
            </td>
            <td width="20%">
                有特色活动，得满分<br>
                无特色活动，不得分
            </td>

            <td style="text-align: center;">2</td>
            <td width="56px" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,2);" itemId="29" itemName="特色教学活动" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['29']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['29']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="29" itemName="特色教学活动" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['29']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['29']}
                </c:if>
            </td>
            <td name="file" itemId="29"></td>
        </tr>
        <tr style="height:50px">
            <td rowspan="3">
                3.4过程考核
            </td>
            <td>
                3.4.1日常考核
            </td>
            <td>
                包括医德医风、临床职业素养、考勤、<br>
                临床实践能力、培训指标完成情况和参<br>
                加业务学习情况，以及形成性评价开展<br>
                情况等内容，相关记录等原始资料齐<br>
                全，真实规范。
            </td>
            <td width="20%" rowspan="3">
                1.现场查看过程考核落实情况<br>
                2.访谈专业基地管理人员、指导医<br>
                师和住院医师
            </td>
            <td>
                评估项目全面，且认真规范，得2分；<br>
                仅有测评结果和考勤记录，得1分；<br>
                无，不得分
            </td>

            <td style="text-align: center;">2</td>
            <td width="56px" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,2);" itemId="30" itemName="日常考核" name="local"
                           class="input" type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['30']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['30']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="30" itemName="日常考核" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%"
                              class="text-input validate[required,maxSize[1000]]">${contentMap['30']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['30']}
                </c:if>
            </td>
            <td name="file" itemId="30"></td>
        </tr>
        <tr style="height:50px">
            <td>
                3.4.2出科考核★
            </td>
            <td>
                有出科考核实施细则；考核内容包括理<br>
                论与技能，体现专业特点和岗位胜任 、<br>
                分层递进的培训理念；出科考核形式规<br>
                范，原始资料齐全，专业基地教学小组<br>
                审核和组长签字
            </td>
            <td>
                1.有实施细则，得1分；<br>
                2.考核内容齐全，并体现专业特点和岗<br>
                位胜任力、分层递进，得1.5分；<br>
                3.考核形式规范，资料齐全，有专业基<br>
                地教学小组审核和组长签字 ，得0.5分
            </td>

            <td style="text-align: center;">3</td>
            <td width="56px" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,3,0);" itemId="31" itemName="出科考核" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['31']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['31']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                 <textarea onchange="saveSpeReason(this);" itemId="31" itemName="出科考核" name="reason"
                           placeholder="请填写详细情况" style="width: 98%;height: 98%"
                           class="text-input validate[required,maxSize[1000]]">${contentMap['31']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['31']}
                </c:if>
            </td>
            <td name="file" itemId="31"></td>
        </tr>
        <tr style="height:50px">
            <td>
                3.4.3年度考核
            </td>
            <td>
                有符合本专业的年度考核实施细则 ，内<br>
                容包括个人总结、理论知识、实践技能<br>
                、综合评价等，能真实全面反映年度培<br>
                训情况，体现专业特点和分层递进的培<br>
                训要求
            </td>
            <td>
                考核内容全面，能体现岗位胜任力，得<br>
                2分<br>
                考核项目不全面，或不能体现岗位胜任<br>
                力，得1分<br>
                无年度考核，不得分
            </td>

            <td style="text-align: center;">2</td>
            <td width="56px" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,2);" itemId="32" itemName="年度考核" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['32']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['32']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveSpeReason(this);" itemId="32" itemName="年度考核" name="reason"
                         placeholder="请填写详细情况" style="width: 98%;height: 98%"
                         class="text-input validate[required,maxSize[1000]]">${contentMap['32']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['32']}
                </c:if>
            </td>
            <td name="file" itemId="32"></td>
        </tr>
        <tr style="height:50px">
            <td>
                3.5培训强度
            </td>
            <td>
                3.5.1培训强度★
            </td>
            <td>
                每名住院医师日均独立诊治门诊患者第<br>
                1年≥4人次，第2年≥5人次，第3年≥6<br>
                人次
            </td>
            <td width="20%">
                查看轮转手册（或医院相关电子系<br>
                统）等，随机抽查访谈各类住院医<br>
                师各1～2名
            </td>
            <td>
                100%达要求，得满分<br>
                ≥80%达要求，得2分<br>
                ≥60%达要求，得1分<br>
                ＜60%达要求，或未安排独立诊治，不<br>
                得分
            </td>

            <td style="text-align: center;">3</td>
            <td width="56px" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,3,0);" itemId="33" itemName="培训强度" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['33']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['33']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                  <textarea onchange="saveSpeReason(this);" itemId="33" itemName="培训强度" name="reason"
                            placeholder="请填写详细情况" style="width: 98%;height: 98%"
                            class="text-input validate[required,maxSize[1000]]">${contentMap['33']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['33']}
                </c:if>
            </td>
            <td name="file" itemId="33"></td>
        </tr>
        <tr style="height:50px">
            <td rowspan="7">
                4.培训质<br>
                量<br>
                （35分）
            </td>
            <td rowspan="2">
                4.1指导医师<br>
                教学质量
            </td>
            <td>
                4.1.1临床教学质量★
            </td>
            <td>
                针对住院医师开展规范的临床教学 ，悉<br>
                心指导住院医师
            </td>
            <td width="20%">
                随机抽查1～2名指导医师临床教<br>
                学，按评分标准考核
            </td>
            <td>
                教学技能评分表见
                <a href="javascript:void(0)" onclick="scheduleOne('hospSelfAssessment3200_02',' 附件2');">附件2</a>
                    <br>
                评估分值≥90分，得6分；80分≤评估<br>
                分值＜90分，得5分；70分≤评估分值<br>
                ＜80分，得3分；60分≤评估分值＜70<br>
                分，得2分；评估分值＜60分，不得分
            </td>

            <td style="text-align: center;">6</td>
            <td width="56px" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input id="fubiao2" onchange="saveScore4Expert(this,6,0);" itemId="34" itemName="临床教学质量" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['34']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['34']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                <textarea onchange="saveSpeReason(this);" itemId="34" itemName="临床教学质量" name="reason"
                          placeholder="请填写详细情况" style="width: 98%;height: 98%"
                          class="text-input validate[required,maxSize[1000]]">${contentMap['34']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['34']}
                </c:if>
            </td>
            <td name="file" itemId="34"></td>
        </tr>
        <tr style="height:50px">
            <td>
                4.1.2技能操作安排情况<br>
                ★
            </td>
            <td>
                符合《住院医师规范化培训内容与标准<br>
                （2022年版）》口腔修复科专业细则要<br>
                求
            </td>
            <td width="20%">
                随机抽查1～2名指导医师的临床教<br>
                学安排统计表
            </td>
            <td>
                符合要求（含协同单位），得满分<br>
                技能操作种类及数量≥规定数的90%，<br>
                得4分<br>
                技能操作种类及数量≥规定数的85%，<br>
                得3分<br>
                技能操作种类及数量≥规定数的80%，<br>
                得2分<br>
                技能操作种类及数量＜规定数的80%，<br>
                不得分
            </td>

            <td style="text-align: center;">5</td>
            <td width="56px" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,5,0);" itemId="35" itemName="技能操作安排情况" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['35']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['35']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
               <textarea onchange="saveSpeReason(this);" itemId="35" itemName="技能操作安排情况" name="reason"
                         placeholder="请填写详细情况" style="width: 98%;height: 98%"
                         class="text-input validate[required,maxSize[1000]]">${contentMap['35']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['35']}
                </c:if>
            </td>
            <td name="file" itemId="35"></td>
        </tr>
        <tr style="height:50px">
            <td rowspan="5">
                4.2住院医师<br>
                培训效果
            </td>
            <td>
                4.2.1病历书写★
            </td>
            <td>
                住院医师病历书写情况
            </td>
            <td width="20%">
                随机抽查1～2名住院医师运行病历<br>
                和归档病历各1份
            </td>
            <td>
                病历书写评分表见
                <a href="javascript:void(0)" onclick="scheduleOne('hospSelfAssessment3200_03',' 附件3');">附件3</a>
                    <br>
                评估分值≥90分，得6分；80分≤评估<br>
                分值＜90分，得5分；70分≤评估分值<br>
                ＜80分，得3分；60分≤评估分值＜70<br>
                分，得2分；评估分值＜60分，不得分
            </td>

            <td style="text-align: center;">6</td>
            <td width="56px" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input id="fubiao3" onchange="saveScore4Expert(this,6,0);" itemId="36" itemName="病历书写" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['36']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['36']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                 <textarea onchange="saveSpeReason(this);" itemId="36" itemName="病历书写" name="reason"
                     placeholder="请填写详细情况" style="width: 98%;height: 98%"
                     class="text-input validate[required,maxSize[1000]]">${contentMap['36']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['36']}
                </c:if>
            </td>
            <td name="file" itemId="36"></td>
        </tr>
        <tr style="height:50px">
            <td>
                4.2.2技能操作或手术★
            </td>
            <td>
                住院医师技能操作或手术情况
            </td>
            <td width="20%">
                随机抽查1～2名二年级以上住院医<br>
                师进行技能操作或常见手术操作<br>
                （术者、助手），查看其掌握情况<br>
                。
            </td>
            <input type="hidden" id="scoreInfo41" value="${scheduleMap['scoreInfo41']}" name="scoreOneInfo">
            <input type="hidden" id="scoreInfo42" value="${scheduleMap['scoreInfo42']}" name="scoreOneInfo">
            <td>
                技能操作评分表见
                <a href="javascript:void(0)" onclick="scheduleMany('hospSelfAssessment3200_Main',' 附件4');">附件4</a>
                <br>
                评估分值≥90分，得7分；80分≤评估<br>
                分值＜90分，得5分；70分≤评估分值<br>
                ＜80分，得4分；60分≤评估分值＜70<br>
                分，得2分；评估分值＜60分，不得分
            </td>

            <td style="text-align: center;">7</td>
            <td width="56px" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input id="fubiao4" onchange="saveScore4Expert(this,7,0);" itemId="37" itemName="技能操作或手术" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['37']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['37']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                <textarea onchange="saveSpeReason(this);" itemId="37" itemName="技能操作或手术" name="reason"
                          placeholder="请填写详细情况" style="width: 98%;height: 98%"
                          class="text-input validate[required,maxSize[1000]]">${contentMap['37']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['37']}
                </c:if>
            </td>
            <td name="file" itemId="37"></td>
        </tr>
        <tr style="height:50px">
            <td>
                4.2.3完成培训内容与要<br>
                求★
            </td>
            <td>
                按照本专业《住院医师规范化培训内容<br>
                与标准（2022年版）》，核实培训内容<br>
                的完成情况
            </td>
            <td width="20%">
                随机抽查访谈本院、委培、社会招<br>
                收住院医师各2～3名，查看轮转登<br>
                记手册、出科考核等原始资料
            </td>
            <td>
                完成率≥95%，得7分<br>
                90%≤完成率＜95%，得5分<br>
                80%≤完成率＜90%，得4分<br>
                完成率＜80%，不得分
            </td>

            <td style="text-align: center;">7</td>
            <td width="56px" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,7,0);" itemId="38" itemName="完成培训内容与要求" name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['38']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['38']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
              <textarea onchange="saveSpeReason(this);" itemId="38" itemName="完成培训内容与要求" name="reason"
                        placeholder="请填写详细情况" style="width: 98%;height: 98%"
                        class="text-input validate[required,maxSize[1000]]">${contentMap['38']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['38']}
                </c:if>
            </td>
            <td name="file" itemId="38"></td>
        </tr>
        <tr style="height:50px">
            <td>
                4.2.4执业医师资格考试
            </td>
            <td>
                住院医师首次参加执业医师资格考试的<br>
                通过率
            </td>
            <td width="20%">
                现场查看
            </td>
            <td>
                近三年本专业住院医师首次参加执业医<br>
                师资格考试的通过率≥近三年全国本专<br>
                业住院医师首次参加执业医师资格考试<br>
                的平均通过率，得1分；低于近三年全<br>
                国本专业住院医师首次参加执业医师资<br>
                格考试的平均通过率不得分<br>
                <br>
                （近三年本专业住院医师首次参加执业<br>
                医师资格考试的通过率=近三年本专业<br>
                首次参加考试通过的人数/近三年本专<br>
                业首次参加考试总人数）
            </td>

            <td style="text-align: center;">1</td>
            <td width="56px" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,1);" itemId="39"  name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['39']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['39']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
              <textarea onchange="saveSpeReason(this);" itemId="39"  name="reason"
                        placeholder="请填写详细情况" style="width: 98%;height: 98%"
                        class="text-input validate[required,maxSize[1000]]">${contentMap['39']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['39']}
                </c:if>
            </td>
            <td name="file" itemId="39"></td>
        </tr>
        <tr style="height:50px">
            <td>
                4.2.5结业考核★
            </td>
            <td>
                住院医师首次参加结业考核的通过率
            </td>
            <td width="20%">
                现场查看
            </td>
            <td>
                近三年本专业住院医师首次参加结业理<br>
                论考核的通过率≥近三年全国本专业住<br>
                院医师首次参加结业理论考核平均通过<br>
                率，得3分；低于近三年全国本专业住<br>
                院医师首次参加结业理论考核平均通过<br>
                率，每降低1个百分点，扣1分，扣完为<br>
                止<br>
                （近三年本专业住院医师首次参加结业<br>
                理论考核的通过率=近三年本专业首次<br>
                参加结业理论考核通过的人数 /近三年<br>
                本专业首次参加结业理论考核总人数 ）
            </td>

            <td style="text-align: center;">3</td>
            <td width="56px" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore4Expert(this,3,0);" itemId="40"  name="local" class="input"
                           type="text" style="height:20px;width: 25px; text-align: center" value="${scoreMap['40']}"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    <span name="local">${scoreMap['40']}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
              <textarea onchange="saveSpeReason(this);" itemId="40"  name="reason"
                        placeholder="请填写详细情况" style="width: 98%;height: 98%"
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
        <tr style="height:16px;">
            <td colspan="10">
                <c:if test="${type eq 'Y'}">
              <textarea onchange="saveSpeReason(this);" itemId="41" name="reason"
                        placeholder="请详细填写存在问题" style="width: 100%;height: 100px"
                        class="text-input validate[required,maxSize[1000]]">${contentMap['41']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${contentMap['41']}
                </c:if>
            </td>
        </tr>
    </table>
    <div style="margin-top: 20px;">
        备注： <br>
        1.一级指标4项，二级指标11项，三级指标40项。三级指标中，核心指标18项、计64分，一般指标22项、计36分，共100分。单个核心指标达标判定标准 ：单个核心指标得分率≥70%为达标，＜70%为不达标。其中，单项指标满分为3分的，若评估得2分的则判定为达标。 <br>
        合格：评估分值≥80分，且核心指标达标数≥14个<br>
        基本合格：70分≤评估分值＜80分，且11个≤核心指标达标数≤13个<br>
        限期整改(黄牌)：60分≤评估分值＜70分；或基本条件合格，7个≤核心指标达标数≤10个；或在培住院医师总数近3年达不到专业基地最小培训容量要求的 ；未制定轮转计划或未严格落实的；结业理论考核通过率处于全省末位者 。 <br>
        撤销(红牌)：评估分值＜60分；或核心指标达标数≤6个；或连续3年“零”招收<br>
        2.指标中所有规章制度，专指住院医师规范化培训相关制度 。<br>
        3.随机抽查对象优先选择委托住院医师和面向社会招收的住院医师 ，如果没有，可考虑本基地住院医师。<br>
        4.现场评估时详细填写存在的问题和扣分原因 。<br>
        5.专业基地应确保所提供的材料真实可靠 ，对于弄虚作假者，一经查实，将提请当地省级卫生健康行政部门暂停其住培招收资格 ，情节严重的，撤销其专业基地资格。
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