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
    </script>
</c:if>


<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" style="position: relative;">
    <input type="hidden" name="pageName" value="step7"/>
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" name="recTypeId" value="${param.recTypeId}"/>

    <table class="basic" style="width: 100%;margin-top: 20px;">
        <tr>
            <th colspan="5" style="text-align: left">（二）引进团队临床学科建设</th>
        </tr>
        <tr>
            <th style="text-align: center">学科带头人</th>
            <th style="text-align: center">学术骨干</th>
            <th style="text-align: center">总人数</th>
            <th style="text-align: center">高职人员数</th>
            <th style="text-align: center">中级人员数</th>
        </tr>
        <tr>
            <td style="text-align: center">
                <input type="text" class="inputText" name="teamSubjLeader" value="${resultMap.teamSubjLeader}" style="width: 80%" />
            </td>
            <td style="text-align: center">
                <input type="text" class="inputText" name="teamSubjBackbone" value="${resultMap.teamSubjBackbone}" style="width: 80%" />
            </td>
            <td style="text-align: center">
                <input type="text" class="inputText" name="teamSubjTotal" value="${resultMap.teamSubjTotal}" style="width: 80%" />
            </td>
            <td style="text-align: center">
                <input type="text" class="inputText" name="teamSubjSenior" value="${resultMap.teamSubjSenior}" style="width: 80%" />
            </td>
            <td style="text-align: center">
                <input type="text" class="inputText" name="teamSubjMiddle" value="${resultMap.teamSubjMiddle}" style="width: 80%" />
            </td>
        </tr>
    </table>
    <table class="basic" style="width: 100%;margin-top: 20px;">
        <tr>
            <th style="text-align: left">（三）引进团队的科研工作</th>
        </tr>
        <tr>
            <td>
                <p>研究工作的创新性构思、主要目标、研究内容、研究方案、解决的关键问题，预期成果。2000字以内</p>
                <textarea class="xltxtarea validate[maxSize[2000]]" name="teamWork" style="width:98%;height: 300px;">${resultMap.teamWork}</textarea>
            </td>
        </tr>
    </table>
</form>

<div class="button" style="width:100%;
<c:if test="${param.view == GlobalConstant.FLAG_Y}">display:none;</c:if>">
    <input id="prev" type="button" onclick="nextOpt('step6')" class="search" value="上一步"/>
    <input id="nxt" type="button" onclick="nextOpt('step8')" class="search" value="下一步"/>
</div>
   