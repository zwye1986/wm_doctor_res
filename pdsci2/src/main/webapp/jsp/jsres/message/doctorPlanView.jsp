<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="jbox" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
    <script>

        // 显示专业简介信息
        function showSpeDescView(assignFlow){
            jboxOpen("<s:url value='/jsres/message/showPlanSpeDesc'/>?assignFlow=" + assignFlow, "专业简介", 700, 500);
        }

        // 点击报名 展示详细的报考信息
        function editDoctorRecruit(speId){
            var jointOrgFlow= $("#jointOrg").val();
            var jointOrgName= $("#jointOrg").find("option:selected").text();
            var speId = speId;
            var orgFlow = $("#orgFlow").val();
            var assignYear = $("#assignYear").val();
            var url = "<s:url value='/jsres/message/editDoctorRecruit'/>?openType=open&speId="+
                speId+"&orgFlow=${sysOrg.orgFlow}&assignYear=${param.assignYear}&jointOrgFlows="+jointOrgFlow+"&jointOrgNames="+jointOrgName;
            var title = "学员报名";
            jboxOpen(url, title, 900, 500);
        }

    </script>
    <div class="div_table" id="divTable" style="height: 580px; overflow-y: auto;">
        <form id="editForm" method="post">
            <c:if test="${signupBtnFlag eq 'N'}">
                <div style="color: red; margin-bottom: 15px;">${signupMsg}</div>
            </c:if>
            <input type="hidden" name="orgFlow" value="${sysOrg.orgFlow}"/>
            <table class="grid">
                <tr>
                    <th style="text-align: right;width: 15%;">招收基地：</th>
                    <td style="text-align: left;width: 35%;">
                        ${sysOrg.orgName}
                    </td>
                    <th style="text-align: right;width: 15%;">招收年份：</th>
                    <td style="text-align: left;width: 35%;">
                        ${param.assignYear}
                    </td>
                </tr>
                <c:if test="${not empty sysOrgList}">
                    <th style="text-align: right;width: 15%;">协同基地：</th>
                    <td style="text-align: left;width: 35%;">
                        <select class="select" name="jointOrgFlow" id="jointOrg">
                            <option value="">请选择</option>
                            <c:forEach items="${sysOrgList}" var="org">
                                <option value="${org.orgFlow}">${org.orgName} </option>
                            </c:forEach>
                        </select>
                    </td>
                </c:if>
            </table>
            <table border="0" cellpadding="0" cellspacing="0" class="grid" id="gridTable">
                <tr>
                    <th style="text-align: center;width: 180px;">招收专业</th>
                    <th style="text-align: center;width: 120px;">专业简介</th>
                    <th style="text-align: center;width: 80px;">招收人数</th>
                    <th style="text-align: center;width: 80px;">已录取人数</th>
                    <th style="text-align: center;width: 100px;">毕业专业要求</th>
                    <th style="text-align: center;width: 100px;">学历学位要求</th>
                    <th style="text-align: center;width: 100px;">操作</th>
                </tr>
                <c:forEach items="${orgSpeList}" var="orgSpe">
                    <tr>
                        <td style="text-align: center;width: 180px;">${orgSpe.SPE_NAME}</td>
                        <td style="text-align: center;width: 120px;">
                            <input type="hidden" name="recordFlow" value="${orgSpe.RECORD_FLOW}"/>
                            <a style="color: #459ae9;cursor: pointer" speDescData="" onclick="showSpeDescView('${orgSpe.RECORD_FLOW}')">[查看详情]</a>
                        </td>
                        <td style="text-align: center;width: 80px;">
                            ${empty orgSpe.ASSIGN_PLAN ? 0 : orgSpe.ASSIGN_PLAN}
                            <c:set var="assignPlan" value="${empty orgSpe.ASSIGN_PLAN ? 0 : orgSpe.ASSIGN_PLAN}"></c:set>
                        </td>
                        <td style="text-align: center;width: 80px;">
                            ${empty orgSpe.ASSIGN_REAL ? 0 : orgSpe.ASSIGN_REAL}
                            <c:set var="assignReal" value="${empty orgSpe.ASSIGN_REAL ? 0 : orgSpe.ASSIGN_REAL}"></c:set>
                        </td>
                        <td style="text-align: center;width: 100px;">
                            ${orgSpe.GRADUATE_SPE}
                        </td>
                        <td style="text-align: center;width: 100px;">
                            ${orgSpe.EDUCATION}
                        </td>
                        <td style="text-align: center;width: 100px;">
                            <c:if test="${signupBtnFlag eq 'Y' and assignPlan > 0 and assignPlan > assignReal}">
                                <c:if test="${speFlag eq 'Y' and orgSpe.SPE_NAME eq '全科'}">
                                    <a class="btn" href="javascript:void(0);" onclick="editDoctorRecruit('${orgSpe.SPE_ID}');">去报名</a>
                                </c:if>
                                <c:if test="${speFlag eq 'N'}">
                                    <a class="btn" href="javascript:void(0);" onclick="editDoctorRecruit('${orgSpe.SPE_ID}');">去报名</a>
                                </c:if>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty orgSpeList}">
                    <tr>
                        <td colspan="7">无记录</td>
                    </tr>
                </c:if>
            </table>
        </form>
        <div align="center" style="margin-top: 20px; margin-bottom:20px;">
            <input type="button" class="btn_green" onclick="jboxCloseMessager();" value="关&#12288;闭"/>
        </div>
    </div>