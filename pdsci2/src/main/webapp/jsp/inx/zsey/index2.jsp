<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>逸仙医学教育 - 中山大学孙逸仙纪念医院</title>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE10" />
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<link rel="shortcut icon" href="<s:url value='/jsp/inx/zsey/images/favicon.ico'/>" />
<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/inx/zsey/css/index.css'/>?v=${applicationScope.sysCfgMap['sys_version']}" />
<script type="text/javascript">
    function show(type){
        document.getElementById(type+"func").style.display = "block";
    }
    function hide(type){
        document.getElementById(type+"func").style.display = "none";
    }
</script>
</head>
<body>
<div class="header"><img src="<s:url value='/jsp/inx/zsey/images/banner_index.png'/>" width="1000" height="260" alt="逸仙医学教育" /></div>
<div class="main">
    <div class="nav">
        <img src="<s:url value='/jsp/inx/zsey/images/center-bg.png'/>" width="1000" height="486" alt="逸仙医学教育" />
        <div class="jxjyk" onmouseover="show('jxjyk')" onmouseout="hide('jxjyk')">
            <a class="mod" href="javascript:void(0);">继续教育科</a>
            <div class="func left" id="jxjykfunc">
                <ul>
                    <li><a href="<s:url value='/inx/zseyjxres'/>" target="_blank"><span>进修生</span></a></li>
                    <li><a href="<s:url value='/inx/zsey/loginpage'/>" target="_blank"><span>住院医师</span></a></li>
                    <li><a href="<s:url value='/inx/zsey/loginpage'/>" target="_blank"><span>并轨研究生</span></a></li>
                    <li><a href="<s:url value='/inx/zsey/loginpage'/>" target="_blank"><span>老师</span></a></li>
                    <li><a href="<s:url value='/inx/zsey/loginpage'/>" target="_blank"><span>管理人员</span></a></li>
                </ul>
            </div>
        </div>
        <div class="yjsk" onmouseover="show('yjsk')" onmouseout="hide('yjsk')">
            <a class="mod" href="#">研究生科</a>
            <div class="func right" id="yjskfunc">
                <ul>
                    <li><a href="<s:url value='/jsp/inx/zsey/underconstruction.html'/>" target="_blank"><span>学术型研究生</span></a></li>
                    <li><a href="<s:url value='/jsp/inx/zsey/underconstruction.html'/>" target="_blank"><span>专业型研究生</span></a></li>
                    <li><a href="<s:url value='/jsp/inx/zsey/underconstruction.html'/>" target="_blank"><span>导师</span></a></li>
                    <li><a href="<s:url value='/jsp/inx/zsey/underconstruction.html'/>" target="_blank"><span>管理人员</span></a></li>
                </ul>
            </div>
        </div>
        <div class="hlb" onmouseover="show('hlb')" onmouseout="hide('hlb')">
            <a class="mod" href="#">护理部</a>
            <div class="func right" id="hlbfunc">
                <ul>
                    <li><a href="<s:url value='/jsp/inx/zsey/underconstruction.html'/>" target="_blank"><span>护士</span></a></li>
                    <li><a href="<s:url value='/jsp/inx/zsey/underconstruction.html'/>" target="_blank"><span>护士长</span></a></li>
                    <li><a href="<s:url value='/jsp/inx/zsey/underconstruction.html'/>" target="_blank"><span>管理人员</span></a></li>
                </ul>
            </div>
        </div>
        <div class="jxk" onmouseover="show('jxk')" onmouseout="hide('jxk')">
            <a class="mod" href="#">教学科</a>
            <div class="func left" id="jxkfunc">
                <ul>
                    <li><a href="<s:url value='/jsp/inx/zsey/underconstruction.html'/>" target="_blank"><span>实习生</span></a></li>
                    <li><a href="<s:url value='/jsp/inx/zsey/underconstruction.html'/>" target="_blank"><span>见习生</span></a></li>
                    <li><a href="<s:url value='/jsp/inx/zsey/underconstruction.html'/>" target="_blank"><span>八年制</span></a></li>
                    <li><a href="<s:url value='/jsp/inx/zsey/underconstruction.html'/>" target="_blank"><span>老师</span></a></li>
                    <li><a href="<s:url value='/jsp/inx/zsey/underconstruction.html'/>" target="_blank"><span>管理人员</span></a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="btn">
        <ul>
            <li><a href="http://61.155.106.48:88" target="_blank"><img src="<s:url value='/jsp/inx/zsey/images/zxks.png'/>" width="412" height="108" alt="逸仙医学在线考试系统" /></a></li>
            <li><a href="<s:url value='/jsp/inx/zsey/underconstruction.html'/>" target="_blank"><img src="<s:url value='/jsp/inx/zsey/images/jxpt.png'/>" width="412" height="108" alt="逸仙医学教学平台" /></a></li>
            <li><a href="<s:url value='/jsp/inx/zsey/underconstruction.html'/>" target="_blank"><img src="<s:url value='/jsp/inx/zsey/images/yygl.png'/>" width="412" height="108" alt="临床技术中心预约管理系统" /></a></li>
            <li><a href="<s:url value='/jsp/inx/zsey/underconstruction.html'/>" target="_blank"><img src="<s:url value='/jsp/inx/zsey/images/bmzl.png'/>" width="412" height="108" alt="逸仙报名招录平台" /></a></li>
        </ul>
    </div>
</div>
<div class="footer"><span>主管单位：中山大学孙逸仙纪念医院 | 技术支持：南京品德网络信息技术有限公司</span></div>
</body>
</html>