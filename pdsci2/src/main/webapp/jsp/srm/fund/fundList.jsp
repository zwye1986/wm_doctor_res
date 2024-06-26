<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>

    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="false"/>
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
        function font() {
            $("td").css("font-size", "12px");
        }
        var height = (window.screen.height) * 0.7;
        var width = (window.screen.width) * 0.7;
        function search() {
            jboxStartLoading();
            $("#searchForm").submit();
        }
        function toPage(page) {
            if (page != undefined) {
                $("#currentPage").val(page);
            }
            search();
        }
        function showDetail(fundFlow) {
            jboxStartLoading();
            //jboxOpen(rootPath()+"/srm/fund/getDetail?fundFlow="+fundFlow, "经费支出信息", width, height);
            var rpath = rootPath();
            if (rpath.indexOf('srm') < 0) {
                rpath += "/srm";
            }
            jboxOpen(rpath + "/fund/getDetail?startTime=${param.startTime}&endTime=${param.endTime}&fundFlow=" + fundFlow, "经费支出信息", width, height);
        }

        function viewItem(fundFlow, projFlow) {
            var w = $('#mainright').width();
            var h = $('#mainright').height();
            //var url =rootPath()+'/srm/fund/details?fundFlow='+fundFlow+"&projFlow="+projFlow;
            var rpath = rootPath();
            if (rpath.indexOf('srm') < 0) {
                rpath += "/srm";
            }
            var url = rpath + '/fund/details?fundFlow=' + fundFlow + "&projFlow=" + projFlow + "&view=Y";
            var iframe = "<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='700px' height='400px' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='" + url + "'></iframe>";
            jboxMessager(iframe, '经费到账信息', w, 400);

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
        //加载项目状态枚举
        function loadProjStatusId() {
            var paramStatusId = '${param.projStatusId}';
            var status = $("#projStatusId");
            status.html('<option value="">请选择</option>');
            var stage = $("#projStageId").val();
            if (stage) {
                var url = "<s:url value='/srm/proj/search/loadProjStatus'/>?projStageId=" + stage;
                jboxStartLoading();
                jboxGet(url, null, function (data) {
                    for (key in data) {
                        var sel = "";
                        if (key == paramStatusId) {
                            sel = "selected"
                        }
                        status.append('<option value="' + key + '" ' + sel + '>' + data[key] + '</option>');
                    }
                }, null, false);
            }
        }
    </script>
</head>
<body onload="font()">
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm" action="<s:url value="/srm/fund/list/${sessionScope.projListScope}"/>" method="post">
                <div class="searchDiv">
                    &#12288;项目年份：
                    <input type="text" class="xltext ctime" name="startYear" readonly="readonly" style="width: 69px"
                           onClick="WdatePicker({dateFmt:'yyyy'})" value="${param.startYear }<c:if test="${empty param.startYear and defaultTerm eq 'Y'}">${pdfn:getCurrYear()}</c:if>"/> -
                    <input type="text" class="xltext ctime" name="endYear" readonly="readonly" style="width: 69px"
                           onClick="WdatePicker({dateFmt:'yyyy'})" value="${param.endYear }<c:if test="${empty param.endYear and defaultTerm eq 'Y'}">${pdfn:getCurrYear()}</c:if>"/>
                </div>
                <div class="searchDiv">
                    &#12288;项目阶段：
                    <select id="projStageId" name="projStageId" onchange="loadProjStatusId();"
                            class="xlselect">
                        <option value="">请选择</option>
                        <c:forEach var="stage" items="${projStageEnumList}">
                            <c:if test="${stage.id ne 'Award'}">
                                <option value="${stage.id}"
                                        <c:if test="${param.projStageId==stage.id}">selected</c:if>>${stage.name}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
                <div class="searchDiv">
                    &#12288;项目状态：
                    <select id="projStatusId" name="projStatusId" class="xlselect">
                        <option value="">请选择项目阶段</option>
                    </select>
                </div>
                <div class="searchDiv">
                    &#12288;项目来源：
                    <select id="projDeclarerFlow" name="projDeclarerFlow" class="xlselect">
                        <option value="">请选择</option>
                        <c:forEach items="${dictTypeEnumProjTypeSourceList}" var="dict" varStatus="status">
                            <option dictFlow="${dict.dictFlow}" value="${dict.dictId}"
                                    <c:if test="${param.projDeclarerFlow eq dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="searchDiv">
                    &#12288;科&#12288;&#12288;室：
                    <input id="trainDept" class="xltext" name="applyDeptName" type="text"
                           value="${param.applyDeptName}" autocomplete="off"/>
                    <input id="deptFlow" name="applyDeptFlow" class="input" value="${param.applyDeptFlow}" type="text"
                           hidden style="margin-left: 0px;"/>
                </div>

                <div class="searchDiv">
                    &#12288;课题编号：
                    <input class="xltext" name="projNo" type="text"
                           value="${param.projNo}"/>
                </div>
                <c:if test="${sysCfgMap['srm_local_type'] ne 'Y'}">
                    <div class="searchDiv">
                        &#12288;课题账号：
                        <input class="xltext" name="accountNo" type="text"
                               value="${param.accountNo}"/>
                    </div>
                </c:if>
                <div class="searchDiv">
                    &#12288;&nbsp;负&nbsp;责&nbsp;人：
                    <input class="xltext" name="applyUserName" type="text" style="margin-left: 2px"
                           value="${param.applyUserName}"/>
                </div>
                <div class="searchDiv">
                    &#12288;计划类别：
                    <select name="planTypeId" class="xlselect">
                        <option value="">请选择</option>
                        <c:forEach items="${dictTypeEnumPlanCategoryList}" var="dict" varStatus="status">
                            <option value="${dict.dictId}"
                                    <c:if test="${param.planTypeId eq dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                        </c:forEach>
                    </select>
                </div>
                <c:if test="${sysCfgMap['srm_local_type'] ne 'Y'}">
                <div class="searchDiv" >
                    &#12288;报销<%--（到账）--%>时间：
                    <input onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="width: 150px;" class="ctime"
                           name="startTime" type="text" value="${param.startTime}"/> —
                    <input onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="width: 150px;" class="ctime"
                           name="endTime" type="text" value="${param.endTime}"/>
                </div>
                </c:if>
                <div class="searchDiv">
                    &#12288;
                    <input class="search" type="button" value="查&#12288;询" onclick="search()"/>
                </div>

                <input id="currentPage" type="hidden" name="currentPage" value=""/>
            </form>
        </div>
        <table class="xllist">
            <thead>
            <tr>
                <th style="width:7%;">课题编号</th>
                <c:if test="${sysCfgMap['srm_local_type'] ne 'Y'}">
                    <th width="7%">课题账号</th>
                </c:if>
                <c:if test="${sessionScope.projListScope=='local'}">
                    <th width="7%">项目负责人</th>
                </c:if>
                <th style="width: 13%">项目名称</th>
                <c:if test="${sysCfgMap['srm_local_type'] ne 'Y'}">
                <th style="width:10%">一级来源</th>
                </c:if>
                <c:if test="${sysCfgMap['srm_local_type'] eq 'Y'}">
                    <th style="width:10%">项目类别</th>
                </c:if>
                <th style="width:7%;">预算总经费(元)</th>
                <th style="width:7%;">下拨金额(元)</th>
                <th style="width:7%;">配套金额(元)</th>
                <th style="width:7%;">到账(元)</th>
                <th style="width:7%;">支出(元)</th>
                <th style="width:7%;">剩余(元)</th>
                <c:if test="${sysCfgMap['srm_local_type'] ne 'Y'}">
                <th style="width:7%;">操作</th>
                </c:if>
            </tr>
            </thead>
            <c:forEach items="${pubProjExtList}" var="projExt">
                <tr>
                    <td>${projExt.projNo}</td>
                    <c:if test="${sysCfgMap['srm_local_type'] ne 'Y'}">
                        <td>${projExt.accountNo}</td>
                    </c:if>
                    <c:if test="${sessionScope.projListScope=='local'}">
                        <td>${projExt.applyUserName}</td>
                    </c:if>
                    <td>${projExt.projName}</td>
                    <c:if test="${sysCfgMap['srm_local_type'] ne 'Y'}">
                    <td>${projExt.projDeclarer}</td>
                    </c:if>
                    <c:if test="${sysCfgMap['srm_local_type'] eq 'Y'}">
                        <td>${projExt.projTypeName}</td>
                    </c:if>
                    <td>${pdfn:transMultiply(projExt.projFundInfo.budgetAmount,10000)}</td>
                    <td>${pdfn:transMultiply(projExt.projFundInfo.budgetGove,10000)}</td>
                    <td>${pdfn:transMultiply(projExt.projFundInfo.budgetOrg,10000)}</td>
                    <td <%--style="color: #00a0ff;cursor:pointer" onclick="viewItem('${projExt.projFundInfo.fundFlow}','${projExt.projFlow}')"--%>>${pdfn:transMultiply(projExt.projFundInfo.realityAmount,10000)}</td>
                    <td>${pdfn:transMultiply(projExt.projFundInfo.yetPaymentAmount,10000)}</td>
                    <td>${pdfn:transMultiply(projExt.projFundInfo.realityBalance,10000)}</td>
                    <c:if test="${sysCfgMap['srm_local_type'] ne 'Y'}">
                    <td><a href="javascript:void(0)" onclick="showDetail('${projExt.projFundInfo.fundFlow}')">查看</a>
                    </td>
                    </c:if>
                </tr>
            </c:forEach>

            <tr>
                <td colspan="
                <c:choose>
                    <c:when test="${(sessionScope.projListScope=='local') and (sysCfgMap['srm_local_type'] eq 'Y')}">4</c:when>
                    <c:when test="${(sessionScope.projListScope=='local') and (sysCfgMap['srm_local_type'] ne 'Y')}">5</c:when>
                    <c:when test="${(sessionScope.projListScope!='local') and (sysCfgMap['srm_local_type'] eq 'Y')}">3</c:when>
                    <c:when test="${(sessionScope.projListScope!='local') and (sysCfgMap['srm_local_type'] ne 'Y')}">4</c:when>
                </c:choose>" style="color: red;">当前页合计
                </td>
                <td style="color: red;">${pdfn:transMultiply(fundSum.amountFundSum,10000) }</td>
                <td style="color: red;">${pdfn:transMultiply(fundSum.goveFundSum,10000) }</td>
                <td style="color: red;">${pdfn:transMultiply(fundSum.orgFundSum,10000) }</td>
                <td style="color: red;">${pdfn:transMultiply(fundSum.realityAmountSum,10000) }</td>
                <td style="color: red;">${pdfn:transMultiply(fundSum.yetPaymentAmountSum,10000) }</td>
                <td style="color: red;">${pdfn:transMultiply(fundSum.realityBalanceSum,10000) }</td>
                <c:if test="${sysCfgMap['srm_local_type'] ne 'Y'}">
                <td></td>
                </c:if>
            </tr>

        </table>
        <p style="text-align: right">
            <%--<span style="color: red;">预算总经费总计：${pdfn:transMultiply(allFundSum.amountFundSum,10000) }&#12288;</span>
            <span style="color: red;">下拨金额总计：${pdfn:transMultiply(allFundSum.goveFundSum,10000) }&#12288;</span>
            <span style="color: red;">配套金额总计：${pdfn:transMultiply(allFundSum.orgFundSum,10000) }&#12288;</span>--%>
            <span style="color: red;">到账金额总计：${pdfn:transMultiply(allFundSum.realityAmountSum,10000) }&#12288;</span>
            <span style="color: red;"> 支出总计：${pdfn:transMultiply(allFundSum.yetPaymentAmountSum,10000) }&#12288;</span>
            <span style="color: red;">剩余总计：${pdfn:transMultiply(allFundSum.realityBalanceSum,10000) }&#12288;</span>
        </p>
        <c:set var="pageView" value="${pdfn:getPageView(pubProjExtList)}" scope="request"></c:set>
        <pd:pagination toPage="toPage"/>
    </div>
</div>
</body>
</html>