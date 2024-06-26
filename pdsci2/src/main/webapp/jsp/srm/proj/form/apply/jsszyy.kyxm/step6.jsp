<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<c:if test="${param.view != GlobalConstant.FLAG_Y}">
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="ueditor" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function nextOpt(step){
            if(false==$("#projForm").validationEngine("validate")){
                return false;
            }
            var form = $('#projForm');
            form.append('<input type="hidden" name="nextPageName" value="'+step+'"/>');
            $('#nxt').attr({"disabled":"disabled"});
            $('#prev').attr({"disabled":"disabled"});
            jboxStartLoading();
            form.submit();
        }
        $(document).ready(function(){
            var ue1 = initUEditer("researchContent");
            var ue2 = initUEditer("researchMethod");
            var ue3 = initUEditer("solveProblem");
            var ue4 = initUEditer("specialAndResult");
        });
    </script>
</c:if>

<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" name="projForm" style="position: relative;">
    <input type="hidden" name="pageName" value="step6" />
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}" />
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}" />
    <input type="hidden" name="recTypeId" value="${param.recTypeId}" />

    <font style="font-size: 14px; font-weight:bold;color: #333; ">五、研究目标、可行性分析</font>
    <table class="basic" style="width: 100%;margin-top: 10px;">
        <tr>
            <td style="text-align:left;">
                <p style="line-height: 25px">1．研究目标（限60字以内）</p>
                <textarea placeholder=""  class="validate[maxSize[60]] xltxtarea" style="height: 50px;" name="researchTarget">${resultMap.researchTarget}</textarea>
            </td>
        </tr>
        <tr>
            <td style="text-align:left;">
                <p style="line-height: 25px">2．研究内容</p>
                <%--<textarea placeholder=""  class="xltxtarea" style="height: 150px;" name="researchContent">${resultMap.researchContent}</textarea>--%>
                <c:choose>
                    <c:when test="${param.view==GlobalConstant.FLAG_Y}">${resultMap.researchContent}</c:when>
                    <c:otherwise>
                        <script id="researchContent" type="text/plain" name="researchContent" style="width:100%;height:200px;margin-right: 10px;">${resultMap.researchContent}</script>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr>
            <td style="text-align:left;">
                <p style="line-height: 25px">3．研究方法、技术路线、可行性分析</p>
                <%--<textarea placeholder=""  class="xltxtarea" style="height: 150px;" name="researchMethod">${resultMap.researchMethod}</textarea>--%>
                <c:choose>
                    <c:when test="${param.view==GlobalConstant.FLAG_Y}">${resultMap.researchMethod}</c:when>
                    <c:otherwise>
                        <script id="researchMethod" type="text/plain" name="researchMethod" style="width:100%;height:200px;margin-right: 10px;">${resultMap.researchMethod}</script>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr>
            <td style="text-align:left;">
                <p style="line-height: 25px">4．本课题拟解决的关键问题</p>
                <%--<textarea placeholder=""  class="xltxtarea" style="height: 150px;" name="solveProblem">${resultMap.solveProblem}</textarea>--%>
                <c:choose>
                    <c:when test="${param.view==GlobalConstant.FLAG_Y}">${resultMap.solveProblem}</c:when>
                    <c:otherwise>
                        <script id="solveProblem" type="text/plain" name="solveProblem" style="width:100%;height:200px;margin-right: 10px;">${resultMap.solveProblem}</script>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr>
            <td style="text-align:left;">
                <p style="line-height: 25px">5．本课题的特色、创新点及预期研究结果</p>
                <%--<textarea placeholder=""  class="xltxtarea" style="height: 150px;" name="specialAndResult">${resultMap.specialAndResult}</textarea>--%>
                <c:choose>
                    <c:when test="${param.view==GlobalConstant.FLAG_Y}">${resultMap.specialAndResult}</c:when>
                    <c:otherwise>
                        <script id="specialAndResult" type="text/plain" name="specialAndResult" style="width:100%;height:200px;margin-right: 10px;">${resultMap.specialAndResult}</script>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>

    </table>

</form>

<c:if test="${param.view!=GlobalConstant.FLAG_Y}">
    <div align="center" style="margin-top: 10px">
        <input id="prev" type="button" onclick="nextOpt('step5')" class="search" value="上一步"/>
        <input id="nxt" type="button" onclick="nextOpt('step7')" class="search" value="保&#12288;存"/>
    </div>
</c:if>
