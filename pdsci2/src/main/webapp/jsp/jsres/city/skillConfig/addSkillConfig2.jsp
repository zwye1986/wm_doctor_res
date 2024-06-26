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

        function checkSkillStart() {
            if ($("#skillStartTime").val()) {
                if ($("#skillStartTime").val() < $("#skillStartTime2").val()) {
                    jboxTip("技能考核时间需在技能考核可配置时间范围内！");
                }
                if($("#skillStartTime").val() > $("#skillEndTime2").val()){
                    jboxTip("技能考核时间需在技能考核可配置时间范围内！");
                }
            }
        }

        function checkSkillEnd() {
            if ($("#skillEndTime").val()) {
                if ($("#skillEndTime").val() < $("#skillStartTime2").val()) {
                    jboxTip("技能考核时间需在技能考核可配置时间范围内！");
                }
                if($("#skillEndTime").val() > $("#skillEndTime2").val()){
                    jboxTip("技能考核时间需在技能考核可配置时间范围内！");
                }
                if ($("#skillStartTime").val() > $("#skillEndTime").val()) {
                    jboxTip("技能考核开始时间不能大于技能考核结束时间！");
                }
            }
        }

        function checkAppointEnd() {
            if ($("#appointEndTime").val()) {
                if($("#appointEndTime").val() > $("#skillEndTime").val()){
                    jboxTip("预约时间需在技能考核结束时间前！");
                }
                if($("#appointStartTime").val() > $("#appointEndTime").val()){
                    jboxTip("预约开始时间不能大于预约结束时间！");
                }
            }
        }

        function save(skillFlow) {
            if ($("#skillStartTime").val() < $("#skillStartTime2").val()) {
                jboxTip("技能考核时间需在技能考核可配置时间范围内！");
                return false;
            }
            if($("#skillStartTime").val() > $("#skillEndTime2").val()){
                jboxTip("技能考核时间需在技能考核可配置时间范围内！");
                return false;
            }
            if ($("#skillEndTime").val() < $("#skillStartTime2").val()) {
                jboxTip("技能考核时间需在技能考核可配置时间范围内！");
                return false;
            }
            if($("#skillEndTime").val() > $("#skillEndTime2").val()){
                jboxTip("技能考核时间需在技能考核可配置时间范围内！");
                return false;
            }
            if ($("#skillStartTime").val() > $("#skillEndTime").val()) {
                jboxTip("技能考核开始时间不能大于技能考核结束时间！");
                return false;
            }
            if($("#appointEndTime").val() > $("#skillEndTime").val()){
                jboxTip("预约时间需在技能考核结束时间前！");
                return false;
            }
            if($("#appointStartTime").val() > $("#appointEndTime").val()){
                jboxTip("预约开始时间不能大于预约结束时间！");
                return false;
            }
            if (false == $("#saveCfgForm").validationEngine("validate")) {
                return false;
            }
            var orgFlows="";
            $("input[name='orgFlow']:checked" ).each(function(i){
                if(i == 0){
                    orgFlows += $(this).val();
                }else {
                    orgFlows += "&orgFlows=" + $(this).val();
                }
            });
            var url = "<s:url value='/jsres/skillTimeConfig/insertSkillConfig'/>?skillFlow=" + skillFlow + "&orgFlows="+orgFlows;
            jboxPost(url, $("#saveCfgForm").serialize(), function (resp) {
                jboxTip(resp);
                if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
                    setTimeout(
                        function () {
                            window.parent.searchSkills();
                            jboxClose();
                        }, 2000
                    );
                }
            }, null, false)
        }

        function isShow(flag) {
            if(flag == 'Y'){
                $(".order").show();
            }else{
                $(".order").hide();
            }
        }
    </script>
</head>
<body>
<div class="mainright" style="max-height: 390px; padding: 0px 20px;overflow: auto  ">
    <form id="saveCfgForm" style="position: relative;">
        <input id="cityId" type="hidden" name="cityId" value="${cityId}"/>
        <input id="skillTimeFlow" type="hidden" name="skillTimeFlow" value="${skillTimeConfig.skillTimeFlow}"/>
        <table class="grid" style="width:100%; margin-bottom: 10px; margin-top: 30px;">
            <tr>
                <th colspan="2" style="text-align: center; width: 30%"> 考试编号<font color="red">*</font></th>
                <td colspan="2" style="text-align: left; width: 70%">
                    <input name="testId"
                           value="${skillTimeConfig.testId}" class="input validate[required]"
                           readonly="readonly"
                    >
                </td>
            </tr>
            <tr>
                <th colspan="2" style="text-align: center"> 技能考核可配置时间范围<font color="red">*</font></th>
                <td colspan="2" style="text-align: left">
                    <input name="skillStartTime2" id="skillStartTime2"
                           class="input validate[required]"
                           value="${skillTimeConfig.skillStartTime}"
                           readonly="readonly"
                           >&nbsp;&nbsp;至&nbsp;&nbsp;
                    <input name="skillEndTime2" id="skillEndTime2"
                           class="input validate[required]"
                           value="${skillTimeConfig.skillEndTime}"
                           readonly="readonly">
                </td>
            </tr>
            <tr>
                <th colspan="2" style="text-align: center"> 技能考核时间<font color="red">*</font></th>
                <td colspan="2" style="text-align: left">
                    <input name="skillStartTime" id="skillStartTime"
                           onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
                           onchange="checkSkillStart()"
                           class="input validate[required]"
                           value="${resSkillConfig.skillStartTime}"
                           >&nbsp;&nbsp;至&nbsp;&nbsp;
                    <input name="skillEndTime" id="skillEndTime"
                           onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
                           onchange="checkSkillEnd()"
                           class="input validate[required]"
                           value="${resSkillConfig.skillEndTime}"
                           >
                </td>
            </tr>
            <tr>
                <th colspan="2" style="text-align: center"> 考核名单方式<font color="red">*</font></th>
                <td colspan="2" style="text-align: left">
                    <input type="radio" name="skillWay" value="order" onchange="isShow('Y');">预约
                    <input type="radio" name="skillWay" value="export" onchange="isShow('N');">导入
                </td>
            </tr>
            <tr class="order" style="display: none">
                <th colspan="2" style="text-align: center;"> 考试名称（学员准考证标题）<font color="red">*</font></th>
                <td colspan="2" style="text-align: left;">
                    <input name="testName"
                           value="${resSkillConfig.testName}" class="input validate[required]"
                    >
                </td>
            </tr>
            <tr class="order" style="display: none">
                <th colspan="2" style="text-align: center"> 技能考核预约时间<font color="red">*</font></th>
                <td colspan="2" style="text-align: left">
                    <input name="appointStartTime" id="appointStartTime"
                           onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
                           class="input validate[required]"
                           value="${resSkillConfig.appointStartTime}"
                           >&nbsp;&nbsp;至&nbsp;&nbsp;
                    <input name="appointEndTime" id="appointEndTime"
                           onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
                           onchange="checkAppointEnd()"
                           class="input validate[required]"
                           value="${resSkillConfig.appointEndTime}"
                           >
                </td>
            </tr>
            <tr class="order" style="display: none">
                <th colspan="2" style="text-align: center"> 考点安排<font color="red">*</font></td>
                <td colspan="2" style="text-align: left">
                    <c:forEach items="${orgs}" var="org">
                        <input type="checkbox" name="orgFlow" id="orgFlow" class="validate[required]" value="${org.orgFlow}"
                               <c:if test="${pdfn:contain(org.orgFlow, orgList)}">checked</c:if>
                        ><font
                            style="font-size: 15px"
                    >${org.orgName}</font>
                    </c:forEach>
                </td>
            </tr>

        </table>
    </form>
    <p style="text-align: center;">
        <c:if test="${flag eq 'Y'}">
        <input type="button" onclick="save('${resSkillConfig.skillFlow}');" style="background: #34A6E3"
               class="btn_green"
               value="保&#12288;存"/>&#12288;
        </c:if>
        <c:if test="${flag eq 'N'}">
            <span style="color: red;font-size: 15px">非发布时间段，暂无法新增！</span>
        </c:if>
    </p>
</div>
</body>
</html>
