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
        function editItem(fundFlow, projFlow) {
            var w = $('#mainright').width();
            var h = $('#mainright').height();
            //var url =rootPath()+'/srm/fund/details?fundFlow='+fundFlow+"&projFlow="+projFlow;
            var rpath = rootPath();
            if (rpath.indexOf('srm') < 0) {
                rpath += "/srm";
            }
            var url = rpath + '/fund/details?fundFlow=' + fundFlow + "&projFlow=" + projFlow;
            var iframe = "<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='400px' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='" + url + "'></iframe>";
            jboxMessager(iframe, '经费到账编辑', w, 400);

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

        function importIncomes(){
            var url = "<s:url value='/srm/fund/importIncomes'/>";
            jboxOpen(url, "经费到账导入", 500, 200);
        }
    </script>
</head>
<body onload="font()">

<div class="mainright" id="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm" action="<s:url value="/srm/fund/accountsList/${sessionScope.projListScope}"/>"
                  method="post">
                <div class="searchDiv">
                    &#12288;科&#12288;&#12288;室：
                    <input id="trainDept" class="xltext" name="applyDeptName" type="text"
                           value="${param.applyDeptName}" autocomplete="off"/>
                    <input id="deptFlow" name="applyDeptFlow" class="input" value="${param.applyDeptFlow}" type="text"
                           hidden style="margin-left: 0px;"/>
                </div>
                <div class="searchDiv">
                    项目来源：
                    <select id="projDeclarerFlow" name="projDeclarerFlow" class="xlselect">
                        <option value="">请选择</option>
                        <c:forEach items="${dictTypeEnumProjTypeSourceList}" var="dict" varStatus="status">
                            <option dictFlow="${dict.dictFlow}" value="${dict.dictId}"
                                    <c:if test="${param.projDeclarerFlow eq dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                        </c:forEach>
                    </select>
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
                    &#12288; 负 责 人：
                    <input class="xltext" name="applyUserName" type="text"
                           value="${param.applyUserName}"/>
                </div>
                <div class="searchDiv">
                    <input id="currentPage" type="hidden" name="currentPage" value=""/>
                    <input type="button" class="search" onclick="search();" value="查&#12288;询">
                    <c:if test="${sysCfgMap['srm_local_type'] ne 'Y'}">
                        <input type="button" class="search" onclick="importIncomes();" value="到账导入">
                    </c:if>
                </div>
            </form>
        </div>

        <table class="xllist">
            <thead>
            <tr>
                <c:if test="${sysCfgMap['srm_local_type'] ne 'Y'}">
                    <th width="100px">课题账号</th>
                </c:if>
                <th width="100px">课题编号</th>
                <th>项目名称</th>
                <th width="150px;">项目类型</th>
                <th width="100px;">负责人</th>
                <%--<th width="100px;">项目开始时间</th>--%>
                <%--<th width="100px;">项目结束时间</th>--%>
                <!-- <th width="8%">下拨金额</th> -->
                <!-- <th width="8%">配套金额</th> -->
                <th width="60px;">预算总额(元)</th>
                <th width="80px;">下拨到账金额(元)</th>
                <th width="80px;">配套到账金额(元)</th>
                <th width="80px">到账总金额(元)</th>
                <th width="60px">到账余额(元)</th>
                <th width="80px;">操作</th>
            </tr>
            </thead>
            <c:forEach items="${PubProjExtList}" var="pubProjExt">
                <tr>
                    <c:if test="${sysCfgMap['srm_local_type'] ne 'Y'}">
                        <td>${pubProjExt.accountNo}</td>
                    </c:if>
                    <td>${pubProjExt.projNo}</td>
                    <td>${pubProjExt.projName}</td>
                    <td>${pubProjExt.projTypeName}</td>
                    <td>${pubProjExt.applyUserName}</td>
                    <td>${pdfn:transMultiply(pubProjExt.projFundInfo.budgetAmount,10000)}</td>
                    <td>${pdfn:transMultiply(pubProjExt.projFundInfo.realityGoveAmount,10000)}</td>
                    <td>${pdfn:transMultiply(pubProjExt.projFundInfo.realityOrgAmount,10000)}</td>
                    <td>${pdfn:transMultiply(pubProjExt.projFundInfo.realityAmount,10000)}</td>
                    <td>${pdfn:transMultiply(pubProjExt.projFundInfo.realityBalance,10000)}</td>
                        <%--<c:if test="${sessionScope.projListScope=='local'}" ></c:if>--%>
                    <td>
                        <c:choose>
                            <c:when test="${projListScope eq 'finance'}">
                                <a href="javascript:void(0)"
                                   onclick="editItem('${pubProjExt.projFundInfo.fundFlow}' , '${pubProjExt.projFlow}')">管理</a>
                            </c:when>
                            <c:otherwise>
                                <a href="javascript:void(0)"
                                   onclick="editItem('${pubProjExt.projFundInfo.fundFlow}' , '${pubProjExt.projFlow}')">编辑</a>
                            </c:otherwise>
                        </c:choose>
                            <%-- <c:if test="${sessionScope.projListScope!='local'}" ><a href="javascript:void(0)" onclick="">[查看]</a></c:if> --%>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <c:set var="pageView" value="${pdfn:getPageView(PubProjExtList)}" scope="request"></c:set>
        <pd:pagination toPage="toPage"/>
    </div>
</div>
</div>
</body>
</html>