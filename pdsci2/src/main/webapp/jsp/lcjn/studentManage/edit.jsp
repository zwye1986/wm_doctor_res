<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_ui_combobox" value="false"/>
        <jsp:param name="jquery_ui_sortable" value="false"/>
        <jsp:param name="jquery_cxselect" value="false"/>
        <jsp:param name="jquery_scrollTo" value="false"/>
        <jsp:param name="jquery_jcallout" value="false"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fullcalendar" value="false"/>
        <jsp:param name="jquery_fngantt" value="false"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
    </jsp:include>
    <script type="text/javascript">
        function save(){
            if (!$("#myForm").validationEngine("validate")) {
                return;
            }
//            var reg1 = /^1[3|4|5|6|7|8|9][0-9]\d{8}$/;
//            if(!$("#userCode").val().match(reg1)){
//               jboxTip("用户名必须为手机号码");
//                return;
//            }
            var isIDCard1=/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/;
            var isIDCard2=/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
            if(!($("#idNo").val().match(isIDCard1)||$("#idNo").val().match(isIDCard2))){
                jboxTip("请输入正确身份证号码");
                return;
            }
            var reg3 = /^1[3|4|5|6|7|8|9][0-9]\d{8}$/;
            if(!$("#userPhone").val().match(reg3)){
                jboxTip("请输入正确手机号码");
                return;
            }
            var reg4 = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
            if(!$("#userEmail").val().match(reg4)){
                jboxTip("请输入正确邮箱地址");
                return;
            }
            var sexText = $("#sexId option:selected").text();
            $("#sexName").val(sexText);
            var titleText = $("#titleId option:selected").text();
            $("#titleName").val(titleText);
            var speText = $("#lcjnSpeId option:selected").text();
            $("#lcjnSpeName").val(speText);
            jboxPost("<s:url value='/lcjn/studentManage/save'/>", $("#myForm").serialize(), function (resp) {
                if(resp==-1){
                    jboxTip("该用户名已被占用");
                }
                if(resp==-2){
                    jboxTip("请先配置学员角色");
                }
                if(resp==1){
                    jboxTip("操作成功");
                    window.parent.frames['mainIframe'].location.reload();
                    jboxClose();
                }
            },null,false);
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="myForm">
            <input type="hidden" name="userFlow" value="${sysUser.userFlow}">
            <table class="basic" style="width: 100%;margin: 10px 0px;">
                <tr>
                    <td style="text-align:right;"><span style="color:red;">*&nbsp;</span>用户名：</td>
                    <td>
                        <input type="text" class="validate[required]" name="userCode" id="userCode" value="${sysUser.userCode}"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;"><span style="color:red;">*&nbsp;</span>姓名：</td>
                    <td>
                        <input type="text" class="validate[required]" name="userName" value="${sysUser.userName}"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;"><span style="color:red;">*&nbsp;</span>性别：</td>
                    <td>
                        <select id="sexId" name="sexId" style="width:156px;" class="select validate[required]">
                            <option value=""></option>
                            <option value="Man" ${sysUser.sexId eq 'Man'?'selected':''}>男</option>
                            <option value="Woman" ${sysUser.sexId eq 'Woman'?'selected':''}>女</option>
                        </select>
                        <input type="hidden" name="sexName" id="sexName" value="${sysUser.sexName}">
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;"><span style="color:red;">*&nbsp;</span>身份证号：</td>
                    <td>
                        <input type="text" class="validate[required]" name="idNo"id="idNo" value="${sysUser.idNo}"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;"><span style="color:red;">*&nbsp;</span>手机号码：</td>
                    <td>
                        <input type="text" class="validate[required]" name="userPhone" id="userPhone" value="${sysUser.userPhone}"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;"><span style="color:red;">*&nbsp;</span>邮箱：</td>
                    <td>
                        <input type="text" class="validate[required]" name="userEmail" id="userEmail" value="${sysUser.userEmail}"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;"><span style="color:red;">*&nbsp;</span>是否本院：</td>
                    <td>
                        <label><input type="radio" name="isOwnerStu" value="Y" class="validate[required]"
                                      <c:if test="${sysUser.isOwnerStu eq 'Y'}">checked="checked"</c:if>
                        >是</label>&#12288;
                        <label><input type="radio" name="isOwnerStu" value="N" class="validate[required]"
                                      <c:if test="${sysUser.isOwnerStu eq 'N'}">checked="checked"</c:if>
                        >否</label>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">所在单位：</td>
                    <td>
                        <input type="text" name="orgName" value="${sysUser.orgName}"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">科室：</td>
                    <td>
                        <input type="text" name="deptName" value="${sysUser.deptName}"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">职称：</td>
                    <td>
                        <select id="titleId" name="titleId" style="width:156px;" class="select">
                            <option/>
                            <c:forEach items="${dictTypeEnumLcjnUserTitleList}" var="dict">
                                <option value="${dict.dictId}" ${sysUser.titleId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                            </c:forEach>
                        </select>
                        <input type="hidden" name="titleName" id="titleName" value="${sysUser.titleName}">
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">培训专业：</td>
                    <td>
                        <select id="lcjnSpeId" name="lcjnSpeId" style="width:156px;" class="select">
                            <option/>
                            <c:forEach items="${dictTypeEnumLcjnSpeList}" var="dict">
                                <option value="${dict.dictId}" ${sysUser.lcjnSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                            </c:forEach>
                        </select>
                        <input type="hidden" name="lcjnSpeName" id="lcjnSpeName" value="${sysUser.titleName}">
                    </td>
                </tr>
            </table>
            <div style="text-align: center;margin-top:20px;">
                <input type="button" class="search" onclick="save();" value="保&#12288;存"/>
                <input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();"/>
            </div>
        </form>
    </div>
</div>
</body>
</html>