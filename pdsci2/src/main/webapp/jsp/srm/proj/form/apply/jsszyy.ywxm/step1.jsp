<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:if test="${param.view != GlobalConstant.FLAG_Y}">
    <script type="text/javascript">
        function nextOpt(step) {
            if (false == $("#projForm").validationEngine("validate")) {
                return false;
            }
            var form = $('#projForm');
            form.append('<input type="hidden" name="nextPageName" value="'+step+'"/>');
            var action = form.attr('action');
            form.attr("action", action);
            form.submit();
        }
        function doBack() {
            <c:if test="${sessionScope.projListScope ne GlobalConstant.PROJ_STATUS_SCOPE_LOCAL}">
            window.location.href = "<s:url value='/srm/proj/mine/process?projFlow='/>" + $("#projFlow").val();
            </c:if>
            <c:if test="${sessionScope.projListScope eq GlobalConstant.PROJ_STATUS_SCOPE_LOCAL}">
            window.location.href = "<s:url value='/srm/proj/search/recList?projFlow='/>" + $("#projFlow").val();
            </c:if>
        }

        $(document).ready(function(){
            if($("#projFileTb tr").length<=0){
                addFile('projFileTb');
            }
        });

        function addFile(tb){
            var myDate = new Date();
            var createTime = myDate.getTime();
            var html = '<tr>'+
                    '<td style="text-align: center;"><input type="checkbox"/></td>'+
                    '<td style="text-align: left;"><input name="fileAddTime" type="hidden" value="'+createTime+'"/>&#12288;<input type="file" name="file" class="validate[required]"/></td>'+
                    '</tr>';
            $('#'+tb).append(html);
        }

        function delTr(tb){
            var trs = $('#'+tb).find(':checkbox:checked');
            jboxConfirm("确认删除？" , function(){
                $.each(trs , function(i , n){
                    $(n).parent('td').parent("tr").remove();
                });
            });
        }

    </script>
</c:if>
<style type="text/css">
    /*.basic tbody th {
        text-align: center;
    }*/
    .readonlycss {
        background-color: #EEEEEE;
    }
</style>
<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" enctype="multipart/form-data">
    <input type="hidden" id="pageName" name="pageName" value="step1"/>
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>

    <div class="content">
        <table width="100%" cellspacing="0" cellpadding="0" class="basic">
            <tr>
                <th colspan="4" style="text-align: left;padding-left: 15px">基本信息</th>
            </tr>
            <tbody>
            <tr>
                <th width="20%" style="text-align: right;">
                    年&#12288;&#12288;度：
                </th>
                <td width="30%">
                   ${proj.projYear}
                </td>
                <th width="20%" style="text-align: right;">
                    项目名称：
                </th>
                <td width="30%">
                    ${proj.projName}
                </td>
            </tr>
            <tr>
                <th style="text-align: right">承担科室：</th>
                <td>
                    ${proj.applyDeptName}
                <th style="text-align: right">项目负责人：</th>
                <td>
                     ${proj.applyUserName}
                </td>
            </tr>
            <tr>
                <th width="20%" style="text-align: right;">学科代码：</th>
                <td width="30%">
                   ${proj.subjName}
                </td>
                <th width="20%" style="text-align: right;">
                    联系方式：
                </th>
                <td width="30%">
                    ${proj.applyUserPhone}
                </td>
            </tr>
            <tr id="planTimeTr">
                <th width="20%" style="text-align: right;">计划开始时间：</th>
                <td width="30%">
                    ${proj.projStartTime}
                </td>
                <th width="20%" style="text-align: right;">计划结束时间：</th>
                <td width="30%">
                    ${proj.projEndTime}
                </td>
            </tr>
            <tr>
                <th style="text-align: right;">一级来源：</th>
                <td>
                    ${proj.projDeclarer}
                </td>
                <th style="text-align: right;">二级来源：</th>
                <td>
                    ${proj.projSecondSourceName}
                </td>
            </tr>
            <tr>
                <th style="text-align: right;">项目类别：</th>
                <td>
                    ${proj.projTypeName}
                </td>
                <th style="text-align: right;"><%--计划类别：--%></th>
                <td>
                    <%--${proj.planTypeName}--%>
                </td>
            </tr>
            </tbody>
        </table>
        <table width="100%" cellspacing="0" cellpadding="0" class="bs_tb">
            <tr>
                <th colspan="2" class="theader">附件信息
                    <c:if test="${param.view!=GlobalConstant.FLAG_Y}"><span style="float: right;padding-right: 10px"><a href="javascript:void(0)"><img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="addFile('projFileTb')"/></a>&#12288;<a href="javascript:void(0)"><img title="删除"  style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delTr('projFileTb')"/></a></span></c:if>
                </th>
            </tr>
            <tr>
                <td width="3%" style="font-weight:bold;"></td>
                <td width="97%" style="font-weight:bold;">附件名称</td>
            </tr>
            <tbody id="projFileTb">
            <c:forEach var="file" items="${pageFileMap}" varStatus="status">
                <c:choose>
                    <c:when test="${param.view==GlobalConstant.FLAG_Y}">
                        <tr>
                            <td style="text-align: center;">${status.count}</td>
                            <td ><input name="file" value="${file.key}" type="hidden"/><a href='<s:url value="/pub/file/down?fileFlow=${file.key}"/>'>${file.value.fileName}</a></td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td style="text-align: center;"><input type="checkbox"/></td>
                            <td style="text-align: left;">&#12288;<input name="file" value="${file.key}" type="hidden"/><a href='<s:url value="/pub/file/down?fileFlow=${file.key}"/>'>${file.value.fileName}</a></td>
                        </tr>
                        <tr style="display: none">
                            <td><input type="hidden" name="file_flow" value="${file.key}"/></td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            </tbody>
        </table>
    </div>
</form>

<c:if test="${param.view!=GlobalConstant.FLAG_Y}">
    <div align="center" style="margin-top: 10px">
        <input id="prev" type="button" onclick="doBack()" class="search" value="返&#12288;回"/>
        <c:if test="${sessionScope.projListScope ne GlobalConstant.PROJ_STATUS_SCOPE_LOCAL}">
            <input id="nxt" type="button" onclick="nextOpt('finish')" class="search" value="完&#12288;成"/>
        </c:if>
        <c:if test="${sessionScope.projListScope eq GlobalConstant.PROJ_STATUS_SCOPE_LOCAL}">
            <%--医院管理员编辑完成标识符更改--%>
            <input id="nxt" type="button" onclick="nextOpt('localFinish')" class="search" value="完&#12288;成"/>
        </c:if>
    </div>
</c:if>	