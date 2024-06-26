<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <title>${sysCfgMap['sys_title_name']}</title>
    <jsp:include page="/jsp/pubedu/htmlhead-pubedu.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
    </jsp:include>
    <script>
        $(document).ready(function(){
            courseSetting();
//            $(".menu_item a").click(function(){
//                $(".select").removeClass("select");
//                $(this).addClass("select");
//            });
            setBodyHeight();
        });

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

        onresize=function(){
            setBodyHeight();
        }
        function toPage(page) {
            if(page!=undefined){
                $("#currentPage").val(page);
            }
            window.location.href="<s:url value='/pubedu/hospital/home'/>?currentPage="+$("#currentPage").val();
        }

        /**
         * 课程维护
         */
        function courseSetting(){
            jboxLoad("content","<s:url value='/pubedu/hospital/courseSetting?currentPage=1'/>",true);
        }

    </script>
</head>
<body>
<div style="overflow:auto;" id="indexBody">
    <div class="">
        <div class="">
            <div class="head">
                <div class="head_inner">
                    <h1 class="logo">
                        <a href="#">公共科目管理平台</a>
                    </h1>
                    <div class="account">
                        <div class="head_right">
                            <img src="<s:url value='/jsp/pubedu/images/message.png'/>"/>欢迎您,${sessionScope.currUser.userName}
                            &#12288;<a href="<s:url value='/inx/pubedu/logout'/>">退出</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="body">
                <div class="container">
                    <div class="content_side">
                        <dl class="menu">
                            <dt class="menu_title">
                                <i class="icon_menu menu_setup"></i>设置
                            </dt>
                            <dd class="menu_item"><a onclick="courseSetting();">课程维护</a></dd>
                        </dl>
                    </div>
                    <div class="col_main" id="content">
                        <div class="content_main">
                            <div class="index_show">

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="footer_box">
        <span class="footer db">技术支持：南京品德网络信息技术有限公司　服务电话：025-69815356&#12288;69815357</span>
    </div>
</div>
</body>
</html>