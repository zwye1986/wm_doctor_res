<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_ui_combobox" value="false"/>
        <jsp:param name="jquery_ui_sortable" value="false"/>
        <jsp:param name="jquery_cxselect" value="false"/>
        <jsp:param name="jquery_scrollTo" value="false"/>
        <jsp:param name="jquery_jcallout" value="false"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fullcalendar" value="false"/>
        <jsp:param name="jquery_fngantt" value="false"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
    </jsp:include>
    <script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <style type="text/css">
        #boxHome .item:HOVER{background-color: #eee;}
    </style>
    <script type="text/javascript">
        //--------
        function uploadFile(id,invitationType,aobj) {
            $.ajaxFileUpload({
                url: "<s:url value='/gyxjgl/abroadApply/uploadInvitationFile'/>?recordFlow=${recordFlow}&invitationType="+invitationType,
                secureuri: false,
                fileElementId: id,
                dataType: 'json',
                success: function (data, status) {
                    if (data.indexOf("success") == -1) {
                        jboxTip(data);
                    } else {
                        jboxTip('${GlobalConstant.UPLOAD_SUCCESSED}');
                        var arr = [];
                        arr = data.split(":");
                        var url = "${sysCfgMap['upload_base_url']}/" + arr[1];
                        $("#"+aobj).attr("href", url);
                        $("#"+aobj).removeAttr("hidden");
                    }
                },
                error: function (data, status, e) {
                    jboxTip('${GlobalConstant.UPLOAD_FAIL}');
                },
                complete: function () {
                    $("#"+id).val("");
                }
            });
        }
    </script>
</head>
<body>
<input type="hidden" id="checkFileFlag" name="checkFileFlag"/>
<form id="pdfForm" method="post" enctype="multipart/form-data">
    <table class="basic" style="width: 100%;">
        <tr>
            <c:if test="${param.type eq 'proj'}">
                <%--项目相关附件上传--%>
                <th style="width: 50%;text-align: center;">项目合同书</th>
                <td>
                    <input type="file" id="pdfFile" name="file" style="display: none" onchange="uploadFile('pdfFile','Contract','fileA');"/>
                    <c:if test="${roleFlag eq 'student'}">
                        <a onclick="$('#pdfFile').click();" style="cursor: pointer;color:blue;">[上传]</a>
                    </c:if>
                    &emsp;<a id="fileA" style="cursor:pointer;color:blue;" href="${sysCfgMap['upload_base_url']}/${abroadApply.projContractUrl}" <c:if test="${empty abroadApply.projContractUrl}">hidden="hidden"</c:if> target="_blank">[导出]</a>
                </td>
            </c:if>
            <c:if test="${param.type ne 'proj'}">
                <th style="width: 50%;text-align: center;">出访目的地国家发来的《邀请信》</th>
                <td>
                    <input type="file" id="pdfFile" name="file" style="display: none" onchange="uploadFile('pdfFile','En','fileA');"/>
                    <c:if test="${roleFlag eq 'student'}">
                        <a onclick="$('#pdfFile').click();" style="cursor: pointer;color:blue;">[上传]</a>
                    </c:if>
                    &emsp;<a id="fileA" style="cursor:pointer;color:blue;" href="${sysCfgMap['upload_base_url']}/${abroadApply.invitationUrl}" <c:if test="${empty abroadApply.invitationUrl}">hidden="hidden"</c:if> target="_blank">[导出]</a>
                </td>
            </c:if>
        </tr>
    </table>
</form>
<form id="pdfForm1" method="post" enctype="multipart/form-data">
    <table class="basic" style="width: 100%;">
        <tr>
            <c:if test="${param.type eq 'proj'}">
                <%--项目相关附件上传--%>
                <th style="width: 50%;text-align: center;">项目金额附件</th>
                <td>
                    <input type="file" id="pdfFile1" name="file" style="display: none" onchange="uploadFile('pdfFile1','Money','fileA1');"/>
                    <c:if test="${roleFlag eq 'student'}">
                        <a onclick="$('#pdfFile1').click();" style="cursor: pointer;color:blue;">[上传]</a>
                    </c:if>
                    &emsp;<a id="fileA1" style="cursor:pointer;color:blue;" href="${sysCfgMap['upload_base_url']}/${abroadApply.projMoneyUrl}" <c:if test="${empty abroadApply.projMoneyUrl}">hidden="hidden"</c:if> target="_blank">[导出]</a>
                </td>
            </c:if>
            <c:if test="${param.type ne 'proj'}">
                <th style="width: 50%;text-align: center;">《邀请信》中文译文</th>
                <td>
                    <input type="file" id="pdfFile1" name="file" style="display: none" onchange="uploadFile('pdfFile1','Ch','fileA1');"/>
                    <c:if test="${roleFlag eq 'student'}">
                        <a onclick="$('#pdfFile1').click();" style="cursor: pointer;color:blue;">[上传]</a>
                    </c:if>
                    &emsp;<a id="fileA1" style="cursor:pointer;color:blue;" href="${sysCfgMap['upload_base_url']}/${abroadApply.chiInvitationUrl}" <c:if test="${empty abroadApply.chiInvitationUrl}">hidden="hidden"</c:if> target="_blank">[导出]</a>
                </td>
            </c:if>
        </tr>
    </table>
</form>
<div style="text-align: center; margin-top: 10px;">
    <input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();"/>
</div>
</body>
</html>