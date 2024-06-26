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
        <jsp:param name="treetable" value="true"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
    </jsp:include>
    <%--<link rel="stylesheet" href="<s:url value='/js/treetable/jquery.treetable.css' />" />--%>
    <%--&lt;%&ndash;<script src="treetable/jquery.js"></script>&ndash;%&gt;--%>
    <%--<script src="<s:url value='/js/treetable/jquery-ui.js'/>"></script>--%>
    <%--<script src="<s:url value='/js/treetable/jquery.treetable.js' />"></script>--%>
    <script type="text/javascript">
        function change(obj) {
            if (obj.id == 'maxLimit') {
                var maxLimitValue = $(obj).text().trim();
                var inputMaxLimit = $("<input style='width: 90%'>");
                inputMaxLimit.attr("name", "maxLimit");
                inputMaxLimit.attr("value", maxLimitValue);
                /*inputMaxLimit.attr("maxlength","5");*/
                inputMaxLimit.attr("class", "validate[required,custom[number],min[0]]");
                inputMaxLimit.attr("onblur", "ckeckNum(this)");

                $(obj).empty();
                $(obj).append(inputMaxLimit);
            }
            if (obj.id == 'remark') {
                var remarkValue = $(obj).text().trim();
                var inputRemark = $("<input style='width: 90%'>");
                inputRemark.attr("name", "remark");
                inputRemark.attr("value", remarkValue);
                $(obj).empty();
                $(obj).append(inputRemark);
            }
        }
        function clicks(obj) {
            var tr = $(obj).parent("td").parent('tr');
            var tdMaxLimit = tr.find('td').eq(2);
            var tdRemark = tr.find('td').eq(3);
            if (obj.checked == true) {
                var inputMaxLimit = $("<input style='width: 90%'>");
                inputMaxLimit.attr("name", "maxLimit");
                inputMaxLimit.attr("class", "validate[custom[number],min[0],max[100]]");
                /*inputMaxLimit.attr("maxlength","5");*/
                inputMaxLimit.attr("onblur", "ckeckNum(this)");
                var inputRemark = $("<input style='width: 90%'>");
                inputRemark.attr("name", "remark");
                // alert($(tdRemark).text());
                $(inputRemark).val($(tdRemark).text().trim());
                tdRemark.empty();
                tdMaxLimit.empty();
                tdMaxLimit.append(inputMaxLimit);
                tdRemark.append(inputRemark);

                var pid = $(tr).attr("data-tt-parent-id");
                if (pid && pid != "0") {
                    var trs = $('#schemeDtl').find('tr');
                    $.each(trs, function (i, n) {
                        if ($(n).attr("data-tt-id") == pid) {
                            var parentCheckbox = $(n).find('td').eq(0).find("input[type='checkbox']");
                            if (!$(parentCheckbox).prop("checked")) {
                                $(n).find('td').eq(0).find("input[type='checkbox']").attr("checked", "checked");
                                var parentTdMaxLimit = $(n).find('td').eq(2);
                                var parentTdRemark = $(n).find('td').eq(3);

                                var input1 = $("<input style='width: 90%'>");
                                input1.attr("name", "maxLimit");
                                input1.attr("class", "validate[custom[number],min[0],max[100]]");
                                /*inputMaxLimit.attr("maxlength","5");*/
                                input1.attr("onblur", "ckeckNum(this)");
                                var input2 = $("<input style='width: 90%'>");
                                input2.attr("name", "remark");
                                $(input2).val($(parentTdRemark).text().trim());


                                parentTdMaxLimit.empty();
                                parentTdRemark.empty();
                                parentTdMaxLimit.append(input1);
                                parentTdRemark.append(input2);
                            }
                        }
                    });

                }else{
                    pid = $(tr).attr("data-tt-id");
                    checkAll(pid,"checked");
                }
            } else {
                tdMaxLimit.empty();
                tdRemark.empty();
                var pid = $(tr).attr("data-tt-parent-id");
                if(!pid){
                pid = $(tr).attr("data-tt-id");
                checkAll(pid,false);
                }
            }
        }

        function checkAll(pid,checked){
            var trs = $('#schemeDtl').find('tr');
            $.each(trs, function (i, n) {
                if ($(n).attr("data-tt-parent-id") == pid) {
                   // var parentCheckbox = $(n).find('td').eq(0).find("input[type='checkbox']");

                        $(n).find('td').eq(0).find("input[type='checkbox']").attr("checked", checked);

                        var parentTdMaxLimit = $(n).find('td').eq(2);
                        var parentTdRemark = $(n).find('td').eq(3);

                        var input1 = $("<input style='width: 90%'>");
                        input1.attr("name", "maxLimit");
                        input1.attr("class", "validate[custom[number],min[0],max[100]]");
                        /*inputMaxLimit.attr("maxlength","5");*/
                        input1.attr("onblur", "ckeckNum(this)");
                        var input2 = $("<input style='width: 90%'>");
                        input2.attr("name", "remark");
                        $(input2).val($(parentTdRemark).text().trim());


                        parentTdMaxLimit.empty();
                        parentTdRemark.empty();
                    if(checked == 'checked'){
                        parentTdMaxLimit.append(input1);
                        parentTdRemark.append(input2);
                    }
                }
            });
        }

        function ckeckNum(obj){
            // $(obj).validationEngine("validate");
            var ttPid = $(obj).parents("tr").attr("data-tt-parent-id");
            var trs = $('#schemeDtl tr');
            var tdMaxLimit = 0;
            var tdLimit = 0;
            var limit= parseFloat($(obj).val()) ? parseFloat($(obj).val()) : 0;
            $(obj).val(parseFloat(limit.toFixed(2)));
            if(ttPid){
                $.each(trs, function (i, n) {

                    if($(n).attr("data-tt-id")== ttPid ){
                        tdMaxLimit= parseFloat($(n).find("input[name='maxLimit']").val()) ? parseFloat($(n).find("input[name='maxLimit']").val()) : 0;
                    }
                    if($(n).attr("data-tt-parent-id") == ttPid){
                        tdLimit += parseFloat($(n).find("input[name='maxLimit']").val()) ? parseFloat($(n).find("input[name='maxLimit']").val()) : 0;
                    }

                });
            }
            if(tdLimit > tdMaxLimit){
                jboxTip("比例总和不允许超过上级预算比例!");
                $(obj).val(0);
            }
        }

        function save(schemeFlow) {
            if (!$("#budgetList").validationEngine("validate")) {
                return false;
            }
            var trs = $('#schemeDtl').find('tr');
            var datas = [];
            var count = 0;
            $.each(trs, function (i, n) {
                var checkbox = $(n).find('td').eq(0).find("input[type='checkbox']");
                if ($(n).find('td').eq(0).find("input[type='checkbox']").prop("checked")) {
                    var tdMaxLimitValue = checkbox.parent('td').parent('tr').find('td').eq(2).text();
                    var tdRemarkValue = checkbox.parent('td').parent('tr').find('td').eq(3).text();
                    var maxLimit = checkbox.parent('td').parent('tr').find("input[name='maxLimit']").val();
                    var remark = checkbox.parent('td').parent('tr').find("input[name='remark']").val();
                    var itemFlow = checkbox.parent('td').parent('tr').find("input[name='itemFlow']").val();
                    var itemPid = checkbox.parent('td').parent('tr').find("input[name='itemPid']").val();
                    var itemName = checkbox.parent('td').parent('tr').find("input[name='itemName']").val();
                    var itemId = checkbox.val();
                    if (maxLimit) {
                    } else {
                        maxLimit = tdMaxLimitValue;
                    }
                    if(!parseFloat(maxLimit)){
                        maxLimit = 0;
                    }
                    maxLimit = parseFloat(maxLimit);
                   // count += parseFloat(maxLimit);
                    if (remark) {
                    } else {
                        remark = tdRemarkValue;
                    }
                    var data = {
                        "maxLimit": maxLimit,
                        "remark": remark.trim(),
                        "itemFlow": itemFlow,
                        "itemId": itemId,
                        "schemeFlow": schemeFlow,
                        "itemPid": itemPid,
                        "itemName":itemName
                    };
//		alert(data);
                    datas.push(data);
                }
            });
//	alert(datas);
//            if (count <= 100) {
                var requestData = JSON.stringify(datas);
                var url = "<s:url value='/srm/fund/scheme/updateDetail'/>";
                jboxStartLoading();
                jboxPostJson(url, requestData, function () {
                    window.location.reload();
                    jboxClose();
                }, null, true);
            /*} else {
                /!*$("#zgbl").css("color","red");
                 $("#zgbl").text("总比例"+count+"%,超出"+(count-100)+"%");*!/
                if (count) {
                    jboxTip("总比例超过100%");
                    count = 0;
                } else {
                    jboxTip("数据不合法");
                }
            }*/
        }

        /*function ckeckNum(obj) {
            var maxLimit = parseFloat($(obj).val());
            if (maxLimit) {
                $(obj).val(parseFloat(maxLimit.toFixed(4)));
            }

        }*/

        $(function () {

            //$("#treeTable1").treetable({onNodeExpand:function(){alert(1);}});
            $("#treeTable1").treetable({expandable: true});
            $("#treeTable1").treetable("collapseAll");

        });


    </script>
    <style type="text/css">
        ul li {
            min-height: 25px;
        }
    </style>
</head>
<body>
<div class="mainright" style="height: 480px">
    <div class="content">
        <form id="budgetList" action="<s:url value="/srm/fund/scheme/list2"/>" method="post">
            <div>
                <table id="treeTable1" class="xllist" style="width: 100%">
                    <tr>
                        <th style="width: 10%">序号</th>
                        <th style="width: 30%">预算项</th>
                        <th style="width: 20%">最高比例(单位：%)</th>
                        <th>备注</th>
                    </tr>
                    <tbody id="schemeDtl">
                    <c:forEach var="dict" items="${dictTypeEnumBudgetItemList}">
                        <c:set var="dictKey" value="dictTypeEnumBudgetItem.${dict.dictId}List"/>
                        <c:set var="psdtl" value="${schemeDetailMap[dict.dictId]}"/>
                        <tr data-tt-id="${dict.dictId}">
                            <td><input type="checkbox"
                                       <c:if test='${not empty psdtl}'>checked="checked"</c:if> value="${dict.dictId}"
                                       onclick="clicks(this);">
                                <input type="hidden" name="itemName" value="${dict.dictName}">
                            </td>
                            <td>
                                    ${dict.dictName}
                                <input type="hidden" name="itemFlow"
                                       value="<c:if test="${not empty psdtl}">${psdtl.itemFlow}</c:if>">
                            </td>
                            <td id="maxLimit" <c:if test="${not empty psdtl}">ondblclick="change(this);"</c:if>>
                                <c:if test="${not empty psdtl}">
                                    ${psdtl.maxLimit}
                                </c:if>
                            </td>
                            <td id="remark" <c:if test="${not empty psdtl}">ondblclick="change(this);"</c:if>>
                                <c:if test="${not empty psdtl}">
                                    ${psdtl.remark}
                                </c:if>
                                <c:if test="${empty psdtl}">
                                    ${dict.dictDesc}
                                </c:if>
                            </td>
                        </tr>
                        <c:forEach items="${applicationScope[dictKey]}" var="sDict">
                            <c:set var="sdtl" value="${schemeDetailMap[dict.dictId.concat(sDict.dictId)]}"/>
                            <tr data-tt-id="${sDict.dictId}" data-tt-parent-id="${dict.dictId}">
                                <td>&#12288;<input type="checkbox"
                                                   <c:if test='${not empty sdtl}'>checked="checked"</c:if>
                                                   value="${sDict.dictId}" onclick="clicks(this);">
                                    <input type="hidden" name="itemPid" value="${dict.dictId}">
                                    <input type="hidden" name="itemName" value="${sDict.dictName}">
                                </td>
                                <td>
                                        ${sDict.dictName}
                                    <input type="hidden" name="itemFlow"
                                           value="<c:if test="${not empty sdtl}">${sdtl.itemFlow}</c:if>">
                                </td>
                                <td id="maxLimit" <c:if test="${not empty sdtl}">ondblclick="change(this);"</c:if>>
                                    <c:if test="${not empty sdtl}">
                                        ${sdtl.maxLimit}
                                    </c:if>
                                </td>
                                <td id="remark" <c:if test="${not empty sdtl}">ondblclick="change(this);"</c:if>>
                                    <c:if test="${not empty sdtl}">
                                        ${sdtl.remark}
                                    </c:if>
                                    <c:if test="${empty sdtl}">
                                        ${sDict.dictDesc}
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <%-- <table class="xllist" >
                 <tr style="height: 20px">
                     <th style="width: 10%">序号</th>
                     <th style="width: 30%">预算项</th>
                     <th id="zgbl" style="width: 20%">最高比例(单位：%)</th>
                     <th>备注</th>
                 </tr>
                 <tbody id="schemeDtl">
                 <c:forEach var="dict" items="${dictTypeEnumBudgetItemList}">
                     <c:set var="dictKey" value="dictTypeEnumStandardDept.${dict.dictId}List"/>
                         <c:set var="selectedSchemeDtl" value="false"/>
                         <c:set var="sdtl" value=""/>
                         <c:forEach var="schemeDtl" items="${schemeDtlList }">
                             <c:if test="${schemeDtl.itemId eq dict.dictId}">
                                 <c:set var="selectedSchemeDtl" value="true"/>
                                 <c:set var="sdtl" value="${schemeDtl}"/>
                             </c:if>
                         </c:forEach>
                         <tr>
                             <td>
                                 <input type="checkbox" name="check" value="${dict.dictId}" <c:if test='${selectedSchemeDtl}'>checked="checked"</c:if> onclick="clicks(this);"/>
                             </td>
                             <td>
                                 ${dict.dictName}
                                 <input type="hidden" name="itemFlow"  value="<c:if test="${not empty sdtl}">${sdtl.itemFlow}</c:if>">
                             </td>
                             <td id="maxLimit" ondblclick="change(this);">
                                 <c:if test="${not empty sdtl}">
                                     ${sdtl.maxLimit}
                                 </c:if>
                             </td>
                             <td id="remark" ondblclick="change(this);">
                                 <c:if test="${not empty sdtl}">
                                     ${sdtl.remark}
                                 </c:if>

                             </td>
                         </tr>
                     </c:forEach>
                 </tbody>
             </table>--%>
        </form>
    </div>
</div>
<div style="margin-top:10px; text-align: center;">
    <input type="button"  class="search" onclick="save('${schemeFlow}');" value="保&#12288;存"/>
    <input type="button" class="search" onclick="jboxCloseMessager()" value="关&#12288;闭"/>
</div>
</body>