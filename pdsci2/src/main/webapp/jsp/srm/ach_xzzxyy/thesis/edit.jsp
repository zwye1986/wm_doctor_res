<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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
    </jsp:include>
    <script type="text/javascript"
            src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript">

        function saveThesis() {
            if (false == $("#srmThesis").validationEngine("validate")) {
                return false;
            }
            if (false == $("#list").validationEngine("validate")) {
                return false;
            }
            if(thesisIsExist){
                return false;
            }
            /*$("#mubiao input[name='isCorresponding']").each(function () {
             if (this.checked == true) {
             this.value = 1;
             } else {
             this.value = 0;
             }
             });*/
            var thesis = $("#srmThesis").serializeJson();
           // var achFile = $("#srmAchFile").serializeJson();
            var authTab = $('#authorList');
            var trs = authTab.children();
            var datas = [];
            $.each(trs, function (i, n) {
                var authorFlow = $(n).find("input[name='authorFlow']").val();
                var typeId = $(n).find("select[name='typeId']").val();
                //var isCorresponding = $(n).find("input[name='isCorresponding']").val();
                var authorName = $(n).find("input[name='authorName']").val();
                var sexId = $(n).find("select[name='sexId']").val();
                var educationId = $(n).find("select[name='educationId']").val();
                var titleId = $(n).find("select[name='titleId']").val();
                var degreeId = $(n).find("select[name='degreeId']").val();
                var userFlow = $(n).find("input[name='userFlow']").val();
                var deptFlow = $(n).find("input[name='deptFlow']").val();
                var deptName = $(n).find("input[name='deptName']").val();
                var data = {
                    "authorFlow": authorFlow,
                    "typeId": typeId,
                    "authorName": authorName,
                    "sexId": sexId,
                    "educationId": educationId,
                    "titleId": titleId,
                    "userFlow": userFlow,
                    "deptFlow": deptFlow,
                    "deptName": deptName,
                    "degreeId": degreeId
                };
                datas.push(data);
            });

            var fileDatas = [];
            var fileFlow = $("#srmAchFile").val();
                var pubFlie = {
                    "fileFlow": fileFlow
                };
                fileDatas.push(pubFlie);
            var t = {'authorList': datas, 'thesis': thesis, "srmAchFileList": fileDatas};
            //var temp = {'jsondata':JSON.stringify(t)};
            $('#jsondata').val(JSON.stringify(t));
            var url = "<s:url value='/srm/ach/thesis/save/${roleScope}'/>";
            jboxStartLoading();
            jboxSubmit($('#srmThesis'), url, function (resp) {

                    },
                    function (resp) {
                        alert("error");
                    }, false);
            return true;
        }
        function saveThe() {
            if (saveThesis()) {
                jboxTip("保存成功");
                setTimeout("window.parent.frames['mainIframe'].location.reload(true);jboxClose();", 2000)
            }
        }
        function delAuthorTr(obj) {
            jboxConfirm("确认删除？", function () {
                var tr = obj.parentNode.parentNode;
                tr.remove();
            });
        }

        function delAuthor(authorFlow, obj) {
            var url = '<s:url value="/srm/ach/thesis/deleteAuthor?authorFlow="/>' + authorFlow;
            jboxConfirm("确认删除？", function () {
                jboxGet(url, null, function () {
                    var tr = $(obj).parent("td").parent("tr");
                    tr.remove();
                }, null, true);
            });
        }

        function change(obj) {
            //$(".a").attr("disabled", true);
            $(".a").attr("checked",false);
            $("#meeting").hide();
            $("#notMeeting").show();
            if (obj.value == "2") {
               // $(".a").attr("disabled", false);
                $("#meeting").show();
                $("#notMeeting").hide();
            }
        }

        function reCheck(obj) {
            $(obj).hide();
            $("#down").hide();
            $("#file").show();
        }

        $(function () {
            <c:if test="${param.editFlag == GlobalConstant.FLAG_N}">
            $('#look input[type!=button]').attr('disabled', "disabled");
            $('#look textarea').attr('readonly', "readonly");
            $('#look select').attr('disabled', "disabled");
            $("#reCheck").css("display", "none");
            </c:if>
        });

        //页数范围
        $(document).ready(function () {
            var page = $("#pageNoRange").val();
            if (page == null || page == "" || page == undefined) {
                return false;
            }
            var pages = page.split("－");
            $("#startPage").val(pages[0]);
            $("#endPage").val(pages[1]);
        });

        function buildPage(){
            var startPage = $("#startPage").val();
            var endPage = $("#endPage").val();
            $("#pageNoRange").val(startPage+"－"+endPage);
        }

        function strChar(obj, limitLength) {
            if ($("#summary").validationEngine("validate")) {
                return false;
            }
            var str = $(obj).val();
            var len = 0;
            for (var i = 0; i < str.length; i++) {
                if (str[i].match(/[^\x00-\xff]/ig) != null) {  //全角
                    len += 2;
                }
                else
                    len += 1;
            }
            if (len > parseInt(limitLength)) {
                jboxTip("当前字符超过限制");
                $(obj).val("");
            }
        }
        $(function () {
            var url = "<s:url value='/srm/ach/topic/searchDept'/>";
            var selectedFlow = "${thesis.deptFlow}";
            <c:if test="${param.editFlag != 'audit'}">
                if(null == selectedFlow || selectedFlow == ""){
                    selectedFlow = "${sessionScope.currUser.deptFlow}"
                }
            </c:if>
            jboxPost(url, null, function (resp) {
                $.each(resp, function (i, n) {
                    if (selectedFlow == n.deptFlow) {
                        $("#selectDept").append("<option selected='selected' value='" + n.deptFlow + "'>" + n.deptName + "</option>");
                        $("#deptName").val(n.deptName);
                    } else {
                        $("#selectDept").append("<option  value='" + n.deptFlow + "'>" + n.deptName + "</option>");
                    }
                });
            }, null, false);
        });
        function getDeptName(obj) {
            $("#deptName").val($(obj).find("option:selected").text());
        }
        var dg;
        function selectAuthor() {
            dg = dialog({
                id: 'openDialog',
                fixed: true,
                padding: 5,
                title: "人员选择",
                url: "<s:url value='/srm/ach/manager/list'/>",
                width: 600,
                height: 500,
                cancelDisplay: false,
                cancelValue: '关闭',
                backdropOpacity: 0.1,
            });
            dg.showModal();
        }
        function closeEditPage(dates) {
            dg.close().remove();
            $.each(dates, function (i, n) {
                // alert(n.userName);
                var tr = $("#moban tr:eq(0)").clone();
                $(tr).find("input[name='authorName']").val(n.userName);
                //alert($(tr).find("input[name='authorName']").val());
                $(tr).find("select[name='sexId']").val(n.sexId);
                $(tr).find("select[name='titleId']").val(n.titleId);
                $(tr).find("select[name='degreeId']").val(n.degreeId);
                $(tr).find("select[name='educationId']").val(n.educationId);
                $(tr).find("input[name='userFlow']").val(n.userFlow);
                $(tr).find("input[name='deptFlow']").val(n.deptFlow);
                $(tr).find("input[name='deptName']").val(n.deptName);
               // alert( $(tr).find("input[name='deptName']").val());
                $('#authorList').append(tr);
            });

        }
        var thesisIsExist ;
        function validateThesisName(thesisName){
            thesisIsExist = false;
            $("#thesisMessage").text("");
            var changedName = $("#thesisName").val().trim();
            if(changedName == thesisName){
                return true;
            }else {
                var url = "<s:url value='/srm/ach/thesis/thesisIsExist'/>";
                var data = {"thesisName":changedName};
                jboxPost(url, data, function (resp) {
                    if(resp){
                        thesisIsExist = true;
                        $("#thesisMessage").text("该论文已存在，如需增加作者信息，请联系登记人'"+resp.applyUserName+"'！");
                    }
                }, null, false);
            }
        }
    </script>
</head>

<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <div id="look">
                <form id="srmThesis" action="<s:url value="/srm/ach/thesis/save"/>" method="post"
                      style="position: relative;" enctype="multipart/form-data">
                    <input id="jsondata" type="hidden" name="jsondata" value=""/>
                    <input type="hidden" name="thesisFlow" value="${thesis.thesisFlow}"/>
                    <c:if test="${(param.editFlag != GlobalConstant.FLAG_N) and (param.editFlag != 'audit')}">
                        <input type="hidden" name="applyUserFlow" value="${sessionScope.currUser.userFlow}"/>
                        <input type="hidden" name="applyUserName" value="${sessionScope.currUser.userName}"/>
                        <input type="hidden" name="applyOrgFlow" value="${sessionScope.currUser.orgFlow}"/>
                        <input type="hidden" name="applyOrgName" value="${sessionScope.currUser.orgName}"/>
                    </c:if>
                    <table class="basic" style="width: 100%">
                        <tr class="bs_name" style="height: 26px">
                            <td style="text-align:left" colspan="4">基本信息</td>
                        </tr>
                        <tr>
                            <th><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>论文题目：
                            </th>
                            <td colspan="3">
                                <input class="validate[required,maxSize[100]] name xltext" type="text" id="thesisName" name="thesisName"
                                       value="${thesis.thesisName}" onchange="validateThesisName('${thesis.thesisName}')" style="width: 97%;"/>
                                <span id="thesisMessage" style="color: #f00"></span>
                            </td>
                        </tr>
                        <tr>
                            <th width="20%"><span class="redspan"
                                                  style="color: red;padding: 0px;margin-left: 10px;">*</span>论文类型：
                            </th>
                            <td>
                                <c:forEach var="dict" items="${dictTypeEnumThesisTypeList}">
                                    <input type="radio" name="typeId" id="ThesisType_${dict.dictId}"
                                           value="${dict.dictId}" onclick="change(this);" class="validate[required]"
                                           <c:if test="${thesis.typeId==dict.dictId}">checked="checked"</c:if>/><label
                                        for="ThesisType_${dict.dictId}">&nbsp;${dict.dictName}</label>&nbsp;
                                </c:forEach>
                            </td>
                            <th>会议类型：</th>
                            <td>
                                <div id="meeting" style="<c:if test="${thesis.typeId ne '2'}">display: none</c:if>">
                                <c:forEach var="dict" items="${dictTypeEnumMeetingTypeList}">
                                    <input type="radio" class="a" name="meetingId" id="MeetingType_${dict.dictId}" value="${dict.dictId}"
                                           <c:if test="${thesis.meetingId==dict.dictId}">checked="checked"</c:if>/><label
                                        for="MeetingType_${dict.dictId}">&nbsp;${dict.dictName}</label>&nbsp;
                                </c:forEach>
                                </div>
                                <div id="notMeeting" style="<c:if test="${thesis.typeId eq '2'}">display: none</c:if>">
                                    <c:forEach var="dict" items="${dictTypeEnumMeetingTypeList}">
                                        <input type="radio" disabled="disabled"/><label>&nbsp;${dict.dictName}</label>&nbsp;
                                    </c:forEach>
                                </div>
                            </td>
                        </tr>

                        <tr>
                            <th><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>发表/出版年份：
                            </th>
                            <td>
                                <input class="validate[required] xltext ctime" style="width:160px;" type="text"
                                       name="publishDate"
                                       value="${thesis.publishDate}" onClick="WdatePicker({dateFmt:'yyyy'})"
                                       readonly="readonly"/>
                            </td>
                            <th><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>发表刊物/论文集：
                            </th>
                            <td>
                                <input class="validate[required] xltext" type="text" name="publishJour"
                                       value="${thesis.publishJour}">
                            </td>
                        </tr>
                        <tr>
                            <th><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>刊物类型：
                            </th>
                            <td colspan="3">
                                <c:if test="${not empty thesis}">
                                    <input type="hidden" id="jtn" value="${thesis.jourTypeId}">
                                </c:if>

                                <c:forEach items="${dictTypeEnumJournalTypeList}" var="dict">
                                    <input type="radio" class="validate[required] name" name="jourTypeId"
                                           <c:if test="${thesis.jourTypeId eq dict.dictId}">checked="checked"</c:if>
                                           id="JournalType_${dict.dictId}" value="${dict.dictId}"/><label
                                        for="JournalType_${dict.dictId}">&nbsp;${dict.dictName}</label>&nbsp;&nbsp;
                                </c:forEach>
                            </td>
                        </tr>
                        <tr>
                            <th><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>项目来源：
                            </th>
                            <td>
                                <select name="projSourceId" class="validate[required] xlselect">
                                    <option value="">请选择</option>
                                    <c:forEach items="${dictTypeEnumProjSourceList}" var="dict">
                                        <option value="${dict.dictId}"
                                                <c:if test="${thesis.projSourceId eq dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <th><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>所在科室：
                            </th>
                            <td>
                                <input type="hidden" id="deptName" name="deptName" value="${thesis.deptName}"/>
                                <select name="deptFlow" id="selectDept" class="validate[required] xlselect"
                                        onchange="getDeptName(this);">
                                    <option value="">请选择</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th>发表范围：</th>
                            <td>
                                <c:forEach items="${dictTypeEnumPublishScopeList}" var="dict">
                                    <input type="radio" name="publishScopeId" id="publishScope_${dict.dictId}"
                                           value="${dict.dictId}"
                                           <c:if test="${thesis.publishScopeId eq dict.dictId}">checked="checked"</c:if>/><label
                                        for="publishScope_${dict.dictId}">&nbsp;${dict.dictName}</label>&nbsp;
                                </c:forEach>
                            </td>
                            <th>学科门类：</th>
                            <td>
                                <c:forEach items="${dictTypeEnumSubjectTypeList}" var="dict">
                                    <input type="radio" name="subjectTypeId" id="subjectType_${dict.dictId}"
                                           value="${dict.dictId}"
                                           <c:if test="${thesis.subjectTypeId eq dict.dictId}">checked="checked"</c:if>/><label
                                        for="subjectType_${dict.dictId}">&nbsp;${dict.dictName}</label>&nbsp;
                                </c:forEach>
                            </td>
                        </tr>

                        <tr>
                            <th>是否为科技核心期刊：</th>
                            <td>
                                <input type="radio" name="isCoreJour" id="isCoreJour1"
                                       <c:if test="${thesis.isCoreJour eq 1}">checked="checked"</c:if> value="1"/><label
                                    for="isCoreJour1">&nbsp;是</label>&nbsp;
                                <input type="radio" name="isCoreJour" id="isCoreJour2"
                                       <c:if test="${thesis.isCoreJour eq 0}">checked="checked"</c:if> value="0"/><label
                                    for="isCoreJour2">&nbsp;否</label>&nbsp;
                            </td>
                            <th>是否为译文：</th>
                            <td>
                                <input type="radio" name="isTranslated" id="isTranslated1"  <c:if
                                        test="${thesis.isTranslated eq 1}"> checked="checked"</c:if> value="1"/> <label
                                    for="isTranslated1">&nbsp;是</label>&nbsp;
                                <input type="radio" name="isTranslated" id="isTranslated0" <c:if
                                        test="${thesis.isTranslated eq 0}"> checked="checked"</c:if> value="0"/><label
                                    for="isTranslated0">&nbsp;否</label>&nbsp;
                            </td>
                        </tr>
                        <tr>
                            <th><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>年，卷（期）：页码</th>
                            <td colspan="3"><input class="inputText validate[required,maxSize[4],custom[number]]" style="width: 50px;" type="text" name="jourYear"
                                                   value="${thesis.jourYear}">，
                                <input class="inputText validate[required,maxSize[6],custom[number]]" type="text" style="width: 50px;" name="volumeCode"
                                       value="${thesis.volumeCode}"/>
                                （<input class="inputText validate[required,maxSize[6],custom[number]]" type="text" style="width: 50px;" name="jourCode" value="${thesis.jourCode}"/>）：
                                <input class="inputText validate[required,maxSize[5],custom[number]]" type="text" style="width: 40px;" id="startPage" onchange="buildPage()" >－
                                <input class="inputText validate[required,maxSize[5],custom[number]]" type="text" style="width: 40px;" id="endPage" onchange="buildPage()">
                                <input id="pageNoRange" type="hidden" name="pageNoRange" value="${thesis.pageNoRange}">
                            </td>
                        </tr>
                        <tr>
                            <th>字数：</th>
                            <td colspan="3"><input class="xltext" type="text" name="wordCount" value="${thesis.wordCount}"
                                       style="margin-right: 0px;">万字
                            </td>
                        </tr>
                    </table>

                    <table class="basic" style="width: 100%">
                        <thead>
                        <tr>
                            <th colspan="2" class="bs_name">备注信息</th>
                        </tr>
                        </thead>
                        <tr>
                            <th width="20%">关键字：</th>
                            <td><input type="text" class="xltext validate[maxSize[200]]" style="width: 97%;" name="keyWord"
                                       placeholder="例如：关键字1 关键字2(200个字符以内)" value="${thesis.keyWord}" />
                            </td>
                        </tr>
                        <tr>
                            <th width="20%">论文摘要：</th>
                            <td><textarea class="xltxtarea validate[maxSize[1000]]" style="margin-left: 0px;" id="summary" name="summary"
                                          onchange="strChar(this,999)">${thesis.summary}</textarea></td>
                        </tr>
                        <tr>
                            <th width="20%">备注信息：</th>
                            <td><textarea class="xltxtarea validate[maxSize[200]]" style="margin-left: 0px;" placeholder="200个字符以内"
                                          name="remark">${thesis.remark}</textarea></td>
                        </tr>
                    </table>

                    <table class="basic" style="width: 100%">
                        <thead>
                        <tr>
                            <th colspan="4" class="bs_name">附件上传（需上传杂志封面，目录，以及内容的第一页）</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <th width="20%">附件：</th>
                            <td colspan="3">
                                <c:choose>
                                    <c:when test="${not empty file.fileName}">
                                        <a id="down"
                                           href='<s:url value="/srm/file/achDown?fileFlow=${file.fileFlow}"/>'>${file.fileName}</a>
                                        <input type="file" id="file" name="files" style="display: none;"/>
								<span style="float: right; padding-right: 10px;">
									<a id="reCheck" href="javascript:void(0);" onclick="reCheck(this);">[重新选择文件]</a>
								</span>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="file" id="file" name="files"/>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </form>
                <form id="list">
                    <table class="basic" id="mubiao" style="width: 100%">
                        <tr>
                            <th colspan="8" class="bs_name"> 论文作者<c:if
                                    test="${param.editFlag != GlobalConstant.FLAG_N}">
                                <a href="javascript:void(0)"><img
                                        src="<s:url value='/'/>css/skin/${skinPath}/images/add.png"
                                        onclick="selectAuthor('${thesis.thesisFlow }');" title="添加"/></a></c:if></th>
                        </tr>
                        <tr>
                            <th style="text-align: center; width: 20%;">作者类型</th>
                            <th style="text-align: center; width: 15%;">作者姓名</th>
                            <th style="text-align: center; width: 10%;">性别</th>
                            <th style="text-align: center; width: 10%;">学历</th>
                            <th style="text-align: center; width: 10%;">学位</th>
                            <th style="text-align: center; width: 15%;">职称</th>
                            <c:if test="${param.editFlag != GlobalConstant.FLAG_N}">
                                <th style="text-align: center; width: 10%;">操作</th>
                            </c:if>
                        </tr>
                        <tbody id="authorList">
                        <%-- 默认作者 --%>
                        <c:if test="${empty param.thesisFlow}">
                            <tr>
                                <td>
                                    <select name="typeId" class="xlselect" style="width: 85%;">
                                        <option value="">请选择</option>
                                        <c:forEach var="dict" items="${dictTypeEnumAuthorTypeList }">
                                            <option value="${dict.dictId }">${dict.dictName}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                    <%-- <td style="text-align: center;">
                                         <input type="checkbox" name="isCorresponding" id="${dict.dictId}" value="0"
                                                <c:if test="${author.isCorresponding eq 1}">checked="checked"</c:if>
                                                onclick="check(this)"/>
                                     </td>--%>
                                <td>
                                    <input class="xltext" style="width: 85%;" type="text" name="authorName"
                                           readonly="readonly" value="${sessionScope.currUser.userName}"/>
                                    <input type="hidden" name="userFlow" value="${sessionScope.currUser.userFlow}"/>
                                    <input type="hidden" name="deptFlow" value="${sessionScope.currUser.deptFlow}"/>
                                    <input type="hidden" name="deptName" value="${sessionScope.currUser.deptName}"/>
                                </td>
                                <td>
                                    <select name="sexId" class="validate[required] xlselect" style="width: 85%;">
                                        <option value="">请选择</option>
                                        <c:forEach var="sex" items="${userSexEnumList}">
                                            <c:if test="${sex.id != userSexEnumUnknown.id}">
                                                <option value="${sex.id}"
                                                        <c:if test="${sessionScope.currUser.sexId eq sex.id}">selected="selected"</c:if>>${sex.name}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <select name="educationId" class="xlselect" style="width: 85%;">
                                        <option value="">请选择</option>
                                        <c:forEach var="dict" items="${dictTypeEnumUserEducationList}">
                                            <option value="${dict.dictId}"
                                                    <c:if test="${sessionScope.currUser.educationId eq dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <select name="degreeId" class="xlselect" style="width: 85%;">
                                        <option value="">请选择</option>
                                        <c:forEach var="dict" items="${dictTypeEnumUserDegreeList}">
                                            <option value="${dict.dictId}"
                                                    <c:if test="${sessionScope.currUser.degreeId eq dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <select name="titleId" class="xlselect" style="width: 85%;">
                                        <option value="">请选择</option>
                                        <c:forEach var="dict" items="${dictTypeEnumUserTitleList}">
                                            <option value="${dict.dictId}"
                                                    <c:if test="${sessionScope.currUser.titleId eq dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td style="text-align: center;">
                                    [<a onclick="delAuthorTr(this)" style="cursor: pointer;">删除</a>]
                                </td>
                            </tr>
                        </c:if>

                        <c:forEach var="author" items="${authorList}">
                            <tr>
                                <td>
                                    <input type="hidden" name="authorFlow" value="${author.authorFlow}"/>

                                    <select name="typeId" class="xlselect" style="width: 85%;">
                                        <option value="">请选择</option>
                                        <c:forEach var="dict" items="${dictTypeEnumAuthorTypeList }">
                                            <option value="${dict.dictId }"
                                                    <c:if test="${author.typeId eq dict.dictId }">selected="selected"</c:if>>${dict.dictName }</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                    <%-- <td style="text-align: center;">
                                         <input type="checkbox" name="isCorresponding" id="${dict.dictId}" value="0"
                                                <c:if test="${author.isCorresponding eq 1}">checked="checked"</c:if>/>
                                     </td>--%>
                                <td>
                                    <input class="xltext" style="width: 85%;" type="text" name="authorName"
                                           readonly="readonly" value="${author.authorName}"/>
                                    <input type="hidden" name="userFlow" value="${author.userFlow}"/>
                                    <input type="hidden" name="deptFlow" value="${author.deptFlow}"/>
                                    <input type="hidden" name="deptName" value="${author.deptName}"/>
                                </td>
                                <td>
                                    <select name="sexId" class="validate[required] xlselect" style="width: 85%;">
                                        <option value="">请选择</option>
                                        <c:forEach var="sex" items="${userSexEnumList}">
                                            <c:if test="${sex.id != userSexEnumUnknown.id}">
                                                <option value="${sex.id}"
                                                        <c:if test="${author.sexId eq sex.id}">selected="selected"</c:if>>${sex.name}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <select name="educationId" class="xlselect" style="width: 85%;">
                                        <option value="">请选择</option>
                                        <c:forEach var="dict" items="${dictTypeEnumUserEducationList}">
                                            <option value="${dict.dictId}"
                                                    <c:if test="${author.educationId eq dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <select name="degreeId" class="xlselect" style="width: 85%;">
                                        <option value="">请选择</option>
                                        <c:forEach var="dict" items="${dictTypeEnumUserDegreeList}">
                                            <option value="${dict.dictId}"
                                                    <c:if test="${author.degreeId eq dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <select name="titleId" class="xlselect" style="width: 85%;">
                                        <option value="">请选择</option>
                                        <c:forEach var="dict" items="${dictTypeEnumUserTitleList}">
                                            <option value="${dict.dictId}"
                                                    <c:if test="${author.titleId eq dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <c:if test="${param.editFlag != GlobalConstant.FLAG_N}">
                                    <td style="text-align: center;">
                                        <%--<c:if test="${not (author.userFlow eq thesis.applyUserFlow)}">--%>
                                        [<a onclick="delAuthor('${author.authorFlow}',this)"
                                            style="cursor: pointer;">删除</a>]
                                        <%--</c:if>--%>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </form>
                <input type="hidden" id="srmAchFile" name="fileFlow" value="${file.fileFlow}"/>
                <p align="center">
                    <c:if test="${(param.editFlag != GlobalConstant.FLAG_N) and (param.editFlag != 'audit')}">
                        <input type="button" value="保&#12288;存" onclick="saveThe();" class="search"/>
                        <input type="button" value="关&#12288;闭" onclick="jboxClose();" class="search"/>
                    </c:if>

                </p>


                <table class="basic" id="moban" style="display: none" style="width: 100%">
                    <tr>
                        <td>
                            <select name="typeId" class="xlselect" style="width: 85%;">
                                <option value="">请选择</option>
                                <c:forEach var="dict" items="${dictTypeEnumAuthorTypeList }">
                                    <option value="${dict.dictId }">${dict.dictName }</option>
                                </c:forEach>
                            </select>
                        </td>
                        <%-- <td style="text-align: center;">
                             <input type="checkbox" name="isCorresponding" id="${dict.dictId}" value="0"
                                    <c:if test="${author.isCorresponding eq 1}">checked="checked"</c:if>
                                    onclick="check(this)"/>
                         </td>--%>
                        <td>
                            <input class="validate[required] xltext" style="width: 85%;" type="text" name="authorName"
                                   readonly="readonly" value="${author.authorName}"/>
                            <input type="hidden" name="userFlow"/>
                            <input type="hidden" name="deptFlow"/>
                            <input type="hidden" name="deptName"/>
                        </td>
                        <td>
                            <select name="sexId" class="validate[required] xlselect" style="width: 85%;">
                                <option value="">请选择</option>
                                <c:forEach var="sex" items="${userSexEnumList}">
                                    <c:if test="${sex.id != userSexEnumUnknown.id}">
                                        <option value="${sex.id}">${sex.name}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <select name="educationId" class="xlselect" style="width: 85%;">
                                <option value="">请选择</option>
                                <c:forEach var="dict" items="${dictTypeEnumUserEducationList}">
                                    <option value="${dict.dictId}">${dict.dictName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <select name="degreeId" class="xlselect" style="width: 85%;">
                                <option value="">请选择</option>
                                <c:forEach var="dict" items="${dictTypeEnumUserDegreeList}">
                                    <option value="${dict.dictId}">${dict.dictName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <select name="titleId" class="xlselect" style="width: 85%;">
                                <option value="">请选择</option>
                                <c:forEach var="dict" items="${dictTypeEnumUserTitleList}">
                                    <option value="${dict.dictId}">${dict.dictName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td style="text-align: center;">
                            [<a onclick="delAuthorTr(this)" style="cursor: pointer;">删除</a>]
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>