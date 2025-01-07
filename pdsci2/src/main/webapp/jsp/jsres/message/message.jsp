<%@ page import="com.pinde.sci.util.JsresUtil" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title><%=JsresUtil.getTitle(request,response,application)%></title>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="font" value="true"/>
</jsp:include>
    <style>
        .container{
            position: relative;
            padding-bottom: 40px;
            box-sizing: border-box;
        }
        .footer-info{
            position: absolute;
            bottom: 20px;
            right: 20px;
            font-size: 16px;
        }

        .ques_img {
            position: fixed;
            right: 0px;
            top: 370px;
            z-index: 1000;
            margin-right: -40px;
        }

        .ques_img img {
            width: 200px;
        }
    </style>
<script type="text/javascript">
    function toRegister() {
        window.location.href = "<s:url value='/inx/jsres/register'/>";
    }
</script>

</head>

<body>
<div class="bd_bg">
<div class="yw">
 <div class="head">
   <div class="head_inner">
     <h1 class="logo">
       <a href="#"><%=JsresUtil.getTitle(request,response,application)%></a>
     </h1>
   </div>
 </div>

 <div class="body">
     <div class="ques_img">
         <img src="<s:url value='/jsp/jsres/images/toRegister.png'/>" onclick="toRegister()">
     </div>
   <div class="container">
      <div class="notice">
        <h1>${msg.messageTitle }</h1>
        <p>
        	${msg.messageContent }
        </p>
      </div>
       <div class="footer-info">基地：${msg.orgName}</div>
   </div>

 </div>
</div>
</div>
 
 <div class="footer_index">
 主管单位：${sysCfgMap['the_competent_unit']}   |  技术支持：南京品德网络信息技术有限公司   |  <a href="https://beian.miit.gov.cn/" target="_blank" style="color:#FFFFFF;">工信部备案号：苏ICP备14054231号-1</a>
 </div>

</body>
</html>
