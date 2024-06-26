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
            selectDictId();
        });
        function saveSupplies(){
            if(false==$("#appointForm").validationEngine("validate")){
                return false;
            }
            var suppliesFlow=$("input[name='suppliesFlow']").val();
            var dictId=$("select[name='dictId']").val();
            var suppliesPrice=$("input[name='suppliesPrice']").val();
            var stockNum=$("input[name='stockNum']").val();
            var addTime=$("input[name='addTime']").val();
            var page='${currentPage}';
            var url="<s:url value='/lcjn/lcjnSuppliesAssets/editSupplies?suppliesFlow='/>" + suppliesFlow+"&dictId="+dictId+"&suppliesPrice="+suppliesPrice+"&stockNum="+stockNum+"&addTime="+addTime;
            jboxPost(url,null,function(resp){
                jboxTip(resp);
                window.parent.frames['mainIframe'].window.toPage(page);
                jboxClose();
            });
        }
        function selectDictId(){
            var dictId=$("select[name='dictId']").val();
            jboxPost("<s:url value='/lcjn/lcjnSuppliesAssets/selectByDictId'/>?dictId="+dictId, null, function (resp) {
                $("#clinicalFlow").empty();
                if (null != resp && resp.length > 0) {
                    $("#priceTD").empty();
                    $("#priceTD").append("<lable>"+resp+"元</lable>");
                }else {
                    $("#priceTD").empty();
                    $("#priceTD").append("<input type='text' name='suppliesPrice' class='validate[required,custom[positiveNum]' value='' style='width: 147px;'>元");
                }
            },null,false);
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="appointForm"  method="post">
            <input type="hidden" name="suppliesFlow" value="${supplies.SUPPLIES_FLOW}"/>
            <table class="basic" style="width:100%;margin:10px 0px;">
                <tr>
                    <td style="text-align:right;">耗材名称：</td>
                    <td>
                        <select name="dictId" id="dictId" style="width:151px;" class="select" onchange="selectDictId()" <c:if test="${exmp eq 'edit'}">disabled="disabled"</c:if>>
                            <c:forEach items="${dictTypeEnumSkillMaterialList}" var="status">
                                <c:if test="${status.orgFlow eq orgFlow}">
                                    <option value="${status.dictId}" ${supplies.DICT_ID eq status.dictId ?'selected':''}>${status.dictName}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">单&#12288;&#12288;价：</td>
                    <td id="priceTD">
                        <c:if test="${not empty supplies.SUPPLIES_PRICE}">
                            <lable>${supplies.SUPPLIES_PRICE}元</lable>
                        </c:if>
                        <c:if test="${empty supplies.SUPPLIES_PRICE}">
                            <input type="text" name="suppliesPrice" class="validate[required,custom[positiveNum]" value="" style="width: 147px;">元
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">维护数量：</td>
                    <td>
                        <input type="text" class="validate[required,custom[integer]]" name="stockNum" value="" style="width: 147px;">
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">维护日期：</td>
                    <td>
                        <lable>${pdfn:getCurrDateTime('yyyy-MM-dd')}</lable>
                        <input name="addTime" type="hidden" value="${pdfn:getCurrDateTime('yyyy-MM-dd')}"/>
                    </td>
                </tr>
            </table>
            <div style="text-align: center;margin-top:20px;">
                <input type="button" class="search" onclick="saveSupplies();" value="保&#12288;存"/>
            </div>
        </form>
    </div>
</div>
</body>
</html>
