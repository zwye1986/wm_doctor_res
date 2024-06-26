<%@ page import="com.pinde.sci.util.jsres.JsresUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="register" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_fullcalendar" value="true"/>
        <jsp:param name="rsasecurity" value="true"/>
    </jsp:include>
    <link rel="stylesheet" type="text/css" href="<s:url value="/jsp/jsres/css/phone.css"/>"/>
    <script type="text/javascript">
        var tip = {
            "equals": "两次输入的密码不一致",
            "verifyCode": "验证码有误，请重新输入",
            "passwordEmpty": "密码不能为空"
        };

        // 初始化验证码
        $(document).ready(function(){
            getVerifyCode();
            $("#code").on('click',getVerifyCode);
        });

        var countdown = 60;

        function register() {
            $("#verifyCodeErr").html("");
            $(".userPhoneErr").text("");
            $("#errorMsg").text("");
            if(!$("#registerForm").validationEngine("validate")){
                return false;
            }
            if ($("#userPhone").val() == "") {
                jboxTip("请填写手机号码");
                return;
            }
            if (!$("#yzm").val()){
                jboxTip("请填写图形验证码");
                return ;
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

            var data = {
                userPhone: $("#userPhone").val(),
                verifyCode: $("#verifyCode").val()
            };
            //11
            var param = JSON.stringify(data);
            // 加密  公钥指数  ""  公钥系数
            if ("${pkExponent}" && "${pkModulus}") {
                var key = RSAUtils.getKeyPair("${pkExponent}", "", "${pkModulus}");
                data = RSAUtils.encryptedString(key, encodeURI(param));
            }
            var url = "<s:url value='/inx/jsres/checkVerifyCodeNew'/>";
            debugger;
            jboxPost(url, {
                    data:data
                },
                function (resp) {
                    if (resp == "") {
                        var userPhone = $("#userPhone").val();
                        var verifyCode = $("#verifyCode").val();
                        var url = "<s:url value='/inx/jsres/setForgetPasswd'/>";
                        document.write("<form action='"+ url +"' method=post id='passwdForm' name='passwdForm' style='display:none'>");
                        document.write("<input type='hidden' name='userPhone' value='"+ userPhone +"' />");
                        document.write("<input type='hidden' name='verifyCode' value='"+ verifyCode +"' />");
                        document.write("</form>");
                        document.passwdForm.submit();
                        <%--window.location.href = "<s:url value='/inx/jsres/setForgetPasswd'/>?userPhone=" + $("#userPhone").val();--%>
                    } else {
                        jboxTip(resp);
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
            if (!$("#yzm").val()){
                jboxTip("请填写图形验证码");
                return;
            }
            // if ($("#yzm").val() != $("#code").html()){
            //     jboxTip("图形验证码错误");
            //     return ;
            // }
            var url = "<s:url value='/inx/jsres/checkPhoneIsVerify'/>";
            var userPhone = $("#userPhone").val();
            var yzm = $("#yzm").val();

            var data = {
                userPhone: userPhone,
                yzm: yzm
            };
            var param = JSON.stringify(data);
            // 加密  公钥指数  ""  公钥系数
            if ("${pkExponent}" && "${pkModulus}") {
                var key = RSAUtils.getKeyPair("${pkExponent}", "", "${pkModulus}");
                data = RSAUtils.encryptedString(key, encodeURI(param));
            }
            jboxPost(url, {
                data:data
                },
                function (resp) {
                    jboxTip(resp);
                    if (resp != "${GlobalConstant.USER_PHONE_NOTAUTHEN}") {
                        settime(obj);
                        // $("#code").click();
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

        function reloadVerifyCode() {
            $("#verifyImage").attr("src", "<s:url value='/captcha'/>?random=" + Math.random());
        }

        // 验证码颜色
        function modBgColor(){
            var color = "rgb(" + Math.floor(Math.random() * 256) + "," + Math.floor(Math.random() * 256) + "," + Math.floor(Math.random() * 256) + ")";
            return color;
        }
        // 验证码
        function getVerifyCode(){
            var ranColor = modBgColor();
            var num = '';
            for (var i = 0; i < 4; i++) {
                num += Math.floor(Math.random() * 10);
            }
            $("#code").html(num);
            if ($("#code").hasClass("nocode")) {
                $("#code").removeClass("nocode");
                $("#code").addClass("code");
            }
            $("#code").css('color',ranColor);
        }
    </script>
</head>
<body>
<div class="yw" style="width: 100%; min-height: 100%;">
    <div class="top" onclick="window.location.href='<s:url value='<%=JsresUtil.getUrl(request,response,application)%>'/>'" style="cursor: pointer;height: auto"><%=JsresUtil.getTitle(request,response,application)%></div>
    <div class="content cont_box" >
        <div class="wjpsw" style="height: 100%;padding: 0;">
            <div class="cont_sty">
                <div id="operDiv">
                    <div class="cont_header">
                        <h1>忘记密码</h1>

                    </div>
                    <form id="registerForm" action="<s:url value='/inx/jsres/checkVerifyCode'/>" method="post"
                    >
                        <div class="cont_body">
                            <div class="form_item" style="margin-right: 106px;">
                                <label for="userPhone">
                                    <span style="color: red;">*</span> 手机号码：</label>
                                <input type="text" name="userPhone" id="userPhone"
                                       onkeydown="clearUselessInfo()"
                                       class="validate[custom[mobile] required] txt cont_input"
                                       value=""/>
                                <span style="color:red" class="userPhoneErr"></span>
                            </div>
                            <div class="form_item">
                                <input type="text" id="yzm" name="yzm" style="margin-left: 35px;" class="validate[required] txt cont_input yzm" value="" placeholder="请输入验证码" />
                                <img id="verifyImage" src="<s:url value='/captcha'/>"
                                     style="cursor:pointer;height: 40px; position: absolute;margin-left: -110px;float: left"
                                     onclick="reloadVerifyCode();" title="点击更换验证码" alt="看不清，换一张"/>
                            </div>
                            <div class="form_item">
                                <label for="verifyCode"><span style="color: red;">*</span> 验证码：</label>
                                <input id="verifyCode" name="verifyCode" type="text"
                                       class="validate[required] txt cont_input"/>
                                <input type="button" id="sendcode" onclick="checkPhone(this)" class="btn_blue yzmBtn"
                                       value="获取验证码">
                            </div>
                            <div>
                                <input type="button" value="下一步" onclick="register();" id="registerButton"
                                       class="btn_blue nextBtn"/>
                                <span style="color:red" id="verifyCodeErr"></span>
                                <c:if test='${!empty errorMsg}'>
                                    <span id="errorMsg" style="color:red;">${errorMsg}</span>
                                </c:if>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="footer">主管单位：${sysCfgMap['the_competent_unit']}   |  技术支持：南京品德网络信息技术有限公司   |  <a href="https://beian.miit.gov.cn/" target="_blank" style="color:#FFFFFF;">工信部备案号：苏ICP备14054231号-1</a></div>
</div>

</body>
</html>