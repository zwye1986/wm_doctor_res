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
            form.append("<input name='nextPageName' value='" + step + "' type='hidden'/>");
            $('#nxt').attr({"disabled": "disabled"});
            $('#prev').attr({"disabled": "disabled"});
            jboxStartLoading();
            form.submit();
        }
        $(document).ready(function(){
            initUEditer("supportPlan");
        });
    </script>
</c:if>

<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" style="position: relative;">
    <input type="hidden" id="pageName" name="pageName" value="step7"/>
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>

    <font style="font-size: 14px; font-weight:bold;color: #333;">七、合作协议</font>
    <table width="100%" cellspacing="0" cellpadding="0" class="basic">

        <tr>
            <td>
                <p>合作协议需明确以下重要因素：合作目标及双方责权利；团队带头人和骨干每年在苏工作时间；知识产权归属；协议薪酬制度,具体薪酬标准可另以补充协议形式明确；其它双方需明确的事项。</p>
                <c:choose>
                    <c:when test="${param.view==GlobalConstant.FLAG_Y}">
                        ${resultMap.supportPlan}
                    </c:when>
                    <c:otherwise>
                        <script id="supportPlan" name="supportPlan" type="text/plain" style="width:100%;height:150px;margin-right: 10px;">${resultMap.supportPlan}</script>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </table>

    <c:if test="${param.view!=GlobalConstant.FLAG_Y}">
        <div align="center" style="margin-top: 10px">
            <input id="prev" type="button" onclick="nextOpt('step6')" class="search" value="上一步"/>
            <input id="nxt" type="button" onclick="nextOpt('step8')" class="search" value="下一步"/>
        </div>
    </c:if>
</form>
