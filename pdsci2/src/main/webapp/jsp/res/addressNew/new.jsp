
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
</head>
<body>
<div class="main_hd">
    <h2 class="underline">请假参数配置</h2>
</div>
<div class="mainright">
    <form id="orgAddressForm">
        <input name="orgFlow" hidden value="${sessionScope.currUser.orgFlow }">
        <c:forEach items="${recDocCategoryEnumList}" var="category">
            <c:set var="res_doctor_category_key" value="res_doctor_category_${category.id }"/>
            <c:if test="${sysCfgMap[res_doctor_category_key]==GlobalConstant.FLAG_Y }">
                <c:set var="key1" value="${category.id}All"></c:set>
                <c:set var="key2" value="${category.id}Greater"></c:set>
                <c:set var="key3" value="${category.id}Midd"></c:set>
                <c:set var="key4" value="${category.id}Less"></c:set>
                <c:set var="cfg1" value="${cfgMap[key1]}"></c:set>
                <c:set var="cfg2" value="${cfgMap[key2]}"></c:set>
                <c:set var="cfg3" value="${cfgMap[key3]}"></c:set>
                <c:set var="cfg4" value="${cfgMap[key4]}"></c:set>
                <fieldset style="margin-top: 20px;">
                    <legend>${category.name}考勤时间设置</legend>
                    <div style="height: 150px;width: 100%">
                        <table class="basic" width="100%" id="${category.id }">
                            <input name="orgFlow" hidden value="${sessionScope.currUser.orgFlow }">
                            <tr>
                                <td width="200px;">请假总天数：</td>
                                <td><input name="${category.id }allDays" type="text" class="input validate[required,custom[integer],min[1]]" value="${cfg1.allDays}"style="width: 80px;"></td>
                                <td width="200px;">请假阀值1：</td>
                                <td><input name="${category.id }intervalDays" type="text" class="input validate[required,custom[integer],min[1]]" value="${cfg1.intervalDays}"style="width: 80px;">天</td>
                                <td width="200px;">请假阀值2：</td>
                                <td><input name="${category.id }intervalDays2" type="text" class="input validate[required,custom[integer],min[1]]" value="${cfg1.intervalDays2}"style="width: 80px;">天</td>
                            </tr>
                        </table>
                    </div>
                </fieldset>
            </c:if>
        </c:forEach>
    </form>
</div>

</body>
</html>