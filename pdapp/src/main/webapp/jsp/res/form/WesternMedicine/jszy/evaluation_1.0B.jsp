<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
        <jsp:param name="ueditor" value="true"/>
    </jsp:include>
    <style type="text/css">

        table.gridtable {
            font-family: verdana,arial,sans-serif;
            font-size:11px;
            color:#333333;
            border-width: 1px;
            border-color: #666666;
            border-collapse: collapse;
        }
        table.gridtable th {
            border-width: 1px;
            padding: 5px;
            border-style: solid;
            border-color: #666666;
            background-color: #dedede;
            text-align: right;
        }
        table.gridtable td {
            border-width: 1px;
            padding: 5px;
            border-style: solid;
            border-color: #666666;
            background-color: #ffffff;
            text-align: left;
        }
    </style>
    <script>
        $(function () {
            <c:if test="${param.roleFlag ne GlobalConstant.RES_ROLE_SCOPE_HEAD  and
            param.roleFlag ne GlobalConstant.RES_ROLE_SCOPE_TEACHER }">

            $(".ctrlInput").attr("readonly", true);
            $("[type='checkbox']").attr("disabled", true);
            $(".jin").hide();
            $('[name="date"]').removeAttr("onclick");
            $('[name="date"]').attr("readonly", true);
            </c:if>
            <c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER}">

            if ($(".theacher").val() != "" && $(".theacher").val() != null) {

                $(".ctrlInput").attr("readonly", true);
                $("[type='checkbox']").attr("disabled", true);
                $(".jin").hide();
                $('[name="date"]').removeAttr("onclick");
                $('[name="date"]').attr("readonly", true);
            }
            if ($(".theacher").val() == "" || $(".theacher").val() == null) {

                $('[name="date"]').removeAttr("onclick");
                $('[name="date"]').attr("readonly", true);
                $('[name="guideTeacher"]').attr("readonly", false);
                var TeacherName = $('[name="guideTeacher"]').val();
                if (TeacherName == "") {
                    TeacherName = '${sessionScope.currUser.userName}';
                    $('[name="guideTeacher"]').val(TeacherName);
                }
            }
            </c:if>

            <c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD}">

            $('.branchDirector').attr("readonly", false);
            var TeacherName = $('[name="branchDirector"]').val();
            if (TeacherName == "") {
                TeacherName = '${sessionScope.currUser.userName}';
                $('[name="branchDirector"]').val(TeacherName);
            }
            ;
            </c:if>
            hideInput();
        });
        function single(box) {
            var curr = box.checked;
            if (curr) {
                var name = box.name;

                $(":checkbox[name='" + name + "']").attr("checked", false);
            }
            box.checked = curr;
        }

        function save() {
            if ($("#editform").validationEngine("validate")) {
                jboxConfirm("确认提交？", function () {
                    saveForm();
                }, null);
            }


        }

        function saveForm() {
            var url = "<s:url value='/res/rec/saveRegistryFormNew'/>";
            jboxPost(url, $('#editform').serialize(), function (resp) {
                if ("${GlobalConstant.SAVE_SUCCESSED}" == resp) {
                    window.parent.document.mainIframe.location.reload();
                    back();
                }

            }, null, true);
        }
        function back() {
            if ("${param.openType}" == "open") {
                jboxClose();
            }
            if ("${param.openType}" == "messager") {
                top.jboxCloseMessager();
            } else {
                $("#detail").rightSlideClose();
            }
        }


        function hideInput() {
            $(":text[readonly='readonly'],textarea[readonly='readonly']").each(function () {
                var val = this.value;
                $(this).after('<label>' + val + '<input type="hidden" name="' + this.name + '" value="' + val + '"/></label>').remove();
            });
            $(":disabled").each(function () {
                var val = this.value;
                var $parent = $(this).closest("label");
                if (this.checked) {
                    $parent.after('<label>' + val + '<input type="hidden" name="' + this.name + '" value="' + val + '"/></label>');
                }
                $parent.remove();
            });
            $("[disabled='disabled']:selected").each(function () {
                var val = this.value;
                var $parent = $(this).closest("select");
                $parent.after('<label>' + val + '<input type="hidden" name="' + $parent.attr("name") + '" value="' + val + '"/></label>').remove();
            });
        }

        function jboxPrint(id) {
            jboxTip("正在准备打印…")
            setTimeout(function () {
                var newstr = $("#" + id).html();
                var oldstr = document.body.innerHTML;
                document.body.innerHTML = newstr;
                window.print();
                document.body.innerHTML = oldstr;
                jboxEndLoading();
                return false;
            }, 2000);
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="editform" enctype="multipart/form-data" method="post">
                <input type="hidden" name="formFileName" value="${formFileName}"/>
                <input type="hidden" name="schDeptFlow" value="${param.schDeptFlow}"/>
                <input type="hidden" name="recFlow" value="${rec.recFlow}"/>
                <input type="hidden" name="roleFlag" value="${param.roleFlag}"/>
                <input type="hidden" name="processFlow" value="${currRegProcess.processFlow}"/>
                <input type="hidden" name="operUserFlow" value="${param.operUserFlow}">
                <c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER}">
                    <input type="hidden" name="auditStatusId" value="${recStatusEnumTeacherAuditY.id}"/>
                </c:if>
                <c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD}">
                    <input type="hidden" name="headAuditStatusId" value="${recStatusEnumHeadAuditY.id}"/>
                </c:if>
                <div id="printDiv">
                    <table class="gridtable" width="100%">
                        <caption style="font-size:20px">社区实践 考核表</caption>
                        <thead>
                        <tr>
                            <th colspan="3" style="text-align: center;">考核内容</th>
                            <th style="text-align: center;">结果</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>考勤</td>
                            <td colspan="2">
                                出勤&#12288;
                                <input class="inputText ctrlInput validate[required,custom[number]]" name="attendance"
                                       type="text"
                                       value="${formDataMap['attendance']}" style="width: 80px;"/>
                                &#12288;天，
                                病事假&#12288;
                                <input class="inputText ctrlInput validate[required,custom[number]]" name="leave"
                                       type="text"
                                       value="${formDataMap['leave']}" style="width: 80px;"/>
                                &#12288;天。
                            </td>
                            <td align="center">
                                <label>
                                    &nbsp;<input onchange="single(this);" type="checkbox" class="validate[required]"
                                                 name="attendanceEva" value="通过"
                                                 <c:if test="${formDataMap['attendanceEva']=='通过'}">checked</c:if>/>
                                    &nbsp;通过
                                </label>
                                <label>
                                    &nbsp;<input onchange="single(this);" type="checkbox" class="validate[required]"
                                                 name="attendanceEva" value="未通过"
                                                 <c:if test="${formDataMap['attendanceEva']=='未通过'}">checked</c:if>/>
                                    &nbsp;未通过
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td rowspan="4" align="center">医德医风</td>
                            <td>服务态度、爱伤观念</td>
                            <td align="center">
                            <span style="padding-right: 100px;" class="service ctrlInput">
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="service" value="优"
                                                     <c:if test="${formDataMap['service']=='优'}">checked</c:if>/>
                                        &nbsp;优
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="service" value="良"
                                                     <c:if test="${formDataMap['service']=='良'}">checked</c:if>/>
                                        &nbsp;良
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="service" value="中"
                                                     <c:if test="${formDataMap['service']=='中'}">checked</c:if>/>
                                        &nbsp;中
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="service" value="差"
                                                     <c:if test="${formDataMap['service']=='差'}">checked</c:if>/>
                                        &nbsp;差
                                    </label>
							</span>
                            </td>
                            <td rowspan="4" align="center">
                                <label>
                                    &nbsp;<input onchange="single(this);" type="checkbox" class="validate[required]"
                                                 name="serviceEva" value="通过"
                                                 <c:if test="${formDataMap['serviceEva']=='通过'}">checked</c:if>/>
                                    &nbsp;通过
                                </label>
                                <label>
                                    &nbsp;<input onchange="single(this);" type="checkbox" class="validate[required]"
                                                 name="serviceEva" value="未通过"
                                                 <c:if test="${formDataMap['serviceEva']=='未通过'}">checked</c:if>/>
                                    &nbsp;未通过
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>工作认真负责、无差错</td>
                            <td align="center">
                            <span style="padding-right: 100px;" class="workAttitude ctrlInput">
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="workAttitude" value="优"
                                                     <c:if test="${formDataMap['workAttitude']=='优'}">checked</c:if>/>
                                        &nbsp;优
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="workAttitude" value="良"
                                                     <c:if test="${formDataMap['workAttitude']=='良'}">checked</c:if>/>
                                        &nbsp;良
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="workAttitude" value="中"
                                                     <c:if test="${formDataMap['workAttitude']=='中'}">checked</c:if>/>
                                        &nbsp;中
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="workAttitude" value="差"
                                                     <c:if test="${formDataMap['workAttitude']=='差'}">checked</c:if>/>
                                        &nbsp;差
                                    </label>
							</span>
                            </td>
                        </tr>
                        <tr>
                            <td>医疗作风端正、廉洁行医</td>
                            <td align="center">
                            <span style="padding-right: 100px;" class="style ctrlInput">
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="style" value="优"
                                                     <c:if test="${formDataMap['style']=='优'}">checked</c:if>/>
                                        &nbsp;优
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="style" value="良"
                                                     <c:if test="${formDataMap['style']=='良'}">checked</c:if>/>
                                        &nbsp;良
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="style" value="中"
                                                     <c:if test="${formDataMap['style']=='中'}">checked</c:if>/>
                                        &nbsp;中
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="style" value="差"
                                                     <c:if test="${formDataMap['style']=='差'}">checked</c:if>/>
                                        &nbsp;差
                                    </label>
							</span>
                            </td>
                        </tr>
                        <tr>
                            <td>团结协作、遵守制度</td>
                            <td align="center">
                            <span style="padding-right: 100px;" class="unite ctrlInput">
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="unite" value="优"
                                                     <c:if test="${formDataMap['unite']=='优'}">checked</c:if>/>
                                        &nbsp;优
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="unite" value="良"
                                                     <c:if test="${formDataMap['unite']=='良'}">checked</c:if>/>
                                        &nbsp;良
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="unite" value="中"
                                                     <c:if test="${formDataMap['unite']=='中'}">checked</c:if>/>
                                        &nbsp;中
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="unite" value="差"
                                                     <c:if test="${formDataMap['unite']=='差'}">checked</c:if>/>
                                        &nbsp;差
                                    </label>
							</span>
                            </td>
                        </tr>
                        <tr>
                            <td rowspan="7" align="center">全科医疗门诊</td>
                            <td>接诊、问诊、体检</td>
                            <td align="center">
                            <span style="padding-right: 100px;" class="examination ctrlInput">
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="examination" value="优"
                                                     <c:if test="${formDataMap['examination']=='优'}">checked</c:if>/>
                                        &nbsp;优
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="examination" value="良"
                                                     <c:if test="${formDataMap['examination']=='良'}">checked</c:if>/>
                                        &nbsp;良
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="examination" value="中"
                                                     <c:if test="${formDataMap['examination']=='中'}">checked</c:if>/>
                                        &nbsp;中
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="examination" value="差"
                                                     <c:if test="${formDataMap['examination']=='差'}">checked</c:if>/>
                                        &nbsp;差
                                    </label>
							</span>
                            </td>
                            <td rowspan="7" align="center">
                                <label>
                                    &nbsp;<input onchange="single(this);" type="checkbox" class="validate[required]"
                                                 name="examinationEva" value="通过"
                                                 <c:if test="${formDataMap['examinationEva']=='通过'}">checked</c:if>/>
                                    &nbsp;通过
                                </label>
                                <label>
                                    &nbsp;<input onchange="single(this);" type="checkbox" class="validate[required]"
                                                 name="examinationEva" value="未通过"
                                                 <c:if test="${formDataMap['examinationEva']=='未通过'}">checked</c:if>/>
                                    &nbsp;未通过
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>医患交往（沟通技巧）</td>
                            <td align="center">
                            <span style="padding-right: 100px;" class="communicate ctrlInput">
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="communicate" value="优"
                                                     <c:if test="${formDataMap['communicate']=='优'}">checked</c:if>/>
                                        &nbsp;优
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="communicate" value="良"
                                                     <c:if test="${formDataMap['communicate']=='良'}">checked</c:if>/>
                                        &nbsp;良
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="communicate" value="中"
                                                     <c:if test="${formDataMap['communicate']=='中'}">checked</c:if>/>
                                        &nbsp;中
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="communicate" value="差"
                                                     <c:if test="${formDataMap['communicate']=='差'}">checked</c:if>/>
                                        &nbsp;差
                                    </label>
							</span>
                            </td>
                        </tr>
                        <tr>
                            <td>整体性健康评估</td>
                            <td align="center">
                            <span style="padding-right: 100px;" class="assessment ctrlInput">
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="assessment" value="优"
                                                     <c:if test="${formDataMap['assessment']=='优'}">checked</c:if>/>
                                        &nbsp;优
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="assessment" value="良"
                                                     <c:if test="${formDataMap['assessment']=='良'}">checked</c:if>/>
                                        &nbsp;良
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="assessment" value="中"
                                                     <c:if test="${formDataMap['assessment']=='中'}">checked</c:if>/>
                                        &nbsp;中
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="assessment" value="差"
                                                     <c:if test="${formDataMap['assessment']=='差'}">checked</c:if>/>
                                        &nbsp;差
                                    </label>
							</span>
                            </td>
                        </tr>
                        <tr>
                            <td>主要健康问题的处理</td>
                            <td align="center">
                            <span style="padding-right: 100px;" class="deal ctrlInput">
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="deal" value="优"
                                                     <c:if test="${formDataMap['deal']=='优'}">checked</c:if>/>
                                        &nbsp;优
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="deal" value="良"
                                                     <c:if test="${formDataMap['deal']=='良'}">checked</c:if>/>
                                        &nbsp;良
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="deal" value="中"
                                                     <c:if test="${formDataMap['deal']=='中'}">checked</c:if>/>
                                        &nbsp;中
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="deal" value="差"
                                                     <c:if test="${formDataMap['deal']=='差'}">checked</c:if>/>
                                        &nbsp;差
                                    </label>
							</span>
                            </td>
                        </tr>
                        <tr>
                            <td>健康档案（病历）的使用和书写</td>
                            <td align="center">
                            <span style="padding-right: 100px;" class="write ctrlInput">
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="write" value="优"
                                                     <c:if test="${formDataMap['write']=='优'}">checked</c:if>/>
                                        &nbsp;优
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="write" value="良"
                                                     <c:if test="${formDataMap['write']=='良'}">checked</c:if>/>
                                        &nbsp;良
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="write" value="中"
                                                     <c:if test="${formDataMap['write']=='中'}">checked</c:if>/>
                                        &nbsp;中
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="write" value="差"
                                                     <c:if test="${formDataMap['write']=='差'}">checked</c:if>/>
                                        &nbsp;差
                                    </label>
							</span>
                            </td>
                        </tr>
                        <tr>
                            <td>社区用药规范性</td>
                            <td align="center">
                            <span style="padding-right: 100px;" class="standard ctrlInput">
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="standard" value="优"
                                                     <c:if test="${formDataMap['standard']=='优'}">checked</c:if>/>
                                        &nbsp;优
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="standard" value="良"
                                                     <c:if test="${formDataMap['standard']=='良'}">checked</c:if>/>
                                        &nbsp;良
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="standard" value="中"
                                                     <c:if test="${formDataMap['standard']=='中'}">checked</c:if>/>
                                        &nbsp;中
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="standard" value="差"
                                                     <c:if test="${formDataMap['standard']=='差'}">checked</c:if>/>
                                        &nbsp;差
                                    </label>
							</span>
                            </td>
                        </tr>
                        <tr>
                            <td>提供以病人为中心的整体性服务</td>
                            <td align="center">
                            <span style="padding-right: 100px;" class="renderService ctrlInput">
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="renderService" value="优"
                                                     <c:if test="${formDataMap['renderService']=='优'}">checked</c:if>/>
                                        &nbsp;优
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="renderService" value="良"
                                                     <c:if test="${formDataMap['renderService']=='良'}">checked</c:if>/>
                                        &nbsp;良
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="renderService" value="中"
                                                     <c:if test="${formDataMap['renderService']=='中'}">checked</c:if>/>
                                        &nbsp;中
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="renderService" value="差"
                                                     <c:if test="${formDataMap['renderService']=='差'}">checked</c:if>/>
                                        &nbsp;差
                                    </label>
							</span>
                            </td>
                        </tr>
                        <tr>
                            <td rowspan="2" align="center">社区卫生调查</td>
                            <td>社区基线调查、基础信息收集</td>
                            <td align="center">
                            <span style="padding-right: 100px;" class="information ctrlInput">
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="information" value="优"
                                                     <c:if test="${formDataMap['information']=='优'}">checked</c:if>/>
                                        &nbsp;优
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="information" value="良"
                                                     <c:if test="${formDataMap['information']=='良'}">checked</c:if>/>
                                        &nbsp;良
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="information" value="中"
                                                     <c:if test="${formDataMap['information']=='中'}">checked</c:if>/>
                                        &nbsp;中
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="information" value="差"
                                                     <c:if test="${formDataMap['information']=='差'}">checked</c:if>/>
                                        &nbsp;差
                                    </label>
							</span>
                            </td>
                            <td rowspan="2" align="center">
                                <label>
                                    &nbsp;<input onchange="single(this);" type="checkbox" class="validate[required]"
                                                 name="informationEva" value="通过"
                                                 <c:if test="${formDataMap['informationEva']=='通过'}">checked</c:if>/>
                                    &nbsp;通过
                                </label>
                                <label>
                                    &nbsp;<input onchange="single(this);" type="checkbox" class="validate[required]"
                                                 name="informationEva" value="未通过"
                                                 <c:if test="${formDataMap['informationEva']=='未通过'}">checked</c:if>/>
                                    &nbsp;未通过
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>社区诊断报告</td>
                            <td align="center">
                            <span style="padding-right: 100px;" class="diagnosis ctrlInput">
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="diagnosis" value="优"
                                                     <c:if test="${formDataMap['diagnosis']=='优'}">checked</c:if>/>
                                        &nbsp;优
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="diagnosis" value="良"
                                                     <c:if test="${formDataMap['diagnosis']=='良'}">checked</c:if>/>
                                        &nbsp;良
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="diagnosis" value="中"
                                                     <c:if test="${formDataMap['diagnosis']=='中'}">checked</c:if>/>
                                        &nbsp;中
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="diagnosis" value="差"
                                                     <c:if test="${formDataMap['diagnosis']=='差'}">checked</c:if>/>
                                        &nbsp;差
                                    </label>
							</span>
                            </td>
                        </tr>
                        <tr>
                            <td rowspan="5" align="center">社区慢病管理</td>
                            <td>高血压、糖尿病患者的筛查</td>
                            <td align="center">
                            <span style="padding-right: 100px;" class="hypertension ctrlInput">
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="hypertension" value="优"
                                                     <c:if test="${formDataMap['hypertension']=='优'}">checked</c:if>/>
                                        &nbsp;优
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="hypertension" value="良"
                                                     <c:if test="${formDataMap['hypertension']=='良'}">checked</c:if>/>
                                        &nbsp;良
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="hypertension" value="中"
                                                     <c:if test="${formDataMap['hypertension']=='中'}">checked</c:if>/>
                                        &nbsp;中
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="hypertension" value="差"
                                                     <c:if test="${formDataMap['hypertension']=='差'}">checked</c:if>/>
                                        &nbsp;差
                                    </label>
							</span>
                            </td>
                            <td rowspan="5" align="center">
                                <label>
                                    &nbsp;<input onchange="single(this);" type="checkbox" class="validate[required]"
                                                 name="hypertensionEva" value="通过"
                                                 <c:if test="${formDataMap['hypertensionEva']=='通过'}">checked</c:if>/>
                                    &nbsp;通过
                                </label>
                                <label>
                                    &nbsp;<input onchange="single(this);" type="checkbox" class="validate[required]"
                                                 name="hypertensionEva" value="未通过"
                                                 <c:if test="${formDataMap['hypertensionEva']=='未通过'}">checked</c:if>/>
                                    &nbsp;未通过
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>危险因素评价、健康教育</td>
                            <td align="center">
                            <span style="padding-right: 100px;" class="healthEducation ctrlInput">
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="healthEducation" value="优"
                                                     <c:if test="${formDataMap['healthEducation']=='优'}">checked</c:if>/>
                                        &nbsp;优
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="healthEducation" value="良"
                                                     <c:if test="${formDataMap['healthEducation']=='良'}">checked</c:if>/>
                                        &nbsp;良
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="healthEducation" value="中"
                                                     <c:if test="${formDataMap['healthEducation']=='中'}">checked</c:if>/>
                                        &nbsp;中
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="healthEducation" value="差"
                                                     <c:if test="${formDataMap['healthEducation']=='差'}">checked</c:if>/>
                                        &nbsp;差
                                    </label>
							</span>
                            </td>
                        </tr>
                        <tr>
                            <td>干预治疗方案的制订</td>
                            <td align="center">
                            <span style="padding-right: 100px;" class="programme ctrlInput">
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="programme" value="优"
                                                     <c:if test="${formDataMap['programme']=='优'}">checked</c:if>/>
                                        &nbsp;优
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="programme" value="良"
                                                     <c:if test="${formDataMap['programme']=='良'}">checked</c:if>/>
                                        &nbsp;良
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="programme" value="中"
                                                     <c:if test="${formDataMap['programme']=='中'}">checked</c:if>/>
                                        &nbsp;中
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="programme" value="差"
                                                     <c:if test="${formDataMap['programme']=='差'}">checked</c:if>/>
                                        &nbsp;差
                                    </label>
							</span>
                            </td>
                        </tr>
                        <tr>
                            <td>家庭访视全过程质量检测评价能力</td>
                            <td align="center">
                            <span style="padding-right: 100px;" class="interview ctrlInput">
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="interview" value="优"
                                                     <c:if test="${formDataMap['interview']=='优'}">checked</c:if>/>
                                        &nbsp;优
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="interview" value="良"
                                                     <c:if test="${formDataMap['interview']=='良'}">checked</c:if>/>
                                        &nbsp;良
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="interview" value="中"
                                                     <c:if test="${formDataMap['interview']=='中'}">checked</c:if>/>
                                        &nbsp;中
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="interview" value="差"
                                                     <c:if test="${formDataMap['interview']=='差'}">checked</c:if>/>
                                        &nbsp;差
                                    </label>
							</span>
                            </td>
                        </tr>
                        <tr>
                            <td>健康档案的使用</td>
                            <td align="center">
                            <span style="padding-right: 100px;" class="apply ctrlInput">
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="apply" value="优"
                                                     <c:if test="${formDataMap['apply']=='优'}">checked</c:if>/>
                                        &nbsp;优
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="apply" value="良"
                                                     <c:if test="${formDataMap['apply']=='良'}">checked</c:if>/>
                                        &nbsp;良
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="apply" value="中"
                                                     <c:if test="${formDataMap['apply']=='中'}">checked</c:if>/>
                                        &nbsp;中
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="apply" value="差"
                                                     <c:if test="${formDataMap['apply']=='差'}">checked</c:if>/>
                                        &nbsp;差
                                    </label>
							</span>
                            </td>
                        </tr>
                        <tr>
                            <td rowspan="2" align="center">传染病防治</td>
                            <td>社区传染病管理</td>
                            <td align="center">
                            <span style="padding-right: 100px;" class="infection ctrlInput">
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="infection" value="优"
                                                     <c:if test="${formDataMap['infection']=='优'}">checked</c:if>/>
                                        &nbsp;优
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="infection" value="良"
                                                     <c:if test="${formDataMap['infection']=='良'}">checked</c:if>/>
                                        &nbsp;良
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="infection" value="中"
                                                     <c:if test="${formDataMap['infection']=='中'}">checked</c:if>/>
                                        &nbsp;中
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="infection" value="差"
                                                     <c:if test="${formDataMap['infection']=='差'}">checked</c:if>/>
                                        &nbsp;差
                                    </label>
							</span>
                            </td>
                            <td rowspan="2" align="center">
                                <label>
                                    &nbsp;<input onchange="single(this);" type="checkbox" class="validate[required]"
                                                 name="infectionEva" value="通过"
                                                 <c:if test="${formDataMap['infectionEva']=='通过'}">checked</c:if>/>
                                    &nbsp;通过
                                </label>
                                <label>
                                    &nbsp;<input onchange="single(this);" type="checkbox" class="validate[required]"
                                                 name="infectionEva" value="未通过"
                                                 <c:if test="${formDataMap['infectionEva']=='未通过'}">checked</c:if>/>
                                    &nbsp;未通过
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>人群健康服务</td>
                            <td align="center">
                            <span style="padding-right: 100px;" class="healthService ctrlInput">
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="healthService" value="优"
                                                     <c:if test="${formDataMap['healthService']=='优'}">checked</c:if>/>
                                        &nbsp;优
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="healthService" value="良"
                                                     <c:if test="${formDataMap['healthService']=='良'}">checked</c:if>/>
                                        &nbsp;良
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="healthService" value="中"
                                                     <c:if test="${formDataMap['healthService']=='中'}">checked</c:if>/>
                                        &nbsp;中
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="healthService" value="差"
                                                     <c:if test="${formDataMap['healthService']=='差'}">checked</c:if>/>
                                        &nbsp;差
                                    </label>
							</span>
                            </td>
                        </tr>
                        <tr>
                            <td rowspan="4" align="center">社区重点人群保健</td>
                            <td>儿童保健</td>
                            <td align="center">
                            <span style="padding-right: 100px;" class="child ctrlInput">
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="child" value="优"
                                                     <c:if test="${formDataMap['child']=='优'}">checked</c:if>/>
                                        &nbsp;优
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="child" value="良"
                                                     <c:if test="${formDataMap['child']=='良'}">checked</c:if>/>
                                        &nbsp;良
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="child" value="中"
                                                     <c:if test="${formDataMap['child']=='中'}">checked</c:if>/>
                                        &nbsp;中
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="child" value="差"
                                                     <c:if test="${formDataMap['child']=='差'}">checked</c:if>/>
                                        &nbsp;差
                                    </label>
							</span>
                            </td>
                            <td rowspan="4" align="center">
                                <label>
                                    &nbsp;<input onchange="single(this);" type="checkbox" class="validate[required]"
                                                 name="childEva" value="通过"
                                                 <c:if test="${formDataMap['childEva']=='通过'}">checked</c:if>/>
                                    &nbsp;通过
                                </label>
                                <label>
                                    &nbsp;<input onchange="single(this);" type="checkbox" class="validate[required]"
                                                 name="childEva" value="未通过"
                                                 <c:if test="${formDataMap['childEva']=='未通过'}">checked</c:if>/>
                                    &nbsp;未通过
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>老年人保健</td>
                            <td align="center">
                            <span style="padding-right: 100px;" class="aged ctrlInput">
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="aged" value="优"
                                                     <c:if test="${formDataMap['aged']=='优'}">checked</c:if>/>
                                        &nbsp;优
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="aged" value="良"
                                                     <c:if test="${formDataMap['aged']=='良'}">checked</c:if>/>
                                        &nbsp;良
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="aged" value="中"
                                                     <c:if test="${formDataMap['aged']=='中'}">checked</c:if>/>
                                        &nbsp;中
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="aged" value="差"
                                                     <c:if test="${formDataMap['aged']=='差'}">checked</c:if>/>
                                        &nbsp;差
                                    </label>
							</span>
                            </td>
                        </tr>
                        <tr>
                            <td>妇女保健</td>
                            <td align="center">
                            <span style="padding-right: 100px;" class="women ctrlInput">
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="women" value="优"
                                                     <c:if test="${formDataMap['women']=='优'}">checked</c:if>/>
                                        &nbsp;优
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="women" value="良"
                                                     <c:if test="${formDataMap['women']=='良'}">checked</c:if>/>
                                        &nbsp;良
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="women" value="中"
                                                     <c:if test="${formDataMap['women']=='中'}">checked</c:if>/>
                                        &nbsp;中
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="women" value="差"
                                                     <c:if test="${formDataMap['women']=='差'}">checked</c:if>/>
                                        &nbsp;差
                                    </label>
							</span>
                            </td>
                        </tr>
                        <tr>
                            <td>残疾人保健</td>
                            <td align="center">
                            <span style="padding-right: 100px;" class="disabled ctrlInput">
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="disabled" value="优"
                                                     <c:if test="${formDataMap['disabled']=='优'}">checked</c:if>/>
                                        &nbsp;优
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="disabled" value="良"
                                                     <c:if test="${formDataMap['disabled']=='良'}">checked</c:if>/>
                                        &nbsp;良
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="disabled" value="中"
                                                     <c:if test="${formDataMap['disabled']=='中'}">checked</c:if>/>
                                        &nbsp;中
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="disabled" value="差"
                                                     <c:if test="${formDataMap['disabled']=='差'}">checked</c:if>/>
                                        &nbsp;差
                                    </label>
							</span>
                            </td>
                        </tr>
                        <tr>
                            <td rowspan="4" align="center">社区卫生服务管理</td>
                            <td>社区卫生服务质量管理评价水平</td>
                            <td align="center">
                            <span style="padding-right: 100px;" class="quality ctrlInput">
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="quality" value="优"
                                                     <c:if test="${formDataMap['quality']=='优'}">checked</c:if>/>
                                        &nbsp;优
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="quality" value="良"
                                                     <c:if test="${formDataMap['quality']=='良'}">checked</c:if>/>
                                        &nbsp;良
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="quality" value="中"
                                                     <c:if test="${formDataMap['quality']=='中'}">checked</c:if>/>
                                        &nbsp;中
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="quality" value="差"
                                                     <c:if test="${formDataMap['quality']=='差'}">checked</c:if>/>
                                        &nbsp;差
                                    </label>
							</span>
                            </td>
                            <td rowspan="4" align="center">
                                <label>
                                    &nbsp;<input onchange="single(this);" type="checkbox" class="validate[required]"
                                                 name="qualityEva" value="通过"
                                                 <c:if test="${formDataMap['qualityEva']=='通过'}">checked</c:if>/>
                                    &nbsp;通过
                                </label>
                                <label>
                                    &nbsp;<input onchange="single(this);" type="checkbox" class="validate[required]"
                                                 name="qualityEva" value="未通过"
                                                 <c:if test="${formDataMap['qualityEva']=='未通过'}">checked</c:if>/>
                                    &nbsp;未通过
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>医疗纠纷防范</td>
                            <td align="center">
                            <span style="padding-right: 100px;" class="dispute ctrlInput">
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="dispute" value="优"
                                                     <c:if test="${formDataMap['dispute']=='优'}">checked</c:if>/>
                                        &nbsp;优
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="dispute" value="良"
                                                     <c:if test="${formDataMap['dispute']=='良'}">checked</c:if>/>
                                        &nbsp;良
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="dispute" value="中"
                                                     <c:if test="${formDataMap['dispute']=='中'}">checked</c:if>/>
                                        &nbsp;中
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="dispute" value="差"
                                                     <c:if test="${formDataMap['dispute']=='差'}">checked</c:if>/>
                                        &nbsp;差
                                    </label>
							</span>
                            </td>
                        </tr>
                        <tr>
                            <td>社区卫生服务机构运行常规管理参与既熟悉</td>
                            <td align="center">
                            <span style="padding-right: 100px;" class="routine ctrlInput">
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="routine" value="优"
                                                     <c:if test="${formDataMap['routine']=='优'}">checked</c:if>/>
                                        &nbsp;优
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="routine" value="良"
                                                     <c:if test="${formDataMap['routine']=='良'}">checked</c:if>/>
                                        &nbsp;良
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="routine" value="中"
                                                     <c:if test="${formDataMap['routine']=='中'}">checked</c:if>/>
                                        &nbsp;中
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="routine" value="差"
                                                     <c:if test="${formDataMap['routine']=='差'}">checked</c:if>/>
                                        &nbsp;差
                                    </label>
							</span>
                            </td>
                        </tr>
                        <tr>
                            <td>提出、参与社区卫生服务课题研究</td>
                            <td align="center">
                            <span style="padding-right: 100px;" class="topic ctrlInput">
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="topic" value="优"
                                                     <c:if test="${formDataMap['topic']=='优'}">checked</c:if>/>
                                        &nbsp;优
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="topic" value="良"
                                                     <c:if test="${formDataMap['topic']=='良'}">checked</c:if>/>
                                        &nbsp;良
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="topic" value="中"
                                                     <c:if test="${formDataMap['topic']=='中'}">checked</c:if>/>
                                        &nbsp;中
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="topic" value="差"
                                                     <c:if test="${formDataMap['topic']=='差'}">checked</c:if>/>
                                        &nbsp;差
                                    </label>
							</span>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4">
                                参加各种形式学习：&#12288;
                                <input class="inputText ctrlInput validate[required,custom[number]]" name="frequency"
                                       type="text"
                                       value="${formDataMap['frequency']}" style="width: 80px;"/>
                                &#12288;次；
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                教学能力：
                            <span style="padding-right: 100px;" class="teach ctrlInput">
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="teach" value="优"
                                                     <c:if test="${formDataMap['teach']=='优'}">checked</c:if>/>
                                        &nbsp;优
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="teach" value="良"
                                                     <c:if test="${formDataMap['teach']=='良'}">checked</c:if>/>
                                        &nbsp;良
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="teach" value="中"
                                                     <c:if test="${formDataMap['teach']=='中'}">checked</c:if>/>
                                        &nbsp;中
                                    </label>
									<label>
                                        &nbsp;<input onchange="single(this);" class="validate[required]" type="checkbox"
                                                     name="teach" value="差"
                                                     <c:if test="${formDataMap['teach']=='差'}">checked</c:if>/>
                                        &nbsp;差
                                    </label>
							</span>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" style="border-right: 0;border-bottom: 0px;">
                                社区实践基地考核意见：
                            </td>
                            <td colspan="2" style="text-align: right;border-left: 0;border-bottom: 0px;">
                                考核总结果:
                                <label>
                                    &nbsp;<input onchange="single(this);" type="checkbox" class="validate[required]"
                                                 name="evaluation" value="通过"
                                                 <c:if test="${formDataMap['evaluation']=='通过'}">checked</c:if>/>
                                    &nbsp;通过
                                </label>
                                <label>
                                    &nbsp;<input onchange="single(this);" type="checkbox" class="validate[required]"
                                                 name="evaluation" value="未通过"
                                                 <c:if test="${formDataMap['evaluation']=='未通过'}">checked</c:if>/>
                                    &nbsp;未通过
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4" style="border-top: 0;">
                            <textarea
                                    style="width:99%; border:1px solid #bdbebe;	height:150px;	margin:5px 5px 5px 0px"
                                    class="ctrlInput validate[required]"
                                    name="opinion">${formDataMap['opinion']}</textarea>
                            </td>
                        </tr>
                        <tr>
                            <c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER}">
                                <td style="border-right:0px;text-align:left;border-bottom: 0px;" colspan="2">
								<span>指导老师签名：
								<c:set var="teaFlow"
                                       value="${not empty rec.auditUserFlow ?  rec.auditUserFlow : sessionScope.currUser.userFlow}"/>
								${pdfn:getSingnPhoto(teaFlow)}
								<input type="hidden" name="guideTeacher" class="theacher"
                                       value="${formDataMap['guideTeacher']}"/>
								<input type="hidden" name="teaFlow" value="${teaFlow}"/>
								</span>
                                </td>
                            </c:if>
                            <c:if test="${!(param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER)}">
                                <td style="border-right:0px;text-align:left;border-bottom: 0px;" colspan="2">
								<span>指导老师签名：
									<c:set var="teaFlow"
                                           value="${not empty rec.auditUserFlow ?  rec.auditUserFlow : formDataMap['teaFlow']}"/>
									${empty pdfn:getSingnPhoto(teaFlow) ?formDataMap['guideTeacher'] : pdfn:getSingnPhoto(teaFlow)}
									<input type="hidden" name="guideTeacher" class="theacher"
                                           value="${formDataMap['guideTeacher']}"/>
									<input type="hidden" name="teaFlow" value="${teaFlow}"/>
								</span>
                                </td>
                            </c:if>
                            <c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD}">
                                <td colspan="2" style="text-align:right;border-left: 0;border-bottom: 0px;">
								<span style="padding-right: 40px;">基地主任签名：
									<c:set var="headFlow"
                                           value="${not empty rec.headAuditUserFlow ?  rec.headAuditUserFlow : sessionScope.currUser.userFlow}"/>
									${pdfn:getSingnPhoto(headFlow)}
									<input type="hidden" name="branchDirector" class="branchDirector"
                                           value="${formDataMap['branchDirector']}"/>
									<input type="hidden" name="headFlow" value="${headFlow}"/>
								</span>
                                </td>
                            </c:if>
                            <c:if test="${!(param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD)}">
                                <td colspan="2" style="text-align:right;border-left: 0;border-bottom: 0px;">
								<span style="padding-right: 40px;">基地主任签名：
									<c:set var="headFlow"
                                           value="${not empty rec.headAuditUserFlow ?  rec.headAuditUserFlow : formDataMap['headFlow']}"/>
									${empty pdfn:getSingnPhoto(headFlow) ?formDataMap['branchDirector'] : pdfn:getSingnPhoto(headFlow)}
									<input type="hidden" name="branchDirector" class="branchDirector"
                                           value="${formDataMap['branchDirector']}"/>
									<input type="hidden" name="headFlow" value="${headFlow}"/>
								</span>

                                </td>
                            </c:if>
                        </tr>
                        <tr>
                            <td colspan="2" style="height: 60px;border-right:0px;border-top: 0;">
                                基地（盖章）
                            </td>
                            <td colspan="2" style="padding-right:5%;text-align: right;border-left: 0;border-top: 0;">
                                ${pdfn:transDate(rec.headAuditTime)}
                            </td>
                        </tr>
                        </tbody>
                        <tfoot>

                        </tfoot>
                    </table>
                </div>
                <div style="text-align: center;margin-top: 5px;">
                    <c:set var="teaSub"
                           value="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId}"/>
                    <c:set var="headSub"
                           value="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD && (not empty rec.auditStatusId)}"/>
                    <c:set var="showMsg"
                           value="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId &&  empty rec.auditStatusId}"/>

                    <c:if test="${showMsg}">
                        [<font color="red">带教老师还未审核，请等待！</font>]
                    </c:if>
                    <c:if test="${teaSub or headSub}">
                        <input type="button" value="提&#12288;交" class="search saveBtn  jin" onclick="save();"/>
                    </c:if>
                    <c:if test="${GlobalConstant.RES_ROLE_SCOPE_DOCTOR eq param.roleFlag && not empty rec.auditStatusId}">
                        <input class="search" type="button" value="打&#12288;印" onclick="jboxPrint('printDiv');"/>
                    </c:if>
                    <input type="button" value="关&#12288;闭" class="search ctrlInput" onclick="back();"/>
                </div>

            </form>
        </div>
    </div>
</div>
</body>
</html>
