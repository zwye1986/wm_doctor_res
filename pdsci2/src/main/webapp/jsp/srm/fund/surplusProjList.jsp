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
        var height = (window.screen.height) * 0.5;

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

        function surplusBalance(projTypeId, fundFlow) {
            var tip = '';
            if (projTypeId == 'jsszyy.ywxm') {
                tip = '院外项目的结转按照10%到K001账号，90%到个人账号的比例结转，';
            } else if (projTypeId == 'jsszyy.kyxm') {
                tip = '院内项目结转按照80%到K001-1账号，20%到个人账号的比例结转，';
            }
            jboxConfirm(tip + "确认结余剩余经费?", function () {
                jboxStartLoading();
                var fundFlows = [];
                fundFlows.push(fundFlow);
                var url = "<s:url value='/srm/surplus/surplusBalance'/>";
                jboxPostJson(url, JSON.stringify(fundFlows), function () {
                    window.location.reload();
                }, null, true);

            });
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
        function checkAll(obj) {
            var isChecked = $(obj).prop("checked");
            $(".xllist input[name='childCheck']").each(function (index, element) {
                $(element).prop("checked", isChecked);
            });
        }
        $(function () {
            $(".changeCheck").on("change", function () {
                var isCheckAll = true;
                $(".xllist input[name='childCheck']").each(function (index, element) {
                    if (!$(element).prop("checked")) {
                        isCheckAll = false;
                    }
                });
                $("#checkAll").prop("checked", isCheckAll);
            });
        })

        function surplusAllBalance() {
            var checkedFlag = false;
            jboxConfirm("确认结余所有选中项目剩余经费?", function () {
                var fundFlows = [];
                var i = 0;
                $(".xllist input[name='childCheck']").each(function (index, element) {
                    if ($(element).prop("checked")) {
                        var fundFlow = $(element).val();
                        checkedFlag = true;
                        fundFlows.push(fundFlow);
                    }
                });
                if (!checkedFlag) {
                    jboxTip("未选择任何项目！");

                } else {
                    jboxStartLoading();
                    var url = "<s:url value='/srm/surplus/surplusBalance'/>";
                    jboxPostJson(url, JSON.stringify(fundFlows), function () {
                        window.location.reload();
                    }, null, true);
                }
            });

        }
        function surplusHistory(fundFlow) {
            /*var width = $('#mainright').width();*/
            jboxStartLoading();
            jboxOpen("<s:url value='/srm/surplus/surplusHistory'/>?fundFlow=" + fundFlow, "经费结余记录", 650, 370);
        }
        function surplusBalanceExport() {
            var url = "<s:url value='/srm/surplus/exportSurplusDetails'/>";
            jboxSubmit($('#searchForm'), url, null, null);
            jboxEndLoading();
        }
    </script>
    <style type="text/css">
        a {
            color: #00a0ff;
        }

        a:hover {
            color: #ff6600
        }
    </style>
</head>
<body>
<div class="mainright" id="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm" action="<s:url value="/srm/surplus/surplusProjInfo/${sessionScope.projListScope}"/>"
                  method="post">
                <%--<div class="searchDiv">
                    &#12288;科&#12288;&#12288;室：
                    <input id="trainDept" class="xltext" name="applyDeptName" type="text"
                           value="${param.applyDeptName}" autocomplete="off"/>
                    <input id="deptFlow" name="applyDeptFlow" class="input" value="${param.applyDeptFlow}" type="text"
                           hidden style="margin-left: 0px;"/>
                </div>--%>
                <div class="searchDiv">
                    &#12288;一级来源：
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
                <div class="searchDiv">
                    &#12288;课题账号：
                    <input class="xltext" name="accountNo" type="text"
                           value="${param.accountNo}"/>
                </div>

                <%--<div class="searchDiv">
                    &#12288; 负 责 人：
                    <input class="xltext"  name="applyUserName" type="text"
                           value="${param.applyUserName}"/>
                </div>--%>
                <input id="currentPage" type="hidden" name="currentPage" value=""/>
                <div class="searchDiv">
                    &#12288;<input type="button" class="search" onclick="search();" value="查&#12288;询">
                    <%--&#12288;<input type="button" class="search" onclick="surplusAllBalance();" value="批量结转">--%>
                    &#12288;<input type="button" class="search" onclick="surplusBalanceExport();" value="经费剩余情况导出">
                </div>
            </form>
        </div>

        <table class="xllist">
            <colgroup>
                <col width="10%"/>
                <col width="10%"/>
                <col width="10%"/>
                <col width="10%"/>
                <col width="30%"/>
                <col width="10%"/>
                <col width="10%"/>
                <col width="10%"/>
            </colgroup>
            <thead>
            <tr>
                <th><input type="checkbox" id="checkAll" onchange="checkAll(this)"/></th>
                <th>课题账户</th>
                <th>课题编号</th>
                <th>项目负责人</th>
                <th>项目名称</th>
                <th>剩余经费（元）</th>
                <th>结转经费（元）</th>
                <th>
                    <a href="javascript:void(0)" onclick="surplusAllBalance()">[批量结转]</a>
                </th>
            </tr>
            </thead>
            <c:forEach items="${pubProjExtList}" var="pubProjExt">
                <tr>
                    <td>
                        <input type="checkbox" name="childCheck" class="changeCheck"
                               value="${pubProjExt.projFundInfo.fundFlow}"/>
                    </td>
                    <td>${pubProjExt.accountNo}</td>
                    <td>${pubProjExt.projNo}</td>
                    <td>${pubProjExt.applyUserName}</td>
                    <td>${pubProjExt.projName}</td>
                    <td>${pdfn:transMultiply(pubProjExt.projFundInfo.realityBalance,10000)}</td>
                    <td>
                        <a href="javascript:void(0)" onclick="surplusHistory('${pubProjExt.projFundInfo.fundFlow}')">${pdfn:transMultiply(pubProjExt.projFundInfo.surplusFund,10000)}</a>
                    </td>
                    <td><a href="javascript:void(0)"
                           onclick="surplusBalance('${pubProjExt.projTypeId}' , '${pubProjExt.projFundInfo.fundFlow}')">[结转]</a><%-- <c:if test="${sessionScope.projListScope!='local'}" ><a href="javascript:void(0)" onclick="">[查看]</a></c:if> --%>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <c:set var="pageView" value="${pdfn:getPageView(pubProjExtList)}" scope="request"></c:set>
        <pd:pagination toPage="toPage"/>
    </div>
</div>
</div>
</body>
</html>