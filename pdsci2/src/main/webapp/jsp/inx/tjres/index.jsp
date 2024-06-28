<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>天津市卫生健康委员会住院医师规范化培训网络管理平台</title>
    <jsp:include page="/jsp/inx/tjres/htmlhead_tjres.jsp" flush="true">
        <jsp:param name="bootstrap" value="true"/>
    </jsp:include>
    <link rel="stylesheet"
          href="<s:url value='/jsp/inx/tjres/css/index.css'/>?v=${applicationScope.sysCfgMap['sys_version']}">
    <link rel="stylesheet"
          href="<s:url value='/jsp/inx/tjres/iconfont.css'/>?v=${applicationScope.sysCfgMap['sys_version']}">
    <script>

        function checkForm() {
            if ($("#username").val() == "") {
                $(".cw").html("用户名不能为空!");
                return false;
            }
            if ($("#pwd").val() == "") {
                $(".cw").html("密码不能为空!");
                return false;
            }
            /*if ($("#verifyCode").val() == "") {
                $(".log_tips").html("验证码不能为空!");
                return false;
            }*/
            $("#loginForm").submit();
        }
    </script>
</head>
<body>
<!-- 顶部导航条开始 -->
<nav class="navbar navbar-default">
    <div class="navbar_content">
        <div class="navbar-header">
            <img src="<s:url value='/jsp/inx/tjres/images/logo.png'/>">
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li>
                    <a class="choose" href="<s:url value='/inx/tjres'/>">首页</a>
                </li>
                <li>
                    <a href="<s:url value='/inx/tjres/loadInfoList?columnId=LM01'/>">公示公告</a>
                </li>
                <li>
                    <a href="<s:url value='/inx/tjres/loadInfoList?columnId=LM02'/>">培训资讯</a>
                </li>
                <li>
                    <a href="<s:url value='/inx/tjres/loadInfoList?columnId=LM03'/>">使用帮助</a>
                </li>
                <li>
                    <a href="<s:url value='/inx/tjres/contact'/>">联系我们</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<!-- 顶部导航条结束 -->
<!-- banner图部分开始 -->
<div class="container-fluid bg">
    <div class="row">
        <div class="col-xs-12">
            <img src="<s:url value='/jsp/inx/tjres/images/banner.png'/>" class="img_banner">
            <div class="banner_box">
                <div class="banner-box_left">
                    <img src="<s:url value='/jsp/inx/tjres/images/title.png'/>">
                </div>
                <div class="banner-box_right">
                    <form id="loginForm" class="form-login" action="<s:url value='/inx/tjres/login'/>" method="post">
                        <div class="form-header">
                            <!-- <span>LOGIN</span> -->
                            <img src="<s:url value='/jsp/inx/tjres/images/login.png'/>">
                            <a href="#">注册</a>
                        </div>
                        <div class="form-group">
                            <label for="username">用户名</label>
                            <i class="iconfont icon-wode2"></i>
                            <input type="text" class="form-control" name="userCode" id="username" placeholder="请输入用户名">
                        </div>
                        <div class="form-group pwd-box">
                            <label for="pwd">密码</label>
                            <i class="iconfont icon-mima"></i>
                            <input type="password"  onpaste="return false" oncontextmenu="return false" oncopy="return false" oncut="return false" name="userPasswd" class="form-control" id="pwd" placeholder="请输入密码">
                        </div>
                        <span class="cw" style="color: #ff0000">
                            <c:if test="${not empty loginErrorMessage}">
                                ${loginErrorMessage}
                            </c:if>
                        </span>
                        <button class="btn btn-block" onclick="return checkForm();">
                            登 录
                        </button>
                        <a href="http://tj.zyysgp.com" target="_blank" class="old-link">
                            点击进入旧版(2015届)>>
                        </a>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- banner图部分结束 -->
<!-- 公示公告部分开始 -->
<div class="container notice_container">
    <div class="row">
        <div class="notice_left">
            <div class="notice_header">
                <div class="notice_title">
                    <p>公示公告</p>
                    <p></p>
                </div>
                <a href="<s:url value='/inx/tjres/loadInfoList?columnId=LM01'/>">更多>></a>
            </div>
            <div class="notice_img">
                <img src="<s:url value='/jsp/inx/tjres/images/notice.png'/>">
            </div>
            <div class="notice_info">
                <ul>
                    <c:forEach items="${lm01List}" var="lm01">
                        <li>
                            <a href="<s:url value='/inx/tjres/loadInfo?infoFlow=${lm01.infoFlow}'/>">${pdfn:cutString(lm01.infoTitle,30,true,6)}</a><span>${lm01.infoTime}</span>
                        </li>
                    </c:forEach>
                    <c:if test="${empty lm01List}">
                        <li>无记录！</li>
                    </c:if>
                </ul>
            </div>
        </div>
        <div class="notice_right">
            <div class="notice_header">
                <div class="notice_title">
                    <p>培训资讯</p>
                    <p></p>
                </div>
                <a href="<s:url value='/inx/tjres/loadInfoList?columnId=LM02'/>">更多>></a>
            </div>
            <div class="notice_info">
                <ul>
                    <c:forEach items="${lm02List}" var="lm02">
                        <li>
                        <a href="<s:url value='/inx/tjres/loadInfo?infoFlow=${lm02.infoFlow}'/>">${pdfn:cutString(lm02.infoTitle,20,true,6)}</a><%--<span>${lm02.infoTime}</span>--%>
                        </li>
                    </c:forEach>
                    <c:if test="${empty lm02List}">
                        <li>无记录！</li>
                    </c:if>
                </ul>
            </div>
            <div class="notice_header">
                <div class="notice_title">
                    <p>使用帮助</p>
                    <p></p>
                </div>
                <a href="<s:url value='/inx/tjres/loadInfoList?columnId=LM03'/>">更多>></a>
            </div>
            <div class="notice_info">
                <ul>
                    <c:forEach items="${lm03List}" var="lm03">
                        <li>
                            <a href="<s:url value='/inx/tjres/loadInfo?infoFlow=${lm03.infoFlow}'/>">${pdfn:cutString(lm03.infoTitle,18,true,6)}</a><%--<span>${lm02.infoTime}</span>--%>
                        </li>
                    </c:forEach>
                    <c:if test="${empty lm03List}">
                        <li>无记录！</li>
                    </c:if>
                </ul>
            </div>
        </div>
    </div>
</div>
<!-- 公告部分结束 -->
<!-- 招生信息开始 -->
<%--<div class="container-fluid stu_container">
    <div class="row">
        <div class="stuinfo_title">
            <div class="stut_left">
                最新医院招生信息
            </div>
            <div class="stut_right">
                <span>搜索</span>
                <input type="text" class="form-control" placeholder="请输入医院名称">
                <i class="iconfont icon-iconfonticonfontsousuo1"></i>
            </div>
        </div>
    </div>
    <div class="hospital">
        <div class="hospital_title">
            <div class="hospital-t_left">医院名称</div>
            <div class="hospital-t_middle">各科室招生人员（人）</div>
            <div class="hospital-t_right">总招生人数（人）</div>
        </div>
        <div class="hospital_content">
            <div class="hospital-c_row">
                <div class="hospital-c_left">天津医科大学总医院</div>
                <div class="hospital-c_middle">60</div>
                <div class="hospital-c_right">100</div>
            </div>
            <div class="hospital-c_row">
                <div class="hospital-c_left">天津医科大学第二医院</div>
                <div class="hospital-c_middle">60</div>
                <div class="hospital-c_right">100</div>
            </div>
            <div class="hospital-c_row">
                <div class="hospital-c_left">天津市第一中心医院</div>
                <div class="hospital-c_middle">60</div>
                <div class="hospital-c_right">100</div>
            </div>
            <div class="hospital-c_row">
                <div class="hospital-c_left">天津市人民医院</div>
                <div class="hospital-c_middle">60</div>
                <div class="hospital-c_right">100</div>
            </div>
            <div class="hospital-c_row">
                <div class="hospital-c_left">天津市第三中心医院</div>
                <div class="hospital-c_middle">60</div>
                <div class="hospital-c_right">100</div>
            </div>
            <div class="hospital-c_row">
                <div class="hospital-c_left">天津市第三中心医院</div>
                <div class="hospital-c_middle">60</div>
                <div class="hospital-c_right">100</div>
            </div>
            <div class="hospital-c_row">
                <div class="hospital-c_left">天津市第四中心医院</div>
                <div class="hospital-c_middle">60</div>
                <div class="hospital-c_right">100</div>
            </div>
            <div class="hospital-c_row">
                <div class="hospital-c_left">天津市第五中心医院</div>
                <div class="hospital-c_middle">60</div>
                <div class="hospital-c_right">100</div>
            </div>
            <div class="hospital-c_row">
                <div class="hospital-c_left">中国人民武装警察部队医学院附属医院</div>
                <div class="hospital-c_middle">60</div>
                <div class="hospital-c_right">100</div>
            </div>
        </div>
        <!-- 分页 -->
        <div class="hospital_footer">
            <div class="hospital-f_left">
                <p>第1页</p>
                <p>共8页</p>
            </div>
            <div class="hospital-f_middle">
                <ul class="pagination">
                    <li><a href="#">首页</a></li>
                    <li><a href="#">上一页</a></li>
                    <li class="page">
                        <a href="#">1</a>
                    </li>
                    <li><a href="#">下一页</a></li>
                    <li><a href="#">尾页</a></li>
                </ul>
            </div>
            <div class="hospital-f_right">
                <span>跳转到</span>
                <div class="dropdown">
                    <button type="button" class="btn dropdown-toggle" id="dropdownMenu1" data-toggle="dropdown">2
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                        <li role="presentation">
                            <a role="menuitem" tabindex="-1" href="#">1</a>
                        </li>
                        <li role="presentation">
                            <a role="menuitem" tabindex="-1" href="#">2</a>
                        </li>
                        <li role="presentation">
                            <a role="menuitem" tabindex="-1" href="#">3</a>
                        </li>
                        <li role="presentation">
                            <a role="menuitem" tabindex="-1" href="#">4</a>
                        </li>
                    </ul>
                    <span>页</span>
                </div>
            </div>
        </div>
    </div>
</div>--%>
<!-- 招生信息结束 -->
<!-- 友情链接开始 -->
<div class="container-fluid link_container">
    <div class="row">
        <div class="link_title">
            <div class="stut_left">
                友情链接
            </div>
        </div>
    </div>
    <div class="link_top">
        <div>
            <a href="http://www.nhfpc.gov.cn/" target="_blank">
                <img src="<s:url value='/jsp/inx/tjres/images/link1.png'/>">
            </a>
        </div>
        <div>
            <a href="http://www.tjwsj.gov.cn/html/WSJn/portal/index/index.htm" target="_blank">
                <img src="<s:url value='/jsp/inx/tjres/images/link2.png'/>">
            </a>
        </div>
        <div>
            <a href="http://www.njpdxx.com/" target="_blank">
                <img src="<s:url value='/jsp/inx/tjres/images/link3.png'/>">
            </a>
        </div>
        <div>
            <a href="http://www.tj.gov.cn/" target="_blank">
                <img src="<s:url value='/jsp/inx/tjres/images/link4.png'/>">
            </a>
        </div>
    </div>
    <div class="link_bottom">
        <div>
            <a href="http://www.tjmugh.com.cn/" target="_blank">
                <img src="<s:url value='/jsp/inx/tjres/images/link5.png'/>">
            </a>
        </div>
        <div>
            <a href="http://www.wjyxyfy.com/" target="_blank">
                <img src="<s:url value='/jsp/inx/tjres/images/link6.png'/>">
            </a>
        </div>
        <div>
            <a href="http://www.tj3zx.cn/" target="_blank">
                <img src="<s:url value='/jsp/inx/tjres/images/link7.png'/>">
            </a>
        </div>
        <div>
            <a href="http://www.yd2y.com.cn/" target="_blank">
                <img src="<s:url value='/jsp/inx/tjres/images/link8.png'/>">
            </a>
        </div>
    </div>
</div>
<!-- 友情链接结束 -->
<!-- 底栏开始 -->
<div class="container-fluid footer_container">
    <div class="row">
        <div class="footer_title">
            Copyright &copy; 2015 主办单位：天津市卫生健康委员会
        </div>
        <div class="footer_text">
            <div class="footer-text_box">


                <div class="footer-t_left">
                    <p>技术支持</p>
                    <p>南京品德信息网络技术有限公司</p>
                </div>
                <div class="footer-t_right">
                    <p>电话：400-999-6635</p>
                    <p>天津客服QQ：2885400132 2885400144</p>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 底栏结束 -->
</body>
</html>