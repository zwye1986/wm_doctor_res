
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="rsasecurity" value="true"/>
</jsp:include>
<link rel="stylesheet" type="text/css" href="<s:url value='/css/skin/LightBlue/basic.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
<script type="text/javascript">
	function modPasswd() {
	    debugger;
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
        var pw1 =  $("#userPasswd").val();
        var pw2 =  $("#userPasswdNew").val();
        var pw3 = pw1.replace("+","%2B");
        var pw4 = pw2.replace("+","%2B");
		var url = "<s:url value='/sys/user/savePasswd'/>";
		// var data = $('#modPasswdForm').serialize();
        var data = {userPasswd : pw3,userPasswdNew : pw4};
        var param = JSON.stringify(data);
        // 加密  公钥指数  ""  公钥系数
        if("${pkExponent}" && "${pkModulus}"){
            var key = RSAUtils.getKeyPair("${pkExponent}", "", "${pkModulus}");
            data = RSAUtils.encryptedString(key, encodeURI(param));
        }
		jboxPost(url, {data : data, userFlow : $("#userFlow").val()}, function(resp) {
			window.parent.jboxTip(resp);
            window.parent.jboxClose();
		}, null , false);
	}

    function checkWeakPassword() {
        var url = "<s:url value='/inx/jsres/checkWeakPassword'/>";
        var password = $("#userPasswdNew").val();
        // 加密  公钥指数  ""  公钥系数
        if("${pkExponent}" && "${pkModulus}"){
            var key = RSAUtils.getKeyPair("${pkExponent}", "", "${pkModulus}");
            password = RSAUtils.encryptedString(key, encodeURI(password));
        }
        jboxPost(url, {password : password}, function (res) {
            if ("${GlobalConstant.FLAG_Y}" == res) {//弱密码池存在
                jboxTip("新密码不符合安全规则，请修改！");
                $("#userPasswdNew").val("");
            }
        },null,false);
    }
</script>
</head>
<body>
<form id="modPasswdForm">
<div class="content">
	<div class="title1 clearfix">
		<div id="tagContent">
			<div class="tagContent selectTag" id="tagContent0">
   		 		<table cellpadding="0" cellspacing="0" class="basic">
           			<tr>
                        <th width="140px;">原始密码：</th>
                        <td width="380px;">
                            <input class="validate[required] xltext" id="userPasswd"  name="userPasswd" type="password" style="width:200px;"/>
                        </td>
                    </tr>
                    <tr>
                        <th>请输入新密码：</th>
                        <td>
                            <input class="validate[custom[newPassword] required] xltext" placeholder="8－20位必须包含数字、大小写字母、特殊字符中的至少3种组成" style="width:200px;" id="userPasswdNew" name="userPasswdNew" onblur="checkWeakPassword();" type="password"/>
                        </td>
                    </tr>
                    <tr>
                        <th>请再次输入新密码：</th>
                        <td>
                            <input class="validate[custom[newPassword] required] xltext" placeholder="8－20位必须包含数字、大小写字母、特殊字符中的至少3种组成" id="userPasswdNew2" style="width:200px;"  name="userPasswdNew2" type="password">
                        </td>
                     </tr>
                </table>

                <div >
                    <p style="color:#c00;">注：8－20位必须包含数字、大小写字母、特殊字符中的至少3种组成</p>
                </div>
				<div class="button">
					<input type="hidden" id="userFlow" name="userFlow" value="${param.userFlow}" />
					<input class="btn_green" type="button" value="保&#12288;存" class="search" onclick="modPasswd()" />
				</div>
			</div>
		</div>
	</div>
</div>
</form>
</body>
</html>