<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        $(function () {
            var edit = $('#edit').val();
            if (edit=='edit'){
                var subjectAddFalg = $("#userLevelId").find("option:selected").val();
                var select;
                var info = $('#info').val();
                for(var i=0; i<select.options.length; i++){
                    if(select.options[i].value == info){
                        select.options[i].selected = true;
                        break;
                    }
                }
            }
            $(".ui-dialog-close").attr("padding","0px 0px");
        })
        function save() {
            jboxStartLoading();
            if (false == $("#editForm").validationEngine("validate")) {
                jboxEndLoading();
                return false;
            }
            var speName = $("#speId").find("option:selected").text();

            if(speName =="请选择"){
                jboxTip("请选择专业！");
                jboxEndLoading();
                return false;
            }

            var url = "<s:url value='/jsres/supervisio/saveHospitalLeader'/>?&resTrainingSpeName=" + speName;
            var data = $('#editForm').serialize();

            jboxPost(url, data, function (resp) {
                if (resp == '${GlobalConstant.SAVE_SUCCESSED}') {
                    jboxTip("保存成功！初始密码为'Njpd@2022!!!'");
                    window.parent.toPage(1);
                    setTimeout(function () {
                        jboxClose();
                    }, 2000);
                } else if (resp == '${GlobalConstant.OPERATE_FAIL}') {
                    jboxTip("保存失败,手机号码已存在！");
                } else if (resp == '${GlobalConstant.UPDATE_SUCCESSED}') {
                    window.parent.signMain();
                    jboxTip(resp);
                    setTimeout(function () {
                        jboxClose();
                    }, 2000);
                } else if (resp == '${GlobalConstant.SAVE_FAIL}') {
                    jboxTip(resp);
                }
                jboxEndLoading();
            }, null, false);
        }

    </script>
</head>

<body>
<div class="infoAudit">
    <div class="div_table">
        <form id="editForm" style="position: relative;" method="post">
            <input type="hidden" name="userFlow" value="${user.userFlow }"/>
            <input type="hidden" id="edit" value="${type}"/>
            <input type="hidden" id="info" value="${user.resTrainingSpeId}"/>
            <div style="color: red;margin-bottom: 10px;">**初始密码 Njpd@2022!!!</div>
            <table border="0" cellpadding="0" cellspacing="0" class="base_info">
                <colgroup>
                    <col width="35%"/>
                    <col width="65%"/>
                </colgroup>
                <tbody>
                <tr>
                    <th><span style="color:red;">*&nbsp;</span>专家姓名：</th>
                    <td><input class="validate[required,maxSize[10]] input" name="userName" type="text"
                               value="${user.userName}" style="width: 300px"/></td>
                </tr>
                <tr>
                    <th><span style="color:red;">*&nbsp;</span>手机号码：</th>
                    <td><input class="validate[required,custom[mobile]] input" name="userPhone" type="text"
                               value="${user.userPhone}" style="width: 300px"/></td>
                </tr>
                <tr>
                    <th><span style="color:red;">*&nbsp;</span>专&#12288;&#12288;业：</th>
                    <td>
                        <select name="resTrainingSpeId" id="speId" class="select" style="width: 307px;margin-left: 4px">
                            <option value="">请选择</option>
                            <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                                <option value="${dict.dictId}" ${user.resTrainingSpeId eq dict.dictId?'selected':''}
                                        <c:if test="${'3700' eq dict.dictId}">style="display: none" </c:if>
                                        <c:if test="${'3500' eq dict.dictId}">style="display: none" </c:if>
                                >${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>

        <div class="button">
            <input type="button" class="btn_green" onclick="save();" value="保&#12288;存"/>
            <input type="button" class="btn_green" onclick="jboxClose();" value="关&#12288;闭"/>
        </div>
    </div>
</div>
</body>
</html>