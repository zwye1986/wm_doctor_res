<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
    </jsp:include>
</head>
<script type="text/javascript">

    function addReport(projFlow, recTypeId, auditId) {
        var reportName = "进展报告";
        if (recTypeId == "${projRecTypeEnumChangeReport.id}") {
            reportName = "变更申请";
        } else if (recTypeId == "${projRecTypeEnumDelayReport.id}") {
            reportName = "延期申请";
        }
        jboxConfirm("确认新增" + reportName + "?", function () {
            jboxStartLoading();
            window.location.href = "<s:url value='/srm/proj/mine/showStep?projFlow='/>" + projFlow + "&recTypeId=" + recTypeId + "&isAdd=Y";
        });
    }
    //删除季报
    function del(recFlow) {
        var url = "<s:url value='/srm/proj/mine/delReport?recFlow='/>" + recFlow;
        jboxConfirm("确认删除？", function () {
            jboxStartLoading();
            jboxGet(url, null, function () {
                window.location.reload();
            }, null, true);
        });
    }


    //维护验收申请书
    function editCompleteReport(projFlow, recTypeId, completeFlag) {
        if (completeFlag == "0") {
            jboxConfirm("确认编辑验收申请？", function () {
                jboxStartLoading();
                window.location.href = "<s:url value='/srm/proj/mine/showStep?projFlow='/>" + projFlow + "&recTypeId=" + recTypeId;
            });
        } else {
            jboxTip("您有未提交或是未审核通过的报告，暂时无法填写验收报告！");
        }

    }

    //清空申请信息的内容
    function delRecContent(projFlow, recTypeId, recFlow) {
        var url = "<s:url value='/srm/proj/mine/delRecContent'/>";
        jboxConfirm("确认删除,删除后将会清空申请信息?", function () {
            var datas = {"projFlow": projFlow, "recTypeId": recTypeId, "recFlow": recFlow};
            jboxStartLoading();
            jboxPost(url, datas, null, null, true);
        });
    }

    //编辑季报
    function editScheduleReport(projFlow, recTypeId, recFlow) {

        jboxConfirm("确认编辑该记录信息？", function () {
            jboxStartLoading();
            window.location.href = "<s:url value='/srm/proj/mine/showStep'/>?recFlow=" + recFlow + "&projFlow=" + projFlow + "&recTypeId=" + recTypeId;
        });
    }
    //编辑终止报告
    function editTerminateReport(projFlow, recTypeId, completeFlag) {
        if (completeFlag == "0") {
            jboxConfirm("确认编辑中止报告信息？", function () {
                jboxStartLoading();
                window.location.href = "<s:url value='/srm/proj/mine/showStep'/>?projFlow=" + projFlow + "&recTypeId=" + recTypeId;
            });
        } else {
            jboxTip("您有未提交或是未审核通过的报告，暂时无法填写中止报告！");
        }
    }

    //编辑申报书信息
    function editApplyInfo(projFlow, recTypeId, recFlow) {
        jboxConfirm("确认编辑申报书信息？", function () {
            jboxStartLoading();
            window.location.href = "<s:url value='/srm/proj/mine/showStep'/>?projFlow=" + projFlow + "&recTypeId=" + recTypeId;
        });
    }
    //编辑合同信息
    function editContractInfo(projFlow, recTypeId) {
        jboxConfirm("确认编辑合同信息？", function () {
            jboxStartLoading();
            window.location.href = "<s:url value='/srm/proj/mine/showStep'/>?projFlow=" + projFlow + "&recTypeId=" + recTypeId;
        });
    }

    //送审
    function review(projFlow, recTypeId, recFlow) {
        var url = "";
        if (recFlow) {
            url = "<s:url value='/srm/proj/mine/prepareReview'/>?projFlow=" + projFlow + "&recFlow=" + recFlow;
        } else if (projFlow && recTypeId) {
            url = "<s:url value='/srm/proj/mine/prepareReview'/>?projFlow=" + projFlow + "&recTypeId=" + recTypeId;
        }

        jboxConfirm("确认送审,送审后无法修改?", function () {
            jboxStartLoading();
            jboxGet(url, null, function (resp) {
                if (resp == "1") {
                    jboxTip("送审成功");
                    window.location.href = "<s:url value='/srm/proj/mine/process?projFlow='/>" + projFlow;
                } else {
                    jboxTip("信息填写不完整，请填写后送审!");
                }

            }, null, false);
        });
    }
</script>
<style type="text/css">
    #tagContent {
        background: #f8f8f8;
        padding: 15px 10px;
    }

    .butt_see {
        padding: 3px 10px;
    }

    .tagContent_yd {
        background: #f8f8f8;
        padding: 15px;
        border: #d0e3f2 1px solid;
        border-radius: 5px 5px 0 0;
        margin-top: 15px;
    }
</style>
</head>

<body>
<div id="main">
    <div class="mainright">
        <div class="content">
            <div>
                <table class="xllist">
                    <tr>
                        <th colspan="4" style="text-align: left;">&#12288;项目信息</th>
                    </tr>
                    <tr>
                        <td width="66%" style="text-align: left" colspan="2">&#12288;<b>项目名称：</b><a style="color: blue"
                                                                                                    href="<s:url value='/srm/proj/mine/projView?projFlow=${proj.projFlow}'/>"
                                                                                                    target="_blank">${proj.projName}</a>
                        </td>
                        <td width="33%" style="text-align: left">&#12288;<b>年&#12288;&#12288;份：</b>${proj.projYear}</td>
                        <%--<td width="200px" style="text-align: left">&#12288;<b>项目编号：</b>${proj.projNo}</td>--%>
                    </tr>
                    <tr>
                        <td width="33%" style="text-align: left">&#12288;<b>项目类型：</b>${proj.projTypeName}</td>
                        <%--<td width="200px" style="text-align: left">&#12288;<b>起止时间：</b>${proj.projStartTime}~${proj.projEndTime}</td>--%>
                        <td width="33%" style="text-align: left">&#12288;<b>当前阶段：</b>${proj.projStageName}</td>
                        <td width="33%" style="text-align: left">&#12288;<b>当前状态：</b>${proj.projStatusName}</td>
                    </tr>
                </table>
            </div>
            <c:set var="icn" value="1"/>
            <c:if test="${proj.projStatusId eq projApproveStatusEnumAwardPrizes.id}">
                <c:set var="icn" value="2"/>
            </c:if>
            <c:if test="${proj.projStatusId eq projApproveStatusEnumNoPrizes.id}">
                <c:set var="icn" value="2"/>
            </c:if>
            <div class="flow_list" id="icn">
                <ul>
                    <li class="list_1_${icnMap['0']}"><span>1</span><br/><a
                            <c:if test="${icnMap['0']=='2'}">onclick="selectTag('0',this)"</c:if>
                            href="javascript:void(0)">项目申报</a></li>
                    <li class="list_2_${icnMap['1']}"><span>2</span><br/><a
                            <c:if test="${icnMap['1']=='2'}">onclick="selectTag('1',this)"</c:if>
                            href="javascript:void(0)">项目评审</a></li>
                    <%--<li class="list_2_${icnMap['2']}" ><span>3</span><br/><a <c:if test="${icnMap['2']=='2'}">onclick="selectTag('2',this)"</c:if> href="javascript:void(0)">合同信息</a></li>--%>
                    <%--<li class="list_2_${icnMap['3']}" ><span>4</span><br/><a <c:if test="${icnMap['3']=='2'}">onclick="selectTag('3',this)"</c:if> href="javascript:void(0)">项目进展</a></li>--%>
                    <li class="list_3_${icn}"><span>3</span><br/><a
                            <c:if test="${icn=='2'}">onclick="selectTag('2',this)"</c:if>
                            href="javascript:void(0)">确认发奖</a></li>
                </ul>
            </div>
            <div id="tagContent">
                <!-- 申报 -->
                <div id="tagContent_0" <c:if test='${tab!=projStageEnumApply.id}'>style="display: none;" </c:if>>
                    <c:if test="${projStageEnumApply.id==proj.projStageId and projApplyStatusEnumApply.id==proj.projStatusId}">
                        <a href='<s:url value="/srm/proj/mine/editProjInfo"/>?projFlow=${proj.projFlow}' target="_self"
                           class="butt_see" title="编辑项目基本信息">项目基本信息</a>&nbsp;&nbsp;
                        <c:if test="${GlobalConstant.FLAG_Y eq applyExists}">
                            <a href="javascript:void(0)" id="projApplyButton"
                               onclick="editApplyInfo('${proj.projFlow}' , '${projRecTypeEnumApply.id}' , '');"
                               class="butt_see" title="编辑申报书">申报书</a>&nbsp;&nbsp;
                            <a href="javascript:void(0)" id="delApplyButton"
                               onclick="delRecContent('${proj.projFlow}' , '${projRecTypeEnumApply.id}' , '');"
                               class="butt_see" title="清空申报书">清空申报书</a>&nbsp;&nbsp;
                        </c:if>
                        <a href="javascript:void(0)" id="projApplyButton"
                           onclick="review('${proj.projFlow}' , '${projRecTypeEnumApply.id}' , '');" class="butt_see"
                           title="申报信息送出给单位审核">送&#12288;审</a>&nbsp;&nbsp;
                    </c:if>
                    <c:if test='${pageContext.request.remoteAddr eq applicationScope.sysCfgMap["super_ip"] }'>
                        <a href='<s:url value="/srm/proj/mine/editProjInfo"/>?projFlow=${proj.projFlow}' target="_self"
                           class="butt_see" title="编辑项目基本信息">项目基本信息2</a>&nbsp;&nbsp;
                        <a href="javascript:void(0)" id="projApplyButton"
                           onclick="editApplyInfo('${proj.projFlow}' , '${projRecTypeEnumApply.id}' , '');"
                           class="butt_see" title="编辑申报书">申报书2</a>&nbsp;&nbsp;
                    </c:if>
                    <a target="_blank" href="<s:url value='/srm/proj/mine/projView?projFlow=${proj.projFlow}'/>"
                       class='butt_see' title="查看详细信息">查&#12288;看</a>
                </div>
                <!-- 申报结束 -->

                <!-- 立项开始 -->
                <div id="tagContent_1" <c:if test='${tab!=projStageEnumApprove.id}'>style="display: none;" </c:if>>
                    <!-- 评审信息开始 -->
                    <c:if test="${!empty srmExpertGroupProj}">
                        <table width="100%" class="bs_tb">
                            <thead>
                            <tr>
                                <th colspan="4" class="bs_name">评审信息</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <th width="10%">评审形式：</th>
                                <td>
                                        ${srmExpertGroupProj.evaluationWayName}
                                </td>
                                <th width="10%">评审时间：</th>
                                <td>
                                    <c:if test='${evaluationWayEnumNetWorkWay.id eq srmExpertGroupProj.evaluationWayId}'>
                                        ${srmExpertGroupProj.beginDate} ~ ${srmExpertGroupProj.endDate}
                                    </c:if>
                                    <c:if test='${evaluationWayEnumMeetingWay.id eq srmExpertGroupProj.evaluationWayId}'>
                                        ${expertGroup.meetingDate}&nbsp;&nbsp;&nbsp;&nbsp;${expertGroup.meetingStartTime} ~ ${expertGroup.meetingEndTime}
                                    </c:if>
                                </td>
                            </tr>
                            <c:if test='${evaluationWayEnumMeetingWay.id eq srmExpertGroupProj.evaluationWayId}'>
                                <tr>
                                    <th width="10%">评审意见：</th>
                                    <td colspan="3">
                                            ${srmExpertGroupProj.evalOpinion}
                                    </td>
                                </tr>
                            </c:if>

                            </tbody>
                        </table>
                        <br/>
                    </c:if>
                </div>
                <!-- 评审信息结束 -->
                <div id="tagContent_2" <c:if test='${tab!=projStageEnumAward.id}'>style="display: none;" </c:if>>
                    <c:choose>
                        <c:when test="${ empty process }">
                            暂无发奖信息
                        </c:when>
                        <c:otherwise>
                            <!-- 立项信息开始 -->
                            <table width="100%" class="xllist">
                                <thead>
                                <tr>
                                    <th colspan="6" class="bs_name" style="text-align: left;">&#12288;奖项信息</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                        <%--<th width="10%">项目编号：</th>--%>
                                        <%--<td width="20%">--%>
                                        <%--${proj.projNo}--%>
                                        <%--</td>--%>
                                    <th width="10%">奖项意见：</th>
                                    <td colspan="3">
                                            ${process.auditContent}
                                    </td>
                                    <th width="10%">报奖结果：</th>
                                    <td width="20%">
                                            ${process.processRemark}
                                    </td>
                                </tr>
                                <tr>
                                    <th width="10%">操作人：</th>
                                    <td width="20%">
                                            ${process.operUserName}
                                    </td>
                                    <th width="10%">操作机构：</th>
                                    <td width="20%">
                                            ${process.operOrgName}
                                    </td>
                                    <th width="10%">操作时间：</th>
                                    <td width="20%">
                                            ${pdfn:transDate(process.createTime)}
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                            <!-- 立项信息结束 -->
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="tagContent_yd">
                <div id="tagContent_yd0"
                     <c:if test='${empty tab||tab==projStageEnumApply.id}'>style="display: block;"  </c:if>
                     <c:if test='${tab!=projStageEnumApply.id}'>style="display: none;" </c:if> >
                    ${applicationScope.sysCfgMap['srm_ky_guide_1']}
                </div>
                <div id="tagContent_yd1"
                     <c:if test='${tab==projStageEnumApprove.id}'>style="display: block;" </c:if>
                     <c:if test='${tab!=projStageEnumApprove.id}'>style="display: none;" </c:if>>
                    ${applicationScope.sysCfgMap['srm_ky_guide_2']}
                </div>
                <div id="tagContent_yd2"
                     <c:if test='${tab==projStageEnumContract.id}'>style="display: block;" </c:if>
                     <c:if test='${tab!=projStageEnumContract.id}'>style="display: none;" </c:if>>
                    ${applicationScope.sysCfgMap['srm_ky_guide_3']}
                </div>
                <div id="tagContent_yd3"
                     <c:if test='${tab==projStageEnumSchedule.id}'>style="display: block;" </c:if>
                     <c:if test='${tab!=projStageEnumSchedule.id}'>style="display: none;" </c:if>>
                    ${applicationScope.sysCfgMap['srm_ky_guide_4']}
                </div>
                <div id="tagContent_yd4"
                     <c:if test='${tab==projStageEnumComplete.id}'>style="display: block;" </c:if>
                     <c:if test='${tab!=projStageEnumComplete.id}'>style="display: none;" </c:if>>
                    ${applicationScope.sysCfgMap['srm_ky_guide_5']}
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    function selectTag(showContent, selfObj) {
        // 操作标签
        var tag = $('#icn').find("a");
        for (var i = 0; i < tag.length; i++) {
            $(tag[i]).removeClass('pt');
        }
        $(selfObj).addClass('pt');
        // 操作内容
        $("div [id^='tagContent_']").hide();
        $('#tagContent_' + showContent).show();
        $('#tagContent_yd' + showContent).show();
    }
</script>
</body>

</html>
