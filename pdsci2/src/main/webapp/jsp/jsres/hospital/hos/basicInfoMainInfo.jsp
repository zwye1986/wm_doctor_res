<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    $(document).ready(function () {
        for (var i = 1; i < 3; i++) {
            for (var j = 1; j < 5; j++) {
                calculate(i, "td" + j);
            }
        }
        for (var i = 5; i < 7; i++) {
            calculate(2, "td" + i);
        }
        $("#cw").text(parseInt($("#td11").text()) + parseInt($("#td12").text()));
        $("#mz").text(parseInt($("#td21").text()) + parseInt($("#td22").text()));
        $("#cy").text(parseInt($("#td31").text()) + parseInt($("#td32").text()));
        $("#dj").text(parseInt($("#td41").text()) + parseInt($("#td42").text()));

        $(":radio").attr("disabled", true);
        $(":input").attr("readonly", true);
        showTable("jbxx");
    });

    function showTable(obj) {
        var oDiv = document.getElementById(obj);
        var aDisplay = oDiv.style.display;
        if ("none" == aDisplay) {
            oDiv.style.display = "";

            var imgDivUp = document.getElementById(obj + "up");
            var imgDivDown = document.getElementById(obj + "down");
            imgDivUp.style.display = "";
            imgDivDown.style.display = "none";
        } else {
            oDiv.style.display = "none";
            var imgDivUp = document.getElementById(obj + "up");
            var imgDivDown = document.getElementById(obj + "down");
            imgDivUp.style.display = "none";
            imgDivDown.style.display = "";
        }
    }

    function calculate(tableName, className) {
        var sum = 0;
        $("#table" + tableName + " ." + className).each(function () {
            var val = $(this).text();
            if (val == null || val == undefined || val == '' || isNaN(val)) {
                val = 0;
            }
            sum += parseFloat(val);
        });
        $("#" + className + tableName).text(parseFloat(sum.toFixed(3)));
    }

    function auditStatus(baseFlow, status) {
        var s = "通过";
        if (status == '${GlobalConstant.FLAG_N}') {
            s = "不通过";
        }
        jboxConfirm("确认" + s + "？", function () {
            var data = {
                "baseFlow": baseFlow,
                "status": status
            };
            jboxPost("<s:url value='/jsres/base/baseAudit'/>", data, function (resp) {
                if ("${GlobalConstant.OPRE_SUCCESSED}" == resp) {
                    window.parent.auditHospitals();
                    setTimeout(function () {
                        loadInfo('${GlobalConstant.BASIC_INFO}', '${param.baseFlow}');
                    }, 1000);
                }
            }, null, true);
        });
    }
</script>
<style>
    .auditPass {
        height: 30px;
        width: 100px
    }

    .div_table {
        padding: 10px 30px 0;
        margin-bottom: 0px;
    }

    .infoAudit h4 {
        color: #000000;
        font: 15px 'Microsoft Yahei';
        font-weight: 500;
    }

    .infoAudit table th {
        color: #000000;
        font: 14px 'Microsoft Yahei';
        font-weight: 500;
    }
    .infoAudit table td {
        color: #000000;
        font: 14px 'Microsoft Yahei';
        font-weight: 400;
    }
</style>
</head>
<body>
<div class="div_table">
    <h4 onclick="showTable('jbxx');">基本信息</h4>
    <img id="jbxxdown" src="<s:url value='/jsp/jsres/images/down3.png'/>" onclick="showTable('jbxx');"
         style="display: none;float:right;width: 20px;height: 20px;margin-right: 40px;margin-top: -35px"/>
    <img id="jbxxup" src="<s:url value='/jsp/jsres/images/up3.png'/>" onclick="showTable('jbxx');"
         style="float:right;width: 20px;height: 20px;margin-right: 40px;margin-top: -35px"/>

    <c:if test="${maintenanceFlag ne true and isglobal ne 'Y' and isJoin ne 'Y'}">
        <img src="<s:url value='/jsp/res/images/test.png'/>"  onclick="editInfo('BasicInfo','${sessionScope.currUser.orgFlow}','${sessionNumber}');"
             style="cursor: pointer;height: 20px;width: 20px;float: right;margin-top: -35px"/>
    </c:if>

    <input type="hidden" id="resBase" name="resBase" value="${resBase}"/>
    <div id="jbxx" style="display: none">
    <table border="0" cellspacing="0" cellpadding="0" class="base_info ">
        <colgroup>
            <col width="20%"/>
            <col width="15%"/>
            <col width="15%"/>
            <col width="15%"/>
            <col width="15%"/>
            <col width="15%"/>
        </colgroup>
        <tbody>
        <tr>
            <th style="background-color: #f4f5f9;">培训基地名称：</th>
            <td colspan="2">${sysOrg.orgName}</td>
            <th style="background-color: #f4f5f9;">所属地区：</th>
            <td colspan="2">${sysOrg.orgProvName}${sysOrg.orgCityName}${sysOrg.orgAreaName}</td>
        </tr>
        <tr>
            <th style="background-color: #f4f5f9;">培训基地地址：</th>
            <td colspan="2">${sysOrg.orgAddress}</td>
            <th style="background-color: #f4f5f9;">邮政编码：</th>
            <td colspan="2">${sysOrg.orgZip}</td>
        </tr>
        <%--<tr>
            <th style="background-color: #f4f5f9;">培训基地负责人：</th>
            <td>${sysOrg.orgPersonInCharge}</td>
            <th style="background-color: #f4f5f9;">职称：</th>
            <td>${basicInfo.jdfzrTitleName}</td>
            <th style="background-color: #f4f5f9;">职务：</th>
            <td>${basicInfo.jdfzrPostName}</td>
        </tr>
        <tr>
            <th style="background-color: #f4f5f9;">手机号码：</th>
            <td>${basicInfo.jdfzrMobilephone}</td>
            <th style="background-color: #f4f5f9;">固定电话：</th>
            <td>${basicInfo.jdfzrFixedPhone}</td>
            <th style="background-color: #f4f5f9;">邮箱地址：</th>
            <td>${basicInfo.jdfzrMailAddress}</td>
        </tr>--%>
        <tr><table class="base_info">
            <colgroup>
                <col width="16.67%"/>
                <col width="16.67%"/>
                <col width="16.67%"/>
                <col width="16.67%"/>
                <col width="16.67%"/>
                <col width="16.67%"/>
            </colgroup>
            <tr>
                <th style="text-align: center">培训基地负责人</th>
                <th style="text-align: center">手机号码</th>
                <th style="text-align: center">固定电话</th>
                <th style="text-align: center">邮箱地址</th>
                <th style="text-align: center">职称</th>
                <th style="text-align: center">职务</th>
            </tr>
            <c:choose>
                <c:when test="${not empty sysOrg.orgPersonInCharge}">
                    <tr>
                        <td style="text-align: center">${sysOrg.orgPersonInCharge}</td>
                        <td style="text-align: center">${basicInfo.jdfzrMobilephone}</td>
                        <td style="text-align: center">${basicInfo.jdfzrFixedPhone}</td>
                        <td style="text-align: center">${basicInfo.jdfzrMailAddress}</td>
                        <td style="text-align: center">${basicInfo.jdfzrTitleName}</td>
                        <td style="text-align: center">${basicInfo.jdfzrPostName}</td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td colspan="6" style="text-align: center">暂未填写</td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </table>
        </tr>
        <%--<tr><table class="base_info">
        <c:choose>
            <c:when test="${not empty basicInfo.ywfgfzrList}">
            <colgroup>
                <col width="20%"/>
                <col width="20%"/>
                <col width="20%"/>
                <col width="20%"/>
                <col width="20%"/>
            </colgroup>
                <tr>
                    <th style="text-align: left">分管院长</th>
                    <th style="text-align: left">手机号码</th>
                    <th style="text-align: left">邮箱地址</th>
                    <th style="text-align: left">职称</th>
                    <th style="text-align: left">职务</th>
                </tr>
                <c:forEach var="ywfg" items="${basicInfo.ywfgfzrList}" varStatus="status">
                    <tr>
                        <td>${ywfg.contactorName}</td>
                        <td>${ywfg.mobilephone}</td>
                        <td>${ywfg.mailAddress}</td>
                        <td>${ywfg.titleName}</td>
                        <td>${ywfg.postName}</td>
                    </tr>
                    &lt;%&ndash;<tr>
                        <th style="background-color: #f4f5f9;">分管院长：</th>
                        <td>${ywfg.contactorName}</td>
                        <th style="background-color: #f4f5f9;">手机号码：</th>
                        <td>${ywfg.mobilephone}</td>
                        <th style="background-color: #f4f5f9;">邮箱地址：</th>
                        <td>${ywfg.mailAddress}</td>
                    </tr>
                    <tr>
                        <th style="background-color: #f4f5f9;">职称：</th>
                        <td>${ywfg.titleName}</td>
                        <th style="background-color: #f4f5f9;">职务：</th>
                        <td colspan="3">${ywfg.postName}</td>
                    </tr>&ndash;%&gt;
                </c:forEach>
            </c:when>
            <c:otherwise>
                <colgroup>
                    <col width="20%"/>
                    <col width="20%"/>
                    <col width="20%"/>
                    <col width="20%"/>
                    <col width="20%"/>
                </colgroup>
                <tr>
                    <th style="text-align: left">分管院长</th>
                    <th style="text-align: left">手机号码</th>
                    <th style="text-align: left">邮箱地址</th>
                    <th style="text-align: left">职称</th>
                    <th style="text-align: left">职务</th>
                </tr>
            </c:otherwise>
        </c:choose>
        </table></tr>
        <tr><table class="base_info">
        <c:choose>
            <c:when test="${not empty basicInfo.zpywkslxrList}">
                <colgroup>
                    <col width="20%"/>
                    <col width="20%"/>
                    <col width="20%"/>
                    <col width="20%"/>
                    <col width="20%"/>
                </colgroup>
                <tr>
                    <th style="text-align: left">住培业务科室（处室）联系人</th>
                    <th style="text-align: left">手机号码</th>
                    <th style="text-align: left">邮箱地址</th>
                    <th style="text-align: left">职称</th>
                    <th style="text-align: left">职务</th>
                </tr>
                <c:forEach var="zpyw" items="${basicInfo.zpywkslxrList}" varStatus="status">
                    <tr>
                    <td>${zpyw.contactorName}</td>
                    <td>${zpyw.mobilephone}</td>
                    <td>${zpyw.mailAddress}</td>
                    <td>${zpyw.titleName}</td>
                    <td>${zpyw.postName}</td>
                    </tr>
                    &lt;%&ndash;<tr>
                        <th style="background-color: #f4f5f9;">住培业务科室（处室）联系人：</th>
                        <td>${zpyw.contactorName}</td>
                        <th style="background-color: #f4f5f9;">手机号码：</th>
                        <td>${zpyw.mobilephone}</td>
                        <th style="background-color: #f4f5f9;">邮箱地址：</th>
                        <td>${zpyw.mailAddress}</td>
                    </tr>
                    <tr>
                        <th style="background-color: #f4f5f9;">职称：</th>
                        <td>${zpyw.titleName}</td>
                        <th style="background-color: #f4f5f9;">职务：</th>
                        <td colspan="3">${zpyw.postName}</td>
                    </tr>&ndash;%&gt;
                </c:forEach>
            </c:when>
            <c:otherwise>
                <colgroup>
                    <col width="20%"/>
                    <col width="20%"/>
                    <col width="20%"/>
                    <col width="20%"/>
                    <col width="20%"/>
                </colgroup>
                <tr>
                    <th style="text-align: left">住培业务科室（处室）联系人</th>
                    <th style="text-align: left">手机号码</th>
                    <th style="text-align: left">邮箱地址</th>
                    <th style="text-align: left">职称</th>
                    <th style="text-align: left">职务</th>
                </tr>
            </c:otherwise>
        </c:choose>
        </table></tr>--%>
        <tr><table class="base_info">
        <c:choose>
            <c:when test="${not empty basicInfo.zpglbmfzrList}">
                <colgroup>
                    <col width="16.67%"/>
                    <col width="16.67%"/>
                    <col width="16.67%"/>
                    <col width="16.67%"/>
                    <col width="16.67%"/>
                    <col width="16.67%"/>
                </colgroup>
                <tr>
                    <th style="text-align: center">住培管理部门负责人</th>
                    <th style="text-align: center">手机号码</th>
                    <th style="text-align: center">固定电话</th>
                    <th style="text-align: center">邮箱地址</th>
                    <th style="text-align: center">职称</th>
                    <th style="text-align: center">职务</th>
                </tr>
                <c:forEach var="zpglbm" items="${basicInfo.zpglbmfzrList}" varStatus="status">
                    <tr>
                    <td style="text-align: center">${zpglbm.contactorName}</td>
                    <td style="text-align: center">${zpglbm.mobilephone}</td>
                    <td style="text-align: center">${zpglbm.fixedPhone}</td>
                    <td style="text-align: center">${zpglbm.mailAddress}</td>
                    <td style="text-align: center">${zpglbm.titleName}</td>
                    <td style="text-align: center">${zpglbm.postName}</td>
                    </tr>
                    <%--<tr>
                        <th style="background-color: #f4f5f9;">住培管理部门负责人：</th>
                        <td>${zpglbm.contactorName}</td>
                        <th style="background-color: #f4f5f9;">手机号码：</th>
                        <td>${zpglbm.mobilephone}</td>
                        <th style="background-color: #f4f5f9;">邮箱地址：</th>
                        <td>${zpglbm.mailAddress}</td>
                    </tr>
                    <tr>
                        <th style="background-color: #f4f5f9;">职称：</th>
                        <td>${zpglbm.titleName}</td>
                        <th style="background-color: #f4f5f9;">职务：</th>
                        <td colspan="3">${zpglbm.postName}</td>
                    </tr>--%>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <colgroup>
                    <col width="16.67%"/>
                    <col width="16.67%"/>
                    <col width="16.67%"/>
                    <col width="16.67%"/>
                    <col width="16.67%"/>
                    <col width="16.67%"/>
                </colgroup>
                <tr>
                    <th style="text-align: center">住培管理部门负责人</th>
                    <th style="text-align: center">手机号码</th>
                    <th style="text-align: center">固定电话</th>
                    <th style="text-align: center">邮箱地址</th>
                    <th style="text-align: center">职称</th>
                    <th style="text-align: center">职务</th>
                </tr>
                <tr>
                    <td colspan="6" style="text-align: center">暂未填写</td>
                </tr>
            </c:otherwise>
        </c:choose>
        </table></tr>

        <tr><table class="base_info">
            <c:choose>
                <c:when test="${not empty basicInfo.contactManList}">
                    <colgroup>
                        <col width="16.67%"/>
                        <col width="16.67%"/>
                        <col width="16.67%"/>
                        <col width="16.67%"/>
                        <col width="16.67%"/>
                        <col width="16.67%"/>
                    </colgroup>
                    <tr>
                        <th style="text-align: center">联络员</th>
                        <th style="text-align: center">手机号码</th>
                        <th style="text-align: center">固定电话</th>
                        <th style="text-align: center">邮箱地址</th>
                        <th style="text-align: center">职称</th>
                        <th style="text-align: center">职务</th>
                    </tr>
                    <c:forEach var="contactMan" items="${basicInfo.contactManList}" varStatus="status">
                        <tr>
                            <td style="text-align: center">${contactMan.contactorName}</td>
                            <td style="text-align: center">${contactMan.mobilephone}</td>
                            <td style="text-align: center">${contactMan.fixedPhone}</td>
                            <td style="text-align: center">${contactMan.mailAddress}</td>
                            <td style="text-align: center">${contactMan.titleName}</td>
                            <td style="text-align: center">${contactMan.postName}</td>
                        </tr>
                        <%--<tr>
                            <th style="background-color: #f4f5f9;">住培管理部门负责人：</th>
                            <td>${zpglbm.contactorName}</td>
                            <th style="background-color: #f4f5f9;">手机号码：</th>
                            <td>${zpglbm.mobilephone}</td>
                            <th style="background-color: #f4f5f9;">邮箱地址：</th>
                            <td>${zpglbm.mailAddress}</td>
                        </tr>
                        <tr>
                            <th style="background-color: #f4f5f9;">职称：</th>
                            <td>${zpglbm.titleName}</td>
                            <th style="background-color: #f4f5f9;">职务：</th>
                            <td colspan="3">${zpglbm.postName}</td>
                        </tr>--%>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <colgroup>
                        <col width="16.67%"/>
                        <col width="16.67%"/>
                        <col width="16.67%"/>
                        <col width="16.67%"/>
                        <col width="16.67%"/>
                        <col width="16.67%"/>
                    </colgroup>
                    <tr>
                        <th style="text-align: center">联络员</th>
                        <th style="text-align: center">手机号码</th>
                        <th style="text-align: center">固定电话</th>
                        <th style="text-align: center">邮箱地址</th>
                        <th style="text-align: center">职称</th>
                        <th style="text-align: center">职务</th>
                    </tr>
                    <tr>
                        <td colspan="6" style="text-align: center">暂未填写</td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </table></tr>
        </tbody>
    </table>
    </div>
</div>

<div class="div_table">
    <h4  onclick="showTable('zz');">培训基地（医院）资质</h4>
    <img id="zzdown" src="<s:url value='/jsp/jsres/images/down3.png'/>" onclick="showTable('zz');"
         style="float:right;width: 20px;height: 20px;margin-right: 40px;margin-top: -35px"/>
    <img id="zzup" src="<s:url value='/jsp/jsres/images/up3.png'/>" onclick="showTable('zz');"
         style="display: none;float:right;width: 20px;height: 20px;margin-right: 40px;margin-top: -35px"/>

    <c:if test="${maintenanceFlag ne true and isglobal ne 'Y' and isJoin ne 'Y'}">
        <img src="<s:url value='/jsp/res/images/test.png'/>"  onclick="editInfo('BasicInfo','${sessionScope.currUser.orgFlow}','${sessionNumber}');"
             style="cursor: pointer;height: 20px;width: 20px;float: right;margin-top: -35px"/>
    </c:if>
    <div id="zz" style="display: none">
        <table border="0" cellspacing="0" cellpadding="0" class="base_info">
            <colgroup>
                <col width="23%"/>
                <col width="27%"/>
                <col width="23%"/>
                <col width="27%"/>
            </colgroup>
            <tbody>
            <tr>
                <th style="background-color: #f4f5f9;">类别：</th>
                <td >${basicInfo.lx}</td>
                <th style="background-color: #f4f5f9;">级别：</th>
                <td >${basicInfo.levelRankName}</td>
            </tr>
            <tr>
                <%--<th style="background-color: #f4f5f9;">等级：</th>
                <td>${basicInfo.dj}</td>
                <th style="background-color: #f4f5f9;">等次：</th>
                <td>${basicInfo.dc}</td>--%>
            </tr>
            <%--<c:if test="${not empty basicInfo.zzOtherInfo}">
                <tr>
                    <th style="background-color: #f4f5f9;">等级其它信息：</th>
                    <td>${basicInfo.zzOtherInfo}</td>
                </tr>
            </c:if>--%>
            <tr>
                <th style="background-color: #f4f5f9;">注册登记类型：</th>
                <td>${basicInfo.zcdjlx}</td>
                <th style="background-color: #f4f5f9;">分类管理方式：</th>
                <td>${basicInfo.classificationManagement} </td>
            </tr>
            <tr>
                <th style="background-color: #f4f5f9;">住院医师基地获批文号：</th>
                <td>${resBase.resApprovalNumberName}</td>
                <th style="background-color: #f4f5f9;">培训基地（医院）统一社会信用代码：</th>
                <td>${sysOrg.creditCode}</td>
            </tr>
            <tr>
                <th style="background-color: #f4f5f9;">执业许可证：</th>
                <td>
                            <span style="display:${!empty basicInfo.professionLicenceUrl?'':'none'} ">
                                <a href="${sysCfgMap['upload_base_url']}/${basicInfo.professionLicenceUrl}"
                                   target="_blank" style="font: 14px 'Microsoft Yahei';font-weight: 400;border-radius: 2px;">查看图片</a>
                            </span>
                </td>
                <th style="background-color: #f4f5f9;">医院等级证书：</th>
                <td>
                            <span style="display:${!empty basicInfo.hospitalLevelLicenceUrl?'':'none'} ">
                                <a href="${sysCfgMap['upload_base_url']}/${basicInfo.hospitalLevelLicenceUrl}"
                                   target="_blank" style="font: 14px 'Microsoft Yahei';font-weight: 400;border-radius: 2px;">查看图片</a>
                            </span>
                </td>
            </tr>
            <c:if test="${null ne jointContractList and jointContractList.size() gt 0}">
                <c:forEach items="${jointContractList}" var="jointContract" varStatus="stat">
                    <tr>
                        <th style="background-color: #f4f5f9;">协同关系协议：${jointContract.orgName}</th>
                        <td>
                            <c:if test="${not empty jointContract.jointContractUrl}">
                            <span>
                                <a href="${sysCfgMap['upload_base_url']}/${jointContract.jointContractUrl}"
                                   target="_blank" style="font: 14px 'Microsoft Yahei';font-weight: 400;border-radius: 2px;">查看图片</a>
                            </span>
                            </c:if>
                            <c:if test="${empty jointContract.jointContractUrl}">
                                <span style="color: grey">暂未上传</span>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
            <c:if test="${jointOrgFlag eq 'Y'}">
                <tr>
                    <th style="background-color: #f4f5f9;">协同关系协议：</th>
                    <td>
                            <span style="display:${!empty basicInfo.collaborativeRelationshipAgreementUrl?'':'none'} ">
                                <a href="${sysCfgMap['upload_base_url']}/${basicInfo.collaborativeRelationshipAgreementUrl}"
                                   target="_blank" style="font: 14px 'Microsoft Yahei';font-weight: 400;border-radius: 2px;">查看图片</a>
                            </span>
                    </td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
</div>

<div class="div_table">
    <h4  onclick="showTable('tj')">基本条件</h4>
    <img id="tjdown" src="<s:url value='/jsp/jsres/images/down3.png'/>" onclick="showTable('tj');"
         style="float:right;width: 20px;height: 20px;margin-right: 40px;margin-top: -35px"/>
    <img id="tjup" src="<s:url value='/jsp/jsres/images/up3.png'/>" onclick="showTable('tj');"
         style="display: none;float:right;width: 20px;height: 20px;margin-right: 40px;margin-top: -35px"/>
    <c:if test="${maintenanceFlag ne true and isglobal ne 'Y' and isJoin ne 'Y'}">
        <img src="<s:url value='/jsp/res/images/test.png'/>"  onclick="editInfo('TeachCondition','${sessionScope.currUser.orgFlow}','${sessionNumber}','${sessionNumber}');"
             style="cursor: pointer;height: 20px;width: 20px;float: right;margin-top: -35px"/>
    </c:if>
    <div id="tj" style="display: none">
        <table border="0" cellspacing="0" cellpadding="0" class="base_info">
            <colgroup>
                <col width="23%"/>
                <col width="27%"/>
                <col width="23%"/>
                <col width="27%"/>
            </colgroup>
            <tbody>
            <tr>
                <th style="background-color: #f4f5f9;">编制总床位数：</th>
                <td>${educationInfo.bzBedCount}张</td>
                <th style="background-color: #f4f5f9;">实有总床位数：</th>
                <td>${educationInfo.sjBedCount}张</td>
            </tr>
            <tr>
                <th style="background-color: #f4f5f9;">年收治住院病人数：<br/></th>
                <td>${ educationInfo. yearlyNumberOfClinicalPatients}人次</td>
                <th style="background-color: #f4f5f9;">病床使用率：<br/></th>
                <td>${educationInfo.bedOccupancy}%</td>
            </tr>
            <tr>
                <th style="background-color: #f4f5f9;">本年门诊量：</th>
                <td>${educationInfo.yearMzCount}万人次</td>
                <th style="background-color: #f4f5f9;">本年急诊量：</th>
                <td>${educationInfo.yearJzCount}万人次</td>
            </tr>
            <tr>
                <th style="background-color: #f4f5f9;">本年手术量：</th>
                <td>${educationInfo.yearSjCount}台次</td>
                <th style="background-color: #f4f5f9;">本年出院病人数：</th>
                <td>${educationInfo.yearCybrCount}万人次</td>
            </tr>
            <tr>
                <th style="background-color: #f4f5f9;">本年专业基地数：</th>
                <td>${educationInfo.numberOfExistingProfessionalBases}个</td>
                <th style="background-color: #f4f5f9;">近三年培训容量总和：</th>
                <td>${educationInfo.total3YearTrainingCapacity}人</td>
            </tr>
            <tr>
                <th style="background-color: #f4f5f9;">本年入出院病人诊断符合率：</th>
                <td>${educationInfo.rcybrzdfhl}%</td>
                <th style="background-color: #f4f5f9;">本年住院病人治愈好转率：</th>
                <td>${educationInfo.zybrzyhzl}%</td>
            </tr>
            <tr>
                <th style="background-color: #f4f5f9;">本年住院总死亡率：</th>
                <td>${educationInfo.zyzswl}%</td>
                <th style="background-color: #f4f5f9;">本年感染总发生率：</th>
                <td>${educationInfo.grzfsl}%</td>
            </tr>
            <tr>
                <th style="background-color: #f4f5f9;">本年手术患者并发症发生率：</th>
                <td>${educationInfo.sshzbfzfsl}%</td>
                <th style="background-color: #f4f5f9;">按省级卫生健康行政部门有关规定核定的培训容量总和：</th>
                <td>${educationInfo.hdpxrlzh}人</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>

<%--<div class="div_table">
    <h4 onclick="showTable('sb');">培训设施设备</h4>
    <img id="sbdown" src="<s:url value='/jsp/jsres/images/down3.png'/>" onclick="showTable('sb');"
         style="float:right;width: 20px;height: 20px;margin-right: 40px;margin-top: -35px"/>
    <img id="sbup" src="<s:url value='/jsp/jsres/images/up3.png'/>" onclick="showTable('sb');"
         style="display: none;float:right;width: 20px;height: 20px;margin-right: 40px;margin-top: -35px"/>
    <c:if test="${maintenanceFlag ne true and isglobal ne 'Y' and isJoin ne 'Y'}">
        <img src="<s:url value='/jsp/res/images/test.png'/>"  onclick="editInfo('BasicInfoBefore','${sessionScope.currUser.orgFlow}','${sessionNumber}');"
             style="cursor: pointer;height: 20px;width: 20px;float: right;margin-top: -35px"/>
    </c:if>
    &lt;%&ndash;        <c:if test="${maintenanceFlag ne true and isglobal ne 'Y' and isJoin ne 'Y'}">&ndash;%&gt;
    &lt;%&ndash;            <img src="<s:url value='/jsp/res/images/test.png'/>"  title="编辑"  onclick="editInfo('TeachCondition','${sessionScope.currUser.orgFlow}');"&ndash;%&gt;
    &lt;%&ndash;                 style="cursor: pointer;height: 20px;width: 20px;float: right;margin-top: -35px"/>&ndash;%&gt;
    &lt;%&ndash;        </c:if>&ndash;%&gt;
    <div id="sb" style="display:none;">
        <table border="2px" cellpadding="1px" cellspacing="1px" class="grid" style="border-top-style: none;">
            <colgroup>
                <col width="22%"/>
                <col width="39%"/>
                <col width="39%"/>
            </colgroup>
            <tbody>
            <tr>
                <th style="background-color: #f4f5f9;border-top: 1px solid #e7e7eb;">教室</th>
                <td style="border-top: 1px solid #e7e7eb;">总面积：${educationInfo.totalClassroomArea}平方米</td>
                <td style="border-top: 1px solid #e7e7eb;">间数：${educationInfo.numberOfClassroom}间</td>
            </tr>
            </tbody>
        </table>
        <table border="2px" cellpadding="1px" cellspacing="1px" class="grid" style="border-top-style: none;">
            <colgroup>
                <col width="20%"/>
                <col width="40%"/>
                <col width="20%"/>
                <col width="10%"/>
            </colgroup>
            <tbody>
            <tr>
                <td style="text-align: left" colspan="4">电化教学设备（设备名称、数量）</td>
            </tr>
            <tr>
                <th style="background-color: #f4f5f9;">序号</th>
                <th style="background-color: #f4f5f9;" colspan="2">设备名称</th>
                <th style="background-color: #f4f5f9;">数量</th>
            </tr>
            <c:forEach var="teachingEquipment" items="${educationInfo.teachingEquipmentList}" varStatus="status">
                <tr>
                    <td>${status.index + 1}</td>
                    <td colspan="2">${teachingEquipment.equipmentName}</td>
                    <td>${teachingEquipment.equipmentNumber}</td>
                </tr>
            </c:forEach>
            <c:if test="${empty educationInfo.teachingEquipmentList}">
                <tr>
                    <td colspan="4">无记录</td>
                </tr>
            </c:if>
            <tr>
                <th style="text-align: right;">临床模拟技能训练中心总面积：</th>
                <td style="text-align: left;" colspan="3">${educationInfo.centerArea}平方米</td>
            </tr>
            <tr>
                <td colspan="4" style="text-align: left;">模拟设备种类（列举名称、型号、数量）</td>
            </tr>
            <tr>
                <th style="background-color: #f4f5f9;">序号</th>
                <th style="background-color: #f4f5f9;">设备名称</th>
                <th style="background-color: #f4f5f9;">型号</th>
                <th style="background-color: #f4f5f9;">数量</th>
            </tr>
            <c:forEach var="centerEquipment" items="${educationInfo.centerEquipmentList}" varStatus="status">
                <tr>
                    <td>${status.index + 1}</td>
                    <td>${centerEquipment.equipmentName}</td>
                    <td>${centerEquipment.equipmentModel}</td>
                    <td>${centerEquipment.equipmentNumber}</td>
                </tr>
            </c:forEach>
            <c:if test="${ empty educationInfo.centerEquipmentList}">
                <tr>
                    <td colspan="4">无记录</td>
                </tr>
            </c:if>
            </tbody>
            <tr>
                <th style="text-align: right;">计算机数量：</th>
                <td style="text-align: left;">${educationInfo.numberOfComputer}台</td>
                <th style="text-align: right;">计算机信息检索系统与网络平台：</th>
                <td style="text-align: left;">${educationInfo.hasComputerSystem}</td>
            </tr>
            <tr>
                <td style="text-align: left;" colspan="4">图书馆藏书</td>
            </tr>
            <tr>
                <th style="text-align: right;">种类：</th>
                <td style="text-align: left;">${educationInfo.kindOfBooks}种</td>
                <th style="text-align: right;">
                    数量：
                </th>
                <td style="text-align: left;">${educationInfo.numberOfBooks}万册</td>
            </tr>
        </table>
    </div>
</div>--%>

<%--<div class="div_table">
    <h4 onclick="showTable('gl');">组织管理（住院医师培训组织管理机构成员及职责）</h4>
    <img id="gldown" src="<s:url value='/jsp/jsres/images/down3.png'/>" onclick="showTable('gl');"
         style="float:right;width: 20px;height: 20px;margin-right: 40px;margin-top: -35px"/>
    <img id="glup" src="<s:url value='/jsp/jsres/images/up3.png'/>" onclick="showTable('gl');"
         style="display: none;float:right;width: 20px;height: 20px;margin-right: 40px;margin-top: -35px"/>
            <c:if test="${maintenanceFlag ne true and isglobal ne 'Y' and isJoin ne 'Y'}">
                <img src="<s:url value='/jsp/res/images/test.png'/>"   onclick="editInfo('OrgManage','${sessionScope.currUser.orgFlow}');"
                     style="cursor: pointer;height: 20px;width: 20px;float: right;margin-top: -35px"/>
            </c:if>
    <div id="gl" style="display: none">
        <table border="0" cellspacing="0" cellpadding="0" class="grid">
            <colgroup>
                <col width="10%"/>
                <col width="10%"/>
                <col width="10%"/>
                <col width="10%"/>
                <col width="10%"/>
                <col width="20%"/>
                <col width="10%"/>
                <col width="10%"/>
                <col width="10%"/>
            </colgroup>
            <tr>
                <th style="background-color: #f4f5f9;">姓名</th>
                <th style="background-color: #f4f5f9;">性别</th>
                <th style="background-color: #f4f5f9;">年龄</th>
                <th style="background-color: #f4f5f9;">专业</th>
                <th style="background-color: #f4f5f9;">最高学历</th>
                <th style="background-color: #f4f5f9;">科室</th>
                <th style="background-color: #f4f5f9;">职务</th>
                <th style="background-color: #f4f5f9;">专职/兼职</th>
                <th style="background-color: #f4f5f9;">联系电话</th>
            </tr>
            <c:forEach var="person" items="${organizationManage.organizationPersonList}" varStatus="status">
                <tr>
                    <td>${person.name }</td>
                    <td>${person.sex }</td>
                    <td>${person.age }</td>
                    <td>${person.profession }</td>
                    <td>${person.tallStudy }</td>
                    <td>${person.dept }</td>
                    <td>${person.job }</td>
                    <td>${person.partOrAllJob }</td>
                    <td>${person.telephone }</td>
                </tr>
            </c:forEach>
            <c:if test="${ empty organizationManage.organizationPersonList}">
                <tr>
                    <td colspan="10">无记录</td>
                </tr>
            </c:if>
            <tr>
                <th colspan="9" style="text-align:left;">现有住院医师培训相关规章制度、培训实施计划、考试考核等（请列出具体名称）</th>
            </tr>
            <tr>
                <td colspan="9" style="padding-left: 0px;padding-right: 1px;"><textarea readonly="readonly">${organizationManage.info}</textarea></td>
            </tr>
        </table>
    </div>
</div>--%>
<div class="div_table">
    <h4 onclick="showTable('lb');">附件列表</h4>
    <img id="lbdown" src="<s:url value='/jsp/jsres/images/down3.png'/>" onclick="showTable('lb');"
         style="float:right;width: 20px;height: 20px;margin-right: 40px;margin-top: -35px"/>
    <img id="lbup" src="<s:url value='/jsp/jsres/images/up3.png'/>" onclick="showTable('lb');"
         style="display: none;float:right;width: 20px;height: 20px;margin-right: 40px;margin-top: -35px"/>
    <c:if test="${maintenanceFlag ne true and isglobal ne 'Y' and isJoin ne 'Y' and sessionNumber lt pdfn:getCurrYear()}">
        <img src="<s:url value='/jsp/res/images/test.png'/>"   onclick="editInfo('OrgManage','${sessionScope.currUser.orgFlow}','${sessionNumber}');"
             style="cursor: pointer;height: 20px;width: 20px;float: right;margin-top: -35px"/>
    </c:if>
    <div id="lb" style="display: none">
        <table id="filesTable" border="0" cellpadding="0" cellspacing="0" class="grid" id="table3">

            <c:forEach items="${files}" var="file">
                <c:if test="${file.fileUpType == 'files'}">
                    <tr>
                        <td style="text-align: left;padding-left: 10px;">
                            <a href="<s:url value='/jsres/base/downFile'/>?fileFlow=${file.fileFlow}"
                               target="_blank">${file.fileName}</a>
                        </td>
                    </tr>
                </c:if>
            </c:forEach>
            <c:if test="${empty files}">
                <tr>
                    <td>
                        暂未上传文件
                    </td>
                </tr>
            </c:if>
        </table>
    </div>
</div>
<%--<div class="div_table">
    <h4 onclick="showTable('jg')">培训机构</h4>
    <img id="jgdown" src="<s:url value='/jsp/jsres/images/down3.png'/>" onclick="showTable('jg');"
         style="float:right;width: 20px;height: 20px;margin-right: 40px;margin-top: -35px"/>
    <img id="jgup" src="<s:url value='/jsp/jsres/images/up3.png'/>" onclick="showTable('jg');"
         style="display: none;float:right;width: 20px;height: 20px;margin-right: 40px;margin-top: -35px"/>
    <div id="jg" style="display: none">
        <table border="2px" cellpadding="0" cellspacing="0" class="grid" id="table4">
            <colgroup>
                <col width="30%"/>
                <col width="20%"/>
                <col width="30%"/>
                <col width="20%"/>
            </colgroup>
            <tr>
                <th style="text-align: right;">培训领导小组：</th>
                <td style="text-align: left;">
                    <label><input name="organizationManage.leaderGroup" type="radio"
                                  value="${ organizationManage.leaderGroup}"
                                  <c:if test="${organizationManage.leaderGroup == GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
                    <label><input name="organizationManage.leaderGroup" type="radio"
                                  value="${ organizationManage.leaderGroup}"
                                  <c:if test="${organizationManage.leaderGroup == GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
                </td>
                <th style="text-align: right;">专家委员会：</th>
                <td style="text-align: left;">
                    <label><input name="organizationManage.expertCommittee" type="radio"
                                  value="${ organizationManage.expertCommittee}"
                                  <c:if test="${organizationManage.expertCommittee == GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
                    <label><input name="organizationManage.expertCommittee" type="radio"
                                  value="${ organizationManage.expertCommittee}"
                                  <c:if test="${organizationManage.expertCommittee == GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
                </td>
            </tr>
            <tr>
                <th style="text-align: right;">培训管理职能部门：</th>
                <td style="text-align: left;">
                    <label><input name="organizationManage.managementDepartment" type="radio"
                                  value="${ organizationManage.managementDepartment}"
                                  <c:if test="${organizationManage.managementDepartment == GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
                    <label><input name="organizationManage.managementDepartment" type="radio"
                                  value="${ organizationManage.managementDepartment}"
                                  <c:if test="${organizationManage.managementDepartment == GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
                </td>
                <th style="text-align: right;">专职管理人员：</th>
                <td style="text-align: left;">
                    <label><input name="organizationManage.managementPersonnel" type="radio"
                                  value="${ organizationManage.managementPersonnel}"
                                  <c:if test="${organizationManage.managementPersonnel == GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
                    <label><input name="organizationManage.managementPersonnel" type="radio"
                                  value="${ organizationManage.managementPersonnel}"
                                  <c:if test="${organizationManage.managementPersonnel == GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
                </td>
            </tr>
            <tr>
                <th style="text-align: right;">专业基地管理主任负责制：</th>
                <td style="text-align: left;">
                    <label><input name="organizationManage.directorResponsibility" type="radio"
                                  value="${ organizationManage.directorResponsibility}"
                                  <c:if test="${organizationManage.directorResponsibility == GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
                    <label><input name="organizationManage.directorResponsibility" type="radio"
                                  value="${ organizationManage.directorResponsibility}"
                                  <c:if test="${organizationManage.directorResponsibility == GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
                </td>
                <th style="text-align: right;">专业基地管理专/兼职秘书：</th>
                <td style="text-align: left;">
                    <label><input name="organizationManage.secretary" type="radio"
                                  value="${ organizationManage.secretary}"
                                  <c:if test="${organizationManage.secretary == GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
                    <label><input name="organizationManage.secretary" type="radio"
                                  value="${ organizationManage.secretary}"
                                  <c:if test="${organizationManage.secretary == GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
                </td>
            </tr>
            <tr>
                <th style="text-align: right;">
                    住院医师规范化培训组织管理机构及职责：
                </th>
                <td colspan="3" style="text-align: left;">
                    <label><input name="organizationManage.orgManagement" type="radio"
                                  value="${ organizationManage.orgManagement}"
                                  <c:if test="${organizationManage.orgManagement == GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
                    <label><input name="organizationManage.orgManagement" type="radio"
                                  value="${ organizationManage.orgManagement}"
                                  <c:if test="${organizationManage.orgManagement == GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
                </td>
            </tr>
        </table>
    </div>
</div>
<div class="div_table">
    <h4 onclick="showTable('zd')">培训制度</h4>
    <img id="zddown" src="<s:url value='/jsp/jsres/images/down3.png'/>" onclick="showTable('zd');"
         style="float:right;width: 20px;height: 20px;margin-right: 40px;margin-top: -35px"/>
    <img id="zdup" src="<s:url value='/jsp/jsres/images/up3.png'/>" onclick="showTable('zd');"
         style="display: none;float:right;width: 20px;height: 20px;margin-right: 40px;margin-top: -35px"/>
    <div id="zd" style="display:none;">
        <table border="2px" cellpadding="0" cellspacing="0" class="grid" id="table5">
            <colgroup>
                <col width="20%"/>
                <col width="50%"/>
                <col width="30%"/>
            </colgroup>
            <tr>
                <td colspan="3" style="text-align: left">
                    请提供现有住院医师规范化培训相关规章制度，包括培训管理、培训考核、奖惩制度、人事管理制度、经费管理制度、培训工作会议记录、培训管理职能部门工作记录、培训方案、考核资料、档案、住培工作纳入培训基地(医院)绩效考核体系
                </td>
            </tr>
            <tr>
                <th style="background-color: #f4f5f9;">序号</th>
                <th style="background-color: #f4f5f9;">规章制度名称</th>
                <th style="background-color: #f4f5f9;">上传附件</th>
            </tr>
            <c:forEach var="trainingSystem" items="${organizationManage.organizationTrainingSystemList}"
                       varStatus="status">
                <tr>
                    <td>${status.index+1}</td>
                    <td>
                        <c:set var="file" value="${fileMap[trainingSystem.appendix]}"></c:set>
                        <c:if test="${not empty file}">
                            <a href="<s:url value='/jsres/base/downFile'/>?fileFlow=${file.fileFlow}"
                               target="_blank">${trainingSystem.rulesAndRegulations }</a>
                        </c:if>
                        <c:if test="${empty file}">
                            ${trainingSystem.rulesAndRegulations }
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${not empty file}">已上传</c:if>
                        <c:if test="${empty file}">暂未上传</c:if>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${ empty organizationManage.organizationTrainingSystemList}">
                <tr>
                    <td colspan="10">无记录</td>
                </tr>
            </c:if>
        </table>
    </div>
</div>
<div class="div_table">
    <h4 onclick="showTable('gzqk')">工作情况</h4>
    <img id="gzqkdown" src="<s:url value='/jsp/jsres/images/down3.png'/>" onclick="showTable('gzqk');"
         style="float:right;width: 20px;height: 20px;margin-right: 40px;margin-top: -35px"/>
    <img id="gzqkup" src="<s:url value='/jsp/jsres/images/up3.png'/>" onclick="showTable('gzqk');"
         style="display: none;float:right;width: 20px;height: 20px;margin-right: 40px;margin-top: -35px"/>
    <div id="gzqk" style="display: none">
        <table border="2px" cellpadding="0" cellspacing="0" class="grid" id="table6">
            <colgroup>
                <col width="20%"/>
                <col width="50%"/>
                <col width="30%"/>
            </colgroup>
            <tr>
                <td colspan="3" style="text-align: left">近三年住院医师培训工作计划、总结、培训名单、人数及考核成绩，本院住院医师培训率，接收外单位培训任务。</td>
            </tr>
            <tr>
                <th style="background-color: #f4f5f9;">序号</th>
                <th style="background-color: #f4f5f9;">文件名称</th>
                <th style="background-color: #f4f5f9;">上传附件</th>
            </tr>
            <c:forEach var="workCondition" items="${organizationManage.organizationWorkConditionList}"
                       varStatus="status">
                <tr>
                    <td>${status.index+1}</td>
                    <td>
                        <c:set var="file" value="${fileMap[workCondition.appendix]}"></c:set>
                        <c:if test="${not empty file}">
                            <a href="<s:url value='/jsres/base/downFile'/>?fileFlow=${file.fileFlow}"
                               target="_blank">${workCondition.fileName }</a>
                        </c:if>
                        <c:if test="${empty file}">
                            ${workCondition.fileName }
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${not empty file}">已上传</c:if>
                        <c:if test="${empty file}">暂未上传</c:if>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${ empty organizationManage.organizationWorkConditionList}">
                <tr>
                    <td colspan="10">无记录</td>
                </tr>
            </c:if>
        </table>
    </div>
</div>
<div class="div_table">
    <h4 onclick="showTable('jy')">住院医师规范化培训工作经验</h4>

    <img id="jydown" src="<s:url value='/jsp/jsres/images/down3.png'/>" onclick="showTable('jy');"
         style="float:right;width: 20px;height: 20px;margin-right: 40px;margin-top: -35px"/>
    <img id="jyup" src="<s:url value='/jsp/jsres/images/up3.png'/>" onclick="showTable('jy');"
         style="display: none;float:right;width: 20px;height: 20px;margin-right: 40px;margin-top: -35px"/>
    <div id="jy" style="display: none">
        <table border="2px" cellpadding="0" cellspacing="0" class="grid" id="table7">
            <colgroup>
                <col width="30%"/>
                <col width="20%"/>
                <col width="30%"/>
                <col width="20%"/>
            </colgroup>
            <tr>
                <td style="text-align: left" colspan="4">不同人员对住院医师培训工作效果的评价与反馈意见</td>
            </tr>
            <tr>
                <th style="text-align: right;">培训对象：</th>
                <td style="text-align: left;">
                    <label><input name="organizationManage.trainees" type="radio"
                                  value="${ organizationManage.trainees}"
                                  <c:if test="${organizationManage.trainees ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
                    <label><input name="organizationManage.trainees" type="radio"
                                  value="${ organizationManage.trainees}"
                                  <c:if test="${organizationManage.trainees ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
                </td>
                <th style="text-align: right;">指导医师：</th>
                <td style="text-align: left;">
                    <label><input name="organizationManage.instructor" type="radio"
                                  value="${ organizationManage.instructor}"
                                  <c:if test="${organizationManage.instructor ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
                    <label><input name="organizationManage.instructor" type="radio"
                                  value="${ organizationManage.instructor}"
                                  <c:if test="${organizationManage.instructor ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
                </td>
            </tr>
            <tr>
                <th style="text-align: right;">专业基地：</th>
                <td style="text-align: left;">
                    <label><input name="organizationManage.professionalBase" type="radio"
                                  value="${ organizationManage.professionalBase}"
                                  <c:if test="${organizationManage.professionalBase ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
                    <label><input name="organizationManage.professionalBase" type="radio"
                                  value="${ organizationManage.professionalBase}"
                                  <c:if test="${organizationManage.professionalBase ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
                </td>
                <th style="text-align: right;">用人单位：</th>
                <td style="text-align: left;">
                    <label><input name="organizationManage.employer" type="radio"
                                  value="${ organizationManage.employer}"
                                  <c:if test="${organizationManage.employer ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
                    <label><input name="organizationManage.employer" type="radio"
                                  value="${ organizationManage.employer}"
                                  <c:if test="${organizationManage.employer ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
                </td>
            </tr>
            <tr>
                <td style="text-align: left" colspan="4">不同人员对指导医师带教质量的评价与反馈意见</td>
            </tr>
            <tr>
                <th style="text-align: right;">培训对象：</th>
                <td style="text-align: left;">
                    <label><input name="organizationManage.trainees1" type="radio"
                                  value="${ organizationManage.trainees1}"
                                  <c:if test="${organizationManage.trainees1 ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
                    <label><input name="organizationManage.trainees1" type="radio"
                                  value="${ organizationManage.trainees1}"
                                  <c:if test="${organizationManage.trainees1 ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
                </td>
                <th style="text-align: right;">上级部门：</th>
                <td style="text-align: left;">
                    <label><input name="organizationManage.superiorDepartment" type="radio"
                                  value="${ organizationManage.superiorDepartment}"
                                  <c:if test="${organizationManage.superiorDepartment ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
                    <label><input name="organizationManage.superiorDepartment" type="radio"
                                  value="${ organizationManage.superiorDepartment}"
                                  <c:if test="${organizationManage.superiorDepartment ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
                </td>
            </tr>
            <tr>
                <th style="text-align: right;">同行：</th>
                <td colspan="3" style="text-align: left;">
                    <label><input name="organizationManage.peer" type="radio" value="${ organizationManage.peer}"
                                  <c:if test="${organizationManage.peer ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
                    <label><input name="organizationManage.peer" type="radio" value="${ organizationManage.peer}"
                                  <c:if test="${organizationManage.peer ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
                </td>
            </tr>
        </table>
    </div>
</div>
<div class="div_table">
    <h4 onclick="showTable('qt')">其他相关措施</h4>
    <img id="qtdown" src="<s:url value='/jsp/jsres/images/down3.png'/>" onclick="showTable('qt');"
         style="float:right;width: 20px;height: 20px;margin-right: 40px;margin-top: -35px"/>
    <img id="qtup" src="<s:url value='/jsp/jsres/images/up3.png'/>" onclick="showTable('qt');"
         style="display: none;float:right;width: 20px;height: 20px;margin-right: 40px;margin-top: -35px"/>
    <div id="qt" style="display: none">
        <table border="2px" cellpadding="0" cellspacing="0" class="grid" id="table8">
            <colgroup>
                <col width="30%"/>
                <col width="70%"/>
            </colgroup>
            <tr>
                <th style="text-align: right;">培训基地能够提供给培训对象的生活补助：</th>
                <td style="text-align: left;">
                    <label><input name="organizationManage.livingAllowance" type="radio"
                                  value="${ organizationManage.livingAllowance}"
                                  <c:if test="${organizationManage.livingAllowance ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
                    <label><input name="organizationManage.livingAllowance" type="radio"
                                  value="${ organizationManage.livingAllowance}"
                                  <c:if test="${organizationManage.livingAllowance ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
                    &#12288;（金额：${organizationManage.allowanceMoney}元/人/月）
                </td>
            </tr>
            <tr>
                <th style="text-align: right;">与培训对象签订培训协议：</th>
                <td style="text-align: left;">
                    <label><input name="organizationManage.trainingAgreement" type="radio"
                                  value="${ organizationManage.trainingAgreement}"
                                  <c:if test="${organizationManage.trainingAgreement ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
                    <label><input name="organizationManage.trainingAgreement" type="radio"
                                  value="${ organizationManage.trainingAgreement}"
                                  <c:if test="${organizationManage.trainingAgreement ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
                </td>
            </tr>
            <tr>
                <th style="text-align: right;">协助解决招收社会学员的档案和工龄：</th>
                <td style="text-align: left;">
                    <label><input name="organizationManage.archivesAndAge" type="radio"
                                  value="${ organizationManage.archivesAndAge}"
                                  <c:if test="${organizationManage.archivesAndAge ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
                    <label><input name="organizationManage.archivesAndAge" type="radio"
                                  value="${ organizationManage.archivesAndAge}"
                                  <c:if test="${organizationManage.archivesAndAge ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
                </td>
            </tr>
            <tr>
                <th style="text-align: right;">协助解决招收社会学员的社会保障：</th>
                <td style="text-align: left;">
                    <label><input name="organizationManage.socialSecurity" type="radio"
                                  value="${ organizationManage.socialSecurity}"
                                  <c:if test="${organizationManage.socialSecurity ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
                    <label><input name="organizationManage.socialSecurity" type="radio"
                                  value="${ organizationManage.socialSecurity}"
                                  <c:if test="${organizationManage.socialSecurity ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
                </td>
            </tr>
            <tr>
                <th style="text-align: right;">解决培训对象住宿：</th>
                <td style="text-align: left;">
                    <label><input name="organizationManage.stay" type="radio" value="${ organizationManage.stay}"
                                  <c:if test="${organizationManage.stay ==GlobalConstant.SOLVE_ALL }">checked="checked"</c:if> />&#12288;全部解决&#12288;</label>
                    <label><input name="organizationManage.stay" type="radio" value="${ organizationManage.stay}"
                                  <c:if test="${organizationManage.stay ==GlobalConstant.SOLVE_PART }">checked="checked"</c:if> />&#12288;部分解决&#12288;</label>
                    <label><input name="organizationManage.stay" type="radio" value="${ organizationManage.stay}"
                                  <c:if test="${organizationManage.stay ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
                </td>
            </tr>
            <tr>
                <th style="text-align: right;">协助解决培训对象的医师资格考试和执业注册：</th>
                <td style="text-align: left;">
                    <label><input name="organizationManage.registrationForPractitioners" type="radio"
                                  value="${ organizationManage.registrationForPractitioners}"
                                  <c:if test="${organizationManage.registrationForPractitioners ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
                    <label><input name="organizationManage.registrationForPractitioners" type="radio"
                                  value="${ organizationManage.registrationForPractitioners}"
                                  <c:if test="${organizationManage.registrationForPractitioners ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
                </td>
            </tr>
        </table>
    </div>
</div>

<div class="div_table">
    <h4 onclick="showTable('zztj')">支撑条件（在是或否栏内选中）</h4>
    <img id="zztjdown" src="<s:url value='/jsp/jsres/images/down3.png'/>" onclick="showTable('zztj');"
         style="float:right;width: 20px;height: 20px;margin-right: 40px;margin-top: -35px"/>
    <img id="zztjup" src="<s:url value='/jsp/jsres/images/up3.png'/>" onclick="showTable('zztj');"
         style="display: none;float:right;width: 20px;height: 20px;margin-right: 40px;margin-top: -35px"/>

    &lt;%&ndash;        <c:if test="${!maintenanceFlag and isglobal ne 'Y' and isJoin ne 'Y'}">&ndash;%&gt;
    &lt;%&ndash;            <img src="<s:url value='/jsp/res/images/test.png'/>"   onclick="editInfo('SupportCondition','${sessionScope.currUser.orgFlow}');"&ndash;%&gt;
    &lt;%&ndash;                 style="cursor: pointer;height: 20px;width: 20px;float: right;margin-top: -35px"/>&ndash;%&gt;
    &lt;%&ndash;        </c:if>&ndash;%&gt;
    <div id="zztj" style="display: none">
        <table border="0" cellpadding="0" cellspacing="0" class="grid" id="supportContent">
            <colgroup>
                <col width="30%"/>
                <col width="8%"/>
                <col width="8%"/>
                <col width="20%"/>
                <col width="30%"/>
            </colgroup>
            <tbody>
            <tr>
                <th style="background-color: #f4f5f9;" style="text-align:left;">项目内容</th>
                <th style="background-color: #f4f5f9;">是</th>
                <th style="background-color: #f4f5f9;">否</th>
                <th style="background-color: #f4f5f9;" style="text-align:left;">划“是”者请填写具体数值或措施</th>
                <th style="background-color: #f4f5f9;">附件</th>
            </tr>
            <tr>
                <td style="text-align:left;">能提供用于基地建设和管理经费</td>
                <td><label><input name="supportCondition.buildAndMang" type="radio"
                                  value="${ supportCondition.buildAndMang}"
                                  <c:if test="${supportCondition.buildAndMang ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />
                </label></td>
                <td><label><input name="supportCondition.buildAndMang" type="radio"
                                  value="${ supportCondition.buildAndMang}"
                                  <c:if test="${supportCondition.buildAndMang ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />
                </label></td>
                <td style="text-align:left;">
                    <div id="buildAndManginput"
                         style="display:${ supportCondition.buildAndMang == GlobalConstant.FLAG_Y?'':'none'}"><span
                            style="height:20px;width:250px ">${supportCondition.buildAndManginfo}</span></div>
                </td>
                <td>
                    <c:set var="file" value="${fileMap['buildAndMangFile']}"></c:set>
                    <c:if test="${empty file}">

                    </c:if>
                    <c:if test="${not empty file}">


                        <a href="<s:url value='/jsres/base/downFile'/>?fileFlow=${file.fileFlow}"
                           target="_blank">${file.fileName}</a>

                    </c:if>
                </td>
            </tr>
            <tr>
                <td style="text-align:left;">能否解决住院医师住宿</td>
                <td><label><input name="supportCondition.giveLive" type="radio"
                                  value="${supportCondition.giveLive }"
                                  <c:if test="${supportCondition.giveLive ==GlobalConstant.FLAG_Y }">checked="checked"</c:if>/>
                </label></td>
                <td><label><input name="supportCondition.giveLive" type="radio"
                                  value="${supportCondition.giveLive }"
                                  <c:if test="${supportCondition.giveLive ==GlobalConstant.FLAG_N }">checked="checked"</c:if>/>
                </label></td>
                <td style="text-align:left;">
                    <div style="display:${supportCondition.giveLive == GlobalConstant.FLAG_Y?'':'none'}"
                         id="giveLiveinput"><span
                            style="height:20px;width:250px ">${supportCondition.giveLiveinfo}</span></div>
                </td>
                <td>
                    <c:set var="file" value="${fileMap['giveLiveFile']}"></c:set>
                    <c:if test="${empty file}">

                    </c:if>
                    <c:if test="${not empty file}">


                        <a href="<s:url value='/jsres/base/downFile'/>?fileFlow=${file.fileFlow}"
                           target="_blank">${file.fileName}</a>

                    </c:if>
                </td>
            </tr>
            <tr>
                <td style="text-align:left;">是否能解决住院医师工资及补贴</td>
                <td><label><input name="supportCondition.wageAndTip" type="radio"
                                  value="${supportCondition.wageAndTip }"
                                  <c:if test="${supportCondition.wageAndTip==GlobalConstant.FLAG_Y }">checked="checked"</c:if>/>
                </label></td>
                <td><label><input name="supportCondition.wageAndTip" type="radio"
                                  value="${supportCondition.wageAndTip }"
                                  <c:if test="${supportCondition.wageAndTip==GlobalConstant.FLAG_N }">checked="checked"</c:if>/>
                </label></td>
                <td style="text-align:left;">
                    <div style="display:${ supportCondition.wageAndTip == GlobalConstant.FLAG_Y?'':'none'}"
                         id="wageAndTipinput"><span
                            style="height:20px;width:250px ">${supportCondition.wageAndTipinfo}</span></div>
                </td>
                <td>
                    <c:set var="file" value="${fileMap['wageAndTipFile']}"></c:set>
                    <c:if test="${empty file}">

                    </c:if>
                    <c:if test="${not empty file}">


                        <a href="<s:url value='/jsres/base/downFile'/>?fileFlow=${file.fileFlow}"
                           target="_blank">${file.fileName}</a>

                    </c:if>
                </td>
            </tr>
            <tr>
                <td style="text-align:left;">是否能解决住院医师的社会保障</td>
                <td><label><input name="supportCondition.socialSecurity" type="radio"
                                  value="${supportCondition.socialSecurity }"
                                  <c:if test="${supportCondition.socialSecurity ==GlobalConstant.FLAG_Y }">checked="checked"</c:if>/>
                </label></td>
                <td><label><input name="supportCondition.socialSecurity" type="radio"
                                  value="${ supportCondition.socialSecurity}"
                                  <c:if test="${supportCondition.socialSecurity ==GlobalConstant.FLAG_N}">checked="checked"</c:if>/>
                </label></td>
                <td style="text-align:left;">
                    <div style="display:${ supportCondition.socialSecurity == GlobalConstant.FLAG_Y?'':'none'}"
                         id="socialSecurityinput"><span
                            style="height:20px;width:250px ">${supportCondition.socialSecurityinfo}</span></div>
                </td>
                <td>
                    <c:set var="file" value="${fileMap['socialSecurityFile']}"></c:set>
                    <c:if test="${empty file}">

                    </c:if>
                    <c:if test="${not empty file}">


                        <a href="<s:url value='/jsres/base/downFile'/>?fileFlow=${file.fileFlow}"
                           target="_blank">${file.fileName}</a>

                    </c:if>
                </td>
            </tr>
            <tr>
                <td style="text-align:left;">能否解决住院医师的人事档案和工龄</td>
                <td><label><input name="supportCondition.recordAndStanding" type="radio"
                                  value="${ supportCondition.recordAndStanding}"
                                  <c:if test="${supportCondition.recordAndStanding ==GlobalConstant.FLAG_Y }">checked="checked"</c:if>/>
                </label></td>
                <td><label><input name="supportCondition.recordAndStanding" type="radio"
                                  value="${supportCondition.recordAndStanding }"
                                  <c:if test="${supportCondition.recordAndStanding ==GlobalConstant.FLAG_N }">checked="checked"</c:if>/>
                </label></td>
                <td style="text-align:left;">
                    <div style="display:${ supportCondition.recordAndStanding == GlobalConstant.FLAG_Y?'':'none'}"
                         id="recordAndStandinginput"><span
                            style="height:20px;width:250px ">${supportCondition.recordAndStandinginfo}</span></div>
                </td>
                <td>
                    <c:set var="file" value="${fileMap['recordAndStandingFile']}"></c:set>
                    <c:if test="${empty file}">

                    </c:if>
                    <c:if test="${not empty file}">


                        <a href="<s:url value='/jsres/base/downFile'/>?fileFlow=${file.fileFlow}"
                           target="_blank">${file.fileName}</a>

                    </c:if>
                </td>
            </tr>
            <tr>
                <td style="text-align:left;">能否解决住院医师的医师资格和注册管理</td>
                <td><label><input name="supportCondition.qualifiAndregis" type="radio"
                                  value="${supportCondition.qualifiAndregis }"
                                  <c:if test="${supportCondition.qualifiAndregis ==GlobalConstant.FLAG_Y }">checked="checked"</c:if>/>
                </label></td>
                <td><label><input name="supportCondition.qualifiAndregis" type="radio"
                                  value="${supportCondition.qualifiAndregis }"
                                  <c:if test="${supportCondition.qualifiAndregis ==GlobalConstant.FLAG_N }">checked="checked"</c:if>/>
                </label></td>
                <td style="text-align:left;">
                    <div style="display:${ supportCondition.qualifiAndregis == GlobalConstant.FLAG_Y?'':'none'}"
                         id="qualifiAndregisinput"><span
                            style="height:20px;width:250px ">${supportCondition.qualifiAndregisinfo}</span></div>
                </td>
                <td>
                    <c:set var="file" value="${fileMap['qualifiAndregisFile']}"></c:set>
                    <c:if test="${empty file}">

                    </c:if>
                    <c:if test="${not empty file}">


                        <a href="<s:url value='/jsres/base/downFile'/>?fileFlow=${file.fileFlow}"
                           target="_blank">${file.fileName}</a>

                    </c:if>
                </td>
            </tr>
            <tr>
                <td style="text-align:left;">能否与培训对象签订3年以上聘用合同</td>
                <td><label><input name="supportCondition.threeYearContract" type="radio"
                                  value="${supportCondition.threeYearContract }"
                                  <c:if test="${supportCondition.threeYearContract==GlobalConstant.FLAG_Y}">checked="checked"</c:if>/>
                </label></td>
                <td><label><input name="supportCondition.threeYearContract" type="radio"
                                  value="${supportCondition.threeYearContract }"
                                  <c:if test="${supportCondition.threeYearContract==GlobalConstant.FLAG_N}">checked="checked"</c:if>/>
                </label></td>
                <td style="text-align:left;">
                    <div style="display:${ supportCondition.threeYearContract  == GlobalConstant.FLAG_Y?'':'none'}"
                         id="threeYearContractinput"><span
                            style="height:20px;width:250px ">${supportCondition.threeYearContractinfo}</span></div>
                </td>
                <td>
                    <c:set var="file" value="${fileMap['threeYearContractFile']}"></c:set>
                    <c:if test="${empty file}">

                    </c:if>
                    <c:if test="${not empty file}">


                        <a href="<s:url value='/jsres/base/downFile'/>?fileFlow=${file.fileFlow}"
                           target="_blank">${file.fileName}</a>

                    </c:if>
                </td>
            </tr>
            <tr>
                <td style="text-align:left;">能否保证大多数住院医师分流去基层服务</td>
                <td><label><input name="supportCondition.goBasicServe" type="radio"
                                  value="${supportCondition.goBasicServe }"
                                  <c:if test="${supportCondition.goBasicServe ==GlobalConstant.FLAG_Y }">checked="checked"</c:if>/>
                </label></td>
                <td><label><input name="supportCondition.goBasicServe" type="radio"
                                  value="${supportCondition.goBasicServe }"
                                  <c:if test="${supportCondition.goBasicServe ==GlobalConstant.FLAG_N }">checked="checked"</c:if>/>
                </label></td>
                <td style="text-align:left;">
                    <div style="display:${ supportCondition.goBasicServe  == GlobalConstant.FLAG_Y?'':'none'}"
                         id="goBasicServeinput"><span
                            style="height:20px;width:250px ">${supportCondition.goBasicServeinfo}</span></div>
                </td>
                <td>
                    <c:set var="file" value="${fileMap['goBasicServeFile']}"></c:set>
                    <c:if test="${not empty file}">


                        <a href="<s:url value='/jsres/base/downFile'/>?fileFlow=${file.fileFlow}"
                           target="_blank">${file.fileName}</a>

                    </c:if>
                </td>
            </tr>
            <tr>
                <td style="text-align:left;">已开展住院医师规范化培训工作的经验</td>
                <td><label><input name="supportCondition.openTraning" type="radio"
                                  value="${supportCondition.openTraning }"
                                  <c:if test="${supportCondition.openTraning==GlobalConstant.FLAG_Y }">checked="checked"</c:if>/>
                </label></td>
                <td><label><input name="supportCondition.openTraning" type="radio"
                                  value="${supportCondition.openTraning }"
                                  <c:if test="${supportCondition.openTraning==GlobalConstant.FLAG_N }">checked="checked"</c:if>/>
                </label></td>
                <td style="text-align:left;">
                    <div style="display:${ supportCondition.openTraning == GlobalConstant.FLAG_Y?'':'none'}"
                         id="openTraninginput"><span
                            style="height:20px;width:250px ">${supportCondition.openTraninginfo}</span></div>
                </td>
                <td>
                    <c:set var="file" value="${fileMap['openTraningFile']}"></c:set>
                    <c:if test="${not empty file}">


                        <a href="<s:url value='/jsres/base/downFile'/>?fileFlow=${file.fileFlow}"
                           target="_blank">${file.fileName}</a>

                    </c:if>
                </td>
            </tr>
            <tr>
                <td style="text-align:left;">是否建立了临床技能训练中心</td>
                <td><label><input name="supportCondition.skillTrain" type="radio"
                                  value="${supportCondition.skillTrain }"
                                  <c:if test="${supportCondition.skillTrain==GlobalConstant.FLAG_Y }">checked="checked"</c:if>/>
                </label></td>
                <td><label><input name="supportCondition.skillTrain" type="radio"
                                  value="${supportCondition.skillTrain }"
                                  <c:if test="${supportCondition.skillTrain==GlobalConstant.FLAG_N }">checked="checked"</c:if>/>
                </label></td>
                <td style="text-align:left;">
                    <div style="display:${ supportCondition.skillTrain == GlobalConstant.FLAG_Y?'':'none'}"
                         id="skillTraininput"><span
                            style="height:20px;width:250px ">${supportCondition.skillTraininfo}</span></div>
                </td>
                <td>
                    <c:set var="file" value="${fileMap['skillTrainFile']}"></c:set>
                    <c:if test="${not empty file}">


                        <a href="<s:url value='/jsres/base/downFile'/>?fileFlow=${file.fileFlow}"
                           target="_blank">${file.fileName}</a>

                    </c:if>
                </td>
            </tr>
            <tr>
                <td style="text-align:left;">注陪合格资格与主治医师报考、聘任挂钩</td>
                <td><label><input name="supportCondition.zpqualification" type="radio"
                                  value="${supportCondition.zpqualification }"
                                  <c:if test="${supportCondition.zpqualification==GlobalConstant.FLAG_Y }">checked="checked"</c:if>/>
                </label></td>
                <td><label><input name="supportCondition.zpqualification" type="radio"
                                  value="${supportCondition.zpqualification }"
                                  <c:if test="${supportCondition.zpqualification==GlobalConstant.FLAG_N }">checked="checked"</c:if>/>
                </label></td>
                <td style="text-align:left;">
                    <div style="display:${ supportCondition.zpqualification  == GlobalConstant.FLAG_Y?'':'none'}"
                         id="zpqualificationinput"><span
                            style="height:20px;width:250px ">${supportCondition.zpqualificationinfo}</span></div>
                </td>
                <td>
                    <c:set var="file" value="${fileMap['zpqualificationFile']}"></c:set>
                    <c:if test="${not empty file}">
                        <a href="<s:url value='/jsres/base/downFile'/>?fileFlow=${file.fileFlow}"
                           target="_blank">${file.fileName}</a>
                    </c:if>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
--%>
</body>
</html>
