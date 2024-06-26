<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_fixedtableheader" value="true"/>
    <jsp:param name="jquery_placeholder" value="true"/>
</jsp:include>
<script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">

    function searchIn() {
        var url = "<s:url value='/jsres/hospital/signManage'/>";
        jboxPostLoad("doctorContent", url, $("#inForm").serialize(), true);
    }
</script>


<div class="main_bd">
    <div class="div_search">
        <form id="inForm">
            <input type="hidden" name="signFlow" id="${sign.signFlow}"/>
            <table class="searchTable">
                <tr>

                </tr>
            </table>
        </form>
    </div>
</div>

