<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="/jsp/common/htmlhead.jsp">
    <jsp:param name="ueditor" value="true"/>
</jsp:include>
<c:if test="${param.view != GlobalConstant.FLAG_Y}">
    <script type="text/javascript">
        function nextOpt(step) {
            if (false == $("#projForm").validationEngine("validate")) {
                return false;
            }
            var form = $('#projForm');
            form.append('<input type="hidden" name="nextPageName" value="' + step + '"/>');
            $('#nxt').attr({"disabled": "disabled"});
            $('#prev').attr({"disabled": "disabled"});
            jboxStartLoading();
            form.submit();
        }
        $(document).ready(function(){
            var ue1 = initUEditer("approveBasis");
        });
    </script>
</c:if>

<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post"
      id="projForm" style="position: relative;">
    <input type="hidden" id="pageName" name="pageName" value="step5"/>
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" name="recTypeId" value="${param.recTypeId}"/>

    <font style="font-size: 14px; font-weight:bold;color: #333;">四、立项依据</font>
    <table class="basic" style="width: 100%;margin-top: 10px;">
        <tr>
            <th class="theader" colspan="4" style="text-align: left;padding-left: 20px;">（研究意义、国内外研究现状及分析；附主要参考文献，限10篇以内）</th>
        </tr>
        <tr>
            <td style="text-align:left;">
                <%--<textarea placeholder=""  class="xltxtarea" style="height: 350px;" name="approveBasis">${resultMap.approveBasis}</textarea>--%>
                <c:choose>
                    <c:when test="${param.view==GlobalConstant.FLAG_Y}">${resultMap.approveBasis}</c:when>
                    <c:otherwise>
                        <script id="approveBasis" type="text/plain" name="approveBasis" style="width:100%;height:400px;margin-right: 10px;">${resultMap.approveBasis}</script>
                    </c:otherwise>
                </c:choose>

            </td>
        </tr>
    </table>

    <c:if test="${param.view != GlobalConstant.FLAG_Y}">
        <div align="center" style="margin-top: 10px">
            <input id="prev" type="button" onclick="nextOpt('step4')" class="search" value="上一步"/>
            <input id="nxt" type="button" onclick="nextOpt('step6')" class="search" value="保&#12288;存"/>
        </div>
    </c:if>
</form>

