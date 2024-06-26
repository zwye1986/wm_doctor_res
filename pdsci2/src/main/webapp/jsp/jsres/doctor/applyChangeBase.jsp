<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>${sysCfgMap['sys_title_name']}</title>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
</jsp:include>
<script type="text/javascript">
$(document).ready(function(){
	getCityArea();
	$("#file").live("change",function(){
		uploadFile();
	});
	//地区回显
	<c:if test="${not empty sysOrg}">
		$("#cityId").attr("data-value","${sysOrg.orgCityId}");
	</c:if>
});

function getCityArea(){
	var url = '<s:url value="/js/provCityAreaJson.min.json"/>';
	var provIds = "320000";
	jboxGet(url,null, function(json) {
		// 提示：如果服务器不支持 .json 类型文件，请将文件改为 .js 文件 
		var newJsonData=new Array();
		var j=0;
		for(var i=0;i<json.length;i++){
			if(provIds==json[i].v){
				var citys=json[i].s;
				for(var k=0;k<citys.length;k++){
				    newJsonData[j++]=citys[k];
					}
			     }
			}
		$.cxSelect.defaults.url = newJsonData; 
		$.cxSelect.defaults.nodata = "none"; 
		$("#provCityAreaId").cxSelect({ 
		    selects : ["city"/* ,"area" */], 
		    nodata : "none",
			firstValue : ""
		}); 
	},null,false);
}

/**
 * 加载关联培训基地
 */
function searchOrgList(){
	//清空原来培训基地！！！
	$("select[name=orgFlow] option[value != '']").remove();
	var cityId = $("#cityId").val();
	if(cityId==""){
		return false;
	}
	jboxStartLoading();
	var url = "<s:url value='/jsres/doctor/searchOrgList'/>?orgCityId=" + cityId;
	jboxGet(url, null, function(resp){
			jboxEndLoading();
			if("" != resp){
				var dataObj = resp;
			 	for(var i = 0; i<dataObj.length; i++){
				  	var orgFlow = dataObj[i].orgFlow;
				  	if(orgFlow != "${param.orgFlow}"){//过滤当前基地
				   	var orgName = dataObj[i].orgName;
				   	$option =$("<option></option>");
				   	$option.attr("value", orgFlow);
				   	$option.text(orgName);
				   	$("select[name=orgFlow]").append($option);
				  	}
				}
				if(""!="${sysOrg.orgFlow}"){
					$("select[name=orgFlow] option[value='${sysOrg.orgFlow}']").attr("selected",true);
				}
			}
		}, null, false);
}
function chooseFile(id){
	$("#upFileId").val(id);
	return $("#file").click();
}
function checkFile(file){
	var filePath = file.value;
	if(filePath==""){
		return false;
	}
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
/**
 * 删除文件
 */
function delFile(fileUrlId) {
	jboxConfirm("确认删除？" , function(){
		$("#"+fileUrlId+"Del").hide();
		$("#"+fileUrlId+"Span").hide();
		$("#"+fileUrlId).text("上传");
		$("#"+fileUrlId+"Value").val("");
		$("#file").val(null);
	});
}
/**
 * 返回文件URL
 */
function returnUrl(filePath){
	var fileUrlId = $("#upFileId").val();
	$('#'+fileUrlId).text("重新上传");
	$('#'+fileUrlId+'Value').val(filePath);
	var filePath = "${sysCfgMap['upload_base_url']}/" + filePath;
	$('#'+fileUrlId+'Del').show();
	$('#'+fileUrlId+'Span').show();
	$('#'+fileUrlId+'Span').find("a").attr('href',filePath);
}
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
	var url = "<s:url value='/jsres/doctor/uploadChangeSpeFile'/>";
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

/**
 * 保存
 */
function submit(){
	if(false==$("#editForm").validationEngine("validate")){
		return false;
	}
	if($("#changeSpeUrlValue").val()==""){
		jboxTip("上传文件不能为空！");
		return false;
	}
	if("${recruit.sessionNumber}">="2015"){
		var checkMess = $("#checkMess").val();
		if("${GlobalConstant.FLAG_Y}" != checkMess){
			jboxInfo(checkMess);
			return false;
		}
	}
	var orgFlow = $("#orgFlow :selected").val();
	var orgName = $("#orgFlow :selected").text();
	var speChangeApplyFile=$("#changeSpeUrlValue").val();
	if(orgName=="请选择"){
		speName = "";
	} 
	var data = {
		recordFlow : "${docOrgHistory.recordFlow}",
		recruitFlow : "${param.recruitFlow}",
		doctorFlow : "${param.doctorFlow}",
		orgFlow : orgFlow,
		orgName : orgName,
		changeStatusId : "${jsResChangeApplyStatusEnumOutApplyWaiting.id}",
		speChangeApplyFile:speChangeApplyFile
	};
	var url = "<s:url value='/jsres/doctor/submitDoctorOrgHistory'/>";
	jboxConfirm("确认提交？",  function(){
		jboxStartLoading();
		$("#submitBtn").attr("disabled",true);
		jboxPost(url, data, function(resp){
			jboxEndLoading();
			window.parent.main("${param.recruitFlow}");
		    if("${GlobalConstant.SAVE_FAIL}" != resp){
				top.jboxTip("保存成功！");
		    	jboxClose();
		    }else{
		    	top.jboxTip("保存失败！");
		    }
		}, function(){
			jboxEndLoading();
		}, false);
	});
}


function checkResOrgSpe(orgFlow){
	if("" == orgFlow){
		return false;	
	}
	if("${recruit.sessionNumber}">="2015"){
		jboxStartLoading();
		var url = "<s:url value='/jsres/doctor/checkResOrgSpe'/>?catSpeId=${param.catSpeId}&speId=${param.speId}&orgFlow=" +orgFlow;
		jboxGet(url, null, function(resp){
				jboxEndLoading();
				if("${GlobalConstant.FLAG_Y}" != resp){
					var orgName = $("#orgFlow :selected").text();
					var catSpeName =  window.parent.$("#catSpeNameTd").text();
					var speName =  window.parent.$("#speNameTd").text();
					$("#checkMess").val("该基地无" + catSpeName + speName + "！");
					jboxInfo($("#checkMess").val());
				}else{
					$("#checkMess").val("${GlobalConstant.FLAG_Y}");
				}
			}, null, false);
	}
}
</script>
</head>
<body>
<div class="div_search">
	<input type="hidden" id="upFileId"/>
	<form id="editForm" style="position: relative;" method="post">
	<input type="hidden" id="checkMess"/>
	<table border="0" cellpadding="0" cellspacing="0" class="base_info" style="margin-top: 25px;">
            <colgroup>
              <col width="30%"/>
              <col width="70%"/>
            </colgroup>
	           <tbody>
        	   <tr>
	               <th>所在地区：</th>
	               <td>
	               		<div id="provCityAreaId">
							<select id="cityId" name="cityId" onchange="searchOrgList();" class="city select validate[required]" data-value="" data-first-title="选择市"></select>&#12288;<b class="red">*</b>
							<!-- <select id="areaId" name="areaId" class="area select validate[required]" data-value="" data-first-title="选择地区"></select> -->
						</div>
					</td>
	           </tr>
	           <tr>
	               <th>培训基地：</th>
	               <td>
	               		<select id="orgFlow" name="orgFlow" class="validate[required] select" onchange="checkResOrgSpe(this.value);" style="width: 160px;">
		               		<option value="">请选择</option>
		               		<%-- <c:forEach items="${orgList}" var="org">
		               			<option value="${org.orgFlow}" ${sysOrg.orgFlow eq org.orgFlow?'selected':''}>${org.orgName}</option>
		               		</c:forEach> --%>
					    </select>&#12288;<b class="red">*</b>
					</td>
	           </tr>
	           <tr>
			<th>选择导入：</th>
			<td colspan="3">
				<input type="hidden" id="changeSpeUrlValue" name="speChangeApplyFile" value="" />
                  	  <label id="changeSpeUrlSpan" style="display:${!empty speChangeApplyFile?'':'none'}">
						[<a href="${sysCfgMap['upload_base_url']}/${speChangeApplyFile}" target="_blank">查看图片</a>]&nbsp;
				      </label>
					  <a id="changeSpeUrl" href="javascript:void(0);" onclick="chooseFile('changeSpeUrl')" class="btn">${empty speChangeApplyFile?'':'重新'}上传</a>&nbsp;
					  <a id="changeSpeUrlDel" href="javascript:delFile('changeSpeUrl');" class="btn" style="${empty speChangeApplyFile?'display:none':''}">删除</a>
					  &nbsp;<label class="red">*</label>
			</td>
		</tr>
	           <tr>
					<th>模板文件：</th>
					<td colspan="3"><a href="<s:url value='/jsp/jsres/daochu/changeBase.docx'/>">培训基地变更申请表.docx</a></td>
				</tr>
	           </tbody>
          </table>
          </form>
          <form id="fileForm" method="post" enctype="multipart/form-data">
			<input type="file" id="file" name="file" accept="${sysCfgMap['inx_image_support_mime']}" style="display: none"/>
	  	</form>
          <div align="center" style="margin-top: 20px; margin-bottom:20px;">
			<input type="button" style="width:100px;" class="btn_red" onclick="javascript:submit();" id="submitBtn" value="提交"/>
			<input type="button" style="width:100px;" class="btn_grey" onclick="javascript:jboxClose();" value="关闭"/>
		 </div>
 </div>
</body>
</html>