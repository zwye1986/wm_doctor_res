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
    <input type="hidden" id="pageName" name="pageName" value="step11"/>
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>

    <table class="basic" style="width: 100%; margin-top: 10px;">
        <tr>
            <th style="text-align: left;padding-left: 20px;">十、审核意见</th>
        </tr>
        <tr>
            <td style="text-align: left;">&#12288;项目单位意见</td>
        </tr>
        <tr>
            <td>
                <textarea name="deptOpinion" style="width:98%;height: 150px;">${resultMap.deptOpinion}</textarea>
                <%--<div style="width: 100%;padding-left: 50%;">--%>
                <%--学术委员会主任（签名）--%>
                <%--&#12288;&#12288;&#12288;&#12288;--%>
                <%--年&#12288;&#12288;月&#12288;&#12288;日--%>
                <%--</div>--%>
            </td>
        </tr>
    </table>
    <c:if test="${param.view != GlobalConstant.FLAG_Y}">
        <div class="button" style="width: 100%;">
            <input id="prev" type="button" onclick="nextOpt('step10')" class="search" value="上一步"/>
            <input id="nxt" type="button" onclick="nextOpt('finish')" class="search" value="完&#12288;成"/>
        </div>
    </c:if>
</form>

