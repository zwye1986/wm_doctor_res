<%@ page import="com.pinde.sci.util.jsres.JsresUtil" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_cxselect" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
    </jsp:include>
    <script type="text/javascript"
            src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript"
            src="<s:url value='/js/DatePicker/WdatePicker.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script>
        function gradeSearch() {
            if (isHaveDept()) {
                jboxTip("您暂无所属专业，请联系管理员设置");
                return
            }
            var form = $("#gradeSearchForm").serialize() || {"gradeRole": "teacher"};
            var url = "<s:url value='/jsres/manage/gradeSearch'/>?role=${GlobalConstant.USER_LIST_SPELOCALSECRETARY}";
            jboxStartLoading();
            jboxPost(url, form, function (resp) {
                jboxEndLoading();
                $("#content").html(resp);
            }, null, false);
        }

        function doctorList() {
            if (isHaveDept()) {
                jboxTip("您暂无所属专业，请联系管理员设置");
                return
            }
            var roleFlag = "${GlobalConstant.USER_LIST_SPELOCALSECRETARY}";
            jboxLoad("content", "<s:url value='/jsres/doctorRecruit/provinceDoctorListNew'/>?roleFlag=" + roleFlag, true);
        }

        function isHaveDept() {
            var isHaveDept = '${isHaveDept}';
            if (isHaveDept == 'N') {
                return true;
            }
            return false;
        }

        function cycle(data) {
            if (isHaveDept()) {
                jboxTip("您暂无所属专业，请联系管理员设置");
                return
            }
            var docTypes = "";
            <c:forEach items="${jsResDocTypeEnumList}" var="type">
            if (docTypes == "") {
                docTypes += "docTypes=" + "${type.id}";
            } else {
                docTypes += "&docTypes=" + "${type.id}";
            }
            </c:forEach>
            var url = "<s:url value='/jsres/doctorRecruit/cycle'/>?roleFlag=${GlobalConstant.USER_LIST_SPELOCALSECRETARY}&" + docTypes;
            jboxStartLoading();
            jboxPost(url, data, function (resp) {
                $("#content").html(resp);
                jboxEndLoading();
            }, null, false);
        }

        function statisticsAppUser() {
            if (isHaveDept()) {
                jboxTip("您暂无所属专业，请联系管理员设置");
                return;
            }
            jboxLoad("content", "<s:url value='/jsres/appUseInfo/main?userListScope=${GlobalConstant.USER_LIST_SPELOCALSECRETARY}'/>", false);
        }

        function attendanceTab(){
            if (isHaveDept()) {
                jboxTip("您暂无所属专业，请联系管理员设置");
                return;
            }
            var url="<s:url value='/jsp/jsres/attendance/hospital/newMain.jsp'/>";
            jboxLoad("content", url, false);
        }

        function shouye(page) {
            if (page == null || page == undefined || page == '' || isNaN(page)) {
                page = 1;
            }
            var url = "<s:url value='/jsres/manage/speAdminSecretary'/>?currentPage=" + page;
            window.location.href = url;
        }
        $(function () {
            $(".menu_item a").click(function(){
                $(".select").removeClass("select");
                $(this).addClass("select");
            });
            getSpandAndshousuo();
            getAA();
        });
        //菜单收缩展开yuh
        function getSpandAndshousuo() {
            var $all_dl= $("dl");
            for(var i =0;i< $all_dl.length;i++){
                $all_dl[i].id ="dl_"+i;
                $("#dl_"+i+" dt").css("cursor","pointer");//图标手形

                $("#dl_"+i+" dt").on("click",function(){
                    var parentId = $(this)[0].parentElement.id;
                    var $all_dd = $("#"+parentId+" dd");
                    for(var j =0;j< $all_dd.length;j++){
                        if($all_dd[j].hidden){
                            $all_dd[j].hidden=false;
                        }else{
                            $all_dd[j].hidden=true;
                        }
                    }
                });
            }
        }
        function getAA() {
            var $all_dl= $("dl");
            for(var i =0;i< $all_dl.length;i++){
                $all_dl[i].id ="dl_"+i;

                var $all_dd= $("#dl_"+i+" dd")
                for(var j =0;j< $all_dd.length;j++){
                    var a_id=   $all_dd[j].children[0].id=i+"_"+j+"aa";
                    $("#"+a_id).on("click",function(){
                        $("html,body").animate({scrollTop:0}, 500);
                    });
                }
            }
        }
    </script>
<body>
<div id="indexBody">
    <div class="bd_bg">
        <div class="yw">

            <div class="head">
                <div class="head_inner">
                    <h1 class="logo">
                        <a onclick="shouye();"><%=JsresUtil.getTitle(request, response, application)%>
                        </a>
                    </h1>
                    <div class="account">
                        <h2 class="head_right">${sessionScope.currUser.orgName }</h2>
                        <div class="head_right">
                            <jsp:include page="/jsp/jsres/changeRole.jsp" flush="true"></jsp:include>
                            <a onclick="shouye();">首页</a>&#12288;
                            <a href="<s:url value='/inx/jsres/logout'/>">退出</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="body">
                <div class="container">
                    <div class="content_side">
                        <input type="hidden" id="orgFlow3" value='${orgFlow}'/>
                        <dl class="menu menu_first">
                            <dt class="menu_title">
                                <i class="icon_menu menu_function"></i>培训过程管理
                            </dt>
                            <dd class="menu_item" id="doctorList"><a onclick="doctorList();">学员信息查询</a></dd>
                            <dd class="menu_item"><a onclick="cycle(null);">学员轮转查询</a> </dd>
                            <dd class="menu_item"><a onclick="attendanceTab()">学员考勤查询</a></dd>
<%--                            <dd class="menu_item"><a href="javascript:statisticsAppUser();">账户登录情况查询</a></dd>--%>
                            <dd class="menu_item" id="gradeSearch"><a onclick="gradeSearch();">出科评价查询</a></dd>
                        </dl>
                    </div>
                    <div class="col_main" id="content">

                    </div>
                </div>
            </div>
        </div>
        <c:if test="${applicationScope.sysCfgMap['online_service']=='Y'}">
            <jsp:include page="/jsp/service.jsp" flush="true"></jsp:include>
        </c:if>
        <div class="foot">
            <div class="foot_inner">
                主管单位：${sysCfgMap['the_competent_unit']} | 技术支持：南京品德网络信息技术有限公司
            </div>
        </div>

    </div>
</div>
</body>
</html>
