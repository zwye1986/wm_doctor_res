<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:if test="${param.view!=GlobalConstant.FLAG_Y}">
<script type="text/javascript">
	function nextOpt(step){
		if(false==$("#projForm").validationEngine("validate")){
			return false;
		}
		var form = $('#projForm');
		var action = form.attr('action');
		action+="?nextPageName="+step;
		form.attr("action" , action);
		form.submit();
	}
	
	function add(tb){
	 	$("#"+tb+"Div").append($("#"+tb+"Template").clone().attr("id",""));
	}
	
	function delTb(obj){
		jboxConfirm("确认删除?",function () {
			$(obj).parent().parent().parent().parent().remove();
		});
	}
	
	$(document).ready(function(){
		 var size =  $("#reserveLeadersDiv").children("table").size();
		 if(size == 0){
			 add('rese');
		 }
	});
</script>
</c:if>
	<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post"
		id="projForm" style="position: relative;">
		<input type="hidden" id="pageName" name="pageName" value="step2" /> 
		<input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
		<input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
		<input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>
		
		<font style="font-size: 14px; font-weight:bold;color: #333;">二、学科带头人情况</font>
        <table width="100%" cellspacing="0" cellpadding="0" class="basic">
			<tr>
				<th colspan="8" style="text-align: left;">&#12288;（一）学科带头人</th>
			</tr>
			<tr>
           		<td style="text-align: right;">姓名：</td>
				<td><input type="text" name="subjectLeaderName" value="${resultMap.subjectLeaderName}" class="inputText" style="width: 90%"/></td>
           		<td style="text-align: right;">性别：</td>
				<td>
					<select name="subjectLeaderGender" class="inputText" style="width: 90%;">
	                   <option value="">请选择</option>
	                   <c:forEach var="dict" items="${userSexEnumList}">
	                   		<c:if test="${dict.id != userSexEnumUnknown.id}">
	                   			<option value="${dict.name}" <c:if test="${resultMap.subjectLeaderGender eq dict.name}">selected="selected"</c:if>>${dict.name}</option>
	                   		</c:if>	 
	                   </c:forEach>
	                </select>
				</td>
           		<td style="text-align: right;">出生年月：</td>
				<td><input type="text" name="subjectLeaderBirthday" value="${resultMap.subjectLeaderBirthday}" onclick="WdatePicker({dateFmt:'yyyy-MM'})" class="inputText ctime" style="width: 90%" readonly="readonly"/></td>
           		<td style="text-align: right;">年龄：</td>
				<td><input type="text" name="subjectLeaderAge" value="${resultMap.subjectLeaderAge}" class="validate[custom[integer]] inputText" style="width: 90%"/></td>
			</tr>
			<tr>
           		<td style="text-align: right;">专业技术职称：</td>
				<td colspan="3">
					<select name="subjectLeaderTitle" class="inputText" style="width: 90%;">
						<option value="">请选择</option>
						<c:forEach var="dict" items="${dictTypeEnumUserTitleList}">
						<option value="${dict.dictName}" <c:if test="${resultMap.subjectLeaderTitle eq dict.dictName}">selected="selected"</c:if>>${dict.dictName }</option> 
						</c:forEach>
					</select>
				</td>
           		<td style="text-align: right;">行政职务：</td>
				<td colspan="3">
					<select name="subjectLeaderPost" class="inputText" style="width: 90%;">
	                   <option value="">请选择</option>
	                   <c:forEach var="dict" items="${dictTypeEnumUserPostList }">
	                   <option value="${dict.dictName}" <c:if test="${resultMap.subjectLeaderPost eq dict.dictName}">selected="selected"</c:if>>${dict.dictName }</option> 
	                   </c:forEach>
	                </select>
				</td>
			</tr>
			<tr>
           		<td style="text-align: right;">学术组织任职情况：</td>
				<td colspan="3"><input type="text" name="subjectLeaderAcademicPost" value="${resultMap.subjectLeaderAcademicPost}" class="inputText" style="width: 90%"/></td>
           		<td style="text-align: right;">擅长专业(最多3个)：</td>
				<td colspan="3"><input type="text" name="subjectLeaderMajor" value="${resultMap.subjectLeaderMajor}" class="inputText" style="width: 90%"/></td>
			</tr>
			<tr>
           		<td style="text-align: right;">最高学历：</td>
				<td colspan="3">
					<select name="subjectLeaderEducation" class="inputText" style="width: 90%;">
	                   <option value="">请选择</option>
	                   <c:forEach var="dict" items="${dictTypeEnumUserEducationList }">
	                   <option value="${dict.dictName}" <c:if test="${resultMap.subjectLeaderEducation eq dict.dictName}">selected="selected"</c:if>>${dict.dictName }</option> 
	                   </c:forEach>
	                </select>
				</td>
           		<td style="text-align: right;">手机：</td>
				<td colspan="3"><input type="text" name="subjectLeaderMobilePhone" value="${resultMap.subjectLeaderMobilePhone}" class="validate[custom[mobile]] inputText" style="width: 90%"/></td>
			</tr>
	        <tr>
		     	<td colspan="8" style="text-align:left;">
		     		<textarea placeholder="是否有海外留学背景。个人业务能力、科研、成果和学术地位等情况简介"  class="validate[maxSize[2000]] xltxtarea" name="subjectLeaderIntroduce">${resultMap.subjectLeaderIntroduce}</textarea>
		     	</td>
		    </tr>
		</table>
		
		<div id="reserveLeadersDiv">
		<c:forEach items="${resultMap.reserveLeader}" var="rese" varStatus="status">
		<table width="100%" cellspacing="0" cellpadding="0" class="basic" style="margin-top: 15px;">
			<tr>
				<th colspan="6" style="text-align: left;">&#12288;后备学科带头人
				<c:if test="${param.view!=GlobalConstant.FLAG_Y}">
					<span style="float: right;padding-right: 10px">
						<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="add('rese')"></img>
						<c:if test="${!status.first}">
							&#12288;<img title="删除"  style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delTb(this);"></img>
						</c:if>						
					</span>
				</c:if>
				</th>
			</tr>
			<tr>
           		<td style="text-align: right;">姓名：</td>
				<td><input type="text" name="reserveLeader_name" value="${rese.objMap.reserveLeader_name}" class="inputText" style="width: 90%"/></td>
           		<td style="text-align: right;">性别：</td>
				<td>
					<select name="reserveLeader_gender" class="inputText" style="width: 90%;">
	                   <option value="">请选择</option>
	                   <c:forEach var="dict" items="${userSexEnumList}">
	                   		<c:if test="${dict.id != userSexEnumUnknown.id}">
	                   			<option value="${dict.name}" <c:if test="${rese.objMap.reserveLeader_gender eq dict.name}">selected="selected"</c:if>>${dict.name}</option>
	                   		</c:if>	 
	                   </c:forEach>
	                </select>
				</td>
           		<td style="text-align: right;">出生年月：</td>
				<td><input type="text" name="reserveLeader_birthday" value="${rese.objMap.reserveLeader_birthday}" onclick="WdatePicker({dateFmt:'yyyy-MM'})" class="inputText ctime" style="width: 90%" readonly="readonly"/></td>
			</tr>
			<tr>
           		<td style="text-align: right;">专业技术职称：</td>
				<td colspan="2">
					<select name="reserveLeader_title" class="inputText" style="width: 90%;">
						<option value="">请选择</option>
						<c:forEach var="dict" items="${dictTypeEnumUserTitleList }">
						<option value="${dict.dictName}" <c:if test="${rese.objMap.reserveLeader_title eq dict.dictName}">selected="selected"</c:if>>${dict.dictName}</option> 
						</c:forEach>
					</select> 
				</td>
           		<td style="text-align: right;">行政职务：</td>
				<td colspan="2">
					<select name="reserveLeader_post" class="inputText" style="width: 90%;">
	                   <option value="">请选择</option>
	                   <c:forEach var="dict" items="${dictTypeEnumUserPostList }">
	                   <option value="${dict.dictName}" <c:if test="${rese.objMap.reserveLeader_post eq dict.dictName}">selected="selected"</c:if>>${dict.dictName }</option> 
	                   </c:forEach>
	                </select>
				</td>
			</tr>
			<tr>
           		<td style="text-align: right;">学术组织任职情况：</td>
				<td colspan="2"><input type="text" name="reserveLeader_academicPost" value="${rese.objMap.reserveLeader_academicPost}" class="inputText" style="width: 90%"/></td>
           		<td style="text-align: right;">擅长专业(最多3个)：</td>
				<td colspan="2"><input type="text" name="reserveLeader_major" value="${rese.objMap.reserveLeader_major}" class="inputText" style="width: 90%"/></td>
			</tr>
			<tr>
           		<td style="text-align: right;">最高学历：</td>
				<td colspan="2">
					<select name="reserveLeader_education" class="inputText" style="width: 90%;">
	                   <option value="">请选择</option>
	                   <c:forEach var="dict" items="${dictTypeEnumUserEducationList }">
	                   <option value="${dict.dictName}" <c:if test="${rese.objMap.reserveLeader_education eq dict.dictName}">selected="selected"</c:if>>${dict.dictName }</option> 
	                   </c:forEach>
	                </select>
				</td>
           		<td style="text-align: right;">手机：</td>
				<td colspan="2"><input type="text" name="reserveLeader_mobilePhone" value="${rese.objMap.reserveLeader_mobilePhone}" class="validate[custom[mobile]] inputText" style="width: 90%"/></td>
			</tr>
	        <tr>
		     	<td colspan="6" style="text-align:left;">
		     		<textarea placeholder="是否有海外留学背景。个人业务能力、科研、成果和学术地位等情况简介"  class="validate[maxSize[2000]] xltxtarea" name="reserveLeader_introduce">${rese.objMap.reserveLeader_introduce}</textarea>
		     	</td>
		    </tr>
		</table>
		</c:forEach>
		
		
		<div id="reseDiv"></div>
		</div>
	</form>

   	<c:if test="${param.view!=GlobalConstant.FLAG_Y}">
     	<div align="center" style="margin-top: 10px">
     	    <input id="prev" type="button" onclick="nextOpt('step1')" class="search" value="上一步"/>
     	    <input id="nxt" type="button" onclick="nextOpt('step3')" class="search" value="下一步"/>
     	</div>
   	</c:if>
   	
<div style="display: none;"> 	
   		<table id="reseTemplate" width="100%" class="basic" style="margin-top: 20px;">
			<tr>
				<th colspan="6" style="text-align: left;">&#12288;后备学科带头人
					<span style="float: right;padding-right: 10px">
						<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="add('rese')"></img>
						&#12288;<img title="删除"  style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delTb(this);"></img>
					</span>
				</th>
			</tr>
			<tr>
           		<td style="text-align: right;">姓名：</td>
				<td><input type="text" name="reserveLeader_name" class="inputText" style="width: 90%"/></td>
           		<td style="text-align: right;">性别：</td>
				<td>
					<select name="reserveLeader_gender" class="inputText" style="width: 90%;">
	                   <option value="">请选择</option>
	                   <c:forEach var="dict" items="${userSexEnumList}">
	                   		<c:if test="${dict.id != userSexEnumUnknown.id}">
	                   			<option value="${dict.name}">${dict.name}</option>
	                   		</c:if>	 
	                   </c:forEach>
	                </select>
				</td>
           		<td style="text-align: right;">出生年月：</td>
				<td><input type="text" name="reserveLeader_birthday" onclick="WdatePicker({dateFmt:'yyyy-MM'})" class="inputText ctime" style="width: 90%" readonly="readonly"/></td>
			</tr>
			<tr>
           		<td style="text-align: right;">专业技术职称：</td>
				<td colspan="2">
					<select name="reserveLeader_title" class="inputText" style="width: 90%;">
						<option value="">请选择</option>
						<c:forEach var="dict" items="${dictTypeEnumUserTitleList}">
						<option value="${dict.dictName}">${dict.dictName}</option> 
						</c:forEach>
					</select> 
				</td>
           		<td style="text-align: right;">行政职务：</td>
				<td colspan="2">
					<select name="reserveLeader_post" class="inputText" style="width: 90%;">
	                   <option value="">请选择</option>
	                   <c:forEach var="dict" items="${dictTypeEnumUserPostList}">
	                   <option value="${dict.dictName}">${dict.dictName}</option> 
	                   </c:forEach>
	                </select>
				</td>
			</tr>
			<tr>
           		<td style="text-align: right;">学术组织任职情况：</td>
				<td colspan="2"><input type="text" name="reserveLeader_academicPost" class="inputText" style="width: 90%"/></td>
           		<td style="text-align: right;">擅长专业(最多3个)：</td>
				<td colspan="2"><input type="text" name="reserveLeader_major" class="inputText" style="width: 90%"/></td>
			</tr>
			<tr>
           		<td style="text-align: right;">最高学历：</td>
				<td colspan="2">
					<select name="reserveLeader_education" class="inputText" style="width: 90%;">
	                   <option value="">请选择</option>
	                   <c:forEach var="dict" items="${dictTypeEnumUserEducationList}">
	                   <option value="${dict.dictName}">${dict.dictName}</option> 
	                   </c:forEach>
	                </select>
				</td>
           		<td style="text-align: right;">手机：</td>
				<td colspan="2"><input type="text" name="reserveLeader_mobilePhone" class="validate[custom[mobile]] inputText" style="width: 90%"/></td>
			</tr>
	        <tr>
		     	<td colspan="6" style="text-align:left;">
		     		<textarea placeholder="是否有海外留学背景。个人业务能力、科研、成果和学术地位等情况简介" class="validate[maxSize[2000]] xltxtarea" name="reserveLeader_introduce"></textarea>
		     	</td>
		    </tr>
		</table>
</div>