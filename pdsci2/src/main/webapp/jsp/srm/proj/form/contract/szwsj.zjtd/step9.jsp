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

<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" enctype="multipart/form-data" style="position: relative;">
    <input type="hidden" id="pageName" name="pageName" value="step9" />
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>
    <font style="font-size: 14px; font-weight:bold;color: #333;">九、苏州市引进临床医学专家团队项目合同审核表</font>
    <table width="100%" cellspacing="0" cellpadding="0" class="bs_tb">
        <tr>
            <td colspan="2" style="text-align: center">团队基本情况</td>
        </tr>
        <tr>
            <td style="text-align: center;width: 20%" >引进单位</td>
            <td style="text-align: left;width: 80%">
                <input type="text" class="inputText"style="width: 90%"
                                                           name="yjdyOrg" value="${resultMap.yjdyOrg}"/></td>
        </tr>
        <tr>
            <td>团队名称及类别</td>
            <td><textarea name="teamNameAndType" class="xltxtarea" placeholder="（名称须与合同书正文一致，类别须与经主管部门及“科教强卫工程”领导小组审核后反馈的一致）" style="height: 100px">${resultMap.teamNameAndType}</textarea></td>
        </tr>
    </table>
</form>

<c:if test="${param.view!=GlobalConstant.FLAG_Y}">
    <div align="center" style="margin-top: 10px; width: 100%">
        <input id="prev" type="button" onclick="nextOpt('step8')" class="search" value="上一步"/>
        <input id="nxt" type="button" onclick="nextOpt('finish')" class="search" value="完&#12288;成"/>
    </div>
</c:if>
