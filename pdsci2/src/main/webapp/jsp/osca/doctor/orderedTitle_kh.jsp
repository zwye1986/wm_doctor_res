<%@ taglib prefix="pdfn" uri="http://www.njpdkj.com/pdfn" %>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true" />
        <jsp:param name="jbox" value="true" />
        <jsp:param name="jquery_form" value="false" />
        <jsp:param name="jquery_ui_tooltip" value="true" />
        <jsp:param name="jquery_ui_combobox" value="false" />
        <jsp:param name="jquery_ui_sortable" value="false" />
        <jsp:param name="jquery_cxselect" value="true" />
        <jsp:param name="jquery_scrollTo" value="false" />
        <jsp:param name="jquery_jcallout" value="false" />
        <jsp:param name="jquery_validation" value="true" />
        <jsp:param name="jquery_datePicker" value="true" />
        <jsp:param name="jquery_fullcalendar" value="true" />
        <jsp:param name="jquery_fngantt" value="false" />
        <jsp:param name="jquery_fixedtableheader" value="true" />
        <jsp:param name="jquery_placeholder" value="true" />
        <jsp:param name="jquery_iealert" value="false" />
        <jsp:param name="ueditor" value="true"/>
        <jsp:param name="jquery_qrcode" value="true"/>
    </jsp:include>
    <link rel="stylesheet" type="text/css" href="<s:url value='/jsp/osca/css/font.css'/>"/>
    <script src="<s:url value='/js/jquery.jqprint-0.3.js'/>" type="text/javascript"></script>
    <style type="text/css">
        td.appoint{cursor:pointer;}
    </style>
    <script type="text/javascript">
        $(document).ready(function(){
            $("li").click(function(){
                $(".tab_select").addClass("tab");
                $(".tab_select").removeClass("tab_select");
                $(this).removeClass("tab");
                $(this).addClass("tab_select");
            });
            if ("${param.liId}" != "") {
                $("#${param.liId}").addClass("tab_select");
            } else {
                $('li').first().addClass("tab_select");
            }
            $(".tab_select a").click();

            $("#baseTr input").each(function(){
                var chVal=$(this).attr("value");
                <%--if("${param.liId}" == "secondLi"){--%>
                <%--if(chVal=="取消预约"){--%>
                <%--$("td input[name='ordered']").attr("class","btn_grey");--%>
                <%--$("td input[name='already']").attr("class","btn_grey");--%>
                <%--$("td input[name='ordered']").attr("disabled",true);--%>
                <%--$("td input[name='already']").attr("disabled",true);--%>
                <%--}--%>
                <%--if(chVal=="已预约"){--%>
                <%--$("td input[name='ordered']").attr("class","btn_grey");--%>
                <%--$("td input[name='cancelOrdered']").attr("class","btn_grey");--%>
                <%--$("td input[name='ordered']").attr("disabled",true);--%>
                <%--$("td input[name='cancelOrdered']").attr("disabled",true);--%>
                <%--}--%>
                <%--}--%>
            });

            <%--if("${param.liId}" == "thirdLi"){--%>
                <%--$("#orderedList").attr("hidden",true);--%>
            <%--}else{--%>
                <%--$("#orderedRecord").attr("hidden",true);--%>
            <%--}--%>

            $('.appoint').bind('click',function(){
                var clinicalFlow = $(this).closest("tr").attr("id");
                var url = "<s:url value='/osca/oscaDoctorOrdered/searchOrderedRecord'/>?liId=thirdLi&clinicalFlow="+clinicalFlow;
                $("#tableLi3").attr("href",url);
                $("#searchSpan").click();
            });
            if('${showTip}'=='Y'){
                jboxConfirm("你的信息已审核通过！开始预约考试",null,null);
            }
        });
        function toOrdered(clinicalFlow,doctorFlow,appointNum,examStartTime,examEndTime,clinicalYear,orgFlow){
            var width=(window.screen.width)*0.3;
            if("${param.liId}" == "secondLi"){
                if('${oscaStudentSubmit}'=='Y'){
                    var url2= "<s:url value='/osca/oscaDoctorOrdered/checkExamTime'/>?doctorFlow="+doctorFlow+"&examStartTime="+examStartTime+"&examEndTime="+examEndTime+"&clinicalFlow="+clinicalFlow;
                    jboxPost(url2, null,function(resp2){
                        if(resp2 == "${GlobalConstant.NOT_NULL}"){
                            jboxInfoBasic("该信息考核时间与已预约的考核时间重叠,请重新选择！",width);
                            return false;
                        }else{
                            toOrderedBeforCheck(clinicalFlow,doctorFlow,appointNum,clinicalYear,orgFlow);
                        }
                    }, null, false);
                }else{
                    if(clinicalYear < "${graduationYear}") {
                        jboxInfoBasic("你的考核年份未到，无法预约技能考核！",width);
                        return false;
                    }else if(${empty graduationYear}) {
                        jboxInfoBasic("你的考核年份未到，无法预约技能考核！",width);
                        return false;
                    }else if(clinicalYear == "${graduationYear}") {
                        if(
                                <%--resScore.theoryScore < hegeScore or empty resScore.theoryScore)--%>
                                <%--and (resScore1.theoryScore < hegeScore or empty resScore1.theoryScore)--%>
                                <%--and --%>
                        ${(oscaStudentSubmit ne 'Y') and graduationYear<2018}
                        ){
                            jboxInfoBasic("你的结业理论成绩未通过，无法预约技能考核！",width);
                            return false;
                        }else{
                            var url2= "<s:url value='/osca/oscaDoctorOrdered/checkExamTime'/>?doctorFlow="+doctorFlow+"&examStartTime="+examStartTime+"&examEndTime="+examEndTime+"&clinicalFlow="+clinicalFlow;
                            jboxPost(url2, null,function(resp2){
                                if(resp2 == "${GlobalConstant.NOT_NULL}"){
                                    jboxInfoBasic("该信息考核时间与已预约的考核时间重叠,请重新选择！",width);
                                    return false;
                                }else{
                                    toOrderedBeforCheck(clinicalFlow,doctorFlow,appointNum,clinicalYear,orgFlow,'passed');
                                }
                            }, null, false);
                        }
                    }else if(clinicalYear > "${graduationYear}"){
                        var url2= "<s:url value='/osca/oscaDoctorOrdered/checkExamTime'/>?doctorFlow="+doctorFlow+"&examStartTime="+examStartTime+"&examEndTime="+examEndTime+"&clinicalFlow="+clinicalFlow;
                        jboxPost(url2, null,function(resp2){
                            if(resp2 == "${GlobalConstant.NOT_NULL}"){
                                jboxInfoBasic("该信息考核时间与已预约的考核时间重叠,请重新选择！",width);
                                return false;
                            }else{
                                toOrderedBeforCheck(clinicalFlow,doctorFlow,appointNum,clinicalYear,orgFlow);
                            }
                        }, null, false);
                    }
                }
            }else{
                var url2= "<s:url value='/osca/oscaDoctorOrdered/checkExamTime'/>?doctorFlow="+doctorFlow+"&examStartTime="+examStartTime+"&examEndTime="+examEndTime+"&clinicalFlow="+clinicalFlow;
                jboxPost(url2, null,function(resp2){
                    if(resp2 == "${GlobalConstant.NOT_NULL}"){
                        jboxInfoBasic("该信息考核时间与已预约的考核时间重叠,请重新选择！",width);
                        return false;
                    }else{
                        toOrderedBeforCheck(clinicalFlow,doctorFlow,appointNum,clinicalYear,orgFlow);
                    }
                }, null, false);
            }
        }

        function toOrderedBeforCheck(clinicalFlow,doctorFlow,appointNum,clinicalYear,orgFlow,flag){
            var width=(window.screen.width)*0.3;
            var isLocal='${isLocal}';
            var url = "<s:url value='/osca/oscaDoctorOrdered/searchOldOrdered'/>?doctorFlow="+doctorFlow+"&isLocal="+isLocal+"&clinicalYear="+clinicalYear+"&orgFlow="+orgFlow;
            jboxPost(url, null,function(resp){
                var msg="确定预约此次考核吗？";
                <%--if(resp == "${GlobalConstant.NOT_NULL}"){--%>
                    <%--jboxInfoBasic("你本年度已经参加过一次结业考核！",width);--%>
                    <%--return false;--%>
                <%--}--%>
                if(resp == "${GlobalConstant.OPERATE_SUCCESSED}"){
                    if(${auditStatusEnumPassing eq doctorAssessmentInfo.oscaDoctorAssessment.auditStatusId}){
                        msg="你已有一条考核预约信息，状态为待审核，是否继续预约？";
                    }
                    <%--if(${auditStatusEnumPassed eq doctorAssessmentInfo.oscaDoctorAssessment.auditStatusId--%>
                    <%--and doctorScoreEnumPassed ne doctorAssessmentInfo.oscaDoctorAssessment.isPass--%>
                    <%--and doctorScoreEnumUnPassed ne doctorAssessmentInfo.oscaDoctorAssessment.isPass}){--%>
                    <%--if(${pdfn:getCurrDateTime('yyyy-MM-dd HH:mm') ge doctorAssessmentInfo.examStartTime--%>
                    <%--and pdfn:getCurrDateTime('yyyy-MM-dd HH:mm') le doctorAssessmentInfo.examEndTime}){--%>
                    <%--jboxInfoBasic("你已有一条考核信息正在考核中！");--%>
                    <%--return false;--%>
                    <%--}--%>
                    <%--}--%>
                }
                jboxConfirm(msg,function(){
                    var url1 = "<s:url value='/osca/oscaDoctorOrdered/ordered'/>?clinicalFlow="+clinicalFlow+"&doctorFlow="+doctorFlow+"&appointNum="+appointNum+"&flag="+flag;
                    jboxStartLoading();
                    jboxPost(url1, null,function(resp1){
                        if(resp1 == "${GlobalConstant.SAVE_SUCCESSED}"){
                            top.jboxTip("预约成功！");
                            top.jboxClose();
                            window.parent.frames['mainIframe'].location.reload(true);
                        }
                        if(resp1=="${GlobalConstant.OPERATE_FAIL}"){
                            jboxTip("此条考核信息预约已满！");
                            jboxClose();
                            setTimeout(function(){
                                window.parent.frames['mainIframe'].location.reload(true);
                            },2000);
                        }
                        if(resp1=="${GlobalConstant.SAVE_FAIL}"){
                            top.jboxTip("预约失败！");
                        }
                    }, null, false);
                });
            }, null, false);
        }

        function toPage(page){
            var form = $("#searchForm");
            $("#currentPage").val(page);
            jboxStartLoading();
            form.submit();
        }

        function changeOrdered(recordFlow){
            jboxConfirm("确定取消此次预约吗？",function(){
                var url = "<s:url value='/osca/oscaDoctorOrdered/changeOrdered'/>?recordFlow="+recordFlow;
                jboxStartLoading();
                jboxPost(url, null,function(resp){
                    if(resp == "${GlobalConstant.SAVE_SUCCESSED}"){
                        top.jboxTip(resp);
                        top.jboxClose();
                        window.parent.frames['mainIframe'].location.reload(true);
                    }
                }, null, false);
            });
        }

        function searchInfo(){
            jboxStartLoading();
            $("#searchForm").submit();
        }

        var width=(window.screen.width)*0.6;
        var height=(window.screen.height)*0.6;
        function showTicket(doctorFlow,clinicalFlow){
            jboxOpen("<s:url value='/osca/oscaDoctorOrdered/showTicket'/>?doctorFlow="+doctorFlow+"&clinicalFlow="+clinicalFlow, "准考证信息", 800, 500);
        }
        var width1=(window.screen.width)*0.7;
        var height1=(window.screen.height)*0.7;
        function showScoreDetails(doctorFlow,clinicalFlow){
            jboxOpen("<s:url value='/osca/oscaDoctorOrdered/showScoreDetails'/>?doctorFlow="+doctorFlow+"&clinicalFlow="+clinicalFlow, "成绩详情", width1, height1);
        }



    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div style="width:100%;float: left" >
            <div class="main_hd" style="margin-left: 20px;">
                <div class="title_tab" style="margin-top: 0;">
                    <ul>
                        <li class="tab" id="firstLi"><a href="<s:url value='/osca/oscaDoctorOrdered/list'/>?isLocal=Y&liId=firstLi" id="tableLi1">院内考核</a></li>
                        <li class="tab" id="secondLi"><a href="<s:url value='/osca/oscaDoctorOrdered/list'/>?isLocal=N&liId=secondLi" id="tableLi2">结业考核</a></li>
                    </ul>
                </div>
            </div>
            <div class="main_bd" style="margin-left: 20px;" id="orderedList">
                <div id="hosContent" >
                    <form id="searchForm" action="<s:url value='/osca/oscaDoctorOrdered/list'/>"
                          method="post">
                        <div style="margin-bottom: 10px;" align="left">
                            <br/>
                            <input type="hidden" id="currentPage" name="currentPage" value="${currentPage}">
                            <input type="hidden" name="isLocal" value="${isLocal}">
                            <input type="hidden" name="liId" value="${liId}">
                            <c:if test="${liId eq 'secondLi'}">
                                考点：
                                <input class="qtext" name="orgName" value="${param.orgName}" type="text" />
                            </c:if>
                            &#12288;
                            <input id="searchNotFull" name="searchNotFull" <c:if test="${param.searchNotFull == 'on'}">checked="checked"</c:if> type="checkbox" />
                            查看预约人员未满考核信息&#12288;
                            <input type="button" value="查&#12288;询" class="search" onclick="searchInfo();"/>
                        </div>
                    </form>
                    <div id="base">
                        <table  class="xllist">
                            <colgroup>
                                <col width="4%"/>
                                <col width="9%"/>
                                <col width="9%"/>
                                <col width="14%"/>
                                <col width="9%"/>
                                <col width="9%"/>
                                <col width="14%"/>
                                <col width="14%"/>
                                <col width="9%"/>
                                <col width="9%"/>
                            </colgroup>
                            <tr>
                                <th  style="text-align: center;">序号</th>
                                <th  style="text-align: center;">考核名称</th>
                                <th  style="text-align: center;">考核专业</th>
                                <th  style="text-align: center;">预约开放时间</th>
                                <th  style="text-align: center;">预约人员容量</th>
                                <th  style="text-align: center;">剩余人员容量</th>
                                <th  style="text-align: center;">考核时间</th>
                                <th  style="text-align: center;">考点</th>
                                <th  style="text-align: center;">备注</th>
                                <th  style="text-align: center;">操作</th>
                            </tr>
                            <c:if test="${isOsca ne 'Y'}">
                                <c:if test="${not empty skillsAssessmentList}">
                                    <c:forEach items="${skillsAssessmentList}" var="skillsAssessment" varStatus="num">
                                        <tr id="baseTr">
                                            <td>${num.count}</td>
                                            <td>${skillsAssessment.clinicalName}</td>
                                            <td>${skillsAssessment.speName}</td>
                                            <td>${skillsAssessment.appointStartTime}~<br/>${skillsAssessment.appointEndTime}</td>
                                            <td>${skillsAssessment.appointNum}</td>
                                            <td>${skillsAssessment.overplus}</td>
                                            <td class="differ" title="<table><tr><th><c:if test="${empty skillsAssessment.examStartTimeList}">暂无考核时间</c:if><c:forEach items="${fn:split(skillsAssessment.examStartTimeList,',')}" var="startTime" varStatus="i">${startTime}
						        <c:forEach items="${fn:split(skillsAssessment.examEndTimeList,',')}" var="endTime" varStatus="j"><c:if test="${i.index eq j.index}">${not empty endTime?'~':''}${endTime}<br/></c:if></c:forEach></c:forEach>
					                </th></tr></table>">
                                                <c:forEach items="${fn:split(skillsAssessment.examStartTimeList,',')}" var="startTime" varStatus="i">
                                                    <c:if test="${i.first}">${startTime}</c:if>
                                                </c:forEach>
                                                <c:forEach items="${fn:split(skillsAssessment.examEndTimeList,',')}" var="endTime" varStatus="i">
                                                    <c:if test="${i.first}">${not empty endTime?'~':''}<br/>${endTime}</c:if>
                                                    <c:if test="${i.last}">${i.count gt 1?'<br/>......':''}</c:if>
                                                </c:forEach>
                                            </td>
                                            <td>${skillsAssessment.orgName}</td>
                                            <td>${skillsAssessment.remarks}</td>
                                            <c:if test="${not empty skillsAssessments}">
                                                <c:forEach items="${skillsAssessments}" var="sam" end="${i}">
                                                    <c:choose>
                                                        <c:when test="${sam.clinicalFlow eq skillsAssessment.clinicalFlow and sam.oscaDoctorAssessment.auditStatusId eq auditStatusEnumPassed}">
                                                            <td id="baseTd"><input class="btn_grey" type="button" name="already" value="已预约"/></td>
                                                            <c:set var="i" value="0"></c:set>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:choose>
                                                                <c:when test="${skillsAssessment.oscaDoctorAssessment.auditStatusId eq auditStatusEnumPassing}">
                                                                    <td id="baseTd"><input class="btn_red" type="button" name="cancelOrdered" value="取消预约" onclick="changeOrdered('${skillsAssessment.oscaDoctorAssessment.recordFlow}')"/></td>
                                                                    <c:set var="i" value="0"></c:set>
                                                                </c:when>
                                                                <c:when test="${skillsAssessment.oscaDoctorAssessment.auditStatusId eq auditStatusEnumUnPassed}">
                                                                    <td id="baseTd"><input type="button" name="ordered" value="预约"
                                                                                           <c:if test="${skillsAssessment.overplus != 0}">class="btn_blue" </c:if>
                                                                                           <c:if test="${skillsAssessment.overplus == 0}">class="btn_grey" disabled="disabled"</c:if>
                                                                                           onclick="toOrdered('${skillsAssessment.clinicalFlow}','${doctorAssessmentInfo.oscaDoctorAssessment.doctorFlow}','${skillsAssessment.appointNum}','${skillsAssessment.oscaDoctorAssessment.examStartTime}','${skillsAssessment.oscaDoctorAssessment.examEndTime}','${skillsAssessment.clinicalYear}','${skillsAssessment.orgFlow}')"/></td>
                                                                    <c:set var="i" value="0"></c:set>
                                                                </c:when>
                                                                <c:when test="${skillsAssessment.oscaDoctorAssessment.auditStatusId eq auditStatusEnumPassed
                                            and skillsAssessment.clinicalName ne doctorAssessmentInfo.clinicalName
                                       and  doctorScoreEnumPassed eq doctorAssessmentInfo.oscaDoctorAssessment.isPass
                                     or doctorScoreEnumUnPassed eq doctorAssessmentInfo.oscaDoctorAssessment.isPass}">
                                                                    <td id="baseTd"><input type="button" name="ordered" value="预约"
                                                                                           <c:if test="${skillsAssessment.overplus != 0}">class="btn_blue" </c:if>
                                                                                           <c:if test="${skillsAssessment.overplus == 0}">class="btn_grey" disabled="disabled"</c:if>
                                                                                           onclick="toOrdered('${skillsAssessment.clinicalFlow}','${doctorAssessmentInfo.oscaDoctorAssessment.doctorFlow}','${skillsAssessment.appointNum}','${skillsAssessment.oscaDoctorAssessment.examStartTime}','${skillsAssessment.oscaDoctorAssessment.examEndTime}','${skillsAssessment.clinicalYear}','${skillsAssessment.orgFlow}')"/></td>
                                                                    <c:set var="i" value="0"></c:set>
                                                                </c:when>
                                                                <c:when test="${skillsAssessment.oscaDoctorAssessment.auditStatusId eq auditStatusEnumPassed}">
                                                                    <td id="baseTd"><input class="btn_grey" type="button" name="already" value="已预约"/></td>
                                                                    <c:set var="i" value="0"></c:set>
                                                                </c:when>
                                                                <c:when test="${empty skillsAssessment.oscaDoctorAssessment.auditStatusId }">
                                                                    <td id="baseTd"><input type="button" name="ordered" value="预约"
                                                                                           <c:if test="${skillsAssessment.overplus != 0}">class="btn_blue" </c:if>
                                                                                           <c:if test="${skillsAssessment.overplus == 0}">class="btn_grey" disabled="disabled"</c:if>
                                                                                           onclick="toOrdered('${skillsAssessment.clinicalFlow}','${doctorAssessmentInfo.oscaDoctorAssessment.doctorFlow}','${skillsAssessment.appointNum}','${skillsAssessment.oscaDoctorAssessment.examStartTime}','${skillsAssessment.oscaDoctorAssessment.examEndTime}','${skillsAssessment.clinicalYear}','${skillsAssessment.orgFlow}')"/></td>
                                                                    <c:set var="i" value="0"></c:set>
                                                                </c:when>
                                                                <c:when test="${skillsAssessment.oscaDoctorAssessment.auditStatusId eq auditStatusEnumPassing
                                                and pdfn:getCurrDateTime('yyyy-MM-dd HH:mm') gt skillsAssessment.appointEndTime}">
                                                                    <td id="baseTd"><input class="btn_grey" type="button" name="already" value="已预约"/></td>
                                                                    <c:set var="i" value="0"></c:set>
                                                                </c:when>
                                                            </c:choose>
                                                        </c:otherwise>
                                                    </c:choose>

                                                </c:forEach>
                                            </c:if>
                                            <c:if test="${empty skillsAssessments }">
                                                <c:choose>
                                                    <c:when test="${skillsAssessment.oscaDoctorAssessment.auditStatusId eq auditStatusEnumPassing}">
                                                        <td id="baseTd"><input class="btn_red" type="button" name="cancelOrdered" value="取消预约" onclick="changeOrdered('${skillsAssessment.oscaDoctorAssessment.recordFlow}')"/></td>
                                                    </c:when>
                                                    <c:when test="${skillsAssessment.oscaDoctorAssessment.auditStatusId eq auditStatusEnumUnPassed}">
                                                        <td id="baseTd"><input type="button" name="ordered" value="预约"
                                                                               <c:if test="${skillsAssessment.overplus != 0}">class="btn_blue" </c:if>
                                                                               <c:if test="${skillsAssessment.overplus == 0}">class="btn_grey" disabled="disabled"</c:if>
                                                                               onclick="toOrdered('${skillsAssessment.clinicalFlow}','${doctorAssessmentInfo.oscaDoctorAssessment.doctorFlow}','${skillsAssessment.appointNum}','${skillsAssessment.oscaDoctorAssessment.examStartTime}','${skillsAssessment.oscaDoctorAssessment.examEndTime}','${skillsAssessment.clinicalYear}','${skillsAssessment.orgFlow}')"/></td>
                                                    </c:when>
                                                    <c:when test="${skillsAssessment.oscaDoctorAssessment.auditStatusId eq auditStatusEnumPassed
                                            and skillsAssessment.clinicalName ne doctorAssessmentInfo.clinicalName
                                       and  doctorScoreEnumPassed eq doctorAssessmentInfo.oscaDoctorAssessment.isPass
                                     or doctorScoreEnumUnPassed eq doctorAssessmentInfo.oscaDoctorAssessment.isPass}">
                                                        <td id="baseTd"><input type="button" name="ordered" value="预约"
                                                                               <c:if test="${skillsAssessment.overplus != 0}">class="btn_blue" </c:if>
                                                                               <c:if test="${skillsAssessment.overplus == 0}">class="btn_grey" disabled="disabled"</c:if>
                                                                               onclick="toOrdered('${skillsAssessment.clinicalFlow}','${doctorAssessmentInfo.oscaDoctorAssessment.doctorFlow}','${skillsAssessment.appointNum}','${skillsAssessment.oscaDoctorAssessment.examStartTime}','${skillsAssessment.oscaDoctorAssessment.examEndTime}','${skillsAssessment.clinicalYear}','${skillsAssessment.orgFlow}')"/></td>
                                                    </c:when>
                                                    <c:when test="${skillsAssessment.oscaDoctorAssessment.auditStatusId eq auditStatusEnumPassed}">
                                                        <td id="baseTd"><input class="btn_grey" type="button" name="already" value="已预约"/></td>
                                                    </c:when>
                                                    <c:when test="${empty skillsAssessment.oscaDoctorAssessment.auditStatusId }">
                                                        <td id="baseTd"><input type="button" name="ordered" value="预约"
                                                                               <c:if test="${skillsAssessment.overplus != 0}">class="btn_blue" </c:if>
                                                                               <c:if test="${skillsAssessment.overplus == 0}">class="btn_grey" disabled="disabled"</c:if>
                                                                               onclick="toOrdered('${skillsAssessment.clinicalFlow}','${doctorAssessmentInfo.oscaDoctorAssessment.doctorFlow}','${skillsAssessment.appointNum}','${skillsAssessment.oscaDoctorAssessment.examStartTime}','${skillsAssessment.oscaDoctorAssessment.examEndTime}','${skillsAssessment.clinicalYear}','${skillsAssessment.orgFlow}')"/></td>
                                                    </c:when>
                                                    <c:when test="${skillsAssessment.oscaDoctorAssessment.auditStatusId eq auditStatusEnumPassing
                                                and pdfn:getCurrDateTime('yyyy-MM-dd HH:mm') gt skillsAssessment.appointEndTime}">
                                                        <td id="baseTd"><input class="btn_grey" type="button" name="already" value="已预约"/></td>
                                                    </c:when>
                                                </c:choose>
                                            </c:if>

                                        </tr>
                                    </c:forEach>
                                </c:if>
                            </c:if>
                                <c:if test="${empty skillsAssessmentList}">
                                    <tr><td colspan="99">暂无记录</td></tr>
                                </c:if>
                        </table>
                        <div>
                            <c:if test="${searchFlag eq 'N' and not empty skillsAssessmentList and isOsca ne 'Y'}">
                                <c:set var="pageView" value="${pdfn:getPageView(skillsAssessmentList)}" scope="request"></c:set>
                                <pd:pagination toPage="toPage"/>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
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
    </div>
    <div id="printTicket" hidden="hidden"></div>
</div>
</body>
</body>
</html>
