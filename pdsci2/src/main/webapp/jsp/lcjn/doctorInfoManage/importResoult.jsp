<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true" />
        <jsp:param name="jbox" value="true" />
        <jsp:param name="jquery_form" value="false" />
        <jsp:param name="jquery_ui_tooltip" value="true" />
        <jsp:param name="jquery_ui_combobox" value="false" />
        <jsp:param name="jquery_ui_sortable" value="false" />
        <jsp:param name="jquery_cxselect" value="true" />
        <jsp:param name="jquery_scrollTo" value="false" />
        <jsp:param name="jquery_jcallout" value="false" />
        <jsp:param name="jquery_validation" value="true" />
        <jsp:param name="jquery_datePicker" value="true" />
        <jsp:param name="jquery_fullcalendar" value="true" />
        <jsp:param name="jquery_fngantt" value="false" />
        <jsp:param name="jquery_fixedtableheader" value="true" />
        <jsp:param name="jquery_placeholder" value="true" />
        <jsp:param name="jquery_iealert" value="false" />
        <jsp:param name="ueditor" value="true"/>
        <jsp:param name="jquery_qrcode" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function closeThis(){
            window.parent.frames['mainIframe'].window.toPage1(1);
            jboxClose();
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="myForm">
            <div style="margin-top:25px;height: 315px;overflow: auto">
                ${fn:length(successList)}条学员信息导入成功！
                <c:if test="${not empty failList}">
                    <div>以下${fn:length(failList)}条学员信息因成功预约培训课程的培训时间与本次课程培训时间有重叠导入失败。<br/>
                        <c:forEach items="${failList}" var="str">
                            <font style="color: red">${str}</font><br/>
                        </c:forEach>
                    </div>
                </c:if>
                <c:if test="${not empty failNumList}">
                    <div>预约人数已满!以下${fn:length(failNumList)}条学员信息导入失败。<br/>
                        <c:forEach items="${failNumList}" var="str">
                            <font style="color: red">${str}</font><br/>
                        </c:forEach>
                    </div>
                </c:if>
            </div>
        </form>
        <div style="text-align: center;margin-bottom:10px;">
            <input class="search" onclick="closeThis()" value="确&#12288;定" type="button">
        </div>
    </div>
</div>
</body>
</html>
