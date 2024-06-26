<jsp:include page="/jsp/sczyres/htmlhead-sczyres.jsp">
	<jsp:param name="font" value="true"/>
	<jsp:param name="basic" value="true"/>
</jsp:include>
<script>
$(document).ready(function(){
	var doctorTypeId = "${doctor.doctorTypeId}"
	if(doctorTypeId == "${sczyRecDocTypeEnumAgency.id}"){
		$(".school").hide();
		$(".address").show();
		changeOrgLevel('${extInfo.medicalHeaithOrgId}');
	}
	if(doctorTypeId == "${sczyRecDocTypeEnumIndustry.id}"){
		$(".school").hide();
		$(".hospital").hide();
		$(".medical").hide();
		$(".address").hide();
	}
	if(doctorTypeId == "${sczyRecDocTypeEnumGraduate.id}"){
		$(".address").hide();
		$(".hospital").hide();
		$(".medical").hide();
		$(".school").show();
	}
	if(doctorTypeId==""){
		$(".school").hide();
		$(".address").hide();
	}
});
function changeOrgLevel(value){
	if(value=="1"){
		$(".medical").hide();
		$("#medicalHeaithOrgIdTd").removeAttr("colspan");
		$(".hospital").show();
		$("#TD").attr("colspan",3);
	}
	if(value=="2"){
		$(".medical").hide();
		$("#medicalHeaithOrgIdTd").attr("colspan",5);
		$(".hospital").hide();
	}
	if(value=="" | value=="4"){
		$(".medical").hide();
		$("#medicalHeaithOrgIdTd").attr("colspan",5);
		$(".hospital").hide();
	}
	if(value=="3"){
		$(".hospital").hide();
		$(".medical").show();
		$("#medicalHeaithOrgIdTd").removeAttr("colspan");
	}
}
</script>
	<div class="main_bd">
	  <ul class="div_table">
          <li class="score_frame">
	         <h1>报名信息</h1>
	         <div class="sub_menu">
              <h3>人员类型：${doctor.doctorTypeName}</h3>
             </div>	
		
     <div class="div_table">
     	<%--<h4>基本信息</h4>--%>
		<table border="0" cellspacing="0" cellpadding="0" class="base_info">
		   <colgroup>
              <col width="12%"/>
              <col width="22%"/>
              <col width="12%"/>
              <col width="22%"/>
              <col width="12%"/>
              <col width="20%"/>
            </colgroup>
	     	 <tr>
				<th>姓名：</th>
				<td>${user.userName}</td>
				<th>出生日期：</th>
				<td colspan="2">${user.userBirthday}</td>
				<td rowspan="6" style="text-align: center; padding-top:5px; padding-bottom:5px;">
					<img src="${sysCfgMap['upload_base_url']}/${user.userHeadImg}" height="120px"
						 onerror="this.src='<s:url value="/css/skin/up-pic.jpg"/>'"/>
				</td>
			</tr>
			<tr>
			    <th>民族：</th>
				<td>${user.nationName}</td>
				<th>性别：</th>
				<td colspan="2">${user.sexName}</td>
			</tr>
			<tr>
	            <th>外语能力：</th>
	            <td>${doctor.foreignSkills}</td>
	            <th>计算机能力：</th>
	            <td colspan="2">${doctor.computerSkills}</td>
	        </tr>
	        <tr>
			   <c:set var="isPartner" value="${doctor.isPartner}"></c:set>
			   <th>是否是对口&#12288;<br/>支援：</th>
			   <td colspan=${isPartner eq GlobalConstant.FLAG_Y?'1':'4'}>${isPartner eq GlobalConstant.FLAG_Y?'是':'否'}</td>
			   <c:if test="${isPartner eq GlobalConstant.FLAG_Y}">
			   <th>生源省份：</th>
			   <td colspan="2">${doctor.sourceProvincesName}</td>
			   </c:if>
		   </tr>
		   <tr>
			   <th>证件类型：</th>
			   <td>${user.cretTypeName}</td>
			   <th>证&ensp;件&ensp;号：</th>
			   <td colspan="2">${user.idNo}</td>
		   </tr>
		   <tr>
			   <th>订单定向培养：</th>
			   <td>${extInfo.isGeneralOrderOrientationTrainee eq GlobalConstant.FLAG_Y?'是':'否'}</td>
			   <th>是否应届生：</th>
			   <td colspan="2">${extInfo.yearGraduateFlag eq GlobalConstant.FLAG_Y?'是':'否'}</td>
		   </tr>
		   <tr>
			   <th>规培生源地：</th>
			   <td colspan="3">
					${extInfo.birthProvName}-${extInfo.birthCityName}-${extInfo.birthAreaName}
			   </td>
			   <th>有无执业医师&#12288;<br/>资格：</th>
			   <td>
				   <c:if test="${doctor.doctorLicenseFlag eq GlobalConstant.FLAG_Y}">有</c:if>
				   <c:if test="${doctor.doctorLicenseFlag eq GlobalConstant.FLAG_N}">无</c:if>
			   </td>
		   </tr>
			<tr>
				<th class="school" style="display: none;">派送学校：</th>
				<td class="school" colspan="5" style="display: none;">
					${doctor.workOrgName}
				</td>
				<th class="address" id="address" style="display: none;">派送单位：</th>
				<td colspan="5" class="address" style="display: none;">
					${doctor.workOrgName}
				</td>
			</tr>
			<tr class="address">
				<th>医疗卫生机构：</th>
				<td colspan="5" id="medicalHeaithOrgIdTd">
					<c:forEach items="${dictTypeEnumMedicalHeaithOrgList}" var="tra">
						<c:if test="${extInfo.medicalHeaithOrgId eq tra.dictId}">${tra.dictName}</c:if>
					</c:forEach>
				</select>
				</td>
				<th class="hospital medical" style="display: none;">医院性质：</th>
				<td class="hospital medical" style="display: none;" colspan="3" id="TD">
					<c:forEach items="${dictTypeEnumHospitalAttrList}" var="tra">
						<c:if test="${extInfo.hospitalAttrId eq tra.dictId}">${tra.dictName}</c:if>
					</c:forEach>
				</select>
				</td>
			</tr>
			<tr style="display: none;" class="medical">
				<th class="medical" style="display: none;">基层医疗&#12288;<br/>卫生机构：</th>
				<td class="medical" style="display: none;">
					<c:forEach items="${dictTypeEnumBasicHealthOrgList}" var="tra">
						<c:if test="${extInfo.basicHealthOrgId eq tra.dictId}">${tra.dictName}</c:if>
					</c:forEach>
				</select>
				</td>
				<th>基层医疗卫&#12288;<br/>生机构等级：</th>
				<td colspan="3">
					<c:forEach items="${dictTypeEnumBasicHealthOrgLevelList}" var="level">
						<c:if test="${extInfo.basicHealthOrgLevelId eq level.dictId}">${level.dictName}</c:if>
					</c:forEach>
				</select>
				</td>
			</tr>
			<tr style="display: none;" class="hospital">
				<th>医院类别：</th>
				<td>
					<c:forEach items="${dictTypeEnumHospitalCategoryList}" var="tra">
						<c:if test="${extInfo.hospitalCategoryId eq tra.dictId}">${tra.dictName}</c:if>
					</c:forEach>
				</select>
				</td>
				<th>单位等级：</th>
				<td colspan="3">
					<c:forEach items="${dictTypeEnumBaseAttributeList}" var="tra">
						<c:if test="${extInfo.baseAttributeId eq tra.dictId}">${tra.dictName}</c:if>
					</c:forEach>
				</select>
				</td>
			</tr>
		</table>
		</div>
		
		<div class="div_table" ID="contactway">
			<h4>本人联系方式</h4>
				<table border="0" cellpadding="0" cellspacing="0" class="base_info">
					<colgroup>
						<col width="12%"/>
						<col width="22%"/>
						<col width="12%"/>
						<col width="22%"/>
						<col width="12%"/>
						<col width="20%"/>
					</colgroup>
					<tbody>
					<tr>
						<th>手机号码：</th>
						<td>${user.userPhone}</td>
						<th>通讯地址：</th>
						<td colspan="3">${user.userAddress}</td>
					</tr>
					<tr>
						<th>E-mail：</th>
						<td>${user.userEmail}</td>
						<th>QQ号：</th>
						<td>${extInfo.qqCode}</td>
						<th>其它方式：</th>
						<td>${extInfo.otherWay}</td>
					</tr>
					<tr>
						<th>家庭住址 ：</th>
						<td>${extInfo.homeAddress}</td>
						<th>家庭电话 ：</th>
						<td>${extInfo.homePhome}</td>
						<th>邮编 ：</th>
						<td>${extInfo.zipCode}</td>
					</tr>
					</tbody>
				</table>
		      </div>

			  <div class="div_table">
				  <h4>教育情况</h4>
				  <table border="0" cellpadding="0" cellspacing="0" class="base_info">
					  <colgroup>
						  <col width="16%"/>
						  <col width="30%"/>
						  <col width="16%"/>
						  <col width="38%"/>
					  </colgroup>
					  <tbody>
					  <tr class="graduateTh">
						  <th>本科毕业院校：</th>
						  <td>
							  ${doctor.graduatedName}
						  </td>
						  <th>本科毕业时间：</th>
						  <td >${doctor.graduationTime}</td>
					  </tr>
					  <tr class="graduateTh">
						  <th>本科毕业专业：</th>
						  <td>${doctor.specialized}</td>
						  <th>学&#12288;&#12288;位：</th>
						  <td style="padding-left:10px;">
								  <c:forEach items="${dictTypeEnumUserDegreeList}" var="dict">
									  <c:if test="${ dict.dictId eq extInfo.degreeId }">${dict.dictName}</c:if>
								  </c:forEach>
						  </td>
					  </tr>
					  <tr class="graduateTh">
						  <th>最高学历：</th>
						  <td colspan="3">
							  <c:forEach var="dict" items="${dictTypeEnumUserEducationList }">
								  <c:if test="${user.educationId eq dict.dictId }">${dict.dictName }</c:if>
							  </c:forEach>
						  </td>
					  </tr>
					  <c:if test="${extInfo.isMaster eq GlobalConstant.FLAG_Y }">
					  <tr>
						  <th>是否为硕士：</th>
						  <td>
							  是
						  </td>
						  <th>状&#12288;&#12288;态：</th>
						  <td>
							  <c:if test="${extInfo.masterStatue eq '1' }">在读</c:if>
							  <c:if test="${extInfo.masterStatue eq '2' }">已毕业</c:if>
						  </td>
					  </tr>
					  <tr>
						  <th>硕士就读院校：</th>
						  <td>
							  ${extInfo.maSchool}
						  </td>
						  <th>硕士入学时间：</th>
						  <td colspan="2">${extInfo.masterStartTime}</td>
					  </tr>
					  <tr>
						  <th>所学专业：</th>
						  <td>${extInfo.maMajor}</td>
						  <c:if test="${extInfo.masterStatue eq '2' }">
						  <th>学&#12288;&#12288;位：</th>
						  <td class="masterIsGrad" style="padding-left:10px;">
							  <c:forEach items="${dictTypeEnumUserDegreeList}" var="dict">
								  <c:if test="${ dict.dictId eq extInfo.masterDegreeId}">${dict.dictName}</c:if>
							  </c:forEach>
						  </td>
							</c:if>
					  </tr>
					  <c:if test="${extInfo.masterStatue eq '2' }">
					  <tr>
						  <th>硕士学位类型：</th>
						  <td class="masterIsGrad">
							  ${extInfo.masterDegreeTypeId eq 2?'科学型':'专业型'}
						  </select>
						  </td>
						  <th>硕士毕业时间：</th>
						  <td class="masterIsGrad" colspan="2">${extInfo.maDate}</td>
					  </tr>
					  </c:if>
					  </c:if>
					  <c:if test="${extInfo.isDoctor eq GlobalConstant.FLAG_Y }">
					  <tr>
						  <th>是否为博士：</th>
						  <td>
							  <c:if test="${extInfo.isDoctor eq GlobalConstant.FLAG_Y }">是</c:if>
							  <c:if test="${extInfo.isDoctor eq GlobalConstant.FLAG_N }">否</c:if>
						  </td>
						  <th>状&#12288;&#12288;态：</th>
						  <td>
							  <c:if test="${extInfo.doctorStatue eq '1' }">在读</c:if>
							  <c:if test="${extInfo.doctorStatue eq '2' }">已毕业</c:if>
						  </td>
					  </tr>
					  <tr>
						  <th>博士就读院校：</th>
						  <td>
							  ${extInfo.phdSchool}
						  </td>
						  <th>博士入学时间：</th>
						  <td colspan="2">${extInfo.doctorStartTime}</td>
					  </tr>
					  <tr>
						  <th>所学专业：</th>
						  <td>${extInfo.phdMajor}</td>
						  <c:if test="${extInfo.doctorStatue eq '2' }">
						  <th>学&#12288;&#12288;位：</th>
						  <td>
							  <c:forEach items="${dictTypeEnumUserDegreeList}" var="dict">
								  <c:if test="${ dict.dictId eq extInfo.doctorDegreeId }">${dict.dictName}</c:if>
							  </c:forEach>
						  </td>
							</c:if>
					  </tr>
					  <c:if test="${extInfo.doctorStatue eq '2' }">
					  <tr>
						  <th>博士学位类型：</th>
						  <td class="doctorIsGrad">
							  ${extInfo.doctorDegreeTypeId eq 2?'科学型':'专业型' }
						  </td>
						  <th>博士毕业时间：</th>
						  <td class="doctorIsGrad" colspan="2">${extInfo.phdDate}</td>
					  </tr>
					  </c:if>
					  </c:if>
					  </tbody>
				  </table>
			  </div>
              
            <%--<div class="div_table">--%>
			<%--<h4>工作（实习）经历</h4>--%>
	         <%--<table border="0" cellpadding="0" cellspacing="0" class="grid">--%>
	         	<%--<colgroup>--%>
	         		<%--<col width="5%"/>--%>
					<%--<col width="10%"/>--%>
					<%--<col width="10%"/>--%>
					<%--<col width="10%"/>--%>
					<%--<col width="10%"/>--%>
					<%--<col width="10%"/>--%>
					<%--<col width="10%"/>--%>
					<%--<col width="10%"/>--%>
					<%--<col width="10%"/>--%>
					<%--<col width="10%"/>--%>
					<%--</colgroup>--%>
					<%--<tr style="font-weight: bold;">--%>
					  <%--<th></th>--%>
		              <%--<th>临床工作<br/>起止时间</th>--%>
		              <%--<th>时间长度</th>--%>
		              <%--<th>医院名称</th>--%>
		              <%--<th>医院级别</th>--%>
		              <%--<th>科室</th>--%>
		              <%--<th>职务</th>--%>
		              <%--<th>证明人</th>--%>
		              <%--<th>证明人<br/>现任何职</th>--%>
		              <%--<th>证明人<br/>联系电话</th>--%>
		           <%--</tr>--%>
					<%--<tbody id="workTb">--%>
					<%--<c:if test="${fn:length(extInfo.workResumeList)>0 }">--%>
					<%--<c:forEach var="resume" items="${extInfo.workResumeList}" varStatus="status">--%>
					<%--<tr>--%>
					  <%--<td>${status.count}</td>--%>
                      <%--<td>${resume.clinicalRoundDate}</td>--%>
		              <%--<td>${resume.dateLength}</td>--%>
		              <%--<td>${resume.hospitalName}</td> --%>
		              <%--<td>${resume.hospitalLevel}</td>--%>
		              <%--<td>${resume.deptName}</td>--%>
		              <%--<td>${resume.postName}</td>--%>
		              <%--<td>${resume.witness}</td>--%>
		              <%--<td>${resume.witnessPost}</td>--%>
		              <%--<td>${resume.witnessPhone}</td>--%>
					<%--</tr>--%>
					<%--</c:forEach>--%>
					<%--</c:if>--%>
					<%--<c:if test="${empty extInfo.workResumeList}">--%>
						<%--<tr><td colspan="10">无记录</td></tr>--%>
					<%--</c:if>--%>
				    <%--</tbody>--%>
	        <%--</table>--%>
	        <%--</div>--%>
	        
	        <div class="div_table">
				<h4>证书及文件</h4>
		        <table border="0" cellpadding="0" cellspacing="0" class="base_info">

		           <tr>
			          <th width="20%">身份证图片：</th>
			          <td colspan="3">
			          	  <span id="idNoUriSpan" style="display:${!empty extInfo.idNoUri?'':'none'} ">
		              	      [<a href="${sysCfgMap['upload_base_url']}/${extInfo.idNoUri}" target="_blank">查看图片</a>]&#12288;
		            	  </span>
			          </td>
			       </tr>
			       <tr>
			          <th>最高学位毕业证编号：</th>
			          <td colspan="3">
			              ${empty doctor.certificateNo?"无":doctor.certificateNo}
			              <c:if test='${!empty extInfo.certificateUri}'>
			                  <span id="certificateUriSpan">
		              	          [<a href="${sysCfgMap['upload_base_url']}/${extInfo.certificateUri}" target="_blank">查看图片</a>]&#12288;
		            	      </span>
			              </c:if>
			          </td>
			       </tr> 
			       <tr>
			          <th>最高学位学位证编号：</th>
			          <td colspan="3">
			               ${empty doctor.degreeNo?"无":doctor.degreeNo}
			               <c:if test='${!empty extInfo.degreeUri}'>
			                   <span id="degreeUriSpan">
		              	           [<a href="${sysCfgMap['upload_base_url']}/${extInfo.degreeUri}" target="_blank">查看图片</a>]&#12288;
		            	       </span>
			               </c:if>
			              
			          </td>
			       </tr>
					<c:if test="${doctor.doctorLicenseFlag eq GlobalConstant.FLAG_Y }">
			       <tr>
			          <th> 执业医师资格证号：</th>
			          <td colspan="3">
			              ${empty doctor.qualifiedNo?"无":doctor.qualifiedNo}
			              <c:if test='${!empty extInfo.qualifiedUri}'>
			                  <span id="qualifiedUriSpan">
		              	          [<a href="${sysCfgMap['upload_base_url']}/${extInfo.qualifiedUri}" target="_blank">查看图片</a>]&#12288;
		            	      </span>
			              </c:if>
			              
			          </td>
			       </tr>
					</c:if>
					<tr>
						<th>派送证明：</th>
						<td colspan="3">
							${empty doctor.dispatchFile?"无":''}
						<c:if test='${!empty doctor.dispatchFile}'>
			          	  <span>
		              	      [<a href="${sysCfgMap['upload_base_url']}/${doctor.dispatchFile}" target="_blank">查看图片</a>]&#12288;
		            	  </span>
						</c:if>
						</td>
					</tr>
			    </table>
			</div>
	<div class="div_table">
	  <h4>志愿填报信息</h4>
		<table border="0" width="945" cellspacing="0" cellpadding="0" class="base_info" style="margin-bottom: 10px;">
		    <colgroup>
	            <col width="12%"/>
	            <col width="44%"/>
	            <col width="12%"/>
	            <col width="32%"/>
	        </colgroup>
            <c:forEach items='${recruits}' var='recruit'>
                <c:if test="${recruit.recruitTypeId eq 'Swap'}">
					<tr>
						<td colspan="4">调剂信息</td>
					</tr>
				</c:if>
				<tr>
					<th>基地名称：</th>
					<td>${recruit.orgName}</td>
					<th>专业名称：</th>
					<td>${recruit.catSpeName}
						<c:if test='${recruit.recruitFlag eq "Y" and recruit.recruitTypeId eq recruitTypeEnumFill.id}'>(已录取)</c:if>
						<c:if test='${recruit.recruitFlag eq "Y" and recruit.recruitTypeId eq recruitTypeEnumSwap.id}'>(调剂)</c:if>
						<c:if test='${recruit.recruitFlag eq "N"}'>(未录取)</c:if>
					</td>
				</tr>
				<c:if test="${recruit.catSpeId eq speCatEnumChineseMedicine.id || recruit.catSpeId eq speCatEnumTCMGeneral.id}">
				<tr>
					<th>对应专业：</th>
					<td>${recruit.speName}</td>
					<c:if test="${recruit.catSpeId eq speCatEnumChineseMedicine.id}">
					<th>二级专业：</th>
					<td>${recruit.secondSpeName}</td>
					</c:if>
				</tr>
				<c:if test="${recruit.swapFlag eq 'Y'}">
					<c:forEach items='${moreSpeMap[recruit.recruitFlow]}' var='moreSpe'>
					<c:if test="${(not empty moreSpe.orgFlow) && (not empty moreSpe.catSpeId)}">
					<tr>
						<td colspan="4">第${moreSpe.orderNum}志愿</td>
					</tr>
					<tr>
						<th>基地名称：</th>
						<td>${moreSpe.orgName}</td>
						<th>专业名称：</th>
						<td>${moreSpe.catSpeName}
						</td>
					</tr>
					<tr>
						<th>对应专业：</th>
						<td>${moreSpe.speName}</td>
						<c:if test="${moreSpe.catSpeId eq speCatEnumChineseMedicine.id}">
							<th>二级专业：</th>
							<td>${moreSpe.secondSpeName}</td>
						</c:if>
					</tr>
					</c:if>
					</c:forEach>
				</c:if>
				</c:if>
            </c:forEach>
		</table>
	</div>
	</li>
  </ul>	
</div>
