<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <style>
        .basic .inputText {
            text-align: left;
        }
    </style>
    <c:if test="${param.view != GlobalConstant.FLAG_Y}">
        <script type="text/javascript">
            function nextOpt(step) {
                if (false == $("#projForm").validationEngine("validate")) {
                    return false;
                }
                if(!checkApplicant()){
                    return false;
                }
                 /*if (false == $("#applicant_oneForm").validationEngine("validate")) {
                 jboxTip("请填写第一申请人信息");
                 return false;
                 }else if (false == $("#applicant_twoForm").validationEngine("validate")) {
                 jboxTip("请填写第二申请人信息");
                 return false;
                 }else if (false == $("#applicant_threeForm").validationEngine("validate")) {
                 jboxTip("请填写第三申请人信息");
                 return false;
                 }*/

                var form = $('#projForm');
                form.append('<input type="hidden" name="nextPageName" value="' + step + '"/>');
                $('#nxt').attr({"disabled": "disabled"});
                $('#prev').attr({"disabled": "disabled"});
                jboxStartLoading();
                form.submit();
            }

            function checkBDDate(dt) {
                var dates = $(':text', $(dt).closest("td"));
                if (dates[0].value && dates[1].value && dates[0].value > dates[1].value) {
                    jboxTip("开始时间不能大于结束时间！");
                    dt.value = "";
                }

            }

            function checkApplicant(){
                var a=true;
                $('#applicant_one .notNULL').each(function(index,element){
                    if(!$(element).val()){
                        jboxTip("请填写第一申请人信息");
                        a=false;
                    }
                });
                $('#applicant_two .notNULL').each(function(index,element){
                    if(!$(element).val()){
                        jboxTip("请填写第二申请人信息");
                        a=false;
                    }
                });
                $('#applicant_three .notNULL').each(function(index,element){
                    if(!$(element).val()){
                        jboxTip("请填写第三申请人信息");
                        a=false;
                    }
                });
                return a;
            }
        </script>
    </c:if>
    <script type="text/javascript">
        function showApplicantDiv(divId, obj) {
            if (false == $("#projForm").validationEngine("validate")) {
                return false;
            }
            $('#applicant_one').hide();
            $('#applicant_two').hide();
            $('#applicant_three').hide();
            $('#applicant_four').hide();
            $('#applicant_five').hide();
            $('#applicant_six').hide();
            $('#applicant_seven').hide();
            $('#applicant_eight').hide();
            $('#applicant_nine').hide();
            $('#tags li').removeClass('selectTag');
            $("#" + divId).show();
            $(obj).parent().addClass("selectTag")
        }
    </script>
</head>
<body>
<div id="main">
    <div class="mainright">
        <div class="content">
            <div style="margin-top: 10px;">
                <form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post"
                      id="projForm" style="position: relative;">
                    <input type="hidden" id="pageName" name="pageName" value="step3"/>
                    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
                    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
                    <input type="hidden" name="recTypeId" value="${param.recTypeId}"/>

                    <font style="font-size: 14px; font-weight:bold;color: #333;">二、课题组主要成员情况表</font>
                    <div>
                        <ul id="tags">
                            <li class="selectTag"><a onclick="showApplicantDiv('applicant_one',this)"
                                                     href="javascript:void(0)">第一申请人</a></li>
                            <li><a onclick="showApplicantDiv('applicant_two',this)" href="javascript:void(0)">第二申请人</a>
                            </li>
                            <li><a onclick="showApplicantDiv('applicant_three',this)"
                                   href="javascript:void(0)">第三申请人</a></li>
                            <li><a onclick="showApplicantDiv('applicant_four',this)" href="javascript:void(0)">第四申请人</a>
                            </li>
                            <li><a onclick="showApplicantDiv('applicant_five',this)" href="javascript:void(0)">第五申请人</a>
                            </li>
                            <li><a onclick="showApplicantDiv('applicant_six',this)" href="javascript:void(0)">第六申请人</a>
                            </li>
                            <li><a onclick="showApplicantDiv('applicant_seven',this)"
                                   href="javascript:void(0)">第七申请人</a></li>
                            <li><a onclick="showApplicantDiv('applicant_eight',this)"
                                   href="javascript:void(0)">第八申请人</a></li>
                            <li><a onclick="showApplicantDiv('applicant_nine',this)" href="javascript:void(0)">第九申请人</a>
                            </li>
                        </ul>
                    </div>
                    <div id="applicant_one">
                        <table class="basic" style="width: 100%;margin-top: 10px;">

                            <tr>
                                <th style="text-align: center;">第一申请人</th>
                                <td style="text-align: center;"><font color="red">*</font>姓名：</td>
                                <td style="text-align: left;">
                                    <input type="text" class="inputText validate[required] notNULL"
                                           name="applicant_one_name" readonly="readonly"
                                           value="${jszyyjApplyMap.memberList[0].objMap.memberList_name}<c:if test="${empty (jszyyjApplyMap.memberList[0].objMap.memberList_name)}">${resultMap.memberList[0].objMap.memberList_name}</c:if>"
                                           style="width: 80%"/><%--${resultMap.applicant_one_name}--%>
                                </td>
                                <td style="text-align: center;"><font color="red">*</font>性别：</td>
                                <td style="text-align: left;">
                                    <input type="text" class="inputText validate[required] notNULL"
                                           name="applicant_one_sexId"
                                           value="${resultMap.applicant_one_sexId}" style="width: 80%"/>
                                </td>
                                <td style="text-align: center;"><font color="red">*</font>职称/职务：</td>
                                <td style="text-align: left;">
                                    <input type="text" class="inputText validate[required] notNULL"
                                           name="applicant_one_Title"
                                           value="${resultMap.applicant_one_Title}" style="width: 80%"/>
                                </td>
                                <td style="text-align: center;"><font color="red">*</font>联系电话：</td>
                                <td style="text-align: left;">
                                    <input type="text" class="inputText validate[required] notNULL"
                                           name="applicant_one_phone"
                                           value="${resultMap.applicant_one_phone}" style="width: 80%"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: center;"><font color="red">*</font>所承担的任务：</td>
                                <td style="text-align: left;" colspan="6">
                                    <input type="text" class="inputText validate[required] notNULL"
                                           name="applicant_one_task"
                                           value="${resultMap.applicant_one_task}" style="width: 80%"/>
                                </td>
                                <td style="text-align: center;"><font color="red">*</font>电子信箱：</td>
                                <td style="text-align: left;">
                                    <input type="text" class="inputText validate[required] notNULL"
                                           name="applicant_one_mail"
                                           value="${resultMap.applicant_one_mail}" style="width: 80%"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: center;">外语语种：</td>
                                <td style="text-align: left;" colspan="2">
                                    <input type="text" class="inputText"
                                           name="applicant_one_language"
                                           value="${resultMap.applicant_one_language}" style="width: 80%"/>
                                </td>
                                <td style="text-align: center;">熟练程度：</td>
                                <td style="text-align: left;" colspan="5">
                                    <input type="radio" value="精通"
                                           <c:if test="${resultMap.applicant_one_proficient eq '精通'}">checked="checked"</c:if>
                                           name="applicant_one_proficient" id="proficient_one_master"/><label
                                        for="proficient_one_master">精通</label>&#12288;
                                    <input type="radio" value="熟练"
                                           <c:if test="${resultMap.applicant_one_proficient eq '熟练'}">checked="checked"</c:if>
                                           name="applicant_one_proficient" id="proficient_one_skilled"/><label
                                        for="proficient_one_skilled">熟练</label>&#12288;
                                    <input type="radio" value="一般"
                                           <c:if test="${resultMap.applicant_one_proficient eq '一般'}">checked="checked"</c:if>
                                           name="applicant_one_proficient" id="proficient_one_common"/><label
                                        for="proficient_one_common">一般</label>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="9" style="text-align: left;">
                                    <span>主要工作简历</span>
                                <textarea class="xltxtarea" name="applicant_one_job"
                                          style="width:98%;height: 150px;">${resultMap.applicant_one_job}
                                </textarea>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="9" style="text-align: left;">
                                    <span>正在承担的其他科研项目(请列明任务来源、课题名称、研究起止年月、本人在该课题中的任务和分工)</span>
                                <textarea class="xltxtarea" name="applicant_one_applyProj"
                                          style="width:98%;height: 150px;">${resultMap.applicant_one_applyProj}
                                </textarea>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="9" style="text-align: left;">
                                    <span>以往研究工作成果(论文、著作目录及获学术奖励或已经研究开发的上市新药、获得的专利等情况)</span>
                                    <br/>
                                <textarea class="xltxtarea" name="applicant_one_relatedProj"
                                          placeholder="与本课题相关的研究成果"
                                          style="width:98%;height: 150px;">${resultMap.applicant_one_relatedProj}
                                </textarea>
                                    <br/>
                                <textarea class="xltxtarea" name="applicant_one_otherProj"
                                          placeholder="其他领域的研究成果"
                                          style="width:98%;height: 150px;">${resultMap.applicant_one_otherProj}
                                </textarea>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <div id="applicant_two" style="display:none">
                        <table class="basic" style="width: 100%;margin-top: 10px;">
                            <tr>
                                <th style="text-align: center;">第二申请人</th>
                                <td style="text-align: center;"><font color="red">*</font>姓名：</td>
                                <td style="text-align: left;">
                                    <input type="text" class="inputText validate[required] notNULL"
                                           name="applicant_two_name" readonly="readonly"
                                           value="${jszyyjApplyMap.memberList[1].objMap.memberList_name}<c:if test="${empty (jszyyjApplyMap.memberList[1].objMap.memberList_name)}">${resultMap.memberList[1].objMap.memberList_name}</c:if>"
                                           style="width: 80%"/> <%--${resultMap.applicant_two_name}--%>
                                </td>
                                <td style="text-align: center;"><font color="red">*</font>性别：</td>
                                <td style="text-align: left;">
                                    <input type="text" class="inputText validate[required] notNULL"
                                           name="applicant_two_sexId"
                                           value="${resultMap.applicant_two_sexId}" style="width: 80%"/>
                                </td>
                                <td style="text-align: center;"><font color="red">*</font>职称/职务：</td>
                                <td style="text-align: left;">
                                    <input type="text" class="inputText validate[required] notNULL"
                                           name="applicant_two_Title"
                                           value="${resultMap.applicant_two_Title}" style="width: 80%"/>
                                </td>
                                <td style="text-align: center;"><font color="red">*</font>联系电话：</td>
                                <td style="text-align: left;">
                                    <input type="text" class="inputText validate[required] notNULL"
                                           name="applicant_two_phone"
                                           value="${resultMap.applicant_two_phone}" style="width: 80%"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: center;"><font color="red">*</font>所承担的任务：</td>
                                <td style="text-align: left;" colspan="6">
                                    <input type="text" class="inputText validate[required] notNULL"
                                           name="applicant_two_task"
                                           value="${resultMap.applicant_two_task}" style="width: 80%"/>
                                </td>
                                <td style="text-align: center;"><font color="red">*</font>电子信箱：</td>
                                <td style="text-align: left;">
                                    <input type="text" class="inputText validate[required] notNULL"
                                           name="applicant_two_mail"
                                           value="${resultMap.applicant_two_mail}" style="width: 80%"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: center;">外语语种：</td>
                                <td style="text-align: left;" colspan="2">
                                    <input type="text" class="inputText"
                                           name="applicant_two_language" value="${resultMap.applicant_two_language}"
                                           style="width: 80%"/>
                                </td>
                                <td style="text-align: center;">熟练程度：</td>
                                <td style="text-align: left;" colspan="5">
                                    <input type="radio" value="精通"
                                           <c:if test="${resultMap.applicant_two_proficient eq '精通'}">checked="checked"</c:if>
                                           name="applicant_two_proficient" id="proficient_two_master"/>
                                    <label for="proficient_two_master">精通</label>&#12288;
                                    <input type="radio" value="熟练"
                                           <c:if test="${resultMap.applicant_two_proficient eq '熟练'}">checked="checked"</c:if>
                                           name="applicant_two_proficient" id="proficient_two_skilled"/>
                                    <label for="proficient_two_skilled">熟练</label>&#12288;
                                    <input type="radio" value="一般"
                                           <c:if test="${resultMap.applicant_two_proficient eq '一般'}">checked="checked"</c:if>
                                           name="applicant_two_proficient" id="proficient_two_common"/>
                                    <label for="proficient_two_common">一般</label>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="9" style="text-align: left;">
                                    <span>主要工作简历</span>
                    <textarea class="xltxtarea" name="applicant_two_job"
                              style="width:98%;height: 150px;">${resultMap.applicant_two_job}
                    </textarea>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="9" style="text-align: left;">
                                    <span>正在承担的其他科研项目(请列明任务来源、课题名称、研究起止年月、本人在该课题中的任务和分工)</span>
                    <textarea class="xltxtarea" name="applicant_two_applyProj"
                              style="width:98%;height: 150px;">${resultMap.applicant_two_applyProj}
                    </textarea>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="9" style="text-align: left;">
                                    <span>以往研究工作成果(论文、著作目录及获学术奖励或已经研究开发的上市新药、获得的专利等情况)</span>
                                    <br/>
                    <textarea class="xltxtarea" name="applicant_two_relatedProj" placeholder="与本课题相关的研究成果"
                              style="width:98%;height: 150px;">${resultMap.applicant_two_relatedProj}
                    </textarea>
                                    <br/>
                    <textarea class="xltxtarea" name="applicant_two_otherProj" placeholder="其他领域的研究成果"
                              style="width:98%;height: 150px;">${resultMap.applicant_two_otherProj}
                    </textarea>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <div id="applicant_three" style="display:none">
                        <table class="basic" style="width: 100%;margin-top: 10px;">

                            <tr>
                                <th style="text-align: center;">第三申请人</th>
                                <td style="text-align: center;"><font color="red">*</font>姓名：</td>
                                <td style="text-align: left;">
                                    <input type="text" class="inputText validate[required] notNULL"
                                           name="applicant_three_name" readonly="readonly"
                                           value="${jszyyjApplyMap.memberList[2].objMap.memberList_name}<c:if test="${empty (jszyyjApplyMap.memberList[2].objMap.memberList_name)}">${resultMap.memberList[2].objMap.memberList_name}</c:if>"
                                           style="width: 80%"/><%--${resultMap.applicant_three_name}--%>
                                </td>
                                <td style="text-align: center;"><font color="red">*</font>性别：</td>
                                <td style="text-align: left;">
                                    <input type="text" class="inputText validate[required] notNULL"
                                           name="applicant_three_sexId"
                                           value="${resultMap.applicant_three_sexId}" style="width: 80%"/>
                                </td>
                                <td style="text-align: center;"><font color="red">*</font>职称/职务：</td>
                                <td style="text-align: left;">
                                    <input type="text" class="inputText validate[required] notNULL"
                                           name="applicant_three_Title"
                                           value="${resultMap.applicant_three_Title}" style="width: 80%"/>
                                </td>
                                <td style="text-align: center;"><font color="red">*</font>联系电话：</td>
                                <td style="text-align: left;">
                                    <input type="text" class="inputText validate[required] notNULL"
                                           name="applicant_three_phone"
                                           value="${resultMap.applicant_three_phone}" style="width: 80%"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: center;"><font color="red">*</font>所承担的任务：</td>
                                <td style="text-align: left;" colspan="6">
                                    <input type="text" class="inputText validate[required] notNULL"
                                           name="applicant_three_task"
                                           value="${resultMap.applicant_three_task}" style="width: 80%"/>
                                </td>
                                <td style="text-align: center;"><font color="red">*</font>电子信箱：</td>
                                <td style="text-align: left;">
                                    <input type="text" class="inputText validate[required] notNULL"
                                           name="applicant_three_mail"
                                           value="${resultMap.applicant_three_mail}" style="width: 80%"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: center;">外语语种：</td>
                                <td style="text-align: left;" colspan="2">
                                    <input type="text" class="inputText "
                                           name="applicant_three_language"
                                           value="${resultMap.applicant_three_language}"
                                           style="width: 80%"/>
                                </td>
                                <td style="text-align: center;">熟练程度：</td>
                                <td style="text-align: left;" colspan="5">
                                    <input type="radio" value="精通"
                                           <c:if test="${resultMap.applicant_three_proficient eq '精通'}">checked="checked"</c:if>
                                           name="applicant_three_proficient" id="proficient_three_master"/>
                                    <label for="proficient_three_master">精通</label>&#12288;
                                    <input type="radio" value="熟练"
                                           <c:if test="${resultMap.applicant_three_proficient eq '熟练'}">checked="checked"</c:if>
                                           name="applicant_three_proficient" id="proficient_three_skilled"/>
                                    <label for="proficient_three_skilled">熟练</label>&#12288;
                                    <input type="radio" value="一般"
                                           <c:if test="${resultMap.applicant_three_proficient eq '一般'}">checked="checked"</c:if>
                                           name="applicant_three_proficient" id="proficient_three_common"/>
                                    <label for="proficient_three_common">一般</label>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="9" style="text-align: left;">
                                    <span>主要工作简历</span>
                <textarea class="xltxtarea" name="applicant_three_job"
                          style="width:98%;height: 150px;">${resultMap.applicant_three_job}
                </textarea>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="9" style="text-align: left;">
                                    <span>正在承担的其他科研项目(请列明任务来源、课题名称、研究起止年月、本人在该课题中的任务和分工)</span>
                <textarea class="xltxtarea" name="applicant_three_applyProj"
                          style="width:98%;height: 150px;">${resultMap.applicant_three_applyProj}
                </textarea>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="9" style="text-align: left;">
                                    <span>以往研究工作成果(论文、著作目录及获学术奖励或已经研究开发的上市新药、获得的专利等情况)</span>
                                    <br/>
                <textarea class="xltxtarea" name="applicant_three_relatedProj" placeholder="与本课题相关的研究成果"
                          style="width:98%;height: 150px;">${resultMap.applicant_three_relatedProj}
                </textarea>
                                    <br/>
                <textarea class="xltxtarea" name="applicant_three_otherProj" placeholder="其他领域的研究成果"
                          style="width:98%;height: 150px;">${resultMap.applicant_three_otherProj}
                </textarea>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <div id="applicant_four" style="display:none">
                        <table class="basic" style="width: 100%;margin-top: 10px;">

                            <tr>
                                <th style="text-align: center;">第四申请人</th>
                                <td style="text-align: center;">姓名：</td>
                                <td style="text-align: left;">
                                    <input type="text" class="inputText " name="applicant_four_name"
                                           value="${resultMap.applicant_four_name}" style="width: 80%"/>
                                </td>
                                <td style="text-align: center;">性别：</td>
                                <td style="text-align: left;">
                                    <input type="text" class="inputText " name="applicant_four_sexId"
                                           value="${resultMap.applicant_four_sexId}" style="width: 80%"/>
                                </td>
                                <td style="text-align: center;">职称/职务：</td>
                                <td style="text-align: left;">
                                    <input type="text" class="inputText " name="applicant_four_Title"
                                           value="${resultMap.applicant_four_Title}" style="width: 80%"/>
                                </td>
                                <td style="text-align: center;">联系电话：</td>
                                <td style="text-align: left;">
                                    <input type="text" class="inputText " name="applicant_four_phone"
                                           value="${resultMap.applicant_four_phone}" style="width: 80%"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: center;">所承担的任务：</td>
                                <td style="text-align: left;" colspan="6">
                                    <input type="text" class="inputText " name="applicant_four_task"
                                           value="${resultMap.applicant_four_task}" style="width: 80%"/>
                                </td>
                                <td style="text-align: center;">电子信箱：</td>
                                <td style="text-align: left;">
                                    <input type="text" class="inputText " name="applicant_four_mail"
                                           value="${resultMap.applicant_four_mail}" style="width: 80%"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: center;">外语语种：</td>
                                <td style="text-align: left;" colspan="2">
                                    <input type="text" class="inputText "
                                           name="applicant_four_language" value="${resultMap.applicant_four_language}"
                                           style="width: 80%"/>
                                </td>
                                <td style="text-align: center;">熟练程度：</td>
                                <td style="text-align: left;" colspan="5">
                                    <input type="radio" value="精通"
                                           <c:if test="${resultMap.applicant_four_proficient eq '精通'}">checked="checked"</c:if>
                                           name="applicant_four_proficient" id="proficient_four_master"/>
                                    <label for="proficient_four_master">精通</label>&#12288;
                                    <input type="radio" value="熟练"
                                           <c:if test="${resultMap.applicant_four_proficient eq '熟练'}">checked="checked"</c:if>
                                           name="applicant_four_proficient" id="proficient_four_skilled"/>
                                    <label for="proficient_four_skilled">熟练</label>&#12288;
                                    <input type="radio" value="一般"
                                           <c:if test="${resultMap.applicant_four_proficient eq '一般'}">checked="checked"</c:if>
                                           name="applicant_four_proficient" id="proficient_four_common"/>
                                    <label for="proficient_four_common">一般</label>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="9" style="text-align: left;">
                                    <span>主要工作简历</span>
                <textarea class="xltxtarea" name="applicant_four_job"
                          style="width:98%;height: 150px;">${resultMap.applicant_four_job}
                </textarea>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="9" style="text-align: left;">
                                    <span>正在承担的其他科研项目(请列明任务来源、课题名称、研究起止年月、本人在该课题中的任务和分工)</span>
                <textarea class="xltxtarea" name="applicant_four_applyProj"
                          style="width:98%;height: 150px;">${resultMap.applicant_four_applyProj}
                </textarea>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="9" style="text-align: left;">
                                    <span>以往研究工作成果(论文、著作目录及获学术奖励或已经研究开发的上市新药、获得的专利等情况)</span>
                                    <br/>
                <textarea class="xltxtarea" name="applicant_four_relatedProj" placeholder="与本课题相关的研究成果"
                          style="width:98%;height: 150px;">${resultMap.applicant_four_relatedProj}
                </textarea>
                                    <br/>
                <textarea class="xltxtarea" name="applicant_four_otherProj" placeholder="其他领域的研究成果"
                          style="width:98%;height: 150px;">${resultMap.applicant_four_otherProj}
                </textarea>
                                </td>
                            </tr>
                        </table>
                    </div>

                    <div id="applicant_five" style="display:none">
                        <table class="basic" style="width: 100%;margin-top: 10px;">

                            <tr>
                                <th style="text-align: center;">第五申请人</th>
                                <td style="text-align: center;">姓名：</td>
                                <td style="text-align: left;">
                                    <input type="text" class="inputText " name="applicant_five_name"
                                           value="${resultMap.applicant_five_name}" style="width: 80%"/>
                                </td>
                                <td style="text-align: center;">性别：</td>
                                <td style="text-align: left;">
                                    <input type="text" class="inputText " name="applicant_five_sexId"
                                           value="${resultMap.applicant_five_sexId}" style="width: 80%"/>
                                </td>
                                <td style="text-align: center;">职称/职务：</td>
                                <td style="text-align: left;">
                                    <input type="text" class="inputText " name="applicant_five_Title"
                                           value="${resultMap.applicant_five_Title}" style="width: 80%"/>
                                </td>
                                <td style="text-align: center;">联系电话：</td>
                                <td style="text-align: left;">
                                    <input type="text" class="inputText " name="applicant_five_phone"
                                           value="${resultMap.applicant_five_phone}" style="width: 80%"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: center;">所承担的任务：</td>
                                <td style="text-align: left;" colspan="6">
                                    <input type="text" class="inputText " name="applicant_five_task"
                                           value="${resultMap.applicant_five_task}" style="width: 80%"/>
                                </td>
                                <td style="text-align: center;">电子信箱：</td>
                                <td style="text-align: left;">
                                    <input type="text" class="inputText " name="applicant_five_mail"
                                           value="${resultMap.applicant_five_mail}" style="width: 80%"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: center;">外语语种：</td>
                                <td style="text-align: left;" colspan="2">
                                    <input type="text" class="inputText "
                                           name="applicant_five_language" value="${resultMap.applicant_five_language}"
                                           style="width: 80%"/>
                                </td>
                                <td style="text-align: center;">熟练程度：</td>
                                <td style="text-align: left;" colspan="5">
                                    <input type="radio" value="精通"
                                           <c:if test="${resultMap.applicant_five_proficient eq '精通'}">checked="checked"</c:if>
                                           name="applicant_five_proficient" id="proficient_five_master"/>
                                    <label for="proficient_five_master">精通</label>&#12288;
                                    <input type="radio" value="熟练"
                                           <c:if test="${resultMap.applicant_five_proficient eq '熟练'}">checked="checked"</c:if>
                                           name="applicant_five_proficient" id="proficient_five_skilled"/>
                                    <label for="proficient_five_skilled">熟练</label>&#12288;
                                    <input type="radio" value="一般"
                                           <c:if test="${resultMap.applicant_five_proficient eq '一般'}">checked="checked"</c:if>
                                           name="applicant_five_proficient" id="proficient_five_common"/>
                                    <label for="proficient_five_common">一般</label>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="9" style="text-align: left;">
                                    <span>主要工作简历</span>
                <textarea class="xltxtarea" name="applicant_five_job"
                          style="width:98%;height: 150px;">${resultMap.applicant_five_job}
                </textarea>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="9" style="text-align: left;">
                                    <span>正在承担的其他科研项目(请列明任务来源、课题名称、研究起止年月、本人在该课题中的任务和分工)</span>
                <textarea class="xltxtarea" name="applicant_five_applyProj"
                          style="width:98%;height: 150px;">${resultMap.applicant_five_applyProj}
                </textarea>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="9" style="text-align: left;">
                                    <span>以往研究工作成果(论文、著作目录及获学术奖励或已经研究开发的上市新药、获得的专利等情况)</span>
                                    <br/>
                <textarea class="xltxtarea" name="applicant_five_relatedProj" placeholder="与本课题相关的研究成果"
                          style="width:98%;height: 150px;">${resultMap.applicant_five_relatedProj}
                </textarea>
                                    <br/>
                <textarea class="xltxtarea" name="applicant_five_otherProj" placeholder="其他领域的研究成果"
                          style="width:98%;height: 150px;">${resultMap.applicant_five_otherProj}
                </textarea>
                                </td>
                            </tr>
                        </table>
                    </div>

                    <div id="applicant_six" style="display:none">
                        <table class="basic" style="width: 100%;margin-top: 10px;">

                            <tr>
                                <th style="text-align: center;">第六申请人</th>
                                <td style="text-align: center;">姓名：</td>
                                <td style="text-align: left;">
                                    <input type="text" class="inputText " name="applicant_six_name"
                                           value="${resultMap.applicant_six_name}" style="width: 80%"/>
                                </td>
                                <td style="text-align: center;">性别：</td>
                                <td style="text-align: left;">
                                    <input type="text" class="inputText " name="applicant_six_sexId"
                                           value="${resultMap.applicant_six_sexId}" style="width: 80%"/>
                                </td>
                                <td style="text-align: center;">职称/职务：</td>
                                <td style="text-align: left;">
                                    <input type="text" class="inputText " name="applicant_six_Title"
                                           value="${resultMap.applicant_six_Title}" style="width: 80%"/>
                                </td>
                                <td style="text-align: center;">联系电话：</td>
                                <td style="text-align: left;">
                                    <input type="text" class="inputText " name="applicant_six_phone"
                                           value="${resultMap.applicant_six_phone}" style="width: 80%"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: center;">所承担的任务：</td>
                                <td style="text-align: left;" colspan="6">
                                    <input type="text" class="inputText " name="applicant_six_task"
                                           value="${resultMap.applicant_six_task}" style="width: 80%"/>
                                </td>
                                <td style="text-align: center;">电子信箱：</td>
                                <td style="text-align: left;">
                                    <input type="text" class="inputText " name="applicant_six_mail"
                                           value="${resultMap.applicant_six_mail}" style="width: 80%"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: center;">外语语种：</td>
                                <td style="text-align: left;" colspan="2">
                                    <input type="text" class="inputText "
                                           name="applicant_six_language" value="${resultMap.applicant_six_language}"
                                           style="width: 80%"/>
                                </td>
                                <td style="text-align: center;">熟练程度：</td>
                                <td style="text-align: left;" colspan="5">
                                    <input type="radio" value="精通"
                                           <c:if test="${resultMap.applicant_six_proficient eq '精通'}">checked="checked"</c:if>
                                           name="applicant_six_proficient" id="proficient_six_master"/>
                                    <label for="proficient_six_master">精通</label>&#12288;
                                    <input type="radio" value="熟练"
                                           <c:if test="${resultMap.applicant_six_proficient eq '熟练'}">checked="checked"</c:if>
                                           name="applicant_six_proficient" id="proficient_six_skilled"/>
                                    <label for="proficient_six_skilled">熟练</label>&#12288;
                                    <input type="radio" value="一般"
                                           <c:if test="${resultMap.applicant_six_proficient eq '一般'}">checked="checked"</c:if>
                                           name="applicant_six_proficient" id="proficient_six_common"/>
                                    <label for="proficient_six_common">一般</label>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="9" style="text-align: left;">
                                    <span>主要工作简历</span>
                <textarea class="xltxtarea" name="applicant_six_job"
                          style="width:98%;height: 150px;">${resultMap.applicant_six_job}
                </textarea>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="9" style="text-align: left;">
                                    <span>正在承担的其他科研项目(请列明任务来源、课题名称、研究起止年月、本人在该课题中的任务和分工)</span>
                <textarea class="xltxtarea" name="applicant_six_applyProj"
                          style="width:98%;height: 150px;">${resultMap.applicant_six_applyProj}
                </textarea>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="9" style="text-align: left;">
                                    <span>以往研究工作成果(论文、著作目录及获学术奖励或已经研究开发的上市新药、获得的专利等情况)</span>
                                    <br/>
                <textarea class="xltxtarea" name="applicant_six_relatedProj" placeholder="与本课题相关的研究成果"
                          style="width:98%;height: 150px;">${resultMap.applicant_six_relatedProj}
                </textarea>
                                    <br/>
                <textarea class="xltxtarea" name="applicant_six_otherProj" placeholder="其他领域的研究成果"
                          style="width:98%;height: 150px;">${resultMap.applicant_six_otherProj}
                </textarea>
                                </td>
                            </tr>
                        </table>
                    </div>

                    <div id="applicant_seven" style="display:none">
                        <table class="basic" style="width: 100%;margin-top: 10px;">

                            <tr>
                                <th style="text-align: center;">第七申请人</th>
                                <td style="text-align: center;">姓名：</td>
                                <td style="text-align: left;">
                                    <input type="text" class="inputText " name="applicant_seven_name"
                                           value="${resultMap.applicant_seven_name}" style="width: 80%"/>
                                </td>
                                <td style="text-align: center;">性别：</td>
                                <td style="text-align: left;">
                                    <input type="text" class="inputText " name="applicant_seven_sexId"
                                           value="${resultMap.applicant_seven_sexId}" style="width: 80%"/>
                                </td>
                                <td style="text-align: center;">职称/职务：</td>
                                <td style="text-align: left;">
                                    <input type="text" class="inputText " name="applicant_seven_Title"
                                           value="${resultMap.applicant_seven_Title}" style="width: 80%"/>
                                </td>
                                <td style="text-align: center;">联系电话：</td>
                                <td style="text-align: left;">
                                    <input type="text" class="inputText " name="applicant_seven_phone"
                                           value="${resultMap.applicant_seven_phone}" style="width: 80%"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: center;">所承担的任务：</td>
                                <td style="text-align: left;" colspan="6">
                                    <input type="text" class="inputText " name="applicant_seven_task"
                                           value="${resultMap.applicant_seven_task}" style="width: 80%"/>
                                </td>
                                <td style="text-align: center;">电子信箱：</td>
                                <td style="text-align: left;">
                                    <input type="text" class="inputText " name="applicant_seven_mail"
                                           value="${resultMap.applicant_seven_mail}" style="width: 80%"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: center;">外语语种：</td>
                                <td style="text-align: left;" colspan="2">
                                    <input type="text" class="inputText "
                                           name="applicant_seven_language" value="${resultMap.applicant_seven_language}"
                                           style="width: 80%"/>
                                </td>
                                <td style="text-align: center;">熟练程度：</td>
                                <td style="text-align: left;" colspan="5">
                                    <input type="radio" value="精通"
                                           <c:if test="${resultMap.applicant_seven_proficient eq '精通'}">checked="checked"</c:if>
                                           name="applicant_seven_proficient" id="proficient_seven_master"/>
                                    <label for="proficient_seven_master">精通</label>&#12288;
                                    <input type="radio" value="熟练"
                                           <c:if test="${resultMap.applicant_seven_proficient eq '熟练'}">checked="checked"</c:if>
                                           name="applicant_seven_proficient" id="proficient_seven_skilled"/>
                                    <label for="proficient_seven_skilled">熟练</label>&#12288;
                                    <input type="radio" value="一般"
                                           <c:if test="${resultMap.applicant_seven_proficient eq '一般'}">checked="checked"</c:if>
                                           name="applicant_seven_proficient" id="proficient_seven_common"/>
                                    <label for="proficient_seven_common">一般</label>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="9" style="text-align: left;">
                                    <span>主要工作简历</span>
                <textarea class="xltxtarea" name="applicant_seven_job"
                          style="width:98%;height: 150px;">${resultMap.applicant_seven_job}
                </textarea>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="9" style="text-align: left;">
                                    <span>正在承担的其他科研项目(请列明任务来源、课题名称、研究起止年月、本人在该课题中的任务和分工)</span>
                <textarea class="xltxtarea" name="applicant_seven_applyProj"
                          style="width:98%;height: 150px;">${resultMap.applicant_seven_applyProj}
                </textarea>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="9" style="text-align: left;">
                                    <span>以往研究工作成果(论文、著作目录及获学术奖励或已经研究开发的上市新药、获得的专利等情况)</span>
                                    <br/>
                <textarea class="xltxtarea" name="applicant_seven_relatedProj" placeholder="与本课题相关的研究成果"
                          style="width:98%;height: 150px;">${resultMap.applicant_seven_relatedProj}
                </textarea>
                                    <br/>
                <textarea class="xltxtarea" name="applicant_seven_otherProj" placeholder="其他领域的研究成果"
                          style="width:98%;height: 150px;">${resultMap.applicant_seven_otherProj}
                </textarea>
                                </td>
                            </tr>
                        </table>
                    </div>

                    <div id="applicant_eight" style="display:none">
                        <table class="basic" style="width: 100%;margin-top: 10px;">

                            <tr>
                                <th style="text-align: center;">第八申请人</th>
                                <td style="text-align: center;">姓名：</td>
                                <td style="text-align: left;">
                                    <input type="text" class="inputText " name="applicant_eight_name"
                                           value="${resultMap.applicant_eight_name}" style="width: 80%"/>
                                </td>
                                <td style="text-align: center;">性别：</td>
                                <td style="text-align: left;">
                                    <input type="text" class="inputText " name="applicant_eight_sexId"
                                           value="${resultMap.applicant_eight_sexId}" style="width: 80%"/>
                                </td>
                                <td style="text-align: center;">职称/职务：</td>
                                <td style="text-align: left;">
                                    <input type="text" class="inputText " name="applicant_eight_Title"
                                           value="${resultMap.applicant_eight_Title}" style="width: 80%"/>
                                </td>
                                <td style="text-align: center;">联系电话：</td>
                                <td style="text-align: left;">
                                    <input type="text" class="inputText " name="applicant_eight_phone"
                                           value="${resultMap.applicant_eight_phone}" style="width: 80%"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: center;">所承担的任务：</td>
                                <td style="text-align: left;" colspan="6">
                                    <input type="text" class="inputText " name="applicant_eight_task"
                                           value="${resultMap.applicant_eight_task}" style="width: 80%"/>
                                </td>
                                <td style="text-align: center;">电子信箱：</td>
                                <td style="text-align: left;">
                                    <input type="text" class="inputText " name="applicant_eight_mail"
                                           value="${resultMap.applicant_eight_mail}" style="width: 80%"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: center;">外语语种：</td>
                                <td style="text-align: left;" colspan="2">
                                    <input type="text" class="inputText "
                                           name="applicant_eight_language" value="${resultMap.applicant_eight_language}"
                                           style="width: 80%"/>
                                </td>
                                <td style="text-align: center;">熟练程度：</td>
                                <td style="text-align: left;" colspan="5">
                                    <input type="radio" value="精通"
                                           <c:if test="${resultMap.applicant_eight_proficient eq '精通'}">checked="checked"</c:if>
                                           name="applicant_eight_proficient" id="proficient_eight_master"/>
                                    <label for="proficient_eight_master">精通</label>&#12288;
                                    <input type="radio" value="熟练"
                                           <c:if test="${resultMap.applicant_eight_proficient eq '熟练'}">checked="checked"</c:if>
                                           name="applicant_eight_proficient" id="proficient_eight_skilled"/>
                                    <label for="proficient_eight_skilled">熟练</label>&#12288;
                                    <input type="radio" value="一般"
                                           <c:if test="${resultMap.applicant_eight_proficient eq '一般'}">checked="checked"</c:if>
                                           name="applicant_eight_proficient" id="proficient_eight_common"/>
                                    <label for="proficient_eight_common">一般</label>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="9" style="text-align: left;">
                                    <span>主要工作简历</span>
                <textarea class="xltxtarea" name="applicant_eight_job" placeholder="主要工作简历"
                          style="width:98%;height: 150px;">${resultMap.applicant_eight_job}
                </textarea>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="9" style="text-align: left;">
                                    <span>正在承担的其他科研项目(请列明任务来源、课题名称、研究起止年月、本人在该课题中的任务和分工)</span>
                <textarea class="xltxtarea" name="applicant_eight_applyProj"
                          style="width:98%;height: 150px;">${resultMap.applicant_eight_applyProj}
                </textarea>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="9" style="text-align: left;">
                                    <span>以往研究工作成果(论文、著作目录及获学术奖励或已经研究开发的上市新药、获得的专利等情况)</span>
                                    <br/>
                <textarea class="xltxtarea" name="applicant_eight_relatedProj" placeholder="与本课题相关的研究成果"
                          style="width:98%;height: 150px;">${resultMap.applicant_eight_relatedProj}
                </textarea>
                                    <br/>
                <textarea class="xltxtarea" name="applicant_eight_otherProj" placeholder="其他领域的研究成果"
                          style="width:98%;height: 150px;">${resultMap.applicant_eight_otherProj}
                </textarea>
                                </td>
                            </tr>
                        </table>
                    </div>

                    <div id="applicant_nine" style="display:none">
                        <table class="basic" style="width: 100%;margin-top: 10px;">

                            <tr>
                                <th style="text-align: center;">第九申请人</th>
                                <td style="text-align: center;">姓名：</td>
                                <td style="text-align: left;">
                                    <input type="text" class="inputText " name="applicant_nine_name"
                                           value="${resultMap.applicant_nine_name}" style="width: 80%"/>
                                </td>
                                <td style="text-align: center;">性别：</td>
                                <td style="text-align: left;">
                                    <input type="text" class="inputText " name="applicant_nine_sexId"
                                           value="${resultMap.applicant_nine_sexId}" style="width: 80%"/>
                                </td>
                                <td style="text-align: center;">职称/职务：</td>
                                <td style="text-align: left;">
                                    <input type="text" class="inputText " name="applicant_nine_Title"
                                           value="${resultMap.applicant_nine_Title}" style="width: 80%"/>
                                </td>
                                <td style="text-align: center;">联系电话：</td>
                                <td style="text-align: left;">
                                    <input type="text" class="inputText " name="applicant_nine_phone"
                                           value="${resultMap.applicant_nine_phone}" style="width: 80%"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: center;">所承担的任务：</td>
                                <td style="text-align: left;" colspan="6">
                                    <input type="text" class="inputText " name="applicant_nine_task"
                                           value="${resultMap.applicant_nine_task}" style="width: 80%"/>
                                </td>
                                <td style="text-align: center;">电子信箱：</td>
                                <td style="text-align: left;">
                                    <input type="text" class="inputText " name="applicant_nine_mail"
                                           value="${resultMap.applicant_nine_mail}" style="width: 80%"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: center;">外语语种：</td>
                                <td style="text-align: left;" colspan="2">
                                    <input type="text" class="inputText "
                                           name="applicant_nine_language" value="${resultMap.applicant_nine_language}"
                                           style="width: 80%"/>
                                </td>
                                <td style="text-align: center;">熟练程度：</td>
                                <td style="text-align: left;" colspan="5">
                                    <input type="radio" value="精通"
                                           <c:if test="${resultMap.applicant_nine_proficient eq '精通'}">checked="checked"</c:if>
                                           name="applicant_nine_proficient" id="proficient_nine_master"/>
                                    <label for="proficient_nine_master">精通</label>&#12288;
                                    <input type="radio" value="熟练"
                                           <c:if test="${resultMap.applicant_nine_proficient eq '熟练'}">checked="checked"</c:if>
                                           name="applicant_nine_proficient" id="proficient_nine_skilled"/>
                                    <label for="proficient_nine_skilled">熟练</label>&#12288;
                                    <input type="radio" value="一般"
                                           <c:if test="${resultMap.applicant_nine_proficient eq '一般'}">checked="checked"</c:if>
                                           name="applicant_nine_proficient" id="proficient_nine_common"/>
                                    <label for="proficient_nine_common">一般</label>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="9" style="text-align: left;">
                                    <span>主要工作简历</span>
                <textarea class="xltxtarea" name="applicant_nine_job"
                          style="width:98%;height: 150px;">${resultMap.applicant_nine_job}
                </textarea>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="9" style="text-align: left;">
                                    <span>正在承担的其他科研项目(请列明任务来源、课题名称、研究起止年月、本人在该课题中的任务和分工)</span>
                <textarea class="xltxtarea" name="applicant_nine_applyProj"
                          style="width:98%;height: 150px;">${resultMap.applicant_nine_applyProj}
                </textarea>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="9" style="text-align: left;">
                                    <span>以往研究工作成果(论文、著作目录及获学术奖励或已经研究开发的上市新药、获得的专利等情况)</span>
                                    <br/>
                <textarea class="xltxtarea" name="applicant_nine_relatedProj" placeholder="与本课题相关的研究成果"
                          style="width:98%;height: 150px;">${resultMap.applicant_nine_relatedProj}
                </textarea>
                                    <br/>
                <textarea class="xltxtarea" name="applicant_nine_otherProj" placeholder="其他领域的研究成果"
                          style="width:98%;height: 150px;">${resultMap.applicant_nine_otherProj}
                </textarea>
                                </td>
                            </tr>
                        </table>
                    </div>
            </div>
            <c:if test="${param.view!=GlobalConstant.FLAG_Y}">
                <div align="center" style="margin-top: 10px">
                    <input id="prev" type="button" onclick="nextOpt('step2')" class="search" value="上一步"/>
                    <input id="nxt" type="button" onclick="nextOpt('step4')" class="search" value="下一步"/>
                </div>
            </c:if>
        </div>
    </div>
</div>
</div>
</body>
</html>