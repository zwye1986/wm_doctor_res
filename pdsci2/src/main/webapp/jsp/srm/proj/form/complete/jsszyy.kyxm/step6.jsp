<head>
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
                form.append("<input name='nextPageName' value='"+step+"' type='hidden'/>");
                $('#nxt').attr({"disabled":"disabled"});
                $('#prev').attr({"disabled":"disabled"});
                jboxStartLoading();
                form.submit();
            }
            $(document).ready(function(){
                initUEditer("expertSuggestion");
            });
        </script>
    </c:if>
</head>
<body>
<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" enctype="multipart/form-data" style="position: relative;">
    <input type="hidden" id="pageName" name="pageName" value="step6" />
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>

    <font style="font-size: 14px; font-weight:bold;color: #333; ">五、专家验收意见</font>

    <table class="basic" style="width: 100%">
        <tr>
            <td colspan="2">
                <%--<textarea style="height: 120px;width: 100%" name="expertSuggestion">${resultMap.expertSuggestion}</textarea>--%>
                <c:choose>
                    <c:when test="${param.view==GlobalConstant.FLAG_Y}">${resultMap.expertSuggestion}</c:when>
                    <c:otherwise>
                        <script id="expertSuggestion" type="text/plain" name="expertSuggestion" style="width:100%;height:400px;margin-right: 10px;">${resultMap.expertSuggestion}</script>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </table>
    <div class="button" style="width: 100%;
    <c:if test="${param.view == GlobalConstant.FLAG_Y}">display:none</c:if>">
        <input id="prev" type="button" onclick="nextOpt('step5')" class="search" value="上一步"/>
        <input id="nxt" type="button" onclick="nextOpt('step7')" class="search" value="保&#12288;存"/>
    </div>
</form>
