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
    <input type="hidden" id="pageName" name="pageName" value="step6"/>
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>

    <font style="font-size: 14px; font-weight:bold;color: #333;">五、推广工作条件保障</font>
    <table width="100%" cellspacing="0" cellpadding="0" class="basic">
        <tr>
            <th style="text-align: left;">
                （一）项目推广单位工作条件保障
            </th>
        </tr>
        <tr>
            <td>&#12288;&#12288;（包括可提供的相关人员、技术支持和必要的设施装备和场地、材料及资金等）</td>
        </tr>
        <tr>
            <td><textarea name="extend_support" style="width:98%;height: 150px;"
                          class="validate[maxSize[1000]]">${resultMap.extend_support}</textarea></td>
        </tr>
    </table>
    <table width="100%" cellspacing="0" cellpadding="0" class="basic">
        <tr>
            <th style="text-align: left;">
                （二）受推单位工作条件保障
            </th>
        </tr>
        <tr>
            <td>&#12288;&#12288;（包括人员配备及相关资质、技术基础，有关设施设备及场地、环境条件及资金等）</td>
        </tr>
        <tr>
            <td><textarea name="accept_support" style="width:98%;height: 150px;"
                          class="validate[maxSize[1000]]">${resultMap.accept_support}</textarea></td>
        </tr>
    </table>
    <c:if test="${param.view!=GlobalConstant.FLAG_Y}">
        <div align="center" style="margin-top: 10px">
            <input id="prev" type="button" onclick="nextOpt('step5')" class="search" value="上一步"/>
            <input id="nxt" type="button" onclick="nextOpt('step7')" class="search" value="下一步"/>
        </div>
    </c:if>
</form>
