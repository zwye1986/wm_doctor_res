<%@ page import="com.pinde.sci.util.jsres.JsresUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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
            "password": "密码长度不足6位，或使用了非法字符",
            "equals": "两次输入的密码不一致",
            "verifyCode": "验证码有误，请重新输入",
            "passwordEmpty": "密码不能为空"
        };

        // 初始化验证码
        $(document).ready(function () {
            getVerifyCode();
            $("#code").on('click', getVerifyCode);
        });


        var countdown = 60;

        function register() {

            $("#verifyCodeErr").html("");
            $(".userPhoneErr").text("");
            $("#errorMsg").text("");
            if (!$("#registerForm").validationEngine("validate")) {
                return false;
            }
            if ($("#userPhone").val() == "") {
                jboxTip("请填写手机号码");
                return;
            }
            if (!$("#yzm").val()) {
                jboxTip("请填写图形验证码");
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
            var data = {
                userPhone: $("#userPhone").val(),
                verifyCode: $("#verifyCode").val()
            };
            var param = JSON.stringify(data);
            // 加密  公钥指数  ""  公钥系数
            if ("${pkExponent}" && "${pkModulus}") {
                var key = RSAUtils.getKeyPair("${pkExponent}", "", "${pkModulus}");
                data = RSAUtils.encryptedString(key, encodeURI(param));
            }
            var url = "<s:url value='/inx/jsres/checkVerifyCode'/>";
            jboxPost(url, {data:data},
                function (resp) {
                    if (resp == "${GlobalConstant.VERIFT_CODE_RIGHT}") {

                        var userPhone = $("#userPhone").val();
                        var verifyCode = $("#verifyCode").val();
                        var url = "<s:url value='/inx/jsres/setPasswd'/>";
                        document.write("<form action='" + url + "' method=post id='passwdForm' name='passwdForm' style='display:none'>");
                        document.write("<input type='hidden' name='userPhone' value='" + userPhone + "' />");
                        document.write("<input type='hidden' name='verifyCode' value='" + verifyCode + "' />");
                        document.write("</form>");
                        document.passwdForm.submit();
                        <%--window.location.href = "<s:url value='/inx/jsres/setPasswd'/>?userPhone=" + $("#userPhone").val();--%>
                    } else {

                        jboxTip(resp);
                    }
                },
                null, false);
        }

        function checkPhone(obj) {

            if (!$("#registerForm").validationEngine("validate")) {
                return false;
            }
            $("#verifyCodeErr").html("");
            $(".userPhoneErr").text("");
            $("#errorMsg").text("");
            if ($("#userPhone").val() == "") {
                jboxTip("请填写手机号码");
                return;
            }
            if (!$("#yzm").val()) {
                jboxTip("请填写图形验证码");
                return;
            }

            var data = {
                userPhone: $("#userPhone").val(),
                yzm: $("#yzm").val()
            };
            var param = JSON.stringify(data);
            // 加密  公钥指数  ""  公钥系数
            if ("${pkExponent}" && "${pkModulus}") {
                var key = RSAUtils.getKeyPair("${pkExponent}", "", "${pkModulus}");
                data = RSAUtils.encryptedString(key, encodeURI(param));
            }

            var url = "<s:url value='/inx/jsres/checkPhone'/>";
            jboxPost(url, {data: data},
                function (resp) {
                    if (resp == "200") {
                        jboxTip("发送成功！");
                    }
                    if (resp != "${GlobalConstant.USER_PHONE_REPEAT}") {
                        settime(obj)
                    }
                    if (resp == "${GlobalConstant.USER_PHONE_REPEAT}") {
                        jboxTip(resp);
                    }
                    if (resp == "请不要频繁发送短信验证码") {
                        jboxTip(resp);
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
        function modBgColor() {
            var color = "rgb(" + Math.floor(Math.random() * 256) + "," + Math.floor(Math.random() * 256) + "," + Math.floor(Math.random() * 256) + ")";
            return color;
        }

        // 验证码
        function getVerifyCode() {
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
            $("#code").css('color', ranColor);
        }

        function checkIdCard() {
            var zjlx = $("#zjlxId").val();
            var data = "";
            if (zjlx == "居民身份证") {
                var idcard = $("#idNo").val();
                const regIdCard = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
                if (!regIdCard.test(idcard)) {
                    jboxTip('身份证号填写格式有误！');
                    $("#idNo").val("");
                    return false;
                }
                data = {idNo: $("#idNo").val()};
            } else if (zjlx == "港澳居民来往内地通行证") {
                data = {idNo: $("#xghzval").val()};
            } else if (zjlx == "台湾居民居住证") {
                data = {idNo: $("#amhzval").val()};
            } else if (zjlx == "台湾居民来往大陆通行证") {
                data = {idNo: $("#twdltxzval").val()};
            } else if (zjlx == "外国人永久居留证") {
                data = {idNo: $("#jwyjjzzval").val()};
            } else if (zjlx == "外籍护照") {
                data = {idNo: $("#hzval").val()};
            } else if (zjlx == "港澳居民居住证") {
                data = {idNo: $("#gatjzzval").val()};
            }

            //查询是否在黑名单
            var url = "<s:url value='/inx/jsres/checkBlack'/>";
            // var data = {idNo:$("#idNo").val()};
            jboxPost(url, data,
                function (resp) {
                    if (resp != "" && resp != null && typeof (resp) != 'undefined') {
                        var height = (window.screen.height) * 0.3;
                        var width = (window.screen.width) * 0.5;
                        jboxOpenContent(resp, "提示信息", width, height, true);
                        $("#idNo").val("");
                        return false;
                    }
                }, null, false);
        }

        function bindOpt() {
            var zjlx = $("#zjlxId").val();
            if (zjlx == "居民身份证") {
                $("#sfz").show();
                $("#xgtqhz").hide();
                $("#amtqhz").hide();
                $("#twdltxz").hide();
                $("#jwyjjzz").hide();
                $("#hz").hide();
                $("#gatjmjzz").hide();
                $("#xghzval").val("");
                $("#amhzval").val("");
                $("#twdltxzval").val("");
                $("#jwyjjzzval").val("");
                $("#hzval").val("");
                $("#gatjzzval").val("");
            } else if (zjlx == "港澳居民来往内地通行证") {
                $("#sfz").hide();
                $("#xgtqhz").show();
                $("#amtqhz").hide();
                $("#twdltxz").hide();
                $("#jwyjjzz").hide();
                $("#hz").hide();
                $("#gatjmjzz").hide();
                $("#idNo").val("");
                $("#amhzval").val("");
                $("#twdltxzval").val("");
                $("#jwyjjzzval").val("");
                $("#hzval").val("");
                $("#gatjzzval").val("");
            } else if (zjlx == "台湾居民居住证") {
                $("#sfz").hide();
                $("#xgtqhz").hide();
                $("#amtqhz").show();
                $("#twdltxz").hide();
                $("#jwyjjzz").hide();
                $("#hz").hide();
                $("#gatjmjzz").hide();
                $("#idNo").val("");
                $("#xghzval").val("");
                $("#twdltxzval").val("");
                $("#jwyjjzzval").val("");
                $("#hzval").val("");
                $("#gatjzzval").val("");
            } else if (zjlx == "台湾居民来往大陆通行证") {
                $("#sfz").hide();
                $("#xgtqhz").hide();
                $("#amtqhz").hide();
                $("#twdltxz").show();
                $("#jwyjjzz").hide();
                $("#hz").hide();
                $("#gatjmjzz").hide();
                $("#idNo").val("");
                $("#xghzval").val("");
                $("#amhzval").val("");
                $("#jwyjjzzval").val("");
                $("#hzval").val("");
                $("#gatjzzval").val("");
            } else if (zjlx == "外国人永久居留证") {
                $("#sfz").hide();
                $("#xgtqhz").hide();
                $("#amtqhz").hide();
                $("#twdltxz").hide();
                $("#jwyjjzz").show();
                $("#hz").hide();
                $("#gatjmjzz").hide();
                $("#idNo").val("");
                $("#xghzval").val("");
                $("#amhzval").val("");
                $("#twdltxzval").val("");
                $("#hzval").val("");
                $("#gatjzzval").val("");
            } else if (zjlx == "外籍护照") {
                $("#sfz").hide();
                $("#xgtqhz").hide();
                $("#amtqhz").hide();
                $("#twdltxz").hide();
                $("#jwyjjzz").hide();
                $("#hz").show();
                $("#gatjmjzz").hide();
                $("#idNo").val("");
                $("#xghzval").val("");
                $("#amhzval").val("");
                $("#twdltxzval").val("");
                $("#jwyjjzzval").val("");
                $("#gatjzzval").val("");
            } else if (zjlx == "港澳居民居住证") {
                $("#sfz").hide();
                $("#xgtqhz").hide();
                $("#amtqhz").hide();
                $("#twdltxz").hide();
                $("#jwyjjzz").hide();
                $("#hz").hide();
                $("#gatjmjzz").show();
                $("#idNo").val("");
                $("#xghzval").val("");
                $("#amhzval").val("");
                $("#twdltxzval").val("");
                $("#hzval").val("");
                $("#jwyjjzzval").val("");
            }
        }
    </script>
</head>
<body>
<div class="yw" style="width: 100%; min-height: 100%;">
    <div class="top"
         onclick="window.location.href='<s:url value='<%=JsresUtil.getUrl(request,response,application)%>'/>'"
         style="cursor: pointer;height: auto"><%=JsresUtil.getTitle(request, response, application)%>
    </div>
    <div class="content cont_box" style="height: 75vh;">
        <div class="wjpsw" style="height: 100%;padding: 0;">
            <div class="cont_sty">
                <div id="operDiv">
                    <div class="cont_header">
                        <h1>注册信息</h1>
                        <h2>如已拥有帐户信息，则<a href="<s:url value='<%=JsresUtil.getUrl(request,response,application)%>'/>"
                                         class="line_c">在此登录</a></h2>
                    </div>
                    <form id="registerForm" action="<s:url value='/inx/jsres/saveRegister'/>" method="post">
                        <div class="cont_body" style="padding-top: 3%">
                            <div class="form_item" style="margin-right: 106px;margin-bottom: 20px;">
                                <label for="userPhone" style="width: 180px">
                                    <span style="color: red;">*</span> 证件类型：</label>
                                <select name="zjlxId" id="zjlxId" class="validate txt cont_input" onchange="bindOpt()">
                                    <option value="居民身份证">居民身份证</option>
                                    <option value="港澳居民来往内地通行证">港澳居民来往内地通行证</option>
                                    <option value="台湾居民居住证">台湾居民居住证</option>
                                    <option value="台湾居民来往大陆通行证">台湾居民来往大陆通行证</option>
                                    <option value="外国人永久居留证">外国人永久居留证</option>
                                    <option value="外籍护照">外籍护照</option>
                                    <option value="港澳居民居住证">港澳居民居住证</option>
                                </select>
                            </div>
                            <div class="form_item" style="margin-right: 106px;margin-bottom: 20px;" id="sfz">
                                <label for="userPhone" style="width: 180px">
                                    <span style="color: red;">*</span> 居民身份证：</label>
                                <input type="text" name="idNo" id="idNo"
                                       onblur="checkIdCard()" class="validate[required] txt cont_input"
                                       value=""/>
                            </div>
                            <div class="form_item" style="margin-right: 106px;margin-bottom: 20px;display: none"
                                 id="xgtqhz">
                                <label for="userPhone" style="width: 180px">
                                    <span style="color: red;">*</span> 港澳居民来往内地通行证：</label>
                                <input type="text" name="xghzval" id="xghzval"
                                       onblur="checkIdCard()" class="validate[required] txt cont_input"
                                       value=""/>
                            </div>
                            <div class="form_item" style="margin-right: 106px;margin-bottom: 20px;display: none"
                                 id="amtqhz">
                                <label for="userPhone" style="width: 180px">
                                    <span style="color: red;">*</span> 台湾居民居住证：</label>
                                <input type="text" name="amhzval" id="amhzval"
                                       onblur="checkIdCard()" class="validate[required] txt cont_input"
                                       value=""/>
                            </div>
                            <div class="form_item" style="margin-right: 106px;margin-bottom: 20px;display: none"
                                 id="twdltxz">
                                <label for="userPhone" style="width: 180px">
                                    <span style="color: red;">*</span> 台湾居民来往大陆通行证：</label>
                                <input type="text" name="twdltxzval" id="twdltxzval"
                                       onblur="checkIdCard()" class="validate[required] txt cont_input"
                                       value=""/>
                            </div>
                            <div class="form_item" style="margin-right: 106px;margin-bottom: 20px;display: none"
                                 id="jwyjjzz">
                                <label for="userPhone" style="width: 180px">
                                    <span style="color: red;">*</span> 外国人永久居留证：</label>
                                <input type="text" name="jwyjjzzval" id="jwyjjzzval"
                                       onblur="checkIdCard()" class="validate[required] txt cont_input"
                                       value=""/>
                            </div>
                            <div class="form_item" style="margin-right: 106px;margin-bottom: 20px;display: none"
                                 id="hz">
                                <label for="userPhone" style="width: 180px">
                                    <span style="color: red;">*</span> 外籍护照：</label>
                                <input type="text" name="hzval" id="hzval"
                                       onblur="checkIdCard()" class="validate[required] txt cont_input"
                                       value=""/>
                            </div>
                            <div class="form_item" style="margin-right: 106px;margin-bottom: 20px;display: none"
                                 id="gatjmjzz">
                                <label for="userPhone" style="width: 180px">
                                    <span style="color: red;">*</span> 港澳居民居住证：</label>
                                <input type="text" name="gatjzzval" id="gatjzzval"
                                       onblur="checkIdCard()" class="validate[required] txt cont_input"
                                       value=""/>
                            </div>
                            <div class="form_item" style="margin-right: 106px;margin-bottom: 20px;">
                                <label for="userPhone" style="width: 180px">
                                    <span style="color: red;">*</span> 手机号码：</label>
                                <input type="text" name="userPhone" id="userPhone"
                                       onkeydown="clearUselessInfo()"
                                       class="validate[custom[mobile] required] txt cont_input"
                                       value=""/>
                                <span style="color:red" class="userPhoneErr"></span>
                            </div>
                            <div class="form_item" style="margin-right: 106px;margin-bottom: 20px;">
                                <label for="verifyCode" style="width: 180px"><span style="color: red;">*</span> 验证码：</label>
                                <input type="text" id="yzm" name="yzm"
                                       class="validate[required] txt cont_input" value="" placeholder="请输入验证码"/>
                                <img id="verifyImage" src="<s:url value='/captcha'/>"
                                     style="cursor:pointer;height: 40px; position: absolute;margin-left: -110px;float: left"
                                     onclick="reloadVerifyCode();" title="点击更换验证码" alt="看不清，换一张"/>
                            </div>
                            <div class="form_item" style="margin-bottom: 20px;">
                                <label for="verifyCode" style="width: 180px"><span style="color: red;">*</span> 短信验证码：</label>
                                <input id="verifyCode" name="verifyCode" type="text" style="margin-left: 3px;"
                                       class="validate txt cont_input"/>
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
</div>
<c:if test="${applicationScope.sysCfgMap['online_service']=='Y'}">
    <jsp:include page="/jsp/service.jsp" flush="true"></jsp:include>
</c:if>
<div class="footer">主管单位：${sysCfgMap['the_competent_unit']} | 技术支持：南京品德网络信息技术有限公司 | <a href="https://beian.miit.gov.cn/"
                                                                                       target="_blank"
                                                                                       style="color:#FFFFFF;">工信部备案号：苏ICP备14054231号-1</a>
</div>
</body>
</html>