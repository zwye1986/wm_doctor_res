<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="false"/>
        <jsp:param name="jquery_ui_combobox" value="false"/>
        <jsp:param name="jquery_ui_sortable" value="false"/>
        <jsp:param name="jquery_cxselect" value="false"/>
        <jsp:param name="jquery_scrollTo" value="false"/>
        <jsp:param name="jquery_jcallout" value="false"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fullcalendar" value="false"/>
        <jsp:param name="jquery_fullcalendar" value="false"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_ztree" value="true"/>
    </jsp:include>
        <script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>"></script>
    <script type="text/javascript">
        <c:if test="${param.view != GlobalConstant.FLAG_Y}">
        function checkBDDate() {
            if ($('#projStartTime').val() && $('#projEndTime').val() && $('#projStartTime').val() > $('#projEndTime').val()) {
                jboxTip("计划开始时间不能大于计划结束时间！");
                return false;
            }
            return true;
        }

        function back() {
            jboxCloseMessager();
            ;
        }


        function save() {
            if (false == $("#projForm").validationEngine("validate")) {
                return false;
            }
            if (!checkBDDate()) return false;
            jboxConfirm("确认提交?", function () {
                var isSave = $("input[name=isSave]").val();
                if (isSave == "${GlobalConstant.FLAG_N}") {
                    jboxTip("课题编号已存在！");
                    return false;
                }
                var trs = $('#appendTbody').children();
                var datas = [];
                $.each(trs, function (i, n) {
                    var projRanking = $(n).find("input[name='projRanking']").val();
                    var authorName = $(n).find("input[name='authorName']").val();
                    var userFlow =  $(n).find("input[name='userFlow']").val();
                    var deptFlow = $(n).find("input[name='authorDeptFlow']").val();
                    var deptName = $(n).find("input[name='authorDeptName']").val();
                    var scoreFlow = $(n).find("input[name='scoreFlow']").val();
                    var scoreName = $(n).find("input[name='scoreName']").val();

                    var data = {
                        "projRanking":projRanking,
                        "authorName": authorName,
                        "userFlow": userFlow,
                        "deptFlow": deptFlow,
                        "deptName": deptName,
                        "scoreName": scoreName,
                        "scoreFlow": scoreFlow
                    };
                    datas.push(data);
                });
                $("#jsondata").val(JSON.stringify(datas));
                var url = "<s:url value='/srm/proj/add/addGlSetUp'/>";
                jboxStartLoading();
                jboxPost(url, $('#projForm').serialize(), function (resp) {
                    if (resp == '${GlobalConstant.FLAG_Y}') {
                        jboxTip("操作成功");
                        window.parent.frames['mainIframe'].location.reload();
                        jboxClose();
                    } else if (resp == '${GlobalConstant.FLAG_N}') {
                        jboxTip("课题编号已存在");
                    }

                }, null, false);
            });
        }

        function searchProjCategory() {
            var dictFlow = $("select[name='projDeclarerFlow'] option:selected").attr("dictFlow");
            var dictTypeId = $("#projDeclarerFlow").val();
            $("#projDeclarer").val($("select[name='projDeclarerFlow'] option:selected").text());
            var data = {
                dictFlow: dictFlow,
                dictTypeId: dictTypeId
            };
            var url = "<s:url value='/sys/dict/getCategoryDictByDeclarer'/>";
            jboxPost(url, data, function (data) {
                //清空原类别！
                $("select[name=projSecondSourceId] option[value != '']").remove();
                var dataObj = data;
                for (var i = 0; i < dataObj.length; i++) {
                    var cId = dataObj[i].dictId;
                    var cName = dataObj[i].dictName;
                    $option = $("<option></option>");
                    $option.attr("value", cId);
                    $option.text(cName);
                    $("select[name='projSecondSourceId']").append($option);
                }
            }, null, false);
        }

        function attrOptionText(name, obj) {
            var text = $(obj).find("option:selected").text();
//            alert(text);
            if (text) {
                $("input[name='" + name + "']").val(text);

            }
            if ($(obj).val()) {
                var url = "<s:url value='/srm/fund/scheme/initFundScheme'/>";
                var firstSourceId = $("#projDeclarerFlow").val();
                var secondSourceId = $(obj).val();
                var data = {
                    firstSourceId: firstSourceId,
                    secondSourceId: secondSourceId
                };
                jboxPost(url, data, function (resp) {
                    $("#schemeFlow").empty();
                    $.each(resp, function (i, n) {
                        $("#schemeFlow").append("<option  value='" + n.schemeFlow + "'>" + n.schemeName + "</option>");
                    });
                }, null, false);
            }
        }
        var setting = {
            view: {
                dblClickExpand: false,
                showIcon: false,
                showTitle: false,
                selectedMulti: false
            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                beforeClick: beforeClick,
                onClick: onClick
            }
        };

        function beforeClick(treeId, treeNode) {
            var check = (treeNode.id != 0);
            if (!check) jboxTip('不能选择根节点');
            return check;
        }

        function onClick(e, treeId, treeNode) {
            var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
                    nodes = zTree.getSelectedNodes(),
                    v = "";
            id = "";
            nodes.sort(function compare(a, b) {
                return a.id - b.id;
            });
            for (var i = 0, l = nodes.length; i < l; i++) {
                v += nodes[i].name + ",";
                id += nodes[i].id + ",";
            }
            if (v.length > 0) v = v.substring(0, v.length - 1);
            if (id.length > 0) id = id.substring(0, id.length - 1);
            var cityObj = $("#proSelect");
            $("#selectProjId").val(id);
            cityObj.attr("value", v);
        }

        function showMenu() {
            var cityObj = $("#proSelect");
            var cityOffset = $("#proSelect").offset();
            $("#menuContent").css({
                left: cityOffset.left + "px",
                top: cityOffset.top + cityObj.outerHeight() + "px"
            }).slideDown("fast");

            $("body").bind("mousedown", onBodyDown);
        }
        function hideMenu() {
            $("#menuContent").fadeOut("fast");
            $("body").unbind("mousedown", onBodyDown);
        }
        function onBodyDown(event) {
            if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length > 0)) {
                hideMenu();
            }
        }

        $(document).ready(function () {
            var url = "<s:url value='/sys/subject/getAllDataJson'/>";
            jboxPostJson(url, null, function (data) {
                if (data) {
                    zNodes = $.parseJSON(data);
                    $.fn.zTree.init($("#treeDemo"), setting, zNodes);
                }
            }, null, false);
        });


        function checkProjNo(obj) {
            var projNo = obj.value;
            if (projNo == '') {
                return;
            }
            var url = "<s:url value = '/srm/proj/approve/checkProjNo?projNo='/>" + projNo;
            jboxStartLoading();
            jboxGet(url, null, function (data) {
                if (data == "${GlobalConstant.FLAG_N}") {
                    $("#projNoMessage").text("课题编号已存在！");
                    $('#projNoMessage').css('color', 'red');
                    $("input[name=isSave]").val("${GlobalConstant.FLAG_N}");
                } else {
                    $("#projNoMessage").text("可以使用此编号！");
                    $('#projNoMessage').css('color', 'green');
                    $("input[name=isSave]").val("${GlobalConstant.FLAG_Y}");
                }
            }, null, false);
        }

        function doClose() {
            jboxClose();
        }
        function getAmountFund() {
            var goveFund = parseFloat($("#goveFund").val().trim());
            var orgFund = parseFloat($("#orgFund").val().trim());
            if (isNaN(goveFund)) {
                goveFund = 0;
                $("#goveFund").val("");
            }
            if (isNaN(orgFund)) {
                orgFund = 0;
                $("#orgFund").val("");
            }
            var amount = goveFund + orgFund;

            if (parseFloat(amount)) {
                $("#amountFund").val(parseFloat(amount.toFixed("2")));
            } else {
                $("#amountFund").val("0");
            }
        }
        function ckeckNum(obj) {
            var maxLimit = parseFloat($(obj).val());
//    var re =/^[0-9]+.?[0-9]*$/;//正数正则
//    var reg = /^([0-9]|[0-9]+.?[0-9]{1,2})$/;
            if (maxLimit) {
                $(obj).val(parseFloat(maxLimit.toFixed("2")));
            }
            getAmountFund();
        }


        var setting2 = {
            view: {
                dblClickExpand: false,
                showIcon: false,
                showTitle: true,
                selectedMulti: false
            },
            data: {
                simpleData: {
                    enable: true
                },
                key: {
                    title:"t"
                }
            },
            callback: {
                beforeClick: beforeClick2,
                onClick: onClick2
            }
        };

        function beforeClick2(treeId, treeNode) {
            var check = (treeNode.id != 0);
            if (!check) {
                jboxTip('不能选择根节点');
                return check;
            }
            if (treeNode.isParent) {
                jboxTip("该项不允许选择，请选择子项...");
                return false;
            }
            if((treeNode.id).substring(0,4) && (treeNode.id).substring(0,4) == 'type'){
                jboxTip("该项不允许选择，请尝试选择其它项... ");
                return false;
            }
        }

        function onClick2(e, treeId, treeNode) {
            /*if (treeNode.isParent) {
             alert("这个 是父节点 ， 去点击子节点吧... ");
             return false;
             }*/

            var zTree = $.fn.zTree.getZTreeObj("treeDemo2"),
                    nodes = zTree.getSelectedNodes(),
                    v = "";
            id = "";
            nodes.sort(function compare(a, b) {
                return a.id - b.id;
            });
            for (var i = 0, l = nodes.length; i < l; i++) {
                v += nodes[i].name + ",";
                id += nodes[i].id + ",";
            }

            if (v.length > 0) v = v.substring(0, v.length - 1);
            if (id.length > 0) id = id.substring(0, id.length - 1);

//        var cityObj = $("#scoreName");
//        $("#scoreFlow").val(id);
//        cityObj.attr("value", v);
            $(flowObj).val(id);
            $(nameObj).val(v);
        }
        var flowObj;
        var nameObj;
        function showMenu2(obj) {
            nameObj = $(obj);
            flowObj = $(obj).next("input[name='scoreFlow']");
            var nameOffset = $(obj).offset();
            $("#menuContent2").css({
                left: nameOffset.left + "px",
                top: nameOffset.top + nameObj.outerHeight() + "px"
            }).slideDown("fast");
            $("body").bind("mousedown", onBodyDown2);
        }

        function hideMenu2() {
            $("#menuContent2").fadeOut("fast");
            $("body").unbind("mousedown", onBodyDown2);
        }
        function onBodyDown2(event) {
            if (!(event.target.id == "menuBtn" || event.target.id == "menuContent2" || $(event.target).parents("#menuContent2").length > 0)) {
                hideMenu2();
            }
        }

        $(document).ready(function () {
            var url = "<s:url value='/srm/ach/score/getScoreDataJson'/>";
            jboxPostJson(url, null, function (data) {
                //console.log(data);
                if (data) {
                    zNodes = $.parseJSON(data);
                    $.fn.zTree.init($("#treeDemo2"), setting2, zNodes);
                }
            }, null, false);
        });



        function addAuthor(){
            var tr = $("#addAuthor tr:eq(0)").clone();
            var trlength = $('#appendTbody').find("tr").length;
            $(tr).find("input[name='authorDeptFlow']").attr("id","deptFlow_"+trlength);
            $(tr).find("input[name='authorDeptName']").attr("id","deptName_"+trlength);
            $(tr).find("input[name='userFlow']").attr("id","userFlow_"+trlength);
            $(tr).find("input[name='authorName']").attr("id","authorName_"+trlength);
            $('#appendTbody').append(tr);
            addDept(trlength);
        }
        function delAuthorTr(obj) {
            jboxConfirm("确认删除？", function () {
                obj.parentNode.parentNode.remove();
            });
        }


        function loadUsers(obj) {
            var tr = $(obj).parents("tr");
            var deptFlow = $(tr).find("input[name='authorDeptFlow']").val();
            var userDatas = [];
            jboxPost('<s:url value="/srm/proj/add/getUsersByDept"/>', {deptFlow: deptFlow}, function (resp) {

                for (var index in resp) {
                    var user = resp[index];

                    var u = {};
                    u.id = user.userFlow;
                    u.text = user.userName;
                    userDatas.push(u);
                }
            }, null, false);
            var userFlow = $(tr).find("input[name='userFlow']").prop("id");
            var authorName = $(tr).find("input[name='authorName']").prop("id");
            $("#"+userFlow).val('');
            $("#"+authorName).val('');
            var itemSelectFuntion = function () {
                $("#"+userFlow).val(this.id);
            };
            $.selectSuggest(authorName, userDatas, itemSelectFuntion, userFlow, true);
        }
        function loadUsers2(deptFlow) {
            $("#applyUserFlow").val('');
            $("#applyUserName").val('');
            var userDatas = [];
            jboxPost('<s:url value="/srm/proj/add/getUsersByDept"/>', {deptFlow: deptFlow}, function (resp) {

                for (var index in resp) {
                    var user = resp[index];

                    var u = {};
                    u.id = user.userFlow;
                    u.text = user.userName;
                    userDatas.push(u);
                }
            }, null, false);
            var itemSelectFuntion = function () {
                $("#applyUserFlow").val(this.id);

            };
            $.selectSuggest("applyUserName", userDatas, itemSelectFuntion, "applyUserFlow", true);
        }
        var deptDatas = [];
        $(document).ready(function () {

            <c:forEach items="${depts}" var="dept">
            var d = {};
            d.id = '${dept.deptFlow}';
            d.text = '${dept.deptName}';
            deptDatas.push(d);
            </c:forEach>
            var itemSelectFuntion = function () {
             $("#deptFlow").val(this.id);
                loadUsers2(this.id);
             };
             $.selectSuggest('trainDept', deptDatas, itemSelectFuntion, "deptFlow", true);

        });
        function addDept(index){
            var itemSelectFuntion = function () {
                $("#deptFlow_"+index).val(this.id);
                loadUsers($("#deptFlow_"+index));
            };
            $.selectSuggest('deptName_'+index, deptDatas, itemSelectFuntion, "deptFlow_"+index, true);
        }
    </script>
    </c:if>
    <%--<div id="main">--%>
        <div class="mainright">
            <div class="content">
                <div class="title1 clearfix">
                <form id="projForm" method="post" action="<s:url value='/srm/proj/add/addGlSetUp'/>" >
                    <input type="hidden" name="pageName" value="step1"/>
                    <input type="hidden" name="jsondata" id="jsondata">
                    <table class="basic" style="width: 100%">
                        <col width="15%"/>
                        <col width="35%"/>
                        <col width="15%"/>
                        <col width="35%"/>
                        <tr>
                            <th colspan="4" style="text-align: left;padding-left: 15px">基本信息</th>
                        </tr>
                        <tbody>
                        <tr>
                            <th style="text-align: right;">
                                年&#12288;&#12288;度：
                            </th>
                            <td style="text-align: left;padding-left: 10px;">
                                <input name="projYear" type="text"
                                       value="<c:if test='${empty proj.projYear}'>${pdfn:getCurrYear()}</c:if><c:if test='${! empty proj.projYear}'>${proj.projYear}</c:if>"
                                       class="inputText ctime" style="width: 80%; text-align: left;" readonly="readonly"
                                       onClick="WdatePicker({dateFmt:'yyyy'})"/>
                            </td>
                            <th style="text-align: right;">
                                项目名称<span style="color: red;">*</span>：
                            </th>
                            <td style="text-align: left;padding-left: 10px;">
                                <input name="projName" type="text" value="${proj.projName}" title="${proj.projName}"
                                       onchange="this.title = this.value;"
                                       class="validate[required]  inputText validate[maxSize[200]]"
                                       style="width: 80%; text-align: left;"/>
                            </td>
                        </tr>
                        <tr>
                            <th style="text-align: right">负责人部门：</th>
                            <td>
                                    <input id="trainDept" class="validate[required] inputText" name="applyDeptName" type="text" style="width: 80%;"/>
                                    <input id="deptFlow" name="applyDeptFlow" class="input" type="text"
                                           hidden style="margin-left: 0px;"/>
                                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
                            <th style="text-align: right">项目负责人：</th>
                            <td >
                                <input id="applyUserName" class="validate[required] inputText" name="applyUserName" style="width: 80%;" type="text"/>
                                <input id="applyUserFlow" name="applyUserFlow" class="input" type="text"
                                       hidden style="margin-left: 0px;"/>
                                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
                            </td>
                        </tr>
                        <tr>
                            <th style="text-align: right;">学科代码<span style="color: red;">*</span>：</th>
                            <td style="text-align: left;padding-left: 10px;">
                                <input type="hidden" id="selectProjId" name='subjId' value='${proj.subjId}'>
                                <input id="proSelect" name="subjName" class="validate[required] inputText"
                                       value="${proj.subjName}" readonly="readonly" class="inputText"
                                       style="width: 80%;text-align: left" onclick="showMenu(); return false;"/>

                            </td>
                            <th style="text-align: right;">
                                联系方式：
                            </th>
                            <td style="text-align: left;padding-left: 10px;">
                                <input name="applyUserPhone" type="text" value="${proj.applyUserPhone}"
                                       title="${proj.applyUserPhone}" onchange="this.title = this.value;"
                                       class="inputText validate[custom[phone]]"
                                       style="width: 80%; text-align: left;"/>
                            </td>
                        </tr>
                        <tr id="planTimeTr">
                            <th  style="text-align: right;">计划开始时间：</th>
                            <td style="text-align: left;padding-left: 10px;">
                                <input id="projStartTime" name="projStartTime" type="text"
                                       value="<c:if test='${! empty proj.projStartTime}'>${proj.projStartTime}</c:if>"
                                       class="inputText ctime" style="width: 80%; text-align: left;" readonly="readonly"
                                       <c:if test="${empty applicationScope.sysCfgMap['srm_projInfo_start_time']}">onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"</c:if>
                                       onchange="checkBDDate()"/>
                            </td>
                            <th style="text-align: right;">计划结束时间：</th>
                            <td style="text-align: left;padding-left: 10px;">
                                <input id="projEndTime" name="projEndTime" type="text"
                                       value="<c:if test='${! empty proj.projEndTime}'>${proj.projEndTime}</c:if>"
                                       class="inputText ctime" style="width: 80%; text-align: left;" readonly="readonly"
                                       <c:if test="${empty applicationScope.sysCfgMap['srm_projInfo_end_time']}">onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"</c:if>
                                       onchange="checkBDDate()"/>
                            </td>
                        </tr>
                        <tr>
                            <th style="text-align: right;">一级来源<span style="color: red;">*</span>：</th>
                            <td style="text-align: left;padding-left: 10px;">
                                <select id="projDeclarerFlow" name="projDeclarerFlow"
                                        class="inputText validate[required]" style="width: 80%;text-align: left;"
                                        onchange="searchProjCategory();">
                                    <option value="">请选择</option>
                                    <c:forEach items="${dictTypeEnumProjTypeSourceList}" var="dict" varStatus="status">
                                        <option dictFlow="${dict.dictFlow}" value="${dict.dictId}"
                                                <c:if test="${proj.projDeclarerFlow eq dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                                    </c:forEach>
                                </select>
                                <input type="hidden" id="projDeclarer" name="projDeclarer" value="${proj.projDeclarer}"/>
                            </td>
                            <th style="text-align: right;">二级来源<span style="color: red;">*</span>：</th>
                            <td style="text-align: left;padding-left: 10px;">
                                <select name="projSecondSourceId" class="inputText validate[required]"
                                        style="width: 80%;text-align: left;"
                                        onchange="attrOptionText('projSecondSourceName',this)">
                                    <option value="">请选择</option>
                                </select>
                                <input type="hidden" name="projSecondSourceName" value="${proj.projSecondSourceName}"/>
                            </td>
                        </tr>
                        <tr>
<%--
                            <th style="text-align: right;">项目类别<span style="color: red;">*</span>：</th>
                            <td style="text-align: left;padding-left: 10px;">
                                <select name="projTypeId" class="inputText validate[required]" style="width: 80%;">
                                    <option value="" >请选择</option>
                                    <c:forEach items="${dictTypeEnumManageTypeList}" var="dict" varStatus="status">
                                        <option dictFlow="${dict.dictFlow}" value="${dict.dictId}"
                                                <c:if test="${proj.projTypeId eq dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                                    </c:forEach>
                                </select>
                            </td>
--%>

                        </tr>
                    </table>

                            <table class="basic" style="width: 100%;">
                                <tr>
                                    <th colspan="5" style="text-align: left;">
                                        主要研究开发人员
                                        <a style="float: right" href="javascript:void(0)"><img
                                                src="<s:url value='/'/>css/skin/${skinPath}/images/add.png"
                                                onclick="addAuthor();" title="添加"/>
                                        </a>
                                    </th>
                                </tr>
                                <tr>
                                    <th style="text-align: center" width="10%">排名</th>
                                    <th style="width: 25%;text-align: center">科室</th>
                                    <th style="width: 25%;text-align: center">姓名</th>
                                    <th style="width: 25%;text-align: center">积分项</th>
                                    <th style="width: 10%;text-align: center">操作</th>
                                </tr>
                                <tbody id="appendTbody">

                                </tbody>

                            </table>

                    <table class="basic" style="width: 100%;margin-top: 20px">
                        <colgroup>
                            <col width="15%"/>
                            <col width="35%"/>
                            <col width="15%"/>
                            <col width="35%"/>
                        </colgroup>
                        <tr>
                            <th colspan="4" style="text-align: left;padding-left: 15px">立项信息</th>
                        </tr>
                        <tbody>
                        <tr>
                            <th>课题编号<span style="color: red;">*</span>：</th>
                            <td colspan="3">
                                <input id="projNo" name="projNo" class="validate[required,custom[onlyLetterNumber],maxSize[30]]" value="${proj.projNo}"
                                       style="width: 230px" onblur="checkProjNo(this)"/>
                                <b id="projNoMessage" style="margin-left: 0px; text-decoration: none;"></b>
                            </td>

                        </tr>
                        <tr>
                            <th>伦理审查：</th>
                            <td>
                                <input type="radio" class="validate[required]" name="isEthicalFlag" value="Y" id="isEthicalFlag_Y" /><label for="isEthicalFlag_Y">&#12288;是</label>&#12288;&#12288;
                                <input type="radio" class="validate[required]" name="isEthicalFlag" value="N" id="isEthicalFlag_N" /><label for="isEthicalFlag_N">&#12288;否</label>
                            </td>
                            <th>计划类别<%--<span style="color: red;">*</span>--%>：</th>
                            <td>
                                <select name="planTypeId" class="validate[required]" style="width: 235px">
                                    <option value="" >请选择</option>
                                    <c:forEach items="${dictTypeEnumPlanCategoryList}" var="dict" varStatus="status">
                                        <option  value="${dict.dictId}"
                                                 <c:if test="${proj.planTypeId eq dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th >经费方案<span style="color: red;">*</span>：</th>
                            <td >
                                <select id="schemeFlow" name="schemeFlow" class="validate[required]"
                                        style="width: 230px">
                                    <option value=""></option>

                                </select>
                            </td>
                            <th >课题账号：</th>
                            <td >
                                <input type="text" name="accountNo" class="validate[required] " style="width: 230px;" />
                            </td>
                        </tr>
                        <tr>
                            <th>经费信息(万元)<span style="color: red;">*</span>：</th>

                            <td>
                                下拨：<input name="goveFund" id="goveFund" onblur="ckeckNum(this)"
                                          class="validate[required,maxSize[10],custom[number,min[0]]]"
                                          style="width: 70px"/>
                                配套：<input name="orgFund" id="orgFund" onblur="ckeckNum(this)"
                                          class="validate[required,maxSize[10],custom[number,min[0]]]"
                                          style="width: 70px"/>
                            </td>
                            <th>
                                总经费：
                            </th>
                            <td>
                                <input id="amountFund" name="amountFund" readonly="readonly"
                                       class="validate[required,maxSize[10]]" style="width: 230px"/>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                立项意见：
                            </th>
                            <td colspan="3">
                                <textarea id="sug" name="sug" class="validate[maxSize[200]]" rows="5" cols="20"
                                          style="margin-top:5px;margin-bottom:5px;width:95%; resize :none"
                                          placeholder="请填写意见..."></textarea>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </form>
                <div align="center" style="margin-top: 10px">
                    <input id="search" type="button" onclick="save()" class="search" value="提&#12288;交"/>
                    <input id="close" type="button" onclick="back()" class="search" value="关&#12288;闭"/>
                </div>
            </div>
        </div>
    </div>
    <div id="menuContent" class="menuContent" style="display:none; position:absolute;z-index:1000;">
        <ul id="treeDemo" class="ztree" style="margin-top:0; width:190px;"></ul>
    </div>
    <div id="menuContent2" class="menuContent" style="display:none; position:absolute;z-index:1000;">
        <ul id="treeDemo2" class="ztree" style="margin-top:0; width:190px;height: 150px"></ul>
    </div>
        <table id="addAuthor" class="basic" style="display: none;" style="width: 100%">
            <tr>
                <td>
                    <%--<input type="text" name="" style="width: 85%;" />--%>
                    <select name="projRanking" class="validate[required]" style="width: 80%;">
                        <option value="" >请选择</option>
                        <c:forEach items="${dictTypeEnumProjAuthorRankList}" var="dict" varStatus="status">
                            <option  value="${dict.dictId}" >${dict.dictName}</option>
                        </c:forEach>
                    </select>
                </td>
                <td>
                    <input type="text" style="width: 85%;" name="authorDeptName" <%--oninput="loadUsers(this);" --%>/>
                    <input type="hidden" name="authorDeptFlow"/>

                    <%--<select name="authorDeptFlow" class="validate[required]"
                            style="width: 80%;text-align: left" onchange="loadUsers(this.value,this);">
                        <option value="">请选择</option>
                        <c:forEach items="${depts}" var="dept">
                            <option value="${dept.deptFlow}">${dept.deptName}</option>
                        </c:forEach>
                        &lt;%&ndash;<option value="">其他部门</option>&ndash;%&gt;
                    </select>
                    <input type="hidden" name="authorDeptName"/>--%>
                </td>
                <td>
                    <%--<select name="userFlow" style="width: 80%;text-align: left" onchange="changeUser(this);"
                            class="validate[required]">
                        <option value="">请选择</option>
                    </select>
                    <input type="hidden" name="authorName"/>--%>
                    <input type="text" style="width: 85%;" name="authorName" />
                    <input type="hidden" name="userFlow"/>
                </td>
                <td>
                    <input type="text"  name="scoreName" class="validate[required] " style="width: 85%;" value="${author.scoreName}" readonly="readonly"
                           onclick="showMenu2(this); return false;"/>
                    <input type="hidden" value="${author.scoreFlow}" name="scoreFlow" />
                </td>
                <td style="text-align: center;">
                    [<a onclick="delAuthorTr(this)" style="cursor: pointer;">删除</a>]
                </td>
            </tr>
        </table>