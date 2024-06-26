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
    <script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>"></script>
</head>
<body>
<script type="text/javascript">
    function saveBookAndAuthor(agreeFlag) {
        if (false == $("#book").validationEngine("validate")) {
            return false;
        }
        if (false == $("#authorList").validationEngine("validate")) {
            return false;
        }
        var book = $("#book").serializeJson();
        var achFile = $("#srmAchFile").serializeJson();
        var trs = $('#appendTbody').children();
        var datas = [];
        $.each(trs, function (i, n) {
            var authorFlow = $(n).find("input[name='authorFlow']").val();
            var authorName = $(n).find("input[name='authorName']").val();
            var writeTypeId = $(n).find("select[name='writeTypeId']").val();
            var typeId = $(n).find("select[name='typeId']").val();
            var sexId = $(n).find("select[name='sexId']").val();
            var educationId = $(n).find("select[name='educationId']").val();
            var titleId = $(n).find("select[name='titleId']").val();
            var writeWordCount = $(n).find("input[name='writeWordCount']").val();
            var workCode =  $(n).find("input[name='workCode']").val();
            var userFlow =  $(n).find("input[name='userFlow']").val();
            var deptFlow = $(n).find("input[name='deptFlow']").val();
            var deptName = $(n).find("input[name='deptName']").val();
            var branchId = $(n).find("select[name='branchIdAuthor']").val();
            if (agreeFlag == 'Y' ) {
                var scoreFlow = $(n).find("select[name='scoreFlow']").val();
            }
            var data = {
                "authorFlow": authorFlow,
                "authorName": authorName,
                "writeTypeId": writeTypeId,
                "typeId": typeId,
                "sexId": sexId,
                "educationId": educationId,
                "titleId": titleId,
                "userFlow": userFlow,
                "deptFlow": deptFlow,
                "workCode": workCode,
                "deptName": deptName,
                "writeWordCount": writeWordCount,
                "branchId":branchId,
                "scoreFlow": scoreFlow
            };
            datas.push(data);
        });
        var t = {'authorList': datas, 'book': book, 'srmAchFile': achFile};
        $("#jsondata").val(JSON.stringify(t));
        var url = "<s:url value='/srm/ach/book/save/${roleScope}'/>";
        //$('#book').submit();
        jboxStartLoading();
        jboxSubmit($('#book'), url, function (resp) {

                },
                function (resp) {
                    alert("error");
                }, false);
        return true;
    }
    function saveBook() {
        if (saveBookAndAuthor()) {
            jboxTip("保存成功");
            setTimeout("window.parent.frames['mainIframe'].location.reload(true);jboxClose();", 2000)
        }
    }

    //删除一条著作作者
    function delAuthor(authorFlow, obj) {
        var url = '<s:url value="/srm/ach/book/deleteAuthor?authorFlow="/>' + authorFlow;
        jboxConfirm("确认删除？", function () {
            jboxGet(url, null, function () {
                var tr = $(obj).parent("td").parent("tr");
                tr.remove();
            }, null, true);
        });
    }

    /*  function addAuthor() {
     $('#appendTbody').append($("#addAuthor tr:eq(0)").clone());
     }*/

    function delAuthorTr(obj) {
        jboxConfirm("确认删除？", function () {
            obj.parentNode.parentNode.remove();
        });
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
    function strChar(obj, limitLength) {
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
            jboxTip("当前字符长度超过限制");
            $(obj).val("");
        }
    }
    function initDept() {
        var datas = [];
        var url = "<s:url value='/srm/ach/topic/searchDept'/>";
        jboxPost(url, null, function (resp) {
            $.each(resp, function (i, n) {
                var d = {};
                d.id = n.deptFlow;
                d.text = n.deptName;
                datas.push(d);
            });
        }, null, false);
        var itemSelectFuntion = function () {
            $("#deptFlow").val(this.id);
        };
        $.selectSuggest('trainDept', datas, itemSelectFuntion, "deptFlow", true);
    }

    $(document).ready(function () {
        initDept();
    });
    /*$(function () {
        var url = "<s:url value='/srm/ach/topic/searchDept'/>";
        var selectedFlow = "${book.deptFlow}";
        <c:if test="${param.auditFlag != 'audit'}">
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
    });*/
    function getDeptName(obj) {
        $("#deptName").val($(obj).find("option:selected").text());
    }
    function getScoreName(obj) {
        $(obj).next().val($(obj).find("option:selected").text());
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
             //alert(n.deptFlow);
            var tr = $("#addAuthor tr:eq(0)").clone();
            $(tr).find("input[name='authorName']").val(n.userName);
            //alert($(tr).find("input[name='authorName']").val());
            $(tr).find("select[name='sexId']").val(n.sexId);
            $(tr).find("select[name='titleId']").val(n.titleId);
            //$(tr).find("select[name='degreeId']").val(n.degreeId);
            $(tr).find("select[name='educationId']").val(n.educationId);
            $(tr).find("input[name='userFlow']").val(n.userFlow);
            $(tr).find("input[name='deptFlow']").val(n.deptFlow);
            $(tr).find("input[name='deptName']").val(n.deptName);
           // alert( $(tr).find("input[name='deptName']").val());
            $('#appendTbody').append(tr);

        });

    }
</script>

<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <div id="look">
                <form id="book" enctype="multipart/form-data" method="post"
                      action="<s:url value='/srm/ach/book/save/${roleScope}'/>" target="screct_frame">
                    <input id="jsondata" type="hidden" name="jsondata" value=""/>
                    <c:if test="${(param.editFlag != GlobalConstant.FLAG_N) and (param.auditFlag != 'audit')}">
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
                            <th><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>著作名称：
                            </th>
                            <td>
                                <input type="hidden" name="bookFlow" value="${book.bookFlow}">
                                <input class="validate[required,maxSize[100]] name xltext" class="td_blue"
                                       name="bookName" type="text" value="${book.bookName}"/>
                            </td>
                            <th>项目来源：</th>
                            <td>
                                <select name="projSourceId" class="xlselect" style="width: 168px;">
                                    <option value="">请选择</option>
                                    <c:forEach items="${dictTypeEnumWxeyProjSourceList }" var="dict">
                                        <option value="${dict.dictId }"
                                                <c:if test="${book.projSourceId eq dict.dictId }">selected="selected"</c:if>>${dict.dictName }</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>出版单位：
                            </th>
                            <td>
                                <input class="validate[required] xltext" name="publishOrg" type="text"
                                       value="${book.publishOrg}"/>
                            </td>
                            <th><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>出版日期：
                            </th>
                            <td>
                                <input onClick="WdatePicker({dateFmt:'yyyy-MM'})" style="width: 160px;"
                                       class="validate[required] ctime"
                                       name="publishDate" type="text" value="${book.publishDate}"/>
                            </td>
                        </tr>
                        <tr>
                            <th>出版地：</th>
                            <td>
                                <c:forEach var="dict" items="${dictTypeEnumPlaceNameTypeList}">
                                    <input type="radio" name="pubPlaceId" id="pubPlaceId_${dict.dictId }"
                                           value="${dict.dictId }"
                                           <c:if test="${book.pubPlaceId==dict.dictId}">checked="checked"</c:if>/><label
                                        for="pubPlaceId_${dict.dictId }">&nbsp;${dict.dictName }</label>&nbsp;
                                </c:forEach>
                            </td>
                            <th><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>著作类别：
                            </th>
                            <td>
                                <c:forEach var="dict" items="${dictTypeEnumAchBookTypeList}">
                                    <input type="radio" name="typeId" id="typeId_${dict.dictId }"
                                           value="${dict.dictId }" class="validate[required]"
                                           <c:if test="${book.typeId==dict.dictId}">checked="checked"</c:if>/><label
                                        for="typeId_${dict.dictId }">&nbsp;${dict.dictName }</label>&nbsp;
                                </c:forEach>
                            </td>
                        </tr>
                        <tr>
                            <th><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>学科门类：
                            </th>
                            <td>
                                <c:forEach var="dict" items="${dictTypeEnumSubjectTypeList}">
                                    <input type="radio" name="categoryId" id="categoryId_${dict.dictId }"
                                           value="${dict.dictId }" class="validate[required]"
                                           <c:if test="${book.categoryId==dict.dictId}">checked="checked"</c:if>/><label
                                        for="categoryId_${dict.dictId }">&nbsp;${dict.dictName }</label>&nbsp;
                                </c:forEach>
                            </td>
                            <th>总字数：</th>
                            <td>
                                <input style="text-align: left;margin-right: 0px;" name="wordCount"
                                       class="validate[custom[number]] xltext" type="text" value="${book.wordCount}"/>万字
                            </td>
                        </tr>
                        <tr>
                            <th>是否译成外文：</th>
                            <td>
                                <c:choose>
                                    <c:when test="${not empty book.isTranslated}">
                                        <c:if test="${book.isTranslated eq 0 }">
                                            <input type="radio" name="isTranslated" id="isTranslated_0" value="0"
                                                   checked="checked"/><label for="isTranslated_0">&nbsp;是</label>&nbsp;
                                            <input type="radio" name="isTranslated" id="isTranslated_1"
                                                   value="1"/><label for="isTranslated_1">&nbsp;否</label>&nbsp;
                                        </c:if>
                                        <c:if test="${book.isTranslated eq 1 }">
                                            <input type="radio" name="isTranslated" id="isTranslated_0"
                                                   value="0"/><label for="isTranslated_0">&nbsp;是</label>&nbsp;
                                            <input type="radio" name="isTranslated" id="isTranslated_1" value="1"
                                                   checked="checked"/><label for="isTranslated_1">&nbsp;否</label>&nbsp;
                                        </c:if>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="radio" name="isTranslated" id="isTranslated_0" value="0"/><label
                                            for="isTranslated_0">&nbsp;是</label>&nbsp;
                                        <input type="radio" name="isTranslated" id="isTranslated_1" value="1"/><label
                                            for="isTranslated_1">&nbsp;否</label>&nbsp;
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <th><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>ISBN号：
                            </th>
                            <td>
                                <input class="validate[required,custom[onlyLetterNumber]] name xltext" name="isbnCode"
                                       type="text" value="${book.isbnCode}"/>
                            </td>
                        </tr>
                        <tr>
                            <th>CIP号：</th>
                            <td>
                                <input name="cptCode" class="xltext" type="text" value="${book.cptCode}"/>
                            </td>
                            <th>出版社级别：</th>
                            <td>
                                <c:forEach var="dict" items="${dictTypeEnumPressLevelTypeList}">
                                    <input type="radio" name="pressLevelId" id="pressLeveId=${dict.dictId }"
                                           value="${dict.dictId }"
                                           <c:if test="${book.pressLevelId==dict.dictId}">checked="checked"</c:if>/><label
                                        for="pressLeveId=${dict.dictId }">&nbsp;${dict.dictName }</label>&nbsp;
                                </c:forEach>
                            </td>
                        </tr>
                        <tr>
                            <th><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>所在科室：
                            </th>
                            <td>
                                <%--<input type="hidden" id="deptName" name="deptName" value="${book.deptName}"/>
                                <select name="deptFlow" id="selectDept" class="validate[required] xlselect"
                                        onchange="getDeptName(this);">
                                    <option value="">请选择</option>
                                </select>--%>
                                <input id="trainDept" class="xltext" name="deptName" type="text"
                                       value="${book.deptName}" autocomplete="off"/>
                                <input id="deptFlow" name="deptFlow" class="input" value="${book.deptFlow}" type="text"
                                       hidden style="margin-left: 0px;"/>
                            </td>
                            <th>语言种类：</th>
                            <td>
                                <select name="languageTypeId" class="xlselect" style="width: 168px;">
                                    <option value="">请选择</option>
                                    <c:forEach items="${dictTypeEnumLanguageTypeList }" var="dict">
                                        <option value="${dict.dictId }"
                                                <c:if test="${book.languageTypeId eq dict.dictId }">selected="selected"</c:if>>${dict.dictName }</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                            <th><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>所在支部：</th>
                            <td>
                                <select name="branchId" class="validate[required] xlselect" style="width: 168px;">
                                    <option value="">请选择</option>
                                    <c:forEach items="${dictTypeEnumWxeyBranchList}" var="dict">
                                        <option value="${dict.dictId }"
                                                <c:if test="${book.branchId eq dict.dictId }">selected="selected"</c:if>>${dict.dictName }</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <th>工号：</th>
                            <td>
                                <input name="jobNumber" class="xltext" type="text" value="${book.jobNumber}"/>
                            </td>

                        </tr>


                    </table>

                    <table class="basic" style="width: 100%">
                        <thead>
                        <tr>
                            <th colspan="4" class="bs_name">附件上传</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <th width="16%"><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>附件：</th>
                            <td colspan="3">
                                <c:choose>
                                    <c:when test="${not empty file.fileName}">
                                        <a id="down"
                                           href='<s:url value="/srm/file/achDown?fileFlow=${file.fileFlow}"/>'>${file.fileName}</a>
                                        <input type="file" id="file" name="file" style="display: none;"/>
							<span style="float: right; padding-right: 10px;">
							<a id="reCheck" href="javascript:void(0);" onclick="reCheck(this);">[重新选择文件]</a>
							</span>
                                    </c:when>
                                    <c:otherwise>
                                        <input class="validate[required]" type="file" id="file" name="file"/>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        </tbody>
                    </table>

                    <table class="basic" style="width: 100%">
                        <thead>
                        <tr>
                            <th colspan="4" class="bs_name">备注信息</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <th width="16%">备注信息：</th>
                            <td>
                                <textarea class="validate[maxSize[200]] xltxtarea" placeholder="200个字符以内" style="margin-left: 0px;"
                                          name="remark">${book.remark }</textarea>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </form>

                <form id="srmAchFile">
                    <input type="hidden" name="fileFlow" value="${file.fileFlow}"/>
                </form>

                <form id="authorList">
                    <table class="basic" style="width: 100%">
                        <tr class="bs_name" style="height: 26px">
                            <td style="text-align:left" colspan="11">著作作者
                                <c:if test="${param.editFlag != GlobalConstant.FLAG_N}">
                                    <a href="javascript:void(0)"><img
                                            src="<s:url value='/'/>css/skin/${skinPath}/images/add.png"
                                            onclick="selectAuthor();" title="添加"/>
                                    </a>
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <th width="9%" style="text-align: center;">署名顺序</th>
                            <th width="9%" style="text-align: center;">作者姓名</th>
                            <th width="9%" style="text-align: center;">参编类型</th>
                            <th width="9%" style="text-align: center;">性别</th>
                            <th width="9%" style="text-align: center;">工号</th>
                            <th width="9%" style="text-align: center;">学位</th>
                            <th width="9%" style="text-align: center;">参编字数(万字)</th>
                            <th width="9%" style="text-align: center;">职称</th>
                            <th width="9%" style="text-align: center;">支部</th>
                            <c:if test="${company == 'Y'}">
                                <th width="9%" style="text-align: center; ">积分项</th>
                            </c:if>
                            <c:if test="${param.editFlag != GlobalConstant.FLAG_N}">
                                <th width="9%" style="text-align: center;">操作</th>
                            </c:if>
                        </tr>

                        <tbody id="appendTbody">
                        <%-- 默认作者 --%>
                        <c:if test="${empty param.bookFlow}">
                            <tr>
                                <td>
                                    <select name="typeId" class="xlselect" style="width: 85%;">
                                        <option value="">请选择</option>
                                        <c:forEach var="dict" items="${dictTypeEnumWxeySignSeqList}">
                                            <option value="${dict.dictId }"
                                                    <c:if test="${author.typeId eq dict.dictId }">selected="selected"</c:if>>${dict.dictName }</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <input class="validate[required] xltext" style="width: 85%" type="text"
                                           readonly="readonly" name="authorName" value="${sessionScope.currUser.userName}"/>
                                    <input type="hidden" name="userFlow" value="${sessionScope.currUser.userFlow}"/>
                                    <input type="hidden" name="deptFlow" value="${sessionScope.currUser.deptFlow}"/>
                                    <input type="hidden" name="deptName" value="${sessionScope.currUser.deptName}"/>
                                </td>
                                <td>
                                    <select name="writeTypeId" class="xlselect" style="width: 85%;">
                                        <option value="">请选择</option>
                                        <c:forEach var="dict" items="${dictTypeEnumWriteNameTypeList}">
                                            <option value="${dict.dictId }"
                                                    <c:if test="${author.writeTypeId eq dict.dictId }">selected="selected"</c:if>>${dict.dictName }</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <select name="sexId" class="validate[required] xlselect" style="width: 85%;">
                                        <option value="">请选择</option>
                                        <c:forEach var="dict" items="${userSexEnumList }">
                                            <c:if test="${dict.id != userSexEnumUnknown.id}">
                                                <option value="${dict.id}"
                                                        <c:if test="${sessionScope.currUser.sexId eq dict.id}">selected="selected"</c:if>>${dict.name}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <input type="text" name="workCode"
                                           class="validate[maxSize[16]] xlselect"
                                           value="${author.workCode }" style="width: 85%"/>
                                </td>
                                <td>
                                    <select name="educationId" class="xlselect" style="width: 85%;">
                                        <option value="">请选择</option>
                                        <c:forEach var="dict" items="${dictTypeEnumUserEducationList }">
                                            <option value="${dict.dictId }"
                                                    <c:if test="${sessionScope.currUser.educationId eq dict.dictId }">selected="selected"</c:if>>${dict.dictName }</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <input type="text" id="writeWordCount" name="writeWordCount"
                                           class="validate[custom[number],maxSize[5]] xlselect"
                                           value="${author.writeWordCount }" style="width: 85%"/>
                                </td>
                                <td>
                                    <select name="titleId" class="xlselect" style="width: 85%;">
                                        <option value="">请选择</option>
                                        <c:forEach var="dict" items="${dictTypeEnumUserTitleList }">
                                            <option value="${dict.dictId }"
                                                    <c:if test="${sessionScope.currUser.titleId eq dict.dictId }">selected="selected"</c:if>>${dict.dictName }</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <select name="branchIdAuthor" class="xlselect" style="width: 85%;">
                                        <option value="">请选择</option>
                                        <c:forEach items="${dictTypeEnumWxeyBranchList}" var="dict">
                                            <option value="${dict.dictId }"<c:if test="${author.branchId eq dict.dictId}">selected="selected"</c:if>>${dict.dictName }</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <c:if test="${company == 'Y'}">
                                    <td>
                                        <select name="scoreFlow" class="validate[required] xlselect" style="width: 85%;"
                                                onchange="getScoreName(this);">
                                            <option value="">请选择</option>
                                            <c:forEach items="${srmAchScoreList}" var="score">
                                                <option value="${score.scoreFlow}" <c:if test="${score.scoreFlow eq author.scoreFlow}">selected="selected"</c:if>>${score.scoreName}</option>
                                            </c:forEach>
                                        </select>
                                        <input type="hidden" name="scoreName" value="${author.scoreName}"/>
                                    </td>
                                </c:if>
                                <c:if test="${param.editFlag != GlobalConstant.FLAG_N}">
                                    <td style="text-align: center;">
                                        [<a onclick="delAuthorTr(this)" style="cursor: pointer;">删除</a>]
                                    </td>
                                </c:if>
                            </tr>
                        </c:if>

                        <c:forEach items="${bookAuthorList}" var="author">
                            <tr>
                                <td>
                                    <select name="typeId" class="xlselect" style="width: 85%;">
                                        <option value="">请选择</option>
                                        <c:forEach var="dict" items="${dictTypeEnumWxeySignSeqList}">
                                            <option value="${dict.dictId }"
                                                    <c:if test="${author.typeId eq dict.dictId }">selected="selected"</c:if>>${dict.dictName }</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <input class="validate[required] xltext" style="width: 85%" type="text"
                                         readonly="readonly"  name="authorName" value="${author.authorName}"/>
                                    <input type="hidden" name="authorFlow" value="${author.authorFlow}">
                                    <input type="hidden" name="userFlow" value="${author.userFlow}"/>
                                    <input type="hidden" name="deptFlow" value="${author.deptFlow}"/>
                                    <input type="hidden" name="deptName" value="${author.deptName}"/>
                                </td>
                                <td>
                                    <select name="writeTypeId" class="xlselect" style="width: 85%;">
                                        <option value="">请选择</option>
                                        <c:forEach var="dict" items="${dictTypeEnumWriteNameTypeList}">
                                            <option value="${dict.dictId }"
                                                    <c:if test="${author.writeTypeId eq dict.dictId }">selected="selected"</c:if>>${dict.dictName }</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <select name="sexId" class="validate[required] xlselect" style="width: 85%;">
                                        <option value="">请选择</option>
                                        <c:forEach var="dict" items="${userSexEnumList }">
                                            <c:if test="${dict.id != userSexEnumUnknown.id}">
                                                <option value="${dict.id}"
                                                        <c:if test="${author.sexId eq dict.id}">selected="selected"</c:if>>${dict.name}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <input type="text" name="workCode"
                                           class="validate[maxSize[16]] xlselect"
                                           value="${author.workCode }" style="width: 85%"/>
                                </td>
                                <td>
                                    <select name="educationId" class="xlselect" style="width: 85%;">
                                        <option value="">请选择</option>
                                        <c:forEach var="dict" items="${dictTypeEnumUserEducationList }">
                                            <option value="${dict.dictId }"
                                                    <c:if test="${author.educationId eq dict.dictId }">selected="selected"</c:if>>${dict.dictName }</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <input type="text" name="writeWordCount"
                                           class="validate[custom[number],maxSize[5]] xlselect"
                                           value="${author.writeWordCount }" style="width: 85%"/>
                                </td>
                                <td>
                                    <select name="titleId" class="xlselect" style="width: 85%;">
                                        <option value="">请选择</option>
                                        <c:forEach var="dict" items="${dictTypeEnumUserTitleList }">
                                            <option value="${dict.dictId }"
                                                    <c:if test="${author.titleId eq dict.dictId }">selected="selected"</c:if>>${dict.dictName }</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <select name="branchIdAuthor" class="xlselect" style="width: 85%;">
                                        <option value="">请选择</option>
                                        <c:forEach items="${dictTypeEnumWxeyBranchList}" var="dict">
                                            <option value="${dict.dictId }" <c:if test="${author.branchId eq dict.dictId}">selected="selected"</c:if>>${dict.dictName }</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <c:if test="${company == 'Y'}">
                                    <td>
                                        <select name="scoreFlow" class="xlselect" style="width: 85%;"
                                                onchange="getScoreName(this);">
                                            <option value="">请选择</option>
                                            <c:forEach items="${srmAchScoreList}" var="score">
                                                <option value="${score.scoreFlow}" <c:if test="${score.scoreFlow eq author.scoreFlow}">selected="selected"</c:if>>${score.scoreName}</option>
                                            </c:forEach>
                                        </select>
                                        <input type="hidden"  name="scoreName" value="${author.scoreName}"/>
                                    </td>
                                </c:if>
                                <c:if test="${param.editFlag != GlobalConstant.FLAG_N}">
                                    <td style="text-align: center;">
                                        [<a onclick="delAuthor('${author.authorFlow}',this)"
                                            style="cursor: pointer;">删除</a>]
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </form>
                <p align="center">
                    <c:if test="${(param.editFlag != GlobalConstant.FLAG_N) and (param.auditFlag != 'audit')}">
                        <input type="button" value="保&#12288;存" onclick="saveBook();" class="search"/>
                        <input type="button" value="关&#12288;闭" onclick="jboxClose();" class="search"/>
                    </c:if>
                </p>
                <table id="addAuthor" class="basic" style="display: none;" style="width: 100%">
                    <tr>
                        <td>
                            <select name="typeId" class="xlselect" style="width: 85%;">
                                <option value="">请选择</option>
                                <c:forEach var="dict" items="${dictTypeEnumWxeySignSeqList}">
                                    <option value="${dict.dictId }"
                                            <c:if test="${author.typeId eq dict.dictId }">selected="selected"</c:if>>${dict.dictName }</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <input id="authorName" class="xltext" class="text" name="authorName" type="text"
                                   readonly="readonly" style="width: 85%;"/>
                            <input type="hidden" name="userFlow" />
                            <input type="hidden" name="deptFlow" />
                            <input type="hidden" name="deptName" />
                        </td>
                        <td>
                            <select id="writeTypeId" name="writeTypeId" class="xlselect" style="width: 85%;">
                                <option value="">请选择</option>
                                <c:forEach var="dict" items="${dictTypeEnumWriteNameTypeList}">
                                    <option value="${dict.dictId }">${dict.dictName }</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <select class="validate[required] xlselect" id="sexId" name="sexId" style="width: 85%;">
                                <option value="">请选择</option>
                                <c:forEach var="dict" items="${userSexEnumList}">
                                    <c:if test="${dict.id != userSexEnumUnknown.id}">
                                        <option value="${dict.id}">${dict.name}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <input type="text" name="workCode"
                                   class="validate[maxSize[16]] xlselect"
                                   value="${author.workCode }" style="width: 85%"/>
                        </td>
                        <td>
                            <select id="educationId" name="educationId" class="xlselect" style="width: 85%;">
                                <option value="">请选择</option>
                                <c:forEach var="dict" items="${dictTypeEnumUserEducationList}">
                                    <option value="${dict.dictId}">${dict.dictName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <input type="text" name="writeWordCount"
                                   class="validate[custom[number],maxSize[5]] xlselect" style="width: 85%;">
                        </td>
                        <td>
                            <select id="titleId" name="titleId" class="xlselect" style="width: 85%;">
                                <option value="">请选择</option>
                                <c:forEach var="dict" items="${dictTypeEnumUserTitleList}">
                                    <option value="${dict.dictId}">${dict.dictName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <select name="branchIdAuthor" class="xlselect" style="width: 85%;">
                                <option value="">请选择</option>
                                <c:forEach items="${dictTypeEnumWxeyBranchList}" var="dict">
                                    <option value="${dict.dictId }" <c:if test="${author.branchId eq dict.dictId}">selected="selected"</c:if>>${dict.dictName }</option>
                                </c:forEach>
                            </select>
                        </td>
                        <c:if test="${company == 'Y'}">
                            <td>
                                <select name="scoreFlow" class="xlselect" style="width: 85%;"
                                        onchange="getScoreName(this);">
                                    <option value="">请选择</option>
                                    <c:forEach items="${srmAchScoreList}" var="score">
                                        <option value="${score.scoreFlow}">${score.scoreName}</option>
                                    </c:forEach>
                                </select>
                                <input type="hidden" name="scoreName" value="${author.scoreName}"/>
                            </td>
                        </c:if>
                        <td style="text-align: center;">
                            [<a onclick="delAuthorTr(this)" style="cursor: pointer;">删除</a>]
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>
<iframe name="screct_frame" style="display: none"></iframe>
</body>
</html>