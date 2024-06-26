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
            "passwordEmpty": "密码不能为空"
        };

        function passwdNew() {
            if (false == $("#modPasswdForm").validationEngine("validate")) {
                return;
            }
            if (!checkPasswd()) {
                return;
            }
            var url = "<s:url value='/inx/jsres/passwdNew'/>";
            // var data = $('#modPasswdForm').serialize();
            var data = {userPhone : $("#userPhone").val(), userPasswd : $("#userPasswd").val(), verifyCode : $("#verifyCode").val()};
            var param = JSON.stringify(data);
            // 加密  公钥指数  ""  公钥系数
            if("${pkExponent}" && "${pkModulus}"){
                var key = RSAUtils.getKeyPair("${pkExponent}", "", "${pkModulus}");
                data = RSAUtils.encryptedString(key, encodeURI(param));
            }
            jboxPost(url, {data : data}, function (res) {
                if ("${GlobalConstant.UPDATE_SUCCESSED}" == res) {
                    jboxTip("${GlobalConstant.UPDATE_SUCCESSED}");
                    setTimeout(function () {
                        window.location.href = "<s:url value='/inx/jsres/modifyToLogin'/>";
                    }, 1000);
                } else if ("${GlobalConstant.UPDATE_FAIL}" == res) {
                    jboxTip("修改失败，请重新修改");
                }
            });
        }

        function checkPasswd() {
            var flag = false;
            var password = $(":password");
            password.each(function () {
                if (!(flag = $.trim(this.value) != "")) {
                    return false;
                }  else {
                    $("." + $(this).attr("name") + "_br").hide();
                    $("." + $(this).attr("name")).text("");
                }
            });
            return flag ? checkEquals(password) : flag;
        }

        function checkEquals(password) {
            if (password[0].value != "" && password[1].value != "") {
                var flag = password[0].value == password[1].value;
                if (flag) {
                    $(".userPasswd2_br").hide();
                } else {
                    $(".userPasswd2_br").show();
                }
                $(".userPasswd2").text(flag ? "" : tip.equals);
                return flag;
            } else {
                return false;
            }
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
                    <form id="modPasswdForm" method="post"
                    >
                        <div class="cont_body">
                            <div class="form_item">
                                <label for="userPasswd">
                                    <span style="color: red;">*</span> 密码：</label>
                                <input type="password" placeholder="请输入8－20位，必须包含数字、大小写字母、特殊字符中的至少3种的密码"
                                       class="validate[custom[newPassword] required] txt cont_input"
                                       name="userPasswd" onchange="checkPasswd();" onpaste="return false"
                                       oncontextmenu="return false" oncopy="return false" oncut="return false"
                                       id="userPasswd" style="width: 358px;"/>
                                <span class="userPasswd_br" style="display:none;"><br/></span><span class="userPasswd"
                                                                                                    style="color: #c00"></span>

                            </div>
                            <div class="form_item">
                                <input type="hidden" id="userPhone" name="userPhone" value="${userPhone}">
                                <input type="hidden" id="verifyCode" name="verifyCode" value="${verifyCode}">
                                <label for="userPasswd2"><span style="color: red;">*</span> 再次输入密码：</label>
                                <input type="password" placeholder="请输入8－20位，必须包含数字、大小写字母、特殊字符中的至少3种的密码"
                                       class="validate[custom[newPassword] required] txt cont_input"
                                       name="userPasswd2" id="userPasswd2" onchange="checkPasswd();"
                                       onpaste="return false" oncontextmenu="return false" oncopy="return false"
                                       oncut="return false" style="width: 358px;"/>
                                <span class="userPasswd2_br" style="display:none;"><br/></span><span class="userPasswd2"
                                                                                                     style="color: #c00"></span>
                            </div>
                            <div>
                                <input type="button" value="确&nbsp;&nbsp;定" onclick="passwdNew();"
                                       style=" margin-left: 110px;"   class=" btn_blue nextBtn"/>
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
