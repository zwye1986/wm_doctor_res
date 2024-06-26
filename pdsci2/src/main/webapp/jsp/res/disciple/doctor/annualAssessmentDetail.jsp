<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<s:url value='/jsp/res/css/font.css'/>"></link>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_ui_combobox" value="false"/>
        <jsp:param name="jquery_ui_sortable" value="false"/>
        <jsp:param name="jquery_cxselect" value="true"/>
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
    <style type="text/css">
        .xuanze {
            background-color: #D0E3F2;
        }

        .noteItems {
            border-bottom: 1px solid #D0E3F2;
        }
        .filesTable{
            border: 1px solid #e7e7eb;
            border-collapse: collapse;
            color: #686868;
            border-spacing: 0 0;
            width: 100%;
            text-align: center;
        }
        .filesTable td{
            text-align: center;
            padding-left: 0px;
        }
    </style>

    <script type="text/javascript">


        function saveAnnualAssessment(editAppendixFlag) {
            var jsonData={};
            var fileFlows=[];
            $("input[name='fileFlows']").each(function(){
                var fileFlow=$(this).val();
                if(fileFlow)
                    fileFlows.push(fileFlow);
            });
            jsonData.fileFlows=fileFlows;
            if($("#annualAssessmentForm").validationEngine("validate")){
                var url = "<s:url value="/res/discipleNote/saveAnnualAssessment/${roleScope}"/>" + "?editAppendixFlag="+editAppendixFlag+"&jsonData="+encodeURI(encodeURI(JSON.stringify(jsonData)));
                jboxSubmit($("#annualAssessmentForm"), url, function (resp) {
                    // search();
                    window.parent.frames['mainIframe'].window.location.reload();
                }, null, true);
            }
        }

        function deleteAnnualAssessment(recordFlow) {
            jboxConfirm("确认删除该条记录?", function () {
                var requestData = {"recordFlow": recordFlow};
                var url = "<s:url value='/res/discipleNote/delAnnualAssessment' />";
                jboxStartLoading();
                jboxPost(url, requestData, function (resp) {
                    if (resp == "${GlobalConstant.DELETE_SUCCESSED}") {
                        search();
                        window.location.reload();
                    }
                }, null, true);
            });
        }

        function submitAnnualAssessment(flag) {
            if (false == $("#annualAssessmentForm").validationEngine("validate")) {
                return false;
            }
            var jsonData={};
            var fileFlows=[];
            $("input[name='fileFlows']").each(function(){
                var fileFlow=$(this).val();
                if(fileFlow)
                    fileFlows.push(fileFlow);
            });
            jsonData.fileFlows=fileFlows;
            if ("${roleScope}" == "doctor") {
                var tent = $("#experienceContent").val().trim();
                if (!tent) {
                    jboxTip("主要继承成绩未填写，无法提交！");
                    return false;
                }
            }
            if ("${roleScope}" == "disciple") {
                var tent = $("#auditContent").val();
                if (!tent) {
                    jboxTip("审核意见未填写，无法保存！").trim();
                    return false;
                }
            }
            if ("${roleScope}" == "admin") {
//                var assessmentImgUrl = $("#imgUrl").val().trim();
//                if (!assessmentImgUrl) {
//                    jboxTip("请上传学员结业考核打分表，否则无法保存！");
//                    return false;
//                }
                var tent = $("#adminContent").val().trim();
                if (!tent) {
                    jboxTip("审核意见未填写，无法保存！");
                    return false;
                }
            }
                var doctorFlow = $("#noteInfos").find("input[name='doctorFlow']").val();
                var year = $("#noteInfos").find("input[name='recordYear']").val();
                var checkUrl = "<s:url value="/res/discipleNote/annualAssessmentIsSubmit?doctorFlow="/>" + doctorFlow + "&year=" + year;
                jboxGet(checkUrl, null, function (data) {
                    if ('Y' == data && '${roleScope}' == '${GlobalConstant.RES_ROLE_SCOPE_DOCTOR}') {
                        jboxTip(year + "已存在年度考核表，无法再次提交");
                    } else {
                        jboxConfirm("提交后将无法修改，确认操作？", function () {
                         //   var annualAssessmentForm = $('#annualAssessmentForm');
                            var url = "<s:url value="/res/discipleNote/saveAnnualAssessment/${roleScope}?flag="/>" + flag + "&jsonData="+encodeURI(encodeURI(JSON.stringify(jsonData)));
                            jboxStartLoading();
                            jboxSubmit($("#annualAssessmentForm"), url, function (resp) {
                                // search();
                                window.parent.frames['mainIframe'].window.location.reload();
                            }, null, true);
                        });
                    }
                }, null, false);


            }
            function uploadImage() {
                var fileName = $("#imageFile").val();
                $("#imgName").text(fileName);
                jboxStartLoading();
                var url = "<s:url value='/res/discipleNote/uploadImg'/>";
                jboxSubmit($('#imageForm'), url, function (resp) {
                            loadAsswssmentDetail('${param.doctorFlow}','${param.recordFlow}');
                        },
                        function (resp) {
                            alert("上传文件出错！");
                        });
            }
        function refresh()
        {
            window.location.reload(true);
        }
        function addFile(){
            $('#filesTable').append($("#fileTableFormat tr:eq(0)").clone());
        }
        function moveTr(obj){
            jboxConfirm("确认删除？" , function(){
                var tr=obj.parentNode.parentNode;
                tr.remove();
            });
        }
    </script>
</head>
<body>
<%--管理员是否审核通过--%>
<c:set var="isShowSelect"
       value="${(annualAssessmentExt.auditStatusId eq discipleStatusEnumSubmit.id or
       annualAssessmentExt.auditStatusId eq discipleStatusEnumAdminAudit.id or
       annualAssessmentExt.auditStatusId eq discipleStatusEnumDiscipleAudit.id)}" />
<c:set var="canEdit"
       value="${(annualAssessmentExt.auditStatusId eq discipleStatusEnumApply.id
                    or annualAssessmentExt.auditStatusId eq discipleStatusEnumDiscipleBack.id
                    or annualAssessmentExt.auditStatusId eq discipleStatusEnumAdminBack.id)
                    or (empty annualAssessmentExt.recordFlow)}"/>
<c:set var="canEditAppendix"
       value="${  annualAssessmentExt.auditStatusId eq discipleStatusEnumAdminAudit.id }"/>

<c:set var="showAuditBtn" value="false"/>
<c:if test="${(roleScope eq GlobalConstant.RES_ROLE_SCOPE_DISCIPLE) and (annualAssessmentExt.auditStatusId eq discipleStatusEnumSubmit.id) and (operaFlag eq 'Y')}">
    <c:set var="showAuditBtn" value="true"/>
</c:if>
<c:if test="${(roleScope eq GlobalConstant.RES_ROLE_SCOPE_ADMIN) and (annualAssessmentExt.auditStatusId eq discipleStatusEnumDiscipleAudit.id) and (operaFlag eq 'Y')}">
    <c:set var="showAuditBtn" value="true"/>
</c:if>
<div class="content">
    <div style="height: 50px;width: 100%;">
        <div id="operateBtn" style="float: left;">
            <c:if test="${canEdit}">
                <p style="">
                    &#12288;
                    <c:if test="${not empty annualAssessmentExt.recordFlow}">
                    <input class="btn_blue" type="button"
                           onclick="deleteAnnualAssessment('${annualAssessmentExt.recordFlow}')"
                           value="删&#12288;除"/> &#12288;&#12288;
                    </c:if>
                    <input class="btn_blue" type="button" onclick="saveAnnualAssessment()"
                           value="保存并阅读"/>&#12288;&#12288;
                    <input class="btn_blue" onclick="submitAnnualAssessment('Y')" type="button" value="提&#12288;交"/>
                    （提交后指导老师才可审核）
                </p>
            </c:if>
            <c:if test="${canEditAppendix}">
                <p style="">
                    <input class="btn_blue" type="button" onclick="saveAnnualAssessment('Y')"
                           value="保存并阅读"/>&#12288;&#12288;
                </p>
            </c:if>
            <c:if test="${showAuditBtn}">
                <input class="search" type="button" onclick="submitAnnualAssessment('Y')" value=" 审&nbsp;核&nbsp;通&nbsp;过 "/>
                <input class="search" onclick="submitAnnualAssessment('N')" type="button" value="审核不通过"/>
            </c:if>
        </div>
    </div>
    <div style="height: 800px;width: 100%;margin-bottom: 20px;border:1px solid #D0E3F2;overflow:auto;">
        <form id="annualAssessmentForm" method="post" enctype="multipart/form-data">
            <input type="hidden" name="recordFlow" value="${annualAssessmentExt.recordFlow}"/>
            <div id="noteInfos"
                 style="<c:if
                         test="${empty annualAssessmentExt}">display: none;</c:if>margin-bottom: 20px">
                <div style="margin-bottom: 10px">
                    <p style="margin:20px auto auto 30px">

                        <span>年度：</span>
                        <c:if test="${canEdit}">
                            <input type="text" name="recordYear"
                                   value="${annualAssessmentExt.recordYear}"
                                   class="inputText validate[required]"
                                   readonly="readonly"/> 年
                        </c:if>
                        <c:if test="${!canEdit}">
                            <label class="inputText"> &#12288;${annualAssessmentExt.recordYear}&#12288; </label>年
                        </c:if>

                    </p>
                </div>
                <table class="basic" width="95%" style="margin-left: 3%;margin-bottom: 15px">
                    <tr>
                        <td width="20%">
                            跟师规培学员姓名：
                        </td>
                        <td width="30%">
                            <input class="inputText" name="doctorName" readonly
                                   value="${annualAssessmentExt.doctorName}<c:if test="${empty annualAssessmentExt.doctorName}">${resDoctor.doctorName}</c:if>"/>
                            <input type="hidden" name="doctorFlow"
                                   value="${annualAssessmentExt.doctorFlow}"/>
                        </td>
                        <td width="20%">
                            师承指导老师姓名：
                        </td>
                        <td width="30%">
                            <input class="inputText" name="teacherName" readonly
                                   value="${annualAssessmentExt.teacherName}<c:if test="${empty annualAssessmentExt.teacherName}">${annualAssessmentExt.discipleTeacherName}</c:if>"/>
                            <input type="hidden" name="teacherFlow"
                                   value="${annualAssessmentExt.discipleTeacherFlow}"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            跟师时间：
                        </td>
                        <td colspan="3">
                            <c:if test="${isShowSelect}">
                                <lable style="margin-left: 20px;height: 24px;width: 250px;" class=" inputText">
                                        ${pdfn:transDateTimeForPattern(annualAssessmentExt.studyStartDate,'yyyy-MM-dd','yyyy')}年${pdfn:transDateTimeForPattern(annualAssessmentExt.studyStartDate,'yyyy-MM-dd','MM')}月${pdfn:transDateTimeForPattern(annualAssessmentExt.studyStartDate,'yyyy-MM-dd','dd')}日
                                    ——${pdfn:transDateTimeForPattern(annualAssessmentExt.studyEndDate,'yyyy-MM-dd','yyyy')}年${pdfn:transDateTimeForPattern(annualAssessmentExt.studyEndDate,'yyyy-MM-dd','MM')}月${pdfn:transDateTimeForPattern(annualAssessmentExt.studyEndDate,'yyyy-MM-dd','dd')}日
                                </lable>
                            </c:if>
                            <c:if test="${!isShowSelect}">
                                <lable style="margin-left: 20px;height: 24px;width: 250px;" class=" inputText">
                                        ${pdfn:transDateTimeForPattern(annualAssessmentExt.discipleStartDate,'yyyy-MM-dd','yyyy')}年${pdfn:transDateTimeForPattern(annualAssessmentExt.discipleStartDate,'yyyy-MM-dd','MM')}月${pdfn:transDateTimeForPattern(annualAssessmentExt.discipleStartDate,'yyyy-MM-dd','dd')}日
                                    ——${pdfn:transDateTimeForPattern(annualAssessmentExt.discipleEndDate,'yyyy-MM-dd','yyyy')}年${pdfn:transDateTimeForPattern(annualAssessmentExt.discipleEndDate,'yyyy-MM-dd','MM')}月${pdfn:transDateTimeForPattern(annualAssessmentExt.discipleEndDate,'yyyy-MM-dd','dd')}日
                                </lable>
                            </c:if>
                            <input type="text" class=" inputText" value="${annualAssessmentExt.discipleStartDate}"
                                   name="studyStartDate"
                                   hidden style="margin-left: 20px;">
                            <input type="text" class=" inputText" value="${annualAssessmentExt.discipleEndDate}"
                                   name="studyEndDate"
                                   hidden style="margin-left: 20px;">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            跟师天数：
                        </td>
                        <td>
                            <c:if test="${!isShowSelect}">
                                <input class="inputText" name="dayCount" value="${ annualAssessmentExt.daySelectCount}"
                                       readonly="readonly"/>
                            </c:if>
                            <c:if test="${isShowSelect}">
                                <input class="inputText" name="dayCount" value="${annualAssessmentExt.dayCount}"
                                       readonly="readonly"/>
                            </c:if>
                        </td>
                        <td>
                            撰写跟师笔记份数：
                        </td>
                        <td>
                            <c:if test="${!isShowSelect}">
                                <input class="inputText" name="noteCount" value="${annualAssessmentExt.noteSelectCount}"
                                       readonly="readonly"/>
                            </c:if>
                            <c:if test="${isShowSelect}">
                                <input class="inputText" name="noteCount" value="${annualAssessmentExt.noteCount}"
                                       readonly="readonly"/>
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            撰写跟师心得份数：
                        </td>
                        <td>
                            <c:if test="${!isShowSelect}">
                                <input class="inputText" name="experienceCount"
                                       value="${annualAssessmentExt.experienceSelectCount}" readonly="readonly"/>
                            </c:if>
                            <c:if test="${isShowSelect}">
                                <input class="inputText" name="experienceCount"
                                       value="${annualAssessmentExt.experienceCount}" readonly="readonly"/>
                            </c:if>
                        </td>
                        <td>
                            学习中医经典部数：
                        </td>
                        <td>
                            <c:if test="${!isShowSelect}">
                                <input class="inputText" name="tcmCount" value="${annualAssessmentExt.bookStudyCount}"
                                       readonly="readonly"/>
                            </c:if>
                            <c:if test="${isShowSelect}">
                                <input class="inputText" name="tcmCount" value="${annualAssessmentExt.tcmCount}"
                                       readonly="readonly"/>
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            经典学习体会份数：
                        </td>
                        <td>
                            <c:if test="${!isShowSelect}">
                                <input class="inputText" name="xxthCount"
                                       value="${annualAssessmentExt.bookexpSelectCount}" readonly="readonly"/>
                            </c:if>
                            <c:if test="${isShowSelect}">
                                <input class="inputText" name="xxthCount" value="${annualAssessmentExt.xxthCount}"
                                       readonly="readonly"/>
                            </c:if>
                        </td>
                        <td>
                            撰写跟师医案份数：
                        </td>
                        <td>
                            <c:if test="${!isShowSelect}">
                                <input class="inputText" name="typicalCasesCount"
                                       value="${annualAssessmentExt.casesCount}" readonly="readonly"/>
                            </c:if>
                            <c:if test="${isShowSelect}">
                                <input class="inputText" name="typicalCasesCount"
                                       value="${annualAssessmentExt.typicalCasesCount}" readonly="readonly"/>
                            </c:if>

                        </td>
                    </tr>
                    <tr>
                        <td>
                            跟师学习考核打分表：
                        </td>
                        <td colspan="3">
                            <c:choose>
                                <c:when test="${(roleScope eq GlobalConstant.RES_ROLE_SCOPE_ADMIN) and (annualAssessmentExt.auditStatusId eq discipleStatusEnumDiscipleAudit.id)}">
                                    <%--<label id="imgName"></label>--%>
                                    <input type="hidden" id="imgUrl" name="assessmentImgUrl"
                                           value="${annualAssessmentExt.assessmentImgUrl}"/>
                                    <a style="margin-left: 20px;" onclick="$('#imageFile').click();">上传</a>
                                    <a style="margin-left: 20px;"
                                       href="<s:url value='/jsp/res/disciple/admin/scord.docx'/>">下载模板</a>
                                    <c:if test="${not empty annualAssessmentExt.assessmentImgUrl}">
                                        <a style="margin-left: 20px;"
                                           href="${sysCfgMap['upload_base_url']}/${annualAssessmentExt.assessmentImgUrl }"
                                           target="_blank">查看</a>
                                    </c:if>
                                </c:when>
                                <c:otherwise>
                                    <c:if test="${empty annualAssessmentExt.assessmentImgUrl}">
                                        <lable style="margin-left: 20px;height: 24px;width: 250px;" class=" inputText">
                                            尚未打分，暂时无法查看
                                        </lable>
                                    </c:if>
                                    <c:if test="${not empty annualAssessmentExt.assessmentImgUrl and (annualAssessmentExt.auditStatusId == discipleStatusEnumAdminAudit.id or annualAssessmentExt.auditStatusId == discipleStatusEnumAdminBack.id)}">
                                        <a style="margin-left: 20px;" class="btn_blue"
                                           href="${sysCfgMap['upload_base_url']}/${annualAssessmentExt.assessmentImgUrl }"
                                           target="_blank">查看</a>
                                    </c:if>
                                </c:otherwise>
                            </c:choose>

                        </td>
                    </tr>
                    <tr>
                        <td colspan="4">
                            <c:if test="${roleScope eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR}">

                            </c:if>
                            培养计划完成情况（如未完成，需说明理由）
                                <textarea class="xltxtarea validate[required]"
                                          <c:if test="${not canEdit}">readonly="readonly"</c:if>
                                          style="height: 250px"
                                          name="compleleContent">${annualAssessmentExt.compleleContent}</textarea>
                            <p style="float: right;margin-right: 30px;margin-bottom: 15px;text-align: right">
                                <span>跟师规培学员签名：</span><input class="inputText" readonly
                                                             value="${annualAssessmentExt.doctorName}"/><br/>
                                <input type="text" name="studentSignTime" class="inputText"
                                       value="${pdfn:transDate(annualAssessmentExt.compleleSignTime)}"
                                       readonly="readonly"/>
                            </p>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4">
                            主要继承成绩（包括典型病案收集，老师经验整理，经典学习，跟师心得，论文（著）发表/出版情况及科研情况等）
                                <textarea class="xltxtarea" id="experienceContent"
                                          <c:if test="${not canEdit}">readonly="readonly"</c:if>
                                          style="height: 250px"
                                          name="experienceContent">${annualAssessmentExt.experienceContent}</textarea>
                            <p style="float: right;margin-right: 30px;margin-bottom: 15px;text-align: right">
                                <span>跟师规培学员签名：</span><input class="inputText" readonly
                                                             value="${annualAssessmentExt.doctorName}"/><br/>
                                <input type="text" name="studentSignTime" class="inputText"
                                       value="${pdfn:transDate(annualAssessmentExt.experienceSignTime)}"
                                       readonly="readonly"/>
                            </p>
                            <c:if test="${annualAssessmentExt.auditStatusId eq 'Apply'
                                            or 'DiscipleBack' eq annualAssessmentExt.auditStatusId
                                            or 'AdminBack' eq annualAssessmentExt.auditStatusId
                                            or canEditAppendix
                                            or empty annualAssessmentExt.auditStatusId}">
                                <table style="max-width: 295px;float: left;margin-left: 5px;margin-bottom: 20px;" class="filesTable" id="filesTable">
                                    <tr>
                                        <td style="text-align: left;padding-left: 10px;">
                                            附件名
                                                        <span>
                                                            <font color="#999" title="${applicationScope.sysCfgMap["inx_image_support_suffix"]}">&#12288;&#12288;仅支持图片格式 </font>
                                                            &#12288;
                                                            <img class="opBtn" title="新增"
                                                                 src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                                                                 style="cursor: pointer;" onclick="addFile();"/>
                                                        </span>
                                        </td>
                                        <td width="75px">操作</td>
                                    </tr>
                                    <c:forEach items="${discipleFiles}" var="discipleFile">
                                        <tr>
                                            <td style="text-align: left;padding-left: 10px;">
                                                <input hidden name="fileFlows" value="${discipleFile.fileFlow}">
                                                <a href="${sysCfgMap['upload_base_url']}/${discipleFile.filePath}" target="_blank">${discipleFile.fileName}</a>
                                            </td>
                                            <td width="75px">
                                                <img class='opBtn' title='删除' style='cursor: pointer;'
                                                     src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick='moveTr(this);'/>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </c:if>
                            <c:if test="${annualAssessmentExt.auditStatusId ne 'Apply' and not empty discipleFiles and !canEditAppendix}">
                                <table style="max-width: 295px;float: left;margin-left: 5px;margin-bottom: 20px;" class="filesTable" id="filesTable">
                                    <tr>
                                        <td style="text-align: left;padding-left: 10px;">
                                            附件名
                                        </td>
                                    </tr>
                                    <c:forEach items="${discipleFiles}" var="discipleFile">
                                        <tr>
                                            <td style="text-align: left;padding-left: 10px;">
                                                <a href="${sysCfgMap['upload_base_url']}/${discipleFile.filePath}" target="_blank">${discipleFile.fileName}</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4">
                            指导老师对规培学员本年度学习情况的评语（对跟师规培学员学习态度、工作态度、跟师效果等作出评议）：
                                    <textarea id="auditContent" class="xltxtarea" style="height: 250px"
                                              <c:if test="${(annualAssessmentExt.auditStatusId != 'Submit') or (not (roleScope eq GlobalConstant.RES_ROLE_SCOPE_DISCIPLE))}">readonly="readonly"</c:if>
                                              name="auditContent">${annualAssessmentExt.auditContent}</textarea>
                            <p style="float: right;margin-right: 30px;margin-bottom: 15px;text-align: right">
                                <span>指导老师签名：</span>
                                <c:if test="${((annualAssessmentExt.auditStatusId != 'Submit')
                                and(annualAssessmentExt.auditStatusId != 'Apply'  or 'DiscipleBack' eq annualAssessmentExt.auditStatusId or 'AdminBack' eq annualAssessmentExt.auditStatusId)
                                and (not empty annualAssessmentExt.auditStatusId )) or (roleScope eq GlobalConstant.RES_ROLE_SCOPE_DISCIPLE)}">
                                    <c:set var="teaFlow" value="${annualAssessmentExt.teacherFlow}"/>
                                    ${pdfn:getSingnPhoto(teaFlow)}
                                </c:if>
                                <c:if test="${!(((annualAssessmentExt.auditStatusId != 'Submit')
                                and(annualAssessmentExt.auditStatusId != 'Apply'or 'DiscipleBack' eq annualAssessmentExt.auditStatusId or 'AdminBack' eq annualAssessmentExt.auditStatusId)
                                and (not empty annualAssessmentExt.auditStatusId )) or (roleScope eq GlobalConstant.RES_ROLE_SCOPE_DISCIPLE))}">
                                    <input class="inputText"  readonly="readonly"/>
                                </c:if><br/>
                                <c:if test="${((annualAssessmentExt.auditStatusId != 'Submit')
                                and(annualAssessmentExt.auditStatusId != 'Apply'or 'DiscipleBack' eq annualAssessmentExt.auditStatusId or 'AdminBack' eq annualAssessmentExt.auditStatusId)
                                and (not empty annualAssessmentExt.auditStatusId )) or (roleScope eq GlobalConstant.RES_ROLE_SCOPE_DISCIPLE)}">
                                    <c:set var="teaFlow" value="${annualAssessmentExt.teacherFlow}"/>
                                    <input type="text" class="inputText"
                                           name="auditTime"
                                           value="${ empty pdfn:transDate(annualAssessmentExt.auditTime) ? pdfn:getCurrDate() : pdfn:transDate(annualAssessmentExt.auditTime)}"
                                           readonly="readonly"/>&nbsp;
                                </c:if>
                                <c:if test="${!(((annualAssessmentExt.auditStatusId != 'Submit')
                                and(annualAssessmentExt.auditStatusId != 'Apply' or 'DiscipleBack' eq annualAssessmentExt.auditStatusId or 'AdminBack' eq annualAssessmentExt.auditStatusId)
                                and (not empty annualAssessmentExt.auditStatusId )) or (roleScope eq GlobalConstant.RES_ROLE_SCOPE_DISCIPLE))}">
                                    <input class="inputText"  readonly="readonly"/>&nbsp;
                                </c:if>
                                <c:if test="${roleScope eq GlobalConstant.RES_ROLE_SCOPE_DISCIPLE}">
                                    <input type="hidden" name="auditUserName"
                                           value="${annualAssessmentExt.discipleTeacherName}"/>
                                    <input type="hidden" name="auditUserFlow"
                                           value="${annualAssessmentExt.discipleTeacherFlow}"/>
                                </c:if>
                            </p>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4">
                            单位考核意见：（包括：本年度继承教学实施情况的总体评价，对跟师学员的学习态度、工作态度、跟师教学质量和效果等作出评议，并按照跟师学习考核打分表对年度考核是否合格提出明确意见。）
                                        <textarea
                                                class="xltxtarea" id="adminContent"
                                                <c:if test="${(roleScope  != GlobalConstant.RES_ROLE_SCOPE_ADMIN) or (annualAssessmentExt.auditStatusId != 'DiscipleAudit')}">readonly="readonly"</c:if>
                                                style="height: 250px"
                                                name="adminContent">${annualAssessmentExt.adminContent}</textarea>
                            <p style="float: right;margin-right: 30px;margin-bottom: 15px;text-align: right">
                                <span>负责人签名：</span>
                                <c:if test="${not empty annualAssessmentExt.adminUserFlow}">
                                    ${pdfn:getSingnPhoto(annualAssessmentExt.adminUserFlow)}
                                </c:if>
                                <c:if test="${empty annualAssessmentExt.adminUserFlow}">
                                    <input class="inputText" readonly/>&#12288;&#12288;&#12288;&nbsp;&nbsp;
                                </c:if>
                                <br/>
                                时&#12288;&#12288;&#12288;间：<input type="text" class="inputText"
                                       value="${pdfn:transDate(annualAssessmentExt.adminTime)}"
                                       readonly="readonly"/>（公章）
                                <c:if test="${roleScope eq GlobalConstant.RES_ROLE_SCOPE_ADMIN}">
                                    <input type="hidden" name="adminUserName"
                                           value="${sessionScope.currUser.userName}"/>
                                    <input type="hidden" name="adminUserFlow"
                                           value="${sessionScope.currUser.userFlow}"/>
                                </c:if>
                            </p>
                        </td>
                    </tr>
                </table>
            </div>
        </form>
    </div>
</div>
<form id="imageForm" enctype="multipart/form-data" method="post" style="display: none">
    <input type="file" id="imageFile" name="file"
           onchange="uploadImage();"/>
    <input type="hidden" name="url" value="${annualAssessmentExt.assessmentImgUrl}">
    <input type="hidden" name="recordFlow" value="${annualAssessmentExt.recordFlow}">
</form>
<%--附件模板--%>
<table class="filesTable" id="fileTableFormat" style="display: none" style="width: 100%">
    <tr>
        <td style="text-align: left;padding-left: 10px;">
            <input type='file' name='files' class='validate[required]' multiple='multiple' style="max-width: 200px;"
                   accept="${applicationScope.sysCfgMap["inx_image_support_suffix"]}"/>
        </td>
        <td>
            <img class='opBtn' title='删除' style='cursor: pointer;'
                 src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick='moveTr(this);'/>
        </td>
    </tr>
</table>
</body>
</html>