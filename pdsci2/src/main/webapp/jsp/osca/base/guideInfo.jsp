<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
    </jsp:include>
        <script type="text/javascript">
            function toPage(page){
                $("#currentPage").val(page);
                $("#myForm").submit();
            }
            function appointOpt(clinicalFlow,subjectFlow,clinicalName,speName){
                $("#clinicalFlow").val(clinicalFlow);
                $("#subjectFlow").val(subjectFlow);
                $("#clinicalName").val(clinicalName);
                $("#speName").val(speName);
                $("#myForm2").submit();
            }
            function gokhxxgl(){
                top.$("#osca-gly-khxxgl a").click();
                window.location.href="<s:url value='/osca/base/checkInfoList?isLocal=Y'/>";
            }
        </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div style="width:960px;font:18px bold;margin:auto;${count eq 0?'':'display:none'}">
            <div style="margin:20px 0px 0px 40px;padding-top:50px;font-size:22px;font-weight:500;">欢迎您，登录临床技能考核管理系统！</div>
            <div style="margin:20px 0px 0px 60px;">亲，您暂未发布任何考核信息！</div>
            <div style="margin:20px 0px 100px 60px;">您可以去<a onclick="gokhxxgl();" style="cursor:pointer;color:blue;text-decoration:none;"><c:out value="考核信息管理"/></a>页面进行考核预约信息发布</div>
            <div style="margin-left:50px;"><img src="<s:url value='/jsp/osca/images/yykhlc.png'/>"></div>
        </div>
        <div style="width:960px;margin:auto;${(count gt 0 && fn:length(dataList) gt 0)?'':'display:none'}">
            <form id="myForm" action="<s:url value="/osca/base/guideInfo"/>" method="post">
                <input type="hidden" name="currentPage" value="1">
            </form>
            <form id="myForm2" action="<s:url value='/osca/base/checkInfoManage'/>" method="post">
                <input type="hidden" id="clinicalFlow" name="clinicalFlow">
                <input type="hidden" id="subjectFlow" name="subjectFlow">
                <input type="hidden" id="clinicalName" name="clinicalName">
                <input type="hidden" id="speName" name="speName">
                <input type="hidden" name="initFlag" value="Y">
            </form>
            <div style="margin-top:40px;font-size:22px;font-weight:500;">欢迎您，登录临床技能考核管理系统！</div>
            <div style="margin:30px 0px 0px 30px;">
                <table style="width:100%;">
                    <tr>
                        <td style="background-color:#4195C5;padding:6px 0px 6px 30px;"><span style="font:24px solid;color:white;">待办事项</span></td>
                    </tr>
                    <tr>
                        <td style="padding-left:18px;background-color:#F5F5F5;border:1px solid lightgrey;"><img src="<s:url value='/jsp/osca/images/ksyyxx.png'/>"></td>
                    </tr>
                    <c:forEach items="${dataList}" var="info">
                        <tr>
                            <td style="background-color:#fff;border:1px solid lightgrey;padding:8px 0px 8px 40px;">
                                <span style="line-height:30px;">
                                ${info.CLINICAL_NAME}（预约时间${fn:replace(fn:substring(info.APPOINT_START_TIME,0,10),"-",".")}~${fn:replace(fn:substring(info.APPOINT_END_TIME,0,10),'-','.')}）<br/>
                                <a onclick="appointOpt('${info.CLINICAL_FLOW}','${info.SUBJECT_FLOW}','${info.CLINICAL_NAME}','${info.SPE_NAME}')" style="cursor:pointer;color:blue;"><c:out value="预约待审核"/></a>&nbsp;<span style="color:red;">${info.DSHNUM}</span>
                                &#12288;预约成功&nbsp;<span style="color:red;">${info.CGNUM}</span>&#12288;预约人员容量&nbsp;<span style="color:red;">${info.APPOINT_NUM}</span>
                                </span>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <div style="margin:60px 0px 0px 280px;">
                <c:set var="pageView" value="${pdfn:getPageView(dataList)}" scope="request"/>
                <pd:pagination toPage="toPage"/>
            </div>
            <div style="margin:50px 0px 0px 60px;"><img src="<s:url value='/jsp/osca/images/yykhlc.png'/>"></div>
        </div>
    </div>
</div>
</body>
</html>