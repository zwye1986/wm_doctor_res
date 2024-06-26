<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<c:if test="${param.view != GlobalConstant.FLAG_Y}">
    <script type="text/javascript">
    function nextOpt(step){
    if(false==$("#projForm").validationEngine("validate")){
    return false;
    }
    var form = $('#projForm');
    form.append('<input type="hidden" name="nextPageName" value="'+step+'"/>');
    $('#nxt').attr({"disabled":"disabled"});
    $('#prev').attr({"disabled":"disabled"});
    jboxStartLoading();
    form.submit();
    }

    </script>
</c:if>

<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" style="position: relative;"
      id="projForm">
    <input type="hidden" id="pageName" name="pageName" value="step12"/>
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>

    <table class="basic" style="width: 100%; margin-top: 10px;">
        <tr>
            <th style="text-align: left;padding-left: 20px;">十七、审核意见</th>
        </tr>
        <tr>
            <td style="text-align: left;">&#12288;学术委员会对本《申报书》中各项内容的真实性、经费预算的合理性签署具体意见</td>
        </tr>
        <tr>
            <td>
                <textarea name="deptOpinion" style="width:98%;height: 150px;">${resultMap.deptOpinion}</textarea>
            </td>
        </tr>
        <tr>
            <td style="text-align: left;">&#12288;所在单位对医学重点学科（发展学科）培养支撑条件、培养经费签署具体意见</td>
        </tr>
        <tr>
            <td>
                <textarea name="conditionsOpinion" style="width:98%;height: 150px;">${resultMap.conditionsOpinion}</textarea>
            </td>
        </tr>

    </table>
    <c:if test="${param.view != GlobalConstant.FLAG_Y}">
        <div class="button" style="width: 100%;">
            <input id="prev" type="button" onclick="nextOpt('step11')" class="search" value="上一步"/>
            <input id="nxt" type="button" onclick="nextOpt('step13')" class="search" value="下一步"/>
        </div>
    </c:if>
</form>

