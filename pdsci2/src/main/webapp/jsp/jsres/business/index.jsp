<%@ page import="com.pinde.sci.util.jsres.JsresUtil" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="consult" value="true"/>
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
            $(".menu_item a").click(function(){
                $(".select").removeClass("select");
                $(this).addClass("select");
            });
            setBodyHeight();
        });

        $(function () {
            searchUserListNew();
        })

        function setBodyHeight(){
            if (navigator.appName.indexOf("Microsoft Internet Explorer")>-1) {//处理ie浏览器placeholder和password的不兼容问题
                if(navigator.appVersion.indexOf("MSIE 7.0")>-1){
                    $("#indexBody").css("height",window.innerHeight+"px");
                }else if(navigator.appVersion.indexOf("MSIE 8.0")>-1){
                    $("#indexBody").css("height",document.documentElement.offsetHeight+"px");
                }else{
                    $("#indexBody").css("height",window.innerHeight+"px");
                }
            } else {
                $("#indexBody").css("height",window.innerHeight+"px");
            }
        }
        function shouye(){
            var url = "<s:url value='/res/nurse/index'/>";
            window.location.href=url;
        }
        onresize=function(){
            setBodyHeight();
        }

        function searchUserListNew() {
            jboxLoad("content","<s:url value='/jsres/business/doctorUserList'/>?checkStatusId=Passing",true);
        }

        function searchTeaListNew() {
            jboxLoad("content","<s:url value='/jsres/business/teacherHead'/>?checkStatusId=Passing",true);
        }

        function searchHospitalListNew() {
            jboxLoad("content","<s:url value='/jsres/business/hospitalHead'/>?checkStatusId=Passing",true);
        }

        function searchSchoolListNew() {
            jboxLoad("content","<s:url value='/jsres/business/schoolHead'/>?checkStatusId=Passing",true);
        }

        function businessAccounts(){
            jboxLoad("content","<s:url value='/jsres/business/accounts'/>",true);
        }
    </script>
    <style>
        body{overflow:hidden;}
    </style>
</head>

<body>
<div style="overflow:auto;" id="indexBody">
    <div class="bd_bg">
        <div class="yw">

            <div class="head">
                <div class="head_inner">
                    <h1 class="logo">
                        <a onclick="shouye();"><%=JsresUtil.getTitle(request,response,application)%></a>
                    </h1>
                    <div class="account">
                        <h2 style="text-align: right;">您好，<span id="topUserName">${sessionScope.currUser.userName }</span></h2>
                        <div class="head_right">
                            <a onclick="shouye();">首页</a>&#12288;
                            <a href="<s:url value='/inx/jsres/logout'/>">退出</a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="body">
                <div class="container">
                    <div class="content_side">
                        <dl class="menu">
                            <dt class="menu_title">
                                <i class="icon_menu menu_management"></i>权限审核管理
                            </dt>
                            <dd class="menu_item"><a onclick="searchUserListNew();">学员权限审核</a></dd>
                            <dd class="menu_item"><a onclick="searchTeaListNew();">带教权限审核</a></dd>
                            <dd class="menu_item"><a onclick="searchHospitalListNew();">医院权限审核</a></dd>
                            <dd class="menu_item"><a onclick="searchSchoolListNew();">高校权限审核</a></dd>
                            <dt class="menu_title">
                                <i class="icon_menu menu_setup"></i>设置
                            </dt>
                            <dd class="menu_item"><a onclick="businessAccounts();">安全中心</a></dd>
                        </dl>
                    </div>
                    <div class="col_main" id="content">
                    </div>

    <c:if test="${applicationScope.sysCfgMap['online_service']=='Y'}">
        <jsp:include page="/jsp/service.jsp" flush="true"></jsp:include>
    </c:if>
    <div class="foot">
        <div class="foot_inner">
            主管单位：${sysCfgMap['the_competent_unit']}   |  技术支持：南京品德网络信息技术有限公司
        </div>
    </div>

</div>

</body>
</html>
