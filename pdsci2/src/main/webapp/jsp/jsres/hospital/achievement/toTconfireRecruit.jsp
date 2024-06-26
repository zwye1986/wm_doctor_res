<%@include file="/jsp/common/doctype.jsp" %>
<jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
    <jsp:param name="font" value="true"/>
</jsp:include>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_ui_combobox" value="true"/>
        <jsp:param name="jquery_ui_sortable" value="false"/>
        <jsp:param name="jquery_cxselect" value="false"/>
        <jsp:param name="jquery_scrollTo" value="false"/>
        <jsp:param name="jquery_jcallout" value="false"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fullcalendar" value="false"/>
        <jsp:param name="jquery_fngantt" value="false"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
    </jsp:include>
    <script src="<s:url value='/js/jsres/screenshot/html2canvas.min.js'/>"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.0.272/jspdf.debug.js"></script>
    <script type="text/javascript"
            src="<s:url value='/js/j.suggest.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript"
            src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>

    <script type="text/javascript">
        function toConfireRecruitN() {
            jboxPost("<s:url value='/jsres/hospital/confireRecruit'/>",$("#saveForm").serialize(), function(resp){
                setTimeout(function(){
                    window.parent.toPage(1);
                    top.jboxClose();
                },300);
            } , null , true);
        }

    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="saveForm" style="text-align: center">
            <table width="100%" cellpadding="0" cellspacing="0" class="basic" style="border: none">
                <colgroup>
                    <col width="100%"/>
                </colgroup>
                <tr style="border: none;">
                    <input type="hidden" name="recruitFlow" value="${recruitFlow}">
                    <input type="hidden" name="doctorFlow" value="${doctorFlow}">
                    <input type="hidden" name="recruitFlag" value="${recruitFlag}">
                    <td style="border: none">
                        <textarea  name="recruitFlagReason" style="width: 350px;height: 150px;"  placeholder="请填写拒绝原因！"></textarea>
                    </td>
                </tr>
            </table>
            <div class="button" >
                <input type="button" class="btn_green" onclick="toConfireRecruitN();" value="确定"/>&nbsp;
                <input type="button" class="btn_green" onclick="jboxClose();" value="取消"/>&nbsp;
            </div>
        </form>
    </div>
</div>
</body>
</html>