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

        function printExamCard(userId){
            var url = "<s:url value='/res/tjExam/printCard'/>?userId=" + userId;
            jboxStartLoading();
            jboxOpen(url, "打印准考证", '20cm', 600);
            jboxEndLoading();
        }

        function shouye() {
            var url = "<s:url value='/res/tjExam/index'/>";
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
                        <nav>
                            <dl class="menu">
                                <dt class="menu_title">
                                    <i class="icon_menu menu_management"></i>结业考核管理
                                </dt>
                                <dd class="menu_item"><a href="#baseInfo">学员信息</a></dd>
                                <dd class="menu_item"><a href="#scoreInfo">成绩信息</a></dd>
                                <dd class="menu_item"><a href="#localInfo">考点信息</a></dd>
                                <dd class="menu_item"><a href="#cardInfo">证书信息</a></dd>
                            </dl>
                        </nav>
                    </div>
                    <div class="col_main" id="content">
                        <div class="main_hd">
                            <h2>住院医师结业考核信息&#12288;&#12288;
                                <input class="btn_green" type="button" id="addRecruitBtn"
                                       onclick="printExamCard('${docinfo.userId}');" value="打印准考证">
                            </h2>
                            <div class="doctorContent">
                                <div class="search_table" id="baseInfo">
                                    <h4>
                                        A学员信息
                                    </h4>
                                    <table table border="0" cellpadding="0" cellspacing="0" class="base_info">
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
                                            <td>${docinfo.userName}</td>
                                            <th>性别</th>
                                            <td>${docinfo.userSex}</td>
                                            <td colspan="2" rowspan="4">
                                                <div style="width: 35mm;height: 60mm">
                                                    <img style="margin-top: 10px;" src="${sysCfgMap['upload_base_url']}/${docinfo.phonePath}"
                                                         onerror="this.src='<s:url value="/jsp/tjresexam/moren.png"/>'" width="100%" height="90%"/>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>学员编号</th>
                                            <td>${docinfo.userId}</td>
                                            <th>准考证号</th>
                                            <td>${docinfo.ticketNum}</td>
                                        </tr>
                                        <tr>
                                            <th>培训基地</th>
                                            <td>${docinfo.orgName}</td>
                                            <th>专业基地</th>
                                            <td>${docinfo.speName}</td>
                                        </tr>
                                        <tr>
                                            <th>学位</th>
                                            <td>${docinfo.degree}</td>
                                            <th>执业医师资格证书号</th>
                                            <td>${docinfo.qualifiedNo}</td>
                                        </tr>
                                        <tr>
                                            <th>身份证号</th>
                                            <td>${docinfo.idNo}</td>
                                            <th>派送单位名称</th>
                                            <td>${docinfo.befOrgName}</td>
                                            <th>手机号码</th>
                                            <td>${docinfo.userPhone}</td>
                                        </tr>
                                        <tr>
                                            <th>备注</th>
                                            <td colspan="5">${docinfo.remark}</td>
                                        </tr>
                                    </table>
                                </div>
                                <div class="search_table" id="scoreInfo">
                                    <h4>
                                        B成绩信息
                                    </h4>
                                    <table border="0" cellpadding="0" cellspacing="0" class="base_info">
                                        <colgroup>
                                            <col width="20%"/>
                                            <col width="80%"/>
                                        </colgroup>
                                        <tr>
                                            <th>一站通过（Y/N）</th>
                                            <td>${docinfo.siteOneStateName}</td>
                                        </tr>
                                        <tr>
                                            <th>二站通过（Y/N）</th>
                                            <td>${docinfo.siteTwoStateName}</td>
                                        </tr>
                                        <tr>
                                            <th>三站通过（Y/N）</th>
                                            <td>${docinfo.siteThreeStateName}</td>
                                        </tr>
                                        <tr>
                                            <th>总成绩通过（Y/N）</th>
                                            <td>${docinfo.totalScoreStateName}</td>
                                        </tr>
                                    </table>
                                </div>
                                <div class="search_table" id="localInfo">
                                    <h4>
                                        C考点信息
                                    </h4>
                                    <table table border="0" cellpadding="0" cellspacing="0" class="base_info">
                                        <colgroup>
                                            <col width="20%"/>
                                            <col width="30%"/>
                                            <col width="20%"/>
                                            <col width="30%"/>
                                        </colgroup>
                                        <tr>
                                            <th>一站考点</th>
                                            <td>${docinfo.siteOneName}</td>
                                            <th>一站时间（年月日时）</th>
                                            <td>${docinfo.siteOneTime}</td>
                                        </tr>
                                        <tr>
                                            <th>二站考点</th>
                                            <td>${docinfo.siteTwoName}</td>
                                            <th>二站时间（年月日时）</th>
                                            <td>${docinfo.siteTwoTime}</td>
                                        </tr>
                                        <tr>
                                            <th>三站考点</th>
                                            <td>${docinfo.siteThreeName}</td>
                                            <th>三站时间（年月日时）</th>
                                            <td>${docinfo.siteThreeTime}</td>
                                        </tr>
                                    </table>
                                </div>
                                <div class="search_table" id="cardInfo">
                                    <h4>
                                        D结业证书
                                    </h4>
                                    <table border="0" cellpadding="0" cellspacing="0" class="base_info">
                                        <colgroup>
                                            <col width="20%"/>
                                            <col width="80%"/>
                                        </colgroup>
                                        <tr>
                                            <th>学员ID</th>
                                            <td>${docinfo.userId}</td>
                                        </tr>
                                        <tr>
                                            <th>证书编号</th>
                                            <td>${docinfo.completeNo}</td>
                                        </tr>
                                    </table>
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

</div>

</body>
</html>
