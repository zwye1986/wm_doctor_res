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
            if (!$("#sysDictForm").validationEngine("validate")) {
                return;
            }
            var partyBranchNameText = $("select[name='sysUser.partyBranchId'] option:selected").text();
            $("input[name='sysUser.partyBranchName']").val(partyBranchNameText);
            var form = $("#sysDictForm").serialize();
            jboxPost("<s:url value='/xjgl/partyBranch/savePartyMember'/>", form, function (obj) {
                if (obj == "${GlobalConstant.SAVE_SUCCESSED}") {
                    window.parent.frames['mainIframe'].window.toPage(1);
                    jboxClose();
                }
            });
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
    <div class="content">
        <div class="title1 clearfix">
            <div id="tagContent">
                <div class="tagContent selectTag" id="tagContent0">
                    <table style="width: 400px" cellpadding="0" cellspacing="0" class="basic">
                        <input name="sysUser.userFlow" value="${data.userFlow}" type="hidden"/>
                        <tr>
                            <th width="150px">&#12288;政治面貌：</th>
                            <td width="200px">
                                <select style="width: 144px;" name="sysUser.politicsStatusId" class="notBlank inputText validate[required] politics">
                                    <option/>
                                    <c:forEach items="${politicsAppearanceEnumList}" var="politics">
                                        <option value="${politics.id}" ${data.sysUser.politicsStatusId eq politics.id?'selected':''}>${politics.name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th>&#12288;入党时间：</th>
                            <td>
                                <input type="text" style="width: 140px;text-align: left;" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="notBlank inputText ctime validate[required] needFlag" name="eduUserExtInfoForm.joinOrgTime" value="${data.modifyTime }">
                            </td>
                        </tr>
                        <tr>
                            <th>所属党支部：</th>
                            <td>
                                <select style="width: 144px;" name="sysUser.partyBranchId" class="inputText">
                                    <option/>
                                    <c:forEach items="${dictTypeEnumXjPartyBranchList }" var="dict">
                                        <option value="${dict.dictId }"
                                                <c:if test="${data.sysUser.partyBranchId eq dict.dictId }">selected</c:if>>${dict.dictName }</option>
                                    </c:forEach>
                                </select><input type="hidden" name="sysUser.partyBranchName" value="${data.sysUser.partyBranchName}">
                            </td>
                        </tr>
                        <tr>
                            <th >是否转入：</th>
                            <td style="text-align:left;" width="350px">
                                <select style="width: 144px;" id="isRelationInto" name="eduUserExtInfoForm.isRelationInto" class="notBlank inputText validate[required] needFlag">
                                    <option/>
                                    <option value="${GlobalConstant.FLAG_Y}" ${data.modifyUserFlow eq GlobalConstant.FLAG_Y?'selected':''}>
                                        是
                                    </option>
                                    <option value="${GlobalConstant.FLAG_N}" ${data.modifyUserFlow eq GlobalConstant.FLAG_N?'selected':''}>
                                        否
                                    </option>
                                </select>
                            </td>
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