<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function doSave() {
            if(false==$("#sysDictForm").validationEngine("validate")){
                return ;
            }
            var url = "<s:url value='/lcjn/base/save'/>";
            var data = $('#sysDictForm').serialize();
            jboxPost(url, data, function(resp) {
                if(resp=='${GlobalConstant.OPRE_FAIL_FLAG}'){
                    jboxTip("字典代码重复！");
                }else{
                    jboxTip('${GlobalConstant.SAVE_SUCCESSED}');
                    window.parent.frames['mainIframe'].window.search();
                    jboxClose();
                }
            },null,false);
        }
        function limitDictName(value){
            if(value == "CourseEvaluationItem" || value == "TeacherEvaluationItem"){//课程评价及老师评价
                $("#dictName").addClass("validate[required,maxSize[8]]");
            }else{
                $("#dictName").removeClass("validate[required,maxSize[8]]");
                $("#dictName").addClass("validate[required,maxSize[50]]");
            }
        }
    </script>
</head>
<body>
<form id="sysDictForm" style="height: 100px;" >
    <input name="dictFlow" value='${param.dictFlow}' type="hidden"/>
    <div class="content">
        <div class="title1 clearfix">
            <div id="tagContent">
                <div class="tagContent selectTag" id="tagContent0">
                    <table style="width: 400px" cellpadding="0" cellspacing="0" class="basic">
                        <%--<tr>--%>
                            <%--<th width="100px">字典类型：</th>--%>
                            <%--<td width="200px">--%>
                                <input name="dictTypeName" value="学籍.所属党支部" type="hidden"/>
                                <input name="dictTypeId" value="XjPartyBranch" type="hidden"/>
                                <%--<select class="validate[required] xlselect" name="dictTypeId" onchange="limitDictName(this.value)" readonly="readonly">--%>
                                    <%--<option value="">请选择</option>--%>
                                    <%--<c:forEach var="dictTypeEnum" items="${dictTypeEnumList}">--%>
                                        <%--<c:if test="${dictTypeEnum.wsid eq sessionScope.currWsId}">--%>
                                            <%--<option value="${dictTypeEnum.id}" <c:if test="${'XjPartyBranch' eq dictTypeEnum.id}">selected="selected"</c:if>>--%>
                                                <%--${dictTypeEnum.name}--%>

                                            <%--</option>--%>
                                        <%--</c:if>--%>
                                    <%--</c:forEach>--%>
                                <%--</select>--%>
                            <%--</td>--%>
                        <%--</tr>--%>
                        <tr>
                            <th width="150px">&#12288;党支部编码：</th>
                            <td width="200px"><input class="validate[required] xltext" name="dictId" type="text" value="${dict.dictId}" /></td>
                        </tr>
                        <tr>
                            <th>&#12288;党支部名称：</th>
                            <td><input class="xltext" id="dictName" name="dictName" type="text" value="${dict.dictName }" /></td>
                        </tr>
                        <tr>
                            <th>描述：</th>
                            <td><input class="xltext validate[maxSize[50]]" name="dictDesc" type="text" value="${dict.dictDesc }" /></td>
                        </tr>
                        <tr>
                            <th >排序码：</th>
                            <td style="text-align:left;" width="350px"><input class="validate[required,custom[integer]] xltext" name="ordinal" type="text" value="${dict.ordinal }" /></td>
                        </tr>
                    </table>
                    <div class="button" style="width: 100%">
                        <input class="search" type="button" value="保&#12288;存" onclick="doSave();" />
                        <input type="button" class="search" onclick="jboxClose();" value="关&#12288;闭">
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>