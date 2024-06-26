<%@include file="/jsp/common/doctype.jsp" %>

<title>${sysCfgMap['sys_title_name']}</title>
<link href="<s:url value='/jsp/pubedu/css/font.css' />" type="text/css"  rel="stylesheet" />

    <div class="head">
        <div class="head_inner">
            <h1 class="logo">
                <a href="#">公共科目管理平台</a>
            </h1>
            <div class="account">
                <!--<h2>武汉同济医院</h2>-->
                <div class="head_right">
                    <img src="<s:url value='/jsp/pubedu/images/message.png'/>"/>欢迎您,${sessionScope.currUser.userName}
                    &#12288;<a href="<s:url value='/pubedu/doctor/home'/>">首页</a>&nbsp;&nbsp;<a href="<s:url value='/inx/pubedu/logout'/>">退出</a>
                </div>
            </div>
        </div>
    </div>


