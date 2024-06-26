<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:if test="${param.view != GlobalConstant.FLAG_Y}">
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="ueditor" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function nextOpt(step) {
            if (false == $("#projForm").validationEngine("validate")) {
                return false;
            }
            var form = $('#projForm');
            var action = form.attr('action');
            action += "?nextPageName=" + step;
            form.attr("action", action);
            form.submit();
        }

        $(document).ready(function(){
            var ue1 = initUEditer("otherApplyProj");
            var ue2 = initUEditer("relevantThisTopic");
            var ue2 = initUEditer("otherTopic");
        });
    </script>
</c:if>

<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" style="position: relative;" id="projForm">
    <input type="hidden" id="pageName" name="pageName" value="step10"/>
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>

    <font style="font-size: 14px; font-weight:bold;color: #333;">九、课题第一申请人情况表</font>
    <table class="basic" style="width: 100%; margin-top: 10px;">
        <tr>
            <td>
                <p style="line-height: 25px;margin-top: 10px">正在承担的其他科研项目(请列明任务来源、课题名称、研究起止年月、本人在该课题中的任务和分工)</p>
                <%--<textarea class="xltxtarea" style="height: 150px;" name="otherApplyProj">${resultMap.otherApplyProj}</textarea>--%>
                <c:choose>
                    <c:when test="${param.view==GlobalConstant.FLAG_Y}">${resultMap.otherApplyProj}</c:when>
                    <c:otherwise>
                        <script id="otherApplyProj" type="text/plain" name="otherApplyProj" style="width:100%;height:200px;margin-right: 10px;">${resultMap.otherApplyProj}</script>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr>
            <td>
                <p style="line-height: 25px;margin-top: 10px">以往研究工作成果(论文、著作目录及获学术奖励或已经研究开发的上市新药、获得的专利等情况)</p>
                <p style="margin-top: 10px">1．与本课题相关的研究成果</p>
                <%--<textarea class="xltxtarea" style="height: 150px;" name="relevantThisTopic">${resultMap.relevantThisTopic}</textarea>--%>
                <c:choose>
                    <c:when test="${param.view==GlobalConstant.FLAG_Y}">${resultMap.relevantThisTopic}</c:when>
                    <c:otherwise>
                        <script id="relevantThisTopic" type="text/plain" name="relevantThisTopic" style="width:100%;height:200px;margin-right: 10px;">${resultMap.relevantThisTopic}</script>
                    </c:otherwise>
                </c:choose>
                <p style="margin-top: 10px">2．其他领域的研究成果</p>
                <%--<textarea class="xltxtarea" style="height: 150px;" name="otherTopic">${resultMap.otherTopic}</textarea>--%>
                <c:choose>
                    <c:when test="${param.view==GlobalConstant.FLAG_Y}">${resultMap.specialAndResult}</c:when>
                    <c:otherwise>
                        <script id="otherTopic" type="text/plain" name="otherTopic" style="width:100%;height:200px;margin-right: 10px;">${resultMap.otherTopic}</script>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </table>
</form>
</form>

<c:if test="${param.view!=GlobalConstant.FLAG_Y}">
    <div align="center" style="margin-top: 10px">
        <input id="prev" type="button" onclick="nextOpt('step9')" class="search" value="上一步"/>
        <input id="nxt" type="button" onclick="nextOpt('step11')" class="search" value="保&#12288;存"/>
    </div>
</c:if>	