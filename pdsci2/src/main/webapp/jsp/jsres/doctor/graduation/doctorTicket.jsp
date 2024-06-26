
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


    <script src="<s:url value='/js/jquery.jqprint-0.3.js'/>" type="text/javascript"></script>
    <script type="text/javascript">
        $(function(){
            $("#t1").focus();
            $("#t2").focus();
            $("#t2").blur();
        })

        $(document).ready(function () {
            var url = "${signUrl}";
            $("#qrcode").qrcode({
                render: "canvas",
                width: 500,
                height:500,
                correctLevel:0,//纠错等级
                text: url
            });
            canvasToImage();
            <c:forEach items="${partFlows}" var="partFlow">
            var length = $(".part_${partFlow}").size()/2;
            $(".part_${partFlow}").first().attr("colspan",length);
            $(".part_${partFlow}:gt(0)").hide();
            </c:forEach>
        });

        function canvasToImage(){
            var dataurl=document.getElementsByTagName("canvas")[0].toDataURL();
            $("#codeImg").attr("src",dataurl);
        }

        function printOscaTicket() {
            if(${empty oscaSkillsAssessmentExt.examAddress or empty oscaSkillsAssessmentExt.oscaDoctorAssessment.examStartTime
                or empty oscaSkillsAssessmentExt.oscaDoctorAssessment.examEndTime}){
                jboxInfoBasic("考核时间及考核地点暂未确定，无法打印准考证！");
                return false;
            }
            jboxTip("打印中，请稍等...");
            $("#base").jqprint({
                debug: false,
                importCSS: true,
                printContainer: true,
                operaSupport: false
            });
        }

    </script>
    <style>
        textarea{line-height: 16px!important;}
    </style>
</head>

<body>
<div style="height:100%;overflow: auto;">
<span>
<div id="base">
    <div id="qrcode" style="text-align: center;" hidden="hidden"></div>
    <div class="head" style="background-color:#0092f0; height:45px; font-size:20px; color:#FFFFFF; text-align:center; line-height:45px;">
        ${oscaSkillsAssessmentExt.clinicalName}准考证
    </div>
    <%--<h1 style="font-size:18px; text-align:center;  margin-bottom:5px;">准考证</h1>--%>
    <div style="float: left;width:65%;margin-left: 5%;">
        <div style="line-height: 170%;margin-top: 20px;" >
            <table>
            <tr>
                <td style="text-align: left">姓&#12288;&#12288;名:
                <input value="${oscaSkillsAssessmentExt.oscaDoctorAssessment.doctorName}" style="border:0; border-bottom:#181818 1px solid;font-size:12px;vertical-align: middle; padding: 0; margin: 0; width:300px; text-align: center" readonly="true"/></td>
            </tr>
            <tr>
                <td style="  ;">准考证号:
                <input value="${oscaSkillsAssessmentExt.oscaDoctorAssessment.ticketNumber}" style="border:0; border-bottom:#181818 1px solid;font-size:12px;vertical-align: middle; padding: 0; margin: 0; width:300px; text-align: center" readonly="true"/></td>
            </tr>
            <tr>
                <td style="  ;">考试时间:
                <input value="${oscaSkillsAssessmentExt.oscaDoctorAssessment.examStartTime}~${oscaSkillsAssessmentExt.oscaDoctorAssessment.examEndTime}" style="border:0; border-bottom:#181818 1px solid;font-size:12px;vertical-align: middle; padding: 0; margin: 0; width:300px; text-align: center" readonly="true"/></td>
            </tr>
            <tr>
                <td>考试地点:
                <textarea rows="1" id="t1" readonly="true" style="resize:none;width: 300px;vertical-align:middle;border:0; border-bottom:#181818 1px solid;font-size:12px;padding: 0; margin: 0;text-align: center;">${oscaSkillsAssessmentExt.examAddress}</textarea></td>
            </tr>
            <tr>
                <td>备&#12288;&#12288;注:
                <textarea rows="1" id="t2" readonly="true" style="resize:none;width: 300px;vertical-align:middle;border:0; border-bottom:#181818 1px solid;font-size:12px;padding: 0; margin: 0;text-align: center;">${oscaSkillsAssessmentExt.remarks}</textarea></td>
            </tr>
            <tr>
                <td style=";" id="t3">考试专业:
                    <input value="${oscaSkillsAssessmentExt.speName}" class="input1" style="border:0; border-bottom:#181818 1px solid;font-size:12px;vertical-align: middle; padding: 0; margin: 0; width:160px;text-align: center" readonly="true"/>
                    (代码：<input value="${oscaSkillsAssessmentExt.speId}" class="input2" style="border:0; border-bottom:#181818 1px solid;font-size:12px;vertical-align: middle; padding: 0; margin: 0; width:85px;text-align: center" readonly="true"/>)</td>
            </tr>
            <tr>
                <td>有效证件号码:
                    <input value="${oscaSkillsAssessmentExt.sysUser.idNo}" class="input3" style="border:0; border-bottom:#181818 1px solid;font-size:12px;vertical-align: middle; padding: 0; margin: 0; width:275px;text-align: center" readonly="true"/></td>
            </tr>
            </table>
            </div><br/>
        <div style="line-height: 150%;margin-top: 25px;">
            <h3 style="margin:0; padding:0; clear:both; font-size:14px; ">
                考生须知：</h3><br/>
            1、考生须同时持准考证和有效身份证，提前到达考试地点集中点名。<br/>
		    2、请考生自带工作服、听诊器、帽子、口罩、签字笔等；贵重物品请自行妥善保管。<br/>
		    3、禁止携带各种通讯工具或电子设备等与考试无关物品进入考场，一经发现视同作弊。<br/>
		    4、考生入场后应严格服从监考人员管理，考试结束待监考人员许可后离场。<br/>
		    5、本准考证需由工作单位或培训单位加盖单位（管理部门）公章方为有效。
        </div>
    </div>

    <div style="float: right;width:30%;text-align: left">
        <div>
            <img src="${sysCfgMap['upload_base_url']}/${oscaSkillsAssessmentExt.sysUser.userHeadImg}" id="showImg"
                 style="text-align: center;" width="140px" height="185px"
                 onerror="this.src='<s:url value="/css/skin/up-pic.jpg"/>'"/>
        </div>
        <br/><br/>
        <div>
            <img src="" id="codeImg" style="text-align: center;" width="140px" height="140px"/>
        </div>
    </div>
    <div style="clear:both"></div>
    <div style="text-align: center;margin-top:15px;">
        你可以在已考核的站点打√，来检查是否完成全部考核内容，以免漏考。
        <table class="xllist" style="width:98%;margin-top:5px; " >
            <tr>
                <c:forEach items="${subjectStations}" var="station">
                    <td class="part_${station.partFlow}">${station.partName}</td>
                </c:forEach>
            </tr>
            <tr>
                <c:forEach items="${subjectStations}" var="station">
                    <td style="width:${width}">${station.stationName}</td>
                </c:forEach>
            </tr>
            <tr>
                <c:forEach items="${subjectStations}" var="station">
                    <td>${station.examinedContent}</td>
                </c:forEach>
            </tr>
            <tr>
                <c:forEach items="${subjectStations}" var="station">
                    <td></td>
                </c:forEach>
            </tr>
        </table>
    </div>
</div>
</span>
    <div style="text-align: center;margin-top:10px; ">
        <input type="button" value="打印准考证" class="search" onclick="printOscaTicket();" style="font-size: 15px;background: #44b549;"/>
    </div>
</div>
</body>
</html>
