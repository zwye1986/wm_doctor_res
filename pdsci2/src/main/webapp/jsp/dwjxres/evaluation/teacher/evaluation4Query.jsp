<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript">
    //分页
    function toPage(page) {
        if (page != undefined) {
            $('#currentPage').val(page);
        }
        jboxPostLoad("tagContent", "<s:url value='/evaluation/manage/evaluationQuery'/>", $('#searchForm').serialize(), true);
    }
</script>
<div class="main_bd" id="div_table_0">
    <div class="div_table">
        <form id="searchForm">
            <input id="currentPage" type="hidden" name="currentPage" value="${param.currentPage}"/>
            <input id="roleId" type="hidden" name="roleId" value="${roleId}"/>
            <table>
                <tr>
                    <td>
                        带教老师：
                        <input class="input" type="text" name="teacherName" value="${param.teacherName}" style="width: 100px;"/>
                    </td>
                    <td>
                        &#12288;所在科室：
                        <select name="speId" class="select" style="width:107px;">
                            <option value="">全部</option>
                            <c:forEach items="${dwjxSpeLst}" var="dept">
                                <option value="${dept.dictId}"
                                        <c:if test="${param.speId eq dept.dictId}">selected</c:if>>${dept.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        &#12288;进修批次：
                        <select name="batchFlow" class="select" style="width:107px;">
                            <option value="">全部</option>
                            <c:forEach items="${batchLst}" var="batch">
                                <option value="${batch.batchFlow}"
                                        <c:if test="${param.batchFlow eq batch.batchFlow}">selected</c:if>>${batch.batchNo}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        &#12288;<input type="button" class="btn_green" onclick="toPage(1)" value="查询"/>
                    </td>
                    <td>
                    </td>
                </tr>
            </table>
        </form>

    </div>
    <div class="div_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th>序号</th>
                <th>学员</th>
                <th>进修批次</th>
                <th>评价老师</th>
                <th>所在科室</th>
                <th>评分</th>
            </tr>
            <c:forEach items="${infoList}" var="stuUser" varStatus="num">
                <tr>
                    <td>${num.count}</td>
                    <td>${stuUser.sysUser.userName}</td>
                    <td>${stuUser.stuBatName}</td>
                    <td>${stuUser.teacherName}</td>
                    <td>${stuUser.speName}</td>
                    <td>
                        <c:if test="${empty evalMap[stuUser.resumeFlow]}">
                            暂未评价
                        </c:if>
                        <c:if test="${not empty evalMap[stuUser.resumeFlow] and evalMap[stuUser.resumeFlow].isForm eq 'Y'}">
                            <a onclick="showEvalDetail('${evalMap[stuUser.resumeFlow].recordFlow}');">${evalMap[stuUser.resumeFlow].evalScore}</a>
                        </c:if>
                        <c:if test="${not empty evalMap[stuUser.resumeFlow] and evalMap[stuUser.resumeFlow].isForm ne 'Y'}">
                            <a onclick="showEvalDetail('${evalMap[stuUser.resumeFlow].recordFlow}');">查看</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty infoList}">
                <tr>
                    <td colspan="10">无记录！</td>
                </tr>
            </c:if>
        </table>
    </div>
    <div class="page" style="padding-right: 40px;">
        <c:set var="pageView" value="${pdfn:getPageView(infoList)}" scope="request"></c:set>
        <pd:pagination-jsres toPage="toPage"/>
    </div>
</div>
 