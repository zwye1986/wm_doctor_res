<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
    </jsp:include>
</head>
<script type="text/javascript">
    /*$(document).ready(function () {
        if ($("#projFileTb tr").length <= 0) {
            addFile('projFileTb');
        }
    });*/

    function addFile(recFlow) {
        var url = "<s:url value='/srm/proj/add/addFiles'/>?recFlow="+recFlow;
        var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='400px' height='300px' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
        jboxMessager(iframe,'附件上传',400,300,null,true);
        /*var trs = $("#" + tb).find("tr");
        var num = trs.length + 1;
        var myDate = new Date();
        var createTime = myDate.getTime();
        var html = '<tr>' +
                '<td class="seq" style="text-align: center;">' + num + '</td>' +
                '<td style="text-align: left;"><input name="fileAddTime" type="hidden" value="' + createTime + '"/>&#12288;<input type="file" name="file" class="validate[required]"/></td>' +
                '<td><a style="cursor: pointer;" onclick="delTr(this)">[删除]</a></td>' +
                '</tr>';
        $('#' + tb).append(html);
        reSeq(tb);*/
    }

    function delFile(obj) {
        var templateId = $(obj).parent('td').parent("tr").parent("tbody").attr("id");

        reSeq(templateId);
    }

    function delTr(obj) {
        var templateId = $(obj).parent('td').parent("tr").parent("tbody").attr("id");
        alert(templateId);
        jboxConfirm("确认删除？", function () {
            $(obj).parent('td').parent("tr").remove();
            reSeq(templateId);
        });

    }
    function reSeq(templateId) {
        $('#' + templateId + ' .seq').each(function (i, n) {
            $(n).text(i + 1);
        });
    }
</script>
<style>
    table {
        font-size: 14px;
    }
</style>
<body>
<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" enctype="multipart/form-data"
      style="position: relative;">
    <div style="height: 430px;overflow: auto">
        <p style="font-size: medium;font-weight: bold;text-align: center;padding: 5px">
            项目信息
        </p>
        <table width="98%" cellspacing="0" cellpadding="0" class="bs_tb">
            <colgroup>
                <col width="17%"/>
                <col width="33%"/>
                <col width="17%"/>
                <col width="33%"/>
            </colgroup>
            <tr>
                <th style="text-align: right">项目名称：</th>
                <td style="text-align: left;padding-left: 5px">${proj.projName}
                    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
                </td>
                <th style="text-align: right">项目负责人：</th>
                <td style="text-align: left;padding-left: 5px">${proj.applyUserName}</td>
            </tr>
            <tr>
                <th style="text-align: right">当前阶段：</th>
                <td style="text-align: left;padding-left: 5px">${proj.projStageName }</td>
                <th style="text-align: right">当前状态：</th>
                <td style="text-align: left;padding-left: 5px">${proj.projStatusName }</td>
            </tr>
        </table>
        <p style="font-size: medium;font-weight: bold;text-align: center;padding: 5px;margin-top: 15px">
            表单附件信息
        </p>
        <c:if test="${empty applyRec and empty contractRec and empty scheduleRecList and empty changeRecList and empty completeRec and empty terminateRec}">
            <table width="98%" cellspacing="0" cellpadding="0" class="bs_tb">
                <tr>
                    <td style="font-size: medium;">该项目还没有填写任何表单！！</td>
                </tr>

            </table>
        </c:if>
        <c:if test="${not empty applyRec}">
            <table width="98%" cellspacing="0" cellpadding="0" class="bs_tb" style="margin-top: 10px">
                <tr>
                    <th colspan="3" class="theader">申报书信息
                        <c:if test="${param.view!=GlobalConstant.FLAG_Y}"><span
                                style="float: right;padding-right: 10px"><a
                                style="cursor: pointer;" onclick="addFile('${applyRec.recFlow}')">[添加附件]</a></span></c:if>
                        <input type="hidden" id="pageName" name="pageName" value="step1"/>
                        <input type="hidden" id="recFlow" name="recFlow" value="${applyRec.recFlow}"/>
                    </th>
                </tr>
                <tr>
                    <td width="10%">序号</td>
                    <td width="70%">附件名称</td>
                    <td width="20%">操作</td>
                </tr>
                <tbody id="applyTb">
                <c:forEach var="file" items="${recFileMap[applyRec.recFlow]}" varStatus="status">

                    <tr>
                        <td class="seq" style="text-align: center;">${status.count}</td>
                        <td style="text-align: left;">&#12288;<input name="file" value="${file.key}"
                                                                     type="hidden"/>
                            <input type="hidden" name="file_flow" value="${file.key}"/><a
                                    href='<s:url value="/pub/file/down?fileFlow=${file.key}"/>'>${file.value.fileName}</a>
                        </td>
                        <td><a style="cursor: pointer;" onclick="delFile(this)">[删除]</a></td>
                    </tr>

                </c:forEach>
                <c:if test="${empty recFileMap[applyRec.recFlow]}">
                    <tr id="applyTb_none">
                        <td colspan="3">无记录！</td>
                    </tr>
                </c:if>
                </tbody>

            </table>
        </c:if>

        <c:if test="${not empty contractRec}">
            <table width="98%" cellspacing="0" cellpadding="0" class="bs_tb" style="margin-top: 10px">
                <tr>
                    <th colspan="3" class="theader">合同书信息
                        <c:if test="${param.view!=GlobalConstant.FLAG_Y}"><span
                                style="float: right;padding-right: 10px"><a
                                style="cursor: pointer;" onclick="addFile('${contractRec.recFlow}')">[添加附件]</a></span></c:if>
                    </th>
                </tr>
                <tr>
                    <td width="10%">序号</td>
                    <td width="70%">附件名称</td>
                    <td width="20%">操作</td>
                </tr>

                <tbody id="contractTb">
                <c:forEach var="file" items="${recFileMap[contractRec.recFlow]}" varStatus="status">
                    <tr>
                        <td class="seq" style="text-align: center;">${status.count}</td>
                        <td style="text-align: left;">&#12288;<input name="file" value="${file.key}"
                                                                     type="hidden"/>
                            <input type="hidden" name="file_flow" value="${file.key}"/><a
                                    href='<s:url value="/pub/file/down?fileFlow=${file.key}"/>'>${file.value.fileName}</a>
                        </td>
                        <td><a style="cursor: pointer;" onclick="delFile(this)">[删除]</a></td>
                    </tr>
                </c:forEach>
                <c:if test="${empty recFileMap[contractRec.recFlow]}">
                    <tr id="contractTb_none">
                        <td colspan="3">无记录！</td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </c:if>

        <c:forEach var="scheduleRec" items="${scheduleRecList}" varStatus="recStatus">
            <table width="98%" cellspacing="0" cellpadding="0" class="bs_tb" style="margin-top: 10px">
                <tr>
                    <th colspan="3" class="theader">进展报告信息
                        <c:if test="${param.view!=GlobalConstant.FLAG_Y}"><span
                                style="float: right;padding-right: 10px"><a
                                style="cursor: pointer;"
                                onclick="addFile('${scheduleRec.recFlow}')">[添加附件]</a></span></c:if>
                    </th>
                </tr>
                <tr>
                    <td width="10%">序号</td>
                    <td width="70%">附件名称</td>
                    <td width="20%">操作</td>
                </tr>
                <tbody id="scheduleReport_${recStatus.count}">
                <c:forEach var="file" items="${recFileMap[scheduleRec.recFlow]}" varStatus="status">
                    <tr>
                        <td class="seq" style="text-align: center;">${status.count}</td>
                        <td style="text-align: left;">&#12288;<input name="file" value="${file.key}"
                                                                     type="hidden"/>
                            <input type="hidden" name="file_flow" value="${file.key}"/>
                            <a
                                    href='<s:url value="/pub/file/down?fileFlow=${file.key}"/>'>${file.value.fileName}</a>
                        </td>
                        <td><a style="cursor: pointer;" onclick="delFile(this)">[删除]</a></td>
                    </tr>


                </c:forEach>
                <c:if test="${empty recFileMap[scheduleRec.recFlow]}">
                    <tr id="scheduleReport_${recStatus.count}_none">
                        <td colspan="3">无记录！</td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </c:forEach>

        <c:forEach var="changeRec" items="${changeRecList}" varStatus="recStatus">
            <table width="98%" cellspacing="0" cellpadding="0" class="bs_tb" style="margin-top: 10px">
                <tr>
                    <th colspan="3" class="theader">变更（延期）申请信息
                        <c:if test="${param.view!=GlobalConstant.FLAG_Y}"><span
                                style="float: right;padding-right: 10px"><a
                                style="cursor: pointer;" onclick="addFile('${changeRec.recFlow}')">[添加附件]</a></span></c:if>
                    </th>
                </tr>
                <tr>
                    <td width="10%">序号</td>
                    <td width="70%">附件名称</td>
                    <td width="20%">操作</td>
                </tr>
                <tbody id="changeReport_${recStatus.count}">
                <c:forEach var="file" items="${recFileMap[changeRec.recFlow]}" varStatus="status">

                    <tr>
                        <td class="seq" style="text-align: center;">${status.count}</td>
                        <td style="text-align: left;">&#12288;<input name="file" value="${file.key}"
                                                                     type="hidden"/>
                            <input type="hidden" name="file_flow" value="${file.key}"/><a
                                    href='<s:url value="/pub/file/down?fileFlow=${file.key}"/>'>${file.value.fileName}</a>
                        </td>
                        <td><a style="cursor: pointer;" onclick="delFile(this)">[删除]</a></td>
                    </tr>

                </c:forEach>
                <c:if test="${empty recFileMap[changeRec.recFlow]}">
                    <tr id="changeReport_${recStatus.count}_none">
                        <td colspan="3">无记录！</td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </c:forEach>

        <c:if test="${not empty terminateRec}">
            <table width="98%" cellspacing="0" cellpadding="0" class="bs_tb" style="margin-top: 10px">
                <tr>
                    <th colspan="3" class="theader">中止申请信息
                        <c:if test="${param.view!=GlobalConstant.FLAG_Y}"><span
                                style="float: right;padding-right: 10px"><a
                                style="cursor: pointer;" onclick="addFile('${terminateRec.recFlow}')">[添加附件]</a></span></c:if>
                    </th>
                </tr>
                <tr>
                    <td width="10%">序号</td>
                    <td width="70%">附件名称</td>
                    <td width="20%">操作</td>
                </tr>
                <tbody id="terminateReportTb">
                <c:forEach var="file" items="${recFileMap[terminateRec.recFlow]}" varStatus="status">
                    <tr>
                        <td class="seq" style="text-align: center;">${status.count}</td>
                        <td style="text-align: left;">&#12288;<input name="file" value="${file.key}"
                                                                     type="hidden"/>
                            <input type="hidden" name="file_flow" value="${file.key}"/>
                            <a href='<s:url value="/pub/file/down?fileFlow=${file.key}"/>'>${file.value.fileName}</a>
                        </td>
                        <td><a style="cursor: pointer;" onclick="delFile(this)">[删除]</a></td>
                    </tr>

                </c:forEach>
                <c:if test="${empty recFileMap[terminateRec.recFlow]}">
                    <tr id="terminateReportTb_none">
                        <td colspan="3">无记录！</td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </c:if>

        <c:if test="${not empty completeRec}">
            <table width="98%" cellspacing="0" cellpadding="0" class="bs_tb" style="margin-top: 10px">
                <tr>
                    <th colspan="3" class="theader">验收申请信息
                        <c:if test="${param.view!=GlobalConstant.FLAG_Y}"><span
                                style="float: right;padding-right: 10px"><a
                                style="cursor: pointer;" onclick="addFile('${completeRec.recFlow}')">[添加附件]</a>&#12288;<%--<a
                            href="javascript:void(0)"><img title="删除" style="cursor: pointer;"
                                                           src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
                                                           onclick="delTr('completeReportTb')"/></a>--%></span></c:if>
                    </th>
                </tr>
                <tr>
                    <td width="10%">序号</td>
                    <td width="70%">附件名称</td>
                    <td width="20%">操作</td>
                </tr>
                <tbody id="completeReportTb">
                <c:forEach var="file" items="${applyRec[completeRec.recFlow]}" varStatus="status">
                    <tr>
                        <td class="seq" style="text-align: center;">${status.count}</td>
                        <td style="text-align: left;">&#12288;<input name="file" value="${file.key}"
                                                                     type="hidden"/>
                            <input type="hidden" name="file_flow" value="${file.key}"/>
                            <a href='<s:url value="/pub/file/down?fileFlow=${file.key}"/>'>${file.value.fileName}</a>
                        </td>
                        <td><a style="cursor: pointer;" onclick="delFile(this)">[删除]</a></td>
                    </tr>

                </c:forEach>
                <c:if test="${empty applyRec[completeRec.recFlow]}">
                    <tr id="completeReportTb_none">
                        <td colspan="3">无记录！</td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </c:if>
        <div style="width: 100%;height: 20px"></div>
    </div>
</form>

<c:if test="${param.view!=GlobalConstant.FLAG_Y}">
    <div align="center" style="margin-top: 10px; width: 100%">
            <%--<input id="prev" type="button" onclick="nextOpt('step10')" class="search" value="保&#12288;存"/>&#12288;--%>
        <input id="nxt" type="button" onclick="jboxClose()" class="search" value="关&#12288;闭"/>
    </div>
</c:if>
</body>