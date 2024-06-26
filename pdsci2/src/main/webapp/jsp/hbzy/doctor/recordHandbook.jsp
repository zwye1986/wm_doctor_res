<jsp:include page="/jsp/hbzy/htmlhead-jszy.jsp">
	<jsp:param name="jquery_form" value="true"/>
</jsp:include>
<script type="text/javascript">
	function downloadTemplate(){
		jboxTip("正在下载…………");
		var url = "<s:url value='/jsp/hbzy/doctor/template.docx'/>";
		window.location.href = url;
	}

	function chooseFile(){
		return $("#file").click();
	}
	$(function(){
		$("#file").live("change",function(){
			uploadFile();
		});
	});
	function uploadFile(){
		if(false == $("#fileForm").validationEngine("validate")){
			return false;
		}
		jboxStartLoading();
		var checkResult = checkFile($("#file")[0]);
		if(!checkResult){
			jboxEndLoading();
			return false;
		}
		var url = "<s:url value='/hbzy/doctor/uploadResRecFile'/>";
		jboxSubmit($("#fileForm"),url,function(resp){
			if("${GlobalConstant.UPLOAD_FAIL}" != resp){
				jboxEndLoading();
				var index = resp.indexOf("/");
				if(index != -1){
					returnUrl(resp);
				}else{//验证文件信息
					jboxInfo(resp);
				}
			}
		}, null, false);
	}

	function returnUrl(filePath){
		$("#recFileUri").text("重新上传");
		$("#recFileUriValue").val(filePath);
		var filePath = "${sysCfgMap['upload_base_url']}/" + filePath;
		$("#recFileUriSpan").show();
		$("#recFileUriSpan").find("a").attr('href',filePath);
	}


	function delFile(type) {
		jboxConfirm("确认删除？" , function(){
			$("#"+type+"Del").hide();
			$("#"+type+"Span").hide();
			$("#"+type).text("上传");
			$("#"+type+"Value").val("");
		});
	}

	function saveRecFileUri(){
		if(false==$("#fileUriForm").validationEngine("validate")){
			return false;
		}
		if($("#recFileUriValue").val()==""){
			jboxTip("请上传附学员签名的诚信声明！");
			return false;
		}
		jboxConfirm("请认真阅读声明内容,确认提交?",function(){
			var url = "<s:url value='/hbzy/doctor/saveRecFileUri'/>";
			jboxPost(url, $("#fileUriForm").serialize(),
					function(resp){
						if("${GlobalConstant.SAVE_SUCCESSED}" == resp){
							setTimeout(function(){
								trainRegister();
							},1000);
						}
					}, null, true);
		},null);

	}

	/**
	 * 检查文件格式
	 */
	function checkFile(file){
		var filePath = file.value;
		var types = "${sysCfgMap['inx_image_support_suffix']}".split(",");
		var regStr = "/";
		for(var i = 0 ;i<types.length;i++){
			if(types[i]){
				if(i==(types.length-1)){
					regStr = regStr+"\\"+types[i]+'$';
				}else{
					regStr = regStr+"\\"+types[i]+'$|';
				}
			}
		}
		regStr = regStr+'/i';
		regStr = eval(regStr);
		if($.trim(filePath)!="" && !regStr.test(filePath)){
			file.value = "";
			jboxTip("请上传&nbsp;${sysCfgMap['inx_image_support_suffix']}格式的图片");
			return false;
		}else{
			return true;
		}
	}

</script>

<div class="main_hd">
	<h2 style="text-align: left;border-bottom:1px solid #e6e7ec;font-size:18px;padding-left:40px;"><B>诚信声明</B></h2>
	<h4 style="margin: 0px 40px;margin-top:20px;">1、考核记录册所有内容均为本人填写或提供。</h4>
	<h4 style="margin: 0px 40px;">2、本人承诺填写或提供的所有内容真实、可信。</h4>
	<h4 style="margin: 0px 40px;">3、若填写或提供的内容存在虚假信息，本人负责，并无条件接受以下处理意见：</h4>
	<p style="margin: 0px 40px;line-height:23px;color:#656565;text-decoration:none;margin-bottom:20px;">&#12288;&#12288;一经查证学员填写或提供的内容存在虚假信息，其参加结业理论省统考和临床技能考核的时间无条件延后1年，其考核记录册所有内容须由培训基地管理部门全面审核（出具审核意见）后提交省中医药毕业后教育研究室终审。</p>
	<div style="border-bottom:1px solid #e6e7ec;margin:0 20px;padding-bottom:10px;">
		<h4 style="text-align: right; margin-right: 100px;">学员签名： ${sessionScope.currUser.userName} </h4>
	</div>
	<div style="margin-top:20px;">
		<form id="fileForm" method="post" enctype="multipart/form-data">
			<input type="file" id="file" name="file" class="validate[required]" style="display: none"/>
		</form>
		<form id="fileUriForm">
			<input type="hidden" name="recordFlow" value="${doctorAuth.recordFlow}"/>

			<%--<a href="javascript:void(0)" onclick="downloadTemplate();" class="btn" style="float:left;margin: 0 40px;">打印声明</a>&nbsp;--%>

			<input type="button"  class="btn_brown" onclick="downloadTemplate();" value="打印声明" style="float:left;margin: 0 40px;"/>

		<span id="recFileUriSpan" style="display:${!empty doctorAuth.imageUrl ? '' : 'none'} ">
			[<a href="${sysCfgMap['upload_base_url']}/${doctorAuth.imageUrl}" target="_blank" style="color: #0a8cd2">诚信声明</a>]&nbsp;
		</span>
			<c:if test="${empty doctorAuth.imageUrl}">
				<%--<a id="recFileUri" href="javascript:chooseFile();" class="btn" style="float:left;">上传声明(学员签字)</a>&nbsp;--%>
				<input type="button"  class="btn_brown" onclick="chooseFile();" value="上传声明(学员签字)" style="float:left;"/>&nbsp;
			</c:if>
			<c:if test="${not empty doctorAuth.imageUrl}">
			<%--<a id="recFileUri" href="javascript:chooseFile();" class="btn" style="float:left;">重新上传</a>&nbsp;--%>
			<input type="button"  class="btn_brown" onclick="chooseFile();" value="重新上传" style="float:left;"/>
			</c:if>

			<%--<a id="recFileUriDel" href="javascript:delFile('recFileUri');" class="btn" style="${empty recContent?'display:none':''}">删除</a>&nbsp;--%>
			<input type="hidden" id="recFileUriValue" name="imageUrl" value="${imageUrl}"/>
			<c:if test="${empty imageUrl}">
				<input type="button"  class="btn_brown" onclick="saveRecFileUri();" value="提&#12288;交" style="margin:auto;display:block;margin-top:20px;"/>
			</c:if>
			<c:if test="${not empty imageUrl}">
				<input type="button"  class="btn_brown" onclick="saveRecFileUri();" value="保&#12288;存" style="margin:auto;display:block;margin-top:20px;"/>
			</c:if>
		</form>
	</div>
</div>