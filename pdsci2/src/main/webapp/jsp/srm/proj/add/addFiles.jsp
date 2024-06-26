<jsp:include page="/jsp/common/htmlhead.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jbox" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_fixedtableheader" value="true"/>
</jsp:include>
<script type="text/javascript">
    $(document).ready(function () {
        if ($("#projFileTb tr").length <= 0) {
            addFile('projFileTb');
        }
    });

    function addFile(tb) {
        var myDate = new Date();
        var createTime = myDate.getTime();
        var html = '<tr>' +
                '<td style="text-align: center;"><input type="checkbox"/></td>' +
                '<td style="text-align: left;"><input name="fileAddTime" type="hidden" value="' + createTime + '"/>&#12288;<input type="file" name="file" class="validate[required]"/></td>' +
                '</tr>';
        $('#' + tb).append(html);
    }

    function delTr(tb) {
        var trs = $('#' + tb).find(':checkbox:checked');
        jboxConfirm("确认删除？", function () {
            $.each(trs, function (i, n) {
                $(n).parent('td').parent("tr").remove();
            });
        });
    }

    function saveFiles(step){
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
<div style="width: 100%;height: 250px;overflow: auto">
    <form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" enctype="multipart/form-data"
          style="position: relative;">
        <input type="hidden" id="pageName" name="pageName" value="step1"/>
        <input type="hidden" id="recFlow" name="recFlow" value="${recFlow}"/>
        <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
        <input type="hidden" id="recTypeId" name="recTypeId" value="${pubProjRec.recTypeId}"/>
        <table width="95%" cellspacing="0" cellpadding="0" class="bs_tb">
            <tr>
                <th colspan="2" class="theader">附件信息
                    <c:if test="${param.view!=GlobalConstant.FLAG_Y}"><span style="float: right;padding-right: 10px"><a
                            href="javascript:void(0)"><img title="新增"
                                                           src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                                                           style="cursor: pointer;"
                                                           onclick="addFile('projFileTb')"/></a>&#12288;<a
                            href="javascript:void(0)"><img title="删除" style="cursor: pointer;"
                                                           src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
                                                           onclick="delTr('projFileTb')"/></a></span></c:if>
                </th>
            </tr>
            <tr>
                <td width="10%" style="font-weight:bold;"></td>
                <td width="90%" style="font-weight:bold;">附件名称</td>
            </tr>
            <tbody id="projFileTb">

            </tbody>
        </table>
    </form>
</div>
<div align="center" style="margin-top: 10px; width: 100%">
    <input id="prev" type="button" onclick="saveFiles('addFiles')" class="search" value="保&#12288;存"/>&#12288;
    <input id="nxt" type="button" onclick="jboxCloseMessager()" class="search" value="关&#12288;闭"/>
</div>
