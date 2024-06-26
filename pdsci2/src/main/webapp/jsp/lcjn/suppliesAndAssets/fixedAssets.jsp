<%@ taglib prefix="pdfn" uri="http://www.njpdkj.com/pdfn" %>
<%@include file="/jsp/common/doctype.jsp"%>
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
    <script>
        function toPage(page){
            $("#currentPage").val(page);
            jboxPostLoad("contentDiv","<s:url value="/lcjn/lcjnSuppliesAssets/fixedAssetsList"/>",$("#appointForm").serialize(),true);
        }
        function editAsset(fixedFlow){
            var width=(window.screen.width)*0.3;
            var height=(window.screen.height)*0.3;
            var currentPage=$("input[name='currentPage']").val();
            jboxStartLoading();
            jboxOpen("<s:url value='/lcjn/lcjnSuppliesAssets/showEditAssets'/>?fixedFlow="+fixedFlow+"&currentPage="+currentPage, "编辑固定资产信息", width, height);
        }
        function deleteAsset(fixedFlow){
            jboxConfirm("是否确认删除？",function () {
                var url="<s:url value='/lcjn/lcjnSuppliesAssets/deleteAssets?fixedFlow='/>" + fixedFlow;
                jboxPost(url,null,function(resp){
                    jboxTip(resp);
                    window.parent.frames['mainIframe'].window.toPage(1);
                    jboxClose();
                });
            });
        }
        function importAssets(){
            var url = "<s:url value='/lcjn/lcjnSuppliesAssets/showImportAssets'/>";
            typeName="导入资产信息";
            jboxOpen(url, typeName, 380, 180);
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content" id="contentDiv">
        <form id="appointForm" action="<s:url value="/lcjn/lcjnSuppliesAssets/fixedAssetsList"/>" method="post">
            <div class="choseDivNewStyle">
            <input id="currentPage" type="hidden" name="currentPage" value="${currentPage}"/>
            <table class="basic" style="width:100%;border:0px;margin:10px 0px;">
                <tr>
                    <td style="border:0px;">
                        <label style="margin-left: -10px;">资产名称：</label>
                        <input type="text" name="dictName" value="${param.dictName}">
                        <span style="padding-left:10px;"></span>资产状态：
                        <select name="statusId" style="width:137px;" class="select">
                            <option value="">全部</option>
                            <c:forEach items="${lcjnFixedAssetsStatusEnumList}" var="status">
                                <option value="${status.id}" ${param.statusId eq status.id ?'selected':''}>${status.name}</option>
                            </c:forEach>
                        </select>
                        <span style="padding-left:20px;"></span>
                        <input type="button" class="search" value="查&#12288;询" onclick="toPage(1)"/>
                        <input type="button" class="search" value="新&#12288;增" onclick="editAsset('')" />
                        <input type="button" class="search" value="导&#12288;入" onclick="importAssets()"/>
                    </td>
                </tr>
            </table>
            </div>
        </form>
        <table class="xllist" style="width:100%;">
            <tr style="background-color:#F5F5F5;">
                <td>资产名称</td>
                <td>单价（元）</td>
                <td>资产编号</td>
                <td>资产状态</td>
                <td>操作</td>
            </tr>
            <c:if test="${not empty fixedAssetsList}">
                <c:forEach items="${fixedAssetsList}" var="info">
                    <tr>
                        <td>${info.DICT_NAME}</td>
                        <td>${info.FIXED_PRICE}</td>
                        <td>${info.FIXED_CODE}</td>
                        <td>${info.STATUS_NAME}</td>
                        <td>
                            <c:if test="${info.record eq 'N'}">
                                <a style="color:#b9c0c6;">编辑</a>&#12288;
                            </c:if>
                            <c:if test="${info.record eq 'Y'}">
                                <a onclick="editAsset('${info.FIXED_FLOW}')" style="cursor:pointer;color:#4195c5;">编辑</a>&#12288;
                            </c:if>
                            <a onclick="deleteAsset('${info.FIXED_FLOW}')" style="cursor:pointer;color:#4195c5;">删除</a>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
            <c:if test="${empty fixedAssetsList}">
                <tr><td colspan="99">暂无记录</td></tr>
            </c:if>
        </table>
        <div style="margin-top:100px;">
            <c:set var="pageView" value="${pdfn:getPageView(fixedAssetsList)}" scope="request"/>
            <pd:pagination toPage="toPage"/>
        </div>
    </div>
</div>

</body>
</html>
