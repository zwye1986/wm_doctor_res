<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
    </jsp:include>
    <script type="text/javascript"
            src="<s:url value='/js/DatePicker/WdatePicker.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <style type="text/css">
        td {
            text-align: left
        }

        textarea {
            width: 90%;
            height: 100px;
            max-height: 100px;
            max-width: 96%;
            margin: 5px 0;
        }
    </style>
    <script type="text/javascript">

        function localAuditShow() {
            $("#localAudit").show();
        }

        function localAuditHide() {
            $("#localAudit").hide();
        }

        function chargeAuditShow() {
            $("#chargeAudit").show();
        }

        function chargeAuditHide() {
            $("#chargeAudit").hide();
        }

        function checkApplyStart() {
            if ($("#applyEndTime").val()) {
                if ($("#applyStartTime").val() > $("#applyEndTime").val()) {
                    jboxTip("报名开始时间不能大于结束时间")
                }
            }
        }

        function checkApplyEnd() {
            if ($("#applyStartTime").val()) {
                if ($("#applyStartTime").val() > $("#applyEndTime").val()) {
                    jboxTip("报名开始时间不能大于结束时间")
                }
            }
        }

        function checkLocalStart() {
            if ($("#localAuditEndTime").val()) {
                if ($("#localAuditStartTime").val() > $("#localAuditEndTime").val()) {
                    jboxTip("基地审核开始时间不能大于结束时间")
                }
            }
        }

        function checkLocalEnd() {
            if ($("#localAuditStartTime").val()) {
                if ($("#localAuditStartTime").val() > $("#localAuditEndTime").val()) {
                    jboxTip("基地审核开始时间不能大于结束时间")
                }
            }
        }

        function checkChargeStart() {
            if ($("#chargeAuditEndTime").val()) {
                if ($("#chargeAuditStartTime").val() > $("#chargeAuditEndTime").val()) {
                    jboxTip("市局审核开始时间不能大于结束时间")
                }
            }
        }

        function checkChargeEnd() {
            if ($("#chargeAuditStartTime").val()) {
                if ($("#chargeAuditStartTime").val() > $("#chargeAuditEndTime").val()) {
                    jboxTip("市局审核开始时间不能大于结束时间")
                }
            }
        }

        function checkSkillStart() {
            if ($("#skillEndTime").val()) {
                if ($("#skillStartTime").val() > $("#skillEndTime").val()) {
                    jboxTip("技能考核开始时间不能大于结束时间")
                }
            }
        }

        function checkSkillEnd() {
            if ($("#skillStartTime").val()) {
                if ($("#skillStartTime").val() > $("#skillEndTime").val()) {
                    jboxTip("技能考核开始时间不能大于结束时间")
                }
            }
        }

        function checkTestEnd() {
            if ($("#applyEndTime").val()) {
                if ($("#applyEndTime").val() > $("#testEndTime").val()) {
                    jboxTip("考试结束时间不能小于报名结束时间")
                }
            } else {
                jboxTip("请先填写报名结束时间")
            }
        }

        function save(skillTimeFlow, flag) {
            if (flag == 'N') {
                jboxTip("已结束或者已关闭的考试不能编辑");
                return false;
            }
            if (false == $("#saveCfgForm").validationEngine("validate")) {
                return false;
            }
            if ($("#applyStartTime").val() > $("#applyEndTime").val()) {
                jboxTip("报名开始时间不能大于结束时间");
                return false;
            }
            if ($("#localAuditStartTime").val() > $("#localAuditEndTime").val()) {
                jboxTip("基地审核开始时间不能大于结束时间");
                return false;
            }
            if ($("#chargeAuditStartTime").val() > $("#chargeAuditEndTime").val()) {
                jboxTip("市局审核开始时间不能大于结束时间");
                return false;
            }
            if ($("#skillStartTime").val() > $("#skillEndTime").val()) {
                jboxTip("技能考核开始时间不能大于结束时间");
                return false;
            }
            if ($("#applyEndTime").val() > $("#testEndTime").val()) {
                jboxTip("考试结束时间不能小于报名结束时间");
                return false;
            }
            if ($("#localAuditEndTime").val() != "") {
                if ($("#localAuditEndTime").val() > $("#testEndTime").val()) {
                    jboxTip("考试结束时间不能小于基地审核时间");
                    return false;
                }
            }
            if ($("#chargeAuditEndTime").val() != "") {
                if ($("#chargeAuditEndTime").val() > $("#testEndTime").val()) {
                    jboxTip("考试结束时间不能小于市局审核时间");
                    return false;
                }
            }
            if ($("#skillEndTime").val() != "") {
                if ($("#skillEndTime").val() > $("#testEndTime").val()) {
                    jboxTip("考试结束时间不能小于技能考核时间");
                    return false;
                }
            }
            if ($("#localAuditEndTime").val() != "" && $("#chargeAuditEndTime").val() != "") {
                if ($("#localAuditEndTime").val() > $("#chargeAuditEndTime").val()) {
                    jboxTip("市局审核的结束时间不能小于基地审核的结束时间");
                    return false;
                }
            }
            jboxPost("<s:url value='/jsres/skillTimeConfig/insertTest'/>?skillTimeFlow=" + skillTimeFlow, $("#saveCfgForm").serialize(), function (resp) {
                jboxTip(resp);
                if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
                    setTimeout(
                        function () {
                            window.parent.search();
                            jboxClose();
                        }, 2000
                    );
                }
            }, null, false)
        }
    </script>
</head>
<body>
<div class="mainright" style="max-height: 700px; padding: 0px 20px;" id="content">
    <form id="saveCfgForm" style="position: relative;">
        <table class="grid" style="width:100%; margin-bottom: 10px; margin-top: 30px;">
            <tr>
                <td colspan="2" style="text-align: center;width: 180px"> 考试名称<font color="red">*</font></td>
                <td colspan="2" style="text-align: left;width: 620px">
                    <input name="testName"
                           value="${resSkillTimeConfig.testName}" class="input validate[required]"
                           <c:if test="${not empty flag}">readonly="readonly"</c:if>
                    >
                </td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: center"> 报名时间<font color="red">*</font></td>
                <td colspan="2" style="text-align: left">
                    <input name="applyStartTime" id="applyStartTime"
                    <c:if test="${empty flag}"> onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"</c:if>
                           onchange="checkApplyStart()"
                           class="input validate[required]"
                           value="${resSkillTimeConfig.applyStartTime}"
                           <c:if test="${not empty flag}">readonly="readonly"</c:if>>&nbsp;&nbsp;至&nbsp;&nbsp;
                    <input name="applyEndTime" id="applyEndTime"
                    <c:if test="${empty flag}"> onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"</c:if>
                           onchange="checkApplyEnd()"
                           class="input validate[required]"
                           value="${resSkillTimeConfig.applyEndTime}"
                           <c:if test="${not empty flag}">readonly="readonly"</c:if>>
                </td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: center"> 地市<font color="red">*</font></td>
                <td colspan="2" style="text-align: left">
                    <c:forEach items="${citys}" var="city">
                        <input type="checkbox" name="citysId" class="validate[required]" value="${city.cityId}"
                               <c:if test="${pdfn:contain(city.cityId, cityList) or empty cityList}">checked</c:if>
                        <c:if test="${not empty flag}"> onclick="return false"</c:if>
                        ><font
                            style="font-size: 15px"
                    >${city.cityName}</font>
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: center"> 基地审核时间<font color="red">*</font></td>
                <td colspan="2" style="text-align: left; align-items: center;">
                    <input type="radio" name="localAudit" class="validate[required]" value="Y"
                           <c:if test="${resSkillTimeConfig.localAudit eq 'Y'}">checked</c:if>
                           onclick="localAuditShow()"
                           <c:if test="${not empty flag}">disabled</c:if>>开通
                    <input type="radio" name="localAudit" class="validate[required]" value="N"
                           <c:if test="${resSkillTimeConfig.localAudit eq 'N'}">checked</c:if>
                           onclick="localAuditHide()"
                           <c:if test="${not empty flag}">disabled</c:if>>关闭
                    <span
                            <c:if test="${resSkillTimeConfig.localAudit eq 'N'}">style="display: none"</c:if>
                            <c:if test="${ empty resSkillTimeConfig.localAudit }">style="display: none"</c:if>
                            id="localAudit">
                        <input name="localAuditStartTime" id="localAuditStartTime"
                        <c:if test="${empty flag}"> onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"</c:if>
                               onchange="checkLocalStart()"
                               value="${resSkillTimeConfig.localAuditStartTime}" class="input validate[required]"
                               <c:if test="${not empty flag}">readonly="readonly"</c:if>> &nbsp;&nbsp;至&nbsp;&nbsp;
                        <input name="localAuditEndTime" id="localAuditEndTime"
                        <c:if test="${empty flag}"> onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"</c:if>
                               onchange="checkLocalEnd()"
                               value="${resSkillTimeConfig.localAuditEndTime}" class="input validate[required]"
                               <c:if test="${not empty flag}">readonly="readonly"</c:if> >
                            </span>
                </td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: center"> 市局审核时间<font color="red">*</font></td>
                <td colspan="2" style="text-align: left;  align-items: center;">
                    <input type="radio" name="chargeAudit" value="Y"
                           <c:if test="${resSkillTimeConfig.chargeAudit eq 'Y'}">checked</c:if>
                           onclick="chargeAuditShow()"
                           <c:if test="${not empty flag}">disabled</c:if>>开通
                    <input type="radio" name="chargeAudit" value="N"
                           <c:if test="${resSkillTimeConfig.chargeAudit eq 'N'}">checked</c:if>
                           onclick="chargeAuditHide()"
                           <c:if test="${not empty flag}">disabled</c:if>>关闭
                    <span   <c:if test="${resSkillTimeConfig.chargeAudit eq 'N'}"> style="display: none"</c:if>
                            <c:if test="${empty resSkillTimeConfig.chargeAudit }"> style="display: none"</c:if>
                            id="chargeAudit">
                        <input name="chargeAuditStartTime" id="chargeAuditStartTime"
                        <c:if test="${empty flag}"> onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"</c:if>
                               onchange="checkChargeStart()"
                               value="${resSkillTimeConfig.chargeAuditStartTime}" class="input validate[required]"
                               <c:if test="${not empty flag}">readonly="readonly"</c:if> >&nbsp;&nbsp;至&nbsp;&nbsp;
                        <input name="chargeAuditEndTime" id="chargeAuditEndTime"
                        <c:if test="${empty flag}"> onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"</c:if>
                               onchange="checkChargeEnd()"
                               value="${resSkillTimeConfig.chargeAuditEndTime}" class="input validate[required]"
                               <c:if test="${not empty flag}">readonly="readonly"</c:if>>
                            </span>
                </td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: center"> 技能考核时间<font color="red">*</font></td>
                <td colspan="2" style="text-align: left">
                    <input name="skillStartTime" id="skillStartTime"
                    <c:if test="${empty flag}"> onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"</c:if>
                           onchange="checkSkillStart()"
                           class="input validate[required]"
                           value="${resSkillTimeConfig.skillStartTime}"
                           <c:if test="${not empty flag}">readonly="readonly"</c:if>>&nbsp;&nbsp;至&nbsp;&nbsp;
                    <input name="skillEndTime" id="skillEndTime"
                    <c:if test="${empty flag}"> onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"</c:if>
                           onchange="checkSkillEnd()"
                           class="input validate[required]"
                           value="${resSkillTimeConfig.skillEndTime}"
                           <c:if test="${not empty flag}">readonly="readonly"</c:if>>
                </td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: center"> 考试结束时间<font color="red">*</font></td>
                <td colspan="2" style="text-align: left">
                    <input name="testEndTime" id="testEndTime"
                    <c:if test="${empty flag}"> onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"</c:if>
                           onchange="checkTestEnd()"
                           value="${resSkillTimeConfig.testEndTime}" class="input validate[required]"
                           <c:if test="${not empty flag}">readonly="readonly"</c:if>>
                </td>
            </tr>
        </table>
    </form>
    <p style="text-align: center;">
        <input type="button" onclick="save('${resSkillTimeConfig.skillTimeFlow}','${flag}');" style="background: #34A6E3"
               class="btn_green"
               value="保&#12288;存"/>&#12288;
    </p>
</div>
</body>
</html>
