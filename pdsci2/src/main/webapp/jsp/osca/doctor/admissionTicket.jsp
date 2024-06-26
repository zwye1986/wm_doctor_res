
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="jquery_qrcode" value="true"/>
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="false"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_ui_combobox" value="false"/>
        <jsp:param name="jquery_ui_sortable" value="false"/>
        <jsp:param name="jquery_cxselect" value="false"/>
        <jsp:param name="jquery_scrollTo" value="false"/>
        <jsp:param name="jquery_jcallout" value="false"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fullcalendar" value="false"/>
        <jsp:param name="jquery_fngantt" value="false"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
    </jsp:include>
    <html>
    <head>
        <script src="<s:url value='/js/jquery.jqprint-0.3.js'/>" type="text/javascript"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                var url = "${signUrl}";
                $("#qrcode").qrcode({
                    render: "canvas",
                    width: 45,
                    height:45,
                    correctLevel:0,//纠错等级
                    text: url
                });
                canvasToImage();
            });

            function canvasToImage(){
                var dataurl=document.getElementsByTagName("canvas")[0].toDataURL();
                $("#codeImg").attr("src",dataurl);
            }

            function printOscaTicket() {
                jboxTip("打印中，请稍等...");
                $("#base").jqprint({
                    debug: false,
                    importCSS: true,
                    printContainer: true,
                    operaSupport: false
                });
            }

        </script>
    </head>
<body>
<div id="base">
    <table  class="xllist" align="center" style="overflow: auto;border: none">
        <div id="qrcode" style="text-align: center;" hidden="hidden"></div>
        <colgroup>
            <col width="20%"/>
            <col width="50%"/>
            <col width="30%"/>
        </colgroup>
        <tr>
            <th style="height: 60px;">
                <img src="" id="codeImg" style="text-align: center;" width="45px" height="45px"/>
            </th>
            <th style="height: 60px;" align="center">住院医师临床技能考核<h2>准考证</h2></th>
            <td style="height: 60px;" rowspan="3">
                <img src="${sysCfgMap['upload_base_url']}/${oscaSkillsAssessmentExt.resDoctor.doctorHeadImg}" id="showImg"
                     style="text-align: center;" width="80px" height="100px"
                     onerror="this.src='<s:url value="/css/skin/up-pic.jpg"/>'"/>
            </td>
        </tr>
        <tr>
            <td>姓名</td>
            <td><label  name="sysOrg.orgName" value="" type="text" >${oscaSkillsAssessmentExt.oscaDoctorAssessment.doctorName}</label></td>
        </tr>
        <tr>
            <td>准考证号</td>
            <td><label  name="sysOrg.orgName" value="" type="text" >${oscaSkillsAssessmentExt.oscaDoctorAssessment.ticketNumber}</label></td>
        </tr>
        <tr>
            <td rowspan="2" colspan="2" style="text-align: left;">
                &#12288;考&#12288;&#12288;点：
                <label  name="sysOrg.orgName" value="" type="text" >${oscaSkillsAssessmentExt.orgName}</label><br/>
                &#12288;工作单位：
                <label  name="sysOrg.orgName" value="" type="text" >${oscaSkillsAssessmentExt.resDoctor.workOrgName}</label><br/>
                &#12288;考核专业：
                <label  name="sysOrg.orgName" value="" type="text" >${oscaSkillsAssessmentExt.speName}</label><br/>
                &#12288;有效身份证明号码：
                <label  name="sysOrg.orgName" value="" type="text" >${oscaSkillsAssessmentExt.sysUser.idNo}</label><br/>
                &#12288;考试地点：
                <label  name="sysOrg.orgName" value="" type="text" >${oscaSkillsAssessmentExt.examAddress}</label><br/>
            </td>
            <td rowspan="1" style="text-align: left;">
                &#12288;考试日期：
                <label  name="sysOrg.orgName" type="text" >${fn:substring(oscaSkillsAssessmentExt.examStartTime, 0, 10)} </label><br/>
                &#12288;考试时间：
                <label  name="sysOrg.orgName" type="text" >${fn:substring(oscaSkillsAssessmentExt.examStartTime, 10, 17)}-${fn:substring(oscaSkillsAssessmentExt.examEndTime, 10, 17)}</label><br/>
            </td>
        </tr>
    </table>
</div>
<br/><br/>
<div align="center">
    <input type="button" value="打印准考证" class="search" onclick="printOscaTicket();"/>
</div>
</body>
</html>
