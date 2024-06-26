
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="false"/>
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
<script type="text/javascript" src="<s:url value='/js/cryptojs/tripledes.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/cryptojs/mode-ecb.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/rsasecurity.js'/>"></script>
<script type="text/javascript">
	function modPasswd() {
		if(false==$("#modPasswdForm").validationEngine("validate")){
			return ;
		}
		if($("#userPasswd").val()== $("#userPasswdNew").val()){
            jboxTip("新密码不能与原密码相同,请重新输入");
			return ;
		}
		if($("#userPasswdNew").val()!= $("#userPasswdNew2").val()) {
            jboxTip("两次密码输入不一致,请重新输入");
			return ;
		}
		var reg = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,}$/;
		if($("#userPasswdNew").val().length<8 || !reg.test($("#userPasswdNew").val())){
			jboxTip("数字和字母组合至少8位数密码");
			return;
		}
		var userPasswd = encryptByDES($("input[name='passwdNew']").val(),"${csrftoken}");
		$("input[name='userPasswdNew']").val(userPasswd);
		var url = "<s:url value='/sys/user/savePasswd'/>";
		// var data = $('#modPasswdForm').serialize();
        var data = {userPasswd : $("#userPasswd").val(),userPasswdNew : $("input[name='userPasswdNew']").val(), desFlag : $("#desFlag").val()};
        var param = JSON.stringify(data);
        // 加密  公钥指数  ""  公钥系数
        if("${pkExponent}" && "${pkModulus}"){
            var key = RSAUtils.getKeyPair("${pkExponent}", "", "${pkModulus}");
            data = RSAUtils.encryptedString(key, encodeURI(param));
        }
		jboxPost(url, {data : data, userFlow : $("#userFlow").val()}, function(resp) {
			if(resp == "${GlobalConstant.PASSWD_ERROR}"){
				jboxTip(resp);
			}else {
				jboxClose();
			}
		});
	}
	function encryptByDES(message, key) {
		var keyHex = CryptoJS.enc.Utf8.parse(key);
		var encrypted = CryptoJS.DES.encrypt(message, keyHex, {
			mode: CryptoJS.mode.ECB,
			padding: CryptoJS.pad.Pkcs7
		});
		return encrypted.toString();
	}
</script>
</head>
<body>
<form id="modPasswdForm">
	<input type="hidden" id="desFlag" name="desFlag" value="DES">
<div class="content">
	<div class="title1 clearfix">
		<div id="tagContent">
			<div class="tagContent selectTag" id="tagContent0">
   		 		<table cellpadding="0" cellspacing="0" class="basic">
           			<tr>
                        <th width="250px;">原始密码：</th>
                        <td>
                            <input class="validate[required] xltext" id="userPasswd"  name="userPasswd" type="password" />
                        </td>
                    </tr>
                    <tr>
                        <th>请输入新密码：</th>
                        <td>
                            <input class="validate[required] xltext" id="userPasswdNew" name="passwdNew" type="password"/>
                            <input name="userPasswdNew" type="hidden"/>
                        </td>
                    </tr>
                    <tr>
                        <th>请再次输入新密码：</th>
                        <td>
                            <input class="validate[required] xltext" id="userPasswdNew2" name="userPasswdNew2" type="password">
                        </td>
                     </tr>
                </table>
				<div class="button">
					<input type="hidden" id="userFlow" name="userFlow" value="${param.userFlow}" />
					<input type="button" value="保&#12288;存" class="search" onclick="modPasswd()" />
				</div>
			</div>
		</div>
	</div>
</div>
</form>
</body>
</html>