<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript">
    //分页
    function toPage(page) {
        if (page != undefined) {
            $('#currentPage').val(page);
        }
        jboxPostLoad("tagContent", "<s:url value='/gzzyjxres/evaluation/evaluationQuery'/>", $('#searchForm').serialize(), true);
    }
</script>
<div class="main_bd" id="div_table_0">
    <div class="div_table">
        <form id="searchForm" action="<s:url value="/res/doc/backTrainCheck" />" method="post">
            <input id="currentPage" type="hidden" name="currentPage" value="${param.currentPage}"/>
            <input id="roleId" type="hidden" name="roleId" value="${roleId}"/>
            <table>
                <tr>
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
                        学员姓名：
                        <input class="input" type="text" name="doctorName" value="${param.doctorName}"
                               style="width: 100px;"/>
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
                <th>所在科室</th>
                <th>带教老师</th>
                <th>带教评分</th>
                <th>科室评分</th>
            </tr>
            <c:forEach items="${infoList}" var="stuUser" varStatus="num">
                <tr>
                    <td>${num.count}</td>
                    <td>${stuUser.sysUser.userName}</td>
                    <td>${stuUser.stuBatName}</td>
                    <td>${stuUser.speName}</td>
                    <td>${stuUser.teacherName}</td>
                    <td>
                        <c:set var="key" value="${stuUser.resumeFlow}&${stuUser.teacherFlow}"></c:set>
                        <c:if test="${empty evalMap[key]}">
                            暂未评价
                        </c:if>
                        <c:if test="${not empty evalMap[key] and evalMap[key].isForm eq 'Y'}">
                            <a onclick="showEvalDetail('${evalMap[key].recordFlow}');">${evalMap[key].evalScore}</a>
                        </c:if>
                        <c:if test="${not empty evalMap[key] and evalMap[key].isForm ne 'Y'}">
                            <a onclick="showEvalDetail('${evalMap[key].recordFlow}');">查看</a>
                        </c:if>
                    </td>
                    <td>
                        <c:set var="key" value="${stuUser.resumeFlow}&${stuUser.speId}"></c:set>
                        <c:if test="${empty evalMap[key]}">
                            暂未评价
                        </c:if>
                        <c:if test="${not empty evalMap[key] and evalMap[key].isForm eq 'Y'}">
                            <a onclick="showEvalDetail('${evalMap[key].recordFlow}');">${evalMap[key].evalScore}</a>
                        </c:if>
                        <c:if test="${not empty evalMap[key] and evalMap[key].isForm ne 'Y'}">
                            <a onclick="showEvalDetail('${evalMap[key].recordFlow}');">查看</a>
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
 