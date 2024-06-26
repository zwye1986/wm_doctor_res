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
        function saveFixed(){
            if(false==$("#appointForm").validationEngine("validate")){
                return false;
            }
            var fixedFlow=$("input[name='fixedFlow']").val();
            var dictId=$("select[name='dictId']").val();
            var fixedPrice=$("input[name='fixedPrice']").val();
            var fixedCode=$("input[name='fixedCode']").val();
            var statusId=$("select[name='statusId']").val();
            var page='${currentPage}';
            var url="<s:url value='/lcjn/lcjnSuppliesAssets/editAssets?fixedFlow='/>" + fixedFlow+"&dictId="+dictId+"&fixedPrice="+fixedPrice+"&fixedCode="+fixedCode+"&statusId="+statusId;
            jboxPost(url,null,function(resp){
                jboxTip(resp);
                window.parent.frames['mainIframe'].window.toPage(page);
                jboxClose();
            });
        }

    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="appointForm" action="<s:url value="/lcjn/lcjnDoctorTrainInfo/signList"/>" method="post">
            <input type="hidden" name="fixedFlow" value="${asset.fixedFlow}"/>
            <table class="basic" style="width:100%;margin:10px 0px;">
                <tr>
                    <td style="text-align:right;">资产名称：</td>
                    <td>
                        <select name="dictId" style="width:151px;" class="select">
                            <c:forEach items="${dictTypeEnumSkileFixedAssetsList}" var="status">
                                <c:if test="${status.orgFlow eq orgFlow}">
                                    <option value="${status.dictId}" ${asset.dictId eq status.dictId ?'selected':''}>${status.dictName}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">单&#12288;&#12288;价：</td>
                    <td>
                        <input type="text" name="fixedPrice" class="validate[required,custom[positiveNum]" value="${asset.fixedPrice}" style="width: 147px;">元
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">资产编号：</td>
                    <td>
                        <input type="text" name="fixedCode" value="${asset.fixedCode}" class="validate[required]" style="width: 147px;">
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">资产状态：</td>
                    <td>
                        <select name="statusId" style="width:151px;" class="select">
                            <c:forEach items="${lcjnFixedAssetsStatusEnumList}" var="status">
                                <option value="${status.id}" ${asset.statusId eq status.id ?'selected':''}>${status.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
            </table>
            <div style="text-align: center;margin-top:20px;">
                <input type="button" class="search" onclick="saveFixed();" value="保&#12288;存"/>
            </div>
        </form>
    </div>
</div>
</body>
</html>
