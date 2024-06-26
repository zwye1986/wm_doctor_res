<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/jsp/sczyres/htmlhead-sczyres.jsp">
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_scrollTo" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<script>
$(function(){
	<c:if test="${doctor.doctorStatusId==regStatusEnumPassing.id}">
		//志愿
		var catSpeId = '${recruit.catSpeId}';
		if(catSpeId=='${speCatEnumChineseMedicine.id}' || catSpeId=='${speCatEnumTCMGeneral.id}'){
			$(".speTr").show();
		}
		if(catSpeId=='${speCatEnumChineseMedicine.id}'){
			$(".secondSpeTr").show();
		}
		//二三志愿
		if('${recruit.swapFlag}'=='Y'){
			var catSpeId2 = '${moreSpe2.catSpeId}';
			if(catSpeId2=='${speCatEnumChineseMedicine.id}' || catSpeId2=='${speCatEnumTCMGeneral.id}'){
				$(".speTr2").show();
			}
			if(catSpeId=='${speCatEnumChineseMedicine.id}'){
				$(".secondSpeTr2").show();
			}
			var catSpeId3 = '${moreSpe3.catSpeId}';
			if(catSpeId3=='${speCatEnumChineseMedicine.id}' || catSpeId3=='${speCatEnumTCMGeneral.id}'){
				$(".speTr3").show();
			}
			if(catSpeId=='${speCatEnumChineseMedicine.id}'){
				$(".secondSpeTr3").show();
			}
		}
	</c:if>
	changeSwapFlag();
	<c:if test="${doctor.doctorStatusId!=regStatusEnumPassing.id}">
		$(".catSpeId").each(function(){
			getSpe($(this));
		});
		setTimeout( function(){
			$("#speId option[value=${recruit.speId}]").attr("selected","selected");
			$("#secondSpeId option[value=${recruit.secondSpeId}]").attr("selected","selected");
			if('${recruit.swapFlag}'=='Y'){
				$("#speId2 option[value=${moreSpe2.speId}]").attr("selected","selected");
				$("#secondSpeId2 option[value=${moreSpe2.secondSpeId}]").attr("selected","selected");
				$("#speId3 option[value=${moreSpe3.speId}]").attr("selected","selected");
				$("#secondSpeId3 option[value=${moreSpe3.secondSpeId}]").attr("selected","selected");
			}
		}, 305 );
	</c:if>
})

function showSingup(){
	<c:if test="${doctor.doctorStatusId==regStatusEnumPassing.id }">
		singup();
	</c:if>
	<c:if test="${doctor.doctorStatusId!=regStatusEnumPassing.id }">
		if($("#catSpeId").val()){
			$("#catSpeName").val($("#catSpeId :selected").text());	
		}
		if($("#speId").val()){
			$("#speName").val($("#speId :selected").text());	
		}
		if($("#secondSpeId").val()){
			$("#secondSpeName").val($("#secondSpeId :selected").text());
		}
		//第二、三志愿
		if($("#swapFlag:checked").val()=='Y'){
			var data = [];
			$(".more_spe").each(function(){
				var orderNum = $(this).find("[name='orderNum']").val();
				var orgFlow = $(this).find("[name='orgFlow']").val();
				var orgName = $(this).find("[name='orgFlow'] option:checked").text();
				var catSpeId = $(this).find("[name='catSpeId']").val();
				var catSpeName = $(this).find("[name='catSpeId'] option:checked").text();
				var speId = $(this).find("[name='speId']").val();
				if(!speId){
					speId = '';
				}
				var speName = '';
				if(speId){
					speName = $(this).find("[name='speId'] option:checked").text();
				}
				var secondSpeId = $(this).find("[name='secondSpeId']").val();
				if(!secondSpeId){
					secondSpeId = '';
				}
				var secondSpeName = '';
				if(secondSpeId){
				    secondSpeName = $(this).find("[name='secondSpeId'] option:checked").text();
				}
				var singleData = {
					orderNum:orderNum,
					orgFlow:orgFlow,
					orgName:orgName,
					catSpeId:catSpeId,
					catSpeName:catSpeName,
					speId:speId,
					speName:speName,
					secondSpeId:secondSpeId,
					secondSpeName:secondSpeName
				}
				data.push(singleData);
			})
		}
		var data0 = $("#recruitForm").serialize();
		if($("#swapFlag:checked").val()=='Y'){
			data0+="&jsonData="+JSON.stringify(data);
		}
		jboxPost("<s:url value='/sczyres/doctor/saveRecruit'/>" , data0 , function(resp){
			singup();
		} , null , false);
	</c:if>
}

function getCatSpe(obj){
	var orgFlow = $(obj).val();
	if(orgFlow){
		var url = "<s:url value='/sczyres/doctor/findcatspe'/>?orgFlow="+orgFlow;
		jboxGet(url , null , function(resp){
			$(obj).closest("tr").next().find("select").empty();//catspe
			$(obj).closest("tr").next().find("select").append("<option value=''>请选择</option>");
			$.each(resp , function(i , n){
				$(obj).closest("tr").next().find("select").append("<option value='"+n.speId+"'>"+n.speName+"</option>");
			});
		} , null , false);
	}
	$(obj).closest("tr").next().next().find("select").empty();//spe
	$(obj).closest("tr").next().next().find("select").append("<option value=''>请选择</option>");
}

function getSpe(obj){
	var catSpeId = $(obj).val();
	$(obj).closest("tr").next().hide();
	$(obj).closest("tr").next().next().hide();
	if(catSpeId=='${speCatEnumChineseMedicine.id}' || catSpeId=='${speCatEnumTCMGeneral.id}'){
		$(obj).closest("tr").next().show();
		var url = "<s:url value='/sczyres/doctor/findspe'/>?catSpeId="+catSpeId;
		jboxGet(url , null , function(resp){
			$(obj).closest("tr").next().find("select").empty();
			$(obj).closest("tr").next().find("select").append("<option value=''>请选择</option>");
			$.each(resp , function(i , n){
				$(obj).closest("tr").next().find("select").append("<option value='"+n.dictId+"'>"+n.dictName+"</option>");
			});
		} , null , false);
	}
	if(catSpeId=='${speCatEnumChineseMedicine.id}'){
		$(obj).closest("tr").next().next().show();
		$(obj).closest("tr").next().next().find("select").empty();
		$(obj).closest("tr").next().next().find("select").append("<option value=''>请选择</option>");
		<c:forEach items="${dictTypeEnumZySpeList}" var="dict">
		$(obj).closest("tr").next().next().find("select").append("<option value=${dict.dictId}>${dict.dictName}</option>");
		</c:forEach>
	}
	if(catSpeId=='${speCatEnumTCMAssiGeneral.id}'){
		$(obj).closest("tr").next().hide();
		$(obj).closest("tr").next().next().hide();
	}
}

function submitRecruit(){
	var a = 1;
	$(".catSpeId").each(function(){
		var catSpeId = $(this).val();
		if(checkLimit(catSpeId)==0){
			a=0;
		}
	});
	if(a==0){
		jboxInfo("基层医疗卫生机构和其他医疗卫生机构委派学员只允许填报中医全科或中医助理全科专业！");
		return;
	}
	if(!$("#recruitForm").validationEngine("validate")){
		return;
	}
	if(!$("#form2").validationEngine("validate")){
		return;
	}
	jboxConfirm("确认提交报名信息,提交后数据将无法修改?",function(){
		//设置catspename spename
		if($("#catSpeId").val()){
			$("#catSpeName").val($("#catSpeId :selected").text());
		}
		if($("#speId").val()){
			$("#speName").val($("#speId :selected").text());
		}
		if($("#secondSpeId").val()){
			$("#secondSpeName").val($("#secondSpeId :selected").text());
		}
		//第二、三志愿
		if($("#swapFlag:checked").val()=='Y'){
			var data = [];
			$(".more_spe").each(function(){
				var orderNum = $(this).find("[name='orderNum']").val();
				var orgFlow = $(this).find("[name='orgFlow']").val();
				var orgName = $(this).find("[name='orgFlow'] option:checked").text();
				var catSpeId = $(this).find("[name='catSpeId']").val();
				var catSpeName = $(this).find("[name='catSpeId'] option:checked").text();
				var speId = $(this).find("[name='speId']").val();
				if(!speId){
					speId = '';
				}
				var speName = '';
				if(speId){
					speName = $(this).find("[name='speId'] option:checked").text();
				}
				var secondSpeId = $(this).find("[name='secondSpeId']").val();
				if(!secondSpeId){
					secondSpeId = '';
				}
				var secondSpeName = '';
				if(secondSpeId){
					secondSpeName = $(this).find("[name='secondSpeId'] option:checked").text();
				}
				var singleData = {
					orderNum:orderNum,
					orgFlow:orgFlow,
					orgName:orgName,
					catSpeId:catSpeId,
					catSpeName:catSpeName,
					speId:speId,
					speName:speName,
					secondSpeId:secondSpeId,
					secondSpeName:secondSpeName
				}
				data.push(singleData);
			})
		}
		var data0 = $("#recruitForm").serialize();
		if($("#swapFlag:checked").val()=='Y'){
			data0+="&jsonData="+JSON.stringify(data);
		}
		jboxPost("<s:url value='/sczyres/doctor/submitRecruit'/>" , data0 , function(resp){
			if(resp=="1"){
				jboxTip("提交成功");
				setTimeout(function(){
					window.location.href="<s:url value='/sczyres/doctor/home'/>";
				},1000);
			}else{
				jboxTip(resp);
			}
		} , null , false);
	});
}
function changeSwapFlag(){
	var val = $("#swapFlag:checked").val();
	if(val=='Y'){
		$(".more_spe").show();
	}else{
		$(".more_spe").hide();
	}
}
function checkLimit(catSpeId){
	if('${doctor.doctorTypeId}'=='${sczyRecDocTypeEnumEntrust.id}' &&
			('${extInfo.medicalHeaithOrgId}'=='${3}'||'${extInfo.medicalHeaithOrgId}'=='${4}')&&
			catSpeId=='${speCatEnumChineseMedicine.id}'
	){
		return 0;
	}
}
</script>
<div class="col_main">
<div class="main_hd"><h2 class="underline">志愿填报</h2></div>
<form id="recruitForm" style="position:relative;">
   <div class="div_table">
   <input type="hidden" name="recruitFlow" value="${recruit.recruitFlow}"/>
   <table class="base_info">
	   <colgroup>
		   <col width="17%"/>
		   <col width="83%"/>
	   </colgroup>
	   <c:if test="${doctor.doctorStatusId==regStatusEnumUnPassed.id}">
		   <tr>
			   <td colspan="2" style="color: red">审核不通过，原因：${doctor.disactiveReason}</td>
		   </tr>
	   </c:if>
     <tr>
       <th><font color="red">*</font>培训基地：</th>
       <td>   
         <c:choose>
       	 	<c:when test="${doctor.doctorStatusId==regStatusEnumPassing.id }">
       	 		${recruit.orgName }
       	 	</c:when>
       	 	<c:otherwise>
       	 		 <select class="select validate[required]" id="orgFlow" name="orgFlow"  onchange="getCatSpe(this);">
		             <option value="">请选择</option>
		             <c:forEach items="${hospitals}" var="hosptial">
		                <option value='${hosptial.orgFlow}' <c:if test='${recruit.orgFlow eq hosptial.orgFlow}'>selected="selected"</c:if>>${hosptial.orgName}</option>
		             </c:forEach>
          		</select>
       	 	</c:otherwise>
       	 </c:choose>   
       </td>
     </tr>
     <tr>
       <th><font color="red">*</font>培训专业：</th>
       <td>   
          <c:choose>
       		<c:when test="${doctor.doctorStatusId==regStatusEnumPassing.id }">
       			${recruit.catSpeName}
       		</c:when>
      		<c:otherwise>
      			 <select class="select validate[required] catSpeId" id="catSpeId" name="catSpeId" style="width: 150px;" onchange="getSpe(this);">
		            <option value="">请选择</option>
		            <c:forEach items="${catspes}" var="catspe">
		               <option value="${catspe.speId}" <c:if test='${recruit.catSpeId eq catspe.speId}'>selected="selected"</c:if>>${catspe.speName}</option>
		            </c:forEach>
		          </select>
         		 <input type="hidden" id='catSpeName' name="catSpeName" value='${recruit.catSpeName}'/>
      		</c:otherwise>
       	</c:choose>
       </td>
     </tr>
	   <tr style="display:none" class="speTr">
		   <th><font color="red">*</font>对应专业：</th>
		   <td>
			   <c:choose>
				   <c:when test="${doctor.doctorStatusId==regStatusEnumPassing.id }">
					   ${recruit.speName}
				   </c:when>
				   <c:otherwise>
					   <select class="select validate[required]" style="width: 150px;" name="speId" id="speId">
					   </select>
					   <input type="hidden" id="speName" name="speName" value='${recruit.speName}'/>
				   </c:otherwise>
			   </c:choose>
		   </td>
	   </tr>
     <tr style="display:none" class="secondSpeTr">
       <th><font color="red">*</font>二级专业：</th>
       <td>   
          <c:choose>
	       		<c:when test="${doctor.doctorStatusId==regStatusEnumPassing.id }">
	       			${recruit.secondSpeName}
	       		</c:when>
	       		<c:otherwise>
	       			 	<select class="select validate[required]" id="secondSpeId"  style="width: 150px;" name="secondSpeId">
			          </select>
			          <input type="hidden" id="secondSpeName" name="secondSpeName" value='${recruit.secondSpeName}'/>
	       		</c:otherwise>
       		</c:choose>
       </td>
     </tr>
	 <tr>
		 <th>
			 <lable><font color="red">*</font>是否服从调剂：</lable>
		 </th>
		 <td>
			 <input type="checkbox" value="Y" name="swapFlag" id="swapFlag" ${recruit.swapFlag eq 'Y'?'checked':''} onclick="changeSwapFlag()"/>&#12288;服从调剂后，在一志愿不录取的情况下，管理员将根据你填报的调剂志愿进行调配，参加复试。
		 </td>
	 </tr>
	 </table>
   		</div>
	 </form>
	<div class="div_table">
	<form id="form2">
	   <table class="more_spe base_info">
		   <colgroup>
			   <col width="17%"/>
			   <col width="83%"/>
		   </colgroup>
	   <input name="orderNum" value="2" type="hidden">
	   <tr>
		   <td colspan="2">第二志愿</td>
	   </tr>
	   <tr>
		   <th>&#12288;培训基地：</th>
		   <td>
			   <c:choose>
				   <c:when test="${doctor.doctorStatusId==regStatusEnumPassing.id }">
					   ${moreSpe2.orgName}
				   </c:when>
				   <c:otherwise>
					   <select class="select validate[required]" name="orgFlow" onchange="getCatSpe(this);">
						   <option value="">请选择</option>
						   <c:forEach items="${hospitals}" var="hosptial">
							   <option value='${hosptial.orgFlow}' ${moreSpe2.orgFlow eq hosptial.orgFlow?'selected':''}>${hosptial.orgName}</option>
						   </c:forEach>
					   </select>
				   </c:otherwise>
			   </c:choose>
		   </td>
	   </tr>
	   <tr>
		   <th>&#12288;培训专业：</th>
		   <td>
			   <c:choose>
				   <c:when test="${doctor.doctorStatusId==regStatusEnumPassing.id }">
					   ${moreSpe2.carSpeName}
				   </c:when>
				   <c:otherwise>
					   <select class="select validate[required] catSpeId" name="catSpeId" style="width: 150px;" onchange="getSpe(this);">
						   <option value="">请选择</option>
						   <c:forEach items="${catspes}" var="catspe">
							   <option value="${catspe.speId}" ${moreSpe2.catSpeId eq catspe.speId?'selected':''}>${catspe.speName}</option>
						   </c:forEach>
					   </select>
					   <input type="hidden" id='catSpeName' name="catSpeName" value='${recruit.catSpeName}'/>
				   </c:otherwise>
			   </c:choose>
		   </td>
	   </tr>
	   <tr style="display:none" class="speTr2">
		   <th>&#12288;对应专业：</th>
		   <td>
			   <c:choose>
				   <c:when test="${doctor.doctorStatusId==regStatusEnumPassing.id }">
					   ${moreSpe2.speName}
				   </c:when>
				   <c:otherwise>
					   <select class="select validate[required]" style="width: 150px;" name="speId" id="speId2">
					   </select>
					   <input type="hidden" id="speName" name="speName" value='${recruit.speName}'/>
				   </c:otherwise>
			   </c:choose>
		   </td>
	   </tr>
	   <tr style="display:none" class="secondSpeTr2">
		   <th>&#12288;二级专业：</th>
		   <td>
			   <c:choose>
				   <c:when test="${doctor.doctorStatusId==regStatusEnumPassing.id }">
					   ${moreSpe2.secondSpeName}
				   </c:when>
				   <c:otherwise>
					   <select class="select validate[required]" style="width: 150px;" name="secondSpeId" id="secondSpeId2">
					   </select>
					   <input type="hidden" id="secondSpeName" name="secondSpeName" value='${recruit.secondSpeName}'/>
				   </c:otherwise>
			   </c:choose>
		   </td>
	   </tr>
   </table>
	</form>
		<table class="more_spe  base_info">
			<colgroup>
				<col width="17%"/>
				<col width="83%"/>
			</colgroup>
			<input name="orderNum" value="3" type="hidden">
			<tr>
				<td colspan="2">第三志愿</td>
			</tr>
			<tr>
				<th>&nbsp;培训基地：</th>
				<td>
					<c:choose>
						<c:when test="${doctor.doctorStatusId==regStatusEnumPassing.id }">
							${moreSpe3.orgName}
						</c:when>
						<c:otherwise>
							<select class="select validate[required]" name="orgFlow" onchange="getCatSpe(this);">
								<option value="">请选择</option>
								<c:forEach items="${hospitals}" var="hosptial">
									<option value='${hosptial.orgFlow}' ${moreSpe3.orgFlow eq hosptial.orgFlow?'selected':''}>${hosptial.orgName}</option>
								</c:forEach>
							</select>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<th>培训专业：</th>
				<td>
					<c:choose>
						<c:when test="${doctor.doctorStatusId==regStatusEnumPassing.id }">
							${moreSpe3.catSpeName}
						</c:when>
						<c:otherwise>
							<select class="select validate[required] catSpeId" name="catSpeId" style="width: 150px;" onchange="getSpe(this);">
								<option value="">请选择</option>
								<c:forEach items="${catspes}" var="catspe">
									<option value="${catspe.speId}" ${moreSpe3.catSpeId eq catspe.speId?'selected':''}>${catspe.speName}</option>
								</c:forEach>
							</select>
							<input type="hidden" id='catSpeName' name="catSpeName" value='${recruit.catSpeName}'/>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr style="display:none" class="speTr3">
				<th>对应专业：</th>
				<td>
					<c:choose>
						<c:when test="${doctor.doctorStatusId==regStatusEnumPassing.id }">
							${moreSpe3.speName}
						</c:when>
						<c:otherwise>
							<select class="select validate[required]" style="width: 150px;" name="speId" id="speId3">
							</select>
							<input type="hidden" id="speName" name="speName" value='${recruit.speName}'/>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr style="display:none" class="secondSpeTr3">
				<th>二级专业：</th>
				<td>
					<c:choose>
						<c:when test="${doctor.doctorStatusId==regStatusEnumPassing.id }">
							${moreSpe3.secondSpeName}
						</c:when>
						<c:otherwise>
							<select class="select validate[required]" style="width: 150px;" name="secondSpeId" id="secondSpeId3">
							</select>
							<input type="hidden" id="secondSpeName" name="secondSpeName" value='${recruit.secondSpeName}'/>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</table>
	</div>
   <div class="btn_info">
       <a class="btn_blue" onclick="showSingup()">上一步</a>
       <c:if test='${doctor.doctorStatusId!=regStatusEnumPassing.id}'>
           <a class="btn" onclick="submitRecruit();">提&#12288;交</a>
       </c:if>
       <c:if test='${doctor.doctorStatusId==regStatusEnumPassing.id}'>
       		&#12288;&#12288;<font color="red">Tip:您的报名信息已提交，请等待基地审核!</font>
       </c:if>
   </div>
</div>