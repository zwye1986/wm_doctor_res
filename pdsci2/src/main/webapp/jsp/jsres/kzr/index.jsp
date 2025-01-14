<%@ page import="com.pinde.sci.util.JsresUtil" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
    </jsp:include>
    <script type="text/javascript" src="<s:url value='/js/DatePicker/WdatePicker.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script>

        $(document).ready(function(){
            $.ajax({
                url: "<s:url value='/jsres/kzr/indexInfo'/>",
                type: "get",
                data: {},
                dataType: "json",
                success: function (res) {
                    $("#currStudentHe").text(res["currStudentHe"]);
                    $("#studentNum").text(res["studentNum"]);
                    $("#ComingStudentNum").text(res["ComingStudentNum"]);
                }
            });
        });

        function EvaStudent() {
            jboxLoad("content", "<s:url value='/res/teacherEvaDoctor/manageEvaluation'/>?recTypeId=ManageDoctorAssess360&roleFlag=head&sessionNumber=${pdfn:getCurrYear()}", true);
        }

        function pjjgcx(role){
            var url = "<s:url value='/res/evaluateHospitalResult/base?gradeRole=doctor&role='/>"+role;
            jboxLoad("content", url, true);
            jboxEndLoading();
        }

        function patientEvaluate() {
            var url = "<s:url value='/res/evaDoctorResult/patientEvaluate'/>?roleFlag=kzr";
            jboxLoad("content", url, false);
        }

        function accounts() {
            jboxLoad("content", "<s:url value='/jsres/manage/accounts'/>", true);
        }
        function userCenter() {
            jboxLoad("content", "<s:url value='/jsres/manage/userCenter'/>", true);
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
            var url = "<s:url value='/jsres/kzr/index'/>?currentPage=" + page;
            window.location.href = url;
        }
        function recruitAuditSearch(form) {
            var url = "<s:url value='/jsres/kzr/recruitAuditSearch'/>";
            jboxStartLoading();
            jboxPost(url, form, function (resp) {
                $("#content").html(resp);
                jboxEndLoading();
            }, null, false);
        }
        function temporaryOutAuditSearch(form) {
            var url = "<s:url value='/jsres/kzr/temporaryOutAuditSearch'/>?roleId=head&biaoJi=Y";
            jboxStartLoading();
            jboxPost(url, form, function (resp) {
                $("#content").html(resp);
                jboxEndLoading();
            }, null, false);
        }

        function temporaryOutSearch(form) {
            var url = "<s:url value='/jsres/kzr/temporaryOutSearch'/>?roleId=head&biaoJi=Y";
            jboxStartLoading();
            jboxPost(url, form, function (resp) {
                $("#content").html(resp);
                jboxEndLoading();
            }, null, false);
        }
        function afterAuditSearch(form) {
            var url = "<s:url value='/jsres/kzr/afterAuditSearch'/>?roleId=head&biaoJi=Y";
            jboxStartLoading();
            jboxPost(url, form, function (resp) {
                $("#content").html(resp);
                jboxEndLoading();
            }, null, false);
        }
        function docProcessEval(form) {
            var url = "<s:url value='/jsres/kzr/docProcessEvalMain'/>";
            jboxLoad("content", url, true);
        }
        function evaluateSearch(form) {
            var url = "<s:url value='/jsres/kzr/evaluateSearch'/>";
            jboxStartLoading();
            jboxPost(url, form, function (resp) {
                jboxEndLoading();
                $("#content").html(resp);
            }, null, false);
        }
        //入科教育管理
        function admissionEducationManage() {
            var url = "<s:url value='/jsres/kzr/admissionEducationMain'/>";
            jboxLoad("content", url, false);
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
        function activityQuery(){
            var roleFlag="head";
            jboxLoad("content","<s:url value='/jsres/activityQuery/main'/>?roleFlag="+roleFlag,true);
        }
        function teacherActivityStatistics(){
            var roleFlag="head";
            jboxLoad("content","<s:url value='/jsres/teacherActivityStatistics/main'/>?roleFlag="+roleFlag,true);
        }
        function doctorActivityStatistics(){
            var roleFlag="head";
            jboxLoad("content","<s:url value='/jsres/doctorActivityStatistics/main'/>?roleFlag="+roleFlag,true);
        }

        //查看考勤信息
        function attendanceTab(form) {
            var url = "<s:url value='/jsp/jsres/attendance/kzr/main.jsp'/>";
            jboxLoad("content", url, false);
        }

        function auditTeacherApplicationMain(deptFlow) {
            var url = "<s:url value='/jsres/statistic/auditTeacherApplicationMain'/>?roleFlag=head&deptFlow=" + deptFlow;
            jboxLoad("content", url, true);
        }

        function cycleResults(){
            var url = "<s:url value='/jsres/doctorRecruit/doctorRecruitResult?roleId=kzr'/>";
            jboxLoad("content",url,true);
        }
        // 请假审核
        function leaveSearch(form) {
            var url = "<s:url value='/jsres/attendanceNew/leaveVerifyList/head'/>";
            jboxStartLoading();
            jboxPost(url, form, function (resp) {
                $("#content").html(resp);
                jboxEndLoading();
            }, null, false);
        }
        function lectures(data){
            var url = "<s:url value='/jsres/lecture/doctorLectureView'/>?roleId=head";
            jboxStartLoading();
            jboxPost(url,data,function(resp){
                $("#content").html(resp);
                jboxEndLoading();
            },null,false);
        }
        $(function () {
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

        function logout() {
            delCookie('first')
            var url ="<s:url value='/inx/jsres/logout'/>"
            window.location.href = url;
        }

        function lecturesAppointment(data) {
            var url = "<s:url value='/res/manager/doctorLectureView'/>?roleId=head&tabType=new&isNew=Y";
            jboxStartLoading();
            jboxPost(url, data, function (resp) {
                $("#content").html(resp);
                jboxEndLoading();
            }, null, false);
        }
        function lecturesEva(data) {
            var url = "<s:url value='/res/manager/doctorLectureView'/>?roleId=head&tabType=history&isEval=Y";
            jboxStartLoading();
            jboxPost(url, data, function (resp) {
                $("#content").html(resp);
                jboxEndLoading();
            }, null, false);
        }

        function hosMain(deptFlow) {
            var url = "<s:url value='/jsres/kzr/main'/>?deptFlow=" + deptFlow;
            currentJboxLoad("content", url, null, true);
        }

        function currentJboxLoad(id, geturl, data, showLoading) {
            if (showLoading) {
                jboxStartLoading();
            }
            jboxPost(geturl, data, function (resp) {
                if (showLoading) {
                    jboxEndLoading();
                }
                $('#' + id).html(resp);
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
                        <h2 class="head_right">${sessionScope.currUser.orgName}-${sessionScope.currUser.userName}</h2>
                        <div class="head_right">
                            <!--        引入切换角色功能 -->
                            <jsp:include page="/jsp/jsres/changeRole.jsp" flush="true"></jsp:include>
                            <a onclick="shouye();">首页</a>&#12288;
                            <a onclick="logout()">退出</a>
                        </div>
                    </div>
                </div>
            </div>


            <div class="body">
                <div class="container">
                    <div class="content_side">
                        <c:set value="jsres_${sessionScope.currUser.orgFlow }_guocheng" var="key"/>
                        <c:if test="${pdfn:jsresPowerCfgMap(key) eq GlobalConstant.RECORD_STATUS_Y}">
                            <dl class="menu menu_first">
                                <dt class="menu_title">
                                    <i class="icon_menu menu_function"></i>基地管理
                                </dt>
                                <dd class="menu_item"><a href="javascript:hosMain('${deptFlow}');">科室管理</a></dd>
                            </dl>
                            <dl class="menu">
                                <dt class="menu_title">
                                    <i class="icon_menu menu_management"></i>入科管理
                                </dt>
                                <dd class="menu_item"><a onclick="admissionEducationManage();">入科教育管理</a></dd>
                            </dl>
                            <dl class="menu">
                                <dt class="menu_title">
                                    <i class="icon_menu menu_statistics"></i>教学管理
                                </dt>
                                <c:set value="jsres_hospital_activity_${sessionScope.currUser.orgFlow }" var="key"/>
                                <c:if test="${pdfn:jsresPowerCfgMap(key) eq GlobalConstant.RECORD_STATUS_Y}">
                                    <dd class="menu_item"><a href="javascript:activityQuery();">教学活动管理</a></dd>
                                    <dd class="menu_item"><a href="javascript:teacherActivityStatistics();">师资活动统计</a></dd>
                                    <dd class="menu_item"><a href="javascript:doctorActivityStatistics();">学员活动统计</a></dd>
                                </c:if>
                                    <%--                                <dd class="menu_item"><a onclick="lectures(null)">讲座信息查询</a></dd>--%>
                                <c:set value="jsres_hospital_jzxxgl_${sessionScope.currUser.orgFlow}" var="jzxxglKey"/>
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
                            <c:set value="jsres_hospital_xyckcjcx_${sessionScope.currUser.orgFlow }" var="key"/>
                            <c:if test="${pdfn:jsresPowerCfgMap(key) eq GlobalConstant.RECORD_STATUS_Y}">
                                <dd class="menu_item"><a onclick="cycleResults(null);">学员出科查询</a></dd>
                            </c:if>
                            <dd class="menu_item" id="recruitAuditSearch"><a onclick="recruitAuditSearch();">培训数据查询</a>
                            </dd>
                            <dd class="menu_item" id="temporaryOutAuditSearch"><a onclick="temporaryOutAuditSearch();">临时出科审核</a>
                            </dd>
                            <dd class="menu_item" id="temporaryOutSearch"><a onclick="temporaryOutSearch();">临时出科查询</a>
                            </dd>
                            <dd class="menu_item" id="evaluateSearch"><a onclick="evaluateSearch();">出科评价查询</a></dd>
                            <c:if test="${pdfn:jsresPowerCfgMap(key) eq GlobalConstant.RECORD_STATUS_Y}">
                                <dd class="menu_item" id="docProcessEval"><a onclick="docProcessEval();">学员考评查询</a></dd>
                            </c:if>
                            <dd class="menu_item" id="recruitAuditSearch"><a onclick="afterAuditSearch();">出科成绩审核</a>
                            </dd>
                            <dd class="menu_item" id="cycleResults"><a onclick="cycleResults();">出科成绩查询</a>
                            </dd>
                            <c:set value="jsres_hospital_xykqcx_${sessionScope.currUser.orgFlow }" var="xykqcxKey"/>
                            <c:if test="${pdfn:jsresPowerCfgMap(xykqcxKey) eq GlobalConstant.RECORD_STATUS_Y}">
                                <dd class="menu_item" id="attendanceSearch"><a onclick="leaveSearch();">学员请假管理</a></dd>
                            </c:if>
                            <c:set value="jsres_${sessionScope.currUser.orgFlow }_export_attendance_head" var="kqdcKey"/>
                            <c:if test="${pdfn:jsresPowerCfgMap(kqdcKey) eq GlobalConstant.RECORD_STATUS_Y}">
                                <dd class="menu_item"><a onclick="attendanceTab()">学员考勤查询</a></dd>
                            </c:if>

                        </dl>
                        <c:set value="jsres_${sessionScope.currUser.orgFlow }_head_audit" var="kzrsh"/>
                        <c:if test="${pdfn:jsresPowerCfgMap(kzrsh) eq GlobalConstant.RECORD_STATUS_Y}">
                            <dl class="menu menu_first">
                                <dt class="menu_title">
                                    <i class="icon_menu menu_function"></i>师资管理
                                </dt>
                                <dd class="menu_item"><a onclick="auditTeacherApplicationMain('${deptFlow}')">审核师资申请</a></dd>
                            </dl>
                        </c:if>
                        <c:if test="${cfgValue eq 'Y'}">
                            <dl class="menu">
                                <dt class="menu_title">
                                    <i class="icon_menu menu_setup"></i>考核管理
                                </dt>
                                <dd class="menu_item"><a href="javascript:EvaStudent();">评价学员</a></dd>
                                <dd class="menu_item"><a href="javascript:pjjgcx('charge');">评价结果查询</a></dd>
<%--                                <dd class="menu_item"><a href="javascript:patientEvaluate();">病人评价查询</a></dd>--%>
                            </dl>
                        </c:if>
                        <dl class="menu">
                            <dt class="menu_title">
                                <i class="icon_menu menu_setup"></i>个人中心
                            </dt>
                            <dd class="menu_item"><a href="javascript:accounts();">安全中心</a></dd>
                            <dd class="menu_item"><a href="javascript:userCenter();">个人信息</a></dd>
                        </dl>
                    </div>
                    <div class="col_main" id="content">
                        <div class="content_main">
                            <div class="index_show">
                                <div class="index_tap top_left">
                                    <ul class="inner">
                                        <li class="index_item index_blue">
                                            <a href="javascript:selectMenu('recruitAuditSearch');">
                  <span class="tap_inner">
                    <i class="message"></i>
                    <em class="number" id="ComingStudentNum"></em>
                    <strong class="title">即将出科人数</strong>
                  </span>
                                            </a>
                                        </li>
                                        <li class="index_item index_blue">
                                            <a href="#">
                  <span class="tap_inner tab_second">
                    <i class="people"></i>
                    <em class="number" id="currStudentHe"></em>
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
                    <em class="number" id="studentNum"></em>
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
 