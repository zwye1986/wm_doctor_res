<script type="text/javascript">
</script>
 <form id="dassessmentInfo"> 
 	<table class="basic userInfo"  style="width:96%; ">
 		<input type="hidden" name="orgName"/>
 		<input type="hidden" name="trainingSpeName"/>
 		<input type="hidden" name="doctorFlow" value="${doctor.doctorFlow}"/>
 		
 		<tbody class="basicInfo">	
        		<tr>
        			<td colspan="2">学员单位</td>
        			<td colspan="2" style="text-align: left;padding-left: 10px;">
        				<select style="width: 150px;">
        					<option value="">请选择</option>
        					<c:forEach items="${applicationScope.sysOrgList}" var="org">
        						<option value="${org.orgFlow}" <c:if test="${doctor.orgFlow eq org.orgFlow}">selected</c:if>>${org.orgName}</option>
        					</c:forEach>
        				</select>
        			</td>
        			<td colspan="2">培训基地</td>
        			<td colspan="2" style="text-align: left;padding-left: 10px;" >
        				<select style="width: 150px;" name="orgFlow" id="orgFlow">
        					<option value="">请选择</option>
        					<c:forEach items="${applicationScope.sysOrgList}" var="org">
        						<option value="${org.orgFlow}" <c:if test="${doctor.orgFlow eq org.orgFlow}">selected</c:if>>${org.orgName}</option>
        					</c:forEach>
        				</select>
        			</td>
        		</tr>
        		<tr>
        			<%--<td colspan="2">类型</td>--%>
        			<%--<td colspan="2" style="text-align: left;padding-left: 10px;">--%>
        				<%--<select style="width: 150px;">--%>
        					<%--<option value="">请选择</option>--%>
        					<%--<option>中医</option>--%>
        					<%--<option>西医</option>--%>
        				<%--</select>--%>
        			<%--</td>--%>
        			<td colspan="2">培训专业</td>
        			<td colspan="2" style="text-align: left;padding-left: 10px;">
        				<select style="width: 150px;" name="trainingSpeId" id="trainingSpeId">
        					<option value="">请选择</option>
        					<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
        						<option value="${dict.dictId}" <c:if test="${doctor.trainingSpeId eq dict.dictId}">selected</c:if>>${dict.dictName}</option>
        					</c:forEach>
        				</select>
        			</td>
						<td colspan="2">是否执业医师</td>
						<td colspan="2" style="text-align: left;padding-left: 10px;">
							<select style="width: 150px;" name="doctorLicenseFlag">
								<option value="">请选择</option>
								<option value="${GlobalConstant.FLAG_Y}" <c:if test="${doctor.doctorLicenseFlag eq GlobalConstant.FLAG_Y}">selected</c:if>>是</option>
								<option value="${GlobalConstant.FLAG_N}" <c:if test="${doctor.doctorLicenseFlag eq GlobalConstant.FLAG_N}">selected</c:if>>否</option>
							</select>
						</td>
        		</tr>
        		<%--<tr>--%>
        			<%--<td colspan="2">是否执业医师</td>--%>
        			<%--<td colspan="6" style="text-align: left;padding-left: 10px;">--%>
						<%--<select style="width: 150px;" name="doctorLicenseFlag">--%>
							<%--<option value="">请选择</option>--%>
							<%--<option value="${GlobalConstant.FLAG_Y}" <c:if test="${doctor.doctorLicenseFlag eq GlobalConstant.FLAG_Y}">selected</c:if>>是</option>--%>
							<%--<option value="${GlobalConstant.FLAG_N}" <c:if test="${doctor.doctorLicenseFlag eq GlobalConstant.FLAG_N}">selected</c:if>>否</option>--%>
        				<%--</select>--%>
        			<%--</td>--%>
        		<%--</tr>--%>
        	</tbody>
 	</table>
 	 <div style="margin-top: 10px;text-align: center;margin-bottom: 10px;">
	        <input type="button" value="保&#12288;存" class="search" onclick="doctorSave();"/>
	        <input type="button" value="关&#12288;闭" class="search" onclick="closes();"/>
	 </div>
 </form>