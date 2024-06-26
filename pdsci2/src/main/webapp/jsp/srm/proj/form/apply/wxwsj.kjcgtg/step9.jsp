<c:if test="${param.view != GlobalConstant.FLAG_Y}">

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
    </script>
</c:if>

<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" style="position: relative;">
    <input type="hidden" id="pageName" name="pageName" value="step9"/>
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>

    <font style="font-size: 14px; font-weight:bold;color: #333;">八、推广单位可提供的条件</font>
    <table width="100%" cellspacing="0" cellpadding="0" class="basic">
        <tr>
            <th style="text-align: left;">
                &#12288;&#12288; （说明项目推广单位可提供的相关人员、技术支持和必要的设施装备和场地、材料等）
            </th>
        </tr>
        <tr>
            <td><textarea name="extend_support" style="width:98%;height: 150px;"
                          class="validate[maxSize[1000]]">${resultMap.extend_support}</textarea></td>
        </tr>

    </table>

    <c:if test="${param.view!=GlobalConstant.FLAG_Y}">
        <div align="center" style="margin-top: 10px">
            <input id="prev" type="button" onclick="nextOpt('step8')" class="search" value="上一步"/>
            <input id="nxt" type="button" onclick="nextOpt('step10')" class="search" value="下一步"/>
        </div>
    </c:if>
</form>
