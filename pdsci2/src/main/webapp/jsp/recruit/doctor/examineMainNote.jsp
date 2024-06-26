<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="false"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_ui_combobox" value="false"/>
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

    <script type="text/javascript">
        function close111() {
            window.parent.frames['mainIframe'].window.search();
            jboxClose();
        }

    </script>

    <style>

    </style>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <div id="tagContent">
                <fieldset>
                    <legend>考试须知</legend>
                    <div>
                        <div class="xllist" style="margin-top: 30px">
                            ${recruitExamMain.mainNote}
                        </div>
                        <div class="button" style="margin: 15px">
                            <input type="button" class="search" onclick="close111();" value="关&#12288;闭"/>
                        </div>
                    </div>
                </fieldset>
            </div>
        </div>
    </div>
</div>
</body>
</html>