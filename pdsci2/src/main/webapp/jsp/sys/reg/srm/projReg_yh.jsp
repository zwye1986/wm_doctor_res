<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="false"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_ui_combobox" value="true"/>
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
        $(document).ready(function(){
            $("#orgFlow").scombobox({
                forbidInvalid : true,
                invalidAsValue : true,
                expandOnFocus : false
            });
            $(".scombobox-display").addClass("validate[required]");
            $(".scombobox-list").css("margin-top","10px");
            if(${not empty requestScope.eroMsg}){
                jboxTip("${requestScope.eroMsg}");
            }
        });

        function operate(op){
            $("#orgName").val($("#orgFlow option:selected").text());
            $("#userIdNo").val($("#userCode").val());
            if(op=="_prev"){
                $("#regForm").validationEngine("detach");
                var action = $("#regForm").attr('action');
                action+='?'+op;
                $("#regForm").attr('action' , action);
                jboxStartLoading();
                $('#regForm').submit();
            }else{
                if(false==$("#regForm").validationEngine("validate")){
                    return ;
                }

                if($("#orgFlow option:selected").val()=='请选择'){
                    jboxTip("请选择您所在的机构");
                    $(".scombobox-display").focus();
                    return false;
                }

                jboxConfirm("确认注册的机构是:"+$("#orgFlow option:selected").text()+"?" , function(){
                    $('#prev').attr({"disabled":"disabled"});
                    $('#finish').attr({"disabled":"disabled"});
                    jboxStartLoading();
                    var action = $("#regForm").attr('action');
                    action+='?'+op;
                    $("#regForm").attr('action' , action);
                    $('#regForm').submit();
                });
            }


        }
    </script>
    <style>
        body,html{
            overflow:hidden;
            position: relative;
        }
    </style>
</head>

<body>
<div id="register">
    <div id="logo"><img src="<s:url value='/css/skin/${skinPath}/images/tleft.png'/>" /></div>
</div>
<div class="mainright">
    <div id="content">
        <h1 class="reg_title">项目负责人信息注册</h1>
        <p class="line">如已拥有帐户信息，则<a href="<s:url value='/index.jsp'/>" class="line_c">在此登录</a></p>
        <%--<p style="color: red">${requestScope.eroMsg}</p>--%>
        <div class="quyu clearfix">
            <dl>
                <form id="regForm" name="regForm" action="<s:url value='/reg/srm/regist'/>" method="post">
                    <dt class="dt_l">用户名：</dt>
                    <dd class="dd_r clearfix"><input name="user.userCode" value="${form.user.userCode}" type="text" class="validate[required,custom[chinaIdLoose]] input_kuang address" id="userCode"/>
                        <span class="check_tips succ_tips" style="padding-left: 0px;"><b style="color: red;padding: 0px;margin: 0px;">*</b>请填写身份证作为用户名注册</span></dd>
                    <dt class="dt_l">姓名：</dt>
                    <dd class="dd_r clearfix"><input name="user.userName" value="${form.user.userName}" type="text" class="validate[required,custom[chinese]] input_kuang address" />
                        <span class="check_tips succ_tips" style="padding-left: 0px;"><b style="color: red;padding: 0px;margin: 0px;">*</b>姓名</span></dd>
                    <dt class="dt_l">性别：</dt>
                    <dd class="radio clearfix">
                        <input id="${userSexEnumMan.id }" type="radio" name="user.sexId"  value="${userSexEnumMan.id }" <c:if test="${userSexEnumMan.id == form.user.sexId}">checked</c:if> />
                        <label for="${userSexEnumMan.id }">${userSexEnumMan.name}</label>&#12288;&#12288;
                        <input id="${userSexEnumWoman.id }" type="radio"  name="user.sexId" value="${userSexEnumWoman.id }" <c:if test="${userSexEnumWoman.id == form.user.sexId}">checked</c:if> />
                        <label for="${userSexEnumWoman.id }">${userSexEnumWoman.name }</label>
                        <span class="check_tips"><b style="color: red;margin-left: 130px;">*</b></span></dd>
                    <%--<dt class="dt_l">身份证号：</dt>--%>
                    <%--<dd class="dd_r clearfix"><input name="user.idNo" value="${form.user.idNo}" type="text" class="validate[required,custom[chinaIdLoose]] input_kuang address" />--%>
                        <%--<span class="check_tips" style="padding-left: 0px;"><b style="color: red;padding: 0px;margin: 0px;">*</b>身份证号</span></dd>--%>
                    <input name="user.idNo" value="${form.user.idNo}" type="text" hidden="hidden" id="userIdNo"/>
                    <dt class="dt_l">手机号：</dt>
                    <dd class="dd_r clearfix"><input name="user.userPhone" value="${form.user.userPhone}" type="text" class="validate[required,custom[mobile]] input_kuang address" />
                        <span class="check_tips succ_tips" style="padding-left: 0px;"><b style="color: red;padding: 0px;margin: 0px;">*</b>手机号</span></dd>
                    <dt class="dt_l">Email：</dt>
                    <dd class="dd_r clearfix"><input name="user.userEmail" value="${form.user.userEmail}" type="text" class="validate[required,custom[email]] input_kuang address" />
                        <span class="check_tips" style="padding-left: 0px;"><b style="color: red;padding: 0px;margin: 0px;">*</b>Email</span></dd>
                    <dt class="dt_l">设置密码：</dt>
                    <dd class="dd_r clearfix"><input id="userPasswd" name="user.userPasswd" value="${form.user.userPasswd}" type="password" class="validate[required,custom[password]] input_kuang address" />
                        <span class="check_tips" style="padding-left: 0px;"><b style="color: red;padding: 0px;margin: 0px;">*</b>密码长度8~20位，数字、字母、字符至少包含两种</span></dd>
                    <dt class="dt_l">确认密码：</dt>
                    <dd class="dd_r clearfix"><input name="userPasswd2" type="password" value="${form.user.userPasswd}" class="validate[required,equals[userPasswd]] input_kuang address" data-errormessage-pattern-mismatch="密码设置不一致"/>
                        <span class="check_tips" style="padding-left: 0px;"><b style="color: red;padding: 0px;margin: 0px;">*</b>密码长度8~20位，数字、字母、字符至少包含两种</span></dd>
                    <dt class="dt_l">所在机构：</dt>
                    <dd class="dd_r clearfix">
                        <div style="width: 252px;">
                            <select class="validate[required] input_kuang address" name="user.orgFlow" id="orgFlow" >
                                <option value="请选择">请检索</option>
                                <c:forEach var="sysOrg" items="${applicationScope.sysOrgList}">
                                    <c:if test="${not (sysOrg.orgLevelId eq '0')}">
                                        <option value="${sysOrg.orgFlow}" <c:if test="${sysOrg.orgFlow==form.user.orgFlow}">selected</c:if>>${sysOrg.orgName}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                            <input type="hidden" name="user.orgName" id="orgName" />
                        </div>
                        <b style="color: red;margin-left: 10px; float: left">*<font color="red">支持按中文字检索</font></b>
                    </dd>
                    <dt class="dt_l">学历：</dt>
                    <dd class="dd_r clearfix">
                        <select name="user.educationId" class="validate[required] input_kuang address" style="80px">
                            <option value="">请选择</option>
                            <c:forEach items="${dictTypeEnumUserEducationList}" var="education">
                                <option value="${education.dictId}" <c:if test='${form.user.educationId==education.dictId}'>selected="selected"</c:if>>${education.dictName}</option>
                            </c:forEach>
                        </select>
                        <b style="color: red;margin-left: 10px;">*</b>
                    </dd>
                    <dt class="dt_l">学位：</dt>
                    <dd class="dd_r clearfix">
                        <select name="user.degreeId" class="validate[required] input_kuang address" style="80px">
                            <option value="">请选择</option>
                            <c:forEach items="${dictTypeEnumUserDegreeList}" var="degree">
                                <option value="${degree.dictId}" <c:if test='${form.user.degreeId==degree.dictId}'>selected="selected"</c:if>>${degree.dictName}</option>
                            </c:forEach>
                        </select>
                        <b style="color: red;margin-left: 10px;">*</b>
                    </dd>
                    <dt class="dt_l">职称：</dt>
                    <dd class="dd_r clearfix">
                        <select name="user.titleId" class="validate[required] input_kuang address" style="80px">
                            <option value="">请选择</option>
                            <c:forEach items="${dictTypeEnumUserTitleList}" var="title">
                                <option value="${title.dictId}" <c:if test='${form.user.titleId==title.dictId}'>selected="selected"</c:if>>${title.dictName}</option>
                            </c:forEach>
                        </select>
                        <b style="color: red;margin-left: 10px;">*</b>
                    </dd>
                    <dt class="dt_l">职务：</dt>
                    <dd class="dd_r clearfix">
                        <select name="user.postId" class="validate[required] input_kuang address">
                            <option value=''>请选择</option>
                            <c:forEach items="${dictTypeEnumUserPostList}" var='dict'>
                                <option value='${dict.dictId}' <c:if test='${form.user.postId eq dict.dictId}'>selected="selected"</c:if> >${dict.dictName}</option>
                            </c:forEach>
                        </select>
                        <b style="color: red;margin-left: 10px;">*</b>
                    </dd>
                    <dt class="dt_l">&nbsp;</dt>
                    <dd class="dd_r clearfix">
                        <div class="sub_login">
                            <input id="prev" class="no_bg" type="button" onclick="operate('_prev');" value="上一步" name="_prev"/>
                            <input id="finish" class="no_bg" type="button" onclick="operate('_finish');" value="完成" name="_finish" style="margin-left: 100px;"/>
                        </div>
                    </dd>
                </form>
            </dl>
        </div>
    </div>

    <div id="footer">技术支持：南京品德信息技术有限公司<br />Copyright © 2001- 2014 Character Technology, Inc. All rights reserved.</div>
</div>
</body>
</html>