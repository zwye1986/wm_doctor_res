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
                startDate: "${recruit.sessionNumber}",
                startView: 2,
                maxViewMode: 2,
                minViewMode: 2,
                format: 'yyyy'
            });
        });
        function editOpinon(vari) {
            if (vari == 'N') {
                $(".opinonTr").show();
                $(".graduationYearTr").hide();
            } else {
                $(".opinonTr").hide();
                $(".graduationYearTr").show();
            }
        }
        function auditChangeSpe() {
            if (false == $("#editForm").validationEngine("validate")) {
                return false;
            }
            jboxStartLoading();
            var options = {
                url: "<s:url value='/jszy/manage/checkChangeSpe'/>",
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
    </script>
</head>
<body>
<div class="infoAudit">
    <form id="editForm" style="position: relative;" method="post" action="<s:url value='/jszy/manage/checkChangeSpe'/>"
          enctype="multipart/form-data">
        <div class="div_table">
            <input type="hidden" name="recordFlow" value="${history.recordFlow}"/>
            <input type="hidden" name="doctorFlow" value="${history.doctorFlow}"/>
            <input type="hidden" name="roleId" value="${roleId}"/>
            <table border="0" cellpadding="0" cellspacing="0" class="base_info">
                <colgroup>
                    <col width="20%"/>
                    <col width="30%"/>
                    <col width="20%"/>
                    <col width="30%"/>
                </colgroup>
                <tr>
                    <th>姓&#12288;&#12288;名：</th>
                    <td>${history.doctorName}</td>
                    <th>变更申请表：</th>
                    <td>
                        <c:if test="${not empty history.speChangeApplyFile }">
                            [<a href="${sysCfgMap['upload_base_url']}/${history.speChangeApplyFile }" target="_blank">查看附件</a>]&nbsp;
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <th>当前培训专业：</th>
                    <td>${history.historyTrainingTypeName }</td>
                    <th>意向培训专业：</th>
                    <td>${history.trainingTypeName }</td>
                </tr>
                <tr>
                    <th>当前对应专业：</th>
                    <td>${history.historyTrainingSpeName }</td>
                    <th>意向对应专业：</th>
                    <td>${history.trainingSpeName }</td>
                </tr>
                <tr>
                    <th>当前二级专业：</th>
                    <td>${history.historySecondSpeName }</td>
                    <th>意向二级专业：</th>
                    <td>${history.secondSpeName }</td>
                </tr>
                <tr>
                    <th>年&#12288;&#12288;级：</th>
                    <td>${recruit.sessionNumber}</td>
                    <th>已培训时间：</th>
                    <td>
                        <c:forEach var="dict" items="${dictTypeEnumYetTrainYearList}">
                            <c:if test="${recruit.yetTrainYear eq dict.dictId}">
                                ${dict.dictName }
                            </c:if>
                        </c:forEach>
                    </td>
                </tr>
                <c:if test="${GlobalConstant.USER_LIST_GLOBAL eq roleId}">
                    <tr class="graduationYearTr">
                        <th><b class="red">*</b>&#12288;结业考核年份：</th>
                        <td colspan="3">
                            <input type="text" id=graduationYear name="graduationYear" value="${recruit.graduationYear}"
                                   class="validate[required] input" readonly="readonly"
                                   style="width: 100px;height: 25px;"/>
                        </td>
                    </tr>
                </c:if>
                <tr>
                    <th><b class="red">*</b>&#12288;审&#12288;&#12288;核：</th>
                    <td>
                        <input class="validate[required]" type="radio" id="agreeChangeStatusId" name="changeStatusId"
                               value="${agree}" onchange="editOpinon('Y');"/>&nbsp;同意&#12288;
                        <input class="validate[required]" type="radio" id="disAgreeChangeStatusId" name="changeStatusId"
                               value="${disAgree}"
                               onchange="editOpinon('N');"/>&nbsp;不同意
                    </td>
                    <th>上传附件：</th>
                    <td>
                        <input type="file" name="${fileName}"
                               style="border: none;width: 170px;"
                               onchange="checkFile(this);" accept="${sysCfgMap['res_file_support_suffix']}"/>
                    </td>
                </tr>
                <tr class="opinonTr" style="display: none;">
                    <th><b class="red">*</b>&#12288;审核意见：</th>
                    <td colspan="3">
                        <textarea class="xltxtarea validate[required]" style=" margin: 0px; padding: 0px;border: 0px"
                                  id="admitNotice"
                                  name="auditOpinion" placeholder="请输入审核意见"></textarea>
                    </td>
                </tr>
            </table>
        </div>
        <div class="btn_info audit">
            <div style="padding-bottom: 20px;">
                <label style="color: red;padding-left: 0px;">注：学员变更专业，将作为培训基地不良信息记录在案,全科、儿科、精神等紧缺专业变更为其他专业，请严控！</label>
            </div>
            <input type="button" style="width:100px;" class="btn_brown" onclick="javascript:auditChangeSpe();"
                   id="submitBtn" value="提交"/>
            <input type="button" style="width:100px;" class="btn_brown" onclick="javascript:jboxClose();" value="关闭"/>
        </div>
    </form>
</div>
</body>
</html>