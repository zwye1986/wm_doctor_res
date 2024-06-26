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
    <link rel="stylesheet" href="<s:url value='/jsp/eval/base/common/js/jquery.mobile-1.4.5.min.css'/>">
    <script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <style type="text/css">
    .selectTr td:first-child{
        background: url("<s:url value='/jsp/eval/base/common/js/images/gou.png'/>") no-repeat center;
        color: white;
    }

    </style>
    <script type="text/javascript">

        $(document).ready(function(){
            var obj= $("#cfgTable").find("tr[class='canClick']:eq(0)");
            var isFile=$(obj).attr("isFile");
            if(isFile=="U")
                 $(obj).click();
            else if(isFile=="Y")
            {
                jboxTip("请手动点击下载评估结果文件！");
            }
        });
        function getResultDetail(selfObj,cfgFlow,isFile) {
            var selLi = $(selfObj).parent();
            $(selfObj).siblings().removeClass("selectTr");
            $(selfObj).addClass("selectTr");
            if("Y"==isFile)
            {
                $("#content2").html("");
                var url="<s:url value='/eval/evalOrgQuery/checkHasFile'/>?orgFlow=${param.orgFlow}&evalYear=${param.evalYear}&cfgFlow="+cfgFlow;
                jboxPost(url,null,function(resp){
                    if(resp=="1")
                    {
                        $("#cfgFlow").val(cfgFlow);
                        jboxTip("评估结果文件下载中…………");
                        $("#searchForm").submit();
                    }else{
                        jboxTip(resp);
                    }
                },null,false);
            }else{
                $("#cfgFlow").val("");
                jboxPostLoad("content2" ,"<s:url value='/eval/evalOrgQuery/showOrgManageDetail'/>?evalYear=${param.evalYear}&cfgFlow="+cfgFlow+"&orgFlow=${param.orgFlow}" ,null , true);
            }
        }
    </script>
</head>
<body>
        <div style="width: 314px;float: left;overflow: auto;min-height:425px;max-height:425px; margin-left:0px;border-right: 1px solid #c5d0dc">
            <table id="cfgTable" class="basic" style="border:0px;">
                <c:forEach items="${cfgList}" var="c">
                    <tr <c:if test="${not empty resultMap[c.cfgFlow]}">
                            style="cursor:pointer;" onclick="getResultDetail(this,'${c.cfgFlow}','${c.isFile}')"
                            class="canClick" isFile="${c.isFile}"
                        </c:if>
                    >
                        <td style="border-left: 0px;border-right: 0px;width: 20px;"></td>
                        <td style="border-left: 0px;border-right: 0px;width: 190px;color: #1E8AB2;line-height: normal;">
                            ${c.cfgName}
                        </td>
                        <td style="border-left: 0px;border-right: 0px;width: 90px;">
                            <c:if test="${empty resultMap[c.cfgFlow]}">
                                状态：<span style="color: red">未填写</span>
                            </c:if>
                            <c:if test="${not empty resultMap[c.cfgFlow]}">
                                <span style="color: green">状态：已填写</span>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div id="content2" style="width: 748px;min-height:425px;max-height:425px;float: left;overflow: auto; margin-right:0px;border-left: 1px solid #c5d0dc">

        </div>
</body>
</html>