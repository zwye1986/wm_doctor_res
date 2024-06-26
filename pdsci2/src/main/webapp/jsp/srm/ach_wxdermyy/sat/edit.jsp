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
    <script type="text/javascript">
        function saveSatAndAuthor(agreeFlag) {
            if (false == $("#satForm").validationEngine("validate")) {
                return false;
            }
            if (false == $("#authorList").validationEngine("validate")) {
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
                var typeName = $(n).find("input[name='typeName']").val();
                var sexId = $(n).find("select[name='sexId']").val();
                var educationId = $(n).find("select[name='educationId']").val();
                var degreeId = $(n).find("select[name='degreeId']").val();
                var workCode =  $(n).find("input[name='workCode']").val();
                var titleId = $(n).find("select[name='titleId']").val();
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
                    "typeName": typeName,
                    "sexId": sexId,
                    "workCode": workCode,
                    "educationId": educationId,
                    "degreeId": degreeId,
                    "userFlow": userFlow,
                    "deptFlow": deptFlow,
                    "deptName": deptName,
                    "titleId": titleId,
                    "branchId":branchId
                    , "scoreFlow": scoreFlow
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
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <div id="look">
                <form id="satForm" method="post" enctype="multipart/form-data">
                    <input id="jsondata" type="hidden" name="jsondata" value=""/>
                    <c:if test="${(param.editFlag != GlobalConstant.FLAG_N) and (param.auditFlag != 'audit')}">
                        <input type="hidden" name="applyUserFlow" value="${sessionScope.currUser.userFlow}"/>
                        <input type="hidden" name="applyUserName" value="${sessionScope.currUser.userName}"/>
                        <input type="hidden" name="applyOrgFlow" value="${sessionScope.currUser.orgFlow}"/>
                        <input type="hidden" name="applyOrgName" value="${sessionScope.currUser.orgName}"/>
                    </c:if>
                    <table class="basic" style="width: 100%;">
                        <tr class="bs_name" style="height: 26px">
                            <td style="text-align:left" colspan="8">基本信息</td>
                        </tr>
                        <tr>
                            <th style="width: 17%;"><span class="redspan"
                                                          style="color: red;padding: 0px;margin-left: 10px;">*</span>项目名称：
                            </th>
                            <td colspan="5">
                                <input type="hidden" name="satFlow" value="${sat.satFlow}"/>
                                <input class="validate[required,maxSize[100]] name xltext" style="width: 97%;"
                                       name="satName" type="text" value="${sat.satName}"/>
                            </td>
                        </tr>
                        <tr>
                            <th>学科类别：</th>
                            <td class="td_blue">
                                <c:forEach var="dict" items="${dictTypeEnumSubjectTypeList}">
                                    <input type="radio" name="categoryId" value="${dict.dictId}"
                                           id="categoryId_${dict.dictId}"
                                           <c:if test="${sat.categoryId==dict.dictId}">checked="checked"</c:if>/> <label
                                        for="categoryId_${dict.dictId}">&nbsp;${dict.dictName}</label>&#12288;
                                </c:forEach>
                            </td>
                            <th><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>科室：
                            </th>
                            <td>
                                <input id="trainDept" class="xltext" name="deptName" type="text"
                                       value="${sat.deptName}" autocomplete="off"/>
                                <input id="deptFlow" name="deptFlow" class="input" value="${sat.deptFlow}" type="text"
                                       hidden style="margin-left: 0px;"/>
                            </td>
                        </tr>
                        <tr>
                            <th><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>所在支部：</th>
                            <td>
                                <select name="branchId" class="validate[required] xlselect" style="width: 168px;">
                                    <option value="">请选择</option>
                                    <c:forEach items="${dictTypeEnumWxeyBranchList}" var="dict">
                                        <option value="${dict.dictId }"
                                                <c:if test="${sat.branchId eq dict.dictId }">selected="selected"</c:if>>${dict.dictName }</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <th>工号：</th>
                            <td>
                                <input name="jobNumber" class="xltext" type="text" value="${sat.jobNumber}"/>
                            </td>
                        </tr>
                        <tr>
                            <th><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>获奖类别：
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
                            <th><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>获奖级别：
                            </th>
                            <td>
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
                            <th><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>获奖年度：
                            </th>
                            <td>
                                <input style="width: 200px" readonly="readonly"
                                       onClick="WdatePicker({dateFmt:'yyyy'})" class="validate[required] ctime"
                                       name="prizedDate"
                                       type="text" value="${sat.prizedDate}"/>
                            </td>
                            <th>奖励批准号：</th>
                            <td>
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
                            <th width="17%"><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>附件：</th>
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
                </form>

                <form id="srmAchFile">
                    <input type="hidden" name="fileFlow" value="${file.fileFlow }"/>
                </form>

                <form id="authorList">
                    <table class="basic" style="width: 100%;">
                        <tr class="bs_name" style="height: 26px">
                            <td style="text-align:left" colspan="10">作者
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
                            <th style="text-align: center; width: 10%">署名顺序</th>
                            <th style="text-align: center; width: 10%">人员名称</th>
                            <th style="text-align: center; width: 10%">性别</th>
                            <th style="text-align: center; width: 10%">工号</th>
                            <th style="text-align: center; width: 10%">学历</th>
                            <th style="text-align: center; width: 10%">学位</th>
                            <th style="text-align: center; width: 10%">职称</th>
                            <th style="text-align: center; width: 10%;">支部</th>
                            <c:if test="${company == 'Y'}">
                                <th style="text-align: center; width: 10%;">积分项</th>
                            </c:if>
                            <c:if test="${param.editFlag != GlobalConstant.FLAG_N}">
                                <th style="text-align: center; width: 10%">操作</th>
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
                                    <input type="text" name="workCode"
                                           class="validate[maxSize[16]] xlselect"
                                           value="${author.workCode }" style="width: 85%"/>
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
                                        <input type="hidden" name="scoreName" value="${author.scoreName}"/>
                                    </td>
                                </c:if>
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
                                    <input class="xltext" style="width: 85%;" type="text" name="typeName"
                                           value="${author.typeName}"/>
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
                                        <select name="scoreFlow" id="selectScore" class="xlselect" style="width: 85%;"
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
                        <input type="button" value="保&#12288;存" onclick="saveSat()" class="search"/>
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
                            <input type="text" name="workCode"
                                   class="validate[maxSize[16]] xlselect"
                                   value="${author.workCode }" style="width: 85%"/>
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
                        <td>
                            <select name="branchIdAuthor" class="xlselect" style="width: 85%;">
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