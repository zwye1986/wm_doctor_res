<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function save(flag){
            var materialFlowLst = [];
            var content = "";
            var number = $(":checkbox[name='materialFlow']:checked").length;
            if(number == 0){
                jboxTip("耗材或固定资产不能为空！");
                return;
            }
            $(":checkbox[name='materialFlow']:checked").each(function(i){
                materialFlowLst.push(this.value);
                content += $(this).attr("materialName")+"&nbsp;<input type='text' name=\"${param.flag eq 'hc'?'suppliesNum':'assetsNum'}\" placeholder='填写数量' value='"+$(this).attr("useNum")+"' style='width:50px;border-top:0px;border-left:0px;border-right:0px;border-bottom:1px solid gray;'>" +
                        "<img style='cursor:pointer;' src=\"<s:url value='/jsp/inx/lcjn/images/reduce.png'/>\" onclick=\"delInfo(this,'${param.flag eq 'hc'?'suppliesTD':'assetsTD'}','"+this.value+"');\" title='删除' />";
                if(i != number-1){
                    content += "</td>";
                    if((i+1) % 3 == 0){
                        content +="</tr><tr>"
                    }
                    content += "<td style='border:0px;width:33%;'>";
                }
            });
            var html = "<table width=90%><tr><td style='border:0px;width:33%;'>"+content+"</td></tr></table><img style='cursor:pointer;float:right;margin:-26px 20px 0px 0px;' src=\"<s:url value='/jsp/inx/lcjn/images/add.png'/>\" onclick=\"addInfo('${param.flag}')\" title='添加'><input type='hidden' id=\"${param.flag eq 'hc'?'suppliesList':'assetsList'}\" name=\"${param.flag eq 'hc'?'suppliesList':'assetsList'}\" value='"+materialFlowLst+"'/>";
            <c:if test="${param.flag eq 'hc'}">
                window.parent.frames['jbox-iframe'].$('#suppliesTD').html(html);
            </c:if>
            <c:if test="${param.flag eq 'gdzc'}">
                window.parent.frames['jbox-iframe'].$('#assetsTD').html(html);
            </c:if>
            jboxCloseMessager();
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <table class="basic" style="width:100%;margin:10px 0px;">
            <c:if test="${param.flag eq 'hc'}">
                <c:forEach items="${suppliesList}" var="supp" varStatus="i">
                    <c:if test="${fn:contains(startSupplies,supp.dictId)}">
                        <c:set var="count" value="${i.index + 1}" />
                        <tr>
                            <td><input type="checkbox" name="materialFlow" materialName="${supp.dictName}"  useNum="${materialMap[supp.suppliesFlow]}" value="${supp.suppliesFlow}" ${fn:contains(exitMaterialLst,supp.suppliesFlow)?'checked':''}>&nbsp;${supp.dictName}</td>
                        </tr>
                    </c:if>
                </c:forEach>
                <c:if test="${empty count}"><tr><th style="text-align:center;">无记录！</th></tr></c:if>
            </c:if>
            <c:if test="${param.flag eq 'gdzc'}">
                <c:forEach items="${assetsList}" var="assets" varStatus="i">
                    <c:if test="${fn:contains(startAssets,assets.dictId)}">
                        <c:set var="count" value="${i.index + 1}" />
                        <tr>
                            <td><input type="checkbox" name="materialFlow" materialName="${assets.dictName}" useNum="${materialMap[assets.fixedFlow]}" value="${assets.fixedFlow}" ${fn:contains(exitMaterialLst,assets.fixedFlow)?'checked':''}>&nbsp;${assets.dictName}</td>
                        </tr>
                    </c:if>
                </c:forEach>
                <c:if test="${empty count}"><tr><th style="text-align:center;">无记录！</th></tr></c:if>
            </c:if>
        </table>
        <div style="text-align: center;margin-top:20px;">
            <input type="button" class="search" onclick="save('${param.flag}');" value="确&#12288;认"/>
            <input type="button" class="search" onclick="jboxCloseMessager();" value="取&#12288;消"/>
        </div>
    </div>
</div>
</body>
</html>