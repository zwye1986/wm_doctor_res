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

            var url = "<s:url value="/srm/regis/proj/details/audit?fundFlow="/>" + fundFlow + "&projFlow=" + projFlow;
            <c:if test="${scope eq 'director'}">
            var url = "<s:url value="/srm/regis/proj/details/director?fundFlow="/>" + fundFlow + "&projFlow=" + projFlow;
            </c:if>
            jboxOpen(url, "经费报销编辑", 800, 600,false);
        }
        function exportExcel(fundFlow, projFlow) {
            $("#fundFlow").val(fundFlow);
            $("#projFlow").val(projFlow);
            var url = "<s:url value='/srm/regis/proj/exportDetails'/>";
            jboxSubmit($('#exportForm'), url, null, null);
            jboxEndLoading();
        }

        function initDept() {
            var datas = [];
            <c:forEach items="${deptList}" var="dept">
            var d = {};

            d.id = "${dept.deptFlow}";
            d.text = "${dept.deptName}";
            datas.push(d);
            </c:forEach>
            var itemSelectFuntion = function () {
                $("#deptFlow").val(this.id);
            };
            $.selectSuggest('trainDept', datas, itemSelectFuntion, "deptFlow", true);
        }

        $(document).ready(function () {
                initDept();
        });
    </script>
</head>
<body onload="font()">

<div class="mainright" id="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm" action="<s:url value="/srm/regis/proj/auditRemburseList/${scope}"/>"
                  method="post">
                <p>
                <div class="searchDiv">
                    &#12288;姓&#12288;&#12288;名：
                    <input type="text" name="applyUserName" value="${param.applyUserName}" class="xltext"/>
                </div>
                <div class="searchDiv">
                    &#12288;项目来源：
                    <select name="projDeclarerFlow" class="xlselect">
                    <option value="">请选择项目来源</option>
                    <c:forEach var="dict" items="${dictTypeEnumWxeyProjSourceList}">
                        <option value="${dict.dictId}"
                                <c:if test='${param.projDeclarer==dict.dictId}'>selected="selected"</c:if>>${dict.dictName}</option>
                    </c:forEach>
                </select>
                </div>
                <div class="searchDiv">
                    &#12288;项目类别：
                    <select class="xlselect" name="projSubTypeId">
                        <option value="">请选择</option>
                        <c:forEach var="dict" items="${dictTypeEnumWxeyProjTypeList}">
                            <option value="${dict.dictId}"
                                    <c:if test='${param.projSubTypeId==dict.dictId}'>selected="selected"</c:if>>${dict.dictName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="searchDiv">
                    &#12288;工&#12288;&#12288;号：
                    <input type="text" name="applyUserEmpnum" value="${param.applyUserEmpnum}" class="xltext"/>
                </div>
                <c:if test="${not (scope eq 'director')}">
                <div class="searchDiv">
                    &#12288;科&#12288;&#12288;室：
                        <input id="trainDept" class="xltext" name="applyDeptName" type="text"
                               value="${param.applyDeptName}" autocomplete="off"/>
                        <input id="deptFlow" name="applyDeptFlow" class="input" value="${param.applyDeptFlow}" type="text"
                               hidden style="margin-left: 0px;"/>
                </div>
                </c:if>
                <div class="searchDiv">
                    &#12288;支&#12288;&#12288;部：
                    <select name="branchId" class="xlselect">
                    <option value="">请选择</option>
                    <c:forEach var="dict" items="${dictTypeEnumWxeyBranchList}">
                        <option value="${dict.dictId}"
                                <c:if test='${param.branchId==dict.dictId}'>selected="selected"</c:if>>${dict.dictName}</option>
                    </c:forEach>
                </select>
                </div>
                <div class="searchDiv">
                    <input id="currentPage" type="hidden" name="currentPage" value=""/>
                    &#12288;<input type="button" class="search" onclick="search();" value="查&#12288;询">
                    <%--&#12288;<input type="button" class="search" onclick="exportExcel();" value="导&#12288;出">--%>
                </div>
                </p>
            </form>
            <form id="exportForm">
                <input type="hidden" id="fundFlow" name="fundFlow"/>
                <input type="hidden" id="projFlow" name="projFlow"/>
            </form>
        </div>

        <table class="xllist">
            <thead>
            <tr>
                <th width="10%">年 度</th>
                <th width="15%">项目编号</th>
                <th width="25%">项目名称</th>
                <th width="15%">项目类别</th>
                <th width="8%">项目奖金</th>
                <th width="8%">项目签名</th>
                <th width="8%">科室</th>
                <th width="8%">支部</th>
                <th width="20%">操 作</th>
            </tr>
            </thead>
            <c:forEach items="${regisProjFundExtList}" var="fundExt">
                <tr>
                    <td>${fundExt.projYear}</td>
                    <td>${fundExt.projNo}</td>
                    <td>${fundExt.projName}</td>
                    <td>${fundExt.projSubTypeName}</td>
                    <td>${fundExt.projFundInfo.amountFund}</td>
                    <td>${fundExt.applyUserName}</td>
                    <td>${fundExt.applyDeptName}</td>
                    <td>${fundExt.branchName}</td>
                    <td>
                        <c:if test="${not (scope eq 'director')}">
                        <a href="javascript:void(0)"
                           onclick="editItem('${fundExt.projFundInfo.fundFlow}' , '${fundExt.projFlow}')">[报销审核<span style="color: #0000ff;">(${fundExt.fundDetailNum})</span>]</a>&#12288;
                        </c:if>
                        <c:if test="${scope eq 'director'}">
                            <a href="javascript:void(0)"
                               onclick="editItem('${fundExt.projFundInfo.fundFlow}' , '${fundExt.projFlow}')">[查看]</a>&#12288;
                        </c:if>
                        <a href="javascript:void(0)"
                           onclick="exportExcel('${fundExt.projFundInfo.fundFlow}' , '${fundExt.projFlow}');">[导出]</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <c:set var="pageView" value="${pdfn:getPageView(regisProjFundExtList)}" scope="request"></c:set>
        <pd:pagination toPage="toPage"/>
    </div>
</div>
</div>
</body>
</html>