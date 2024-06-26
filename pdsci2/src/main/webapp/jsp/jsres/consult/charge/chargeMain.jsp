<%@ page import="com.pinde.sci.util.jsres.JsresUtil" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="consult" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_cxselect" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        $(document).ready(function(){
            $("li").click(function(){
                $(".tab_select").addClass("tab");
                $(".tab_select").removeClass("tab_select");
                $(this).removeClass("tab");
                $(this).addClass("tab_select");
            });
            if ("${param.liId}" != "") {
                $("#${param.liId}").addClass("tab_select");
            } else {
                $('li').first().addClass("tab_select");
            }
            $(".tab_select").click();
        });

        function enquireManage(){
            var url = "<s:url value='/jsres/consult/enquireManage'/>";
            jboxLoad("div_table_0", url, true);
        }

        function policyManage(){
            var url = "<s:url value='/jsres/consult/policyManage'/>";
            jboxLoad("div_table_0", url, true);
        }

    </script>
</head>
    <body>
    <div class="bd_bg" style="overflow: auto">
    <div class="head">
            <div class="head_inner">
                <h1 class="logo">
                    <a href="/jsres/manage/global">江苏省住院医师规范化培训管理平台</a>
                </h1>
            </div>
        </div>
        <div class="container" style="min-width: 1200px;max-width: 1200px;min-height: 700px;margin-left: auto;margin-right: auto;">
            <div style="margin: 32px">
                <h1>咨询专区</h1>
            </div>
            <div class="title_tab">
                <ul>
                    <li class="tab_select" onclick="enquireManage()"><a>问答专区</a></li>
                    <li class="tab" onclick="policyManage()"><a>政策专区</a></li>
                </ul>
            </div>
            <div class="main_bd" id="div_table_0" >
                <div id="ConsultContent">
                </div>
            </div>
        </div>

    </div>
    </body>
</html>

