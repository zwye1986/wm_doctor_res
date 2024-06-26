<script type="text/javascript">
    $(document).ready(function () {
        jboxEndLoading();
        initCheck();
    });

    function oper(obj, userFlow, cfg) {
        var cfgValue = "${GlobalConstant.FLAG_N}";
        var login = $("#jsres_doctor_app_login_" + userFlow);
        var menu = $("#jsres_doctor_app_menu_" + userFlow);
        if ($(obj).attr("checked") == "checked") {
            cfgValue = "${GlobalConstant.FLAG_Y}";
        }

        if (login.attr("checked") == "checked") {
            menu.removeAttr("disabled");
        } else {
            if (menu.attr("checked") == "checked") {
                jboxInfo("请先取消app付费功能菜单权限！");
                login.attr("checked", true);
                return false;
            }
            menu.attr("disabled", true);
        }
        $("#cfgCode").val(cfg + userFlow);
        $("#cfgValue").val(cfgValue);
        if (cfg == "jsres_doctor_app_login_") {
            $("#desc").val("是否开放学员app登录权限");
        } else if (cfg == "jsres_doctor_app_menu_") {
            $("#desc").val("是否开放学员付费菜单");
        } else if (cfg == "jsres_doctor_exam_") {
            $("#desc").val("是否开放学员出科考核表");
        } else if (cfg == "jsres_doctor_manual_") {
            $("#desc").val("是否开放学员手册");
        } else if (cfg == "jsres_doctor_graduation_exam_") {
            $("#desc").val("是否开放学员结业理论模拟考核");
        } else if (cfg == "jsres_doctor_activity_") {
            $("#desc").val("是否开放学员教学活动功能");
        } else if (cfg == "jsres_doctor_attendance_") {
            $("#desc").val("是否开放学员考勤功能");
        }
        save(userFlow, cfg, cfgValue);
    }

    function save(userFlow, cfg, cfgValue) {
        var url = "<s:url value='/jsres/powerCfg/saveOne'/>?userFlow=" + userFlow;
        var title = "确认开放权限？";
        if (cfgValue == "N") {
            title = "确认取消权限？";
        }
        jboxButtonConfirm(title, "确认", "取消", function () {
            jboxPost(url, $($('#sysCfgForm').html().htmlFormart(cfg + userFlow)).serialize(), function (resp) {
                if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
                    jboxTip("操作成功！");
                    search();
                    if (cfg == "jsres_doctor_app_menu_") {
                        var menu = $("#jsres_doctor_app_menu_" + userFlow);
                        var time = $("#jsres_doctor_data_time_" + userFlow);
                        if (menu.attr("checked") == "checked") {
                            var c = "changeTime('" + userFlow + "','jsres_doctor_data_time_" + userFlow + "')";
                            time.attr("onclick", c);
                        } else {
                            time.removeAttr("onclick");
                        }
                    }
                } else {
                    jboxTip("操作失败！");
                }
                initCheck();
            }, null, false);
        }, function () {
            search();
        }, 300);
    }

    String.prototype.htmlFormart = function () {
        var html = this;
        for (var index in arguments) {
            var reg = new RegExp('\\{' + index + '\\}', 'g');
            html = html.replace(reg, arguments[index]);
        }
        return html;
    };

    function initCheck() {
        var cl = $("input[name='appLogins']:checked").length;
        var al = $("input[name='appLogins']").length;
        if (cl != 0 && cl == al) {
            $("input[name='appLogin']").attr("checked", true);
        } else {
            $("input[name='appLogin']").attr("checked", false);
        }
        var cl = $("input[name='appMenus']:checked").length;
        var al = $("input[name='appMenus']").length;
        if (cl != 0 && cl == al) {
            $("input[name='appMenu']").attr("checked", true);
        } else {
            $("input[name='appMenu']").attr("checked", false);
        }
        var cl = $("input[name='attendances']:checked").length;
        var al = $("input[name='attendances']").length;
        if (cl != 0 && cl == al) {
            $("input[name='attendance']").attr("checked", true);
        } else {
            $("input[name='attendance']").attr("checked", false);
        }
        var cl = $("input[name='activitys']:checked").length;
        var al = $("input[name='activitys']").length;
        if (cl != 0 && cl == al) {
            $("input[name='activity']").attr("checked", true);
        } else {
            $("input[name='activity']").attr("checked", false);
        }
        var cl = $("input[name='outProcessExams']:checked").length;
        var al = $("input[name='outProcessExams']").length;
        if (cl != 0 && cl == al) {
            $("input[name='outProcessExam']").attr("checked", true);
        } else {
            $("input[name='outProcessExam']").attr("checked", false);
        }
        var cl = $("input[name='outGraduationExams']:checked").length;
        var al = $("input[name='outGraduationExams']").length;
        if (cl != 0 && cl == al) {
            $("input[name='outGraduationExam']").attr("checked", true);
        } else {
            $("input[name='outGraduationExam']").attr("checked", false);
        }
        var cl = $("input[name='schManuals']:checked").length;
        var al = $("input[name='schManuals']").length;
        if (cl != 0 && cl == al) {
            $("input[name='schManual']").attr("checked", true);
        } else {
            $("input[name='schManual']").attr("checked", false);
        }
        if ($("input[name='activity']").is(':checked') && $("input[name='attendance']").is(':checked') && $("input[name='appMenu']").is(':checked')) {
            $("input[name='all']").attr("checked", true);
        } else {
            $("input[name='all']").attr("checked", false);
        }
    }

    function updateSubmitOne(userFlow) {
        var userFlows = userFlow;
        var url = "<s:url value='/jsres/powerCfg/updateSubmit'/>?userFlows=" + userFlows;
        jboxConfirm("确认提交？", function () {
            jboxPost(url, null, function (resp) {
                if ("${GlobalConstant.OPRE_FAIL}" == resp) {
                    jboxTip("操作失败！");
                } else {
                    search();
                    jboxTip(resp);
                }
            }, null, true);
        });
    }
</script>
<style type="text/css">
    .search_table th {
        border: 1px solid #bbbbbb;
    }

    .search_table td {
        border: 1px solid #bbbbbb;
    }
</style>
<form id="sysCfgForm">
    <input type="hidden" id="cfgCode" name="cfgCode"/>
    <input type="hidden" id="cfgValue" name="{0}"/>
    <input type="hidden" id="wsId" value="${GlobalConstant.RES_WS_ID }" name="{0}_ws_id"/>
    <input type="hidden" id="desc" name="{0}_desc">
</form>

<div class="search_table" style="width: 900px;height: auto;margin-top: 5px;margin-bottom: 10px;margin-left: 0px;overflow-x: auto">
    <table border="0" cellpadding="0" cellspacing="0" class="grid"  style="width: 100%;">
        <tr>
            <th rowspan="2" style="min-width: 120px; max-width: 120px; " >培训基地</th>
            <th rowspan="2" style="min-width: 50px; max-width: 50px; " >年级</th>
            <th rowspan="2" style="min-width: 80px; max-width: 80px; ">姓名</th>
            <th rowspan="2" style="min-width: 120px; max-width: 120px; " class="fixed">证件号码</th>
            <th rowspan="2" style="min-width: 80px; max-width: 80px; " >登录名</th>
            <th rowspan="2" style="min-width: 80px; max-width: 80px; " >培养年限</th>
            <th rowspan="2" style="min-width: 80px; max-width: 80px; " >APP登录<br/>权限</th>
            <th colspan="3" style="min-width: 240px; max-width: 240px; " >付费功能</th>
            <th rowspan="2" style="min-width: 90px; max-width: 90px; " >数据审核区间</th>
            <th rowspan="2" style="min-width: 90px; max-width: 90px; " >出科考试权限</th>
            <th rowspan="2" style="min-width: 95px; max-width: 95px; ">结业理论模拟考核</th>
            <th rowspan="2" style="min-width: 80px; max-width: 80px; ">培训手册</th>
            <th rowspan="2" style="min-width: 80px; max-width: 80px; ">是否提交</th>
            <th rowspan="2" style="min-width: 80px; max-width: 80px; " >审核状态</th>
            <th rowspan="2" style="min-width: 80px; max-width: 80px; " >开通时间</th>
            <th rowspan="2" style="min-width: 80px; max-width: 80px; ">审核时间</th>
        </tr>
        <tr>
            <th style="min-width: 80px; max-width: 80px; width: 80px;">教学活动</th>
            <th style="min-width: 80px; max-width: 80px; width: 80px; ">考勤功能</th>
            <th style="min-width: 90px; max-width: 90px; width: 90px; ">其他付费功能</th>
        </tr>

        <c:forEach items="${list }" var="userExt">
            <tr>
                <td>${userExt.orgName }</td>
                <td>${userExt.sessionNumber }</td>
                <td>${userExt.userName }</td>
                <td >${userExt.idNo }</td>
                <td>${userExt.userCode}</td>
                <td>
                    <c:choose>
                        <c:when test="${userExt.trainingYears eq 'OneYear'}">
                            一年
                        </c:when>
                        <c:when test="${userExt.trainingYears eq 'TwoYear'}">
                            两年
                        </c:when>
                        <c:when test="${userExt.trainingYears eq 'ThreeYear'}">
                            三年
                        </c:when>
                    </c:choose>
                </td>
                <td>
                        <%--APP登录权限--%>
                    <c:set var="key" value="jsres_doctor_app_login_${userExt.userFlow}"/>
                    <input type="checkbox"
                           <c:if test="${not empty param.isQuery}">disabled</c:if>
                           id="jsres_doctor_app_login_${userExt.userFlow}"
                           name="appLogins"
                           value="${userExt.userFlow}" ${pdfn:jsresPowerCfgMap(key) == GlobalConstant.FLAG_Y?'checked':''}
                           onchange="oper(this,'${userExt.userFlow }','jsres_doctor_app_login_');"/>
                    <c:if test="${pdfn:jsresPowerCfgMap(key) == GlobalConstant.FLAG_N or empty pdfn:jsresPowerCfgMap(key)}">
                        <input type="checkbox" name="jsres_doctor_app_login_" value="${userExt.userFlow }"
                               style="display: none">
                    </c:if>
                </td>
                    <%-- 活动--%>
                <td>
                    <c:set var="key1" value="jsres_doctor_activity_${userExt.userFlow}"/>
                    <input type="checkbox"
                           <c:if test="${not empty param.isQuery}">disabled</c:if>
                           id="jsres_doctor_activity_${userExt.userFlow}"
                           name="activitys"
                           value="${userExt.userFlow}" ${pdfn:jsresPowerCfgMap(key) != GlobalConstant.FLAG_Y?'disabled':''} ${pdfn:jsresPowerCfgMap(key1)==GlobalConstant.FLAG_Y?'checked':''}
                           onchange="oper(this,'${userExt.userFlow }','jsres_doctor_activity_');"/>
                    <c:if test="${pdfn:jsresPowerCfgMap(key1) == GlobalConstant.FLAG_N or empty pdfn:jsresPowerCfgMap(key1)}">
                        <input type="checkbox" name="jsres_doctor_activity_" value="${userExt.userFlow}"
                               style="display: none">
                    </c:if>

                </td>
                <td>
                        <%--考勤--%>
                    <c:set var="key1" value="jsres_doctor_attendance_${userExt.userFlow}"/>
                    <input type="checkbox"
                           <c:if test="${not empty param.isQuery}">disabled</c:if>
                           id="jsres_doctor_attendance_${userExt.userFlow}"
                           name="attendances"
                           value="${userExt.userFlow}" ${pdfn:jsresPowerCfgMap(key) != GlobalConstant.FLAG_Y?'disabled':''} ${pdfn:jsresPowerCfgMap(key1)==GlobalConstant.FLAG_Y?'checked':''}
                           onchange="oper(this,'${userExt.userFlow }','jsres_doctor_attendance_');"/>
                    <c:if test="${pdfn:jsresPowerCfgMap(key1) == GlobalConstant.FLAG_N or empty pdfn:jsresPowerCfgMap(key1)}">
                        <input type="checkbox" name="jsres_doctor_attendance_" value="${userExt.userFlow }"
                               style="display: none">
                    </c:if>
                </td>
                    <%--其他--%>
                <td>
                    <c:set var="key1" value="jsres_doctor_app_menu_${userExt.userFlow}"/>
                    <input type="checkbox"
                           <c:if test="${not empty param.isQuery}">disabled</c:if>
                           id="jsres_doctor_app_menu_${userExt.userFlow}"
                           name="appMenus"
                           value="${userExt.userFlow}" ${pdfn:jsresPowerCfgMap(key) != GlobalConstant.FLAG_Y?'disabled':''} ${pdfn:jsresPowerCfgMap(key1)==GlobalConstant.FLAG_Y?'checked':''}
                           onchange="oper(this,'${userExt.userFlow }','jsres_doctor_app_menu_');"/>
                    <c:if test="${pdfn:jsresPowerCfgMap(key1) == GlobalConstant.FLAG_N or empty pdfn:jsresPowerCfgMap(key1)}">
                        <input type="checkbox" name="jsres_doctor_app_menu_" value="${userExt.userFlow }"
                               style="display: none">
                    </c:if>
                </td>
                <td
                        <c:if test="${pdfn:jsresPowerCfgMap(key1)==GlobalConstant.FLAG_Y and empty param.isQuery}">onclick="changeTime('${userExt.userFlow}','jsres_doctor_data_time_${userExt.userFlow}')"</c:if>
                        id="jsres_doctor_data_time_${userExt.userFlow}">
                    <c:set var="cfg" value="${cfgMap[userExt.userFlow]}"/>
                        ${cfg.powerStartTime}
                    <c:if test="${!((empty cfg.powerStartTime) and (empty cfg.powerEndTime))}">至</c:if>
                        ${cfg.powerEndTime}
                </td>

                <td>
                        <%--出科考试权限--%>
                    <c:set var="key2" value="jsres_doctor_exam_${userExt.userFlow}"/>
                    <input type="checkbox"
                           <c:if test="${not empty param.isQuery}">disabled</c:if>
                           id="jsres_doctor_exam_${userExt.userFlow}"
                           name="outProcessExams"
                           value="${userExt.userFlow}" ${pdfn:jsresPowerCfgMap(key2)==GlobalConstant.FLAG_Y?'checked':''}
                           onchange="oper(this,'${userExt.userFlow }','jsres_doctor_exam_');"/>
                    <c:if test="${pdfn:jsresPowerCfgMap(key2) == GlobalConstant.FLAG_N or empty pdfn:jsresPowerCfgMap(key2)}">
                        <input type="checkbox" name="jsres_doctor_exam_" value="${userExt.userFlow }"
                               style="display: none">
                    </c:if>
                </td>
                <td>
                        <%--结业理论模拟考核--%>
                    <c:set var="key2" value="jsres_doctor_graduation_exam_${userExt.userFlow}"/>
                    <input type="checkbox"
                           <c:if test="${not empty param.isQuery}">disabled</c:if>
                           id="jsres_doctor_graduation_exam_${userExt.userFlow}"
                           name="outGraduationExams"
                           value="${userExt.userFlow}" ${pdfn:jsresPowerCfgMap(key2)==GlobalConstant.FLAG_Y?'checked':''}
                           onchange="oper(this,'${userExt.userFlow }','jsres_doctor_graduation_exam_');"/>
                    <c:if test="${pdfn:jsresPowerCfgMap(key2) == GlobalConstant.FLAG_N or empty pdfn:jsresPowerCfgMap(key2)}">
                        <input type="checkbox" name="jsres_doctor_graduation_exam_" value="${userExt.userFlow }"
                               style="display: none">
                    </c:if>
                </td>
                <td><%--培训手册--%>
                    <c:set var="key3" value="jsres_doctor_manual_${userExt.userFlow}"/>
                    <input type="checkbox"
                           <c:if test="${not empty param.isQuery}">disabled</c:if>
                           id="jsres_doctor_manual_${userExt.userFlow}"
                           name="schManuals"
                           value="${userExt.userFlow}" ${pdfn:jsresPowerCfgMap(key3)==GlobalConstant.FLAG_Y?'checked':''}
                           onchange="oper(this,'${userExt.userFlow }','jsres_doctor_manual_');"/>
                    <c:if test="${pdfn:jsresPowerCfgMap(key3) == GlobalConstant.FLAG_N or empty pdfn:jsresPowerCfgMap(key3)}">
                        <input type="checkbox" name="jsres_doctor_manual_" value="${userExt.userFlow }"
                               style="display: none">
                    </c:if>
                </td>
                <td>
                    <c:if test="${userExt.isSubmitId eq 'NotSubmit' or empty userExt.isSubmitId }">
                        <c:set var="key" value="jsres_doctor_app_login_${userExt.userFlow}"/>
                        <c:set var="key1" value="jsres_doctor_app_menu_${userExt.userFlow}"/>
                        <c:set var="cfg" value="${cfgMap[userExt.userFlow]}"/>
                        <c:set var="key2" value="jsres_doctor_exam_${userExt.userFlow}"/>
                        <c:set var="key2" value="jsres_doctor_graduation_exam_${userExt.userFlow}"/>
                        <c:set var="key3" value="jsres_doctor_manual_${userExt.userFlow}"/>

                        <c:if test="${pdfn:jsresPowerCfgMap(key) == GlobalConstant.FLAG_Y or pdfn:jsresPowerCfgMap(key1)==GlobalConstant.FLAG_Y
						or !((empty cfg.powerStartTime) and (empty cfg.powerEndTime)) or pdfn:jsresPowerCfgMap(key2)==GlobalConstant.FLAG_Y
						or pdfn:jsresPowerCfgMap(key3)==GlobalConstant.FLAG_Y}">
                            <input type="button" value="提交" class=""
                                   style="background-color: #e9e9ed;height: 25px;width: 45px;border-radius: 5px"
                                   onclick="updateSubmitOne('${userExt.userFlow}')"/>
                            <input type="checkbox" id="isSubmitId" name="isSubmitId" value="${userExt.userFlow}"
                                   style="display: none"/>
                        </c:if>

                    </c:if>
                    <c:if test="${userExt.isSubmitId eq 'Submit' and userExt.checkStatusId ne 'UnPassed'}">
                        已提交
                    </c:if>
                    <c:if test="${userExt.isSubmitId eq 'Submit' and userExt.checkStatusId eq 'UnPassed'}">
                        <input type="button" value="重新提交" class=""
                               style="background-color: #e9e9ed;height: 25px;width: 65px;border-radius: 5px"
                               onclick="updateSubmitOne('${userExt.userFlow}')"/>
                    </c:if>
                </td>
                <td>${userExt.checkStatusName }</td>
                <td>${userExt.submitTime }</td>
                <td>${userExt.checkTime }</td>
            </tr>
        </c:forEach>
        </tbody>

        <c:if test="${empty list}">
            <tr>
                <td colspan="15">暂无记录！</td>
            </tr>
        </c:if>
    </table>
</div>
<div class="page" style="padding-right: 40px;">
    <c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"></c:set>
    <pd:pagination-jsres toPage="toPage"/>
</div>