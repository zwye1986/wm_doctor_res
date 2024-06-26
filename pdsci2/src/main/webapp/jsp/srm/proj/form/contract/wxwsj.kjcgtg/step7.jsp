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
    <input type="hidden" id="pageName" name="pageName" value="step7"/>
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>

    <font style="font-size: 14px; font-weight:bold;color: #333;">六、推广工作目标和效益</font>
    <table width="100%" cellspacing="0" cellpadding="0" class="basic">
        <tr>
            <th style="text-align: left;">
                （一）推广工作目标
            </th>
        </tr>
        <tr>
            <td><span>1. 项目工作基本目标：<br/>&#12288;&#12288;（按照项目验收“基本指标”要求填写，包括推广项目覆盖面、工作任务完成、培养技术骨干人数及达到水平等目标)</span></td>
        </tr>
        <tr>
            <td><textarea name="mainTarget" style="width:98%;height: 150px;"
                          class="validate[maxSize[1000]]">${resultMap.mainTarget}</textarea></td>
        </tr>
        <tr>
            <td><span>2.其他工作目标：<br/>&#12288;&#12288;（包括项目工作公开发表的论文、项目成效分析报告、项目政策建议书等）</span></td>
        </tr>
        <tr>
            <td><textarea name="otherTarget" style="width:98%;height: 150px;"
                          class="validate[maxSize[1000]]">${resultMap.otherTarget}</textarea></td>
        </tr>
    </table>
    <table width="100%" cellspacing="0" cellpadding="0" class="basic">
        <tr>
            <th style="text-align: left;">
                （二）推广工作预期健康效益和社会效益
            </th>
        </tr>
        <tr>
            <td><textarea name="expectBenefit" style="width:98%;height: 150px;"
                          class="validate[maxSize[1000]]">${resultMap.expectBenefit}</textarea></td>
        </tr>
    </table>

    <c:if test="${param.view!=GlobalConstant.FLAG_Y}">
        <div align="center" style="margin-top: 10px">
            <input id="prev" type="button" onclick="nextOpt('step6')" class="search" value="上一步"/>
            <input id="nxt" type="button" onclick="nextOpt('step8')" class="search" value="下一步"/>
        </div>
    </c:if>
</form>
