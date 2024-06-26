<head>
    <c:if test="${param.view != GlobalConstant.FLAG_Y}">
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
        </script>
    </c:if>
</head>
<body>
<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" enctype="multipart/form-data" style="position: relative;">
    <input type="hidden" id="pageName" name="pageName" value="step7" />
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>

    <font style="font-size: 14px; font-weight:bold;color: #333; ">六、项目管理部门意见</font>

    <table class="basic" style="width: 100%">
        <tr>
            <th style="text-align: left">科室审核意见</th>
        </tr>
        <tr>
            <td><textarea style="height: 120px;width: 100%" name="deptSuggestion">${resultMap.deptSuggestion}</textarea></td>
        </tr>
        <%--<tr>
            <th style="text-align: left">科技处审核意见</th>
        </tr>
        <tr>
            <td><textarea style="height: 120px;width: 100%" name="tecSuggestion">${resultMap.tecSuggestion}</textarea></td>
        </tr>--%>
    </table>
    <div class="button" style="width: 100%;
    <c:if test="${param.view == GlobalConstant.FLAG_Y}">display:none</c:if>">
        <input id="prev" type="button" onclick="nextOpt('step6')" class="search" value="上一步"/>
        <input id="nxt" type="button" onclick="nextOpt('step8')" class="search" value="保&#12288;存"/>
    </div>
</form>
