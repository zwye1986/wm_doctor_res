<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true" />
        <jsp:param name="jbox" value="true" />
        <jsp:param name="jquery_validation" value="true" />
        <jsp:param name="jquery_datePicker" value="true" />
    </jsp:include>
    <style type="text/css">
        .xllist td{
            text-align: left;height: 35px;
        }
    </style>
    <script type="text/javascript">
        $(document).ready(function(){

        });

        function save() {
            if(false==$("#editForm").validationEngine("validate")){
                return false;
            }
            var url = "<s:url value='/res/discipleAdminAudit/updateDiscipleReq'/>";
            var data = $('#editForm').serialize();
            jboxStartLoading();
            jboxPost(url, data, function(resp) {
                if(resp == 'cannotInsert'){
                    jboxTip("该配置项要求数已存在请检查！");
                    jboxEndLoading();
                    return false;
                }
                jboxTip("操作成功！");
                window.parent.frames['mainIframe'].toPage(1);
                jboxClose();
            }, null, false);
        }

    </script>
</head>
<body>
<div class="infoAudit">
    <form id="editForm" style="position: relative;padding-top: 20px;" method="post">
        <div id="infoDiv" class="div_table" style="padding-top: 5px;">
            <input type="hidden" name="recordFlow" value="${resDiscipleReq.recordFlow }"/>
            <table class="xllist">
                <tbody>
                <tr>
                    <th>培训专业：</th>
                    <td>&nbsp;
                        <select name="trainingTypeId" class="validate[required] qselect"  >
                            <option value="">--请选择--</option>
                            <c:forEach items="${recDocCategoryEnumList}" var="category">
                                <c:set var="res_doctor_category_key" value="res_doctor_category_${category.id }"/>
                                <c:if test="${sysCfgMap[res_doctor_category_key]==GlobalConstant.FLAG_Y }">
                                    <option value="${category.id}" ${resDiscipleReq.trainingTypeId eq category.id?'selected':''}>${category.name}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </td>
                    <th>年&#12288;&#12288;级：</th>
                    <td>&nbsp;
                        <input type="text" onclick="WdatePicker({dateFmt:'yyyy'})" id="sessionNumber" name="sessionNumber" value="${resDiscipleReq.sessionNumber }" class="validate[required] qtext" readonly="readonly"/>
                    </td>
                </tr>
                <tr>
                    <th>记录类型：</th>
                    <td>&nbsp;
                        <select name="discipleTypeId" class="validate[required] qselect"  >
                            <option value="">--请选择--</option>
                            <c:forEach items="${noteTypeEnumList}" var="noteType">
                                <option value="${noteType.id}" ${resDiscipleReq.discipleTypeId eq noteType.id?'selected':''}>${noteType.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <th>
                        年要求数：
                    </th>
                    <td>&nbsp;
                        <input type="text" name="reqNumber" value="${resDiscipleReq.reqNumber }" class="validate[required ,custom[integer]] qtext"/>
                    </td>
                </tr>
                </tbody>
            </table>
            <div class="button">
                <input type="button" class="search" onclick="save();" value="保&#12288;存"/>&#12288;
                <input type="button" class="search" onclick="jboxClose();" value="关&#12288;闭"/>
            </div>
        </div>
    </form>
</div>

</body>
</html>