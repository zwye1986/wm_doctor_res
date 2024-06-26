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

        function showDocInfo(userId) {
            if (userId) {
                jboxOpenContent($("#showInfo_" + userId).html(), "学员详细信息", 800, 600);
            }
        }

        function shouye() {
            var url = "<s:url value='/res/tjExam/index'/>";
            window.location.href = url;
        }

        function searchDocInfo() {
            jboxStartLoading();
            $("#paramForm").submit();
        }
        function printExamCard(userId) {
            var url = "<s:url value='/res/tjExam/printCard'/>?userId=" + userId;
            jboxStartLoading();
            jboxOpen(url, "打印准考证", '20cm', 600);
            jboxEndLoading();
        }
        function printCertificate(userId) {
            var url = "<s:url value='/res/tjExam/printCertificate'/>?userId=" + userId;
            jboxStartLoading();
            jboxOpen(url, "合格证书预览", 580,600);
            jboxEndLoading();
        }
        $(function () {
            $("#indexBody").css("height", window.innerHeight + "px");
        });
    </script>
    <style>
        body {
            overflow: hidden;
        }

        .inputText {
            border: 1px solid;
            font: 13px 'Microsoft Yahei', sans-serif;
            vertical-align: middle;
            padding: 0;
            margin: 0;
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
                        <a onclick="shouye();">住院医师规范化培训结业考核管理平台</a>
                    </h1>
                    <div class="account">
                        <h2 style="text-align: right;">您好，<span
                                id="topUserName">${sessionScope.currUser.userName }</span></h2>
                        <div class="head_right">
                            <a onclick="shouye();">首页</a>&#12288;
                            <a href="<s:url value='/inx/tjResExam/logout'/>">退出</a>
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
                            <form id="paramForm" action="<s:url value="/res/tjExam/index"/>" method="post">
                                <h2>姓名<input class="inputText" type="text" name="userName" value="${param.userName}">&#12288;
                                    学号<input class="inputText" type="text" name="userId" value="${param.userId}">&#12288;
                                    <c:if test="${sessionScope.currUser.docrole eq '3'}">
                                        基地名称<input class="inputText " type="text" name="orgName" value="${param.orgName}">&#12288;
                                    </c:if>
                                    <input class="btn_green" type="button" id=""
                                           onclick="searchDocInfo();" value="查询">
                                </h2>
                                <h3 style=""><label style="font-size: 12px;float: right">
                                    一站通过<input type="checkbox" name="siteOneState" value="Y"
                                               <c:if test="${param.siteOneState eq 'Y'}">checked</c:if>
                                               onclick="searchDocInfo();">&#12288;
                                    二站通过<input type="checkbox" name="siteTwoState" value="Y"
                                               <c:if test="${param.siteTwoState eq 'Y'}">checked</c:if>
                                               onclick="searchDocInfo();">&#12288;
                                    三站通过<input type="checkbox" name="siteThreeState" value="Y"
                                               <c:if test="${param.siteThreeState eq 'Y'}">checked</c:if>
                                               onclick="searchDocInfo();">&#12288;
                                    总成绩通过<input type="checkbox" name="totalScoreState" value="Y"
                                                <c:if test="${param.totalScoreState eq 'Y'}">checked</c:if>
                                                onclick="searchDocInfo();">&#12288;&#12288;&#12288;</label></h3>
                            </form>
                            <div class="doctorContent">
                                <div class="search_table" id="baseInfo">
                                    <table border="0" cellpadding="0" cellspacing="0" class="base_info">
                                        <colgroup>
                                            <col width="15%"/>
                                            <col width="10%"/>
                                            <col width="10%"/>
                                            <col width="24%"/>
                                            <col width="26%"/>
                                        </colgroup>
                                        <tr>
                                            <th style="text-align:center;">学员编号</th>
                                            <th style="text-align:center;">姓名</th>
                                            <th style="text-align:center;">性别</th>
                                            <th style="text-align:center;">派送单位名称</th>
                                            <th style="text-align:center;">操作</th>
                                        </tr>
                                        <c:forEach items="${extList}" var="ext" varStatus="extStatus">
                                            <tr>
                                                <td>${ext.userId}</td>
                                                <td>${ext.userName}</td>
                                                <td>${ext.userSex}</td>
                                                <td>${ext.befOrgName}</td>
                                                <td>
                                                    <a onclick="showDocInfo('${ext.userId}')">查看信息</a>&#12288;|&#12288;
                                                    <a onclick="printExamCard('${ext.userId}')">打印准考证</a>
                                                    <c:if test="${ext.totalScoreState eq 'Y'}">
                                                    &#12288;|&#12288;<a onclick="printCertificate('${ext.userId}')">打印合格证</a>
                                                    </c:if>
                                                </td>
                                                <td style="display: none">
                                                    <div id="showInfo_${ext.userId}">
                                                        <div style="height:600px;overflow: auto">
                                                            <div class="search_table" id="baseInfo">
                                                                <h4>
                                                                    A学员信息
                                                                </h4>
                                                                <table table border="0" cellpadding="0" cellspacing="0"
                                                                       class="base_info">
                                                                    <colgroup>
                                                                        <col width="15%"/>
                                                                        <col width="20%"/>
                                                                        <col width="15%"/>
                                                                        <col width="20%"/>
                                                                        <col width="15%"/>
                                                                        <col width="15%"/>
                                                                    </colgroup>
                                                                    <tr>
                                                                        <th>姓名</th>
                                                                        <td>${ext.userName}</td>
                                                                        <th>性别</th>
                                                                        <td>${ext.userSex}</td>
                                                                        <td colspan="2" rowspan="4">
                                                                            <div style="width: 35mm;height: 60mm">
                                                                                <img style="margin-top: 10px;" src="${sysCfgMap['upload_base_url']}/${ext.phonePath}"
                                                                                     onerror="this.src='<s:url value="/jsp/tjresexam/moren.png"/>'"   width="100%" height="90%"/>
                                                                            </div>
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th>学员编号</th>
                                                                        <td>${ext.userId}</td>
                                                                        <th>准考证号</th>
                                                                        <td>${ext.ticketNum}</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th>培训基地</th>
                                                                        <td>${ext.orgName}</td>
                                                                        <th>专业基地</th>
                                                                        <td>${ext.speName}</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th>学位</th>
                                                                        <td>${ext.degree}</td>
                                                                        <th>执业医师资格证书号</th>
                                                                        <td>${ext.qualifiedNo}</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th>身份证号</th>
                                                                        <td>${ext.idNo}</td>
                                                                        <th>派送单位名称</th>
                                                                        <td>${ext.befOrgName}</td>
                                                                        <th>手机号码</th>
                                                                        <td>${ext.userPhone}</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th>备注</th>
                                                                        <td colspan="5">${ext.remark}</td>
                                                                    </tr>
                                                                </table>
                                                            </div>
                                                            <div class="search_table" id="scoreInfo">
                                                                <h4>
                                                                    B成绩信息
                                                                </h4>
                                                                <table border="0" cellpadding="0" cellspacing="0"
                                                                       class="base_info">
                                                                    <colgroup>
                                                                        <col width="20%"/>
                                                                        <col width="30%"/>
                                                                        <col width="20%"/>
                                                                        <col width="30%"/>
                                                                    </colgroup>
                                                                    <tr>
                                                                        <th>一站通过（Y/N）</th>
                                                                        <td <c:if test="${not (sessionScope.currUser.docrole eq '3')}">colspan="3" </c:if>>${ext.siteOneStateName}</td>
                                                                        <c:if test="${sessionScope.currUser.docrole eq '3'}">
                                                                            <th>一站成绩</th>
                                                                            <td>${ext.siteOneScore}</td>
                                                                        </c:if>
                                                                    </tr>
                                                                    <tr>
                                                                        <th>二站通过（Y/N）</th>
                                                                        <td <c:if test="${not (sessionScope.currUser.docrole eq '3')}">colspan="3" </c:if>>${ext.siteTwoStateName}</td>
                                                                        <c:if test="${sessionScope.currUser.docrole eq '3'}">
                                                                            <th>二站成绩</th>
                                                                            <td>${ext.siteTwoScore}</td>
                                                                        </c:if>
                                                                    </tr>
                                                                    <tr>
                                                                        <th>三站通过（Y/N）</th>
                                                                        <td <c:if test="${not (sessionScope.currUser.docrole eq '3')}">colspan="3" </c:if>>${ext.siteThreeStateName}</td>
                                                                        <c:if test="${sessionScope.currUser.docrole eq '3'}">
                                                                            <th>三站成绩</th>
                                                                            <td>${ext.siteThreeScore}</td>
                                                                        </c:if>
                                                                    </tr>
                                                                    <tr>
                                                                        <th>总成绩通过（Y/N）</th>
                                                                        <td colspan="3">${ext.totalScoreStateName}</td>
                                                                    </tr>
                                                                </table>
                                                            </div>
                                                            <div class="search_table" id="localInfo">
                                                                <h4>
                                                                    C考点信息
                                                                </h4>
                                                                <table table border="0" cellpadding="0" cellspacing="0"
                                                                       class="base_info">
                                                                    <colgroup>
                                                                        <col width="20%"/>
                                                                        <col width="30%"/>
                                                                        <col width="20%"/>
                                                                        <col width="30%"/>
                                                                    </colgroup>
                                                                    <tr>
                                                                        <th>一站考点</th>
                                                                        <td>${ext.siteOneName}</td>
                                                                        <th>一站时间（年月日时）</th>
                                                                        <td>${ext.siteOneTime}</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th>二站考点</th>
                                                                        <td>${ext.siteTwoName}</td>
                                                                        <th>二站时间（年月日时）</th>
                                                                        <td>${ext.siteTwoTime}</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th>三站考点</th>
                                                                        <td>${ext.siteThreeName}</td>
                                                                        <th>三站时间（年月日时）</th>
                                                                        <td>${ext.siteThreeTime}</td>
                                                                    </tr>
                                                                </table>
                                                            </div>
                                                            <div class="search_table" id="cardInfo">
                                                                <h4>
                                                                    D结业证书
                                                                </h4>
                                                                <table border="0" cellpadding="0" cellspacing="0"
                                                                       class="base_info">
                                                                    <colgroup>
                                                                        <col width="20%"/>
                                                                        <col width="80%"/>
                                                                    </colgroup>
                                                                    <tr>
                                                                        <th>学员ID</th>
                                                                        <td>${ext.userId}</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th>证书编号</th>
                                                                        <td>${ext.completeNo}</td>
                                                                    </tr>
                                                                </table>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </table>
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
        主管单位：天津市卫生和计划生育委员会 | 技术支持：南京品德网络信息技术有限公司
    </div>
</div>

</body>
</html>
