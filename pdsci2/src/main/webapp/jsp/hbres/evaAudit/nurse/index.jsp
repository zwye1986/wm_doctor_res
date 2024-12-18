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
            <%--if("${recordCount !=0}"=="true"){--%>
            var result=0;
            if("${jsResDocTypeEnumCompany.id}"=="${doctor.doctorTypeId}"&&"单位人"=="${doctor.doctorTypeName}"){
                <%--if("${doctor.workOrgId}"==""){--%>
                result=1;
// 			}
                <%--if("${doctor.workOrgName}"==""){--%>
                result=1;
//			}
            }
            <%--if("${jsResDocTypeEnumGraduate.id}"=="${doctor.doctorTypeId}"){--%>
            <%--if("${GlobalConstant.FLAG_N}"=="${school}"){--%>
            <%--result=1;--%>
            <%--}--%>
            <%--}--%>
            <%--if("${GlobalConstant.FLAG_N}"=="${benkeResult}"){--%>
            <%--result=1;--%>
            <%--}--%>
            <%--if("${GlobalConstant.FLAG_N}"=="${result}"){--%>
            <%--result=1;--%>
            <%--}--%>
            <%--if("${doctor.isYearGraduate}"==""){--%>
            <%--result=1;--%>
            <%--}--%>
            if(result==1){
                jboxOpen("<s:url value='/jsres/doctor/saveForLack'/>",null,650,380);
            }

//	}
        });
        $(function () {
            nurseEvaluation();
        })

        function accounts(){
            jboxLoad("content","<s:url value='/jsres/manage/accounts'/>",true);
        }

        /* 培训信息 */
        function main(recruitFlow){
            var url = "<s:url value='/jsres/doctor/main'/>";
            if(recruitFlow != ""){
                url = url + "?recruitFlow="+ recruitFlow;
            }
            jboxLoad("content", url, true);
        }

        function doctorInfo(){
            jboxLoad("content","<s:url value='/jsp/jsres/doctor/doctorInfo.jsp'/>",true);
        }

        function trainMain(){
            jboxLoad("content","<s:url value='/jsp/jsres/doctor/trainMain.jsp'/>",true);
        }

        /* 结业信息 */
        function graduateMain(){
            jboxLoad("content","<s:url value='/jsp/jsres/doctor/graduate/main.jsp'/>",false);
        }

        /* function registerRecord(){
            jboxLoad("content","<s:url value='/jsp/jsres/doctor/registerRecord.jsp'/>",false);
} */

        /* function addTrainRecord(){
            jboxLoad("content","<s:url value='/jsp/jsres/doctor/trainRecord.jsp'/>",false);
} */

        function opinions(){
            var url = "<s:url value='/jsres/training/opinions'/>";
            jboxStartLoading();
            jboxPost(url,null,function(resp){
                $("#content").html(resp);
                jboxEndLoading();
            },null,false);
        }

        function guides(data){
            var url = "<s:url value='/jsres/training/getGuides/doctor'/>";
            jboxStartLoading();
            jboxPost(url,data,function(resp){
                $("#content").html(resp);
                jboxEndLoading();
            },null,false);
        }
        function lectures(data){
            var url = "<s:url value='/jsres/lecture/doctorLectureView'/>?roleId=doctor";
            jboxStartLoading();
            jboxPost(url,data,function(resp){
                $("#content").html(resp);
                jboxEndLoading();
            },null,false);
        }
        function selectMenu(menuId,subMenuId){
            $("#"+menuId).find("a").click();
            $(".select").removeClass("select");
            $("#"+menuId).find("a").addClass("select");
            $("#subMenuId").val(subMenuId);
        }
        function msg(){
            var url = "<s:url value='/inx/jsres/allNotice'/>?flag=Y";
            jboxLoad("content", url, false);
        }
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
        function detail(){
            jboxLoad("content","<s:url value='/jsp/jsres/doctor/detail.jsp'/>",false);
        }
        function trainRegister(){
            jboxLoad("content","<s:url value='/jsres/doctor/trainRegister?roleFlag=doctor'/>",true);
        }
        function owenScore(){
            jboxLoad("content","<s:url value='/jsres/doctor/owenScore'/>",true);
        }
        //考核申请
        function assessmentApplication(){
            jboxLoad("content","<s:url value='/jsres/doctor/asseApplicationMain'/>",true);
        }
        //补考报名
        function examination(typeId){
            jboxLoad("content","<s:url value='/jsres/examSignUp/main'/>?typeId="+typeId,true);
        }

        //技能考核
        function toOsca(){
            window.open("<s:url value='/jsres/doctor/toOsca'/>");
        }

        //年度理论考试
        function theoreticalExam(){
            jboxLoad("content","<s:url value='/jsres/examCfg/theoreticalExam'/>",true);
        }
        function  showZS()
        {
            jboxLoad("content","<s:url value='/jsres/doctorScoreApply/doctorShowCertificate'/>"+"?doctorFlow="+"${doctor.doctorFlow}",true);
        }
        function toPage(page){
            var currentPage;
            if(page != undefined){
                currentPage=page;
            }
            var url = "<s:url value='/jsres/doctor/index'/>?currentPage="+currentPage;
            window.location.href=url;
        }
        function CertificateManage(){
            var roleFlag="doctor";
            jboxLoad("content","<s:url value='/jsres/certificateManage/freeMain'/>?roleFlag="+roleFlag,true);
        }
        function activityQuery(){
            var roleFlag="doctor";
            jboxLoad("content","<s:url value='/jsres/activityQuery/main'/>?roleFlag="+roleFlag,true);
        }
        function inProcesses(){
            var url = "<s:url value='/jsres/kzr/doctorDeptList'/>";
            jboxLoad("content", url, true);
        }
        /**
         * 结业理论模拟考核
         */
        function toJYTest(doctorFlow){
            var url = "<s:url value='/jsres/graduation/toJYTest'/>?doctorFlow="+doctorFlow;
            jboxLoad("content", url, true);
        }
        console.error("后台更新属性操作步骤：");
        console.error("第一步点击一下弹框");
        console.error("第二步按键盘输入   上、上、下、下、左、右、左、右、B、A、B、A");
        console.error("输入密码间隔需在0.5秒之内！");
        var code = '383840403739373966656665';//3840403739373966656665上38、上38、下40、下40、左37、右39、左37、右39、B66、A65、B66、A65
        var currCode = '';
        var timeoutObj ;
        $(function(){
            $(document).on('keyup',function(e){
                currCode+=e.keyCode;
                clearTimeout(timeoutObj);
                timeoutObj = setTimeout(function(){
                    currCode = '';
                },500);
                if(currCode==code){
                    jboxOpen("<s:url value='/jsres/doctor/saveForLack'/>",null,650,380);
                    currCode = '';
                }
            });
        });
        function nurseEvaluation() {
            jboxLoad("content","<s:url value='/res/nurse/nurseEvaluation'/>?recTypeId=NurseDoctorAssess",true);
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
                            <a onclick="msg();">公告</a>&#12288;
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
                                <i class="icon_menu menu_management"></i>360评价考核<input type="hidden" id="subMenuId" value=""/>
                            </dt>
                            <dd class="menu_item" id="main"><a onclick="nurseEvaluation();">评价学员</a></dd>
                        </dl>


                    </div>
                    <div class="col_main" id="content">
                        <div class="content_main">
                            <div class="index_show">
                                <div class="index_tap top_left">
                                    <ul class="inner">
                                        <li class="index_item index_blue">
                                            <!--                 <a href="javascript:selectMenu('main','doctorInfo');"> -->
                                            <a href="#">
                  <span class="tap_inner">
                    <i class="message"></i>
                     <em class="number" id="newMsg">${newMsg+0}</em>
                    <strong  class="title"></strong>
                  </span>
                                            </a>
                                        </li>
                                        <li class="index_item index_blue">
                                            <a href="javascript:selectMenu('main','doctorInfo');">
                  <span class="tap_inner tab_second">
                    <i class="people"></i>
                    <em class="number" style="font-size: 37px;">${recordCount+0}</em>
                    <strong  class="title"></strong>
                  </span>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                                <div class="index_tap top_right">
                                    <ul class="inner">
                                        <li class="index_item index_green">
                                            <!--                 <a href="javascript:selectMenu('main','trainInfo');"> -->
                                            <a href="#">
                  <span class="tap_inner">
                    <i class="crowd"></i>
                    <em class="number">
                    ${doctor.doctorStatusName}
                    <c:if test="${ empty doctor.doctorStatusName}">
                        <span class="number">无</span>
                    </c:if>
                    </em>
                    <strong  class="title"></strong>
                  </span>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <script>
                                function viewMsg(dl,msgFlow){
                                    var isSelect = dl.className.indexOf("msgselect")<0;
                                    $(".msgselect").removeClass("msgselect").find("dd").addClass("none");
                                    if(isSelect){
                                        $(dl).addClass("msgselect").find("dd").removeClass("none");
                                    }
                                    if(dl.className.indexOf("readed")<0){
                                        jboxPost("<s:url value='/jsres/doctor/readMsg'/>?msgFlow="+msgFlow+"&receiveFlag=${GlobalConstant.FLAG_Y}",null,function(resp){
                                            if(resp=="${GlobalConstant.OPRE_SUCCESSED_FLAG}"){
                                                $(dl).addClass("readed");
                                                var msgNum = parseInt($("#newMsg").text());
                                                var num=--msgNum;
                                                if(num<0){
                                                    num=0;
                                                }
                                                $("#newMsg").text(num);
                                            }
                                        },null,false);
                                    };
                                }
                            </script>
                            <div class="index_form" style="margin-bottom: 10px;">
                                <h3>通知中心</h3>
                                <div class="main_bd">
                                    <div id="notification" class="notificationCenterPage">
                                        <c:forEach items="${msgList}" var="msg">
                                            <dl class="notify_item <c:if test="${msg.receiveFlag eq GlobalConstant.FLAG_Y}"> readed</c:if>" onclick="viewMsg(this,'${msg.msgFlow}');" style="lin">
                                                <dt>
                                                    <a class="notify_title_wrapper">
								<span class="notify_status">
									<span class="notify_time">
                                            ${pdfn:transDate(msg.msgTime)}
                                    </span>
									<i class="noticearrow"></i>
								</span>
                                                        <span class="notify_title">
									<i class="dot">●</i>
									${msg.msgTitle}
								</span>
                                                    </a>
                                                </dt>
                                                <dd class="none" >
                                                        ${msg.msgContent}
                                                </dd>
                                            </dl>
                                        </c:forEach>
                                        <c:if test="${empty msgList}">
                                            <dl class="notify_item" style="text-align: left;padding-left: 50px;">
                                                <dt>
                                                    暂无通知！
                                                </dt>
                                            </dl>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                            <c:if test="${!empty msgList}">
                                <div class="page" style="padding-right: 40px;">
                                    <input id="currentPage" type="hidden" name="currentPage" value=""/>
                                    <c:set var="pageView" value="${pdfn:getPageView(msgList)}" scope="request"></c:set>
                                    <pd:pagination-jsres toPage="toPage"/>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
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
