<%@ taglib prefix="pdfn" uri="http://www.njpdkj.com/pdfn" %>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true" />
        <jsp:param name="jquery_ui_tooltip" value="true" />
        <jsp:param name="jquery_cxselect" value="true" />
        <jsp:param name="jquery_validation" value="true" />
        <jsp:param name="jquery_datePicker" value="true" />
        <jsp:param name="jquery_fullcalendar" value="true" />
        <jsp:param name="jquery_fixedtableheader" value="true" />
        <jsp:param name="jquery_placeholder" value="true" />
        <jsp:param name="ueditor" value="true"/>
        <jsp:param name="jquery_qrcode" value="true"/>
    </jsp:include>
    <%--<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/osca/css/font.css'/>"/>--%>
    <script src="<s:url value='/js/jquery.jqprint-0.3.js'/>" type="text/javascript"></script>
    <script type="text/javascript">

        /**
         * 查看 考站信息
         */
        function showStationInfo(clinicalFlow){
            var url='<s:url value="/jsres/skillTimeConfig/showStationInfo?clinicalFlow="/>'+clinicalFlow;
            jboxOpen(url,"考站信息",900,400);
        }

        function toPage(page){
            var form = $("#searchForm");
            $("#currentPage").val(page);
            jboxStartLoading();
            form.submit();
        }


        function searchInfo(){
            jboxStartLoading();
            $("#searchForm").submit();
        }

        var width=(window.screen.width)*0.6;
        var height=(window.screen.height)*0.6;
        function showTicket(doctorFlow,clinicalFlow){
            jboxOpen("<s:url value='/jsres/skillTimeConfig/showTicket'/>?doctorFlow="+doctorFlow+"&clinicalFlow="+clinicalFlow, "准考证信息", 800, 500);
        }
        var width1=(window.screen.width)*0.7;
        var height1=(window.screen.height)*0.7;
        function showScoreDetails(doctorFlow,clinicalFlow){
            jboxOpen("<s:url value='/osca/oscaDoctorOrdered/showScoreDetails'/>?doctorFlow="+doctorFlow+"&clinicalFlow="+clinicalFlow, "成绩详情", width1, height1);
        }

        function printOscaTicket(doctorFlow){
            jboxTip("打印中，请稍等...");
            setTimeout(function(){
                var url = "<s:url value='/osca/oscaDoctorOrdered/showPrintTicket'/>?doctorFlow="+doctorFlow;
                jboxPost(url, null, function(resp){
                    if (resp) {
                        $("#printTicket").append(resp);
                        $("#printTicket").jqprint({
                            debug: false,
                            importCSS: true,
                            printContainer: true,
                            operaSupport: false
                        });
//                        console.log(resp);
//                        var headstr = "<html><head><title></title></head><body>";
//                        var footstr = "</body></html>";
//                        var newstr = resp;
//                        var oldstr = document.body.innerHTML;
//                        document.body.innerHTML = headstr+newstr+footstr;
//                        window.print();
//                        document.body.innerHTML = oldstr;
//                        return false;
                    }
                }, null, false);
            },2000);
        }

    </script>
</head>
<body>
<div class="main_hd">
    <h2 class="underline">我的技能考核预约</h2>
</div>
<div class="main_bd" style="margin-left: 10px;margin-right: 10px;">
    <div class="main_bd" style="margin-left: 20px;" id="orderedRecord">
        <div id="base" class="main_bd clearfix" style="margin-top:20px;">
            <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <colgroup>
                <col width="8%"/>
                <col width="8%"/>
                <col width="16%"/>
                <col width="4%"/>
                <col width="4%"/>
                <col width="16%"/>
                <col width="8%"/>
                <col width="8%"/>
                <col width="8%"/>
                <col width="8%"/>
                <col width="12%"/>
            </colgroup>
            <tr>
                <th  style="text-align: center;">考核名称</th>
                <th  style="text-align: center;">考核专业</th>
                <th  style="text-align: center;">预约时间</th>
                <th  style="text-align: center;">预约容量</th>
                <th  style="text-align: center;">剩余容量</th>
                <th  style="text-align: center;">考核时间</th>
                <th  style="text-align: center;">考核类型</th>
                <th  style="text-align: center;">考点</th>
                <th  style="text-align: center;">预约状态</th>
                <th  style="text-align: center;">考核方案</th>
                <th  style="text-align: center;">准考证</th>
            </tr>
            <c:if test="${not empty skillsAssessmentList}">
                <c:forEach items="${skillsAssessmentList}" var="skillsAssessment" varStatus="num">
                    <tr id="${skillsAssessment.clinicalFlow}">
                        <td class="appoint">${skillsAssessment.clinicalName}</td>
                        <td class="appoint">${skillsAssessment.speName}</td>
                        <td class="appoint">${skillsAssessment.appointStartTime}~<br/>${skillsAssessment.appointEndTime}</td>
                        <td class="appoint">${skillsAssessment.appointNum}</td>
                        <td class="appoint">${skillsAssessment.overplus}</td>
                        <td class="appoint">${skillsAssessment.oscaDoctorAssessment.examStartTime}~<br/>${skillsAssessment.oscaDoctorAssessment.examEndTime}</td>
                        <td class="appoint"><c:if test="${skillsAssessment.isLocal eq 'N'}">结业考核</c:if>
                            <c:if test="${skillsAssessment.isLocal eq 'Y'}">院内考核</c:if></td>
                        <td class="appoint">${skillsAssessment.orgName}</td>
                        <td class="appoint">
                            <label  name="sysOrg.orgName" value="" type="text" >
                                    ${skillsAssessment.oscaDoctorAssessment.auditStatusName}
                                <c:if test="${not empty skillsAssessment.oscaDoctorAssessment.reason and skillsAssessment.oscaDoctorAssessment.auditStatusId eq 'UnPassed'}">
                                    （${skillsAssessment.oscaDoctorAssessment.reason}）
                                </c:if>
                            </label>
                        </td>
                        <td class="appoint">
                            <a style="cursor: pointer;color:#4195c5;" onclick="showStationInfo('${skillsAssessment.clinicalFlow}')">查看</a>
                        </td>
                        <td class="appoint">
                        <c:if test="${skillsAssessment.oscaDoctorAssessment.auditStatusId eq auditStatusEnumPassed}">
                            <input type="button" value="预览/打印" class="btn_green" onclick="showTicket('${skillsAssessment.oscaDoctorAssessment.doctorFlow}','${skillsAssessment.clinicalFlow}');"/>
                        </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
            <c:if test="${empty skillsAssessmentList}">
                <tr><td colspan="99">暂无记录</td></tr>
            </c:if>
        </table>
        </div>

    </div>
    <div class="page" style="padding-right: 40px;">
        <c:set var="pageView" value="${pdfn:getPageView(skillsAssessmentList)}" scope="request"/>
        <pd:pagination-jsres toPage="toPage"/>
    </div>
    <div class="main_hd" id="defaultInfo" style="display: none;">
            <div align="center">
                <h2>临床技能考核参与条件</h2>
            </div>
            <div style="margin-bottom: 10px;" align="left">
                &#12288;1、培训年限是否满足（3/2/1）？<br/>
                &#12288;2、学历要求：本科及以上学历<br/>
                &#12288;3、公共科目或全科医学相关理论知识考试合格<br/>
                &#12288;4、完成培训计划（培训数据填写）<br/>
                &#12288;5、考核记录完整规范<br/>
                &#12288;6、有临床或口腔执医证<br/>
                &#12288;7、医师执业范围与培训科目相对应
            </div>
        </div>
    <div id="printTicket" hidden="hidden"></div>
</div>
</body>
</html>
