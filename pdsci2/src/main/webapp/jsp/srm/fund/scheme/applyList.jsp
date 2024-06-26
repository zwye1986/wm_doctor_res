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
        function search() {
            jboxStartLoading();
            $("#budgetList").submit();
        }
        function toPage(page) {
            if(page!=undefined){
                $("#currentPage").val(page);
            }
            search();
        }
        function viewDetail(projFlow){
            var w = $('#mainright').width();
            var url ='<s:url value="/srm/fund/scheme/viewDetailBudget?projFlow="/>'+projFlow;
            var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='460px' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
            jboxMessager(iframe,'预算项查看',w,500);
        }
        function edit(projFlow) {
            var w = $('#mainright').width();
            var url = '<s:url value="/srm/fund/scheme/applyEdit?projFlow="/>' + projFlow;
            var iframe = "<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='460px' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='" + url + "'></iframe>";
            jboxMessager(iframe, '预算项设置', w, 500);
        }
        function view(projFlow) {
            var w = $('#mainright').width();
            var url = '<s:url value="/srm/fund/scheme/applyEdit?projFlow="/>' + projFlow + "&look=look";
            var iframe = "<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='330px' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='" + url + "'></iframe>";
            jboxMessager(iframe, '预算项查看', w, 400);
        }
        function showProcess(fundFlow){
            jboxStartLoading();
            jboxOpen("<s:url value="/srm/payment/showPaymentAudit?fundFlow="/>"+fundFlow,"操作信息", 800, 600);
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
    </script>
</head>
<body>
<div class="mainright" id="mainright">
    <div class="content">
        <form id="budgetList" action="<s:url value="/srm/fund/scheme/applyList/${sessionScope.projCateScope}"/>" method="post">
            <input id="currentPage" type="hidden" name="currentPage" value=""/>
            <div class="title1 clearfix">
                <div class="searchDiv">
                    项目名称：
                    <input type="text" name="projName" class="xltext" value="${param.projName}">
                </div>
                <div class="searchDiv">
                    科&#12288;&#12288;室：
                    <input id="trainDept" class="xltext" name="applyDeptName" type="text"
                           value="${param.applyDeptName}" autocomplete="off"/>
                    <input id="deptFlow" name="applyDeptFlow" class="input" value="${param.applyDeptFlow}" type="text"
                           hidden style="margin-left: 0px;"/>
                </div>
                <div class="searchDiv">
                    课题编号：
                    <input class="xltext" name="projNo" type="text"
                           value="${param.projNo}"/>
                </div>
                <div class="searchDiv">
                    课题账号：
                    <input class="xltext" name="accountNo" type="text"
                           value="${param.accountNo}"/>
                </div>
                <div class="searchDiv">
                    负 责 人 ： <input type="text" name="applyUserName" value="${param.applyUserName}" class="xltext"/>
                </div>
                <div class="searchDiv">
                    <input type="button" class="search" onclick="search();" value="查&#12288;询">
                </div>
            </div>

            <table class="xllist" id="mubiao">
                <!--  <tr>
                    <th colspan="7" class="bs_name">项目列表</th>
                 </tr> -->
                <thead>
                <tr>

                    <th style="width:150px;">课题编号</th>
                    <c:if test="${sysCfgMap['srm_local_type'] ne 'Y'}">
                    <th style="width:150px;">课题账号</th>
                    </c:if>
                    <th>项目名称</th>
                    <th style="width:150px;">项目类型</th>
                    <th style="width:100px;">开始时间</th>
                    <th style="width:100px;">结束时间</th>
                    <th style="width:70px;">预算总金额(元)</th>
                    <th style="width:100px;">审核状态</th>
                    <th style="width:100px;">操作</th>
                </tr>
                </thead>
                <c:forEach var="proj" items="${projList}">
                    <tr>
                        <td>${proj.projNo}</td>
                        <c:if test="${sysCfgMap['srm_local_type'] ne 'Y'}">
                        <td>${proj.accountNo}</td>
                        </c:if>
                        <td>${proj.projName}</td>
                        <td>${proj.projTypeName}</td>
                        <td>${proj.projStartTime}</td>
                        <td>${proj.projEndTime}</td>
                        <td>
                                ${pdfn:transMultiply(fundMap[proj.projFlow].budgetAmount,10000)}
                        </td>
                        <td>
                                ${fundMap[proj.projFlow].budgetStatusName }
                                    <br/>[<a href="javascript:void(0);" onclick="showProcess('${fundMap[proj.projFlow].fundFlow}')">查看过程</a>]
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${empty fundMap[proj.projFlow].budgetStatusId or fundMap[proj.projFlow].budgetStatusId eq achStatusEnumFirstBack.id or fundMap[proj.projFlow].budgetStatusId eq achStatusEnumSecondBack.id or fundMap[proj.projFlow].budgetStatusId eq achStatusEnumApply.id}">
                                    <a href="javascript:void(0)" onclick="edit('${proj.projFlow}');">编辑&nbsp;|</a>
                                    <a href="javascript:void(0);" onclick="view('${proj.projFlow}');">查看</a>
                                </c:when>
                                <c:otherwise>
                                    <c:choose>
                                    <c:when test="${fundMap[proj.projFlow].budgetStatusId eq achStatusEnumFirstAudit.id}">
                                        <a href="javascript:void(0);" onclick="viewDetail('${proj.projFlow}');">查看</a>
                                    </c:when>
                                        <c:otherwise>
                                            <a href="javascript:void(0);" onclick="view('${proj.projFlow}');">查看</a>
                                        </c:otherwise>
                                    </c:choose>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>

            </table>
            <c:set var="pageView" value="${pdfn:getPageView(projList)}" scope="request"></c:set>
            <pd:pagination toPage="toPage"/>
        </form>

    </div>
</div>
</body>