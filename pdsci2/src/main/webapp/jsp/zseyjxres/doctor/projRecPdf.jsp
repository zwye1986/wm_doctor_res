<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>


<jsp:include page="/jsp/common/htmlhead.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jbox" value="true"/>
    <jsp:param name="jquery_ui_tooltip" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<script type="text/javascript" src="<s:url value='/js/jquery.media.js'/>"></script>


<script type="text/javascript">
   $(document).ready(function(){
       var height=(window.screen.height)*0.7;
       var width=(window.screen.width)*0.7;
//       $(".media").media();
       $('a.media').media({width:width, height:600});
   });
</script>

<div id="main">
    <div class="mainright">
        <div class="content">
            <div style="text-align: center;margin-left: 15%" class="title1 clearfix">
                <a class="media" href="${sysCfgMap['upload_base_url']}/${pdfn:encodeString2(pdfPath)}"></a>
            </div>
        </div>
    </div>
</div>
