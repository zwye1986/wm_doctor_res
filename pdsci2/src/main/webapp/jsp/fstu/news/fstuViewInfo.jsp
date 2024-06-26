
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
        function passed(infoStatusId){
            var infoFlow = $("#infoFlow").val();
            var stringData = "flows="+infoFlow+"&infoStatusId="+infoStatusId;
            var url = "<s:url value='/fstu/newsInfoManage/updateStatus'/>";
            jboxPost(url , stringData , function(resp){
                if(resp=='${GlobalConstant.OPRE_SUCCESSED}'){
                    window.parent.frames['mainIframe'].window.search();
                    jboxCloseMessager();
                }
            } , null , true);
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="sysOrgForm">
                <table class="basic" width="100%" style="margin: 0 auto;">
                    <tr>
                        <td class="bs_name" colspan="4">资讯信息：</td>
                    </tr>
                    <tr>
                        <th class="td_blue" width="100px">标&#12288;&#12288;题：</th>
                        <td colspan="3">
                            ${info.infoTitle }
                        </td>
                    </tr>
                    <tr>
                        <th class="td_blue">所属栏目：</th>
                        <td colspan="3" id="column">
                            <c:if test="${info.column != null }"> ${info.column.columnName } </c:if>
                        </td>
                    </tr>
                    <tr>
                        <th width="13%">标题图片：</th>
                        <td width="37%"><c:if test="${info.titleImg!=null&&info.titleImg!=''}" ><a href="${imageBaseUrl}${info.titleImg}" target="_blank" title="点击查看大图"><img src="${imageBaseUrl}${info.titleImg}" width="150px"/></a></c:if></td>
                        <th width="13%">资讯时间：</th>
                        <td width="37%">
                            ${info.infoTime}
                        </td>
                    </tr>
                    <tr>
                        <th>关键字：</th>
                        <td>
                            ${info.infoKeyword}
                        </td>

                        <th>是否置顶：</th>
                        <td>
                            <c:if test="${info.isTop==GlobalConstant.RECORD_STATUS_Y }" >是</c:if><c:if test="${info.isTop==GlobalConstant.RECORD_STATUS_N }" >否</c:if>
                        </td>
                    </tr>
                    <tr>
                        <th class="td_blue">资讯内容：</th>
                        <td colspan="3">
                            ${info.infoContent}
                        </td>
                    </tr>
                </table>
                <p align="center" style="width:100%">
                    <c:if test="${flag=='pass'}"><input class="search" type="button" value="审核通过"  onclick="passed('${fstuInfoStatusEnumPassed.id}');" /></c:if>
                    <c:if test="${flag=='pass'}"><input class="search" type="button" value="审核不通过"  onclick="passed('${fstuInfoStatusEnumNoPassed.id}');" /></c:if>
                    <input class="search" type="button" value="关&#12288;闭"  onclick="jboxCloseMessager();" />
                    <input id="infoFlow" type="hidden"  value="${info.infoFlow}">
                </p>
            </form>
        </div>

    </div>
</div>
</body>
</html>