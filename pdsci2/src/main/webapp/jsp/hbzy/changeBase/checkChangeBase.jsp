<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <title>${sysCfgMap['sys_title_name']}</title>
    <jsp:include page="/jsp/hbzy/htmlhead-jszy.jsp">
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
            $('#changeInGraduationYear').datepicker({
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
                    url: "<s:url value='/hbzy/manage/checkChangeOrg'/>",
                    type: "post",
                    cache: false,
                    beforeSend: function () {
                    },
                    success: function (resp) {
                        jboxTip(resp);
                        setTimeout(function () {
                            top.searchIn();
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
                if ("${checkType}" == 'inCheck') {
                    $("#isAgreeOldTrainTr").hide();
                    $("#changeInGraduationYearTr").hide();
                    $("#auditOpinionTr").show();
                    $("#isAgreeOldTrainY").click();
                } else {
                    $("#auditOpinionTr").show();
                }
            } else {
                if ("${checkType}" == 'inCheck') {
                    $("#isAgreeOldTrainTr").show();
                    $("#auditOpinionTr").hide();
                } else {
                    $("#auditOpinionTr").hide();
                }
            }
        }
        function editGraduation(vari) {
            if (vari == 'N') {
                $("#changeInGraduationYearTr").show();

            } else {
                $("#changeInGraduationYearTr").hide();
            }
        }
    </script>
</head>
<body>
<div class="div_search">
    <input type="hidden" id="upFileId"/>
    <form id="editForm" style="position: relative;" method="post" action="<s:url value='/hbzy/manage/checkChangeOrg'/>"
          enctype="multipart/form-data">
        <input type="hidden" name="recordFlow" value="${history.recordFlow}"/>
        <input type="hidden" name="checkType" value="${checkType}"/>
        <table border="0" cellpadding="0" cellspacing="0" class="base_info">
            <colgroup>
                <col width="25%"/>
                <col width="75%"/>
            </colgroup>
            <tbody>
            <tr>
                <th><b class="red">*</b>&#12288;审核：</th>
                <td>
                    <input class="validate[required]" type="radio" id="agreeChangeStatusId" name="changeStatusId"
                           value="${agree}" onchange="editOpinon('Y');"/>&nbsp;同意&#12288;
                    <input class="validate[required]" type="radio" id="disAgreeChangeStatusId" name="changeStatusId"
                           value="${disAgree}"
                           onchange="editOpinon('N');"/>&nbsp;不同意
                </td>
            </tr>
            <c:if test="${checkType eq 'inCheck'}">
                <tr id="isAgreeOldTrainTr" style="display: none;">
                    <th><b class="red">*</b>&#12288;是否认可原&#12288;<br/>有培训记录：</th>
                    <td>
                        <input class="validate[required]" type="radio" name="isAgreeOldTrain" id="isAgreeOldTrainY"
                               value="${GlobalConstant.FLAG_Y}" onchange="editGraduation('Y');"/>&nbsp;是&#12288;&#12288;
                        <input class="validate[required]" type="radio" name="isAgreeOldTrain" id="isAgreeOldTrainN"
                               value="${GlobalConstant.FLAG_N}" onchange="editGraduation('N');"/>&nbsp;否
                    </td>
                </tr>
                <tr id="changeInGraduationYearTr" style="display: none;">
                    <th><b class="red">*</b>&#12288;结业考核年份：</th>
                    <td>
                        <input type="text" class="input validate[required]" id="changeInGraduationYear"
                               name="changeInGraduationYear" readonly="readonly" style="width: 100px;height: 20px"/>
                    </td>
                </tr>
            </c:if>
            <tr id="auditOpinionTr" style="display: none;">
                <th><b class="red">*</b>&#12288;审核意见：</th>
                <td>
                    <textarea class="xltxtarea validate[required]"
                              style=" margin: 0px; padding: 0px;border: 0px;height: 100px;"
                              id="auditOpinion" name="auditOpinion" placeholder="请输入审核意见"></textarea>
                </td>
            </tr>
            <tr>
                <th>上传附件：</th>
                <td>
                    <input type="file" name="${fileName}" style="border: none;"
                           onchange="checkFile(this);" accept="${sysCfgMap['res_file_support_suffix']}"/>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
    <c:if test="${roleId eq GlobalConstant.USER_LIST_GLOBAL}">
        <div align="left" style="margin-top: 20px; margin-bottom:20px;">
            <p>
                <c:if test="${history.isAgreeOldTrain eq GlobalConstant.FLAG_Y}">
                    注：${history.orgName}同意转入，并且认可原培训基地的培训记录！
                </c:if>
                <c:if test="${history.isAgreeOldTrain eq GlobalConstant.FLAG_N}">
                    注：${history.orgName}同意转入，但不认可原培训基地的培训记录！将学员结业考核年份延期至${history.changeInGraduationYear}年！
                </c:if>
            </p>
        </div>
    </c:if>
    <div align="center" style="margin-top: 20px; margin-bottom:20px;">
        <input type="button" style="width:100px;" class="btn_brown" onclick="javascript:submit();" id="submitBtn"
               value="提交"/>
        <input type="button" style="width:100px;" class="btn_brown" onclick="javascript:jboxClose();" value="关闭"/>
    </div>
</div>
</body>
</html>