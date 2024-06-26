<%@include file="/jsp/common/doctype.jsp" %>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jbox" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
</jsp:include>
<script type="text/javascript" src="<s:url value='/js/itemSelect/itemSelect2.js'/>"></script>
<style type="text/css">
    .boxHome .item:HOVER {
        background-color: #eee;
    }

    .cur {
        color: red;
    }
</style>
<script type="text/javascript">
    function doclose() {
        top.jboxClose();
    }

    function applyAudit() {
        var reason = "";
        var reason2 = "";
        var id = $("input[name='auditStatusId']:checked").val();
        if (id == "" || id == undefined) {
            jboxTip("请选择审核结果！！");
            return false;
        }
        if (id == "${jsResAuditStatusEnumPassed.id}") {
            reason = $("#passReason1").val();
            reason2 = $("#passReason2").val();
        }
        if (id == "${jsResAuditStatusEnumNotPassed.id}") {
            reason = $("#unpassReason1").val();
            reason2 = $("#unpassReason2").val();
        }
        if (!reason && id == "${jsResAuditStatusEnumNotPassed.id}") {
            jboxTip("请选择原因！！");
            return false;
        }
        if (reason == "不通过，无材料或缺某一个材料" && !reason2) {
            jboxTip("请输入具体原因！！");
            return false;
        }
        $("#auditReason").val(reason + " " + reason2);
        var signupFlows = "";
        <c:forEach items="${flows}" var="flow" varStatus="f">
        <c:if test="${f.first}">
        signupFlows += "signupFlows=${flow}";
        </c:if>
        <c:if test="${!f.first}">
        signupFlows += "&signupFlows=${flow}";
        </c:if>
        </c:forEach>
        if (signupFlows == "") {
            jboxTip("审核人次为0，无法审核！");
            return false;
        }
        jboxConfirm("确认审核？", function () {
            jboxStartLoading();
            jboxPost("<s:url value='/jsres/examSignUp/batchSaveAudit'/>?" + signupFlows,
                $("#myform").serialize(),
                function (resp) {
                    top.jboxEndLoading();
                    endloadIng();
                    jboxTip(resp);
                    if (resp == "审核成功") {
                        var currentPage = window.parent.$("#currentPage").val();
                        if (!currentPage) {
                            currentPage = 1;
                        }
                        var length = (window.parent.$("#dataTable tbody").find("tr").length || 0) - signupFlows.split('&').length;
                        if (length <= 0 && currentPage > 1) {
                            currentPage = currentPage - 1;
                        }
                        window.parent.toPage(currentPage);
                        top.jboxClose();
                    }

                }, null, false);
        });
    }

    function endloadIng() {
        var openDialog = dialog.get('artLoading');
        if (openDialog != null && openDialog.open) {
            openDialog.close().remove();
        }
        openDialog = dialog.get('loadingDialog');
        if (openDialog != null && openDialog.open) {
            openDialog.close().remove();
        }
        var jboxMainIframeLoading = $("#jboxMainIframeLoading");
        if (jboxMainIframeLoading != null) {
            jboxMainIframeLoading.fadeOut(500, function () {
                $(jboxMainIframeLoading).remove();
            });
        }
    }

    function check() {
        var id = $("input[name='auditStatusId']:checked").val();
        if (id == "${jsResAuditStatusEnumPassed.id}") {
            $("#passReason1").removeAttr("disabled");
            $("#passReason2").removeAttr("disabled");
            $("#unpassReason1").attr("disabled", "disabled");
            $("#unpassReason1").removeClass("validate[required]");
            $("#unpassReason2").attr("disabled", "disabled");
            $("#unpassReason2").removeClass("validate[required]");
        }
        if (id == "${jsResAuditStatusEnumNotPassed.id}") {
            $("#unpassReason1").removeAttr("disabled");
            $("#unpassReason1").addClass("validate[required]");
            $("#unpassReason2").removeAttr("disabled");
            $("#passReason1").attr("disabled", "disabled");
            $("#passReason2").attr("disabled", "disabled");
        }
    }

    $(function () {
        var datas = [];//所有的分配的授课老师账号

        var arry = {"id": "无", "text": "无"};
        datas.push(arry);
        var arry = {"id": "可考试，公共课合格后发证", "text": "可考试，公共课合格后发证"};
        datas.push(arry);
        var arry = {"id": "可考试，提供医师执业证书后发证", "text": "可考试，提供医师执业证书后发证"};
        datas.push(arry);
        var arry = {"id": "可考试，提供医师执业证书，公共课合格后发证", "text": "可考试，提供医师执业证书，公共课合格后发证"};
        datas.push(arry);
        var arry = {"id": "可考试，补轮转后发证", "text": "可考试，补轮转后发证"};
        datas.push(arry);
        var arry = {"id": "可考试，变更执业范围后发证", "text": "可考试，变更执业范围后发证"};
        datas.push(arry);
        var arry = {"id": "可考试，出科表上传完整后发证", "text": "可考试，出科表上传完整后发证"};
        datas.push(arry);
        var arry = {"id": "可考试，出科表上传完整，提供医师执业证书后发证", "text": "可考试，出科表上传完整，提供医师执业证书后发证"};
        datas.push(arry);
        var arry = {"id": "可考试，出科表上传完整，公共课合格后发证", "text": "可考试，出科表上传完整，公共课合格后发证"};
        datas.push(arry);
        var arry = {"id": "可考试，出科表上传完整，提供医师执业证书,公共课合格后发证", "text": "可考试，出科表上传完整，提供医师执业证书,公共课合格后发证"};
        datas.push(arry);
//	$.itemSelect("passReason",datas,null,null,null);
        var datas = [];
        var arry = {"id": "不通过，APP填写不符合要求或出科表上传不符合要求", "text": "不通过，APP填写不符合要求或出科表上传不符合要求"};
        datas.push(arry);
        var arry = {"id": "不通过，超出执业范围", "text": "不通过，超出执业范围"};
        datas.push(arry);
        var arry = {"id": "不通过，培训时间不足", "text": "不通过，培训时间不足"};
        datas.push(arry);
        var arry = {"id": "不通过，无材料或缺某一个材料（填写时需明确哪个材料，故需要提供文本框输入）", "text": "不通过，无材料或缺某一个材料（填写时需明确哪个材料，故需要提供文本框输入）"};
        datas.push(arry);
        var arry = {
            "id": "不通过，非${pdfn:getCurrYear()-1}年国家执业医师考试成绩单/国家执业医师考试成绩不合格",
            "text": "不通过，非${pdfn:getCurrYear()-1}年国家执业医师考试成绩单/国家执业医师考试成绩不合格"
        };
        datas.push(arry);
        var arry = {"id": "不通过，培训手册填写不符合要求", "text": "不通过，培训手册填写不符合要求"};
        datas.push(arry);
//	$.itemSelect("unpassReason",datas,null,null,null);
    });
</script>
<div class="search_table" style="margin-top:20px;text-align: center;">
    <div class="main_bd" id="div_table_0">
        <form id="myform">
            <table border="0" cellpadding="0" cellspacing="0" style=" width:100%;text-align: center;" class="base_info">
                <colgroup>
                    <col width="25%"/>
                    <col width="25%"/>
                </colgroup>
                <tbody>
                <tr style=" width:100%;text-align: center;">
                    <th colspan="2" style="text-align: center;">您本次操作学员数量为：${size}人/次</th>
                </tr>
                <tr style=" width:100%;text-align: center;">
                    <td style="text-align: left;border-right:1px solid #e7e7eb;">&#12288;&#12288;<input type="radio"
                                                                                                        name="auditStatusId"
                                                                                                        value="${jsResAuditStatusEnumPassed.id}"
                                                                                                        onclick="check();">通过
                    </th>
                    <td style="text-align: left;">&#12288;&#12288;<input type="radio" name="auditStatusId"
                                                                         value="${jsResAuditStatusEnumNotPassed.id}"
                                                                         onclick="check();">退回
                    </td>
                </tr>
                <tr style=" width:100%;text-align: center;">
                    <td style="border: 0px solid;border-right:1px solid #e7e7eb;">
                        &#12288;&#12288;原因(选填):
                    </td>
                    <td style="border: 0px solid;">
                        &#12288;&#12288;原因(必填):
                    </td>
                </tr>
                <tr style=" width:100%;text-align: center;">
                    <td style="border: 0px solid;border-right:1px solid #e7e7eb;text-align: left;padding-left: 30px;">
                        <%--<div style="min-width: 250px;float: left;border:1px">--%>
                        <%--<input name="auditReason" id="passReason" type="text"class="input"  style="width: 100%;border: 1px solid #e7e7eb;margin-left: 0px;" readonly="readonly" placeholder="点击选择原因"/>--%>
                        <%--</div>--%>
                        <select id="passReason1" class="select" style="width: 250px;">
                            <option value=""></option>
                            <option value="无">无</option>
                            <option value="可考试，公共课合格后发证">可考试，公共课合格后发证</option>
                            <option value="可考试，提供医师执业证书后发证">可考试，提供医师执业证书后发证</option>
                            <option value="可考试，提供医师执业证书，公共课合格后发证">可考试，提供医师执业证书，公共课合格后发证</option>
                            <option value="可考试，补轮转后发证">可考试，补轮转后发证</option>
                            <option value="可考试，变更执业范围后发证">可考试，变更执业范围后发证</option>
                            <option value="可考试，出科表上传完整后发证">可考试，出科表上传完整后发证</option>
                            <option value="可考试，出科表上传完整，提供医师执业证书后发证">可考试，出科表上传完整，提供医师执业证书后发证</option>
                            <option value="可考试，出科表上传完整，公共课合格后发证">可考试，出科表上传完整，公共课合格后发证</option>
                            <option value="可考试，出科表上传完整，提供医师执业证书,公共课合格后发证">可考试，出科表上传完整，提供医师执业证书,公共课合格后发证</option>
                        </select>
                        <textarea id="passReason2" style="width: 248px;height: 100px;"></textarea>
                    </td>
                    <td style="border: 0px solid;border-right:1px solid #e7e7eb;text-align: left;padding-left: 30px;">
                        <%--<div style="min-width: 250px;float: left;border:1px">--%>
                        <%--<input name="auditReason" id="unpassReason" type="text"class="input"  style="width: 100%;border: 1px solid #e7e7eb;margin-left: 0px;" readonly="readonly" placeholder="点击选择原因"/>--%>
                        <%--</div>--%>
                        <select id="unpassReason1" class="select" style="width: 250px;">
                            <option value=""></option>
                            <option value="不通过，APP填写不符合要求或出科表上传不符合要求">不通过，APP填写不符合要求或出科表上传不符合要求</option>
                            <option value="不通过，超出执业范围">不通过，超出执业范围</option>
                            <option value="不通过，培训时间不足">不通过，培训时间不足</option>
                            <option value="不通过，无材料或缺某一个材料">不通过，无材料或缺某一个材料</option>
                            <option value="不通过，非${pdfn:getCurrYear()-1}年国家执业医师考试成绩单/国家执业医师考试成绩不合格">
                                不通过，非${pdfn:getCurrYear()-1}年国家执业医师考试成绩单/国家执业医师考试成绩不合格
                            </option>
                            <option value="不通过，培训手册填写不符合要求">不通过，培训手册填写不符合要求</option>
                        </select>
                        <textarea id="unpassReason2" style="width: 248px;height: 100px;"></textarea>
                    </td>
                </tr>
                <input name="auditReason" id="auditReason" value="" hidden>
                </tbody>
            </table>
        </form>
        <div class="main_bd" id="div_table_1">
            <div align="center" style="margin-top: 20px; margin-bottom:20px;">
                <input type="button" id="" class="btn_green" onclick="applyAudit();" value="确定"/>&nbsp;
                <input type="button" id="submitBtn" class="btn_green" onclick="doclose();" value="关闭"/>&nbsp;
            </div>
        </div>
    </div>
</div>
