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
        .edit3 {
            text-align: center;
            border: none;
        }

        .cont_list {
            background: none;
            background-color: #f8f8f8;
            border: 1px solid #e3e3e3;
            border-bottom-style: none;
        }

        .cont_list .left .name {
            color: #333;
        }

        [type='text'] {
            width: 150px;
            height: 22px;
        }

        select {
            width: 153px;
            height: 27px;
        }
    </style>
    <script type="text/javascript">
        function addForm() {
            var url = "<s:url value='/res/cfg/insertForm'/>";
            jboxOpen(url, "新增表单", 700, 250);
        }

        function searchForm() {
            jboxStartLoading();
            $("#searchForm").submit();
        }

        function toPage(page) {
            if (page != undefined) {
                $("#currentPage").val(page);
            }
            searchForm();
        }

        function addAssessTitle() {
            var cfgCodeId = $("select[name='cfgCodeId']").val();
            if (cfgCodeId == "") {
                jboxTip("请选择评分类型！");
                return false;
            }
            $("#addTitleButton").prev().show();
            $("#addTitleButton").hide();
            $("#saveTitleButton").show();
        }

        function modifyAssessTitle(titleId) {
            $("#assessTitle_" + titleId).prev().hide();
            $("#assessTitle_" + titleId).show();
            $(".assessType_" + titleId).toggle();
            $(".assessEvalType_" + titleId).toggle();
        }

        function saveAssessTitle(titleId) {
            var data = "";
            var saveFlag = true;
            if (titleId != "") {//修改名称
                if ($("#assessTitle_" + titleId).val().trim() == "") {
                    saveFlag = false;
                    jboxTip("请填写名称！");
                    return false;
                }
                data = {
                    id: titleId,
                    name: $("#assessTitle_" + titleId).val()
                };
                var assessTypeId = $(".assessType_" + titleId + " :checked").val();
                if (!assessTypeId) {
                    saveFlag = false;
                    jboxTip("请选择分制类型！");
                    return false;
                }
                data.assessTypeId = assessTypeId;
                <c:if test="${param.cfgCodeId eq resAssessTypeEnumTeacherDoctorAssess.id
                or param.cfgCodeId eq resAssessTypeEnumNurseDoctorAssess.id
                or param.cfgCodeId eq resAssessTypeEnumPatientDoctorAssess.id
                or param.cfgCodeId eq resAssessTypeEnumSecretaryDoctorAssess.id
                }">
                var evalTypeId = $(".assessEvalType_" + titleId + " :checked").val();
                if (!evalTypeId) {
                    saveFlag = false;
                    jboxTip("请选择打分类型！");
                    return false;
                }
                data.evalTypeId = evalTypeId;
                </c:if>
            } else {//新增名称
                if (false == $("#assessTitleForm").validationEngine("validate")) {
                    saveFlag = false;
                    return false;
                }
                data = $("#assessTitleForm").serialize();
                $("#saveTitleButton").attr("disabled", true);
            }
            if (!saveFlag) {
                return false;
            }
            var cfgCodeId = $("select[name='cfgCodeId'] option:selected").val();
            var url = "<s:url value='/res/cfg/saveAssessTitle'/>?cfgFlow=${assessCfg.cfgFlow}&cfgCodeId=" + cfgCodeId;
            jboxPost(url, data,
                function (resp) {
                    if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
                        search();
                    }
                }, null, true);
        }

        function search() {
            var cfgCodeId = $("select[name='cfgCodeId'] option:selected").val();
            var url = "<s:url value='/res/cfg/assessCfg'/>?cfgCodeId=" + cfgCodeId;
            window.location.href = url;
        }

        function editForm(cfgFlow, formName) {
            var url = "<s:url value='/res/cfg/assessCfg'/>?cfgFlow=" + cfgFlow;
            jboxOpen(url, formName, 1100, 600,false);
        }

        function editFormStatus(cfgFlow, cfgCodeId, formStatusId) {
            var url = "<s:url value='/res/cfg/editFormStatus'/>?cfgFlow=" + cfgFlow + "&cfgCodeId=" + cfgCodeId + "&formStatusId=" + formStatusId;
            jboxGet(url,null,function(resp){
                if(resp=="${GlobalConstant.OPRE_SUCCESSED}"){
                    toPage('${currentPage}');
                }
            });
        }

        function deleteTitle(id) {
            jboxConfirm("确认删除？", function () {
                var cfgFlow = $("input[name='cfgFlow']").val();
                var url = "<s:url value='/res/cfg/deleteTitle'/>?cfgFlow=" + cfgFlow + "&id=" + id;
                jboxPost(url, null,
                    function (resp) {
                        if ("${GlobalConstant.DELETE_SUCCESSED}" == resp) {
                            search();
                        }
                    },
                    null, true);
            });
        }

        function addItem(titleId) {
            $("#" + titleId + "Td").append($("#clone tr:eq(0)").clone());
            $addLastTr = $("#" + titleId + "Td").children("tr:last");
            $addLastTr.find("input[name='titleId']").val(titleId);
            $addLastTr.find("input[name='score']").attr("onblur", "scoreCount('" + titleId + "')");
            $("#saveButton").show();
            $("#" + titleId + "noRecoredTd").hide();
            $addLastTr.children("td:last").children().attr("onclick", "removeItemTr(this,'" + titleId + "')");
        }

        function removeItemTr(obj, titleId) {
            $(obj).parent().parent().remove();
            var trs = $(".itemTd .addTr");
            if (trs.length <= 0) {
                $("#saveButton").hide();
            }
            scoreCount(titleId);
            var addTrs = $(".itemTd .addTr");
            if (trs.length <= 0) {
                $("#" + titleId + "noRecoredTd").show();
            }
        }

        function save() {
            if (false == $("#assessCfgForm").validationEngine("validate")) {
                return false;
            }
            var trs = $(".itemTd .addTr");
            if (trs.length <= 0) {
                jboxTip("请添加考核指标！");
                return false;
            }
            var datas = [];
            $.each(trs, function (i, n) {
                var titleId = $(n).find("input[name='titleId']").val();
                var name = $(n).find("input[name='name']").val();
                var score = $(n).find("input[name='score']").val();
                var data = {
                    "titleId": titleId,
                    "name": name,
                    "score": score
                };
                datas.push(data);
            });
            var cfgFlow = $("input[name='cfgFlow']").val();
            var requestData = {"itemFormList": datas, "cfgFlow": cfgFlow};
            var url = "<s:url value='/res/cfg/saveAssessItemList'/>";
            $("#saveButton").attr("disabled", true);
            jboxPostJson(
                url,
                JSON.stringify(requestData),
                function (resp) {
                    if ("${GlobalConstant.SAVE_SUCCESSED}" == resp) {
                        search();
                    }
                }, null, true);
        }

        function changeStyle(obj, stylename) {
            $scoreTd = $(obj).parent().parent().prev();
            $score = $scoreTd.children();
            $name = $scoreTd.prev().children();
            $score.removeClass(stylename);
            $name.removeClass(stylename);
            $score.removeAttr("readonly");
            $name.removeAttr("readonly");
            $(obj).parent().hide();
            $(obj).parent().next().show();
        }

        function modifyItem(obj, itemId, titleId) {
            if (false == $("#assessCfgForm").validationEngine("validate")) {
                return false;
            }
            $scoreTd = $(obj).parent().parent().prev();
            $score = $scoreTd.children();
            $name = $scoreTd.prev().children();
            $score.addClass("edit3");
            $name.addClass("edit3");
            $score.attr("readonly", "readonly");
            $name.attr("readonly", "readonly");
            $(obj).parent().hide();
            $(obj).parent().prev().show();
            if ($name.val() == $name.attr("oldvalue") && $score.val() == $score.attr("oldvalue")) {
                jboxTip("没有修改！");
                return;
            }
            var cfgFlow = $("input[name='cfgFlow']").val();
            var data = {
                cfgFlow: cfgFlow,
                id: itemId,
                name: $name.val(),
                score: $score.val()
            };
            jboxPost("<s:url value='/res/cfg/modifyItem'/>", data,
                function (resp) {
                    if ("${GlobalConstant.SAVE_SUCCESSED}" == resp) {
                        scoreCount(titleId);
                        $name.attr("oldvalue", $name.val());
                        $score.attr("oldvalue", $score.val());
                    }
                }
                , null, true);
        }

        function deleteItem(id) {
            jboxConfirm("确认删除？", function () {
                var cfgFlow = $("input[name='cfgFlow']").val();
                var url = "<s:url value='/res/cfg/deleteItem'/>?cfgFlow=" + cfgFlow + "&id=" + id;
                jboxPost(url, null,
                    function (resp) {
                        if ("${GlobalConstant.DELETE_SUCCESSED}" == resp) {
                            search();
                        }
                    },
                    null, true);
            });
        }

        function scoreCount(titleId) {
            var trs = $("#" + titleId + "Td").children();
            var scoreCount = 0;
            $.each(trs, function (i, n) {
                var score = $(n).find("input[name='score']").val();
                if (score == null || score == undefined || score == '' || isNaN(score)) {
                    score = 0;
                }
                scoreCount += parseFloat(score);
            });
            $("#scoreCount_" + titleId).text(scoreCount);
        }

        $(document).ready(function () {
            <c:forEach items="${titleFormList}" var="title">
            scoreCount("${title.id}");
            </c:forEach>
        });
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm" action="<s:url value="/res/cfg/list" />" method="post">
                <div class="queryDiv">
                    <div class="inputDiv">
                        <label class="qlable">表&nbsp;单&nbsp;名&nbsp;称：</label>
                        <input type="text" name="formName" value="${param.formName}" class="qtext"/>
                        <input id="currentPage" type="hidden" name="currentPage" value=""/>
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">评&nbsp;价&nbsp;类&nbsp;型：</label>
                        <select name="cfgCodeId" class="qselect">
                            <option value="">全部</option>
                            <c:forEach items="${resAssessTypeEnums}" var="resAssessTypeEnum">
                                <option value="${resAssessTypeEnum.id}"
                                        <c:if test="${param.cfgCodeId eq resAssessTypeEnum.id}">selected</c:if>>${resAssessTypeEnum.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">表&nbsp;单&nbsp;状&nbsp;态：</label>
                        <select name="formStatusId" class="qselect">
                            <option value="">全部</option>
                            <c:forEach items="${achScoreEnums}" var="achScoreEnum">
                                <option value="${achScoreEnum.id}"
                                        <c:if test="${param.formStatusId eq achScoreEnum.id}">selected</c:if>>${achScoreEnum.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="funcDiv">
                    <input type="button" class="search" onclick="searchForm();" value="查&#12288;询">
                    <input type="button" class="search" onclick="addForm();" value="新增表单">
                </div>
            </form>
        </div>
        <table class="xllist" style="min-width: 999px;">
            <tr>
                <th width="50px">序号</th>
                <th width="100px">表单名称</th>
                <th width="100px">评价类型</th>
                <th width="50px">状态</th>
                <th width="100px">操作</th>
            </tr>
            <c:forEach items="${resAssessCfgs}" var="resAssessCfg" varStatus="assessCfg">
                <tr>
                    <td>${assessCfg.index + 1}</td>
                    <td>${resAssessCfg.formName}</td>
                    <td>${resAssessCfg.cfgCodeName}</td>
                    <td>${resAssessCfg.formStatusName}</td>
                    <td>
                        <a onclick="editForm('${resAssessCfg.cfgFlow}','${resAssessCfg.formName}')">
                            <font color="#1e90ff">
                                编辑
                            </font></a>
                        <c:if test="${resAssessCfg.formStatusId eq 'Y'}"><a
                                onclick="editFormStatus('${resAssessCfg.cfgFlow}','${resAssessCfg.cfgCodeId}','${resAssessCfg.formStatusId}')"
                                style="margin-left: 40px">
                            <font color="#1e90ff">
                                停用
                            </font> </a></c:if>
                        <c:if test="${resAssessCfg.formStatusId eq 'N'}">
                            <c:if test="${resAssessCfgMaps[resAssessCfg.cfgFlow] ge 3}">
                                <a onclick="editFormStatus('${resAssessCfg.cfgFlow}','${resAssessCfg.cfgCodeId}','${resAssessCfg.formStatusId}')"
                                   style="margin-left: 40px">
                                    <font color="#1e90ff">
                                        启用
                                    </font></a>
                            </c:if>
                            <c:if test="${resAssessCfgMaps[resAssessCfg.cfgFlow] lt 3 or empty resAssessCfgMaps[resAssessCfg.cfgFlow]}">
                                <a href="#"
                                   style="margin-left: 40px">
                                    <font color="#696969">
                                        启用
                                    </font></a>
                            </c:if>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty resAssessCfgs}">
                <c:set var="colNum" value="5"></c:set>
                <tr>
                    <td align="center" colspan="${colNum }">无记录</td>
                </tr>
            </c:if>
        </table>
        <c:set var="pageView" value="${pdfn:getPageView(resAssessCfgs)}" scope="request"></c:set>
        <pd:pagination toPage="toPage"/>
    </div>
</div>
</div>
</body>
</html>
</head>
<body>

</body>
</html>
