<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
    </jsp:include>
</head>
<script type="text/javascript">
    function searchProj() {
        jboxStartLoading();
        var url = "<s:url value='/srm/proj/search/recList?projFlow=${proj.projFlow}'/>";
        window.location = url;
    }
    //加载归档审核界面
    function archiveAudit(projFlow) {
        jboxStartLoading();
        jboxOpen("<s:url value='/srm/proj/archive/audit'/>?projFlow=" + projFlow, "审核", 900, 600);
    }
    //加载申报审核界面
    function applyAudit(projFlow) {
        jboxStartLoading();
        jboxOpen("<s:url value='/srm/proj/apply/audit?projFlow='/>" + projFlow, "审核", 900, 600);
    }
    //加载立项审核界面
    function setUp(projFlow) {
        jboxStartLoading();
        jboxOpen("<s:url value='/srm/proj/approve/setUp?projFlow='/>" + projFlow, "立项信息", 900, 600);

    }
    //加载合同审核界面
    function contractAudit(projFlow) {
        jboxStartLoading();
        jboxOpen("<s:url value='/srm/proj/contract/audit'/>?projFlow=" + projFlow, "审核", 900, 600);
    }
    //加载进展报告审核界面
    function scheduleAudit(recFlow) {
        var url = "<s:url value='/srm/proj/schedule/audit?recFlow='/>" + recFlow;
        jboxStartLoading();
        jboxGet(url, null, function (data) {
            $("#auditDiv").html(data);
            $("#auditDiv").hide();
            $("#auditDiv").slideDown(500);
        }, null, false);
    }
    //加载中止审核界面
    function terminateAudit(projFlow) {
        jboxStartLoading();
        jboxOpen("<s:url value='/srm/proj/terminate/audit'/>?projFlow=" + projFlow, "审核", 900, 600);
    }
    //加载验收审核界面
    function completeAudit(projFlow) {
        jboxStartLoading();
        jboxOpen("<s:url value='/srm/proj/complete/audit'/>?projFlow=" + projFlow, "审核", 900, 600);
    }
    //加载下拨页面
    function fundPlan(projFlow) {
        jboxStartLoading();
        jboxOpen('<s:url value="/srm/proj/approve/editFundPlan"/>?projFlow=' + projFlow, "下拨", 800, 600);
    }
    //加载项目归档页面
    function archiveAudit(projFlow) {
        jboxStartLoading();
        jboxOpen("<s:url value='/srm/proj/archive/audit'/>?projFlow=" + projFlow, "审核", 900, 600);
    }

    //添加表单
    function editForm(projFlow, recTypeId, tip) {

        jboxConfirm("确认" + tip + "？", function () {
            jboxStartLoading();
            <%--window.location.href="<s:url value='/srm/proj/mine/showStep?projFlow='/>" + projFlow+"&recTypeId="+recTypeId;--%>
            var url = "<s:url value='/srm/proj/add/addForm?projFlow='/>" + projFlow + "&recTypeId=" + recTypeId;
            jboxOpen(url, tip, 600, 400, true);
        });
    }

    //修改表单信息
    function editProjRec(projFlow, recTypeId, recFlow) {
        //编辑申报书信息
        jboxConfirm("确认编辑申报书信息？", function () {
            jboxStartLoading();
            window.location.href = "<s:url value='/srm/proj/mine/showStep'/>?projFlow=" + projFlow + "&recTypeId=" + recTypeId;
        });
    }

    function reAuditOption(projFlow) {
        jboxConfirm("确认重审申报书？", function () {
            jboxPost("<s:url value='/srm/proj/reAudit/reAuditOption?projFlow='/>" + projFlow, null, function (resp) {
                    window.location.reload();
            }, null, true);
        }, null);
    }
    function approveReAudit(projFlow) {
        jboxConfirm("确认重新立项？", function () {
            jboxPost("<s:url value='/srm/proj/reAudit/approveReAudit?projFlow='/>" + projFlow, null, function (resp) {
                    window.location.reload();
            }, null, true);
        }, null);
    }
    function contractReAudit(projFlow) {
        jboxConfirm("确认重审合同？", function () {
            jboxPost("<s:url value='/srm/proj/reAudit/contractReAudit?projFlow='/>" + projFlow, null, function (resp) {
                    window.location.reload();
            }, null, true);
        }, null);
    }
    function scheduleReAudit(projFlow) {
        jboxConfirm("确认重审进展报告？", function () {
            jboxPost("<s:url value='/srm/proj/reAudit/scheduleReAudit?projFlow='/>" + projFlow, null, function (resp) {
                    window.location.reload();
            }, null, true);
        }, null);
    }
    function changeReAudit(projFlow) {
        jboxConfirm("确认重审变更申请？", function () {
            jboxPost("<s:url value='/srm/proj/reAudit/scheduleReAudit?projFlow='/>" + projFlow, null, function (resp) {
                    window.location.reload();
            }, null, true);
        }, null);
    }
    function completeReAudit(projFlow) {
        jboxConfirm("确认重审验收报告？", function () {
            jboxPost("<s:url value='/srm/proj/reAudit/completeReAudit?projFlow='/>" + projFlow, null, function (resp) {
                    window.location.reload();
            }, null, true);
        }, null);
    }
    function terminateReAudit(projFlow) {
        jboxConfirm("确认重审中止申请？", function () {
            jboxPost("<s:url value='/srm/proj/reAudit/terminateReAudit?projFlow='/>" + projFlow, null, function (resp) {
                    window.location.reload();
            }, null, true);
        }, null);
    }
</script>
</head>
<style type="text/css">
    a {
        color: blue;
    }

    a:hover {
        color: #ff4f15;
    }
</style>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <div>
                <table>
                    <tr height="30px">
                        <td style="font-weight: bold;">项目名称：</td>
                        <td
                                <c:if test="${applicationScope.sysCfgMap['srm_for_use'] == GlobalConstant.PROJ_STATUS_SCOPE_LOCAL}">colspan="3" </c:if> >
                            <a target="_blank" style="color:blue;"
                               href="<s:url value='/srm/proj/mine/auditProjView?projFlow=${proj.projFlow}'/>">${proj.projName}</a>
                        </td>
                        <c:if test="${applicationScope.sysCfgMap['srm_for_use']=='global' }">
                            <c:if test="${sessionScope.projListScope=='global'}">
                                <td style="font-weight: bold;">拨款状态：</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${(proj.projStageId eq projStageEnumApprove.id and projApproveStatusEnumNotApprove.id != proj.projStatusId and projApproveStatusEnumApproving.id != proj.projStatusId)
        		          or proj.projStageId eq projStageEnumContract.id or proj.projStageId eq projStageEnumSchedule.id or proj.projStageId eq projStageEnumComplete.id}">
                                            &#12288;<a href="#" onclick="fundPlan('${proj.projFlow}')"><font style="color:red;">点我拨款</font></a>
                                        </c:when>
                                        <c:otherwise>
                                            <font style="color:red;"> 目前不能拨款</font>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </c:if>
                        </c:if>
                    </tr>
                    <tr height="30px">
                        <td style="font-weight: bold;">项目类型：</td>
                        <td width="200px">${proj.projTypeName}</td>
                        <td style="font-weight: bold;">起止时间：</td>
                        <td>${proj.projStartTime}~${proj.projEndTime}</td>
                    </tr>
                    <tr height="30px">
                        <td style="font-weight: bold;">承担单位：</td>
                        <td>${proj.applyOrgName}</td>
                        <td style="font-weight: bold;">负责人：</td>
                        <td>${proj.applyUserName}</td>
                    </tr>
                    <tr height="30px">
                        <td style="font-weight: bold;">当前阶段：</td>
                        <td>${proj.projStageName}</td>
                        <td style="font-weight: bold;">当前状态：</td>
                        <td>${proj.projStatusName}</td>
                    </tr>
                    <tr height="30px">
                        <td style="font-weight: bold;">项目编号：</td>
                        <td>
                            <c:if test="${not empty proj.projNo}">${proj.projNo}</c:if>
                            <c:if test="${empty proj.projNo}">无</c:if>
                        </td>
                    </tr>
                </table>
            </div>
        </div>

        <div>
            <table class="xllist">
                <tr>
                    <th width="20%">记录名</th>
                    <th width="20%">项目负责人</th>
                    <th width="20%">所处阶段</th>
                    <th width="20%">所在状态</th>
                    <th width="20%">操作</th>
                </tr>
                <tr>
                    <td>项目基本信息</td>
                    <td>${proj.applyUserName}</td>
                    <td></td>
                    <td></td>
                    <td><a target="_blank" style="color:blue;"
                           href="<s:url value='/srm/proj/mine/auditProjView?projFlow=${proj.projFlow}'/>">[查看]</a></td>
                </tr>
                <c:forEach items="${recList }" var="projRec">
                    <tr>
                        <td>${projRec.recTypeName}</td>
                        <td>${projRec.operUserName}</td>
                        <td>${projRec.projStageName}</td>
                        <td>${projRec.projStatusName}</td>
                        <td>
                            <a target="_blank" style="color:blue;"
                               href="<s:url value='/srm/proj/mine/auditProjView?projFlow=${proj.projFlow}&recFlow=${projRec.recFlow}'/>">[查看]</a>
                            <c:if test="${applicationScope.sysCfgMap['srm_for_use'] == GlobalConstant.PROJ_STATUS_SCOPE_LOCAL and (applicationScope.sysCfgMap['srm_local_type'] == 'common')}">
                                <!-- 院版功能管理员编辑申报书 -->
                                <c:if test="${sessionScope.projListScope == GlobalConstant.PROJ_STATUS_SCOPE_LOCAL}">
                                    <c:if test='${(projRec.recTypeId ne projRecTypeEnumInfo.id) and (projRec.projStatusId ne "Apply")}'>
                                        <a style="color:blue;" href="javascript:void(0);"
                                           onclick="editProjRec('${projRec.projFlow}','${projRec.recTypeId}','${projRec.recFlow}');">[编辑]</a>
                                    </c:if>
                                </c:if>
                            </c:if>
                            <c:if test="${applicationScope.sysCfgMap['srm_for_use'] == GlobalConstant.PROJ_STATUS_SCOPE_GLOBAL }">
                                <!-- 卫生局登录 -->
                                <c:if test="${sessionScope.projListScope == GlobalConstant.PROJ_STATUS_SCOPE_GLOBAL}">
                                    <c:if test='${projRec.recTypeId eq projRecTypeEnumContract.id and projRec.projStatusId eq projContractStatusEnumThirdAudit.id and recCount==2}'>
                                        <a style="color:blue;" href="javascript:void(0);"
                                           onclick="controctBackForThirdAudit('${projRec.recFlow}');">[退回]</a>
                                        <script>
                                            function controctBackForThirdAudit(recFlow) {
                                                jboxConfirm("确认退回？", function () {
                                                    var url = "<s:url value='/srm/proj/contract/controctbackforthridaudit?recFlow='/>" + recFlow;
                                                    jboxGet(url, null, function (data) {
                                                        window.location.reload(true);
                                                    });
                                                });
                                            }
                                        </script>
                                    </c:if>
                                </c:if>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div style="margin-top: 20px;">
            <font style="font-weight: bold;"> 该项目目前可以执行的操作有：</font><br/>
            <!-------------------------- 卫生局版本 ----------------------------------->
            <c:if test="${applicationScope.sysCfgMap['srm_for_use'] == GlobalConstant.PROJ_STATUS_SCOPE_GLOBAL }">
                <!-- 卫生局登录 -->
                <c:if test="${sessionScope.projListScope == GlobalConstant.PROJ_STATUS_SCOPE_GLOBAL}">

                    <c:if test="${proj.projStageId eq projStageEnumApprove.id and proj.projStatusId eq projApproveStatusEnumApproving.id}">
                        &#12288;<a href="<s:url
                            value='/srm/proj/evaluation/groupProjConfig?projFlow=${proj.projFlow}&evaluationId=${evaluationEnumApproveEvaluation.id}'/>">[立项评审]</a>
                        &#12288;<a href="#" onclick="setUp('${proj.projFlow}')">[立项审批]</a>
                    </c:if>


                    <c:if test="${proj.chargeOrgFlow eq sysCfgMap['global_org_flow'] }">
                        <c:if test="${proj.projStageId eq projStageEnumApply.id and proj.projStatusId == projApplyStatusEnumFirstAudit.id}">
                            &#12288;<a href="#" onclick="applyAudit('${proj.projFlow}')">[申报审核]</a>
                        </c:if>
                        <c:if test="${proj.projStageId eq projStageEnumContract.id and proj.projStatusId == projContractStatusEnumFirstAudit.id}">
                            &#12288;<a href="#" onclick="contractAudit('${proj.projFlow}')">[合同审核]</a>
                        </c:if>
                        <c:if test="${proj.projStageId eq projStageEnumSchedule.id and (proj.projStatusId == projScheduleStatusEnumFirstAudit.id)}">
                            <c:if test="${scheduleRec.recTypeId eq projRecTypeEnumScheduleReport.id }">
                                &#12288;<a href="#" onclick="scheduleAudit('${scheduleRec.recFlow}')">[进展报告审核]</a>
                            </c:if>
                            <c:if test="${scheduleRec.recTypeId eq projRecTypeEnumChangeReport.id }">
                                &#12288;<a href="#" onclick="scheduleAudit('${scheduleRec.recFlow}')">[变更报告审核]</a>
                            </c:if>
                        </c:if>
                        <c:if test="${proj.projStageId eq projStageEnumComplete.id and proj.projStatusId == projApplyStatusEnumFirstAudit.id}">
                            <c:if test="${completeRec.recTypeId eq projRecTypeEnumCompleteReport.id }">
                                &#12288;<a href="#" onclick="completeAudit('${proj.projFlow}')">[验收报告审核]</a>
                            </c:if>
                            <c:if test="${completeRec.recTypeId eq projRecTypeEnumTerminateReport.id }">
                                &#12288;<a href="#" onclick="terminateAudit('${proj.projFlow}')">[中止报告审核]</a>
                            </c:if>
                        </c:if>

                    </c:if>

                    <c:if test="${proj.chargeOrgFlow != sysCfgMap['global_org_flow'] }">
                        <c:if test="${proj.projStageId eq projStageEnumApply.id and proj.projStatusId == projApplyStatusEnumSecondAudit.id}">
                            &#12288;<a href="#" onclick="applyAudit('${proj.projFlow}')">[申报审核]</a>
                        </c:if>
                        <c:if test="${proj.projStageId eq projStageEnumContract.id and proj.projStatusId == projContractStatusEnumSecondAudit.id}">
                            &#12288;<a href="#" onclick="contractAudit('${proj.projFlow}')">[合同审核]</a>
                        </c:if>
                        <c:if test="${proj.projStageId eq projStageEnumSchedule.id and (proj.projStatusId == projScheduleStatusEnumSecondAudit.id)}">
                            <c:if test="${scheduleRec.recTypeId eq projRecTypeEnumScheduleReport.id }">
                                &#12288;<a href="#" onclick="scheduleAudit('${scheduleRec.recFlow}')">[进展报告审核]</a>
                            </c:if>
                            <c:if test="${scheduleRec.recTypeId eq projRecTypeEnumChangeReport.id }">
                                &#12288;<a href="#" onclick="scheduleAudit('${scheduleRec.recFlow}')">[变更报告审核]</a>
                            </c:if>
                        </c:if>
                        <c:if test="${proj.projStageId eq projStageEnumComplete.id and proj.projStatusId == projApplyStatusEnumSecondAudit.id}">
                            <c:if test="${completeRec.recTypeId eq projRecTypeEnumCompleteReport.id }">
                                &#12288;<a href="#" onclick="completeAudit('${proj.projFlow}')">[验收报告审核]</a>
                            </c:if>
                            <c:if test="${completeRec.recTypeId eq projRecTypeEnumTerminateReport.id }">
                                &#12288;<a href="#" onclick="terminateAudit('${proj.projFlow}')">[中止报告审核]</a>
                            </c:if>
                        </c:if>
                    </c:if>

                    <c:if test="${proj.projStageId eq projStageEnumComplete.id and proj.projStatusId eq projCompleteStatusEnumThirdAudit.id }">
                        <c:if test="${completeRec.recTypeId != projRecTypeEnumTerminateReport.id}">
                            &#12288;<a href="<s:url
                                value='/srm/proj/evaluation/groupProjConfig?projFlow=${proj.projFlow}&evaluationId=${evaluationEnumCompleteEvaluation.id}'/>">[验收评审]</a>
                        </c:if>
                        &#12288;<a href="#" onclick="archiveAudit('${proj.projFlow}')">[项目归档]</a>
                    </c:if>

                </c:if>


                <!-- 主管部门登陆 -->
                <c:if test="${sessionScope.projListScope == GlobalConstant.PROJ_STATUS_SCOPE_CHARGE}">

                    <c:if test="${(proj.projStageId eq projStageEnumApply.id) and (proj.projStatusId eq projApplyStatusEnumFirstAudit.id or proj.projStatusId eq projApplyStatusEnumThirdBack.id)}">
                        &#12288;<a href="#" onclick="applyAudit('${proj.projFlow}')">[申报审核]</a>
                    </c:if>

                    <c:if test="${proj.projStageId eq projStageEnumContract.id and (proj.projStatusId eq projContractStatusEnumFirstAudit.id or proj.projStatusId eq projContractStatusEnumThirdBack.id)}">
                        &#12288;<a href="#" onclick="contractAudit('${proj.projFlow}')">[合同审核]</a>
                    </c:if>

                    <c:if test="${proj.projStageId eq projStageEnumSchedule.id and (proj.projStatusId eq projScheduleStatusEnumFirstAudit.id or proj.projStatusId eq projScheduleStatusEnumThirdBack.id)}">
                        <c:if test="${scheduleRec.recTypeId eq projRecTypeEnumScheduleReport.id }">
                            &#12288;<a href="#" onclick="scheduleAudit('${scheduleRec.recFlow}')">[进展报告审核]</a>
                        </c:if>
                        <c:if test="${scheduleRec.recTypeId eq projRecTypeEnumChangeReport.id }">
                            &#12288;<a href="#" onclick="scheduleAudit('${scheduleRec.recFlow}')">[变更报告审核]</a>
                        </c:if>
                    </c:if>

                    <c:if test="${proj.projStageId eq projStageEnumComplete.id and (proj.projStatusId eq projCompleteStatusEnumFirstAudit.id or proj.projStatusId eq projCompleteStatusEnumThirdBack.id)}">
                        <c:if test="${completeRec.recTypeId eq projRecTypeEnumCompleteReport.id }">
                            &#12288;<a href="#" onclick="completeAudit('${proj.projFlow}')">[验收报告审核]</a>
                        </c:if>
                        <c:if test="${completeRec.recTypeId eq projRecTypeEnumTerminateReport.id }">
                            &#12288;<a href="#" onclick="terminateAudit('${proj.projFlow}')">[中止报告审核]</a>
                        </c:if>
                    </c:if>

                </c:if>

                <!-- 医院登录 -->
                <c:if test="${sessionScope.projListScope == GlobalConstant.PROJ_STATUS_SCOPE_LOCAL}">
                    <c:if test="${proj.chargeOrgFlow eq sysCfgMap['global_org_flow'] }">
                        <c:if test="${(proj.projStageId eq projStageEnumApply.id) and (proj.projStatusId eq projApplyStatusEnumSubmit.id or proj.projStatusId eq projApplyStatusEnumThirdBack.id)}">
                            &#12288;
                            [<a href="#" onclick="applyAudit('${proj.projFlow}')">申报审核 </a>]
                            [<a href="javascript:delProj('${proj.projFlow}');">删除</a>]

                            <script>
                                function delProj(projFlow) {
                                    var url = "<s:url value='/srm/proj/mine/del?projFlow='/>" + projFlow;
                                    jboxConfirm("确认删除该项目？", function () {
                                        jboxGet(url, null, function () {
                                            window.location.href = "<s:url value='/srm/proj/search/list/local/${sessionScope.projCateScope}'/>";
                                        }, null, true);
                                    });
                                }
                            </script>
                        </c:if>
                        <c:if test="${proj.projStageId eq projStageEnumContract.id and (proj.projStatusId eq projContractStatusEnumSubmit.id or proj.projStatusId eq projContractStatusEnumThirdBack.id)}">
                            &#12288;<a href="#" onclick="contractAudit('${proj.projFlow}')">[合同审核]</a>
                        </c:if>
                        <c:if test="${proj.projStageId eq projStageEnumSchedule.id and (proj.projStatusId eq projScheduleStatusEnumSubmit.id or proj.projStatusId eq projScheduleStatusEnumThirdBack.id)}">
                            <c:if test="${scheduleRec.recTypeId eq projRecTypeEnumScheduleReport.id }">
                                &#12288;<a href="#" onclick="scheduleAudit('${scheduleRec.recFlow}')">[进展报告审核]</a>
                            </c:if>
                            <c:if test="${scheduleRec.recTypeId eq projRecTypeEnumChangeReport.id }">
                                &#12288;<a href="#" onclick="scheduleAudit('${scheduleRec.recFlow}')">[变更报告审核]</a>
                            </c:if>
                        </c:if>
                        <c:if test="${proj.projStageId eq projStageEnumComplete.id and (proj.projStatusId eq projCompleteStatusEnumSubmit.id or proj.projStatusId eq projCompleteStatusEnumThirdBack.id)}">
                            <c:if test="${completeRec.recTypeId eq projRecTypeEnumCompleteReport.id }">
                                &#12288;<a href="#" onclick="completeAudit('${proj.projFlow}')">[验收报告审核]</a>
                            </c:if>
                            <c:if test="${completeRec.recTypeId eq projRecTypeEnumTerminateReport.id }">
                                &#12288;<a href="#" onclick="terminateAudit('${proj.projFlow}')">[中止报告审核]</a>
                            </c:if>
                        </c:if>

                    </c:if>

                    <c:if test="${proj.chargeOrgFlow != sysCfgMap['global_org_flow'] }">
                        <c:if test="${(proj.projStageId eq projStageEnumApply.id) and (proj.projStatusId eq projApplyStatusEnumSubmit.id or proj.projStatusId eq projApplyStatusEnumSecondBack.id)}">
                            &#12288;
                            [<a href="#" onclick="applyAudit('${proj.projFlow}')">申报审核 </a>]
                            [<a href="javascript:delProj('${proj.projFlow}');">删除</a>]

                            <script>
                                function delProj(projFlow) {
                                    var url = "<s:url value='/srm/proj/mine/del?projFlow='/>" + projFlow;
                                    jboxConfirm("确认删除该项目？", function () {
                                        jboxGet(url, null, function () {
                                            window.location.href = "<s:url value='/srm/proj/search/list/local/${sessionScope.projCateScope}'/>";
                                        }, null, true);
                                    });
                                }
                            </script>
                        </c:if>
                        <c:if test="${proj.projStageId eq projStageEnumContract.id and (proj.projStatusId eq projContractStatusEnumSubmit.id or proj.projStatusId eq projContractStatusEnumSecondBack.id)}">
                            &#12288;<a href="#" onclick="contractAudit('${proj.projFlow}')">[合同审核]</a>
                        </c:if>
                        <c:if test="${proj.projStageId eq projStageEnumSchedule.id and (proj.projStatusId eq projScheduleStatusEnumSubmit.id or proj.projStatusId eq projScheduleStatusEnumSecondBack.id)}">
                            <c:if test="${scheduleRec.recTypeId eq projRecTypeEnumScheduleReport.id }">
                                &#12288;<a href="#" onclick="scheduleAudit('${scheduleRec.recFlow}')">[进展报告审核]</a>
                            </c:if>
                            <c:if test="${scheduleRec.recTypeId eq projRecTypeEnumChangeReport.id }">
                                &#12288;<a href="#" onclick="scheduleAudit('${scheduleRec.recFlow}')">[变更报告审核]</a>
                            </c:if>
                        </c:if>
                        <c:if test="${proj.projStageId eq projStageEnumComplete.id and (proj.projStatusId eq projCompleteStatusEnumSubmit.id or proj.projStatusId eq projCompleteStatusEnumSecondBack.id)}">
                            <c:if test="${completeRec.recTypeId eq projRecTypeEnumCompleteReport.id }">
                                &#12288;<a href="#" onclick="completeAudit('${proj.projFlow}')">[验收报告审核]</a>
                            </c:if>
                            <c:if test="${completeRec.recTypeId eq projRecTypeEnumTerminateReport.id }">
                                &#12288;<a href="#" onclick="terminateAudit('${proj.projFlow}')">[中止报告审核]</a>
                            </c:if>
                        </c:if>
                    </c:if>


                </c:if>

            </c:if>
            <!-------------------------- 医院版本 ----------------------------------->
            <c:if test="${applicationScope.sysCfgMap['srm_for_use'] == GlobalConstant.PROJ_STATUS_SCOPE_LOCAL}">

                <!-- 医院登录 -->
                <c:if test="${sessionScope.projListScope == GlobalConstant.PROJ_STATUS_SCOPE_LOCAL}">

                    <c:if test="${proj.projStageId eq projStageEnumApply.id and proj.projStatusId eq projApplyStatusEnumSubmit.id}">
                        &#12288;<a href="#" onclick="applyAudit('${proj.projFlow}')">[申报审核]</a>
                    </c:if>

                    <%--<c:if test="${proj.projStageId eq projStageEnumApply.id and proj.projStatusId eq projApplyStatusEnumFirstAudit.id}">
                            &#12288;<a style="cursor: pointer;" onclick="reAuditOption('${proj.projFlow}')">[申报重申]</a>
                            &#12288;<a href="<s:url value='/srm/proj/evaluation/groupProjConfig?projFlow=${proj.projFlow}&evaluationId=${evaluationEnumApproveEvaluation.id}'/>">[立项评审]</a>
                            &#12288;<a href="#" onclick="setUp('${proj.projFlow}')">[立项审批]</a>
                    </c:if>--%>

                    <c:if test="${proj.projStageId eq projStageEnumApprove.id and proj.projStatusId eq projApproveStatusEnumApproving.id}">
                        <c:if test="${sessionScope.projCateScope eq projCategroyEnumGl.id}">
                            &#12288;<a style="cursor: pointer;" onclick="reAuditOption('${proj.projFlow}')">[申报重审]</a>
                        </c:if>
                        &#12288;<a href="<s:url
                            value='/srm/proj/evaluation/groupProjConfig?projFlow=${proj.projFlow}&evaluationId=${evaluationEnumApproveEvaluation.id}'/>">[立项评审]</a>
                        &#12288;<a href="#" onclick="setUp('${proj.projFlow}')">[立项审批]</a>
                    </c:if>
                    <c:if test="${proj.projStageId eq projStageEnumContract.id and proj.projStatusId eq projContractStatusEnumSubmit.id}">
                        &#12288;<a href="#" onclick="contractAudit('${proj.projFlow}')">[合同审核]</a>
                    </c:if>

                    <c:if test="${proj.projStageId eq projStageEnumSchedule.id and proj.projStatusId eq projScheduleStatusEnumSubmit.id}">
                        <c:if test="${scheduleRec.recTypeId eq projRecTypeEnumScheduleReport.id }">
                            &#12288;<a href="#" onclick="scheduleAudit('${scheduleRec.recFlow}')">[进展报告审核]</a>
                        </c:if>
                        <c:if test="${scheduleRec.recTypeId eq projRecTypeEnumChangeReport.id }">
                            &#12288;<a href="#" onclick="scheduleAudit('${scheduleRec.recFlow}')">[变更报告审核]</a>
                        </c:if>
                    </c:if>

                    <c:if test="${proj.projStageId eq projStageEnumComplete.id and proj.projStatusId eq projCompleteStatusEnumSubmit.id}">
                        <c:if test="${completeRec.recTypeId eq projRecTypeEnumCompleteReport.id }">
                            &#12288;<a href="#" onclick="completeAudit('${proj.projFlow}')">[验收报告审核]</a>
                        </c:if>
                        <c:if test="${completeRec.recTypeId eq projRecTypeEnumTerminateReport.id }">
                            &#12288;<a href="#" onclick="terminateAudit('${proj.projFlow}')">[中止报告审核]</a>
                        </c:if>
                    </c:if>

                    <c:if test="${sessionScope.projCateScope eq projCategroyEnumGl.id}">
                        <c:if test="${proj.projStageId eq projStageEnumContract.id and proj.projStatusId eq projContractStatusEnumApply.id}">
                            &#12288;<a href="#" onclick="approveReAudit('${proj.projFlow}')">[立项重审]</a>
                        </c:if>
                        <c:if test="${proj.projStageId eq projStageEnumSchedule.id and proj.projStatusId eq projScheduleStatusEnumApply.id}">
                            <c:if test="${(empty scheduleRec) and (empty scheduleRec) and (contentIsExist eq 'Y')}">
                                &#12288;<a href="#" onclick="contractReAudit('${scheduleRec.recFlow}')">[合同重审]</a>
                            </c:if>
                            <c:if test="${(empty scheduleRec) and (empty scheduleRec) and (contentIsExist eq 'N')}">
                                &#12288;<a href="#" onclick="approveReAudit('${proj.projFlow}')">[立项重审]</a>
                            </c:if>
                        </c:if>
                        <c:if test="${proj.projStageId eq projStageEnumSchedule.id and proj.projStatusId eq projScheduleStatusEnumFirstAudit.id}">
                            <c:if test="${scheduleRec.recTypeId eq projRecTypeEnumScheduleReport.id }">
                                &#12288;<a href="#" onclick="scheduleReAudit('${scheduleRec.recFlow}')">[进展报告重审]</a>
                            </c:if>
                            <c:if test="${scheduleRec.recTypeId eq projRecTypeEnumChangeReport.id }">
                                &#12288;<a href="#" onclick="changeReAudit('${scheduleRec.recFlow}')">[变更申请重审]</a>
                            </c:if>
                        </c:if>

                        <c:if test="${proj.projStageId eq projStageEnumComplete.id and proj.projStatusId eq projCompleteStatusEnumFirstAudit.id}">
                            <c:if test="${completeRec.recTypeId eq projRecTypeEnumCompleteReport.id }">
                                &#12288;<a href="#" onclick="completeReAudit('${proj.projFlow}')">[验收报告重审]</a>
                            </c:if>
                            <c:if test="${completeRec.recTypeId eq projRecTypeEnumTerminateReport.id }">
                                &#12288;<a href="#" onclick="terminateReAudit('${proj.projFlow}')">[中止报告重审]</a>
                            </c:if>
                        </c:if>
                    </c:if>

                    <c:if test="${proj.projStageId eq projStageEnumComplete.id and proj.projStatusId eq projCompleteStatusEnumFirstAudit.id }">
                        <c:if test="${completeRec.recTypeId != projRecTypeEnumTerminateReport.id}">
                            &#12288;<a href="<s:url
                                value='/srm/proj/evaluation/groupProjConfig?projFlow=${proj.projFlow}&evaluationId=${evaluationEnumCompleteEvaluation.id}'/>">[验收评审]</a>
                        </c:if>
                        &#12288;<a href="#" onclick="archiveAudit('${proj.projFlow}')">[项目归档]</a>
                    </c:if>
                    <c:if test="${proj.projTypeId eq 'jsszyy.ywxm'}">
                        <c:set var="isAdd" value="Y"/>
                        <c:choose>
                            <c:when test="${proj.projStageId eq 'Contract'}">
                                <c:forEach items="${recs}" var="rec">
                                    <c:if test="${rec.recTypeId eq 'Contract'}">
                                        <c:set var="isAdd" value="N"/>
                                    </c:if>
                                </c:forEach>
                                <c:if test="${isAdd eq 'Y'}">
                                    &#12288;&#12288;<a style="cursor: pointer" onclick="editForm('${proj.projFlow}','Contract','添加合同')">[添加合同]</a>
                                </c:if>
                            </c:when>
                            <c:when test="${proj.projStageId eq 'Schedule'}">
                                <c:forEach items="${recs}" var="rec">
                                    <c:if test="${(rec.recTypeId eq 'ScheduleReport') and (rec.projStatusId ne 'FirstAudit')}">
                                        <c:set var="isAdd" value="N"/>
                                    </c:if>
                                    <c:if test="${(rec.recTypeId eq 'ChangeReport') and (rec.projStatusId ne 'FirstAudit')}">
                                        <c:set var="isAdd" value="N"/>
                                    </c:if>
                                </c:forEach>
                                <c:if test="${isAdd eq 'Y'}">
                                    &#12288;&#12288;<a style="cursor: pointer" onclick="editForm('${proj.projFlow}','ScheduleReport','添加进展报告')">[添加进展报告]</a>&#12288;&#12288;
                                    <a style="cursor: pointer"
                                       onclick="editForm('${proj.projFlow}','ChangeReport','添加变更申请')">[添加变更申请]</a>&#12288;&#12288;
                                    <a style="cursor: pointer"
                                       onclick="editForm('${proj.projFlow}','TerminateReport','添加中止报告')">[添加中止报告]</a>&#12288;&#12288;
                                    <a style="cursor: pointer"
                                       onclick="editForm('${proj.projFlow}','CompleteReport','添加验收报告')">[添加验收报告]</a>
                                </c:if>
                            </c:when>
                            <%--<c:when test="${proj.projStageId eq 'Complete'}">
                                <c:forEach items="${recs}" var="rec">
                                <c:if test="${rec.recTypeId eq 'CompleteReport'}">
                                    <c:set var="isAdd" value="N" />
                                </c:if>
                                <c:if test="${rec.recTypeId eq 'TerminateReport'}">
                                    <c:set var="isAdd" value="N" />
                                </c:if>
                                </c:forEach>
                                <c:if test="${isAdd eq 'Y'}">

                                </c:if>
                            </c:when>--%>
                        </c:choose>
                    </c:if>
                </c:if>
            </c:if>
        </div>
        <div id="auditDiv" style="display: none;margin-top: 30px;">
        </div>

    </div>
</div>

</body>
</html>
