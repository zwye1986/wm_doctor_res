<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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
    <%--<script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>"></script>--%>
    <script type="text/javascript">
        function font(){
            $("td").css("font-size","12px");
        }
        var height=(window.screen.height)*0.7;
        var width=(window.screen.width)*0.7;
        function search() {
            jboxStartLoading();
            $("#searchForm").submit();
        }
        function toPage(page) {
            if(page!=undefined){
                $("#currentPage").val(page);
            }
            search();
        }
        function editItem(fundFlow , projFlow){
            var w = $('#mainright').width();
            var h = $('#mainright').height();
            //var url =rootPath()+'/srm/fund/details?fundFlow='+fundFlow+"&projFlow="+projFlow;
            var rpath = rootPath();
            if(rpath.indexOf('srm') < 0){
                rpath += "/srm";
            }
            var url =rpath+'/fund/feeDetails?fundFlow='+fundFlow+"&projFlow="+projFlow;
            var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='400px' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
            jboxMessager(iframe,'管理费扣除编辑',w , 400);

        }

       /* function initDept() {
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
        });*/
    </script>
</head>
<body onload="font()">

<div class="mainright" id="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm" action="<s:url value="/srm/fund/feeList/${sessionScope.projListScope}"/>" method="post">
                <div class="searchDiv">
                    项目名称：
                    <input type="text" name="projName" value="${param.projName}" class="xltext"/>
                    </div>
                <%--<div class="searchDiv">
                    科&#12288;&#12288;室：
                    <input id="trainDept" class="xltext" name="deptName" type="text"
                           value="${param.deptName}" autocomplete="off"/>
                    <input id="deptFlow" name="deptFlow" class="input" value="${param.deptFlow}" type="text"
                           hidden style="margin-left: 0px;"/>
                </div>--%>
                <div class="searchDiv">
                        &#12288;负责人：
                        <input type="text" name="applyUserName" value="${param.applyUserName}" class="xltext"/>
                  </div>
                <div class="searchDiv">
                    <input id="currentPage" type="hidden" name="currentPage" value=""/>
                    <input type="button" class="search" onclick="search();" value="查&#12288;询">
                </div>
            </form>
        </div>

        <table class="xllist">
            <thead>
            <tr>
                <th>项目名称</th>
                <th width="150px;">项目类型</th>
                <th width="100px;">负责人</th>
                <th width="100px;">项目开始时间</th>
                <th width="100px;">项目结束时间</th>
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
                    <td>${pubProjExt.projName}</td>
                    <td>${pubProjExt.projTypeName}</td>
                    <td>${pubProjExt.applyUserName}</td>
                    <td>${pubProjExt.projStartTime}</td>
                    <td>${pubProjExt.projEndTime}</td>
                    <!-- <td>${fundExt.fund.goveFund}</td> -->
                    <!-- <td>${fundExt.fund.orgFund}</td> -->
                    <td>${pdfn:transMultiply(pubProjExt.projFundInfo.budgetAmount,10000)}</td>
                    <td>${pdfn:transMultiply(pubProjExt.projFundInfo.realityGoveAmount,10000)}</td>
                    <td>${pdfn:transMultiply(pubProjExt.projFundInfo.realityOrgAmount,10000)}</td>
                    <td>${pdfn:transMultiply(pubProjExt.projFundInfo.realityAmount,10000)}</td>
                    <td>${pdfn:transMultiply(pubProjExt.projFundInfo.realityBalance,10000)}</td>
                        <%--<c:if test="${sessionScope.projListScope=='local'}" ></c:if>--%>
                    <td><a href="javascript:void(0)" onclick="editItem('${pubProjExt.projFundInfo.fundFlow}' , '${pubProjExt.projFlow}')">编辑</a><%-- <c:if test="${sessionScope.projListScope!='local'}" ><a href="javascript:void(0)" onclick="">[查看]</a></c:if> --%></td>
                </tr>
            </c:forEach>
        </table>
        <c:set var="pageView" value="${pdfn:getPageView(PubProjExtList)}" scope="request"></c:set>
        <pd:pagination toPage="toPage"/>
    </div>
</div>
</body>
</html>