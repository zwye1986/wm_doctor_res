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
    <input type="hidden" id="pageName" name="pageName" value="step4"/>
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>

    <font style="font-size: 14px; font-weight:bold;color: #333;">三、研究试验方法及技术路线</font>
    <table width="100%" cellspacing="0" cellpadding="0" class="basic">
        <tr>
            <th style="text-align: left;">
                &#12288;&#12288;研究试验方法及技术路线（工艺路线）。
                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
            </th>
        </tr>
        <tr>
            <td><textarea name="craftRoute" style="width:98%;height: 150px;"
                          class="xltxtarea validate[required]">${resultMap.craftRoute}</textarea></td>
        </tr>

    </table>

    <c:if test="${param.view!=GlobalConstant.FLAG_Y}">
        <div align="center" style="margin-top: 10px">
            <input id="prev" type="button" onclick="nextOpt('step3')" class="search" value="上一步"/>
            <input id="nxt" type="button" onclick="nextOpt('step5')" class="search" value="下一步"/>
        </div>
    </c:if>
</form>
