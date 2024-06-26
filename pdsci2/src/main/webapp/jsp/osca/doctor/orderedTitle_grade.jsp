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

            $(".stationSum").each(function(){
                var sum = 0;
                $(this).closest("tr").find(".station").each(function(){
                   var single = parseInt($(this).text());
                    sum+=single;
                });
                if(sum){
                    $(this).text(sum);
                }
            });
        });


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

        function searchScoreForm(clinicalFlow,doctorFlow,stationFlow){
            jboxOpen("<s:url value='/osca/base/searchScoreForm?doctorFlow='/>"+doctorFlow+"&stationFlow="
                    +stationFlow+"&clinicalFlow="+clinicalFlow, "评分表",600,600);
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
                        <li class="tab" id="firstLi"><a href="<s:url value='/osca/oscaDoctorOrdered/gradeList'/>?isLocal=Y&liId=firstLi" id="tableLi1">院内成绩</a></li>
                        <li class="tab" id="secondLi"><a href="<s:url value='/osca/oscaDoctorOrdered/gradeList'/>?isLocal=N&liId=secondLi" id="tableLi2">结业成绩</a></li>
                    </ul>
                </div>
            </div>

            <div class="main_bd" style="margin-left: 20px;">
                <br/>
                <form id="searchForm" action="<s:url value='/osca/oscaDoctorOrdered/gradeList'/>">
                    <input type="hidden" id="currentPage" name="currentPage" value="${currentPage}">
                    <input type="hidden" name="isLocal" value="${param.isLocal}">
                </form>
                </div>
                <table  class="xllist">
                    <tr>
                        <th  style="text-align: center;">姓名</th>
                        <th  style="text-align: center;">考核名称</th>
                        <th  style="text-align: center;">专业</th>
                        <th  style="text-align: center;">考核类型</th>
                        <th  style="text-align: center;">考核年份</th>
                        <c:forEach items="${stationNumList}" var="station">
                        <th  style="text-align: center;">第${station}站</th>
                        </c:forEach>
                        <th  style="text-align: center;">总分</th>
                        <th  style="text-align: center;">是否合格</th>

                    </tr>
                    <c:if test="${not empty resultMapList}">
                        <c:forEach items="${resultMapList}" var="result" varStatus="i">
                            <tr>
                                <td>${result.DOCTOR_NAME}</td>
                                <td>${result.CLINICAL_NAME}</td>
                                <td>${result.OSA_NAME}</td>
                                <td>
                                    <c:if test="${result.IS_LOCAL eq 'N'}">结业考核</c:if>
                                    <c:if test="${result.IS_LOCAL eq 'Y'}">院内考核</c:if>
                                </td>
                                <td>${result.CLINICAL_YEAR}</td>
                                <c:forEach items="${stationNumList}" var="station">
                                    <c:set value="STATION${station}" var="key"></c:set>
                                    <td class="station">
                                        <c:if test="${result.IS_SHOW_FROOM eq 'Y'}">
                                            <a onclick="searchScoreForm('${result.CLINICAL_FLOW}','${result.DOCTOR_FLOW}','${result[key][1]}')" style="cursor:pointer;color:#4195C5;">
                                                    ${result[key][0]}
                                            </a>
                                        </c:if>
                                        <c:if test="${result.IS_SHOW_FROOM ne 'Y'}">
                                            ${result[key][0]}
                                        </c:if>
                                    </td>
                                </c:forEach>
                                <td class="stationSum"></td>
                                <td>${result.IS_PASS_NAME}</td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <c:if test="${empty resultMapList}">
                        <tr><td colspan="99">暂无记录</td></tr>
                    </c:if>
                </table>
                <div>
                    <%--<c:if test="${liId eq 'thirdLi' and not empty skillsAssessmentList}">--%>
                        <c:set var="pageView" value="${pdfn:getPageView(resultMapList)}" scope="request"></c:set>
                        <pd:pagination toPage="toPage"/>
                    <%--</c:if>--%>
                </div>
            </div>

        </div>


        <div style="float: right;width:28%;margin-top: 80px;">

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
