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

        .noteItems p {
            white-space: nowrap; /* 强制在同一行内显示所有文本，直到文本结束或者遭遇 br 对象。不换行 */
            overflow: hidden; /* 内容超出宽度时隐藏超出部分的内容 */
            text-overflow: ellipsis; /* IE 专有属性，当对象内文本溢出时显示省略标记(...) ；需与overflow:hidden;一起使用。*/
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
    <c:if test="${not ((noteInfoWithBLOBs.auditStatusId eq 'Apply' or noteInfoWithBLOBs.auditStatusId eq 'UnQualified') or (empty noteInfoWithBLOBs))}">
        <script>
            $(function () {
                $('#noteInfos input').attr("readonly", "readonly");
                //$('textarea').attr("readonly", "readonly");
                // $("select").attr("disabled", "disabled");
                $("#noteInfos :checkbox").attr("disabled", "disabled");
                $("#noteInfos :radio").attr("disabled", "disabled");
                $("#noteInfos .ctime").removeAttr("onclick");
            });
        </script>
    </c:if>
    <script type="text/javascript">
        function shouInfo(recordFlow, doctorFlow) {
            var scope = "${scope}";
            var roleScope = "${roleScope}";
            location.href = "<s:url value="/res/discipleNote/showDiscipleNoteInfo/"/>" + roleScope + "/" + scope + "?doctorFlow=" + doctorFlow + "&operaFlag=${operaFlag}" + "&recordFlow=" + recordFlow;
        }
        function editNotes() {
            $("#notice").hide();
            $("#delBtn").hide();
            $("#operateBtn").show();
            $("#operateBtn p").show();
            $("#editTip").hide();
            $("#canEditAppendix").hide();
            $("#noteInfoForm").empty();
            $("#noteInfoForm").html($("#addNoteInfos").html());
            $("#teacherNameAdd").attr("readonly", "readonly");
        }
        $(function () {
            //鼠标的移入移出
            $(".noteItems").mouseover(function () {
                $(this).addClass("xuanze");
            }).mouseout(function () {
                $(this).removeClass("xuanze");
            });
        });

        function saveNotes(scope,editAppendixFlag) {
            var jsonData={};
            var fileFlows=[];
            $("input[name='fileFlows']").each(function(){
                var fileFlow=$(this).val();
                if(fileFlow)
                    fileFlows.push(fileFlow);
            });
            jsonData.fileFlows=fileFlows;
            if($("#noteInfoForm").validationEngine("validate")){
                var url = "<s:url value="/res/discipleNote/saveDiscipleNoteInfo/doctor/"/>" + scope + "?editAppendixFlag="+editAppendixFlag+"&jsonData="+encodeURI(encodeURI(JSON.stringify(jsonData)));
                jboxSubmit($("#noteInfoForm"), url, function (resp) {
                    //   search();
                    window.location.reload();
                }, null, true);
            }
        }

        function deleteNotes(recordFlow) {
            jboxConfirm("确认删除该条心得?", function () {
                var requestData = {"recordFlow": recordFlow};
                var url = "<s:url value='/res/discipleNote/delDiscipleNoteInfo' />";
                jboxStartLoading();
                jboxPost(url, requestData, function (resp) {
                    if (resp == "${GlobalConstant.DELETE_SUCCESSED}") {
                        search();
                        // window.location.reload();
                    }
                }, null, true);
            });
        }

        function submitNotes(flag) {
            if (false == $("#noteInfoForm").validationEngine("validate")) {
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
            if ("${roleScope}" == "disciple") {
                var tent = $("#auditContent").val();
                if (!tent) {
                    jboxTip("审核意见未填写，无法保存！");
                    return false;
                }
            }
            jboxConfirm("提交后将无法修改，确认操作？", function () {
                var noteInfoForm = $('#noteInfoForm');
                var url = "<s:url value="/res/discipleNote/saveDiscipleNoteInfo/${roleScope}/${scope}?flag="/>" + flag + "&jsonData="+encodeURI(encodeURI(JSON.stringify(jsonData)));
                jboxStartLoading();
                jboxSubmit($("#noteInfoForm"), url, function (resp) {
                    // search();
                    window.location.reload();
                }, null, true);
            });
        }

        function search() {
            jboxStartLoading();
            $("#searchForm").submit();
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
        function agreeRecordBatch(exp){
            var url = "<s:url value='/res/discipleNote/agreeRecordBatch/${roleScope}/${scope}?doctorFlow=${doctorFlow}'/>";
            jboxConfirm("确认审核通过该学员所有待审核？" , function() {
                jboxPost(url,null,function(resp){
                    jboxTip(resp);
                    setTimeout(function(){
                        location.reload();
                    },1000);
                }, null, false);
            });
        }
        function exportDocx(scope,flow) {
            if(!flow){
                jboxTip("无数据");
                return;
            }
            var url = '<s:url value="/res/discipleNote/exportDoc"/>?recordFlow='+flow+'&scope='+scope;
            jboxTip("打印中,请稍等...");
            window.location.href = url;
        }
    </script>
</head>
<body>
<c:set var="canEditAppendix" value="${  noteInfoWithBLOBs.auditStatusId eq 'Qualified' }"/>
<div style="display: none">
    <form id="searchForm" action="<s:url value="/res/discipleNote/showDiscipleNoteInfo/${roleScope}/${scope}"/>"
          method="post">
        <input type="hidden" name="doctorFlow" value="${resDoctor.doctorFlow}"/>
        <input type="hidden" name="discipleTeacherFlow" value="${resDoctor.discipleTeacherFlow}"/>
    </form>
</div>
<div class="mainright">
    <div class="content">
        <div style="margin-top: 10px;width: 100%;">
            <div style="width: 28%;margin-bottom: 40px; float: left">
                <div class="index_title" style="height: 50px;width: 100%;">
                    <p>
                        <span style="float: left;margin-top: 10px;">跟师学习笔记:</span>
                        <c:if test="${roleScope eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR}">
                            <input class="btn_blue" style="float: right" type="button" value="新建笔记"
                                   onclick="editNotes()"/>
                        </c:if>
                    </p>
                </div>
                <div style="height: 800px;width: 100%;margin-bottom: 20px;border:1px solid #D0E3F2;overflow:auto;">
                    <c:if test="${empty resDiscipleNoteInfoList}">
                        <p id="" style="margin-top: 30px;color: #BABABA;font-size:18px;">
                            <c:if test="${roleScope eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR}">
                                &#12288;&#12288;还没有创建任何学习笔记哦
                            </c:if>
                            <c:if test="${roleScope eq GlobalConstant.RES_ROLE_SCOPE_DISCIPLE}">
                                &#12288;&#12288;暂无待审核学习笔记
                            </c:if>
                            <c:if test="${roleScope eq GlobalConstant.RES_ROLE_SCOPE_ADMIN}">
                                &#12288;&#12288;暂无待审核学习笔记
                            </c:if>
                        </p>
                    </c:if>
                    <c:forEach items="${resDiscipleNoteInfoList}" var="noteInfo">
                        <c:set var="statusColor" value=""/>
                        <c:if test="${(noteInfo.auditStatusId eq 'Apply' or noteInfoWithBLOBs.auditStatusId eq 'UnQualified') or (noteInfo.auditStatusId eq 'Submit')}">
                            <c:set var="statusColor" value="#428BFF"/>
                        </c:if>
                        <c:if test="${noteInfo.auditStatusId eq 'Qualified'}">
                            <c:set var="statusColor" value="#00CC33"/>
                        </c:if>
                        <c:if test="${noteInfo.auditStatusId eq 'UnQualified'}">
                            <c:set var="statusColor" value="#FF2F5D"/>
                        </c:if>
                        <div class="noteItems" style="width: 100%;padding-bottom: 10px;padding-top: 10px;<c:if
                                test="${noteInfoWithBLOBs.recordFlow eq noteInfo.recordFlow or (param.recordFlow eq noteInfo.recordFlow)}">background: #D0E3F2;</c:if>"
                             onclick="shouInfo('${noteInfo.recordFlow}','${noteInfo.doctorFlow}')">
                            <p><img src="">跟师学习时间：<span name="studyStartDate">${noteInfo.studyStartDate}</span>&#12288;&#12288;<span
                            >${noteInfo.studyTimeNmae}</span></p>
                            <p>创建时间：<span name="createTime">${pdfn:transDateTime(noteInfo.createTime)}</span>
                                <br/>状态：
                                <span title="${noteInfo.auditStatusName}"
                                      style="color: ${statusColor}">${noteInfo.auditStatusName}</span></p>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <c:set var="showBtn" value="true"/>

            <div style="width: 65%;margin-left: 20px;margin-bottom: 40px; float: left">
                <div style="height: 50px;width: 100%;position: relative;">
                    <c:if test="${roleScope eq GlobalConstant.RES_ROLE_SCOPE_DISCIPLE or roleScope eq GlobalConstant.RES_ROLE_SCOPE_ADMIN}">
                        <div>
                            <span style="float: right;"><input type="button" value="一键审核" class="search" onclick="agreeRecordBatch();"/>
                            <input class="search" type="button" onclick="exportDocx('${scope}','${noteInfoWithBLOBs.recordFlow}')"
                                   value="打&#12288;印"/>
                            </span>
                        </div>
                    </c:if>
                    <div id="operateBtn"
                         style="float: left;<c:if test="${empty resDiscipleNoteInfoList}">display: none;</c:if>">
                        <c:if test="${(roleScope eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR)}">
                            <p style="<c:if
                                    test="${not (noteInfoWithBLOBs.auditStatusId eq 'Apply' or noteInfoWithBLOBs.auditStatusId eq 'UnQualified' )}">display: none;</c:if>">
                                &#12288;<label id="delBtn"><input class="btn_blue" type="button"
                                       onclick="deleteNotes('${noteInfoWithBLOBs.recordFlow}')"
                                       value="删&#12288;除"/> &#12288;&#12288;</label>
                                <input class="btn_blue" type="button" onclick="saveNotes('${scope}')"
                                       value="保&#12288;存"/>&#12288;&#12288;
                                <input class="btn_blue" onclick="submitNotes('Y')" type="button" value="提&#12288;交"/>
                                （提交后指导老师才可审核）
                            </p>
                            <c:if test="${canEditAppendix}">
                                <p style="">
                                    <input  id="canEditAppendix" class="btn_blue" type="button" onclick="saveNotes('${scope}','Y')"
                                           value="保&#12288;存"/>&#12288;&#12288;
                                </p>
                            </c:if>
                            <p style="position: absolute;top: 0;right: 0;">
                                <input class="btn_blue" type="button" onclick="exportDocx('${scope}','${noteInfoWithBLOBs.recordFlow}')"
                                       value="打&#12288;印"/>
                            </p>
                            <p id="editTip" style="padding-top: 3px;" class="red" style="<c:if test="${noteInfoWithBLOBs.auditStatusId eq 'Apply'}">display: none;</c:if>">
                                （${(empty sysCfgMap['zy_gsxxbj_top'])?'每周完成1篇以上跟师笔记':sysCfgMap['zy_gsxxbj_top']}）
                            </p>
                        </c:if>
                        <c:if test="${(roleScope eq GlobalConstant.RES_ROLE_SCOPE_DISCIPLE) and (noteInfoWithBLOBs.auditStatusId eq 'Submit') and (operaFlag eq 'Y')}">
                            <input class="btn_blue" type="button" onclick="submitNotes('Y')" value=" 审核通过 "/>&#12288;&#12288;
                            <input class="btn_blue" onclick="submitNotes('N')" type="button" value="审核不通过"/>
                        </c:if>
                    </div>
                    <c:if test="${empty resDiscipleNoteInfoList}">
                        <p class="red" style="padding-top: 3px;">
                            （${(empty sysCfgMap['zy_gsxxbj_top'])?'每周完成1篇以上跟师笔记':sysCfgMap['zy_gsxxbj_top']}）
                        </p>
                    </c:if>
                </div>
                <div style="height: 800px;width: 100%;padding-bottom: 20px;border:1px solid #D0E3F2;overflow:auto;">
                    <c:if test="${empty resDiscipleNoteInfoList}">
                        <div id="notice" style="margin-top: 30px;color: #BABABA;font-size:18px;">
                            <c:if test="${roleScope eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR}">
                                &#12288;&#12288;还没有创建任何学习笔记哦
                            </c:if>
                            <c:if test="${roleScope eq GlobalConstant.RES_ROLE_SCOPE_DISCIPLE}">
                                &#12288;&#12288;暂无待审核学习笔记
                            </c:if>
                            <c:if test="${roleScope eq GlobalConstant.RES_ROLE_SCOPE_ADMIN}">
                                &#12288;&#12288;暂无待审核学习笔记
                            </c:if>
                        </div>
                    </c:if>
                    <form id="noteInfoForm" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="recordFlow" value="${noteInfoWithBLOBs.recordFlow}"/>
                        <div id="noteInfos"
                             style="<c:if
                                     test="${empty resDiscipleNoteInfoList or ((empty noteInfoWithBLOBs) and (roleScope eq GlobalConstant.RES_ROLE_SCOPE_DISCIPLE))}">display: none;</c:if>margin-bottom: 20px">
                            <div style="margin-bottom: 10px">
                                <p style="margin:20px auto auto 30px">
                                    <span>跟师学习时间：</span>
                                    <input type="text" name="studyStartDate" value="${noteInfoWithBLOBs.studyStartDate}"
                                           class="inputText ctime validate[required]"
                                           onClick="WdatePicker({dateFmt:'yyyy年MM月dd日'})"
                                           readonly="readonly"/>
                                    &#12288;<input type="radio" name="studyTimeId" id="am" value="am" class="validate[required]"
                                                   <c:if test="${noteInfoWithBLOBs.studyTimeId eq 'am'}">checked="checked"</c:if> /><label
                                        for="am">上午</label>
                                    &#12288;<input type="radio" name="studyTimeId" id="pm" value="pm" class="validate[required]"
                                                   <c:if test="${noteInfoWithBLOBs.studyTimeId eq 'pm'}">checked="checked"</c:if> /><label
                                        for="pm">下午</label>
                                    &#12288;<input type="radio" name="studyTimeId" id="allDay" value="allDay" class="validate[required]"
                                                   <c:if test="${noteInfoWithBLOBs.studyTimeId eq 'allDay'}">checked="checked"</c:if> /><label
                                        for="allDay">全天</label>
                                </p>
                            </div>
                            <table class="basic" width="90%" style="margin-left: 30px">
                                <tr>
                                    <td>
                                        跟师规培学员姓名：
                                    </td>
                                    <td>
                                        <input class="inputText validate[required]" name="doctorName"
                                               value="${noteInfoWithBLOBs.doctorName}<c:if test="${empty noteInfoWithBLOBs.doctorName}">${resDoctor.doctorName}</c:if>"/>
                                        <input type="hidden" name="doctorFlow"
                                               value="${noteInfoWithBLOBs.doctorFlow}<c:if test="${empty noteInfoWithBLOBs.doctorFlow}">${resDoctor.doctorFlow}</c:if>"/>
                                    </td>
                                    <td>
                                        师承指导老师姓名：
                                    </td>
                                    <td>
                                        <input class="inputText validate[required]" name="teacherName" readonly="readonly"
                                               value="${noteInfoWithBLOBs.teacherName}<c:if test="${empty noteInfoWithBLOBs.teacherName}">${resDoctor.discipleTeacherName}</c:if>"/>
                                        <input type="hidden" name="teacherFlow"
                                               value="${noteInfoWithBLOBs.teacherFlow}<c:if test="${empty noteInfoWithBLOBs.teacherFlow}">${resDoctor.discipleTeacherFlow}</c:if>"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="4">
                                <textarea class="xltxtarea validate[required]"
                                          <c:if test="${not ((noteInfoWithBLOBs.auditStatusId eq 'Apply'  or noteInfoWithBLOBs.auditStatusId eq 'UnQualified') or (empty noteInfoWithBLOBs))}">readonly="readonly"</c:if>
                                          placeholder="本次跟师主要情况（诊疗人次、主要病种等）、典型病例摘录、指导老师指导意见、个人心得体会等"
                                          style="height: 250px"
                                          name="experienceContent">${noteInfoWithBLOBs.experienceContent}</textarea>
                                        <p style="float: right;margin-right: 30px;text-align: right">
                                            <span>跟师规培学员签名：</span><input class="inputText"
                                                                         value="${noteInfoWithBLOBs.doctorName}<c:if test="${empty noteInfoWithBLOBs.doctorName}">${resDoctor.doctorName}</c:if>"/><br/>

                                            <input type="text" name="studentSignTime" class="inputText"
                                                   value="${pdfn:transDate(noteInfoWithBLOBs.studentSignTime)}"
                                                   readonly="readonly"/>
                                        </p>
                                        <c:if test="${noteInfoWithBLOBs.auditStatusId eq 'Apply' or noteInfoWithBLOBs.auditStatusId eq 'UnQualified' or canEditAppendix}">
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
                                        <c:if test="${noteInfoWithBLOBs.auditStatusId ne 'Apply' and not empty discipleFiles and !canEditAppendix}">
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
                                        师承指导老师批阅意见：
                                    <textarea id="auditContent"
                                            <c:if test="${(noteInfoWithBLOBs.auditStatusId != 'Submit') or (not (roleScope eq GlobalConstant.RES_ROLE_SCOPE_DISCIPLE))}">readonly="readonly"</c:if>
                                            class="xltxtarea"
                                            style="height: 250px" name="auditContent"
                                    >${noteInfoWithBLOBs.auditContent}</textarea>
                                        <p style="float: right;margin-right: 30px;margin-bottom: 15px;text-align: right">
                                            <span>师承签名：</span>
                                            <c:if test="${((noteInfoWithBLOBs.auditStatusId != 'Submit') and(noteInfoWithBLOBs.auditStatusId != 'Apply') and (not empty noteInfoWithBLOBs.auditStatusId )) or (roleScope eq GlobalConstant.RES_ROLE_SCOPE_DISCIPLE)}">
                                                <c:set var="teaFlow" value="${noteInfoWithBLOBs.teacherFlow}"/>
                                                ${pdfn:getSingnPhoto(teaFlow)}
                                            </c:if>
                                            <c:if test="${!(((noteInfoWithBLOBs.auditStatusId != 'Submit') and(noteInfoWithBLOBs.auditStatusId != 'Apply') and (not empty noteInfoWithBLOBs.auditStatusId )) or (roleScope eq GlobalConstant.RES_ROLE_SCOPE_DISCIPLE))}">
                                                <input class="inputText"  readonly="readonly"/>
                                            </c:if>
                                            <br/>

                                            <c:if test="${((noteInfoWithBLOBs.auditStatusId != 'Submit') and(noteInfoWithBLOBs.auditStatusId != 'Apply') and (not empty noteInfoWithBLOBs.auditStatusId )) or (roleScope eq GlobalConstant.RES_ROLE_SCOPE_DISCIPLE)}">
                                                <c:set var="teaFlow" value="${noteInfoWithBLOBs.teacherFlow}"/>
                                                <input type="text" class="inputText"
                                                       name="auditTime"
                                                       value="${ empty pdfn:transDate(noteInfoWithBLOBs.auditTime) ? pdfn:getCurrDate() : pdfn:transDate(noteInfoWithBLOBs.auditTime)}"
                                                       readonly="readonly"/>&nbsp;
                                            </c:if>
                                            <c:if test="${!(((noteInfoWithBLOBs.auditStatusId != 'Submit') and(noteInfoWithBLOBs.auditStatusId != 'Apply') and (not empty noteInfoWithBLOBs.auditStatusId )) or (roleScope eq GlobalConstant.RES_ROLE_SCOPE_DISCIPLE))}">
                                                <input class="inputText"  readonly="readonly"/>&nbsp;
                                            </c:if>
                                        </p>
                                    </td>
                                </tr>
                            </table>
                            <span style="color: #FF0000;margin-left: 30px;">${(empty sysCfgMap['zy_gsxxbj_buttom'])?'注：本日期需与跟师记录相对应':sysCfgMap['zy_gsxxbj_buttom']}</span>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div style="display: none;">
        <div id="addNoteInfos" style="margin-bottom: 20px">
            <div style="margin-bottom: 10px">
                <p style="margin:20px auto auto 30px">
                    <span>跟师学习时间：</span>
                    <input type="text" name="studyStartDate" class="inputText ctime validate[required]"
                           onClick="WdatePicker({dateFmt:'yyyy年MM月dd日'})" readonly="readonly"/>
                    &#12288;<input type="radio" name="studyTimeId" id="am" value="am" class="validate[required]"/><label
                        for="am">上午</label>
                    &#12288;<input type="radio" name="studyTimeId" id="pm" value="pm" class="validate[required]"/><label
                        for="pm">下午</label>
                    &#12288;<input type="radio" name="studyTimeId" id="allDay" value="allDay" class="validate[required]"/><label
                        for="allDay">全天</label>
                </p>
            </div>
            <table class="basic" width="90%" style="margin-left: 30px">
                <tr>
                    <td>
                        跟师规培学员姓名：
                    </td>
                    <td>
                        <input class="inputText validate[required]" name="doctorName"
                               value="${resDoctor.doctorName}"/>
                        <input type="hidden" name="doctorFlow" value="${resDoctor.doctorFlow}"/>
                    </td>
                    <td>
                        师承指导老师姓名：
                    </td>
                    <td>
                        <input class="inputText validate[required]" name="teacherName" id="teacherNameAdd"
                               value="${resDoctor.discipleTeacherName}"/>
                        <input type="hidden" name="teacherFlow" value="${resDoctor.discipleTeacherFlow}"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="4">
                                <textarea class="xltxtarea validate[required]"
                                          placeholder="本次跟师主要情况（诊疗人次、主要病种等）、典型病例摘录、指导老师指导意见、个人心得体会等"
                                          style="height: 250px"
                                          name="experienceContent"></textarea>
                        <p style="float: right;margin-right: 30px;margin-bottom: 15px;text-align: right">
                            <span>跟师规培学员签名：</span><input class="inputText" value="${resDoctor.doctorName}"/><br/>
                            <input type="text" name="studentSignTime" class="inputText" title="获取当前系统时间，不可修改"
                                   readonly="readonly"/>
                        </p>
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
                        </table>
                    </td>
                </tr>
                <tr>
                    <td colspan="4">
                        师承指导老师批阅意见：
                                    <textarea readonly="readonly" id="auditContent"
                                              class="xltxtarea" style="height: 250px" name="auditContent"></textarea>
                        <p style="float: right;margin-right: 30px;margin-bottom: 15px;text-align: right">
                            <span>师承签名：</span><input class="inputText" readonly="readonly"/><br/>
                            <input type="text" name="auditTime" class="inputText"
                                   readonly="readonly"/>
                        </p>
                    </td>
                </tr>
            </table>
            <span style="color: #FF0000;margin-left: 30px;">${(empty sysCfgMap['zy_gsxxbj_buttom'])?'注：本日期需与跟师记录相对应':sysCfgMap['zy_gsxxbj_buttom']}</span>
        </div>
    </div>
</div>
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