<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="login" value="true"/>
        <jsp:param name="jquery_form" value="false"/>
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
    <script>
        function editExamCard(userId){
            var url = "<s:url value='/res/njExam/editCard'/>?userId=" + userId;
            jboxOpen(url, "编辑准考证", '20cm', 500);
        }
        function toPage(page) {
            $("#currentPage").val(page);
            jboxStartLoading();
            $("#paramForm").submit();
        }
        function importDocInfo() {
            jboxOpen("<s:url value='/res/njExam/examCardImport'/>","导入",600,180);
        }
        function shouye() {
            var url = "<s:url value='/res/njExam/index'/>";
            window.location.href = url;
        }
        $(function () {
            $("#indexBody").css("height", window.innerHeight + "px");
        });
    </script>
    <style>
        body {
            overflow: hidden;
        }
    </style>
</head>

<body>
<div style="overflow:auto;" id="indexBody">
    <div class="bd_bg">
        <div class="yw">
            <div class="head">
                <div class="head_inner">
                    <h1 class="logo">
                        <a onclick="shouye();">江苏省${pdfn:getCurrYear()}年结业技能考试准考证打印平台</a>
                    </h1>
                    <div class="account">
                        <h2 style="text-align: right;">您好，<span
                                id="topUserName">${sessionScope.currUser.userName }</span></h2>
                        <div class="head_right">
                            <a onclick="shouye();">首页</a>&#12288;
                            <a href="<s:url value='/inx/njresexam/logout'/>">退出</a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="body">
                <div class="container">
                    <div class="content_side">
                        <input type="hidden" id="subMenuId" value=""/>
                        <dl class="menu">
                            <dt class="menu_title">
                                <i class="icon_menu menu_management"></i>结业考核管理
                            </dt>
                            <dd class="menu_item"><a>学员信息</a></dd>
                        </dl>
                    </div>
                    <div class="col_main" id="content">
                        <div class="main_hd">
                            <form id="paramForm" action="<s:url value="/res/njExam/index"/>" method="post">
                                <h2>姓名<input class="input" type="text" name="userName" value="${param.userName}">&#12288;
                                    证件号<input class="input" type="text" name="idNo" value="${param.idNo}">&#12288;
                                    <input type="hidden" id="currentPage" name="currentPage"/>
                                    <input class="btn_green" type="button"
                                           onclick="toPage(1);" value="查询">
                                    <input class="btn_green" type="button"
                                           onclick="importDocInfo();" value="导入">
                                </h2>
                            </form>
                            <div class="doctorContent">
                                <div class="search_table" id="baseInfo">
                                    <table border="0" cellpadding="0" cellspacing="0" class="base_info">
                                        <colgroup>
                                            <col width="10%"/>
                                            <col width="10%"/>
                                            <col width="10%"/>
                                            <col width="10%"/>
                                            <col width="10%"/>
                                            <col width="10%"/>
                                            <col width="10%"/>
                                            <col width="10%"/>
                                            <col width="10%"/>
                                        </colgroup>
                                        <tr>
                                            <th style="text-align:center;">姓名</th>
                                            <th style="text-align:center;">证件号码</th>
                                            <th style="text-align:center;">培训专业</th>
                                            <th style="text-align:center;">准考证号</th>
                                            <th style="text-align:center;">考试地点</th>
                                            <th style="text-align:center;">考试时间</th>
                                            <th style="text-align:center;">考生联系电话</th>
                                            <th style="text-align:center;">考点联系电话</th>
                                            <th style="text-align:center;">准考证标题</th>
                                            <th style="text-align:center;">编辑信息</th>
                                        </tr>
                                        <c:forEach items="${extList}" var="ext" varStatus="extStatus">
                                            <tr>
                                                <td>${ext.userName}</td>
                                                <td>${ext.idNo}</td>
                                                <td>${ext.speName}</td>
                                                <td>${ext.ticketNum}</td>
                                                <td>${ext.address}</td>
                                                <td>${ext.examtime}</td>
                                                <td>${ext.userPhone}</td>
                                                <td>${ext.sitephone}</td>
                                                <td>${ext.title}</td>
                                                <td>  <a onclick="editExamCard('${ext.userId}')">编辑信息</a></td>
                                            </tr>
                                        </c:forEach>
                                        <c:if test="${empty extList}">
                                            <tr>
                                                <td colspan="9" >无记录！</td>
                                            </tr>
                                        </c:if>
                                    </table>
                                </div>
                                <div class="page" style="padding-right: 40px;">
                                    <c:set var="pageView" value="${pdfn:getPageView(extList)}" scope="request"></c:set>
                                    <pd:pagination-jszy toPage="toPage"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="foot">
    <div class="foot_inner">
        主管单位：江苏省卫生健康委员会科教处 | 技术支持：南京品德网络信息技术有限公司
    </div>
</div>

</body>
</html>
