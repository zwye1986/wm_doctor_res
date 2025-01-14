<%@ page import="com.pinde.sci.util.JsresUtil" %>
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
    <script type="text/javascript" src="<s:url value='/js/DatePicker/WdatePicker.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script>

        $(document).ready(function(){
            if ("${GlobalConstant.FLAG_Y}"=="${pageFlag}") {
                $("#userCenter").addClass("select");
                setIndexHeight();
                userCenter();
            }
            if(!getCookie('first')){
                $(function () {
                    isVerify = '${sessionScope.currUser.isVerify}';
                    if(isVerify != 'Y'){
                        jboxConfirm("认证手机号码，享受更便捷的登录体验，快去认证吧" , function(){
                            accounts();
                        });
                    }
                });
                setCookie('first',1,1)
            }
        });

        //设置cookie
		function setCookie(cname, cvalue, exdays) {
			var d = new Date();
			d.setTime(d.getTime() + (exdays*60*60*1000));
			var expires = "expires="+d.toUTCString();
			document.cookie = cname + "=" + cvalue + ";" + expires+";path=/";
		}
		//获取cookie
		function getCookie(cname) {
			var name = cname + "=";
			var ca = document.cookie.split(';');
			for(var i=0; i<ca.length; i++) {
				var c = ca[i];
				while (c.charAt(0)==' ') c = c.substring(1);
				if (c.indexOf(name) != -1) return c.substring(name.length, c.length);
			}
			return "";
		}

        //清除cookie
        function delCookie(name) {
            setCookie(name, "", -1);
        }

        function planUserMain() {
            var url = "<s:url value='/jsres/phyAss/planUserMsgMain'/>";
            jboxLoad("content", url, false);
        }

        function grpjcx(){
            var url = "<s:url value='/res/evaTeacherResult/main'/>";
            jboxLoad("content", url, false);
        }

        function patientEvaluate() {
            var url = "<s:url value='/res/evaDoctorResult/patientEvaluate'/>?roleFlag=teacher";
            jboxLoad("content", url, false);
        }
        function EvaStudent() {
            jboxLoad("content", "<s:url value='/res/teacherEvaDoctor/teacherEvaluation'/>?recTypeId=TeacherDoctorAssess", true);
        }
        function accounts() {
            jboxLoad("content", "<s:url value='/jsres/manage/accounts'/>", true);
        }
        function userCenter() {
            jboxLoad("content", "<s:url value='/jsres/manage/userCenter'/>", true);
        }

        function reportDept() {
            jboxLoad("content", "<s:url value='/jsres/report/toDept'/>", true);
        }

        function reportDoc() {
            jboxLoad("content", "<s:url value='/jsres/report/toDoc'/>", true);
        }
        function toPage(page){
            if (page == null || page == undefined || page == '' || isNaN(page)) {
                page = 1;
            }
            shouye(page);
        }
        function shouye(page) {
            if (page == null || page == undefined || page == '' || isNaN(page)) {
                page = 1;
            }
            var url = "<s:url value='/jsres/teacher/index'/>?currentPage=" + page;
            window.location.href = url;
        }
        function logout() {
            delCookie('first')
            var url ="<s:url value='/inx/jsres/logout'/>"
            window.location.href = url;
        }

        function recruitAuditSearch(form) {
            var url = "<s:url value='/jsres/teacher/recruitAuditSearch'/>";
            jboxStartLoading();
            jboxPost(url, form, function (resp) {
                $("#content").html(resp);
                jboxEndLoading();
            }, null, false);
        }
        function attendanceSearch(form) {
            var url = "<s:url value='/jsres/teacher/attendanceSearch/teacher'/>";
            jboxStartLoading();
            jboxPost(url, form, function (resp) {
                $("#content").html(resp);
                jboxEndLoading();
            }, null, false);
        }
        function vettedAuditSearch(form) {
            var url = "<s:url value='/jsres/teacher/vettedAuditSearch'/>";
            jboxStartLoading();
            jboxPost(url, form, function (resp) {
                $("#content").html(resp);
                jboxEndLoading();
            }, null, false);
        }
        // 请假审核
        function leaveSearch(form) {
            var url = "<s:url value='/jsres/attendanceNew/leaveVerifyList/teacher'/>";
            jboxStartLoading();
            jboxPost(url, form, function (resp) {
                $("#content").html(resp);
                jboxEndLoading();
            }, null, false);
        }
        function stuEvaluation(form) {
            var url = "<s:url value='/jsres/teacher/stuEvaluation'/>";
            jboxStartLoading();
            jboxPost(url, form, function (resp) {
                $("#content").html(resp);
                jboxEndLoading();
            }, null, false);
        }
        window.onresize = function () {
            setIndexHeight();
        };

        $(function () {
            $(".menu_item a").click(function () {
                $(".select").removeClass("select");
                $(this).addClass("select");
            });
            setIndexHeight();
        });

        function setIndexHeight() {
            $("#indexBody").css("height", document.documentElement.clientHeight);
        }
        function selectMenu(menuId) {
            $("#" + menuId).find("a").click();
        }
        function activityQuery(){
            var roleFlag="teach";
            jboxLoad("content","<s:url value='/jsres/activityQuery/main'/>?roleFlag="+roleFlag,true);
        }
        function lectures(data){
            var url = "<s:url value='/jsres/lecture/doctorLectureView'/>?roleId=teacher";
            jboxStartLoading();
            jboxPost(url,data,function(resp){
                $("#content").html(resp);
                jboxEndLoading();
            },null,false);
        }
        function lecturesAppointment(data) {
            var url = "<s:url value='/res/manager/doctorLectureView'/>?roleId=teacher&tabType=new&isNew=Y";
            jboxStartLoading();
            jboxPost(url, data, function (resp) {
                $("#content").html(resp);
                jboxEndLoading();
            }, null, false);
        }
        //查看考勤信息
        function attendanceTab(form) {
            var url = "<s:url value='/jsp/jsres/attendance/teacher/main.jsp'/>";
            jboxLoad("content", url, false);
        }
        function lecturesEva(data) {
            var url = "<s:url value='/res/manager/doctorLectureView'/>?roleId=teacher&tabType=history&isEval=Y";
            jboxStartLoading();
            jboxPost(url, data, function (resp) {
                $("#content").html(resp);
                jboxEndLoading();
            }, null, false);
        }

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
        <div class="yw" style="height: 90%;">
            <div class="head">
                <div class="head_inner">
                    <h1 class="logo">
                        <a onclick="shouye();"><%=JsresUtil.getTitle(request, response, application)%>
                        </a>
                    </h1>
                    <div class="account">
                        <h2 class="head_right">${sessionScope.currUser.orgName }-${sessionScope.currUser.userName }老师</h2>
                        <div class="head_right">
                            <!--        引入切换角色功能 -->
                            <jsp:include page="/jsp/jsres/changeRole.jsp" flush="true"></jsp:include>
                            <a onclick="shouye();">首页</a>&#12288;
                            <a onclick="logout()">退出</a>
                        </div>
                    </div>
                </div>
            </div>


            <div class="body" style="height: 90%;">
                <div class="container">
                    <div class="content_side">
                        <c:set value="jsres_${sessionScope.currUser.orgFlow }_guocheng" var="key"/>
                        <c:if test="${pdfn:jsresPowerCfgMap(key) eq GlobalConstant.RECORD_STATUS_Y}">
                            <dl class="menu">
                                <dt class="menu_title">
                                    <i class="icon_menu menu_statistics"></i>教学管理
                                </dt>
                                <c:set value="jsres_hospital_activity_${sessionScope.currUser.orgFlow }" var="activityKey"/>
                                <c:if test="${pdfn:jsresPowerCfgMap(activityKey) eq GlobalConstant.RECORD_STATUS_Y}">
                                    <dd class="menu_item"><a href="javascript:activityQuery();">教学活动维护</a></dd>
                                </c:if>
<%--                                <dd class="menu_item"><a onclick="lectures(null)">讲座信息查询</a></dd>--%>
                                <c:set value="jsres_hospital_jzxxgl_${sessionScope.currUser.orgFlow }" var="jzxxglKey"/>
                                <c:if test="${pdfn:jsresPowerCfgMap(jzxxglKey) eq GlobalConstant.RECORD_STATUS_Y}">
                                    <dd class="menu_item"><a onclick="lecturesAppointment(null)">讲座活动预约</a></dd>
                                    <dd class="menu_item"><a onclick="lecturesEva(null)">讲座活动评价</a></dd>
                                </c:if>
                            </dl>
                        </c:if>
                        <dl class="menu">
                            <dt class="menu_title">
                                <i class="icon_menu menu_management"></i>轮转管理
                            </dt>
                            <c:set value="jsres_hospital_xykqcx_${sessionScope.currUser.orgFlow }" var="xykqcxKey"/>
                            <c:if test="${pdfn:jsresPowerCfgMap(xykqcxKey) eq GlobalConstant.RECORD_STATUS_Y}">
                                <dd class="menu_item" id="attendanceSearch"><a onclick="attendanceSearch();">学员考勤审核</a></dd>
                                <dd class="menu_item" id="attendanceSearch"><a onclick="leaveSearch();">学员请假管理</a></dd>
                            </c:if>
                            <c:set value="jsres_${sessionScope.currUser.orgFlow }_export_attendance_teacher" var="kqdcKey"/>
                            <c:if test="${pdfn:jsresPowerCfgMap(kqdcKey) eq GlobalConstant.RECORD_STATUS_Y}">
                                <dd class="menu_item"><a onclick="attendanceTab()">学员考勤查询</a></dd>
                            </c:if>

                            <c:set value="jsres_hospital_pxsjsh_${sessionScope.currUser.orgFlow }" var="pxsjshKey"/>
                            <c:if test="${pdfn:jsresPowerCfgMap(pxsjshKey) eq GlobalConstant.RECORD_STATUS_Y}">
                                <dd class="menu_item" id="recruitAuditSearch"><a onclick="recruitAuditSearch();">培训数据审核</a></dd>
                            </c:if>
                            <c:if test="${pdfn:jsresPowerCfgMap(key) eq GlobalConstant.RECORD_STATUS_Y}">
                                <dd class="menu_item"><a onclick="stuEvaluation()">学员考评审核</a></dd>
                            </c:if>
                            <dd class="menu_item" id="vettedAuditSearch"><a onclick="vettedAuditSearch();">临床技能审核</a></dd>
                        </dl>
                        <c:if test="${cfgValue eq 'Y'}">
                            <dl class="menu">
                                <dt class="menu_title">
                                    <i class="icon_menu menu_setup"></i>考核管理
                                </dt>
                                <dd class="menu_item"><a href="javascript:EvaStudent();">评价学员</a></dd>
                                <dd class="menu_item"><a href="javascript:grpjcx();">个人评价查询</a></dd>
<%--                                <dd class="menu_item"><a href="javascript:patientEvaluate();">病人评价查询</a></dd>--%>
                            </dl>
                        </c:if>

                        <dl class="menu">
                            <dt class="menu_title">
                                <i class="icon_menu menu_setup"></i>师资管理
                            </dt>
                            <dd class="menu_item"><a href="javascript:planUserMain();">师资证书查询</a></dd>
                        </dl>

                        <dl class="menu">
                            <dt class="menu_title">
                                <i class="icon_menu menu_setup"></i>个人中心
                            </dt>
                            <dd class="menu_item"><a href="javascript:accounts();">安全中心</a></dd>
                            <dd class="menu_item"><a id="userCenter" href="javascript:userCenter();">个人信息</a></dd>
                        </dl>
                    </div>
                    <div class="col_main" id="content" style="height: 50%;">
                        <div class="content_main">
                            <div class="index_show">
                                <div class="index_tap top_left">
                                    <ul class="inner">
                                        <li class="index_item index_blue">
                                            <a href="javascript:selectMenu('recruitAuditSearch');">
                                              <span class="tap_inner">
                                                <i class="message"></i>
                                                <em class="number">${noAuditNumber}</em>
                                                <strong class="title">培训数据待审核</strong>
                                              </span>
                                            </a>
                                        </li>
                                        <li class="index_item index_blue">
                                            <a href="#">
                                              <span class="tap_inner tab_second">
                                                <i class="people"></i>
                                                <em class="number">${dShenHe}</em>
                                                <strong class="title">当前轮转学员数</strong>
                                              </span>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                                <div class="index_tap top_right">
                                    <ul class="inner">
                                        <li class="index_item index_green">
                                            <a href="#">
                                              <span class="tap_inner">
                                                <i class="crowd"></i>
                                                <em class="number">${studentNum}</em>
                                                <strong class="title">总学员数</strong>
                                              </span>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="index_form" style="margin-bottom: 10px;">
                                <h3>通知公告</h3>
                                <ul class="form_main">
                                    <c:forEach items="${infos}" var="msg">
                                        <li>
                                            <strong><a
                                                    href="<s:url value='/inx/jsres/noticeView'/>?infoFlow=${msg.infoFlow}"
                                                    target="_blank">${msg.infoTitle}</a>
                                                <c:if test="${pdfn:signDaysBetweenTowDate(pdfn:getCurrDate(),pdfn:transDate(msg.createTime))<=7}">
                                                    <i class="new1"></i>
                                                </c:if>
                                            </strong>
                                            <span>${pdfn:transDate(msg.createTime)}</span>
                                        </li>
                                    </c:forEach>
                                    <c:if test="${empty infos}">
                                        <li>
                                            <strong>无记录!</strong>
                                        </li>
                                    </c:if>
                                </ul>
                            </div>
                            <div class="page">
                               <span>
                                 <input id="currentPage" type="hidden" name="currentPage" value="${param.currentPage}"/>
                                 <c:set var="pageView" value="${pdfn:getPageView(infos)}" scope="request"></c:set>
                                 <pd:pagination-jsres toPage="toPage"/>
                               </span>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
    <div class="foot">
        <div class="foot_inner">
            主管单位：${sysCfgMap['the_competent_unit']} | 技术支持：南京品德网络信息技术有限公司
        </div>
    </div>
</div>
<c:if test="${applicationScope.sysCfgMap['online_service']=='Y'}">
    <jsp:include page="/jsp/service.jsp" flush="true"></jsp:include>
</c:if>


</body>
</html>
 