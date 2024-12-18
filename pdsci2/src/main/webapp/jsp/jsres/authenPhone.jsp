<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head><title>江苏省住院医师规范化培训管理平台</title>
    <jsp:include page="/jsp/jszy/htmlhead-jszy.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="rsasecurity" value="true"/>
    </jsp:include>
    <link rel="stylesheet" type="text/css" href="<s:url value='/css/skin/LightBlue/basic.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
    <script type="text/javascript">
        var countdown = 60;

        function register() {
            $("#verifyCodeErr").html("");
            $(".userPhoneErr").text("");
            $("#errorMsg").text("");
            if ($("#userPhone").val() == "") {
                jboxTip("请填写手机号码");
                return;
            }
            if ($("#verifyCode").val() == "") {
                jboxTip("验证码不能为空");
                return;
            }
            if ($("#verifyCode").val().trim().length != 6) {
                jboxTip("验证码错误");
                return;
            }
            $("#verifyCodeErr").html("");
            <%--var url = "<s:url value='/inx/jsres/authenVerifyCode'/>";--%>
            var url = "<s:url value='/inx/jsres/modifyPhoneVerifyCode'/>";
            jboxPost(url, $("#registerForm").serialize(),
                function (resp) {
                    if (resp == "${GlobalConstant.VERIFT_CODE_RIGHT}") {
                        jboxTip("手机号码认证成功！");
                        $("#accounts").click();
                        setTimeout(function () {
                            top.jboxCloseMessager();
                        },2000);
                        window.parent.accounts();
                    } else {
                        jboxInfo(resp);
                    }
                },
                null, false);
        }

        function checkPhone(obj) {
            $("#verifyCodeErr").html("");
            $(".userPhoneErr").text("");
            $("#errorMsg").text("");
            if ($("#userPhone").val() == "") {
                jboxTip("请填写手机号码");
                return;
            }

            var userPhone = $("#userPhone").val();
            var yzm = $("#yzm").val();

            var data = {
                userPhone: userPhone,
            };
            var param = JSON.stringify(data);
            // 加密  公钥指数  ""  公钥系数
            if ("${pkExponent}" && "${pkModulus}") {
                var key = RSAUtils.getKeyPair("${pkExponent}", "", "${pkModulus}");
                data = RSAUtils.encryptedString(key, encodeURI(param));
            }
            var url = "<s:url value='/inx/jsres/authenPhone'/>";
            jboxPost(url, {data:data},
                function (resp) {
                    jboxTip(resp);
                    if (resp != "${GlobalConstant.USER_PHONE_REPEAT}") {
                        settime(obj)
                    }
                },
                null, false);
        }


        function settime(obj) {
            if (countdown == 0) {
                obj.removeAttribute("disabled");
                obj.value = "获取验证码";
                countdown = 60;
                return;
            } else {
                obj.setAttribute("disabled", true);
                obj.value = "重新发送(" + countdown + ")";
                countdown--;
            }
            setTimeout(function () {
                    settime(obj)
                }
                , 1000)
        }
        function clearUselessInfo() {
            $("#verifyCodeErr").html("");
            $(".userPhoneErr").text("");
            $("#errorMsg").text("");
        }
    </script>
</head>
<body>
<form  id="registerForm">
    <div class="content">
        <div class="title1 clearfix">
            <div id="tagContent">
                <div class="tagContent selectTag" id="tagContent0">
                    <table cellpadding="0" cellspacing="0" class="basic">
                        <tr>
                            <th width="150px;"><font color="red">*</font>手机号码：</th>
                            <td>
                                <input type="text" name="userPhone" id="userPhone"
                                       onkeydown="clearUselessInfo()"  class="validate[custom[mobile] required] xltext"  value="${sessionScope.currUser.userPhone }"/>
                                <span style="color:red" class="userPhoneErr"></span>
                            </td>
                        </tr>
                        <tr>
                            <th><font color="red">*</font>验证码：</th>
                            <td>
                                <input id="verifyCode" name="verifyCode" type="text" class="validate[required] xltext"/>
                                <input type="button" id="sendcode" onclick="checkPhone(this)" class="search"
                                        value="获取验证码">
                            </td>
                        </tr>
                    </table>
                    <div class="button">
                        <input type="button" value="确&nbsp;&nbsp;定" onclick="register();" id="registerButton" class="search"
                               style="margin-top: 0px;"/>
                        <span style="color:red" id="verifyCodeErr"></span>
                        <c:if test='${!empty errorMsg}'>
                            <span id="errorMsg" style="color:red;">${errorMsg}</span>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>