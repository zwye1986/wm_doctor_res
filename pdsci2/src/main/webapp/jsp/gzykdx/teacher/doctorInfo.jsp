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
    <script>
        var batchMap = {"0":"无","1":"第一批次","2":"第二批次","3":"第三批次","4":"第四批次","5":"第五批次","6":"第六批次",
            "7":"第七批次","8":"第八批次","9":"第九批次","10":"第十批次"}
        function toPage(page){
            if(page){
                search(page);
            }
        }
        function search(page){
            $("#currentPage").val(page);
            $("#gradeForm").submit();
        }
        function detail(flow){
            jboxOpen("<s:url value='/gzykdx/recruit/getDetail'/>?userFlow="+flow, "复试考生信息",800,600);
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div id="gradeDiv" class="labelDiv">
            <form id="gradeForm" action="<s:url value="/gzykdx/teaAndDoc/doctorAdmissionList"/>" method="post">
                <div class="choseDivNewStyle">
                    <input id="currentPage" type="hidden" name="currentPage" value="${currentPage}"/>
                    <table class="basic" style="width:100%;border:0px;margin:10px 0px;">
                        <tr>
                            <td style="border:0px;">
                                <span style="margin-left: -10px;"></span>考生编号：
                                <input type="text" name="doctorCode" value="${param.doctorCode}" style="width:137px;"/><span style="padding-left:10px;"></span>
                                姓&#12288;&#12288;名：
                                <input type="text" name="doctorName" value="${param.doctorName}" style="width:137px;"/>
                                <span style="padding-left:10px;"></span>专业名称：
                                <select name="speId" style="width:137px;" class="select">
                                    <option value="">全部</option>
                                    <c:forEach items="${dictTypeEnumGzykdxSpeList}" var="status">
                                        <option value="${status.dictId}" ${param.speId eq status.dictId ?'selected':''}>${status.dictName}[${status.dictId}]</option>
                                    </c:forEach>
                                </select><span style="padding-left:10px;"></span>
                                录取状态：
                                <select name="schoolAuditStatusId" style="width:137px;" class="select">
                                    <option value="">全部</option>
                                    <c:forEach items="${gzykdxAdmissionStatusEnumList}" var="status">
                                        <option value="${status.id}" ${param.schoolAuditStatusId eq status.id ?'selected':''}>${status.name}</option>
                                    </c:forEach>
                                </select>
                                <span style="padding-left:20px;"></span><br/>
                                <span style="margin-left: -10px;"></span>年&#12288;&#12288;份：
                                <input type="text" name="recruitYear" value="${(empty param.recruitYear)?recruitYear:param.year}" style="width:137px;" onClick="WdatePicker({dateFmt:'yyyy'})"/>
                                <span style="padding-left:10px;"></span>学位类型：
                                <select name="degreeTypeId" style="width:140px;" class="select">
                                    <option value="">全部</option>
                                    <c:forEach items="${gzykdxDegreeTypeEnumList }" var="dict">
                                        <option <c:if test="${param.degreeTypeId eq dict.id }">selected="selected"</c:if>
                                                value="${dict.id }">${dict.name }</option>
                                    </c:forEach>
                                </select><span style="padding-left:10px;"></span>
                                研究方向：
                                <input type="text" name="researchDirectionName" value="${param.researchDirectionName}" style="width:137px;"/>
                                <%--<select name="researchDirectionId" class="select" style="width:138px;">--%>
                                    <%--<option value="">请选择</option>--%>
                                    <%--<c:forEach items="${dictTypeEnumResearchAreaList }" var="dict">--%>
                                        <%--<option--%>
                                                <%--<c:if test="${param.researchDirectionId eq dict.dictId }">selected="selected"</c:if>--%>
                                                <%--value="${dict.dictId }">${dict.dictName }</option>--%>
                                    <%--</c:forEach>--%>
                                <%--</select>--%>
                                &#12288;
                                <input type="button" class="search" value="查&#12288;询" onclick="toPage(1)"/>
                            </td>
                        </tr>
                    </table>
                </div>
            </form>
            <table class="xllist" style="width:100%;">
                <tr>
                    <th>考生编号</th>
                    <th>姓名</th>
                    <th>复试号</th>
                    <th>性别</th>
                    <th style="max-width: 200px;">研究方向</th>
                    <th>报考专业</th>
                    <th>学位类型</th>
                    <th>报考类型</th>
                    <th>初试成绩</th>
                    <th>复试成绩</th>
                    <th>总成绩</th>
                    <th>调剂批次</th>
                    <th>录取状态</th>
                </tr>
                <c:forEach items="${doctorAdmissionList}" var="info" varStatus="i">
                    <tr>
                        <td>${info.USER_CODE}</td>
                        <td>
                            <a onclick="detail('${info.USER_FLOW}')" style="cursor: pointer">${info.USER_NAME}</a>
                        </td>
                        <td>${info.infoForm.fsh}</td>
                        <td>${info.SEX_NAME}</td>
                        <td style="max-width: 200px;">${info.RESEARCH_AREA_NAME}</td>
                        <td>${info.SPE_NAME}</td>
                        <td>${info.DEGREE_TYPE_NAME}</td>
                        <td>${info.RECRUIT_TYPE_NAME}</td>
                        <td>${info.infoForm.cscj}</td>
                        <td>${info.infoForm.fscj}</td>
                        <td>${info.infoForm.zcj}</td>
                        <td id="batch${info.USER_CODE}"></td>
                        <script>
                            $("#batch${info.USER_CODE}").text(batchMap["${info.RECRUIT_BATCH}"]);
                        </script>
                        <td>${info.SCHOOL_AUDIT_STATUS_NAME}</td>
                    </tr>
                </c:forEach>
                <c:if test="${empty doctorAdmissionList}">
                    <tr><td colspan="99">暂无记录</td></tr>
                </c:if>
            </table>
            <div id="detail"></div>
            <div style="margin-top:100px;">
                <c:if test="${not empty doctorAdmissionList}">
                <c:set var="pageView" value="${pdfn:getPageView(doctorAdmissionList)}" scope="request"/>
                <pd:pagination toPage="toPage"/>
                </c:if>
            </div>
        </div>
    </div>
</div>
</body>
</html>
