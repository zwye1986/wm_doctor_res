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
        function jboxInfoBasic(info, width) {
            if (window.parent.frames['mainIframe'] != null) {
                var d = top.dialog({
                    id: "artInfo",
                    fixed: true,
                    width: width,
                    title: '提示',
                    cancelValue: '关闭',
                    content: info,
                    backdropOpacity: 0.1,
                    button: [
                        {
                            value: '确定',
                            callback: function () {
                                d.close().remove();
                            },
                            autofocus: true
                        }
                    ]
                });
                d.show();
            } else {
                var d = dialog({
                    id: "artInfo",
                    fixed: true,
                    width: width,
                    title: '提示',
                    cancelValue: '关闭',
                    content: info,
                    backdropOpacity: 0.1,
                    button: [
                        {
                            value: '确定',
                            callback: function () {
                                d.close().remove();
                            },
                            autofocus: true
                        }
                    ]
                });
                d.show();
            }
            jboxCenter();
        }

        function jboxCenter() {
            if (window.parent.frames['mainIframe'] != null) {
                var jboxs = $(window.parent.document).find(".ui-dialog");
                $.each(jboxs, function (i, jbox) {
                    $(jbox).parent().position({
                        my: "center",
                        at: "center",
                        of: $("body", window.parent.document),
                        within: $("body", window.parent.document)
                    });
                });
            } else {
                var jboxs = $(window.document).find(".ui-dialog");
                $.each(jboxs, function (i, jbox) {
                    $(jbox).parent().position({
                        my: "center",
                        at: "center",
                        of: $("body", window.document),
                        within: $("body", window.document)
                    });
                });
            }
        }
        var tip = {
            "equals": "两次输入的密码不一致",
            "passwordEmpty": "密码不能为空"
        };

        function eidtPassword() {
            if (false == $("#modPasswdForm").validationEngine("validate")) {
                return;
            }
            if (!checkPasswd()) {
                return;
            }
            var url = "<s:url value='/inx/supervisio/eidtPassword'/>";
            var data = $('#modPasswdForm').serialize();
            jboxPost(url, data, function (res) {
                if ("${GlobalConstant.UPDATE_SUCCESSED}" == res) {
                    jboxTip("${GlobalConstant.UPDATE_SUCCESSED}");
                    setTimeout(function () {
                        window.location.href = "<s:url value='/inx/supervisio/modifyToLogin'/>";
                    }, 1000);
                } else {
                    jboxTip(res);
                }
            });
        }

        function checkPasswd() {
            var flag = false;
            var password = $(".newPass");
            password.each(function () {
                if (!(flag = $.trim(this.value) != "")) {
                    return false;
                } else {
                    $("." + $(this).attr("name") + "_br").hide();
                    $("." + $(this).attr("name")).text("");
                }
            });
            return flag ? checkEquals(password) : flag;
        }

        function checkWeakPassword() {
            var url = "<s:url value='/inx/supervisio/checkWeakPassword'/>";
            var password = $("#userPasswd").val();
            // 加密  公钥指数  ""  公钥系数
            if("${pkExponent}" && "${pkModulus}"){
                var key = RSAUtils.getKeyPair("${pkExponent}", "", "${pkModulus}");
                password = RSAUtils.encryptedString(key, encodeURI(password));
            }
            jboxPost(url, {password : password}, function (res) {
                if ("${GlobalConstant.FLAG_Y}" == res) {//弱密码池存在
                    jboxTip("新密码不符合安全规则，请修改！");
                    $("#userPasswd").val("");
                }
            },null,false);
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
        $(function () {
            var flag = '${flag}';
            if (flag == 'Y') {
                jboxInfoBasic("您的密码已较长时间未修改，为保证安全性，请重新设置！请输入8－20位，必须包含数字、大小写字母、特殊字符中的至少3种的密码", 370)
            } else {
                jboxInfoBasic("您的密码过于简单，请重新设置！请输入8－20位，必须包含数字、大小写字母、特殊字符中的至少3种的密码", 370)
            }
        })
    </script>
</head>
<body>
<div class="yw"  style="width: 100%; min-height: 100%;">
    <div class="content cont_box" >
        <div class="wjpsw" style="height: 100%;padding: 0;" >
            <div class="cont_sty">
                <div id="operDiv">
                    <div class="cont_header">
                        <h1>修改密码（若无法修改，请联系管理员重置密码）</h1>
                        <h2>如已拥有帐户信息，则<a href="<s:url value='/inx/supervisio'/>"
                                         class="line_c">在此登录</a></h2>
                    </div>
                    <form id="modPasswdForm" method="post"
                    >
                        <div class="cont_body">
                            <div class="form_item">
                                <label for="oldUserPasswd">
                                    <span style="color: red;">*</span> 原密码：</label>
                                <input type="password" placeholder="请输入原密码"
                                       class="validate[required] txt cont_input"
                                       name="oldUserPasswd" id="oldUserPasswd" onpaste="return false"
                                       oncontextmenu="return false" oncopy="return false" oncut="return false"
                                       style="width: 358px;"/>
                            </div>
                            <div class="form_item">
                                <label for="ideentityCheck">
                                    <span style="color: red;">*</span> 身份验证：</label>
                                <input type="text" placeholder="手机号"
                                       class="validate[required,custom[number],maxSize[11]] txt cont_input"
                                       name="ideentityCheck" id="ideentityCheck" onpaste="return false"
                                       oncontextmenu="return false" oncopy="return false" oncut="return false"
                                       style="width: 358px;"/>
                            </div>
                            <div class="form_item">
                                <label for="userPasswd">
                                    <span style="color: red;">*</span> 新密码：</label>
                                <input type="password" placeholder="请输入8－20位，必须包含数字、大小写字母、特殊字符中的至少3种的密码"
                                       class="`validate`[custom[newPassword] required] txt cont_input newPass"
                                       name="userPasswd"  onchange="checkPasswd();" onpaste="return false" onblur="checkWeakPassword();"
                                       oncontextmenu="return false" oncopy="return false" oncut="return false"
                                       id="userPasswd" style="width: 358px;"/>
                                <span class="userPasswd_br" style="display:none;"><br/></span><span class="userPasswd"
                                                                                                    style="color: #c00"></span>

                            </div>
                            <div class="form_item">
                                <input type="hidden" name="userCode" value="${userCode}">
                                <label for="userPasswd2"><span style="color: red;">*</span> 再次输入新密码：</label>
                                <input type="password" placeholder="请输入8－20位，必须包含数字、大小写字母、特殊字符中的至少3种的密码"
                                       class="`validate`[custom[newPassword] required] txt cont_input newPass"
                                       name="userPasswd2" id="userPasswd2" onchange="checkPasswd();"
                                       onpaste="return false" oncontextmenu="return false" oncopy="return false"
                                       oncut="return false" style="width: 358px;"/>
                                <span class="userPasswd2_br" style="display:none;"><br/></span><span class="userPasswd2"
                                                                                                     style="color: #c00"></span>
                            </div>
                            <div>
                                <input type="button" value="修&nbsp;&nbsp;改" onclick="eidtPassword();"
                                       class=" btn_blue nextBtn"/>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="footer">   技术支持：南京品德网络信息技术有限公司 </div>
</div>
</body>
</html>
