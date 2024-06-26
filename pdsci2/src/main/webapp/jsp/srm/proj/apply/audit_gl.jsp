<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
    </jsp:include>
</head>
<script type="text/javascript">

    function saveAudit(agreeFlag) {
        if (agreeFlag == "${GlobalConstant.FLAG_Y}") {
            $('#auditContent').removeClass("validate[required,maxSize[200]]");
        } else {
            $('#auditContent').addClass("validate[required,maxSize[200]]");
        }
        if ($("#auditForm").validationEngine("validate")) {
            var tip = agreeFlag == "${GlobalConstant.FLAG_N}" ? "退回" : "审核通过";
            var url = "<s:url value='/srm/proj/apply/saveAudit/${sessionScope.projListScope}/${sessionScope.projCateScope}'/>?agreeFlag=" + agreeFlag;
            jboxConfirm("确认" + tip + "?", function () {
                jboxStartLoading();
                jboxPost(url, $('#auditForm').serialize(), function () {
                    window.parent.frames['mainIframe'].window.searchProj();
                    jboxClose();
                }, null, true);
            });
        }
    }

    function checkApplyLimit(agreeFlag) {
        if ("${GlobalConstant.PROJ_STATUS_SCOPE_LOCAL}" == "${sessionScope.projListScope}") {
            var url = "<s:url value='/srm/proj/apply/checkApplyLimit/${sessionScope.projListScope}'/>?agreeFlag=" + agreeFlag;
            jboxStartLoading();
            jboxPost(url, $('#auditForm').serialize(), function (resp) {
                if ("${GlobalConstant.FLAG_Y}" == resp.resultFlag) {
                    saveAudit(agreeFlag);
                } else {
                    jboxEndLoading();
                    jboxInfo("${proj.projTypeName} 年度限报数为 <font color='red'>" + resp.limitNum + "</font> ，已审核通过  <font color='red'>" + resp.auditCount + "</font>，无法审核.");
                }
            }, null, false);
        } else {
            saveAudit(agreeFlag);
        }
    }
</script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <div>
                <table>
                    <tr height="30px">
                        <td style="font-weight: bold;">项目名称：</td>
                        <td colspan="3"><a target="_blank" style="color:blue;"
                                           href="<s:url value='/srm/proj/mine/auditProjView?projFlow=${proj.projFlow}'/>">${proj.projName}</a>
                        </td>
                    </tr>
                    <tr height="30px">
                        <%--<td style="font-weight: bold;">项目类型：</td>--%>
                        <%--<td width="200px">${proj.projTypeName}</td>--%>
                        <td style="font-weight: bold;">项目来源：</td>
                        <td width="200px">一级来源&#12288;${proj.projDeclarer}<br />二级来源&#12288;${proj.projSecondSourceName}</td>
                        <td style="font-weight: bold;">起止时间：</td>
                        <td>${proj.projStartTime}~${proj.projEndTime}</td>
                    </tr>
                    <tr height="30px">
                        <td style="font-weight: bold;">承担单位：</td>
                        <td>${proj.applyOrgName}</td>
                        <td style="font-weight: bold;">负责人：</td>
                        <td>${proj.applyUserName}</td>
                    </tr>
                </table>
            </div>
        </div>
        <hr/>
        <form id="auditForm">
            <table cellpadding="0" cellspacing="0" style="width: 100%">
                <tr>
                    <td style="text-align: center">
                        <table cellpadding="0" cellspacing="0" style="width: 100%">
                            <tr style="height: 25px; background-image: url(../css/skin/${skinPath}/images/top_bg.jpg); background-repeat: repeat-x">
                                <td colspan="2" style="width: 100%; text-align: left">
                                    &#12288;&#12288;&#12288;<span style="font-weight: bold">项目审核信息：<font color="red">
                                    项目被退回时必须填写“审核意见”(最多200个字)</font></span>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" style="height: 10px">
                                </td>
                            </tr>

                            <tr>
                                <td style="width: 15%; text-align: right; vertical-align: top; padding-top: 3px">
                                    &#12288;审核意见：&#12288;
                                </td>
                                <td style="width: 85%; text-align: left">
                                    <textarea id="auditContent" name="auditContent" rows="6" cols="20"
                                              class="textbox validate[maxSize[200]]"
                                              style="width:90%;margin-top:5px;margin-bottom:5px;"
                                              placeholder="请填写审核意见..."></textarea>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                </td>
                                <td style="text-align:left;padding-top:5px;">
                                    <input class='search' onclick="checkApplyLimit('${GlobalConstant.FLAG_Y}')"
                                           type='button' value='同&#12288;意'/>&#12288;
                                    <input class='search' onclick="saveAudit('${GlobalConstant.FLAG_N}')" type='button'
                                           value='不同意'/>&#12288;
                                </td>

                            </tr>
                            <tr>
                                <td colspan="2" style="height: 10px">
                                    <input type="hidden" name="projFlow" value="${proj.projFlow }"/>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>
