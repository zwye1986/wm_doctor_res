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
    <script type="text/javascript">
        function saveSatAndAuthor() {
            if (false == $("#satForm").validationEngine("validate")) {
                return false;
            }
            if (false == $("#authorList").validationEngine("validate")) {
                return false;
            }
            jboxConfirm("确认保存？",function(){
                var sat = $("#satForm").serializeJson();
                var trs = $('#appendTbody').children();
                var fileTr = $("#projFileTb").children();
                var datas = [];
                $.each(trs, function (i, n) {
                    var authorFlow = $(n).find("input[name='authorFlow']").val();
                    var authorName = $(n).find("input[name='authorName']").val();
                    var typeName = $(n).find("input[name='typeName']").val();
                    var sexId = $(n).find("select[name='sexId']").val();
                    var educationId = $(n).find("select[name='educationId']").val();
                    var degreeId = $(n).find("select[name='degreeId']").val();
                    var titleId = $(n).find("select[name='titleId']").val();
                    var data = {
                        "authorFlow": authorFlow,
                        "authorName": authorName,
                        "typeName": typeName,
                        "sexId": sexId,
                        "educationId": educationId,
                        "degreeId": degreeId,
                        "titleId": titleId
                    };
                    datas.push(data);
                });
                var fileDatas = [];
                $.each(fileTr, function (i, n) {
                    var fileFlow = $(n).find("input[name='fileFlow']").val();
                    var fileRemark = $(n).find("input[name='fileRemark']").val();
                    var pubFlie = {
                        "fileFlow": fileFlow,
                        "fileRemark": fileRemark
                    };
                    fileDatas.push(pubFlie);
                });
                var requestData = {'authorList': datas, 'sat': sat, 'fileList': fileDatas};
                $("#jsondata").val(JSON.stringify(requestData));
                //alert($("#jsondata").val());
                var url = "<s:url value='/fstu/aisr/sat/save'/>";
                jboxStartLoading();
                jboxSubmit($("#satForm"), url, function (resp) {
                    window.parent.frames['mainIframe'].location.reload(true);
                    jboxClose();
                }, function (resp) {
                    jboxTip(resp);
                });
            });

        }

        function addAuthor() {
            $('#appendTbody').append($("#addAuthorTable tr:eq(0)").clone());
        }

        function delAuthorTr(obj) {
            jboxConfirm("确认删除？", function () {
                obj.parentNode.parentNode.remove();
            });
        }

        //删除作者
        function delAuthor(authorFlow, obj) {
            var url = '<s:url value="/fstu/aisr/sat/deleteAuthor?authorFlow="/>' + authorFlow;
            jboxConfirm("确认删除？", function () {
                jboxGet(url, null, function () {
                    var tr = $(obj).parent("td").parent("tr");
                    tr.remove();
                }, null, true);
            });
        }

        function reCheck(obj) {
            $(obj).hide();
            $("#down").hide();
            $("#file").show();
        }

        $(function () {
            <c:if test="${param.editFlag == GlobalConstant.FLAG_N}">
//            $('#look input[type!=button]').attr('disabled', "disabled");
//            $('#look textarea').attr('readonly', "readonly");
//            $('#look select').attr('disabled', "disabled");
            $("#reCheck").css("display", "none");
            $("#look a[id!=down]").css({color:"gray",cursor:"default"}).removeAttr("onclick");
            $("#addButton").hide();
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
                jboxTip("当前字符超过限制");
                $(obj).val("");
            }
        }
        <c:if test="${param.editFlag != GlobalConstant.FLAG_N}">
        $(document).ready(function () {
            if ($("#projFileTb tr").length <= 0) {
                addFile('projFileTb');
            }
        });
        </c:if>
        function addFile(tb) {
            if ($("#projFileTb tr").length > 9) {
                jboxTip("附件总数不能超过10个！");
                return false;
            }
            var html = '<tr>' +
                    '<td><input type="file" id="file" name="files" class="validate[required]"/></td>' +
                    '<td><input class="validate[required,maxSize[100]] xltext" style="width: 97%;" name="fileRemark" type="text"/></td>' +
                    '<td><a class="reCheck" href="javascript:void(0);" onclick="delTr(this);">[删除]</a></td>' +
                    '</tr>';
            $('#' + tb).append(html);
        }

        function delTr(tb) {
            jboxConfirm("确认删除？", function () {
                $(tb).parent('td').parent("tr").remove();
            });
        }

        function delFile(obj,fileFlow){
            var url = '<s:url value="/pub/file/delFileByFlow?fileFlow="/>' + fileFlow;
            jboxConfirm("确认删除？", function () {
                jboxGet(url, null, function () {
                    var tr = $(obj).parent("td").parent("tr");
                    tr.remove();
                }, null, true);
            });
        }
        function backOpt(){
            var url = "<s:url value='/fstu/aisr/sat/saveAudit?satFlow=${param.satFlow}&agreeFlag=N'/>";
            jboxConfirm("确认退回?" ,  function(){
                jboxStartLoading();
                jboxPost(url , null , function(resp){
                    window.parent.frames['mainIframe'].location.reload();
                    jboxClose();
                } , null , true);
            });
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <div id="look">
                <form id="satForm" method="post" enctype="multipart/form-data" style="position: relative;">
                    <input id="jsondata" type="hidden" name="jsondata" value=""/>
                    <table class="basic" style="width: 100%;">
                        <tr class="bs_name" style="height: 26px">
                            <td style="text-align:left" colspan="8">基本信息</td>
                        </tr>
                        <tr>
                            <th style="width: 17%;">科技名称：</th>
                            <td colspan="5">
                                <input type="hidden" name="satFlow" value="${sat.satFlow}"/>
                                <input class="validate[required,maxSize[100]] name xltext" style="width: 97%;"
                                       name="satName" type="text" value="${sat.satName}"/>
                            </td>
                        </tr>
                        <tr>
                            <th>所属单位：</th>
                            <td>
                                <c:forEach var="dict" items="${dictTypeEnumSatOrgList}">
                                    <input type="radio" name="orgBelongId" value="${dict.dictId}"
                                           id="orgBelongId_${dict.dictId}"
                                           <c:if test="${sat.orgBelongId==dict.dictId}">checked="checked"</c:if>/><label
                                        for="orgBelongId_${dict.dictId}">&nbsp;${dict.dictName}</label>&#12288;
                                </c:forEach>
                            </td>
                            <th>项目来源：</th>
                            <td>
                                <select name="projSourceId" class="xlselect" style="width: 208px;">
                                    <option value="">请选择</option>
                                    <c:forEach var="dict" items="${dictTypeEnumProSourceList}">
                                        <option value="${dict.dictId}"
                                                <c:if test="${dict.dictId eq sat.projSourceId}">selected</c:if>>${dict.dictName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th>成果形式：</th>
                            <td>
                                <select class="xlselect" name="achTypeId" style="width: 168px;margin-top: 6px">
                                    <option value="">请选择</option>
                                    <c:forEach var="dict" items="${dictTypeEnumSatTypeList}">
                                        <option value="${dict.dictId}"
                                                <c:if test="${sat.achTypeId==dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <th>获奖日期：</th>
                            <td colspan="3">
                                <input style="width: 200px" readonly="readonly"
                                       onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="ctime" name="prizedDate"
                                       type="text" value="${sat.prizedDate}"/>
                            </td>
                        </tr>
                        <tr>
                            <th>获奖级别：</th>
                            <td>
                                <select class="xlselect" name=prizedGradeId style="width: 168px;">
                                    <option value="">请选择</option>
                                    <c:forEach var="dict" items="${dictTypeEnumSatGradeList}">
                                        <option value="${dict.dictId}"
                                                <c:if test="${sat.prizedGradeId==dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <th>获奖等级：</th>
                            <td colspan="3">
                                <select class="xlselect" name="prizedLevelId" style="width: 208px;">
                                    <option value="">请选择</option>
                                    <c:forEach var="dict" items="${dictTypeEnumSatLevelList}">
                                        <option value="${dict.dictId}"
                                                <c:if test="${sat.prizedLevelId==dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th>分值：</th>
                            <td>
                                <input class="xltext validate[custom[number]]" type="text" name="scoreValue" value="${sat.scoreValue}" style="margin-right: 0px;">
                            </td>
                            <th>奖励批准号：</th>
                            <td colspan="3">
                                <input type="text" class="xltext" style="text-align: left; width: 200px"
                                       name="approvalCode" value="${sat.approvalCode}"/>
                            </td>
                        </tr>
                    </table>


                    <table class="basic" style="width:100%;">
                        <thead>
                        <tr>
                            <th colspan="4" class="bs_name">备注信息</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <th width="17%">备注信息：</th>
                            <td>
                                <textarea class="xltxtarea" placeholder="50个字以内" style="margin-left: 0px;"
                                          name="remark" onblur="strChar(this,100)">${sat.remark }</textarea>
                            </td>
                        </tr>
                        </tbody>
                    </table>

                    <table class="basic" style="width:100%;">
                        <thead>
                        <tr>
                            <th colspan="4" class="bs_name">附件${(param.editFlag != GlobalConstant.FLAG_N)?'上传':'查看'}
                                <c:if test="${param.editFlag != GlobalConstant.FLAG_N}">
                                    <span style="float: right;padding-right: 10px"><a href="javascript:void(0)"><img
                                            title="新增" src="<s:url value='/'/>css/skin/${skinPath}/images/add.png"
                                            style="cursor: pointer;" onclick="addFile('projFileTb')"/></a></span>
                                </c:if>
                            </th>
                        </tr>
                        <tr>
                            <td width="30%" style="font-weight:bold;">附件名称</td>
                            <td width="40%" style="font-weight:bold;">附件内容</td>
                            <c:if test="${param.editFlag != GlobalConstant.FLAG_N}">
                                <td width="20%" style="font-weight:bold;">附件操作</td>
                            </c:if>
                        </tr>
                        </thead>
                        <tbody id="projFileTb">
                        <c:forEach var="file" items="${fileList}" varStatus="status">
                            <tr>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty file.fileFlow}">
                                            <a id="down"
                                               href='<s:url value="/fstu/book/fileDown?fileFlow=${file.fileFlow}"/>'>${file.fileName}</a>
                                            <input type="file" id="file" name="files" style="display: none;"/>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="file" id="file" name="files"/>
                                        </c:otherwise>
                                    </c:choose>
                                    <input type="hidden" name="fileFlow" value="${file.fileFlow}"/>
                                </td>
                                <td>
                                    <input class="validate[required,maxSize[100]] xltext" style="width: 97%;"
                                           name="fileRemark" type="text" value="${file.fileRemark}"/>
                                </td>
                                <c:if test="${param.editFlag != GlobalConstant.FLAG_N}">
                                    <td>
                                        <a class="reCheck" href="javascript:void(0);" onclick="reCheck(this);">[重新选择文件]</a>&#12288;
                                        <a class="reCheck" href="javascript:void(0);" onclick="delFile(this,'${file.fileFlow}');">[删除]</a>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </form>

                <form id="pubFile">
                    <input type="hidden" name="fileFlow" value="${file.fileFlow}"/>
                </form>

                <form id="authorList">
                    <table class="basic" style="width: 100%;">
                        <tr class="bs_name" style="height: 26px">
                            <td style="text-align:left" colspan="8">科技作者
                                        <a id="addButton" href="javascript:void(0)">
                                            <img src="<s:url value='/'/>css/skin/${skinPath}/images/add.png"
                                                 onclick="addAuthor();"
                                                 title="添加"/>
                                        </a>
                            </td>
                        </tr>
                        <tr>
                            <th style="text-align: center; width: 10%">署名顺序</th>
                            <th style="text-align: center; width: 15%">人员名称</th>
                            <th style="text-align: center; width: 15%">性别</th>
                            <th style="text-align: center; width: 15%">学历</th>
                            <th style="text-align: center; width: 15%">学位</th>
                            <th style="text-align: center; width: 15%">职称</th>
                            <c:if test="${param.editFlag != GlobalConstant.FLAG_N}">
                            <th width="10%" style="text-align: center;">操作</th>
                            </c:if>
                        </tr>

                        <tbody id="appendTbody">
                        <%-- 默认作者 --%>
                        <c:if test="${empty param.satFlow}">
                            <tr>
                                <td>
                                    <input class="xltext" style="width: 85%;" type="text" name="typeName"/>
                                </td>
                                <td>
                                    <input class="xltext" style="width: 85%;" type="text" name="authorName"
                                           value="${sessionScope.currUser.userName}"/>
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
                                    <select name="educationId" class="xlselect" style="width: 85%;">
                                        <option value="">请选择</option>
                                        <c:forEach var="dict" items="${dictTypeEnumUserEducationList}">
                                            <option value="${dict.dictId }"
                                                    <c:if test="${sessionScope.currUser.educationId eq dict.dictId }">selected="selected"</c:if>>${dict.dictName }</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <select name="degreeId" class="xlselect" style="width: 85%;">
                                        <option value="">请选择</option>
                                        <c:forEach var="dict" items="${dictTypeEnumUserDegreeList}">
                                            <option value="${dict.dictId }"
                                                    <c:if test="${sessionScope.currUser.degreeId eq dict.dictId }">selected="selected"</c:if>>${dict.dictName }</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <select name="titleId" class="xlselect" style="width: 85%;">
                                        <option value="">请选择</option>
                                        <c:forEach var="dict" items="${dictTypeEnumUserTitleList}">
                                            <option value="${dict.dictId }"
                                                    <c:if test="${sessionScope.currUser.titleId eq dict.dictId }">selected="selected"</c:if>>${dict.dictName }</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <c:if test="${param.editFlag != GlobalConstant.FLAG_N}">
                                    <td style="text-align: center;">
                                        [<a onclick="delAuthorTr(this)" style="cursor: pointer;">删除</a>]
                                    </td>
                                </c:if>
                            </tr>
                        </c:if><%-- 默认作者 End--%>


                        <c:forEach items="${satAuthorList}" var="author">
                            <td>
                                <input class="xltext" style="width: 85%;" type="text" name="typeName"
                                       value="${author.typeName}"/>
                            </td>
                            <td style="text-align: center;">
                                <input class="xltext" style="width: 85%;" type="text" name="authorName"
                                       value="${author.authorName}"/>
                                <input type="hidden" name="authorFlow" value="${author.authorFlow}"/>
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
                                <select name="educationId" class="xlselect" style="width: 85%;">
                                    <option value="">请选择</option>
                                    <c:forEach var="dict" items="${dictTypeEnumUserEducationList }">
                                        <option value="${dict.dictId }"
                                                <c:if test="${author.educationId eq dict.dictId }">selected="selected"</c:if>>${dict.dictName }</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <select name="degreeId" class="xlselect" style="width: 85%;">
                                    <option value="">请选择</option>
                                    <c:forEach var="dict" items="${dictTypeEnumUserDegreeList }">
                                        <option value="${dict.dictId }"
                                                <c:if test="${author.degreeId eq dict.dictId }">selected="selected"</c:if>>${dict.dictName }</option>
                                    </c:forEach>
                                </select>
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
                    <c:if test="${param.editFlag != GlobalConstant.FLAG_N}">
                        <input type="button" value="保&#12288;存" onclick="saveSatAndAuthor()" class="search"/>
                        <input type="button" value="关&#12288;闭" onclick="jboxClose();" class="search"/>
                    </c:if>
                    <c:if test="${sat.operStatusId eq 'Passed'}">
                        <input type="button" value="退&#12288;回" onclick="backOpt();" class="search"/>
                        <input type="button" value="关&#12288;闭" onclick="jboxClose();" class="search"/>
                    </c:if>
                </p>


                <%-- 克隆作者--%>
                <table id="addAuthorTable" style="display: none" class="basic" style="width: 100%;">
                    <tr>
                        <td>
                            <input class="xltext" style="width: 85%;" type="text" name="typeName"/>
                        </td>
                        <td>
                            <input class="validate[required] xltext" style="width: 85%;" name="authorName" type="text"/>
                        </td>
                        <td>
                            <select class="validate[required] xlselect" style="width: 85%;" name="sexId">
                                <option value="">请选择</option>
                                <c:forEach var="dict" items="${userSexEnumList}">
                                    <c:if test="${dict.id != userSexEnumUnknown.id}">
                                        <option value="${dict.id}">${dict.name}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <select class="xlselect" name="educationId" style="width: 85%;">
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
                                    <option value="${dict.dictId }">${dict.dictName}</option>
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
                        <td style="text-align: center;">
                            <input type="hidden" name="authorFlow" value="${param.authorFlow}"/>
                            <input type="hidden" name="satFlow" value="${param.satFlow}"/>
                            [<a onclick="delAuthorTr(this)" style="cursor: pointer;">删除</a>]
                        </td>
                    </tr>
                </table>
            </div>
       