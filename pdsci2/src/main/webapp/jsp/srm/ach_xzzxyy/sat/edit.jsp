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
            if(satIsExist){
                return false;
            }
            var sat = $("#satForm").serializeJson();
            var trs = $('#appendTbody').children();
            var achFile = $("#srmAchFile").serializeJson();
            var datas = [];
            //var j = $("#satForm").serializeJson();
            $.each(trs, function (i, n) {
                var authorFlow = $(n).find("input[name='authorFlow']").val();
                var authorName = $(n).find("input[name='authorName']").val();
                var typeName = $(n).find("select[name='typeName']").val();
                var sexId = $(n).find("select[name='sexId']").val();
                var educationId = $(n).find("select[name='educationId']").val();
                var degreeId = $(n).find("select[name='degreeId']").val();
                var titleId = $(n).find("select[name='titleId']").val();
                var userFlow =  $(n).find("input[name='userFlow']").val();
                var deptFlow = $(n).find("input[name='deptFlow']").val();
                var deptName = $(n).find("input[name='deptName']").val();
                var data = {
                    "authorFlow": authorFlow,
                    "authorName": authorName,
                    "typeName": typeName,
                    "sexId": sexId,
                    "educationId": educationId,
                    "degreeId": degreeId,
                    "userFlow": userFlow,
                    "deptFlow": deptFlow,
                    "deptName": deptName,
                    "titleId": titleId
                };
                datas.push(data);
            });
            var requestData = {'authorList': datas, 'sat': sat, 'srmAchFile': achFile};
            $("#jsondata").val(JSON.stringify(requestData));
            //alert($("#jsondata").val());
            var url = "<s:url value='/srm/ach/sat/save/${roleScope}'/>";
            jboxStartLoading();
            jboxSubmit($("#satForm"), url, function (resp) {

            }, function (resp) {
                jboxTip(resp);
            }, false);
            return true;
        }
        function saveSat() {
            if (saveSatAndAuthor()) {
                jboxTip("保存成功");
                setTimeout("window.parent.frames['mainIframe'].location.reload(true);jboxClose();", 2000)
            }
        }
        function delAuthorTr(obj) {
            jboxConfirm("确认删除？", function () {
                obj.parentNode.parentNode.remove();
            });
        }

        //删除作者
        function delAuthor(authorFlow, obj) {
            var url = '<s:url value="/srm/ach/sat/deleteAuthor?authorFlow="/>' + authorFlow;
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
                jboxTip("当前字符超过限制");
                $(obj).val("");
            }
        }
        $(function () {
            var url = "<s:url value='/srm/ach/topic/searchDept'/>";
            var selectedFlow = "${sat.deptFlow}";
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
                var tr = $("#addAuthorTable tr:eq(0)").clone();
                $(tr).find("input[name='authorName']").val(n.userName);
                //alert($(tr).find("input[name='authorName']").val());
                $(tr).find("select[name='sexId']").val(n.sexId);
                $(tr).find("select[name='titleId']").val(n.titleId);
                $(tr).find("select[name='degreeId']").val(n.degreeId);
                $(tr).find("select[name='educationId']").val(n.educationId);
                $(tr).find("input[name='userFlow']").val(n.userFlow);
                $(tr).find("input[name='deptFlow']").val(n.deptFlow);
                $(tr).find("input[name='deptName']").val(n.deptName);
                $('#appendTbody').append(tr);
            });

        }

        function checkSatName (){
            var satNameCheck = $("#satNameCheck").val();
            if(satNameCheck == '其他自填'){
                $("#satName").val("");
                $("#satName").show();
            }else{
                $("#satName").hide();
                $("#satName").val("");
                $("#satName").val(satNameCheck);
            }
        }

        var satIsExist ;
        function validateApprovalCode(){
            var approvalCode = '${sat.approvalCode}';
            satIsExist = false;
            $("#satMessage").text("");
            var changeCode = $("#approvalCode").val().trim();
            // alert(changeCode);
            if(approvalCode == changeCode){
                return true;
            }else {
                var url = "<s:url value='/srm/ach/sat/satIsExist'/>";
                var data = {"approvalCode":changeCode};
                jboxPost(url, data, function (resp) {
                    if(resp){
                        satIsExist = true;
                        $("#satMessage").text("该奖项已存在，如需增加作者信息，请联系登记人'"+resp.applyUserName+"'！");
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
                <form id="satForm" method="post" enctype="multipart/form-data" style="position: relative;">
                    <input id="jsondata" type="hidden" name="jsondata" value=""/>
                    <c:if test="${(param.editFlag != GlobalConstant.FLAG_N) and (param.editFlag != 'audit')}">
                        <input type="hidden" name="applyUserFlow" value="${sessionScope.currUser.userFlow}"/>
                        <input type="hidden" name="applyUserName" value="${sessionScope.currUser.userName}"/>
                        <input type="hidden" name="applyOrgFlow" value="${sessionScope.currUser.orgFlow}"/>
                        <input type="hidden" name="applyOrgName" value="${sessionScope.currUser.orgName}"/>
                    </c:if>
                    <table class="basic" style="width: 100%;">
                        <colgroup>
                            <col width="17%" />
                            <col width="33%" />
                            <col width="17%" />
                            <col width="33%" />
                        </colgroup>
                        <tr class="bs_name" style="height: 26px">
                            <td style="text-align:left" colspan="8">基本信息</td>
                        </tr>
                        <tr>
                            <th style="width: 17%;"><span class="redspan"
                                                          style="color: red;padding: 0px;margin-left: 10px;">*</span>奖项名称：
                            </th>
                            <td colspan="5">
                                <input type="hidden" name="satFlow" value="${sat.satFlow}"/>
                                <select class="xlselect validate[required]" id="satNameCheck" onchange="checkSatName()" style="width: 25%">
                                    <option value="">请选择</option>
                                    <option <c:if test="${sat.satName eq '省新技术引进奖'}">selected="selected"</c:if> value="省新技术引进奖">省新技术引进奖</option>
                                    <option <c:if test="${sat.satName eq '市新技术引进奖'}">selected="selected"</c:if> value="市新技术引进奖">市新技术引进奖</option>
                                    <option <c:if test="${sat.satName eq '市科技进步奖'}">selected="selected"</c:if> value="市科技进步奖">市科技进步奖</option>
                                    <option <c:if test="${(not empty sat.satName) and (sat.satName ne '省新技术引进奖') and (sat.satName ne '市新技术引进奖') and (sat.satName ne '市科技进步奖')}">selected="selected"</c:if> value="其他自填">其他自填</option>
                                </select>
                                <input class="validate[required,maxSize[100]] name xltext" style="text-align: left;width: 47%;<c:if test="${(empty sat.satName) or (sat.satName eq '省新技术引进奖') or (sat.satName eq '市新技术引进奖') or (sat.satName eq '市科技进步奖')}">display: none</c:if>"
                                       name="satName" type="text" value="${sat.satName}" id="satName"/>
                            </td>
                        </tr>
                        <tr>
                            <th style="width: 17%;"><span class="redspan"
                                                          style="color: red;padding: 0px;margin-left: 10px;">*</span>课题名称：
                            </th>
                            <td colspan="5">
                                <input class="validate[required,maxSize[150]] name xltext" style="width: 97%;"
                                       name="subjectName" type="text" value="${sat.subjectName}"/>
                            </td>
                        </tr>
                        <tr>
                            <th><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>所属单位：
                            </th>
                            <td>
                                <c:forEach var="dict" items="${dictTypeEnumOrgBelongList}">
                                    <input type="radio" name="orgBelongId" value="${dict.dictId}"
                                           id="orgBelongId_${dict.dictId}" class="validate[required]"
                                           <c:if test="${sat.orgBelongId==dict.dictId}">checked="checked"</c:if>/><label
                                        for="orgBelongId_${dict.dictId}">&nbsp;${dict.dictName}</label>&#12288;
                                </c:forEach>
                            </td>
                            <th><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>所在科室：
                            </th>
                            <td colspan="3">
                                <input type="hidden" id="deptName" name="deptName" value="${sat.deptName}"/>
                                <select name="deptFlow" id="selectDept" class="validate[required] xlselect"
                                        onchange="getDeptName(this);">
                                    <option value="">请选择</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>成果形式：
                            </th>
                            <td>
                                <select class="validate[required] xlselect" name="achTypeId"
                                        style="width: 168px;margin-top: 6px">
                                    <option value="">请选择</option>
                                    <c:forEach var="dict" items="${dictTypeEnumAchTypeList}">
                                        <option value="${dict.dictId}"
                                                <c:if test="${sat.achTypeId==dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <th><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>获奖年份：
                            </th>
                            <td colspan="3">
                                <input style="width: 200px" readonly="readonly"
                                       onClick="WdatePicker({dateFmt:'yyyy'})" class="validate[required] ctime"
                                       name="prizedDate"
                                       type="text" value="${sat.prizedDate}"/>
                            </td>
                        </tr>
                        <tr>
                            <th><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>获奖级别：
                            </th>
                            <td>
                                <select class="validate[required] xlselect" name=prizedGradeId style="width: 168px;">
                                    <option value="">请选择</option>
                                    <c:forEach var="dict" items="${dictTypeEnumPrizedGradeList}">
                                        <option value="${dict.dictId}"
                                                <c:if test="${sat.prizedGradeId==dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <th><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>获奖等级：
                            </th>
                            <td colspan="3">
                                <select class="validate[required] xlselect" name="prizedLevelId" style="width: 208px;">
                                    <option value="">请选择</option>
                                    <c:forEach var="dict" items="${dictTypeEnumPrizedLevelList}">
                                        <option value="${dict.dictId}"
                                                <c:if test="${sat.prizedLevelId==dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th>学科门类：</th>
                            <td class="td_blue">
                                <c:forEach var="dict" items="${dictTypeEnumSubjectTypeList}">
                                    <input type="radio" name="categoryId" value="${dict.dictId}"
                                           id="categoryId_${dict.dictId}"
                                           <c:if test="${sat.categoryId==dict.dictId}">checked="checked"</c:if>/> <label
                                        for="categoryId_${dict.dictId}">&nbsp;${dict.dictName}</label>&#12288;
                                </c:forEach>
                            </td>
                            <th><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>奖励批准号：</th>
                            <td colspan="3">
                                <input type="text" class="validate[required] xltext" style="text-align: left; width: 200px"
                                       name="approvalCode" id="approvalCode" value="${sat.approvalCode}" onchange="validateApprovalCode()"/>
                                <p id="satMessage" style="color: #f00"></p>
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
                                <textarea class="validate[maxSize[200]] xltxtarea" placeholder="200个字符以内" style="margin-left: 0px;"
                                          name="remark" >${sat.remark }</textarea>
                            </td>
                        </tr>
                        </tbody>
                    </table>

                    <table class="basic" style="width:100%;">
                        <thead>
                        <tr>
                            <th colspan="4" class="bs_name">附件上传（上传的附件为证书扫描件）</th>
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
                </form>

                <form id="srmAchFile">
                    <input type="hidden" name="fileFlow" value="${file.fileFlow }"/>
                </form>

                <form id="authorList">
                    <table class="basic" style="width: 100%;">
                        <tr class="bs_name" style="height: 26px">
                            <td style="text-align:left" colspan="8">作者
                                <c:if test="${param.editFlag != GlobalConstant.FLAG_N}">
                                    <a href="javascript:void(0)">
                                        <img src="<s:url value='/'/>css/skin/${skinPath}/images/add.png"
                                             onclick="selectAuthor();"
                                             title="添加"/>
                                    </a>
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <th style="text-align: center; width: 20%">署名顺序</th>
                            <th style="text-align: center; width: 15%">人员名称</th>
                            <th style="text-align: center; width: 10%">性别</th>
                            <th style="text-align: center; width: 15%">学历</th>
                            <th style="text-align: center; width: 10%">学位</th>
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
                                    <select name="typeName" class="xlselect" style="width: 85%;">
                                        <option value="">请选择</option>
                                        <c:forEach var="dict" items="${dictTypeEnumApplyUserTypeList }">
                                            <option value="${dict.dictName }" >${dict.dictName }</option>
                                        </c:forEach>
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
                            <tr>
                                <td>
                                    <select name="typeName" class="xlselect" style="width: 85%;">
                                        <option value="">请选择</option>
                                        <c:forEach var="dict" items="${dictTypeEnumApplyUserTypeList }">
                                            <option value="${dict.dictName }"
                                                    <c:if test="${author.typeName eq dict.dictName }">selected="selected"</c:if>>${dict.dictName }</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td style="text-align: center;">
                                    <input class="xltext" style="width: 85%;" type="text" name="authorName"
                                           readonly="readonly" value="${author.authorName}"/>
                                    <input type="hidden" name="authorFlow" value="${author.authorFlow}"/>
                                    <input type="hidden" name="userFlow" value="${author.userFlow}"/>
                                    <input type="hidden" name="deptFlow" value="${author.deptFlow}"/>
                                    <input type="hidden" name="deptName" value="${author.deptName}"/>
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
                    <c:if test="${(param.editFlag != GlobalConstant.FLAG_N) and (param.editFlag != 'audit')}">
                        <input type="button" value="保&#12288;存" onclick="saveSat()" class="search"/>
                        <input type="button" value="关&#12288;闭" onclick="jboxClose();" class="search"/>
                    </c:if>

                </p>


                <%-- 克隆作者--%>
                <table id="addAuthorTable" style="display: none" class="basic" style="width: 100%;">
                    <tr>
                        <td>
                            <select name="typeName" class="xlselect" style="width: 85%;">
                                <option value="">请选择</option>
                                <c:forEach var="dict" items="${dictTypeEnumApplyUserTypeList }">
                                    <option value="${dict.dictName }">${dict.dictName }</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <input class="validate[required] xltext" readonly="readonly" style="width: 85%;" name="authorName" type="text"/>
                            <input type="hidden" name="userFlow" />
                            <input type="hidden" name="deptFlow" />
                            <input type="hidden" name="deptName" />
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
        </div>
    </div>
</div>
</body>
</html>