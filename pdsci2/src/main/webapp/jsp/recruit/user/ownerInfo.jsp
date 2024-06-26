
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
	<script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>

	<script type="text/javascript">

	function save(){

		if(!$("#sysUserForm").validationEngine("validate")) {
			return false;
		}
		if('${user.userHeadImg}'==""&&$("#asshole").val()!="Y"){
			jboxTip("请上传头像");
			return false;
		}
		jboxPost("<s:url value='/recruit/user/saveOwnerInfo'/>", $("#sysUserForm").serialize(), function (resp) {
			jboxTip(resp);
			if(resp=="${GlobalConstant.SAVE_SUCCESSED}")
			{
				setTimeout(function() {
					window.location.reload(true);
				},1000);
			}

		}, null, true);
	}

	function uploadImage(){
		$.ajaxFileUpload({
			url:"<s:url value='/sys/user/userHeadImgUpload'/>?userFlow=${user.userFlow}",
			secureuri:false,
			fileElementId:'imageFile',
			dataType: 'json',
			success: function (data, status){
				if(data.indexOf("success")==-1){
					jboxTip(data);
				}else{
					var arr = [];
					arr = data.split(":");
					$("#userImg").val(arr[1]);
					var url = "${sysCfgMap['upload_base_url']}/"+ arr[1];
					$("#showImg").attr("src",url);
					$("#asshole").val("Y");
				}
			},
			error: function (data, status, e){
				jboxTip('${GlobalConstant.UPLOAD_FAIL}');
			},
			complete:function(){
				$("#imageFile").val("");
			}
		});
	}
</script>
<style>
.ss3{text-align: right;padding-right: 80px;width: 210px;}
.ss3 a{color: #2f8cef}
</style>
</head>
<body>

<input type="hidden" id="asshole">
<form id="sysUserForm" style="height: 100px;" >
	<input type="hidden" name="userFlow" value="${sessionScope.currUser.userFlow}">
<div class="content">
	<div class="title1 clearfix" style="text-align: center;">
		<table class="basic" style="width: 500px;margin:auto;">
			<tr>
				<td style="text-align:right;width:30%;"><font color="red" >*</font>用&nbsp;户&nbsp;名：</td>
				<td>
					<input class="validate[required,custom[userCode]] qtext" title="用户名必须以字母开头，长度在3-18之间，只能包含字符、数字和下划线"  name="userCode" type="text" class="qtext" value="${user.userCode}" />
				</td>
				<td style="text-align: left;;width:200px" rowspan="4">
					<div style="width: 110px;height: 130px;margin: 5px auto 0px;">
						<img src="${sysCfgMap['upload_base_url']}/${user.userHeadImg}" id="showImg" width="100%"
							 height="100%" onerror="this.src='<s:url value="/css/skin/up-pic.jpg"/>'"/>
					</div>
					<div style="text-align: center;">
							<span style="cursor: pointer;text-align: center;">
								[<a onclick="$('#imageFile').click();">${empty user.userHeadImg?'上传头像':'重新上传'}</a>]
							</span>
						<input type="file" id="imageFile" name="headImg" style="display: none" onchange="uploadImage();"/>
					</div>
				</td>
			</tr>
			<tr>
				<td style="text-align:right;line-height:18px;"><font color="red" >*</font>姓&#12288;&#12288;名：</td>
				<td>
					<input class="validate[required] qtext"  name="userName" type="text" class="qtext" value="${user.userName}" />
				</td>
			</tr>
			<tr>
				<td style="text-align:right;line-height:18px;"><font color="red" >*</font>手机号码：</td>
				<td>
					<input class="validate[required,custom[mobile]] qtext"  name="userPhone" type="text" class="qtext" value="${user.userPhone}" />
				</td>
			</tr>
			<tr>
				<td style="text-align:right;line-height:18px;"><font color="red" >*</font>证件号码：</td>
				<td>
					<input class="validate[required] qtext"  name="idNo" type="text" class="qtext" value="${user.idNo}" />
				</td>
			</tr>
		</table>
		<div class="text" style="margin-top: 20px;text-align:center"><font color="red">上传头像文件大小不能超过300K</font></div>
		<div style="text-align: center;margin-top:20px;">
			<input type="button" class="search" onclick="save();" value="保&#12288;存"/>
		</div>
	</div>
</div>
</form>
</body>
</html>