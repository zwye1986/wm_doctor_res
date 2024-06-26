<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="false"/>
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
    <style type="text/css">
        table {
            margin: 10px 0;
            border-collapse: collapse;
        }

        caption, th, td {
            height: 40px;
        }

        caption {
            line-height: 40px;
            text-align: left;
            font-weight: bold;
            padding-left: 10px;
            border-bottom: 1px solid #ddd;
            color: #f60;
            margin-bottom: 10px;
        }

        th {
            text-align: right;
            font-weight: 500;
            padding-right: 5px;
            color: #333;
        }

        td {
            text-align: left;
            padding-left: 5px;
        }

        [type='text'] {
            width: 150px;
            height: 22px;
        }

        select {
            width: 153px;
            height: 27px;
        }
    </style>

    <script type="text/javascript">
        function doClose() {
            top.jboxClose();
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix" style="padding:0; ">
            <div class="div_table" style="width:100%;">
                <table class="basic" width="100%">
                    <tr>
                        <th style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">完成比例</th>
                        <th style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">跟师记录</th>
                        <th style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">跟师学习<br/>笔记</th>
                        <th style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">跟师心得</th>
                        <th style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">中医经典书籍<br/>学习记录</th>
                        <th style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">经典医籍<br/>学习体会</th>
                        <th style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">跟师医案</th>
                        <th style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">跟师学习<br/>年度考核<br/>情况记录表
                        </th>
                        <th style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">跟师学习<br/>结业考核<br/>情况记录表
                        </th>
                    </tr>
                    <tr>
                        <td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">
                            要求数
                        </td>
                        <td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">
                            ${empty noteTypeReqMap[noteTypeEnumFollowTeacherRecord.id] ? '-' : noteTypeReqMap[noteTypeEnumFollowTeacherRecord.id]}
                            <c:set var="followTeacherRecordReq" value="${noteTypeReqMap[noteTypeEnumFollowTeacherRecord.id]}"></c:set>
                        </td>
                        <td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">
                            ${empty noteTypeReqMap[noteTypeEnumNote.id] ? '-' : noteTypeReqMap[noteTypeEnumNote.id]}
                            <c:set var="noteReq" value="${noteTypeReqMap[noteTypeEnumNote.id]}"></c:set>
                        </td>
                        <td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">
                            ${empty noteTypeReqMap[noteTypeEnumExperience.id] ? '-' : noteTypeReqMap[noteTypeEnumExperience.id]}
                                <c:set var="experienceReq" value="${noteTypeReqMap[noteTypeEnumExperience.id]}"></c:set>
                        </td>
                        <td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">
                            ${empty noteTypeReqMap[noteTypeEnumBookRecord.id] ? '-' : noteTypeReqMap[noteTypeEnumBookRecord.id]}
                                <c:set var="bookRecordReq" value="${noteTypeReqMap[noteTypeEnumBookRecord.id]}"></c:set>
                        </td>
                        <td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">
                            ${empty noteTypeReqMap[noteTypeEnumBookExperience.id] ? '-' : noteTypeReqMap[noteTypeEnumBookExperience.id]}
                                <c:set var="bookExperienceReq" value="${noteTypeReqMap[noteTypeEnumBookExperience.id]}"></c:set>
                        </td>
                        <td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">
                            ${empty noteTypeReqMap[noteTypeEnumTypicalCases.id] ? '-' : noteTypeReqMap[noteTypeEnumTypicalCases.id]}
                                <c:set var="typicalCasesReq" value="${noteTypeReqMap[noteTypeEnumTypicalCases.id]}"></c:set>
                        </td>
                        <td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">
                            ${empty noteTypeReqMap[noteTypeEnumAnnualAssessment.id] ? '-' : noteTypeReqMap[noteTypeEnumAnnualAssessment.id]}
                                <c:set var="annualAssessmentReq" value="${noteTypeReqMap[noteTypeEnumAnnualAssessment.id]}"></c:set>
                        </td>
                        <td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">
                            ${empty noteTypeReqMap[noteTypeEnumGraduationAssessment.id] ? '-' : noteTypeReqMap[noteTypeEnumGraduationAssessment.id]}
                                <c:set var="graduationAssessmentReq" value="${noteTypeReqMap[noteTypeEnumGraduationAssessment.id]}"></c:set>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">
                            完成数
                        </td>
                        <td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">
                            ${empty noteTypeFinMap[noteTypeEnumFollowTeacherRecord.id] ? '0' : noteTypeFinMap[noteTypeEnumFollowTeacherRecord.id]}
                                <c:set var="followTeacherRecordFin" value="${noteTypeFinMap[noteTypeEnumFollowTeacherRecord.id]}"></c:set>
                        </td>
                        <td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">
                            ${empty noteTypeFinMap[noteTypeEnumNote.id] ? '0' : noteTypeFinMap[noteTypeEnumNote.id]}
                                <c:set var="noteFin" value="${noteTypeFinMap[noteTypeEnumNote.id]}"></c:set>
                        </td>
                        <td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">
                            ${empty noteTypeFinMap[noteTypeEnumExperience.id] ? '0' : noteTypeFinMap[noteTypeEnumExperience.id]}
                                <c:set var="experienceFin" value="${noteTypeFinMap[noteTypeEnumExperience.id]}"></c:set>
                        </td>
                        <td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">
                            ${empty noteTypeFinMap[noteTypeEnumBookRecord.id] ? '0' : noteTypeFinMap[noteTypeEnumBookRecord.id]}
                                <c:set var="bookRecordFin" value="${noteTypeFinMap[noteTypeEnumBookRecord.id]}"></c:set>
                        </td>
                        <td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">
                            ${empty noteTypeFinMap[noteTypeEnumBookExperience.id] ? '0' : noteTypeFinMap[noteTypeEnumBookExperience.id]}
                                <c:set var="bookExperienceFin" value="${noteTypeFinMap[noteTypeEnumBookExperience.id]}"></c:set>
                        </td>
                        <td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">
                            ${empty noteTypeFinMap[noteTypeEnumTypicalCases.id] ? '0' : noteTypeFinMap[noteTypeEnumTypicalCases.id]}
                                <c:set var="typicalCasesFin" value="${noteTypeFinMap[noteTypeEnumTypicalCases.id]}"></c:set>
                        </td>
                        <td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">
                            ${empty noteTypeFinMap[noteTypeEnumAnnualAssessment.id] ? '0' : noteTypeFinMap[noteTypeEnumAnnualAssessment.id]}
                                <c:set var="annualAssessmentFin" value="${noteTypeFinMap[noteTypeEnumAnnualAssessment.id]}"></c:set>
                        </td>
                        <td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">
                            ${empty noteTypeFinMap[noteTypeEnumGraduationAssessment.id] ? '0' : noteTypeFinMap[noteTypeEnumGraduationAssessment.id]}
                                <c:set var="graduationAssessmentFin" value="${noteTypeFinMap[noteTypeEnumGraduationAssessment.id]}"></c:set>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">
                            完成比例
                        </td>
                        <td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">
                            <c:if test="${empty followTeacherRecordReq}">
                                -
                            </c:if>
                            <c:if test="${not empty followTeacherRecordReq}">
                                <c:if test="${followTeacherRecordReq eq '0'}">
                                    100%
                                </c:if>
                                <c:if test="${followTeacherRecordReq ne '0'}">
                                    <fmt:formatNumber type="NUMBER" var="per" value="${followTeacherRecordFin/followTeacherRecordReq}"/>
                                    <c:if test="${per >= 1.0}">
                                        100%
                                    </c:if>
                                    <c:if test="${per < 1.0}">
                                        <fmt:formatNumber type="percent" value="${per}" maxFractionDigits="2"/>
                                    </c:if>
                                </c:if>
                            </c:if>
                        </td>
                        <td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">
                            <c:if test="${empty noteReq}">
                                -
                            </c:if>
                            <c:if test="${not empty noteReq}">
                                <c:if test="${noteReq eq '0'}">
                                    100%
                                </c:if>
                                <c:if test="${noteReq ne '0'}">
                                    <fmt:formatNumber type="NUMBER" var="per" value="${noteFin/noteReq}"/>
                                    <c:if test="${per >= 1.0}">
                                        100%
                                    </c:if>
                                    <c:if test="${per < 1.0}">
                                        <fmt:formatNumber type="percent" value="${per}" maxFractionDigits="2"/>
                                    </c:if>
                                </c:if>
                            </c:if>
                        </td>
                        <td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">
                            <c:if test="${empty experienceReq}">
                                -
                            </c:if>
                            <c:if test="${not empty experienceReq}">
                                <c:if test="${experienceReq eq '0'}">
                                    100%
                                </c:if>
                                <c:if test="${experienceReq ne '0'}">
                                    <fmt:formatNumber type="NUMBER" var="per" value="${experienceFin/experienceReq}"/>
                                    <c:if test="${per >= 1.0}">
                                        100%
                                    </c:if>
                                    <c:if test="${per < 1.0}">
                                        <fmt:formatNumber type="percent" value="${per}" maxFractionDigits="2"/>
                                    </c:if>
                                </c:if>
                            </c:if>
                        </td>
                        <td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">
                            <c:if test="${empty bookRecordReq}">
                                -
                            </c:if>
                            <c:if test="${not empty bookRecordReq}">
                                <c:if test="${bookRecordReq eq '0'}">
                                    100%
                                </c:if>
                                <c:if test="${bookRecordReq ne '0'}">
                                    <fmt:formatNumber type="NUMBER" var="per" value="${bookRecordFin/bookRecordReq}"/>
                                    <c:if test="${per >= 1.0}">
                                        100%
                                    </c:if>
                                    <c:if test="${per < 1.0}">
                                        <fmt:formatNumber type="percent" value="${per}" maxFractionDigits="2"/>
                                    </c:if>
                                </c:if>
                            </c:if>
                        </td>
                        <td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">
                            <c:if test="${empty bookExperienceReq}">
                                -
                            </c:if>
                            <c:if test="${not empty bookExperienceReq}">
                                <c:if test="${bookExperienceReq eq '0'}">
                                    100%
                                </c:if>
                                <c:if test="${bookExperienceReq ne '0'}">
                                    <fmt:formatNumber type="NUMBER" var="per" value="${bookExperienceFin/bookExperienceReq}"/>
                                    <c:if test="${per >= 1.0}">
                                        100%
                                    </c:if>
                                    <c:if test="${per < 1.0}">
                                        <fmt:formatNumber type="percent" value="${per}" maxFractionDigits="2"/>
                                    </c:if>
                                </c:if>
                            </c:if>
                        </td>
                        <td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">
                            <c:if test="${empty typicalCasesReq}">
                                -
                            </c:if>
                            <c:if test="${not empty typicalCasesReq}">
                                <c:if test="${typicalCasesReq eq '0'}">
                                    100%
                                </c:if>
                                <c:if test="${typicalCasesReq ne '0'}">
                                    <fmt:formatNumber type="NUMBER" var="per" value="${typicalCasesFin/typicalCasesReq}"/>
                                    <c:if test="${per >= 1.0}">
                                        100%
                                    </c:if>
                                    <c:if test="${per < 1.0}">
                                        <fmt:formatNumber type="percent" value="${per}" maxFractionDigits="2"/>
                                    </c:if>
                                </c:if>
                            </c:if>
                        </td>
                        <td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">
                            <c:if test="${empty annualAssessmentReq}">
                                -
                            </c:if>
                            <c:if test="${not empty annualAssessmentReq}">
                                <c:if test="${annualAssessmentReq eq '0'}">
                                    100%
                                </c:if>
                                <c:if test="${annualAssessmentReq ne '0'}">
                                    <fmt:formatNumber type="NUMBER" var="per" value="${annualAssessmentFin/annualAssessmentReq}"/>
                                    <c:if test="${per >= 1.0}">
                                        100%
                                    </c:if>
                                    <c:if test="${per < 1.0}">
                                        <fmt:formatNumber type="percent" value="${per}" maxFractionDigits="2"/>
                                    </c:if>
                                </c:if>
                            </c:if>
                        </td>
                        <td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">
                            <c:if test="${empty graduationAssessmentReq}">
                                -
                            </c:if>
                            <c:if test="${not empty graduationAssessmentReq}">
                                <c:if test="${graduationAssessmentReq eq '0'}">
                                    100%
                                </c:if>
                                <c:if test="${graduationAssessmentReq ne '0'}">
                                    <fmt:formatNumber type="NUMBER" var="per" value="${graduationAssessmentFin/graduationAssessmentReq}"/>
                                    <c:if test="${per >= 1.0}">
                                        100%
                                    </c:if>
                                    <c:if test="${per < 1.0}">
                                        <fmt:formatNumber type="percent" value="${per}" maxFractionDigits="2"/>
                                    </c:if>
                                </c:if>
                            </c:if>
                        </td>
                    </tr>
                </table>
            </div>
            <p style="text-align: center;">
                <input type="button" onclick="doClose();" class="search" value="关&#12288;闭"/>
            </p>
        </div>
    </div>
</div>
</body>
</html>
