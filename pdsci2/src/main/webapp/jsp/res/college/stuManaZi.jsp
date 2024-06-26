<script type="text/javascript">
</script>
 <form id="assessmentInfo"> 
 	<table class="basic userInfo"  style="width:96%; ">
 		<c:set var="diyi" value="${resScoreTypeEnumYearScore.id}${resScoreTypeEnumFirstYear.id}"/>
 		<c:set var="dier" value="${resScoreTypeEnumYearScore.id}${resScoreTypeEnumSecondYear.id}"/>
 		<c:set var="disan" value="${resScoreTypeEnumYearScore.id}${resScoreTypeEnumThirdYear.id}"/>
 		<c:set var="disi" value="${resScoreTypeEnumGraduationScore.id}"/>
			<tbody class="assessInfo">
	        		<tr>
	        			<th  colspan="9">年度考核</th>
	        		</tr>
	        		<tr>
	        			<th colspan="9">第一年</th>
	        		</tr>
	        		<tr class="score">
	        			<td style="width:16%; line-height: 20px;">过程考核<br>(是否通过)</td>
	        			<c:if test="${param.roleFlag eq  GlobalConstant.USER_LIST_GLOBAL}">
		        			<td style="width: 10%">
		        				<select name="schResultId">
		        					<option>请选择</option>
		        					<option value="Y" <c:if test="${map[diyi].schResultId eq 'Y'}">selected</c:if>>是</option>
		        					<option value="N" <c:if test="${map[diyi].schResultId eq 'N'}">selected</c:if>>否</option>
		        				</select>
		        			</td>
	        			</c:if>
	        			<c:if test="${param.roleFlag eq  GlobalConstant.USER_LIST_LOCAL}">
	        				<td style="width: 10%">
	        					<c:if test="${map[diyi].schResultId eq 'Y'}">
	        						<lable>是</lable>
	        					</c:if>
	        					<c:if test="${map[diyi].schResultId eq 'N'}">
	        						<lable>否</lable>
	        					</c:if>
	        				</td>
	        			</c:if>
	        			<td style="width: 10%">理论考试</td>
	        			<c:if test="${param.roleFlag eq  GlobalConstant.USER_LIST_GLOBAL}">
		        			<td style="width: 10%">
		        				<input type="text" class="inputText" name="theoryScore" value="${map[diyi].theoryScore}" style="width: 50px;"/>
		        			</td>
	        			</c:if>
	        			<c:if test="${param.roleFlag eq  GlobalConstant.USER_LIST_LOCAL}">
	        				<td style="width: 10%">
		        				<lable>${map[diyi].theoryScore}</lable>
		        			</td>
	        			</c:if>
	        			<td style="width: 10%">技能考试</td>
	        			<c:if test="${param.roleFlag eq  GlobalConstant.USER_LIST_GLOBAL}">
		        			<td style="width: 10%">
		        				<input type="text" class="inputText" name="skillScore" value="${map[diyi].skillScore}" style="width: 50px;"/>
		        			</td>
	        			</c:if>
	        			<c:if test="${param.roleFlag eq  GlobalConstant.USER_LIST_LOCAL}">
	        				<td style="width: 10%">
		        				<lable>${map[diyi].skillScore}</lable>
		        			</td>
	        			</c:if>
	        			<td style="width: 10%; line-height: 20px;">考核结果<br>(是否通过)</td>
	        			<c:if test="${param.roleFlag eq  GlobalConstant.USER_LIST_GLOBAL}">
		        			<td style="width: 10%">
		        				<select name="scoreResultId">
		        					<option>请选择</option>
		        					<option value="Y" <c:if test="${map[diyi].scoreResultId eq 'Y'}">selected</c:if>>是</option>
		        					<option value="N" <c:if test="${map[diyi].scoreResultId eq 'N'}">selected</c:if>>否</option>
		        				</select>
	        				</td>
	        			</c:if>
	        			<c:if test="${param.roleFlag eq  GlobalConstant.USER_LIST_LOCAL}">
	        				<td style="width: 10%">
	        					<c:if test="${map[diyi].scoreResultId eq 'Y'}">
	        						<lable>是</lable>
	        					</c:if>
	        					<c:if test="${map[diyi].scoreResultId eq 'N'}">
	        						<lable>否</lable>
	        					</c:if>
	        				</td>
	        			</c:if>
	        			<c:if test="${param.roleFlag eq  GlobalConstant.USER_LIST_LOCAL}">
		        			<td style="width: 10%">
		        				<a style="cursor: pointer; color: blue;" onclick="auditing('${map[diyi].scoreFlow}');">审核</a>
		        			</td>
	        			</c:if>
	        			<input type="hidden" name="scoreTypeId" value="${resScoreTypeEnumYearScore.id}"/>
	        			<input type="hidden" name="scoreTypeName" value="${resScoreTypeEnumYearScore.name}"/>
	        			<input type="hidden" name="scorePhaseId" value="${resScoreTypeEnumFirstYear.id}"/>
	        			<input type="hidden" name="scorePhaseName" value="${resScoreTypeEnumFirstYear.name}"/>
	        			<input type="hidden" name="scoreFlow" value="${map[diyi].scoreFlow}"/>
	        			
				    <tr>
	        			<th colspan="9">第二年</th>
	        		</tr>  
	        		<tr class="score">
	        			<td style="line-height: 20px;">过程考核<br>(是否通过)</td>
	        			<c:if test="${param.roleFlag eq  GlobalConstant.USER_LIST_GLOBAL}">
		        			<td>
		        				<select name="schResultId">
		        					<option>请选择</option>
		        					<option value="Y" <c:if test="${map[dier].schResultId eq 'Y'}">selected</c:if>>是</option>
		        					<option value="N" <c:if test="${map[dier].schResultId eq 'N'}">selected</c:if>>否</option>
		        				</select>
	        				</td>
	        			</c:if>
	        			<c:if test="${param.roleFlag eq  GlobalConstant.USER_LIST_LOCAL}">
	        				<td style="width: 10%">
	        					<c:if test="${map[dier].schResultId eq 'Y'}">
	        						<lable>是</lable>
	        					</c:if>
	        					<c:if test="${map[dier].schResultId eq 'N'}">
	        						<lable>否</lable>
	        					</c:if>
	        				</td>
	        			</c:if>
	        			<td>理论考试</td>
	        			<c:if test="${param.roleFlag eq  GlobalConstant.USER_LIST_GLOBAL}">
		        			<td>
	        					<input type="text" class="inputText" name="theoryScore" value="${map[dier].theoryScore}" style="width: 50px;"/>
	        				</td>
	        			</c:if>
	        			<c:if test="${param.roleFlag eq  GlobalConstant.USER_LIST_LOCAL}">
	        				<td style="width: 10%">
		        				<lable>${map[dier].theoryScore}</lable>
		        			</td>
	        			</c:if>
	        			<td>技能考试</td>
	        			
	        			<c:if test="${param.roleFlag eq  GlobalConstant.USER_LIST_GLOBAL}">
		        			<td><input type="text" class="inputText" name="skillScore" value="${map[dier].skillScore}" style="width: 50px;"/></td>
	        			</c:if>
	        			<c:if test="${param.roleFlag eq  GlobalConstant.USER_LIST_LOCAL}">
	        				<td style="width: 10%">
		        				<lable>${map[dier].skillScore}</lable>
		        			</td>
	        			</c:if>
	        			
	        			<td style="line-height: 20px;">考核结果<br>(是否通过)</td>
	        			<c:if test="${param.roleFlag eq  GlobalConstant.USER_LIST_GLOBAL}">
		        			<td>
	        					<select name="scoreResultId">
		        					<option>请选择</option>
		        					<option value="Y" <c:if test="${map[dier].scoreResultId eq 'Y'}">selected</c:if>>是</option>
		        					<option value="N" <c:if test="${map[dier].scoreResultId eq 'N'}">selected</c:if>>否</option>
	        					</select>
	        				</td>
	        			</c:if>
	        			<c:if test="${param.roleFlag eq  GlobalConstant.USER_LIST_LOCAL}">
	        				<td style="width: 10%">
	        					<c:if test="${map[dier].scoreResultId eq 'Y'}">
	        						<lable>是</lable>
	        					</c:if>
	        					<c:if test="${map[dier].scoreResultId eq 'N'}">
	        						<lable>否</lable>
	        					</c:if>
	        				</td>
	        			</c:if>
	        			<input type="hidden" name="scoreTypeId" value="${resScoreTypeEnumYearScore.id}"/>
	        			<input type="hidden" name="scoreTypeName" value="${resScoreTypeEnumYearScore.name}"/>
	        			<input type="hidden" name="scorePhaseId" value="${resScoreTypeEnumSecondYear.id}"/>
	        			<input type="hidden" name="scorePhaseName" value="${resScoreTypeEnumSecondYear.name}"/>
	        			<input type="hidden" name="scoreFlow" value="${map[dier].scoreFlow}"/>
	        			<c:if test="${param.roleFlag eq  GlobalConstant.USER_LIST_LOCAL}">
		        			<td style="width: 10%">
		        				<a style="cursor: pointer; color: blue;" onclick="auditing('${map[dier].scoreFlow}');">审核</a>
		        			</td>
	        			</c:if>
	        		<tr>
	        			<th colspan="9">第三年</th>
	        		</tr>  
	        		<tr class="score">
	        			<td style="line-height: 20px;">过程考核<br>(是否通过)</td>
	        			<c:if test="${param.roleFlag eq  GlobalConstant.USER_LIST_GLOBAL}">
		        			<td>
		        				<select name="schResultId">
		        					<option>请选择</option>
		        					<option value="Y" <c:if test="${map[disan].schResultId eq 'Y'}">selected</c:if>>是</option>
		        					<option value="N" <c:if test="${map[disan].schResultId eq 'N'}">selected</c:if>>否</option>
		        				</select>
	        				</td>
	        			</c:if>
	        			<c:if test="${param.roleFlag eq  GlobalConstant.USER_LIST_LOCAL}">
	        				<td style="width: 10%">
	        					<c:if test="${map[disan].schResultId eq 'Y'}">
	        						<lable>是</lable>
	        					</c:if>
	        					<c:if test="${map[disan].schResultId eq 'N'}">
	        						<lable>否</lable>
	        					</c:if>
	        				</td>
	        			</c:if>
	        			<td>理论考试</td>
	        			<c:if test="${param.roleFlag eq  GlobalConstant.USER_LIST_GLOBAL}">
		        			<td><input type="text" class="inputText" name="theoryScore" value="${map[disan].theoryScore}" style="width: 50px;"/></td>
	        			</c:if>
	        			<c:if test="${param.roleFlag eq  GlobalConstant.USER_LIST_LOCAL}">
	        				<td style="width: 10%">
		        				<lable>${map[disan].theoryScore}</lable>
		        			</td>
	        			</c:if>
	        			<td>技能考试</td>
	        			<c:if test="${param.roleFlag eq  GlobalConstant.USER_LIST_GLOBAL}">
		        			<td><input type="text" class="inputText" name="skillScore" value="${map[disan].skillScore}" style="width: 50px;"/></td>
	        			</c:if>
	        			<c:if test="${param.roleFlag eq  GlobalConstant.USER_LIST_LOCAL}">
	        				<td style="width: 10%">
		        				<lable>${map[disan].skillScore}</lable>
		        			</td>
	        			</c:if>
	        			<td style="line-height: 20px;">考核结果<br>(是否通过)</td>
	        			<c:if test="${param.roleFlag eq  GlobalConstant.USER_LIST_GLOBAL}">
		        			<td>
		        				<select name="scoreResultId">
		        					<option>请选择</option>
		        					<option value="Y" <c:if test="${map[disan].scoreResultId eq 'Y'}">selected</c:if>>是</option>
		        					<option value="N" <c:if test="${map[disan].scoreResultId eq 'N'}">selected</c:if>>否</option>
		        				</select>
	        				</td>
	        			</c:if>
	        			<c:if test="${param.roleFlag eq  GlobalConstant.USER_LIST_LOCAL}">
	        				<td style="width: 10%">
	        					<c:if test="${map[disan].scoreResultId eq 'Y'}">
	        						<lable>是</lable>
	        					</c:if>
	        					<c:if test="${map[disan].scoreResultId eq 'N'}">
	        						<lable>否</lable>
	        					</c:if>
	        				</td>
	        			</c:if>
	        			<input type="hidden" name="scoreTypeId" value="${resScoreTypeEnumYearScore.id}"/>
	        			<input type="hidden" name="scoreTypeName" value="${resScoreTypeEnumYearScore.name}"/>
	        			<input type="hidden" name="scorePhaseId" value="${resScoreTypeEnumThirdYear.id}"/>
	        			<input type="hidden" name="scorePhaseName" value="${resScoreTypeEnumThirdYear.name}"/>
	        			<input type="hidden" name="scoreFlow" value="${map[disan].scoreFlow}"/>
	        			<c:if test="${param.roleFlag eq  GlobalConstant.USER_LIST_LOCAL}">
		        			<td style="width: 10%">
		        				<a style="cursor: pointer; color: blue;" onclick="auditing('${map[disan].scoreFlow}');">审核</a>
		        			</td>
	        			</c:if>
	        		</tr>
	        		<tr>
	        			<th  colspan="9">结业考核</th>
	        		</tr> 
	        		<tr class="score">
	        			<td>公共科目</td>
	        			<c:if test="${param.roleFlag eq  GlobalConstant.USER_LIST_GLOBAL}">
		        			<td>
		        				<select name="schResultId">
		        					<option>请选择</option>
		        					<option value="Y" <c:if test="${map[disi].schResultId eq 'Y'}">selected</c:if>>是</option>
		        					<option value="N" <c:if test="${map[disi].schResultId eq 'N'}">selected</c:if>>否</option>
		        				</select>
	        				</td>
	        			</c:if>
	        			<c:if test="${param.roleFlag eq  GlobalConstant.USER_LIST_LOCAL}">
	        				<td style="width: 10%">
	        					<c:if test="${map[disi].schResultId eq 'Y'}">
	        						<lable>是</lable>
	        					</c:if>
	        					<c:if test="${map[disi].schResultId eq 'N'}">
	        						<lable>否</lable>
	        					</c:if>
	        				</td>
	        			</c:if>
	        			<td>理论知识</td>
	        			<c:if test="${param.roleFlag eq  GlobalConstant.USER_LIST_GLOBAL}">
		        			<td><input type="text" class="inputText" name="theoryScore" value="${map[disi].theoryScore}" style="width: 50px;"/></td>
	        			</c:if>
	        			<c:if test="${param.roleFlag eq  GlobalConstant.USER_LIST_LOCAL}">
	        				<td style="width: 10%">
		        				<lable>${map[disi].theoryScore}</lable>
		        			</td>
	        			</c:if>
	        			<td>实践技能</td>
	        			
	        			<c:if test="${param.roleFlag eq  GlobalConstant.USER_LIST_GLOBAL}">
		        			<td><input type="text" class="inputText" name="skillScore" value="${map[disi].skillScore}" style="width: 50px;"/></td>
	        			</c:if>
	        			<c:if test="${param.roleFlag eq  GlobalConstant.USER_LIST_LOCAL}">
	        				<td style="width: 10%">
		        				<lable>${map[disi].skillScore}</lable>
		        			</td>
	        			</c:if>
	        			<td style="line-height: 20px;">考核结果<br>(是否通过)</td>
	        			<c:if test="${param.roleFlag eq  GlobalConstant.USER_LIST_GLOBAL}">
		        			<td>
		        				<select name="scoreResultId">
		        					<option>请选择</option>
		        					<option value="Y" <c:if test="${map[disi].scoreResultId eq 'Y'}">selected</c:if>>是</option>
		        					<option value="N" <c:if test="${map[disi].scoreResultId eq 'N'}">selected</c:if>>否</option>
		        				</select>
	        				</td>
	        			</c:if>
	        			<c:if test="${param.roleFlag eq  GlobalConstant.USER_LIST_LOCAL}">
	        				<td style="width: 10%">
	        					<c:if test="${map[disi].scoreResultId eq 'Y'}">
	        						<lable>是</lable>
	        					</c:if>
	        					<c:if test="${map[disi].scoreResultId eq 'N'}">
	        						<lable>否</lable>
	        					</c:if>
	        				</td>
	        			</c:if>
	        			<input type="hidden" name="scoreTypeId" value="${resScoreTypeEnumGraduationScore.id}"/>
	        			<input type="hidden" name="scoreTypeName" value="${resScoreTypeEnumGraduationScore.name}"/>
	        			<input type="hidden" name="scoreFlow" value="${map[disi].scoreFlow}"/>
	        			<c:if test="${param.roleFlag eq  GlobalConstant.USER_LIST_LOCAL}">
		        			<td style="width: 10%">
		        				<a style="cursor: pointer; color: blue;" onclick="auditing('${map[disi].scoreFlow}');">审核</a>
		        			</td>
	        			</c:if>
	        		</tr>
	        	</tbody>
	    </table>
	   <div style="margin-top: 10px;text-align: center;margin-bottom: 10px;">
	        		<input type="button" value="保&#12288;存" class="search" onclick="save();"/>
	        		<input type="button" value="关&#12288;闭" class="search" onclick="closes();"/>
	    </div>
</form>

