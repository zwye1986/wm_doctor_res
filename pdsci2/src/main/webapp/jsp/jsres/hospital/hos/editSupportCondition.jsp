<title>江苏省住院医师规范化培训管理平台</title>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_cxselect" value="true"/>
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
<script type="text/javascript">
function saveSupportCondition(){
	$(".noVal").each(function(){
		$(this).val("");
	});
	jboxSubmit($("#BaseInfoForm"), "<s:url value='/jsres/base/saveBase'/>?type=${GlobalConstant.SUPPORT_CONDITION}", function (resp) {
		if("${GlobalConstant.SAVE_SUCCESSED}" == resp){
			setTimeout(function(){
				$("#submitBtn").show();
				loadInfo('${GlobalConstant.BASIC_INFO}','${sessionScope.currUser.orgFlow}');
			},1000);
		}
	}, null, true);
	<%--jboxPost("<s:url value='/jsres/base/saveBase'/>" , $("#BaseInfoForm").serialize() , function(resp){--%>
		<%--if("${GlobalConstant.SAVE_SUCCESSED}" == resp){--%>
			<%--setTimeout(function(){--%>
				<%--$("#submitBtn").show();--%>
				<%--loadInfo('${GlobalConstant.SUPPORT_CONDITION}','${sessionScope.currUser.orgFlow}');--%>
			<%--},1000);--%>
		<%--}--%>
	<%--} , null , true);--%>
}
function changeStatus(type,showStatus){
	if(!showStatus){
		$("#"+type+"input").hide();
		$("#"+type+"input").addClass("noVal");
	}else{
		$("#"+type+"input").show();
		$("#"+type+"input").removeClass("noVal");
	}
}

function uploadFile(obj) {

	var fileName = $(obj).val();

	var types = ".doc,.docx,.xls,.xlsx,.zip,.ppt,.pptx".split(",");
	var regStr = "/";
	for (var i = 0; i < types.length; i++) {
		if (types[i]) {
			if (i == (types.length - 1)) {
				regStr = regStr + "\\" + types[i] + '$';
			} else {
				regStr = regStr + "\\" + types[i] + '$|';
			}
		}
	}
	regStr = regStr + '/i';
	regStr = eval(regStr);
	if ($.trim(fileName) != "" && !regStr.test(fileName)) {
		$(obj).val("")
		jboxTip("请上传&nbsp;.doc,.docx,.xls,.xlsx,.zip,.ppt,.pptx格式的文件");
		return;
	}
}

function reupload(id,obj){
	jboxConfirm("确认重新选择？" , function(){
		$("#"+id).show();
		$("#"+id+"Flow").remove();
		$("#"+id+"A").hide();
		$(obj).hide();
	});
}

</script>
<style>
   .condition{height: 20px; width: 250px;}
   .div_table h4 {
	   color: #000000;
	   font: 15px 'Microsoft Yahei';
	   font-weight: 500;
   }

   .grid th {
	   color: #000000;
	   font: 14px 'Microsoft Yahei';
	   font-weight: 500;
   }

   .grid td, .grid td input {
	   color: #000000;
	   font: 14px 'Microsoft Yahei';
	   font-weight: 400;
   }
</style>
<input type="hidden" id="resBase" name="resBase" value="${resBase}"/>
<form id='BaseInfoForm' style="position:relative;">
  <input type="hidden" name="sysOrg.orgFlow" value="${empty sysOrg.orgFlow?sessionScope.currUser.orgFlow:sysOrg.orgFlow}"/>
	<input type="hidden" name="flag" value="${GlobalConstant.SUPPORT_CONDITION}"/>
	<div class="main_bd">
		<div class="div_table">
		  <h4>支撑条件（在是或否栏内选中）</h4>
		  <table border="0" cellpadding="0" cellspacing="0" class="grid">
		    <colgroup>
		      <col width="30%"/>
		      <col width="8%"/>
		      <col width="8%"/>
		      <col width="20%"/>
		      <col width="30%"/>
		    </colgroup>
		    <tbody>
		      <tr>
		        <th style="text-align:left;">项目内容</th>
		        <th>是</th>
		        <th>否</th>
		        <th style="text-align:left;">划“是”者请填写具体数值或措施</th>
				<th>附件</th>
		      </tr>
		      <tr>
		        <td style="text-align:left;">能提供用于基地建设和管理经费</td>
		        <td><label><input  type="radio" name="supportCondition.buildAndMang"  onclick="changeStatus('buildAndMang',true);"value="${GlobalConstant.FLAG_Y }"  <c:if test="${supportCondition.buildAndMang ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> /> </label></td>
		        <td><label><input  type="radio" name="supportCondition.buildAndMang"  onclick="changeStatus('buildAndMang',false);"value="${GlobalConstant.FLAG_N}"  <c:if test="${supportCondition.buildAndMang ==GlobalConstant.FLAG_N }">checked="checked"</c:if> /> </label> </td>
		        <td><input  id="buildAndManginput"  class="input1 condition" name="supportCondition.buildAndManginfo" value="${supportCondition.buildAndManginfo}"  <c:if test="${supportCondition.buildAndMang==GlobalConstant.FLAG_N or empty supportCondition.buildAndMang  }">style="display: none;"</c:if> /></td>
		      	<td>
					<c:set var="file" value="${fileMap['buildAndMangFile']}"></c:set>
					<c:if test="${empty file}">
						<input type='file' name='buildAndMangFile' onchange="uploadFile(this);" accept=".doc,.docx,.xls,.xlsx,.zip,.ppt,.pptx" style="width: 100%;"/>
					</c:if>
					<c:if test="${not empty file}">
						<input type='file' id='buildAndMangFile' name='buildAndMangFile' onchange="uploadFile(this);" accept=".doc,.docx,.xls,.xlsx,.zip,.ppt,.pptx" style="width: 100%;display: none;"/>
						<input hidden id="buildAndMangFileFlow" name="fileFlows" value="${file.fileFlow}">
						<a  id="buildAndMangFileA" href="<s:url value='/jsres/base/downFile'/>?fileFlow=${file.fileFlow}" target="_blank">${file.fileName}</a>
						<a class='opBtn'  style='cursor: pointer;'href="javascript:void(0);" onclick='reupload("buildAndMangFile",this);'>重新选择</a>
					</c:if>
				</td>
			  </tr>
		      <tr>
		        <td style="text-align:left;">能否解决住院医师住宿</td>
		        <td><label><input  type="radio" name="supportCondition.giveLive" onclick="changeStatus('giveLive',true);" value="${GlobalConstant.FLAG_Y }" <c:if test="${supportCondition.giveLive ==GlobalConstant.FLAG_Y }">checked="checked"</c:if>/> </label> </td>
		        <td><label><input type="radio" name="supportCondition.giveLive" onclick="changeStatus('giveLive',false);"value="${GlobalConstant.FLAG_N}"  <c:if test="${supportCondition.giveLive==GlobalConstant.FLAG_N  }">checked="checked"</c:if> /> </label> </td>
		        <td><input id="giveLiveinput"  class="input1 condition" name="supportCondition.giveLiveinfo" value="${supportCondition.giveLiveinfo}"  <c:if test="${supportCondition.giveLive==GlobalConstant.FLAG_N or empty supportCondition.giveLive  }">style="display: none;"</c:if> /></td>
				  <td>
					  <c:set var="file" value="${fileMap['giveLiveFile']}"></c:set>
					  <c:if test="${empty file}">
						  <input type='file' name='giveLiveFile' onchange="uploadFile(this);" accept=".doc,.docx,.xls,.xlsx,.zip,.ppt,.pptx" style="width: 100%;"/>
					  </c:if>
					  <c:if test="${not empty file}">
						  <input type='file' id='giveLiveFile' name='giveLiveFile' onchange="uploadFile(this);" accept=".doc,.docx,.xls,.xlsx,.zip,.ppt,.pptx" style="width: 100%;display: none;"/>
						  <input hidden id="giveLiveFileFlow" name="fileFlows" value="${file.fileFlow}">
						  <a  id="giveLiveFileA" href="<s:url value='/jsres/base/downFile'/>?fileFlow=${file.fileFlow}" target="_blank">${file.fileName}</a>
						  <a class='opBtn'  style='cursor: pointer;'href="javascript:void(0);" onclick='reupload("giveLiveFile",this);'>重新选择</a>
					  </c:if>
				  </td>
			  </tr>
		      <tr>
		        <td style="text-align:left;">是否能解决住院医师工资及补贴</td>
		        <td><label><input type="radio" name="supportCondition.wageAndTip"  onclick="changeStatus('wageAndTip',true)"  value="${GlobalConstant.FLAG_Y }"<c:if test="${supportCondition.wageAndTip==GlobalConstant.FLAG_Y }">checked="checked"</c:if>/> </label> </td>
		        <td><label><input type="radio" name="supportCondition.wageAndTip" onclick="changeStatus('wageAndTip',false)" value="${GlobalConstant.FLAG_N}" <c:if test="${supportCondition.wageAndTip==GlobalConstant.FLAG_N  }">checked="checked"</c:if>/> </label> </td>
		        <td><input id="wageAndTipinput"  class="input1 condition" name="supportCondition.wageAndTipinfo" value="${supportCondition.wageAndTipinfo}" <c:if test="${supportCondition.wageAndTip==GlobalConstant.FLAG_N or empty supportCondition.wageAndTip  }">style="display: none;"</c:if> /></td>
				  <td>
					  <c:set var="file" value="${fileMap['wageAndTipFile']}"></c:set>
					  <c:if test="${empty file}">
						  <input type='file' name='wageAndTipFile' onchange="uploadFile(this);" accept=".doc,.docx,.xls,.xlsx,.zip,.ppt,.pptx" style="width: 100%;"/>
					  </c:if>
					  <c:if test="${not empty file}">
						  <input type='file' id='wageAndTipFile' name='wageAndTipFile' onchange="uploadFile(this);" accept=".doc,.docx,.xls,.xlsx,.zip,.ppt,.pptx" style="width: 100%;display: none;"/>
						  <input hidden id="wageAndTipFileFlow" name="fileFlows" value="${file.fileFlow}">
						  <a  id="wageAndTipFileA" href="<s:url value='/jsres/base/downFile'/>?fileFlow=${file.fileFlow}" target="_blank">${file.fileName}</a>
						  <a class='opBtn'  style='cursor: pointer;'href="javascript:void(0);" onclick='reupload("wageAndTipFile",this);'>重新选择</a>
					  </c:if>
				  </td>
			  </tr>
		      <tr>
		        <td style="text-align:left;">是否能解决住院医师的社会保障</td>
		        <td><label><input  type="radio" name="supportCondition.socialSecurity"  onclick="changeStatus('socialSecurity',true);"value="${GlobalConstant.FLAG_Y }"  <c:if test="${supportCondition.socialSecurity ==GlobalConstant.FLAG_Y }">checked="checked"</c:if>/> </label> </td>
		        <td><label><input  type="radio" name="supportCondition.socialSecurity" onclick="changeStatus('socialSecurity',false);" value="${GlobalConstant.FLAG_N}" <c:if test="${supportCondition.socialSecurity==GlobalConstant.FLAG_N  }">checked="checked"</c:if> /> </label> </td>
		        <td><input id="socialSecurityinput"  class="input1 condition" name="supportCondition.socialSecurityinfo" value="${supportCondition.socialSecurityinfo}" <c:if test="${supportCondition.socialSecurity==GlobalConstant.FLAG_N or empty supportCondition.socialSecurity  }">style="display: none;"</c:if> /></td>
				  <td>
					  <c:set var="file" value="${fileMap['socialSecurityFile']}"></c:set>
					  <c:if test="${empty file}">
						  <input type='file' name='socialSecurityFile' onchange="uploadFile(this);" accept=".doc,.docx,.xls,.xlsx,.zip,.ppt,.pptx" style="width: 100%;"/>
					  </c:if>
					  <c:if test="${not empty file}">
						  <input type='file' id='socialSecurityFile' name='socialSecurityFile' onchange="uploadFile(this);" accept=".doc,.docx,.xls,.xlsx,.zip,.ppt,.pptx" style="width: 100%;display: none;"/>
						  <input hidden id="socialSecurityFileFlow" name="fileFlows" value="${file.fileFlow}">
						  <a  id="socialSecurityFileA" href="<s:url value='/jsres/base/downFile'/>?fileFlow=${file.fileFlow}" target="_blank">${file.fileName}</a>
						  <a class='opBtn'  style='cursor: pointer;'href="javascript:void(0);" onclick='reupload("socialSecurityFile",this);'>重新选择</a>
					  </c:if>
				  </td>
			  </tr>
		      <tr>
		        <td style="text-align:left;">能否解决住院医师的人事档案和工龄</td>
		        <td><label><input type="radio" name="supportCondition.recordAndStanding" onclick="changeStatus('recordAndStanding',true);" value="${GlobalConstant.FLAG_Y }"  <c:if test="${supportCondition.recordAndStanding ==GlobalConstant.FLAG_Y }">checked="checked"</c:if>/> </label> </td>
		        <td><label><input type="radio"  name="supportCondition.recordAndStanding"  onclick="changeStatus('recordAndStanding',false);" value="${GlobalConstant.FLAG_N}" <c:if test="${supportCondition.recordAndStanding==GlobalConstant.FLAG_N  }">checked="checked"</c:if>/> </label> </td>
		        <td><input  id="recordAndStandinginput"  class="input1 condition" name="supportCondition.recordAndStandinginfo" value="${supportCondition.recordAndStandinginfo}" <c:if test="${supportCondition.recordAndStanding==GlobalConstant.FLAG_N or empty supportCondition.recordAndStanding   }">style="display: none;"</c:if> /></td>
				  <td>
					  <c:set var="file" value="${fileMap['recordAndStandingFile']}"></c:set>
					  <c:if test="${empty file}">
						  <input type='file' name='recordAndStandingFile' onchange="uploadFile(this);" accept=".doc,.docx,.xls,.xlsx,.zip,.ppt,.pptx" style="width: 100%;"/>
					  </c:if>
					  <c:if test="${not empty file}">
						  <input type='file' id='recordAndStandingFile' name='recordAndStandingFile' onchange="uploadFile(this);" accept=".doc,.docx,.xls,.xlsx,.zip,.ppt,.pptx" style="width: 100%;display: none;"/>
						  <input hidden id="recordAndStandingFileFlow" name="fileFlows" value="${file.fileFlow}">
						  <a  id="recordAndStandingFileA" href="<s:url value='/jsres/base/downFile'/>?fileFlow=${file.fileFlow}" target="_blank">${file.fileName}</a>
						  <a class='opBtn'  style='cursor: pointer;'href="javascript:void(0);" onclick='reupload("recordAndStandingFile",this);'>重新选择</a>
					  </c:if>
				  </td>
			  </tr>
		      <tr>
		        <td style="text-align:left;">能否解决住院医师的医师资格和注册管理</td>
		        <td><label><input  type="radio" name="supportCondition.qualifiAndregis" onclick="changeStatus('qualifiAndregis',true)" value="${GlobalConstant.FLAG_Y }"<c:if test="${supportCondition.qualifiAndregis ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> /> </label> </td>
		        <td><label><input  type="radio"  name="supportCondition.qualifiAndregis"  onclick="changeStatus('qualifiAndregis',false)" value="${GlobalConstant.FLAG_N}" <c:if test="${supportCondition.qualifiAndregis==GlobalConstant.FLAG_N }">checked="checked"</c:if>/> </label> </td>
		        <td><input id="qualifiAndregisinput"  class="input1 condition" name="supportCondition.qualifiAndregisinfo" value="${supportCondition.qualifiAndregisinfo}" <c:if test="${supportCondition.qualifiAndregis==GlobalConstant.FLAG_N or empty supportCondition.qualifiAndregis  }">style="display: none;"</c:if> /></td>
				  <td>
					  <c:set var="file" value="${fileMap['qualifiAndregisFile']}"></c:set>
					  <c:if test="${empty file}">
						  <input type='file' name='qualifiAndregisFile' onchange="uploadFile(this);" accept=".doc,.docx,.xls,.xlsx,.zip,.ppt,.pptx" style="width: 100%;"/>
					  </c:if>
					  <c:if test="${not empty file}">
						  <input type='file' id='qualifiAndregisFile' name='qualifiAndregisFile' onchange="uploadFile(this);" accept=".doc,.docx,.xls,.xlsx,.zip,.ppt,.pptx" style="width: 100%;display: none;"/>
						  <input hidden id="qualifiAndregisFileFlow" name="fileFlows" value="${file.fileFlow}">
						  <a  id="qualifiAndregisFileA" href="<s:url value='/jsres/base/downFile'/>?fileFlow=${file.fileFlow}" target="_blank">${file.fileName}</a>
						  <a class='opBtn'  style='cursor: pointer;'href="javascript:void(0);" onclick='reupload("qualifiAndregisFile",this);'>重新选择</a>
					  </c:if>
				  </td>
			  </tr>
		      <tr>
		        <td style="text-align:left;">能否与培训对象签订3年以上聘用合同</td>
		        <td><label><input  type="radio" name="supportCondition.threeYearContract" onclick="changeStatus('threeYearContract',true);" value="${GlobalConstant.FLAG_Y }" <c:if test="${supportCondition.threeYearContract==GlobalConstant.FLAG_Y}">checked="checked"</c:if>/> </label> </td>
		        <td><label><input  type="radio"name="supportCondition.threeYearContract"  onclick="changeStatus('threeYearContract',false);" value="${GlobalConstant.FLAG_N}" <c:if test="${supportCondition.threeYearContract ==GlobalConstant.FLAG_N  }">checked="checked"</c:if>/> </label> </td>
		        <td><input  id="threeYearContractinput"  class="input1 condition" name="supportCondition.threeYearContractinfo" value="${supportCondition.threeYearContractinfo}" <c:if test="${supportCondition.threeYearContract==GlobalConstant.FLAG_N or empty supportCondition.threeYearContract  }">style="display: none;"</c:if> /></td>
				  <td>
					  <c:set var="file" value="${fileMap['threeYearContractFile']}"></c:set>
					  <c:if test="${empty file}">
						  <input type='file' name='threeYearContractFile' onchange="uploadFile(this);" accept=".doc,.docx,.xls,.xlsx,.zip,.ppt,.pptx" style="width: 100%;"/>
					  </c:if>
					  <c:if test="${not empty file}">
						  <input type='file' id='threeYearContractFile' name='threeYearContractFile' onchange="uploadFile(this);" accept=".doc,.docx,.xls,.xlsx,.zip,.ppt,.pptx" style="width: 100%;display: none;"/>
						  <input hidden id="threeYearContractFileFlow" name="fileFlows" value="${file.fileFlow}">
						  <a  id="threeYearContractFileA" href="<s:url value='/jsres/base/downFile'/>?fileFlow=${file.fileFlow}" target="_blank">${file.fileName}</a>
						  <a class='opBtn'  style='cursor: pointer;'href="javascript:void(0);" onclick='reupload("threeYearContractFile",this);'>重新选择</a>
					  </c:if>
				  </td>
			  </tr>
		      <tr>
		        <td style="text-align:left;">能否保证大多数住院医师分流去基层服务</td>
		        <td><label><input  type="radio" name="supportCondition.goBasicServe" onclick="changeStatus('goBasicServe',true);"value="${GlobalConstant.FLAG_Y }" <c:if test="${supportCondition.goBasicServe ==GlobalConstant.FLAG_Y }">checked="checked"</c:if>/> </label> </td>
		        <td><label><input  type="radio" name="supportCondition.goBasicServe" onclick="changeStatus('goBasicServe',false);" value="${GlobalConstant.FLAG_N}" <c:if test="${supportCondition.goBasicServe==GlobalConstant.FLAG_N  }">checked="checked"</c:if>/> </label> </td>
		        <td><input  id="goBasicServeinput"  class="input1 condition" name="supportCondition.goBasicServeinfo" value="${supportCondition.goBasicServeinfo}" <c:if test="${supportCondition.goBasicServe==GlobalConstant.FLAG_N  or empty supportCondition.goBasicServe }">style="display: none;"</c:if> /></td>
				  <td>
					  <c:set var="file" value="${fileMap['goBasicServeFile']}"></c:set>
					  <c:if test="${empty file}">
						  <input type='file' name='goBasicServeFile' onchange="uploadFile(this);" accept=".doc,.docx,.xls,.xlsx,.zip,.ppt,.pptx" style="width: 100%;"/>
					  </c:if>
					  <c:if test="${not empty file}">
						  <input type='file' id='goBasicServeFile' name='goBasicServeFile' onchange="uploadFile(this);" accept=".doc,.docx,.xls,.xlsx,.zip,.ppt,.pptx" style="width: 100%;display: none;"/>
						  <input hidden id="goBasicServeFileFlow" name="fileFlows" value="${file.fileFlow}">
						  <a  id="goBasicServeFileA" href="<s:url value='/jsres/base/downFile'/>?fileFlow=${file.fileFlow}" target="_blank">${file.fileName}</a>
						  <a class='opBtn'  style='cursor: pointer;'href="javascript:void(0);" onclick='reupload("goBasicServeFile",this);'>重新选择</a>
					  </c:if>
				  </td>
			  </tr>
		      <tr>
		        <td style="text-align:left;">已开展住院医师规范化培训工作的经验</td>
		        <td><label><input  type="radio" name="supportCondition.openTraning" onclick="changeStatus('openTraning',true);"value="${GlobalConstant.FLAG_Y }" <c:if test="${supportCondition.openTraning==GlobalConstant.FLAG_Y }">checked="checked"</c:if>/> </label> </td>
		        <td><label><input  type="radio"name="supportCondition.openTraning" onclick="changeStatus('openTraning',false);"value="${GlobalConstant.FLAG_N}" <c:if test="${supportCondition.openTraning==GlobalConstant.FLAG_N  }">checked="checked"</c:if> /> </label> </td>
				<td><input id="openTraninginput" class="input1 condition" name="supportCondition.openTraninginfo" value="${supportCondition.openTraninginfo}" <c:if test="${supportCondition.openTraning==GlobalConstant.FLAG_N or empty supportCondition.openTraning  }">style="display: none;"</c:if> /></td>
				  <td>
					  <c:set var="file" value="${fileMap['openTraningFile']}"></c:set>
					  <c:if test="${empty file}">
						  <input type='file' name='openTraningFile' onchange="uploadFile(this);" accept=".doc,.docx,.xls,.xlsx,.zip,.ppt,.pptx" style="width: 100%;"/>
					  </c:if>
					  <c:if test="${not empty file}">
						  <input type='file' id='openTraningFile' name='openTraningFile' onchange="uploadFile(this);" accept=".doc,.docx,.xls,.xlsx,.zip,.ppt,.pptx" style="width: 100%;display: none;"/>
						  <input hidden id="openTraningFileFlow" name="fileFlows" value="${file.fileFlow}">
						  <a  id="openTraningFileA" href="<s:url value='/jsres/base/downFile'/>?fileFlow=${file.fileFlow}" target="_blank">${file.fileName}</a>
						  <a class='opBtn'  style='cursor: pointer;'href="javascript:void(0);" onclick='reupload("openTraningFile",this);'>重新选择</a>
					  </c:if>
				  </td>
			  </tr>
		      <tr>
		        <td style="text-align:left;">是否建立了临床技能训练中心</td>
		        <td><label><input  type="radio" name="supportCondition.skillTrain"  onclick="changeStatus('skillTrain',true);"  value="${GlobalConstant.FLAG_Y }" <c:if test="${supportCondition.skillTrain==GlobalConstant.FLAG_Y }">checked="checked"</c:if>/> </label> </td>
		        <td><label><input type="radio" name="supportCondition.skillTrain"   onclick="changeStatus('skillTrain',false);"value="${GlobalConstant.FLAG_N}" <c:if test="${supportCondition.skillTrain ==GlobalConstant.FLAG_N  }">checked="checked"</c:if>/> </label> </td>
		        <td><input class="input1 condition"  id="skillTraininput" name="supportCondition.skillTraininfo" value="${supportCondition.skillTraininfo}" <c:if test="${supportCondition.skillTrain==GlobalConstant.FLAG_N or empty supportCondition.skillTrain  }">style="display: none;"</c:if> /></td>
				  <td>
					  <c:set var="file" value="${fileMap['skillTrainFile']}"></c:set>
					  <c:if test="${empty file}">
						  <input type='file' name='skillTrainFile' onchange="uploadFile(this);" accept=".doc,.docx,.xls,.xlsx,.zip,.ppt,.pptx" style="width: 100%;"/>
					  </c:if>
					  <c:if test="${not empty file}">
						  <input type='file' id='skillTrainFile' name='skillTrainFile' onchange="uploadFile(this);" accept=".doc,.docx,.xls,.xlsx,.zip,.ppt,.pptx" style="width: 100%;display: none;"/>
						  <input hidden id="skillTrainFileFlow" name="fileFlows" value="${file.fileFlow}">
						  <a  id="skillTrainFileA" href="<s:url value='/jsres/base/downFile'/>?fileFlow=${file.fileFlow}" target="_blank">${file.fileName}</a>
						  <a class='opBtn'  style='cursor: pointer;'href="javascript:void(0);" onclick='reupload("skillTrainFile",this);'>重新选择</a>
					  </c:if>
				  </td>
			  </tr>
		      <tr>
		        <td style="text-align:left;">注陪合格资格与主治医师报考、聘任挂钩</td>
		        <td><label><input type="radio" name="supportCondition.zpqualification"  onclick="changeStatus('zpqualification',true);"  value="${GlobalConstant.FLAG_Y }" <c:if test="${supportCondition.zpqualification==GlobalConstant.FLAG_Y }">checked="checked"</c:if>/> </label> </td>
		        <td><label><input type="radio" name="supportCondition.zpqualification" onclick="changeStatus('zpqualification',false);" value="${GlobalConstant.FLAG_N}" <c:if test="${supportCondition.zpqualification ==GlobalConstant.FLAG_N }">checked="checked"</c:if>/> </label> </td>
		        <td><input id="zpqualificationinput"  class="input1 condition" name="supportCondition.zpqualificationinfo" value="${supportCondition.zpqualificationinfo}" <c:if test="${supportCondition.zpqualification==GlobalConstant.FLAG_N or empty supportCondition.zpqualification  }">style="display: none;"</c:if> /></td>
				  <td>
					  <c:set var="file" value="${fileMap['zpqualificationFile']}"></c:set>
					  <c:if test="${empty file}">
						  <input type='file' name='zpqualificationFile' onchange="uploadFile(this);" accept=".doc,.docx,.xls,.xlsx,.zip,.ppt,.pptx" style="width: 100%;"/>
					  </c:if>
					  <c:if test="${not empty file}">
						  <input type='file' id='zpqualificationFile' name='zpqualificationFile' onchange="uploadFile(this);" accept=".doc,.docx,.xls,.xlsx,.zip,.ppt,.pptx" style="width: 100%;display: none;"/>
						  <input hidden id="zpqualificationFileFlow" name="fileFlows" value="${file.fileFlow}">
						  <a  id="zpqualificationFileA" href="<s:url value='/jsres/base/downFile'/>?fileFlow=${file.fileFlow}" target="_blank">${file.fileName}</a>
						  <a class='opBtn'  style='cursor: pointer;'href="javascript:void(0);" onclick='reupload("zpqualificationFile",this);'>重新选择</a>
					  </c:if>
				  </td>
			  </tr>
		    </tbody>
		  </table>
		</div>
	</div>
	<c:if test="${resBase.baseStatusId eq baseStatusEnumNotSubmit.id  or resBase.baseStatusId eq baseStatusEnumNotPassed.id or empty resBase.baseStatusId  }">
		<div class="btn_info">
			<input class="btn_green" onclick="saveSupportCondition();" type="button" value="保&#12288;存"/>
		</div>
	</c:if>
</form>
