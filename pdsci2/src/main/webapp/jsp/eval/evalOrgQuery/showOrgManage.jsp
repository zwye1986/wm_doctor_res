<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <c:if test="${!param.head}">
        <jsp:include page="/jsp/common/htmlhead.jsp">
            <jsp:param name="basic" value="true"/>
            <jsp:param name="jbox" value="true"/>
            <jsp:param name="jquery_form" value="false"/>
            <jsp:param name="jquery_ui_tooltip" value="true"/>
            <jsp:param name="jquery_ui_combobox" value="false"/>
            <jsp:param name="jquery_ui_sortable" value="false"/>
            <jsp:param name="jquery_cxselect" value="true"/>
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
    </c:if>
    <script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript">

        $(document).ready(function(){
           $("#tags").find("li:eq(0)").find("a").click();
        });
        function selectTag(selfObj,cfgFlow,orgFlow,isFile) {
            var selLi = $(selfObj).parent();
            selLi.siblings("li").removeClass("selectTag");
            selLi.addClass("selectTag");
            if("U"==isFile)
            {
                $("#content1").css("overflow","auto");
            }else{
                $("#content1").css("overflow","hidden");
            }
            jboxPostLoad("content1" ,"<s:url value='/eval/evalOrgQuery/showOrgManageDetail'/>?evalYear=${param.evalYear}&cfgFlow="+cfgFlow+"&orgFlow="+orgFlow ,null , true);
        }
    </script>
</head>
<body>
    <div class="content">
        <div style="">
            <ul id="tags" style="margin-left: 0px;">
                <c:forEach items="${cfgList}" var="cfg">
                    <li>
                        <a onclick="selectTag(this,'${cfg.cfgFlow}','${param.orgFlow}','${cfg.isFile}')" href="javascript:void(0)">${cfg.cfgName}</a>
                    </li>
                </c:forEach>
            </ul>
            <div id="content1" style="width: 100%;max-height:430px;overflow: auto; border: 1px solid #c5d0dc">
            </div>
        </div>
    </div>
</body>
</html>