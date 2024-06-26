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
        $(document).ready(function(){
            $(".showInfo").on("mouseover mouseout",
                    function(){
                        $(".theInfo",this).toggle();
                    }
            );
        });
        function toPage(page){
            $("#currentPage").val(page);
            jboxPostLoad("contentDiv","<s:url value="/lcjn/lcjnSuppliesAssets/suppliesList"/>",$("#appointForm").serialize(),true);
        }
        function editSupplies(suppliesFlow,exmp){
            var width=(window.screen.width)*0.3;
            var height=(window.screen.height)*0.3;
            var currentPage=$("input[name='currentPage']").val();
            jboxStartLoading();
            jboxOpen("<s:url value='/lcjn/lcjnSuppliesAssets/showEditSupplies'/>?suppliesFlow="+suppliesFlow+"&exmp="+exmp+"&currentPage="+currentPage, "添加耗材", width, height);
        }
        function deleteSupplies(suppliesFlow){
            jboxConfirm("是否确认删除？",function () {
                var url="<s:url value='/lcjn/lcjnSuppliesAssets/deleteSupplies?suppliesFlow='/>" + suppliesFlow;
                jboxPost(url,null,function(resp){
                    jboxTip(resp);
                    window.parent.frames['mainIframe'].window.toPage(1);
                    jboxClose();
                });
            });
        }
        function imp(){
            jboxOpen("<s:url value='/jsp/lcjn/suppliesAndAssets/importSupplies.jsp'/>", "导入耗材信息",380,180);
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content" id="contentDiv">
        <form id="appointForm" action="<s:url value="/lcjn/lcjnSuppliesAssets/suppliesList"/>" method="post">
            <div class="choseDivNewStyle">
            <input id="currentPage" type="hidden" name="currentPage" value="${currentPage}"/>
            <table class="basic" style="width:100%;border:0px;margin:10px 0px;">
                <tr>
                    <td style="border:0px;">
                        <label style="margin-left: -10px;">耗材名称：</label>
                        <input type="text" name="dictName" value="${param.dictName}">
                        <span style="padding-left:20px;"></span>
                        <input type="button" class="search" value="查&#12288;询" onclick="toPage(1)"/>
                        <input type="button" class="search" value="导&#12288;入" onclick="imp()"/>
                        <input type="button" class="search" value="添加耗材" onclick="editSupplies('','add')"/>
                    </td>
                </tr>
            </table>
            </div>
        </form>
        <table class="xllist" style="width:100%;">
            <tr style="background-color:#F5F5F5;">
                <td>耗材名称</td>
                <td>单价（元）</td>
                <td>维护总数量</td>
                <td>合计（元）</td>
                <td>操作</td>
            </tr>
            <c:if test="${not empty suppliesList}">
                <c:forEach items="${suppliesList}" var="info">
                    <tr>
                        <td>${info.DICT_NAME}</td>
                        <td>${info.SUPPLIES_PRICE}</td>
                        <td <c:if test='${not empty info.batchList}'>class="showInfo"</c:if> >
                            ${info.STOCK_NUM}
                            <div style="width: 0px;height: 0px;overflow: visible;">
                                <div style="max-height: 300px;overflow: auto;display: none;position:relative;margin-left:-90px;width: 300px;" class="theInfo">
                                    <table class="xllist" style="background: white;width: 280px;">
                                        <tr>
                                            <th align="center" style="width:100px;font-weight: 900;">日期</th>
                                            <th align="center" style="width:100px;font-weight: 900;">维护数量</th>
                                        </tr>
                                        <c:forEach items="${info.batchList}" var="batch">
                                            <tr>
                                                <td align="center">${batch.addTime}</td>
                                                <td align="center">${batch.stockNum}</td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </div>
                            </div>
                        </td>
                        <td>${info.SUPPLIES_PRICE * info.STOCK_NUM}</td>
                        <td>
                            <c:if test="${info.record eq 'N'}">
                                <a style="color:#b9c0c6;">新增</a>
                            </c:if>
                            <c:if test="${info.record eq 'Y'}">
                                <a onclick="editSupplies('${info.SUPPLIES_FLOW}','edit')" style="cursor:pointer;color:#4195c5;">新增</a>
                            </c:if>
                            <%--<a onclick="deleteSupplies('${info.SUPPLIES_FLOW}')" style="cursor:pointer;color:#4195c5;">删除</a>--%>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
            <c:if test="${empty suppliesList}">
                <tr><td colspan="99">暂无记录</td></tr>
            </c:if>
        </table>
        <div style="margin-top:100px;">
            <c:set var="pageView" value="${pdfn:getPageView(suppliesList)}" scope="request"/>
            <pd:pagination toPage="toPage"/>
        </div>
    </div>
</div>

</body>
</html>
