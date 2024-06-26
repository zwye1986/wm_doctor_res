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
        // selTag("Audit");
        selTag("${param.tabTag}");
    });
    function selTag(tabTag){
        if("CountryCertificate"==tabTag)
        {
            $("#CountryCertificate").removeClass("tab");
            $("#CountryCertificate").addClass("tab_select");
            $("#ProvinceCertificate").addClass("tab");
            $("#ProvinceCertificate").removeClass("tab_select");
            $("#CertificateSend").addClass("tab");
            $("#CertificateSend").removeClass("tab_select");
            $("#CertificateSearch").addClass("tab");
            $("#CertificateSearch").removeClass("tab_select");
            $("#SignManage").addClass("tab");
            $("#SignManage").removeClass("tab_select");
            $("#SealManage").addClass("tab");
            $("#SealManage").removeClass("tab_select");
            jboxLoad("mainDiv","<s:url value='/jsres/certificateManage/createMain'/>?roleFlag=${param.roleFlag}&tabTag="+tabTag+"&catSpeId=${param.catSpeId}",true);
        }else if("ProvinceCertificate"==tabTag){
            $("#ProvinceCertificate").removeClass("tab");
            $("#ProvinceCertificate").addClass("tab_select");
            $("#CountryCertificate").addClass("tab");
            $("#CountryCertificate").removeClass("tab_select");
            $("#CertificateSend").addClass("tab");
            $("#CertificateSend").removeClass("tab_select");
            $("#CertificateSearch").addClass("tab");
            $("#CertificateSearch").removeClass("tab_select");
            $("#SignManage").addClass("tab");
            $("#SignManage").removeClass("tab_select");
            $("#SealManage").addClass("tab");
            $("#SealManage").removeClass("tab_select");
            jboxLoad("mainDiv","<s:url value='/jsres/certificateManage/createMain'/>?roleFlag=${param.roleFlag}&tabTag="+tabTag+"&catSpeId=${param.catSpeId}",true);
        }else if("CertificateSend"==tabTag){
            $("#CertificateSend").removeClass("tab");
            $("#CertificateSend").addClass("tab_select");
            $("#CountryCertificate").addClass("tab");
            $("#CountryCertificate").removeClass("tab_select");
            $("#ProvinceCertificate").addClass("tab");
            $("#ProvinceCertificate").removeClass("tab_select");
            $("#CertificateSearch").addClass("tab");
            $("#CertificateSearch").removeClass("tab_select");
            $("#SignManage").addClass("tab");
            $("#SignManage").removeClass("tab_select");
            $("#SealManage").addClass("tab");
            $("#SealManage").removeClass("tab_select");
            jboxLoad("mainDiv","<s:url value='/jsres/certificateManage/main'/>?roleFlag=${param.roleFlag}&tabTag="+tabTag+"&catSpeId=${param.catSpeId}",true);
        }else if("CertificateSearch"==tabTag){
            $("#CertificateSearch").removeClass("tab");
            $("#CertificateSearch").addClass("tab_select");
            $("#CountryCertificate").addClass("tab");
            $("#CountryCertificate").removeClass("tab_select");
            $("#ProvinceCertificate").addClass("tab");
            $("#ProvinceCertificate").removeClass("tab_select");
            $("#CertificateSend").addClass("tab");
            $("#CertificateSend").removeClass("tab_select");
            $("#SignManage").addClass("tab");
            $("#SignManage").removeClass("tab_select");
            $("#SealManage").addClass("tab");
            $("#SealManage").removeClass("tab_select");
            jboxLoad("mainDiv","<s:url value='/jsres/certificateManage/main'/>?roleFlag=${param.roleFlag}&tabTag="+tabTag+"&catSpeId=${param.catSpeId}",true);
        }else if("SignManage"==tabTag){
            $("#SignManage").removeClass("tab");
            $("#SignManage").addClass("tab_select");
            $("#CountryCertificate").addClass("tab");
            $("#CountryCertificate").removeClass("tab_select");
            $("#ProvinceCertificate").addClass("tab");
            $("#ProvinceCertificate").removeClass("tab_select");
            $("#CertificateSend").addClass("tab");
            $("#CertificateSend").removeClass("tab_select");
            $("#CertificateSearch").addClass("tab");
            $("#CertificateSearch").removeClass("tab_select");
            $("#SealManage").addClass("tab");
            $("#SealManage").removeClass("tab_select");
            jboxLoad("mainDiv","<s:url value='/jsres/hospital/signSearch'/>?roleFlag=${param.roleFlag}&tabTag="+tabTag+"&catSpeId=${param.catSpeId}",true);
        }else if("SealManage"==tabTag){
            $("#SealManage").removeClass("tab");
            $("#SealManage").addClass("tab_select");
            $("#CountryCertificate").addClass("tab");
            $("#CountryCertificate").removeClass("tab_select");
            $("#ProvinceCertificate").addClass("tab");
            $("#ProvinceCertificate").removeClass("tab_select");
            $("#CertificateSend").addClass("tab");
            $("#CertificateSend").removeClass("tab_select");
            $("#CertificateSearch").addClass("tab");
            $("#CertificateSearch").removeClass("tab_select");
            $("#SignManage").addClass("tab");
            $("#SignManage").removeClass("tab_select");
            jboxLoad("mainDiv","<s:url value='/jsres/certificateManage/sealManage'/>?roleFlag=${param.roleFlag}&tabTag="+tabTag+"&catSpeId=${param.catSpeId}",true);
        }
    }
</script>

<div class="main_hd">
    <h2 class="underline">结业考核资格查询</h2>
</div>
<div class="title_tab" style="margin-top: 0px;">
    <ul id="reducationTab">
        <li id="CountryCertificate" class="tab" onclick="selTag('CountryCertificate');"><a>国家证书生成</a></li>
        <li id="ProvinceCertificate" class="tab" onclick="selTag('ProvinceCertificate');"><a>省级证书生成</a></li>
        <li id="CertificateSend" class="tab" onclick="selTag('CertificateSend');"><a>证书发放</a></li>
        <li id="CertificateSearch" class="tab" onclick="selTag('CertificateSearch');"><a>证书查询</a></li>
        <li id="SignManage" class="tab" onclick="selTag('SignManage');"><a>签名管理</a></li>
        <li id="SealManage" class="tab" onclick="selTag('SealManage');"><a>印章管理</a></li>
    </ul>
</div>
</div>
<div id="mainDiv">
</div>
</html>