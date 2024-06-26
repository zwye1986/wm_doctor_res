<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<c:if test="${param.compatible=='true'}">
<meta http-equiv="X-UA-Compatible" content="chrome=1,IE=edge,IE=10;">
</c:if>

<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="Cache-Control" content="no-cache, must-revalidate"> 
<meta http-equiv="expires" content="Wed, 26 Feb 1997 08:21:57 GMT">

<link rel="shortcut icon" href="<s:url value='/jsp/sczyres/sczy.ico'/>" />
<c:set var="min" value=".min"></c:set>
<c:if test="${param.basic=='true'}">
	<script type="text/javascript" src="<s:url value='/js/jquery-1.8.3${min}.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	<script type="text/javascript" src="<s:url value='/js/jquery.SuperSlide.2.1.1.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript" src="<s:url value='/js/common.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
</c:if>
<c:if test="${param.font=='true'}">
	<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/gzzyjxres/css/step.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
	<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/gzzyjxres/css/font.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
</c:if>

<c:if test="${param.login=='true'}">
<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/sczyres/css/login.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
</c:if>

<%--<c:if test="${param.jquery_datePicker=='true'}">--%>
	<%--<script type="text/javascript"--%>
			<%--src="<s:url value='/js/DatePicker/WdatePicker.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>--%>
<%--</c:if>--%>

<c:if test="${param.jquery_datePicker=='true'}">
	<script type="text/javascript" charset="utf-8"
			src="<s:url value='/js/bootstrap-datepicker-en.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	<link rel="stylesheet" type="text/css"
		  href="<s:url value='/css/datepicker.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
</c:if>

<c:if test="${param.register=='true'}">
	<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/gzzyjxres/css/style.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
</c:if>
<c:if test="${param.jbox=='true'}">
	<link rel="stylesheet" type="text/css" href="<s:url value='/js/artDialog/css/ui-dialog.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
	<script type="text/javascript" src="<s:url value='/js/artDialog/dialog-min.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	<script type="text/javascript" src="<s:url value='/js/artDialog/dialog-plus-min.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	<script type="text/javascript" src="<s:url value='/js/common-edu-jbox-en.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
</c:if>
<c:if test="${param.jquery_ui_tooltip=='true'}">
<link rel="stylesheet" type="text/css" href="<s:url value='/css/jquery-ui/product/jquery-ui.min.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
<script type="text/javascript" src="<s:url value='/js/jquery-ui/jquery.ui.core.min.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript" src="<s:url value='/js/jquery-ui/jquery.ui.widget.min.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript" src="<s:url value='/js/jquery-ui/jquery.ui.effect.min.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript" src="<s:url value='/js/jquery-ui/jquery.ui.effect-fold.min.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript" src="<s:url value='/js/jquery-ui/jquery.ui.position.min.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript" src="<s:url value='/js/jquery-ui/jquery.ui.tooltip.min.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
$(document).ready(function(){
	$(document).tooltip({
			//hide: { Bounce : "blind", duration: 1000 } ,
			track: true,
			show:{ effect: "Fold", duration: 800 } ,
			events: {
				def: "mouseover,mouseout",
				input: "focus mouseover,blur mouseout",
				widget: "focus mouseover,blur mouseout",
				tooltip: "mouseover,mouseout"
			},
			content: function() {
		        var element = $( this );
		        if ( element.is( "[title]" ) ) {
		          return element.attr( "title" );
		        }
			}
		}
	);
});
</script>
</c:if>

<c:if test="${param.jquery_placeholder=='true'}">
<link rel="stylesheet" type="text/css" href="<s:url value='/css/jquery.html5.placeholder.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
<script type="text/javascript" src="<s:url value='/js/jquery.html5.placeholder${min}.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
</c:if>

<c:if test="${param.jquery_form=='true'}">
<script type="text/javascript" src="<s:url value='/js/jquery.form${min}.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
</c:if>

<c:if test="${param.jquery_validation=='true'}">
<link rel="stylesheet" type="text/css" href="<s:url value='/css/validationEngine.jquery.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
<script type="text/javascript" src="<s:url value='/js/jquery.validationEngine-zh_EN.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript" src="<s:url value='/js/jquery.validationEngine${min}.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
$(document).ready(function(){
	var forms = $("form");
	$.each(forms , function(i , form){ 
		$(form).validationEngine("attach",{
			promptPosition : "topRight",
			scroll:true,
			autoPositionUpdate: true,
			//addPromptClass:"formError-noArrow formError-text"
			autoHidePrompt:true,
			maxErrorsPerField:1,
			showOneMessage : true
			}
		); 
	});
});
</script>
</c:if>


<c:if test="${param.ueditor=='true'}">
<script type="text/javascript">
	window.UEDITOR_HOME_URL = "<s:url value='/'/>ueditor/";
</script>
<script type="text/javascript" charset="utf-8" src="<s:url value='/ueditor/ueditor.config.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript" charset="utf-8" src="<s:url value='/ueditor/ueditor.all.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript" charset="utf-8" src="<s:url value='/ueditor/lang/zh-cn/zh-cn.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
</c:if>
<c:if test="${param.jquery_ui_combobox=='true'}">
<link rel="stylesheet" type="text/css" href="<s:url value='/css/jquery.scombobox.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
<script type="text/javascript" src="<s:url value='/js/jquery.scombobox.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript" src="<s:url value='/js/jquery.easing${min}.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
</c:if>
<c:if test="${param.echarts=='true'}">
<script type="text/javascript" src="<s:url value='/js/echarts/echarts.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
        // 路径配置
        require.config({
            paths: {
                'echarts' : '<s:url value='/js/echarts'/>'
            }
        });
    </script>
</c:if>
<c:if test="${not empty applicationScope.sysCfgMap['online_notice'] and sessionScope.online_notice!=applicationScope.sysCfgMap['online_notice']}">
<script type="text/javascript">
$(document).ready(function(){
	var url = "<s:url value='/jsp/notice.jsp'/>"+"?time="+new Date();
	//var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='400px' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
	jboxOpen(url,"notice", 800, 400,true);
	//$("#jbox-content").css("overflow-y","hidden");
});
</script>
</c:if>

<c:if test="${param.jquery_cxselect=='true'}">
<script type="text/javascript" src="<s:url value='/js/jquery.cxselect${min}.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
</c:if>

<c:if test="${param.jquery_scrollTo=='true'}">
<script type="text/javascript" src="<s:url value='/js/jquery.scrollTo${min}.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
</c:if>

