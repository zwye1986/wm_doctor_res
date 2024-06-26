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
        function savePat() {
            if (false == $("#srmPatent").validationEngine("validate")) {
                return false;
            }
            if (false == $("#srmPatentAuthor").validationEngine("validate")) {
                return false;
            }
            if(patentIsExist){
                return false;
            }
            var patent = $("#srmPatent").serializeJson();
            var achFile = $("#srmAchFile").serializeJson();
            var authTab = $('#test');
            var trs = authTab.children();
            var datas = [];
            $.each(trs, function (i, n) {
                var authorFlow = $(n).find("input[name='authorFlow']").val();
                var typeName = $(n).find("select[name='typeName']").val();
                var authorName = $(n).find("input[name='authorName']").val();
                var sexId = $(n).find("select[name='sexId']").val();
                var educationId = $(n).find("select[name='educationId']").val();
                var titleId = $(n).find("select[name='titleId']").val();
                var degreeId = $(n).find("select[name='degreeId']").val();
                var userFlow =  $(n).find("input[name='userFlow']").val();
                var deptFlow = $(n).find("input[name='deptFlow']").val();
                var deptName = $(n).find("input[name='deptName']").val();
                var data = {
                    "authorFlow": authorFlow,
                    "typeName": typeName,
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
            var t = {'achPatentList': datas, 'patent': patent, "srmAchFile": achFile};
            $("#jsondata").val(JSON.stringify(t));
            var url = "<s:url value='/srm/ach/patent/save/${roleScope}'/>";
            jboxStartLoading();
            jboxSubmit($('#srmPatent'), url, function (resp) {

                    },
                    function (resp) {
                        alert("error");
                    }, false);
            return true;
        }
        function savePatent() {
            if (savePat()) {
                jboxTip("保存成功");
                setTimeout("window.parent.frames['mainIframe'].location.reload(true);jboxClose();", 2000)
                //
            }
        }
        function delAuthorTr(obj) {
            jboxConfirm("确认删除？", function () {
                var tr = obj.parentNode.parentNode;
                tr.remove();
            });
        }

        function delAuthor(authorFlow, obj) {
            var url = '<s:url value="/srm/ach/patent/deleteAuthor?authorFlow="/>' + authorFlow;
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
        $(function () {
            var url = "<s:url value='/srm/ach/topic/searchDept'/>";
            var selectedFlow = "${patent.deptFlow}";
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
                $('#test').append(tr);
            });

        }
        $(document).ready(function () {
            var authorize = $("#authorizeCode").val();
            if (authorize == null || authorize == "" || authorize == undefined) {
                return false;
            }
            var pages = authorize.split(" ");
            $("#codeYear").val(pages[1]);
            $("#codeType").val(pages[2]);
            $("#codeNum").val(pages[3]);
            $("#codeCheck").val(pages[5]);
        });
        function buildCode(){
            var codeYear = $("#codeYear").val();
            var codeType = $("#codeType").val();
            var codeNum = $("#codeNum").val();
            var codeCheck = $("#codeCheck").val();
            $("#authorizeCode").val("ZL"+" "+codeYear+" "+codeType+" "+codeNum+" . "+codeCheck);
            validatePatentCode();
        }
        var patentIsExist ;
        function validatePatentCode(){
            var authorizeCode = '${patent.authorizeCode}';
            patentIsExist = false;
            $("#patentMessage").text("");
            var changeCode = $("#authorizeCode").val().trim();
           // alert(changeCode);
            if(authorizeCode == changeCode){
                return true;
            }else {
                var url = "<s:url value='/srm/ach/patent/patentIsExist'/>";
                var data = {"authorizeCode":changeCode};
                jboxPost(url, data, function (resp) {
                    if(resp){
                        patentIsExist = true;
                        $("#patentMessage").text("该专利已存在，如需增加作者信息，请联系登记人'"+resp.applyUserName+"'！");
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
                <form id="srmPatent" style="position: relative;" enctype="multipart/form-data" method="post">
                    <input id="jsondata" type="hidden" name="jsondata" value=""/>
                    <c:if test="${(param.editFlag != GlobalConstant.FLAG_N) and (param.editFlag != 'audit')}">
                        <input type="hidden" name="applyUserFlow" value="${sessionScope.currUser.userFlow}"/>
                        <input type="hidden" name="applyUserName" value="${sessionScope.currUser.userName}"/>
                        <input type="hidden" name="applyOrgFlow" value="${sessionScope.currUser.orgFlow}"/>
                        <input type="hidden" name="applyOrgName" value="${sessionScope.currUser.orgName}"/>
                    </c:if>
                    <table class="basic" style="width: 100%">
                        <colgroup>
                            <col width="17%">
                            <col width="33%">
                            <col width="17%">
                            <col width="33%">
                        </colgroup>
                        <tr class="bs_name" style="height: 26px">
                            <td colspan="4">基本信息</td>
                        </tr>
                        <tr>
                            <th width="17%"><span class="redspan"
                                                  style="color: red;padding: 0px;">*</span>专利名称：
                            </th>
                            <td colspan="3">
                                <input type="hidden" name="patentFlow" value="${patent.patentFlow }">
                                <input class="validate[required,maxSize[100]] name xltext" type="text" name="patentName"
                                       value="${patent.patentName }" style="width: 97%;"></td>
                        </tr>
                        <tr>
                            <th><span class="redspan" style="color: red;padding: 0px;">*</span>所属单位：
                            </th>
                            <td>
                                <c:forEach var="dict" items="${dictTypeEnumOrgBelongList }">
                                    <input type="radio" name="orgBelongId" class="validate[required]"
                                           <c:if test="${patent.orgBelongId eq dict.dictId }">checked="checked"</c:if>
                                           id="patentOrgBelong_${dict.dictId }" value="${dict.dictId }"><label
                                        for="patentOrgBelong_${dict.dictId }">&nbsp;${dict.dictName}</label>&nbsp;
                                </c:forEach>
                            </td>
                            <th><span class="redspan" style="color: red;padding: 0px;">*</span>所在科室：
                            </th>
                            <td>
                                <input type="hidden" id="deptName" name="deptName" value="${patent.deptName}"/>
                                <select name="deptFlow" id="selectDept" class="xlselect validate[required]"
                                        onchange="getDeptName(this);">
                                    <option value="">请选择</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th><span class="redspan" style="color: red;padding: 0px;">*</span>专利类型：
                            </th>
                            <td>
                                <c:forEach var="dict" items="${dictTypeEnumPatentTypeList }">
                                    <input type="radio" name="typeId" class="validate[required]"
                                           <c:if test="${patent.typeId eq dict.dictId }">checked="checked"</c:if>
                                           id="patentType_${dict.dictId }" value="${dict.dictId }"><label
                                        for="patentType_${dict.dictId }">&nbsp;${dict.dictName }</label>&nbsp;
                                </c:forEach>
                            </td>
                            <th><span class="redspan" style="color: red;padding: 0px;">*</span>专利范围：
                            </th>
                            <td>
                                <c:forEach var="dict" items="${dictTypeEnumPatentRangeList }">
                                    <input type="radio" name="rangeId" class="validate[required]"
                                           <c:if test="${patent.rangeId eq dict.dictId }">checked="checked"</c:if>
                                           id="patentRange_${dict.dictId }" value="${dict.dictId }"><label
                                        for="patentRange_${dict.dictId }">&nbsp;${dict.dictName }</label>&nbsp;
                                </c:forEach>
                            </td>
                        </tr>
                        <tr>
                            <th><span class="redspan" style="color: red;padding: 0px;">*</span>专利权人：
                            </th>
                            <td><input type="text" class="xltext validate[required,maxSize[50]]" name="patentee"
                                       value="${patent.patentee }"></td>
                            <th><span class="redspan" style="color: red;padding: 0px;">*</span>是否为职务专利：
                            </th>
                            <td>
                                <input type="radio" name="isPost" id="isPost_1" class="validate[required]"
                                       <c:if test="${patent.isPost eq 1 }">checked="checked"</c:if> value="1"><label
                                    for="isPost_1">&nbsp;是</label>&nbsp;
                                <input type="radio" name="isPost" id="isPost_0" class="validate[required]"
                                       <c:if test="${patent.isPost eq 0 }">checked="checked"</c:if> value="0"><label
                                    for="isPost_0">&nbsp;否</label>&nbsp;
                            </td>
                        </tr>
                        <tr>
                            <th><span class="redspan" style="color: red;padding: 0px;">*</span>授权日期：
                            </th>
                            <td><input class="xltext ctime validate[required]" style="width:160px;" type="text"
                                       name="authorizeDate"
                                       value="${patent.authorizeDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
                                       readonly="readonly"></td>
                            <th><span class="redspan" style="color: red;padding: 0px;">*</span>专利号：
                            </th>
                            <td>
                                <span style="width: 30px"> ZL </span>
                                年号<input class="inputText validate[required,custom[number],maxSize[4]]" type="text" id="codeYear" onchange="buildCode()" style="width: 50px">
                                种类<input class="inputText validate[required,custom[number],maxSize[4]]" type="text" id="codeType" onchange="buildCode()" style="width: 50px">
                                顺序号<input class="inputText validate[required,custom[number],maxSize[8]]" type="text" id="codeNum" onchange="buildCode()" style="width: 50px"> ．
                                校验位<input class="inputText validate[required,custom[number],maxSize[4]]" type="text" id="codeCheck" onchange="buildCode()" style="width: 50px">
                                <p id="patentMessage" style="color: #f00"></p>
                                <input class="inputText validate[required]" type="hidden" id="authorizeCode" name="authorizeCode"
                                       value="${patent.authorizeCode }">
                            </td>
                        </tr>
                        <tr>
                            <th><span class="redspan" style="color: red;padding: 0px;">*</span>合作类型：
                            </th>
                            <td>
                                <c:forEach var="dict" items="${dictTypeEnumPatentCooperTypeList }">
                                    <input type="radio" name="cooperTypeId" class="validate[required]"
                                           <c:if test="${patent.cooperTypeId eq dict.dictId }">checked="checked"</c:if>
                                           id="patentCooperType_${dict.dictId }" value="${dict.dictId }"><label
                                        for="patentCooperType_${dict.dictId }">&nbsp;${dict.dictName }</label>&nbsp;
                                </c:forEach>
                            </td>
                            <th><span class="redspan" style="color: red;padding: 0px;">*</span>获得年份：
                            </th>
                            <td><input class="xltext ctime validate[required]" style="width:160px;" type="text"
                                       name="authorizeYear"
                                       value="${patent.authorizeYear }" onClick="WdatePicker({dateFmt:'yyyy'})"
                                       readonly="readonly"></td>
                        </tr>
                        <tr>
                            <th>申请日期：</th>
                            <td><input class="xltext ctime" style="width:160px;" type="text" name="applyDate"
                                       value="${patent.applyDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
                                       readonly="readonly"></td>
                            <th>申请号：</th>
                            <td><input class="xltext validate[custom[onlyLetterNumber],maxSize[20]]" type="text" name="applyCode" value="${patent.applyCode }"></td>
                        </tr>
                        <tr>
                            <th>公开日期：</th>
                            <td><input class="xltext ctime" style="width:160px;" type="text" name="openDate"
                                       value="${patent.openDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
                                       readonly="readonly"></td>
                            <th>公开号：</th>
                            <td><input class="xltext validate[custom[onlyLetterNumber],maxSize[20]]" type="text" name="openCode" value="${patent.openCode }"></td>
                        </tr>
                        <tr>
                            <th>是否为PTC专利：</th>
                            <td>
                                <input type="radio" name="isPtc" id="isPtc_1"
                                       <c:if test="${patent.isPtc eq 1 }">checked="checked"</c:if> value="1"><label
                                    for="isPtc_1">&nbsp;是</label>&nbsp;
                                <input type="radio" name="isPtc" id="isPtc_0"
                                       <c:if test="${patent.isPtc eq 0 }">checked="checked"</c:if> value="0"><label
                                    for="isPtc_0">&nbsp;否</label>&nbsp;
                            </td>
                            <th>是否失效：</th>
                            <td>
                                <input type="radio" name="isExpired" id="isExpired_1"
                                       <c:if test="${patent.isExpired eq 1 }">checked="checked"</c:if> value="1"><label
                                    for="isExpired_1">&nbsp;是</label>&nbsp;
                                <input type="radio" name="isExpired" id="isExpired_0"
                                       <c:if test="${patent.isExpired eq 0 }">checked="checked"</c:if> value="0"><label
                                    for="isExpired_0">&nbsp;否</label>&nbsp;
                            </td>
                        </tr>
                        <tr>
                            <th>专利状态：</th>
                            <td colspan="3">
                                <c:forEach var="dict" items="${dictTypeEnumPatentStatusList }">
                                    <input type="radio" name="statusId"
                                           <c:if test="${patent.statusId eq dict.dictId }">checked="checked"</c:if>
                                           id="patentStatus_${dict.dictId }" value="${dict.dictId }"><label
                                        for="patentStatus_${dict.dictId }">&nbsp;${dict.dictName }</label>&nbsp;
                                </c:forEach>
                            </td>
                        </tr>
                    </table>


                    <table class="basic" style="width: 100%">
                        <thead>
                        <tr>
                            <th colspan="4" class="bs_name">附件上传（上传的附件为证书扫描件，格式为JPG格式）</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <th width="17%">附件：</th>
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
                                        <input type="file" id="file" name="file"/>
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
                            <th width="17%">备注信息：</th>
                            <td>
                                <textarea class="xltxtarea validate[maxSize[200]]" style="margin-left: 0px;" placeholder="200个字符以内"
                                          name="remark">${patent.remark}</textarea>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </form>

                <form id="srmAchFile">
                    <input type="hidden" name="fileFlow" value="${file.fileFlow }"/>
                </form>

                <!-- 专利作者表单 -->
                <form id="srmPatentAuthor">
                    <table class="basic" id="mubiao" style="width: 100%">
                        <tr>
                            <td class="bs_name" colspan="7">专利作者 <c:if
                                    test="${param.editFlag != GlobalConstant.FLAG_N}"><a
                                    href="javascript:void(0)"><img
                                    src="<s:url value='/'/>css/skin/${skinPath}/images/add.png"
                                    onclick="selectAuthor();" title="添加"/></a></c:if></td>
                        </tr>
                        <tr>
                            <th width="20%;" style="text-align: center;">署名顺序</th>
                            <th width="15%;" style="text-align: center;">发明(设计)人姓名</th>
                            <th width="10%;" style="text-align: center;">性别</th>
                            <th width="15%;" style="text-align: center;">学历</th>
                            <th width="10%;" style="text-align: center;">学位</th>
                            <th width="15%;" style="text-align: center;">职称</th>
                            <c:if test="${param.editFlag != GlobalConstant.FLAG_N}">
                                <th style="text-align: center;" style="text-align: center;">操作</th>
                            </c:if>
                        </tr>
                        <tbody id="test">
                        <%-- 默认作者 --%>
                        <c:if test="${empty param.patentFlow}">
                            <tr>
                                <td>
                                    <select name="typeName" class="xlselect" style="width: 85%;">
                                        <option value="">请选择</option>
                                        <c:forEach var="dict" items="${dictTypeEnumApplyUserTypeList }">
                                            <option value="${dict.dictName }" >${dict.dictName }</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <input class="xltext" style="width: 85%;" readonly="readonly" type="text"
                                           name="authorName" value="${sessionScope.currUser.userName }"/>
                                    <input type="hidden" name="userFlow" value="${sessionScope.currUser.userFlow}"/>
                                    <input type="hidden" name="deptFlow" value="${sessionScope.currUser.deptFlow}"/>
                                    <input type="hidden" name="deptName" value="${sessionScope.currUser.deptName}"/>
                                </td>
                                <td>
                                    <select name="sexId" class="validate[required] xlselect" style="width: 85%;">
                                        <option value="">请选择</option>
                                        <c:forEach var="dict" items="${userSexEnumList}">
                                            <c:if test="${dict.id != userSexEnumUnknown.id}">
                                                <option value="${dict.id }"
                                                        <c:if test="${sessionScope.currUser.sexId eq dict.id }">selected="selected"</c:if>>${dict.name }</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
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
                                    <select name="degreeId" class="xlselect" style="width: 85%;">
                                        <option value="">请选择</option>
                                        <c:forEach var="dict" items="${dictTypeEnumUserDegreeList }">
                                            <option value="${dict.dictId }"
                                                    <c:if test="${sessionScope.currUser.degreeId eq dict.dictId }">selected="selected"</c:if>>${dict.dictName }</option>
                                        </c:forEach>
                                    </select>
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
                                <c:if test="${param.editFlag != GlobalConstant.FLAG_N}">
                                    <td style="text-align: center;">
                                        [<a onclick="delAuthorTr(this)" style="cursor: pointer;">删除</a>]
                                    </td>
                                </c:if>
                            </tr>
                        </c:if>

                        <c:forEach var="author" items="${authorList }">
                            <td>
                                <input type="hidden" name="authorFlow" value="${author.authorFlow }">
                                <select name="typeName" class="xlselect" style="width: 85%;">
                                    <option value="">请选择</option>
                                    <c:forEach var="dict" items="${dictTypeEnumApplyUserTypeList }">
                                        <option value="${dict.dictName }"
                                                <c:if test="${author.typeName eq dict.dictName }">selected="selected"</c:if>>${dict.dictName }</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <input class="xltext" style="width: 85%;" type="text" name="authorName"
                                       readonly="readonly" value="${author.authorName }"/>
                                <input type="hidden" name="userFlow" value="${author.userFlow}"/>
                                <input type="hidden" name="deptFlow" value="${author.deptFlow}"/>
                                <input type="hidden" name="deptName" value="${author.deptName}"/>
                            </td>
                            <td>
                                <select name="sexId" class="validate[required] xlselect" style="width: 85%;">
                                    <option value="">请选择</option>
                                    <c:forEach var="dict" items="${userSexEnumList}">
                                        <c:if test="${dict.id != userSexEnumUnknown.id}">
                                            <option value="${dict.id }"
                                                    <c:if test="${author.sexId eq dict.id }">selected="selected"</c:if>>${dict.name }</option>
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
                                    [<label onclick="delAuthor('${author.authorFlow}',this)">删除</label>]
                                </td>
                            </c:if>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </form>

                <!-- 作者模板 -->
                <table class="basic" id="moban" style="display: none" style="width: 100%">
                    <tr>
                        <td style="text-align: center;">
                            <select name="typeName" class="xlselect" style="width: 85%;">
                                <option value="">请选择</option>
                                <c:forEach var="dict" items="${dictTypeEnumApplyUserTypeList }">
                                    <option value="${dict.dictName }">${dict.dictName }</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td style="text-align: center;">
                            <input class="validate[required] xltext" style="width: 85%;" type="text" readonly="readonly"
                                   name="authorName" value="${author.authorName }"/>
                            <input type="hidden" name="userFlow" />
                            <input type="hidden" name="deptFlow" />
                            <input type="hidden" name="deptName" />
                        </td>
                        <td>
                            <select name="sexId" class="validate[required] xlselect" style="width: 85%;">
                                <option value="">请选择</option>
                                <c:forEach var="dict" items="${userSexEnumList }">
                                    <c:if test="${dict.id != userSexEnumUnknown.id}">
                                        <option value="${dict.id }">${dict.name}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </td>
                        <td style="text-align: center;">
                            <select name="educationId" class="xlselect" style="width: 85%;">
                                <option value="">请选择</option>
                                <c:forEach var="dict" items="${dictTypeEnumUserEducationList }">
                                    <option value="${dict.dictId }">${dict.dictName }</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td style="text-align: center;">
                            <select name="degreeId" class="xlselect" style="width: 85%;">
                                <option value="">请选择</option>
                                <c:forEach var="dict" items="${dictTypeEnumUserDegreeList }">
                                    <option value="${dict.dictId }">${dict.dictName }</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td style="text-align: center;">
                            <select name="titleId" class="xlselect" style="width: 85%;">
                                <option value="">请选择</option>
                                <c:forEach var="dict" items="${dictTypeEnumUserTitleList }">
                                    <option value="${dict.dictId }">${dict.dictName }</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td style="text-align: center;">
                            [<label onclick="delAuthorTr(this)">删除</label>]
                        </td>
                    </tr>
                </table>

                <p align="center">
                    <c:if test="${(param.editFlag != GlobalConstant.FLAG_N) and (param.editFlag != 'audit')}">
                        <input type="button" onClick="savePatent();" value="保&#12288;存" class="search"/>
                        <input type="button" value="关&#12288;闭" onclick="jboxClose();" class="search"/>
                    </c:if>

                </p>

            </div>
        </div>
    </div>
</div>
</body>
</html>