<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <title>${sysCfgMap['sys_title_name']}</title>
    <jsp:include page="/jsp/jszy/htmlhead-jszy.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_cxselect" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#graduationYear').datepicker({
                startDate: "${recruit.graduationYear}",
                startView: 2,
                maxViewMode: 2,
                minViewMode: 2,
                format: 'yyyy'
            });
        });
        function checkFile(file) {
            var filePath = file.value;
            var types = "${sysCfgMap['res_file_support_suffix']}".split(",");
            var regStr = "/";
            for (var i = 0; i < types.length; i++) {
                if (types[i]) {
                    if (i == (types.length - 1)) {
                        regStr = regStr + "\\" + types[i] + '$';
                    } else {
                        regStr = regStr + "\\" + types[i] + '$|';
                    }
                }
            }
            regStr = regStr + '/i';
            regStr = eval(regStr);
            if ($.trim(filePath) != "" && !regStr.test(filePath)) {
                file.value = "";
                jboxTip("请上传&nbsp;${sysCfgMap['res_file_support_suffix']}格式的图片");
            }
        }
        function submit() {
            if ($("#editForm").validationEngine("validate")) {
                jboxStartLoading();
                var options = {
                    url: "<s:url value='/jszy/delayReturn/checkBackTrain'/>",
                    type: "post",
                    cache: false,
                    beforeSend: function () {
                    },
                    success: function (resp) {
                        jboxTip(resp);
                        setTimeout(function () {
                            top.searchBackInfo();
                            jboxClose();
                        }, 500);
                    },
                    error: function () {
                        jboxTip("操作失败,请刷新页面后重试");
                    },
                    complete: function () {
                    },
                    iframe: true
                };
                $("#editForm").ajaxSubmit(options);
            }

        }
        function editOpinon(vari) {
            if (vari == 'N') {
                $("#auditOpinionTr").show();
            } else {
                $("#auditOpinionTr").hide();
            }
        }
    </script>
</head>
<body>
<div class="div_search">
    <input type="hidden" id="upFileId"/>
    <form id="editForm" style="position: relative;" method="post"
          action="<s:url value='/jszy/delayReturn/checkDelayInfo'/>"
          enctype="multipart/form-data">
        <input type="hidden" name="recordFlow" value="${backTrainInfo.recordFlow}"/>
        <table border="0" cellpadding="0" cellspacing="0" class="base_info">
            <colgroup>
                <col width="25%"/>
                <col width="75%"/>
            </colgroup>
            <tbody>
            <tr>
                <th><b class="red">*</b>&#12288;审核：</th>
                <td>
                    <input class="validate[required]" type="radio" id="agreeChangeStatusId" name="backTrainStatusId"
                           value="${agree}" onchange="editOpinon('Y');"/>&nbsp;同意&#12288;
                    <input class="validate[required]" type="radio" id="disAgreeChangeStatusId" name="backTrainStatusId"
                           value="${disAgree}"
                           onchange="editOpinon('N');"/>&nbsp;不同意
                </td>
            </tr>
            <tr id="auditOpinionTr" style="display: none;">
                <th><b class="red">*</b>&#12288;审核意见：</th>
                <td>
                    <textarea class="xltxtarea validate[required]"
                              style=" margin: 0px; padding: 0px;border: 0px;height: 100px;"
                              id="auditOpinion" name="auditOpinion" placeholder="请输入审核意见"></textarea>
                </td>
            </tr>
            <tr>
                <th>附件：</th>
                <td>
                    <input type="file" name="${fileName}" style="border: none;"
                           onchange="checkFile(this);" accept="${sysCfgMap['res_file_support_suffix']}"/>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
    <div align="center" style="margin-top: 20px; margin-bottom:20px;">
        <input type="button" style="width:100px;" class="btn_brown" onclick="javascript:submit();" id="submitBtn"
               value="提交"/>
        <input type="button" style="width:100px;" class="btn_brown" onclick="javascript:jboxClose();" value="关闭"/>
    </div>
</div>
</body>
</html>