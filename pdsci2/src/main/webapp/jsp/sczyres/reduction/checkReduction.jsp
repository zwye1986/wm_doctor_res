<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <title>${sysCfgMap['sys_title_name']}</title>
    <jsp:include page="/jsp/sczyres/htmlhead-sczyres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_cxselect" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function submit() {
            if (false == $("#editForm").validationEngine("validate")) {
                return;
            }
            var url = "<s:url value='/sczy/reduction/checkReductionInfo'/>";
            var data = $('#editForm').serialize();
            jboxPost(url, data, function (resp) {
                if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                    jboxTip(resp);
                    setTimeout(function () {
                        top.searchIn();
                        jboxClose();
                    }, 1000);
                } else {
                    top.jboxTip(resp);
                }
            }, null, false);
        }
        function editOpinon(vari) {
            if (vari == 'N') {
                $("#auditOpinion").addClass("validate[required]");
                $("#redOpinion").show();
            } else {
                $("#auditOpinion").removeClass("validate[required]");
                $("#redOpinion").hide();
            }
        }
    </script>
</head>
<body>
<div class="div_search">
    <input type="hidden" id="upFileId"/>
    <form id="editForm" style="position: relative;" method="post"
          action="<s:url value='/sczy/reduction/checkReductionInfo'/>"
          enctype="multipart/form-data">
        <input type="hidden" name="recordFlow" value="${reduction.recordFlow}"/>
        <input type="hidden" name="roleId" value="${roleId}"/>
        <table border="0" cellpadding="0" cellspacing="0" class="base_info">
            <colgroup>
                <col width="25%"/>
                <col width="75%"/>
            </colgroup>
            <tbody>
            <tr>
                <th><b class="red">*</b>&#12288;审核：</th>
                <td>
                    <label><input class="validate[required]" type="radio" name="reductionAuditStatusId"
                           value="${agree}" onchange="editOpinon('Y');"
                                  <%--<c:if test="${roleId eq GlobalConstant.USER_LIST_GLOBAL}">onchange="editOpinon('Y');"</c:if>--%>
                    />
                        &nbsp;同意</label>&#12288;
                    <label><input class="validate[required]" type="radio" name="reductionAuditStatusId"
                           value="${disAgree}" onchange="editOpinon('N');"
                                  <%--<c:if test="${roleId eq GlobalConstant.USER_LIST_GLOBAL}">onchange="editOpinon('N');"</c:if>--%>
                    />
                        &nbsp;不同意</label>
                </td>
            </tr>
            <tr>
                <th><b id="redOpinion" class="red">*</b>&#12288;审核意见：</th>
                <td>
                    <textarea class="xltxtarea validate[required]"
                              style=" margin: 0px; padding: 0px;border: 0px;height: 100px;"
                              id="auditOpinion" name="auditOpinion" placeholder="请输入审核意见"></textarea>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
    <div align="center" style="margin-top: 20px; margin-bottom:20px;">
        <input type="button" class="btn_blue" onclick="javascript:submit();" id="submitBtn"
               value="提&#12288;交"/>&#12288;
        <input type="button" class="btn_blue" onclick="javascript:jboxClose();" value="关&#12288;闭"/>
    </div>
</div>
</body>
</html>