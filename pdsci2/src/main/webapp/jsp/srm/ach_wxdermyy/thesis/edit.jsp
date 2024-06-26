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
    <script type="text/javascript"
            src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript">

        function saveThesis(agreeFlag) {
            if (false == $("#srmThesis").validationEngine("validate")) {
                return false;
            }
            if (false == $("#list").validationEngine("validate")) {
                return false;
            }
            if($("#fileTb tr").length < 2){
                jboxTip("至少上传一个附件！");
                return false;
            }
            if($('#authorList tr').length < 2){
                jboxTip("论文作者最少两人（通讯作者，第一作者）");
                return false;
            }else{
                var tongxun = false;
                var diyi = false;
                $.each($('#authorList tr'),function (i,n) {
                    var typeName = $(n).find("select[name='typeId'] :selected").text();
                    if(typeName == '通讯作者'){
                        tongxun = true;
                    }
                    if(typeName == '第一作者'){
                        diyi = true;
                    }
                });
//                alert(tongxun+"-------"+diyi);
                if(!(tongxun && diyi)){
                    jboxTip("论文作者必须有第一作者及通讯作者！");
                    return false;
                }
            }
            /*$("#mubiao input[name='isCorresponding']").each(function () {
             if (this.checked == true) {
             this.value = 1;
             } else {
             this.value = 0;
             }
             });*/
            var thesis = $("#srmThesis").serializeJson();
            //var achFile = $("#srmAchFile").serializeJson();
            var authTab = $('#authorList');
            var trs = authTab.children();
            var datas = [];
            $.each(trs, function (i, n) {
                var authorFlow = $(n).find("input[name='authorFlow']").val();
                var typeId = $(n).find("select[name='typeId']").val();
                var workCode =  $(n).find("input[name='workCode']").val();
                //var isCorresponding = $(n).find("input[name='isCorresponding']").val();
                var authorName = $(n).find("input[name='authorName']").val();
                var sexId = $(n).find("select[name='sexId']").val();
                var educationId = $(n).find("select[name='educationId']").val();
                var titleId = $(n).find("select[name='titleId']").val();
                var degreeId = $(n).find("select[name='degreeId']").val();
                var authorPart = $(n).find("select[name='authorPart']").val();
                var userFlow = $(n).find("input[name='userFlow']").val();
                var deptFlow = $(n).find("input[name='deptFlow']").val();
                var deptName = $(n).find("input[name='deptName']").val();
                var branchId = $(n).find("select[name='branchIdAuthor']").val();
                if (agreeFlag == 'Y' ) {
                    var scoreFlow = $(n).find("select[name='scoreFlow']").val();
                }
                var data = {
                    "authorFlow": authorFlow,
                    "typeId": typeId,
                    "workCode": workCode,
                    "authorName": authorName,
                    "authorPart": authorPart,
                    "sexId": sexId,
                    "educationId": educationId,
                    "titleId": titleId,
                    "userFlow": userFlow,
                    "deptFlow": deptFlow,
                    "deptName": deptName,
                    "degreeId": degreeId,
                    "branchId":branchId
                    ,"scoreFlow": scoreFlow
                };
                datas.push(data);
            });
            var fileDatas = [];
            var fileTr = $("tr[class='achFile']");
            $.each(fileTr, function (i, n) {
                var fileFlow = $(n).find("input[name='fileFlow']").val();
               // var fileRemark = $(n).find("input[name='fileRemark']").val();
                var pubFlie = {
                    "fileFlow": fileFlow
                };
//                alert(n);
                fileDatas.push(pubFlie);
            });
            var t = {'authorList': datas, 'thesis': thesis, "srmAchFileList": fileDatas};
            //var temp = {'jsondata':JSON.stringify(t)};
            $('#jsondata').val(JSON.stringify(t));
            var url = "<s:url value='/srm/ach/thesis/save/${roleScope}'/>";
           // jboxStartLoading();
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
            $(".a").attr("disabled", true);
            if (obj.value == "2") {
                $(".a").attr("disabled", false);
            }
        }

        function reCheck(obj) {
            $(obj).hide();
            $(obj).parent().parent().find("#down").hide();
            $(obj).parent().parent().find("#view").hide();
           // $("#down").hide();
            $(obj).parent().parent().find("#file").show();
        }

        $(function () {
            <c:if test="${param.editFlag == GlobalConstant.FLAG_N}">
            $('#look input[type!=button]').attr('disabled', "disabled");
            $('#look textarea').attr('readonly', "readonly");
            $('#look select').attr('disabled', "disabled");
            $("#reCheck").css("display", "none");
            </c:if>
        });

        //刊物类型
        $(document).ready(function () {
            var jti = $("#jtn").val();
            if (jti == null || jti == "" || jti == undefined) {
                return false;
            }
            var jtis = jti.split(",");
            $("input[name='jourTypeId']").each(function () {
                for (var i = 0; i < jtis.length; i++) {
                    if (jtis[i] == this.value) {
                        this.checked = true;
                    }
                }
            });
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
                jboxTip("当前字符超过限制");
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
        function getDeptName(obj) {
            $("#deptName").val($(obj).find("option:selected").text());
        }
        function getScoreName(obj) {
            $(obj).next().val($(obj).find("option:selected").text());
        }
        var dg;
        function selectAuthor() {
            jboxButtonConfirm("是否本院作者？","本院","外院", function(){//本院
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
            },function(){//外院
                var tr = $("#moban tr:eq(0)").clone();
                $(tr).find("select[name='authorPart']").val("外院");
                $(tr).find("input[name='authorName']").attr("readonly",false);

                $(tr).find("select[name='scoreFlow']").parent().text("无");//attr("disabled","disabled");
//                $(tr).find("select[name='scoreFlow']").val(n.scoreFlow);
                /*外院作者去掉必填设置*/
                $(tr).find("select[name='sexId']").removeClass("validate[required]");
                $(tr).find("select[name='titleId']").removeClass("validate[required]");
                $(tr).find("select[name='degreeId']").removeClass("validate[required]");
                $(tr).find("select[name='educationId']").removeClass("validate[required]");
                $(tr).find("select[name='branchIdAuthor']").removeClass("validate[required]");
                $(tr).find("input[name='workCode']").removeClass();
                $(tr).find("input[name='workCode']").attr("class","validate[maxSize[16]] xlselect");


                $('#authorList').append(tr);
            },300);

        }
        function closeEditPage(dates) {
            dg.close().remove();
            $.each(dates, function (i, n) {
                // alert(n.userName);
                var tr = $("#moban tr:eq(0)").clone();
                $(tr).find("input[name='authorName']").val(n.userName);
                $(tr).find("select[name='authorPart']").val("本院");
                $(tr).find("select[name='sexId']").val(n.sexId);
                $(tr).find("select[name='titleId']").val(n.titleId);
                $(tr).find("select[name='degreeId']").val(n.degreeId);
                $(tr).find("select[name='educationId']").val(n.educationId);
                $(tr).find("input[name='userFlow']").val(n.userFlow);
                $(tr).find("input[name='deptFlow']").val(n.deptFlow);
                $(tr).find("input[name='deptName']").val(n.deptName);
                $(tr).find("select[name='branchIdAuthor']").val(n.branchId);
                $(tr).find("select[name='scoreFlow']").val(n.scoreFlow);
                $(tr).find("input[name='scoreName']").val(n.scoreName);
               // alert( $(tr).find("input[name='deptName']").val());
                $('#authorList').append(tr);
            });

        }
        <c:if test="${empty fileList}">
            $(function(){
                addFile("fileTb");
            });
        </c:if>
        function addFile(tb) {
            /*if ($("#fileTb tr").length > 9) {
                jboxTip("附件总数不能超过10个！");
                return false;
            }*/
            var html = '<tr class="achFile">' +
                '<td><input type="file" id="file" name="files" onchange="checkFile(this)" class="validate[required]"/></td>' +
                '<td><a class="reCheck" href="javascript:void(0);" onclick="delTr(this);">[删除]</a></td>' +
                '</tr>';
            $('#' + tb).append(html);
            fileTbRowspanNum();
        }

        function delTr(tb) {
            jboxConfirm("确认删除？", function () {
                $(tb).parent('td').parent("tr").remove();
            });
            fileTbRowspanNum();
        }

        function delFile(obj,fileFlow){
            var url = '<s:url value="/srm/file/delFileByFlow?fileFlow="/>' + fileFlow;
            jboxConfirm("确认删除？", function () {
                    var tr = $(obj).parent("td").parent("tr");
                    tr.remove();
                jboxGet(url, null, function (resp) {
                    if(resp == '${GlobalConstant.DELETE_SUCCESSED}'){
                    }else{
                      window.location.reload();
                    }
                }, null, true);
            });
            fileTbRowspanNum();
        }

        function fileTbRowspanNum(){
            var rowspanNum = $("#fileTb tr").length + 1;
            $("#rowspanTh").attr("rowSpan",rowspanNum);
        }
        function checkFile(obj){
            var array = new Array('jpg', 'pdf');  //可以上传的文件类型   'gif', 'jpeg', 'png'
            if (obj.value == '') {
                jboxTip("让选择要上传的文件！");
                return false;
            } else {
                var fileContentType = obj.value.match(/^(.*)(\.)(.{1,8})$/)[3]; //这个文件类型正则很有用
                var isExists = false;
                for (var i in array) {
                    if (fileContentType.toLowerCase() == array[i].toLowerCase()) {
                        isExists = true;
                        break;
                    }
                }
                if (isExists == false) {
                    obj.value = null;
                    jboxTip("上传文件类型不正确！");
                    return false;
                }
            }
        }
    </script>
</head>

<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <div id="look">
                <form id="srmThesis" action="<s:url value="/srm/ach/thesis/save/${roleScope}"/>" method="post" enctype="multipart/form-data" style="position: relative">
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
                            <th><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>论文题目：
                            </th>
                            <td colspan="3">
                                <input class="validate[required,maxSize[200]] name xltext" type="text" name="thesisName"
                                       value="${thesis.thesisName}" style="width: 97%;"/>
                            </td>
                        </tr>

                        <tr>
                            <th><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>发表/出版日期：
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
                                    <input type="checkbox" class="validate[required] name" name="jourTypeId"
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
                            <th><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>学科门类：</th>
                            <td>
                                <c:forEach items="${dictTypeEnumSubjectTypeList}" var="dict">
                                    <input class="validate[required]" type="radio" name="subjectTypeId" id="subjectType_${dict.dictId}"
                                           value="${dict.dictId}"
                                           <c:if test="${thesis.subjectTypeId eq dict.dictId}">checked="checked"</c:if>/><label
                                        for="subjectType_${dict.dictId}">&nbsp;${dict.dictName}</label>&nbsp;
                                </c:forEach>
                            </td>
                        </tr>
                        <tr>
                            <th><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>发表范围：</th>
                            <td>
                                <c:forEach items="${dictTypeEnumPublishScopeList}" var="dict">
                                    <input class="validate[required]" type="radio" name="publishScopeId" id="publishScope_${dict.dictId}"
                                           value="${dict.dictId}"
                                           <c:if test="${thesis.publishScopeId eq dict.dictId}">checked="checked"</c:if>/><label
                                        for="publishScope_${dict.dictId}">&nbsp;${dict.dictName}</label>&nbsp;
                                </c:forEach>
                            </td>
                            <th><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>CN号：</th>
                            <td>
                                <input class="xltext validate[required,maxSize[20]]" type="text" name="cnCode"
                                       value="${thesis.cnCode}"/>
                                <%-- thesisFlow --%>
                                <input type="hidden" name="thesisFlow" value="${thesis.thesisFlow}"/>
                            </td>
                        </tr>
                        <tr>
                            <th><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>杂志年卷页：</th>
                            <td colspan="3"><input class="inputText validate[required,maxSize[20]]" style="width: 50px;" type="text" name="jourYear"
                                                   value="${thesis.jourYear}">，
                                <input class="inputText validate[required,maxSize[20]]" type="text" style="width: 50px;" name="volumeCode"
                                       value="${thesis.volumeCode}"/>
                                （<input class="inputText validate[required,maxSize[20]]" type="text" style="width: 50px;" name="jourCode" value="${thesis.jourCode}"/>）：
                                <input class="inputText validate[required,maxSize[20]]" type="text" name="pageNoRange" style="width: 120px" value="${thesis.pageNoRange}">
                                &#12288;&#12288;范例：年.卷（期）：页，2017.1（2）：3-5
                            </td>
                        </tr>
                        <tr>
                            <th><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>字数（千字）：</th>
                            <td><input class="xltext validate[required]" type="text" name="wordCount" value="${thesis.wordCount}"
                                       style="margin-right: 0px;">
                            </td>
                            <th>影响因子：</th>
                            <td><input class="xltext" type="text" name="sci" value="${thesis.sci}"></td>
                        </tr>
                        <tr>
                            <th><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>类别：</th>
                            <td>
                                <select name="typeName" class="validate[required] xlselect">
                                    <option value="">请选择</option>
                                    <option value="论著" <c:if test="${thesis.typeName eq '论著'}">selected="selected"</c:if>>论著</option>
                                    <option value="长篇" <c:if test="${thesis.typeName eq '长篇'}">selected="selected"</c:if>>长篇</option>
                                    <option value="短篇" <c:if test="${thesis.typeName eq '短篇'}">selected="selected"</c:if>>短篇</option>
                                    <option value="病例报告" <c:if test="${thesis.typeName eq '病例报告'}">selected="selected"</c:if>>病例报告</option>
                                    <option value="综述" <c:if test="${thesis.typeName eq '综述'}">selected="selected"</c:if>>综述</option>
                                    <option value="经验交流" <c:if test="${thesis.typeName eq '经验交流'}">selected="selected"</c:if>>经验交流</option>
                                    <option value="小经验" <c:if test="${thesis.typeName eq '小经验'}">selected="selected"</c:if>>小经验</option>
                                    <option value="其他" <c:if test="${thesis.typeName eq '其他'}">selected="selected"</c:if>>其他</option>
                                </select>
                            </td>
                            <th><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>ISSN号：</th>
                            <td>
                                <input class="xltext validate[required]" type="text" name="issnCode" value="${thesis.issnCode}" >
                            </td>
                        </tr>
                        <tr>
                            <th><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>科室：
                            </th>
                            <td>
                                <input id="trainDept" class="xltext" name="deptName" type="text"
                                       value="${thesis.deptName}" autocomplete="off"/>
                                <input id="deptFlow" name="deptFlow" class="input" value="${thesis.deptFlow}" type="text"
                                       hidden style="margin-left: 0px;"/>
                            </td>
                            <th><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>支部：</th>
                            <td>
                                <select name="branchId" class="validate[required] xlselect" style="width: 168px;">
                                    <option value="">请选择</option>
                                    <c:forEach items="${dictTypeEnumWxeyBranchList}" var="dict">
                                        <option value="${dict.dictId }"
                                                <c:if test="${thesis.branchId eq dict.dictId }">selected="selected"</c:if>>${dict.dictName }</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>版面：</th>
                            <td colspan="3">
                                <c:forEach var="dict" items="${dictTypeEnumPublishTypeList}">
                                    <input type="radio" class="validate[required]" name="publishTypeId" id="PublishType_${dict.dictId}" value="${dict.dictId}" <c:if test="${thesis.publishTypeId eq dict.dictId}">checked="checked"</c:if>/><label for="PublishType_${dict.dictId}">&nbsp;${dict.dictName}</label>&nbsp;
                                </c:forEach>
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
                            <td><input type="text" class="validate[maxSize[200]] xltext" style="width: 97%;" name="keyWord"
                                       placeholder="例如：关键字1 关键字2(200个字符以内)" value="${thesis.keyWord}"
                                       /></td>
                        </tr>
                        <tr>
                            <th width="20%">论文摘要：</th>
                            <td><textarea class="validate[maxSize[500]] xltxtarea" style="margin-left: 0px;" name="summary" placeholder="500个字符以内"
                                         >${thesis.summary}</textarea></td>
                        </tr>
                        <tr>
                            <th width="20%">备注信息：</th>
                            <td><textarea class="validate[maxSize[200]] xltxtarea" style="margin-left: 0px;" placeholder="200个字符以内"
                                          name="remark" >${thesis.remark}</textarea></td>
                        </tr>
                    </table>

                    <table class="basic" style="width: 100%">
                        <thead>
                        <tr>
                            <th colspan="4" class="bs_name">
                                附件上传（需上传杂志封面、目录、全文，格式只支持pdf、jpg；全文上传建议维普、万方数据库下载。）
                                <c:if test="${param.editFlag != GlobalConstant.FLAG_N}">
                                    <span style="float: right;padding-right: 10px"><a href="javascript:void(0)"><img
                                            title="新增" src="<s:url value='/'/>css/skin/${skinPath}/images/add.png"
                                            style="cursor: pointer;" onclick="addFile('fileTb')"/></a></span>
                                </c:if>
                            </th>
                        </tr>
                        </thead>
                        <tbody id="fileTb">
                        <tr>
                            <th width="20%" id="rowspanTh" style="font-weight:bold;" rowspan="${fn:length(fileList)+1}"><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>附件信息：</th>
                            <td style="font-weight:bold;">附件名称</td>
                            <td width="30%" style="font-weight:bold;">附件操作</td>
                        </tr>
                        <c:forEach var="file" items="${fileList}" varStatus="status">
                            <tr class="achFile">
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty file.fileFlow}">
                                            <a id="view" target="_blank" href="${sysCfgMap["upload_base_url"]}/${pdfn:encodeString2(file.filePath)}">${file.fileName}</a>&#12288;
                                            <input type="file" id="file" class="validate[required]" name="files" onchange="checkFile(this)" style="display: none;"/>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="file" id="file" class="validate[required]" onchange="checkFile(this)" name="files"/>
                                        </c:otherwise>
                                    </c:choose>
                                    <input type="hidden" name="fileFlow" value="${file.fileFlow}"/>
                                </td>

                                    <td>
                                        <a id="down" href='<s:url value="/srm/file/achDown?fileFlow=${file.fileFlow}"/>'>[下载]</a>&#12288;
                                        <c:if test="${param.editFlag != GlobalConstant.FLAG_N}">
                                        <a class="reCheck" href="javascript:void(0);" onclick="reCheck(this);">[重新选择文件]</a>&#12288;
                                        <a class="reCheck" href="javascript:void(0);" onclick="delFile(this,'${file.fileFlow}');">[删除]</a>
                                        </c:if>
                                    </td>

                            </tr>
                        </c:forEach>
                        </tbody>
                        <%--<tbody>
                        <tr>
                            <th width="20%"><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>附件：</th>
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
                        </tbody>--%>
                    </table>
                </form>
                <form id="list">
                    <table class="basic" id="mubiao" style="width: 100%">
                        <tr>
                            <th colspan="11" class="bs_name"> 论文作者<c:if
                                    test="${param.editFlag != GlobalConstant.FLAG_N}">
                                <a href="javascript:void(0)"><img
                                        src="<s:url value='/'/>css/skin/${skinPath}/images/add.png"
                                        onclick="selectAuthor('${thesis.thesisFlow }');" title="添加"/></a></c:if></th>
                        </tr>
                        <tr>
                            <th style="text-align: center; width: 9%;"><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>作者类型</th>
                            <th style="text-align: center; width: 9%;"><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>是否本院</th>
                            <th style="text-align: center; width: 9%;"><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>作者姓名</th>
                            <th style="text-align: center; width: 9%;"><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>性别</th>
                            <th style="text-align: center; width: 9%;"><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>工号</th>
                            <th style="text-align: center; width: 9%;"><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>学历</th>
                            <th style="text-align: center; width: 9%;"><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>学位</th>
                            <th style="text-align: center; width: 9%;"><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>职称</th>
                            <th style="text-align: center; width: 9%;"><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>支部</th>
                            <c:if test="${company == 'Y'}">
                                <th style="text-align: center; width: 9%;">积分项</th>
                            </c:if>
                            <c:if test="${param.editFlag != GlobalConstant.FLAG_N}">
                                <th style="text-align: center; width: 9%;">操作</th>
                            </c:if>
                        </tr>
                        <tbody id="authorList">
                        <%-- 默认作者 --%>
                        <c:if test="${empty param.thesisFlow}">
                            <tr>
                                <td>
                                    <select name="typeId" class="validate[required] xlselect" style="width: 85%;">
                                        <option value="">请选择</option>
                                        <c:forEach var="dict" items="${dictTypeEnumAuthorTypeList }">
                                            <option value="${dict.dictId }"
                                                    <c:if test="${author.typeId eq dict.dictId }">selected="selected"</c:if>>${dict.dictName }</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <select name="authorPart" class="xlselect" style="width: 85%;" disabled="disabled" >
                                        <option value="本院" selected="selected">本院</option>
                                    </select>
                                </td>
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
                                    <input type="text" name="workCode"
                                           class="validate[required,maxSize[16]] xlselect"
                                           value="${author.workCode }" style="width: 85%"/>
                                </td>
                                <td>
                                    <select name="educationId" class="validate[required] xlselect" style="width: 85%;">
                                        <option value="">请选择</option>
                                        <c:forEach var="dict" items="${dictTypeEnumUserEducationList}">
                                            <option value="${dict.dictId}"
                                                    <c:if test="${sessionScope.currUser.educationId eq dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <select name="degreeId" class="validate[required] xlselect" style="width: 85%;">
                                        <option value="">请选择</option>
                                        <c:forEach var="dict" items="${dictTypeEnumUserDegreeList}">
                                            <option value="${dict.dictId}"
                                                    <c:if test="${sessionScope.currUser.degreeId eq dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <select name="titleId" class="validate[required] xlselect" style="width: 85%;">
                                        <option value="">请选择</option>
                                        <c:forEach var="dict" items="${dictTypeEnumUserTitleList}">
                                            <option value="${dict.dictId}"
                                                    <c:if test="${sessionScope.currUser.titleId eq dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <select name="branchIdAuthor" class="validate[required] xlselect" style="width: 85%;">
                                        <option value="">请选择</option>
                                        <c:forEach items="${dictTypeEnumWxeyBranchList}" var="dict">
                                            <option value="${dict.dictId }">${dict.dictName }</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <c:if test="${company == 'Y'}">
                                <td>
                                    <input type="hidden" value="${author.scoreName}"/>
                                    <select class="xlselect" style="width: 85%;">
                                        <option value="">请选择</option>
                                    </select>
                                </td>
                                </c:if>
                                <td style="text-align: center;">
                                    [<a onclick="delAuthor('${author.authorFlow}',this)"
                                        style="cursor: pointer;">删除</a>]
                                </td>
                            </tr>

                        </c:if>

                        <c:forEach var="author" items="${authorList}">
                            <tr>
                                <td>
                                    <input type="hidden" name="authorFlow" value="${author.authorFlow}"/>

                                    <select name="typeId" class="<c:if test="${author.authorPart ne '外院' }">validate[required]</c:if> xlselect" style="width: 85%;">
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
                                    <select name="authorPart" class="xlselect" style="width: 85%;" disabled="disabled">
                                        <option value="本院" <c:if test="${author.authorPart eq '本院' }">selected="selected"</c:if> >本院</option>
                                        <option value="外院" <c:if test="${author.authorPart eq '外院' }">selected="selected"</c:if>>外院</option>
                                    </select>
                                </td>
                                <td>
                                    <input class="xltext" style="width: 85%;" type="text" name="authorName"
                                           readonly="readonly" value="${author.authorName}"/>
                                    <input type="hidden" name="userFlow" value="${author.userFlow}"/>
                                    <input type="hidden" name="deptFlow" value="${author.deptFlow}"/>
                                    <input type="hidden" name="deptName" value="${author.deptName}"/>
                                </td>
                                <td>
                                    <select name="sexId" class="<c:if test="${author.authorPart ne '外院' }">validate[required]</c:if> xlselect" style="width: 85%;">
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
                                    <input type="text" name="workCode"
                                           class="validate[<c:if test="${author.authorPart ne '外院' }">required,</c:if>maxSize[16]] xlselect"
                                           value="${author.workCode }" style="width: 85%"/>
                                </td>
                                <td>
                                    <select name="educationId" class="<c:if test="${author.authorPart ne '外院' }">validate[required]</c:if> xlselect" style="width: 85%;">
                                        <option value="">请选择</option>
                                        <c:forEach var="dict" items="${dictTypeEnumUserEducationList}">
                                            <option value="${dict.dictId}"
                                                    <c:if test="${author.educationId eq dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <select name="degreeId" class="<c:if test="${author.authorPart ne '外院' }">validate[required]</c:if> xlselect" style="width: 85%;">
                                        <option value="">请选择</option>
                                        <c:forEach var="dict" items="${dictTypeEnumUserDegreeList}">
                                            <option value="${dict.dictId}"
                                                    <c:if test="${author.degreeId eq dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <select name="titleId" class="<c:if test="${author.authorPart ne '外院' }">validate[required]</c:if> xlselect" style="width: 85%;">
                                        <option value="">请选择</option>
                                        <c:forEach var="dict" items="${dictTypeEnumUserTitleList}">
                                            <option value="${dict.dictId}"
                                                    <c:if test="${author.titleId eq dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <select name="branchIdAuthor" class="<c:if test="${author.authorPart ne '外院' }">validate[required]</c:if> xlselect" style="width: 85%;">
                                        <option value="">请选择</option>
                                        <c:forEach items="${dictTypeEnumWxeyBranchList}" var="dict">
                                            <option value="${dict.dictId }"
                                                    <c:if test="${author.branchId eq dict.dictId }">selected="selected"</c:if>>${dict.dictName }</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <c:if test="${company == 'Y'}">
                                <td>
                                    <c:if test="${author.authorPart ne '外院'}">
                                    <select name="scoreFlow" id="selectScore" class="xlselect" style="width: 85%;"
                                            onchange="getScoreName(this);">
                                        <option value="">请选择</option>
                                        <c:forEach items="${srmAchScoreList}" var="score">
                                            <option value="${score.scoreFlow}" <c:if test="${score.scoreFlow eq author.scoreFlow}">selected="selected"</c:if>>${score.scoreName}</option>
                                        </c:forEach>
                                    </select>
                                    <input type="hidden" name="scoreName" value="${author.scoreName}"/>
                                    </c:if>
                                    <c:if test="${author.authorPart eq '外院'}">
                                        无
                                    </c:if>
                                </td>
                                </c:if>
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

                <%-- 文件流水号 --%>
                <form id="srmAchFile">
                    <input type="hidden" name="fileFlow" value="${file.fileFlow}"/>
                </form>

                <p align="center">
                    <c:if test="${(param.editFlag != GlobalConstant.FLAG_N) and (param.auditFlag != 'audit')}">
                        <input type="button" value="保&#12288;存" onclick="saveThe();" class="search"/>
                        <input type="button" value="关&#12288;闭" onclick="jboxClose();" class="search"/>
                    </c:if>

                </p>


                <table class="basic" id="moban" style="display: none" style="width: 100%">
                    <tr>
                        <td>
                            <select name="typeId" class="validate[required] xlselect" style="width: 85%;">
                                <option value="">请选择</option>
                                <c:forEach var="dict" items="${dictTypeEnumAuthorTypeList }">
                                    <option value="${dict.dictId }">${dict.dictName }</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <select name="authorPart" class="xlselect" style="width: 85%;" disabled="disabled">
                                <option value="本院" >本院</option>
                                <option value="外院" >外院</option>
                            </select>
                        </td>
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
                            <input type="text" name="workCode"
                                   class="validate[required,maxSize[16]] xlselect"
                                   value="${author.workCode }" style="width: 85%"/>
                        </td>
                        <td>
                            <select name="educationId" class="validate[required] xlselect" style="width: 85%;">
                                <option value="">请选择</option>
                                <c:forEach var="dict" items="${dictTypeEnumUserEducationList}">
                                    <option value="${dict.dictId}">${dict.dictName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <select name="degreeId" class="validate[required] xlselect" style="width: 85%;">
                                <option value="">请选择</option>
                                <c:forEach var="dict" items="${dictTypeEnumUserDegreeList}">
                                    <option value="${dict.dictId}">${dict.dictName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <select name="titleId" class="validate[required] xlselect" style="width: 85%;">
                                <option value="">请选择</option>
                                <c:forEach var="dict" items="${dictTypeEnumUserTitleList}">
                                    <option value="${dict.dictId}">${dict.dictName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <select name="branchIdAuthor" class="validate[required] xlselect" style="width: 85%;">
                                <option value="">请选择</option>
                                <c:forEach items="${dictTypeEnumWxeyBranchList}" var="dict">
                                    <option value="${dict.dictId }">${dict.dictName }</option>
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
</body>
</html>