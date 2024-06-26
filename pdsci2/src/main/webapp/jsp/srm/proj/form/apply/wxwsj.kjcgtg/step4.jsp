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

    <font style="font-size: 14px; font-weight:bold;color: #333;">三、成果技术水平</font>
    <table width="100%" cellspacing="0" cellpadding="0" class="basic">
        <tr>
            <th style="text-align: left;">
                &#12288;&#12288;（包括该成果技术达到的水平及安全性，说明成果鉴定、获奖、专利、配套产品市场准入等证书获得具体名称、等级、授奖时间与单位等）
            </th>
        </tr>
        <tr>
            <td><textarea name="achievement_level" style="width:98%;height: 150px;"
                          class="validate[maxSize[1000]]">${resultMap.achievement_level}</textarea></td>
        </tr>

    </table>

    <c:if test="${param.view!=GlobalConstant.FLAG_Y}">
        <div align="center" style="margin-top: 10px">
            <input id="prev" type="button" onclick="nextOpt('step3')" class="search" value="上一步"/>
            <input id="nxt" type="button" onclick="nextOpt('step5')" class="search" value="下一步"/>
        </div>
    </c:if>
</form>
