<%@include file="/jsp/common/doctype.jsp" %>
<html>
<title>${sysCfgMap['sys_title_name']}</title>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<script type="text/javascript">
    $(document).ready(function(){
        selTag("${param.tabTag}");
    });

    function selTag(tabTag){
        if("phyAssCertificate"==tabTag)
        {
            $("#phyAssCertificate").removeClass("tab");
            $("#phyAssCertificate").addClass("tab_select");
            $("#certificateSend").addClass("tab");
            $("#certificateSend").removeClass("tab_select");
            $("#sealManage").addClass("tab");
            $("#sealManage").removeClass("tab_select");
            jboxLoad("mainDiv","<s:url value='/jsres/phyAss/phyAssCertificate'/>?tabTag="+tabTag,true);
        }else if("certificateSend"==tabTag){
            $("#certificateSend").removeClass("tab");
            $("#certificateSend").addClass("tab_select");
            $("#phyAssCertificate").addClass("tab");
            $("#phyAssCertificate").removeClass("tab_select");
            $("#sealManage").addClass("tab");
            $("#sealManage").removeClass("tab_select");
            jboxLoad("mainDiv","<s:url value='/jsres/phyAss/phyAssCertificate'/>?tabTag="+tabTag,true);
        }else if("sealManage"==tabTag){
            $("#sealManage").removeClass("tab");
            $("#sealManage").addClass("tab_select");
            $("#phyAssCertificate").addClass("tab");
            $("#phyAssCertificate").removeClass("tab_select");
            $("#certificateSend").addClass("tab");
            $("#certificateSend").removeClass("tab_select");
            jboxLoad("mainDiv","<s:url value='/jsres/phyAss/sealManage'/>?tabTag="+tabTag,true);
        }
    }
</script>

<div class="main_hd">
    <h2 class="underline">师资证书发放</h2>
</div>
<div class="title_tab" style="margin-top: 0px;">
    <ul id="reducationTab">
        <li id="phyAssCertificate" class="tab" onclick="selTag('phyAssCertificate');"><a>证书生成</a></li>
        <li id="certificateSend" class="tab" onclick="selTag('certificateSend');"><a>证书发放</a></li>
        <li id="sealManage" class="tab" onclick="selTag('sealManage');"><a>印章管理</a></li>
    </ul>
</div>
</div>
<div id="mainDiv">
</div>
</html>