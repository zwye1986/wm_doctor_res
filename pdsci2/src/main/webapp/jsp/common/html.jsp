<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<!-- meta使用viewport以确保页面可自由缩放 -->
<meta name="viewport" content="width=device-width, initial-scale=1">


<c:choose>
    <c:when test="${empty applicationScope.sysCfgMap['sys_jbox'] or applicationScope.sysCfgMap['sys_jbox']=='jbox'}">
        <link rel="stylesheet" type="text/css" href="<s:url value='/css/jBox/Blue/jbox.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
        <script type="text/javascript" src="<s:url value='/js/jquery.jBox.min.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery.jBox-zh-CN.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
        <script type="text/javascript" src="<s:url value='/js/common-jbox.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery-ui/jquery.ui.core.min.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery-ui/jquery.ui.position.min.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    </c:when>
    <c:when test="${applicationScope.sysCfgMap['sys_jbox']=='art'}">
        <link rel="stylesheet" type="text/css" href="<s:url value='/js/artDialog/css/ui-dialog.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
        <script type="text/javascript" src="<s:url value='/js/artDialog/dialog-min.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
        <script type="text/javascript" src="<s:url value='/js/artDialog/dialog-plus-min.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
        <script type="text/javascript" src="<s:url value='/js/common-art.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    </c:when>
</c:choose>
<script>
    $(document).ready(function(){
        jboxEndLoading();
    });
</script>

<!-- 引入 jQuery Mobile 样式 -->
<link rel="stylesheet" href="<s:url value='/jsp/eval/base/common/js/jquery.mobile-1.4.5.min.css'/>">

<!-- 引入 jQuery 库 -->
<script src="<s:url value='/jsp/eval/base/common/js/jquery.js'/>"></script>

<!-- 引入 jQuery Mobile 库 -->
<script src="<s:url value='/jsp/eval/base/common/js/jquery.mobile-1.4.5.min.js'/>"></script>
<script src="<s:url value='/jsp/eval/base/common/js/jquery.validate.js'/>"></script>
<script src="<s:url value='/jsp/eval/base/common/js/DatePicker/WdatePicker.js'/>"></script>


<link rel="stylesheet" href="<s:url value='/jsp/eval/base/common/css/reset.css'/>">
<link rel="stylesheet" href="<s:url value='/jsp/eval/base/common/css/zdialog.css'/>">
<script src="<s:url value='/jsp/eval/base/common/js/zdialog.js'/>"></script>
<script>

    var autoTextarea = function (elem, extra, maxHeight) {
        extra = extra || 0;
        var isFirefox = !!document.getBoxObjectFor || 'mozInnerScreenX' in window,
                isOpera = !!window.opera && !!window.opera.toString().indexOf('Opera'),
                addEvent = function (type, callback) {
                    elem.addEventListener ?
                            elem.addEventListener(type, callback, false) :
                            elem.attachEvent('on' + type, callback);
                },
                getStyle = elem.currentStyle ? function (name) {
                    var val = elem.currentStyle[name];

                    if (name === 'height' && val.search(/px/i) !== 1) {
                        var rect = elem.getBoundingClientRect();
                        return rect.bottom - rect.top -
                                parseFloat(getStyle('paddingTop')) -
                                parseFloat(getStyle('paddingBottom')) + 'px';
                    };

                    return val;
                } : function (name) {
                    return getComputedStyle(elem, null)[name];
                },
                minHeight = parseFloat(getStyle('height')+30);


        elem.style.resize = 'none';

        var change = function () {
            var scrollTop, height,
                    padding = 0,
                    style = elem.style;

            if (elem._length === elem.value.length) return;
            elem._length = elem.value.length;

            if (!isFirefox && !isOpera) {
                padding = parseInt(getStyle('paddingTop')) + parseInt(getStyle('paddingBottom'));
            };
            scrollTop = document.body.scrollTop || document.documentElement.scrollTop;

            elem.style.height = minHeight + 'px';
            if (elem.scrollHeight > minHeight) {
                if (maxHeight && elem.scrollHeight > maxHeight) {
                    height = maxHeight - padding;
                    style.overflowY = 'auto';
                } else {
                    height = elem.scrollHeight - padding;
                    style.overflowY = 'hidden';
                };
                style.height = height + extra + 'px';
                scrollTop += parseInt(style.height) - elem.currHeight;
                document.body.scrollTop = scrollTop;
                document.documentElement.scrollTop = scrollTop;
                elem.currHeight = parseInt(style.height+30);
            };
        };

        addEvent('propertychange', change);
        addEvent('input', change);
        addEvent('focus', change);
        addEvent('click', change);
        change();
    };
</script>