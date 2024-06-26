<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>江苏省住院医师规范化培训管理平台</title>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>

</jsp:include>
<script type="text/javascript">

    $(document).ready(function(){
        $('#sessionNumber').datepicker({
            startView: 2,
            maxViewMode: 2,
            minViewMode:2,
            format:'yyyy'
        });
        searchMessage('${sysCfgMap['jsres_local_sessionNumber']}');
    });

    function searchMessage(sessionNumber) {
       var url="<s:url value='/inx/jsres/allOrgMessageList'/>?sessionNumber="+sessionNumber;
        jboxPostLoad("infoTable", url, true);
    }
</script>
</head>

<body>
<div class="bd_bg">
<div class="yw">
 <div class="head">
   <div class="head_inner">
     <h1 class="logo">
       <a href="#">江苏省住院医师规范化培训管理平台</a>
     </h1>
   </div>
 </div>

 <div class="body">
     <div class="container">
         <div class="notice" style="margin: 30px">
             <div class="index_form">
                 <h3>
                     <input class="input" name="sessionNumber" id="sessionNumber" readonly="readonly" value="${sessionNumber}" type="text" onchange="searchMessage(this.value)"/>
                            年各基地招录信息公告
                 </h3>
                     <div id="infoTable">
                     </div>
             </div>
         </div>
     </div>
 </div>
</div>
 
 <div class="footer_index">
 主管单位：${sysCfgMap['the_competent_unit']}   |  技术支持：南京品德网络信息技术有限公司   |  <a href="https://beian.miit.gov.cn/" target="_blank" style="color:#FFFFFF;">工信部备案号：苏ICP备14054231号-1</a>
 </div>

</body>
</html>
