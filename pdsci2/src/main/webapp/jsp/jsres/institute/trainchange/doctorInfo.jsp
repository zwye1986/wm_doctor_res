<script type="text/javascript">
$(function(){
	changeWorkAdress("${doctor.doctorTypeId}");
	$("#showImg").load(function(){
		   $("#idNoWatermark").show();
	});
	checkBx('${userResumeExt.isMaster}','master');
	checkBx('${userResumeExt.isDoctor}','doctor');
});
function checkBx(value,type){
	if(value=="${GlobalConstant.FLAG_N}"){
		$("."+type+"Th").hide();
		$("#"+type).attr("colspan",4);
	}else{
		$("."+type+"Th").show();
		$("#"+type).removeAttr("colspan");
	}
}
function changeWorkAdress(doctorTypeId){
	if(doctorTypeId == "${jsResDocTypeEnumCompany.id}"){
		$(".school").hide();
		$(".address").show();
		$("#doctorTypeNameTd").removeAttr("colspan");
		changeOrgLevel("${userResumeExt.medicalHeaithOrgId}");
	}
	if(doctorTypeId == "${jsResDocTypeEnumSocial.id}"){
		$(".school").hide();
		$(".address").hide();
		$("#doctorTypeNameTd").attr("colspan",4);
		$(".address").hide();
		$(".hospital").hide();
	}
	if(doctorTypeId == "${jsResDocTypeEnumGraduate.id}"){
		$(".address").hide();
		$(".school").show();
		$("#doctorTypeNameTd").removeAttr("colspan");
		$(".address").hide();
		$(".hospital").hide();
	}
	if(doctorTypeId==""){
		$(".school").hide();
		$(".address").hide();
		$("#doctorTypeNameTd").attr("colspan",4);
		$(".address").hide();
		$(".hospital").hide();
	}
}
function changeOrgLevel(value){
	if(value!=""){
		$(".dict").hide();
		$("#"+value).show();
	}else{
		$(".dict").hide();
	}
	if(value=="1"){
		$(".medical").hide();
		$("#medicalHeaithOrgIdTd").removeAttr("colspan");
		$(".hospital").show();
		$("#TD").attr("colspan",2);
	}
	if(value=="2"){
		$(".medical").hide();
		$("#medicalHeaithOrgIdTd").attr("colspan",4);
		$(".hospital").hide();
	}
	if(value==""){
		$(".medical").hide();
		$("#medicalHeaithOrgIdTd").attr("colspan",4);
		$(".hospital").hide();
	}
	if(value=="3"){
		$(".medical").show();
		$(".hospital").hide();
		$("#medicalHeaithOrgIdTd").removeAttr("colspan");
	}
}
</script>
 <div class="infoAudit">
	<div class="${'open' eq param.type?'infoAudit2':'main_bd' }">
		<div class="${'open' eq param.type?'search_table':'div_table' }">
			<h4>基本信息</h4>
			<table border="0" cellpadding="0" cellspacing="0" class="base_info">
			<colgroup>
				<col width="15%"/>
				<col width="30%"/>
				<col width="15%"/>
				<col width="21%"/>
				<col width="19%"/>
			</colgroup>
			<tbody>
			    <tr>
			    	<th>姓&#12288;&#12288;名：</th>
			        <td>${user.userName}</td>
			        <th>性&#12288;&#12288;别：</th>
			        <td class="fontc" style="padding-left:10px;">${user.sexName}</td>
			        <td rowspan="5" style="text-align: center;">
			        	<font id="idNoWatermark"  style="display:none;font-size:9px;color:red; position:relative; top: 110px;">${user.idNo }</font>
						<img src="${sysCfgMap['upload_base_url']}/${user.userHeadImg}" style="margin-top: -30px;"
							 id="showImg" width="130px" height="150px"
							 onerror="this.src='<s:url value="/css/skin/up-pic.jpg"/>'"/>
			        </td>
			    </tr>
			    <tr>
			    	<th>证件类型：</th>
			        <td>${user.cretTypeName}</td>
			        <th>证件号：</th>
			        <td>${user.idNo}</td>
				</tr>
			    <tr>
			    	<th>民&#12288;&#12288;族：</th>
			        <td>${user.nationName}</td>
			        <th>生&#12288;&#12288;日：</th>
			        <td>${user.userBirthday}</td>
			    </tr>
			    <tr>
			    	<th>手&#12288;&#12288;机：</th>
			        <td>${user.userPhone}</td>
			        <th>固定电话：</th>
			        <td>${userResumeExt.telephone}</td>
               </tr>
			    <tr>
			    	<th>计算机能力：</th>
			        <td>${doctor.computerSkills}</td>
			        <th>外语能力：</th>
			        <td>${doctor.foreignSkills}</td>
			    </tr>
			    <tr>
			    	<th>电子邮箱：</th>
			        <td colspan="4">${user.userEmail}</td>
			    </tr>
			    <tr>
			    	<th>人员类型：</th>
			        <td id="doctorTypeNameTd">${doctor.doctorTypeName}</td>
			         <th class="school">派送学校：</th>
			        <td class="school" colspan="2">
			        	<label>${doctor.workOrgName}</label>
			        </td>
			        <th class="address">派送单位：</th>
			        <td colspan="2" class="address">${doctor.workOrgName}</td>
			    </tr>
				<tr class="address">
					<th>医疗卫生机构：</th>
					<td colspan="4" id="medicalHeaithOrgIdTd">${userResumeExt.medicalHeaithOrgName}</td>
					<th class="hospital" style="display: none;">医院属性：</th>
					<td colspan="2" class="hospital">${userResumeExt.hospitalAttrName}</td>
					<th class="medical" style="display: none;">基层医疗卫生机构：</th>
					<td colspan="2"  class="medical" >${userResumeExt.basicHealthOrgName}</td>
				</tr>
				<tr style="display: none;" class="hospital">
					<th>医院类别：</th>
					<td>${userResumeExt.hospitalCategoryName}</td>
					<th>单位性质：</th>
			    	<td colspan="2">${userResumeExt.baseAttributeName}</td>
				</tr>
			    <tr>
			    	<th>本人通讯地址：</th>
			        <td colspan="4">${user.userAddress}</td>
			    </tr>
			    <tr>
			    	<th>紧急联系人：</th>
			        <td>${doctor.emergencyName}</td>
			        <th>联系人手机：</th>
			        <td colspan="2">${doctor.emergencyPhone}</td>
			    </tr>
			    <tr>
			    	<th>紧急联系人地址：</th>
			        <td colspan="4">${userResumeExt.emergencyAddress}</td>
			    </tr>
			 </tbody>
		</table>
	</div>
	<div class="div_table">
		<h4>教育情况</h4>		
		<table border="0" cellpadding="0" cellspacing="0" class="base_info">
			<colgroup>
				<col width="15%">
				<col width="30%">
				<col width="15%">
				<col width="40%">
			</colgroup>
			<tbody>
				<tr>
					<th>是否为应届生：</th>			    
			        <td colspan="4">&nbsp;<c:if test="${doctor.isYearGraduate eq GlobalConstant.FLAG_Y }">是</c:if>
			        	 <c:if test="${doctor.isYearGraduate eq GlobalConstant.FLAG_N }">否</c:if>
			        </td>
				</tr>
			     <tr>
			    	<th>本科毕业院校：</th>
			        <td>${userResumeExt.graduatedName}</td>
			        <th>本科毕业专业：</th>
			        <td colspan="2">${userResumeExt.specialized}</td>
			    </tr>
			    <tr>
			        <th>本科毕业时间：</th>
			        <td >${userResumeExt.graduationTime}</td>
			        <th>学位：</th>
		            <td >${userResumeExt.degreeName}</td>
			    </tr>
			    <tr>
			    	<th>是否为硕士：</th>
			    	<td id="master">
			    		<c:if test="${userResumeExt.isMaster eq GlobalConstant.FLAG_Y}">是</c:if>
			    		<c:if test="${userResumeExt.isMaster eq GlobalConstant.FLAG_N}">否</c:if>
			    	</td>
			    	<th class="masterTh" style="display: none;">硕士学位类型：</th>
			    	<td class="masterTh"style="display: none;" colspan="2">${userResumeExt.masterDegreeTypeName}</td>
			    </tr>
		  	    <tr class="masterTh">
			      <th>硕士毕业院校：</th>
			       <td>${userResumeExt.masterGraSchoolName }</td>
			       <th>硕士毕业时间：</th>
			       <td>${userResumeExt.masterGraTime }</td>
		    	</tr>
		  	    <tr class="masterTh">
			      <th>硕士毕业专业：</th>
			       <td>${userResumeExt.masterMajor }</td>
			      <th>学位：</th>
			       <td>${userResumeExt.masterDegreeName}</td>
		    	</tr>
			    <tr>
			    	<th>是否为博士：</th>
			    	<td colspan="4" id="doctor">
			    		<c:if test="${userResumeExt.isDoctor eq GlobalConstant.FLAG_Y}">是</c:if>
			    		<c:if test="${userResumeExt.isDoctor eq GlobalConstant.FLAG_N}">否</c:if>
			    	</td>
			    	<th class="doctorTh" style="display: none;">博士学位类型：</th>
			    	<td class="doctorTh" style="display: none;"colspan="2">${userResumeExt.doctorDegreeTypeName}</td>
			    </tr>
		  	    <tr class="doctorTh">
			      <th>博士毕业院校：</th>
			       <td>${userResumeExt.doctorGraSchoolName }</td>
			       <th>博士毕业时间：</th>
			       <td>${userResumeExt.doctorGraTime }</td>
		    	</tr>
		  	    <tr class=doctorTh>
			      <th>博士毕业专业：</th>
			       <td>${userResumeExt.doctorMajor }</td>
			      <th>学位：</th>
			       <td>${userResumeExt.doctorDegreeName}</td>
		    	</tr>
			    <tr>
			        <th>最高毕业证书编号：</th>
			        <td>${doctor.certificateNo}</td>
			        <th>最高毕业证书上传：</th>
			        <td colspan="2">
			        	<span id="certificateUriSpan" style="display:${!empty userResumeExt.certificateUri?'':'none'} ">
							[<a href="${sysCfgMap['upload_base_url']}/${userResumeExt.certificateUri}" target="_blank">查看毕业证书</a>]
						</span>
					</td>			       
			    </tr>
		        <tr>
			        <th>最高学位证书编号：</th>
			        <td>${doctor.degreeNo}</td>
			        <th>最高学位证书上传：</th>
			        <td colspan="2">
			        	<a style="color:#459ae9;float:right;margin-right:30px;"></a>
			        	<span id="degreeUriSpan" style="display:${!empty userResumeExt.degreeUri?'':'none'} ">
							[<a href="${sysCfgMap['upload_base_url']}/${userResumeExt.degreeUri}" target="_blank">查看学位证书</a>]
						</span>
			        </td>	      
			    </tr>
			 </tbody>
		</table></div>
		<div class="div_table">	
		<h4>现有资格信息</h4>
		<table border="0" align="center" cellpadding="0" cellspacing="0" class="base_info">
		<colgroup>
				<col width="15%">
				<col width="30%">
				<col width="15%">
				<col width="40%">
			</colgroup>
			<tbody>
			    <tr>
			    	<th>专业技术资格：</th>
			        <td>${userResumeExt.technologyQualificationName}</td>
			        <th>取得日期：</th>
			        <td colspan="2">${userResumeExt.getTechnologyQualificationDate}</td>
			    </tr>
			     <tr>
			    	<th>执业资格材料：</th>
			        <td>${userResumeExt.qualificationMaterialName}</td>
			        <th>资格材料编码：</th>
			        <td colspan="2">${userResumeExt.qualificationMaterialCode}</td>
			    </tr>
			     <tr>
			    	<th>执业类别：</th>
			        <td>${userResumeExt.practicingCategoryName}</td>
			        <th>执业范围：</th>
		         	<td colspan="2">${userResumeExt.practicingScope}</td>
			    </tr>
			    <tr>
			    	<th>资格证书上传：</th>
			    	<td colspan="4">
			    		<a style="color:#459ae9;float:right;margin-right:30px;"></a>
			        	<span id="qualificationMaterialUriSpan" style="display:${!empty userResumeExt.qualificationMaterialUri?'':'none'} ">
							[<a href="${sysCfgMap['upload_base_url']}/${userResumeExt.qualificationMaterialUri}" target="_blank">查看资格证书</a>]
						</span>
			    	</td>
			    </tr>
			  </tbody>
			</table>
		</div>	
		<div class="btn_info">
			<input type="button" style="width:100px;" class="btn_blue" onclick="jboxClose();" value="关闭"/>
		</div>
	</div>
</div>